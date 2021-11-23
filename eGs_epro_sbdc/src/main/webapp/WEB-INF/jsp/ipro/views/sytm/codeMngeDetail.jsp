<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 시스템관리 > 코드관리 > 상세
 *
 * <pre>
 * cont 
 *    |_ codeMngeDetail.jsp
 * 
 * </pre>
 * @date : 2017. 06. 14
 * @version : 1.0
 * @author : 은우소프트 은잔디
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %> 
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %> 

<script type="text/javascript" src="${jsPath}/ipro/views/sytm/codeMngeDetail.js"></script>

<div class="conts_wrap">
	<div class="inner">
		<div class="tit_wrap">
			<h3 class="tit">코드관리 상세</h3>
			<ul class="step_wrap">
				<li><a href="#">${myMenuList.bigMenuNm}</a></li>
				<li><a href="#">${myMenuSubList.smallMenuNm}</a></li>
			</ul>			
		</div>
		<form>
			<fieldset>
				<div class="view_wrap typeB">
					<h4 class="tit">코드관리</h4>
					<div class="view_area">
						<table id="entrpsTb" class="typeB_list_table">
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
				           				<input type="checkbox" checked="checked" id="P_DELETE_NO" name="P_DELETE_NO" onclick="checkValSet(this);" disabled="disabled">
				           				<input type="hidden" name="P_ROW_CHECK" value="Y" disabled="disabled">
				           			</td>
									<td><input type="text" id="P_CD_ID" name="P_CD_ID" value="" disabled="disabled" style="text-align: center;"></td>
									<td><input type="text" id="P_CD_VALUE" name="P_CD_VALUE" value="" disabled="disabled" style="text-align: center;"></td>
									<td><input type="text" id="P_CD_VALUE_NM" name="P_CD_VALUE_NM" value="" disabled="disabled"></td>
									<td><input type="text" id="P_EXTRA1_VALUE" name="P_EXTRA1_VALUE" value="" style="text-align: center;"></td>
									<td><input type="text" id="P_EXTRA3_VALUE" name="P_EXTRA3_VALUE" value="" style="text-align: center;"></td>
									<td>
										<div class="selectLayer2 w_120">
											<select id="P_USE_AT" name="P_USE_AT" disabled="disabled">
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
										<input type="hidden" name="P_ROW_CHECK" value="N"></td>
									<td>
										C00001
										<input type="hidden" id="P_CD_ID1" name="P_CD_ID" value="C00001">
									</td>
									<td>
										0
										<input type="hidden" id="P_CD_VALUE" name="P_CD_VALUE" value="0">
									</td>
									<td><input type="text" id="P_CD_VALUE_NM" name="P_CD_VALUE_NM" value="구매"></td>
									<td><input type="text" id="P_EXTRA1_VALUE" name="P_EXTRA1_VALUE" value="" style="text-align: center;"></td>
									<td><input type="text" id="P_EXTRA3_VALUE" name="P_EXTRA3_VALUE" value="" style="text-align: center;"></td>
									<td>
										<div class="selectLayer2 w_120">
											<select id="P_USE_AT" name="P_USE_AT">
												<option value="Y" selected>예</option>
												<option value="N" >아니오</option>
											</select>
										</div>
									</td>
								</tr>
							
								<tr style="text-align: center;">
									<td>
										<input type="checkbox" id="P_DELETE_NO" name="P_DELETE_NO" onclick="checkValSet(this);" value="1">
										<input type="hidden" name="P_ROW_CHECK" value="N">
									</td>
									<td>
										C00001
										<input type="hidden" id="P_CD_ID2" name="P_CD_ID" value="C00001">
									</td>
									<td>
										1
										<input type="hidden" id="P_CD_VALUE" name="P_CD_VALUE" value="1">
									</td>
									<td><input type="text" id="P_CD_VALUE_NM" name="P_CD_VALUE_NM" value="용역"></td>
									<td><input type="text" id="P_EXTRA1_VALUE" name="P_EXTRA1_VALUE" value="1" style="text-align: center;"></td>
									<td><input type="text" id="P_EXTRA3_VALUE" name="P_EXTRA3_VALUE" value="" style="text-align: center;"></td>
									<td>
										<div class="selectLayer2 w_120">
											<select id="P_USE_AT" name="P_USE_AT">
												<option value="Y" selected>예</option>
												<option value="N" >아니오</option>
											</select>
										</div>
									</td>
								</tr>
							
								<tr style="text-align: center;">
									<td>
										<input type="checkbox" id="P_DELETE_NO" name="P_DELETE_NO" onclick="checkValSet(this);" value="2">
										<input type="hidden" name="P_ROW_CHECK" value="N">
									</td>
									<td>
										C00001
										<input type="hidden" id="P_CD_ID3" name="P_CD_ID" value="C00001">
									</td>
									<td>
										2
										<input type="hidden" id="P_CD_VALUE" name="P_CD_VALUE" value="2">
									</td>
									<td><input type="text" id="P_CD_VALUE_NM" name="P_CD_VALUE_NM" value="공사"></td>
									<td><input type="text" id="P_EXTRA1_VALUE" name="P_EXTRA1_VALUE" value="1" style="text-align: center;"></td>
									<td><input type="text" id="P_EXTRA3_VALUE" name="P_EXTRA3_VALUE" value="" style="text-align: center;"></td>
									<td>
										<div class="selectLayer2 w_120">
											<select id="P_USE_AT" name="P_USE_AT">
												<option value="Y" selected>예</option>
												<option value="N" >아니오</option>
											</select>
										</div>
									</td>
								</tr>
							
								<tr style="text-align: center;">
									<td>
										<input type="checkbox" id="P_DELETE_NO" name="P_DELETE_NO" onclick="checkValSet(this);" value="3">
										<input type="hidden" name="P_ROW_CHECK" value="N">
									</td>
									<td>
										C00001
										<input type="hidden" id="P_CD_ID4" name="P_CD_ID" value="C00001">
									</td>
									<td>
										3
										<input type="hidden" id="P_CD_VALUE" name="P_CD_VALUE" value="3">
									</td>
									<td><input type="text" id="P_CD_VALUE_NM" name="P_CD_VALUE_NM" value="외자"></td>
									<td><input type="text" id="P_EXTRA1_VALUE" name="P_EXTRA1_VALUE" value="" style="text-align: center;"></td>
									<td><input type="text" id="P_EXTRA3_VALUE" name="P_EXTRA3_VALUE" value="" style="text-align: center;"></td>
									<td>
										<div class="selectLayer2 w_120">
											<select id="P_USE_AT" name="P_USE_AT">
												<option value="Y" selected>예</option>
												<option value="N" >아니오</option>
											</select>
										</div>
									</td>
								</tr>
							</tbody>
					    </table>					
					</div>
				</div>
				
				<div class="btn_wrap view_btn">
		    		<button type="button" class="btn btn_02 btn_revise" id="plusBtn" >추가</button>
		    		<button type="button" class="btn btn_02 btn_sch" id="deleteBtn" >삭제</button>
		    		<button type="button" class="btn btn_02 btn_sch" id="listBtn" >목록</button>					
				</div>
								
			</fieldset>
		</form>
	</div>
</div>
	
<form id="listFrm" method="POST" >
	<input type="hidden" id="P_CD_ID" name="P_CD_ID" value="">
	<input type="hidden" id="resourceName" name="resourceName" value="${param.resourceName}">
	<input type="hidden" id="resourceDesc" name="resourceDesc" value="${param.resourceDesc}">
</form>