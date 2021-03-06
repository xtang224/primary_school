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

public class GenerateTest {
  
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
  private String subAndroidPath = "\\android";
  private String subPath6 = "\\ch6_all"; 
  private String subPath7 = "\\ch6_all2";
  private String subPath8 = "\\ch6_all3";
  private String subPath9 = "\\ch6_all4";

  private String filePath = "";  
  private String result = "";

  private HashSet hs = new HashSet();
  private HashSet hs_tf = new HashSet();
  private HashSet mhs = new HashSet();
  private HashSet hs_fb = new HashSet();
  private HashSet hs_fdb = new HashSet();  
  private HashSet hs_m = new HashSet();
  private HashSet hs_d = new HashSet();

  private HashSet hs_used = new HashSet();
  private HashSet hs_tf_used = new HashSet();
  private HashSet mhs_used = new HashSet();
  private HashSet hs_fb_used = new HashSet();
  private HashSet hs_fdb_used = new HashSet();  
  private HashSet hs_m_used = new HashSet();
  private HashSet hs_d_used = new HashSet();

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

  //private String[] sources = new String[]{"ch5_1", "ch5_2", "ch5_3", "ch5_4", "ch5_5", "ch5_6", "ch5_self", "ch5_hk", "ch5", "ch5_add"};
  //private String[] sources = new String[]{"ch6_5", "ch6_6"};
  //private String[] sources = new String[]{"ch6_7", "ch6_8"}; 
  private String[] sources = new String[]{"ch6_1", "ch6_2", "ch6_3", "ch6_4",  "ch6_5", "ch6_6", "ch6_7", "ch6_8", "ch6_9", "ch6_10", "ch6_hk", "ch6_hk2", "ch6", "ch56"}; 
    
  public static void main(String[] args) {
     GenerateTest aTest = new GenerateTest();
     aTest.initUsedSet();
     aTest.initSet();
     //aTest.addSet();
     aTest.saveTestIds();
     //aTest.initSetStr();
     //aTest.initSetForAndroid();
     //aTest.saveTestIdsForAndroid();
     //aTest.initTestPaper();
     //aTest.saveTestPaper();
  }
   
   public GenerateTest(){}

   public void initUsedSet(){
      filePath = TOMCAT_PATH + subPath6;
      hs_used = initSetFromPath(filePath + "\\" + "Questions2_test_used.txt");
      hs_tf_used = initSetFromPath(filePath + "\\" + "Choices2_test_used.txt");
      hs_fb_used = initSetFromPath(filePath + "\\" + "FillBlank2_test_used.txt");
      hs_fdb_used = initSetFromPath(filePath + "\\" + "FillDoubleBlanks2_test_used.txt");
      hs_m_used = initSetFromPath(filePath + "\\" + "Multiply_test_used.txt");
      hs_d_used = initSetFromPath(filePath + "\\" + "Division_test_used.txt");
      
      filePath = TOMCAT_PATH + subPath7;
      addSetFromPath(filePath + "\\" + "Questions2_test_used.txt", hs_used);
      addSetFromPath(filePath + "\\" + "Choices2_test_used.txt", hs_tf_used);
      addSetFromPath(filePath + "\\" + "FillBlank2_test_used.txt", hs_fb_used); 
      addSetFromPath(filePath + "\\" + "FillDoubleBlanks2_test_used.txt", hs_fdb_used); 
      addSetFromPath(filePath + "\\" + "Multiply_test_used.txt", hs_m_used); 
      addSetFromPath(filePath + "\\" + "Division_test_used.txt", hs_d_used); 

      filePath = TOMCAT_PATH + subPath8;
      addSetFromPath(filePath + "\\" + "Questions2_test_used.txt", hs_used);
      addSetFromPath(filePath + "\\" + "Choices2_test_used.txt", hs_tf_used);
      addSetFromPath(filePath + "\\" + "FillBlank2_test_used.txt", hs_fb_used); 
      addSetFromPath(filePath + "\\" + "FillDoubleBlanks2_test_used.txt", hs_fdb_used); 
      addSetFromPath(filePath + "\\" + "Multiply_test_used.txt", hs_m_used); 
      addSetFromPath(filePath + "\\" + "Division_test_used.txt", hs_d_used); 
   }

   public void initSet(){
     try{
        connect();
        int total = 10;

        String source = "";
        for (int i=0; i<sources.length; i++){
           source = sources[i];
           rs = execSQL("select id from questions2_test where source='" + source + "'");
           while (rs.next()){
              int usedId = rs.getInt("id");
              hs.add(new Integer(usedId));
           }
        }
        hs.removeAll(hs_used);     
        System.out.println("hs.size() = " + hs.size());   
        //now we want to only get 10 questions for this generated test
        HashSet tmpSet = new HashSet();
        HashSet controlSet = new HashSet();
        Object[] obs = hs.toArray();
        total = Math.min(10, obs.length);
        for (int i=0; i<total; i++){
           int intRandom = (int)(Math.random() * hs.size());
           while(controlSet.contains(new Integer(intRandom)))
              intRandom = (int)(Math.random() * hs.size());
           controlSet.add(new Integer(intRandom));
           int intSelect = ((Integer)obs[intRandom]).intValue();
           tmpSet.add(new Integer(intSelect));
        }
        hs.clear();
        hs.addAll(tmpSet);        

        source = "";
        for (int i=0; i<sources.length; i++){
           source = sources[i]; 
           rs = execSQL("select id from choices2_test where source='" + source + "'");
           while (rs.next()){
              int usedId = rs.getInt("id");
              hs_tf.add(new Integer(usedId));
           }
        }
        hs_tf.removeAll(hs_tf_used);      
        System.out.println("hs_tf.size() = " + hs_tf.size());    
        //now we want to only get 5 choices for this generated test
        //since hs_tf does not have enough problems remained, we just take all of them
        /*
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
        */

        source = "";
        for (int i=0; i<sources.length; i++){
           source = sources[i];  
           rs = execSQL("select id from fillBlank2_test where source='" + source + "'");
           while (rs.next()){
              int usedId = rs.getInt("id");
              hs_fb.add(new Integer(usedId));
           }
        } 
        hs_fb.removeAll(hs_fb_used);   
        System.out.println("hs_fb.size() = " + hs_fb.size());       
        //now we want to only get 10 fillBlank2 for this generated test
        tmpSet = new HashSet();
        controlSet = new HashSet();
        obs = hs_fb.toArray();
        total = Math.min(10, obs.length);
        for (int i=0; i<total; i++){
           int intRandom = (int)(Math.random() * hs_fb.size());
           while(controlSet.contains(new Integer(intRandom)))
              intRandom = (int)(Math.random() * hs_fb.size());
           controlSet.add(new Integer(intRandom));
           int intSelect = ((Integer)obs[intRandom]).intValue();
           tmpSet.add(new Integer(intSelect));
        }
        hs_fb.clear();
        hs_fb.addAll(tmpSet);       

        source = "";
        for (int i=0; i<sources.length; i++){
           source = sources[i];  
           rs = execSQL("select id from fillDoubleBlanks2_test where source='" + source + "'");
           while (rs.next()){
              int usedId = rs.getInt("id");
              hs_fdb.add(new Integer(usedId));
           } 
        }
        hs_fdb.removeAll(hs_fdb_used); 
        System.out.println("hs_fdb.size() = " + hs_fdb.size());         
        //now we want to only get 20 fillDoubleBlanks2 for this generated test
        tmpSet = new HashSet();
        controlSet = new HashSet();
        obs = hs_fdb.toArray();
        total = Math.min(20, obs.length);
        for (int i=0; i<total; i++){
           int intRandom = (int)(Math.random() * hs_fdb.size());
           while(controlSet.contains(new Integer(intRandom)))
              intRandom = (int)(Math.random() * hs_fdb.size());
           controlSet.add(new Integer(intRandom));
           int intSelect = ((Integer)obs[intRandom]).intValue();
           tmpSet.add(new Integer(intSelect));
        }
        hs_fdb.clear();
        hs_fdb.addAll(tmpSet);
        

        source = "";
        for (int i=0; i<sources.length; i++){
           source = sources[i];  
           rs = execSQL("select id from division_test where source='" + source + "'");
           while (rs.next()){
              int usedId = rs.getInt("id");
              hs_d.add(new Integer(usedId));
           } 
        }
        hs_d.removeAll(hs_d_used);
        System.out.println("hs_d.size() = " + hs_d.size());  
        //now we want to get 10 divisions
        tmpSet = new HashSet();
        controlSet = new HashSet();
        obs = hs_d.toArray();
        total = Math.min(10, obs.length);
        for (int i=0; i<total; i++){
           int intRandom = (int)(Math.random() * hs_d.size());
           while(controlSet.contains(new Integer(intRandom)))
              intRandom = (int)(Math.random() * hs_d.size());
           controlSet.add(new Integer(intRandom));
           int intSelect = ((Integer)obs[intRandom]).intValue();
           tmpSet.add(new Integer(intSelect));
        }
        hs_d.clear();
        hs_d.addAll(tmpSet);  
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

   public void addSet(){
     try{
        connect();

        String source = "ch5_add";        
        rs = execSQL("select id from questions2_test where source='" + source + "'");
        while (rs.next()){
           int usedId = rs.getInt("id");
           hs.add(new Integer(usedId));
        }
      
        rs = execSQL("select id from choices2_test where source='" + source + "'");
        while (rs.next()){
           int usedId = rs.getInt("id");
           hs_tf.add(new Integer(usedId));
        }
        
        rs = execSQL("select id from fillBlank2_test where source='" + source + "'");
        while (rs.next()){
           int usedId = rs.getInt("id");
           hs_fb.add(new Integer(usedId));
        }
       
        rs = execSQL("select id from fillDoubleBlanks2_test where source='" + source + "'");
        while (rs.next()){
           int usedId = rs.getInt("id");
           hs_fdb.add(new Integer(usedId));
        } 
 
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

   public void saveTestIds(){
      result = "";
      Object[] obs = hs.toArray();
      Arrays.sort(obs);
      for (int i=0; i<obs.length; i++){
         Integer integer = (Integer)obs[i];
         result += integer.intValue() + ";";
      }
      filePath = TOMCAT_PATH + subPath9 + "\\" + "Questions2_test_used.txt";
      saveFile();

      result = "";
      obs = hs_tf.toArray();
      for (int i=0; i<obs.length; i++){
         Integer integer = (Integer)obs[i];
         result += integer.intValue() + ";";
      }
      filePath = TOMCAT_PATH + subPath9 + "\\" + "Choices2_test_used.txt";
      saveFile();

      result = "";
      obs = hs_m.toArray();
      Arrays.sort(obs);
      for (int i=0; i<obs.length; i++){
         Integer integer = (Integer)obs[i];
         result += integer.intValue() + ";";
      }
      filePath = TOMCAT_PATH + subPath9 + "\\" + "Multiply_test_used.txt";
      saveFile();

      result = "";
      obs = mhs.toArray();
      Arrays.sort(obs);
      for (int i=0; i<obs.length; i++){
         Integer integer = (Integer)obs[i];
         result += integer.intValue() + ";";
      }
      filePath = TOMCAT_PATH + subPath9 + "\\" + "MultipleQuestions2_test_used.txt";
      saveFile();

      result = "";
      obs = hs_fb.toArray();
      Arrays.sort(obs);
      for (int i=0; i<obs.length; i++){
         Integer integer = (Integer)obs[i];
         result += integer.intValue() + ";";
      }
      filePath = TOMCAT_PATH + subPath9 + "\\" + "FillBlank2_test_used.txt";
      saveFile();

      result = "";
      obs = hs_fdb.toArray();
      Arrays.sort(obs);
      for (int i=0; i<obs.length; i++){
         Integer integer = (Integer)obs[i];
         result += integer.intValue() + ";";
      }
      filePath = TOMCAT_PATH + subPath9 + "\\" + "FillDoubleBlanks2_test_used.txt";
      saveFile();

      result = "";
      obs = hs_d.toArray();
      Arrays.sort(obs);
      for (int i=0; i<obs.length; i++){
         Integer integer = (Integer)obs[i];
         result += integer.intValue() + ";";
      }
      filePath = TOMCAT_PATH + subPath9 + "\\" + "Division_test_used.txt";
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
               hs_str.add(tmpStr.substring(0, tmpStr.length()-4));
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
               hs_tf_str.add(tmpStr.substring(0, tmpStr.length()-4));
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
               hs_fb_str.add(tmpStr.substring(0, tmpStr.length()-4));
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
               hs_fdb_str.add(tmpStr.substring(0, tmpStr.length()-4));
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
      hs_android = initSetFromPathForAndroid(filePath + "\\" + "choices_test.txt", hs_str);
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
      filePath = TOMCAT_PATH + subPath2 + subAndroidPath + "\\" + "Questions2_test_usedForAndroid.txt";
      saveFile();

      result = "";
      obs = hs_tf_android.toArray();
      Arrays.sort(obs);
      for (int i=0; i<obs.length; i++){
         Integer integer = (Integer)obs[i];
         result += integer.intValue() + ";";
      }
      filePath = TOMCAT_PATH + subPath2 + subAndroidPath + "\\" + "Choices2_test_usedForAndroid.txt";
      saveFile();

      result = "";
      obs = hs_m.toArray();
      Arrays.sort(obs);
      for (int i=0; i<obs.length; i++){
         Integer integer = (Integer)obs[i];
         result += integer.intValue() + ";";
      }
      filePath = TOMCAT_PATH + subPath2 + subAndroidPath + "\\" + "Multiply_test_used.txt";
      saveFile();

      result = "";
      obs = mhs.toArray();
      Arrays.sort(obs);
      for (int i=0; i<obs.length; i++){
         Integer integer = (Integer)obs[i];
         result += integer.intValue() + ";";
      }
      filePath = TOMCAT_PATH + subPath2 + subAndroidPath + "\\" + "MultipleQuestions2_test_used.txt";
      saveFile();

      result = "";
      obs = hs_fb_android.toArray();
      Arrays.sort(obs);
      for (int i=0; i<obs.length; i++){
         Integer integer = (Integer)obs[i];
         result += integer.intValue() + ";";
      }
      filePath = TOMCAT_PATH + subPath2 + subAndroidPath + "\\" + "FillBlank2_test_usedForAndroid.txt";
      saveFile();

      result = "";
      obs = hs_fdb_android.toArray();
      Arrays.sort(obs);
      for (int i=0; i<obs.length; i++){
         Integer integer = (Integer)obs[i];
         result += integer.intValue() + ";";
      }
      filePath = TOMCAT_PATH + subPath2 + subAndroidPath + "\\" + "FillDoubleBlanks2_test_usedForAndroid.txt";
      saveFile();
   }

   public void initTestPaper(){
      result = "";      
      
      int count = 1;
      try{
         connect();
         
         Object[] obs = hs_fdb.toArray();
         Arrays.sort(obs);   
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
         Arrays.sort(obs);
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
         Arrays.sort(obs);
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
         Arrays.sort(obs);
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
         Arrays.sort(obs);
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
         Arrays.sort(obs);
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

   private void addSetFromPath(String path, HashSet retSet){
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
            for (int i=0; i<obs.length; i++){
               String tmpStr = (String)obs[i];
               if (strings[0].startsWith(tmpStr)){
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
