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

public class TableApplet_2_6 extends JApplet
{  
   public void init()
   {  
      Container contentPane = getContentPane();
      contentPane.add(new TablePanel_2_6());
   }
}

class TablePanel_2_6 extends JPanel
 implements ActionListener
{  
    private boolean DEBUG = false;
    private JButton button;
    private String GETINPUT = "请在上面表格中-1处填入恰当的数字，然后点击此按钮完成填空，最后关闭此页面返回原页面完成填空，而后提交答案。";
    private String INPUTNOTDONE = "你还没有填完所有的空，请继续填空，最后关闭此页面返回原页面完成填空，而后提交答案。";
    private String INPUTDONE_1 = "你已经填完所有的空，请关闭此页面返回原页面并填：";
    private String INPUTDONE_2 = "，而后提交答案。";
    private MyTableModel myTable;

    private String target;

    Connection dbCon = null;
    PreparedStatement ptmt = null;
    ResultSet rs = null;
    boolean done = false;
    boolean done1 = false;
    boolean done2 = false;
    boolean done3 = false;

    public TablePanel_2_6() {
        super(new GridLayout(2,1));

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
        add(scrollPane);

        button = new JButton(GETINPUT);
        button.setActionCommand(GETINPUT);
        button.addActionListener(this);
        add(button);   

        done = false;
        done1 = false;
        done2 = false;
        done3 = false;
        /*
        try{       
           connect();                        
           ptmt = dbCon.prepareStatement("select term2 from hintMatch where term1=?");
           ptmt.clearParameters();
           ptmt.setString(1, "table"); 
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
        */
    }

    public void actionPerformed(ActionEvent e) {
       String command = e.getActionCommand(); 
       if (command.equals(GETINPUT)) {
          Integer carValue = (Integer)myTable.getValueAt(0,2);
          Integer truckValue = (Integer)myTable.getValueAt(0,4);
          Integer busValue = (Integer)myTable.getValueAt(1,1);
          Integer jeepValue = (Integer)myTable.getValueAt(1,3); 
          if (carValue.intValue()!=-1){
             done1 = true;
             if (truckValue.intValue()!=-1){
                done2 = true;
                if (busValue.intValue()!=-1){
                   System.out.println("You have finished doing it!");
                   done3 = true;
                   done = true;
                }
             }
          } 
          
          if (done){
             target = carValue+";"+truckValue+";"+busValue+";"+jeepValue;
             button.setText(INPUTDONE_1+target+INPUTDONE_2);
          }else if (done1 || done2 || done3)
             button.setText(INPUTNOTDONE);

          if (done){                       
             try{       
                connect(); 
                
                /*               
                ptmt = dbCon.prepareStatement("select term2 from hintMatch where term1=?");
                ptmt.clearParameters();
                ptmt.setString(1, "table"); 
                rs = ptmt.executeQuery();
                while (rs.next()){
                   target = rs.getString(1);
                }
                */
                ptmt = dbCon.prepareStatement("update hintMatch set term2=? where term1=?");
                ptmt.clearParameters();
                ptmt.setString(1, target); 
                ptmt.setString(2, "input");
                ptmt.executeUpdate();
                ptmt = dbCon.prepareStatement("update hintMatch set term2=? where term1=?");
                ptmt.clearParameters();
                ptmt.setString(1, "done"); 
                ptmt.setString(2, "tableHint");
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

        for (int i = 0; i < 5; i++) {
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
        private String[] columnNames = {"", "", "", "", ""};
        private Object[][] data = {
	    {new String("数量/千克"), new Integer(2), new Integer(-1), new Integer(8), new Integer(-1)},
	    {new String("总价/元"), new Integer(-1), new Integer(150), new Integer(-1), new Integer(250)}
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
            if (col < 0) {
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

