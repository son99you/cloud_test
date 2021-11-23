<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %>
    

<script type="text/javascript" src="${jsPath}/ipro/views/main/adminByCertLoginForm.js"></script>  
<script src="${contextPath}/vestsign/library/json3.min.js"></script>
<script src="${contextPath}/vestsign/vestsign_ew.js"></script>
<script src="${contextPath}/vestsign/vestsign.js"></script>

	<h1><img src="${imagePath}/ipro/login/login_logo.png" alt="은우소프트"></h1>
	<div class="login">
        <form id="loginFrm" name="loginFrm">
        	<input type="hidden" name="resourceName" value="${comFn:nvl(param.resourceName, resourceName)}" />
 			<input type="hidden" name="loginData" id="loginData" value='' />
			<input type="hidden" id="P_LOGIN_ID_VIEW" name="P_LOGIN_ID_VIEW" value="1198602801" class="w200 mb4" />
			<input type="hidden" id="P_LOGIN_ID" name="P_LOGIN_ID">
 			
			<input type="text" id="P_BIZRNO" name="P_BIZRNO" class="w200 mb4" placeholder="BIZRNO" style="display: none;" />
			<input type="text" id="P_USR_ID" name="P_USR_ID" class="w200 mb4" placeholder="ID" style="display: none;"  />
			<input type="password" id="P_PWD" name="P_PWD" maxlength="20" class="w200" placeholder="PW" style="display: none;" />
			<button id="bizrnoLoginBtn" name="bizrnoLoginBtn" type="button" class="btn btn_login" title="로그인">로그인</button>
			
			<div id="chk_div">
				<label id="chk_lab"><input type="checkbox" id="P_LOGIN_ID_COO" name="P_LOGIN_ID_COO">
				<span id="chk_span">&nbsp;업체</span></label>
			</div>
		</form>
	</div>
	<p class="copy">Copyright ⓒ KTO. All Rights Reserved.</p>  