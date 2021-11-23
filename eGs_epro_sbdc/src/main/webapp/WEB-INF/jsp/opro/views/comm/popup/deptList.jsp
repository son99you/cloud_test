<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">--%>
<%--
 * 공통 > 요구부서 조회(팝업)
 *
 * <pre>
 * comm 
 *  |_popup
 *   |_rqstDeptList.jsp
 * 
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %>

<script type="text/javascript" src="${jsPath}/opro/views/comm/popup/deptList.js"></script> 
 
<div class="pop_sp_sec"> 
	<h3 class="sp_tit">부서 목록</h3>
	<div class="sp_cont">
		<form id="searchFrm" class="search_form" method="POST" >
			<input type="hidden" id="P_PAGE_NO" name="P_PAGE_NO" value="${comFn:nvl(param.P_PAGE_NO, 1)}">
			<input type="hidden" id="P_PAGE_SIZE" name="P_PAGE_SIZE" value="${comFn:nvl(param.P_PAGE_SIZE, 10)}">
			<input type="hidden" id="setMulti" name="setMulti" value="${param.setMulti}">
		
			<div class="sch_box filter_sch">
				<dl class="first">
					<dt>부서코드</dt>
					<dd><input type="text" id="P_DEPT_NO_S" name="P_DEPT_NO_S" value="${param.P_DEPT_NO_S}"  class="input w100p"  placeholder="부서코드" /></dd>
					<dt>부서명</dt>
					<dd><input type="text" id="P_DEPT_NM" name="P_DEPT_NM" value="${param.P_DEPT_NM }" class="input w100p"  placeholder="부서명" /></dd>
				</dl>	
			</div>
			<button type="button" class="btn ty03 btn_sch" id="searchBtn">조회</button>
          </form>
		<!-- Data Total Count -->
	    	<div class="list_top">
				<p class="total">총 <span>${comFn:nvl(deptInqireListTotcnt, 0)}</span>건</p>
	        </div>
	       
	   		<table class="list_tb">
	            <caption>부서 목록</caption>
	            <colgroup>
	                <col width="5%">
	                <col width="30%">
	                <col width="*">
	            </colgroup>			
				<thead>
	                <tr>
	                	<c:choose>
							<c:when test="${param.setMulti eq 'Y'}">
								 <th class="noBg">
								 	<label for="deptAllCbx" class="blind">체크박스</label>
			                    	<input type="checkbox" id="deptAllCbx" name="deptCbx" onclick="FwkCmmnUtil.setAllCheck('deptAllCbx','deptCbx');">
			                    </th>
							</c:when>
							<c:otherwise>
								 <th class="noBg">번호</th>
							</c:otherwise>
						</c:choose>
						<th>부서코드</th>
						<th>부서명</th>
	                </tr> 
	            </thead> 
				<tbody>
					<c:if test="${comFn:nvl(deptInqireListTotcnt, 0) == 0}"> 
						<tr>
							<td colspan="3" style="text-align: center;">검색 조건에 해당하는 데이터가 존재하지 않습니다.</td>
						</tr>
					</c:if>
					<c:if test="${deptInqireListTotcnt > 0}">
						<c:forEach var="data" items="${deptInqireList}" varStatus="status">
							<tr class="row" onclick="setdeptInfo('${data.DEPT_NO}', '${data.DEPT_NM}');" style="cursor: pointer;">
							<tr class="row" <c:if test="${param.setMulti ne 'Y'}">onclick="setdeptInfo('${data.DEPT_NO}', '${data.DEPT_NM}');"</c:if> style="cursor: pointer;">
								<c:if test="${param.setMulti eq 'Y'}">
									<td class="txtc">
										<label for="deptCbx${status.count }" class="blind">체크박스</label>
										<input type="checkbox" id="deptCbx${status.count }" name="deptCbx">
									</td>
								</c:if>
								<c:if test="${param.setMulti ne 'Y'}">
									<td class="no">${data.RNUM}&nbsp;</td>
								</c:if>
								<td>
									<input type="hidden" name="P_DEPT_NO" value="${data.DEPT_NO }">
									${data.DEPT_NO}&nbsp;
								</td>
								<td class="txtl pl5">
									<input type="hidden" name="P_DEPT_NM" value="${data.DEPT_NM }">
									${data.DEPT_NM}&nbsp;
								</td>
							</tr>
						</c:forEach>
					</c:if>
				</tbody> 
			</table>  
			<!-- Data Paging -->
			<div class="paging">
				<comTag:pagingIpro totalCount="${comFn:nvl(deptInqireListTotcnt, 0)}" pageNo="${comFn:nvl(param.P_PAGE_NO, 1)}" pageSize="${comFn:nvl(param.P_PAGE_SIZE, 10)}" clickPage="pageObj.clickPage"/>
			</div>
			<!-- 페이징 끝 -->
    		<div class="btm_btns">
		   		<c:if test="${param.setMulti eq 'Y'}">
	        		<button type="button" class="btn ty02" id="choiceBtn">선택</button>
	        	</c:if>
				<button type="button" class="btn ty04" id="closeBtn" onclick="window.close();">닫기</button>
    		</div>
	</div> 
</div>
<%--page move form --%>
<form id="detailFrm" method="POST" >
	<input type="hidden" name="contextPath" value="${contextPath}" >
	<input type="hidden" name="P_NTT_SN" >
	<input type="hidden" name="P_LOGIN_ID" value="${loginResult.LOGIN_ID}" >
	<input type="hidden" id="resourceName" name="resourceName" value="${param.resourceName}">
	<input type="hidden" id="resourceDesc" name="resourceDesc" value="${param.resourceDesc}">
</form>      
