/**
   @version 1.30 2000-05-12
   @author Cay Horstmann
*/

import java.awt.*;
import java.awt.event.*;
import java.beans.*;
import java.io.*;
import java.net.URL;
import java.util.*;
import java.sql.*;

import javax.swing.text.*;
import javax.swing.undo.*;
import javax.swing.event.*;
import javax.swing.*;

public class InputApplet extends JApplet
{  
   public void init()
   {  
      Container contentPane = getContentPane();
      contentPane.add(new InputPanel());
   }
}

/**
   A panel with calculator buttons and a result display.
   (Unchanged from chapter 9.)
*/
class InputPanel extends JPanel implements ActionListener
{  
   //private Editor display;
   private Editor display;
   private JPanel panel;
   //private ButtonGroup  status_group = null;
   //private JRadioButton status_One = null;
   private JButton status_One = null;
   private JButton status_Two = null;
   private JButton status_Three = null;
   private JButton status_Four = null;
   private JButton status_Five = null;
   private JButton status_Six = null;
   private JButton status_Seven = null;
   private JButton status_Eight = null;
   private JButton status_Nine = null;
   private JButton status_Ten = null;
   private JButton status_Hundred = null;
   private JButton status_Thousand = null;
   private JButton status_Wan = null;
   private JButton status_Yi = null;
   private JButton status_Zero = null;

   private JButton status_Delete = null;

   private JButton buttonDone = null;

   //private String[] strs = new String[]{"一"; "二"; "三"; "四"; "五"; "六"; "七"; "八"; "九"; "十"; "百"; "千"; "万"; "亿"; "零"};
   private String[] strs = new String[]{"一", "二", "三", "四", "五", "六", "七", "八", "九", "十", "百", "千", "万", "亿", "零"};

   Connection dbCon = null;
   ResultSetMetaData rsmd = null;
   PreparedStatement ptmt = null;
   ResultSet rs = null;
 
   public InputPanel()
   {  
      setLayout(new BorderLayout());
      
      // add the display
      //display = new JTextField("0");
      display = new Editor();
      //display.setEditable(true);
      add(display, BorderLayout.NORTH);     
      
      // add the buttons in a n x 1 grid
      panel = new JPanel();
      panel.setLayout(new GridLayout(4, 4));

      //status_One = new JRadioButton("一");   
      status_One = new JButton("一");   
      status_One.setActionCommand("一");
      status_One.addActionListener(this);

      status_Two = new JButton("二");
      status_Two.setActionCommand("二");
      status_Two.addActionListener(this);

      status_Three = new JButton("三");
      status_Three.setActionCommand("三");
      status_Three.addActionListener(this);

      status_Four = new JButton("四");
      status_Four.setActionCommand("四");
      status_Four.addActionListener(this);

      status_Five = new JButton("五");
      status_Five.setActionCommand("五");
      status_Five.addActionListener(this);

      status_Six = new JButton("六");
      status_Six.setActionCommand("六");
      status_Six.addActionListener(this);

      status_Seven = new JButton("七");
      status_Seven.setActionCommand("七");
      status_Seven.addActionListener(this);

      status_Eight = new JButton("八");
      status_Eight.setActionCommand("八");
      status_Eight.addActionListener(this);

      status_Nine = new JButton("九");
      status_Nine.setActionCommand("九");
      status_Nine.addActionListener(this);

      status_Ten = new JButton("十");
      status_Ten.setActionCommand("十");
      status_Ten.addActionListener(this);

      status_Hundred = new JButton("百");
      status_Hundred.setActionCommand("百");
      status_Hundred.addActionListener(this);

      status_Thousand = new JButton("千");
      status_Thousand.setActionCommand("千");
      status_Thousand.addActionListener(this);

      status_Wan = new JButton("万");
      status_Wan.setActionCommand("万");
      status_Wan.addActionListener(this);

      status_Yi = new JButton("亿");
      status_Yi.setActionCommand("亿");
      status_Yi.addActionListener(this);

      status_Zero = new JButton("零");
      status_Zero.setActionCommand("零");
      status_Zero.addActionListener(this);

      status_Delete = new JButton("<-");
      status_Delete.setActionCommand("<-");
      status_Delete.addActionListener(this);

      buttonDone = new JButton("输入结束");
      buttonDone.setActionCommand("输入结束");
      buttonDone.addActionListener(this);
      /*
      status_group = new ButtonGroup();
      status_group.add(status_One);
      status_group.add(status_Two);   
      */
      panel.add(status_One);
      panel.add(status_Two);
      panel.add(status_Three);
      panel.add(status_Four);
      panel.add(status_Five);
      panel.add(status_Six);
      panel.add(status_Seven);
      panel.add(status_Eight);
      panel.add(status_Nine);
      panel.add(status_Ten);
      panel.add(status_Hundred);
      panel.add(status_Thousand);
      panel.add(status_Wan);
      panel.add(status_Yi);
      panel.add(status_Zero);
      panel.add(status_Delete);

      add(panel, BorderLayout.CENTER);
      add(buttonDone, BorderLayout.SOUTH);
   }

   public void actionPerformed(ActionEvent e) {
      String command = e.getActionCommand(); 
      String tmpStr = "";
      for (int i=0; i<strs.length; i++){
         if (command.equals(strs[i])) {
            tmpStr = display.getText() + command;
     	    display.setText(tmpStr);
            System.out.println("result = " + display.getText());
         }
      }
      if (command.equals("<-")){
         tmpStr = display.getText();
         if (tmpStr.length() > 0){
            tmpStr = tmpStr.substring(0,tmpStr.length()-1);
            display.setText(tmpStr);
            System.out.println("result = " + display.getText());
         }
      }
      if (command.equals("输入结束")){
         tmpStr = display.getText();
         try{
            connect();                        
            ptmt = dbCon.prepareStatement("update hintMatch set term2=? where term1=?");
            ptmt.clearParameters();
            ptmt.setString(1, tmpStr); 
            ptmt.setString(2, "input");            
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

   private boolean connect() throws ClassNotFoundException, SQLException
    {
       Class.forName("org.hsqldb.jdbcDriver");
       dbCon = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/xdb", "SA", "");
       return true;     
    }   
}

   class Editor extends JTextArea implements MouseListener, CaretListener {
       private boolean currentWork = false;

       Editor(){
          super();
          //addMouseListener(this);
          //addCaretListener(this);
       }

       public void setCurrentWork(boolean currentWork){
          this.currentWork = currentWork;
       }

       public void mouseClicked(MouseEvent e){          
          
          Component component = e.getComponent();
          if (component instanceof JTextArea) {
             JTextArea area = (JTextArea)component;
             
          }
          /*
          String text = getText();     
          String text2 = text;     
          int pos = getCaretPosition();
          text2 = text2.substring(0,pos);
          int posLastReturn = text2.lastIndexOf("\n"); 
          String colText = "col = " + (pos-posLastReturn);         

          int count = 1;
          pos = text2.indexOf("\n");
          while(pos != -1){
             count ++;
             text2 = text2.substring(pos+1);
             pos = text2.indexOf("\n");
          }
          //System.out.println("Line number = " + count + "::" + colText);
          status.setText("Line number = " + count + "::" + colText);
          */
       }

       public void caretUpdate(CaretEvent e){ 
          String text = getText();       
          /*
          String text2 = text;
          int pos = getCaretPosition();
          text2 = text2.substring(0,pos);
          int posLastReturn = text2.lastIndexOf("\n"); 
          String colText = "col = " + (pos-posLastReturn);         

          int count = 1;
          pos = text2.indexOf("\n");
          while(pos != -1){
             count ++;
             text2 = text2.substring(pos+1);
             pos = text2.indexOf("\n");
          }
          //System.out.println("Line number = " + count + "::" + colText);
          status.setText("Line number = " + count + "::" + colText);
          */
       }

       public void mousePressed(MouseEvent e){

       }

       public void mouseEntered(MouseEvent e){}
       public void mouseExited(MouseEvent e){}       
       public void mouseReleased(MouseEvent e){}  
       
    }


