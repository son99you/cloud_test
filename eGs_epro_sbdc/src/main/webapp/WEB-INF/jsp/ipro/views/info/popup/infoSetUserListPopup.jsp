<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 결재선추가 (넥스원 결재선등록->추가)
 *
 * <pre>
 * info 
 *    |_ popup
 			|_  infoSetUserListPopup.jsp
 * 
 * </pre>
 * @date : 2017. 06. 16
 * @version : 1.0
 * @author : 은우소프트 홍찬일
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %> 
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %> 

<link rel="stylesheet" type="text/css" href="${cssPath}/ipro/windowPop.css">
<link rel="stylesheet" type="text/css" href="${cssPath}/ipro/template/pagingPlace.css">

<script type="text/javascript" src="${jsPath}/ipro/views/info/popup/infoSetUserListPopup.js"></script> 
 
<div id="windowPopup" class="w_800">
		<h3 class="windowTitle">결재선추가</h3>
	<div class="formLayer">
		<br/><br/> &nbsp;
			<table class="contable2">
				<colgroup>												
					<col width="15%">
					<col width="35%">
					<col width="15%">
					<col width="35%">
				</colgroup>
				<tr height="24">
					
					<td>
						
						부서명
					</td>
					<td>
						<input type="text">
					</td>
					<td>
						
						담당자
					</td>
					<td>
						<input type="text">
					</td>
				</tr>
				<tr>
					<td class=" searchBtnTd" colspan="4">
						<button type="button" class="grayBtn ico" id="searchBtn">
		                    <img src="${imagePath}/ipro/icon/ico_search.png" alt="조회 버튼"> 조회
		                </button>
					</td>
				</tr>
			</table>
	</div>
		<div class="list">
			<table>
           		<colgroup>
					<col width="33%" >
					<col width="33%" >
					<col width="*" >
				</colgroup>
				<thead>
	                <tr>
                    	<th>부서명</th>
						<th>직급</th>
                    	<th>담당자</th>
	                </tr>
	            </thead>
				<tbody>
					 <tr class="pointer" onclick="regFn('결재팀','부장','임동일');">
						<td class="txtc">결재팀</td>
						<td class="txtc">부장</td>
						<td class="txtc">임동일</td>
					</tr>
					 <tr class="pointer" onclick="regFn('결재팀','차장','김명수');">
						<td class=" txtc">결재팀</td>
						<td class=" txtc">차장</td>
						<td class=" txtc">김명수</td>
					</tr>
					 <tr class="pointer" onclick="regFn('결재팀','차장','김봉수');">
						<td class="txtc">결재팀</td>
						<td class="txtc">차장</td>
						<td class="txtc">김봉수</td>
					</tr>
				</tbody>
			</table>
		</div>
	    <div class="paging_place" style="width: 740px;">
			<div class="paging_wrap"><a href="#" class="pprev" title="맨앞으로" style="display: none;"><span style="display: none;">맨앞으로</span></a><a href="#" class="prev" title="앞으로"><span style="display: none;">앞으로</span></a>
			<span><a href="#" class="active" title="1">1</a></span>
			<a href="#" class="next" title="뒤로"><span style="display: none;">뒤로</span></a><a href="#" class="nnext" title="맨뒤로" style="display: none;"><span style="display: none;">맨뒤로</span></a></div>
		</div>
		<div class="T_btnLayer fr">
			<button type="button" class="btn btn_s btn_gray2" id="" onclick="javascript:window.close();">닫기</button>
		</div>
</div>

<%-- DETAIL FORM --%>
<form id="detailFrm" method="POST" > 
	<input type="hidden" name="resourceName" value="${ param.resourceName }" >
</form>