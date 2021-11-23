<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 협력사별품목관리 리스트
 *
 * <pre>
 * vend
 *    |_ vendItemMngeList.jsp
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

<script type="text/javascript" src="${jsPath}/ipro/views/vend/vendItemMngeList.js"></script>

<div class="conts_wrap">
	<div class="inner">
		<div class="tit_wrap">
			<h3 class="tit">협력사별품목관리</h3>
			<ul class="step_wrap">
				<li><a href="#">${bigMenuNm}</a></li>
				<li><a href="#">${smallMenuNm}</a></li>
			</ul>			
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
								<col width="15%" >
								<col width="35%">
							</colgroup>
							<tr height="24">
								<th>품목번호</th>
								<td>
									<input type="text" id="P_ITEM_NO_S" name="P_ITEM_NO_S" value="${param.P_ITEM_NO_S}">
								</td>
								<th>품목명</th>
								<td>
									<input type="text" id="P_ITEM_NM_S" name="P_ITEM_NM_S" value="${param.P_ITEM_NM_S}">
								</td>
							</tr>
							<tr height="24">
								<th>대분류</th>
								<td>
									<input type="text" id="P_LLF_NM_S" name="P_LLF_NM_S" value="${param.P_LLF_NM_S}">
								</td>
								<th>중분류</th>
								<td>
									<input type="text" id="P_MLF_NM_S" name="P_MLF_NM_S" value="${param.P_MLF_NM_S}">
								</td>
							</tr>
							
							<tr height="24">
								<th>소분류</th>
								<td>
									<input type="text" id="P_SLF_NM_S" name="P_SLF_NM_S" value="${param.P_SLF_NM_S}">
								</td>
								<th>협력사명</th>
								<td>
									<input type="text" id="P_VEND_NM_S" name="P_VEND_NM_S" value="${param.P_VEND_NM_S}">
								</td>
							</tr>
						</table>				
					</div>
				</div>
				
				<div class="btn_wrap mt10">
					<button type="button" class="btn btn_03 btn_inquire" id="searchBtn">조회</button>
				</div> <!--// btn_wrap E -->
				
				<div class="list_wrap mt30 contentWrap">
					<div class="list_top">
						<p class="total">총 <span>${comFn:nvl(vendItemMngeListTotcnt, 0)}</span>건</p>
						<!--  <div class="btn_right"> -->
							<!-- <button type="button" class="btn btn_02 btn_down">엑셀 다운로드</button> -->
						<!-- </div> -->
					</div> <!--// list_top E -->
					<div class="list_conts">			
						<table>
			           		<colgroup>
								<col width="*">
								<col width="7%">
								<col width="15%">
								<col width="18%">
								<col width="18%">
								<col width="18%">
								<col width="12%">
							</colgroup>
							<thead>
				                <tr>
									<th scope="col">협력사명</th>
			                    	<th scope="col">품목번호</th>
									<th scope="col">대분류</th>
									<th scope="col">중분류</th>
									<th scope="col">소분류</th>
									<th scope="col">품목명</th>
									<th scope="col">단가</th>
				                </tr>
				            </thead>
							<tbody>
								<c:if test="${comFn:nvl(vendItemMngeListTotcnt, 0) == 0}">
									<tr>
										<td colspan="7" class="txt-center">검색 조건에 해당하는 데이터가 존재하지 않습니다.</td>
									</tr>
								</c:if>
								<c:if test="${vendItemMngeListTotcnt > 0}">
									
									<c:set var="tdStyle" value="alterstyle" scope="request"></c:set>
									<c:set var="totCnt" value="0" scope="request"></c:set>
									
									<c:forEach var="data" items="${vendItemMngeList}" varStatus="status" >
										<c:if test="${ status.count == 1 }">
											<c:set var="totCnt" value="${ vendItemMngeListTotcnt }" scope="request"></c:set>
										</c:if>
										<c:if test="${ status.index % 2 == 0 }">
											<c:set var="tdStyle" value="td01" scope="request"/>
										</c:if>
										<c:if test="${ status.index % 2 != 0 }">
											<c:set var="tdStyle" value="" scope="request"/>
										</c:if>
										
										<tr>
											<td class="pl5 list_tit">${data.VEND_NM}</td>
											<td>${data.ITEM_NO}</td>
											<td class="pl5 txtl">${data.LLF_NM}</td>
											<td class="pl5 txtl">${data.MLF_NM}</td>
											<td class="pl5 txtl">${data.SLF_NM}</td>
											<td class="pl5 txtl">${data.ITEM_NM}</td>
											<td class="pr5 txtr">
												<c:if test="${empty data.PRICE }">0</c:if>
												<c:if test="${not empty data.PRICE }">${comFn:formatMoney(data.ITEM_UPRC)}</c:if>
											</td>
										</tr>
									</c:forEach>
								</c:if>
							</tbody>
						</table>
					</div>
					<div class="list_bottom">
						<comTag:pagingIpro totalCount="${vendItemMngeListTotcnt}" pageNo="${comFn:nvl(param.P_PAGE_NO, 1)}" pageSize="${comFn:nvl(param.P_PAGE_SIZE, 10)}" clickPage="pageObj.clickPage"/>
						<div class="list_btn">
<!-- 							<button type="button" class="btn btn_02 btn_blue" id="memorRegistBtn">수기등록</button> -->
						</div> <!--// btn_wrap E -->
					</div> <!--// list_bottom E -->				
				</div>				
				
			</fieldset>
		</form>
	</div>
</div> 