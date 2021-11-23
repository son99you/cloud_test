<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<input type="hidden" id="errMsg" value="${ex}">
<input type="hidden" id="errMsgSub" value="${exMsg}">
<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
<!--  
  <tr>
    <td width="100%" height="100%" align="center" valign="middle" style="padding-top:150px;">
        <div>
            <table>
                <tbody>
                    <tr>
						<td>
                        	에러가 발생하였습니다. 
                        	<a href="/main/mainPage.do">메인으로 돌아가기</a>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
    </td>
  </tr>
-->
</table>

<form id="loginFrm" name="loginFrm">
 	<input type="hidden" name="resourceName" value="ipm1011" />
 	<input type="hidden" name="P_ERROR" value="${P_ERROR}" />
	<input type="hidden" name="P_ERROR_MESSAGE" value="${P_ERROR_MESSAGE}" />
   	<input type="hidden" name="P_ACCESS_TOKEN" value="" />
</form>

<script type="text/javascript">
	$(function() {
		FwkCmmnUtil.loadingImage();
		
		alert($('#errMsgSub').val());
		
/* 		var sysCd = "C0010018";
		
		var response_type = "code";
		var client_id = "2e613055bb604bc4a192cd7b6712cd87";
		var rtnUrl = "http%3A%2F%2Fdevbid.keri.re.kr%2Fmain%2FssoLoginForm.do";
		var scope = "http://sso.keri.re.kr";
		var state = ""; 
		
		FwkCmmnUtil.submitForm("loginFrm", "http://devkepos.keri.re.kr/kp/index.jsp?sysCd="+sysCd+"&rtnUrl="+rtnUrl+"");
		*/		
		// MESSAGE CODE 값 MssageUtil.js에 정의
		var sysCd = FwkMssageUtil.getMessage("SSO.SYS.CD");
		var response_type = FwkMssageUtil.getMessage("SSO.RESPONSE.TYPE");
		var client_id = FwkMssageUtil.getMessage("SSO.CLIENT.ID");
		var rtnUrl = FwkMssageUtil.getMessage("SSO.SYS.URL");
		var scope = FwkMssageUtil.getMessage("SSO.SCOPE");
		var url = FwkMssageUtil.getMessage("SSO.URL");
		
		var state = "";
		
		FwkCmmnUtil.submitForm("loginFrm", url+"?sysCd="+sysCd+"&rtnUrl="+rtnUrl+"");
		
	});
	
	emplyrLogin = function() {
		FwkCmmnUtil.submitForm("loginFrm", "/main/ssoLoginForm.do");  
	};
	
	
</script>