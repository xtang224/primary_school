/**
   @version 1.30 2000-05-12
   @author Cay Horstmann
*/
import java.awt.geom.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class ComApplet extends JApplet
{  
   public void init()
   {  
      Container contentPane = getContentPane();
      contentPane.add(new ComLine());
     
   }  
}

class ComLine extends JPanel implements ActionListener{
   public JLabel tLabel;
   public JTextArea tArea;
   public JButton button,button2;
   public JPanel buttonPanel;
   public JPanel northPanel;
   public JScrollPane scroller; 
   public JViewport port; 
   public String EXECUTE_QUERY = "作文完成，请点击这里提交";
   public String EXECUTE_UPDATE = "你已经成功提交作文，请关闭这个页面，返回原页面后直接提交即可";

   public Connection dbCon;
   public PreparedStatement ptmt;
   public ResultSet rs;

   public String result = "";
   private static String target = "50"; 

   public ComLine(){
      setLayout(new BorderLayout());

      /*
      button2 = new JButton(EXECUTE_UPDATE);
      button2.setActionCommand(EXECUTE_UPDATE);
      button2.addActionListener(this);

      buttonPanel = new JPanel();
      buttonPanel.setLayout(new GridLayout(2,1));
      buttonPanel.add(button);
      //buttonPanel.add(button2);
      */
      tLabel = new JLabel();    

      add(tLabel, BorderLayout.NORTH);

      tArea = new JTextArea();
      scroller = new JScrollPane();
      port = scroller.getViewport();
      port.add(tArea);
      add(scroller, BorderLayout.CENTER); 

      button = new JButton(EXECUTE_QUERY);
      button.setActionCommand(EXECUTE_QUERY);
      button.addActionListener(this);
      add(button, BorderLayout.SOUTH);

      try{       
         connect();                        
         ptmt = dbCon.prepareStatement("select term2 from hintMatch where term1=?");
         ptmt.clearParameters();
         ptmt.setString(1, "compositionStatement"); 
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

      tLabel.setText(target);
   }

   public void actionPerformed(ActionEvent e) {
      String command = e.getActionCommand(); 
      if (command.equals(EXECUTE_QUERY)) {
         result = tArea.getText();
         try{
            connect();
            ptmt = dbCon.prepareStatement("update hintMatch set term2=? where term1=?");
            ptmt.clearParameters();
            ptmt.setString(1, result); 
            ptmt.setString(2, "compositionYourAnswer");    
            ptmt.executeUpdate();

            tLabel.setText(EXECUTE_UPDATE);
         }catch (SQLException ex){
            ex.printStackTrace();
         }catch (ClassNotFoundException ex){
            ex.printStackTrace();
         }finally{
            try{
               if (rs != null) rs.close();               
               if (dbCon != null) dbCon.close();
            }catch (SQLException ex){
               ex.printStackTrace();
            }
         }
      }
   }

   public static void main(String[] args) {        
      javax.swing.SwingUtilities.invokeLater(new Runnable() {
         public void run() {
            createAndShowGUI();
         }
      });
   }

   /*********/

   private boolean connect() throws ClassNotFoundException, SQLException
    {
      Class.forName("org.hsqldb.jdbcDriver");
      dbCon = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/xdb", "SA", "");
      return true;     
    }
    
    private ResultSet execSQL(String sql) throws SQLException
    {
       Statement s = dbCon.createStatement();
       ResultSet r = s.executeQuery(sql);
       return ( r == null) ? null : r ;
    }
    
    private void execUpdateSQL(String sql) throws SQLException
    {
       Statement s = dbCon.createStatement();
       s.executeUpdate(sql);
    }

   private static void createAndShowGUI(){
     //Make sure we have nice window decorations.
        JFrame.setDefaultLookAndFeelDecorated(true);

        //Create and set up the window.
        JFrame frame = new JFrame("DirectOpenOfficePanel3_ec_linux");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        ComLine newContentPane = new ComLine();
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);

        //Display the window.
        frame.pack();
        frame.setVisible(true);     
  }

}
