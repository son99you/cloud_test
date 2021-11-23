<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 입찰관리 >나의 참가신청 상세  (구매)
 *
 * <pre>
 * ebid 
 *    |_ myPartcptReqstSttusDetail.jsp
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

<script type="text/javascript" src="${jsPath}/opro/views/ebid/myPartcptReqstSttusDetail.js"></script>  
  
<ul class="step_wrap">
	<li><a href="#">경쟁입찰</a></li>
	<li><a href="#">나의 참가신청</a></li>
</ul> <!--// step_wrap E -->
<div class="tit_wrap">
	<h3 class="tit">나의 참가신청 상세</h3>
</div> <!--// tit_wrap E -->  

<input type="hidden" id="sevrTime" value="${serverTime}">
	<input type="hidden" id="fngprtBidAt" value="${myPartcptReqstSttusDetail.FNGPRT_BID_AT}">
	<input type="hidden" id="pblancDt" value="${myPartcptReqstSttusDetail.PBLANC_DT}"><%-- 공고일시 --%>
	<input type="hidden" id="bipaPresentnBeginDt" value="${myPartcptReqstSttusDetail.BIPA_PRESENTN_BEGIN_DT}"><%-- 입찰서 제출 시작일시 --%>
	<input type="hidden" id="bipaPresentnEndDt" value="${myPartcptReqstSttusDetail.BIPA_PRESENTN_END_DT}"><%-- 입찰서 제출 종료일시 --%>
	<input type="hidden" id="bidDcPeoDt" value="${myPartcptReqstSttusDetail.BID_DC_PEO_DT}"><%-- 일찰설명회 일시 --%>
	<input type="hidden" id="pareBeginDt" value="${myPartcptReqstSttusDetail.PARE_BEGIN_DT}"><%-- 참가신청시작일시 --%>
	<input type="hidden" id="pareEndDt" value="${myPartcptReqstSttusDetail.PARE_END_DT}"><%-- 참가신청종료일시 --%>
	<input type="hidden" id="opengDt" value="${myPartcptReqstSttusDetail.OPENG_DT}"><%-- 개찰일시 --%>
	<input type="hidden" id="peoRegistDt" value="${bidDcPeoAtndncInfo.REGIST_DT}"><%-- 입찰설명회 등록 시간 --%>
	<input type="hidden" id="dcPeoAtndncAt" <c:if test="${inProgrsBidPblancDetail.BID_DC_PEO_CD eq 'BPYY' and empty bidDcPeoAtndncInfo}"> value="Y"</c:if>><%-- 설명회 필수시 설명회참석 여부 --%>
	<input type="hidden" id="bidPartcptnSttus" <c:if test="${not empty bidPartcptnSttus}"> value="Y"</c:if>><%-- 참가신청 여부 --%>
	<input type="hidden" id="bddprSttus" <c:if test="${not empty bddprSttus}"> value="Y"</c:if>><%-- 투찰 여부 --%>
	<input type="hidden" id="registAt" value="${registAt }">
	<input type="hidden" id="hiddnButton" value="${hiddnButton}">
	<input type="hidden" id="P_COPERTN_SPLDMD_DUTY_SE_CD" value="${entrpsinfoinqire.COPERTN_SPLDMD_DUTY_SE_CD}">
		
	<c:if test="${sessionScope.loginResult.LOGIN_ID eq P_ENTRPS_REGIST_NO }">
		<input type="hidden" id="P_REPRSNT_ENTRPS_AT" value="Y">
	</c:if>
	<c:if test="${sessionScope.loginResult.LOGIN_ID ne P_ENTRPS_REGIST_NO }">
		<input type="hidden" id="P_REPRSNT_ENTRPS_AT" value="N">
	</c:if>	
	
	 <%-- 입찰진행상태가 공고중, 정정정고 일때만 활성화 --%>
    <c:if test="${myPartcptReqstSttusDetail.BID_PROGRS_STTUS_CD eq 'PF20' or myPartcptReqstSttusDetail.BID_PROGRS_STTUS_CD eq 'PF30' or myPartcptReqstSttusDetail.BID_PROGRS_STTUS_CD eq 'PF50' }">
       	<div class="processStep">
       		<%-- 입찰상세진행상태가 공고중이면 CSS 적용 --%>
            <div <c:if test="${fn:substring(myPartcptReqstSttusDetail.BID_DETAIL_PRST_CD,0,2)  eq 'PF'}">class='on'</c:if> id="biddingId">공고중</div>
       		<%-- 입찰상세진행상태가 공고중이면 CSS 적용 END --%>
            <img src="${imagePath}/opro/icon/ico_arrow.png" alt="다음 단계">
            
            <%-- 입찰설명회가 있을경우 활성화 --%>
            <c:if test="${myPartcptReqstSttusDetail.BID_DC_PEO_CD ne 'BPNN'}">
            	<%-- 업체 입찰설명회 참가 시 참여시간 표시 --%>
            	<c:if test="${not empty bidDcPeoAtndncInfo}">
	            <div class="line_3<c:if test="${fn:substring(myPartcptReqstSttusDetail.BID_DETAIL_PRST_CD,0,2)  eq 'PG'}"> on</c:if>" id="dcPeoId">
	                <span class="line_top">
	                <%-- 입찰설명회 의무 비의무 표시 --%>
	                	입찰설명회<c:if test="${myPartcptReqstSttusDetail.BID_DC_PEO_CD eq 'BPYY'}">(의무)</c:if><c:if test="${myPartcptReqstSttusDetail.BID_DC_PEO_CD eq 'BPYN'}">(비의무)</c:if>
	            	<%-- 입찰설명회 의무 비의무 표시 END --%>
	                </span>
	                <span class="f_normal">${comFn:formatDate(bidDcPeoAtndncInfo.REGIST_DT,'yyyyMMddHHmmss','yyyy-MM-dd')}<br>${comFn:formatDate(bidDcPeoAtndncInfo.REGIST_DT,'yyyyMMddHHmmss','HH : mm : ss')}</span>
	            </div>
	            </c:if>
            	<%-- 입찰설명회 참가 시 참여시간 표시 END --%>
            	<%-- 업체 입찰설명회 미참가시 --%>
            	<c:if test="${empty bidDcPeoAtndncInfo}">
		            <%-- 입찰설명회 의무 비의무 표시 --%>
		            <div <c:if test="${fn:substring(myPartcptReqstSttusDetail.BID_DETAIL_PRST_CD,0,2)  eq 'PG'}">class='on'</c:if> id="dcPeoId">입찰설명회<c:if test="${myPartcptReqstSttusDetail.BID_DC_PEO_CD eq 'BPYY'}">(의무)</c:if><c:if test="${myPartcptReqstSttusDetail.BID_DC_PEO_CD eq 'BPYN'}">(비의무)</c:if></div>
	            	<%-- 입찰설명회 의무 비의무 표시 END --%>
	            </c:if>
            	<%-- 입찰설명회 미참가시 END --%>
	            <img src="${imagePath}/opro/icon/ico_arrow.png" alt="다음 단계">
            </c:if>
            <%-- 입찰설명회가 있을경우 활성화 END --%>

			<%-- 참가신청 --%>
			<%-- 참가신청내역이 있을경우 --%>
			<c:if test="${not empty bidPartcptnSttus}"><!-- && not empty bidPartcptAbandnReqstdoc  -->
	            <div class="line_3" <c:if test="${fn:substring(myPartcptReqstSttusDetail.BID_DETAIL_PRST_CD,0,2)  eq 'PH'}">style='display:none;'</c:if> id="pareTxtId">
	            	<span class="line_top">참가신청</span>
	            	<span class="f_normal">${comFn:formatDate(bidPartcptnSttus.PARTCPTN_DT,'yyyyMMddHHmmss','yyyy-MM-dd')}<br>${comFn:formatDate(bidPartcptnSttus.PARTCPTN_DT,'yyyyMMddHHmmss','HH : mm : ss')}</span>
	            </div>
				<%-- 입찰상세진행상태가 참가신청이면 CSS 적용 --%>
	            <div class="line_3 <c:if test="${fn:substring(myPartcptReqstSttusDetail.BID_DETAIL_PRST_CD,0,2)  eq 'PH'}">on</c:if>" <c:if test="${fn:substring(myPartcptReqstSttusDetail.BID_DETAIL_PRST_CD,0,2)  ne 'PH'}">style='display:none;'</c:if> id="pareLinkId">
	            	<span class="line_top"><a title="참가신청 버튼" style="cursor:pointer;">참가신청</a></span>
	            	<span class="f_normal">${comFn:formatDate(bidPartcptnSttus.PARTCPTN_DT,'yyyyMMddHHmmss','yyyy-MM-dd')}<br>${comFn:formatDate(bidPartcptnSttus.PARTCPTN_DT,'yyyyMMddHHmmss','HH : mm : ss')}</span>
	            </div>
				<%-- 입찰상세진행상태가 참가신청이면 CSS 적용 END --%>
			</c:if>
			
			<%-- 참가신청내역이 있을경우 END --%>
			<%-- 참가신청내역이 없을경우 --%>
			<c:if test="${empty bidPartcptnSttus}">
	            <div <c:if test="${fn:substring(myPartcptReqstSttusDetail.BID_DETAIL_PRST_CD,0,2)  eq 'PH'}">style='display:none;'</c:if> id="pareTxtId">참가신청</div>
				<%-- 입찰상세진행상태가 참가신청이면 CSS 적용 --%>
	            <div <c:if test="${fn:substring(myPartcptReqstSttusDetail.BID_DETAIL_PRST_CD,0,2)  eq 'PH'}">class='on'</c:if> <c:if test="${fn:substring(myPartcptReqstSttusDetail.BID_DETAIL_PRST_CD,0,2)  ne 'PH'}">style='display:none;'</c:if> id="pareLinkId">
	            	<a title="참가신청 버튼" style="cursor:pointer;">참가신청</a>
	            </div>
			</c:if>
	            <img src="${imagePath}/opro/icon/ico_arrow.png" alt="다음 단계">
				<%-- 입찰상세진행상태가 참가신청이면 CSS 적용 END --%>
			<%-- 참가신청내역이 없을경우 END --%>
			<%-- 참가신청 END --%>
            
            <%-- 입찰서제출 --%>
            <c:if test="${not empty bddprSttus}">
            	<div class="line_3" id="pcOfePresentnTxtId" <c:if test="${fn:substring(myPartcptReqstSttusDetail.BID_DETAIL_PRST_CD,0,2)  eq 'PI'}">style='display:none;'</c:if>>
	            	<span class="line_top">입찰서제출</span>
	            	<span class="f_normal">${comFn:formatDate(bddprSttus.PARTCPTN_DT,'yyyyMMddHHmmss','yyyy-MM-dd')}<br>${comFn:formatDate(bddprSttus.PARTCPTN_DT,'yyyyMMddHHmmss','HH : mm : ss')}</span>
	            </div>
				<%-- 입찰상세진행상태가 입찰서제출 이면 CSS 적용 --%>
	            <div class="line_3 <c:if test="${fn:substring(myPartcptReqstSttusDetail.BID_DETAIL_PRST_CD,0,2)  eq 'PI'}">on</c:if>" <c:if test="${fn:substring(myPartcptReqstSttusDetail.BID_DETAIL_PRST_CD,0,2)  ne 'PI'}">style='display:none;'</c:if> id="pcOfePresentnLinkId">
	            	<span class="line_top"><a title="입찰서제출 버튼" style="cursor:pointer;">입찰서제출</a></span>
	            	<span class="f_normal">${comFn:formatDate(bddprSttus.PARTCPTN_DT,'yyyyMMddHHmmss','yyyy-MM-dd')}<br>${comFn:formatDate(bddprSttus.PARTCPTN_DT,'yyyyMMddHHmmss','HH : mm : ss')}</span>
	            </div>
            </c:if>
            <c:if test="${empty bddprSttus}">
	            <div id="pcOfePresentnTxtId" <c:if test="${fn:substring(myPartcptReqstSttusDetail.BID_DETAIL_PRST_CD,0,2)  eq 'PI'}">style='display:none;'</c:if>>입찰서제출</div>
				<%-- 입찰상세진행상태가 입찰서제출 이면 CSS 적용 --%>
	            <div <c:if test="${fn:substring(myPartcptReqstSttusDetail.BID_DETAIL_PRST_CD,0,2)  eq 'PI'}">class='on' </c:if> <c:if test="${fn:substring(myPartcptReqstSttusDetail.BID_DETAIL_PRST_CD,0,2)  ne 'PI'}">class='on' style='display:none;'</c:if> id="pcOfePresentnLinkId">
	                <a title="입찰서제출 버튼" style="cursor:pointer;">입찰서제출</a>
	            </div>
            </c:if>
            <img src="${imagePath}/opro/icon/ico_arrow.png" alt="다음 단계">
			<%-- 입찰상세진행상태가 입찰서제출 이면 CSS 적용 END --%>
            <%-- 입찰서제출 END --%>
			
			<%-- 개찰여부가 Y이거나 진행상태가 개찰일 경우 CSS 적용 --%>
            <div id="opengComptId" <c:if test="${myPartcptReqstSttusDetail.OPENG_AT  eq 'Y' or myPartcptReqstSttusDetail.BID_PROGRS_STTUS_CD eq 'PF50'}">class='on'</c:if>>
            	<c:if test="${myPartcptReqstSttusDetail.OPENG_AT  eq 'Y'}">개찰완료</c:if>
            	<c:if test="${myPartcptReqstSttusDetail.OPENG_AT  ne 'Y' and (myPartcptReqstSttusDetail.BID_PROGRS_STTUS_CD eq 'PF20' or myPartcptReqstSttusDetail.BID_PROGRS_STTUS_CD eq 'PF30')}">개찰</c:if>
            	<c:if test="${myPartcptReqstSttusDetail.OPENG_AT  ne 'Y' and myPartcptReqstSttusDetail.BID_PROGRS_STTUS_CD eq 'PF50'}">개찰중</c:if>
            </div>
			<%-- 개찰여부가 Y이거나 진행상태가 개찰일 경우 CSS 적용 END --%>
        </div>
       </c:if>
       <%-- 입찰진행상태가 공고중, 정정정고 일때만 활성화 END --%>
	
	<div class="view_wrap typeB">
	<div class="tit_area">
		<h4 class="tit">입찰개요</h4>
	</div> <!--// tit_area E -->
	<div class="view_area">
		<table class="table">
	    	<caption>입찰개요</caption>
			<colgroup>
				<col width="170px">
                <col width="320px">
                <col width="170px">
                <col width="320px">
			</colgroup>
	        <tbody>
				<tr class="line">
                    <th scope="row">입찰공고번호</th>
                    <td>
                        ${myPartcptReqstSttusDetail.PBLANC_NO}-${myPartcptReqstSttusDetail.PBLANC_ODR}
                    </td>
                    <th scope="row">공고일자</th>
                    <td>
                        ${comFn:formatDate(myPartcptReqstSttusDetail.PBLANC_DT,'yyyyMMddHHmmss','yyyy-MM-dd')}
                    </td>
                </tr>
	        	<tr>
		            <th scope="row">입찰명</th>
                    <td colspan="3">${myPartcptReqstSttusDetail.BID_NM}</td>
		        </tr>
                <tr>
                    <th scope="row">공고구분</th>
                    <td>
                        ${myPartcptReqstSttusDetail.PBLANC_SE_CD_NM}
                    </td>
                    <th scope="row">입찰구분</th>
                    <td>
                        ${myPartcptReqstSttusDetail.PRCURE_SE_CD_NM}&nbsp;/&nbsp; ${myPartcptReqstSttusDetail.PRCURE_DETAIL_SE_CD_NM}
                    </td>
                </tr>
                <tr>
	                <th scope="row">긴급입찰여부</th>
                    <td>
                        ${myPartcptReqstSttusDetail.EMRGNCY_BID_AT_NM}
                    </td>
                    <th scope="row">지문입찰여부</th>
                    <td>
                        ${myPartcptReqstSttusDetail.FNGPRT_BID_AT_NM}
                    </td>
				</tr>
				<tr>
	                <th scope="row">산출내역서 제출필수여부</th>
                    <td <c:if test="${myPartcptReqstSttusDetail.PRCURE_SE_CD eq '0'}">colspan="3"</c:if>>
                        <c:choose>
                        	<c:when test="${myPartcptReqstSttusDetail.CARE_ESSNTL_AT eq 'Y' }">예</c:when>
                        	<c:otherwise>아니오</c:otherwise>
                        </c:choose>
                    </td>
                   	<%-- 입찰구분이 공사, 용역일때 --%>
                	<c:if test="${myPartcptReqstSttusDetail.PRCURE_SE_CD ne '0'}">
                    <th scope="row">기자재 포함 여부</th>
                    <td>
                        ${myPartcptReqstSttusDetail.MHRML_INCLS_AT_NM}
                    </td>
                	</c:if>
				</tr>
				<tr>
                	<th scope="row">투찰내역서 제출필수여부</th>
                	<td colspan="3">
                		<c:choose>
                        	<c:when test="${myPartcptReqstSttusDetail.BDDPR_DTSTMN_PRESENTN_AT eq 'Y' }">예</c:when>
                        	<c:otherwise>아니오</c:otherwise>
                        </c:choose>
                    </td>
                </tr>
                <%-- 입찰구분이 [공사], [용역] 일 경우 활성화 --%>
                <c:if test="${myPartcptReqstSttusDetail.PRCURE_SE_CD ne '0'}">
                <tr>
                    <th scope="row">입찰범위</th>
                    <td colspan="3">${myPartcptReqstSttusDetail.BID_SCOPE_CN}</td>
                </tr>
                <tr>
                    <th scope="row">계약기간</th>
                    <td colspan="3">${myPartcptReqstSttusDetail.CNTRCTPD_CN}</td>
                </tr>
                </c:if>
                <%-- 입찰구분이 [공사], [용역] 일 경우 활성화 END --%>
                
				<tr>
                    <th scope="row">입찰한도액</th>
                    <td <c:if test="${myPartcptReqstSttusDetail.SCSBID_MTH_CD ne '31'}">colspan="3"</c:if>>
                        ${comFn:formatMoney(myPartcptReqstSttusDetail.BID_LMT_AMOUNT)} 원
                    </td>
                    <%-- 적격심사일경우 기초예가 공개 --%>
                    <c:if test="${myPartcptReqstSttusDetail.SCSBID_MTH_CD eq '31'}">
	                    <th scope="row">기초예가</th>
	                    <td>
	                    	<c:choose>
	                    		<c:when test="${myPartcptReqstSttusDetail.COMPNO_PRDPRC_SE_CD eq 180000}">
			                        ${comFn:formatMoney(myPartcptReqstSttusDetail.BSIS_PRDPRC_AMOUNT)} <c:if test="${not empty myPartcptReqstSttusDetail.BSIS_PRDPRC_AMOUNT }">원</c:if>
	                    		</c:when>
	                    		<c:otherwise>
	                    			${comFn:formatMoney(myPartcptReqstSttusDetail.PLNPRC_AMOUNT)} <c:if test="${not empty myPartcptReqstSttusDetail.PLNPRC_AMOUNT }">원</c:if>
	                    		</c:otherwise>
	                    	</c:choose>
	                    </td>
                    </c:if>
                </tr>
	        </tbody>
	    </table>
	</div> <!--// view_area E -->
	
	<c:if test="${not empty pblancNtcnInfoList}">
		<div class="tit_area">
			<h4 class="tit">입찰방법</h4>
		</div> <!--// tit_area E -->
		<div class="view_area">
	        <table class="table">
		    	<caption>입찰방법</caption>
				<colgroup>
		            <col width="170px">
		            <col width="320px">
		            <col width="170px">
		            <col width="320px">
				</colgroup>
		        <tbody>
					<tr class="line">
	                    <th scope="row">국제입찰여부</th>
	                    <td>
	                        ${myBidPblancDetail.INTRLBID_AT_NM}
	                    </td>
	                    <th scope="row">나라장터 게시여부</th>
	                    <td>
	                         ${myBidPblancDetail.G2B_NTCE_AT_NM}
	                    </td>
	                </tr>
		        	<tr>
			           <th scope="row">계약방법</th>
	                    <td>
	                        ${myBidPblancDetail.CNTRCT_MTH_CD_NM}
	                    </td>
	                    <th scope="row">낙찰자선정방법</th>
	                    <td>
	                         ${myBidPblancDetail.SCSBID_MTH_CD_NM}
	                    </td>
			        </tr>
			        <tr>
			            <th scope="row">복수예가여부</th>
	                    <td <c:if test="${myBidPblancDetail.SCSBID_MTH_CD ne '31'}">colspan="3"</c:if>>
	                        ${myBidPblancDetail.COMPNO_PRDPRC_SE_NM}
	                    </td>
	                    <c:if test="${myBidPblancDetail.SCSBID_MTH_CD eq '31'}">
	                    <th scope="row">낙찰하한율</th>
	                    <td>
	                        ${myBidPblancDetail.SCSBID_LWLT_RT}
	                    </td>
	                    </c:if>
			        </tr>
			        <tr>
			            <th scope="row">입찰설명회여부</th>
	                    <td <c:if test="${myBidPblancDetail.BID_DC_PEO_CD eq 'BPNN'}">colspan="3"</c:if>>
	                        ${myBidPblancDetail.BID_DC_PEO_CD_NM}
	                    </td>
	                    <c:if test="${myBidPblancDetail.BID_DC_PEO_CD ne 'BPNN'}">
	                    <th scope="row">입찰설명회장소</th>
	                    <td>${myBidPblancDetail.BID_DC_PEO_PLACE_NM}</td>
	                    </c:if>
			        </tr>
	                <tr>
	                    <th scope="row">입찰보증금 납부형태</th>
	                    <td colspan="3">
	                    	<label for="P_BID_GTN_PAY_STLE_CN" class="blind">입찰보증금 납부형태</label>
	                    	<textarea id="P_BID_GTN_PAY_STLE_CN" rows="5" cols="70" readonly taView style="display: none;"><c:out value="${myBidPblancDetail.BID_GTN_PAY_STLE_CN}"></c:out></textarea>
	                    </td>
	                </tr>
	                <c:if test="${myBidPblancDetail.CNTRCT_MTH_CD eq '10000' or myBidPblancDetail.CNTRCT_MTH_CD eq '10002'}">
		                 <tr>
		                    <th scope="row">입찰참가자격</th>
		                    <td colspan="3">
		                    	<label for="P_BID_PARTCPT_QUALF_CN" class="blind">입찰보증금 납부형태</label>
		                    	<textarea id="P_BID_PARTCPT_QUALF_CN" rows="5" cols="70" readonly taView style="display: none;"><c:out value="${myBidPblancDetail.BID_PARTCPT_QUALF_CN}"></c:out></textarea>
		                    </td>
		                </tr>
	                </c:if>
	                <tr>
	                    <th scope="row">공동수급 여부</th>
	                    <td  colspan="3">
	                        ${myBidPblancDetail.COPERTN_SPLDMD_DUTY_SE_CD_NM}
	                    </td>
	                </tr>
	                <%-- 낙찰자선정방법이 [협상에 의한 계약(기술평가)], [협상에 의한 계약(기술가격종합평가)], [기술.가격분리(기술가격입찰동시-최저가)] 일 경우 활성화 --%>
	                <c:if test="${myBidPblancDetail.SCSBID_MTH_CD eq '40' }">
		                <tr>
		                    <th scope="row">제안서제출방법</th>
		                    <td>
		                        ${myBidPblancDetail.PRPR_MTH_CD_NM}
		                    </td>
		                    <th scope="row">온라인제출필수여부</th>
		                    <td>
		                        ${myBidPblancDetail.ONPR_ESSNTL_AT_NM}
		                    </td>
		                </tr>
		                <tr>
		                    <th scope="row">기술평가 담당자</th>
		                    <td colspan="3">${myBidPblancDetail.TCHQVLN_CHARGER_NM}</td>
		                </tr>
		            </c:if>
	                <%-- 낙찰자선정방법이 [협상에 의한 계약(기술평가)], [협상에 의한 계약(기술가격종합평가)], [기술.가격분리(기술가격입찰동시-최저가)] 일 경우 활성화 END --%>
		        </tbody>
		    </table>
		</div> <!--// view_area E -->
	</c:if>
	
	
	<div class="tit_area">
		<h4 class="tit">계약조건</h4>
	</div> <!--// tit_area E -->
	<div class="view_area">
		<div class="tableLayer">
			<table class="table">
	    	<caption>계약조건</caption>
			<colgroup>
                <col width="170px">
                <col width="320px">
                <col width="170px">
                <col width="320px">
			</colgroup>
	        <tbody>
				<tr class="line">
                    <th scope="row">지체상금률(%)</th>
                    <td>
                        ${myPartcptReqstSttusDetail.DFRCMPST_RT} /1,000
                    </td>
                    <th scope="row">하자이행보증금률</th>
                    <td>
                         ${myPartcptReqstSttusDetail.FLAW_FLFL_GTN_RT}&nbsp;%
                    </td>
                </tr>
                <tr>
                    <th scope="row">계약보증금률</th>
                    <td <c:if test="${myPartcptReqstSttusDetail.MHRML_INCLS_AT ne 'Y'}">colspan="3"</c:if>>
                        ${myPartcptReqstSttusDetail.CRYMY_RT}&nbsp;%
                    </td>
	                <c:if test="${myPartcptReqstSttusDetail.MHRML_INCLS_AT eq 'Y'}">
                    <th scope="row">물품인도조건</th>
                    <td>
                         ${myPartcptReqstSttusDetail.THNG_DELY_CND_CD_NM}
                    </td>
                    </c:if>
                </tr>
                <c:if test="${myPartcptReqstSttusDetail.MHRML_INCLS_AT eq 'Y'}">
	                <tr >
	                    <th scope="row">납품장소</th>
	                    <td colspan="3">
	                    	<label for="P_DVYFG_PLACE_NM" class="blind">납품장소</label>
	                    	<textarea id="P_DVYFG_PLACE_NM" rows="5" cols="70" readonly taView style="display: none;"><c:out value="${myPartcptReqstSttusDetail.DVYFG_PLACE_NM}"></c:out></textarea>
	                    </td>
	                </tr>
	                <tr>
	                    <th scope="row">납품기한</th>
	                    <td colspan="3">
	                    	<label for="P_DVYFG_PD_CN" class="blind">납품기한</label>
	                    	<textarea id="P_DVYFG_PD_CN" rows="5" cols="70" readonly taView style="display: none;"><c:out value="${myPartcptReqstSttusDetail.DVYFG_PD_CN}"></c:out></textarea>
	                    </td>
	                </tr>
	                <!--  
	                <tr>
	                    <th scope="row">설치기한</th>
	                    <td colspan="3">
	                    	<label for="P_INSTL_TMLMT_CN" class="blind">설치기한</label>
	                    	<textarea id="P_INSTL_TMLMT_CN" rows="5" cols="70" readonly  taView style="display: none;"><c:out value="${myPartcptReqstSttusDetail.INSTL_TMLMT_CN}"></c:out></textarea>
	                    </td>
	                </tr>
	                -->
	                <tr>
	                    <th scope="row">비고</th>
	                    <td colspan="3">
	                    	<label for="P_RMK" class="blind">비고</label>
	                    	<textarea id="P_RMK" rows="5" cols="70" readonly  taView style="display:none;"><c:out value="${myPartcptReqstSttusDetail.RMK}"></c:out></textarea>
	                    </td>
	                </tr>
                </c:if>
	        </tbody>
	    </table>
		</div> <!--// view_area E -->
	</div> <!--// view_wrap E -->
	
	<div class="tit_area">
		<h4 class="tit">입찰진행순서</h4>
	</div> <!--// tit_area E -->
	<div class="view_area">
		<div class="tableLayer">
			<table class="table">
	    	<caption>입찰진행순서</caption>
			<colgroup>
				<col width="170px">
                <col width="810px">
			</colgroup>
	        <tbody>
				<tr class="line">
                    <th scope="row">입찰공고 일시</th>
                    <td>${comFn:formatDate(myPartcptReqstSttusDetail.PBLANC_DT,'yyyyMMddHHmmss','yyyy-MM-dd HH:mm')}</td>
                </tr>
                <c:if test="${myPartcptReqstSttusDetail.BID_DC_PEO_CD ne 'BPNN'}">
                <tr>
                    <th scope="row">입찰설명회 일시</th>
                    <td>${comFn:formatDate(myPartcptReqstSttusDetail.BID_DC_PEO_DT,'yyyyMMddHHmmss','yyyy-MM-dd HH:mm')}</td>
                </tr>
                </c:if>
                <tr>
                    <th scope="row">참가신청서 제출기간</th>
                    <td>${comFn:formatDate(myPartcptReqstSttusDetail.PARE_BEGIN_DT,'yyyyMMddHHmmss','yyyy-MM-dd HH:mm')} ~ ${comFn:formatDate(myPartcptReqstSttusDetail.PARE_END_DT,'yyyyMMddHHmmss','yyyy-MM-dd HH:mm')}</td>
                </tr>
                <tr>
                    <th scope="row">입찰서 제출기간</th>
                    <td>${comFn:formatDate(myPartcptReqstSttusDetail.BIPA_PRESENTN_BEGIN_DT,'yyyyMMddHHmmss','yyyy-MM-dd HH:mm')} ~ ${comFn:formatDate(myPartcptReqstSttusDetail.BIPA_PRESENTN_END_DT,'yyyyMMddHHmmss','yyyy-MM-dd HH:mm')}</td>
                </tr>
                <tr>
                    <th scope="row">개찰일시</th>
                    <td>${comFn:formatDate(myPartcptReqstSttusDetail.OPENG_DT,'yyyyMMddHHmmss','yyyy-MM-dd HH:mm')}</td>
                </tr>
	        </tbody>
	    </table>
		</div> <!--// view_area E -->
	</div> <!--// view_wrap E -->
	
	<c:if test="${myPartcptReqstSttusDetail.PRCURE_SE_CD eq '0'}">
		<div class="tit_area">
			<h4 class="tit">입찰품목정보</h4>
		</div> <!--// tit_area E -->
		<div class="view_area">
			<div class="tableLayer">
				<table class="tableList">
	            <caption>입찰품목정보</caption>
	            <colgroup>
	                <col width="50px">
	                <col width="130px">
	                <col width="300px">
	                <col width="300px">
	                <col width="100px">
	                <col width="100px">
	            </colgroup>
				<thead>
	                <tr>
	                    <th scope="col" class="noBg">번호</th>
	                    <th scope="col">품명번호</th>
	                    <th scope="col">품명</th>
	                    <th scope="col">참조사항</th>
	                    <th scope="col">단위</th>
	                    <th scope="col">수량</th>
	                </tr>
	            </thead>
				<tbody>
					<c:if test="${empty bidPrdlsList}">
						<tr class="row">
							<td colspan="6">입찰품목 정보가 없습니다.</td>
						</tr>
					</c:if>
					<c:if test="${not empty bidPrdlsList}">
						<c:forEach var="data" items="${bidPrdlsList}" varStatus="status" >
						<tr class="row">
							<td>${status.count}</td>
							<td>${data.THNG_NO}</td>
							<td class="left_T">${data.QLY_NM}</td>
							<td class="left_T">${data.PRDLST_REFRN_CN}</td>
							<td>${data.G2B_UNIT_NM}</td>
							<td>${data.PRDLST_QY}</td>
						</tr>
						</c:forEach>
					</c:if>
				</tbody>
			</table>
			</div> <!--// view_area E -->
		</div> <!--// view_wrap E -->
	</c:if>
	
	<div class="tit_area">
		<h4 class="tit">입찰첨부파일</h4>
	</div> <!--// tit_area E -->
	<div class="view_area">
		<div class="tableLayer">
			<table class="table">
		    	<colgroup>
		        	<col width="170px">
                    <col width="810px">
		        </colgroup>
		        <tr class="line">
	               	<th scope="row" class="vtop">첨부파일</th>
					<td class="vtop">
						<div id="fileViewDiv" style="width: 680px; line-height: 30px;"> 
                  			<c:if test="${not empty bidAtchDocList }">
		                   		<c:forEach items="${bidAtchDocList }" var="data" varStatus="status">
		                   			<div style="height: 30px;"> 
		                   				<a href="javascript:pageObj.download('${data.ATCHMNFL_SN}');" class="attfile">${data.ATCHMNFL_NM }</a>
		                    		</div>
		                    	</c:forEach> 
		                    </c:if>
		                    <c:if test="${empty bidAtchDocList}">
					        	<div style="height: 30px;"> 입찰첨부파일 정보가 없습니다.</div>
					        </c:if>
	                   	</div>
					</td>	
	             </tr>
		    </table>
		</div> <!--// view_area E -->
	</div> <!--// view_wrap E -->
	
	<div class="tit_area">
		<h4 class="tit">담당자 정보</h4>
	</div> <!--// tit_area E -->
	<div class="view_area">
		<div class="tableLayer">
			<table class="table" summary="담당자 정보 입니다.">
                <caption>당당자 정보</caption>
                <colgroup>
                    <col width="170px">
	                <col width="320px">
	                <col width="170px">
	                <col width="320px">
                </colgroup>
                <tbody>
	                <tr class="line">
	                    <th scope="row">입찰담당자</th>
	                    <td>
	                        ${myPartcptReqstSttusDetail.BID_CHARGER_NM}
	                    </td>
	                    <th scope="row">사업담당자(의뢰자)</th>
	                    <td>
	                        ${myPartcptReqstSttusDetail.BID_CLIENT_NM}
	                    </td>
	                </tr>
	                <tr>
	                    <th scope="row">입찰담당자 이메일</th>
	                    <td>
	                        ${myPartcptReqstSttusDetail.BID_CHARGER_EMAIL_ADRES}
	                    </td>
	                    <th scope="row">사업담당자 이메일</th>
	                    <td>
	                        ${myPartcptReqstSttusDetail.BID_CLIENT_EMAIL_ADRES}
	                    </td>
	                </tr>
                </tbody>
            </table>
		</div> <!--// view_area E -->
	</div> <!--// view_wrap E -->
	
	<div id="bidPartcptAbandnDiv" <c:if test="${empty bidPartcptAbandnReqstdoc}">style="display:none;"</c:if> >
	<div class="tit_area">
		<h4 class="tit">입찰참가 포기사유</h4>
	</div> <!--// tit_area E -->
	<div class="view_area">
	        <div class="tableLayer">
	            <table class="table" >
	                <caption>입찰참가 포기사유</caption>
	                <colgroup>
	                    <col width="170px">
	                    <col width="810px">
	                </colgroup>
	                <tbody>
	                <tr class="line">
	                    <th scope="row">포기일자</th>
	                    <td id="abandnRegistDt">
	                    	${comFn:formatDate(bidPartcptAbandnReqstdoc.REGIST_DT,'yyyyMMddHHmmss','yyyy-MM-dd HH:mm:ss')}
	                    </td>
	                </tr>
	                <tr>
	                    <th scope="row">포기사유</th>
	                    <td>
	                    	<label for="BIAB_RESN_CN" class="blind">포기사유</label>
	                    	<textarea class="disabled" rows="5" cols="70" id="BIAB_RESN_CN" maxlength="4000" disabled  taView style="display: none;"><c:out value="${bidPartcptAbandnReqstdoc.BIAB_RESN_CN}"></c:out></textarea>
	                    </td>
	                </tr>
	                </tbody>
	            </table>
	        </div>
        </div>
     </div>
	<div class="btn_wrap view_btn">
	<c:if test="${ myPartcptReqstSttusDetail.BID_PROGRS_STTUS_CD ne 'PF40' && hiddnButton eq 'N'}"> 
        
           	<button type="button" class="btn btn_m btn_del" id="bidPareViewBtn" >입찰참가신청서 보기</button>
           	<%-- 입찰 미포기 --%>
           	<c:if test="${empty bidPartcptAbandnReqstdoc}">
	           		<%-- 투찰 안함 --%>
	            	<c:if test="${empty bddprSttus}">
	            		<%-- 공동수급방식이 단독이 아닐경우 --%>
	            		<c:if test="${entrpsinfoinqire.COPERTN_SPLDMD_DUTY_SE_CD ne '240001'}">
		            		<%-- 공동수급협정서 미제출 --%>
		            		<c:if test="${empty copertnSdenList && empty bidPartcptnSttus.BID_PARTCPTN_STTUS}">
		            			<c:if test="${sessionScope.loginResult.LOGIN_ID eq P_ENTRPS_REGIST_NO }">
			            			<button type="button" class="btn btn_m btn_orange" id="cospBtn" >공동수급협정서 제출</button>
			            			<input type="hidden" id="cospAt" value="N">
			            		</c:if>
		            		</c:if>
		            		<%-- 공동수급협정서 미제출 END--%>
		            		<%-- 공동수급협정서 제출 --%>
			            	<c:if test="${not empty copertnSdenList}">
			            		<c:if test="${sessionScope.loginResult.LOGIN_ID eq P_ENTRPS_REGIST_NO }">
			            			<button type="button" class="btn btn_m btn_orange" id="bipaPresentnBtn">입찰서작성</button>
			            		</c:if>
				            	<button type="button" class="btn btn_m btn_del" id="cospViewBtn" >공동수급협정서 보기</button>
				            	<input type="hidden" id="cospAt" value="Y">
			            	</c:if>
		            		<%-- 공동수급협정서 제출 END--%>
	            		</c:if>
	            		<%-- 공동수급방식이 단독이 아닐경우 END --%>
	            		<%-- 공동수급방식이 단독일 경우 --%>
	            		<c:if test="${entrpsinfoinqire.COPERTN_SPLDMD_DUTY_SE_CD eq '240001'}">
	            			<c:if test="${sessionScope.loginResult.LOGIN_ID eq P_ENTRPS_REGIST_NO }">
	            				<button type="button" class="btn btn_m btn_orange" id="bipaPresentnBtn" disabled>입찰서작성</button>
	            			</c:if>
	            		</c:if>
	            		<%-- 공동수급방식이 단독일 경우 END --%>
	            	</c:if>
	           		<%-- 투찰 안함 END --%>
	            	<c:if test="${not empty bidPartcptnSttus.PARTCPTN_DT and myPartcptReqstSttusDetail.OPENG_AT  ne 'Y' and myPartcptReqstSttusDetail.BID_PROGRS_STTUS_CD ne 'PF50' and myPartcptReqstSttusDetail.BID_PROGRS_STTUS_CD ne 'PF60' and myPartcptReqstSttusDetail.BID_PROGRS_STTUS_CD ne 'PF99'}">
	           			<c:if test="${sessionScope.loginResult.LOGIN_ID eq P_ENTRPS_REGIST_NO }">
	           				<button type="button" class="btn btn_m btn_orange" id="bidPartcptAbandnBtn">입찰포기신청</button>
	           			</c:if>
	           		</c:if>
	           		<%-- 투찰 함 --%> 
	            	<c:if test="${not empty bddprSttus}">
	            		<%-- 공동수급방식이 단독이 아닐경우 --%>
	            		<c:if test="${entrpsinfoinqire.COPERTN_SPLDMD_DUTY_SE_CD ne '240001'}">
	            			<button type="button" class="btn btn_m btn_del" id="cospViewBtn" >공동수급협정서 보기</button>
	            		</c:if>
	            		<%-- 공동수급방식이 단독이 아닐경우 END --%>
	            	</c:if>
	           		<%-- 투찰 함 END --%>
           		</c:if>
           	<%-- 입찰 미포기 END --%>
           </c:if>
        	<%-- 입찰 미참가 END --%>
            <button type="button" class="btn btn_m btn_del" id="listBtn">목록</button>
	</div> <!--// btn_wrap E -->
</div>
	
<%--page move form --%>
<form id="listFrm" method="POST" action="">
	<input type="hidden" name="resourceName" value="${param.resourceName}">
</form>
<form id="detailFrm" method="POST" action="">
	<input type="hidden" name="resourceName" value="${param.resourceName}">
	<input type="hidden" name="P_PBLANC_NO" value="${myPartcptReqstSttusDetail.PBLANC_NO}">
	<input type="hidden" name="P_PBLANC_ODR" value="${myPartcptReqstSttusDetail.PBLANC_ODR}">
</form>
<form id="bidPartcptAbandnFrm" method="POST" action="">
	<input type="hidden" name="resourceName" value="${param.resourceName}">
	<input type="hidden" name="P_PBLANC_NO" value="${myPartcptReqstSttusDetail.PBLANC_NO}">
	<input type="hidden" name="P_PBLANC_ODR" value="${myPartcptReqstSttusDetail.PBLANC_ODR}">
	<input type="hidden" name="P_BID_NM" value="${myPartcptReqstSttusDetail.BID_NM}">
</form>
<form id="bipaPresentnFrm" method="POST" action="">
	<input type="hidden" name="resourceName" value="${param.resourceName}">
	<input type="hidden" name="P_PBLANC_NO" value="${myPartcptReqstSttusDetail.PBLANC_NO}">
	<input type="hidden" name="P_PBLANC_ODR" value="${myPartcptReqstSttusDetail.PBLANC_ODR}">
	<input type="hidden" name="P_SCRIN_SE" value="bipaPresentnForm">
	<input type="hidden" name="JOB_GUBUN" value="">
</form>
<%-- 팝업 폼 --%>
<form id="popupFrm" method="POST" action="">
	<input type="hidden" name="resourceName" value="${param.resourceName}">
	<input type="hidden" name="P_PBLANC_NO" value="${myPartcptReqstSttusDetail.PBLANC_NO}">
	<input type="hidden" name="P_PBLANC_ODR" value="${myPartcptReqstSttusDetail.PBLANC_ODR}">
	<input type="hidden" name="P_ENTRPS_REGIST_NO" value="${P_ENTRPS_REGIST_NO}">
</form>
<%-- 기본 폼 --%>
<form id="basicFrm" method="POST" action="">
  	<input type="hidden" name="resourceName" value="${param.resourceName}">
	<input type="hidden" name="P_PBLANC_NO" value="${myPartcptReqstSttusDetail.PBLANC_NO}">
	<input type="hidden" name="P_PBLANC_ODR" value="${myPartcptReqstSttusDetail.PBLANC_ODR}">
</form>
<form id="fileViewFrm" method="POST" action = "">
	<input type="hidden" id="P_REGLTN_ATCHMNFL_GROUP_NO" name="P_REGLTN_ATCHMNFL_GROUP_NO" value="${bidAtchDoc2.ATCHMNFL_GROUP_NO}">
	<input type="hidden" id="P_ATCHMNFL_GROUP_NO" name="P_ATCHMNFL_GROUP_NO" value="${bidAtchDoc.ATCHMNFL_GROUP_NO}">
</form>
<form id="writngFrm" method="POST" action="">
	<input type="hidden" name="resourceName" value="${param.resourceName}">
	<input type="hidden" name="P_PBLANC_NO" value="${myPartcptReqstSttusDetail.PBLANC_NO}">
	<input type="hidden" name="P_PBLANC_ODR" value="${myPartcptReqstSttusDetail.PBLANC_ODR}">
	<input type="hidden" name="P_ENTRPS_REGIST_NO" value="${P_ENTRPS_REGIST_NO}">
</form>
<form id="downFrm" method="post">
	<input type="hidden" name="P_ATCHMNFL_SN">
</form>
