<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 기준정보 > 서식관리 등록
 *
 * <pre>
 * sytm 
 *    |_ contFormRegForm.jsp
 * 
 * </pre>
 * @date : 2017. 06. 19
 * @version : 1.0
 * @author : 은우소프트 은잔디저장
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld"%>
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld"%>

<script type="text/javascript" src="${jsPath}/opro/views/user/bakFilePage.js"></script>

<div class="sp_sec">
	<div class="nav_sec">
		<ul id="breadcrumbs"> 
			<li class="home">홈</li>
			<li>${myMenuList.bigMenuNm}</li>
			<li>${myMenuSubList.smallMenuNm}</li> 
		</ul>
	</div><!--// nav_sec -->
	<h3 class="sp_tit"></h3> 

	<form id="registFrm" method="POST" enctype="multipart/form-data">
		<input type="hidden" name="P_REGR_NM" value="${sessionScope.loginResult.USR_NM}">
		<input type="hidden" name="P_USR_ID" value="${sessionScope.loginResult.USR_ID}" >
		<input type="hidden" name="resourceName" value="OEP13001" >
		<div class="sp_cont">
			<p class="spc_stit"></p>
			<table class="form_tb"> 
				<colgroup>
					<col width="20%">
					<col width="*">
				</colgroup> 
				<tbody>
					<th>파일</th>
					<td class="vtop"> 
						<input type="file" name="P_FILE" style="width: 100%">
                    </td>	
				</tbody>
			</table>
				
			<div class="btm_btns">
				<button type="button" class="btn ty02" id="saveBtn">저장</button>
			</div>
		</div> 
	</form>
	
</div>


<form id="listFrm" method="POST">
	<input type="hidden" name="resourceName" value="OEP13001"> 
</form>

