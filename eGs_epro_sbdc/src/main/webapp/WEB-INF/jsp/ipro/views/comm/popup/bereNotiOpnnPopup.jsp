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
		<h1 class="tit">사전규격공개 의견등록</h1>
	</div> <!--// pop_header E -->
	<div class="pop_container">
	</div>

<div class="view_wrap typeC">
				<div class="view_area m0 typeB">
			<table>
				<colgroup>
					<col width="15%" align="left">
					<col width="35%" align="left">
					<col width="15%" align="left">
					<col width="35%" align="left">
				</colgroup>
				<tr>
					<th>제목</th>
					<td colspan="3"><input type="text" value="관련문의"></td>
				</tr>
				<tr>
					<th>등록자</th>
					<td>
						<input type="text">
					</td>
					<th>이메일</th>
					<td>
						hci1135@eunwoosoft.com
					</td>
				</tr>
				<tr>
					<th>
						내용
					</th>
					<td colspan="3">
						<textarea rows="" cols="">해당공고문의</textarea>
					</td>
				</tr>
				<tr>
					<th>의견서첨부</th>
					<td colspan="3"><input type="file" style="height: 29px;"></td>
				</tr>
			</table>
		</div>	
	</div>
           
	    <div class="T_btnLayer fr" style="margin-top: 20px;">
	    	<button type="button" class="btn btn_02 btn_blue" id="closeBtn" onclick="window.close();">저장</button>
			<button type="button" class="btn btn_02 btn_blue" id="closeBtn" onclick="window.close();">닫기</button>
	    </div>
</div>
