/**
   @version 1.30 2000-05-12
   @author Cay Horstmann
*/
import java.awt.geom.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class DrawLineApplet105 extends JApplet
{  
   public void init()
   {  
      Container contentPane = getContentPane();
      contentPane.add(new DrawLine105());
     
   }  
}

class DrawLine105 extends JPanel implements MouseListener, ActionListener
{
  private JButton buttonStart;
  private JButton buttonEnd;
  private boolean canDrawStart = false;
  private boolean canDrawEnd = true;
  private static boolean firstClick = false;
  private static boolean drawCorrect = false;
  private static int clickCount = -1;
  private static int startX = 0;
  private static int startY = 0;
  private static int endX = 0; 
  private static int endY = 0;

  private static int startX2 = 0;
  private static int startY2 = 0;
  private static int endX2 = 0; 
  private static int endY2 = 0;

  private static int startX3 = 0;
  private static int startY3 = 0;
  private static int endX3 = 0; 
  private static int endY3 = 0;

  private static int startX4 = 0;
  private static int startY4 = 0;
  private static int endX4 = 0; 
  private static int end43 = 0;

  private static int startX5 = 0;
  private static int startY5 = 0;

  private boolean done1 = false;
  private boolean done2 = false;
  private boolean done3 = false;
  private boolean done4 = false;
  private boolean done5 = false;
  
  private static int xcenterO = 30;
  private static int ycenterO = 500;// Center position of O

  private static int xcenterA = xcenterO + 1*30;
  private static int ycenterA = ycenterO - 4*30; // Center position of A
  private static int xcenterB = xcenterO + 6*30;
  private static int ycenterB = ycenterO - 6*30; // Center position of B
  private static int xcenterC = xcenterB + 1*30;
  private static int ycenterC = ycenterB + 2*30; // Center position of C  
  private static int xcenterD = xcenterC-10*30;
  private static int ycenterD = ycenterC; // Center position of D
   
  private static int xcenterO2 = 530;
  private static int ycenterO2 = 30; // Center position of O2
  private static int xcenterH = xcenterC+80;
  private static int ycenterH = ycenterC;
  private static int xcenterH2 = xcenterD+80;
  private static int ycenterH2 = ycenterD;
  private static int xcenterLO = xcenterO2;
  private static int ycenterLO = ycenterO2+80;
  private static int xcenterLO2 = xcenterB;
  private static int ycenterLO2 = ycenterB+80;
  private static int xcenterLH = xcenterD;
  private static int ycenterLH = ycenterD-80;
  private static int xcenterLH2 = xcenterH2;
  private static int ycenterLH2 = ycenterH2-80;

  private static int xcenterA2 = xcenterA;
  private static int ycenterA2 = ycenterA;
  private static int xcenterB2 = xcenterB;
  private static int ycenterB2 = ycenterB + 4*30;
  private static int xcenterC2 = xcenterC - 3*30;
  private static int ycenterC2 = ycenterC + 3*30;
  private static int xcenterD2 = xcenterD + 160;
  private static int ycenterD2 = ycenterD;

  private static int xcenterA3 = xcenterA2 + 10*30;
  private static int ycenterA3 = ycenterA2;
  private static int xcenterB3 = xcenterB2 + 10*30;
  private static int ycenterB3 = ycenterB2;
  private static int xcenterC3 = xcenterC2 + 10*30;
  private static int ycenterC3 = ycenterC2;

  private static String comment = "";
  private static String comment2 = "";
  private static String target = "50"; 
 
  Connection dbCon = null;
  ResultSetMetaData rsmd = null;
  PreparedStatement ptmt = null;
  ResultSet rs = null;

  //Constructor
  DrawLine105()
  {
    //Enables the closing of the window.
    //addWindowListener(new MyFinishWindow());
   
    addMouseListener(this);
    clickCount = -1;
    firstClick = false;
    drawCorrect = false;
    done1 = false;
    done2 = false;
    done3 = false;
    done4 = false;
    done5 = false; 

    try{       
       connect();                        
       ptmt = dbCon.prepareStatement("select term2 from hintMatch where term1=?");
       ptmt.clearParameters();
       ptmt.setString(1, "line"); 
       rs = ptmt.executeQuery();
       while (rs.next()){
          target = rs.getString(1);
       }
    }catch (SQLException ex){
       ex.printStackTrace();
    }catch (ClassNotFoundException ex){
       ex.printStackTrace();
    }finally{
       try{
          if (rs != null) rs.close();
          if (ptmt != null) ptmt.close();
          if (dbCon != null) dbCon.close();
       }catch (SQLException e2){
          e2.printStackTrace();
       }
    }    
  }

  public void actionPerformed(ActionEvent e) {
     String command = e.getActionCommand();   
     System.out.println("Button clicked");
  }
  
  public void mousePressed(MouseEvent e){}

  public void mouseClicked(MouseEvent e){  
     clickCount ++;
     if (done1==false){        
         startX = e.getX();
         startY = e.getY();       
     }
     firstClick = true;     
     repaint();
     System.out.println("Mouse clicked and e.getX()=" + e.getX() + " and e.getY()=" + e.getY() + " and target = " + target + " and clickCount = " + clickCount);    
  }

  public void clearWindow(Graphics2D g)
  {
    g.setPaint(Color.white);
    g.fill(new Rectangle(0,0,getWidth(),getHeight()));
    g.setPaint(Color.black);
  }
  
  public void paint(Graphics g)
  {
    Graphics2D g2d = (Graphics2D) g;
    clearWindow(g2d);
    drawGrid(g2d);

    //Use of antialiasing to have nicer lines.
    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);

    //The lines should have a thickness of 3.0 instead of 1.0.
    BasicStroke bs = new BasicStroke(3.0f);
    g2d.setStroke(bs);

    drawArc(g2d);
    //drawArc2(g2d); 
    drawRuler(g2d);  
    drawRuler2(g2d); 
    drawDirection(g2d);   
  }

  public void drawArc2(Graphics2D g2d){
     g2d.setPaint(Color.red);
     g2d.drawArc(xcenterB, ycenterB, 480, 480, -90, 0); 
  }

  public void drawArc(Graphics2D g2d){
     g2d.setPaint(Color.red);
     //g2d.drawArc(xcenterA, ycenterA, 480, 480, 0, 360); 
     //g2d.drawRect(xcenterA, ycenterA, 400, 200);
     
     //g2d.fillArc(xcenterA-5, ycenterA-5, 10, 10, 0, 360);      
     //g2d.drawString("A", xcenterA+10, ycenterA+10); 

     g2d.fillArc(xcenterB-5, ycenterB-5, 10, 10, 0, 360);      
     g2d.drawString("B", xcenterB+10, ycenterB+10); 

     //g2d.fillArc(xcenterC-5, ycenterC-5, 10, 10, 0, 360);      
     //g2d.drawString("C", xcenterC+10, ycenterC+10); 
     
     GeneralPath gp = new GeneralPath();
     /*
     gp.moveTo(xcenterA,ycenterA);
     gp.lineTo(xcenterB,ycenterB);   
     gp.moveTo(xcenterB,ycenterB);
     gp.lineTo(xcenterC,ycenterC); 
     gp.moveTo(xcenterC,ycenterC);     
     gp.lineTo(xcenterA,ycenterA);
     g2d.draw(gp);
     
     g2d.drawString("��", xcenterO2-20, ycenterO2+20); 
     GeneralPath gp = new GeneralPath(); 
     gp.moveTo(xcenterO2,ycenterO2+20);
     gp.lineTo(xcenterO2,ycenterO2);
     gp.moveTo(xcenterO2,ycenterO2);
     gp.lineTo(xcenterO2-5,ycenterO2+5);
     gp.moveTo(xcenterO2,ycenterO2);
     gp.lineTo(xcenterO2-5,ycenterO2+5);   
     gp.moveTo(xcenterO2,ycenterO2);
     gp.lineTo(xcenterO2+5,ycenterO2+5);   
     */
     if (clickCount>=0){
        g2d.setPaint(Color.black); 
        gp = new GeneralPath();
        if (done1){             
           g2d.drawArc(xcenterB-2*30, ycenterB-2*30, 4*30, 4*30, 0, 360);                                    
        }else{
           if (aroundCenter(startX,startY,xcenterB,ycenterB,4*30/2)){
              done1 = true;     
              drawCorrect = true;           
              g2d.drawArc(xcenterB-2*30, ycenterB-2*30, 4*30, 4*30, 0, 360);                             
           }           
        }  
        /*
        if (done2){             
           gp.moveTo(xcenterB2,ycenterB2);
           gp.lineTo(xcenterC2,ycenterC2);  
           gp.moveTo(xcenterA2,ycenterA2);
           gp.lineTo(xcenterC2,ycenterC2);                    
        }else{
           if (aroundPoint(startX2,startY2,xcenterC2,ycenterC2)){
              done2 = true;               
              gp.moveTo(xcenterB2,ycenterB2);
              gp.lineTo(xcenterC2,ycenterC2);  
              gp.moveTo(xcenterA2,ycenterA2);
              gp.lineTo(xcenterC2,ycenterC2);                            
           }           
        }

        if (done3){           
           g2d.fillArc(xcenterA3-5, ycenterA3-5, 10, 10, 0, 360);      
           g2d.drawString("A3", xcenterA3+10, ycenterA3+10); 
        }else{
           if (aroundPoint(startX3,startY3,xcenterA3,ycenterA3)){
              done3 = true;              
              g2d.fillArc(xcenterA3-5, ycenterA3-5, 10, 10, 0, 360);      
              g2d.drawString("A3", xcenterA3+10, ycenterA3+10);                      
           }           
        }

        if (done4){           
           gp.moveTo(xcenterA3,ycenterA3);
           gp.lineTo(xcenterB3,ycenterB3);
        }else{
           if (aroundPoint(startX4,startY4,xcenterB3,ycenterB3)){
              done4 = true;              
              gp.moveTo(xcenterA3,ycenterA3);
              gp.lineTo(xcenterB3,ycenterB3);                        
           }           
        }    

        if (done5){           
           gp.moveTo(xcenterB3,ycenterB3);
           gp.lineTo(xcenterC3,ycenterC3);  
           gp.moveTo(xcenterA3,ycenterA3);
           gp.lineTo(xcenterC3,ycenterC3); 
        }else{
           if (aroundPoint(startX5,startY5,xcenterC3,ycenterC3)){
              done5 = true;    
              drawCorrect = true;            
              gp.moveTo(xcenterB3,ycenterB3);
              gp.lineTo(xcenterC3,ycenterC3);  
              gp.moveTo(xcenterA3,ycenterA3);
              gp.lineTo(xcenterC3,ycenterC3);                        
           }           
        }
        */                    
     }
     g2d.draw(gp);

     if (clickCount>=0){
        if (done1==false){            
           comment = "�㻹û�л���,����ѡ���Ե�BΪԲ�ĵ�ֱ����4cm��Բ���ϵ�һ��,�㻭��" + (clickCount+1) + "��;"; 
           comment2 = "";
        }else{
           comment = "����ȫ������,�㻭��" + (clickCount+1) + "��;�뷵��ԭ����ҳ������ťֱ���ύ"; 
           comment2 = "";                              
        }       
     }else{   
        comment = "ͼ�и����˵�B�����Ե�BΪԲ�Ļ�һ��ֱ����4cm��Բ��";        
        comment2 = "����ѡ���Ե�BΪԲ�ĵ�ֱ����4cm��Բ���ϵ�һ��";        
     }
     g2d.drawString(comment, 50, 550);
     g2d.drawString(comment2, 50, 570);

     if (drawCorrect){
        try{       
           connect();                        
           ptmt = dbCon.prepareStatement("select term2 from hintMatch where term1=?");
           ptmt.clearParameters();
           ptmt.setString(1, "line"); 
           rs = ptmt.executeQuery();
           while (rs.next()){
             target = rs.getString(1);
           }
           ptmt = dbCon.prepareStatement("update hintMatch set term2=? where term1=?");
           ptmt.clearParameters();
           ptmt.setString(1, target); 
           ptmt.setString(2, "input");
           ptmt.executeUpdate();
           ptmt = dbCon.prepareStatement("update hintMatch set term2=? where term1=?");
           ptmt.clearParameters();
           ptmt.setString(1, "done and correct"); 
           ptmt.setString(2, "lineHint");
           ptmt.executeUpdate();
        }catch (SQLException ex){
          ex.printStackTrace();
        }catch (ClassNotFoundException ex){
          ex.printStackTrace();
        }finally{
          try{
             if (rs != null) rs.close();
             if (ptmt != null) ptmt.close();
             if (dbCon != null) dbCon.close();
          }catch (SQLException e2){
             e2.printStackTrace();
          }
        } 
     }else if (clickCount>3){
        try{       
           connect();          
           ptmt = dbCon.prepareStatement("update hintMatch set term2=? where term1=?");
           ptmt.clearParameters();
           ptmt.setString(1, "0"); 
           ptmt.setString(2, "input");
           ptmt.executeUpdate();
           ptmt = dbCon.prepareStatement("update hintMatch set term2=? where term1=?");
           ptmt.clearParameters();
           ptmt.setString(1, "done and not correct"); 
           ptmt.setString(2, "lineHint");
           ptmt.executeUpdate();
        }catch (SQLException ex){
          ex.printStackTrace();
        }catch (ClassNotFoundException ex){
          ex.printStackTrace();
        }finally{
          try{
             if (rs != null) rs.close();
             if (ptmt != null) ptmt.close();
             if (dbCon != null) dbCon.close();
          }catch (SQLException e2){
             e2.printStackTrace();
          }
        } 
     }     
  }

  public void drawGrid(Graphics2D g2d){
     g2d.setPaint(Color.yellow);
     GeneralPath gp = new GeneralPath();
     for (int i=30; i<=510; i+=30){
        gp.moveTo(i,50);
        gp.lineTo(i,500);
     }
     for (int i=500; i>=50; i-=30){
        gp.moveTo(30,i);
        gp.lineTo(510,i);
     }
     g2d.draw(gp);
  }

  public void drawRuler(Graphics2D g2d){
     g2d.setPaint(Color.red);
     //g2d.drawArc(xcenterA-10, ycenterA-10, 20, 20, 0, 360);
     //g2d.drawString("A", xcenterA-20, ycenterA+20);
     
     GeneralPath gp = new GeneralPath();
     String[] strs = new String[]{"","1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16"};
     //String[] strs2 = new String[]{"","��","","��","","��","","��","","��",""};
     
     int intV = 0;
     for (int i=0; i<=160; i++){        
        if (i % 10 == 0){
           intV = i/10;           
           g2d.drawString(strs[intV], 30+i*3, 520);
           //g2d.drawString(strs2[intV], 100+i*4, 540);                      
        } 
     }
     gp.moveTo(30,500);
     gp.lineTo(510,500);
     //gp.moveTo(410,530);
     //gp.lineTo(400,520);  
     //gp.moveTo(410,530);
     //gp.lineTo(400,540);   
     g2d.drawString("", 500, 530);   
     //g2d.drawString("1:20000", 230+60, 550);  
     g2d.drawString("", 230, 550);    
     g2d.draw(gp);
  }

  public void drawRuler2(Graphics2D g2d){
     g2d.setPaint(Color.red);     
     
     GeneralPath gp = new GeneralPath();
     String[] strs = new String[]{"0","1","2","3","4","5","6","7","8","9","10","11","12","13","14","15",""};
     //String str;
     int intV = 0;
     for (int i=0; i<=160; i++){        
        if (i % 10 == 0){
           intV = i/10;
           //str = intV*50 + "";
           g2d.drawString(strs[intV], 30-20, 500-i*3);           
        }
     }      
     gp.moveTo(30,500);
     gp.lineTo(30,20);
     //gp.moveTo(140,20);
     //gp.lineTo(130,30);  
     //gp.moveTo(140,20);
     //gp.lineTo(150,30);  
     //g2d.drawString("20", 80, 100);
     g2d.drawString("", 180, 40);
     g2d.draw(gp);
  } 

  public void drawDirection(Graphics2D g2d){
     g2d.setPaint(Color.red);     
     
     GeneralPath gp = new GeneralPath();
     gp.moveTo(530,140);
     gp.lineTo(530,110);
     gp.moveTo(530,110);
     gp.lineTo(540,120);
     gp.moveTo(530,110);
     gp.lineTo(520,120);
    
     g2d.drawString("��", 530, 90); 
     g2d.draw(gp); 
  }

  public boolean perpendicular(int startX, int startY, int endX, int endY, int targetStartX, int targetStartY, int targetEndX, int targetEndY){
     if (targetStartX==targetEndX && targetStartY!=targetEndY){
        if (Math.abs(startY-endY)<=10 && startX!=endX)
           return true;
        else
           return false;
     }else if (targetStartY==targetEndY && targetStartX!=targetEndX){
        if (Math.abs(startX-endX)<=10 && startY!=endY)
           return true;
        else
           return false;
     }else{
        double tangent1 = ((double)(targetEndX - targetStartX)) / (-(targetEndY - targetStartY));
        double tangent2 = ((double)(endX - startX)) / (-(endY - startY));
        if (Math.abs(tangent1 * tangent2 + 1)<=0.5)
           return true;
        else 
           return false;
     }
  }

  public boolean aroundPoint(int startX, int startY, int targetX, int targetY){
     if (Math.abs(startX-targetX)<=10 && Math.abs(startY-targetY)<=10)
        return true;
     return false;
  }

  public boolean aroundCenter(int startX, int startY, int targetX, int targetY, double radius){
     double dist = Math.sqrt((startX-targetX)*(startX-targetX)+(startY-targetY)*(startY-targetY));
     if (dist>=radius-10 && dist<=radius+10)
        return true;
     return false;
  }

   public boolean inLine(int startX, int endX, int startY, int endY, int pX, int pY){
       /*
       int r1 = (endY-startY) * (pX-startX);
       int r2 = (endX-startX) * (pY-startY);
       System.out.println("r1=" + r1 + ", r2=" + r2);
       if (Math.abs(r1-r2)<=10)
          return true;
       */
       double d1=0, d2=0;
       if ((endX-startX)!=0 && (pX-startX)!=0){
          d1=((double)(endY-startY))/(endX-startX);
          d2=((double)(pY-startY))/(pX-startX);
          System.out.println("d1=" + d1 + ", d2=" + d2);
          if (Math.abs(d1-d2)<=15)
             return true;
       }else if ((endX-startX)==0 && (pX-startX)==0)
          return true;
       return false;
   }

   public void mouseEntered(MouseEvent e){}
   public void mouseExited(MouseEvent e){}       
   public void mouseReleased(MouseEvent e){} 

   private boolean connect() throws ClassNotFoundException, SQLException
    {
       //Class.forName("org.hsqldb.jdbcDriver");
       //dbCon = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/xdb", "SA", "");
       Class.forName("org.sqlite.JDBC"); 
       dbCon = DriverManager.getConnection("jdbc:sqlite:/home/xtang/Downloads/sqlite_tools/databases/mi3/class_db"); 
       return true;     
    }   
     
}
