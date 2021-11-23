<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 기준정보 > 서식관리 상세
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

<script type="text/javascript" src="${jsPath}/ipro/views/sytm/estmFrmUpdtForm.js"></script>
<script type="text/javascript" src="/raonkeditor/js/raonkeditor.js"></script>

<!-- 네비게이션 -->
<div class="area-detail">
	<div class="table-detail">
		<!-- Top -->
		<div class="nav_sec">

			<div class="btn-help" style="display:none;">
				<a href="javascript:helpPopup();">도움말</a>
			</div>
			
			<div class="option-left">
				<ul class="location">
					<li style="font-size: 30px; font-weight: 500;">평가서식관리 수정</li>
				</ul>
			</div>
			
			<div class="option-right">
				<ul class="location">
					<li class="home"><a href="#">홈</a></li>
					<li><a href="#">${myMenuList.bigMenuNm}</a></li>
					<li><a href="#">${myMenuSubList.smallMenuNm}</a></li>
				</ul>
			</div>
		</div>
	</div>
</div>
<!--//네비게이션 -->

<!-- 서식데이터세팅 -->
<c:forEach var="data" items="${estmFrmItemList}" varStatus="status">
	<input type="hidden" name="D_ESTM_ITEM_NO" value="${data.ESTM_ITEM_NO }"><!-- 항목번호 -->
	<input type="hidden" name="D_ESTM_ITEM_NM" value="${data.ESTM_ITEM_NM }"><!-- 항목명 -->
	<input type="hidden" name="D_ESTM_ITEM_DSMK" value="${data.ESTM_ITEM_DSMK }"><!-- 배점 -->
	<input type="hidden" name="D_ESTM_ITEM_DESC" value="${data.ESTM_ITEM_DESC }"><!-- 내용 -->
	<input type="hidden" name="D_ESTM_MTHD_SECD" value="${data.ESTM_MTHD_SECD }"><!-- 평가항목/평가기준 -->
</c:forEach>

<!-- //서식데이터세팅 -->

<!-- Detail -->
<form id="modiFrm" method="POST">
	<input type="hidden" name="resourceName" value="${comFn:nvl(param.resourceName, resourceName)}" >
	<input type="hidden" name="P_ESTM_FRM_NO" id="P_ESTM_FRM_NO" value="${estmFrmDetail.ESTM_FRM_NO}">
	

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
							<td><comTag:comCmcdCdValueComboBox id="P_ESTM_PROCD_SECD" name="P_ESTM_PROCD_SECD" selectKey="${estmFrmDetail.ESTM_PROCD_SECD}" cdId="ESTM_PROCD_SECD"  headerKey="" headerValue="전체" className="component-select"/></td>
							<th>&nbsp;</th>
							<td>&nbsp;</td>
						</tr>
						<tr>
							<th>
								<i class="icon-necessary"></i>서식명
							</th>
							<td colspan="3">
								<input type="text" id="P_ESTM_FRM_NM" name="P_ESTM_FRM_NM" class="component-input" style="width: 500px;" value="${estmFrmDetail.ESTM_FRM_NM}">
								
							</td>
						</tr>
						</tbody>
					</table>
				</div>

				<div class="table-detail">
					<!-- Top -->
					<div class="top-detail">
						<div class="type-fleft">
							<div class="contents-label">평가항목</div>
						</div>
					</div>
					<!-- //Top -->
					<div id="tableDiv">
						<table class="component-detail-table type-line-none">
							<colgroup>
								<col style="width: auto;">
							</colgroup>
							<tbody id="copyArea">
							<tr>
								<th>평가항목명</th>
							</tr>
							<tr>
								<td>
									<input type="hidden" class="component-input" name="P_ESTM_DTL_ITEM_NO">
									<input type="text" class="component-input type-full" placeholder="평가항목명" name="P_ESTM_DTL_ITEM_NM">
									<textarea class="component-textarea" placeholder="평가항목내용" name="P_ESTM_ITEM_DTL_CNTN"></textarea>
									<input type="text" class="component-input" name="P_ESTM_DTL_ITEM_SCR" placeholder="배점" numeric>
									<select class="component-select type-division" name="P_ESTM_DTL_ITEM_SCR_SECD"><option value="A">합산</option><option value="B">최고점</option></select>
									<button type="button" class="component-button-s type-line" name="regBtn">+</button>
					  				<button type="button" class="component-button-s type-line" name="regChildBtn">>></button>
									<!-- <a href="javascript:regBtnClick('01');" class="component-button-s type-line">+</a>
									<a href="javascript:regChildBtnClick('01');" class="component-button-s type-line"></a> -->
								
								</td>
							</tr>
							</tbody>
						</table>
					</div>
				</div>
</form>

				<!-- bottom button -->
				<div class="bottom-buttons">
					<a href="javascript:" id="saveBtn" class="btn-bottom type-b">저장</a>
					<a href="javascript:" id="cnclBtn"class="btn-bottom type-a">취소</a>
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

<form id="ubiPopupFrm" method="POST" > 
	<input type="hidden" name="P_JRF" value=""/>
	<input type="hidden" name="P_ARG" value=""/>
	<input type="hidden" name="P_FRM_NO" value="${contFormDetail.FRM_NO}">
</form>

<form id="popupFrm" method="POST"> 
	<input type="hidden" name="resourceName" value="${comFn:nvl(param.resourceName, resourceName)}" >
	<input type="hidden" name="P_FILE_GRP_NO" >
	<input type="hidden" name="P_ESTM_PROCD_SECD" >
</form>

