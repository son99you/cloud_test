<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * sg설명 팝업창
 *
 * <pre>
 * vend 
 *    |_ sgPopup.jsp
 * 
 * </pre>
 * @date : 2017. 06. 13
 * @version : 1.0
 * @author : 은우소프트 홍찬일
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %> 
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %> 

<script type="text/javascript" src="${jsPath}/ipro/views/vend/popup/vendEvalReviewPopup.js"></script> 


<div class="pop_wrap">
	<div class="pop_header">
		<h1 class="tit">평가항목표</h1>
	</div> <!--// pop_header E -->

	<div class="pop_container">
		<div class="pop_list_wrap">
			<div class="pop_list_conts">
				<div class="tit_area">
				<!-- <p class="spc_stit"></p> -->
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
							<th class="txtc">점수</th>
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
								<!-- 배점 -->
								<!-- <input type="radio" name="P_ESTM_DTL_ITEM_POINT" value=""> -->
							</td>
						</tr>
					</tbody>
				</table>
				</div>		
			</div>
			<div class="pop_list_bottom">
				<div class="btn_wrap view_btn">
					<button type="button" class="btn btn_02 btn_close" id="closeBtn" onclick="javascript:window.close();">닫기</button>
				</div> <!--// btn_wrap E -->
			</div>			
		</div>
	</div>
</div>