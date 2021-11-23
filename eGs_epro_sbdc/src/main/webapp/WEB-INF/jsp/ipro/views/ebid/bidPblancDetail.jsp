<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 입찰관리 > 입찰공고 상세
 *
 * <pre>
 * ebid 
 *    |_ bidPblancDetail.jsp
 * 
 * </pre>
 * @date : 2017. 06. 19
 * @version : 1.0
 * @author : 은우소프트 전상훈
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %> 
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %> 

<script type="text/javascript" src="${jsPath}/ipro/views/ebid/bidPblancDetail.js"></script> 

<div class="contents_wrap">
	<ul class="step_wrap">
		<li><a href="#">${myMenuList.bigMenuNm}</a></li>
		<li><a href="#">${myMenuSubList.smallMenuNm}</a></li>
	</ul>
	<div class="tit_wrap">
		<h3 class="tit">입찰공고 상세</h3>
	</div>

	<div class="view_wrap typeB">
	    <div class="btn_wrap">
	    	<button type="button" class="btn btn_s2 btn_c2" id="detailBtn" style="width: 120px;">입찰공고문 상세</button>
	    	<button type="button" class="btn btn_s2 btn_c2" id="detailBereBtn" style="width: 120px;">사전공고문 상세</button>
	    	<c:if test="${bidPlanDetail.BID_PSCD ne 'PF02'}">
		    	<c:if test="${bidPlanDetail.BID_BRFS_YN eq 'Y'}">
    				<button type="button" class="btn btn_m btn_blue" id="bidBrfsDetailBtn">현장설명회</button>
    			</c:if>
				<button type="button" class="btn btn_m btn_blue" id="bidPartcptDetailBtn">입찰참가검토</button>
		    	<c:if test="${bidPlanDetail.PRPDC_ESS_YN eq 'Y'}">
					<button type="button" class="btn btn_m btn_blue" id="bidPrprcDetailBtn">제안/규격서검토</button>
				</c:if>
				<c:if test="${bidPlanDetail.BIDC_SBMT_ENDT < toDay }">
					<button type="button" class="btn btn_m btn_blue" id="bidOpengDetailBtn">개찰대기현황</button>
				</c:if>
	    	</c:if>
	    </div>
	    	
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
			    		<th scope="row">사전공고번호</th>
			    		<td>
			    			<c:if test="${empty bidPlanDetail.BFAN_NO_LIST}">사전공고번호가 없습니다.</c:if>
			    			<c:if test="${not empty bidPlanDetail.BFAN_NO_LIST}">${bidPlanDetail.BFAN_NO_LIST }</c:if>
			    		</td>
			    		<th scope="row">공고번호</th>
						<td>${bidPlanDetail.ANNC_NO} - ${bidPlanDetail.ANNC_NGR}</td>
			    	</tr>
			    	<tr>	
			            <th scope="row">입찰담당자</th>
			            <td colspan="3">${bidPlanDetail.CHRGR_NM}</td>
		        	</tr>
		        	<tr>
			            <th scope="row">입찰문의 전화번호</th>
			            <td>${bidPlanDetail.CHRGR_TEL_NO }</td>
			            <th scope="row">입찰문의 이메일</th>
			            <td>${bidPlanDetail.CHRGR_EMAL }</td>
		        	</tr>
			    	<tr>	
			            <th scope="row">기술문의담당자</th>
			            <td colspan="3">${bidPlanDetail.TCHN_CHRGR_NM }</td>
		        	</tr>
		        	<tr>
			            <th scope="row">기술문의 전화번호</th>
			            <td>${bidPlanDetail.TCHN_CHRGR_TEL_NO }</td>
			            <th scope="row">기술문의 이메일</th>
			            <td>${bidPlanDetail.TCHN_CHRGR_EMAL }</td>
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
		    		<th scope="row">입찰방법</th>
		    		<td>${bidPlanDetail.BID_MTCD_NM}</td>
		            <th scope="row">계약구분</th>
		            <td>
		            	${bidPlanDetail.CONT_SECD_NM}<c:if test="${bidPlanDetail.UPRC_YN eq 'Y'}">(단가)</c:if>
		            	<c:if test="${bidPlanDetail.CONT_DECD eq '200'}">&nbsp;/&nbsp;${bidPlanDetail.CONT_DECD_NM}</c:if>
		            </td>
		        </tr>
		        <tr>
		            <th scope="row">입찰공고명</th>
		            <td colspan="3">
                        <c:if test="${bidPlanDetail.EMRG_YN eq 'Y'}"><font color="red">[긴급] </font></c:if>
						<c:if test="${bidPlanDetail.ANNC_NGR > 1}"><font color="red">[정정] </font></c:if>
						<c:if test="${not empty bidPlanDetail.BF_ANNC_NO}"><font color="red">[재공고] </font></c:if>
						${bidPlanDetail.BID_NM}
		            </td>
		        </tr>
		        <tr>
		            <th scope="row">제안서(규격서)필수여부</th>
		            <td>${bidPlanDetail.PRPDC_ESS_YN_NM}</td>
		            <th scope="row">G2B동시게시여부</th>
		            <td>${bidPlanDetail.G2B_NTFY_YN_NM}</td>
				</tr>
				<tr class="emrgDiv" <c:if test="${bidPlanDetail.EMRG_YN ne 'Y'}"> style="display: none;"</c:if>>
		            <th scope="row">긴급입찰사유</th>
		            <td colspan="3">${bidPlanDetail.EMRG_BID_RSN}</td>
		        </tr>
		        <tr>
                    <th scope="row">계약기간(TEXT)</th>
                    <td>${bidPlanDetail.CONT_TE}</td>
		        	<th scope="row">계약기간</th>
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
                    <th scope="row">SW사업대상</th>
					<td colspan="3">
						<c:if test="${bidPlanDetail.SW_BSNS_OBJ_YN ne 'Y'}">아니오</c:if>
                    	<c:if test="${bidPlanDetail.SW_BSNS_OBJ_YN eq 'Y'}">예</c:if>
					</td>
                </tr>                
                <c:if test="${bidPlanDetail.CONT_SECD eq '4'}">
                <tr>
                    <th scope="row">통화</th>
					<td>${bidPlanDetail.CURR_SECD_NM}</td>
                    <th scope="row">환율</th>
					<td>${comFn:formatMoney(bidPlanDetail.EXRT)}</td>
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
		            <th scope="row">계약방법</th>
		            <td colspan="3">${bidPlanDetail.CONT_MTCD_NM}</td>
		        </tr>
		        <tr>
		        	<th scope="row">낙찰자선정방법</th>
		            <td>${bidPlanDetail.SBID_MTCD_NM}</td>
					<th scope="row">
						<%-- 낙찰자선정방식이 [적격심사]가 아닐 경우 활성화 --%>
						<label class="elgbDiv" <c:if test="${bidPlanDetail.SBID_MTCD ne '31' and bidPlanDetail.SBID_MTCD ne '20'}">style="display: none;"</c:if>>낙찰하한율 (%)</label>
						<%-- 낙찰자선정방식이 [적격심사]가 아닐 경우 활성화 End--%>
						<%-- 낙찰자선정방식이 [협상에의한계약]이 아닐 경우 활성화 --%>
						<label class="negoDiv" <c:if test="${bidPlanDetail.SBID_MTCD ne '40'}">style="display: none;"</c:if>>합산비율</label>
						<%-- 낙찰자선정방식이 [협상에의한계약]이 아닐 경우 활성화 End--%>
					</th>
					<td>
	                    <div class="elgbDiv" <c:if test="${bidPlanDetail.SBID_MTCD ne '31' and bidPlanDetail.SBID_MTCD ne '20'}"> style="display:none;"</c:if>>
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
			        	<th scope="row">적격심사종류</th>
			        	<td>${bidPlanDetail.ELGB_ESTM_KDCD_NM}</td>
			        	<th scope="row">적격심사통과점수</th>
			        	<td>${bidPlanDetail.ELGB_LMT_SCR }</td>
			        </tr>
		        </c:if>
		        <tr>
		        	<th scope="row">예가방식</th>
		            <td>${bidPlanDetail.ESTPC_SECD_NM}</td>
		        	<th scope="row"><label <c:if test="${bidPlanDetail.ESTPC_SECD eq '180002'}"> style="display:none;"</c:if>>기초금액 (원)</label></th>
		        	<td>
		        		<c:if test="${bidPlanDetail.ESTPC_SECD ne '180002'}">
		        			${comFn:formatMoney(bidPlanDetail.BASE_AMT)}
		        		</c:if>
		        	</td>
		        </tr>
		        <c:if test="${bidPlanDetail.ESTPC_SECD eq '180000'}">
		        <tr>
		        	<th scope="row">복수예비가격범위</th>
		            <td colspan="3">${bidPlanDetail.PLR_ESTPC_RNG_CD_NM}</td>
		        </tr>
		        </c:if>
		        <tr>    
		            <th scope="row">입찰설명회여부</th>
		            <td>${bidPlanDetail.BID_BRFS_YN_NM}</td>
		            <%-- 입찰설명회 [예] 일 경우 활성화 --%>
		            <th scope="row"><label class="brfsDiv" <c:if test="${bidPlanDetail.BID_BRFS_YN ne 'Y'}">style="display:none;"</c:if>>입찰설명회필수참석여부</label></th>
		            <td>
		            	<div class="brfsDiv" <c:if test="${bidPlanDetail.BID_BRFS_YN ne 'Y'}">style="display:none;"</c:if>>
		            		${bidPlanDetail.BID_BRFS_ATND_YN_NM}
		            	</div>
		            </td>
				</tr>
				<tr class="brfsDiv" <c:if test="${bidPlanDetail.BID_BRFS_YN ne 'Y'}">style="display:none;"</c:if>>    
		            <th scope="row">입찰설명회장소</th> 
                    <td colspan="3">
                        ${bidPlanDetail.BRFS_PLC_NM}
                    </td>
				</tr>
				<%-- 입찰설명회 [예] 일 경우 활성화 END--%>
				<%-- <tr class="ctrcDiv" <c:if test="${bidPlanDetail.CONT_SECD ne '2'}">style="display:none;"</c:if>>    
		            <th scope="row">현장설명회여부</th>
		            <td>${bidPlanDetail.SITE_BRFS_YN_NM}</td>
		            현장설명회 [예] 일 경우 활성화
		            <th scope="row"><label class="ctrcDiv siteBrfsDiv" <c:if test="${bidPlanDetail.SITE_BRFS_YN ne 'Y'}">style="display:none;"</c:if>>현장설명회일시</label></th>
		            <td>
		            	<div class="ctrcDiv siteBrfsDiv" <c:if test="${bidPlanDetail.SITE_BRFS_YN ne 'Y'}">style="display:none;"</c:if>>
			            	${comFn:formatDate(bidPlanDetail.SITE_BRFS_DT,'yyyyMMddHHmmss','yyyy-MM-dd HH:mm')}
                        </div>
		            </td>
				</tr>
				<tr class="ctrcDiv siteBrfsDiv" <c:if test="${bidPlanDetail.CONT_SECD ne '2' and bidPlanDetail.SITE_BRFS_YN ne 'Y'}">style="display:none;"</c:if>>    
		            <th scope="row">현장설명회장소</th> 
                    <td colspan="3">
                        ${bidPlanDetail.SITE_BRFS_PLC}
                    </td>
				</tr> --%>
				<%-- 현장설명회 [예] 일 경우 활성화 END--%>
				
				<tr>
                    <th scope="row">입찰보증률</th>
                    <td>${bidPlanDetail.BID_GTAMT_RT}</td>
                    <th scope="row">입찰보증금</th>
                    <td>${comFn:formatMoney(bidPlanDetail.BIDGR_AMT)}</td>
                </tr>
                <tr>
                    <th scope="row">입찰참가자격</th>
                    <td colspan="3">
                        <textarea taView style="display: none;">${bidPlanDetail.PRTC_QLF_CNTN}</textarea>
                    </td>
                </tr>
                <tr>
                	<th scope="row">공동수급</th>
                    <td>${bidPlanDetail.ASSO_SPDM_CD_NM}</td>
                    <th scope="row">동가입찰옵션</th>
                    <td>${bidPlanDetail.SMPR_BID_SECD_NM}</td>
                </tr>
		    </table>
		</div>
		
		<%-- 정정공고 사유가 등록되어 있을 경우 활성화 --%>
		<c:if test="${not empty pblancPf30HistList}">
        	<div class="tit_area">
        		<h4 class="tit" style="clear: both;">정정공고</h4>
	        </div>
	        <div class="view_area">
	            <table class="tableList">
	                <caption>정정공고</caption>
	                <colgroup>
		        	<col style="width: 15%;">						        
		        	<col style="width: 85%;">						        
		            </colgroup>
					<thead>
		                <tr>
		                    <th class="txtc">등록일자</th>
		                    <th class="txtc">정정공고사유</th>
		                </tr>
		            </thead>
					<tbody>
						<c:forEach var="data" items="${pblancPf30HistList}" varStatus="status">
							<c:if test="${status.count eq 1 }">
								<tr>
									<td class="txtc">${comFn:formatDate(data.PROC_DT,'yyyyMMddHHmmss','yyyy-MM-dd')}</td>
									<td>
										<label  class="blind">정정공고사유</label>
										<textarea id="P_NTCN_RESN_CN" readonly taView style="display: none;">${data.PROC_RSN}</textarea>
									</td>
								</tr>
							</c:if>
						</c:forEach>
					</tbody>
	            </table>
	        </div>
        </c:if>
		
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
		                    <th scope="col" class="txtc">사업자번호</th>
		                    <th scope="col" class="txtc">업체명</th>
		                    <th scope="col" class="txtc">대표자명</th>
		                </tr>
	                </thead>
	                <tbody id="nmenChoiseShowTbdy">
	                	<c:forEach var="data" items="${bidNmfpcEntrpsList}" varStatus="status">
							<tr class="row">
								<td class="txtc">${comFn:formatBizNumber(data.BIZRNO) }</td>
								<td>${data.VEND_NM}</td>
								<td class="txtc">${data.RPRS_NM}</td>
							</tr>
						</c:forEach>
	                </tbody>
	                <tbody>
		               	<tr class="row" id="nmenChoiseEmpty" <c:if test="${not empty bidNmfpcEntrpsList}"> style="display:none;"</c:if>>
		               		<td colspan="3"  class="txtc">선택된 업체가 없습니다.</td>
		               	</tr>
	               	</tbody>
	            </table>
	        </div>
        </div>
        <%-- 계약방법이 [지명경쟁] 일 경우 활성화 End --%>
        
        <%-- 계약방법이 [제한경쟁] 일 경우 활성화 --%>
        <div class="lmtDiv" <c:if test="${bidPlanDetail.CONT_MTCD ne '10002'}"> style="display: none;"</c:if>>
        	<div class="tit_area">
				<h4 class="tit">투찰제한</h4>
			</div>
        	<div class="view_area">
				<table>
					<caption>투찰제한</caption>
			    	<colgroup>
			        	<col style="width: 15%;">						        
			        	<col style="width: 35%;">						        
			        	<col style="width: 15%;">						        
			        	<col style="width: 35%;">	
			        </colgroup>
			        <tr>
			            <th scope="row">지역제한</th>
			            <td>${bidPlanDetail.ARA_LMT_CD_NM}</td>
			            <th scope="row">업종제한</th>
			            <td>${bidPlanDetail.BTP_LMT_CD_NM}</td>
			        </tr>
				</table>
			</div>
		</div>
        <%-- 계약방법이 [제한경쟁] 일 경우 활성화 End --%>
        
        <br>
        
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
                    <th scope="row">지체상금률 (%)</th>
                    <td>${bidPlanDetail.CPDF_RT} /1,000</td>
                    <th scope="row">계약보증금률 (%)</th>
                    <td>${bidPlanDetail.CTFL_GTAMT_RT}</td>
                </tr>
                <tr>
                    <th scope="row">하자이행보증금률 (%)</th>
                    <td>${bidPlanDetail.DFFL_GTAMT_RT}</td>
                    <%-- 계약구분이 [구매] 일 경우 활성화 --%>
                    <th scope="row"><label class="buyDiv" <c:if test="${bidPlanDetail.CONT_SECD ne '0' and bidPlanDetail.CONT_SECD ne '4'}"> style="display: none;"</c:if>>물품인도조건</label></th>
                    <td>
                    	<div class="buyDiv" <c:if test="${bidPlanDetail.CONT_SECD ne '0' and bidPlanDetail.CONT_SECD ne '4'}">style="display: none;"</c:if>>
	            			${bidPlanDetail.ITEM_DRCD_NM}
	            		</div>
                    </td>
                </tr>
                <tr class="buyDiv" <c:if test="${bidPlanDetail.CONT_SECD ne '0' and bidPlanDetail.CONT_SECD ne '4'}"> style="display: none;"</c:if>>
                    <th scope="row">납품장소</th>
                    <td colspan="3">
                        <textarea taView style="display: none;">${bidPlanDetail.DLGD_PLC_NM}</textarea>
                    </td>
                </tr>
                <tr class="buyDiv" <c:if test="${bidPlanDetail.CONT_SECD ne '0' and bidPlanDetail.CONT_SECD ne '4'}"> style="display: none;"</c:if>>
                    <th scope="row">납품기한</th>
                    <td colspan="3">
                    	<textarea taView style="display: none;">${bidPlanDetail.DLGD_TE_CNTN}</textarea>
                    </td>
                </tr>
                <tr>
                    <th scope="row">비고</th>
                    <td colspan="3">
                    	<textarea taView style="display: none;">${bidPlanDetail.RMK}</textarea>
                    </td>
                </tr>                
                <%-- 계약구분이 [구매] 일 경우 활성화 END --%>
                </tbody>
            </table>
        </div>
        
		<div class="tit_area">
			<h4 class="tit">입찰진행순서</h4>
		</div>
		<div class="view_area">
            <table>
                <caption>입찰진행순서</caption>
                <colgroup>
		        	<col style="width: 15%;">						        
		        	<col style="width: 85%;">	
                </colgroup>
                <tbody>
                <tr class="line">
                    <th scope="row">입찰공고게시일시</th>
                    <td>${comFn:formatDate(bidPlanDetail.ANNC_DT,'yyyyMMddHHmmss','yyyy-MM-dd HH:mm')}</td>
                </tr>
                <%-- 입찰설명회 여부가 [예] 일 경우에 활성화 --%>
                <c:if test="${bidPlanDetail.ROUND_NO < 2}">
	                <tr class="brfsDiv" <c:if test="${bidPlanDetail.BID_BRFS_YN ne 'Y'}"> style="display:none;"</c:if>>
	                    <th scope="row">입찰설명회일시</th>
	                    <td>${comFn:formatDate(bidPlanDetail.BRFS_DT,'yyyyMMddHHmmss','yyyy-MM-dd HH:mm')}</td>
	                </tr>
	                <c:if test="${bidPlanDetail.PRPDC_ESS_YN eq 'Y'}">
		                <tr>
		                    <th scope="row">제안서제출기간</th>
		                    <td>
			                    ${comFn:formatDate(bidPlanDetail.PRPDC_SBMT_STDT,'yyyyMMddHHmmss','yyyy-MM-dd HH:mm')}
		                        <span class="wave">~</span>
		                        ${comFn:formatDate(bidPlanDetail.PRPDC_SBMT_ENDT,'yyyyMMddHHmmss','yyyy-MM-dd HH:mm')}
		                    </td>
		                </tr>
	                </c:if>
                </c:if>                
                <%-- 입찰설명회 여부가 [예] 일 경우에 활성화 End --%>
                <c:if test="${bidPlanDetail.SBID_MTCD ne '34' or ( bidPlanDetail.SBID_MTCD eq '34' and not empty bidPlanDetail.BIDC_SBMT_ENDT )}">
	                <tr>
	                    <th scope="row">입찰서제출기간</th>
	                    <td>
	                    	${comFn:formatDate(bidPlanDetail.BIDC_SBMT_STDT,'yyyyMMddHHmmss','yyyy-MM-dd HH:mm')}
	                        <span class="wave">~</span>
	                        ${comFn:formatDate(bidPlanDetail.BIDC_SBMT_ENDT,'yyyyMMddHHmmss','yyyy-MM-dd HH:mm')}
	                    </td>
	                </tr>
                </c:if>
                <c:if test="${bidPlanDetail.SBID_MTCD ne '34' or ( bidPlanDetail.SBID_MTCD eq '34' and not empty bidPlanDetail.BIDC_SBMT_ENDT )}">
	                <tr>
	                    <th scope="row">개찰일시</th>
	                    <td>${comFn:formatDate(bidPlanDetail.OPNG_DT,'yyyyMMddHHmmss','yyyy-MM-dd HH:mm')}</td>
	                </tr>
                </c:if>
                <c:if test="${bidPlanDetail.ANNC_SECD eq 'HNDW_BID' }">
	                <tr>
	                    <th scope="row">개찰장소</th>
	                    <td>${bidPlanDetail.OPNG_PLC_NM }</td>
					</tr>
				</c:if>
                </tbody>
            </table>
        </div>
        
        <div class="buyDiv">
        	<div class="tit_area">
		       	<h4 class="tit" style="clear: both;">입찰품목</h4>
	        </div>
			<div class="view_area" style="margin-bottom: 30px; overflow: auto;">
	            <table style="width:100%;">
	                <caption>입찰품목정보</caption>
	                <colgroup>
	                   	<col style="width: 10%;">
	                   	<col style="width: 10%;">
	                   	<col style="width: auto;">
	                   	<col style="width: 15%;">
	                   	<col style="width: 8%;">
	                   	<col style="width: 6%;">
	                   	<col style="width: 8%;">
	                   	<col style="width: 8%;">
	                </colgroup>
	                <thead>
		                <tr>
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
		               		<td colspan="8" class="txtc">입찰품목이 없습니다.</td>
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
	    	<c:if test="${bidPlanDetail.BID_PSCD eq 'PF02' }">
		    	<button type="button" class="btn btn_m btn_orange" id="ntfyBtn">공고게시</button>
	            <button type="button" class="btn btn_m btn_orange" id="updateBtn">수정</button>
            	<button type="button" class="btn btn_m btn_orange" id="deleteBtn">삭제</button>
	    	</c:if>
	    	<c:if test="${bidPlanDetail.BID_PSCD eq 'PF20' or bidPlanDetail.BID_PSCD eq 'PF30' }">
	    	<button type="button" class="btn btn_m btn_orange" id="canclPblancBtn">취소공고</button>
	    	</c:if>
			<c:if test="${bidPlanDetail.BIDC_SBMT_STDT > toDay }">
		    	<c:if test="${bidPlanDetail.BID_PSCD eq 'PF20' or bidPlanDetail.BID_PSCD eq 'PF30' }">
	    			<button type="button" class="btn btn_m btn_orange" id="updtPblancBtn">정정공고</button>
					<c:if test="${bidPlanDetail.BID_PSCD eq 'PF41'}">
						<button type="button" class="btn btn_m btn_orange" id="canclPblancDelBtn">취소공고 삭제</button>
					</c:if>
		    	</c:if>
		    </c:if>
		    	
			<c:if test="${bidPlanDetail.BID_PSCD eq 'PF30'}">
				<c:if test="${VEND_TNDR_YN eq 'Y'}">
		    		<button type="button" class="btn btn_m btn_orange" id="vendBtn">참여업체</button>
		    	</c:if>		    	
	    	</c:if>
	    	<button type="button" class="btn btn_m btn_del" id="listBtn">목록</button>
	    </div>
   	</div>
</div>

<form id="listFrm" method="POST">
	<input type="hidden" name="resourceName" value="${param.resourceName}">
</form>

<form id="updtFrm" method="POST">
	<input type="hidden" name="resourceName" value="${param.resourceName}">
	<input type="hidden" name="P_ANNC_NO" value="${bidPlanDetail.ANNC_NO}">
	<input type="hidden" name="P_ANNC_NGR" value="${bidPlanDetail.ANNC_NGR}">
	<input type="hidden" name="P_ROUND_NO" value="${bidPlanDetail.ROUND_NO}">
	<input type="hidden" name="P_PROV_SN" value="${bidPlanDetail.PROV_SN}">
	<input type="hidden" name="P_APPR_YN">
</form>

<form id="sttusFrm" method="POST">
	<input type="hidden" name="resourceName" value="${param.resourceName}">
	<input type="hidden" name="P_ANNC_NO" value="${bidPlanDetail.ANNC_NO}">
	<input type="hidden" name="P_ANNC_NGR" value="${bidPlanDetail.ANNC_NGR}">
	<input type="hidden" name="P_ROUND_NO" value="${bidPlanDetail.ROUND_NO}">
	<input type="hidden" name="P_BID_PSCD">
	<input type="hidden" name="P_APPR_STCD">
</form>

<form id="canclpblancPopupFrm" method="POST">
	<input type="hidden" name="P_ANNC_NO" value="${bidPlanDetail.ANNC_NO}">
	<input type="hidden" name="P_ANNC_NGR" value="${bidPlanDetail.ANNC_NGR}">
	<input type="hidden" name="P_ROUND_NO" value="${bidPlanDetail.ROUND_NO}">
</form>

<form id="roundPopupFrm" method="POST">
	<input type="hidden" name="P_ANNC_NO" value="${bidPlanDetail.ANNC_NO}">
	<input type="hidden" name="P_ANNC_NGR" value="${bidPlanDetail.ANNC_NGR}">
	<input type="hidden" name="P_ROUND_NO" value="${bidPlanDetail.ROUND_NO}">
</form>

<form id="delFrm" method="POST">
	<input type="hidden" name="P_ANNC_NO" value="${bidPlanDetail.ANNC_NO}">
	<input type="hidden" name="P_ANNC_NGR" value="${bidPlanDetail.ANNC_NGR}">
	<input type="hidden" name="P_PROV_SN" value="${bidPlanDetail.PROV_SN}">
</form>

<form id="vendPopupFrm" method="POST">
	<input type="hidden" name="P_ANNC_NO" value="${bidPlanDetail.ANNC_NO}">
	<input type="hidden" name="P_ANNC_NGR" value="${bidPlanDetail.ANNC_NGR}">
	<input type="hidden" name="P_ROUND_NO" value="${bidPlanDetail.ROUND_NO}">
	<input type="hidden" name="P_BID_PSCD" value="${bidPlanDetail.BID_PSCD}">
</form>

<form id="bidNotiRegistPopupFrm" method="POST">
	<input type="hidden" name="P_ANNC_NO" value="${bidPlanDetail.ANNC_NO}">
	<input type="hidden" name="P_ANNC_NGR" value="${bidPlanDetail.ANNC_NGR}">
	<input type="hidden" name="P_ROUND_NO" value="${bidPlanDetail.ROUND_NO}">
	<input type="hidden" name="P_OPNN_SN">
</form>

<form id="apprCrrcFrm" method="POST">
	<input type="hidden" name="P_ANNC_NO" value="${bidPlanDetail.ANNC_NO}">
	<input type="hidden" name="P_ANNC_NGR" value="${bidPlanDetail.ANNC_NGR}">
	<input type="hidden" name="P_ROUND_NO" value="${bidPlanDetail.ROUND_NO}">
	<input type="hidden" name="P_INTERFACE_ID" value="${biApprCntcB.APPR_CNTC_KEY}">
	<input type="hidden" name="P_STATUS" value="${biApprCntcB.APPR_STCD}">
	<input type="hidden" name="P_DRAFT_SABUN" value="${SABUN}">
</form>

<form id="apprCnclFrm" method="POST">
	<input type="hidden" name="P_ANNC_NO" value="${bidPlanDetail.ANNC_NO}">
	<input type="hidden" name="P_ANNC_NGR" value="${bidPlanDetail.ANNC_NGR}">
	<input type="hidden" name="P_ROUND_NO" value="${bidPlanDetail.ROUND_NO}">
	<input type="hidden" name="P_INTERFACE_ID" value="${biApprCntcC.APPR_CNTC_KEY}">
	<input type="hidden" name="P_STATUS" value="${biApprCntcC.APPR_STCD}">
	<input type="hidden" name="P_DRAFT_SABUN" value="${SABUN}">
</form>

<form id="bidWrtancDetailPopupFrm" method="POST">
	<input type="hidden" name="P_ANNC_NO" value="${bidPlanDetail.ANNC_NO}">
	<input type="hidden" name="P_ANNC_NGR" value="${bidPlanDetail.ANNC_NGR}">
	<input type="hidden" name="P_ROUND_NO" value="${bidPlanDetail.ROUND_NO}">
</form>
<%-- 파일 VIEW 폼 --%>
<form id="fileViewFrm" method="POST">
	<input type="hidden" id="P_FILE_GRP_NO" name="P_FILE_GRP_NO" value="${bidPlanDetail.FILE_GRP_NO}">
</form>