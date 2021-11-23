<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 정보관리 > 업체정보관리 상세
 *
 * <pre>
 *  sytm 
 *    |_ vendMngeDetail.jsp
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

<script type="text/javascript" src="${jsPath}/ipro/views/sytm/vendMngeDetail.js"></script>

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
					<li style="font-size: 30px; font-weight: 500;">업체정보관리 상세</li>
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
		<input type="hidden" name="resourceName" value="${comFn:nvl(param.resourceName, resourceName)}" >
		<input type="hidden" name="P_VEND_REG_NO" value="${vendMngeDetail.VEND_REG_NO }">
	
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
				<tr>
					<th>업체등록번호</th>
					<td>${vendMngeDetail.VEND_REG_NO}</td>
					<th>사업자번호</th>
					<td>${comFn:formatBizNumber(vendMngeDetail.BIZRNO)}</td>
				</tr>
				<tr>
					<th>업체명</th>	
					<td>${vendMngeDetail.VEND_NM}</td>	
					<th>대표자명</th>
					<td>${vendMngeDetail.RPRS_NM}</td>
				</tr>
				<tr>
					<th>업태</th>
					<td>${vendMngeDetail.BCNM}</td>			   		                    
					<th>업종</th>
					<td>${vendMngeDetail.BTNM}</td>			   		                    
		        </tr>
				<tr>
					<th>전화번호</th>
					<td>${comFn:formatPhoneNumber(vendMngeDetail.TEL_NO)}</td>
					<th>이메일주소</th>
					<td>${vendMngeDetail.EMAL_ADDR}</td>
				</tr>
				<tr>
					<th>주소</th>
					<td colspan="3">	${vendMngeDetail.ADDR_NM }</td>
				</tr>
			</table>
		</div>
			
		<!-- bottom button -->
		<div class="bottom-buttons">
			<a href="javascript:"class="btn-bottom type-a" id="listBtn">목록</a>
		</div>
		<!-- // bottom button -->
	</form>
</div>
<!-- //Detail -->

<!-- LIST FORM -->
<form id="listFrm" method="POST"> 
	<input type="hidden" name="resourceName" value="${comFn:nvl(param.resourceName, resourceName)}" >
</form>
