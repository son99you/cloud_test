<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 공통 > 비밀번호 변경 (팝업)
 *
 * <pre>
 * comm 
 *  |_popup
 *   |_userPwdChangeView.jsp
 * 
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %>

<link rel="stylesheet" type="text/css" href="${cssPath}/ipro/windowPop.css">
<link rel="stylesheet" type="text/css" href="${cssPath}/ipro/template/pagingPlace.css">

<script type="text/javascript" src="/statics/fwk/CmmnUtil.js"></script>
<script type="text/javascript" src="${jsPath}/ipro/views/comm/popup/joinUserIdCheckView.js"></script>

<div id="windowPopup" class="w_500">
	<h3 class="windowTitle">아이디 중복확인</h3>
	<div class="formLayer">
		<form id="searchFrm" name="searchFrm" class="search_form" method="POST" >
			<input type="hidden" id="resourceName" name="resourceName" value="${param.resourceName}">
			<input type="hidden" id="resourceDesc" name="resourceDesc" value="${param.resourceDesc}">
			<%-- <input type="hidden" id="P_EMPL_NO" name="P_EMPL_NO" value="${param.P_EMPL_NO}"> --%>
			
			<table class="contable2">
				<tr>
					<td>
						<table class="contable">
							<tr>
								<td>
									<table>
										<colgroup>
											<col width="30%" align="left">
											<col width="70%" align="left">
										</colgroup>
										<tr height="29px">
											<td> 아이디</td>
											
												<input type="text" id="P_USER_ID" name="P_USER_ID" size="20" maxlength="100" onkeyup="unformatId(this)" >
												<br><font color="#5853EB">3~10자의 영문 대소문자와 숫자, 빼기(-), 밑줄(_)만 사용</font>
											</td>
										</tr>
									</table>
								</td>
							</tr> 
						</table>
					</td>
				</tr>
			</table>
           
		</form>
		
	    <div class="T_btnLayer fr">
			<button type="button" class="blueBtn L" id="searchBtn">조회</button>
			<!-- <button type="button" class="blueBtn L" id="closeBtn" onclick="window.close();">닫기</button> -->
	    </div>
	</div> 
	
	<c:if test="${ not empty userId }">
		<div id="popPageData"> 
			<table class="contable2">
				<tr>
					<td align="center">
						<c:if test="${ useYn eq 'Y' }">
							<b><c:out value="${ userId }"></c:out></b>는 사용이 가능합니다. 
						</c:if>
						<c:if test="${ useYn ne 'Y' }">
							<b><c:out value="${ userId }"></c:out></b>는 이미 사용중입니다.
						</c:if>
					</td>
				</tr> 
			</table>
		</div>
	</c:if>
	
	<div id="popPageAction">
		<table class="contable2">
			<tr>
				<td align="center">
					<c:if test="${ useYn eq 'Y' }">
					<button type="button" class="grayBtn L" style="cursor:pointer;" onclick="fnSetData('<c:out value="${ userId }"/>')">확인</button>
					</c:if>
					<c:if test="${ useYn ne 'Y' }">
					<button type="button" class="grayBtn L" style="cursor:pointer;" onclick="parent.window.close()">취소</button>
					</c:if>
				</td>
			</tr>
		</table>
	</div>
</div>

<%-- LIST FORM --%>
<form id="listFrm" method="POST" >  
	<input type="hidden" name="resourceName" value="${ param.resourceName }">
	<input type="hidden" name="resourceDesc" value="${param.resourceDesc}">
</form>