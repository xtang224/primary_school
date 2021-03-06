//package org.primefaces.examples.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

//import org.primefaces.examples.domain.Car;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.context.ExternalContext;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.DragDropEvent;
import org.primefaces.event.*;
import org.primefaces.model.*;

import java.io.*;
import java.util.*;
import java.sql.*;

import javax.servlet.*;
import javax.servlet.http.*;

@ManagedBean(name = "termTableBean4", eager = true)
@RequestScoped

public class TermTableBean4 implements Serializable {

	private List<Term> termsSmall;
        private List<Term> droppedTerms;

        private Term selectedTerm; 
        private String correctContent; 
        private String correctContent2; 
        private String[] correctContents;
        private String[] correctContents2;
        private String strCorrectContentSet;    
        private String chosenContent;   
        private int[] intIds;
        private int intId;

        private String answeredHashSet;
        private String answeredHashSet_tf;
        private String answered_M_HashSet;
        private String answeredHashSet_fb;
        private String answeredHashSet_fdb;
        private String answeredHashSet_tm;
        private String answered_MY_HashSet;
        private String answered_D_HashSet;
        private String answered_C_HashSet;
        private String answeredProblems;
        private String correctAnswers;
        private String totalScore;
        private String correctAnswers_low;
        private String answeredProblems_low;
        private String correctAnswers_middle;
        private String answeredProblems_middle;
        private String correctAnswers_high;
        private String answeredProblems_high;
        private String userName;
        private String passWord;
        private String trueName;
        private String source;
        private String lastType;
        private String thisType;
        private String times;
        private String randomNumber;
        private String id;
        private String planStatus;

        private String dropHint;

	public TermTableBean4() {
	   termsSmall = new ArrayList<Term>();
           droppedTerms = new ArrayList<Term>();
           correctContents = new String[4];
           correctContents2 = new String[4];
           strCorrectContentSet = "true";   
           intIds = new int[5]; 
 
           populateTerms(strCorrectContentSet);                      
           System.out.println("constructor is called");
	}

        public void getParamRedir() {
           String link = "TermMatch_Check.jsp?answeredHashSet=" + answeredHashSet + "&answeredHashSet_tf=" + answeredHashSet_tf + "&answered_M_HashSet=" + answered_M_HashSet + "&answeredHashSet_fb=" + answeredHashSet_fb + "&answeredHashSet_fdb=" + answeredHashSet_fdb + "&answeredHashSet_tm=" + answeredHashSet_tm + "&answered_MY_HashSet=" + answered_MY_HashSet + "&answered_D_HashSet=" + answered_D_HashSet + "&answered_C_HashSet=" + answered_C_HashSet + "&answeredProblems=" + answeredProblems + "&correctAnswers=" + correctAnswers + "&totalScore=" + totalScore + "&correctAnswers_low=" + correctAnswers_low + "&answeredProblems_low=" + answeredProblems_low + "&correctAnswers_middle=" + correctAnswers_middle + "&answeredProblems_middle=" + answeredProblems_middle + "&correctAnswers_high=" + correctAnswers_high + "&answeredProblems_high=" + answeredProblems_high + "&userName=" + userName + "&passWord=" + passWord + "&trueName=" + trueName + "&source=" + source + "&lastType=" + lastType + "&thisType=" + thisType + "&times=" + times + "&randomNumber=" + randomNumber + "&id=" + intId + "&planStatus=" + planStatus;
           ExternalContext extContext = FacesContext.getCurrentInstance().getExternalContext();
           //HttpServletResponse response = (HttpServletResponse)extContext.getResponse();
           try {
              extContext.redirect(link.trim());
           }catch (Exception e){
              e.printStackTrace();
           }
           /* 
           String strHidden1 = extContext.getRequestParameterMap().get("hidden1");
           System.out.println("Inside getParam, after get hidden1, strHidden1= " + strHidden1);
           termsSmall.clear();
           populateTerms(strHidden1);
           */
        }

        public void populateTerms(String strCorrectContentSet) {           
           ExternalContext extContext = FacesContext.getCurrentInstance().getExternalContext();
           System.out.println("Inside populateTerms");

           java.sql.Connection myConnection = null;
           PreparedStatement stmt = null;
           PreparedStatement stmt2 = null;
           PreparedStatement stmt3 = null;
           Statement s = null;
           ResultSet rs = null;
           ResultSet rs2 = null;
           ResultSet rs3 = null;
           HashSet usedSet = new HashSet();  
           HashSet totalSet = new HashSet(); 

           try{
              Class.forName("org.hsqldb.jdbcDriver");
              myConnection = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/xdb", "SA", ""); 

              if (strCorrectContentSet.equals("false")){
                 //do nothing                  
               }else if (strCorrectContentSet.equals("true")){ 
                  stmt = myConnection.prepareStatement("select id from currentTermMatch");
                  stmt.clearParameters();
                  rs = stmt.executeQuery();
                  int count = 0;
                  String strTerm1 = null;
                  String strTerm2 = null;
                  int intTemp = 0;

                  while(rs.next()){
                     intTemp = rs.getInt(1);
                     intIds[count] = intTemp;
                     stmt2 = myConnection.prepareStatement("select term1, term2 from termMatch_test where id=?");
                     stmt2.clearParameters();
                     stmt2.setInt(1, intTemp);
                     rs2 = stmt2.executeQuery();
                     count++;     
                     if (count == 5) break;
                     while(rs2.next()){
                        strTerm1 = rs2.getString(1);
                        strTerm2 = rs2.getString(2);
                        correctContents[count-1] = strTerm1;
                        correctContents2[count-1] = strTerm2;
                        termsSmall.add(new Term(strTerm2));
                     }
                  }
                  correctContent = correctContents[intTemp];
                  correctContent2 = correctContents2[intTemp];
                  intId = intIds[intTemp];
                  System.out.println("inside populateTerms, correctContent = " + correctContent);
                  System.out.println("inside populateTerms, correctContent2 = " + correctContent2);
                  System.out.println("inside populateTerms, intId = " + intId);      

                  stmt3 = myConnection.prepareStatement("select name from students where studentNo=?");
                  stmt3.clearParameters();
                  stmt3.setString(1, userName);
            
      rs3 = stmt3.executeQuery();
                  while(rs3.next()){
                     trueName = rs3.getString(1);
                  }                  
                  System.out.println("inside populateTerms, studentNo or userName = " + userName);
                  
System.out.println("inside populateTerms, trueName = " + trueName);

                  //below we want to set the Chinese hint for drop
                  stmt3 = myConnection.prepareStatement("select term2 from hintMatch where term1=?");
                  stmt3.clearParameters();
                  stmt3.setString(1, "drop");            
                  rs3 = stmt3.executeQuery();
                  while(rs3.next()){
                     dropHint = rs3.getString(1);
                  }                 

               }else { //strCorrectContentSet.equals("")
                  //do nothing
               }
           }catch(Exception e){
              e.printStackTrace();
           }finally{
              try {
                    if (rs != null) rs.close();
                    if (rs2 != null) rs2.close();
                    if (s != null) s.close();
                    if (stmt != null) stmt.close();
                    if (stmt2 != null) stmt2.close();
                    if (myConnection != null) myConnection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
           }         
        }

	public List<Term> getTermsSmall() {
		return termsSmall;
	}

        public void onTermDrop(DragDropEvent ddEvent) {           
           ExternalContext extContext = FacesContext.getCurrentInstance().getExternalContext();
           String strHidden1 = extContext.getRequestParameterMap().get("hidden1");
           System.out.println("Inside onTermDrop, after get hidden1, strHidden1= " + strHidden1);

           Term term = ((Term) ddEvent.getData());
           System.out.println("term is selected");
           selectedTerm = term;
           System.out.println("selectedTerm:" + selectedTerm.getContent());

           System.out.println("inside onTermDrop, correctContent = " + correctContent);
           System.out.println("inside onTermDrop, correctContent2 = " + correctContent2);
           chosenContent = selectedTerm.getContent();
           String strAnswer = null;
           if (chosenContent.equals(correctContent2)){
              selectedTerm.setRightAnswer(true);
              strAnswer = "true";
           }else{
              selectedTerm.setRightAnswer(false);
              strAnswer = "false";
           }

           java.sql.Connection myConnection = null;
           PreparedStatement stmt = null; 
           Statement s = null;
           try{
              Class.forName("org.hsqldb.jdbcDriver");
              myConnection = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/xdb", "SA", ""); 

              s = myConnection.createStatement();
              s.execute("delete from termMatchAnswer where studentNo='" + strHidden1 + "'");

      stmt = myConnection.prepareStatement("insert into termMatchAnswer(studentNo, answerRightOrWrong) values(?, ?)");
              stmt.clearParameters();
              stmt.setString(1, strHidden1);
              stmt.setString(2, strAnswer);
              stmt.executeUpdate();

           }catch(Exception e){
              e.printStackTrace();
           }finally{
              try {
                 if (stmt != null) stmt.close();                 
                 if (myConnection != null) myConnection.close();
              } catch (SQLException e) {
                 e.printStackTrace();
              }
           }

           droppedTerms.add(term);
           termsSmall.remove(term);           
       }

       public Term getSelectedTerm() {           
           return selectedTerm;
       }

       public String getCorrectContent() {           
           return correctContent;
       }

       public List<Term> getDroppedTerms() {
           return droppedTerms;
       }

       public String getStrCorrectContentSet() {    
           System.out.println("getStrCorrectContentSet() is called and strCorrectContentSet = " + strCorrectContentSet);    
           return strCorrectContentSet;
       }

       public String getAnsweredHashSet() {    
           return answeredHashSet;
       }

       public String getAnsweredHashSet_tf() {    
           return answeredHashSet_tf;
       }

       public String getAnswered_M_HashSet() {    
           return answered_M_HashSet;
       }

       public String getAnswered_MY_HashSet() {    
           return answered_MY_HashSet;
       }

       public String getAnsweredHashSet_fb() {    
           return answeredHashSet_fb;
       }

       public String getAnsweredHashSet_fdb() {    
           return answeredHashSet_fdb;
       }

       public String getAnsweredHashSet_tm() {    
           return answeredHashSet_tm;
       }

       public String getAnswered_D_HashSet() {    
           return answered_D_HashSet;
       }

       public String getAnswered_C_HashSet() {    
           return answered_C_HashSet;
       }

       public String getAnsweredProblems() {    
           return answeredProblems;
       }

       public String getCorrectAnswers() {    
           return correctAnswers;
       }

       public String getTotalScore() {    
           return totalScore;
       }

       public String getCorrectAnswers_low() {    
           return correctAnswers_low;
       }

       public String getAnsweredProblems_low() {    
           return answeredProblems_low;
       }

       public String getCorrectAnswers_middle() {    
           return correctAnswers_middle;
       }

       public String getAnsweredProblems_middle() {    
           return answeredProblems_middle;
       }

       public String getCorrectAnswers_high() {    
           return correctAnswers_high;
       }

       public String getAnsweredProblems_high() {    
           return answeredProblems_high;
       }

       public String getUserName() {    
           System.out.println("getUserName is called and userName = " + userName);    
           return userName;
       }

       public String getPassWord() {    
           return passWord;
       }

       public String getTrueName() {   
           System.out.println("getTrueName is called and trueName = " + trueName);    
           java.sql.Connection myConnection = null;
           PreparedStatement stmt = null; 
           ResultSet rs = null;

           try{
              Class.forName("org.hsqldb.jdbcDriver");
              myConnection = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/xdb", "SA", ""); 

              stmt = myConnection.prepareStatement("select name from students where studentNo=?");
              stmt.clearParameters();
              stmt.setString(1, userName);
              rs = stmt.executeQuery();
              while(rs.next()){
                 trueName = rs.getString(1);
              }                  
              System.out.println("inside getTrueName, studentNo or userName = " + userName);
              System.out.println("inside getTrueName, trueName = " + trueName);
           }catch(Exception e){
              e.printStackTrace();
           }finally{
              try {
                 if (rs != null) rs.close();
                 if (stmt != null) stmt.close();                 
                 if (myConnection != null) myConnection.close();
              } catch (SQLException e) {
                 e.printStackTrace();
              }
           }


           return trueName;
       }

       public String getSource() {    
           return source;
       }

       public String getLastType() {    
           return lastType;
       }

       public String getThisType() {    
           return thisType;
       }

       public String getTimes() {    
           return times;
       }

       public String getRandomNumber() {    
           return randomNumber;
       }

       public String getId() {    
           return id;
       }

       public String getPlanStatus() {    
           return planStatus;
       }

       public String getDropHint() {    
           return dropHint;
       }

/****************************************************/

       public void setStrCorrectContentSet(String strCorrectContentSet) {
           this.strCorrectContentSet = strCorrectContentSet;
           System.out.println("setStrCorrectContentSet(strCorrectContentSet) is called and strCorrectContentSet = " + strCorrectContentSet);       
       }       

       public void setAnsweredHashSet(String answeredHashSet) {    
           this.answeredHashSet = answeredHashSet;
       }

       public void setAnsweredHashSet_tf(String answeredHashSet_tf) {    
           this.answeredHashSet_tf = answeredHashSet_tf;
       }

       public void setAnswered_M_HashSet(String answered_M_HashSet) {    
           this.answered_M_HashSet = answered_M_HashSet;
       }

       public void setAnswered_MY_HashSet(String answered_MY_HashSet) {    
           this.answered_MY_HashSet = answered_MY_HashSet;
       }

       public void setAnsweredHashSet_fb(String answeredHashSet_fb) {    
           this.answeredHashSet_fb = answeredHashSet_fb;
       }

       public void setAnsweredHashSet_fdb(String answeredHashSet_fdb) {    
           this.answeredHashSet_fdb = answeredHashSet_fdb;
       }

       public void setAnsweredHashSet_tm(String answeredHashSet_tm) {    
           this.answeredHashSet_tm = answeredHashSet_tm;
       }

       public void setAnswered_D_HashSet(String answered_D_HashSet) {    
           this.answered_D_HashSet = answered_D_HashSet;
       }

       public void setAnswered_C_HashSet(String answered_C_HashSet) {    
           this.answered_C_HashSet = answered_C_HashSet;
       }

       public void setAnsweredProblems(String answeredProblems) {    
           this.answeredProblems = answeredProblems;
       }

       public void setCorrectAnswers(String correctAnswers) {    
           this.correctAnswers = correctAnswers;
       }

       public void setTotalScore(String totalScore) {    
           this.totalScore = totalScore;
       }

       public void setCorrectAnswers_low(String correctAnswers_low) {    
           this.correctAnswers_low = correctAnswers_low;
       }

       public void setAnsweredProblems_low(String answeredProblems_low) {    
           this.answeredProblems_low = answeredProblems_low;
       }

       public void setCorrectAnswers_middle(String correctAnswers_middle) {    
           this.correctAnswers_middle = correctAnswers_middle;
       }

       public void setAnsweredProblems_middle(String answeredProblems_middle) {    
           this.answeredProblems_middle = answeredProblems_middle;
       }

       public void setCorrectAnswers_high(String correctAnswers_high) {    
           this.correctAnswers_high = correctAnswers_high;
       }

       public void setAnsweredProblems_high(String answeredProblems_high) {    
           this.answeredProblems_high = answeredProblems_high;
       }

       public void setUserName(String userName) {
           this.userName = userName;
           System.out.println("setuserName(userName) is called and userName = " + userName);       
       }

       public void setPassWord(String passWord) {    
           this.passWord = passWord;
       }

       public void setTrueName(String trueName) {    
           this.trueName = trueName;
       }

       public void setSource(String source) {    
           this.source = source;
       }

       public void setLastType(String lastType) {    
           this.lastType = lastType;
       }

       public void setThisType(String thisType) {    
           this.thisType = thisType;
       }

       public void setTimes(String times) {    
           this.times = times;
       }

       public void setRandomNumber(String randomNumber) {    
           this.randomNumber = randomNumber;
       }

       public void setId(String id) {    
           this.id = id;
       }

       public void setPlanStatus(String planStatus) {    
           this.planStatus = planStatus;
       }

       public void setDropHint(String dropHint) {    
           this.dropHint = dropHint;
       }


}
	
