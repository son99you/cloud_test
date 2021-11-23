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
     
    SignVerifier sv = new SignVerifier(sm, /*���ڼ���*/
            cert_verify,				/*��ȿ������ ���*/
            1);				/*���� ���ڵ� ��*/
    sv.verify();
%>

<html>
<head>
    <title>���� ���� ���</title>
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
                                    <p align="center"><font size="5" face="Times New Roman, Times, serif">WebBrowser�κ���
                                        �Է¹��� ����Դϴ�.</font></p>
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
                                                        <td width='17%'><strong>����</strong></td>
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
                                                        <td width='17%'><strong>���� ���� ���</strong></td>
                                                        <td width='83%'><strong>
                                                            <%
                                                            
                                                            // 0 : ����
                                                            // -10 : ���� 
                                                            // -20 : ���
                                                            // -30 : ��Ÿ����
                                                            
                                                                int nVerifierResult = sv.getLastErrorCode();
                                                                
                                                                if (nVerifierResult == 0) {
                                                                	if(cert_verify == 2){ 
	                                                                   out.println("������� ���� ����.<br>");
	                                                                   //out.println("������� : " + sv.getLastErrorMsg() + "<br>");
	                                                                   }
	                                                                else{
																																			out.println("�������  ���� ����.<br>");
																																			out.println("������ ��ȿ�� ���� ����.<br>");
	                                                                   //out.println("������� : " + sv.getLastErrorMsg() + "<br>");
	                                                                }
	                                                                    
                                                                    
                                                                } else if(nVerifierResult == -10){
                                                                    out.println("�������� ����Ǿ����ϴ�.<br>");
                                                                    out.println("���� ��ȣ : " + sv.getLastErrorCode() + "<br>");
                                                                    out.println("���� �޽��� : " + sv.getLastErrorMsg() + "<br>");                                                                	
                                                                } else if(nVerifierResult == -20){
                                                                    out.println("�������� ���Ǿ����ϴ�.<br>");
                                                                    out.println("���� ��ȣ : " + sv.getLastErrorCode() + "<br>");
                                                                    out.println("���� �޽��� : " + sv.getLastErrorMsg() + "<br>");                                                                	
                                                                } else if(nVerifierResult == -30){ //OCSP. CRL ����
                                                                    out.println("������ ������ �ֽ��ϴ�.<br>");
                                                                    out.println("���� ��ȣ : " + sv.getLastErrorCode() + "<br>");
                                                                    out.println("���� �޽��� : " + sv.getLastErrorMsg() + "<br>");                                                                	
                                                                } else { // VestSign ���̺귯�� ����
                                                                    out.println("������ ������ �ֽ��ϴ�.<br>");
                                                                    out.println("���� ��ȣ : " + sv.getLastErrorCode() + "<br>");
                                                                    out.println("���� �޽��� : " + sv.getLastErrorMsg() + "<br>");
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
                                                        <td width='17%'><strong>���ڼ��� ����</strong></td>
                                                        <td width='83%'><strong>

                                                                <%
                                                                    out.println(sv.getSignedMessageText()+ "<br>");
                                                                %>


                                                        </strong></td>
                                                    </tr>
                                                    <tr>
                                                        <td width='17%'><strong>������ ������ DN</strong></td>
                                                        <td width='83%'><strong>


                                                                <%=cert.getSubject()%>


                                                        </strong></td>
                                                    </tr>
                                                    <tr>
                                                        <td width='17%'><strong>������ ������ ��å �ʵ�</strong></td>
                                                        <td width='83%'><strong>


                                                                <%=cert.getPolicyIdentifier()%>


                                                        </strong></td>
                                                    </tr>
                                                    <tr>
                                                        <td width='17%'><strong>������ serial</strong></td>
                                                        <td width='83%'><strong>

                                                             <%=cert.getSerial()%>

                                                        </strong></td>
                                                    </tr>                                                                                                              
                                                    <tr>
                                                        <td width='17%'><strong>������ serial(���)</strong></td>
                                                        <td width='83%'><strong>

                                                             <%=Integer.toHexString(cert.getSerial())%>

                                                        </strong></td>
                                                    </tr>   
                                                    <tr>
                                                    </tr>
          
                                                    <tr>
                                                        <td width='17%'><strong>�����޼���</strong></td>
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
