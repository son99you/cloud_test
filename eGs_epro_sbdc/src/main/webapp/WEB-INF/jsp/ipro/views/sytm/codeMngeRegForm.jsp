<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 시스템관리 > 코드관리 > 작성
 *
 * <pre>
 * cont 
 *    |_ codeMngeRegForm.jsp
 * 
 * </pre>
 * @date : 2017. 07. 03
 * @version : 1.0
 * @author : 은우소프트 은잔디
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %> 
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %> 

<link rel="stylesheet" type="text/css" href="${cssPath}/ipro/bodyBasic.css">
<link rel="stylesheet" type="text/css" href="${cssPath}/ipro/template/buttonStyle.css">
<link rel="stylesheet" type="text/css" href="${cssPath}/ipro/template/pagingPlace.css">
<link rel="stylesheet" type="text/css" href="${cssPath}/ipro/template/calendar.css">

<script type="text/javascript" src="${jsPath}/ipro/views/sytm/codeMngeRegForm.js"></script>

<div class="content">
	<form id="registFrm" method="POST">
	<input type="hidden" id="P_DELETE_CODE" name="P_DELETE_CODE" value="">
		<h3>코드관리 작성</h3>
		
		<div class="conts_wrap">
			<div class="btn_wrap view_btn fr">
		    	<button type="button" class="btn btn_02 btn_revise" id="plusBtn" >추가</button>
		    	<button type="button" class="btn btn_02 btn_revise" id="deleteBtn" >삭제</button>
		    	<button type="button" class="btn btn_02 btn_revise" id="saveBtn" >저장</button>
		    	<button type="button" class="btn btn_02 btn_revise" id="cancelBtn" >취소</button>
		    </div>
			
			<div class="contentTitle" style="">코드관리</div>
			
	       	<div id="codeList">
				<table id="entrpsTb">
			    	<colgroup>
			    		<col width="5%"/>
			    		<col width="16%"/>
						<col width="15%"/>
						<col width="15%"/>
						<col width="15%"/>
						<col width="15%"/>
						<col width="15%"/>
					</colgroup>
			        <thead>
		            	<tr class="line">
		            		<th style="text-align: center;">
		            			<input type="checkbox" id="allCheck" onclick="checkValSet(this);">
		            		</th>
		                    <th style="text-align: center;">코드</th>
		                    <th style="text-align: center;">코드명</th>
		                    <th style="text-align: center;">코드설명</th>
		                    <th style="text-align: center;">여분1</th>
		                    <th style="text-align: center;">여분3</th>
		                    <th style="text-align: center;">사용여부</th>
		            	</tr>
		            </thead>
		            
		            <tbody id="addRow">
		            	<tr style="display: none; text-align: center;">
		           			<td>
		           				<input type="checkbox" id="P_DELETE_NO" name="P_DELETE_NO" onclick="checkValSet(this);" disabled="disabled">
		           				<input type="hidden" name="P_ROW_CHECK" value="Y" disabled="disabled">
		           			</td>
							<td><input type="text" id="P_CD_ID" name="P_CD_ID" style="text-align: center;"></td>
							<td><input type="text" id="P_CD_VALUE" name="P_CD_VALUE" disabled="disabled" style="text-align: center;"></td>
							<td><input type="text" id="P_CD_VALUE_NM" name="P_CD_VALUE_NM" disabled="disabled"></td>
							<td><input type="text" id="P_EXTRA1_VALUE" name="P_EXTRA1_VALUE" style="text-align: center;"></td>
							<td><input type="text" id="P_EXTRA3_VALUE" name="P_EXTRA3_VALUE" style="text-align: center;"></td>
							<td>
								<div class="selectLayer2 w_120">
									<select id="P_USE_AT" name="P_USE_AT" disabled="disabled">
										<option value="">선택</option>
										<option value="Y">예</option>
										<option value="N">아니오</option>
									</select>
								</div>
							</td>
		           		</tr>
		            </tbody>
		            
		            <tbody id="contentRow">                          
						<tr style="text-align: center;">
							<td>
								<input type="checkbox" id="P_DELETE_NO" name="P_DELETE_NO" onclick="checkValSet(this);" value="0">
								<input type="hidden" name="P_ROW_CHECK" value="Y"></td>
							<td>
								<input type="text" id="P_CD_ID1" name="P_CD_ID" value="" style="text-align: center;">
							</td>
							<td>
								<input type="text" id="P_CD_VALUE" name="P_CD_VALUE" value="" style="text-align: center;">
							</td>
							<td><input type="text" id="P_CD_VALUE_NM" name="P_CD_VALUE_NM" value=""></td>
							<td><input type="text" id="P_EXTRA1_VALUE" name="P_EXTRA1_VALUE" value="" style="text-align: center;"></td>
							<td><input type="text" id="P_EXTRA3_VALUE" name="P_EXTRA3_VALUE" value="" style="text-align: center;"></td>
							<td>
								<div class="selectLayer2 w_120">
									<select id="P_USE_AT" name="P_USE_AT">
										<option value="">선택</option>
										<option value="Y">예</option>
										<option value="N" >아니오</option>
									</select>
								</div>
							</td>
						</tr>
					</tbody>
			    </table>
	       	</div>
	       	
		</div>
	</form>
	
	<form id="listFrm" method="POST" >
		<input type="hidden" id="P_CD_ID" name="P_CD_ID" value="">
		<input type="hidden" id="resourceName" name="resourceName" value="${param.resourceName}">
		<input type="hidden" id="resourceDesc" name="resourceDesc" value="${param.resourceDesc}">
	</form>

</div> <!--// content E-->
