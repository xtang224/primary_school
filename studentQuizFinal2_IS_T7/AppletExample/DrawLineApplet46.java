/**
   @version 1.30 2000-05-12
   @author Cay Horstmann
*/
import java.awt.geom.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class DrawLineApplet46 extends JApplet
{  
   public void init()
   {  
      Container contentPane = getContentPane();
      contentPane.add(new DrawLine46());
     
   }  
}

class DrawLine46 extends JPanel implements MouseListener, ActionListener
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

  private static int startX2 = 1000;
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
  
  private static int xcenterP1 = 50;
  private static int ycenterP1 = 100; 
  private static int xcenterP2 = 150;
  private static int ycenterP2 = 300;
  private static int xcenterP3 = 150;
  private static int ycenterP3 = 100; 
  private static int xcenterP4 = 50;
  private static int ycenterP4 = 300; 

  private static int xcenterA = 100;
  private static int ycenterA = 100; // Center position of A
  private static int xcenterB = 50;
  private static int ycenterB = 300; // Center position of B
  private static int xcenterC = 250;
  private static int ycenterC = 300; // Center position of C  
  private static int xcenterD = 300;
  private static int ycenterD = 100; // Center position of D
  private static int xcenterO = 200;
  private static int ycenterO = 200; // Center position of O
  private static int xcenterO2 = 0;
  private static int ycenterO2 = 100; // Center position of O2
  private static int xcenterON = 0;
  private static int ycenterON = 100; // Center position of ON
  private static int xcenterO3 = 0;
  private static int ycenterO3 = 0; // Center position of O3
  private static int xcenterO4 = 0;
  private static int ycenterO4 = 0; // Center position of O4
  private static int xcenterO5 = 0;
  private static int ycenterO5 = 0; // Center position of O5
  private static int xcenterO6 = 0;
  private static int ycenterO6 = 0; // Center position of O6   
  
  private static String comment = "";
  private static String comment2 = "";    
  private static String target = "50"; 
 
  Connection dbCon = null;
   ResultSetMetaData rsmd = null;
   PreparedStatement ptmt = null;
   ResultSet rs = null;

  //Constructor
  DrawLine46()
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

    if (target.equals("剪一个梯形")){
       xcenterA = 100;
       ycenterA = 100;
       xcenterB = 50;
       ycenterB = 300;
       xcenterC = 250;
       ycenterC = 300; 
       xcenterD = 300;
       ycenterD = 100; 
    }else{//if (target.equals("剪两个相同梯形"))
       xcenterA = 100;
       ycenterA = 100;
       xcenterB = 100;
       ycenterB = 200;
       xcenterC = 300;
       ycenterC = 200; 
       xcenterD = 300;
       ycenterD = 100;
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
     /*
     g2d.fillArc(xcenterB-5, ycenterB-5, 10, 10, 0, 360);      
     g2d.drawString("B", xcenterB+20, ycenterB+20); 
     g2d.fillArc(xcenterC-5, ycenterC-5, 10, 10, 0, 360);      
     g2d.drawString("C", xcenterC+20, ycenterC+20); 
      */ 
     GeneralPath gp = new GeneralPath();
     gp.moveTo(xcenterA,ycenterA);
     gp.lineTo(xcenterB,ycenterB);
     gp.moveTo(xcenterB,ycenterB);
     gp.lineTo(xcenterC,ycenterC);  
     gp.moveTo(xcenterC,ycenterC);
     gp.lineTo(xcenterD,ycenterD); 
     gp.moveTo(xcenterD,ycenterD);
     gp.lineTo(xcenterA,ycenterA);
     
     g2d.draw(gp);

     if (clickCount>=0){
        g2d.setPaint(Color.black); 
        gp = new GeneralPath();
        if (done1){  
           g2d.fillArc(xcenterO-5, ycenterO-5, 10, 10, 0, 360);         
           g2d.drawString("O", xcenterO+20, ycenterO-20);      
        }else{
           if (target.equals("剪一个梯形") && Math.abs(startY-ycenterA)<=30 && startX>=xcenterA && startX<=xcenterD){
              done1 = true;              
              xcenterO = startX;
              ycenterO = ycenterA;
              xcenterON = xcenterB + (xcenterO-xcenterA);
              ycenterON = ycenterB;
              g2d.fillArc(xcenterO-5, ycenterO-5, 10, 10, 0, 360);   
              g2d.drawString("O", xcenterO+20, ycenterO-20);
           }else if  (target.equals("剪两个相同梯形") && Math.abs(startY-ycenterA)<=30 && startX>=xcenterA && startX<=xcenterD){
              done1 = true;              
              xcenterO = startX;
              ycenterO = ycenterA;
              xcenterO2 = xcenterC- (xcenterO-xcenterA);
              ycenterO2 = ycenterB;
              g2d.fillArc(xcenterO-5, ycenterO-5, 10, 10, 0, 360);
              g2d.drawString("O", xcenterO+20, ycenterO-20);
           }          
        }

        if (done2){           
           gp.moveTo(xcenterO,ycenterO);
           gp.lineTo(xcenterO3,ycenterO3); 
           gp.moveTo(xcenterO4,ycenterO4);
           gp.lineTo(xcenterO5,ycenterO5); 
           gp.moveTo(xcenterO6,ycenterO6);
           gp.lineTo(xcenterO2,ycenterO2);        
        }else{
           if (target.equals("剪一个梯形") && Math.abs(startY2-ycenterB)<=30 && startX2>=xcenterB && startX2<=xcenterC && Math.abs(startX2-xcenterON)>30){
              done2 = true;
              drawCorrect = true; 
              xcenterO2= startX2;
              ycenterO2 = ycenterB;
              xcenterO3 = (int)(0.83 * xcenterO + 0.17 * xcenterO2);
              ycenterO3 = (int)(0.83 * ycenterO + 0.17 * ycenterO2); 
              xcenterO4 = (int)(0.5 * xcenterO + 0.5 * xcenterO2);
              ycenterO4 = (int)(0.5 * ycenterO + 0.5 * ycenterO2); 
              xcenterO5 = (int)(0.33 * xcenterO + 0.67 * xcenterO2);
              ycenterO5 = (int)(0.33 * ycenterO + 0.67 * ycenterO2);    
              xcenterO6 = (int)(0.17 * xcenterO + 0.83 * xcenterO2);
              ycenterO6 = (int)(0.17 * ycenterO + 0.83 * ycenterO2);   
              gp.moveTo(xcenterO,ycenterO);
              gp.lineTo(xcenterO3,ycenterO3); 
              gp.moveTo(xcenterO4,ycenterO4);
              gp.lineTo(xcenterO5,ycenterO5); 
              gp.moveTo(xcenterO6,ycenterO6);
              gp.lineTo(xcenterO2,ycenterO2);       
           }else if (target.equals("剪两个相同梯形") && aroundPoint(startX2,startY2,xcenterO2,ycenterO2)){
              done2 = true;
              drawCorrect = true; 
              xcenterO3 = (int)(0.83 * xcenterO + 0.17 * xcenterO2);
              ycenterO3 = (int)(0.83 * ycenterO + 0.17 * ycenterO2); 
              xcenterO4 = (int)(0.5 * xcenterO + 0.5 * xcenterO2);
              ycenterO4 = (int)(0.5 * ycenterO + 0.5 * ycenterO2); 
              xcenterO5 = (int)(0.33 * xcenterO + 0.67 * xcenterO2);
              ycenterO5 = (int)(0.33 * ycenterO + 0.67 * ycenterO2);    
              xcenterO6 = (int)(0.17 * xcenterO + 0.83 * xcenterO2);
              ycenterO6 = (int)(0.17 * ycenterO + 0.83 * ycenterO2);   
              gp.moveTo(xcenterO,ycenterO);
              gp.lineTo(xcenterO3,ycenterO3); 
              gp.moveTo(xcenterO4,ycenterO4);
              gp.lineTo(xcenterO5,ycenterO5); 
              gp.moveTo(xcenterO6,ycenterO6);
              gp.lineTo(xcenterO2,ycenterO2);    
           }
          
        }

     }
     g2d.draw(gp);

     if (clickCount>=0){
        if (done1==false){  
           comment = "你还没有画对,请先在过点A的横边上找一点,你画了" + (clickCount+1) + "次;"; 
           comment2 = "";
        }else{
           if (done2==false){
              if (target.equals("剪一个梯形")){
                 comment = "你已经在过点A的横边上找了一点，请继续在A的对边上找一点并连虚线。你画了" + (clickCount+1) + "次;";  
                 comment2 = "这条虚线连线会将这个平行四边形分成至少有一个梯形。";
              }else{
                 comment = "你已经在过点A的横边上找了一点，请继续在A的对边上找一点并连虚线。你画了" + (clickCount+1) + "次;";  
                 comment2 = "这条虚线连线会将这个长方形分成两个完全相同的梯形。";
              }
           }else{
              drawCorrect = true;
              comment = "你完全画对了,你画了" + (clickCount+1) + "次;请返回原答题页面点击按钮完成填空，然后提交"; 
              comment2 = ""; 
           }        
        }       
     }else{      
        comment = "你还没有开始画：请先在过点A的横边上找一点";
        if (target.equals("剪一个梯形")) 
           comment2 = "再过这点和对边连线将这个平行四边形分出至少有一个梯形";  
        else  
           comment2 = "再过这点和对边连线将这个平行四边形分成两个完全相同的梯形";    
     }
     g2d.drawString(comment, 10, 500);
     //if (clickCount<0)
     g2d.drawString(comment2, 10, 520);

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
     g2d.drawString("10厘米", 500, 520);
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
     g2d.drawString("10厘米", 80, 100);
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

   public boolean parallel(int startX, int startY, int endX, int endY, int targetStartX, int targetStartY, int targetEndX, int targetEndY){
     if (targetStartX==targetEndX && targetStartY!=targetEndY){
        System.out.println("inside parallel, targetStartX = " + targetStartX + " and targetStartY = " + targetStartY + " and startY = " + startY + " and endY = " + endY + " and startX = " + startX + " and endX = " + endX);
        if (Math.abs(startX-endX)<=10 && startY!=endY){
           System.out.println("parallel is true");
           return true;
        }else{
           System.out.println("parallel is not true");
           return false;
        }
     }else if (targetStartY==targetEndY && targetStartX!=targetEndX){
        if (Math.abs(startY-endY)<=10 && startX!=endX)
           return true;
        else
           return false;
     }else{
        double tangent1 = ((double)(targetEndX - targetStartX)) / (-(targetEndY - targetStartY));
        double tangent2 = ((double)(endX - startX)) / (-(endY - startY));
        if (Math.abs(tangent1 - tangent2)<=1.0)
           return true;
        else 
           return false;
     }
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

