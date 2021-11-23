<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%--
 * 청렴이행각서 조회
 *
 * <pre>
 * elbi 
 *  |_popup
 *   | 	intgtyFlflMmrdInqire.jsp
 * 
 * </pre>
 * @date : 2017. 06. 21
 * @version : 1.0
 * @author : 은우소프트 이주연 
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %>

<script type="text/javascript" src="${jsPath}/opro/views/comm/popup/intgtyFlflMmrdInqire.js"></script>

<script type="text/javascript" src="/nxts/js/bs-3.3.5/js/bootstrap.js"></script>
<script type="text/javascript" src="/nxts/js/nxts/nxts.min.js"></script>
<script type="text/javascript" src="/nxts/js/nxts/nxtspki_config.js"></script>
<script type="text/javascript" src="/nxts/js/nxts/nxtspki.js"></script>

<div id="windowPopup">
	<fieldset>
        <legend></legend>
	  	<div id="cloneDiv">
		<h3 style="text-align: center; padding-left: 11px;  font-size: 18px;   margin-bottom: 8px;   color: #252525;">청&nbsp;렴&nbsp;계&nbsp;약&nbsp;이&nbsp;행&nbsp;각&nbsp;서</h3>
        <div class="tableLayer">
            <p class="popSubTitle marginSet"> 입찰명 : ${intgtyFlflMmrdInqire.BID_NM }</p>
            <table class="table" summary="청렴계약이행각서 입니다.">
                <caption>청렴계약이행각서</caption>
                <colgroup>
                 	<col width="100px">
                  	<col width="200px">
                    <col width="100px">
                    <col width="200px">
                </colgroup>
                <tbody>
	                <tr>
	                    <td colspan="4">
	                    	<br>
	                    	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	                    	당사의 임직원과 대리인은 표제사업 참여와 관련하여 다음 사항을 확약합니다.
	                    	<br><br><br>
	                    	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	                    	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	                    	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	                    	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	                    	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	                    	-&nbsp;&nbsp;다&nbsp;&nbsp;&nbsp;음&nbsp;&nbsp;-
	                    	<br><br><br>
	                    	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	                    	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	                    	1.&nbsp;&nbsp;&nbsp;&nbsp;당사는 입찰가격의 유지나 특정인의 낙찰을 위한 담합을 하거나 다른 업체와 합의하여 입찰의<br>
	                    	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	                    	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	                    	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	                    	 자유 경쟁을 부당하게 저해하는 일체의 불공정한 행위를 하지 않을 것임.<br>
	                    	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	                    	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	                    	2.&nbsp;&nbsp;&nbsp;&nbsp;당사는 「국제상거래에있어서외국공무원에대한뇌물방지법」 (제정 : 법률 제5588호, 1998.12.28<br>
	                    	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	                    	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	                    	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
	                    	공포)의 내용을 충분히 숙지하고 동법에서 규제하고 있는 사항을 정히 준수하겠음.<br>
	                    	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	                    	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	                    	3.&nbsp;&nbsp;&nbsp;&nbsp;당사는 불공정, 뇌물제공행위가 확인될 경우 은우소프트가 다음의 조치를 취하더라도 이의를 제기<br>
	                    	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	                    	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	                    	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	                    	하지 않겠음.<br>
	                    	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	                    	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	                    	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	                    	가. 입찰참가자격의 제한<br>
	                    	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	                    	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	                    	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      						나. 사법기관 및 조달청 통지<br>
      						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	                    	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	                    	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      						다. 입찰보증금 납부면제대상 제외<br>
      						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	                    	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	                    	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      						라. 낙찰자 결정 취소<br>
      						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	                    	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	                    	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      						마. 계약취소, 당해 계약의 전부 또는 일부계약을 해제 또는 해지<br><br>
	                    </td>
	                </tr>
	                
                </tbody>
            </table>
        </div>
        
        <br><br>
        
      
      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      ${comFn:formatDate(intgtyFlflMmrdInqire.PARTCPTN_DT,'yyyyMMddHHmmss','yyyy')}년 &nbsp;
      ${comFn:formatDate(intgtyFlflMmrdInqire.PARTCPTN_DT,'yyyyMMddHHmmss','MM')}월 &nbsp;
      ${comFn:formatDate(intgtyFlflMmrdInqire.PARTCPTN_DT,'yyyyMMddHHmmss','dd')}일 &nbsp;
      
      <br>
      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      계약자 : ${intgtyFlflMmrdInqire.ENTRPS_NM}
      <br>
      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      대표자 : ${intgtyFlflMmrdInqire.RPRSNTV_NM}
      <br><br>
      
      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      &nbsp;&nbsp;
      
      <font style="text-align: center; font-size: 15px;color: #252525;">&nbsp;은&nbsp;우&nbsp;소&nbsp;프&nbsp;트&nbsp;귀&nbsp;중</font>
      <br><br> 
	  </div> 
        
        <div class="T_btnLayer fr">
            <button type="button" class="blueBtn L" id="closeBtn" >닫기</button>
        </div>
    </fieldset>
</div>