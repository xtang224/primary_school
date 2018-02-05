<%@ include file="taglibs.jsp" %>

<html:html locale="true">
  <head>
    <title><bean:message key="hello.jsp.page.solution"/></title>
    <html:base/>
  </head>
  <body bgcolor="white"><p>   

   <html:errors/><p> 

   <h2>Click Below To Check Final Score</h2>
    
       <center><h2><bean:message key="hello.jsp.prompt.finishedClass1"/></h2></center>
        <center>
      <html:form action="/Score2.do" >     
      <html:hidden property="answeredHashSet" value=""/>
      <html:hidden property="answeredHashSet_tf" value=""/>
      <html:hidden property="answered_M_HashSet" value=""/>
      <html:hidden property="answeredHashSet_fb" value=""/>
      <html:hidden property="answeredHashSet_fdb" value=""/>
      <html:hidden property="answeredHashSet_tm" value=""/>
      <html:hidden property="answeredHashSet_fqb" value=""/>
      <html:hidden property="userName" value=""/>
      <html:hidden property="passWord" value=""/>
      <html:hidden property="trueName" value=""/>
      <html:hidden property="answeredProblems" value="1"/><br>
      <html:hidden property="correctAnswers" value="1"/>
      <html:hidden property="totalScore" value="1"/>
      <html:hidden property="correctAnswers_low" value="1"/>
      <html:hidden property="answeredProblems_low" value="1"/>
 <html:hidden property="correctAnswers_middle" value="1"/>
 <html:hidden property="answeredProblems_middle" value="1"/>
     <html:hidden property="correctAnswers_high" value="1"/>
     <html:hidden property="answeredProblems_high" value="1"/>
      <html:hidden property="times" value=""/>
      <html:hidden property="planStatus" value=""/>
      <html:hidden property="classId" value="class1"/>
  
      <html:submit property="submit" value="Submit"/>
      <html:reset/>

    </html:form><br>
    </center>

  <HR>
   <center><h2><bean:message key="hello.jsp.prompt.finishedClass2"/></h2></center>
        <center>
      <html:form action="/Score2.do" >     
      <html:hidden property="answeredHashSet" value=""/>
      <html:hidden property="answeredHashSet_tf" value=""/>
      <html:hidden property="answered_M_HashSet" value=""/>
      <html:hidden property="answeredHashSet_fb" value=""/>
      <html:hidden property="answeredHashSet_fdb" value=""/>
      <html:hidden property="answeredHashSet_tm" value=""/>
      <html:hidden property="answeredHashSet_fqb" value=""/>
      <html:hidden property="userName" value=""/>
      <html:hidden property="passWord" value=""/>
      <html:hidden property="trueName" value=""/>
      <html:hidden property="answeredProblems" value="1"/><br>
      <html:hidden property="correctAnswers" value="1"/>
      <html:hidden property="totalScore" value="1"/>
      <html:hidden property="correctAnswers_low" value="1"/>
      <html:hidden property="answeredProblems_low" value="1"/>
 <html:hidden property="correctAnswers_middle" value="1"/>
 <html:hidden property="answeredProblems_middle" value="1"/>
     <html:hidden property="correctAnswers_high" value="1"/>
     <html:hidden property="answeredProblems_high" value="1"/>
      <html:hidden property="times" value=""/>
      <html:hidden property="planStatus" value=""/>
      <html:hidden property="classId" value="class2"/>
  
      <html:submit property="submit" value="Submit"/>
      <html:reset/>

    </html:form><br>
    </center>

  <HR>
  <center><h2><bean:message key="hello.jsp.prompt.finishedClass3"/></h2></center>
        <center>
      <html:form action="/Score2.do" >     
      <html:hidden property="answeredHashSet" value=""/>
      <html:hidden property="answeredHashSet_tf" value=""/>
      <html:hidden property="answered_M_HashSet" value=""/>
      <html:hidden property="answeredHashSet_fb" value=""/>
      <html:hidden property="answeredHashSet_fdb" value=""/>
      <html:hidden property="answeredHashSet_tm" value=""/>
      <html:hidden property="answeredHashSet_fqb" value=""/>
      <html:hidden property="userName" value=""/>
      <html:hidden property="passWord" value=""/>
      <html:hidden property="trueName" value=""/>
      <html:hidden property="answeredProblems" value="1"/><br>
      <html:hidden property="correctAnswers" value="1"/>
      <html:hidden property="totalScore" value="1"/>
      <html:hidden property="correctAnswers_low" value="1"/>
      <html:hidden property="answeredProblems_low" value="1"/>
 <html:hidden property="correctAnswers_middle" value="1"/>
 <html:hidden property="answeredProblems_middle" value="1"/>
     <html:hidden property="correctAnswers_high" value="1"/>
     <html:hidden property="answeredProblems_high" value="1"/>
      <html:hidden property="times" value=""/>
      <html:hidden property="planStatus" value=""/>
      <html:hidden property="classId" value="class3"/>
  
      <html:submit property="submit" value="Submit"/>
      <html:reset/>

    </html:form><br>
    </center>
  

    <html:img page="/struts-power.gif" alt="Powered by Struts"/>

  </body>
</html:html>
