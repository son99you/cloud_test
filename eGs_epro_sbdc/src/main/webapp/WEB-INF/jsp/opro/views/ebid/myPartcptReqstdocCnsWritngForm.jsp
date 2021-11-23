<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 입찰관리 > 나의 입찰공고 상세 > 입찰참가신청서 작성 폼(공사)
 *
 * <pre> 
 * ebid
 *    |_ myPartcptReqstdocCnsWritngForm.jsp
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

<link rel="stylesheet" type="text/css" href="${cssPath}/opro/supplierCommon.css">
<script type="text/javascript" src="${jsPath}/comm/comUtil.js"></script>  
<script type="text/javascript" src="${jsPath}/opro/views/ebid/myPartcptReqstdocCnsWritngForm.js"></script> 


  <input type="hidden" id="rowCount" name="rowCount" value="1">
	<div id="panelSubContent"> 
		<h3 class="subTitle">입찰 참가신청서 작성</h3>

        <h4 class="bulSubTitle">입찰개요</h4>
        <div class="tableLayer">
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
                    	P2017-00003-1
                    </td>
                </tr>
                <tr>
                    <th scope="row">입찰구분</th>
                    <td>공사</td>
                </tr>
                <tr>
                    <th scope="row">입찰명</th>
                    <td>태원광업㈜하장광산 집진기 설치공사  </td>
                </tr>
                </tbody>
            </table>
        </div>
        
		<h4 class="bulSubTitle">신청인 정보</h4>
        <div class="tableLayer">
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
                       주식회사 은우소프트 
                    </td>
                </tr>
                <tr>
                    <th scope="row">대표자</th>
                    <td>
                       정한규
                    </td>
                    <th scope="row">사업자등록번호</th>
                    <td>
                        119-86-02801
                    </td>
                </tr>
                <tr>
                    <th scope="row">주소</th>
                    <td colspan="3">
                       서울특별시 구로구 디지털로33길
                    </td>
                </tr>
                <tr>
                    <th scope="row" class="bullet_orange">담당자</th>
                    <td>
                        <label for="P_CHARGER_NM" class="blind">담당자</label>
                		<input type="text" class="lineTxt" id="P_CHARGER_NM" name="P_CHARGER_NM" style="width: 300px;" value="" maxlength="30">
                    </td>
                    <th scope="row" class="bullet_orange">담당자전화번호</th>
                    <td>
                        <label for="P_TELNO" class="blind">담당자 전화번호</label>
                		<input type="text" class="lineTxt" id="P_TELNO" name="P_TELNO" style="width: 300px;" value="" maxlength="30" tel>
                    </td>
                </tr>
                <tr>
                    <th scope="row" class="bullet_orange">담당자 E-MAIL</th>
                    <td>
                        <label for="P_EMAIL_ADRES" class="blind">담당자 E-MAIL</label>
                		<input type="text" class="lineTxt" id="P_EMAIL_ADRES" name="P_EMAIL_ADRES" style="width: 300px;" value="" maxlength="30" eMail>
                    </td>
                    <td colspan="2">
                    	<label for="P_INDVDL_INFO_USE_AGRE_AT" class="checkFormLayer"><input type="checkbox" id="P_INDVDL_INFO_USE_AGRE_AT" name="P_INDVDL_INFO_USE_AGRE_AT" value=""  > 개인정보 사용에 동의</label>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
        
        <h4 class="bulSubTitle">산출내역서 첨부파일</h4>
        <div class="tableLayer">
			<table class="table">
		    	<caption>산출내역서 첨부파일</caption>
				<colgroup>
					<col width="170px">
	                <col width="320px">
	                <col width="170px">
	                <col width="320px">  
				</colgroup>
		        <tbody>
					<tr>
	                	<th scope="row" class="vtop">첨부파일</th> 
						<td colspan="3" class="vtop"  style="margin-top: 3px; line-height: 30px;"> 
							<button type="button" class="btn btn_search02" title="검색" style="float: right; margin-top: 3px;" id="fileBtn"></button>
	                    	<div id="fileRow" style="display: none; height: 30px;">
	                    		<input type="file" name="" onchange="fileChange(this)" style="display: none;">
	                    		<button type="button" class="btn btn_del" style="float: right; display: inline-block; margin-top: 3px;" onclick="rowDel(this)">삭제</button>
	                    	</div> 
	                    	<div id="fileDiv" style="width: 680px; line-height: 30px;">
	                    	</div>
						</td>
		             </tr>
		        </tbody>
		    </table>
		</div>
        

	    
	    <h4 class="bulSubTitle">입찰보증금</h4>
	    <span class="form_info red">
            <img src="${imagePath}/opro/main/bullet_orange.png" alt="필수입력사항 표시" class="mr5"> 입찰보증금 납부방법을 숙지하신 후 귀사에 해당하는 방법을 선택하세요.</span>
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
	    
	    <div class="tableComment m_15">
		       <div class="T_btnLayer fr cn n_m ml_15">
		       		<button type="button" class="grayBtn S" id="addRowBtn" >납부방법추가</button> 
		       </div>
	   </div>	
        <div class="tableLayer">
            <table class="table" summary="납부방법 입니다.">
                <caption>납부방법</caption>
                <colgroup>
                    <col width="170px">
                    <col width="810px">
                </colgroup>
	                <tr class="line">
	                    <th scope="row" class="bullet_orange">납부방법1</th>
	                    <td >
	                     <%--    <comTag:comCmcdCdValueRadio name="P_BID_GTN_PAY_STLE_CD0" selectKey="" cdId="22147" />
	                 --%>
	                 <input type="radio" name="P_BID_GTN_PAY_STLE_CD0"  value="190000"/>지급각서
	                 <input type="radio" name="P_BID_GTN_PAY_STLE_CD0" value="190001"/>보증증권
	                  
  	 				</td>
	                </tr>
	                <tr id="mmrd0" style="display:none;">
	                    <th scope="row" class="bullet_orange">보증액</th>
	                    <td >
	                        <label for="P_BID_GTN_AM0" class="blind">보증액</label>
	                		<input type="text" class="lineTxt tr" id="P_BID_GTN_AM0" name="P_BID_GTN_AM0" style="width: 300px;" maxlength="22" money onkeyup="conversionNumToKor(this);" disabled> &nbsp;(일금 <font color="red"></font>원)
	                    </td>
	                </tr>
	                <tr id="scrits0" style="display:none;">
	                    <th scope="row" class="bullet_orange">보증증권</th>
	                    <td >
	                    	<span style="width: 80px; display: inline-block;">보증서번호&nbsp;</span>
	                    	<label for="P_BID_GRNTY_NO0" class="blind">보증서번호</label>
	                		<input type="text" class="lineTxt" id="P_BID_GRNTY_NO0" name="P_BID_GRNTY_NO0" style="width: 300px;" maxlength="22" disabled> ("-"를 뺴고 입력)<br>
	                        <span style="width: 80px; display: inline-block;">보증금</span>
	                        <label for="P_BID_GTN_AM0" class="blind">보증금</label>
	                		<input type="text" class="lineTxt  tr" id="P_BID_GTN_AM0" name="P_BID_GTN_AM0" style="width: 300px;" maxlength="22" money onkeyup="conversionNumToKor(this);" disabled> &nbsp;(일금 <font color="red"></font>원)<br>
	                        <span style="width: 80px; display: inline-block;">발급기관명</span>
	                        <label for="P_ISSU_INSTT_NM0" class="blind ">발급기관명</label>
	                		<input type="text" class="lineTxt tr" id="P_ISSU_INSTT_NM0" name="P_ISSU_INSTT_NM0" style="width: 300px;" maxlength="22"  disabled> 
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
	                    <%--     <comTag:comCmcdCdValueRadio name="P_BID_GTN_PAY_STLE_CD" selectKey="" cdId="22147" />
	                     --%>     
	                     <input type="radio" name="P_BID_GTN_PAY_STLE_CD"  value="190000"/>지급각서
	                 		<input type="radio" name="P_BID_GTN_PAY_STLE_CD" value="190001"/>보증증권
	                  
	                     <div class="T_btnLayer fr cn n_m ml_15"><button type="button" class="grayBtn S" id="delRowBtn" name="delRowBtn">삭제</button></div>
	                    </td>
	                </tr>
	                <tr id="mmrd" style="display:none;">
	                    <th scope="row" class="bullet_orange">보증액</th>
	                    <td >
	                        <label for="P_BID_GTN_AM" class="blind">보증액</label>
	                		<input type="text" class="lineTxt tr" id="P_BID_GTN_AM" name="P_BID_GTN_AM" style="width: 300px;" maxlength="22" money onkeyup="conversionNumToKor(this);" disabled> &nbsp;(일금 <font color="red"></font>원)
	                    </td>
	                </tr>
	                <tr id="scrits" style="display:none;">
	                    <th scope="row" class="bullet_orange">보증증권</th>
	                    <td >
	                    	<span style="width: 80px; display: inline-block;">보증서번호</span>
	                    	<label for="P_BID_GRNTY_NO" class="blind">보증서번호</label>
	                		<input type="text" class="lineTxt" id="P_BID_GRNTY_NO" name="P_BID_GRNTY_NO" style="width: 300px;" maxlength="22" disabled> ("-"를 뺴고 입력)<br>
	                        <span style="width: 80px; display: inline-block;">보증금</span>
	                        <label for="P_BID_GTN_AM" class="blind">보증금</label>
	                		<input type="text" class="lineTxt tr" id="P_BID_GTN_AM" name="P_BID_GTN_AM" style="width: 300px;" maxlength="22" money onkeyup="conversionNumToKor(this);" disabled> &nbsp;(일금 <font color="red"></font>원)<br>
	                		 <span style="width: 80px; display: inline-block;">발급기관명</span>
	                        <label for="P_ISSU_INSTT_NM" class="blind">발급기관명</label>
	                		<input type="text" class="lineTxt tr" id="P_ISSU_INSTT_NM" name="P_ISSU_INSTT_NM" style="width: 300px;" maxlength="22"  disabled>
	                    </td>
	                </tr>
	                </tbody>
					<!--------------------  납부방법추가 부분 END ------------------->	                
            </table>
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
	        	<p>2017-06-19</p>
			    <p>신청자 : 정한규</p>
			    <p>한국국제협력단 이사장 귀하 </p>
			</div> 
        </div>
        
        <div class="T_btnLayer fr" >
            <button type="button" class="blueBtn L" id="pareBtn" >참가신청</button>
            <button type="button" class="blueBtn L" id="returnBtn">돌아가기</button>
        </div>
    </div>
    
<form id="returnFrm" method="POST" action="">
	<input type="hidden" id="resourceName" name="resourceName" value="${param.resourceName}">
</form>  

<form id="listFrm" method="POST" action="">
	<input type="hidden" id="resourceName" name="resourceName" value="${param.resourceName}">
</form>  

<form id="bidPartcptAbandnFrm" method="POST" >
	<input type="hidden" id="resourceName" name="resourceName" value="${param.resourceName}">
</form>