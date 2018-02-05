<%@ include file="taglibs.jsp" %>

<html:html locale="true">
  <head>   
    <html:base/>
  </head>
  <body bgcolor="white"><p>    

   <html:errors/><p> 

<%! HttpSession session = null; 
        hello.CompositionBean _c2b = null;
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
        String compositionHint = null;
        String strExit = null;
        String calculatorHint = null;

        String strInput = "";
        String inputHint = "";
        String inputHint2 = "";
        String inputHint3 = "";
        String inputHint8 = "";
        String tmpStr = "";
        String tmpStr2 = "not done";

        String randomNumber = "-10";
        String multiply2ndPart = "";
    %>
    <% session = request.getSession(true);     
       _c2b = (hello.CompositionBean)request.getAttribute(hello.Constants.COMPOSITION_KEY);
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
          stmt.setString(1, "compositionHint");
          rs = stmt.executeQuery();              
          while(rs.next()){
             compositionHint = rs.getString(1);
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
          stmt.setString(1, "inputHint8");
          rs = stmt.executeQuery();              
          while(rs.next()){
             inputHint8 = rs.getString(1);
          }     

          stmt = myConnection.prepareStatement("select term2 from hintMatch where term1=?");
          stmt.clearParameters();
          stmt.setString(1, "multiply2ndPart");
          rs = stmt.executeQuery();              
          while(rs.next()){
             multiply2ndPart = rs.getString(1);
          } 

          stmt = myConnection.prepareStatement("update hintMatch set term2=? where term1=?");
          stmt.clearParameters();
          stmt.setString(1, _c2b.getStatement());
          stmt.setString(2, "compositionStatement");  
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
    %>
    
       <logic:present name="compositionbean" scope="session">
          <h2>
          <bean:message key="hello.jsp.page.problem"/>
          <bean:write name="compositionbean" property="id" /><p>
          <bean:write name="compositionbean" property="type" /><p>
          <bean:write name="compositionbean" property="statement" /><br>                    
          </h2>
       </logic:present>

    <CENTER><H3><a href="javascript:window.open('AppletExample/ComApplet.html')"><%= inputHint8%></a></H3></CENTER><P>
     

    <% remainSeconds = _pb2.getRemainSeconds();
       if (remainSeconds > 0 && _pb2.getPlanStatus().equals("not finished")){ 
    %>

    <html:form action="/SmartRandomCheck.do" >

      <%= compositionHint%> 
        
      <html:text property="inputAnswer" size="100" maxlength="5000" />;<br>
      
      <html:hidden property="answerSentences" value="<%= _c2b.getAnswerSentences()%>"/>
      <html:hidden property="answerWords" value="<%= _c2b.getAnswerWords()%>"/>          
      <html:hidden property="source" value="<%= _c2b.getSource()%>"/> 
      <html:hidden property="fullScore" value="<%= new Integer(_c2b.getFullScore()).toString() %>"/> 
      <html:hidden property="period" value="。"/> 
      
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
      <html:hidden property="answered_C2_HashSet" value="<%= _hs_c2%>"/>
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



