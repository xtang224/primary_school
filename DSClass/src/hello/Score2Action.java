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

public final class Score2Action extends Action {

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
        HttpSession session = request.getSession(true);

        /*
         * Validate the request parameters specified by the user
         * Note: Basic field validation done in HelloForm.java
         *       Business logic validation done in HelloAction.java
         */
        ActionMessages errors = new ActionMessages();        
        
        
        PersonBean pb2 = null;
        pb2 = new PersonBean();   
        
        String userName = (String)((Score2Form) form).getUserName();
        String passWord = (String)((Score2Form) form).getPassWord();
        String trueName = (String)((Score2Form) form).getTrueName();
        pb2.setUserName(userName);
        pb2.setPassWord(passWord); 
        pb2.setTrueName(trueName);       

        String planStatus = (String)((Score2Form) form).getPlanStatus();
        pb2.setPlanStatus(planStatus);

        String classId = (String)((Score2Form) form).getClassId();
        pb2.setClassId(classId);

        String answeredProblems = (String)((Score2Form) form).getAnsweredProblems();
        String correctAnswers = (String)((Score2Form) form).getCorrectAnswers();
        int intAnsweredProblems = Integer.parseInt(answeredProblems);
        int intCorrectAnswers = Integer.parseInt(correctAnswers);                   
        pb2.setAnsweredProblems(intAnsweredProblems);
        pb2.setCorrectAnswers(intCorrectAnswers); 

        String totalScore = (String)((Score2Form) form).getTotalScore();
        String correctAnswers_low = (String)((Score2Form) form).getCorrectAnswers_low();
        String answeredProblems_low = (String)((Score2Form) form).getAnsweredProblems_low();
        double dbTotalScore = Double.parseDouble(totalScore);
        int intCorrectAnswers_low = Integer.parseInt(correctAnswers_low);
        int intAnsweredProblems_low = Integer.parseInt(answeredProblems_low);                      
        pb2.setTotalScore(dbTotalScore);
        pb2.setCorrectAnswers_low(intCorrectAnswers_low);
        pb2.setAnsweredProblems_low(intAnsweredProblems_low);   

        String correctAnswers_middle = (String)((Score2Form) form).getCorrectAnswers_middle();
        String answeredProblems_middle = (String)((Score2Form) form).getAnsweredProblems_middle();        
        int intCorrectAnswers_middle = Integer.parseInt(correctAnswers_middle);
        int intAnsweredProblems_middle = Integer.parseInt(answeredProblems_middle);       
        pb2.setCorrectAnswers_middle(intCorrectAnswers_middle);
        pb2.setAnsweredProblems_middle(intAnsweredProblems_middle); 

        String correctAnswers_high = (String)((Score2Form) form).getCorrectAnswers_high();
        String answeredProblems_high = (String)((Score2Form) form).getAnsweredProblems_high();        
        int intCorrectAnswers_high = Integer.parseInt(correctAnswers_high);
        int intAnsweredProblems_high = Integer.parseInt(answeredProblems_high);       
        pb2.setCorrectAnswers_high(intCorrectAnswers_high);
        pb2.setAnsweredProblems_high(intAnsweredProblems_high);  
 
        String times = (String)((Score2Form) form).getTimes();
        pb2.setTimes(times);     

        // Remove the Form Bean - don't need to carry values forward
        request.removeAttribute(mapping.getAttribute());        

        session.setAttribute( Constants.PERSON_KEY, pb2); 
        request.setAttribute( Constants.PERSON_KEY, pb2);
       
        boolean isDone_1 = true;      
        boolean isDone_2 = false;        
        
        javax.sql.DataSource dataSource = null;
        java.sql.Connection myConnection = null;  
        PreparedStatement stmt = null; 
        Statement s = null;
        ResultSet rs = null; 
        Statement s2 = null;
        ResultSet rs2 = null; 

        String answeredHashSetStr = (String)((Score2Form) form).getAnsweredHashSet();
        HashSet hs = StringSetTransfer.stringToSet(answeredHashSetStr);
        hs.remove(new Integer(0));

        String answeredHashSetStr_tf = (String)((Score2Form) form).getAnsweredHashSet_tf();
        HashSet hs_tf = StringSetTransfer.stringToSet(answeredHashSetStr_tf);
        hs_tf.remove(new Integer(0));

        String answeredHashSetStr_m = (String)((Score2Form) form).getAnswered_M_HashSet();
        HashSet mhs = StringSetTransfer.stringToSet(answeredHashSetStr_m);
        mhs.remove(new Integer(0));

        String answeredHashSetStr_fb = (String)((Score2Form) form).getAnsweredHashSet_fb();       
        HashSet hs_fb = StringSetTransfer.stringToSet(answeredHashSetStr_fb);
        hs_fb.remove(new Integer(0));

        String answeredHashSetStr_fdb = (String)((Score2Form) form).getAnsweredHashSet_fdb();       
        HashSet hs_fdb = StringSetTransfer.stringToSet(answeredHashSetStr_fdb);
        hs_fdb.remove(new Integer(0));

        String answeredHashSetStr_fqb = (String)((Score2Form) form).getAnsweredHashSet_fqb();       
        HashSet hs_fqb = StringSetTransfer.stringToSet(answeredHashSetStr_fqb);
        hs_fqb.remove(new Integer(0));

        getServlet().log("Score2Action, hs string is " + answeredHashSetStr); 
        getServlet().log("Score2Action, hs_tf string is " + answeredHashSetStr_tf); 
        getServlet().log("Score2Action, mhs string is " + answeredHashSetStr_m); 
        getServlet().log("Score2Action, hs_fb string is " + answeredHashSetStr_fb); 
        getServlet().log("Score2Action, hs_fdb string is " + answeredHashSetStr_fdb); 
        getServlet().log("Score2Action, hs_fqb string is " + answeredHashSetStr_fqb); 
        
        ArrayList scoreList = new ArrayList();
        ArrayList timeList = new ArrayList();
        ArrayList infoList = new ArrayList();
        try {
           dataSource = getDataSource(request);
           myConnection = dataSource.getConnection();           
           //Class.forName("org.hsqldb.jdbcDriver");
           //myConnection = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/xdb", "SA", "");   

           s = myConnection.createStatement();
           rs = s.executeQuery("select playerId from student_score where studentNo='" + userName + "'");                
           while (rs.next()){
              String playerId = rs.getString(1);
              pb2.setPlayerId(playerId);
           }

stmt = myConnection.prepareStatement("update student_score set totalscore=?, answeredProblems=?, correctAnswers=?, answeredProblems_low=?, correctAnswers_low=?, answeredProblems_middle=?, correctAnswers_middle=?, answeredProblems_high=?, correctAnswers_high=?, planStatus=? where studentNo=?");  
           stmt.clearParameters();
           stmt.setDouble(1, dbTotalScore);
           stmt.setInt(2, intAnsweredProblems);
           stmt.setInt(3, intCorrectAnswers);  
           stmt.setInt(4, intAnsweredProblems_low);
           stmt.setInt(5, intCorrectAnswers_low);    
           stmt.setInt(6, intAnsweredProblems_middle);
           stmt.setInt(7, intCorrectAnswers_middle);  
           stmt.setInt(8, intAnsweredProblems_high);
           stmt.setInt(9, intCorrectAnswers_high);
           stmt.setString(10, planStatus);  
           stmt.setString(11, userName);
           stmt.executeUpdate();
           //getServlet().log("score is " + dbTotalScore); 
           
           isDone_2 = true;
           System.out.println("isDone_2 is true");   
           //getServlet().log("isDone_2 is true");  

           //now we want to sort all the students by their sum of scores in score_1st, score_2nd, ..., and etc.
           int intTotalScore = 0;
           int score_1st, score_2nd, score_3rd, score_4th, score_5th, score_6th, score_7th, score_8th, score_9th, score_10th, score_11th, score_12th, score_13th, score_14th, score_15th, score_16th, score_17th, score_18th, score_19th, score_20th, score_21st, score_22nd, score_23rd, score_24th, score_25th, score_26th, score_27th, score_28th, score_29th, score_30th, score_31st, score_32nd, score_33rd, score_34th, score_35th, score_36th, score_37th, score_38th, score_39th, scoreTimes;
           String name = "";
           
           s2 = myConnection.createStatement();
           rs2 = s2.executeQuery("select score_1st, score_2nd, score_3rd, score_4th, score_5th, score_6th, score_7th, score_8th, score_9th, score_10th, score_11th, score_12th, score_13th, score_14th, score_15th, score_16th, score_17th, score_18th, score_19th, score_20th, score_21st, score_22nd, score_23rd, score_24th, score_25th, score_26th, score_27th, score_28th, score_29th, score_30th, score_31st, score_32nd, score_33rd, score_34th, score_35th, score_36th, score_37th, score_38th, score_39th, studentNo, name from students where source='Dongshan' and class='" + classId + "'");  
           while(rs2.next()){
              intTotalScore = 0; scoreTimes = 0;
              score_1st = rs2.getInt(1); score_2nd = rs2.getInt(2); score_3rd = rs2.getInt(3); score_4th = rs2.getInt(4); score_5th = rs2.getInt(5);
              score_6th = rs2.getInt(6); score_7th = rs2.getInt(7); score_8th = rs2.getInt(8); score_9th = rs2.getInt(9); score_10th = rs2.getInt(10);
              score_11th = rs2.getInt(11); score_12th = rs2.getInt(12); score_13th = rs2.getInt(13); score_14th = rs2.getInt(14); score_15th = rs2.getInt(15); score_16th = rs2.getInt(16); score_17th = rs2.getInt(17); score_18th = rs2.getInt(18); score_19th = rs2.getInt(19); score_20th = rs2.getInt(20); score_21st = rs2.getInt(21); score_22nd = rs2.getInt(22); score_23rd = rs2.getInt(23);  score_24th = rs2.getInt(24); score_25th = rs2.getInt(25); score_26th = rs2.getInt(26); score_27th = rs2.getInt(27); score_28th = rs2.getInt(28); score_29th = rs2.getInt(29); score_30th = rs2.getInt(30); score_31st = rs2.getInt(31); score_32nd = rs2.getInt(32); score_33rd = rs2.getInt(33); score_34th = rs2.getInt(34); score_35th = rs2.getInt(35); score_36th = rs2.getInt(36); score_37th = rs2.getInt(37); score_38th = rs2.getInt(38); score_39th = rs2.getInt(39); 
              userName = rs2.getString(40); name = rs2.getString(41);
              if (score_1st != -1){
                 intTotalScore += score_1st;
                 scoreTimes ++;
              }
              if (score_2nd != -1){
                 intTotalScore += score_2nd;
                 scoreTimes ++;
              }
              if (score_3rd != -1){
                 intTotalScore += score_3rd;
                 scoreTimes ++;
              }
              if (score_4th != -1){
                 intTotalScore += score_4th;
                 scoreTimes ++;
              }
              if (score_5th != -1){
                 intTotalScore += score_5th;
                 scoreTimes ++;
              }
              if (score_6th != -1){
                 intTotalScore += score_6th;
                 scoreTimes ++;
              }
              if (score_7th != -1){
                 intTotalScore += score_7th;
                 scoreTimes ++;
              }
              if (score_8th != -1){
                 intTotalScore += score_8th;
                 scoreTimes ++;
              }
              if (score_9th != -1){
                 intTotalScore += score_9th;
                 scoreTimes ++;
              }
              if (score_10th != -1){
                 intTotalScore += score_10th;
                 scoreTimes ++;
              }
              if (score_11th != -1){
                 intTotalScore += score_11th;
                 scoreTimes ++;
              }
              if (score_12th != -1){
                 intTotalScore += score_12th;
                 scoreTimes ++;
              }
              if (score_13th != -1){
                 intTotalScore += score_13th;
                 scoreTimes ++;
              }
              if (score_14th != -1){
                 intTotalScore += score_14th;
                 scoreTimes ++;
              }
              if (score_15th != -1){
                 intTotalScore += score_15th;
                 scoreTimes ++;
              }
              if (score_16th != -1){
                 intTotalScore += score_16th;
                 scoreTimes ++;
              }
              if (score_17th != -1){
                 intTotalScore += score_17th;
                 scoreTimes ++;
              }
              if (score_18th != -1){
                 intTotalScore += score_18th;
                 scoreTimes ++;
              }
              if (score_19th != -1){
                 intTotalScore += score_19th;
                 scoreTimes ++;
              }
              if (score_20th != -1){
                 intTotalScore += score_20th;
                 scoreTimes ++;
              }
              if (score_21st != -1){
                 intTotalScore += score_21st;
                 scoreTimes ++;
              }
              if (score_22nd != -1){
                 intTotalScore += score_22nd;
                 scoreTimes ++;
              }
              if (score_23rd != -1){
                 intTotalScore += score_23rd;
                 scoreTimes ++;
              }
              if (score_24th != -1){
                 intTotalScore += score_24th;
                 scoreTimes ++;
              }
              if (score_25th != -1){
                 intTotalScore += score_25th;
                 scoreTimes ++;
              }
              if (score_26th != -1){
                 intTotalScore += score_26th;
                 scoreTimes ++;
              }
              if (score_27th != -1){
                 intTotalScore += score_27th;
                 scoreTimes ++;
              } 
              if (score_28th != -1){
                 intTotalScore += score_28th;
                 scoreTimes ++;
              }
              if (score_29th != -1){
                 intTotalScore += score_29th;
                 scoreTimes ++;
              }
              if (score_30th != -1){
                 intTotalScore += score_30th;
                 scoreTimes ++;
              }
              if (score_31st != -1){
                 intTotalScore += score_31st;
                 scoreTimes ++;
              }
              if (score_32nd != -1){
                 intTotalScore += score_32nd;
                 scoreTimes ++;
              }
              if (score_33rd != -1){
                 intTotalScore += score_33rd;
                 scoreTimes ++;
              }
              if (score_34th != -1){
                 intTotalScore += score_34th;
                 scoreTimes ++;
              }
              if (score_35th != -1){
                 intTotalScore += score_35th;
                 scoreTimes ++;
              }
              if (score_36th != -1){
                 intTotalScore += score_36th;
                 scoreTimes ++;
              }
              if (score_37th != -1){
                 intTotalScore += score_37th;
                 scoreTimes ++;
              }
              if (score_38th != -1){
                 intTotalScore += score_38th;
                 scoreTimes ++;
              }
              if (score_39th != -1){
                 intTotalScore += score_39th;
                 scoreTimes ++;
              }
              scoreList.add(new Integer(intTotalScore));
              timeList.add(new Integer(scoreTimes));
              String info = userName + ":" + name + ":" + intTotalScore + ":" + scoreTimes;
              infoList.add(info);
           }  
        } catch (SQLException sqle) {
           getServlet().log("Connection.process", sqle);
        } finally {
           //enclose this in a finally block to make
           //sure the connection is closed
           try {
              if (rs != null) rs.close();
              if (s != null) s.close();
              if (rs2 != null) rs2.close();
              if (s2 != null) s2.close();
              if (stmt != null) stmt.close();
              if (myConnection != null) myConnection.close();
           } catch (SQLException e) {
             getServlet().log("Connection.close", e);
           }
        }        
        
        //Now we are going to sort these students by their totalScore and scoreTimes
        System.out.println("Inside Score2Action, scoreList.size() = " + scoreList.size());
        System.out.println("Inside Score2Action, timeList.size() = " + timeList.size());
        System.out.println("Inside Score2Action, infoList.size() = " + infoList.size());
        Object[] scoreListArr = scoreList.toArray();
        int[] intScoreListArr = new int[scoreList.size()];
        for (int i = 0; i < scoreList.size(); i++){
           intScoreListArr[i] = ((Integer)scoreListArr[i]).intValue();
        }
        Object[] timeListArr = timeList.toArray();
        int[] intTimeListArr = new int[timeList.size()];
        for (int i = 0; i < timeList.size(); i++){
           intTimeListArr[i] = ((Integer)timeListArr[i]).intValue();
        }
        Object[] infoListArr = infoList.toArray();

        int intTemp = 0;
        Object obTemp = null;
        for (int i = 0; i < scoreList.size()-1; i++){          
           for (int j = i+1; j < scoreList.size(); j++){
              if (intScoreListArr[i] < intScoreListArr[j]){
                 intTemp = intScoreListArr[i];
                 intScoreListArr[i] = intScoreListArr[j];
                 intScoreListArr[j] = intTemp;

                 intTemp = intTimeListArr[i];
                 intTimeListArr[i] = intTimeListArr[j];
                 intTimeListArr[j] = intTemp;

                 obTemp = infoListArr[i];
                 infoListArr[i] = infoListArr[j];
                 infoListArr[j] = obTemp; 
              }else if ((intScoreListArr[i] == intScoreListArr[j]) && (intTimeListArr[i] > intTimeListArr[j])){
                 intTemp = intScoreListArr[i];
                 intScoreListArr[i] = intScoreListArr[j];
                 intScoreListArr[j] = intTemp;

                 intTemp = intTimeListArr[i];
                 intTimeListArr[i] = intTimeListArr[j];
                 intTimeListArr[j] = intTemp;

                 obTemp = infoListArr[i];
                 infoListArr[i] = infoListArr[j];
                 infoListArr[j] = obTemp; 
              }
          }
       }       

       ArrayList newInfoList = new ArrayList();
       for (int i = 0; i < infoListArr.length; i++){
          String strTemp = new String((String)infoListArr[i]);
          newInfoList.add(strTemp);
       }

       session.setAttribute( "infoList", newInfoList); 
       request.setAttribute( "infoList", newInfoList); 

       return (mapping.findForward("ShowScore2"));               
    }    
}
