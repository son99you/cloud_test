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

<script type="text/javascript" src="${jsPath}/opro/views/comm/popup/trmPopup.js"></script>
<script type="text/javascript" src="/raonkupload/js/raonkupload.js"></script>

<div class="pop_sp_sec">
	<h3 class="sp_tit">
		용어정리 상세
	</h3>
	<form id="detailFrm" class="search_form" method="POST" >
		<input type="hidden" id="P_TRM_SECD" value="${trmDetail.TRM_SECD }">
		<input type="hidden" id="P_FILE_GRP_NO" value="${trmDetail.FILE_GRP_NO }">
		<div class="sp_cont">
	    <!-- Data List -->
			<table class="form_tb">
                <caption>용어정리</caption>
                <colgroup>
                   	<col width="15%" align="left">
					<col width="35%" align="left">
					<col width="15%" align="left">
					<col width="35%" align="left">
                </colgroup>
                <tbody>
					<tr class="line">
						<th>제목</th>
						<td colspan="3">${trmDetail.TTL_NM }</td>
					</tr>
					<tr>
						<th>내용</th>
						<td colspan="3">
							<textarea id="P_BBS_CNTN" name="P_BBS_CNTN" style="width: 100%; height: 200px;" readonly="readonly">${trmDetail.TRM_CNTN }</textarea>
						</td>
					</tr>
                </tbody>
            </table>
		
			<p class="spc_stit">첨부파일</p>
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
			
			<div class="btm_btns">
				<button type="button" class="btn ty04" onclick="javascript:window.close();">닫기</button>
			</div> <!--// btn_wrap E -->
			
		</div>
	</form> 
</div>
