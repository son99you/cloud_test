<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 개찰현황 > 업체적격검토 상세(팝업)
 *
 * <pre>
 * ebid 
 *    |_ popup
 			  |_ entrpsProperPrprcDetail.jsp
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

<script type="text/javascript" src="${jsPath}/ipro/views/ebid/popup/entrpsProperPrprcRegistForm.js"></script> 
 
<div class="pop_wrap">
	<div class="pop_header">
		<h1 class="tit">
			<c:if test="${entrpsBidInfoInqire.SBID_MTCD eq '31'}">적격심사</c:if>
			<c:if test="${entrpsBidInfoInqire.SBID_MTCD ne '31'}">제안/규격서 검토</c:if>
		</h1>
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
				    <input type="hidden" name="P_BID_PSCD" value="${entrpsBidInfoInqire.BID_PSCD}">
				    <input type="hidden" id="P_SBID_MTCD" value="${entrpsBidInfoInqire.SBID_MTCD}">
					<input type="hidden" id="P_FILE_GROUP_FLAG" name="P_FILE_GROUP_FLAG" value="tndr">
					<input type="hidden" id="P_BID_TPI_SECD" name="P_BID_TPI_SECD" value="OP06">
					<input type="hidden" id="P_BID_SBMT_FSCD" name="P_BID_SBMT_FSCD" value="DO06">
					
					<table class="table">
				    	<colgroup>
				        	<col style="width: 25%;">
							<col style="width: 75%;">
				        </colgroup>
				    	<tr>
				        	<th>낙찰방법</th>
				            <td>${entrpsBidInfoInqire.SBID_MTCD_NM}</td>
				        </tr>
				    	<tr>
				        	<th>평가결과</th>
				            <td>
				            	<c:if test="${entrpsBidInfoInqire.ESTM_ELCD eq 'ELGB' }">적격</c:if>
				            	<c:if test="${entrpsBidInfoInqire.ESTM_ELCD eq 'NT_ELGB' }">부적격</c:if>
				            </td>
				        </tr>
				        <c:if test="${entrpsBidInfoInqire.SBID_MTCD eq '31'}"><!-- 적격심사 -->
					    	<tr>
					        	<th>적격제한점수</th>
					            <td>${entrpsBidInfoInqire.ELGB_LMT_SCR}</td>
					        </tr>
				        </c:if>
				        <c:if test="${entrpsBidInfoInqire.SBID_MTCD eq '40'}"><!-- 협상에 의한 계약 -->
					    	<tr>
					        	<th>기술 : 가격 점수</th>
					            <td>${entrpsBidInfoInqire.TCHN_SCR_RT} : ${entrpsBidInfoInqire.PRCE_SCR_RT}</td>
					        </tr>
				        </c:if>
				        <c:if test="${entrpsBidInfoInqire.SBID_MTCD ne '34'}"><!-- 적격심사 -->
					    	<tr>
					        	<th>평가점수</th>
					            <td>${entrpsBidInfoInqire.ESTM_SCR}</td>
					        </tr>
				        </c:if>
				        	
				        <c:if test="${entrpsBidInfoInqire.ESTM_ELCD eq 'NT_ELGB' }">
					        <tr>
					            <th>부적격사유</th>
					            <td>
					            	<textarea id="P_ESTM_NT_ELGB_RSN" name="P_ESTM_NT_ELGB_RSN" taView style="display: none;">${entrpsBidInfoInqire.ESTM_NT_ELGB_RSN}</textarea>
					            </td>
					        </tr>
				        </c:if>
				        <tr>
				            <th>검토결과첨부</th>
				            <td>
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
				            </td>
				        </tr>				        
				    </table>
				</form>
			</div>
			
			<div class="btn_wrap view_btn">
		    	<button type="button" class="btn btn_m btn_del" id="closeBtn">닫기</button>
		    </div>
		</div>		    
	</div>
</div> <!--// content E-->
<form id="delFrm" method="POST">
	<input type="hidden" name="P_ANNC_NO" value="${entrpsBidInfoInqire.ANNC_NO}">
    <input type="hidden" name="P_ANNC_NGR" value="${entrpsBidInfoInqire.ANNC_NGR}">
    <input type="hidden" name="P_ROUND_NO" value="${entrpsBidInfoInqire.ROUND_NO}">
    <input type="hidden" name="P_VEND_REG_NO" value="${entrpsBidInfoInqire.VEND_REG_NO}">
    <input type="hidden" name="P_BID_VEND_PSCD" value="OP04">
</form>
<%-- 파일 VIEW 폼 --%>
<form id="fileViewFrm" method="POST">
	<input type="hidden" id="P_FILE_GRP_NO" name="P_FILE_GRP_NO" value="${entrpsBidInfoInqire.FILE_GRP_NO}">
</form>