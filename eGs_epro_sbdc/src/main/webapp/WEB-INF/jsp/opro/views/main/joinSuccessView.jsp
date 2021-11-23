<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 메인화면 > 회원가입 폼
 *
 * <pre>
 * masc 
 *    |_ oepMascMberSbscrbForm.jsp
 * 
 * </pre>
 * @date : 2015. 06. 08. 오전 10:10:32
 * @version : 1.0
 * @author : 은우소프트 전상훈
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %> 
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %> 

<%-- <script src="${kicaPath}/script/sgj_scripts.js" type="text/javascript"></script>
<script src="${kicaPath}/script/sgj_util.js" type="text/javascript"></script>
<script src="${kicaPath}/script/sgj_object.js" type="text/javascript"></script> --%>

<link rel="stylesheet" type="text/css" href="${kicaPath}/WebUI/css/base.css" />
<script type="text/javascript" src="${kicaPath}/WebUI/js/pub.ui.js"></script>
<script type="text/javascript" src="${kicaPath}/KICA/config/config.js"></script>
<script type="text/javascript" src="${kicaPath}/extension/secukitnx.js"></script>

<script type="text/javascript" src="${jsPath}/opro/views/main/joinSuccessView.js"></script>  

<div id="content">
	<form id="registFrm" name="registFrm" method="POST" enctype="multipart/form-data">
		<div id="panelSubContent">
			 <div class="panelContentHead">
				<h3 class="pageTitle">회원가입완료</h3>
			</div>
			
		       <div class="tableLayer">
		           <table class="table">
		           		<tr><td style="text-align: center;">
			           		<b><c:out value="${P_LOGIN_ID}"></c:out>사용자 등록이 완료되었습니다.</b>  
							<br>로그인 화면으로 이동하여 로그인하시기 바랍니다.  
							<br>
							<div id="divBack" style="display: inline;"><center>&gt;&gt; <a href="/opro/main/loginPage.do"><b>HOME</b></a> &lt;&lt;</center></div>
						
		           		</td></tr>
		           		
		           </table>
		       </div>
		</div>
	</form>
</div>


<%-- DETAIL FORM --%>
<form id="detailFrm" method="POST" > 
	<input type="hidden" name="resourceName" value="${ param.resourceName }" >
</form>
<form id="popupFrm" method="POST" > 
	<input type="hidden" name="resourceName" value="${ param.resourceName }" >
	<input type="hidden" name="resourceDesc" value="${param.resourceDesc}">
</form>

<form id="itemListPopupFrm" method="POST">
	<input type="hidden" name="resourceName" value="${param.resourceName}">
	<input type="hidden" name="resourceDesc" value="${param.resourceDesc}">
</form>

<form id="zipPopupFrm" method="POST">
	<input type="hidden" name="resourceName" value="${param.resourceName}">
	<input type="hidden" name="resourceDesc" value="${param.resourceDesc}">
</form>

