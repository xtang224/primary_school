<%@ include file="taglibs.jsp" %>

<HTML>
    <HEAD>
	<TITLE> Random Test End Page </TITLE> 			
    </HEAD>

<BODY>
    <CENTER><H1>Random Test End Page</H1><P>
    <CENTER><P>

    <logic:present name="personbean" scope="session">
       <h2>
         <bean:message key="hello.jsp.page.hello"/>
         <bean:write name="personbean" property="trueName" /><bean:message key="hello.jsp.page.student"/><p>  
     </h2>
    </logic:present>

<%! HttpSession session = null;         
    hello.PersonBean _pb2 = null;
   
    java.sql.Connection myConnection = null;
    PreparedStatement stmt = null;        
    ResultSet rs = null;
    String userNameHint = null;
    String playerIdHint = null;
    String trueNameHint = null;
    String totalScoreHint = null;
    String problemAnswerHint = null;
    String problemCorrectHint = null;
    String lowTypeAnswerHint = null;
    String lowTypeCorrectHint = null;
    String middleTypeAnswerHint = null;
    String middleTypeCorrectHint = null;
    String highTypeAnswerHint = null;
    String highTypeCorrectHint = null;

    String scoreInfoHint = null;
    String inputHint4 = null;
%>

<% session = request.getSession(true);            
   _pb2 = (hello.PersonBean)request.getAttribute(hello.Constants.PERSON_KEY);

   try{
          Class.forName("org.hsqldb.jdbcDriver");
          myConnection = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/xdb", "SA", ""); 

          stmt = myConnection.prepareStatement("select term2 from hintMatch where term1=?");
          stmt.clearParameters();
          stmt.setString(1, "userName");
          rs = stmt.executeQuery();              
          while(rs.next()){
             userNameHint = rs.getString(1);
          }

          stmt = myConnection.prepareStatement("select term2 from hintMatch where term1=?");
          stmt.clearParameters();
          stmt.setString(1, "playerId");
          rs = stmt.executeQuery();              
          while(rs.next()){
             playerIdHint = rs.getString(1);
          }

          stmt = myConnection.prepareStatement("select term2 from hintMatch where term1=?");
          stmt.clearParameters();
          stmt.setString(1, "trueName");
          rs = stmt.executeQuery();              
          while(rs.next()){
             trueNameHint = rs.getString(1);
          }

          stmt = myConnection.prepareStatement("select term2 from hintMatch where term1=?");
          stmt.clearParameters();
          stmt.setString(1, "totalScore");
          rs = stmt.executeQuery();              
          while(rs.next()){
             totalScoreHint = rs.getString(1);
          }

          stmt = myConnection.prepareStatement("select term2 from hintMatch where term1=?");
          stmt.clearParameters();
          stmt.setString(1, "problemAnswer");
          rs = stmt.executeQuery();              
          while(rs.next()){
             problemAnswerHint = rs.getString(1);
          }

          stmt = myConnection.prepareStatement("select term2 from hintMatch where term1=?");
          stmt.clearParameters();
          stmt.setString(1, "problemCorrect");
          rs = stmt.executeQuery();              
          while(rs.next()){
             problemCorrectHint = rs.getString(1);
          }

          stmt = myConnection.prepareStatement("select term2 from hintMatch where term1=?");
          stmt.clearParameters();
          stmt.setString(1, "lowTypeAnswer");
          rs = stmt.executeQuery();              
          while(rs.next()){
             lowTypeAnswerHint = rs.getString(1);
          }

          stmt = myConnection.prepareStatement("select term2 from hintMatch where term1=?");
          stmt.clearParameters();
          stmt.setString(1, "lowTypeCorrect");
          rs = stmt.executeQuery();              
          while(rs.next()){
             lowTypeCorrectHint = rs.getString(1);
          }

          stmt = myConnection.prepareStatement("select term2 from hintMatch where term1=?");
          stmt.clearParameters();
          stmt.setString(1, "middleTypeAnswer");
          rs = stmt.executeQuery();              
          while(rs.next()){
             middleTypeAnswerHint = rs.getString(1);
          }

          stmt = myConnection.prepareStatement("select term2 from hintMatch where term1=?");
          stmt.clearParameters();
          stmt.setString(1, "middleTypeCorrect");
          rs = stmt.executeQuery();              
          while(rs.next()){
             middleTypeCorrectHint = rs.getString(1);
          }

          stmt = myConnection.prepareStatement("select term2 from hintMatch where term1=?");
          stmt.clearParameters();
          stmt.setString(1, "highTypeAnswer");
          rs = stmt.executeQuery();              
          while(rs.next()){
             highTypeAnswerHint = rs.getString(1);
          }

          stmt = myConnection.prepareStatement("select term2 from hintMatch where term1=?");
          stmt.clearParameters();
          stmt.setString(1, "highTypeCorrect");
          rs = stmt.executeQuery();              
          while(rs.next()){
             highTypeCorrectHint = rs.getString(1);
          }

          stmt = myConnection.prepareStatement("select term2 from hintMatch where term1=?");
          stmt.clearParameters();
          stmt.setString(1, "scoreInfo");
          rs = stmt.executeQuery();              
          while(rs.next()){
             scoreInfoHint = rs.getString(1);
          }

          stmt = myConnection.prepareStatement("select term2 from hintMatch where term1=?");
          stmt.clearParameters();
          stmt.setString(1, "inputHint4");
          rs = stmt.executeQuery();              
          while(rs.next()){
             inputHint4 = rs.getString(1);
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

    <CENTER><H3><%= scoreInfoHint%></H3></CENTER><br>

    <CENTER><TABLE BGCOLOR="#D8D8D8" BORDER=2>
    <tr ALIGN=center>
       <th><%= userNameHint%></th>
       <th><%= playerIdHint%></th>
       <th><%= trueNameHint%></th>
       <th><%= totalScoreHint%></th>
       <th><%= problemAnswerHint%></th>
       <th><%= problemCorrectHint%></th>
       <th><%= lowTypeAnswerHint%></th>
       <th><%= lowTypeCorrectHint%></th>
       <th><%= middleTypeAnswerHint%></th>
       <th><%= middleTypeCorrectHint%></th>
       <th><%= highTypeAnswerHint%></th>
       <th><%= highTypeCorrectHint%></th>
    </tr>
    <TR ALIGN=center><FONT COLOR="#FF5555">
        <TD><%= _pb2.getUserName() %></TD>
        <TD><%= _pb2.getPlayerId() %></TD>
        <TD><%= _pb2.getTrueName() %></TD>
        <TD><%= _pb2.getTotalScore() %></TD>
        <TD><%= _pb2.getAnsweredProblems() %></TD>
        <TD><%= _pb2.getCorrectAnswers() %></TD>
        <TD><%= _pb2.getAnsweredProblems_low() %></TD>
        <TD><%= _pb2.getCorrectAnswers_low() %></TD>
        <TD><%= _pb2.getAnsweredProblems_middle() %></TD>
        <TD><%= _pb2.getCorrectAnswers_middle() %></TD>
        <TD><%= _pb2.getAnsweredProblems_high() %></TD>
        <TD><%= _pb2.getCorrectAnswers_high() %></TD>
    </FONT></TR>
    </TABLE>
    </CENTER>    

    <HR WIDTH=60%><BR>
    <!--
    <logic:present name="personbean" scope="session">
       <h2>
         <bean:message key="hello.jsp.page.hello"/>
         <bean:write name="personbean" property="trueName" /><bean:message key="hello.jsp.page.student"/><p>  
         <bean:message key="hello.jsp.page.lastCorrect"/>
         <bean:write name="personbean" property="lastCorrect" /><p>      
        <bean:message key="hello.jsp.page.answeredProblems"/>
         <bean:write name="personbean" property="answeredProblems" /><p>         
         <bean:message key="hello.jsp.page.correctAnswers"/>
         <bean:write name="personbean" property="correctAnswers" /><p>     
         <bean:message key="hello.jsp.page.answeredProblems_low"/>
         <bean:write name="personbean" property="answeredProblems_low" /><br> 
         <bean:message key="hello.jsp.page.correctAnswers_low"/>
         <bean:write name="personbean" property="correctAnswers_low" /><br>   
         <bean:message key="hello.jsp.page.answeredProblems_middle"/>
         <bean:write name="personbean" property="answeredProblems_middle" /><br> 
         <bean:message key="hello.jsp.page.correctAnswers_middle"/>
         <bean:write name="personbean" property="correctAnswers_middle" /><br>            
         <bean:message key="hello.jsp.page.answeredProblems_high"/>
         <bean:write name="personbean" property="answeredProblems_high" /><br> 
         <bean:message key="hello.jsp.page.correctAnswers_high"/>
         <bean:write name="personbean" property="correctAnswers_high" /><br>      
         <bean:message key="hello.jsp.page.totalScore"/>
         <bean:write name="personbean" property="totalScore" /><br>            
       </h2>
    </logic:present>
    -->
    <HR WIDTH=70%><BR>   
   
   <!--
    <CENTER><H3>If you want to view the scores of the entire class, please type "yes" in the following box, otherwise type "no" in the following box, and then click the "submit" button</H3><P>
    <html:form action="/Score.do?first=true" >
      <bean:message key="hello.jsp.prompt.vote"/><html:text property="vote" size="16" maxlength="16"  /><br>
      <html:hidden property="userName" value="<%= _pb2.getUserName()%>"/>
      <html:hidden property="trueName" value="<%= _pb2.getTrueName()%>"/>
      <html:hidden property="playerId" value="<%= _pb2.getPlayerId()%>"/>
      <html:submit property="submit" value="Submit"/>
      <html:reset/>
    </html:form><br>  
  --> 

     <CENTER><H3><a href="javascript:window.open('AppletExample/DrawFlagApplet2.html')"><%= inputHint4%></a></H3></CENTER><P>
  
</BODY>
</HTML>
