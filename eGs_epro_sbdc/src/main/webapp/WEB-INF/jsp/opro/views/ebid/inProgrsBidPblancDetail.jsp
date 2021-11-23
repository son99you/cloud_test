<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 입찰관리 > 입찰진행현황 상세
 *
 * <pre>
 * ebid 
 *    |_ inProgrsBidPblancDetail.jsp
 * 
 * </pre>
 * @date : 2017. 06.19
 * @version : 1.0
 * @author : 은우소프트 이주연 
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 

<script type="text/javascript" src="${jsPath}/opro/views/ebid/inProgrsBidPblancDetail.js"></script> 

<input type="hidden" id="sevrTime" value="${serverTime}">
<input type="hidden" id="bidcSbmtStdt" value="${inProgrsBidPblancDetail.BIDC_SBMT_STDT}"><%-- 입찰서 제출 시작일시 --%>
<input type="hidden" id="bidcSbmtEndt" value="${inProgrsBidPblancDetail.BIDC_SBMT_ENDT}"><%-- 입찰서 제출 종료일시 --%>
<input type="hidden" id="prpdcSbmtStdt" value="${inProgrsBidPblancDetail.PRPDC_SBMT_STDT}"><%-- 제안서 제출 시작일시 --%>
<input type="hidden" id="prpdcSbmtEndt" value="${inProgrsBidPblancDetail.PRPDC_SBMT_ENDT}"><%-- 제안서 제출 종료일시 --%>
<input type="hidden" id="bidBrfsYn" <c:if test="${inProgrsBidPblancDetail.BID_BRFS_YN eq 'Y' and inProgrsBidPblancDetail.BID_BRFS_ATND_YN eq 'Y' and not empty bidDcPeoAtndncInfo}"> value="Y"</c:if>><%-- 설명회 필수시 설명회참석 여부 --%>
<input type="hidden" id="usrId" value="${loginResult.USR_ID}">
<input type="hidden" id="errMsg" value="${msg}">

<div class="contents_wrap">
	<ul class="step_wrap">
		<li><a href="#">${myMenuList.bigMenuNm}</a></li>
		<li><a href="#">${myMenuSubList.smallMenuNm}</a></li>
	</ul>
	<div class="tit_wrap">
		<h3 class="tit">입찰진행현황 상세</h3>
	</div>  

	<div class="view_wrap typeB">
	
		<div class="tit_area">
			<h4 class="tit">기본정보</h4>
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
						<th scope="row">구매번호</th>
						<td>${inProgrsBidPblancDetail.PRCH_NO}</td>
						<th scope="row">공고번호</th>
						<td>${inProgrsBidPblancDetail.ANNC_NO} - ${inProgrsBidPblancDetail.ANNC_NGR}</td>
					</tr>
					<tr>	
						<th scope="row">입찰담당자</th>
						<td colspan="3">${inProgrsBidPblancDetail.CHRGR_NM}</td>
					</tr>
					<tr>
						<th scope="row">입찰문의 전화번호</th>
						<td>${inProgrsBidPblancDetail.CHRGR_TEL_NO }</td>
						<th scope="row">입찰문의 이메일</th>
						<td>${inProgrsBidPblancDetail.CHRGR_EMAL }</td>
					</tr>
					<tr>	
						<th scope="row">기술문의담당자</th>
						<td colspan="3">${inProgrsBidPblancDetail.TCHN_CHRGR_NM }</td>
					</tr>
					<tr>
						<th scope="row">기술문의 전화번호</th>
						<td>${inProgrsBidPblancDetail.TCHN_CHRGR_TEL_NO }</td>
						<th scope="row">기술문의 이메일</th>
			            <td>${inProgrsBidPblancDetail.TCHN_CHRGR_EMAL }</td>
					</tr>
				</tbody>
			</table>
		</div>
	
		<div class="tit_area">
			<h4 class="tit">입찰개요</h4>
		</div>
		<div class="view_area">
			<table>
				<caption>입찰개요</caption>
		    	<colgroup>
		        	<col width="15%">
		        	<col width="35%">
		        	<col width="15%">
		        	<col width="35%">
				</colgroup>
		    	<tr>
		    		<th scope="row">입찰방법</th>
			    	<td>
			    		${inProgrsBidPblancDetail.BID_MTCD_NM}
			    	</td>
			    	<th scope="row"><label >계약구분</label></th>
			    	<td>
			    		${inProgrsBidPblancDetail.CONT_SECD_NM}
			            <c:if test="${inProgrsBidPblancDetail.CONT_DECD eq '200'}">&nbsp;/&nbsp;${inProgrsBidPblancDetail.CONT_DECD_NM}</c:if>
			    	</td>
			    </tr>
			    <tr>
			    	<th scope="row"><label >입찰공고명</label></th>
			    	<td colspan="3">
		            	<c:if test="${inProgrsBidPblancDetail.EMRG_YN eq 'Y'}"><font color="red">[긴급] </font></c:if>
						<c:if test="${inProgrsBidPblancDetail.ANNC_NGR > 1}"><font color="red">[정정] </font></c:if>
                        ${inProgrsBidPblancDetail.BID_NM}
			    	</td>
			    </tr>
			    <tr>    
			    	<th scope="row">제안서(규격서)필수여부</th>
			    	<td>${inProgrsBidPblancDetail.PRPDC_ESS_YN_NM}</td>
			    	<th scope="row">G2B동시게시여부</th>
			    	<td>${inProgrsBidPblancDetail.G2B_NTFY_YN_NM}</td>
			    </tr>
			    <tr class="emrgDiv" <c:if test="${inProgrsBidPblancDetail.EMRG_YN ne 'Y'}"> style="display: none;"</c:if>>
			    	<th scope="row"><label >긴급입찰사유</label></th>
			    	<td colspan="3">
			    		${inProgrsBidPblancDetail.EMRG_BID_RSN}
			    	</td>
			    </tr>
			    <tr>
			    	<th scope="row">계약기간(TEXT)</th>
			    	<td>${inProgrsBidPblancDetail.CONT_TE}</td>
			    	<th scope="row"><label >계약기간</label></th>
			    	<td>
                        ${comFn:formatDate(inProgrsBidPblancDetail.CONT_STDE,'yyyyMMdd','yyyy-MM-dd')}
                        &nbsp;~&nbsp;
                        ${comFn:formatDate(inProgrsBidPblancDetail.CONT_ENDE,'yyyyMMdd','yyyy-MM-dd')}
			    	</td>
			    </tr>
			    <tr>
		        	<th scope="row">추정금액 (원)</th>
                    <td>${comFn:formatMoney(inProgrsBidPblancDetail.ESTT_AMT)}</td>
		        	<th scope="row">추정가격 (원)</th>
                    <td>${comFn:formatMoney(inProgrsBidPblancDetail.ESTT_PRCE)}</td>
                </tr>
			    <tr>
                    <th scope="row">SW사업대상</th>
					<td colspan="3">
						<c:if test="${inProgrsBidPblancDetail.SW_BSNS_OBJ_YN ne 'Y'}">아니오</c:if>
                    	<c:if test="${inProgrsBidPblancDetail.SW_BSNS_OBJ_YN eq 'Y'}">예</c:if>
					</td>
                </tr>
			    <c:if test="${inProgrsBidPblancDetail.CONT_SECD eq '4'}">
				    <tr>
	                    <th scope="row">통화</th>
						<td>
							${inProgrsBidPblancDetail.CURR_SECD_NM}
						</td>
	                    <th scope="row">환율</th>
						<td>
							${comFn:formatMoney(inProgrsBidPblancDetail.EXRT)}
						</td>
				    </tr>
			    </c:if>
            </table>
        </div>
        
		<div class="tit_area">
			<h4 class="tit">입찰방법</h4>
		</div>
		<div class="view_area">
			<table>
                <caption>입찰방법</caption>
                <colgroup>
		        	<col width="15%">
		        	<col width="35%">
		        	<col width="15%">
		        	<col width="35%">
                </colgroup>
                <tbody>
                	<tr>
			            <th scope="row"><label >계약방법</label></th>
			            <td>
		            		${inProgrsBidPblancDetail.CONT_MTCD_NM}
			            </td>
			            <th scope="row"></th>
			            <td></td>
			            <%-- <th scope="row">자동유찰여부</th>
			            <td>
			            	${inProgrsBidPblancDetail.AUTO_FBID_YN_NM}
			            </td> --%>
			        </tr>
			        <tr>
			        	<th scope="row"><label >낙찰자선정방법</label></th>
			            <td>
		            		${inProgrsBidPblancDetail.SBID_MTCD_NM}
			            </td>
						<th scope="row">
							<%-- 낙찰자선정방식이 [적격심사]가 아닐 경우 활성화 --%>
							<label  class="elgbDiv" <c:if test="${inProgrsBidPblancDetail.SBID_MTCD ne '20' and inProgrsBidPblancDetail.SBID_MTCD ne '31'}">style="display: none;"</c:if>>낙찰하한율</label>
							<%-- 낙찰자선정방식이 [적격심사]가 아닐 경우 활성화 End--%>
							<%-- 낙찰자선정방식이 [협상에의한계약]이 아닐 경우 활성화 --%>
							<label  class="negoDiv" <c:if test="${inProgrsBidPblancDetail.SBID_MTCD ne '40'}">style="display: none;"</c:if>>합산비율</label>
							<%-- 낙찰자선정방식이 [협상에의한계약]이 아닐 경우 활성화 End--%>
						</th>
						<td>
		                    <div class="elgbDiv" <c:if test="${inProgrsBidPblancDetail.SBID_MTCD ne '20' and inProgrsBidPblancDetail.SBID_MTCD ne '31'}"> style="display:none;"</c:if>>
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
				        	<th scope="row">적격심사종류</th>
				        	<td>${inProgrsBidPblancDetail.ELGB_ESTM_KDCD_NM}</td>
				        	<th scope="row">적격심사통과점수</th>
				        	<td>${inProgrsBidPblancDetail.ELGB_LMT_SCR }</td>
				        </tr>
			        </c:if>
			        <c:if test="${inProgrsBidPblancDetail.SBID_MTCD ne '70'}">
			        <tr>
			        	<th scope="row"><label >예가방식</label></th>
			            <td>
		            		${inProgrsBidPblancDetail.ESTPC_SECD_NM}
						</td>
			        	<th scope="row"><label <c:if test="${inProgrsBidPblancDetail.ESTPC_SECD eq '180002'}"> style="display:none;"</c:if>>기초금액 (원)</label></th>
			        	<td>
			        		<c:if test="${inProgrsBidPblancDetail.ESTPC_SECD ne '180002'}">
			        			${comFn:formatMoney(inProgrsBidPblancDetail.BASE_AMT)}
			        		</c:if>
			        	</td>
			        </tr>
			        <c:if test="${inProgrsBidPblancDetail.ESTPC_SECD eq '180000'}">
			        <tr>
			        	<th scope="row">복수예비가격범위</th>
			            <td colspan="3">
		            		${inProgrsBidPblancDetail.PLR_ESTPC_RNG_CD_NM}
						</td>
			        </tr>
			        </c:if>
			        <tr>
						<th>산정기준&산정방법</th>
						<td colspan="3">
-복수예가<br>
기초금액을 근거로 하여 15개의 예비가격을 작성(비공개)한 후 이중 입찰 참가자로 하여금 예비가격을 선택하게 하고<br> 
최다빈도수의 예비가격 산술평균으로 예정가격을 결정하는 방식<br><br>
-단일예가<br>
복수예가방식을 사용하지 않고 기초금액을 근거로 하여 담당자가 미리 예정가격을 결정하는 방식
						</td>
					</tr>
			        <tr>    
			            <th scope="row">입찰설명회여부</th>
			            <td>
			            	${inProgrsBidPblancDetail.BID_BRFS_YN_NM}
			            </td>
			            <%-- 입찰설명회 [예] 일 경우 활성화 --%>
			            <th scope="row"><label class="brfsDiv" <c:if test="${inProgrsBidPblancDetail.BID_BRFS_YN ne 'Y'}">style="display:none;"</c:if>>설명회필수참석여부</label></th>
			            <td>
			            	<div class="brfsDiv" <c:if test="${inProgrsBidPblancDetail.BID_BRFS_YN ne 'Y'}">style="display:none;"</c:if>>
			            		${inProgrsBidPblancDetail.BID_BRFS_ATND_YN_NM}
			            	</div>
			            </td>
					</tr>
					<tr class="brfsDiv" <c:if test="${inProgrsBidPblancDetail.BID_BRFS_YN ne 'Y'}">style="display:none;"</c:if>>    
			            <th scope="row"><label >입찰설명회장소</label></th> 
	                    <td colspan="3">
	                        ${inProgrsBidPblancDetail.BRFS_PLC_NM}
	                    </td>
					</tr>
					</c:if>
					<%-- 입찰설명회 [예] 일 경우 활성화 END--%>
					<%-- 계약구분이 [공사] 일 경우 활성화 --%>
					<%-- <tr class="ctrcDiv" <c:if test="${inProgrsBidPblancDetail.CONT_SECD ne '2'}">style="display:none;"</c:if>>    
			            <th scope="row">현장설명회여부</th>
			            <td>
			            	${inProgrsBidPblancDetail.SITE_BRFS_YN_NM}
			            </td>
			            현장설명회 [예] 일 경우 활성화
			            <th scope="row"><label  class="ctrcDiv siteBrfsDiv" <c:if test="${inProgrsBidPblancDetail.SITE_BRFS_YN ne 'Y'}">style="display:none;"</c:if>>현장설명회일시</label></th>
			            <td>
			            	<div class="ctrcDiv siteBrfsDiv" <c:if test="${inProgrsBidPblancDetail.SITE_BRFS_YN ne 'Y'}">style="display:none;"</c:if>>
				            	${comFn:formatDate(inProgrsBidPblancDetail.SITE_BRFS_DT,'yyyyMMddHHmmss','yyyy-MM-dd HH:mm')}
	                        </div>
			            </td>
					</tr>
					<tr class="ctrcDiv siteBrfsDiv" <c:if test="${inProgrsBidPblancDetail.CONT_SECD ne '2' or inProgrsBidPblancDetail.SITE_BRFS_YN ne 'Y'}">style="display:none;"</c:if>>    
			            <th scope="row"><label >현장설명회장소</label></th> 
	                    <td colspan="3">
	                        ${inProgrsBidPblancDetail.SITE_BRFS_PLC}
	                    </td>
					</tr> --%>
					<%-- 현장설명회 [예] 일 경우 활성화 END--%>
					<%-- 계약구분이 [공사] 일 경우 활성화 END --%>
					<c:if test="${inProgrsBidPblancDetail.SBID_MTCD ne '70'}">
						<tr <c:if test="${inProgrsBidPblancDetail.EXMT_YN ne 'N'}">style="display: none;"</c:if>>
		                    <th scope="row">입찰보증률</th>
		                    <td>
		                    	${inProgrsBidPblancDetail.BID_GTAMT_RT}
		                    </td>
		                    <th scope="row">입찰보증금</th>
		                    <td>
								${comFn:formatMoney(inProgrsBidPblancDetail.BIDGR_AMT)}
		                    </td>
		                </tr>
		                <tr>
		                    <th scope="row"><label >입찰참가자격</label></th>
		                    <td colspan="3">
		                        <textarea taView style="display: none;">${inProgrsBidPblancDetail.PRTC_QLF_CNTN}</textarea>
		                    </td>
		                </tr>
		                <tr>
		                	<th scope="row"><label >공동수급</label></th>
		                    <td>
			            		${inProgrsBidPblancDetail.ASSO_SPDM_CD_NM}
		                    </td>
		                    <th scope="row"><label >동가입찰옵션</label></th>
		                    <td>
			            		${inProgrsBidPblancDetail.SMPR_BID_SECD_NM}
		                    </td>
		                </tr>
	                </c:if>
	                <c:if test="${inProgrsBidPblancDetail.SBID_MTCD eq '70'}">
	                	<tr>
		                	<th scope="row">제한횟수</th>
		                    <td>
			            		${inProgrsBidPblancDetail.DTAC_TNDR_LMT_CNT}
		                    </td>
		                    <th scope="row">최소인하비율</th>
		                    <td>
			            		${inProgrsBidPblancDetail.MIN_DN_RT}
		                    </td>
		                </tr>
		                <tr>
		                    <th scope="row">0원입찰가능여부</th>
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
			            <th scope="row"><label >지역제한</label></th>
			            <td>
			            	${inProgrsBidPblancDetail.ARA_LMT_CD_NM}
			            </td>
			            <th scope="row"><label >업종제한</label></th>
			            <td>
			            	${inProgrsBidPblancDetail.BTP_LMT_CD_NM}
			            </td>
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
		                    <th scope="row"><label >지체상금률 (%)</label></th>
		                    <td>
		                        ${inProgrsBidPblancDetail.CPDF_RT} /1,000
		                    </td>
		                    <th scope="row"><label >계약보증금률 (%)</label></th>
		                    <td>
		                        ${inProgrsBidPblancDetail.CTFL_GTAMT_RT}
		                    </td>
		                </tr>
		                <tr>
		                    <th scope="row"><label >하자이행보증금률 (%)</label></th>
		                    <td>
		                        ${inProgrsBidPblancDetail.DFFL_GTAMT_RT}
		                    </td>
		                    <%-- 계약구분이 [구매] 일 경우 활성화 --%>
		                    <th scope="row"><label  class="buyDiv" <c:if test="${inProgrsBidPblancDetail.CONT_SECD ne '0' and inProgrsBidPblancDetail.CONT_SECD ne '4'}"> style="display: none;"</c:if>>물품인도조건</label></th>
		                    <td>
		                    	<div class="buyDiv" <c:if test="${inProgrsBidPblancDetail.CONT_SECD ne '0' and inProgrsBidPblancDetail.CONT_SECD ne '4'}">style="display: none;"</c:if>>
			            			${inProgrsBidPblancDetail.ITEM_DRCD_NM}
			            		</div>
		                    </td>
		                </tr>
		                <tr class="buyDiv" <c:if test="${inProgrsBidPblancDetail.CONT_SECD ne '0' and inProgrsBidPblancDetail.CONT_SECD ne '4'}"> style="display: none;"</c:if>>
		                    <th scope="row"><label >납품장소</label></th>
		                    <td colspan="3">
		                        <textarea taView style="display: none;">${inProgrsBidPblancDetail.DLGD_PLC_NM}</textarea>
		                    </td>
		                </tr>
		                <tr class="buyDiv" <c:if test="${inProgrsBidPblancDetail.CONT_SECD ne '0' and inProgrsBidPblancDetail.CONT_SECD ne '4'}"> style="display: none;"</c:if>>
		                    <th scope="row"><label >납품기한</label></th>
		                    <td colspan="3">
		                    	<textarea taView style="display: none;">${inProgrsBidPblancDetail.DLGD_TE_CNTN}</textarea>
		                    </td>
		                </tr>
		                <%--
		                <tr class="buyDiv" <c:if test="${inProgrsBidPblancDetail.CONT_SECD ne '0'}"> style="display: none;"</c:if>>
		                    <th scope="row"><label >설치기한</label></th>
		                    <td colspan="3">
		                    	<textarea taView style="display: none;">${inProgrsBidPblancDetail.ISTL_LMT}</textarea>
		                    </td>
		                </tr>
		                --%>
		                <tr>
		                    <th scope="row"><label >비고</label></th>
		                    <td colspan="3">
		                    	<textarea taView style="display:none;">${inProgrsBidPblancDetail.RMK}</textarea>
		                    </td>
		                </tr>
		                <%-- 계약구분이 [구매] 일 경우 활성화 END --%>
	                </tbody>
	            </table>
	        </div>
        </c:if>
        
		<div class="tit_area">
			<h4 class="tit">입찰진행순서</h4>
		</div>
		<div class="view_area">
			<table>
                <caption>입찰진행순서</caption>
                <colgroup>
                    <col width="15%">
                    <col width="85%">
                </colgroup>
                <tbody>
                	<tr>
	                    <th scope="row"><label >입찰공고게시일시</label></th>
	                    <td>
							${comFn:formatDate(inProgrsBidPblancDetail.ANNC_DT,'yyyyMMddHHmmss','yyyy-MM-dd HH:mm')}
	                    </td>
	                </tr>
	                <%-- 입찰설명회 여부가 [예] 일 경우에 활성화 --%>
	                <c:if test="${inProgrsBidPblancDetail.ROUND_NO < 2}">
	                <tr class="brfsDiv" <c:if test="${inProgrsBidPblancDetail.BID_BRFS_YN ne 'Y'}"> style="display:none;"</c:if>>
	                    <th scope="row"><label >입찰설명회일시</label></th>
	                    <td>
							${comFn:formatDate(inProgrsBidPblancDetail.BRFS_DT,'yyyyMMddHHmmss','yyyy-MM-dd HH:mm')}
	                    </td>
	                </tr>
	                <c:if test="${inProgrsBidPblancDetail.PRPDC_ESS_YN eq 'Y'}">
	                <tr>
	                    <th scope="row"><label >제안서제출기간</label></th>
	                    <td>
		                    ${comFn:formatDate(inProgrsBidPblancDetail.PRPDC_SBMT_STDT,'yyyyMMddHHmmss','yyyy-MM-dd HH:mm')}
	                        &nbsp;~&nbsp;
	                        ${comFn:formatDate(inProgrsBidPblancDetail.PRPDC_SBMT_ENDT,'yyyyMMddHHmmss','yyyy-MM-dd HH:mm')}
	                    </td>
	                </tr>
	                </c:if>
	                </c:if>
	                <%-- 입찰설명회 여부가 [예] 일 경우에 활성화 End --%>
	                <c:if test="${bidPblancDetail.SBID_MTCD ne '34'}">
	                <tr>
	                    <th scope="row"><label >입찰서제출기간</label></th>
	                    <td>
	                    	${comFn:formatDate(inProgrsBidPblancDetail.BIDC_SBMT_STDT,'yyyyMMddHHmmss','yyyy-MM-dd HH:mm')}
	                        &nbsp;~&nbsp;
	                        ${comFn:formatDate(inProgrsBidPblancDetail.BIDC_SBMT_ENDT,'yyyyMMddHHmmss','yyyy-MM-dd HH:mm')}
	                    </td>
	                </tr>
	                </c:if>
	                <c:if test="${bidPblancDetail.SBID_MTCD eq '34' and not empty bidPlanDetail.PRPDC_SBMT_ENDT}">
	                <tr>
	                    <th scope="row"><label >입찰서제출기간</label></th>
	                    <td>
	                    	${comFn:formatDate(inProgrsBidPblancDetail.BIDC_SBMT_STDT,'yyyyMMddHHmmss','yyyy-MM-dd HH:mm')}
	                        &nbsp;~&nbsp;
	                        ${comFn:formatDate(inProgrsBidPblancDetail.BIDC_SBMT_ENDT,'yyyyMMddHHmmss','yyyy-MM-dd HH:mm')}
	                    </td>
	                </tr>	                
	                </c:if>
	                <tr>
	                    <th scope="row"><label >개찰일시</label></th>
	                    <td>
		                    ${comFn:formatDate(inProgrsBidPblancDetail.OPNG_DT,'yyyyMMddHHmmss','yyyy-MM-dd HH:mm')}
	                    </td>
	                </tr>
	                <c:if test="${inProgrsBidPblancDetail.ANNC_SECD eq 'HNDW_BID' }">
		                <tr>
		                    <th scope="row">개찰장소</th>
		                    <td>
		                    	${inProgrsBidPblancDetail.OPNG_PLC_NM }
		                    </td>
						</tr>
					</c:if>
                </tbody>
            </table>
        </div>
        
		<div class="buyDiv">
        	<div class="tit_area">
		       	<h4 class="tit">입찰품목</h4>
	        </div>
			<div class="view_area">
	            <table>
	                <caption>입찰품목</caption>
	                <colgroup>
	                   	<col width="10%">
              		 	<col width="10%">
              		 	<col width="*">
              		 	<col width="15%">
              		 	<col width="10%">
              		 	<col width="10%">
              		 	<col width="10%">
              		 	<col width="10%">
	                </colgroup>
	                <thead>
		                <tr class="line">
		                    <th scope="col" class="txtc">구매요구번호</th>
		                    <th scope="col" class="txtc">품목번호</th>
		                    <th scope="col" class="txtc">품명</th>
		                    <th scope="col" class="txtc">규격</th>
		                    <th scope="col" class="txtc">단위</th>
		                    <th scope="col" class="txtc">수량</th>
		                    <th scope="col" class="txtc">추정단가<br/>(VAT포함)</th>
		                    <th scope="col" class="txtc">추정금액<br/>(수량*단가)</th>
		                </tr>
	                </thead>
	                <tbody id="biprInfoShowTbdy">
	                	<c:if test="${ empty bidPrdlsList}">
	                		<tr>
								<td class="txtc" colspan="8">검색 조건에 해당하는 데이터가 존재하지 않습니다.</td>
							</tr>
	                	</c:if>
	                	<c:if test="${not empty bidPrdlsList}">
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
	                	</c:if>
	                </tbody>
	            </table>
	        </div>
        </div>
        
        <br>
		
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
        	<c:if test="${not empty loginResult}">
        		<c:if test="${empty bddprSttus }">
	        		<c:if test="${ inProgrsBidPblancDetail.BID_BRFS_YN eq 'Y'}">
	        			<c:if test="${ inProgrsBidPblancDetail.BRFS_DT > serverTime}">
	        				<button type="button" class="btn btn_m btn_orange" id="bidDcBtn">입찰설명회신청</button>
	        			</c:if>
	        		</c:if>
	        	</c:if>
	        	<c:if test="${serverTime < inProgrsBidPblancDetail.BIDC_SBMT_ENDT  }">
	          		<button type="button" class="btn btn_m btn_orange" id="bidcSbmtBtn">입찰서작성</button>
	          	</c:if>
	        	<c:if test="${serverTime < inProgrsBidPblancDetail.PRPDC_SBMT_ENDT  }">
	         	 	<button type="button" class="btn btn_m btn_orange" id="bidcPrprcBtn">제안서제출</button>
	          	</c:if>
	          	<%-- <c:if test="${not empty inProgrsBidPblancDetail.BIDC_SBMT_ENDT }"> --%>
	          	<c:if test="${serverTime < inProgrsBidPblancDetail.BIDC_SBMT_ENDT  }">
		        	<c:if test="${inProgrsBidPblancDetail.ASSO_SPDM_CD ne '240000'}">
		        		<c:if test="${empty copertnSdenList}">
			          		<button type="button" class="btn btn_m btn_orange" id="assoSpdmRegBtn">공동수급협정서제출</button>
			          	</c:if>
			          	<c:if test="${not empty copertnSdenList}">
				          	<button type="button" class="btn btn_m btn_orange" id="assoSpdmViewBtn">공동수급협정서보기</button>
			          	</c:if>
		        	</c:if>
	        	</c:if>
        	</c:if>
            <button type="button" class="btn btn_m btn_del" id="listBtn">목록</button>
        </div>
    </div>
    
</div>

<form id="listFrm" method="POST">
	<input type="hidden" name="resourceName" value="${param.resourceName}">
</form>

<%-- 공동수급협정서 --%>
<form id="assoSpdmRegFrm" method="POST">
 	<input type="hidden" name="resourceName" value="${param.resourceName}">
	<input type="hidden" name="P_ANNC_NO" value="${inProgrsBidPblancDetail.ANNC_NO}">
	<input type="hidden" name="P_ANNC_NGR" value="${inProgrsBidPblancDetail.ANNC_NGR}">
	<input type="hidden" name="P_ROUND_NO" value="${inProgrsBidPblancDetail.ROUND_NO}">
	<input type="hidden" name="P_RETURN_URL" value="inProgrsBidPblancDetail">
	<input type="hidden" name="P_BID_GBN" value="BID">
</form>

<%-- 입찰서제출 --%>
<form id="bipaPresentnFrm" method="POST">
  	<input type="hidden" name="resourceName" value="${param.resourceName}">
	<input type="hidden" name="P_ANNC_NO" value="${inProgrsBidPblancDetail.ANNC_NO}">
	<input type="hidden" name="P_ANNC_NGR" value="${inProgrsBidPblancDetail.ANNC_NGR}">
	<input type="hidden" name="P_ROUND_NO" value="${inProgrsBidPblancDetail.ROUND_NO}">
	<input type="hidden" name="P_RETURN_URL" value="inProgrsBidPblancDetail">
</form>

<%-- 팝업 폼 --%>
  	<form id="popupFrm" method="POST">
	<input type="hidden" name="P_ANNC_NO" value="${inProgrsBidPblancDetail.ANNC_NO}">
	<input type="hidden" name="P_ANNC_NGR" value="${inProgrsBidPblancDetail.ANNC_NGR}">
	<input type="hidden" name="P_ROUND_NO" value="${inProgrsBidPblancDetail.ROUND_NO}">
</form>
  	
<%-- 파일 VIEW 폼 --%>
<form id="fileViewFrm" method="POST">
	<input type="hidden" id="P_FILE_GRP_NO" name="P_FILE_GRP_NO" value="${inProgrsBidPblancDetail.FILE_GRP_NO}">
</form>