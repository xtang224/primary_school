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
  
   <%! HttpSession session = null;        
       hello.PersonBean _pb2 = null; 
       java.sql.Connection myConnection = null;
       PreparedStatement stmt = null;  
       Statement s = null;      
       ResultSet rs = null;
       String studentHint = null;     
       String allRandomHint = "";  
       String allGenHint = "";  
       String clearHint = "";

       String[] note;
       String[] meaning;
       int count = 0;
       int size = 0;
       String titleHint = "";
       String noteHint = "";
       String meaningHint = "";
       String sourceHint = "";
       String submitHint = ""; 
   %>
 
   <%
       StringSetTransfer.init = false;
       if (StringSetTransfer.hs_low_used != null) StringSetTransfer.hs_low_used.clear();
       if (StringSetTransfer.hs_low != null) StringSetTransfer.hs_low.clear();
       if (StringSetTransfer.hs_low_tf_used != null) StringSetTransfer.hs_low_tf_used.clear();
       if (StringSetTransfer.hs_low_tf != null) StringSetTransfer.hs_low_tf.clear();
       if (StringSetTransfer.mhs_high_used != null) StringSetTransfer.mhs_high_used.clear();
       if (StringSetTransfer.mhs_high != null) StringSetTransfer.mhs_high.clear();
       if (StringSetTransfer.hs_middle_fb_used != null) StringSetTransfer.hs_middle_fb_used.clear();
       if (StringSetTransfer.hs_middle_fb != null) StringSetTransfer.hs_middle_fb.clear();
       if (StringSetTransfer.hs_high_fdb_used != null) StringSetTransfer.hs_high_fdb_used.clear();
       if (StringSetTransfer.hs_high_fdb != null) StringSetTransfer.hs_high_fdb.clear();
       if (StringSetTransfer.hs_middle_tm_used != null) StringSetTransfer.hs_middle_tm_used.clear();
       if (StringSetTransfer.hs_middle_tm != null) StringSetTransfer.hs_middle_tm.clear();
       if (StringSetTransfer.hs_high_m_used != null) StringSetTransfer.hs_high_m_used.clear();
       if (StringSetTransfer.hs_high_m != null) StringSetTransfer.hs_high_m.clear();
       if (StringSetTransfer.hs_high_d_used != null) StringSetTransfer.hs_high_d_used.clear();
       if (StringSetTransfer.hs_high_d != null) StringSetTransfer.hs_high_d.clear();
       if (StringSetTransfer.hs_high_c2_used != null) StringSetTransfer.hs_high_c2_used.clear();
       if (StringSetTransfer.hs_high_c2 != null) StringSetTransfer.hs_high_c2.clear();

       try{
          Class.forName("org.hsqldb.jdbcDriver");
          myConnection = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/xdb", "SA", ""); 

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

          stmt = myConnection.prepareStatement("select term2 from hintMatch where term1=?");
          stmt.clearParameters();
          stmt.setString(1, "student");
          rs = stmt.executeQuery();              
          while(rs.next()){
             studentHint = rs.getString(1);
          }   

          stmt = myConnection.prepareStatement("select term2 from hintMatch where term1=?");
          stmt.clearParameters();
          stmt.setString(1, "allRandomHint");
          rs = stmt.executeQuery();              
          while(rs.next()){
             allRandomHint = rs.getString(1);
          } 

          stmt = myConnection.prepareStatement("select term2 from hintMatch where term1=?");
          stmt.clearParameters();
          stmt.setString(1, "allGenHint");
          rs = stmt.executeQuery();              
          while(rs.next()){
             allGenHint = rs.getString(1);
          }      

          stmt = myConnection.prepareStatement("select term2 from hintMatch where term1=?");
          stmt.clearParameters();
          stmt.setString(1, "clearHint");
          rs = stmt.executeQuery();              
          while(rs.next()){
             clearHint = rs.getString(1);
          } 

          stmt = myConnection.prepareStatement("select term2 from hintMatch where term1=?");
          stmt.clearParameters();
          stmt.setString(1, "titleHint");
          rs = stmt.executeQuery();              
          while(rs.next()){
             titleHint = rs.getString(1);
          }

          stmt = myConnection.prepareStatement("select term2 from hintMatch where term1=?");
          stmt.clearParameters();
          stmt.setString(1, "noteHint");
          rs = stmt.executeQuery();              
          while(rs.next()){
             noteHint = rs.getString(1);
          } 

          stmt = myConnection.prepareStatement("select term2 from hintMatch where term1=?");
          stmt.clearParameters();
          stmt.setString(1, "meaningHint");
          rs = stmt.executeQuery();              
          while(rs.next()){
             meaningHint = rs.getString(1);
          }

          stmt = myConnection.prepareStatement("select term2 from hintMatch where term1=?");
          stmt.clearParameters();
          stmt.setString(1, "sourceHint");
          rs = stmt.executeQuery();              
          while(rs.next()){
             sourceHint = rs.getString(1);
          }

          stmt = myConnection.prepareStatement("select term2 from hintMatch where term1=?");
          stmt.clearParameters();
          stmt.setString(1, "submitHint");
          rs = stmt.executeQuery();              
          while(rs.next()){
             submitHint = rs.getString(1);
          }

          //clear the inputs for the game  
          stmt = myConnection.prepareStatement("update hintMatch set term2=? where term1=?");
          stmt.clearParameters();
          stmt.setString(1, ""); 
          stmt.setString(2, "gameOpponentInput");
          stmt.executeUpdate(); 

          stmt = myConnection.prepareStatement("update hintMatch set term2=? where term1=?");
          stmt.clearParameters();
          stmt.setString(1, ""); 
          stmt.setString(2, "gameYourInput");
          stmt.executeUpdate(); 
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

       if (allRandomHint == null)
          allRandomHint = "";   
   %>   

    <logic:present name="personbean" scope="request">
       <h2>
         <bean:message key="hello.jsp.page.hello"/>
         <bean:write name="personbean" property="trueName" />
       </h2>
    </logic:present>
    

    <logic:present name="personbean" scope="request">
       <h2>
         <bean:write name="personbean" property="changePassword" /><p>        
       </h2>
    </logic:present>    

    <jsp:useBean id="pb2" scope="session" class="hello.PersonBean" />
    
    <% session = request.getSession(true);        
       _pb2 = (hello.PersonBean)session.getAttribute(hello.Constants.PERSON_KEY);      
       
       session.setAttribute(hello.Constants.PERSON_KEY, _pb2);       
       request.setAttribute(hello.Constants.PERSON_KEY, _pb2);     
    %>
<!--
    <html:form action="/Random.do?first=true" >
      <bean:message key="hello.jsp.prompt.quizTime"/><html:text property="times" size="16" maxlength="16" value="1st" /><br>
      <bean:message key="hello.jsp.prompt.person"/><br>
      <html:hidden property="userName" value="<%= _pb2.getUserName()%>"/>
      <html:hidden property="passWord" value="<%= _pb2.getPassWord()%>"/>
      <html:hidden property="trueName" value="<%= _pb2.getTrueName()%>"/>
      <html:hidden property="questionUsedUp" value="<%= _pb2.getQuestionUsedUp()%>"/>

      <html:submit property="submit" value="Submit to normal random quiz"/>
      <html:reset/>

    </html:form><br>
   <HR>
-->

     <CENTER><H3><%= titleHint%></H3></CENTER>

    <CENTER><TABLE BGCOLOR="#D8D8D8" BORDER=2>
    <tr ALIGN=center>
       <th><%= noteHint%></th>
       <th><%= meaningHint%></th>       
    </tr>    
    <% for (count=0; count<size; count++) { %>
       <TR ALIGN=center><FONT COLOR="#FF5555">
          <TD><%= note[count] %></TD>
          <TD><%= meaning[count] %></TD>          
       </FONT></TR>
    <% } %>
 
    </TABLE>
    </CENTER>

    <html:form action="/SmartRandom.do?first=true" >
      <bean:message key="hello.jsp.prompt.quizTime"/><html:text property="times" size="16" maxlength="16" value="FinalExam2" /><br>
      <%= sourceHint%><html:text property="source" size="16" maxlength="16" value="ch2"/><br>
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

      <html:submit property="submit" value="<%= submitHint%>"/>
      <html:reset/>

    </html:form><br>

   <%= allRandomHint%>
   <html:form action="/SmartRandomAll.do?first=true" >
      <bean:message key="hello.jsp.prompt.quizTime"/><html:text property="times" size="16" maxlength="16" value="FinalExam2" /><br>
      <bean:message key="hello.jsp.prompt.source"/><html:text property="source" size="16" maxlength="16" value="random"/><br>
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

      <html:submit property="submit" value="Submit to final exam"/>
      <html:reset/>

    </html:form><br>

   <%= allGenHint%>
   <html:form action="/SmartRandomGen.do?first=true" >
      <bean:message key="hello.jsp.prompt.quizTime"/><html:text property="times" size="16" maxlength="16" value="FinalExam2" /><br>
      <bean:message key="hello.jsp.prompt.source"/><html:text property="source" size="16" maxlength="16" value="mid_gen"/><br>
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

      <html:submit property="submit" value="Submit to final exam"/>
      <html:reset/>

    </html:form><br>

    <%= clearHint%>
    <html:form action="/Clear.do?first=true" >
      <bean:message key="hello.jsp.prompt.quizTime"/><html:text property="times" size="16" maxlength="16" value="FinalExam2" /><br>
      <bean:message key="hello.jsp.prompt.source"/><html:text property="source" size="16" maxlength="16" value="mid_gen"/><br>
      <bean:message key="hello.jsp.prompt.smart.person"/><br>
      <html:hidden property="userName" value="<%= _pb2.getUserName()%>"/>
      <html:hidden property="passWord" value="<%= _pb2.getPassWord()%>"/>      

      <html:submit property="submit" value="Submit to clear used problems"/>
      <html:reset/>

    </html:form><br>

<HR>
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
