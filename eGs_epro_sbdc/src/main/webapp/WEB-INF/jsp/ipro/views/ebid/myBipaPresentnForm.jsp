<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 입찰관리 > 나의 입찰관리 상세 > 나의 입찰서 제출 폼
 *
 * <pre>
 * ebid
 *   	 |_ myBipaPresentnForm.jsp
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
<script type="text/javascript" src="${jsPath}/opro/views/ebid/myBipaPresentnForm.js"></script> 

<div id="panelSubContent">
        <h3 class="subTitle">입찰서 제출</h3>

        <h4 class="bulSubTitle">입찰개요</h4>
        <div class="tableLayer">
            <table class="table">
                <caption>입찰개요</caption>
                <colgroup>
                    <col width="170px">
                    <col width="320px">
                    <col width="170px">
                    <col width="320px">
                </colgroup>
                <tbody>
                <tr class="line">
                    <th>입찰공고번호</th>
                    <td>
                      	P2017-00004-1
                    </td>
                    <th>공고일자</th>
                    <td>
						2017-02-21 
                    </td>
                </tr>
                <tr>
                    <th>입찰명</th>
                    <td colspan="3">송변전분야 유지보수용 예비품-램프 등 17종  </td>
                </tr>
                <tr>
                    <th>공고구분</th>
                    <td> </td>
                    <th>입찰구분</th>
                    <td>물품</td>
                </tr>
                <tr>
                    <th>계약방법</th>
                    <td>
                    	일반경쟁 
                    </td>
                    <th>낙찰자선정방법</th>
                    <td>
                    협상에 의한 계약
                    </td>
                </tr>
                <tr>
                	<th>입찰한도액</th>
                	<td>
                		600,000,000원
                	</td>
                	<th>기초예가금액</th>
                	<td> 100,000,000 원
                	</td>
                </tr>
                </tbody>
            </table>
        </div>
        
		<h4 class="bulSubTitle">예가선택 [ 임의로 4개를 선택하세요.]</h4>
        <div class="tableLayer">
            <table class="table">
                <caption>복수예가선택</caption>
                <colgroup>
                    <col width="980px">
                </colgroup>
                <tbody>
                <tr class="line">
					<td>
						<c:forEach begin="1" end="15" varStatus="loop">
							<label for="compnoPrdprcNo${ loop.count}" class="checkFormLayer">
								<input type="checkbox" class="right_15" id="compnoPrdprcNo${ loop.count }" name="P_COMPNO_PRDPRC_NO" value="${loop.count}" style="border:0px;" onclick="checkPrdprcNo(this)">
							</label>&nbsp;&nbsp;&nbsp;
						</c:forEach>
					</td>
                </tr>
                </tbody>
            </table>
        </div>
        
		<h4 class="bulSubTitle">입찰금액</h4>
        <div class="tableLayer">
            <table class="table">
                <caption>입찰금액</caption>
                <colgroup>
                    <col width="170px">
                    <col width="810px">
                </colgroup>
                <tbody>
                <tr class="line">
                    <th>입찰금액</th>
                    <td id="abandnRegistDt">
                        <label for="P_BID_NM_S" class="blind">입찰금액</label>
               			￦ <input type="text" class="lineTxt disabled"  id="P_BDDPR_AMOUNT" name="P_BDDPR_AMOUNT" money  style="width: 300px;" value="" maxlength="22" onkeyup="conversionNumToKor(this);" readonly="readonly">  &nbsp;(일금 <font color="red"></font>원)
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
        
        <%-- 입찰구분이 [구매] 일 경우 활성화 --%>
        <h4 class="bulSubTitle">입찰품목정보</h4>
	 	<div class="tableLayer">
			<table class="tableList">
	            <caption>입찰품목정보</caption>
	            <colgroup>
	                <col width="40px"/>
	                <col width="300px"/>
	                <col width="200px"/>
	                <col width="70px"/>
	                <col width="70px"/>
	                <col width="150px"/>
	                <col width="150px"/>
	            </colgroup>
				<thead>
	                <tr>
	                    <th class="noBg">번호</th>
	                    <th>분류</th>
	                    <th>품명</th>
	                    <th>단위</th>
	                    <th>수량</th>
	                    <th>단가</th>
	                    <th>금액</th>
	                </tr>
	            </thead>
				<tbody>
						<tr class="row">
							<td>1</td> 
							<td class="left_T">컴퓨터장비및액세서리 </td> 
							<td class="left_T">노트북</td>
							<td>대</td>
							<td>10</td>
							<td>
								<input type="text" id="P_BID_UNTPC_AMOUNT" bidUntpcAmount style="width: 140px;" maxlength="22" money onkeyup="prdlstAcctoAmount(this, '10');">
								<input type="hidden" name="P_BID_UNTPC_ENCPT_VALUE" >
							</td> 
							<td><input type="text" class="disabled" name="sumAmount" style="width: 140px;" readonly money></td>
						</tr>
				</tbody>
			</table>
		</div>
		 <h4 class="bulSubTitle">제안요약서</h4> 
        <div>
				<!-- <div class="T_btnLayer cn n_m fr" style="float:left;">
					<button type="button" class="grayBtn S" id="setAllCheckOn">전체선택</button>
					<button type="button" class="grayBtn S" id="setAllCheckOff">전체해제</button>				
				</div> -->
				<div class="T_btnLayer cn n_m fr" style="float:right;">
		            <button type="button" class="grayBtn S" id="entrpsPlusBtn">문서추가</button>
		            <button type="button" class="grayBtn S" id="entrpsDeleteBtn">문서삭제</button>
		       	</div>
		       	
		       	<div class="tableLayer">
					<table class="tableList" id="entrpsTb">
				    	<colgroup>
				    		<col width="8%"/>
			                <col width="8%"/>
			                <col width="*"/>
			                <col width="10%"/>
			                <col width="10%"/>
				        </colgroup>
				        <thead>
			            	<tr >
				            	<th style="text-align: center;" class="noBg" scope="col">선택</th>
				            	<th style="text-align: center;" scope="col">순번</th>
				            	<th style="text-align: center;" scope="col">문서명</th>
				            	<th style="text-align: center;" scope="col">파일타입</th>
				            	<th class="copsntrA" style="text-align: center;" scope="col">파일사이즈</th>
			            	</tr>
			            
			            <tbody>
			            	<tr class="row" style="display: none;">
								<td style="text-align: center;">
									<label for="entrpsChoiceCbx" class="blind">체크박스</label>
									<input type="checkbox" id="entrpsChoiceCbx" name="entrpsChoiceCbx">
								</td>
								<td style="text-align: center;"></td>
								<td style="text-align: center;">
									<input type="file" style="width: 95%;"/>
				                </td>
								<td style="text-align: center;"></td>
								<td style="text-align: center;"></td>
			            	</tr>
			            </tbody>
				    </table>
		       	</div>
			</div>
        
        
		
		<!-- 파일업로드 -->
		<h4 class="bulSubTitle">투찰내역서</h4> 
		<div class="tableLayer"> 
            <table class="table">
                <caption>투찰내역서</caption>
                <colgroup>
                    <col width="170px">
                    <col width="*">
                </colgroup>
                <tbody> 
                <tr class="line">
                    <th>투찰내역서</th>
                    <td>
                    	<input type="file" name="bidSendFile" size="100" onchange="checkuploadFileExt(this);">
                    </td>
                </tr>
            	</tbody>
        	</table>
        </div>
        
		<!-- 파일업로드 END -->
		
		
		<h4 class="bulSubTitle">입찰담당자</h4>
        <div class="tableLayer">
            <table class="table">
                <caption>입찰담당자</caption>
                <colgroup>
                    <col width="170px">
                    <col width="320px">
                    <col width="170px">
                    <col width="320px">
                </colgroup>
                <tbody>
                <tr class="line">
                    <th>담당자명</th>
                    <td>
                        <input type="text" id="P_CHARGER_NM" name="P_CHARGER_NM" style="width: 200px;" maxlength="30" value="테스트">
                    </td>
                    <th>전화번호</th>
                    <td>
                        <input type="text" id="P_TELNO" name="P_TELNO" style="width: 200px;" maxlength="30" value="02-153-5555">
                    </td>
                </tr>
                <tr>
                    <th>이메일주소</th>
                    <td colspan="3">
                        <input type="text" id="P_EMAIL_ADRES" name="P_EMAIL_ADRES" style="width: 690px;" maxlength="50" value="eunwoo@eunwoosoft.com" eMail>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
        
        <div class="blueBox">
            동 입찰에 참여함에 있어 「대외무상협력사업에 관한 조달 및 계약규정」등 관련규정이 정하는 바에 따르며, 본 입찰과 관련된 모든 세부사항을 숙지하였음을 확인합니다.<br>
            동 입찰이 귀 협력단에 의하여 수락되면 계약상의 모든 조건에 따라 위의 금액으로 계약기간내에 업무를 완료할 것을 확약하며 입찰서를 제출합니다.<br><br>
            <label for="agreIem" class="mr_10">
	           <input type="radio" id="agreIemY" name="agreIem" class="mr_5" > 동의함
	           <input type="radio" id="agreIemN" name="agreIem" class="mr_5" > 동의안함
           </label>
        </div>
		
        <div class="T_btnLayer fr">
            <button type="button" class="blueBtn L" id="bipaPresentnBtn">가격서제출</button>
            <button type="button" class="blueBtn L" id="listBtn">목록</button>
        </div>
    </div>
    
<%--page move form --%> 
<form id="listFrm" class="search_form" method="POST" > 
	<input type="hidden" id="resourceName" name="resourceName" value="${param.resourceName}">
</form>

<form id="popupFrm" method="POST" >	</form> <!-- 청렴이행각서 팝업 -->
