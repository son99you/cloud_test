<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">--%>

<%--
 * 메인> 소액견적 상세 팝업
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %>

<script type="text/javascript" src="${jsPath}/opro/views/main/popup/estiDetailPopup.js"></script>

<div class="pop_wrap">
	<input type="hidden" id="sevrTime" value="${serverTime}">
	<input type="hidden" id="bidcSbmtStdt" value="${inProgrsBidPblancDetail.BIDC_SBMT_STDT}"><%-- 견적서 제출 시작일시 --%>
	<input type="hidden" id="bidcSbmtEndt" value="${inProgrsBidPblancDetail.BIDC_SBMT_ENDT}"><%-- 견적서 제출 종료일시 --%>
	<input type="hidden" id="bidBrfsYn" <c:if test="${inProgrsBidPblancDetail.BID_BRFS_YN eq 'Y' and inProgrsBidPblancDetail.BID_BRFS_ATND_YN eq 'Y' and not empty bidDcPeoAtndncInfo}"> value="Y"</c:if>><%-- 설명회 필수시 설명회참석 여부 --%>
	<input type="hidden" id="usrId" value="${loginResult.USR_ID}">
	<input type="hidden" id="errMsg" value="${msg}">

	<div class="pop_header">
		<h1 class="tit">소액견적</h1>
	</div> <!--// pop_header E -->
	
	<div class="pop_container">
		
		<div class="view_wrap typeC">
		
			<div class="tit_area">
	           	<h4 class="tit">견적개요</h4>
			</div>
				
			<div class="view_area">
				<table>
					<caption>견적개요</caption>
			    	<colgroup>
			        	<col width="15%">
			        	<col width="35%">
			        	<col width="15%">
			        	<col width="35%">
					</colgroup>
			    	<tr>
			    		<th>견적방법</th>
			    		<td>${inProgrsBidPblancDetail.BID_MTCD_NM}</td>
			            <th>계약구분</th>
			            <td>
			            	${inProgrsBidPblancDetail.CONT_SECD_NM}
			            	<c:if test="${not empty inProgrsBidPblancDetail.CONT_DECD_NM}">&nbsp;/&nbsp;${inProgrsBidPblancDetail.CONT_DECD_NM}</c:if>
			            </td>
			        </tr>
			        <tr>
			            <th>견적공고명</th>
			            <td colspan="3">
			            	<c:if test="${inProgrsBidPblancDetail.EMRG_YN eq 'Y'}"><font color="red">[긴급] </font></c:if>
							<c:if test="${inProgrsBidPblancDetail.ANNC_NGR> 1}"><font color="red">[정정] </font></c:if>
	                        ${inProgrsBidPblancDetail.BID_NM}
			            </td>
			        </tr>
			        <tr>
			        	<th scope="row">추정금액 (원)</th>
	                    <td>${comFn:formatMoney(inProgrsBidPblancDetail.ESTT_AMT)}</td>
			        	<th scope="row">추정가격 (원)</th>
	                    <td>${comFn:formatMoney(inProgrsBidPblancDetail.ESTT_PRCE)}</td>
	                </tr>
	                <tr>
	                    <th>SW사업대상</th>
						<td>
							<c:if test="${inProgrsBidPblancDetail.SW_BSNS_OBJ_YN ne 'Y'}">아니오</c:if>
	                    	<c:if test="${inProgrsBidPblancDetail.SW_BSNS_OBJ_YN eq 'Y'}">예</c:if>
						</td>
			            <th>G2B동시게시여부</th>
			            <td>${inProgrsBidPblancDetail.G2B_NTFY_YN_NM}</td>			            
					</tr>
					<tr class="emrgDiv" <c:if test="${inProgrsBidPblancDetail.EMRG_YN ne 'Y'}"> style="display: none;"</c:if>>
			            <th>긴급견적사유</th>
			            <td colspan="3">
	                        ${inProgrsBidPblancDetail.EMRG_BID_RSN}
			            </td>
			        </tr>
			        <tr>
	                    <th>계약기간(TEXT)</th>
	                    <td>${inProgrsBidPblancDetail.CONT_TE}</td>
			        	<th>계약기간</th>
	                    <td>
	                        ${comFn:formatDate(inProgrsBidPblancDetail.CONT_STDE,'yyyyMMdd','yyyy-MM-dd')}
	                        &nbsp;~&nbsp;
	                        ${comFn:formatDate(inProgrsBidPblancDetail.CONT_ENDE,'yyyyMMdd','yyyy-MM-dd')}
	                    </td>
	                </tr>
	            </table>
			</div>
			
			
			<div class="tit_area">
				<h4 class="tit">견적방법</h4>
			</div>
			<div class="view_area">
				<table>
	                <caption>견적방법</caption>
	                <colgroup>
			        	<col width="15%">
			        	<col width="35%">
			        	<col width="15%">
			        	<col width="35%">
	                </colgroup>
	                <tbody>
	                	<tr>
				            <th>계약방법</th>
				            <td>${inProgrsBidPblancDetail.CONT_MTCD_NM}</td>
				            <th>자동유찰여부</th>
				            <td>${inProgrsBidPblancDetail.AUTO_FBID_YN_NM}</td>
				        </tr>
				        <tr>
				        	<th>낙찰자선정방법</th>
				            <td>${inProgrsBidPblancDetail.SBID_MTCD_NM}</td>
							<th>
								<%-- 낙찰자선정방식이 [적격심사]가 아닐 경우 활성화 --%>
								<label  class="elgbDiv" <c:if test="${inProgrsBidPblancDetail.SBID_MTCD ne '31'}">style="display: none;"</c:if>>낙찰하한율</label>
								<%-- 낙찰자선정방식이 [적격심사]가 아닐 경우 활성화 End--%>
								<%-- 낙찰자선정방식이 [협상에의한계약]이 아닐 경우 활성화 --%>
								<label  class="negoDiv" <c:if test="${inProgrsBidPblancDetail.SBID_MTCD ne '40'}">style="display: none;"</c:if>>합산비율</label>
								<%-- 낙찰자선정방식이 [협상에의한계약]이 아닐 경우 활성화 End--%>
							</th>
							<td>
			                    <div class="elgbDiv" <c:if test="${inProgrsBidPblancDetail.SBID_MTCD ne '31'}"> style="display:none;"</c:if>>
				                    <%-- 낙찰자선정방식이 [적격심사]가 아닐 경우 활성화 --%>
			                        ${inProgrsBidPblancDetail.SBID_LWST_RT}
			                    	<%-- 낙찰자선정방식이 [적격심사]가 아닐 경우 활성화 End--%>
			                    </div>
			                    <div class="negoDiv" <c:if test="${inProgrsBidPblancDetail.SBID_MTCD ne '40'}"> style="display:none;"</c:if>>
			                    	<%-- 낙찰자선정방식이 [협상에의한계약]이 아닐 경우 활성화 --%>
		                        	${inProgrsBidPblancDetail.TCHN_SCR_RT }&nbsp;:&nbsp;${inProgrsBidPblancDetail.PRCE_SCR_RT }
									<%-- 낙찰자선정방식이 [협상에의한계약]이 아닐 경우 활성화 End--%>
			                    </div>
		                    </td>
				        </tr>
				        <c:if test="${inProgrsBidPblancDetail.SBID_MTCD eq '31'}">
					        <tr>
					        	<th>적격심사종류</th>
					        	<td>${inProgrsBidPblancDetail.ELGB_ESTM_KDCD_NM}</td>
					        	<th>적격심사통과점수</th>
					        	<td>${inProgrsBidPblancDetail.ELGB_LMT_SCR }</td>
					        </tr>
				        </c:if>
				        <c:if test="${inProgrsBidPblancDetail.SBID_MTCD ne '70'}">
					        <tr>
					        	<th>예가방식</th>
					            <td>${inProgrsBidPblancDetail.ESTPC_SECD_NM}</td>
					        	<th><label <c:if test="${inProgrsBidPblancDetail.ESTPC_SECD eq '180002'}"> style="display:none;"</c:if>>기초금액 (원)</label></th>
					        	<td>
					        		<c:if test="${inProgrsBidPblancDetail.ESTPC_SECD ne '180002'}">
					        			${comFn:formatMoney(inProgrsBidPblancDetail.BASE_AMT)}
					        		</c:if>
					        	</td>
					        </tr>
					        <c:if test="${inProgrsBidPblancDetail.ESTPC_SECD eq '180000'}">
						        <tr>
						        	<th>복수예비가격범위</th>
						            <td colspan="3">
					            		${inProgrsBidPblancDetail.PLR_ESTPC_RNG_CD_NM}
									</td>
						        </tr>
					        </c:if>
					        <tr>
								<th>산정기준&산정방법</th>
								<td colspan="3">
		-복수예가<br>
		기초금액을 근거로 하여 15개의 예비가격을 작성(비공개)한 후 이중 견적 참가자로 하여금 예비가격을 선택하게 하고<br> 
		최다빈도수의 예비가격 산술평균으로 예정가격을 결정하는 방식<br><br>
		-단일예가<br>
		복수예가방식을 사용하지 않고 기초금액을 근거로 하여 담당자가 미리 예정가격을 결정하는 방식
								</td>
							</tr>
					        <tr>    
					            <th>현장설명회여부</th>
					            <td>${inProgrsBidPblancDetail.BID_BRFS_YN_NM}</td>
					            <%-- 현장설명회 [예] 일 경우 활성화 --%>
					            <th><label class="brfsDiv" <c:if test="${inProgrsBidPblancDetail.BID_BRFS_YN ne 'Y'}">style="display:none;"</c:if>>설명회필수참석여부</label></th>
					            <td>
					            	<div class="brfsDiv" <c:if test="${inProgrsBidPblancDetail.BID_BRFS_YN ne 'Y'}">style="display:none;"</c:if>>
					            		${inProgrsBidPblancDetail.BID_BRFS_ATND_YN_NM}
					            	</div>
					            </td>
							</tr>
							<tr class="brfsDiv" <c:if test="${inProgrsBidPblancDetail.BID_BRFS_YN ne 'Y'}">style="display:none;"</c:if>>    
					            <th>현장설명회장소</th> 
			                    <td colspan="3">${inProgrsBidPblancDetail.BRFS_PLC_NM}</td>
							</tr>
						</c:if>
						<%-- 현장설명회 [예] 일 경우 활성화 END--%>
						<%-- 계약구분이 [공사] 일 경우 활성화 --%>
						<tr class="ctrcDiv" <c:if test="${inProgrsBidPblancDetail.CONT_SECD ne '2'}">style="display:none;"</c:if>>    
				            <th>현장설명회여부</th>
				            <td>${inProgrsBidPblancDetail.SITE_BRFS_YN_NM}</td>
				            <%-- 현장설명회 [예] 일 경우 활성화 --%>
				            <th><label  class="ctrcDiv siteBrfsDiv" <c:if test="${inProgrsBidPblancDetail.SITE_BRFS_YN ne 'Y'}">style="display:none;"</c:if>>현장설명회일시</label></th>
				            <td>
				            	<div class="ctrcDiv siteBrfsDiv" <c:if test="${inProgrsBidPblancDetail.SITE_BRFS_YN ne 'Y'}">style="display:none;"</c:if>>
					            	${comFn:formatDate(inProgrsBidPblancDetail.SITE_BRFS_DT,'yyyyMMddHHmmss','yyyy-MM-dd HH:mm')}
		                        </div>
				            </td>
						</tr>
						<tr class="ctrcDiv siteBrfsDiv" <c:if test="${inProgrsBidPblancDetail.CONT_SECD ne '2' or inProgrsBidPblancDetail.SITE_BRFS_YN ne 'Y'}">style="display:none;"</c:if>>    
				            <th>현장설명회장소</th> 
		                    <td colspan="3">${inProgrsBidPblancDetail.SITE_BRFS_PLC}</td>
						</tr>
						<%-- 현장설명회 [예] 일 경우 활성화 END--%>
						<%-- 계약구분이 [공사] 일 경우 활성화 END --%>
						<c:if test="${inProgrsBidPblancDetail.SBID_MTCD ne '70'}">
			                <tr>
			                    <th>견적참가자격</th>
			                    <td colspan="3">
			                        <textarea taView style="display: none;">${inProgrsBidPblancDetail.PRTC_QLF_CNTN}</textarea>
			                    </td>
			                </tr>
			                <tr>
			                	<th>공동수급</th>
			                    <td>${inProgrsBidPblancDetail.ASSO_SPDM_CD_NM}</td>
			                    <th>동가견적옵션</th>
			                    <td>${inProgrsBidPblancDetail.SMPR_BID_SECD_NM}</td>
			                </tr>
		                </c:if>
		                <c:if test="${inProgrsBidPblancDetail.SBID_MTCD eq '70'}">
		                	<tr>
			                	<th>제한횟수</th>
			                    <td>${inProgrsBidPblancDetail.DTAC_TNDR_LMT_CNT}</td>
			                    <th>최소인하비율</th>
			                    <td>${inProgrsBidPblancDetail.MIN_DN_RT}</td>
			                </tr>
			                <tr>
			                    <th>0원견적가능여부</th>
			                    <td colspan="3">
			                    	<c:if test="${inProgrsBidPblancDetail.ZERO_BID_PSBL_YN eq 'Y'}">예</c:if>
			                    	<c:if test="${inProgrsBidPblancDetail.ZERO_BID_PSBL_YN ne 'Y'}">아니오</c:if>
			                    </td>
			                </tr>
		                </c:if>
	                </tbody>
	            </table>
	        </div>
        
	        <%-- 정정공고 사유가 등록되어 있을 경우 활성화 --%>
	        <c:if test="${not empty pblancNtcnInfoList}">
				<div class="tit_area">
					<h4 class="tit">정정공고</h4>
				</div>
				<div class="view_area">
					<table>
		                <caption>정정공고</caption>
		                <colgroup>
			                <col width="15%">
			                <col width="85%">
			            </colgroup>
						<thead>
			                <tr>
			                    <th scope="col">등록일자</th>
			                    <th scope="col">정정공고사유</th>
			                </tr>
			            </thead>
						<tbody>
							<c:forEach var="data" items="${pblancNtcnInfoList}" varStatus="status">
								<c:if test="${status.count eq 1 }">
									<tr class="row">
										<td>${comFn:formatDate(data.PROC_DT,'yyyyMMddHHmmss','yyyy-MM-dd')}</td>
										<td class="left_T">
											<label for="P_NTCN_RESN_CN" class="blind">정정공고사유</label>
											<textarea id="P_NTCN_RESN_CN" rows="5" cols="137" readonly taView style="display: none;"><c:out value="${data.PROC_RSN}"></c:out></textarea>
										</td>
									</tr>
								</c:if>
							</c:forEach>
						</tbody>
		            </table>
		        </div>
	        </c:if>
	        
	        
	        <%-- 계약방법이 [제한경쟁] 일 경우 활성화 --%>
	        <c:if test="${inProgrsBidPblancDetail.CONT_MTCD eq '10002'}">
				<div class="tit_area">
					<h4 class="tit">투찰제한</h4>
				</div>
				<div class="view_area">
					<table>
						<caption>투찰제한</caption>
				    	<colgroup>
				        	<col width="15%">
				        	<col width="35%">
				        	<col width="15%">
				        	<col width="35%">
				        </colgroup>
				        <tr>
				            <th>지역제한</th>
				            <td>${inProgrsBidPblancDetail.ARA_LMT_CD_NM}</td>
				            <th>업종제한</th>
				            <td>${inProgrsBidPblancDetail.BTP_LMT_CD_NM}</td>
				        </tr>
					</table>
				</div>
			</c:if>
	        <%-- 계약방법이 [제한경쟁] 일 경우 활성화 End --%>
	        
	        <c:if test="${inProgrsBidPblancDetail.SBID_MTCD ne '70'}">
				<div class="tit_area">
					<h4 class="tit">계약조건</h4>
				</div>
				<div class="view_area">
					<table>
		                <caption>계약조건</caption>
		                <colgroup>
				        	<col width="15%">
				        	<col width="35%">
				        	<col width="15%">
				        	<col width="35%">
		                </colgroup>
		                <tbody>
		                	<tr>
			                    <th>지체상금률 (%)</th>
			                    <td>${inProgrsBidPblancDetail.CPDF_RT} /1,000</td>
			                    <th>계약보증금률 (%)</th>
			                    <td>${inProgrsBidPblancDetail.CTFL_GTAMT_RT}</td>
			                </tr>
			                <tr>
			                    <th>하자이행보증금률 (%)</th>
			                    <td>${inProgrsBidPblancDetail.DFFL_GTAMT_RT}</td>
			                    <%-- 계약구분이 [구매] 일 경우 활성화 --%>
			                    <th><label  class="buyDiv" <c:if test="${inProgrsBidPblancDetail.CONT_SECD ne '0'}"> style="display: none;"</c:if>>물품인도조건</label></th>
			                    <td>
			                    	<div class="buyDiv" <c:if test="${inProgrsBidPblancDetail.CONT_SECD ne '0'}">style="display: none;"</c:if>>
				            			${inProgrsBidPblancDetail.ITEM_DRCD_NM}
				            		</div>
			                    </td>
			                </tr>
			                <tr class="buyDiv" <c:if test="${inProgrsBidPblancDetail.CONT_SECD ne '0'}"> style="display: none;"</c:if>>
			                    <th>납품장소</th>
			                    <td colspan="3">
			                        <textarea taView style="display: none;">${inProgrsBidPblancDetail.DLGD_PLC_NM}</textarea>
			                    </td>
			                </tr>
			                <tr class="buyDiv" <c:if test="${inProgrsBidPblancDetail.CONT_SECD ne '0'}"> style="display: none;"</c:if>>
			                    <th>납품기한</th>
			                    <td colspan="3">
			                    	<textarea taView style="display: none;">${inProgrsBidPblancDetail.DLGD_TE_CNTN}</textarea>
			                    </td>
			                </tr>
			                <tr class="buyDiv" <c:if test="${inProgrsBidPblancDetail.CONT_SECD ne '0'}"> style="display: none;"</c:if>>
			                    <th>설치기한</th>
			                    <td colspan="3">
			                    	<textarea taView style="display: none;">${inProgrsBidPblancDetail.ISTL_LMT}</textarea>
			                    </td>
			                </tr>
			                <%-- 계약구분이 [구매] 일 경우 활성화 END --%>
		                </tbody>
		            </table>
		        </div>
	        </c:if>
	        
			<div class="tit_area">
				<h4 class="tit">견적진행순서</h4>
			</div>
			<div class="view_area">
				<table>
	                <caption>견적진행순서</caption>
	                <colgroup>
	                    <col width="15%">
	                    <col width="85%">
	                </colgroup>
	                <tbody>
	                	<tr>
		                    <th>견적공고게시일시</th>
		                    <td>${comFn:formatDate(inProgrsBidPblancDetail.ANNC_DT,'yyyyMMddHHmmss','yyyy-MM-dd HH:mm')}</td>
		                </tr>
		                <%-- 현장설명회 여부가 [예] 일 경우에 활성화 --%>
		                <tr class="brfsDiv" <c:if test="${inProgrsBidPblancDetail.BID_BRFS_YN ne 'Y'}"> style="display:none;"</c:if>>
		                    <th>현장설명회일시</th>
		                    <td>${comFn:formatDate(inProgrsBidPblancDetail.BRFS_DT,'yyyyMMddHHmmss','yyyy-MM-dd HH:mm')}</td>
		                </tr>
		                <%-- 현장설명회 여부가 [예] 일 경우에 활성화 End --%>
		                <tr>
		                    <th>견적서제출기간</th>
		                    <td>
		                    	${comFn:formatDate(inProgrsBidPblancDetail.BIDC_SBMT_STDT,'yyyyMMddHHmmss','yyyy-MM-dd HH:mm')}
		                        &nbsp;~&nbsp;
		                        ${comFn:formatDate(inProgrsBidPblancDetail.BIDC_SBMT_ENDT,'yyyyMMddHHmmss','yyyy-MM-dd HH:mm')}
		                    </td>
		                </tr>
		                <tr>
		                    <th>개찰일시</th>
		                    <td>${comFn:formatDate(inProgrsBidPblancDetail.OPNG_DT,'yyyyMMddHHmmss','yyyy-MM-dd HH:mm')}</td>
		                </tr>
		                <c:if test="${inProgrsBidPblancDetail.ANNC_SECD eq 'HNDW_BID' }">
			                <tr>
			                    <th>개찰장소</th>
			                    <td>${inProgrsBidPblancDetail.OPNG_PLC_NM }</td>
							</tr>
						</c:if>
	                </tbody>
	            </table>
	        </div>
	        
			<div class="buyDiv">
	        	<div class="tit_area">
			       	<h4 class="tit" style="clear: both;">견적품목</h4>
		        </div>
				<div class="view_area" style="margin-bottom: 30px; overflow: auto;">
		            <table style="width:100%;">
		                <caption>견적품목정보</caption>
		                <colgroup> 
							<col style="width: 12%;">
		                   	<col style="width: 12%;">
		                   	<col style="width: auto;"> 
		                   	<col style="width: 13%;">
		                   	<col style="width: 8%;">
		                   	<col style="width: 8%;">
		                   	<col style="width: 10%;">
		                   	<col style="width: 10%;">
		                </colgroup>
		                <thead>
			                <tr>
			                    <th scope="col" style="text-align: center;">구매요구번호</th>
			                    <th scope="col" style="text-align: center;">품목번호</th>
			                    <th scope="col" style="text-align: center;">품명</th>
			                    <th scope="col" style="text-align: center;">규격</th>
			                    <th scope="col" style="text-align: center;">단위</th>
			                    <th scope="col" style="text-align: center;">수량</th>
			                    <th scope="col" style="text-align: center;">추정단가<br/>(VAT포함)</th>
			                    <th scope="col" style="text-align: center;">추정금액<br/>(수량*단가)</th>
			                </tr>
		                </thead>
		                <tbody id="biprInfoShowTbdy">
		                	<c:forEach var="data" items="${bidPrdlsList}" varStatus="status">
								<tr class="row">
									<td class="txtc">${data.PCRQ_NO}</td>
									<td class="txtc">${data.ITEM_NO}</td>
									<td>${data.ITEM_NM}</td>
									<td>${data.STND_NM}</td>
									<td class="txtc">${data.ITEM_UNNM}</td>
									<td class="txtr pr5">${data.ITEM_QTY}</td>
									<td class="txtr pr5">${comFn:formatMoney(data.RQST_UNIT)}</td>
									<td class="txtr pr5">${comFn:formatMoney(data.SCH_UNIT)}</td>
								</tr>
							</c:forEach>
		                </tbody>
		                <tbody>
							<tr class="row" id="biprInfoEmpty" <c:if test="${ not empty bidPrdlsList}"> style="display:none;"</c:if>>
			               		<td colspan="8" style="text-align: center;">견적품목이 없습니다.</td>
			               	</tr>
		               	</tbody>
		            </table>
		        </div>
	        </div>
			
			<div class="tit_area">
				<h4 class="tit">연구원첨부파일</h4>
			</div>
	           <div class="view_area fileViewer">
				<!-- 업로드 삽입. -->
				<script type="text/javascript">
					DEXT5UPLOAD.config.Mode = 'view';
					DEXT5UPLOAD.config.Width = '100%';
					DEXT5UPLOAD.config.FolderNameRule = '/bid';
					var dext5Upload = new Dext5Upload("upload");
				</script>	            	
			</div>	
			<div id="upload_fileInfo"></div>
			
			<div class="btn_wrap view_btn">
				<button type="button" class="btn btn_m btn_del" onclick="javascript:window.close();">닫기</button>
			</div> <!--// btn_wrap E -->
			
		</div>
	</div> 
</div>

<%-- 파일 VIEW 폼 --%>
<form id="fileViewFrm" method="POST">
	<input type="hidden" id="P_FILE_GRP_NO" name="P_FILE_GRP_NO" value="${inProgrsBidPblancDetail.FILE_GRP_NO}">
</form>