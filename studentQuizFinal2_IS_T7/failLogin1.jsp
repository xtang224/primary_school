<%@ include file="taglibs.jsp" %>


<html:html locale="true">
  <head>
    <title><bean:message key="hello.jsp.title"/></title>
    <html:base/>
  </head>
  <body bgcolor="white"><p>

    <h2><bean:message key="hello.jsp.page.heading"/></h2><p>

   <html:errors/><p> 

    <logic:present name="personbean" scope="request">
       <h2>
         <bean:message key="hello.jsp.page.hello"/>
         <bean:write name="personbean" property="trueName" /><bean:message key="hello.jsp.page.student"/><p>
         <bean:message key="hello.jsp.page.failLogin1"/>
       </h2>
    </logic:present>    

    <html:img page="/struts-power.gif" alt="Powered by Struts"/>

  </body>
</html:html>
