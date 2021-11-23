<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 정보관리 > 서식관리 상세
 *
 * <pre>
 * sytm 
 *    |_ estmFrmDetail.jsp
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

<script type="text/javascript" src="${jsPath}/ipro/views/sytm/estmFrmDetail.js"></script>
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
					<li style="font-size: 30px; font-weight: 500;">평가서식관리 상세</li>
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

<input type="hidden" name="P_ESTM_FRM_PROCD_SECD" value="${estmFrmDetail.ESTM_PROCD_SECD }"/>

<!-- Detail -->
<form id="detailFrm" method="POST">
	<input type="hidden"  id="resultCode" value="${resultCode}">
	<input type="hidden" name="resourceName" value="${comFn:nvl(param.resourceName, resourceName)}" >
	<input type="hidden" name="P_ESTM_FRM_NO" id="P_ESTM_FRM_NO" value="${estmFrmDetail.ESTM_FRM_NO }">
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
								<th>평가서식번호</th>
								<td>${estmFrmDetail.ESTM_FRM_NO }</td>
								<th>평가절차구분</th>
								<td>${estmFrmDetail.ESTM_PROCD_SECD_NM }</td>
							</tr>
							<tr>
								<th>서식명</th>
								<td colspan="3">${estmFrmDetail.ESTM_FRM_NM }</td>
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
								<!-- <td>
									<input type="hidden" class="component-input" name="P_ESTM_DTL_ITEM_NO">
									<input type="text" class="component-input type-full" placeholder="평가항목명" name="P_ESTM_DTL_ITEM_NM">
									<textarea class="component-textarea" placeholder="평가항목내용" name="P_ESTM_ITEM_DTL_CNTN"></textarea>
									<input type="text" class="component-input" name="P_ESTM_DTL_ITEM_SCR" placeholder="배점" numeric>
								</td> -->
							</tr>
							</tbody>
						</table>
					</div>
				</div>


				<!-- bottom button -->
				<div class="bottom-buttons">
					<a href="javascript:" id="updtBtn" class="btn-bottom type-b">수정</a>
					<a href="javascript:" id="deltBtn" class="btn-bottom type-b">삭제</a>
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

<form id="updtFrm" method="POST">
	<input type="hidden" name="resourceName" value="${comFn:nvl(param.resourceName, resourceName)}" >
	<input type="hidden" name="P_ESTM_FRM_NO" id="P_ESTM_FRM_NO" value="${estmFrmDetail.ESTM_FRM_NO }">
</form>

<form id="popupFrm" method="POST"> 
	<input type="hidden" name="resourceName" value="${comFn:nvl(param.resourceName, resourceName)}" >
	<input type="hidden" name="P_FILE_GRP_NO" >
</form>