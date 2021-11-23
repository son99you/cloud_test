<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 입찰현황 > 입찰대기현황 수정 폼
 *
 * <pre>
 * ebid 
 *    |_ bidPlanUpdtForm.jsp
 * 
 * </pre>
 * @date : 2017. 06. 19
 * @version : 1.0
 * @author : 은우소프트 전상훈
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %> 
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %> 
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %> 

<script type="text/javascript" src="${jsPath}/ipro/views/ebid/bidPlanAprvlUpdtForm.js"></script>
 
<div class="conts_wrap">
	<div class="inner">
		<div class="tit_wrap">
	   		<h3 class="tit">입찰계획품의 수정</h3>
	   		<ul class="step_wrap">
		      <li><a href="#">${myMenuList.bigMenuNm}</a></li>
		      <li><a href="#">${myMenuSubList.smallMenuNm}</a></li>
		   </ul> <!--// step_wrap E -->
		</div> <!--// tit_wrap E -->
		<form id="bidPlanUpdtFrm" method="POST" enctype="multipart/form-data">
			<input type="hidden" id="resourceName" name="resourceName" value="${param.resourceName}">
			<input type="hidden" name="P_USER_ID" value="${sessionScope.loginResult.USER_ID}" >
			<input type="hidden" id="P_PAGE_NO" name="P_PAGE_NO" value="1">
			<input type="hidden" id="P_PAGE_SIZE" name="P_PAGE_SIZE" value="10">
			<input type="hidden" id="P_PBLANC_NO" name="P_PBLANC_NO" value="${bidPlanAprvlDetail.PBLANC_NO}">
			<input type="hidden" id="P_PBLANC_ODR" name="P_PBLANC_ODR" value="${bidPlanAprvlDetail.PBLANC_ODR}">
		    <input type="hidden" id="P_ATCHMNFL_GROUP_NO" name="P_ATCHMNFL_GROUP_NO" value="${bidAtchDoc.ATCHMNFL_GROUP_NO}">
			<input type="hidden" id="P_DEL_SN" name="P_DEL_SN">
		
			<div class="view_wrap typeA">
				<h4 class="tit">입찰개요</h4>
				<div class="view_area">
					<table>
				    	<colgroup>
				        	<col style="width: 15%;">						        
				        	<col style="width: 35%;">						        
				        	<col style="width: 15%;">						        
				        	<col style="width: 35%;">
				        </colgroup>
				        <c:if test="${not empty bidPlanAprvlDetail.PBLANC_NO}">
			                <tr class="line">
			                    <th scope="row">공고번호</th>
			                    <td colspan="3">${bidPlanAprvlDetail.PBLANC_NO}-${bidPlanAprvlDetail.PBLANC_ODR}</td>
			                </tr>
		                </c:if>
		                <tr>
		                    <th scope="row" class="bullet_orange">공고구분</th>
		                    <td>
		                    	<comTag:cmmnCdValueRadio name="P_PBLANC_SE_CD"  selectKey="${comFn:nvl(bidPlanAprvlDetail.PBLANC_SE_CD, 'RBID')}" list="{'RBID':'실공고'}"/>
		                    </td>
		                    <th scope="row" class="bullet_orange">입찰구분</th>
		                    <td>
			            		<comTag:comCmcdCdValueComboBox id="P_PRCURE_SE_CD" name="P_PRCURE_SE_CD"   selectKey="${bidPlanAprvlDetail.PRCURE_SE_CD}" cdId="C00001" headerKey="" headerValue="선택" width="160"/>
		                		<span id = "prcureDetailSeCdBuyDiv" <c:if test="${empty bidPlanAprvlDetail.PRCURE_SE_CD || bidPlanAprvlDetail.PRCURE_SE_CD eq '1' || bidPlanAprvlDetail.PRCURE_SE_CD eq '2' }"> style="display: none;"</c:if>>
					            		<comTag:comCmcdCdValueComboBox id="P_PRCURE_DETAIL_SE_BUY_CD" name="P_PRCURE_DETAIL_SE_CD"   selectKey="${bidPlanAprvlDetail.PRCURE_DETAIL_SE_CD}" cdId="C00026" headerKey="" headerValue="선택" cond1="0" width="160"/>
		                        </span>
		                		<span id = "prcureDetailSeCdEmpDiv" <c:if test="${empty bidPlanAprvlDetail.PRCURE_SE_CD || bidPlanAprvlDetail.PRCURE_SE_CD eq '0' || bidPlanAprvlDetail.PRCURE_SE_CD eq '2' }"> style="display: none;"</c:if> >
					            		<comTag:comCmcdCdValueComboBox id="P_PRCURE_DETAIL_SE_EMP_CD" name="P_PRCURE_DETAIL_SE_CD"   selectKey="${bidPlanAprvlDetail.PRCURE_DETAIL_SE_CD}" cdId="C00026" headerKey="" headerValue="선택" cond1="1" width="160"/>
		                        </span>
		                        <span id = "prcureDetailSeCdPrjDiv" <c:if test="${empty bidPlanAprvlDetail.PRCURE_SE_CD || bidPlanAprvlDetail.PRCURE_SE_CD eq '0' || bidPlanAprvlDetail.PRCURE_SE_CD eq '1' }"> style="display: none;"</c:if> >
					            		<comTag:comCmcdCdValueComboBox id="P_PRCURE_DETAIL_SE_PRJ_CD" name="P_PRCURE_DETAIL_SE_CD"   selectKey="${bidPlanAprvlDetail.PRCURE_DETAIL_SE_CD}" cdId="C00026" headerKey="" headerValue="선택" cond1="2" width="160"/>
		                        </span>
		                    </td>
		                </tr>
		                <tr>
		                    <th scope="row" class="bullet_orange">입찰명</th>
		                    <td colspan="3">
		                        <label for="P_BID_NM" class="blind">입찰명</label>
		                        <input type="text" id="P_BID_NM" name="P_BID_NM"  value="${bidPlanAprvlDetail.BID_NM}" maxlength="600">
		                    </td>
		                </tr>
		                <tr id="bidEngNmTr" <c:if test="${bidPlanAprvlDetail.INTRLBID_AT ne 'Y'}">style="display:none;"</c:if>>
		                    <th scope="row" class="bullet_orange">입찰명(영문)</th>
		                    <td colspan="3">
		                        <label for="P_BID_ENG_NM" class="blind">입찰명(영문)</label>
		                        <input type="text" id="P_BID_ENG_NM" name="P_BID_ENG_NM"  value="${bidPlanAprvlDetail.BID_ENG_NM}" maxlength="600" english>
		                    </td>
		                </tr>
		                <tr>
		                    <th scope="row">긴급입찰여부</th>
		                    <td>
		                    	<comTag:cmmnCdValueRadio name="P_EMRGNCY_BID_AT"  selectKey="${comFn:nvl(bidPlanAprvlDetail.EMRGNCY_BID_AT, 'Y')}" list="{'Y':'예', 'N':'아니오'}"/>
		                    </td>
		                   	<th scope="row">투찰내역서 제출필수여부</th>
		                	<td colspan="3">
		                    	<comTag:cmmnCdValueRadio name="P_BDDPR_DTSTMN_PRESENTN_AT"  selectKey="${comFn:nvl(bidPlanAprvlDetail.BDDPR_DTSTMN_PRESENTN_AT, 'Y')}" list="{'Y':'예', 'N':'아니오'}"/>
		                    </td>
		                </tr>
		                <tr>
		                    <th scope="row">산출내역서 제출필수여부</th>
		                    <td>
		                    	<comTag:cmmnCdValueRadio name="P_CARE_ESSNTL_AT"  selectKey="${comFn:nvl(bidPlanAprvlDetail.CARE_ESSNTL_AT, 'Y')}" list="{'Y':'예', 'N':'아니오'}"/>
		                    </td>
		                    <%-- 입찰구분이 [용역], [공사] 일 경우 활성화, [구매]일 경우 보여지진 않지만  "예"로 선택 --%>
		                    <th scope="row" id="mhrmlInclsAtTh"><c:if test="${bidPlanAprvlDetail.PRCURE_SE_CD ne '0'}">기자재포함 여부</c:if></th><%-- 기자재포함 여부 --%>
		                    <td>
		                    	<div id="mhrmlInclsAtDiv" <c:if test="${bidPlanAprvlDetail.PRCURE_SE_CD eq '0'}"> style="display: none;"</c:if>>
		                    		<comTag:cmmnCdValueRadio name="P_MHRML_INCLS_AT"  selectKey="${comFn:nvl(bidPlanAprvlDetail.MHRML_INCLS_AT, 'Y')}" list="{'Y':'예', 'N':'아니오'}"/>
		                    	</div>
		                    </td>
		                    <%-- 입찰구분이 [용역], [공사] 일 경우 활성화 끝 --%>
		                </tr>
		                <%-- 입찰구분이 [공사], [용역] 일 경우 활성화 --%>
		                <tr id="servcNdCntrwkScopeCnTr" <c:if test="${bidPlanAprvlDetail.PRCURE_SE_CD eq '0'}"> style="display: none;"</c:if>>
		                    <th scope="row" class="bullet_orange" id="servcNdCntrwkScopeCnTh">입찰범위</th>
		                    <td colspan="3">
		                        <label for="P_BID_SCOPE_CN" class="blind">입찰범위</label>
		                        <textarea id="P_BID_SCOPE_CN" name="P_BID_SCOPE_CN" style="width: 100%; height: 50px;" maxlength="4000">${bidPlanAprvlDetail.BID_SCOPE_CN}</textarea>
		                    </td>
		                </tr>
		                <%-- 국제입찰여부가 Y 이면 활성화 --%>
		                <tr id="servcNdCntrwkScopeEngCnTr" <c:if test="${bidPlanAprvlDetail.PRCURE_SE_CD eq '0' or bidPlanAprvlDetail.INTRLBID_AT ne 'Y'}"> style="display: none;"</c:if>>
		                    <th scope="row" class="bullet_orange" id="servcNdCntrwkScopeEngCnTh">입찰범위(영문)</th>
		                    <td colspan="3">
		                        <label for="P_BID_SCOPE_ENG_CN" class="blind">입찰범위(영문)</label>
		                        <textarea id="P_BID_SCOPE_ENG_CN" name="P_BID_SCOPE_ENG_CN" style="width: 100%; height: 50px;" maxlength="4000" english>${bidPlanAprvlDetail.BID_SCOPE_ENG_CN}</textarea>
		                    </td>
		                </tr>
		                <%-- 국제입찰여부가 Y 이면 활성화 END --%>
		                <tr id="servcNdCntrwkPdCnTr" <c:if test="${bidPlanAprvlDetail.PRCURE_SE_CD eq '0'}"> style="display: none;"</c:if>>
		                    <th scope="row" class="bullet_orange"  id="servcNdCntrwkPdCnTh">계약기간</th>
		                    <td colspan="3">
		                        <label for="P_CNTRCTPD_CN" class="blind">계약기간</label>
		                        <input type="text" id="P_CNTRCTPD_CN" name="P_CNTRCTPD_CN"  value="${bidPlanAprvlDetail.CNTRCTPD_CN}" maxlength="200">
		                    </td>
		                </tr>
		                <%-- 국제입찰여부가 Y 이면 활성화 --%>
		                <tr id="servcNdCntrwkPdEngCnTr" <c:if test="${bidPlanAprvlDetail.PRCURE_SE_CD eq '0' or bidPlanAprvlDetail.INTRLBID_AT ne 'Y'}"> style="display: none;"</c:if>>
		                    <th scope="row" class="bullet_orange"  id="servcNdCntrwkPdEngCnTh">계약기간(영문)</th>
		                    <td colspan="3">
		                        <label for="P_CNTRCTPD_ENG_CN" class="blind">계약기간(영문)</label>
		                        <input type="text" id="P_CNTRCTPD_ENG_CN" name="P_CNTRCTPD_ENG_CN"  value="${bidPlanAprvlDetail.CNTRCTPD_ENG_CN}" maxlength="200" english>
		                    </td>
		                </tr>
		                <%-- 국제입찰여부가 Y 이면 활성화 END --%>
		                <%-- 입찰구분이 [공사], [용역] 일 경우 활성화 끝 --%>
		                <tr>
		                    <th scope="row" class="bullet_orange">입찰한도액 (원)</th>
		                    <td colspan="3">
		                        <label for="P_BID_LMT_AMOUNT" class="blind">입찰한도액</label>
		                        <input type="text" class="tr" id="P_BID_LMT_AMOUNT" name="P_BID_LMT_AMOUNT" size="20" money value="${comFn:formatMoney(bidPlanAprvlDetail.BID_LMT_AMOUNT)}" maxlength="22">
		                    </td>
		                </tr>
				    </table>
				</div>
		
				<h4 class="tit">입찰방법</h4>
				<div class="view_area">
					<table>
				    	<colgroup>
				        	<col style="width: 15%;">						        
				        	<col style="width: 35%;">						        
				        	<col style="width: 15%;">						        
				        	<col style="width: 35%;">
				        </colgroup>
		                <tr>
		                    <th scope="row" class="bullet_orange">계약방법</th>
		                    <td>
		                        <div class="selectLayer2 w_120">
				            		<comTag:comCmcdCdValueComboBox id="P_CNTRCT_MTH_CD" name="P_CNTRCT_MTH_CD"   selectKey="${bidPlanAprvlDetail.CNTRCT_MTH_CD}" cdId="22026" cond1="BID" headerKey="" headerValue="선택" />
		                		</div>
		                    </td>
		                    <th scope="row" class="bullet_orange">낙찰자선정방법</th>
		                    <td>
		                        <div class="selectLayer2">
				            		<comTag:comCmcdCdValueComboBox id="P_SCSBID_MTH_CD" name="P_SCSBID_MTH_CD"   selectKey="${bidPlanAprvlDetail.SCSBID_MTH_CD}" cdId="C00006"  headerKey="" headerValue="선택" />
		                		</div>
		                    </td>
		                </tr>
		                <tr>
		                    <th scope="row" class="bullet_orange">복수예가여부</th>
		                    <td>
		                        <div class="selectLayer2 w_120">
				            		<comTag:comCmcdCdValueComboBox id="P_COMPNO_PRDPRC_SE_CD" name="P_COMPNO_PRDPRC_SE_CD"   selectKey="${bidPlanAprvlDetail.COMPNO_PRDPRC_SE_CD}" cdId="22079" headerKey="" headerValue="선택" />
		                		</div>
		                    </td>
		                    <th scope="row" <c:if test="${bidPlanAprvlDetail.SCSBID_MTH_CD eq '31'}"> class="bullet_orange"</c:if> id="adupRateTh"><c:if test="${bidPlanAprvlDetail.SCSBID_MTH_CD eq '31'}">낙찰하한율</c:if></th>
		                    <td id="adupRateTd">
			                    <%-- 낙찰자선정방식이 [적격심사]가 아닐 경우 활성화 --%>
		                    	<div id="sclwRtDiv" <c:if test="${bidPlanAprvlDetail.SCSBID_MTH_CD ne '31'}"> style="display:none;"</c:if>>
			                        <label for="P_SCSBID_LWLT_RT" class="blind">낙찰하한율</label>
			                        <input type="text" class="tr PT3" id="P_SCSBID_LWLT_RT" name="P_SCSBID_LWLT_RT" size="20" value="${bidPlanAprvlDetail.SCSBID_LWLT_RT}" maxlength="10" numeric>
		                    	</div>
		                    	<%-- 낙찰자선정방식이 [적격심사]가 아닐 경우 활성화 End--%>
		                    </td>
		                </tr>
		                <tr>
		                    <th scope="row" class="bullet_orange">입찰설명회여부</th><%-- 22420 --%>
		                    <td>
		                    	<comTag:comCmcdCdValueRadio name="P_BID_DC_PEO_CD"  selectKey="${bidPlanAprvlDetail.BID_DC_PEO_CD}" cdId="22420" />
		                    </td>
		                    <%-- 입찰설명회 여부가 [예(의무)], [예(비의무)] 일 경우에 활성화 --%>
		                    <th scope="row" id="bidDcPeoPlaceNmTh"><c:if test="${bidPlanAprvlDetail.BID_DC_PEO_CD ne 'BPNN'}">입찰설명회장소</c:if></th> 
		                    <td id="bidDcPeoPlaceNmTd">
		                    	<div id="bidDcPeoPlaceNmDiv" <c:if test="${bidPlanAprvlDetail.BID_DC_PEO_CD eq 'BPNN'}">style="display:none;"</c:if>>
			                        <label for="P_BID_DC_PEO_PLACE_NM" class="blind">입찰설명회장소</label>
			                        <input type="text" id="P_BID_DC_PEO_PLACE_NM" name="P_BID_DC_PEO_PLACE_NM" size="20" value="${bidPlanAprvlDetail.BID_DC_PEO_PLACE_NM}" maxlength="100">
		                    	</div>
		                    </td>
		                    <%-- 입찰설명회 여부가 [예(의무)], [예(비의무)] 일 경우에 활성화 End --%>
		                </tr>
		                <tr>
		                    <th scope="row">입찰보증금 납부형태</th>
		                    <td colspan="3">
		                    	<label for="P_BID_GTN_PAY_STLE_CN" class="blind">입찰보증금 납부형태</label>
		                        <textarea id="P_BID_GTN_PAY_STLE_CN" name="P_BID_GTN_PAY_STLE_CN" style="width: 100%; height: 50px;" maxlength="2000"><c:if test="${empty bidPlanAprvlDetail.PBLANC_NO}">협력단 대외무상협력사업에관한조달및계약규정시행세칙 제26조에 의거 입찰금액의 100분의 5 이상 현금 또는 보증보험증권 등으로 제출하되, 협력단과 계약이행 실적이 있는 조달협력업체 또는 국가가 기본재산의 50%이상 출연한 법인은 별도의 입찰보증금 납부없이 입찰참가신청서상에 입찰보증금 지급각서로 대체하며, 낙찰자가 계약을 체결하지 않을 시 입찰보증금은 우리 협력단에 귀속됩니다.</c:if><c:if test="${not empty bidPlanAprvlDetail.PBLANC_NO}">${bidPlanAprvlDetail.BID_GTN_PAY_STLE_CN}</c:if></textarea>
		                    </td>
		                </tr>
		                <%-- 계약방법이 [일반경쟁], [제한경쟁] 일 경우 활성화 --%>
		                <tr id="bipaQualfTr" <c:if test="${bidPlanAprvlDetail.CNTRCT_MTH_CD ne '10000' and bidPlanAprvlDetail.CNTRCT_MTH_CD ne '10002'}"> style="display:none;"</c:if> >
		                    <th scope="row">입찰참가자격</th>
		                    <td colspan="3">
		                    	<label for="P_BID_PARTCPT_QUALF_CN" class="blind">입찰참가자격</label>
		                        <textarea id="P_BID_PARTCPT_QUALF_CN" name="P_BID_PARTCPT_QUALF_CN" style="width: 100%; height: 50px;" maxlength="4000">${bidPlanAprvlDetail.BID_PARTCPT_QUALF_CN}</textarea>
		                    </td>
		                </tr>
		                <%-- 계약방법이 [일반경쟁], [제한경쟁] 일 경우 활성화 End --%>
		                <tr>
		                    <th scope="row" class="bullet_orange">공동도급</th>
		                    <td colspan="3">
		                        <div class="selectLayer2 w_120">
				            		<comTag:comCmcdCdValueComboBox id="P_COPERTN_SPLDMD_DUTY_SE_CD" name="P_COPERTN_SPLDMD_DUTY_SE_CD"   selectKey="${bidPlanAprvlDetail.COPERTN_SPLDMD_DUTY_SE_CD}" cdId="22035" headerKey="" headerValue="선택" />
		                		</div>
		                    </td>
		                </tr>
		                <%-- 낙찰자선정방법이 [협상에 의한 계약(기술평가)], [협상에 의한 계약(기술가격종합평가)], [기술.가격분리(기술가격입찰동시-최저가)] 일 경우 활성화 --%>
		                <tr id="prprTr" <c:if test="${bidPlanAprvlDetail.SCSBID_MTH_CD ne '40' }"> style="display: none;"</c:if>>
		                    <th scope="row">제안요약본제출방법</th>
		                    <td>
		                        <div class="selectLayer2 w_120">
				            		<comTag:comCmcdCdValueComboBox id="P_PRPR_MTH_CD" name="P_PRPR_MTH_CD"   selectKey="${bidPlanAprvlDetail.PRPR_MTH_CD}" cdId="22421" headerKey="" headerValue="선택" />
		                		</div>
		                    </td>
		                    <th scope="row">온라인제출 필수여부</th>
		                    <td>
		                   		<comTag:cmmnCdValueRadio name="P_ONPR_ESSNTL_AT"  selectKey="${comFn:nvl(bidPlanAprvlDetail.ONPR_ESSNTL_AT, 'Y')}" list="{'Y':'예', 'N':'아니오'}"/>
		                    </td>
		                </tr>
		                <tr id="tchqvlnChargerTd" <c:if test="${bidPlanAprvlDetail.SCSBID_MTH_CD ne '40'}"> style="display:none;"</c:if>>
		                    <th id="tchqvlnChargerTh" scope="row" class="bullet_orange">기술평가 담당자</th>
		                    <td colspan="3">
		                        <label for="userNm" class="blind">기술평가 담당자</label>
		                        <input type="text" class="float-left w280" id="userNm" name="P_TCHQVLN_CHARGER_NM" value="${bidPlanAprvlDetail.TCHQVLN_CHARGER_NM}" >
		                        <input type="hidden" id="userId" name="P_TCHQVLN_CHARGER_ID" value="${bidPlanAprvlDetail.TCHQVLN_CHARGER_ID}">
		                        <button type="button" class="btn btn_02 btn_sch vert" id="chargerBtn">검색</button>
		                    </td>
		                </tr>
		                <%-- 낙찰자선정방법이 [협상에 의한 계약(기술평가)], [협상에 의한 계약(기술가격종합평가)], [기술.가격분리(기술가격입찰동시-최저가)] 일 경우 활성화 END --%>
				    </table>
				</div>
		
				<%-- 계약방법이 [지명경쟁] 일 경우 활성화 --%>
			        
		        <div id="nmenSearchDiv" <c:if test="${bidPlanAprvlDetail.CNTRCT_MTH_CD ne '10001' and bidPlanDetail.CONT_MTCD ne '10005'}"> style="display: none;"</c:if> >
		        	<div class="tit_area">
				        <h4 class="tit" style="clear: both;">지명업체선택</h4>
				        <div class="btn_right">
				            <button type="button" class="btn btn_02_auto btn_c2" id="nmenSearchBtn">지명업체 찾기</button>
				            <button type="button" class="btn btn_02_auto btn_c2" id="nmenDeleteBtn">지명업체 삭제</button>
				        </div>
					</div>
			        <div class="view_area" style="margin-bottom: 30px;"> 
			            <table class="tableList" summary="지명업체선택 수정 입니다.">
			                <caption>지명업체선택</caption>
			                <colgroup>
			                    <col style="width: 5%;">
			                   	<col style="width: 20%;">
			                   	<col style="width: auto;">
			                   	<col style="width: 20%;">
			                </colgroup>
			                <thead>
			                <tr class="line">
			                    <th class="txtc">
			                    	<label for="nmenChoiseAllCbx" class="blind">체크박스</label>
			                    	<input type="checkbox" id="nmenChoiseAllCbx" name="nmenChoiseCbx" onclick="FwkCmmnUtil.setAllCheck('nmenChoiseAllCbx','nmenChoiseCbx');">
			                    </th>
			                    <th class="txtc">사업자번호</th>
			                    <th class="txtc">업체명</th>
			                    <th class="txtc">대표자명</th>
			                </tr>
			                </thead>
			                <tbody id="nmenChoiseHiddTbdy">
			                	<tr style="display: none;">
									<td class="txtc">
										<label for="nmenChoiseCbx" class="blind">체크박스</label>
										<input type="checkbox" id="nmenChoiseCbx" name="nmenChoiseCbx">
										<input type="hidden" name="P_ENTRPS_REGIST_NO" disabled>
									</td>
									<td bizrNo class="txtc"></td>
									<td entrpsNm ></td>
									<td rprsntvNm ></td>
								</tr>
			                </tbody>
			                <tbody id="nmenChoiseShowTbdy">
			                	<c:forEach var="data" items="${bidNmfpcEntrpsList}" varStatus="status" >
									<tr>
										<td class="txtc">
											<label for="nmenChoiseCbx${status.count}" class="blind">체크박스</label>
											<input type="checkbox" id="nmenChoiseCbx${status.count}" name="nmenChoiseCbx">
											<input type="hidden" name="P_ENTRPS_REGIST_NO" value="${data.ENTRPS_REGIST_NO}" >
											<c:set var="cnt" value="${status.count}" scope="request" />
										</td>
										<td class="txtc">${data.BIZRNO}</td>
										<td >${data.ENTRPS_NM}</td>
										<td >${data.RPRSNTV_NM}</td>
									</tr>
								</c:forEach>
			                </tbody>
			                <tbody>
				               	<tr class="row" id="nmenChoiseEmpty" <c:if test="${not empty bidNmfpcEntrpsList}"> style="display:none;"</c:if>>
				               		<td colspan="4" class="txtc">선택된 업체가 없습니다.<input type="hidden" id="cnt" value="${cnt}"></td>
				               	</tr>
			               	</tbody>
			            </table>
		            </div>
		        </div>
		        <%-- 계약방법이 [지명경쟁] 일 경우 활성화 End --%>
		
				<h4 class="tit">계약조건</h4>
				<div class="view_area">
					<table>
				    	<colgroup>
				        	<col style="width: 15%;">						        
				        	<col style="width: 35%;">						        
				        	<col style="width: 15%;">						        
				        	<col style="width: 35%;">
				        </colgroup>
				        <tr class="line">
		                    <th scope="row" class="bullet_orange">지체상금률 (%)</th>
		                    <td>
		                    	<label for="P_DFRCMPST_RT" class="blind">지체상금률</label>
		                        <input type="text" class="tr PT2" id="P_DFRCMPST_RT" name="P_DFRCMPST_RT" size="20" value="<c:if test="${empty bidPlanAprvlDetail.PBLANC_NO}">0.25</c:if><c:if test="${not empty bidPlanAprvlDetail.PBLANC_NO}">${bidPlanAprvlDetail.DFRCMPST_RT}</c:if>" numeric maxlength="22"> /1,000
		                    </td>
		                    <th scope="row" class="bullet_orange">하자이행보증금률 (%)</th>
		                    <td>
		                        <label for="P_FLAW_FLFL_GTN_RT" class="blind">하자이행보증금률</label>
		                        <input type="text" class="tr PT2" id="P_FLAW_FLFL_GTN_RT" name="P_FLAW_FLFL_GTN_RT" size="20" value="<c:if test="${empty bidPlanAprvlDetail.PBLANC_NO}">3</c:if><c:if test="${not empty bidPlanAprvlDetail.PBLANC_NO}">${bidPlanAprvlDetail.FLAW_FLFL_GTN_RT}</c:if>" numeric maxlength="22">
		                    </td>
		                </tr>
		                <tr>
		                    <th scope="row" class="bullet_orange">계약보증금률 (%)</th>
		                    <td>
		                        <label for="P_CRYMY_RT" class="blind">계약보증금률</label>
		                        <input type="text" class="tr PT0" id="P_CRYMY_RT" name="P_CRYMY_RT" size="20" value="<c:if test="${empty bidPlanAprvlDetail.PBLANC_NO}">20</c:if><c:if test="${not empty bidPlanAprvlDetail.PBLANC_NO}">${bidPlanAprvlDetail.CRYMY_RT}</c:if>" numeric maxlength="22">
		                    </td>
		                    <%-- 기자재포함여부가 [예] 일때 활성화 --%>
		                    <th scope="row" id="thngDelyCndCdTh"><c:if test="${bidPlanAprvlDetail.MHRML_INCLS_AT eq 'Y'}">물품인도조건</c:if></th>
		                    <td>
			                    <div id="thngDelyCndCdDiv" <c:if test="${bidPlanAprvlDetail.MHRML_INCLS_AT ne 'Y'}"> style="display:none;"</c:if>>
			                        <div class="selectLayer2 w_120">
					            		<comTag:comCmcdCdValueComboBox id="P_THNG_DELY_CND_CD" name="P_THNG_DELY_CND_CD"   selectKey="${bidPlanAprvlDetail.THNG_DELY_CND_CD}" cdId="C00013" headerKey="" headerValue="선택" />
			                		</div>
			                    </div>
		                    </td>
		                    <%-- 기자재포함여부가 [예] 일때 활성화 END--%>
		                </tr>
		                <%-- 기자재포함여부가 [예] 일때 활성화 --%>
		                <tr id="dvyfgPlaceTr" <c:if test="${bidPlanAprvlDetail.MHRML_INCLS_AT ne 'Y'}"> style="display:none;"</c:if>>
		                    <th scope="row" class="bullet_orange">납품장소</th>
		                    <td colspan="3">
		                    	<label for="P_DVYFG_PLACE_NM" class="blind">납품장소</label>
		                        <textarea id="P_DVYFG_PLACE_NM" name="P_DVYFG_PLACE_NM" style="width: 100%; height: 50px;" <c:if test="${bidPlanAprvlDetail.MHRML_INCLS_AT ne 'Y'}"> disabled</c:if> maxlength="100">${bidPlanAprvlDetail.DVYFG_PLACE_NM}</textarea>
		                    </td>
		                </tr>
		                <tr id="dvyfgPdCnTr" <c:if test="${bidPlanAprvlDetail.MHRML_INCLS_AT ne 'Y'}"> style="display:none;"</c:if>>
		                    <th scope="row" class="bullet_orange">납품기한</th>
		                    <td colspan="3">
		                    	<label for="P_DVYFG_PD_CN" class="blind">납품기한</label>
		                    	<textarea id="P_DVYFG_PD_CN" name="P_DVYFG_PD_CN" style="width: 100%; height: 50px;" <c:if test="${bidPlanAprvlDetail.MHRML_INCLS_AT ne 'Y'}"> disabled</c:if> maxlength="2000">${bidPlanAprvlDetail.DVYFG_PD_CN}</textarea>
		                    </td>
		                </tr>
		                <%-- 기자재포함여부가 [예] 일때 활성화 END--%>
		                <tr  id="instlTmlmtCnTr" <c:if test="${bidPlanAprvlDetail.MHRML_INCLS_AT ne 'Y'}"> style="display:none;"</c:if>>
		                    <th scope="row" class="bullet_orange" >설치기한</th>
		                    <td colspan="3">
		                    	<label for="P_INSTL_TMLMT_CN" class="blind">설치기한</label>
		                    	<textarea id="P_INSTL_TMLMT_CN" name="P_INSTL_TMLMT_CN"  style="width: 100%; height: 50px;" <c:if test="${bidPlanAprvlDetail.MHRML_INCLS_AT ne 'Y'}"> disabled</c:if> maxlength="4000">${bidPlanAprvlDetail.INSTL_TMLMT_CN}</textarea>
		                    </td>
		                </tr>
				    </table>
				</div>
		
				<h4 class="tit">입찰진행순서</h4>
				<div class="view_area">
					<table>
				    	<colgroup>
				        	<col style="width: 15%;">						        
				        	<col style="width: 85%;">						        
				        </colgroup>
				        <tr class="line">
		                    <th scope="row" class="bullet_orange">입찰공고일시</th>
		                    <td>
		                        <label for="P_PBLANC_DT" class="blind">입찰공고일자</label>
				                <input type="text"id="P_PBLANC_DT" name="P_PBLANC_DT" date  value="${comFn:formatDate(bidPlanAprvlDetail.PBLANC_DT,'yyyyMMddHHmmss','yyyy-MM-dd')}" class="w170">
		                        <span class="wave">&nbsp;</span>
		                        <label for="P_BID_PBLANC_HH" class="blind">입찰공고시간</label>
		                        <input type="text" class="float-left" id="P_BID_PBLANC_HH" name="P_BID_PBLANC_HH" style="width: 52px" maxlength="2" numeric  value="${comFn:formatDate(bidPlanAprvlDetail.PBLANC_DT,'yyyyMMddHHmmss','HH')}"><span class="wave">:</span>
		                        <label for="P_BID_PBLANC_MM" class="blind">입찰공고분</label>
		                        <input type="text" class="float-left mr5" id="P_BID_PBLANC_MM" name="P_BID_PBLANC_MM" style="width: 52px" maxlength="2" numeric value="${comFn:formatDate(bidPlanAprvlDetail.PBLANC_DT,'yyyyMMddHHmmss','mm')}" >
		                        <div class="radio_box"><label for="day5" class="vam"><input type="radio" name="P_BID_PBLANC_DT_CHOISE" value="5" id="day5" class="mr5 vam">5일</label></div>
		                        <div class="radio_box"><label for="day7" class="vam"><input type="radio" name="P_BID_PBLANC_DT_CHOISE" value="7" id="day7" class="mr5 vam">7일</label></div>
		                        <div class="radio_box"><label for="day10" class="vam"><input type="radio" name="P_BID_PBLANC_DT_CHOISE" value="10" id="day10" class="mr5 vam">10일</label></div>
		                        <div class="radio_box"><label for="day15" class="vam"><input type="radio" name="P_BID_PBLANC_DT_CHOISE" value="15" id="day15" class="mr5 vam">15일</label></div>
		                        <div class="radio_box"><label for="day40" class="vam"><input type="radio" name="P_BID_PBLANC_DT_CHOISE" value="40" id="day40" class="mr5 vam">40일</label></div>
		                    </td>
		                </tr>
		                <%-- 입찰설명회 여부가 [예(의무)], [예(비의무)] 일 경우에 활성화 --%>
		                <tr id="bidDcPeoDtTr" <c:if test="${bidPlanAprvlDetail.BID_DC_PEO_CD eq 'BPNN'}"> style="display:none;"</c:if>>
		                    <th scope="row" class="bullet_orange">입찰설명회일시</th>
		                    <td>
	                        	<label for="P_BID_DC_PEO_DT" class="blind">입찰설명회일자</label>
			                    <input type="text"id="P_BID_DC_PEO_DT" name="P_BID_DC_PEO_DT" date  <c:if test="${not empty bidPlanAprvlDetail.BID_DC_PEO_DT}"> value="${comFn:formatDate(bidPlanAprvlDetail.BID_DC_PEO_DT,'yyyyMMddHHmmss','yyyy-MM-dd')}"</c:if>  class="w170">
		                        <span class="wave">&nbsp;</span>
		                        <label for="P_BID_DC_PEO_HH" class="blind">입찰설명회시간</label>
		                        <input type="text" class="float-left" id="P_BID_DC_PEO_HH" name="P_BID_DC_PEO_HH" style="width: 52px" maxlength="2" numeric <c:if test="${not empty bidPlanAprvlDetail.BID_DC_PEO_DT}"> value="${comFn:formatDate(bidPlanAprvlDetail.BID_DC_PEO_DT,'yyyyMMddHHmmss','HH')}"</c:if>><span class="wave">:</span>
		                        <label for="P_BID_DC_PEO_MM" class="blind">입찰설명회분</label>
		                        <input type="text" class="float-left" id="P_BID_DC_PEO_MM" name="P_BID_DC_PEO_MM" style="width: 52px" maxlength="2" numeric <c:if test="${not empty bidPlanAprvlDetail.BID_DC_PEO_DT}"> value="${comFn:formatDate(bidPlanAprvlDetail.BID_DC_PEO_DT,'yyyyMMddHHmmss','mm')}"</c:if>>
		                    </td>
		                </tr>
		                <%-- 입찰설명회 여부가 [예(의무)], [예(비의무)] 일 경우에 활성화 End --%>
		                <tr>
		                    <th scope="row" class="bullet_orange">참가신청서 제출기간</th>
		                    <td>
	                        	<label for="P_PARE_BEGIN_DT" class="blind">참가신청서 제출기간 시작일</label>
			                    <input type="text"id="P_PARE_BEGIN_DT" name="P_PARE_BEGIN_DT" date  value="${comFn:formatDate(bidPlanAprvlDetail.PARE_BEGIN_DT,'yyyyMMddHHmmss','yyyy-MM-dd')}" class="w170">
		                        <span class="wave">&nbsp;</span>
		                        <label for="P_PARE_BEGIN_HH" class="blind">참가신청서 제출기간 시작 시간</label>
		                        <input type="text" class="float-left" id="P_PARE_BEGIN_HH" name="P_PARE_BEGIN_HH" style="width: 52px" maxlength="2" numeric  value="${comFn:formatDate(bidPlanAprvlDetail.PARE_BEGIN_DT,'yyyyMMddHHmmss','HH')}"><span class="wave">:</span>
		                        <label for="P_PARE_BEGIN_MM" class="blind">참가신청서 제출기간 시작 분</label>
		                        <input type="text" class="float-left" id="P_PARE_BEGIN_MM" name="P_PARE_BEGIN_MM" style="width: 52px" maxlength="2" numeric  value="${comFn:formatDate(bidPlanAprvlDetail.PARE_BEGIN_DT,'yyyyMMddHHmmss','mm')}">
		                        <span class="wave">~</span>
	                        	<label for="P_PARE_END_DT" class="blind">참가신청서 제출기간 종료일</label>
			                    <input type="text"id="P_PARE_END_DT" name="P_PARE_END_DT" date  value="${comFn:formatDate(bidPlanAprvlDetail.PARE_END_DT,'yyyyMMddHHmmss','yyyy-MM-dd')}" class="w170">
		                        <span class="wave">&nbsp;</span>
		                        <label for="P_PARE_END_HH" class="blind">참가신청서 제출기간 종료 시간</label>
		                        <input type="text" class="float-left" id="P_PARE_END_HH" name="P_PARE_END_HH" style="width: 52px" maxlength="2" numeric  value="${comFn:formatDate(bidPlanAprvlDetail.PARE_END_DT,'yyyyMMddHHmmss','HH')}"><span class="wave">:</span>
		                        <label for="P_PARE_END_MM" class="blind">참가신청서 제출기간 종료 분</label>
		                        <input type="text" class="float-left" id="P_PARE_END_MM" name="P_PARE_END_MM" style="width: 52px" maxlength="2" numeric  value="${comFn:formatDate(bidPlanAprvlDetail.PARE_END_DT,'yyyyMMddHHmmss','mm')}">
		                    </td>
		                </tr>
		                <tr>
		                    <th scope="row" class="bullet_orange">입찰서 제출기간</th>
		                    <td>
	                        	<label for="P_BIPA_PRESENTN_BEGIN_DT" class="blind">입찰서 제출기간 시작일</label>
			                    <input type="text"id="P_BIPA_PRESENTN_BEGIN_DT" name="P_BIPA_PRESENTN_BEGIN_DT" date  value="${comFn:formatDate(bidPlanAprvlDetail.BIPA_PRESENTN_BEGIN_DT,'yyyyMMddHHmmss','yyyy-MM-dd')}" class="w170">
		                        <span class="wave">&nbsp;</span>
		                        <label for="P_BIPA_PRESENTN_BEGIN_HH" class="blind">입찰서 제출기간 시작 시간</label>
		                        <input type="text" class="float-left" id="P_BIPA_PRESENTN_BEGIN_HH" name="P_BIPA_PRESENTN_BEGIN_HH" style="width: 52px" maxlength="2" numeric value="${comFn:formatDate(bidPlanAprvlDetail.BIPA_PRESENTN_BEGIN_DT,'yyyyMMddHHmmss','HH')}" ><span class="wave">:</span>
		                        <label for="P_BIPA_PRESENTN_BEGIN_MM" class="blind">입찰서 제출기간 시작 분</label>
		                        <input type="text" class="float-left" id="P_BIPA_PRESENTN_BEGIN_MM" name="P_BIPA_PRESENTN_BEGIN_MM" style="width: 52px" maxlength="2" numeric value="${comFn:formatDate(bidPlanAprvlDetail.BIPA_PRESENTN_BEGIN_DT,'yyyyMMddHHmmss','mm')}" >
		                        <span class="wave">~</span>
	                        	<label for="P_BIPA_PRESENTN_END_DT" class="blind">입찰서 제출기간 종료일</label>
			                    <input type="text"id="P_BIPA_PRESENTN_END_DT" name="P_BIPA_PRESENTN_END_DT" date  value="${comFn:formatDate(bidPlanAprvlDetail.BIPA_PRESENTN_END_DT,'yyyyMMddHHmmss','yyyy-MM-dd')}" class="w170">
		                        <span class="wave">&nbsp;</span>
		                        <label for="P_BIPA_PRESENTN_END_HH" class="blind">입찰서 제출기간 종료 시간</label>
		                        <input type="text" class="float-left" id="P_BIPA_PRESENTN_END_HH" name="P_BIPA_PRESENTN_END_HH" style="width: 52px" maxlength="2" numeric value="${comFn:formatDate(bidPlanAprvlDetail.BIPA_PRESENTN_END_DT,'yyyyMMddHHmmss','HH')}" ><span class="wave">:</span>
		                        <label for="P_BIPA_PRESENTN_END_MM" class="blind">입찰서 제출기간 종료 분</label>
		                        <input type="text" class="float-left" id="P_BIPA_PRESENTN_END_MM" name="P_BIPA_PRESENTN_END_MM" style="width: 52px" maxlength="2" numeric value="${comFn:formatDate(bidPlanAprvlDetail.BIPA_PRESENTN_END_DT,'yyyyMMddHHmmss','mm')}" >
		                    </td>
		                </tr>
		                <tr>
		                    <th scope="row" class="bullet_orange">개찰일시</th>
		                    <td>
	                        	<label for="P_OPENG_DT" class="blind">개찰일자</label>
			                    <input type="text" id="P_OPENG_DT" name="P_OPENG_DT" date  value="${comFn:formatDate(bidPlanAprvlDetail.OPENG_DT,'yyyyMMddHHmmss','yyyy-MM-dd')}" class="w170">
		                        <span class="wave">&nbsp;</span>
		                        <label for="P_OPENG_HH" class="blind">개찰 시간</label>
		                        <input type="text" class="float-left" id="P_OPENG_HH" name="P_OPENG_HH" style="width: 52px" maxlength="2" numeric value="${comFn:formatDate(bidPlanAprvlDetail.OPENG_DT,'yyyyMMddHHmmss','HH')}" ><span class="wave">:</span>
		                        <label for="P_OPENG_MM" class="blind">개찰 분</label>
		                        <input type="text" class="float-left" id="P_OPENG_MM" name="P_OPENG_MM" style="width: 52px" maxlength="2" numeric value="${comFn:formatDate(bidPlanAprvlDetail.OPENG_DT,'yyyyMMddHHmmss','mm')}" >
		                    </td>
		                </tr>
				    </table>
				</div>
		
				<div id="biPrDiv" <c:if test="${bidPlanAprvlDetail.PRCURE_SE_CD ne '0'}">style="display: none;"</c:if>>
					<div class="tit_area">
			        	<h4 class="tit" style="clear: both;">입찰품목정보</h4>
			        	<div class="btn_right">
				            <button type="button" class="btn btn_02_auto btn_c2" id="biprInfoSearchBtn">입찰품목 찾기</button>
				            <button type="button" class="btn btn_02_auto btn_c2" id="biprInfoDeleteBtn">입찰픔목 삭제</button>
				        </div>
			        </div>
		        	<div class="view_area" style="margin-bottom: 30px;">
			            <table>
			                <caption>입찰품목정보</caption>
			                <colgroup>
			                    <col width="5%"/>
			                   	<col width="20%"/>
			                   	<col width="25%"/>
			                   	<col width="*"/>
			                   	<col width="10%"/>
			                   	<col width="10%"/>
			                </colgroup>
			                <thead>
			                <tr>
			                    <th class="txtc">
			                    	<label for="biprInfoAllCbx" class="blind">체크박스</label>
			                    	<input type="checkbox" id="biprInfoAllCbx" name="biprInfoCbx" onclick="FwkCmmnUtil.setAllCheck('biprInfoAllCbx','biprInfoCbx');">
			                    </th>
			                    <th class="txtc">품명번호</th>
			                    <th class="txtc">품명</th>
			                    <th class="txtc">참조사항</th>
			                    <th class="txtc">단위</th>
			                    <th class="txtc">수량</th>
			                </tr>
			                </thead>
			                <tbody id="biprInfoHiddTbdy">
			                	<tr style="display: none;">
									<td  class="txtc">
										<label for="biprInfoCbx" class="blind">체크박스</label>
										<input type="checkbox" id="biprInfoCbx" name="biprInfoCbx">
										<input type="hidden" name="P_THNG_NO" disabled>
										<input type="hidden" name="P_QLY_NM" disabled>
									</td>
									<td g2bPrdlstCd class="txtc"></td>
									<td qlyNm></td>
									<td>
										<label for="P_PRDLST_REFRN_CN" class="blind">참조사항</label>
										<input type="text" id="P_PRDLST_REFRN_CN" name="P_PRDLST_REFRN_CN" style="width: 90%" disabled maxlength="100">
									</td>
									<td>
										<label for="P_G2B_UNIT_NM" class="blind">단위</label>
										<input type="text" id="P_G2B_UNIT_NM" name="P_G2B_UNIT_NM" style="width: 90%" disabled maxlength="50">
									</td>
									<td>
										<label for="P_PRDLST_QY" class="blind">수량</label>
										<input type="text" id="P_PRDLST_QY" name="P_PRDLST_QY" style="width: 90%" money disabled  maxlength="22">
									</td>
								</tr>
			                </tbody>
			                <tbody id="biprInfoShowTbdy">
			                	<tr style="display: none;"><td></td></tr>
			                	<c:forEach var="data" items="${bidPrdlsList}" varStatus="status" >
								<tr>
									<td class="txtc">
										<label for="biprInfoCbx${status.count }" class="blind">체크박스</label>
										<input type="checkbox" id="biprInfoCbx${status.count }" name="biprInfoCbx">
										<input type="hidden" name="P_THNG_NO" value="${data.THNG_NO}" >
										<input type="hidden" name="P_QLY_NM" value="${data.QLY_NM}" >
									</td>
									<td class="txtc">${data.THNG_NO}</td>
									<td>${data.QLY_NM}</td>
									<td>
										<label for="P_PRDLST_REFRN_CN" class="blind">참조사항</label>
										<input type="text" id="P_PRDLST_REFRN_CN" name="P_PRDLST_REFRN_CN" style="width: 90%" maxlength="100" value="${data.PRDLST_REFRN_CN}">
									</td>
									<td>
										<label for="P_G2B_UNIT_NM" class="blind">단위</label>
										<input type="text" id="P_G2B_UNIT_NM" name="P_G2B_UNIT_NM" style="width: 90%" maxlength="50" value="${data.G2B_UNIT_NM}">
									</td>
									<td>
										<label for="P_PRDLST_QY" class="blind">수량</label>
										<input type="text" id="P_PRDLST_QY" name="P_PRDLST_QY" style="width: 90%" money maxlength="22" value="${comFn:formatMoney(data.PRDLST_QY)}">
									</td>
								</tr>
								</c:forEach>
			                </tbody>
			                <tbody>
								<tr class="row" id="biprInfoEmpty" <c:if test="${ not empty bidPrdlsList}"> style="display:none;"</c:if>>
				               		<td colspan="6" class="txtc">선택된 입찰품목이 없습니다.</td>
				               	</tr>
			               	</tbody>
			            </table>
		            </div>
		        </div>
		
				<h4 class="tit">첨부파일 (입찰과 관련된 파일을 첨부)</h4>
				<div class="view_area">
					<table id="fileTable">
				    	<colgroup>
				        	<col style="width: 15%;">						        
				        	<col style="width: 85%;">
				        </colgroup>
			            <tr>
		                	<th scope="row" class="vtop">첨부파일</th>
							<td class="vtop">
							<span class="stD">
		                    	<button type="button"  class="btn btn_02 btn_sch" id="fileBtn" style="float: right;">추가</button> 
		                    </span>
		                    	<div id="fileRow" style="display: none; height: 30px;">
		                    		<input type="file" name="" style="width: 80%;height: 24px;" >
				                   	<span class="stD"> 
		                    			<button type="button" class="btn btn_02 btn_sch" style="display: inline-block; margin-top: 3px;" onclick="rowDel(this)">삭제</button>
		                    		</span>
		                    	</div>
		                    	<div id="fileViewDiv" style="width: 680px; line-height: 30px;">
		                    		<c:if test="${not empty bidAtchDocList }">
					            		<c:forEach var="data" items="${bidAtchDocList }" varStatus="status">
					            			<div style="height: 30px;"> 
				                   				<a href="javascript:pageObj.download('${data.ATCHMNFL_SN}');" class="attfile">${data.ATCHMNFL_NM }</a>
				                   				<span class="stD">
					                   				<button type="button" class="btn btn_02 btn_sch" style="display: inline-block; margin-top: 3px;" onclick="fileDel(this,'${data.ATCHMNFL_SN }')">삭제</button>
					                   			</span> 
				                    		</div>
					            		</c:forEach>
					            	</c:if>
		                    	</div>
		                    	<div id="fileDiv" style="width: 680px; line-height: 30px;">
		                    	</div>
		                    </td>	
			             </tr>
				    </table>
				</div>
		
				<h4 class="tit">담당자 정보</h4>
				<div class="view_area">
					<table>
				    	<colgroup>
				        	<col style="width: 15%;">						        
				        	<col style="width: 35%;">						        
				        	<col style="width: 15%;">						        
				        	<col style="width: 35%;">
				        </colgroup>
			        	<tr class="line">
		                    <th scope="row">입찰담당자</th>
		                    <td>
		                    	${bidPlanAprvlDetail.BID_CHARGER_NM}
		                    </td>
		                    <th scope="row">사업담당자(의뢰자)</th>
		                    <td>
		                        ${bidPlanAprvlDetail.BID_CLIENT_NM}
		                    </td>
		                </tr>
		                <tr>
		                    <th scope="row">입찰담당자 이메일</th>
		                    <td>
		                    	${bidPlanAprvlDetail.BID_CHARGER_EMAIL_ADRES}
		                    </td>
		                    <th scope="row">사업담당자 이메일</th>
		                    <td>
		                        ${bidPlanAprvlDetail.BID_CLIENT_EMAIL_ADRES}
		                    </td>
		                </tr>
				    </table>
				</div>
				<div class="btn_wrap view_btn">
					<button type="button" class="btn btn_02 btn_revise js_UpdateBtn" id="UpdateBtn" >저장</button>
					<button type="button" class="btn btn_02 btn_sch js_cancelBtn" id="cancelBtn" >취소</button>
				</div>
			</div>
		</form>
	</div>
</div> <!--// content E-->
<form id="detailFrm" method="POST" action="">
	<input type="hidden" name="resourceName" value="${param.resourceName}">
	<input type="hidden" name="P_PBLANC_NO" value="${bidPlanAprvlDetail.PBLANC_NO}" >
	<input type="hidden" name="P_PBLANC_ODR" value="${bidPlanAprvlDetail.PBLANC_ODR}" >
</form>
<form id="ccpyPopupFrm" method="POST" action="">
	<input type="hidden" name="resourceName" value="${param.resourceName}">
	<input type="hidden" name="setMulti" value="Y">
</form>
<form id="popupFrm" method="POST">
	<input type="hidden" name="resourceName" value="${param.resourceName}">
	<input type="hidden" name="setMulti" value="Y">
</form>
<form id="chargerPopupFrm" method="POST">
	<input type="hidden" name="resourceName" value="${param.resourceName}">
	<input type="hidden" name="P_USER_NM_S" value="" >
	<input type="hidden" name="P_ORG_NM_S" value="기술평가팀" >
</form>
<form id="downFrm" method="POST" >
	<input type="hidden" name="P_ATCHMNFL_SN">
</form>

