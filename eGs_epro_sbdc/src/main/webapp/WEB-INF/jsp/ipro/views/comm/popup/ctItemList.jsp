<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 공통 > 계약품목 목록 (팝업)
 *
 * <pre>
 * comm
 *    |_ ctItemList.jsp
 * 
 * </pre>
 *
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %>

<script type="text/javascript" src="${jsPath}/ipro/views/comm/popup/ctItemList.js"></script>
<div class="pop_wrap">
	<div class="pop_header">
		<h1 class="tit">계약품목</h1>
	</div> <!--// pop_header E -->
	
	<div class="pop_container">
		<form id="searchFrm" name="searchFrm" class="search_form" method="POST" >
			<input type="hidden" id="P_PAGE_NO" name="P_PAGE_NO" value="${comFn:nvl(param.P_PAGE_NO, 1)}">
			<input type="hidden" id="P_PAGE_SIZE" name="P_PAGE_SIZE" value="${comFn:nvl(param.P_PAGE_SIZE, 10)}">
			<input type="hidden" id="P_CTRTC" name="P_CTRTC" value="${param.P_CTRTC }">
			<input type="hidden" id="btnId" name="searchGbnId" value="${param.searchGbnId}">
			<input type="hidden" id="gbnDept" name="searchGbnDept" value="${param.searchGbnDept }">
			<input type="hidden" id="setMulti" name="setMulti" value="${param.setMulti}">
			<input type="hidden" id="over" name="over" value="${param.over}"> <!-- 중복가능 -->
			<input type="hidden" id="orgP_ENTRPS_REGIST_NO" name="orgP_ENTRPS_REGIST_NO" value="${param.orgP_ENTRPS_REGIST_NO}" >
			<input type="hidden" id="CNTRCT_MTH_CD" name="CNTRCT_MTH_CD" value="${param.CNTRCT_MTH_CD}" >
			<input type="hidden" id="returnGbn" name="returnGbn" value="${param.returnGbn}" >
			<!-- 값을 받는 방법이 TEXT인경우 td에 id로 적혀있는 경우 -->
			<input type="hidden" id="P_TEXT_GBN" name="P_TEXT_GBN" value="${param.P_TEXT_GBN}" >
			
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
							<td>품번</td>
							<td><input type="text" maxlength="600" id="P_ITEM_NO_S" name="P_ITEM_NO_S" value="${param.P_ITEM_NO_S}"></td>
							<td>품목명</td>
							<td><input type="text" maxlength="600" id="P_ITEM_NM_S" name="P_ITEM_NM_S" value="${param.P_ITEM_NM_S}"></td>
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
	        	<p class="list_count">총 <span>${comFn:nvl(itemListTotcnt, 0)}</span>건</p>
	    	</div> <!--// pop list_top E -->
		    <!-- Data List -->
			<div class="pop_list_conts">
				<table>
		            <caption>업체 목록</caption>
		            <colgroup>
		            
		            	<col width="10%">
		            	<col width="15%">
		            	<col width="*">
		            
		                <%-- <col width="8%">
		                <col width="10%">
		                <col width="10%">
		                <col width="10%">
		                <col width="*">
		                <col width="10%">
		                <col width="10%"> --%>
		            </colgroup>			
					<thead>
		                <tr>
							<th class="noBg">
								선택
		                    </th>
		                    <th>품번</th>
		                    <!-- <th>대분류</th>
		                    <th>중분류</th>
		                    <th>소분류</th> -->
		                    <th>품명</th>
		                    <!-- <th>규격</th>
		                    <th>단위</th> -->
		                </tr>
		            </thead>
					<tbody>
						<c:if test="${comFn:nvl(itemListTotcnt, 0) == 0}">
							<tr>
								<td colspan="3" style="text-align: center;">검색 조건에 해당하는 데이터가 존재하지 않습니다.</td>
							</tr>
						</c:if>
						<c:if test="${itemListTotcnt > 0}">
							<c:forEach var="data" items="${itemList}" varStatus="status" >
								<tr class="row" style="cursor: pointer;">
									<td class="txtc">
										<label for="chargerCbx${status.count }" class="blind">체크박스</label>
										<input type="checkbox" id="chargerCbx${status.count }" name="chargerCbx">
									</td>
									
									<td class="txtc">
										${data.ITEM_NO}
										<input type="hidden" name="P_ITEM_NO" value="${data.ITEM_NO}">
									</td>
									<td class="pl5 txtl">
										${data.ITEM_NM}
										<input type="hidden" name="P_ITEM_NM" value="${data.ITEM_NM}">
									</td>
									
									<%-- <td>
										${data.LLF_CD}
										<input type="hidden" name="P_LLF_CD_S" value="${data.LLF_CD}">
									</td>
									<td>
										${data.MLF_CD}
										<input type="hidden" name="P_MLF_CD_S" value="${data.MLF_CD}">
									</td>
									<td>
										${data.SLF_CD}
										<input type="hidden" name="P_SLF_CD_S" value="${data.SLF_CD}">
									</td>
									<td class="pl5 txtl">
										${data.ITEM_NM}
										<input type="hidden" name="P_ITEM_NM" value="${data.ITEM_NM}">
									</td>
									<td>
										${data.STND_NM}
										<input type="hidden" name="P_STND_NM_S" value="${data.STND_NM}">
									</td>
									<td>
										${data.ITEM_UNCD}
										<input type="hidden" name="P_ITEM_UNCD_S" value="${data.ITEM_UNCD}">
									</td> --%>
								</tr>
							</c:forEach>
						</c:if>
					
					</tbody> 
				</table>
			</div> 
			<!-- Data Paging -->
			<div class="pop_list_bottom">
				<div class="pop_pager">
					<comTag:pagingIpro totalCount="${itemListTotcnt}" pageNo="${comFn:nvl(param.P_PAGE_NO, 1)}" pageSize="${comFn:nvl(param.P_PAGE_SIZE, 10)}" clickPage="pageObj.clickPage"/>
				</div>
			</div> <!--// list_pager E -->
			<div class="btn_wrap view_btn">
				<c:if test="${param.setMulti eq 'Y'}"> 
        			<button type="button" class="btn btn_m btn_orange"   id="choiceBtn">선택</button>
        		</c:if>
				<button type="button" class="btn btn_m btn_del" id="closeBtn" onclick="window.close();">닫기</button>
			</div>
		</div> <!--// list_wrap E -->
	</div> <!--// pop_container E -->

	
<%--page move form --%>
<form id="detailFrm" method="POST" >
	<input type="hidden" name="contextPath" value="${contextPath}" >
	<input type="hidden" name="P_NTT_SN" >
	<input type="hidden" name="P_LOGIN_ID" value="${loginResult.LOGIN_ID}" >
	<input type="hidden" id="resourceName" name="resourceName" value="${param.resourceName}">
	<input type="hidden" id="resourceDesc" name="resourceDesc" value="${param.resourceDesc}">
</form>         
</div> <!--// pop_wrap E -->

