<%@ include file="taglibs.jsp" %>

<html:html locale="true">
  <head>   
    <html:base/>
  </head>
  <body bgcolor="white"><p>    

   <html:errors/><p> 

    <logic:present name="problembean" scope="session">
       <h1>
         <bean:message key="hello.jsp.page.problem"/>
         <bean:write name="problembean" property="id" /><p>
         <bean:message key="hello.jsp.page.problemScore"/>
         <bean:write name="problembean" property="score" /><p> 
         <bean:write name="problembean" property="type" /><p> 
         <bean:write name="problembean" property="statement" /><p>
         <bean:write name="problembean" property="choiceA" /><p>
         <bean:write name="problembean" property="choiceB" /><p>
         <bean:write name="problembean" property="choiceC" /><p>
         <bean:write name="problembean" property="choiceD" /><p>
       </h1>
    </logic:present> 

    <logic:present name="personbean" scope="session">
       <h1>
         <bean:message key="hello.jsp.page.selectedStudent"/><bean:write name="personbean" property="userName" />
         <bean:message key="hello.jsp.page.doubleColon"/><bean:write name="personbean" property="trueName" /><p>          
       </h1>
    </logic:present>   
  
    <%! HttpSession session = null; 
        hello.SmartProblemBean _pb = null;
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
        
        int remainSeconds = 300;
        int id = 0;
    %>
    <% session = request.getSession(true);     
       _pb = (hello.SmartProblemBean)request.getAttribute(hello.Constants.PROBLEM_KEY);
       _pb2 = (hello.PersonBean)request.getAttribute(hello.Constants.PERSON_KEY);
       _hs = (String)request.getAttribute(hello.Constants.HASHSET_KEY);
       _hs_tf = (String)request.getAttribute(hello.Constants.HASHSET_TF_KEY);
       _mhs = (String)request.getAttribute(hello.Constants.HASHSET_M_KEY);
       _hs_fb = (String)request.getAttribute(hello.Constants.HASHSET_FB_KEY);
       _hs_fdb = (String)request.getAttribute(hello.Constants.HASHSET_FDB_KEY);
       _hs_ftb = (String)request.getAttribute(hello.Constants.HASHSET_FTB_KEY);
       _hs_fqb = (String)request.getAttribute(hello.Constants.HASHSET_FQB_KEY); 
       _hs_m = (String)request.getAttribute(hello.Constants.HASHSET_MY_KEY); 
       _hs_tm = (String)request.getAttribute(hello.Constants.HASHSET_TM_KEY);
       _hs_d = (String)request.getAttribute(hello.Constants.HASHSET_D_KEY); 
       _hs_pm = (String)request.getAttribute(hello.Constants.HASHSET_PM_KEY);        

       id = _pb2.getCurrentProblemId();      
    %>

    <% if (id == 493) { %>
        <html:img page="/pic/b6_ch4_9_q_493.png" alt="Powered by Struts"/>
    <% } else if (id == 519) { %>
        <html:img page="/pic/b6_mid_2_q_519.png" alt="Powered by Struts"/>
    <% } else if (id == 521) { %>
        <h1>1</h1><html:img page="/pic/b6_ch3_q_521_a.png" alt="Powered by Struts"/><br><h1>2</h1><html:img page="/pic/b6_ch3_q_521_b.png" alt="Powered by Struts"/><br><h1>3</h1><html:img page="/pic/b6_ch3_q_521_c.png" alt="Powered by Struts"/>
    <% } else if (id == 538) { %>
        <html:img page="/pic/b6_ch1_q_538.png" alt="Powered by Struts"/>
    <% } else if (id == 572) { %>
        <html:img page="/pic/b6_ch6_3_q_572.png" alt="Powered by Struts"/>
    <% } else if (id == 577) { %>
        <html:img page="/pic/b6_ch1_self_q_577.png" alt="Powered by Struts"/>
    <% } else if (id == 607) { %>
        <html:img page="/pic/b6_ch6_4_q_607.png" alt="Powered by Struts"/>
    <% } else if (id == 609) { %>
        <html:img page="/pic/b6_ch6_5_q_609.png" alt="Powered by Struts"/>
    <% } else if (id == 613) { %>
        <html:img page="/pic/b6_ch6_7_q_613.png" alt="Powered by Struts"/>
    <% } else if (id>=617 && id<=619) { %>
        <html:img page="/pic/b6_ch6_7_q_617.png" alt="Powered by Struts"/>
    <% } else if (id==626) { %>
        <html:img page="/pic/b6_ch6_8_q_626.png" alt="Powered by Struts"/>
    <% } else if (id==627) { %>
        <html:img page="/pic/b6_ch6_8_q_627.png" alt="Powered by Struts"/>
    <% } else if (id==636) { %>
        <html:img page="/pic/b6_ch6_9_q_636.png" alt="Powered by Struts"/>
    <% } else if (id==638) { %>
        <html:img page="/pic/b6_ch6_10_q_638.png" alt="Powered by Struts"/>
    <% } else if (id>=640 && id<=642) { %>
        <html:img page="/pic/b6_ch6_hw5_q_640_642.png" alt="Powered by Struts"/>
    <% } else if (id>=643 && id<=645) { %>
        <html:img page="/pic/b6_ch6_hw5_q_643_645.png" alt="Powered by Struts"/>
    <% } else if (id==653) { %>
        <html:img page="/pic/b6_ch6_12_q_653.png" alt="Powered by Struts"/>
    <% } else if (id==676) { %>
        <html:img page="/pic/b6_final_class2_q_676.png" alt="Powered by Struts"/>
    <% } else if (id==689) { %>
        <html:img page="/pic/b6_final_2_q_689.png" alt="Powered by Struts"/>
    <% } else if (id==709) { %>
        <html:img page="/pic/b6_final_4_q_709.png" alt="Powered by Struts"/>
    <% } else if (id==710) { %>
        <html:img page="/pic/b6_final_4_q_710.png" alt="Powered by Struts"/>
    <% } else if (id==713) { %>
        <html:img page="/pic/b6_final_5_q_713.png" alt="Powered by Struts"/>
    <% } else if (id==714) { %>
        <html:img page="/pic/b6_final_5_q_714.png" alt="Powered by Struts"/>
    <% } else if (id==715) { %>
        <html:img page="/pic/b6_final_5_q_715.png" alt="Powered by Struts"/>
    <% } else if (id==716) { %>
        <html:img page="/pic/b6_final_5_q_716.png" alt="Powered by Struts"/>
    <% } else if (id==731) { %>
        <html:img page="/pic/b6_final_6_q_731.png" alt="Powered by Struts"/>
    <% } else if (id==734) { %>
        <html:img page="/pic/b6_final_6_q_734.png" alt="Powered by Struts"/>
    <% } else if (id==736) { %>
        <html:img page="/pic/b6_final_6_q_736.png" alt="Powered by Struts"/>
    <% } else if (id==746) { %>
        <html:img page="/pic/b6_final_com3_q_746.png" alt="Powered by Struts"/>
    <% } else if (id==747) { %>
        <html:img page="/pic/b6_final_com3_q_747.png" alt="Powered by Struts"/>
    <% } else if (id==756) { %>
        <html:img page="/pic/b6_final_class7_q_756.png" alt="Powered by Struts"/>
    <% } else if (id==765) { %>
        <html:img page="/pic/b6_final_com4_q_765_1.png" alt="Powered by Struts"/><html:img page="/pic/b6_final_com4_q_765_2.png" alt="Powered by Struts"/>
    <% } else if (id==767) { %>
        <html:img page="/pic/b6_final_com5_q_767.png" alt="Powered by Struts"/>
    <% } else if (id==785) { %>
        <html:img page="/pic/b6_final_2014_spring_q_785.png" alt="Powered by Struts"/>
    <% } else if (id==786) { %>
        <html:img page="/pic/b6_final_2014_spring_q_786.png" alt="Powered by Struts"/>
    <% } else if (id==812) { %>
        <html:img page="/pic/b6_final_simu5_q_812.png" alt="Powered by Struts"/>
    <% } else if (id==813) { %>
        <html:img page="/pic/b6_final_simu5_q_813.png" alt="Powered by Struts"/>
    <% } else if (id==814) { %>
        <html:img page="/pic/b6_final_simu5_q_814.png" alt="Powered by Struts"/>
    <% } else if (id==818) { %>
        <html:img page="/pic/b6_final_simu6_q_818.png" alt="Powered by Struts"/>
    <% } else if (id==824) { %>
        <html:img page="/pic/b6_final_real_q_824.png" alt="Powered by Struts"/>
    <% } %>


    <% remainSeconds = _pb2.getRemainSeconds();
       if (remainSeconds > 0 && _pb2.getPlanStatus().equals("not finished")){ 
    %>

    <html:form action="/SmartRandomCheck.do" >
      <bean:message key="hello.jsp.prompt.problem"/>
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
      <html:hidden property="answeredHashSet_ftb" value="<%= _hs_ftb%>"/>
      <html:hidden property="answeredHashSet_fqb" value="<%= _hs_fqb%>"/>
      <html:hidden property="answered_MY_HashSet" value="<%= _hs_m%>"/>
      <html:hidden property="answeredHashSet_tm" value="<%= _hs_tm%>"/>
      <html:hidden property="answered_D_HashSet" value="<%= _hs_d%>"/>
      <html:hidden property="answeredHashSet_pm" value="<%= _hs_pm%>"/>
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
      <html:hidden property="classId" value="<%= _pb2.getClassId()%>"/>

      <html:submit property="submit" value="Submit"/>
      <html:reset/>

    </html:form><br>

    <h2><bean:message key="hello.jsp.prompt.skip"/><br></h2>
    <html:form action="/SmartRandomCheck2.do" >     
      
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
      <html:hidden property="answeredHashSet_ftb" value="<%= _hs_ftb%>"/>
      <html:hidden property="answeredHashSet_fqb" value="<%= _hs_fqb%>"/>
      <html:hidden property="answered_MY_HashSet" value="<%= _hs_m%>"/>
      <html:hidden property="answeredHashSet_tm" value="<%= _hs_tm%>"/>
      <html:hidden property="answered_D_HashSet" value="<%= _hs_d%>"/>
      <html:hidden property="answeredHashSet_pm" value="<%= _hs_pm%>"/>
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
      <html:hidden property="classId" value="<%= _pb2.getClassId()%>"/>

      <html:submit property="submit" value="Skip this problem"/>
      <html:reset/>

    </html:form><br>

    <% } else { %>

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
      <html:hidden property="answered_MY_HashSet" value="<%= _hs_m%>"/>
      <html:hidden property="answeredHashSet_tm" value="<%= _hs_tm%>"/>
      <html:hidden property="answered_D_HashSet" value="<%= _hs_d%>"/>
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
