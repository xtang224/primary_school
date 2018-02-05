/**
   @version 1.30 2000-05-12
   @author Cay Horstmann
*/
import java.awt.geom.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class DrawLineApplet40 extends JApplet
{  
   public void init()
   {  
      Container contentPane = getContentPane();
      contentPane.add(new DrawLine40());
     
   }  
}

class DrawLine40 extends JPanel implements MouseListener, ActionListener
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
  
  private static int xcenterA = 300;
  private static int ycenterA = 100; // Center position of A
  private static int xcenterB = 50;
  private static int ycenterB = 350; // Center position of B
  private static int xcenterC = 250;
  private static int ycenterC = 300; // Center position of C  
  private static int xcenterD = 0;
  private static int ycenterD = 0; // Center position of D
  private static int xcenterO = 344;
  private static int ycenterO = 276; // Center position of O
  private static int xcenterO2 = 0;
  private static int ycenterO2 = 0;
  private static int xcenterO3 = 0;
  private static int ycenterO3 = 0;
  private static int xcenterO4 = 0;
  private static int ycenterO4 = 0;  

  private static String comment = "";
  private static String comment2 = "";
  private static String target = "50"; 
 
  Connection dbCon = null;
   ResultSetMetaData rsmd = null;
   PreparedStatement ptmt = null;
   ResultSet rs = null;

  //Constructor
  DrawLine40()
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
  
    double xD = 0.833*xcenterC + 0.167*xcenterO;
    double yD = 0.833*ycenterC + 0.167*ycenterO;
    xcenterD = (int)xD;
    ycenterD = (int)yD;

    double xO2 = 0.167*xcenterC + 0.833*xcenterO;
    double yO2 = 0.167*ycenterC + 0.833*ycenterO;
    xcenterO2 = (int)xO2;
    ycenterO2 = (int)yO2;

    double xO3 = 0.5*xcenterC + 0.5*xcenterO;
    double yO3 = 0.5*ycenterC + 0.5*ycenterO;
    xcenterO3 = (int)xO3;
    ycenterO3 = (int)yO3;

    double xO4 = 0.33*xcenterC + 0.67*xcenterO;
    double yO4 = 0.33*ycenterC + 0.67*ycenterO;
    xcenterO4 = (int)xO4;
    ycenterO4 = (int)yO4;

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
    /*
    if (target.equals("给定底边高B")){
       xcenterA = 300;
       ycenterA = 100; 
       xcenterB = 100;
       ycenterB = 100; 
       xcenterC = 150;
       ycenterC = 300; 
       xcenterD = 400;
       ycenterD = 300; 
       xcenterO = 300;
       ycenterO = 300;        
    }else{//if target.equals("给定底边高C")
       xcenterA = 300;
       ycenterA = 300; 
       xcenterB = 100;
       ycenterB = 350; 
       xcenterC = 150;
       ycenterC = 150; 
       xcenterD = 350;
       ycenterD = 100; 
       xcenterO = 256;
       ycenterO = 123;       
    }  
    */  
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
     
     GeneralPath gp = new GeneralPath();
     gp.moveTo(xcenterA,ycenterA);
     gp.lineTo(xcenterB,ycenterB);
     gp.moveTo(xcenterB,ycenterB);
     gp.lineTo(xcenterC,ycenterC);
     gp.moveTo(xcenterC,ycenterC);     
     gp.lineTo(xcenterA,ycenterA);
     g2d.draw(gp);
     
     g2d.fillArc(xcenterA-5, ycenterA-5, 10, 10, 0, 360);      
     g2d.drawString("A", xcenterA+20, ycenterA-20);        
     g2d.drawString("底", (xcenterB+xcenterC)/2-20, (ycenterB+ycenterC)/2+20);     
 
      /* 
     g2d.fillArc(xcenterB-5, ycenterB-5, 10, 10, 0, 360);      
     g2d.drawString("B", xcenterB-20, ycenterB-20);
     g2d.fillArc(xcenterC-5, ycenterC-5, 10, 10, 0, 360);      
     g2d.drawString("C", xcenterC-20, ycenterC+20);
     g2d.fillArc(xcenterD-5, ycenterD-5, 10, 10, 0, 360);      
     g2d.drawString("D", xcenterD+20, ycenterD+20);
       */
     if (clickCount>=0){
        g2d.setPaint(Color.black); 
        gp = new GeneralPath();
        if (done1){           
           gp.moveTo(xcenterA,ycenterA);
           gp.lineTo(xcenterO,ycenterO); 
           gp.moveTo(xcenterC,ycenterC);
           gp.lineTo(xcenterD,ycenterD); 
           gp.moveTo(xcenterO3,ycenterO3);
           gp.lineTo(xcenterO4,ycenterO4); 
           gp.moveTo(xcenterO2,ycenterO2);
           gp.lineTo(xcenterO,ycenterO);           
        }else{
           if (inLine(xcenterB,ycenterB,xcenterC,ycenterC,startX,startY) && perpendicular(startX,startY,xcenterA,ycenterA,xcenterB,ycenterB,xcenterC,ycenterC)){
              done1 = true; 
              drawCorrect = true;              
              gp.moveTo(xcenterA,ycenterA);
              gp.lineTo(xcenterO,ycenterO);
              gp.moveTo(xcenterC,ycenterC);
              gp.lineTo(xcenterD,ycenterD); 
              gp.moveTo(xcenterO3,ycenterO3);
              gp.lineTo(xcenterO4,ycenterO4); 
              gp.moveTo(xcenterO2,ycenterO2);
              gp.lineTo(xcenterO,ycenterO);  
           }           
        }           
        
        g2d.draw(gp);
     }
    
     if (clickCount>=0){
        if (done1==false){  
           comment = "你还没有画对,请点击底边上的一点，作通过顶点A垂直于底边的图形的高,你画了" + (clickCount+1) + "次;"; 
        }else{
           drawCorrect = true;
           comment = "你完全画对了,你画了" + (clickCount+1) + "次;请返回原答题页面点击按钮完成填空，然后提交";                   
        }       
     }else{   
        comment = "你还没有开始画，请用鼠标点击画线：请按提示画通过顶点A垂直于底边的图形的高；";         
     }
     
     g2d.drawString(comment, 50, 530);        

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
        System.out.println("inside perpendicular, targetStartX = " + targetStartX + " and targetStartY = " + targetStartY + " and startY = " + startY + " and endY = " + endY + " and startX = " + startX + " and endX = " + endX);
        if (Math.abs(startY-endY)<=10 && startX!=endX){
           System.out.println("perpendicular is true");
           return true;
        }else{
           System.out.println("perpendicular is not true");
           return false;
        }
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

   public boolean inLine(int startX, int startY, int endX, int endY, int pX, int pY){       
       double d1=0, d2=0;
       if ((endX-startX)==0 && Math.abs(pX-startX)<=5){
          System.out.println("inLine is true part A");
          return true;
       }else if ((endY-startY)==0 && Math.abs(pY-startY)<=5){
          System.out.println("inLine is true part B");
          return true;
       }else if ((endX-startX)!=0 && (pX-startX)!=0){
          d1=((double)(endY-startY))/(endX-startX);
          d2=((double)(pY-startY))/(pX-startX);
          System.out.println("d1=" + d1 + ", d2=" + d2);
          if (Math.abs(d1-d2)<=15){
             System.out.println("inLine is true part C"); 
             return true;
          }
       }          
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

