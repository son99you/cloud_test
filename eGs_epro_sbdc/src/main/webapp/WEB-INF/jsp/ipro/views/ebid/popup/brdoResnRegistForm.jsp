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

<script type="text/javascript" src="${jsPath}/ipro/views/ebid/popup/brdoResnRegistForm.js"></script> 
 
<div id="windowPopup">
	<div class="tableLayer">
		<h3 class="windowTitle" style="">결렬사유등록</h3>
		
		<table class="table">
	    	<colgroup>
	        	<col width="15%" />
	            <col width="85%" />
	        </colgroup>
	    	<tr>
	        	<th>입찰번호</th>
	            <td>P2017-00099-1</td>
	        </tr>
	        <tr>
	        	<th>결렬처리일자</th>
	            <td id="nowDate"></td>
	        </tr>
	        <tr>
	            <th>결렬사유내용</th>
	            <td>
	            	<c:if test="${param.P_REGIST_AT eq 'N' }"> 
	            		<textarea rows="8" cols="40" style="width: 99%"></textarea>
	            	</c:if>
	            	<c:if test="${param.P_REGIST_AT eq 'Y' }">
	            		위원의 개인사정으로 인한 교섭결렬
	            	</c:if>
	            </td>
	        </tr>
	    </table>
	</div>
	<div class="T_btnLayer fr top10">
		<c:if test="${param.P_REGIST_AT eq 'N' }"> 
			<button type="button" class="blueBtn L pointer" id="registBtn" >저장</button>
		</c:if>
    	<button type="button" class="blueBtn L pointer" id="closeBtn" >닫기</button>
    </div>
</div> <!--// content E-->
