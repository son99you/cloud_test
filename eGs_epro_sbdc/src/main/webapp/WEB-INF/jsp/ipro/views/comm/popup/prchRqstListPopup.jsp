<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">--%>
<%--
 * 공통 > 구매의뢰 조회(팝업)
 *
 * <pre>
 * comm 
 *  |_popup
 *   |_prchRqstListPopup.jsp
 * 
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %>

<script type="text/javascript" src="${jsPath}/ipro/views/comm/popup/prchRqstListPopup.js"></script>
<div class="pop_wrap">
	<div class="pop_header">
		<h1 class="tit">계약의뢰조회</h1>
	</div> <!--// pop_header E -->
	<div class="pop_container">
		<form id="searchFrm" class="search_form" method="POST" >
		<br/><br/>
			<input type="hidden" id="P_PAGE_NO" name="P_PAGE_NO" value="${comFn:nvl(param.P_PAGE_NO, 1)}">
			<input type="hidden" id="P_PAGE_SIZE" name="P_PAGE_SIZE" value="${comFn:nvl(param.P_PAGE_SIZE, 10)}">
			<input type="hidden" name="P_BFAN_REG_YN" value="${param.P_BFAN_REG_YN}">
			<input type="hidden" name="P_BID_REG_YN" value="${param.P_BID_REG_YN}">
			<input type="hidden" name="P_CONT_REG_YN" value="${param.P_CONT_REG_YN}">
			<input type="hidden" name="P_ESTMT_REG_YN" value="${param.P_ESTMT_REG_YN}">
			<input type="hidden" name="P_CONT_FLAG" value="${param.P_CONT_FLAG}">
			<input type="hidden" name="P_CONT_SECD" value="${param.P_CONT_SECD}">
			<div class="view_wrap typeC">
				<div class="view_area m0 typeB">
					<table>
						<colgroup>
							<col width="15%" align="left">
							<col width="35%" align="left">
							<col width="15%" align="left">
							<col width="35%" align="left">
						</colgroup>
						<tr>
							<td>의뢰자</td>
							<td>
								<input type="text" id="P_RQSTR_NM_S" name="P_RQST_EMPL_NO" value="${param.P_RQST_EMPL_NO}">
							</td>
							<td>의뢰부서</td>
							<td>
								<input type="text" id="P_RQST_DEPT_NM_S" name="P_CONT_RQST_DEPT" value="${param.P_CONT_RQST_DEPT}">
							</td>
						</tr>
						<tr>
							<td>계약의뢰번호</td>
							<td>
								<input type="text" id="P_CONT_RQST_NO_S" name="P_CONT_RQST_NO" value="${param.P_CONT_RQST_NO}">
							</td>
							<td>계약의뢰명</td>
							<td>
								<input type="text" id="P_CONT_RQST_NM_S" name="P_TTL_TXT" value="${param.P_TTL_TXT}">
							</td>
						</tr>
					</table>
				</div> <!--// view_area E -->
			</div> <!--// view_wrap E -->
		</form>
		<div class="btn_wrap mt10">
			<button type="button" class="btn_p btn_p1 btn_lookup" id="searchBtn">
				<img src="${imagePath}/ipro/icon/btn_icon02.png" alt="">조회
			</button>
		</div> <!--// btn_wrap E -->
		<!-- Data Total Count -->
	    <div class="pop_list_wrap">
			<div class="pop_list_top">
	        	<p class="list_count">총 <span>${comFn:nvl(prchRqstListTotcnt, 0)}</span>건</p>
	    	</div> <!--// pop list_top E -->
		    <!-- Data List -->
			<div class="pop_list_conts">
				<table>
		            <caption>업체 목록</caption>
		            <colgroup>
		                <col width="15%">
		                <col width="*">
		                <col width="10%">
		                <col width="15%">
		                <col width="15%">
		            </colgroup>			
					<thead>
		                <tr>
		                    <th>계약의뢰번호</th>
		                    <th>계약의뢰명</th>
		                    <th>의뢰자</th>
							<th>의뢰부서</th>
							<th>의뢰일자</th>
		                </tr>
		            </thead>
					<tbody>
						<c:if test="${comFn:nvl(prchRqstListTotcnt, 0) == 0}">
							<tr>
								<td colspan="5" style="text-align: center;">검색 조건에 해당하는 데이터가 존재하지 않습니다.</td>
							</tr>
						</c:if>
						<c:if test="${prchRqstListTotcnt > 0}">
							<c:forEach var="data" items="${prchRqstList}" varStatus="status" >
								<tr class="row" onclick="setPrchRqstInfo('${data.IFC_ID}', '${data.CONT_RQST_NO}');" style="cursor: pointer;">
									<td>${data.CONT_RQST_NO }</td>
									<td class="txtl pl5">${data.TTL_TXT}</td>
									<td align="center">${data.USERNAME}</td>
									<td>${data.DEPTNAME}</td>
									<td>${comFn:formatDate(data.RQST_DE,'yyyyMMdd','yyyy-MM-dd') }</td>
								</tr>
							</c:forEach>
						</c:if>
					</tbody> 
				</table>
			</div> 
			
			<!-- Data Paging -->
			<div class="pop_list_bottom">
				<div class="pop_pager">
					<comTag:pagingIpro totalCount="${prchRqstListTotcnt}" pageNo="${comFn:nvl(param.P_PAGE_NO, 1)}" pageSize="${comFn:nvl(param.P_PAGE_SIZE, 10)}" clickPage="pageObj.clickPage"/>
				</div>
			</div> <!--// list_pager E -->
			<div class="btn_wrap view_btn">
				<button type="button" class="btn btn_02 btn_close" id="closeBtn" onclick="window.close();">닫기</button>
			</div>
		</div> <!--// list_wrap E -->
	</div> <!--// pop_container E -->

	
<%--page move form --%>
<form id="detailFrm" method="POST" >
	<input type="hidden" name="P_IFC_ID">
	<input type="hidden" name="P_CONT_RQST_NO">
</form>         
</div> <!--// pop_wrap E -->