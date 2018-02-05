/**
   @version 1.30 2000-05-12
   @author Cay Horstmann
*/
import java.awt.geom.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class DrawLineApplet6 extends JApplet{  
   public void init()
   {  
      Container contentPane = getContentPane();
      contentPane.add(new DrawLine6());     
   }  
}

class DrawLine6 extends JPanel implements MouseListener, ActionListener
{
  private JButton buttonStart;
  private JButton buttonEnd;
  private boolean canDrawStart = false;
  private boolean canDrawEnd = true;
  private static boolean firstClick = false;
  private static boolean drawCorrect = false;
  private static int clickCount = 0;
  private static int startX = 270;
  private static int startY = 305;
  private static int endX = 480; 
  private static int endY = 305;

  private static int xcenter = 120;
  private static int ycenter = 155; // Center position

  private static String comment = "";
  private static String target = "50"; 
 
  Connection dbCon = null;
   ResultSetMetaData rsmd = null;
   PreparedStatement ptmt = null;
   ResultSet rs = null;

  //Constructor
  DrawLine6()
  {
    //Enables the closing of the window.
    //addWindowListener(new MyFinishWindow());
    buttonStart = new JButton("draw the start point");
    buttonStart.setActionCommand("draw the start point");
    buttonStart.addActionListener(this);

    buttonEnd = new JButton("点击此按钮激活鼠标，然后用鼠标确定另一条边");
    buttonEnd.setActionCommand("点击此按钮激活鼠标，然后用鼠标确定另一条边");
    buttonEnd.addActionListener(this);

    JPanel panel = new JPanel();
    panel.setLayout(new GridLayout(1,2));
    //panel.add(buttonStart);
    panel.add(buttonEnd);
    add(panel, BorderLayout.NORTH);

    addMouseListener(this);
    clickCount = 0;
    firstClick = false;
    drawCorrect = false;

    try{       
       connect();                        
       ptmt = dbCon.prepareStatement("select term2 from hintMatch where term1=?");
       ptmt.clearParameters();
       ptmt.setString(1, "angle"); 
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
     if (command.equals("draw the start point")){
        canDrawStart = true;
     }else if (command.equals("点击此按钮激活鼠标，然后用鼠标确定另一条边")){
        canDrawEnd = true;
     }
     System.out.println("Button clicked");
  }
  
  public void mousePressed(MouseEvent e){}

  public void mouseClicked(MouseEvent e){  
     
     if (canDrawEnd){
        endX = e.getX();
        endY = e.getY();                 
     }  
        
     firstClick = true;
     clickCount ++;
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

    //The GeneralPath to decribe the car.
    GeneralPath gp = new GeneralPath();
    gp.moveTo(267,305);
    gp.lineTo(480,305);

    if (canDrawEnd){
       gp.moveTo(startX,startY);
       gp.lineTo(endX,endY);
       //canDrawEnd = false;        
    }
    g2d.draw(gp);

    drawArc(g2d);
    
  }
  
  public void drawArc(Graphics2D g2d){
     g2d.setPaint(Color.red);
     g2d.drawArc(xcenter-50, ycenter-50, 400, 400, 0, 180);
     GeneralPath gp = new GeneralPath();     

     int xs = 0, ys = 0;
     int xs_2=0, ys_2 = 0;
     String strDraw = "";
     gp = new GeneralPath();
     for (int s=0; s<=18; s++){
        xs = (int) (Math.cos(s * Math.PI / 18) * 200 + startX);
        ys = (int) (startY - Math.sin(s * Math.PI / 18 ) * 200);
        xs_2 = (int) (Math.cos(s * Math.PI / 18) * 180 + startX);
        ys_2 = (int) (startY - Math.sin(s * Math.PI / 18 ) * 180);
        gp.moveTo(xs,ys);
        gp.lineTo(xs_2,ys_2);
        strDraw = (10 * s) + "";
        if (s<9)
           g2d.drawString(strDraw, xs+5, ys);
        else if (s>9)
           g2d.drawString(strDraw, xs-20, ys);
        else
           g2d.drawString(strDraw, xs, ys);
     }
     gp.moveTo(startX-200, startY);
     gp.lineTo(startX+200, startY);
     
     BasicStroke bs = new BasicStroke(1.0f);
     g2d.setStroke(bs);
     g2d.draw(gp); 

     //here, the calculation of angle is actually dx/dy, which is the inverse of normal math definition and have the opposite signal
     double actualAngle = (double)(endX - startX) / (endY - startY);
     if (firstClick){
        if ((actualAngle<0) && target.equalsIgnoreCase("<90")){
           comment = "你画对了,你画了" + clickCount + "次;请返回原答题页面点击按钮完成填空，然后提交"; 
           drawCorrect = true;
        }else if ((actualAngle>0) && target.equalsIgnoreCase(">90")){
           comment = "你画对了,你画了" + clickCount + "次;请返回原答题页面点击按钮完成填空，然后提交"; 
           drawCorrect = true;
        }else if (Math.abs(endX-startX)<=2 && target.equalsIgnoreCase("=90")){
           comment = "你画对了,你画了" + clickCount + "次;请返回原答题页面点击按钮完成填空，然后提交"; 
           drawCorrect = true;
        }else     
           comment = "你画错了，你画了" + clickCount + "次"; 
     }else{
        comment = "你还没有开始画，请用鼠标点击画图"; 
     }
     g2d.drawString(comment, startX-200, startY+50);

     if (drawCorrect){
        try{       
           connect();                        
           ptmt = dbCon.prepareStatement("select term2 from hintMatch where term1=?");
           ptmt.clearParameters();
           ptmt.setString(1, "angle"); 
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
           ptmt.setString(2, "angleHint");
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
           ptmt.setString(2, "angleHint");
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

