<%@ page contentType="text/html; charset=utf-8" %>
<%@ page import="com.yettiesoft.vestsign.exception.VSException" %> 
<%@ page import="com.yettiesoft.vestsign.external.Enveloper" %> 
<%@ page import="com.yettiesoft.javarose.standard.cert.SGCertificate" %> 
<%@ page import="com.yettiesoft.javarose.util.SGUtils" %> 
<%@ page import="java.io.*" %>
<%@ page import="java.util.*" %>

<%

String envMsg = request.getParameter("envMsg");
byte[] pkFile = null;
pkFile = SGUtils.readFile("/home/post/VestSignServer/cert/srvcert/signPri.key");
String password = "qwer1234";
%>

<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="A layout example with a side menu that hides on mobile, just like the Pure website.">

    <title>VestSign BMT DEMO</title>

<link rel="stylesheet" href="./css/pure/pure.css">
    <!--[if lte IE 8]>
        <link rel="stylesheet" href="./css/layouts/side-menu-old-ie.css">
        <link rel="stylesheet" href="./css/pure/grids-responsive-old-ie.css">
    <![endif]-->
    <!--[if gt IE 8]><!-->
        <link rel="stylesheet" href="./css/layouts/side-menu.css">
        <link rel="stylesheet" href="./css/pure/grids-responsive.css">
    <!--<![endif]-->
</head>
<body>



<div id="layout">
    <!-- Menu toggle -->
    <a href="#menu" id="menuLink" class="menu-link">
        <!-- Hamburger icon -->
        <span></span>
    </a>


    <div id="main">
        <div class="header">
            <h1> 암호화 및 복호화 기능</h1>
            <h2>지정한 text를  사용자가 선택한 키로 암/복호화가 가능한지 여부 확인합니다.</h2>
            <p>클라이언트에서 전송된 암호화 된 text을 서버에서 복호화합니다.</p>
        </div>
	
        <div class="content">
        	<br>

			    <div class="pure-u-1 pure-u-md-1-2">
			    	<form class="pure-form">
				
					    <fieldset class="pure-group">
					    	<label for="암호화 스트링">암호화 스트링 </label>
					        <textarea class="pure-input-1" style="height:300px;" placeholder="암호화 스트링">전송된 암호화 스트링 : [<%=envMsg%>] </textarea>
					    </fieldset>
					
					    <!--button type="submit" class="pure-button pure-input-1 pure-button-primary">다운로드된 암호화 파일 경로</button-->
					</form>
			    </div>
			    <div class="pure-u-1 pure-u-md-1-2">
			    	<form class="pure-form">
				
					    <fieldset class="pure-group">
					    	<label for=" 복호화"> 복호화 결과</label>
					        <textarea class="pure-input-1" style="height:300px;" placeholder="복호화 결과">
<%
					        	     Enveloper env = new Enveloper();

									 String decResult ="";
									 /**
									 System.out.println(envMsg);
									 System.out.println(pkFile);
									 System.out.println(password);
									 **/
									 decResult = env.deEnvelope(envMsg, 0,"utf-8");
									 out.println("복호화 결과 = [" + decResult +"]");

									 if (env.getLastErrorCode() != 0){
											out.println("errorCode:"+env.getLastErrorCode()+"\nmsg:"+env.getLastErrorMsg());
									}
%>					        	
					        	</textarea>
					    </fieldset>
					
					    <!--button type="submit" class="pure-button pure-input-1 pure-button-primary">전자서명 검증</button-->
					</form>
			    </div>
        </div>
    </div>
</div>

<script src="./js/ui.js"></script>

</body>
</html>
