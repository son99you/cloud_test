<%@ page import="com.yettiesoft.vestsign.external.*" %>
<%@ page import="com.yettiesoft.vestsign.util.*" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>VestSign Sample</title>

  <link rel="stylesheet" href="./stylesheets/font-awesome.min.css">
  <link rel="stylesheet" href="./stylesheets/base.css">
  <link rel="stylesheet" href="./stylesheets/skeleton.css">
  <link rel="stylesheet" href="./stylesheets/refineslide.css">
  <link rel="stylesheet" href="./stylesheets/layout.css">

  <script type="text/javascript" src="../vestsign.js"></script>
</head>

<body>
<div class="wrap-header">
  <div class="container">
    <div class="eleven columns omega main-header header-bar-left-only">
      <h1 class="logo"><a href="#"></a></h1>
    </div>
  </div>
</div>

<div class="sub-header">
  <div class="container">
    <div class="eleven columns omega left-only">
      <div id="search">
        <button name="search" type="button" value="val">&#xf002;</button>
        <input type="text" name="search" value="" />
      </div>
    </div>
    <div class="one-third column alpha">
      <div class="breadcrumbs">
        <h1>VestSign Test</h1>
      </div>
    </div>
  </div>
</div>
<div class="container">
  <div class="eleven columns omega fullwidth-bar left-only">
    <div class="main-content clearfix">
      <div class="post-fullwidth">
        <section class="clearfix">
          <div class="heading"><h2>전자서명+본인확인<br>(주민번호 or 사업자번호 입력)</h2></div>
          <form method='post' name='Reg_Form' id="Reg_Form" autocomplete="off" action='./signVerify_vid.jsp' >
            서버에서 검증할 주민번호(사업자번호): <input type='taxt' name='idn' id="idn">
            <br>
            서명원문  : <textarea cols='120' rows='5' name='plain' id="signMsg">signature test</textarea>
            서명(된)문 : <textarea cols='120' rows='5' name='signedMsg' id="signedMsg"></textarea>
            식별번호값 : <textarea cols='120' rows='5' name='vidMsg' id="vidMsg"></textarea>
            <br>
            <input type="radio" name="showType" value="iframe" checked="true" />iframe
            <input type="radio" name="showType" value="popup" />popup
            <br>
            <button type="button" id="signBtnVid">전자서명하기</button>
            <button type="button" id="signVerify">검증하기</button>
          </form>
        </section>
      </div>
    </div>
  </div>
</div>
<div class="wrap-footer">
  <footer class="container">
    <div class="eleven columns omega footer-bar left-only">
      <div class="widgets clearfix">
        <div class="one-third column alpha">
          <div class="widget-bound">
            <div class="heading-dark"></div>
          </div>
        </div>
        <div class="one-third column"></div>
      </div>
    </div>
    <div class="one-third column alpha">
      <div class="copyright clearfix">
        <small>&copy; 2011 <a href="http://www.yettiesoft.com">YettieSoft.</a>, All Rights Reserved.</small>
      </div>
    </div>
  </footer>
</div>
<script>
  <%
      VidVerifier vid = new VidVerifier();  
      out.println(vid.writeServerCertScript("s"));
  %>

  var option = {
      encoding: 'Hex',
      charset: 'utf-8'        // utf-8, euc-kr 선택
   };

  var callbackVid = function (result) {
    var signedMsg = document.getElementById( 'signedMsg' );
    var vidMsg = document.getElementById( 'vidMsg' );
    signedMsg.value = result.signature;
    vidMsg.value = result.vidMessage;
  };

  var errorcallback = function (error) {
    console.error(error);
  };

  document.getElementById( 'signBtnVid' ).onclick = function () {
      option.vid = {
        recipientCertificate: s
      };

      var plain = document.getElementById('signMsg').value;
      yettie.sign(plain, option, callbackVid, errorcallback);
  };
  
  document.getElementById( 'signVerify' ).onclick = function () {
      yettie.signVerifyEvent();
  };
</script>

</body>
</html>
