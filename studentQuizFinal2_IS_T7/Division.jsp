<%@ include file="taglibs.jsp" %>

<html:html locale="true">
  <head>   
    <html:base/>
  </head>
  <body bgcolor="white"><p>    

   <html:errors/><p> 

<%! HttpSession session = null; 
        hello.DivisionBean _db = null;
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
        int currentId = 0;  
        int level = 3;      

        java.sql.Connection myConnection = null;
        PreparedStatement stmt = null;        
        ResultSet rs = null;
        String divisionHint = null;
        String strExit = null;
        String calculatorHint = null;

        String strInput = "";
        String inputHint = "";
        String inputHint2 = "";
        String inputHint3 = "";
        String tmpStr = "";
        String tmpStr2 = "not done";

        String randomNumber = "-10";
        String multiply2ndPart = "";
    %>
    <% session = request.getSession(true);     
       _db = (hello.DivisionBean)request.getAttribute(hello.Constants.DIVISION_KEY);
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

       currentId = _pb2.getCurrentProblemId();  
       randomNumber =  _pb2.getRandomNumber();  

       try{
          Class.forName("org.hsqldb.jdbcDriver");
          myConnection = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/xdb", "SA", ""); 
          stmt = myConnection.prepareStatement("select term2 from hintMatch where term1=?");
          stmt.clearParameters();
          stmt.setString(1, "divisionHint");
          rs = stmt.executeQuery();              
          while(rs.next()){
             divisionHint = rs.getString(1);
          }
          //divisionHint += "randomNumber = " + randomNumber;

          stmt = myConnection.prepareStatement("select term2 from hintMatch where term1=?");
          stmt.clearParameters();
          stmt.setString(1, "exit");
          rs = stmt.executeQuery();              
          while(rs.next()){
             strExit = rs.getString(1);
          }

          stmt = myConnection.prepareStatement("select term2 from hintMatch where term1=?");
          stmt.clearParameters();
          stmt.setString(1, "calculator");
          rs = stmt.executeQuery();              
          while(rs.next()){
             calculatorHint = rs.getString(1);
          }

          stmt = myConnection.prepareStatement("select term2 from hintMatch where term1=?");
          stmt.clearParameters();
          stmt.setString(1, "input");
          rs = stmt.executeQuery();              
          while(rs.next()){
             strInput = rs.getString(1);
          }

          stmt = myConnection.prepareStatement("select term2 from hintMatch where term1=?");
          stmt.clearParameters();
          stmt.setString(1, "inputHint");
          rs = stmt.executeQuery();              
          while(rs.next()){
             inputHint = rs.getString(1);
          }

          stmt = myConnection.prepareStatement("select term2 from hintMatch where term1=?");
          stmt.clearParameters();
          stmt.setString(1, "inputHint2");
          rs = stmt.executeQuery();              
          while(rs.next()){
             inputHint2 = rs.getString(1);
          }        

          stmt = myConnection.prepareStatement("select term2 from hintMatch where term1=?");
          stmt.clearParameters();
          stmt.setString(1, "inputHint3");
          rs = stmt.executeQuery();              
          while(rs.next()){
             inputHint3 = rs.getString(1);
          }     

          stmt = myConnection.prepareStatement("select term2 from hintMatch where term1=?");
          stmt.clearParameters();
          stmt.setString(1, "multiply2ndPart");
          rs = stmt.executeQuery();              
          while(rs.next()){
             multiply2ndPart = rs.getString(1);
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

  
    <% level = _db.getLevel();
       if (level == 3){ 
    %>
       <logic:present name="divisionbean" scope="session">
          <h2>
          <bean:message key="hello.jsp.page.problem"/>
          <bean:write name="divisionbean" property="id" /><p>
          <bean:write name="divisionbean" property="type" /><p>
          <bean:write name="divisionbean" property="b_result" /><br>          
          <bean:write name="divisionbean" property="line1" /><br> 
          <bean:write name="divisionbean" property="divider" /><bean:write name="divisionbean" property="division_line" /><bean:write name="divisionbean" property="divided" /><br>  
          <bean:write name="divisionbean" property="b_product1" /><br>      
          <bean:write name="divisionbean" property="line2" /><br>      
          <bean:write name="divisionbean" property="b_remain1" /><br>            
          </h2>
       </logic:present>
    <% } else if (level == 5) { %>
       <logic:present name="divisionbean" scope="session">
          <h2>
          <bean:message key="hello.jsp.page.problem"/>
          <bean:write name="divisionbean" property="id" /><p>
          <bean:write name="divisionbean" property="type" /><p>
          <bean:write name="divisionbean" property="b_result" /><br>          
          <bean:write name="divisionbean" property="line1" /><br> 
          <bean:write name="divisionbean" property="divider" /><bean:write name="divisionbean" property="division_line" /><bean:write name="divisionbean" property="divided" /><br>  
          <bean:write name="divisionbean" property="b_product1" /><br>      
          <bean:write name="divisionbean" property="line2" /><br>      
          <bean:write name="divisionbean" property="b_remain1" /><br>         
          <bean:write name="divisionbean" property="b_product2" /><br>  
          <bean:write name="divisionbean" property="line3" /><br>  
          <bean:write name="divisionbean" property="b_remain2" /><br>           
          </h2>
       </logic:present>
    <% } else if (level == 7) { %>
       <logic:present name="divisionbean" scope="session">
          <h2>
          <bean:message key="hello.jsp.page.problem"/>
          <bean:write name="divisionbean" property="id" /><p>
          <bean:write name="divisionbean" property="type" /><p>
          <bean:write name="divisionbean" property="b_result" /><br>          
          <bean:write name="divisionbean" property="line1" /><br> 
          <bean:write name="divisionbean" property="divider" /><bean:write name="divisionbean" property="division_line" /><bean:write name="divisionbean" property="divided" /><br>  
          <bean:write name="divisionbean" property="b_product1" /><br>      
          <bean:write name="divisionbean" property="line2" /><br>      
          <bean:write name="divisionbean" property="b_remain1" /><br>         
          <bean:write name="divisionbean" property="b_product2" /><br>  
          <bean:write name="divisionbean" property="line3" /><br>  
          <bean:write name="divisionbean" property="b_remain2" /><br>         
          <bean:write name="divisionbean" property="b_product3" /><br>  
          <bean:write name="divisionbean" property="line4" /><br>
          <bean:write name="divisionbean" property="b_remain3" /><br>    
          </h2>
       </logic:present>
    <% } %>     

    <% remainSeconds = _pb2.getRemainSeconds();
       if (remainSeconds > 0 && _pb2.getPlanStatus().equals("not finished")){ 
    %>

    <html:form action="/SmartRandomCheck.do" >

      <%= divisionHint%> 
      <% if (level == 3) { %>     
         <html:text property="inputAnswer" size="16" maxlength="100" />;<br><html:text property="inputAnswer2" size="16" maxlength="100" />;<br><html:text property="inputAnswer3" size="16" maxlength="100" />;<br>
         <html:hidden property="inputAnswer4" value=""/><html:hidden property="inputAnswer5" value=""/><html:hidden property="inputAnswer6" value=""/><html:hidden property="inputAnswer7" value=""/>
      <% } else if (level == 5) { %>
         <html:text property="inputAnswer" size="16" maxlength="100" />;<br><html:text property="inputAnswer2" size="16" maxlength="100" />;<br><html:text property="inputAnswer3" size="16" maxlength="100" />;<br><html:text property="inputAnswer4" size="16" maxlength="100" />;<br><html:text property="inputAnswer5" size="16" maxlength="100" />;<br>
         <html:hidden property="inputAnswer6" value=""/><html:hidden property="inputAnswer7" value=""/>
      <% } else if (level == 7) { %>
         <html:text property="inputAnswer" size="16" maxlength="100" />;<br><html:text property="inputAnswer2" size="16" maxlength="100" />;<br><html:text property="inputAnswer3" size="16" maxlength="100" />;<br><html:text property="inputAnswer4" size="16" maxlength="100" />;<br><html:text property="inputAnswer5" size="16" maxlength="100" />;<br><html:text property="inputAnswer6" size="16" maxlength="100" />;<br><html:text property="inputAnswer7" size="16" maxlength="100" /><br>      
      <% } %> 
      <html:hidden property="result" value="<%= _db.getResult()%>"/>
      <html:hidden property="product1" value="<%= _db.getProduct1()%>"/>
      <html:hidden property="remain1" value="<%= _db.getRemain1()%>"/>
      <html:hidden property="product2" value="<%= _db.getProduct2()%>"/>
      <html:hidden property="remain2" value="<%= _db.getRemain2()%>"/>
      <html:hidden property="product3" value="<%= _db.getProduct3()%>"/>
      <html:hidden property="remain3" value="<%= _db.getRemain3()%>"/>      
      <html:hidden property="source" value="<%= _db.getSource()%>"/> 
      <html:hidden property="level" value="<%= new Integer(_db.getLevel()).toString() %>"/> 
      
      <html:hidden property="correctAnswer" value=""/> 
      <html:hidden property="solution" value=""/>
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

      <center><h2><%= strExit%></h2></h2></center>
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

    <CENTER><H3><a href="javascript:window.open('AppletExample/InputApplet2.html')"><%= inputHint2%></a></H3></CENTER><P>

    <!-- <APPLET CODEBASE="AppletExample" CODE="InputApplet2.class" WIDTH=360 HEIGHT=360></APPLET>  -->

    <html:img page="/struts-power.gif" alt="Powered by Struts"/>
   
  </body>
</html:html>



