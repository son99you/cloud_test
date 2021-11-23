<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<input type="hidden" id="errMsg" value="${ex}">
<input type="hidden" id="errMsgSub" value="${exMsg}">

<div class="contents-box" onclick="javascript:history.back();" style="cursor: pointer;">
	<div class="title">시스템오류</div>
	<img src="${imagePath}/comm/error.png">
	
	<p>이용에 불편을 드려서 죄송합니다.<br>시스템 담당자에게 문의 바랍니다. (02-841-0000)</p>
	<ul>
		<li>로그인 후, 4시간 이상 경과되어 재 로그인이 필요한 경우</li>
		<li>이용폭주 및 인터넷 상태불안으로 서비스가 지연되는 경우</li>
		<li>해당 페이지에 대한 접근이 허가되지 않는 경우 (비정상 접근 등)</li>
	</ul>
</div>

<script type="text/javascript">
	alert("에러가 발생하였습니다. 관리자에게 문의하십시오.");
</script>