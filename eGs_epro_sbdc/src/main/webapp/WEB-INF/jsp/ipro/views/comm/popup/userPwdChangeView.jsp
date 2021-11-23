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

<script type="text/javascript" src="/statics/fwk/CmmnUtil.js"></script>
<script type="text/javascript" src="${jsPath}/ipro/views/comm/popup/userPwdChangeView.js"></script>

<div class="pop_wrap">
	<div class="pop_header">
		<h1 class="tit">비밀번호 변경</h1>
	</div> <!--// pop_header E -->

	<div class="pop_container">
		<div class="view_wrap typeC">
			<div class="view_area m0 typeB">
				<form id="searchFrm" name="searchFrm" class="search_form" method="POST" action="${contextPath}/comm/popup/userPwdChange.do">
				
					<input type="hidden" id="resourceName" name="resourceName" value="${param.resourceName}">
					<input type="hidden" id="resourceDesc" name="resourceDesc" value="${param.resourceDesc}">
					<input type="hidden" id="P_EMPL_NO" name="P_EMPL_NO" value="${param.P_EMPL_NO}">
						
					<table>
						<colgroup>
							<col width="30%" align="left">
							<col width="70%" align="left">
						</colgroup>
						<tr height="29px">
							<th> 현재 비밀번호</th>
							<td>
								<input type="password" id="P_NOW_PWD" name="P_NOW_PWD" size="20" maxlength="100">
							</td>
						</tr>
						<tr height="29px">
							<th> 변경 비밀번호</th>
							<td>
								<input type="password" id="P_CHG_PWD" name="P_CHG_PWD" size="20" maxlength="100">
							</td>
						</tr>
						
						<tr height="29px">
							<th> 변경 비밀번호확인</th>
							<td>
								<input type="password" id="P_CHG_PWD_CFRM" name="P_CHG_PWD_CFRM" size="20" maxlength="100">
							</td>
						</tr>
					</table>
				</form>	
			</div> <!--// view_area E -->
		</div> <!--// view_wrap E -->
		
		<div class="pop_list_wrap">
			<div class="pop_list_bottom">

				<div class="btn_wrap view_btn">
					<button type="button" class="btn btn_02 btn_close" id="saveBtn">저장</button>
					<button type="button" class="btn btn_02 btn_close" id="closeBtn" onclick="window.close();">닫기</button>
				</div> <!--// btn_wrap E -->
			</div>			
		</div>
	</div>
</div>