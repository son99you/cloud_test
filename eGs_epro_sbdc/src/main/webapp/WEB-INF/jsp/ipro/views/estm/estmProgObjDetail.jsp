<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 평가관리 > 평가진행현황 상세 - 평가대상
 *
 * <pre>
 * estm
 *    |_ estmProgObjDetail.jsp
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

<script type="text/javascript" src="${jsPath}/ipro/views/estm/estmProgObjDetail.js"></script>

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
	<form id="registFrm" name="registFrm" method="POST">
		<input type="hidden" name="P_REGR_NM" value="${sessionScope.loginResult.USR_NM}">
		<input type="hidden" name="P_USR_ID" value="${sessionScope.loginResult.USR_ID}">
		<input type="hidden" name="resourceDesc" value="${ param.resourceDesc}">
		<input type="hidden" name="resourceName" value="${comFn:nvl(param.resourceName, resourceName)}">
		<input type="hidden" name="P_ESTM_NO" value="${estmMngMstDetail.ESTM_NO }">
		<input type="hidden" name="P_ESTM_PSCD" value="${estmMngMstDetail.ESTM_PSCD }">
		<input type="hidden" name="P_ESTM_INFO_CNTC_NO" value="${estmMngMstDetail.ESTM_INFO_CNTC_NO }">
		
		<input type="hidden" id="excelResultCode" value="${excelResultCode }">
		
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
				<li>
					<a href="javascript:tabEvent(1);">기본정보</a>
				</li>
				<li class="is-selected">
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
		
		<!-- 평가위원이 상품인 경우 -->
		<c:if test="${estmMngMstDetail.ESTM_OBJ_SECD eq 'B'}">
		
			<div class="table-detail">
				<!-- Top -->
				<div class="top-detail">
					<div class="type-fleft">
						<div class="contents-label">평가대상정보 - 상품</div>
					</div>
					<div class="type-fright">
						<c:if test="${estmMngMstDetail.ESTM_PSCD ne 'A005' }">
							<c:if test="${not empty estmMngMstDetail.ESTM_INFO_CNTC_NO }">
								<a href="javascript:" class="component-button-s type-add" id="estmCntcObjBtn">평가대상불러오기</a>
							</c:if>
							<c:if test="${empty estmMngMstDetail.ESTM_INFO_CNTC_NO }">
								<a type="button" href="javascript:excelDwd('excelTemplatDownFrm', '/estm/excelTemplatDwld.do','${comFn:nvl(1, 0)}');" class="btn-download-s">
									<i class="icon-download"></i>업로드 양식 다운로드
								</a>
								<a type="button" id="obj_3_excelUploadBtn" class="btn-upload-s">
									<i class="icon-upload"></i>엑셀 업로드
								</a>
							</c:if>
							<!-- <a href="javascript:" class="component-button-s type-add" id="obj_2_excelUploadBtn">엑셀업로드</a> -->
							
							<c:if test="${empty estmMngMstDetail.ESTM_INFO_CNTC_NO }">
								<a href="javascript:" class="component-button-s type-add" id="obj_2_addBtn">대상추가</a>
							</c:if>
							
							
						</c:if>
					</div>
				</div>
				<!-- //Top -->
	
				<table class="component-detail-table ">
					<colgroup>
						<col width="6%">
						<col width="12%">
						<col width="*">
						<col width="12%">
						<col width="12%">
						<col width="12%">
						<col width="20%">
						<col width="12%">
					</colgroup>
					<thead>
						<tr>
							<th>선택</th>
							<th>상품번호</th>
							<th>상품명</th>
							<th>업체명</th>
							<th>사업자번호</th>
							<th>전화번호</th>
							<th>이메일</th>
							<th></th>
						</tr>
					</thead>
					<tbody id="obj_2_HidTbdy" style="display: none;">
						<tr>
							<td class="txt-center">
								<label class="component-checkbox">
									<input type="checkbox" name="objCbk">
									<i></i>
								</label>
								<input type="hidden" name="P_OBJ_YN" value="hndwRegist">
								<input type="hidden" name="P_ESTM_OBJ_SEQ">
								<input type="hidden" name="P_DEL_AT" value="">
							</td>
							<td><input type="text" name="P_ITEM_NO" class="component-input w100" maxlength="1000"></td>
							<td><input type="text" name="P_ITEM_NM" class="component-input w100" maxlength="1000"></td>
							<td><input type="text" name="P_VEND_NM" class="component-input w100" maxlength="100"></td>
							<td><input type="text" name="P_BIZRNO" class="component-input w100" maxlength="10"></td>
							<td><input type="text" name="P_TEL_NO" class="component-input w100" maxlength="11"></td>
							<td><input type="text" name="P_EMAL" class="component-input w100" maxlength="500"></td>
							<td class="txt-center"><a href="javascript:" class="component-button-s type-line" name="obj_2_delBtn" onclick="objRowDel(this)">행삭제</a></td>
						</tr>
					</tbody>
					<tbody id="obj_2_ShowTbdy">
						<c:if test="${not empty estmObjList}">
							<c:forEach var="data" items="${estmObjList}" varStatus="status">
								<tr>
									<td class="txt-center">
										<label class="component-checkbox">
											<input type="checkbox" name="objCbk" onclick="objDelCheck(this);">
											<i></i>
										</label>
										<input type="hidden" name="P_OBJ_YN">
										<input type="hidden" name="P_ESTM_OBJ_SEQ" value="${data.ESTM_OBJ_SEQ}">
									</td>
									<td class="txt-center">
										${data.ITEM_NO }
									</td>
									<td>
										<a href="javascript:" onclick="estmObjImstarsDetailView('popupFrm', '${data.ITEM_NO }', '${data.BIZRNO }', '${data.ESTM_OBJ_SEQ }')">${data.ITEM_NM }</a>
										<input type="hidden" name="P_ITEM_NO" value="${data.ITEM_NO }">
										<input type="hidden" name="P_ITEM_NM" value="${data.ITEM_NM }">
										<input type="hidden" name="P_VEND_NM" value="${data.VEND_NM }">
										<input type="hidden" name="P_BIZRNO" value="${data.BIZRNO }">
										<input type="hidden" name="P_TEL_NO" value="${data.TEL_NO }">
										<input type="hidden" name="P_EMAL" value="${data.EMAL }">
										<input type="hidden" name="P_DEL_AT" value="N">
									</td>
									<td>
										<a href="javascript:" onclick="estmObjImstarsMainView('popupFrm', '${data.ITEM_NO }', '${data.BIZRNO }', '${data.ESTM_OBJ_SEQ }')">${data.VEND_NM }</a>
									</td>
									<td class="txt-center">${comFn:formatBizNumber(data.BIZRNO)}</td>
									<td>${data.TEL_NO }</td>
									<td>${data.EMAL }</td>
									<td class="txt-center"><a href="javascript:" class="component-button-s type-a" onclick="pageObj.estmObjFileView('${data.ESTM_OBJ_SEQ }', '${data.RSDN_NO }', '${data.BIZRNO }', '${data.FILE_GRP_NO }')" style="font-size:11px;">첨부파일보기</a></td>
								</tr>
							</c:forEach>
						</c:if>
					</tbody>
				</table>
			</div>
		</c:if>
		<!-- //평가위원이 상품인 경우 -->
		
		
		<!-- 평가위원이 업체인 경우 -->
		<c:if test="${estmMngMstDetail.ESTM_OBJ_SECD eq 'A'}">
			<div class="table-detail">
				<!-- Top -->
				<div class="top-detail">
					<div class="type-fleft">
						<div class="contents-label">평가대상정보 - 업체</div>
					</div>
					<div class="type-fright">
						<c:if test="${estmMngMstDetail.ESTM_PSCD ne 'A005' }">
							<c:if test="${not empty estmMngMstDetail.ESTM_INFO_CNTC_NO }">
								<a href="javascript:" class="component-button-s type-add" id="estmCntcObjBtn">평가대상불러오기</a>
							</c:if>
							<c:if test="${empty estmMngMstDetail.ESTM_INFO_CNTC_NO }">
								<a type="button" href="javascript:excelDwd('excelTemplatDownFrm', '/estm/excelTemplatDwld.do','${comFn:nvl(1, 0)}');" class="btn-download-s">
									<i class="icon-download"></i>업로드 양식 다운로드
								</a>
								<a type="button" id="obj_3_excelUploadBtn" class="btn-upload-s">
									<i class="icon-upload"></i>엑셀 업로드
								</a>
							</c:if>
							<c:if test="${empty estmMngMstDetail.ESTM_INFO_CNTC_NO }">
								<a href="javascript:" class="component-button-s type-add" id="obj_3_addBtn">대상추가</a>
							</c:if>
						</c:if>
					</div>
				</div>
				<!-- //Top -->
	
				<table class="component-detail-table ">
					<colgroup>
						<col width="6%">
						<col width="*">
						<col width="15%">
						<col width="15%">
						<col width="25%">
						<col width="10%">
					</colgroup>
					<thead>
						<tr>
							<th>선택</th>
							<th>업체명</th>
							<th>사업자번호</th>
							<th>전화번호</th>
							<th>이메일</th>
							<th class="txt-center"></th>
						</tr>
					</thead>
					<tbody id="obj_3_HidTbdy" style="display: none;">
						<tr>
							<td class="txt-center">
								<label class="component-checkbox">
									<input type="checkbox" name="objCbk">
									<i></i>
								</label>
								<input type="hidden" name="P_OBJ_YN" value="hndwRegist">
								<input type="hidden" name="P_ESTM_OBJ_SEQ">
								<input type="hidden" name="P_DEL_AT" value="">
							</td>
							<td><input type="text" name="P_VEND_NM" class="component-input w100" maxlength="100"></td>
							<td><input type="text" name="P_BIZRNO" class="component-input w100" maxlength="10"></td>
							<td><input type="text" name="P_TEL_NO" class="component-input w100" maxlength="11"></td>
							<td><input type="text" name="P_EMAL" class="component-input w100" maxlength="500"></td>
							<td class="txt-center"><a href="javascript:" class="component-button-s type-line" name="obj_3_delBtn" onclick="objRowDel(this)">행삭제</a></td>
						</tr>
					</tbody>
					<tbody id="obj_3_ShowTbdy">
						<c:if test="${not empty estmObjList}">
							<c:forEach var="data" items="${estmObjList}" varStatus="status">
								<tr>
									<td class="txt-center">
										<label class="component-checkbox">
											<input type="checkbox" name="objCbk" onclick="objDelCheck(this);">
											<i></i>
										</label>
										<input type="hidden" name="P_OBJ_YN">
										<input type="hidden" name="P_ESTM_OBJ_SEQ" value="${data.ESTM_OBJ_SEQ}">
									</td>
									<td>
										<a href="javascript:" onclick="estmObjImstarsMainView('popupFrm', '${data.ITEM_NO }', '${data.BIZRNO }', '${data.ESTM_OBJ_SEQ }')">${data.VEND_NM }</a>
										
										<input type="hidden" name="P_VEND_NM" value="${data.VEND_NM }">
										<input type="hidden" name="P_BIZRNO" value="${data.BIZRNO }">
										<input type="hidden" name="P_TEL_NO" value="${data.TEL_NO }">
										<input type="hidden" name="P_EMAL" value="${data.EMAL }">
										<input type="hidden" name="P_DEL_AT" value="N">
									</td>
									<td class="txt-center">${comFn:formatBizNumber(data.BIZRNO)}</td>
									<td>${data.TEL_NO }</td>
									<td>${data.EMAL }</td>
									<td class="txt-center"><a href="javascript:" class="component-button-s type-a" onclick="pageObj.estmObjFileView('${data.ESTM_OBJ_SEQ }', '${data.RSDN_NO }', '${data.BIZRNO }', '${data.FILE_GRP_NO }')" style="font-size:11px;">첨부파일보기</a></td>
								</tr>
							</c:forEach>
						</c:if>
					</tbody>
				</table>
			</div>
		</c:if>
		<!-- //평가위원이 업체인 경우 -->
		
		
		
		<!-- 평가위원이 사람인 경우 -->
		<c:if test="${estmMngMstDetail.ESTM_OBJ_SECD eq 'C'}">
			<div class="table-detail">
				<!-- Top -->
				<div class="top-detail">
					<div class="type-fleft">
						<div class="contents-label">평가대상정보 - 사람(크리에이터)</div>
					</div>
					<div class="type-fright">
						<c:if test="${estmMngMstDetail.ESTM_PSCD ne 'A005' }">
							<c:if test="${not empty estmMngMstDetail.ESTM_INFO_CNTC_NO }">
								<a href="javascript:" class="component-button-s type-add" id="estmCntcObjBtn">평가대상불러오기</a>
							</c:if>
							<c:if test="${empty estmMngMstDetail.ESTM_INFO_CNTC_NO }">
								<a type="button" href="javascript:excelDwd('excelTemplatDownFrm', '/estm/excelTemplatDwld.do','${comFn:nvl(1, 0)}');" class="btn-download-s">
									<i class="icon-download"></i>업로드 양식 다운로드
								</a>
								<a type="button" id="obj_3_excelUploadBtn" class="btn-upload-s">
									<i class="icon-upload"></i>엑셀 업로드
								</a>
							</c:if>
							<c:if test="${empty estmMngMstDetail.ESTM_INFO_CNTC_NO }">
								<a href="javascript:" class="component-button-s type-add" id="obj_4_addBtn">대상추가</a>
							</c:if>
						</c:if>
					</div>
				</div>
				<!-- //Top -->
	
				<table class="component-detail-table ">
					<colgroup>
						<col width="6%">
						<col width="15%">
						<col width="15%">
						<col width="15%">
						<col width="25%">
						<col width="10%">
					</colgroup>
					<thead>
						<tr>
							<th>선택</th>
							<th>신청자명</th>
							<th>주민번호</th>
							<th>전화번호</th>
							<th>이메일</th>
							<th class="txt-center"></th>
						</tr>
					</thead>
					<tbody id="obj_4_HidTbdy" style="display: none;">
						<tr>
							<td class="txt-center">
								<label class="component-checkbox">
									<input type="checkbox" name="objCbk">
									<i></i>
								</label>
								<input type="hidden" name="P_OBJ_YN" value="hndwRegist">
								<input type="hidden" name="P_ESTM_OBJ_SEQ">
								<input type="hidden" name="P_DEL_AT" value="">
							</td>
							<td><input type="text" name="P_ESTM_OBJ_PE_NM" class="component-input" maxlength="100"></td>
							<td>
								<input type="text" name="P_RSDN_NO_1" class="component-input w50" maxlength="6"> - 
								<input type="text" name="P_RSDN_NO_2" class="component-input w20" maxlength="1">******
							</td>
							<td><input type="text" name="P_TEL_NO" class="component-input w100" maxlength="11"></td>
							<td><input type="text" name="P_EMAL" class="component-input w100" maxlength="500"></td>
							<td class="txt-center"><a href="javascript:" class="component-button-s type-line" name="obj_4_delBtn" onclick="objRowDel(this)">행삭제</a></td>
						</tr>
					</tbody>
					<tbody id="obj_4_ShowTbdy">
						<c:if test="${not empty estmObjList}">
							<c:forEach var="data" items="${estmObjList}" varStatus="status">
								<tr>
									<td class="txt-center">
										<label class="component-checkbox">
											<input type="checkbox" name="objCbk" onclick="objDelCheck(this);">
											<i></i>
										</label>
										<input type="hidden" name="P_OBJ_YN">
										<input type="hidden" name="P_ESTM_OBJ_SEQ" value="${data.ESTM_OBJ_SEQ}">
									</td>
									<td class="txt-center" style="cursor: pointer;" onclick="objDetailPopup('popupFrm', '${estmMngMstDetail.ESTM_OBJ_SECD}', '${data.ESTM_OBJ_PE_NO}');">
										${data.ESTM_OBJ_PE_NM }
										<input type="hidden" name="P_ESTM_OBJ_PE_NM" value="${data.ESTM_OBJ_PE_NM }">
										<input type="hidden" name="P_TEL_NO" value="${data.TEL_NO }">
										<input type="hidden" name="P_EMAL" value="${data.EMAL }">
										
										<input type="hidden" name="P_RSDN_NO_1" value="${data.RSDN_NO_1 }">
										<input type="hidden" name="P_RSDN_NO_2" value="${data.RSDN_NO_2 }">
										
										<input type="hidden" name="P_DEL_AT" value="N">
									</td>
									<td class="txt-center">
										${data.RSDN_NO_1 } - ${data.RSDN_NO_2 }******
									</td>
									<td>${data.TEL_NO }</td>
									<td>${data.EMAL }</td>
									<td class="txt-center"><a href="javascript:" class="component-button-s type-a" onclick="pageObj.estmObjFileView('${data.ESTM_OBJ_SEQ }', '${data.RSDN_NO }', '${data.BIZRNO }', '${data.FILE_GRP_NO }')" style="font-size:11px;">첨부파일보기</a></td>
								</tr>
							</c:forEach>
						</c:if>
					</tbody>
				</table>
			</div>
		</c:if>
		<!-- //평가위원이 사람인 경우 -->
		
		<!-- 평가위원이 제안제도인 경우 -->
		<c:if test="${estmMngMstDetail.ESTM_OBJ_SECD eq 'D'}">
			<div class="table-detail">
				<!-- Top -->
				<div class="top-detail">
					<div class="type-fleft">
						<div class="contents-label">평가대상정보 - 제안제도</div>
					</div>
					<div class="type-fright">
						<c:if test="${estmMngMstDetail.ESTM_PSCD ne 'A005' }">
							<%-- <c:if test="${not empty estmMngMstDetail.ESTM_INFO_CNTC_NO }">
								<a href="javascript:" class="component-button-s type-add" id="estmCntcObjBtn">평가대상불러오기</a>
							</c:if> --%>
							<%-- <c:if test="${empty estmMngMstDetail.ESTM_INFO_CNTC_NO }">
								<a type="button" href="javascript:excelDwd('excelTemplatDownFrm', '/estm/excelTemplatDwld.do','${comFn:nvl(1, 0)}');" class="btn-download-s">
									<i class="icon-download"></i>업로드 양식 다운로드
								</a>
								<a type="button" id="obj_3_excelUploadBtn" class="btn-upload-s">
									<i class="icon-upload"></i>엑셀 업로드
								</a>
							</c:if> --%>
							<c:if test="${empty estmMngMstDetail.ESTM_INFO_CNTC_NO }">
								<a href="javascript:" class="component-button-s type-add" id="obj_5_addBtn">대상추가</a>
							</c:if>
						</c:if>
					</div>
				</div>
				<!-- //Top -->
	
				<table class="component-detail-table ">
					<colgroup>
						<col width="6%">
						<col width="15%">
						<col width="35%">
						<col width="10%">
					</colgroup>
					<thead>
						<tr>
							<th>선택</th>
							<th>제안제도번호</th><!-- 제안제도번호 = 품목번호 -->
							<th>제안제도명</th><!-- 제안제도명 = 품목명 -->
							<th class="txt-center"></th>
						</tr>
					</thead>
					<tbody id="obj_5_HidTbdy" style="display: none;">
						<tr>
							<td class="txt-center">
								<label class="component-checkbox">
									<input type="checkbox" name="objCbk">
									<i></i>
								</label>
								<input type="hidden" name="P_OBJ_YN" value="hndwRegist">
								<input type="hidden" name="P_ESTM_OBJ_SEQ">
								<input type="hidden" name="P_DEL_AT" value="">
							</td>
							<td><input type="text" name="P_ESTM_OBJ_PE_NO" class="component-input w100" maxlength="100"></td>
							<td><input type="text" name="P_ESTM_OBJ_PE_NM" class="component-input w100" maxlength="100"></td>
							<td class="txt-center"><a href="javascript:" class="component-button-s type-line" name="obj_5_delBtn" onclick="objRowDel(this)">행삭제</a></td>
						</tr>
					</tbody>
					<tbody id="obj_5_ShowTbdy">
						<c:if test="${not empty estmObjList}">
							<c:forEach var="data" items="${estmObjList}" varStatus="status">
								<tr>
									<td class="txt-center">
										<label class="component-checkbox">
											<input type="checkbox" name="objCbk" onclick="objDelCheck(this);">
											<i></i>
										</label>
										<input type="hidden" name="P_OBJ_YN">
										<input type="hidden" name="P_ESTM_OBJ_SEQ" value="${data.ESTM_OBJ_SEQ}">
									</td>
									<td class="txt-center">
										${data.ESTM_OBJ_PE_NO }
										<input type="hidden" name="P_ESTM_OBJ_PE_NO" value="${data.ESTM_OBJ_PE_NO }">
									</td>
									<td class="txt-center">
										${data.ESTM_OBJ_PE_NM }
										<input type="hidden" name="P_ESTM_OBJ_PE_NM" value="${data.ESTM_OBJ_PE_NM }">
										<input type="hidden" name="P_DEL_AT" value="N">
									</td>
									<td class="txt-center"><a href="javascript:" class="component-button-s type-a" onclick="pageObj.estmObjFileView('${data.ESTM_OBJ_SEQ }', '${data.RSDN_NO }', '${data.BIZRNO }', '${data.FILE_GRP_NO }')" style="font-size:11px;">첨부파일보기</a></td>
								</tr>
							</c:forEach>
						</c:if>
					</tbody>
				</table>
			</div>
		</c:if>
		<!-- //평가위원이 제안제도인 경우 -->
		
		
		<!-- 평가위원이 인사인 경우 -->
		<c:if test="${estmMngMstDetail.ESTM_OBJ_SECD eq 'E'}">
			<div class="table-detail">
				<!-- Top -->
				<div class="top-detail">
					<div class="type-fleft">
						<div class="contents-label">평가대상정보 - 인사</div>
					</div>
					<div class="type-fright">
						<c:if test="${estmMngMstDetail.ESTM_PSCD ne 'A005' }">
							<%-- <c:if test="${not empty estmMngMstDetail.ESTM_INFO_CNTC_NO }">
								<a href="javascript:" class="component-button-s type-add" id="estmCntcObjBtn">평가대상불러오기</a>
							</c:if> --%>
							<%-- <c:if test="${empty estmMngMstDetail.ESTM_INFO_CNTC_NO }">
								<a type="button" href="javascript:excelDwd('excelTemplatDownFrm', '/estm/excelTemplatDwld.do','${comFn:nvl(1, 0)}');" class="btn-download-s">
									<i class="icon-download"></i>업로드 양식 다운로드
								</a>
								<a type="button" id="obj_3_excelUploadBtn" class="btn-upload-s">
									<i class="icon-upload"></i>엑셀 업로드
								</a>
							</c:if> --%>
							<c:if test="${empty estmMngMstDetail.ESTM_INFO_CNTC_NO }">
								<a href="javascript:" class="component-button-s type-add" id="obj_6_addBtn">대상추가</a>
							</c:if>
						</c:if>
					</div>
				</div>
				<!-- //Top -->
	
				<table class="component-detail-table ">
					<colgroup>
						<col width="6%">
						<col width="15%">
						<col width="35%">
						<col width="10%">
					</colgroup>
					<thead>
						<tr>
							<th>선택</th>
							<th>소속부서 또는 수험번호</th><!-- 소속부서 또는 수험번호  = 품목번호 -->
							<th>성명</th><!-- 성명 = 품목명 -->
							<th class="txt-center"></th>
						</tr>
					</thead>
					<tbody id="obj_6_HidTbdy" style="display: none;">
						<tr>
							<td class="txt-center">
								<label class="component-checkbox">
									<input type="checkbox" name="objCbk">
									<i></i>
								</label>
								<input type="hidden" name="P_OBJ_YN" value="hndwRegist">
								<input type="hidden" name="P_ESTM_OBJ_SEQ">
								<input type="hidden" name="P_DEL_AT" value="">
							</td>
							<td><input type="text" name="P_ESTM_OBJ_PE_NO" class="component-input w100" maxlength="100"></td>
							<td><input type="text" name="P_ESTM_OBJ_PE_NM" class="component-input w100" maxlength="100"></td>
							<td class="txt-center"><a href="javascript:" class="component-button-s type-line" name="obj_6_delBtn" onclick="objRowDel(this)">행삭제</a></td>
						</tr>
					</tbody>
					<tbody id="obj_6_ShowTbdy">
						<c:if test="${not empty estmObjList}">
							<c:forEach var="data" items="${estmObjList}" varStatus="status">
								<tr>
									<td class="txt-center">
										<label class="component-checkbox">
											<input type="checkbox" name="objCbk" onclick="objDelCheck(this);">
											<i></i>
										</label>
										<input type="hidden" name="P_OBJ_YN">
										<input type="hidden" name="P_ESTM_OBJ_SEQ" value="${data.ESTM_OBJ_SEQ}">
									</td>
									<td class="txt-center">
										${data.ESTM_OBJ_PE_NO }
										<input type="hidden" name="P_ESTM_OBJ_PE_NO" value="${data.ESTM_OBJ_PE_NO }">
									</td>
									<td class="txt-center">
										${data.ESTM_OBJ_PE_NM }
										<input type="hidden" name="P_ESTM_OBJ_PE_NM" value="${data.ESTM_OBJ_PE_NM }">
										<input type="hidden" name="P_DEL_AT" value="N">
									</td>
									<td class="txt-center"><a href="javascript:" class="component-button-s type-a" onclick="pageObj.estmObjFileView('${data.ESTM_OBJ_SEQ }', '${data.RSDN_NO }', '${data.BIZRNO }', '${data.FILE_GRP_NO }')" style="font-size:11px;">첨부파일보기</a></td>
								</tr>
							</c:forEach>
						</c:if>
					</tbody>
				</table>
			</div>
		</c:if>
		<!-- //평가위원이 인사인 경우 -->
		

		<!-- bottom button -->
		<div class="bottom-buttons">
			<c:if test="${estmMngMstDetail.ESTM_PSCD ne 'A005' }">
				<a href="javascript:" class="btn-bottom type-b" id="delBtn">삭제</a>
				<a href="javascript:" class="btn-bottom type-b" id="saveBtn">저장</a>
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

<!-- DETAIL FORM -->
<form id="detailFrm" method="POST">
	<input type="hidden" name="resourceDesc" value="${ param.resourceDesc}">
	<input type="hidden" name="resourceName" value="${comFn:nvl(param.resourceName, resourceName)}">
	<input type="hidden" name="P_ESTM_NO" value="${estmMngMstDetail.ESTM_NO }">
	<!-- 동적 Tab 이동시 필요 -->
	<input type="hidden" name="P_ESTM_PROCD_SEQ">
	<input type="hidden" name="P_ESTR_SECD">
	<input type="hidden" name="P_ESTM_CMTM_SLCT_NGR" value="${estmMngMstDetail.MAX_ESTM_CMTM_SLCT_NGR }">
	<input type="hidden" name="P_CMPL_YN" value="${estmMngMstDetail.CMTM_SLCT_CMPL_YN }">
</form>

<!-- POPUP FORM -->
<form id="popupFrm" method="POST">
	<input type="hidden" name="resourceName" value="${comFn:nvl(param.resourceName, resourceName)}">
	<input type="hidden" name="resourceDesc" value="${param.resourceDesc}">
	<input type="hidden" name="P_ESTM_OBJ_PE_NO">
	<input type="hidden" name="P_ESTM_NO" value="${estmMngMstDetail.ESTM_NO }">
	<input type="hidden" name="P_ESTM_OBJ_SEQ">
	<input type="hidden" name="P_FILE_GRP_NO">
	<input type="hidden" name="P_BSNM_REGIST_NO">
	<input type="hidden" name="P_GOODSNO">
	<input type="hidden" name="P_REQST_NO">
	<input type="hidden" name="P_ESTM_INFO_CNTC_NO" value="${estmMngMstDetail.ESTM_INFO_CNTC_NO }">
	
</form>

<form id="excelTemplatDownFrm" method="POST">
	<input type="hidden" name="P_ESTM_OBJ_SECD" value="${estmMngMstDetail.ESTM_OBJ_SECD }">
</form>

<form id="excelUploadForm" method="POST" enctype="multipart/form-data">
	<input type="hidden" id="resourceName" name="resourceName" value="${param.resourceName}">
	<input type="hidden" id="resourceDesc" name="resourceDesc" value="${param.resourceDesc}"> 
	<input type="hidden" name="P_ESTM_NO" value="${estmMngMstDetail.ESTM_NO }">
	<input type="hidden" name="P_ESTM_OBJ_SECD" value="${estmMngMstDetail.ESTM_OBJ_SECD }">
	<input type="hidden" name="fileFlag" value="Y"/>
	<input type="file" id="excelFileUpload" name="excelFileUpload"  style="display: none;" onchange="excelUploadRegist()" />
</form>