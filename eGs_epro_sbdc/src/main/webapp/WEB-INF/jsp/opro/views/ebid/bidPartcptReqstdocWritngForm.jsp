<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 입찰관리 > 진행중인 입찰공고상세 > 입찰참가신청서 작성 폼(구매)
 *
 * <pre>
 * ebid
 *    |_ partcptReqstdocWritngForm.jsp
 * 
 
 * </pre>
 * @date : 2017.06.19
 * @version : 1.0
 * @author : 은우소프트 이주연 
--%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 

<script type="text/javascript" src="${jsPath}/comm/comUtil.js"></script>  
<script type="text/javascript" src="${jsPath}/opro/views/ebid/bidPartcptReqstdocWritngForm.js"></script>
<script src="/nxts/js/nxts/nxts.min.js"></script>
<script src="/nxts/js/nxts/nxtspki_config.js"></script>
<script src="/nxts/js/nxts/nxtspki.js"></script>

<ul class="step_wrap">
	<li><a href="#">경쟁입찰</a></li>
	<li><a href="#">입찰공고조회</a></li> 
</ul> <!--// step_wrap E -->
<div class="tit_wrap">
	<h3 class="tit">입찰참가신청서 작성</h3>
</div> <!--// tit_wrap E --> 

<div class="view_wrap typeA">
	<form id="registFrm" method="POST" enctype="multipart/form-data">
		<input type="hidden" name="resourceName" value="${param.resourceName}">
		<input type="hidden" id="copertnSpldmdDutySeCd" value="${inProgrsBidPblancDetail.COPERTN_SPLDMD_DUTY_SE_CD}">
		<input type="hidden" name="P_PBLANC_NO" value="${inProgrsBidPblancDetail.PBLANC_NO}">
		<input type="hidden" name="P_PBLANC_ODR" value="${inProgrsBidPblancDetail.PBLANC_ODR}">
		<input type="hidden" id="bizrNo" name="bizrNo" value="${sessionScope.loginResult.BIZR_NO }">
		<input type="hidden" id="P_CARE_ESSNTL_AT" name="P_CARE_ESSNTL_AT" value="${inProgrsBidPblancDetail.CARE_ESSNTL_AT }">
		<input type="hidden" id="P_PRCURE_BSNS_SE_CD" name="P_PRCURE_BSNS_SE_CD" value="${inProgrsBidPblancDetail.PRCURE_BSNS_SE_CD }">
		<input type="hidden" id="P_ENTRPS_REGIST_NO" name="P_ENTRPS_REGIST_NO" value="${sessionScope.loginResult.LOGIN_ID}">
		<input type="hidden" id="fngprtBidAt" value="${inProgrsBidPblancDetail.FNGPRT_BID_AT}">
		<input type="hidden" id="certGbn" value="${sessionScope.loginResult.LOGIN_GBN }">
		<input type="hidden" id="rowCount" name="rowCount" value="1">
		<input type="hidden" id="serverTime" name="serverTime" value="${serverTime }">
		
		<div class="tit_area">
			<h4 class="tit">입찰개요</h4>
		</div> <!--// tit_area E -->
		<div class="view_area">
			<table class="table" summary="입찰참가 신청서 입니다.">
                <caption>입찰개요</caption>
                <colgroup>
                    <col width="170px">
                    <col width="810px">
                </colgroup>
                <tbody>
	                <tr class="line">
	                    <th scope="row">입찰공고번호</th>
	                    <td>
	                        ${inProgrsBidPblancDetail.PBLANC_NO}-${inProgrsBidPblancDetail.PBLANC_ODR}
	                    </td>
	                </tr>
	                <tr>
	                    <th scope="row">입찰구분</th>
	                    <td>
	                        ${inProgrsBidPblancDetail.PRCURE_SE_CD_NM} &nbsp;/&nbsp; ${inProgrsBidPblancDetail.PRCURE_DETAIL_SE_CD_NM}
	                    </td>
	                </tr>
	                <tr>
	                    <th scope="row">입찰명</th>
	                    <td>${inProgrsBidPblancDetail.BID_NM}</td>
	                </tr>
                </tbody>
            </table>
		</div> <!--// view_area E -->
		
		<div class="tit_area">
			<h4 class="tit">신청인 정보</h4>
		</div> <!--// tit_area E -->
		<div class="view_area">
			<table class="table" summary="신청자 정보 입니다.">
                <caption>신청인 정보</caption>
                <colgroup>
                    <col width="170px">
                    <col width="320px">
                    <col width="170px">
                    <col width="320px">
                </colgroup>
                <tbody>
	                <tr class="line">
	                    <th scope="row">업체명</th>
	                    <td colspan="3">
	                        ${entrpsinfoinqire.ENTRPS_NM}
	                    </td>
	                </tr>
	                <tr>
	                    <th scope="row">대표자</th>
	                    <td>
	                        ${entrpsinfoinqire.RPRSNTV_NM}
	                    </td>
	                    <th scope="row">사업자등록번호</th>
	                    <td>
	                       ${comFn:formatBizNumber(entrpsinfoinqire.BIZRNO)}
	                    </td>
	                </tr>
	                <tr>
	                    <th scope="row">주소</th>
	                    <td colspan="3">
	                        ${entrpsinfoinqire.ENTRPS_ADRES}
	                    </td>
	                </tr>
	                <tr>
	                    <th scope="row" class="bullet_orange">담당자</th>
	                    <td>
	                		<input type="text" class="lineTxt" id="P_CHARGER_NM" name="P_CHARGER_NM" style="width: 300px;" value="${inProgrsBidPblancDetail.CHARGER_NM}" maxlength="30">
	                    </td>
	                    <th scope="row" class="bullet_orange">담당자전화번호</th>
	                    <td>
	                		<input type="text" class="lineTxt" id="P_TELNO" name="P_TELNO" style="width: 300px;" value="${inProgrsBidPblancDetail.TELNO}" maxlength="30" tel>
	                    </td>
	                </tr>
	                <tr>
	                    <th scope="row" class="bullet_orange">담당자 E-MAIL</th>
	                    <td>
	                		<input type="text" class="lineTxt" id="P_EMAIL_ADRES" name="P_EMAIL_ADRES" style="width: 300px;" value="${inProgrsBidPblancDetail.EMAIL_ADRES}" maxlength="30" eMail>
	                    </td>
	                    <td colspan="2">
	                    	<label for="P_INDVDL_INFO_USE_AGRE_AT" class="checkFormLayer"><input type="checkbox" id="P_INDVDL_INFO_USE_AGRE_AT" name="P_INDVDL_INFO_USE_AGRE_AT" value=""  > 개인정보 사용에 동의</label>
	                    </td>
	                </tr>
                </tbody>
            </table>
		</div> <!--// view_area E -->
		
		<div class="tit_area">
			<h4 class="tit">산출내역서 첨부파일</h4>
		</div> <!--// tit_area E -->
		<div class="view_area">
			<div class="tableLayer">
				<table id="fileTable" class="table">
		    	<colgroup>
		    		<col width="170px">
                    <col width="810px">
		        </colgroup>
	            <tr>
                	<th scope="row" class="vtop">첨부파일</th>
					<td class="vtop">
					<span class="stD">
                    	<button type="button"  class="btn btn_m btn_del" id="fileBtn" style="float: right; border: 1px solid #bfc7c3; ">추가</button>
<!--                     	  -->
                    </span>
                    	<div id="fileRow" style="display: none; height: 30px;">
                    		<input type="file" name="" style="width: 80%;height: 24px;" >
		                   	<span class="stD"> 
                    			<button type="button" class="btn btn_m btn_del" style="display: inline-block; margin-top: 3px;" onclick="rowDel(this)">삭제</button>
                    		</span>
                    	</div>
                    	<div id="fileDiv" style="width: 680px; line-height: 30px;">
                    	</div>
                    </td>	
	             </tr>
		    </table>
		    </div> <!--// view_area E -->
		</div> <!--// view_area E -->
		
		<c:if test="${inProgrsBidPblancDetail.COPERTN_SPLDMD_DUTY_SE_CD eq '240001'}">
	    	<input type="hidden" name="P_COPERTN_SPLDMD_DUTY_SE_CD" value="${inProgrsBidPblancDetail.COPERTN_SPLDMD_DUTY_SE_CD}">
	    </c:if>
	    <c:if test="${inProgrsBidPblancDetail.COPERTN_SPLDMD_DUTY_SE_CD ne '240001'}">
		<div class="tit_area">
			<h4 class="tit">입찰보증금</h4>
		    <span class="form_info red">
            <img src="${imagePath}/opro/main/bullet_orange.png" alt="필수입력사항 표시" class="mr5"> 입찰보증금 납부방법을 숙지하신 후 귀사에 해당하는 방법을 선택하세요.</span>
		</div> <!--// tit_area E -->
		<div class="view_area">
			<div class="tableLayer">
				 
            <table class="table" summary="입찰보증금 입니다.">
                <caption>입찰보증금</caption>
                <colgroup>
                    <col width="980px">
                </colgroup>
                <tbody>
                <tr class="line">
                    <td>
                       협력단 대외무상협력 사업에 관한 조달 및 계약규정 시행세칙 제 26조에 의거 입찰금액의 100분의 5이상 현금 또는 보증보험증권 등으로제출하되, 협력단과 계약이행 질적이 있는 조달협력업체 또는 국가가 기본재산의 50%이상 출연한 법인은 별도의 입찰보증금 납부없이 입찰참가신청서상에 입찰보증금 지급각서로 대체하며, 낙찰자가 예약을 체결하지 않을 시 입찰보증금은 우리 협력단에 귀속됩니다.
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
	    </div>
	    <div class="view_area">
        <div class="tableLayer">
            <table class="table" summary="공동수급방식 선택 입니다.">
                <caption>공동수급방식 선택</caption>
                <colgroup>
                    <col width="170px">
                    <col width="810px">
                </colgroup>
                <tbody>
                <tr class="line">
                    <th scope="row" class="bullet_orange">공동수급방식선택</th>
                    <td>
                    	<c:forEach var="data" items="${cmmnCdList}" varStatus="status">
                    		<%-- 공동수급의무구분이 [공동이행] 일 경우 --%>
                    		<c:if test="${inProgrsBidPblancDetail.COPERTN_SPLDMD_DUTY_SE_CD eq '240000'}">
                    			<c:if test="${data.CD_VALUE eq '240000' or data.CD_VALUE eq '240001'}">
	                    			<input type="radio" id="cospDutySeCd${data.CD_VALUE}" name="P_COPERTN_SPLDMD_DUTY_SE_CD" value="${data.CD_VALUE}" ><label class="mr_10" for="cospDutySeCd${data.CD_VALUE}"> ${data.CD_VALUE_NM}</label>
                    			</c:if>
                    		</c:if>
                    		<%-- 공동수급의무구분이 [공동이행] 일 경우 END--%>
                    		<%-- 공동수급의무구분이 [분담이행] 일 경우 --%>
                    		<c:if test="${inProgrsBidPblancDetail.COPERTN_SPLDMD_DUTY_SE_CD eq '240002'}">
                    			<c:if test="${data.CD_VALUE eq '240002' or data.CD_VALUE eq '240001'}">
	                    			<input type="radio" id="cospDutySeCd${data.CD_VALUE}" name="P_COPERTN_SPLDMD_DUTY_SE_CD" value="${data.CD_VALUE}" ><label class="mr_10" for="cospDutySeCd${data.CD_VALUE}"> ${data.CD_VALUE_NM}</label>
                    			</c:if>
                    		</c:if>
                    		<%-- 공동수급의무구분이 [분담이행] 일 경우 END --%>
                    		<%-- 공동수급의무구분이 [공동,분담모두가능] 일 경우 --%>
                    		<c:if test="${inProgrsBidPblancDetail.COPERTN_SPLDMD_DUTY_SE_CD eq '240003'}">
                    			<c:if test="${data.CD_VALUE ne '240003'}">
	                    			<input type="radio" id="cospDutySeCd${data.CD_VALUE}" name="P_COPERTN_SPLDMD_DUTY_SE_CD" value="${data.CD_VALUE}" ><label class="mr_10" for="cospDutySeCd${data.CD_VALUE}"> ${data.CD_VALUE_NM}</label>
                    			</c:if>
                    		<%-- 공동수급의무구분이 [공동,분담모두가능] 일 경우 END --%>
                    		</c:if>
                    	</c:forEach>
                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                    <span style="color: red">수급방식의 선택은 참가신청서 제출 이후 변경할 수 없으니 최종확인 후 결정하시기 바랍니다.</span><br>
                    공동수급을 선택시 공동수급협정서는 입찰참가 신청서 제출직후 작성할 수 있으며 이후에도 입찰 참가신청서 제출기간 내에 작성이 가능합니다.                                          
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
		</div> <!--// view_wrap E -->
        </c:if>
		<div class="tit_area">
			<div class="btn_right">
				<button type="button" class="btn btn_s2 btn_c2"id="addRowBtn">납부방법추가</button>
	  		 </div>	
	   </div>	
	   <div class="view_area">
        <div class="tableLayer">
            <table class="table" summary="납부방법 입니다.">
                <caption>납부방법</caption>
                <colgroup>
                    <col width="170px">
                    <col width="810px">
                </colgroup>
	                <tr class="line">
	                    <th scope="row" class="bullet_orange">납부방법1</th>
	                    <td>
							<comTag:comCmcdCdValueRadio name="P_BID_GTN_PAY_STLE_CD0" selectKey="" cdId="22147" />
	                    </td>
	                </tr>
	                <tr id="mmrd0" style="display:none;">
	                    <th scope="row" class="bullet_orange">보증액</th>
	                    <td >
	                		<input type="text" class="lineTxt tr" id="P_BID_GTN_AM0" name="P_BID_GTN_AM0" style="width: 300px;" maxlength="22" money onkeyup="conversionNumToKor(this);" disabled> &nbsp;(일금 <font color="red"></font>원)
	                    </td>
	                </tr>
	                <tr id="scrits0" style="display:none;">
	                    <th scope="row" class="bullet_orange">보증증권</th>
	                    <td >
	                    	<span style="width: 80px; display: inline-block;">보증서번호&nbsp;</span>
	                		<input type="text" class="lineTxt" id="P_BID_GRNTY_NO0" name="P_BID_GRNTY_NO0" style="width: 300px;" maxlength="22" disabled> ("-"를 뺴고 입력)<br>
	                        <span style="width: 80px; display: inline-block;">보증금</span>
	                		<input type="text" class="lineTxt  tr" id="P_BID_GTN_AM0" name="P_BID_GTN_AM0" style="width: 300px;" maxlength="22" money onkeyup="conversionNumToKor(this);" disabled> &nbsp;(일금 <font color="red"></font>원)<br>
	                        <span style="width: 80px; display: inline-block;">발급기관명&nbsp;&nbsp;&nbsp;&nbsp;</span>
	                		<input type="text" class="lineTxt" id="P_ISSU_INSTT_NM0" name="P_ISSU_INSTT_NM0" style="width: 300px;" maxlength="22"  disabled>
	                		<div>
				                <span style="width: 83px; display: inline-block; float: left; margin-top: 5px;">보증첨부파일</span>
				                <div id="scritsFileDiv0">
									<input type="file" id="P_FILE0" name="P_FILE0" class="fl" style="width: 300px; height: 28px;">&nbsp;
								</div>
							</div> 
	                    </td>
	                </tr>
	                <tr id="cash0" style="display: none;">
	                	<th scope="row" class="bullet_orange">현금지급</th>
	                    <td>
	                        <span style="width: 80px; display: inline-block;">보증금</span>
	                		<input type="text" class="lineTxt  tr" id="P_BID_GTN_AM0" name="P_BID_GTN_AM0" style="width: 300px;" maxlength="22" money onkeyup="conversionNumToKor(this);"> &nbsp;(일금 <font color="red"></font>원)<br>
	                		<span style="width: 83px; display: inline-block; float: left; margin-top: 5px;">보증기간</span>
	                		<div class="calendar_wrap">
			                    <input type="text" id="P_GRNTY_PD_BEGIN_DE0" name="P_GRNTY_PD_BEGIN_DE0" class="datepicker1" style="width: 110px;" >
			               		<span class="wave"> ~ </span>
			                    <input type="text" id="P_GRNTY_PD_END_DE0" name="P_GRNTY_PD_END_DE0" class="datepicker2"  style="width: 110px;" >
			                </div>
			                <div style="margin-top: 15px;">
				                <span style="width: 100px; display: inline-block; float: left; margin-top: 5px;">계좌이체확인서</span>
				                <div id="cashFileDiv0">
									<input type="file" id="P_FILE0" name="P_FILE0" class="fl"  style="width: 300px; height: 28px;">&nbsp;
								</div>
							</div>
	                    </td>
	                </tr>
	                <tbody id="contentRow"></tbody>
	                 <tbody id="mmrdtext" style="display:none;">
	                <tr>
	                	<th colspan="2">입찰보증금 지급각서</th>
	                </tr>
	                <tr>
	                	<td colspan="2" align="center">※ 지급각서 대체자 : 본인은 낙찰 후 계약미체결시 귀 기관에 낙찰금액에 해당하는 소정의 입찰보증금을 현금으로 납부할 것을 확약합니다. <br>(대외무상협력사업에 관한 조달 및 계약규정 시행세칙 제26조 2항 각 호에 해당하는 자)<br><br>
					        <input type="radio" id="agreIemY" name="agreIem" class="" ><label for="agreIemY"> 동의함</label>
					        <input type="radio" id="agreIemN" name="agreIem" class="ml_10" ><label for="agreIemN"> 동의안함</label>
	                	</td>
	                </tr>
	                </tbody>
<!-------------------- 납부방법추가 부분 ------------------->
	                <tbody id="addRow" style="display: none;">
                	<tr class="line" >
	                    <th scope="row" class="bullet_orange" id="BID_GTN_PAY_STLE_NM">납부방법</th>
	                    <td>
	                        <comTag:comCmcdCdValueRadio name="P_BID_GTN_PAY_STLE_CD" selectKey="" cdId="22147" />
	                         <span> 
	                         <button type="button" class="btn btn_m btn_del" id="delRowBtn" name="delRowBtn">삭제</button>
	                         </span>
	                    </td>
	                </tr>
	                <tr id="mmrd" style="display:none;">
	                    <th scope="row" class="bullet_orange">보증액</th>
	                    <td >
	                		<input type="text" class="lineTxt tr" id="P_BID_GTN_AM" name="P_BID_GTN_AM" style="width: 300px;" maxlength="22" money onkeyup="conversionNumToKor(this);" disabled> &nbsp;(일금 <font color="red"></font>원)
	                    </td>
	                </tr>
	                <tr id="scrits" style="display:none;">
	                    <th scope="row" class="bullet_orange">보증증권</th>
	                    <td >
	                    	<span style="width: 80px; display: inline-block;">보증서번호</span>
	                		<input type="text" class="lineTxt" id="P_BID_GRNTY_NO" name="P_BID_GRNTY_NO" style="width: 300px;" maxlength="22" disabled> ("-"를 뺴고 입력)<br>
	                        <span style="width: 80px; display: inline-block;">보증금</span>
	                		<input type="text" class="lineTxt tr" id="P_BID_GTN_AM" name="P_BID_GTN_AM" style="width: 300px;" maxlength="22" money onkeyup="conversionNumToKor(this);" disabled> &nbsp;(일금 <font color="red"></font>원)<br>
	                		 <span style="width: 80px; display: inline-block;">발급기관명&nbsp;&nbsp;&nbsp;&nbsp;</span>
	                		<input type="text" class="lineTxt" id="P_ISSU_INSTT_NM" name="P_ISSU_INSTT_NM" style="width: 300px;" maxlength="22"  disabled>
	                		<div>
				                <span style="width: 83px; display: inline-block; float: left; margin-top: 5px;">보증파일명</span>
				                <label for="P_FILE" class="blind">보증파일명</label>
				                <div id="scritsFileDiv">
									<input type="file" id="P_FILE" name="P_FILE" class="fl"  style="width: 300px; height: 28px;">&nbsp;
								</div>
							</div>
	                    </td>
	                </tr>
	                <tr id="cash" style="display: none;">
	                	<th scope="row" class="bullet_orange">현금지급</th>
	                    <td>
	                        <span style="width: 80px; display: inline-block;">보증금</span>
	                		<input type="text" class="lineTxt  tr" id="P_BID_GTN_AM" name="P_BID_GTN_AM" style="width: 300px;" maxlength="22" money onkeyup="conversionNumToKor(this);"> &nbsp;(일금 <font color="red"></font>원)<br>
	                		<span style="width: 83px; display: inline-block; float: left; margin-top: 5px;">보증기간</span>
	                		<div class="calendar_wrap">
			                    <input type="text" id="P_GRNTY_PD_BEGIN_DE0" name="P_GRNTY_PD_BEGIN_DE0" class="datepicker1" style="width: 110px;" >
			               		<span class="wave"> ~ </span>
			                    <input type="text" id="P_GRNTY_PD_END_DE0" name="P_GRNTY_PD_END_DE0" class="datepicker2"  style="width: 110px;" >
			                </div>
			                <div style="margin-top: 15px;">
				                <span style="width: 100px; display: inline-block; float: left; margin-top: 5px;">계좌이체확인서</span>
				                <div id="cashFileDiv">
									<input type="file" id="P_FILE" name="P_FILE" class="fl"  style="width: 300px; height: 28px;">&nbsp;
								</div>
							</div>
	                    </td>
	                </tr>
	                </tbody>
<!--------------------  납부방법추가 부분 END ------------------->	                
            </table>
            </div>
        </div>
		
		
		<div class="tableLayer">
            <table class="" summary="참가신청 인내 입니다.">
                <caption>참가신청안내</caption>
                <colgroup>
                    <col width="980px">
                </colgroup>
                <tbody>
                <tr>
                    <td>
                        본인은 위의 번호로 공고한 입찰건에 대해 한국국제협력단에서 정한 입찰유의서 및 입찰공고 사항을 모두 승낙하고 입찰 참가 신청을 합니다.
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
        
        <div class="contract_wrap">
	        <div class="contract_sign">
	        	<p>${today}</p>
			    <p>신청자 : ${entrpsinfoinqire.RPRSNTV_NM}</p>
			    <p>한국국제협력단 이사장 귀하 </p>
			</div>
        </div>
		
		<div class="btn_wrap view_btn">
             <button type="button" class="btn btn_m btn_orange" id="pareBtn" >참가신청</button>
            <button type="button" class="btn btn_m btn_del" id="returnBtn">돌아가기</button>
		</div> <!--// btn_wrap E -->
	</form>
</div>  
	<form id="returnFrm" method="POST" action="">
		<input type="hidden" name="P_PBLANC_NO" value="${inProgrsBidPblancDetail.PBLANC_NO}">
		<input type="hidden" name="P_PBLANC_ODR" value="${inProgrsBidPblancDetail.PBLANC_ODR}">
		<input type="hidden" id="resourceName" name="resourceName" value="${param.resourceName}">
	</form>
	<form id="bidPartcptAbandnFrm" method="POST" >
		<input type="hidden" name="P_PBLANC_NO" value="${inProgrsBidPblancDetail.PBLANC_NO}"/>
		<input type="hidden" name="P_PBLANC_ODR" value="${inProgrsBidPblancDetail.PBLANC_ODR}"/>
		<input type="hidden" name="P_BID_NM" value="${inProgrsBidPblancDetail.BID_NM}"/>
		<input type="hidden" id="resourceName" name="resourceName" value="${param.resourceName}">
	</form>
	<form id="popupFrm" method="post" action=""></form>