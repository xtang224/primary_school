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

public final class SmartRandomCheck2Action extends Action {

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
        HttpSession session = request.getSession(true);

        String strRandomNumber = (String)((SmartRandomCheck2Form) form).getRandomNumber();
        int randomNumber = Integer.parseInt(strRandomNumber);
        String inputAnswer = (String)((SmartRandomCheck2Form) form).getInputAnswer();
        if (inputAnswer != null)
           inputAnswer = inputAnswer.trim();
        else
           inputAnswer = "";        
        String correctChoice = null;
        correctChoice = (String)((SmartRandomCheck2Form) form).getCorrectAnswer();
        String solution = null;
        solution = (String)((SmartRandomCheck2Form) form).getSolution();
        String source = (String)((SmartRandomCheck2Form) form).getSource();
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
              
              pb.setCorrectChoice("");
              pb.setSolution("");
              pb.setSource(source);
              request.setAttribute( Constants.PROBLEM_KEY, pb); 
              session.setAttribute( Constants.PROBLEM_KEY, pb); 
              break;
           case 1:
              cb.setChoice("");
              cb.setSolution("");
              cb.setSource(source);
              request.setAttribute( Constants.CHOICE_KEY, cb); 
              session.setAttribute( Constants.CHOICE_KEY, cb);  
              break;
           case 2:
              mpb.setCorrectChoice("");
              mpb.setSolution("");
              mpb.setSource(source);
              request.setAttribute( Constants.MULTIPLEPROBLEM_KEY, mpb); 
              session.setAttribute( Constants.MULTIPLEPROBLEM_KEY, mpb); 
              break;
           case 3:
              statement_1st = "";
              statement_2nd = "";
              fbb.setStatement_1st(statement_1st);
              fbb.setStatement_2nd(statement_2nd);
              fbb.setSolution("");
              fbb.setSource(source);
              request.setAttribute( Constants.FILLBLANK_KEY, fbb); 
              session.setAttribute( Constants.FILLBLANK_KEY, fbb);  
              break;
           case 4:
              statement_1st = "";
              statement_2nd = "";
              statement_3rd = "";
              fdbb.setStatement_1st(statement_1st);
              fdbb.setStatement_2nd(statement_2nd);              
              fdbb.setStatement_3rd(statement_3rd);
              fdbb.setSource(source);
              request.setAttribute( Constants.FILLDOUBLEBLANK_KEY, fdbb); 
              session.setAttribute( Constants.FILLDOUBLEBLANK_KEY, fdbb);  
              break;
           case 5:
              tmb.setCorrectChoice("");
              tmb.setSolution("");
              tmb.setSource(source);
              request.setAttribute( Constants.TERMMATCH_KEY, tmb); 
              session.setAttribute( Constants.TERMMATCH_KEY, tmb);
              break; 
           case 6:
              statement_1st = "";
              statement_2nd = "";
              statement_3rd = "";
              statement_4th = "";
              statement_5th = "";
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
              statement_1st = "";
              statement_2nd = "";
              statement_3rd = "";
              statement_4th = "";              
              ftbb.setStatement_1st(statement_1st);
              ftbb.setStatement_2nd(statement_2nd);              
              ftbb.setStatement_3rd(statement_3rd);
              ftbb.setStatement_4th(statement_4th);              
              ftbb.setSource(source);
              request.setAttribute( Constants.FILLTRIPLEBLANK_KEY, ftbb); 
              session.setAttribute( Constants.FILLTRIPLEBLANK_KEY, ftbb);  
              break;
           case 8:
              factor1 = "";
              factor2 = "";
              p1Solution = "";
              p2Solution = "";  
              rSolution = "";            
              mb.setFactor1(factor1);
              mb.setFactor2(factor2);              
              mb.setP1Solution(p1Solution);
              mb.setP2Solution(p2Solution);
              mb.setResultSolution(rSolution);              
              mb.setSource(source);
              request.setAttribute( Constants.MULTIPLY_KEY, mb); 
              session.setAttribute( Constants.MULTIPLY_KEY, mb);  
              break;
           case 9:
              String divided = "";
              String divider = "";
              result = "";
              product1 = "";  
              remain1 = "";     
              product2 = "";  
              remain2 = ""; 
              product3 = "";  
              remain3 = "";      
              db.setDivided(divided);
              db.setDivider(divider);              
              db.setResult(result);
              db.setProduct1(product1);
              db.setRemain1(remain1); 
              db.setProduct2(product2);
              db.setRemain2(remain2);   
              db.setProduct3(product3);
              db.setRemain3(remain3);          
              db.setSource(source);
              request.setAttribute( Constants.DIVISION_KEY, db); 
              session.setAttribute( Constants.DIVISION_KEY, db);  
              break;
           case 10:
              factor1 = "";
              factor2 = "";
              result = "";                          
              pmb.setFactor1(factor1);
              pmb.setFactor2(factor2);              
              pmb.setResult(result);              
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
        //PersonBean pb2 = new PersonBean();
        PersonBean pb2 = new PersonBean();
        String userName = (String)((SmartRandomCheck2Form) form).getUserName();
        String passWord = (String)((SmartRandomCheck2Form) form).getPassWord();
        String trueName = (String)((SmartRandomCheck2Form) form).getTrueName();
        pb2.setUserName(userName);
        pb2.setPassWord(passWord);  
        pb2.setTrueName((String)StringSetTransfer.nameMap.get(userName));
        pb2.setInputAnswer(inputAnswer);      

        String times = (String)((SmartRandomCheck2Form) form).getTimes();
        pb2.setTimes(times);

        String classId = (String)((SmartRandomCheck2Form) form).getClassId();
        pb2.setClassId(classId);
       
        String answeredHashSetStr = (String)((SmartRandomCheck2Form) form).getAnsweredHashSet();
        HashSet hs = StringSetTransfer.stringToSet(answeredHashSetStr);
        String answeredHashSetStr_tf = (String)((SmartRandomCheck2Form) form).getAnsweredHashSet_tf();
        HashSet hs_tf = StringSetTransfer.stringToSet(answeredHashSetStr_tf);
        String answeredHashSetStr_m = (String)((SmartRandomCheck2Form) form).getAnswered_M_HashSet();
        HashSet mhs = StringSetTransfer.stringToSet(answeredHashSetStr_m);
        String answeredHashSetStr_fb = (String)((SmartRandomCheck2Form) form).getAnsweredHashSet_fb();
        HashSet hs_fb = StringSetTransfer.stringToSet(answeredHashSetStr_fb);
        String answeredHashSetStr_fdb = (String)((SmartRandomCheck2Form) form).getAnsweredHashSet_fdb();
        HashSet hs_fdb = StringSetTransfer.stringToSet(answeredHashSetStr_fdb);
        String answeredHashSetStr_ftb = (String)((SmartRandomCheck2Form) form).getAnsweredHashSet_ftb();
        HashSet hs_ftb = StringSetTransfer.stringToSet(answeredHashSetStr_ftb);
        String answeredHashSetStr_fqb = (String)((SmartRandomCheck2Form) form).getAnsweredHashSet_fqb();
        HashSet hs_fqb = StringSetTransfer.stringToSet(answeredHashSetStr_fqb);
        String answeredHashSetStr_tm = (String)((SmartRandomCheck2Form) form).getAnsweredHashSet_tm();
        HashSet hs_tm = StringSetTransfer.stringToSet(answeredHashSetStr_tm);
        //String answeredHashSetStr_my = (String)((SmartRandomCheck2Form) form).getAnsweredHashSet_m();
        String answeredHashSetStr_my = (String)((SmartRandomCheck2Form) form).getAnswered_MY_HashSet();
        HashSet hs_m = StringSetTransfer.stringToSet(answeredHashSetStr_my);
        //String answeredHashSetStr_d = (String)((SmartRandomCheck2Form) form).getAnsweredHashSet_d();
        String answeredHashSetStr_d = (String)((SmartRandomCheck2Form) form).getAnswered_D_HashSet();
        HashSet hs_d = StringSetTransfer.stringToSet(answeredHashSetStr_d);
        String answeredHashSetStr_pm = (String)((SmartRandomCheck2Form) form).getAnsweredHashSet_pm();
        HashSet hs_pm = StringSetTransfer.stringToSet(answeredHashSetStr_pm);

        String answeredProblems = (String)((SmartRandomCheck2Form) form).getAnsweredProblems();
        String correctAnswers = (String)((SmartRandomCheck2Form) form).getCorrectAnswers();
        int intAnsweredProblems = Integer.parseInt(answeredProblems);
        int intCorrectAnswers = Integer.parseInt(correctAnswers);         
       
        String totalScore = (String)((SmartRandomCheck2Form) form).getTotalScore();
        String correctAnswers_low = (String)((SmartRandomCheck2Form) form).getCorrectAnswers_low();
        String answeredProblems_low = (String)((SmartRandomCheck2Form) form).getAnsweredProblems_low();
        String correctAnswers_middle = (String)((SmartRandomCheck2Form) form).getCorrectAnswers_middle();
        String answeredProblems_middle = (String)((SmartRandomCheck2Form) form).getAnsweredProblems_middle();
        String correctAnswers_high = (String)((SmartRandomCheck2Form) form).getCorrectAnswers_high();
        String answeredProblems_high = (String)((SmartRandomCheck2Form) form).getAnsweredProblems_high();
        double dbTotalScore = Double.parseDouble(totalScore);
        int intCorrectAnswers_low = Integer.parseInt(correctAnswers_low);
        int intAnsweredProblems_low = Integer.parseInt(answeredProblems_low);    
        int intCorrectAnswers_middle = Integer.parseInt(correctAnswers_middle);
        int intAnsweredProblems_middle = Integer.parseInt(answeredProblems_middle);
        int intCorrectAnswers_high = Integer.parseInt(correctAnswers_high);
        int intAnsweredProblems_high = Integer.parseInt(answeredProblems_high);
        boolean lastCorrect = false;
        String lastType = (String)((SmartRandomCheck2Form) form).getLastType();
        

        int intCurrentProblemId = 0;
        String strCurrentProblemId = null;
        strCurrentProblemId = (String)((SmartRandomCheck2Form) form).getId();
        intCurrentProblemId = Integer.parseInt(strCurrentProblemId);
        String inputAnswer_lowerCase = inputAnswer.toLowerCase();
        String correctChoice_lowerCase = ""; // correctChoice.toLowerCase();        
        
        //StringSetTransfer stringSetTransfer = new StringSetTransfer(userName);       

        //String strLastCorrect = (String)((SmartRandomCheck2Form) form).getLastCorrect();
        String strLastCorrect = "true";
        lastCorrect = Boolean.parseBoolean(strLastCorrect);
        pb2.setLastCorrect(lastCorrect);
        pb2.setAnsweredProblems(intAnsweredProblems);        
        pb2.setCorrectAnswers(intCorrectAnswers);
        
        pb2.setTotalScore(dbTotalScore);
        pb2.setCorrectAnswers_low(intCorrectAnswers_low);
        pb2.setAnsweredProblems_low(intAnsweredProblems_low);
        pb2.setCorrectAnswers_middle(intCorrectAnswers_middle);
        pb2.setAnsweredProblems_middle(intAnsweredProblems_middle);
        pb2.setCorrectAnswers_high(intCorrectAnswers_high);
        pb2.setAnsweredProblems_high(intAnsweredProblems_high);        
        
        String thisType = (String)((SmartRandomCheck2Form) form).getThisType();
        pb2.setLastType(lastType);
        pb2.setThisType(thisType);
        //String continueRight = (String)((SmartRandomCheck2Form) form).getContinueRight();
        //String continueWrong = (String)((SmartRandomCheck2Form) form).getContinueWrong();
        //int intContinueRight = Integer.parseInt(continueRight);
        //int intContinueWrong = Integer.parseInt(continueWrong);        
        //pb2.setContinueRight(intContinueRight);
        //pb2.setContinueWrong(intContinueWrong);

        //String neverHigh = (String)((SmartRandomCheck2Form) form).getNeverHigh();
        //boolean boolNeverHigh = Boolean.parseBoolean(neverHigh);
        //pb2.setNeverHigh(boolNeverHigh);

        String planStatus = (String)((SmartRandomCheck2Form) form).getPlanStatus();
        pb2.setPlanStatus(planStatus);
               
        //now get the remainSeconds
        javax.sql.DataSource dataSource = null;
        java.sql.Connection myConnection = null;
        Statement s = null;
        PreparedStatement stmt = null;        
        ResultSet rs = null;

        String strRemainSeconds = null;
        int remainSeconds = 0;
        if (strRemainSeconds == null){               
           try{
              dataSource = getDataSource(request);
              myConnection = dataSource.getConnection();
              //Class.forName("org.hsqldb.jdbcDriver");
              //myConnection = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/xdb", "SA", "");
              s = myConnection.createStatement();
              rs = s.executeQuery("select remainSeconds from student_time where studentNo='" + userName + "'");
              remainSeconds = 0;
              while (rs.next()){
                 remainSeconds = rs.getInt("remainSeconds");              
              }              

              if (pb2.getPlanStatus().equals("finished")){
                 pb2.setPlanStatus("not finished");
                 s = myConnection.createStatement();
                 s.executeUpdate("update student_score set planStatus='not finished' where studentNo = '" + userName + "'");
              }
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
        }else{
           remainSeconds = Integer.parseInt(strRemainSeconds);
        }
        pb2.setRemainSeconds(remainSeconds);
        pb2.setRemainSeconds(1800); //now we want this demonstration program not to end

        session.setAttribute( Constants.PERSON_KEY, pb2); 
        request.setAttribute( Constants.PERSON_KEY, pb2);        
        
        System.out.println("SmartRandomCheck2Action, hs string is " + answeredHashSetStr);
        System.out.println("SmartRandomCheck2Action, hs_tf str is " + answeredHashSetStr_tf);
        System.out.println("SmartRandomCheck2Action, mhs str is " + answeredHashSetStr_m);   
        System.out.println("SmartRandomCheck2Action, hs_fb str is " + answeredHashSetStr_fb); 
        System.out.println("SmartRandomCheck2Action, hs_fdb str is " + answeredHashSetStr_fdb);
        System.out.println("SmartRandomCheck2Action, hs_ftb str is " + answeredHashSetStr_ftb);  
        System.out.println("SmartRandomCheck2Action, hs_fqb str is " + answeredHashSetStr_fqb);     
        System.out.println("SmartRandomCheck2Action, hs_tm str is " + answeredHashSetStr_tm); 
        System.out.println("SmartRandomCheck2Action, hs_m str is " + answeredHashSetStr_m);   
        System.out.println("SmartRandomCheck2Action, hs_d str is " + answeredHashSetStr_d); 
        System.out.println("SmartRandomCheck2Action, hs_pm str is " + answeredHashSetStr_pm);  
        
        session.setAttribute( Constants.HASHSET_KEY, StringSetTransfer.setToString(hs));   
        session.setAttribute( Constants.HASHSET_TF_KEY, StringSetTransfer.setToString(hs_tf));
        session.setAttribute( Constants.HASHSET_M_KEY, StringSetTransfer.setToString(mhs)); 
        session.setAttribute( Constants.HASHSET_FB_KEY, StringSetTransfer.setToString(hs_fb));
        session.setAttribute( Constants.HASHSET_FDB_KEY, StringSetTransfer.setToString(hs_fdb));
        session.setAttribute( Constants.HASHSET_FTB_KEY, StringSetTransfer.setToString(hs_ftb));
        session.setAttribute( Constants.HASHSET_FQB_KEY, StringSetTransfer.setToString(hs_fqb));
        session.setAttribute( Constants.HASHSET_TM_KEY, StringSetTransfer.setToString(hs_tm));
        session.setAttribute( Constants.HASHSET_MY_KEY, StringSetTransfer.setToString(hs_m));
        session.setAttribute( Constants.HASHSET_D_KEY, StringSetTransfer.setToString(hs_d));
        session.setAttribute( Constants.HASHSET_PM_KEY, StringSetTransfer.setToString(hs_pm));

        request.setAttribute( Constants.HASHSET_KEY, StringSetTransfer.setToString(hs));   
        request.setAttribute( Constants.HASHSET_TF_KEY, StringSetTransfer.setToString(hs_tf));
        request.setAttribute( Constants.HASHSET_M_KEY, StringSetTransfer.setToString(mhs)); 
        request.setAttribute( Constants.HASHSET_FB_KEY, StringSetTransfer.setToString(hs_fb)); 
        request.setAttribute( Constants.HASHSET_FDB_KEY, StringSetTransfer.setToString(hs_fdb)); 
        request.setAttribute( Constants.HASHSET_FTB_KEY, StringSetTransfer.setToString(hs_ftb)); 
        request.setAttribute( Constants.HASHSET_FQB_KEY, StringSetTransfer.setToString(hs_fqb)); 
        request.setAttribute( Constants.HASHSET_TM_KEY, StringSetTransfer.setToString(hs_tm));
        request.setAttribute( Constants.HASHSET_MY_KEY, StringSetTransfer.setToString(hs_m));
        request.setAttribute( Constants.HASHSET_D_KEY, StringSetTransfer.setToString(hs_d));   
        request.setAttribute( Constants.HASHSET_PM_KEY, StringSetTransfer.setToString(hs_pm));

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
