<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 알림마당 > 용어사전 등록
 *
 * <pre>
 * noti
 *    |_ trmRegistForm.jsp
 *
 * </pre>
 * @date : 2017. 06. 14
 * @version : 1.0
 * @author : 은우소프트 이주연
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %>
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %>

<script type="text/javascript" src="${jsPath}/ipro/views/noti/trmRegistForm.js"></script>
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
					<li style="font-size: 30px; font-weight: 500;">용어사전 등록</li>
				</ul>
			</div>
			
			<div class="option-right">
				<ul class="location">
					<li class="home"><a href="#">홈</a></li>
					<li><a href="#">${myMenuList.bigMenuNm}</a></li>
					<li><a href="#">${myMenuSubList.smallMenuNm}</a></li>
				</ul>
			</div>
		</div>
	</div>
</div>
<!--//네비게이션 -->

<!-- Detail -->
<div class="area-detail">
	<div class="table-detail">
		<!-- Top -->
		<div class="top-detail">
			<div class="type-fleft">
				<div class="contents-label">기본정보</div>
			</div>
		</div>
		<!-- //Top -->
		
		<form id="registFrm" method="POST">
			<input type="hidden" name="P_REGR_NM" value="${sessionScope.loginResult.USR_NM}">
			<input type="hidden" name="P_USR_ID" value="${sessionScope.loginResult.USR_ID}">
			<input type="hidden" name="resourceDesc" value="${ param.resourceDesc}">
			<input type="hidden" name="resourceName" value="${comFn:nvl(param.resourceName, resourceName)}">
			<input type="hidden" id="P_FILE_GRP_NO" name="P_FILE_GRP_NO">
			<input type="hidden" name="P_TRM_SECD">
			<input type="hidden" name="P_TRM_SN">
			
			<table class="component-detail-table type-line-none">
				<colgroup>
					<col width="15%">
					<col width="35%">
					<col width="15%">
					<col width="35%">
				</colgroup>
				<tr>
					<th>제목</th>
					<td colspan="3"><input type="text" id="P_TTL_NM" name="P_TTL_NM"  class="component-input type-full"></td>
				</tr>
				<tr>
					<th>등록자</th>
					<td colspan="3">${sessionScope.loginResult.USR_NM}</td>
				</tr>
				<tr>
					<th>내용</th>
					<td colspan="3">
						<textarea id="P_TRM_CNTN" name="P_TRM_CNTN" class="component-textarea" style="width: 100%; height: 200px; padding:7px"></textarea>
					</td>
				</tr>
			</table>
				
			<div class="table-detail">
				<!-- Top -->
				<div class="top-detail">
					<div class="contents-label">첨부파일</div>
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
								            Id: "upload",
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
							</td>
						</tr>
					</tbody>
				</table>
			</div>

			<!-- bottom button --> 
			<div class="bottom-buttons">
				<button type="button" class="btn-bottom type-b" id="registBtn">저장</button>
				<button type="button" class="btn-bottom type-a" id="listBtn">목록</button>
			</div>
			<!-- // bottom button -->
		</form>
	</div>
</div>
<!--  //Detail -->

<!-- LIST FORM -->
<form id="listFrm" method="POST">
	<input type="hidden" name="resourceName" value="${comFn:nvl(param.resourceName, resourceName)}">
</form>

<!-- DETAIL FORM -->
<form id="detailFrm" method="POST">
	<input type="hidden" name="resourceName" value="${comFn:nvl(param.resourceName, resourceName)}">
	<input type="hidden" name="P_TRM_SECD">
	<input type="hidden" name="P_TRM_SN">
	<input type="hidden" name="P_TTL_NM_S">
</form>