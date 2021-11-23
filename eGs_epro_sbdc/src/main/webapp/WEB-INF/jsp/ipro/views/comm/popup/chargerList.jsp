<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">--%>
<%--
 * 공통 > 담당자 조회 (팝업)
 *
 * <pre>
 * comm 
 *  |_ popup
 *   |_ chargerList.jsp
 * 
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %>
<%@ taglib prefix="fc" uri="/WEB-INF/tlds/fwkComboBoxTag.tld" %>

<script type="text/javascript" src="${jsPath}/ipro/views/comm/popup/chargerList.js"></script>

<div class="layout-pop">
	<form id="searchFrm" class="search_form" method="POST">
		<input type="hidden" id="P_PAGE_NO" name="P_PAGE_NO" value="${comFn:nvl(param.P_PAGE_NO, 1)}">
		<input type="hidden" id="P_PAGE_SIZE" name="P_PAGE_SIZE" value="${comFn:nvl(param.P_PAGE_SIZE, 10)}">
		<input type="hidden" id="setMulti" name="setMulti" value="${param.setMulti}">
		<input type="hidden" id="setChargerGbn" name="setChargerGbn" value="${param.setChargerGbn}">
		
		<div class="pop_header">
			<div class="title">담당자 목록</div>
		</div> <!--// pop_header E -->
		
		<div class="form-setting-box">
	        <div class="ui-setting">
	        
	        	<!-- Form Setting -->
	            <div class="form-setting">
	                <span class="txt-label">성명</span>
	                <input type="text" id="P_USR_NM_S" name="P_USR_NM_S" value="${param.P_USR_NM_S}"  class="component-input w50"/>
	            </div>
	            <!-- //Form Setting -->
	            
	            <!-- Form Setting -->
	            <div class="form-setting">
	                <span class="txt-label">사원번호</span>
	                <input type="text" id="P_EMPL_NO_S" name="P_EMPL_NO_S" value="${param.P_EMPL_NO_S }" class="component-input w50"/>
	            </div>
	            <!-- //Form Setting -->
	            
	        </div>
	  </div>
	  
	  
	  <!-- buttons -->
	  <div class="setting-button">
	      <button class="component-button" id="searchBtn">조회</button>
	  </div>
	  <!-- //buttons -->
	  <div class="area-list">
	      <!-- Option -->
	      <div class="table-option">
	          <!-- Right -->
	          <div class="option-right">
	              <div class="table-num type-fleft">
	                  총 <strong>${comFn:nvl(chargerListTotcnt, 0)}</strong>건
	              </div>
	          </div>
	          <!-- //Right -->
	
	
	      </div>
	      <!-- //Option -->

			<table class="component-table">
				<colgroup>
					<col width="5%">
	                <col width="10%">
	                <col width="10%">
	                <col width="12%">
	                <col width="15%">
	                <col width="12%">
	                <col width="15%">
	                <col width="*">
				</colgroup>
				<thead>
					<tr>
	                	<c:choose>
							<c:when test="${param.setMulti eq 'Y'}">
								 <th class="txt-center">
								 	<label for="chargerAllCbx" class="component-checkbox">
				                    	<input type="checkbox" id="chargerAllCbx" name="chargerCbx" onclick="FwkCmmnUtil.setAllCheck('chargerAllCbx','chargerCbx');">
				                    	<i></i>
			                    	</label>
			                    </th>
							</c:when>
							<c:otherwise>
								 <th class="txt-center">번호</th>
							</c:otherwise>
						</c:choose>
	                    <th>사용자ID</th>
	                    <th class="txt-center">사원번호</th>
	                    <th class="txt-center">성명</th>
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
							<tr class="row" <c:if test="${param.setMulti ne 'Y'}">onclick="setchargerInfo('${data.USR_ID}', '${data.EMPL_NO}', '${data.USR_NM}', '${data.DEPT_NO}','${data.DEPT_NM}','${data.OFPS_CD}', '${data.OFPS_NM }', '${data.TEL_NO }', '${data.EMAL_ADDR }');"</c:if> style="cursor: pointer;">
								<c:if test="${param.setMulti eq 'Y'}">
									<td class="txt-center">
										<label for="chargerCbx${status.count }" class="component-checkbox">
											<input type="checkbox" id="chargerCbx${status.count }" name="chargerCbx">
											<i></i>
										</label>
									</td>
								</c:if>
								<c:if test="${param.setMulti ne 'Y'}">
									<td class="txt-center">${data.RNUM}&nbsp;</td>
								</c:if>
								<td>
									<input type="hidden" name="P_USR_ID" value="${data.USR_ID }">
									${data.USR_ID}&nbsp;
								</td>
								<td class="txt-center">
									<input type="hidden" name="P_EMPL_NO" value="${data.EMPL_NO }">
									${data.EMPL_NO}&nbsp;
								</td>
								<td class="txt-center">
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
		</div>
		
		<!-- pageing -->
		<div class="component-pageing">
			<comTag:pagingIpro totalCount="${comFn:nvl(chargerListTotcnt,0)}" pageNo="${comFn:nvl(param.P_PAGE_NO, 1)}" pageSize="${comFn:nvl(param.P_PAGE_SIZE, 10)}" clickPage="pageObj.clickPage"/>
		</div>
		<!--//pageing -->
		
		<div class="bottom-buttons">
			<c:if test="${param.setMulti eq 'Y'}">
				<a href="javascript:" class="btn-bottom type-b" id="choiceBtn">선택</a>
			</c:if>
			<a href="javascript:" class="btn-bottom type-a" onclick="window.close();">닫기</a>
		</div>
	</form>
</div>