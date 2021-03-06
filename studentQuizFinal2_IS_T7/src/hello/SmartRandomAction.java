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

public final class SmartRandomAction extends Action {

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
        SmartProblemBean pb = null;
        SmartChoiceBean cb = null;
        SmartMultipleProblemBean mpb = null;
        FillBlankBean fbb = null;
        FillDoubleBlankBean fdbb = null;
        TermMatchBean tmb = null;
        MultiplyBean mb = null;
        DivisionBean db = null;
        CompositionBean c2b = null;
        String first = null;
        first = (String)request.getParameter("first"); 

        PersonBean pb2 = null;
        pb2 = new PersonBean();
        String userName = (String)((SmartRandomForm) form).getUserName();
        String passWord = (String)((SmartRandomForm) form).getPassWord();
        String trueName = (String)((SmartRandomForm) form).getTrueName();
        String playerId = (String)((SmartRandomForm) form).getPlayerId();
        pb2.setUserName(userName);
        pb2.setPassWord(passWord); 
        pb2.setTrueName(trueName);
        pb2.setPlayerId(playerId);  

        String planStatus = (String)((SmartRandomForm) form).getPlanStatus();
        pb2.setPlanStatus(planStatus);

        String source = (String)((SmartRandomForm) form).getSource();
        pb2.setSource(source);

        javax.sql.DataSource dataSource = null;
        java.sql.Connection myConnection = null;
        Statement s = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        pb = new SmartProblemBean();
        cb = new SmartChoiceBean();
        mpb = new SmartMultipleProblemBean(); 
        fbb = new FillBlankBean();
        fdbb = new FillDoubleBlankBean();     
        tmb = new TermMatchBean();
        mb = new MultiplyBean();
        db = new DivisionBean();
        c2b = new CompositionBean();
        pb.setSource(source);
        cb.setSource(source);
        mpb.setSource(source);
        fbb.setSource(source);
        fdbb.setSource(source);
        tmb.setSource(source);
        mb.setSource(source);
        db.setSource(source);
        c2b.setSource(source); 

        HashSet hs = null;
        HashSet hs_tf = null;
        HashSet mhs = null;
        HashSet hs_fb = null;
        HashSet hs_fdb = null;
        HashSet hs_tm = null;
        HashSet hs_m = null;
        HashSet hs_d = null;
        HashSet hs_c2 = new HashSet();
        
        if (first.equals("true")){
            hs = new HashSet(); 
            hs.add(new Integer(0));
            hs_tf = new HashSet();
            hs_tf.add(new Integer(0));
            mhs = new HashSet();
            mhs.add(new Integer(0));
            hs_fb = new HashSet(); 
            hs_fb.add(new Integer(0));
            hs_fdb = new HashSet(); 
            hs_fdb.add(new Integer(0));
            hs_tm = new HashSet(); 
            hs_tm.add(new Integer(0));
            hs_m = new HashSet(); 
            hs_m.add(new Integer(0));
            hs_d = new HashSet(); 
            hs_d.add(new Integer(0));
            hs_c2 = new HashSet(); 
            hs_c2.add(new Integer(0));

            HashSet hs_low = new HashSet();            
            HashSet hs_middle = new HashSet(); 
            HashSet hs_high = new HashSet();
            HashSet hs_low_tf = new HashSet();
            HashSet hs_middle_tf = new HashSet(); 
            HashSet hs_high_tf = new HashSet();
            HashSet mhs_low = new HashSet();
            HashSet mhs_middle = new HashSet(); 
            HashSet mhs_high = new HashSet();
            HashSet hs_low_fb = new HashSet();
            HashSet hs_middle_fb = new HashSet(); 
            HashSet hs_high_fb = new HashSet();
            HashSet hs_low_fdb = new HashSet();
            HashSet hs_middle_fdb = new HashSet(); 
            HashSet hs_high_fdb = new HashSet();
            HashSet hs_middle_tm = new HashSet();
            HashSet hs_high_m = new HashSet();
            HashSet hs_high_d = new HashSet();
            HashSet hs_high_c2 = new HashSet();

            HashSet hs_low_used = new HashSet();
            HashSet hs_low_tf_used = new HashSet();
            HashSet hs_middle_fb_used = new HashSet(); 
            HashSet mhs_high_used = new HashSet();
            HashSet hs_high_fdb_used = new HashSet();
            HashSet hs_middle_tm_used = new HashSet();
            HashSet hs_high_m_used = new HashSet();
            HashSet hs_high_d_used = new HashSet();
            HashSet hs_high_c2_used = new HashSet();

            int count = 0;            
            String tempPlayerId = null;
            String tempUserName = null;
            try{
                //we will try to initiate the usedQuestions first, then generate the random question id 
               dataSource = getDataSource(request);
               myConnection = dataSource.getConnection();
               //Class.forName("org.hsqldb.jdbcDriver");
               //myConnection = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/xdb", "SA", "");

               if (StringSetTransfer.init == false){

                  s = myConnection.createStatement();
                  s.executeUpdate("delete from student_vote");

                  s = myConnection.createStatement();
                  s.executeUpdate("update student_score set planStatus='not finished' where studentNo='" + userName + "'");
                    
                     //we want to initiate StringSetTransfer.hs_low_used,hs_low,hs_middle,hs_high,total
                    
                     stmt = myConnection.prepareStatement("select id from usedQuestions2_test where studentNo=? and source=?");
                     stmt.clearParameters();
                     stmt.setString(1, userName);
                     stmt.setString(2, source);
                     rs = stmt.executeQuery();      
                     while (rs.next()){
                        int usedId = rs.getInt("id");
                        hs_low_used.add(new Integer(usedId));
                     }
                     StringSetTransfer.hs_low_used = hs_low_used;
                     rs = s.executeQuery("select id from questions2_test where type='L' and source='" + source + "'");        
                     while (rs.next()){
                        int usedId = rs.getInt("id");
                        hs_low.add(new Integer(usedId));
                     }
                     hs_low.removeAll(hs_low_used);
                     StringSetTransfer.hs_low = hs_low; 
                     
                     //now we want to initiate StringSetTransfer.hs_low_tf_used,hs_low_tf,hs_middle_tf,hs_high_tf,total_tf
                   
                     stmt = myConnection.prepareStatement("select id from usedChoices2_test where studentNo=? and source=?");
                     stmt.clearParameters();
                     stmt.setString(1, userName);
                     stmt.setString(2, source);
                     rs = stmt.executeQuery();
                     while (rs.next()){
                        int usedId = rs.getInt("id");
                        hs_low_tf_used.add(new Integer(usedId));
                     }
                     StringSetTransfer.hs_low_tf_used = hs_low_tf_used;
                     rs = s.executeQuery("select id from choices2_test where type='L' and source='" + source + "'");     
                     while (rs.next()){
                        int usedId = rs.getInt("id");
                        hs_low_tf.add(new Integer(usedId));
                     }
                     hs_low_tf.removeAll(hs_low_tf_used);
                     StringSetTransfer.hs_low_tf = hs_low_tf;

                     //now we want to initiate StringSetTransfer.mhs_low,mhs_middle,mhs_high_used,mhs_high,mtotal
                     
                     stmt = myConnection.prepareStatement("select id from usedMultipleQuestions2_test where studentNo=? and source=?");
                     stmt.clearParameters();
                     stmt.setString(1, userName);
                     stmt.setString(2, source);
                     rs = stmt.executeQuery();
                     while (rs.next()){
                        int usedId = rs.getInt("id");
                        mhs_high_used.add(new Integer(usedId));
                     }
                     StringSetTransfer.mhs_high_used = mhs_high_used;
                     rs = s.executeQuery("select id from multipleQuestions2_test where type='H' and source='" + source + "'");
                     while (rs.next()){
                        int usedId = rs.getInt("id");
                        mhs_high.add(new Integer(usedId));
                     }
                     mhs_high.removeAll(mhs_high_used);
                     
                     StringSetTransfer.mhs_high = mhs_high;
                     
                    //now we want to initiate StringSetTransfer.hs_low_fb,hs_middle_fb_used,hs_middle_fb,hs_high_fb,total_fb
                    
                     stmt = myConnection.prepareStatement("select id from usedFillBlank2_test where studentNo=? and source=?");
                     stmt.clearParameters();
                     stmt.setString(1, userName);
                     stmt.setString(2, source);
                     rs = stmt.executeQuery();
                     while (rs.next()){
                        int usedId = rs.getInt("id");
                        hs_middle_fb_used.add(new Integer(usedId));
                     }
                     StringSetTransfer.hs_middle_fb_used = hs_middle_fb_used;
                     
                     
                     rs = s.executeQuery("select id from fillBlank2_test where type='M' and source='" + source + "'");
                     while (rs.next()){
                        int usedId = rs.getInt("id");
                        hs_middle_fb.add(new Integer(usedId));
                     }
                     hs_middle_fb.removeAll(hs_middle_fb_used);
                     StringSetTransfer.hs_middle_fb = hs_middle_fb;
                     
                     //now we want to initiate StringSetTransfer.hs_low_fdb,hs_middle_fdb,hs_high_fdb_used,hs_high_fdb,total_fdb
                    
                     stmt = myConnection.prepareStatement("select id from usedFillDoubleBlanks2_test where studentNo=? and source=?");
                     stmt.clearParameters();
                     stmt.setString(1, userName);
                     stmt.setString(2, source);
                     rs = stmt.executeQuery();                    
                     while (rs.next()){
                        int usedId = rs.getInt("id");
                        hs_high_fdb_used.add(new Integer(usedId));
                     }
                     StringSetTransfer.hs_high_fdb_used = hs_high_fdb_used;
                     rs = s.executeQuery("select id from fillDoubleBlanks2_test where type='H' and source='" + source + "'");
                     while (rs.next()){
                        int usedId = rs.getInt("id");
                        hs_high_fdb.add(new Integer(usedId));
                     }
                     hs_high_fdb.removeAll(hs_high_fdb_used);
                     StringSetTransfer.hs_high_fdb = hs_high_fdb;
                     
                     //now we want to initiate StringSetTransfer.hs_middle_tm, hs_middle_tm_used, total_tm
                     
                     stmt = myConnection.prepareStatement("select id from usedTermMatch_test where studentNo=? and source=?");
                     stmt.clearParameters();
                     stmt.setString(1, userName);
                     stmt.setString(2, source);
                     rs = stmt.executeQuery();                     
                     while (rs.next()){
                        int usedId = rs.getInt("id");
                        hs_middle_tm_used.add(new Integer(usedId));
                     }
                     StringSetTransfer.hs_middle_tm_used = hs_middle_tm_used;
                     rs = s.executeQuery("select id from termMatch_test where type='M' and source='" + source + "'");
                     while (rs.next()){
                        int usedId = rs.getInt("id");
                        hs_middle_tm.add(new Integer(usedId));
                     }
                     hs_middle_tm.removeAll(hs_middle_tm_used);
                     StringSetTransfer.hs_middle_tm = hs_middle_tm;

                     //now we want to initiate StringSetTransfer.hs_high_m, hs_high_m_used, total_m                     
                     stmt = myConnection.prepareStatement("select id from usedMultiply_test where studentNo=? and source=?");
                     stmt.clearParameters();
                     stmt.setString(1, userName);
                     stmt.setString(2, source);
                     rs = stmt.executeQuery();                     
                     while (rs.next()){
                        int usedId = rs.getInt("id");
                        hs_high_m_used.add(new Integer(usedId));
                     }
                     StringSetTransfer.hs_high_m_used = hs_high_m_used;
                     rs = s.executeQuery("select id from multiply_test where type='H' and source='" + source + "'");
                     while (rs.next()){
                        int usedId = rs.getInt("id");
                        hs_high_m.add(new Integer(usedId));
                     }
                     hs_high_m.removeAll(hs_high_m_used);
                     StringSetTransfer.hs_high_m = hs_high_m;

                     //now we want to initiate StringSetTransfer.hs_high_d, hs_high_d_used, total_d                     
                     stmt = myConnection.prepareStatement("select id from usedDivision_test where studentNo=? and source=?");
                     stmt.clearParameters();
                     stmt.setString(1, userName);
                     stmt.setString(2, source);
                     rs = stmt.executeQuery();                     
                     while (rs.next()){
                        int usedId = rs.getInt("id");
                        hs_high_d_used.add(new Integer(usedId));
                     }
                     StringSetTransfer.hs_high_d_used = hs_high_d_used;
                     rs = s.executeQuery("select id from division_test where type='H' and source='" + source + "'");
                     while (rs.next()){
                        int usedId = rs.getInt("id");
                        hs_high_d.add(new Integer(usedId));
                     }
                     hs_high_d.removeAll(hs_high_d_used);
                     StringSetTransfer.hs_high_d = hs_high_d;

                     //now we want to initiate StringSetTransfer.hs_high_c2, hs_high_c2_used, total_c2                     
                     stmt = myConnection.prepareStatement("select id from usedComposition_test where studentNo=? and source=?");
                     stmt.clearParameters();
                     stmt.setString(1, userName);
                     stmt.setString(2, source);
                     rs = stmt.executeQuery();                     
                     while (rs.next()){
                        int usedId = rs.getInt("id");
                        hs_high_c2_used.add(new Integer(usedId));
                     }
                     StringSetTransfer.hs_high_c2_used = hs_high_c2_used;
                     rs = s.executeQuery("select id from composition_test where type='H' and source='" + source + "'");
                     while (rs.next()){
                        int usedId = rs.getInt("id");
                        hs_high_c2.add(new Integer(usedId));
                     }
                     hs_high_c2.removeAll(hs_high_c2_used);
                     StringSetTransfer.hs_high_c2 = hs_high_c2;

               //System.out.println("the total number of questions: " + (StringSetTransfer.total_fdb+StringSetTransfer.total_fb+StringSetTransfer.mtotal+StringSetTransfer.total_tf+StringSetTransfer.total) + "");
               System.out.println("among which, the total number of type low questions: " + (StringSetTransfer.hs_low.size()+StringSetTransfer.hs_low_tf.size()));
               System.out.println("among which, the total number of type middle questions: " + (StringSetTransfer.hs_middle_fb.size()+StringSetTransfer.hs_middle_tm.size()) + "");     
               System.out.println("among which, the total number of type middle questions of termMatch: " + StringSetTransfer.hs_middle_tm.size()); 
               System.out.println("among which, the total number of type high questions: " + (StringSetTransfer.hs_high_fdb.size()+StringSetTransfer.mhs_high.size()+StringSetTransfer.hs_high_m.size()+StringSetTransfer.hs_high_d.size()+StringSetTransfer.hs_high_c2.size()) + "");          
                System.out.println("among which, StringSetTransfer.hs_high_c2.size()= " + StringSetTransfer.hs_high_c2.size() + "");      

                     s = myConnection.createStatement();
                     s.executeUpdate("update student_score set status = 'initiated' where studentNo='" + userName + "' ");

                     //Now we want to initiate the score of different problems for different types
                     stmt = myConnection.prepareStatement("select id, score from termMatch_test_score where source=?");
                     stmt.clearParameters();                     
                     stmt.setString(1, source);
                     rs = stmt.executeQuery();                     
                     while (rs.next()){
                        int tempId = rs.getInt("id");
                        int tempScore = rs.getInt("score");
                        StringSetTransfer.hm_tm.put(new Integer(tempId), new Integer(tempScore));
                     }

                     stmt = myConnection.prepareStatement("select id, score from fillBlank2_test_score where source=?");
                     stmt.clearParameters();                     
                     stmt.setString(1, source);
                     rs = stmt.executeQuery();                     
                     while (rs.next()){
                        int tempId = rs.getInt("id");
                        int tempScore = rs.getInt("score");
                        StringSetTransfer.hm_fb.put(new Integer(tempId), new Integer(tempScore));
                     }

                     stmt = myConnection.prepareStatement("select id, score from fillDoubleBlanks2_test_score where source=?");
                     stmt.clearParameters();                     
                     stmt.setString(1, source);
                     rs = stmt.executeQuery();                     
                     while (rs.next()){
                        int tempId = rs.getInt("id");
                        int tempScore = rs.getInt("score");
                        StringSetTransfer.hm_fdb.put(new Integer(tempId), new Integer(tempScore));
                     }

                     stmt = myConnection.prepareStatement("select id, score from questions2_test_score where source=?");
                     stmt.clearParameters();                     
                     stmt.setString(1, source);
                     rs = stmt.executeQuery();                     
                     while (rs.next()){
                        int tempId = rs.getInt("id");
                        int tempScore = rs.getInt("score");
                        StringSetTransfer.hm_q.put(new Integer(tempId), new Integer(tempScore));
                     }

                     stmt = myConnection.prepareStatement("select id, score from multiplequestions2_test_score where source=?");
                     stmt.clearParameters();                     
                     stmt.setString(1, source);
                     rs = stmt.executeQuery();                     
                     while (rs.next()){
                        int tempId = rs.getInt("id");
                        int tempScore = rs.getInt("score");
                        StringSetTransfer.hm_mq.put(new Integer(tempId), new Integer(tempScore));
                     }

                     stmt = myConnection.prepareStatement("select id, score from choices2_test_score where source=?");
                     stmt.clearParameters();                     
                     stmt.setString(1, source);
                     rs = stmt.executeQuery();                     
                     while (rs.next()){
                        int tempId = rs.getInt("id");
                        int tempScore = rs.getInt("score");
                        StringSetTransfer.hm_c.put(new Integer(tempId), new Integer(tempScore));
                     }

                     stmt = myConnection.prepareStatement("select id, score from multiply_test_score where source=?");
                     stmt.clearParameters();                     
                     stmt.setString(1, source);
                     rs = stmt.executeQuery();                     
                     while (rs.next()){
                        int tempId = rs.getInt("id");
                        int tempScore = rs.getInt("score");
                        StringSetTransfer.hm_m.put(new Integer(tempId), new Integer(tempScore));
                     }

                     stmt = myConnection.prepareStatement("select id, score from division_test_score where source=?");
                     stmt.clearParameters();                     
                     stmt.setString(1, source);
                     rs = stmt.executeQuery();                     
                     while (rs.next()){
                        int tempId = rs.getInt("id");
                        int tempScore = rs.getInt("score");
                        StringSetTransfer.hm_d.put(new Integer(tempId), new Integer(tempScore));
                     }

                     stmt = myConnection.prepareStatement("select id, score from composition_test_score where source=?");
                     stmt.clearParameters();                     
                     stmt.setString(1, source);
                     rs = stmt.executeQuery();                     
                     while (rs.next()){
                        int tempId = rs.getInt("id");
                        int tempScore = rs.getInt("score");
                        StringSetTransfer.hm_c2.put(new Integer(tempId), new Integer(tempScore));
                     }

                     StringSetTransfer.init = true;

               }//THE END OF (StringSetTransfer.init == false)

               s = myConnection.createStatement();              
               pb2.setPlayerId("player1");              
               pb2.setPlanStatus("not finished");

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
        }else{//first.equals("false")
            String answeredHashSetStr = (String)((SmartRandomForm) form).getAnsweredHashSet();
            hs = StringSetTransfer.stringToSet(answeredHashSetStr);
            String answeredHashSetStr_tf = (String)((SmartRandomForm) form).getAnsweredHashSet_tf();
            hs_tf = StringSetTransfer.stringToSet(answeredHashSetStr_tf);
            String answered_M_HashSetStr = (String)((SmartRandomForm) form).getAnswered_M_HashSet();
            mhs = StringSetTransfer.stringToSet(answered_M_HashSetStr);
            String answeredHashSetStr_fb = (String)((SmartRandomForm) form).getAnsweredHashSet_fb();
            hs_fb = StringSetTransfer.stringToSet(answeredHashSetStr_fb);
            String answeredHashSetStr_fdb = (String)((SmartRandomForm) form).getAnsweredHashSet_fdb();
            hs_fdb = StringSetTransfer.stringToSet(answeredHashSetStr_fdb);
            String answeredHashSetStr_tm = (String)((SmartRandomForm) form).getAnsweredHashSet_tm();  
            hs_tm = StringSetTransfer.stringToSet(answeredHashSetStr_tm);
            String answeredHashSetStr_m = (String)((SmartRandomForm) form).getAnswered_MY_HashSet();  
            hs_m = StringSetTransfer.stringToSet(answeredHashSetStr_m);   
            String answeredHashSetStr_d = (String)((SmartRandomForm) form).getAnswered_D_HashSet();  
            hs_d = StringSetTransfer.stringToSet(answeredHashSetStr_d); 
            String answeredHashSetStr_c = (String)((SmartRandomForm) form).getAnswered_C_HashSet();  
            hs_c2 = StringSetTransfer.stringToSet(answeredHashSetStr_c);                    
        }

        // Remove the Form Bean - don't need to carry values forward
        request.removeAttribute(mapping.getAttribute());

        session.setAttribute( Constants.PERSON_KEY, pb2); 
        request.setAttribute( Constants.PERSON_KEY, pb2); 

        /*
        if (userName.equals("2000")){
           return (mapping.findForward("Show2000Zero"));
        }
        */
         
        String lastType = (String)((SmartRandomForm) form).getLastType();
        String thisType = (String)((SmartRandomForm) form).getThisType();
        String continueRight = (String)((SmartRandomForm) form).getContinueRight();
        String continueWrong = (String)((SmartRandomForm) form).getContinueWrong();
        int intContinueRight = Integer.parseInt(continueRight);
        int intContinueWrong = Integer.parseInt(continueWrong);
        String neverHigh = (String)((SmartRandomForm) form).getNeverHigh();
        boolean boolNeverHigh = Boolean.parseBoolean(neverHigh);
        System.out.println("Inside SmartRandomAction.java, lastType = " + lastType);

        int randomNumber = 0;
        HashSet control = new HashSet();
        control.add(new Integer(0)); control.add(new Integer(1)); control.add(new Integer(2)); control.add(new Integer(3)); control.add(new Integer(4));
        HashSet forCom = new HashSet();
        int id = 0;
        HashSet forType = new HashSet();
        String lastCorrect = (String)((SmartRandomForm) form).getLastCorrect();
        System.out.println("Inside SmartRandomAction.java, lastCorrect = " + lastCorrect);

        boolean boolLowNotUsedUp = true;
        boolean boolMiddleNotUsedUp = true;
        boolean boolHighNotUsedUp = true;
        
        // if first == true, then thisType = L for sure, thus we can directly find id
        if (first.equals("true")){              
           lastType = "L";
           thisType = "L";             
        }

        //in below we will implement the same logic no matter first==true or not
        outer:
        while (true){ 
                  if (StringSetTransfer.testDebug == true){
                     thisType = "M";
                     randomNumber = 4;
                     id = 151;
                     break outer;
                  }

                  if (StringSetTransfer.start19==true && StringSetTransfer.done19==false){
                     thisType = "M";
                     randomNumber = 3;
                     id = 19;
                     break outer;
                  }

                  if (StringSetTransfer.start25==true && StringSetTransfer.done25==false){
                     thisType = "M";
                     randomNumber = 3;
                     id = 25;
                     break outer;
                  }

                  if (StringSetTransfer.start237==true && StringSetTransfer.done237==false){
                     thisType = "M";
                     randomNumber = 3;
                     id = 237;
                     break outer;
                  }

                  if (StringSetTransfer.start282==true && StringSetTransfer.done282==false){
                     thisType = "M";
                     randomNumber = 3;
                     id = 282;
                     break outer;
                  }

                  if (StringSetTransfer.start378==true && StringSetTransfer.done378==false){
                     thisType = "M";
                     randomNumber = 3;
                     id = 378;
                     break outer;
                  }

                  if (StringSetTransfer.start383==true && StringSetTransfer.done383==false){
                     thisType = "M";
                     randomNumber = 3;
                     id = 383;
                     break outer;
                  }

                  if (StringSetTransfer.start462==true && StringSetTransfer.done462==false){
                     thisType = "M";
                     randomNumber = 3;
                     id = 462;
                     break outer;
                  }

                  if (StringSetTransfer.start470==true && StringSetTransfer.done470==false){
                     thisType = "M";
                     randomNumber = 3;
                     id = 470;
                     break outer;
                  }  

                  if (StringSetTransfer.start583==true && StringSetTransfer.done583==false){
                     thisType = "M";
                     randomNumber = 3;
                     id = 583;
                     break outer;
                  }      

                  if (StringSetTransfer.start611==true && StringSetTransfer.done611==false){
                     thisType = "M";
                     randomNumber = 3;
                     id = 611;
                     break outer;
                  }          

                  if (StringSetTransfer.start614==true && StringSetTransfer.done614==false){
                     thisType = "M";
                     randomNumber = 3;
                     id = 614;
                     break outer;
                  }

                  if (StringSetTransfer.start688==true && StringSetTransfer.done688==false){
                     thisType = "M";
                     randomNumber = 3;
                     id = 688;
                     break outer;
                  } 

                  if (StringSetTransfer.start711==true && StringSetTransfer.done711==false){
                     thisType = "M";
                     randomNumber = 3;
                     id = 711;
                     break outer;
                  }  

                  if (StringSetTransfer.start722==true && StringSetTransfer.done722==false){
                     thisType = "M";
                     randomNumber = 3;
                     id = 722;
                     break outer;
                  } 

                  if (StringSetTransfer.start739==true && StringSetTransfer.done739==false){
                     thisType = "M";
                     randomNumber = 3;
                     id = 739;
                     break outer;
                  } 

                  if (StringSetTransfer.start749==true && StringSetTransfer.done749==false){
                     thisType = "M";
                     randomNumber = 3;
                     id = 749;
                     break outer;
                  } 

                  if (StringSetTransfer.start789==true && StringSetTransfer.done789==false){
                     thisType = "M";
                     randomNumber = 3;
                     id = 789;
                     break outer;
                  } 

                  if (StringSetTransfer.start796==true && StringSetTransfer.done796==false){
                     thisType = "M";
                     randomNumber = 3;
                     id = 796;
                     break outer;
                  } 

                  if (StringSetTransfer.start809==true && StringSetTransfer.done809==false){
                     thisType = "M";
                     randomNumber = 3;
                     id = 809;
                     break outer;
                  }

                  if (StringSetTransfer.start861==true && StringSetTransfer.done861==false){
                     thisType = "M";
                     randomNumber = 3;
                     id = 861;
                     break outer;
                  } 

                  if (StringSetTransfer.start874==true && StringSetTransfer.done874==false){
                     thisType = "M";
                     randomNumber = 3;
                     id = 874;
                     break outer;
                  } 

                  if (StringSetTransfer.start879==true && StringSetTransfer.done879==false){
                     thisType = "M";
                     randomNumber = 3;
                     id = 879;
                     break outer;
                  }  

                  if (StringSetTransfer.start883==true && StringSetTransfer.done883==false){
                     thisType = "M";
                     randomNumber = 3;
                     id = 883;
                     break outer;
                  } 

                  if (StringSetTransfer.start888==true && StringSetTransfer.done888==false){
                     thisType = "M";
                     randomNumber = 3;
                     id = 888;
                     break outer;
                  }

                  if (StringSetTransfer.start909==true && StringSetTransfer.done909==false){
                     thisType = "M";
                     randomNumber = 3;
                     id = 909;
                     break outer;
                  }

                  if (StringSetTransfer.start330==true && StringSetTransfer.done330==false){
                     thisType = "H";
                     randomNumber = 4;
                     id = 330;
                     break outer;
                  }

                  if (StringSetTransfer.start382==true && StringSetTransfer.done382==false){
                     thisType = "H";
                     randomNumber = 4;
                     id = 382;
                     break outer;
                  }

                  if (StringSetTransfer.start445==true && StringSetTransfer.done445==false){
                     thisType = "H";
                     randomNumber = 4;
                     id = 445;
                     break outer;
                  }

                  if (StringSetTransfer.start447==true && StringSetTransfer.done447==false){
                     thisType = "H";
                     randomNumber = 4;
                     id = 447;
                     break outer;
                  }

                  if (StringSetTransfer.start473==true && StringSetTransfer.done473==false){
                     thisType = "H";
                     randomNumber = 4;
                     id = 473;
                     break outer;
                  }

                  if (StringSetTransfer.start553==true && StringSetTransfer.done553==false){
                     thisType = "H";
                     randomNumber = 4;
                     id = 553;
                     break outer;
                  }

                  if (StringSetTransfer.start558==true && StringSetTransfer.done558==false){
                     thisType = "H";
                     randomNumber = 4;
                     id = 558;
                     break outer;
                  }

                  if (StringSetTransfer.start577==true && StringSetTransfer.done577==false){
                     thisType = "H";
                     randomNumber = 4;
                     id = 577;
                     break outer;
                  }

                  if (StringSetTransfer.start582==true && StringSetTransfer.done582==false){
                     thisType = "H";
                     randomNumber = 4;
                     id = 582;
                     break outer;
                  }
          
                  if (StringSetTransfer.start623==true && StringSetTransfer.done623==false){
                     thisType = "H";
                     randomNumber = 4;
                     id = 623;
                     break outer;
                  }

                  if (StringSetTransfer.start625==true && StringSetTransfer.done625==false){
                     thisType = "H";
                     randomNumber = 4;
                     id = 625;
                     break outer;
                  }

                  if (StringSetTransfer.start638==true && StringSetTransfer.done638==false){
                     thisType = "H";
                     randomNumber = 4;
                     id = 638;
                     break outer;
                  }

                  if (StringSetTransfer.start645==true && StringSetTransfer.done645==false){
                     thisType = "H";
                     randomNumber = 4;
                     id = 645;
                     break outer;
                  }

                  if (StringSetTransfer.start746==true && StringSetTransfer.done746==false){
                     thisType = "H";
                     randomNumber = 4;
                     id = 746;
                     break outer;
                  }

                  if (StringSetTransfer.start748==true && StringSetTransfer.done748==false){
                     thisType = "H";
                     randomNumber = 4;
                     id = 748;
                     break outer;
                  } 

                  if (StringSetTransfer.start750==true && StringSetTransfer.done750==false){
                     thisType = "H";
                     randomNumber = 4;
                     id = 750;
                     break outer;
                  } 

                  if (StringSetTransfer.start752==true && StringSetTransfer.done752==false){
                     thisType = "H";
                     randomNumber = 4;
                     id = 752;
                     break outer;
                  }

                  if (StringSetTransfer.start810==true && StringSetTransfer.done810==false){
                     thisType = "H";
                     randomNumber = 4;
                     id = 810;
                     break outer;
                  }

                  if (StringSetTransfer.start858==true && StringSetTransfer.done858==false){
                     thisType = "H";
                     randomNumber = 4;
                     id = 858;
                     break outer;
                  }

                  id = 0;           
          
                 //however, first, we want to check if there are exceptions
                 //now to simplify the process, we want to get rid of this exception
                 //if there is no exception, then we want to decide thisType 
                 
                 //we want to enforce the lastCorrect mechanism only when none of low,middle,high are used up
                 if (boolLowNotUsedUp && boolMiddleNotUsedUp && boolHighNotUsedUp){
                    boolean boolLastCorrect = Boolean.parseBoolean(lastCorrect);
                    if (boolLastCorrect){
                       switch (lastType.charAt(0)){
                          case 'L':
                             thisType = 'M' + "";
                             break;
                          case 'M':
                             thisType = 'H' + "";              
                             break;
                          case 'H':
                             thisType = 'H' + "";           
                             break;
                          default:
                             thisType = 'L' + "";
                             break;
                       }
                    }else{ //booLastCorrect == false
                       switch (lastType.charAt(0)){
                          case 'L':
                             thisType = 'L' + "";      
                             break;
                          case 'M':
                             thisType = 'L' + "";
                             break;
                          case 'H':
                             thisType = 'M' + "";
                             break;
                          default:
                             thisType = 'L' + "";
                             break;
                        }
                     }
                 }
             
             //up to this point, we have decided the value of thisChar for both first==true and first!=true

                 System.out.println("Inside SmartRandomAction, thisType = " + thisType);
                 //below we will not care about any used set when we decide whether low,middle,high are used up
                 switch (thisType.charAt(0)){
                    case 'L':
                       System.out.println("Inside SmartRandomAction, case 'L', thisType = " + thisType);
                       HashSet temp_hs_low = new HashSet(StringSetTransfer.hs_low);
                       temp_hs_low.removeAll(StringSetTransfer.hs_low_used);
                       HashSet temp_hs_low_tf = new HashSet(StringSetTransfer.hs_low_tf);
                       temp_hs_low_tf.removeAll(StringSetTransfer.hs_low_tf_used);
                       if (hs.containsAll(temp_hs_low)){
                          if (hs_tf.containsAll(temp_hs_low_tf)){
                             boolLowNotUsedUp = false;
                             if (boolMiddleNotUsedUp){
                                thisType = "M";
                                continue outer;
                             }else if (boolHighNotUsedUp){
                                thisType = "H";
                                continue outer;
                             }else{
                                planStatus = "finished";
                                pb2.setPlanStatus(planStatus);
                                id = 0;
                                break outer;
                             }
                          }
                       }
                       randomNumber = (int)(Math.random() * 2); //generate randomNumber= 0,1
                       switch (randomNumber){
                          case 0:
                             id = StringSetTransfer.getRandomNumber(thisType.charAt(0), hs);
                             if (id == 0){
                                continue outer;
                             } 
                             break;
                          case 1:
                             id = StringSetTransfer.getRandomNumber_tf(thisType.charAt(0), hs_tf); 
                             if (id == 0){
                                continue outer;
                             }
                             break;
                          default:
                             break;    
                       }//up to this point, we have also decided the value of id for type 'L'
                       break;
                    case 'M':
                       System.out.println("Inside SmartRandomAction, case 'M', thisType = " + thisType);
                       
                       if (hs_fb.containsAll(StringSetTransfer.hs_middle_fb)){
                          if (hs_tm.containsAll(StringSetTransfer.hs_middle_tm)){
                             System.out.println("Inside SmartRandomAction, case 'M', this means all middle problems are ex = " + thisType);
                             boolMiddleNotUsedUp = false;
                             if (boolLowNotUsedUp){
                                thisType = "L";
                                continue outer;
                             }else if (boolHighNotUsedUp){
                                thisType = "H";
                                continue outer;
                             }else{
                                planStatus = "finished";
                                pb2.setPlanStatus(planStatus);
                                id = 0;
                                break outer;
                             }  
                          }
                       }
                       if (Math.random() > 0.5){
                          randomNumber = 3;
                       }else{
                          randomNumber = 5;
                       }
                       switch(randomNumber){
                          case 3:
                             
                             id = StringSetTransfer.getRandomNumber_fb(thisType.charAt(0), hs_fb); 
                             if (id == 0){
                                continue outer;
                             }
                             break;
                          case 5:
                             id = StringSetTransfer.getRandomNumber_tm(thisType.charAt(0), hs_tm); 
                             if (id == 0){
                                continue outer;
                             }
                             break;
                          default:
                             break;
                       }
                       //up to this point, we have also decided the value of id for type 'M'
                       break;
                    case 'H':
                       System.out.println("Inside SmartRandomAction, case 'H', thisType = " + thisType);
                       if (mhs.containsAll(StringSetTransfer.mhs_high)){
                          if (hs_fdb.containsAll(StringSetTransfer.hs_high_fdb)){
                             if (hs_m.containsAll(StringSetTransfer.hs_high_m)){
                                if (hs_d.containsAll(StringSetTransfer.hs_high_d)){
                                      if (hs_c2.containsAll(StringSetTransfer.hs_high_c2)){
                                      boolHighNotUsedUp = false;
                                      if (boolLowNotUsedUp){
                                         thisType = "L";
                                         continue outer;
                                      }else if (boolMiddleNotUsedUp){
                                         thisType = "M";
                                         continue outer;
                                      }else{
                                         planStatus = "finished";
                                         pb2.setPlanStatus(planStatus);
                                         id = 0;
                                         break outer;
                                      }
                                   }
                                }
                             }  
                          }
                       }
                       if (Math.random() <= 0.2){
                          randomNumber = 2;
                       }else if ((Math.random()>0.2) && (Math.random()<=0.4)){
                          randomNumber = 4;
                       }else if ((Math.random()>0.4) && (Math.random()<=0.6)){
                          randomNumber = 6;
                       }else if ((Math.random()>0.6) && (Math.random()<=0.8)){
                          randomNumber = 7;
                       }else{
                          randomNumber = 8;
                       }
                       switch(randomNumber){
                          case 2:
                             id = StringSetTransfer.get_M_RandomNumber(thisType.charAt(0), mhs); 
                             if (id == 0){
                                continue outer;
                             }
                             break;
                          case 4:
                             id = StringSetTransfer.getRandomNumber_fdb(thisType.charAt(0), hs_fdb); 
                             if (id == 0){
                                continue outer;
                             }
                             break;
                          case 6:
                             id = StringSetTransfer.getRandomNumber_m(thisType.charAt(0), hs_m); 
                             if (id == 0){
                                continue outer;
                             }
                             break;
                          case 7:
                             id = StringSetTransfer.getRandomNumber_d(thisType.charAt(0), hs_d); 
                             if (id == 0){
                                continue outer;
                             }
                             break;
                          case 8:
                             id = StringSetTransfer.getRandomNumber_c2(thisType.charAt(0), hs_c2); 
                             if (id == 0){
                                continue outer;
                             }
                             break;
                          default:
                             break;
                       }//up to this point, we have also decided the value of id for type 'H'
                       break;
                    default:
                       break;
                 }
                 //up to this point, we have also decided the value of id
                  
                 lastType = thisType;


          if (id != 0){
             break outer;
          }
     }//end of while(true)

     System.out.println("Inside SmartRandomAction, after id and randomNumber is obtained, id = " + id + " and randomNumber = " + randomNumber);

     if (id != 0){
        try {
           dataSource = getDataSource(request);
           myConnection = dataSource.getConnection();
           //Class.forName("org.hsqldb.jdbcDriver");
           //myConnection = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/xdb", "SA", "");    
                
           pb2.setLastType(lastType);
           pb2.setThisType(thisType);
           pb2.setContinueRight(intContinueRight);
           pb2.setContinueWrong(intContinueWrong);
           pb2.setNeverHigh(boolNeverHigh);

           switch (randomNumber){
              case 0:
                  hs.add(new Integer(id));  
                  stmt = myConnection.prepareStatement("select * from questions2_test where id=?");
                  stmt.clearParameters();
                  stmt.setInt(1, id);
                  rs = stmt.executeQuery();
                  while (rs.next()){
                     pb.setId(rs.getInt("id"));
                     pb.setStatement(rs.getString("statement"));
                     pb.setChoiceA(rs.getString("choiceA"));
                     pb.setChoiceB(rs.getString("choiceB"));
                     pb.setChoiceC(rs.getString("choiceC"));
                     pb.setChoiceD(rs.getString("choiceD"));
                     pb.setCorrectChoice(rs.getString("correctChoice")); 
                     pb.setSolution(rs.getString("solution"));                             
                     pb.setType(rs.getString("type"));
                  }
                  break;
               case 1:
                  hs_tf.add(new Integer(id)); 
                  stmt = myConnection.prepareStatement("select * from choices2_test where id=?");
                  //getServlet().log("inside SmartRandomAction.java, when select * from choices2 where id=?,  id = " + id);
                  stmt.clearParameters();
                  stmt.setInt(1, id);
                  rs = stmt.executeQuery();
                  while (rs.next()){
                     cb.setId(rs.getInt("id"));
                     cb.setStatement(rs.getString("statement"));
                     cb.setChoice(rs.getString("choice"));     
                     cb.setSolution(rs.getString("solution"));         
                     cb.setType(rs.getString("type"));
                  }
                  break;
               case 2:
                  mhs.add(new Integer(id));  
                  stmt = myConnection.prepareStatement("select * from multipleQuestions2_test where id=?");
                  stmt.clearParameters();
                  stmt.setInt(1, id);
                  rs = stmt.executeQuery();
                  while (rs.next()){
                     mpb.setId(rs.getInt("id"));
                     mpb.setStatement(rs.getString("statement"));
                     mpb.setChoiceA(rs.getString("choiceA"));
                     mpb.setChoiceB(rs.getString("choiceB"));
                     mpb.setChoiceC(rs.getString("choiceC"));
                     mpb.setChoiceD(rs.getString("choiceD"));
                     mpb.setChoiceE(rs.getString("choiceE"));
                     mpb.setCorrectChoice(rs.getString("correctChoice")); 
                     mpb.setSolution(rs.getString("solution"));                              
                     mpb.setType(rs.getString("type"));
                  }
                  break;
               case 3:
                  if (id>=19 && id<=24){
                     if (StringSetTransfer.start19==false){
                        StringSetTransfer.start19=true; 
                     }                    
                     boolean done = true;
                     int i=19;
                     for (i=19; i<=24; i++){
                        if (!hs_fb.contains(new Integer(i))){
                           id = i;
                           if (i<24)
                              done = false;
                           break;
                        }
                     }
                     StringSetTransfer.done19 = done;
                  }

                  if (id>=25 && id<=30){
                     if (StringSetTransfer.start25==false){
                        StringSetTransfer.start25=true; 
                     }                    
                     boolean done = true;
                     int i=25;
                     for (i=25; i<=30; i++){
                        if (!hs_fb.contains(new Integer(i))){
                           id = i;
                           if (i<30)
                              done = false;
                           break;
                        }
                     }
                     StringSetTransfer.done25 = done;
                  }   

                  if (id>=237 && id<=239){
                     if (StringSetTransfer.start237==false){
                        StringSetTransfer.start237=true; 
                     }                    
                     boolean done = true;
                     int i=237;
                     for (i=237; i<=239; i++){
                        if (!hs_fb.contains(new Integer(i))){
                           id = i;
                           if (i<239)
                              done = false;
                           break;
                        }
                     }
                     StringSetTransfer.done237 = done;
                  } 

                  if (id>=282 && id<=284){
                     if (StringSetTransfer.start282==false){
                        StringSetTransfer.start282=true; 
                     }                    
                     boolean done = true;
                     int i=282;
                     for (i=282; i<=284; i++){
                        if (!hs_fb.contains(new Integer(i))){
                           id = i;
                           if (i<284)
                              done = false;
                           break;
                        }
                     }
                     StringSetTransfer.done282 = done;
                  }

                  if (id>=378 && id<=382){
                     if (StringSetTransfer.start378==false){
                        StringSetTransfer.start378=true; 
                     }                    
                     boolean done = true;
                     int i=378;
                     for (i=378; i<=382; i++){
                        if (!hs_fb.contains(new Integer(i))){
                           id = i;
                           if (i<382)
                              done = false;
                           break;
                        }
                     }
                     StringSetTransfer.done378 = done;
                  } 

                  if (id>=383 && id<=388){
                     if (StringSetTransfer.start383==false){
                        StringSetTransfer.start383=true; 
                     }                    
                     boolean done = true;
                     int i=383;
                     for (i=383; i<=388; i++){
                        if (!hs_fb.contains(new Integer(i))){
                           id = i;
                           if (i<388)
                              done = false;
                           break;
                        }
                     }
                     StringSetTransfer.done383 = done;
                  } 

                  if (id>=462 && id<=467){
                     if (StringSetTransfer.start462==false){
                        StringSetTransfer.start462=true; 
                     }                    
                     boolean done = true;
                     int i=462;
                     for (i=462; i<=467; i++){
                        if (!hs_fb.contains(new Integer(i))){
                           id = i;
                           if (i<467)
                              done = false;
                           break;
                        }
                     }
                     StringSetTransfer.done462 = done;
                  }

                  if (id>=470 && id<=474){
                     if (StringSetTransfer.start470==false){
                        StringSetTransfer.start470=true; 
                     }                    
                     boolean done = true;
                     int i=470;
                     for (i=470; i<=474; i++){
                        if (!hs_fb.contains(new Integer(i))){
                           id = i;
                           if (i<474)
                              done = false;
                           break;
                        }
                     }
                     StringSetTransfer.done470 = done;
                  }

                  if (id>=583 && id<=584){
                     if (StringSetTransfer.start583==false){
                        StringSetTransfer.start583=true; 
                     }                    
                     boolean done = true;
                     int i=583;
                     for (i=583; i<=584; i++){
                        if (!hs_fb.contains(new Integer(i))){
                           id = i;
                           if (i<584)
                              done = false;
                           break;
                        }
                     }
                     StringSetTransfer.done583 = done;
                  }  

                  if (id>=611 && id<=613){
                     if (StringSetTransfer.start611==false){
                        StringSetTransfer.start611=true; 
                     }                    
                     boolean done = true;
                     int i=611;
                     for (i=611; i<=613; i++){
                        if (!hs_fb.contains(new Integer(i))){
                           id = i;
                           if (i<613)
                              done = false;
                           break;
                        }
                     }
                     StringSetTransfer.done611 = done;
                  }  

                  if (id>=614 && id<=616){
                     if (StringSetTransfer.start614==false){
                        StringSetTransfer.start614=true; 
                     }                    
                     boolean done = true;
                     int i=614;
                     for (i=614; i<=616; i++){
                        if (!hs_fb.contains(new Integer(i))){
                           id = i;
                           if (i<616)
                              done = false;
                           break;
                        }
                     }
                     StringSetTransfer.done614 = done;
                  }

                  if (id>=688 && id<=693){
                     if (StringSetTransfer.start688==false){
                        StringSetTransfer.start688=true; 
                     }                    
                     boolean done = true;
                     int i=688;
                     for (i=688; i<=693; i++){
                        if (!hs_fb.contains(new Integer(i))){
                           id = i;
                           if (i<693)
                              done = false;
                           break;
                        }
                     }
                     StringSetTransfer.done688 = done;
                  }

                  if (id>=711 && id<=714){
                     if (StringSetTransfer.start711==false){
                        StringSetTransfer.start711=true; 
                     }                    
                     boolean done = true;
                     int i=711;
                     for (i=711; i<=714; i++){
                        if (!hs_fb.contains(new Integer(i))){
                           id = i;
                           if (i<714)
                              done = false;
                           break;
                        }
                     }
                     StringSetTransfer.done711 = done;
                  }

                  if (id>=722 && id<=727){
                     if (StringSetTransfer.start722==false){
                        StringSetTransfer.start722=true; 
                     }                    
                     boolean done = true;
                     int i=722;
                     for (i=722; i<=727; i++){
                        if (!hs_fb.contains(new Integer(i))){
                           id = i;
                           if (i<727)
                              done = false;
                           break;
                        }
                     }
                     StringSetTransfer.done722 = done;
                  } 

                  if (id>=739 && id<=744){
                     if (StringSetTransfer.start739==false){
                        StringSetTransfer.start739=true; 
                     }                    
                     boolean done = true;
                     int i=739;
                     for (i=739; i<=744; i++){
                        if (!hs_fb.contains(new Integer(i))){
                           id = i;
                           if (i<744)
                              done = false;
                           break;
                        }
                     }
                     StringSetTransfer.done739 = done;
                  }   

                  if (id>=749 && id<=753){
                     if (StringSetTransfer.start749==false){
                        StringSetTransfer.start749=true; 
                     }                    
                     boolean done = true;
                     int i=749;
                     for (i=749; i<=753; i++){
                        if (!hs_fb.contains(new Integer(i))){
                           id = i;
                           if (i<753)
                              done = false;
                           break;
                        }
                     }
                     StringSetTransfer.done749 = done;
                  }  

                  if (id>=789 && id<=795){
                     if (StringSetTransfer.start789==false){
                        StringSetTransfer.start789=true; 
                     }                    
                     boolean done = true;
                     int i=789;
                     for (i=789; i<=795; i++){
                        if (!hs_fb.contains(new Integer(i))){
                           id = i;
                           if (i<795)
                              done = false;
                           break;
                        }
                     }
                     StringSetTransfer.done789 = done;
                  }

                  if (id>=796 && id<=806){
                     if (StringSetTransfer.start796==false){
                        StringSetTransfer.start796=true; 
                     }                    
                     boolean done = true;
                     int i=796;
                     for (i=796; i<=806; i++){
                        if (!hs_fb.contains(new Integer(i))){
                           id = i;
                           if (i<806)
                              done = false;
                           break;
                        }
                     }
                     StringSetTransfer.done796 = done;
                  }

                  if (id>=809 && id<=811){
                     if (StringSetTransfer.start809==false){
                        StringSetTransfer.start809=true; 
                     }                    
                     boolean done = true;
                     int i=809;
                     for (i=809; i<=811; i++){
                        if (!hs_fb.contains(new Integer(i))){
                           id = i;
                           if (i<811)
                              done = false;
                           break;
                        }
                     }
                     StringSetTransfer.done809 = done;
                  }

                  if (id>=861 && id<=867){
                     if (StringSetTransfer.start861==false){
                        StringSetTransfer.start861=true; 
                     }                    
                     boolean done = true;
                     int i=861;
                     for (i=861; i<=867; i++){
                        if (!hs_fb.contains(new Integer(i))){
                           id = i;
                           if (i<867)
                              done = false;
                           break;
                        }
                     }
                     StringSetTransfer.done861 = done;
                  } 

                  if (id>=874 && id<=876){
                     if (StringSetTransfer.start874==false){
                        StringSetTransfer.start874=true; 
                     }                    
                     boolean done = true;
                     int i=874;
                     for (i=874; i<=876; i++){
                        if (!hs_fb.contains(new Integer(i))){
                           id = i;
                           if (i<876)
                              done = false;
                           break;
                        }
                     }
                     StringSetTransfer.done874 = done;
                  }

                  if (id>=879 && id<=882){
                     if (StringSetTransfer.start879==false){
                        StringSetTransfer.start879=true; 
                     }                    
                     boolean done = true;
                     int i=879;
                     for (i=879; i<=882; i++){
                        if (!hs_fb.contains(new Integer(i))){
                           id = i;
                           if (i<882)
                              done = false;
                           break;
                        }
                     }
                     StringSetTransfer.done879 = done;
                  }

                  if (id>=883 && id<=885){
                     if (StringSetTransfer.start883==false){
                        StringSetTransfer.start883=true; 
                     }                    
                     boolean done = true;
                     int i=883;
                     for (i=883; i<=885; i++){
                        if (!hs_fb.contains(new Integer(i))){
                           id = i;
                           if (i<885)
                              done = false;
                           break;
                        }
                     }
                     StringSetTransfer.done883 = done;
                  }

                  if (id>=888 && id<=893){
                     if (StringSetTransfer.start888==false){
                        StringSetTransfer.start888=true; 
                     }                    
                     boolean done = true;
                     int i=888;
                     for (i=888; i<=893; i++){
                        if (!hs_fb.contains(new Integer(i))){
                           id = i;
                           if (i<893)
                              done = false;
                           break;
                        }
                     }
                     StringSetTransfer.done888 = done;
                  }

                  if (id>=909 && id<=912){
                     if (StringSetTransfer.start909==false){
                        StringSetTransfer.start909=true; 
                     }                    
                     boolean done = true;
                     int i=909;
                     for (i=909; i<=912; i++){
                        if (!hs_fb.contains(new Integer(i))){
                           id = i;
                           if (i<912)
                              done = false;
                           break;
                        }
                     }
                     StringSetTransfer.done909 = done;
                  }   

                  hs_fb.add(new Integer(id)); 
                  stmt = myConnection.prepareStatement("select * from fillBlank2_test where id=?");
                  stmt.clearParameters();
                  stmt.setInt(1, id);
                  rs = stmt.executeQuery();
                  while (rs.next()){
                     fbb.setId(rs.getInt("id"));
                     fbb.setStatement_1st(rs.getString("statement_1st"));
                     fbb.setStatement_2nd(rs.getString("statement_2nd"));
                     fbb.setSolution(rs.getString("solution"));
                     fbb.setType(rs.getString("type"));
                  }
                  break;
               case 4:
                  if (id>=330 && id<=335){
                     if (StringSetTransfer.start330==false){
                        StringSetTransfer.start330=true; 
                     }                    
                     boolean done = true;
                     int i=330;
                     for (i=330; i<=335; i++){
                        if (!hs_fdb.contains(new Integer(i))){
                           id = i;
                           if (i<335)
                              done = false;
                           break;
                        }
                     }
                     StringSetTransfer.done330 = done;
                  }

                  if (id>=382 && id<=384){
                     if (StringSetTransfer.start382==false){
                        StringSetTransfer.start382=true; 
                     }                    
                     boolean done = true;
                     int i=382;
                     for (i=382; i<=384; i++){
                        if (!hs_fdb.contains(new Integer(i))){
                           id = i;
                           if (i<384)
                              done = false;
                           break;
                        }
                     }
                     StringSetTransfer.done382 = done;
                  } 

                  if (id>=445 && id<=446){
                     if (StringSetTransfer.start445==false){
                        StringSetTransfer.start445=true; 
                     }                    
                     boolean done = true;
                     int i=445;
                     for (i=445; i<=446; i++){
                        if (!hs_fdb.contains(new Integer(i))){
                           id = i;
                           if (i<446)
                              done = false;
                           break;
                        }
                     }
                     StringSetTransfer.done445 = done;
                  } 

                  if (id>=447 && id<=448){
                     if (StringSetTransfer.start447==false){
                        StringSetTransfer.start447=true; 
                     }                    
                     boolean done = true;
                     int i=447;
                     for (i=447; i<=448; i++){
                        if (!hs_fdb.contains(new Integer(i))){
                           id = i;
                           if (i<448)
                              done = false;
                           break;
                        }
                     }
                     StringSetTransfer.done447 = done;
                  }

                  if (id>=473 && id<=474){
                     if (StringSetTransfer.start473==false){
                        StringSetTransfer.start473=true; 
                     }                    
                     boolean done = true;
                     int i=473;
                     for (i=473; i<=474; i++){
                        if (!hs_fdb.contains(new Integer(i))){
                           id = i;
                           if (i<474)
                              done = false;
                           break;
                        }
                     }
                     StringSetTransfer.done473 = done;
                  }

                  if (id>=553 && id<=557){
                     if (StringSetTransfer.start553==false){
                        StringSetTransfer.start553=true; 
                     }                    
                     boolean done = true;
                     int i=553;
                     for (i=553; i<=557; i++){
                        if (!hs_fdb.contains(new Integer(i))){
                           id = i;
                           if (i<557)
                              done = false;
                           break;
                        }
                     }
                     StringSetTransfer.done553 = done;
                  }

                  if (id>=558 && id<=560){
                     if (StringSetTransfer.start558==false){
                        StringSetTransfer.start558=true; 
                     }                    
                     boolean done = true;
                     int i=558;
                     for (i=558; i<=560; i++){
                        if (!hs_fdb.contains(new Integer(i))){
                           id = i;
                           if (i<560)
                              done = false;
                           break;
                        }
                     }
                     StringSetTransfer.done558 = done;
                  }

                  if (id>=577 && id<=578){
                     if (StringSetTransfer.start577==false){
                        StringSetTransfer.start577=true; 
                     }                    
                     boolean done = true;
                     int i=577;
                     for (i=577; i<=578; i++){
                        if (!hs_fdb.contains(new Integer(i))){
                           id = i;
                           if (i<578)
                              done = false;
                           break;
                        }
                     }
                     StringSetTransfer.done577 = done;
                  }  

                  if (id>=582 && id<=583){
                     if (StringSetTransfer.start582==false){
                        StringSetTransfer.start582=true; 
                     }                    
                     boolean done = true;
                     int i=582;
                     for (i=582; i<=583; i++){
                        if (!hs_fdb.contains(new Integer(i))){
                           id = i;
                           if (i<583)
                              done = false;
                           break;
                        }
                     }
                     StringSetTransfer.done582 = done;
                  }
 
                  if (id>=623 && id<=624){
                     if (StringSetTransfer.start623==false){
                        StringSetTransfer.start623=true; 
                     }                    
                     boolean done = true;
                     int i=623;
                     for (i=623; i<=624; i++){
                        if (!hs_fdb.contains(new Integer(i))){
                           id = i;
                           if (i<624)
                              done = false;
                           break;
                        }
                     }
                     StringSetTransfer.done623 = done;
                  }

                  if (id>=625 && id<=628){
                     if (StringSetTransfer.start625==false){
                        StringSetTransfer.start625=true; 
                     }                    
                     boolean done = true;
                     int i=625;
                     for (i=625; i<=628; i++){
                        if (!hs_fdb.contains(new Integer(i))){
                           id = i;
                           if (i<628)
                              done = false;
                           break;
                        }
                     }
                     StringSetTransfer.done625 = done;
                  }

                  if (id>=638 && id<=644){
                     if (StringSetTransfer.start638==false){
                        StringSetTransfer.start638=true; 
                     }                    
                     boolean done = true;
                     int i=638;
                     for (i=638; i<=644; i++){
                        if (!hs_fdb.contains(new Integer(i))){
                           id = i;
                           if (i<644)
                              done = false;
                           break;
                        }
                     }
                     StringSetTransfer.done638 = done;
                  }

                  if (id>=645 && id<=651){
                     if (StringSetTransfer.start645==false){
                        StringSetTransfer.start645=true; 
                     }                    
                     boolean done = true;
                     int i=645;
                     for (i=645; i<=651; i++){
                        if (!hs_fdb.contains(new Integer(i))){
                           id = i;
                           if (i<651)
                              done = false;
                           break;
                        }
                     }
                     StringSetTransfer.done645 = done;
                  }

                  if (id>=746 && id<=747){
                     if (StringSetTransfer.start746==false){
                        StringSetTransfer.start746=true; 
                     }                    
                     boolean done = true;
                     int i=746;
                     for (i=746; i<=747; i++){
                        if (!hs_fdb.contains(new Integer(i))){
                           id = i;
                           if (i<747)
                              done = false;
                           break;
                        }
                     }
                     StringSetTransfer.done746 = done;
                  }

                  if (id>=748 && id<=749){
                     if (StringSetTransfer.start748==false){
                        StringSetTransfer.start748=true; 
                     }                    
                     boolean done = true;
                     int i=748;
                     for (i=748; i<=749; i++){
                        if (!hs_fdb.contains(new Integer(i))){
                           id = i;
                           if (i<749)
                              done = false;
                           break;
                        }
                     }
                     StringSetTransfer.done748 = done;
                  } 

                  if (id>=750 && id<=751){
                     if (StringSetTransfer.start750==false){
                        StringSetTransfer.start750=true; 
                     }                    
                     boolean done = true;
                     int i=750;
                     for (i=750; i<=751; i++){
                        if (!hs_fdb.contains(new Integer(i))){
                           id = i;
                           if (i<751)
                              done = false;
                           break;
                        }
                     }
                     StringSetTransfer.done750 = done;
                  }

                  if (id>=752 && id<=755){
                     if (StringSetTransfer.start752==false){
                        StringSetTransfer.start752=true; 
                     }                    
                     boolean done = true;
                     int i=752;
                     for (i=752; i<=755; i++){
                        if (!hs_fdb.contains(new Integer(i))){
                           id = i;
                           if (i<755)
                              done = false;
                           break;
                        }
                     }
                     StringSetTransfer.done752 = done;
                  }

                  if (id>=810 && id<=812){
                     if (StringSetTransfer.start810==false){
                        StringSetTransfer.start810=true; 
                     }                    
                     boolean done = true;
                     int i=810;
                     for (i=810; i<=812; i++){
                        if (!hs_fdb.contains(new Integer(i))){
                           id = i;
                           if (i<812)
                              done = false;
                           break;
                        }
                     }
                     StringSetTransfer.done810 = done;
                  }

                  if (id>=858 && id<=859){
                     if (StringSetTransfer.start858==false){
                        StringSetTransfer.start858=true; 
                     }                    
                     boolean done = true;
                     int i=858;
                     for (i=858; i<=859; i++){
                        if (!hs_fdb.contains(new Integer(i))){
                           id = i;
                           if (i<859)
                              done = false;
                           break;
                        }
                     }
                     StringSetTransfer.done858 = done;
                  }                  

                  hs_fdb.add(new Integer(id)); 
                  stmt = myConnection.prepareStatement("select * from fillDoubleBlanks2_test where id=?");
                  stmt.clearParameters();
                  stmt.setInt(1, id);
                  rs = stmt.executeQuery();
                  while (rs.next()){
                     fdbb.setId(rs.getInt("id"));
                     fdbb.setStatement_1st(rs.getString("statement_1st"));
                     fdbb.setStatement_2nd(rs.getString("statement_2nd"));
                     fdbb.setStatement_3rd(rs.getString("statement_3rd"));
                     fdbb.setSolution_1st(rs.getString("solution_1st"));
                     fdbb.setSolution_2nd(rs.getString("solution_2nd"));
                     //String source = rs.getString("source");
                     fdbb.setType(rs.getString("type"));
                  }
                  //getServlet().log("at initiation in SmartRandomActioin: fdbb is reached");
                  break;
               case 5:
                  //hs_tm.add(new Integer(id)); 
                  stmt = myConnection.prepareStatement("select * from termMatch_test where id=?");
                  stmt.clearParameters();
                  stmt.setInt(1, id);
                  rs = stmt.executeQuery();
                  while (rs.next()){
                     tmb.setId(rs.getInt("id"));
                     tmb.setTerm1(rs.getString("term1"));
                     tmb.setTerm2(rs.getString("term2"));
                     tmb.setType(rs.getString("type"));                     
                     //tmb.setSource(rs.getString("source"));                     
                  }
                  break;   
               case 6:
                  hs_m.add(new Integer(id)); 
                  stmt = myConnection.prepareStatement("select * from multiply_test where id=?");
                  stmt.clearParameters();
                  stmt.setInt(1, id);
                  rs = stmt.executeQuery();
                  while (rs.next()){
                     mb.setId(rs.getInt("id"));
                     mb.setFactor1(rs.getString("factor1"));
                     mb.setFactor2(rs.getString("factor2"));
                     mb.setProcess1(rs.getString("process1"));  
                     mb.setProcess2(rs.getString("process2")); 
                     mb.setResult(rs.getString("result")); 
                     mb.setP1Solution(rs.getString("solution1"));  
                     mb.setP2Solution(rs.getString("solution2"));  
                     mb.setRSolution(rs.getString("rSolution"));
                     mb.setResultSolution(rs.getString("rSolution"));   
                     mb.setType(rs.getString("type"));                     
                     //mb.setSource(rs.getString("source"));                     
                  }
                  break;     
               case 7:
                  hs_d.add(new Integer(id)); 
                  stmt = myConnection.prepareStatement("select * from division_test where id=?");
                  stmt.clearParameters();
                  stmt.setInt(1, id);
                  rs = stmt.executeQuery();
                  while (rs.next()){
                     db.setId(rs.getInt("id"));
                     db.setDivided(rs.getString("divided"));
                     db.setDivider(rs.getString("divider"));
                     db.setResult(rs.getString("result"));
                     db.setProduct1(rs.getString("product1"));
                     db.setRemain1(rs.getString("remain1"));
                     db.setProduct2(rs.getString("product2"));
                     db.setRemain2(rs.getString("remain2"));  
                     db.setProduct3(rs.getString("product3"));
                     db.setRemain3(rs.getString("remain3"));
                     db.setLevel(rs.getInt("level"));   
                     db.setType(rs.getString("type"));                     
                     //mb.setSource(rs.getString("source"));                     
                  }
                  break; 
               case 8:
                  hs_c2.add(new Integer(id)); 
                  stmt = myConnection.prepareStatement("select * from composition_test where id=?");
                  stmt.clearParameters();
                  stmt.setInt(1, id);
                  rs = stmt.executeQuery();
                  while (rs.next()){
                     c2b.setId(rs.getInt("id"));
                     c2b.setStatement(rs.getString("statement"));
                     c2b.setAnswerSentences(rs.getString("answerSentences"));
                     c2b.setAnswerWords(rs.getString("answerWords"));
                     c2b.setType(rs.getString("type"));                     
                     c2b.setFullScore(rs.getInt("fullScore"));                     
                  }
                  break;          
               default:
                  break;
           }//end of switch(randomNumber)                       
        } catch (SQLException sqle) {
            getServlet().log("Connection.process", sqle);
        } finally {
            //enclose this in a finally block to make
            //sure the connection is closed
            try {
               if (rs != null) rs.close();             
               if (stmt != null) stmt.close();
               if (myConnection != null) myConnection.close();
            } catch (SQLException e) {
               getServlet().log("Connection.close", e);
            }
        }//end of try-catch-finally block 

        pb2.setCurrentProblemId(id);

     }else {//id==0 and planStatus.equals("finished")
        //do nothing
     }

    pb2.setCurrentProblemId(id);
    System.out.println("Inside SmartRandomAction, after id and randomNumber is obtained and after if(id!=0) and try block, id = " + id + " and randomNumber = " + randomNumber);
      
        switch (randomNumber){
            case 0:
               request.setAttribute( Constants.PROBLEM_KEY, pb);        
               session.setAttribute( Constants.PROBLEM_KEY, pb); 
               break;
            case 1:
               request.setAttribute( Constants.CHOICE_KEY, cb);        
               session.setAttribute( Constants.CHOICE_KEY, cb);   
               break;
            case 2:
               request.setAttribute( Constants.MULTIPLEPROBLEM_KEY, mpb);        
               session.setAttribute( Constants.MULTIPLEPROBLEM_KEY, mpb); 
               break;
            case 3:
               request.setAttribute( Constants.FILLBLANK_KEY, fbb);        
               session.setAttribute( Constants.FILLBLANK_KEY, fbb); 
               break;
            case 4:
               request.setAttribute( Constants.FILLDOUBLEBLANK_KEY, fdbb);        
               session.setAttribute( Constants.FILLDOUBLEBLANK_KEY, fdbb); 
               break;
            case 5:
               request.setAttribute( Constants.TERMMATCH_KEY, tmb);        
               session.setAttribute( Constants.TERMMATCH_KEY, tmb); 
               break;
            case 6:
               request.setAttribute( Constants.MULTIPLY_KEY, mb);        
               session.setAttribute( Constants.MULTIPLY_KEY, mb); 
               break;
            case 7:
               request.setAttribute( Constants.DIVISION_KEY, db);        
               session.setAttribute( Constants.DIVISION_KEY, db); 
               break;
            case 8:
               request.setAttribute( Constants.COMPOSITION_KEY, c2b);        
               session.setAttribute( Constants.COMPOSITION_KEY, c2b); 
               break;
            default:
               break;
        }          
        
        session.setAttribute( Constants.HASHSET_KEY, StringSetTransfer.setToString(hs));   
        session.setAttribute( Constants.HASHSET_TF_KEY, StringSetTransfer.setToString(hs_tf));
        session.setAttribute( Constants.HASHSET_M_KEY, StringSetTransfer.setToString(mhs)); 
        session.setAttribute( Constants.HASHSET_FB_KEY, StringSetTransfer.setToString(hs_fb));
        session.setAttribute( Constants.HASHSET_FDB_KEY, StringSetTransfer.setToString(hs_fdb));
        session.setAttribute( Constants.HASHSET_TM_KEY, StringSetTransfer.setToString(hs_tm));
        session.setAttribute( Constants.HASHSET_MY_KEY, StringSetTransfer.setToString(hs_m));
        session.setAttribute( Constants.HASHSET_D_KEY, StringSetTransfer.setToString(hs_d));
        session.setAttribute( Constants.HASHSET_C2_KEY, StringSetTransfer.setToString(hs_c2)); 

        request.setAttribute( Constants.HASHSET_KEY, StringSetTransfer.setToString(hs));   
        request.setAttribute( Constants.HASHSET_TF_KEY, StringSetTransfer.setToString(hs_tf));
        request.setAttribute( Constants.HASHSET_M_KEY, StringSetTransfer.setToString(mhs)); 
        request.setAttribute( Constants.HASHSET_FB_KEY, StringSetTransfer.setToString(hs_fb)); 
        request.setAttribute( Constants.HASHSET_FDB_KEY, StringSetTransfer.setToString(hs_fdb)); 
        request.setAttribute( Constants.HASHSET_TM_KEY, StringSetTransfer.setToString(hs_tm));
        request.setAttribute( Constants.HASHSET_MY_KEY, StringSetTransfer.setToString(hs_m)); 
        request.setAttribute( Constants.HASHSET_D_KEY, StringSetTransfer.setToString(hs_d));
        request.setAttribute( Constants.HASHSET_C2_KEY, StringSetTransfer.setToString(hs_c2));
        /*
        session.setAttribute( Constants.HASHSET_LOW_KEY, StringSetTransfer.setToString(stringSetTransfer.hs_low));
        session.setAttribute( Constants.HASHSET_MIDDLE_KEY, StringSetTransfer.setToString(stringSetTransfer.hs_middle));
        session.setAttribute( Constants.HASHSET_HIGH_KEY, StringSetTransfer.setToString(stringSetTransfer.hs_high));
        request.setAttribute( Constants.HASHSET_LOW_KEY, StringSetTransfer.setToString(stringSetTransfer.hs_low));
        request.setAttribute( Constants.HASHSET_MIDDLE_KEY, StringSetTransfer.setToString(stringSetTransfer.hs_middle));
        request.setAttribute( Constants.HASHSET_HIGH_KEY, StringSetTransfer.setToString(stringSetTransfer.hs_high));

      session.setAttribute( Constants.HASHSET_LOW_TF_KEY, StringSetTransfer.setToString(stringSetTransfer.hs_low_tf));
      session.setAttribute( Constants.HASHSET_MIDDLE_TF_KEY, StringSetTransfer.setToString(stringSetTransfer.hs_middle_tf));
      session.setAttribute( Constants.HASHSET_HIGH_TF_KEY, StringSetTransfer.setToString(stringSetTransfer.hs_high_tf));
      request.setAttribute( Constants.HASHSET_LOW_TF_KEY, StringSetTransfer.setToString(stringSetTransfer.hs_low_tf));
      request.setAttribute( Constants.HASHSET_MIDDLE_TF_KEY, StringSetTransfer.setToString(stringSetTransfer.hs_middle_tf));
      request.setAttribute( Constants.HASHSET_HIGH_TF_KEY, StringSetTransfer.setToString(stringSetTransfer.hs_high_tf));

      session.setAttribute( Constants.HASHSET_LOW_FB_KEY, StringSetTransfer.setToString(stringSetTransfer.hs_low_fb));
      session.setAttribute( Constants.HASHSET_MIDDLE_FB_KEY, StringSetTransfer.setToString(stringSetTransfer.hs_middle_fb));
      session.setAttribute( Constants.HASHSET_HIGH_FB_KEY, StringSetTransfer.setToString(stringSetTransfer.hs_high_fb));
      request.setAttribute( Constants.HASHSET_LOW_FB_KEY, StringSetTransfer.setToString(stringSetTransfer.hs_low_fb));
      request.setAttribute( Constants.HASHSET_MIDDLE_FB_KEY, StringSetTransfer.setToString(stringSetTransfer.hs_middle_fb));
      request.setAttribute( Constants.HASHSET_HIGH_FB_KEY, StringSetTransfer.setToString(stringSetTransfer.hs_high_fb));
 
    session.setAttribute( Constants.HASHSET_LOW_FDB_KEY, StringSetTransfer.setToString(stringSetTransfer.hs_low_fdb));
    session.setAttribute( Constants.HASHSET_MIDDLE_FDB_KEY, StringSetTransfer.setToString(stringSetTransfer.hs_middle_fdb));
    session.setAttribute( Constants.HASHSET_HIGH_FDB_KEY, StringSetTransfer.setToString(stringSetTransfer.hs_high_fdb));
    request.setAttribute( Constants.HASHSET_LOW_FDB_KEY, StringSetTransfer.setToString(stringSetTransfer.hs_low_fdb));
    request.setAttribute( Constants.HASHSET_MIDDLE_FDB_KEY, StringSetTransfer.setToString(stringSetTransfer.hs_middle_fdb));
    request.setAttribute( Constants.HASHSET_HIGH_FDB_KEY, StringSetTransfer.setToString(stringSetTransfer.hs_high_fdb));

      session.setAttribute( Constants.HASHSET_LOW_M_KEY, StringSetTransfer.setToString(stringSetTransfer.mhs_low));
      session.setAttribute( Constants.HASHSET_MIDDLE_M_KEY, StringSetTransfer.setToString(stringSetTransfer.mhs_middle));
      session.setAttribute( Constants.HASHSET_HIGH_M_KEY, StringSetTransfer.setToString(stringSetTransfer.mhs_high));
      request.setAttribute( Constants.HASHSET_LOW_M_KEY, StringSetTransfer.setToString(stringSetTransfer.mhs_low));
      request.setAttribute( Constants.HASHSET_MIDDLE_M_KEY, StringSetTransfer.setToString(stringSetTransfer.mhs_middle));
      request.setAttribute( Constants.HASHSET_HIGH_M_KEY, StringSetTransfer.setToString(stringSetTransfer.mhs_high));
      */
        System.out.println("at SmartRandomAction, hs string is " + StringSetTransfer.setToString(hs));
        System.out.println("SmartRandomAction, hs_tf str is " + StringSetTransfer.setToString(hs_tf));
        System.out.println("SmartRandomAction, mhs str is " + StringSetTransfer.setToString(mhs));   
        System.out.println("SmartRandomAction, hs_fb str is " + StringSetTransfer.setToString(hs_fb));   
        System.out.println("SmartRandomAction, hs_fdb str is " + StringSetTransfer.setToString(hs_fdb));
        System.out.println("SmartRandomAction, hs_tm str is " + StringSetTransfer.setToString(hs_tm));   
        System.out.println("SmartRandomAction, hs_m str is " + StringSetTransfer.setToString(hs_m));      
        System.out.println("SmartRandomAction, hs_d str is " + StringSetTransfer.setToString(hs_d));
        System.out.println("SmartRandomAction, hs_c2 str is " + StringSetTransfer.setToString(hs_c2));                    
        
        String answeredProblems = (String)((SmartRandomForm) form).getAnsweredProblems();
        String correctAnswers = (String)((SmartRandomForm) form).getCorrectAnswers();
        int intAnsweredProblems = Integer.parseInt(answeredProblems);
        int intCorrectAnswers = Integer.parseInt(correctAnswers);           
        pb2.setAnsweredProblems(intAnsweredProblems);
        pb2.setCorrectAnswers(intCorrectAnswers); 

        String totalScore = (String)((SmartRandomForm) form).getTotalScore();
        String correctAnswers_low = (String)((SmartRandomForm) form).getCorrectAnswers_low();
        String answeredProblems_low = (String)((SmartRandomForm) form).getAnsweredProblems_low();
        double dbTotalScore = Double.parseDouble(totalScore);
        int intCorrectAnswers_low = Integer.parseInt(correctAnswers_low);
        int intAnsweredProblems_low = Integer.parseInt(answeredProblems_low);                      
        pb2.setTotalScore(dbTotalScore);
        pb2.setCorrectAnswers_low(intCorrectAnswers_low);
        pb2.setAnsweredProblems_low(intAnsweredProblems_low);   

        String correctAnswers_middle = (String)((SmartRandomForm) form).getCorrectAnswers_middle();
        String answeredProblems_middle = (String)((SmartRandomForm) form).getAnsweredProblems_middle();        
        int intCorrectAnswers_middle = Integer.parseInt(correctAnswers_middle);
        int intAnsweredProblems_middle = Integer.parseInt(answeredProblems_middle);       
        pb2.setCorrectAnswers_middle(intCorrectAnswers_middle);
        pb2.setAnsweredProblems_middle(intAnsweredProblems_middle); 

        String correctAnswers_high = (String)((SmartRandomForm) form).getCorrectAnswers_high();
        String answeredProblems_high = (String)((SmartRandomForm) form).getAnsweredProblems_high();        
        int intCorrectAnswers_high = Integer.parseInt(correctAnswers_high);
        int intAnsweredProblems_high = Integer.parseInt(answeredProblems_high);       
        pb2.setCorrectAnswers_high(intCorrectAnswers_high);
        pb2.setAnsweredProblems_high(intAnsweredProblems_high);         

        String times = (String)((SmartRandomForm) form).getTimes();
        pb2.setTimes(times);
        pb2.setRandomNumber(new Integer(randomNumber).toString());          

        //now we are going to set up the timer
        if (first.equals("true")){
           if (StringSetTransfer.myTask != null) StringSetTransfer.myTask.cancel();
           if (StringSetTransfer.aTimer != null) StringSetTransfer.aTimer.cancel();
           StringSetTransfer.aTimer = new Timer(userName);
           StringSetTransfer.myTask = new MyTask(userName);           
           StringSetTransfer.aTimer.scheduleAtFixedRate(StringSetTransfer.myTask, 0, 30000);
        }          

        //getServlet().log("before forward to Pair2000Zero");
        /*
        session.setAttribute( Constants.PERSON_KEY, pb2); 
        request.setAttribute( Constants.PERSON_KEY, pb2); 

        if (userName.equals("2000")){
           return (mapping.findForward("Show2000Zero"));
        }    
        */

        //now get the remainSeconds and update planStatus
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
                

           if (pb2.getPlanStatus().equals("finished")){
              s = myConnection.createStatement();
              s.executeUpdate("update student_score set planStatus='finished' where studentNo = '" + userName + "'");
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
     
        session.setAttribute( Constants.PERSON_KEY, pb2); 
        request.setAttribute( Constants.PERSON_KEY, pb2);  

        // Forward control to the specified success URI
        switch (randomNumber){
            case 0:
               return (mapping.findForward("ShowSmartProblem"));               
            case 1:
               return (mapping.findForward("ShowSmartChoice")); 
            case 2:
               return (mapping.findForward("ShowSmartMultipleProblem"));   
            case 3:
               return (mapping.findForward("ShowFillBlank"));   
            case 4:
               return (mapping.findForward("ShowFillDoubleBlank"));
            case 5:
               return (mapping.findForward("ShowTermMatch")); 
            case 6:
               return (mapping.findForward("ShowMultiply"));     
            case 7:
               return (mapping.findForward("ShowDivision")); 
            case 8:
               return (mapping.findForward("ShowComposition"));                           
            default:
               break;
        }          
        return (mapping.findForward("ShowSmartProblem"));        
    }
}

