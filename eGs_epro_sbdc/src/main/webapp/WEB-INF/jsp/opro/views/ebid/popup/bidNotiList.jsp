<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 전자입찰 > 입찰공지
 *
 * <pre>
 * elbi 
 *    |_ bidNotiList.jsp
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

<script type="text/javascript" src="${jsPath}/opro/views/ebid/popup/bidNotiList.js"></script>

<div class="pop_wrap">
	<div class="pop_header">
		<h1 class="tit">입찰공지</h1>
	</div> <!--// pop_header E -->
	<div class="pop_container">
		<div class="view_wrap typeC">
        	<div class="view_area">
	            <table>
		            <caption>입찰공지</caption>
		            <colgroup>
		                <col width="*"/>
		                <col width="20%"/>
		            </colgroup>			
					<thead>
		                <tr>
		                    <th>내용</th>
		                    <th>등록일시</th>
		                </tr>
		            </thead>
					<tbody>
						<c:if test="${empty bidPblancOpinionInfoList}">
							<tr class="row">
								<td colspan="2">검색 조건에 해당하는 데이터가 존재하지 않습니다.</td>
							</tr>
						</c:if>
						<c:if test="${not empty bidPblancOpinionInfoList}">
							<c:forEach var="data" items="${bidPblancOpinionInfoList}" varStatus="status" >
								<tr class="row">
									<td class="left_T"><a href="javascript:pageObj.bidNotiPopup('${data.OPNN_SN}');" >${data.RPLY_CNTN}</a></td>
									<td>${comFn:formatDate(data.RPLY_SV_DT,'yyyyMMddHHmmss','yyyy-MM-dd HH:mm')}</td>
								</tr>
							</c:forEach>
						</c:if>
					</tbody> 
				</table>
			</div>
			
			<div style="text-align: center;">
				<label for="chckOk"><input type="checkbox" id="chckOk">&nbsp;해당 입찰공지를 확인 했습니다.</label>
				<input type="hidden" id="P_URL" value="${param.P_URL }">
			</div>
			
			<div class="btn_wrap view_btn">
	        	<button type="button" class="btn btn_m btn_orange" id="chckBtn">확인</button>
	            <button type="button" class="btn btn_m btn_orange" id="closeBtn">닫기</button>
	        </div>			
		</div>
	</div>
</div>

<form id="notiPopupFrm" method="post">
	<input type="hidden" name="P_ANNC_NO" value="${param.P_ANNC_NO}">
	<input type="hidden" name="P_ANNC_NGR" value="${param.P_ANNC_NGR}">
	<input type="hidden" name="P_OPNN_SN" value="">
</form>
<form id="okFrm" method="post">
	<input type="hidden" name="P_ANNC_NO" value="${param.P_ANNC_NO}">
	<input type="hidden" name="P_ANNC_NGR" value="${param.P_ANNC_NGR}">
	<input type="hidden" name="P_ROUND_NO" value="${param.P_ROUND_NO}">
	<input type="hidden" name="P_BID_VEND_PSCD" value="OP12">
</form>