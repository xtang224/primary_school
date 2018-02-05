/**
   @version 1.30 2000-05-12
   @author Cay Horstmann
*/
import java.awt.geom.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class DrawLineApplet84 extends JApplet
{  
   public void init()
   {  
      Container contentPane = getContentPane();
      contentPane.add(new DrawLine84());
     
   }  
}

class DrawLine84 extends JPanel implements MouseListener, ActionListener
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
  private static int endX4 = 0; 
  private static int end43 = 0;

  private static int startX5 = 0;
  private static int startY5 = 0;  
  private static int startX6 = 0;
  private static int startY6 = 0; 

  private static int startX7 = 0;
  private static int startY7 = 0;  
  private static int startX8 = 0;
  private static int startY8 = 0;  

  private boolean done1 = false;
  private boolean done2 = false;
  private boolean done3 = false;
  private boolean done4 = false;
  private boolean done5 = false;
  private boolean done6 = false;
  private boolean done7 = false;
  private boolean done8 = false;

  private boolean plan1 = false;
  private boolean plan2 = false;
  
  private static int xcenterA = 30;
  private static int ycenterA = 50; // Center position of A
  private static int xcenterB = xcenterA;
  private static int ycenterB = ycenterA+300; // Center position of B
  private static int xcenterC = xcenterB+150;
  private static int ycenterC = ycenterB; // Center position of C  
  private static int xcenterC2 = xcenterB+150+30;
  private static int ycenterC2 = ycenterB; // Center position of C   
  private static int xcenterD = xcenterC;
  private static int ycenterD = ycenterC-6*30; // Center position of D
  private static int xcenterD2 = xcenterC2;
  private static int ycenterD2 = ycenterC2-4*30; // Center position of D
  private static int xcenterE = xcenterD+30;
  private static int ycenterE = ycenterD; // Center position of E 
  private static int xcenterE2 = xcenterD2-30;
  private static int ycenterE2 = ycenterD2; // Center position of E  
  private static int xcenterF = xcenterE;
  private static int ycenterF = ycenterA; // Center position of F 
  private static int xcenterF2 = xcenterE2;
  private static int ycenterF2 = ycenterA; // Center position of F  
  private static int xcenterG = 40+19*20;
  private static int ycenterG = ycenterA; // Center position of G  
  private static int xcenterH = 40+8*20;
  private static int ycenterH = ycenterA; // Center position of H  

  private static int xcenterO = 30;
  private static int ycenterO = 50; // Center position of O
  private static int xcenterO2 = xcenterO+5*60;
  private static int ycenterO2 = ycenterO+2*60; // Center position of O2
  private static int xcenterO3 = xcenterO+5*60;
  private static int ycenterO3 = ycenterO+2*60; // Center position of O2
  private static int xcenterO4 = xcenterO+5*60;
  private static int ycenterO4 = ycenterO+2*60; // Center position of O2
  
  private static int xcenterH2 = xcenterD+80;
  private static int ycenterH2 = ycenterD;
  private static int xcenterLO = xcenterO2;
  private static int ycenterLO = ycenterO2+80;
  private static int xcenterLO2 = xcenterB;
  private static int ycenterLO2 = ycenterB+80;
  private static int xcenterLH = xcenterD;
  private static int ycenterLH = ycenterD-80;
  private static int xcenterLH2 = xcenterH2;
  private static int ycenterLH2 = ycenterH2-80; 

  private static String comment = "";
  private static String comment2 = "";
  private static String target = "50"; 
 
  Connection dbCon = null;
  ResultSetMetaData rsmd = null;
  PreparedStatement ptmt = null;
  ResultSet rs = null;

  //Constructor
  DrawLine84()
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
    done8 = false;

    plan1 = false;
    plan2 = false;

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
    //drawGrid3(g2d);
    drawGrid(g2d);

    //Use of antialiasing to have nicer lines.
    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);

    //The lines should have a thickness of 3.0 instead of 1.0.
    BasicStroke bs = new BasicStroke(3.0f);
    g2d.setStroke(bs);

    drawArc(g2d);
    //drawArc2(g2d); 
    //drawRuler(g2d);  
    //drawRuler2(g2d);    
  }

  public void drawArc2(Graphics2D g2d){
     g2d.setPaint(Color.red);
     g2d.drawArc(xcenterB, ycenterB, 480, 480, -90, 0); 
  }

  public void drawArc(Graphics2D g2d){
     g2d.setPaint(Color.red);
     //g2d.fill(new Rectangle(xcenterO,ycenterO,xcenterO2-xcenterO,ycenterO2-ycenterO));   
     //g2d.drawArc(xcenterA, ycenterA, 480, 480, 0, 360); 
     //g2d.drawRect(xcenterA, ycenterA, 400, 200);
     
     g2d.fillArc(xcenterA-5, ycenterA-5, 10, 10, 0, 360);      
     g2d.drawString("A", xcenterA-20, ycenterA-20);         
     g2d.fillArc(xcenterB-5, ycenterB-5, 10, 10, 0, 360);      
     g2d.drawString("B", xcenterB-20, ycenterB+20);  
     
     /* 
     g2d.fillArc(xcenterC-5, ycenterC-5, 10, 10, 0, 360);      
     g2d.drawString("C", xcenterC+20, ycenterC+20);
     g2d.fillArc(xcenterD-5, ycenterD-5, 10, 10, 0, 360);      
     g2d.drawString("D", xcenterD+20, ycenterD-20);
     //g2d.fillArc(xcenterO-5, ycenterO-5, 10, 10, 0, 360);      
     //g2d.drawString("O", xcenterO-20, ycenterO-20);
     */ 
     g2d.setPaint(Color.black); 
     GeneralPath gp = new GeneralPath();
     gp.moveTo(xcenterA,ycenterA);
     gp.lineTo(xcenterB,ycenterB); 
     /*  
     gp.moveTo(xcenterB,ycenterB);
     gp.lineTo(xcenterC,ycenterC); 
     gp.moveTo(xcenterC,ycenterC);
     gp.lineTo(xcenterD,ycenterD);
     gp.moveTo(xcenterD,ycenterD);
     gp.lineTo(xcenterA,ycenterA);
     g2d.draw(gp);
     */
     //g2d.drawString("北", xcenterO2-20, ycenterO2+20); 
     //GeneralPath gp = new GeneralPath();
     /* 
     gp.moveTo(xcenterO2,ycenterO2+20);
     gp.lineTo(xcenterO2,ycenterO2);
     gp.moveTo(xcenterO2,ycenterO2);
     gp.lineTo(xcenterO2-5,ycenterO2+5);
     gp.moveTo(xcenterO2,ycenterO2);
     gp.lineTo(xcenterO2-5,ycenterO2+5);   
     gp.moveTo(xcenterO2,ycenterO2);
     gp.lineTo(xcenterO2+5,ycenterO2+5);   
     */
     if (clickCount>=0){
        g2d.setPaint(Color.black); 
        //gp = new GeneralPath();
        if (done1){  
           gp.moveTo(xcenterB,ycenterB);
           gp.lineTo(xcenterO,ycenterO); 
           g2d.fillArc(xcenterO-5, ycenterO-5, 10, 10, 0, 360);      
           g2d.drawString("C", xcenterO+20, ycenterO+20);                   
        }else{
           if (aroundPoint(startX,startY,xcenterC,ycenterC)){
              done1 = true;
              plan1 = true; plan2 = false;
              xcenterO = xcenterC; ycenterO = ycenterC;                                       
           }else if (aroundPoint(startX,startY,xcenterC2,ycenterC2)){
              done1 = true;
              plan1 = false; plan2 = true;
              xcenterO = xcenterC2; ycenterO = ycenterC2; 
           }
           if (done1 && (plan1 || plan2)){
              gp.moveTo(xcenterB,ycenterB);
              gp.lineTo(xcenterO,ycenterO);  
              g2d.fillArc(xcenterO-5, ycenterO-5, 10, 10, 0, 360);      
              g2d.drawString("C", xcenterO+20, ycenterO+20); 
           }           
        }  
        
        if (done2){  
           gp.moveTo(xcenterO,ycenterO);
           gp.lineTo(xcenterO2,ycenterO2);           
           g2d.fillArc(xcenterO2-5, ycenterO2-5, 10, 10, 0, 360);      
           g2d.drawString("D", xcenterO2+20, ycenterO2+20);                        
        }else{
           if (plan1 && aroundPoint(startX2,startY2,xcenterD,ycenterD)){
              done2 = true;  
              xcenterO2 = xcenterD; ycenterO2 = ycenterD;                                           
           }else if (plan2 && aroundPoint(startX2,startY2,xcenterD2,ycenterD2)){
              done2 = true;  
              xcenterO2 = xcenterD2; ycenterO2 = ycenterD2; 
           } 
           if (done2 && (plan1 || plan2)){
              gp.moveTo(xcenterO,ycenterO);
              gp.lineTo(xcenterO2,ycenterO2);  
              g2d.fillArc(xcenterO2-5, ycenterO2-5, 10, 10, 0, 360);      
              g2d.drawString("D", xcenterO2+20, ycenterO2+20); 
           }          
        }  

        if (done3){  
           gp.moveTo(xcenterO2,ycenterO2);
           gp.lineTo(xcenterO3,ycenterO3);           
           g2d.fillArc(xcenterO3-5, ycenterO3-5, 10, 10, 0, 360);      
           g2d.drawString("E", xcenterO3+20, ycenterO3+20);                        
        }else{
           if (plan1 && aroundPoint(startX3,startY3,xcenterE,ycenterE)){
              done3 = true;  
              xcenterO3 = xcenterE; ycenterO3 = ycenterE;                                           
           }else if (plan2 && aroundPoint(startX3,startY3,xcenterE2,ycenterE2)){
              done3 = true;  
              xcenterO3 = xcenterE2; ycenterO3 = ycenterE2; 
           } 
           if (done3 && (plan1 || plan2)){
              gp.moveTo(xcenterO2,ycenterO2);
              gp.lineTo(xcenterO3,ycenterO3);  
              g2d.fillArc(xcenterO3-5, ycenterO3-5, 10, 10, 0, 360);      
              g2d.drawString("E", xcenterO3+20, ycenterO3+20); 
           }          
        } 

        if (done4){  
           gp.moveTo(xcenterO3,ycenterO3);
           gp.lineTo(xcenterO4,ycenterO4); 
           gp.moveTo(xcenterO4,ycenterO4);
           gp.lineTo(xcenterA,ycenterA);           
           g2d.fillArc(xcenterO4-5, ycenterO4-5, 10, 10, 0, 360);      
           g2d.drawString("F", xcenterO4+20, ycenterO4+20);    
           g2d.fillRect(xcenterA,ycenterA,xcenterO3-xcenterA,ycenterO3-ycenterA);   
           g2d.fillRect(xcenterA,ycenterO3,xcenterO-xcenterA,ycenterO-ycenterO3);                    
        }else{
           if (plan1 && aroundPoint(startX4,startY4,xcenterF,ycenterF)){
              done4 = true; drawCorrect = true;  
              xcenterO4 = xcenterF; ycenterO4 = ycenterF;                                           
           }else if (plan2 && aroundPoint(startX4,startY4,xcenterF2,ycenterF2)){
              done4 = true;  drawCorrect = true;
              xcenterO4 = xcenterF2; ycenterO4 = ycenterF2; 
           } 
           if (done4 && (plan1 || plan2)){
              gp.moveTo(xcenterO3,ycenterO3);
              gp.lineTo(xcenterO4,ycenterO4); 
              gp.moveTo(xcenterO4,ycenterO4);
              gp.lineTo(xcenterA,ycenterA);    
              g2d.fillArc(xcenterO4-5, ycenterO4-5, 10, 10, 0, 360);      
              g2d.drawString("F", xcenterO4+20, ycenterO4+20); 
              g2d.fillRect(xcenterA,ycenterA,xcenterO3-xcenterA,ycenterO3-ycenterA);   
              g2d.fillRect(xcenterA,ycenterO3,xcenterO-xcenterA,ycenterO-ycenterO3);  
           }          
        }       
     }
     g2d.draw(gp);

     if (clickCount>=0){
        if (done1==false){            
           comment = "你还没有画对,请在图中沿着逆时针方向找到顶点C，";
           comment2 = "使多边形ABCDEF的面积占整个面积的0.54,你画了" + (clickCount+1) + "次;";           
        }else{
           if (done2==false){
              comment = "你已经找到了C点，请继续沿着逆时针方向找D点,";
              comment2 = "使多边形ABCDEF的面积占整个面积的0.54,你画了" + (clickCount+1) + "次;";        
           }else{
              if (done3==false){
                 comment = "你已经找到了C,D点，请继续沿着逆时针方向找E点,";
                 comment2 = "使多边形ABCDEF的面积占整个面积的0.54,你画了" + (clickCount+1) + "次;";  
              }else{
                 if (done4==false){
                    comment = "你已经找到了C,D,E点，请继续沿着逆时针方向找F点,";
                    comment2 = "使多边形ABCDEF的面积占整个面积的0.54,你画了" + (clickCount+1) + "次;";  
                 }else{
                    comment = "你完全画对了,你画了" + (clickCount+1) + "次;请返回原答题页面点击按钮直接提交"; 
                    comment2 = "";
                 }
              }                                             
           }          
        }       
     }else{   
        comment = "图中给出了方框和线段AB，请沿着逆时针方向找到另外4个顶点C,D,E,F";   
        comment2 = "使多边形ABCDEF的面积占整个图形的0.54;";        
     }
     g2d.drawString(comment, 50, 500);
     g2d.drawString(comment2, 50, 520);

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

  public void drawGrid(Graphics2D g2d){
     g2d.setPaint(Color.red);
     GeneralPath gp = new GeneralPath();
     for (int i=30; i<=330; i+=30){
        gp.moveTo(i,50);
        gp.lineTo(i,350);
     }
     for (int i=350; i>=50; i-=30){
        gp.moveTo(30,i);
        gp.lineTo(330,i);
     }
     g2d.draw(gp);
  }

  public void drawGrid2(Graphics2D g2d, int startX, int startY, int endX, int endY){
     g2d.setPaint(Color.blue);
     GeneralPath gp = new GeneralPath();
     for (int i=startX; i<=endX; i+=60){
        gp.moveTo(i,startY);
        gp.lineTo(i,endY);
     }
     for (int i=endY; i>=startY; i-=60){
        gp.moveTo(startX,i);
        gp.lineTo(endX,i);
     }
     g2d.draw(gp);
  } 

  public void drawGrid3(Graphics2D g2d){
     g2d.setPaint(Color.red);
     GeneralPath gp = new GeneralPath();
     int index = 0;
     for (int i=40; i<=520; i+=20){
        index = i/20;
        if (index%2==0){
           gp.moveTo(i,150);
           gp.lineTo(i,130);
        }else{
           gp.moveTo(i,150);
           gp.lineTo(i,140); 
        }
     }
     gp.moveTo(40,150);
     gp.lineTo(550,150);
     gp.moveTo(550,150);
     gp.lineTo(540,140);
     gp.moveTo(550,150);
     gp.lineTo(540,160); 
     g2d.draw(gp);
     g2d.drawString("0",280-5,150+10);
  } 

  public void drawRuler(Graphics2D g2d){
     g2d.setPaint(Color.red);
     //g2d.drawArc(xcenterA-10, ycenterA-10, 20, 20, 0, 360);
     //g2d.drawString("A", xcenterA-20, ycenterA+20);
     
     GeneralPath gp = new GeneralPath();
     String[] strs = new String[]{"","","","","","","","","","",""};
     //String[] strs2 = new String[]{"","组","","组","","组","","组","","组",""};
     
     int intV = 0;
     for (int i=0; i<=70; i++){        
        if (i % 10 == 0){
           intV = i/10;           
           g2d.drawString(strs[intV], 240+i*3, 550);
           //g2d.drawString(strs2[intV], 100+i*4, 540);                      
        } 
     }
     gp.moveTo(30,530);
     gp.lineTo(510,530);
     //gp.moveTo(410,530);
     //gp.lineTo(400,520);  
     //gp.moveTo(410,530);
     //gp.lineTo(400,540);   
     g2d.drawString("", 500, 530);   
     g2d.drawString("1:2000", 230+60, 550);  
     g2d.drawString("", 230, 550);    
     g2d.draw(gp);
  }

  public void drawRuler2(Graphics2D g2d){
     g2d.setPaint(Color.red);     
     
     GeneralPath gp = new GeneralPath();
     String[] strs = new String[]{"","","","","","","","","","","","","","","","",""};
     //String str;
     int intV = 0;
     for (int i=0; i<=160; i++){        
        if (i % 10 == 0){
           intV = i/10;
           //str = intV*50 + "";
           g2d.drawString(strs[intV], 240-30, 530-i*3);           
        }
     }      
     gp.moveTo(30,530);
     gp.lineTo(30,20);
     //gp.moveTo(140,20);
     //gp.lineTo(130,30);  
     //gp.moveTo(140,20);
     //gp.lineTo(150,30);  
     //g2d.drawString("20", 80, 100);
     g2d.drawString("", 180, 40);
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

