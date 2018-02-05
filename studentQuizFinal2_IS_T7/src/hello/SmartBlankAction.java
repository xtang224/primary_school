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

public final class SmartBlankAction extends Action {

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

        ActionMessages errors = new ActionMessages();
        String strRandomNumber = (String)((SmartBlankForm) form).getRandomNumber();
        int randomNumber = Integer.parseInt(strRandomNumber);
        String inputAnswer = (String)((SmartBlankForm) form).getInputAnswer();
        inputAnswer = inputAnswer.trim();
        HttpSession session = request.getSession(true);
        String correctChoice = null;
        correctChoice = (String)((SmartBlankForm) form).getCorrectAnswer();
        String solution = null;
        solution = (String)((SmartBlankForm) form).getSolution();        
          
        FillBlankBean fbb = new FillBlankBean();
        FillDoubleBlankBean fdbb = new FillDoubleBlankBean();

        String statement_1st = null;
        String statement_2nd = null;
        String statement_3rd = null;
        String solution_1st = null;
        String solution_2nd = null;
        
        PersonBean pb2 = new PersonBean();
        String userName = (String)((SmartBlankForm) form).getUserName();
        String passWord = (String)((SmartBlankForm) form).getPassWord();
        String trueName = (String)((SmartBlankForm) form).getTrueName();
        pb2.setUserName(userName);
        pb2.setPassWord(passWord);  
        pb2.setTrueName(trueName);      
        pb2.setInputAnswer(inputAnswer);
        pb2.setRandomNumber(strRandomNumber);

        String times = (String)((SmartBlankForm) form).getTimes();
        pb2.setTimes(times);

        String source = (String)((SmartBlankForm) form).getSource();
        pb2.setSource(source);

        String answeredProblems = (String)((SmartBlankForm) form).getAnsweredProblems();
        String correctAnswers = (String)((SmartBlankForm) form).getCorrectAnswers();
        int intAnsweredProblems = Integer.parseInt(answeredProblems);
        int intCorrectAnswers = Integer.parseInt(correctAnswers);        

        //intAnsweredProblems ++;
        pb2.setAnsweredProblems(intAnsweredProblems);        
       
        String totalScore = (String)((SmartBlankForm) form).getTotalScore();
        String correctAnswers_low = (String)((SmartBlankForm) form).getCorrectAnswers_low();
        String answeredProblems_low = (String)((SmartBlankForm) form).getAnsweredProblems_low();
        String correctAnswers_middle = (String)((SmartBlankForm) form).getCorrectAnswers_middle();
        String answeredProblems_middle = (String)((SmartBlankForm) form).getAnsweredProblems_middle();
        String correctAnswers_high = (String)((SmartBlankForm) form).getCorrectAnswers_high();
        String answeredProblems_high = (String)((SmartBlankForm) form).getAnsweredProblems_high();
        double dbTotalScore = Double.parseDouble(totalScore);
        int intCorrectAnswers_low = Integer.parseInt(correctAnswers_low);
        int intAnsweredProblems_low = Integer.parseInt(answeredProblems_low);    
        int intCorrectAnswers_middle = Integer.parseInt(correctAnswers_middle);
        int intAnsweredProblems_middle = Integer.parseInt(answeredProblems_middle);
        int intCorrectAnswers_high = Integer.parseInt(correctAnswers_high);
        int intAnsweredProblems_high = Integer.parseInt(answeredProblems_high);
        boolean lastCorrect = false;
        String lastType = (String)((SmartBlankForm) form).getLastType();       
        
        String strCurrentProblemId = (String)((SmartBlankForm) form).getId();
        int intCurrentProblemId = Integer.parseInt(strCurrentProblemId);
        pb2.setCurrentProblemId(intCurrentProblemId);
        fbb.setId(intCurrentProblemId);
        fdbb.setId(intCurrentProblemId);

        int tempIntScore = 0;
        String inputAnswer_lowerCase = inputAnswer.toLowerCase();
        String correctChoice_lowerCase = correctChoice.toLowerCase();  

        javax.sql.DataSource dataSource = null;
        java.sql.Connection myConnection = null; 
        Statement s = null;
        ResultSet rs = null;  
        PreparedStatement stmt = null;
      
        switch (randomNumber){
           case 3: //fillblank2               
              System.out.println("In SmartBlankAction, intCurrentProblemId = " + intCurrentProblemId + " and randomNumber = " + randomNumber);
              try{
                 dataSource = getDataSource(request);
                 myConnection = dataSource.getConnection(); 

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
                    if (stmt != null) stmt.close();
                    if (myConnection != null) myConnection.close();
                 } catch (SQLException e) {
                    getServlet().log("Connection.close", e);
                 }
              }              

              break;
           case 4: //filldoubleblanks2              
              String inputAnswer2 = (String)((SmartBlankForm) form).getInputAnswer2();
              pb2.setInputAnswer2(inputAnswer2);
              
               //now we want to get correct answers directly from database
              System.out.println("In SmartBlankAction, intCurrentProblemId = " + intCurrentProblemId + " and randomNumber = " + randomNumber);
              try{
                 dataSource = getDataSource(request);
                 myConnection = dataSource.getConnection(); 

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
           default:
              break;
        }
        
        
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
        
        String thisType = (String)((SmartBlankForm) form).getThisType();
        String continueRight = (String)((SmartBlankForm) form).getContinueRight();
        String continueWrong = (String)((SmartBlankForm) form).getContinueWrong();
        int intContinueRight = Integer.parseInt(continueRight);
        int intContinueWrong = Integer.parseInt(continueWrong);       
        pb2.setLastType(lastType);
        pb2.setThisType(thisType);
        pb2.setContinueRight(intContinueRight);
        pb2.setContinueWrong(intContinueWrong);

        String neverHigh = (String)((SmartBlankForm) form).getNeverHigh();
        boolean boolNeverHigh = Boolean.parseBoolean(neverHigh);
        pb2.setNeverHigh(boolNeverHigh);

        String planStatus = (String)((SmartBlankForm) form).getPlanStatus();
        pb2.setPlanStatus(planStatus);
               
        //now get the remainSeconds               
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

        String answeredHashSetStr = (String)((SmartBlankForm) form).getAnsweredHashSet();
        session.setAttribute( Constants.HASHSET_KEY, answeredHashSetStr);   
        request.setAttribute( Constants.HASHSET_KEY, answeredHashSetStr);  

        String answeredHashSetStr_tf = (String)((SmartBlankForm) form).getAnsweredHashSet_tf();
        session.setAttribute( Constants.HASHSET_TF_KEY, answeredHashSetStr_tf);   
        request.setAttribute( Constants.HASHSET_TF_KEY, answeredHashSetStr_tf);  

        String answeredHashSetStr_m = (String)((SmartBlankForm) form).getAnswered_M_HashSet();
        session.setAttribute( Constants.HASHSET_M_KEY, answeredHashSetStr_m);   
        request.setAttribute( Constants.HASHSET_M_KEY, answeredHashSetStr_m); 

        String answeredHashSetStr_fb = (String)((SmartBlankForm) form).getAnsweredHashSet_fb();
        session.setAttribute( Constants.HASHSET_FB_KEY, answeredHashSetStr_fb);   
        request.setAttribute( Constants.HASHSET_FB_KEY, answeredHashSetStr_fb); 

        String answeredHashSetStr_fdb = (String)((SmartBlankForm) form).getAnsweredHashSet_fdb();
        session.setAttribute( Constants.HASHSET_FDB_KEY, answeredHashSetStr_fdb);   
        request.setAttribute( Constants.HASHSET_FDB_KEY, answeredHashSetStr_fdb); 

        String answeredHashSetStr_my = (String)((SmartBlankForm) form).getAnswered_MY_HashSet();
        session.setAttribute( Constants.HASHSET_MY_KEY, answeredHashSetStr_my);   
        request.setAttribute( Constants.HASHSET_MY_KEY, answeredHashSetStr_my);

        String answeredHashSetStr_tm = (String)((SmartBlankForm) form).getAnsweredHashSet_tm();
        session.setAttribute( Constants.HASHSET_TM_KEY, answeredHashSetStr_tm);   
        request.setAttribute( Constants.HASHSET_TM_KEY, answeredHashSetStr_tm); 

        String answeredHashSetStr_d = (String)((SmartBlankForm) form).getAnswered_D_HashSet();
        session.setAttribute( Constants.HASHSET_D_KEY, answeredHashSetStr_d);   
        request.setAttribute( Constants.HASHSET_D_KEY, answeredHashSetStr_d);

        String answeredHashSetStr_c = (String)((SmartBlankForm) form).getAnswered_C_HashSet();
        session.setAttribute( Constants.HASHSET_C2_KEY, answeredHashSetStr_c);   
        request.setAttribute( Constants.HASHSET_C2_KEY, answeredHashSetStr_c);

        System.out.println("In SmartBlankAction, hs string is " + answeredHashSetStr);
        System.out.println("SmartBlankAction, hs_tf str is " + answeredHashSetStr_tf);
        System.out.println("SmartBlankAction, mhs str is " + answeredHashSetStr_m);   
        System.out.println("SmartBlankAction, hs_fb str is " + answeredHashSetStr_fb); 
        System.out.println("SmartBlankAction, hs_fdb str is " + answeredHashSetStr_fdb);     
        System.out.println("SmartRandomCheckAction, hs_m str is " + answeredHashSetStr_my);         
        System.out.println("SmartRandomCheckAction, hs_tm str is " + answeredHashSetStr_tm);
        System.out.println("SmartRandomCheckAction, hs_d str is " + answeredHashSetStr_d);
        System.out.println("SmartRandomCheckAction, hs_c str is " + answeredHashSetStr_c);                        
        
        // Forward control to the specified success URI
        switch (randomNumber){           
           case 3:
              return (mapping.findForward("ShowFillBlank")); 
           case 4:
              return (mapping.findForward("ShowFillDoubleBlank")); 
           default:
              break;
        } 
        return (mapping.findForward("ShowSmartProblemSolution"));        
    }

}

