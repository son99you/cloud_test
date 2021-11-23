<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 전자입찰 >공동수급협정서 숙지안내 팝업.
 *
 * <pre>
 * elbi
 	  |_	popup 
 *    			|_ oepElbiPopupBidPartcptReqstComptUseAgree.jsp
 * 
 * </pre>
 * @date : 2017. 03. 09
 * @version : 1.0
 * @author : 은우소프트 홍찬일
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %>

<script type="text/javascript" src="${jsPath}/opro/views/ebid/popup/bidPartcptReqstComptUseAgree.js"></script>

<div id="windowPopup">
	<h4 class="windowTitle">공동수급협정서 제출 안내</h4>
	<fieldset class="useAgree">
		<legend class="blind">공동수급협정서 제출 안내</legend>
		<div class="tableLayer" tabindex="0"  style="border: 1px solid #d9d9d9; padding: 5px 5px 5px 5px;">
			<h4 style="font-family: NanumGothic, NanumGothic, Dotum, 돋움, Arial, sans-serif; font-size: 14px; color: #555; line-height: 1.5em; font-weight: normal;">
				&nbsp;&nbsp;협정서는 제출 이후 수정하실 수 없습니다. 따라서 참가자 협정내용을 사전에 반드시 확정하시고 신청하시기 바랍니다.
			</h4>
			<h5 class="windowTitle" style="font-size: 13px; font-weight: normal; color:#656565; margin-top: 21px;">
				 공동수급이 허용되는 입찰에서 수급체를 구성하고 단독으로 참여하는 경우 무효처리
			</h5>
			<h5 class="windowTitle" style="font-size: 13px; font-weight: normal; color:#656565; margin-top: 21px;">
				 수급체의 성격(공동이행, 분담이행) 을 달리하여 제출하는 경우 무효처리
			</h5> 
		</div>
<!-- 		<div class="tableComment m_15"> -->
<!-- 		       <div class="T_btnLayer fr cn n_m ml_15 mt_2"><button type="button" class="grayBtn S" id="P_USE_AGRE">위 내용을 충분히 숙지하였습니다.</button></div> -->
<!-- 	   	</div> -->
    	<div style="text-align: center;">
			<label for="chckOk"><input type="checkbox" id="chckOk">&nbsp;해당 안내를 확인 했습니다.</label>
		</div>
	</fieldset>
    <div class="T_btnLayer fr">
       	<button type="button" class="blueBtn L" id="chckBtn">확인</button>
        <button type="button" class="blueBtn L" id="closeBtn">닫기</button>
    </div>
</div>
<form id="registFrm" method="post">
	<input type="hidden" name="P_ANNC_NO" value="${param.P_ANNC_NO }">
	<input type="hidden" name="P_ANNC_NGR" value="${param.P_ANNC_NGR }">
	<input type="hidden" name="P_ROUND_NO" value="${param.P_ROUND_NO }">
	<input type="hidden" name="P_BID_VEND_PSCD" value="OP13">
</form>