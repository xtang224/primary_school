<%@ include file="taglibs.jsp" %>


<html:html locale="true">
  <head>
    <title><bean:message key="hello.jsp.page.solution"/></title>
    <html:base/>
  </head>
  <body bgcolor="white"><p>   

   <html:errors/><p> 

    <logic:present name="fillblankbean" scope="request">
       <h1>
         <bean:message key="hello.jsp.page.solution"/>
         <bean:write name="fillblankbean" property="statement_1st" />         
         <bean:write name="fillblankbean" property="solution" />    
         <bean:write name="fillblankbean" property="statement_2nd" /><br> 
         <bean:write name="fillblankbean" property="explanation" /><P>   
       </h1>
    </logic:present>

    <app:validateSession/>
    
    <logic:present name="personbean" scope="session">
       <h1>
         <bean:message key="hello.jsp.page.hello"/>
         <bean:write name="personbean" property="trueName" /><bean:message key="hello.jsp.page.student"/>
         <bean:message key="hello.jsp.page.answeredProblems"/>
         <bean:write name="personbean" property="answeredProblems" /><p>     
         <bean:message key="hello.jsp.page.secondsRemaining"/>
         <bean:write name="personbean" property="remainSeconds" /><p>  
  <!--   <bean:message key="hello.jsp.page.lastCorrect"/>
         <bean:write name="personbean" property="lastCorrect" /><p>           
         <bean:message key="hello.jsp.page.correctAnswers"/>
         <bean:write name="personbean" property="correctAnswers" /><p>     
         <bean:message key="hello.jsp.page.answeredProblems_low"/>
         <bean:write name="personbean" property="answeredProblems_low" /><p> 
         <bean:message key="hello.jsp.page.correctAnswers_low"/>
         <bean:write name="personbean" property="correctAnswers_low" /><p>   
         <bean:message key="hello.jsp.page.answeredProblems_middle"/>
         <bean:write name="personbean" property="answeredProblems_middle" /><p> 
         <bean:message key="hello.jsp.page.correctAnswers_middle"/>
         <bean:write name="personbean" property="correctAnswers_middle" /><p>            
         <bean:message key="hello.jsp.page.answeredProblems_high"/>
         <bean:write name="personbean" property="answeredProblems_high" /><p> 
         <bean:message key="hello.jsp.page.correctAnswers_high"/>
         <bean:write name="personbean" property="correctAnswers_high" /><p>      
         <bean:message key="hello.jsp.page.totalScore"/>
         <bean:write name="personbean" property="totalScore" /><p>        -->    
       </h1>
    </logic:present>

    
    <%! HttpSession session = null; 
        hello.FillBlankBean _fbb = null;
        hello.PersonBean _pb2 = null;
        String _hs = null;
        String _hs_tf = null;
        String _mhs = null;
        String _hs_fb = null;
        String _hs_fdb = null;
        String _hs_ftb = null;
        String _hs_fqb = null;
        String _hs_tm = null;
        String _hs_m = null;
        String _hs_d = null;
        String _hs_pm = null;
        
        int answeredProblems = 0;
        int remainSeconds = 300;
        String lastCorrect = null;
        int id = 0;
    %>
    <% session = request.getSession(true);     
       _fbb = (hello.FillBlankBean)request.getAttribute(hello.Constants.FILLBLANK_KEY);
       _pb2 = (hello.PersonBean)request.getAttribute(hello.Constants.PERSON_KEY);
       _hs = (String)request.getAttribute(Constants.HASHSET_KEY);
       _hs_tf = (String)request.getAttribute(Constants.HASHSET_TF_KEY);
       _mhs = (String)request.getAttribute(hello.Constants.HASHSET_M_KEY);
       _hs_fb = (String)request.getAttribute(Constants.HASHSET_FB_KEY);
       _hs_fdb = (String)request.getAttribute(Constants.HASHSET_FDB_KEY);
       _hs_ftb = (String)request.getAttribute(Constants.HASHSET_FTB_KEY);
       _hs_fqb = (String)request.getAttribute(Constants.HASHSET_FQB_KEY);
       _hs_tm = (String)request.getAttribute(Constants.HASHSET_TM_KEY);
       _hs_m = (String)request.getAttribute(Constants.HASHSET_MY_KEY);
       _hs_d = (String)request.getAttribute(Constants.HASHSET_D_KEY);
       _hs_pm = (String)request.getAttribute(Constants.HASHSET_PM_KEY);
    
       session.setAttribute(hello.Constants.FILLBLANK_KEY, _fbb);
       session.setAttribute(hello.Constants.PERSON_KEY, _pb2);
       session.setAttribute(hello.Constants.HASHSET_KEY, _hs);
       session.setAttribute(hello.Constants.HASHSET_TF_KEY, _hs_tf);
       session.setAttribute(hello.Constants.HASHSET_M_KEY, _mhs);
       session.setAttribute(hello.Constants.HASHSET_FB_KEY, _hs_fb);
       session.setAttribute(hello.Constants.HASHSET_FDB_KEY, _hs_fdb);
       session.setAttribute(hello.Constants.HASHSET_FTB_KEY, _hs_ftb);
       session.setAttribute(hello.Constants.HASHSET_FQB_KEY, _hs_fqb);
       session.setAttribute(hello.Constants.HASHSET_TM_KEY, _hs_tm);
       session.setAttribute(hello.Constants.HASHSET_MY_KEY, _hs_m);
       session.setAttribute(hello.Constants.HASHSET_D_KEY, _hs_d);
       session.setAttribute(hello.Constants.HASHSET_PM_KEY, _hs_pm);

       id = _pb2.getCurrentProblemId();

       if (_pb2.getLastCorrect()){
          lastCorrect = "true";
       }else{
          lastCorrect = "false";
       }       
    %>

    <% if (id == 1143) { %>
        <html:img page="/pic/b6_ch4_class5_b1143e.png" alt="Powered by Struts"/>
    <% } else if (id == 1148) { %>
        <html:img page="/pic/b6_ch4_4_b1148e.png" alt="Powered by Struts"/>
    <% } else if (id == 1154) { %>
        <html:img page="/pic/b6_ch4_7_b1154e.png" alt="Powered by Struts"/>
    <% } else if (id == 1157) { %>
        <html:img page="/pic/b6_ch4_class8_b1157e.png" alt="Powered by Struts"/>
    <% } else if (id == 1229) { %>
        <html:img page="/pic/b6_ch4__2_b_1229e.png" alt="Powered by Struts"/>
    <% } else if (id == 1289) { %>
        <html:img page="/pic/b6_ch1_b_1289e.png" alt="Powered by Struts"/>
    <% } else if (id == 1344) { %>
        <html:img page="/pic/b6_ch6_1_b_1344e.png" alt="Powered by Struts"/>
    <% } else if (id == 1345) { %>
        <html:img page="/pic/b6_ch6_1_b_1345e.png" alt="Powered by Struts"/>
    <% } else if (id == 1346) { %>
        <html:img page="/pic/b6_ch6_1_b_1346e.png" alt="Powered by Struts"/>
    <% } else if (id == 1385) { %>
        <html:img page="/pic/b6_ch1_2_b_1385e.png" alt="Powered by Struts"/>
    <% } else if (id == 1390) { %>
        <html:img page="/pic/b6_ch1_self_b_1390_2e.png" alt="Powered by Struts"/>
    <% } else if (id == 1555) { %>
        <html:img page="/pic/b6_ch6_hw5_b_1555_e.png" alt="Powered by Struts"/><html:img page="/pic/b6_ch6_hw5_b_1555_e2.png" alt="Powered by Struts"/>
    <% } else if (id == 1796) { %>
        <html:img page="/pic/b6_final_class7_b_1796_e.png" alt="Powered by Struts"/>
    <% } else if (id == 1797) { %>
        <html:img page="/pic/b6_final_class7_b_1797_e_qb_152.png" alt="Powered by Struts"/>
    <% } else if (id == 1800) { %>
        <html:img page="/pic/b6_final_class7_b_1800_e.png" alt="Powered by Struts"/>
    <% } else if (id == 1867) { %>
        <html:img page="/pic/b6_final_simu2_b_1867_e.png" alt="Powered by Struts"/>
    <% } %>

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

    <html:img page="/struts-power.gif" alt="Powered by Struts"/>

  </body>
</html:html>