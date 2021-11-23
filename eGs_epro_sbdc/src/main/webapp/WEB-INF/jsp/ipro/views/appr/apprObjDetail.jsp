<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 결재관리 > 결재대상 상세
 *
 * <pre>
 * appr
 *    |_ apprObjDetail.jsp
 * 
 * </pre>
 * @date : 2017. 06. 14
 * @version : 1.0
 * @author : 은우소프트 은잔디
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %> 
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %> 

<script type="text/javascript" src="${jsPath}/ipro/views/appr/apprObjDetail.js"></script> 

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
					<li style="font-size: 30px; font-weight: 500;">결제대상 상세</li>
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
		<input type="hidden" id="P_APPR_NO" name="P_APPR_NO" value="${apprObjMstDetail.APPR_NO}">
		<input type="hidden" id="P_APRP_SN" name="P_APRP_SN" value="${apprObjMstDetail.APRP_SN}">
		<input type="hidden" id="P_APPR_YN" name="P_APPR_YN">
		
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
			         	<th>결재번호</th>
			            <td>${apprObjMstDetail.APPR_NO }</td>
			        	<th>결재상태</th>
			            <td>${apprObjMstDetail.APPR_STCD }</td>
			        </tr>
					<tr>
			        	<th>결재명</th>
			            <td colspan="3">${apprObjMstDetail.APPR_NM }</td>
			         </tr>
			         <tr>   
			         	<th>요청자</th>
			            <td>${apprObjMstDetail.REGR_NM }</td>
			        	<th>요청일시</th>
			            <td>${comFn:formatDate(apprObjMstDetail.REG_DT, 'yyyyMMddhhmmss', 'yyyy-MM-dd')}</td>
			        </tr> 
				</tbody>
			</table>
		</div>
		
		<div class="table-detail">
			<!-- Top -->
			<div class="top-detail">
				<div class="type-fleft">
					<div class="contents-label">결재자정보</div>
				</div>
			</div>
			<!-- //Top -->
			
			<table class="component-detail-table ">
				<colgroup>
					<col width="10%">
					<col width="20%">
					<col width="*">
					<col width="15%">
					<col width="15%">
				</colgroup>
				<thead>
					<tr>
						<th class="txt-center">순번</th>
			        	<th class="txt-center">성명</th>
			        	<th>부서</th>
			        	<th class="txt-center">일자</th>
			        	<th class="txt-center">결재여부</th>
					</tr>
				</thead>
				 <tbody id="apprShowTbdy">
		        	<c:forEach items="${apprObjAprpList }" var="data" varStatus="status">
			        	<tr>
			        		<td class="txt-center">${status.count}</td>
			        		<td class="txt-center">${data.APPR_USER_NM }</td>
			        		<td>${data.APPR_DEPT_NM }</td>
			        		<td class="txt-center">${comFn:formatDate(data.REG_DT, 'yyyyMMddhhmmss', 'yyyy-MM-dd')}</td>
			        		<td class="txt-center">
			        			<c:if test="${comFn:nvl(data.APPR_YN, 'N') eq 'Y'}">예</c:if>
			        			<c:if test="${comFn:nvl(data.APPR_YN, 'N') ne 'Y'}">아니오</c:if>
			        		</td>
			        	</tr>
		        	</c:forEach>
		        </tbody>
			</table>
		</div>
		
		<!-- bottom button -->
		<div class="bottom-buttons">
			<c:if test="${apprObjMstDetail.APPR_TRGET eq 'Y' }">
           		<button type="button" class="btn-bottom type-b" id="apprBtn">승인</button>
           		<button type="button" class="btn-bottom type-b" id="rtrnBtn">반려</button>
           	</c:if> 
			<a href="javascript:" class="btn-bottom type-a" id="listBtn">목록</a>
		</div>
		<!-- //bottom button -->
	
	</form>
	
</div>	
<!-- //Detail -->


<!-- LIST FORM -->
<form id="listFrm" method="POST">
	<input type="hidden" name="resourceDesc" value="${ param.resourceDesc}">
	<input type="hidden" name="resourceName" value="${comFn:nvl(param.resourceName, resourceName)}">
</form>

<!-- POPUP FORM -->
<form id="popupFrm" method="POST" action="${contextPath}/info/popup/infoApprRsnRegist.do">
	<input type="hidden" id="P_APPR_NO" name="P_APPR_NO" value="${apprObjMstDetail.APPR_NO}">
	<input type="hidden" id="P_SEQ" name="P_SEQ" value="${apprObjMstDetail.SEQ}">
	<input type="hidden" id="P_APPR_YN" name="P_APPR_YN">
	<input type="hidden" id="P_JOB_SE" name="P_JOB_SE" value="${apprObjMstDetail.JOB_SE}">
</form>

<!-- APPR FORM -->
<form id="apprFrm" method="POST">
	<input type="hidden" name="resourceName" value="${ param.resourceName }">
	<input type="hidden" id="P_APPR_NO" name="P_APPR_NO" value="${apprObjMstDetail.APPR_NO}">
	<input type="hidden" id="P_APRP_SN" name="P_APRP_SN" value="${apprObjMstDetail.APRP_SN}">
	<input type="hidden" id="P_APPR_YN" name="P_APPR_YN">
	<input type="hidden" id="P_APPR_KEY1" name="P_APPR_KEY1" value="${apprObjMstDetail.APPR_KEY1}">
	<input type="hidden" id="P_APPR_KEY2" name="P_APPR_KEY2" value="${apprObjMstDetail.APPR_KEY2}">
	<input type="hidden" id="P_APPR_KEY3" name="P_APPR_KEY3" value="${apprObjMstDetail.APPR_KEY3}">
</form>