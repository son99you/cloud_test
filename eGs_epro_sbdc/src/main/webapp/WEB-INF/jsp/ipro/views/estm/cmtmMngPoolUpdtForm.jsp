<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 평가관리 > 평가위원관리 > 평가위원POOL현황 수정
 *
 * <pre>
 * estm
 *    |_ cmtmMngPoolUpdtForm.jsp
 * 
 * </pre>
 * @date : 2021. 04. 19.
 * @version : 1.0
 * @author : 은우소프트 
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %> 
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %> 

<script type="text/javascript" src="${jsPath}/ipro/views/estm/cmtmMngPoolUpdtForm.js"></script>

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
					<li style="font-size: 30px; font-weight: 500;">평가위원POOL현황 수정</li>
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
	<form id="updtFrm" name="updtFrm" method="POST" enctype="multipart/form-data">
		<input type="hidden" name="resourceDesc" value="${ param.resourceDesc}">
		<input type="hidden" name="resourceName" value="${comFn:nvl(param.resourceName, resourceName)}">
		<input type="hidden" id="P_ESTM_CMTM_NO" name="P_ESTM_CMTM_NO" value="${estmCmtmPoolMstDetail.ESTM_CMTM_NO }">
		<input type="hidden" id="P_SLCT_YN" name="P_SLCT_YN" value="${estmCmtmPoolMstDetail.SLCT_YN }">
		
		<c:if test="${not empty estmCmtmEducList }">
			<input type="hidden" id="P_EDUC_FILE_GRP_NO" name="P_EDUC_FILE_GRP_NO" value="${estmCmtmEducList.get(0).FILE_GRP_NO }">		<!-- 학력 FILE_GRP_NO -->
		</c:if>
		<c:if test="${not empty estmCmtmPoolHffcFileList }">
			<input type="hidden" id="P_FILE_GRP_NO" name="P_FILE_GRP_NO" value="${estmCmtmPoolHffcFileList.get(0).FILE_GRP_NO }">	<!-- 첨부파일 FILE_GRP_NO -->
		</c:if>
		<input type="hidden" name="P_FILE_MOD_YN" value="N" class="component-input w100"> 		<!-- 첨부파일 수정여부 -->
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
						<th><i class="icon-necessary"></i>평가위원명</th>
						<td><input type="text" id="P_ESTM_CMTM_NM" name="P_ESTM_CMTM_NM" class="component-input" maxlength="100" value="${estmCmtmPoolMstDetail.ESTM_CMTM_NM }"></td>
						<th><i class="icon-necessary"></i>내/외부 구분</th>
						<td>
							<comTag:comCmcdCdValueComboBox id="P_INO_CMTM_SECD" name="P_INO_CMTM_SECD" cdId="INO_CMTM_SECD"  selectKey= "${estmCmtmPoolMstDetail.INO_CMTM_SECD}" headerKey="" headerValue="전체" className="component-select"/>
						</td>
					</tr>
					<tr>
						<th><i class="icon-necessary"></i>평가위원등록자</th>
						<td>
							<div class="component-input-search type-division">
								<input type="text" id="P_ESTM_CMTM_REGR_NM" name="P_ESTM_CMTM_REGR_NM" class="component-input" readonly="readonly" value="${estmCmtmPoolMstDetail.ESTM_CMTM_REGR_NM }" }>
								<input type="hidden" id="P_ESTM_CMTM_REGR_ID" name="P_ESTM_CMTM_REGR_ID" value="${estmCmtmPoolMstDetail.ESTM_CMTM_REGR_ID }" }>
								<a href="javascript:" class="btn-search" id="estmCmtmRegrSrchBtn"></a>
							</div>
						</td>
						<th><i class="icon-necessary"></i>평가위원등록부서</th>
						<td>
							<input type="text" id="P_ESTM_CMTM_REG_DEPT_NM" name="P_ESTM_CMTM_REG_DEPT_NM" class="component-input" readonly="readonly" value="${estmCmtmPoolMstDetail.ESTM_CMTM_REG_DEPT_NM }">
							<input type="hidden" id="P_ESTM_CMTM_REG_DEPT_NO" name="P_ESTM_CMTM_REG_DEPT_NO" value="${estmCmtmPoolMstDetail.ESTM_CMTM_REG_DEPT_NO }">
						</td>
					</tr>
					<tr>
						<th>생년월일</th>
						<td><input type="text" id="P_BRDT" name="P_BRDT" class="component-input" maxlength="8" value="${estmCmtmPoolMstDetail.BRDT }" numeric>- 없이 입력&nbsp;&nbsp;(예: 19900101)</td>
						<th><i class="icon-necessary"></i>수기등록여부</th>
						<td>
							<%-- <comTag:comCmcdCdValueComboBox id="P_HNDW_REG_YN" name="P_HNDW_REG_YN" cdId="HNDW_REG_YN" selectKey="${comFn:nvl(estmCmtmPoolMstDetail.HNDW_REG_YN, 'Y')}" headerValue="전체"  className="component-select"/> --%>
							${comFn:nvl(estmCmtmPoolMstDetail.HNDW_REG_YN_NM, '아니오') }
							<input type="hidden" id="P_HNDW_REG_YN" name="P_HNDW_REG_YN" value="${comFn:nvl(estmCmtmPoolMstDetail.HNDW_REG_YN, 'N') }"/>
						</td>
					</tr>
					<tr>
						<th><i class="icon-necessary"></i>휴대폰전화번호</th>
						<td><input type="text" id="P_CP_NO" name="P_CP_NO" class="component-input" maxlength="11" numeric value="${estmCmtmPoolMstDetail.CP_NO }">- 없이 입력</td>
						<th><i class="icon-necessary"></i>전화번호</th>
						<td><input type="text" id="P_TEL_NO" name="P_TEL_NO" class="component-input" maxlength="11" numeric value="${estmCmtmPoolMstDetail.TEL_NO }">- 없이 입력</td>
					</tr>
					<tr>
						<th><i class="icon-necessary"></i>이메일</th>
						<td><input type="text" id="P_EMAL" name="P_EMAL" class="component-input w100" maxlength="500" value="${estmCmtmPoolMstDetail.EMAL }"></td>
						<th>팩스번호</th>
						<td><input type="text" id="P_FX_NO" name="P_FX_NO" class="component-input" maxlength="11"  numeric value="${estmCmtmPoolMstDetail.FX_NO }">- 없이 입력</td>
					</tr>
					<tr>
						<th>주소</th>
						<td colspan="3">
							(우편번호)&nbsp;&nbsp;&nbsp;<input type="text" id="P_ZIP" name="P_ZIP" class="component-input w20" maxlength="6" numeric value="${estmCmtmPoolMstDetail.ZIP}">
							<input type="text" id="P_ADDR_1" name="P_ADDR_1" class="component-input w70" maxlength="1000" value="${estmCmtmPoolMstDetail.ADDR_1} ${estmCmtmPoolMstDetail.ADDR_2}">
						</td>
					</tr>
					<tr>
						<th>대분류</th>
						<td><comTag:comCmcdCdValueComboBox id="P_LLF_SECD" name="P_LLF_SECD" cdId="LLF_SECD" headerKey="" headerValue="전체" className="component-select" selectKey="${estmCmtmPoolMstDetail.LLF_SECD}"/></td>
						<th>내역</th>
						<td><comTag:comCmcdCdValueComboBox id="P_CNTN_SECD" name="P_CNTN_SECD" cdId="CNTN_SECD" headerKey="" headerValue="전체" className="component-select" selectKey="${estmCmtmPoolMstDetail.CNTN_SECD}"/></td>
					</tr>
					<tr>
						<th>중분류</th>
						<td><comTag:comCmcdCdValueComboBox id="P_MLF_SECD" name="P_MLF_SECD" cdId="MLF_SECD" headerKey="" headerValue="전체" className="component-select" selectKey="${estmCmtmPoolMstDetail.MLF_SECD}"/></td>
						<th>소분류</th>
						<td><comTag:comCmcdCdValueComboBox id="P_SLF_SECD" name="P_SLF_SECD" cdId="SLF_SECD" headerKey="" headerValue="전체" className="component-select" selectKey="${estmCmtmPoolMstDetail.SLF_SECD}"/></td>
					</tr>
					<tr>
						<th>직업</th>
						<td><input type="text" id="P_JOB_NM" name="P_JOB_NM" class="component-input w100" maxlength="500" value="${estmCmtmPoolMstDetail.JOB_NM }"></td>
						<th>최종학력</th>
						<td><input type="text" id="P_LT_EDUC" name="P_LT_EDUC" class="component-input w100" maxlength="500" value="${estmCmtmPoolMstDetail.LT_EDUC }"></td>
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
						<th><i class="icon-necessary"></i>소속회사명</th>
						<td><input type="text" id="P_ESTM_CMTM_BLNG_AGNC" name="P_ESTM_CMTM_BLNG_AGNC" class="component-input w100" maxlength="100" value="${estmCmtmPoolMstDetail.ESTM_CMTM_BLNG_AGNC }"></td>
						<th>사업자번호</th>
						<td><input type="text" id="P_BLNG_AGNC_BIZRNO" name="P_BLNG_AGNC_BIZRNO" class="component-input" maxlength="10" value="${estmCmtmPoolMstDetail.BLNG_AGNC_BIZRNO }" numeric>- 없이 입력</td>
					</tr>
					<tr>
						<th><i class="icon-necessary"></i>부서</th>
						<td><input type="text" id="P_ESTM_CMTM_BLNG_DEPT" name="P_ESTM_CMTM_BLNG_DEPT" class="component-input" maxlength="100" value="${estmCmtmPoolMstDetail.ESTM_CMTM_BLNG_DEPT }"></td>
						<th><i class="icon-necessary"></i>직위</th>
						<td><input type="text" id="P_ESTM_CMTM_OFPS" name="P_ESTM_CMTM_OFPS" class="component-input" maxlength="100" value="${estmCmtmPoolMstDetail.ESTM_CMTM_OFPS }"></td>
					</tr>
					<tr>
						<th>회사팩스번호</th>
						<td><input type="text" id="P_BLNG_AGNC_FX_NO" name="P_BLNG_AGNC_FX_NO" class="component-input" maxlength="30" numeric value="${estmCmtmPoolMstDetail.BLNG_AGNC_FX_NO }">- 없이 입력</td>
						<th>회사전화번호</th>
						<td><input type="text" id="P_BLNG_AGNC_TEL_NO" name="P_BLNG_AGNC_TEL_NO" class="component-input" maxlength="30" numeric value="${estmCmtmPoolMstDetail.BLNG_AGNC_TEL_NO }">- 없이 입력</td>
					</tr>
					<tr>
						<th>회사주소</th>
						<td colspan="3">
							(우편번호)&nbsp;&nbsp;&nbsp;<input type="text" id="P_BLNG_AGNC_ZIP" name="P_BLNG_AGNC_ZIP" class="component-input w20" maxlength="6" numeric value="${estmCmtmPoolMstDetail.BLNG_AGNC_ZIP}">
							<input type="text" id="P_BLNG_AGNC_ADDR_1" name="P_BLNG_AGNC_ADDR_1" class="component-input w70" maxlength="1000" value="${estmCmtmPoolMstDetail.BLNG_AGNC_ADDR_1}">
						</td>
					</tr>
					<tr>
						<th><i class="icon-necessary"></i>홈페이지주소</th>
						<td><input type="text" id="P_BLNG_AGNC_HMPG_ADDR" name="P_BLNG_AGNC_HMPG_ADDR" class="component-input w100" maxlength="400" value="${estmCmtmPoolMstDetail.BLNG_AGNC_HMPG_ADDR }"></td>
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
							<i class="icon-necessary"></i>재직증명서 사본
						</th>
						<c:if test="${estmCmtmPoolMstDetail.HNDW_REG_YN eq 'Y' }">
							<c:if test="${not empty estmCmtmPoolHffcFileList }">
								<c:forEach var="data" items="${estmCmtmPoolHffcFileList}" varStatus="status">
									<td>
										<a href="javascript:pageObj.download('${data.FILE_GRP_NO}', '${data.FILE_SN}')">${data.SYS_FILE_NM}</a>
										<span style="float:right;">
											<button type="button" class="component-button-s type-a" onclick="mstFileModBtn(this);">수정</button>
										</span>
									</td>
									<td style="display:none;">
										<span>
											<input type="file" id="P_HFFC_FILE" style="width: 70%;">
											<input type="hidden" name="P_FILE_DOC_SECD" value="${data.FILE_DOC_SECD}" disabled="disabled">
										</span>
										<span style="float:right;"><button type="button" class="component-button-s type-a" onclick="mstFileCancleBtn(this);">취소</button></span>				
									</td>
								</c:forEach>
							</c:if>
							<c:if test="${empty estmCmtmPoolHffcFileList }">
								<td>
									<span>
										<input type="file" name="P_FILE" id="P_HFFC_FILE" style="width: 70%;" onclick="mstFileSet(this)">
										<input type="hidden" name="P_FILE_DOC_SECD" value="HFFC" disabled="disabled">
									</span>
								</td>
							</c:if>
						</c:if>
						<c:if test="${estmCmtmPoolMstDetail.HNDW_REG_YN ne 'Y' }">
							<!-- HFFC_PROOF_FILE_ID,HFFC_PRODDF_FILE_STRE_COURS,HFFC_PRODDF_WON_FILE_NM -->
							<td>
								<c:if test="${not empty estmCmtmPoolMstDetail.HFFC_PRODDF_FILE_STRE_COURS }">
									<a href="javascript:pageObj.poolDownload('${estmCmtmPoolMstDetail.HFFC_PRODDF_FILE_STRE_COURS}', '${estmCmtmPoolMstDetail.HFFC_PRODDF_WON_FILE_NM}')">${estmCmtmPoolMstDetail.HFFC_PRODDF_WON_FILE_NM}</a>
								</c:if>
							</td>
						</c:if>
					</tr>
					
					<tr>
						<th>
							자격증명서 사본
						</th>
						<c:if test="${estmCmtmPoolMstDetail.HNDW_REG_YN eq 'Y' }">
							<c:if test="${not empty estmCmtmPoolCrqfcFileList }">
								<c:forEach var="data" items="${estmCmtmPoolCrqfcFileList}" varStatus="status">
									<td>
										<a href="javascript:pageObj.download('${data.FILE_GRP_NO}', '${data.FILE_SN}')">${data.SYS_FILE_NM}</a>
										<span style="float:right;">
											<button type="button" class="component-button-s type-a" onclick="mstFileModBtn(this);">수정</button>
										</span>
									</td>
									<td style="display:none;">
										<span>
											<input type="file" id="P_CRQFC_FILE" style="width: 70%;">
											<input type="hidden" name="P_FILE_DOC_SECD" value="${data.FILE_DOC_SECD}">
										</span>
										<span style="float:right;"><button type="button" class="component-button-s type-a" onclick="mstFileCancleBtn(this);">취소</button></span>				
									</td>
								</c:forEach>
							</c:if>
							<c:if test="${empty estmCmtmPoolCrqfcFileList }">
								<td>
									<span>
										<input type="file" name="P_FILE" style="width: 70%;" id="P_CRQFC_FILE" onclick="mstFileSet(this)">
										<input type="hidden" name="P_FILE_DOC_SECD" value="CRQFC" disabled="disabled">
									</span>
								</td>
							</c:if>
						</c:if>
						<c:if test="${estmCmtmPoolMstDetail.HNDW_REG_YN ne 'Y' }">
								<!-- CRQFC_FILE_ID,CRQFC_FILE_STRE_COURS,CRQFC_WON_FILE_NM -->
								<td>
									<c:if test="${not empty estmCmtmPoolMstDetail.CRQFC_FILE_STRE_COURS }">
										<a href="javascript:pageObj.poolDownload('${estmCmtmPoolMstDetail.CRQFC_FILE_STRE_COURS}', '${estmCmtmPoolMstDetail.CRQFC_WON_FILE_NM}')">${estmCmtmPoolMstDetail.CRQFC_WON_FILE_NM}</a>
									</c:if>
								</td>
							</c:if>
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
				
				<div class="type-fright">
					<a href="javascript:" class="component-button-s type-add" id="careAddBtn">추가</a>
					<a href="javascript:" class="component-button-s type-del" id="careDelBtn">삭제</a>
				</div>
				
			</div>
			<!-- //Top -->
			
			<table class="component-detail-table">
				<colgroup>
					<col width="50">
					<col width="*">
					<col width="*">
					<col width="100">
					<col width="*">
				</colgroup>
				<thead>
					<tr>
						<th>선택</th>
						<th>사업명</th>
						<th>평가분야</th>
						<th>수행년도</th>
						<th>기관명</th>
					</tr>
				</thead>
				<tbody id="careHidTbdy" style="display: none;">
					<tr>
						<td class="txt-center">
							<label class="component-checkbox">
								<input type="checkbox" name="careCbx">
								<i></i>
							</label>
						</td>
						<td><input type="text" name="P_BSNS_NM" class="component-input w100"></td>
						<td><input type="text" name="P_ESTM_SPHE" class="component-input w100"></td>
						<td class="txt-center"><input type="text" name="P_RUN_YR" class="component-input w70" maxlength="4" numeric> 년</td>
						<td><input type="text" name="P_AGNC_NM" class="component-input w100"></td>
					</tr>
				</tbody>
				<tbody id="careShowTbdy">
					<c:forEach var="data" items="${estmCmtmCareList}" varStatus="status">
						<tr>
							<td class="txt-center">
								<label class="component-checkbox">
									<input type="checkbox" name="careCbx">
									<i></i>
								</label>
							</td>
							<td>
								<input type="text" name="P_BSNS_NM" value="${data.BSNS_NM }" class="component-input w100">
							</td>
							<td><input type="text" name="P_ESTM_SPHE" value="${data.ESTM_SPHE }" class="component-input w100"></td>
							<td class="txt-center"><input type="text" name="P_RUN_YR" value="${data.RUN_YR }" class="component-input w50" maxlength="4" numeric> 년</td>
							<td><input type="text" name="P_AGNC_NM" value="${data.AGNC_NM }" class="component-input w100"></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		
		<div class="table-detail">
			<!-- Top -->
			<div class="top-detail">
				<div class="type-fleft">
					<div class="contents-label">학력</div>
				</div>
				
				<div class="type-fright">
					<a href="javascript:" class="component-button-s type-add" id="educAddBtn">추가</a>
					<a href="javascript:" class="component-button-s type-del" id="educDelBtn">삭제</a>
				</div>
				
			</div>
			<!-- //Top -->
			<div style="overflow-x: scroll; overflow-y:hidden">
				<table class="component-detail-table">
					<colgroup>
						<col width="50px">
						<col width="200px">
						<col width="200px">
						<col width="200px">
						<col width="400px">
						<col width="300px">
					</colgroup>
					<thead>
						<tr>
							<th>선택</th>
							<th>학위</th>
							<th>학교</th>
							<th>전공</th>
							<th>기간</th>
							<th>첨부파일</th>
						</tr>
					</thead>
					<tbody id="educHidTbdy" style="display: none;">
						<tr>
							<td class="txt-center">
								<label class="component-checkbox">
									<input type="checkbox" name="educCbx">
									<i></i>
								</label>
							</td>
							<td>
								<input type="text" name="P_MSDG" class="component-input w100">
								<input type="hidden" name="P_EDUC_SEQ" value="" class="component-input w100">
							</td>
							<td><input type="text" name="P_SHL" class="component-input w100"></td>
							<td><input type="text" name="P_SCCT" class="component-input w100"></td>
							<td>
								<input type="text" name="P_TE_FROM_Y" class="component-input w20" maxlength="4" value="" numeric>&nbsp;년&nbsp;
								<input type="text" name="P_TE_FROM_M" class="component-input w10" maxlength="2"  value="" numeric>&nbsp;월&nbsp;
								~&nbsp;&nbsp;<input type="text" name="P_TE_TO_Y" class="component-input w20" maxlength="4" value="" numeric>&nbsp;월&nbsp;
								<input type="text" name="P_TE_TO_M" class="component-input w10" maxlength="2"  value="" numeric>&nbsp;월&nbsp;
							</td>
							<td>
								<input type="file" name="P_EDUC_FILE" style="width: 70%;">
								<input type="hidden" value="${data.FILE_SZ }" name="P_FILE_SZ">
								<input type="hidden" value="${data.SV_FILE_NM }" name="P_SV_FILE_NM">
								<input type="hidden" value="${data.SYS_FILE_NM }" name="P_SYS_FILE_NM">
								<input type="hidden" value="${data.FILE_LCTN }" name="P_FILE_LCTN">
							</td>
						</tr>
					</tbody>
					<tbody id="educShowTbdy">
						<c:forEach var="data" items="${estmCmtmEducList}" varStatus="status">
							<tr>
								<td class="txt-center">
									<label class="component-checkbox">
										<input type="checkbox" name="educCbx">
										<i></i>
									</label>
								</td>
								<td>
									<input type="text" name="P_MSDG" value="${data.MSDG }" class="component-input w100">
									<input type="hidden" name="P_EDUC_SEQ" value="${data.EDUC_SEQ }" class="component-input w100">
									<input type="hidden" name="P_MOD_YN" value="N" class="component-input w100">
								</td>
								<td><input type="text" name="P_SHL" value="${data.SHL }" class="component-input w100"></td>
								<td><input type="text" name="P_SCCT" value="${data.SCCT }" class="component-input w100"></td>
								<td>
									<input type="text" name="P_TE_FROM_Y" class="component-input w20" maxlength="4" value="${comFn:formatDate(data.TE_FROM,'yyyyMM','yyyy')}" numeric>&nbsp;년&nbsp;
									<input type="text" name="P_TE_FROM_M" class="component-input w10" maxlength="2"  value="${comFn:formatDate(data.TE_FROM,'yyyyMM','MM')}" numeric>&nbsp;월&nbsp;
									~&nbsp;<input type="text" name="P_TE_TO_Y" class="component-input w20" maxlength="4" value="${comFn:formatDate(data.TE_TO,'yyyyMM','yyyy')}" numeric>&nbsp;년&nbsp;
									<input type="text" name="P_TE_TO_M" class="component-input w10" maxlength="2" value="${comFn:formatDate(data.TE_TO,'yyyyMM','MM')}" numeric>&nbsp;월&nbsp;
								</td>
								</td>
								<c:if test="${not empty data.FILE_GRP_NO }">
									<td>
										<a href="javascript:pageObj.download('${data.FILE_GRP_NO}', '${data.FILE_SN}')">
											${data.SYS_FILE_NM }
										</a>					
										<span style="float:right;">
											<button type="button" class="component-button-s type-a" onclick="fileModBtn(this);">수정</button>
										</span>
										<input type="hidden" value="${data.FILE_SZ }" name="P_FILE_SZ">
										<input type="hidden" value="${data.SV_FILE_NM }" name="P_SV_FILE_NM">
										<input type="hidden" value="${data.SYS_FILE_NM }" name="P_SYS_FILE_NM">
										<input type="hidden" value="${data.FILE_LCTN }" name="P_FILE_LCTN">
									</td>
									<td style="display:none;">
										<span>
											<input type="file" id="P_EDUC_FILE" style="width: 70%;">
										</span>
										<span style="float:right;"><button type="button" class="component-button-s type-a" onclick="fileCancleBtn(this);">취소</button></span>				
									</td>
								</c:if>
								<c:if test="${empty data.FILE_GRP_NO }">
									<td>
										<span>
											<input type="file" id="P_EDUC_FILE" name="P_EDUC_FILE" style="width: 70%;" onclick="fileSet(this)">
											<input type="hidden" name="P_FILE_SZ">
											<input type="hidden" name="P_SV_FILE_NM">
											<input type="hidden" name="P_SYS_FILE_NM">
											<input type="hidden" name="P_FILE_LCTN">
										</span>
									</td>
								</c:if>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
		
		<div class="table-detail">
			<!-- Top -->
			<div class="top-detail">
				<div class="type-fleft">
					<div class="contents-label">직장경력</div>
				</div>
				
				<div class="type-fright">
					<a href="javascript:" class="component-button-s type-add" id="ofcCareAddBtn">추가</a>
					<a href="javascript:" class="component-button-s type-del" id="ofcCareDelBtn">삭제</a>
				</div>
				
			</div>
			<!-- //Top -->
			
			<table class="component-detail-table">
				<colgroup>
					<col width="50">
					<col width="*">
					<col width="*">
					<col width="*">
					<col width="100">
					<col width="*">
				</colgroup>
				<thead>
					<tr>
						<th>선택</th>
						<th>직장명</th>
						<th>근무부서</th>
						<th>직위</th>
						<th>근속년수</th>
						<th>담당업무_실적</th>
					</tr>
				</thead>
				<tbody id="ofcCareHidTbdy" style="display: none;">
					<tr>
						<td class="txt-center">
							<label class="component-checkbox">
								<input type="checkbox" name="ofcCareCbx">
								<i></i>
							</label>
						</td>
						<td><input type="text" name="P_OFC_NM" class="component-input w100"></td>
						<td><input type="text" name="P_WRK_DEPT_NM" class="component-input w100"></td>
						<td><input type="text" name="P_OPNM" class="component-input w100"></td>
						<td class="txt-center"><input type="text" name="P_WRK_YEAR_CNT" class="component-input w50" maxlength="2" numeric> 년</td>
						<td><input type="text" name="P_CHRG_TSK_ACPS" class="component-input w100"></td>
					</tr>
				</tbody>
				<tbody id="ofcCareShowTbdy">
					<c:forEach var="data" items="${estmCmtmOfcCareList}" varStatus="status">
						<tr>
							<td class="txt-center">
								<label class="component-checkbox">
									<input type="checkbox" name="ofcCareCbx">
									<i></i>
								</label>
							</td>
							<td><input type="text" name="P_OFC_NM" value="${data.OFC_NM}" class="component-input w100"></td>
							<td><input type="text" name="P_WRK_DEPT_NM" value="${data.WRK_DEPT_NM}" class="component-input w100"></td>
							<td><input type="text" name="P_OPNM" value="${data.OPNM}" class="component-input w100"></td>
							<td class="txt-center"><input type="text" name="P_WRK_YEAR_CNT" value="${data.WRK_YEAR_CNT}" class="component-input w50" maxlength="2" numeric> 년</td>
							<td><input type="text" name="P_CHRG_TSK_ACPS" value="${data.CHRG_TSK_ACPS}" class="component-input w100"></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		
		<div class="table-detail">
			<!-- Top -->
			<div class="top-detail">
				<div class="type-fleft">
					<div class="contents-label">자격증 및 면허</div>
				</div>
				
				<div class="type-fright">
					<a href="javascript:" class="component-button-s type-add" id="crqfAddBtn">추가</a>
					<a href="javascript:" class="component-button-s type-del" id="crqfDelBtn">삭제</a>
				</div>
				
			</div>
			<!-- //Top -->
			
			<table class="component-detail-table">
				<colgroup>
					<col width="50">
					<col width="*">
					<col width="*">
					<col width="*">
				</colgroup>
				<thead>
					<tr>
						<th>선택</th>
						<th>자격증명</th>
						<th>발행기관</th>
						<th>취득일</th>
					</tr>
				</thead>
				<tbody id="crqfHidTbdy" style="display: none;">
					<tr>
						<td class="txt-center">
							<label class="component-checkbox">
								<input type="checkbox" name="crqfCbx">
								<i></i>
							</label>
						</td>
						<td class="txt-center"><input type="text" name="P_CRQF_NM" class="component-input w100"></td>
						<td class="txt-center"><input type="text" name="P_PBLS_AGNC" class="component-input w100"></td>
						<td class="txt-center">
							<div class="area-calen">
								<div class="component-calen">
									<div class="data-calen">
										<input type="text" name="P_ACQS_DE" class="component-input" date>
									</div>
								</div>
							</div>
						</td>
					</tr>
				</tbody>
				<tbody id="crqfShowTbdy">
					<c:if test="${not empty estmCmtmCrqfList }">
						<c:forEach var="data" items="${estmCmtmCrqfList}" varStatus="status">
							<tr>
								<td class="txt-center">
									<label class="component-checkbox">
										<input type="checkbox" name="crqfCbx">
										<i></i>
									</label>
								</td>
								<td class="txt-center"><input type="text" name="P_CRQF_NM" value="${data.CRQF_NM}" class="component-input w100"></td>
								<td class="txt-center"><input type="text" name="P_PBLS_AGNC" value="${data.PBLS_AGNC}" class="component-input w100"></td>
								<td class="txt-center">
									<input type="text" name="P_ACQS_DE" value="${comFn:formatDate(data.ACQS_DE, 'yyyyMMdd', 'yyyy-MM-dd')}" class="component-input" style="width:100px;" date>
								</td>
							</tr>
						</c:forEach>
					</c:if>
				</tbody>
			</table>
		</div>
		 
		<!-- bottom button -->
		<div class="bottom-buttons">
			<a href="javascript:" class="btn-bottom type-b" id="saveBtn">저장</a>
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

<!-- POPUP FORM -->
<form id="popupFrm" method="POST">
	<input type="hidden" name="resourceName" value="${comFn:nvl(param.resourceName, resourceName)}">
	<input type="hidden" name="resourceDesc" value="${param.resourceDesc}">
	<input type="hidden" name="setChargerGbn">
</form>

<form id="downloadFrm" method="POST">
	<input type="hidden" name="P_FILE_GRP_NO" value="">
	<input type="hidden" name="P_FILE_SN" value="">
	<input type="hidden" name="P_FILE_STRE_COURS" value="">
	<input type="hidden" name="P_WON_FILE_NM" value="">
</form>