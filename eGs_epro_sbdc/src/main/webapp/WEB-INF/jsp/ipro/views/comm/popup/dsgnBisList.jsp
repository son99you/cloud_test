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

<%-- <link rel="stylesheet" type="text/css" href="${cssPath}/ipro/windowPop.css">
<link rel="stylesheet" type="text/css" href="${cssPath}/ipro/template/pagingPlace.css"> --%>

<script type="text/javascript" src="${jsPath}/ipro/views/comm/popup/dsgnBisList.js"></script>

<div class="pop_wrap">
	<div class="pop_header">
		<h1 class="tit">교재 조회</h1>
	</div> <!--// pop_header E -->
	<div class="pop_container">
		<form id="searchFrm" class="search_form" method="POST" action="">
			<input type="hidden" id="P_PAGE_NO" name="P_PAGE_NO" value="${comFn:nvl(param.P_PAGE_NO, 1)}">
			<input type="hidden" id="P_PAGE_SIZE" name="P_PAGE_SIZE" value="${comFn:nvl(param.P_PAGE_SIZE, 10)}">
			<input type="hidden" id="setMulti" name="setMulti" value="${param.setMulti}">
			<div class="view_wrap typeC">
				<div class="view_area m0 typeB">
					<table>
						<colgroup>
							<col width="15%" align="left">
							<col width="85%" align="left">
						</colgroup>
						<tr height="29px">
							<td>
								교재명
							</td>
							<td>
								<input type="text" class="lineTxt" id="P_COUR_BOOK" name="P_COUR_BOOK" style="width: 160px;" value="${param.P_COUR_BOOK}" >
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
	        	<p class="list_count">총 <span>${comFn:nvl(dsgnListTotcnt, 0)}</span>건</p>
	    	</div> <!--// pop list_top E -->
		    <!-- Data List -->
			<div class="pop_list_conts">
				<table class="tableList" summary="내부 담당자 목록 입니다.">
		            <caption>내부 담당자 목록</caption>
		            <colgroup>
		                <col width="6%">
		                <col width="*">
		            </colgroup>			
					<thead>
		                <tr>
							 <th class="noBg">
							 	<label for="chargerAllCbx" class="blind">체크박스</label>
		                    	<input type="checkbox" id="chargerAllCbx" name="chargerCbx" onclick="FwkCmmnUtil.setAllCheck('chargerAllCbx','chargerCbx');">
		                    </th>		                
		                    <th>교재명</th>
		                </tr>
		            </thead>
					<tbody>
						<c:if test="${comFn:nvl(dsgnListTotcnt, 0) == 0}">
							<tr>
								<td colspan="6" class="txtc">검색 조건에 해당하는 데이터가 존재하지 않습니다.</td>
							</tr>
						</c:if>
						<c:if test="${dsgnListTotcnt > 0}">
							<c:forEach var="data" items="${dsgnList}" varStatus="status" >
								<tr class="row" style="cursor: pointer;">
									<td class="txtc">
										<label for="chargerCbx${status.count }" class="blind">체크박스</label>
										<input type="checkbox" id="chargerCbx${status.count }" name="chargerCbx">
									</td>
									<td>
										<input type="hidden" name="P_COUR_BOOK" value="${data.COUR_BOOK }">
										<input type="hidden" name="P_COUR_CODE" value="${data.COUR_CODE }">
										<input type="hidden" name="P_MAIN_CCNT" value="${data.MAIN_CCNT }">
										${data.COUR_BOOK}&nbsp;
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
					<comTag:pagingIpro totalCount="${dsgnListTotcnt}" pageNo="${comFn:nvl(param.P_PAGE_NO, 1)}" pageSize="${comFn:nvl(param.P_PAGE_SIZE, 10)}" clickPage="pageObj.clickPage"/>
				</div>
			</div> <!--// list_pager E -->
			<div class="btn_wrap view_btn">
				<c:if test="${param.setMulti eq 'Y'}"> 
        			<button type="button" class="btn btn_02 btn_close" id="choiceBtn">선택</button>
        		</c:if>
				<button type="button" class="btn btn_02 btn_close" id="closeBtn" onclick="window.close();">닫기</button>
			</div>
		</div> <!--// list_wrap E -->
	</div> <!--// pop_container E -->
</div>