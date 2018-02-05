<%@ include file="taglibs.jsp" %>

<html:html locale="false">
  <head>       
  </head>
  <body bgcolor="white"><p> 

  <%! HttpSession session = null; 

     String test = null;
  %>
    
   <% session = request.getSession(true);  
      response.setCharacterEncoding("UTF-8");
   %>   
    
   <FORM ACTION="TestInputCheck.jsp" METHOD="GET">
         <TABLE BORDER=0>    
        <TR>
           <TD><INPUT TYPE="text" name="test" size="16" maxlength="100" value=""></TD>
        </TR>
        <TR>      
           <TD align=center><INPUT TYPE="Submit" VALUE="Submit to TestInputCheck.jsp">&nbsp;&nbsp;
                       <INPUT TYPE="Reset" VALUE="Reset"></TD>
       </TR>
         </TABLE>
      </FORM>
  </body>
</html:html>
