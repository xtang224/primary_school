<%@ include file="taglibs.jsp" %>
<%@page pageEncoding="utf-8"%>

<html:html locale="true">
  <head>
    <title><bean:message key="hello.jsp.title"/></title>
    <html:base/>
  </head>


  <body bgcolor="white"><p>

    <h2><bean:message key="hello.jsp.page.heading"/></h2><p>

   <html:errors/><p> 

    <logic:present name="personbean" scope="request">
       <h2>
         <bean:message key="hello.jsp.page.hello"/>
         <bean:write name="personbean" property="trueName" /><bean:message key="hello.jsp.page.student"/><br>
         <bean:write name="personbean" property="changePassword" /><p>        
       </h2>
    </logic:present>    

    <jsp:useBean id="pb2" scope="session" class="hello.PersonBean" />
    <%! HttpSession session = null;        
        hello.PersonBean _pb2 = null;       
        String classId = "";

        java.sql.Connection myConnection = null;
        PreparedStatement stmt = null;  
        Statement s = null;      
        ResultSet rs = null;       

        String[] note;
        String[] meaning;
        int count = 0;
        int size = 0;
    %>
    <% session = request.getSession(true);        
       _pb2 = (hello.PersonBean)session.getAttribute(hello.Constants.PERSON_KEY);      
       classId = (String)session.getAttribute(hello.Constants.CLASS_KEY);        

       session.setAttribute(hello.Constants.PERSON_KEY, _pb2);       
       request.setAttribute(hello.Constants.PERSON_KEY, _pb2);  

       /*
       StringSetTransfer.init = false;
       if (StringSetTransfer.hs_low_used != null) StringSetTransfer.hs_low_used.clear();
       if (StringSetTransfer.hs_low != null) StringSetTransfer.hs_low.clear();
       if (StringSetTransfer.hs_low_tf_used != null) StringSetTransfer.hs_low_tf_used.clear();
       if (StringSetTransfer.hs_low_tf != null) StringSetTransfer.hs_low_tf.clear();
       if (StringSetTransfer.hs_middle_fb_used != null) StringSetTransfer.hs_middle_fb_used.clear();
       if (StringSetTransfer.hs_middle_fb != null) StringSetTransfer.hs_middle_fb.clear();
       if (StringSetTransfer.hs_high_fdb_used != null) StringSetTransfer.hs_high_fdb_used.clear();
       if (StringSetTransfer.hs_high_fdb != null) StringSetTransfer.hs_high_fdb.clear(); 
       if (StringSetTransfer.hs_high_ftb_used != null) StringSetTransfer.hs_high_ftb_used.clear();
       if (StringSetTransfer.hs_high_ftb != null) StringSetTransfer.hs_high_ftb.clear(); 
       if (StringSetTransfer.hs_high_fqb_used != null) StringSetTransfer.hs_high_fqb_used.clear();
       if (StringSetTransfer.hs_high_fqb != null) StringSetTransfer.hs_high_fqb.clear();    

       */

       try{
          //Class.forName("org.hsqldb.jdbcDriver");
          //myConnection = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/xdb", "SA", ""); 
          Class.forName("org.sqlite.JDBC");
          //myConnection = DriverManager.getConnection("jdbc:sqlite:/home/xtang/Downloads/sqlite_tools/databases/mi3/class_db");  
          //myConnection = DriverManager.getConnection("jdbc:sqlite:/mnt/data/mi3/class_db");   
          //myConnection = DriverManager.getConnection("jdbc:sqlite:F://Dongshan/mi3/class_db");
          myConnection = DriverManager.getConnection("jdbc:sqlite:D://Database_Server/mi3/class_db");      

          s = myConnection.createStatement();
          rs = s.executeQuery("select note, meaning from titles");           
          count = 0;            
          while(rs.next()){
             count ++;
          }

          size = count;
          note = new String[size];
          meaning = new String[size];
          count = 0;
          System.out.println("Inside checkLogin.jsp, size = " + size); 

          s = myConnection.createStatement();
          rs = s.executeQuery("select note, meaning from titles"); 
          count = 0;            
          while(rs.next()){
             note[count] = rs.getString(1);
             meaning[count] = rs.getString(2);             
             System.out.println("Inside checkLogin.jsp, note[" + count + "] = " + note[count]); 
             count ++;
          }
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
    %>

    <CENTER><H3><bean:message key="hello.jsp.prompt.title2"/></H3></CENTER>

    <CENTER><TABLE BGCOLOR="#D8D8D8" BORDER=2>
    <tr ALIGN=center>
       <th><bean:message key="hello.jsp.prompt.titleNote"/></th>
       <th><bean:message key="hello.jsp.prompt.titleMeaning"/></th>  
       <th><bean:message key="hello.jsp.prompt.clickEnter"/></th>       
    </tr>    
    <% for (count=0; count<size; count++) { %>
       <TR ALIGN=center><FONT COLOR="#FF5555">
          <TD><%= note[count] %></TD>
          <TD><%= meaning[count] %></TD>
          <TD><html:form action="/SmartRandom.do?first=true" >    
      <html:hidden property="times" value="FinalExam2"/>
      <html:hidden property="source" value="<%= note[count] %>"/>
      <html:hidden property="userName" value="<%= _pb2.getUserName()%>"/>
      <html:hidden property="passWord" value="<%= _pb2.getPassWord()%>"/>
      <html:hidden property="trueName" value="<%= _pb2.getTrueName()%>"/>
      <html:hidden property="lastCorrect" value="false"/>
      <html:hidden property="answeredProblems" value="0"/>
      <html:hidden property="correctAnswers" value="0"/>
      <html:hidden property="totalScore" value="0"/>
      <html:hidden property="correctAnswers_low" value="0"/>
      <html:hidden property="answeredProblems_low" value="0"/>
      <html:hidden property="correctAnswers_middle" value="0"/>
      <html:hidden property="answeredProblems_middle" value="0"/>
      <html:hidden property="correctAnswers_high" value="0"/>
      <html:hidden property="answeredProblems_high" value="0"/>
      <html:hidden property="lastType" value="L"/>
      <html:hidden property="thisType" value="L"/>
      <html:hidden property="continueRight" value="0"/>
      <html:hidden property="continueWrong" value="0"/>
      <html:hidden property="neverHigh" value="true"/>
      <html:hidden property="planStatus" value="not finished"/>
     <html:hidden property="classId" value="<%= classId%>"/>
      <html:submit property="submit" value="Submit to final exam"/>
      <html:reset/>
    </html:form></TD>          
       </FONT></TR>
    <% } %>
 
    </TABLE>
    </CENTER>

    <br>

    <html:form action="/SmartRandom.do?first=true" >
      <bean:message key="hello.jsp.prompt.quizTime"/><html:text property="times" size="16" maxlength="16" value="FinalExam2" /><br>
      <bean:message key="hello.jsp.prompt.source"/><html:text property="source" size="16" maxlength="16" /><br>
      <bean:message key="hello.jsp.prompt.smart.person"/><br>
      <html:hidden property="userName" value="<%= _pb2.getUserName()%>"/>
      <html:hidden property="passWord" value="<%= _pb2.getPassWord()%>"/>
      <html:hidden property="trueName" value="<%= _pb2.getTrueName()%>"/>
      <html:hidden property="lastCorrect" value="false"/>
      <html:hidden property="answeredProblems" value="0"/>
      <html:hidden property="correctAnswers" value="0"/>
      <html:hidden property="totalScore" value="0"/>
      <html:hidden property="correctAnswers_low" value="0"/>
      <html:hidden property="answeredProblems_low" value="0"/>
      <html:hidden property="correctAnswers_middle" value="0"/>
      <html:hidden property="answeredProblems_middle" value="0"/>
      <html:hidden property="correctAnswers_high" value="0"/>
      <html:hidden property="answeredProblems_high" value="0"/>
      <html:hidden property="lastType" value="L"/>
      <html:hidden property="thisType" value="L"/>
      <html:hidden property="continueRight" value="0"/>
      <html:hidden property="continueWrong" value="0"/>
      <html:hidden property="neverHigh" value="true"/>
      <html:hidden property="planStatus" value="not finished"/>
      <html:hidden property="classId" value="<%= classId%>"/>

      <html:submit property="submit" value="Submit to final exam"/>
      <html:reset/>
    </html:form><br>

    <html:form action="/SmartRandom5.do?first=true" >
      <bean:message key="hello.jsp.prompt.quizTime"/><html:text property="times" size="16" maxlength="16" value="FinalExam2" /><br>
      <bean:message key="hello.jsp.prompt.source"/><html:text property="source" size="16" maxlength="16" /><br>
      <bean:message key="hello.jsp.prompt.smart.person"/><br>
      <html:hidden property="userName" value="<%= _pb2.getUserName()%>"/>
      <html:hidden property="passWord" value="<%= _pb2.getPassWord()%>"/>
      <html:hidden property="trueName" value="<%= _pb2.getTrueName()%>"/>
      <html:hidden property="lastCorrect" value="false"/>
      <html:hidden property="answeredProblems" value="0"/>
      <html:hidden property="correctAnswers" value="0"/>
      <html:hidden property="totalScore" value="0"/>
      <html:hidden property="correctAnswers_low" value="0"/>
      <html:hidden property="answeredProblems_low" value="0"/>
      <html:hidden property="correctAnswers_middle" value="0"/>
      <html:hidden property="answeredProblems_middle" value="0"/>
      <html:hidden property="correctAnswers_high" value="0"/>
      <html:hidden property="answeredProblems_high" value="0"/>
      <html:hidden property="lastType" value="L"/>
      <html:hidden property="thisType" value="L"/>
      <html:hidden property="continueRight" value="0"/>
      <html:hidden property="continueWrong" value="0"/>
      <html:hidden property="neverHigh" value="true"/>
      <html:hidden property="planStatus" value="not finished"/>
      <html:hidden property="classId" value="<%= classId%>"/>

      <html:submit property="submit" value="Submit to final exam WO used problems"/>
      <html:reset/>
    </html:form><br>

    <html:form action="/SmartRandom3.do?first=true" >
      <bean:message key="hello.jsp.prompt.quizTimeCombine"/><html:text property="times" size="16" maxlength="16" value="FinalExam2" /><br>
      <bean:message key="hello.jsp.prompt.sourceCombine"/><html:text property="source" size="16" maxlength="16" /><br>
      <bean:message key="hello.jsp.prompt.smart.person"/><br>
      <html:hidden property="userName" value="<%= _pb2.getUserName()%>"/>
      <html:hidden property="passWord" value="<%= _pb2.getPassWord()%>"/>
      <html:hidden property="trueName" value="<%= _pb2.getTrueName()%>"/>
      <html:hidden property="lastCorrect" value="false"/>
      <html:hidden property="answeredProblems" value="0"/>
      <html:hidden property="correctAnswers" value="0"/>
      <html:hidden property="totalScore" value="0"/>
      <html:hidden property="correctAnswers_low" value="0"/>
      <html:hidden property="answeredProblems_low" value="0"/>
      <html:hidden property="correctAnswers_middle" value="0"/>
      <html:hidden property="answeredProblems_middle" value="0"/>
      <html:hidden property="correctAnswers_high" value="0"/>
      <html:hidden property="answeredProblems_high" value="0"/>
      <html:hidden property="lastType" value="L"/>
      <html:hidden property="thisType" value="L"/>
      <html:hidden property="continueRight" value="0"/>
      <html:hidden property="continueWrong" value="0"/>
      <html:hidden property="neverHigh" value="true"/>
      <html:hidden property="planStatus" value="not finished"/>
      <html:hidden property="classId" value="<%= classId%>"/>

      <html:submit property="submit" value="Submit to final exam"/>
      <html:reset/>

    </html:form><br>

    <html:form action="/SmartRandom4.do?first=true" >
      <bean:message key="hello.jsp.prompt.quizTimeCombineWOUsed"/><html:text property="times" size="16" maxlength="16" value="FinalExam2" /><br>
      <bean:message key="hello.jsp.prompt.sourceCombine"/><html:text property="source" size="16" maxlength="16" /><br>
      <bean:message key="hello.jsp.prompt.smart.person"/><br>
      <html:hidden property="userName" value="<%= _pb2.getUserName()%>"/>
      <html:hidden property="passWord" value="<%= _pb2.getPassWord()%>"/>
      <html:hidden property="trueName" value="<%= _pb2.getTrueName()%>"/>
      <html:hidden property="lastCorrect" value="false"/>
      <html:hidden property="answeredProblems" value="0"/>
      <html:hidden property="correctAnswers" value="0"/>
      <html:hidden property="totalScore" value="0"/>
      <html:hidden property="correctAnswers_low" value="0"/>
      <html:hidden property="answeredProblems_low" value="0"/>
      <html:hidden property="correctAnswers_middle" value="0"/>
      <html:hidden property="answeredProblems_middle" value="0"/>
      <html:hidden property="correctAnswers_high" value="0"/>
      <html:hidden property="answeredProblems_high" value="0"/>
      <html:hidden property="lastType" value="L"/>
      <html:hidden property="thisType" value="L"/>
      <html:hidden property="continueRight" value="0"/>
      <html:hidden property="continueWrong" value="0"/>
      <html:hidden property="neverHigh" value="true"/>
      <html:hidden property="planStatus" value="not finished"/>
      <html:hidden property="classId" value="<%= classId%>"/>

      <html:submit property="submit" value="Submit to final exam"/>
      <html:reset/>

    </html:form><br>

<HR>
    <bean:message key="hello.jsp.prompt.clearQuiz"/><br>
    <html:form action="/Clear.do?first=true" >
      <bean:message key="hello.jsp.prompt.quizTime"/><html:text property="times" size="16" maxlength="16" value="FinalExam2" /><br>
      <bean:message key="hello.jsp.prompt.source"/><html:text property="source" size="16" maxlength="16" value=""/><br>      
      <html:hidden property="userName" value="<%= _pb2.getUserName()%>"/>
      <html:hidden property="passWord" value="<%= _pb2.getPassWord()%>"/>      

      <html:submit property="submit" value="Submit"/>
      <html:reset/>

    </html:form><br>


<HR>
    <html:form action="/UpdateScore.do?first=true" >
      <bean:message key="hello.jsp.prompt.updateN2"/><html:text property="times" size="16" maxlength="16" value="1st" /><br>
      <bean:message key="hello.jsp.prompt.person"/><br>
      <html:hidden property="userName" value="<%= _pb2.getUserName()%>"/>
      <html:hidden property="passWord" value="<%= _pb2.getPassWord()%>"/>
      <html:hidden property="trueName" value="<%= _pb2.getTrueName()%>"/>      

      <html:submit property="submit" value="Submit to Update Those Students On Leave With Permission"/>
      <html:reset/>

    </html:form><br>
    
<HR>
   
    <h2><html:text property="changePassword" value="<%= _pb2.getChangePassword() %>" size="44" maxlength="100" /></h2><br>
    <h2><bean:message key="hello.jsp.page.changePassword"/></h2><p>           
       
    <html:form action="/Password.do" >
      
      <bean:message key="hello.jsp.prompt.oldPassword"/><html:password property="oldPassword" size="16" maxlength="16"/>
      <br>  
      <bean:message key="hello.jsp.prompt.newPassword"/><html:password property="newPassword" size="16" maxlength="16"/>
      <br>  
      <bean:message key="hello.jsp.prompt.confirmNewPassword"/>
      <html:password property="confirmNewPassword" size="16" maxlength="16"/>
      <br>
      <html:hidden property="userName" value="<%= _pb2.getUserName()%>"/>
      <html:hidden property="passWord" value="<%= _pb2.getPassWord()%>"/>
      <html:hidden property="trueName" value="<%= _pb2.getTrueName()%>"/>

      <html:submit property="submit" value="Submit"/>
      <html:reset/>

    </html:form><br>

    <html:img page="/struts-power.gif" alt="Powered by Struts"/>

  </body>
</html:html>
