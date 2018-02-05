/**
   @version 1.30 2000-05-12
   @author Cay Horstmann
*/
import java.awt.geom.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class DrawLineApplet131 extends JApplet
{  
   public void init()
   {  
      Container contentPane = getContentPane();
      contentPane.add(new DrawLine131());
     
   }  
}

class DrawLine131 extends JPanel implements MouseListener, ActionListener
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
  private static int startX6 = 0;
  private static int startY6 = 0;

  private boolean done1 = false;
  private boolean done2 = false;
  private boolean done3 = false;
  private boolean done4 = false;
  private boolean done5 = false;
  private boolean done6 = false;
  
  private static int xcenterO = 60;
  private static int ycenterO = 320; // Center position of O
  private static int xcenterO2 = xcenterO + 6*30; //150
  private static int ycenterO2 = ycenterO - 4*30; // Center position of O2:230
  private static int xcenterA = xcenterO2 - 2*30;
  private static int ycenterA = ycenterO2 - 2*30; // Center position of A
  private static int xcenterB = xcenterA + 4*30;
  private static int ycenterB = ycenterA; // Center position of B
  private static int xcenterC = xcenterB;
  private static int ycenterC = ycenterB+4*30; // Center position of C  
  private static int xcenterD = xcenterC-4*30;
  private static int ycenterD = ycenterC; // Center position of D 
  private static int xcenterE = (xcenterA+xcenterB)/2;
  private static int ycenterE = (ycenterA+ycenterB)/2; // Center position of E  
  private static int xcenterF = (xcenterC+xcenterD)/2;
  private static int ycenterF = (ycenterC+ycenterD)/2;// Center position of F  
  
  private static int xcenterO3 = (xcenterO+2*xcenterD)/3;
  private static int ycenterO3 = (ycenterO+2*ycenterD)/3; // Center position of O2
 
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
  private static int ycenterA2 = ycenterO + (ycenterO-ycenterA);
  private static int xcenterB2 = xcenterB;
  private static int ycenterB2 = ycenterO + (ycenterO-ycenterB);
  private static int xcenterC2 = xcenterC;
  private static int ycenterC2 = ycenterO + (ycenterO-ycenterC);
  private static int xcenterD2 = xcenterD;
  private static int ycenterD2 = ycenterD;

  private static int xcenterA3 = xcenterA2 + 6*30;
  private static int ycenterA3 = ycenterA2 - 4*30;
  private static int xcenterB3 = xcenterA3;
  private static int ycenterB3 = ycenterA3 + 2*30;
  private static int xcenterC3 = xcenterB3 + 6*30;
  private static int ycenterC3 = ycenterB3;

  private static String comment = "";
  private static String comment2 = "";
  private static String target = "50"; 
 
  Connection dbCon = null;
  ResultSetMetaData rsmd = null;
  PreparedStatement ptmt = null;
  ResultSet rs = null;

  //Constructor
  DrawLine131()
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
    done4= false;
    done5 = false;
    done6= false;

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
     }else if (done2==false){        
         startX2 = e.getX();
         startY2 = e.getY();       
     }else if (done3==false){        
         startX3 = e.getX();
         startY3 = e.getY();       
     }else if (done4==false){        
         startX4 = e.getX();
         startY4 = e.getY();       
     }else if (done5==false){        
         startX5 = e.getX();
         startY5 = e.getY();       
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
    //drawRuler(g2d);  
    //drawRuler2(g2d);
    //drawRatio(g2d); 
    //drawDirection(g2d);   
  }

  public void drawArc2(Graphics2D g2d){
     g2d.setPaint(Color.red);
     g2d.drawArc(xcenterB, ycenterB, 480, 480, -90, 0); 
  }

  public void drawArc(Graphics2D g2d){
     //g2d.setPaint(Color.red);
     //g2d.drawArc(xcenterA, ycenterA, 480, 480, 0, 360); 
     //g2d.drawRect(xcenterA, ycenterA, 400, 200);
     /*
     g2d.setPaint(Color.white);
     g2d.fillArc(xcenterO2-60, ycenterO2-60, 120, 120, 0, 360); 
     g2d.setPaint(Color.red);    
     g2d.drawArc(xcenterO2-60, ycenterO2-60, 120, 120, 0, 360); 
     */
     //g2d.drawString("�㳡", xcenterO2+10, ycenterO2-10); 

     g2d.fillArc(xcenterA-5, ycenterA-5, 10, 10, 0, 360);      
     g2d.drawString("A", xcenterA+10, ycenterA-10); 
     /*
     g2d.fillArc(xcenterB-5, ycenterB-5, 10, 10, 0, 360);      
     g2d.drawString("B", xcenterB-10, ycenterB-10); 

     g2d.fillArc(xcenterC-5, ycenterC-5, 10, 10, 0, 360);      
     g2d.drawString("C", xcenterC-10, ycenterC-10); 

     g2d.fillArc(xcenterD-5, ycenterD-5, 10, 10, 0, 360);      
     g2d.drawString("D", xcenterD-10, ycenterD+10); 
     */
     GeneralPath gp = new GeneralPath();
     /*
     gp.moveTo(xcenterO,ycenterO);     
     gp.lineTo(xcenterA,ycenterA);
     gp.moveTo(xcenterA,ycenterA);
     gp.lineTo(xcenterB,ycenterB);   
     gp.moveTo(xcenterB,ycenterB);
     gp.lineTo(xcenterC,ycenterC);  
     gp.moveTo(xcenterC,ycenterC);
     gp.lineTo(xcenterD,ycenterD); 

     gp.moveTo(xcenterO2-10,ycenterO2);
     gp.lineTo(xcenterO2+10,ycenterO2);

     gp.moveTo(xcenterO3-10,ycenterO3);
     gp.lineTo(xcenterO3+10,ycenterO3);
     */
     g2d.draw(gp);
     /*
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
           gp.moveTo(xcenterA,ycenterA);
           gp.lineTo(xcenterB,ycenterB);                             
        }else{
           if (aroundPoint(startX,startY,xcenterB,ycenterB)){
              done1 = true;                      
              gp.moveTo(xcenterA,ycenterA);
              gp.lineTo(xcenterB,ycenterB);             
           }           
        } 
        if (done2){             
           gp.moveTo(xcenterB,ycenterB);
           gp.lineTo(xcenterC,ycenterC);                             
        }else{
           if (aroundPoint(startX2,startY2,xcenterC,ycenterC)){
              done2 = true;                      
              gp.moveTo(xcenterB,ycenterB);
              gp.lineTo(xcenterC,ycenterC);             
           }           
        } 
        if (done3){             
           gp.moveTo(xcenterC,ycenterC);
           gp.lineTo(xcenterD,ycenterD);
           gp.moveTo(xcenterA,ycenterA);
           gp.lineTo(xcenterD,ycenterD); 
           g2d.fillRect(xcenterA, ycenterA, 4*30, 4*30);                             
        }else{
           if (aroundPoint(startX3,startY3,xcenterD,ycenterD)){
              done3 = true;                      
              gp.moveTo(xcenterC,ycenterC);
              gp.lineTo(xcenterD,ycenterD);
              gp.moveTo(xcenterA,ycenterA);
              gp.lineTo(xcenterD,ycenterD);
              g2d.fillRect(xcenterA, ycenterA, 4*30, 4*30);             
           }           
        }
        if (done4){ 
           g2d.setPaint(Color.white);            
           g2d.fillArc(xcenterE-60, ycenterE-60, 120, 120, 0, 360);                                         
        }else{
           if (aroundPoint2(startX4,startY4,xcenterE,ycenterE,60)){
              done4 = true;   
              g2d.setPaint(Color.white);            
              g2d.fillArc(xcenterE-60, ycenterE-60, 120, 120, 0, 360);         
           }           
        }
        if (done5){ 
           g2d.setPaint(Color.white);            
           g2d.fillArc(xcenterF-60, ycenterF-60, 120, 120, 0, 360);                                         
        }else{
           if (aroundPoint2(startX5,startY5,xcenterF,ycenterF,60)){
              done5 = true;   
              drawCorrect = true;           
              g2d.setPaint(Color.white);            
              g2d.fillArc(xcenterF-60, ycenterF-60, 120, 120, 0, 360);           
           }           
        }                            
     }
     g2d.draw(gp);

     if (clickCount>=0){
        if (done1==false){            
           comment = "�㻹û�л���,����������˳ʱ�뷽���ҵ���AΪ���϶˵�ı߳�Ϊ4��������εĵ�2�����㡣�㻭��" + (clickCount+1) + "��;"; 
           comment2 = "";
        }else{
           if (done2==false){            
              comment = "���Ѿ�����˳ʱ�뷽���ҵ���AΪ���϶˵�ı߳�Ϊ4��������εĵ�2�����㡣�㻭��" + (clickCount+1) + "��;"; 
              comment2 = "���������˳ʱ�뷽���ҵ���AΪ���϶˵�ı߳�Ϊ4��������εĵ�3������";
           }else{
              if (done3==false){            
                 comment = "���Ѿ�����˳ʱ�뷽���ҵ���AΪ���϶˵�ı߳�Ϊ4��������εĵ�3�����㡣�㻭��" + (clickCount+1) + "��;"; 
                 comment2 = "���������˳ʱ�뷽���ҵ���AΪ���϶˵�ı߳�Ϊ4��������εĵ�4������";
              }else{
                 if (done4==false){            
                    comment = "���Ѿ�����˳ʱ�뷽���ҵ���AΪ���϶˵�ı߳�Ϊ4��������εĵ�4�����㡣�㻭��" + (clickCount+1) + "��;"; 
                    comment2 = "���������ҵ�����������ε��ϱ�Ϊֱ����Բ";
                 }else{ 
                    if (done5==false){            
                       comment = "���Ѿ��ҵ�������������ε��ϱ�Ϊֱ����Բ,�㻭��" + (clickCount+1) + "��;"; 
                       comment2 = "���������ҵ�����������ε��±�Ϊֱ����Բ";
                    }else{         
                       comment = "����ȫ������,�㻭��" + (clickCount+1) + "��;"; 
                       comment2 = "������ص�ԭ��ҳ������";
                    }
                 }
              }
           }                  
        }       
     }else{   
        comment = "��ͼ��ͼ�и����˵�A�������������AΪ���϶˵�ı߳�Ϊ4��������Σ�Ȼ�����������Բ���γ���Ӱ���֡�";   
        comment2 = "����������˳ʱ�뷽���ҵ���AΪ���϶˵�ı߳�Ϊ4��������εĵ�2������";     
     }
     g2d.setPaint(Color.red); 
     g2d.drawString(comment, 50, 510);
     g2d.drawString(comment2, 50, 530);

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
     for (int i=60; i<=420; i+=30){
        gp.moveTo(i,80);
        gp.lineTo(i,320);
     }
     for (int i=320; i>=80; i-=30){
        gp.moveTo(60,i);
        gp.lineTo(420,i);
     }
     g2d.draw(gp);
  }

  public void drawRuler(Graphics2D g2d){
     g2d.setPaint(Color.red);
     //g2d.drawArc(xcenterA-10, ycenterA-10, 20, 20, 0, 360);
     //g2d.drawString("A", xcenterA-20, ycenterA+20);
     
     GeneralPath gp = new GeneralPath();     
     String[] strs = new String[]{"","1","2","3","4","5","6","7","8","9","10","11","12"};
     //String[] strs2 = new String[]{"","��","","��","","��","","��","","��",""};
     
     int intV = 0;
     for (int i=0; i<=120; i++){        
        if (i % 10 == 0){
           intV = i/10;           
           //g2d.drawString(strs[intV], 60+i*3, 340);
           //g2d.drawString(strs2[intV], 100+i*4, 540);                      
        } 
     }
     gp.moveTo(60,230);
     gp.lineTo(420,230);
     gp.moveTo(420,230);
     gp.lineTo(410,220);  
     gp.moveTo(420,230);
     gp.lineTo(410,240);   
     //g2d.drawString("", 500, 530);   
     //g2d.drawString("1:20000", 230+60, 550);  
     //g2d.drawString("", 230, 550);    
     g2d.draw(gp);
  }

  public void drawRuler2(Graphics2D g2d){
     g2d.setPaint(Color.red);     
     
     GeneralPath gp = new GeneralPath();
     String[] strs = new String[]{"0","1","2","3","4","5","6","7","8","","","","","","","",""};
     //String str;
     int intV = 0;
     for (int i=0; i<=80; i++){        
        if (i % 10 == 0){
           intV = i/10;
           //str = intV*50 + "";
           //g2d.drawString(strs[intV], 60-30, 320-i*3);           
        }
     }      
     gp.moveTo(240,80);
     gp.lineTo(240,320);
     gp.moveTo(240,80);
     gp.lineTo(230,90);  
     gp.moveTo(240,80);
     gp.lineTo(250,90);  
     //g2d.drawString("20", 80, 100);
     //g2d.drawString("", 180, 40);
     g2d.draw(gp);
  }

  public void drawRatio(Graphics2D g2d){
     g2d.setPaint(Color.red);     
     
     GeneralPath gp = new GeneralPath();
     gp.moveTo(60,380);
     gp.lineTo(90,380);
     gp.moveTo(60,380);
     gp.lineTo(60,375);
     gp.moveTo(90,380);
     gp.lineTo(90,375);
    
     g2d.drawString("500m", 60, 360); 
     g2d.draw(gp); 
  } 

  public void drawDirection(Graphics2D g2d){
     g2d.setPaint(Color.red);     
     
     GeneralPath gp = new GeneralPath();
     gp.moveTo(450,140);
     gp.lineTo(450,110);
     gp.moveTo(450,110);
     gp.lineTo(440,120);
     gp.moveTo(450,110);
     gp.lineTo(460,120);
    
     g2d.drawString("��", 450, 90); 
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

  public boolean aroundPoint2(int startX, int startY, int targetX, int targetY, int radius){
     double dist = Math.sqrt((startX-targetX)*(startX-targetX) + (startY-targetY)*(startY-targetY));
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
