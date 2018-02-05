/**
   @version 1.30 2000-05-12
   @author Cay Horstmann
*/
import java.awt.geom.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class DrawLineApplet125 extends JApplet
{  
   public void init()
   {  
      Container contentPane = getContentPane();
      contentPane.add(new DrawLine125());
     
   }  
}

class DrawLine125 extends JPanel implements MouseListener, ActionListener
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
  private static int startX7 = 0;
  private static int startY7 = 0;
  private static int startX8 = 0;
  private static int startY8 = 0;

  private boolean done1 = false;
  private boolean done2 = false;
  private boolean done3 = false;
  private boolean done4 = false;
  private boolean done5 = false;
  private boolean done6 = false;
  private boolean done7 = false;
  private boolean done8 = false;
  
  private static int xcenterO = 30;
  private static int ycenterO = 500; // Center position of O

  private static int xcenterA = xcenterO + 60;
  private static int ycenterA = ycenterO - 3*30; // Center position of A
  private static int xcenterB = xcenterA + 60;
  private static int ycenterB = ycenterO - 2*30 - 15; // Center position of B
  private static int xcenterC = xcenterB + 60;
  private static int ycenterC = ycenterO - 4*30; // Center position of C  
  private static int xcenterD = xcenterC + 60;
  private static int ycenterD = ycenterO - 4*30 - 9; // Center position of D
  
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

  private static int xcenterA2 = xcenterO + 60;
  private static int ycenterA2 = ycenterO -30;
  private static int xcenterB2 = xcenterA2 + 60;
  private static int ycenterB2 = ycenterO - 30 - 15;
  private static int xcenterC2 = xcenterB2 + 60;
  private static int ycenterC2 = ycenterO -30 -3;
  private static int xcenterD2 = xcenterC2 + 60;
  private static int ycenterD2 = ycenterO - 3*30;

  private static int xcenterA3 = xcenterA2;
  private static int ycenterA3 = ycenterA2 + 2*30;
  private static int xcenterB3 = xcenterB2;
  private static int ycenterB3 = ycenterB2 + 2*30;
  private static int xcenterC3 = xcenterC2;
  private static int ycenterC3 = ycenterC2 + 2*30;

  private static String comment = "";
  private static String comment2 = "";
  private static String target = "50"; 
 
  Connection dbCon = null;
  ResultSetMetaData rsmd = null;
  PreparedStatement ptmt = null;
  ResultSet rs = null;

  //Constructor
  DrawLine125()
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
    done5= false;
    done6 = false;
    done7= false;
    done8= false;

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
     }else if (done6==false){        
         startX6 = e.getX();
         startY6 = e.getY();       
     }else if (done7==false){        
         startX7 = e.getX();
         startY7 = e.getY();       
     }else if (done8==false){        
         startX8 = e.getX();
         startY8 = e.getY();       
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
    drawDemon(g2d);
    drawRuler(g2d);  
    drawRuler2(g2d);    
  }

  public void drawArc2(Graphics2D g2d){
     g2d.setPaint(Color.red);
     g2d.drawArc(xcenterB, ycenterB, 480, 480, -90, 0); 
  }

  public void drawArc(Graphics2D g2d){
     g2d.setPaint(Color.red);
     //g2d.drawArc(xcenterA, ycenterA, 480, 480, 0, 360); 
     //g2d.drawRect(xcenterA, ycenterA, 400, 200);
     /*
     g2d.fillArc(xcenterA-5, ycenterA-5, 10, 10, 0, 360);      
     g2d.drawString("A", xcenterA, ycenterA-10); 

     g2d.fillArc(xcenterB-5, ycenterB-5, 10, 10, 0, 360);      
     g2d.drawString("B", xcenterB+10, ycenterB+10); 

     g2d.fillArc(xcenterC-5, ycenterC-5, 10, 10, 0, 360);      
     g2d.drawString("C", xcenterC+10, ycenterC+10);

     g2d.fillArc(xcenterA2-5, ycenterA2-5, 10, 10, 0, 360);      
     g2d.drawString("A2", xcenterA2, ycenterA2-10);     
     */
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
           gp.moveTo(xcenterO,ycenterO);           
           gp.lineTo(xcenterA,ycenterA);                                         
        }else{
           if (aroundPoint(startX,startY,xcenterA,ycenterA)){
              done1 = true;                                        
              gp.moveTo(xcenterO,ycenterO);                
              gp.lineTo(xcenterA,ycenterA);                          
           }           
        }  

        if (done2){          
           gp.moveTo(xcenterA,ycenterA);                
           gp.lineTo(xcenterB,ycenterB);                                 
        }else{
           if (aroundPoint(startX2,startY2,xcenterB,ycenterB)){
              done2 = true;                            
              gp.moveTo(xcenterA,ycenterA);                
              gp.lineTo(xcenterB,ycenterB);                                          
           }           
        }  

        if (done3){          
           gp.moveTo(xcenterB,ycenterB);                
           gp.lineTo(xcenterC,ycenterC);                                 
        }else{
           if (aroundPoint(startX3,startY3,xcenterC,ycenterC)){
              done3 = true;             
              gp.moveTo(xcenterB,ycenterB);                
              gp.lineTo(xcenterC,ycenterC);                                          
           }           
        } 

        if (done4){          
           gp.moveTo(xcenterC,ycenterC);                
           gp.lineTo(xcenterD,ycenterD);                                 
        }else{
           if (aroundPoint(startX4,startY4,xcenterD,ycenterD)){
              done4 = true;                             
              gp.moveTo(xcenterC,ycenterC);                
              gp.lineTo(xcenterD,ycenterD);                                          
           }           
        }

        if (done5){             
           dashLine(xcenterO, ycenterO, xcenterA2, ycenterA2, g2d);                                          
        }else{
           if (aroundPoint(startX5,startY5,xcenterA2,ycenterA2)){
              done5 = true;                                        
              dashLine(xcenterO, ycenterO, xcenterA2, ycenterA2, g2d);                         
           }           
        }  

        if (done6){             
           dashLine(xcenterA2, ycenterA2, xcenterB2, ycenterB2, g2d);                                          
        }else{
           if (aroundPoint(startX6,startY6,xcenterB2,ycenterB2)){
              done6 = true;                                        
              dashLine(xcenterA2, ycenterA2, xcenterB2, ycenterB2, g2d);                         
           }           
        } 

        if (done7){             
           dashLine(xcenterB2, ycenterB2, xcenterC2, ycenterC2, g2d);                                            
        }else{
           if (aroundPoint(startX7,startY7,xcenterC2,ycenterC2)){
              done7 = true;                                        
              dashLine(xcenterB2, ycenterB2, xcenterC2, ycenterC2, g2d);                         
           }           
        } 

        if (done8){             
           dashLine(xcenterC2, ycenterC2, xcenterD2, ycenterD2, g2d);                                            
        }else{
           if (aroundPoint(startX8,startY8,xcenterD2,ycenterD2)){
              done8 = true;
              drawCorrect = true;                                         
              dashLine(xcenterC2, ycenterC2, xcenterD2, ycenterD2, g2d);                         
           }           
        }                            
     }
     g2d.draw(gp);

     if (clickCount>=0){
        if (done1==false){            
           comment = "���ҵ�һ���ȵ��ӻ�����1500̨�ĵ�,�㻭��" + (clickCount+1) + "��;"; 
           comment2 = "";
        }else{
           if (done2==false){
              comment = "���Ѿ��ҵ���һ���ȵ��ӻ�����1500̨������Ӧ�ĵ�";
              comment2 = "������Ҷ����ȵ��ӻ�����1250̨������Ӧ�ĵ�,�㻭��" + (clickCount+1) + "��;";
           }else{
              if (done3==false){
                 comment = "���Ѿ��ҵ���һ�������ȵ��ӻ���������Ӧ�ĵ�";
                 comment2 = "������������ȵ��ӻ�����2000̨������Ӧ�ĵ�,�㻭��" + (clickCount+1) + "��;";
              }else{
                 if (done4==false){
                    comment = "���Ѿ��ҵ���һ�����������ȵ��ӻ���������Ӧ�ĵ�";
                    comment2 = "��������ļ��ȵ��ӻ�����2300̨������Ӧ�ĵ�,�㻭��" + (clickCount+1) + "��;";
                 }else{
                    if (done5==false){
                       comment = "���Ѿ��ҵ���һ�����������ļ��ȵ��ӻ���������Ӧ�ĵ�";
                       comment2 = "�������һ�����������500̨������Ӧ�ĵ�,�㻭��" + (clickCount+1) + "��;";
                    }else{
                       if (done6==false){
                          comment = "���Ѿ��ҵ���һ�����������ļ��ȵ��ӻ����ۺ�һ���������������Ӧ�ĵ�";
                          comment2 = "������������������750̨������Ӧ�ĵ�,�㻭��" + (clickCount+1) + "��;";
                       }else{
                          if (done7==false){
                             comment = "���Ѿ��ҵ���һ�����������ļ��ȵ��ӻ����ۺ�һ�������������������Ӧ�ĵ�";
                             comment2 = "������������������600̨������Ӧ�ĵ�,�㻭��" + (clickCount+1) + "��;";
                          }else{
                             if (done8==false){
                                comment = "���Ѿ��ҵ���һ�����������ļ��ȵ��ӻ����ۺ�һ�����������������������Ӧ�ĵ�";
                                comment2 = "������ļ����������1500̨������Ӧ�ĵ�,�㻭��" + (clickCount+1) + "��;";
                             }else{
                                comment = "����ȫ������,�㻭��" + (clickCount+1) + "��;�뷵��ԭ����ҳ������ťֱ���ύ"; 
                                comment2 = "";
                             } 
                          }
                       }
                    }
                 }
              } 
           }
        }        
     }else{   
        comment = "ͼ�и���������ϵ���뻭�����ӻ������������ͼ��";   
        comment2 = "���ҵ�һ���ȵ��ӻ�����1500̨�ĵ�";     
     }   

     g2d.drawString(comment, 50, 540);
     g2d.drawString(comment2, 50, 560);

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

  public void drawDemon(Graphics2D g2d){
     g2d.setPaint(Color.red);
     GeneralPath gp = new GeneralPath();
     gp.moveTo(100, 40);
     gp.lineTo(160, 40);
     g2d.drawString("���ӻ�", 160, 40);  
     dashLine(100,60,160,60,g2d);
     g2d.drawString("����", 160, 60); 
     g2d.draw(gp);
  }

  public void dashLine(int startX, int startY, int endX, int endY,  Graphics2D g2d){
     GeneralPath gp = new GeneralPath();
     gp.moveTo((5*startX+endX)/6, (5*startY+endY)/6);
     gp.lineTo((4*startX+2*endX)/6, (4*startY+2*endY)/6);
     gp.moveTo((2*startX+4*endX)/6, (2*startY+4*endY)/6);
     gp.lineTo((1*startX+5*endX)/6, (1*startY+5*endY)/6);
     g2d.draw(gp);
  }

  public void drawGrid(Graphics2D g2d){
     g2d.setPaint(Color.yellow);
     GeneralPath gp = new GeneralPath();
     for (int i=30; i<=510; i+=60){
        gp.moveTo(i,80);
        gp.lineTo(i,500);
     }
     for (int i=500; i>=80; i-=30){
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
     String[] strs = new String[]{"","һ����","������","������","�ļ���","","","","","",""};
     //String[] strs2 = new String[]{"","��","","��","","��","","��","","��",""};
     
     int intV = 0;
     for (int i=0; i<=50; i++){        
        if (i % 10 == 0){
           intV = i/10;           
           g2d.drawString(strs[intV], 30+i*6, 520);
           //g2d.drawString(strs2[intV], 100+i*4, 540);                      
        } 
     }
     gp.moveTo(30,500);
     gp.lineTo(510,500);
     gp.moveTo(510,500);
     gp.lineTo(500,490);  
     gp.moveTo(510,500);
     gp.lineTo(500,510);   
     g2d.drawString("", 500, 500);   
     //g2d.drawString("1:20000", 230+60, 550);  
     g2d.drawString("", 230, 530);    
     g2d.draw(gp);
  }

  public void drawRuler2(Graphics2D g2d){
     g2d.setPaint(Color.red);     
     
     GeneralPath gp = new GeneralPath();
     String[] strs = new String[]{"0","500","1000","1500","2000","2500","","","","","","","","","","",""};
     //String str;
     int intV = 0;
     for (int i=0; i<=100; i++){        
        if (i % 10 == 0){
           intV = i/10;
           //str = intV*50 + "";
           g2d.drawString(strs[intV], 30-30, 500-i*3);           
        }
     }      
     gp.moveTo(30,500);
     gp.lineTo(30,20);
     gp.moveTo(30,20);
     gp.lineTo(20,30);  
     gp.moveTo(30,20);
     gp.lineTo(40,30);  
     //g2d.drawString("20", 80, 100);
     g2d.drawString("����/̨", 40, 40);
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
