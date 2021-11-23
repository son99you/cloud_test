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

<script type="text/javascript" src="${jsPath}/ipro/views/ebid/popup/grntDetail.js"></script>

<div class="pop_wrap">
	<div class="pop_header">
		<h1 class="tit">입찰보증금 지급각서</h1>
	</div> <!--// pop_header E -->
	<div class="pop_container">
		<div class="view_wrap typeC">
			<div class="view_area">
				<table class="table">
					<caption>입찰보증금 지급각서</caption>
					<colgroup>
						<col width="*"/>
					</colgroup>			
					<tbody>
						<tr>
							<td>이 입찰에서 본인이 낙찰자로 선정된 후 정당한 이유없이 소정의 기일내에 계약을 체결하지 않아 입찰보증금의 국고귀속 사유가 발생한
경우에는 귀기관의 요청에 따라 국가를 당사자로하는 계약에 관한 법률시행령 제38조(입찰보증금의 국고귀속)의 규정에 의한 낙찰금액의
5%이상에 해당하는 입찰보증금을 지체없이 귀기관에 현금으로 납입하겠으며, 기타 입찰보증금의 국고귀속 사유로 인한 여하한 조치에 
대하여도 귀기관의 결정 또는 요구에 따를 것임을 확약합니다.</td>
						</tr>
					</tbody> 
				</table>
			</div>
		</div>
	</div>
		
	<div style="text-align: center; margin-top: 10px;">
		제출일시 : ${comFn:formatDate(grntDetail.SIGN_DT,'yyyyMMddHHmmss','yyyy-MM-dd HH:mm:ss')}
	</div>
        
    <div class="btn_wrap view_btn">
		<button type="button" class="btn btn_02 btn_close" id="closeBtn" >닫기</button>
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
</form>