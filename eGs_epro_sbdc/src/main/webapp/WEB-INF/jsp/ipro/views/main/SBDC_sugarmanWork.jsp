<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %>

<script type="text/javascript" src="${jsPath}/ipro/views/main/SBDC_sugarmanWork.js"></script>

<script>
	$(function(){
         $('.list-login-tab li').click(function(){
             var $tabIdx = $(this).index();
             $(this).addClass('is-selected');
             $(this).siblings().removeClass('is-selected');
             $('.tab-contents').eq($tabIdx).show();
             $('.tab-contents').eq($tabIdx).siblings().hide();
		})
	})
</script>

	<div class="layout-contents">
		<div class="ui-contents">

			<!-- 로그인 -->
			<div class="box-login3">
				<h2 class="box-label">LOGIN</h2>
				<ul class="list-login-tab">
					<li class="is-selected">
						<a href="javascript:">내부</a>
					</li>
					<li>
						<a href="javascript:">외부평가위원</a>
					</li>
				</ul>
				
				<div class="wrap-tab-contents">
					<form id="loginFrm" name="loginFrm" method="POST">
						<input type="hidden" id="P_LOGIN_ID" name="P_LOGIN_ID">
						<input type="hidden" id="P_LOGIN_GBN" name="P_LOGIN_GBN">
						<input type="hidden" id="P_LOGIN_MTHD" name="P_LOGIN_MTHD">
						 
						<input type="hidden" name="resourceName">
						
						<!-- 슈가맨로그인시 필요 -->
						<input type="hidden" name="logCheck" value="N">
						
						
						<div class="tab-contents">
							<div class="form-login">
								<div class="form-label">ID/PW</div>
								<input type="text" id="P_USR_ID" name="P_USR_ID" class="input-login" placeholder="ID" value="" />
								<br>
								<input type="password" id="P_PWD" name="P_PWD" maxlength="20" class="input-login" placeholder="PW" value="" />
							</div>
							<button type="button" class="btn-login" id="loginBtn" onsubmit="return false;">ID/PW 로그인</button>
						</div>
						
						<div class="tab-contents" style="display:none;">
						
						<div class="tab-contents">
							<div class="form-login">
								<div class="form-label">주민등록번호</div>
								<input type="text" id="P_RSDN_NO_1" name="P_RSDN_NO_1" class="input-login-h2" maxlength="6" value="" style="text-align: center;" numeric>
								<span class="hyphen">-</span>
								<input type="password" id="P_RSDN_NO_2" name="P_RSDN_NO_2" class="input-login-h2" maxlength="7" value="" style="text-align: center;" numeric>
								
								<input type="password" id="P_ESTM_PWD" name="P_ESTM_PWD" maxlength="20" class="input-login" placeholder="PW" value="" style="margin-top: 55px;" />
							</div>
							<button type="button" class="btn-login" id="rsdnNoLoginBtn" onsubmit="return false;">주민등록번호 로그인</button>
						</div>
						
					</form>
				</div>
			</div>
			<!-- //로그인 -->
		</div>
	
	</div>
	<!-- //Contents -->
</div>