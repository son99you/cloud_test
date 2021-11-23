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

<script type="text/javascript" src="${jsPath}/ipro/views/ebid/popup/cleanDetail.js"></script>

<div class="pop_wrap">
	<div class="pop_header">
		<h1 class="tit">청렴계약 이행서약서</h1>
	</div> <!--// pop_header E -->
	<div class="pop_container">
		<div class="view_wrap typeC">
			<div class="view_area">
	    		<table class="table">
		            <caption>청렴이행각서</caption>
		            <colgroup>
		                <col width="*"/>
		            </colgroup>			
					<tbody>
						<tr>
							<td>${param}당사는 청렴계약 취지에 적극 호응하여 한국전기연구원에서 발주하는 모든 공사, 물품, 용역 등의 입찰에 참여함에 있어 당사 임직원과 대리인은<br><br>

   1. 입찰가격의 유지나 특정인의 낙찰을 위한 담합을 하거나 다른 업체와 협정, 결의, 합의하여 입찰의 자유경쟁을 부당하게 저해하는 일체의 불공정한 행위를 않겠습니다.<br><br>
     ㅇ이를 위반하여 경쟁입찰에 있어서 특정인의 낙찰을 위하여 담합을 주도한 것이 사실로 드러날 경우 한국전기연구원에서 발주하는 입찰에 입찰참가자격제한 처분을 받은 날로부터 2년 동안 참가하지 않겠으며,<br><br>
     ㅇ 경쟁입찰에 있어서 입찰자간에 서로 상의하여 미리 입찰가격을 협정하거나 특정인의 낙찰을 위하여 담합을 한 사실이 드러날 경우 한국전기연구원가 시행하는 입찰에 입찰참가자격 제한 처분을 받은 날로부터 1년동안 참여하지 않고,<br><br>
     ㅇ 위와 같이 담합 등 불공정행위를 한 사실이 드러날 경우 독점규제 및 공정거래에관한법률에 따라 공정거래위원회에 고발하여 과징금등을 부과토록 하는데 일체의 이의를 제기하지 않겠습니다.<br><br>
   2. 입찰․계약체결 및 계약이행 과정에서 관계직원에게 직․간접적으로 금품․향응 등의 부당한 이익을 제공하지 않겠습니다.<br><br>
     ㅇ 이를 위반하여 입찰, 계약의 체결 또는 계약이행과 관련하여 관계직원에게 금품, 향응 등을 제공함으로써 입찰에 유리하게 되어 계약이 체결되었거나 계약이행 과정에서 편의를 받아 부실하게 시공 또는 제조한 사실이 드러날 경우에는 한국전기연구원가 시행하는 입찰에 입찰참가자격제한 처분을 받은 날로부터 2년동안 참가하지 않겠으며,<br><br>
    ㅇ 입찰 및 계약조건이 입찰자 및 낙찰자에게 유리하게 되도록 하거나, 계약목적물의 이행을 부실하게 할 목적으로 관계직원에게 금품, 향응 등을 제공한 사실이 드러날 경우에는 한국전기연구원가 시행하는 입찰에 입찰참가자격 제한 처분을 받은 날로부터 1년 동안 참가하지 않고,<br><br>
     ㅇ 입찰, 계약체결 및 계약이행과 관련하여 관계직원에게 금품, 향응 등을 제공한 사실이 드러날 경우에는 한국전기연구원가 시행하는 입찰에 입찰참가자격제한 처분을 받은 날로부터 6개월 동안 참가하지 않겠습니다.<br><br>
   3. 입찰, 계약체결 및 계약이행과 관련하여 관계직원에게 금품, 향응 등을 제공한 사실이 드러날 경우에는 계약체결 이전의 경우에는 낙찰자 결정 취소, 계약이행 전에는 계약취소, 계약이행 이후에는 당해 계약의 전부 또는 일부계약을 해제 또는 해지하여도 감수하겠으며, 민․형사상 이의를 제기하지 않겠습니다.<br><br>
   4. 회사 임․직원이 관계직원에게 금품, 향응 등을 제공하거나 담합 등 불공정 행위를 하지 않도록 하는 회사윤리강령과 내부비리 제보자에 대해서도 일체의 불이익처분을 하지 않는 사규를 제정토록 노력하겠습니다.<br><br> 
    위 청렴계약 서약은 상호신뢰를 바탕으로 한 약속으로서 반드시 지킬 것이며, 낙찰자로 결정될 시 본 서약내용을 그대로 계약특수조건으로 계약하여 이행하고, 입찰참가자격 제한, 계약해지 등 한국전기연구원의 조치와 관련하여 당사가 한국전기연구원을 상대로 손해배상을 청구하거나 당사를 배제하는 입찰에 관하여 민․형사상 어떠한 이의도 제기하지 않을 것을 서약합니다.<br><br>
    <span style="float: right;">${comFn:formatDate(cleanDetail.SIGN_DT,'yyyyMMddHHmmss','yyyy-MM-dd')}</span><br>
    <span style="float: right;">서약자 : ${cleanDetail.VEND_NM } 회사 대표 ${cleanDetail.RPRS_NM}</span><br>
    <p style="text-align: center;">한국전기연구원 사장 귀하<p></td>
						</tr>
					</tbody> 
				</table>
			</div>
		</div>
	</div>	
	<div style="text-align: center; margin-top: 10px;">
		제출일시 : ${comFn:formatDate(cleanDetail.SIGN_DT,'yyyyMMddHHmmss','yyyy-MM-dd HH:mm:ss')}
	</div>
        
     <div class="btn_wrap view_btn fr">
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