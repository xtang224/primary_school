/**
   @version 1.30 2000-05-12
   @author Cay Horstmann
*/
import java.awt.geom.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class DrawLineApplet109 extends JApplet
{  
   public void init()
   {  
      Container contentPane = getContentPane();
      contentPane.add(new DrawLine109());
     
   }  
}

class DrawLine109 extends JPanel implements MouseListener, ActionListener
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
  private static int ycenterA = 500; // Center position of A
  private static int xcenterB = 300;
  private static int ycenterB = 100; // Center position of B
  private static int xcenterC = 0;
  private static int ycenterC = 400; // Center position of C
  private static int xcenterB2 = 300;
  private static int ycenterB2 = 0; // Center position of B
  private static int xcenterC2 = 0;
  private static int ycenterC2 = 300; // Center position of C   
  private static int xcenterO = 100;
  private static int ycenterO = 300;

  private static String comment = "";
  private static String target = "50"; 
 
  Connection dbCon = null;
   ResultSetMetaData rsmd = null;
   PreparedStatement ptmt = null;
   ResultSet rs = null;

  //Constructor
  DrawLine109()
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
    /*
    if (target.equals("垂线A")){
       xcenterB = 200; ycenterB = 400;
       xcenterC = 500; ycenterC = 400;
       xcenterA = 400; ycenterA = 400;
       xcenterO = 400; ycenterO = 200;
    }else if (target.equals("垂线B")){
       xcenterB = 500; ycenterB = 175;
       xcenterC = 200; ycenterC = 400;
       xcenterA = 400; ycenterA = 270;
       xcenterO = 390; ycenterO = 258;
    }else if (target.equals("垂线C")){
       xcenterB = 600; ycenterB = 100;
       xcenterC = 200; ycenterC = 400;
       xcenterA = 400; ycenterA = 250;
       xcenterO = 280; ycenterO = 90;
    }else if (target.equals("垂线D")){
       xcenterB = 200; ycenterB = 100;
       xcenterC = 600; ycenterC = 400;
       xcenterA = 564; ycenterA = 298;
       xcenterO = 528; ycenterO = 346;
    }else if (target.equals("垂线E")){
       xcenterB = 200; ycenterB = 300;
       xcenterC = 400; ycenterC = 300;
       xcenterA = 300; ycenterA = 100;
       xcenterO = 300; ycenterO = 300;
    }else {//if (target.equals("垂线F"))
       xcenterB = 100; ycenterB = 200;
       xcenterC = 400; ycenterC = 100;
       xcenterA = 200; ycenterA = 100;
       xcenterO = 220; ycenterO = 160;   
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
     startX = e.getX();
     startY = e.getY();
     /*
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
     */
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
  }
  
  public void drawArc(Graphics2D g2d){
     g2d.setPaint(Color.red);
     //g2d.drawRect(xcenterA, ycenterA, 400, 200);
     g2d.fillArc(xcenterA-5, ycenterA-5, 10, 10, 0, 360);
     g2d.drawString("东柳村", xcenterA-20, ycenterA-20);
     g2d.drawString("水南公路", (xcenterB2+xcenterC2)/2, (ycenterB2+ycenterC2)/2+20);
     GeneralPath gp = new GeneralPath();
     gp.moveTo(xcenterB,ycenterB);
     gp.lineTo(xcenterC,ycenterC);
     gp.moveTo(xcenterB2,ycenterB2);
     gp.lineTo(xcenterC2,ycenterC2);
     g2d.draw(gp);    
     
    
     if (clickCount>=0){
        if (drawCorrect==false){
           if (perpendicular(startX, startY, xcenterA, ycenterA, xcenterB, ycenterB, xcenterC, ycenterC)){  
              drawCorrect = true;                 
              comment = "你完全画对了,你画了" + (clickCount+1)/2 + "次;请返回原答题页面点击按钮完成填空，然后提交";  
           }else{          
              comment = "你还没有画对,请继续点击画线：画出过点东柳村垂直于水南公路的垂线,你画了" + (clickCount+1)/2 + "次;"; 
           } 
        }else
           comment = "你完全画对了,你画了" + (clickCount+1)/2 + "次;请返回原答题页面点击按钮完成填空，然后提交";
     }else{  
        comment = "你还没有开始画，请用鼠标点击画线：画出过点东柳村垂直于水南公路的垂线"; 
     }
     g2d.drawString(comment, 50, 530);

     if (clickCount>=0){
        g2d.setPaint(Color.black); 
        if (drawCorrect){
           gp = new GeneralPath();        
           gp.moveTo(xcenterA,ycenterA);
           gp.lineTo(xcenterO,ycenterO);  
           g2d.draw(gp);  
         }       
     }

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

   public boolean inLine(int startX, int startY, int endX, int endY, int pX, int pY){      
       System.out.println("inLine, startX=" + startX + ", startY=" + startY + ", endX=" + endX  + ", endY=" + endY  + ", pX=" + pX  + ", pY=" + pY); 
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
       //Class.forName("org.hsqldb.jdbcDriver");
       //dbCon = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/xdb", "SA", "");
       Class.forName("org.sqlite.JDBC"); 
       dbCon = DriverManager.getConnection("jdbc:sqlite:/home/xtang/Downloads/sqlite_tools/databases/mi3/class_db"); 
       return true;     
    }   
     
}

