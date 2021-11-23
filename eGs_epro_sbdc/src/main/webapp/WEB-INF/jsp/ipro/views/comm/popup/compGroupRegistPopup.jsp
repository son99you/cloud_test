<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">--%>
<%--
 * 공통 > 견적확인
 *
 * <pre>
 * comm 
 *  |_popup
 *   |_rqstDeptList.jsp
 * 
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %>

<script type="text/javascript" src="${jsPath}/ipro/views/comm/popup/compGroupRegistPopup.js"></script>

<div class="pop_wrap">
	<div class="pop_header">
		<h1 class="tit">업체그룹등록</h1>
	</div> <!--// pop_header E -->
	<div class="pop_container">
		<div class="view_wrap typeA">
			<div class="tit_area">
<!-- 				<h2 class="tit01">업체그룹등록</h2> -->
			</div>
			<div class="view_area">
				<form id="saveFrm" method="POST" action="">
					<table>
						<colgroup>
							<col width="30%" align="left">
							<col width="70%" align="left">
						</colgroup>
						<tr height="29px">
							<th>그룹ID</th>
							<td><input type="text" id="P_GRP_ID" name="P_GRP_ID"></td>
						</tr>
						<tr height="29px">
							<th>그룹명</th>
							<td><input type="text" id="P_GRP_NM" name="P_GRP_NM"></td>
						</tr>
						<tr height="29px">
							<th>비고</th>
							<td><textarea  name="P_RMK"></textarea></td>
						</tr>
					</table>
				</form>
			</div>
	    	<div class="btn_wrap view_btn" style="margin-top: 5px;">
				<button type="button" class="btn btn_02 btn_revise" id="saveBtn">저장</button>
				<button type="button" class="btn btn_02 btn_close" id="closeBtn" onclick="window.close();">닫기</button>
	    	</div>
		</div> 
	</div>
</div>

	<form id="downloadFrm" method="POST" action="">
   		<input type="hidden" id="P_FILE_SN" name="P_FILE_SN" value="">
   		<input type="hidden" id="P_FILE_GRP_NO" name="P_FILE_GRP_NO" value="">
   	</form>