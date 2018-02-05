/**
   @version 1.30 2000-05-12
   @author Cay Horstmann
*/
import java.awt.geom.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class ChartApplet26 extends JApplet
{  
   public void init()
   {  
      Container contentPane = getContentPane();
      contentPane.add(new ChartLine26());
     
   }  
}

class ChartLine26 extends JPanel implements MouseListener, ActionListener
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

  private boolean done1 = false;
  private boolean done2 = false;
  private boolean done3 = false;
  private boolean done4 = false;
  private boolean done5 = false;
  private boolean done6 = false;
  
  private static int xcenterA = 140;
  private static int ycenterA = 500-280-12; // Center position of A
  private static int xcenterB = 220;
  private static int ycenterB = 500-240; // Center position of B
  private static int xcenterC = 300;
  private static int ycenterC = 500-80-16; // Center position of C  
  private static int xcenterD = 380;
  private static int ycenterD = 500-120; // Center position of D
  private static int xcenterE = 460;
  private static int ycenterE = 500-160-32; // Center position of E
  private static int xcenterF = 430;
  private static int ycenterF = 260; // Center position of D
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
  ChartLine26()
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

    g2d.drawString("电器1月份至5月份销售情况统计图", 150, 120);  
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
           g2d.fillRect(xcenterA, ycenterA, 40, 292);              
        }else{
           if (between2Points(startX, startY, xcenterA,ycenterA,xcenterA+40,ycenterA)){
              done1 = true;  
              g2d.fillRect(xcenterA, ycenterA, 40, 292);                          
           }           
        } 

        if (done2){
           g2d.fillRect(xcenterB, ycenterB, 40, 240);            
        }else{
           if (between2Points(startX2, startY2, xcenterB,ycenterB,xcenterB+40,ycenterB)){
              done2 = true;               
              g2d.fillRect(xcenterB, ycenterB, 40, 240);             
           }           
        } 

        if (done3){
           g2d.fillRect(xcenterC, ycenterC, 40, 96);         
        }else{
           if (between2Points(startX3, startY3, xcenterC,ycenterC,xcenterC+40,ycenterC)){
              done3 = true;                           
              g2d.fillRect(xcenterC, ycenterC, 40, 96);          
           }           
        } 

        if (done4){
           g2d.fillRect(xcenterD, ycenterD, 40, 120);            
        }else{
           if (between2Points(startX4, startY4, xcenterD,ycenterD,xcenterD+40,ycenterD)){
              done4 = true;               
              g2d.fillRect(xcenterD, ycenterD, 40, 120);             
           }           
        } 

        if (done5){
           g2d.fillRect(xcenterE, ycenterE, 40, 192);            
        }else{
           if (between2Points(startX5, startY5, xcenterE,ycenterE,xcenterE+40,ycenterE)){
              done5 = true;  
              drawCorrect = true;              
              g2d.fillRect(xcenterE, ycenterE, 40, 192);             
           }           
        }                 
     }
     

     if (clickCount>=0){
        if (done1==false){  
           comment = "你还没有画对,请先画出1月份售365台空调的柱状图,你画了" + (clickCount+1) + "次"; 
        }else{
           if (done2==false){
              comment = "你已经画出了1月份售空调的柱状图，请继续画2月份售300台空调的柱状图。你画了" + (clickCount+1) + "次";
           }else{
              if (done3==false){
                 comment = "你已经画出了1、2月份售空调的柱状图，请继续画3月份售120台空调的柱状图。你画了" + (clickCount+1) + "次";
              }else{
                 if (done4==false){
                    comment = "你已经画出了1、2、3月份售空调的柱状图，请继续画4月份售150台空调的柱状图。你画了" + (clickCount+1) + "次";
                 }else{
                    if (done5==false){
                       comment = "你已经画出了1、2、3、4月份售空调的柱状图，请继续画5月份售240台空调的柱状图。你画了" + (clickCount+1) + "次";
                    }else{
                       drawCorrect = true;
                       comment = "你完全画对了,你画了" + (clickCount+1) + "次;请返回原答题页面点击按钮完成填空，然后提交"; 
                    }
                 }
              }
           }               
        }       
     }else{   
        comment = "你还没有开始画，请用鼠标点击画线：请按照提示画出家家乐电器公司1至5月售空调的统计图。";
        comment2 = "即画出1月份售365台、2月份售300台、3月份售120台、4月份售150台和5月份售240台的条形统计图。"; 
     }
     g2d.drawString(comment, 50, 600);
     if (clickCount<0)
        g2d.drawString(comment2, 50, 620);

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
     for (int i=100; i<=540; i+=40){
        gp.moveTo(i,180);
        gp.lineTo(i,500);
     }
     for (int i=500; i>=180; i-=40){
        gp.moveTo(100,i);
        gp.lineTo(540,i);
     }
     g2d.draw(gp);
  }

  public void drawRuler(Graphics2D g2d){
     g2d.setPaint(Color.red);
     //g2d.drawArc(xcenterA-10, ycenterA-10, 20, 20, 0, 360);
     //g2d.drawString("A", xcenterA-20, ycenterA+20);
     
     GeneralPath gp = new GeneralPath();
     String[] strs = new String[]{"","1","","2","","3","","4","","5",""};
     //String[] strs2 = new String[]{"","组","","组","","组","","组","","组",""};
     
     int intV = 0;
     for (int i=0; i<=100; i++){        
        if (i % 10 == 0){
           intV = i/10;           
           g2d.drawString(strs[intV], 100+i*4, 520);
           //g2d.drawString(strs2[intV], 100+i*4, 540);                      
        } 
     }
     gp.moveTo(100,500);
     gp.lineTo(560,500);
     //gp.moveTo(540,500);
     //gp.lineTo(530,490);  
     //gp.moveTo(540,500);
     //gp.lineTo(530,510);   
     g2d.drawString("月份", 540, 520);     
     g2d.draw(gp);
  }

  public void drawRuler2(Graphics2D g2d){
     g2d.setPaint(Color.red);     
     
     GeneralPath gp = new GeneralPath();
     //String[] strs = new String[]{"","","一月","","","二月","","","三月","",""};
     String str;
     int intV = 0;
     for (int i=0; i<=80; i++){        
        if (i % 10 == 0){
           intV = i/10;
           str = intV*50 + "";
           //g2d.drawString(strs[intV], 100-30, 500-i*4);
           g2d.drawString(str, 100-30, 500-i*4);
        }
     }      
     gp.moveTo(100,500);
     gp.lineTo(100,160);
     //gp.moveTo(100,60);
     //gp.lineTo(90,70);  
     //gp.moveTo(100,60);
     //gp.lineTo(110,70);  
     //g2d.drawString("20", 80, 100);
     g2d.drawString("数量/台", 80, 160);
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
