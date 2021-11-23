<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 평가관리 > 평가위원순위선정진행현황 상세
 *
 * <pre>
 * estm
 *    |_ cmtmRnkSlctProgDetail.jsp
 * 
 * </pre>
 * @date : 2021. 03. 19.
 * @version : 1.0
 * @author : 은우소프트
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %> 
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %> 

<script type="text/javascript" src="${jsPath}/ipro/views/estm/cmtmRnkSlctProgDetail.js"></script>
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
					<li style="font-size: 30px; font-weight: 500;">평가위원순위선정진행현황 상세</li>
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
		<input type="hidden" id="P_ESTM_CMTM_SLCT_NGR" name="P_ESTM_CMTM_SLCT_NGR" value="${comFn:nvl(estmMngMstDetail.MAX_ESTM_CMTM_SLCT_NGR, 1)}">
		<input type="hidden" name="P_ESTM_PSCD">
		
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
					<tr>
						<th>외부평가위원선정방법</th>
						<td>${estmMngMstDetail.OUT_CMTM_SLCT_MTHD_SECD_NM }</td>
						<th>내부평가위원선정방법</th>
						<td>${estmMngMstDetail.INN_CMTM_SLCT_MTHD_SECD_NM }</td>
					</tr>
					<tr>
						<th>외부평가위원수</th>
						<td><c:if test="${not empty estmMngMstDetail.OUT_ESTM_CMTM_CNT }">${estmMngMstDetail.OUT_ESTM_CMTM_CNT } 명</c:if></td>
						<th>내부평가위원수</th>
						<td><c:if test="${not empty estmMngMstDetail.INN_ESTM_CMTM_CNT }">${estmMngMstDetail.INN_ESTM_CMTM_CNT } 명</c:if></td>
					</tr>
					<tr>
						<th>외부평가위원배수</th>
						<td><c:if test="${not empty estmMngMstDetail.OUT_ESTM_CMTM_TMES }">${estmMngMstDetail.OUT_ESTM_CMTM_TMES } 배수</c:if></td>
						<th>내부평가위원배수</th>
						<td><c:if test="${not empty estmMngMstDetail.INN_ESTM_CMTM_TMES }">${estmMngMstDetail.INN_ESTM_CMTM_TMES } 배수</c:if></td>
					</tr>
					<tr>
						<th>지정평가위원수</th>
						<td><c:if test="${not empty estmMngMstDetail.FIX_ESTM_CMTM_CNT }">${estmMngMstDetail.FIX_ESTM_CMTM_CNT } 명</c:if></td>
						<th></th>
						<td></td>
					</tr>
					<tr>
						<th>우선순위선정자</th>
						<td>${estmMngMstDetail.PRIO_RNK_SLCT_PE_NM }</td>
						<th>우선순위선정부서</th>
						<td>${estmMngMstDetail.PRIO_RNK_SLCT_DEPT_NM }</td>
					</tr>
					<tr>
						<th>비고</th>
						<td colspan="3">
							<textarea taView style="display: none;">${estmMngMstDetail.RMK}</textarea>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
		
		<div class="table-detail">
			<c:if test="${ (estmMngMstDetail.OUT_CMTM_SLCT_MTHD_SECD eq 'B') or (estmMngMstDetail.OUT_CMTM_SLCT_MTHD_SECD eq 'C') }">
				<!-- Top -->
				<div class="top-detail">
					<div class="type-fleft">
						<div class="contents-label">외부평가위원정보</div>
					</div>
				</div>
				<!-- //Top -->
				
				<table class="component-detail-table ">
					<colgroup>
						<col width="6%">
						<col width="5%">
						<col width="10%">
						<col width="6%">
						<col width="10%">
						<col width="10%">
						<col width="15%">
						<col width="*">
						<col width="10%">
						<col width="15%">
					</colgroup>
					<thead>
						<tr>
							<th>선정<span class="th-sub_br">차수</th>
							<th>순위</th>
							<th>평가위원명</th>
							<th>내/외부<span class="th-sub_br">구분</span></th>
							<th>대분류</th>
							<th>내역</th>
							<th>중분류</th>
							<th>소분류</th>
							<th>휴대폰번호</th>
							<th>이메일</th>
						</tr>
					</thead>
					<tbody>
						<!-- A003 : 평가위원순위선정요청 -->
						<c:if test="${not empty outEstmCmtmList}">
							<c:forEach var="data" items="${outEstmCmtmList}" varStatus="status">
								<tr>
									<td class="txt-center">${data.ESTM_CMTM_SLCT_NGR }</td>
									<td class="txt-center">${data.PRIO_RNK }</td>
									<td class="txt-center">${data.ESTM_CMTM_NM }</td>
									<td class="txt-center">${data.INO_CMTM_SECD_NM}</td>
									<td>${data.LLF_SECD_NM }</td>
									<td>${data.CNTN_SECD_NM }</td>
									<td>${data.MLF_SECD_NM }</td>
									<td>${data.SLF_SECD_NM }</td>
									<td>${data.CP_NO }</td>
									<td>${data.EMAL }</td>
								</tr>
							</c:forEach>
						</c:if>
					</tbody>
				</table>
			</c:if>
		</div>
			
		<div class="table-detail">
			<c:if test="${ (estmMngMstDetail.INN_CMTM_SLCT_MTHD_SECD eq 'B') or (estmMngMstDetail.INN_CMTM_SLCT_MTHD_SECD eq 'C') }">
				<!-- Top -->
				<div class="top-detail">
					<div class="type-fleft">
						<div class="contents-label">내부평가위원정보</div>
					</div>
				</div>
				<!-- //Top -->
				
				<table class="component-detail-table ">
					<colgroup>
						<col width="6%">
						<col width="5%">
						<col width="10%">
						<col width="6%">
						<col width="10%">
						<col width="10%">
						<col width="15%">
						<col width="*">
						<col width="10%">
						<col width="15%">
					</colgroup>
					<thead>
						<tr>
							<th>선정<span class="th-sub_br">차수</th>
							<th>순위</th>
							<th>평가위원명</th>
							<th>내/외부<span class="th-sub_br">구분</span></th>
							<th>대분류</th>
							<th>내역</th>
							<th>중분류</th>
							<th>소분류</th>
							<th>휴대폰번호</th>
							<th>이메일</th>
						</tr>
					</thead>
					<tbody>
						<!-- A003 : 평가위원순위선정요청 -->
						<c:if test="${not empty innEstmCmtmList}">
							<c:forEach var="data" items="${innEstmCmtmList}" varStatus="status">
								<tr>
									<td class="txt-center">${data.ESTM_CMTM_SLCT_NGR }</td>
									<td class="txt-center">${data.PRIO_RNK }</td>
									<td class="txt-center">${data.ESTM_CMTM_NM }</td>
									<td class="txt-center">${data.INO_CMTM_SECD_NM}</td>
									<td>${data.LLF_SECD_NM }</td>
									<td>${data.CNTN_SECD_NM }</td>
									<td>${data.MLF_SECD_NM }</td>
									<td>${data.SLF_SECD_NM }</td>
									<td>${data.CP_NO }</td>
									<td>${data.EMAL }</td>
								</tr>
							</c:forEach>
						</c:if>
					</tbody>
				</table>
			</c:if>
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
		
		<!-- bottom button -->
		<div class="bottom-buttons">
			<c:if test="${estmMngMstDetail.ESTM_PSCD eq 'A003' }"><!-- 평가위원순위선정요청 -->
				<a href="javascript:" class="btn-bottom type-b" id="prioRnkAutoSlctBtn">우선순위자동선별</a>
			</c:if>
			<a href="javascript:" class="btn-bottom type-a" id="listBtn">목록</a>
		</div>
		<!-- //bottom button -->
	
	</form>
	
</div>	
<!-- //Detail -->

<!-- LIST FORM -->
<form id="listFrm" method="POST">
	<input type="hidden" name="resourceName" value="${comFn:nvl(param.resourceName, resourceName)}">
</form>

<%-- 파일 VIEW 폼 --%>
<form id="fileViewFrm" method="POST">
	<input type="hidden" id="P_FILE_GRP_NO" name="P_FILE_GRP_NO" value="${estmMstFile.FILE_GRP_NO}">
</form>

<form id="searhFrm" method="POST">
	<input type="hidden" name="resourceDesc" value="${ param.resourceDesc}">
	<input type="hidden" name="resourceName" value="${comFn:nvl(param.resourceName, resourceName)}">
	<input type="hidden" name="P_ESTM_NO" value="${estmMngMstDetail.ESTM_NO }">
	<input type="hidden" name="P_ESTM_CMTM_SLCT_NGR">
</form>