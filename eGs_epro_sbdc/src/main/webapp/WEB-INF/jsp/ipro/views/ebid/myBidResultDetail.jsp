<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 입찰관리
 * 		> 나의 입찰결과 상세 
 *
 * <pre>
 * ebid 
 *    |_ myBidPblancDetail.jsp
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

<script type="text/javascript" src="${jsPath}/opro/views/ebid/myBidResultDetail.js"></script>

<ul class="step_wrap">
	<li><a href="#">경쟁입찰</a></li>
	<li><a href="#">나의입찰결과</a></li> 
</ul> <!--// step_wrap E -->
<div class="tit_wrap">
	<h3 class="tit">나의입찰결과 상세</h3>
</div> <!--// tit_wrap E -->  
   
<div class="view_wrap typeB">
	<div class="tit_area">
		<h4 class="tit">입찰개요</h4>
	</div> <!--// tit_area E -->
	<div class="view_area">
		<table class="table">
                <caption>입찰개요</caption>
                <colgroup>
                    <col width="15%">
                    <col width="35%">
                    <col width="15%">
                    <col width="35%">
                </colgroup>
                <tbody>
                <tr class="line">
                    <th>입찰공고번호</th>
                    <td colspan="3">
                        ${myBidResultDetail.PBLANC_NO}-${myBidResultDetail.PBLANC_ODR}
                    </td>
                </tr>
                <tr>
                    <th>입찰명</th>
                    <td colspan="3">${myBidResultDetail.BID_NM}&nbsp;</td>
                </tr>
                <tr>
                    <th>입찰구분</th>
                    <td>
                        ${myBidResultDetail.PRCURE_SE_NM}&nbsp;
                    </td>
                    <th>입찰상세구분</th>
                    <td>
                        ${myBidResultDetail.PRCURE_DETAIL_SE_NM}&nbsp;
                    </td>
                </tr>
                <%-- 입찰구분이 [공사], [용역] 일 경우 활성화 --%>
                <c:if test="${myBidResultDetail.PRCURE_SE_CD ne '0'}">
                <tr>
                    <th scope="row">입찰범위</th>
                    <td colspan="3">${myBidResultDetail.BID_SCOPE_CN}</td>
                </tr>
                <tr>
                    <th scope="row">계약기간</th>
                    <td colspan="3">${myBidResultDetail.CNTRCTPD_CN}</td>
                </tr>
                </c:if>
                <%-- 입찰구분이 [공사], [용역] 일 경우 활성화 END --%>
                <tr>
                	<th>계약방법</th>
                	<td colspan="3">
                		${myBidResultDetail.CNTRCT_MTH_NM }
                	</td>
                </tr>
                <tr>
                	<th>낙찰방법</th>
                	<td colspan="3">
                		${myBidResultDetail.SCSBID_MTH_NM}
                	</td>
                </tr>
                <c:if test="${ myBidResultDetail.BID_PROGRS_STTUS_CD eq 'PF60' || myBidResultDetail.BID_PROGRS_STTUS_CD eq 'PF50' }">
                <tr>
                	<th>입찰결과</th>
                	<td colspan="3">
                		<c:if test="${not empty myBidResultDetail.CNTRCT_DE }">계약체결 &nbsp;(${comFn:formatDate(myBidResultDetail.CNTRCT_DE,'yyyyMMdd','yyyy-MM-dd')})&nbsp;</c:if>
                		<c:if test="${empty myBidResultDetail.CNTRCT_DE}"> 
                			<c:choose>
                				<c:when test="${myBidResultDetail.BID_PROGRS_STTUS_CD eq 'PF60' }">낙찰</c:when>
                				<c:otherwise>낙찰자선정중</c:otherwise>
                			</c:choose>
                		</c:if>
                </tr>
                </c:if>
                <c:if test="${ myBidResultDetail.BID_PROGRS_STTUS_CD eq 'PF99' }">
                <tr>
                	<th>입찰결과</th>
                	<td colspan="3">${myBidResultDetail.BID_PROGRS_STTUS_CD_NM }&nbsp;</td>
                </tr>
                <tr>
                	<th>유찰단계</th>
                	<td colspan="3">${myBidResultDetail.FIB_STEP_CD }&nbsp;</td>
                </tr>
                <tr>
                	<th>유찰사유</th>
                	<td colspan="3">${myBidResultDetail.NTCN_RESN_CN }&nbsp;</td>
                </tr>
                </c:if>
                </tbody>
            </table>
	</div> <!--// view_area E -->

	<c:if test ="${myBidResultDetail.BID_PROGRS_STTUS_CD ne 'PF40' }">
        
        <c:if test ="${myBidResultDetail.BID_PROGRS_STTUS_CD ne 'PF99' }">
        <div class="tit_area">
        <h4 class="tit">예가관리</h4>
        </div>
        <c:if test="${myBidResultDetail.COMPNO_PRDPRC_SE_CD eq '180000'}">
	        <div class="fl">
	        	<span class="form_info fl red hid">
	            	&nbsp;&nbsp;&nbsp;<img src="${imagePath}/opro/main/bullet_orange.png" alt="필수입력사항 표시" class="mr5"> 예가범위는 97.5% ~ 102.5%까지 15개 구간에 의해 균등분할 됩니다.
	        	</span>
	        </div> 
        </c:if>
        <div class="view_area">
            <table class="table">
                <caption>입찰한도액</caption>
                <colgroup>
                 	<col width="15%">
                    <col width="*">
                </colgroup>
                <tbody>
                <tr>
                    <th>입찰한도액</th>
                    <td>
                        ${comFn:formatMoney(myBidResultDetail.BID_LMT_AMOUNT)} ( 일금 <font color="red">${myBidResultDetail.BID_LMT_AMOUNT_KOR}</font>원 )
                    </td>
                </tr>
                <tr>
                	<th>예가구분</th>
                	<td>${ myBidResultDetail.COMPNO_PRDPRC_SE_NM}</td>
                </tr>
                 <%-- 예가구분이 [복수예가]일 경우에 활성화 --%>
                <c:if test="${myBidResultDetail.COMPNO_PRDPRC_SE_CD eq '180000'}">
                <tr>
                    <th>기초예비가격</th>
                    <td>
                        <!-- <label for="P_BSIS_PRDPRC_AMOUNT" class="blind">기초예비가격</label> -->
                        ${comFn:formatMoney(myBidResultDetail.BSIS_PRDPRC_AMOUNT)} ( 일금 <font color="red">${myBidResultDetail.BSIS_PRDPRC_AMOUNT_KOR}</font>원 )
                    </td>
                </tr>
               <tr>
                    <th>예정가격</th>
                    <td>
                        <!-- <label for="P_PLNPRC_AMOUNT" class="blind">예정가격</label> -->
                        ${comFn:formatMoney(myBidResultDetail.PLNPRC_AMOUNT)} ( 일금 <font color="red">${myBidResultDetail.PLNPRC_AMOUNT_KOR}</font>원 )
                    </td>
                </tr>
                </c:if>
                <%-- 예가구분이 [복수예가]일 경우에 활성화 END --%>
                <%-- 예가구분이 [단일예가]일 경우에 활성화 --%>
                <c:if test="${myBidResultDetail.COMPNO_PRDPRC_SE_CD eq '180001'}">
                <%-- <tr>
                    <th class="bullet_orange">기초예정가격</th>
                    <td>
                        <!-- <label for="P_BSIS_PRDPRC_AMOUNT" class="blind">기초예정가격</label> -->
                        ${comFn:formatMoney(myBidResultDetail.BSIS_PRDPRC_AMOUNT)} ( 일금 <font color="red">${myBidResultDetail.BSIS_PRDPRC_AMOUNT_KOR}</font>원 )
                    </td>
                </tr> --%>
                <tr>
                    <th>예정가격</th>
                    <td>
                        <!-- <label for="P_PLNPRC_AMOUNT" class="blind">예정가격</label> -->
                        ${comFn:formatMoney(myBidResultDetail.PLNPRC_AMOUNT)} ( 일금 <font color="red">${myBidResultDetail.PLNPRC_AMOUNT_KOR}</font>원 )
                    </td>
                </tr>
                </c:if>
                <%-- 예가구분이 [단일예가]일 경우에 활성화 END --%>
                </tbody>
            </table>
        </div>

		
		
		<%-- 복수예가이면 개찰 시 활성화 (상태값 미정)--%>
		<c:if test="${ myBidResultDetail.BID_PROGRS_STTUS_CD eq 'PF60' && myBidResultDetail.COMPNO_PRDPRC_SE_CD eq '180000' }">
		<!-- myBidResultDetail.BID_PROGRS_STTUS_CD eq 'PF60' && -->
		<div class="tit_area"> 
		<h4 class="tit">예가별 선택현황 </h4>
		</div>
		<div class="fl">
		<span class="form_info red fl">
            &nbsp;&nbsp;&nbsp;<img src="${imagePath}/opro/main/bullet_orange.png" alt="필수입력사항 표시" class="mr5"> 선택수를 클릭하시면 예가를 선택한 업체를 확인하실 수 있습니다.
        </span> 
        </div>
		
		
		  
		<div class="view_area">
            <table class="table">
                <caption>입찰개요</caption>
                <colgroup>
                    <col width="60px">
                    <col width="150px">
                    <col width="60px">
                    <col width="60px">
                    <col width="150px">
                    <col width="60px">
                    <col width="60px">
                    <col width="150px">
                    <col width="60px">
                    
                </colgroup><thead>
                	<tr>
                			<th class="thNoneStyle" style="text-align: center;">번호</th>
                			<th class="thNoneStyle" style="text-align: center;">예비가격</th>
                			<th class="thNoneStyle" style="text-align: center;">선택수</th>
                			<th class="thNoneStyle" style="text-align: center;">번호</th>
                			<th class="thNoneStyle" style="text-align: center;">예비가격</th>
                			<th class="thNoneStyle" style="text-align: center;">선택수</th>
                			<th class="thNoneStyle" style="text-align: center;">번호</th>
                			<th class="thNoneStyle" style="text-align: center;">예비가격</th>
                			<th class="thNoneStyle" style="text-align: center;">선택수</th>
                	</tr>
                </thead>
                <tbody>
                <tr style="text-align: center;">
                    <th class="thNoneStyle" style="text-align: center;">${aList[0]}</th>
                    <td>
                    	<c:if test="${dList[0] <= 4 }">
                        	<font color="red"><b>${comFn:formatMoney(bList[0])}</b></font>
                        </c:if>
                        <c:if test="${dList[0] > 4 }">
                        	${comFn:formatMoney(bList[0])}
                        </c:if>
                    </td>
                    <td onclick="bidResultPrdprcChoiseEntrpsInqire('${aList[0]}');" style="cursor: pointer;">${comFn:formatMoney(cList[0])}</td>
                    
                    <th class="thNoneStyle" style="text-align: center;">${aList[1]}</th>
                    <td>
                        <c:if test="${dList[1] <= 4 }">
                        	<font color="red"><b>${comFn:formatMoney(bList[1])}</b></font>
                        </c:if>
                        <c:if test="${dList[1] > 4 }">
                        	${comFn:formatMoney(bList[1])}
                        </c:if>
                    </td>
                    <td onclick="bidResultPrdprcChoiseEntrpsInqire('${aList[1]}');" style="cursor: pointer;">${comFn:formatMoney(cList[1])}</td>
                    
                    <th class="thNoneStyle" style="text-align: center;">${aList[2]}</th>
                    <td>
                        <c:if test="${dList[2] <= 4 }">
                        	<font color="red"><b>${comFn:formatMoney(bList[2])}</b></font>
                        </c:if>
                        <c:if test="${dList[2] > 4 }">
                        	${comFn:formatMoney(bList[2])}
                        </c:if>
                    </td>
                    <td onclick="bidResultPrdprcChoiseEntrpsInqire('${aList[2]}');" style="cursor: pointer;">${comFn:formatMoney(cList[2])}</td>
                </tr>
                <tr style="text-align: center;">
                    <th class="thNoneStyle" style="text-align: center;">${aList[3]}</th>
                    <td>
                        <c:if test="${dList[3] <= 4 }">
                        	<font color="red"><b>${comFn:formatMoney(bList[3])}</b></font>
                        </c:if>
                        <c:if test="${dList[3] > 4 }">
                        	${comFn:formatMoney(bList[3])}
                        </c:if>
                    </td>
                    <td onclick="bidResultPrdprcChoiseEntrpsInqire('${aList[3]}');" style="cursor: pointer;">${comFn:formatMoney(cList[3])}</td>

                    <th class="thNoneStyle" style="text-align: center;">${aList[4]}</th>
                    <td>
                        <c:if test="${dList[4] <= 4 }">
                        	<font color="red"><b>${comFn:formatMoney(bList[4])}</b></font>
                        </c:if>
                        <c:if test="${dList[4] > 4 }">
                        	${comFn:formatMoney(bList[4])}
                        </c:if>
                    </td>
                    <td onclick="bidResultPrdprcChoiseEntrpsInqire('${aList[4]}');" style="cursor: pointer;">${comFn:formatMoney(cList[4])}</td>
                    
                    <th class="thNoneStyle" style="text-align: center;">${aList[5]}</th>
                    <td>
                        <c:if test="${dList[5] <= 4 }">
                        	<font color="red"><b>${comFn:formatMoney(bList[5])}</b></font>
                        </c:if>
                        <c:if test="${dList[5] > 4 }">
                        	${comFn:formatMoney(bList[5])}
                        </c:if>
                    </td>
                    <td onclick="bidResultPrdprcChoiseEntrpsInqire('${aList[5]}');" style="cursor: pointer;">${comFn:formatMoney(cList[5])}</td>
                </tr>
                <tr style="text-align: center;">
                	<th class="thNoneStyle" style="text-align: center;">${aList[6]}</th>
                    <td>
                        <c:if test="${dList[6] <= 4 }">
                        	<font color="red"><b>${comFn:formatMoney(bList[6])}</b></font>
                        </c:if>
                        <c:if test="${dList[6] > 4 }">
                        	${comFn:formatMoney(bList[6])}
                        </c:if>
                    </td>
                    <td onclick="bidResultPrdprcChoiseEntrpsInqire('${aList[6]}');" style="cursor: pointer;">${comFn:formatMoney(cList[6])}</td>
                    <th class="thNoneStyle" style="text-align: center;">${aList[7]}</th>
                    <td>
                        <c:if test="${dList[7] <= 4 }">
                        	<font color="red"><b>${comFn:formatMoney(bList[7])}</b></font>
                        </c:if>
                        <c:if test="${dList[7] > 4 }">
                        	${comFn:formatMoney(bList[7])}
                        </c:if>
                    </td>
                    <td onclick="bidResultPrdprcChoiseEntrpsInqire('${aList[7]}');" style="cursor: pointer;">${comFn:formatMoney(cList[7])}</td>
                	<th class="thNoneStyle" style="text-align: center;">${aList[8]}</th>
                    <td>
                        <c:if test="${dList[8] <= 4 }">
                        	<font color="red"><b>${comFn:formatMoney(bList[8])}</b></font>
                        </c:if>
                        <c:if test="${dList[8] > 4 }">
                        	${comFn:formatMoney(bList[8])}
                        </c:if>
                    </td>
                    <td onclick="bidResultPrdprcChoiseEntrpsInqire('${aList[8]}');" style="cursor: pointer;">${comFn:formatMoney(cList[8])}</td>
                
                </tr>
                <tr style="text-align: center;">
                    <th class="thNoneStyle" style="text-align: center;">${aList[9]}</th>
                    <td>
                        <c:if test="${dList[9] <= 4 }">
                        	<font color="red"><b>${comFn:formatMoney(bList[9])}</b></font>
                        </c:if>
                        <c:if test="${dList[9] > 4 }">
                        	${comFn:formatMoney(bList[9])}
                        </c:if>
                    </td>
                    <td onclick="bidResultPrdprcChoiseEntrpsInqire('${aList[9]}');" style="cursor: pointer;">${comFn:formatMoney(cList[9])}</td>
                    <th class="thNoneStyle" style="text-align: center;">${aList[10]}</th>
                    <td>
                        <c:if test="${dList[10] <= 4 }">
                        	<font color="red"><b>${comFn:formatMoney(bList[10])}</b></font>
                        </c:if>
                        <c:if test="${dList[10] > 4 }">
                        	${comFn:formatMoney(bList[10])}
                        </c:if>
                    </td>
                    <td onclick="bidResultPrdprcChoiseEntrpsInqire('${aList[10]}');" style="cursor: pointer;">${comFn:formatMoney(cList[10])}</td>
                    <th class="thNoneStyle" style="text-align: center;">${aList[11]}</th>
                    <td>
                        <c:if test="${dList[11] <= 4 }">
                        	<font color="red"><b>${comFn:formatMoney(bList[11])}</b></font>
                        </c:if>
                        <c:if test="${dList[11] > 4 }">
                        	${comFn:formatMoney(bList[11])}
                        </c:if>
                    </td>
                    <td onclick="bidResultPrdprcChoiseEntrpsInqire('${aList[11]}');" style="cursor: pointer;">${comFn:formatMoney(cList[11])}</td>
                </tr>
                <tr style="text-align: center;">
                    <th class="thNoneStyle" style="text-align: center;">${aList[12]}</th>
                    <td>
                        <c:if test="${dList[12] <= 4 }">
                        	<font color="red"><b>${comFn:formatMoney(bList[12])}</b></font>
                        </c:if>
                        <c:if test="${dList[12] > 4 }">
                        	${comFn:formatMoney(bList[12])}
                        </c:if>
                    </td>
                    <td onclick="bidResultPrdprcChoiseEntrpsInqire('${aList[12]}');" style="cursor: pointer;">${comFn:formatMoney(cList[12])}</td>
                    <th class="thNoneStyle" style="text-align: center;">${aList[13]}</th>
                    <td>
                        <c:if test="${dList[13] <= 4 }">
                        	<font color="red"><b>${comFn:formatMoney(bList[13])}</b></font>
                        </c:if>
                        <c:if test="${dList[13] > 4 }">
                        	${comFn:formatMoney(bList[13])}
                        </c:if>
                    </td>
                    <td onclick="bidResultPrdprcChoiseEntrpsInqire('${aList[13]}');" style="cursor: pointer;">${comFn:formatMoney(cList[13])}</td>
                    <th class="thNoneStyle" style="text-align: center;">${aList[14]}</th>
                    <td>
                        <c:if test="${dList[14] <= 4 }">
                        	<font color="red" ><b>${comFn:formatMoney(bList[14])}</b></font>
                        </c:if>
                        <c:if test="${dList[14] > 4 }">
                        	${comFn:formatMoney(bList[14])}
                        </c:if>
                    </td>
                    <td onclick="bidResultPrdprcChoiseEntrpsInqire('${aList[14]}');" style="cursor: pointer;">${comFn:formatMoney(cList[14])}</td>
                </tr>
                </tbody>
            </table>
        </div> 
        </c:if>
        <%-- 복수예가이며 개찰 시  활성화  END--%>
        </c:if>
      <div class="tit_area">
		<h4 class="tit">입찰참여업체</h4>
            &nbsp;&nbsp;&nbsp;<img src="${imagePath}/opro/main/bullet_orange.png" alt="필수입력사항 표시" class="mr5"> 업체명을 클릭하시면 제출된 품목별 세부입찰내역을 확인하실 수 있습니다.
		</div>
        <div class="fl"> 
        </div>
        
         <!-- 낙찰방법이 협상에 의한 계약 중 기술가격종합평가, 기술평가인 경우 활성화    -->
		<c:if test="${(myBidResultDetail.SCSBID_MTH_CD eq '40' ) && myBidResultDetail.BID_PROGRS_STTUS_CD ne 'PF99' }">        
        <div class="view_area">
            <table class="table">
                <caption>입찰참여업체</caption>
                <colgroup>
                    <col width="40px">
                    <col width="300px">
                    <col width="100px">
                    <col width="120px">
                    <col width="100px">
                    <col width="100px">
                    <col width="100px">
                    <col width="120px">
                </colgroup>
                <thead>
	                <tr>
	                    <th class="txtc">번호</th>
	                    <th class="txtc">업체명</th>
	                    <th class="txtc">대표자</th>
	                    <th class="txtc">투찰금액</th>
	                    <th class="txtc">투찰률</th>
	                    <th class="txtc">기술점수</th>
	                    <th class="txtc">낙찰</th>
	                    <th class="txtc">결격사유</th>
	                </tr>
	            </thead>
                 	<c:if test="${empty ntatScsbidPrearngerList}">
						<tr class="row">
							<td colspan="8">데이터가 존재 하지 않습니다.</td>
						</tr>
					</c:if>
					<c:if test="${not empty ntatScsbidPrearngerList}">
					<tbody id="ntatScsbidPrearngerListJson">
						<c:forEach var="data" items="${ntatScsbidPrearngerList}" varStatus="status" >
							<c:if test="${data.ENTRPS_REGIST_NO eq param.P_REPRSNT_ENTRPS_REGIST_NO }">
								<tr class="row">
									<td>1<%-- ${status.count} --%></td>
									<td  class="left_T" onclick="prdlstAcctoDetailBidDtlsInqire('${data.ENTRPS_REGIST_NO}');" style="cursor: pointer;">${data.ENTRPS_NM}&nbsp;</td>
									<td>${data.RPRSNTV_NM}&nbsp;</td>
									<td class="right_T">${comFn:formatMoney(data.BDDPR_AMOUNT)}&nbsp;</td>
									<td><c:if test="${not empty data.BDDPR_RT }">${data.BDDPR_RT }%</c:if></td>
									<td>${data.TCHQVLN_SCORE}</td>
									<td>
											<c:if test="${ data.SCSBID_AT eq 'Y' }">
											낙찰
											</c:if>
											<c:if test="${ data.SCSBID_AT ne 'Y' }">
											&nbsp;
											</c:if>
									<td>
									<c:if test="${not empty data.BID_INADQC_RESN_CN}">
											<div class="btn_before" id="ntatDspthBtn" onclick="bidResultBrdoResnInqire('${data.ENTRPS_REGIST_NO}');" style="cursor: pointer;" >사유보기</div>
									</c:if>
									</td>
								</tr>
							</c:if>
						</c:forEach>
						
					</tbody>
					 </c:if> 
            </table>
        </div>
        </c:if>
        
        
        <!-- 낙찰 방법이 최저가순 적격심사일 경우 -->
        <c:if test="${myBidResultDetail.SCSBID_MTH_CD eq '31' && myBidResultDetail.BID_PROGRS_STTUS_CD ne 'PF99'}">        
        <div class="view_area">
            <table class="table">
                <caption>입찰참여업체</caption>
                <colgroup>
                    <col width="40px">
                    <col width="400px">
                    <col width="100px">
                    <col width="120px">
                    <col width="100px">
                    <col width="100px">
                    <col width="120px">
                </colgroup>
                <thead>
	                <tr>
	                    <th class="txtc">번호</th>
	                    <th class="txtc">업체명</th>
	                    <th class="txtc">대표자</th>
	                    <th class="txtc">투찰금액</th>
	                    <th class="txtc">투찰률</th>
	                    <th class="txtc">낙찰</th>
	                    <th class="txtc">결격사유</th>
	                </tr>    
	            </thead>
                 	<c:if test="${empty lwetScsbidPrearngerList}">
						<tr class="row">
							<c:if test="${myBidResultDetail.PRCURE_SE_CD ne '1' && myBidResultDetail.MHRML_INCLS_AT eq 'N' }">
								<td colspan="7">데이터가 존재 하지 않습니다.</td>
							</c:if>
						</tr>
					</c:if>
					<c:if test="${not empty lwetScsbidPrearngerList && myBidResultDetail.BID_PROGRS_STTUS_CD ne 'PF99'}">
					<tbody id="lwetScsbidPrearngerListJson">
						<c:forEach var="data" items="${lwetScsbidPrearngerList}" varStatus="status" >
							<c:if test="${data.ENTRPS_REGIST_NO eq param.P_REPRSNT_ENTRPS_REGIST_NO  }">
								<tr class="row">
									<td>1<%-- ${data.RNUM } --%></td>
									<td class="left_T" onclick="prdlstAcctoDetailBidDtlsInqire('${data.ENTRPS_REGIST_NO}');" style="cursor: pointer;">${data.ENTRPS_NM}&nbsp;</td>
									<td>${data.RPRSNTV_NM}&nbsp;</td>
									<td class="right_T">${comFn:formatMoney(data.BDDPR_AMOUNT)}&nbsp;</td>
									<td>${data.BDDPR_RT}%</td>
									<td>
										<c:if test="${ data.SCSBID_AT eq 'Y' }">낙찰</c:if>
										</td>
									<td>
										${data.OPENG_RESN_CN}
										<c:if test="${not empty data.BID_INADQC_RESN_CN}">
										<div class="btn_before" onclick="bidResultBrdoResnInqire('${data.ENTRPS_REGIST_NO}');" style="cursor: pointer;" >사유보기</div>
										</c:if>
									</td>
								</tr>
							</c:if>
						</c:forEach>
					</tbody>
					 </c:if> 
            </table>
        </div>
        </c:if>
        
         <!-- 최저가 낙찰일 경우 -->
       <c:if test="${myBidResultDetail.SCSBID_MTH_CD ne '40' && myBidResultDetail.SCSBID_MTH_CD ne '31' && myBidResultDetail.BID_PROGRS_STTUS_CD ne 'PF99'}">
        <div class="view_area">
            <table class="table">
                <caption>입찰참여업체</caption>
                <colgroup>
                    <col width="40px">
                    <col width="400px">
                    <col width="100px">
                    <col width="120px">
                    <col width="100px">
                    <col width="100px">
                    <col width="120px">
                </colgroup>
                <thead>
	                <tr>
	                    <th class="txtc">번호</th>
	                    <th class="txtc">업체명</th>
	                    <th class="txtc">대표자</th>
	                    <th class="txtc">투찰금액</th>
	                    <th class="txtc">투찰률</th>
	                    <th class="txtc">낙찰</th>
	                    <th class="txtc">결격사유</th>
	                </tr>   
	            </thead>
                 	<c:if test="${empty scsbidPrearngerList}">
						<tr class="row">
								<td colspan="7">데이터가 존재 하지 않습니다.</td>
						</tr>
					</c:if>
					<c:if test="${not empty scsbidPrearngerList}">
					<tbody id="scsbidPrearngerListJson">
						<c:forEach var="data" items="${scsbidPrearngerList}" varStatus="status" >
							<c:if test="${data.ENTRPS_REGIST_NO eq param.P_REPRSNT_ENTRPS_REGIST_NO  }">
								<tr class="row">
									<td>1<%-- ${status.count } --%></td>
									<td class="left_T" onclick="prdlstAcctoDetailBidDtlsInqire('${data.ENTRPS_REGIST_NO}');" style="cursor: pointer;">${data.ENTRPS_NM}&nbsp;</td>
									<td>${data.RPRSNTV_NM}&nbsp;</td>
									<td class="right_T">${comFn:formatMoney(data.BDDPR_AMOUNT)}&nbsp;</td>
									<td><c:if test="${not empty data.BDDPR_RT }">${data.BDDPR_RT }%</c:if></td>
									<td>
											<c:if test="${ data.SCSBID_AT eq 'Y' }">
											낙찰
											</c:if>
											<c:if test="${ data.SCSBID_AT ne 'Y' }">
											&nbsp;
											</c:if>
									</td>
									<td>
									<c:if test="${not empty data.BID_INADQC_RESN_CN}">
											<div class="btn_before" id="ntatDspthBtn" onclick="bidResultBrdoResnInqire('${data.ENTRPS_REGIST_NO}');" style="cursor: pointer;" >사유보기</div>
									</c:if>
									</td>
								</tr>
							</c:if>
						</c:forEach>
					</tbody>
					 </c:if> 
            </table>
        </div>
        </c:if> 
        
	         <!-- 입찰결과가 유찰인 경우 -->
	        <c:if test="${myBidResultDetail.BID_PROGRS_STTUS_CD eq 'PF99'}">        
	        <div class="view_area">
	            <table class="table">
	                <caption>입찰참여업체</caption>
	                <colgroup>
	                    <col width="40px">
	                    <col width="400px">
	                    <col width="100px">
	                    <col width="180px">
	                    <col width="140px">
	                    <col width="120px">
	<%--                     <c:if test="${(myBidResultDetail.SCSBID_MTH_CD eq '20000' || myBidResultDetail.SCSBID_MTH_CD eq '20001'  || myBidResultDetail.SCSBID_MTH_CD eq '20002' || myBidResultDetail.SCSBID_MTH_CD eq '20003')}"> --%>
	<%--                       <col width="100px"> --%>
	<%--                     </c:if> --%>
	                </colgroup>
	                <thead>
		                <tr>
		                    <th class="txtc">번호</th>
		                    <th class="txtc">업체명</th>
		                    <th class="txtc">대표자</th>
		                    <th class="txtc">사업자번호</th>
		                    <th class="txtc">참가일자</th>
		                    <th class="txtc">포기신청서</th>
	<%-- 	                    <c:if test="${(myBidResultDetail.SCSBID_MTH_CD eq '20000' || myBidResultDetail.SCSBID_MTH_CD eq '20001'  || myBidResultDetail.SCSBID_MTH_CD eq '20002' || myBidResultDetail.SCSBID_MTH_CD eq '20003')}"> --%>
	<!-- 	                      <th>평가의견서</th> -->
	<%-- 	                    </c:if> --%>
		                </tr>
		            </thead>
	                 	<c:if test="${empty fibScsbidPrearngerList}">
							<tr class="row">
	<%-- 						  <c:choose> --%>
	<%-- 						    <c:when test="${(myBidResultDetail.SCSBID_MTH_CD eq '20000' || myBidResultDetail.SCSBID_MTH_CD eq '20001'  || myBidResultDetail.SCSBID_MTH_CD eq '20002' || myBidResultDetail.SCSBID_MTH_CD eq '20003')}"> --%>
	<!-- 						      <td colspan="7">데이터가 존재 하지 않습니다.</td> -->
	<%-- 						    </c:when> --%>
	<%-- 						    <c:otherwise> --%>
							      <td colspan="6">데이터가 존재 하지 않습니다.</td>
	<%-- 						    </c:otherwise> --%>
	<%-- 						  </c:choose> --%>
							</tr>
						</c:if>
						<c:if test="${not empty fibScsbidPrearngerList}">
							<c:forEach var="data" items="${fibScsbidPrearngerList}" varStatus="status" >
								<c:if test="${data.ENTRPS_REGIST_NO eq param.P_REPRSNT_ENTRPS_REGIST_NO  }">
									<tr class="row">
										<td>1<%-- ${data.RNUM } --%></td>
										<td class="left_T">${data.ENTRPS_NM}&nbsp;</td>
										<td>${data.RPRSNTV_NM}&nbsp;</td>
										<td>${comFn:formatBizNumber(data.BIZRNO)}&nbsp;</td>
										<td>${comFn:formatDate(data.PARTCPTN_DT,'yyyyMMddHHmmss','yyyy-MM-dd')}</td>
										<td>
										<c:if test="${ not empty data.BIAB_RESN_CN }">
											<div class="btn_before" onclick="bidResultAbandnReqstdocInqire('${data.ENTRPS_REGIST_NO}');" style="cursor: pointer;" >포기신청서보기</div>
										</c:if>
										</td>
	<%-- 									<c:if test="${(myBidResultDetail.SCSBID_MTH_CD eq '20000' || myBidResultDetail.SCSBID_MTH_CD eq '20001'  || myBidResultDetail.SCSBID_MTH_CD eq '20002' || myBidResultDetail.SCSBID_MTH_CD eq '20003')}"> --%>
	<%-- 									  <td onclick="qualEvlWrtopnInqire('${data.ENTRPS_REGIST_NO}');" style="cursor: pointer;" title="평가의견서를 조회할 수 있습니다."><button type="button" class="grayBtn S">평가의견서</button></td> --%>
	<%-- 									</c:if> --%>
									</tr>
								</c:if>
							</c:forEach>
						 </c:if> 
	            </table>
	        </div>
        </c:if>
        
        </c:if>
        	
	<div class="btn_wrap view_btn">
            <button type="button" class="btn btn_m btn_del" id="listBtn">목록</button>
	</div> <!--// btn_wrap E -->
</div>     
    
   	<form id="listFrm" method="POST" >
		<input type="hidden" name="resourceName" value="${param.resourceName}">
		<input type="hidden" name="P_PRCURE_BSNS_SE_CD" value="${myBidResultDetail.PRCURE_BSNS_SE_CD }">
	</form>
   	<form id="popupFrm" method="POST" >
		<input type="hidden" name="P_PBLANC_NO" value="${myBidResultDetail.PBLANC_NO}">
		<input type="hidden" name="P_PBLANC_ODR" value="${myBidResultDetail.PBLANC_ODR}">
		<input type="hidden" name="P_COMPNO_PRDPRC_NO" >
		<input type="hidden" name="P_ENTRPS_REGIST_NO">
		<input type="hidden" name="resourceName" value="${param.resourceName}">
		
	</form>
	<form id="sttusFrm" method="POST">
		<input type="hidden" name="P_PBLANC_NO" value="${myBidResultDetail.PBLANC_NO}">
		<input type="hidden" name="P_PBLANC_ODR" value="${myBidResultDetail.PBLANC_ODR}">
		<input type="hidden" name="P_SCSBID_MTH_CD" value="${myBidResultDetail.SCSBID_MTH_CD }">
		<input type="hidden" name="P_BID_LMT_AMOUNT" value="${myBidResultDetail.BID_LMT_AMOUNT }">
		<input type="hidden" name="P_PRCURE_SE_CD" value="${myBidResultDetail.PRCURE_SE_CD }">
		<input type="hidden" name="P_EVL_CN_TY">
		<input type="hidden" name="P_ENTRPS_REGIST_NO">
		<input type="hidden" name="P_ENTRPS_PRST_CD" >
		<input type="hidden" name="resourceName" value="${param.resourceName}">
	</form>
	<!-- 평가의견서 상세 팝업 폼 -->
	<form id="popupWrtopnFrm" method="POST" >
		<input type="hidden" name="P_PBLANC_NO" value="${myBidResultDetail.PBLANC_NO}">
		<input type="hidden" name="P_PBLANC_ODR" value="${myBidResultDetail.PBLANC_ODR}">
		<input type="hidden" name="P_ENTRPS_REGIST_NO">
	</form>
	
