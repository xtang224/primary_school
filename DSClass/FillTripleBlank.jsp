<%@ include file="taglibs.jsp" %>

<html:html locale="true">
  <head>   
    <html:base/>
  </head>
  <body bgcolor="white"><p>    

   <html:errors/><p> 

    <logic:present name="filltripleblankbean" scope="session">
       <h1>
         <bean:message key="hello.jsp.page.problem"/>
         <bean:write name="filltripleblankbean" property="id" /><p>
         <bean:message key="hello.jsp.page.problemScore"/>
         <bean:write name="filltripleblankbean" property="score" /> 
         <bean:write name="filltripleblankbean" property="type" /><p>          
       </h1>
    </logic:present>  

    <logic:present name="personbean" scope="session">
       <h1>
         <bean:message key="hello.jsp.page.selectedStudent"/><bean:write name="personbean" property="userName" />
         <bean:message key="hello.jsp.page.doubleColon"/><bean:write name="personbean" property="trueName" /><p>          
       </h1>
    </logic:present>  
    
    <%! HttpSession session = null; 
        hello.FillTripleBlankBean _ftbb = null;
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
       _ftbb = (hello.FillTripleBlankBean)request.getAttribute(hello.Constants.FILLTRIPLEBLANK_KEY);
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

       String _correctAnswer = _ftbb.getSolution_1st() + ";" + _ftbb.getSolution_2nd() + ";" + _ftbb.getSolution_3rd(); 
       id = _pb2.getCurrentProblemId();
    %>
    <h1><%= _ftbb.getStatement_1st()%><%= _ftbb.getStatement_2nd()%><%= _ftbb.getStatement_3rd()%><%= _ftbb.getStatement_4th()%></h1>

    <% if (id==109 || id==110) { %>
        <html:img page="/pic/b6_ch4__2_b_1230.png" alt="Powered by Struts"/>
    <% } else if (id==115) { %>
        <html:img page="/pic/b6_ch3_tb_115.png" alt="Powered by Struts"/>
    <% } else if (id==122) { %>
        <html:img page="/pic/b6_ch5_tb_122.png" alt="Powered by Struts"/>
    <% } else if (id==162) { %>
        <html:img page="/pic/b6_ch3_class1_tb_162.png" alt="Powered by Struts"/>
    <% } else if (id==163) { %>
        <html:img page="/pic/b6_ch3_class1_tb_163.png" alt="Powered by Struts"/>
    <% } else if (id==164) { %>
        <html:img page="/pic/b6_ch3_class1_tb_164.png" alt="Powered by Struts"/>
    <% } else if (id==165) { %>
        <html:img page="/pic/b6_ch3_class1_tb_165.png" alt="Powered by Struts"/>
    <% } else if (id==166) { %>
        <html:img page="/pic/b6_ch3_class1_tb_166.png" alt="Powered by Struts"/>
    <% } else if (id==172) { %>
        <html:img page="/pic/b6_ch6_5_tb_172.png" alt="Powered by Struts"/>
    <% } else if (id==187) { %>
        <html:img page="/pic/b6_ch6_10_tb_187.png" alt="Powered by Struts"/>
    <% } else if (id==192) { %>
        <html:img page="/pic/b6_ch6_11_tb_192.png" alt="Powered by Struts"/>
    <% } else if (id==195) { %>
        <html:img page="/pic/b6_ch6_12_tb_195_1.png" alt="Powered by Struts"/><html:img page="/pic/b6_ch6_12_tb_195_2.png" alt="Powered by Struts"/><html:img page="/pic/b6_ch6_12_tb_195_3.png" alt="Powered by Struts"/>
    <% } else if (id==196) { %>
        <html:img page="/pic/b6_ch6_12_tb_196.png" alt="Powered by Struts"/>
    <% } else if (id==198) { %>
        <html:img page="/pic/b6_final_self_tb_198.png" alt="Powered by Struts"/>
    <% } else if (id==203) { %>
        <html:img page="/pic/b6_ch6_class7_tb_203.png" alt="Powered by Struts"/>
    <% } else if (id==204) { %>
        <html:img page="/pic/b6_ch6_class7_tb_204.png" alt="Powered by Struts"/>
    <% } else if (id==206) { %>
        <html:img page="/pic/b6_ch6_class8_tb_206.png" alt="Powered by Struts"/>
    <% } else if (id==207) { %>
        <html:img page="/pic/b6_ch6_class8_tb_207.png" alt="Powered by Struts"/>
    <% } else if (id==223) { %>
        <html:img page="/pic/b6_final_1_tb_223_qb_112.png" alt="Powered by Struts"/>
    <% } else if (id==226 || id==227) { %>
        <html:img page="/pic/b6_final_2_tb_226_227.png" alt="Powered by Struts"/>
    <% } else if (id==240) { %>
        <html:img page="/pic/b6_final_3_tb_240_qb_120.png" alt="Powered by Struts"/>
    <% } else if (id==254 || id==255) { %>
        <html:img page="/pic/b6_final_4_tb_254_255.png" alt="Powered by Struts"/>
    <% } else if (id==256) { %>
        <html:img page="/pic/b6_final_4_tb_256_qb_133_1.png" alt="Powered by Struts"/><html:img page="/pic/b6_final_4_tb_256_qb_133_2.png" alt="Powered by Struts"/>
    <% } else if (id==266) { %>
        <html:img page="/pic/b6_final_com2_tb_266_qb_138.png" alt="Powered by Struts"/>
    <% } else if (id==277) { %>
        <html:img page="/pic/b6_final_6_tb_277.png" alt="Powered by Struts"/>
    <% } else if (id==280) { %>
        <html:img page="/pic/b6_final_class6_tb_280.png" alt="Powered by Struts"/>
    <% } else if (id==281) { %>
        <html:img page="/pic/b6_final_class6_tb_281.png" alt="Powered by Struts"/>
    <% } else if (id==282) { %>
        <html:img page="/pic/b6_final_class7_b_1801_tb_282_qb_153_1.png" alt="Powered by Struts"/><html:img page="/pic/b6_final_class7_tb_282_qb_153_2.png" alt="Powered by Struts"/>
    <% } else if (id==300) { %>
        <html:img page="/pic/b6_final_simu_tb_300.png" alt="Powered by Struts"/>
    <% } else if (id==301) { %>
        <html:img page="/pic/b6_final_simu_tb_301.png" alt="Powered by Struts"/>
    <% } else if (id==302) { %>
        <html:img page="/pic/b6_final_2014_spring_tb_302.png" alt="Powered by Struts"/>
    <% } else if (id==308) { %>
        <html:img page="/pic/b6_final_2014_spring_tb_308.png" alt="Powered by Struts"/>
    <% } else if (id==309) { %>
        <html:img page="/pic/b6_final_2014_autumn_tb_309.png" alt="Powered by Struts"/>
    <% } else if (id==310) { %>
        <html:img page="/pic/b6_final_2014_autumn_tb_310.png" alt="Powered by Struts"/>
    <% } else if (id==311) { %>
        <html:img page="/pic/b6_final_2014_autumn_tb_311.png" alt="Powered by Struts"/>
    <% } else if (id==320) { %>
        <html:img page="/pic/b6_final_simu3_tb_320.png" alt="Powered by Struts"/>
    <% } else if (id==321) { %>
        <html:img page="/pic/b6_final_simu3_tb_321.png" alt="Powered by Struts"/>
    <% } else if (id==330) { %>
        <html:img page="/pic/b6_final_simu5_tb_330.png" alt="Powered by Struts"/>
    <% } else if (id==337) { %>
        <html:img page="/pic/b6_final_real_tb_337.png" alt="Powered by Struts"/>
    <% } else if (id==338) { %>
        <html:img page="/pic/b6_final_real_tb_338.png" alt="Powered by Struts"/>
    <% } %>


    <% remainSeconds = _pb2.getRemainSeconds();
       if (remainSeconds > 0 && _pb2.getPlanStatus().equals("not finished")){ 
    %>

    <html:form action="/SmartRandomCheck.do" >

      <bean:message key="hello.jsp.prompt.filltripleblank"/>
      <html:text property="inputAnswer" size="36" maxlength="100"/><br>
      <html:hidden property="correctAnswer" value="<%= _correctAnswer%>"/>
      <html:hidden property="solution" value="<%= _correctAnswer%>"/>
      <html:hidden property="source" value="<%= _ftbb.getSource()%>"/>
      <html:hidden property="statement_1st" value="<%= _ftbb.getStatement_1st()%>"/>
      <html:hidden property="statement_2nd" value="<%= _ftbb.getStatement_2nd()%>"/>
      <html:hidden property="statement_3rd" value="<%= _ftbb.getStatement_3rd()%>"/>
      <html:hidden property="statement_4th" value="<%= _ftbb.getStatement_4th()%>"/>
      
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
