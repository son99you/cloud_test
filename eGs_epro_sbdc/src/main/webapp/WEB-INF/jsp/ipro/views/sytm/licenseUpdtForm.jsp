<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 정보관리 > 라이센스 수정폼
 *
 * <pre>
 * sytm 
 *    |_ licenseUpdtForm.jsp
 * 
 * </pre>
 * @date : 2021. 09. 24
 * @version : 1.0
 * @author : 은우소프트 손연우
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld"%>
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld"%>

<script type="text/javascript" src="${jsPath}/ipro/views/sytm/licenseUpdtForm.js"></script>
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
					<li style="font-size: 30px; font-weight: 500;">라이센스 수정</li>
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
<form id="modiFrm" method="POST">
	<input type="hidden" name="resourceName" value="${comFn:nvl(param.resourceName, resourceName)}" >
	<input type="hidden" name="P_LICENSE_NO" id="P_LICENSE_NO" value="${licenseDetail.LICENSE_NO}">
	<div class="area-detail">
				<div class="table-detail">
					<!-- Top -->
					<div class="top-detail">
						<div class="type-fleft">
							<div class="contents-label">라이센스정보</div>
						</div>

					</div>
					<!-- //Top -->

					<table class="component-detail-table type-line-none">
						<colgroup>
							<col width="180">
							<col width="*">
							<col width="150">
							<col width="*">
						</colgroup>
						<tbody>
						<tr>
							<th><i class="icon-necessary"></i>사업자등록번호</th>
							<td><input type="text" id="P_BIZRNO" name="P_BIZRNO" class="component-input type-full" maxlength="10" numeric value="${licenseDetail.BIZRNO}"></td>
							<th>진행상태</th>
							<td>${licenseDetail.LICENSE_PSCD_NM}</td>
						</tr>
						<tr>
							<th>
								<i class="icon-necessary"></i>업체명
							</th>
							<td colspan="3">
								<input type="text" class="component-input type-full" id="P_VEND_NM" name="P_VEND_NM" value="${licenseDetail.VEND_NM}">
							</td>
						</tr>
						<tr>
							<th><i class="icon-necessary"></i>버전</th>
							<td><comTag:comCmcdCdValueComboBox id="P_VERSION_NO" name="P_VERSION_NO" selectKey="${licenseDetail.VERSION_NO}" cdId="VERSION_NO"  headerKey="" headerValue="선택" className="component-select"/></td>
							<th>
								<i class="icon-necessary"></i>발급구분
							</th>
							<td>
								<comTag:comCmcdCdValueComboBox id="P_LICENSE_PROCD_SECD" name="P_LICENSE_PROCD_SECD" selectKey="${licenseDetail.LICENSE_PROCD_SECD}" cdId="LICENSE_PROCD_SECD"  headerKey="" headerValue="선택" className="component-select"/>
							</td>
						</tr>
						<tr>
							<th>
								발급기간
							</th>
							<td colspan="3">
								<%-- ${comFn:formatDate(licenseDetail.LICENSE_ST_DATE, 'yyyyMMdd', 'yyyy-MM-dd')} ~ ${comFn:formatDate(licenseDetail.LICENSE_END_DATE, 'yyyyMMdd', 'yyyy-MM-dd')} --%>
								<!-- Component Calen -->
								<div class="component-calen">
									<div class="data-calen">
										<input type="text" id="P_LICENSE_ST_DATE" name="P_LICENSE_ST_DATE" class="component-input" value="${comFn:formatDate(licenseDetail.LICENSE_ST_DATE, 'yyyyMMdd', 'yyyy-MM-dd') }" date>
									</div>
									<em class="txt-bar">~</em>
									<div class="data-calen">
										<input type="text" id="P_LICENSE_END_DATE" name="P_LICENSE_END_DATE" class="component-input" value="${comFn:formatDate(licenseDetail.LICENSE_END_DATE, 'yyyyMMdd', 'yyyy-MM-dd') }" date>
									</div>
								</div>
								<!-- //Component Calen -->
							</td>
						</tr>
						<tr style="height: 50px;">
							<th>
								<i class="icon-necessary"></i>IP주소 <br> (여러개 인경우 콤마[,]로 표시)
							</th>
							<td colspan="3">
								<input type="text" class="component-input type-full" id="P_VEND_IP_ADDR" name="P_VEND_IP_ADDR"  value="${licenseDetail.VEND_IP_ADDR}">
							</td>
						</tr>
						</tbody>
					</table>
				</div>

				<!-- bottom button -->
				<div class="bottom-buttons">
					<a href="javascript:" id="saveBtn" class="btn-bottom type-b">저장</a>
					<a href="javascript:" id="cnclBtn" class="btn-bottom type-a">취소</a>
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

<form id="ubiPopupFrm" method="POST" > 
	<input type="hidden" name="P_JRF" value=""/>
	<input type="hidden" name="P_ARG" value=""/>
	<input type="hidden" name="P_LICENSE_NO" value="${licenseDetail.LICENSE_NO}">
</form>

<form id="popupFrm" method="POST"> 
	<input type="hidden" name="resourceName" value="${comFn:nvl(param.resourceName, resourceName)}" >
	<input type="hidden" name="P_FILE_GRP_NO" >
	<input type="hidden" name="P_ESTM_PROCD_SECD" >
</form>

