<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 정보관리 > 사용자점보 상세
 *
 * <pre>
 *  user 
 *    |_ estmCmtmInfoDetail.jsp
 * 
 * </pre>
 * @date : 2017. 06. 14
 * @version : 1.0
 * @author : 은우소프트 이주연
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %> 
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %> 

<script type="text/javascript" src="${jsPath}/opro/views/user/estmCmtmInfoDetail.js"></script>

<!-- 네비게이션 -->
<div class="area-detail">
	<div class="table-detail">
		<!-- Top -->
		<div class="nav_sec">

			<div class="btn-help" style="display:none;">
				<a href="javascript:helpPopup();">도움말</a>
			</div>
			
			<div class="option-left">
				<ul class="location">
					<li style="font-size: 30px; font-weight: 500;">사용자정보 상세</li>
				</ul>
			</div>
			
			<div class="option-right">
				<ul class="location">
					<li class="home"><a href="#">홈</a></li>
					<li><a href="#">${myMenuList.bigMenuNm}</a></li>
					<li><a href="#">${myMenuSubList.smallMenuNm}</a></li>
				</ul>
			</div>
		</div>
	</div>
</div>
<!--//네비게이션 --> 

<!-- Detail -->
<div class="area-detail">
	<form>
		<input type="hidden" id="P_ESTM_CMTM_NO" name="P_ESTM_CMTM_NO" value="${sessionScope.loginResult.USR_ID}">
		
		<%-- <input type="text" name="P_USR_ID" value="${sessionScope.loginResult.USR_ID}"> --%>
		
		<div class="table-detail">
			<!-- Top -->
			<div class="top-detail">
				<div class="type-fleft">
					<div class="contents-label">기본정보</div>
				</div>
			</div>
			<!-- //Top -->
			
			<table class="component-detail-table type-line-none">
				<colgroup>
					<col width="15%">
					<col width="35%">
					<col width="15%">
					<col width="35%">
				</colgroup>
				<tbody>
					<tr>
						<th>평가위원번호</th>
						<td>${estmCmtmInfoDetail.ESTM_CMTM_NO }</td>
						<th>평가위원명</th>
						<td>${estmCmtmInfoDetail.ESTM_CMTM_NM }</td>
					</tr>
					<tr>
						<th>휴대전화번호</th>
						<td>${estmCmtmInfoDetail.TEL_NO }</td>
						<th>전화번호</th>
						<td>${estmCmtmInfoDetail.TEL_NO }</td>
					</tr>
					<tr>
						<th>이메일</th>
						<td>${estmCmtmInfoDetail.EMAL }</td>
						<th></th>
						<td></td>
					</tr>
					<tr>
						<th>대분류</th>
						<td>${estmCmtmInfoDetail.LLF_SECD_NM }</td>
						<th>내역</th>
						<td>${estmCmtmInfoDetail.CNTN_SECD_NM  }</td>
					</tr>
					<tr>
						<th>중분류</th>
						<td>${estmCmtmInfoDetail.MLF_SECD_NM }</td>
						<th>소분류</th>
						<td>${estmCmtmInfoDetail.SLF_SECD_NM }</td>
					</tr>
				</tbody>
			</table>
		</div>
	</form>
</div>	
<!-- //Detail -->

<!-- LIST FORM -->
 <form id="listFrm" method="POST"> 
	<input type="hidden" id="resourceName" name="resourceName" value="${comFn:nvl(param.resourceName, resourceName)}">
	<input type="hidden" id="resourceDesc" name="resourceDesc" value="${param.resourceDesc}">
</form>

<!-- VIEW FORM -->
<form id="viewFrm" method="POST" > 
	<input type="hidden" id="resourceName" name="resourceName" value="${param.resourceName}">
	<input type="hidden" id="resourceDesc" name="resourceDesc" value="${param.resourceDesc}">
	<input type="hidden" name="P_USR_ID_S">
</form>