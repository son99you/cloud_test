<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 메인 > 평가진행현황 상세
 *
 * <pre>
 * recr 
 *    |_ recrAnncDetail.jsp
 * 
 * </pre>
 * @date : 2017. 06. 13
 * @version : 1.0
 * @author : 은우소프트 이주연
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %> 
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %>

<script type="text/javascript" src="${jsPath}/opro/views/recr/recrAnncDetail.js"></script> 
<script type="text/javascript" src="/raonkupload/js/raonkupload.js"></script>

<!-- 네비게이션 -->
<div class="area-detail" style="width: 100%;">
	<div class="table-detail" style="width: 100%;">
		<!-- Top -->
		<div class="nav_sec">

			<div class="btn-help" style="display:none;">
				<a href="javascript:helpPopup();">도움말</a>
			</div>
			
			<div class="option-left">
				<ul class="location">
					<li style="font-size: 30px; font-weight: 500;">평가진행현황 상세</li>
				</ul>
			</div>
			
			<div class="option-right">
				<ul class="location">
					<li class="home"><a href="#">홈</a></li>
					<li ><a href="#">평가진행현황 상세</a></li>
					<li><a href="#">${myMenuSubList.smallMenuNm}</a></li>
				</ul>
			</div> 
		</div>
	</div>
</div>
<!--//네비게이션 -->

<!-- Detail -->
<div class="area-detail" style="width: 100%;">
	<form id="detailFrm" name="detailFrm" method="POST">
	<input type="hidden" name="P_REGR_NM"  value=       "${sessionScope.loginResult.USR_NM}">
	<input type="hidden" name="P_USR_ID"  value="${sessionScope.loginResult.USR_ID}">
	<input type="hidden" name="resourceDesc"  value="${ param.resourceDesc}">
	<input type="hidden" name="resourceName"  value="${comFn:nvl(param.resourceName, resourceName)}">
	<input type="hidden" name="P_ESTM_NO"  value="${recrGnrlDetail.ESTM_NO }">
	<input type="hidden" name="P_ESTM_PSCD">
	<input type="hidden" name="P_ESTM_PROCD_SEQ">
	<input type="hidden" name="P_ESTM_PROCD_PSCD"> 
	<input type="hidden" name="P_ESTM_NO_TRANS" value="${P_ESTM_NO_TRANS}">
	<input type="hidden"  id="resultCode" name="resultCode" value="${resultCode}">
	
	
	<div class="table-detail">
	<!--  Top -->
	<div class="top-detail">
		<div class="type-fleft">
			<div class="contents-label">기본정보</div>
		</div>
	</div>
	<!-- // Top -->
	
		<!-- 필수정보 고정 -->
		<table class="component-detail-table type-line-none">
			<colgroup>
				<col width="15%">
				<col width="35%">
				<col width="15%">
				<col width="35%">
			</colgroup>
			<tbody>
				<tr>
					<th>평가명</th>
					<td colspan="3">${recrGnrlDetail.ESTM_NM }</td>
				</tr>
				<tr>
					<th>평가구분</th>
					<td>${recrGnrlDetail.ESTM_SECD_NM }</td>
					<th></th>
					<td></td>
				</tr>
				<tr>
					<th>등록자</th>
					<td>${recrGnrlDetail.REGR_NM }</td>
					<th>등록부서</th>
					<td>${recrGnrlDetail.ESTM_CHRG_DEPT_NM}</td>
				</tr>
			</tbody>
		</table>
	</div>
	<!-- //필수정보 고정 -->
		
		<div class="table-detail">
			<!-- Top -->
			<div class="top-detail">
				<div class="contents-label">첨부파일</div>
			</div>
			<!-- //Top -->
			
			<table class="component-detail-table type-file">
			<colgroup>
					<col width="120">
					<col width="*">
				</colgroup>
				<tbody>
					<tr>
						<th>첨부파일</th>
						<td colspan="3">
							<div class="fileViewer">
							<!-- 업로드 삽입. -->
							<script type="text/javascript">
								var raonkParam = {
						            Id: "uploadView1",
						            Width: "100%",
						            Height: "200px",
						            ButtonBarEdit: "open,download",
						            BorderStyle: "solid",
						        };
								var raonkUpload = new RAONKUpload(raonkParam);
							</script>
							</div>
							<div id="upload_fileInfo"></div>
						</td>
					</tr>
				 </tbody>
			</table>
		</div>
		
	<!-- bottom button -->
	<div class="bottom-buttons">
		<c:choose>
			<c:when test="${ recrGnrlDetail.ESTM_SECD eq 'E016' && recrGnrlDetail.ESTM_PSCD eq 'A002' }">
				 <a href="javascript:" class="btn-bottom type-b" id="regBtn">참여신청</a>
				<a href="javascript:" class="btn-bottom type-a" id="listBtn">목록</a>
			</c:when>
			<c:when test="${ recrGnrlDetail.ESTM_SECD eq 'E016' && recrGnrlDetail.ESTM_PSCD ne 'A002' }">
				<a href="javascript:" class="btn-bottom type-b" id="">참여종료</a>
				<a href="javascript:" class="btn-bottom type-a" id="listBtn">목록</a>
			</c:when>
			<c:when test="${ recrGnrlDetail.ESTM_SECD eq 'E007'  && recrGnrlDetail.ESTM_PSCD eq 'A002' }">
				<a href="javascript:" class="btn-bottom type-b" id="regEstmBtn">참여신청</a>
				<a href="javascript:" class="btn-bottom type-a" id="listBtn">목록</a>
			</c:when>
			<c:when test="${ recrGnrlDetail.ESTM_SECD eq 'E007'  && recrGnrlDetail.ESTM_PSCD ne 'A002' }">
				<a href="javascript:" class="btn-bottom type-b" id="">참여종료</a>
				<a href="javascript:" class="btn-bottom type-a" id="listBtn">목록</a>
			</c:when>
			<c:otherwise>
				 <a href="javascript:" class="btn-bottom type-a" id="listBtn"  style="width: 100px; font-size: 18px;">목록</a>
			</c:otherwise>
		</c:choose>
	</div>
	<!-- //bottom button -->
	</form>
	
</div>
<!-- //Detail -->	

<!-- LIST FORM -->
<form id="listFrm" method="POST">
	<input type="hidden" name="resourceName" value="${comFn:nvl(param.resourceName, resourceName)}">
	<input type="hidden" name="resourceDesc" value="${ param.resourceDesc }">
</form>

<%-- 파일 VIEW 폼 --%>
<form id="fileViewFrm" method="POST">
	<input type="hidden" id="P_FILE_GRP_NO" name="P_FILE_GRP_NO" value="${recrFile.FILE_GRP_NO}">
</form>

<!-- POPUP FORM -->
<form id="popupFrm" method="POST">
	<input type="hidden" name="resourceName" value="${comFn:nvl(param.resourceName, resourceName)}">
	<input type="hidden" name="resourceDesc" value="${param.resourceDesc }">
	<input type="hidden"  name="P_ESTM_NO"  value="${recrGnrlDetail.ESTM_NO }">
</form>

<!-- REG FROM  -->
<form id = "regFrm"  method="POST">
	<input type="hidden" name="resourceDesc" value="${ param.resourceDesc }">
	<input type="hidden" name="resourceName" value="${ comFn:nvl(param.resourceName, resourceName) }">
	<input type="hidden" name="contextPath" value="${contextPath }">
	<input type="hidden"  id="P_ESTM_NO" name="P_ESTM_NO"    value="${recrGnrlDetail.ESTM_NO }" >
</form>

<form id="manualFrm" method="POST">
</form>