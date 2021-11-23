<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 개찰현황 > 업체적격검토 상세 (팝업)
 *
 * <pre>
 * ebid 
 *    |_ popup
 * 			  |_ negoRsltDetail.jsp
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

<script type="text/javascript" src="${jsPath}/ipro/views/ebid/popup/negoRsltDetail.js"></script> 
 
<div class="pop_wrap">
	<div class="pop_header">
		<h1 class="tit">협상결과</h1>
	</div> <!--// pop_header E -->
	
	<div class="pop_container">
		<div class="view_wrap typeA">
			<div class="view_area">
				<form id="registFrm" method="POST">
				    <input type="hidden" name="P_ANNC_NO" value="${negoRsltInfo.ANNC_NO}">
				    <input type="hidden" name="P_ANNC_NGR" value="${negoRsltInfo.ANNC_NGR}">
				    <input type="hidden" name="P_ROUND_NO" value="${negoRsltInfo.ROUND_NO}">
				    <input type="hidden" name="P_VEND_REG_NO" value="${negoRsltInfo.VEND_REG_NO}">
				    <input type="hidden" name="P_BID_VEND_PSCD" value="OP14">
				    <input type="hidden" name="P_BID_PSCD" value="${negoRsltInfo.BID_PSCD}">
				    
					<input type="hidden" id="P_FILE_GROUP_FLAG" name="P_FILE_GROUP_FLAG" value="biop">
					
					<table class="table">
				    	<colgroup>
				        	<col style="width: 20%;">
							<col style="width: 80%;">
				        </colgroup>
				    	<tr>
				        	<th>최종협상금액</th>
				            <td>${comFn:formatMoney(negoRsltInfo.LT_NEGO_AMT)}</td>
				        </tr>
				    	<tr>
				        	<th>협상일시</th>
				            <td>${comFn:formatDate(negoRsltInfo.NEGO_DT,'yyyyMMddHHmmss','yyyy-MM-dd')}</td>
				        </tr>
				        <tr>
				            <th>협상결과<br/>첨부파일</th>
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
	<input type="hidden" name="P_ANNC_NO" value="${negoRsltInfo.ANNC_NO}">
    <input type="hidden" name="P_ANNC_NGR" value="${negoRsltInfo.ANNC_NGR}">
    <input type="hidden" name="P_ROUND_NO" value="${negoRsltInfo.ROUND_NO}">
    <input type="hidden" name="P_VEND_REG_NO" value="${negoRsltInfo.VEND_REG_NO}">
    <input type="hidden" name="P_BID_VEND_PSCD" value="OP04">
</form>

<%-- 파일 VIEW 폼 --%>
<form id="fileViewFrm" method="POST">
	<input type="hidden" id="P_FILE_GRP_NO" name="P_FILE_GRP_NO" value="${negoRsltInfo.FILE_GRP_NO}">
</form>