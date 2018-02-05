<%@ include file="taglibs.jsp" %>


<html:html locale="true">
  <head>
    <title><bean:message key="hello.jsp.title"/></title>
    <html:base/>
  </head>
  <body bgcolor="white"><p>

    <h2><bean:message key="hello.jsp.page.heading"/></h2><p>

   <%! HttpSession session = null; 
        String classId = "";
    %>

    <% session = request.getSession(true);
       classId = (String)request.getParameter("classId");
    %>

   <html:errors/><p>   

   <h2><center><bean:message key="hello.jsp.prompt.welcomeMainPage"/></center></h2>  

    <html:form action="/Login.do" >

      <bean:message key="hello.jsp.prompt.username"/><html:text property="userName" size="16" maxlength="16"/>
      <br>
      <bean:message key="hello.jsp.prompt.password"/><html:password property="passWord" size="16" maxlength="16"/>
      <br>
      <html:hidden property="classId" value="<%= classId%>"/>
      <html:submit property="submit" value="Submit"/>
      <html:reset/>

    </html:form><br>

    <html:img page="/struts-power.gif" alt="Powered by Struts"/>

  </body>
</html:html>
