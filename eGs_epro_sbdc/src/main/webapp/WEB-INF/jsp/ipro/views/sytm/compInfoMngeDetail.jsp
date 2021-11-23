<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 본사정보관리 상세
 *
 * <pre>
 * sytm 
 *    |_ compInfoMngeDetail.jsp
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

<script type="text/javascript" src="${jsPath}/ipro/views/sytm/compInfoMngeDetail.js"></script>

<div class="conts_wrap">
	<div class="inner">
		<div class="tit_wrap">
			<h3 class="tit">본사정보관리</h3>
			<ul class="step_wrap">
				<li><a href="#">${myMenuList.bigMenuNm}</a></li>
				<li><a href="#">${myMenuSubList.smallMenuNm}</a></li>
			</ul>			
		</div>
		<form id="detailFrm" name="registFrm" method="POST" >
			<input type="hidden" id="P_PRTNR_TY_SE_CD" name="P_PRTNR_TY_SE_CD" value="${compInfoMngeDetail.PRTNR_TY_SE_CD}"/>
			
			<fieldset>
				<div class="view_wrap typeB">
					<h4 class="tit">기본정보</h4>
					<div class="view_area">
						<table>
							<colgroup>
								<col width="30%" align="left">
								<col width="70%" align="left">
							</colgroup>
							<tr height="24">
								<th class=" txtl"> 사업자번호</th>
								<td>
									<input type="text" class="w120" id="P_BIZRNO_1" name="P_BIZRNO_1" value="${compInfoMngeDetail.BIZRNO_1 }" maxlength="3" numeric class="disabled" readonly="readonly">
									-
									<input type="text" class="w120" id="P_BIZRNO_2" name="P_BIZRNO_2" value="${compInfoMngeDetail.BIZRNO_2 }" maxlength="2" numeric class="disabled" readonly="readonly">
									-
									<input type="text" class="w120" id="P_BIZRNO_3" name="P_BIZRNO_3" value="${compInfoMngeDetail.BIZRNO_3 }" maxlength="5" numeric class="disabled" readonly="readonly">
								</td>
							</tr>
							<tr height="24">
								<th class=" txtl"> 대표자명</th>
								<td>
									<input type="text" id="P_RPRSNTV_NM" name="P_RPRSNTV_NM" value="${compInfoMngeDetail.RPRSNTV_NM }">
								</td>
							</tr>
							<tr height="24">
								<th class=" txtl"> 업체명</th>
								<td>
									<input type="text" id="P_PRTNR_KOREAN_NM" name="P_PRTNR_KOREAN_NM" value="${compInfoMngeDetail.PRTNR_KOREAN_NM }" style="width: 25%;" class="disabled" readonly="readonly">
								</td>
							</tr>
							<tr height="24">
								<th class=" txtl"> 업태</th>
								<td>
									<input type="text" id="P_BIZCND_NM" name="P_BIZCND_NM" value="${compInfoMngeDetail.BIZCND_NM }" style="width: 25%;">
								</td>
							</tr>
							<tr height="24">
								<th class=" txtl"> 종목</th>
								<td>
									<input type="text" id="P_INDUTY_NM" name="P_INDUTY_NM" value="${compInfoMngeDetail.INDUTY_NM }" style="width: 25%;">
								</td>
							</tr>
							<tr height="24">
							    <th class=" txtl">우편번호</th>
							    <td>
								
									<%-- <button class="btn btn_02 btn_sch" id="zipBtn" onclick="zipPopup('zipA');" ><img src="${imagePath}/ipro/icon/ico_search.png" alt="">우편번호</button> --%>
									
									<input type="text" id="P_ZIP" name="P_ZIP" value="${compInfoMngeDetail.ZIP }" style="width: 10%;" class="disabled" readonly="readonly">
									<button type="button" class="btn btn_02 btn_sch" id="zipBtn" name="zipBtn"> 우편번호</button>
				                            
									<br>
									<input type="text" id="P_RN_ADRES" name="P_RN_ADRES" value="${compInfoMngeDetail.RN_ADRES }" style="width: 50%;">
								</td>
							</tr>
							
							<tr height="24">
							    <th class=" txtl"> 나머지주소</th>
								<td>
									<input type="text" id="P_DETAIL_ADRES" name="P_DETAIL_ADRES" value="${compInfoMngeDetail.DETAIL_ADRES }" style="width: 50%;">
								</td>
							</tr>
						</table>					
					</div>
				</div>
				
				<div class="btn_wrap view_btn">
					<button type="button" class="btn btn_02 btn_revise">수정</button>
					<button type="button" class="btn btn_02 btn_sch">삭제</button>
					<button type="button" class="btn btn_02 btn_sch">취소</button>
				</div> <!--// btn_wrap E -->
				
			</fieldset>
		</form>
	</div>
</div>

<%-- VIEW FORM --%>
<form id="viewFrm" method="POST">
	<input type="hidden" name="resourceName" value="${param.resourceName}">
	<input type="hidden" name="resourceDesc" value="${param.resourceDesc}">
	<input type="hidden" name="P_ENTRPS_REGIST_NO">
</form>
<%-- ZIP POPUP FORM --%>
<form id="zipPopupFrm" method="POST">
	<input type="hidden" name="resourceName" value="${param.resourceName}">
	<input type="hidden" name="resourceDesc" value="${param.resourceDesc}">
</form>
