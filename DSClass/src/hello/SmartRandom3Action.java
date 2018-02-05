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

public final class SmartRandom3Action extends Action {

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
        FillTripleBlankBean ftbb = null;
        FillQuadBlankBean fqbb = null;
        TermMatchBean tmb = null;
        MultiplyBean mb = null;
        DivisionBean db = null;
        PlusMinusBean pmb = null;
        String first = null;
        first = (String)request.getParameter("first"); 

        PersonBean pb2 = null;
        pb2 = new PersonBean();
        String userName = (String)((SmartRandom3Form) form).getUserName();
        String passWord = (String)((SmartRandom3Form) form).getPassWord();
        String trueName = (String)((SmartRandom3Form) form).getTrueName();
        String playerId = (String)((SmartRandom3Form) form).getPlayerId();
        pb2.setUserName(userName);
        pb2.setPassWord(passWord); 
        pb2.setTrueName(trueName);
        pb2.setPlayerId(playerId);  

        String times = (String)((SmartRandom3Form) form).getTimes();
        pb2.setTimes(times);

        String planStatus = (String)((SmartRandom3Form) form).getPlanStatus();
        pb2.setPlanStatus(planStatus);

        String classId = (String)((SmartRandom3Form) form).getClassId();
        pb2.setClassId(classId);

        String source = (String)((SmartRandom3Form) form).getSource();

        javax.sql.DataSource dataSource = null;
        java.sql.Connection myConnection = null;
        Statement s = null;
        PreparedStatement stmt = null;
        PreparedStatement stmt2 = null;
        ResultSet rs = null;
        pb = new SmartProblemBean();
        cb = new SmartChoiceBean();
        mpb = new SmartMultipleProblemBean(); 
        fbb = new FillBlankBean();
        fdbb = new FillDoubleBlankBean();
        ftbb = new FillTripleBlankBean();           
        fqbb = new FillQuadBlankBean();     
        tmb = new TermMatchBean();
        mb = new MultiplyBean();
        db = new DivisionBean();
        pmb = new PlusMinusBean(); 

        pb.setSource(source);
        cb.setSource(source);
        mpb.setSource(source);
        fbb.setSource(source);
        fdbb.setSource(source);
        ftbb.setSource(source);
        fqbb.setSource(source);
        tmb.setSource(source);
        mb.setSource(source);
        db.setSource(source);
        pmb.setSource(source);

        HashSet hs = null;
        HashSet hs_tf = null;
        HashSet mhs = null;
        HashSet hs_fb = null;
        HashSet hs_fdb = null;
        HashSet hs_ftb = null;
        HashSet hs_fqb = null;
        HashSet hs_tm = null;   
        HashSet hs_m = null; 
        HashSet hs_d = null;   
        HashSet hs_pm = null;   

        String tempPlayerId = null;
        String tempUserName = null;
        String tempTrueName = null;
        String tempPassword = null;

        //String TOMCAT_PATH = "E:\\Tomcat_6\\reverseTest\\grade6_2nd";
        String TOMCAT_PATH = "F:\\Dongshan\\Tomcat_6\\reverseTest\\grade6_2nd";
        //String TOMCAT_PATH = "G:\\Dongshan\\Tomcat_6\\reverseTest\\grade6_2nd";
        String cNote = "\\";

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
            hs_ftb = new HashSet(); 
            hs_ftb.add(new Integer(0));
            hs_fqb = new HashSet(); 
            hs_fqb.add(new Integer(0));
            hs_tm = new HashSet(); 
            hs_tm.add(new Integer(0));
            hs_m = new HashSet(); 
            hs_m.add(new Integer(0));
            hs_d = new HashSet(); 
            hs_d.add(new Integer(0));
            hs_pm = new HashSet(); 
            hs_pm.add(new Integer(0));

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
            HashSet hs_high_ftb = new HashSet();
            HashSet hs_high_fqb = new HashSet();
            HashSet hs_middle_tm = new HashSet();
            HashSet hs_middle_tm2 = new HashSet();
            HashSet hs_high_d = new HashSet();
            HashSet hs_high_m = new HashSet();
            HashSet hs_middle_pm = new HashSet();

            HashSet hs_low_used = new HashSet();
            HashSet hs_low_tf_used = new HashSet();
            HashSet hs_middle_fb_used = new HashSet(); 
            HashSet mhs_high_used = new HashSet();
            HashSet hs_high_fdb_used = new HashSet();
            HashSet hs_high_ftb_used = new HashSet();
            HashSet hs_high_fqb_used = new HashSet();
            HashSet hs_middle_tm_used = new HashSet();
            HashSet hs_middle_tm2_used = new HashSet();
            HashSet hs_high_d_used = new HashSet();
            HashSet hs_high_m_used = new HashSet();
            HashSet hs_middle_pm_used = new HashSet();

            int count = 0;             
            try{
                //we will try to initiate the usedQuestions first, then generate the random question id 
               dataSource = getDataSource(request);
               myConnection = dataSource.getConnection();
               //Class.forName("org.hsqldb.jdbcDriver");
               //myConnection = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/xdb", "SA", "");
               if (!userName.equals("2000")){
                  stmt = myConnection.prepareStatement("update student_score set status='active',planStatus='not finished' where studentNo = ?");
                  stmt.clearParameters();
                  stmt.setString(1, userName);
                  stmt.executeUpdate();   
        
                  s = myConnection.createStatement();
                  rs = s.executeQuery("select status from student_score where studentNo = '2000' ");
                  String status = null;
                  while (rs.next()){
                     status = rs.getString("status");
                  }
                  if (status == null || status.equals("") || status.equals("not active")){
                     request.removeAttribute(mapping.getAttribute());     
                     session.setAttribute( Constants.PERSON_KEY, pb2); 
                     request.setAttribute( Constants.PERSON_KEY, pb2); 
                     return (mapping.findForward("ShowSmartPairWait"));        
                  }
               }else{//userName.equals("2000")
          

                     //we want to initiate StringSetTransfer.hs_low_used,hs_low,hs_middle,hs_high,total
                     /*
                     s = myConnection.createStatement();
                     rs = s.executeQuery("select problemId from usedQuestions2_test");                
                     while (rs.next()){
                        int usedId = rs.getInt("problemId");
                        hs_low_used.add(new Integer(usedId));
                     }
                     rs = s.executeQuery("select problemId from usedQuestions2_test where type='L' and source='" + source + "'");
                     while (rs.next()){
                        int usedId = rs.getInt("problemId");
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
                     rs = s.executeQuery("select id from questions2_test where source='" + source + "'");         
                     int numberId = 0;
                     while (rs.next()){
                        int usedId = rs.getInt("id");
                        numberId ++;                 
                        //getServlet().log("score part is reached");
                     }  
                     StringSetTransfer.total = numberId;
                                                    

                     //now we want to initiate StringSetTransfer.hs_low_tf_used,hs_low_tf,hs_middle_tf,hs_high_tf,total_tf
                     rs = s.executeQuery("select problemId from usedChoices2_test");                
                     while (rs.next()){
                        int usedId = rs.getInt("problemId");
                        hs_low_tf_used.add(new Integer(usedId));
                     }
                     rs = s.executeQuery("select problemId from usedChoices2_test where type='L' and source='" + source + "'");
                     while (rs.next()){
                        int usedId = rs.getInt("problemId");
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
                     rs = s.executeQuery("select id from choices2_test where source='" + source + "'");         
                     numberId = 0;
                     while (rs.next()){
                        int usedId = rs.getInt("id");
                        numberId ++;                 
                        //getServlet().log("score part is reached");
                     }  
                     StringSetTransfer.total_tf = numberId;
                   

                    //now we want to initiate StringSetTransfer.hs_middle_fb_used,hs_middle_fb,total_fb
                    
                     rs = s.executeQuery("select problemId from usedFillBlank2_test where type='M' and source='" + source + "'");       
                     while (rs.next()){
                        int usedId = rs.getInt("problemId");
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
                     
                     rs = s.executeQuery("select id from fillBlank2_test where source='" + source + "'");         
                     numberId = 0;
                     while (rs.next()){
                        int usedId = rs.getInt("id");
                        numberId ++;                 
                        //getServlet().log("score part is reached");
                     }  
                     StringSetTransfer.total_fb = numberId;

                 //now we want to initiate StringSetTransfer.hs_high_fdb_used,hs_high_fdb,total_fdb
                    
                     rs = s.executeQuery("select problemId from usedFillDoubleBlanks2_test where type='H' and source='" + source + "'");
                     while (rs.next()){
                        int usedId = rs.getInt("problemId");
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
                     rs = s.executeQuery("select id from fillDoubleBlanks2_test where source='"+ source + "'");         
                     numberId = 0;
                     while (rs.next()){
                        int usedId = rs.getInt("id");
                        numberId ++;                 
                        //getServlet().log("score part is reached");
                     }  
                     StringSetTransfer.total_fdb = numberId;

                     //now we want to initiate StringSetTransfer.hs_high_fqb_used,hs_high_fqb,total_fqb                     
                     rs = s.executeQuery("select problemId from usedFillQuadBlanks2_test where type='H' and source='" + source + "'");
                     while (rs.next()){
                        int usedId = rs.getInt("problemId");
                        hs_high_fqb_used.add(new Integer(usedId));
                     }
                     StringSetTransfer.hs_high_fqb_used = hs_high_fqb_used;
                     rs = s.executeQuery("select id from fillQuadBlanks2_test where type='H' and source='" + source + "'");
                     while (rs.next()){
                        int usedId = rs.getInt("id");
                        hs_high_fqb.add(new Integer(usedId));
                     }
                     hs_high_fqb.removeAll(hs_high_fqb_used);
                     StringSetTransfer.hs_high_fqb = hs_high_fqb;
                     rs = s.executeQuery("select id from fillQuadBlanks2_test where source='"+ source + "'");         
                     numberId = 0;
                     while (rs.next()){
                        int usedId = rs.getInt("id");
                        numberId ++;                 
                        //getServlet().log("score part is reached");
                     }  
                     StringSetTransfer.total_fqb = numberId;
                     */

                    hs_low = StringSetTransfer.getSetFromFile(TOMCAT_PATH+cNote+source+cNote+"Questions2_test_used.txt"); 
                    s = myConnection.createStatement();                    
                    rs = s.executeQuery("select problemId from usedQuestions2_test where type='L' and source='" + source + "'");
                    while (rs.next()){
                        int usedId = rs.getInt("problemId");
                        hs_low_used.add(new Integer(usedId));
                    }
                    StringSetTransfer.hs_low_used = hs_low_used;
                    hs_low.removeAll(hs_low_used);
                    StringSetTransfer.hs_low = hs_low;
                    StringSetTransfer.total = StringSetTransfer.hs_low.size();

                    hs_low_tf =  StringSetTransfer.getSetFromFile(TOMCAT_PATH+cNote+source+cNote+"Choices2_test_used.txt");
                    s = myConnection.createStatement();  
                    rs = s.executeQuery("select problemId from usedChoices2_test where type='L' and source='" + source + "'");
                    while (rs.next()){
                       int usedId = rs.getInt("problemId");
                       hs_low_tf_used.add(new Integer(usedId));
                    }
                    StringSetTransfer.hs_low_tf_used = hs_low_tf_used;
                    hs_low_tf.removeAll(hs_low_tf_used);
                    StringSetTransfer.hs_low_tf = hs_low_tf;
                    StringSetTransfer.total_tf = StringSetTransfer.hs_low_tf.size(); 

                    hs_middle_fb = StringSetTransfer.getSetFromFile(TOMCAT_PATH+cNote+source+cNote+"FillBlank2_test_used.txt");
                    s = myConnection.createStatement();
                    rs = s.executeQuery("select problemId from usedFillBlank2_test where type='M' and source='" + source + "'");       
                    while (rs.next()){
                       int usedId = rs.getInt("problemId");
                       hs_middle_fb_used.add(new Integer(usedId));
                    }
                    StringSetTransfer.hs_middle_fb_used = hs_middle_fb_used;
                    hs_middle_fb.removeAll(hs_middle_fb_used);
                    StringSetTransfer.hs_middle_fb = hs_middle_fb;
                    StringSetTransfer.total_fb = StringSetTransfer.hs_middle_fb.size();

                    hs_high_fdb = StringSetTransfer.getSetFromFile(TOMCAT_PATH+cNote+source+cNote+"FillDoubleBlanks2_test_used.txt");
                    s = myConnection.createStatement();
                    rs = s.executeQuery("select problemId from usedFillDoubleBlanks2_test where type='H' and source='" + source + "'");
                    while (rs.next()){
                        int usedId = rs.getInt("problemId");
                        hs_high_fdb_used.add(new Integer(usedId));
                    }
                    StringSetTransfer.hs_high_fdb_used = hs_high_fdb_used;
                    hs_high_fdb.removeAll(hs_high_fdb_used);
                    StringSetTransfer.hs_high_fdb = hs_high_fdb;
                    StringSetTransfer.total_fdb = StringSetTransfer.hs_high_fdb.size();

                    hs_high_ftb = StringSetTransfer.getSetFromFile(TOMCAT_PATH+cNote+source+cNote+"FillTripleBlanks2_test_used.txt");
                    s = myConnection.createStatement();
                    rs = s.executeQuery("select problemId from usedFillTripleBlanks2_test where type='H' and source='" + source + "'");
                    while (rs.next()){
                        int usedId = rs.getInt("problemId");
                        hs_high_ftb_used.add(new Integer(usedId));
                    }
                    StringSetTransfer.hs_high_ftb_used = hs_high_ftb_used;
                    hs_high_ftb.removeAll(hs_high_ftb_used);
                    StringSetTransfer.hs_high_ftb = hs_high_ftb;
                    StringSetTransfer.total_ftb = StringSetTransfer.hs_high_ftb.size();

                    hs_high_fqb = StringSetTransfer.getSetFromFile(TOMCAT_PATH+cNote+source+cNote+"FillQuadBlanks2_test_used.txt");
                    s = myConnection.createStatement(); 
                    rs = s.executeQuery("select problemId from usedFillQuadBlanks2_test where type='H' and source='" + source + "'");
                    while (rs.next()){
                       int usedId = rs.getInt("problemId");
                       hs_high_fqb_used.add(new Integer(usedId));
                    }
                    StringSetTransfer.hs_high_fqb_used = hs_high_fqb_used;
                    hs_high_fqb.removeAll(hs_high_fqb_used);
                    StringSetTransfer.hs_high_fqb = hs_high_fqb;
                    StringSetTransfer.total_fqb = StringSetTransfer.hs_high_fqb.size(); 

                    hs_high_m = StringSetTransfer.getSetFromFile(TOMCAT_PATH+cNote+source+cNote+"Multiply_test_used.txt");
                    s = myConnection.createStatement(); 
                    rs = s.executeQuery("select problemId from usedMultiply_test where type='H' and source='" + source + "'");
                    while (rs.next()){
                       int usedId = rs.getInt("problemId");
                       hs_high_m_used.add(new Integer(usedId));
                    }
                    StringSetTransfer.hs_high_m_used = hs_high_m_used;
                    hs_high_m.removeAll(hs_high_m_used);
                    StringSetTransfer.hs_high_m = hs_high_m;
                    StringSetTransfer.total_m = StringSetTransfer.hs_high_m.size();

                    hs_high_d = StringSetTransfer.getSetFromFile(TOMCAT_PATH+cNote+source+cNote+"Division_test_used.txt");
                    s = myConnection.createStatement(); 
                    rs = s.executeQuery("select problemId from usedDivision_test where type='H' and source='" + source + "'");
                    while (rs.next()){
                       int usedId = rs.getInt("problemId");
                       hs_high_d_used.add(new Integer(usedId));
                    }
                    StringSetTransfer.hs_high_d_used = hs_high_d_used;
                    hs_high_d.removeAll(hs_high_d_used);
                    StringSetTransfer.hs_high_d = hs_high_d;
                    StringSetTransfer.total_d = StringSetTransfer.hs_high_d.size();

                    hs_middle_pm = StringSetTransfer.getSetFromFile(TOMCAT_PATH+cNote+source+cNote+"PlusMinus_used.txt");
                    s = myConnection.createStatement(); 
                    rs = s.executeQuery("select problemId from usedPlusMinus where source='" + source + "'");
                    while (rs.next()){
                       int usedId = rs.getInt("problemId");
                       hs_middle_pm_used.add(new Integer(usedId));
                    }
                    StringSetTransfer.hs_middle_pm_used = hs_middle_pm_used;
                    hs_middle_pm.removeAll(hs_middle_pm_used);
                    StringSetTransfer.hs_middle_pm = hs_middle_pm;
                    StringSetTransfer.total_pm = StringSetTransfer.hs_middle_pm.size();
                    
 System.out.println("the total number of questions: " + (StringSetTransfer.total_fb+StringSetTransfer.total_fdb+StringSetTransfer.total_ftb+StringSetTransfer.total_fqb+StringSetTransfer.mtotal+StringSetTransfer.total_tf+StringSetTransfer.total+StringSetTransfer.total_m+StringSetTransfer.total_d+StringSetTransfer.total_pm) + "");
                System.out.println("among which, the total number of type low questions: " + (StringSetTransfer.hs_low_tf.size()+StringSetTransfer.hs_low.size()) + "");
                System.out.println("among which, the total number of type middle questions: " + (StringSetTransfer.hs_middle_fb.size()+StringSetTransfer.hs_middle_pm.size()) + "");     
                System.out.println("among which, the total number of type high questions: " + (StringSetTransfer.hs_high_fdb.size()+StringSetTransfer.hs_high_ftb.size()+StringSetTransfer.hs_high_fqb.size()+StringSetTransfer.mhs_high.size()+StringSetTransfer.hs_high_m.size()+StringSetTransfer.hs_high_d.size()) + "");          

                     //now we also want 2000 to insert into StringSetTransfer.nameMap all the student names
                     s = myConnection.createStatement();
                     rs = s.executeQuery("select studentNo, name from students");
                     while (rs.next()){
                        tempUserName = rs.getString(1);
                        tempTrueName = rs.getString(2);
                        StringSetTransfer.nameMap.put(tempUserName, tempTrueName);
                     }  

                     s = myConnection.createStatement();
                     s.executeUpdate("update student_score set status = 'initiated' where studentNo ='2000' ");

               }//THE END OF ELSE userName.equals("2000")

               s = myConnection.createStatement();
               rs = s.executeQuery("select playerId, planStatus from student_score where studentNo = '" + userName + "'");
               while (rs.next()){
                  playerId = rs.getString("playerId");
                  planStatus = rs.getString("planStatus");
               }
               pb2.setPlayerId(playerId);              
               pb2.setPlanStatus(planStatus);

            }catch (SQLException sqle) {
               //getServlet().log("Connection.process", sqle);
               sqle.printStackTrace();
            } finally {           
                try {
                    if (rs != null) rs.close();
                    if (s != null) s.close();
                    if (myConnection != null) myConnection.close();
                } catch (SQLException e) {
                    //getServlet().log("Connection.close", e);
                    e.printStackTrace();
                }
            }
        }else{//first.equals("false")
            String answeredHashSetStr = (String)((SmartRandom3Form) form).getAnsweredHashSet();
            hs = StringSetTransfer.stringToSet(answeredHashSetStr);
            String answeredHashSetStr_tf = (String)((SmartRandom3Form) form).getAnsweredHashSet_tf();
            hs_tf = StringSetTransfer.stringToSet(answeredHashSetStr_tf);
            String answered_M_HashSetStr = (String)((SmartRandom3Form) form).getAnswered_M_HashSet();
            mhs = StringSetTransfer.stringToSet(answered_M_HashSetStr);
            String answeredHashSetStr_fb = (String)((SmartRandom3Form) form).getAnsweredHashSet_fb();
            hs_fb = StringSetTransfer.stringToSet(answeredHashSetStr_fb);            
            String answeredHashSetStr_fdb = (String)((SmartRandom3Form) form).getAnsweredHashSet_fdb();
            hs_fdb = StringSetTransfer.stringToSet(answeredHashSetStr_fdb);
            String answeredHashSetStr_ftb = (String)((SmartRandom3Form) form).getAnsweredHashSet_ftb();
            hs_ftb = StringSetTransfer.stringToSet(answeredHashSetStr_ftb);
            String answeredHashSetStr_fqb = (String)((SmartRandom3Form) form).getAnsweredHashSet_fqb();
            hs_fqb = StringSetTransfer.stringToSet(answeredHashSetStr_fqb);
            String answeredHashSetStr_tm = (String)((SmartRandom3Form) form).getAnsweredHashSet_tm();  
            hs_tm = StringSetTransfer.stringToSet(answeredHashSetStr_tm);           
            String answeredHashSetStr_m = (String)((SmartRandom3Form) form).getAnsweredHashSet_m();  
            hs_m = StringSetTransfer.stringToSet(answeredHashSetStr_m);
            String answeredHashSetStr_d = (String)((SmartRandom3Form) form).getAnsweredHashSet_d();  
            hs_d = StringSetTransfer.stringToSet(answeredHashSetStr_d);
            String answeredHashSetStr_pm = (String)((SmartRandom3Form) form).getAnsweredHashSet_pm();  
            hs_pm = StringSetTransfer.stringToSet(answeredHashSetStr_pm);   
        }

        // Remove the Form Bean - don't need to carry values forward
        request.removeAttribute(mapping.getAttribute());

        session.setAttribute( Constants.PERSON_KEY, pb2); 
        request.setAttribute( Constants.PERSON_KEY, pb2); 

        if (userName.equals("2000")){
           return (mapping.findForward("Show2000Zero"));
        }
         
        String lastType = (String)((SmartRandom3Form) form).getLastType();
        String thisType = (String)((SmartRandom3Form) form).getThisType();
        String continueRight = (String)((SmartRandom3Form) form).getContinueRight();
        String continueWrong = (String)((SmartRandom3Form) form).getContinueWrong();
        int intContinueRight = Integer.parseInt(continueRight);
        int intContinueWrong = Integer.parseInt(continueWrong);
        String neverHigh = (String)((SmartRandom3Form) form).getNeverHigh();
        boolean boolNeverHigh = Boolean.parseBoolean(neverHigh);
        System.out.println("Inside SmartRandom3Action.java, lastType = " + lastType);

        int randomNumber = 0;
        HashSet control = new HashSet();
        control.add(new Integer(0)); control.add(new Integer(1)); control.add(new Integer(2)); control.add(new Integer(3)); control.add(new Integer(4));
        HashSet forCom = new HashSet();
        int id = 0;
        HashSet forType = new HashSet();
        String lastCorrect = (String)((SmartRandom3Form) form).getLastCorrect();
        System.out.println("Inside SmartRandom3Action.java, lastCorrect = " + lastCorrect);

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
           if (StringSetTransfer.tb133_start==true && StringSetTransfer.tb133_end==false){
              thisType = "H";
              randomNumber = 7;
              id = 133;
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

                 System.out.println("Inside SmartRandom3Action, thisType = " + thisType);
                 //below we will not care about any used set when we decide whether low,middle,high are used up
                 switch (thisType.charAt(0)){
                    case 'L':
                       System.out.println("Inside SmartRandom3Action, case 'L', thisType = " + thisType);
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
                             if (hs_pm.containsAll(StringSetTransfer.hs_middle_pm)){
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
                       }
                       if (Math.random() < 0.3){
                          randomNumber = 3;
                       }else if (Math.random() < 0.6){
                          randomNumber = 5;
                       }else{
                          randomNumber = 10;
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
                          case 10:
                             id = StringSetTransfer.getRandomNumber_pm(thisType.charAt(0), hs_pm); 
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
                             if (hs_fqb.containsAll(StringSetTransfer.hs_high_fqb)){
                                if (hs_ftb.containsAll(StringSetTransfer.hs_high_ftb)){
                                   if (hs_m.containsAll(StringSetTransfer.hs_high_m)){
                                      if (hs_d.containsAll(StringSetTransfer.hs_high_d)){ 
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
                       }                       
                       if (Math.random() < 0.15){
                          randomNumber = 2;
                       }else if (Math.random() < 0.30){
                          randomNumber = 4;
                       }else if (Math.random() < 0.45){
                          randomNumber = 6;
                       }else if (Math.random() < 0.6){
                          randomNumber = 7;
                       }else if (Math.random() < 0.75){
                          randomNumber = 8;
                       }else{
                          randomNumber = 9;
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
                             id = StringSetTransfer.getRandomNumber_fqb(thisType.charAt(0), hs_fqb); 
                             if (id == 0){
                                continue outer;
                             }
                             break;
                          case 7:
                             id = StringSetTransfer.getRandomNumber_ftb(thisType.charAt(0), hs_ftb); 
                             if (id == 0){
                                continue outer;
                             }
                             break;
                          case 8:
                             id = StringSetTransfer.getRandomNumber_m(thisType.charAt(0), hs_m); 
                             if (id == 0){
                                continue outer;
                             }
                             break;
                          case 9:
                             id = StringSetTransfer.getRandomNumber_d(thisType.charAt(0), hs_d); 
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

     System.out.println("Inside SmartRandom3Action, after id and randomNumber is obtained, id = " + id + " and randomNumber = " + randomNumber);

     int score = 0;
     if (id != 0){
        pb2.setCurrentProblemId(id);

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
                  stmt = myConnection.prepareStatement("select score from questions2_test_score where id=?");
                  stmt.clearParameters();
                  stmt.setInt(1, id);
                  rs = stmt.executeQuery();
                  while(rs.next()){
                     pb.setScore(rs.getInt("score"));
                  }
                  break;
               case 1:
                  hs_tf.add(new Integer(id)); 
                  stmt = myConnection.prepareStatement("select * from choices2_test where id=?");
                  //getServlet().log("inside SmartRandom3Action.java, when select * from choices2 where id=?,  id = " + id);
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
                  stmt = myConnection.prepareStatement("select score from choices2_test_score where id=?");
                  stmt.clearParameters();
                  stmt.setInt(1, id);
                  rs = stmt.executeQuery();
                  while(rs.next()){
                     cb.setScore(rs.getInt("score"));
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
                  stmt = myConnection.prepareStatement("select score from multipleQuestions2_test_score where id=?");
                  stmt.clearParameters();
                  stmt.setInt(1, id);
                  rs = stmt.executeQuery();
                  while(rs.next()){
                     mpb.setScore(rs.getInt("score"));
                  }
                  break;
               case 3:
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
                  stmt = myConnection.prepareStatement("select score from fillBlank2_test_score where id=?");
                  stmt.clearParameters();
                  stmt.setInt(1, id);
                  rs = stmt.executeQuery();
                  while(rs.next()){
                     fbb.setScore(rs.getInt("score"));
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
                  stmt = myConnection.prepareStatement("select score from fillDoubleBlanks2_test_score where id=?");
                  stmt.clearParameters();
                  stmt.setInt(1, id);
                  rs = stmt.executeQuery();
                  while(rs.next()){
                     fdbb.setScore(rs.getInt("score"));
                  }
                  break;
               case 5:
                  /*
                  hs_tm.add(new Integer(id)); 
                   
                  stmt = myConnection.prepareStatement("select * from termMatch2 where id=?");
                  stmt.clearParameters();
                  stmt.setInt(1, id);
                  rs = stmt.executeQuery();
                  while (rs.next()){
                     tmb.setId(rs.getInt("id"));
                     tmb.setStatement(rs.getString("statement"));
                     tmb.setChoiceA(rs.getString("choiceA"));
                     tmb.setChoiceB(rs.getString("choiceB"));
                     tmb.setChoiceC(rs.getString("choiceC"));
                     tmb.setChoiceD(rs.getString("choiceD"));
                     tmb.setCorrectChoice(rs.getString("correctChoice")); 
                     tmb.setSolution(rs.getString("solution"));                             
                     tmb.setType(rs.getString("type"));
                     tmb.setSource(rs.getString("source"));
                    
                     stmt2 = myConnection.prepareStatement("insert into usedTermMatch2(Id,Type,Times,StudentNo,StudentAnswer,source) values(?,'M',?,?,?,?)");
                     stmt2.clearParameters();
                     stmt2.setInt(1, rs.getInt("id"));
                     stmt2.setString(2, times);
                     stmt2.setString(3, userName);
                     stmt2.setString(4, "");
                     stmt2.setString(5, rs.getString("source"));
                     stmt2.execute();                                  
                  }                

                  */
                  /*
                  stmt = myConnection.prepareStatement("select * from termMatch where id=?");
                  stmt.clearParameters();
                  stmt.setInt(1, id);
                  rs = stmt.executeQuery();
                  while (rs.next()){
                     tmb.setId(rs.getInt("id"));
                     tmb.setTerm1(rs.getString("term1"));
                     tmb.setTerm2(rs.getString("term2"));
                     tmb.setType(rs.getString("type"));                     
                     tmb.setSource(rs.getString("source"));                     
                  }
                  */
                  break; 
               case 6:
                  hs_fqb.add(new Integer(id)); 
                  stmt = myConnection.prepareStatement("select * from fillQuadBlanks2_test where id=?");
                  stmt.clearParameters();
                  stmt.setInt(1, id);
                  rs = stmt.executeQuery();
                  while (rs.next()){
                     fqbb.setId(rs.getInt("id"));
                     fqbb.setStatement_1st(rs.getString("statement_1st"));
                     fqbb.setStatement_2nd(rs.getString("statement_2nd"));
                     fqbb.setStatement_3rd(rs.getString("statement_3rd"));
                     fqbb.setStatement_4th(rs.getString("statement_4th"));
                     fqbb.setStatement_5th(rs.getString("statement_5th"));
                     fqbb.setSolution_1st(rs.getString("solution_1st"));
                     fqbb.setSolution_2nd(rs.getString("solution_2nd"));
                     fqbb.setSolution_3rd(rs.getString("solution_3rd"));
                     fqbb.setSolution_4th(rs.getString("solution_4th"));
                     //String source = rs.getString("source");
                     fqbb.setType(rs.getString("type"));
                     fqbb.setScore(rs.getInt("score")); 
                  }
                  System.out.println("at initiation in SmartRandom3Actioin: fqbb is reached");
                  break;  
               case 7:
                  if (id>=133 && id<=134){
                     if (StringSetTransfer.tb133_start==false){
                        StringSetTransfer.tb133_start=true; 
                     }                    
                     boolean done = true;
                     int i=133;
                     for (i=133; i<=134; i++){
                        if (!hs_ftb.contains(new Integer(i))){
                           id = i;
                           if (i<134)
                              done = false;
                           break;
                        }
                     }
                     StringSetTransfer.tb133_end = done;
                  }

                  hs_ftb.add(new Integer(id)); 
                  stmt = myConnection.prepareStatement("select * from fillTripleBlanks2_test where id=?");
                  stmt.clearParameters();
                  stmt.setInt(1, id);
                  rs = stmt.executeQuery();
                  while (rs.next()){
                     ftbb.setId(rs.getInt("id"));
                     ftbb.setStatement_1st(rs.getString("statement_1st"));
                     ftbb.setStatement_2nd(rs.getString("statement_2nd"));
                     ftbb.setStatement_3rd(rs.getString("statement_3rd"));
                     ftbb.setStatement_4th(rs.getString("statement_4th"));                     
                     ftbb.setSolution_1st(rs.getString("solution_1st"));
                     ftbb.setSolution_2nd(rs.getString("solution_2nd"));
                     ftbb.setSolution_3rd(rs.getString("solution_3rd"));                    
                     //String source = rs.getString("source");
                     ftbb.setType(rs.getString("type"));
                  }
                  stmt = myConnection.prepareStatement("select score from fillTripleBlanks2_test_score where id=?");
                  stmt.clearParameters();
                  stmt.setInt(1, id);
                  rs = stmt.executeQuery();
                  while(rs.next()){
                     ftbb.setScore(rs.getInt("score"));
                  }
                  System.out.println("at initiation in SmartRandom3Actioin: ftbb is reached");
                  break;
               case 8:
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
                     mb.setP1Solution(rs.getInt("solution1")+"");  
                     mb.setP2Solution(rs.getInt("solution2")+"");  
                     mb.setRSolution(rs.getDouble("rSolution")+"");
                     mb.setResultSolution(rs.getDouble("rSolution")+"");   
                     mb.setType(rs.getString("type"));                   
                     //mb.setSource(rs.getString("source"));                     
                  }
                  stmt = myConnection.prepareStatement("select score from multiply_test_score where id=?");
                  stmt.clearParameters();
                  stmt.setInt(1, id);
                  rs = stmt.executeQuery();
                  while(rs.next()){
                     mb.setScore(rs.getInt("score"));
                  }
                  System.out.println("at initiation in SmartRandom3Actioin: mb is reached");       
                  break; 
               case 9:
                  hs_d.add(new Integer(id)); 
                  stmt = myConnection.prepareStatement("select * from division_test where id=?");
                  stmt.clearParameters();
                  stmt.setInt(1, id);
                  rs = stmt.executeQuery();
                  while (rs.next()){
                     db.setId(rs.getInt("id"));
                     db.setDivided(rs.getString("divided"));
                     db.setDivider(rs.getString("divider"));
                     db.setResult(rs.getDouble("result")+"");
                     db.setProduct1(rs.getInt("product1")+"");
                     db.setRemain1(rs.getInt("remain1")+"");
                     db.setProduct2(rs.getInt("product2")+"");
                     db.setRemain2(rs.getInt("remain2")+"");  
                     db.setProduct3(rs.getInt("product3")+"");
                     db.setRemain3(rs.getInt("remain3")+"");
                     db.setLevel(rs.getInt("level"));   
                     db.setType(rs.getString("type"));                     
                     //db.setSource(rs.getString("source"));                     
                  }
                  stmt = myConnection.prepareStatement("select score from division_test_score where id=?");
                  stmt.clearParameters();
                  stmt.setInt(1, id);
                  rs = stmt.executeQuery();
                  while(rs.next()){
                     db.setScore(rs.getInt("score"));
                  }
                  System.out.println("at initiation in SmartRandom3Actioin: db is reached");
                  break; 
               case 10:
                  hs_pm.add(new Integer(id)); 
                  stmt = myConnection.prepareStatement("select * from plusMinus where id=?");
                  stmt.clearParameters();
                  stmt.setInt(1, id);
                  rs = stmt.executeQuery();
                  while (rs.next()){
                     pmb.setId(rs.getInt("id"));
                     pmb.setFactor1(rs.getDouble("factor1")+"");
                     pmb.setFactor2(rs.getString("operator")+rs.getDouble("factor2"));
                     pmb.setResult(rs.getDouble("result")+"");                      
                     pmb.setType(rs.getString("type"));                     
                     pmb.setScore(rs.getInt("score"));                  
                  }                  
                  System.out.println("at initiation in SmartRandom3Actioin: pmb is reached");       
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
               if (stmt2 != null) stmt2.close();
               if (myConnection != null) myConnection.close();
            } catch (SQLException e) {
               getServlet().log("Connection.close", e);
            }
        }//end of try-catch-finally block 
     }else {//id==0 and planStatus.equals("finished")
        //do nothing
     }  
       
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
               request.setAttribute( Constants.FILLQUADBLANK_KEY, fqbb);        
               session.setAttribute( Constants.FILLQUADBLANK_KEY, fqbb); 
               break;
            case 7:
               request.setAttribute( Constants.FILLTRIPLEBLANK_KEY, ftbb);        
               session.setAttribute( Constants.FILLTRIPLEBLANK_KEY, ftbb); 
               break;
            case 8:
               request.setAttribute( Constants.MULTIPLY_KEY, mb);        
               session.setAttribute( Constants.MULTIPLY_KEY, mb); 
               break;
            case 9:
               request.setAttribute( Constants.DIVISION_KEY, db);        
               session.setAttribute( Constants.DIVISION_KEY, db); 
               break;  
            case 10:
               request.setAttribute( Constants.PLUSMINUS_KEY, pmb);        
               session.setAttribute( Constants.PLUSMINUS_KEY, pmb); 
               break;   
            default:
               break;
        }          
        
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
        
        System.out.println("at SmartRandom3Action, hs string is " + StringSetTransfer.setToString(hs));
        System.out.println("SmartRandom3Action, hs_tf str is " + StringSetTransfer.setToString(hs_tf));
        System.out.println("SmartRandom3Action, mhs str is " + StringSetTransfer.setToString(mhs));   
        System.out.println("SmartRandom3Action, hs_fb str is " + StringSetTransfer.setToString(hs_fb));   
        System.out.println("SmartRandom3Action, hs_fdb str is " + StringSetTransfer.setToString(hs_fdb));
        System.out.println("SmartRandom3Action, hs_ftb str is " + StringSetTransfer.setToString(hs_ftb));
        System.out.println("SmartRandom3Action, hs_fqb str is " + StringSetTransfer.setToString(hs_fqb));
        System.out.println("SmartRandom3Action, hs_tm str is " + StringSetTransfer.setToString(hs_tm));                      
        System.out.println("SmartRandom3Action, hs_m str is " + StringSetTransfer.setToString(hs_m));                      
        System.out.println("SmartRandom3Action, hs_d str is " + StringSetTransfer.setToString(hs_d));
        System.out.println("SmartRandom3Action, hs_pm str is " + StringSetTransfer.setToString(hs_pm)); 
        
        String answeredProblems = (String)((SmartRandom3Form) form).getAnsweredProblems();
        String correctAnswers = (String)((SmartRandom3Form) form).getCorrectAnswers();
        int intAnsweredProblems = Integer.parseInt(answeredProblems);
        int intCorrectAnswers = Integer.parseInt(correctAnswers);           
        pb2.setAnsweredProblems(intAnsweredProblems);
        pb2.setCorrectAnswers(intCorrectAnswers); 

        String totalScore = (String)((SmartRandom3Form) form).getTotalScore();
        String correctAnswers_low = (String)((SmartRandom3Form) form).getCorrectAnswers_low();
        String answeredProblems_low = (String)((SmartRandom3Form) form).getAnsweredProblems_low();
        double dbTotalScore = Double.parseDouble(totalScore);
        int intCorrectAnswers_low = Integer.parseInt(correctAnswers_low);
        int intAnsweredProblems_low = Integer.parseInt(answeredProblems_low);                      
        pb2.setTotalScore(dbTotalScore);
        pb2.setCorrectAnswers_low(intCorrectAnswers_low);
        pb2.setAnsweredProblems_low(intAnsweredProblems_low);   

        String correctAnswers_middle = (String)((SmartRandom3Form) form).getCorrectAnswers_middle();
        String answeredProblems_middle = (String)((SmartRandom3Form) form).getAnsweredProblems_middle();        
        int intCorrectAnswers_middle = Integer.parseInt(correctAnswers_middle);
        int intAnsweredProblems_middle = Integer.parseInt(answeredProblems_middle);       
        pb2.setCorrectAnswers_middle(intCorrectAnswers_middle);
        pb2.setAnsweredProblems_middle(intAnsweredProblems_middle); 

        String correctAnswers_high = (String)((SmartRandom3Form) form).getCorrectAnswers_high();
        String answeredProblems_high = (String)((SmartRandom3Form) form).getAnsweredProblems_high();        
        int intCorrectAnswers_high = Integer.parseInt(correctAnswers_high);
        int intAnsweredProblems_high = Integer.parseInt(answeredProblems_high);       
        pb2.setCorrectAnswers_high(intCorrectAnswers_high);
        pb2.setAnsweredProblems_high(intAnsweredProblems_high);         

        //String times = (String)((SmartRandom3Form) form).getTimes();
        //pb2.setTimes(times);
        pb2.setRandomNumber(new Integer(randomNumber).toString());          

        //now we are going to set up the timer
        if (first.equals("true")){
           Timer aTimer = new Timer(userName);
           MyTask myTask = new MyTask(userName);           
           aTimer.scheduleAtFixedRate(myTask, 0, 30000);
        }       

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
           pb2.setRemainSeconds(1800); //now we want this demonstration program not to end

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

        //below we want to find the student who needs an opportunity to answer this question
        HashSet candidateStudents = new HashSet();
        String scoreNumber = "score_1st";
        String sql = "";
        outer2:
        while(true){
           try{
              dataSource = getDataSource(request);
              myConnection = dataSource.getConnection();
              //Class.forName("org.hsqldb.jdbcDriver");
              //myConnection = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/xdb", "SA", "");

              sql = "select studentNo from students where source='Dongshan' and class=? and " + scoreNumber + "=?";
              stmt = myConnection.prepareStatement(sql);
              stmt.clearParameters();
              stmt.setString(1, classId); 
              stmt.setInt(2, -1);
              rs = stmt.executeQuery();           
              while (rs.next()){
                 tempUserName = rs.getString("studentNo");             
                 candidateStudents.add(tempUserName);             
              }

              candidateStudents.remove("2000");
              candidateStudents.remove("3000");
              candidateStudents.remove("3001");

              if (candidateStudents.size()==0){                    
                 if (scoreNumber.equals("score_1st"))
                    scoreNumber = "score_2nd";
                 else if (scoreNumber.equals("score_2nd"))
                    scoreNumber = "score_3rd";
                 else if (scoreNumber.equals("score_3rd"))
                    scoreNumber = "score_4th";
                 else if (scoreNumber.equals("score_4th"))
                    scoreNumber = "score_5th";
                 else if (scoreNumber.equals("score_5th"))
                    scoreNumber = "score_6th";
                 else if (scoreNumber.equals("score_6th"))
                    scoreNumber = "score_7th";
                 else if (scoreNumber.equals("score_7th"))
                    scoreNumber = "score_8th";
                 else if (scoreNumber.equals("score_8th"))
                    scoreNumber = "score_9th";
                 else if (scoreNumber.equals("score_9th"))
                    scoreNumber = "score_10th";
                 else if (scoreNumber.equals("score_10th"))
                    scoreNumber = "score_11th";
                 else if (scoreNumber.equals("score_11th"))
                    scoreNumber = "score_12th";
                 else if (scoreNumber.equals("score_12th"))
                    scoreNumber = "score_13th";
                 else if (scoreNumber.equals("score_13th"))
                    scoreNumber = "score_14th";
                 else if (scoreNumber.equals("score_14th"))
                    scoreNumber = "score_15th"; 
                 else if (scoreNumber.equals("score_15th"))
                    scoreNumber = "score_16th";
                 else if (scoreNumber.equals("score_16th"))
                    scoreNumber = "score_17th";
                 else if (scoreNumber.equals("score_17th"))
                    scoreNumber = "score_18th";
                 else if (scoreNumber.equals("score_18th"))
                    scoreNumber = "score_19th";
                 else if (scoreNumber.equals("score_19th"))
                    scoreNumber = "score_20th";
                 else if (scoreNumber.equals("score_20th"))
                    scoreNumber = "score_21st";
                 else if (scoreNumber.equals("score_21st"))
                    scoreNumber = "score_22nd";
                 else if (scoreNumber.equals("score_22nd"))
                    scoreNumber = "score_23rd";
                 else if (scoreNumber.equals("score_23rd"))
                    scoreNumber = "score_24th";
                 else if (scoreNumber.equals("score_24th"))
                    scoreNumber = "score_25th";
                 else if (scoreNumber.equals("score_25th"))
                    scoreNumber = "score_26th";
                 else if (scoreNumber.equals("score_26th"))
                    scoreNumber = "score_27th";
                 else if (scoreNumber.equals("score_27th"))
                    scoreNumber = "score_28th";
                 else if (scoreNumber.equals("score_28th"))
                    scoreNumber = "score_29th";
                 else if (scoreNumber.equals("score_29th"))
                    scoreNumber = "score_30th";
                 else if (scoreNumber.equals("score_30th"))
                    scoreNumber = "score_31st";
                 else if (scoreNumber.equals("score_31st"))
                    scoreNumber = "score_32nd";
                 else if (scoreNumber.equals("score_32nd"))
                    scoreNumber = "score_33rd";
                 else if (scoreNumber.equals("score_33rd"))
                    scoreNumber = "score_34th";
                 else if (scoreNumber.equals("score_34th"))
                    scoreNumber = "score_35th";
                 else if (scoreNumber.equals("score_35th"))
                    scoreNumber = "score_36th";
                 else if (scoreNumber.equals("score_36th"))
                    scoreNumber = "score_37th";
                 else if (scoreNumber.equals("score_37th"))
                    scoreNumber = "score_38th";
                 else if (scoreNumber.equals("score_38th"))
                    scoreNumber = "score_39th";
              }else{
                 break outer2;
              }
           
           }catch (SQLException ex){
              getServlet().log("Inside SmartRandom3Action.java, process.", ex);
           }finally{
              try{
                 if (rs != null) rs.close();
                 if (s != null) s.close();
                 if (stmt != null) stmt.close(); 
                 if (myConnection != null) myConnection.close();
              }catch (SQLException e){
                 getServlet().log("Inside SmartRandom3Action.java, closing.", e);
              }
           } 
        }

        //decide the student selected to answer the question
        int selectNumber = (int)(Math.random() * candidateStudents.size());                  
        Iterator iter = candidateStudents.iterator();
        int index = 0;
        while (iter.hasNext()) {
           if (index == selectNumber){
              tempUserName = (String)iter.next();
              System.out.println("inside SmartRandom3Action, the selected student = " + tempUserName);
              break;
           }
           iter.next();
           index++;                      
        }
         
        //get all the information about this student
        try{
           dataSource = getDataSource(request);
           myConnection = dataSource.getConnection();
           //Class.forName("org.hsqldb.jdbcDriver");
           //myConnection = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/xdb", "SA", "");
           s = myConnection.createStatement();
           sql = "select password from students where studentNo='" + tempUserName + "'";
           rs = s.executeQuery(sql);          
           while (rs.next()){
              tempPassword = rs.getString("password");              
           }

           s = myConnection.createStatement();
           rs = s.executeQuery("select playerId from student_score where studentNo='" + tempUserName + "'");          
           while (rs.next()){
              tempPlayerId = rs.getString("playerId");              
           }           
        }catch (SQLException ex){
           getServlet().log("Inside SmartRandomCheckAction.java, process.", ex);
        }finally{
           try{
              if (rs != null) rs.close();
              if (s != null) s.close();
              if (myConnection != null) myConnection.close();
           }catch (SQLException e){
              getServlet().log("Inside RandomCheckAction.java, closing.", e);
           }
        }
        pb2.setUserName(tempUserName);
        pb2.setPassWord(tempPassword); 
        pb2.setTrueName((String)StringSetTransfer.nameMap.get(tempUserName));
        pb2.setPlayerId(tempPlayerId);  

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
               return (mapping.findForward("ShowFillQuadBlank"));
            case 7:
               return (mapping.findForward("ShowFillTripleBlank")); 
            case 8:
               return (mapping.findForward("ShowMultiply"));
            case 9:
               return (mapping.findForward("ShowDivision")); 
            case 10:
               return (mapping.findForward("ShowPlusMinus"));     
            default:
               break;
        }          
        return (mapping.findForward("ShowSmartProblem"));        
    }
}

