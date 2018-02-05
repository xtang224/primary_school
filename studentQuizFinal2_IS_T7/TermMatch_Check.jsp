<%@ include file="taglibs.jsp" %>

<html:html locale="true">
  <head>   
    <html:base/>
  </head>
  <body bgcolor="white"><p>    

   <html:errors/><p> 

   
    <%! HttpSession session = null; 
        hello.TermMatchBean _tmb = null;
        hello.PersonBean _pb2 = null;
        String _hs = null;
        String _hs_tf = null;
        String _mhs = null;
        String _hs_fb = null;
        String _hs_fdb = null;
        String _hs_tm = null;
        String _hs_my = null;
        String _hs_d = null;
        String _hs_c2 = null;
        String answeredProblems = null;
        String correctAnswers = null;
        String totalScore = null;
        String correctAnswers_low = null;
        String answeredProblems_low = null;
        String correctAnswers_middle = null;
        String answeredProblems_middle = null;
        String correctAnswers_high = null;
        String answeredProblems_high = null;
        String userName  = null;
        String passWord = null;
        String trueName = null;
        String source = null;
        String lastType = null;
        String thisType = null;
        String times = null;
        String randomNumber = null;
        String id = null;
        String planStatus = null;
        String lastCorrect = null;

        String didRight = null;
        String didWrong = null;

        int intAnsweredProblems = 0;
        int intCorrectAnswers = 0;
        double dblTotalScore = 0;
        int intAnsweredProblems_middle = 0;
        int intCorrectAnswers_middle = 0;

        int remainSeconds = 300;       
        java.sql.Connection myConnection = null;
        PreparedStatement stmt = null;        
        ResultSet rs = null;

        String term1 = null;
        String term2 = null;
        int intId = 0;
 
        String strContinue = null;
        String strExit = null;
    %>
    <% session = request.getSession(true);     
       
       //_fdbb = (hello.FillDoubleBlankBean)request.getAttribute(hello.Constants.FILLDOUBLEBLANK_KEY);
       //_pb2 = (hello.PersonBean)request.getAttribute(hello.Constants.PERSON_KEY);
       _hs = (String)request.getParameter("answeredHashSet");
       _hs_tf = (String)request.getParameter("answeredHashSet_tf");
       _mhs = (String)request.getParameter("answered_M_HashSet");
       _hs_fb = (String)request.getParameter("answeredHashSet_fb");
       _hs_fdb = (String)request.getParameter("answeredHashSet_fdb");
       _hs_tm = (String)request.getParameter("answeredHashSet_tm");
       _hs_my = (String)request.getParameter("answered_MY_HashSet");
       _hs_d = (String)request.getParameter("answered_D_HashSet");
       _hs_c2 = (String)request.getParameter("answered_C_HashSet");
       answeredProblems = (String)request.getParameter("answeredProblems");
       correctAnswers = (String)request.getParameter("correctAnswers");
       totalScore = (String)request.getParameter("totalScore");
       correctAnswers_low = (String)request.getParameter("correctAnswers_low");
       answeredProblems_low = (String)request.getParameter("answeredProblems_low");
       correctAnswers_middle = (String)request.getParameter("correctAnswers_middle");
       answeredProblems_middle = (String)request.getParameter("answeredProblems_middle");
       correctAnswers_high = (String)request.getParameter("correctAnswers_high");
       answeredProblems_high = (String)request.getParameter("answeredProblems_high");
       userName = (String)request.getParameter("userName");
       passWord = (String)request.getParameter("passWord");
       trueName = (String)request.getParameter("trueName");
       source = (String)request.getParameter("source");
       lastType = (String)request.getParameter("lastType");
       thisType = (String)request.getParameter("thisType");
       times = (String)request.getParameter("times");
       randomNumber = (String)request.getParameter("randomNumber");
       id = (String)request.getParameter("id");
       planStatus = (String)request.getParameter("planStatus");

       System.out.println("inside TermMatch_Check.jsp, _hs = " + _hs);
       System.out.println("inside TermMatch_Check.jsp, _hs_tf = " + _hs_tf);
       System.out.println("inside TermMatch_Check.jsp, _mhs = " + _mhs);
       System.out.println("inside TermMatch_Check.jsp, _hs_fb = " + _hs_fb);
       System.out.println("inside TermMatch_Check.jsp, _hs_fdb = " + _hs_fdb);
       System.out.println("inside TermMatch_Check.jsp, _hs_tm = " + _hs_tm);
       System.out.println("inside TermMatch_Check.jsp, _hs_my = " + _hs_my);
       System.out.println("inside TermMatch_Check.jsp, _hs_d = " + _hs_d);
       System.out.println("inside TermMatch_Check.jsp, _hs_c2 = " + _hs_c2);

       System.out.println("inside TermMatch_Check.jsp, userName = " + userName);

       intAnsweredProblems = Integer.parseInt(answeredProblems);
       intCorrectAnswers = Integer.parseInt(correctAnswers);
       dblTotalScore = Double.parseDouble(totalScore);
       intAnsweredProblems_middle = Integer.parseInt(answeredProblems_middle);
       intCorrectAnswers_middle = Integer.parseInt(correctAnswers_middle);       

       lastType = thisType;
       thisType = "M";
       intId = Integer.parseInt(id);

       try{
          Class.forName("org.hsqldb.jdbcDriver");
          myConnection = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/xdb", "SA", ""); 

          stmt = myConnection.prepareStatement("select remainSeconds from student_time where studentNo=?");
          stmt.clearParameters();
          stmt.setString(1, userName);
          rs = stmt.executeQuery();              
          while(rs.next()){
             remainSeconds = rs.getInt(1);
          }

          stmt = myConnection.prepareStatement("select answerRightOrWrong from termMatchAnswer where studentNo=?");
          stmt.clearParameters();
          stmt.setString(1, userName);
          rs = stmt.executeQuery();              
          while(rs.next()){
             lastCorrect = rs.getString(1);
          }

          stmt = myConnection.prepareStatement("update usedTermMatch_test set studentAnswer=? where studentNo=? and id=?");
          stmt.clearParameters();
          stmt.setString(1, lastCorrect);
          stmt.setString(2, userName);
          stmt.setInt(3, intId);
          stmt.executeUpdate();

          stmt = myConnection.prepareStatement("select term1,term2 from termMatch_test where id=?");
          stmt.clearParameters();
          stmt.setInt(1, intId);
          rs = stmt.executeQuery();              
          while(rs.next()){
             term1 = rs.getString(1);
             term2 = rs.getString(2);
          }

          //below we want to get the correct form of trueName again
          stmt = myConnection.prepareStatement("select name from students where studentNo=?");
          stmt.clearParameters();
          stmt.setString(1, userName);
          rs = stmt.executeQuery();              
          while(rs.next()){
             trueName = rs.getString(1);
          }

          //below we want to get the correct hint, the wrong hint and the continue hint
          stmt = myConnection.prepareStatement("select term2 from hintMatch where term1=?");
          stmt.clearParameters();
          stmt.setString(1, "right");
          rs = stmt.executeQuery();              
          while(rs.next()){
             didRight = rs.getString(1);
          }
          stmt = myConnection.prepareStatement("select term2 from hintMatch where term1=?");
          stmt.clearParameters();
          stmt.setString(1, "wrong");
          rs = stmt.executeQuery();              
          while(rs.next()){
             didWrong = rs.getString(1);
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

       intAnsweredProblems ++;
       intAnsweredProblems_middle ++;
       if (lastCorrect.equals("true")){
          //dblTotalScore += 1.5;
          dblTotalScore += StringSetTransfer.hm_tm.get(new Integer(id)).intValue();
          intCorrectAnswers += 1;
          intCorrectAnswers_middle += 1;
       }
       answeredProblems = "" + intAnsweredProblems;
       answeredProblems_middle = "" + intAnsweredProblems_middle;
       totalScore = "" + dblTotalScore;
       correctAnswers = "" + intCorrectAnswers;
       correctAnswers_middle = "" + intCorrectAnswers_middle;
    %>
<h2>Hello, <%= trueName%><P>
The correct answer is:<br>
Id : <%= id%><p>
Type: <%= lastType%><p>
<%= term1 %><p>    
<%= term2 %><p>

<center> 
    <% if (lastCorrect.equals("true")) { %>
        <%= didRight%>
    <% } else if (lastCorrect.equals("false")){ %>
        <%= didWrong%>
    <% } %>
</center>
</h2><p>
    <% 
       if (remainSeconds > 0 && planStatus.equals("not finished")){ 
    %>
     
         <html:form action="/SmartRandom.do?first=false" >

      <%= strContinue%><br>
      <html:hidden property="answeredProblems" value="<%= answeredProblems %>"/><br>
      <html:hidden property="correctAnswers" value="<%= correctAnswers %>"/>
      <html:hidden property="totalScore" value="<%= totalScore %>"/>
      <html:hidden property="correctAnswers_low" value="<%= correctAnswers_low %>"/>
      <html:hidden property="answeredProblems_low" value="<%= answeredProblems_low %>"/>
 <html:hidden property="correctAnswers_middle" value="<%= correctAnswers_middle %>"/>
 <html:hidden property="answeredProblems_middle" value="<%= answeredProblems_middle %>"/>
     <html:hidden property="correctAnswers_high" value="<%= correctAnswers_high %>"/>
     <html:hidden property="answeredProblems_high" value="<%= answeredProblems_high %>"/>
      <html:hidden property="answeredHashSet" value="<%= _hs%>"/>
      <html:hidden property="answeredHashSet_tf" value="<%= _hs_tf%>"/>
      <html:hidden property="answered_M_HashSet" value="<%= _mhs%>"/>
      <html:hidden property="answeredHashSet_fb" value="<%= _hs_fb%>"/>
      <html:hidden property="answeredHashSet_fdb" value="<%= _hs_fdb%>"/>
      <html:hidden property="answeredHashSet_tm" value="<%= _hs_tm%>"/>
      <html:hidden property="answered_MY_HashSet" value="<%= _hs_my%>"/>
      <html:hidden property="answered_D_HashSet" value="<%= _hs_d%>"/>
      <html:hidden property="answered_C_HashSet" value="<%= _hs_c2%>"/>
      <html:hidden property="userName" value="<%= userName %>"/>
      <html:hidden property="passWord" value="<%= passWord %>"/>
      <html:hidden property="trueName" value="<%= trueName %>"/>
      <html:hidden property="source" value="<%= source %>"/>
      <html:hidden property="lastCorrect" value="<%= lastCorrect%>"/>
      <html:hidden property="lastType" value="<%= lastType %>"/>
      <html:hidden property="thisType" value="<%= thisType %>"/>
      <html:hidden property="times" value="<%= times %>"/>
      <html:hidden property="planStatus" value="<%= planStatus %>"/>

      <html:submit property="submit" value="Submit"/>
      <html:reset/>

    </html:form><br><br>      

<% } else {   %>
    
       <center><h2><%= strExit%></h2></center>
        <center>
      <html:form action="/SmartQuit.do" >     
      <html:hidden property="answeredHashSet" value="<%= _hs%>"/>
      <html:hidden property="answeredHashSet_tf" value="<%= _hs_tf%>"/>
      <html:hidden property="answered_M_HashSet" value="<%= _mhs%>"/>
      <html:hidden property="answeredHashSet_fb" value="<%= _hs_fb%>"/>
      <html:hidden property="answeredHashSet_fdb" value="<%= _hs_fdb%>"/>
      <html:hidden property="answeredHashSet_tm" value="<%= _hs_tm%>"/>
      <html:hidden property="answered_MY_HashSet" value="<%= _hs_my%>"/>
      <html:hidden property="answered_D_HashSet" value="<%= _hs_d%>"/>
      <html:hidden property="answered_C_HashSet" value="<%= _hs_c2%>"/>
      <html:hidden property="userName" value="<%= userName %>"/>
      <html:hidden property="passWord" value="<%= passWord %>"/>
      <html:hidden property="trueName" value="<%= trueName %>"/>
      <html:hidden property="source" value="<%= source %>"/>
      <html:hidden property="answeredProblems" value="<%= answeredProblems %>"/><br>
      <html:hidden property="correctAnswers" value="<%= correctAnswers %>"/>
      <html:hidden property="totalScore" value="<%= totalScore %>"/>
      <html:hidden property="correctAnswers_low" value="<%= correctAnswers_low %>"/>
      <html:hidden property="answeredProblems_low" value="<%= answeredProblems_low %>"/>
 <html:hidden property="correctAnswers_middle" value="<%= correctAnswers_middle %>"/>
 <html:hidden property="answeredProblems_middle" value="<%= answeredProblems_middle %>"/>
     <html:hidden property="correctAnswers_high" value="<%= correctAnswers_high %>"/>
     <html:hidden property="answeredProblems_high" value="<%= answeredProblems_high %>"/>
      <html:hidden property="times" value="<%= times %>"/>
      <html:hidden property="planStatus" value="<%= planStatus %>"/>
  
      <html:submit property="submit" value="Submit"/>
      <html:reset/>

    </html:form><br>
    </center>

    <% } %>

    <html:img page="/struts-power.gif" alt="Powered by Struts"/>
   
  </body>
</html:html>
