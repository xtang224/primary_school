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

public final class SmartRandomAllAction extends Action {

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
        String first = null;
        first = (String)request.getParameter("first"); 

        PersonBean pb2 = null;
        pb2 = new PersonBean();
        String userName = (String)((SmartRandomAllForm) form).getUserName();
        String passWord = (String)((SmartRandomAllForm) form).getPassWord();
        String trueName = (String)((SmartRandomAllForm) form).getTrueName();
        String playerId = (String)((SmartRandomAllForm) form).getPlayerId();
        pb2.setUserName(userName);
        pb2.setPassWord(passWord); 
        pb2.setTrueName(trueName);
        pb2.setPlayerId(playerId);  

        String planStatus = (String)((SmartRandomAllForm) form).getPlanStatus();
        pb2.setPlanStatus(planStatus);

        String source = (String)((SmartRandomAllForm) form).getSource();
        if (source.startsWith("random"))
           System.out.println("Inside SmartRandomAllAction, source starts with random!");
        else
           System.out.println("Inside SmartRandomAllAction, something is wrong because source does not start with random!");
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
        pb.setSource(source);
        cb.setSource(source);
        mpb.setSource(source);
        fbb.setSource(source);
        fdbb.setSource(source);
        tmb.setSource(source);
        mb.setSource(source);
        db.setSource(source);

        HashSet hs = null;
        HashSet hs_tf = null;
        HashSet mhs = null;
        HashSet hs_fb = null;
        HashSet hs_fdb = null;
        HashSet hs_tm = null;
        HashSet hs_m = null;
        HashSet hs_d = null;
        
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

            HashSet hs_low_used = new HashSet();
            HashSet hs_low_tf_used = new HashSet();
            HashSet hs_middle_fb_used = new HashSet(); 
            HashSet mhs_high_used = new HashSet();
            HashSet hs_high_fdb_used = new HashSet();
            HashSet hs_middle_tm_used = new HashSet();
            HashSet hs_high_m_used = new HashSet();
            HashSet hs_high_d_used = new HashSet();

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
                     /*                    
                     stmt = myConnection.prepareStatement("select id from usedQuestions2_test where studentNo=? and source=?");
                     stmt.clearParameters();
                     stmt.setString(1, userName);
                     stmt.setString(2, source);
                     rs = stmt.executeQuery();      
                     while (rs.next()){
                        int usedId = rs.getInt("id");
                        hs_low_used.add(new Integer(usedId));
                     }
                     */                     
                     //rs = s.executeQuery("select id from questions2_test where type='L' and source='" + source + "'");  

                     StringSetTransfer.hs_low_used = hs_low_used;      
                     rs = s.executeQuery("select id from questions2_test where type='L'");
                     while (rs.next()){
                        int usedId = rs.getInt("id");
                        hs_low.add(new Integer(usedId));
                     }
                     hs_low.removeAll(hs_low_used);
                     //now we want to only get 3 questions for this random test
                     HashSet tmpSet = new HashSet();
                     HashSet controlSet = new HashSet();
                     Object[] obs = hs_low.toArray();
                     for (int i=0; i<3; i++){
                         int intRandom = (int)(Math.random() * hs_low.size());
                         while(controlSet.contains(new Integer(intRandom)))
                            intRandom = (int)(Math.random() * hs_low.size());
                         controlSet.add(new Integer(intRandom));
                         int intSelect = ((Integer)obs[intRandom]).intValue();
                         tmpSet.add(new Integer(intSelect));
                     }
                     hs_low.clear();
                     hs_low.addAll(tmpSet);
                     StringSetTransfer.hs_low = hs_low; 
                     
                     //now we want to initiate StringSetTransfer.hs_low_tf_used,hs_low_tf,hs_middle_tf,hs_high_tf,total_tf   
                     /*                
                     stmt = myConnection.prepareStatement("select id from usedChoices2_test where studentNo=? and source=?");
                     stmt.clearParameters();
                     stmt.setString(1, userName);
                     stmt.setString(2, source);
                     rs = stmt.executeQuery();
                     while (rs.next()){
                        int usedId = rs.getInt("id");
                        hs_low_tf_used.add(new Integer(usedId));
                     }
                     */
                     StringSetTransfer.hs_low_tf_used = hs_low_tf_used;
                     rs = s.executeQuery("select id from choices2_test where type='L'");     
                     while (rs.next()){
                        int usedId = rs.getInt("id");
                        hs_low_tf.add(new Integer(usedId));
                     }
                     hs_low_tf.removeAll(hs_low_tf_used);
                     //now we want to get 3 choices for the random test
                     tmpSet.clear();
                     controlSet.clear();
                     obs = hs_low_tf.toArray();
                     for (int i=0; i<3; i++){
                         int intRandom = (int)(Math.random() * hs_low_tf.size());
                         while(controlSet.contains(new Integer(intRandom)))
                            intRandom = (int)(Math.random() * hs_low_tf.size());
                         controlSet.add(new Integer(intRandom));
                         int intSelect = ((Integer)obs[intRandom]).intValue();
                         tmpSet.add(new Integer(intSelect));
                     }
                     hs_low_tf.clear();
                     hs_low_tf.addAll(tmpSet);
                     StringSetTransfer.hs_low_tf = hs_low_tf;

                     //now we want to initiate StringSetTransfer.mhs_low,mhs_middle,mhs_high_used,mhs_high,mtotal  
                     /*                   
                     stmt = myConnection.prepareStatement("select id from usedMultipleQuestions2_test where studentNo=? and source=?");
                     stmt.clearParameters();
                     stmt.setString(1, userName);
                     stmt.setString(2, source);
                     rs = stmt.executeQuery();
                     while (rs.next()){
                        int usedId = rs.getInt("id");
                        mhs_high_used.add(new Integer(usedId));
                     }
                     */  
                     StringSetTransfer.mhs_high_used = mhs_high_used;
                     rs = s.executeQuery("select id from multipleQuestions2_test where type='H'");
                     while (rs.next()){
                        int usedId = rs.getInt("id");
                        mhs_high.add(new Integer(usedId));
                     }
                     mhs_high.removeAll(mhs_high_used); 
                     //now we want to get 3 multipleQuestions for the random test because there are only 3 multipleQuestions in multipleQuestions2_test
                     tmpSet.clear();
                     controlSet.clear();
                     obs = mhs_high.toArray();
                     for (int i=0; i<3; i++){
                         int intRandom = (int)(Math.random() * mhs_high.size());
                         while(controlSet.contains(new Integer(intRandom)))
                            intRandom = (int)(Math.random() * mhs_high.size());
                         controlSet.add(new Integer(intRandom));
                         int intSelect = ((Integer)obs[intRandom]).intValue();
                         tmpSet.add(new Integer(intSelect));
                     }
                     mhs_high.clear();
                     mhs_high.addAll(tmpSet);                    
                     StringSetTransfer.mhs_high = mhs_high;
                     
                    //now we want to initiate StringSetTransfer.hs_low_fb,hs_middle_fb_used,hs_middle_fb,hs_high_fb,total_fb     
                     /*               
                     stmt = myConnection.prepareStatement("select id from usedFillBlank2_test where studentNo=? and source=?");
                     stmt.clearParameters();
                     stmt.setString(1, userName);
                     stmt.setString(2, source);
                     rs = stmt.executeQuery();
                     while (rs.next()){
                        int usedId = rs.getInt("id");
                        hs_middle_fb_used.add(new Integer(usedId));
                     }
                     */
                     StringSetTransfer.hs_middle_fb_used = hs_middle_fb_used;                      
                     rs = s.executeQuery("select id from fillBlank2_test where type='M'");
                     while (rs.next()){
                        int usedId = rs.getInt("id");
                        hs_middle_fb.add(new Integer(usedId));
                     }
                     hs_middle_fb.removeAll(hs_middle_fb_used);
                     //now we want to get 3 fillBlank2 for the random test
                     tmpSet.clear();
                     controlSet.clear();
                     obs = hs_middle_fb.toArray();
                     for (int i=0; i<3; i++){
                         int intRandom = (int)(Math.random() * hs_middle_fb.size());
                         while(controlSet.contains(new Integer(intRandom)))
                            intRandom = (int)(Math.random() * hs_middle_fb.size());
                         controlSet.add(new Integer(intRandom));
                         int intSelect = ((Integer)obs[intRandom]).intValue();
                         tmpSet.add(new Integer(intSelect));
                     }
                     hs_middle_fb.clear();
                     hs_middle_fb.addAll(tmpSet);  
                     StringSetTransfer.hs_middle_fb = hs_middle_fb;
                     
                     //now we want to initiate StringSetTransfer.hs_low_fdb,hs_middle_fdb,hs_high_fdb_used,hs_high_fdb,total_fdb   
                     /*                 
                     stmt = myConnection.prepareStatement("select id from usedFillDoubleBlanks2_test where studentNo=? and source=?");
                     stmt.clearParameters();
                     stmt.setString(1, userName);
                     stmt.setString(2, source);
                     rs = stmt.executeQuery();                    
                     while (rs.next()){
                        int usedId = rs.getInt("id");
                        hs_high_fdb_used.add(new Integer(usedId));
                     }
                     */
                     StringSetTransfer.hs_high_fdb_used = hs_high_fdb_used;                     
                     rs = s.executeQuery("select id from fillDoubleBlanks2_test where type='H'");
                     while (rs.next()){
                        int usedId = rs.getInt("id");
                        hs_high_fdb.add(new Integer(usedId));
                     }
                     hs_high_fdb.removeAll(hs_high_fdb_used);
                     //now we want to get 3 fillDoubleBlanks2 for the random test
                     tmpSet.clear();
                     controlSet.clear();
                     obs = hs_high_fdb.toArray();
                     for (int i=0; i<3; i++){
                         int intRandom = (int)(Math.random() * hs_high_fdb.size());
                         while(controlSet.contains(new Integer(intRandom)))
                            intRandom = (int)(Math.random() * hs_high_fdb.size());
                         controlSet.add(new Integer(intRandom));
                         int intSelect = ((Integer)obs[intRandom]).intValue();
                         tmpSet.add(new Integer(intSelect));
                     }
                     hs_high_fdb.clear();
                     hs_high_fdb.addAll(tmpSet); 
                     StringSetTransfer.hs_high_fdb = hs_high_fdb;
                     
                     //now we want to initiate StringSetTransfer.hs_middle_tm, hs_middle_tm_used, total_tm    
                     /*                 
                     stmt = myConnection.prepareStatement("select id from usedTermMatch_test where studentNo=? and source=?");
                     stmt.clearParameters();
                     stmt.setString(1, userName);
                     stmt.setString(2, source);
                     rs = stmt.executeQuery();                     
                     while (rs.next()){
                        int usedId = rs.getInt("id");
                        hs_middle_tm_used.add(new Integer(usedId));
                     }
                     */
                     StringSetTransfer.hs_middle_tm_used = hs_middle_tm_used;
                     rs = s.executeQuery("select id from termMatch_test where type='M'");
                     while (rs.next()){
                        int usedId = rs.getInt("id");
                        hs_middle_tm.add(new Integer(usedId));
                     }
                     hs_middle_tm.removeAll(hs_middle_tm_used);
                     //now we want to get 5 termMatch for the random test
                     tmpSet.clear();
                     controlSet.clear();
                     obs = hs_middle_tm.toArray();
                     for (int i=0; i<5; i++){
                         int intRandom = (int)(Math.random() * hs_middle_tm.size());
                         while(controlSet.contains(new Integer(intRandom)))
                            intRandom = (int)(Math.random() * hs_middle_tm.size());
                         controlSet.add(new Integer(intRandom));
                         int intSelect = ((Integer)obs[intRandom]).intValue();
                         tmpSet.add(new Integer(intSelect));
                     }
                     hs_middle_tm.clear();
                     hs_middle_tm.addAll(tmpSet); 
                     StringSetTransfer.hs_middle_tm = hs_middle_tm;

                     //now we want to initiate StringSetTransfer.hs_high_m, hs_high_m_used, total_m    
                     /*                 
                     stmt = myConnection.prepareStatement("select id from usedMultiply_test where studentNo=? and source=?");
                     stmt.clearParameters();
                     stmt.setString(1, userName);
                     stmt.setString(2, source);
                     rs = stmt.executeQuery();                     
                     while (rs.next()){
                        int usedId = rs.getInt("id");
                        hs_high_m_used.add(new Integer(usedId));
                     }
                     */
                     StringSetTransfer.hs_high_m_used = hs_high_m_used;
                     rs = s.executeQuery("select id from multiply_test where type='H'");
                     while (rs.next()){
                        int usedId = rs.getInt("id");
                        hs_high_m.add(new Integer(usedId));
                     }
                     hs_high_m.removeAll(hs_high_m_used);
                     //now we want to get 3 multiply for the random test
                     tmpSet.clear();
                     controlSet.clear();
                     obs = hs_high_m.toArray();
                     for (int i=0; i<3; i++){
                         int intRandom = (int)(Math.random() * hs_high_m.size());
                         while(controlSet.contains(new Integer(intRandom)))
                            intRandom = (int)(Math.random() * hs_high_m.size());
                         controlSet.add(new Integer(intRandom));
                         int intSelect = ((Integer)obs[intRandom]).intValue();
                         tmpSet.add(new Integer(intSelect));
                     }
                     hs_high_m.clear();
                     hs_high_m.addAll(tmpSet); 
                     StringSetTransfer.hs_high_m = hs_high_m;

                     StringSetTransfer.hs_high_d_used = hs_high_d_used;
                     StringSetTransfer.hs_high_d = hs_high_d;

               //System.out.println("the total number of questions: " + (StringSetTransfer.total_fdb+StringSetTransfer.total_fb+StringSetTransfer.mtotal+StringSetTransfer.total_tf+StringSetTransfer.total) + "");
               System.out.println("among which, the total number of type low questions: " + (StringSetTransfer.hs_low.size()+StringSetTransfer.hs_low_tf.size()));
               System.out.println("among which, the total number of type middle questions: " + (StringSetTransfer.hs_middle_fb.size()+StringSetTransfer.hs_middle_tm.size()) + "");     
               System.out.println("among which, the total number of type middle questions of termMatch: " + StringSetTransfer.hs_middle_tm.size()); 
               System.out.println("among which, the total number of type high questions: " + (StringSetTransfer.hs_high_fdb.size()+StringSetTransfer.mhs_high.size()+StringSetTransfer.hs_high_m.size()) + "");          

                     s = myConnection.createStatement();
                     s.executeUpdate("update student_score set status = 'initiated' where studentNo='" + userName + "' ");

                     //Now we want to initiate the score of different problems for different types
                     //stmt = myConnection.prepareStatement("select id, score from termMatch_test_score");
                     //stmt.clearParameters();                    
                     //rs = stmt.executeQuery(); 
                     s = myConnection.createStatement();
                     rs = s.executeQuery("select id, score from termMatch_test_score");                    
                     while (rs.next()){
                        int tempId = rs.getInt("id");
                        int tempScore = rs.getInt("score");
                        StringSetTransfer.hm_tm.put(new Integer(tempId), new Integer(tempScore));
                     }
                     
                     s = myConnection.createStatement();
                     rs = s.executeQuery("select id, score from fillBlank2_test_score");                      
                     while (rs.next()){
                        int tempId = rs.getInt("id");
                        int tempScore = rs.getInt("score");
                        StringSetTransfer.hm_fb.put(new Integer(tempId), new Integer(tempScore));
                     }
                     
                     s = myConnection.createStatement();
                     rs = s.executeQuery("select id, score from fillDoubleBlanks2_test_score");                       
                     while (rs.next()){
                        int tempId = rs.getInt("id");
                        int tempScore = rs.getInt("score");
                        StringSetTransfer.hm_fdb.put(new Integer(tempId), new Integer(tempScore));
                     }
                     
                     s = myConnection.createStatement();
                     rs = s.executeQuery("select id, score from questions2_test_score");                      
                     while (rs.next()){
                        int tempId = rs.getInt("id");
                        int tempScore = rs.getInt("score");
                        StringSetTransfer.hm_q.put(new Integer(tempId), new Integer(tempScore));
                     }
                     
                     s = myConnection.createStatement();
                     rs = s.executeQuery("select id, score from multiplequestions2_test_score");                   
                     while (rs.next()){
                        int tempId = rs.getInt("id");
                        int tempScore = rs.getInt("score");
                        StringSetTransfer.hm_mq.put(new Integer(tempId), new Integer(tempScore));
                     }

                     s = myConnection.createStatement();
                     rs = s.executeQuery("select id, score from choices2_test_score");                     
                     while (rs.next()){
                        int tempId = rs.getInt("id");
                        int tempScore = rs.getInt("score");
                        StringSetTransfer.hm_c.put(new Integer(tempId), new Integer(tempScore));
                     }
                     
                     s = myConnection.createStatement();
                     rs = s.executeQuery("select id, score from multiply_test_score");                       
                     while (rs.next()){
                        int tempId = rs.getInt("id");
                        int tempScore = rs.getInt("score");
                        StringSetTransfer.hm_m.put(new Integer(tempId), new Integer(tempScore));
                     }

                     System.out.println("among which, the total number of scores of questions2: " + StringSetTransfer.hm_q.size());
                     System.out.println("among which, the total number of scores of choices2: " + StringSetTransfer.hm_c.size());
                     System.out.println("among which, the total number of scores of termMatch: " + StringSetTransfer.hm_tm.size());
                     System.out.println("among which, the total number of scores of fillBlank2: " + StringSetTransfer.hm_fb.size());
                     System.out.println("among which, the total number of scores of fillDoubleBlanks2: " + StringSetTransfer.hm_fdb.size());
                     System.out.println("among which, the total number of scores of multipleQuestions2: " + StringSetTransfer.hm_mq.size());
                     System.out.println("among which, the total number of scores of multiply: " + StringSetTransfer.hm_m.size());

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
            String answeredHashSetStr = (String)((SmartRandomAllForm) form).getAnsweredHashSet();
            hs = StringSetTransfer.stringToSet(answeredHashSetStr);
            String answeredHashSetStr_tf = (String)((SmartRandomAllForm) form).getAnsweredHashSet_tf();
            hs_tf = StringSetTransfer.stringToSet(answeredHashSetStr_tf);
            String answered_M_HashSetStr = (String)((SmartRandomAllForm) form).getAnswered_M_HashSet();
            mhs = StringSetTransfer.stringToSet(answered_M_HashSetStr);
            String answeredHashSetStr_fb = (String)((SmartRandomAllForm) form).getAnsweredHashSet_fb();
            hs_fb = StringSetTransfer.stringToSet(answeredHashSetStr_fb);
            String answeredHashSetStr_fdb = (String)((SmartRandomAllForm) form).getAnsweredHashSet_fdb();
            hs_fdb = StringSetTransfer.stringToSet(answeredHashSetStr_fdb);
            String answeredHashSetStr_tm = (String)((SmartRandomAllForm) form).getAnsweredHashSet_tm();  
            hs_tm = StringSetTransfer.stringToSet(answeredHashSetStr_tm);
            String answeredHashSetStr_m = (String)((SmartRandomAllForm) form).getAnswered_MY_HashSet();  
            hs_m = StringSetTransfer.stringToSet(answeredHashSetStr_m);    
            String answeredHashSetStr_d = (String)((SmartRandomForm) form).getAnswered_D_HashSet();  
            hs_d = StringSetTransfer.stringToSet(answeredHashSetStr_d);         
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
         
        String lastType = (String)((SmartRandomAllForm) form).getLastType();
        String thisType = (String)((SmartRandomAllForm) form).getThisType();
        String continueRight = (String)((SmartRandomAllForm) form).getContinueRight();
        String continueWrong = (String)((SmartRandomAllForm) form).getContinueWrong();
        int intContinueRight = Integer.parseInt(continueRight);
        int intContinueWrong = Integer.parseInt(continueWrong);
        String neverHigh = (String)((SmartRandomAllForm) form).getNeverHigh();
        boolean boolNeverHigh = Boolean.parseBoolean(neverHigh);
        System.out.println("Inside SmartRandomAllAction.java, lastType = " + lastType);

        int randomNumber = 0;
        HashSet control = new HashSet();
        control.add(new Integer(0)); control.add(new Integer(1)); control.add(new Integer(2)); control.add(new Integer(3)); control.add(new Integer(4));
        HashSet forCom = new HashSet();
        int id = 0;
        HashSet forType = new HashSet();
        String lastCorrect = (String)((SmartRandomAllForm) form).getLastCorrect();
        System.out.println("Inside SmartRandomAllAction.java, lastCorrect = " + lastCorrect);

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

                 System.out.println("Inside SmartRandomAllAction, thisType = " + thisType);
                 //below we will not care about any used set when we decide whether low,middle,high are used up
                 switch (thisType.charAt(0)){
                    case 'L':
                       System.out.println("Inside SmartRandomAllAction, case 'L', thisType = " + thisType);
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
                       System.out.println("Inside SmartRandomAllAction, case 'M', thisType = " + thisType);
                       
                       if (hs_fb.containsAll(StringSetTransfer.hs_middle_fb)){
                          if (hs_tm.containsAll(StringSetTransfer.hs_middle_tm)){
                             System.out.println("Inside SmartRandomAllAction, case 'M', this means all middle problems are ex = " + thisType);
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
                       System.out.println("Inside SmartRandomAllAction, case 'H', thisType = " + thisType);
                       if (mhs.containsAll(StringSetTransfer.mhs_high)){
                          if (hs_fdb.containsAll(StringSetTransfer.hs_high_fdb)){
                             if (hs_m.containsAll(StringSetTransfer.hs_high_m)){
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
                       if (Math.random() <= 0.3){
                          randomNumber = 2;
                       }else if ((Math.random()>0.3) && (Math.random()<=0.6)){
                          randomNumber = 4;
                       }else{
                          randomNumber = 6;
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

     System.out.println("Inside SmartRandomAllAction, after id and randomNumber is obtained, id = " + id + " and randomNumber = " + randomNumber);

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
                  //getServlet().log("inside SmartRandomAllAction.java, when select * from choices2 where id=?,  id = " + id);
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
                  //getServlet().log("at initiation in SmartRandomAllActioin: fdbb is reached");
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
    System.out.println("Inside SmartRandomAllAction, after id and randomNumber is obtained and after if(id!=0) and try block, id = " + id + " and randomNumber = " + randomNumber);
      
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

        request.setAttribute( Constants.HASHSET_KEY, StringSetTransfer.setToString(hs));   
        request.setAttribute( Constants.HASHSET_TF_KEY, StringSetTransfer.setToString(hs_tf));
        request.setAttribute( Constants.HASHSET_M_KEY, StringSetTransfer.setToString(mhs)); 
        request.setAttribute( Constants.HASHSET_FB_KEY, StringSetTransfer.setToString(hs_fb)); 
        request.setAttribute( Constants.HASHSET_FDB_KEY, StringSetTransfer.setToString(hs_fdb)); 
        request.setAttribute( Constants.HASHSET_TM_KEY, StringSetTransfer.setToString(hs_tm));
        request.setAttribute( Constants.HASHSET_MY_KEY, StringSetTransfer.setToString(hs_m)); 
        request.setAttribute( Constants.HASHSET_D_KEY, StringSetTransfer.setToString(hs_d)); 
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
        getServlet().log("at SmartRandomAllAction, hs string is " + StringSetTransfer.setToString(hs));
        getServlet().log("SmartRandomAllAction, hs_tf str is " + StringSetTransfer.setToString(hs_tf));
        getServlet().log("SmartRandomAllAction, mhs str is " + StringSetTransfer.setToString(mhs));   
        getServlet().log("SmartRandomAllAction, hs_fb str is " + StringSetTransfer.setToString(hs_fb));   
        getServlet().log("SmartRandomAllAction, hs_fdb str is " + StringSetTransfer.setToString(hs_fdb));
        getServlet().log("SmartRandomAllAction, hs_tm str is " + StringSetTransfer.setToString(hs_tm));   
        getServlet().log("SmartRandomAllAction, hs_m str is " + StringSetTransfer.setToString(hs_m));   
        getServlet().log("SmartRandomGenAction, hs_d str is " + StringSetTransfer.setToString(hs_d));                   
        
        String answeredProblems = (String)((SmartRandomAllForm) form).getAnsweredProblems();
        String correctAnswers = (String)((SmartRandomAllForm) form).getCorrectAnswers();
        int intAnsweredProblems = Integer.parseInt(answeredProblems);
        int intCorrectAnswers = Integer.parseInt(correctAnswers);           
        pb2.setAnsweredProblems(intAnsweredProblems);
        pb2.setCorrectAnswers(intCorrectAnswers); 

        String totalScore = (String)((SmartRandomAllForm) form).getTotalScore();
        String correctAnswers_low = (String)((SmartRandomAllForm) form).getCorrectAnswers_low();
        String answeredProblems_low = (String)((SmartRandomAllForm) form).getAnsweredProblems_low();
        double dbTotalScore = Double.parseDouble(totalScore);
        int intCorrectAnswers_low = Integer.parseInt(correctAnswers_low);
        int intAnsweredProblems_low = Integer.parseInt(answeredProblems_low);                      
        pb2.setTotalScore(dbTotalScore);
        pb2.setCorrectAnswers_low(intCorrectAnswers_low);
        pb2.setAnsweredProblems_low(intAnsweredProblems_low);   

        String correctAnswers_middle = (String)((SmartRandomAllForm) form).getCorrectAnswers_middle();
        String answeredProblems_middle = (String)((SmartRandomAllForm) form).getAnsweredProblems_middle();        
        int intCorrectAnswers_middle = Integer.parseInt(correctAnswers_middle);
        int intAnsweredProblems_middle = Integer.parseInt(answeredProblems_middle);       
        pb2.setCorrectAnswers_middle(intCorrectAnswers_middle);
        pb2.setAnsweredProblems_middle(intAnsweredProblems_middle); 

        String correctAnswers_high = (String)((SmartRandomAllForm) form).getCorrectAnswers_high();
        String answeredProblems_high = (String)((SmartRandomAllForm) form).getAnsweredProblems_high();        
        int intCorrectAnswers_high = Integer.parseInt(correctAnswers_high);
        int intAnsweredProblems_high = Integer.parseInt(answeredProblems_high);       
        pb2.setCorrectAnswers_high(intCorrectAnswers_high);
        pb2.setAnsweredProblems_high(intAnsweredProblems_high);         

        String times = (String)((SmartRandomAllForm) form).getTimes();
        pb2.setTimes(times);
        pb2.setRandomNumber(new Integer(randomNumber).toString());          

        //now we are going to set up the timer
        if (first.equals("true")){
           if (StringSetTransfer.myTask != null) StringSetTransfer.myTask.cancel();
           if (StringSetTransfer.aTimer != null) StringSetTransfer.aTimer.cancel();
           StringSetTransfer.aTimer = new Timer(userName);
           StringSetTransfer.myTask = new MyTask(userName);           
           StringSetTransfer.aTimer.scheduleAtFixedRate(StringSetTransfer.myTask, 0, 30000);
           /*
           Timer aTimer = new Timer(userName);
           MyTask myTask = new MyTask(userName);           
           aTimer.scheduleAtFixedRate(myTask, 0, 30000);
           */
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
            default:
               break;
        }          
        return (mapping.findForward("ShowSmartProblem"));        
    }
}

