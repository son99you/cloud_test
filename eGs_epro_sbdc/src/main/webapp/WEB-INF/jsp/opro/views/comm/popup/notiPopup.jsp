<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 공통 > 안내 팝업
 *
 * <pre>
 * comm 
 *    |_ popup
               |_ notiPopup.jsp
 * 
 * </pre>
 * @date : 2018. 12. 31
 * @version : 1.0
 * @author : 은우소프트 전상훈
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %> 
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %> 

<script type="text/javascript" src="${jsPath}/opro/views/comm/popup/notiPopup.js"></script> 
 
<div id="windowPopup" style="width: 500px;">
	<div class="tableLayer">
		<img alt="입찰안내이미지" src="${imagePath}/opro/main/notiPopup.png">
	</div>
	<div class="T_btnLayer fr top10">
		<label for="oneDay" style="float: left;"><input type="checkbox" id="oneDay">하루동안 안보기</label>
    	<button type="button" class="blueBtn L pointer" name="closeBtn">닫기</button>
    </div>
</div> <!--// content E-->

<form id="downFrm" method="POST" >
	<input type="hidden" name="P_ATCHMNFL_SN">
</form>
