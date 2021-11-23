<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 유관부서평가(신분당선 유관부서평가)
 *
 * <pre>
 * vend
 *    |_ vendEvalDeptList.jsp
 * 
 * </pre>
 * @date : 2017. 06. 15
 * @version : 1.0
 * @author : 은우소프트 홍찬일
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %> 
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %> 

<script type="text/javascript" src="${jsPath}/ipro/views/vend/vendEvalDeptList.js"></script>

<div class="sp_sec">
	<div class="nav_sec">
		<ul id="breadcrumbs">
			<li class="home">홈</li>
			<li>${myMenuList.bigMenuNm}</li>
			<li>${myMenuSubList.smallMenuNm}</li> 
		</ul>
	</div>
	
	<h3 class="sp_tit">유관부서평가</h3>
	
	<form id="searchFrm" method="POST"> 
		<input type="hidden" name="resourceName" value="${ param.resourceName }">
		<input type="hidden" id="P_PAGE_NO" name="P_PAGE_NO" value="${comFn:nvl(param.P_PAGE_NO, 1)}">
		<input type="hidden" id="P_PAGE_SIZE" name="P_PAGE_SIZE" value="${comFn:nvl(param.P_PAGE_SIZE, 10)}">
			
		<div class="sp_cont">
			<div class="sch_box filter_sch">
				<dl class="first">
					<dt>년도</dt>
					<dd>
						<input type="text" id="dyyyy_sch" name="dyyyy_sch" value="${dyyyy_sch }">
					</dd>
					<dt>평가명</dt>
					<dd>
						<input type="text" id="ev_name_sch" name="ev_name_sch" value="${ev_name_sch }">
					</dd>
					<dt>상태</dt>
					<dd>
						<comTag:comCmcdCdValueComboBox name="ev_state_sch" cdId="N00006" selectKey="${ev_state_sch }" headerValue="전체" width="352"/>
					</dd>
				</dl>
			</div>
			
			<button type="button" class="btn ty03 btn_sch" id="searchBtn">조회</button>
			
			<div class="list_top">
				<p class="total">총 <span>${comFn:nvl(vendEvalDeptListTotcnt, 0)}</span>건</p>
				<p class="total" style="float: right;">
					<button type="button" class="btn ty02" id="memorRegistBtn">수기등록</button>
				</p>
			</div><!--// list_top -->

			<table class="list_tb">
           		<colgroup>
					<col width="10%">
					<col width="10%">
					<col width="50%">  
					<col width="15%">
					<col width="15%">
				</colgroup>
				<thead>
	                <tr>
                    	<th>년도</th>
						<th>차수</th>
						<th>평가명</th>
						<th>평가유형</th>
						<th>진행상태</th>
	                </tr>
	            </thead>
				<tbody>
					<c:if test="${ empty vendEvalDeptList }">
						<tr><td colspan="5"><fmt:message key="error.data.not_found" /></td></tr>
					</c:if>
					<c:if test="${ not empty vendEvalDeptList }">
						<c:forEach items="${ vendEvalDeptList }" var="list" varStatus="loop">
							<tr class="pointer" onclick="fnDetailView( '<c:out value='${ list.DYYYY }'/>', '<c:out value='${ list.EV_SEQ }'/>' )">
								<td class="txtc">
									<c:out value="${list.DYYYY}"/>
								</td>
								<td class="txtc">
									<c:out value='${list.EV_SEQ }'/>
								</td>
								<td class="txtl pl5 tit">
									<a href="#"><c:out value='${list.EV_NAME }'/></a>
								</td>
								<td class="txtc">
									<c:out value='${list.EV_TYPE_NM }'/>
								</td>
								<td class="txtc">
									<c:out value='${list.EV_STATE_NM }'/>
								</td>
							</tr>
						</c:forEach>
					</c:if>
				</tbody>
			</table>
			
			<div class="paging">
				<comTag:pagingIpro totalCount="${comFn:nvl(vendEvalDeptListTotcnt,0)}" pageNo="${comFn:nvl(param.P_PAGE_NO, 1)}" pageSize="${comFn:nvl(param.P_PAGE_SIZE, 10)}" clickPage="pageObj.clickPage"/>
			</div> <!--// list_bottom E -->
		</div>
	</form>
</div>

<%-- DETAIL FORM --%>
<form id="detailFrm" method="POST"> 
	<input type="hidden" name="resourceName" value="${ param.resourceName }">
	<input type="hidden" name="dyyyy">
	<input type="hidden" name="ev_seq">
</form>