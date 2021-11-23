<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 소액견적 > 업체적격검토 팝업
 *
 * <pre>
 * ebid 
 *    |_ popup
 			  |_ entrpsProperExmntRegistForm.jsp
 * 
 * </pre>
 * @date : 2017. 06. 14
 * @version : 1.0
 * @author : 은우소프트 전상훈
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %> 
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %> 

<div class="pop_wrap">
	<div class="pop_header">
		<h1 class="tit">참가적격검토</h1>
	</div> <!--// pop_header E -->
	<div class="pop_container">
		<div class="view_wrap typeA">
			<div class="view_area">
				<form id="registFrm" method="POST">
				    <input type="hidden" name="P_ANNC_NO" value="${entrpsBidInfoInqire.ANNC_NO}">
				    <input type="hidden" name="P_ANNC_NGR" value="${entrpsBidInfoInqire.ANNC_NGR}">
				    <input type="hidden" name="P_ROUND_NO" value="${entrpsBidInfoInqire.ROUND_NO}">
				    <input type="hidden" name="P_VEND_REG_NO" value="${entrpsBidInfoInqire.VEND_REG_NO}">
				    <input type="hidden" name="P_BID_VEND_PSCD" value="OP14">
					
					<table class="table">
				    	<colgroup>
				        	<col style="width: 30%;">
							<col style="width: 70%;">
				        </colgroup>
				    	<tr>
				        	<th>공고번호</th>
				            <td>${entrpsBidInfoInqire.ANNC_NO}-${entrpsBidInfoInqire.ANNC_NGR}</td>
				        </tr>
				        <tr>
				            <th>업체명</th>
				            <td>${entrpsBidInfoInqire.VEND_NM}</td>				        
				        </tr>
				        <c:if test="${not empty entrpsBidInfoInqire.REVW_DE}">
					        <tr>
					        	<th>검토일자</th>
					            <td>${comFn:formatDate(entrpsBidInfoInqire.REVW_DE,'yyyyMMdd','yyyy-MM-dd')}</td>
					        </tr>
				        </c:if>
				        <tr>
				        	<th>검토결과</th>
				            <td>${entrpsBidInfoInqire.BID_ELCD_NM }</td>
				        </tr>
				        <c:if test="${entrpsBidInfoInqire.BID_ELCD eq 'NT_ELGB' }">
					        <tr>
					            <th>부적격사유</th>
					            <td>
					            	<textarea taView style="display: none;">${entrpsBidInfoInqire.NT_ELGB_RSN}</textarea>
					            </td>
					        </tr>
				        </c:if>
				    </table>
				</form>
			</div>
		
			<div class="btn_wrap view_btn">
		    	<button type="button" class="btn btn_m btn_del" id="closeBtn" onclick="window.close();">닫기</button>
		    </div>
		</div>		    
	</div>
</div> <!--// content E-->