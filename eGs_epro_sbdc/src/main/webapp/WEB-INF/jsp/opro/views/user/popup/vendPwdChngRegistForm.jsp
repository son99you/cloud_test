<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">--%>
<%--
 * 비밀번호변경(팝업)
 *
 * <pre>
 * 
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %>

<script type="text/javascript" src="${jsPath}/opro/views/user/popup/vendPwdChngRegistForm.js"></script>

<div class="pop_wrap">
	<div class="pop_header">
		<h1 class="tit">비밀번호 변경</h1>
	</div> <!--// pop_header E -->
	<div class="pop_container">
		<div class="view_wrap typeC">
			<div class="view_area m0 typeB">
				<form id="registFrm" class="registFrm" method="POST" >
					<input type="hidden" name="P_VEND_REG_NO" value="${param.P_VEND_REG_NO}">
					<table class="table">
		                <colgroup>
		                   	<col width="30%" align="left">
							<col width="70%" align="left">
		                </colgroup>
		                <tbody>
							<!-- <tr class="line">
								<th>현재 비밀번호</th>
								<td><input type="password" id="P_NOW_PWD" name="P_NOW_PWD" size="20" maxlength="100"></td>
							</tr> -->
							<tr>
								<th class="bullet_orange"> 변경 비밀번호</th>
								<td><input type="password" id="P_CHG_PWD" name="P_CHG_PWD" size="20" maxlength="100"></td>
							</tr>
							
							<tr>
								<th class="bullet_orange"> 변경 비밀번호 확인</th>
								<td><input type="password" id="P_CHG_PWD_CFRM" name="P_CHG_PWD_CFRM" size="20" maxlength="100"></td>
							</tr>
		                </tbody>
		            </table>    
				</form>
			</div>
			
			<div class="btn_wrap view_btn">
				<button type="button" class="btn btn_m btn_orange" id="chngBtn">변경</button>
				<button type="button" class="btn btn_m btn_del" id="closeBtn">닫기</button>
			</div> <!--// btn_wrap E -->
			
		</div>
	</div> 
</div>