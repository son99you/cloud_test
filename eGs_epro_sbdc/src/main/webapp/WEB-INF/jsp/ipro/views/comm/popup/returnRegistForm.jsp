<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">--%>
<%--
 * 공통 > 요구부서 조회(팝업)
 *
 * <pre>
 * comm 
 *  |_popup
 *   |_rqstDeptList.jsp
 * 
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %>

<link rel="stylesheet" type="text/css" href="${cssPath}/ipro/windowPop.css">
<link rel="stylesheet" type="text/css" href="${cssPath}/ipro/template/pagingPlace.css">

<script type="text/javascript" src="${jsPath}/ipro/views/comm/popup/returnRegistForm.js"></script>

<div id="windowPopup" class="w_500">
	<h3 class="windowTitle">반려 팝업</h3>
	<div class="formLayer">
		<form id="registFrm" method="POST" >
	    	<input type="hidden" id="P_PURDMD_NO" name="P_PURDMD_NO" value="${param.P_PURDMD_NO}">
			<input type="hidden" id="P_PURDMD_ODR" name="P_PURDMD_ODR" value="${param.P_PURDMD_ODR}">
			<input type="hidden" id="P_ACMSLT_CRTF_NO" name="P_ACMSLT_CRTF_NO" value="${param.P_ACMSLT_CRTF_NO}">
			<input type="hidden" id="P_RETURN_GBN" name="P_RETURN_GBN" value="${param.P_RETURN_GBN}">
			<input type="hidden" name="P_USER_ID" value="${sessionScope.loginResult.USER_ID}">
			
			<br/><br/> &nbsp;
				<table class="contable2">
					<tr>
						<td> 
							<table class="contable">
								<tr>
									<td>
										<table>
											<colgroup>
												<col style="width: 50px;">
												<col style="width: 200px;">	
											</colgroup>
											  <tbody>
								                <tr>
								                	<th>반려<br>사유</th>
							                    	<td><textarea id="P_RM_CN" name="P_RM_CN" rows="7" cols="52" ></textarea> &nbsp;</td>
								                </tr>
							                </tbody>
										</table>
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>

		    <div class="T_btnLayer fr" style="margin-top: 20px;">
		    	<button type="button" class="blueBtn L"  id="registBtn" >저장</button>
				<button type="button" class="blueBtn L"  id="closeBtn" onclick="window.close();">닫기</button>
		    </div>
	    </form>
	</div> 
</div>
 
<%--page move form --%>
<form id="listFrm" method="POST" >
	<input type="hidden" name="contextPath" value="${contextPath}" >
	<input type="hidden" name="P_EMPl_ID" value="${sessionScope.loginResult.USER_ID}" >
	<input type="hidden" id="resourceName" name="resourceName" value="${param.resourceName}">
	<input type="hidden" id="resourceDesc" name="resourceDesc" value="${param.resourceDesc}">
	<input type="hidden" id="P_PURDMD_NO" name="P_PURDMD_NO" value="${param.P_PURDMD_NO}"> 
 	<input type="hidden" id="P_PURDMD_ODR" name="P_PURDMD_ODR" value="${param.P_PURDMD_ODR}">
</form>   