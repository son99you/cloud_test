<%@ page contentType="text/html; charset=euc-kr" %>
<%@ page buffer="16kb" %>
<%@ page import="java.util.Vector" %>
<%@ page import="com.yettiesoft.javarose.standard.cert.SGCertificate" %>
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
		String sm = request.getParameter("signedMsg");
		int cert_verify = 0;
     
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
																																			out.println("서명원문  검증 성공.<br>");
																																			out.println("인증서 유효성 검증 성공.<br>");
	                                                                   //out.println("검증결과 : " + sv.getLastErrorMsg() + "<br>");
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
                                                      //System.out.println("CERTTTTTTTTTTTTTT" + cert);
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
