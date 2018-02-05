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

public class TableApplet11 extends JApplet
{  
   public void init()
   {  
      Container contentPane = getContentPane();
      contentPane.add(new Table11Panel());
   }
}

class Table11Panel extends JPanel
 implements ActionListener
{  
    private boolean DEBUG = false;
    private JLabel label;
    private JButton button;
    private String GETINPUT = "������������пհ״�����ǡ�������֣�Ȼ�����˰�ť�����գ����رմ�ҳ�淵��ԭҳ�������գ������ύ�𰸡�";
    private String INPUTNOTDONE = "�㻹û���������еĿգ��������գ����رմ�ҳ�淵��ԭҳ�������գ������ύ�𰸡�";
    private String INPUTDONE = "���Ѿ��������еĿգ���رմ�ҳ�淵��ԭҳ�棬�ٵ�������հ�ť�������ύ�𰸡�";
    private MyTableModel myTable;

    private String target="";

    Connection dbCon = null;
    PreparedStatement ptmt = null;
    ResultSet rs = null;
    boolean done = false;
    boolean done1 = false;
    boolean done2 = false;
    boolean done3 = false;
    boolean done4 = false;
    boolean done5 = false; 
    boolean done6 = false;

    public Table11Panel() {
        //super(new GridLayout(2,1));
        super(new BorderLayout());

        label = new JLabel(target);
        add(label, BorderLayout.NORTH);

        myTable = new MyTableModel();
        JTable table = new JTable(myTable);
        table.setPreferredScrollableViewportSize(new Dimension(500, 500));
        //table.setFillsViewportWidth(true);
        table.setFillsViewportHeight(true);

        //Create the scroll pane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(table);

        //Set up column sizes.
        initColumnSizes(table);

        //Fiddle with the Sport column's cell editors/renderers.
        //setUpSportColumn(table, table.getColumnModel().getColumn(2));

        //Add the scroll pane to this panel.
        add(scrollPane, BorderLayout.CENTER);

        button = new JButton(GETINPUT);
        button.setActionCommand(GETINPUT);
        button.addActionListener(this);
        add(button, BorderLayout.SOUTH);   

        done = false;
        done1 = false;
        done2 = false;
        done3 = false;
        done4 = false;
        done5 = false;
        done6 = false;
        
        try{       
           connect();                        
           ptmt = dbCon.prepareStatement("select term2 from hintMatch where term1=?");
           ptmt.clearParameters();
           ptmt.setString(1, "tableHint2"); 
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
   
        label.setText(target);          
    }

    public void actionPerformed(ActionEvent e) {
       String command = e.getActionCommand(); 
       if (command.equals(GETINPUT)) {
          Integer class1Value = (Integer)myTable.getValueAt(0,3);
          Integer class2Value = (Integer)myTable.getValueAt(1,2);
          Integer class3Value = (Integer)myTable.getValueAt(2,1);
          //String class4Value = (String)myTable.getValueAt(1,3);
          //String class5Value = (String)myTable.getValueAt(2,2);
          //String class6Value = (String)myTable.getValueAt(2,3);
          //Integer class4Value = (Integer)myTable.getValueAt(0,4);
          if (class1Value.intValue() != -1){
             done1 = true;
             if (class2Value.intValue() != -1){
                done2 = true;
                if (class3Value.intValue() != -1){
                   done3 = true;
                   button.setText(INPUTDONE);
                   System.out.println("You have finished doing it!");
                   done = true;                                          
                }
             }
          }

          if (done){
             try{       
                connect();    
                target = "Table"+";"+class1Value+";"+class2Value+";"+class3Value;
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
            //column.setPreferredWidth(Math.max(headerWidth, 100));
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
        private String[] columnNames = {"Ʒ��", "����/Ԫ", "����/��", "�ܼ�/Ԫ"};
        private Object[][] data = {	    
            {"����", new Integer(80), new Integer(25), new Integer(-1)},
	    {"����", new Integer(25), new Integer(-1), new Integer(750)},
            {"����", new Integer(-1), new Integer(28), new Integer(560)}
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

   