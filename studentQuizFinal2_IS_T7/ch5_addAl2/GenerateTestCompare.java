import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.table.AbstractTableModel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.*;
import java.util.*;
import java.io.*;
import javax.swing.*;
import javax.swing.text.*;
import javax.swing.filechooser.*;
import java.text.*;

import java.io.*;
import java.sql.*;

//import hello.*;

public class GenerateTestCompare {
  
  Connection dbCon = null;  
  ResultSet rs = null;  
  Statement s = null;
  PreparedStatement stmt = null;

  private static final String NEW_LINE = "\n"; 
  private final String encoding = "GBK";
  private final String encoding2 = "UTF-8";
  private final String MATH_PATH = "E:\\eclipse_workspace\\Math\\res\\raw";
  private final String TOMCAT_PATH = "E:\\Tomcat_6\\webapps\\studentQuizFinal2_IS_T7";
  private String subPath1 = "\\generatedTest";
  private String subPath2 = "\\generatedTest2";
  private String subPath3 = "\\generatedTest3";
  private String subPath4 = "\\ch5_56";
  private String subPath5 = "\\ch5_addAll2";
  private String subAndroidPath = "\\android";
  private String comparePath = "\\compare";

  private String filePath = "";  
  private String result = "";

  private HashSet hs = new HashSet();
  private HashSet hs_tf = new HashSet();
  private HashSet mhs = new HashSet();
  private HashSet hs_fb = new HashSet();
  private HashSet hs_fdb = new HashSet();  
  private HashSet hs_m = new HashSet();

  private HashSet hs_used = new HashSet();
  private HashSet hs_tf_used = new HashSet();
  private HashSet mhs_used = new HashSet();
  private HashSet hs_fb_used = new HashSet();
  private HashSet hs_fdb_used = new HashSet();  
  private HashSet hs_m_used = new HashSet();

  private HashSet hs_str = new HashSet();
  private HashSet hs_tf_str = new HashSet();
  private HashSet mhs_str = new HashSet();//actually not needed
  private HashSet hs_fb_str = new HashSet();
  private HashSet hs_fdb_str = new HashSet();  
  private HashSet hs_m_str = new HashSet();//actually not needed

  private HashSet hs_android = new HashSet();
  private HashSet hs_tf_android = new HashSet();
  private HashSet mhs_android = new HashSet();//actually not needed
  private HashSet hs_fb_android = new HashSet();
  private HashSet hs_fdb_android = new HashSet();  
  private HashSet hs_m_android = new HashSet();//actually not needed
    
  public static void main(String[] args) {
     GenerateTestCompare aTest = new GenerateTestCompare();
     //aTest.initUsedSet();
     aTest.initCurrentSet();
     aTest.saveTestIdsCompare();
     aTest.initSetStr();
     aTest.initSetForAndroid();
     aTest.saveTestIdsForAndroid();
     //aTest.initTestPaper();
     //aTest.saveTestPaper();
  }
   
   public GenerateTestCompare(){}

   public void initCurrentSet(){
      filePath = TOMCAT_PATH + subPath5;
      hs = initSetFromPath(filePath + "\\" + "Questions2_test_used.txt");
      hs_tf = initSetFromPath(filePath + "\\" + "Choices2_test_used.txt");
      hs_fb = initSetFromPath(filePath + "\\" + "FillBlank2_test_used.txt");
      hs_fdb = initSetFromPath(filePath + "\\" + "FillDoubleBlanks2_test_used.txt");      
   }

   public void initUsedSet(){
      filePath = TOMCAT_PATH + subPath1;
      hs_used = initSetFromPath(filePath + "\\" + "Questions2_test_used.txt");
      hs_tf_used = initSetFromPath(filePath + "\\" + "Choices2_test_used.txt");
      hs_fb_used = initSetFromPath(filePath + "\\" + "FillBlank2_test_used.txt");
      hs_fdb_used = initSetFromPath(filePath + "\\" + "FillDoubleBlanks2_test_used.txt");
      hs_m_used = initSetFromPath(filePath + "\\" + "Multiply_test_used.txt");
   }

   public void initSet(){
     try{
        connect();

        rs = execSQL("select id from questions2_test where type='L'");
        while (rs.next()){
           int usedId = rs.getInt("id");
           hs.add(new Integer(usedId));
        }
        hs.removeAll(hs_used);
        //now we want to only get 5 questions for this generated test
        HashSet tmpSet = new HashSet();
        HashSet controlSet = new HashSet();
        Object[] obs = hs.toArray();
        for (int i=0; i<5; i++){
           int intRandom = (int)(Math.random() * hs.size());
           while(controlSet.contains(new Integer(intRandom)))
              intRandom = (int)(Math.random() * hs.size());
           controlSet.add(new Integer(intRandom));
           int intSelect = ((Integer)obs[intRandom]).intValue();
           tmpSet.add(new Integer(intSelect));
        }
        hs.clear();
        hs.addAll(tmpSet);

        rs = execSQL("select id from choices2_test where type='L'");
        while (rs.next()){
           int usedId = rs.getInt("id");
           hs_tf.add(new Integer(usedId));
        }
        hs_tf.removeAll(hs_tf_used);
        //now we want to only get 5 choices for this generated test
        tmpSet = new HashSet();
        controlSet = new HashSet();
        obs = hs_tf.toArray();
        for (int i=0; i<5; i++){
           int intRandom = (int)(Math.random() * hs_tf.size());
           while(controlSet.contains(new Integer(intRandom)))
              intRandom = (int)(Math.random() * hs_tf.size());
           controlSet.add(new Integer(intRandom));
           int intSelect = ((Integer)obs[intRandom]).intValue();
           tmpSet.add(new Integer(intSelect));
        }
        hs_tf.clear();
        hs_tf.addAll(tmpSet);

        rs = execSQL("select id from multiply_test where type='H'");
        while (rs.next()){
           int usedId = rs.getInt("id");
           hs_m.add(new Integer(usedId));
        } 
        hs_m.removeAll(hs_m_used);
        //now we want to only get 3 multiply for this generated test
        tmpSet = new HashSet();
        controlSet = new HashSet();
        obs = hs_m.toArray();
        for (int i=0; i<3; i++){
           int intRandom = (int)(Math.random() * hs_m.size());
           while(controlSet.contains(new Integer(intRandom)))
              intRandom = (int)(Math.random() * hs_m.size());
           controlSet.add(new Integer(intRandom));
           int intSelect = ((Integer)obs[intRandom]).intValue();
           tmpSet.add(new Integer(intSelect));
        }
        hs_m.clear();
        hs_m.addAll(tmpSet);

        rs = execSQL("select id from multipleQuestions2_test where type='H'");
        while (rs.next()){
           int usedId = rs.getInt("id");
           mhs.add(new Integer(usedId));
        }
        mhs.removeAll(mhs_used);
        //now we want to only get 3 multipleQuestions for this generated test
        tmpSet = new HashSet();
        controlSet = new HashSet();
        obs = mhs.toArray();
        for (int i=0; i<3; i++){
           int intRandom = (int)(Math.random() * mhs.size());
           while(controlSet.contains(new Integer(intRandom)))
              intRandom = (int)(Math.random() * mhs.size());
           controlSet.add(new Integer(intRandom));
           int intSelect = ((Integer)obs[intRandom]).intValue();
           tmpSet.add(new Integer(intSelect));
        }
        mhs.clear();
        mhs.addAll(tmpSet);

        rs = execSQL("select id from fillBlank2_test where type='M'");
        while (rs.next()){
           int usedId = rs.getInt("id");
           hs_fb.add(new Integer(usedId));
        } 
        hs_fb.removeAll(hs_fb_used);
        //now we want to only get 20 fillBlank2 for this generated test
        tmpSet = new HashSet();
        controlSet = new HashSet();
        obs = hs_fb.toArray();
        for (int i=0; i<20; i++){
           int intRandom = (int)(Math.random() * hs_fb.size());
           while(controlSet.contains(new Integer(intRandom)))
              intRandom = (int)(Math.random() * hs_fb.size());
           controlSet.add(new Integer(intRandom));
           int intSelect = ((Integer)obs[intRandom]).intValue();
           tmpSet.add(new Integer(intSelect));
        }
        hs_fb.clear();
        hs_fb.addAll(tmpSet);

        rs = execSQL("select id from fillDoubleBlanks2_test where type='H'");
        while (rs.next()){
           int usedId = rs.getInt("id");
           hs_fdb.add(new Integer(usedId));
        } 
        hs_fdb.removeAll(hs_fdb_used);
        //now we want to only get 20 fillDoubleBlanks2 for this generated test
        tmpSet = new HashSet();
        controlSet = new HashSet();
        obs = hs_fdb.toArray();
        for (int i=0; i<20; i++){
           int intRandom = (int)(Math.random() * hs_fdb.size());
           while(controlSet.contains(new Integer(intRandom)))
              intRandom = (int)(Math.random() * hs_fdb.size());
           controlSet.add(new Integer(intRandom));
           int intSelect = ((Integer)obs[intRandom]).intValue();
           tmpSet.add(new Integer(intSelect));
        }
        hs_fdb.clear();
        hs_fdb.addAll(tmpSet);

     }catch (ClassNotFoundException ce) {
        ce.printStackTrace();
     }catch (SQLException sqle) {
        sqle.printStackTrace();
     } finally {           
        try {
           if (rs != null) rs.close();
           if (s != null) s.close();                    
           if (dbCon != null) dbCon.close();
         } catch (SQLException e) {
           e.printStackTrace();
         }
     }     
   }

   public void saveTestIdsCompare(){
      result = "";
      Object[] obs = hs.toArray();
      Arrays.sort(obs);
      for (int i=0; i<obs.length; i++){
         Integer integer = (Integer)obs[i];
         result += integer.intValue() + ";";
      }
      filePath = TOMCAT_PATH + subPath5 + "\\compare" + "\\" + "Questions2_test_used.txt";
      saveFile();

      result = "";
      obs = hs_tf.toArray();
      Arrays.sort(obs);
      for (int i=0; i<obs.length; i++){
         Integer integer = (Integer)obs[i];
         result += integer.intValue() + ";";
      }
      filePath = TOMCAT_PATH + subPath5 + "\\compare" + "\\" + "Choices2_test_used.txt";
      saveFile();      

      result = "";
      obs = hs_fb.toArray();
      Arrays.sort(obs);
      for (int i=0; i<obs.length; i++){
         Integer integer = (Integer)obs[i];
         result += integer.intValue() + ";";
      }
      filePath = TOMCAT_PATH + subPath5 + "\\compare" + "\\" + "FillBlank2_test_used.txt";
      saveFile();

      result = "";
      obs = hs_fdb.toArray();
      Arrays.sort(obs);
      for (int i=0; i<obs.length; i++){
         Integer integer = (Integer)obs[i];
         result += integer.intValue() + ";";
      }
      filePath = TOMCAT_PATH + subPath5 + "\\compare"  + "\\" + "FillDoubleBlanks2_test_used.txt";
      saveFile();
   }

   public void initSetStr(){
      try{
         connect();

         Object[] obs = hs.toArray();
         for (int i=0; i<obs.length; i++){
            Integer integer = (Integer)obs[i];
            stmt = dbCon.prepareStatement("select statement from questions2_test where id=?");
            stmt.clearParameters();
            stmt.setInt(1, integer.intValue());            
            rs = stmt.executeQuery();
            while(rs.next()){
               String tmpStr = rs.getString(1);
               tmpStr = tmpStr.trim();
               hs_str.add(tmpStr.substring(0, tmpStr.length()-1));
            } 
         }

         obs = hs_tf.toArray();
         for (int i=0; i<obs.length; i++){
            Integer integer = (Integer)obs[i];
            stmt = dbCon.prepareStatement("select statement from choices2_test where id=?");
            stmt.clearParameters();
            stmt.setInt(1, integer.intValue());            
            rs = stmt.executeQuery();
            while(rs.next()){
               String tmpStr = rs.getString(1);
               tmpStr = tmpStr.trim(); 
               hs_tf_str.add(tmpStr.substring(0, tmpStr.length()-1));
            } 
         } 

         obs = hs_fb.toArray();
         for (int i=0; i<obs.length; i++){
            Integer integer = (Integer)obs[i];
            stmt = dbCon.prepareStatement("select statement_1st from fillBlank2_test where id=?");
            stmt.clearParameters();
            stmt.setInt(1, integer.intValue());            
            rs = stmt.executeQuery();
            while(rs.next()){
               String tmpStr = rs.getString(1);
               tmpStr = tmpStr.trim();
               hs_fb_str.add(tmpStr.substring(0, tmpStr.length()-1));
            } 
         }  

         obs = hs_fdb.toArray();
         for (int i=0; i<obs.length; i++){
            Integer integer = (Integer)obs[i];
            stmt = dbCon.prepareStatement("select statement_1st from fillDoubleBlanks2_test where id=?");
            stmt.clearParameters();
            stmt.setInt(1, integer.intValue());            
            rs = stmt.executeQuery();
            while(rs.next()){
               String tmpStr = rs.getString(1);
               tmpStr = tmpStr.trim();
               hs_fdb_str.add(tmpStr.substring(0, tmpStr.length()-1));
            } 
         } 
     }catch (ClassNotFoundException ce) {
        ce.printStackTrace();
     }catch (SQLException sqle) {
        sqle.printStackTrace();
     } finally {           
        try {
           if (rs != null) rs.close();
           if (s != null) s.close();          
           if (stmt != null) stmt.close();          
           if (dbCon != null) dbCon.close();
         } catch (SQLException e) {
           e.printStackTrace();
         }
     } 
   }

   public void initSetForAndroid(){
      filePath = MATH_PATH;
      hs_android = initSetFromPathForAndroid_Question(filePath + "\\" + "choices_test.txt", hs_str);
      hs_tf_android = initSetFromPathForAndroid(filePath + "\\" + "judge_test.txt", hs_tf_str);
      hs_fb_android = initSetFromPathForAndroid(filePath + "\\" + "blank_test.txt", hs_fb_str);
      hs_fdb_android = initSetFromPathForAndroid(filePath + "\\" + "doubleblank_test.txt", hs_fdb_str);     
   }
   
   public void saveTestIdsForAndroid(){
      result = "";
      Object[] obs = hs_android.toArray();
      Arrays.sort(obs);
      for (int i=0; i<obs.length; i++){
         Integer integer = (Integer)obs[i];
         result += integer.intValue() + ";";
      }
      filePath = TOMCAT_PATH + subPath5 + comparePath + subAndroidPath + "\\" + "Questions2_test_usedForAndroid.txt";
      saveFile();

      result = "";
      obs = hs_tf_android.toArray();
      Arrays.sort(obs);
      for (int i=0; i<obs.length; i++){
         Integer integer = (Integer)obs[i];
         result += integer.intValue() + ";";
      }
      filePath = TOMCAT_PATH + subPath5 + comparePath + subAndroidPath + "\\" + "Choices2_test_usedForAndroid.txt";
      saveFile();     

      result = "";
      obs = hs_fb_android.toArray();
      Arrays.sort(obs);
      for (int i=0; i<obs.length; i++){
         Integer integer = (Integer)obs[i];
         result += integer.intValue() + ";";
      }
      filePath = TOMCAT_PATH + subPath5 + comparePath + subAndroidPath + "\\" + "FillBlank2_test_usedForAndroid.txt";
      saveFile();

      result = "";
      obs = hs_fdb_android.toArray();
      Arrays.sort(obs);
      for (int i=0; i<obs.length; i++){
         Integer integer = (Integer)obs[i];
         result += integer.intValue() + ";";
      }
      filePath = TOMCAT_PATH + subPath5 + comparePath + subAndroidPath + "\\" + "FillDoubleBlanks2_test_usedForAndroid.txt";
      saveFile();
   }

   public void initTestPaper(){
      result = "";      
      
      int count = 1;
      try{
         connect();
         
         Object[] obs = hs_fdb.toArray();
         for (int i=0; i<obs.length; i++){
            Integer integer = (Integer)obs[i];
            stmt = dbCon.prepareStatement("select statement_1st,statement_2nd,statement_3rd from fillDoubleBlanks2_test where id=?");
            stmt.clearParameters();
            stmt.setInt(1, integer.intValue());            
            rs = stmt.executeQuery();
            while(rs.next()){
               String tmpStr1 = rs.getString(1);
               String tmpStr2 = rs.getString(2);
               String tmpStr3 = rs.getString(3);
               result += integer.intValue() + "." + tmpStr1 + tmpStr2 + tmpStr3 + NEW_LINE;
               count ++;
            } 
         }

         result += NEW_LINE + NEW_LINE;
         count = 1;
         obs = hs_fb.toArray();
         for (int i=0; i<obs.length; i++){
            Integer integer = (Integer)obs[i];
            stmt = dbCon.prepareStatement("select statement_1st,statement_2nd from fillBlank2_test where id=?");
            stmt.clearParameters();
            stmt.setInt(1, integer.intValue());            
            rs = stmt.executeQuery();
            while(rs.next()){
               String tmpStr1 = rs.getString(1);
               String tmpStr2 = rs.getString(2);               
               result += integer.intValue() + "." + tmpStr1 + tmpStr2 + NEW_LINE;
               count ++;
            } 
         }

         result += NEW_LINE + NEW_LINE;
         count = 1;
         obs = hs_tf.toArray();
         for (int i=0; i<obs.length; i++){
            Integer integer = (Integer)obs[i];
            stmt = dbCon.prepareStatement("select statement from choices2_test where id=?");
            stmt.clearParameters();
            stmt.setInt(1, integer.intValue());            
            rs = stmt.executeQuery();
            while(rs.next()){
               String tmpStr1 = rs.getString(1);                             
               result += integer.intValue() + "." + tmpStr1 + NEW_LINE;
               count ++;
            } 
         }

         result += NEW_LINE + NEW_LINE;
         count = 1;
         obs = hs.toArray();
         for (int i=0; i<obs.length; i++){
            Integer integer = (Integer)obs[i];
            stmt = dbCon.prepareStatement("select statement,choiceA,choiceB,choiceC,choiceD from questions2_test where id=?");
            stmt.clearParameters();
            stmt.setInt(1, integer.intValue());            
            rs = stmt.executeQuery();
            while(rs.next()){
               String tmpStr1 = rs.getString(1);     
               String tmpStr2 = rs.getString(2);   
               String tmpStr3 = rs.getString(3);
               String tmpStr4 = rs.getString(4);    
               String tmpStr5 = rs.getString(5);                           
               result += integer.intValue() + "." + tmpStr1 + NEW_LINE + tmpStr2 + "\t" + tmpStr3 + "\t" + tmpStr4 + "\t" + tmpStr5 + NEW_LINE;
               count ++;
            } 
         }

         result += NEW_LINE + NEW_LINE;
         count = 1;
         obs = mhs.toArray();
         for (int i=0; i<obs.length; i++){
            Integer integer = (Integer)obs[i];
            stmt = dbCon.prepareStatement("select statement,choiceA,choiceB,choiceC,choiceD from multipleQuestions2_test where id=?");
            stmt.clearParameters();
            stmt.setInt(1, integer.intValue());            
            rs = stmt.executeQuery();
            while(rs.next()){
               String tmpStr1 = rs.getString(1);     
               String tmpStr2 = rs.getString(2);   
               String tmpStr3 = rs.getString(3);
               String tmpStr4 = rs.getString(4);    
               String tmpStr5 = rs.getString(5);                           
               result += integer.intValue() + "." + tmpStr1 + NEW_LINE + tmpStr2 + "\t" + tmpStr3 + "\t" + tmpStr4 + "\t" + tmpStr5 + NEW_LINE;
               count ++;
            } 
         }

         result += NEW_LINE + NEW_LINE;
         count = 1;
         obs = hs_m.toArray();
         for (int i=0; i<obs.length; i++){
            Integer integer = (Integer)obs[i];
            stmt = dbCon.prepareStatement("select factor1,factor2 from multiply_test where id=?");
            stmt.clearParameters();
            stmt.setInt(1, integer.intValue());            
            rs = stmt.executeQuery();
            while(rs.next()){
               String tmpStr1 = rs.getString(1);     
               String tmpStr2 = rs.getString(2);                                          
               result += integer.intValue() + "." + tmpStr1 + tmpStr2 + NEW_LINE;
               count ++;
            } 
         }
      }catch (ClassNotFoundException ce) {
        ce.printStackTrace();
      }catch (SQLException sqle) {
        sqle.printStackTrace();
      } finally {           
        try {
           if (rs != null) rs.close();
           if (s != null) s.close();          
           if (stmt != null) stmt.close();          
           if (dbCon != null) dbCon.close();
         } catch (SQLException e) {
           e.printStackTrace();
         }
      }
   }

   public void saveTestPaper(){
      filePath = TOMCAT_PATH + subPath2 + "\\" + "generated_test_paper.txt";
      saveFile();
   }
 
/***********************************************************/

   private HashSet initSetFromPath(String path){
      HashSet retSet = new HashSet();
      BufferedReader reader = null;
      InputStream inputStream = null;
      File file = new File(path);
      String[] strings;
      try {
         inputStream = new FileInputStream(file);
         reader =  new BufferedReader(new InputStreamReader(inputStream, encoding));
         String line;
         int count = 0;
         while ((line = reader.readLine()) != null) {
            count ++;
            System.out.println("line = " + line);            
            strings = line.split(";");
            for (int i=0; i<strings.length; i++){
               retSet.add(new Integer(strings[i].trim()));
            }
         }
          reader.close();
      }catch (FileNotFoundException e){
         e.printStackTrace();
      }catch (UnsupportedEncodingException e){
         e.printStackTrace();
      }catch (IOException e){
         e.printStackTrace();
      }finally {      
          System.out.println("This is the end of the reading");
      }
      return retSet;
   }

   private HashSet initSetFromPathForAndroid(String path, HashSet hSet){
      HashSet retSet = new HashSet();
      BufferedReader reader = null;
      InputStream inputStream = null;
      File file = new File(path);
      String[] strings;
      Object[] obs = hSet.toArray();
      try {
         inputStream = new FileInputStream(file);
         reader =  new BufferedReader(new InputStreamReader(inputStream, encoding));
         String line;
         int count = 0;
         while ((line = reader.readLine()) != null) {
            count ++;
            System.out.println("line = " + line);            
            strings = line.split("-");
            strings[0] = strings[0].trim();
            strings[0] = strings[0].substring(0, strings[0].length()-1);
            for (int i=0; i<obs.length; i++){
               String tmpStr = (String)obs[i];
               if (strings[0].equalsIgnoreCase(tmpStr)){
                  retSet.add(new Integer(count));
                  break;
               }
            }
         }
          reader.close();
      }catch (FileNotFoundException e){
         e.printStackTrace();
      }catch (UnsupportedEncodingException e){
         e.printStackTrace();
      }catch (IOException e){
         e.printStackTrace();
      }finally {      
          System.out.println("This is the end of the reading");
      }
      return retSet;
   } 

   private HashSet initSetFromPathForAndroid_Question(String path, HashSet hSet){
      HashSet retSet = new HashSet();
      BufferedReader reader = null;
      InputStream inputStream = null;
      File file = new File(path);
      String[] strings; String[] strings2;
      Object[] obs = hSet.toArray();
      try {
         inputStream = new FileInputStream(file);
         reader =  new BufferedReader(new InputStreamReader(inputStream, encoding));
         String line;
         int count = 0;
         while ((line = reader.readLine()) != null) {
            count ++;
            System.out.println("line = " + line);            
            strings = line.split("-");
            strings2 = strings[0].split("::");
            strings2[0] = strings2[0].trim();
            strings2[0] = strings2[0].substring(0, strings2[0].length()-1);
            for (int i=0; i<obs.length; i++){
               String tmpStr = (String)obs[i];
               if (strings2[0].equalsIgnoreCase(tmpStr)){
                  retSet.add(new Integer(count));
                  break;
               }
            }
         }
          reader.close();
      }catch (FileNotFoundException e){
         e.printStackTrace();
      }catch (UnsupportedEncodingException e){
         e.printStackTrace();
      }catch (IOException e){
         e.printStackTrace();
      }finally {      
          System.out.println("This is the end of the reading");
      }
      return retSet;
   }
    
   private boolean connect() throws ClassNotFoundException, SQLException
    {
      //String dbURL= "jdbc:odbc:pubs";
      //String username = "sa";
      //String password = "family11Wife";
      //String dbDriver = "sun.jdbc.odbc.JdbcOdbcDriver";      
      //Class.forName(dbDriver);      
      //dbCon = DriverManager.getConnection(dbURL, username, password);
      //Class.forName("com.mysql.jdbc.Driver"); 
      //dbCon = DriverManager.getConnection("jdbc:mysql://localhost:3306/EC", "root", "123456");
	  Class.forName("org.hsqldb.jdbcDriver");
	 // dbCon = DriverManager.getConnection("jdbc:hsqldb:file:/home/xtang/Database/TestDb2", "SA", "");
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
  
    private void openFile(File file) {     
      try {
      	 FileReader fileReader = new FileReader(file);
      	 char[] forRead = new char[1000000];
      	 fileReader.read(forRead);
      	 result = new String(forRead);      	
         fileReader.close();         
      }catch (Exception e) {
         e.printStackTrace();	
      }
    }

    private void saveAsFile(){
      JFileChooser fc = new JFileChooser();
      fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
      int returnVal = fc.showSaveDialog(new java.awt.Container());
      if (returnVal == JFileChooser.APPROVE_OPTION) {
         File file = fc.getSelectedFile();           
         if (file.exists() ){
             Object[] options = {"continue", "cancel"};
             int n = JOptionPane.showOptionDialog(null, "The file already exists, do you want to replace it?", "Warning", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);
             if (n == JOptionPane.YES_OPTION){
                 filePath = file.getAbsolutePath();                               
                 saveFile();
             }else{
                //do nothing
             }
         }else { //a completely new file
              filePath = file.getAbsolutePath();            
              saveFile();
         }
      } else {
         //do nothing 
      }       
    }

    private void saveFile(){
       if(filePath != null &&  !filePath.equals("")) {      
          try {
             FileWriter fileOut = new FileWriter(filePath);                      
             fileOut.write(result);
             fileOut.close();         
          }catch (Exception e) {
            e.printStackTrace();	
          }
       }else {
          saveAsFile();
       }
    }

    
   private boolean containsIgnoreCase(String correctAnswer, String input){
      boolean ret = false;
      String[] correctAnswers = correctAnswer.split("/");
      for (int i=0; i<correctAnswers.length; i++){
         if (correctAnswers[i].equalsIgnoreCase(input)){
            ret = true;
            break;
         }
      }
      return ret;
   }    
}
