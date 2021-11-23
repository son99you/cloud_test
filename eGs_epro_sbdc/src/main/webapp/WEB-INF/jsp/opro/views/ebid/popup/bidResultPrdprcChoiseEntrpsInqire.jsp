<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 전자입찰 > 예가선택업체 조회(팝업)
 *
 * <pre>
 * elbi 
 *    |_iepElbiPopupBidResultPrdprcChoiseEntrpsInqire.jsp
 * 
 * </pre>
 * @date : 2015. 03. 23. 오전 12:12:33
 * @version : 1.0
 * @author : 은우소프트 손연우
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %>

<script type="text/javascript" src="${jsPath}/opro/views/ebid/popup/bidResultPrdprcChoiseEntrpsInqire.js"></script>

<div id="windowPopup">
	<h3 class="windowTitle">예가선택업체 조회</h3>
	<fieldset>
        <legend>예가선택업체 조회</legend>
        <div class="tableLayer">
            <p class="popSubTitle marginSet"></p>
            <table class="tableList">
            <caption>심사평가</caption>
            <colgroup>
                <col width="40px"/>
                <col width="300px"/>
                <col width="140px"/>
                <col width="140px"/>
                <%-- <col width="140px"/> --%>
            </colgroup>			
			<thead>
                <tr>
                    <th>번호</th>
                    <th>업체명</th>
                    <th>대표자</th>
                    <th>사업자번호</th>
                </tr>
            </thead>
			<tbody>
			<c:if test="${empty prdprcChoiseEntrpsInqire}">
					<tr class="row">
						<td colspan="4">검색 조건에 해당하는 데이터가 존재하지 않습니다.</td>
					</tr>
			</c:if>
			<c:if test="${not empty prdprcChoiseEntrpsInqire}">
					<c:forEach var="data" items="${prdprcChoiseEntrpsInqire}" varStatus="status" >
					<tr class="row">
						<td>${data.RNUM}</td>
						<td class="left_T">${data.VEND_NM}</td>
						<td>${data.RPRS_NM}</td>
						<td>${comFn:formatBizNumber(data.BIZRNO)}</td>
					</tr>
					</c:forEach>
			</c:if>
			</tbody> 
		</table>
		</div>
        
        <div class="T_btnLayer fr">
            <button type="button" class="blueBtn L" id="closeBtn">닫기</button>
        </div>
    </fieldset>
</div>