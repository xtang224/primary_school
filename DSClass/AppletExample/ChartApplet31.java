/**
   @version 1.30 2000-05-12
   @author Cay Horstmann
*/
import java.awt.geom.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class ChartApplet31 extends JApplet
{  
   public void init()
   {  
      Container contentPane = getContentPane();
      contentPane.add(new ChartLine31());
     
   }  
}

class ChartLine31 extends JPanel implements MouseListener, ActionListener
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
  private static int startX5 = 0;
  private static int startY5 = 0; 
  private static int startX6 = 0;
  private static int startY6 = 0;  
  private static int startX7 = 0;
  private static int startY7 = 0;  

  private boolean done1 = false;
  private boolean done2 = false;
  private boolean done3 = false;
  private boolean done4 = false;
  private boolean done5 = false;
  private boolean done6 = false;
  private boolean done7 = false;
  
  private static int xcenterA = 130;
  private static int ycenterA = 100 + 4*30 + 30; // Center position of A
  private static int xcenterB = 190;
  private static int ycenterB = 100 + 4*30; // Center position of B
  private static int xcenterC = 250;
  private static int ycenterC = 100 + 4*30 + 2*30; // Center position of C  
  private static int xcenterD = 310;
  private static int ycenterD = 100 + 4*30 + 4*30; // Center position of D
  private static int xcenterE = 370;
  private static int ycenterE = 100 + 4*30; // Center position of E 
  private static int xcenterF = 430;
  private static int ycenterF = 100 + 24; // Center position of F 
  private static int xcenterG = 490;
  private static int ycenterG = 100 + 2*30; // Center position of F
  private static int xcenterO = xcenterA+80;
  private static int ycenterO = ycenterA; // Center position of O

  private static String comment = "";
  private static String comment2 = "";
  private static String target = "50"; 
 
  Connection dbCon = null;
   ResultSetMetaData rsmd = null;
   PreparedStatement ptmt = null;
   ResultSet rs = null;

  //Constructor
  ChartLine31()
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
    done6 = false;
    done7 = false;  

    try{       
       connect();                        
       ptmt = dbCon.prepareStatement("select term2 from hintMatch where term1=?");
       ptmt.clearParameters();
       ptmt.setString(1, "chart"); 
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
    drawRuler(g2d);  
    drawRuler2(g2d);    
  }
  
  public void drawArc(Graphics2D g2d){
     g2d.setPaint(Color.red);   
     
     /*
     g2d.fillArc(xcenterA-5, ycenterA-5, 10, 10, 0, 360);      
     g2d.drawString("A", xcenterA-20, ycenterA+20);     
     g2d.fillArc(xcenterB-5, ycenterB-5, 10, 10, 0, 360);      
     g2d.drawString("B", xcenterB-20, ycenterB-20);  
     */

     g2d.fillRect(xcenterA, ycenterA, 30, 7*30);  
     g2d.fillRect(xcenterB, ycenterB, 30, 8*30);  
     g2d.fillRect(xcenterC, ycenterC, 30, 6*30); 

     if (clickCount>=0){        
        if (done1){  
           g2d.fillRect(xcenterD, ycenterD, 30, 4*30);              
        }else{
           if (between2Points(startX, startY, xcenterD,ycenterD,xcenterD+30,ycenterD)){
              done1 = true;  
              drawCorrect = true;
              g2d.fillRect(xcenterD, ycenterD, 30, 4*30);                          
           }           
        }          
     }
     

     if (clickCount>=0){
        if (done1==false){  
           comment = "你还没有画对,请根据要求画出8人喜欢其他项目的柱状图,你画了" + (clickCount+1) + "次;"; 
           comment2 = "";
        }else{                                        
           comment = "你完全画对了,你画了" + (clickCount+1) + "次;请返回原答题页面点击按钮完成填空，然后提交";
           comment2 = "";                                         
        }       
     }else{   
        comment = "如图，已经给出了篮球、足球、乒乓球的柱状图。";
        comment2 = "请进一步画出8人喜欢其它项目的条形统计图。"; 
     }
     g2d.drawString(comment, 50, 530);
     g2d.drawString(comment2, 50, 550);

     if (drawCorrect){
        try{       
           connect();                        
           ptmt = dbCon.prepareStatement("select term2 from hintMatch where term1=?");
           ptmt.clearParameters();
           ptmt.setString(1, "chart"); 
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
           ptmt.setString(2, "chartHint");
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
     for (int i=100; i<=520; i+=30){
        gp.moveTo(i,100+4*30);
        gp.lineTo(i,460);
     }
     for (int i=460; i>=100+4*30; i-=30){
        gp.moveTo(100,i);
        gp.lineTo(520,i);
     }
     g2d.draw(gp);
  }

  public void drawRuler(Graphics2D g2d){
     g2d.setPaint(Color.red);
     //g2d.drawArc(xcenterA-10, ycenterA-10, 20, 20, 0, 360);
     //g2d.drawString("A", xcenterA-20, ycenterA+20);
     
     GeneralPath gp = new GeneralPath();
     String[] strs = new String[]{"","篮球","足球","乒乓球","其它","","",""};
     //String[] strs2 = new String[]{"","小数","百分数","计量","知识","知识","比例","知识"};
     int intV = 0;
     for (int i=0; i<=140; i++){        
        if (i % 20 == 0){
           intV = i/20;           
           g2d.drawString(strs[intV], 100+i*3-30, 480);
           //g2d.drawString(strs2[intV], 100+i*3-30, 500);
        }
     }
     gp.moveTo(100,460);
     gp.lineTo(550,460);
     gp.moveTo(550,460);
     gp.lineTo(540,450);  
     gp.moveTo(550,460);
     gp.lineTo(540,470);   
     g2d.drawString("项目", 550, 480);
     g2d.draw(gp);
  }

  public void drawRuler2(Graphics2D g2d){
     g2d.setPaint(Color.red);     
     
     GeneralPath gp = new GeneralPath();
     String str = "";
     int intV = 0;
     for (int i=0; i<=160; i++){        
        if (i % 20 == 0){
           intV = i/20;
           str = (intV*2+0) + "";
           g2d.drawString(str, 100-20, 460-i*3/2);
        }
     }      
     gp.moveTo(100,460);
     gp.lineTo(100,60+4*30);
     gp.moveTo(100,60+4*30);
     gp.lineTo(90,70+4*30);  
     gp.moveTo(100,60+4*30);
     gp.lineTo(110,70+4*30);  
     g2d.drawString("", 80, 100);
     g2d.drawString("人数/人", 80, 50+4*30);
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

  //assuming targetY==targetY2
  public boolean between2Points(int startX, int startY, int targetX, int targetY, int targetX2, int targetY2){
     if (Math.abs(startY-targetY)<=10 && startX>=targetX && startX<=targetX2)
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

