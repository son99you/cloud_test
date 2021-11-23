<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 정보관리 > 평가분야관리 상세
 *
 * <pre>
 * sytm
 *    |_ estmSpheMngDetail.jsp
 * 
 * </pre>
 * @date : 2021. 03. 23
 * @version : 1.0
 * @author : 은우소프트
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %> 
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %> 

<script type="text/javascript" src="${jsPath}/ipro/views/sytm/estmSpheMngDetail.js"></script>

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
					<li style="font-size: 30px; font-weight: 500;">평가분야관리 상세</li>
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
	<form id="detailFrm" name="detailFrm" method="POST">
		<input type="hidden" id="resourceName" name="resourceName" value="${comFn:nvl(param.resourceName, resourceName)}">
		<input type="hidden" id="resourceDesc" name="resourceDesc" value="${param.resourceDesc}">
		<input type="hidden" id="P_PAGE_NO" name="P_PAGE_NO" value="${comFn:nvl(param.P_PAGE_NO, 1)}">
		<input type="hidden" id="P_PAGE_SIZE" name="P_PAGE_SIZE" value="${comFn:nvl(param.P_PAGE_SIZE, 10)}">
		<input type="hidden" id="P_ESTM_SPHE_SECD" name="P_ESTM_SPHE_SECD" value="${estmSpheMngDetail.ESTM_SPHE_SECD }">
		
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
					<col width="*">
				</colgroup>
				<tbody>
					<tr>
						<th>평가분야구분명</th>
						<td colspan="3">${estmSpheMngDetail.ESTM_SPHE_SENM }</td>
					</tr>
					<!-- <tr>
						<th>
							111							
						</th>
						<td colspan="3">
							<div><input type="text"></div>
							<div><button class="btn-bottom type-b">test</button></div>
						</td>
					</tr> -->
				</tbody>
			</table>
		</div>
		
		<!-- bottom button -->
		<div class="bottom-buttons">
			<a href="javascript:" class="btn-bottom type-b" id="deleteBtn">삭제</a>
			<a href="javascript:" class="btn-bottom type-b" id="updtBtn">수정</a>
			<a href="javascript:" class="btn-bottom type-a" id="listBtn">목록</a>
		</div>
		<!-- //bottom button -->
	
	</form>
	
</div>	
<!-- //Detail -->


<!-- LIST FORM -->
<form id="listFrm" method="POST">
	<input type="hidden" name="resourceName" value="${comFn:nvl(param.resourceName, resourceName)}">
</form>