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

<script type="text/javascript" src="${jsPath}/ipro/views/sytm/estmFrmRegForm.js"></script>
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
					<li style="font-size: 30px; font-weight: 500;">평가서식관리 등록</li>
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

<!-- Detail -->
<form id="regFrm" method="POST">
	<input type="hidden" name="resourceName" value="${comFn:nvl(param.resourceName, resourceName)}" >
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
							<th><i class="icon-necessary"></i>평가절차구분</th>
							<td><comTag:comCmcdCdValueComboBox id="P_ESTM_PROCD_SECD" name="P_ESTM_PROCD_SECD" selectKey="${param.P_ESTM_PROCD_SECD}" cdId="ESTM_PROCD_SECD"  headerKey="" headerValue="선택" className="component-select"/></td>
							<th>&nbsp;</th>
							<td>&nbsp;</td>
						</tr>
						<tr>
							<th>
								<i class="icon-necessary"></i>서식명
							</th>
							<td colspan="3">
								<input type="text" class="component-input type-full" id="P_ESTM_FRM_NM" name="P_ESTM_FRM_NM" >
							</td>
						</tr>
						</tbody>
					</table>
				</div>

				<div class="table-detail">
					<!-- Top -->
					<div class="top-detail">
						<div class="type-fleft">
							<div class="contents-label">상세</div>
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
								<th>상세내용</th>
							</tr>
							<tr>
								<td>
									항목형식&nbsp; : &nbsp; <select class="component-select type-division" name="P_ITEM_TYPE" onchange="changeItemSecd(this);"><option value="A">항목명</option><option value="B">항목데이터</option></select>
									<input type="hidden" class="component-input" name="P_ITEM_NO"> <br>
									필수체크여부&nbsp; : &nbsp;<select class="component-select type-division" name="P_ESS_AT"><option value="N">아니오</option><option value="Y">예</option></select>
									<input type="text" class="component-input type-full" placeholder="항목명" name="P_ITEM_NM">
									<button type="button" class="component-button-s type-line" style="width:30px; display: none;" id="searchBtn" name="searchBtn">?</button>
									<button type="button" class="component-button-s type-line" style="width:30px;" name="regBtn">+</button>
					  				<button type="button" class="component-button-s type-line" style="width:30px;" name="regChildBtn">>></button>
								</td>
								
							</tr>
							</tbody>
						</table>
					</div>
				</div>

				<!-- bottom button -->
				<div class="bottom-buttons">
					<a href="javascript:" id="reViewBtn" class="btn-bottom type-b">미리보기</a>
					<a href="javascript:" id="saveBtn" class="btn-bottom type-b">저장</a>
					<a href="javascript:" id="listBtn" class="btn-bottom type-a">목록</a>
				</div>
				<!-- //bottom button -->
			</div>
			<!-- //Detail -->
		</div>
</form>

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

