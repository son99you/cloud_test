<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * Admin > 시스템현황 > 메세지관리 목록
 *
 * <pre>
 * sytm
 *    |_ mssgeLogList.jsp
 * 
 * </pre>
 * @date : 2016. 09. 28
 * @version : 1.0
 * @author :  yhs
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %>

<script type="text/javascript" src="${jsPath}/ipro/views/sytm/mssgeLogList.js"></script>

<div class="contents_wrap">
	<ul class="step_wrap">
		<li><a href="#">${myMenuList.bigMenuNm}</a></li>
		<li><a href="#">${myMenuSubList.smallMenuNm}</a></li>
	</ul>
	<div class="tit_wrap">
		<h3 class="tit">메세지관리</h3>
	</div>
		
	<form id="listFrm" class="listFrm" method="POST">
		<input type="hidden" id="resourceName" name="resourceName" value="${param.resourceName}">
		<input type="hidden" id="resourceDesc" name="resourceDesc" value="${param.resourceDesc}">
		<input type="hidden" id="P_PAGE_NO" name="P_PAGE_NO" value="${comFn:nvl(param.P_PAGE_NO, 1)}">
		<input type="hidden" id="P_PAGE_SIZE" name="P_PAGE_SIZE" value="${comFn:nvl(param.P_PAGE_SIZE, 10)}">
		
		<fieldset>
			<div class="view_wrap typeA">
				<div class="view_area">
					<table>
						<colgroup>
							<col width="15%" align="left">
							<col width="35%" align="left">
							<col width="15%" align="left">
							<col width="35%" align="left">
						</colgroup>
						<tr>
							<th>메시지내용</th>
							<td colspan="3"><input type="text" id="P_MSG_CNTN_S" name="P_MSG_CNTN_S" value="${param.P_MSG_CNTN_S }"></td>
						</tr>
					</table>				
				</div>
			</div>
			
			<div class="btn_wrap mt10">
				<button type="button" class="btn btn_m btn_c2" id="searchBtn">조회</button>
			</div>
			
			<div class="list_wrap mt30" id="contentWrap">
				<div class="list_top">
					<p class="total">총 <span>${comFn:nvl(mssgeLogListTotCnt, 0)}</span>건</p>		
				</div> <!--// list_top E -->
				<div class="list_conts">		
					<table id="list_tbl">
						<caption>메세지관리 목록</caption>
		              	<colgroup>
		                   	<col width="10%"/>
		                   	<col width="*"/>
		                   	<col width="8%"/>
		            	</colgroup>
		            	<thead>
					    	<tr>
					        	<th>메시지일련번호</th>
					        	<th>메시지내용</th>
					        	<th>상세</th>
					        </tr>
		            	</thead>
		            	<tbody>
							<c:if test="${comFn:nvl(mssgeLogListTotCnt, 0) == 0}">
								<tr>
									<td colspan="3" class="txt-center">검색 조건에 해당하는 데이터가 존재하지 않습니다.</td>
								</tr>
							</c:if>
							<c:if test="${mssgeLogListTotCnt > 0}">
								<c:forEach var="data" items="${mssgeLogList}" varStatus="status">
									<tr>
										<td>${data.MSG_SN}</td>
										<td class="pl5 txtl">${comFn:toShorten(data.MSG_CNTN, 100)}</td>
										<td>
											<button type="button" class="btn btn_s btn_sch" onclick="detailInqire('${data.MSG_SN}')">상세</button>
										</td>
									</tr>
								</c:forEach>
							</c:if>
						</tbody>
				    </table>
				</div>
				
				<div class="list_bottom">
					<comTag:pagingIpro totalCount="${mssgeLogListTotCnt}" pageNo="${comFn:nvl(param.P_PAGE_NO, 1)}" pageSize="${comFn:nvl(param.P_PAGE_SIZE, 10)}" clickPage="pageObj.clickPage"/>
				</div> <!--// list_bottom E -->
		    </div>				
			
		</fieldset>
	</form>
</div>

<%-- POPUP FORM --%>
<form id="popupFrm" method="POST"> 
	<input type="hidden" name="resourceName" value="${ param.resourceName }">
	<input type="hidden" name="P_MSG_SN">
</form>