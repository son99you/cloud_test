<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 입찰현황 > 관심입찰업체목록
 *
 * <pre>
 * ebid 
 *    |_ popup
              |_ actHistInqire.jsp
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

<script type="text/javascript" src="${jsPath}/ipro/views/ebid/popup/actHistInqire.js"></script> 
 
<div id="windowPopup" class="w_980">
	<div class="tableLayer">
		<h3 class="windowTitle" style="">활동이력 조회</h3>
		<div class="popSubTitle" style="">전문가 수행경험 정보</div>
		<table class="table">
			<caption>전문가 수행경험 정보</caption>
              		<colgroup>
                   	<col width="10%"/>
                   	<col width="*"/>
                   	<col width="15%"/>
                   	<col width="15%"/>
                   	<col width="15%"/>
            	</colgroup>
	    	<tr>
	            <th class="txtc">번호</th>
	            <th class="txtc">사업명</th>
	            <th class="txtc">기간</th>
	            <th class="txtc">대상국가명</th>
	            <th class="last txtc">분야명</th>
	        </tr>
	        <tr>
	        	<td class="txtc">1</td>
	        	<td >통합 시스템 구축 사업</td>
	            <td >2014-08-01~2016-02-28</td>
	            <td class="txtc">대한민국</td>
	            <td class="txtc">SW 개발</td>
	        </tr>
	    </table>
    </div>
    
    <div class="tableLayer">
		<div class="popSubTitle" style="">전문가 경력 정보</div>
		<table class="table">
			<caption>전문가 경력 정보</caption>
              		<colgroup>
                   	<col width="10%"/>
                   	<col width="*"/>
                   	<col width="15%"/>
                   	<col width="15%"/>
                   	<col width="15%"/>
            	</colgroup>
	    	<tr>
	            <th class="txtc">번호</th>
	            <th class="txtc">근무처명</th>
	            <th class="txtc">기간</th>
	            <th class="txtc">부서명</th>
	            <th class="last txtc">직책명</th>
	        </tr>
	        <tr>
	        	<td class="txtc">1</td>
	        	<td >LIZ 시스템</td>
	            <td >1991-04-01</td>
	            <td class="txtc">개발본부</td>
	            <td class="txtc">부장</td>
	        </tr>
	    </table>
    </div>
    
    <div class="tableLayer">
		<div class="popSubTitle" style="">전문가 학력 정보</div>
		<table class="table">
			<caption>전문가 학력 정보</caption>
              		<colgroup>
                   	<col width="10%"/>
                   	<col width="*"/>
                   	<col width="15%"/>
                   	<col width="15%"/>
                   	<col width="15%"/>
            	</colgroup>
	    	<tr>
	            <th class="txtc">번호</th>
	            <th class="txtc">학교명</th>
	            <th class="txtc">기간</th>
	            <th class="txtc">전공명</th>
	            <th class="last txtc">학위구분</th>
	        </tr>
	        <tr>
	        	<td class="txtc">1</td>
	        	<td >서울시립대학교</td>
	            <td >1985-03-01~1989-02-28</td>
	            <td class="txtc">컴퓨터공학</td>
	            <td class="txtc">학사</td>
	        </tr>
	        <tr>
	        	<td class="txtc">2</td>
	        	<td >연세대학원</td>
	            <td >1989-03-01~1991-02-28</td>
	            <td class="txtc">컴퓨터공학</td>
	            <td class="txtc">석사</td>
	        </tr>
	    </table>
    </div>
	<div class="T_btnLayer fr top10">
    	<button type="button" class="blueBtn L pointer" id="closeBtn" >닫기</button>
    </div>
</div> <!--// content E-->
