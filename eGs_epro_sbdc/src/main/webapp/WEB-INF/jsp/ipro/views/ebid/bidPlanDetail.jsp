<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 입찰현황 > 입찰대기현황 상세
 *
 * <pre>
 * ebid 
 *    |_ bidPlanDetail.jsp
 * 
 * </pre>
 * @date : 2017. 06. 15
 * @version : 1.0
 * @author : 은우소프트 전상훈
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %> 
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %> 

<script type="text/javascript" src="${jsPath}/ipro/views/ebid/bidPlanDetail.js"></script> 

<div class="contents_wrap">
	<ul class="step_wrap">
		<li><a href="#">${myMenuList.bigMenuNm}</a></li>
		<li><a href="#">${myMenuSubList.smallMenuNm}</a></li>
	</ul>
	<div class="tit_wrap">
		<h3 class="tit">입찰작성현황 상세</h3>
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
			    		<th>구매번호</th>
			    		<td colspan="3">${bidPlanDetail.PCAC_NO}</td>
			    	</tr>
			    	<tr>	
			            <th>입찰담당자</th>
			            <td>${bidPlanDetail.CHRGR_NM}</td>
			            <th>입찰문의 전화번호</th>
			            <td>${bidPlanDetail.CHRGR_TEL_NO }</td>
		        	</tr>
			    	<tr>	
			            <th>기술문의담당자</th>
			            <td>${bidPlanDetail.TCHN_CHRGR_NM }</td>
			            <th>기술문의 전화번호</th>
			            <td>${bidPlanDetail.TCHN_CHRGR_TEL_NO }</td>
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
		        	<col style="width: 15%;">						        
		        	<col style="width: 35%;">						        
		        	<col style="width: 15%;">						        
		        	<col style="width: 35%;">						        
				</colgroup>
		    	<tr>
		    		<th>입찰방법</th>
		    		<td>${bidPlanDetail.BID_MTCD_NM}</td>
		            <th>계약구분</th>
		            <td>
		            	${bidPlanDetail.CONT_SECD_NM}<c:if test="${bidPlanDetail.UPRC_YN eq 'Y'}">(단가)</c:if>
		            	<c:if test="${bidPlanDetail.CONT_DECD eq '200'}">&nbsp;/&nbsp;${bidPlanDetail.CONT_DECD_NM}</c:if>
		            </td>
		        </tr>
		        <tr>
		        	<th>입찰공고명</th>
		            <td colspan="3">
                        <c:if test="${bidPlanDetail.EMRG_YN eq 'Y'}"><font color="red">[긴급] </font></c:if>
						<c:if test="${bidPlanDetail.ANNC_NGR > 1}"><font color="red">[정정] </font></c:if>
						${bidPlanDetail.BID_NM}
		            </td>
		        </tr>
		        <tr>
		            <th>제안서(규격서)필수여부</th>
		            <td>${bidPlanDetail.PRPDC_ESS_YN_NM}</td>
		            <th>G2B동시게시여부</th>
		            <td>${bidPlanDetail.G2B_NTFY_YN_NM}</td>
				</tr>
				<tr class="emrgDiv" <c:if test="${bidPlanDetail.EMRG_YN ne 'Y'}"> style="display: none;"</c:if>>
		            <th>긴급입찰사유</th>
		            <td colspan="3">${bidPlanDetail.EMRG_BID_RSN}</td>
		        </tr>
		        <tr>
                    <th>계약기간(TEXT)</th>
                    <td>${bidPlanDetail.CONT_TE}</td>
		        	<th>계약기간</th>
                    <td>
                        ${comFn:formatDate(bidPlanDetail.CONT_STDE,'yyyyMMdd','yyyy-MM-dd')}
                        &nbsp;~&nbsp;
                        ${comFn:formatDate(bidPlanDetail.CONT_ENDE,'yyyyMMdd','yyyy-MM-dd')}
                    </td>
                </tr>
                <tr>
		        	<th scope="row">추정금액 (원)</th>
                    <td>${comFn:formatMoney(bidPlanDetail.ESTT_AMT)}</td>
		        	<th scope="row">추정가격 (원)</th>
                    <td>${comFn:formatMoney(bidPlanDetail.ESTT_PRCE)}</td>
                </tr>
                <tr>
                    <th>SW사업대상</th>
					<td colspan="3">
						<c:if test="${bidPlanDetail.SW_BSNS_OBJ_YN ne 'Y'}">아니오</c:if>
                    	<c:if test="${bidPlanDetail.SW_BSNS_OBJ_YN eq 'Y'}">예</c:if>
					</td>
                </tr>                
                <c:if test="${bidPlanDetail.CONT_SECD eq '4'}">
                <tr>
                    <th>통화</th>
					<td>
						${bidPlanDetail.CURR_SECD_NM}
					</td>
                    <th scope="row">환율</th>
					<td>
						${comFn:formatMoney(bidPlanDetail.EXRT)}
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
		        	<col style="width: 15%;">						        
		        	<col style="width: 35%;">						        
		        	<col style="width: 15%;">						        
		        	<col style="width: 35%;">
		        </colgroup>
		        <tr>
		            <th>계약방법</th>
		            <td>${bidPlanDetail.CONT_MTCD_NM}</td>
		            <th>자동유찰여부</th>
		            <td>${bidPlanDetail.AUTO_FBID_YN_NM}</td>
		        </tr>
		        <tr>
		        	<th>낙찰자선정방법</th>
		            <td>${bidPlanDetail.SBID_MTCD_NM}</td>
					<th>
						<%-- 낙찰자선정방식이 [적격심사]가 아닐 경우 활성화 --%>
						<label class="elgbDiv" <c:if test="${bidPlanDetail.SBID_MTCD ne '31'}">style="display: none;"</c:if>>낙찰하한율 (%)</label>
						<%-- 낙찰자선정방식이 [적격심사]가 아닐 경우 활성화 End--%>
						<%-- 낙찰자선정방식이 [협상에의한계약]이 아닐 경우 활성화 --%>
						<label class="negoDiv" <c:if test="${bidPlanDetail.SBID_MTCD ne '40'}">style="display: none;"</c:if>>합산비율</label>
						<%-- 낙찰자선정방식이 [협상에의한계약]이 아닐 경우 활성화 End--%>
					</th>
					<td>
	                    <div class="elgbDiv" <c:if test="${bidPlanDetail.SBID_MTCD ne '31'}"> style="display:none;"</c:if>>
		                    <%-- 낙찰자선정방식이 [적격심사]가 아닐 경우 활성화 --%>
	                        ${bidPlanDetail.SBID_LWST_RT}
	                    	<%-- 낙찰자선정방식이 [적격심사]가 아닐 경우 활성화 End--%>
	                    </div>
	                    <div class="negoDiv" <c:if test="${bidPlanDetail.SBID_MTCD ne '40'}"> style="display:none;"</c:if>>
	                    	<%-- 낙찰자선정방식이 [협상에의한계약]이 아닐 경우 활성화 --%>
                        	기술점수비율&nbsp;${bidPlanDetail.TCHN_SCR_RT }&nbsp;:&nbsp;가격점수비율&nbsp;${bidPlanDetail.PRCE_SCR_RT }
							<%-- 낙찰자선정방식이 [협상에의한계약]이 아닐 경우 활성화 End--%>
	                    </div>
                    </td>
		        </tr>
		        <c:if test="${bidPlanDetail.SBID_MTCD eq '31'}">
			        <tr>
			        	<th>적격심사종류</th>
			        	<td>${bidPlanDetail.ELGB_ESTM_KDCD_NM}</td>
			        	<th>적격심사통과점수</th>
			        	<td>${bidPlanDetail.ELGB_LMT_SCR }</td>
			        </tr>
		        </c:if>
		        <c:if test="${bidPlanDetail.SBID_MTCD ne '70'}">
			        <tr>
			        	<th>예가방식</th>
			            <td>${bidPlanDetail.ESTPC_SECD_NM}</td>
			        	<th><label <c:if test="${bidPlanDetail.ESTPC_SECD eq '180002'}"> style="display:none;"</c:if>>기초금액 (원)</label></th>
			        	<td>
			        		<c:if test="${bidPlanDetail.ESTPC_SECD ne '180002'}">${comFn:formatMoney(bidPlanDetail.BASE_AMT)}</c:if>
			        	</td>
			        </tr>
			        <c:if test="${bidPlanDetail.ESTPC_SECD eq '180000'}">
				        <tr>
				        	<th>복수예비가격범위</th>
				            <td colspan="3">${bidPlanDetail.PLR_ESTPC_RNG_CD_NM}</td>
				        </tr>
			        </c:if>
			        <tr>    
			            <th>입찰설명회여부</th>
			            <td>${bidPlanDetail.BID_BRFS_YN_NM}</td>
			            <%-- 입찰설명회 [예] 일 경우 활성화 --%>
			            <th><label class="brfsDiv" <c:if test="${bidPlanDetail.BID_BRFS_YN ne 'Y'}">style="display:none;"</c:if>>입찰설명회필수참석여부</label></th>
			            <td>
			            	<div class="brfsDiv" <c:if test="${bidPlanDetail.BID_BRFS_YN ne 'Y'}">style="display:none;"</c:if>>
			            		${bidPlanDetail.BID_BRFS_ATND_YN_NM}
			            	</div>
			            </td>
					</tr>
					<tr class="brfsDiv" <c:if test="${bidPlanDetail.BID_BRFS_YN ne 'Y'}">style="display:none;"</c:if>>    
			            <th>입찰설명회장소</th> 
	                    <td colspan="3">${bidPlanDetail.BRFS_PLC_NM}</td>
					</tr>
				</c:if>
				<%-- 입찰설명회 [예] 일 경우 활성화 END--%>
				<%-- 계약구분이 [공사] 일 경우 활성화 --%>
				<tr class="ctrcDiv" <c:if test="${bidPlanDetail.CONT_SECD ne '2'}">style="display:none;"</c:if>>    
		            <th>현장설명회여부</th>
		            <td>
		            	${bidPlanDetail.SITE_BRFS_YN_NM}
		            </td>
		            <%-- 현장설명회 [예] 일 경우 활성화 --%>
		            <th><label class="ctrcDiv siteBrfsDiv" <c:if test="${bidPlanDetail.SITE_BRFS_YN ne 'Y'}">style="display:none;"</c:if>>현장설명회일시</label></th>
		            <td>
		            	<div class="ctrcDiv siteBrfsDiv" <c:if test="${bidPlanDetail.SITE_BRFS_YN ne 'Y'}">style="display:none;"</c:if>>
			            	${comFn:formatDate(bidPlanDetail.SITE_BRFS_DT,'yyyyMMddHHmmss','yyyy-MM-dd HH:mm')}
                        </div>
		            </td>
				</tr>
				<tr class="ctrcDiv siteBrfsDiv" <c:if test="${bidPlanDetail.CONT_SECD ne '2' and bidPlanDetail.SITE_BRFS_YN ne 'Y'}">style="display:none;"</c:if>>    
		            <th>현장설명회장소</th> 
                    <td colspan="3">
                        ${bidPlanDetail.SITE_BRFS_PLC}
                    </td>
				</tr>
				<%-- 현장설명회 [예] 일 경우 활성화 END--%>
				<%-- 계약구분이 [공사] 일 경우 활성화 END --%>
				<c:if test="${bidPlanDetail.SBID_MTCD ne '70'}">
					<tr <c:if test="${bidPlanDetail.EXMT_YN ne 'N'}">style="display: none;"</c:if>>
	                    <th>입찰보증률</th>
	                    <td>${bidPlanDetail.BID_GTAMT_RT}</td>
	                    <th>입찰보증금</th>
	                    <td>${comFn:formatMoney(bidPlanDetail.BIDGR_AMT)}</td>
	                </tr>
	                <tr>
	                    <th>입찰참가자격</th>
	                    <td colspan="3">
	                        <textarea taView style="display: none;">${bidPlanDetail.PRTC_QLF_CNTN}</textarea>
	                    </td>
	                </tr>
	                <tr>
	                	<th>공동수급</th>
	                    <td>${bidPlanDetail.ASSO_SPDM_CD_NM}</td>
	                    <th>동가입찰옵션</th>
	                    <td>${bidPlanDetail.SMPR_BID_SECD_NM}</td>
	                </tr>
                </c:if>
                <c:if test="${bidPlanDetail.SBID_MTCD eq '70'}">
                	<tr>
	                	<th>제한횟수</th>
	                	<td>${bidPlanDetail.DTAC_TNDR_LMT_CNT}</td>
	                	<th>최소인하비율 (%)</th>
	                	<td>${bidPlanDetail.MIN_DN_RT}</td>
	                </tr>
	                <tr>
	                	<th>0원입찰가능여부</th>
	                	<td colspan="3">
	                		<c:if test="${bidPlanDetail.ZERO_BID_PSBL_YN eq 'Y'}">예</c:if>
	                		<c:if test="${bidPlanDetail.ZERO_BID_PSBL_YN ne 'Y'}">아니오</c:if>
	                	</td>
	                </tr>
                </c:if>
		    </table>
		</div>
			
		<%-- 계약방법이 [지명경쟁] 일 경우 활성화 --%>
        <div class="slctDiv" <c:if test="${bidPlanDetail.CONT_MTCD ne '10001' and bidPlanDetail.CONT_MTCD ne '10005'}"> style="display: none;"</c:if>>
        	<div class="tit_area">
        		<h4 class="tit" style="clear: both;">지명업체</h4>
	        </div>
			<div class="view_area" style="margin-bottom: 30px;">
	            <table>
	                <caption>지명업체선택</caption>
	                <colgroup>
	                   	<col style="width: 20%;">
	                   	<col style="width: auto;">
	                   	<col style="width: 20%;">
	                </colgroup>
	                <thead>
		                <tr>
		                    <th scope="col" style="text-align: center;">사업자번호</th>
		                    <th scope="col" style="text-align: center;">업체명</th>
		                    <th scope="col" style="text-align: center;">대표자명</th>
		                </tr>
	                </thead>
	                <tbody>
		               	<tr class="row" id="nmenChoiseEmpty" <c:if test="${not empty bidNmfpcEntrpsList}"> style="display:none;"</c:if>>
		               		<td colspan="3"  style="text-align: center;">선택된 업체가 없습니다.</td>
		               	</tr>
	               	</tbody>
	                <tbody id="nmenChoiseShowTbdy">
	                	<c:forEach var="data" items="${bidNmfpcEntrpsList}" varStatus="status" >
							<tr class="row">
								<td style="text-align: center;">${data.BIZRNO}</td>
								<td>${data.VEND_NM}</td>
								<td>${data.RPRS_NM}</td>
							</tr>
						</c:forEach>
	                </tbody>
	            </table>
	        </div>
        </div>
        <%-- 계약방법이 [지명경쟁] 일 경우 활성화 End --%>
	        
        <%-- 계약방법이 [제한경쟁] 일 경우 활성화 --%>
        <div class="lmtDiv" <c:if test="${bidPlanDetail.CONT_MTCD ne '10002'}"> style="display: none;"</c:if>>
        	<h4 class="tit">투찰제한</h4>
        	<div class="view_area" style="margin-bottom: 30px;">
				<table>
					<caption>투찰제한</caption>
			    	<colgroup>
			        	<col style="width: 15%;">						        
			        	<col style="width: 35%;">						        
			        	<col style="width: 15%;">						        
			        	<col style="width: 35%;">
			        </colgroup>
			        <tr>
			            <th>지역제한</th>
			            <td>${bidPlanDetail.ARA_LMT_CD_NM}</td>
			            <th>업종제한</th>
			            <td>${bidPlanDetail.BTP_LMT_CD_NM}</td>
			        </tr>
				</table>
			</div>
		</div>
        <%-- 계약방법이 [제한경쟁] 일 경우 활성화 End --%>
			
		<c:if test="${bidPlanDetail.SBID_MTCD ne '70'}">
        <div class="tit_area">
	        <h4 class="tit">계약조건</h4>
		</div>
		
		<div class="view_area">
            <table>
                <caption>계약조건</caption>
                <colgroup>
		        	<col style="width: 15%;">						        
		        	<col style="width: 35%;">						        
		        	<col style="width: 15%;">						        
		        	<col style="width: 35%;">
                </colgroup>
                <tbody>
                <tr>
                    <th>지체상금률 (%)</th>
                    <td>${bidPlanDetail.CPDF_RT} /1,000</td>
                    <th>계약보증금률 (%)</th>
                    <td>${bidPlanDetail.CTFL_GTAMT_RT}</td>
                </tr>
                <tr>
                    <th>하자이행보증금률 (%)</th>
                    <td>${bidPlanDetail.DFFL_GTAMT_RT}</td>
                    <%-- 계약구분이 [구매] 일 경우 활성화 --%>
                    <th><label class="buyDiv" <c:if test="${bidPlanDetail.CONT_SECD ne '0'}"> style="display: none;"</c:if>>물품인도조건</label></th>
                    <td>
                    	<div class="buyDiv" <c:if test="${bidPlanDetail.CONT_SECD ne '0'}">style="display: none;"</c:if>>
	            			${bidPlanDetail.ITEM_DRCD_NM}
	            		</div>
                    </td>
                </tr>
                <tr class="buyDiv" <c:if test="${bidPlanDetail.CONT_SECD ne '0'}"> style="display: none;"</c:if>>
                    <th>납품장소</th>
                    <td colspan="3">
                        <textarea taView style="display: none;">${bidPlanDetail.DLGD_PLC_NM}</textarea>
                    </td>
                </tr>
                <tr class="buyDiv" <c:if test="${bidPlanDetail.CONT_SECD ne '0'}"> style="display: none;"</c:if>>
                    <th>납품기한</th>
                    <td colspan="3">
                    	<textarea taView style="display: none;">${bidPlanDetail.DLGD_TE_CNTN}</textarea>
                    </td>
                </tr>
                <%-- 
                <tr class="buyDiv" <c:if test="${bidPlanDetail.CONT_SECD ne '0'}"> style="display: none;"</c:if>>
                    <th>설치기한</th>
                    <td colspan="3">
                    	<textarea taView style="display: none;">${bidPlanDetail.ISTL_LMT}</textarea>
                    </td>
                </tr>
                --%>
                <tr>
                    <th>비고</th>
                    <td colspan="3">
                    	<textarea taView style="display:none;">${bidPlanDetail.RMK}</textarea>
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
        <c:if test="${bidPlanDetail.SBID_MTCD ne '70'}">
			<div class="view_area">
	            <table>
	                <caption>입찰진행순서</caption>
	                <colgroup>
			        	<col style="width: 15%;">						        
			        	<col style="width: 85%;">						        
	                </colgroup>
	                <tbody>
		                <tr>
		                    <th>입찰공고게시일시</th>
		                    <td>${comFn:formatDate(bidPlanDetail.ANNC_DT,'yyyyMMddHHmmss','yyyy-MM-dd HH:mm')}</td>
		                </tr>
		                <%-- 입찰설명회 여부가 [예] 일 경우에 활성화 --%>
		                <c:if test="${bidPlanDetail.ROUND_NO < 2}">
			                <tr class="brfsDiv" <c:if test="${bidPlanDetail.BID_BRFS_YN ne 'Y'}"> style="display:none;"</c:if>>
			                    <th>입찰설명회일시</th>
			                    <td>${comFn:formatDate(bidPlanDetail.BRFS_DT,'yyyyMMddHHmmss','yyyy-MM-dd HH:mm')}</td>
			                </tr>
			                <%-- 입찰설명회 여부가 [예] 일 경우에 활성화 End --%>
			                <c:if test="${bidPlanDetail.PRPDC_ESS_YN eq 'Y'}">
				                <tr>
				                    <th>제안서제출기간</th>
				                    <td>
					                    ${comFn:formatDate(bidPlanDetail.PRPDC_SBMT_STDT,'yyyyMMddHHmmss','yyyy-MM-dd HH:mm')}
				                        <span class="wave">~</span>
				                        ${comFn:formatDate(bidPlanDetail.PRPDC_SBMT_ENDT,'yyyyMMddHHmmss','yyyy-MM-dd HH:mm')}
				                    </td>
				                </tr>
			                </c:if>
		                </c:if>
		                <tr>
		                    <th>입찰서제출기간</th>
		                    <td>
		                    	${comFn:formatDate(bidPlanDetail.BIDC_SBMT_STDT,'yyyyMMddHHmmss','yyyy-MM-dd HH:mm')}
		                        <span class="wave">~</span>
		                        ${comFn:formatDate(bidPlanDetail.BIDC_SBMT_ENDT,'yyyyMMddHHmmss','yyyy-MM-dd HH:mm')}
		                    </td>
		                </tr>
		                <tr>
		                    <th>개찰일시</th>
		                    <td>${comFn:formatDate(bidPlanDetail.OPNG_DT,'yyyyMMddHHmmss','yyyy-MM-dd HH:mm')}</td>
		                </tr>
		                <c:if test="${bidPlanDetail.ANNC_SECD eq 'HNDW_BID' }">
			                <tr>
			                    <th>개찰장소</th>
			                    <td>${bidPlanDetail.OPNG_PLC_NM }</td>
							</tr>
						</c:if>
	                </tbody>
	            </table>
	        </div>
        </c:if>
        
        <c:if test="${bidPlanDetail.SBID_MTCD eq '70'}">
        	<div class="view_area">
	            <table>
	                <caption>입찰진행순서</caption>
	                <colgroup>
			        	<col style="width: 15%;">						        
			        	<col style="width: 85%;">
	                </colgroup>
	                <tbody>
	                	<tr>
                    		<th><label>입참참가자격등록일시</label></th>
		                    <td>
								${comFn:formatDate(bidPlanDetail.PRTC_APLY_ENDT,'yyyyMMddHHmmss','yyyy-MM-dd HH:mm')}
		                    </td>
		                </tr>
		                <tr>
		                    <th><label>역경매마감일시</label></th>
		                    <td>
		                        ${comFn:formatDate(bidPlanDetail.BIDC_SBMT_ENDT,'yyyyMMddHHmmss','yyyy-MM-dd HH:mm')}
		                    </td>
		                </tr>
		                <tr>
		                    <th><label>개찰일시</label></th>
		                    <td>
			                    ${comFn:formatDate(bidPlanDetail.OPNG_DT,'yyyyMMddHHmmss','yyyy-MM-dd HH:mm')}
		                    </td>
		                </tr>
	                </tbody>
	            </table>
	        </div>
        </c:if>
	        
        <div class="buyDiv" <c:if test="${bidPlanDetail.CONT_SECD ne '0'}">style="display: none;"</c:if>>
        	<div class="tit_area">
		       	<h4 class="tit" style="clear: both;">입찰품목</h4>
	        </div>
			<div class="view_area" style="margin-bottom: 30px;">
	            <table>
	                <caption>입찰품목정보</caption>
	                <colgroup>
	                   	<col style="width: 10%;">
	                   	<col style="width: auto;">
	                   	<col style="width: 30%;">
	                   	<col style="width: 10%;">
	                   	<col style="width: 10%;">
	                </colgroup>
	                <thead>
		                <tr>
		                    <th scope="col" style="text-align: center;">품명분류</th>
		                    <th scope="col" style="text-align: center;">품목명</th>
		                    <th scope="col" style="text-align: center;">규격</th>
		                    <th scope="col" style="text-align: center;">단위</th>
		                    <th scope="col" style="text-align: center;">수량</th>
		                </tr>
	                </thead>
	                <tbody id="biprInfoShowTbdy">
	                	<c:forEach var="data" items="${bidPrdlsList}" varStatus="status" >
							<tr class="row">
								<td style="text-align: center;">${data.ITEM_NO}</td>
								<td>${data.ITEM_NM}</td>
								<td>${data.STND_NM}</td>
								<td class="txtc">${data.ITEM_UNNM}</td>
								<td class="txtr pr5">${data.ITEM_QTY}</td>
							</tr>
						</c:forEach>
	                </tbody>
	                <tbody>
						<tr class="row" id="biprInfoEmpty" <c:if test="${ not empty bidPrdlsList}"> style="display:none;"</c:if>>
		               		<td colspan="5" style="text-align: center;">입찰품목이 없습니다.</td>
		               	</tr>
	               	</tbody>
	            </table>
	        </div>
        </div>
	        
        <div class="" <c:if test="${bidPlanDetail.CONT_SECD ne '2' || empty bidPlanDetail.IFC_ID}">style="display: none;"</c:if>>
        	<div class="tit_area">
		       	<h4 class="tit" style="clear: both;">공사종류</h4>
	        </div>
			<div class="view_area" style="margin-bottom: 30px;">
	            <table>
	                <caption>공사종류정보</caption>
	                <colgroup>
	                    <col style="width: 10%;">
	                   	<col style="width: 10%;">
	                   	<col style="width: 10%;">
	                   	<col style="width: auto;">
	                </colgroup>
	                <thead>
		                <tr>
		                    <th scope="col" style="text-align: center;">번호</th>
		                    <th scope="col" style="text-align: center;">공사종류</th>
		                    <th scope="col" style="text-align: center;">예상금액</th>
		                    <th scope="col" style="text-align: center;">작업내용</th>
		                </tr>
	                </thead>
	                <tbody>
	                	<c:forEach var="data" items="${bidPrdlsList}" varStatus="status" >
							<tr class="row">
								<td style="text-align: center;">${status.count}</td>
								<td class="txtc">${data.ITEM_NM}</td>
								<td class="txtr pr5">${comFn:formatMoney(data.RQST_UNIT)}</td>
								<td class="txtl">${data.STND_NM}</td>
							</tr>
						</c:forEach>
	                </tbody>
	                <tbody>
						<tr class="row" <c:if test="${ not empty bidPrdlsList}"> style="display:none;"</c:if>>
		               		<td colspan="4" style="text-align: center;">공사품목이 없습니다.</td>
		               	</tr>
	               	</tbody>
	            </table>
	        </div>
        </div>
       
		<div class="tit_area">
           	<h4 class="tit">기타첨부파일</h4>
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
			
	    <div class="btn_wrap">
			<c:if test="${empty biApprCntc.APPR_STCD or biApprCntc.APPR_STCD eq 'STOP' or biApprCntc.APPR_STCD eq 'NEW'}">
	            <button type="button" class="btn btn_m btn_orange" id="sanctnBtn">결재기안</button>
			</c:if>
<%-- 				<c:if test="${biApprCntc.APPR_STCD eq 'NEW'}"> --%>
<!-- 		            <button type="button" class="btn btn_02 btn_revise" id="sanctnViewBtn">결재기안</button> -->
<%-- 				</c:if> --%>
			<c:if test="${biApprCntc.APPR_STCD eq 'APP'}">
	            <button type="button" class="btn btn_m btn_orange" id="sanctnViewBtn">결재문서</button>
			</c:if>
			<c:if test="${biApprCntc.APPR_STCD eq 'STOP'}">
	            <button type="button" class="btn btn_m btn_orange" id="sanctnViewBtn">이전결재문서</button>
			</c:if>
			<c:if test="${empty biApprCntc.APPR_STCD or biApprCntc.APPR_STCD eq 'STOP' or biApprCntc.APPR_STCD eq 'NEW'}">
	        	<%-- 결재전까지 수정/삭제 보여주기 --%>
	            <button type="button" class="btn btn_m btn_orange" id="updateBtn">수정</button>
	        	<c:if test="${bidPlanDetail.ANNC_NGR eq 1}">
	            	<button type="button" class="btn btn_m btn_orange" id="deleteBtn">삭제</button>
	            </c:if>
	        </c:if>
        	<button type="button" class="btn btn_m btn_del" id="listBtn" >목록</button>
		</div> <!--// btn_wrap E -->
	</div>
</div> <!--// content E-->

<form id="listFrm" method="POST">
	<input type="hidden" name="resourceName" value="${param.resourceName}">
</form>
<form id="updtFrm" method="POST">
	<input type="hidden" name="resourceName" value="${param.resourceName}">
	<input type="hidden" name="P_ANNC_NO" value="${bidPlanDetail.ANNC_NO}">
	<input type="hidden" name="P_ANNC_NGR" value="${bidPlanDetail.ANNC_NGR}">
	<input type="hidden" name="P_ROUND_NO" value="${bidPlanDetail.ROUND_NO}">
	<input type="hidden" name="P_IFC_ID" value="${bidPlanDetail.IFC_ID}">
</form>

<form id="sttusFrm" method="POST" >
	<input type="hidden" name="resourceName" value="${param.resourceName}">
	<input type="hidden" name="P_ANNC_NO" value="${bidPlanDetail.ANNC_NO}">
	<input type="hidden" name="P_ANNC_NGR" value="${bidPlanDetail.ANNC_NGR}">
	<input type="hidden" name="P_ROUND_NO" value="${bidPlanDetail.ROUND_NO}">
	<input type="hidden" name="P_BID_PSCD">
	<input type="hidden" name="P_APPR_STCD">
</form>

<form id="downFrm" method="POST" >
	<input type="hidden" name="P_FILE_SN">
	<input type="hidden" name="P_FILE_GRP_NO">
</form>

<form id="apprFrm" method="POST">
	<input type="hidden" name="P_ANNC_NO" value="${bidPlanDetail.ANNC_NO}">
	<input type="hidden" name="P_ANNC_NGR" value="${bidPlanDetail.ANNC_NGR}">
	<input type="hidden" name="P_ROUND_NO" value="${bidPlanDetail.ROUND_NO}">
	<input type="hidden" name="P_INTERFACE_ID" value="${biApprCntc.APPR_CNTC_KEY}" >
	<input type="hidden" name="P_STATUS" value="${biApprCntc.APPR_STCD}" >
	<input type="hidden" name="P_DRAFT_SABUN" value="${SABUN}" >
</form>

<%-- 파일 VIEW 폼 --%>
<form id="fileViewFrm" method="POST">
	<input type="hidden" id="P_FILE_GRP_NO" name="P_FILE_GRP_NO" value="${bidPlanDetail.FILE_GRP_NO}">
</form>