<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 본사정보관리
 *
 * <pre>
 * sytm 
 *    |_ compInfoMngeList.jsp
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

<script type="text/javascript" src="${jsPath}/ipro/views/sytm/compInfoMngeList.js"></script> 

<div class="contents_wrap">
	<ul class="step_wrap">
		<li><a href="#">${myMenuList.bigMenuNm}</a></li>
		<li><a href="#">${myMenuSubList.smallMenuNm}</a></li>
	</ul>
	<div class="tit_wrap">
		<h3 class="tit">본사정보관리</h3>
	</div>
	
	<form id="searchFrm" class="search_form" method="POST" action="${contextPath}/buyer/prpo/prpoPurcntrCurstatList.do">
		<input type="hidden" id="resourceName" name="resourceName" value="${param.resourceName}">
		<input type="hidden" id="resourceDesc" name="resourceDesc" value="${param.resourceDesc}">
		<input type="hidden" id="P_PAGE_NO" name="P_PAGE_NO" value="${comFn:nvl(param.P_PAGE_NO, 1)}">
		<input type="hidden" id="P_PAGE_SIZE" name="P_PAGE_SIZE" value="${comFn:nvl(param.P_PAGE_SIZE, 10)}">
		
		<fieldset>
			<div class="view_wrap typeA">
				<div class="view_area">
					<table>
						<colgroup>
							<col width="130px" align="left">
							<col width="280px" align="left">
							<col width="130px" align="left">
							<col width="280px" align="left">
						</colgroup>
						<tr height="24px">
							<th>
								계약명
							</th>
							<td>
								<input type="text" >
							</td>
							<th>업체명</th>
							<td>
								<input type="text">
							</td>
						</tr>
					</table>
				</div>
			
			</div>	

			<div class="btn_wrap mt10">
				<button type="button" class="btn btn_m btn_c2" id="searchBtn">조회</button>
			</div> <!--// btn_wrap E -->				
 
			<div class="list_wrap mt30" id="contentWrap">
				<div class="list_top">
					<p class="total">총 <span></span>건</p>		
					<!--  <div class="btn_right"> -->
						<!-- <button type="button" class="btn btn_02 btn_down">엑셀 다운로드</button> -->
					<!-- </div> -->
				</div> <!--// list_top E -->
				<div class="list_conts">
					<table>
		           		<colgroup>
							<col width="15%" >
							<col width="*">  
							<col width="15%">
						</colgroup>
						<thead>
			                <tr>
		                    	<th>사업자번호</th>
								<th>지사명</th>
		                    	<th>대표자명</th>
			                </tr>
			            </thead>
						<tbody>
							 <tr class="pointer" onclick="movePage('/sytm/compInfoMngeDetail.do', '');">
								<td class="txtc">410-04-33365</td>
								<td class="txtl">동부지사</td>
								<td class="txtc">홍찬일</td>
							</tr>
							<tr class="pointer" onclick="movePage('/sytm/compInfoMngeDetail.do', '');">
								<td class=" txtc">135-24-23737</td>
								<td class=" txtl">서부지사</td>
								<td class=" txtc">이주연</td>
							</tr>
							<tr class="pointer" onclick="movePage('/sytm/compInfoMngeDetail.do', '');">
								<td class="txtc">211-85-36065</td>
								<td class="txtl">남부지사</td>
								<td class="txtc">전상훈</td>
							</tr>
							<tr class="pointer" onclick="movePage('/sytm/compInfoMngeDetail.do', '');">
								<td class=" txtc">142-50-68465</td>
								<td class=" txtl">북부지사</td>
								<td class=" txtc">은잔디</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</fieldset>
	</form>
</div>

<%-- DETAIL FORM --%>
<form id="detailFrm" method="POST" > 
	<input type="hidden" name="resourceName" value="${ param.resourceName }" >
</form>
<form id="popupFrm" method="POST" > 
	<input type="hidden" name="resourceName" value="${ param.resourceName }" >
</form>