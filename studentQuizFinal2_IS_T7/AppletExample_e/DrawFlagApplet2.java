/*
 * @(#)Clock2.java	1.16 06/02/22
 * 
 * Copyright (c) 2006 Sun Microsystems, Inc. All Rights Reserved.
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * 
 * -Redistribution of source code must retain the above copyright notice, this
 *  list of conditions and the following disclaimer.
 * 
 * -Redistribution in binary form must reproduce the above copyright notice, 
 *  this list of conditions and the following disclaimer in the documentation
 *  and/or other materials provided with the distribution.
 * 
 * Neither the name of Sun Microsystems, Inc. or the names of contributors may 
 * be used to endorse or promote products derived from this software without 
 * specific prior written permission.
 * 
 * This software is provided "AS IS," without a warranty of any kind. ALL 
 * EXPRESS OR IMPLIED CONDITIONS, REPRESENTATIONS AND WARRANTIES, INCLUDING
 * ANY IMPLIED WARRANTY OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE
 * OR NON-INFRINGEMENT, ARE HEREBY EXCLUDED. SUN MICROSYSTEMS, INC. ("SUN")
 * AND ITS LICENSORS SHALL NOT BE LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE
 * AS A RESULT OF USING, MODIFYING OR DISTRIBUTING THIS SOFTWARE OR ITS
 * DERIVATIVES. IN NO EVENT WILL SUN OR ITS LICENSORS BE LIABLE FOR ANY LOST 
 * REVENUE, PROFIT OR DATA, OR FOR DIRECT, INDIRECT, SPECIAL, CONSEQUENTIAL, 
 * INCIDENTAL OR PUNITIVE DAMAGES, HOWEVER CAUSED AND REGARDLESS OF THE THEORY 
 * OF LIABILITY, ARISING OUT OF THE USE OF OR INABILITY TO USE THIS SOFTWARE, 
 * EVEN IF SUN HAS BEEN ADVISED OF THE POSSIBILITY OF SUCH DAMAGES.
 * 
 * You acknowledge that this software is not designed, licensed or intended
 * for use in the design, construction, operation or maintenance of any
 * nuclear facility.
 */

/*
 * @(#)Clock2.java	1.16 06/02/22
 * 
 *  revised by Xiaoyu Tang 10/27/2013
 */
import java.awt.geom.*;

import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

import java.util.*;
import java.awt.*;
import java.applet.*;
import java.text.*;

/**
 * Time!
 *
 * @author Rachel Gollub
 * @modified Daniel Peek replaced circle drawing calculation, few more changes
 */
public class DrawFlagApplet2 extends Applet implements Runnable {
    private volatile Thread timer;       // The thread that displays clock
    private int lastxs, lastys, lastxm,
                lastym, lastxh, lastyh;  // Dimensions used to draw hands 
    private SimpleDateFormat formatter;  // Formats the date displayed
    private String lastdate;             // String to hold date displayed
    private Font clockFaceFont;          // Font for number display on clock
    private java.util.Date currentDate;            // Used to get date to display
    private Color handColor;             // Color of main hands and dial
    private Color numberColor;           // Color of second hand and numbers
    private int xcenter = 80, ycenter = 55; // Center position

    private java.util.Date endDate;
    private String strEndDate;

    Connection dbCon = null;
    ResultSetMetaData rsmd = null;
    PreparedStatement ptmt = null;
    ResultSet rs = null;

    private String target;
    private int intTarget;
    private int level = 0;

    public void init() {
        int x,y;
        lastxs = lastys = lastxm = lastym = lastxh = lastyh = 0;
        formatter = new SimpleDateFormat ("EEE MMM dd HH:mm:ss yyyy", Locale.getDefault());        
        
        currentDate = new java.util.Date();
        lastdate = "current time is :" + formatter.format(currentDate);
        clockFaceFont = new Font("Serif", Font.PLAIN, 14);
        handColor = Color.blue;
        numberColor = Color.darkGray;

        endDate = new java.util.Date(currentDate.getTime() + 1000*1800);
        strEndDate = "end time is :" + formatter.format(endDate);

        try {
            setBackground(new Color(Integer.parseInt(getParameter("bgcolor"),
                                                     16)));
        } catch (NullPointerException e) {
        } catch (NumberFormatException e) {
        }
        try {
            handColor = new Color(Integer.parseInt(getParameter("fgcolor1"),
                                                   16));
        } catch (NullPointerException e) {
        } catch (NumberFormatException e) {
        }
        try {
            numberColor = new Color(Integer.parseInt(getParameter("fgcolor2"),
                                                     16));
        } catch (NullPointerException e) {
        } catch (NumberFormatException e) {
        }

        try{       
           connect();                        
           ptmt = dbCon.prepareStatement("select term2 from hintMatch where term1=?");
           ptmt.clearParameters();
           ptmt.setString(1, "totalScore2"); 
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

      double dblTarget = Double.parseDouble(target);
      intTarget = (int)dblTarget;

        //resize(600,500);              // Set clock window size
    }

    // Paint is the main part of the program
    public void update(Graphics g) {
        if (level <= intTarget)
           clearWindow(g);
        g.setColor(Color.red);
        if (level <= intTarget)
           g.fillRect(150, 500-level*4, 100, 50);  
        drawRuler(g);

        //g.fillArc(150, 500-level*4, 10, 10, 0, 360);
        //Image img1 = Toolkit.getDefaultToolkit().getImage("blue.gif");
        
        Image img1 = Toolkit.getDefaultToolkit().getImage("flag.jpg");
        g.drawImage(img1, 150, 500-level*4, this);
        g.finalize();
                
        /*
        int xh, yh, xm, ym, xs, ys;
        int s = 0, m = 10, h = 10;
        String today;       

        currentDate = new java.util.Date();
        
        formatter.applyPattern("s");
        try {
            s = Integer.parseInt(formatter.format(currentDate));
        } catch (NumberFormatException n) {
            s = 0;
        }
        formatter.applyPattern("m");
        try {
            m = Integer.parseInt(formatter.format(currentDate));
        } catch (NumberFormatException n) {
            m = 10;
        }    
        formatter.applyPattern("h");
        try {
            h = Integer.parseInt(formatter.format(currentDate));
        } catch (NumberFormatException n) {
            h = 10;
        }
    
        // Set position of the ends of the hands
        xs = (int) (Math.cos(s * Math.PI / 30 - Math.PI / 2) * 45 + xcenter);
        ys = (int) (Math.sin(s * Math.PI / 30 - Math.PI / 2) * 45 + ycenter);
        xm = (int) (Math.cos(m * Math.PI / 30 - Math.PI / 2) * 40 + xcenter);
        ym = (int) (Math.sin(m * Math.PI / 30 - Math.PI / 2) * 40 + ycenter);
        xh = (int) (Math.cos((h*30 + m / 2) * Math.PI / 180 - Math.PI / 2) * 30
                   + xcenter);
        yh = (int) (Math.sin((h*30 + m / 2) * Math.PI / 180 - Math.PI / 2) * 30
                   + ycenter);
    
        // Get the date to print at the bottom        
        formatter.applyPattern("EEE MMM dd HH:mm:ss yyyy");
        today = "current time is :" + formatter.format(currentDate);

        g.setFont(clockFaceFont);
        // Erase if necessary
        g.setColor(getBackground());
        if (xs != lastxs || ys != lastys) {
            g.drawLine(xcenter, ycenter, lastxs, lastys);
            g.drawString(lastdate, 5, 125);
            g.drawString(strEndDate, 5, 145);
        }
        if (xm != lastxm || ym != lastym) {
            g.drawLine(xcenter, ycenter-1, lastxm, lastym);
            g.drawLine(xcenter-1, ycenter, lastxm, lastym); 
        }
        if (xh != lastxh || yh != lastyh) {
            g.drawLine(xcenter, ycenter-1, lastxh, lastyh);
            g.drawLine(xcenter-1, ycenter, lastxh, lastyh); 
        }

        // Draw date and hands
        g.setColor(numberColor);
        g.drawString(today, 5, 125);   
        g.drawString(strEndDate, 5, 145);
  
        g.drawLine(xcenter, ycenter, xs, ys);
        g.setColor(handColor);
        g.drawLine(xcenter, ycenter-1, xm, ym);
        g.drawLine(xcenter-1, ycenter, xm, ym);
        g.drawLine(xcenter, ycenter-1, xh, yh);
        g.drawLine(xcenter-1, ycenter, xh, yh);
        lastxs = xs; lastys = ys;
        lastxm = xm; lastym = ym;
        lastxh = xh; lastyh = yh;
        lastdate = today;
        currentDate = null;
        */
    }

    public void paint(Graphics g) {
        clearWindow(g);
        //g.fillArc(150, 500-level*4, 10, 10, 0, 360);       
        g.fillRect(150, 500-level*4, 100, 50);  
        drawRuler(g);
     
        //Image img1 = Toolkit.getDefaultToolkit().getImage("blue.gif");
        
        Image img1 = Toolkit.getDefaultToolkit().getImage("flag.jpg");
        g.drawImage(img1, 150, 500-level*4, this);
        g.finalize(); 
                
        /*
        g.setFont(clockFaceFont);
        // Draw the circle and numbers
        g.setColor(handColor);
        g.drawArc(xcenter-50, ycenter-50, 100, 100, 0, 360);
        g.setColor(numberColor);
        g.drawString("9", xcenter-45, ycenter+3); 
        g.drawString("3", xcenter+40, ycenter+3);
        g.drawString("12", xcenter-5, ycenter-37);
        g.drawString("6", xcenter-3, ycenter+45);

        // Draw date and hands
        g.setColor(numberColor);
        g.drawString(lastdate, 5, 125);    
        g.drawString(strEndDate, 5, 145);

        g.drawLine(xcenter, ycenter, lastxs, lastys);
        g.setColor(handColor);
        g.drawLine(xcenter, ycenter-1, lastxm, lastym);
        g.drawLine(xcenter-1, ycenter, lastxm, lastym);
        g.drawLine(xcenter, ycenter-1, lastxh, lastyh);
        g.drawLine(xcenter-1, ycenter, lastxh, lastyh); 
        */
    }

    public void drawRuler(Graphics g){
     g.setColor(Color.red);
     //g2d.drawArc(xcenterA-1, ycenterA-1, 2, 2, 0, 360);
     //g2d.drawString("A", xcenterA-20, ycenterA-20);     
     //GeneralPath gp = new GeneralPath();
     //gp.moveTo(100,500-i*4);
     //gp.lineTo(100+20,500-i*4);
     //g2d.draw(gp);

     String str = "";
     int intV = 0;
     for (int i=0; i<=100; i++){       
        if (i % 10 == 0){
           g.drawLine(100, 500-i*4, 100+20,500-i*4);
           intV = i;
           str = intV + "";
           g.drawString(str, 100-20, 500-i*4);
        }else
           g.drawLine(100, 500-i*4, 100+10,500-i*4);  
     }     
  }

    public void clearWindow(Graphics g)
  {
    g.setColor(Color.white);
    //g.fill(new Rectangle(0,0,getWidth(),getHeight()));
    g.fillRect(0,0,getWidth(),getHeight());
    g.setColor(Color.red);
  }

    private boolean connect() throws ClassNotFoundException, SQLException
    {
       Class.forName("org.hsqldb.jdbcDriver");
       dbCon = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/xdb", "SA", "");
       return true;     
    } 

    public void start() {
        timer = new Thread(this);
        timer.start();
    }

    public void stop() {
        timer = null;
    }

    public void run() {
        Thread me = Thread.currentThread();
        while (timer == me) {
            try {
                Thread.currentThread().sleep(100);
            } catch (InterruptedException e) {
            }
            if (level<=intTarget)
               level ++;
            else
               stop();
            repaint();
        }
    }

    public String getAppletInfo() {
        return "Title: A Clock \n"
            + "Author: Rachel Gollub, 1995 \n"
            + "An analog clock.";
    }
  
    public String[][] getParameterInfo() {
        String[][] info = {
            {"bgcolor", "hexadecimal RGB number", 
             "The background color. Default is the color of your browser."},
            {"fgcolor1", "hexadecimal RGB number", 
             "The color of the hands and dial. Default is blue."},
            {"fgcolor2", "hexadecimal RGB number", 
             "The color of the second hand and numbers. Default is dark gray."}
        };
        return info;
    }
}
