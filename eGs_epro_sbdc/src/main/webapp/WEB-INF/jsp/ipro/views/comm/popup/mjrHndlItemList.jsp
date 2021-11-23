<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">--%>
<%--
 * 공통 > 주요취급품목(팝업)
 *
 * <pre>
 * comm 
 *  |_ popup
 *    |_ mjrHndlItemList.jsp
 * 
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %>
<%@ taglib prefix="fc" uri="/WEB-INF/tlds/fwkComboBoxTag.tld" %>

<script type="text/javascript" src="${jsPath}/ipro/views/comm/popup/mjrHndlItemList.js"></script>

<div class="pop_wrap">
	<div class="pop_header">
		<h1 class="tit">주요취급품목 조회</h1>
	</div>
	
	<div class="pop_container">
		<form id="searchFrm" class="search_form" method="POST">
			<input type="hidden" id="P_PAGE_NO" name="P_PAGE_NO" value="${comFn:nvl(param.P_PAGE_NO, 1)}">
			<input type="hidden" id="P_PAGE_SIZE" name="P_PAGE_SIZE" value="${comFn:nvl(param.P_PAGE_SIZE, 10)}">
			
			<input type="hidden" id="btnId" name="searchGbnId" value="${param.searchGbnId}">
			<input type="hidden" id="setMulti" name="setMulti" value="${param.setMulti}">
			
			<div class="view_wrap typeC">
				<div class="view_area m0 typeB">
					<table>
						<colgroup>
							<col width="15%" align="left">
							<col width="35%" align="left">
							<col width="15%" align="left">
							<col width="35%" align="left">
						</colgroup>
						<tr height="29px">
							<td>품목명</td>
							<td colspan="3">
								<input type="text" id="P_ITEM_NM_S" name="P_ITEM_NM_S" value="${param.P_ITEM_NM_S}" class="lineTxt">
							</td>
						</tr>											
					</table>
				</div> <!--// view_area E -->
			</div> <!--// view_wrap E -->
        </form>
        
		<div class="btn_wrap mt10">
			<button type="button" class="btn btn_m btn_c2" id="searchBtn">조회</button>
		</div> <!--// btn_wrap E -->
		
		<!-- Data Total Count -->
    	<div class="pop_list_wrap">
			<div class="pop_list_top">
	        	<p class="list_count">총 <span>${comFn:nvl(mjrHndlItemListTotcnt, 0)}</span>건</p>
	    	</div> <!--// pop list_top E -->
	    	
		    <!-- Data List -->
			<div class="pop_list_conts">
				<table class="tableList" summary="주요취급품목 목록 입니다.">
		            <caption>주요취급품목 목록</caption>
		            <colgroup>
		                <col width="40px">
		                <col width="100px">
		                <col width="150px">
		            </colgroup>			
					<thead>
		                <tr>
		                	<c:choose>
								<c:when test="${param.setMulti eq 'Y'}">
									 <th class="noBg">
									 	번호
									 	<!-- <label for="itemAllCbx" class="blind">체크박스</label>
				                    	<input type="checkbox" id="itemAllCbx" name="itemCbx" onclick="FwkCmmnUtil.setAllCheck('itemAllCbx','itemCbx');"> -->
				                    </th>
								</c:when>
								<c:otherwise>
									 <th class="noBg">번호</th>
								</c:otherwise>
							</c:choose>
		                    <th>품목번호</th>
		                    <th>품목명</th>
		                </tr>
		            </thead>
					<tbody>
						<c:if test="${comFn:nvl(mjrHndlItemListTotcnt, 0) == 0}">
							<tr>
								<td colspan="3" class="txtc">검색 조건에 해당하는 데이터가 존재하지 않습니다.</td>
							</tr>
						</c:if>
						<c:if test="${mjrHndlItemListTotcnt > 0}">
							<c:forEach var="data" items="${mjrHndlItemList}" varStatus="status" >
								<tr class="row" <c:if test="${param.setMulti ne 'Y'}">onclick="setInfo('${data.ITEM_NO}', '${data.ITEM_NM}', '${data.LLF_CD}', '${data.MLF_CD}', '${data.SLF_CD}', '${data.DLF_CD}');"</c:if> style="cursor: pointer;">
									<c:if test="${param.setMulti eq 'Y'}">
										<td class="txtc">
											<label for="itemCbx${status.count }" class="blind">체크박스</label>
											<input type="checkbox" id="itemCbx${status.count }" name="itemCbx">
										</td>
									</c:if>
									<c:if test="${param.setMulti ne 'Y'}">
										<td class="txtc">${data.RNUM}&nbsp;</td>
									</c:if>
									<td>${data.ITEM_NO}&nbsp;</td>
									<td class="pl5" align="left">${data.ITEM_NM}&nbsp;
										<input type="hidden" name="P_ITEM_NO" value="${data.ITEM_NO}">
										<input type="hidden" name="P_ITEM_NM" value="${data.ITEM_NM}">
										<input type="hidden" name="P_LLF_CD" value="${data.LLF_CD}">
										<input type="hidden" name="P_MLF_CD" value="${data.MLF_CD}">
										<input type="hidden" name="P_SLF_CD" value="${data.SLF_CD}">
										<input type="hidden" name="P_DLF_CD" value="${data.DLF_CD}">
									</td>
								</tr>
							</c:forEach>
						</c:if>
					</tbody> 
				</table>
			</div> 
		
			<!-- Data Paging -->
			<div class="pop_list_bottom">
				<div class="pop_pager">
					<comTag:pagingIpro totalCount="${mjrHndlItemListTotcnt}" pageNo="${comFn:nvl(param.P_PAGE_NO, 1)}" pageSize="${comFn:nvl(param.P_PAGE_SIZE, 10)}" clickPage="pageObj.clickPage"/>
				</div>
			</div> <!--// list_pager E -->
			
			<div class="btn_wrap view_btn">
				<c:if test="${param.setMulti eq 'Y'}"> 
        			<button type="button" class="btn btn_s btn_orange" id="choiceBtn">선택</button>
        		</c:if>
				<button type="button" class="btn btn_s btn_del" id="closeBtn">닫기</button>
			</div>
		</div> <!--// list_wrap E -->
	</div> <!--// pop_container E -->
</div>