<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 평가관리 > 권한관리 상세
 *
 * <pre>
 * estm 
 *    |_ authMgrDetail.jsp
 * 
 * </pre>
 * @date : 2018. 08. 22
 * @version : 1.0
 * @author : 은우소프트 은잔디
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld"%>
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld"%>

<script type="text/javascript" src="${jsPath}/ipro/views/sytm/authMgrDetail.js"></script>
<script type="text/javascript" src="/raonkeditor/js/raonkeditor.js"></script>

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
					<li style="font-size: 30px; font-weight: 500;">권한관리 상세</li>
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
				<div class="table-detail">
					<!-- Top -->
					<div class="top-detail">
						<div class="type-fleft">
							<div class="contents-label">기본정보</div>
						</div>

						<div class="type-fright">

							<!-- <a href="javascript:" class="component-button-s type-s">저장</a> -->
							<!-- <a href="javascript:" class="component-button-s type-s">목록</a> -->
						</div>
					</div>
					<!-- //Top -->

					<table class="component-detail-table type-line-none">
						<colgroup>
							<col width="150">
							<col width="*">
							<col width="150">
							<col width="*">
						</colgroup>
						<tbody>
						<tr>
							<th>권한ID</th>
							<td>${menuAuthMgrDetail.AUTH_ID }</td>
							<th>권한명</th>
							<td>${menuAuthMgrDetail.AUTH_NM }</td>
						</tr>
						</tbody>
					</table>
				</div>
				<div class="table-detail">
					<!-- Top -->
					<div class="top-detail">
						<div class="type-fleft">
							<div class="contents-label">메뉴목록</div>
						</div>
					</div>
					<!-- //Top -->
					
					<div style="overflow-x: scroll;">
						<table class="component-detail-table">
							<colgroup>
								<col width="30%">
								<col width="30%">
								<col width="40%">
							</colgroup>
							<thead>
								<tr>
									<th>권한명</th>
									<th>메뉴ID</th>
									<th>메뉴명</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="data" items="${menuAuthMgrList}" varStatus="status">
									<c:if test="${data.SELECT_AT eq 'Y' }">
									<tr class="txt-center">
										<%-- <td class="txt-center">${data.RNUM}</td> --%>
										<td class="txt-center">${data.AUTH_NM}</td>
										<td>${data.MENU_ID}</td>
										<td class="txt-center">
											${data.MENU_NM}
										</td>
									</tr>
									</c:if>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>

				<!-- bottom button -->
				<div class="bottom-buttons">
					<a href="javascript:" id="updtBtn" class="btn-bottom type-b">수정</a>
					<a href="javascript:" id="listBtn" class="btn-bottom type-a">목록</a>
				</div>
				<!-- //bottom button -->
			</div>
			<!-- //Detail -->
		</div>

<form id="listFrm" method="POST">
	<input type="hidden" name="resourceName" value="${comFn:nvl(param.resourceName, resourceName)}" >
</form> 

<form id="downloadFrm" method="POST">
	<input type="hidden" name="P_FILE_GRP_NO" value="" >
	<input type="hidden" name="P_FILE_SN" value="" >
</form> 

<form id="updtFrm" method="POST">
	<input type="hidden" name="resourceName" value="${comFn:nvl(param.resourceName, resourceName)}" >
	<input type="hidden" name="P_AUTH_ID" id="P_AUTH_ID" value="${menuAuthMgrDetail.AUTH_ID}">
</form>

<form id="ubiPopupFrm" method="POST" > 
	<input type="hidden" name="P_JRF" value=""/>
	<input type="hidden" name="P_ARG" value=""/>
	<input type="hidden" name="P_FRM_NO" value="${contFormDetail.FRM_NO}">
</form>

<form id="popupFrm" method="POST"> 
	<input type="hidden" name="resourceName" value="${comFn:nvl(param.resourceName, resourceName)}" >
	<input type="hidden" name="P_FILE_GRP_NO" >
</form>

