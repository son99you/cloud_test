
<%@ page contentType="text/html; charset=euc-kr" %>
<%@ page contentType="text/html; charset=euc-kr" %>
<%@ page buffer="16kb" %>
<%@ page import="com.yettiesoft.vestsign.external.*" %>
<%@ page import="com.yettiesoft.vestsign.util.*" %>
<%@ page import="java.io.*" %>

<meta http-equiv="Content-Type" content="text/html; charset=euc-kr">

<%
    /*
     * 	public static final int CERT_STATUS_CRL	= 0;
     *  public static final int CERT_STATUS_OCSP = 1;
     *  public static final int CERT_STATUS_NONE = 2;
     */
		String sm = request.getParameter("signed_msg");
		int cert_verify = 2;         
     
    SignVerifier sv = new SignVerifier(sm, /*전자서명문*/
            cert_verify,				/*유효성검증 방법*/
            1);				/*서명문 인코딩 룰*/
    sv.verify();
%>

<html>
<head>
    <title>서명 검증 결과</title>
    <META HTTP-EQUIV="Pragma" CONTENT="No-Cache">
    <style type="text/css">
        <!--
        .font1 {
            font-size: 9pt;
            color: #666666;
        }

        td {
            font-size: 9pt;
            color: #333333
        }

        A:link {
            font-size: 9pt;
            color: #010824;
            text-decoration: none;
        }

        A:active {
            font-size: 9pt;
            color: #010824;
            text-decoration: none;
        }

        A:visited {
            font-size: 9pt;
            color: #010824;
            text-decoration: none;
        }

        A:hover {
            font-size: 9pt;
            color: #0066cc;
            text-decoration: none;
        }

        -->
    </style>
</head>


<body bgcolor="#FFFFFF" text="#000000" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<table width="100%" border="0" cellspacing="0" cellpadding="0" height="100%">
    <tr>
        <td colspan="2" valign="top">
            <p>&nbsp;</p>

            <p>&nbsp;</p>
            <table width="92%" border="0" cellspacing="0" cellpadding="0" align="center">
                <tr>
                    <td valign="top">
                        <table width="85%" border="0" cellspacing="0" cellpadding="0">
                            <tr>
                                <td height="100" width="8%">&nbsp;</td>
                                <td height="120" valign="middle">
                                    <p align="center"><font size="5" face="Times New Roman, Times, serif">WebBrowser로부터
                                        입력받은 결과입니다.</font></p>
                                </td>
                            </tr>
                            <tr>
                                <td>&nbsp;</td>
                                <td valign="top">
                                    <table width="100%" border="0" cellspacing="0" cellpadding="0">
                                        <tr>
                                            <td width="2%" height="24" bgcolor="#DEDBCB">&nbsp;</td>
                                            <td width="2%" height="24" bgcolor="#DEDBCB">&nbsp;</td>
                                            <td height="24" bgcolor="#DEDBCB"></td>
                                        </tr>
                                        <tr>
                                            <td width="2%">&nbsp;</td>
                                            <td valign="top" width="2%">&nbsp;</td>
                                            <td>
                                                <p>&nbsp; </p>
                                            </td>
                                        </tr>
                                        <tr bgcolor="#FFFFFF">
                                            <td colspan="3" height="24" bgcolor="#FFFFFF">


                                                <table border='1' width='100%'>

                                                    <tr>
                                                        <td width='17%'><strong>서명문</strong></td>
                                                        <td width='83%'><strong>
                                                            <%
                                                                int i;
                                                                for (i = 0; i < sm.length(); i += 80) {
                                                                    if (i + 80 < sm.length())
                                                                        out.println(sm.substring(i, i + 80));
                                                                    else
                                                                        out.println(sm.substring(i, sm.length()));
                                                                }
                                                            %>
                                                        </strong></td>
                                                    </tr>
                                                    <tr>
                                                        <td width='17%'><strong> 'vid_msg' :</strong></td>
                                                        <td width='83%'><strong>

                                                            <%

                                                                //String vm = "76f5b974135e503935b7bf1fbd0a85ef0c8b65f39fdbb9e02e16f86f85d2dd3b9fcab0bb983e193d196c66680fe775d67ef74f686d04c4647a6ff44f14631c2b0b2aff3b37b82ab7f8ba915577d4d44140eeb858b58ad37a20b23bc2a53e32ea399861c8d9278ef12d4ead9781b6e773867ac369c6f4446f02ba5e8274e9ed64b5c164126b44a6c6d4f01fdd6ff48b724dfb5a1361e02c52f71752376d9fbe531cef71e86cf7b65d82aacb5722d597a3510033e392bcfe70258c56609f98849769ad489e6f626bdb78d81cadf88ee6ba84f8799a56ceeb50f5efd9d771e70ff9e1c37f93728412e9e1cb80d358fb8e4c9b857437907c19472591dc8a77770f2fdabb4096b09beee3766b6b2d8130e710cd5cf2e154a3db0b5c7c9363fc12c5d290fa95fa7dbdee23f32ecb6bebd249c2";
                                                                //String vm = "1c2602188bed2b1863106317705251aecf9828232264fe7652d6babf7f98d0c4a1913a896e4f3e0b8ae173db7d48303a7467bbaffb6c01db3a406ceff3ee769ab5152c7f634d9d46b318631657cdcc4bb5e9b42c54752c434c7b86847c8f711743bb0ccae70e9053bd553794c3d43d734393f470342a83cdd5cb4902f8dc0b3395145468c1c0b5ec68cc5d864630e83a1cbb6e00cd601f30255fc7b108fe51563d28a1472dd31fd8d14372f8f537ed1e5910f52ab5e3edd995652024abec043277ee1ce061858cc285de4934bf0e371923a9fad071148f2a789d5a9f5fd0985ff0618906463ec4a3d9cacea5b2f371b0bd4ea7811bed4d48b6829843088c7bbde464d5aea3b54d689e8efbe390af5db21be961fbcbbfb88818518a061838376a";
                                                                String vm = request.getParameter("vid_msg");
                                                                //out.println(vm);

                                                                if (vm != "" && vm != "undefined") {
                                                                    for (i = 0; i < vm.length(); i += 80) {
                                                                        if (i + 80 < vm.length())
                                                                            out.println(vm.substring(i, i + 80));
                                                                        else
                                                                            out.println(vm.substring(i, vm.length()));
                                                                    }
                                                                }

                                                            %>

                                                        </strong></td>
                                                    </tr>
                                                    <tr>
                                                        <td width='17%'><strong>서명 검증 결과</strong></td>
                                                        <td width='83%'><strong>
                                                            <%
                                                            
                                                            // 0 : 성공
                                                            // -10 : 만료 
                                                            // -20 : 폐기
                                                            // -30 : 기타오류
                                                            
                                                                int nVerifierResult = sv.getLastErrorCode();

                                                                if (nVerifierResult == 0) {
                                                                	if(cert_verify == 2){ 
	                                                                   out.println("서명원문 검증 성공.<br>");
	                                                                   //out.println("검증결과 : " + sv.getLastErrorMsg() + "<br>");
	                                                                   }
	                                                                else{

	                                                                }                                                                    
                                                                } else if(nVerifierResult == -10){
                                                                    out.println("인증서가 만료되었습니다.<br>");
                                                                    out.println("오류 번호 : " + sv.getLastErrorCode() + "<br>");
                                                                    out.println("오류 메시지 : " + sv.getLastErrorMsg() + "<br>");                                                                	
                                                                } else if(nVerifierResult == -20){
                                                                    out.println("인증서가 폐기되었습니다.<br>");
                                                                    out.println("오류 번호 : " + sv.getLastErrorCode() + "<br>");
                                                                    out.println("오류 메시지 : " + sv.getLastErrorMsg() + "<br>");                                                                	
                                                                } else if(nVerifierResult == -30){ //OCSP. CRL 오류
                                                                    out.println("서명문에 문제가 있습니다.<br>");
                                                                    out.println("오류 번호 : " + sv.getLastErrorCode() + "<br>");
                                                                    out.println("오류 메시지 : " + sv.getLastErrorMsg() + "<br>");                                                                	
                                                                } else { // VestSign 라이브러리 에러
                                                                    out.println("서명문에 문제가 있습니다.<br>");
                                                                    out.println("오류 번호 : " + sv.getLastErrorCode() + "<br>");
                                                                    out.println("오류 메시지 : " + sv.getLastErrorMsg() + "<br>");
                                                                }
                                                            %>
                                                        </strong></td>
                                                    </tr>
                                                    <%
                                                        if (nVerifierResult == 0 ) {

                                                    %>

                                                    <%
                                                      CertificateInfo  cert = sv.getSignerCertificate();
                                                      VidVerifier vid = new VidVerifier();
                                                      String idn = request.getParameter("idn");

                                                      boolean isSuccess = false;
                                                      if (idn != "") {
                                                          isSuccess = vid.verifyVirtualID(vm, idn, cert, 1); //성공이면 true
                                                      }
                                                    %>
                                                  <tr>
                                                        <td width='17%'><strong>전자서명 원문</strong></td>
                                                        <td width='83%'><strong>

                                                                <%
                                                                    out.println(sv.getSignedMessageText()+ "<br>");
                                                                %>


                                                        </strong></td>
                                                    </tr>
                                                    <tr>
                                                        <td width='17%'><strong>서명자 인증서 DN</strong></td>
                                                        <td width='83%'><strong>


                                                                <%=cert.getSubject()%>


                                                        </strong></td>
                                                    </tr>
                                                    <tr>
                                                        <td width='17%'><strong>서명자 인증서 정책 필드</strong></td>
                                                        <td width='83%'><strong>


                                                                <%=cert.getPolicyIdentifier()%>


                                                        </strong></td>
                                                    </tr>
                                                    <tr>
                                                        <td width='17%'><strong>서명자 serial</strong></td>
                                                        <td width='83%'><strong>

                                                             <%=cert.getSerial()%>

                                                        </strong></td>
                                                    </tr>                                                                                                              
                                                    <tr>
                                                        <td width='17%'><strong>서명자 serial(헥사)</strong></td>
                                                        <td width='83%'><strong>

                                                             <%=Integer.toHexString(cert.getSerial())%>

                                                        </strong></td>
                                                    </tr>   
                                                    <tr>
                                                    </tr>
                                                    <tr>
                                                        <td width='17%'><strong>식별번호검증결과</strong></td>
                                                        <td width='83%'><strong>

                                                            <%
                                                                if (!isSuccess) {
                                                                    out.println("식별번호 검증에 문제가 있습니다.<br>");
                                                                    out.println("오류번호 : " + vid.getLastErrorCode() + "<br>");
                                                                    out.println("오류메세지: " + vid.getLastErrorMsg() + "<br>");
                                                                } else {
                                                                    out.println("식별번호 검증 성공<br>");
                                                                }
                                                            %>

                                                        </strong></td>
                                                    </tr>
                                                    <%
                                                    } else {

                                                    %>

                                                    <tr>
                                                        <td width='17%'><strong>오류메세지</strong></td>
                                                        <td width='83%'><strong>
                                                            <pre>
                                                            <%=sv.getLastErrorMsg()%>
                                                            </pre>
                                                        </strong></td>
                                                    </tr>
                                                    <%
                                                        }
                                                    %>

                                                </table>

                                            </td>
                                        </tr>
                                        <tr>
                                            <td width="2%">&nbsp;</td>
                                            <td valign="top" width="2%">&nbsp;</td>
                                            <td>
                                                <p>&nbsp; </p>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td width="2%" height="24" bgcolor="#DEDBCB">&nbsp;</td>
                                            <td width="2%" height="24" bgcolor="#DEDBCB">&nbsp;</td>
                                            <td height="24" bgcolor="#DEDBCB">&nbsp;</td>
                                        </tr>
                                        <tr>
                                            <td width="2%">&nbsp;</td>
                                            <td width="2%">&nbsp;</td>
                                            <td>&nbsp;</td>
                                        </tr>
                                        <tr>
                                            <td width="2%">&nbsp;</td>
                                            <td width="2%">&nbsp;</td>
                                            <td>&nbsp;</td>
                                        </tr>
                                    </table>
                                </td>
                            </tr>
                            <tr>
                                <td height="40">&nbsp;</td>
                                <td height="40">&nbsp;</td>
                            </tr>
                        </table>
                    </td>
                </tr>
            </table>
        </td>
    </tr>
</table>
</body>
</html>
