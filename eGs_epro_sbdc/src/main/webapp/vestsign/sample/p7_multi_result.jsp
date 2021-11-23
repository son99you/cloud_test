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

   
    
<%
	String sm1 = request.getParameter("signedMsg1");
	String sm2 = request.getParameter("signedMsg2");
	
	String sverify  = request.getParameter("verify");

    /*
     * 	public static final int CERT_STATUS_CRL	= 0;
     *  public static final int CERT_STATUS_OCSP = 1;
     *  public static final int CERT_STATUS_NONE = 2;
     */

	int cert_verify = 2;
	if (sverify != null)  
		cert_verify = Integer.parseInt(request.getParameter("verify") ) - 1;

	SignVerifier sv1 = new SignVerifier(sm1, cert_verify, 1);
	SignVerifier sv2 = new SignVerifier(sm2, cert_verify, 1);
	sv1.verify();
	sv2.verify();

%>

    <div id="main">
        <div class="header">
            <h1>HTML5 공인인증</h1>
            <h2>공인인증 검증 페이지</h2>
            <p>전자서명문 서버검증</p>
        </div>
	
        <div class="content">
            <div class="table-responsive">
            <br>
		        <table class="pure-table pure-table-bordered">
		            <thead>
		                <tr>
		                    <th>항목</th>
		                    <th>RESULT</th>
		                </tr>
		            </thead>
		            <tbody>
		                <tr>
		                    <td>전자서명문</td>
		                    <td>첫번째 서명 : <br><textarea cols='80' rows='5'><%=sm1%></textarea> <br>
		                    	두번째 서명 : <br><textarea cols='80' rows='5'><%=sm2%></textarea>
		                    	</td>
		                </tr>
<%

	int nVerifierResult1 = sv1.getLastErrorCode();
	String sErrorMsg1 = sv1.getLastErrorMsg();
	
	int nVerifierResult2 = sv2.getLastErrorCode();
	String sErrorMsg2 = sv2.getLastErrorMsg();
	
	System.out.println("================" + nVerifierResult1 + "==============" + sErrorMsg1);
	System.out.println("================" + nVerifierResult2 + "==============" + sErrorMsg2);

	if (nVerifierResult1 == 0 && nVerifierResult2 == 0 ) {
		CertificateInfo  cert = sv1.getSignerCertificate();
%>
		                <tr>
		                    <td>전자서명 원문</td>
		                    <td>첫번째 서명 : <%=sv1.getSignedMessageText()%> <br>
		                   두번째 서명 : <%=sv2.getSignedMessageText()%></td>
		                </tr>
						<tr>
		                    <td>사용자 인증서 정책</td>
		                    <td><%=cert.getPolicyIdentifier()%></td>
		                </tr>
		                <tr>
		                    <td>사용자 인증서 DN</td>
		                    <td><%=cert.getSubject()%></td>
		                </tr>
		                <tr>
		                    <td>사용자 인증서 serial</td>
		                    <td> <%=cert.getSerialToHex()%></td>
		                </tr>
<%
		if (cert_verify == 0){
			sErrorMsg1= "1번째 서명원문 검증 성공 & 인증서 유효성 검증 성공(CRL)";
			sErrorMsg2= "2번째 서명원문 검증 성공 & 인증서 유효성 검증 성공(CRL)";
		}
		if (cert_verify == 1){
			sErrorMsg1= "1번째 서명원문 검증 성공 & 인증서 유효성 검증 성공(OCSP)";
			sErrorMsg2= "2번째 서명원문 검증 성공 & 인증서 유효성 검증 성공(OCSP)";
		}
		if (cert_verify == 2){
			sErrorMsg1= "1번째 서명원문 검증 성공";
			sErrorMsg2= "2번째 서명원문 검증 성공";
		}
	}
%>						
						<tr>
		                    <td>에러코드</td>
		                    <td>첫번째 서명 :  <%=nVerifierResult1%><br>
		                    두번째 서명 : <%=nVerifierResult2%></td>
		                </tr>
						<tr>
		                    <td>검증결과</td>
		                    <td>첫번째 서명 : <%=sErrorMsg1%><br>
		                    두번째 서명 : <%=sErrorMsg2%></td>
		                </tr>
		            </tbody>
		        </table>
    		</div>
        </div>
    </div>
</div>



<script src="./js/ui.js"></script>
</body>
</html>
