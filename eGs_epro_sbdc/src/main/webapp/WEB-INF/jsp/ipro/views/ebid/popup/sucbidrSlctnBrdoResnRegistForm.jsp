<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 낙찰자선정 > 낙찰자선전 결격사유 등록
 *
 * <pre>
 * ebid 
 *    |_ popup
 			  |_ sucbidrSlctnBrdoResnRegistForm.jsp
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

<script type="text/javascript" src="${jsPath}/ipro/views/ebid/popup/sucbidrSlctnBrdoResnRegistForm.js"></script> 
 
<div class="pop_wrap">
	<div class="pop_header">
		<c:if test="${not empty entrpsInfoDetail.NT_ELGB_RSN }">
			<h1 class="tit">낙찰자선정 결격사유 조회</h1>
		</c:if>
		<c:if test="${empty entrpsInfoDetail.NT_ELGB_RSN }">
			<h1 class="tit">낙찰자선정 결격사유 등록</h1>
		</c:if>
	</div> <!--// pop_header E -->
	<div class="pop_container">
		<div class="view_wrap typeB">
			<div class="view_area">
			    <form id="registFrm" method="POST">
				    <input type="hidden" name="P_VEND_REG_NO" value="${ entrpsInfoDetail.VEND_REG_NO }">
				    <input type="hidden" name="P_ANNC_NO" value="${ bidInfoDetail.ANNC_NO }">
				    <input type="hidden" name="P_ANNC_NGR" value="${bidInfoDetail.ANNC_NGR }">
				    <input type="hidden" name="P_ROUND_NO" value="${bidInfoDetail.ROUND_NO }">
					<table class="table">
				    	<colgroup>
				        	<col width="15%" />
				            <col width="35%" />
				            <col width="15%" />
				            <col width="35%" />
				        </colgroup>
				    	<tr class="line">
			                <th>공고번호</th>
			                <td>${bidInfoDetail.ANNC_NO}-${bidInfoDetail.ANNC_NGR}</td>
			                <th>공고일자</th>
			            <td>${comFn:formatDate(bidInfoDetail.ANNC_DT,'yyyyMMddHHmmss','yyyy-MM-dd')}</td>
			            </tr>
			            <tr>
			                <th>입찰명</th>
			                <td colspan="3">${bidInfoDetail.BID_NM}</td>
			            </tr>
			            <tr>
			                <th>계약방법</th>
			               	<td>${bidInfoDetail.CONT_MTCD_NM}</td>
			               	<th>결격처리일자</th>
			            	<td>
			            		<c:if test="${empty entrpsInfoDetail.REG_DT }">
				            		${comFn:formatDate(today,'yyyyMMdd','yyyy-MM-dd') }
			            		</c:if>
			            		<c:if test="${not empty entrpsInfoDetail.REG_DT }">
				            		${comFn:formatDate(entrpsInfoDetail.REG_DT,'yyyyMMddHHmmss','yyyy-MM-dd') }
			            		</c:if>
			            		
			            	</td>              
						</tr>
				        <tr>
		                	<th>결격사유</th>
		                	<c:if test="${empty entrpsInfoDetail.NT_ELGB_RSN }">
		                	<td colspan="3">
<%-- 		                		<comTag:comCmcdCdValueComboBox id="P_BID_ELCD" name="P_BID_ELCD" cdId="BID_ELCD" cond1="" selectKey="${entrpsBidInfoInqire.BID_ELCD }" headerValue="선택" headerKey=""/> --%>
								<textarea id="P_NT_ELGB_RSN" name="P_NT_ELGB_RSN"></textarea>
		                	</td>
		                	</c:if>
		                	<c:if test="${not empty entrpsInfoDetail.NT_ELGB_RSN }">
		                	<td colspan="3">
		                		<textarea taView style="display: none;">${entrpsInfoDetail.NT_ELGB_RSN}</textarea>
		                	 </td>
		                	</c:if>
		                </tr>
				    </table>
				</form>
			</div> <!--// view_area E -->
			<div class="btn_wrap view_btn">
				<c:if test="${empty entrpsInfoDetail.NT_ELGB_RSN }">
		        	<button type="button" class="btn btn_02 btn_revise" id="registBtn">저장</button>
				</c:if>
    			<button type="button" class="btn btn_02 btn_close" id="closeBtn" >닫기</button>
    		</div>
   		</div> <!--// view_wrap E -->
	</div> <!--// pop_container E -->
</div> <!--// pop_wrap E -->



<form action="" id="fibFrm" method="POST"></form>
<form action="" id="listFrm" method="POST">
	<input type="hidden" name="resourceName" value="${ param.resourceName }" >
</form>
