<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html lang="zh-CN">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <meta name="description" content="">
    <meta name="author" content="">
    <!-- 
    <link rel="icon" href="../../favicon.ico">
 	-->
    <title>MeetU</title>

    <!-- Bootstrap core CSS -->
    <link href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="res/css/cover.css" rel="stylesheet">

    <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
    <!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
    <script src="res/js-lib/ie-emulation-modes-warning.js"></script>

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>

  <body>

    <div class="site-wrapper">

      <div class="site-wrapper-inner">

        <div class="cover-container">

          <div class="masthead clearfix">
            <div class="inner">
              <h3 class="masthead-brand">MeetU  <font color="black">don't pass the beauty</font></h3>
              <!-- navigator一级导航栏  -->
              <!-- href="#"的意义在于 让鼠标变成小手 -->
              <nav>
                <ul class="nav masthead-nav">
                  <li id="homeLi" class="active"><a href="#" onclick="onChange('home')">Home</a></li>
                  <li id="developersLi"><a href="#" onclick="onChange('developers')">Developers</a></li>
                  <li id="contactUsLi"><a href="#" onclick="onChange('contactUs')">Contact us</a></li>
                </ul>
              </nav>
              
            </div>
          </div>

          <div id="homeDiv" class="inner cover">
            <h1 class="cover-heading">MeetU</h1>
            <h3 class="cover-heading" style="color:black">Don't pass the beauty</h3>
            
            <p class="lead">
            	Every day may not be good…but there's something good in every day.
            </p>
            <p class="lead"> 
            	Create good memories today, so that you can have a good past
            </p>
            <p class="lead">
              <a href="index.jsp" class="btn btn-lg btn-default">Touch me</a>
            </p>
          </div>

          <div id="developersDiv" class="inner cover" style="display:none">
            <h1 class="cover-heading"><font size="7">Owners</font></h1>
            <br>
            <h1 class="cover-heading" style="color:black">chief android architect</h1>
            <p class="lead"> 
            	<font size="3">
					Walfud
				</font>
            </p>            
            <h1 class="cover-heading" style="color:black">chief system architect</h1>
            <p class="lead">
            	<font size="3">
            		DirkMurphy
            	</font>
            </p>
          </div>

          <div id="contactUsDiv" class="inner cover" style="display:none">
            <h1 class="cover-heading"><font size="7">contact us</font></h1>
            <br>
            <p class="lead"> 
            	<font size="3">
            		dirkmurphy@live.cn
				</font>
            </p> 
            <p class="lead"> 
            	<font size="3">
            		giuge@aliyun.com
				</font>
            </p>              

          </div>

          <div class="mastfoot">
            <div class="inner">
              <p>Cover template for <a href="http://getbootstrap.com">Bootstrap</a>, by <a href="https://twitter.com/mdo">@mdo</a>.</p>
            </div>
          </div>

        </div>

      </div>

    </div>

    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="res/js-lib/jquery/jquery-1.11.3.min.js"></script>
    <script src="//cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="res/js-lib/ie10-viewport-bug-workaround.js"></script>
  </body>
  <script type="text/javascript">
  	//定义navigator数组.以后要加的话在此维护一下
  	var navs = new Array();
	  	navs[0] = "home";
	  	navs[1] = "developers"; 
	  	navs[2] = "contactUs";
	  	/**
	  	navs["home"] = "home";
	  	navs["developers"] = "developers"; 
	  	navs["contactUs"] = "contactUs";
	  	*/

	/**
  	当切换nav时的操作/while clickNavigator
  	将当前li标签class设为active,其他标签的class设为""
  	将当前div的style.display设为"",其他div的style.display设为"none"
  	*/
  	function onChange(flag) {
		var currNavId = flag+"Li";
		var currBodyDivId = flag+"Div";
		//设置当前
		document.getElementById(currNavId).setAttribute("class","active");
		document.getElementById(currBodyDivId).style.display="";
		//设置其他
		for(var i=0 ; i< navs.length ; i++) {
			var currFlag = navs[i];
			if(currFlag != flag) {
				document.getElementById(currFlag+"Li").setAttribute("class","");
				document.getElementById(currFlag+"Div").style.display="none";
			} 
		}
  	}
  </script>
</html>
