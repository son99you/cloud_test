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

<script type="text/javascript" src="${jsPath}/ipro/views/vend/vendEvalStndRegForm4.js"></script> 
 
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
			<div class="tit_area">
				<p class="spc_stit">평가항목</p>
			</div>
			<div id="tableDiv" class="sp_cont">
			<table class="tb">
           		<colgroup>
					<col style="width: auto;">
				</colgroup>
				<thead>
	                <tr>
						<th class="txtc">평가항목</th>
	                </tr>
	            </thead>
				<tbody id="copyArea">
					<tr>
						<td class="txt">
							<input type="hidden" name="P_ESTM_DTL_ITEM_NO" value="">
							<input style="width : 70%;" type="text" placeholder="평가항목명" name="P_ESTM_DTL_ITEM_NM">
							<textarea style="width: 90%; height: 70px;" placeholder="평가항목내용" name="P_ESTM_ITEM_DTL_CNTN"></textarea>
							<input type="text" style="width : 10%;" name="P_ESTM_DTL_ITEM_SCR" placeholder="배점" numeric>
							<!-- <div class="rad_g">
								<div class="radio_box">
									<input type="radio" name="P_ESTM_DTL_ITEM_POINT" id="id1" value="1" checked="">
									<label for="id1" class="vam mr5">1</label>
								</div>
								<div class="radio_box">
									<input type="radio" name="P_ESTM_DTL_ITEM_POINT" id="id2" value="2" checked="">
									<label for="id2" class="vam mr5">2</label>
								</div>
							</div> -->
							&nbsp;&nbsp;
							<select style="width : 80px;" name="P_ESTM_DTL_ITEM_SCR_SECD"><option value="A">합산</option><option value="B">최고점</option></select>
							&nbsp;&nbsp;
							<button type="button" class="btn btn_02" style="min-width: 0px;" name="regBtn">+</button>
					  		<button type="button" class="btn btn_02" style="min-width: 0px;" name="regChildBtn">>></button>
					  		
					  		<!-- <div class="rad_g">
								<div class="radio_box">
									<input type="radio" name="P_ESTM_DTL_ITEM_POINT1" id="id3" value="1" checked="">
									<label for="id3" class="vam mr5">1</label>
								</div>
								<div class="radio_box">
									<input type="radio" name="P_ESTM_DTL_ITEM_POINT1" id="id4" value="2" checked="">
									<label for="id4" class="vam mr5">2</label>
								</div>
							</div> -->
						</td>
					</tr>
				</tbody>
			</table>
			</div>
			
			<div class="btn_wrap view_btn">
				<button type="button" class="btn ty02" id="dataBtn">데이터확인</button>
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

<form id="popupFrm" method="POST" > 
	<input type="hidden" name="resourceName" value="${ param.resourceName }" >
</form>