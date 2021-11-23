<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">--%>
<%--
 * 공통 > 입찰 조회 (팝업)
 *
 * <pre>
 * comm 
 *   |_ popup
 *     |_ bidVendItemRefList.jsp
 * 
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %>
<%@ taglib prefix="fc" uri="/WEB-INF/tlds/fwkComboBoxTag.tld" %>

<script type="text/javascript" src="${jsPath}/ipro/views/comm/popup/bidVendItemRefList.js"></script>

<div class="pop_wrap">
	<div class="pop_header">
		<h1 class="tit">입찰 조회</h1>
	</div> <!--// pop_header E -->
	
	<div class="pop_container">
		
		<!-- Data Total Count -->
    	<div class="pop_list_wrap">
			<div class="pop_list_top">
	        	<p class="list_count">총 <span>${comFn:nvl(vendItemAnncListTotcnt, 0)}</span>건</p>
	    	</div> <!--// pop list_top E -->
		    <!-- Data List -->
			<div class="pop_list_conts">
				<table class="tableList" summary="입찰 목록 입니다.">
		            <caption>입찰 목록</caption>
		            <colgroup>
		                <col width="30%">
		                <col width="*">
		            </colgroup>			
					<thead>
		                <tr>
		                    <th>공고번호</th>
		                    <th>공고명</th>
		                </tr>
		            </thead>
					<tbody>
						<c:if test="${comFn:nvl(vendItemAnncListTotcnt, 0) == 0}">
							<tr>
								<td class="txtc" colspan="2">검색 조건에 해당하는 데이터가 존재하지 않습니다.</td>
							</tr>
						</c:if>
						<c:if test="${vendItemAnncListTotcnt > 0}">
							<c:forEach var="data" items="${vendItemAnncList}" varStatus="status" >
								<tr class="row" title="${data.BID_NM}" onclick="inputData('${data.ANNC_NO}', '${data.BID_NM}');" style="cursor: pointer;">
									<td>${data.ANNC_NO}</td>
									<td class="txtl pl5">${data.BID_NM}</td>
								</tr>
							</c:forEach>
						</c:if>
					</tbody> 
				</table>
			</div> 
			<div class="pop_list_bottom">
				<div class="pop_pager">
					<comTag:pagingIpro totalCount="${vendItemAnncListTotcnt}" pageNo="${comFn:nvl(param.P_PAGE_NO, 1)}" pageSize="${comFn:nvl(param.P_PAGE_SIZE, 10)}" clickPage="pageObj.clickPage"/>
				</div>
			</div> <!--// list_pager E -->
			
			<form id="sendFrm" method="POST">
				<input type="hidden" name="resourceName" value="${param.resourceName}">
				<input type="hidden" id="choiceYn" name="P_ANNC_NO" readonly="readonly">
				<input type="hidden" name="P_ITEM_NO_LIST" value="${vendItemNoList}" readonly="readonly">
				<input type="hidden" id="P_VEND_REG_NO_LIST" name="P_VEND_REG_NO_LIST" value="${P_VEND_REG_NO_LIST}" readonly="readonly">
				
				<div class="view_wrap typeC" id="sendDiv" style="display: none;">
					<div class="view_area m0 typeB">
						<table>
							<colgroup>
								<col width="15%">
								<col width="%">
							</colgroup>
							<tr>
								<th>제목</th>
								<td><input type="text" id="P_TTL" name="P_TTL"></td>
							</tr>
							<tr>
								<th>내용</th>
								<td>
									<textarea rows="" cols="" id="P_CNTN" name="P_CNTN"></textarea> 
								</td>
							</tr>
							<tbody id="sendVendRegNo" style="display: none;">
							</tbody>
						</table>
					</div>	
				</div>
				
			</form>
			
			
			<div class="btn_wrap view_btn">
       			<button type="button" class="btn btn_s btn_orange" id="sendBtn">발송</button>
				<button type="button" class="btn btn_s btn_del" id="closeBtn" onclick="window.close();">닫기</button>
			</div>
		</div> <!--// list_wrap E -->
	</div> <!--// pop_container E -->
</div>
