<%@ page language="java" contentType="text/html; charset=UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %>

<script type="text/javascript" src="${jsPath}/opro/views/main/emgncCmtmLoginForm.js"></script>

<%-- <iframe src="/WebCubeSetupStart.htm" style="width:100vw; height:100vw; visibility:hidden; display:none"></iframe>
<script type="text/javascript" src="${contextPath}/WebCube/WebCubeAgent_SetupA.js"></script>
<script type="text/javascript" src="${contextPath}/WebCube/WebCubeAgent_Msg.js"></script> --%>

<link rel="stylesheet" href="${contextPath}/PKIToolkitDemo_forOpenWeb/score-demo-openweb/js/bs-3.3.5/css/bootstrap.css" type="text/css">
<link rel="stylesheet" href="${contextPath}/PKIToolkitDemo_forOpenWeb/score-demo-openweb/js/bs-3.3.5/css/bootstrap-theme.css" type="text/css">
<link rel="stylesheet" href="${contextPath}/PKIToolkitDemo_forOpenWeb/score-demo-openweb/css/common.css" type="text/css">


<div class="layout-contents">
	<div class="ui-contents">
		<!-- 로그인 -->
		<div class="box-login2" style="padding: 25px 25px;">
			<h2 class="box-label" style="padding: 0 0 10px 0; margin-top: 0px; margin-bottom: 0px;">비대면 평가관리 시스템</h2>
			<h2 class="box-label2" style="padding: 0 0 10px 0; margin-top: 0px; margin-bottom: 0px;">- 평가위원 비상용 -</h2>
			<ul class="list-login-tab">
			</ul>
			
			<div class="wrap-tab-contents">
				<form id="loginFrm" name="loginFrm" method="POST">
					<input type="hidden" id="P_LOGIN_ID" name="P_LOGIN_ID">
					<input type="hidden" id="P_LOGIN_GBN" name="P_LOGIN_GBN">
					<input type="hidden" id="P_LOGIN_MTHD" name="P_LOGIN_MTHD">
					
					<input type="hidden" name="resourceName" value="OEP15001">
					<div class="tab-contents">
						<div class="form-login" style="position:absolute; left:30%">
							<div style="float:left;">
								<input type="text" id="P_USR_ID" name="P_USR_ID" class="input-login-i" style="text-align: left; padding-left: 10px;" placeholder="아이디" value="" />
								<br>
								<input type="password" id="P_PWD" name="P_PWD" maxlength="20" class="input-login-i" style="text-align: left; padding-left: 10px;" placeholder="비밀번호" value="" />
								<br>
								<input title="주민번호앞자리6자리" placeholder="주민번호앞6자리" type="text" id="P_RSDN_NO_1" name="P_RSDN_NO_1" class="input-login-h" maxlength="6" value="" style="text-align: center; width: 130px;" numeric>
								<span class="hyphen">-</span>
								<input title="주민번호뒷자리7자리" placeholder="주민번호뒤7자리" type="password" id="P_RSDN_NO_2" name="P_RSDN_NO_2" class="input-login-h" maxlength="7" value="" style="text-align: center; width: 130px;" numeric>
							</div>
							<button type="button" class="btn-login_3" id="loginBtn"  name="loginBtn" style="float:right; height: 140px;" >로그인</button>
						</div>
					</div>
				</form>
			</div>
		</div>
		<!-- //로그인 -->
	</div>
	
	
	<div class="ui-contents">
		<!-- 공지 -->
		<div class="box-board">
			<div class="ui-title">
				<h2 class="box-label">
					공지
				</h2>
				<a href="javascript:" class="link-add" onclick="notiList('NOTI')">+ 더보기</a>
			</div>

			<ul class="list-board">
				<c:if test="${notiListTotCnt > 0}">
					<c:forEach var="data" items="${notiList}" varStatus="status">
						<li>
							<a href="javascript:" onclick="detailInqire('${data.BBS_SECD }', '${data.BBS_SN}');">
								<div class="txt-title">${data.TTL_NM }</div>
								<div class="txt-date">${comFn:formatDate(data.REG_DT,"yyyyMMddHHmmss","yyyy-MM-dd") }</div>
							</a>
						</li>
					</c:forEach>
				</c:if>
			</ul>
		</div>
		<!-- //공지 -->

		<!-- 질문 -->
		<div class="box-qa">
			<div class="ui-title">
				<h2 class="box-label">
					자주하는<br>질문
				</h2>
				<a href="javascript:" class="link-add" onclick="notiList('FAQ')">+ 더보기</a>
			</div>
			<ul class="list-qa">
				<c:if test="${faqListTotCnt > 0}">
					<c:forEach var="data" items="${faqList}" varStatus="status">
						<li>
							<a href="javascript:" onclick="detailInqire('${data.BBS_SECD }', '${data.BBS_SN}');">
								<span class="txt-qa-title">
									<em class="txt-qa">Q.</em>
									${data.TTL_NM }
								</span>
							</a>
						</li>
					</c:forEach>
				</c:if>
			</ul>
		</div>
		<!-- //질문 -->
	</div>
</div>


<form id=manualFrm method="POST">
</form>
<form id="popupFrm" method="POST">
	<input type="hidden" name="resourceName" />
	<input type="hidden" name="P_BBS_SECD" />
	<input type="hidden" name="P_BBS_SN" />
</form>