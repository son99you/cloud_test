<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">--%>
<%--
 * 공통 > 사전규격진행사항 조회(팝업)
 *
 * <pre>
 * comm 
 *  |_popup
 *   |bfanProgPopup.jsp
 * 
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %>
<%@ taglib prefix="fc" uri="/WEB-INF/tlds/fwkComboBoxTag.tld" %>

<script type="text/javascript" src="${jsPath}/ipro/views/comm/popup/bfanProgPopup.js"></script>
<div class="pop_sp_sec"> 
	<h3 class="sp_tit">사전규격 진행이력</h3>
	<div class="sp_cont">
		<form id="searchFrm" class="search_form" method="POST" >
			<input type="hidden" id="resourceName" name="resourceName" value="${comFn:nvl(param.resourceName, resourceName)}">
			<input type="hidden" id="P_PAGE_NO" name="P_PAGE_NO" value="${comFn:nvl(param.P_PAGE_NO, 1)}">
			<input type="hidden" id="P_PAGE_SIZE" name="P_PAGE_SIZE" value="${comFn:nvl(param.P_PAGE_SIZE, 10)}">
			<input type="hidden" id="P_BFAN_NO" name="P_BFAN_NO" value="${P_BFAN_NO }">
				
			<!-- Data Total Count -->
			<div class="list_top">
				<p class="total">총 <span>${comFn:nvl(bfanProgListTotcnt, 0)}</span>건</p>
				<p class="total" style="float: right;"> 
				</p>
			</div><!--// list_top -->
			    <!-- Data List -->
			<table class="list_tb">
		            <caption>진행이력</caption>
		            <colgroup>
		                <col width="8%">
		                <col width="20%">
		                <col width="*">
		                <col width="15%">
		                <col width="20%">
		            </colgroup>			
					<thead>
		                <tr class="">
		                    <th>번호</th>
		                    <th>진행상태</th>
	                      	<th>상세내용</th>
		                    <th>작성자</th>
		                    <th>처리일시</th>
		                </tr>
		            </thead>
					<tbody>
						<c:if test="${comFn:nvl(bfanProgListTotcnt, 0) == 0}">
							<tr>
								<td colspan="5" class="txtc">검색 조건에 해당하는 데이터가 존재하지 않습니다.</td>
							</tr>
						</c:if>
						<c:if test="${bfanProgListTotcnt > 0}">
							<c:forEach var="data" items="${bfanProgList}" varStatus="status" >
								<tr >
									<td class="txtc">${data.HSTY_SN}</td>
									<td class="txtc">${data.BFAN_PSCD_NM}</td>
									<td class="txtl pl5">${data.RMK}</td>
									<td class="txtc">${data.REGR_NM}</td>
									<td class="txtc">${comFn:formatDate(data.PROC_DT,'yyyyMMddHHmmss','yyyy-MM-dd HH:mm:ss')}</td>
									<%-- <td class="txtc">${comFn:formatDate(data.PROC_DT,'yyyyMMdd','yyyy-MM-dd')}</td> --%>
								</tr>
							</c:forEach>
						</c:if>
					
					</tbody> 
				</table>
				<!-- Data Paging -->
				<!-- Data Paging -->
					<div class="paging">
					<comTag:pagingIpro totalCount="${comFn:nvl(bfanProgListTotcnt,0)}" pageNo="${comFn:nvl(param.P_PAGE_NO, 1)}" pageSize="${comFn:nvl(param.P_PAGE_SIZE, 10)}" clickPage="pageObj.clickPage"/>
				</div>
				<div class="btm_btns">
	        		<button type="button" class="btn ty04" id="closeBtn" onclick="window.close();">닫기</button>
				</div>
		</form>	
	</div> <!--// pop_container E -->
</div>
<%--page move form --%>
	<form id="detailFrm" method="POST" >
		<input type="hidden" id="resourceName" name="resourceName" value="${comFn:nvl(param.resourceName, resourceName)}">
		<input type="hidden" id="resourceDesc" name="resourceDesc" value="${param.resourceDesc}">
	</form>         
</div> <!--// pop_wrap E -->

