<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 평가관리 > 평가진행현황_평가위원 상세 - 기본정보
 *
 * <pre>
 * estm
 *    |_ estmCmtmProgDetail.jsp
 * 
 * </pre>
 * @date : 2021. 03. 23.
 * @version : 1.0
 * @author : 은우소프트
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %> 
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %> 

<script type="text/javascript" src="${jsPath}/ipro/views/estm/estmCmtmProgDetail.js"></script>
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
					<li style="font-size: 30px; font-weight: 500;">평가진행현황_평가위원 상세</li>
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
	<form id="detailFrm" name="detailFrm" method="POST">
		<input type="hidden" name="P_REGR_NM" value="${sessionScope.loginResult.USR_NM}">
		<input type="hidden" name="P_USR_ID" value="${sessionScope.loginResult.USR_ID}">
		<input type="hidden" name="resourceDesc" value="${ param.resourceDesc}">
		<input type="hidden" name="resourceName" value="${comFn:nvl(param.resourceName, resourceName)}">
		<input type="hidden" name="P_ESTM_NO" value="${estmMngMstDetail.ESTM_NO }">
		<input type="hidden" name="P_ESTM_PSCD">
		<input type="hidden" name="P_ESTM_PROCD_SEQ">
		<input type="hidden" name="P_ESTM_PROCD_PSCD">
		
		<!-- 필수정보 고정 -->
		<div class="table-detail">
			<table class="component-detail-table type-line-none">
				<colgroup>
					<col width="15%">
					<col width="35%">
					<col width="15%">
					<col width="35%">
				</colgroup>
				<tbody>
					<tr>
						<th>평가번호</th>
						<td>${estmMngMstDetail.ESTM_NO }</td>
						<th>평가진행상태</th>
						<td>${estmMngMstDetail.ESTM_PSCD_NM }</td>
					</tr>
					<tr>
						<th>평가명</th>
						<td colspan="3">${estmMngMstDetail.ESTM_NM }</td>
					</tr>
					<tr>
						<th>평가구분</th>
						<td>${estmMngMstDetail.ESTM_SECD_NM }</td>
						<th>평가정보연계번호</th>
						<td>${estmMngMstDetail.ESTM_INFO_CNTC_NO }</td>
					</tr>
					<tr>
						<th>평가분야구분</th>
						<td>${estmMngMstDetail.ESTM_SPHE_SECD_NM }</td>
						<th>&nbsp;</th>
						<td>&nbsp;</td>
					</tr>
				</tbody>
			</table>
		</div>
		<!-- //필수정보 고정 -->
		
		
		<!-- tab -->
		<div class="component-tab-line">
			<ul class="list-tab">
				<li class="is-selected">
					<a href="javascript:cmtmTabEvent(1);">기본정보</a>
				</li>
				<!--
					2021.04.15 손연우
					평가절차에 따른 탭 리스트형식으로 받아오기 
				 -->
				<c:forEach var="data" items="${estmTabList}" varStatus="status">
					<c:if test="${data.ESTR_SECD eq 'A' }"><!-- 평가위원 평가만 보이도록 세팅 -->
						<c:if test="${ estmProcdDetail.ESTM_PROCD_SEQ eq data.ESTM_PROCD_SEQ }">
							<li class="is-selected">
								<a href="javascript:dynamicCmtmEstmTabEvent('${data.ESTM_PROCD_SEQ }','${data.ESTR_SECD }','detailFrm','${data.ESTM_PROCD_PSCD }');">${data.ESTM_PROCD_NM }</a>
							</li>
						</c:if>
						<c:if test="${estmProcdDetail.ESTM_PROCD_SEQ ne data.ESTM_PROCD_SEQ }">
							<li>
								<a href="javascript:dynamicCmtmEstmTabEvent('${data.ESTM_PROCD_SEQ }','${data.ESTR_SECD }','detailFrm','${data.ESTM_PROCD_PSCD }');">${data.ESTM_PROCD_NM }</a>
							</li>
						</c:if>
					</c:if>
				</c:forEach>
				<li>
					<a href="javascript:cmtmTabEvent(7);">평가결과</a>
				</li>
				<li>
					<a href="javascript:cmtmTabEvent(8);">화상회의</a>
				</li>
			</ul>
		</div>
		<!-- //tab -->
		
		<div class="table-detail">
			<!-- Top -->
			<div class="top-detail">
				<div class="type-fleft">
					<div class="contents-label">기본정보</div>
				</div>
			</div>
			<!-- //Top -->
			
			<table class="component-detail-table type-line-none">
				<colgroup>
					<col width="15%">
					<col width="35%">
					<col width="15%">
					<col width="35%">
				</colgroup>
				<tbody>
					<tr>
						<th>평가번호</th>
						<td>${estmMngMstDetail.ESTM_NO }</td>
						<th>평가진행상태</th>
						<td>${estmMngMstDetail.ESTM_PSCD_NM }</td>
					</tr>
					<tr>
						<th>평가명</th>
						<td colspan="3">${estmMngMstDetail.ESTM_NM }</td>
					</tr>
					<tr>
						<th>평가구분</th>
						<td>${estmMngMstDetail.ESTM_SECD_NM }</td>
						<th>평가정보연계번호</th>
						<td>${estmMngMstDetail.ESTM_INFO_CNTC_NO }</td>
					</tr>
					<tr>
						<th>평가분야구분</th>
						<td>${estmMngMstDetail.ESTM_SPHE_SECD_NM }</td>
						<th>&nbsp;</th>
						<td>&nbsp;</td>
					</tr>
					<tr>
						<th>평가담당자</th>
						<td>${estmMngMstDetail.ESTM_CHRGR_NM }</td>
						<th>평가담당부서</th>
						<td>${estmMngMstDetail.ESTM_CHRG_DEPT_NM }</td>
					</tr>
					<tr>
						<th>평가시작일시</th>
						<td>${comFn:formatDate(estmMngMstDetail.TOTL_ESTM_ST_DT,'yyyyMMddHHmmss','yyyy-MM-dd HH:mm:ss')}</td>
						<th>평가종료일시</th>
						<td>${comFn:formatDate(estmMngMstDetail.TOTL_ESTM_END_DT,'yyyyMMddHHmmss','yyyy-MM-dd HH:mm:ss')}</td>
					</tr>
					<tr>
						<th>최고/최저점제외 여부</th>
						<td>
							<c:if test="${estmMngMstDetail.MXMN_SCR_EXCP_YN eq 'Y'}">예</c:if>
							<c:if test="${estmMngMstDetail.MXMN_SCR_EXCP_YN ne 'Y'}">아니오</c:if>
						</td>
						<th>&nbsp;</th>
						<td>&nbsp;</td>
					</tr>
				</tbody>
			</table>
		</div>
			
	
		<div class="table-detail">
			<!-- Top -->
			<div class="top-detail">
				<div class="contents-label">첨부파일</div>
			</div>
			<!-- //Top -->
	
			<table class="component-detail-table type-file">
				<colgroup>
					<col width="15%">
					<col width="85%">
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
							            Height: "200px",
							            ButtonBarEdit: "open,download",
							            BorderStyle: "solid",
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
		
		
		<div class="table-detail">
			<!-- Top -->
			<div class="top-detail">
				<div class="option-left" style="font-weight: bold;">
					<span style="float:left;font-weight: bold;">심사위원 서명파일</span>
					<span style="float:right;font-weight: bold;">※ 아래 첨부파일을 확인하였습니다.
						<label class="component-checkbox">
							<input type="checkbox">
							<i></i>
						</label>
					</span>
				</div>
			</div>
			<!-- //Top -->
	
			<table class="component-detail-table"> 
				<colgroup>
					<col width="30%">
					<col width="70%">
				</colgroup>
				<thead>
					<tr>
						<th>문서명</th>
						<th>파일첨부</th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${empty estmSignFileList}">
						<tr>
							<td colspan="2" class="txt-center">검색 조건에 해당하는 데이터가 존재하지 않습니다.</td>
						</tr>
					</c:if>
                	<c:if test="${not empty estmSignFileList }">
                  		<c:forEach items="${estmSignFileList}" var="data" varStatus="status">
                  			<tr>
                  				<th>${data.FILE_DOC_NM}</th>
                  				<td><a href="javascript:download('${data.FILE_GRP_NO}', '${data.FILE_SN}');">${data.SYS_FILE_NM }</a></td>
                  			</tr>
	                   	</c:forEach>
                   	</c:if>
				</tbody>
			</table> 
		</div>
		
		
		<!-- bottom button -->
		<div class="bottom-buttons">
			<!-- <a href="javascript:" class="btn-bottom type-b" id="saveBtn">심사위원 서명파일 전자서명</a> -->
			<a href="javascript:" class="btn-bottom type-a" id="listBtn">목록</a>
		</div>
		<!-- //bottom button -->
	</form>
	
</div>
<!-- //Detail -->


<!-- LIST FORM -->
<form id="listFrm" method="POST">
	<input type="hidden" name="resourceName" value="${comFn:nvl(param.resourceName, resourceName)}">
	<input type="hidden" name="resourceDesc" value="${ param.resourceDesc}">
</form>

<%-- 파일 VIEW 폼 --%>
<form id="fileViewFrm" method="POST">
	<input type="hidden" id="P_FILE_GRP_NO" name="P_FILE_GRP_NO" value="${estmMstFile.FILE_GRP_NO}">
</form>

<!-- DOWNLOAD FORM -->
<form id="downloadFrm" method="POST">
	<input type="hidden" name="P_FILE_SN">
	<input type="hidden" name="P_FILE_GRP_NO">
</form>