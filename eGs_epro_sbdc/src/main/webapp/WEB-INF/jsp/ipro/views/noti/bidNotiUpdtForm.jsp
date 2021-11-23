<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 알림마당 > 입찰공지사항 수정
 *
 * <pre>
 * noti 
 *    |_ bidNotiUpdtForm.jsp
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

<script type="text/javascript" src="${jsPath}/ipro/views/noti/bidNotiUpdtForm.js"></script>

<div class="contents_wrap">
	<ul class="step_wrap">
		<li><a href="#">${myMenuList.bigMenuNm}</a></li>
		<li><a href="#">${myMenuSubList.smallMenuNm}</a></li>
	</ul>
	<div class="tit_wrap">
		<h3 class="tit">입찰공지사항 수정</h3>
	</div>
	
	<form id="updtFrm" method="POST" enctype="multipart/form-data">
		<input type="hidden" name="P_BBS_SECD" value="${bidNotiDetail.BBS_SECD}">
		<input type="hidden" name="P_BBS_SN" value="${bidNotiDetail.BBS_SN}">
		
		<input type="hidden" id="P_FILE_GROUP_FLAG" name="P_FILE_GROUP_FLAG" value="noti">
		<input type="hidden" id="P_FILE_GRP_NO" name="P_FILE_GRP_NO" value="${bidNotiDetail.FILE_GRP_NO}">
		
		<div class="view_wrap typeA">
			<div class="view_area">
				<table>
					<colgroup>
						<col width="15%">
						<col width="35%">
						<col width="15%">
						<col width="35%">
					</colgroup>
					<tr class="line">
						<th>제목</th>
						<td colspan="3"><input type="text" id="P_TTL_NM" name="P_TTL_NM" value="${bidNotiDetail.TTL_NM }" maxlength="200" class="w_95p"></td>
					</tr>
					<tr>
						<th>등록자</th>
						<td>${bidNotiDetail.REGR_NM }</td>
						<th>이메일</th>
						<td>${bidNotiDetail.EMAL_ADDR }</td>
					</tr>
					<tr style="display: none;">
						<th>팝업기간</th>
						<td colspan="3">
							<div class="cddDiv">
		                        <input type="text" id="P_PPUP_STDE" name="P_PPUP_STDE" value="${comFn:formatDate(bidNotiDetail.PPUP_STDE,'yyyyMMdd','yyyy-MM-dd')}" class="w120" date>
		                        &nbsp;~&nbsp;
		                        <input type="text" id="P_PPUP_ENDE" name="P_PPUP_ENDE" value="${comFn:formatDate(bidNotiDetail.PPUP_ENDE,'yyyyMMdd','yyyy-MM-dd')}" class="w120" date>
		                        &nbsp;&nbsp;
		                    	<label for="PPUP_YN"><input type="checkbox" id="P_PPUP_YN" name="P_PPUP_YN" value="${bidNotiDetail.PPUP_YN }" <c:if test="${bidNotiDetail.PPUP_YN eq 'Y'}">checked</c:if> onclick="ppupYnEvent(this)">팝업게시</label>
	                    	</div>
						</td>
					</tr>
					<tr>
						<th>내용</th>
						<td colspan="3">
							<textarea id="P_BBS_CNTN" name="P_BBS_CNTN" style="width: 100%; height: 200px;">${bidNotiDetail.BBS_CNTN }</textarea>
						</td>
					</tr>
				</table>
			</div>
	
			<div class="tit_area">
		  		<h4 class="tit">첨부파일</h4>
		  	</div>
			<div class="view_area fileViewer">
				<!-- 업로드 삽입. -->
				<script type="text/javascript">
					DEXT5UPLOAD.config.Mode = 'upload';
					DEXT5UPLOAD.config.Width = '100%';
					DEXT5UPLOAD.config.FolderNameRule = '/noti';
					var dext5Upload = new Dext5Upload("upload");
				</script>
			</div>	
			<div id="upload_fileInfo"></div>
			
			<div class="btn_wrap">
				<button type="button" class="btn btn_m btn_orange" id="updtBtn">저장</button>
				<button type="button" class="btn btn_m btn_del" id="listBtn">목록</button>
			</div>
			
		</div>
	</form>
</div>

<form id="listFrm" method="POST">
	<input type="hidden" name="resourceName" value="${ param.resourceName }">
	<input type="hidden" name="resourceDesc" value="${ param.resourceDesc }">
</form>
<form id="detailFrm" method="POST" > 
	<input type="hidden" name="resourceName" value="${ param.resourceName }" >
	<input type="hidden" name="P_BBS_SECD" value="${bidNotiDetail.BBS_SECD}">
	<input type="hidden" name="P_BBS_SN" value="${bidNotiDetail.BBS_SN}">
</form>

<!-- FILE VIEW FORM -->
<form id="fileViewFrm" method="POST">
	<input type="hidden" id="P_FILE_GRP_NO_EXT" name="P_FILE_GRP_NO_EXT" value="${bidNotiDetail.FILE_GRP_NO}">
</form>