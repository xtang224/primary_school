<%@ include file="taglibs.jsp" %>

<html:html locale="true">
  <head>   
    <html:base/>
  </head>
  <body bgcolor="white"><p>    


   <%! HttpSession session = null; 
        ArrayList infoList = null;
        //Object[] infoListArr = null;
        int i = 0;
        String[] infos;
        String info = null;
        int index = 0;
        String userName = null;        
        String name = null;
        String totalScore = null;
        String times = null;
    %>
    <% session = request.getSession(true);     
       infoList = (ArrayList)request.getAttribute("infoList");       
       //infoListArr = request.getAttribute("infoListArr");         
       //result = (String)request.getAttribute("infoStr");
       System.out.println("Inside ShowScore2.jsp, infoList.size() = " + infoList.size());
    %>
       <CENTER><H1><bean:message key="hello.jsp.prompt.studentByOrder"/></H1><BR>  
       
    <CENTER><TABLE BGCOLOR="#D8D8D8" BORDER=2>    
    <tr ALIGN=center><FONT SIZE="24">
       <th><H1><bean:message key="hello.jsp.prompt.studentNo2"/></H1></th>
       <th><H1><bean:message key="hello.jsp.prompt.trueName2"/></H1></th>      
       <th><H1><bean:message key="hello.jsp.prompt.totalScore2"/></H1></th>
       <th><H1><bean:message key="hello.jsp.prompt.answerTimes"/></H1></th>       
    </FONT></tr>
  
   <% for (i = 0; i < infoList.size(); i++){ 
         info = (String)infoList.get(i);
         System.out.println("inside ShowScore2.jsp, info = " + info);
         infos = info.split(":");
         userName = infos[0];
         name = infos[1];
         totalScore = infos[2];
         times = infos[3];        
   %>
    <h1><TR ALIGN=center><FONT COLOR="#FF5555" SIZE="24">
        <TD><H1><%= userName %></H1></TD>
        <TD><H1><%= name %></H1></TD>
        <TD><H1><%= totalScore %></H1></TD>
        <TD><H1><%= times %></H1></TD>        
    </FONT></TR></h1>    

   <% } %>   
    </TABLE>   
    </CENTER>
    
    <html:img page="/struts-power.gif" alt="Powered by Struts"/>
   
  </body>
</html:html>
