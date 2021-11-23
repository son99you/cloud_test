<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 평가관리 > 평가위원평가내용 팝업 상세
 *
 * <pre>
 * estm/popup 
 *    |_ estmFrmDetail.jsp
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

<script type="text/javascript" src="${jsPath}/ipro/views/comm/popup/estmCmtmEstmFrmDetail.js"></script>
<script type="text/javascript" src="/raonkupload/js/raonkupload.js"></script>

<div class="layout-pop">

	<!-- 서식데이터세팅 -->
	<c:forEach var="data" items="${estmCmtmEstmFrmItemList}" varStatus="status">
		<input type="hidden" name="D_ESTM_ITEM_NO" value="${data.ESTM_ITEM_NO }"><!-- 항목번호 -->
		<input type="hidden" name="D_ESTM_ITEM_NM" value="${data.ESTM_ITEM_NM }"><!-- 항목명 -->
		<input type="hidden" name="D_ESTM_ITEM_DSMK" value="${data.ESTM_ITEM_DSMK }"><!-- 배점 -->
		<input type="hidden" name="D_ESTM_ITEM_DESC" value="${data.ESTM_ITEM_DESC }"><!-- 내용 -->
		<input type="hidden" name="D_ESTM_MTHD_SECD" value="${data.ESTM_MTHD_SECD }"><!-- 평가항목/평가기준 -->
		<input type="hidden" name="D_ESTM_SCR" value="${data.ESTM_SCR }"><!-- 평가위원입력점수 -->
	</c:forEach>

	<!-- //서식데이터세팅 -->
	
	<input type="hidden" id="P_ESTM_FRM_PROCD_SECD" name="P_ESTM_FRM_PROCD_SECD" value="${estmCmtmEstmFrmDetail.ESTM_FRM_PROCD_SECD }">

	<form id="registFrm" class="search_form" method="POST">
		<input type="hidden"  id="resultCode" value="${resultCode}">
		<input type="hidden" id="P_PAGE_NO" name="P_PAGE_NO" value="${comFn:nvl(param.P_PAGE_NO, 1)}">
		<input type="hidden" id="P_PAGE_SIZE" name="P_PAGE_SIZE" value="${comFn:nvl(param.P_PAGE_SIZE, 10)}">
		<input type="hidden" id="setMulti" name="setMulti" value="${param.setMulti}">
		<input type="hidden" id="P_GBN" name="P_GBN" value="${comFn:nvl(param.P_GBN, GBN)}">
		<input type="hidden" id="P_FRM_GBN" name="P_FRM_GBN" value="${param.P_FRM_GBN}">
		<input type="hidden" id="P_ESTM_NO" name="P_ESTM_NO" value="${estmCmtmEstmFrmDetail.ESTM_NO}"><!-- 평가번호 -->
		<input type="hidden" id="P_ESTM_OBJ_SEQ" name="P_ESTM_OBJ_SEQ" value="${estmCmtmEstmFrmDetail.ESTM_OBJ_SEQ}"><!-- 평가대상순번 -->
		<input type="hidden" id="P_ESTM_PROCD_SEQ" name="P_ESTM_PROCD_SEQ" value="${estmCmtmEstmFrmDetail.ESTM_PROCD_SEQ}"><!-- 평가절차순번 -->
		<input type="hidden" id="P_ESTM_CMTM_NO" name="P_ESTM_CMTM_NO" value="${estmCmtmEstmFrmDetail.ESTM_CMTM_NO}"><!-- 평가위원번호 -->
		<input type="hidden" id="P_FILE_GRP_NO" name="P_FILE_GRP_NO" value="${estmCmtmEstmFrmDetail.FILE_GRP_NO }">
		<input type="hidden" id="P_FILE_GRP_NO_NEW" name="P_FILE_GRP_NO_NEW">
		<input type="hidden" id="reloadURL" name="reloadURL" value="${reloadURL }">
		<input type="hidden" id="P_ESTR_SECD" name="P_ESTR_SECD" value="${estmCmtmEstmFrmDetail.ESTR_SECD}"><!-- -->
		
		<input type="hidden" id="PREV_ESTM_OBJ_SEQ" name="PREV_ESTM_OBJ_SEQ" value="${estmObjDetail.PREV_ESTM_OBJ_SEQ}"><!-- 이전 평가대상 -->
		<input type="hidden" id="NEXT_ESTM_OBJ_SEQ" name="NEXT_ESTM_OBJ_SEQ" value="${estmObjDetail.NEXT_ESTM_OBJ_SEQ}"><!-- 다음 평가대상 -->
		
		
		<div class="pop_header">
			<div class="title">평가위원평가표</div>
		</div> <!--// pop_header E -->
		
		<table class="component-detail-table type-line-none">
			<colgroup>
				<col width="20%">
				<col width="30%">
				<col width="20%">
				<col width="30%">
			</colgroup>
			<tbody>
			<tr>
				<th>평가절차구분</th>
				<td>${estmCmtmEstmFrmDetail.ESTM_PROCD_SECD_NM }</td>
				<th>&nbsp;</th>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<th>서식명</th>
				<td colspan="3">
					${estmCmtmEstmFrmDetail.ESTM_PROCD_NM }
				</td>
			</tr>
			<tr>
				<th>평가대상명</th>
				<td class="txt-center" style="cursor: pointer;" onclick="estmObjImstarsMainView('popupFrm', '${estmObjDetail.ITEM_NO}', '${estmObjDetail.BIZRNO}', '${estmObjDetail.ESTM_OBJ_SEQ }');">
					${estmObjDetail.OBJ_NM }
				</td>
				<th>상품명</th>
				<td class="txt-center" style="cursor: pointer;" onclick="estmObjImstarsDetailView('popupFrm', '${estmObjDetail.ITEM_NO}', '${estmObjDetail.BIZRNO}', '${estmObjDetail.ESTM_OBJ_SEQ }');">
					${estmObjDetail.ITEM_NM }
				</td>
			</tr>
			</tbody>
		</table>
		
		
		<!-- 평가대상 -->
<%-- 		<table class="component-detail-table type-line-none">
			<colgroup>
				<col width="20%">
				<col width="30%">
				<col width="20%">
				<col width="30%">
			</colgroup>
			<tbody>
			<tr>
				<th>평가절차구분</th>
				<td>${estmCmtmEstmFrmDetail.ESTM_PROCD_SECD_NM }</td>
				<th>&nbsp;</th>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<th>서식명</th>
				<td colspan="3">
					${estmCmtmEstmFrmDetail.ESTM_PROCD_NM }
				</td>
			</tr>
			</tbody>
		</table> --%>
		
		
		
		<div class="bottom-buttons">
			<!-- <button type="button" id="saveBtn" class="btn-bottom type-b">저장</button> -->
			<c:if test="${not empty estmObjDetail.NEXT_ESTM_OBJ_SEQ }">
				<button type="button" name="nextBtn" style="float: right; margin-bottom: 10px;" class="btn-bottom type-a">다음</button>
			</c:if>
			<c:if test="${not empty estmObjDetail.PREV_ESTM_OBJ_SEQ }">
				<button type="button" name="prevBtn"  style="float: right; margin-bottom: 10px;" class="btn-bottom type-a">이전</button>
			</c:if>
		</div>
		
		<div id="tableDiv">
			<table class="component-detail-table type-line-none">
				<colgroup>
					<col style="width: auto;">
				</colgroup>
				<tbody id="copyArea">
				<tr>
					<th>평가항목명</th>
				</tr>
				<tr>
				</tr>
				</tbody>
			</table>
		</div>
		
		<div style="height: 10px;"></div>
		<c:if test="${estmCmtmEstmFrmDetail.ESTM_FRM_PROCD_SECD ne 'C'}">
			<table class="component-detail-table type-line-none" style="float: right; width:150px;">
				<colgroup>
					<col width="50%">
					<col width="50%">
				</colgroup>
				<tbody>
				<tr>
					<th>합계</th>
					<td><input type="text"  readonly="readonly" id="P_TOT_SCR" name="P_TOT_SCR" class="component-input type-full"  placeholder=""></td>
				</tr>
				</tbody>
			</table>
		</c:if>
		
		<c:if test="${estmCmtmEstmFrmDetail.ESTM_FRM_PROCD_SECD eq 'C'}">
			<input type="hidden"  readonly="readonly" id="P_TOT_SCR" name="P_TOT_SCR" class="component-input type-full"  placeholder="">
		</c:if>
		
		<div style="height: 10px;"></div>
		
		<div class="table-detail">
			<div class="top-detail">
				<div class="contents-label">평가첨부파일</div>
	
			</div>
			<!-- //Top -->
	
			<table class="component-detail-table type-file">
				<colgroup>
					<col width="120">
					<col width="*">
				</colgroup>
				<tbody>
					<tr>
						<th>평가첨부파일</th>
						<td>
							<div class="fileViewer">
								<!-- 업로드 삽입. -->
								<script type="text/javascript">
									var raonkParam = {
							            Id: "uploadView1",
							            Width: "100%",
							            Height: "200px",
							            ButtonBarEdit: "add,remove,remove_all,open,download",
							            BorderStyle: "solid",
							            FolderNameRule: "yyyy/mm/dd/estm"
							        };
									var raonkUpload = new RAONKUpload(raonkParam);
								</script>
							</div>
							<div id="upload_fileInfo"></div>
						</td>
					</tr>
					<tr>
						<th>평가위원의견</th>
						<td>
							<textarea class="component-textarea" style="height: 70px;" placeholder="" name="P_ESTM_OPNN">${estmCmtmEstmFrmDetail.ESTM_OPNN}</textarea>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
		
		<div class="bottom-buttons">
			<c:if test="${not empty estmObjDetail.PREV_ESTM_OBJ_SEQ }">
				<button type="button" name="prevBtn" class="btn-bottom type-a">이전</button>
			</c:if>
			<c:if test="${estmCmtmEstmFrmDetail.ESTM_PROCD_PSCD ne 'B002' }">
				<c:if test="${param.P_GBN ne 'cmpl' }">
					<c:if test="${param.P_ESTM_CMTM_NO ne 'cmtmAtFalse' }">
						<button type="button" id="saveBtn" class="btn-bottom type-b">저장</button>
					</c:if>
					<c:if test="${param.P_ESTM_CMTM_NO eq 'cmtmAtFalse' }">
						<button type="button" id="saveFalseBtn" class="btn-bottom type-c">저장</button>
					</c:if>
				</c:if>
			</c:if>
			<button type="button" id="colseBtn" class="btn-bottom type-a">닫기</button>
			<c:if test="${not empty estmObjDetail.NEXT_ESTM_OBJ_SEQ }">
				<button type="button" name="nextBtn" class="btn-bottom type-a">다음</button>
			</c:if>
		</div>
		
		<div style="padding-top: 30px;"></div>
	</form>
</div> 

<form id="listFrm" method="POST">
	<input type="hidden" name="resourceName" value="${comFn:nvl(param.resourceName, resourceName)}">
</form> 

<form id="updtFrm" method="POST">
	<input type="hidden" name="resourceName" value="${comFn:nvl(param.resourceName, resourceName)}">
	<input type="hidden" name="P_FRM_NO" id="P_FRM_NO" value="${contFormDetail.FRM_NO}">
	<input type="hidden" name="P_VRSN" id="P_VRSN" value="${contFormDetail.VRSN}">
</form>


<form id="popupFrm" method="POST"> 
	<input type="hidden" name="resourceName" value="${comFn:nvl(param.resourceName, resourceName)}">
	<input type="hidden" name="P_FILE_GRP_NO">
	<input type="hidden" name="P_ESTM_PROCD_SEQ" value="${estmCmtmEstmFrmDetail.ESTM_PROCD_SEQ }">
	<input type="hidden" name="P_ESTM_NO" value="${estmCmtmEstmFrmDetail.ESTM_NO }">
	<input type="hidden" name="P_ESTM_OBJ_SEQ"/>
	<input type="hidden" name="P_ESTM_CMTM_NO"/>
	<%-- <input type="hidden" name="P_ESTM_PROCD_PSCD" value="${estmProcdDetail.ESTM_PROCD_PSCD}"/> --%>
	<input type="hidden" name="P_ESTM_OBJ_PE_NO"/>
	<input type="hidden" name="reloadURL"/>
	<input type="hidden" name="P_BSNM_REGIST_NO">
	<input type="hidden" name="P_GOODSNO">
	<input type="hidden" name="P_REQST_NO">
	<input type="hidden" name="P_ESTM_INFO_CNTC_NO" value="${estmMngMstDetail.ESTM_INFO_CNTC_NO }">
</form>
