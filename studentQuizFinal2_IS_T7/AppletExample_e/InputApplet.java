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

   //private String[] strs = new String[]{"һ"; "��"; "��"; "��"; "��"; "��"; "��"; "��"; "��"; "ʮ"; "��"; "ǧ"; "��"; "��"; "��"};
   private String[] strs = new String[]{"һ", "��", "��", "��", "��", "��", "��", "��", "��", "ʮ", "��", "ǧ", "��", "��", "��"};

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

      //status_One = new JRadioButton("һ");   
      status_One = new JButton("һ");   
      status_One.setActionCommand("һ");
      status_One.addActionListener(this);

      status_Two = new JButton("��");
      status_Two.setActionCommand("��");
      status_Two.addActionListener(this);

      status_Three = new JButton("��");
      status_Three.setActionCommand("��");
      status_Three.addActionListener(this);

      status_Four = new JButton("��");
      status_Four.setActionCommand("��");
      status_Four.addActionListener(this);

      status_Five = new JButton("��");
      status_Five.setActionCommand("��");
      status_Five.addActionListener(this);

      status_Six = new JButton("��");
      status_Six.setActionCommand("��");
      status_Six.addActionListener(this);

      status_Seven = new JButton("��");
      status_Seven.setActionCommand("��");
      status_Seven.addActionListener(this);

      status_Eight = new JButton("��");
      status_Eight.setActionCommand("��");
      status_Eight.addActionListener(this);

      status_Nine = new JButton("��");
      status_Nine.setActionCommand("��");
      status_Nine.addActionListener(this);

      status_Ten = new JButton("ʮ");
      status_Ten.setActionCommand("ʮ");
      status_Ten.addActionListener(this);

      status_Hundred = new JButton("��");
      status_Hundred.setActionCommand("��");
      status_Hundred.addActionListener(this);

      status_Thousand = new JButton("ǧ");
      status_Thousand.setActionCommand("ǧ");
      status_Thousand.addActionListener(this);

      status_Wan = new JButton("��");
      status_Wan.setActionCommand("��");
      status_Wan.addActionListener(this);

      status_Yi = new JButton("��");
      status_Yi.setActionCommand("��");
      status_Yi.addActionListener(this);

      status_Zero = new JButton("��");
      status_Zero.setActionCommand("��");
      status_Zero.addActionListener(this);

      status_Delete = new JButton("<-");
      status_Delete.setActionCommand("<-");
      status_Delete.addActionListener(this);

      buttonDone = new JButton("�������");
      buttonDone.setActionCommand("�������");
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
      if (command.equals("�������")){
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


