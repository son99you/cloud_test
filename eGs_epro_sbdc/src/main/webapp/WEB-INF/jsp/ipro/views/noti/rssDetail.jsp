<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 알림마당 > 계약자료실 상세
 *
 * <pre>
 * noti 
 *    |_ rssDetail.jsp
 * 
 * </pre>
 * @date : 2017. 06. 14
 * @version : 1.0
 * @author : 은우소프트 이주연
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld"%>
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld"%>

<script type="text/javascript" src="${jsPath}/ipro/views/noti/rssDetail.js"></script>
<script type="text/javascript" src="/raonkupload/js/raonkupload.js"></script>

<div class="sp_sec">
	<div class="nav_sec">
		<ul id="breadcrumbs">
			<li class="home">홈</li>
			<li>${myMenuList.bigMenuNm}</li>
			<li>${myMenuSubList.smallMenuNm}</li> 
			</ul>
	</div>
	<h3 class=sp_tit>계약자료실 상세</h3>

	<div class="sp_cont">
		<table class="form_tb">
			<colgroup>
				<col width="15%">
				<col width="35%">
				<col width="15%">
				<col width="35%">
			</colgroup>
			<tr class="line">
				<th>제목</th>
				<td colspan="3">${rssDetail.TTL_NM }</td>
			</tr>
			<tr>
				<th>등록자</th>
				<td>${rssDetail.REGR_NM }</td>
				<th>이메일</th>
				<td>${rssDetail.EMAL_ADDR }</td>
			</tr>
			<tr>
				<th>내용</th>
				<td colspan="3">
					<textarea id="P_BBS_CNTN" name="P_BBS_CNTN" style="width: 100%; height: 200px; padding:7px" readonly="readonly">${rssDetail.BBS_CNTN }</textarea>
				</td>
			</tr>
		</table>
		<p class="spc_stit">첨부파일</p>
		<div class="fileViewer">  <!-- btn_wrap전에 있는 board_write에 m0 추가 -->
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
			<c:if test="${rssDetail.REGR_ID eq loginResult.USR_ID}">
				<button type="button" class="btn ty02" id="updtBtn">수정</button>
				<button type="button" class="btn ty02" id="deleteBtn">삭제</button>
			</c:if>
			<button type="button" class="btn ty04" id="listBtn">목록</button>
		</div>
	</div>
</div>

<form id="listFrm" method="POST">
	<input type="hidden" name="resourceName" value="${comFn:nvl(param.resourceName, resourceName)}" >
	<input type="hidden" name="resourceDesc" value="${ param.resourceDesc }">
</form>
<form id="updtFrm" method="POST">
	<input type="hidden" name="resourceName" value="${comFn:nvl(param.resourceName, resourceName)}" >
	<input type="hidden" name="resourceDesc" value="${ param.resourceDesc }">
	<input type="hidden" name="P_BBS_SECD" value="${rssDetail.BBS_SECD}">
	<input type="hidden" name="P_BBS_SN" value="${rssDetail.BBS_SN}">
</form>

<%-- 파일 VIEW 폼 --%>
<form id="fileViewFrm" method="POST">
	<input type="hidden" id="P_FILE_GRP_NO" name="P_FILE_GRP_NO" value="${rssDetail.FILE_GRP_NO}">
</form>