<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">--%>
<%--
 * 공통 >품목팝업 조회(팝업)
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

<script type="text/javascript" src="${jsPath}/ipro/views/comm/popup/itemList.js"></script>
<div class="pop_sp_sec"> 
	<h1 class="sp_tit">품목목록</h1>
	<div class="sp_cont">
		<form id="searchFrm" class="search_form" method="POST" >
			<input type="hidden" id="P_PAGE_NO" name="P_PAGE_NO" value="${comFn:nvl(param.P_PAGE_NO, 1)}">
			<input type="hidden" id="P_PAGE_SIZE" name="P_PAGE_SIZE" value="${comFn:nvl(param.P_PAGE_SIZE, 10)}">
			<input type="hidden" id="setMulti" name="setMulti" value="${param.setMulti}">
			<input type="hidden" id="P_SEARCH_S" name="P_SEARCH_S" value="Y">
			<input type="hidden" id="P_CONT_SECD_S" name="P_CONT_SECD_S" value="${param.P_CONT_SECD_S}">
			
			<div class="sch_box filter_sch">
				<dl class="first">
					<dt>품목코드</dt>
					<dd><input type="text" id="P_ITEM_NO_S" name="P_ITEM_NO_S" value="${param.P_ITEM_NO_S}"  class="input w100p"  placeholder="품명코드" /></dd>
					<dt>품명</dt>
					<dd><input type="text" id="P_ITEM_NM_S" name="P_ITEM_NM_S" value="${param.P_ITEM_NM_S }" class="input w100p"  placeholder="품명" /></dd>
				</dl>	
			</div>
			<button type="button" class="btn ty03 btn_sch" id="searchBtn">조회</button>
		</form>
		
		
		<!-- Data Total Count -->
	    <div class="list_top">
			<p class="total">총 <span>${comFn:nvl(itemListTotcnt, 0)}</span>건</p>
        </div>
	    	
		<table class="list_tb">
            <caption>품목목록</caption>
            <colgroup>
                <col width="5%">
                <col width="15%">
                <col width="15%">
                <col width="*">
                <col width="12%">
                <col width="12%">
                <col width="12%">
            </colgroup>			
			<thead>
                <tr>
                	<c:choose>
	                	<c:when test="${param.setMulti eq 'Y'}">
							 <th class="noBg">
							 	<label for="itemAllCbx" class="blind">체크박스</label>
		                    	<input type="checkbox" id="itemAllCbx" name="itemCbx" onclick="FwkCmmnUtil.setAllCheck('itemAllCbx','itemCbx');">
		                    </th>
						</c:when>
						<c:otherwise>
							 <th class="noBg">번호</th>
						</c:otherwise>
					</c:choose>
                    <th>대분류명</th>
                    <th>소분류명</th>
                    <th>품명</th>
                    <th>단위</th>
                    <th>규격</th>
                    <th>품목코드</th>
                </tr>
            </thead>
			<tbody>
				<c:if test="${comFn:nvl(itemListTotcnt, 0) == 0}">
					<tr>
						<td colspan="7" style="text-align: center;">검색 조건에 해당하는 데이터가 존재하지 않습니다.</td>
					</tr>
				</c:if>
				<c:if test="${itemListTotcnt > 0}">
					<c:forEach var="data" items="${itemList}" varStatus="status" >
						<tr class="row" <c:if test="${param.setMulti ne 'Y'}">onclick="setitemInfo('${data.LLF_NM}','${data.LLF_CD }','${data.SLF_NM}','${data.SLF_CD }','${data.ITEM_NM}','${data.ITEM_UNCD }','${data.STND_NM }','${data.ITEM_NO}');"</c:if> style="cursor: pointer;">
							<c:if test="${param.setMulti eq 'Y'}">
								<td class="txtc">
									<label for="itemCbx${status.count }" class="blind">체크박스</label>
									<input type="checkbox" id="itemCbx${status.count }" name="itemCbx">
								</td> 
							</c:if>
							<c:if test="${param.setMulti ne 'Y'}"> 
								<td class="no">${data.RNUM}&nbsp;</td>
							</c:if>
							<td class="txtc">
								${data.LLF_NM}&nbsp;
								<input type="hidden" name="P_LLF_NM" value="${data.LLF_NM}">
								<input type="hidden" name="P_LLF_CD" value="${data.LLF_CD}">
							</td>
							<td class="txtc">
								${data.SLF_NM}&nbsp;
								<input type="hidden" name="P_SLF_NM" value="${data.SLF_NM}"> 
								<input type="hidden" name="P_SLF_CD" value="${data.SLF_CD}"> 
							</td>
							<td class="txtc">
								${data.ITEM_NM}&nbsp; 
								<input type="hidden" name="P_ITEM_NM" value="${data.ITEM_NM}"> 
							</td>
							<td  class="txtc">
								${data.ITEM_UNCD_NM}&nbsp;
								<input type="hidden" name="P_ITEM_UNCD" value="${data.ITEM_UNCD}">
								<input type="hidden" name="P_ITEM_UNCD_NM" value="${data.ITEM_UNCD_NM}">
								<input type="hidden" name="P_ITEM_DESC" value="${data.ITEM_DESC}">
							</td>
							<td  class="txtc">
								${data.STND_NM}&nbsp;
								<input type="hidden" name="P_STND_NM" value="${data.STND_NM}">
							</td>
							<td class="txtc">
								${data.ITEM_NO}&nbsp; 
								<input type="hidden" name="P_ITEM_NO" value="${data.ITEM_NO}"> 
							</td>
						</tr>
					</c:forEach>
				</c:if>
			</tbody> 
		</table>
		
		<!-- Data Paging -->
		<div class="paging">
			<comTag:pagingIpro totalCount="${comFn:nvl(itemListTotcnt, 0)}" pageNo="${comFn:nvl(param.P_PAGE_NO, 1)}" pageSize="${comFn:nvl(param.P_PAGE_SIZE, 10)}" clickPage="pageObj.clickPage"/>
		</div> <!--// list_pager E -->
		
		<div class="btm_btns">
			<c:if test="${param.setMulti eq 'Y'}"> 
       			<button type="button" class="btn ty02"   id="choiceBtn">선택</button>
       		</c:if>
			<button type="button" class="btn ty04" id="closeBtn" onclick="window.close();">닫기</button>
		</div>
	</div> <!--// list_wrap E -->
</div> <!--// pop_container E -->
