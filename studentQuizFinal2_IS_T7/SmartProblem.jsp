<%@ include file="taglibs.jsp" %>

<html:html locale="true">
  <head>   
    <html:base/>
  </head>
  <body bgcolor="white"><p>    

   <html:errors/><p> 

    <logic:present name="problembean" scope="session">
       <h2>
         <bean:message key="hello.jsp.page.problem"/>
         <bean:write name="problembean" property="id" /><p>
         <bean:write name="problembean" property="type" /><p> 
         <bean:write name="problembean" property="statement" /><p>
         <bean:write name="problembean" property="choiceA" /><p>
         <bean:write name="problembean" property="choiceB" /><p>
         <bean:write name="problembean" property="choiceC" /><p>
         <bean:write name="problembean" property="choiceD" /><p>
       </h2>
    </logic:present>  
  
    <%! HttpSession session = null; 
        hello.SmartProblemBean _pb = null;
        hello.PersonBean _pb2 = null;
        String _hs = null;
        String _hs_tf = null;
        String _mhs = null;
        String _hs_fb = null;
        String _hs_fdb = null; 
        String _hs_m = null;
        String _hs_tm = null;   
        String _hs_d = null;
        String _hs_c2 = null;
        int remainSeconds = 300;
        int id = 0;

        java.sql.Connection myConnection = null;
        PreparedStatement stmt = null;        
        ResultSet rs = null;
        String problemHint = null;
        String strExit = null;
    %>
    <% session = request.getSession(true);     
       _pb = (hello.SmartProblemBean)request.getAttribute(hello.Constants.PROBLEM_KEY);
       _pb2 = (hello.PersonBean)request.getAttribute(hello.Constants.PERSON_KEY);
       _hs = (String)request.getAttribute(hello.Constants.HASHSET_KEY);
       _hs_tf = (String)request.getAttribute(hello.Constants.HASHSET_TF_KEY);
       _mhs = (String)request.getAttribute(hello.Constants.HASHSET_M_KEY);
       _hs_fb = (String)request.getAttribute(hello.Constants.HASHSET_FB_KEY);
       _hs_fdb = (String)request.getAttribute(hello.Constants.HASHSET_FDB_KEY);
       _hs_m = (String)request.getAttribute(hello.Constants.HASHSET_MY_KEY); 
       _hs_tm = (String)request.getAttribute(hello.Constants.HASHSET_TM_KEY);
       _hs_d = (String)request.getAttribute(hello.Constants.HASHSET_D_KEY); 
       _hs_c2 = (String)request.getAttribute(hello.Constants.HASHSET_C2_KEY);

       id = _pb2.getCurrentProblemId(); 

       try{
          Class.forName("org.hsqldb.jdbcDriver");
          myConnection = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/xdb", "SA", ""); 
          stmt = myConnection.prepareStatement("select term2 from hintMatch where term1=?");
          stmt.clearParameters();
          stmt.setString(1, "problem");
          rs = stmt.executeQuery();              
          while(rs.next()){
             problemHint = rs.getString(1);
          }

          stmt = myConnection.prepareStatement("select term2 from hintMatch where term1=?");
          stmt.clearParameters();
          stmt.setString(1, "exit");
          rs = stmt.executeQuery();              
          while(rs.next()){
             strExit = rs.getString(1);
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

    <% if (id==40){ %>
        <html:img page="/pic/ch3_test9_q40.PNG" alt="Powered by Struts"/>
    <% } else if (id==76){ %>
        <html:img page="/pic/mid_2_test2_q76.PNG" alt="Powered by Struts"/>
    <% } else if (id==78){ %>
        <html:img page="/pic/ch3_4_test2_q78.PNG" alt="Powered by Struts"/>
    <% } else if (id==136){ %>
        <html:img page="/pic/ch5_3_test1_q136.PNG" alt="Powered by Struts"/>
    <% } else if (id==138){ %>
        <html:img page="/pic/ch5_4_test1_q138.PNG" alt="Powered by Struts"/>
    <% } else if (id==141){ %>
        <html:img page="/pic/ch5_5_test1_q141.PNG" alt="Powered by Struts"/>
    <% } else if (id==160){ %>
        <html:img page="/pic/ch5_test3_q160.PNG" alt="Powered by Struts"/>
    <% } else if (id==162){ %>
        <html:img page="/pic/ch5_test4_q162.PNG" alt="Powered by Struts"/>
    <% } else if (id==164){ %>
        <html:img page="/pic/ch5_test5_q164.PNG" alt="Powered by Struts"/>
    <% } else if (id==165){ %>
        <html:img page="/pic/ch5_test6_q165.PNG" alt="Powered by Struts"/>
    <% } else if (id==240){ %>
        <html:img page="/pic/ch6_5_test3_q240.PNG" alt="Powered by Struts"/>
    <% } else if (id==241){ %>
        <html:img page="/pic/ch6_5_test4_q241.PNG" alt="Powered by Struts"/>
    <% } else if (id==242){ %>
        <html:img page="/pic/ch6_5_test5_q242.PNG" alt="Powered by Struts"/>
    <% } else if (id==243){ %>
        <html:img page="/pic/ch6_5_test6_q243.PNG" alt="Powered by Struts"/>
    <% } else if (id==263){ %>
        <html:img page="/pic/ch6_self_test1_q263.PNG" alt="Powered by Struts"/>
    <% } else if (id==264){ %>
        <html:img page="/pic/ch6_self_test2_q264.PNG" alt="Powered by Struts"/>
    <% } else if (id==265){ %>
        <html:img page="/pic/ch6_self_test3_q265.PNG" alt="Powered by Struts"/>
    <% } else if (id==292) { %>
       <html:img page="/pic/ch6_test1_q292.PNG" alt="Powered by Struts"/>
    <% } else if (id==293) { %>
       <html:img page="/pic/ch6_test2_q293.PNG" alt="Powered by Struts"/>
    <% } else if (id==297) { %>
       <html:img page="/pic/ch56_test3_q297.PNG" alt="Powered by Struts"/>
    <% } else if (id==352) { %>
       <html:img page="/pic/final_1_test1_q352.png" alt="Powered by Struts"/>
    <% } else if (id==358) { %>
       <html:img page="/pic/final_2_test2_q358.png" alt="Powered by Struts"/>
    <% } else if (id==359) { %>
       <html:img page="/pic/ch7_test3_q359.png" alt="Powered by Struts"/>
    <% } else if (id==372) { %>
       <html:img page="/pic/final_4_test2_q372.png" alt="Powered by Struts"/>
    <% } else if (id==389) { %>
       <html:img page="/pic/final_5_test4_q389.png" alt="Powered by Struts"/>
    <% } else if (id==392) { %>
       <html:img page="/pic/final_5_test5_q392.png" alt="Powered by Struts"/>
    <% } else if (id==405) { %>
       <html:img page="/pic/final_6_test1_q405.png" alt="Powered by Struts"/>
    <% } %>

   

    <% remainSeconds = _pb2.getRemainSeconds();
       if (remainSeconds > 0 && _pb2.getPlanStatus().equals("not finished")){ 
    %>

    <html:form action="/SmartRandomCheck.do" >

      <%= problemHint%>
      <html:text property="inputAnswer" size="16" maxlength="16"/><br>
      <html:hidden property="correctAnswer" value="<%= _pb.getCorrectChoice()%>"/>
      <html:hidden property="solution" value="<%= _pb.getSolution()%>"/>
      <html:hidden property="source" value="<%= _pb.getSource()%>"/>
      <html:hidden property="answeredProblems" value="<%= (new Integer(_pb2.getAnsweredProblems()).toString())%>"/>
      <html:hidden property="correctAnswers" value="<%= (new Integer(_pb2.getCorrectAnswers()).toString())%>"/>
      <html:hidden property="totalScore" value="<%= (new Double(_pb2.getTotalScore()).toString())%>"/>
      <html:hidden property="correctAnswers_low" value="<%= (new Integer(_pb2.getCorrectAnswers_low()).toString())%>"/>
      <html:hidden property="answeredProblems_low" value="<%= (new Integer(_pb2.getAnsweredProblems_low()).toString())%>"/>
 <html:hidden property="correctAnswers_middle" value="<%= (new Integer(_pb2.getCorrectAnswers_middle()).toString())%>"/>
 <html:hidden property="answeredProblems_middle" value="<%= (new Integer(_pb2.getAnsweredProblems_middle()).toString())%>"/>
     <html:hidden property="correctAnswers_high" value="<%= (new Integer(_pb2.getCorrectAnswers_high()).toString())%>"/>
     <html:hidden property="answeredProblems_high" value="<%= (new Integer(_pb2.getAnsweredProblems_high()).toString())%>"/>
      <html:hidden property="answeredHashSet" value="<%= _hs%>"/>
      <html:hidden property="answeredHashSet_tf" value="<%= _hs_tf%>"/>
      <html:hidden property="answered_M_HashSet" value="<%= _mhs%>"/>
      <html:hidden property="answeredHashSet_fb" value="<%= _hs_fb%>"/>
      <html:hidden property="answeredHashSet_fdb" value="<%= _hs_fdb%>"/>
      <html:hidden property="answered_MY_HashSet" value="<%= _hs_m%>"/>
      <html:hidden property="answeredHashSet_tm" value="<%= _hs_tm%>"/>
      <html:hidden property="answered_D_HashSet" value="<%= _hs_d%>"/>
      <html:hidden property="answered_C_HashSet" value="<%= _hs_c2%>"/>
      <html:hidden property="userName" value="<%= _pb2.getUserName()%>"/>
      <html:hidden property="passWord" value="<%= _pb2.getPassWord()%>"/>
      <html:hidden property="trueName" value="<%= _pb2.getTrueName()%>"/>
      <html:hidden property="source" value="<%= _pb2.getSource()%>"/>
      <html:hidden property="lastType" value="<%= _pb2.getLastType()%>"/>
      <html:hidden property="thisType" value="<%= _pb2.getThisType()%>"/>
      <html:hidden property="continueRight" value="<%= (new Integer(_pb2.getContinueRight()).toString())%>"/>
      <html:hidden property="continueWrong" value="<%= (new Integer(_pb2.getContinueWrong()).toString())%>"/>
      <html:hidden property="neverHigh" value="<%= (new Boolean(_pb2.getNeverHigh()).toString())%>"/>
      <html:hidden property="times" value="<%= _pb2.getTimes()%>"/>
      <html:hidden property="randomNumber" value="<%= _pb2.getRandomNumber()%>"/>
      <html:hidden property="id" value="<%= new Integer(_pb2.getCurrentProblemId()).toString() %>"/>
      <html:hidden property="planStatus" value="<%= _pb2.getPlanStatus()%>"/>

      <html:submit property="submit" value="Submit"/>
      <html:reset/>

    </html:form><br>

    <% } else { %>

      <center><h2><%= strExit%></h2></center>
        <center>
      <html:form action="/SmartQuit.do" >     
      <html:hidden property="answeredHashSet" value="<%= _hs%>"/>
      <html:hidden property="answeredHashSet_tf" value="<%= _hs_tf%>"/>
      <html:hidden property="answered_M_HashSet" value="<%= _mhs%>"/>
      <html:hidden property="answeredHashSet_fb" value="<%= _hs_fb%>"/>
      <html:hidden property="answeredHashSet_fdb" value="<%= _hs_fdb%>"/>
      <html:hidden property="answered_MY_HashSet" value="<%= _hs_m%>"/>
      <html:hidden property="answeredHashSet_tm" value="<%= _hs_tm%>"/>
      <html:hidden property="answered_D_HashSet" value="<%= _hs_d%>"/>
      <html:hidden property="answered_C_HashSet" value="<%= _hs_c2%>"/>
      <html:hidden property="userName" value="<%= _pb2.getUserName()%>"/>
      <html:hidden property="passWord" value="<%= _pb2.getPassWord()%>"/>
      <html:hidden property="trueName" value="<%= _pb2.getTrueName()%>"/>
      <html:hidden property="source" value="<%= _pb2.getSource()%>"/>
      <html:hidden property="answeredProblems" value="<%= (new Integer(_pb2.getAnsweredProblems()).toString())%>"/><br>
      <html:hidden property="correctAnswers" value="<%= (new Integer(_pb2.getCorrectAnswers()).toString())%>"/>
      <html:hidden property="totalScore" value="<%= (new Double(_pb2.getTotalScore()).toString())%>"/>
      <html:hidden property="correctAnswers_low" value="<%= (new Integer(_pb2.getCorrectAnswers_low()).toString())%>"/>
      <html:hidden property="answeredProblems_low" value="<%= (new Integer(_pb2.getAnsweredProblems_low()).toString())%>"/>
 <html:hidden property="correctAnswers_middle" value="<%= (new Integer(_pb2.getCorrectAnswers_middle()).toString())%>"/>
 <html:hidden property="answeredProblems_middle" value="<%= (new Integer(_pb2.getAnsweredProblems_middle()).toString())%>"/>
     <html:hidden property="correctAnswers_high" value="<%= (new Integer(_pb2.getCorrectAnswers_high()).toString())%>"/>
     <html:hidden property="answeredProblems_high" value="<%= (new Integer(_pb2.getAnsweredProblems_high()).toString())%>"/>
      <html:hidden property="times" value="<%= _pb2.getTimes()%>"/>
      <html:hidden property="planStatus" value="<%= _pb2.getPlanStatus()%>"/>
  
      <html:submit property="submit" value="Submit"/>
      <html:reset/>

    </html:form><br>
    </center>

     
    <% } %>
    <html:img page="/struts-power.gif" alt="Powered by Struts"/>
   
  </body>
</html:html>
