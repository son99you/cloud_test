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

<script type="text/javascript" src="${jsPath}/ipro/views/ebid/popup/bidAbandnReqstdocInqire.js"></script> 
 
<div class="pop_wrap">
	<div class="pop_header">
		<h1 class="tit">입찰포기신청서</h1>
	</div> <!--// pop_header E -->
	<div class="pop_container">
		<div class="view_wrap typeC">
			<div class="view_area">
				<table class="table">
			    	<colgroup>
			        	<col style="width: 15%;">
						<col style="width: 35%;">
						<col style="width: 15%;">
						<col style="width: 35%;">
			        </colgroup>
			    	<tr class="line">
		               <th>공고번호</th>
		               <td>${bidAbandnReqstdocInqire.ANNC_NO}-${bidAbandnReqstdocInqire.ANNC_NGR}</td>
		               <th>공고일자</th>
		              	<td>
		                  	${comFn:formatDate(bidAbandnReqstdocInqire.ANNC_DT,'yyyyMMddHHmmss','yyyy-MM-dd')}
		              	</td>	     
		           </tr>
		           <tr>
		               <th>공고명</th>
		               <td colspan="3">${bidAbandnReqstdocInqire.BID_NM}</td>
		           </tr>
		            <tr>
		                <th>업체명</th>
		                <td>${bidAbandnReqstdocInqire.VEND_NM}</td>
		                <th>입찰포기일시</th>
		                <td>${comFn:formatDate(bidAbandnReqstdocInqire.REG_DT,'yyyyMMddHHmmss','yyyy-MM-dd')}</td>
		            </tr>
		            <tr>
		                <th>입찰포기사유</th>
		                <td colspan="3"><textarea readonly="readonly" taView style="display: none;"><c:out value="${bidAbandnReqstdocInqire.BID_ABND_RSN }"></c:out></textarea></td>
		            </tr>
			    </table>
			</div>
			<div class="btn_wrap view_btn">
		    	<button type="button" class="btn btn_02 btn_close" id="closeBtn" >닫기</button>
		    </div>
		</div>
	</div>
</div> <!--// content E-->
