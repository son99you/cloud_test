<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 결재관리 > 결재등록
 *
 * <pre>
 * appr 
 *    |_ apprRegList.jsp
 * 
 * </pre>
 * @date : 2018. 03. 08
 * @version : 1.0
 * @author : 은우소프트 맹경열
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %> 
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %> 

<script type="text/javascript" src="${jsPath}/ipro/views/appr/apprRegList.js"></script>
<script type="text/javascript" src="${jsPath}/ipro/views/info/jquery.tablednd.js"></script>

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
					<li style="font-size: 30px; font-weight: 500;">결재등록 목록</li>
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

<div class="area-detail">
	<form id="listFrm" class="listFrm" method="POST">
		<input type="hidden" id="resourceName" name="resourceName" value="${param.resourceName}">
		<input type="hidden" id="resourceDesc" name="resourceDesc" value="${param.resourceDesc}">
		<input type="hidden" id="P_APPL_NO" name="P_APPL_NO" value="${P_APPL_NO}">
		<input type="hidden" name="P_FIRST"  id ="P_FIRST">
		<input type="hidden" name="P_APPL_SE" id="P_APPL_SE" value="${P_APPL_SE }">
	
		<div class="wrap-box-move">
			<div class="left-box">
				<div class="top-detail">
					<div class="type-fleft">
						<div class="contents-label">결재선정보</div>
					</div>
					<div class="type-fright">
						<a href="javascript:" class="component-button-s type-s" id="registBtn">결재선등록</a>
					</div>
				</div>
				<div class="box-move-list type-fleft" style="width: 400px;">
					<table class="component-table">
						<colgroup>
							<col width="50">
							<col width="*">
							<col width="100">
						</colgroup>
						<thead>
							<tr>
								<th class="txt-center">순번</th>
						    	<th>결재선명</th>
						    	<th class="txt-center">삭제</th>
							</tr>
						</thead>
						<tbody>
							<c:if test="${applMstListTotCnt > 0}">
								<c:forEach var="data" items="${applMstList}" varStatus="status">
									<tr onclick="applMstDetail('${data.APPL_NO}','${data.APPL_NM}', '${data.APPL_SE}' )">
										<td class="txt-center">${status.count}</td>
										<td><a href="#">&nbsp;${data.APPL_NM}</a></td>
										<td class="txt-center"><button type="button" class="component-button-s type-del" onclick="pageObj.applMstDelete('${data.APPL_NO}');">삭제</button></td>
									</tr>
								</c:forEach> 
							</c:if>
			           	</tbody>
					</table>					
				</div>
			</div>
			
			<div class="right-box">
				<div class="top-detail">
					<div class="type-fleft">
						<div class="contents-label type-fright">
							결재자정보
							<br><br>
							<c:if test="${not empty P_APPL_NM}"><span style="color: red;">(결재선명 : ${P_APPL_NM})</span></c:if>
							<input type="hidden" id="P_APPL_NM" name="P_APPL_NM" value="${P_APPL_NM}">
						</div>
					</div>
					
					<div class="type-fright">
						<a href="javascript:" class="component-button-s type-add" id="popupBtn">추가</a>
					</div>
					
				</div>
				
				<div class="box-move-list type-fright" style="width: 560px;">
					<table class="component-table">
						<colgroup>
							<col width="50">
							<col width="100">
							<col width="120">
							<col width="*">
							<col width="100">						
						</colgroup>
						<thead>
							<tr>
								<th class="txt-center">순번</th>
						    	<th class="txt-center">구분</th>
						    	<th class="txt-center">성명</th>
						    	<th>부서명</th>
						    	<th class="txt-center">삭제</th>
							</tr>
						</thead>
						<tbody id="userFrame">
							<c:if test="${apprAprpListTotCnt > 0}">
								<c:forEach var="data" items="${apprAprpList}" varStatus="status">
									<tr>
										<td class="txt-center">${status.count}</td>
										<td class="txt-center">
											<c:if test="${data.APPR_AUCD eq 'S'}">결재</c:if>
											<c:if test="${data.APPR_AUCD eq 'C'}">참조</c:if>
											<c:if test="${data.APPR_AUCD eq 'A'}">합의</c:if>
											<input type="hidden" name="P_APPR_AUCD" value="${data.APPR_AUCD}">
										</td>
										<td class="txt-center">
											${data.APPR_USER_NM}
											<input type="hidden" name="P_APRP_SN" value="${data.APRP_SN}">
											<input type="hidden" name="P_APPR_USER_ID" value="${data.APPR_USER_ID}">
											<input type="hidden" name="P_APPR_USER_NM" value="${data.APPR_USER_NM}">
											<input type="hidden" name="P_ORG_NM" value="${data.APPR_DEPT_NM}">
											<input type="hidden" name="P_TR_NUM" value="${data.APRP_SN}">
											<input type="hidden" name="P_APRP_ORD_SN" value="${status.count}"> 
										</td>
										<td>
											${data.APPR_DEPT_NM}
										</td>
										<td class="txt-center">
											<label class="component-checkbox">
												<input type="checkbox" name="P_DEL_CHK" onclick="applAprpDel(this);">
												<i></i>
											</label>
											<input type="hidden" name="P_DEL_AT" value="N">
										</td> 
									</tr>
								</c:forEach>  
							</c:if> 
			           	</tbody> 
					</table>
				</div>
			</div>
		</div>
		
		<div class="bottom-buttons">
			<a href="javascript:" class="btn-bottom type-b" id="saveBtn" style="float: right; margin-top: 12px;">저장</a>
		</div>
	</form>
</div>

<%-- DETAIL FORM --%>
<form id="deleteFrm" method="POST"> 
	<input type="hidden" name="resourceName" value="${ param.resourceName }">
	<input type="hidden" id="resourceDesc" name="resourceDesc" value="${param.resourceDesc}">
	<input type="hidden" name="P_APPL_NO" id="P_APPL_NO">
</form>

<!-- POPUP FORM -->
<form id="popupFrm" method="POST"> 
	<input type="hidden" name="resourceName" value="${ param.resourceName }">
	<input type="hidden" name="setMulti" value="Y">
	<input type="hidden" id="P_APPL_SE" name="P_APPL_SE">
</form>

<!-- DETAIL FORM -->
<form id="detailFrm" method="POST">
	<input type="hidden" id="resourceName" name="resourceName" value="${param.resourceName}">
	<input type="hidden" id="resourceDesc" name="resourceDesc" value="${param.resourceDesc}">
	<input type="hidden" name="P_APPL_NO" id="P_APPL_NO">
	<input type="hidden" id="P_APPL_SE" name="P_APPL_SE">
</form>