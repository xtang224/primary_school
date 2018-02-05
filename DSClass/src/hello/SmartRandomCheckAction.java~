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
        HttpSession session = request.getSession(true);
        String correctChoice = null;
        correctChoice = (String)((SmartRandomCheckForm) form).getCorrectAnswer();
        String solution = null;
        solution = (String)((SmartRandomCheckForm) form).getSolution();
        String source = (String)((SmartRandomCheckForm) form).getSource();
        SmartProblemBean pb = new SmartProblemBean();
        SmartChoiceBean cb = new SmartChoiceBean();
        SmartMultipleProblemBean mpb = new SmartMultipleProblemBean();    
        FillBlankBean fbb = new FillBlankBean();
        FillDoubleBlankBean fdbb = new FillDoubleBlankBean();
        FillTripleBlankBean ftbb = new FillTripleBlankBean();
        FillQuadBlankBean fqbb = new FillQuadBlankBean();
        TermMatchBean tmb = new TermMatchBean();
        MultiplyBean mb = new MultiplyBean();
        DivisionBean db = new DivisionBean();
        PlusMinusBean pmb = new PlusMinusBean();

        String statement_1st = null;
        String statement_2nd = null;
        String statement_3rd = null;
        String statement_4th = null;
        String statement_5th = null;

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

        switch (randomNumber){
           case 0:
              pb.setCorrectChoice(correctChoice);
              pb.setSolution(solution);
              pb.setSource(source);
              request.setAttribute( Constants.PROBLEM_KEY, pb); 
              session.setAttribute( Constants.PROBLEM_KEY, pb); 
              break;
           case 1:
              cb.setChoice(correctChoice);
              cb.setSolution(solution);
              cb.setSource(source);
              request.setAttribute( Constants.CHOICE_KEY, cb); 
              session.setAttribute( Constants.CHOICE_KEY, cb);  
              break;
           case 2:
              mpb.setCorrectChoice(correctChoice);
              mpb.setSolution(solution);
              mpb.setSource(source);
              request.setAttribute( Constants.MULTIPLEPROBLEM_KEY, mpb); 
              session.setAttribute( Constants.MULTIPLEPROBLEM_KEY, mpb); 
              break;
           case 3:
              statement_1st = (String)((SmartRandomCheckForm) form).getStatement_1st();
              statement_2nd = (String)((SmartRandomCheckForm) form).getStatement_2nd();
              fbb.setStatement_1st(statement_1st);
              fbb.setStatement_2nd(statement_2nd);
              fbb.setSolution(solution);
              fbb.setSource(source);
              request.setAttribute( Constants.FILLBLANK_KEY, fbb); 
              session.setAttribute( Constants.FILLBLANK_KEY, fbb);  
              break;
           case 4:
              statement_1st = (String)((SmartRandomCheckForm) form).getStatement_1st();
              statement_2nd = (String)((SmartRandomCheckForm) form).getStatement_2nd();
              statement_3rd = (String)((SmartRandomCheckForm) form).getStatement_3rd();
              fdbb.setStatement_1st(statement_1st);
              fdbb.setStatement_2nd(statement_2nd);              
              fdbb.setStatement_3rd(statement_3rd);
              fdbb.setSource(source);
              request.setAttribute( Constants.FILLDOUBLEBLANK_KEY, fdbb); 
              session.setAttribute( Constants.FILLDOUBLEBLANK_KEY, fdbb);  
              break;
           case 5:
              tmb.setCorrectChoice(correctChoice);
              tmb.setSolution(solution);
              tmb.setSource(source);
              request.setAttribute( Constants.TERMMATCH_KEY, tmb); 
              session.setAttribute( Constants.TERMMATCH_KEY, tmb); 
              break;
           case 6:
              statement_1st = (String)((SmartRandomCheckForm) form).getStatement_1st();
              statement_2nd = (String)((SmartRandomCheckForm) form).getStatement_2nd();
              statement_3rd = (String)((SmartRandomCheckForm) form).getStatement_3rd();
              statement_4th = (String)((SmartRandomCheckForm) form).getStatement_4th();
              statement_5th = (String)((SmartRandomCheckForm) form).getStatement_5th();
              fqbb.setStatement_1st(statement_1st);
              fqbb.setStatement_2nd(statement_2nd);              
              fqbb.setStatement_3rd(statement_3rd);
              fqbb.setStatement_4th(statement_4th);
              fqbb.setStatement_5th(statement_5th);
              fqbb.setSource(source);
              request.setAttribute( Constants.FILLQUADBLANK_KEY, fqbb); 
              session.setAttribute( Constants.FILLQUADBLANK_KEY, fqbb);  
              break;
           case 7:
              statement_1st = (String)((SmartRandomCheckForm) form).getStatement_1st();
              statement_2nd = (String)((SmartRandomCheckForm) form).getStatement_2nd();
              statement_3rd = (String)((SmartRandomCheckForm) form).getStatement_3rd();
              statement_4th = (String)((SmartRandomCheckForm) form).getStatement_4th();              
              ftbb.setStatement_1st(statement_1st);
              ftbb.setStatement_2nd(statement_2nd);              
              ftbb.setStatement_3rd(statement_3rd);
              ftbb.setStatement_4th(statement_4th);             
              ftbb.setSource(source);
              request.setAttribute( Constants.FILLTRIPLEBLANK_KEY, ftbb); 
              session.setAttribute( Constants.FILLTRIPLEBLANK_KEY, ftbb);  
              break;
           case 10:
              factor1 = (String)((SmartRandomCheckForm) form).getFactor1();
              factor2 = (String)((SmartRandomCheckForm) form).getFactor2();
              result = (String)((SmartRandomCheckForm) form).getResult();                           
              pmb.setFactor1(factor1);
              pmb.setFactor2(factor2);              
              pmb.setResult(result); 
              pmb.setYourSolution(inputAnswer);              
              pmb.setSource(source);
              request.setAttribute( Constants.PLUSMINUS_KEY, pmb); 
              session.setAttribute( Constants.PLUSMINUS_KEY, pmb);  
              break;
           default:
              break;
        }       
        
        /*
         * Having received and validated the data submitted
         * from the View, we now update the model
         */        
        
        //pb.saveToPersistentStore();

        /*
         * If there was a choice of View components that depended on the model
         * (or some other) status, we'd make the decision here as to which
         * to display. In this case, there is only one View component.
         *
         * We pass data to the View components by setting them as attributes
         * in the page, request, session or servlet context. In this case, the
         * most appropriate scoping is the "request" context since the data
         * will not be neaded after the View is generated.
         *
         * Constants.PERSON_KEY provides a key accessible by both the
         * Controller component (i.e. this class) and the View component
         * (i.e. the jsp file we forward to).
         */    
        PersonBean pb2 = new PersonBean();
        String userName = (String)((SmartRandomCheckForm) form).getUserName();
        String passWord = (String)((SmartRandomCheckForm) form).getPassWord();
        String trueName = (String)((SmartRandomCheckForm) form).getTrueName();
        pb2.setUserName(userName);
        pb2.setPassWord(passWord);  
        pb2.setTrueName((String)StringSetTransfer.nameMap.get(userName));
        pb2.setInputAnswer(inputAnswer);      

        String times = (String)((SmartRandomCheckForm) form).getTimes();
        pb2.setTimes(times);

        String classId = (String)((SmartRandomCheckForm) form).getClassId();
        pb2.setClassId(classId);

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

        int intCurrentProblemId = 0;
        String strCurrentProblemId = null;
        strCurrentProblemId = (String)((SmartRandomCheckForm) form).getId();
        intCurrentProblemId = Integer.parseInt(strCurrentProblemId);
        pb2.setCurrentProblemId(intCurrentProblemId); 
        System.out.println("intCurrentProblemId = " + intCurrentProblemId);

        String inputAnswer_lowerCase = inputAnswer.toLowerCase();
        String correctChoice_lowerCase = correctChoice.toLowerCase();        
        switch (randomNumber){
           case 0: //questions2
              if (correctChoice_lowerCase.equals(inputAnswer_lowerCase)){
                 intCorrectAnswers ++;
                 lastCorrect = true;  
              }

              //we want to record studentAnswer of questions2
              strCurrentProblemId = (String)((SmartRandomCheckForm) form).getId();
              intCurrentProblemId = Integer.parseInt(strCurrentProblemId);
     
              System.out.println("inside 0, intCurrentProblemId = " + intCurrentProblemId + ", userName=" + userName + ", inputAnswer_lowerCase=" + inputAnswer_lowerCase + ", source=" + source);         
              try{
                 dataSource = getDataSource(request);
                 myConnection = dataSource.getConnection();
 
                 s = myConnection.createStatement();
                 rs = s.executeQuery("select max(id) from usedQuestions2_test");
                 int existCount = 0;
                 while(rs.next()){
                    existCount=rs.getInt(1);
                 }

                 existCount++;

               System.out.println("Inside SmartRandomCheckAction, before the insert is executed, existCount=" + existCount);
               stmt = myConnection.prepareStatement("insert into usedQuestions2_test(Id,problemId,Type,StudentNo,StudentAnswer,source) values(?,?,'L',?,?,?)");
               stmt.clearParameters();
               stmt.setInt(1, existCount);
               stmt.setInt(2, intCurrentProblemId);
               
               stmt.setString(3, userName);
               stmt.setString(4, inputAnswer_lowerCase);
               stmt.setString(5, source);
               stmt.executeUpdate(); 
               System.out.println("Inside SmartRandomCheckAction, after the insert is executed");
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
                 lastCorrect = true;  
              }

              //we want to record studentAnswer of choices2
              strCurrentProblemId = (String)((SmartRandomCheckForm) form).getId();
              intCurrentProblemId = Integer.parseInt(strCurrentProblemId);
              try{
                 dataSource = getDataSource(request);
                 myConnection = dataSource.getConnection();

                 s = myConnection.createStatement();
                 rs = s.executeQuery("select max(id) from usedChoices2_test");
                 int existCount = 0;
                 while(rs.next()){
                    existCount=rs.getInt(1);
                 }
                 existCount++;

               stmt = myConnection.prepareStatement("insert into usedChoices2_test(Id,problemId,Type,StudentNo,StudentAnswer,source) values(?,?,'L',?,?,?)");
               stmt.clearParameters();
               stmt.setInt(1, existCount);
               stmt.setInt(2, intCurrentProblemId);
               
               stmt.setString(3, userName);
               stmt.setString(4, inputAnswer_lowerCase);
               stmt.setString(5, source);
               stmt.executeUpdate(); 
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
                 lastCorrect = true;          
              }

              //we want to record studentAnswer of multipleQuestions2
              strCurrentProblemId = (String)((SmartRandomCheckForm) form).getId();
              intCurrentProblemId = Integer.parseInt(strCurrentProblemId);
              try{
                 dataSource = getDataSource(request);
                 myConnection = dataSource.getConnection();

                 s = myConnection.createStatement();
                 rs = s.executeQuery("select max(id) from usedMultipleQuestions2_test");
                 int existCount = 0;
                 while(rs.next()){
                    existCount=rs.getInt(1);
                 }
                 existCount++;

               stmt = myConnection.prepareStatement("insert into usedMultipleQuestions2_test(Id,problemId,Type,Times,StudentNo,StudentAnswer,source) values(?,'H',?,?,?,?)");
               stmt.clearParameters();
               stmt.setInt(1, existCount);
               stmt.setInt(2, intCurrentProblemId);
               stmt.setString(3, times);
               stmt.setString(4, userName);
               stmt.setString(5, inputAnswer_lowerCase);
               stmt.setString(6, source);
               stmt.executeUpdate(); 
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
              String[] correctChoices;
              if (correctChoice_lowerCase.contains("#"))
                 correctChoices = correctChoice_lowerCase.split("#");
              else
                 correctChoices = correctChoice_lowerCase.split("%");
              for (int i = 0; i < correctChoices.length; i++){
                 System.out.println("correctChoices[" + i + "]=" + correctChoices[i]);
                 if ((!inputAnswer_lowerCase.equals("")) && inputAnswer_lowerCase.equals(correctChoices[i])){
                    intCorrectAnswers ++;
                    lastCorrect = true;
                    break;
                 }
              }
 
              //to be able to recheck the solutions of fillblank2, we want to record the student answer
              strCurrentProblemId = (String)((SmartRandomCheckForm) form).getId();
              intCurrentProblemId = Integer.parseInt(strCurrentProblemId);
              getServlet().log("In fillBlank2, intCurrentProblemId = " + intCurrentProblemId);
              String explanation = "";
              try{
                 dataSource = getDataSource(request);
                 myConnection = dataSource.getConnection();

                 stmt = myConnection.prepareStatement("select explanation from FillBlank2_test where id=?");
                 stmt.clearParameters();
                 stmt.setInt(1, intCurrentProblemId);
                 rs = stmt.executeQuery();
                 while(rs.next()){
                     explanation = rs.getString(1);                     
                 }

                 s = myConnection.createStatement();
                 rs = s.executeQuery("select max(id) from usedFillBlank2_test");
                 int existCount = 0;
                 while(rs.next()){
                    existCount=rs.getInt(1);
                 }
                 existCount++;

               stmt = myConnection.prepareStatement("insert into usedFillBlank2_test(Id,problemId,Type,StudentNo,StudentAnswer,source) values(?,?,'M',?,?,?)");
               stmt.clearParameters();
               stmt.setInt(1, existCount);
               stmt.setInt(2, intCurrentProblemId);
               
               stmt.setString(3, userName);
               stmt.setString(4, inputAnswer_lowerCase);
               stmt.setString(5, source);
               stmt.executeUpdate(); 
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
              if (explanation==null)
                 explanation = "";
              fbb.setExplanation(explanation);
              request.setAttribute( Constants.FILLBLANK_KEY, fbb); 
              session.setAttribute( Constants.FILLBLANK_KEY, fbb);   
              break;
           case 4: //filldoubleblanks2
              int indexOfColon = inputAnswer_lowerCase.indexOf(";");
              int indexOfColon2 = inputAnswer_lowerCase.indexOf("；"); 
              String inputAnswer_1st = null;
              String inputAnswer_2nd = null;
              if (indexOfColon >=0 && indexOfColon < inputAnswer_lowerCase.length()){
                 inputAnswer_1st = inputAnswer_lowerCase.substring(0, indexOfColon);
                 inputAnswer_2nd = inputAnswer_lowerCase.substring(indexOfColon + 1);
              }else if(indexOfColon2 >=0 && indexOfColon2 < inputAnswer_lowerCase.length()){
                 inputAnswer_1st = inputAnswer_lowerCase.substring(0, indexOfColon2);
                 inputAnswer_2nd = inputAnswer_lowerCase.substring(indexOfColon2 + 1);
              }else{
                 inputAnswer_1st = "wrong";
                 inputAnswer_2nd = "wrong";
              }
              inputAnswer_1st = inputAnswer_1st.trim();
              inputAnswer_2nd = inputAnswer_2nd.trim();
              indexOfColon = correctChoice_lowerCase.indexOf(";");
              String correctChoice_1st = correctChoice_lowerCase.substring(0, indexOfColon);
              String correctChoice_2nd = correctChoice_lowerCase.substring(indexOfColon + 1);

              fdbb.setSolution_1st(correctChoice_1st);
              fdbb.setSolution_2nd(correctChoice_2nd);
              request.setAttribute( Constants.FILLDOUBLEBLANK_KEY, fdbb); 
              session.setAttribute( Constants.FILLDOUBLEBLANK_KEY, fdbb); 
              
              String[] solution_lower_1sts;
              if (correctChoice_1st.contains("#"))
                 solution_lower_1sts = correctChoice_1st.split("#");
              else
                 solution_lower_1sts = correctChoice_1st.split("%");
              String[] solution_lower_2nds;
              if (correctChoice_2nd.contains("#"))
                 solution_lower_2nds = correctChoice_2nd.split("#");
              else 
                 solution_lower_2nds = correctChoice_2nd.split("%");

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

              //to be able to recheck the solutions of fillDoubleblanks2, we want to record the student answer
              strCurrentProblemId = (String)((SmartRandomCheckForm) form).getId();
              intCurrentProblemId = Integer.parseInt(strCurrentProblemId);
              getServlet().log("In fillDoubleBlanks2, intCurrentProblemId = " + intCurrentProblemId);
              explanation = "";
              try{
                 dataSource = getDataSource(request);
                 myConnection = dataSource.getConnection();

                 stmt = myConnection.prepareStatement("select explanation from FillDoubleBlanks2_test where id=?");
                 stmt.clearParameters();
                 stmt.setInt(1, intCurrentProblemId);
                 rs = stmt.executeQuery();
                 while(rs.next()){
                     explanation = rs.getString(1);                     
                 }
  
                 s = myConnection.createStatement();
                 rs = s.executeQuery("select max(id) from usedFillDoubleBlanks2_test");
                 int existCount = 0;
                 while(rs.next()){
                    existCount=rs.getInt(1);
                 }
                 existCount++;

               stmt = myConnection.prepareStatement("insert into usedFillDoubleBlanks2_test(Id,problemId,Type,StudentNo,StudentAnswer,source) values(?,?,'H',?,?,?)");
               stmt.clearParameters();
               stmt.setInt(1, existCount);
               stmt.setInt(2, intCurrentProblemId);
               
               stmt.setString(3, userName);
               stmt.setString(4, inputAnswer_lowerCase);
               stmt.setString(5, source);
               stmt.executeUpdate(); 
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
              if (explanation==null)
                 explanation = "";
              fdbb.setExplanation(explanation);
              request.setAttribute( Constants.FILLDOUBLEBLANK_KEY, fdbb); 
              session.setAttribute( Constants.FILLDOUBLEBLANK_KEY, fdbb);  
              break;
           case 5: //termMatch2
              System.out.println("inside SmartRandomCheckAction.java, termMatch2 is reached, and inputAnswer_lowerCase = " + inputAnswer_lowerCase);
              if (correctChoice_lowerCase.equals(inputAnswer_lowerCase)){
                 intCorrectAnswers ++;
                 lastCorrect = true;  
                 //hs_tm_correct.add(new Integer(intCurrentProblemId)); 
              }
              
              //we want to record studentAnswer of termMatch2    
              strCurrentProblemId = (String)((SmartRandomCheckForm) form).getId();
              intCurrentProblemId = Integer.parseInt(strCurrentProblemId);          
              try{
                 dataSource = getDataSource(request);
                 myConnection = dataSource.getConnection();
             
                 stmt = myConnection.prepareStatement("insert into usedTermMatch2(Id,Type,Times,StudentNo,StudentAnswer,source) values(?,'M',?,?,?,?)");
                 stmt.clearParameters();
                 stmt.setInt(1, intCurrentProblemId);
                 stmt.setString(2, times);
                 stmt.setString(3, userName);
                 stmt.setString(4, inputAnswer_lowerCase);
                 stmt.setString(5, source);
                 stmt.executeUpdate(); 

                 /*
                 stmt = myConnection.prepareStatement("update usedTermMatch2 set StudentAnswer=? where studentNo=? and id=?");
                 stmt.clearParameters();
                 stmt.setString(1, inputAnswer_lowerCase);
                 stmt.setString(2, userName);   
                 stmt.setInt(3, intCurrentProblemId);                
                 stmt.executeUpdate(); 
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
              break; 
           case 6: //fillquadblanks2
              indexOfColon = inputAnswer_lowerCase.indexOf(";");
              indexOfColon2 = inputAnswer_lowerCase.indexOf("；"); 
              String[] inputAnswers = null;              
              if (indexOfColon >=0 && indexOfColon < inputAnswer_lowerCase.length()){
                 inputAnswers = inputAnswer_lowerCase.split(";");                 
              }else if(indexOfColon2 >=0 && indexOfColon2 < inputAnswer_lowerCase.length()){
                 inputAnswers = inputAnswer_lowerCase.split("；"); 
              }else{
                 inputAnswers = new String[4];
                 for (int i=0; i<4; i++)
                    inputAnswers[i] = "Wrong";
              }
              
              indexOfColon = correctChoice_lowerCase.indexOf(";");
              correctChoices = correctChoice_lowerCase.split(";");

              fqbb.setSolution_1st(correctChoices[0]);
              fqbb.setSolution_2nd(correctChoices[1]);
              fqbb.setSolution_3rd(correctChoices[2]);
              fqbb.setSolution_4th(correctChoices[3]);
              request.setAttribute( Constants.FILLQUADBLANK_KEY, fqbb); 
              session.setAttribute( Constants.FILLQUADBLANK_KEY, fqbb);               
             
              lastCorrect = true;
              outer:
              for (int i = 0; i < correctChoices.length; i++){
                 String tmpCorrectAnswer = (String)correctChoices[i];                 
                 if (!StringSetTransfer.match(tmpCorrectAnswer, inputAnswers[i])){                     
                     lastCorrect = false; 
                     break outer;
                 }                  
              } 
              if (lastCorrect) 
                  intCorrectAnswers ++;      

              //to be able to recheck the solutions of fillDoubleblanks2, we want to record the student answer
              strCurrentProblemId = (String)((SmartRandomCheckForm) form).getId();
              intCurrentProblemId = Integer.parseInt(strCurrentProblemId);
              System.out.println("In fillQuadBlanks2, intCurrentProblemId = " + intCurrentProblemId);
              int existCount = 0;
              explanation = "";
              try{
                 dataSource = getDataSource(request);
                 myConnection = dataSource.getConnection();

                 stmt = myConnection.prepareStatement("select explanation from FillQuadBlanks2_test where id=?");
                 stmt.clearParameters();
                 stmt.setInt(1, intCurrentProblemId);
                 rs = stmt.executeQuery();
                 while(rs.next()){
                     explanation = rs.getString(1);                     
                 }

                 s = myConnection.createStatement();
                 rs = s.executeQuery("select max(id) from usedFillQuadBlanks2_test");
                 while(rs.next()){
                    existCount=rs.getInt(1); 
                 }
                 existCount++;
                
               stmt = myConnection.prepareStatement("insert into usedFillQuadBlanks2_test(Id,problemId,Type,StudentNo,StudentAnswer,source) values(?,?,'H',?,?,?)");
               stmt.clearParameters();
               stmt.setInt(1, existCount);
               stmt.setInt(2, intCurrentProblemId);               
               stmt.setString(3, userName);
               stmt.setString(4, inputAnswer_lowerCase);
               stmt.setString(5, source);
               stmt.executeUpdate(); 
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
              if (explanation==null)
                 explanation = "";
              fqbb.setExplanation(explanation);
              request.setAttribute( Constants.FILLQUADBLANK_KEY, fqbb); 
              session.setAttribute( Constants.FILLQUADBLANK_KEY, fqbb);  
              break;
           case 7: //filltripleblanks2
              indexOfColon = inputAnswer_lowerCase.indexOf(";");
              indexOfColon2 = inputAnswer_lowerCase.indexOf("；"); 
              inputAnswers = null;              
              if (indexOfColon >=0 && indexOfColon < inputAnswer_lowerCase.length()){
                 inputAnswers = inputAnswer_lowerCase.split(";");                 
              }else if(indexOfColon2 >=0 && indexOfColon2 < inputAnswer_lowerCase.length()){
                 inputAnswers = inputAnswer_lowerCase.split("；"); 
              }else{
                 inputAnswers = new String[4];
                 for (int i=0; i<4; i++)
                    inputAnswers[i] = "Wrong";
              }
              
              indexOfColon = correctChoice_lowerCase.indexOf(";");
              correctChoices = correctChoice_lowerCase.split(";");

              ftbb.setSolution_1st(correctChoices[0]);
              ftbb.setSolution_2nd(correctChoices[1]);
              ftbb.setSolution_3rd(correctChoices[2]);              
              request.setAttribute( Constants.FILLTRIPLEBLANK_KEY, ftbb); 
              session.setAttribute( Constants.FILLTRIPLEBLANK_KEY, ftbb);               
             
              lastCorrect = true;
              outer:
              for (int i = 0; i < correctChoices.length; i++){
                 String tmpCorrectAnswer = (String)correctChoices[i];                 
                 if (!StringSetTransfer.match(tmpCorrectAnswer, inputAnswers[i])){                     
                     lastCorrect = false; 
                     break outer;
                 }                  
              } 
              if (lastCorrect) 
                  intCorrectAnswers ++;      

              //to be able to recheck the solutions of fillTripleblanks2, we want to record the student answer
              strCurrentProblemId = (String)((SmartRandomCheckForm) form).getId();
              intCurrentProblemId = Integer.parseInt(strCurrentProblemId);
              System.out.println("In fillTripleBlanks2, intCurrentProblemId = " + intCurrentProblemId);
              existCount = 0;
              explanation = "";
              try{
                 dataSource = getDataSource(request);
                 myConnection = dataSource.getConnection();

                 stmt = myConnection.prepareStatement("select explanation from FillTripleBlanks2_test where id=?");
                 stmt.clearParameters();
                 stmt.setInt(1, intCurrentProblemId);
                 rs = stmt.executeQuery();
                 while(rs.next()){
                     explanation = rs.getString(1);                     
                 }

                 s = myConnection.createStatement();
                 rs = s.executeQuery("select max(id) from usedFillTripleBlanks2_test");
                 while(rs.next()){
                    existCount=rs.getInt(1); 
                 }
                 existCount++;
                
               stmt = myConnection.prepareStatement("insert into usedFillTripleBlanks2_test(Id,problemId,Type,StudentNo,StudentAnswer,source) values(?,?,'H',?,?,?)");
               stmt.clearParameters();
               stmt.setInt(1, existCount);
               stmt.setInt(2, intCurrentProblemId);               
               stmt.setString(3, userName);
               stmt.setString(4, inputAnswer_lowerCase);
               stmt.setString(5, source);
               stmt.executeUpdate(); 
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
              if (explanation==null)
                 explanation = "";
              ftbb.setExplanation(explanation);
              request.setAttribute( Constants.FILLTRIPLEBLANK_KEY, ftbb); 
              session.setAttribute( Constants.FILLTRIPLEBLANK_KEY, ftbb);  
              break;
           case 8: //multiply 
              System.out.println("Inside SmartRandomCheckAction, now we are in case 8");             
              String inputAnswer2 = (String)((SmartRandomCheckForm) form).getInputAnswer2();
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
              
              //to be able to recheck the solutions of multiply_test, we want to record the student answer              
              getServlet().log("In multiply, intCurrentProblemId = " + intCurrentProblemId);
              System.out.println("In multiply, intCurrentProblemId = " + intCurrentProblemId);
              existCount = 0;
              try{
                 dataSource = getDataSource(request);
                 myConnection = dataSource.getConnection();

                System.out.println("In multiply, inside try block, after dataSource.getConnection()");

                s = myConnection.createStatement();
                 rs = s.executeQuery("select max(id) from usedMultiply_test");
                 while(rs.next()){
                    existCount=rs.getInt(1); 
                 }
                 existCount++;    
              
               stmt = myConnection.prepareStatement("insert into usedMultiply_test(Id,problemId,Type,StudentNo,StudentAnswer,source) values(?,?,'H',?,?,?)");
               stmt.clearParameters();
               stmt.setInt(1, existCount);
               stmt.setInt(2, intCurrentProblemId);               
               stmt.setString(3, userName);
               String totalAnswer = inputAnswer_lowerCase+";"+inputAnswer2+";"+inputAnswer3;
               stmt.setString(4, totalAnswer);
               stmt.setString(5, source);
               stmt.executeUpdate();  
               
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
           case 9: //division 
              System.out.println("Inside SmartRandomCheckAction, now we are in case 9");             
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
              
              //to be able to recheck the solutions of division_test, we want to record the student answer              
              getServlet().log("In division, intCurrentProblemId = " + intCurrentProblemId);
              System.out.println("In division, intCurrentProblemId = " + intCurrentProblemId);
              existCount = 0;
              try{
                 dataSource = getDataSource(request);
                 myConnection = dataSource.getConnection();

                System.out.println("In division, inside try block, after dataSource.getConnection()");

                s = myConnection.createStatement();
                 rs = s.executeQuery("select max(id) from usedMultiply_test");
                 while(rs.next()){
                    existCount=rs.getInt(1); 
                 }
                 existCount++; 
              
                stmt = myConnection.prepareStatement("insert into usedDivision_test(Id,ProblemId,Type,StudentNo,StudentAnswer,source,level) values(?,?,'H',?,?,?,?)");
               stmt.clearParameters();
               stmt.setInt(1, existCount);
               stmt.setInt(2, intCurrentProblemId);              
               stmt.setString(3, userName);
               String totalAnswer = inputAnswer_lowerCase+";"+inputAnswer2+";"+inputAnswer3+";"+inputAnswer4+";"+inputAnswer5+";"+inputAnswer6+";"+inputAnswer7;
               stmt.setString(4, totalAnswer);
               stmt.setString(5, source);
               stmt.setInt(6, level);               
               stmt.executeUpdate();               
                
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
           case 10:
              existCount = 0;
              try{
                 dataSource = getDataSource(request);
                 myConnection = dataSource.getConnection();

                 System.out.println("In division, inside try block, after dataSource.getConnection()");

                 s = myConnection.createStatement();
                 rs = s.executeQuery("select max(id) from usedPlusMinus");
                 while(rs.next()){
                    existCount=rs.getInt(1); 
                 }
                 existCount++; 
              
                stmt = myConnection.prepareStatement("insert into usedPlusMinus(Id,ProblemId,Type,StudentNo,StudentAnswer,source) values(?,?,'H',?,?,?)");
                stmt.clearParameters();
                stmt.setInt(1, existCount);
                stmt.setInt(2, intCurrentProblemId);              
                stmt.setString(3, userName);              
                stmt.setString(4, inputAnswer);
                stmt.setString(5, source);                           
                stmt.executeUpdate();
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
              int length1, length2, length3, length4, longest;
              if (pmb.getFactor1().contains("."))
                 length1 = pmb.getFactor1().indexOf("."); 
              else
                 length1 = pmb.getFactor1().length();
              if (pmb.getFactor2().contains("."))
                 length2 = pmb.getFactor2().indexOf("."); 
              else
                 length2 = pmb.getFactor2().length();
              if (pmb.getResult().contains("."))
                 length3 = pmb.getResult().indexOf("."); 
              else
                 length3 = pmb.getResult().length(); 
              if (pmb.getYourSolution().contains("."))
                 length4 = pmb.getYourSolution().indexOf("."); 
              else
                 length4 = pmb.getYourSolution().length();   
              longest = Math.max(length1, length2);  
              longest = Math.max(longest, length3);
              longest = Math.max(longest, length4);              
              for (int i=length1; i<longest; i++){
                 pmb.setFactor1("_"+pmb.getFactor1());
              } 
              for (int i=length2; i<longest; i++){
                 pmb.setFactor2("_"+pmb.getFactor2());
              } 
              for (int i=length3; i<longest; i++){
                 pmb.setResult("_"+pmb.getResult());
              }
              for (int i=length4; i<longest; i++){
                 pmb.setYourSolution("_"+pmb.getYourSolution());
              }
              request.setAttribute( Constants.PLUSMINUS_KEY, pmb); 
              session.setAttribute( Constants.PLUSMINUS_KEY, pmb);
              break;      
           default:
              break;
        }

        
        //StringSetTransfer stringSetTransfer = new StringSetTransfer(userName);
        double[] results =  StringSetTransfer.rate(lastCorrect, lastType.charAt(0));         
        dbTotalScore += results[0];        
        intAnsweredProblems_low += (int)results[1];
        intCorrectAnswers_low += (int)results[2];
        intAnsweredProblems_middle += (int)results[3];
        intCorrectAnswers_middle += (int)results[4]; 
        intAnsweredProblems_high += (int)results[5];       
        intCorrectAnswers_high += (int)results[6];        

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
           pb2.setRemainSeconds(1800); //now we want this demonstration program not to end
        }catch (SQLException ex){
           getServlet().log("Inside RandomCheckAction.java, process.", ex);
        }finally{
           try{
              if (rs != null) rs.close();
              if (s != null) s.close();
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

        String answeredHashSetStr_ftb = (String)((SmartRandomCheckForm) form).getAnsweredHashSet_ftb();
        session.setAttribute( Constants.HASHSET_FTB_KEY, answeredHashSetStr_ftb);   
        request.setAttribute( Constants.HASHSET_FTB_KEY, answeredHashSetStr_ftb);  

        String answeredHashSetStr_fqb = (String)((SmartRandomCheckForm) form).getAnsweredHashSet_fqb();
        session.setAttribute( Constants.HASHSET_FQB_KEY, answeredHashSetStr_fqb);   
        request.setAttribute( Constants.HASHSET_FQB_KEY, answeredHashSetStr_fqb); 

        String answeredHashSetStr_tm = (String)((SmartRandomCheckForm) form).getAnsweredHashSet_tm();
        session.setAttribute( Constants.HASHSET_TM_KEY, answeredHashSetStr_tm);   
        request.setAttribute( Constants.HASHSET_TM_KEY, answeredHashSetStr_tm); 

        //String answeredHashSetStr_my = (String)((SmartRandomCheckForm) form).getAnsweredHashSet_m();
        String answeredHashSetStr_my = (String)((SmartRandomCheckForm) form).getAnswered_MY_HashSet();
        session.setAttribute( Constants.HASHSET_MY_KEY, answeredHashSetStr_my);   
        request.setAttribute( Constants.HASHSET_MY_KEY, answeredHashSetStr_my); 

        //String answeredHashSetStr_d = (String)((SmartRandomCheckForm) form).getAnsweredHashSet_d();
        String answeredHashSetStr_d = (String)((SmartRandomCheckForm) form).getAnswered_D_HashSet();
        session.setAttribute( Constants.HASHSET_D_KEY, answeredHashSetStr_d);   
        request.setAttribute( Constants.HASHSET_D_KEY, answeredHashSetStr_d); 

        String answeredHashSetStr_pm = (String)((SmartRandomCheckForm) form).getAnsweredHashSet_pm();
        session.setAttribute( Constants.HASHSET_PM_KEY, answeredHashSetStr_pm);   
        request.setAttribute( Constants.HASHSET_PM_KEY, answeredHashSetStr_pm); 

        System.out.println("SmartRandomCheckAction, hs string is " + answeredHashSetStr);
        System.out.println("SmartRandomCheckAction, hs_tf str is " + answeredHashSetStr_tf);
        System.out.println("SmartRandomCheckAction, mhs str is " + answeredHashSetStr_m);   
        System.out.println("SmartRandomCheckAction, hs_fb str is " + answeredHashSetStr_fb); 
        System.out.println("SmartRandomCheckAction, hs_fdb str is " + answeredHashSetStr_fdb);        
        System.out.println("SmartRandomCheckAction, hs_ftb str is " + answeredHashSetStr_ftb);        
        System.out.println("SmartRandomCheckAction, hs_fqb str is " + answeredHashSetStr_fqb);        
        System.out.println("SmartRandomCheckAction, hs_tm str is " + answeredHashSetStr_tm); 
        System.out.println("SmartRandomCheckAction, hs_my str is " + answeredHashSetStr_my);      
        System.out.println("SmartRandomCheckAction, hs_d str is " + answeredHashSetStr_d);
        System.out.println("SmartRandomCheckAction, hs_pm str is " + answeredHashSetStr_pm);       
        
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
           case 5:
              return (mapping.findForward("ShowTermMatchSolution")); 
           case 6:
              return (mapping.findForward("ShowFillQuadBlankSolution"));
           case 7:
              return (mapping.findForward("ShowFillTripleBlankSolution"));
           case 8:
              return (mapping.findForward("ShowMultiplySolution"));
           case 9:
              return (mapping.findForward("ShowDivisionSolution"));
           case 10:
              return (mapping.findForward("ShowPlusMinusSolution"));
           default:
              break;
        } 
        return (mapping.findForward("ShowSmartProblemSolution"));        
    }
}
