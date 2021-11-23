<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">--%>
<%--
 * 공통 > 업체팝업 조회(팝업)
 *
 * <pre>
 * comm 
 *  |_popup
 *   |_entrpsList.jsp
 * 
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %>

<script type="text/javascript" src="${jsPath}/opro/views/comm/popup/entrpsList.js"></script>
<div class="pop_sp_sec"> 
	<h1 class="sp_tit">업체목록</h1>
	<div class="sp_cont">
		<form id="searchFrm" class="search_form" method="POST" >
			<input type="hidden" id="P_PAGE_NO" name="P_PAGE_NO" value="${comFn:nvl(param.P_PAGE_NO, 1)}">
			<input type="hidden" id="P_PAGE_SIZE" name="P_PAGE_SIZE" value="${comFn:nvl(param.P_PAGE_SIZE, 10)}">
			<input type="hidden" id="setMulti" name="setMulti" value="${param.setMulti}">
			<input type="hidden" id="P_SEARCH_S" name="P_SEARCH_S" value="Y">
			
			<div class="sch_box filter_sch">
				<dl class="first">
					<dt>업체명</dt>
					<dd><input type="text" id="P_VEND_NM_S" name="P_VEND_NM_S" value="${param.P_VEND_NM_S}"  class="input w100p"  placeholder="업체명" /></dd>
					<dt style="width:16%">사업자번호</dt>
					<dd><input type="text" id="P_BIZRNO_S" name="P_BIZRNO_S" value="${param.P_BIZRNO_S }" class="input w100p"  placeholder="사업자번호" /></dd>
				</dl>	
				<dl>
					<dt>업체코드</dt>
					<dd><input type="text" id="P_VEND_REG_NO_S" name="P_VEND_REG_NO_S" value="${param.P_VEND_REG_NO_S }" class="input w100p"  placeholder="업체코드" /></dd>
				</dl>
			</div>
			<button type="button" class="btn ty03 btn_sch" id="searchBtn">조회</button>
		</form>
		
		<!-- Data Total Count -->
		<div class="list_top">
        	<p class="total">총 <span>${comFn:nvl(entrpsInqireListTotcnt, 0)}</span>건</p>
    	</div> <!--// pop list_top E -->
	    	
		    <!-- Data List -->
		<table class="list_tb">
            <caption>업체 목록</caption>
            <colgroup>
                <col width="5%">
                <col width="20%">
                <col width="20%">
                <col width="*">
                <col width="20%">
            </colgroup>			
			<thead>
                <tr>
                	<c:choose>
	                	<c:when test="${param.setMulti eq 'Y'}">
							 <th class="noBg">
							 	<label for="entrpsAllCbx" class="blind">체크박스</label>
		                    	<input type="checkbox" id="entrpsAllCbx" name="entrpsCbx" onclick="FwkCmmnUtil.setAllCheck('entrpsAllCbx','entrpsCbx');">
		                    </th>
						</c:when>
						<c:otherwise>
							 <th class="noBg">번호</th>
						</c:otherwise>
					</c:choose>
                    <th>업체코드</th>
                    <th>사업자번호</th>
                    <th>업체명</th>
                    <th>대표자명</th>
                </tr>
            </thead>
			<tbody>
				<c:if test="${comFn:nvl(entrpsInqireListTotcnt, 0) == 0}">
					<tr>
						<td colspan="5" style="text-align: center;">검색 조건에 해당하는 데이터가 존재하지 않습니다.</td>
					</tr>
				</c:if>
				<c:if test="${entrpsInqireListTotcnt > 0}">
					<c:forEach var="data" items="${entrpsInqireList}" varStatus="status" >
						<tr class="row" <c:if test="${param.setMulti ne 'Y'}">onclick="setEntrpsInfo('${data.VEND_REG_NO}','${data.BIZRNO }', '${data.VEND_NM}', '${data.RPRS_NM}','${data.ADDR_NM}','${data.TEL_NO}','${data.EMAL_ADDR}');"</c:if> style="cursor: pointer;">
							<c:if test="${param.setMulti eq 'Y'}">
								<td class="txtc">
									<label for="entrpsCbx${status.count }" class="blind">체크박스</label>
									<input type="checkbox" id="entrpsCbx${status.count }" name="entrpsCbx">
								</td> 
							</c:if>
							<c:if test="${param.setMulti ne 'Y'}"> 
								<td class="no">${data.RNUM}&nbsp;</td>
							</c:if>
							<td>
								${comFn:formatBizNumber(data.VEND_REG_NO)}&nbsp;
								<input type="hidden" name="P_VEND_REG_NO" value="${data.VEND_REG_NO}">
							</td>
							<td>
								${comFn:formatBizNumber(data.BIZRNO)}&nbsp;
								<input type="hidden" name="P_BIZRNO" value="${data.BIZRNO}"> 
							</td>
							<td class="txtl pl5">
								${data.VEND_NM}&nbsp; 
								<input type="hidden" name="P_VEND_NM" value="${data.VEND_NM}"> 
							</td>
							<td>
								${data.RPRS_NM}&nbsp;
								<input type="hidden" name="P_RPRS_NM" value="${data.RPRS_NM}">
								<input type="hidden" name="P_TEL_NO" value="${data.TEL_NO}">
								<input type="hidden" name="P_EMAL_ADDR" value="${data.EMAL_ADDR}">
								<input type="hidden" name="P_ADDR_NM" value="${data.ADDR_NM}">
							</td>
						</tr>
					</c:forEach>
				</c:if>
			</tbody> 
		</table>
			
		<!-- Data Paging -->
		<div class="paging">
			<comTag:pagingIpro totalCount="${comFn:nvl(entrpsInqireListTotcnt)}" pageNo="${comFn:nvl(param.P_PAGE_NO, 1)}" pageSize="${comFn:nvl(param.P_PAGE_SIZE, 10)}" clickPage="pageObj.clickPage"/>
		</div>
			
		<div class="btm_btns">
			<c:if test="${param.setMulti eq 'Y'}"> 
       			<button type="button" class="btn ty02"   id="choiceBtn">선택</button>
       		</c:if>
			<button type="button" class="btn ty04" id="closeBtn" onclick="window.close();">닫기</button>
		</div>
	</div> <!--// pop_container E -->
</div> <!--// pop_wrap E -->