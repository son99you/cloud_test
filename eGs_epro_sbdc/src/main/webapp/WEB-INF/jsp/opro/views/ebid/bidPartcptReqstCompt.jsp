<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 입찰공고 > 입찰공고상세 > 입찰참가신청 완료
 *
 * <pre>
 *elbi 
 *    |_ oepElbiBidPartcptReqstCompt.jsp
 * 
 
 * </pre>
 * @date : 2015. 04. 10. 오후 15:56:25
 * @version : 1.0
 * @author : 은우소프트 하성윤
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %>

<script type="text/javascript" src="${jsPath}/opro/views/ebid/bidPartcptReqstCompt.js"></script>  

<div class="contents">
	<input type="hidden" id="copertnSpldmdDutySeCd" value="${inProgrsBidPblancDetail.COPERTN_SPLDMD_DUTY_SE_CD}">
	<input type="hidden" id="registAt" name="registAt" value="${registAt }">
	<input type="hidden" id="P_COPERTN_SPLDMD_DUTY_SE_CD" name="P_COPERTN_SPLDMD_DUTY_SE_CD" value="${P_COPERTN_SPLDMD_DUTY_SE_CD }">
	
	<div id="panelSubContent">

        <h4 class="bulSubTitle">작업완료</h4>
        <div class="blueBox">
	        <p class="blueBoxTitle">입찰참가신청서 제출이 완료되었습니다.</p>
	         <!--
	             ul list style 두가지입니다.
	             class name : decimal
	             class name : disc
	         -->
	        <ul class="decimal">
	            <li>당 입찰건에 대해 공동 입찰 참여 의사에 의해 공동수급협정서를 작성해야 합니다.</li>
	            <li>지금 공동수급협정서를 작성 하시려면 공동수급협정서작성 버튼을 선택하시기 바랍니다.</li>
	            <li>입찰서 제출전에 공동수급협정서를 제출하셔야만 입찰서를 제출할 수 있습니다.</li>
	    	</ul>
	    </div>

        <div class="T_btnLayer fr" >
            <button type="button" class="blueBtn L" id="writngBtn" >공동수급협정서작성</button>
            <button type="button" class="blueBtn L" id="canclBtn">지금작성안함</button>
        </div>
    </div>
    
   	<form id="writngFrm" method="POST" action="">
   		<input type="hidden" id="resourceName" name="resourceName" value="${param.resourceName}">
		<input type="hidden" name="P_PBLANC_NO" value="${param.P_PBLANC_NO}">
		<input type="hidden" name="P_PBLANC_ODR" value="${param.P_PBLANC_ODR}">
		<input type="hidden" name="P_ENTRPS_REGIST_NO" value="${sessionScope.loginResult.LOGIN_ID}">
	</form>
	<form id="popupFrm" method="post" action="">
	</form>
</div>