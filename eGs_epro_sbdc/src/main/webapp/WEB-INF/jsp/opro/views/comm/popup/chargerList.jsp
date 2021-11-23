<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">--%>
<%--
 * 공통 > 담당자 조회 (팝업)
 *
 * <pre>
 * comm 
 *  |_popup
 *   |_chargerList.jsp
 * 
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %>
<%@ taglib prefix="fc" uri="/WEB-INF/tlds/fwkComboBoxTag.tld" %>

<script type="text/javascript" src="${jsPath}/opro/views/comm/popup/chargerList.js"></script>

<div class="pop_sp_sec"> 
	<h1 class="sp_tit">담당자 목록</h1>
	<div class="sp_cont">
		<form id="searchFrm" class="search_form" method="POST">
			<input type="hidden" id="P_PAGE_NO" name="P_PAGE_NO" value="${comFn:nvl(param.P_PAGE_NO, 1)}">
			<input type="hidden" id="P_PAGE_SIZE" name="P_PAGE_SIZE" value="${comFn:nvl(param.P_PAGE_SIZE, 10)}">
			<input type="hidden" id="setMulti" name="setMulti" value="${param.setMulti}">
			<input type="hidden" id="setChargerGbn" name="setChargerGbn" value="${param.setChargerGbn}">    <!--감독원/ 검사원 여부 확인  -->
			<div class="sch_box filter_sch">
				<dl class="first">
					<dt>성명</dt>
					<dd><input type="text" id="P_USR_NM_S" name="P_USR_NM_S" value="${param.P_USR_NM_S}"  class="input w100p"  placeholder="성명" /></dd>
					<dt>사원번호</dt>
					<dd><input type="text" id="P_EMPL_NO_S" name="P_EMPL_NO_S" value="${param.P_EMPL_NO_S }" class="input w100p"  placeholder="사원번호" /></dd>
				</dl>	
			</div>
			<button type="button" class="btn ty03 btn_sch" id="searchBtn">조회</button>
		</form>
		
		<!-- Data Total Count -->
    	 <div class="list_top">
        	<p class="total">총 <span>${comFn:nvl(chargerListTotcnt, 0)}</span>건</p>
    	</div> <!--// pop list_top E -->
		    <!-- Data List -->
		<table class="list_tb">
            <caption>담당자 목록</caption>
            <colgroup>
                <col width="5%">
                <col width="10%">
                <col width="13%">
                <col width="15%">
                <col width="15%">
                <col width="*">
                <col width="*">
            </colgroup>			
			<thead>
                <tr>
                	<c:choose>
						<c:when test="${param.setMulti eq 'Y'}">
							 <th class="noBg">
							 	<label for="chargerAllCbx" class="blind">체크박스</label>
		                    	<input type="checkbox" id="chargerAllCbx" name="chargerCbx" onclick="FwkCmmnUtil.setAllCheck('chargerAllCbx','chargerCbx');">
		                    </th>
						</c:when>
						<c:otherwise>
							 <th class="noBg">번호</th>
						</c:otherwise>
					</c:choose>
                    <th>사용자ID</th>
                    <th>사원번호</th>
                    <th>성명</th>
                    <th>부서명</th>
                    <th>직위</th>
                    <th>전화번호</th>
                    <th>이메일</th>
                </tr>
            </thead>
			<tbody>
				<c:if test="${comFn:nvl(chargerListTotcnt, 0) == 0}">
					<tr>
						<td colspan="8" class="txtc">검색 조건에 해당하는 데이터가 존재하지 않습니다.</td>
					</tr>
				</c:if>
				<c:if test="${chargerListTotcnt > 0}">
					<c:forEach var="data" items="${chargerList}" varStatus="status" >
						<tr class="row" <c:if test="${param.setMulti ne 'Y'}">onclick="setchargerInfo('${data.USR_ID}', '${data.EMPL_NO}', '${data.USR_NM}', '${data.DEPT_CD}','${data.DEPT_NM}','${data.OFPS_CD}', '${data.OFPS_NM }', '${data.TEL_NO }', '${data.EMAL_ADDR }');"</c:if> style="cursor: pointer;">
							<c:if test="${param.setMulti eq 'Y'}">
								<td class="txtc">
									<label for="chargerCbx${status.count }" class="blind">체크박스</label>
									<input type="checkbox" id="chargerCbx${status.count }" name="chargerCbx">
								</td>
							</c:if>
							<c:if test="${param.setMulti ne 'Y'}">
								<td class="no">${data.RNUM}&nbsp;</td>
							</c:if>
							<td>
								<input type="hidden" name="P_USR_ID" value="${data.USR_ID }">
								${data.USR_ID}&nbsp;
							</td>
							<td class="txtc">
								<input type="hidden" name="P_EMPL_NO" value="${data.EMPL_NO }">
								${data.EMPL_NO}&nbsp;
							</td>
							<td class="txtc">
								<input type="hidden" name="P_USR_NM" value="${data.USR_NM }">
								${data.USR_NM}&nbsp;
							</td>
							<td>
								<input type="hidden" name="P_DEPT_NO" value="${data.DEPT_NO }">
								<input type="hidden" name="P_DEPT_NM" value="${data.DEPT_NM }">
								${data.DEPT_NM}&nbsp;
							</td>
							<td align="left">
								<input type="hidden" name="P_OFPS_CD" value="${data.OFPS_CD }">
								<input type="hidden" name="P_OFPS_NM" value="${data.OFPS_NM }">
								${data.OFPS_NM}&nbsp;
							</td>
							<td align="left">
								<input type="hidden" name="P_TEL_NO" value="${data.TEL_NO }">
								${data.TEL_NO}&nbsp;
							</td>
							<td align="left">
								<input type="hidden" name="P_EMAL_ADDR" value="${data.EMAL_ADDR }">
								${data.EMAL_ADDR}&nbsp;
							</td>
						</tr>
					</c:forEach>
				</c:if>
			</tbody> 
		</table>
		<!-- Data Paging -->
		<div class="paging">
			<comTag:pagingIpro totalCount="${comFn:nvl(chargerListTotcnt, 0)}" pageNo="${comFn:nvl(param.P_PAGE_NO, 1)}" pageSize="${comFn:nvl(param.P_PAGE_SIZE, 10)}" clickPage="pageObj.clickPage"/>
		</div>
		<div class="btm_btns">
			<c:if test="${param.setMulti eq 'Y'}"> 
       			<button type="button" class="btn ty02" id="choiceBtn">선택</button>
       		</c:if>
			<button type="button" class="btn ty04" id="closeBtn" onclick="window.close();">닫기</button>
		</div>
		</div> <!--// list_wrap E -->
	</div> <!--// pop_container E -->
</div>