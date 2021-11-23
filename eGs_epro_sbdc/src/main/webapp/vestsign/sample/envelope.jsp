<%@ page contentType="text/html; charset=utf-8" %>
<%@ page buffer="16kb" %>
<%@ page import="java.util.Vector" %>
<%@ page import="com.yettiesoft.javarose.standard.cert.SGCertificate" %>
<%@ page import="com.yettiesoft.vestsign.external.*" %>
<%@ page import="com.yettiesoft.vestsign.util.*" %>
<%@ page import="java.io.*" %>

<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="A layout example with a side menu that hides on mobile, just like the Pure website.">

    <title>VestSign DEMO</title>

<link rel="stylesheet" href="./css/pure/pure.css">
    <!--[if lte IE 8]>
        <link rel="stylesheet" href="./css/layouts/side-menu-old-ie.css">
        <link rel="stylesheet" href="./css/pure/grids-responsive-old-ie.css">
    <![endif]-->
    <!--[if gt IE 8]><!-->
        <link rel="stylesheet" href="./css/layouts/side-menu.css">
        <link rel="stylesheet" href="./css/pure/grids-responsive.css">
    <!--<![endif]-->

	<script>
	<%
		VidVerifier vid = new VidVerifier();
		out.println(vid.writeServerCertScript("s"));
	%>
    </script>
    
</head>

<body>
<div id="layout">
    <!-- Menu toggle -->
    <a href="#menu" id="menuLink" class="menu-link">
        <!-- Hamburger icon -->
        <span></span>
    </a>

<script type="text/javascript" src="../vestsign.js"></script>
<script type="text/javascript" src="../library/json3.min.js"></script>
<SCRIPT language=javascript>


if(!(typeof(console) === 'object' && typeof(console.log) === 'object')){
   console = {};
   console.log = function() {};
}

  var option = {
      encoding: 'base64',
      charset: 'utf-8'       // utf-8, euc-kr 선택
      //signtype: '2', // p7서명
 }
  var callback = function (result) {
    document.getElementById('envMsg').placeholder = result;
    document.getElementById('envMsg').value = result;

  };

  var errorcallback = function (error) {
    // error = { code, getReason() };
    if(error.code == -9999) console.log(error.code);  // 취소 버튼 이벤트 error.code: -9999
    else alert(error.msg);
  };



  function envelope() {
    var plain = document.getElementById('plainMsg').value;
    if (plain == null || plain == ""){
    	plain = document.getElementById('plainMsg').placeholder;
    }

    yettie.envelope(s, plain, option, callback, errorcallback);
  };


     
  function VerifyServer() {
	
		if(sForm.envMsg.value == ""){
			alert("전자서명문 메시지가 올바르지 않습니다.");
			return ;
		}
		
		document.sForm.idn.value = document.getElementById('envMsg').value;
		document.sForm.method = "POST";
	    document.sForm.action = "deenvelope.jsp";
	    document.sForm.submit();

  };

</SCRIPT>

    <div id="main">
        <div class="header">
            <h1>VestSign P7 전자봉투</h1>
            <h2>서버의 공개키로 사용자의 데이터를 암호화 합니다.</h2>
            <p>서버로 부터 전달 받은 서버의 인증서로 암호화를 수행합니다.</p>
            <p>암호화된 데이터는 서버에서 복호화 됩니다.</p>

        </div>
	
         <div class="content">
        	<br>
			    <div class="pure-u-1 pure-u-md-1-2">
			    	<form class="pure-form">
				
					    <fieldset class="pure-group">

					    	<label for="전자봉투메세지">서버의 인증서로 암호화 할  데이터</label>
					        <textarea class="pure-input-1" style="height:300px;" id="plainMsg" placeholder="enveloping data"></textarea>
					    </fieldset>
					
					    <button type="button" class="pure-button pure-input-1 pure-button-primary" onClick=envelope()>전자봉투 생성</button>
					</form>
			    </div>
			    <div class="pure-u-1 pure-u-md-1-2">
			    	<form class="pure-form" name='sForm' id='sForm'>
				
					    <fieldset class="pure-group">
					    	<input  type="hidden" name="idn" id="idn" value="" ></input>
					    	<label for="전자봉투">전자봉투 스트링</label>
					        <textarea class="pure-input-1" style="height:300px;" name="envMsg" id="envMsg" placeholder="" value=""></textarea>
					    </fieldset> 

						<button type="button" class="pure-button pure-input-1 pure-button-primary" onClick=VerifyServer()>전자봉투 검증 (server)</button>
					</form>
			    </div>
			</div>
        </div>
</div>


<script src="./js/ui.js"></script>

</body>
</html>
