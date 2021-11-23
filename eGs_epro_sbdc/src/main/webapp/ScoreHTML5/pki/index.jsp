<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html> 
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <!-- BEGIN THEME STYLES -->
        <link href="http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700&subset=all" rel="stylesheet" type="text/css" />
        <link href="../assets/global/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
        <link href="../assets/global/plugins/simple-line-icons/simple-line-icons.min.css" rel="stylesheet" type="text/css" />
        <link href="../assets/global/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
        <link href="../assets/global/plugins/uniform/css/uniform.default.css" rel="stylesheet" type="text/css" />
        <link href="../assets/global/plugins/bootstrap-switch/css/bootstrap-switch.min.css" rel="stylesheet" type="text/css" />
        <link href="../assets/global/css/components.min.css" rel="stylesheet" id="style_components" type="text/css" />
        <link href="../assets/global/css/plugins.min.css" rel="stylesheet" type="text/css" />
        <link href="../assets/layouts/layout4/css/layout.min.css" rel="stylesheet" type="text/css" />
        <link href="../assets/layouts/layout4/css/themes/light.min.css" rel="stylesheet" type="text/css" id="style_color" />
        <link href="../assets/global/css/custom.css" rel="stylesheet" type="text/css" />
        <!-- END THEME STYLES -->

        <title>SCORE PKI for HTML5 Demo</title>
    </head>
    <body class="page-container-bg-solid page-header-fixed">
        <div class="page-container">
            <div class="container">
            <%@include file="header.jsp" %>

            <div class="col-sm-12">
                <div><h1>SCORE PKI for HTML5 Demo</h1></div>

                <ul class="list-group">
                    <li class="list-group-item font-white bg-title-custom"><b>전자서명</b></li>
                    <li class="list-group-item"><a href="./signData.jsp">1. 데이터 서명/검증 (Signed Data / verifySignedData)</a></li>
                    <li class="list-group-item"><a href="./addSignInSignedData.jsp">2. 추가 데이터 서명/검증 (addSignInSignedData / verifySignedData)</a></li>
                    <li class="list-group-item"><a href="./generateSignature.jsp">3. 서명 생성/검증 (generateSignature / verifySignature)</a></li>
                    <li class="list-group-item"><a href="./multiSignData.jsp">4. 멀티 데이터 서명 (multiSignData)</a></li>
                    <li class="list-group-item"><a href="./multiGenerateSignature.jsp">5. 멀티 서명 생성 (multiGenerateSignature)</a></li>
                </ul>
                <ul class="list-group">
                    <li class="list-group-item font-white bg-title-custom"><b>로그인데이터</b></li>
                    <li class="list-group-item"><a href="./loginData.jsp">1. 로그인데이터 생성</a></li>
                </ul>
                <ul class="list-group">
                    <li class="list-group-item font-white bg-title-custom"><b>전자봉투</b></li>
                    <li class="list-group-item"><a href="./envelopData.jsp">1. 전자봉투</a></li>
                </ul>
                <ul class="list-group">
                    <li class="list-group-item font-white bg-title-custom"><b>대칭키 암복호화</b></li>
                    <li class="list-group-item"><a href="./encryptData.jsp">1. 대칭키 암복호화</a></li>
                </ul>
                <ul class="list-group">
                    <li class="list-group-item font-white bg-title-custom"><b>신원 확인</b></li>
                    <li class="list-group-item"><a href="./verifyVID.jsp">1. VID 검사</a></li>
                </ul>
                <ul class="list-group">
                    <li class="list-group-item font-white bg-title-custom"><b>해시 및 인디코딩</b></li>
                    <li class="list-group-item"><a href="./base64.jsp">1. base64 인코딩/디코딩 (base64encode / base64encode)</a></li>
                    <li class="list-group-item"><a href="./hashData.jsp">2. 해시 데이터 (hashData)</a></li>
                </ul>
                <ul class="list-group">
                    <li class="list-group-item font-white bg-title-custom"><b>그 외</b></li>
                    <li class="list-group-item"><a href="./generateRandomNumber.jsp">1. 랜덤값 생성 (generateRandomNumber)</a></li>
                </ul>
                <ul class="list-group">
                    <li class="list-group-item font-white bg-title-custom"><b>로그 관리</b></li>
                    <li class="list-group-item"><a href="./logRead.jsp">1. 로그 확인 </a></li>
                </ul>
                <ul class="list-group">
                    <li class="list-group-item font-white bg-title-custom"><b>전자서명 부가기능</b></li>
                    <li class="list-group-item"><a href="./signDataWithTimeStampToken.jsp">1. 전자서명 with TimeStamp </a></li>
                </ul>

                <hr>
              </div>
            <%@include file="footer.jsp" %>
            </div>
        </div>
    </body>
</html>
