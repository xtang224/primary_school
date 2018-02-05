<%@ include file="taglibs.jsp" %>

<html:html locale="true">
  <head>   
    <html:base/>
  </head>
  <body bgcolor="white"><p>    

   <html:errors/><p> 

    <logic:present name="fillquadblankbean" scope="session">
       <h1>
         <bean:message key="hello.jsp.page.problem"/>
         <bean:write name="fillquadblankbean" property="id" /><p>         
         <bean:message key="hello.jsp.page.problemScore"/>
         <bean:write name="fillquadblankbean" property="score" /><p>
         <bean:write name="fillquadblankbean" property="type" /><p>          
       </h1>
    </logic:present>  

    <logic:present name="personbean" scope="session">
       <h1>
         <bean:message key="hello.jsp.page.selectedStudent"/><bean:write name="personbean" property="userName" />
         <bean:message key="hello.jsp.page.doubleColon"/><bean:write name="personbean" property="trueName" /><p>          
       </h1>
    </logic:present>  
    
    <%! HttpSession session = null; 
        hello.FillQuadBlankBean _fqbb = null;
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
       _fqbb = (hello.FillQuadBlankBean)request.getAttribute(hello.Constants.FILLQUADBLANK_KEY);
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

       String _correctAnswer = _fqbb.getSolution_1st() + ";" + _fqbb.getSolution_2nd() + ";" + _fqbb.getSolution_3rd() + ";" + _fqbb.getSolution_4th(); 
       id = _pb2.getCurrentProblemId();
    %>
    <h1><%= _fqbb.getStatement_1st()%><%= _fqbb.getStatement_2nd()%><%= _fqbb.getStatement_3rd()%><%= _fqbb.getStatement_4th()%><%= _fqbb.getStatement_5th()%></h1>

     <% if (id==3) { %>
       <h1>1:</h1><html:img page="/pic/b6_ch3_1_qb1_1.PNG" alt="Powered by Struts"/><h1>2:</h1><html:img page="/pic/b6_ch3_1_qb1_2.PNG" alt="Powered by Struts"/><h1>3:</h1><html:img page="/pic/b6_ch3_1_qb1_3.PNG" alt="Powered by Struts"/>
    <% } else if (id==8){ %>
        <html:img page="/pic/b6_ch4_class7_b1146.jpg" alt="Powered by Struts"/>
    <% } else if (id==9){ %>
        <html:img page="/pic/b6_ch4_4_qb_9.png" alt="Powered by Struts"/>
    <% } else if (id==10){ %>
        <html:img page="/pic/b6_ch4_5_qb_10.PNG" alt="Powered by Struts"/>
    <% } else if (id==11){ %>
        <html:img page="/pic/b6_ch4_5_qb_11.png" alt="Powered by Struts"/>
    <% } else if (id==12){ %>
        <html:img page="/pic/b6_ch4_class9_qb_12.png" alt="Powered by Struts"/>
    <% } else if (id==14){ %>
        <html:img page="/pic/b6_ch4_qb_14.png" alt="Powered by Struts"/>
    <% } else if (id==17){ %>
        <html:img page="/pic/b6_ch3_qb_17.png" alt="Powered by Struts"/>
    <% } else if (id==21){ %>
        <html:img page="/pic/b6_ch1_qb_21.png" alt="Powered by Struts"/>
    <% } else if (id==22){ %>
        <html:img page="/pic/b6_ch1_qb_22.png" alt="Powered by Struts"/>
    <% } else if (id==24){ %>
        <html:img page="/pic/b6_ch6_1_b_1347.png" alt="Powered by Struts"/>
    <% } else if (id==50){ %>
        <html:img page="/pic/b6_ch3_6_qb_50.png" alt="Powered by Struts"/>
    <% } else if (id==64){ %>
        <html:img page="/pic/b6_ch6_8_qb_64.png" alt="Powered by Struts"/>
    <% } else if (id==65){ %>
        <html:img page="/pic/b6_ch6_8_qb_65.png" alt="Powered by Struts"/>
    <% } else if (id>=74 && id<=75){ %>
        <html:img page="/pic/b6_ch6_9_qb_74.png" alt="Powered by Struts"/>
    <% } else if (id==76){ %>
        <html:img page="/pic/b6_ch6_9_qb_76.png" alt="Powered by Struts"/>
    <% } else if (id>=77 && id<=79){ %>
        <html:img page="/pic/b6_ch6_9_qb_77.png" alt="Powered by Struts"/>
    <% } else if (id==84){ %>
        <html:img page="/pic/b6_ch6_10_db_1150.png" alt="Powered by Struts"/>
    <% } else if (id==85){ %>
        <html:img page="/pic/b6_ch6_class6_qb_85.png" alt="Powered by Struts"/>
    <% } else if (id==87){ %>
        <html:img page="/pic/b6_ch6_hw5_qb_87.png" alt="Powered by Struts"/>
    <% } else if (id==88){ %>
        <html:img page="/pic/b6_ch6_hw5_db_1161_qb_88.png" alt="Powered by Struts"/>
    <% } else if (id==99){ %>
        <html:img page="/pic/b6_ch6_class7_b_1580_qb_99.png" alt="Powered by Struts"/>
    <% } else if (id==112){ %>
        <html:img page="/pic/b6_final_1_tb_223_qb_112.png" alt="Powered by Struts"/>
    <% } else if (id==119){ %>
        <html:img page="/pic/b6_final_3_qb_119.png" alt="Powered by Struts"/>
    <% } else if (id==120){ %>
        <html:img page="/pic/b6_final_3_tb_240_qb_120.png" alt="Powered by Struts"/>
    <% } else if (id==133){ %>
        <html:img page="/pic//b6_final_4_tb_256_qb_133_1.png" alt="Powered by Struts"/><html:img page="/pic//b6_final_4_tb_256_qb_133_2.png" alt="Powered by Struts"/>
    <% } else if (id==138){ %>
        <html:img page="/pic/b6_final_com2_tb_266_qb_138.png" alt="Powered by Struts"/>
    <% } else if (id==139 || id==140){ %>
        <html:img page="/pic/b6_final_5_qb_139_140.png" alt="Powered by Struts"/>
    <% } else if (id==141){ %>
        <html:img page="/pic/b6_final_5_qb_141.png" alt="Powered by Struts"/>
    <% } else if (id==144){ %>
        <html:img page="/pic/b6_final_6_qb_144.png" alt="Powered by Struts"/>
    <% } else if (id==149){ %>
        <html:img page="/pic/b6_final_com3_b_1770_qb_149.png" alt="Powered by Struts"/>
    <% } else if (id==150 || id==151){ %>
        <html:img page="/pic/b6_final_class6_qb_150_151.png" alt="Powered by Struts"/>
    <% } else if (id==152){ %>
        <html:img page="/pic/b6_final_class7_b_1797_e_qb_152.png" alt="Powered by Struts"/>
    <% } else if (id==153){ %>
        <html:img page="/pic/b6_final_class7_b_1801_tb_282_qb_153_1.png" alt="Powered by Struts"/><html:img page="/pic/b6_final_class7_tb_282_qb_153_2.png" alt="Powered by Struts"/>
    <% } else if (id==154 || id==155){ %>
        <html:img page="/pic/b6_final_class7_qb_154_155.png" alt="Powered by Struts"/>
    <% } else if (id==156 || id==157){ %>
        <html:img page="/pic/b6_final_class7_b_1799_qb_156_157.png" alt="Powered by Struts"/>
    <% } else if (id==174){ %>
        <html:img page="/pic/b6_final_simu2_db_1294_qb_174.png" alt="Powered by Struts"/>
    <% } else if (id==179){ %>
        <html:img page="/pic/b6_final_2014_spring_tb_308_qb_179.png" alt="Powered by Struts"/>
    <% } else if (id==182 || id==183 || id==184){ %>
        <html:img page="/pic/b6_final_2014_autumn_db_1299_qb_182_183_184.png" alt="Powered by Struts"/>
    <% } else if (id==208){ %>
        <html:img page="/pic/b6_final_simu6_qb_208.png" alt="Powered by Struts"/>
    <% } else if (id==213){ %>
        <html:img page="/pic/b6_final_real_b_1955_qb_213.png" alt="Powered by Struts"/>
    <% } %>

     <% remainSeconds = _pb2.getRemainSeconds();
       if (remainSeconds > 0 && _pb2.getPlanStatus().equals("not finished")){ 
    %>

    <html:form action="/SmartRandomCheck.do" >

      <bean:message key="hello.jsp.prompt.filldoubleblank"/>
      <html:text property="inputAnswer" size="36" maxlength="100"/><br>
      <html:hidden property="correctAnswer" value="<%= _correctAnswer%>"/>
      <html:hidden property="solution" value="<%= _correctAnswer%>"/>
      <html:hidden property="source" value="<%= _fqbb.getSource()%>"/>
      <html:hidden property="statement_1st" value="<%= _fqbb.getStatement_1st()%>"/>
      <html:hidden property="statement_2nd" value="<%= _fqbb.getStatement_2nd()%>"/>
      <html:hidden property="statement_3rd" value="<%= _fqbb.getStatement_3rd()%>"/>
      <html:hidden property="statement_4th" value="<%= _fqbb.getStatement_4th()%>"/>
      <html:hidden property="statement_5th" value="<%= _fqbb.getStatement_5th()%>"/>
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
