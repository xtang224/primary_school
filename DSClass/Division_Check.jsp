<%@ include file="taglibs.jsp" %>


<html:html locale="true">
  <head>
    <title><bean:message key="hello.jsp.page.solution"/></title>
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
        String _hs_ftb = null;
        String _hs_fqb = null;
        String _hs_m = null;
        String _hs_tm = null;
        String _hs_d = null;
        String _hs_pm = null;
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

        String yourSolutionHint = null;
        int id;
        int level = 3;
        String extraSolution = "";
    %>
    <% session = request.getSession(true);     
   
       _db = (hello.DivisionBean)request.getAttribute(hello.Constants.DIVISION_KEY);
       _pb2 = (hello.PersonBean)request.getAttribute(hello.Constants.PERSON_KEY);
       _hs = (String)request.getAttribute(Constants.HASHSET_KEY);
       _hs_tf = (String)request.getAttribute(Constants.HASHSET_TF_KEY);
       _mhs = (String)request.getAttribute(hello.Constants.HASHSET_M_KEY);
       _hs_fb = (String)request.getAttribute(Constants.HASHSET_FB_KEY);
       _hs_fdb = (String)request.getAttribute(Constants.HASHSET_FDB_KEY);
       _hs_ftb = (String)request.getAttribute(Constants.HASHSET_FTB_KEY);
       _hs_fqb = (String)request.getAttribute(Constants.HASHSET_FQB_KEY);
       _hs_m = (String)request.getAttribute(Constants.HASHSET_MY_KEY);
       _hs_tm = (String)request.getAttribute(hello.Constants.HASHSET_TM_KEY);
       _hs_d = (String)request.getAttribute(Constants.HASHSET_D_KEY);
       _hs_pm = (String)request.getAttribute(hello.Constants.HASHSET_PM_KEY);
    
       session.setAttribute(hello.Constants.DIVISION_KEY, _db);
       session.setAttribute(hello.Constants.PERSON_KEY, _pb2);
       session.setAttribute(hello.Constants.HASHSET_KEY, _hs);
       session.setAttribute(hello.Constants.HASHSET_TF_KEY, _hs_tf);
       session.setAttribute(hello.Constants.HASHSET_M_KEY, _mhs);
       session.setAttribute(hello.Constants.HASHSET_FB_KEY, _hs_fb);
       session.setAttribute(hello.Constants.HASHSET_FDB_KEY, _hs_fdb);
       session.setAttribute(hello.Constants.HASHSET_FTB_KEY, _hs_ftb);
       session.setAttribute(hello.Constants.HASHSET_FQB_KEY, _hs_fqb);
       session.setAttribute(hello.Constants.HASHSET_MY_KEY, _hs_m);
       session.setAttribute(hello.Constants.HASHSET_TM_KEY, _hs_tm);
       session.setAttribute(hello.Constants.HASHSET_D_KEY, _hs_d);
       session.setAttribute(hello.Constants.HASHSET_PM_KEY, _hs_pm);

       if (_pb2.getLastCorrect()){
          lastCorrect = "true";
       }else{
          lastCorrect = "false";
       }

       id = _pb2.getCurrentProblemId();       
       
       if (extraSolution == null)
          extraSolution = ""; 
    %>

    <bean:message key="hello.jsp.page.solution"/>
    <% level = _db.getLevel();
       if (level == 3){ 
          System.out.println("Inside Division_Check.jsp, we are in level == 3");
    %>
       <logic:present name="divisionbean" scope="session">
          <h2>
            <bean:write name="divisionbean" property="result" /><br>          
            <bean:write name="divisionbean" property="line1" /><br>    
            <bean:write name="divisionbean" property="divider" /><bean:write name="divisionbean" property="division_line" /><bean:write name="divisionbean" property="divided" /><br>  
            <bean:write name="divisionbean" property="product1" /><br>      
            <bean:write name="divisionbean" property="line2" /><br>      
            <bean:write name="divisionbean" property="remain1" /><br>    
          </h2>
       </logic:present>
       
    <% } else if (level == 5) { %>
       <logic:present name="divisionbean" scope="session">
          <h2>        
            <bean:write name="divisionbean" property="result" /><br>          
            <bean:write name="divisionbean" property="line1" /><br> 
            <bean:write name="divisionbean" property="divider" /><bean:write name="divisionbean" property="division_line" /><bean:write name="divisionbean" property="divided" /><br>  
            <bean:write name="divisionbean" property="product1" /><br>      
            <bean:write name="divisionbean" property="line2" /><br>      
            <bean:write name="divisionbean" property="remain1" /><br>         
            <bean:write name="divisionbean" property="product2" /><br>  
            <bean:write name="divisionbean" property="line3" /><br>  
            <bean:write name="divisionbean" property="remain2" /><br>           
          </h2>
       </logic:present>
    <% } else if (level == 7) { %>
      <logic:present name="divisionbean" scope="session">
          <h2>         
            <bean:write name="divisionbean" property="result" /><br>          
            <bean:write name="divisionbean" property="line1" /><br> 
            <bean:write name="divisionbean" property="divider" /><bean:write name="divisionbean" property="division_line" /><bean:write name="divisionbean" property="divided" /><br>  
            <bean:write name="divisionbean" property="product1" /><br>      
            <bean:write name="divisionbean" property="line2" /><br>      
            <bean:write name="divisionbean" property="remain1" /><br>         
            <bean:write name="divisionbean" property="product2" /><br>  
            <bean:write name="divisionbean" property="line3" /><br>  
            <bean:write name="divisionbean" property="remain2" /><br>         
            <bean:write name="divisionbean" property="product3" /><br>  
            <bean:write name="divisionbean" property="line4" /><br>
            <bean:write name="divisionbean" property="remain3" /><P> 
          </h2>
       </logic:present>
    <% } %> 
    <%= extraSolution%><br>
    
    <bean:message key="hello.jsp.page.yourSolution"/>
    <% if (level == 3){ %>
       <logic:present name="divisionbean" scope="session">
          <h2>         
             <bean:write name="divisionbean" property="yourResult" /><br>          
             <bean:write name="divisionbean" property="line1" /><br> 
             <bean:write name="divisionbean" property="divider" /><bean:write name="divisionbean" property="division_line" /><bean:write name="divisionbean" property="divided" /><br>  
             <bean:write name="divisionbean" property="yourProduct1" /><br>      
             <bean:write name="divisionbean" property="line2" /><br>      
             <bean:write name="divisionbean" property="yourRemain1" /><br>            
          </h2>
       </logic:present>
    <% } else if (level == 5) { %>
       <logic:present name="divisionbean" scope="session">
          <h2>         
             <bean:write name="divisionbean" property="yourResult" /><br>          
             <bean:write name="divisionbean" property="line1" /><br> 
             <bean:write name="divisionbean" property="divider" /><bean:write name="divisionbean" property="division_line" /><bean:write name="divisionbean" property="divided" /><br>  
             <bean:write name="divisionbean" property="yourProduct1" /><br>      
             <bean:write name="divisionbean" property="line2" /><br>      
             <bean:write name="divisionbean" property="yourRemain1" /><br>         
             <bean:write name="divisionbean" property="yourProduct2" /><br>  
             <bean:write name="divisionbean" property="line3" /><br>  
             <bean:write name="divisionbean" property="yourRemain2" /><br>            
          </h2>
       </logic:present>
    <% } else if (level == 7) { %>
       <logic:present name="divisionbean" scope="session">
          <h2>         
             <bean:write name="divisionbean" property="yourResult" /><br>          
             <bean:write name="divisionbean" property="line1" /><br> 
             <bean:write name="divisionbean" property="divider" /><bean:write name="divisionbean" property="division_line" /><bean:write name="divisionbean" property="divided" /><br>  
             <bean:write name="divisionbean" property="yourProduct1" /><br>      
             <bean:write name="divisionbean" property="line2" /><br>      
             <bean:write name="divisionbean" property="yourRemain1" /><br>         
             <bean:write name="divisionbean" property="yourProduct2" /><br>  
             <bean:write name="divisionbean" property="line3" /><br>  
             <bean:write name="divisionbean" property="yourRemain2" /><br>         
             <bean:write name="divisionbean" property="yourProduct3" /><br>  
             <bean:write name="divisionbean" property="line4" /><br>
             <bean:write name="divisionbean" property="yourRemain3" /><P> 
          </h2>
       </logic:present>
    <% } %>     

    <logic:present name="personbean" scope="session">
       <h1>
         <bean:message key="hello.jsp.page.hello"/>
         <bean:write name="personbean" property="trueName" /><bean:message key="hello.jsp.page.student"/>
         <bean:message key="hello.jsp.page.answeredProblems"/>
         <bean:write name="personbean" property="answeredProblems" /><p>     
         <bean:message key="hello.jsp.page.secondsRemaining"/>
         <bean:write name="personbean" property="remainSeconds" /><p> 
       </h1>
    </logic:present>    

   <% remainSeconds = _pb2.getRemainSeconds();
       if (remainSeconds > 0 && _pb2.getPlanStatus().equals("not finished")){ 
    %>

    <html:form action="/SmartRandom2.do?first=false" >

     <bean:message key="hello.jsp.prompt.updateScore"/><html:text property="updateScore" size="16" maxlength="16"/><br>
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
      <html:hidden property="answeredHashSet_ftb" value="<%= _hs_ftb%>"/>
      <html:hidden property="answeredHashSet_fqb" value="<%= _hs_fqb%>"/>
      <html:hidden property="answeredHashSet_tm" value="<%= _hs_tm%>"/>
      <html:hidden property="answeredHashSet_m" value="<%= _hs_m%>"/>
      <html:hidden property="answeredHashSet_d" value="<%= _hs_d%>"/>
      <html:hidden property="answeredHashSet_pm" value="<%= _hs_pm%>"/>
      <html:hidden property="userName" value="<%= _pb2.getUserName()%>"/>
      <html:hidden property="passWord" value="<%= _pb2.getPassWord()%>"/>
      <html:hidden property="trueName" value="<%= _pb2.getTrueName()%>"/>
      <html:hidden property="lastCorrect" value="<%= lastCorrect%>"/>
      <html:hidden property="lastType" value="<%= _pb2.getLastType()%>"/>
      <html:hidden property="thisType" value="<%= _pb2.getThisType()%>"/>
      <html:hidden property="continueRight" value="<%= (new Integer(_pb2.getContinueRight()).toString())%>"/>
      <html:hidden property="continueWrong" value="<%= (new Integer(_pb2.getContinueWrong()).toString())%>"/>
      <html:hidden property="neverHigh" value="<%= (new Boolean(_pb2.getNeverHigh()).toString())%>"/>
      <html:hidden property="times" value="<%= _pb2.getTimes()%>"/>
      <html:hidden property="planStatus" value="<%= _pb2.getPlanStatus()%>"/>
      <html:hidden property="classId" value="<%= _pb2.getClassId()%>"/>

      <html:submit property="submit" value="Submit"/>
      <html:reset/>

    </html:form><br><br>    

    <% } else {        
    %>
       <center><h2><bean:message key="hello.jsp.prompt.finished"/></h2></center>
        <center>
      <html:form action="/Score2.do" >     
      <html:hidden property="answeredHashSet" value="<%= _hs%>"/>
      <html:hidden property="answeredHashSet_tf" value="<%= _hs_tf%>"/>
      <html:hidden property="answered_M_HashSet" value="<%= _mhs%>"/>
      <html:hidden property="answeredHashSet_fb" value="<%= _hs_fb%>"/>
      <html:hidden property="answeredHashSet_fdb" value="<%= _hs_fdb%>"/>
      <html:hidden property="answeredHashSet_ftb" value="<%= _hs_ftb%>"/>
      <html:hidden property="answeredHashSet_fqb" value="<%= _hs_fqb%>"/>
      <html:hidden property="answeredHashSet_tm" value="<%= _hs_tm%>"/>
      <html:hidden property="answeredHashSet_m" value="<%= _hs_m%>"/>
      <html:hidden property="answeredHashSet_d" value="<%= _hs_d%>"/>
      <html:hidden property="answeredHashSet_pm" value="<%= _hs_pm%>"/>
      <html:hidden property="userName" value="<%= _pb2.getUserName()%>"/>
      <html:hidden property="passWord" value="<%= _pb2.getPassWord()%>"/>
      <html:hidden property="trueName" value="<%= _pb2.getTrueName()%>"/>
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
      <html:hidden property="classId" value="<%= _pb2.getClassId()%>"/>
  
      <html:submit property="submit" value="Submit"/>
      <html:reset/>

    </html:form><br>
    </center>

    <% } %>

    <app:validateSession/>
    
    <html:img page="/struts-power.gif" alt="Powered by Struts"/>

  </body>
</html:html>
