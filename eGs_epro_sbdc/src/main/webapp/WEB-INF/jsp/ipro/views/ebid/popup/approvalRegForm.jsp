<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 입찰현황 > 유찰등록 팝업
 *
 * <pre>
 * ebid 
 *    |_ popup
 			  |_ fibRegistForm.jsp
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

<link rel="stylesheet" type="text/css" href="${cssPath}/ipro/template/pagingPlace.css">
<link rel="stylesheet" type="text/css" href="${cssPath}/ipro/windowPop.css">

<script type="text/javascript" src="${jsPath}/ipro/views/ebid/popup/prdprcRegistForm.js"></script> 
 
<div class="pop_wrap">
	<div class="pop_header">
		<h1 class="tit">대리결재자지정</h1>
	</div> <!--// pop_header E -->
	<div class="pop_container">
	</div>

<div class="view_wrap typeC">
				<div class="view_area m0 typeB">
			<table>
				<colgroup>
					<col width="15%" align="left">
					<col width="*%" align="left">
				</colgroup>
				<tr>
					<th>원결재자</th>
					<td>홍찬일(계약관리팀)</td>
				</tr>
				<tr>
					<th>대리결재자</th>
					<td>
						<input type="text">
					</td>
				</tr>
				<tr>
					<th>대리기간</th>
					<td>
						<input type="text" class="w170" date>~
						<input type="text" class="w170" date>
					</td>
				</tr>
				<tr>	
					<th>등록자</th>
					<td>홍찬일</td>
				</tr>
				<tr>
					<th>
						사유
					</th>
					<td colspan="3">
						<textarea rows="" cols=""></textarea>
					</td>
				</tr>
			</table>
		</div>	
	</div>
           
	    <div class="T_btnLayer fr" style="margin-top: 20px;">
	    	<button type="button" class="btn btn_02 btn_blue" id="closeBtn" onclick="window.close();">저장</button>
			<button type="button" class="btn btn_02 btn_blue" id="closeBtn" onclick="window.close();">닫기</button>
	    </div>
</div>
