/**
   @version 1.30 2000-05-12
   @author Cay Horstmann
*/
import java.awt.geom.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class DrawLineApplet42 extends JApplet
{  
   public void init()
   {  
      Container contentPane = getContentPane();
      contentPane.add(new DrawLine42());
     
   }  
}

class DrawLine42 extends JPanel implements MouseListener, ActionListener
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

  private boolean done1 = false;
  private boolean done2 = false;
  private boolean done3 = false;
  
  private static int xcenterP1 = 10;
  private static int ycenterP1 = 100; 
  private static int xcenterP2 = 510;
  private static int ycenterP2 = 100;
  private static int xcenterP3 = 10;
  private static int ycenterP3 = 300; 
  private static int xcenterP4 = 510;
  private static int ycenterP4 = 300; 

  private static int xcenterA = 100;
  private static int ycenterA = 100; // Center position of A
  private static int xcenterB = 100;
  private static int ycenterB = 300; // Center position of B
  private static int xcenterC = 100+40;
  private static int ycenterC = 500-40-80; // Center position of C  
  private static int xcenterD = 100+160+40;
  private static int ycenterD = 500-40-80; // Center position of D
  private static int xcenterO = 100;
  private static int ycenterO = 500; // Center position of O

  private static String comment = "";
  private static String comment2 = "";    
  private static String target = "50"; 
 
  Connection dbCon = null;
   ResultSetMetaData rsmd = null;
   PreparedStatement ptmt = null;
   ResultSet rs = null;

  //Constructor
  DrawLine42()
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
    
    if (target.equals("ƽ���߳�����")){
       //do nothing
    }else {// if (target.equals("ƽ��������"))
       //do nothing 
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

    //Use of antialiasing to have nicer lines.
    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);

    //The lines should have a thickness of 3.0 instead of 1.0.
    BasicStroke bs = new BasicStroke(3.0f);
    g2d.setStroke(bs);

    drawArc(g2d);
    //drawRuler(g2d);  
    //drawRuler2(g2d);  
  }
  
  public void drawArc(Graphics2D g2d){
     g2d.setPaint(Color.red);
     //g2d.drawRect(xcenterA, ycenterA, 400, 200);
     
     g2d.fillArc(xcenterA-5, ycenterA-5, 10, 10, 0, 360);      
     g2d.drawString("A", xcenterA+20, ycenterA-20);     
     GeneralPath gp = new GeneralPath();
     gp.moveTo(xcenterP1,ycenterP1);
     gp.lineTo(xcenterP2,ycenterP2);     
     g2d.drawString("ֱ��a", xcenterP1+20, ycenterP1-20);     
     gp.moveTo(xcenterP3,ycenterP3);
     gp.lineTo(xcenterP4,ycenterP4);
     g2d.drawString("ֱ��b", xcenterP3+20, ycenterP3-20);     
     g2d.draw(gp);

     if (clickCount>=0){
        g2d.setPaint(Color.black); 
        gp = new GeneralPath();
        if (done1){           
           gp.moveTo(xcenterA,ycenterA);
           gp.lineTo(xcenterB,ycenterB);      
        }else{
           if (target.equals("ƽ���߳�����") && aroundPoint(startX, startY, xcenterB,ycenterB)){
              done1 = true;                  
              gp.moveTo(xcenterA,ycenterA);
              gp.lineTo(xcenterB,ycenterB);      
           }else if (target.equals("ƽ��������") && Math.abs(startY-ycenterP3)<=30 && Math.abs(startX-xcenterA)>30){
              done1 = true;
              xcenterB = startX;
              gp.moveTo(xcenterA,ycenterA);
              gp.lineTo(xcenterB,ycenterB);
           }           
        }

        if (done2){           
           gp.moveTo(xcenterA,ycenterA);
           gp.lineTo(xcenterD,ycenterD);      
        }else{
           if (target.equals("ƽ���߳�����") && Math.abs(startY2-ycenterA) <= 30 && !aroundPoint(startX2, startY2, xcenterA,ycenterA)){
              done2 = true;    
              xcenterD = startX2;
              ycenterD = ycenterA;
              xcenterC = xcenterD;
              ycenterC = ycenterB;
              gp.moveTo(xcenterA,ycenterA);
              gp.lineTo(xcenterD,ycenterD);      
          }else if (target.equals("ƽ��������") && Math.abs(startY2-ycenterA) <= 30 && Math.abs(startX2-xcenterA)>30 && Math.abs(startX2-xcenterB)>30){
              done2 = true;    
              xcenterD = startX2;
              ycenterD = ycenterA;
              xcenterO = xcenterB + (xcenterD-xcenterA);
              ycenterO = ycenterB;
              gp.moveTo(xcenterA,ycenterA);
              gp.lineTo(xcenterD,ycenterD);      
           }
        } 
               
        if (done3){           
           gp.moveTo(xcenterC,ycenterC);
           gp.lineTo(xcenterD,ycenterD);      
           gp.moveTo(xcenterC,ycenterC);
           gp.lineTo(xcenterB,ycenterB);
        }else{
           if (target.equals("ƽ���߳�����") && aroundPoint(startX3, startY3, xcenterC,ycenterC)){
              done3 = true;    
              drawCorrect = true;       
              gp.moveTo(xcenterC,ycenterC);
              gp.lineTo(xcenterD,ycenterD);      
              gp.moveTo(xcenterC,ycenterC);
              gp.lineTo(xcenterB,ycenterB);                 
           }else if (target.equals("ƽ��������") && Math.abs(startY3-ycenterB)<=30 && !aroundPoint(startX3, startY3, xcenterO,ycenterO)){
              done3 = true;    
              drawCorrect = true;       
              xcenterC = startX3;
              ycenterC = ycenterB;
              gp.moveTo(xcenterC,ycenterC);
              gp.lineTo(xcenterD,ycenterD);      
              gp.moveTo(xcenterC,ycenterC);
              gp.lineTo(xcenterB,ycenterB);                 
           }
        } 
     }
     g2d.draw(gp);

     if (clickCount>=0){
        if (done1==false){  
           if (target.equals("ƽ���߳�����"))
              comment = "�㻹û�л���,��������еĶ���A������������ֱ��b�ϵ�����һ������,�㻭��" + (clickCount+1) + "��;";            
           else // if (target.equals("ƽ��������"))
              comment = "�㻹û�л���,��������еĶ���A����������ֱ��b�ϵ�����һ������,�㻭��" + (clickCount+1) + "��;"; 
        }else{
           if (done2 == false){
              if (target.equals("ƽ���߳�����"))
                 comment = "���Ѿ��ҵ��˳�������ֱ��b�ϵ�����һ������,������ҳ�������ֱ��a�ϵ�����һ������,�㻭��" + (clickCount+1) + "��;";
             else // if (target.equals("ƽ��������"))
                 comment = "���Ѿ��ҵ���������ֱ��b�ϵ�����һ������,�������������ֱ��a�ϵ�����һ������,�㻭��" + (clickCount+1) + "��;";
           }else{
              if (done3 == false){
                 if (target.equals("ƽ���߳�����"))
                    comment = "���Ѿ��ҵ��˳����ε���������,������ҳ�������ֱ��b�ϵĵ�4������,�㻭��" + (clickCount+1) + "��;";
                 else // if (target.equals("ƽ��������"))
                    comment = "���Ѿ��ҵ��˳����ε���������,������ҳ�������ֱ��b�ϵĵ�4������,�㻭��" + (clickCount+1) + "��;";            
              }else{
                 drawCorrect = true;
                 comment = "����ȫ������,�㻭��" + (clickCount+1) + "��;�뷵��ԭ����ҳ������ť�����գ�Ȼ���ύ";                 
              }
           } 
        }       
     }else{   
        if (target.equals("ƽ���߳�����")){      
           comment = "�㻹û�п�ʼ������������еĳ����εĶ���A��������ֱ��a��b�ϵ�����3�����㣬������������"; 
           comment2 = "���Ȼ������͵�Aͬ�ߵĳ�������ֱ��b�ϵĶ���";
        }else { //if (target.equals("ƽ��������"))
           comment = "�㻹û�п�ʼ������������е����εĶ���A��������ֱ��a��b�ϵ�����3�����㣬����������"; 
           comment2 = "���Ȼ������͵�Aͬ�ߵ�������ֱ��b�ϵĶ���";
        }
     }
     g2d.drawString(comment, 10, 500);
     if (clickCount<0)
        g2d.drawString(comment, 10, 520);

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

  public void drawRuler(Graphics2D g2d){
     g2d.setPaint(Color.red);
     //g2d.drawArc(xcenterA-10, ycenterA-10, 20, 20, 0, 360);
     //g2d.drawString("A", xcenterA-20, ycenterA+20);
     
     GeneralPath gp = new GeneralPath();
     String str = "";
     int intV = 0;
     for (int i=0; i<100; i++){        
        gp.moveTo(100+i*4,500);
        if (i % 10 == 0){
           gp.lineTo(100+i*4,500-20);
           intV = i/10;
           str = intV + "";
           g2d.drawString(str, 100+i*4, 520);
        }else
           gp.lineTo(100+i*4,500-10);  
     }
     gp.moveTo(500,500);
     gp.lineTo(500,480);  
     gp.moveTo(100,500);
     gp.lineTo(500,500);  
     g2d.drawString("10����", 500, 520);
     g2d.draw(gp);
  }

  public void drawRuler2(Graphics2D g2d){
     g2d.setPaint(Color.red);     
     
     GeneralPath gp = new GeneralPath();
     String str = "";
     int intV = 0;
     for (int i=0; i<100; i++){        
        gp.moveTo(100,500-i*4);
        if (i % 10 == 0){
           gp.lineTo(100+20,500-i*4);
           intV = i/10;
           str = intV + "";
           g2d.drawString(str, 100-20, 500-i*4);
        }else
           gp.lineTo(100+10,500-i*4);  
     }
     gp.moveTo(100,100);
     gp.lineTo(120,100);  
     gp.moveTo(100,500);
     gp.lineTo(100,100);  
     g2d.drawString("10����", 80, 100);
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
     if (Math.abs(startX-targetX)<=30 && Math.abs(startY-targetY)<=30)
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
       Class.forName("org.hsqldb.jdbcDriver");
       dbCon = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/xdb", "SA", "");
       return true;     
    }   
     
}
