<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 입찰관리 > 입찰결과조회 상세
 *
 * <pre>
 * ebid 
 *    |_ bidResultDetail.jsp
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
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 

<script type="text/javascript" src="${jsPath}/ipro/views/ebid/bidResultDetail.js"></script> 
 
<div class="contents_wrap">
	<ul class="step_wrap">
		<li><a href="#">${myMenuList.bigMenuNm}</a></li>
		<li><a href="#">${myMenuSubList.smallMenuNm}</a></li>
	</ul>
	<div class="tit_wrap">
		<h3 class="tit">입찰결과조회 상세</h3>
	</div>
	
	<div class="view_wrap typeB">
		<input type="hidden" id="P_TCHN_SCR_RT" value="${bidInfoDetail.TCHN_SCR_RT }">
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
		        <tr class="line">
					<th>사전공고번호</th>
					<td>
						<c:if test="${empty bidInfoDetail.BFAN_NO_LIST}">사전공고번호가 없습니다.</c:if>
						<c:if test="${not empty bidInfoDetail.BFAN_NO_LIST}">${bidInfoDetail.BFAN_NO_LIST }</c:if>
					</td>
					<th>공고번호</th>
					<td>${bidInfoDetail.ANNC_NO}-${bidInfoDetail.ANNC_NGR}</td>
	            </tr>
	            <tr>
		            <th>공고명</th>
		            <td colspan="3">
		            	<c:if test="${bidInfoDetail.EMRG_YN eq 'Y'}"><font color="red">[긴급] </font></c:if>
						<c:if test="${bidInfoDetail.ANNC_NGR > 1}"><font color="red">[정정] </font></c:if>
						${bidInfoDetail.BID_NM}
					</td>
		        </tr>
		    	<tr>
		        	<th>계약구분</th>
		            <td>
		            	${bidInfoDetail.CONT_SECD_NM}<c:if test="${bidInfoDetail.UPRC_YN eq 'Y'}">(단가)</c:if>
		            	<c:if test="${bidInfoDetail.CONT_DECD eq '200'}">&nbsp;/&nbsp;${bidInfoDetail.CONT_DECD_NM}</c:if>
		            </td>
		            <c:if test="${bidInfoDetail.SBID_MTCD ne '70' }">
		            <th>공고일자</th>
		            <td>${comFn:formatDate(bidInfoDetail.ANNC_DT,'yyyyMMddHHmmss','yyyy-MM-dd')}</td>
		            </c:if>
		            <c:if test="${bidInfoDetail.SBID_MTCD eq '70' }">
		            	<th></th>
		            	<td></td>
		            </c:if>
		        </tr>
		        <c:if test="${bidInfoDetail.BID_PSCD eq 'PF99' }">
			        <tr>
			        	<th>유찰사유</th>
			        	<td><textarea taView style="display: none;">${procRsnDetail.PROC_RSN}</textarea></td>
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
		            <td colspan="3">${bidInfoDetail.ESTPC_SECD_NM}</td>
		        </tr>
		        <tr>
		            <th>추정금액 (원)</th>
		            <td>${comFn:formatMoney(bidInfoDetail.ESTT_AMT)}</td>
		            <th>추정가격 (원)</th>
		            <td>${comFn:formatMoney(bidInfoDetail.ESTT_PRCE)}</td>
		        </tr>
            	<tr>
                    <th>기초금액 (원)</th>
                    <td>${comFn:formatMoney(bidInfoDetail.BASE_ESTPC_AMT)}</td>
                    <th>예정가격 (원)</th>
                    <td>${comFn:formatMoney(bidInfoDetail.SCH_PRCE_AMT)}</td>
                </tr>
		    </table>
		</div>
		
		<%-- 복수예가이며 개찰 시 활성화 (상태값 미정)--%>
		<c:if test="${(bidInfoDetail.BID_PSCD eq 'PF60' or bidInfoDetail.BID_PSCD eq 'PF61' or bidInfoDetail.BID_PSCD eq 'PF63') && bidInfoDetail.ESTPC_SECD eq '180000' }">
		<div class="tit_area">
			<h4 class="tit">예비가격별 선택현황<font color="red" size="2">*필수입력사항 표시 선택수를 클릭하시면 예가를 선택한 업체를 확인하실 수 있습니다.</font></h4>
		</div>			
		<div class="view_area">
<%-- 					<c:if test="${bidInfoDetail.UPR_8CNT_YN eq 'Y'}"> --%>
<%-- 				    	<c:set var="P_UPR" value="8" /> --%>
<%-- 				    </c:if> --%>
<%-- 				    <c:if test="${bidInfoDetail.UPR_8CNT_YN ne 'Y'}"> --%>
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
		
		<c:if test="${bidInfoDetail.BID_PSCD eq 'PF99'}">
			<div class="tit_area">
				<h4 class="tit">투찰업체</h4>
			</div>
			<div class="view_area">
	            <table>
	                <caption>투찰업체</caption>
	                <colgroup>
	                   	<col style="width: 75px;"><!-- 업체명 -->
	                   	<col style="width: 60px;"><!-- 사업자번호 -->
	                   	<col style="width: 40px;"><!-- 담당자 -->
	                   	<col style="width: 60px;"><!-- 전화번호 -->
	                   	<col style="width: 80px;"><!-- 이메일 -->
	                </colgroup>
	                <thead>
		                <tr>
		                    <th class="txtc">업체명</th>
		                    <th class="txtc">사업자번호</th>
		                    <th class="txtc">담당자</th>
		                    <th class="txtc">전화번호</th>
		                    <th class="txtc">이메일</th>
		                </tr>
		            </thead>
	                <c:if test="${empty fibScsbidPrearngerList}">
						<tr>
							<td colspan="5" class="txtc">데이터가 존재 하지 않습니다</td>
						</tr>
					</c:if>
					<c:if test="${not empty fibScsbidPrearngerList}">
						<tbody>
							<c:forEach var="data" items="${fibScsbidPrearngerList}" varStatus="status">
								<tr>
									<td class="txtl">${data.VEND_NM}</td><!-- 업체명 -->
									<td class="txtc">${comFn:formatBizNumber(data.BIZRNO) }</td><!-- 사업자번호 -->
									<td class="txtc">${data.CHRGR_NM}</td><!-- 담당자명 -->
									<td class="txtc">${data.TEL_NO}</td><!-- 전화번호 -->
									<td class="txtc">${data.EMAL_ADDR}</td><!-- 이메일 -->
								</tr>
							</c:forEach>
						</tbody>
					 </c:if>
	            </table>
		    </div>			
		</c:if>
		
		<c:if test="${bidInfoDetail.BID_PSCD eq 'PF60' or bidInfoDetail.BID_PSCD eq 'PF61' or bidInfoDetail.BID_PSCD eq 'PF63'}">
			<div class="tit_area">
				<h4 class="tit">투찰업체</h4>
			</div>
			<div class="view_area" style="overflow: auto;">
	            <table style="width: 150%;">
	                <caption>투찰업체</caption>
	                <colgroup>
	                	<col style="width: 40px;"><!-- 낙찰여부 -->
	                   	<col style="width: 75px;"><!-- 업체명 -->
	                   	<col style="width: 60px;"><!-- 사업자번호 -->
	                   	<col style="width: 50px;"><!-- 대표자 -->
	                   	<col style="width: 40px;"><!-- 순위 -->
	                   	<col style="width: 60px;"><!-- 투찰금액 -->
	                   	<col style="width: 50px;"><!-- 투찰률 -->
	                   	<col style="width: 40px;"><!-- 참가검토 -->
	                   	<c:if test="${bidInfoDetail.SBID_MTCD eq '40'}">
	                   		<col style="width: 40px;">
	                   		<col style="width: 40px;">
	                   		<col style="width: 40px;">
	                   		<col style="width: 60px;">
	                   	</c:if>
	                   	<c:if test="${bidInfoDetail.SBID_MTCD eq '31'}">
	              	     	<col style="width: 60px;"><!-- 심사결과 -->
	                   	</c:if>
	                   	<c:if test="${bidInfoDetail.SBID_MTCD eq '33' or bidInfoDetail.SBID_MTCD eq '34'}">
	              	     	<col style="width: 60px;"><!-- 평가결과 -->
	                   	</c:if>
	                   	<col style="width: 60px;"><!-- 입찰참가서류 -->
	                   	<col style="width: 60px;"><!-- 입찰보증정보 -->
	                   	<col style="width: 60px;"><!-- 산출내역서 -->
	                   	<c:if test="${ (bidInfoDetail.SBID_MTCD eq '33') or (bidInfoDetail.SBID_MTCD eq '34') or (bidInfoDetail.SBID_MTCD eq '40') }">
		                   	<col style="width: 60px;"><!-- 제안서/규격서 -->
	                   	</c:if>
	                   	<c:if test="${bidInfoDetail.ASSO_SPDM_CD ne '240000'}">
	                   		<col style="width: 60px;"><!-- 공동협정업체 -->
	                   	</c:if>
	                   	<col style="width: 40px;"><!-- 담당자 -->
	                   	<col style="width: 60px;"><!-- 전화번호 -->
	                   	<col style="width: 80px;"><!-- 이메일 -->
	                </colgroup>
	                <thead>
		                <tr>
		                	<th class="txtc">낙찰여부</th>
		                    <th class="txtc">업체명</th>
		                    <th class="txtc">사업자번호</th>
		                    <th class="txtc">대표자</th>
		                    <th class="txtc">순위</th>
		                    <th class="txtc">투찰금액</th>
		                    <th class="txtc">투찰률</th>
		                    <th class="txtc">참가검토</th>
		                   	<c:if test="${bidInfoDetail.SBID_MTCD eq '40'}">
		                   		<th class="txtc">평가결과</th>	
		                   		<th class="txtc">기술점수</th>	
		                   		<th class="txtc">가격점수</th>	
		                   		<th class="txtc">협상결과</th>
		                   	</c:if>
		                   	<c:if test="${bidInfoDetail.SBID_MTCD eq '31'}">
		            	        <th class="txtc">심사결과</th>
		                    </c:if>
		                   	<c:if test="${bidInfoDetail.SBID_MTCD eq '33' or bidInfoDetail.SBID_MTCD eq '34'}">
		            	        <th class="txtc">평가결과</th>
		                    </c:if>
		                    <%-- <c:if test="${bidInfoDetail.SBID_MTCD ne '31' && bidInfoDetail.SBID_MTCD ne '40' && bidInfoDetail.SBID_MTCD ne '33' && bidInfoDetail.SBID_MTCD ne '34'}">
		            	        <th class="txtc">결격사유</th>
		                    </c:if> --%>
				            <th class="txtc">입찰참가서류</th>
				            <th class="txtc">입찰보증정보</th>
		                    <th class="txtc">산출내역서</th>
		                    <c:if test="${ (bidInfoDetail.SBID_MTCD eq '33') or (bidInfoDetail.SBID_MTCD eq '34') or (bidInfoDetail.SBID_MTCD eq '40') }">
			                    <th class="txtc">제안서/규격서</th>
		                    </c:if>
		                    <c:if test="${bidInfoDetail.ASSO_SPDM_CD ne '240000'}">
		                   		<th class="txtc">공동협정업체</th>
		                    </c:if>
		                    <th class="txtc">담당자</th>
		                    <th class="txtc">전화번호</th>
		                    <th class="txtc">이메일</th>
		                </tr>
		            </thead>
	                <c:if test="${empty scsbidPrearngerList}">
						<tr>
							<c:if test="${bidInfoDetail.SBID_MTCD eq '31'}">
								<td colspan="8" class="txtc">데이터가 존재 하지 않습니다</td>
							</c:if>
							<c:if test="${bidInfoDetail.SBID_MTCD eq '40'}">
								<td colspan="10" class="txtc">데이터가 존재 하지 않습니다</td>
							</c:if>
							<c:if test="${bidInfoDetail.SBID_MTCD ne '31' and bidInfoDetail.SBID_MTCD ne '40'}">
								<td colspan="14" class="txtc">데이터가 존재 하지 않습니다</td>
							</c:if>
						</tr>
					</c:if>
					<c:if test="${not empty scsbidPrearngerList}">
						<tbody id="lwetScsbidPrearngerListJson">
							<c:forEach var="data" items="${scsbidPrearngerList}" varStatus="status">
								<tr>
									<td class="txtc"><!-- 낙찰여부 -->
										<c:if test="${ bidInfoDetail.BID_PSCD eq 'PF61'}">
											<c:if test="${data.SBID_YN eq 'Y' }">낙찰</c:if>
										</c:if>
									</td>
									<td class="txtl">${data.VEND_NM}</td><!-- 업체명 -->
									<td class="txtc">${comFn:formatBizNumber(data.BIZRNO) }</td><!-- 사업자번호 -->
									<td class="txtc">${data.RPRS_NM}</td><!-- 대표자명 -->
									<td class="txtc">${data.OPNG_RNK}</td><!-- 순위 -->
									<td class="txtr pr5"><!-- 투찰금액 -->
										<button type="button" class="btn btn_m btn_sch" onclick="tndrItemAmtList('${data.VEND_REG_NO}');">${comFn:formatMoney(data.TNDR_AMT)}</button>
									</td>
									<td class="txtc">${data.BDDPR_RT} %</td><!-- 투찰률 -->
									<td class="txtc"><button type="button" class="btn btn_s btn_sch" onclick="partDetail('${data.VEND_REG_NO}');">${data.BID_ELCD_NM }</button></td><!-- 참가검토 -->
									<c:if test="${bidInfoDetail.SBID_MTCD eq '40'}">
										<td class="txtc">
											<button type="button" class="btn btn_s btn_sch" onclick="dfDcdDetail('${data.VEND_REG_NO}');">${data.ESTM_ELCD_NM}</button>
										</td>
										<td class="txtc">
											<input type="hidden" name="P_ESTM_SCR" value="${data.ESTM_SCR }">
											<fmt:formatNumber var="ESTM_SCR_FMT" pattern="#.##" value="${data.ESTM_SCR * bidInfoDetail.TCHN_SCR_RT}" />
											<fmt:formatNumber var="ESTM_SCR_SUM" pattern="#.##" value="${ESTM_SCR_FMT/10}" />
											<%-- ${ESTM_SCR_SUM} --%>
											<span id="ESTM_SCR_SUM${status.count}"></span>
										</td>
										<td class="txtc">${data.PRCE_SCR}</td>
										<td class="txtc">
											<c:if test="${data.BID_VEND_PSCD eq 'OP06' && bidInfoDetail.BID_PSCD ne 'PF60'}">
												<c:if test="${bidInfoDetail.REGR_ID eq loginResult.USR_ID }">
													<button type="button" class="btn btn_s btn_sch pointer" onclick="negoRsltDetail('${data.VEND_REG_NO}');">미등록</button>
												</c:if>
											</c:if>
											<c:if test="${data.BID_VEND_PSCD eq 'OP08' || data.BID_VEND_PSCD eq 'OP09'}">
												<button type="button" class="btn btn_m btn_sch pointer" onclick="negoRsltDetail('${data.VEND_REG_NO}');">협상등록</button>
											</c:if>
											<c:if test="${data.BID_VEND_PSCD eq 'OP15'}">
												부적격
											</c:if>
										</td>
									</c:if>
									<c:if test="${bidInfoDetail.SBID_MTCD ne '40'}">
										<c:if test="${bidInfoDetail.SBID_MTCD eq '31' or bidInfoDetail.SBID_MTCD eq '33' or bidInfoDetail.SBID_MTCD eq '34'}">
											<td class="txtc">
												<c:if test="${empty data.ESTM_ELCD}">
													<button type="button" class="btn btn_s btn_sch" onclick="dfDcdRegist('${data.VEND_REG_NO}');">검토전</button>
												</c:if>
												<c:if test="${not empty data.ESTM_ELCD}">
													<button type="button" class="btn btn_s btn_sch" onclick="dfDcdDetail('${data.VEND_REG_NO}');">${data.ESTM_ELCD_NM}</button>
												</c:if>
											</td>
										</c:if>
										<c:if test="${bidInfoDetail.SBID_MTCD ne '31' && bidInfoDetail.SBID_MTCD eq '33' && bidInfoDetail.SBID_MTCD eq '34'}">
											<td class="txtc">
												<c:if test="${ not empty data.OPNG_RSN }">
													${data.OPNG_RSN }
												</c:if>
												<c:if test="${ empty data.OPNG_RSN && bidInfoDetail.BID_PSCD ne 'PF60'}">
													<c:if test="${ empty data.NT_ELGB_RSN }">
														<input type="hidden" name="P_RNK" value="${data.OPNG_RNK}">
														<c:if test="${bidInfoDetail.REGR_ID eq loginResult.USR_ID }">
															<button type="button" class="btn btn_auto btn_sch" onclick="sucbidrSlctnBrdoResnRegistForm('${data.VEND_REG_NO}');">사유등록</button>
														</c:if>
													</c:if>
													<c:if test="${ not empty data.NT_ELGB_RSN }">
														<c:if test="${bidInfoDetail.REGR_ID eq loginResult.USR_ID }">
															<button type="button" class="btn btn_auto btn_sch" onclick="sucbidrSlctnBrdoResnRegistForm('${data.VEND_REG_NO}');">사유조회</button>
														</c:if>
													</c:if>
												</c:if>
											</td>
										</c:if>
									</c:if>
									<td class="txtc"><!-- 입찰참가서류 -->
										<button type="button" class="btn btn_s btn_sch" onclick="bidPartcptReqstdocInqire('${data.VEND_REG_NO}');">보기</button>
									</td>
									<td class="txtc"><!-- 입찰보증정보 -->
										<button type="button" class="btn btn_s btn_sch" onclick="bidAssrncInfoDetail('${data.VEND_REG_NO}');">보기</button>
									</td>	
									<td class="txtc"><!-- 산출내역서 -->
										<button type="button" class="btn btn_m btn_sch" onclick="vendTndrDocPopup('${data.VEND_REG_NO}');">내역서</button>
									</td>
									<c:if test="${ (bidInfoDetail.SBID_MTCD eq '33') or (bidInfoDetail.SBID_MTCD eq '34') or (bidInfoDetail.SBID_MTCD eq '40') }">
										<td class="txtc"><!-- 제안서/규격서 -->
											<button type="button" class="btn btn_s btn_sch" onclick="bidPartcptPrprcDocInqire('${data.VEND_REG_NO}');">보기</button>
										</td>
									</c:if>
									<c:if test="${bidInfoDetail.ASSO_SPDM_CD ne '240000'}">
										<td class="txtc"><!-- 공동협정업체 -->
											<button type="button" class="btn btn_s btn_sch" onclick="bidAssoInfoInqire('${data.VEND_REG_NO}');">보기</button>
										</td>
									</c:if>
									<td class="txtc">${data.CHRGR_NM}</td><!-- 담당자명 -->
									<td class="txtc">${data.TEL_NO}</td><!-- 전화번호 -->
									<td class="txtc">${data.EMAL_ADDR}</td><!-- 이메일 -->
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
	    	<button type="button" class="btn btn_m btn_orange" id="rountInfoBtn">라운드정보</button>
	    	<c:if test="${bidInfoDetail.BID_PSCD eq 'PF99' }">
	    		<c:if test="${empty bidInfoDetail.BF_ANNC_NO}">
		    		<button type="button" class="btn btn_m btn_orange" id="reAnnoBtn">재공고</button>
		    	</c:if>
	    	</c:if>
	    	<c:if test="${bidInfoDetail.BID_PSCD eq 'PF41' }">
	    		<button type="button" class="btn btn_m btn_orange" id="reAnnoBtn">재공고</button>
	    	</c:if>
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
	<input type="hidden" name="P_ANNC_NO" value="${bidInfoDetail.ANNC_NO}">
	<input type="hidden" name="P_ANNC_NGR" value="${bidInfoDetail.ANNC_NGR}">
	<input type="hidden" name="P_ROUND_NO" value="${bidInfoDetail.ROUND_NO }">
	<input type="hidden" name="P_PLR_ESTPC_NO">
	<input type="hidden" name="P_VEND_REG_NO">
	<input type="hidden" id="setMulti" name="setMulti">
	<input type="hidden" id="P_SBID_MTCD" value="${bidInfoDetail.SBID_MTCD}">
	<input type="hidden" name="P_BID_SBMT_FSCD">
	<input type="hidden" name="P_BID_TPI_SECD">	
</form>

<form id="reBidFrm" method="POST">
	<input type="hidden" name="resourceName" value="${param.resourceName}">
	<input type="hidden" name="P_ANNC_NO" value="${bidInfoDetail.ANNC_NO}">
	<input type="hidden" name="P_ANNC_NGR" value="${bidInfoDetail.ANNC_NGR}">
	<input type="hidden" name="P_ROUND_NO" value="${bidInfoDetail.ROUND_NO }">
	<input type="hidden" name="P_BID_PSCD">
	<input type="hidden" name="P_APPR_STCD">
</form>

<form id="fibFrm" method="POST">
   	<input type="hidden" name="P_ANNC_NO" value="${bidInfoDetail.ANNC_NO}">
   	<input type="hidden" name="P_ANNC_NGR" value="${bidInfoDetail.ANNC_NGR}">
   	<input type="hidden" name="P_ROUND_NO" value="${bidInfoDetail.ROUND_NO}">
</form>

<form id="bidWrtancDetailPopupFrm" method="POST">
	<input type="hidden" name="P_ANNC_NO" value="${bidInfoDetail.ANNC_NO}">
	<input type="hidden" name="P_ANNC_NGR" value="${bidInfoDetail.ANNC_NGR}">
	<input type="hidden" name="P_ROUND_NO" value="${bidInfoDetail.ROUND_NO}">
</form>