<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">--%>
<%--
 * 공통 > 요구부서 조회(팝업)
 *
 * <pre>
 * comm 
 *  |_popup
 *   |_bsnsList.jsp
 * 
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %>
<%@ taglib prefix="fc" uri="/WEB-INF/tlds/fwkComboBoxTag.tld" %>

<script type="text/javascript" src="${jsPath}/ipro/views/comm/popup/apprLineList.js"></script>

<div id="" class="pop_wrap">
	<div class="pop_header">
		<h1 class="tit">결재선목록</h1>
	</div> <!--// pop_header E -->
	<!-- <h3 class="windowTitle">결재선 조회</h3> -->
	<div class="pop_container">
		<form id="searchFrm" class="search_form" method="POST" action="">
			<input type="hidden" id="P_PAGE_NO" name="P_PAGE_NO" value="${comFn:nvl(param.P_PAGE_NO, 1)}">
			<input type="hidden" id="P_PAGE_SIZE" name="P_PAGE_SIZE" value="${comFn:nvl(param.P_PAGE_SIZE, 10)}">
			<input type="hidden" id="P_BSNS_CNT" name="P_BSNS_CNT" value="${param.P_BSNS_CNT}">
			<input type="hidden" id="P_CTRTC" name="P_CTRTC" value="${param.P_CTRTC }">
			<input type="hidden" id="btnId" name="searchGbnId" value="${param.searchGbnId}">
			<input type="hidden" id="gbnDept" name="searchGbnDept" value="${param.searchGbnDept }">
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
							<td>
								결재선명
							</td>
							<td colspan="3">
								<input type="text" size="20" maxlength="600" id="P_APPL_NM" name="P_APPL_NM" value="${param.P_APPL_NM}">
							</td>
							<%-- <td>
								사업자번호
							</td>
							<td>
								<input type="text" size="20" maxlength="10" id="P_BIZRNO_S" name="P_BIZRNO_S" value="${param.P_BIZRNO_S}">
							</td> --%>
						</tr>
					</table>
				</div> <!--// view_area E -->
			</div> <!--// view_wrap E -->
			
			<%-- <table class="contable2">
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
												
												결재선명
											</td>
											<td  colspan="3">
												<input type="text" class="lineTxt" id="P_APPL_NM" name="P_APPL_NM" style="width: 160px;" value="${param.P_APPL_NM}" >
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
			</table> --%>			
        </form>
	
	<div class="btn_wrap mt10">
		<button type="button" class="btn_p btn_p1 btn_lookup" id="searchBtn">
			<img src="${imagePath}/ipro/icon/btn_icon02.png" alt="">조회
		</button>
	</div> <!--// btn_wrap E -->            
	<%-- <!-- Data Total Count -->
    <div class="tableComment">
        <p class="list_count">총 <span>${comFn:nvl(apprLineListTotcnt, 0)}</span>건</p>
    </div> --%>
    <div class="pop_list_wrap">
    	<div class="pop_list_top">
        	<p class="list_count">총 <span>${comFn:nvl(apprLineListTotcnt, 0)}</span>건</p>
    	</div> <!--// pop list_top E -->
    <!-- Data List -->
	<div class="pop_list_conts">
		<table class="" summary="내부 담당자 목록 입니다.">
            <caption>업체 목록</caption>
            <colgroup>
                <col width="40px">
                <col width="*">
            </colgroup>			
			<thead>
                <tr>
					<th class="noBg">번호</th>
                    <th>결재선명</th>
                </tr>
            </thead>
			<tbody>
				<c:if test="${comFn:nvl(apprLineListTotcnt, 0) == 0}">
					<tr>
						<td colspan="6" class="txtc">검색 조건에 해당하는 데이터가 존재하지 않습니다.</td>
					</tr>
				</c:if>
				<c:if test="${apprLineListTotcnt > 0}">
					<c:forEach var="data" items="${apprLineList}" varStatus="status" >
						<tr class="row"  style="cursor: pointer;" onclick="setApprListInfo('${data.APPL_NO}', '${data.APPL_NM}');">
							<td class="txtc">${data.RNUM}&nbsp;</td>
							<td class="txtc">
								<input type="hidden" name="P_APPL_NM" value="${data.APPL_NM}">
								<input type="hidden" name="P_APPL_NO" value="${data.APPL_NO}">
								${data.APPL_NM}&nbsp;
							</td>
						</tr>
					</c:forEach>
				</c:if>
			</tbody> 
		</table>
		</div>
		
		<div class="pop_list_bottom">
		<!-- Data Paging -->
			<div class="paging_place">
				<comTag:pagingIpro totalCount="${apprLineListTotcnt}" pageNo="${comFn:nvl(param.P_PAGE_NO, 1)}" pageSize="${comFn:nvl(param.P_PAGE_SIZE, 10)}" clickPage="pageObj.clickPage"/>
			</div>
		</div>
        <div class="btn_wrap view_btn">
            <button type="button" class="btn btn_02 btn_close" id="closeBtn">닫기</button>
        </div>
	</div>
	</div>         
</div>