<%@ page language="java" contentType="text/html; charset=UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %>

<script type="text/javascript" src="${jsPath}/opro/views/main/emgncLoginForm.js"></script>

<%-- <iframe src="/WebCubeSetupStart.htm" style="width:100vw; height:100vw; visibility:hidden; display:none"></iframe>
<script type="text/javascript" src="${contextPath}/WebCube/WebCubeAgent_SetupA.js"></script>
<script type="text/javascript" src="${contextPath}/WebCube/WebCubeAgent_Msg.js"></script> --%>

<link rel="stylesheet" href="${contextPath}/PKIToolkitDemo_forOpenWeb/score-demo-openweb/js/bs-3.3.5/css/bootstrap.css" type="text/css">
<link rel="stylesheet" href="${contextPath}/PKIToolkitDemo_forOpenWeb/score-demo-openweb/js/bs-3.3.5/css/bootstrap-theme.css" type="text/css">
<link rel="stylesheet" href="${contextPath}/PKIToolkitDemo_forOpenWeb/score-demo-openweb/css/common.css" type="text/css">

<script type="text/javascript" src="../js/demo.js"></script>
<script type="text/javascript" src="${contextPath}/PKIToolkitDemo_forOpenWeb/score-demo-openweb/js/bs-3.3.5/js/bootstrap.js"></script>
<script type="text/javascript" src="${contextPath}/PKIToolkitDemo_forOpenWeb/score-demo-openweb/openweb/js/nxts/nxts.min.js"></script>
<script type="text/javascript" src="${contextPath}/PKIToolkitDemo_forOpenWeb/score-demo-openweb/openweb/js/nxts/nxtspki_config.js"></script>
<script type="text/javascript" src="${contextPath}/PKIToolkitDemo_forOpenWeb/score-demo-openweb/openweb/js/nxts/nxtspki.js"></script>


<script src="${contextPath}/ScoreHTML5/js/bs-3.3.5/js/bootstrap.js"></script>
<link rel="stylesheet" href="${contextPath}/ScoreHTML5/css/tsign_cert_style.css" type="text/css"/>
<script type="text/javascript" src="${contextPath}/ScoreHTML5/js/tsHTML5PKIConfig.js"></script>
<script type="text/javascript" src="${contextPath}/ScoreHTML5/js/tsHTML5_forge.js"></script>
<script type="text/javascript" src="${contextPath}/ScoreHTML5/js/tsHTML5.min.js"></script>

	<!-- Contents -->
	<div class="layout-contents">
		<div class="ui-contents">
			<!-- ?????????????????? -->
			<div class="box-noti" style="height: 350px;">
				<h2 class="box-label" style="margin-top: 5px;">??????????????????</h2>
				<a href="javascript: gotoEstmAnncList();" class="link-add" style="margin-top: 5px;">+ ?????????</a>
					
				<!-- Slider Category -->
				<div class="slider-category slider slider-nav">
					<button type="button" href="javascript:" class="txt-rolling is-selected" onclick="estmAnncDetail('')">??????</button>
					<c:if test="${not empty estmSecdCodeList }">
						<c:forEach var="data" items="${estmSecdCodeList}" varStatus="status">
							<button type="button" href="javascript:" class="txt-rolling" onclick="estmAnncDetail('${data.CD_VALUE}')">${data.CD_VALUE_NM }</button>
						</c:forEach>
					</c:if>
					<!-- Add Pagination -->
				</div>
				<!-- //Slider Category -->
	
	
				<div class="wrap-noti">
					<div class="slider slider-for">
						<div class="ui-noti" style="display:block;" id="estmAnncDiv">
							<!-- area noti -->
							<c:if test="${empty estmAnncList}">
								<div class="area-noti">
									<span class="txt-day">&nbsp;</span>
									<div class="txt-title">??????????????? ????????????.</div>
								</div>
							</c:if>
							
							<c:if test="${not empty estmAnncList}">
								<c:forEach var="data" items="${estmAnncList}" varStatus="status">
									<div class="area-noti" onclick="recrAnncDetail('${data.ESTM_NO}')" style="cursor: pointer;">
										<span class="txt-day">${data.REG_DT_F }</span>
										<div class="txt-title">${data.ESTM_NM }</div>
										<div class="txt-date">
											<div class="txt-start">
											<span class="txt-date-tag">?????????</span>
												<span class="txt-date">${data.TOTL_ESTM_ST_DT_F }</span>
											</div>
											<div class="txt-end">
												<span class="txt-date-tag">?????????</span>
												<span class="txt-date">${data.TOTL_ESTM_END_DT_F }</span>
											</div>
										</div>
									</div>
								</c:forEach>
							</c:if>
							<!-- //area noti -->
						</div>
					</div>
				</div>
			</div>
			<!-- //???????????? -->
	
	
			<!-- ????????? -->
			<div class="box-login" style="height: 350px;">
				<h2 class="box-label" style="font-weight: 500;">????????? ???????????? ?????????</h2>
				<h2 class="box-label2"  style="font-weight: 500;">- ??????????????? -</h2>
				<!-- <ul class="list-login-tab">
					<li class="is-selected">
						<span class="a">???????????????</span>
					</li>
					<li>
						<a href="javascript:">????????????</a>
					</li>
				</ul> -->
				
				<div class="wrap-tab-contents">
					<form id="loginFrm" name="loginFrm" method="POST">
						<input type="hidden" id="P_LOGIN_ID" name="P_LOGIN_ID">
						<input type="hidden" id="P_LOGIN_GBN" name="P_LOGIN_GBN">
						<input type="hidden" id="P_LOGIN_MTHD" name="P_LOGIN_MTHD">
						<input type="hidden" id="P_LOGIN_DN" name="P_LOGIN_DN">
						<input type="hidden" id="P_CERT_POLICY" name="P_CERT_POLICY">
						
						<input title="??????" type="checkbox" id="verifyVID" style="display: none;"/>
						<input type="hidden" id="userInfo" name="userInfo"/>
						<input type="hidden" id="ssn" name="ssn"/>
						<input type="hidden" id="sessionId" name="sessionId"/>
						<input type="hidden" id="loginData" name="loginData"/>
						<input type="hidden" id="loginDataKmCert" name="loginDataKmCert" value="${loginDataKmCert}">
						 
						<input type="hidden" name="resourceName">
						<div class="tab-contents">
							<div class="form-login">
								<div class="form-label" style="font-weight: 500;">??????????????????</div>
								<input title="?????????????????????6??????" type="text" id="P_RSDN_NO_1" name="P_RSDN_NO_1" class="input-login-h" maxlength="6" value="" style="text-align: center;" numeric>
								<span class="hyphen">-</span>
								<input title="?????????????????????7??????" type="password" id="P_RSDN_NO_2" name="P_RSDN_NO_2" class="input-login-h" maxlength="7" value="" style="text-align: center;" numeric>
							</div>
							<button type="button" class="btn-login_1" id="browserLoginBtn" onsubmit="return false;" style="font-weight: 500;">?????????????????????<br>?????????</button>
							<button type="button" class="btn-login_1" id="assoLoginBtn" onsubmit="return false;" style="margin-left: 10px; font-weight: 500;">??????????????? <br>?????????</button>
							<br>
							<!-- <button type="button" class="btn-login_2" id="rsdnNoLoginBtn" onsubmit="return false;">?????????????????? ?????????</button> -->
						</div>
						<div class="tab-contents" style="display:none;">
							<div class="tab-contents">
								<div class="form-login">
									<div class="form-label">???????????????</div>
									<input title="???????????????10??????" type="text" id="P_LOGIN_ID_VIEW" name="P_LOGIN_ID_VIEW" class="input-login" value="" numeric maxlength="10">
								</div>
								<button type="button" class="btn-login" id="bizrnoLoginBtn" onsubmit="return false;">??????????????? ?????????</button>
							</div>
						</div>
					</form>
				</div>
			</div>
			<!-- //????????? -->
		</div>
		
		
		<div class="ui-contents">
			<!-- ?????? -->
			<div class="box-board">
				<div class="ui-title">
					<h1 class="box-label" style="font-size: 24px">
						??????
					</h1>
					<a href="javascript:" class="link-add" onclick="notiList('NOTI')">+ ?????????</a>
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
			<!-- //?????? -->
	
			<!-- ?????? -->
			<div class="box-qa">
				<div class="ui-title">
					<h1 class="box-label" style="font-size: 24px">
						<!-- ????????????<br>?????? -->
						FAQ
					</h1>
					<a href="javascript:" class="link-add" onclick="notiList('FAQ')">+ ?????????</a>
				</div>
				<ul class="list-qa">
					<c:if test="${faqListTotCnt > 0}">
						<c:forEach var="data" items="${faqList}" varStatus="status">
							<li>
								<a href="javascript:" onclick="detailInqire('${data.BBS_SECD }', '${data.BBS_SN}');">
									<span class="txt-qa-title">
										<em class="txt-qa">Q.</em>
										${data.TTL_NM}
									</span>
								</a>
							</li>
						</c:forEach>
					</c:if>
				</ul>
			</div>
			<!-- //?????? -->
		</div>
	
	</div>
	<!-- //Contents -->
</div>

<form id="detailFrm" method="POST">
	<input type="hidden" name="resourceDesc" value="${ param.resourceDesc }"> 
	<input type="hidden" name="resourceName" value="${comFn:nvl(param.resourceName, resourceName)}">
	<input type="hidden" name="P_ESTM_SECD_S">
	<input type="hidden" name="P_ESTM_NO">
</form>


<form id="notiDetailFrm" method="POST">
	<input type="hidden" name="resourceDesc" value="${ param.resourceDesc }"> 
	<input type="hidden" name="resourceName" value="${comFn:nvl(param.resourceName, resourceName)}">
	<input type="hidden" name="P_BBS_SECD">
	<input type="hidden" name="P_BBS_SN">
</form>

<form id="manualFrm" method="POST">
</form>