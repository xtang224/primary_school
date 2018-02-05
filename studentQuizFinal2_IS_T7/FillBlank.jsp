<%@ include file="taglibs.jsp" %>

<html:html locale="true">
  <head>   
    <html:base/>
  </head>
  <body bgcolor="white"><p>    

   <html:errors/><p> 

    <logic:present name="fillblankbean" scope="session">
       <h2>
         <bean:message key="hello.jsp.page.problem"/>
         <bean:write name="fillblankbean" property="id" /><p>
         <bean:write name="fillblankbean" property="type" /><p>
         <bean:write name="fillblankbean" property="statement_1st" />  
         <bean:write name="fillblankbean" property="statement_2nd" />         
       </h2>
    </logic:present>
    
    <%! HttpSession session = null; 
        hello.FillBlankBean _fbb = null;
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
        int currentId = 0;        

        java.sql.Connection myConnection = null;
        PreparedStatement stmt = null;        
        ResultSet rs = null;
        String blankHint = null;
        String strExit = null;
        String calculatorHint = null;

        String strInput = "";
        String inputHint = "";
        String inputHint2 = "";
        String inputHint3 = "";
        String inputHint5 = "";
        String inputHint6 = "";
        String inputHint7 = "";
        String tmpStr = "";
        String tmpStr2 = "not done";

        int[] angleIds;
        int[] angleIds_One;
        
        int[] lineIds;
        int[] tableIds;
        int[] chartIds;
        int[] gameIds;
    %>
    <% session = request.getSession(true);     
       _fbb = (hello.FillBlankBean)request.getAttribute(hello.Constants.FILLBLANK_KEY);
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

       currentId = _pb2.getCurrentProblemId();  
 
       angleIds = new int[]{82,83,84,87,88,89,90,91,92,93,94,246,247,248,266,267,268,285,286,291,292,293,294,308,309,310,321,322,323,342,343,344,393,394,662,663,664,761,762,763,776,777,778,779,780,941,942,943,944};
       angleIds_One = new int[]{82,83,84,87,88,89,90,91,92,93,94,246,247,248,266,267,268,291,292,293,294,321,322,323,342,343,344,393,394,662,663,664,761,762,763,776,777,778,779,780,941,942,943,944};

       lineIds = new int[]{149,150,245,250,312,313,392,407,408,409,410,411,412,413,414,415,416,417,418,424,425,426,427,428,431,432,433,435,436,437,438,439,440,441,447,453,454,455,456,457,458,459,460,475,601,602,603,665,666,667,676,677,683,764,768,781,782,783,784,813,814,825,826,827,828,829,830,831,832,833,834,844,845,857,858,859,860,938,939,940};

       tableIds = new int[]{567,568,594,632,678,715,716,746,754,760,843,895,898,906,917,921,922,923};

       chartIds = new int[]{633,639,645,646,649,651,679,680,684,688,692,696,701,702,711,717,722,728,733,815,846,861,862,900,903,907,909,910,945};

       gameIds = new int[]{747,755,756};

       try{
          Class.forName("org.hsqldb.jdbcDriver");
          myConnection = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/xdb", "SA", ""); 
          stmt = myConnection.prepareStatement("select term2 from hintMatch where term1=?");
          stmt.clearParameters();
          stmt.setString(1, "blank");
          rs = stmt.executeQuery();              
          while(rs.next()){
             blankHint = rs.getString(1);
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
          stmt.setString(1, "calculator");
          rs = stmt.executeQuery();              
          while(rs.next()){
             calculatorHint = rs.getString(1);
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

          stmt = myConnection.prepareStatement("select term2 from hintMatch where term1=?");
          stmt.clearParameters();
          stmt.setString(1, "inputHint3");
          rs = stmt.executeQuery();              
          while(rs.next()){
             inputHint3 = rs.getString(1);
          }  

          stmt = myConnection.prepareStatement("select term2 from hintMatch where term1=?");
          stmt.clearParameters();
          stmt.setString(1, "inputHint5");
          rs = stmt.executeQuery();              
          while(rs.next()){
             inputHint5 = rs.getString(1);
          }

          stmt = myConnection.prepareStatement("select term2 from hintMatch where term1=?");
          stmt.clearParameters();
          stmt.setString(1, "inputHint6");
          rs = stmt.executeQuery();              
          while(rs.next()){
             inputHint6 = rs.getString(1);
          }

          stmt = myConnection.prepareStatement("select term2 from hintMatch where term1=?");
          stmt.clearParameters();
          stmt.setString(1, "inputHint7");
          rs = stmt.executeQuery();              
          while(rs.next()){
             inputHint7 = rs.getString(1);
          }
          
          if (StringSetTransfer.inGroup(angleIds, currentId)){ 
             stmt = myConnection.prepareStatement("select solution from fillBlank2_test where id=?");
             stmt.clearParameters();
             stmt.setInt(1, currentId);
             rs = stmt.executeQuery();              
             while(rs.next()){
                tmpStr = rs.getString(1);
             }

             stmt = myConnection.prepareStatement("update hintMatch set term2=? where term1=?");
             stmt.clearParameters();
             stmt.setString(1, tmpStr);
             stmt.setString(2, "angle");
             stmt.executeUpdate();    

             stmt = myConnection.prepareStatement("select term2 from hintMatch where term1=?");
             stmt.clearParameters();
             stmt.setString(1, "angleHint");
             rs = stmt.executeQuery();              
             while(rs.next()){
                tmpStr2 = rs.getString(1);
             }   
             System.out.println("inside FillBlank.jsp, tmpStr = " + tmpStr + " and tmpStr2=" + tmpStr2);   
          }

          //if (currentId==149 || currentId==150 || currentId==245 || currentId==250 || currentId==312 || currentId==313){ 
          if (StringSetTransfer.inGroup(lineIds, currentId)){
             stmt = myConnection.prepareStatement("select solution from fillBlank2_test where id=?");
             stmt.clearParameters();
             stmt.setInt(1, currentId);
             rs = stmt.executeQuery();              
             while(rs.next()){
                tmpStr = rs.getString(1);
             }

             stmt = myConnection.prepareStatement("update hintMatch set term2=? where term1=?");
             stmt.clearParameters();
             stmt.setString(1, tmpStr);
             stmt.setString(2, "line");
             stmt.executeUpdate();    

             stmt = myConnection.prepareStatement("select term2 from hintMatch where term1=?");
             stmt.clearParameters();
             stmt.setString(1, "lineHint");
             rs = stmt.executeQuery();              
             while(rs.next()){
                tmpStr2 = rs.getString(1);
             }   
             System.out.println("inside FillBlank.jsp, tmpStr = " + tmpStr + " and tmpStr2=" + tmpStr2);   
          }

          if (StringSetTransfer.inGroup(tableIds, currentId)){
             stmt = myConnection.prepareStatement("select solution from fillBlank2_test where id=?");
             stmt.clearParameters();
             stmt.setInt(1, currentId);
             rs = stmt.executeQuery();              
             while(rs.next()){
                tmpStr = rs.getString(1);
             }

             stmt = myConnection.prepareStatement("update hintMatch set term2=? where term1=?");
             stmt.clearParameters();
             stmt.setString(1, tmpStr);
             stmt.setString(2, "table");
             stmt.executeUpdate(); 

             stmt = myConnection.prepareStatement("update hintMatch set term2=? where term1=?");
             stmt.clearParameters();
             stmt.setString(1, _fbb.getStatement_1st()+_fbb.getStatement_2nd());
             stmt.setString(2, "tableHint2");
             stmt.executeUpdate();   

             stmt = myConnection.prepareStatement("select term2 from hintMatch where term1=?");
             stmt.clearParameters();
             stmt.setString(1, "tableHint");
             rs = stmt.executeQuery();              
             while(rs.next()){
                tmpStr2 = rs.getString(1);
             }   
             System.out.println("inside FillBlank.jsp, tmpStr = " + tmpStr + " and tmpStr2=" + tmpStr2);   
          }

          if (StringSetTransfer.inGroup(chartIds, currentId)){
             stmt = myConnection.prepareStatement("select solution from fillBlank2_test where id=?");
             stmt.clearParameters();
             stmt.setInt(1, currentId);
             rs = stmt.executeQuery();              
             while(rs.next()){
                tmpStr = rs.getString(1);
             }

             stmt = myConnection.prepareStatement("update hintMatch set term2=? where term1=?");
             stmt.clearParameters();
             stmt.setString(1, tmpStr);
             stmt.setString(2, "chart");
             stmt.executeUpdate();    

             stmt = myConnection.prepareStatement("select term2 from hintMatch where term1=?");
             stmt.clearParameters();
             stmt.setString(1, "chartHint");
             rs = stmt.executeQuery();              
             while(rs.next()){
                tmpStr2 = rs.getString(1);
             } 
             System.out.println("inside FillBlank.jsp, tmpStr = " + tmpStr + " and tmpStr2=" + tmpStr2);   
             System.out.println("inside FillBlank.jsp, tmpStr = " + tmpStr + " and tmpStr2=" + tmpStr2);   
          }

          if (StringSetTransfer.inGroup(gameIds, currentId)){
             stmt = myConnection.prepareStatement("select solution from fillBlank2_test where id=?");
             stmt.clearParameters();
             stmt.setInt(1, currentId);
             rs = stmt.executeQuery();              
             while(rs.next()){
                tmpStr = rs.getString(1);
             }

             stmt = myConnection.prepareStatement("update hintMatch set term2=? where term1=?");
             stmt.clearParameters();
             stmt.setString(1, tmpStr);
             stmt.setString(2, "game");
             stmt.executeUpdate(); 

             stmt = myConnection.prepareStatement("update hintMatch set term2=? where term1=?");
             stmt.clearParameters();
             stmt.setString(1, _fbb.getStatement_1st()+_fbb.getStatement_2nd());
             stmt.setString(2, "gameHint2");
             stmt.executeUpdate();   

             stmt = myConnection.prepareStatement("select term2 from hintMatch where term1=?");
             stmt.clearParameters();
             stmt.setString(1, "gameHint");
             rs = stmt.executeQuery();              
             while(rs.next()){
                tmpStr2 = rs.getString(1);
             }   
             System.out.println("inside FillBlank.jsp, tmpStr = " + tmpStr + " and tmpStr2=" + tmpStr2);   
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
 

    <% if (currentId==69){ %>
        <html:img page="/pic/ch3_test2_b69.PNG" alt="Powered by Struts"/>
    <% } else if (currentId==70){ %>
        <html:img page="/pic/ch3_test3_b70.PNG" alt="Powered by Struts"/>
    <% } else if (currentId==71){ %>
        <html:img page="/pic/ch3_test4_b71.PNG" alt="Powered by Struts"/>
    <% } else if (currentId==72){ %>
        <html:img page="/pic/ch3_test5_b72.PNG" alt="Powered by Struts"/>
    <% } else if (currentId==73){ %>
        <html:img page="/pic/ch3_test6_b73.PNG" alt="Powered by Struts"/>
    <% } else if (currentId==74){ %>
        <html:img page="/pic/ch3_test7_b74.PNG" alt="Powered by Struts"/>
    <% } else if (currentId==75){ %>
        <html:img page="/pic/ch3_test10_b75.PNG" alt="Powered by Struts"/>
    <% } else if (currentId==76){ %>
        <html:img page="/pic/ch3_test11_b76.PNG" alt="Powered by Struts"/>
    <% } else if (currentId==77){ %>
        <html:img page="/pic/ch3_test12_b77.PNG" alt="Powered by Struts"/>
    <% } else if (currentId==78){ %>
        <html:img page="/pic/ch3_test13_b78.PNG" alt="Powered by Struts"/>
    <% } else if (currentId==79){ %>
        <html:img page="/pic/ch3_test14_b79.PNG" alt="Powered by Struts"/>
    <% } else if (currentId==80){ %>
        <html:img page="/pic/ch3_test15_b80.PNG" alt="Powered by Struts"/>
    <% } else if (currentId==81){ %>
        <html:img page="/pic/ch3_test16_b81.PNG" alt="Powered by Struts"/>
    <% } else if (currentId==85){ %>
        <html:img page="/pic/ch3_test18_db55.PNG" alt="Powered by Struts"/>
    <% } else if (currentId==95){ %>
        <html:img page="/pic/ch3_test_extra_1.PNG" alt="Powered by Struts"/>
    <% } else if (currentId==96){ %>
        <html:img page="/pic/ch3_test_extra_2.PNG" alt="Powered by Struts"/>
    <% } else if (currentId==97){ %>
        <html:img page="/pic/ch3_test_extra_3.PNG" alt="Powered by Struts"/>
    <% }else if (currentId==101){ %>
        <html:img page="/pic/ch23_test_extra_1.PNG" alt="Powered by Struts"/>
    <% } else if (currentId==102){ %>
        <html:img page="/pic/ch23_test_extra_4.PNG" alt="Powered by Struts"/>
    <% } else if (currentId==103){ %>
        <html:img page="/pic/ch23_test_extra_5.PNG" alt="Powered by Struts"/>
    <% } else if (currentId==104){ %>
        <html:img page="/pic/ch23_test_extra_6.PNG" alt="Powered by Struts"/>
    <% } else if (currentId==146){ %>
        <html:img page="/pic/ch34_test1_b146_p1.PNG" alt="Powered by Struts"/><html:img page="/pic/ch34_test1_b146_p2.PNG" alt="Powered by Struts"/>
    <% } else if (currentId==147){ %>
        <html:img page="/pic/ch34_test1_b146_p1.PNG" alt="Powered by Struts"/><html:img page="/pic/ch34_test2_b147_p2.PNG" alt="Powered by Struts"/>
    <% } else if (currentId==148){ %>
        <html:img page="/pic/ch34_test1_b146_p1.PNG" alt="Powered by Struts"/><html:img page="/pic/ch34_test3_b148_p2.PNG" alt="Powered by Struts"/>
    <% } else if (currentId==240) { %>
       <html:img page="/pic/mid_self_test1_b240.PNG" alt="Powered by Struts"/>
    <% } else if (currentId==241) { %>
       <html:img page="/pic/mid_self_test2_b241.PNG" alt="Powered by Struts"/>
    <% } else if (currentId==242) { %>
       <html:img page="/pic/mid_self_test3_b242.PNG" alt="Powered by Struts"/>
    <% } else if (currentId==243) { %>
       <html:img page="/pic/mid_self_test4_b243.PNG" alt="Powered by Struts"/>
    <% } else if (currentId==244) { %>
       <html:img page="/pic/mid_self_test5_b244.PNG" alt="Powered by Struts"/>
    <% } else if (currentId==251) { %>
       <html:img page="/pic/mid_self_test11_b251.PNG" alt="Powered by Struts"/>
    <% } else if (currentId==252) { %>
       <html:img page="/pic/mid_self_test12_b252.PNG" alt="Powered by Struts"/>
    <% } else if (currentId==253) { %>
       <html:img page="/pic/mid_self_test13_b253.PNG" alt="Powered by Struts"/>
    <% } else if (currentId==279) { %>
       <html:img page="/pic/mid_2_test1_b279.PNG" alt="Powered by Struts"/>
    <% } else if (currentId==282) { %>
       <html:img page="/pic/mid_2_test5_1_b282.PNG" alt="Powered by Struts"/>
    <% } else if (currentId==283) { %>
       <html:img page="/pic/mid_2_test5_2_b283.PNG" alt="Powered by Struts"/>
    <% } else if (currentId==284) { %>
       <html:img page="/pic/mid_2_test5_3_b284.PNG" alt="Powered by Struts"/>
    <% } else if (currentId==295) { %>
       <html:img page="/pic/ch3_5_test1_b295.PNG" alt="Powered by Struts"/>
    <% } else if (currentId==296) { %>
       <html:img page="/pic/ch3_5_test2_b296.PNG" alt="Powered by Struts"/>
    <% } else if (currentId==300) { %>
       <html:img page="/pic/ch3_4_test3_b300.PNG" alt="Powered by Struts"/>
    <% } else if (currentId==301) { %>
       <html:img page="/pic/ch3_4_test4_b301.PNG" alt="Powered by Struts"/>
    <% } else if (currentId==299) { %>
       <html:img page="/pic/ch3_4_test1_db160.PNG" alt="Powered by Struts"/>
    <% } else if (currentId==302) { %>
       <html:img page="/pic/ch3_3_test1_b302.PNG" alt="Powered by Struts"/>
    <% } else if (currentId==303) { %>
       <html:img page="/pic/ch3_3_test2_b303.PNG" alt="Powered by Struts"/>
    <% } else if (currentId==304) { %>
       <html:img page="/pic/ch3_3_test3_b304.PNG" alt="Powered by Struts"/>
    <% } else if (currentId==305) { %>
       <html:img page="/pic/ch3_3_test4_b305.PNG" alt="Powered by Struts"/>
    <% } else if (currentId==306) { %>
       <html:img page="/pic/ch3_3_test5_b306.PNG" alt="Powered by Struts"/>
    <% } else if (currentId==307) { %>
       <html:img page="/pic/ch3_3_test6_b307.PNG" alt="Powered by Struts"/>
    <% } else if (currentId==311) { %>
       <html:img page="/pic/ch3_2_test2_b311.PNG" alt="Powered by Struts"/>
    <% } else if (currentId==324) { %>
       <html:img page="/pic/mid_s1_test1_db191.PNG" alt="Powered by Struts"/>
    <% } else if (currentId==325) { %>
       <html:img page="/pic/mid_s1_test2_b325.PNG" alt="Powered by Struts"/>
    <% } else if (currentId==345) { %>
       <html:img page="/pic/mid_s2_test1_db221.PNG" alt="Powered by Struts"/>
    <% } else if (currentId==346) { %>
       <html:img page="/pic/mid_s2_test2_b346.PNG" alt="Powered by Struts"/>
    <% } else if (currentId==373) { %>
       <html:img page="/pic/ch1_11_test1_b373e.PNG" alt="Powered by Struts"/>
    <% } else if (currentId==374) { %>
       <html:img page="/pic/ch1_11_test2_b374e.PNG" alt="Powered by Struts"/>
    <% } else if (currentId==375) { %>
       <html:img page="/pic/ch1_11_test1_b375e.PNG" alt="Powered by Struts"/>
    <% } else if (currentId==395) { %>
       <html:img page="/pic/mid_real_test2_db352.PNG" alt="Powered by Struts"/>
    <% } else if (currentId==396) { %>
       <html:img page="/pic/mid_real_test3_db353.PNG" alt="Powered by Struts"/>
    <% } else if (currentId==405) { %>
       <html:img page="/pic/ch5_1_test1_b405.PNG" alt="Powered by Struts"/>
    <% } else if (currentId==406) { %>
       <html:img page="/pic/ch5_1_test2_b406.PNG" alt="Powered by Struts"/>
    <% } else if (currentId==423) { %>
       <html:img page="/pic/ch5_5_test2_b423e.PNG" alt="Powered by Struts"/>
    <% } else if (currentId==430) { %>
       <html:img page="/pic/ch5_6_test3_b430.PNG" alt="Powered by Struts"/>
    <% } else if (currentId==442) { %>
       <html:img page="/pic/ch5_hk_test4_b442.PNG" alt="Powered by Struts"/>
    <% } else if (currentId==443) { %>
       <html:img page="/pic/ch5_hk_test5_b443.PNG" alt="Powered by Struts"/>
    <% } else if (currentId==444) { %>
       <html:img page="/pic/ch5_hk_test6_b444.PNG" alt="Powered by Struts"/>
    <% } else if (currentId==445) { %>
       <html:img page="/pic/ch5_hk_test7_b445.PNG" alt="Powered by Struts"/>
    <% } else if (currentId==446) { %>
       <html:img page="/pic/ch5_hk_test8_b446.PNG" alt="Powered by Struts"/>
    <% } else if (currentId==450) { %>
       <html:img page="/pic/ch5_test8_db385.PNG" alt="Powered by Struts"/>
    <% } else if (currentId==451) { %>
       <html:img page="/pic/ch5_test9_b451.PNG" alt="Powered by Struts"/>
    <% } else if (currentId==452) { %>
       <html:img page="/pic/ch5_test10_b452.PNG" alt="Powered by Struts"/>
    <% } else if (currentId==461) { %>
       <html:img page="/pic/ch5_add_test2_b461.PNG" alt="Powered by Struts"/>
    <% } else if (currentId==462) { %>
       <html:img page="/pic/ch5_add_test3_1_b462.PNG" alt="Powered by Struts"/>
    <% } else if (currentId==463) { %>
       <html:img page="/pic/ch5_add_test3_2_b463.PNG" alt="Powered by Struts"/>
    <% } else if (currentId==464) { %>
       <html:img page="/pic/ch5_add_test3_3_b464.PNG" alt="Powered by Struts"/>
    <% } else if (currentId==465) { %>
       <html:img page="/pic/ch5_add_test3_4_b465.PNG" alt="Powered by Struts"/>
    <% } else if (currentId==466) { %>
       <html:img page="/pic/ch5_add_test3_5_b466.PNG" alt="Powered by Struts"/>
    <% } else if (currentId==467) { %>
       <html:img page="/pic/ch5_add_test3_6_b467.PNG" alt="Powered by Struts"/>
    <% } else if (currentId==468) { %>
       <html:img page="/pic/ch5_56_test6.PNG" alt="Powered by Struts"/>
    <% } else if (currentId==469) { %>
       <html:img page="/pic/ch5_add_test4_db388.PNG" alt="Powered by Struts"/>
    <% } else if (currentId==470) { %>
       <html:img page="/pic/ch5_add_test5_1_b470.PNG" alt="Powered by Struts"/>
    <% } else if (currentId==471) { %>
       <html:img page="/pic/ch5_add_test5_2_b471.PNG" alt="Powered by Struts"/>
    <% } else if (currentId==472) { %>
       <html:img page="/pic/ch5_add_test5_3_b472.PNG" alt="Powered by Struts"/>
    <% } else if (currentId==473) { %>
       <html:img page="/pic/ch5_add_test5_4_b473.PNG" alt="Powered by Struts"/>
    <% } else if (currentId==474) { %>
       <html:img page="/pic/ch5_add_test5_5_b474.PNG" alt="Powered by Struts"/>
    <% } else if (currentId==532) { %>
       <html:img page="/pic/ch6_6_test1_b532.PNG" alt="Powered by Struts"/>
    <% } else if (currentId==533) { %>
       <html:img page="/pic/ch6_6_test2_b533.PNG" alt="Powered by Struts"/>
    <% } else if (currentId==534) { %>
       <html:img page="/pic/ch6_6_test3_b534.PNG" alt="Powered by Struts"/>
    <% } else if (currentId==535) { %>
       <html:img page="/pic/ch6_6_test4_b535.PNG" alt="Powered by Struts"/>
    <% } else if (currentId==536) { %>
       <html:img page="/pic/ch6_6_test5_b536.PNG" alt="Powered by Struts"/>
    <% } else if (currentId==537) { %>
       <html:img page="/pic/ch6_6_test6_b537.PNG" alt="Powered by Struts"/>
    <% } else if (currentId==538) { %>
       <html:img page="/pic/ch6_6_test7_b538.PNG" alt="Powered by Struts"/>
    <% } else if (currentId==539) { %>
       <html:img page="/pic/ch6_6_test8_b539.PNG" alt="Powered by Struts"/>
    <% } else if (currentId==542) { %>
       <html:img page="/pic/ch6_8_test1_b542.PNG" alt="Powered by Struts"/>
    <% } else if (currentId==594) { %>
       <html:img page="/pic/ch56_test1_db607.PNG" alt="Powered by Struts"/>
    <% } else if (currentId==596) { %>
       <html:img page="/pic/ch56_test2_db613.PNG" alt="Powered by Struts"/>
    <% } else if (currentId==599) { %>
       <html:img page="/pic/ch56_test4_b599.PNG" alt="Powered by Struts"/>
    <% } else if ((currentId>=630 && currentId<=633) || currentId==644) { %>
       <html:img page="/pic/ch7_1_test1_b630.png" alt="Powered by Struts"/>
    <% } else if ((currentId>=639 && currentId<=642) || (currentId>=694 && currentId<=695)) { %>
       <html:img page="/pic/ch7_1_test2_b639.png" alt="Powered by Struts"/>
    <% } else if (currentId>=645 && currentId<=648) { %>
       <html:img page="/pic/ch7_2_test1_b645.png" alt="Powered by Struts"/>
    <% } else if (currentId>=649 && currentId<=654) { %>
      <html:img page="/pic/ch7_2_test2_b649_1.png" alt="Powered by Struts"/><html:img page="/pic/ch7_2_test3_b649_2.png" alt="Powered by Struts"/>
    <% } else if (currentId==659) { %>
       <html:img page="/pic/final_1_test2_b659.png" alt="Powered by Struts"/>
    <% } else if (currentId==660) { %>
       <html:img page="/pic/final_1_test3_b660.png" alt="Powered by Struts"/>
    <% } else if (currentId==661) { %>
       <html:img page="/pic/final_1_test4_b661.png" alt="Powered by Struts"/>
    <% } else if (currentId==668) { %>
       <html:img page="/pic/final_1_test5_db718_1.png" alt="Powered by Struts"/><br><html:img page="/pic/final_1_test6_db718_2.png" alt="Powered by Struts"/>
    <% } else if (currentId==673) { %>
       <html:img page="/pic/final_2_test1_b669.png" alt="Powered by Struts"/>
    <% } else if (currentId>=678 && currentId<=681) { %>
       <html:img page="/pic/final_2_test3_b678.png" alt="Powered by Struts"/>
    <% } else if (currentId==682) { %>
       <html:img page="/pic/final_2_test4_b682.png" alt="Powered by Struts"/>
    <% } else if (currentId==683) { %>
       <html:img page="/pic/final_2_test5_db728.png" alt="Powered by Struts"/>
    <% } else if (currentId>=684 && currentId<=687) { %>
       <html:img page="/pic/ch7_3_test1_b684.png" alt="Powered by Struts"/>
    <% } else if (currentId>=688 && currentId<=693) { %>
       <html:img page="/pic/ch7_3_test2_b688.png" alt="Powered by Struts"/>
    <% } else if (currentId>=696 && currentId<=700) { %>
       <html:img page="/pic/ch7_self_test1_b696.png" alt="Powered by Struts"/>
    <% } else if (currentId>=701 && currentId<=705) { %>
       <html:img page="/pic/ch7_self_test2_b701.png" alt="Powered by Struts"/>
    <% } else if (currentId>=706 && currentId<=707) { %>
       <html:img page="/pic/ch7_test1_b706.png" alt="Powered by Struts"/>
    <% } else if (currentId>=708 && currentId<=710) { %>
       <html:img page="/pic/final_3_test1_b708_1.png" alt="Powered by Struts"/><html:img page="/pic/final_3_test2_b708_2.png" alt="Powered by Struts"/>
    <% } else if (currentId>=711 && currentId<=714) { %>
       <html:img page="/pic/final_4_test1_b711.png" alt="Powered by Struts"/>
    <% } else if (currentId>=717 && currentId<=721) { %>
       <html:img page="/pic/ch7_test4_b717.png" alt="Powered by Struts"/>
    <% } else if (currentId>=722 && currentId<=726) { %>
       <html:img page="/pic/ch7_test5_b722.png" alt="Powered by Struts"/>
    <% } else if (currentId==727) { %>
       <html:img page="/pic/ch7_test6_b727.png" alt="Powered by Struts"/>
    <% } else if (currentId>=728 && currentId<=732) { %>
       <html:img page="/pic/ch7_hk_test1_b728.png" alt="Powered by Struts"/>
    <% } else if (currentId==733) { %>
       <html:img page="/pic/ch7_hk_test2_b733.png" alt="Powered by Struts"/>
    <% } else if (currentId==754) { %>
       <html:img page="/pic/ch8_self_test1_b754.png" alt="Powered by Struts"/>
    <% } else if (currentId>=815 && currentId<=817) { %>
       <html:img page="/pic/final_1d_test2_b815.png" alt="Powered by Struts"/>
    <% } else if (currentId==823) { %>
       <html:img page="/pic/final_5_test1_db826.png" alt="Powered by Struts"/>
    <% } else if (currentId==824) { %>
       <html:img page="/pic/final_5_test6_b824.png" alt="Powered by Struts"/>
    <% } else if (currentId>=846 && currentId<=848) { %>
       <html:img page="/pic/final_2d_test3_b846.png" alt="Powered by Struts"/>
    <% } else if (currentId==852) { %>
       <html:img page="/pic/final_self_test8_b852.png" alt="Powered by Struts"/>
    <% } else if (currentId==853) { %>
       <html:img page="/pic/final_self_test9_b853.png" alt="Powered by Struts"/>
    <% } else if (currentId>=861 && currentId<=867) { %>
       <html:img page="/pic/final_self_test12_b861_12.png" alt="Powered by Struts"/>
    <% } else if (currentId>=879 && currentId<=882) { %>
       <html:img page="/pic/final_6_test2_b879.png" alt="Powered by Struts"/>
    <% } else if (currentId>=888 && currentId<=893) { %>
       <html:img page="/pic/final_7_test1_b888.png" alt="Powered by Struts"/>
    <% } else if (currentId==894) { %>
       <html:img page="/pic/final_7_test2_b894.png" alt="Powered by Struts"/>
    <% } else if (currentId>=895 && currentId<=897) { %>
       <html:img page="/pic/final_7_test3_b895.png" alt="Powered by Struts"/>
    <% } else if (currentId>=898 && currentId<=899) { %>
       <html:img page="/pic/final_7_test4_b898.png" alt="Powered by Struts"/>
    <% } else if (currentId>=900 && currentId<=902) { %>
       <html:img page="/pic/final_7_test5_b900.png" alt="Powered by Struts"/>
    <% } else if (currentId>=903 && currentId<=905) { %>
       <html:img page="/pic/final_7_test6_b903.png" alt="Powered by Struts"/>
    <% } else if (currentId>=906 && currentId<=908) { %>
       <html:img page="/pic/final_7_test8_b893_fortable.png" alt="Powered by Struts"/>
    <% } else if (currentId>=909 && currentId<=912) { %>
       <html:img page="/pic/final_7_test7_b909.png" alt="Powered by Struts"/>
    <% } else if (currentId==931) { %>
       <html:img page="/pic/final_9_test1_db910.png" alt="Powered by Struts"/>
    <% } else if (currentId==945) { %>
       <html:img page="/pic/final_9_test2_b945.png" alt="Powered by Struts"/>
    <% } %>

    <% if (currentId>=13 && currentId<=18){ %>
       <CENTER><H3><a href="javascript:window.open('AppletExample/CalculatorAppletApplication.html')"><%= calculatorHint%></a></H3></CENTER><P>
    <% } %>  

    <% if (StringSetTransfer.inGroup(angleIds_One, currentId)){ %>
       <CENTER><H3><a href="javascript:window.open('AppletExample/DrawLineApplet.html')"><%= inputHint3%></a></H3></CENTER><P>
    <% } %>

    <% if (currentId==149 || currentId==150 || currentId==392){ %>
       <CENTER><H3><a href="javascript:window.open('AppletExample/DrawLineApplet2.html')"><%= inputHint3%></a></H3></CENTER><P>
    <% } %>

    <% if (currentId==245 || currentId==312){ %>
       <CENTER><H3><a href="javascript:window.open('AppletExample/DrawLineApplet3.html')"><%= inputHint3%></a></H3></CENTER><P>
    <% } %>

    <% if (currentId==250){ %>
       <CENTER><H3><a href="javascript:window.open('AppletExample/DrawLineApplet4.html')"><%= inputHint3%></a></H3></CENTER><P>
    <% } %>

    <% if (currentId>=285 && currentId<=286){ %>
       <CENTER><H3><a href="javascript:window.open('AppletExample/DrawLineApplet5.html')"><%= inputHint3%></a></H3></CENTER><P>
    <% } %>

    <% if (currentId>=308 && currentId<=310){ %>
       <CENTER><H3><a href="javascript:window.open('AppletExample/DrawLineApplet6.html')"><%= inputHint3%></a></H3></CENTER><P>
    <% } %>

    <% if (currentId==313){ %>
       <CENTER><H3><a href="javascript:window.open('AppletExample/DrawLineApplet7.html')"><%= inputHint3%></a></H3></CENTER><P>
    <% } %>

    <% if (currentId==407 || currentId==408){ %>
       <CENTER><H3><a href="javascript:window.open('AppletExample/DrawLineApplet8.html')"><%= inputHint3%></a></H3></CENTER><P>
    <% } %>

    <% if ((currentId>=409 && currentId<=412) || currentId==435 || currentId==857){ %>
       <CENTER><H3><a href="javascript:window.open('AppletExample/DrawLineApplet9.html')"><%= inputHint3%></a></H3></CENTER><P>
    <% } %>

    <% if (currentId==413){ %>
       <CENTER><H3><a href="javascript:window.open('AppletExample/DrawLineApplet10.html')"><%= inputHint3%></a></H3></CENTER><P>
    <% } %>

    <% if (currentId==414){ %>
       <CENTER><H3><a href="javascript:window.open('AppletExample/DrawLineApplet11.html')"><%= inputHint3%></a></H3></CENTER><P>
    <% } %>

    <% if (currentId==415 || currentId==460){ %>
       <CENTER><H3><a href="javascript:window.open('AppletExample/DrawLineApplet12.html')"><%= inputHint3%></a></H3></CENTER><P>
    <% } %>

    <% if (currentId==416 || currentId==417 || currentId==454 || currentId==830 || currentId==858){ %>
       <CENTER><H3><a href="javascript:window.open('AppletExample/DrawLineApplet13.html')"><%= inputHint3%></a></H3></CENTER><P>
    <% } %>

    <% if (currentId==418){ %>
       <CENTER><H3><a href="javascript:window.open('AppletExample/DrawLineApplet14.html')"><%= inputHint3%></a></H3></CENTER><P>
    <% } %>

    <% if (currentId==424 || currentId==425){ %>
       <CENTER><H3><a href="javascript:window.open('AppletExample/DrawLineApplet15.html')"><%= inputHint3%></a></H3></CENTER><P>
    <% } %>

    <% if (currentId==426){ %>
       <CENTER><H3><a href="javascript:window.open('AppletExample/DrawLineApplet16.html')"><%= inputHint3%></a></H3></CENTER><P>
    <% } %>

    <% if (currentId==427 || currentId==428){ %>
       <CENTER><H3><a href="javascript:window.open('AppletExample/DrawLineApplet17.html')"><%= inputHint3%></a></H3></CENTER><P>
    <% } %>

    <% if (currentId==431 || currentId==457 || currentId==677 || currentId==844){ %>
       <CENTER><H3><a href="javascript:window.open('AppletExample/DrawLineApplet18.html')"><%= inputHint3%></a></H3></CENTER><P>
    <% } %>

    <% if (currentId==432 || currentId==458 || currentId==845){ %>
       <CENTER><H3><a href="javascript:window.open('AppletExample/DrawLineApplet19.html')"><%= inputHint3%></a></H3></CENTER><P>
    <% } %>

    <% if (currentId==433){ %>
       <CENTER><H3><a href="javascript:window.open('AppletExample/DrawLineApplet20.html')"><%= inputHint3%></a></H3></CENTER><P>
    <% } %>

    <% if (currentId==436 || currentId==667 || currentId==764){ %>
       <CENTER><H3><a href="javascript:window.open('AppletExample/DrawLineApplet21.html')"><%= inputHint3%></a></H3></CENTER><P>
    <% } %>

    <% if (currentId==437){ %>
       <CENTER><H3><a href="javascript:window.open('AppletExample/DrawLineApplet22.html')"><%= inputHint3%></a></H3></CENTER><P>
    <% } %>

    <% if (currentId==438){ %>
       <CENTER><H3><a href="javascript:window.open('AppletExample/DrawLineApplet23.html')"><%= inputHint3%></a></H3></CENTER><P>
    <% } %>

    <% if (currentId==439){ %>
       <CENTER><H3><a href="javascript:window.open('AppletExample/DrawLineApplet24.html')"><%= inputHint3%></a></H3></CENTER><P>
    <% } %>

    <% if (currentId==440){ %>
       <CENTER><H3><a href="javascript:window.open('AppletExample/DrawLineApplet25.html')"><%= inputHint3%></a></H3></CENTER><P>
    <% } %> 

    <% if (currentId==441){ %>
       <CENTER><H3><a href="javascript:window.open('AppletExample/DrawLineApplet26.html')"><%= inputHint3%></a></H3></CENTER><P>
    <% } %>

    <% if (currentId==447 || currentId==453){ %>
       <CENTER><H3><a href="javascript:window.open('AppletExample/DrawLineApplet27.html')"><%= inputHint3%></a></H3></CENTER><P>
    <% } %>

    <% if (currentId==455){ %>
       <CENTER><H3><a href="javascript:window.open('AppletExample/DrawLineApplet28.html')"><%= inputHint3%></a></H3></CENTER><P>
    <% } %>

    <% if (currentId==456){ %>
       <CENTER><H3><a href="javascript:window.open('AppletExample/DrawLineApplet29.html')"><%= inputHint3%></a></H3></CENTER><P>
    <% } %>

    <% if (currentId==459){ %>
       <CENTER><H3><a href="javascript:window.open('AppletExample/DrawLineApplet30.html')"><%= inputHint3%></a></H3></CENTER><P>
    <% } %>

    <% if (currentId==475){ %>
       <CENTER><H3><a href="javascript:window.open('AppletExample/DrawLineApplet31.html')"><%= inputHint3%></a></H3></CENTER><P>
    <% } %> 

    <% if (currentId==567){ %>
       <CENTER><H3><a href="javascript:window.open('AppletExample/TableApplet.html')"><%= inputHint5%></a></H3></CENTER><P>
    <% } %> 

    <% if (currentId==568){ %>
       <CENTER><H3><a href="javascript:window.open('AppletExample/TableApplet2.html')"><%= inputHint5%></a></H3></CENTER><P>
    <% } %> 

    <% if (currentId==593){ %>
       <CENTER><H3><a href="javascript:window.open('AppletExample/TableApplet3.html')"><%= inputHint5%></a></H3></CENTER><P>
    <% } %> 

    <% if (currentId==601){ %>
       <CENTER><H3><a href="javascript:window.open('AppletExample/DrawLineApplet32.html')"><%= inputHint3%></a></H3></CENTER><P>
    <% } %> 

    <% if (currentId==602){ %>
       <CENTER><H3><a href="javascript:window.open('AppletExample/DrawLineApplet33.html')"><%= inputHint3%></a></H3></CENTER><P>
    <% } %> 

    <% if (currentId==603){ %>
       <CENTER><H3><a href="javascript:window.open('AppletExample/DrawLineApplet34.html')"><%= inputHint3%></a></H3></CENTER><P>
    <% } %> 

    <% if (currentId==632){ %>
       <CENTER><H3><a href="javascript:window.open('AppletExample/TableApplet4.html')"><%= inputHint5%></a></H3></CENTER><P>
    <% } %>

    <% if (currentId==633){ %>
       <CENTER><H3><a href="javascript:window.open('AppletExample/ChartApplet.html')"><%= inputHint6%></a></H3></CENTER><P>
    <% } %>

    <% if (currentId==639){ %>
       <CENTER><H3><a href="javascript:window.open('AppletExample/ChartApplet2.html')"><%= inputHint6%></a></H3></CENTER><P>
    <% } %> 

    <% if (currentId==645){ %>
       <CENTER><H3><a href="javascript:window.open('AppletExample/ChartApplet3.html')"><%= inputHint6%></a></H3></CENTER><P>
    <% } %> 

    <% if (currentId==646){ %>
       <CENTER><H3><a href="javascript:window.open('AppletExample/ChartApplet4.html')"><%= inputHint6%></a></H3></CENTER><P>
    <% } %> 

    <% if (currentId==649){ %>
       <CENTER><H3><a href="javascript:window.open('AppletExample/ChartApplet5.html')"><%= inputHint6%></a></H3></CENTER><P>
    <% } %> 

    <% if (currentId==651){ %>
       <CENTER><H3><a href="javascript:window.open('AppletExample/ChartApplet6.html')"><%= inputHint6%></a></H3></CENTER><P>
    <% } %> 

    <% if (currentId==665 || currentId==666){ %>
       <CENTER><H3><a href="javascript:window.open('AppletExample/DrawLineApplet35.html')"><%= inputHint3%></a></H3></CENTER><P>
    <% } %>

    <% if (currentId==676){ %>
       <CENTER><H3><a href="javascript:window.open('AppletExample/DrawLineApplet36.html')"><%= inputHint3%></a></H3></CENTER><P>
    <% } %> 

    <% if (currentId==678){ %>
       <CENTER><H3><a href="javascript:window.open('AppletExample/TableApplet5.html')"><%= inputHint5%></a></H3></CENTER><P>
    <% } %> 

    <% if (currentId==679){ %>
       <CENTER><H3><a href="javascript:window.open('AppletExample/ChartApplet7.html')"><%= inputHint6%></a></H3></CENTER><P>
    <% } %> 

    <% if (currentId==680){ %>
       <CENTER><H3><a href="javascript:window.open('AppletExample/ChartApplet8.html')"><%= inputHint6%></a></H3></CENTER><P>
    <% } %> 

    <% if (currentId==683){ %>
       <CENTER><H3><a href="javascript:window.open('AppletExample/DrawLineApplet37.html')"><%= inputHint3%></a></H3></CENTER><P>
    <% } %>

    <% if (currentId==684){ %>
       <CENTER><H3><a href="javascript:window.open('AppletExample/ChartApplet9.html')"><%= inputHint6%></a></H3></CENTER><P>
    <% } %>

    <% if (currentId==688){ %>
       <CENTER><H3><a href="javascript:window.open('AppletExample/ChartApplet10.html')"><%= inputHint6%></a></H3></CENTER><P>
    <% } %> 

    <% if (currentId==692){ %>
       <CENTER><H3><a href="javascript:window.open('AppletExample/ChartApplet11.html')"><%= inputHint6%></a></H3></CENTER><P>
    <% } %> 

    <% if (currentId==696){ %>
       <CENTER><H3><a href="javascript:window.open('AppletExample/ChartApplet12.html')"><%= inputHint6%></a></H3></CENTER><P>
    <% } %> 

    <% if (currentId==701){ %>
       <CENTER><H3><a href="javascript:window.open('AppletExample/ChartApplet13.html')"><%= inputHint6%></a></H3></CENTER><P>
    <% } %> 

    <% if (currentId==702){ %>
       <CENTER><H3><a href="javascript:window.open('AppletExample/ChartApplet14.html')"><%= inputHint6%></a></H3></CENTER><P>
    <% } %> 

    <% if (currentId==711){ %>
       <CENTER><H3><a href="javascript:window.open('AppletExample/ChartApplet15.html')"><%= inputHint6%></a></H3></CENTER><P>
    <% } %>  

    <% if (currentId==715){ %>
       <CENTER><H3><a href="javascript:window.open('AppletExample/TableApplet6.html')"><%= inputHint5%></a></H3></CENTER><P>
    <% } %>

    <% if (currentId==716){ %>
       <CENTER><H3><a href="javascript:window.open('AppletExample/TableApplet7.html')"><%= inputHint5%></a></H3></CENTER><P>
    <% } %>

    <% if (currentId==717){ %>
       <CENTER><H3><a href="javascript:window.open('AppletExample/ChartApplet16.html')"><%= inputHint6%></a></H3></CENTER><P>
    <% } %>

    <% if (currentId==722){ %>
       <CENTER><H3><a href="javascript:window.open('AppletExample/ChartApplet17.html')"><%= inputHint6%></a></H3></CENTER><P>
    <% } %>

    <% if (currentId==728){ %>
       <CENTER><H3><a href="javascript:window.open('AppletExample/ChartApplet18.html')"><%= inputHint6%></a></H3></CENTER><P>
    <% } %>

    <% if (currentId==733){ %>
       <CENTER><H3><a href="javascript:window.open('AppletExample/ChartApplet19.html')"><%= inputHint6%></a></H3></CENTER><P>
    <% } %>

    <% if (currentId==746){ %>
       <CENTER><H3><a href="javascript:window.open('AppletExample/TableApplet8.html')"><%= inputHint5%></a></H3></CENTER><P>
    <% } %> 

    <% if (currentId==747){ %>
       <CENTER><H3><a href="javascript:window.open('AppletExample/GameApplet.html')"><%= inputHint7%></a></H3></CENTER><P>
    <% } %> 

    <% if (currentId==754){ %>
       <CENTER><H3><a href="javascript:window.open('AppletExample/TableApplet9.html')"><%= inputHint5%></a></H3></CENTER><P>
    <% } %>

    <% if (currentId==755){ %>
       <CENTER><H3><a href="javascript:window.open('AppletExample/GameApplet5.html')"><%= inputHint7%></a></H3></CENTER><P>
    <% } %> 

    <% if (currentId==756){ %>
       <CENTER><H3><a href="javascript:window.open('AppletExample/GameApplet8.html')"><%= inputHint7%></a></H3></CENTER><P>
    <% } %> 

    <% if (currentId==760){ %>
       <CENTER><H3><a href="javascript:window.open('AppletExample/TableApplet10.html')"><%= inputHint5%></a></H3></CENTER><P>
    <% } %> 

    <% if (currentId==768){ %>
       <CENTER><H3><a href="javascript:window.open('AppletExample/DrawLineApplet38.html')"><%= inputHint3%></a></H3></CENTER><P>
    <% } %>

    <% if (currentId==782 || currentId==783){ %>
       <CENTER><H3><a href="javascript:window.open('AppletExample/DrawLineApplet39.html')"><%= inputHint3%></a></H3></CENTER><P>
    <% } %>

    <% if (currentId==781){ %>
       <CENTER><H3><a href="javascript:window.open('AppletExample/DrawLineApplet40.html')"><%= inputHint3%></a></H3></CENTER><P>
    <% } %>

    <% if (currentId==784){ %>
       <CENTER><H3><a href="javascript:window.open('AppletExample/DrawLineApplet41.html')"><%= inputHint3%></a></H3></CENTER><P>
    <% } %>

    <% if (currentId==813 || currentId==814){ %>
       <CENTER><H3><a href="javascript:window.open('AppletExample/DrawLineApplet42.html')"><%= inputHint3%></a></H3></CENTER><P>
    <% } %>

    <% if (currentId==815){ %>
       <CENTER><H3><a href="javascript:window.open('AppletExample/ChartApplet20.html')"><%= inputHint6%></a></H3></CENTER><P>
    <% } %>

    <% if (currentId>=825 && currentId<=827){ %>
       <CENTER><H3><a href="javascript:window.open('AppletExample/DrawLineApplet43.html')"><%= inputHint3%></a></H3></CENTER><P>
    <% } %>

    <% if (currentId==828){ %>
       <CENTER><H3><a href="javascript:window.open('AppletExample/DrawLineApplet44.html')"><%= inputHint3%></a></H3></CENTER><P>
    <% } %>

    <% if (currentId==829){ %>
       <CENTER><H3><a href="javascript:window.open('AppletExample/DrawLineApplet45.html')"><%= inputHint3%></a></H3></CENTER><P>
    <% } %>

    <% if (currentId>=831 && currentId<=832){ %>
       <CENTER><H3><a href="javascript:window.open('AppletExample/DrawLineApplet46.html')"><%= inputHint3%></a></H3></CENTER><P>
    <% } %>

    <% if (currentId==833){ %>
       <CENTER><H3><a href="javascript:window.open('AppletExample/DrawLineApplet47.html')"><%= inputHint3%></a></H3></CENTER><P>
    <% } %>

    <% if (currentId==834){ %>
       <CENTER><H3><a href="javascript:window.open('AppletExample/DrawLineApplet48.html')"><%= inputHint3%></a></H3></CENTER><P>
    <% } %>

    <% if (currentId==835){ %>
       <CENTER><H3><a href="javascript:window.open('AppletExample/DrawLineApplet49.html')"><%= inputHint3%></a></H3></CENTER><P>
    <% } %>

    <% if (currentId==843){ %>
       <CENTER><H3><a href="javascript:window.open('AppletExample/TableApplet11.html')"><%= inputHint5%></a></H3></CENTER><P>
    <% } %>

    <% if (currentId==846){ %>
       <CENTER><H3><a href="javascript:window.open('AppletExample/ChartApplet21.html')"><%= inputHint6%></a></H3></CENTER><P>
    <% } %>

    <% if (currentId>=859 && currentId<=860){ %>
       <CENTER><H3><a href="javascript:window.open('AppletExample/DrawLineApplet50.html')"><%= inputHint3%></a></H3></CENTER><P>
    <% } %>

    <% if (currentId==861){ %>
       <CENTER><H3><a href="javascript:window.open('AppletExample/ChartApplet22.html')"><%= inputHint6%></a></H3></CENTER><P>
    <% } %>

    <% if (currentId==862){ %>
       <CENTER><H3><a href="javascript:window.open('AppletExample/ChartApplet23.html')"><%= inputHint6%></a></H3></CENTER><P>
    <% } %>

    <% if (currentId==895){ %>
       <CENTER><H3><a href="javascript:window.open('AppletExample/TableApplet12.html')"><%= inputHint5%></a></H3></CENTER><P>
    <% } %>

    <% if (currentId==899){ %>
       <CENTER><H3><a href="javascript:window.open('AppletExample/TableApplet13.html')"><%= inputHint5%></a></H3></CENTER><P>
    <% } %>

    <% if (currentId==900){ %>
       <CENTER><H3><a href="javascript:window.open('AppletExample/ChartApplet24.html')"><%= inputHint6%></a></H3></CENTER><P>
    <% } %>

    <% if (currentId==903){ %>
       <CENTER><H3><a href="javascript:window.open('AppletExample/ChartApplet25.html')"><%= inputHint6%></a></H3></CENTER><P>
    <% } %>

    <% if (currentId==906){ %>
       <CENTER><H3><a href="javascript:window.open('AppletExample/TableApplet14.html')"><%= inputHint5%></a></H3></CENTER><P>
    <% } %>

    <% if (currentId==907){ %>
       <CENTER><H3><a href="javascript:window.open('AppletExample/ChartApplet26.html')"><%= inputHint6%></a></H3></CENTER><P>
    <% } %>

    <% if (currentId==909){ %>
       <CENTER><H3><a href="javascript:window.open('AppletExample/ChartApplet27.html')"><%= inputHint6%></a></H3></CENTER><P>
    <% } %>

    <% if (currentId==910){ %>
       <CENTER><H3><a href="javascript:window.open('AppletExample/ChartApplet28.html')"><%= inputHint6%></a></H3></CENTER><P>
    <% } %>

    <% if (currentId==917){ %>
       <CENTER><H3><a href="javascript:window.open('AppletExample/TableApplet15.html')"><%= inputHint5%></a></H3></CENTER><P>
    <% } %> 

    <% if (currentId==921){ %>
       <CENTER><H3><a href="javascript:window.open('AppletExample/TableApplet16.html')"><%= inputHint5%></a></H3></CENTER><P>
    <% } %>  

    <% if (currentId==922){ %>
       <CENTER><H3><a href="javascript:window.open('AppletExample/TableApplet17.html')"><%= inputHint5%></a></H3></CENTER><P>
    <% } %> 

    <% if (currentId==923){ %>
       <CENTER><H3><a href="javascript:window.open('AppletExample/TableApplet18.html')"><%= inputHint5%></a></H3></CENTER><P>
    <% } %> 

    <% if (currentId>=938 && currentId<=939){ %>
       <CENTER><H3><a href="javascript:window.open('AppletExample/DrawLineApplet51.html')"><%= inputHint3%></a></H3></CENTER><P>
    <% } %> 

    <% if (currentId==940){ %>
       <CENTER><H3><a href="javascript:window.open('AppletExample/DrawLineApplet52.html')"><%= inputHint3%></a></H3></CENTER><P>
    <% } %> 

    <% if (currentId==945){ %>
       <CENTER><H3><a href="javascript:window.open('AppletExample/ChartApplet29.html')"><%= inputHint6%></a></H3></CENTER><P>
    <% } %>

    <% remainSeconds = _pb2.getRemainSeconds();
       if (remainSeconds > 0 && _pb2.getPlanStatus().equals("not finished")){ 
    %>

    <html:form action="/SmartRandomCheck.do" >

      <%= blankHint%>
      <% if (StringSetTransfer.inGroup(angleIds, currentId) || StringSetTransfer.inGroup(lineIds, currentId) || StringSetTransfer.inGroup(tableIds, currentId) || StringSetTransfer.inGroup(chartIds, currentId) || StringSetTransfer.inGroup(gameIds, currentId)){  %>
         <html:text property="tmpStr" size="16" maxlength="100" value="<%= tmpStr2%>"/><br>
         <html:hidden property="inputAnswer" value="<%= strInput%>"/>
      <% } else { %>
         <html:text property="inputAnswer" size="16" maxlength="100" value="<%= strInput%>"/><br>
      <% } %>
      <html:hidden property="correctAnswer" value="<%= _fbb.getSolution()%>"/>
      <html:hidden property="solution" value="<%= _fbb.getSolution()%>"/>
      <html:hidden property="source" value="<%= _fbb.getSource()%>"/>
      <html:hidden property="statement_1st" value="<%= _fbb.getStatement_1st()%>"/>
      <html:hidden property="statement_2nd" value="<%= _fbb.getStatement_2nd()%>"/>
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
      <html:hidden property="correctAnswer" value="<%= _fbb.getSolution()%>"/>
      <html:hidden property="solution" value="<%= _fbb.getSolution()%>"/>
      <html:hidden property="source" value="<%= _fbb.getSource()%>"/>
      <html:hidden property="statement_1st" value="<%= _fbb.getStatement_1st()%>"/>
      <html:hidden property="statement_2nd" value="<%= _fbb.getStatement_2nd()%>"/>
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

      <center><h2><%= strExit%></h2></h2></center>
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

    <!-- <APPLET CODEBASE="AppletExample" CODE="InputApplet2.class" WIDTH=360 HEIGHT=360></APPLET>  -->

    <html:img page="/struts-power.gif" alt="Powered by Struts"/>
   
  </body>
</html:html>



