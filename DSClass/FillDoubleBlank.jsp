<%@ include file="taglibs.jsp" %>

<html:html locale="true">
  <head>   
    <html:base/>
  </head>
  <body bgcolor="white"><p>    

   <html:errors/><p> 

    <logic:present name="filldoubleblankbean" scope="session">
       <h1>
         <bean:message key="hello.jsp.page.problem"/>
         <bean:write name="filldoubleblankbean" property="id" /><p>
         <bean:message key="hello.jsp.page.problemScore"/>
         <bean:write name="filldoubleblankbean" property="score" /><p> 
         <bean:write name="filldoubleblankbean" property="type" /><p>
   <!--  <bean:write name="filldoubleblankbean" property="statement_1st" />   
         <bean:write name="filldoubleblankbean" property="statement_2nd" />      -->        
       </h1>
    </logic:present>  

    <logic:present name="personbean" scope="session">
       <h1>
         <bean:message key="hello.jsp.page.selectedStudent"/><bean:write name="personbean" property="userName" />
         <bean:message key="hello.jsp.page.doubleColon"/><bean:write name="personbean" property="trueName" /><p>          
       </h1>
    </logic:present> 

    
    <%! HttpSession session = null; 
        hello.FillDoubleBlankBean _fdbb = null;
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
        int id=0;
    %>
    <% session = request.getSession(true);     
       _fdbb = (hello.FillDoubleBlankBean)request.getAttribute(hello.Constants.FILLDOUBLEBLANK_KEY);
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

       String _correctAnswer = _fdbb.getSolution_1st() + ";" + _fdbb.getSolution_2nd();
       id =  _pb2.getCurrentProblemId();   
    %>
    <h1><%= _fdbb.getStatement_1st()%><%= _fdbb.getStatement_2nd()%><%= _fdbb.getStatement_3rd()%></h1>

    <% if (id == 975) { %>
        <html:img page="/pic/b6_ch3_1_db97.PNG" alt="Powered by Struts"/>
    <% } else if (id == 976) { %>
        <html:img page="/pic/b6_ch3_1_db98.PNG" alt="Powered by Struts"/>
    <% } else if (id == 985) { %>
        <html:img page="/pic/b6_ch4_class6_db985.png" alt="Powered by Struts"/>
    <% } else if (id == 986) { %>
        <html:img page="/pic/b6_ch4_class7_db986.png" alt="Powered by Struts"/>
    <% } else if (id == 987) { %>
        <html:img page="/pic/b6_ch4_4_qb_9.png" alt="Powered by Struts"/>
    <% } else if (id == 988) { %>
        <html:img page="/pic/b6_ch4_5_db_988.png" alt="Powered by Struts"/>
    <% } else if (id == 990) { %>
        <html:img page="/pic/b6_ch4_7_db990.png" alt="Powered by Struts"/>
    <% } else if (id == 991) { %>
        <html:img page="/pic/b6_ch4_8_db991.png" alt="Powered by Struts"/>
    <% } else if (id == 1002) { %>
        <html:img page="/pic/b6_ch4_db_1002.png" alt="Powered by Struts"/>
    <% } else if (id == 1008) { %>
        <html:img page="/pic/b6_mid_1_db_1008.png" alt="Powered by Struts"/>
    <% } else if (id == 1013) { %>
        <html:img page="/pic/b6_mid_2_db_1013.png" alt="Powered by Struts"/>
    <% } else if (id == 1021) { %>
        <html:img page="/pic/b6_ch3_db_1021.png" alt="Powered by Struts"/>
    <% } else if (id == 1029) { %>
        <html:img page="/pic/b6_ch2_db_1029.png" alt="Powered by Struts"/>
    <% } else if (id == 1032) { %>
        <html:img page="/pic/b6_ch5_db_1032.png" alt="Powered by Struts"/><br><html:img page="/pic/b6_ch5_db_1032_2.png" alt="Powered by Struts"/>
    <% } else if (id == 1053) { %>
        <html:img page="/pic/b6_ch6_3_q_572.png" alt="Powered by Struts"/><br>
    <% } else if (id == 1054) { %>
        <html:img page="/pic/b6_ch6_3_db_1054.png" alt="Powered by Struts"/><br>
    <% } else if (id == 1055) { %>
        <html:img page="/pic/b6_ch6_3_db_1055.png" alt="Powered by Struts"/><br>
    <% } else if (id == 1062) { %>
        <html:img page="/pic/b6_ch1_class2_db_1062.png" alt="Powered by Struts"/><br>
    <% } else if (id == 1065) { %>
        <html:img page="/pic/b6_ch1_1_db_1065_1.png" alt="Powered by Struts"/><html:img page="/pic/b6_ch1_1_db_1065_2.png" alt="Powered by Struts"/><html:img page="/pic/b6_ch1_1_db_1065_3.png" alt="Powered by Struts"/><html:img page="/pic/b6_ch1_1_db_1065_4.png" alt="Powered by Struts"/><html:img page="/pic/b6_ch1_1_db_1065_5.png" alt="Powered by Struts"/><br>
    <% } else if (id == 1089) { %>
        <html:img page="/pic/b6_ch3_2_db_1089.png" alt="Powered by Struts"/><br>
    <% } else if (id == 1091) { %>
        <html:img page="/pic/b6_ch3_5_db_1091.png" alt="Powered by Struts"/><br>
    <% } else if (id == 1092) { %>
        <html:img page="/pic/b6_ch3_6_db_1092.png" alt="Powered by Struts"/><br>
    <% } else if (id == 1093) { %>
        <html:img page="/pic/b6_ch3_6_db_1093.png" alt="Powered by Struts"/><br>
    <% } else if (id == 1099) { %>
        <html:img page="/pic/b6_ch3_class3_db_1099.png" alt="Powered by Struts"/><br>
    <% } else if (id == 1100) { %>
        <html:img page="/pic/b6_ch3_class3_db_1100.png" alt="Powered by Struts"/><br>
    <% } else if (id == 1101) { %>
        <html:img page="/pic/b6_ch3_class3_db_1101.png" alt="Powered by Struts"/><br>
    <% } else if (id == 1120) { %>
        <html:img page="/pic/b6_ch6_4_db_1120.png" alt="Powered by Struts"/><br>
    <% } else if (id == 1121) { %>
        <html:img page="/pic/b6_ch6_4_db_1121_1.png" alt="Powered by Struts"/><html:img page="/pic/b6_ch6_4_db_1121_2.png" alt="Powered by Struts"/><br>
    <% } else if (id == 1128) { %>
        <html:img page="/pic/b6_ch6_6_db_1128.png" alt="Powered by Struts"/><br>
    <% } else if (id == 1129) { %>
        <html:img page="/pic/b6_ch6_6_db_1129.png" alt="Powered by Struts"/><br>
    <% } else if (id == 1130) { %>
        <html:img page="/pic/b6_ch6_6_db_1130.png" alt="Powered by Struts"/><br>
    <% } else if (id == 1134) { %>
        <html:img page="/pic/b6_ch6_7_db_1134.png" alt="Powered by Struts"/><br>
    <% } else if (id == 1135) { %>
        <html:img page="/pic/b6_ch6_7_db_1135.png" alt="Powered by Struts"/><br>
    <% } else if (id == 1136) { %>
        <html:img page="/pic/b6_ch6_7_db_1136.png" alt="Powered by Struts"/><br>
    <% } else if (id == 1142) { %>
        <html:img page="/pic/b6_ch6_8_db_1142.png" alt="Powered by Struts"/><br>
    <% } else if (id == 1147) { %>
        <html:img page="/pic/b6_ch6_9_db_1147.png" alt="Powered by Struts"/><html:img page="/pic/b6_ch6_9_db_1147_2.png" alt="Powered by Struts"/><br>
    <% } else if (id == 1150) { %>
        <html:img page="/pic/b6_ch6_10_db_1150.png" alt="Powered by Struts"/><br><html:img page="/pic/b6_ch6_10_db_1150_2.png" alt="Powered by Struts"/><br>
    <% } else if (id == 1151) { %>
        <html:img page="/pic/b6_ch6_10_db_1151.png" alt="Powered by Struts"/><br>
    <% } else if (id == 1153) { %>
        <html:img page="/pic/b6_ch6_10_db_1153.png" alt="Powered by Struts"/><br>
    <% } else if (id == 1154) { %>
        <html:img page="/pic/b6_ch6_hw5_db_1154.png" alt="Powered by Struts"/><br>
    <% } else if (id == 1155) { %>
        <html:img page="/pic/b6_ch6_hw5_db_1155.png" alt="Powered by Struts"/><br>
    <% } else if (id == 1156) { %>
        <html:img page="/pic/b6_ch6_hw5_db_1156.png" alt="Powered by Struts"/><br>
    <% } else if (id == 1157) { %>
        <html:img page="/pic/b6_ch6_hw5_db_1157.png" alt="Powered by Struts"/><br>
    <% } else if (id == 1158) { %>
        <html:img page="/pic/b6_ch6_hw5_db_1158.png" alt="Powered by Struts"/><br>
    <% } else if (id == 1160) { %>
        <html:img page="/pic/b6_ch6_hw5_db_1160.png" alt="Powered by Struts"/><br>
    <% } else if (id == 1161) { %>
        <html:img page="/pic/b6_ch6_hw5_db_1161_qb_88.png" alt="Powered by Struts"/><br>
    <% } else if (id == 1162) { %>
        <html:img page="/pic/b6_ch6_hw6_db_1162.png" alt="Powered by Struts"/><br>
    <% } else if (id == 1173) { %>
        <html:img page="/pic/b6_final_self_db_1173.png" alt="Powered by Struts"/><br>
    <% } else if (id == 1174) { %>
        <html:img page="/pic/b6_final_self_db_1174.png" alt="Powered by Struts"/><br>
    <% } else if (id == 1175) { %>
        <html:img page="/pic/b6_final_self_db_1175.png" alt="Powered by Struts"/><br>
    <% } else if (id>=1177 && id<=1179) { %>
        <html:img page="/pic/b6_final_self_db_1177_1179.png" alt="Powered by Struts"/><br>
    <% } else if (id==1184) { %>
        <html:img page="/pic/b6_ch6_class8_db_1184.png" alt="Powered by Struts"/><br>
    <% } else if (id==1185) { %>
        <html:img page="/pic/b6_ch6_class10_db_1185.png" alt="Powered by Struts"/><br>
    <% } else if (id==1203) { %>
        <html:img page="/pic/b6_final_1_db_1203.png" alt="Powered by Struts"/><br>
    <% } else if (id==1204) { %>
        <html:img page="/pic/b6_final_1_db_1204.png" alt="Powered by Struts"/><br>
    <% } else if (id==1206) { %>
        <html:img page="/pic/b6_final_1_db_1206.png" alt="Powered by Struts"/><br>
    <% } else if (id==1219) { %>
        <html:img page="/pic/b6_final_3_db_1219.png" alt="Powered by Struts"/><br>
    <% } else if (id==1252) { %>
        <html:img page="/pic/b6_final_class4_db_1252.png" alt="Powered by Struts"/><br>
    <% } else if (id==1256) { %>
        <html:img page="/pic/b6_final_6_db_1256.png" alt="Powered by Struts"/><br>
    <% } else if (id==1279) { %>
        <html:img page="/pic/b6_final_com4_db_1279.png" alt="Powered by Struts"/><br>
    <% } else if (id==1294) { %>
        <html:img page="/pic/b6_final_simu2_db_1294_qb_174.png" alt="Powered by Struts"/><br>
    <% } else if (id==1298) { %>
        <html:img page="/pic/b6_final_2014_spring_db_1298.png" alt="Powered by Struts"/><br>
    <% } else if (id==1299) { %>
        <html:img page="/pic/b6_final_2014_autumn_db_1299_qb_182_183_184.png" alt="Powered by Struts"/><br>
    <% } else if (id==1300) { %>
        <html:img page="/pic/b6_final_2014_autumn_db_1300.png" alt="Powered by Struts"/><br>
    <% } else if (id==1306) { %>
        <html:img page="/pic/b6_final_simu3_db_1306.png" alt="Powered by Struts"/><br>
    <% } else if (id==1311) { %>
        <html:img page="/pic/b6_final_simu4_db_1311.png" alt="Powered by Struts"/><br>
    <% } else if (id==1319) { %>
        <html:img page="/pic/b6_final_simu4_db_1319.png" alt="Powered by Struts"/><br>
    <% } else if (id==1321) { %>
        <html:img page="/pic/b6_final_simu5_db_1321_1.png" alt="Powered by Struts"/><html:img page="/pic/b6_final_simu5_db_1321_2.png" alt="Powered by Struts"/><br>
    <% } %>

     <% remainSeconds = _pb2.getRemainSeconds();
       if (remainSeconds > 0 && _pb2.getPlanStatus().equals("not finished")){ 
    %>

    <html:form action="/SmartRandomCheck.do" >

      <bean:message key="hello.jsp.prompt.filldoubleblank"/>
      <html:text property="inputAnswer" size="36" maxlength="100"/><br>
      <html:hidden property="correctAnswer" value="<%= _correctAnswer%>"/>
      <html:hidden property="solution" value="<%= _correctAnswer%>"/>
      <html:hidden property="source" value="<%= _fdbb.getSource()%>"/>
      <html:hidden property="statement_1st" value="<%= _fdbb.getStatement_1st()%>"/>
      <html:hidden property="statement_2nd" value="<%= _fdbb.getStatement_2nd()%>"/>
      <html:hidden property="statement_3rd" value="<%= _fdbb.getStatement_3rd()%>"/>
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
