/**
   @version 1.30 2000-05-12
   @author Cay Horstmann
*/
import java.awt.geom.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class ChartTestApplet extends JApplet
{  
   public void init()
   {  
      Container contentPane = getContentPane();
      contentPane.add(new ChartTestLine());
     
   }  
}

class ChartTestLine extends JPanel implements MouseListener, ActionListener
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

  private boolean done1 = false;
  private boolean done2 = false;
  private boolean done3 = false;
  private boolean done4 = false;
  
  private static int xcenterA = 100+300;
  private static int ycenterA = 460; // Center position of A
  private static int xcenterB = 100+230;
  private static int ycenterB = 380; // Center position of B
  private static int xcenterC = 100+280;
  private static int ycenterC = 300; // Center position of C  
  private static int xcenterD = 100+140;
  private static int ycenterD = 220; // Center position of D
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
  ChartTestLine()
  {
    //Enables the closing of the window.
    //addWindowListener(new MyFinishWindow());
   
    addMouseListener(this);
    clickCount = -1;
    firstClick = false;
    drawCorrect = false;
    done1 = true;
    done2 = true;
    done3 = true;
    done4 = true;

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
    drawGrid(g2d);

    //Use of antialiasing to have nicer lines.
    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);

    //The lines should have a thickness of 3.0 instead of 1.0.
    BasicStroke bs = new BasicStroke(3.0f);
    g2d.setStroke(bs);

    drawArc(g2d);
    drawRuler(g2d);  
    drawRuler2(g2d);  

    g2d.drawString("课外活动小组人数统计图", 200, 150);  
  }
  
  public void drawArc(Graphics2D g2d){
     g2d.setPaint(Color.red);   
     
     /*
     g2d.fillArc(xcenterA-5, ycenterA-5, 10, 10, 0, 360);      
     g2d.drawString("A", xcenterA-20, ycenterA+20);     
     g2d.fillArc(xcenterB-5, ycenterB-5, 10, 10, 0, 360);      
     g2d.drawString("B", xcenterB-20, ycenterB-20);  
     */    

     if (clickCount>=0){        
        if (done1){  
           g2d.fillRect(xcenterA-300, ycenterA-40, 300, 40);  
           g2d.drawString("30", xcenterA+20, ycenterA-20);           
        }else{
           if (between2Points(startX, startY, xcenterA,ycenterA,xcenterA,ycenterA-40)){
              done1 = true;  
              g2d.fillRect(xcenterA-300, ycenterA-40, 300, 40);                          
           }           
        } 

        if (done2){
           g2d.fillRect(xcenterB-230, ycenterB-40, 230, 40); 
           g2d.drawString("23", xcenterB+20, ycenterB-20);               
        }else{
           if (between2Points(startX2, startY2, xcenterB,ycenterB,xcenterB,ycenterB-40)){
              done2 = true;               
              g2d.fillRect(xcenterB-230, ycenterB-40, 230, 40);             
           }           
        } 

        if (done3){
           g2d.fillRect(xcenterC-280, ycenterC-40, 280, 40);  
           g2d.drawString("28", xcenterC+20, ycenterC-20);         
        }else{
           if (between2Points(startX3, startY3, xcenterC,ycenterC,xcenterC,ycenterC-40)){
              done3 = true;  
              drawCorrect = true;            
              g2d.fillRect(xcenterC-280, ycenterC-40, 280, 40);          
           }           
        } 

        g2d.fillRect(xcenterD-140, ycenterD-40, 140, 40);  
        g2d.drawString("14", xcenterD+20, ycenterD-20);                     
     }
     

     if (clickCount>=0){
        if (done1==false){  
           comment = "你还没有画对,请先画出成人读物一月份售书3400本的柱状图,你画了" + (clickCount+1) + "次"; 
        }else{
           if (done2==false){
              comment = "你已经画出了成人读物一月份售书的柱状图，请继续画二月份售书3000本的柱状图。你画了" + (clickCount+1) + "次";
           }else{
              if (done3==false){
                 comment = "你已经画出了成人读物一、二月份售书的柱状图，请继续画三月份售书2700本的柱状图。你画了" + (clickCount+1) + "次";
              }else{
                 drawCorrect = true;
                 comment = "你完全画对了,你画了" + (clickCount+1) + "次;请返回原答题页面点击按钮完成填空，然后提交"; 
              }
           }               
        }       
     }else{   
        comment = "你还没有开始画，请用鼠标点击画线：请按照提示先后画出成人读物一、二、三月份售书的柱状图。";
        comment2 = "即画出一月份售书3400本、二月份售书3000本和三月份售书2700本的条形统计图。"; 
     }
     g2d.drawString(comment, 50, 550);
     if (clickCount<0)
        g2d.drawString(comment2, 50, 570);

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
     for (int i=100; i<=420; i+=40){
        gp.moveTo(i,500-8*40);
        gp.lineTo(i,500);
     }
     for (int i=500; i>=180; i-=40){
        gp.moveTo(100,i);
        gp.lineTo(460,i);
     }
     g2d.draw(gp);
  }

  public void drawRuler(Graphics2D g2d){
     g2d.setPaint(Color.red);
     //g2d.drawArc(xcenterA-10, ycenterA-10, 20, 20, 0, 360);
     //g2d.drawString("A", xcenterA-20, ycenterA+20);
     
     GeneralPath gp = new GeneralPath();
     //String[] strs = new String[]{"","1000","2000","3000","4000","5000","6000","7000","8000","",""};
     String str="";
     int intV = 0;
     for (int i=0; i<=80; i++){        
        if (i % 10 == 0){
           intV = i/10 * 4;  
           str = "" + intV;
           g2d.drawString(str, 100+i*4, 520); 
           //g2d.drawString(strs[intV], 100+i*4, 520);
        } 
     }
     gp.moveTo(100,500);
     gp.lineTo(460,500);
     gp.moveTo(460,500);
     gp.lineTo(450,490);  
     gp.moveTo(460,500);
     gp.lineTo(450,510);   
     g2d.drawString("人数/人", 460, 520);
     g2d.draw(gp);
  }

  public void drawRuler2(Graphics2D g2d){
     g2d.setPaint(Color.red);     
     
     GeneralPath gp = new GeneralPath();
     String[] strs = new String[]{"","手工组","","书画组","","乒乓球组","","航模组","",""};
     int intV = 0;
     for (int i=0; i<100; i++){        
        if (i % 10 == 0){
           intV = i/10;
           //str = intV*2 + "";
           g2d.drawString(strs[intV], 100-50, 500-i*4);
        }
     }      
     gp.moveTo(100,500);
     gp.lineTo(100,140);
     gp.moveTo(100,140);
     gp.lineTo(90,150);  
     gp.moveTo(100,140);
     gp.lineTo(110,150);  
     //g2d.drawString("20", 80, 100);
     g2d.drawString("组类", 120, 130);
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
  
  public boolean between2Points(int startX, int startY, int targetX, int targetY, int targetX2, int targetY2){
     if (targetY==targetY2){
        if (Math.abs(startY-targetY)<=10 && startX>=targetX && startX<=targetX2)
           return true;
        return false;
     }else if (targetX==targetX2){
        if (Math.abs(startX-targetX)<=10 && startY<=targetY && startY>=targetY2)
           return true;
        return false; 
     }else
        return inLine(startX,startY,targetX,targetY,targetX2,targetY2);
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

