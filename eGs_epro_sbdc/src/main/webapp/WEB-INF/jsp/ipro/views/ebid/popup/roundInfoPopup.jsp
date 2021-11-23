<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 입찰 > 라운드정보 조회 팝업
 *
 * <pre>
 * ebid 
 *    |_ popup
 *		      |_ roundInfoPopup.jsp
 * 
 * </pre>
 * @date : 2017. 06. 13
 * @version : 1.0
 * @author : 은우소프트 전상훈
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %> 
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %> 

<script type="text/javascript" src="${jsPath}/ipro/views/ebid/popup/roundInfoPopup.js"></script> 
 
<div class="pop_wrap">
	<div class="pop_header">
		<h1 class="tit">라운드정보</h1>
	</div> <!--// pop_header E -->
	
	<div class="pop_container">
		<div class="view_wrap typeC">
			<div class="tit_area">
				<h2 class="tit01">라운드정보</h2>
			</div>
			<div class="view_area">
				<table class="table">
			    	<colgroup>
			        	<col style="width: 15%;">
			        	<col style="width: *;">
			        	<col style="width: 20%;">
			        	<col style="width: 15%;">
			        </colgroup>
			        <thead>
			        	<tr>
			        		<th class="txtc">라운드</th>
			        		<th class="txtc">입찰시간</th>
			        		<th class="txtc">개찰일시</th>
			        		<th class="txtc">예정가격</th>
			        	</tr>
			        </thead>
			        <tbody>
						<c:if test="${not empty bidResultRoundList}">
							<c:forEach var="data" items="${bidResultRoundList}" varStatus="status" >
								<tr class="row" onclick="detailInqire('${data.ROUND_NO}');" style="cursor: pointer;">
									<td class="txtc">${data.ROUND_NO}</td>						
									<td class="txtc">${comFn:formatDate(data.BIDC_SBMT_STDT,'yyyyMMddHHmmss','yyyy-MM-dd HH:mm')} ~ ${comFn:formatDate(data.BIDC_SBMT_ENDT,'yyyyMMddHHmmss','yyyy-MM-dd HH:mm')}</td>						
									<td class="txtc">${comFn:formatDate(data.OPNG_DT,'yyyyMMddHHmmss','yyyy-MM-dd HH:mm')}</td>						
									<td class="txtr pr5">${comFn:formatMoney(data.SCH_PRCE_AMT)}</td>						
								</tr>
							</c:forEach>
						</c:if>
			        </tbody>
				</table>
			</div>

			<input type="hidden" id="P_ROUND_NO_S" value="${param.P_ROUND_NO_S }">
			<div class="view_wrap typeB" id="roundDiv">
				<div class="tit_area">
					<h4 class="tit">${param.P_ROUND_NO_S } 라운드</h4>
				</div>
				<div class="view_area">
					<table>
						<caption>기본정보</caption>
						<colgroup>
							<col style="width: 15%;">
							<col style="width: 35%;">
							<col style="width: 15%;">
							<col style="width: 35%;">
						</colgroup>
						<tbody>
							<tr>
								<th>재입찰사유</th>
								<td colspan="3">
									<textarea id="P_ROUND_RSN" name="P_ROUND_RSN" readonly="readonly">${roundRsn}</textarea>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
				
				
				<c:if test="${(bidInfoDetail.BID_PSCD eq 'PF60' or bidInfoDetail.BID_PSCD eq 'PF61' or bidInfoDetail.BID_PSCD eq 'PF63') && bidInfoDetail.ESTPC_SECD eq '180000' }">
					<div class="tit_area">
						<h4 class="tit">예비가격별 선택현황<font color="red" size="2">*필수입력사항 표시 선택수를 클릭하시면 예가를 선택한 업체를 확인하실 수 있습니다.</font></h4>
					</div>			
					<div class="view_area">
					    <c:set var="P_UPR" value="4" />
						<table>
					    	<colgroup>
					            <col style="width: 8%;">
			                   	<col style="width: 18%;">
			                   	<col style="width: 7%;">
			                   	<col style="width: 8%;">
			                   	<col style="width: 18%;">
			                   	<col style="width: 7%;">
			                   	<col style="width: 8%;">
			                   	<col style="width: 18%;">
			                   	<col style="width: 7%;">
					        </colgroup>
					        <tr>
					            <th class="txtc">번호</th>
					            <th class="txtc">예비가격</th>
					            <th class="txtc">선택수</th>
					            <th class="txtc">번호</th>
					            <th class="txtc">예비가격</th>
					            <th class="txtc">선택수</th>
					            <th class="txtc">번호</th>
					            <th class="txtc">예비가격</th>
					            <th class="txtc">선택수</th>
					        </tr>
					        <tr class="txtc">
			                    <th class="thNoneStyle txtc">${aList[0]}</th>
			                    <td class="txtc">
			                      	<c:if test="${dList[0] <= P_UPR }">
			                        	<font color="red"><b>${comFn:formatMoney(bList[0])}</b></font>
			                        </c:if>
			                        <c:if test="${dList[0] > P_UPR }">
			                        	${comFn:formatMoney(bList[0])}
			                        </c:if>
			                    </td>
			                    <td class="txtc">${comFn:formatMoney(cList[0])}</td>
			                    <th class="thNoneStyle txtc">${aList[1]}</th>
			                    <td class="txtc">
			                        <c:if test="${dList[1] <= P_UPR }">
			                        	<font color="red"><b>${comFn:formatMoney(bList[1])}</b></font>
			                        </c:if>
			                        <c:if test="${dList[1] > P_UPR }">
			                        	${comFn:formatMoney(bList[1])}
			                        </c:if>
			                    </td>
			                    <td class="txtc">${comFn:formatMoney(cList[1])}</td>
			                    <th class="thNoneStyle txtc">${aList[2]}</th>
			                    <td class="txtc">
			                        <c:if test="${dList[2] <= P_UPR }">
			                        	<font color="red"><b>${comFn:formatMoney(bList[2])}</b></font>
			                        </c:if>
			                        <c:if test="${dList[2] > P_UPR }">
			                        	${comFn:formatMoney(bList[2])}
			                        </c:if>
			                    </td>
			                    <td class="txtc">${comFn:formatMoney(cList[2])}</td>
			                </tr>
			                <tr class="txtc">
			                    <th class="thNoneStyle txtc">${aList[3]}</th>
			                    <td class="txtc">
			                        <c:if test="${dList[3] <= P_UPR }">
			                        	<font color="red"><b>${comFn:formatMoney(bList[3])}</b></font>
			                        </c:if>
			                        <c:if test="${dList[3] > P_UPR }">
			                        	${comFn:formatMoney(bList[3])}
			                        </c:if>
			                    </td>
			                    <td class="txtc">${comFn:formatMoney(cList[3])}</td>
			
			                    <th class="thNoneStyle txtc">${aList[4]}</th>
			                    <td class="txtc">
			                        <c:if test="${dList[4] <= P_UPR }">
			                        	<font color="red"><b>${comFn:formatMoney(bList[4])}</b></font>
			                        </c:if>
			                        <c:if test="${dList[4] > P_UPR }">
			                        	${comFn:formatMoney(bList[4])}
			                        </c:if>
			                    </td>
			                    <td class="txtc">${comFn:formatMoney(cList[4])}</td>
			                    
			                    <th class="thNoneStyle txtc">${aList[5]}</th>
			                    <td class="txtc">
			                        <c:if test="${dList[5] <= P_UPR }">
			                        	<font color="red"><b>${comFn:formatMoney(bList[5])}</b></font>
			                        </c:if>
			                        <c:if test="${dList[5] > P_UPR }">
			                        	${comFn:formatMoney(bList[5])}
			                        </c:if>
			                    </td>
			                    <td class="txtc">${comFn:formatMoney(cList[5])}</td>
			                </tr>
			                <tr class="txtc">
			                	<th class="thNoneStyle txtc">${aList[6]}</th>
			                    <td class="txtc">
			                        <c:if test="${dList[6] <= P_UPR }">
			                        	<font color="red"><b>${comFn:formatMoney(bList[6])}</b></font>
			                        </c:if>
			                        <c:if test="${dList[6] > P_UPR }">
			                        	${comFn:formatMoney(bList[6])}
			                        </c:if>
			                    </td>
			                    <td class="txtc">${comFn:formatMoney(cList[6])}</td>
			                    <th class="thNoneStyle txtc">${aList[7]}</th>
			                    <td class="txtc">
			                        <c:if test="${dList[7] <= P_UPR }">
			                        	<font color="red"><b>${comFn:formatMoney(bList[7])}</b></font>
			                        </c:if>
			                        <c:if test="${dList[7] > P_UPR }">
			                        	${comFn:formatMoney(bList[7])}
			                        </c:if>
			                    </td>
			                    <td class="txtc">${comFn:formatMoney(cList[7])}</td>
			                	<th class="thNoneStyle txtc">${aList[8]}</th>
			                    <td class="txtc">
			                        <c:if test="${dList[8] <= P_UPR }">
			                        	<font color="red"><b>${comFn:formatMoney(bList[8])}</b></font>
			                        </c:if>
			                        <c:if test="${dList[8] > P_UPR }">
			                        	${comFn:formatMoney(bList[8])}
			                        </c:if>
			                    </td>
			                    <td class="txtc">${comFn:formatMoney(cList[8])}</td>
			                </tr>
			                <tr class="txtc">
			                    <th class="thNoneStyle txtc">${aList[9]}</th>
			                    <td class="txtc">
			                        <c:if test="${dList[9] <= P_UPR }">
			                        	<font color="red"><b>${comFn:formatMoney(bList[9])}</b></font>
			                        </c:if>
			                        <c:if test="${dList[9] > P_UPR }">
			                        	${comFn:formatMoney(bList[9])}
			                        </c:if>
			                    </td>
			                    <td class="txtc">${comFn:formatMoney(cList[9])}</td>
			                    <th class="thNoneStyle txtc">${aList[10]}</th>
			                    <td class="txtc">
			                        <c:if test="${dList[10] <= P_UPR }">
			                        	<font color="red"><b>${comFn:formatMoney(bList[10])}</b></font>
			                        </c:if>
			                        <c:if test="${dList[10] > P_UPR }">
			                        	${comFn:formatMoney(bList[10])}
			                        </c:if>
			                    </td>
			                    <td class="txtc">${comFn:formatMoney(cList[10])}</td>
			                    <th class="thNoneStyle txtc">${aList[11]}</th>
			                    <td class="txtc">
			                        <c:if test="${dList[11] <= P_UPR }">
			                        	<font color="red"><b>${comFn:formatMoney(bList[11])}</b></font>
			                        </c:if>
			                        <c:if test="${dList[11] > P_UPR }">
			                        	${comFn:formatMoney(bList[11])}
			                        </c:if>
			                    </td>
			                    <td class="txtc">${comFn:formatMoney(cList[11])}</td>
			                </tr>
			                <tr class="txtc">
			                    <th class="thNoneStyle txtc">${aList[12]}</th>
			                    <td class="txtc">
			                        <c:if test="${dList[12] <= P_UPR }">
			                        	<font color="red"><b>${comFn:formatMoney(bList[12])}</b></font>
			                        </c:if>
			                        <c:if test="${dList[12] > P_UPR }">
			                        	${comFn:formatMoney(bList[12])}
			                        </c:if>
			                    </td>
			                    <td class="txtc">${comFn:formatMoney(cList[12])}</td>
			                    <th class="thNoneStyle txtc">${aList[13]}</th>
			                    <td class="txtc">
			                        <c:if test="${dList[13] <= P_UPR }">
			                        	<font color="red"><b>${comFn:formatMoney(bList[13])}</b></font>
			                        </c:if>
			                        <c:if test="${dList[13] > P_UPR }">
			                        	${comFn:formatMoney(bList[13])}
			                        </c:if>
			                    </td>
			                    <td class="txtc">${comFn:formatMoney(cList[13])}</td>
			                    <th class="thNoneStyle txtc">${aList[14]}</th>
			                    <td class="txtc">
			                        <c:if test="${dList[14] <= P_UPR }">
			                        	<font color="red" ><b>${comFn:formatMoney(bList[14])}</b></font>
			                        </c:if>
			                        <c:if test="${dList[14] > P_UPR }">
			                        	${comFn:formatMoney(bList[14])}
			                        </c:if>
			                    </td>
			                    <td class="txtc" onclick="bidResultPrdprcChoiseEntrpsInqire('${aList[14]}');">${comFn:formatMoney(cList[14])}</td>
			                </tr>
					    </table>
					</div>
				</c:if>
				
				
				<!-- 투찰업체 START -->
				<c:if test="${bidInfoDetail.BID_PSCD eq 'PF60' or bidInfoDetail.BID_PSCD eq 'PF61' or bidInfoDetail.BID_PSCD eq 'PF63'}">
					<div class="tit_area">
						<h4 class="tit">투찰업체</h4>
					</div>			
					<div class="view_area">
			            <table>
			                <caption>투찰업체</caption>
			                <colgroup>
			                   	<col style="width: 15%;">
					        	<col style="width: 15%;">
					        	<col style="width: 10%;">
					        	<col style="width: 8%;">
					        	<col style="width: 15%;">
					        	<col style="width: 15%;">
					        	<col style="width: 10%;">
			                </colgroup>
			                <thead>
				                <tr>
				                    <th class="txtc">업체명</th>
				                    <th class="txtc">사업자번호</th>
				                    <th class="txtc">대표자</th>
				                    <th class="txtc">순위</th>
				                    <th class="txtc">투찰금액</th>
				                    <th class="txtc">투찰률</th>
				                    <th class="txtc">낙찰여부</th>
				                </tr>
				            </thead>
			                <c:if test="${empty scsbidPrearngerList}">
								<tr>
									<td colspan="7" class="txtc">데이터가 존재 하지 않습니다</td>
								</tr>
							</c:if>
							<c:if test="${not empty scsbidPrearngerList}">
								<tbody id="lwetScsbidPrearngerListJson">
									<c:forEach var="data" items="${scsbidPrearngerList}" varStatus="status" >
										<tr>
											<td class="txtl">${data.VEND_NM}</td>
											<td class="txtc">${comFn:formatBizNumber(data.BIZRNO) }</td>
											<td class="txtc">${data.RPRS_NM}</td>
											<td class="txtc">${data.OPNG_RNK}</td>
											<td class="txtr pr5">${comFn:formatMoney(data.TNDR_AMT)}</td>
											<td class="txtc">${data.BDDPR_RT} %</td>
											<td class="txtc">
												<c:if test="${ bidInfoDetail.BID_PSCD eq 'PF61'}">
													<c:if test="${data.SBID_YN eq 'Y' }">낙찰</c:if>
												</c:if>
											</td>
										</tr>
									</c:forEach>
								</tbody>
							 </c:if> 
			            </table>
				    </div>
				</c:if>
				
			</div>

			<div class="btn_wrap view_btn">
		    	<button type="button" class="btn btn_m btn_del" id="closeBtn" >닫기</button>
		    </div>
		</div>
    </div>
</div> <!--// content E-->

<!-- DETAIL FORM -->
<form id="detailFrm" method="POST">
	<input type="hidden" name="P_ANNC_NO" value="${param.P_ANNC_NO }">
	<input type="hidden" name="P_ANNC_NGR" value="${param.P_ANNC_NGR }">
	<input type="hidden" name="P_ROUND_NO_S">
</form>