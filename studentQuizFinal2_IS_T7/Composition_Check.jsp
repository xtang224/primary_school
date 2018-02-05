<%@ include file="taglibs.jsp" %>


<html:html locale="true">
  <head>
    <title><bean:message key="hello.jsp.page.solution"/></title>
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
        int answeredProblems = 0;
        int remainSeconds = 300;
        String lastCorrect = null;

        java.sql.Connection myConnection = null;
        PreparedStatement stmt = null;        
        ResultSet rs = null;
        String solutionHint = null;
        String problemDone = null;
        String problemRight = null;
        String strHello = null;
        String secondsRemaining = null;
        String strContinue = null;
        String strExit = null;
        String trueScoreHint = "";
        String yourScoreHint = "";
        String commentHint = "";

        String yourSolutionHint = null;
        int id;
        int level = 3;
        String extraSolution = "";
        int trueScore = 0;
        double yourScore = 0;
    %>
    <% session = request.getSession(true);     
   
       _c2b = (hello.CompositionBean)request.getAttribute(hello.Constants.COMPOSITION_KEY);
       _pb2 = (hello.PersonBean)request.getAttribute(hello.Constants.PERSON_KEY);
       _hs = (String)request.getAttribute(Constants.HASHSET_KEY);
       _hs_tf = (String)request.getAttribute(Constants.HASHSET_TF_KEY);
       _mhs = (String)request.getAttribute(hello.Constants.HASHSET_M_KEY);
       _hs_fb = (String)request.getAttribute(Constants.HASHSET_FB_KEY);
       _hs_fdb = (String)request.getAttribute(Constants.HASHSET_FDB_KEY);
       _hs_m = (String)request.getAttribute(Constants.HASHSET_MY_KEY);
       _hs_tm = (String)request.getAttribute(hello.Constants.HASHSET_TM_KEY);
       _hs_d = (String)request.getAttribute(Constants.HASHSET_D_KEY);
       _hs_c2 = (String)request.getAttribute(Constants.HASHSET_C2_KEY);
    
       session.setAttribute(hello.Constants.COMPOSITION_KEY, _c2b);
       session.setAttribute(hello.Constants.PERSON_KEY, _pb2);
       session.setAttribute(hello.Constants.HASHSET_KEY, _hs);
       session.setAttribute(hello.Constants.HASHSET_TF_KEY, _hs_tf);
       session.setAttribute(hello.Constants.HASHSET_M_KEY, _mhs);
       session.setAttribute(hello.Constants.HASHSET_FB_KEY, _hs_fb);
       session.setAttribute(hello.Constants.HASHSET_FDB_KEY, _hs_fdb);
       session.setAttribute(hello.Constants.HASHSET_MY_KEY, _hs_m);
       session.setAttribute(hello.Constants.HASHSET_TM_KEY, _hs_tm);
       session.setAttribute(hello.Constants.HASHSET_D_KEY, _hs_d);
       session.setAttribute(hello.Constants.HASHSET_C2_KEY, _hs_c2);

       if (_pb2.getLastCorrect()){
          lastCorrect = "true";
       }else{
          lastCorrect = "false";
       }

       id = _pb2.getCurrentProblemId();
       try{
          Class.forName("org.hsqldb.jdbcDriver");
          myConnection = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/xdb", "SA", ""); 
          stmt = myConnection.prepareStatement("select term2 from hintMatch where term1=?");
          stmt.clearParameters();
          stmt.setString(1, "solution");
          rs = stmt.executeQuery();              
          while(rs.next()){
             solutionHint = rs.getString(1);
          }
          //solutionHint += "Multiply_Check";

          stmt = myConnection.prepareStatement("select term2 from hintMatch where term1=?");
          stmt.clearParameters();
          stmt.setString(1, "problemDone");
          rs = stmt.executeQuery();              
          while(rs.next()){
             problemDone = rs.getString(1);
          }

          stmt = myConnection.prepareStatement("select term2 from hintMatch where term1=?");
          stmt.clearParameters();
          stmt.setString(1, "problemRight");
          rs = stmt.executeQuery();              
          while(rs.next()){
             problemRight = rs.getString(1);
          }

          stmt = myConnection.prepareStatement("select term2 from hintMatch where term1=?");
          stmt.clearParameters();
          stmt.setString(1, "hello");
          rs = stmt.executeQuery();              
          while(rs.next()){
             strHello = rs.getString(1);
          }

          stmt = myConnection.prepareStatement("select term2 from hintMatch where term1=?");
          stmt.clearParameters();
          stmt.setString(1, "secondsRemaining");
          rs = stmt.executeQuery();              
          while(rs.next()){
             secondsRemaining = rs.getString(1);
          }

          stmt = myConnection.prepareStatement("select term2 from hintMatch where term1=?");
          stmt.clearParameters();
          stmt.setString(1, "continue");
          rs = stmt.executeQuery();              
          while(rs.next()){
             strContinue = rs.getString(1);
          }

          stmt = myConnection.prepareStatement("select term2 from hintMatch where term1=?");
          stmt.clearParameters();
          stmt.setString(1, "exit");
          rs = stmt.executeQuery();              
          while(rs.next()){
             strExit = rs.getString(1);
          }

          stmt = myConnection.prepareStatement("select term2 from hintMatch where term1=?");
          stmt.clearParameters();
          stmt.setString(1, "yourSolution");
          rs = stmt.executeQuery();              
          while(rs.next()){
             yourSolutionHint = rs.getString(1);
          } 

          stmt = myConnection.prepareStatement("select term2 from hintMatch where term1=?");
          stmt.clearParameters();
          stmt.setString(1, "trueScoreHint");
          rs = stmt.executeQuery();              
          while(rs.next()){
             trueScoreHint = rs.getString(1);
          }

          stmt = myConnection.prepareStatement("select term2 from hintMatch where term1=?");
          stmt.clearParameters();
          stmt.setString(1, "yourScoreHint");
          rs = stmt.executeQuery();              
          while(rs.next()){
             yourScoreHint = rs.getString(1);
          }

          stmt = myConnection.prepareStatement("select term2 from hintMatch where term1=?");
          stmt.clearParameters();
          stmt.setString(1, "commentHint");
          rs = stmt.executeQuery();              
          while(rs.next()){
             commentHint = rs.getString(1);
          }

          stmt = myConnection.prepareStatement("select score from composition_test_score where id=?");
          stmt.clearParameters();
          stmt.setInt(1, id);
          rs = stmt.executeQuery();              
          while(rs.next()){
             trueScore = rs.getInt(1);
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

       yourScore = trueScore * _c2b.getScoreRatio();
       
       if (extraSolution == null)
          extraSolution = ""; 
    %>


    <%= solutionHint%>
    
       <logic:present name="compositionbean" scope="session">
          <h2>
            <bean:write name="compositionbean" property="statement" /><br>          
            <bean:write name="compositionbean" property="answerSentences" /><br>                
          </h2>
       </logic:present>
       
    
    <%= extraSolution%><br>
    
    <%= yourSolutionHint%>
    
       <logic:present name="compositionbean" scope="session">
          <h2>         
             <bean:write name="compositionbean" property="yourSentences" /><br>                       
          </h2>
       </logic:present>
      

    <%= strHello%>
    <logic:present name="personbean" scope="session">
       <h2>         
         <bean:write name="personbean" property="trueName" /><p> 
       </h2>
    </logic:present>

    <%= trueScoreHint%><%= trueScore%><br>
    <%= yourScoreHint%><%= yourScore%><br>
    <%= commentHint%><%= _c2b.getComment()%><br> 

    <%= problemDone%>
    <logic:present name="personbean" scope="session">
       <h2>        
         <bean:write name="personbean" property="answeredProblems" /><p>            
       </h2>
    </logic:present>
    
    <%= problemRight%>
    <logic:present name="personbean" scope="session">
       <h2>        
         <bean:write name="personbean" property="correctAnswers" /><p>            
       </h2>
    </logic:present>
    
    <%= secondsRemaining%>
    <logic:present name="personbean" scope="session">
       <h2>        
         <bean:write name="personbean" property="remainSeconds" /><p>            
       </h2>
    </logic:present>     

   <% remainSeconds = _pb2.getRemainSeconds();
       if (remainSeconds > 0 && _pb2.getPlanStatus().equals("not finished")){ 
    %>

    <%= strContinue%>
    <html:form action="/SmartRandom.do?first=false" >
     
      <html:hidden property="answeredProblems" value="<%= (new Integer(_pb2.getAnsweredProblems()).toString())%>"/><br>
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
      <html:hidden property="lastCorrect" value="<%= lastCorrect%>"/>
      <html:hidden property="lastType" value="<%= _pb2.getLastType()%>"/>
      <html:hidden property="thisType" value="<%= _pb2.getThisType()%>"/>
      <html:hidden property="continueRight" value="<%= (new Integer(_pb2.getContinueRight()).toString())%>"/>
      <html:hidden property="continueWrong" value="<%= (new Integer(_pb2.getContinueWrong()).toString())%>"/>
      <html:hidden property="neverHigh" value="<%= (new Boolean(_pb2.getNeverHigh()).toString())%>"/>
      <html:hidden property="times" value="<%= _pb2.getTimes()%>"/>
      <html:hidden property="planStatus" value="<%= _pb2.getPlanStatus()%>"/>

      <html:submit property="submit" value="Submit"/>
      <html:reset/>

    </html:form><br><br>    

    <% } else {        
    %>
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

    <app:validateSession/>
    
    <html:img page="/struts-power.gif" alt="Powered by Struts"/>

  </body>
</html:html>
