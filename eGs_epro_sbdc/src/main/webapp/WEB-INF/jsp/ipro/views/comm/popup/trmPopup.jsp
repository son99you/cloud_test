<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">--%>
<%--
 * 공통 > 용어사전(팝업)
 *
 * <pre>
 * comm
 *    |_ popup
 *           |_ trmPopup.jsp
 * <pre>
 * 
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %>

<script type="text/javascript" src="${jsPath}/ipro/views/comm/popup/trmPopup.js"></script>
<script type="text/javascript" src="/raonkupload/js/raonkupload.js"></script>

<div class="layout-pop">
	<div class="pop_header">
		<div class="title">용어사전</div>
	</div> <!--// pop_header E -->
	<div>
		<form id="detailFrm" class="search_form" method="POST">
			<input type="hidden" id="P_TRM_SECD" value="${trmDetail.TRM_SECD }">
			<input type="hidden" id="P_FILE_GRP_NO" value="${trmDetail.FILE_GRP_NO }">
			<table class="component-detail-table type-line-none">
				<colgroup>
					<col style="width: 20%;">
					<col style="width: auto;">
				</colgroup>
				<tbody>
					<tr>
						<th>제목</th>
						<td colspan="3">${trmDetail.TTL_NM }</td>
					</tr>
					<tr>
						<th>내용</th>
						<td colspan="3">
							<textarea id="P_TRM_CNTN" name="P_TRM_CNTN" class="component-textarea" style="width: 100%; height: 200px;" readonly="readonly">${trmDetail.TRM_CNTN }</textarea>
						</td>
					</tr>
				</tbody>		
			</table>
		</form>
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
				<td colspan="3">
					<div class="fileViewer">
						<!-- 업로드 삽입. -->
						<script type="text/javascript">
							var raonkParam = {
						            Id: "uploadView1",
						            Mode: "view",
						            Width: "100%",
						            Height: "200px",
						            ButtonBarView: "open,download",
						            BorderStyle: "solid"
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
	
	<div class="bottom-buttons">
		<button type="button" class="btn-bottom type-a" onclick="javascript:window.close();">닫기</button>
	</div>

</div>