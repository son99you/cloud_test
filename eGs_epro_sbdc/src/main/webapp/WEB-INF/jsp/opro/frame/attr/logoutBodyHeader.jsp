<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<script type="text/javascript" src="${jsPath}/logoutBodyHeader.js"></script> 
<script type="text/javascript" src="${jsPath}/serverTime.js"></script>

<input type="hidden" id="sevrTime" value="${serverTime}">

        <!--sta 메뉴-->
        <div class="staWrap">
            <div class="sta">
                <div class="staLeftBtns">
                	<a href="${contextPath }/masc/mainPageForm.do" title="home" class="home on">Home</a>
                </div>
            </div>
        </div>
        <div class="gnbWrap">
            <div class="gnb">
            </div>
        </div>
        
<%-- DEFAULT FORM --%>
<form id="defaultFrm" method="POST" action="${contextPath}/iep">

</form>        

<form id="logOutFrm" method="post" action="${contextPath }">

</form>

<form id="menuLeftMoveFrm" method="post" action="${contextPath}/iep">
	<input type="hidden" name="contextPath" value="${contextPath}" >
	<input type="hidden" name="resourceName" >
</form>

<form id="inqireFrm" method="post" action="${contextPath}/iep">
	<input type="hidden" name="contextPath" value="${contextPath}" >
	<input type="hidden" name="P_USER_ID" value="${loginResult.USER_ID }" >
</form>
