<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 평가관리 > 평가위원관리 > 평가위원POOL현황 상세
 *
 * <pre>
 * estm
 *    |_ cmtmMngPoolDetail.jsp
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

<script type="text/javascript" src="${jsPath}/ipro/views/estm/cmtmMngPoolDetail.js"></script>

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
					<li style="font-size: 30px; font-weight: 500;">평가위원POOL현황 상세</li>
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
		<input type="hidden" id="P_ESTM_CMTM_NO" name="P_ESTM_CMTM_NO" value="${estmCmtmPoolMstDetail.ESTM_CMTM_NO }">
		
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
						<th>평가위원명</th>
						<td>${estmCmtmPoolMstDetail.ESTM_CMTM_NM }</td>
						<th>내/외부 구분</th>
						<td>${estmCmtmPoolMstDetail.INO_CMTM_SECD_NM }</td>
					</tr>
					<tr>
						<th>평가위원등록자</th>
						<td>${estmCmtmPoolMstDetail.ESTM_CMTM_REGR_NM }</td>
						<th>평가위원등록부서</th>
						<td>${estmCmtmPoolMstDetail.ESTM_CMTM_REG_DEPT_NM }</td>
					</tr>
					<tr>
						<th>생년월일</th>
						<td>${estmCmtmPoolMstDetail.BRDT }</td>
						<th>수기등록여부</th>
						<td>${estmCmtmPoolMstDetail.HNDW_REG_YN_NM }</td>
					</tr>
					<tr>
						<th>휴대폰전화번호</th>
						<td>${comFn:formatPhoneNumber(estmCmtmPoolMstDetail.CP_NO)}</td>
						<th>전화번호</th>
						<td>${comFn:formatPhoneNumber(estmCmtmPoolMstDetail.TEL_NO)}</td>
					</tr>
					<tr>
						<th>이메일</th>
						<td>${estmCmtmPoolMstDetail.EMAL }</td>
						<th>팩스번호</th>
						<td>${comFn:formatPhoneNumber(estmCmtmPoolMstDetail.FX_NO)}</td>
					</tr>
					<tr>
						<th>주소</th>
						<td colspan="3">
							(우편번호)&nbsp;&nbsp;&nbsp;${estmCmtmPoolMstDetail.ZIP}&nbsp;&nbsp;&nbsp;${estmCmtmPoolMstDetail.ADDR_1}&nbsp;${estmCmtmPoolMstDetail.ADDR_2}
							
						</td>
					</tr>
					<tr>
						<th>대분류</th>
						<td>${estmCmtmPoolMstDetail.LLF_SECD_NM }</td>
						<th>내역</th>
						<td>${estmCmtmPoolMstDetail.CNTN_SECD_NM }</td>
					</tr>
					<tr>
						<th>중분류</th>
						<td>${estmCmtmPoolMstDetail.MLF_SECD_NM }</td>
						<th>소분류</th>
						<td>${estmCmtmPoolMstDetail.SLF_SECD_NM }</td>
					</tr>
					<tr>
						<th>직업</th>
						<td>${estmCmtmPoolMstDetail.JOB_NM }</td>
						<th>최종학력</th>
						<td>${estmCmtmPoolMstDetail.LT_EDUC }</td>
					</tr>
				</tbody>
			</table>
		</div>

		<div class="table-detail">
			<!-- Top -->
			<div class="top-detail">
				<div class="type-fleft">
					<div class="contents-label">부가정보</div>
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
						<th>소속회사명</th>
						<td>${estmCmtmPoolMstDetail.ESTM_CMTM_BLNG_AGNC }</td>
						<th>사업자번호</th>
						<td>${comFn:formatBizNumber(estmCmtmPoolMstDetail.BLNG_AGNC_BIZRNO)}</td>
					</tr>
					<tr>
						<th>부서</th>
						<td>${estmCmtmPoolMstDetail.ESTM_CMTM_BLNG_DEPT }</td>
						<th>직위</th>
						<td>${estmCmtmPoolMstDetail.ESTM_CMTM_OFPS }</td>
					</tr>
					<tr>
						<th>회사팩스번호</th>
						<td>${comFn:formatPhoneNumber(estmCmtmPoolMstDetail.BLNG_AGNC_FX_NO)}</td>
						<th>회사전화번호</th>
						<td>${comFn:formatPhoneNumber(estmCmtmPoolMstDetail.BLNG_AGNC_TEL_NO)}</td>
					</tr>
					<tr>
						<th>회사주소</th>
						<td colspan="3">
							(우편번호)&nbsp;&nbsp;&nbsp;${estmCmtmPoolMstDetail.BLNG_AGNC_ZIP}&nbsp;&nbsp;&nbsp;${estmCmtmPoolMstDetail.BLNG_AGNC_ADDR_1}&nbsp;${estmCmtmPoolMstDetail.BLNG_AGNC_ADDR_2}
						</td>
					</tr>
					<tr>
						<th>홈페이지주소</th>
						<td>${estmCmtmPoolMstDetail.BLNG_AGNC_HMPG_ADDR }</td>
						<th></th>
						<td></td>
					</tr>
				</tbody>
			</table>
		</div>
		
		<div class="table-detail">
			<!-- Top -->
			<div class="top-detail">
				<div class="type-fleft">
					<div class="contents-label">첨부파일</div>
				</div>
			</div>
			<!-- //Top -->
	
			<table class="component-detail-table type-line-none">
				<colgroup>
					<col width="15%">
					<col width="85%">
				</colgroup>
				<tbody>
					<tr>
						<th>
							재직증명서 사본
						</th>
						<td>
							<c:if test="${estmCmtmPoolMstDetail.HNDW_REG_YN eq 'Y' }">
								<c:if test="${not empty estmCmtmPoolHffcFileList }">
									<c:forEach var="data" items="${estmCmtmPoolHffcFileList}" varStatus="status">
										<a href="javascript:pageObj.download('${data.FILE_GRP_NO}', '${data.FILE_SN}')">${data.SYS_FILE_NM}</a>
									</c:forEach>
								</c:if>
							</c:if>
							<c:if test="${estmCmtmPoolMstDetail.HNDW_REG_YN ne 'Y' }">
								<c:if test="${not empty estmCmtmPoolMstDetail.HFFC_PRODDF_FILE_STRE_COURS }">
									<a href="javascript:pageObj.poolDownload('${estmCmtmPoolMstDetail.HFFC_PRODDF_FILE_STRE_COURS}', '${estmCmtmPoolMstDetail.HFFC_PRODDF_WON_FILE_NM}')">${estmCmtmPoolMstDetail.HFFC_PRODDF_WON_FILE_NM}</a>
								</c:if>
							</c:if>
						</td>
					</tr>
					<tr>
						<th>
							자격증명서 사본
						</th>
						<td>
							<c:if test="${estmCmtmPoolMstDetail.HNDW_REG_YN eq 'Y' }">
								<c:if test="${not empty estmCmtmPoolCrqfcFileList }">
									<c:forEach var="data" items="${estmCmtmPoolCrqfcFileList}" varStatus="status">
										<a href="javascript:pageObj.download('${data.FILE_GRP_NO}', '${data.FILE_SN}')">${data.SYS_FILE_NM}</a>
									</c:forEach>
								</c:if>
							</c:if>
							<c:if test="${estmCmtmPoolMstDetail.HNDW_REG_YN ne 'Y' }">
								<c:if test="${not empty estmCmtmPoolMstDetail.CRQFC_FILE_STRE_COURS }">
									<a href="javascript:pageObj.poolDownload('${estmCmtmPoolMstDetail.CRQFC_FILE_STRE_COURS}', '${estmCmtmPoolMstDetail.CRQFC_WON_FILE_NM}')">${estmCmtmPoolMstDetail.CRQFC_WON_FILE_NM}</a>
								</c:if>
							</c:if>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
		
		<div class="table-detail">
			<!-- Top -->
			<div class="top-detail">
				<div class="type-fleft">
					<div class="contents-label">평가활동경력</div>
				</div>
			</div>
			<!-- //Top -->
			
			<table class="component-detail-table ">
				<colgroup>
					<col width="50">
					<col width="*">
					<col width="*">
					<col width="80">
					<col width="*">
				</colgroup>
				<thead>
					<tr>
						<th>번호</th>
						<th>사업명</th>
						<th>평가분야</th>
						<th>수행년도</th>
						<th>기관명</th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${empty estmCmtmCareList}">
						<tr>
							<td colspan="5" class="txt-center">검색 조건에 해당하는 데이터가 존재하지 않습니다.</td>
						</tr>
					</c:if>
					<c:if test="${not empty estmCmtmCareList }">
						<c:forEach var="data" items="${estmCmtmCareList}" varStatus="status">
							<tr>
								<td class="txt-center">${status.count}</td>
								<td>${data.BSNS_NM }</td>
								<td>${data.ESTM_SPHE }</td>
								<td class="txt-center">${data.RUN_YR } 년</td>
								<td>${data.AGNC_NM }</td>
							</tr>
						</c:forEach>
					</c:if>
				</tbody>
			</table>
		</div>
		
		<div class="table-detail">
			<!-- Top -->
			<div class="top-detail">
				<div class="type-fleft">
					<div class="contents-label">학력</div>
				</div>
			</div>
			<!-- //Top -->
			
			<table class="component-detail-table ">
				<colgroup>
					<col width="50">
					<col width="*">
					<col width="*">
					<col width="*">
					<col width="*">
					<col width="*">
				</colgroup>
				<thead>
					<tr>
						<th>번호</th>
						<th>학위</th>
						<th>학교</th>
						<th>전공</th>
						<th>기간</th>
						<th>첨부파일</th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${empty estmCmtmEducList}">
						<tr>
							<td colspan="6" class="txt-center">검색 조건에 해당하는 데이터가 존재하지 않습니다.</td>
						</tr>
					</c:if>
					<c:if test="${not empty estmCmtmEducList }">
						<c:forEach var="data" items="${estmCmtmEducList}" varStatus="status">
							<tr>
								<td class="txt-center">${status.count}</td>
								<td>${data.MSDG }</td>
								<td>${data.SHL }</td>
								<td>${data.SCCT }</td>
								<td class="txt-center">
									${comFn:formatDate(data.TE_FROM,'yyyyMM','yyyy/MM')}&nbsp;~&nbsp;${comFn:formatDate(data.TE_TO,'yyyyMM','yyyy/MM')}
								</td>
								<td class="txt-center">
									<c:if test="${not empty data.FILE_GRP_NO}">
										<a href="javascript:pageObj.download('${data.FILE_GRP_NO}', '${data.FILE_SN}')">
											<img src="${imagePath}/comm/sub/icon_file.png" alt="첨부파일">
										</a>					
									</c:if>
								</td>
							</tr>
						</c:forEach>
					</c:if>
				</tbody>
			</table>
		</div>

		<div class="table-detail">
			<!-- Top -->
			<div class="top-detail">
				<div class="type-fleft">
					<div class="contents-label">직장경력</div>
				</div>
			</div>
			<!-- //Top -->
			
			<table class="component-detail-table ">
				<colgroup>
					<col width="50">
					<col width="*">
					<col width="*">
					<col width="*">
					<col width="80">
					<col width="*">
				</colgroup>
				<thead>
					<tr>
						<th>번호</th>
						<th>직장명</th>
						<th>근무부서</th>
						<th>직위</th>
						<th>근속년수</th>
						<th>담당업무_실적</th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${empty estmCmtmOfcCareList}">
						<tr>
							<td colspan="6" class="txt-center">검색 조건에 해당하는 데이터가 존재하지 않습니다.</td>
						</tr>
					</c:if>
					<c:if test="${not empty estmCmtmOfcCareList }">
						<c:forEach var="data" items="${estmCmtmOfcCareList}" varStatus="status">
							<tr>
								<td class="txt-center">${status.count}</td>
								<td>${data.OFC_NM }</td>
								<td>${data.WRK_DEPT_NM }</td>
								<td>${data.OPNM }</td>
								<td class="txt-center">${data.WRK_YEAR_CNT } 년</td>
								<td>${data.CHRG_TSK_ACPS }</td>
							</tr>
						</c:forEach>
					</c:if>
				</tbody>
			</table>
		</div>
		
		<div class="table-detail">
			<!-- Top -->
			<div class="top-detail">
				<div class="type-fleft">
					<div class="contents-label">자격증 및 면허</div>
				</div>
			</div>
			<!-- //Top -->
			
			<table class="component-detail-table ">
				<colgroup>
					<col width="50">
					<col width="*">
					<col width="*">
					<col width="*">
				</colgroup>
				<thead>
					<tr>
						<th>번호</th>
						<th>자격증명</th>
						<th>발행기관</th>
						<th>취득일</th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${empty estmCmtmCrqfList}">
						<tr>
							<td colspan="4" class="txt-center">검색 조건에 해당하는 데이터가 존재하지 않습니다.</td>
						</tr>
					</c:if>
					<c:if test="${not empty estmCmtmCrqfList }">
						<c:forEach var="data" items="${estmCmtmCrqfList}" varStatus="status">
							<tr>
								<td class="txt-center">${status.count}</td>
								<td>${data.CRQF_NM }</td>
								<td>${data.PBLS_AGNC }</td>
								<td class="txt-center">${comFn:formatDate(data.ACQS_DE, 'yyyyMMdd', 'yyyy-MM-dd')}</td>
							</tr>
						</c:forEach>
					</c:if>
				</tbody>
			</table>
		</div>
		
		<!-- bottom button -->
		<div class="bottom-buttons">
			<c:if test="${estmCmtmPoolMstDetail.HNDW_REG_YN eq 'Y' }">
				<a href="javascript:" class="btn-bottom type-b" id="delBtn">삭제</a>
			</c:if>
			<a href="javascript:" class="btn-bottom type-b" id="updtBtn">수정</a>
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

<form id="downloadFrm" method="POST">
	<input type="hidden" name="P_FILE_GRP_NO">
	<input type="hidden" name="P_FILE_SN">
	<input type="hidden" name="P_FILE_STRE_COURS">
	<input type="hidden" name="P_WON_FILE_NM">
</form>