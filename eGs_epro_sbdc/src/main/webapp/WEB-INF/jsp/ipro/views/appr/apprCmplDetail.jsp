<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 결재관리 > 결재완료 상세
 *
 * <pre>
 * appr 
 *    |_ apprCmplDetail.jsp
 * 
 * </pre>
 * @date : 2018. 11. 06
 * @version : 1.0
 * @author : 은우소프트 
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %> 
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %> 

<script type="text/javascript" src="${jsPath}/ipro/views/appr/apprCmplDetail.js"></script> 

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
					<li style="font-size: 30px; font-weight: 500;">결재완료 상세</li>
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
	<form id="" name="" method="POST">
		<input type="hidden" id="P_USR_ID" name="P_USR_ID" value="${userMgrDetail.USR_ID }">
		
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
			<a href="javascript:" class="btn-bottom type-a" id="listBtn">목록</a>
		</div>
		<!-- //bottom button -->
	
	</form>
	
</div>	
<!-- //Detail -->


<form id="listFrm" method="POST">
	<input type="hidden" name="resourceName" value="${ param.resourceName }">
</form>

<form id="detailFrm" target="doc_frame" method="POST">
	<input type="hidden" name="P_BEFFAT_PBLANC_NO" value="${tApprMstList[0].APRDC_INTL_NO }">
	<input type="hidden" name="resourceName" value="${ param.resourceName }">
</form>

<form id="docuFrm" method="POST" action="${contextPath}/info/infoAppConsultDetail.do"> 
	<input type="hidden" id="P_APRDC_NO_S" name="P_APRDC_NO_S" value="${tApprMstList[0].APRDC_NO}">
	<input type="hidden" id="P_APRDC_SE_S" name="P_APRDC_SE_S" value="${tApprMstList[0].APRDC_SE}">
	<input type="hidden" name="resourceName" value="${ param.resourceName }">
</form>