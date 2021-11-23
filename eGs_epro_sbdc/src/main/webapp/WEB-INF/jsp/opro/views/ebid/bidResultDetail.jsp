<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 입찰관리 > 입찰결과현황 상세
 *
 * <pre>
 * ebid 
 *    |_ bidResultDetail.jsp
 * 
 * </pre>
 * @date : 2017. 06.21
 * @version : 1.0
 * @author : 은우소프트 이주연 
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 

<script type="text/javascript" src="${jsPath}/opro/views/ebid/bidResultDetail.js"></script>

<div class="contents_wrap">
	<ul class="step_wrap">
		<li><a href="#">${myMenuList.bigMenuNm}</a></li>
		<li><a href="#">${myMenuSubList.smallMenuNm}</a></li>
	</ul>
	<div class="tit_wrap">
		<h3 class="tit">입찰결과현황 상세</h3>
	</div>
	
	<div class="view_wrap typeB">
		<div class="tit_area">
			<h4 class="tit">입찰개요</h4>
			<div class="btn_right">
				<button type="button" class="btn btn_s2 btn_c2" id="detailBtn" style="width: 120px;">입찰공고문 상세</button>
			</div>
		</div>
		<div class="view_area">
			<table>
		    	<colgroup>
		        	<col style="width: 15%;">						        
		        	<col style="width: 35%;">						        
		        	<col style="width: 15%;">						        
		        	<col style="width: 35%;">
		        </colgroup>
		    	<tr>
		        	<th>계약구분</th>
		            <td>
		            	${myBidResultDetail.CONT_SECD_NM}<c:if test="${myBidResultDetail.UPRC_YN eq 'Y'}">(단가)</c:if>
		            	<c:if test="${myBidResultDetail.CONT_DECD eq '200'}">&nbsp;/&nbsp;${myBidResultDetail.CONT_DECD_NM}</c:if>
		            </td>
		            <c:if test="${myBidResultDetail.SBID_MTCD ne '70' }">
			            <th>공고일자</th>
			            <td>${comFn:formatDate(myBidResultDetail.ANNC_DT,'yyyyMMddHHmmss','yyyy-MM-dd')}</td>
		            </c:if>
		            <c:if test="${myBidResultDetail.SBID_MTCD eq '70' }">
		            	<th></th>
		            	<td></td>
		            </c:if>
		        </tr>
		        <tr>
		            <th>입찰공고명</th>
		            <td colspan="3">
		            	<c:if test="${myBidResultDetail.EMRG_YN eq 'Y'}"><font color="red">[긴급] </font></c:if>
						<c:if test="${myBidResultDetail.ANNC_NGR > 1}"><font color="red">[정정] </font></c:if>
						${myBidResultDetail.BID_NM}
					</td>
		        </tr>
		        <c:if test="${myBidResultDetail.BID_PSCD eq 'PF99' }">
			        <tr>
			        	<th>유찰사유</th>
			        	<td><textarea taView style="display: none;">${pf99Detail.PROC_RSN}</textarea></td>
			        </tr>
		        </c:if>
		    </table>
		</div>
	
		<div class="tit_area">
			<h4 class="tit">예가정보</h4>
		</div>
		<div class="view_area">
			<table>
		    	<colgroup>
		        	<col style="width: 15%;">						        
		        	<col style="width: 35%;">						        
		        	<col style="width: 15%;">						        
		        	<col style="width: 35%;">
		        </colgroup>
		        <tr>
		            <th>예가구분</th>
		            <td colspan="3">${myBidResultDetail.ESTPC_SECD_NM}</td>
		        </tr>
		        <tr>
		            <th>추정금액 (원)</th>
		            <td>${comFn:formatMoney(myBidResultDetail.ESTT_AMT)}</td>
		            <th>추정가격 (원)</th>
		            <td>${comFn:formatMoney(myBidResultDetail.ESTT_PRCE)}</td>
		        </tr>
            	<tr>
                    <th>기초금액 (원)</th>
                    <td>${comFn:formatMoney(myBidResultDetail.BASE_ESTPC_AMT)}</td>
                    <th>예정가격 (원)</th>
                    <td>${comFn:formatMoney(myBidResultDetail.SCH_PRCE_AMT)}</td>
                </tr>
		    </table>
		</div>
		
		<%-- 복수예가이며 개찰 시 활성화 (상태값 미정)--%>
		<c:if test="${(myBidResultDetail.BID_PSCD eq 'PF60' or myBidResultDetail.BID_PSCD eq 'PF61' or myBidResultDetail.BID_PSCD eq 'PF63') && myBidResultDetail.ESTPC_SECD eq '180000' }">
		<div class="tit_area">
			<h4 class="tit">예비가격별 선택현황<font color="red" size="2">*필수입력사항 표시 선택수를 클릭하시면 예가를 선택한 업체를 확인하실 수 있습니다.</font></h4>
		</div>			
		<div class="view_area">
<%-- 					<c:if test="${myBidResultDetail.UPR_8CNT_YN eq 'Y'}"> --%>
<%-- 				    	<c:set var="P_UPR" value="8" /> --%>
<%-- 				    </c:if> --%>
<%-- 				    <c:if test="${myBidResultDetail.UPR_8CNT_YN ne 'Y'}"> --%>
<%-- 				    	<c:set var="P_UPR" value="7" /> --%>
<%-- 				    </c:if> --%>
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
		        <tr style="text-align: center;">
                    <th class="thNoneStyle" style="text-align: center;">${aList[0]}</th>
                    <td class="txtc">
                      	<c:if test="${dList[0] <= P_UPR }">
                        	<font color="red"><b>${comFn:formatMoney(bList[0])}</b></font>
                        </c:if>
                        <c:if test="${dList[0] > P_UPR }">
                        	${comFn:formatMoney(bList[0])}
                        </c:if>
                    </td>
                    <td class="txtc" onclick="bidResultPrdprcChoiseEntrpsInqire('${aList[0]}');" style="cursor: pointer;">${comFn:formatMoney(cList[0])}</td>
                    
                    <th class="thNoneStyle" style="text-align: center;">${aList[1]}</th>
                    <td class="txtc">
                    	
                        <c:if test="${dList[1] <= P_UPR }">
                        	<font color="red"><b>${comFn:formatMoney(bList[1])}</b></font>
                        </c:if>
                        <c:if test="${dList[1] > P_UPR }">
                        	${comFn:formatMoney(bList[1])}
                        </c:if>
                    </td>
                    <td class="txtc" onclick="bidResultPrdprcChoiseEntrpsInqire('${aList[1]}');" style="cursor: pointer;">${comFn:formatMoney(cList[1])}</td>
                    
                    <th class="thNoneStyle" style="text-align: center;">${aList[2]}</th>
                    <td class="txtc">
                        <c:if test="${dList[2] <= P_UPR }">
                        	<font color="red"><b>${comFn:formatMoney(bList[2])}</b></font>
                        </c:if>
                        <c:if test="${dList[2] > P_UPR }">
                        	${comFn:formatMoney(bList[2])}
                        </c:if>
                    </td>
                    <td class="txtc" onclick="bidResultPrdprcChoiseEntrpsInqire('${aList[2]}');" style="cursor: pointer;">${comFn:formatMoney(cList[2])}</td>
                </tr>
                <tr style="text-align: center;">
                    <th class="thNoneStyle" style="text-align: center;">${aList[3]}</th>
                    <td class="txtc">
                        <c:if test="${dList[3] <= P_UPR }">
                        	<font color="red"><b>${comFn:formatMoney(bList[3])}</b></font>
                        </c:if>
                        <c:if test="${dList[3] > P_UPR }">
                        	${comFn:formatMoney(bList[3])}
                        </c:if>
                    </td>
                    <td class="txtc" onclick="bidResultPrdprcChoiseEntrpsInqire('${aList[3]}');" style="cursor: pointer;">${comFn:formatMoney(cList[3])}</td>

                    <th class="thNoneStyle" style="text-align: center;">${aList[4]}</th>
                    <td class="txtc">
                        <c:if test="${dList[4] <= P_UPR }">
                        	<font color="red"><b>${comFn:formatMoney(bList[4])}</b></font>
                        </c:if>
                        <c:if test="${dList[4] > P_UPR }">
                        	${comFn:formatMoney(bList[4])}
                        </c:if>
                    </td>
                    <td class="txtc" onclick="bidResultPrdprcChoiseEntrpsInqire('${aList[4]}');" style="cursor: pointer;">${comFn:formatMoney(cList[4])}</td>
                    
                    <th class="thNoneStyle" style="text-align: center;">${aList[5]}</th>
                    <td class="txtc">
                        <c:if test="${dList[5] <= P_UPR }">
                        	<font color="red"><b>${comFn:formatMoney(bList[5])}</b></font>
                        </c:if>
                        <c:if test="${dList[5] > P_UPR }">
                        	${comFn:formatMoney(bList[5])}
                        </c:if>
                    </td>
                    <td class="txtc" onclick="bidResultPrdprcChoiseEntrpsInqire('${aList[5]}');" style="cursor: pointer;">${comFn:formatMoney(cList[5])}</td>
                </tr>
                <tr style="text-align: center;">
                	<th class="thNoneStyle" style="text-align: center;">${aList[6]}</th>
                    <td class="txtc">
                        <c:if test="${dList[6] <= P_UPR }">
                        	<font color="red"><b>${comFn:formatMoney(bList[6])}</b></font>
                        </c:if>
                        <c:if test="${dList[6] > P_UPR }">
                        	${comFn:formatMoney(bList[6])}
                        </c:if>
                    </td>
                    <td class="txtc" onclick="bidResultPrdprcChoiseEntrpsInqire('${aList[6]}');" style="cursor: pointer;">${comFn:formatMoney(cList[6])}</td>
                    <th class="thNoneStyle" style="text-align: center;">${aList[7]}</th>
                    <td class="txtc">
                        <c:if test="${dList[7] <= P_UPR }">
                        	<font color="red"><b>${comFn:formatMoney(bList[7])}</b></font>
                        </c:if>
                        <c:if test="${dList[7] > P_UPR }">
                        	${comFn:formatMoney(bList[7])}
                        </c:if>
                    </td>
                    <td class="txtc" onclick="bidResultPrdprcChoiseEntrpsInqire('${aList[7]}');" style="cursor: pointer;">${comFn:formatMoney(cList[7])}</td>
                	<th class="thNoneStyle" style="text-align: center;">${aList[8]}</th>
                    <td class="txtc">
                        <c:if test="${dList[8] <= P_UPR }">
                        	<font color="red"><b>${comFn:formatMoney(bList[8])}</b></font>
                        </c:if>
                        <c:if test="${dList[8] > P_UPR }">
                        	${comFn:formatMoney(bList[8])}
                        </c:if>
                    </td>
                    <td class="txtc" onclick="bidResultPrdprcChoiseEntrpsInqire('${aList[8]}');" style="cursor: pointer;">${comFn:formatMoney(cList[8])}</td>
                
                </tr>
                <tr style="text-align: center;">
                    <th class="thNoneStyle" style="text-align: center;">${aList[9]}</th>
                    <td class="txtc">
                        <c:if test="${dList[9] <= P_UPR }">
                        	<font color="red"><b>${comFn:formatMoney(bList[9])}</b></font>
                        </c:if>
                        <c:if test="${dList[9] > P_UPR }">
                        	${comFn:formatMoney(bList[9])}
                        </c:if>
                    </td>
                    <td class="txtc" onclick="bidResultPrdprcChoiseEntrpsInqire('${aList[9]}');" style="cursor: pointer;">${comFn:formatMoney(cList[9])}</td>
                    <th class="thNoneStyle" style="text-align: center;">${aList[10]}</th>
                    <td class="txtc">
                        <c:if test="${dList[10] <= P_UPR }">
                        	<font color="red"><b>${comFn:formatMoney(bList[10])}</b></font>
                        </c:if>
                        <c:if test="${dList[10] > P_UPR }">
                        	${comFn:formatMoney(bList[10])}
                        </c:if>
                    </td>
                    <td class="txtc" onclick="bidResultPrdprcChoiseEntrpsInqire('${aList[10]}');" style="cursor: pointer;">${comFn:formatMoney(cList[10])}</td>
                    <th class="thNoneStyle" style="text-align: center;">${aList[11]}</th>
                    <td class="txtc">
                        <c:if test="${dList[11] <= P_UPR }">
                        	<font color="red"><b>${comFn:formatMoney(bList[11])}</b></font>
                        </c:if>
                        <c:if test="${dList[11] > P_UPR }">
                        	${comFn:formatMoney(bList[11])}
                        </c:if>
                    </td>
                    <td class="txtc" onclick="bidResultPrdprcChoiseEntrpsInqire('${aList[11]}');" style="cursor: pointer;">${comFn:formatMoney(cList[11])}</td>
                </tr>
                <tr style="text-align: center;">
                    <th class="thNoneStyle" style="text-align: center;">${aList[12]}</th>
                    <td class="txtc">
                        <c:if test="${dList[12] <= P_UPR }">
                        	<font color="red"><b>${comFn:formatMoney(bList[12])}</b></font>
                        </c:if>
                        <c:if test="${dList[12] > P_UPR }">
                        	${comFn:formatMoney(bList[12])}
                        </c:if>
                    </td>
                    <td class="txtc" onclick="bidResultPrdprcChoiseEntrpsInqire('${aList[12]}');" style="cursor: pointer;">${comFn:formatMoney(cList[12])}</td>
                    <th class="thNoneStyle" style="text-align: center;">${aList[13]}</th>
                    <td class="txtc">
                        <c:if test="${dList[13] <= P_UPR }">
                        	<font color="red"><b>${comFn:formatMoney(bList[13])}</b></font>
                        </c:if>
                        <c:if test="${dList[13] > P_UPR }">
                        	${comFn:formatMoney(bList[13])}
                        </c:if>
                    </td>
                    <td class="txtc" onclick="bidResultPrdprcChoiseEntrpsInqire('${aList[13]}');" style="cursor: pointer;">${comFn:formatMoney(cList[13])}</td>
                    <th class="thNoneStyle" style="text-align: center;">${aList[14]}</th>
                    <td class="txtc">
                        <c:if test="${dList[14] <= P_UPR }">
                        	<font color="red"><b>${comFn:formatMoney(bList[14])}</b></font>
                        </c:if>
                        <c:if test="${dList[14] > P_UPR }">
                        	${comFn:formatMoney(bList[14])}
                        </c:if>
                    </td>
                    <td class="txtc" onclick="bidResultPrdprcChoiseEntrpsInqire('${aList[14]}');" style="cursor: pointer;">${comFn:formatMoney(cList[14])}</td>
                </tr>
		    </table>
		</div>
		</c:if>
		<%-- 복수예가이며 개찰 시  활성화  END--%>
		
		<c:if test="${myBidResultDetail.BID_PSCD eq 'PF60' or myBidResultDetail.BID_PSCD eq 'PF61' or myBidResultDetail.BID_PSCD eq 'PF63'}">
			<div class="tit_area">
				<h4 class="tit">투찰업체</h4>
			</div>
			<div class="view_area" style="overflow: auto;">
	            <table>
	                <caption>투찰업체</caption>
	                <colgroup>
	                   	<col style="width: *">
	                   	<col style="width: 15%">
	                   	<col style="width: 10%">
	                   	<col style="width: 6%">
	                   	<col style="width: 12%">
	                   	<col style="width: 12%">
	                   	<col style="width: 10%">
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
							<c:forEach var="data" items="${scsbidPrearngerList}" varStatus="status">
								<tr>
									<td class="txtl">${data.VEND_NM}</td>
									<td class="txtc">${comFn:formatBizNumber(data.BIZRNO) }</td>
									<td class="txtc">${data.RPRS_NM}</td>
									<td class="txtc">${data.OPNG_RNK}</td>
									<td class="txtr pr5">${comFn:formatMoney(data.TNDR_AMT)}</td>
									<td class="txtc">${data.BDDPR_RT} %</td>
									<td class="txtc">
										<c:if test="${ myBidResultDetail.BID_PSCD eq 'PF61'}">
											<c:if test="${data.SBID_YN eq 'Y' }">낙찰</c:if>
										</c:if>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					 </c:if> 
	            </table>
		    </div>		
			    
		    <c:if test="${not empty ntElgbVendList}">
			    <h4 class="tit">사전판단 부적격 업체</h4>
				<div class="view_area">
		            <table>
		                <caption>사전판단 부적격 업체</caption>
		                <colgroup>
		                   	<col style="width: auto;">
		                   	<col style="width: 20%;">
		                   	<col style="width: 20%;">
		                   	<col style="width: 30%;">
		                </colgroup>
		                <thead>
			                <tr>
			                    <th class="txtc">업체명</th>
			                    <th class="txtc">사업자번호</th>
			                    <th class="txtc">대표자</th>
			                    <th class="txtc">결격사유</th>
			                </tr>
			            </thead>
		                <c:if test="${empty ntElgbVendList}">
							<tr>
								<td colspan="4" class="txtc">데이터가 존재 하지 않습니다</td>
							</tr>
						</c:if>
						<c:if test="${not empty ntElgbVendList}">
							<tbody>
								<c:forEach var="data" items="${ntElgbVendList}" varStatus="status">
									<tr>
										<td>${data.VEND_NM}</td>
										<td class="txtc">${comFn:formatBizNumber(data.BIZRNO) }</td>
										<td class="txtc">${data.RPRS_NM}</td>
										<td class="txtc">
											<c:if test="${ not empty data.NT_ELGB_RSN }">
												<button type="button" class="btn btn_02 btn_sch pointer" onclick="sucbidrSlctnBrdoResnRegistForm('${data.VEND_REG_NO}');">사유조회</button>
											</c:if>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						 </c:if>
		            </table>
			    </div>
		    </c:if>
	    </c:if>

	    <div class="btn_wrap view_btn">
	    	<button type="button" class="btn btn_m btn_del" id="listBtn">목록</button>
	    </div>
	</div>
</div>

<form id="listFrm" method="POST">
	<input type="hidden" name="resourceName" value="${param.resourceName}">
	<input type="hidden" name="P_ANNC_NO">
	<input type="hidden" name="P_ANNC_NGR">
	<input type="hidden" name="P_ROUND_NO">	
</form>

<form id="popupFrm" method="POST">
	<input type="hidden" name="P_ANNC_NO" value="${myBidResultDetail.ANNC_NO}">
	<input type="hidden" name="P_ANNC_NGR" value="${myBidResultDetail.ANNC_NGR}">
	<input type="hidden" name="P_ROUND_NO" value="${myBidResultDetail.ROUND_NO }">
	<input type="hidden" name="P_PLR_ESTPC_NO">
	<input type="hidden" name="P_VEND_REG_NO">
	<input type="hidden" id="setMulti" name="setMulti">
	<input type="hidden" id="P_SBID_MTCD" value="${myBidResultDetail.SBID_MTCD}">
	<input type="hidden" name="P_BID_SBMT_FSCD">
	<input type="hidden" name="P_BID_TPI_SECD">	
</form>

<form id="bidWrtancDetailPopupFrm" method="POST">
	<input type="hidden" name="P_ANNC_NO" value="${myBidResultDetail.ANNC_NO}">
	<input type="hidden" name="P_ANNC_NGR" value="${myBidResultDetail.ANNC_NGR}">
	<input type="hidden" name="P_ROUND_NO" value="${myBidResultDetail.ROUND_NO}">
</form>