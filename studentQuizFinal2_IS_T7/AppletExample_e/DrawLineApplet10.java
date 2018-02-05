/**
   @version 1.30 2000-05-12
   @author Cay Horstmann
*/
import java.awt.geom.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class DrawLineApplet10 extends JApplet{  
   public void init()
   {  
      Container contentPane = getContentPane();
      contentPane.add(new DrawLine10());     
   }  
}

class DrawLine10 extends JPanel implements MouseListener, ActionListener
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

  private static int xcenterA = 400;
  private static int ycenterA = 54; // Center position of A
  private static int xcenterB = 200;
  private static int ycenterB = 400; // Center position of B
  private static int xcenterC = 600;
  private static int ycenterC = 400; // Center position of C

  private static String comment = "";
  private static String target = "50"; 
 
  Connection dbCon = null;
   ResultSetMetaData rsmd = null;
   PreparedStatement ptmt = null;
   ResultSet rs = null;

  //Constructor
  DrawLine10()
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
     g2d.fillArc(xcenterB-5, ycenterB-5, 10, 10, 0, 360); 
     g2d.fillArc(xcenterC-5, ycenterC-5, 10, 10, 0, 360); 
     g2d.drawString("A", xcenterA-20, ycenterA-20);
     g2d.drawString("B", xcenterB-20, ycenterB+20);
     g2d.drawString("C", xcenterC+20, ycenterC+20);     
     
     if (clickCount>=0){
        g2d.setPaint(Color.black); 
        gp = new GeneralPath();
        if (done1){           
           gp.moveTo(xcenterA,ycenterA);
           gp.lineTo(xcenterA,400);           
        }else{
           if (aroundPoint(startX, startY, xcenterA,ycenterA)){
              done1 = true;
              gp.moveTo(xcenterA,ycenterA);
              gp.lineTo(xcenterA,400);
           }           
        }

        if (done2){           
           gp.moveTo(xcenterB,ycenterB);
           gp.lineTo((xcenterA+xcenterC)/2,(ycenterA+ycenterC)/2);          
        }else{           
           if (aroundPoint(startX2, startY2, xcenterB,ycenterB)){
              done2 = true;
              gp.moveTo(xcenterB,ycenterB);
              gp.lineTo((xcenterA+xcenterC)/2,(ycenterA+ycenterC)/2);   
           }           
        }

        if (done3){           
           gp.moveTo(xcenterC,ycenterC);
           gp.lineTo((xcenterA+xcenterB)/2,(ycenterA+ycenterB)/2);           
        }else{           
           if (aroundPoint(startX3, startY3, xcenterC,ycenterC)){
              done3 = true;
              gp.moveTo(xcenterC,ycenterC);
              gp.lineTo((xcenterA+xcenterB)/2,(ycenterA+ycenterB)/2);  
           }           
        }  
        g2d.draw(gp);
     }
    
     if (clickCount>=0){
        if (done1==false){           
           comment = "你还没有画对,请继续点击点A画线,你画了" + (clickCount+1) + "次;"; 
        }else{
           if (done2==false){
              comment = "你画对了一条线,请继续点击点B画线,你画了" + (clickCount+1) + "次;"; 
           }else{
              if (done3==false){
                 comment = "你画对了两条线,请继续点击点C画线,你画了" + (clickCount+1) + "次;";
              }else{
                 drawCorrect = true;
                 comment = "你完全画对了,你画了" + (clickCount+1) + "次;请返回原答题页面点击按钮完成填空，然后提交"; 
              }
           }
        }       
     }else{         
        comment = "你还没有开始画，请用鼠标点击画线：请按提示依次点击点A，B，C，从而画出过这些顶点这个三角形对边的垂线"; 
     }
     g2d.drawString(comment, xcenterB-150, ycenterB+50);

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
       Class.forName("org.hsqldb.jdbcDriver");
       dbCon = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/xdb", "SA", "");
       return true;     
    }   
     
}

