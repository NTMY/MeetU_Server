<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>Meet U</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="meetu,meet u,meetyou,meet you">
	<meta http-equiv="description" content="index page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  <body>
    Meet U <br>
    http://localhost:8080/meetu/userAction!meetu?userId=0&longitude=50.000000&latitude=10.000000<br>
    http://localhost:8080/meetu/userAction!upload?curr.userId=0&curr.longitude=50.000000&curr.latitude=10.000000&curr.address=广安门车站东街&curr.business=广安门商圈<br>
    http://localhost:8080/meetu/userAction!access?&user.mobile=15011448840&user.pwd=123123
  </body>
</html>
