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
    相遇<br>
    http://localhost:8080/meetuAction!meetu?curr.userId=0&curr.longitude=50.000000&curr.latitude=10.000000&curr.address=广安门车站东街&curr.business=广安门商圈<br>
    http://localhost:8080/meetuAction!upload?curr.userId=0&curr.longitude=50.000000&curr.latitude=10.000000&curr.address=广安门车站东街&curr.business=广安门商圈<br>
    用户<br>
    http://localhost:8080/userAction!access?&user.mobile=15011448840&user.pwd=123123<br>
    http://localhost:8080/userAction!update?&user.id=1&user.mobile=15011448840&user.name=gaowen<br>
    好友<br>
    http://localhost:8080/friendAction!sendFriendReq?&req.reqUserId=1&req.reqFriendId=1&req.reqWay=QQ&req.reqFriendData=1053186456&req.reqMessage=loveISlove<br>
    反馈<br>
    http://localhost:8080/feedbackAction!feedback?feed.userId=1&feed.content=fuckUALL<br>
    硬件设备<br>
    http://localhost:8080/deviceAction!uploadDeviceInfo?device.userId=1&device.imei=111&device.osName=ANDROID&device.osVer=5.0&device.deviceCompany=GOOGLE<br>
  百度云推送信息<br>
    http://localhost:8080/pushAction!savePushInfo?push.imei=111&push.userId=1&push.userId_push=baidu1&push.channelId=3545744288033740498<br>
 文件下载<br>
 	http://localhost:8080/fileDownloadAction!download?userId=1&resolution=&fileName=USER_1.jpg<br>
  </body>
</html>
