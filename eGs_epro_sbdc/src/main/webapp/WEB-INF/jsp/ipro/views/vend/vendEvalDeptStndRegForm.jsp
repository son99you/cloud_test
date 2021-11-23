<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 유관부서평가기준 추가(신분당선 유관부서평가기준)
 *
 * <pre>
 * vend
 *    |_ vendEvalDeptStndReg.jsp
 * 
 * </pre>
 * @date : 2017. 06. 16
 * @version : 1.0
 * @author : 은우소프트 홍찬일
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %> 
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %> 

<script type="text/javascript" src="${jsPath}/ipro/views/vend/vendEvalDeptStndRegForm.js"></script> 
 
<div class="sp_sec">
	<div class="nav_sec">
		<ul id="breadcrumbs">
			<li class="home">홈</li>
			<li>${myMenuList.bigMenuNm}</li>
			<li>${myMenuSubList.smallMenuNm}</li> 
		</ul>
	</div><!--// nav_sec -->
	
	<h3 class="sp_tit">유관부서평가기준 등록</h3>
	
 	<form id="registFrm" method="POST">
		<div class="sp_cont">
			<p class="spc_stit">기본정보</p>	
			<table class="form_tb">
				<colgroup>												
					<col width="15%">
					<col width="35%">
					<col width="15%">
					<col width="35%">
				</colgroup>
				<tr>
					<th>평가분야코드</th>
					<td>
						<font color="red"><b>자동생성</b></font>
					</td>
					<th>평가분야명</th>
					<td>
						<input type="text"  style="width: 98%;" name="ev_name1">
					</td>
				</tr>
				<tr>
					<th>배점</th>
					<td>
						<input type="text" name="dist_score" numeric>
					</td>
					<th>평가구분</th>
					<td>
						<comTag:comCmcdCdValueRadio name="ev_gubun1" cdId="N00003"  />
					</td>
				</tr>
				<tr>
					<th>SG명</th>
					<td>
						<comTag:comCmcdCdValueComboBox name="dept_sg_code" cdId="N00001" headerValue="전체"/>
					</td>
					<th>사용유무</th>
					<td>
						<comTag:comCmcdCdValueRadio name="use_yn" cdId="N00004"/>
					</td>
				</tr>
				<tr>
					<th>설명</th>
					<td  colspan="3">
						<textarea name="remark1" style="width: 100%; height: 50px; overflow: hidden;"></textarea>
					</td>
				</tr>
			</table>
		
			<p class="spc_stit">평가항목
			  	<button type="button" class="btn btn_02 btn_c2" id="addbtn">추가</button>
			 </p>
		 
			<table class="tb">
				<colgroup>
					<col style="width: 10%;">
					<col style="width: 15%;">
					<col style="width: 10%;">
					<col style="width: auto;">
					<col style="width: 10%;">
				</colgroup>
				<thead>
	                <tr>
	                   	<th class="txtc">순번</th>
						<th class="txtc">평가항목명</th>
						<th class="txtc">배점</th>
						<th class="txtc">설명</th>
						<th class="txtc">삭제</th>
	                </tr>
	            </thead>
				<tbody id="trgetFrame">
					<tr id="copyTrget" style="display: none;">
						<td class="txtc"><font color="red"><b>자동생성</b></font></td>
						<td class="txtc"><input type="text" name="ev_name2"></td>
						<td class="txtc"><input type="text" name="dist_score2"></td>
						<td class="txtc"><textarea style="width: 100%; height: 70px;" name="remark2"></textarea></td>
						<td class="txtc">
							<button class='btn btn_02 btn_sch' id='deleteBtn' onclick='deleteFn(this);' >삭제</button>
						</td>
					</tr>
				</tbody>
				<tbody id="copyArea">
					<tr>
						<td class="txtc"><font color="red"><b>자동생성</b></font></td>
						<td class="txtc"><input type="text" name="ev_name2"></td>
						<td class="txtc"><input type="text" name="dist_score2"></td>
						<td class="txtc"><textarea style="width: 100%; height: 70px;" name="remark2"></textarea></td>
						<td class="txtc">
							<button class='btn btn_02 btn_sch' id='deleteBtn' onclick='deleteFn(this);' >삭제</button>
						</td>
					</tr>
				</tbody>
			</table>
		
			<div class="btn_wrap view_btn">
		  		<button type="button" class="btn ty02 btn_revise" id="saveBtn">저장</button>
		  		<button type="button" class="btn ty04 btn_sch" id="listBtn">취소</button>
	   		</div>	
		</div>
	</div>
</form>

<%-- DETAIL FORM --%>
<form id="detailFrm" method="POST"> 
	<input type="hidden" name="resourceName" value="${ param.resourceName }">
	<input type="hidden" name="ev_code1">
</form>
<form id="listFrm" method="POST"> 
	<input type="hidden" name="resourceName" value="${ param.resourceName }">
</form>