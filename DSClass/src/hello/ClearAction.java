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

public final class ClearAction extends Action {

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
        String userName = (String)((ClearForm) form).getUserName();
        String passWord = (String)((ClearForm) form).getPassWord();
        String source = (String)((ClearForm) form).getSource();      
        String times = (String)((ClearForm) form).getTimes();        

        javax.sql.DataSource dataSource = null;
        java.sql.Connection myConnection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;        
        PersonBean pb2 = new PersonBean();
        pb2.setUserName(userName);
        pb2.setPassWord(passWord);
       
        try {
           dataSource = getDataSource(request);
           myConnection = dataSource.getConnection();
           //Class.forName("org.hsqldb.jdbcDriver");
           //myConnection = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/xdb", "SA", "");             
           
           stmt = myConnection.prepareStatement("select name from students where studentNo=?");
           stmt.clearParameters();
           stmt.setString(1, userName);
           rs = stmt.executeQuery();
           while(rs.next()){
              pb2.setTrueName(rs.getString(1));
           }

           //below we are going to clear all used problems
           stmt = myConnection.prepareStatement("delete from usedQuestions2_test where source=?");
           stmt.clearParameters();            
           stmt.setString(1, source);  
           stmt.executeUpdate();

           stmt = myConnection.prepareStatement("delete from usedChoices2_test where source=?");
           stmt.clearParameters();            
           stmt.setString(1, source);  
           stmt.executeUpdate();          

           stmt = myConnection.prepareStatement("delete from usedFillBlank2_test where source=?");
           stmt.clearParameters();           
           stmt.setString(1, source);  
           stmt.executeUpdate();

           stmt = myConnection.prepareStatement("delete from usedFillDoubleBlanks2_test where source=?");
           stmt.clearParameters();           
           stmt.setString(1, source);  
           stmt.executeUpdate();

           stmt = myConnection.prepareStatement("delete from usedFillTripleBlanks2_test where source=?");
           stmt.clearParameters();            
           stmt.setString(1, source);  
           stmt.executeUpdate();

           stmt = myConnection.prepareStatement("delete from usedFillQuadBlanks2_test where source=?");
           stmt.clearParameters();           
           stmt.setString(1, source);  
           stmt.executeUpdate();

           stmt = myConnection.prepareStatement("delete from usedMultiply_test where source=?");
           stmt.clearParameters();           
           stmt.setString(1, source);  
           stmt.executeUpdate(); 

           stmt = myConnection.prepareStatement("delete from usedDivision_test where source=?");
           stmt.clearParameters();           
           stmt.setString(1, source);  
           stmt.executeUpdate(); 

           stmt = myConnection.prepareStatement("delete from usedPlusMinus where source=?");
           stmt.clearParameters();           
           stmt.setString(1, source);  
           stmt.executeUpdate();

           /*  
           stmt = myConnection.prepareStatement("delete from usedMultipleQuestions2_test where studentNo=? and source=?");
           stmt.clearParameters();
           stmt.setString(1, userName);  
           stmt.setString(2, source);  
           stmt.executeUpdate();          

           stmt = myConnection.prepareStatement("delete from usedComposition_test where studentNo=? and source=?");
           stmt.clearParameters();
           stmt.setString(1, userName);  
           stmt.setString(2, source);  
           stmt.executeUpdate(); 

           //now we want to mark this clearing
           stmt = myConnection.prepareStatement("update student_time set clearTimes=?, source=? where studentNo=?");
           stmt.clearParameters();
           stmt.setInt(1, 1); 
           stmt.setString(2, source);  
           stmt.setString(3, userName);  
           stmt.executeUpdate(); 
           */
           System.out.println("Now we are at the end of the try block of ClearAction, this means all clear actions are successes!");
           
        } catch (SQLException sqle) {
           getServlet().log("Connection.process", sqle);
        } finally {
           //enclose this in a finally block to make
           //sure the connection is closed
           try {
              rs.close();
              stmt.close();
              myConnection.close();
           } catch (SQLException e) {
             getServlet().log("Connection.close", e);
         }
       }
   
        request.removeAttribute(mapping.getAttribute());
        session.setAttribute( Constants.PERSON_KEY, pb2); 
        request.setAttribute( Constants.PERSON_KEY, pb2);    
              
        return (mapping.findForward("ClearDone"));       
        
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
        
    }
}
