<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 입찰관리 > 산출내역서(견적서) 상세 팝업
 *
 * <pre>
 * ebid 
 *    |_ popup
 		      |_ vendTndrDocPopup.jsp
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

<script type="text/javascript" src="${jsPath}/ipro/views/ebid/popup/vendTndrDocPopup.js"></script> 
 
<div class="pop_wrap">
	<div class="pop_header">
		<h1 class="tit">산출내역서(견적서)</h1>
	</div> <!--// pop_header E -->
	<div class="pop_container">
		<div class="view_wrap typeC">
			<div class="tit_area">
				<h2 class="tit01">입찰개요</h2>
			</div>
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
		                <td>${bidPartcptReqstdocInqire.ANNC_NO}-${bidPartcptReqstdocInqire.ANNC_NGR}</td>
		                <th>공고일자</th>
		                <td>${comFn:formatDate(bidPartcptReqstdocInqire.ANNC_DT,'yyyyMMddHHmmss','yyyy-MM-dd')}</td>
		            </tr>
		            <tr>
		               	<th>공고명</th>
		               	<td colspan="3">${bidPartcptReqstdocInqire.BID_NM}</td>
		           	</tr>
		           	<tr>
		               	<th>계약구분</th>
		               	<td>
		               		${bidPartcptReqstdocInqire.CONT_SECD_NM}<c:if test="${bidPartcptReqstdocInqire.UPRC_YN eq 'Y'}">(단가)</c:if>
			            	<c:if test="${bidPartcptReqstdocInqire.CONT_DECD eq '200'}">&nbsp;/&nbsp;${bidPartcptReqstdocInqire.CONT_DECD_NM}</c:if>
		               	</td>
		               	<th>계약방법</th>
		               	<td>${bidPartcptReqstdocInqire.CONT_MTCD_NM}</td>
		           	</tr>
				</table>
			</div>
		
			<div class="tit_area">
				<h2 class="tit01">투찰참가자</h2>
			</div>
			<div class="view_area">
				<table >
			    	<colgroup>
			            <col style="width: 15%;">
						<col style="width: 35%;">
						<col style="width: 15%;">
						<col style="width: 35%;">
			        </colgroup>
			    	<tr>
		                <th>업체명</th>
		                <td colspan="3">${bidPartcptReqstdocInqire.VEND_NM }</td>
		            </tr>
		            <tr>
		                <th>사업자등록번호</th>
		                <td>${comFn:formatBizNumber(bidPartcptReqstdocInqire.BIZRNO) }</td>
		                <th>대표자</th>
		                <td>${bidPartcptReqstdocInqire.RPRS_NM}</td>
		            </tr>
		            <tr>
		            	<th>주소</th>
		            	<td colspan="3">${bidPartcptReqstdocInqire.ADDR_NM }&nbsp;${bidPartcptReqstdocInqire.ADDR_DENM }</td>
		            </tr>
		            <tr>
		            	<th>담당자</th>
		            	<td>${bidPartcptReqstdocInqire.CHRGR_NM }</td>
		            	<th>전화번호</th>
		            	<td>${bidPartcptReqstdocInqire.TEL_NO}</td>
		            </tr>
		            <tr>
		            	<th>담당자 이메일</th>
		            	<td colspan="3">${bidPartcptReqstdocInqire.EMAL_ADDR }</td>
		            </tr>
			    </table>
    		</div>
    	
    		<div class="tit_area">
				<h2 class="tit01">산출내역서</h2>
			</div>
           <div class="view_area fileViewer">
				<!-- 업로드 삽입. -->
				<script type="text/javascript">
					DEXT5UPLOAD.config.Mode = 'view';
					DEXT5UPLOAD.config.Width = '100%';
					DEXT5UPLOAD.config.FolderNameRule = '/bid';
					var dext5Upload = new Dext5Upload("upload");
				</script>	            	
			</div>
    
		    <div class="btn_wrap view_btn">
		    	<button type="button" class="btn btn_m btn_del" id="closeBtn" >닫기</button>
		    </div>
		</div>
    </div>
</div> <!--// content E-->
<form id="downFrm" method="POST" >
	<input type="hidden" name="P_FILE_SN">
	<input type="hidden" name="P_FILE_GRP_NO">
</form>
<%-- 파일 VIEW 폼 --%>
<form id="fileViewFrm" method="POST">
	<input type="hidden" id="P_FILE_GRP_NO" name="P_FILE_GRP_NO" value="${bidPartcptReqstdocInqire.FILE_GRP_NO}">
</form>