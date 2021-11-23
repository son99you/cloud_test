<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 평가관리 > 평가진행현황 상세 - 기본정보
 *
 * <pre>
 * estm
 *    |_ estmCmtmProgDetail.jsp
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

<script type="text/javascript" src="${jsPath}/opro/views/estm/estmCmtmProgDetail.js"></script>
<script type="text/javascript" src="/raonkupload/js/raonkupload.js"></script>

<link rel="stylesheet" href="${contextPath}/PKIToolkitDemo_forOpenWeb/score-demo-openweb/js/bs-3.3.5/css/bootstrap.css" type="text/css">
<link rel="stylesheet" href="${contextPath}/PKIToolkitDemo_forOpenWeb/score-demo-openweb/js/bs-3.3.5/css/bootstrap-theme.css" type="text/css">
<link rel="stylesheet" href="${contextPath}/PKIToolkitDemo_forOpenWeb/score-demo-openweb/css/common.css" type="text/css">

<script type="text/javascript" src="${contextPath}/PKIToolkitDemo_forOpenWeb/score-demo-openweb/js/bs-3.3.5/js/bootstrap.js"></script>
<script type="text/javascript" src="${contextPath}/PKIToolkitDemo_forOpenWeb/score-demo-openweb/openweb/js/nxts/nxts.min.js"></script>
<script type="text/javascript" src="${contextPath}/PKIToolkitDemo_forOpenWeb/score-demo-openweb/openweb/js/nxts/nxtspki_config.js"></script>
<script type="text/javascript" src="${contextPath}/PKIToolkitDemo_forOpenWeb/score-demo-openweb/openweb/js/nxts/nxtspki.js"></script>

<script src="${contextPath}/ScoreHTML5/js/bs-3.3.5/js/bootstrap.js"></script>
<link rel="stylesheet" href="${contextPath}/ScoreHTML5/css/tsign_cert_style.css" type="text/css"/>
<script type="text/javascript" src="${contextPath}/ScoreHTML5/js/tsHTML5PKIConfig.js"></script>
<script type="text/javascript" src="${contextPath}/ScoreHTML5/js/tsHTML5_forge.js"></script>
<script type="text/javascript" src="${contextPath}/ScoreHTML5/js/tsHTML5.min.js"></script>

<!-- 2021-05-07 : 툴킷 bootstrap.css 가 깨져서 common_sbdc.css 추가함 -->
<link rel="stylesheet" href="/statics/css/comm/common_sbdc.css">

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
					<li style="font-size: 30px; font-weight: 500;">평가진행현황 상세</li>
				</ul>
			</div>
			
			<div class="option-right">
				<ul class="location">
					<li class="home"><a href="#">홈</a></li>
					<li  ><a href="#">${myMenuList.bigMenuNm}</a></li>
					<li><a href="#">${myMenuSubList.smallMenuNm}</a></li>
				</ul>
			</div>
		</div>
	</div>
</div>
<!--//네비게이션 --> 

<!-- Detail -->
<div class="area-detail">
	<form id="detailFrm" name="detailFrm" method="POST"  enctype="multipart/form-data">
		<input type="hidden" name="resourceDesc" value="${ param.resourceDesc}">
		<input type="hidden" name="resourceName" value="${comFn:nvl(param.resourceName, resourceName)}">
		<input type="hidden" name="P_ESTM_NO" value="${estmMngMstDetail.ESTM_NO }">
		<input type="hidden" name="P_ESTM_PSCD" value="${estmMngMstDetail.ESTM_PSCD }">
		<input type="hidden" name="P_ESTM_PROCD_SEQ">
		<input type="hidden" name="P_ESTM_PROCD_PSCD">
		<input type="hidden" name="P_ESTM_CMTM_NO" value="${sessionScope.loginResult.USR_ID}">
		<input type="hidden" name="P_LOGIN_MTHD" value="${sessionScope.loginResult.LOGIN_MTHD}">
		<input type="hidden" id="P_LOGIN_DN" name="P_LOGIN_DN" value="${sessionScope.loginResult.LOGIN_DN}">
		<input type="hidden" id="P_CERT_POLICY" name="P_CERT_POLICY" value="${sessionScope.loginResult.CERT_POLICY}">
		
		<input type="hidden" id="P_ESTM_NO_TRANS" value="${P_ESTM_NO_TRANS}">
		<input type="hidden"  id="resultCode" name="resultCode" value="${resultCode}">
		
		<!-- 심사위원 서명파일 file_grp_no -->
		<input type="hidden" id="P_SIGN_FILE_GRP_NO" name="P_SIGN_FILE_GRP_NO" value="${estmMngMstDetail.SIGN_FILE_GRP_NO }">
		<input type="hidden"  id="P_SIGN_YN" name="P_SIGN_YN" value="${comFn:nvl(estmMngMstDetail.SIGN_YN, 'Y')}">
		
		<!-- 심사위원 첨부파일 등록 여부 -->
		<input type="hidden" id="P_CMTM_FILE_GRP_NO"  name="P_CMTM_FILE_GRP_NO" value="${comFn:nvl(estmMngMstDetail.CMTM_FILE_GRP_NO,"") }">
		
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
					<a href="javascript:progTabEvent(1);">기본정보</a>
				</li>
				<!--
					2021.04.15 손연우
					평가절차에 따른 탭 리스트형식으로 받아오기 
				 -->
				<c:forEach var="data" items="${estmTabList}" varStatus="status">
					<c:if test="${data.ESTR_SECD eq 'A' }"><!-- 평가위원 평가만 보이도록 세팅 -->
						<c:if test="${ estmProcdDetail.ESTM_PROCD_SEQ eq data.ESTM_PROCD_SEQ }">
							<li class="is-selected">
								<a href="javascript:dynamicEstmTabEvent('${data.ESTM_PROCD_SEQ }','${data.ESTR_SECD }','detailFrm','${data.ESTM_PROCD_PSCD }');">${data.ESTM_PROCD_NM }</a>
							</li>
						</c:if>
						<c:if test="${estmProcdDetail.ESTM_PROCD_SEQ ne data.ESTM_PROCD_SEQ }">
							<li>
								<a href="javascript:dynamicEstmTabEvent('${data.ESTM_PROCD_SEQ }','${data.ESTR_SECD }','detailFrm','${data.ESTM_PROCD_PSCD }');">${data.ESTM_PROCD_NM }</a>
							</li>
						</c:if>
					</c:if>
				</c:forEach>
				<li>
					<a href="javascript:progTabEvent(8);">화상회의</a>
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
					
					<c:if test="${not empty estmCmtmFileList }">
						<input type="hidden" value="${estmCmtmFileList.get(0).FILE_GRP_NO }" name="P_FILE_GRP_NO">
						<c:forEach items="${estmCmtmFileCodeList}" var="code" varStatus="status">
							<c:forEach items="${estmCmtmFileList}" var="data" varStatus="status">
								<c:if test="${data.FILE_DOC_SECD eq code.CD_DTL_ID}">
									<tr>
										<th>
											${code.CD_DTL_NM}
										</th>
										<td>
											<a href="javascript:pageObj.download('${data.FILE_GRP_NO}', '${data.FILE_SN}')">
												${data.SYS_FILE_NM }
											</a>					
											<span style="float:right;">
												<button type="button" class="component-button-s type-a" onclick="fileModBtn(this);">수정</button>
											</span>
										</td>
										<td style="display:none;">
											<span style="float:left;">
												<input type="hidden" value="${code.CD_DTL_ID }" name="P_FILE_DOC_SECD" disabled>
												<input type="file" id="P_FILE" style="width: 100%;">
											</span>
											<span style="float:right;"><button type="button" class="component-button-s type-a" onclick="fileCancleBtn(this);">취소</button></span>
										</td>
									</tr>
								</c:if>
							</c:forEach> 
						</c:forEach>
					</c:if>

					<c:if test="${empty estmCmtmFileList }">
						<c:forEach items="${estmCmtmFileCodeList}" var="code" varStatus="status">
							<tr>
								<th>
									${code.CD_DTL_NM}
								</th>
								<td>
									<input type="hidden" value="${code.CD_DTL_ID }" name="P_FILE_DOC_SECD">
									<input type="file" name="P_FILE" style="width: 70%;">			
								</td>
							</tr>
						</c:forEach>
					</c:if>
				</tbody>
				
			</table>
		</div>
		
		<c:if test="${not empty estmSignFileList }">
			<div class="table-detail">
				<!-- Top -->
				<div class="top-detail">
					<div class="option-left" style="font-weight: bold;">
						<span style="float:left;font-weight: bold;">심사위원 서명파일</span>
						<span style="float:right;font-weight: bold;">※ 아래 첨부파일을 확인하였습니다.
							<label class="component-checkbox">
								<input type="checkbox" id="confirmCheck" name="confirmCheck"/>
								<i></i>
							</label>
						</span>
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
								<td colspan="2" class="">데이터가 존재하지 않습니다.</td>
							</tr>
						</c:if>
	                	<c:if test="${not empty estmSignFileList }">
	                		
	                  		<c:forEach items="${estmSignFileList}" var="data" varStatus="status">
	                  			<tr>
	                  				<th>${data.FILE_DOC_NM}</th>
	                  				<td><a href="javascript: pageObj.download('${data.FILE_GRP_NO}', '${data.FILE_SN}');">${data.SYS_FILE_NM }</a></td>
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
			<c:if test="${not empty estmSignFileList }">
				<c:if test="${FILE_SIGN_YN.SIGN_YN ne 'Y' }"><!-- 미서명 상태 -->
					<c:if test="${sessionScope.loginResult.LOGIN_MTHD eq 'ASSO'}">
						<a href="javascript:" class="btn-bottom type-b" id="assoSignBtn">심사위원 서명파일 전자서명(공동인증서)</a>
					</c:if>
					
					<c:if test="${sessionScope.loginResult.LOGIN_MTHD eq 'BROWSER'}">
						<a href="javascript:" class="btn-bottom type-b" id="browserSignBtn">심사위원 서명파일 전자서명(브라우저인증서)</a>
					</c:if>
					
					<c:if test="${sessionScope.loginResult.LOGIN_MTHD eq 'RSDNNO'}">
						<a href="javascript:" class="btn-bottom type-b" id="assoSignBtn">심사위원 서명파일 전자서명(공동인증서)</a>
						<a href="javascript:" class="btn-bottom type-b" id="browserSignBtn">심사위원 서명파일 전자서명(브라우저인증서)</a>
					</c:if>
				</c:if>
			</c:if>
			
			<c:if test="${empty estmCmtmFileList }">
				<a href="javascript:" class="btn-bottom type-b" id="registBtn">심사위원 첨부파일 저장</a>
			</c:if>
			<c:if test="${not empty estmCmtmFileList }">
				<a href="javascript:" class="btn-bottom type-b" id="updtBtn">심사위원 첨부파일 수정</a>
			</c:if>
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


<%-- 서명 FORM --%>
<form id="signFrm" method="POST">
	<input type="hidden" name="resourceName" value="${param.resourceName}">
	<input type="hidden" name="resourceDesc" value="${param.resourceDesc}">
	
	<input type="hidden" name="P_ESTM_NO" value="${estmMngMstDetail.ESTM_NO }">
	<input type="hidden" id="P_LOGIN_DN" name="P_LOGIN_DN" value="${sessionScope.loginResult.LOGIN_DN}">
	<input type="hidden" name="P_ESTM_CMTM_NO" value="${sessionScope.loginResult.USR_ID}">
	
	<c:forEach var="data" items="${fileHashcdValueListInqire}" varStatus="status">
		<input type="hidden" name="P_FILE_HASH_VAL" value="${data.FILE_HASH_VAL}">
		<input type="hidden" name="P_FILE_SN" value="${data.FILE_SN}">
		<input type="hidden" name="P_CERT_INHR_VAL" value="${data.CERT_INHR_VAL }">
		<input type="hidden" name="P_ELEC_SIGN_VAL" value="${data.ELEC_SIGN_VAL }">
		<input type="hidden" id="P_SIGN_DT" value="${data.SIGN_DT }">
	</c:forEach>
</form>