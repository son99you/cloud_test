<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 평가관리 > 평가진행현황 상세 - 평가위원
 *
 * <pre>
 * estm
 *    |_ estmProgCmtmDetail.jsp
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

<script type="text/javascript" src="${jsPath}/ipro/views/estm/estmProgCmtmDetail.js"></script>

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
		<input type="hidden" name="resourceDesc" value="${ param.resourceDesc}">
		<input type="hidden" name="resourceName" value="${comFn:nvl(param.resourceName, resourceName)}">
		<input type="hidden" name="P_ESTM_NO" value="${estmMngMstDetail.ESTM_NO }">
		
		<!-- 동적 Tab 이동시 필요 시작 -->
		<input type="hidden" id="P_ESTM_PSCD" name="P_ESTM_PSCD" value="${estmMngMstDetail.ESTM_PSCD }">
		<input type="hidden" name="P_ESTM_PROCD_SEQ">
		<!-- 동적 Tab 이동시 필요 종료 -->
		
		
		<!-- 평가위원 선정 시 필요 시작 - 삭제하지 말 것 -->
		<input type="hidden" id="P_PRIO_RNK_RE_SLCT_YN" name="P_PRIO_RNK_RE_SLCT_YN" value="${param.P_PRIO_RNK_RE_SLCT_YN }"><!-- 재선정여부 -->
		<input type="hidden" id="P_CMPL_YN" name="P_CMPL_YN" value="${param.P_CMPL_YN }"><!-- 최종여부 -->
		
		<input type="hidden" name="P_ESTM_CMTM_RE_SLCT_NGR" value="${P_ESTM_CMTM_RE_SLCT_NGR }"><!-- 재선정될 차수 -->
		<input type="hidden" name="P_INO_CMTM_SECD">
		<input type="hidden" name="P_SLCT_SECD">
		<input type="hidden" id="P_ESTM_CMTM_SLCT_NGR" name="P_ESTM_CMTM_SLCT_NGR" value="${comFn:nvl(estmMngMstDetail.MAX_ESTM_CMTM_SLCT_NGR, 1)}"><!-- 현재차수  -->
		<input type="hidden" id="P_INN_ESTM_CMTM_CNT" name="P_INN_ESTM_CMTM_CNT" value="${estmMngMstDetail.INN_ESTM_CMTM_CNT }"><!-- 내부평가위원수 -->
		<input type="hidden" id="P_OUT_ESTM_CMTM_CNT" name="P_OUT_ESTM_CMTM_CNT" value="${estmMngMstDetail.OUT_ESTM_CMTM_CNT }"><!-- 외부평가위원수 -->
		<input type="hidden" id="P_OUT_CMTM_SLCT_MTHD_SECD" value="${estmMngMstDetail.OUT_CMTM_SLCT_MTHD_SECD }"><!-- 외부평가위원선정방법 -->
		<input type="hidden" id="P_INN_CMTM_SLCT_MTHD_SECD" value="${estmMngMstDetail.INN_CMTM_SLCT_MTHD_SECD }"><!-- 내부평가위원선정방법 -->
		<input type="hidden" id="P_ESTM_CMTM_SLCT_NGR_CLICK" name="P_ESTM_CMTM_SLCT_NGR_CLICK" value="${ P_ESTM_CMTM_SLCT_NGR_CLICK}"><!-- 선정차수 버튼 클릭 -->
		<input type="hidden" id="P_DELETE_ESTM_CMTM_NO" name="P_DELETE_ESTM_CMTM_NO">
		<!-- 평가위원 선정 시 필요 종료 - 삭제하지 말 것 -->
		
		<!-- 필수정보 고정 -->
		<div class="table-detail">
			<table class="component-detail-table type-line-none" style="display: none;">
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
			
			<br><br>
					
			<table class="component-detail-table type-line-none">
				<colgroup>
					<col width="15%">
					<col width="35%">
					<col width="15%">
					<col width="35%">
				</colgroup>
				<tbody>		
					<tr>
						<th>외부평가위원선정방법</th>
						<td>${estmMngMstDetail.OUT_CMTM_SLCT_MTHD_SECD_NM } - ${estmMngMstDetail.OUT_CMTM_SLCT_MTHD_SECD} </td>
						<th>내부평가위원선정방법</th>
						<td>${estmMngMstDetail.INN_CMTM_SLCT_MTHD_SECD_NM } - ${estmMngMstDetail.INN_CMTM_SLCT_MTHD_SECD} </td>
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
						<th>ESTM_PSCD</th>
						<td>${estmMngMstDetail.ESTM_PSCD } - ${estmMngMstDetail.ESTM_PSCD_NM }</td>
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
				<li>
					<a href="javascript:tabEvent(2);">평가대상</a>
				</li>
				<li class="is-selected">
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
			<div class="type-fleft">
				<c:if test="${comFn:nvl(estmMngMstDetail.MAX_ESTM_CMTM_SLCT_NGR, 1) > 0 }">
					<c:set var="btnClass" value="type-add" />
					<c:forEach begin="1" end="${ comFn:nvl(estmMngMstDetail.MAX_ESTM_CMTM_SLCT_NGR, 1) }" step="1" var="estmCmtmSlctNgr" varStatus="loop">
						<c:if test="${ estmCmtmSlctNgr ne P_ESTM_CMTM_SLCT_NGR_CLICK}">
							<c:set var="btnClass" value="type-del" />
						</c:if>
						<c:if test="${ estmCmtmSlctNgr eq P_ESTM_CMTM_SLCT_NGR}">
							<c:set var="btnClass" value="type-add" />
						</c:if>
						<a href="javascript:" class="component-button-s ${btnClass }" onclick="estmCmtmSlctNgrDetail('${estmCmtmSlctNgr }')">${estmCmtmSlctNgr }</a>
					</c:forEach>
					
					<c:set var="btnClass" value="type-N" />
					<c:if test="${estmMngMstDetail.CMTM_SLCT_CMPL_YN eq 'Y'}">
						<c:if test="${ param.P_CMPL_YN eq 'Y'}">
							<c:set var="btnClass" value="type-Y" />
						</c:if>
						<a href="javascript:" class="component-button-s ${btnClass }" onclick="estmCmtmSlctCmplDetail()">최종</a>
					</c:if>
					
				</c:if>
			</div>
		</div>			
			
			
		<div class="table-detail">
			<!-- Top -->
			<div class="top-detail">
				<div class="type-fleft">
					<div class="contents-label">외부평가위원정보 : ${estmMngMstDetail.OUT_CMTM_SLCT_MTHD_SECD_NM }</div>
				</div>
				
				<div class="type-fright">
					<c:if test="${not empty estmMngMstDetail.OUT_CMTM_SLCT_CNT}">
						<div class="contents-sub_label">현재 선정된 외부평가위원 수 : ${estmMngMstDetail.OUT_CMTM_SLCT_CNT}</div>
					</c:if>
					
					<c:if test="${ (estmMngMstDetail.OUT_CMTM_SLCT_MTHD_SECD eq 'B') or (estmMngMstDetail.OUT_CMTM_SLCT_MTHD_SECD eq 'C') }"><!-- 우선순위(B), 자체선정+우선순위(C)일 경우 -->
						<c:if test="${(estmMngMstDetail.ESTM_PSCD eq 'A004') or (estmMngMstDetail.ESTM_PSCD eq 'A0041')}">
							<c:choose>
								<c:when test="${(P_ESTM_CMTM_RE_SLCT_NGR > 1 ) and (param.P_PRIO_RNK_RE_SLCT_YN eq 'Y') }">
									<a href="javascript:" class="component-button-s type-a" id="estmCmtmReAutoSlctBtn" onclick="estmCmtmReAutoSlct('OUT')">우선순위재선정-자동선별</a>
								</c:when>
								
								<c:when test="${cmtmAutoBtnCtr.OUT_AUTO_BTN_CNTR eq 'F' }">&nbsp;</c:when>
								
								<c:when test="${cmtmAutoBtnCtr.OUT_AUTO_BTN_CNTR eq 'T' }">
									<c:if test="${not empty P_ESTM_CMTM_RE_SLCT_NGR }">
										<a href="javascript:" class="component-button-s type-a" id="estmCmtmReAutoSlctBtn" onclick="estmCmtmReAutoSlct('OUT')">우선순위재선정-자동선별</a>
									</c:if>
								</c:when>
								
								<c:otherwise>&nbsp;</c:otherwise>
							</c:choose>
						</c:if>
						
						<c:if test="${(estmMngMstDetail.ESTM_PSCD ne 'A003') and (estmMngMstDetail.ESTM_PSCD ne 'A004') and (estmMngMstDetail.ESTM_PSCD ne 'A0041') and (estmMngMstDetail.ESTM_PSCD ne 'A005')}">
							<a href="javascript:" class="component-button-s type-a" onclick="estmCmtmAutoSlct('OUT')">자동선별</a>
						</c:if>
					</c:if>
					
					<c:if test="${estmMngMstDetail.CMTM_SLCT_CMPL_YN ne 'Y'}">
						<c:if test="${(estmMngMstDetail.ESTM_PSCD ne 'A004') and (estmMngMstDetail.ESTM_PSCD ne 'A005') }">
							<c:if test="${ estmMngMstDetail.OUT_CMTM_SLCT_MTHD_SECD eq 'A' }">
								<!-- 자체선정일 경우 -->
								<a href="javascript:" class="component-button-s type-a" onclick="estmCmtmRegist('OUT')">평가위원저장</a>
								<a href="javascript:" class="component-button-s type-add" id="outEstmAddBtn">추가</a>
								<a href="javascript:" class="component-button-s type-del" id="outEstmDelBtn">삭제</a>
							</c:if>
						</c:if>
					</c:if>
				</div>
			</div>
			<!-- //Top -->
			
			<div style="overflow-x: scroll; overflow-y:hidden">
				<table class="component-detail-table ">
					<colgroup>
						<col width="80px">
						<col width="60px">
						<col width="100px">
						<col width="100px">
						<col width="100px">
						<col width="180px">
						<col width="210px">
						<col width="110px">
						<col width="200px">
						<col width="200px">
						<col width="80px">
						<col width="100px">
						<col width="200px">
					</colgroup>
					<thead>
						<tr>
							<th>선정차수</th>
							<c:if test="${ estmMngMstDetail.OUT_CMTM_SLCT_MTHD_SECD ne 'A' }">
								<th>순위</th>
							</c:if>
							<c:if test="${ estmMngMstDetail.OUT_CMTM_SLCT_MTHD_SECD eq 'A' }">
								<th>선택</th>
							</c:if>
							<th>평가위원명</th>
							<th>내/외부 구분</th>
							<th>대분류</th>
							<th>중분류</th>
							<th>소분류</th>
							<th>휴대폰번호</th>
							<th>이메일</th>
							<th>주민등록번호</th>
							<th>선별구분</th>
							<th>선정여부</th>
							<th>비고</th>
						</tr>
					</thead>
					<tbody id="outShowTbdy">
						<c:if test="${param.P_PRIO_RNK_RE_SLCT_YN ne 'Y'}">
							<c:if test="${not empty outEstmCmtmList}">
								<c:forEach var="data" items="${outEstmCmtmList}" varStatus="status">
									<tr>
										<td class="txt-center">${data.ESTM_CMTM_SLCT_NGR }</td>
										<c:if test="${ estmMngMstDetail.OUT_CMTM_SLCT_MTHD_SECD ne 'A' }">
											<td class="txt-center">${data.PRIO_RNK }</td>
										</c:if>
										<c:if test="${ estmMngMstDetail.OUT_CMTM_SLCT_MTHD_SECD eq 'A' }">
											<td class="txt-center">
												<label class="component-checkbox">
													<input type="checkbox" name="estmCbk">
													<i></i>
												</label>
											</td>
										</c:if>
										<td class="txt-center">
											${data.ESTM_CMTM_NM }
											<input type="hidden" name="P_ESTM_CMTM_NO" value="${data.ESTM_CMTM_NO }" class="component-input" disabled="disabled">
										</td>
										<td class="txt-center">${data.INO_CMTM_SECD_NM}</td>
										<td>${data.LLF_SECD_NM }</td>
										<td>${data.MLF_SECD_NM }</td>
										<td>${data.SLF_SECD_NM }</td>
										<td>${data.CP_NO }</td>
										<td>${data.EMAL }</td>
										<td class="txt-center"><!-- 주민등록번호 -->
											<c:if test="${estmMngMstDetail.CMTM_SLCT_CMPL_YN eq 'Y'}">
												<c:if test="${estmMngMstDetail.ESTM_PSCD eq 'A005'}">
													<%-- ${data.RSDN_NO_1 } -${data.RSDN_NO_3 } --%>
													${data.RSDN_NO_1 } - ${data.RSDN_NO_2 }******
												</c:if>
												<c:if test="${estmMngMstDetail.ESTM_PSCD ne 'A005'}">
													<input type="text" name="P_RSDN_NO_1" class="component-input w40" maxlength="6" value="${data.RSDN_NO_1 }"> - 
													<input type="password" name="P_RSDN_NO_2" class="component-input w50" maxlength="7" value="${data.RSDN_NO_3 }">
												</c:if>
											</c:if>
										</td>
										<td class="txt-center">${data.SLCT_SECD_NM}</td>
										<td class="txt-center"><!-- 선정여부 -->
										
											<c:if test="${(estmMngMstDetail.ESTM_PSCD eq 'A004')}"><!-- 평가위원순위선정완료(A004) -->
												<c:if test="${estmMngMstDetail.CMTM_SLCT_CMPL_YN ne 'Y'}"><!-- 평가위원선정완료여부(N) -->
													<c:if test="${ empty data.SLCT_YN }">
														<!-- empty data.SLCT_YN -->
														<c:if test="${ empty data.PRIO_RNK }">
															<!-- 비선정1 -->
															<font color="red">비선정</font> 
														</c:if>
															
														<c:if test="${ not empty data.PRIO_RNK }">
															<!-- not empty data.PRIO_RNK -->
															<a href="javascript:" class="component-button-s type-Y" onclick="pageObj.cmtmSlctYnUpdt(this, '${data.ESTM_CMTM_SLCT_NGR }', '${data.ESTM_CMTM_NO}', '${data.INO_CMTM_SECD }', 'Y')">선정</a>
															<a href="javascript:" class="component-button-s type-N" onclick="pageObj.cmtmSlctYnUpdt(this, '${data.ESTM_CMTM_SLCT_NGR }', '${data.ESTM_CMTM_NO}', '${data.INO_CMTM_SECD }', 'N')">비선정</a>
														</c:if>
													</c:if>
													
													<c:if test="${ not empty data.SLCT_YN }">
														<!-- not empty data.SLCT_YN -->
													    <c:if test="${ data.SLCT_YN eq 'Y' }">선정</c:if>
														<c:if test="${ data.SLCT_YN ne 'Y' }"><!-- 비선정3 --><font color="red">비선정</font></c:if>
														<c:if test="${(estmMngMstDetail.ESTM_PSCD eq 'A004') and (estmMngMstDetail.OUT_CMTM_SLCT_MTHD_SECD ne 'A')}">
															<c:if test="${ (data.SLCT_YN eq 'Y') and (not empty data.PRIO_RNK) }">
																<c:if test="${ data.ESTM_CMTM_SLCT_NGR eq comFn:nvl(estmMngMstDetail.MAX_ESTM_CMTM_SLCT_NGR, 1) }">
																	<a href="javascript:;" class="component-button-s type-del" onclick="pageObj.cmtmSlctYnUpdt(this, '${data.ESTM_CMTM_SLCT_NGR }', '${data.ESTM_CMTM_NO}', '${data.INO_CMTM_SECD }', 'C')">취소</a>
																</c:if>
															</c:if>
														</c:if>
													</c:if>
												</c:if>
												
												
												<c:if test="${estmMngMstDetail.CMTM_SLCT_CMPL_YN eq 'Y'}"><!-- 평가위원선정완료여부(Y) -->
													<c:if test="${ data.SLCT_YN eq 'Y' }">선정</c:if>
													<c:if test="${ data.SLCT_YN ne 'Y' }"><!-- 비선정4 --><font color="red">비선정</font></c:if>
												</c:if>
												<input type="hidden" name="P_SLCT_YN_CHK" value="${data.SLCT_YN }" disabled="disabled">
											</c:if>
											
											<c:if test="${(estmMngMstDetail.ESTM_PSCD ne 'A004')}">
												<!-- estmMngMstDetail.ESTM_PSCD ne 'A004' -->
												<c:if test="${ data.SLCT_YN eq 'Y' }">선정</c:if>
												<c:if test="${ data.SLCT_YN ne 'Y' }"><!-- 비선정5 --><font color="red">비선정</font></c:if>
												<input type="hidden" name="P_SLCT_YN_CHK" value="${data.SLCT_YN }" disabled="disabled">
											</c:if>
										</td>
										
										
										<td><!-- 비고 -->
											<c:if test="${ (not empty data.SLCT_YN) and (not empty data.RMK) }">
												<input type="text" name="P_RMK" value="${data.RMK }" class="component-input w100" maxlength="4000" readonly="readonly">	
											</c:if>
											<c:if test="${ (not empty data.SLCT_YN) and (empty data.RMK )}">	
												${data.RMK }
											</c:if>
											<c:if test="${ empty data.SLCT_YN }">
												<input type="text" name="P_RMK" class="component-input w100" maxlength="4000">	
											</c:if>
										</td>
									</tr>
								</c:forEach>
							</c:if>
						</c:if>
						
						<c:if test="${param.P_PRIO_RNK_RE_SLCT_YN eq 'Y'}"><!-- 재선정일 경우 -->
							<c:if test="${not empty outEstmCmtmSlctList}">
								<c:forEach var="data" items="${outEstmCmtmSlctList}" varStatus="status">  
									<tr>
										<td class="txt-center">${data.ESTM_CMTM_SLCT_NGR }</td>
										<c:if test="${ estmMngMstDetail.OUT_CMTM_SLCT_MTHD_SECD ne 'A' }">
											<td class="txt-center">${data.PRIO_RNK }</td>
										</c:if>
										<c:if test="${ estmMngMstDetail.OUT_CMTM_SLCT_MTHD_SECD eq 'A' }">
											<td class="txt-center">
												<label class="component-checkbox">
													<input type="checkbox" name="estmCbk">
													<i></i>
												</label>
											</td>
										</c:if>
										<td class="txt-center">
											${data.ESTM_CMTM_NM }
											<input type="hidden" name="P_ESTM_CMTM_NO" value="${data.ESTM_CMTM_NO }" class="component-input" disabled="disabled">
										</td>
										<td class="txt-center">${data.INO_CMTM_SECD_NM}</td>
										<td>${data.LLF_SECD_NM }</td>
										<td>${data.MLF_SECD_NM }</td>
										<td>${data.SLF_SECD_NM }</td>
										<td>${data.CP_NO }</td>
										<td>${data.EMAL }</td>
										<td class="txt-center"><!-- 주민등록번호 -->
											<c:if test="${estmMngMstDetail.CMTM_SLCT_CMPL_YN eq 'Y'}">
												<%-- ${data.RSDN_NO_1 } -${data.RSDN_NO_3 } --%>
													${data.RSDN_NO_1 } - ${data.RSDN_NO_2 }******
											</c:if>
											<c:if test="${estmMngMstDetail.CMTM_SLCT_CMPL_YN ne 'Y'}">
												<c:if test="${ data.SLCT_YN eq 'Y'}">
													<input type="text" name="P_RSDN_NO_1" class="component-input w40" maxlength="6"> - 
													<input type="password" name="P_RSDN_NO_2" class="component-input w50" maxlength="7">
												</c:if>
											</c:if>
										</td>
										<td class="txt-center">${data.SLCT_SECD_NM}</td>
										<td class="txt-center"><!-- 선정여부 -->
										
											<%-- <c:if test="${(estmMngMstDetail.ESTM_PSCD eq 'A004')}"><!-- 평가위원순위선정완료 --> --%>
												<c:if test="${estmMngMstDetail.CMTM_SLCT_CMPL_YN ne 'Y'}"><!-- 평가위원선정완료여부 N -->
													<c:if test="${ empty data.SLCT_YN }">	
														<a href="javascript:" class="component-button-s type-Y" onclick="pageObj.cmtmSlctYnUpdt(this, '${data.ESTM_CMTM_SLCT_NGR }', '${data.ESTM_CMTM_NO}', '${data.INO_CMTM_SECD }', 'Y')">선정</a>
														<a href="javascript:" class="component-button-s type-N" onclick="pageObj.cmtmSlctYnUpdt(this, '${data.ESTM_CMTM_SLCT_NGR }', '${data.ESTM_CMTM_NO}', '${data.INO_CMTM_SECD }', 'N')">비선정</a>
													</c:if>
													
													<c:if test="${ not empty data.SLCT_YN }">
													    <c:if test="${ data.SLCT_YN == 'Y' }">선정</c:if>
														<c:if test="${ data.SLCT_YN == 'N' }"><font color="red">비선정</font></c:if>
														<c:if test="${(estmMngMstDetail.ESTM_PSCD eq 'A004') and (estmMngMstDetail.OUT_CMTM_SLCT_MTHD_SECD eq 'B') or (estmMngMstDetail.OUT_CMTM_SLCT_MTHD_SECD eq 'C')}">
															<c:if test="${P_ESTM_CMTM_RE_SLCT_NGR eq (estmMngMstDetail.MAX_ESTM_CMTM_SLCT_NGR)}">
																<a href="javascript:;" class="component-button-s type-del" onclick="pageObj.cmtmSlctYnUpdt(this, '${data.ESTM_CMTM_SLCT_NGR }', '${data.ESTM_CMTM_NO}', '${data.INO_CMTM_SECD }', 'C')">취소</a>
															</c:if>
														</c:if>
													</c:if>
												</c:if>
												<c:if test="${estmMngMstDetail.CMTM_SLCT_CMPL_YN eq 'Y'}"><!-- 평가위원선정완료여부 Y -->
													<c:if test="${ data.SLCT_YN eq 'Y' }">선정</c:if>
													<c:if test="${ data.SLCT_YN ne 'Y' }"><font color="red">비선정</font></c:if>
												</c:if>
												<input type="hidden" name="P_SLCT_YN_CHK" value="${data.SLCT_YN }" disabled="disabled">
											<%-- </c:if> --%>
											
											<c:if test="${(estmMngMstDetail.ESTM_PSCD ne 'A004')}">
												<c:if test="${ data.SLCT_YN eq 'Y' }">선정</c:if>
												<c:if test="${ data.SLCT_YN ne 'Y' }"><font color="red">비선정</font></c:if>
												<input type="hidden" name="P_SLCT_YN_CHK" value="${data.SLCT_YN }" disabled="disabled">
											</c:if>
										</td>
										<td><!-- 비고 -->
											<c:if test="${ (not empty data.SLCT_YN) and (not empty data.RMK) }">
												<input type="text" name="P_RMK" value="${data.RMK }" class="component-input w100" maxlength="4000" readonly="readonly">	
											</c:if>
											<c:if test="${ (not empty data.SLCT_YN) and (empty data.RMK )}">	
												${data.RMK }
											</c:if>
											<c:if test="${ empty data.SLCT_YN }">
												<input type="text" name="P_RMK" class="component-input w100" maxlength="4000">	
											</c:if>
										</td>
									</tr>
								</c:forEach>
							</c:if>
						</c:if>
					</tbody>
				</table>
			</div>
		</div>
		
		<c:if test="${ estmMngMstDetail.OUT_CMTM_SLCT_MTHD_SECD eq 'C' }">
			<div class="table-detail">
				<!-- Top -->
				<div class="top-detail">
					<div class="type-fleft">
						<div class="contents-label">지정평가위원정보</div>
					</div>
					
					<c:if test="${estmMngMstDetail.CMTM_SLCT_CMPL_YN ne 'Y'}">
						<c:if test="${(estmMngMstDetail.ESTM_PSCD ne 'A004') and (estmMngMstDetail.ESTM_PSCD ne 'A005') and (estmMngMstDetail.ESTM_PSCD ne 'A003') }">
							<div class="type-fright">
								<a href="javascript:" class="component-button-s type-a" onclick="estmCmtmRegist('OUT')">평가위원저장</a>
								<a href="javascript:" class="component-button-s type-add" id="fixEstmAddBtn">추가</a>
								<a href="javascript:" class="component-button-s type-del" id="fixEstmDelBtn">삭제</a>
							</div>
						</c:if>
					</c:if>	
						
				</div>
				<!-- //Top -->
				
				<div style="overflow-x: scroll; overflow-y:hidden">
					<table class="component-detail-table ">
						<colgroup>
							<col width="60px">
							<col width="100px">
							<col width="100px">
							<col width="100px">
							<col width="180px">
							<col width="210px">
							<col width="110px">
							<col width="200px">
							<col width="200px">
							<col width="80px">
							<col width="100px">
							<col width="200px">
						</colgroup>
						<thead>
							<tr>
								<th>선택</th>
								<th>평가위원명</th>
								<th>내/외부 구분</th>
								<th>대분류</th>
								<th>중분류</th>
								<th>소분류</th>
								<th>휴대폰번호</th>
								<th>이메일</th>
								<th>주민등록번호</th>
								<th>선별구분</th>
								<th>선정여부</th>
								<th>비고</th>
							</tr>
						</thead>
						<tbody id="fixShowTbdy">
							<c:if test="${not empty fixEstmCmtmList}">
								<c:forEach var="data" items="${fixEstmCmtmList}" varStatus="status">
									<tr>
										<c:if test="${ estmMngMstDetail.OUT_CMTM_SLCT_MTHD_SECD eq 'B' }">
											<td class="txt-center">${data.PRIO_RNK }</td>
										</c:if>
										<c:if test="${ estmMngMstDetail.OUT_CMTM_SLCT_MTHD_SECD ne 'B' }">
											<td class="txt-center">
												<label class="component-checkbox">
													<input type="checkbox" name="estmCbk">
													<i></i>
												</label>
											</td>
										</c:if>
										<td class="txt-center">
											${data.ESTM_CMTM_NM }
											<input type="hidden" name="P_ESTM_CMTM_NO" value="${data.ESTM_CMTM_NO }" class="component-input" disabled="disabled">
										</td>
										<td class="txt-center">${data.INO_CMTM_SECD_NM}</td>
										<td>${data.LLF_SECD_NM }</td>
										<td>${data.MLF_SECD_NM }</td>
										<td>${data.SLF_SECD_NM }</td>
										<td>${data.CP_NO }</td>
										<td>${data.EMAL }</td>
										<td class="txt-center"><!-- 주민등록번호 -->
											<c:if test="${estmMngMstDetail.CMTM_SLCT_CMPL_YN eq 'Y'}">
												<%-- ${data.RSDN_NO_1 } -${data.RSDN_NO_3 } --%>
													${data.RSDN_NO_1 } - ${data.RSDN_NO_2 }******
											</c:if>
											<c:if test="${estmMngMstDetail.CMTM_SLCT_CMPL_YN ne 'Y'}">
												<c:if test="${ data.SLCT_YN eq 'Y'}">
													<input type="text" name="P_RSDN_NO_1" class="component-input w40" maxlength="6"> - 
													<input type="password" name="P_RSDN_NO_2" class="component-input w50" maxlength="7">
												</c:if>
											</c:if>
										</td>
										<td class="txt-center">${data.SLCT_SECD_NM}</td>
										<td class="txt-center">선정</td>
										<td>${data.RMK }</td>
									</tr>
								</c:forEach>
							</c:if>
						</tbody>
					</table>
				</div>
			</div>
		</c:if>
		
		
		<div class="table-detail">
			<!-- Top -->
			<div class="top-detail">
				<div class="type-fleft">
					<div class="contents-label">내부평가위원정보 : ${estmMngMstDetail.INN_CMTM_SLCT_MTHD_SECD_NM }</div>
				</div>
				
				<div class="type-fright">
				
					<c:if test="${not empty estmMngMstDetail.INN_CMTM_SLCT_CNT}">
						<div class="contents-sub_label">현재 선정된 내부평가위원 수 : ${estmMngMstDetail.INN_CMTM_SLCT_CNT}</div>
					</c:if>
					
					<c:if test="${(estmMngMstDetail.ESTM_PSCD ne 'A003') and (estmMngMstDetail.ESTM_PSCD ne 'A004') and (estmMngMstDetail.ESTM_PSCD ne 'A005')}">
						<c:if test="${ estmMngMstDetail.INN_CMTM_SLCT_MTHD_SECD eq 'B' }">
							<!-- 우선순위일 경우 -->
							<a href="javascript:" class="component-button-s type-a" onclick="estmCmtmAutoSlct('INN')">자동선별</a>
						</c:if>
					</c:if>

					<c:if test="${estmMngMstDetail.CMTM_SLCT_CMPL_YN ne 'Y'}">
						<c:if test="${(estmMngMstDetail.ESTM_PSCD ne 'A004') and (estmMngMstDetail.ESTM_PSCD ne 'A005') and (estmMngMstDetail.ESTM_PSCD ne 'A003') }">
							<c:if test="${ estmMngMstDetail.INN_CMTM_SLCT_MTHD_SECD eq 'A' }">


								<c:if test="${ (estmMngMstDetail.INN_CMTM_SLCT_CNT) ne (comFn:nvl(estmMngMstDetail.INN_ESTM_CMTM_CNT, 0) ) }">
									<!-- 자체선정일 경우 -->
									<a href="javascript:" class="component-button-s type-a" onclick="estmCmtmRegist('INN')">평가위원저장</a>
									<a href="javascript:" class="component-button-s type-add" id="innEstmAddBtn">추가</a>
									<a href="javascript:" class="component-button-s type-del" id="innEstmDelBtn">삭제</a>
								</c:if>
								
								
								
								
							</c:if>
						</c:if>
					</c:if>
				</div>
			</div>
			<!-- //Top -->

			<div style="overflow-x: scroll; overflow-y:hidden">
				<table class="component-detail-table ">
					<colgroup>
						<col width="60px">
						<col width="100px">
						<col width="100px">
						<col width="100px">
						<col width="180px">
						<col width="210px">
						<col width="110px">
						<col width="200px">
						<col width="80px">
						<col width="100px">
						<col width="200px">
					</colgroup>
					<thead>
						<tr>
							<c:if test="${ estmMngMstDetail.INN_CMTM_SLCT_MTHD_SECD eq 'B' }">
								<th>순위</th>
							</c:if>
							<c:if test="${ estmMngMstDetail.INN_CMTM_SLCT_MTHD_SECD ne 'B' }">
								<th>선택</th>
							</c:if>
							<th>평가위원명</th>
							<th>내/외부 구분</th>
							<th>대분류</th>
							<th>중분류</th>
							<th>소분류</th>
							<th>휴대폰번호</th>
							<th>이메일</th>
							<th>선별구분</th>
							<th>선정여부</th>
							<th>비고</th>
						</tr>
					</thead>
					<tbody id="innShowTbdy">
						<c:if test="${param.P_PRIO_RNK_RE_SLCT_YN ne 'Y'}">
							<c:if test="${not empty innEstmCmtmList}">
								<c:forEach var="data" items="${innEstmCmtmList}" varStatus="status">
									<tr>
										<c:if test="${ estmMngMstDetail.INN_CMTM_SLCT_MTHD_SECD eq 'B' }">
											<td class="txt-center">${data.PRIO_RNK }</td>
										</c:if>
										<c:if test="${ estmMngMstDetail.INN_CMTM_SLCT_MTHD_SECD ne 'B' }">
											<td class="txt-center">
												<label class="component-checkbox">
													<input type="checkbox" name="estmCbk">
													<i></i>
												</label>
											</td>
										</c:if>
										<td class="txt-center">
											${data.ESTM_CMTM_NM }
											<input type="hidden" name="P_ESTM_CMTM_NO" value="${data.ESTM_CMTM_NO }" class="component-input" disabled="disabled">
										</td>
										<td class="txt-center">${data.INO_CMTM_SECD_NM}</td>
										<td>${data.LLF_SECD_NM }</td>
										<td>${data.MLF_SECD_NM }</td>
										<td>${data.SLF_SECD_NM }</td>
										<td>${data.CP_NO }</td>
										<td>${data.EMAL }</td>
										<td class="txt-center">${data.SLCT_SECD_NM}</td>
										
										<td class="txt-center"><!-- 선정여부 -->
											<c:if test="${(estmMngMstDetail.ESTM_PSCD eq 'A004')}"><!-- 평가위원순위선정완료 -->
												<c:if test="${estmMngMstDetail.CMTM_SLCT_CMPL_YN ne 'Y'}"><!-- 평가위원선정완료여부 N -->
													<c:if test="${ empty data.SLCT_YN }">	
														<a href="javascript:" class="component-button-s type-Y" onclick="pageObj.cmtmSlctYnUpdt(this, '${data.ESTM_CMTM_SLCT_NGR }', '${data.ESTM_CMTM_NO}', '${data.INO_CMTM_SECD }', 'Y')">선정</a>
														<a href="javascript:" class="component-button-s type-N" onclick="pageObj.cmtmSlctYnUpdt(this, '${data.ESTM_CMTM_SLCT_NGR }', '${data.ESTM_CMTM_NO}', '${data.INO_CMTM_SECD }', 'N')">비선정</a>
													</c:if>
													<c:if test="${ not empty data.SLCT_YN }">
													    <c:if test="${ data.SLCT_YN == 'Y' }">선정</c:if>
														<c:if test="${ data.SLCT_YN == 'N' }"><font color="red">비선정</font></c:if>
														<c:if test="${(estmMngMstDetail.ESTM_PSCD eq 'A004') and (estmMngMstDetail.INN_CMTM_SLCT_MTHD_SECD ne 'A')}">
															<a href="javascript:;" class="component-button-s type-del" onclick="pageObj.cmtmSlctYnUpdt(this, '${data.ESTM_CMTM_SLCT_NGR }', '${data.ESTM_CMTM_NO}', '${data.INO_CMTM_SECD }', 'C')">취소</a>
														</c:if>
													</c:if>
												</c:if>
												<c:if test="${estmMngMstDetail.CMTM_SLCT_CMPL_YN eq 'Y'}"><!-- 평가위원선정완료여부 Y -->
													<c:if test="${ data.SLCT_YN eq 'Y' }">선정</c:if>
													<c:if test="${ data.SLCT_YN ne 'Y' }"><font color="red">비선정</font></c:if>
												</c:if>
												<input type="hidden" name="P_SLCT_YN_CHK" value="${data.SLCT_YN }" disabled="disabled">
											</c:if>
											
											<c:if test="${(estmMngMstDetail.ESTM_PSCD ne 'A004')}">
												<c:if test="${ data.SLCT_YN eq 'Y' }">선정</c:if>
												<c:if test="${ data.SLCT_YN ne 'Y' }"><font color="red">비선정</font></c:if>
												<input type="hidden" name="P_SLCT_YN_CHK" value="${data.SLCT_YN }" disabled="disabled">
											</c:if>
										</td>
										
										<td><!-- 비고 -->
											<c:if test="${ (not empty data.SLCT_YN) and (not empty data.RMK) }">
												<input type="text" name="P_RMK" value="${data.RMK }" class="component-input w100" maxlength="4000" readonly="readonly">	
											</c:if>
											<c:if test="${ (not empty data.SLCT_YN) and (empty data.RMK )}">	
												${data.RMK }
											</c:if>
											<c:if test="${ empty data.SLCT_YN }">
												<input type="text" name="P_RMK" class="component-input w100" maxlength="4000">	
											</c:if>
										</td>
									</tr>
								</c:forEach>
							</c:if>
						</c:if>
						
						
						<c:if test="${param.P_PRIO_RNK_RE_SLCT_YN eq 'Y'}"><!-- 재선정일 경우 -->
							<c:if test="${not empty innEstmCmtmSlctList}">
								<c:forEach var="data" items="${innEstmCmtmSlctList}" varStatus="status">
									<tr>
										<c:if test="${ estmMngMstDetail.INN_CMTM_SLCT_MTHD_SECD eq 'B' }">
											<td class="txt-center">${data.PRIO_RNK }</td>
										</c:if>
										<c:if test="${ estmMngMstDetail.INN_CMTM_SLCT_MTHD_SECD ne 'B' }">
											<td class="txt-center">
												<label class="component-checkbox">
													<input type="checkbox" name="estmCbk">
													<i></i>
												</label>
											</td>
										</c:if>
										<td class="txt-center">
											${data.ESTM_CMTM_NM }
											<input type="hidden" name="P_ESTM_CMTM_NO" value="${data.ESTM_CMTM_NO }" class="component-input" disabled="disabled">
										</td>
										<td class="txt-center">${data.INO_CMTM_SECD_NM}</td>
										<td>${data.LLF_SECD_NM }</td>
										<td>${data.MLF_SECD_NM }</td>
										<td>${data.SLF_SECD_NM }</td>
										<td>${data.CP_NO }</td>
										<td>${data.EMAL }</td>
										<td class="txt-center">${data.SLCT_SECD_NM}</td>
										
										<td class="txt-center"><!-- 선정여부 -->
											<c:if test="${(estmMngMstDetail.ESTM_PSCD eq 'A004')}"><!-- 평가위원순위선정완료 -->
												<c:if test="${estmMngMstDetail.CMTM_SLCT_CMPL_YN ne 'Y'}"><!-- 평가위원선정완료여부 N -->
													<c:if test="${ empty data.SLCT_YN }">	
														<a href="javascript:" class="component-button-s type-Y" onclick="pageObj.cmtmSlctYnUpdt(this, '${data.ESTM_CMTM_SLCT_NGR }', '${data.ESTM_CMTM_NO}', '${data.INO_CMTM_SECD }', 'Y')">선정</a>
														<a href="javascript:" class="component-button-s type-N" onclick="pageObj.cmtmSlctYnUpdt(this, '${data.ESTM_CMTM_SLCT_NGR }', '${data.ESTM_CMTM_NO}', '${data.INO_CMTM_SECD }', 'N')">비선정</a>
													</c:if>
													<c:if test="${ not empty data.SLCT_YN }">
													    <c:if test="${ data.SLCT_YN == 'Y' }">선정</c:if>
														<c:if test="${ data.SLCT_YN == 'N' }"><font color="red">비선정</font></c:if>
														<c:if test="${(estmMngMstDetail.ESTM_PSCD eq 'A004') and (estmMngMstDetail.INN_CMTM_SLCT_MTHD_SECD ne 'A')}">
															<a href="javascript:;" class="component-button-s type-del" onclick="pageObj.cmtmSlctYnUpdt(this, '${data.ESTM_CMTM_SLCT_NGR }', '${data.ESTM_CMTM_NO}', '${data.INO_CMTM_SECD }', 'C')">취소</a>
														</c:if>
													</c:if>
												</c:if>
												<c:if test="${estmMngMstDetail.CMTM_SLCT_CMPL_YN eq 'Y'}"><!-- 평가위원선정완료여부 Y -->
													<c:if test="${ data.SLCT_YN eq 'Y' }">선정</c:if>
													<c:if test="${ data.SLCT_YN ne 'Y' }"><font color="red">비선정</font></c:if>
												</c:if>
												<input type="hidden" name="P_SLCT_YN_CHK" value="${data.SLCT_YN }" disabled="disabled">
											</c:if>
											
											<c:if test="${(estmMngMstDetail.ESTM_PSCD ne 'A004')}">
												<c:if test="${ data.SLCT_YN eq 'Y' }">선정</c:if>
												<c:if test="${ data.SLCT_YN ne 'Y' }"><font color="red">비선정</font></c:if>
												<input type="hidden" name="P_SLCT_YN_CHK" value="${data.SLCT_YN }" disabled="disabled">
											</c:if>
										</td>
										
										<td><!-- 비고 -->
											<c:if test="${ (not empty data.SLCT_YN) and (not empty data.RMK) }">
												<input type="text" name="P_RMK" value="${data.RMK }" class="component-input w100" maxlength="4000" readonly="readonly">	
											</c:if>
											<c:if test="${ (not empty data.SLCT_YN) and (empty data.RMK )}">	
												${data.RMK }
											</c:if>
											<c:if test="${ empty data.SLCT_YN }">
												<input type="text" name="P_RMK" class="component-input w100" maxlength="4000">	
											</c:if>
										</td>
									</tr>
								</c:forEach>
							</c:if>
						</c:if>
					</tbody>
				</table>
			</div>
		</div>
		
		
		<!-- bottom button -->
		<div class="bottom-buttons">
			<c:choose>
				<c:when test="${(estmMngMstDetail.OUT_CMTM_SLCT_CNT) ne (estmMngMstDetail.OUT_ESTM_CMTM_CNT ) 
					or (estmMngMstDetail.INN_CMTM_SLCT_CNT) ne (estmMngMstDetail.INN_ESTM_CMTM_CNT ) }">
						<c:if test="${(estmMngMstDetail.ESTM_PSCD eq 'A004')}"><!-- 평가위원순위선정완료 -->
							<a href="javascript:" class="btn-bottom type-b" id="prioRnkReSlct">우선순위재선정</a>
						</c:if>
						<c:if test="${(estmMngMstDetail.ESTM_PSCD eq 'A0041')}">
							<a href="javascript:" class="btn-bottom type-b" id="estmCmtmPrioRnkSlctRqstBtn">평가위원우선순위선정요청</a><!-- 우선순위일 경우 -->
						</c:if>
				</c:when>
				
				<c:when test="${(estmMngMstDetail.OUT_CMTM_SLCT_CNT) eq (comFn:nvl(estmMngMstDetail.OUT_ESTM_CMTM_CNT, 0) )
				and (estmMngMstDetail.INN_CMTM_SLCT_CNT) eq (comFn:nvl(estmMngMstDetail.INN_ESTM_CMTM_CNT, 0) ) }">
						<c:if test="${(estmMngMstDetail.ESTM_PSCD eq 'A004')}"><!-- 평가위원순위선정완료 -->
							<c:if test="${(estmMngMstDetail.CMTM_SLCT_CMPL_YN ne 'Y')}">
								<a href="javascript:" class="btn-bottom type-b" id="estmCmtmSlctCmpl">평가위원선정완료</a>
							</c:if>
						</c:if>
				</c:when>
				
				<c:otherwise>&nbsp;</c:otherwise>
			</c:choose>
			
			
			<c:if test="${(estmMngMstDetail.OUT_CMTM_SLCT_MTHD_SECD eq 'B') or (estmMngMstDetail.OUT_CMTM_SLCT_MTHD_SECD eq 'C') or (estmMngMstDetail.INN_CMTM_SLCT_MTHD_SECD eq 'B' ) }">
				<c:if test="${(estmMngMstDetail.ESTM_PSCD eq 'A001')}">
					<a href="javascript:" class="btn-bottom type-b" id="estmCmtmPrioRnkSlctRqstBtn">평가위원우선순위선정요청</a><!-- 우선순위일 경우 -->
				</c:if>
			</c:if>
			
			<c:if test="${estmMngMstDetail.CMTM_SLCT_CMPL_YN eq 'Y'}">
				<c:if test="${estmMngMstDetail.ESTM_PSCD ne 'A005'}"><!-- 평가시작 -->
					<a href="javascript:" class="btn-bottom type-b" id="saveBtn">저장</a><!-- 우선순위일 경우 -->
				</c:if>
			</c:if>
			
			<a href="javascript:" class="btn-bottom type-a" id="listBtn">목록</a>
			
		</div>
		<!-- //bottom button -->
	</form>
	
</div>
<!-- //Detail -->


<!-- LIST FORM -->
<form id="listFrm" method="POST">
	<input type="hidden" name="resourceDesc" value="${ param.resourceDesc}">
	<input type="hidden" name="resourceName" value="${comFn:nvl(param.resourceName, resourceName)}">
</form>

<!-- ESTM_CMTM_FORM -->
<form id="estmCmtmFrm" method="POST">
	<input type="hidden" name="resourceDesc" value="${ param.resourceDesc}">
	<input type="hidden" name="resourceName" value="${comFn:nvl(param.resourceName, resourceName)}">
	<input type="hidden" name="P_ESTM_NO" value="${estmMngMstDetail.ESTM_NO }">
	<input type="hidden" name="P_ESTM_CMTM_SLCT_NGR">
	<input type="hidden" name="P_ESTM_CMTM_NO">
	<input type="hidden" name="P_SLCT_YN">
	<input type="hidden" name="P_INO_CMTM_SECD">
	<input type="hidden" name="P_RMK">
</form>

<!-- POPUP FORM -->
<form id="popupFrm" method="POST">
	<input type="hidden" name="resourceName" value="${comFn:nvl(param.resourceName, resourceName)}">
	<input type="hidden" name="resourceDesc" value="${param.resourceDesc}">
	<input type="hidden" name="P_ESTM_NO" value="${estmMngMstDetail.ESTM_NO }">
	<input type="hidden" name="P_INO_CMTM_SECD">
	<input type="hidden" name="P_ESTM_OBJ_SEQ">
	<input type="hidden" name="P_FILE_GRP_NO">
	<input type="hidden" name="setMulti">
	<input type="hidden" name="P_ESTM_CMTM_CNT">
	<input type="hidden" name="tbdy_id">
</form>

<form id="ajaxCmtmForm" method="POST">
	<input type="hidden" name="P_ESTM_CMTM_NO_S">
	<input type="hidden" name="P_ESTM_CMTM_NO">
</form>

<!-- 선정된 평가위원 주민등록번호 저장을 위한 FORM -->
<form id="rsdnNoForm" name="rsdnNoForm" method="POST">
	<input type="hidden" name="P_ESTM_NO" value="${estmMngMstDetail.ESTM_NO }">
	<input type="hidden" name="P_ESTM_PSCD" value="${estmMngMstDetail.ESTM_PSCD }">
	<input type="hidden" name="P_ESTM_CMTM_SLCT_NGR" value="${comFn:nvl(estmMngMstDetail.MAX_ESTM_CMTM_SLCT_NGR, 1)}" disabled="disabled">
</form>