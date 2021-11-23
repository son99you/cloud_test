<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 정보관리 > 사용자관리 상세
 *
 * <pre>
 *  sytm 
 *    |_ userMgrDetail.jsp
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

<script type="text/javascript" src="${jsPath}/ipro/views/sytm/userMgrDetail.js"></script>

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
					<li style="font-size: 30px; font-weight: 500;">사용자관리 상세</li>
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
		<input type="hidden" id="P_USR_ID" name="P_USR_ID" value="${userMgrDetail.USR_ID }">
		<!-- 로그에 이전권한에 대한 정보가 들어가야되기 때문에 추가 -->
		<input type="hidden" id="P_BEFORE_AUTH_NM" name="P_BEFORE_AUTH_NM" value="${userMgrDetail.AUTH_NM }">
		<input type="hidden" id="P_AFTER_AUTH_NM" name="P_AFTER_AUTH_NM" value="${userMgrDetail.AUTH_NM }">
		<input type="hidden" id="P_LOG_CHANGE_USR_NM" name="P_LOG_CHANGE_USR_NM" value="${userMgrDetail.USR_NM }"><!-- 변경되는 사용자명 -->
		<input type="hidden" name="P_LOG_INSERT_USR_NM" value="${sessionScope.loginResult.USR_NM}">
		
		
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
						<th>사용자ID</th>
						<td>${userMgrDetail.USR_ID }</td>
						<th>사번</th>
						<td>${userMgrDetail.EMPL_NO }</td>
					</tr>
					<tr>
						<th>사용자명</th>
						<td>${userMgrDetail.USR_NM }</td>
						<th>부서코드</th>
						<td>${userMgrDetail.DEPT_NO }</td>
					</tr>
					<tr>
						<th>부서명</th>
						<td>${userMgrDetail.DEPT_NM }</td>
						<th>직위</th>
						<td>${userMgrDetail.OFPS_NM }</td>
					</tr>
					<tr>
						<th>이메일</th>
						<td>${userMgrDetail.EMAL_ADDR }</td>
						<th>전화번호</th>
						<td>${userMgrDetail.TEL_NO }</td>
					</tr>
					<tr>
						<th>휴대폰번호</th>
						<td colspan="3">${userMgrDetail.CP_NO }</td>
					</tr>
					<tr>	
						<th><i class="icon-necessary"></i>사용자권한</th>
						<td  colspan="3">
							<comTag:comCmcdCdValueComboBox id="P_AUTH_ID" name="P_AUTH_ID" selectKey="${userMgrDetail.AUTH_ID}" cdId="AUTH_ID"  headerKey="" headerValue="전체" className="component-select"/>
						</td>
					</tr>
					<%-- <tr>
						<th><i class="icon-necessary"></i>승인권한여부</th>
						<td colspan="3">
							<comTag:cmmnCdValueRadio name="P_APPR_AUTH_YN" className="component-radio" selectKey="${comFn:nvl(userMgrDetail.APPR_AUTH_YN,'N')}"  list="{'Y':'예', 'N':'아니오'}"/>
						</td>
					</tr> --%>
				</tbody>
			</table>
		</div>
		
		<!-- bottom button -->
		<div class="bottom-buttons">
			
			<c:if test="${loginResult.AUTH_ID eq '1' }">
				<a href="javascript:" class="btn-bottom type-b" id="saveBtn">저장</a>
			</c:if>
			
			<a href="javascript:" class="btn-bottom type-a" id="listBtn">목록</a>
		</div>
		<!-- //bottom button -->
	</form>
</div>	
<!-- //Detail -->

<!-- LIST FORM -->
<form id="listFrm" method="POST"> 
	<input type="hidden" id="resourceName" name="resourceName" value="${param.resourceName}">
	<input type="hidden" id="resourceDesc" name="resourceDesc" value="${param.resourceDesc}">
</form>

<!-- VIEW FORM -->
<form id="viewFrm" method="POST" > 
	<input type="hidden" id="resourceName" name="resourceName" value="${param.resourceName}">
	<input type="hidden" id="resourceDesc" name="resourceDesc" value="${param.resourceDesc}">
	<input type="hidden" name="P_USR_ID_S">
</form>