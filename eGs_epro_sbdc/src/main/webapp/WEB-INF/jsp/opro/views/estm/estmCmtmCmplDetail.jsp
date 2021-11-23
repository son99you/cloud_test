<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 평가관리 > 평가진행현황 상세 - 기본정보
 *
 * <pre>
 * estm
 *    |_ estmCmtmCmplDetail.jsp
 * 
 * </pre>
 * @date : 2021. 03. 22.
 * @version : 1.0
 * @author : 은우소프트
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %> 
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %> 

<script type="text/javascript" src="${jsPath}/opro/views/estm/estmCmtmCmplDetail.js"></script>
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
					<li style="font-size: 30px; font-weight: 500;">평가완료현황 상세</li>
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
		<input type="hidden" name="resourceDesc" value="${ param.resourceDesc}">
		<input type="hidden" name="resourceName" value="${comFn:nvl(param.resourceName, resourceName)}">
		<input type="hidden" name="P_ESTM_NO" value="${estmMngMstDetail.ESTM_NO }">
		
		<!-- 동적 Tab 이동시 필요 -->
		<input type="hidden" name="P_ESTM_PROCD_SEQ" value="">
		<input type="hidden" name="P_ESTR_SECD" value="">
		<!-- 필수정보 고정 -->
		
		<input type="hidden" name="P_ESTM_CMTM_NO" value="${sessionScope.loginResult.USR_ID}">
		<!-- 심사위원 서명파일 file_grp_no -->
		<input type="hidden" id="P_SIGN_FILE_GRP_NO" name="P_SIGN_FILE_GRP_NO" value="${estmMngMstDetail.SIGN_FILE_GRP_NO }">
		<input type="hidden"  id="P_SIGN_YN" name="P_SIGN_YN" value="${comFn:nvl(estmMngMstDetail.SIGN_YN, 'Y')}">
		
		<!-- 심사위원 첨부파일 등록 여부 -->
		<input type="hidden" id="P_CMTM_FILE_GRP_NO"  name="P_CMTM_FILE_GRP_NO" value="${comFn:nvl(estmMngMstDetail.CMTM_FILE_GRP_NO,"") }">
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
						<th>평가대상구분</th>
						<td>${estmMngMstDetail.ESTM_OBJ_SECD_NM }</td>
					</tr>
				</tbody>
			</table>
		</div>
		<!-- //필수정보 고정 -->
		
		
		<!-- tab -->
		<div class="component-tab-line">
			<ul class="list-tab">
				<li class="is-selected">
					<a href="javascript:cmplTabEvent(1);">기본정보</a>
				</li>
				<c:forEach var="data" items="${estmTabList}" varStatus="status">
					<c:if test="${data.ESTR_SECD eq 'A' }"><!-- 평가위원 평가만 보이도록 세팅 -->
						<c:if test="${estmProcdDetail.ESTM_PROCD_SEQ eq data.ESTM_PROCD_SEQ }">
							<li class="is-selected">
								<a href="javascript:dynamicEstmTabEvent('${data.ESTM_PROCD_SEQ }','${data.ESTR_SECD }','detailFrm','${data.ESTM_PROCD_PSCD }','cmpl');">${data.ESTM_PROCD_NM }</a>
							</li>
						</c:if>
						<c:if test="${estmProcdDetail.ESTM_PROCD_SEQ ne data.ESTM_PROCD_SEQ }">
							<li>
								<a href="javascript:dynamicEstmTabEvent('${data.ESTM_PROCD_SEQ }','${data.ESTR_SECD }','detailFrm','${data.ESTM_PROCD_PSCD }','cmpl');">${data.ESTM_PROCD_NM }</a>
							</li>
						</c:if>
					</c:if>
				</c:forEach>
				<li class="">
					<a href="javascript:cmplTabEvent(8);">화상회의</a>
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
						<td colspan="3">${estmMngMstDetail.ESTM_SPHE_SECD_NM }</td>
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
						<th>실평가여부</th>
						<td>
							<c:if test="${estmMngMstDetail.REAL_ESTM_YN eq 'Y'}">예</c:if>
							<c:if test="${estmMngMstDetail.REAL_ESTM_YN ne 'Y'}">아니오</c:if>
						</td>
						<th>최고/최저점제외 여부</th>
						<td>
							<c:if test="${estmMngMstDetail.MXMN_SCR_EXCP_YN eq 'Y'}">예</c:if>
							<c:if test="${estmMngMstDetail.MXMN_SCR_EXCP_YN ne 'Y'}">아니오</c:if>
						</td>
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
					<span style="float:left;font-weight: bold;">심사위원 첨부파일</span>
				</div>
			</div>
			<!-- //Top -->
		
			<table class="component-detail-table type-file">
				<colgroup>
					<col width="30%">
					<col width="70%">
				</colgroup>
				<tbody>
				
					<c:forEach items="${estmCmtmFileCodeList}" var="code" varStatus="status">
						<c:if test="${empty estmCmtmFileList}" >
							<tr>
								<th>${code.CD_DTL_NM}</th>
								<td>데이터가 존재하지 않습니다.</td>
							</tr>
						</c:if>
						
						<c:if test="${not empty estmCmtmFileList}" >
							<c:forEach items="${estmCmtmFileList}" var="data" varStatus="status">
								<c:if test="${data.FILE_DOC_SECD eq code.CD_DTL_ID}">
									<tr>
										<th>
											${code.CD_DTL_NM}
										</th>
										<td>
											<a href="javascript:pageObj.download('${data.FILE_GRP_NO}', '${data.FILE_SN}')">${data.SYS_FILE_NM }</a>					
										</td>
									</tr>
								</c:if>
							</c:forEach> 
						</c:if>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<c:if test="${not empty estmSignFileList }">
			<div class="table-detail">
				<!-- Top -->
				<div class="top-detail">
					<div class="option-left" style="font-weight: bold;">
						<span style="float:left;font-weight: bold;">심사위원 서명파일</span>
					</div>
				</div>
				<!-- //Top -->
		
				<table class="component-detail-table type-file">
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
								<td colspan="2" class="txt-center">데이터가 존재하지 않습니다.</td>
							</tr>
						</c:if>
	                	<c:if test="${not empty estmSignFileList }">
	                  		<c:forEach items="${estmSignFileList}" var="data" varStatus="status">
	                  			<tr>
	                  				<th>${data.FILE_DOC_NM}</th>
	                  				<td><a href="javascript:pageObj.download('${data.FILE_GRP_NO}', '${data.FILE_SN}');">${data.SYS_FILE_NM }</a></td>
	                  			</tr>
		                   	</c:forEach>
	                   	</c:if>
	                   	<tr>
		                   	<th>서명여부</th>
		                   	<td>
								<c:if test="${FILE_SIGN_YN.SIGN_YN eq 'Y' }">서명완료</c:if>
								<c:if test="${FILE_SIGN_YN.SIGN_YN ne 'Y' }">미서명</c:if>
		                   	</td>
	                   	</tr>
					</tbody>
				</table>
			</div>
		</c:if>
		<!-- bottom button -->
		<div class="bottom-buttons">
			<!-- <a href="javascript:" class="btn-bottom type-b" id="svyBtn">설문조사</a> -->
			<a href="javascript:" class="btn-bottom type-a" id="listBtn">목록</a>
		</div>
		<!-- //bottom button -->
	</form>
	</div>
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

<!-- POPUP FORM -->
<form id="popupFrm" method="POST">
	<input type="hidden" name="resourceName" value="${comFn:nvl(param.resourceName, resourceName)}">
	<input type="hidden" name="resourceDesc" value="${param.resourceDesc}">
	<input type="hidden" name="P_ESTM_NO" value="${estmMngMstDetail.ESTM_NO }">
	<input type="hidden" name="P_ESTM_PROCD_SEQ">
</form>

<!-- DOWNLOAD FORM -->
<form id="downFrm" method="POST">
	<input type="hidden" name="P_FILE_SN">
	<input type="hidden" name="P_FILE_GRP_NO">
</form>