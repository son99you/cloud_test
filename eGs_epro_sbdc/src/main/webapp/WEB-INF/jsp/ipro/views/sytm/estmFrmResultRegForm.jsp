<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 기준정보 > 서식관리결과작성
 *
 * <pre>
 * cont 
 *    |_ contFormDetail.jsp
 * 
 * </pre>
 * @date : 2018. 08. 22
 * @version : 1.0
 * @author : 은우소프트 은잔디
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld"%>
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld"%>

<script type="text/javascript" src="${jsPath}/ipro/views/sytm/estmFrmResultRegForm.js"></script>
<script type="text/javascript" src="/raonkeditor/js/raonkeditor.js"></script>

<!-- Detail -->
			<div class="area-detail">
				<div class="table-detail">
					<!-- Top -->
					<div class="top-detail">
						<div class="type-fleft">
							<div class="contents-label">기본정보</div>
						</div>

					</div>
					<!-- //Top -->

					<table class="component-detail-table type-line-none">
						<colgroup>
							<col width="150">
							<col width="*">
							<col width="150">
							<col width="*">
						</colgroup>
						<tbody>
						<tr>
							<th>평가절차구분</th>
							<td><comTag:comCmcdCdValueComboBox id="P_ESTM_PROCD_SECD_S" name="P_ESTM_PROCD_SECD_S" selectKey="${param.P_ESTM_PROCD_SECD_S}" cdId="ESTM_PROCD_SECD"  headerKey="" headerValue="전체" className="component-select"/></td>
							<th>&nbsp;</th>
							<td>&nbsp;</td>
						</tr>
						<tr>
							<th>
								<i class="icon-necessary"></i>서식명
							</th>
							<td colspan="3">
								<input type="text" class="component-input" style="width: 500px;">
							</td>
						</tr>
						</tbody>
					</table>
				</div>

				<div class="table-detail">
					<!-- Top -->
					<div class="top-detail">
						<div class="type-fleft">
							<div class="contents-label">평가결과항목</div>
						</div>
					</div>
					<!-- //Top -->
					<div id="tableDiv" style="width: 1300px;">
						<table class="component-detail-table type-line-none">
							<colgroup>
								<col style="width: auto;">
							</colgroup>
							<tbody id="copyArea">
							<!-- <tr>
								<th>평가항목명</th>
							</tr> -->
							<tr>
								<th>
									<input type="hidden" class="component-input" name="P_ESTM_DTL_ITEM_NO">
									<input type="text" class="component-input" name="P_ESTM_DTL_ITEM_NM" placeholder="항목명" ><br>
									<input type="text" class="component-input" name="P_ESTM_DTL_ITEM_SCR" placeholder="실제값"><br>
					  				<button type="button" class="component-button-s type-line" style="width: 30px;" name="regBtn">>></button><!-- 셀추가(열추가) -->
									<!-- <button type="button" class="component-button-s type-line" style="width: 30px;" name="regUpBtn">^</button>위로 행추가 -->
					  				<!-- <button type="button" class="component-button-s type-line" style="width: 30px;" name="absBtn">+</button>좌에서 우로 셀합치기 -->
					  				<!-- <button type="button" class="component-button-s type-line" style="width: 30px;" name="regBtn">X</button>셀삭제 -->
								</th>
							</tr>
							</tbody>
						</table>
					</div>
				</div>


				<!-- bottom button -->
				<div class="bottom-buttons">
					<a href="javascript:" class="btn-bottom type-a">목록</a>
					<a href="javascript:" class="btn-bottom type-b">저장</a>
				</div>
				<!-- //bottom button -->
			</div>
			<!-- //Detail -->
		</div>

<form id="listFrm" method="POST">
	<input type="hidden" name="resourceName" value="${comFn:nvl(param.resourceName, resourceName)}" >
</form> 

<form id="downloadFrm" method="POST">
	<input type="hidden" name="P_FILE_GRP_NO" value="" >
	<input type="hidden" name="P_FILE_SN" value="" >
</form> 

<form id="modiFrm" method="POST">
	<input type="hidden" name="resourceName" value="${comFn:nvl(param.resourceName, resourceName)}" >
	<input type="hidden" name="P_FRM_NO" id="P_FRM_NO" value="${contFormDetail.FRM_NO}">
	<input type="hidden" name="P_VRSN" id="P_VRSN" value="${contFormDetail.VRSN}">
</form>

<form id="ubiPopupFrm" method="POST" > 
	<input type="hidden" name="P_JRF" value=""/>
	<input type="hidden" name="P_ARG" value=""/>
	<input type="hidden" name="P_FRM_NO" value="${contFormDetail.FRM_NO}">
</form>

<form id="popupFrm" method="POST"> 
	<input type="hidden" name="resourceName" value="${comFn:nvl(param.resourceName, resourceName)}" >
	<input type="hidden" name="P_FILE_GRP_NO" >
</form>

