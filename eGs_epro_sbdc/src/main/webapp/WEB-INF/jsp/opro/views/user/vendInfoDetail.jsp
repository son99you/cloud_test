<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 마이페이지 > 자사정보
 *
 * <pre>
 * user 
 *    |_ vendInfoDetail.jsp
 * </pre>
 * @date : 2017. 06. 22.
 * @version : 1.0
 * @author : 은우소프트 홍찬일
 *
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld"%>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld"%>
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %> 

<script type="text/javascript" src="${jsPath}/opro/views/user/vendInfoDetail.js"></script>

<div class="sp_sec">
	<div class="nav_sec">
		<ul id="breadcrumbs">
			<li class="home">홈</li>
			<li>${myMenuList.bigMenuNm}</li>
			<li>${myMenuSubList.smallMenuNm}</li> 
		</ul>
	</div>
	<h3 class="sp_tit">자사정보</h3>
	
	<form id="registFrm" name="registFrm" method="POST" enctype="multipart/form-data">
		<input type="hidden" name="resourceName" value="${comFn:nvl(param.resourceName, resourceName)}" >
		<input type="hidden" id="P_PAGE_NO" name="P_PAGE_NO" value="${comFn:nvl(param.P_PAGE_NO, 1)}">
		<input type="hidden" id="P_PAGE_SIZE" name="P_PAGE_SIZE" value="${comFn:nvl(param.P_PAGE_SIZE, 10)}">
		<input type="hidden" id="P_VEND_REG_NO" name="P_VEND_REG_NO" value="${vendInfoDetail.VEND_REG_NO}">
		
		<div class="sp_cont">
		<p class="spc_stit">업체정보
		<c:if test="${loginResult.BIZR_NO eq '1198602801'}">
			<button type="button" class="btn ty04" id="test">반영</button>
		</c:if>
		</p>
			<table class="form_tb" summary="업체정보 입니다.">
				<colgroup>
					<col width="15%">
					<col width="35%">
					<col width="15%">
					<col width="35%">
				</colgroup>
				<tr>
					<th>업체등록번호</th>
					<td>${vendInfoDetail.VEND_REG_NO}</td>
					<th>사업자번호</th>
					<td>${comFn:formatBizNumber(vendInfoDetail.BIZRNO)}</td>
				</tr>
				<tr>
					<th>업체명</th>	
					<td>${vendInfoDetail.VEND_NM}</td>	
					<th>대표자명</th>
					<td>${vendInfoDetail.RPRS_NM}</td>
				</tr>
				<tr>
					<th>업태</th>
					<td>${vendInfoDetail.BCNM}</td>			   		                    
					<th>업종</th>
					<td>${vendInfoDetail.BTNM}</td>			   		                    
		        </tr>
				<tr>
					<th>전화번호</th>
					<td>${comFn:formatPhoneNumber(vendInfoDetail.TEL_NO)}</td>
					<th>이메일주소</th>
					<td>${vendInfoDetail.EMAL_ADDR}</td>
				</tr>
				<tr>
					<th>주소</th>
					<td colspan="3">
						${vendInfoDetail.ADDR_NM }
					</td>
				</tr>
				<tr>
					<th>우선구매업체</th>
					<td colspan="3">
						<input type="checkbox" id="CHK_WOMAN_YN"  disabled <c:if test="${vendInfoDetail.WOMAN_YN eq 'Y'}">checked="checked"</c:if>/><label for="CHK_WOMAN_YN"  style="padding:0 10px 0 5px;">여성기업</label>
						<input type="checkbox" id="CHK_HANDICAP_YN"  disabled  <c:if test="${vendInfoDetail.HANDICAP_YN eq 'Y'}">checked="checked"</c:if>/><label for="CHK_HANDICAP_YN" style="padding:0 10px 0 5px;">장애인기업</label>
						<input type="checkbox" id="CHK_NEW_TECH_YN"  disabled  <c:if test="${vendInfoDetail.NEW_TECH_YN eq 'Y'}">checked="checked"</c:if>/><label for="CHK_NEW_TECH_YN" style="padding:0 10px 0 5px;">신기술기업</label>
						<input type="checkbox" id="CHK_ECO_FRIENDLY_YN"  disabled  <c:if test="${vendInfoDetail.ECO_FRIENDLY_YN eq 'Y'}">checked="checked"</c:if>/><label for="CHK_ECO_FRIENDLY_YN" style="padding:0 10px 0 5px;">친환경기업</label>
						<input type="checkbox" id="CHK_SER_HANDICAP_YN"  disabled  <c:if test="${vendInfoDetail.SER_HANDICAP_YN eq 'Y'}">checked="checked"</c:if>/><label for="CHK_SER_HANDICAP_YN" style="padding:0 10px 0 5px;">중증장애인기업</label>
						<input type="checkbox" id="CHK_MAN_HANDICAP_YN"  disabled  <c:if test="${vendInfoDetail.MAN_HANDICAP_YN eq 'Y'}">checked="checked"</c:if>/><label for="CHK_MAN_HANDICAP_YN" style="padding:0 10px 0 5px;">용사촌기업</label>
						<input type="checkbox" id="CHK_SOCIAL_YN"  disabled  <c:if test="${vendInfoDetail.SOCIAL_YN eq 'Y'}">checked="checked"</c:if>/><label for="CHK_SOCIAL_YN" style="padding:0 10px 0 5px;">사회적기업</label>
						<input type="checkbox" id="CHK_SMALL_YN"  disabled  <c:if test="${vendInfoDetail.SMALL_YN eq 'Y'}">checked="checked"</c:if>/><label for="CHK_SMALL_YN" style="padding:0 10px 0 5px;">소기업</label>
						<input type="checkbox" id="CHK_SMMAN_YN"  disabled  <c:if test="${vendInfoDetail.SMMAN_YN eq 'Y'}">checked="checked"</c:if>/><label for="CHK_SMMAN_YN" style="padding:0 10px 0 5px;">소상공인</label>
						<input type="checkbox" id="CHK_SOCIAL_YN2"  disabled  <c:if test="${vendInfoDetail.SOCIAL_YN2 eq 'Y'}">checked="checked"</c:if>/><label for="CHK_SOCIAL_YN2" style="padding:0 10px 0 5px;">사회적협동기업조합</label>
						<input type="checkbox" id="CHK_HANDICAP_ST_CORP_YN"  disabled  <c:if test="${vendInfoDetail.HANDICAP_ST_CORP_YN eq 'Y'}">checked="checked"</c:if>/><label for="CHK_HANDICAP_ST_CORP_YN" style="padding:0 10px 0 5px;">장애인표준사업장여부</label>
						<input type="checkbox" id="CHK_NEP_YN"  disabled  <c:if test="${vendInfoDetail.NEP_YN eq 'Y'}">checked="checked"</c:if>/><label for="CHK_NEP_YN" style="padding:0 10px 0 5px;">NEP</label>
						<%-- <input type="checkbox" id="CHK_CLIENT_TAX_TYPE_YN"  disabled  <c:if test="${vendInfoDetail.CLIENT_TAX_TYPE_YN eq 'Y'}">checked="checked"</c:if>/><label for="CLIENT_TAX_TYPE_YN" style="padding:0 10px 0 5px;">과세자유형</label> --%>
						<input type="checkbox" id="CHK_INNP_TYPE_YN"  disabled  <c:if test="${vendInfoDetail.INNP_TYPE_YN eq 'Y'}">checked="checked"</c:if>/><label for="CHK_INNP_TYPE_YN" style="padding:0 10px 0 5px;">혁신시제품</label>
						<input type="checkbox" id="CHK_INNP_SHOP_YN"  disabled  <c:if test="${vendInfoDetail.INNP_SHOP_YN eq 'Y'}">checked="checked"</c:if>/><label for="CHK_INNP_SHOP_YN" style="padding:0 10px 0 5px;">혁신장터</label>
						<input type="checkbox" id="CHK_VENTURE_YN"  disabled  <c:if test="${vendInfoDetail.VENTURE_YN eq 'Y'}">checked="checked"</c:if>/><label for="CHK_VENTURE_YN" style="padding:0 10px 0 5px;">벤처장터</label>
						<input type="checkbox" id="CHK_TR_PUR_YN"  disabled  <c:if test="${vendInfoDetail.TR_PUR_YN eq 'Y'}">checked="checked"</c:if>/><label for="CHK_TR_PUR_YN" style="padding:0 10px 0 5px;">시범구매</label>
						<input type="checkbox" id="CHK_TR_TECH_YN"  disabled  <c:if test="${vendInfoDetail.TR_TECH_YN eq 'Y'}">checked="checked"</c:if>/><label for="CHK_TR_TECH_YN" style="padding:0 10px 0 5px;">시범우수기술여부</label>
					</td>
				</tr>
			</table>
			<div class="btm_btns">
				<!-- <button type="button" class="btn ty04" id="listBtn">목록</button> -->
			</div>
		</div>
	</form>
</div> <!--// content E-->

