<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%--
 *   입찰공고 상세 
 *
 * <pre>
 * ebid
 *  |_popup
 *   | 	  popupBidPblancDetail.jsp
 * 
 * </pre>
 * @date : 2017. 06. 23.
 * @version : 1.0
 * @author : 은우소프트 이주연 
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %>

<script type="text/javascript" src="${jsPath}/views/comm/popup/popupBidPblancDetail.js"></script>

<div id="windowPopup" class="w_92p">
	<h3 class="windowTitle"> 입찰공고 상세</h3>
	<%--Data View Area1 --%>
	<div class="tableLayer">
       <p class="popSubTitle marginSet">입찰개요</p>
		<table class="table"> 
	    	<caption>입찰개요</caption>
			<colgroup>
				<col width="15%">
                <col width="35%">
                <col width="15%">
                <col width="35%"> 
			</colgroup>
	        <tbody>
				<tr class="line">
                    <th>입찰공고번호</th>
                    <td>P2017-00004-1</td>
                    <th>공고일자</th>
                    <td>2017-02-21</td>
                </tr>
	        	<tr>
		            <th>입찰명</th>
		            <td colspan="3">송변전분야 유지보수용 예비품-램프 등 17종 </td>
		        </tr>
                <tr>
                    <th >공고구분</th>
                    <td></td>
                    <th >입찰구분</th>
                    <td>물품</td>
                </tr>
                <tr>
	                <th>긴급입찰여부</th>
					<td>아니오</td>
					<th>지문입찰여부</th>
					<td>아니오</td>
				</tr>
				<tr>
	                <th>산출내역서 제출필수여부</th>
					<td colspan="3">아니오</td>
				</tr>
				<tr>
	                <th>입찰한도액</th>
					<td colspan="3">600,000,000원</td>
				</tr>
	        </tbody>
	    </table>
	</div>
	    
	    <%--Data View Area2 --%>
	<div class="tableLayer">
     <p class="popSubTitle marginSet">입찰방법</p>
		<table class="table">
	    	<caption>입찰방법</caption>
			<colgroup>
				<col width="15%">
                <col width="35%">
                <col width="15%">
                <col width="35%"> 
			</colgroup>
	        <tbody>
				<tr class="line">
                    <th>국제입찰여부</th>
                    <td>아니오</td>
                     <th>나라장터 게시여부</th>
                    <td>예</td>
                </tr>
	        	<tr>
		            <th>계약방법</th>
		            <td>일반경쟁 </td> 
		            <th>낙찰자선정방법</th>
		            <td>협상에 의한 계약</td>
		        </tr>
		        <tr>
		            <th>복수예가여부</th>
		            <td colspan="3">복수예가</td>
		        </tr>
		        <tr>
		            <th>입찰설명회여부</th>
		            <td colspan="3">아니오</td>
		        </tr>
                <tr>
                    <th>입찰보증금 납부형태</th>
                    <td colspan="3" >
                    <textarea id="P_BID_GTN_PAY_STLE_CN" rows="5" cols="70" readonly taView style="display: none;"><c:out value="은우소프트 대외무상협력사업에관한조달및계약규정시행세칙 제26조에 의거 입찰금액의 100분의 5 이상 현금 또는 보증보험증권 등으로 제출하되, 은우소프트와 계약이행 실적이 있는 조달협력업체 또는 국가가 기본재산의 50%이상 출연한 법인은 별도의 입찰보증금 납부없이 입찰참가신청서상에 입찰보증금 지급각서로 대체하며, 낙찰자가 계약을 체결하지 않을 시 입찰보증금은 우리 은우소프트에 귀속됩니다. <br>" /></textarea>
		    <!--            은우소프트 대외무상협력사업에관한조달및계약규정시행세칙 제26조에 의거 입찰금액의 100분의 5 이상 현금 또는 보증보험증권 등으로 제출하되, 은우소프트와 계약이행 실적이 있는 조달협력업체 또는 국가가 기본재산의 50%이상 출연한 법인은 별도의 입찰보증금 납부없이 입찰참가신청서상에 입찰보증금 지급각서로 대체하며, 낙찰자가 계약을 체결하지 않을 시 입찰보증금은 우리 은우소프트에 귀속됩니다. <br>
			 -->		</td>
                </tr>
                 <tr>
                    <th>입찰참가자격</th>
                    <td colspan="3" >
                     <textarea id="P_BID_GTN_PAY_STLE_CN" rows="5" cols="70" readonly taView style="display: none;"><c:out value="①은우소프트 조달협력업체로 등록을 필한 업체<br>②입찰설명회에 참석한 업체<br>③공동도급(공동이행방식)도 가능하며 공동수급체의 구성원 수는 대표자를 포함 2인 (2개 업체)이내로 한함.<br>  - 단, 종계약자는 10%이상 참여 필요<br>" /></textarea>
					</td>
                </tr>
                <tr>
                    <th>공동수급 여부</th>
                    <td colspan="3" >
						단독이행  
					</td>
                </tr>
	        </tbody>
	    </table>
	</div>
	
	<%--Data View Area1 --%>
	<div class="tableLayer">
      <p class="popSubTitle marginSet">계약조건</p>
		<table class="table">
	    	<caption>계약조건</caption>
			<colgroup>
				<col width="15%">
                <col width="35%">
                <col width="15%">
                <col width="35%"> 
			</colgroup>
	        <tbody>
				<tr class="line">
                    <th>지체상금률</th>
                    <td>0.25 %</td>
                    <th>하자이행보증금률</th>
                    <td>3 %</td>
                </tr>
	        	<tr>
		            <th>계약보증금률</th>
		            <td colspan="3">20 %</td>
		        </tr>
	        </tbody>
	    </table>
	</div>
	
	
<%--Data View Area1 --%>
  <p class="popSubTitle marginSet">입찰진행순서</p>
		<table class="table">
	    	<caption>입찰진행순서</caption>
			<colgroup>
				<col width="15%">
                <col width="35%">
                <col width="15%">
                <col width="35%"> 
			</colgroup>
	        <tbody>
				<tr class="line">
                    <th>입찰공고 일시</th>
                    <td colspan="3">2017-02-21 09:00</td>
                </tr>
                <tr>
                	<th>입찰설명회 일시</th>
                    <td colspan="3">2017-02-22 09:00 ~ 2017-02-22 18:00</td>
                </tr>
                <tr>
                	<th>참가신청서 제출기간</th>
                    <td colspan="3">2017-02-20 09:00 ~ 2017-02-27 18:00</td>
                </tr>
	        	<tr>
		            <th>입찰서 제출기간</th>
		            <td colspan="3">2017-02-27 09:30 ~ 2017-03-04 18:30 </td>
		        </tr>
		        <tr>
		            <th>개찰일시</th>
		            <td colspan="3">2017-03-05 09:00 </td>
		        </tr>
	        </tbody>
	    </table>
	
	<%--Data View Area1 --%>
	<br>
       <p class="popSubTitle marginSet">입찰품목정보</p>
       <div class="tableLayer">
	 	<table class="tableList" summary="입찰품목정보 입니다.">
	            <caption>입찰품목정보</caption>
	            <caption>입찰품목정보</caption>
	            <colgroup>
	                 <col width="5%">
	                <col width="15%">
	                <col width="*">
	                <col width="10%"> 
	                <col width="10%">
	                <col width="10%">
	            </colgroup>
				<thead>
	                <tr>
	                    <th scope="col" class="noBg">번호</th>
	                    <th scope="col">품명번호</th>
	                    <th scope="col">품명</th>
	                    <th scope="col">참조사항</th>
	                    <th scope="col">단위</th>
	                    <th scope="col">수량</th>
	                </tr>
	            </thead>
				<tbody>
						<tr class="row">
							<td>1</td>
							<td>32101516</td>
							<td class="left_T"> 노트북컴퓨터 </td>
							<td> 컴퓨터 </td>
							<td> 대</td>
							<td> 10 </td> 
						</tr>
				</tbody>
			</table> 
			</div>
	
		<%-- <div class="tableLayer">
        <p class="popSubTitle marginSet">입찰품목정보</p>
			<table class="tableList" summary="입찰품목정보 입니다.">
	            <caption>입찰품목정보</caption>
	            <colgroup>
	                <col width="5%">
	                <col width="15%">
	                <col width="*">
	                <col width="10%"> 
	                <col width="10%">
	                <col width="10%">
	            </colgroup>
				<thead>
	                <tr>
	                    <th scope="col" class="noBg">번호</th>
	                    <th scope="col">품명번호</th>
	                    <th scope="col">품명</th>
	                    <th scope="col">참조사항</th>
	                    <th scope="col">단위</th>
	                    <th scope="col">수량</th>
	                </tr>
	            </thead>
				<tbody>
						<tr class="row">
							<td>1</td>
							<td>10101506</td>
							<td class="left_T">말</td>
							<td class="left_T">참조사항</td>
							<td>마리</td>
							<td>1</td> 
						</tr>
				</tbody>
			</table> 
		</div> --%>
		
        <div class="tableLayer">
		   <p class="popSubTitle marginSet">담당자 정보</p>
            <table class="table" summary="담당자 정보 입니다.">
                <caption>당당자 정보</caption>
                <colgroup>
                    <col width="15%">
	                <col width="35%">
	                <col width="15%">
	                <col width="35%"> 
                </colgroup>
                <tbody>
                <tr class="line">
                    <th scope="row">입찰담당자</th>
                    <td>
                       		사용자1
                    </td>
                    <th scope="row">사업담당자(의뢰자)</th>
                    <td>
                        	사용자3
                    </td>
                </tr>
                <tr>
                    <th scope="row">입찰담당자 이메일</th>
                    <td>
                       		test1@mail.com
                    </td>
                    <th scope="row">사업담당자 이메일</th>
                    <td>
                     		test3@mail.com
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
        
   		<div class="T_btnLayer fr">
            <button type="button" class="blueBtn L" id="closeBtn" onclick="window.close();" style="margin-bottom: 10px; ">닫기</button>
        </div>
    <br><div></div>
    </div>
