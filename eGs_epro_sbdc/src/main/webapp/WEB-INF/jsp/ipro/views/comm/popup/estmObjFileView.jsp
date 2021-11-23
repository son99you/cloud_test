<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">--%>
<%--
 * 공통 > 평가대상정보 첨부파일 상세 팝업
 *
 * <pre>
 * comm 
 *  |_popup
 *   |_ estmObjFileView.jsp
 * 
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %>

<script type="text/javascript" src="${jsPath}/ipro/views/comm/popup/estmObjFileView.js"></script>
<script type="text/javascript" src="/raonkupload/js/raonkupload.js"></script>

<div class="layout-pop">
	<form id="detailFrm" class="detailFrm" method="POST">
		<input type="hidden" name="resourceDesc" value="${ param.resourceDesc}">
		<input type="hidden" name="resourceName" value="${comFn:nvl(param.resourceName, resourceName)}">
		<input type="hidden" id="P_ESTM_NO" name="P_ESTM_NO" value="${estmObjFileView.ESTM_NO }">
		<input type="hidden" id="P_ESTM_OBJ_SEQ" name="P_ESTM_OBJ_SEQ" value="${estmObjFileView.ESTM_OBJ_SEQ }">
		<input type="hidden" id="P_FILE_GRP_NO" name="P_FILE_GRP_NO" value="${estmObjFileView.FILE_GRP_NO }">
		<input type="hidden" id="P_UPDT_PSBL_YN" value="${param.P_UPDT_PSBL_YN }"><!-- 수정가능여부 - N:수정불가, Y:수정가능 -->
		
		<div class="pop_header">
			<div class="title">평가대상정보 첨부파일 상세</div>
		</div> <!--// pop_header E -->
		
		<div>
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
						            Mode: "view",
						            Height: "200px",
						            ButtonBarView: "open,download",
						            BorderStyle: "solid",
						        };
								var raonkUpload = new RAONKUpload(raonkParam);
							</script>
						</div>
						<div id="upload_fileInfo"></div>
					</td>
				</tr>
			</table>
		</div>
		
		
		<div class="bottom-buttons">
			<c:if test="${param.P_UPDT_PSBL_YN ne 'N' }">
				<a href="javascript:" class="btn-bottom type-b" id="updtBtn">수정</a>
			</c:if>
			<a href="javascript:" class="btn-bottom type-a" onclick="window.close();">닫기</a>
		</div>
	</form>
</div> 