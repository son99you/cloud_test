<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 공통 > 품목등록 (팝업)
 *
 * <pre>
 * comm 
 *  |_popup
 *   |_itemList.jsp
 * 
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %>

<script type="text/javascript" src="/statics/fwk/CmmnUtil.js"></script>
<script type="text/javascript" src="${jsPath}/ipro/views/comm/popup/uniAnncItemList.js"></script>

<div class="pop_wrap">
	<div class="pop_header">
		<h1 class="tit">품목 목록</h1>
	</div> <!--// pop_header E -->
	<div class="pop_container">
		<div class="view_wrap typeC">
			<div class="view_area m0 typeB">
				<form id="searchFrm" name="searchFrm" class="search_form" method="POST" action="${contextPath}/comm/popup/itemList.do">
					<input type="hidden" id="P_PAGE_NO" name="P_PAGE_NO" value="${comFn:nvl(param.P_PAGE_NO, 1)}">
					<input type="hidden" id="P_PAGE_SIZE" name="P_PAGE_SIZE" value="${comFn:nvl(param.P_PAGE_SIZE, 10)}">
					
					<input type="hidden" id="P_BID_WAIT_NO" name="P_BID_WAIT_NO" value="${param.P_BID_WAIT_NO}">
					
					<input type="hidden" id="setMulti" name="setMulti" value="${param.setMulti}">
					<input type="hidden" id="gbn" name="gbn" value="${param.gbn}">
					<table>
						<colgroup>
							<col style="width: 15%;">
							<col style="width: 35%;">
							<col style="width: 15%;">
							<col style="width: 35%;">
						</colgroup>
						<tr>
							<th scope="row"><label for="P_PCAC_NO_S">구매접수번호</label></th>
							<td>
								<input type="text" id="P_PCAC_NO_S" name="P_PCAC_NO_S" value="${param.P_PCAC_NO_S}" maxlength="100">
							</td>
							<th scope="row"><label for="P_PCRQ_NM_S">구매요구명</label></th>
							<td colspan="3">
								<input type="text" id="P_PCRQ_NM_S" name="P_PCRQ_NM_S" value="${param.P_PCRQ_NM_S}" maxlength="100">
							</td>							
						</tr>
					</table>
				</form>
			</div> <!--// view_area E -->
		</div> <!--// view_wrap E -->
		<div class="btn_wrap mt10">
			<button type="button" class="btn btn_m btn_c2" id="searchBtn"><img src="${imagePath}/ipro/icon/btn_icon02.png" alt="">조회</button>
		</div> <!--// btn_wrap E -->
		<!-- Data Total Count -->
		<div class="pop_list_wrap">
			<div class="pop_list_top">
				<p class="pop_total">총 <span>${comFn:nvl(uniAnncPsblListTotcnt, 0)}</span>건</p>
			</div> <!--// pop list_top E -->
		    <!-- Data List -->
			<div class="pop_list_conts">
				<table>
		            <caption>업체 목록</caption>
		            <colgroup>
		                <col width="7%">
		                <col width="12%">
		                <col width="10%">
		                <col width="*">
		                <col width="12%">
		            </colgroup>			
					<thead>
		                <tr>
							<th class="noBg">선택</th>
		                    <th>구매접수번호</th>
		                    <th>계약구분</th>
		                    <th>구매요구명</th>
		                    <th>요구일자</th>
		                </tr>
		            </thead>
					<tbody>
						<c:if test="${comFn:nvl(uniAnncPsblListTotcnt, 0) == 0}">
							<tr>
								<td colspan="5" class="txtc">검색 조건에 해당하는 데이터가 존재하지 않습니다.</td>
							</tr>
						</c:if>
						<c:if test="${uniAnncPsblListTotcnt > 0}">
							<c:forEach var="data" items="${uniAnncPsblList}" varStatus="status" >
								<tr class="row"  style="cursor: pointer;">
									<td class="txtc">
										<input type="checkbox" id="anncChoiseCbx${status.count}" name="anncChoiseCbx" value="${data.BID_WAIT_NO }">
										<input type="hidden" name="P_BID_WAIT_NO" value="${data.BID_WAIT_NO }">
									</td>
									<td class="txtl pl5">${data.PCAC_NO}</td>
									<td>${data.CONT_SECD_NM}</td>
									<td class="txtl pl5">${data.PCRQ_NM}</td>
									<td>${comFn:formatDate(data.RQR_DE,'yyyyMMdd','yyyy-MM-dd')}</td>
								</tr>
							</c:forEach>
						</c:if>
					</tbody> 
				</table>
			</div>
			<div class="pop_list_bottom">
				<!-- Data Paging -->
				<div class="pop_list_pager">
					<comTag:pagingIpro totalCount="${uniAnncPsblListTotcnt}" pageNo="${comFn:nvl(param.P_PAGE_NO, 1)}" pageSize="${comFn:nvl(param.P_PAGE_SIZE, 10)}" clickPage="pageObj.clickPage"/>
				</div>
			    <div class="btn_wrap view_btn">
			    	<c:if test="${param.setMulti eq 'Y'}"> 
		        		<button type="button" class="btn btn_m btn_orange"   id="choiceBtn">선택</button>
		        	</c:if>
					<button type="button" class="btn btn_m btn_del" id="closeBtn" onclick="window.close();">닫기</button>
			    </div> <!--// btn_wrap E -->
			</div> 
		</div>
	</div> 
</div>