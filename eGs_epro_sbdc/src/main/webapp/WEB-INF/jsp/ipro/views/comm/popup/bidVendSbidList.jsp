<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 공통 > 투찰 업체 열람 조회 팝업
 *
 * <pre>
 * comm
 *    |_popup
 *        |_bidVendSbidList.jsp
 * 
 * </pre>
 * @date : 2019. 07. 23. 
 * @version : 1.0
 * @author : 은우소프트 맹경열
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %>

<div class="pop_wrap">
	<div class="pop_header">
		<h1 class="tit">입찰 업체 조회</h1>
	</div> <!--// pop_header E -->
	
	<div class="pop_container">
    	<form id="listFrm" method="POST">

			<input type="hidden" id="P_ANNC_NO" name="P_ANNC_NO" value="${param.P_ANNC_NO}">
			<input type="hidden" id="P_ANNC_NGR" name="P_ANNC_NGR" value="${param.P_ANNC_NGR}">
			<input type="hidden" id="P_ROUND_NO" name="P_ROUND_NO" value="${param.P_ROUND_NO}">

	    	<div class="pop_list_wrap">
				<div class="list_top typeB">
					
					<div class="pop_list_conts">
						<table>
				            <caption>입찰 업체 열람 목록</caption>
				            <colgroup>
				                <col width="35%"/>
				                <col width="30%"/>
				                <col width="35%"/>
				            </colgroup>			
							<thead>
				                <tr>
				                    <th scope="col">업체명</th>
				                    <th scope="col">사업자번호</th>
				                    <th scope="col">대표자명</th>
				                </tr>
				            </thead>
							<tbody>
								<c:if test="${empty bidVendSbidList}">
									<tr>
										<td colspan="3" class="txtc">데이터가 존재하지 않습니다.</td>
									</tr>
								</c:if>
								<c:if test="${!empty bidVendSbidList}">
									<c:forEach var="data" items="${bidVendSbidList}" varStatus="status">
										<tr class="row">
											<td>${data.VEND_NM}</td>
											<td>${comFn:formatBizNumber(data.BIZRNO)}</td>
											<td>${data.RPRS_NM}</td>
										</tr>
									</c:forEach>
								</c:if>
							</tbody> 
						</table>
					</div>
				</div>	
			</div>
    	</form>		
	</div>
	<div class="btn_wrap view_btn">
	    <button type="button" class="btn btn_m btn_del" id="closeBtn" onclick="window.close();">닫기</button>
	</div>
</div>