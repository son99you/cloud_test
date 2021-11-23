<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 입찰현황 > 유찰공고 목록
 *
 * <pre>
 * ebid 
 *    |_ popup
              |_ fibList.jsp
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

<link rel="stylesheet" type="text/css" href="${cssPath}/ipro/template/pagingPlace.css">
<link rel="stylesheet" type="text/css" href="${cssPath}/ipro/windowPop.css">

<script type="text/javascript" src="${jsPath}/ipro/views/ebid/popup/evlIemRegistForm.js"></script> 
 
<div id="windowPopup">
	<div class="tableLayer">
		<h3 class="windowTitle">평가항목 등록</h3>
		<div class="popSubTitle" style="">평가항목 등록</div>
		<table class="table">
			<caption>입찰설명회 목록</caption>
       		<colgroup>
            	<col width="15%"/>
            	<col width="85%"/>
           	</colgroup>
	    	<tr>
	        	<th>항목명</th>
	            <td><input type="text" id="P_TEXT" style="width: 95%"></td>
	        </tr>
	        <tr>
	        	<th>배점</th>
	            <td><input type="text" id="P_SCORE"> 점</td>
	        </tr>
	    </table>
    </div>
    <div class="T_btnLayer fr top10">
		<button type="button" class="blueBtn L pointer" id="registBtn">저장</button>
	   	<button type="button" class="blueBtn L pointer" id="closeBtn">닫기</button>
	</div>
	
</div> <!--// content E-->
