<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">--%>
<%--
 * 일반공지 > 일반공지
 *
 * <pre>
 * 
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %>

<script type="text/javascript" src="${jsPath}/ipro/views/main/popup/noticePopup.js"></script>
<script type="text/javascript" src="/raonkupload/js/raonkupload.js"></script>
<script type="text/javascript" src="/raonkeditor/js/raonkeditor.js"></script>

<div class="layout-pop">
	
	<form id="detailFrm" class="search_form" method="POST">
		<input type="hidden" id="P_BBS_SECD" value="${notiDetail.BBS_SECD }">
		<input type="hidden" id="P_FILE_GRP_NO" value="${notiDetail.FILE_GRP_NO }">
		
		<div class="pop_header">
			<div class="title">
				<c:if test="${notiDetail.BBS_SECD eq 'FAQ'}">FAQ</c:if>
				<c:if test="${notiDetail.BBS_SECD eq 'NOTI'}">공지사항</c:if> 상세
			</div>
		</div>
	
		<div>
			<table class="component-detail-table type-line-none">
                <colgroup>
                   	<col width="15%" align="left">
					<col width="35%" align="left">
					<col width="15%" align="left">
					<col width="35%" align="left">
                </colgroup>
                <tbody>
					<tr>
						<th>제목</th>
						<td colspan="3">${notiDetail.TTL_NM }</td>
					</tr>
					<tr>
						<th>등록자</th>
						<td>${notiDetail.REGR_NM }</td>
						<th>이메일</th>
						<td>${notiDetail.EMAL_ADDR }</td>
					</tr>
					<tr>
						<th>내용</th>
						<td colspan="3" style="min-height: 100px; vertical-align: top; padding-top: 5px;">
	                	<div style="width:100%;height:450px;">  
	                       <script type="text/javascript">
			                       var kEditorParam = {
					                       Id: "editor1",
					                       SkinName: "bluegray",
					                       Lang: "ko-kr", // ko-kr, en-us, ja-jp, zh-cn, zh-tw
					                       Mode: "view",
					                       Width: "100%",
					                       Height: "100%"
					                   };
					         	var editor1 = new RAONKEditor(kEditorParam);
						   </script>
						</div>
						<textarea id="P_BBS_CNTN" name="P_BBS_CNTN" style="width: 100%; height: 100%; display: none;">${notiDetail.BBS_CNTN}</textarea>
					</td>
					</tr>
                </tbody>
            </table>
		</div>
		
		
		<!-- Top -->
		<div class="top-detail">
			<div class="top-detail">
				<div class="contents-label">첨부파일</div>
			</div>
		</div>
		<!-- //Top -->
		
		<table class="component-detail-table type-file">
			<colgroup>
				<col width="15%">
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
		
		<div class="bottom-buttons">
			<a href="javascript:" class="btn-bottom type-b" id="listBtn">목록</a>
			<a href="javascript:" class="btn-bottom type-a" onclick="javascript:window.close();">닫기</a>
		</div> <!--// btn_wrap E -->
			
	</form> 
</div>


<%-- 파일 VIEW 폼 --%>
<form id="listFrm" method="POST">
	<input type="hidden" id="P_BBS_SECD"  name="P_BBS_SECD" value="${notiDetail.BBS_SECD }">
	<input type="hidden" id="P_PAGE_NO" name="P_PAGE_NO" value="${comFn:nvl(param.P_PAGE_NO, 1)}">
	<input type="hidden" id="P_PAGE_SIZE" name="P_PAGE_SIZE" value="${comFn:nvl(param.P_PAGE_SIZE, 10)}">
</form>