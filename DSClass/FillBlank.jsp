<%@ include file="taglibs.jsp" %>

<html:html locale="true">
  <head>   
    <html:base/>
  </head>
  <body bgcolor="white"><p>    

   <html:errors/><p> 

    <logic:present name="fillblankbean" scope="session">
       <h1>
         <bean:message key="hello.jsp.page.problem"/>
         <bean:write name="fillblankbean" property="id" /><p>
         <bean:message key="hello.jsp.page.problemScore"/>
         <bean:write name="fillblankbean" property="score" /><p> 
         <bean:write name="fillblankbean" property="type" /><p>
         <bean:write name="fillblankbean" property="statement_1st" />  
         <bean:write name="fillblankbean" property="statement_2nd" />    
     
       </h1>
    </logic:present>  

    <logic:present name="personbean" scope="session">
       <h1>
         <bean:message key="hello.jsp.page.selectedStudent"/><bean:write name="personbean" property="userName" />
         <bean:message key="hello.jsp.page.doubleColon"/><bean:write name="personbean" property="trueName" /><p>          
       </h1>
    </logic:present>  

    <%! HttpSession session = null; 
        hello.FillBlankBean _fbb = null;
        hello.PersonBean _pb2 = null;
        String _hs = null;
        String _hs_tf = null;
        String _mhs = null;
        String _hs_fb = null;
        String _hs_fdb = null;
        String _hs_ftb = null;
        String _hs_fqb = null;
        String _hs_m = null;
        String _hs_tm = null;
        String _hs_d = null;
        String _hs_pm = null;
        int remainSeconds = 300;
        int id = 0;
    %>
    <% session = request.getSession(true);     
       _fbb = (hello.FillBlankBean)request.getAttribute(hello.Constants.FILLBLANK_KEY);
       _pb2 = (hello.PersonBean)request.getAttribute(hello.Constants.PERSON_KEY);
       _hs = (String)request.getAttribute(hello.Constants.HASHSET_KEY);
       _hs_tf = (String)request.getAttribute(hello.Constants.HASHSET_TF_KEY);
       _mhs = (String)request.getAttribute(hello.Constants.HASHSET_M_KEY);
       _hs_fb = (String)request.getAttribute(hello.Constants.HASHSET_FB_KEY);
       _hs_fdb = (String)request.getAttribute(hello.Constants.HASHSET_FDB_KEY);
       _hs_ftb = (String)request.getAttribute(hello.Constants.HASHSET_FTB_KEY);
       _hs_fqb = (String)request.getAttribute(hello.Constants.HASHSET_FQB_KEY);

       _hs_m = (String)request.getAttribute(hello.Constants.HASHSET_MY_KEY); 
       _hs_tm = (String)request.getAttribute(hello.Constants.HASHSET_TM_KEY);
       _hs_d = (String)request.getAttribute(hello.Constants.HASHSET_D_KEY); 
       _hs_pm = (String)request.getAttribute(hello.Constants.HASHSET_PM_KEY);

       id = _pb2.getCurrentProblemId();       
    %>
<!--    <h2><%= _fbb.getStatement_1st()%><%= _fbb.getStatement_2nd()%></h2> -->

    <% if (id == 1109){ %>
        <h1>1</h1><html:img page="/pic/b6_ch3_1_b1_1.PNG" alt="Powered by Struts"/><br><h1>2</h1><html:img page="/pic/b6_ch3_1_b1_2.PNG" alt="Powered by Struts"/><br><h1>3</h1><html:img page="/pic/b6_ch3_1_b1_3.PNG" alt="Powered by Struts"/><br><h1>4</h1><html:img page="/pic/b6_ch3_1_b1_4.PNG" alt="Powered by Struts"/><br><h1>5</h1><html:img page="/pic/b6_ch3_1_b1_5.PNG" alt="Powered by Struts"/> 
    <% } else if (id == 1113) { %>
        <html:img page="/pic/b6_ch3_1_b70.PNG" alt="Powered by Struts"/>
    <% } else if (id == 1114) { %>
        <html:img page="/pic/b6_ch3_1_b71.PNG" alt="Powered by Struts"/>
    <% } else if (id == 1115) { %>
        <html:img page="/pic/b6_ch4_1_b1115.PNG" alt="Powered by Struts"/> 
    <% } else if (id == 1116) { %>
        <html:img page="/pic/b6_ch4_1_b1116.PNG" alt="Powered by Struts"/>
    <% } else if (id == 1117) { %>
        <html:img page="/pic/b6_ch4_1_b1117_1.PNG" alt="Powered by Struts"/><html:img page="/pic/b6_ch4_1_b1117_2.PNG" alt="Powered by Struts"/>
    <% } else if (id == 1141) { %>
        <html:img page="/pic/b6_ch4_class5_b1141.png" alt="Powered by Struts"/>
    <% } else if (id == 1142) { %>
        <html:img page="/pic/b6_ch4_class5_b1142.png" alt="Powered by Struts"/>
    <% } else if (id == 1143) { %>
        <html:img page="/pic/b6_ch4_class5_b1143.png" alt="Powered by Struts"/>  
    <% } else if (id == 1144) { %>
        <html:img page="/pic/b6_ch4_class6_b1144.jpg" alt="Powered by Struts"/>  
    <% } else if (id == 1146) { %>
        <html:img page="/pic/b6_ch4_class7_b1146.jpg" alt="Powered by Struts"/>
    <% } else if (id == 1147) { %>
        <html:img page="/pic/b6_ch4_4_qb_9.png" alt="Powered by Struts"/>
    <% } else if (id == 1148) { %>
        <html:img page="/pic/b6_ch4_4_b1148.png" alt="Powered by Struts"/>
    <% } else if (id == 1149) { %>
        <html:img page="/pic/b6_ch4_5_qb_11.png" alt="Powered by Struts"/>
    <% } else if (id == 1150) { %>
        <html:img page="/pic/b6_ch4_5_b1150.png" alt="Powered by Struts"/>
    <% } else if (id == 1154) { %>
        <html:img page="/pic/b6_ch4_7_b1154.png" alt="Powered by Struts"/>
    <% } else if (id == 1157) { %>
        <html:img page="/pic/b6_ch4_class8_b1157.png" alt="Powered by Struts"/>
    <% } else if (id == 1159) { %>
        <html:img page="/pic/b6_ch4_class8_b1159.png" alt="Powered by Struts"/>
    <% } else if (id == 1178) { %>
        <html:img page="/pic/b6_ch4_11_b1178.png" alt="Powered by Struts"/>
    <% } else if (id == 1227) { %>
        <html:img page="/pic/b6_ch4__2_b_1227.png" alt="Powered by Struts"/>
    <% } else if (id == 1229) { %>
        <html:img page="/pic/b6_ch4__2_b_1229.png" alt="Powered by Struts"/>
    <% } else if (id == 1230) { %>
        <html:img page="/pic/b6_ch4__2_b_1230.png" alt="Powered by Struts"/>
    <% } else if (id == 1233) { %>
        <html:img page="/pic/b6_ch4__2_b_1229e.png" alt="Powered by Struts"/>
    <% } else if (id == 1246) { %>
        <html:img page="/pic/b6_mid_1_b_1246.png" alt="Powered by Struts"/>
    <% } else if (id == 1252) { %>
        <html:img page="/pic/b6_mid_2_b_1252.png" alt="Powered by Struts"/>
    <% } else if (id == 1261) { %>
        <html:img page="/pic/b6_mid_2_b_1261.png" alt="Powered by Struts"/>
    <% } else if (id == 1269) { %>
        <html:img page="/pic/b6_ch3_b_1269.png" alt="Powered by Struts"/>
    <% } else if (id == 1270) { %>
        <html:img page="/pic/b6_ch3_b_1270.png" alt="Powered by Struts"/>
    <% } else if (id == 1271) { %>
        <html:img page="/pic/b6_ch3_b_1271.png" alt="Powered by Struts"/>
    <% } else if (id == 1273) { %>
        <html:img page="/pic/b6_ch3_b_1273.png" alt="Powered by Struts"/>
    <% } else if (id == 1289) { %>
        <html:img page="/pic/b6_ch1_b_1289.png" alt="Powered by Struts"/>
    <% } else if (id == 1308) { %>
        <html:img page="/pic/b6_ch5_class1_b_1308.png" alt="Powered by Struts"/>
    <% } else if (id == 1309) { %>
        <html:img page="/pic/b6_ch5_class1_b_1309.png" alt="Powered by Struts"/>
    <% } else if (id == 1344) { %>
        <html:img page="/pic/b6_ch6_1_b_1344.png" alt="Powered by Struts"/>
    <% } else if (id == 1345) { %>
        <html:img page="/pic/b6_ch6_1_b_1345.png" alt="Powered by Struts"/>
    <% } else if (id == 1346) { %>
        <html:img page="/pic/b6_ch6_1_b_1346.png" alt="Powered by Struts"/>
    <% } else if (id == 1347) { %>
        <html:img page="/pic/b6_ch6_1_b_1347.png" alt="Powered by Struts"/>
    <% } else if (id == 1381) { %>
        <html:img page="/pic/b6_ch1_class2_b_1381.png" alt="Powered by Struts"/>
    <% } else if (id == 1382) { %>
        <html:img page="/pic/b6_ch1_class2_db_1062.png" alt="Powered by Struts"/>
    <% } else if (id == 1384) { %>
        <html:img page="/pic/b6_ch1_2_b_1384.png" alt="Powered by Struts"/>
    <% } else if (id == 1385) { %>
        <html:img page="/pic/b6_ch1_2_b_1385.png" alt="Powered by Struts"/>
    <% } else if (id == 1388) { %>
        <html:img page="/pic/b6_ch1_self_b_1388.png" alt="Powered by Struts"/>
    <% } else if (id == 1390) { %>
        <html:img page="/pic/b6_ch1_self_b_1390.png" alt="Powered by Struts"/><html:img page="/pic/b6_ch1_self_b_1390_2.png" alt="Powered by Struts"/>
    <% } else if (id == 1391) { %>
        <html:img page="/pic/b6_ch1_self_b_1391.png" alt="Powered by Struts"/>
    <% } else if (id == 1416) { %>
       <html:img page="/pic/b6_ch2_4_b_1416.png" alt="Powered by Struts"/>
    <% } else if (id == 1449) { %>
       <html:img page="/pic/b6_ch3_3_b_1449.png" alt="Powered by Struts"/>
    <% } else if (id == 1450) { %>
       <html:img page="/pic/b6_ch3_3_b_1450.png" alt="Powered by Struts"/>
    <% } else if (id == 1457) { %>
       <html:img page="/pic/b6_ch3_4_b_1457.png" alt="Powered by Struts"/>
    <% } else if (id == 1458) { %>
       <html:img page="/pic/b6_ch3_4_b_1458.png" alt="Powered by Struts"/>
    <% } else if (id == 1461) { %>
       <html:img page="/pic/b6_ch3_5_b_1461.png" alt="Powered by Struts"/>
    <% } else if (id == 1462) { %>
       <html:img page="/pic/b6_ch3_5_b_1462.png" alt="Powered by Struts"/>
    <% } else if (id == 1463) { %>
       <html:img page="/pic/b6_ch3_5_b_1463.png" alt="Powered by Struts"/>
    <% } else if (id == 1465) { %>
       <html:img page="/pic/b6_ch3_5_b_1465.png" alt="Powered by Struts"/>
    <% } else if (id == 1466) { %>
       <html:img page="/pic/b6_ch3_6_b_1466.png" alt="Powered by Struts"/>
    <% } else if (id == 1467) { %>
       <html:img page="/pic/b6_ch3_6_qb_50.png" alt="Powered by Struts"/>
    <% } else if (id == 1468) { %>
       <html:img page="/pic/b6_ch3_6_b_1468.png" alt="Powered by Struts"/>
    <% } else if (id == 1469) { %>
       <html:img page="/pic/b6_ch3_6_b_1469.png" alt="Powered by Struts"/>
    <% } else if (id == 1470) { %>
       <html:img page="/pic/b6_ch3_7_b_1470.png" alt="Powered by Struts"/>
    <% } else if (id == 1471) { %>
       <html:img page="/pic/b6_ch3_7_b_1471.png" alt="Powered by Struts"/>
    <% } else if (id == 1474) { %>
       <html:img page="/pic/b6_ch3_self_b_1474.png" alt="Powered by Struts"/>
    <% } else if (id == 1477) { %>
       <html:img page="/pic/b6_ch3_self_b_1477.png" alt="Powered by Struts"/>
    <% } else if (id == 1478) { %>
       <html:img page="/pic/b6_ch3_self_b_1478.png" alt="Powered by Struts"/>
    <% } else if (id == 1480) { %>
       <html:img page="/pic/b6_ch3_self_b_1480.png" alt="Powered by Struts"/>
    <% } else if (id == 1481) { %>
       <html:img page="/pic/b6_ch3_self_b_1481.png" alt="Powered by Struts"/>
    <% } else if (id == 1482) { %>
       <html:img page="/pic/b6_ch3_class1_b_1482.png" alt="Powered by Struts"/>
    <% } else if (id == 1483) { %>
       <html:img page="/pic/b6_ch3_class2_b_1483.png" alt="Powered by Struts"/>
    <% } else if (id == 1484) { %>
       <html:img page="/pic/b6_ch3_class2_b_1484.png" alt="Powered by Struts"/>
    <% } else if (id == 1485) { %>
       <html:img page="/pic/b6_ch3_class2_b_1485.png" alt="Powered by Struts"/>
    <% } else if (id == 1489) { %>
       <html:img page="/pic/b6_ch3_class2_b_1489.png" alt="Powered by Struts"/>
    <% } else if (id == 1491) { %>
       <html:img page="/pic/b6_ch3_class3_b_1491.png" alt="Powered by Struts"/>
    <% } else if (id == 1492) { %>
       <html:img page="/pic/b6_ch6_hw3_b_1492.png" alt="Powered by Struts"/>
    <% } else if (id == 1493) { %>
       <html:img page="/pic/b6_ch6_hw3_b_1493.png" alt="Powered by Struts"/>
    <% } else if (id == 1494) { %>
       <html:img page="/pic/b6_ch3_class4_b_1494.png" alt="Powered by Struts"/>
    <% } else if (id == 1497) { %>
       <html:img page="/pic/b6_ch3_class5_b_1497.png" alt="Powered by Struts"/>
    <% } else if (id == 1498) { %>
       <html:img page="/pic/b6_ch3_class5_b_1498.png" alt="Powered by Struts"/>
    <% } else if (id == 1499) { %>
       <html:img page="/pic/b6_ch3_class5_b_1499.png" alt="Powered by Struts"/>
    <% } else if (id == 1500) { %>
       <html:img page="/pic/b6_ch3_class5_b_1500.png" alt="Powered by Struts"/>
    <% } else if (id == 1501) { %>
       <html:img page="/pic/b6_ch3_class6_b_1501.png" alt="Powered by Struts"/>
    <% } else if (id == 1508) { %>
       <html:img page="/pic/b6_ch6_4_b_1508.png" alt="Powered by Struts"/>
    <% } else if (id == 1530) { %>
       <html:img page="/pic/b6_ch6_6_b_1530.png" alt="Powered by Struts"/>
    <% } else if (id == 1531) { %>
       <html:img page="/pic/b6_ch6_6_b_1531.png" alt="Powered by Struts"/>
    <% } else if (id == 1537) { %>
       <html:img page="/pic/b6_ch6_7_b_1537.png" alt="Powered by Struts"/>
    <% } else if (id == 1540) { %>
       <html:img page="/pic/b6_ch6_7_b_1540.png" alt="Powered by Struts"/>
    <% } else if (id == 1551) { %>
       <html:img page="/pic/b6_ch6_10_b_1551.png" alt="Powered by Struts"/>
    <% } else if (id == 1555) { %>
       <html:img page="/pic/b6_ch6_hw5_b_1555.png" alt="Powered by Struts"/>
    <% } else if (id == 1556) { %>
       <html:img page="/pic/b6_ch6_hw6_b_1556.png" alt="Powered by Struts"/>
    <% } else if (id == 1564) { %>
       <html:img page="/pic/b6_ch6_12_b_1564.png" alt="Powered by Struts"/>
    <% } else if (id == 1573) { %>
       <html:img page="/pic/b6_final_self_b_1573.png" alt="Powered by Struts"/>
    <% } else if (id == 1576) { %>
       <html:img page="/pic/b6_final_self_b_1576.png" alt="Powered by Struts"/>
    <% } else if (id == 1580) { %>
       <html:img page="/pic/b6_ch6_class7_b_1580_qb_99.png" alt="Powered by Struts"/>
    <% } else if (id == 1581) { %>
       <html:img page="/pic/b6_ch6_class7_b_1581_1.png" alt="Powered by Struts"/><html:img page="/pic/b6_ch6_class7_b_1581_2.png" alt="Powered by Struts"/>
    <% } else if (id == 1615) { %>
       <html:img page="/pic/b6_final_1_tb_223_qb_112.png" alt="Powered by Struts"/>
    <% } else if (id == 1635) { %>
       <html:img page="/pic/b6_final_com1_b_1635.png" alt="Powered by Struts"/>
    <% } else if (id == 1638) { %>
       <html:img page="/pic/b6_final_com1_b_1638.png" alt="Powered by Struts"/>
    <% } else if (id == 1650) { %>
       <html:img page="/pic/b6_final_3_b_1650.png" alt="Powered by Struts"/>
    <% } else if (id == 1651) { %>
       <html:img page="/pic/b6_final_3_b_1651.png" alt="Powered by Struts"/>
    <% } else if (id == 1696) { %>
       <html:img page="/pic/b6_final_com2_b_1696.png" alt="Powered by Struts"/>
    <% } else if (id == 1704) { %>
       <html:img page="/pic/b6_final_5_b_1704.png" alt="Powered by Struts"/>
    <% } else if (id == 1715) { %>
       <html:img page="/pic/b6_final_class4_b_1715.png" alt="Powered by Struts"/>
    <% } else if (id == 1727) { %>
       <html:img page="/pic/b6_final_6_b_1727.png" alt="Powered by Struts"/>
    <% } else if (id == 1729) { %>
       <html:img page="/pic/b6_final_6_b_1729.png" alt="Powered by Struts"/>
    <% } else if (id == 1732) { %>
       <html:img page="/pic/b6_final_class5_b_1732.png" alt="Powered by Struts"/>
    <% } else if (id == 1733) { %>
       <html:img page="/pic/b6_final_class5_b_1733.png" alt="Powered by Struts"/>
    <% } else if (id == 1744) { %>
       <html:img page="/pic/b6_final_class5_b_1744.png" alt="Powered by Struts"/>
    <% } else if (id == 1745) { %>
       <html:img page="/pic/b6_final_class5_b_1745.png" alt="Powered by Struts"/>
    <% } else if (id == 1746) { %>
       <html:img page="/pic/b6_final_class5_b_1746.png" alt="Powered by Struts"/>
    <% } else if (id == 1747) { %>
       <html:img page="/pic/b6_final_class5_b_1747.png" alt="Powered by Struts"/>
    <% } else if (id == 1748) { %>
       <html:img page="/pic/b6_final_class5_b_1748.png" alt="Powered by Struts"/>
    <% } else if (id == 1749) { %>
       <html:img page="/pic/b6_final_class5_b_1749.png" alt="Powered by Struts"/>
    <% } else if (id == 1750) { %>
       <html:img page="/pic/b6_final_class5_b_1750.png" alt="Powered by Struts"/>
    <% } else if (id == 1770) { %>
       <html:img page="/pic/b6_final_com3_b_1770_qb_149.png" alt="Powered by Struts"/>
    <% } else if (id == 1772) { %>
       <html:img page="/pic/b6_final_com3_b_1772.png" alt="Powered by Struts"/>
    <% } else if (id == 1775) { %>
       <html:img page="/pic/b6_final_com3_b_1775.png" alt="Powered by Struts"/>
    <% } else if (id == 1777) { %>
       <html:img page="/pic/b6_final_com3_b_1777.png" alt="Powered by Struts"/>
    <% } else if (id == 1783) { %>
       <html:img page="/pic/b6_final_class6_b_1783.png" alt="Powered by Struts"/>
    <% } else if (id == 1790) { %>
       <html:img page="/pic/b6_final_class6_b_1790.png" alt="Powered by Struts"/>
    <% } else if (id == 1793) { %>
       <html:img page="/pic/b6_final_class6_b_1793.png" alt="Powered by Struts"/>
    <% } else if (id == 1796) { %>
       <html:img page="/pic/b6_final_class7_b_1796.png" alt="Powered by Struts"/>
    <% } else if (id == 1797) { %>
       <html:img page="/pic/b6_final_class7_b_1797.png" alt="Powered by Struts"/>
    <% } else if (id == 1799) { %>
       <html:img page="/pic/b6_final_class7_b_1799_qb_156_157.png" alt="Powered by Struts"/>
    <% } else if (id == 1800) { %>
       <html:img page="/pic/b6_final_class7_b_1800.png" alt="Powered by Struts"/>
    <% } else if (id == 1801) { %>
       <html:img page="/pic/b6_final_class7_b_1801_tb_282_qb_153_1.png" alt="Powered by Struts"/>
    <% } else if (id == 1803) { %>
       <html:img page="/pic/b6_final_com4_b_1803.png" alt="Powered by Struts"/>
    <% } else if (id == 1808) { %>
       <html:img page="/pic/b6_final_com4_b_1808.png" alt="Powered by Struts"/>
    <% } else if (id == 1822) { %>
       <html:img page="/pic/b6_final_com5_b_1822.png" alt="Powered by Struts"/>
    <% } else if (id == 1831) { %>
       <html:img page="/pic/b6_final_com5_b_1831.png" alt="Powered by Struts"/>
    <% } else if (id == 1837) { %>
       <html:img page="/pic/b6_final_com5_b_1837.png" alt="Powered by Struts"/>
    <% } else if (id == 1847) { %>
       <html:img page="/pic/b6_final_simu_b_1847.png" alt="Powered by Struts"/>
    <% } else if (id == 1848) { %>
       <html:img page="/pic/b6_final_simu_b_1848.png" alt="Powered by Struts"/>
    <% } else if (id == 1860) { %>
       <html:img page="/pic/b6_final_simu2_b_1860.png" alt="Powered by Struts"/>
    <% } else if (id == 1861) { %>
       <html:img page="/pic/b6_final_simu2_b_1861.png" alt="Powered by Struts"/>
    <% } else if (id == 1867) { %>
       <html:img page="/pic/b6_final_simu2_b_1867.png" alt="Powered by Struts"/>
    <% } else if (id == 1876) { %>
       <html:img page="/pic/b6_final_2014_spring_b_1876.png" alt="Powered by Struts"/>
    <% } else if (id == 1877) { %>
       <html:img page="/pic/b6_final_2014_spring_b_1877.png" alt="Powered by Struts"/>
    <% } else if (id == 1878) { %>
       <html:img page="/pic/b6_final_2014_spring_b_1878.png" alt="Powered by Struts"/>
    <% } else if (id == 1887) { %>
       <html:img page="/pic/b6_final_2014_autumn_b_1887.png" alt="Powered by Struts"/>
    <% } else if (id == 1918) { %>
       <html:img page="/pic/b6_final_simu5_b_1918.png" alt="Powered by Struts"/>
    <% } else if (id == 1924) { %>
       <html:img page="/pic/b6_final_simu5_b_1924.png" alt="Powered by Struts"/>
    <% } else if (id == 1935) { %>
       <html:img page="/pic/b6_final_simu6_b_1935.png" alt="Powered by Struts"/>
    <% } else if (id == 1938) { %>
       <html:img page="/pic/b6_final_simu6_b_1938.png" alt="Powered by Struts"/>
    <% } else if (id == 1951) { %>
       <html:img page="/pic/b6_final_real_b_1951.png" alt="Powered by Struts"/>
    <% } else if (id == 1955) { %>
       <html:img page="/pic/b6_final_real_b_1955_qb_213.png" alt="Powered by Struts"/>
    <% } %>


   <% if (id==1147){ %>
       <CENTER><H3><a href="javascript:window.open('AppletExample/DrawLineApplet64.html')"><bean:message key="hello.jsp.page.draw"/></a></H3></CENTER><P>
    <% } else if (id==1158){ %>
       <CENTER><H3><a href="javascript:window.open('AppletExample/DrawLineApplet65.html')"><bean:message key="hello.jsp.page.draw"/></a></H3></CENTER><P>
    <% } else if (id==1160){ %>
       <CENTER><H3><a href="javascript:window.open('AppletExample/DrawLineApplet66.html')"><bean:message key="hello.jsp.page.draw"/></a></H3></CENTER><P>
    <% } else if (id==1161){ %>
       <CENTER><H3><a href="javascript:window.open('AppletExample/DrawLineApplet67.html')"><bean:message key="hello.jsp.page.draw"/></a></H3></CENTER><P>
    <% } else if (id==1162){ %>
       <CENTER><H3><a href="javascript:window.open('AppletExample/DrawLineApplet68.html')"><bean:message key="hello.jsp.page.draw"/></a></H3></CENTER><P>
    <% } else if (id==1163){ %>
       <CENTER><H3><a href="javascript:window.open('AppletExample/DrawLineApplet69.html')"><bean:message key="hello.jsp.page.draw"/></a></H3></CENTER><P>
    <% } else if (id==1171){ %>
       <CENTER><H3><a href="javascript:window.open('AppletExample/DrawLineApplet70.html')"><bean:message key="hello.jsp.page.draw"/></a></H3></CENTER><P>
    <% } else if (id==1172){ %>
       <CENTER><H3><a href="javascript:window.open('AppletExample/DrawLineApplet71.html')"><bean:message key="hello.jsp.page.draw"/></a></H3></CENTER><P>
    <% } else if (id==1211){ %>
       <CENTER><H3><a href="javascript:window.open('AppletExample/DrawLineApplet72.html')"><bean:message key="hello.jsp.page.draw"/></a></H3></CENTER><P>
    <% } else if (id==1212){ %>
       <CENTER><H3><a href="javascript:window.open('AppletExample/DrawLineApplet73.html')"><bean:message key="hello.jsp.page.draw"/></a></H3></CENTER><P>
    <% } else if (id==1213){ %>
       <CENTER><H3><a href="javascript:window.open('AppletExample/DrawLineApplet74.html')"><bean:message key="hello.jsp.page.draw"/></a></H3></CENTER><P>
    <% } else if (id==1231){ %>
       <CENTER><H3><a href="javascript:window.open('AppletExample/DrawLineApplet75.html')"><bean:message key="hello.jsp.page.draw"/></a></H3></CENTER><P>
    <% } else if (id==1232){ %>
       <CENTER><H3><a href="javascript:window.open('AppletExample/DrawLineApplet76.html')"><bean:message key="hello.jsp.page.draw"/></a></H3></CENTER><P>
    <% } else if (id==1233){ %>
       <CENTER><H3><a href="javascript:window.open('AppletExample/DrawLineApplet77.html')"><bean:message key="hello.jsp.page.draw"/></a></H3></CENTER><P>
    <% } else if (id==1262){ %>
       <CENTER><H3><a href="javascript:window.open('AppletExample/DrawLineApplet78.html')"><bean:message key="hello.jsp.page.draw"/></a></H3></CENTER><P>
    <% } else if (id==1276){ %>
       <CENTER><H3><a href="javascript:window.open('AppletExample/DrawLineApplet79.html')"><bean:message key="hello.jsp.page.draw"/></a></H3></CENTER><P>
    <% } else if (id==1277){ %>
       <CENTER><H3><a href="javascript:window.open('AppletExample/DrawLineApplet80.html')"><bean:message key="hello.jsp.page.draw"/></a></H3></CENTER><P>
    <% } else if (id==1291){ %>
       <CENTER><H3><a href="javascript:window.open('AppletExample/DrawLineApplet81.html')"><bean:message key="hello.jsp.page.draw"/></a></H3></CENTER><P>
    <% } else if (id==1292){ %>
       <CENTER><H3><a href="javascript:window.open('AppletExample/DrawLineApplet82.html')"><bean:message key="hello.jsp.page.draw"/></a></H3></CENTER><P>
    <% } else if (id==1349){ %>
       <CENTER><H3><a href="javascript:window.open('AppletExample/DrawLineApplet83.html')"><bean:message key="hello.jsp.page.draw"/></a></H3></CENTER><P>
    <% } else if (id==1350){ %>
       <CENTER><H3><a href="javascript:window.open('AppletExample/DrawLineApplet84.html')"><bean:message key="hello.jsp.page.draw"/></a></H3></CENTER><P>
    <% } else if (id==1351){ %>
       <CENTER><H3><a href="javascript:window.open('AppletExample/DrawLineApplet85.html')"><bean:message key="hello.jsp.page.draw"/></a></H3></CENTER><P>
    <% } else if (id==1382){ %>
       <CENTER><H3><a href="javascript:window.open('AppletExample/DrawLineApplet86.html')"><bean:message key="hello.jsp.page.draw"/></a></H3></CENTER><P>
    <% } else if (id==1386){ %>
       <CENTER><H3><a href="javascript:window.open('AppletExample/DrawLineApplet87.html')"><bean:message key="hello.jsp.page.draw"/></a></H3></CENTER><P>
    <% } else if (id==1387){ %>
       <CENTER><H3><a href="javascript:window.open('AppletExample/DrawLineApplet88.html')"><bean:message key="hello.jsp.page.draw"/></a></H3></CENTER><P>
    <% } else if (id==1392){ %>
       <CENTER><H3><a href="javascript:window.open('AppletExample/DrawLineApplet89.html')"><bean:message key="hello.jsp.page.draw"/></a></H3></CENTER><P>
    <% } else if (id==1393){ %>
       <CENTER><H3><a href="javascript:window.open('AppletExample/DrawLineApplet90.html')"><bean:message key="hello.jsp.page.draw"/></a></H3></CENTER><P>
    <% } else if (id==1518){ %>
       <CENTER><H3><a href="javascript:window.open('AppletExample/DrawLineApplet91.html')"><bean:message key="hello.jsp.page.draw"/></a></H3></CENTER><P>
    <% } else if (id==1519){ %>
       <CENTER><H3><a href="javascript:window.open('AppletExample/DrawLineApplet92.html')"><bean:message key="hello.jsp.page.draw"/></a></H3></CENTER><P>
    <% } else if (id==1520){ %>
       <CENTER><H3><a href="javascript:window.open('AppletExample/DrawLineApplet93.html')"><bean:message key="hello.jsp.page.draw"/></a></H3></CENTER><P>
    <% } else if (id==1532){ %>
       <CENTER><H3><a href="javascript:window.open('AppletExample/DrawLineApplet94.html')"><bean:message key="hello.jsp.page.draw"/></a></H3></CENTER><P>
    <% } else if (id==1533){ %>
       <CENTER><H3><a href="javascript:window.open('AppletExample/DrawLineApplet95.html')"><bean:message key="hello.jsp.page.draw"/></a></H3></CENTER><P>
    <% } else if (id==1541){ %>
       <CENTER><H3><a href="javascript:window.open('AppletExample/DrawLineApplet96.html')"><bean:message key="hello.jsp.page.draw"/></a></H3></CENTER><P>
    <% } else if (id==1542){ %>
       <CENTER><H3><a href="javascript:window.open('AppletExample/DrawLineApplet97.html')"><bean:message key="hello.jsp.page.draw"/></a></H3></CENTER><P>
    <% } else if (id==1543){ %>
       <CENTER><H3><a href="javascript:window.open('AppletExample/DrawLineApplet98.html')"><bean:message key="hello.jsp.page.draw"/></a></H3></CENTER><P>
    <% } else if (id==1544){ %>
       <CENTER><H3><a href="javascript:window.open('AppletExample/DrawLineApplet99.html')"><bean:message key="hello.jsp.page.draw"/></a></H3></CENTER><P>
    <% } else if (id==1545){ %>
       <CENTER><H3><a href="javascript:window.open('AppletExample/DrawLineApplet100.html')"><bean:message key="hello.jsp.page.draw"/></a></H3></CENTER><P>
    <% } else if (id==1546){ %>
       <CENTER><H3><a href="javascript:window.open('AppletExample/DrawLineApplet101.html')"><bean:message key="hello.jsp.page.draw"/></a></H3></CENTER><P>
    <% } else if (id==1547){ %>
       <CENTER><H3><a href="javascript:window.open('AppletExample/DrawLineApplet102.html')"><bean:message key="hello.jsp.page.draw"/></a></H3></CENTER><P>
    <% } else if (id==1552){ %>
       <CENTER><H3><a href="javascript:window.open('AppletExample/DrawLineApplet103.html')"><bean:message key="hello.jsp.page.draw"/></a></H3></CENTER><P>
    <% } else if (id==1577){ %>
       <CENTER><H3><a href="javascript:window.open('AppletExample/DrawLineApplet104.html')"><bean:message key="hello.jsp.page.draw"/></a></H3></CENTER><P>
    <% } else if (id==1578){ %>
       <CENTER><H3><a href="javascript:window.open('AppletExample/DrawLineApplet105.html')"><bean:message key="hello.jsp.page.draw"/></a></H3></CENTER><P>
    <% } else if (id==1579){ %>
       <CENTER><H3><a href="javascript:window.open('AppletExample/DrawLineApplet106.html')"><bean:message key="hello.jsp.page.draw"/></a></H3></CENTER><P>
    <% } else if (id==1615){ %>
       <CENTER><H3><a href="javascript:window.open('AppletExample/DrawLineApplet107.html')"><bean:message key="hello.jsp.page.draw"/></a></H3></CENTER><P>
    <% } else if (id==1627){ %>
       <CENTER><H3><a href="javascript:window.open('AppletExample/DrawLineApplet108.html')"><bean:message key="hello.jsp.page.draw"/></a></H3></CENTER><P>
    <% } else if (id==1641){ %>
       <CENTER><H3><a href="javascript:window.open('AppletExample/DrawLineApplet109.html')"><bean:message key="hello.jsp.page.draw"/></a></H3></CENTER><P>
    <% } else if (id==1655){ %>
       <CENTER><H3><a href="javascript:window.open('AppletExample/DrawLineApplet110.html')"><bean:message key="hello.jsp.page.draw"/></a></H3></CENTER><P>
    <% } else if (id==1675){ %>
       <CENTER><H3><a href="javascript:window.open('AppletExample/DrawLineApplet111.html')"><bean:message key="hello.jsp.page.draw"/></a></H3></CENTER><P>
    <% } else if (id==1676){ %>
       <CENTER><H3><a href="javascript:window.open('AppletExample/DrawLineApplet112.html')"><bean:message key="hello.jsp.page.draw"/></a></H3></CENTER><P>
    <% } else if (id==1677){ %>
       <CENTER><H3><a href="javascript:window.open('AppletExample/DrawLineApplet113.html')"><bean:message key="hello.jsp.page.draw"/></a></H3></CENTER><P>
    <% } else if (id==1678){ %>
       <CENTER><H3><a href="javascript:window.open('AppletExample/DrawLineApplet114.html')"><bean:message key="hello.jsp.page.draw"/></a></H3></CENTER><P>
    <% } else if (id==1703){ %>
       <CENTER><H3><a href="javascript:window.open('AppletExample/DrawLineApplet115.html')"><bean:message key="hello.jsp.page.draw"/></a></H3></CENTER><P>
    <% } else if (id==1725){ %>
       <CENTER><H3><a href="javascript:window.open('AppletExample/DrawLineApplet116.html')"><bean:message key="hello.jsp.page.draw"/></a></H3></CENTER><P>
    <% } else if (id==1726){ %>
       <CENTER><H3><a href="javascript:window.open('AppletExample/DrawLineApplet117.html')"><bean:message key="hello.jsp.page.draw"/></a></H3></CENTER><P>
    <% } else if (id==1755){ %>
       <CENTER><H3><a href="javascript:window.open('AppletExample/DrawLineApplet92.html')"><bean:message key="hello.jsp.page.draw"/></a></H3></CENTER><P>
    <% } else if (id==1756){ %>
       <CENTER><H3><a href="javascript:window.open('AppletExample/DrawLineApplet93.html')"><bean:message key="hello.jsp.page.draw"/></a></H3></CENTER><P>
    <% } else if (id==1757){ %>
       <CENTER><H3><a href="javascript:window.open('AppletExample/DrawLineApplet20.html')"><bean:message key="hello.jsp.page.draw"/></a></H3></CENTER><P>
    <% } else if (id==1779){ %>
       <CENTER><H3><a href="javascript:window.open('AppletExample/DrawLineApplet118.html')"><bean:message key="hello.jsp.page.draw"/></a></H3></CENTER><P>
    <% } else if (id==1780){ %>
       <CENTER><H3><a href="javascript:window.open('AppletExample/DrawLineApplet119.html')"><bean:message key="hello.jsp.page.draw"/></a></H3></CENTER><P>
    <% } else if (id==1794){ %>
       <CENTER><H3><a href="javascript:window.open('AppletExample/DrawLineApplet120.html')"><bean:message key="hello.jsp.page.draw"/></a></H3></CENTER><P>
    <% } else if (id==1795){ %>
       <CENTER><H3><a href="javascript:window.open('AppletExample/DrawLineApplet121.html')"><bean:message key="hello.jsp.page.draw"/></a></H3></CENTER><P>
    <% } else if (id==1801){ %>
       <CENTER><H3><a href="javascript:window.open('AppletExample/ChartApplet30.html')"><bean:message key="hello.jsp.page.draw"/></a></H3></CENTER><P>
    <% } else if (id==1838){ %>
       <CENTER><H3><a href="javascript:window.open('AppletExample/DrawLineApplet122.html')"><bean:message key="hello.jsp.page.draw"/></a></H3></CENTER><P>
    <% } else if (id==1851){ %>
       <CENTER><H3><a href="javascript:window.open('AppletExample/DrawLineApplet123.html')"><bean:message key="hello.jsp.page.draw"/></a></H3></CENTER><P>
    <% } else if (id==1852){ %>
       <CENTER><H3><a href="javascript:window.open('AppletExample/DrawLineApplet124.html')"><bean:message key="hello.jsp.page.draw"/></a></H3></CENTER><P>
    <% } else if (id==1853){ %>
       <CENTER><H3><a href="javascript:window.open('AppletExample/DrawLineApplet125.html')"><bean:message key="hello.jsp.page.draw"/></a></H3></CENTER><P>
    <% } else if (id==1868){ %>
       <CENTER><H3><a href="javascript:window.open('AppletExample/DrawLineApplet126.html')"><bean:message key="hello.jsp.page.draw"/></a></H3></CENTER><P>
    <% } else if (id==1869){ %>
       <CENTER><H3><a href="javascript:window.open('AppletExample/DrawLineApplet127.html')"><bean:message key="hello.jsp.page.draw"/></a></H3></CENTER><P>
    <% } else if (id==1883){ %>
       <CENTER><H3><a href="javascript:window.open('AppletExample/DrawLineApplet128.html')"><bean:message key="hello.jsp.page.draw"/></a></H3></CENTER><P>
    <% } else if (id==1890){ %>
       <CENTER><H3><a href="javascript:window.open('AppletExample/DrawLineApplet129.html')"><bean:message key="hello.jsp.page.draw"/></a></H3></CENTER><P>
    <% } else if (id==1891){ %>
       <CENTER><H3><a href="javascript:window.open('AppletExample/DrawLineApplet130.html')"><bean:message key="hello.jsp.page.draw"/></a></H3></CENTER><P>
    <% } else if (id==1904 || id==1917){ %>
       <CENTER><H3><a href="javascript:window.open('AppletExample/DrawLineApplet131.html')"><bean:message key="hello.jsp.page.draw"/></a></H3></CENTER><P>
    <% } else if (id==1928){ %>
       <CENTER><H3><a href="javascript:window.open('AppletExample/DrawLineApplet11.html')"><bean:message key="hello.jsp.page.draw"/></a></H3></CENTER><P>
    <% } else if (id==1929){ %>
       <CENTER><H3><a href="javascript:window.open('AppletExample/DrawLineApplet132.html')"><bean:message key="hello.jsp.page.draw"/></a></H3></CENTER><P>
    <% } else if (id==1939){ %>
       <CENTER><H3><a href="javascript:window.open('AppletExample/DrawLineApplet133.html')"><bean:message key="hello.jsp.page.draw"/></a></H3></CENTER><P>
    <% } else if (id==1940){ %>
       <CENTER><H3><a href="javascript:window.open('AppletExample/DrawLineApplet134.html')"><bean:message key="hello.jsp.page.draw"/></a></H3></CENTER><P>
    <% } else if (id==1941){ %>
       <CENTER><H3><a href="javascript:window.open('AppletExample/DrawLineApplet113.html')"><bean:message key="hello.jsp.page.draw"/></a></H3></CENTER><P>
    <% } else if (id==1942){ %>
       <CENTER><H3><a href="javascript:window.open('AppletExample/ChartApplet31.html')"><bean:message key="hello.jsp.page.draw"/></a></H3></CENTER><P>
    <% } else if (id==1956){ %>
       <CENTER><H3><a href="javascript:window.open('AppletExample/DrawLineApplet135.html')"><bean:message key="hello.jsp.page.draw"/></a></H3></CENTER><P>
    <% } else if (id==1957){ %>
       <CENTER><H3><a href="javascript:window.open('AppletExample/DrawLineApplet136.html')"><bean:message key="hello.jsp.page.draw"/></a></H3></CENTER><P>
    <% } %>

    <% remainSeconds = _pb2.getRemainSeconds();
       if (remainSeconds > 0 && _pb2.getPlanStatus().equals("not finished")){ 
    %>

    <html:form action="/SmartRandomCheck.do" >

      <bean:message key="hello.jsp.prompt.fillblank"/>
      <html:text property="inputAnswer" size="16" maxlength="100"/><br>
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
      <html:hidden property="answeredHashSet_ftb" value="<%= _hs_ftb%>"/>
      <html:hidden property="answeredHashSet_fqb" value="<%= _hs_fqb%>"/>
      <html:hidden property="answered_MY_HashSet" value="<%= _hs_m%>"/>
      <html:hidden property="answeredHashSet_tm" value="<%= _hs_tm%>"/>
      <html:hidden property="answered_D_HashSet" value="<%= _hs_d%>"/>
      <html:hidden property="answeredHashSet_pm" value="<%= _hs_pm%>"/>
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
      <html:hidden property="classId" value="<%= _pb2.getClassId()%>"/>

      <html:submit property="submit" value="Submit"/>
      <html:reset/>

    </html:form><br>

    <h2><bean:message key="hello.jsp.prompt.skip"/><br></h2>
    <html:form action="/SmartRandomCheck2.do" >    
      
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
      <html:hidden property="answeredHashSet_ftb" value="<%= _hs_ftb%>"/>
      <html:hidden property="answeredHashSet_fqb" value="<%= _hs_fqb%>"/>
      <html:hidden property="answered_MY_HashSet" value="<%= _hs_m%>"/>
      <html:hidden property="answeredHashSet_tm" value="<%= _hs_tm%>"/>
      <html:hidden property="answered_D_HashSet" value="<%= _hs_d%>"/>
      <html:hidden property="answeredHashSet_pm" value="<%= _hs_pm%>"/>      
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
      <html:hidden property="classId" value="<%= _pb2.getClassId()%>"/>

      <html:submit property="submit" value="Skip this problem"/>
      <html:reset/>

    </html:form><br>

    <% } else { %>

      <center><h2><bean:message key="hello.jsp.prompt.finished"/></h2></center>
        <center>
      <html:form action="/Score2.do" >     
      <html:hidden property="answeredHashSet" value="<%= _hs%>"/>
      <html:hidden property="answeredHashSet_tf" value="<%= _hs_tf%>"/>
      <html:hidden property="answered_M_HashSet" value="<%= _mhs%>"/>
      <html:hidden property="answeredHashSet_fb" value="<%= _hs_fb%>"/>
      <html:hidden property="answeredHashSet_fdb" value="<%= _hs_fdb%>"/>
      <html:hidden property="answeredHashSet_ftb" value="<%= _hs_ftb%>"/>
      <html:hidden property="answeredHashSet_fqb" value="<%= _hs_fqb%>"/>
      <html:hidden property="answered_MY_HashSet" value="<%= _hs_m%>"/>
      <html:hidden property="answeredHashSet_tm" value="<%= _hs_tm%>"/>
      <html:hidden property="answered_D_HashSet" value="<%= _hs_d%>"/>
      <html:hidden property="answeredHashSet_pm" value="<%= _hs_pm%>"/>
      <html:hidden property="userName" value="<%= _pb2.getUserName()%>"/>
      <html:hidden property="passWord" value="<%= _pb2.getPassWord()%>"/>
      <html:hidden property="trueName" value="<%= _pb2.getTrueName()%>"/>
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
      <html:hidden property="classId" value="<%= _pb2.getClassId()%>"/>
  
      <html:submit property="submit" value="Submit"/>
      <html:reset/>

    </html:form><br>
    </center>

     
    <% } %>

    
    <html:img page="/struts-power.gif" alt="Powered by Struts"/>
   
  </body>
</html:html>
