<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 안전환경평가(신분당선 안전환경평가)
 *
 * <pre>
 * vend
 *    |_ vendEvalSafeList.jsp
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

<script type="text/javascript" src="${jsPath}/ipro/views/vend/vendEvalSafeList.js"></script> 
 
<div class="content">
		<h3>안전환경평가 목록</h3>
		<div class="list">
		<div id="searchWrap">
			<table>
				<colgroup>												
					<col width="15%" align="left">
					<col width="35%" align="left">
					<col width="15%" align="left">
					<col width="35%"   align="left">
				</colgroup>
				<tr height="24">
					<td>
						
						<input type="hidden" id="dyyyy_sch_hid" value="">
						년도
					</td>
					<td>
						<select name="dyyyy_sch" onchange="yyyySet(this);" ></select>
					</td>
					<td>
						
						평가명
					</td>
					<td>
						<input type="text" >
					</td>
				</tr>
				<tr height="24">
					<td>
					</td>
					<td>
					</td>
					<td>
						
						진행상태
					</td>
					<td>
						<select >
							<option>전체 </option>    
							<option>작성</option>    
							<option>평가진행</option>
							<option>평가종료</option>
							<option>상반기현업평가완료</option>
							<option>하반기현업평가완료</option>
						</select>
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
	<br>
	<div class="list_wrap mt30 contentWrap">
		<div class="list_top">
			<p class="total">총 <span></span>건</p>		
			<!--  <div class="btn_right"> -->
				<!-- <button type="button" class="btn btn_02 btn_down">엑셀 다운로드</button> -->
			<!-- </div> -->
		</div> <!--// list_top E -->
		<div class="list_conts">	
			<table>
           		<colgroup>
					<col width="150" >
					<col width="150">
					<col width="*">  
					<col width="250" >
					<col width="250" >
				</colgroup>
				<thead>
	                <tr>
                    	<th>년도</th>
						<th>차수</th>
						<th>평가명</th>
						<th>평가유형</th>
						<th>진행상태</th>
	                </tr>
	            </thead>
				<tbody>
					 <tr class="pointer" onclick="movePage('/vend/vendEvalSafeDetail.do', '');">
						<td class="txtc">2017</td>
						<td class="txtc">30</td>
						<td class="txtl">2017년 06월 15일 30차</td>
						<td class="txtc">등급평가</td>
						<td class="txtc">상반기현업평가완료</td>
					</tr>
					 <tr class="pointer" onclick="movePage('/vend/vendEvalSafeDetail.do', '');">
						<td class=" txtc">2017</td>
						<td class=" txtc">29</td>
						<td class=" txtl">2017년 06월 01일 29차</td>
						<td class=" txtc">등급평가</td>
						<td class=" txtc">상반기현업평가완료</td>
					</tr>
					 <tr class="pointer" onclick="movePage('/vend/vendEvalSafeDetail.do', '');">
						<td class="txtc">2017</td>
						<td class="txtc">28</td>
						<td class="txtl">2017년 05월 15일 28차</td>
						<td class="txtc">등급평가</td>
						<td class="txtc">상반기현업평가완료</td>
					</tr>
					 <tr class="pointer" onclick="movePage('/vend/vendEvalSafeDetail.do', '');">
						<td class=" txtc">2017</td>
						<td class=" txtc">27</td>
						<td class=" txtl">2017년 05월 01일 27차</td>
						<td class=" txtc">등급평가</td>
						<td class=" txtc">상반기현업평가완료</td>
					</tr>
					 <tr class="pointer" onclick="movePage('/vend/vendEvalSafeDetail.do', '');">
						<td class="txtc">2017</td>
						<td class="txtc">26</td>
						<td class="txtl">2017년 04월 15일 26차</td>
						<td class="txtc">등급평가</td>
						<td class="txtc">하반기현업평가완료</td>
					</tr>
					 <tr class="pointer" onclick="movePage('/vend/vendEvalSafeDetail.do', '');">
						<td class=" txtc">2017</td>
						<td class=" txtc">25</td>
						<td class=" txtl">2017년 04월 01일 25차</td>
						<td class=" txtc">등급평가</td>
						<td class=" txtc">하반기현업평가완료</td>
					</tr>
					 <tr class="pointer" onclick="movePage('/vend/vendEvalSafeDetail.do', '');">
						<td class="txtc">2017</td>
						<td class="txtc">24</td>
						<td class="txtl">2017년 03월 15일 24차</td>
						<td class="txtc">등급평가</td>
						<td class="txtc">상반기현업평가완료</td>
					</tr>
					 <tr class="pointer" onclick="movePage('/vend/vendEvalSafeDetail.do', '');">
						<td class=" txtc">2017</td>
						<td class=" txtc">23</td>
						<td class=" txtl">2017년 03월 01일 23차</td>
						<td class=" txtc">등급평가</td>
						<td class=" txtc">하반기현업평가완료</td>
					</tr>
					 <tr class="pointer" onclick="movePage('/vend/vendEvalSafeDetail.do', '');">
						<td class="txtc">2017</td>
						<td class="txtc">22</td>
						<td class="txtl">2017년 02월 15일 22차</td>
						<td class="txtc">등급평가</td>
						<td class="txtc">하반기현업평가완료</td>
					</tr>
					 <tr class="pointer" onclick="movePage('/vend/vendEvalSafeDetail.do', '');">
						<td class=" txtc">2017</td>
						<td class=" txtc">21</td>
						<td class=" txtl">2017년 02월 01일 21차</td>
						<td class=" txtc">등급평가</td>
						<td class=" txtc">상반기현업평가완료</td>
					</tr>
				</tbody>
			</table>
		</div>	
		</div>
		    <div class="paging_place">
				<div class="paging_wrap"><a href="#" class="pprev" title="맨앞으로" style="display: none;"><span style="display: none;">맨앞으로</span></a><a href="#" class="prev" title="앞으로"><span style="display: none;">앞으로</span></a>
				<span><a href="#" class="active" title="1">1</a></span>
				<span><a href="#" title="2">2</a></span>
				<span><a href="#" title="3">3</a></span>
				<span><a href="#" title="4">4</a></span>
				<span><a href="#" title="5">5</a></span>
				<span><a href="#" title="6">6</a></span>
				<span><a href="#" title="7">7</a></span>
				<span><a href="#" title="8">8</a></span>
				<span><a href="#" title="9">9</a></span>
				<span><a href="#" title="10">10</a></span>
				<a href="#" class="next" title="뒤로"><span style="display: none;">뒤로</span></a><a href="#" class="nnext" title="맨뒤로" style="display: none;"><span style="display: none;">맨뒤로</span></a></div>
			</div>
</div>
</div>

<%-- DETAIL FORM --%>
<form id="detailFrm" method="POST" > 
	<input type="hidden" name="resourceName" value="${ param.resourceName }" >
</form>