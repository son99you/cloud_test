<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 평가관리 > 평가진행현황 상세 - 기본정보
 *
 * <pre>
 * estm
 *    |_ estmProgDetail.jsp
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

<script type="text/javascript" src="${jsPath}/ipro/views/estm/estmProgDetail.js"></script>
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
					<li style="font-size: 30px; font-weight: 500;">평가진행현황 상세</li>
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
		<input type="hidden" name="P_ESTM_CMTM_SLCT_NGR" value="${estmMngMstDetail.MAX_ESTM_CMTM_SLCT_NGR }">
		<input type="hidden" name="P_CMPL_YN" value="${estmMngMstDetail.CMTM_SLCT_CMPL_YN }">
		<input type="hidden" name="P_ESTM_PSCD" value="${estmMngMstDetail.ESTM_PSCD }">
		<input type="hidden" name="P_ESTM_PROCD_SEQ">
		<input type="hidden" name="P_ESTM_PROCD_PSCD">
		<input type="hidden" name="P_ESTM_PROCD_NM">
		<input type="hidden" name="P_ESTM_NO_TRANS" value="${P_ESTM_NO_TRANS}">
		<input type="hidden"  id="resultCode" name="resultCode" value="${resultCode}">
		
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
					<a href="javascript:tabEvent(1);">기본정보</a>
				</li>
				<li>
					<a href="javascript:tabEvent(2);">평가대상</a>
				</li>
				<li>
					<a href="javascript:tabEvent(3);">평가위원</a>
				</li>
				<!--
					2021.04.15 손연우
					평가절차에 따른 탭 리스트형식으로 받아오기 
				 -->
				<c:forEach var="data" items="${estmTabList}" varStatus="status">
					<c:if test="${estmProcdDetail.ESTM_PROCD_SEQ eq data.ESTM_PROCD_SEQ }">
						<li class="is-selected">
							<a href="javascript:dynamicEstmTabEvent('${data.ESTM_PROCD_SEQ }','${data.ESTR_SECD }','detailFrm');">${data.ESTM_PROCD_NM }</a>
						</li>
					</c:if>
					<c:if test="${estmProcdDetail.ESTM_PROCD_SEQ ne data.ESTM_PROCD_SEQ }">
						<li>
							<a href="javascript:dynamicEstmTabEvent('${data.ESTM_PROCD_SEQ }','${data.ESTR_SECD }','detailFrm');">${data.ESTM_PROCD_NM }</a>
						</li>
					</c:if>
				</c:forEach>
				<li>
					<a href="javascript:tabEvent(7);">평가결과</a>
				</li>
				<li>
					<a href="javascript:tabEvent(8);">화상회의</a>
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
						<th>평가대상구분</th>
						<td>${estmMngMstDetail.ESTM_OBJ_SECD_NM }</td>
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
			<!-- Top -->
			<div class="top-detail">
				<div class="type-fleft">
					<div class="contents-label">평가절차</div>
				</div>
			</div>
			<!-- //Top -->

			<table class="component-detail-table ">
				<colgroup>
					<col width="5%">
					<col width="9%">
					<col width="9%">
					<col width="*">
					<col width="15%">
					<col width="15%">
					<col width="10%">
					<col width="10%">
					<col width="10%">
				</colgroup>
				<thead>
					<tr>
						<th>번호</th>
						<th>평가절차구분</th>
						<th>평가자구분</th>
						<th>평가절차명</th>
						<th>평가절차 시작일시</th>
						<th>평가절차 종료일시</th>
						<th>평가표보기</th>
						<th>진행상태</th>
						<th>진행상태변경</th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${empty estmProcdList}">
						<tr>
							<td colspan="9" class="txt-center">검색 조건에 해당하는 데이터가 존재하지 않습니다.</td>
						</tr>
					</c:if>
						
					<c:if test="${not empty estmProcdList}">
						<c:forEach var="data" items="${estmProcdList}" varStatus="status">
							<tr>
								<td class="txt-center">${data.ESTM_PROCD_SEQ }</td>
								<td class="txt-center">${data.ESTM_PROCD_SECD_NM }</td>
								<td class="txt-center">${data.ESTR_SECD_NM }</td>
								<td class="txt-center">${data.ESTM_PROCD_NM }</td>
								<td class="txt-center">${comFn:formatDate(data.ESTM_PROCD_ST_DT,'yyyyMMddHHmmss','yyyy-MM-dd HH:mm:ss')}</td>
								<td class="txt-center">${comFn:formatDate(data.ESTM_PROCD_END_DT,'yyyyMMddHHmmss','yyyy-MM-dd HH:mm:ss')}</td>
								<td class="txt-center"><button type="button" class="component-button-s type-a" onclick="pageObj.frmViewPopup('${data.ESTM_PROCD_SEQ}', '${estmMngMstDetail.ESTM_SECD}')" >보기</button></td>
								<td class="txt-center">${data.ESTM_PROCD_PSCD_NM }</td>
								<td class="txt-center">
								<c:if test="${estmMngMstDetail.ESTM_PSCD ne 'A005'}">
									<c:if test="${(data.ESTM_PROCD_PSCD eq '') or (empty data.ESTM_PROCD_PSCD) }">
										<button type="button"  class="component-button-s type-c" >진행</button>	
									</c:if>
								</c:if>
								<c:if test="${estmMngMstDetail.ESTM_PSCD eq 'A005'}">
									<c:if test="${(data.ESTM_PROCD_PSCD eq '') or (empty data.ESTM_PROCD_PSCD) }">
										<button type="button" class="component-button-s type-a" onclick="pageObj.estmProcdPscdUpdt('${data.ESTM_PROCD_SEQ}', 'B001', '${data.ESTM_PROCD_NM }')">진행</button>
									</c:if>
									<c:if test="${data.ESTM_PROCD_PSCD eq 'B001' }">
										<button type="button" class="component-button-s type-a" onclick="pageObj.estmProcdPscdUpdt('${data.ESTM_PROCD_SEQ}', 'B002', '${data.ESTM_PROCD_NM }')">완료</button>
									</c:if>
									<%-- <c:if test="${ (data.ESTM_PROCD_PSCD eq 'B002') and (data.NEXT_ESTM_PROCD_PSCD eq 'B001') and (data.MAX_ESTM_PROCD_SEQ ne data.ESTM_PROCD_SEQ)}"> --%>
									<c:if test="${ (data.ESTM_PROCD_PSCD eq 'B002') and (empty data.NEXT_ESTM_PROCD_PSCD) and (data.MAX_ESTM_PROCD_SEQ ne data.ESTM_PROCD_SEQ)}">
										<button type="button" class="component-button-s type-a" onclick="pageObj.estmProcdPscdUpdt('${data.ESTM_PROCD_SEQ}', 'B001', '${data.ESTM_PROCD_NM }')">재진행</button>
									</c:if>
									<c:if test="${ (data.ESTM_PROCD_PSCD eq 'B002') and (data.MAX_ESTM_PROCD_SEQ eq data.ESTM_PROCD_SEQ)}">
										<button type="button" class="component-button-s type-a" onclick="pageObj.estmProcdPscdUpdt('${data.ESTM_PROCD_SEQ}', 'B001', '${data.ESTM_PROCD_NM }')">재진행</button>
									</c:if>
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
				<div class="type-fleft">
					<div class="contents-label">심사위원 서명파일</div>
				</div>
				<!-- //Top -->
			</div>
			
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
			
			<c:if test="${estmMngMstDetail.ESTM_PSCD eq 'A001' }"><!-- 평가작성 -->
				<a href="javascript:" class="btn-bottom type-b" id="updtBtn">수정</a>
				<a href="javascript:" class="btn-bottom type-b" id="delBtn">삭제</a>
			</c:if>
			<c:if test="${estmMngMstDetail.ESTM_SECD eq 'E016' }"><!-- 크리에이터 육성 -->
				<c:if test="${(estmMngMstDetail.ESTM_PSCD eq 'A001') or (estmMngMstDetail.ESTM_PSCD eq 'A004') }"><!-- 평가작성, 평가위원순위선정완료 -->
					<a href="javascript:" class="btn-bottom type-b" id="estmAnncBtn">평가공고</a>
				</c:if>
			</c:if>
			<c:if test="${(estmMngMstDetail.ESTM_PSCD eq 'A002') or (estmMngMstDetail.ESTM_PSCD ne 'A005')  }"><!-- 평가공고, 평가시작 -->
				<a href="javascript:" class="btn-bottom type-b" id="estmStBtn">평가시작</a> 
			</c:if>
			
			<a href="javascript:" class="btn-bottom type-b" id="estmCnclBtn">평가취소</a>
			
			<c:if test="${estmMngMstDetail.ESTM_PSCD eq 'A005' }"><!-- 평가시작 -->
				<a href="javascript:" class="btn-bottom type-b" id="estmEndBtn">평가종료</a>
			</c:if>
			
			<a href="javascript:" class="btn-bottom type-b" id="estmHistBtn">진행이력</a>
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

<!-- POPUP FORM -->
<form id="popupFrm" method="POST">
	<input type="hidden" name="resourceName" value="${comFn:nvl(param.resourceName, resourceName)}">
	<input type="hidden" name="resourceDesc" value="${param.resourceDesc}">
	<input type="hidden" name="P_ESTM_NO" value="${estmMngMstDetail.ESTM_NO }">
	<input type="hidden" name="P_ESTM_PROCD_SEQ">
	<input type="hidden" name="P_ESTM_SECD">
</form>

<!-- DOWNLOAD FORM -->
<form id="downloadFrm" method="POST">
	<input type="hidden" name="P_FILE_SN">
	<input type="hidden" name="P_FILE_GRP_NO">
</form>