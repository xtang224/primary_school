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

import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

public class GameApplet7 extends JApplet
{  
   public void init()
   {  
      Container contentPane = getContentPane();
      contentPane.add(new Game7Panel());
   }
}

class Game7Panel extends JPanel
 implements ActionListener
{  
    private boolean DEBUG = false;
    private JTextField field, field2, field3;
    private JLabel label, label2, label3, label4, label5;
    private JPanel labelPanel, buttonPanel;
    private JPanel label3Panel, label4Panel, label5Panel;
    private JButton button, button2, button3, button4, button5;
    private String problemHint = "你和对手轮流报数，每次只能报1,2,3或4，把两人报的所有数加起来，谁报数后和是59，谁就获胜";
    private String GETINPUT = "请按照提示完成对策，然后点击此按钮完成题目，最后关闭此页面返回原页面完成填空，而后提交答案。";
    private String INPUTNOTDONE = "你还没有完成对策，请继续填写你的对策，最后关闭此页面返回原页面完成填空，而后提交答案。";
    private String INPUTDONE = "你已经完成对策，请关闭此页面返回原页面，再点击完成填空按钮，而后提交答案。";
    private String RANDOM = "点击此按钮决定谁先走。";
    private MyTableModel myTable;

    private String target;
    private String opponentInput;
    private String yourInput;

    Connection dbCon = null;
    PreparedStatement ptmt = null;
    ResultSet rs = null;
    boolean done = false;
    boolean done1 = false;
    boolean done2 = false;
    boolean done3 = false;
    boolean win = false;
    boolean isEnd = false;

    private int count = 0;
    private int maxGameSize = 20;
    private int gameTotal = 59;
    private int gameCritical = 8;
    private int gameSum = 0;
    private int step = 5;
    private int maxInput = 4;
    private int minInput = 1;

    public Game7Panel() {
        super(new BorderLayout());
        
        myTable = new MyTableModel();
        JTable table = new JTable(myTable);
        table.setPreferredScrollableViewportSize(new Dimension(500, 70));
        table.setFillsViewportHeight(true);

        //Create the scroll pane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(table);

        //Set up column sizes.
        initColumnSizes(table);

        //Fiddle with the Sport column's cell editors/renderers.
        //setUpSportColumn(table, table.getColumnModel().getColumn(2));

        //Add the scroll pane to this panel.
        add(scrollPane, BorderLayout.CENTER);

        button = new JButton("点击此按钮提交你的对策");
        button.setActionCommand("点击此按钮提交你的对策");
        button.addActionListener(this);
 
        button2 = new JButton("点击此按钮提交对手的对策");
        button2.setActionCommand("点击此按钮提交对手的对策");
        button2.addActionListener(this);       

        button3 = new JButton(RANDOM);
        button3.setActionCommand(RANDOM);
        button3.addActionListener(this);

        button4 = new JButton("基本数据输入完毕");
        button4.setActionCommand("基本数据输入完毕");
        button4.addActionListener(this);

        button5 = new JButton(GETINPUT);
        button5.setActionCommand(GETINPUT);
        button5.addActionListener(this);

        buttonPanel = new JPanel(new GridLayout(5,1));
        buttonPanel.add(button);
        buttonPanel.add(button2);
        buttonPanel.add(button3); 
        buttonPanel.add(button4);
        buttonPanel.add(button5); 
        add(buttonPanel, BorderLayout.SOUTH);  

        label = new JLabel(problemHint); 
        label2 = new JLabel("");         

        labelPanel = new JPanel(new GridLayout(5,1));
    
        label3Panel = new JPanel(new GridLayout(1,2));
        label3 = new JLabel("请填入允许输入的最小数字：");
        field = new JTextField();
        label3Panel.add(label3); 
        label3Panel.add(field);
        labelPanel.add(label3Panel);

        label4Panel = new JPanel(new GridLayout(1,2));
        label4 = new JLabel("请填入允许输入的最大数字：");
        field2 = new JTextField();
        label4Panel.add(label4); 
        label4Panel.add(field2);
        labelPanel.add(label4Panel);

        label5Panel = new JPanel(new GridLayout(1,2));
        label5 = new JLabel("请填入最终的和：");
        field3 = new JTextField();
        label5Panel.add(label5); 
        label5Panel.add(field3);
        labelPanel.add(label5Panel);

        labelPanel.add(label);
        labelPanel.add(label2);
        add(labelPanel, BorderLayout.NORTH);   

        done = false;
        done1 = false;
        done2 = false;
        done3 = false;
        isEnd = false;
        count = 0;
        
        yourInput = "";
        opponentInput = "";
        setYourInput();    
        setOpponentInput();  

        try{       
          connect();                        
          ptmt = dbCon.prepareStatement("select term2 from hintMatch where term1=?");
          ptmt.clearParameters();
          ptmt.setString(1, "gameHint2"); 
          rs = ptmt.executeQuery();
          while (rs.next()){
             problemHint = rs.getString(1);
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

        //label.setText(problemHint);  
        gameCritical = gameTotal % step;
        System.out.println("gameCritical =" + gameCritical); 
        if (gameCritical==0)
           gameCritical = step;
    }

    public void actionPerformed(ActionEvent e) {
       String command = e.getActionCommand(); 
       if (command.equals("点击此按钮提交你的对策")){
          done1 = true;
          yourInput = "";
          Integer tmpInteger;
          for (int i=0; i<maxGameSize; i++){
             tmpInteger =  (Integer)myTable.getValueAt(i,1);
             if (tmpInteger.intValue()!=0){
                if (tmpInteger.intValue()<minInput || tmpInteger.intValue()>maxInput){
                   label2.setText("你只能填1-"+ maxInput +"，请重新提交你的对策");
                   return;
                }
                label2.setText("你提交的对策是1-"+ maxInput + "，符合要求");
                yourInput += tmpInteger + ";";
             }
          }
          System.out.println("in branch 1, before setYourInput(), yourInput =" + yourInput); 
          setYourInput();
          opponentInput = "";          
          for (int i=0; i<maxGameSize; i++){
             tmpInteger =  (Integer)myTable.getValueAt(i,2);
             if (tmpInteger.intValue()!=0){                
                opponentInput += tmpInteger + ";";
             }
          }
          System.out.println("in branch 1, before setOpponentInput(), opponentInput =" + opponentInput); 
          setOpponentInput();

          gameSum = 0;
          String[] strs = yourInput.split(";"); 
          if (!yourInput.equals(""))
             for (int i=0; i<strs.length; i++)
                gameSum += Integer.parseInt(strs[i].trim());
        
          String[] strs2 = opponentInput.split(";"); 
          if (!opponentInput.equals(""))
             for (int i=0; i<strs2.length; i++)
                gameSum += Integer.parseInt(strs2[i].trim()); 

          if (gameSum == gameTotal){
             label2.setText("游戏结束，请点击最下面一个按钮完成这道题。");
             done = true;
             win = true;  
             myTable.setValueAt(new String("你"),3,3);
          }else if (gameSum > gameTotal){
             label2.setText("所有的数加总之和不能超过" + gameTotal + "。游戏结束，请点击最下面一个按钮完成这道题。");
             done = true;
             win = false;  
             myTable.setValueAt(new String("对手"),3,3);
          }

          label2.setText("当前的和是：" + gameSum + "");
       }else if (command.equals("点击此按钮提交对手的对策")){
          //getYourInput();
          //getOpponentInput();
          yourInput = "";
          Integer tmpInteger;
          for (int i=0; i<maxGameSize; i++){
             tmpInteger =  (Integer)myTable.getValueAt(i,1);
             if (tmpInteger.intValue()!=0){
                if (tmpInteger.intValue()<minInput || tmpInteger.intValue()>maxInput){
                   label2.setText("对手只能填1-"+ maxInput +"，请重新提交你的对策");
                   return;
                }
                label2.setText("对手提交的对策是1-"+ maxInput + "，符合要求");
                yourInput += tmpInteger + ";";
             }
          }
          System.out.println("in branch 1, before setYourInput(), yourInput =" + yourInput); 
          setYourInput();
          opponentInput = "";          
          for (int i=0; i<maxGameSize; i++){
             tmpInteger =  (Integer)myTable.getValueAt(i,2);
             if (tmpInteger.intValue()!=0){                
                opponentInput += tmpInteger + ";";
             }
          }
          System.out.println("in branch 1, before setOpponentInput(), opponentInput =" + opponentInput); 
          setOpponentInput();

          gameSum = 0;
          String[] strs = yourInput.split(";"); 
          if (!yourInput.equals(""))
             for (int i=0; i<strs.length; i++)
                gameSum += Integer.parseInt(strs[i].trim());
        
          String[] strs2 = opponentInput.split(";"); 
          if (!opponentInput.equals(""))
             for (int i=0; i<strs2.length; i++)
                gameSum += Integer.parseInt(strs2[i].trim()); 

          if (gameSum == gameTotal){
             label2.setText("游戏结束，请点击最下面一个按钮完成这道题。");
             done = true;
             win = false;  
             myTable.setValueAt(new String("对手"),3,3);
          }else if (gameSum > gameTotal){
             label2.setText("所有的数加总之和不能超过" + gameTotal + "。游戏结束，请点击最下面一个按钮完成这道题。");
             done = true;
             win = true;  
             myTable.setValueAt(new String("你"),3,3);
          }

          label2.setText("当前的和是：" + gameSum + "");
       }else if (command.equals(RANDOM)) { 
           if (Math.random()>0.5)
              label2.setText("你先走。");
           else
              label2.setText("对手先走。");
       }else if (command.equals("基本数据输入完毕")) { 
           String str = field.getText().trim();
           minInput = Integer.parseInt(str);
           str = field2.getText().trim();
           maxInput = Integer.parseInt(str);
           str = field3.getText().trim();
           gameTotal = Integer.parseInt(str);
           if (minInput>maxInput || maxInput>=gameTotal){
              minInput = 0; maxInput = 0; gameTotal = 100;
              label.setText("允许输入的最小的数必须小于或等于允许输入的最大的数，同时，你输入的最终的和不能一下子就达到"); 
              return;
           }
           label.setText("你和对手轮流报数，每次只能报" + minInput + "-" + maxInput + "，把两人报的所有数加起来，谁报数后和是" + gameTotal + "，谁就获胜");
       }else if (command.equals(GETINPUT)) {        
          System.out.println("You have finished doing it!");
               
          if (done)
             button5.setText(INPUTDONE);
          else if (done1 || done2 || done3)
             button5.setText(INPUTNOTDONE);

          if (done){                       
             try{       
                connect(); 
                //target = "Table"+";"+carValue+";"+truckValue+";"+busValue;
                               
                ptmt = dbCon.prepareStatement("select term2 from hintMatch where term1=?");
                ptmt.clearParameters();
                ptmt.setString(1, "game"); 
                rs = ptmt.executeQuery();
                while (rs.next()){
                   target = rs.getString(1);
                }
                
                ptmt = dbCon.prepareStatement("update hintMatch set term2=? where term1=?");
                ptmt.clearParameters();
                if (win)
                   ptmt.setString(1, target); 
                else
                   ptmt.setString(1, "lose"); 
                ptmt.setString(2, "input");
                ptmt.executeUpdate();
                ptmt = dbCon.prepareStatement("update hintMatch set term2=? where term1=?");
                ptmt.clearParameters();
                ptmt.setString(1, "done"); 
                ptmt.setString(2, "gameHint");
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
    }  

    private void getYourInput(){
       try{       
           connect(); 

           ptmt = dbCon.prepareStatement("select term2 from hintMatch where term1=?");
           ptmt.clearParameters();
           ptmt.setString(1, "gameYourInput"); 
           rs = ptmt.executeQuery();
           while (rs.next()){
             yourInput = rs.getString(1);
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

    private void getOpponentInput(){
       try{       
           connect();           

           ptmt = dbCon.prepareStatement("select term2 from hintMatch where term1=?");
           ptmt.clearParameters();
           ptmt.setString(1, "gameOpponentInput"); 
           rs = ptmt.executeQuery();
           while (rs.next()){
             opponentInput = rs.getString(1);
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

    private void setYourInput(){
       try{       
           connect();           

           ptmt = dbCon.prepareStatement("update hintMatch set term2=? where term1=?");
           ptmt.clearParameters();
           ptmt.setString(1, yourInput); 
           ptmt.setString(2, "gameYourInput");
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

    private void setOpponentInput(){
       try{       
           connect();           

           ptmt = dbCon.prepareStatement("update hintMatch set term2=? where term1=?");
           ptmt.clearParameters();
           ptmt.setString(1, opponentInput); 
           ptmt.setString(2, "gameOpponentInput");
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

    /*
     * This method picks good column sizes.
     * If all column heads are wider than the column's cells'
     * contents, then you can just use column.sizeWidthToFit().
     */
    private void initColumnSizes(JTable table) {
        MyTableModel model = (MyTableModel)table.getModel();
        TableColumn column = null;
        Component comp = null;
        int headerWidth = 0;
        int cellWidth = 0;
        Object[] longValues = model.longValues;
        TableCellRenderer headerRenderer =
            table.getTableHeader().getDefaultRenderer();

        for (int i = 0; i < 4; i++) {
            column = table.getColumnModel().getColumn(i);

            comp = headerRenderer.getTableCellRendererComponent(
                                 null, column.getHeaderValue(),
                                 false, false, 0, 0);
            headerWidth = comp.getPreferredSize().width;

            comp = table.getDefaultRenderer(model.getColumnClass(i)).
                             getTableCellRendererComponent(
                                 table, longValues[i],
                                 false, false, 0, i);
            cellWidth = comp.getPreferredSize().width;

            if (DEBUG) {
                System.out.println("Initializing width of column "
                                   + i + ". "
                                   + "headerWidth = " + headerWidth
                                   + "; cellWidth = " + cellWidth);
            }

            column.setPreferredWidth(Math.max(headerWidth, cellWidth));
        }
    }

    public void setUpSportColumn(JTable table,
                                 TableColumn sportColumn) {
        //Set up the editor for the sport cells.
        JComboBox comboBox = new JComboBox();
        comboBox.addItem("Snowboarding");
        comboBox.addItem("Rowing");
        comboBox.addItem("Knitting");
        comboBox.addItem("Speed reading");
        comboBox.addItem("Pool");
        comboBox.addItem("None of the above");
        sportColumn.setCellEditor(new DefaultCellEditor(comboBox));

        //Set up tool tips for the sport cells.
        DefaultTableCellRenderer renderer =
                new DefaultTableCellRenderer();
        renderer.setToolTipText("Click for combo box");
        sportColumn.setCellRenderer(renderer);
    }

    class MyTableModel extends AbstractTableModel {
        private String[] columnNames = {"",
                                        "你的报数",
                                        "对手的报数",
                                        "胜者"};
        private Object[][] data = {
	    {"第一次", new Integer(0), new Integer(0), new String("")},
	    {"第二次", new Integer(0), new Integer(0), new String("")},
            {"第三次", new Integer(0), new Integer(0), new String("")},	    
            {"第四次", new Integer(0), new Integer(0), new String("")},
            {"第五次", new Integer(0), new Integer(0), new String("")},
	    {"第六次", new Integer(0), new Integer(0), new String("")},
            {"第七次", new Integer(0), new Integer(0), new String("")},	    
            {"第八次", new Integer(0), new Integer(0), new String("")},
            {"第九次", new Integer(0), new Integer(0), new String("")},	    
            {"第十次", new Integer(0), new Integer(0), new String("")},
            {"第十一次", new Integer(0), new Integer(0), new String("")},
            {"第十二次", new Integer(0), new Integer(0), new String("")},	    
            {"第十三次", new Integer(0), new Integer(0), new String("")},
            {"第十四次", new Integer(0), new Integer(0), new String("")},	    
            {"第十五次", new Integer(0), new Integer(0), new String("")},
            {"第十六次", new Integer(0), new Integer(0), new String("")},
            {"第十七次", new Integer(0), new Integer(0), new String("")},	    
            {"第十八次", new Integer(0), new Integer(0), new String("")},
            {"第十九次", new Integer(0), new Integer(0), new String("")},	    
            {"第二十次", new Integer(0), new Integer(0), new String("")}    
        };

        public final Object[] longValues = {"Jane", "Kathy",
                                            "None of the above",
                                            new Integer(20), Boolean.TRUE};

        public int getColumnCount() {
            return columnNames.length;
        }

        public int getRowCount() {
            return data.length;
        }

        public String getColumnName(int col) {
            return columnNames[col];
        }

        public Object getValueAt(int row, int col) {
            return data[row][col];
        }

        /*
         * JTable uses this method to determine the default renderer/
         * editor for each cell.  If we didn't implement this method,
         * then the last column would contain text ("true"/"false"),
         * rather than a check box.
         */
        public Class getColumnClass(int c) {
            return getValueAt(0, c).getClass();
        }

        /*
         * Don't need to implement this method unless your table's
         * editable.
         */
        public boolean isCellEditable(int row, int col) {
            //Note that the data/cell address is constant,
            //no matter where the cell appears onscreen.
            if (col < 1) {
                return false;
            } else {
                return true;
            }
        }

        /*
         * Don't need to implement this method unless your table's
         * data can change.
         */
        public void setValueAt(Object value, int row, int col) {
            if (DEBUG) {
                System.out.println("Setting value at " + row + "," + col
                                   + " to " + value
                                   + " (an instance of "
                                   + value.getClass() + ")");
            }

            data[row][col] = value;
            fireTableCellUpdated(row, col);

            if (DEBUG) {
                System.out.println("New value of data:");
                printDebugData();
            }
        }

        private void printDebugData() {
            int numRows = getRowCount();
            int numCols = getColumnCount();

            for (int i=0; i < numRows; i++) {
                System.out.print("    row " + i + ":");
                for (int j=0; j < numCols; j++) {
                    System.out.print("  " + data[i][j]);
                }
                System.out.println();
            }
            System.out.println("--------------------------");
        }
    }   

   private boolean connect() throws ClassNotFoundException, SQLException
    {
       Class.forName("org.hsqldb.jdbcDriver");
       dbCon = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/xdb", "SA", "");
       return true;     
    }   
}

   
