/**
   @version 1.30 2000-05-12
   @author Cay Horstmann
*/
import java.awt.geom.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class DrawLineApplet127 extends JApplet
{  
   public void init()
   {  
      Container contentPane = getContentPane();
      contentPane.add(new DrawLine127());
     
   }  
}

class DrawLine127 extends JPanel implements MouseListener, ActionListener
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

  private boolean done1 = false;
  private boolean done2 = false;
  private boolean done3 = false;
  private boolean done4 = false;
  private boolean done5 = false;
  private boolean done6 = false;
  private boolean done7 = false;
  private boolean done8 = false;
  private boolean done9 = false;
  
  private static int xcenterO = 60+5*30;
  private static int ycenterO = 200; // Center position of O

  private static int xcenterA = xcenterO - 30;
  private static int ycenterA = ycenterO - 30; // Center position of A
  private static int xcenterB = xcenterA - 2*30;
  private static int ycenterB = ycenterA; // Center position of B
  private static int xcenterC = xcenterB;
  private static int ycenterC = ycenterB+2*30; // Center position of C  
  private static int xcenterD = xcenterC+2*30;
  private static int ycenterD = ycenterC; // Center position of D
  
  private static int xcenterH = xcenterC+80;
  private static int ycenterH = ycenterC;
  private static int xcenterH2 = xcenterD+80;
  private static int ycenterH2 = ycenterD;
  private static int xcenterLO = xcenterO;
  private static int ycenterLO = ycenterO+80;
  private static int xcenterLO2 = xcenterB;
  private static int ycenterLO2 = ycenterB+80;
  private static int xcenterLH = xcenterD;
  private static int ycenterLH = ycenterD-80;
  private static int xcenterLH2 = xcenterH2;
  private static int ycenterLH2 = ycenterH2-80;

  private static int xcenterA2 = xcenterO+30;
  private static int ycenterA2 = ycenterO-30;
  private static int xcenterB2 = xcenterA2;
  private static int ycenterB2 = ycenterA2 - 2*30;
  private static int xcenterC2 = xcenterB2 - 2*30;
  private static int ycenterC2 = ycenterB2;
  private static int xcenterD2 = xcenterC2;
  private static int ycenterD2 = ycenterC2 + 2*30;

  private static int xcenterO2 = xcenterO + 5*30;
  private static int ycenterO2 = ycenterO; // Center position of O2
  private static int xcenterA3 = xcenterA2 + 5*30;
  private static int ycenterA3 = ycenterA2;
  private static int xcenterB3 = xcenterB2 + 5*30;
  private static int ycenterB3 = ycenterB2;
  private static int xcenterC3 = xcenterC2 + 5*30;
  private static int ycenterC3 = ycenterC2;
  private static int xcenterD3 = xcenterD2 + 5*30;
  private static int ycenterD3 = ycenterD2;

  private static String comment = "";
  private static String comment2 = "";
  private static String target = "50"; 
  
  private int[] xPoints = new int[6];
  private int[] yPoints = new int[6];
  private int nPoint = 5;  

  private int[] xPoints2 = new int[6];
  private int[] yPoints2 = new int[6];
  private int nPoint2 = 5;

  private int[] xPoints3 = new int[6];
  private int[] yPoints3 = new int[6];
  private int nPoint3 = 5;
 
  Connection dbCon = null;
  ResultSetMetaData rsmd = null;
  PreparedStatement ptmt = null;
  ResultSet rs = null;

  //Constructor
  DrawLine127()
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
    done4= false;
    done5= false;
    done6 = false;
    done7 = false;
    done8= false;
    done9= false; 

    xPoints[0] = xcenterO;
    xPoints[1] = xcenterA;
    xPoints[2] = xcenterB;
    xPoints[3] = xcenterC;
    xPoints[4] = xcenterD;
    xPoints[5] = xcenterO;
    yPoints[0] = ycenterO;
    yPoints[1] = ycenterA;
    yPoints[2] = ycenterB;
    yPoints[3] = ycenterC;
    yPoints[4] = ycenterD;
    yPoints[5] = ycenterO;

    xPoints2[0] = xcenterO;
    xPoints2[1] = xcenterA2;
    xPoints2[2] = xcenterB2;
    xPoints2[3] = xcenterC2;
    xPoints2[4] = xcenterD2;
    xPoints2[5] = xcenterO;
    yPoints2[0] = ycenterO;
    yPoints2[1] = ycenterA2;
    yPoints2[2] = ycenterB2;
    yPoints2[3] = ycenterC2;
    yPoints2[4] = ycenterD2;
    yPoints2[5] = ycenterO;

    xPoints3[0] = xcenterO2;
    xPoints3[1] = xcenterA3;
    xPoints3[2] = xcenterB3;
    xPoints3[3] = xcenterC3;
    xPoints3[4] = xcenterD3;
    xPoints3[5] = xcenterO2;
    yPoints3[0] = ycenterO2;
    yPoints3[1] = ycenterA3;
    yPoints3[2] = ycenterB3;
    yPoints3[3] = ycenterC3;
    yPoints3[4] = ycenterD3;
    yPoints3[5] = ycenterO2;

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
     
     g2d.fillArc(xcenterO-5, ycenterO-5, 10, 10, 0, 360);      
     g2d.drawString("O", xcenterO+10, ycenterO+10); 

     g2d.fillArc(xcenterA-5, ycenterA-5, 10, 10, 0, 360);      
     g2d.drawString("A", xcenterA, ycenterA-10); 

     g2d.fillArc(xcenterB-5, ycenterB-5, 10, 10, 0, 360);      
     g2d.drawString("B", xcenterB-10, ycenterB-10); 

     g2d.fillArc(xcenterC-5, ycenterC-5, 10, 10, 0, 360);      
     g2d.drawString("C", xcenterC-10, ycenterC+10);    

     g2d.fillArc(xcenterD-5, ycenterD-5, 10, 10, 0, 360);      
     g2d.drawString("D", xcenterD+10, ycenterD+10);     
     
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
     gp.lineTo(xcenterO,ycenterO);    
     g2d.draw(gp);
     g2d.fillPolygon(xPoints, yPoints, nPoint); 
     /*
     g2d.drawString("��", xcenterO2-20, ycenterO2+20); 
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
           g2d.fillPolygon(xPoints2, yPoints2, nPoint2);                  
        }else{
           if (aroundPoint(startX4,startY4,xcenterD2,ycenterD2)){
              done4 = true;                         
              g2d.fillPolygon(xPoints2, yPoints2, nPoint2);                                         
           }           
        } 

        if (done5){             
           g2d.fillArc(xcenterO2-5, ycenterO2-5, 10, 10, 0, 360);      
           g2d.drawString("O2", xcenterO2, ycenterO2+10);                              
        }else{
           if (aroundPoint(startX5,startY5,xcenterO2,ycenterO2)){
              done5 = true;                                    
              g2d.fillArc(xcenterO2-5, ycenterO2-5, 10, 10, 0, 360);      
              g2d.drawString("O2", xcenterO2, ycenterO2+10);                                        
           }           
        } 

        if (done6){             
           gp.moveTo(xcenterO2,ycenterO2);                
           gp.lineTo(xcenterA3,ycenterA3);                               
        }else{
           if (aroundPoint(startX6,startY6,xcenterA3,ycenterA3)){
              done6 = true;                                        
              gp.moveTo(xcenterO2,ycenterO2);                
              gp.lineTo(xcenterA3,ycenterA3);                                         
           }           
        }

        if (done7){             
           gp.moveTo(xcenterA3,ycenterA3);                
           gp.lineTo(xcenterB3,ycenterB3);                               
        }else{
           if (aroundPoint(startX7,startY7,xcenterB3,ycenterB3)){
              done7 = true;                                        
              gp.moveTo(xcenterA3,ycenterA3);                
              gp.lineTo(xcenterB3,ycenterB3);                                         
           }           
        }

        if (done8){             
           gp.moveTo(xcenterB3,ycenterB3);                
           gp.lineTo(xcenterC3,ycenterC3);                               
        }else{
           if (aroundPoint(startX8,startY8,xcenterC3,ycenterC3)){
              done8 = true;                                        
              gp.moveTo(xcenterB3,ycenterB3);                
              gp.lineTo(xcenterC3,ycenterC3);                                         
           }           
        }

        if (done9){             
           g2d.fillPolygon(xPoints3, yPoints3, nPoint3);                             
        }else{
           if (aroundPoint(startX9,startY9,xcenterD3,ycenterD3)){
              done9 = true; 
              drawCorrect = true;                                           
              g2d.fillPolygon(xPoints3, yPoints3, nPoint3);                                         
           }           
        }
                              
     }
     g2d.draw(gp);

     if (clickCount>=0){
        if (done1==false){            
           comment = "�㻹û�л���,����ѡ���A�Ƶ�O��˳ʱ�뷽����ת90�Ⱥ󵽴�ĵ�,�㻭��" + (clickCount+1) + "��;"; 
           comment2 = "";
        }else{
           if (done2==false){
              comment = "���Ѿ��ҵ��˵�A�Ƶ�O��˳ʱ�뷽����ת90�Ⱥ󵽴�ĵ�";
              comment2 = "������ҵ�B�Ƶ�O��˳ʱ�뷽����ת90�Ⱥ󵽴�ĵ�,�㻭��" + (clickCount+1) + "��;";
           }else{
              if (done3==false){
                 comment = "���Ѿ��ҵ��˵�A��B�Ƶ�O��˳ʱ�뷽����ת90�Ⱥ󵽴�ĵ�";
                 comment2 = "������ҵ�C�Ƶ�O��˳ʱ�뷽����ת90�Ⱥ󵽴�ĵ�,�㻭��" + (clickCount+1) + "��;";
              }else{
                 if (done4==false){
                    comment = "���Ѿ��ҵ��˵�A��B��C�Ƶ�O��˳ʱ�뷽����ת90�Ⱥ󵽴�ĵ�";
                    comment2 = "������ҵ�D�Ƶ�O��˳ʱ�뷽����ת90�Ⱥ󵽴�ĵ�,�㻭��" + (clickCount+1) + "��;";
                 }else{
                    if (done5==false){
                       comment = "���Ѿ��ҵ��˵�A��B��C��D�Ƶ�O��˳ʱ�뷽����ת90�Ⱥ󵽴�ĵ�";
                       comment2 = "������ҵ�O����ƽ��5���ĵ�,�㻭��" + (clickCount+1) + "��;";                       
                    }else{
                       if (done6==false){
                          comment = "���Ѿ��ҵ��˵�O����ƽ��5���ĵ�";                          
                          comment2 = "������ҵ�A��ת�󵽴�ĵ�����ƽ��5���ĵ�,�㻭��" + (clickCount+1) + "��;";
                       }else{
                          if (done7==false){
                             comment = "���Ѿ��ҵ��˵�A��ת�󵽴�ĵ�����ƽ��5���ĵ�";                          
                             comment2 = "������ҵ�B��ת�󵽴�ĵ�����ƽ��5���ĵ�,�㻭��" + (clickCount+1) + "��;";
                          }else{
                             if (done8==false){
                                comment = "���Ѿ��ҵ��˵�B��ת�󵽴�ĵ�����ƽ��5���ĵ�";                          
                                comment2 = "������ҵ�C��ת�󵽴�ĵ�����ƽ��5���ĵ�,�㻭��" + (clickCount+1) + "��;";
                             }else{
                                if (done9==false){
                                   comment = "���Ѿ��ҵ��˵�C��ת�󵽴�ĵ�����ƽ��5���ĵ�";                          
                                   comment2 = "������ҵ�D��ת�󵽴�ĵ�����ƽ��5���ĵ�,�㻭��" + (clickCount+1) + "��;";
                                }else{
                                   comment = "����ȫ������,�㻭��" + (clickCount+1) + "��;�뷵��ԭ����ҳ������ťֱ���ύ"; 
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
     }else{   
        comment = "ͼ�и����˶����OABCD����������ͼ����O�㰴˳ʱ�뷽����ת90�Ⱥ��ͼ�Ρ�";   
       comment2 = "�ٽ���ת���ͼ������ƽ��5��";     
     }
     g2d.drawString(comment, 50, 530);
     g2d.drawString(comment2, 50, 550);

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
        gp.lineTo(i,500);
     }
     for (int i=500; i>=50; i-=30){
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
     //String[] strs2 = new String[]{"","��","","��","","��","","��","","��",""};
     
     int intV = 0;
     for (int i=0; i<=70; i++){        
        if (i % 10 == 0){
           intV = i/10;           
           g2d.drawString(strs[intV], 240+i*3, 550);
           //g2d.drawString(strs2[intV], 100+i*4, 540);                      
        } 
     }
     gp.moveTo(30,500);
     gp.lineTo(510,500);
     //gp.moveTo(410,530);
     //gp.lineTo(400,520);  
     //gp.moveTo(410,530);
     //gp.lineTo(400,540);   
     g2d.drawString("", 500, 500);   
     //g2d.drawString("1:20000", 230+60, 550);  
     g2d.drawString("", 230, 530);    
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
     gp.moveTo(30,500);
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

