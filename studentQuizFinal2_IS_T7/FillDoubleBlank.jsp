<%@ include file="taglibs.jsp" %>

<html:html locale="true">
  <head>   
    <html:base/>
  </head>
  <body bgcolor="white"><p>    

   <html:errors/><p> 

    <logic:present name="filldoubleblankbean" scope="session">
       <h2>
         <bean:message key="hello.jsp.page.problem"/>
         <bean:write name="filldoubleblankbean" property="id" /><p>
         <bean:write name="filldoubleblankbean" property="type" /><p>
   <!--  <bean:write name="filldoubleblankbean" property="statement_1st" />   
         <bean:write name="filldoubleblankbean" property="statement_2nd" />      -->        
       </h2>
    </logic:present>  

    
    <%! HttpSession session = null; 
        hello.FillDoubleBlankBean _fdbb = null;
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
        String doubleBlankHint = null;
        String strExit = null;

        String strInput = "";
        String strInput2 = "";
        String inputHint = "";
        String inputHint2 = "";

        String _correctAnswer = null;
        String _correctAnswer_1st = null;
        String _correctAnswer_2nd = null;
    %>
    <% session = request.getSession(true);     
       _fdbb = (hello.FillDoubleBlankBean)request.getAttribute(hello.Constants.FILLDOUBLEBLANK_KEY);
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

       _correctAnswer = _fdbb.getSolution_1st() + ";;" + _fdbb.getSolution_2nd(); 

       id = _pb2.getCurrentProblemId();

       try{
          Class.forName("org.hsqldb.jdbcDriver");
          myConnection = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/xdb", "SA", ""); 
          stmt = myConnection.prepareStatement("select term2 from hintMatch where term1=?");
          stmt.clearParameters();
          stmt.setString(1, "doubleBlank");
          rs = stmt.executeQuery();              
          while(rs.next()){
             doubleBlankHint = rs.getString(1);
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
          stmt.setString(1, "input");
          rs = stmt.executeQuery();              
          while(rs.next()){
             strInput = rs.getString(1);
          }

          stmt = myConnection.prepareStatement("select term2 from hintMatch where term1=?");
          stmt.clearParameters();
          stmt.setString(1, "input2");
          rs = stmt.executeQuery();              
          while(rs.next()){
             strInput2 = rs.getString(1);
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
    <h2><%= _fdbb.getStatement_1st()%><%= _fdbb.getStatement_2nd()%><%= _fdbb.getStatement_3rd()%></h2>
  
    <% if (id == 43) { %>
       <html:img page="/pic/db43.PNG" alt="Powered by Struts"/>
    <% } else if (id == 45) { %>
       <html:img page="/pic/ch3_test1_db45.PNG" alt="Powered by Struts"/>
    <% } else if (id>=48 && id<=51) { %>
       <html:img page="/pic/ch3_test8_db48.PNG" alt="Powered by Struts"/>
    <% } else if (id == 53) { %>
       <html:img page="/pic/ch3_test17_db53.PNG" alt="Powered by Struts"/>
    <% } else if (id == 54) { %>
       <html:img page="/pic/ch3_test15_b80.PNG" alt="Powered by Struts"/>
    <% } else if (id == 55) { %>
       <html:img page="/pic/ch3_test18_db55.PNG" alt="Powered by Struts"/>
    <% } else if (id == 56) { %>
       <html:img page="/pic/ch3_test19_db56.PNG" alt="Powered by Struts"/>
    <% } else if (id == 57) { %>
       <html:img page="/pic/ch3_test20_db57.PNG" alt="Powered by Struts"/>
    <% } else if (id == 58) { %>
       <html:img page="/pic/ch3_test21_db58.PNG" alt="Powered by Struts"/>
    <% } else if (id == 59) { %>
       <html:img page="/pic/ch3_test22_db59.PNG" alt="Powered by Struts"/>
    <% } else if (id == 60) { %>
       <html:img page="/pic/ch3_test23_db60.PNG" alt="Powered by Struts"/>
    <% } else if (id == 63) { %>
       <html:img page="/pic/ch3_test_extra_db63.PNG" alt="Powered by Struts"/><html:img page="/pic/ch3_test3_b70.PNG" alt="Powered by Struts"/>
    <% } else if (id == 64) { %>
       <html:img page="/pic/ch3_test_extra_db64_1.PNG" alt="Powered by Struts"/><html:img page="/pic/ch3_test_extra_db64_2.PNG" alt="Powered by Struts"/>
    <% } else if (id == 65) { %>
       <html:img page="/pic/ch3_test_extra_2.PNG" alt="Powered by Struts"/>
    <% } else if (id == 70) { %>
       <html:img page="/pic/ch23_test_extra_2.PNG" alt="Powered by Struts"/>
    <% } else if (id==71 || id==72) { %>
       <html:img page="/pic/ch23_test_extra_3.PNG" alt="Powered by Struts"/>
    <% } else if (id==73) { %>
       <html:img page="/pic/ch23_test_extra_4.PNG" alt="Powered by Struts"/>
    <% } else if (id==109) { %>
       <html:img page="/pic/mid_self_test1_b240.PNG" alt="Powered by Struts"/>
    <% } else if (id==110) { %>
       <html:img page="/pic/mid_self_test6_db110.PNG" alt="Powered by Struts"/>
    <% } else if (id==111) { %>
       <html:img page="/pic/mid_self_test7_db111.PNG" alt="Powered by Struts"/>
    <% } else if (id==112) { %>
       <html:img page="/pic/mid_self_test8_db112.PNG" alt="Powered by Struts"/>
    <% } else if (id==113) { %>
       <html:img page="/pic/mid_self_test9_db113.PNG" alt="Powered by Struts"/>
    <% } else if (id==114) { %>
       <html:img page="/pic/mid_self_test10_db114.PNG" alt="Powered by Struts"/>
    <% } else if (id==122) { %>
       <html:img page="/pic/mid_1_test1_db122.PNG" alt="Powered by Struts"/>
    <% } else if (id==152) { %>
       <html:img page="/pic/mid_2_test3_db152.PNG" alt="Powered by Struts"/>
    <% } else if (id==153) { %>
       <html:img page="/pic/mid_2_test4_db153.PNG" alt="Powered by Struts"/>
    <% } else if (id==155) { %>
       <html:img page="/pic/ch3_5_test2_b296.PNG" alt="Powered by Struts"/>
    <% } else if (id==160) { %>
       <html:img page="/pic/ch3_4_test1_db160.PNG" alt="Powered by Struts"/>
    <% } else if (id==161) { %>
       <html:img page="/pic/ch3_4_test1_db160.PNG" alt="Powered by Struts"/>
    <% } else if (id>=162 && id<=163) { %>
       <html:img page="/pic/ch3_3_test7_db162.PNG" alt="Powered by Struts"/>
    <% } else if (id>=164 && id<=167) { %>
       <html:img page="/pic/ch3_3_test8_db164.PNG" alt="Powered by Struts"/>
    <% } else if (id==168) { %>
       <html:img page="/pic/ch3_2_test1_db168.PNG" alt="Powered by Struts"/>
    <% } else if (id==169) { %>
       <html:img page="/pic/ch3_2_test3_db169.PNG" alt="Powered by Struts"/>
    <% } else if (id==170) { %>
       <html:img page="/pic/ch3_1_test1_db170.PNG" alt="Powered by Struts"/>
    <% } else if (id==191) { %>
       <html:img page="/pic/mid_s1_test1_db191.PNG" alt="Powered by Struts"/>
    <% } else if (id==221) { %>
       <html:img page="/pic/mid_s2_test1_db221.PNG" alt="Powered by Struts"/>
    <% } else if (id==351) { %>
       <html:img page="/pic/ch3_test1_db45.PNG" alt="Powered by Struts"/>
    <% } else if (id==352) { %>
       <html:img page="/pic/mid_real_test2_db352.PNG" alt="Powered by Struts"/>
    <% } else if (id==353) { %>
       <html:img page="/pic/mid_real_test3_db353.PNG" alt="Powered by Struts"/>
    <% } else if (id==357) { %>
       <html:img page="/pic/ch5_1_test3_db357.PNG" alt="Powered by Struts"/>
    <% } else if (id==358) { %>
       <html:img page="/pic/ch5_1_test4_db358.PNG" alt="Powered by Struts"/>
    <% } else if (id==359) { %>
       <html:img page="/pic/ch5_2_test1_db359.PNG" alt="Powered by Struts"/>
    <% } else if (id==360) { %>
       <html:img page="/pic/ch5_2_test2_db360.PNG" alt="Powered by Struts"/>
    <% } else if (id==361) { %>
       <html:img page="/pic/ch5_2_test3_db361.PNG" alt="Powered by Struts"/>
    <% } else if (id==362) { %>
       <html:img page="/pic/ch5_2_test4_db362.PNG" alt="Powered by Struts"/>
    <% } else if (id==366) { %>
       <html:img page="/pic/ch5_6_test1_db366.PNG" alt="Powered by Struts"/>
    <% } else if (id==367) { %>
       <html:img page="/pic/ch5_6_test2_db367.PNG" alt="Powered by Struts"/>
    <% } else if (id==368) { %>
       <html:img page="/pic/ch5_self_test1_db368.PNG" alt="Powered by Struts"/>
    <% } else if (id==369) { %>
       <html:img page="/pic/ch5_self_test2_db369.PNG" alt="Powered by Struts"/>
    <% } else if (id==370) { %>
       <html:img page="/pic/ch5_hk_test1_db370.PNG" alt="Powered by Struts"/>
    <% } else if (id==371 || id==372) { %>
       <html:img page="/pic/ch5_hk_test2_db371.PNG" alt="Powered by Struts"/>
    <% } else if (id==373 || id==374) { %>
       <html:img page="/pic/ch5_hk_test3_db373.PNG" alt="Powered by Struts"/>
    <% } else if (id==376) { %>
       <html:img page="/pic/ch5_test1_db376e.PNG" alt="Powered by Struts"/>
    <% } else if (id==380 || id==381) { %>
       <html:img page="/pic/ch5_test2_db380.PNG" alt="Powered by Struts"/>
    <% } else if (id==382 || id==383 || id==384) { %>
       <html:img page="/pic/ch5_test7_db382.PNG" alt="Powered by Struts"/>
    <% } else if (id==385) { %>
       <html:img page="/pic/ch5_test8_db385.PNG" alt="Powered by Struts"/>
    <% } else if (id==386 || id==387) { %>
       <html:img page="/pic/ch5_add_test1_db386.PNG" alt="Powered by Struts"/>
    <% } else if (id==388 || id==389) { %>
       <html:img page="/pic/ch5_add_test4_db388.PNG" alt="Powered by Struts"/>
    <% } else if (id==392) { %>
       <html:img page="/pic/ch5_add_test6_db392.PNG" alt="Powered by Struts"/>
    <% } else if (id==407) { %>
       <html:img page="/pic/ch6_2_test1_db407.PNG" alt="Powered by Struts"/>
    <% } else if (id==408 || id==409) { %>
       <html:img page="/pic/ch6_2_test2_db408.PNG" alt="Powered by Struts"/>
    <% } else if (id==445 || id==446) { %>
       <html:img page="/pic/ch6_5_test1_db445.PNG" alt="Powered by Struts"/>
    <% } else if (id==447 || id==448) { %>
       <html:img page="/pic/ch6_5_test2_db447.PNG" alt="Powered by Struts"/>
    <% } else if (id==607) { %>
       <html:img page="/pic/ch56_test1_db607.PNG" alt="Powered by Struts"/>
    <% } else if (id==613) { %>
       <html:img page="/pic/ch56_test2_db613.PNG" alt="Powered by Struts"/>
    <% } else if (id>=718 && id<=720) { %>
       <html:img page="/pic/final_1_test5_db718_1.png" alt="Powered by Struts"/><html:img page="/pic/final_1_test6_db718_2.png" alt="Powered by Struts"/>
    <% } else if (id>=725 && id<=726) { %>
       <html:img page="/pic/final_2_test3_b678.png" alt="Powered by Struts"/>
    <% } else if (id==728) { %>
       <html:img page="/pic/final_2_test5_db728.png" alt="Powered by Struts"/>
    <% } else if (id>=729 && id<=731) { %>
       <html:img page="/pic/ch7_test1_b706.png" alt="Powered by Struts"/>
    <% } else if (id>=732 && id<=734) { %>
       <html:img page="/pic/final_3_test1_b708_1.png" alt="Powered by Struts"/><html:img page="/pic/final_3_test2_b708_2.png" alt="Powered by Struts"/>
    <% } else if (id>=735 && id<=737) { %>
       <html:img page="/pic/ch7_test2_db735.png" alt="Powered by Struts"/>
    <% } else if (id>=826 && id<=827) { %>
       <html:img page="/pic/final_5_test1_db826.png" alt="Powered by Struts"/>
    <% } else if (id==828) { %>
       <html:img page="/pic/final_5_test2_db828.png" alt="Powered by Struts"/>
    <% } else if (id>=829 && id<=830) { %>
       <html:img page="/pic/final_5_test3_db829.png" alt="Powered by Struts"/>
    <% } else if (id==831) { %>
       <html:img page="/pic/final_5_test7_db831.png" alt="Powered by Struts"/>
    <% } else if (id==832) { %>
       <html:img page="/pic/final_5_test8_db832.png" alt="Powered by Struts"/>
    <% } else if (id==833) { %>
       <html:img page="/pic/final_5_test9_db833.png" alt="Powered by Struts"/>
    <% } else if (id==834) { %>
       <html:img page="/pic/final_5_test10_db834.png" alt="Powered by Struts"/>
    <% } else if (id==835) { %>
       <html:img page="/pic/final_5_test11_db835.png" alt="Powered by Struts"/>
    <% } else if (id==836) { %>
       <html:img page="/pic/final_5_test12_db836.png" alt="Powered by Struts"/>
    <% } else if (id==841) { %>
       <html:img page="/pic/final_2d_test0_db841.png" alt="Powered by Struts"/>
    <% } else if (id==852) { %>
       <html:img page="/pic/final_2d_test1_db852.png" alt="Powered by Struts"/>
    <% } else if (id==853) { %>
       <html:img page="/pic/final_2d_test2_db853.png" alt="Powered by Struts"/>
    <% } else if (id==860) { %>
       <html:img page="/pic/final_self_test1_db860.png" alt="Powered by Struts"/>
    <% } else if (id==861) { %>
       <html:img page="/pic/final_self_test2_db861.png" alt="Powered by Struts"/>
    <% } else if (id==862) { %>
       <html:img page="/pic/final_self_test3_db862.png" alt="Powered by Struts"/>
    <% } else if (id==863) { %>
       <html:img page="/pic/final_self_test4_db863.png" alt="Powered by Struts"/>
    <% } else if (id==864) { %>
       <html:img page="/pic/final_self_test5_db864.png" alt="Powered by Struts"/>
    <% } else if (id==865) { %>
       <html:img page="/pic/final_self_test6_db865.png" alt="Powered by Struts"/>
    <% } else if (id==866) { %>
       <html:img page="/pic/final_self_test7_db866.png" alt="Powered by Struts"/>
    <% } else if (id==889) { %>
       <html:img page="/pic/final_7_test2_b894.png" alt="Powered by Struts"/>
    <% } else if (id==890) { %>
       <html:img page="/pic/final_7_test3_b895.png" alt="Powered by Struts"/>
    <% } else if (id==891) { %>
       <html:img page="/pic/final_7_test4_b898.png" alt="Powered by Struts"/>
    <% } else if (id==892) { %>
       <html:img page="/pic/final_7_test5_b900.png" alt="Powered by Struts"/>
    <% } else if (id>=893 && id<=895) { %>
       <html:img page="/pic/final_7_test7_b909.png" alt="Powered by Struts"/>
    <% } else if (id==910) { %>
       <html:img page="/pic/final_9_test1_db910.png" alt="Powered by Struts"/>
    <% } %>

     <% remainSeconds = _pb2.getRemainSeconds();
       if (remainSeconds > 0 && _pb2.getPlanStatus().equals("not finished")){ 
    %>

    <html:form action="/SmartRandomCheck.do" >

      <%= doubleBlankHint%>
      <html:text property="inputAnswer" size="36" maxlength="100" value="<%= strInput%>"/>;<html:text property="inputAnswer2" size="36" maxlength="100" value="<%= strInput2%>"/><br>
      <html:hidden property="correctAnswer" value="<%= _correctAnswer%>"/>
      <html:hidden property="correctAnswer_1st" value="<%= _fdbb.getSolution_1st()%>"/>
      <html:hidden property="correctAnswer_2nd" value="<%= _fdbb.getSolution_2nd()%>"/>
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

     <html:form action="/SmartBlank.do" >     
      <html:hidden property="inputAnswer" value="<%= strInput%>"/><br>
      <html:hidden property="correctAnswer" value="<%= _correctAnswer%>"/>
      <html:hidden property="correctAnswer_1st" value="<%= _fdbb.getSolution_1st()%>"/>
      <html:hidden property="correctAnswer_2nd" value="<%= _fdbb.getSolution_2nd()%>"/>
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

      <html:submit property="submit" value="<%= inputHint%>"/>
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

    <CENTER><H3><a href="javascript:window.open('AppletExample/InputApplet2.html')"><%= inputHint2%></a></H3></CENTER><P>

    <!-- <APPLET CODEBASE="AppletExample" CODE="InputApplet2.class" WIDTH=360 HEIGHT=360></APPLET> -->

    <html:img page="/struts-power.gif" alt="Powered by Struts"/>
   
  </body>
</html:html>
