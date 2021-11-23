<%@ page contentType="text/html; charset=utf-8" %>

<%@ page buffer="16kb" %>
<%@ page import="java.util.Vector" %>
<%@ page import="com.yettiesoft.vestsign.external.*" %>
<%@ page import="com.yettiesoft.vestsign.base.bean.UCPIDResult" %>
<%@ page import="com.yettiesoft.vestsign.external.UcpidVerifier" %>
<%@ page import="java.io.*" %>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<%
    String signedPersonInfoReq = request.getParameter("signedMsg");

    UcpidVerifier verifier = new UcpidVerifier();
    UCPIDResult result = verifier.verify(signedPersonInfoReq, 1);  // 1 hex
    // System.out.println(result);
%>

<html>
<head>
    <title>UCPID 검증 결과</title>
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
                                                        <td width='17%'><strong>errorCode</strong></td>
                                                        <td width='83%'><strong>

                                                            <%
                                                            out.println(verifier.getLastErrorCode());
                                                            %>
                                                        </strong></td>
                                                    </tr>
                                                    <tr>
                                                        <td width='17%'><strong>errorMsg</strong></td>
                                                        <td width='83%'><strong>

                                                            <%
                                                            if(verifier.getLastErrorCode() != 0)
                                                                out.println(verifier.getLastErrorMsg());
                                                            else
                                                                out.println("에러없음");
                                                            %>
                                                        </strong></td>
                                                    </tr>
                                                    <tr>
                                                        <td width='17%'><strong>version</strong></td>
                                                        <td width='83%'><strong>

                                                            <%
                                                            if(result != null)
                                                                out.println(result.getVersion());
                                                            else
                                                                out.println("정보없음");
                                                            %>
                                                        </strong></td>
                                                    </tr>
                                                    <tr>
                                                        <td width='17%'><strong>ucpidNonce</strong></td>
                                                        <td width='83%'><strong>

                                                            <%
                                                            if(result != null)
                                                                out.println(result.getUcpidNonce());
                                                            else
                                                                out.println("정보없음");
                                                            %>
                                                        </strong></td>
                                                    </tr>
                                                    <tr>
                                                        <td width='17%'><strong>cpRequestNumber</strong></td>
                                                        <td width='83%'><strong>

                                                            <%
                                                            if(result != null)
                                                                out.println(result.getCpRequestNumber());
                                                            else
                                                                out.println("정보없음");
                                                            %>
                                                        </strong></td>
                                                    </tr>
                                                    <tr>
                                                        <td width='17%'><strong>certDn</strong></td>
                                                        <td width='83%'><strong>

                                                            <%
                                                            if(result != null)
                                                                out.println(result.getCertDn());
                                                            else
                                                                out.println("정보없음");
                                                            %>
                                                        </strong></td>
                                                    </tr>
                                                    <tr>
                                                        <td width='17%'><strong>cpCode</strong></td>
                                                        <td width='83%'><strong>

                                                            <%
                                                            if(result != null)
                                                                out.println(result.getCpCode());
                                                            else
                                                                out.println("정보없음");
                                                            %>
                                                        </strong></td>
                                                    </tr>
                                                    <tr>
                                                        <td width='17%'><strong>ci</strong></td>
                                                        <td width='83%'><strong>

                                                            <%
                                                            if(result != null)
                                                                out.println(result.getCi());
                                                            else
                                                                out.println("정보없음");
                                                            %>
                                                        </strong></td>
                                                    </tr>
                                                    <tr>
                                                        <td width='17%'><strong>di</strong></td>
                                                        <td width='83%'><strong>

                                                            <%
                                                            if(result != null)
                                                                out.println(result.getDi());
                                                            else
                                                                out.println("정보없음");
                                                            %>
                                                        </strong></td>
                                                    </tr>
                                                    <tr>
                                                        <td width='17%'><strong>realName</strong></td>
                                                        <td width='83%'><strong>

                                                            <%
                                                            if(result != null)
                                                                out.println(result.getRealName());
                                                            else
                                                                out.println("정보없음");
                                                            %>
                                                        </strong></td>
                                                    </tr>
                                                    <tr>
                                                        <td width='17%'><strong>gender</strong></td>
                                                        <td width='83%'><strong>
                                                            <%
                                                            if(result != null) {
                                                                if (result.getGender() == UCPIDResult.GENDER_WOMAN)
                                                                    out.println("여성");
                                                                else if (result.getGender() == UCPIDResult.GENDER_MAN)
                                                                    out.println("남성");
                                                                else
                                                                    out.println("정보없음");
                                                                }
                                                            else
                                                                out.println("정보없음");
                                                            %>
                                                        </strong></td>
                                                    </tr>
                                                    <tr>
                                                        <td width='17%'><strong>NationalInfo</strong></td>
                                                        <td width='83%'><strong>

                                                            <%
                                                            if(result != null) {
                                                                if (result.getNationalInfo() == UCPIDResult.NATIONAL_LOCAL)
                                                                    out.println("내국인");
                                                                else if (result.getNationalInfo() == UCPIDResult.NATIONAL_FOREIGNER)
                                                                    out.println("외국인");
                                                                else
                                                                    out.println("정보없음");
                                                                }
                                                            else
                                                                out.println("정보없음");
                                                            %>
                                                        </strong></td>
                                                    </tr>
                                                    <tr>
                                                        <td width='17%'><strong>birthDate</strong></td>
                                                        <td width='83%'><strong>

                                                            <%
                                                            if(result != null)
                                                                out.println(result.getBirthDate());
                                                            else
                                                                out.println("정보없음");
                                                            %>
                                                        </strong></td>
                                                    </tr>
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
