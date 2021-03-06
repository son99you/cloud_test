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
                    <li class="list-group-item font-white bg-title-custom"><b>????????????</b></li>
                    <li class="list-group-item"><a href="./signData.jsp">1. ????????? ??????/?????? (Signed Data / verifySignedData)</a></li>
                    <li class="list-group-item"><a href="./addSignInSignedData.jsp">2. ?????? ????????? ??????/?????? (addSignInSignedData / verifySignedData)</a></li>
                    <li class="list-group-item"><a href="./generateSignature.jsp">3. ?????? ??????/?????? (generateSignature / verifySignature)</a></li>
                    <li class="list-group-item"><a href="./multiSignData.jsp">4. ?????? ????????? ?????? (multiSignData)</a></li>
                    <li class="list-group-item"><a href="./multiGenerateSignature.jsp">5. ?????? ?????? ?????? (multiGenerateSignature)</a></li>
                </ul>
                <ul class="list-group">
                    <li class="list-group-item font-white bg-title-custom"><b>??????????????????</b></li>
                    <li class="list-group-item"><a href="./loginData.jsp">1. ?????????????????? ??????</a></li>
                </ul>
                <ul class="list-group">
                    <li class="list-group-item font-white bg-title-custom"><b>????????????</b></li>
                    <li class="list-group-item"><a href="./envelopData.jsp">1. ????????????</a></li>
                </ul>
                <ul class="list-group">
                    <li class="list-group-item font-white bg-title-custom"><b>????????? ????????????</b></li>
                    <li class="list-group-item"><a href="./encryptData.jsp">1. ????????? ????????????</a></li>
                </ul>
                <ul class="list-group">
                    <li class="list-group-item font-white bg-title-custom"><b>?????? ??????</b></li>
                    <li class="list-group-item"><a href="./verifyVID.jsp">1. VID ??????</a></li>
                </ul>
                <ul class="list-group">
                    <li class="list-group-item font-white bg-title-custom"><b>?????? ??? ????????????</b></li>
                    <li class="list-group-item"><a href="./base64.jsp">1. base64 ?????????/????????? (base64encode / base64encode)</a></li>
                    <li class="list-group-item"><a href="./hashData.jsp">2. ?????? ????????? (hashData)</a></li>
                </ul>
                <ul class="list-group">
                    <li class="list-group-item font-white bg-title-custom"><b>??? ???</b></li>
                    <li class="list-group-item"><a href="./generateRandomNumber.jsp">1. ????????? ?????? (generateRandomNumber)</a></li>
                </ul>
                <ul class="list-group">
                    <li class="list-group-item font-white bg-title-custom"><b>?????? ??????</b></li>
                    <li class="list-group-item"><a href="./logRead.jsp">1. ?????? ?????? </a></li>
                </ul>
                <ul class="list-group">
                    <li class="list-group-item font-white bg-title-custom"><b>???????????? ????????????</b></li>
                    <li class="list-group-item"><a href="./signDataWithTimeStampToken.jsp">1. ???????????? with TimeStamp </a></li>
                </ul>

                <hr>
              </div>
            <%@include file="footer.jsp" %>
            </div>
        </div>
    </body>
</html>
