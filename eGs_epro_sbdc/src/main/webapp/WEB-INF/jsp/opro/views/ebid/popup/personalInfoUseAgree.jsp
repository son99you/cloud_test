<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 전자입찰 >개인정보활용동의서(팝업)
 *
 * <pre>
 * elbi
 	  |_	popup 
 *    			|_ oepElbiPopupPersonalInfoUseAgree.jsp
 * 
 * </pre>
 * @date : 2016. 03. 15. 오후 08:12:51
 * @version : 1.0
 * @author : 은우소프트 전상훈
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %>

<script type="text/javascript" src="${jsPath}/opro/views/ebid/popup/personalInfoUseAgree.js"></script>

<div id="windowPopup">
	<h3 class="windowTitle">개인정보활용동의서</h3>
    
	<fieldset class="useAgree">
		<legend class="blind">개인정보활용동의약관</legend>
		<div class="box" tabindex="0"  style="border: 1px solid #d9d9d9;">
			<h4 style="font-family: NanumGothic, NanumGothic, Dotum, 돋움, Arial, sans-serif; font-size: 15px; color: #555; line-height: 1.5em; font-weight: normal;">
				개인정보보호법 제15조(개인정보의 수집·이용)에 따라 귀하의 개인 정보를 다음과 같이 수집 및 이용하고자 합니다.
			</h4>
			<h5 style="font-size: 15px; font-weight: normal; color:#656565; margin-top: 21px;">
				1. 개인정보의 수집 및 이용 목적
			</h5>
			<p style="font-size: 15px;">개인정보의 수집은 입찰 업무 진행을 위한 목적으로 수집하며 이외의 목적으로 사용되지 않습니다.</p>
			<h5 style="font-size: 15px; font-weight: normal; color:#656565; margin-top: 21px;">
				2. 개인정보 수집 항목
			</h5> 
			<p style="font-size: 15px;">&nbsp;&nbsp;&nbsp;- 개인식별정보 : 성명, (휴대)전화번호, E-mail</p>
		</div>
		
		<p class="agreeChek" style="font-size: 14px;">
			<input type="checkbox" id="P_USE_AGRE" name="P_USE_AGRE">
			<label for="P_USE_AGRE" >'개인정보활용 동의약관'을 읽어 보았으며 이에 동의합니다.</label>
		</p>
		
	</fieldset>
        
        
    <div class="T_btnLayer fr">
        <button type="button" class="blueBtn L" id="closeBtn">닫기</button>
    </div>
    
</div>
