<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 평가관리 > 평가완료현황 상세 - 평가결과
 *
 * <pre>
 * estm
 *    |_ estmCmplResultDetail.jsp
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

<script type="text/javascript" src="${jsPath}/ipro/views/estm/estmCmplResultDetail.js"></script>

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
		<input type="hidden" name="P_SEARCH_CHECK_YN" value="${comFn:nvl(P_SEARCH_CHECK_YN, 'N')}">
		<input type="hidden" name="P_REGR_NM" value="${sessionScope.loginResult.USR_NM}">
		<input type="hidden" name="P_USR_ID" value="${sessionScope.loginResult.USR_ID}">
		<input type="hidden" name="resourceDesc" value="${ param.resourceDesc}">
		<input type="hidden" name="resourceName" value="${comFn:nvl(param.resourceName, resourceName)}">
		<input type="hidden" name="P_ESTM_PROCD_SEQ" value="${param.P_ESTM_PROCD_SEQ}">
		<input type="hidden" name="P_ESTM_NO" value="${estmMngMstDetail.ESTM_NO }">
		<input type="hidden" name="P_ESTR_SECD">
		<input type="hidden" name="P_ESTM_CMTM_SLCT_NGR" value="${estmMngMstDetail.MAX_ESTM_CMTM_SLCT_NGR }">
		<input type="hidden" id="P_CLC_RUL" name="P_CLC_RUL" value="${estmMngMstDetail.CLC_RUL}">
		<input type="hidden" name="P_CMPL_YN" value="${estmMngMstDetail.CMTM_SLCT_CMPL_YN }">
		<input type="hidden" id="P_PAGE_NO" name="P_PAGE_NO" value="${comFn:nvl(param.P_PAGE_NO, 1)}">
		<input type="hidden" id="P_PAGE_SIZE" name="P_PAGE_SIZE" value="${comFn:nvl(param.P_PAGE_SIZE, 100)}">

		
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
		
		<div class="table-detail">
			<!-- Top -->
			<div class="top-detail">
				<div class="type-fleft">
					<div class="contents-label">평가결과산술식</div>
				</div>
				
				<c:forEach var="data" items="${estmTabList}" varStatus="status">
					<input type="hidden" name="D_ESTM_PROCD_SEQ" value="${data.ESTM_PROCD_SEQ }"/>
					<input type="hidden" name="D_ESTM_PROCD_SECD" value="${data.ESTM_PROCD_SECD }"/>
					<input type="hidden" name="D_ESTM_PROCD_NM" value="${data.ESTM_PROCD_NM }"/>
				</c:forEach>
				
				<div class="type-fright">
					<button type="button" class="component-button-s type-add" id="addSanSoolStrBtn" name="addBtn">산술추가</button>
					<button type="button" class="component-button-s type-add" id="addProcdBtn" name="addBtn">절차추가</button>
					<button type="button" class="component-button-s type-del" id="delSanSoolBtn" name="delBtn">산술식삭제</button>
					<button type="button" class="component-button-s type-add" id="sansoolSaveBtn" >산술식저장</button>
					<!-- <a href="javascript:" class="btn-bottom type-b" id="sansoolSaveBtn">산술저장</a> -->
				</div>
			</div>
			<!-- //Top -->

			<table class="component-detail-table type-line-none">
				<colgroup>
					<col width="150">
					<col width="*">
				</colgroup>
				<tbody>
					<tr >
						<th>산술식</th>
						<td id="sansool"></td>
					</tr>

				</tbody>
			</table>
		</div>
		
		<!-- //필수정보 고정 -->
		
		
		<!-- tab -->
		<div class="component-tab-line">
			<ul class="list-tab">
				<li>
					<a href="javascript:cmplTabEvent(1);">기본정보</a>
				</li>
				<li>
					<a href="javascript:cmplTabEvent(2);">평가대상</a>
				</li>
				<li>
					<a href="javascript:cmplTabEvent(3);">평가위원</a>
				</li>
				<c:forEach var="data" items="${estmTabList}" varStatus="status">
					<c:if test="${estmProcdDetail.ESTM_PROCD_SEQ eq data.ESTM_PROCD_SEQ }">
						<li class="is-selected">
							<a href="javascript:dynamicEstmTabEvent('${data.ESTM_PROCD_SEQ }','${data.ESTR_SECD }','detailFrm','cmpl');">${data.ESTM_PROCD_NM }</a>
						</li>
					</c:if>
					<c:if test="${estmProcdDetail.ESTM_PROCD_SEQ ne data.ESTM_PROCD_SEQ }">
						<li>
							<a href="javascript:dynamicEstmTabEvent('${data.ESTM_PROCD_SEQ }','${data.ESTR_SECD }','detailFrm','cmpl');">${data.ESTM_PROCD_NM }</a>
						</li>
					</c:if>
				</c:forEach>
				<li class="is-selected">
					<a href="javascript:cmplTabEvent(7);">평가결과</a>
				</li>
				<li>
					<a href="javascript:cmplTabEvent(8);">화상회의</a>
				</li>
				<li class="">
					<a href="javascript:cmplTabEvent(9);">수당지급</a>
				</li>
			</ul>
		</div>
		<!-- //tab -->
		
		<!-- 평가결과인경우 -->
		<div class="sch_box filter_sch view">
				<a href="#" class="filter_btn">상세검색필터 닫기</a>
				<!-- 평가결과조회 -->
				<div class="table-detail" id="filter_schDiv" style="display: ;"><!-- display: none; -->
					<table class="component-detail-table ">
						<colgroup>
							<c:forEach var="data" items="${estmTabList}" varStatus="status">
							<col width="auto;">
							</c:forEach>
							
						</colgroup>
						<thead>
							<tr>
							<c:forEach var="data" items="${estmTabList}" varStatus="status">
								<th style="width: auto;">${data.ESTM_PROCD_NM }</th>
							</c:forEach>
							</tr>
						</thead>
						<tbody>
							<%-- <!-- 제일 상단에 평가위원총점, 평가총점만 체크박스 보이도록 구현하기 -->
							<tr>
							<c:forEach var="data" items="${estmTabList}" varStatus="status">
								<td>
									<label class="component-checkbox">
										<input type="checkbox" name="P_SEARCH_ITEM_TOTSUMAT" id="TOTSUMAT${ data.ESTM_PROCD_SEQ }"  value="${ data.ESTM_PROCD_SEQ }"/>
										<i></i>
										<span class="txt-checkbox">최종점수합계적용여부</span>
										<span class="txt-checkbox" style="display: none;">&nbsp;&nbsp;&nbsp;&nbsp;[합계비율]&nbsp;&nbsp;:&nbsp;&nbsp;</span>
									</label>
									<input type="text" id="P_ESTM_PROCD_SEQ_TOTSUM${ data.ESTM_PROCD_SEQ }" name="P_ESTM_PROCD_SEQ_TOTSUM${ data.ESTM_PROCD_SEQ }" class="component-input" style="width:50px;height:20px;display: none;" maxlength="600">
								</td>
							</c:forEach>
							</tr> --%>
							<tr>
							<c:forEach var="data" items="${estmTabList}" varStatus="status">
								<td>
									<c:if test="${data.ESTM_FRM_PROCD_SECD ne 'C' }">
									<label class="component-checkbox">
										<input type="checkbox" name="P_SEARCH_ITEM" id="SEQTOT${ data.ESTM_PROCD_SEQ }"  value="TOT-${ data.ESTM_PROCD_SEQ }"
										
										<c:forEach items="${SEARCH_ITEM_CHECK_LIST}" var="selecColData" varStatus="cnt">
											<c:set var="totVal" value="TOT-${ data.ESTM_PROCD_SEQ }" />
									 		<c:if test="${ selecColData.VAL eq totVal }">
									 			checked
									 		</c:if>
										</c:forEach>
										
										/>
										<i></i>
										<span class="txt-checkbox">평가총점</span>
									</label>
									</c:if>
								</td>
							</c:forEach>
							</tr>
							
							<tr>
							<!-- 
								평가절차가 평가담당자이면 평가위원총점 선택막기
								평가위원이 있는 경우(평가위원 ESTR_SECD : A)
								평가위원이 없는 경우(평가담당자 ESTR_SECD : B)
							-->
							<c:forEach var="data" items="${estmTabList}" varStatus="status">
								<td>
									<c:if test="${data.ESTR_SECD eq 'A' }">
										<label class="component-checkbox">
											<input type="checkbox" name="P_SEARCH_ITEM" id="CMTMTOT${ data.ESTM_PROCD_SEQ }"  value="CMTM-${ data.ESTM_PROCD_SEQ }"
											
											<c:forEach items="${SEARCH_ITEM_CHECK_LIST}" var="selecColData" varStatus="cnt">
												<c:set var="cmtmVal" value="CMTM-${ data.ESTM_PROCD_SEQ }" />
										 		<c:if test="${ selecColData.VAL eq cmtmVal }">
										 			checked
										 		</c:if>
											</c:forEach>
											
											/>
											<i></i>
											<span class="txt-checkbox">평가위원총점</span>
										</label>
									</c:if>
									<c:if test="${data.ESTR_SECD eq 'B' }">
										<!-- <label class="component-checkbox">
											<i></i>
											<span class="txt-checkbox">평가위원총점(담당자평가이기 때문에 제외)</span>
										</label> -->
									</c:if>
								</td>
							</c:forEach>
							</tr>
							
							<c:forEach var="row" begin="1" end="${maxSearchColLength}">
							<tr>
							<c:forEach var="data" items="${estmTabList}" varStatus="status">
								<td>	
									<c:forEach var="searchCol" items="${searchColList}" varStatus="status">
										<c:if test="${row eq searchCol.ITEM_NUMBER}">
											<c:if test="${searchCol.ESTM_PROCD_SEQ eq data.ESTM_PROCD_SEQ }">
												<c:if test="${not empty searchCol.ESTM_ITEM_NM}">
													<label class="component-checkbox">
														<input type="checkbox" name="P_SEARCH_ITEM" id="P_SEARCH_ITEM${ data.ESTM_PROCD_SEQ }-${searchCol.ESTM_ITEM_NO}"  value="${ data.ESTM_PROCD_SEQ }-${searchCol.ESTM_ITEM_NO}"
														
														<c:forEach items="${SEARCH_ITEM_CHECK_LIST}" var="selecColData" varStatus="cnt">
													 		<c:if test="${ selecColData.VAL eq searchCol.ESTM_NUMBER }">
													 			checked
													 		</c:if>
														</c:forEach>
														
														/>
														<i></i>
														<span class="txt-checkbox"><c:out value='${ searchCol.ESTM_ITEM_NM }'/></span>
													</label>
												</c:if>
											</c:if>
										</c:if>
									</c:forEach>
								</td>
								</c:forEach>
							</tr>
							</c:forEach>
						</tbody>
					</table>
					
					<!-- bottom button -->
					<div class="bottom-buttons">
						<a href="javascript:" class="btn-bottom type-a" id="searchBtn">조회</a>
					</div>
					<!-- //bottom button -->
				</div>
				<!-- //평가결과조회 -->
		</div>
		<!-- //평가결과인경우 -->
	</form>
		
		<div class="table-detail">
			<!-- Option -->
			<div class="table-option">
				<!-- Right -->
				<div class="option-right">
					<div class="table-num type-fleft">
						총 <strong>${comFn:nvl(estmResultObjAllListTotCnt, 0)}</strong>건
					</div>
					<a href="javascript:excelDwd('detailFrm', '/estm/estmCmplResultDetailExcelDwld.do','${comFn:nvl(estmResultObjAllListTotCnt, 0)}');" class="btn-download-s type-fleft">
						<i class="icon-download"></i>엑셀 다운로드
					</a>
				</div>
				<!-- //Right -->
			</div>
			<!-- //Option -->
			<div style="width: auto; height: 400px; overflow-x: auto; overflow-y: auto; white-space:nowrap;">
			<table class="component-detail-table " style="width: auto; ">
				<colgroup>
					<col width="80px;">
					<col width="200px;">
					<col width="auto;">
				</colgroup>
				<thead id="excelTh">
					<tr id="0">
						<!--
							평가위원
							평가위원이 있는 경우(ESTR_SECD : A) (평가위원수*체크된평가항목) + 평가총점여부
							평가위원이 없는 경우(ESTR_SECD : B) 체크된평가항목 + 평가총점여부
						 -->
						<c:set var="rowsize" value="2"/>
						<c:forEach var="procdSeq" items="${estmResultProcdSeqList}" varStatus="procdSeqStatus">
							<c:if test="${procdSeq.ESTR_SECD eq 'A' }">
								<c:forEach var="itemNo" items="${estmResultItemNoList}" varStatus="itemNoStatus">
										<c:set var="totVal" value="TOT-${ itemNo.ESTM_PROCD_SEQ }" />
										<c:if test="${ (itemNo.ESTM_NUMBER ne totVal) and (procdSeq.ESTM_PROCD_SEQ eq itemNo.ESTM_PROCD_SEQ) }">
											<!-- 평가총점 'TOT-'로 시작하는 데이터가 아니면 cnt2 추가 -->
											<c:set var="rowsize" value="3" />
										</c:if>
								</c:forEach>
							</c:if>
						</c:forEach>
					
						<th rowspan="${rowsize }" style="vertical-align: middle;">순위</th>
						<th rowspan="${rowsize }" style="vertical-align: middle;">평가대상명</th>
						<!-- 
							[평가명]
							평가별 colspan 갯수 체크
							평가위원이 있는 경우(ESTR_SECD : A) (평가위원수*체크된평가항목) + 평가총점여부
							평가위원이 없는 경우(ESTR_SECD : B) 체크된평가항목 + 평가총점여부
						-->
						<c:forEach var="procdSeq" items="${estmResultProcdSeqList}" varStatus="procdSeqStatus">
							<!-- 평가위원일 때 -->
							<c:if test="${procdSeq.ESTR_SECD eq 'A' }">
								<c:set var="cnt" value="0" />
								<c:set var="cnt2" value="0" />
								<c:forEach var="itemNo" items="${estmResultItemNoList}" varStatus="itemNoStatus">
									<c:if test="${procdSeq.ESTM_PROCD_SEQ eq itemNo.ESTM_PROCD_SEQ }">
										<c:set var="totVal" value="TOT-${ itemNo.ESTM_PROCD_SEQ }" />
										<!-- 평가총점여부판별 -->
								 		<c:if test="${ itemNo.ESTM_NUMBER eq totVal }">
								 			<!-- 평가총점 'TOT-'로 시작하는 데이터이면 cnt 추가 -->
											<c:set var="cnt" value="${cnt+1}" />
										</c:if>
										<c:if test="${ itemNo.ESTM_NUMBER ne totVal }">
											<!-- 평가총점 'TOT-'로 시작하는 데이터가 아니면 cnt2 추가 -->
											<c:set var="cnt2" value="${cnt2+1}" />
										</c:if>
									</c:if>
								</c:forEach>
								<c:set var="cmtmCnt" value="${estmResultCmtmAllListCnt }"/>
								<c:if test="${procdSeq.ESTM_PROCD_SECD eq 'C' }">
									<c:set var="cnt" value="${(cmtmCnt*cnt2)+cnt+1}" />
								</c:if>
								<c:if test="${procdSeq.ESTM_PROCD_SECD ne 'C' }">
									<c:set var="cnt" value="${(cmtmCnt*cnt2)+cnt}" />
								</c:if>
								<th colspan="${cnt}"> ${procdSeq.ESTM_PROCD_NM }</th>
							</c:if>
							<!-- 평가담당자일 때 -->
							<c:if test="${procdSeq.ESTR_SECD eq 'B' }">
								<c:set var="cnt" value="0" />
								<c:set var="cnt2" value="0" />
								<c:forEach var="itemNo" items="${estmResultItemNoList}" varStatus="itemNoStatus">
									<c:if test="${procdSeq.ESTM_PROCD_SEQ eq itemNo.ESTM_PROCD_SEQ }">
										<c:set var="totVal" value="TOT-${ itemNo.ESTM_PROCD_SEQ }" />
										<!-- 평가총점여부판별 -->
								 		<c:if test="${ itemNo.ESTM_NUMBER eq totVal }">
								 			<!-- 평가총점 'TOT-'로 시작하는 데이터이면 cnt 추가 -->
											<c:set var="cnt" value="${cnt+1}" />
										</c:if>
										<c:if test="${ itemNo.ESTM_NUMBER ne totVal }">
											<!-- 평가총점 'TOT-'로 시작하는 데이터가 아니면 cnt2 추가 -->
											<c:set var="cnt2" value="${cnt2+1}" />
										</c:if>
									</c:if>
								</c:forEach>
								<%-- <c:set var="cmtmCnt" value="${estmResultCmtmAllListCnt }"/> --%>
								<c:if test="${procdSeq.ESTM_PROCD_SECD eq 'C' }">
									<c:set var="cnt2" value="${cnt2+cnt+1}" />
								</c:if>
								<c:if test="${procdSeq.ESTM_PROCD_SECD ne 'C' }">
									<c:set var="cnt2" value="${cnt2+cnt}" />
								</c:if>
								<th colspan="${cnt2}"> ${procdSeq.ESTM_PROCD_NM }</th>
							</c:if>
						</c:forEach>
						<th rowspan="${rowsize }" style="vertical-align: middle;">최종결과점수</th>
					</tr>
					<tr id="1">
						<c:forEach var="procdSeq" items="${estmResultProcdSeqList}" varStatus="procdSeqStatus">
							<!-- 평가위원일때 -->
							<c:if test="${procdSeq.ESTR_SECD eq 'A' }">
								<c:set var="cnt2" value="0" /><!-- 평가항목cnt -->
								<c:forEach var="itemNo" items="${estmResultItemNoList}" varStatus="itemNoStatus">
									<c:if test="${procdSeq.ESTM_PROCD_SEQ eq itemNo.ESTM_PROCD_SEQ }">
										<c:set var="totVal" value="TOT-${ itemNo.ESTM_PROCD_SEQ }" />
										<c:if test="${ itemNo.ESTM_NUMBER ne totVal }">
											<!-- 평가총점 'TOT-'로 시작하는 데이터가 아니면 cnt2 추가 -->
											<c:set var="cnt2" value="${cnt2+1}" />
										</c:if>
									</c:if>
								</c:forEach>
								<!-- 아이템이 평가총점을 제외하고 없는 경우 평가위원 리스트 안나오도록 구현 -->
								<c:if test="${cnt2 ne 0 }">
									<c:forEach var="cmtm" items="${estmResultCmtmAllList}" varStatus="cmtmStatus">
										<th style="width: auto;" colspan="${cnt2 }">${cmtm.ESTM_CMTM_NM }</th>
									</c:forEach>
								</c:if>
								
								<!-- 평가점수  -->
								<c:forEach var="itemNo" items="${estmResultItemNoList}" varStatus="itemNoStatus">
									<c:if test="${procdSeq.ESTM_PROCD_SEQ eq itemNo.ESTM_PROCD_SEQ }">
										<c:set var="totVal" value="TOT-${ itemNo.ESTM_PROCD_SEQ }" />
								 		<c:if test="${ itemNo.ESTM_NUMBER eq totVal }">
								 			<c:if test="${rowsize eq '3' }">
												<th rowspan="2" style="width: auto;vertical-align: middle;">${itemNo.ESTM_ITEM_NM }</th>
											</c:if>
											<c:if test="${rowsize ne '3' }">
												<th style="width: auto;">${itemNo.ESTM_ITEM_NM }</th>
											</c:if>
										</c:if>
									</c:if>
								</c:forEach>
								<c:if test="${procdSeq.ESTM_PROCD_SECD eq 'C' }">
									<th rowspan="2" style="width: auto;vertical-align: middle;">적격여부</th>
								</c:if>
							</c:if>
							<!-- 평가담당자일때 -->
							<c:if test="${procdSeq.ESTR_SECD eq 'B' }">
								<!-- 평가점수  -->
								<c:forEach var="itemNo" items="${estmResultItemNoList}" varStatus="itemNoStatus">
									<c:if test="${procdSeq.ESTM_PROCD_SEQ eq itemNo.ESTM_PROCD_SEQ }">
								 			<c:if test="${rowsize eq '3' }">
												<th rowspan="2" style="width: auto;vertical-align: middle;">${itemNo.ESTM_ITEM_NM }</th>
											</c:if>
											<c:if test="${rowsize ne '3' }">
												<th style="width: auto;">${itemNo.ESTM_ITEM_NM }</th>
											</c:if>
									</c:if>
								</c:forEach>
								<%-- <c:if test="${procdSeq.ESTM_FRM_PROCD_SECD eq 'C' }"> --%>
								<c:if test="${procdSeq.ESTM_PROCD_SECD eq 'C' }">
									<c:if test="${rowsize eq '3' }">
										<th rowspan="2" style="width: auto;vertical-align: middle;">적격여부</th>
									</c:if>
									<c:if test="${rowsize ne '3' }">
										<th style="width: auto;vertical-align: middle;">적격여부</th>
									</c:if>
								</c:if>
							</c:if>
						</c:forEach>
					</tr>
					<c:if test="${rowsize eq '3' }">
					<tr id="2">
						<c:forEach var="procdSeq" items="${estmResultProcdSeqList}" varStatus="procdSeqStatus">
							<!-- 평가위원일때 -->
							<c:if test="${procdSeq.ESTR_SECD eq 'A' }">
								<c:forEach var="cmtm" items="${estmResultCmtmAllList}" varStatus="cmtmStatus">
									<c:forEach var="itemNo" items="${estmResultItemNoList}" varStatus="itemNoStatus">
										<c:if test="${procdSeq.ESTM_PROCD_SEQ eq itemNo.ESTM_PROCD_SEQ }">
												<c:if test="${procdSeq.ESTM_PROCD_SEQ eq itemNo.ESTM_PROCD_SEQ }">
													<c:set var="totVal" value="TOT-${ itemNo.ESTM_PROCD_SEQ }" />
											 		<c:if test="${ itemNo.ESTM_NUMBER ne totVal }">
															<th style="width: auto;">${itemNo.ESTM_ITEM_NM }</th>
													</c:if>
												</c:if>
										</c:if>
									</c:forEach>
								</c:forEach>
							</c:if>
							<!-- 평가담당자는 3번째 row에 나올 필요가 없음. -->
						</c:forEach>
					</tr>
					</c:if>
				</thead>
				<tbody>
					<c:forEach var="obj" items="${estmResultObjAllList }" varStatus="objStatus"><!-- 평가대상 리스트 -->
					<tr>
						<td class="txt-center">${obj.RNUM }</td><!-- 순위 -->
						<td class="txt-center">
							${obj.OBJ_NM }
						</td><!-- 평가대상명 -->
							<c:forEach var="procdSeq" items="${estmResultProcdSeqList}" varStatus="procdSeqStatus">
								<!-- 평가위원일때 -->
								<c:if test="${procdSeq.ESTR_SECD eq 'A' }">
									<c:forEach var="cmtm" items="${estmResultCmtmAllList}" varStatus="cmtmStatus">
										<c:forEach var="itemNo" items="${estmResultItemNoList}" varStatus="itemNoStatus">
											<c:forEach var="itemAll" items="${estmResultItemAllList}" varStatus="itemAllStatus">
												<c:if test="${	   
														(procdSeq.ESTM_PROCD_SEQ eq itemAll.ESTM_PROCD_SEQ)
													and (obj.ESTM_OBJ_SEQ eq itemAll.ESTM_OBJ_SEQ)
												}">
													<c:if test="${
														(cmtm.ESTM_CMTM_NO eq itemAll.ESTM_CMTM_NO)
														and (itemNo.ESTM_NUMBER eq itemAll.ESTM_NUMBER)
													}">
														<td class="txt-center" style="width: auto;">${itemAll.ESTM_SCR }</td>
													</c:if>
												</c:if>
												</c:forEach>
											</c:forEach>
										</c:forEach>
									</c:if>
									<!-- //평가위원일때 -->
									<!-- 평가담당자일때 -->
									<c:if test="${procdSeq.ESTR_SECD eq 'B' }">
									<!-- 평가점수  -->
									<c:forEach var="itemNo" items="${estmResultItemNoList}" varStatus="itemNoStatus">
										<c:forEach var="itemAll" items="${estmResultItemAllList}" varStatus="itemAllStatus">
											<c:if test="${	   
													(procdSeq.ESTM_PROCD_SEQ eq itemAll.ESTM_PROCD_SEQ)
												and (obj.ESTM_OBJ_SEQ eq itemAll.ESTM_OBJ_SEQ)
												and (itemNo.ESTM_NUMBER eq itemAll.ESTM_NUMBER)
											}">
												<td class="txt-center" style="width: auto;">${itemAll.ESTM_SCR }</td>
											</c:if>
											</c:forEach>
										</c:forEach>
									</c:if>
									<!-- //평가담당자일때 -->
									
								<!-- 평가절차구분이 적격인경우  -->
								<%-- <c:if test="${procdSeq.ESTM_FRM_PROCD_SECD eq 'C' }"> --%>
								<c:if test="${procdSeq.ESTM_PROCD_SECD eq 'C' }">
									<c:forEach var="slct" items="${estmResultProcdObjSlctList}" varStatus="slctStatus">
										<c:if test="${ (procdSeq.ESTM_PROCD_SEQ eq slct.ESTM_PROCD_SEQ) and (obj.ESTM_OBJ_SEQ eq slct.ESTM_OBJ_SEQ) }">
											<td class="txt-center" style="width: auto;">${slct.SLCT_YN_NM}</td>
										</c:if>
									</c:forEach>
								</c:if>
								<!-- //평가절차구분이 적격인경우  -->
									
									
									
									<!-- 평가위원인 평가절차에서
										  평가총점이 존재시 -->
									<c:if test="${procdSeq.ESTR_SECD eq 'A' }">
										<c:forEach var="itemNo" items="${estmResultItemNoList}" varStatus="itemNoStatus">
											<c:forEach var="itemAll" items="${estmResultItemAllList}" varStatus="itemAllStatus">
												<c:if test="${	   
														(procdSeq.ESTM_PROCD_SEQ eq itemAll.ESTM_PROCD_SEQ)
													and (obj.ESTM_OBJ_SEQ eq itemAll.ESTM_OBJ_SEQ)
													and (fn:contains(itemAll.ESTM_NUMBER, 'TOT-'))
													and (itemNo.ESTM_NUMBER eq itemAll.ESTM_NUMBER)
												}">
													<td class="txt-center" style="width: auto;">
														${itemAll.ESTM_SCR }
													</td>
												</c:if>
											</c:forEach>
										</c:forEach>
									</c:if>
						</c:forEach>
						<td class="txt-center">${obj.CLC_TOT_SCR }</td><!-- 최종결과점수 -->
					</tr>
					</c:forEach>
				</tbody>
			</table>
			</div>
		</div>
		
		<!-- pageing -->
		<div class="component-pageing">
			<comTag:pagingIpro totalCount="${comFn:nvl(estmResultObjAllListTotCnt,0)}" pageNo="${comFn:nvl(param.P_PAGE_NO, 1)}" pageSize="${comFn:nvl(param.P_PAGE_SIZE, 100)}" clickPage="pageObj.clickPage"/>
		</div>
		<!--//pageing -->
		
		<!-- bottom button -->
		<div class="bottom-buttons">
			<a href="javascript:" class="btn-bottom type-a" id="listBtn">목록</a>
		</div>
		<!-- //bottom button -->
		

	
</div>
<!-- //Detail -->


<!-- LIST FORM -->
<form id="listFrm" method="POST">
	<input type="hidden" name="resourceName" value="${comFn:nvl(param.resourceName, resourceName)}">
</form>

<form id="popupFrm" method="POST"> 
	<input type="hidden" name="resourceName" value="${comFn:nvl(param.resourceName, resourceName)}">
	<input type="hidden" name="P_FILE_GRP_NO">
</form>