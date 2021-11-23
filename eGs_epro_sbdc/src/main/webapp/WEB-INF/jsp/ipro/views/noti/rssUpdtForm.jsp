<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 알림마당 > 계약자료실 수정
 *
 * <pre>
 * noti 
 *    |_ rssUpdtForm.jsp
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

<script type="text/javascript" src="${jsPath}/ipro/views/noti/rssUpdtForm.js"></script>
<script type="text/javascript" src="/raonkupload/js/raonkupload.js"></script>

<div class="sp_sec">
	<div class="nav_sec">
		<ul id="breadcrumbs">
			<li class="home">홈</li>
			<li>${myMenuList.bigMenuNm}</li>
			<li>${myMenuSubList.smallMenuNm}</li> 
		</ul>
	</div><!--// nav_sec -->
	<h3 class="sp_tit">계약자료실 수정</h3>
	
	<form id="updtFrm" method="POST" enctype="multipart/form-data">
		<input type="hidden" name="resourceDesc" value="${ param.resourceDesc}" >
		<input type="hidden" name="resourceName" value="${comFn:nvl(param.resourceName, resourceName)}" >
		<input type="hidden" name="P_BBS_SECD" value="${rssDetail.BBS_SECD}">
		<input type="hidden" name="P_BBS_SN" value="${rssDetail.BBS_SN}">
		<input type="hidden" id="P_FILE_GROUP_FLAG" name="P_FILE_GROUP_FLAG" value="noti">
		<input type="hidden" id="P_FILE_GRP_NO" name="P_FILE_GRP_NO" value="${rssDetail.FILE_GRP_NO}">
		<input type="hidden" id="P_FILE_GRP_NO_NEW" name="P_FILE_GRP_NO_NEW" value="">
		
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
					<td colspan="3"><input type="text" id="P_TTL_NM" name="P_TTL_NM" value="${rssDetail.TTL_NM }" class="input "  placeholder="제목"></td>
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
						<textarea id="P_BBS_CNTN" name="P_BBS_CNTN" style="width: 100%; height: 200px ; padding:7px" placeholder="내용">${rssDetail.BBS_CNTN }</textarea>
					</td>
				</tr>
			</table>
	
			<p class="spc_stit">첨부파일</p>
			<div class="fileViewer">
				<!-- 업로드 삽입. -->
				<script type="text/javascript">
					var raonkParam = {
			            Id: "uploadView1",
			            Width: "100%",
			            Height: "200px",
			            ButtonBarEdit: "add,remove,remove_all",
			            BorderStyle: "solid",
			            FolderNameRule: "yyyy/mm/dd/noti"
			        };
					var raonkUpload = new RAONKUpload(raonkParam);
				</script>
			</div>	
			<div id="upload_fileInfo"></div>
			
			<div class="btm_btns">
				<button type="button" class="btn ty02" id="updtBtn">저장</button>
				<button type="button" class="btn ty04" id="listBtn">목록</button>
			</div>
		</div>
	</form>
</div>

<form id="listFrm" method="POST">
	<input type="hidden" name="resourceName" value="${comFn:nvl(param.resourceName, resourceName)}">
	<input type="hidden" name="resourceDesc" value="${ param.resourceDesc }">
</form>
<form id="detailFrm" method="POST" > 
	<input type="hidden" name="resourceName" value="${comFn:nvl(param.resourceName, resourceName)}" >
	<input type="hidden" name="P_BBS_SECD" value="${rssDetail.BBS_SECD}">
	<input type="hidden" name="P_BBS_SN" value="${rssDetail.BBS_SN}">
</form>

<!-- FILE VIEW FORM -->
<form id="fileViewFrm" method="POST">
	<input type="hidden" id="P_FILE_GRP_NO_EXT" name="P_FILE_GRP_NO_EXT" value="${rssDetail.FILE_GRP_NO}">
</form>