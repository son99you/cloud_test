<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 메인화면 > 메인화면
 *
 * <pre>
 * main 
 *    |_ mainPage.jsp
 * 
 * </pre>
 * @date : 2017. 06. 30
 * @version : 1.0
 * @author : 은우소프트 홍찬일
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %> 
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %> 
 
<script type="text/javascript" src="${jsPath}/ipro/views/main/mainPage.js"></script>

<div class="wrap-page">
	<!-- Header -->
	<div class="layout-mheader">
		<div class="wrap-header">
			<h1 class="img-logo" onclick="mainPage();" style="cursor: pointer;"></h1>
			<!-- User -->
			<div class="area-user">
				${loginResult.USR_NM}님 방문을 환영합니다.
				<a href="javascript:" class="btn-user-login" onclick="javascript:logout();">로그아웃</a>
			</div>
			<!-- //User -->
			<div class="header-menu">
				<ul class="list-menu">
					<c:forEach var="menuList" items="${myMenuList.myMenuList}" varStatus="idx">
					<li>
						<a href="#" onclick="clickLeftMenuMove('${menuList.LNK_URL}', '${menuList.URL_ID}', '${menuList.MENU_NM}');" title="${menuList.MENU_NM}">${menuList.MENU_NM}</a>
					</li>
					</c:forEach>
				</ul>
			</div>
		</div>
	</div>
	<!-- Header -->
	<!-- Contents -->
	<div class="layout-mcontents">
		<div class="ui-contents">
			<div class="box-report">
				<h1>평가진행현황</h1>
				<form id="searchFrm" method="POST">
					<h2 class="date">
						<input type="hidden" value="Y" name="P_SEARCH">
						<input type="hidden" value="" name="P_ESTM_PSCD">
						<!-- <div style="display:inline-block;">조회기간 : </div> -->
						<%-- &nbsp;${comFn:formatDate(P_BEGIN_DT_S,'yyyyMMdd','yyyy-MM-dd')} ~ ${comFn:formatDate(P_END_DT_S,'yyyyMMdd','yyyy-MM-dd')} --%>
						<!-- Component Calen -->
						<div class="component-calen" style="display:inline-block;">
							<div class="data-calen">
								<input type="text" id="P_BEGIN_DT_S" name="P_BEGIN_DT_S" value="${comFn:formatDate(P_BEGIN_DT_S,'yyyyMMdd','yyyy-MM-dd')}" class="component-input" date>
							</div>
							<em class="txt-bar">~</em>
							<div class="data-calen">
								<input type="text" id="P_END_DT_S" name="P_END_DT_S" value="${comFn:formatDate(P_END_DT_S,'yyyyMMdd','yyyy-MM-dd')}" class="component-input" date>
							</div>
							&nbsp;&nbsp;&nbsp;
							<div class="type-fright">
								<a href="javascript:" class="component-button-s type-add" id="searchBtn">조회</a>
							</div>
						</div>
						<!-- //Component Calen -->
					</h2>
				</form>
				<ul class="report">
					<li class="m_1">
						<span style="cursor:pointer;" onclick="estmMainList('')">${estmCntResult.TOTAL_CNT}</span>
						<strong>전체</strong>
					</li>
					<li class="m_2">
						<span style="cursor:pointer;" onclick="estmMainList('A001')">${estmCntResult.ESTM_A1_CNT}</span>
						<strong>평가작성</strong>
					</li>
					<li class="m_3">
						<span style="cursor:pointer;" onclick="estmMainList('A002')">${estmCntResult.ESTM_A2_CNT}</span>
						<strong>평가공고</strong>
					</li>
					<li class="m_4">
						<span style="cursor:pointer;" onclick="estmMainList('A003')">${estmCntResult.ESTM_A3_CNT}</span>
						<strong>평가위원<br>순위선정</strong>
					</li>
					<li class="m_5">
						<span style="cursor:pointer;" onclick="estmMainList('A005')">${estmCntResult.ESTM_A5_CNT}</span>
						<strong>평가중</strong>
					</li>
					<li class="m_6">
						<span style="cursor:pointer;" onclick="estmMainList('C001')">${estmCntResult.ESTM_C1_CNT}</span>
						<strong>평가종료</strong>
					</li>
				</ul>
			</div>
			
			<div class="box-flow">
				<h1>평가진행상세</h1>
				<!-- #1 -->
				<div id="estmList">
					<c:if test="${empty estmMainList }">
						<h2>
							해당 상태의 평가진행정보가 없습니다.
						</h2>				
					</c:if>
					<c:if test="${not empty estmMainList }">
						<c:forEach var="data" items="${estmMainList}" varStatus="status">
							<h2>
								[${data.ESTM_CHRG_DEPT_NM}]&nbsp;${data.ESTM_NM}
								<span class="date">등록일자 : ${comFn:formatDate(data.REG_DT, 'yyyyMMddHHmmss', 'yyyy-MM-dd')}</span>
							</h2>				
							<!-- 순서도 -->
							<ul class="flow">
								<li>
									<span <c:if test="${data.ESTM_PSCD eq 'A001' }">class="active" onclick="movePage('${data.ESTM_NO }', '${data.ESTM_PSCD}','${data.ESTM_SECD}'); " style="cursor:pointer;"</c:if>>평가작성</span>
								</li>
								<li>
									<span <c:if test="${data.ESTM_PSCD eq 'A002' }">class="active" onclick="movePage('${data.ESTM_NO }', '${data.ESTM_PSCD}','${data.ESTM_SECD}'); " style="cursor:pointer;"</c:if>>평가공고</span>
								</li>
								<li>
									<span <c:if test="${data.ESTM_PSCD eq 'A003' || data.ESTM_PSCD eq 'A0031' || data.ESTM_PSCD eq 'A004'  || data.ESTM_PSCD eq 'A0041'}">class="active" onclick="movePage('${data.ESTM_NO }', '${data.ESTM_PSCD}','${data.ESTM_SECD}'); " style="cursor:pointer;"</c:if> >평가위원순위선정</span>
								</li>
								<li>
									<span <c:if test="${data.ESTM_PSCD eq 'A005'}">class="active" onclick="movePage('${data.ESTM_NO }', '${data.ESTM_PSCD}','${data.ESTM_SECD}'); " style="cursor:pointer;"</c:if>>평가중</span>
								</li>
								<li>
									<span <c:if test="${data.ESTM_PSCD eq 'C001'}">class="active" onclick="movePage('${data.ESTM_NO }', '${data.ESTM_PSCD}','${data.ESTM_SECD}'); " style="cursor:pointer;"</c:if>>평가종료</span>
								</li>
							</ul>
							<!-- 설명 -->
							<ul class="flow_tip">
								<li></li>
								<li>평가시작일시 : ${comFn:formatDate(data.TOTL_ESTM_ST_DT, 'yyyyMMddHHmmss', 'yyyy-MM-dd HH:mm')}</li>
								<li></li>
								<li>평가종료일시 : ${comFn:formatDate(data.TOTL_ESTM_END_DT, 'yyyyMMddHHmmss', 'yyyy-MM-dd HH:mm')}</li>
								<li></li>
							</ul>
						</c:forEach>
					</c:if>
				</div>
			</div>
		</div>
		<div class="ui-contents">
			<!-- 공지 -->
			<div class="box-board">
				<div class="ui-title">
					<h2 class="box-label">
						공지
					</h2>
					<a href="javascript:notiList('NOTI')" class="link-add">+ 더보기</a>
				</div>
	
				<ul class="list-board">
					<c:if test="${notiListTotCnt > 0}">
						<c:forEach var="data" items="${notiList}" varStatus="status">
							<li>
								<a onclick="detailInqire('${data.BBS_SECD }', '${data.BBS_SN}');">
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
						자주하는<br>
						질문
					</h2>
					<a href="javascript:notiList('FAQ')" class="link-add">+ 더보기</a>
				</div>
				<ul class="list-qa">
					<c:if test="${faqListTotCnt > 0}">
						<c:forEach var="data" items="${faqList}" varStatus="status">
							<li>
								<a onclick="detailInqire('${data.BBS_SECD }', '${data.BBS_SN}');">
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
	<!-- //Contents -->

	<!-- Footer -->
	<div class="layout-footer">
		<span class="footer-logo">

		</span>
		<div class="txt-footer">
			(07997) 서울특별시 양천구 목동동로 309 중소기업유통센터   대표자 : 정진수 / 대표전화 : 02-6678-9000 / 사업자등록번호 : 107-81-53660

			<div class="txt-copy">
				Small & Medium Business Distribution Center. All Rights Reserved.
			</div>
		</div>
		<button type="button" class="btn-download" onclick="downloadManual();">메뉴얼 다운로드</button>
	</div>
	<!-- //Footer -->
</div>


<%-- DETAIL FORM --%>
<form id="detailFrm" method="POST">
	<input type="hidden" name="resourceName" />
	<input type="hidden" name="P_BBS_SECD" />
	<input type="hidden" name="P_BBS_SN" />
</form>

<form id="estmDetailFrm" method="POST">
	<input type="hidden" name="resourceName" />
	<input type="hidden" name="P_ESTM_NO" />
	<input type="hidden" name="P_ESTM_PSCD" />
</form>

<%-- POPUP FORM --%>
<form id="popupFrm" method="POST"> 
	<input type="hidden" name="resourceName" value="${comFn:nvl(param.resourceName, resourceName)}" />
</form>

<form id=manualFrm method="POST">
</form>

<form id="downloadFrm" method="POST">
 	<input type="hidden" name="P_FILE_SN">
 	<input type="hidden" name="P_FILE_GRP_NO">
</form>

<form id="menuLeftMoveFrm" method="post">
	<input type="hidden" name="resourceName" value="${param.resourceName}">
	<input type="hidden" name="P_CHRGR_ID_S" value="${loginResult.USR_ID}">
	<input type="hidden" name="P_CHRGR_NM_S" value="${loginResult.USR_NM}">	
</form>

<form id="logOutFrm" method="post">
</form>