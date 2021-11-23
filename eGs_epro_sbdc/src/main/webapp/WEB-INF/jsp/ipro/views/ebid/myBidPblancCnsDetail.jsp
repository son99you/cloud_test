<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 입찰관리
 * 		> 나의 입찰공고 상세 (공사)
 *
 * <pre>
 * ebid 
 *    |_ myBidPblancCnsDetail.jsp
 *  
 * </pre>
 * @date : 2017. 06.20
 * @version : 1.0
 * @author : 은우소프트 이주연 
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 

<link rel="stylesheet" type="text/css" href="${cssPath}/opro/supplierCommon.css">
 
<script type="text/javascript" src="${jsPath}/comm/comUtil.js"></script>  
<script type="text/javascript" src="${jsPath}/opro/views/ebid/myBidPblancCnsDetail.js"></script> 
  
 
<div id="panelSubContent">
	<h3 class="subTitle">나의 입찰공고 상세</h3>
	
        		<div class="processStep"> 
	            <div class="on" id="biddingId">공고중</div>
	            <img src="${imagePath}/opro/icon/ico_arrow.png" alt="다음 단계"> 
	             <div  class=""  id="dcPeoId">입찰설명회</div>
	            <img src="${imagePath}/opro/icon/ico_arrow.png" alt="다음 단계"> 
		        <div class=""  id="pareTxtId"> 참가신청 </div> 
		        <img src="${imagePath}/opro/icon/ico_arrow.png" alt="다음 단계"> 
	            <div class=""  id="pcOfePresentnTxtId">입찰서제출</div>
                <img src="${imagePath}/opro/icon/ico_arrow.png" alt="다음 단계"> 
	            <div  class="" id="opengComptId">개찰</div>
	        </div>
	
	<%--Data View Area1 --%>
    <h4 class="bulSubTitle">입찰개요</h4>
	<div class="tableLayer">
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
                    <td>P2017-00003-1</td>
                    <th>공고일자</th>
                    <td>2016-12-01</td>
                </tr>
	        	<tr>
		            <th>입찰명</th>
		            <td colspan="3">태원광업㈜하장광산 집진기 설치공사  </td>
		        </tr>
                <tr>
                    <th >공고구분</th>
                    <td></td>
                    <th >입찰구분</th>
                    <td>공사 </td>
                </tr>
                <tr>
	                <th>긴급입찰여부</th>
					<td>아니오</td>
					<th>지문입찰여부</th>
					<td>아니오</td>
				</tr>
				<tr>
	                <th>산출내역서 <br>제출필수여부</th>
					<td>아니오</td>
					<th>기자재 포함 여부</th>
					<td>아니오</td>
				</tr>
				<tr>
	                <th>입찰범위</th>
					<td colspan="3">입찰범위</td>
				</tr>
				<tr>
	                <th>계약기간</th>
					<td colspan="3">계약기간</td>
				</tr>
				<tr>
	                <th>입찰한도액</th>
					<td colspan="3">600,000,000원</td>
				</tr>
	        </tbody>
	    </table>
	</div>
	    
	    <%--Data View Area2 --%>
    <h4 class="bulSubTitle">입찰방법</h4>
	<div class="tableLayer">
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
		            <td>최저가</td>
		        </tr> 
		        <tr>
		            <th>복수예가여부</th>
		            <td>복수예가</td>
					<th>낙찰하한율</th>
		            <td>86%</td>
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
    <h4 class="bulSubTitle">계약조건</h4>
	<div class="tableLayer">
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
    <h4 class="bulSubTitle">입찰진행순서</h4>
	<div class="tableLayer">
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
                    <td colspan="3">2017-02-21 11:20</td>
                </tr>
                <tr>
                	<th>참가신청서 제출기간</th>
                    <td colspan="3">2017-02-21 11:21 ~ 2027-02-21 11:30</td>
                </tr>
	        	<tr>
		            <th>입찰서 제출기간</th>
		            <td colspan="3">2027-02-21 11:30 ~ 2027-02-21 11:40 </td>
		        </tr>
		        <tr>
		            <th>개찰일시</th>
		            <td colspan="3">2027-02-21 11:40 </td>
		        </tr>
	        </tbody>
	    </table>
	</div>
	
	    <h4 class="bulSubTitle">입찰규정 및 지침</h4>
		<div class="tableLayer">
			<table class="table">
		    	<caption>입찰규정 및 지침</caption>
				<colgroup>
					<col width="15%">
	                <col width="35%">
	                <col width="15%">
	                <col width="35%">
				</colgroup>
		        <tbody>
					<tr>
	                	<th scope="row" class="vtop">첨부파일</th> 
						<td colspan="3" class="vtop"  style="margin-top: 3px; line-height: 30px;"> 
							<!-- <button type="button" class="btn btn_search02" title="검색" style="float: right; margin-top: 3px;" id="fileBtn"></button> -->
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
		
		 <h4 class="bulSubTitle">첨부파일</h4>
		<div class="tableLayer">
			<table class="table">
		    	<caption>첨부파일</caption>
				<colgroup>
					<col width="15%">
	                <col width="35%">
	                <col width="15%">
	                <col width="35%">
				</colgroup> 
		        <tbody>
					<tr>
	                	<th scope="row" class="vtop">첨부파일</th> 
						<td colspan="3" class="vtop"  style="margin-top: 3px; line-height: 30px;"> 
							<!-- <button type="button" class="btn btn_search02" title="검색" style="float: right; margin-top: 3px;" id="fileBtn2"></button> -->
	                    	<div id="fileRow2" style="display: none; height: 30px;"> 
	                    		<input type="file" name="" onchange="fileChange(this)" style="display: none;">
	                    		<button type="button" class="btn btn_del" style="float: right; display: inline-block; margin-top: 3px;" onclick="rowDel(this)">삭제</button>
	                    	</div> 
	                    	<div id="fileDiv2" style="width: 680px; line-height: 30px;">
	                    	</div>
						</td>
		             </tr>
		        </tbody>
		    </table>
		</div>
		
		<h4 class="bulSubTitle">담당자 정보</h4>
        <div class="tableLayer">
            <table class="table" summary="담당자 정보 입니다.">
                <caption>당당자 정보</caption>
                <colgroup>
                    <col width="150px">
                    <col width="300px">
                    <col width="160px"> 
                    <col width="370px">
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
        
        <h4 class="bulSubTitle">의견정보 ( <font id="opinionTotcnt">1</font> 건 )</h4>
        <div class="T_btnLayer fl cn n_m">
            <button type="button" class="grayBtn S" id="showBtn">+</button>
            <button type="button" class="grayBtn S" id="hideBtn">-</button>
        </div>
        <div class="T_btnLayer fr cn n_m">
            <button type="button" class="grayBtn S" id="opinionRegistBtn" style="display: " onclick="bidPblancOpinionRegistForm('','new')">의견등록</button>
        </div>
	 	<div class="tableLayer">
			<table class="tableList" id="opinionInfoTb" summary="의견정보 입니다.">
	            <caption>의견정보</caption>
	            <colgroup>
	                <col width="40px">
	                <col width="540px">
	                <col width="160px">
	                <col width="120px">
	                <col width="120px">
	            </colgroup>
				<thead>
	                <tr>
	                    <th scope="col" class="noBg">번호</th>
	                    <th scope="col">제목</th>
	                    <th scope="col">등록자</th>
	                    <th scope="col">등록일자</th>
	                    <th scope="col">답변여부</th>
	                </tr>
	            </thead>
				<tbody id="opinionInfoToggle" style="display:none;">
						<tr class="row" onclick="bidPblancOpinionRegistForm('1','detail');" style="cursor: pointer;">
							<td>1</td>
							<td class="left_T" >제목</td>
							<td>등록자</td> 
							<td>2017-01-10</td>
							<td>아니오</td>
						</tr>
				</tbody>
				<tbody id="opinionInfoAdd">
					<tr style="display: none;"><td></td></tr>
				</tbody>
			</table>  
		</div> 
		
		<div class="T_btnLayer fr">
	        <button type="button" class="blueBtn L" id="reqstdocWritngBtn"  disabled>참가신청서작성</button>
            <button type="button" class="blueBtn L" id="listBtn">목록</button>
        </div>
        
    <%--page move form --%>
	<form id="listFrm" class="search_form" method="POST" > 
		<input type="hidden" id="resourceName" name="resourceName" value="${param.resourceName}">
	</form>
	<form id="RegistFrm" class="search_form" method="POST" > 
		<input type="hidden" id="resourceName" name="resourceName" value="${param.resourceName}">
	</form>
</div>