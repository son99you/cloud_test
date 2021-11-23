<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 전자입찰 > 입찰참가업체 등록(팝업)
 *
 * <pre>
 * elbi 
 *    |_iepElbiPopupBidPartcptEntrpsRegistForm.jsp
 * 
 * </pre>
 * @date : 2015. 02. 16. 오전 09:38:41
 * @version : 1.0
 * @author : 은우소프트 손연우
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %>

<script type="text/javascript" src="${jsPath}/ipro/views/ebid/popup/bidPartcptEntrpsRegistForm.js"></script> 
 
<div class="pop_wrap">
	<div class="pop_header">
		<h1 class="tit">입찰참가업체 등록</h1>
	</div> <!--// pop_header E -->
	
	<div class="pop_container">
		<div class="view_wrap typeC">
			<div class="view_area m0 typeB">
				<form id="searchFrm" class="search_form" method="POST">
					<input type="hidden" id="P_PAGE_NO" name="P_PAGE_NO" value="${comFn:nvl(param.P_PAGE_NO, 1)}">
					<input type="hidden" id="P_PAGE_SIZE" name="P_PAGE_SIZE" value="${comFn:nvl(param.P_PAGE_SIZE, 10)}">
					<input type="hidden" name="P_ANNC_NO" value="${ P_ANNC_NO }" >
					<input type="hidden" name="P_ANNC_NGR"  value="${ P_ANNC_NGR }" >
					<input type="hidden" name="P_USR_ID" value="${sessionScope.loginResult.USR_ID}">

					<table>
						<colgroup>
							<col width="15%" align="left">
							<col width="35%" align="left">
							<col width="15%" align="left">
							<col width="35%" align="left">
						</colgroup>
						<tr height="29px">
							<th>
								업체명
							</th>
							<td>
								<input type="text" size="20" maxlength="600" id="P_VEND_NM_S" name="P_VEND_NM_S" value="${param.P_VEND_NM_S}">
							</td>
							<th>
								사업자번호
							</th>
							<td>
								<input type="text" size="20" maxlength="10" id="P_BIZRNO_S" name="P_BIZRNO_S" value="${param.P_BIZRNO_S}" bizrno numeric>
							</td>
						</tr>
					</table>
        		</form>
        	</div>
        </div>
         
         <div class="btn_wrap mt10">
			<button type="button" class="btn_p btn_p1 btn_lookup" id="searchBtn"><img src="${imagePath}/ipro/icon/btn_icon02.png" alt="">조회</button>
		</div> <!--// btn_wrap E -->
         
            
    	<div class="pop_list_wrap">
		<!-- Data Total Count -->
		<div class="pop_list_top">
        	<p class="pop_total">총 <span>${comFn:nvl(bidDcPeoPartcptEntrpsListTotcnt, 0)}</span>건</p>
    	</div>
       
	    <!-- Data List -->
		<div class="pop_list_conts">
			<form id="registFrm" method="POST">
				<input type="hidden" name="P_ANNC_NO" value="${ P_ANNC_NO }" >
				<input type="hidden" name="P_ANNC_NGR"  value="${ P_ANNC_NGR }" >
				<input type="hidden" name="P_USR_ID" value="${sessionScope.loginResult.USR_ID}">
					<table class="tableList">
			            <caption>입찰설명회 목록</caption>
			            <colgroup>
			                <col width="40px"/>
			                <col width="80px"/>
			                <col width="300px"/>
			                <col width="80px"/>
			                <col width="120px"/>
			            </colgroup>			
						<thead>
			                <tr>
			                	<th  class="noBg txtc"><input type="checkbox" id="entrpsChoiseCbx" name="entrpsChoiseCbx" onclick="FwkCmmnUtil.setAllCheck('entrpsChoiseCbx','entrpsChoiseCbx');"></th>
			                    <th class="txtc">번호</th>
			                   <!--  <th>업체구분</th> -->
			                    <th class="txtc">업체명</th>
			                    <th class="txtc">대표자명</th>
			                    <th class="txtc">사업자번호</th>
			                </tr>
			            </thead>
						<tbody>
							<c:if test="${comFn:nvl(bidDcPeoPartcptEntrpsListTotcnt, 0) == 0}">
								<tr>
									<td colspan="5">검색 조건에 해당하는 데이터가 존재하지 않습니다.</td>
								</tr>
							</c:if>
							<c:if test="${bidDcPeoPartcptEntrpsListTotcnt > 0}">
								<c:forEach var="data" items="${bidDcPeoPartcptEntrpsList}" varStatus="status" >
								<tr class="row"  style="cursor: pointer;">
									<td class="txtc"><input type="checkbox" id="entrpsChoiseCbx" name="entrpsChoiseCbx">
											<input type="hidden" name="P_VEND_REG_NO" value="${data.VEND_REG_NO }">	</td>
									<td class="txtc">${data.RNUM}</td>
									<%-- <td>${data.ENTRPS_SE_NM}</td> --%>
									<td class="txtl pl5">${data.VEND_NM}</td>
									<td class="txtc">${data.RPRS_NM}</td>
									<td class="txtc">${comFn:formatBizNumber(data.BIZRNO)}</td>
								</tr>
								</c:forEach>
							</c:if>
						</tbody> 
					</table>
				</form> 
			</div>
			<!-- Data Paging -->
			<div class="pop_list_bottom">
				<div class="pop_list_pager">
					<comTag:pagingIpro totalCount="${bidDcPeoPartcptEntrpsListTotcnt}" pageNo="${comFn:nvl(param.P_PAGE_NO, 1)}" pageSize="${comFn:nvl(param.P_PAGE_SIZE, 10)}" clickPage="pageObj.clickPage"/>
				</div>
			</div>
	        <div class="btn_wrap view_btn">
	            <button type="button" class="btn btn_02 btn_revise" id="registBtn">저장</button>
	            <button type="button" class="btn btn_02 btn_close" id="closeBtn">닫기</button>
	        </div>
		</div>
	</div>         
</div>