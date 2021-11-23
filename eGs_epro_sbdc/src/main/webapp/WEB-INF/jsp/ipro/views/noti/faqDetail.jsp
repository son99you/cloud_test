<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 알림마당 > 자주묻는질문 상세
 *
 * <pre>
 * noti 
 *    |_ faqDetail.jsp
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

<script type="text/javascript" src="${jsPath}/ipro/views/noti/faqDetail.js"></script>
<script type="text/javascript" src="/raonkupload/js/raonkupload.js"></script>
<script type="text/javascript" src="/raonkeditor/js/raonkeditor.js"></script>

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
					<li style="font-size: 30px; font-weight: 500;">FAQ 상세</li>
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
				<div class="contents-label"></div>
			</div>
		</div>
		<!-- //Top -->

			<table class="component-detail-table type-line-none">
				<colgroup>
					<col width="119">
					<col width="*">
					<col width="150">
					<col width="*">
				</colgroup>
				<tbody>
				<tr class="line">
					<th>제목</th>
					<td colspan="3">${faqDetail.TTL_NM }</td>
				</tr>
				<tr>
					<th>등록자</th>
					<td>${faqDetail.REGR_NM }</td>
					<th>이메일</th>
					<td>${faqDetail.EMAL_ADDR }</td>
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
					<textarea id="P_BBS_CNTN" name="P_BBS_CNTN" style="width: 100%; height: 100%; display: none;">${faqDetail.BBS_CNTN }</textarea>
					</td>
				</tr>
		   </tbody>
		 </table>
	
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
		  </div>
	</div>	
	<!-- bottom button -->
	<div class="bottom-buttons">
		<c:if test="${loginResult.AUTH_ID eq '1' }">
			<a href="javascript:" class="btn-bottom type-b" id="updtBtn">수정</a>
			<a href="javascript:" class="btn-bottom type-b" id="deleteBtn">삭제</a>
		</c:if>
		<a href="javascript:" class="btn-bottom type-a" id="listBtn">목록</a>
	</div>
	<!-- //bottom button -->
</div>
<!-- //Detail -->	

<form id="listFrm" method="POST">
	<input type="hidden" name="resourceName" value="${comFn:nvl(param.resourceName, resourceName)}">
	<input type="hidden" name="resourceDesc" value="${ param.resourceDesc }">
</form>
<form id="updtFrm" method="POST">
	<input type="hidden" name="resourceName" value="${comFn:nvl(param.resourceName, resourceName)}">
	<input type="hidden" name="resourceDesc" value="${ param.resourceDesc }">
	<input type="hidden" name="P_BBS_SECD" value="${faqDetail.BBS_SECD}">
	<input type="hidden" name="P_BBS_SN" value="${faqDetail.BBS_SN}">
</form>

<%-- 파일 VIEW 폼 --%>
<form id="fileViewFrm" method="POST">
	<input type="hidden" id="P_FILE_GRP_NO" name="P_FILE_GRP_NO" value="${faqDetail.FILE_GRP_NO}">
</form>