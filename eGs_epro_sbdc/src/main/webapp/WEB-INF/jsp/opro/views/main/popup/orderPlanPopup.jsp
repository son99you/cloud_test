<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">--%>

<%--
 * 메인 > 발주계획 상세 팝업
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %>

<script type="text/javascript" src="${jsPath}/opro/views/main/popup/orderPlanPopup.js"></script>

<div class="pop_wrap">
	<div class="pop_header">
		<h1 class="tit">발주계획</h1>
	</div> <!--// pop_header E -->
	<div class="pop_container">
		<div class="view_wrap typeC">
			<div class="view_area m0 typeB">
					
				<input type="hidden" id="P_PLAN_NO" value="${yearOrderPlanDetail.PLAN_NO }">
					
				<table>
					<colgroup>
						<col width="15%">
						<col width="35%">
						<col width="15%">
						<col width="35%">
					</colgroup>
					<tr>
						<th>발주계획명</th>
						<td colspan="3">${yearOrderPlanDetail.PLAN_NM }</td>
					</tr>
					<tr>
						<th>담당부서</th>
						<td>${yearOrderPlanDetail.CHRGR_DEPT_NM }</td>
						<th>담당자/부서전화번호</th>
						<td>${yearOrderPlanDetail.CHRGR_NM }/${yearOrderPlanDetail.CHRGR_ID }</td>
					</tr>
					<tr>
						<th>계약구분</th>
						<td>${yearOrderPlanDetail.CONT_SECD_NM }</td>
						<th>계약방법</th>
						<td>${yearOrderPlanDetail.CONT_MTCD_NM }</td>
					</tr>
					<tr>
						<th>예산금액</th>
						<td>${comFn:formatMoney(yearOrderPlanDetail.BSNS_BDG_AMT)}</td>
						<th>발주시기</th>
						<td>${yearOrderPlanDetail.ORDR_YYYY}년 ${yearOrderPlanDetail.ORDR_MM }월 </td>
					</tr>
				</table>
			</div>
			
			<div class="tit_area">
	           	<h4 class="tit">첨부파일</h4>
			</div>
	           <div class="view_area fileViewer">
				<!-- 업로드 삽입. -->
				<script type="text/javascript">
					DEXT5UPLOAD.config.Mode = 'view';
					DEXT5UPLOAD.config.Width = '100%';
					DEXT5UPLOAD.config.FolderNameRule = '/year';
					var dext5Upload = new Dext5Upload("upload");
				</script>	            	
			</div>	
			<div id="upload_fileInfo"></div>
			
			<div class="btn_wrap view_btn">
				<button type="button" class="btn btn_m btn_del" onclick="javascript:window.close();">닫기</button>
			</div> <!--// btn_wrap E -->
			
		</div>
	</div> 
</div>

<form id="downloadFrm" method="POST" > 
	<input type="hidden" name="P_ATCHMNFL_SN">  
	<input type="hidden" name="resourceDesc" value="${ param.resourceDesc }" >
	<input type="hidden" name="resourceName" value="${ param.resourceName }" >
</form>

<%-- 파일 VIEW 폼 --%>
<form id="fileViewFrm" method="POST">
	<input type="hidden" id="P_FILE_GRP_NO" name="P_FILE_GRP_NO" value="${yearOrderPlanDetail.FILE_GRP_NO}">
</form>