<%@ include file="taglibs.jsp" %>

<html:html locale="false">
  <head>       
  </head>
  <body bgcolor="white"><p> 

  <%! HttpSession session = null; 

     String test = null;
     String test2 = null; 
  %>
    
   <% session = request.getSession(true);  
      test =  (String)request.getParameter("test");
      test2 = new String(test.getBytes("iso8859-1"),"UTF-8");
   %> 
    
   <%= test2 %>
  </body>
</html:html>
