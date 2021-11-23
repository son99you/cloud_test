<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">--%>
<%--
 * 공통 > 요구부서 조회(팝업)
 *
 * <pre>
 * comm 
 *  |_popup
 *   |_rqstDeptList.jsp
 * 
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %>

<link rel="stylesheet" type="text/css" href="${cssPath}/ipro/windowPop.css">
<link rel="stylesheet" type="text/css" href="${cssPath}/ipro/template/pagingPlace.css">

<script type="text/javascript" src="${jsPath}/ipro/views/comm/popup/ordrPopup.js"></script>

<div id="windowPopup" class="w_800">
	<h3 class="windowTitle">업체 목록</h3>
	<div class="formLayer">
		<form id="searchFrm" class="search_form" method="POST" >
		<br/><br/> &nbsp;
			<table class="contable2">
				<tr>
					<td>
						<table class="contable">
							<tr>
								<td>
									<table>
										<colgroup>
											<col width="15%" align="left">
											<col width="35%" align="left">
											<col width="15%" align="left">
											<col width="35%" align="left">
										</colgroup>
										<tr height="29px">
											<td>
												
												업체명
											</td>
											
												<input class="w_95p" ype="text" size="20" maxlength="100">
											</td>
											<td>
												
												업체코드
											</td>
											
												<input class="w_95p" type="text" size="20" maxlength="100">
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
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</form>
           
		<!-- Data Total Count -->
		<br />
	    <div class="tableComment">
	        <p class="list_count">총 <span>5</span>건</p>
	    </div>
	       
	    <!-- Data List -->
		<div class="list">
			<table>
	            <caption>품목 목록</caption>
	            <colgroup>
	                <col width="*">
	                <col width="*">
	                <col width="*">
	                <col width="*">
	                <col width="*">
	            </colgroup>			
				<thead>
	                <tr>
	                    <th>No</th>
	                    <th>업체명</th>
	                    <th>업체코드</th>
	                    <th>재고물량</th>
	                    <th>단가(개별)</th>
	                </tr>
	            </thead>
				<tbody>
					<tr class="select_tr2" style="cursor:pointer;">
						<td class="txtc">1</td>
						<td class="txtc">(주)삼영소프트</td>
						<td class="txtc">12355</td>
						<td class="txtc">200</td>						
						<td class="txtc">1,300,000원</td>						
					</tr>					
					<tr class="select_tr2" style="cursor:pointer;">
						<td class=" txtc">2</td>
						<td class=" txtc">(주)한국패키지</td>
						<td class=" txtc">47898</td>
						<td class=" txtc">100</td>						
						<td class=" txtc">1,280,000원</td>						
					</tr>					
					<tr class="select_tr2" style="cursor:pointer;">
						<td class="txtc">3</td>
						<td class="txtc">(주)효진소프트코리아</td>
						<td class="txtc">25775</td>
						<td class="txtc">500</td>						
						<td class="txtc">1,320,000</td>						
					</tr>					
					<tr class="select_tr2" style="cursor:pointer;">
						<td class=" txtc">4</td>
						<td class=" txtc">티노스컴퓨터</td>
						<td class=" txtc">80876</td>
						<td class=" txtc">20</td>						
						<td class=" txtc">1,250,000</td>						
					</tr>					
					<tr class="select_tr2" style="cursor:pointer;">
						<td class="txtc">5</td>
						<td class="txtc">남영물산</td>
						<td class="txtc">21314</td>
						<td class="txtc">1000</td>						
						<td class="txtc">1,330,000</td>						
					</tr>										
				</tbody> 
			</table>
			
			<!-- 페이징 -->
			<div class="paging_place">
				<div class="paging_wrap"><a href="#" class="pprev" title="맨앞으로" style="display: none;"><span style="display: none;">맨앞으로</span></a><a href="#" class="prev" title="앞으로"><span style="display: none;">앞으로</span></a>
					<span><a href="#" class="active" title="1">1</a></span>
					<a href="#" class="next" title="뒤로"><span style="display: none;">뒤로</span></a><a href="#" class="nnext" title="맨뒤로" style="display: none;"><span style="display: none;">맨뒤로</span></a>
				</div>
			</div>
			<!-- 페이징 끝 -->
		</div> 
		<br>
	    <div class="T_btnLayer fr">
			<button type="button" class="blueBtn L" id="closeBtn" onclick="window.close();">닫기</button>
	    </div>
	</div> 
</div>
