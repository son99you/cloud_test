<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 입찰현황 > 입찰포기신청서 팝업
 *
 * <pre>
 * ebid 
 *    |_ popup
 		      |_ bidAbandnReqstdocInqire.jsp
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

<script type="text/javascript" src="${jsPath}/opro/views/ebid/popup/bidAbandnReqstdocInqire.js"></script> 
 
<div id="windowPopup">
	<div class="tableLayer">
		<h3 class="windowTitle">입찰포기신청서</h3>
		<table class="table">
	    	<colgroup>
	        	<col width="15%" />
	            <col width="85%" />
	        </colgroup>
	    	<tr class="line">
                <th>입찰번호</th>
                <td>${bidAbandnReqstdocInqire.ANNC_NO}-${bidAbandnReqstdocInqire.ANNC_NGR}</td>
            </tr>
            <tr>
                <th>입찰명</th>
                <td>${bidAbandnReqstdocInqire.BID_NM}&nbsp;</td>
            </tr>
            <tr>
                <th>업체명</th>
                <td>${bidAbandnReqstdocInqire.VEND_NM}&nbsp;</td>
            </tr>
            <tr>
                <th>포기일자</th>
                <td>${comFn:formatDate(bidAbandnReqstdocInqire.TPI_DT,'yyyyMMddHHmmss','yyyy-MM-dd')}&nbsp;</td>
            </tr>
            <tr>
                <th>포기사유</th>
                <td><textarea readonly="readonly"  rows="5" maxlength="4000" taView style="display: none;"><c:out value="${bidAbandnReqstdocInqire.BID_ABND_RSN }"></c:out></textarea>&nbsp;</td>
            </tr>
	    </table>
	</div>
	<div class="T_btnLayer fr">
    	<button type="button" class="blueBtn L pointer" id="closeBtn" >닫기</button>
    </div>
</div> <!--// content E-->
