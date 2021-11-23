<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 정보관리 > 권한관리 수정폼
 *
 * <pre>
 * estm 
 *    |_ authMgrUpdtForm.jsp
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

<%-- <script type="text/javascript" src="${jsPath}/ipro/views/estm/estmCmtmSpheMpgUpdtForm.js"></script> --%>
<script type="text/javascript" src="${jsPath}/ipro/views/sytm/authMgrUpdtForm.js"></script>
<script type="text/javascript" src="/raonkeditor/js/raonkeditor.js"></script>

<!-- 데이터세팅 -->
<c:forEach var="data" items="${menuAuthMgrList}" varStatus="status">
	<input type="hidden" name="D_AUTH_NM" value="${data.AUTH_NM }"><!-- 권한명 -->
	<input type="hidden" name="D_MENU_ID" value="${data.MENU_ID }"><!-- 메뉴ID -->
	<input type="hidden" name="D_MENU_NM" value="${data.MENU_NM }"><!-- 메뉴명 -->
	<input type="hidden" name="D_SELECT_AT" value="${data.SELECT_AT }"><!-- 메뉴선정구분 -->
</c:forEach>
<!-- //데이터세팅 -->


<form id="updtFrm" method="POST">
	<input type="hidden" id="resourceName" name="resourceName" value="${comFn:nvl(param.resourceName, resourceName)}">
	<input type="hidden" id="resourceDesc" name="resourceDesc" value="${param.resourceDesc}">
	<input type="hidden" name="P_AUTH_ID" value="${menuAuthMgrDetail.AUTH_ID}">
	
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
						<li style="font-size: 30px; font-weight: 500;">권한관리 수정</li>
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
					<th>권한ID</th>
					<td>${menuAuthMgrDetail.AUTH_ID }</td>
					<th>권한명</th>
					<td>${menuAuthMgrDetail.AUTH_NM }</td>
				</tr>
				</tbody>
			</table>
		</div>
		
		<div class="table-detail">
			<div class="wrap-box-move">
				<div class="left-box">
					<div class="top-detail">
						<div class="type-fleft">
							<div class="contents-label">미선정메뉴</div>
						</div>
					</div>

					<!-- <table class="component-detail-table" style="width: 100%;"> -->
					<%-- <table class="component-detail-table" style="width: 480px;">
						<colgroup>
							<col width="100px">
							<col width="*">
							<col width="100px">
							<col width="*">
						</colgroup>
						<tbody>
							<tr>
								<th>평가위원명</th>
								<td colspan=""><input type="text" id="noSlctCmtmNm" class="component-input w100"></td>
								<!-- <th>내외부구분</th> -->
								<th>대분류</th>
								<td colspan="">
									<comTag:comCmcdCdValueComboBox id="P_LLF_SECD" name="P_LLF_SECD" selectKey="${param.P_LLF_SECD}" cdId="LLF_SECD"  headerKey="" headerValue="전체" className="component-select w100"/>
								</td>
								<!-- <td><button type="button" class="component-button-s" id="noSlctBtn">조회</button></td> -->
								 <!-- <a type="button" href="javascript:" id="noSlctBtn" class="btn-bottom type-b">조회</a> -->
							</tr>
							<tr>
								<th>내역</th>
								<td>
									<comTag:comCmcdCdValueComboBox id="P_CNTN_SECD" name="P_CNTN_SECD" selectKey="${param.P_CNTN_SECD}" cdId="CNTN_SECD"  headerKey="" headerValue="전체" className="component-select w100"/>
								</td>
								<!-- <th>내외부구분</th> -->
								<th>중분류</th>
								<td>
									<comTag:comCmcdCdValueComboBox id="P_MLF_SECD" name="P_MLF_SECD" selectKey="${param.P_MLF_SECD}" cdId="MLF_SECD"  headerKey="" headerValue="전체" className="component-select w100"/>
								</td>
							</tr>
							<tr>
								<th>소분류</th>
								<td colspan="2">
									<comTag:comCmcdCdValueComboBox id="P_SLF_SECD" name="P_SLF_SECD" selectKey="${param.P_SLF_SECD}" cdId="SLF_SECD"  headerKey="" headerValue="전체" className="component-select w100"/>
								</td>
								<td><button type="button" class="component-button-s" id="noSlctBtn">조회</button></td>
							</tr>
						</tbody>
					</table> --%>
					
					<div class="box-move-list type-fleft" style="height:510px;">
						
					
						<table class="component-table" style="table-layout: auto;">
							<colgroup>
								<col width="10%;">
								<col width="auto;">
								<col width="auto;">
								<col width="auto;">
							</colgroup>
							<thead>
								<tr>
									<th class="txt-center">선택</th>
									<th class="txt-center">권한명</th>
									<th class="txt-center">메뉴ID</th>
									<th>메뉴명</th>
								</tr>
							</thead>
							<tbody id="copyAreaOne">
							</tbody>
						</table>
					</div>
				</div>

				<div class="ui-move-button">
					<a href="javascript:moveDataRight();" class="btn-move-right"></a>
					<a href="javascript:moveDataLeft();" class="btn-move-left"></a>
				</div>

				<div class="right-box">
					<div class="top-detail">
						<div class="type-fleft">
							<div class="contents-label type-fright">선정메뉴</div>
						</div>
					</div>

					<div class="box-move-list type-fright" style="height:510px;">
						<table class="component-table" style="table-layout: auto;">
							<colgroup>
								<col width="10%;">
								<col width="auto;">
								<col width="auto;">
								<col width="auto;">
							</colgroup>
							<thead>
								<tr>
									<th class="txt-center">선택</th>
									<th class="txt-center">권한명</th>
									<th class="txt-center">메뉴ID</th>
									<th>메뉴명</th>
								</tr>
							</thead>
							<tbody id="copyAreaTwo">

							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
		

		<!-- bottom button -->
		<div class="bottom-buttons">
			<a href="javascript:" id="saveBtn" class="btn-bottom type-b">저장</a>
			<a href="javascript:" id="listBtn" class="btn-bottom type-a">목록</a>
		</div>
		<!-- //bottom button -->
	</div>
	<!-- //Detail -->
</form>

<form id="listFrm" method="POST">
	<input type="hidden" id="resourceName" name="resourceName" value="${comFn:nvl(param.resourceName, resourceName)}">
	<input type="hidden" id="resourceDesc" name="resourceDesc" value="${param.resourceDesc}">
</form> 

<form id="updtFrm" method="POST">
	<input type="hidden" id="resourceName" name="resourceName" value="${comFn:nvl(param.resourceName, resourceName)}">
	<input type="hidden" id="resourceDesc" name="resourceDesc" value="${param.resourceDesc}">
	<input type="hidden" name="P_FRM_NO" id="P_FRM_NO" value="${contFormDetail.FRM_NO}">
	<input type="hidden" name="P_VRSN" id="P_VRSN" value="${contFormDetail.VRSN}">
</form>