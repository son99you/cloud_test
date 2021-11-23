<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 전자입찰 > 적격심사 평가 조회폼(팝업)
 *
 * <pre>
 * elbi 
 *    |_iepElbiPopupProperJdgmnEvlInqire.jsp
 * 
 * </pre>
 * @date : 2015. 03. 12. 오후 08:12:51
 * @version : 1.0
 * @author : 은우소프트 손연우
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %>

<link rel="stylesheet" type="text/css" href="${cssPath}/ipro/template/pagingPlace.css">
<link rel="stylesheet" type="text/css" href="${cssPath}/ipro/windowPop.css">

<script type="text/javascript" src="${jsPath}/ipro/views/ebid/popup/properJdgmnEvlInqire.js"></script>

<div id="windowPopup">
	<h3 class="windowTitle">적격심사 평가조회</h3>
    
        <div class="tableLayer">
            <p class="popSubTitle marginSet"></p>
            <table class="table">
                <caption>입찰개요</caption>
                <colgroup>
                   <col width="80px">
                   <col width="290px">
                </colgroup>
                <tbody>
	                <tr class="line">
	                    <th>공고번호</th>
	                    <td>${properJdgmnEntrpsDetail.ANNC_NO}-${properJdgmnEntrpsDetail.ANNC_NGR}</td>
	                </tr>
	                <tr>
	                    <th>입찰명</th>
	                    <td>${properJdgmnEntrpsDetail.BID_NM}&nbsp;</td>
	                </tr>
	                <tr>
	                    <th>입찰한도액(원)</th>
	                    <td>${comFn:formatMoney(properJdgmnEntrpsDetail.ESTT_AMT)}&nbsp;</td>
	                </tr>
	                
                </tbody>
            </table>
        </div>
        <div class="tableLayer">
            <p class="popSubTitle marginSet">심사대상입찰자</p>
            <table class="table">
                <caption></caption>
                <colgroup>
                    <col width="80px">
                    <col width="110px">
                    <col width="80px">
                    <col width="100px">
                </colgroup>
                <tbody>
	                <tr>
	                    <th>업체명</th>
	                    <td>${properJdgmnEntrpsDetail.VEND_NM}&nbsp;</td>
	                    <th>대표자</th>
	                    <td>${properJdgmnEntrpsDetail.RPRS_NM }&nbsp;</td>
	                </tr>
	                <tr>
	                    <th>주사업내용</th>
	                    <td colspan="3">${MAIN_BSNS }&nbsp;</td>
	                </tr>
                </tbody>
            </table>
        </div>
        
        <div class="tableLayer">
		<p class="popSubTitle marginSet">사업참여이력</p>
		<table class="table">
            <caption>사업참여이력</caption>
            <colgroup>
                <col width="40px"/>
                <col width="120px"/>
                <col width="340px"/>
                <col width="60px"/>
                <col width="60px"/>
            </colgroup>			
			<thead>
                <tr>
                    <th>번호</th>
                    <th>공고번호</th>
                    <th>공고명</th>
                    <th>낙찰여부</th>
                    <th>계약체결여부</th>
                </tr>
            </thead>
			<tbody>
				<c:forEach var="data" items="${bsnsPartcptnHistList}" varStatus="status" >
					<tr>
						<td class="txtc">${data.RNUM}</td>
						<td class="txtc">${data.ANNC_NO}</td>
						<td class="left_T">${data.BID_NM}</td>
						<td class="txtc">	
							<c:if test="${data.SBID_YN eq 'Y'}">낙찰</c:if>
						</td>
						<td class="txtc">	
							<c:if test="${data.CNTRCT_COMPT_AT eq 'Y'}">계약완료</c:if>
						</td>
					</tr>
				</c:forEach>
			</tbody> 
		</table>
		
		</div>
        
        
        <div class="tableLayer">
        <p class="popSubTitle marginSet">심사평가</p>
		<form id="registFrm" method="POST" action="${contextPath}/elbi/properJdgmnEvlRegist.do">
		<input type="hidden" name="P_ANNC_NO" value="${ P_ANNC_NO }" >
		<input type="hidden" name="P_ANNC_NGR"  value="${ P_ANNC_NGR }" >
		<input type="hidden" name="P_VEND_REG_NO" value="${ P_VEND_REG_NO }">
		<input type="hidden" name="P_CONT_DECD" value="${properJdgmnEntrpsDetail.CONT_DECD }"/>
		<input type="hidden" name="P_CONT_SECD" value="${properJdgmnEntrpsDetail.CONT_SECD}">
		<table class="table">
            <caption>심사평가</caption>
            <colgroup>
                <col width="30px"/>
                <col width="100px"/>
                <col width="80px"/>
                <col width="80px"/>
                <col width="80px"/>
            </colgroup>			
			<thead>
                <tr>
                    <th>번호</th>
                    <th>구분</th>
                    <th>배점한도</th>
                    <th>자기평점(점)</th>
                    <th>심사평점(점)</th>
                </tr>
            </thead>
			<tbody>
			<c:if test="${not empty entrpsJdgmnEvlList }">
					<c:forEach var="data" items="${entrpsJdgmnEvlList}" varStatus="status" >
					<tr>
						<td class="txtc">${data.RNUM}</td>
						<td>${data.EVL_CN}</td>
						<td class="txtc">${data.ALLOT_LMT_CN}</td>
						<td class="txtc">
								${data.ENTRPS_EVL_SCORE}
								<input type="hidden" id="entrpsEvlScore[${status.index }]" value="${data.ENTRPS_EVL_SCORE}">
								<c:if test="${ data.EVL_CN eq '입찰가격'}">
									<input type="hidden" readonly="readonly" class="disabled" style="text-align: center;" id="entrpsEvlScore${status.index }" name="P_ENTRPS_EVL_SCORE_BID_PC" onchange="entrpsEvlSm();" value="${data.ENTRPS_EVL_SCORE}"/>
								</c:if>																																
						  		<c:if test="${ data.EVL_CN ne '입찰가격' }">
						  			<input type="hidden" style="text-align: center;" id="entrpsEvlScore_${status.index }" name="P_ENTRPS_EVL_SCORE" onchange="entrpsEvlSm(this,'${data.ALLOT_LMT_CN}');" onkeyup="entrpsEvlSm(this,'${data.ALLOT_LMT_CN}');" value="${data.ENTRPS_EVL_SCORE}" jdgmn>
						  		</c:if>
						</td>						
						<td class="txtc">
								${data.KOICA_EVL_SCORE}
								<c:if test="${ data.EVL_CN eq '입찰가격'}">
									<input type="hidden" style="text-align: center;" readonly="readonly" class="disabled"  name="P_KOICA_EVL_SCORE_BID_PC" id="koicaEvlScore${status.index }" value="${data.KOICA_EVL_SCORE}">
								</c:if>
								<c:if test="${ data.EVL_CN ne '입찰가격' }">
									<input type="hidden" style="text-align: center;" id="koicaEvlScore[${status.index }]" name="P_KOICA_EVL_SCORE" onchange="koicaEvlSm(this,'${data.ALLOT_LMT_CN}');" onkeyup="koicaEvlSm(this,'${data.ALLOT_LMT_CN}');" value="${data.KOICA_EVL_SCORE}" jdgmn>
								</c:if>
						</td>
					</tr>
					</c:forEach>
					<tr>
						<td colspan="2" class="txtc">계</td>
						<td class="txtc">100</td>
						<td id="entrpsEvlTot" class="txtc"></td>
						<td id="koicaEvlTot" class="txtc"></td>
					</tr>
			</c:if>
			<c:if test="${empty entrpsJdgmnEvlList }">
				<td colspan="5" class="txtc">데이터가 존재 하지 않습니다.</td>
			</c:if>
			</tbody> 
		</table>
		</form> 
		</div>
        
        <div class="tableLayer">
            
            <table>
                <colgroup>
                    <col width="800px">
                </colgroup>
                <tbody>
	                <tr>
	                    <td style="padding-left: 55px;">
	                    	※ 협력단 물품구매 적격심사기준 제4조 제3항에 의거, 신인도 평가는 납품이행능력, 취득점수가 배점한도에
	                    	<br> 부족한 경우에만 배점한도 범위내에서 가산점을 부여하며, 배점한도를 초과하는 점수는 [5.납품이행능력 총점제한]
	                    	<br> 항목에서 차감합니다.
 
	                    </td>
	                    
	                </tr>
                </tbody>
            </table>
        </div>
        
        
        <div class="T_btnLayer fr">
            <button type="button" class="blueBtn L" id="closeBtn">닫기</button>
        </div>
</div>
