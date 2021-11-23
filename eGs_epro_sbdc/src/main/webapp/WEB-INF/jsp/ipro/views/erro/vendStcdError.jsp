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
	<input type="hidden" name="resourceName" value="opm801">
	<input type="hidden" name="resourceDesc" value="${param.resourceDesc}">
</form>

<script type="text/javascript">
	$(function() {
		alert($('#errMsgSub').val());
		
		FwkCmmnUtil.submitForm("loginFrm", "/opro/user/vendInfoDetail.do");
		
	});
	
</script>