<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 입찰관리 > 개찰대기현황 상세
 *
 * <pre>
 * ebid 
 *    |_ opengManageDetail.jsp
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

<script type="text/javascript" src="${jsPath}/ipro/views/ebid/opengManageDetail.js"></script> 
 
<input type="hidden" id="P_ESTPC_SECD" value="${bidPlanDetail.ESTPC_SECD}">
<input type="hidden" id="P_SBID_MTCD" value="${bidPlanDetail.SBID_MTCD}">
<input type="hidden" id="P_BID_MTCD" value="${bidPlanDetail.BID_MTCD}">
<input type="hidden" id="errMsg" value="${msg}">
 
<div class="contents_wrap">
	<ul class="step_wrap">
		<li><a href="#">${myMenuList.bigMenuNm}</a></li>
		<li><a href="#">${myMenuSubList.smallMenuNm}</a></li>
	</ul>
	<div class="tit_wrap">
		<h3 class="tit">개찰대기현황 상세</h3>
	</div>

	<div class="view_wrap typeB">
		<div	class="tit_area">
			<h4 class="tit">입찰개요</h4>
			<div	class="btn_right">
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
						<c:if test="${empty bidPlanDetail.BFAN_NO_LIST}">사전공고번호가 없습니다.</c:if>
						<c:if test="${not empty bidPlanDetail.BFAN_NO_LIST}">${bidPlanDetail.BFAN_NO_LIST }</c:if>
					</td>
					<th>공고번호</th>
					<td>${bidPlanDetail.ANNC_NO}-${bidPlanDetail.ANNC_NGR}</td>
	            </tr>
	            <tr>
		            <th>입찰명</th>
		            <td colspan="3">
		            	<c:if test="${bidPlanDetail.EMRG_YN eq 'Y'}"><font color="red">[긴급] </font></c:if>
						<c:if test="${bidPlanDetail.ANNC_NGR > 1}"><font color="red">[정정] </font></c:if>
						${bidPlanDetail.BID_NM}
					</td>
		        </tr>
		    	<tr>
		            <th>계약구분</th>
	                <td <c:if test="${bidPlanDetail.SBID_MTCD eq '70'}">colspan="3"</c:if>>
	                    ${bidPlanDetail.CONT_SECD_NM}<c:if test="${bidPlanDetail.UPRC_YN eq 'Y'}">(단가)</c:if>
	                    <c:if test="${bidPlanDetail.CONT_DECD eq '200'}">&nbsp;/&nbsp;${bidPlanDetail.CONT_DECD_NM}</c:if>
	                </td>
	                <c:if test="${bidPlanDetail.SBID_MTCD ne '70'}">
	                <th>공고일자</th>
	                <td>${comFn:formatDate(bidPlanDetail.ANNC_DT,'yyyyMMddHHmmss','yyyy-MM-dd')}</td>
	                </c:if>
		        </tr>
		        <tr>
		        	<th>계약방법</th>
		            <td>${bidPlanDetail.CONT_MTCD_NM}</td>
		            <th>낙찰자선정방법</th>
		            <td>${bidPlanDetail.SBID_MTCD_NM}</td>
		        </tr>
		        <tr>    
		            <th>예가구분</th>
		            <td>${bidPlanDetail.ESTPC_SECD_NM}</td>
		            <th>기초금액 (원)</th>
		            <td>${comFn:formatMoney(prdprcInfo.BASE_ESTPC_AMT)}</td>		            
		        </tr>
		        <tr>
		            <th>추정금액 (원)</th>
		            <td>${comFn:formatMoney(bidPlanDetail.ESTT_AMT)}</td>
		            <th>추정가격 (원)</th>
		            <td>${comFn:formatMoney(bidPlanDetail.ESTT_PRCE)}</td>
		        </tr>
		    </table>
		</div>
		
		<div	class="tit_area">
			<h4 class="tit">입찰진행순서</h4>
		</div>
		<div class="view_area">
			<table>
		    	<colgroup>
		        	<col style="width: 15%;">						        
		        	<col style="width: 85%;">						        
		        </colgroup>
		        <tr>
		            <th>입찰공고게시일시</th>
		            <td>${comFn:formatDate(bidPlanDetail.ANNC_DT,'yyyyMMddHHmmss','yyyy-MM-dd HH:mm')}</td>
		        </tr>
		        <%-- 입찰설명회 [예(의무), (비의무)] 일경우 활성화 --%>
	                <c:if test="${bidPlanDetail.BID_BRFS_YN eq 'Y'}">
	                <tr>
	                    <th scope="row">입찰설명회 일시</th>
	                    <%-- 입찰설명회 [예(의무) 일 경우에 필수 예(비의무) 일 경우에 없음 활성화 --%>
	                    <td>
	                    	${comFn:formatDate(bidPlanDetail.BRFS_DT,'yyyyMMddHHmmss','yyyy-MM-dd HH:mm')} ( 참석의무 <c:if test="${bidPlanDetail.BID_BRFS_ATND_YN eq 'N'}">필수아님</c:if><c:if test="${bidPlanDetail.BID_BRFS_ATND_YN eq 'Y'}">필수</c:if> ) 
<!-- 		                    <font color="red">※입찰설명회 참석시간 엄수</font> -->
	                    </td>
	                </tr>
	                </c:if>
		        <c:if test="${bidPlanDetail.PRPDC_ESS_YN eq 'Y'}">
			        <tr>
			            <th>제안서제출기간</th>
			            <td>${comFn:formatDate(bidPlanDetail.PRPDC_SBMT_STDT,'yyyyMMddHHmmss','yyyy-MM-dd HH:mm')} ~ ${comFn:formatDate(bidPlanDetail.PRPDC_SBMT_ENDT,'yyyyMMddHHmmss','yyyy-MM-dd HH:mm')}</td>
			        </tr>
		        </c:if>	                
		        <tr>
		            <th>입찰서제출기간</th>
		            <td>${comFn:formatDate(bidPlanDetail.BIDC_SBMT_STDT,'yyyyMMddHHmmss','yyyy-MM-dd HH:mm')} ~ ${comFn:formatDate(bidPlanDetail.BIDC_SBMT_ENDT,'yyyyMMddHHmmss','yyyy-MM-dd HH:mm')}</td>
		        </tr>
		        <tr>
		            <th>개찰일시</th>
		            <td>${comFn:formatDate(bidPlanDetail.OPNG_DT,'yyyyMMddHHmmss','yyyy-MM-dd HH:mm')}</td>
		        </tr>
		    </table>
		</div>
		
		<div	class="tit_area">
			<h4 class="tit">투찰업체</h4>
		</div>
		<div class="view_area">
			<input type="hidden" id="P_ESTPC_SECD" value="${bidPlanDetail.ESTPC_SECD}">
			
			<form id="tchnFrm" method="POST">
				<input type="hidden" name="resourceName" value="${param.resourceName}">
				<input type="hidden" name="P_ANNC_NO" value="${bidPlanDetail.ANNC_NO}">
			   	<input type="hidden" name="P_ANNC_NGR" value="${bidPlanDetail.ANNC_NGR}">
			   	<input type="hidden" name="P_ROUND_NO" value="${bidPlanDetail.ROUND_NO}">
			   	<input type="hidden" id="P_TCHN_SCR_RT" name="P_TCHN_SCR_RT" value="${bidPlanDetail.TCHN_SCR_RT}">
			   	
				<table>
					<caption>투찰업체</caption>
					<colgroup>
						<c:if test="${bidPlanDetail.SBID_MTCD eq '10' || bidPlanDetail.SBID_MTCD eq '31'}">
		                   	<col style="width: 20%;">
		                   	<col style="width: 15%;">
		                   	<col style="width: 15%;">
		                   	<col style="width: 20%;">
		                   	<col style="width: *">
	                   	</c:if>
	                   	<c:if test="${bidPlanDetail.SBID_MTCD eq '40' && empty tchnCompAt}">
		                   	<col style="width: 15%;">
		                   	<col style="width: 10%;">
		                   	<col style="width: 10%;">
		                   	<col style="width: 15%;">
		                   	<col style="width: *">
		                   	<col style="width: 10%;">
	                   	</c:if>
	            	</colgroup>
	            	<thead>
				    	<tr>
				            <th class="txtc">업체명</th>
				            <th class="txtc">사업자번호</th>
				            <th class="txtc">대표자명</th>
				            <th class="txtc">투찰일시</th>
				            <th class="txtc">사전판단</th>
				            <c:if test="${bidPlanDetail.SBID_MTCD eq '40'}">
				            	<th class="txtc">기술점수<br>(환산)</th>
				            </c:if>
				        </tr>
					</thead>
					<tbody id="nmenChoiseShowTbdy">
						<c:if test="${empty bidPartcptEntrpsList}">
							<tr>
								<c:if test="${bidPlanDetail.BID_MTCD ne 'OFF'}">
									<c:if test="${bidPlanDetail.SBID_MTCD eq '10' || bidPlanDetail.SBID_MTCD eq '31'}">
										<td colspan="5" class="txtc">참여한 업체 정보가 없습니다.</td>
									</c:if>
									<c:if test="${bidPlanDetail.SBID_MTCD eq '34' || bidPlanDetail.SBID_MTCD eq '35'}">
										<td colspan="5" class="txtc">참여한 업체 정보가 없습니다.</td>
									</c:if>
									<c:if test="${bidPlanDetail.SBID_MTCD eq '40'}">
										<td colspan="6" class="txtc">참여한 업체 정보가 없습니다.</td>
									</c:if>
								</c:if>
							</tr>
						</c:if>
						<c:if test="${not empty bidPartcptEntrpsList}">
							<c:forEach var="data" items="${bidPartcptEntrpsList}" varStatus="status">
								<tr>
									<td>
										${data.VEND_NM}
										<input type="hidden" name="P_VEND_REG_NO" value="${data.VEND_REG_NO}">
									</td>
									<td class="txtc">${comFn:formatBizNumber(data.BIZRNO)}</td>
									<td class="txtc">${data.RPRS_NM}</td>
									<td class="txtc">${comFn:formatDate(data.SIGN_DT,'yyyyMMddHHmmss','yyyy-MM-dd HH:mm')}</td>
									<td class="txtc">
										<input type="hidden" name="P_BID_ELCD_LIST" value="${data.BID_ELCD}">
										<c:if test="${empty data.BID_ELCD_NM}">
											검토전
										</c:if>
										<c:if test="${not empty data.BID_ELCD_NM}">
											${data.BID_ELCD_NM}
										</c:if>
									</td>
									<c:if test="${bidPlanDetail.SBID_MTCD eq '40' }"><!-- 협상에 의한 계약일 경우 -->
										<input type="hidden" name="P_ESTM_SCR" value="${data.ESTM_SCR }">
										<fmt:formatNumber var="ESTM_SCR_FMT" pattern="#.##" value="${data.ESTM_SCR * bidPlanDetail.TCHN_SCR_RT}" /><!-- 평가점수 * 기술점수율 -->
										<fmt:formatNumber var="ESTM_SCR_SUM" pattern="#.##" value="${ESTM_SCR_FMT/10}" />
										<td class="txtc">
											<%-- ${ESTM_SCR_SUM} --%>
											<span id="ESTM_SCR_SUM${status.count}"></span>
										</td>
									</c:if>
								</tr>
							</c:forEach>
						</c:if>
					</tbody>
			    </table>
		    </form>
	    </div>
	    
		<div class="btn_wrap">
			<%-- 개찰여부가 Y가 아니면 활성화 --%>
        	<c:if test="${bidPlanDetail.OPNG_YN ne 'Y' }">

        		<c:if test="${(bidPlanDetail.SBID_MTCD eq '40' || bidPlanDetail.SBID_MTCD eq '34' || bidPlanDetail.SBID_MTCD eq '35') && not empty tchnCompAt}">
        			<c:if test="${bidPlanDetail.REGR_ID eq loginResult.USR_ID }">
						<button type="button" class="btn btn_m btn_orange" id="opengBtn">개찰</button>
					</c:if>
				</c:if>
				<c:if test="${bidPlanDetail.SBID_MTCD eq '10' || bidPlanDetail.SBID_MTCD eq '31' || bidPlanDetail.SBID_MTCD eq '20' || bidPlanDetail.SBID_MTCD eq '33' || bidPlanDetail.SBID_MTCD eq '60'}">
					<c:if test="${bidPlanDetail.REGR_ID eq loginResult.USR_ID }">
						<button type="button" class="btn btn_m btn_orange" id="opengBtn">개찰</button>
					</c:if>
				</c:if>
				<c:if test="${bidPlanDetail.REGR_ID eq loginResult.USR_ID }">
					<button type="button" class="btn btn_m btn_orange" id="fibBtn">유찰</button>
					<!-- 2019-08-12 은잔디 추가 : 개찰대기현황 재입찰 버튼 삭제 -->
					<%-- <c:if test="${NOT_ELCD_EXT_YN eq 'N'}">
						<button type="button" class="btn btn_m btn_orange" id="reBidBtn">재입찰</button>
					</c:if> --%>
				</c:if>
			</c:if>
	    	<button type="button" class="btn btn_m btn_del" id="listBtn">목록</button>
	    </div>
	</div>
</div>	
	
<form id="listFrm" method="POST">
	<input type="hidden" name="resourceName" value="${param.resourceName}">
</form>
<form id="detailFrm" method="POST">
	<input type="hidden" name="resourceName" value="${param.resourceName}">
	<input type="hidden" name="P_ANNC_NO" value="${bidPlanDetail.ANNC_NO}">
	<input type="hidden" name="P_ANNC_NGR" value="${bidPlanDetail.ANNC_NGR}">
	<input type="hidden" name="P_ROUND_NO" value="${bidPlanDetail.ROUND_NO}">
</form>
<form id="opengFrm" method="POST">
	<input type="hidden" name="resourceName" value="ipm307">
	<input type="hidden" name="P_ANNC_NO" value="${bidPlanDetail.ANNC_NO}">
	<input type="hidden" name="P_ANNC_NGR" value="${bidPlanDetail.ANNC_NGR}">
	<input type="hidden" name="P_ROUND_NO" value="${bidPlanDetail.ROUND_NO}">
	<input type="hidden" name="P_IN_YN">
</form>
<form id="bidWrtancDetailPopupFrm" method="POST">
   	<input type="hidden" name="P_ANNC_NO" value="${bidPlanDetail.ANNC_NO}">
   	<input type="hidden" name="P_ANNC_NGR" value="${bidPlanDetail.ANNC_NGR}">
   	<input type="hidden" name="P_ROUND_NO" value="${bidPlanDetail.ROUND_NO}">
</form>
<form id="fibFrm" method="POST">
   	<input type="hidden" name="P_ANNC_NO" value="${bidPlanDetail.ANNC_NO}">
   	<input type="hidden" name="P_ANNC_NGR" value="${bidPlanDetail.ANNC_NGR}">
   	<input type="hidden" name="P_ROUND_NO" value="${bidPlanDetail.ROUND_NO}">
</form>
<form id="popupFrm" method="post">
	<input type="hidden" name="P_ANNC_NO" value="${bidPlanDetail.ANNC_NO}">
   	<input type="hidden" name="P_ANNC_NGR" value="${bidPlanDetail.ANNC_NGR}">
   	<input type="hidden" name="P_ROUND_NO" value="${bidPlanDetail.ROUND_NO}">
   	<input type="hidden" name="P_VEND_REG_NO">
   	<input type="hidden" name="setMulti" value="Y">
</form>
<form id="downFrm" method="post">
	<input type="hidden" name="P_ZIP_FILE_NM" value="${bidPlanDetail.ANNC_NO}-${bidPlanDetail.ANNC_NGR}_">
	<input type="hidden" name="P_VEND_REG_NO">
   	<input type="hidden" name="P_FILE_GRP_NO">
</form>

<form id="apprFrm" method="POST">
	<input type="hidden" name="P_ANNC_NO" value="${bidPlanDetail.ANNC_NO}">
	<input type="hidden" name="P_ANNC_NGR" value="${bidPlanDetail.ANNC_NGR}">
	<input type="hidden" name="P_ROUND_NO" value="${bidPlanDetail.ROUND_NO}">
	<input type="hidden" name="P_INTERFACE_ID" value="${biApprCntc.APPR_CNTC_KEY}">
	<input type="hidden" name="P_STATUS" value="${biApprCntc.APPR_STCD}">
	<input type="hidden" name="P_DRAFT_SABUN" value="${SABUN}">
</form>