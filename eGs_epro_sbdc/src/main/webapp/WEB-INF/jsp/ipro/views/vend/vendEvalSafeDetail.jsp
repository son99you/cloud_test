<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 안전관리평가 상세(신분당선 안전관리평가)
 *
 * <pre>
 * vend
 *    |_ vendEvalSafeDetail.jsp
 * 
 * </pre>
 * @date : 2017. 06. 20
 * @version : 1.0
 * @author : 은우소프트 홍찬일
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %> 
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %> 

<link rel="stylesheet" type="text/css" href="${cssPath}/ipro/bodyBasic.css">
<link rel="stylesheet" type="text/css" href="${cssPath}/ipro/template/buttonStyle.css">
<link rel="stylesheet" type="text/css" href="${cssPath}/ipro/template/pagingPlace.css">
<link rel="stylesheet" type="text/css" href="${cssPath}/ipro/template/calendar.css">

<script type="text/javascript" src="${jsPath}/ipro/views/vend/vendEvalSafeDetail.js"></script> 
 
<div class="content">
		<h3>안전환경평가 상세</h3>
		<div class="conts_wrap">
		<div class="btn_wrap view_btn fr">
	  		<button type="button" class="btn btn_02 btn_sch" id="listBtn">목록</button>
   		</div>	
   		<br>
	<table>
		<colgroup>												
			<col width="15%" align="left">
			<col width="35%" align="left">
			<col width="15%" align="left">
			<col width="35%"   align="left">
		</colgroup>
		<tr height="24">
			<th class=" txtl">
				<img src="${imagePath}/ipro/main/dot_blue.gif" width="11" height="11">
				년도
			</th>
			<td>
				2017년도 30차
			</td>
			<th class=" txtl">
				<img src="${imagePath}/ipro/main/dot_blue.gif" width="11" height="11">
				평가명
			</th>
			<td>
				2017년05월30일 평가
			</td>
		</tr>
		<tr height="24">
			<th class=" txtl">
				<img src="${imagePath}/ipro/main/dot_blue.gif" width="11" height="11">
				평가유형
			</th>
			<td>
				등급평가
			</td>
			<th class=" txtl">
				<img src="${imagePath}/ipro/main/dot_blue.gif" width="11" height="11">
				평가수행기간
			</th>
			<td>
				2017-06-01 ~ 2017-06-14 
			</td>
		</tr>
		<tr height="24">
			<th class=" txtl">
				<img src="${imagePath}/ipro/main/dot_blue.gif" width="11" height="11">
				평가실적기간
			</th>
			<td>
				2017-06-01 ~ 2017-06-14 
			</td>
			<th class=" txtl">
				<img src="${imagePath}/ipro/main/dot_blue.gif" width="11" height="11">
				진행상태
			</th>
			<td>
				상반기현업평가완료  
			</td>
		</tr>
	</table>
	<div class="contentTitle" style="">평가업체</div>
			<table>
           		<colgroup>
					<col width="10%" >
					<col width="30%" >
					<col width="15%">  
					<col width="15%">
					<col width="15%">
					<col width="15%">
				</colgroup>
				<thead>
	                <tr>
                    	<th class="txtc">순번</th>
						<th class="txtc">협력업체</th>
						<th class="txtc">대표자</th>
						<th class="txtc">소싱그룹</th>
						<th class="txtc">부서명</th>
						<th class="txtc">총점</th>
	                </tr>
	            </thead>
				<tbody>
					<tr class="pointer" onclick="movePage('/vend/vendEvalSafeDetailEvalView.do', '');">
						<td class="txtc">1</td>
						<td class="txtl">(주)한국철도기술공사</td>
						<td class="txtc">김대영</td>
						<td class="txtc">공통</td>
						<td class="txtc">영업팀</td>
						<td class="txtc">100</td>
					</tr>
					<tr class="pointer" onclick="movePage('/vend/vendEvalSafeDetailEvalView.do', '');">
						<td class="txtc">2</td>
						<td class="txtl">(재)한국재난연구원</td>
						<td class="txtc">윤영조</td>
						<td class="txtc">기계</td>
						<td class="txtc">영업팀</td>
						<td class="txtc">100</td>
					</tr>
					<tr class="pointer" onclick="movePage('/vend/vendEvalSafeDetailEvalView.do', '');">
						<td class="txtc">3</td>
						<td class="txtl">㈜평일</td>
						<td class="txtc">노관성</td>
						<td class="txtc">선로</td>
						<td class="txtc">영업팀</td>
						<td class="txtc">80</td>
					</tr>
					<tr>
						<td class="txtc">4</td>
						<td class="txtl">㈜한국IT</td>
						<td class="txtc">최지윤</td>
						<td class="txtc">공통</td>
						<td class="txtc">영업팀</td>
						<td class="txtc">70</td>
					</tr>
					<tr class="pointer" onclick="movePage('/vend/vendEvalSafeDetailEvalView.do', '');">
						<td class="txtc">5</td>
						<td class="txtl">㈜한국화이바</td>
						<td class="txtc">조문수</td>
						<td class="txtc">전력</td>
						<td class="txtc">영업팀</td>
						<td class="txtc">70</td>
					</tr>
					<tr class="pointer" onclick="movePage('/vend/vendEvalSafeDetailEvalView.do', '');">
						<td class="txtc">6</td>
						<td class="txtl">신세계산업㈜ </td>
						<td class="txtc">장정열</td>
						<td class="txtc">통신</td>
						<td class="txtc">영업팀</td>
						<td class="txtc">60</td>
					</tr>
				</tbody>
			</table>
		</div>
		    <div class="paging_place">
				<div class="paging_wrap"><a href="#" class="pprev" title="맨앞으로" style="display: none;"><span style="display: none;">맨앞으로</span></a><a href="#" class="prev" title="앞으로"><span style="display: none;">앞으로</span></a>
				<span><a href="#" class="active" title="1">1</a></span>
				<a href="#" class="next" title="뒤로"><span style="display: none;">뒤로</span></a><a href="#" class="nnext" title="맨뒤로" style="display: none;"><span style="display: none;">맨뒤로</span></a></div>
			</div>
</div>
</div>

<%-- DETAIL FORM --%>
<form id="detailFrm" method="POST" > 
	<input type="hidden" name="resourceName" value="${ param.resourceName }" >
</form>