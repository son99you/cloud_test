<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 입찰현황 > 입찰참가신청서 팝업
 *
 * <pre>
 * ebid 
 *    |_ popup
 		      |_ bidPartcptReqstdocInqire.jsp
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

<script type="text/javascript" src="${jsPath}/opro/views/ebid/popup/bidPartcptReqstdocInqire.js"></script> 
 
<div id="windowPopup">
	<div class="tableLayer">
		<h3 class="windowTitle">입찰참가신청서</h3>
		<div class="popSubTitle" style="">입찰개요</div>
		<table class="table">
	    	<colgroup>
	        	<col width="120px">
	            <col width="580px">
	        </colgroup>
	    	<tr class="line">
                <th>입찰공고번호</th>
                <td colspan="3">${bidPartcptReqstdocInqire.ANNC_NO}-${bidPartcptReqstdocInqire.ANNC_NGR}</td>
            </tr>
            <tr>
               	<th>입찰명</th>
               	<td colspan="3">${bidPartcptReqstdocInqire.BID_NM}&nbsp;</td>
           	</tr>
           	<tr>
               	<th>입찰구분</th>
               	<td colspan="3">${bidPartcptReqstdocInqire.PRCURE_SE_NM}&nbsp;</td>
           	</tr>
           	<tr>
               	<th>입찰방법</th>
               	<td colspan="3">${bidPartcptReqstdocInqire.CNTRCT_MTH_NM} &nbsp; > &nbsp; ${bidPartcptReqstdocInqire.SCSBID_MTH_NM}&nbsp;</td>
           	</tr>
		</table>
	</div>

	<div class="tableLayer">
		<div class="popSubTitle" style="">신청인</div>
		<table class="table">
	    	<colgroup>
	        	<col width="120px">
                 <col width="230px">
                 <col width="120px">
                 <col width="230px">
	        </colgroup>
	    	<tr>
                <th>업체명</th>
                <td colspan="3">${bidPartcptReqstdocInqire.VEND_NM }&nbsp;</td>
            </tr>
            <tr>
                <th>대표자</th>
                <td>${bidPartcptReqstdocInqire.RPRS_NM}&nbsp;</td>
                <th>사업자등록번호</th>
                <td>${comFn:formatBizNumber(bidPartcptReqstdocInqire.BIZRNO) }&nbsp;</td>
            </tr>
            <tr>
            	<th>주소</th>
            	<td colspan="3">${bidPartcptReqstdocInqire.VEND_ADDR }&nbsp;</td>
            </tr>
            <tr>
            	<th>담당자</th>
            	<td>${bidPartcptReqstdocInqire.CHRGR_NM }&nbsp;</td>
            	<th>전화번호</th>
            	<td>${bidPartcptReqstdocInqire.TEL_NO}&nbsp;</td>
            </tr>
            <tr>
            	<th>담당자 이메일</th>
            	<td colspan="3">${bidPartcptReqstdocInqire.EMAL_ADDR }&nbsp;</td>
            </tr>
	    </table>
    </div>
    
    <div class="tableLayer">
    	<div class="popSubTitle" style="">산출내역서 첨부파일</div>
    	<table class="table">
	    	<colgroup>
	        	<col width="120px">
	            <col width="580px">
	        </colgroup>
	    	<tr>
	        	<th scope="row" class="vtop">첨부파일</th>
				<td class="vtop">
					<div id="fileViewDiv" style="line-height: 30px;"> 
						<c:if test="${not empty bidAtchDocList }">
                  			<c:forEach items="${bidAtchDocList }" var="data" varStatus="status">
	                   			<div style="height: 30px;"> 
	                   				<a href="javascript:pageObj.download('${data.ATCHMNFL_SN}');" class="attfile">${data.ATCHMNFL_NM }</a>
	                    		</div>
	                    	</c:forEach> 
	                    </c:if>
	                    <c:if test="${empty bidAtchDocList}">
				        	<div style="height: 30px;"> 첨부파일 정보가 없습니다.</div>
				        </c:if>
                   	</div>
				</td>	
             </tr>
		</table>
    </div>
    
    <div class="tableLayer">
		<div class="popSubTitle" style="">입찰보증금</div>
		<table class="table">
	    	<colgroup>
	        	<col width="120px">
                 <col width="230px">
                 <col width="120px">
                 <col width="230px">
	        </colgroup>
	    	<%-- 납부방법이 [지급각서] 일 경우 --%>
            <c:if test="${bidPartcptReqstdocInqire.BIDGR_SECD eq '190000'}">
	            <tr>
	                <th>납부방법</th>
	                <td>${bidPartcptReqstdocInqire.BIDGR_SECD_NM }&nbsp;</td>
	               	<th>보증액</th>
	               	<td>￦ ${comFn:formatMoney(bidPartcptReqstdocInqire.BIDGR_AMT) }&nbsp;</td>
	            </tr>
	            <tr>
	            	<th colspan="4" style="text-align: center;">입찰보증금 지급각서</th>
	            </tr>
	            <tr>
	            	<td colspan="4">	상기인은 동 일찹참가에 있어 입찰보증금(입찰보증금 5%이상)을 납부하여야 하나, 귀 고객사의 사업에 관한 조달 및 계약규정시행세칙 제26조 제2항의 규정등에 
	            							의거 입찰보증금의 납부를 면제 받았는바, 이에 입찰보증금 귀속사유 발생시 동 입찰금액을 현금으로 즉시 납입할 것을 각서합니다.
	            							
	            	</td>
	            </tr>
            </c:if>
			<%-- 납부방법이 [지급각서] 일 경우 END --%>
            <%-- 납부방법이 [이행보증보험증권] 일 경우--%>
           	<c:if test="${bidPartcptReqstdocInqire.BIDGR_SECD eq '190001'}">
	            <tr>
	                <th>납부방법</th>
	                <td>${bidPartcptReqstdocInqire.BIDGR_SECD_NM }&nbsp;</td>
	                <th>보증서번호</th>
	                <td>${bidPartcptReqstdocInqire.BIDGR_NO }&nbsp;</td>
	            </tr>
	            <tr>
	               	<th>보증금</th>
	               	<td colspan="3">￦ ${comFn:formatMoney(bidPartcptReqstdocInqire.BIDGR_AMT) }&nbsp;</td>
<!-- 	               	<th>발급기관명</th> -->
<%-- 	                <td>${bidPartcptReqstdocInqire.ISSU_INSTT_NM}</td> --%>
	            </tr>
	            <tr>
	            	<th>보증첨부파일</th>
	                <td colspan="3"><div class="btn_before" style="cursor:pointer;"  onclick="pageObj.download('${bidPartcptReqstdocInqire.ATCHMNFL_SN}');" >다운로드</div></td>
	            </tr>
            </c:if>
           	<%-- 납부방법이 [이행보증보험증권] 일 경우 END --%>
         	<%-- 납부방법이 [현금납부] 일 경우--%>
           	<c:if test="${bidPartcptReqstdocInqire.BIDGR_SECD eq '190002'}">
	            <tr>
	                <th>납부방법</th>
	                <td colspan="3">${bidPartcptReqstdocInqire.BIDGR_SECD_NM }&nbsp;</td>
	            </tr>
	            <tr>
	               	<th>보증금</th>
	               	<td colspan="3">￦ ${comFn:formatMoney(bidPartcptReqstdocInqire.BIDGR_AMT) }&nbsp;</td>
<!-- 	               	<th>보증기간</th> -->
<%-- 	               	<td>${comFn:formatDate(bidPartcptReqstdocInqire.GRNTY_PD_BEGIN_DE,'yyyyMMdd','yyyy-MM-dd')} ~ ${comFn:formatDate(bidPartcptReqstdocInqire.GRNTY_PD_END_DE,'yyyyMMdd','yyyy-MM-dd')}</td> --%>
	            </tr>
	            <tr>
	            	<th>계좌이체확인서</th>
	                <td colspan="3"><div class="btn_before" style="cursor:pointer;"  onclick="pageObj.download('${bidPartcptReqstdocInqire.ATCHMNFL_SN}');" >다운로드</div></td>
	            </tr>
            </c:if>
           	<%-- 납부방법이 [현금납부] 일 경우 END --%>
         
            <tr>
                <th>공동수급방식</th>
                <td colspan="3">
					<c:if test="${bidPartcptReqstdocInqire.ASSO_SPDM_CD eq '240000'}">공동</c:if>
					<c:if test="${bidPartcptReqstdocInqire.ASSO_SPDM_CD eq '240001'}">단독</c:if>
					<c:if test="${bidPartcptReqstdocInqire.ASSO_SPDM_CD eq '240002'}">분담</c:if>
					&nbsp;
				</td>
            </tr>
	    </table>
<!-- 	    <div class="txtc top10"> -->
<!-- 	     	본인은 위의 번호를 공고한 입찰 건에 대해 고객사에서 정한 입찰유의서 및 입찰 공고사항을<br> -->
<!-- 	     	모두 승낙하고 입찰 참가 신청을 합니다.<br><br>  -->
<%-- 			${comFn:formatDate(bidPartcptReqstdocInqire.TPI_DT,'yyyyMMddHHmmss','yyyy-MM-dd')}<br>  --%>
<%-- 			신청자 : ${bidPartcptReqstdocInqire.RPRS_NM}<br>  --%>
<!-- 			고객사 이사장 귀하 -->
<!-- 		</div>  -->
		 <br><br>
      <div class="tableLayer">  
      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        본인은 위의 번호를 공고한 입찰 건에 대해 한국국제협력단에서 정한 입찰유의서 및 입찰 공고<br>
      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
	    사항을 모두 승낙하고 입찰 참가 신청을 합니다.
      
      
      <br><br>
      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      ${comFn:formatDate(bidPartcptReqstdocInqire.TPI_DT,'yyyyMMddHHmmss','yyyy-MM-dd')}
      <br>
      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      신청자 : ${bidPartcptReqstdocInqire.RPRS_NM}
      <br>
      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      한국국제협력단 이사장 귀하
     </div> 
      <br><br>
    </div>
    <div class="T_btnLayer fr top10">
    	<button type="button" class="blueBtn L pointer" id="closeBtn" >닫기</button>
    </div>
</div> <!--// content E-->
<form id="viewFrm" method="POST" action="">
	<input type="hidden" id="P_FILE_GRP_NO" name="P_FILE_GRP_NO" value="${bidPartcptReqstdocInqire.FILE_GRP_NO}">
</form>
<form id="downFrm" method="POST" action="">
	<input type="hidden" name="P_ATCHMNFL_SN" >
</form>
