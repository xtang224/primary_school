/**
   @version 1.30 2000-05-12
   @author Cay Horstmann
*/
import java.awt.geom.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class DrawLineApplet4 extends JApplet{  
   public void init()
   {  
      Container contentPane = getContentPane();
      contentPane.add(new DrawLine4());     
   }  
}

class DrawLine4 extends JPanel implements MouseListener, ActionListener
{
  private JButton buttonStart;
  private JButton buttonEnd;
  private boolean canDrawStart = false;
  private boolean canDrawEnd = true;
  private static boolean firstClick = false;
  private static boolean drawCorrect = false;
  private static int clickCount = -1;
  private static int startX = 270;
  private static int startY = 305;
  private static int endX = 480; 
  private static int endY = 305;

  private static int startX2 = 270;
  private static int startY2 = 305;
  private static int endX2 = 480; 
  private static int endY2 = 305;

  private static int xcenterA = 270;
  private static int ycenterA = 305; // Center position of A
  private static int xcenterB = 480;
  private static int ycenterB = 305; // Center position of B

  private static String comment = "";
  private static String target = "50"; 
 
  Connection dbCon = null;
   ResultSetMetaData rsmd = null;
   PreparedStatement ptmt = null;
   ResultSet rs = null;

  //Constructor
  DrawLine4()
  {
    //Enables the closing of the window.
    //addWindowListener(new MyFinishWindow());
   
    addMouseListener(this);
    clickCount = -1;
    firstClick = false;
    drawCorrect = false;

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
     if (clickCount % 4 == 0){
        startX = e.getX();
        startY = e.getY(); 
     }else if (clickCount % 4 == 1){
        endX = e.getX();
        endY = e.getY(); 
     }else if (clickCount % 4 == 2){
        startX2 = e.getX();
        startY2 = e.getY(); 
     }else if (clickCount % 4 == 3){
        endX2 = e.getX();
        endY2 = e.getY(); 
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
  }
  
  public void drawArc(Graphics2D g2d){
     g2d.setPaint(Color.red);
     g2d.fillArc(xcenterA-5, ycenterA-5, 10, 10, 0, 360);
     g2d.drawString("A", xcenterA-20, ycenterA-20);
     //g2d.drawArc(xcenterB-1, ycenterB-1, 2, 2, 0, 360);    
     
     if (clickCount>=1){
        g2d.setPaint(Color.black); 
        GeneralPath gp = new GeneralPath();
        gp.moveTo(startX,startY);
        gp.lineTo(endX,endY);
        if (clickCount>=3){
           gp.moveTo(startX2,startY2);
           gp.lineTo(endX2,endY2);
        }
        g2d.draw(gp);
     }
    
     if (clickCount>=3){
        if (inLine(startX, startY, endX, endY, xcenterA, ycenterA) && inLine(startX2, startY2, endX2, endY2, xcenterA, ycenterA)){
           comment = "你画对了,你画了" + (clickCount+1)/4 + "次;请返回原答题页面点击按钮完成填空，然后提交"; 
           drawCorrect = true;
        }else     
           comment = "你画错了，你画了" + (clickCount+1)/4 + "次"; 
        
     }else{
        comment = "你还没有开始画，请用鼠标点击画线"; 
     }
     g2d.drawString(comment, xcenterA-250, ycenterA+50);

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
          if (Math.abs(d1-d2)<=0.25)
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

