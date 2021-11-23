<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %>
    
    <script type="text/javascript" src="${jsPath}/ipro/views/main/ssoLoginForm.js"></script>  

	<h1><img src="${imagePath}/ipro/login/login_logo.png" alt="은우소프트"></h1>
	<div class="login">
        <form id="loginFrm" name="loginFrm">
        	<input type="hidden" name="resourceName" value="ipm1011" />
        	<input type="hidden" name="P_ERROR" value="${P_ERROR}" />
        	<input type="hidden" name="P_ERROR_MESSAGE" value="${P_ERROR_MESSAGE}" />
        	<input type="hidden" name="P_ACCESS_TOKEN" value="" />
        	
			<fieldset>
	        	<legend>로그인</legend>
	        	<!--  
				<input type="text" id="P_USR_ID" name="P_USR_ID" class="w200 mb4" placeholder="ID" value="user1">
				<input type="password" id="P_PWD" name="P_PWD" maxlength="20" class="w200" placeholder="PW" value="user1">
				<button id="loginBtn" name="loginBtn" type="button" class="btn btn_login" title="로그인">로그인</button>
				<div id="chk_div">
					<label id="chk_lab"><input type="checkbox" id="P_LOGIN_ID_COO" name="P_LOGIN_ID_COO">
					<span id="chk_span">&nbsp;아이디저장</span></label>
				</div>
				-->
			</fieldset>
		</form>
	</div>
	<p class="copy">Copyright ⓒ KERI. All Rights Reserved.</p>  
