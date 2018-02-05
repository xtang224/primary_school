/**
   @version 1.30 2000-05-12
   @author Cay Horstmann
*/
import java.awt.geom.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class DrawLineApplet103 extends JApplet
{  
   public void init()
   {  
      Container contentPane = getContentPane();
      contentPane.add(new DrawLine103());     
   }  
}

class DrawLine103 extends JPanel implements MouseListener, ActionListener
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
  private static int startX9 = 0;
  private static int startY9 = 0; 
  private static int startX10 = 0;
  private static int startY10 = 0;
  private static int startX11 = 0;
  private static int startY11 = 0;
  private static int startX12 = 0;
  private static int startY12 = 0;

  private boolean done1 = false;
  private boolean done2 = false;
  private boolean done3 = false;
  private boolean done4 = false;
  private boolean done5 = false;
  private boolean done6 = false;
  private boolean done7 = false;
  private boolean done8 = false;
  private boolean done9 = false;
  private boolean done10 = false;
  private boolean done11= false;
  private boolean done12 = false;
  
  private static int xcenterO = 60+5*30;
  private static int ycenterO = 200; // Center position of O
  private static int xcenterA = xcenterO-2*30;
  private static int ycenterA = ycenterO; // Center position of A
  private static int xcenterB = xcenterA-30;
  private static int ycenterB = ycenterA-30; // Center position of B
  private static int xcenterC = xcenterB-30;
  private static int ycenterC = ycenterB+30; // Center position of C  
  private static int xcenterD = xcenterC+30;
  private static int ycenterD = ycenterC+30; // Center position of D
  private static int xcenterE = xcenterD;
  private static int ycenterE = ycenterD+4*30; // Center position of E
  private static int xcenterF = xcenterE+30;
  private static int ycenterF = ycenterE+30; // Center position of F
  private static int xcenterG = xcenterF+2*30;
  private static int ycenterG = ycenterF; // Center position of H
  private static int xcenterH = xcenterG;
  private static int ycenterH = ycenterG-30;
  private static int xcenterI = xcenterH-30;
  private static int ycenterI = ycenterH-30;
  private static int xcenterJ = xcenterI+30;
  private static int ycenterJ = ycenterI; 
  private static int xcenterK = xcenterJ;
  private static int ycenterK = ycenterJ-2*30;  
  
  private static int xcenterO2 = xcenterK-2*30;
  private static int ycenterO2 = ycenterK; // Center position of O2
  private static int xcenterO3 = xcenterO + (xcenterO-xcenterO2) - 30;
  private static int ycenterO3 = ycenterO2;   
  
  private static int xcenterLO = xcenterO2;
  private static int ycenterLO = ycenterO2+80;
  private static int xcenterLO2 = xcenterB;
  private static int ycenterLO2 = ycenterB+80;
  private static int xcenterLH = xcenterD;
  private static int ycenterLH = ycenterD-80;
  
  private static int xcenterA2 = xcenterO + (xcenterO-xcenterA);
  private static int ycenterA2 = ycenterA;
  private static int xcenterB2 = xcenterO + (xcenterO-xcenterB);
  private static int ycenterB2 = ycenterB;
  private static int xcenterC2 = xcenterO + (xcenterO-xcenterC);
  private static int ycenterC2 = ycenterC;
  private static int xcenterD2 = xcenterO + (xcenterO-xcenterD);
  private static int ycenterD2 = ycenterD;
  private static int xcenterE2 = xcenterO + (xcenterO-xcenterE);
  private static int ycenterE2 = ycenterE;
  private static int xcenterF2 = xcenterO + (xcenterO-xcenterF);
  private static int ycenterF2 = ycenterF; 
  private static int xcenterG2 = xcenterO + (xcenterO-xcenterG);
  private static int ycenterG2 = ycenterG; 
  private static int xcenterH2 = xcenterO + (xcenterO-xcenterH);
  private static int ycenterH2 = ycenterH; 
  private static int xcenterI2 = xcenterO + (xcenterO-xcenterI);
  private static int ycenterI2 = ycenterI; 
  private static int xcenterJ2 = xcenterO + (xcenterO-xcenterJ);
  private static int ycenterJ2 = ycenterJ;
  private static int xcenterK2 = xcenterO + (xcenterO-xcenterK);
  private static int ycenterK2 = ycenterK;  

  private static int xcenterA3 = xcenterA2 + 6*30;
  private static int ycenterA3 = ycenterA2 - 4*30;
  private static int xcenterB3 = xcenterA3;
  private static int ycenterB3 = ycenterA3 + 2*30;
  private static int xcenterC3 = xcenterB3 + 6*30;
  private static int ycenterC3 = ycenterB3;

  private static String comment = "";
  private static String comment2 = "";
  private static String target = "50"; 
 
  Connection dbCon = null;
  ResultSetMetaData rsmd = null;
  PreparedStatement ptmt = null;
  ResultSet rs = null;

  //Constructor
  DrawLine103()
  {
    //Enables the closing of the window.
    //addWindowListener(new MyFinishWindow());
    
    System.out.println("xcenterO3=" + xcenterO3 + ", ycenterO3=" + ycenterO3);
    addMouseListener(this);
    clickCount = -1;
    firstClick = false;
    drawCorrect = false;
    done1 = false;
    done2 = false;
    done3 = false;
    done4= false;
    done5 = false;
    done6 = false;
    done7 = false;
    done8= false;
    done9 = false;
    done10 = false;
    done11= false;
    done12= false;

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
     }else if (done6==false){        
         startX6 = e.getX();
         startY6 = e.getY();       
     }else if (done7==false){        
         startX7 = e.getX();
         startY7 = e.getY();       
     }else if (done8==false){        
         startX8 = e.getX();
         startY8 = e.getY();       
     }else if (done9==false){        
         startX9 = e.getX();
         startY9 = e.getY();       
     }else if (done10==false){        
         startX10 = e.getX();
         startY10 = e.getY();       
     }else if (done11==false){        
         startX11 = e.getX();
         startY11 = e.getY();       
     }else if (done12==false){        
         startX12 = e.getX();
         startY12 = e.getY();       
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
    //drawArc2(g2d); 
    drawRuler(g2d);  
    drawRuler2(g2d);    
  }

  public void drawArc2(Graphics2D g2d){
     g2d.setPaint(Color.red);
     g2d.drawArc(xcenterB, ycenterB, 480, 480, -90, 0); 
  }

  public void drawArc(Graphics2D g2d){
     g2d.setPaint(Color.red);
     //g2d.drawArc(xcenterA, ycenterA, 480, 480, 0, 360); 
     //g2d.drawRect(xcenterA, ycenterA, 400, 200);
     
     g2d.fillArc(xcenterA-5, ycenterA-5, 10, 10, 0, 360);      
     g2d.drawString("A", xcenterA-10, ycenterA-10); 

     g2d.fillArc(xcenterB-5, ycenterB-5, 10, 10, 0, 360);      
     g2d.drawString("B", xcenterB-10, ycenterB-10); 

     g2d.fillArc(xcenterC-5, ycenterC-5, 10, 10, 0, 360);      
     g2d.drawString("C", xcenterC-10, ycenterC+10); 

     g2d.fillArc(xcenterD-5, ycenterD-5, 10, 10, 0, 360);      
     g2d.drawString("D", xcenterD-10, ycenterD+10); 
  
     g2d.fillArc(xcenterE-5, ycenterE-5, 10, 10, 0, 360);      
     g2d.drawString("E", xcenterE-10, ycenterE+10); 

     g2d.fillArc(xcenterF-5, ycenterF-5, 10, 10, 0, 360);      
     g2d.drawString("F", xcenterF-10, ycenterF+10);

     g2d.fillArc(xcenterG-5, ycenterG-5, 10, 10, 0, 360);      
     g2d.drawString("G", xcenterG-10, ycenterG+10);

     g2d.fillArc(xcenterH-5, ycenterH-5, 10, 10, 0, 360);      
     g2d.drawString("H", xcenterH-10, ycenterH+10);

     g2d.fillArc(xcenterI-5, ycenterI-5, 10, 10, 0, 360);      
     g2d.drawString("I", xcenterI-10, ycenterI+10);

     g2d.fillArc(xcenterJ-5, ycenterJ-5, 10, 10, 0, 360);      
     g2d.drawString("J", xcenterJ-10, ycenterJ+10);

     g2d.fillArc(xcenterK-5, ycenterK-5, 10, 10, 0, 360);      
     g2d.drawString("K", xcenterK-10, ycenterK+10);
     
     GeneralPath gp = new GeneralPath();
     gp.moveTo(xcenterO,ycenterO);     
     gp.lineTo(xcenterA,ycenterA);
     gp.moveTo(xcenterA,ycenterA);
     gp.lineTo(xcenterB,ycenterB);   
     gp.moveTo(xcenterB,ycenterB);
     gp.lineTo(xcenterC,ycenterC);  
     gp.moveTo(xcenterC,ycenterC);
     gp.lineTo(xcenterD,ycenterD); 
     gp.moveTo(xcenterD,ycenterD);
     gp.lineTo(xcenterE,ycenterE); 
     gp.moveTo(xcenterE,ycenterE);
     gp.lineTo(xcenterF,ycenterF); 
     gp.moveTo(xcenterF,ycenterF);
     gp.lineTo(xcenterG,ycenterG);
     gp.moveTo(xcenterG,ycenterG);
     gp.lineTo(xcenterH,ycenterH);
     gp.moveTo(xcenterH,ycenterH);
     gp.lineTo(xcenterI,ycenterI);
     gp.moveTo(xcenterI,ycenterI);
     gp.lineTo(xcenterJ,ycenterJ);
     gp.moveTo(xcenterJ,ycenterJ);
     gp.lineTo(xcenterK,ycenterK);

     //gp.moveTo(xcenterO2,ycenterO2-10);
     //gp.lineTo(xcenterO2,ycenterO2+10);

     //gp.moveTo(xcenterO3,ycenterO3-10);
     //gp.lineTo(xcenterO3,ycenterO3+10);       

     g2d.draw(gp);

     g2d.fillRect(xcenterO2, ycenterO2, 30, 30); 
     /*
     g2d.drawString("北", xcenterO2-20, ycenterO2+20); 
     GeneralPath gp = new GeneralPath(); 
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
        gp = new GeneralPath();
        if (done1){             
           gp.moveTo(xcenterA2,ycenterA2);
           gp.lineTo(xcenterO,ycenterO);   
                                 
        }else{
           if (aroundPoint(startX,startY,xcenterA2,ycenterA2)){
              done1 = true;              
              gp.moveTo(xcenterA2,ycenterA2);
              gp.lineTo(xcenterO,ycenterO);                             
           }           
        }  
        
        if (done2){             
           gp.moveTo(xcenterB2,ycenterB2);           
           gp.lineTo(xcenterA2,ycenterA2);                             
        }else{
           if (aroundPoint(startX2,startY2,xcenterB2,ycenterB2)){
              done2 = true;                                        
              gp.moveTo(xcenterB2,ycenterB2);                
              gp.lineTo(xcenterA2,ycenterA2);              
           }           
        }  

        if (done3){             
           gp.moveTo(xcenterB2,ycenterB2);
           gp.lineTo(xcenterC2,ycenterC2);                                
        }else{
           if (aroundPoint(startX3,startY3,xcenterC2,ycenterC2)){
              done3 = true;               
              gp.moveTo(xcenterB2,ycenterB2);
              gp.lineTo(xcenterC2,ycenterC2);                                        
           }           
        } 

        if (done4){             
           gp.moveTo(xcenterC2,ycenterC2);
           gp.lineTo(xcenterD2,ycenterD2);                                
        }else{
           if (aroundPoint(startX4,startY4,xcenterD2,ycenterD2)){
              done4 = true;                                         
              gp.moveTo(xcenterC2,ycenterC2);
              gp.lineTo(xcenterD2,ycenterD2);                                        
           }           
        } 

        if (done5){             
           gp.moveTo(xcenterD2,ycenterD2);
           gp.lineTo(xcenterE2,ycenterE2);                                
        }else{
           if (aroundPoint(startX5,startY5,xcenterE2,ycenterE2)){
              done5 = true;                                        
              gp.moveTo(xcenterD2,ycenterD2);
              gp.lineTo(xcenterE2,ycenterE2);                                        
           }           
        } 

        if (done6){             
           gp.moveTo(xcenterE2,ycenterE2);
           gp.lineTo(xcenterF2,ycenterF2);                                
        }else{
           if (aroundPoint(startX6,startY6,xcenterF2,ycenterF2)){
              done6 = true;                                        
              gp.moveTo(xcenterE2,ycenterE2);
              gp.lineTo(xcenterF2,ycenterF2);                                        
           }           
        } 

        if (done7){             
           gp.moveTo(xcenterF2,ycenterF2);
           gp.lineTo(xcenterG2,ycenterG2);                                
        }else{
           if (aroundPoint(startX7,startY7,xcenterG2,ycenterG2)){
              done7 = true;                                        
              gp.moveTo(xcenterF2,ycenterF2);
              gp.lineTo(xcenterG2,ycenterG2);                                        
           }           
        } 

        if (done8){             
           gp.moveTo(xcenterH2,ycenterH2);
           gp.lineTo(xcenterG2,ycenterG2);                                
        }else{
           if (aroundPoint(startX8,startY8,xcenterH2,ycenterH2)){
              done8 = true;                                        
              gp.moveTo(xcenterH2,ycenterH2);
              gp.lineTo(xcenterG2,ycenterG2);                                        
           }           
        } 

        if (done9){             
           gp.moveTo(xcenterI2,ycenterI2);
           gp.lineTo(xcenterH2,ycenterH2);                                
        }else{
           if (aroundPoint(startX9,startY9,xcenterI2,ycenterI2)){
              done9 = true;                                        
              gp.moveTo(xcenterI2,ycenterI2);
              gp.lineTo(xcenterH2,ycenterH2);                                        
           }           
        }   

        if (done10){             
           gp.moveTo(xcenterI2,ycenterI2);
           gp.lineTo(xcenterJ2,ycenterJ2);                                
        }else{
           if (aroundPoint(startX10,startY10,xcenterJ2,ycenterJ2)){
              done10 = true;                                        
              gp.moveTo(xcenterI2,ycenterI2);
              gp.lineTo(xcenterJ2,ycenterJ2);                                        
           }           
        }  

        if (done11){             
           gp.moveTo(xcenterJ2,ycenterJ2);
           gp.lineTo(xcenterK2,ycenterK2);                                
        }else{
           if (aroundPoint(startX11,startY11,xcenterK2,ycenterK2)){
              done11 = true;                                        
              gp.moveTo(xcenterK2,ycenterK2);
              gp.lineTo(xcenterJ2,ycenterJ2);                                        
           }           
        } 

        if (done12){             
           g2d.fillRect(xcenterO3,ycenterO3,30,30);                                
        }else{
           if (aroundPoint2(startX12,startY12,xcenterO3,ycenterO3)){
              done12 = true; 
              drawCorrect = true;                                       
              g2d.fillRect(xcenterO3,ycenterO3,30,30);                                      
           }           
        }                 
     }
     g2d.draw(gp);

     if (clickCount>=0){
        if (done1==false){            
           comment = "你还没有画对,请点击选择点A所对应的对称轴上的点,你画了" + (clickCount+1) + "次;"; 
           comment2 = "";
        }else{
           if (done2==false){
              comment = "你已经找到了点A所对应的对称轴上的点";
              comment2 = "请继续找点B所对应的对称轴上的点,你画了" + (clickCount+1) + "次;";
           }else{
              if (done3==false){
                 comment = "你已经找到了点A，B所对应的对称轴上的点";
                 comment2 = "请继续找点C所对应的对称轴上的点,你画了" + (clickCount+1) + "次;";
              }else{
                 if (done4==false){
                    comment = "你已经找到了点A，B，C所对应的对称轴上的点";
                    comment2 = "请继续找点D所对应的对称轴上的点,你画了" + (clickCount+1) + "次;";
                 }else{
                    if (done5==false){
                       comment = "你已经找到了点A，B，C，D所对应的对称轴上的点";
                       comment2 = "请继续找点E所对应的对称轴上的点,你画了" + (clickCount+1) + "次;";
                    }else{
                       if (done6==false){
                          comment = "你已经找到了点A，B，C，D，E所对应的对称轴上的点";
                          comment2 = "请继续找点F所对应的对称轴上的点,你画了" + (clickCount+1) + "次;";
                       }else{
                          if (done7==false){
                             comment = "你已经找到了点A，B，C，D，E，F所对应的对称轴上的点";
                             comment2 = "请继续找点G所对应的对称轴上的点,你画了" + (clickCount+1) + "次;";
                          }else{
                             if (done8==false){
                                comment = "你已经找到了点A，B，C，D，E，F，G所对应的对称轴上的点";
                                comment2 = "请继续找点H所对应的对称轴上的点,你画了" + (clickCount+1) + "次;";
                             }else{
                                if (done9==false){
                                   comment = "你已经找到了点A，B，C，D，E，F，G，H所对应的对称轴上的点";
                                   comment2 = "请继续找点I所对应的对称轴上的点,你画了" + (clickCount+1) + "次;";
                                }else{
                                   if (done10==false){
                                      comment = "你已经找到了点A，B，C，D，E，F，G，H，I所对应的对称轴上的点";
                                      comment2 = "请继续找点J所对应的对称轴上的点,你画了" + (clickCount+1) + "次;";
                                   }else{ 
                                      if (done11==false){
                                         comment = "你已经找到了点A，B，C，D，E，F，G，H，I，J所对应的对称轴上的点";
                                         comment2 = "请继续找点K所对应的对称轴上的点,你画了" + (clickCount+1) + "次;";
                                      }else{ 
                                         if (done12==false){
                                            comment = "你已经找到了点A，B，C，D，E，F，G，H，I，J，K所对应的对称轴上的点";
                                            comment2 = "请继续找涂色部分所对应的对称轴上的点,你画了" + (clickCount+1) + "次;";
                                         }else{  
                                            comment = "你完全画对了,你画了" + (clickCount+1) + "次;请返回原答题页面点击按钮直接提交"; 
                                            comment2 = "";                                           
                                         }
                                      }
                                   }
                                }
                             }
                          }
                       }
                    }
                 }    
              }                             
           }          
        }       
     }else{   
        comment = "图中给出了一个图形的一半和一条对称轴，请按照这条对称轴完成图形的另一半。";   
        comment2 = "请点击选择点A所对应的对称轴上的点。";     
     }
     g2d.drawString(comment, 50, 550);
     g2d.drawString(comment2, 50, 570);

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
     g2d.setPaint(Color.yellow);
     GeneralPath gp = new GeneralPath();
     for (int i=30; i<=540; i+=30){
        gp.moveTo(i,50);
        gp.lineTo(i,530);
     }
     for (int i=530; i>=50; i-=30){
        gp.moveTo(30,i);
        gp.lineTo(540,i);
     }
     g2d.draw(gp);
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
     //g2d.drawString("1:20000", 230+60, 550);  
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

  public boolean aroundPoint2(int startX, int startY, int targetX, int targetY){
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

