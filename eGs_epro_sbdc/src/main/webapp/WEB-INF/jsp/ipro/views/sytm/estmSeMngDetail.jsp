<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 정보관리 > 평가구분관리 상세
 *
 * <pre>
 * sytm 
 *    |_ estmSeMngDetail.jsp
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

<script type="text/javascript" src="${jsPath}/ipro/views/sytm/estmSeMngDetail.js"></script>

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
					<li style="font-size: 30px; font-weight: 500;">평가구분관리 상세</li>
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
		<input type="hidden" name="resourceDesc" value="${ param.resourceDesc}">
		<input type="hidden" name="resourceName" value="${comFn:nvl(param.resourceName, resourceName)}">
		<input type="hidden" name="P_ESTM_SECD" id="P_ESTM_SECD" value="${estmSeMngDetail.ESTM_SECD }">
		<input type="hidden" id="P_ESTM_SECD_TRANS" value="${P_ESTM_SECD_TRANS}">
		<input type="hidden"  id="resultCode" name="resultCode" value="${resultCode}">
		<input type="hidden" id="P_CLC_RUL" name="P_CLC_RUL" value="${estmSeMngDetail.CLC_RUL}">
		
		
		
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
					<col width="150">
					<col width="*">
					<col width="150">
					<col width="*">
				</colgroup>
				<tbody>
					<tr>
						<th>평가구분</th>
						<td>${estmSeMngDetail.ESTM_SENM }</td>
						<th>최고/최저점제외여부</th>
						<td>${estmSeMngDetail.MXMN_SCR_EXCP_YN_NM }</td>
					</tr>
					<tr>
						<th>평가대상구분</th>
						<td>${estmSeMngDetail.ESTM_OBJ_SECD_NM }</td>
						<th>평가부서</th>
						<td>${estmSeMngDetail.ESTM_DEPT_NM }</td>
					</tr>
				</tbody>
			</table>
		</div>
		
		
		<div class="table-detail">
			<!-- Top -->
			<div class="top-detail">
				<div class="type-fleft">
					<div class="contents-label">심사위원 서명파일</div>
				</div>
				<!-- //Top -->
			</div>
			
			<table class="component-detail-table"> 
				<colgroup>
					<col width="30%">
					<col width="70%">
				</colgroup>
				<thead>
					<tr>
						<th>문서명</th>
						<th>파일첨부</th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${empty estmSignFileList}">
						<tr>
							<td colspan="2" class="txt-center">검색 조건에 해당하는 데이터가 존재하지 않습니다.</td>
						</tr>
					</c:if>
                	<c:if test="${not empty estmSignFileList }">
                  		<c:forEach items="${estmSignFileList}" var="data" varStatus="status">
                  			<tr>
                  				<th>${data.FILE_DOC_NM}</th>
                  				<td><a href="javascript:download('${data.FILE_GRP_NO}', '${data.FILE_SN}');">${data.SYS_FILE_NM }</a></td>
                  			</tr>
	                   	</c:forEach>
                   	</c:if>
				</tbody>
			</table> 
		</div>
				

		<div class="table-detail">
			<!-- Top -->
			<div class="top-detail">
				<div class="type-fleft">
					<div class="contents-label">평가절차</div>
				</div>
			</div>
			<!-- //Top -->

			<table class="component-detail-table ">
				<colgroup>
					<col width="88">
					<col width="100px;">
					<col width="auto;">
					<col width="100px;">
					<col width="300px;">
				</colgroup>
				<thead>
				<tr>
					<th>순번</th>
					<th>평가절차구분</th>
					<th>평가절차명</th>
					<th>평가서식</th>
					<th>평가서식명</th>
				</tr>

				</thead>
				<tbody>
				<c:forEach var="data" items="${estmSeProcdList}" varStatus="status">
				<input type="hidden" name="D_ESTM_PROCD_SEQ" value="${data.ESTM_PROCD_SEQ }"/>
				<input type="hidden" name="D_ESTM_PROCD_SECD" value="${data.ESTM_PROCD_SECD }"/>
				<input type="hidden" name="D_ESTM_PROCD_NM" value="${data.ESTM_PROCD_NM }"/>
					<tr>
						<td class="txt-center">${data.RNUM}</td>
						<td class="txt-center">${data.ESTM_PROCD_SECD_NM}</td>
						<td>${data.ESTM_PROCD_NM}</td>
						<td class="txt-center">
							<a type="button" href="javascript: estmSeMngFrmPopup('${data.ESTM_FRM_NO}','${data.ESTM_PROCD_SEQ }');" name="estmSeMngFrmPopup" class="component-button-s type-a">보기</a>
						</td>
						<td>${data.ESTM_FRM_NM}</td>
					</tr>
				</c:forEach>
				</tbody>
			</table>

		</div>
		
		<div class="table-detail">
			<!-- Top -->
			<div class="top-detail">
				<div class="type-fleft">
					<div class="contents-label">평가결과산술식</div>
				</div>
				<div class="type-fright">
					<button type="button" class="component-button-s type-add" id="addSanSoolStrBtn" name="addBtn">산술추가</button>
					<button type="button" class="component-button-s type-add" id="addProcdBtn" name="addBtn">절차추가</button>
					<button type="button" class="component-button-s type-del" id="delSanSoolBtn" name="delBtn">산술식삭제</button>
				</div>
			</div>
			<!-- //Top -->

			<table class="component-detail-table type-line-none">
				<colgroup>
					<col width="150">
					<col width="*">
				</colgroup>
				<tbody>
					<tr >
						<th>산술식</th>
						<td id="sansool"></td>
					</tr>

				</tbody>
			</table>
		</div>
		
		
		
		<!-- bottom button -->
		<div class="bottom-buttons">
			<a href="javascript:" class="btn-bottom type-b" id="sansoolSaveBtn">산술저장</a>
			<a href="javascript:" class="btn-bottom type-b" id="updtBtn">수정</a>
			<a href="javascript:" class="btn-bottom type-b" id="delBtn">삭제</a>
			<a href="javascript:" class="btn-bottom type-a" id="listBtn">목록</a>
		</div>
		<!-- //bottom button -->
	</form>	
</div>

<form id="listFrm" method="POST">
	<input type="hidden" name="resourceDesc" value="${ param.resourceDesc}">
	<input type="hidden" name="resourceName" value="${comFn:nvl(param.resourceName, resourceName)}">
</form> 

<form id="popupFrm" method="POST"> 
	<input type="hidden" name="resourceDesc" value="${ param.resourceDesc}">
	<input type="hidden" name="resourceName" value="${comFn:nvl(param.resourceName, resourceName)}">
	<input type="hidden" name="P_FRM_GBN" >
	<input type="hidden" name="P_ESTM_SECD" id="P_ESTM_SECD" value="${estmSeMngDetail.ESTM_SECD }">
	<input type="hidden" name="P_ESTM_PROCD_SEQ" id="P_ESTM_PROCD_SEQ">
	<input type="hidden" name="P_ESTM_FRM_NO" id="P_ESTM_FRM_NO">
	
</form>

<!-- DOWNLOAD FORM -->
<form id="downloadFrm" method="POST">
	<input type="hidden" name="P_FILE_SN">
	<input type="hidden" name="P_FILE_GRP_NO">
</form>