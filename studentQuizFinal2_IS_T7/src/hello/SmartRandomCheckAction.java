package hello;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.MessageResources;

import java.util.*;
import java.sql.*;
import java.io.*;
import javax.sql.*;

import util.*;

public final class SmartRandomCheckAction extends Action {

    /**
     * Process the specified HTTP request, and create the corresponding HTTP
     * response (or forward to another web component that will create it).
     * Return an <code>ActionForward</code> instance describing where and how
     * control should be forwarded, or <code>null</code> if the response has
     * already been completed.
     */
    public ActionForward execute(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response)
    throws Exception {

        // These "messages" come from the ApplicationResources.properties file
        MessageResources messages = getResources(request);

        /*
         * Validate the request parameters specified by the user
         * Note: Basic field validation done in HelloForm.java
         *       Business logic validation done in HelloAction.java
         */
        ActionMessages errors = new ActionMessages();
        String strRandomNumber = (String)((SmartRandomCheckForm) form).getRandomNumber();
        int randomNumber = Integer.parseInt(strRandomNumber);
        String inputAnswer = (String)((SmartRandomCheckForm) form).getInputAnswer();
        inputAnswer = inputAnswer.trim();
        inputAnswer = new String(inputAnswer.getBytes("iso8859-1"), "UTF-8");
        HttpSession session = request.getSession(true);
        String correctChoice = "";
        correctChoice = (String)((SmartRandomCheckForm) form).getCorrectAnswer();
        correctChoice = new String(correctChoice.getBytes("iso8859-1"), "UTF-8");
        String solution = "";
        solution = (String)((SmartRandomCheckForm) form).getSolution();
        solution = new String(solution.getBytes("iso8859-1"), "UTF-8"); 
        
        SmartProblemBean pb = new SmartProblemBean();
        SmartChoiceBean cb = new SmartChoiceBean();
        SmartMultipleProblemBean mpb = new SmartMultipleProblemBean();    
        FillBlankBean fbb = new FillBlankBean();
        FillDoubleBlankBean fdbb = new FillDoubleBlankBean();
        MultiplyBean mb = new MultiplyBean();
        DivisionBean db = new DivisionBean();
        CompositionBean c2b = new CompositionBean();

        String statement_1st = null;
        String statement_2nd = null;
        String statement_3rd = null;
        String solution_1st = null;
        String solution_2nd = null;
        String factor1 = null;
        String factor2 = null;
        String process1 = null;
        String process2 = null;
        String result = null;
        String p1Solution = null;
        String p2Solution = null;
        String rSolution = null;

        String product1 = null;
        String remain1 = null;
        String product2 = null;
        String remain2 = null;
        String product3 = null;
        String remain3 = null;   

        String answerSentences = null; 
        String answerWords = null;   
        String fullScore = null;    
        String period = null; 
            
        PersonBean pb2 = new PersonBean();
        String userName = (String)((SmartRandomCheckForm) form).getUserName();
        String passWord = (String)((SmartRandomCheckForm) form).getPassWord();
        String trueName = (String)((SmartRandomCheckForm) form).getTrueName();
        pb2.setUserName(userName);
        pb2.setPassWord(passWord);  
        pb2.setTrueName(trueName);      
        pb2.setInputAnswer(inputAnswer);

        String times = (String)((SmartRandomCheckForm) form).getTimes();
        pb2.setTimes(times);

        String source = (String)((SmartRandomCheckForm) form).getSource();
        pb2.setSource(source);

        String answeredProblems = (String)((SmartRandomCheckForm) form).getAnsweredProblems();
        String correctAnswers = (String)((SmartRandomCheckForm) form).getCorrectAnswers();
        int intAnsweredProblems = Integer.parseInt(answeredProblems);
        int intCorrectAnswers = Integer.parseInt(correctAnswers);        

        intAnsweredProblems ++;
        pb2.setAnsweredProblems(intAnsweredProblems);        
       
        String totalScore = (String)((SmartRandomCheckForm) form).getTotalScore();
        String correctAnswers_low = (String)((SmartRandomCheckForm) form).getCorrectAnswers_low();
        String answeredProblems_low = (String)((SmartRandomCheckForm) form).getAnsweredProblems_low();
        String correctAnswers_middle = (String)((SmartRandomCheckForm) form).getCorrectAnswers_middle();
        String answeredProblems_middle = (String)((SmartRandomCheckForm) form).getAnsweredProblems_middle();
        String correctAnswers_high = (String)((SmartRandomCheckForm) form).getCorrectAnswers_high();
        String answeredProblems_high = (String)((SmartRandomCheckForm) form).getAnsweredProblems_high();
        double dbTotalScore = Double.parseDouble(totalScore);
        int intCorrectAnswers_low = Integer.parseInt(correctAnswers_low);
        int intAnsweredProblems_low = Integer.parseInt(answeredProblems_low);    
        int intCorrectAnswers_middle = Integer.parseInt(correctAnswers_middle);
        int intAnsweredProblems_middle = Integer.parseInt(answeredProblems_middle);
        int intCorrectAnswers_high = Integer.parseInt(correctAnswers_high);
        int intAnsweredProblems_high = Integer.parseInt(answeredProblems_high);
        boolean lastCorrect = false;
        String lastType = (String)((SmartRandomCheckForm) form).getLastType();

        javax.sql.DataSource dataSource = null;
        java.sql.Connection myConnection = null;
        Statement s = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        String strCurrentProblemId = (String)((SmartRandomCheckForm) form).getId();
        int intCurrentProblemId = Integer.parseInt(strCurrentProblemId);
        int tempIntScore = 0;
        String inputAnswer_lowerCase = inputAnswer.toLowerCase();
        String correctChoice_lowerCase = correctChoice.toLowerCase();   
        System.out.println("Inside SmartRandomCheckAction, randomNumber = " + randomNumber + " and intCurrentProblemId = " + intCurrentProblemId); 

        String cNote = "\\";
        String TOMCAT_PATH = "E:\\Tomcat_6\\webapps\\studentQuizFinal2_IS_T7";
        //String TOMCAT_PATH = "C:\\Java_Related\\Tomcat_6\\webapps\\studentQuizFinal2_IS_T7";
        if (StringSetTransfer.linux){
           TOMCAT_PATH = "/home/xtang/Tomcat_6/webapps/studentQuizFinal2_IS_T7";
           cNote = "/";
        }
    
        switch (randomNumber){
           case 0: //questions2
              if (correctChoice_lowerCase.equals(inputAnswer_lowerCase)){
                 intCorrectAnswers ++;                 
                 intCorrectAnswers_low ++;
                 intAnsweredProblems_low ++;
                 lastCorrect = true;  
                 tempIntScore = StringSetTransfer.hm_q.get(new Integer(intCurrentProblemId)).intValue();
                 dbTotalScore += tempIntScore;
              }else{
                 intAnsweredProblems_low ++;
                 lastCorrect = false;  
              }

              //we want to record studentAnswer of questions2              
              try{
                 dataSource = getDataSource(request);
                 myConnection = dataSource.getConnection();

  stmt = myConnection.prepareStatement("insert into usedQuestions2_test(Id,Type,Times,StudentNo,StudentAnswer,source) values(?,'L',?,?,?,?)");
               stmt.clearParameters();
               stmt.setInt(1, intCurrentProblemId);
               stmt.setString(2, times);
               stmt.setString(3, userName);
               stmt.setString(4, inputAnswer_lowerCase);
               stmt.setString(5, source);
               stmt.execute(); 
  
               //now we want to get correct answers directly from database
               stmt = myConnection.prepareStatement("select correctChoice,solution from questions2_test where id=?");
               stmt.clearParameters();
               stmt.setInt(1, intCurrentProblemId);
               rs = stmt.executeQuery();              
               while(rs.next()){
                  correctChoice = rs.getString(1);
                  solution = rs.getString(2);
               }
               pb.setCorrectChoice(correctChoice);
               pb.setSolution(solution);
               pb.setSource(source);
               request.setAttribute( Constants.PROBLEM_KEY, pb); 
               session.setAttribute( Constants.PROBLEM_KEY, pb); 
              }catch (SQLException sqle) {
                 getServlet().log("Connection.process", sqle);
              } finally {           
                 try {
                    if (rs != null) rs.close();
                    if (s != null) s.close();
                    if (stmt != null) stmt.close();
                    if (myConnection != null) myConnection.close();
                 } catch (SQLException e) {
                    getServlet().log("Connection.close", e);
                 }
              }
              break;
           case 1: //choices2
              if (correctChoice_lowerCase.equals(inputAnswer_lowerCase)){
                 intCorrectAnswers ++;
                 intCorrectAnswers_low ++;
                 intAnsweredProblems_low ++;
                 lastCorrect = true; 
                 tempIntScore = StringSetTransfer.hm_c.get(new Integer(intCurrentProblemId)).intValue();
                 dbTotalScore += tempIntScore; 
              }else{
                 intAnsweredProblems_low ++;
                 lastCorrect = false;  
              }

              //we want to record studentAnswer of choices2              
              try{
                 dataSource = getDataSource(request);
                 myConnection = dataSource.getConnection();

  stmt = myConnection.prepareStatement("insert into usedChoices2_test(Id,Type,Times,StudentNo,StudentAnswer,source) values(?,'L',?,?,?,?)");
               stmt.clearParameters();
               stmt.setInt(1, intCurrentProblemId);
               stmt.setString(2, times);
               stmt.setString(3, userName);
               stmt.setString(4, inputAnswer_lowerCase);
               stmt.setString(5, source);
               stmt.execute(); 

               //now we want to get correct answers directly from database
               stmt = myConnection.prepareStatement("select choice, solution from choices2_test where id=?");
               stmt.clearParameters();
               stmt.setInt(1, intCurrentProblemId);
               rs = stmt.executeQuery();              
               while(rs.next()){  
                  correctChoice = rs.getString(1);                
                  solution = rs.getString(2);
               }  
               cb.setChoice(correctChoice);             
               cb.setSolution(solution); 
               cb.setSource(source);
               request.setAttribute( Constants.CHOICE_KEY, cb); 
               session.setAttribute( Constants.CHOICE_KEY, cb);
              }catch (SQLException sqle) {
                 getServlet().log("Connection.process", sqle);
              } finally {           
                 try {
                    if (rs != null) rs.close();
                    if (s != null) s.close();
                    if (stmt != null) stmt.close();
                    if (myConnection != null) myConnection.close();
                 } catch (SQLException e) {
                    getServlet().log("Connection.close", e);
                 }
              }
              break;
           case 2: //multiplequestions2 
              char[] inputAnswer_lowerCase_Arr = inputAnswer_lowerCase.toCharArray();
              Arrays.sort(inputAnswer_lowerCase_Arr);
              String temp = new String(inputAnswer_lowerCase_Arr);
              //assuming in multiple choices, correctChoice takes the correct order
              if (temp.equals(correctChoice_lowerCase)){
                 intCorrectAnswers ++;
                 intCorrectAnswers_high ++;
                 intAnsweredProblems_high ++;
                 lastCorrect = true;
                 tempIntScore = StringSetTransfer.hm_mq.get(new Integer(intCurrentProblemId)).intValue();
                 dbTotalScore += tempIntScore;          
              }else{
                 intAnsweredProblems_high ++;
                 lastCorrect = false;  
              }

              //we want to record studentAnswer of multipleQuestions2              
              try{
                 dataSource = getDataSource(request);
                 myConnection = dataSource.getConnection();

  stmt = myConnection.prepareStatement("insert into usedMultipleQuestions2_test(Id,Type,Times,StudentNo,StudentAnswer,source) values(?,'H',?,?,?,?)");
               stmt.clearParameters();
               stmt.setInt(1, intCurrentProblemId);
               stmt.setString(2, times);
               stmt.setString(3, userName);
               stmt.setString(4, inputAnswer_lowerCase);
               stmt.setString(5, source);
               stmt.execute(); 

               //now we want to get correct answers directly from database
               stmt = myConnection.prepareStatement("select correctChoice,solution from multipleQuestions2_test where id=?");
               stmt.clearParameters();
               stmt.setInt(1, intCurrentProblemId);
               rs = stmt.executeQuery();              
               while(rs.next()){
                  correctChoice = rs.getString(1);
                  solution = rs.getString(2);
               }
               mpb.setCorrectChoice(correctChoice);
               mpb.setSolution(solution);  
               mpb.setSource(source);            
               request.setAttribute( Constants.MULTIPLEPROBLEM_KEY, mpb); 
               session.setAttribute( Constants.MULTIPLEPROBLEM_KEY, mpb);  
              }catch (SQLException sqle) {
                 getServlet().log("Connection.process", sqle);
              } finally {           
                 try {
                    if (rs != null) rs.close();
                    if (s != null) s.close();
                    if (stmt != null) stmt.close();
                    if (myConnection != null) myConnection.close();
                 } catch (SQLException e) {
                    getServlet().log("Connection.close", e);
                 }
              }
              break;              
           case 3: //fillblank2 
              String[] correctChoices = null;
              if (correctChoice_lowerCase.indexOf(";")!=-1){
                 correctChoices = correctChoice_lowerCase.split(";");
              }else
                 correctChoices = correctChoice_lowerCase.split("/"); 
              lastCorrect = false;             
              for (int i = 0; i < correctChoices.length; i++){
                 System.out.println("correctChoices[" + i + "]=" + correctChoices[i]);
                 if ((!inputAnswer_lowerCase.equals("")) && inputAnswer_lowerCase.equals(correctChoices[i])){
                    intCorrectAnswers ++;
                    intCorrectAnswers_middle ++;
                    intAnsweredProblems_middle ++;                    
                    lastCorrect = true;
                    tempIntScore = StringSetTransfer.hm_fb.get(new Integer(intCurrentProblemId)).intValue();
                    dbTotalScore += tempIntScore;
                    break;
                 }
              }            
              if (lastCorrect == false){
                 intAnsweredProblems_middle ++;  
              }

              //below we want to handle Table answers
              System.out.println("Inside SmartRandomCheckAction, in fillBlank2, before handling table, dbTotalScore = " + dbTotalScore);
              if (lastCorrect == false && correctChoice_lowerCase.startsWith("table")){
                 try{
                    dataSource = getDataSource(request);
                    myConnection = dataSource.getConnection();
                    stmt = myConnection.prepareStatement("select solution from fillBlank2_test where id=?");
                    stmt.clearParameters();
                    stmt.setInt(1, intCurrentProblemId);
                    rs = stmt.executeQuery();              
                    while(rs.next()){                  
                       solution = rs.getString(1);
                       correctChoice_lowerCase = solution.toLowerCase();
                    }
                    stmt = myConnection.prepareStatement("select term2 from hintMatch where term1=?");
                    stmt.clearParameters();
                    stmt.setString(1, "input");
                    rs = stmt.executeQuery();              
                    while(rs.next()){                  
                       inputAnswer = rs.getString(1);
                       inputAnswer_lowerCase = inputAnswer.toLowerCase();
                    }
                 }catch (SQLException sqle) {
                    getServlet().log("Connection.process", sqle);
                 } finally {           
                    try {
                       if (rs != null) rs.close();
                       if (s != null) s.close();
                       if (stmt != null) stmt.close();
                       if (myConnection != null) myConnection.close();
                    } catch (SQLException e) {
                       getServlet().log("Connection.close", e);
                    }
                 }
                 correctChoices = correctChoice_lowerCase.split(";");
                 String[] inputAnswers = inputAnswer_lowerCase.split(";");
                 tempIntScore = StringSetTransfer.hm_fb.get(new Integer(intCurrentProblemId)).intValue();
                 double tempDbScore = ((double)tempIntScore) / (correctChoices.length - 1); 
                 System.out.println("Inside SmartRandomCheckAction, in fillBlank2, inside handling table, tempDbScore = " + tempDbScore + " and correctChoices.length =" + correctChoices.length + " and correctChoice_lowerCase=" + correctChoice_lowerCase);
                 lastCorrect = true;
                 for (int i=1; i<correctChoices.length; i++){
                    System.out.println("correctChoices[" + i + "]=" + correctChoices[i]);
                    if (correctChoices[i].equalsIgnoreCase(inputAnswers[i]))
                       dbTotalScore += tempDbScore;
                    else
                       lastCorrect = false;
                 }
                 if (lastCorrect){
                    intCorrectAnswers ++;
                    intCorrectAnswers_middle ++; 
                 }
              }
              System.out.println("Inside SmartRandomCheckAction, in fillBlank2, after handling table, dbTotalScore = " + dbTotalScore);

              //below we want to handle the logic of Chinese input, the basic idea is that if the Chinese input cannot exactly match
              //the answer, as long as it contains the key word, it can still be graded as correct
              System.out.println("Inside SmartRandomCheckAction, in fillBlank2, before handling Chinese input, dbTotalScore = " + dbTotalScore);
              if (lastCorrect == false && correctChoice_lowerCase.startsWith("chinese")){
                 correctChoices = correctChoice_lowerCase.split("/");
                 for (int i=0; i<correctChoices.length; i++){
                    if (inputAnswer_lowerCase.contains(correctChoices[i])){
                       intCorrectAnswers ++;
                       intCorrectAnswers_middle ++;                                       
                       lastCorrect = true;
                       tempIntScore = StringSetTransfer.hm_fb.get(new Integer(intCurrentProblemId)).intValue();
                       dbTotalScore += tempIntScore;
                       break;
                    }
                 }
              }

              //to be able to recheck the solutions of fillblank2, we want to record the student answer              
              getServlet().log("In fillBlank2, intCurrentProblemId = " + intCurrentProblemId);
              try{
                 dataSource = getDataSource(request);
                 myConnection = dataSource.getConnection();

  stmt = myConnection.prepareStatement("insert into usedFillBlank2_test(Id,Type,Times,StudentNo,StudentAnswer,source,rightOrWrong) values(?,'M',?,?,?,?,?)");
               stmt.clearParameters();
               stmt.setInt(1, intCurrentProblemId);
               stmt.setString(2, times);
               stmt.setString(3, userName);
               stmt.setString(4, inputAnswer_lowerCase);
               stmt.setString(5, source);
               if (lastCorrect)
                  stmt.setString(6, "T");
               else
                  stmt.setString(6, "F");
               stmt.execute(); 

               //now we want to get correct answers directly from database
               stmt = myConnection.prepareStatement("select statement_1st, statement_2nd, solution from fillBlank2_test where id=?");
               stmt.clearParameters();
               stmt.setInt(1, intCurrentProblemId);
               rs = stmt.executeQuery();              
               while(rs.next()){
                  statement_1st = rs.getString(1);
                  statement_2nd = rs.getString(2);
                  solution = rs.getString(3);
                  //correctChoice_lowerCase = solution.toLowerCase();
               }
               fbb.setStatement_1st(statement_1st);
               fbb.setStatement_2nd(statement_2nd);
               fbb.setSolution(solution);       
               fbb.setSource(source);    
               fbb.setYourSolution(inputAnswer);   
               request.setAttribute( Constants.FILLBLANK_KEY, fbb); 
               session.setAttribute( Constants.FILLBLANK_KEY, fbb);   
              }catch (SQLException sqle) {
                 getServlet().log("Connection.process", sqle);
              } finally {           
                 try {
                    if (rs != null) rs.close();
                    if (s != null) s.close();
                    if (stmt != null) stmt.close();if (correctChoice_lowerCase.indexOf(";")!=-1){
                 correctChoices = correctChoice_lowerCase.split(";");
              }else
                 correctChoices = correctChoice_lowerCase.split("/");
                    if (myConnection != null) myConnection.close();
                 } catch (SQLException e) {
                    getServlet().log("Connection.close", e);
                 }
              }              

              break;
           case 4: //filldoubleblanks2
              String inputAnswer2 = (String)((SmartRandomCheckForm) form).getInputAnswer2();
              inputAnswer2 =  new String(inputAnswer2.getBytes("iso8859-1"), "UTF-8");
              pb2.setInputAnswer2(inputAnswer2);
              String inputAnswer_1st = inputAnswer_lowerCase.trim();
              String inputAnswer_2nd = inputAnswer2.toLowerCase().trim();
              
              int indexOfColon = correctChoice_lowerCase.indexOf(";;");
              String correctChoice_1st = correctChoice_lowerCase.substring(0, indexOfColon);
              String correctChoice_2nd = correctChoice_lowerCase.substring(indexOfColon + 2);  
              /*
              correctChoice_1st = (String)((SmartRandomCheckForm) form).getCorrectAnswer_1st(); 
              correctChoice_2nd = (String)((SmartRandomCheckForm) form).getCorrectAnswer_2nd();
              correctChoice_1st = new String(correctChoice_1st.getBytes("iso8859-1"),"UTF-8");
              correctChoice_2nd = new String(correctChoice_2nd.getBytes("iso8859-1"),"UTF-8");
              */
              System.out.println("Inside SmartRandomCheckAction, under fillDoubleBlanks2_test case, inputAnswer_1st=" + inputAnswer_1st);
              System.out.println("Inside SmartRandomCheckAction, under fillDoubleBlanks2_test case, inputAnswer_2nd=" + inputAnswer_2nd);
              System.out.println("Inside SmartRandomCheckAction, under fillDoubleBlanks2_test case, correctChoice_1st=" + correctChoice_1st);
              System.out.println("Inside SmartRandomCheckAction, under fillDoubleBlanks2_test case, correctChoice_2nd=" + correctChoice_2nd);

              String[] solution_lower_1sts = null;
              String[] solution_lower_2nds = null;
              if (correctChoice_1st.indexOf(";")!=-1){
                 solution_lower_1sts = correctChoice_1st.split(";");
                 System.out.println("Inside SmartRandomCheckAction, under fillDoubleBlanks2_test case, solution_lower_1sts is splitted by ; and solution_lower_1sts.length=" + solution_lower_1sts.length);
              }else if (correctChoice_1st.indexOf("/")!=-1){
                 solution_lower_1sts = correctChoice_1st.split("/");
                 System.out.println("Inside SmartRandomCheckAction, under fillDoubleBlanks2_test case, solution_lower_1sts is splitted by / and solution_lower_1sts.length=" + solution_lower_1sts.length);
              }else{
                 solution_lower_1sts = new String[1];
                 solution_lower_1sts[0] = correctChoice_1st;
                 System.out.println("Inside SmartRandomCheckAction, under fillDoubleBlanks2_test case, solution_lower_1sts is not splitted and solution_lower_1sts[0]=" + solution_lower_1sts[0]); 
              }
              if (correctChoice_2nd.indexOf(";")!=-1){
                 solution_lower_2nds = correctChoice_2nd.split(";");
                 System.out.println("Inside SmartRandomCheckAction, under fillDoubleBlanks2_test case, solution_lower_2nds is splitted by ; and solution_lower_2nds.length=" + solution_lower_2nds.length);
              }else if (correctChoice_2nd.indexOf("/")!=-1){
                 solution_lower_2nds = correctChoice_2nd.split("/");
                 System.out.println("Inside SmartRandomCheckAction, under fillDoubleBlanks2_test case, solution_lower_2nds is splitted by / and solution_lower_2nds.length=" + solution_lower_2nds.length);
              }else{
                 solution_lower_2nds = new String[1];
                 solution_lower_2nds[0] = correctChoice_2nd;
                 System.out.println("Inside SmartRandomCheckAction, under fillDoubleBlanks2_test case, solution_lower_2nds is not splitted and solution_lower_2nds[0]=" + solution_lower_2nds[0]); 
              }
              tempIntScore = StringSetTransfer.hm_fdb.get(new Integer(intCurrentProblemId)).intValue();
              double tempDblScore = (double)tempIntScore / 2.0;
              boolean correct1st = false;
              boolean correct2nd = false;
              for (int i = 0; i < solution_lower_1sts.length; i++){
                 System.out.println("Inside SmartRandomCheckAction, under fillDoubleBlanks2_test case, solution_lower_1sts[" + i + "]= " + solution_lower_1sts[i]);
                 if (!inputAnswer_1st.equals("") && inputAnswer_1st.equals(solution_lower_1sts[i])){
                    correct1st = true;
                    dbTotalScore += tempDblScore;
                    System.out.println("Inside SmartRandomCheckAction, under fillDoubleBlanks2_test case, solution_lower_1sts[" + i + "]= " + solution_lower_1sts[i] + " and correct1st==true"); 
                    break;
                 }
              }
              for (int i = 0; i < solution_lower_2nds.length; i++){
                 System.out.println("Inside SmartRandomCheckAction, under fillDoubleBlanks2_test case, solution_lower_2nds[" + i + "]= " + solution_lower_2nds[i]);
                 if (!inputAnswer_2nd.equals("") && inputAnswer_2nd.equals(solution_lower_2nds[i])){
                    correct2nd = true;
                    dbTotalScore += tempDblScore;
                    System.out.println("Inside SmartRandomCheckAction, under fillDoubleBlanks2_test case, solution_lower_2nds[" + i + "]= " + solution_lower_2nds[i] + " and correct2nd==true"); 
                    break;
                 }
              }
              if (correct1st && correct2nd){
                 intCorrectAnswers ++;
                 intCorrectAnswers_high ++;
                 intAnsweredProblems_high ++;   
                 lastCorrect = true; 
              }else{
                 intAnsweredProblems_high ++;   
                 lastCorrect = false; 
              }

              /*
              outer:
              for (int i = 0; i < solution_lower_1sts.length; i++){
                 for (int j = 0; j < solution_lower_2nds.length; j++){
                    if (!inputAnswer_1st.equals("") && inputAnswer_1st.equals(solution_lower_1sts[i])){
                       if (!inputAnswer_2nd.equals("") && inputAnswer_2nd.equals(solution_lower_2nds[j])){
                          intCorrectAnswers ++;
                          lastCorrect = true; 
                          break outer;
                       }   
                    }
                 }
              }                   
              */
              //to be able to recheck the solutions of fillDoubleblanks2, we want to record the student answer              
              getServlet().log("In fillDoubleBlanks2, intCurrentProblemId = " + intCurrentProblemId);
              try{
                 dataSource = getDataSource(request);
                 myConnection = dataSource.getConnection();

stmt = myConnection.prepareStatement("insert into usedFillDoubleBlanks2_test(Id,Type,Times,StudentNo,StudentAnswer,source,rightOrWrong,correct1st,correct2nd) values(?,'H',?,?,?,?,?,?,?)");
               stmt.clearParameters();
               stmt.setInt(1, intCurrentProblemId);
               stmt.setString(2, times);
               stmt.setString(3, userName);
               stmt.setString(4, inputAnswer_lowerCase+";"+inputAnswer2);
               stmt.setString(5, source);
               if (lastCorrect)
                  stmt.setString(6, "T");
               else
                  stmt.setString(6, "F");
               if (correct1st)
                  stmt.setString(7, "T");
               else
                  stmt.setString(7, "F");
               if (correct2nd)
                  stmt.setString(8, "T");
               else
                  stmt.setString(8, "F");
               stmt.execute(); 

               //now we want to get correct answers directly from database
               stmt = myConnection.prepareStatement("select statement_1st, statement_2nd, statement_3rd, solution_1st, solution_2nd from fillDoubleBlanks2_test where id=?");
               stmt.clearParameters();
               stmt.setInt(1, intCurrentProblemId);
               rs = stmt.executeQuery();              
               while(rs.next()){
                  statement_1st = rs.getString(1);
                  statement_2nd = rs.getString(2);
                  statement_3rd = rs.getString(3);
                  solution_1st = rs.getString(4);
                  solution_2nd = rs.getString(5);
                  //correctChoice_1st = solution_1st.toLowerCase();
                  //correctChoice_2nd = solution_2nd.toLowerCase();
               }
               fdbb.setStatement_1st(statement_1st);
               fdbb.setStatement_2nd(statement_2nd);              
               fdbb.setStatement_3rd(statement_3rd);
               fdbb.setSolution_1st(solution_1st);
               fdbb.setSolution_2nd(solution_2nd);
               fdbb.setYourSolution_1st(inputAnswer);
               fdbb.setYourSolution_2nd(inputAnswer2);
               fdbb.setSource(source);
               request.setAttribute( Constants.FILLDOUBLEBLANK_KEY, fdbb); 
               session.setAttribute( Constants.FILLDOUBLEBLANK_KEY, fdbb);                
              
              }catch (SQLException sqle) {
                 getServlet().log("Connection.process", sqle);
              } finally {           
                 try {
                    if (rs != null) rs.close();
                    if (s != null) s.close();
                    if (stmt != null) stmt.close();
                    if (myConnection != null) myConnection.close();
                 } catch (SQLException e) {
                    getServlet().log("Connection.close", e);
                 }
              }
              break;
           case 6: //multiply 
              System.out.println("Inside SmartRandomCheckAction, now we are in case 6");             
              inputAnswer2 = (String)((SmartRandomCheckForm) form).getInputAnswer2();
              pb2.setInputAnswer2(inputAnswer2);
              String inputAnswer3 = (String)((SmartRandomCheckForm) form).getInputAnswer3();   
              pb2.setInputAnswer3(inputAnswer3);           
              inputAnswer_1st = inputAnswer_lowerCase.trim();
              inputAnswer_2nd = inputAnswer2.toLowerCase().trim();
              String inputAnswer_3rd = inputAnswer3.toLowerCase().trim();
              
              p1Solution = (String)((SmartRandomCheckForm) form).getP1Solution();
              p2Solution = (String)((SmartRandomCheckForm) form).getP2Solution();
              rSolution = (String)((SmartRandomCheckForm) form).getResultSolution();
              System.out.println("Inside SmartRandomCheckAction, rSolution = " + rSolution);
              
              tempIntScore = StringSetTransfer.hm_m.get(new Integer(intCurrentProblemId)).intValue();
              tempDblScore = (double)tempIntScore / 3.0;
              correct1st = false;
              correct2nd = false;
              boolean correct3rd = false;
              
              if (inputAnswer_1st!=null && inputAnswer_1st.equals(p1Solution)){
                 correct1st = true;
                 dbTotalScore += tempDblScore;                    
              }              
              if (inputAnswer_2nd!=null && inputAnswer_2nd.equals(p2Solution)){
                 correct2nd = true;
                 dbTotalScore += tempDblScore;                    
              }
              if (inputAnswer_3rd!=null && inputAnswer_3rd.equals(rSolution)){
                 correct3rd = true;
                 dbTotalScore += tempDblScore;                    
               }

               if (correct1st && correct2nd && correct3rd){
                 intCorrectAnswers ++;
                 intCorrectAnswers_high ++;
                 intAnsweredProblems_high ++;   
                 lastCorrect = true; 
               }else{
                 intAnsweredProblems_high ++;   
                 lastCorrect = false; 
               }
              
              //to be able to recheck the solutions of multiply_test, we want to record the student answer              
              getServlet().log("In multiply, intCurrentProblemId = " + intCurrentProblemId);
              System.out.println("In multiply, intCurrentProblemId = " + intCurrentProblemId);
              try{
                 dataSource = getDataSource(request);
                 myConnection = dataSource.getConnection();

                System.out.println("In multiply, inside try block, after dataSource.getConnection()");
              
                stmt = myConnection.prepareStatement("insert into usedMultiply_test(Id,Type,Times,StudentNo,StudentAnswer,source,rightOrWrong,correct1,correct2,rCorrect) values(?,'H',?,?,?,?,?,?,?,?)");
               stmt.clearParameters();
               stmt.setInt(1, intCurrentProblemId);
               stmt.setString(2, times);
               stmt.setString(3, userName);
               String totalAnswer = inputAnswer_lowerCase+";"+inputAnswer2+";"+inputAnswer3;
               stmt.setString(4, totalAnswer);
               stmt.setString(5, source);
               if (lastCorrect)
                  stmt.setString(6, "T");
               else
                  stmt.setString(6, "F");
               if (correct1st)
                  stmt.setString(7, "T");
               else
                  stmt.setString(7, "F");
               if (correct2nd)
                  stmt.setString(8, "T");
               else
                  stmt.setString(8, "F");
               if (correct3rd)
                  stmt.setString(9, "T");
               else
                  stmt.setString(9, "F");
               stmt.execute();               
                
               System.out.println("In multiply, now we want to get correct answers directly from database");
               //now we want to get correct answers directly from database
               stmt = myConnection.prepareStatement("select factor1, factor2, solution1, solution2, rSolution from multiply_test where id=?");
               stmt.clearParameters();
               stmt.setInt(1, intCurrentProblemId);
               rs = stmt.executeQuery();              
               while(rs.next()){
                  factor1 = rs.getString(1);
                  factor2 = rs.getString(2);
                  String tmpString = rs.getString(3);
                  p1Solution = rs.getString(3);
                  p2Solution = rs.getString(4);
                  rSolution = rs.getString(5);                  
               }
               mb.setFactor1(factor1);
               mb.setFactor2(factor2);  
               p2Solution += "_";
               int margin = p2Solution.length() - p1Solution.length();
               for (int i=0; i<margin; i++)   
                  p1Solution = "_" + p1Solution;                
               mb.setP1Solution(p1Solution);
               mb.setP2Solution(p2Solution);
               mb.setRSolution(rSolution);      
               mb.setResultSolution(rSolution);

               inputAnswer2 += "_";
               margin = inputAnswer2.length() - inputAnswer.length();               
               for (int i=0; i<margin; i++)   
                  inputAnswer = "_" + inputAnswer; 
               mb.setYourSolution_1st(inputAnswer);
               mb.setYourSolution_2nd(inputAnswer2);
               mb.setYourSolution_3rd(inputAnswer3);
               mb.setSource(source);
               request.setAttribute( Constants.MULTIPLY_KEY, mb); 
               session.setAttribute( Constants.MULTIPLY_KEY, mb);            
               System.out.println("In multiply, now we are at the end of the request.setAttribute()");
              }catch (SQLException sqle) {
                 getServlet().log("Connection.process", sqle);
              } finally {           
                 try {
                    if (rs != null) rs.close();
                    if (s != null) s.close();
                    if (stmt != null) stmt.close();
                    if (myConnection != null) myConnection.close();
                 } catch (SQLException e) {
                    getServlet().log("Connection.close", e);
                 }
              }            
              break;
           case 7: //division 
              System.out.println("Inside SmartRandomCheckAction, now we are in case 7");             
              inputAnswer2 = (String)((SmartRandomCheckForm) form).getInputAnswer2();
              pb2.setInputAnswer2(inputAnswer2);
              inputAnswer3 = (String)((SmartRandomCheckForm) form).getInputAnswer3();   
              pb2.setInputAnswer3(inputAnswer3); 
              String inputAnswer4 = (String)((SmartRandomCheckForm) form).getInputAnswer4();   
              String inputAnswer5 = (String)((SmartRandomCheckForm) form).getInputAnswer5();  
              String inputAnswer6 = (String)((SmartRandomCheckForm) form).getInputAnswer6();        
              String inputAnswer7 = (String)((SmartRandomCheckForm) form).getInputAnswer7();
              inputAnswer_1st = inputAnswer_lowerCase.trim();
              inputAnswer_2nd = inputAnswer2.toLowerCase().trim();
              inputAnswer_3rd = inputAnswer3.toLowerCase().trim();
              String inputAnswer_4th = inputAnswer4.toLowerCase().trim();
              String inputAnswer_5th = inputAnswer5.toLowerCase().trim();
              String inputAnswer_6th = inputAnswer6.toLowerCase().trim();
              String inputAnswer_7th = inputAnswer7.toLowerCase().trim();
              
              result = (String)((SmartRandomCheckForm) form).getResult();
              product1 = (String)((SmartRandomCheckForm) form).getProduct1();
              remain1 = (String)((SmartRandomCheckForm) form).getRemain1();
              product2 = (String)((SmartRandomCheckForm) form).getProduct2();
              remain2 = (String)((SmartRandomCheckForm) form).getRemain2(); 
              product3 = (String)((SmartRandomCheckForm) form).getProduct3();
              remain3 = (String)((SmartRandomCheckForm) form).getRemain3(); 
              System.out.println("Inside SmartRandomCheckAction, result = " + result);
              
              String strLevel = (String)((SmartRandomCheckForm) form).getLevel();
              int level = Integer.parseInt(strLevel);
              db.setLevel(level);
              tempIntScore = StringSetTransfer.hm_d.get(new Integer(intCurrentProblemId)).intValue();              
              correct1st = false;
              correct2nd = false;
              correct3rd = false;
              boolean correct4th = false;
              boolean correct5th = false;
              boolean correct6th = false;
              boolean correct7th = false;              
              
              if (level == 3){
                 tempDblScore = (double)tempIntScore / 3.0;
                 if (inputAnswer_1st!=null && inputAnswer_1st.equals(result)){
                    correct1st = true;
                    dbTotalScore += tempDblScore;                    
                 }              
                 if (inputAnswer_2nd!=null && inputAnswer_2nd.equals(product1)){
                    correct2nd = true;
                    dbTotalScore += tempDblScore;                    
                 }
                 if (inputAnswer_3rd!=null && inputAnswer_3rd.equals(remain1)){
                    correct3rd = true;
                    dbTotalScore += tempDblScore;                    
                  }
              }else if (level == 5){
                 tempDblScore = (double)tempIntScore / 5.0; 
                 if (inputAnswer_1st!=null && inputAnswer_1st.equals(result)){
                    correct1st = true;
                    dbTotalScore += tempDblScore;                    
                 }              
                 if (inputAnswer_2nd!=null && inputAnswer_2nd.equals(product1)){
                    correct2nd = true;
                    dbTotalScore += tempDblScore;                    
                 }
                 if (inputAnswer_3rd!=null && inputAnswer_3rd.equals(remain1)){
                    correct3rd = true;
                    dbTotalScore += tempDblScore;                    
                 }
                 if (inputAnswer_4th!=null && inputAnswer_4th.equals(product2)){
                    correct4th = true;
                    dbTotalScore += tempDblScore;                    
                 }
                 if (inputAnswer_5th!=null && inputAnswer_5th.equals(remain2)){
                    correct5th = true;
                    dbTotalScore += tempDblScore;                    
                 }
              }else if (level == 7){  
                 tempDblScore = (double)tempIntScore / 7.0;             
                 if (inputAnswer_1st!=null && inputAnswer_1st.equals(result)){
                    correct1st = true;
                    dbTotalScore += tempDblScore;                    
                 }              
                 if (inputAnswer_2nd!=null && inputAnswer_2nd.equals(product1)){
                    correct2nd = true;
                    dbTotalScore += tempDblScore;                    
                 }
                 if (inputAnswer_3rd!=null && inputAnswer_3rd.equals(remain1)){
                    correct3rd = true;
                    dbTotalScore += tempDblScore;                    
                 }
                 if (inputAnswer_4th!=null && inputAnswer_4th.equals(product2)){
                    correct4th = true;
                    dbTotalScore += tempDblScore;                    
                 }
                 if (inputAnswer_5th!=null && inputAnswer_5th.equals(remain2)){
                    correct5th = true;
                    dbTotalScore += tempDblScore;                    
                 }
                 if (inputAnswer_6th!=null && inputAnswer_6th.equals(product3)){
                    correct6th = true;
                    dbTotalScore += tempDblScore;                    
                 }
                 if (inputAnswer_7th!=null && inputAnswer_7th.equals(remain3)){
                    correct7th = true;
                    dbTotalScore += tempDblScore;                    
                 }
               }               

               if (level == 3){
                  if (correct1st && correct2nd && correct3rd){
                     intCorrectAnswers ++;
                     intCorrectAnswers_high ++;
                     intAnsweredProblems_high ++;   
                     lastCorrect = true; 
                  }else{
                     intAnsweredProblems_high ++;   
                     lastCorrect = false; 
                  }
               }else if (level == 5){
                  if (correct1st && correct2nd && correct3rd && correct4th && correct5th){
                     intCorrectAnswers ++;
                     intCorrectAnswers_high ++;
                     intAnsweredProblems_high ++;   
                     lastCorrect = true; 
                  }else{
                     intAnsweredProblems_high ++;   
                     lastCorrect = false; 
                  }
               }else if (level == 7){
                  if (correct1st && correct2nd && correct3rd && correct4th && correct5th && correct6th && correct7th){
                     intCorrectAnswers ++;
                     intCorrectAnswers_high ++;
                     intAnsweredProblems_high ++;   
                     lastCorrect = true; 
                  }else{
                     intAnsweredProblems_high ++;   
                     lastCorrect = false; 
                  }
               }
              
              //to be able to recheck the solutions of multiply_test, we want to record the student answer              
              getServlet().log("In division, intCurrentProblemId = " + intCurrentProblemId);
              System.out.println("In division, intCurrentProblemId = " + intCurrentProblemId);
              try{
                 dataSource = getDataSource(request);
                 myConnection = dataSource.getConnection();

                System.out.println("In division, inside try block, after dataSource.getConnection()");
              
                stmt = myConnection.prepareStatement("insert into usedDivision_test(Id,Type,Times,StudentNo,StudentAnswer,source,level,rightOrWrong,correct1,correct2,correct3,correct4,correct5,correct6,correct7) values(?,'H',?,?,?,?,?,?,?,?,?,?,?,?,?)");
               stmt.clearParameters();
               stmt.setInt(1, intCurrentProblemId);
               stmt.setString(2, times);
               stmt.setString(3, userName);
               String totalAnswer = inputAnswer_lowerCase+";"+inputAnswer2+";"+inputAnswer3+";"+inputAnswer4+";"+inputAnswer5+";"+inputAnswer6+";"+inputAnswer7;
               stmt.setString(4, totalAnswer);
               stmt.setString(5, source);
               stmt.setInt(6, level);
               if (lastCorrect)
                  stmt.setString(7, "T");
               else
                  stmt.setString(7, "F");
               if (correct1st)
                  stmt.setString(8, "T");
               else
                  stmt.setString(8, "F");
               if (correct2nd)
                  stmt.setString(9, "T");
               else
                  stmt.setString(9, "F");
               if (correct3rd)
                  stmt.setString(10, "T");
               else
                  stmt.setString(10, "F");
               if (correct4th)
                  stmt.setString(11, "T");
               else
                  stmt.setString(11, "F");
               if (correct5th)
                  stmt.setString(12, "T");
               else
                  stmt.setString(12, "F");
               if (correct6th)
                  stmt.setString(13, "T");
               else
                  stmt.setString(13, "F");
               if (correct7th)
                  stmt.setString(14, "T");
               else
                  stmt.setString(14, "F"); 
               stmt.execute();               
                
               System.out.println("In division, now we want to get correct answers directly from database");
               //now we want to get correct answers directly from database
               stmt = myConnection.prepareStatement("select divider, divided, result, product1, remain1, product2, remain2, product3, remain3, level from division_test where id=?");
               stmt.clearParameters();
               stmt.setInt(1, intCurrentProblemId);
               rs = stmt.executeQuery();              
               while(rs.next()){
                  db.setDivider(rs.getString(1));
                  db.setDivided(rs.getString(2));
                  result = rs.getString(3);
                  product1 = rs.getString(4);
                  remain1 = rs.getString(5);
                  product2 = rs.getString(6);
                  remain2 = rs.getString(7);
                  product3 = rs.getString(8);    
                  remain3 = rs.getString(9); 
                  db.setLevel(rs.getInt(10));             
               }
               String tmpPad = ""; String tmpPad2 = ""; String tmpPad3 = ""; String tmpPad4 = ""; String tmpPadR = "";
               if (db.getDivider().length()==1)   
                  tmpPad = "___"; 
               else if (db.getDivider().length()==2) 
                  tmpPad = "____";
               else if (db.getDivider().length()==3) 
                  tmpPad = "_____";
               else
                  tmpPad = "______";
               if (result.length()==db.getDivided().length())
                  db.setResult(tmpPad + result);
               else{
                  tmpPadR = tmpPad;
                  for (int i=0;i<db.getDivided().length()-result.length();i++)
                     tmpPadR += "_";
                  db.setResult(tmpPadR + result);
               }
               db.setProduct1(tmpPad + product1);
               if (remain1.length()==db.getDivider().length())
                  tmpPad2 = tmpPad + "_";  
               else if (remain1.length() > db.getDivider().length())
                  tmpPad2 = tmpPad;
               else  
                  tmpPad2 = tmpPad + "__";                              
               db.setRemain1(tmpPad2 + remain1);
               if (product2.length()==db.getDivider().length())
                  tmpPad2 = tmpPad + "_";   
               else if (product2.length() > db.getDivider().length())
                  tmpPad2 = tmpPad;  
               else   
                  tmpPad2 = tmpPad + "__";          
               db.setProduct2(tmpPad2 + product2);
               if (remain2.length()==db.getDivider().length())
                  tmpPad3 = "_" + tmpPad; 
               else if (remain2.length() > db.getDivider().length())
                  tmpPad3 = "" + tmpPad; 
               else
                  tmpPad3 = "__" + tmpPad; 
               db.setRemain2(tmpPad3 + remain2);  
               if (product3.length()==db.getDivider().length())
                  tmpPad3 = "_" + tmpPad;  
               else if (product3.length() > db.getDivider().length())
                  tmpPad3 = "" + tmpPad; 
               else 
                  tmpPad3 = "__" + tmpPad; 
               db.setProduct3(tmpPad3 + product3);
               if (remain3.length()==db.getDivider().length())
                  tmpPad4 = "__" + tmpPad; 
               else if (remain3.length()>db.getDivider().length())
                  tmpPad4 = "_" + tmpPad; 
               else
                  tmpPad4 = "___" + tmpPad; 
               db.setRemain3(tmpPad4 + remain3);
               //handles the case of divided with 4 or more digits
               if (db.getDivided().length() > 3){
                  for (int i=3; i<db.getDivided().length(); i++){
                     db.setRemain1("_" + db.getRemain1());
                     db.setProduct2("_" + db.getProduct2());
                     db.setRemain2("_" + db.getRemain2());
                     db.setProduct3("_" + db.getProduct3());
                     db.setRemain3("_" + db.getRemain3());
                  }                  
               }
               
               if (inputAnswer.length()==db.getDivided().length())
                  db.setYourResult(tmpPad + inputAnswer);
               else{
                  tmpPadR = tmpPad;
                  for (int i=0;i<db.getDivided().length()-inputAnswer.length();i++)
                     tmpPadR += "_";
                  db.setYourResult(tmpPadR + inputAnswer);
               }               
               db.setYourProduct1(tmpPad + inputAnswer2);
               if (inputAnswer3.length()==db.getDivider().length())
                  tmpPad2 = tmpPad + "_";  
               else if (inputAnswer3.length() > db.getDivider().length())
                  tmpPad2 = tmpPad; 
               else
                  tmpPad2 = tmpPad + "__"; 
               db.setYourRemain1(tmpPad2 + inputAnswer3);
               if (inputAnswer4.length()==db.getDivider().length())
                  tmpPad2 = tmpPad + "_";   
               else if (inputAnswer4.length() > db.getDivider().length())
                  tmpPad2 = tmpPad;
               else
                  tmpPad2 = tmpPad + "__"; 
               db.setYourProduct2(tmpPad2 + inputAnswer4);
               if (inputAnswer5.length()==db.getDivider().length())
                  tmpPad3 = "_" + tmpPad; 
               else if (inputAnswer5.length() > db.getDivider().length())
                  tmpPad3 = "" + tmpPad; 
               else
                  tmpPad3 = "__" + tmpPad; 
               db.setYourRemain2(tmpPad3 + inputAnswer5);
               if (inputAnswer6.length()==db.getDivider().length())
                  tmpPad3 = "_" + tmpPad; 
               else if (inputAnswer6.length() > db.getDivider().length())
                  tmpPad3 = "" + tmpPad;
               else
                  tmpPad3 = "__" + tmpPad;
               db.setYourProduct3(tmpPad3 + inputAnswer6);
               if (inputAnswer7.length()==db.getDivider().length())
                  tmpPad4 = "__" + tmpPad; 
               else if (inputAnswer7.length() > db.getDivider().length())
                  tmpPad4 = "_" + tmpPad; 
               else
                  tmpPad4 = "___" + tmpPad; 
               db.setYourRemain3(tmpPad4 + inputAnswer7);
               //handles the case of divided with 4 or more digits
               if (db.getDivided().length() > 3){
                  for (int i=3; i<db.getDivided().length(); i++){
                     db.setYourRemain1("_" + db.getYourRemain1());
                     db.setYourProduct2("_" + db.getYourProduct2());
                     db.setYourRemain2("_" + db.getYourRemain2());
                     db.setYourProduct3("_" + db.getYourProduct3());
                     db.setYourRemain3("_" + db.getYourRemain3());
                  }                  
               } 

               db.setSource(source);
               request.setAttribute( Constants.DIVISION_KEY, db); 
               session.setAttribute( Constants.DIVISION_KEY, db);            
               System.out.println("In division, now we are at the end of the request.setAttribute()");
              }catch (SQLException sqle) {
                 getServlet().log("Connection.process", sqle);
              } finally {           
                 try {
                    if (rs != null) rs.close();
                    if (s != null) s.close();
                    if (stmt != null) stmt.close();
                    if (myConnection != null) myConnection.close();
                 } catch (SQLException e) {
                    getServlet().log("Connection.close", e);
                 }
              }            
              break; 
           case 8: //composition 
              System.out.println("Inside SmartRandomCheckAction, now we are in case 8");             
              
              answerSentences = (String)((SmartRandomCheckForm) form).getAnswerSentences();
              answerWords = (String)((SmartRandomCheckForm) form).getAnswerWords();
              fullScore = (String)((SmartRandomCheckForm) form).getFullScore(); 
              period = (String)((SmartRandomCheckForm) form).getPeriod(); 
              
              String keyWord = "";
              String commonWord = "";
              try{
                 dataSource = getDataSource(request);
                 myConnection = dataSource.getConnection();

                 stmt = myConnection.prepareStatement("select answerSentences, answerWords, keyWords from composition_test where id=?");
                 stmt.clearParameters();
                 stmt.setInt(1, intCurrentProblemId);
                 rs = stmt.executeQuery();              
                 while(rs.next()){
                    answerSentences = rs.getString(1);                    
                    answerWords = rs.getString(2);       
                    keyWord = rs.getString(3);                          
                 }

                 stmt = myConnection.prepareStatement("select term2 from hintMatch where term1=?");
                 stmt.clearParameters();
                 stmt.setString(1, "compositionYourAnswer");
                 rs = stmt.executeQuery();              
                 while(rs.next()){
                    inputAnswer = rs.getString(1);                                               
                 }
                 /* 
                 stmt = myConnection.prepareStatement("select term2 from hintMatch where term1=?");
                 stmt.clearParameters();
                 stmt.setString(1, "commonWord");
                 rs = stmt.executeQuery();              
                 while(rs.next()){
                    commonWord = rs.getString(1);                                               
                 }
                 */  
              }catch (SQLException sqle) {
                 getServlet().log("Connection.process", sqle);
              } finally {           
                 try {
                    if (rs != null) rs.close();
                    if (s != null) s.close();
                    if (stmt != null) stmt.close();
                    if (myConnection != null) myConnection.close();
                 } catch (SQLException e) {
                    getServlet().log("Connection.close", e);
                 }
              }                
             
              int intFullScore = Integer.parseInt(fullScore);
              c2b.setFullScore(intFullScore);            
              
              System.out.println("Inside SmartRandomCheckAction, fullScore = " + fullScore); 
              System.out.println("Inside SmartRandomCheckAction, composition, case 8, inputAnswer = " + inputAnswer);              
              System.out.println("Inside SmartRandomCheckAction, composition, case 8, answerSentences = " + answerSentences);                     
                
              HashSet sSet = new HashSet();
              String[] aSes = answerSentences.split("，");
              String[] aSes2, aSes3; 
              for (int i=0; i<aSes.length;i++){
                 if (aSes[i].contains("。"))
                    aSes2 = aSes[i].split("。");
                 else if (aSes[i].contains("；"))
                    aSes2 = aSes[i].split("；");
                 else if (aSes[i].contains("？"))
                    aSes2 = aSes[i].split("？");
                 else if (aSes[i].contains("："))
                    aSes2 = aSes[i].split("：");
                 else if (aSes[i].contains("！"))
                    aSes2 = aSes[i].split("！");
                 else 
                    aSes2 = aSes[i].split("，");
   
                 if (aSes2.length>1){
                    for (int j=0; j<aSes2.length; j++){
                       if (aSes2[j].contains("。"))
                          aSes3 = aSes2[j].split("。");
                       else if (aSes2[j].contains("；"))
                          aSes3 = aSes2[j].split("；");
                       else if (aSes2[j].contains("？"))
                          aSes3 = aSes2[j].split("？");
                       else if (aSes2[j].contains("："))
                          aSes3 = aSes2[j].split("：");
                       else if (aSes2[j].contains("！"))
                          aSes3 = aSes2[j].split("！");
                       else 
                          aSes3 = aSes2[j].split("，");                       

                       for (int k=0; k<aSes3.length; k++)
                          sSet.add(aSes3[k]); 
                    }
                 }else
                    for (int j=0; j<aSes2.length; j++)
                       sSet.add(aSes2[j]);
              }
              System.out.println("Inside SmartRandomCheckAction, composition, case 8, aSes.length = " + aSes.length);
              System.out.println("Inside SmartRandomCheckAction, case 8, sSet.size() = " + sSet.size());              
 
              Object[] obj = sSet.toArray();
              for (int i=0; i<obj.length; i++){
                 String tmpStr = (String)obj[i];
                 System.out.println("obj[" + i + "] = " + tmpStr); 
              }             
  
              HashSet wSet = new HashSet();
              String[] aWes = answerWords.split("/");
              for (int i=0; i<aWes.length;i++)
                 wSet.add(aWes[i].trim());
              System.out.println("Inside SmartRandomCheckAction, case 8, wSet.size() = " + wSet.size()); 

              String filePath = TOMCAT_PATH + cNote + "commonWord" + cNote + "commonWord.txt";
              commonWord = StringSetTransfer.getString(filePath);
              HashSet wSet2 = new HashSet();
              String[] commonWords = commonWord.split(";");
              for (int i=0; i<commonWords.length;i++)
                 wSet2.add(commonWords[i].trim());             
              System.out.println("Inside SmartRandomCheckAction, case 8, wSet2.size() = " + wSet2.size()); 
              //we want to extend the commonWord every time by saving it
              HashSet wSet3 = new HashSet(wSet2);
              wSet3.addAll(wSet);
              String savedCommonWord = "";
              Object[] objSavedCommonWords = wSet3.toArray();
              for (int i=0; i<objSavedCommonWords.length; i++){
                 String tmpStr = (String)objSavedCommonWords[i];
                 savedCommonWord += tmpStr + ";";
              }
              StringSetTransfer.saveString(filePath, savedCommonWord);  

              HashSet ysSet = new HashSet();
              String[] inSes = inputAnswer.split("，");               
              String[] inSes2, inSes3;
              for (int i=0; i<inSes.length; i++){   
                 //System.out.println("inSes[" + i + "] = " + inSes[i]);
                 if (inSes[i].contains("。"))
                    inSes2 = inSes[i].split("。");
                 else if (inSes[i].contains("；"))
                    inSes2 = inSes[i].split("；");
                 else if (inSes[i].contains("？"))
                    inSes2 = inSes[i].split("？");
                 else if (inSes[i].contains("："))
                    inSes2 = inSes[i].split("：");
                 else if (inSes[i].contains("！"))
                    inSes2 = inSes[i].split("！");
                 else 
                    inSes2 = inSes[i].split("，");

                 if (inSes2.length>1){
                    for (int j=0; j<inSes2.length; j++){
                       if (inSes2[j].contains("。"))
                          inSes3 = inSes2[j].split("。");
                       else if (inSes2[j].contains("；"))
                          inSes3 = inSes2[j].split("；");
                       else if (inSes2[j].contains("？"))
                          inSes3 = inSes2[j].split("？");
                       else if (inSes2[j].contains("："))
                          inSes3 = inSes2[j].split("：");
                       else if (inSes2[j].contains("！"))
                          inSes3 = inSes2[j].split("！");
                       else 
                          inSes3 = inSes2[j].split("，");                       

                       for (int k=0; k<inSes3.length; k++)
                          ysSet.add(inSes3[k]); 
                    }
                 }else
                    for (int j=0; j<inSes2.length; j++)
                       ysSet.add(inSes2[j]);                
              } 

              System.out.println("Inside SmartRandomCheckAction, composition, case 8,  inSes.length=" + inSes.length); 
              System.out.println("Inside SmartRandomCheckAction, composition, case 8,  ysSet.size()=" + ysSet.size()); 

              int tmpScore = 0; 
              HashSet relatedWordSet = new HashSet();
              Object[] objYs = ysSet.toArray();
              for (int i=0; i<objYs.length; i++){
                 String tmpStr = (String)objYs[i];
                 if (sSet.contains(tmpStr)){
                    tmpScore += 10;
                 }else{
                    Object[] words = wSet.toArray();
                    for (int j2=0; j2<words.length; j2++){
                       String tmpStr2 = (String)words[j2];
                       if (tmpStr.contains(tmpStr2)){
                          tmpScore += 2;
                          relatedWordSet.add(tmpStr2);
                       }
                    }
                 }                 
              }

              System.out.println("Inside SmartRandomCheckAction, case 8, after reviewing sentences, tmpScore = " + tmpScore); 
              System.out.println("Inside SmartRandomCheckAction, case 8, after reviewing sentences, relatedWordSet.size() = " + relatedWordSet.size());        
              
              wSet2.removeAll(wSet);
              Object[] words2 = wSet2.toArray();
              int commonWordCount = 0;
              for (int i=0; i<words2.length; i++){
                 String tmpStr = (String)(words2[i]);
                 if (inputAnswer.contains(tmpStr)){
                    tmpScore += 4;
                    commonWordCount ++;
                 }
              }
              System.out.println("Inside SmartRandomCheckAction, case 8, after reviewing common words, tmpScore = " + tmpScore + " and commonWordCount = " + commonWordCount);             
              
              String comment = "你一共写了" + ysSet.size() + "句话，使用了" + relatedWordSet.size() + "个范文中的词语；此外，还使用了" + commonWordCount + "个一般性词语。";
              if (ysSet.size()<20)
                 comment += "建议你以后丰富写作内容，争取写出篇幅更长的作文。";
              else if (ysSet.size() < 30)
                 comment += "你的文章篇幅比较合适。";
              else if (ysSet.size() < 40)
                 comment += "你的文章篇幅达到了要求。";
              else
                 comment += "你的文章篇幅很长。";
                 
              if (relatedWordSet.size() < wSet.size()/2){
                 String[] keyWords = keyWord.split(":");
                 boolean hasKeyWord = false;
                 for (int i=0; i<keyWords.length; i++){
                    if (inputAnswer.contains(keyWords[i]))
                       hasKeyWord = true; 
                 }
                 if (hasKeyWord)
                    comment += "建议你的写作要集中笔墨，多写和题目相关的内容。";
                 else
                    comment += "请注意检查你的文章，看是否跑题。";
              }else
                 comment += "你的写作比较切合题目要求。";

              if (commonWordCount < 20)
                 comment += "你的一般词汇比较贫乏，建议你以后扩大阅读量，使自己有意义的词汇更加丰富。";
              else if (commonWordCount < 40)
                 comment += "你的一般词汇比较丰富，建议你以后扩大阅读量，使自己有意义的词汇更加丰富。";
              else if (commonWordCount < 60)
                 comment += "你的一般词汇已经达到了相当丰富的程度，建议你以后扩大阅读量，使自己有意义的词汇更加丰富。";
              else
                 comment += "你的一般词汇已经很丰富，建议你以后更加注重提高写作技巧，写出更加绚丽多彩的文章。";
                 
              c2b.setComment(comment);

              double scoreRatio = ((double)tmpScore)/((double)intFullScore);              
              if (scoreRatio > 1)
                 scoreRatio = 1;
              c2b.setScoreRatio(scoreRatio);

              tempIntScore = StringSetTransfer.hm_c2.get(new Integer(intCurrentProblemId)).intValue();  
              dbTotalScore += scoreRatio * tempIntScore;
              System.out.println("Inside SmartRandomCheckAction, composition, case 8, tmpScore = " + tmpScore + "; scoreRatio = " + scoreRatio);
          
              if (scoreRatio >= 1){
                 intCorrectAnswers ++;
                 intCorrectAnswers_high ++;
                 intAnsweredProblems_high ++;   
                 lastCorrect = true; 
              }else{
                 intAnsweredProblems_high ++;   
                 lastCorrect = false; 
              }

              /*
              Object[] obj2 = sSet.toArray();
              for (int i=0; i<obj2.length; i++){
                 String tmpStr = (String)obj2[i];
                 if (inputAnswer.contains(tmpStr)){
                    tmpScore += 10;
                   
                    //we want to count the words in the sameple composition only once
                    Object[] words = wSet.toArray();
                    for (int j2=0; j2<words.length; j2++){
                       String tmpStr2 = (String)words[j2];
                       if (tmpStr.contains(tmpStr2))
                          wSet.remove(tmpStr2);
                    }
                 }
              }
              */              
              
              //to be able to recheck the solutions of compositiontest, we want to record the student answer              
              getServlet().log("In composition, intCurrentProblemId = " + intCurrentProblemId);
              System.out.println("In composition, intCurrentProblemId = " + intCurrentProblemId);
              try{
                 dataSource = getDataSource(request);
                 myConnection = dataSource.getConnection();

                System.out.println("In composition, inside try block, after dataSource.getConnection()");
              
                stmt = myConnection.prepareStatement("insert into usedComposition_test(Id,Type,Times,StudentNo,StudentAnswer,source,scoreRatio) values(?,'H',?,?,?,?,?)");
               stmt.clearParameters();
               stmt.setInt(1, intCurrentProblemId);
               stmt.setString(2, times);
               stmt.setString(3, userName);               
               stmt.setString(4, inputAnswer);
               stmt.setString(5, source);
               stmt.setDouble(6, scoreRatio);               
               stmt.execute();               
                
               System.out.println("In composition, now we want to get correct answers directly from database");
               //now we want to get correct answers directly from database
               stmt = myConnection.prepareStatement("select statement, answerSentences, answerWords from composition_test where id=?");
               stmt.clearParameters();
               stmt.setInt(1, intCurrentProblemId);
               rs = stmt.executeQuery();              
               while(rs.next()){
                  c2b.setStatement(rs.getString(1));
                  c2b.setAnswerSentences(rs.getString(2));                   
                  c2b.setAnswerWords(rs.getString(3));             
               }
               c2b.setYourSentences(inputAnswer);             

               c2b.setSource(source);
               request.setAttribute( Constants.COMPOSITION_KEY, c2b); 
               session.setAttribute( Constants.COMPOSITION_KEY, c2b);            
               System.out.println("In composition, now we are at the end of the request.setAttribute()");
              }catch (SQLException sqle) {
                 getServlet().log("Connection.process", sqle);
              } finally {           
                 try {
                    if (rs != null) rs.close();
                    if (s != null) s.close();
                    if (stmt != null) stmt.close();
                    if (myConnection != null) myConnection.close();
                 } catch (SQLException e) {
                    getServlet().log("Connection.close", e);
                 }
              }            
              break;  
           default:
              break;
        }
        pb2.setCurrentProblemId(intCurrentProblemId);
        
        /*
        double[] result =  StringSetTransfer.rate(lastCorrect, lastType.charAt(0));         
        dbTotalScore += result[0];        
        intAnsweredProblems_low += (int)result[1];
        intCorrectAnswers_low += (int)result[2];
        intAnsweredProblems_middle += (int)result[3];
        intCorrectAnswers_middle += (int)result[4]; 
        intAnsweredProblems_high += (int)result[5];       
        intCorrectAnswers_high += (int)result[6];        
        */
        pb2.setCorrectAnswers(intCorrectAnswers);
        pb2.setLastCorrect(lastCorrect);
        pb2.setTotalScore(dbTotalScore);
        pb2.setCorrectAnswers_low(intCorrectAnswers_low);
        pb2.setAnsweredProblems_low(intAnsweredProblems_low);
        pb2.setCorrectAnswers_middle(intCorrectAnswers_middle);
        pb2.setAnsweredProblems_middle(intAnsweredProblems_middle);
        pb2.setCorrectAnswers_high(intCorrectAnswers_high);
        pb2.setAnsweredProblems_high(intAnsweredProblems_high);        
        
        String thisType = (String)((SmartRandomCheckForm) form).getThisType();
        String continueRight = (String)((SmartRandomCheckForm) form).getContinueRight();
        String continueWrong = (String)((SmartRandomCheckForm) form).getContinueWrong();
        int intContinueRight = Integer.parseInt(continueRight);
        int intContinueWrong = Integer.parseInt(continueWrong);       
        pb2.setLastType(lastType);
        pb2.setThisType(thisType);
        pb2.setContinueRight(intContinueRight);
        pb2.setContinueWrong(intContinueWrong);

        String neverHigh = (String)((SmartRandomCheckForm) form).getNeverHigh();
        boolean boolNeverHigh = Boolean.parseBoolean(neverHigh);
        pb2.setNeverHigh(boolNeverHigh);

        String planStatus = (String)((SmartRandomCheckForm) form).getPlanStatus();
        pb2.setPlanStatus(planStatus);
               
        //now get the remainSeconds
        //javax.sql.DataSource dataSource = null;
        //java.sql.Connection myConnection = null; 
        //Statement s = null;
        //ResultSet rs = null;         
        try{
           dataSource = getDataSource(request);
           myConnection = dataSource.getConnection();
           //Class.forName("org.hsqldb.jdbcDriver");
           //myConnection = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/xdb", "SA", "");
           s = myConnection.createStatement();
           rs = s.executeQuery("select remainSeconds from student_time where studentNo='" + userName + "'");
           int remainSeconds = 0;
           while (rs.next()){
              remainSeconds = rs.getInt("remainSeconds");              
           }
           pb2.setRemainSeconds(remainSeconds);

           //now we want to set true name directly from the database
           s = myConnection.createStatement();
           rs = s.executeQuery("select name from students where studentNo='" + userName + "'");
           while (rs.next()){
              trueName = rs.getString(1);              
           }
           pb2.setTrueName(trueName);

           //now we want to set angleHint to "not done"
           stmt = myConnection.prepareStatement("update hintMatch set term2=? where term1=?");
           stmt.clearParameters();
           stmt.setString(1, "not done");
           stmt.setString(2, "angleHint");
           stmt.executeUpdate();     

           //now we want to set lineHint to "not done"
           stmt = myConnection.prepareStatement("update hintMatch set term2=? where term1=?");
           stmt.clearParameters();
           stmt.setString(1, "not done");
           stmt.setString(2, "lineHint");
           stmt.executeUpdate();     

           //now we want to set tableHint to "not done"
           stmt = myConnection.prepareStatement("update hintMatch set term2=? where term1=?");
           stmt.clearParameters();
           stmt.setString(1, "not done");
           stmt.setString(2, "tableHint");
           stmt.executeUpdate();   

           //now we want to set chartHint to "not done"
           stmt = myConnection.prepareStatement("update hintMatch set term2=? where term1=?");
           stmt.clearParameters();
           stmt.setString(1, "not done");
           stmt.setString(2, "chartHint");
           stmt.executeUpdate();   

           //now we want to set gameYourInput and gameOpponent to ""
           stmt = myConnection.prepareStatement("update hintMatch set term2=? where term1=?");
           stmt.clearParameters();
           stmt.setString(1, "");
           stmt.setString(2, "gameYourInput");
           stmt.executeUpdate();   

           stmt = myConnection.prepareStatement("update hintMatch set term2=? where term1=?");
           stmt.clearParameters();
           stmt.setString(1, "");
           stmt.setString(2, "gameOpponentInput");
           stmt.executeUpdate();        
        }catch (SQLException ex){
           getServlet().log("Inside RandomCheckAction.java, process.", ex);
        }finally{
           try{
              if (rs != null) rs.close();
              if (s != null) s.close();
              if (stmt != null) stmt.close();
              if (myConnection != null) myConnection.close();
           }catch (SQLException e){
              getServlet().log("Inside RandomCheckAction.java, closing.", e);
           }
        }
   

        // Remove the Form Bean - don't need to carry values forward
        request.removeAttribute(mapping.getAttribute());

        session.setAttribute( Constants.PERSON_KEY, pb2);      
        request.setAttribute( Constants.PERSON_KEY, pb2);  

        String answeredHashSetStr = (String)((SmartRandomCheckForm) form).getAnsweredHashSet();
        session.setAttribute( Constants.HASHSET_KEY, answeredHashSetStr);   
        request.setAttribute( Constants.HASHSET_KEY, answeredHashSetStr);  

        String answeredHashSetStr_tf = (String)((SmartRandomCheckForm) form).getAnsweredHashSet_tf();
        session.setAttribute( Constants.HASHSET_TF_KEY, answeredHashSetStr_tf);   
        request.setAttribute( Constants.HASHSET_TF_KEY, answeredHashSetStr_tf);  

        String answeredHashSetStr_m = (String)((SmartRandomCheckForm) form).getAnswered_M_HashSet();
        session.setAttribute( Constants.HASHSET_M_KEY, answeredHashSetStr_m);   
        request.setAttribute( Constants.HASHSET_M_KEY, answeredHashSetStr_m); 

        String answeredHashSetStr_fb = (String)((SmartRandomCheckForm) form).getAnsweredHashSet_fb();
        session.setAttribute( Constants.HASHSET_FB_KEY, answeredHashSetStr_fb);   
        request.setAttribute( Constants.HASHSET_FB_KEY, answeredHashSetStr_fb); 

        String answeredHashSetStr_fdb = (String)((SmartRandomCheckForm) form).getAnsweredHashSet_fdb();
        session.setAttribute( Constants.HASHSET_FDB_KEY, answeredHashSetStr_fdb);   
        request.setAttribute( Constants.HASHSET_FDB_KEY, answeredHashSetStr_fdb); 

        String answeredHashSetStr_my = (String)((SmartRandomCheckForm) form).getAnswered_MY_HashSet();
        session.setAttribute( Constants.HASHSET_MY_KEY, answeredHashSetStr_my);   
        request.setAttribute( Constants.HASHSET_MY_KEY, answeredHashSetStr_my);

        String answeredHashSetStr_tm = (String)((SmartRandomCheckForm) form).getAnsweredHashSet_tm();
        session.setAttribute( Constants.HASHSET_TM_KEY, answeredHashSetStr_tm);   
        request.setAttribute( Constants.HASHSET_TM_KEY, answeredHashSetStr_tm); 

        String answeredHashSetStr_d = (String)((SmartRandomCheckForm) form).getAnswered_D_HashSet();
        session.setAttribute( Constants.HASHSET_D_KEY, answeredHashSetStr_d);   
        request.setAttribute( Constants.HASHSET_D_KEY, answeredHashSetStr_d); 

        String answeredHashSetStr_c = (String)((SmartRandomCheckForm) form).getAnswered_C_HashSet();
        session.setAttribute( Constants.HASHSET_C2_KEY, answeredHashSetStr_c);   
        request.setAttribute( Constants.HASHSET_C2_KEY, answeredHashSetStr_c);

        System.out.println("SmartRandomCheckAction, hs string is " + answeredHashSetStr);
        System.out.println("SmartRandomCheckAction, hs_tf str is " + answeredHashSetStr_tf);
        System.out.println("SmartRandomCheckAction, mhs str is " + answeredHashSetStr_m);   
        System.out.println("SmartRandomCheckAction, hs_fb str is " + answeredHashSetStr_fb); 
        System.out.println("SmartRandomCheckAction, hs_fdb str is " + answeredHashSetStr_fdb);
        System.out.println("SmartRandomCheckAction, hs_m str is " + answeredHashSetStr_my);         
        System.out.println("SmartRandomCheckAction, hs_tm str is " + answeredHashSetStr_tm);
        System.out.println("SmartRandomCheckAction, hs_d str is " + answeredHashSetStr_d);    
        System.out.println("SmartRandomCheckAction, hs_c str is " + answeredHashSetStr_c);                
        
        // Forward control to the specified success URI
        switch (randomNumber){
           case 0:
              return (mapping.findForward("ShowSmartProblemSolution"));   
           case 1:
              return (mapping.findForward("ShowSmartChoiceSolution"));   
           case 2:
              return (mapping.findForward("ShowSmartMultipleProblemSolution")); 
           case 3:
              return (mapping.findForward("ShowFillBlankSolution")); 
           case 4:
              return (mapping.findForward("ShowFillDoubleBlankSolution")); 
           case 6:
              return (mapping.findForward("ShowMultiplySolution"));  
           case 7:
              return (mapping.findForward("ShowDivisionSolution"));
           case 8:
              return (mapping.findForward("ShowCompositionSolution"));   
           default:
              break;
        } 
        return (mapping.findForward("ShowSmartProblemSolution"));        
    }
}
