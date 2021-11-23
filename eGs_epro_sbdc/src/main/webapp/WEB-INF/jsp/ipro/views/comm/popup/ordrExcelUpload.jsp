<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">--%>
<%--
 * 공통 > 요구부서 조회(팝업)
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

<link rel="stylesheet" type="text/css" href="${cssPath}/ipro/windowPop.css">
<%-- <link rel="stylesheet" type="text/css" href="${cssPath}/ipro/template/pagingPlace.css"> --%>
 
<script type="text/javascript" src="${jsPath}/ipro/views/comm/popup/ordrExcelUpload.js"></script>

<div class="pop_wrap">
	<div class="pop_header">
		<h1 class="tit">엑셀 일괄등록</h1>
	</div> <!--// pop_header E -->
	<div class="pop_container">
	</div>

<div class="view_wrap typeC">
				<div class="view_area m0 typeB">
			<table>
				<colgroup>
					<col width="15%" align="left">
					<col width="85%" align="left">
				</colgroup>
				<tr>
					<th>제목</th>
					<td><input type="text" class="w120"></td>
				</tr>
				<tr>
					<th>변경년월</th>
					<td>
						<div class="w338" > 
			                 <label for=" " class="blind">발주시기 년</label> 
			                 <select name="P_ORPR_ERA_Y" id="P_ORPR_ERA_Y_2" class="w120"></select> 년
			                <label for=" " class="blind">발주시기 월</label> &nbsp;&nbsp;&nbsp;&nbsp;
			                 <select name="P_ORPR_ERA_M" id="P_ORPR_ERA_M_2" class="w120"></select> 월
			             </div>
					</td>
				</tr>
				<tr>
					<th>엑셀파일</th>
					<td><input type="file" style="height: 29px;"></td>
				</tr>
			</table>
		</div>	
	</div>
           
	    <div class="T_btnLayer fr" style="margin-top: 20px;">
	    	<button type="button" class="btn btn_02 btn_blue" id="closeBtn" onclick="window.close();">저장</button>
			<button type="button" class="btn btn_02 btn_blue" id="closeBtn" onclick="window.close();">닫기</button>
	    </div>
</div>
