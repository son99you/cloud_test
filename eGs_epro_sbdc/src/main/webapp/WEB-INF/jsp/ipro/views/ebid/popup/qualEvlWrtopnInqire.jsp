<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 평가결과현황 > 기술평가  의견상세 팝업
 *
 * <pre>
 * ebid 
 *    |_ popup
               |_ qualEvlWrtopnInqire.jsp
 * 
 * </pre>
 * @date : 2017. 06. 19
 * @version : 1.0
 * @author : 은우소프트 전상훈
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %> 
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %> 

<link rel="stylesheet" type="text/css" href="${cssPath}/ipro/template/pagingPlace.css">
<link rel="stylesheet" type="text/css" href="${cssPath}/ipro/windowPop.css">

<script type="text/javascript" src="${jsPath}/ipro/views/ebid/popup/qualEvlWrtopnInqire.js"></script> 
 
<div id="windowPopup" class="w_980">
	<div class="tableLayer">
		<h3 class="windowTitle">기술평가 의견상세 ( 업체 : 주식회사 은우소프트 님 )</h3>
		<div class="popSubTitle" style="">상임위원 평가의견서</div>
		<table class="table">
			<caption>상임위원 평가의견서</caption>
          		<colgroup>
               	<col width="20%"/>
               	<col width="10%"/>
               	<col width="*"/>
           	</colgroup>
	    	<tr>
	        	<th class="txtc">평가항목</th>
	        	<th class="txtc">평가위원</th>
	            <th class="last txtc">평가의견</th>
	        </tr>
	        <tbody>
		        <tr>
		        	<td class="txtc">제안사 소개</td>
		        	<td class="txtc">상임위원1</td>
		            <td >잘들음</td>
		        </tr>
		        <tr>
		        	<td class="txtc">전략 및 방법론</td>
		        	<td class="txtc">상임위원1</td>
		            <td >노련한 방법론</td>
		        </tr>
		        <tr>
		        	<td class="txtc">기술 및 기능</td>
		        	<td class="txtc">상임위원1</td>
		            <td >뛰어난 기술력</td>
		        </tr>
		        <tr>
		        	<td class="txtc">프로젝트 관리</td>
		        	<td class="txtc">상임위원1</td>
		            <td >탁월한 관리 능력</td>
		        </tr>
		        <tr>
		        	<td class="txtc">프로젝트 지원</td>
		        	<td class="txtc">상임위원1</td>
		            <td >탄탄한 지원능력</td>
		        </tr>
		        <tr>
		        	<td class="txtc">특수제안</td>
		        	<td class="txtc">상임위원1</td>
		            <td >기발한 제한</td>
		        </tr>
	        </tbody>
	    </table>
	</div>
	
	<div class="tableLayer">
		<div class="popSubTitle" style="">비상임위원 평가의견서</div>
		<table class="table">
			<caption>비상임위원 평가의견서</caption>
          		<colgroup>
               	<col width="20%"/>
               	<col width="10%"/>
               	<col width="*"/>
           	</colgroup>
	    	<tr>
	        	<th class="txtc">평가항목</th>
	        	<th class="txtc">평가위원</th>
	            <th class="last txtc">평가의견</th>
	        </tr>
	        <tbody>
		        <tr>
		        	<td class="txtc">제안사 소개</td>
		        	<td class="txtc">비상임위원1</td>
		            <td >좋음</td>
		        </tr>
		        <tr>
		        	<td class="txtc">전략 및 방법론</td>
		        	<td class="txtc">비상임위원1</td>
		            <td >훌륭함</td>
		        </tr>
		        <tr>
		        	<td class="txtc">기술 및 기능</td>
		        	<td class="txtc">비상임위원1</td>
		            <td >뛰어남</td>
		        </tr>
		        <tr>
		        	<td class="txtc">프로젝트 관리</td>
		        	<td class="txtc">비상임위원1</td>
		            <td >매우 뛰어남</td>
		        </tr>
		        <tr>
		        	<td class="txtc">프로젝트 지원</td>
		        	<td class="txtc">비상임위원1</td>
		            <td >좋음</td>
		        </tr>
		        <tr>
		        	<td class="txtc">특수제안</td>
		        	<td class="txtc">비상임위원1</td>
		            <td >혁신적임</td>
		        </tr>
	        </tbody>
	    </table>
	</div>	
	
	<div class="T_btnLayer fr top10">
    	<button type="button" class="blueBtn L pointer" name="closeBtn" >닫기</button>
    </div>
</div> <!--// content E-->
