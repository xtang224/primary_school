<%@ include file="taglibs.jsp" %>

<html:html locale="true">
  <head>
    <title><bean:message key="hello.jsp.title"/></title>
    <html:base/>
  </head>
  <body bgcolor="white"><p>

    <h2><bean:message key="hello.jsp.page.heading"/></h2>
    <html:errors/><p> 

   <%! HttpSession session = null; 
       java.sql.Connection myConnection = null;
       PreparedStatement stmt = null;
       ResultSet rs = null;

       int size = 0;
       String[] userName;
       String[] password;
       String[] trueName;
       int count = 0;

       String userNameHint = null;
       String passwordHint = null;
       String trueNameHint = null;
       String loginHint = null;
   %>  

   <%  try{
          Class.forName("org.hsqldb.jdbcDriver");
          myConnection = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/xdb", "SA", ""); 

          stmt = myConnection.prepareStatement("select studentNo, password, name from students");
          stmt.clearParameters();
          rs = stmt.executeQuery(); 
          count = 0;            
          while(rs.next()){
             count ++;
          }
          size = count;
          userName = new String[size];
          password = new String[size];
          trueName = new String[size];

          stmt = myConnection.prepareStatement("select studentNo, password, name from students");
          stmt.clearParameters();
          rs = stmt.executeQuery(); 
          count = 0;            
          while(rs.next()){
             userName[count] = rs.getString(1);
             password[count] = rs.getString(2);
             trueName[count] = rs.getString(3);
             count++;
          }

          stmt = myConnection.prepareStatement("select term2 from hintMatch where term1=?");
          stmt.clearParameters();
          stmt.setString(1, "userName");
          rs = stmt.executeQuery();              
          while(rs.next()){
             userNameHint = rs.getString(1);
          }

          stmt = myConnection.prepareStatement("select term2 from hintMatch where term1=?");
          stmt.clearParameters();
          stmt.setString(1, "password");
          rs = stmt.executeQuery();              
          while(rs.next()){
             passwordHint = rs.getString(1);
          }

          stmt = myConnection.prepareStatement("select term2 from hintMatch where term1=?");
          stmt.clearParameters();
          stmt.setString(1, "trueName");
          rs = stmt.executeQuery();              
          while(rs.next()){
             trueNameHint = rs.getString(1);
          }

          stmt = myConnection.prepareStatement("select term2 from hintMatch where term1=?");
          stmt.clearParameters();
          stmt.setString(1, "login");
          rs = stmt.executeQuery();              
          while(rs.next()){
             loginHint = rs.getString(1);
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

    <CENTER><H3><%= loginHint%></H3></CENTER>

    <CENTER><TABLE BGCOLOR="#D8D8D8" BORDER=2>
    <tr ALIGN=center>
       <th><%= userNameHint%></th>
       <th><%= passwordHint%></th>
       <th><%= trueNameHint%></th>
    </tr>    
    <% for (count=0; count<size; count++) { %>
       <TR ALIGN=center><FONT COLOR="#FF5555">
          <TD><%= userName[count] %></TD>
          <TD><%= password[count] %></TD>
          <TD><%= trueName[count] %></TD><br>
       </FONT></TR>
    <% } %>
    
    </TABLE>
    </CENTER>  

    <html:form action="/Login.do" >

      <bean:message key="hello.jsp.prompt.username"/><html:text property="userName" size="16" maxlength="16"/>
      <br>
      <bean:message key="hello.jsp.prompt.password"/><html:password property="passWord" size="16" maxlength="16"/>
      <br>

      <html:submit property="submit" value="Submit"/>
      <html:reset/>

    </html:form><br>

    <html:img page="/struts-power.gif" alt="Powered by Struts"/>

  </body>
</html:html>
