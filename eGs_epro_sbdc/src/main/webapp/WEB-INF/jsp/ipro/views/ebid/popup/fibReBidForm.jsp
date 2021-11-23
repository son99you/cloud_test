<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 입찰현황 > 유찰등록 팝업
 *
 * <pre>
 * ebid 
 *    |_ popup
 			  |_ fibRegistForm.jsp
 * 
 * </pre>
 * @date : 2017. 06. 14
 * @version : 1.0
 * @author : 은우소프트 전상훈
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %> 
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %> 

<script type="text/javascript" src="${jsPath}/ipro/views/ebid/popup/fibReBidForm.js"></script> 
 
<div class="pop_wrap">
	<div class="pop_header">
		<h1 class="tit">유찰 등록</h1>
	</div> <!--// pop_header E -->
	<div class="pop_container">
		<div class="view_wrap typeC">
			<div class="view_area">
				<form id="registFrm"  method="POST" >
		    		<input type="hidden" id="P_ANNC_NO" name="P_ANNC_NO" value="${bidPblancDetail.ANNC_NO }">
		    		<input type="hidden" id="P_ANNC_NGR" name="P_ANNC_NGR" value="${bidPblancDetail.ANNC_NGR }">
		    		<input type="hidden" id="P_ROUND_NO" name="P_ROUND_NO" value="${bidPblancDetail.ROUND_NO }">
		    		<input type="hidden" id="P_ROUND_NO" name="P_BID_PSCD" value="">
		    		<input type="hidden" id="P_ROUND_RE_BID_YN" name="P_ROUND_RE_BID_YN" value="Y">
		    		<input type="hidden" name="P_PRPDC_SBMT_STDT" value="${bidPblancDetail.PRPDC_SBMT_STDT}">
		    		<input type="hidden" name="P_PRPDC_SBMT_ENDT" value="${bidPblancDetail.PRPDC_SBMT_ENDT}">
		    		<input type="hidden" name="P_BID_GBN" value="${param.P_BID_GBN}">
					
					<table class="table">
				    	<colgroup>
				        	<col style="width: 20%;">
							<col style="width: 30%;">
							<col style="width: 20%;">
							<col style="width: 30%;">
				        </colgroup>
				    	<tr>
				        	<th>공고번호</th>
				            <td <c:if test="${bidPblancDetail.SBID_MTCD eq '70'}">colspan="3"</c:if>>${bidPblancDetail.ANNC_NO }-${bidPblancDetail.ANNC_NGR }</td>
				            <c:if test="${bidPblancDetail.SBID_MTCD ne '70'}">
				            <th>공고일자</th>
				            <td>${comFn:formatDate(bidPblancDetail.ANNC_DT,'yyyyMMddHHmmss','yyyy-MM-dd')}</td>
				            </c:if>
				        </tr>
				        <tr>
				        	<th>공고명</th>
				            <td colspan="3">${bidPblancDetail.BID_NM }</td>
				        </tr>
				        <tr>
				        	<th>계약방법</th>
				            <td>${bidPblancDetail.CONT_MTCD_NM}</td>
				            <th>유찰처리일자</th>
				            <td>${comFn:formatDate(today,'yyyyMMdd','yyyy-MM-dd')}</td>
				        </tr>
				        <c:if test="${bidPblancDetail.ANNC_NGR eq '1'}">
			                <tr>
			                    <th scope="row" class="bullet_orange"><label for="P_BIDC_SBMT_STDT">입찰서제출기간</label></th>
			                    <td colspan="3">
			                    	<div style="display: inline-block;">
					                    <input type="text"  id="P_BIDC_SBMT_STDT" name="P_BIDC_SBMT_STDT" class="w120" date  value="${comFn:formatDate(bidPblancDetail.BIDC_SBMT_STDT,'yyyyMMddHHmmss','yyyy-MM-dd')}">
				                        <span class="wave">&nbsp;</span>
				                        <input type="text" class="float-left" id="P_BIDC_SBMT_STDT_HH" name="P_BIDC_SBMT_STDT_HH" style="width: 50px" maxlength="2" numeric  value="${comFn:formatDate(bidPblancDetail.BIDC_SBMT_STDT,'yyyyMMddHHmmss','HH')}"><span class="wave">:</span>
				                        <input type="text" class="float-left" id="P_BIDC_SBMT_STDT_MM" name="P_BIDC_SBMT_STDT_MM" style="width: 50px" maxlength="2" numeric  value="${comFn:formatDate(bidPblancDetail.BIDC_SBMT_STDT,'yyyyMMddHHmmss','mm')}">
			                        </div>
			                        <span class="wave">~</span>
			                        <div style="display: inline-block;">
					                    <input type="text"  id="P_BIDC_SBMT_ENDT" name="P_BIDC_SBMT_ENDT" class="w120 edOpDt" date  value="${comFn:formatDate(bidPblancDetail.BIDC_SBMT_ENDT,'yyyyMMddHHmmss','yyyy-MM-dd')}">
				                        <span class="wave">&nbsp;</span>
				                        <input type="text" class="float-left edOpDt" id="P_BIDC_SBMT_ENDT_HH" name="P_BIDC_SBMT_ENDT_HH" style="width: 50px" maxlength="2" numeric  value="${comFn:formatDate(bidPblancDetail.BIDC_SBMT_ENDT,'yyyyMMddHHmmss','HH')}"><span class="wave">:</span>
				                        <input type="text" class="float-left edOpDt" id="P_BIDC_SBMT_ENDT_MM" name="P_BIDC_SBMT_ENDT_MM" style="width: 50px" maxlength="2" numeric  value="${comFn:formatDate(bidPblancDetail.BIDC_SBMT_ENDT,'yyyyMMddHHmmss','mm')}">
			                        </div>
			                    </td>
			                </tr>
			                <tr>
			                    <th scope="row" class="bullet_orange"><label for="P_OPNG_DT">개찰일시</label></th>
			                    <td colspan="3">
				                    <input type="text"   id="P_OPNG_DT" name="P_OPNG_DT" class="w120 edOpDt" date  value="${comFn:formatDate(bidPblancDetail.OPNG_DT,'yyyyMMddHHmmss','yyyy-MM-dd')}">
			                        <span class="wave">&nbsp;</span>
			                        <input type="text" class="float-left edOpDt" id="P_OPNG_DT_HH" name="P_OPNG_DT_HH" style="width: 50px" maxlength="2" numeric  value="${comFn:formatDate(bidPblancDetail.OPNG_DT,'yyyyMMddHHmmss','HH')}"><span class="wave">:</span>
			                        <input type="text" class="float-left edOpDt" id="P_OPNG_DT_MM" name="P_OPNG_DT_MM" style="width: 50px" maxlength="2" numeric  value="${comFn:formatDate(bidPblancDetail.OPNG_DT,'yyyyMMddHHmmss','mm')}">
			                    </td>
			                </tr>
						</c:if>
						<c:if test="${bidPblancDetail.BID_PSCD ne 'PF99'}">
					        <tr>
					            <th class="bullet_orange">재입찰사유</th>
					            <td colspan="3">
				                    <label for="P_ROUND_RSN" class="blind">유찰사유</label>
				                    <textarea id="P_ROUND_RSN" name="P_ROUND_RSN" style="width: 99%;height: 100px;"></textarea>
			                    </td>
					        </tr>
				        </c:if>
				    </table>
				</form>
			</div>
			<div class="btn_wrap view_btn">
				<button type="button" class="btn btn_m btn_orange" id="reBidBtn" >재입찰</button>
		    	<button type="button" class="btn btn_m btn_del" id="closeBtn" >닫기</button>
		    </div>
		</div>
	</div>
</div> <!--// content E-->

<form action="" id="fibFrm" method="POST"></form>
