<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 알림마당 > 공지사항 상세
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

<script type="text/javascript" src="${jsPath}/opro/views/recr/recrAnncEstmRegForm.js"></script> 
<script type="text/javascript" src="/raonkeditor/js/raonkeditor.js"></script>
<script type="text/javascript" src="/raonkupload/js/raonkupload.js"></script>

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
					<li style="font-size: 30px; font-weight: 500;">평가공고 신청</li>
				</ul>
			</div>
			
			<div class="option-right">
				<ul class="location">
					<li class="home"><a href="#">홈</a></li>
					<li><a href="#">평가공고 신청</a></li>
					<%-- <li><a href="#">${myMenuList.bigMenuNm}</a></li>  --%>
					<li><a href="#">${myMenuSubList.smallMenuNm}</a></li>
				</ul>
			</div> 
		</div>
	</div>
</div>
<!--//네비게이션 -->

<!-- Detail -->
<div class="area-detail">
	<form id="regFrm" method="POST"  enctype="multipart/form-data">
	<input type="hidden" name="P_REGR_NM" value="${sessionScope.loginResult.USR_NM}">
	<input type="hidden" name="P_USR_ID" value="${sessionScope.loginResult.USR_ID}">
	<input type="hidden" name="resourceDesc" value="${ param.resourceDesc}">
	<input type="hidden" name="P_ESTM_NO" value="${recrGnrlDetail.ESTM_NO }">
	<input type="hidden" name="resourceName" value="${comFn:nvl(param.resourceName, resourceName)}">
	<input type="hidden" name="P_ESTM_OBJ_SEQ" >
	<input type="hidden" name="P_ESTM_PSCD" >
	<input type="hidden" name="P_ESTM_PROCD_SEQ" >
	<input type="hidden" name="P_ESTM_PROCD_PSCD" >
	<input type="hidden" name="P_ESTM_NO_TRANS" value="${P_ESTM_NO_TRANS}">
	<input type="hidden" name="P_CRTR_NO"  >
	<input type="hidden" name="resultCode"  id="resultCode" value="${ resultCode }">
	<input type="hidden" id="P_FILE_GRP_NO" name="P_FILE_GRP_NO">
	
	<div class="table-detail">
		<!-- Top -->
		<div class="top-detail">
			<div class="type-fleft">
				<div class="contents-label">기본정보</div>
			</div>
		</div>
		<!-- //Top -->

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
		<!-- //필수정보 고정 -->
	
		<div class="top-detail">
			<div class="type-fleft">
				<div class="contents-label">입력정보</div>
			</div>
		</div>
		
		<!-- 입력정보 -->
		<table class="component-detail-table type-line-none">
			<colgroup>
				<col width="15%">
				<col width="35%">
				<col width="15%">
				<col width="35%">
			</colgroup>
			<tbody>
				<tr>
					<th>사업자등록번호 </th>
					<td colspan="3">
						<input type="text" id="P_BIZRNO" style="width:100px;" name="P_BIZRNO" class="component-input" >
					</td>
				</tr>
				<tr>
					<th>업체명</th>
					<td><input type="text" id="P_VEND_NM"  name="P_VEND_NM" class="component-input" placeholder="" ></td>
					<th>상품명</th>
					<td><input type="text" id="P_ITEM_NM" name="P_ITEM_NM" class="component-input"  placeholder="" ></td>
				</tr>
				<tr>
					<th>이메일</th>
					<td><input type="text" id="P_EMAL" name="P_EMAL" class="component-input"  placeholder="" ></td>
					<th></th>
					<td></td>
				</tr>
				<tr>
					<th>성명</th>
					<td><input type="text" id="P_ESTM_OBJ_PE_NM" name="P_ESTM_OBJ_PE_NM" class="component-input" placeholder="" ></td>
					<th>전화번호 </th>
					<td><input type="text" id="P_TEL_NO" name="P_TEL_NO" class="component-input" placeholder="" ></td>
				</tr>
			</tbody>
		</table>
		<!-- //입력정보 -->
	</div>
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
						<td>
							<div class="fileViewer">
								<!-- 업로드 삽입. -->
								<script type="text/javascript">
									var raonkParam = {
							            Id: "upload",
							            Width: "100%",
							            Height: "200px",
							            ButtonBarEdit: "add,remove,remove_all",
							            BorderStyle: "solid",
							            FolderNameRule: "yyyy/mm/dd/recr"
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
			<a href="javascript:" class="btn-bottom type-b" id="saveBtn">신청</a>
			<a href="javascript:" class="btn-bottom type-a" id="beforPageBtn">이전</a>
		</div>
		<!-- //bottom button -->
		
	</form>
</div>
<!-- //Detail -->	


<form id="detailFrm" method="POST">
	<input type="hidden" name="resourceName" value="${comFn:nvl(param.resourceName, resourceName)}">
	<input type="hidden" name="resourceDesc" value="${ param.resourceDesc }">
	<input type="hidden" name="contextPath" value="${contextPath}" >
	<input type="hidden"  name="P_ESTM_NO" value="${recrGnrlDetail.ESTM_NO }">
</form>

<%-- 파일 VIEW 폼 
<form id="fileViewFrm" method="POST">
	<input type="hidden" id="P_FILE_GRP_NO" name="P_FILE_GRP_NO" value="${recrFile.FILE_GRP_NO}">
</form> --%>
