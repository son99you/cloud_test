<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 메인 > 평가진행현황 참여신청
 *
 * <pre>
 * recr 
 *    |_ recrAnncRegForm.jsp
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

<script type="text/javascript" src="${jsPath}/opro/views/recr/recrAnncRegForm.js"></script> 
<script type="text/javascript" src="/raonkeditor/js/raonkeditor.js"></script>
<script type="text/javascript" src="/raonkupload/js/raonkupload.js"></script>

<!-- 네비게이션 -->
<div class="area-detail" style="width: 100%;">
	<div class="table-detail">
		<!-- Top -->
		<div class="nav_sec">

			<div class="btn-help" style="display:none;">
				<a href="javascript:helpPopup();">도움말</a>
			</div>
			
			<div class="option-left">
				<ul class="location">
					<c:if test="${empty estmObjRsdnNo.ESTM_OBJ_SEQ}"><li style="font-size: 30px; font-weight: 500;">평가진행현황 참여신청</li></c:if>
					<c:if test="${not empty estmObjRsdnNo.ESTM_OBJ_SEQ}"><li style="font-size: 30px; font-weight: 500;">평가진행현황 참여신청 수정</li></c:if>
				</ul>
			</div>
			
			<div class="option-right">
				<ul class="location">
					<li class="home"><a href="#">홈</a></li>
					<c:if test="${empty estmObjRsdnNo.ESTM_OBJ_SEQ}"><li><a href="#">평가진행현황 참여신청</a></li></c:if>
					<c:if test="${not empty estmObjRsdnNo.ESTM_OBJ_SEQ}"><li><a href="#">평가진행현황 참여신청 수정</a></li></c:if>
					<%-- <li><a href="#">${myMenuList.bigMenuNm}</a></li> --%>
					<li><a href="#">${myMenuSubList.smallMenuNm}</a></li>
				</ul>
			</div> 
		</div>
	</div>
</div>
<!--//네비게이션 -->

<!-- Detail -->
<div class="area-detail" style="width: 100%;">
	<!-- DB 및 JS에서 뽑아낸 데이터를 담아줄 그릇들  -->
	<form id="regFrm" method="POST"  enctype="multipart/form-data">
	<input type="hidden" name="P_REGR_NM" value="${sessionScope.loginResult.USR_NM}">
	<input type="hidden" name="P_USR_ID" value="${sessionScope.loginResult.USR_ID}">
	<input type="hidden" name="resourceDesc" value="${ param.resourceDesc}">
	<input type="hidden" name="P_ESTM_NO" value="${recrGnrlDetail.ESTM_NO }">
	<input type="hidden" name="resourceName" value="${comFn:nvl(param.resourceName, resourceName)}">
	<input type="hidden" id="P_ESTM_OBJ_SEQ" name="P_ESTM_OBJ_SEQ" value="${estmObjRsdnNo.ESTM_OBJ_SEQ }">
	<input type="hidden" id="P_ESTM_PSCD" name="P_ESTM_PSCD" value="${recrGnrlDetail.ESTM_PSCD }">
	<input type="hidden" name="P_ESTM_PROCD_SEQ" >
	<input type="hidden" name="P_ESTM_PROCD_PSCD" >
	<input type="hidden" name="P_ESTM_NO_TRANS" value="${P_ESTM_NO_TRANS}">
	<input type="hidden" name="resultCode"  id="resultCode" value="${ resultCode }">
	<input type="hidden" name="P_FILE_GRP_NO" id = "P_FILE_GRP_NO"  value= "${estmObjRsdnNo.FILE_GRP_NO }">
	<input type="hidden" name="P_FILE_GRP_NO_NEW" id = "P_FILE_GRP_NO_NEW">
	<input type="hidden" id="P_RECR_CHECK"  name="P_RECR_CHECK"  > 
	<input type="hidden" id="P_CRTR_NO"  name="P_CRTR_NO"  value="${ recrAnncDetail.CRTR_NO }">
	<input type="hidden" id="P_RSDN_NO"  name="P_RSDN_NO">
	
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
				<div class="contents-label">입력정보 </div>
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
					<th>생년월일</th>
					<c:if test="${empty estmObjRsdnNo.ESTM_OBJ_SEQ}">
						<td>
							<input type="text" id="P_RSDN_NO_1" style="width:100px;" name="P_RSDN_NO_1" class="component-input" maxlength="6"  value="${estmObjRsdnNo.RSDN_NO_1 }" >
							&nbsp;-&nbsp;
							<input type="text" id="P_RSDN_NO_2" style="width:35px;" name="P_RSDN_NO_2" class="component-input" maxlength="1"  value="${estmObjRsdnNo.RSDN_NO_2 }">******
							 &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							 <a href="javascript:" class="component-button-s type-a" id="checkBtn" >중복확인</a> 
							<span style="color:red;"><br>※생년월일과 전화번호를 입력한 후 중복체크를 진행해주세요.</span>
						</td>
						<th>전화번호</th>
						<td>
							<input type="text" id="P_TEL_NO"   name="P_TEL_NO" class="component-input w70"  maxlength="11"  value="${recrAnncDetail.TEL_NO }"  numeric>
						</td>
					</c:if>
					<c:if test="${not empty estmObjRsdnNo.ESTM_OBJ_SEQ}">
						<td>
							<input type="text" id="P_RSDN_NO_1" style="width:100px; " name="P_RSDN_NO_1" class="component-input" maxlength="6"  value="${estmObjRsdnNo.RSDN_NO_1 }" readonly="readonly">
							&nbsp;-&nbsp;
							<input type="text" id="P_RSDN_NO_2" style="width:35px;" name="P_RSDN_NO_2" class="component-input" maxlength="1"  value="${estmObjRsdnNo.RSDN_NO_2 }" readonly="readonly">******
							<span  style="color:red;" ><br>※생년월일과 전화번호는 수정이 불가합니다.</span>
						</td>
						<th>전화번호</th>
						<td>
							<input type="text" id="P_TEL_NO"   name="P_TEL_NO"  class="component-input"  maxlength="11" value="${recrAnncDetail.TEL_NO }" readonly="readonly" numeric>
						</td>
					</c:if>
				</tr>
				<tr>
					<th>성명</th>
					<td><input type="text" id="P_CRTR_NM"  name="P_CRTR_NM" class="component-input" placeholder=""  value="${recrAnncDetail.CRTR_NM }"></td>
					<th></th>
					<td></td>
				</tr>
				<tr>
					<th>이메일</th>
					<td><input type="text" id="P_EMAL" name="P_EMAL" class="component-input"  placeholder=""  value="${recrAnncDetail.EMAL }"></td>
					<th></th>
					<td></td>
				</tr>
				<tr>
					<th>채널명</th>
					<td><input type="text" id="P_CHNL_NM" name="P_CHNL_NM" class="component-input" placeholder=""   value="${recrAnncDetail.CHNL_NM }"></td>
					<th>구독자수</th>
					<td><input type="text" id="P_SSCRT_CNT" name="P_SSCRT_CNT" class="component-input" placeholder=""   value="${recrAnncDetail.SSCRT_CNT }" numeric></td>
				</tr>
				<tr>
					<th>채널주소</th>
					<td><input type="text" id="P_CHNL_ADDR" name="P_CHNL_ADDR" class="component-input" placeholder=""   value="${recrAnncDetail.CHNL_ADDR }"></td>
					<th></th>
					<td></td>
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
			
			
		<c:if test="${empty estmObjRsdnNo.ESTM_OBJ_SEQ}">
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
		</c:if>
			
		<c:if test="${not empty estmObjRsdnNo.ESTM_OBJ_SEQ}">
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
		 </c:if>
	</div>
		
	<!-- bottom button -->
	<div class="bottom-buttons">
	<c:if test="${empty estmObjRsdnNo.ESTM_OBJ_SEQ}">
		<a href="javascript:" class="btn-bottom type-b" id="saveBtn">신청</a>
	</c:if>
	<c:if test="${not empty estmObjRsdnNo.ESTM_OBJ_SEQ}">
		<a href="javascript:" class="btn-bottom type-b" id="upateBtn">수정</a>
	</c:if>
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
	<input type="hidden" id="P_PAGE_NO" name="P_PAGE_NO" value="${comFn:nvl(param.P_PAGE_NO, 1)}">
	<input type="hidden" id="P_PAGE_SIZE" name="P_PAGE_SIZE" value="${comFn:nvl(param.P_PAGE_SIZE, 10)}">
</form>

<!-- 파일 VIEW 폼 --> 
<c:if test="${not empty estmObjRsdnNo.ESTM_OBJ_SEQ}">
 <form id="fileViewFrm" method="POST">
	<input type="hidden" id="P_FILE_GRP_NO" name="P_FILE_GRP_NO" value="${estmObjRsdnNo.FILE_GRP_NO}">
</form>
</c:if> 
