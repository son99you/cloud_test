<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * Admin > 시스템현황 > 인터페이스 목록
 *
 * <pre>
 * sytm
 *    |_ intfMnge.jsp
 * 
 * </pre>
 * @date : 2016. 09. 28
 * @version : 1.0
 * @author :  yhs
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %>

<script type="text/javascript" src="${jsPath}/ipro/views/sytm/intfMnge.js"></script>

<div class="contents_wrap">
	<ul class="step_wrap">
		<li><a href="#">${myMenuList.bigMenuNm}</a></li>
		<li><a href="#">${myMenuSubList.smallMenuNm}</a></li>
	</ul>
	<div class="tit_wrap">
		<h3 class="tit">인터페이스</h3>
	</div>

	<form id="listFrm" name="listFrm" class="search_form" method="POST">
		<input type="hidden" id="resourceName" name="resourceName" value="${param.resourceName}">
		<input type="hidden" id="resourceDesc" name="resourceDesc" value="${param.resourceDesc}">
		
		<input type="hidden" id="P_PAGE_NO" name="P_PAGE_NO" value="${comFn:nvl(param.P_PAGE_NO, 1)}">
		<input type="hidden" id="P_PAGE_SIZE" name="P_PAGE_SIZE" value="${comFn:nvl(param.P_PAGE_SIZE, 10)}">
		<fieldset>
			<div class="view_wrap typeA">
				<div class="view_area">
					<table>
						<colgroup>												
							<col width="15%">
							<col width="35%">
							<col width="15%">
							<col width="35%">
						</colgroup>
						<tr>
							<th>인터페이스명</th>
							<td colspan="3"><input type="text" id="P_IF_NM_S" name="P_IF_NM_S" value="${param.P_IF_NM_S }"></td>
						</tr>
					</table>
				</div>
			</div>
			
			<div class="btn_wrap mt10">
				<button type="button" class="btn btn_m btn_c2" id="searchBtn">조회</button>
			</div> <!--// btn_wrap E -->
			
			<div class="list_wrap mt30 contentWrap">
				<div class="list_top">
					<p class="total">총 <span>${comFn:nvl(intfMngeListTotCnt, 0)}</span>건</p>
				</div> <!--// list_top E -->
				<div class="list_conts">			
					<table>
		           		<colgroup>
							<col width="8%">
							<col width="15%">  
							<col width="*">
							<col width="10%">
							<col width="10%">
							<col width="10%">
							<col width="20%">
						</colgroup>
						<thead>
			                <tr>
								<th scope="col">인터페이스ID</th>
								<th scope="col">인터페이스명</th>
								<th scope="col">인터페이스내용</th>
								<th scope="col">송신처</th>
								<th scope="col">수신처</th>
								<th scope="col">통신방법</th>
								<th scope="col">비고</th>
			                </tr>
			            </thead>
						<tbody>
							<c:if test="${comFn:nvl(intfMngeListTotCnt, 0) == 0}">
								<tr>
									<td colspan="7" class="txt-center">검색 조건에 해당하는 데이터가 존재하지 않습니다.</td>
								</tr>
							</c:if>
							<c:if test="${intfMngeListTotCnt > 0}">
								<c:forEach var="data" items="${intfMngeList}" varStatus="status" >
									<tr>
										<td>${data.IF_NO}</td>
										<td class="pl5 txtl">${data.IF_NM}</td> 
										<td class="pl5 txtl">${data.IF_CNTN}</td> 
										<td class="pl5 txtl">${data.SNDR}</td> 
										<td class="pl5 txtl">${data.DSTN}</td> 
										<td>${data.CMCT_MTHD}</td> 
										<td class="pl5 txtl">${data.RMK}</td> 
									</tr>
								</c:forEach>
							</c:if>
						</tbody>
					</table>
				</div>
				<div class="list_bottom">
					<comTag:pagingIpro totalCount="${intfMngeListTotCnt}" pageNo="${comFn:nvl(param.P_PAGE_NO, 1)}" pageSize="${comFn:nvl(param.P_PAGE_SIZE, 10)}" clickPage="pageObj.clickPage"/>
				</div> <!--// list_bottom E -->				
			</div>				
		</fieldset>
	</form>
</div>