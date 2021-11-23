<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 평가계획현황 > 평가계획  평가의견서
 *
 * <pre>
 * ebid 
 *    |_ popup
               |_ evlWrtopnRegistForm.jsp
 * 
 * </pre>
 * @date : 2017. 06. 26
 * @version : 1.0
 * @author : 은우소프트 전상훈
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %> 
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %> 

<link rel="stylesheet" type="text/css" href="${cssPath}/ipro/template/pagingPlace.css">
<link rel="stylesheet" type="text/css" href="${cssPath}/ipro/windowPop.css">

<script type="text/javascript" src="${jsPath}/ipro/views/ebid/popup/evlWrtopnRegistForm.js"></script> 
 
<div id="windowPopup">
	<div class="tableLayer">
		<h3 class="windowTitle">평가의견서 조회</h3>
		<div class="popSubTitle" style="">평가표 기본정보</div>
		<table class="table">
			<caption>평가표 기본정보</caption>
          		<colgroup>
               	<col width="15%"/>
               	<col width="85%"/>
           	</colgroup>
	    	<tr>
	        	<th class="txtc">평가표명</th>
	        	<td>SW사업(ICT컨설팅) 평가기준표</td>
	       	</tr>
	       	<tr>
	        	<th class="txtc">평가유형</th>
	        	<td>소집</td>
	        </tr>
	        <tr>
	        	<th class="txtc">평가자유형</th>
	        	<td>
	        		<c:if test="${param.P_EVL_GUBN eq 'NPMT'}">비</c:if>상임위원
	        	</td>
	        </tr>
	    </table>
	</div>
	
	<div class="tableLayer">
		<div class="popSubTitle" style="">평가의견서 정보
			<div class="T_btnLayer textfr">
				<button type="button" class="grayBtn ico pointer" id="mrgOrdrRegistBtn">병합순서등록</button>
			</div>
		</div>
		<table class="table">
			<caption>평가의견서 정보</caption>
          		<colgroup>
               	<col width="20%"/>
               	<col width="*"/>
               	<col width="20%"/>
           	</colgroup>
	    	<tr>
	        	<th class="txtc">선택</th>
	        	<th class="txtc">평가항목명</th>
	            <th class="last txtc">배점</th>
	        </tr>
	        <tbody>
		        <tr>
		        	<td class="txtc" style="border-left: 1px solid #d5ddfd"><input type="checkbox"></td>
		            <td >제안사 소개</td>
		            <td class="txtc">5</td>
		        </tr>
		        <tr>
		        	<td class="txtc" style="border-left: 1px solid #d5ddfd"><input type="checkbox"></td>
		            <td >기술부문</td>
		            <td class="txtc">35</td>
		        </tr>
		        <tr>
		        	<td class="txtc" style="border-left: 1px solid #d5ddfd"><input type="checkbox"></td>
		            <td >사업관리 부문</td>
		            <td class="txtc">30</td>
		        </tr>
		        <tr>
		        	<td class="txtc" style="border-left: 1px solid #d5ddfd"><input type="checkbox"></td>
		            <td > 특수제안 </td>
		            <td class="txtc">5</td>
		        </tr>
		        <tr>
		        	<td class="txtc" style="border-left: 1px solid #d5ddfd"><input type="checkbox"></td>
		            <td >PM 역량</td>
		            <td class="txtc">5</td>
		        </tr>
	        </tbody>
	    </table>
	</div>	
	
	<div class="tableLayer">
		<div class="popSubTitle" style="">평가의견서 병합정보
			<div class="T_btnLayer textfr">
				<button type="button" class="grayBtn ico pointer" id="mrgOrdrResetBtn">병합순서재설정</button>
			</div>
		</div>
		<input type="hidden" value="1" id="cnt">
		<table class="table">
			<caption>평가의견서 정보</caption>
          		<colgroup>
               	<col width="20%"/>
               	<col width="*"/>
           	</colgroup>
	    	<tr>
	        	<th class="txtc">병합순서</th>
	        	<th class="txtc">평가의견서 병합 항목</th>
	        </tr>
	        <tr id="zeroTr">
	        	<td class="txtc" colspan="2" style="border-left: 1px solid #d5ddfd">평가의견서 병합 정보가 존재하지 않습니다.</td>
	        </tr>
	        <tbody id="showTr">
	        </tbody>
	    </table>
	</div>
	
	<div class="T_btnLayer fr top10">
    	<button type="button" class="blueBtn L pointer" id="closeBtn" >닫기</button>
    </div>
</div> <!--// content E-->
<form action="" id="popupFrm" method="POST"></form>