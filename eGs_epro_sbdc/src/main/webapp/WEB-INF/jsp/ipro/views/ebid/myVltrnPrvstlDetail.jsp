<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 수의시담 상세
 *
 * <pre>
 * ebid
 *    |_ myVltrnPrvstlDetail.jsp
 * 
 * </pre>
 * @date : 2017. 06. 20
 * @version : 1.0
 * @author : 은우소프트 홍찬일
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %> 
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %> 

<link rel="stylesheet" type="text/css" href="${cssPath}/opro/supplierCommon.css">

<script type="text/javascript" src="${jsPath}/opro/views/ebid/myVltrnPrvstlDetail.js"></script> 
 
<div id="panelSubContent">
	<h3 class="subTitle">나의 수의시담 상세</h3>
	<div class="tableLayer">
	    <table class="table">
	        <caption>기본정보</caption>
	        <colgroup>
	            <col width="15%">  
	            <col width="35%">  
	            <col width="15%">  
	            <col width="35%">  
	        </colgroup>
	        <tbody>
	        <tr class="line">
	            <th>의뢰번호</th>
	            <td>V2017-00100 </td>
	            <th>의뢰일자</th>
	            <td>2017-06-15</td>
	        </tr>
	        <tr>
	            <th>계약방법</th>
	            <td>수의계약</td>
	            <th>집행한도액</th>
	            <td>51,000,000원</td>
	        </tr>
	        <tr>
	            <th>의뢰명</th>
	            <td colspan="3">EBS 프로그램 연장구매 수의계약 의뢰</td>
	        </tr>
	        <tr>
	            <th>시담선정업체</th>
	            <td>㈜은우소프트</td>
	            <th>업체전화번호</th>
	            <td>02-841-0721</td>
	        </tr>
	        <tr>
	            <th>의뢰자</th>
	            <td>홍찬일</td>
	            <th>의뢰부서</th>
	            <td>사업총괄팀</td>
	        </tr>
	        <tr>
	            <th>시담일시</th>
	            <td colspan="3">2017-06-15 18:00</td>
	        </tr>
	        <tr>
	            <th>시담담당자</th>
	            <td>이주연</td>
	            <th>시담 담당부서</th>
	            <td>지식정보팀</td>
	        </tr>
	        <tr>
	            <th>납품기한</th>
	            <td colspan="3">90일 이내</td>
	        </tr>
	        <tr>
	            <th>시담공지내용</th>
	            <td colspan="3">공지내용입니다.</td>
	        </tr>
	        <tr>
	            <th>첨부파일</th>
	            <td colspan="3"><a href="#">첨부파일1</a><br><a href="#">첨부파일2</a></td>
	        </tr>
	        </tbody>
	    </table>
	</div>
		<br>
		<table>
        <tr>
        <td>
        <h4 class="bulSubTitle">시담진행</h4>
	        <div class="tableLayer" style="width: 600px;">
	            <table class="table" style="width: 600px;">
	                <caption>기본정보</caption>
	                <colgroup>
	                    <col width="300px">
	                    <col width="300px">
	                </colgroup>
	                <tbody>
	                <tr class="line" style="width:600px;">
	                	<th class="thNoneStyle" style="width: 300px; text-align: center;">시담 담당자</th>
	                	<th class="thNoneStyle" style="width: 300px; text-align: center;">업체 담당자</th>
	                </tr>
	                <tr>
	                	<td colspan="2" style="padding : 0px; width: 600px;">
	                	<div id="progrsList" style="height: 200px; width: 600px; overflow-x: hidden;" >
	                		<table border="0" cellspacing="0" cellpadding="0" style="overflow-y: scroll;" >
                				<tbody id="TrgetFrame">
	                			</tbody>
               					<tr>
               						<td width="300px" style="text-align: left; border:0px;">&nbsp;</td>
	                				<td width="300px" style="text-align: right; border:0px;">안녕하세요~<br>2017-06-23 16:17:03 <br> </td>
                				</tr>
                				<tr>
               						<td width="300px" style="text-align: left; border:0px;">반갑습니다~<br>2017-06-23 16:10:40<br> </td>
               						<td width="300px" style="text-align: right; border:0px;">&nbsp;</td>
                				</tr>
	                		</table>
	                	</div>
	                	</td>
	                </tr>
	                <tr>
	                	<td colspan="2">
	                		<div class="fl" style="margin-top: 5px; width: 100%;">
	                			<font style="text-align: center;">입력 : &nbsp; </font>
	                			<label for="P_PRVSTL_CN" class="blind">대화내용 입력</label>
	                			<input type="text" id="msg"  name="msg" size="78">
	                			<div class="T_btnLayer fr" style="margin-top: 5px;">
	                			<button type="button" class="grayBtn S" id="msgBtn">전송</button>
	                			</div>
	                		</div>
	                	</td>
	                </tr>
	                </tbody>
	            </table>
	         </div>
        </td>
        <td style="padding-left: 40px; vertical-align:top;">
        <h4 class="bulSubTitle">업체 제시 금액</h4><div class="fr">단위(원)</div>
        <input type="hidden" id="P_PLNPRC_AMOUNT" name="P_PLNPRC_AMOUNT" value="51000000">
        <div class="tableLayer">
	            <table class="table" style="width: 340px;">
	                <caption>기본정보</caption>
	                <colgroup>
	                    <col width="170px">
	                    <col width="170px">
	                </colgroup>
	                <tbody>
	                
	                	<th class="thNoneStyle" style="text-align: center;">예가 비교</th>
	                	<th class="thNoneStyle" style="text-align: center;">업체 제시 금액</th>
	                </tr>
	                <tr>
	                	<td colspan="2" style="padding : 0px; width: 340px;">
		                	<div id="ntatAmountList" style="overflow-x: hidden; height: 200px; width: 340px;" >
		                		<table border="0" cellspacing="0" cellpadding="0" style="overflow-y: scroll;" >
			                		<tbody id="TrgetFrame1">
		                			</tbody>
	                			</table>
		                	</div>
	                	</td>
	                </tr>
	                <tr>
	                	<td colspan="2">
	                	<form id="ntatRegistFrm" method="POST">
	                		<div class="fl" style="margin-top: 5px; width: 100%;">
	                			<span id="NTAT_AMOUNT_TEXT" >금액 : &nbsp;</span>
	                			<label for="msg1" class="blind">협상금액 입력</label>
	                			<input type="text" id="msg1"  name="msg1" size="35" money >
	                			<div class="T_btnLayer fr" style="margin-top: 5px;">
	                			<button type="button" class="grayBtn S" id="msgBtn1" >전송</button>
	                			</div>
	                		</div>
	                	</form>
	                	</td>
	                </tr>
	                </tbody>
	            </table>
	         </div>
        </td>
        </tr>
        </table>
		<h4 class="bulSubTitle">결정금액 (<font color="red">귀사가 제시한 아래의 금액으로 수의 계약을 체결합니다. </font>)</h4>
	        <div class="tableLayer" style="width: 600px;">
			    <div id="lastNtatAmount">
			    	<table class="table" style="width: 600px;">
                		<caption>결정금액</caption>
		                <colgroup>
		                    <col width="100px">
		                    <col width="500px">
		                </colgroup>
		                <tbody>
			                <tr class="line" style="width:600px;">
			                	<th style="width: 100px;"><font color="red">결정금액</font></th>
			                	<td style="width: 500px;">
                				<div style="margin: 10px 0;">
             					<input type="text" id="LAST_NTAT_AMOUNT_TEXT" money>
								<input type="hidden" id="P_LAST_NTAT_AMOUNT" name="P_LAST_NTAT_AMOUNT" money value=""   size="50">
		                				(원)
                				<div class="T_btnLayer fr" style="margin: 0px;">
               						<button type="button" class="grayBtn S" id="prvstlCnclsBtn">체결확인</button>
               					</div>
	                			</div>
		                			
			                	</td>
			                </tr>
		                </tbody>
		            </table>
	  			 </div>
	         </div>
</div>

<%-- DETAIL FORM --%>
<form id="detailFrm" method="POST" > 
	<input type="hidden" name="resourceName" value="${ param.resourceName }" >
</form>