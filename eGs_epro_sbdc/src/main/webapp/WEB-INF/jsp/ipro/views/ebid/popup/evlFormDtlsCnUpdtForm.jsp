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

<script type="text/javascript" src="${jsPath}/ipro/views/ebid/popup/evlFormDtlsCnUpdtForm.js"></script> 
 
<div id="windowPopup">
	<div class="tableLayer">
		<h3 class="windowTitle" style="">평가상세항목 내용 등록</h3>
		
		<table class="table">
	    	<colgroup>
	        	<col width="20%" />
	            <col width="80%" />
	        </colgroup>
	    	<tr>
	        	<th>평가항목명</th>
	            <td>경영상태</td>
	        </tr>
	        <tr>
	        	<th>평가상세항목명</th>
	            <td>${param.P_TEXT}</td>
	        </tr>
	        <tr>
	            <th>평가상세항목 내용</th>
	            <td>
	            	<textarea rows="8" cols="40" style="width: 99%"><c:if test="${param.P_REGIST_AT eq 'Y' }">- 참여사 전체의 신용평가 등급에 대해 정량평가 기준표에 의거하여 평가한다.평가</c:if></textarea>
	            </td>
	        </tr>
	    </table>
	</div>
	<div class="T_btnLayer fr top10">
		<button type="button" class="blueBtn L pointer" id="registBtn" >저장</button>
    	<button type="button" class="blueBtn L pointer" id="closeBtn" >닫기</button>
    </div>
</div> <!--// content E-->

<form action="" id="fibFrm" method="POST"></form>
<form action="" id="listFrm" method="POST">
	<input type="hidden" name="resourceName" value="${ param.resourceName }" >
</form>
