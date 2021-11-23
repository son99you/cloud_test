<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 평가기준 추가(신분당선 평가기준)
 *
 * <pre>
 * vend
 *    |_ vendEvalStndRegForm.jsp
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

<script type="text/javascript" src="${jsPath}/ipro/views/vend/vendEvalStndRegForm2.js"></script> 
 
<div class="sp_sec">
	<div class="nav_sec">
		<ul id="breadcrumbs">
			<li class="home">홈</li>
			<li>${myMenuList.bigMenuNm}</li>
			<li>${myMenuSubList.smallMenuNm}</li> 
		</ul>
	</div><!--// nav_sec -->
	
	<h3 class="sp_tit">평가기준 등록</h3>

  	<form id="registFrm" method="POST"> 
  		<div class="sp_cont">
			<p class="spc_stit">기본정보</p>	
			<table class="form_tb">
				<caption>기본정보</caption>
				<colgroup>
					<col width="15%">
					<col width="35%">
					<col width="15%">
					<col width="35%">
				</colgroup>
				<tr>
					<th>
						평가분야코드
					</th>
					<td>
						<font color="red"><b>자동생성</b></font>
					</td>
					<th>
						평가분야명
					</th>
					<td>
						<input  type="text" id="ev_name1" style="width: 98%;" name="ev_name1"/>
					</td>
				</tr>
				<tr>
					<th>
						사용유무
					</th>
					<td>
						<comTag:comCmcdCdValueRadio name="use_yn" cdId="N00004"/>
					</td>
					<th>
						평가구분
					</th>
					<td>
						 <comTag:comCmcdCdValueRadio name="ev_gubun1" cdId="N00002"  />
					</td>
				</tr>
				<tr>
					<th>
						설명
					</th>
					<td  colspan="3">
						<textarea name="remark1" style="width: 100%; height: 50px; overflow: hidden;"></textarea>
					</td>
				</tr>
			</table>
			</div>
			
			
			
			<div class="tit_area">
				<p class="spc_stit">평가항목</p>
			</div>
			<div id="tableDiv" class="sp_cont">
			<table class="tb">
           		<colgroup>
					<col style="width: 40%;">
					<col style="width: auto;">
					<col style="width: 5%;">
					<col style="width: 10%;">
				</colgroup>
				<thead>
	                <tr>
						<th class="txtc">평가항목명</th>
						<th class="txtc">평가항목내용</th>
						<th class="txtc">배점</th>
						<th class="txtc">삭제</th>
	                </tr>
	            </thead>
				<!-- <tbody id="trgetFrame">
					<tr id="copyTrget" style="display: none;">
						<td class="txtc">
							<button type="button" class="btn btn_02 btn_c2" style="min-width: 0px;" name="regBtn">+</button>
					  		<button type="button" class="btn btn_02 btn_c2" style="min-width: 0px;" name="regChildBtn">>></button>
							<input type="text" name="P_ESTM_DTL_ITEM_NO">
							<input type="text" name="P_ESTM_DTL_ITEM_NM">
						</td>
						<td class="txtc"><input type="text" name="P_ESTM_DTL_ITEM_SCR" numeric></td>
						<td class="txtc"><textarea style="width: 100%; height: 70px;" name="P_ESTM_ITEM_DTL_CNTN"></textarea></td>
						<td class="txtc">
							<button class='btn btn_02 btn_sch' id='deleteBtn' onclick='deleteFn(this);' >삭제</button>
						</td>
					</tr>
				</tbody> -->
				<tbody id="copyArea">
					<tr>
						<td class="txtc">
							<button type="button" class="btn btn_02" style="min-width: 0px;" name="regBtn">+</button>
					  		<button type="button" class="btn btn_02" style="min-width: 0px;" name="regChildBtn">>></button>
							<input type="hidden" name="P_ESTM_DTL_ITEM_NO" value="">
							<input style="width : 150px;" type="text" name="P_ESTM_DTL_ITEM_NM">
						</td>
						<td class="txtc"><textarea style="width: 100%; height: 70px;" name="P_ESTM_ITEM_DTL_CNTN"></textarea></td>
						<td class="txtc"><input type="text" name="P_ESTM_DTL_ITEM_SCR" numeric></td>
						<td class="txtc">
							<!-- <button class='btn btn_02 btn_sch' id='deleteBtn' onclick='deleteFn(this);' >삭제</button> -->
						</td>
					</tr>
				</tbody>
			</table>
			</div>
			
			<div class="btn_wrap view_btn">
				<button type="button" class="btn ty02" id="reViewBtn">미리보기</button>
		  		<button type="button" class="btn ty02" id="saveBtn">저장</button>
		  		<button type="button" class="btn ty04" id="listBtn">취소</button>
	   		</div>
		</div>
	</form>		
</div>

<form id="detailFrm" method="POST"> 
	<input type="hidden" name="resourceName" value="${ param.resourceName }">
	<input type="hidden" name="ev_code1">
</form>

<form id="listFrm" method="POST"> 
	<input type="hidden" name="resourceName" value="${ param.resourceName }">
</form>