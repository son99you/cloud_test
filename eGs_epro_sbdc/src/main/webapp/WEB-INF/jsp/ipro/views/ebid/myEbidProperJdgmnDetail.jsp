<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
입찰관리 > 나의 적격심사 상세
 *
 * <pre>
 * ebid 
 *   	 |_ myEbidProperJdgmnDetail.jsp
 * </pre>
 * @date : 2017. 06. 22.
 * @version : 1.0
 * @author : 은우소프트 이주연
--%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %>

<script type="text/javascript" src="${jsPath}/comm/comUtil.js"></script>  
<script type="text/javascript" src="${jsPath}/opro/views/ebid/myEbidProperJdgmnDetail.js"></script>  
 
<div class="contents"> 
	<div id="panelSubContent">
		
        <h3 class="subTitle">나의 적격심사 상세</h3>
        <h4 class="bulSubTitle">심사대상물품</h4>
        
        <div class="tableLayer">
            <table class="table">
                <caption>심사대상물품</caption>
                <colgroup>
                    <col width="170px">
                    <col width="320px">
                    <col width="170px">
                    <col width="320px">
                </colgroup>
                <tbody>
                <tr class="line">
                    <th>입찰공고번호</th>
                    <td>P2017-00004-1 </td>
                    <th>공고일자</th>
                    <td>2017-02-21 16:29:00  &nbsp;</td>
                </tr>
                <tr>
                    <th>입찰명</th>
                    <td colspan="3">송변전분야 유지보수용 예비품-램프 등 17종  &nbsp;</td>
                </tr>
                <tr>
                    <th>입찰금액(원)</th>
                    <td colspan="3">￦&nbsp; 490,000 &nbsp;</td>
                </tr>
                
                </tbody>
            </table>
        </div>
        
        
        <h4 class="bulSubTitle">심사대상입찰자</h4>
        <div class="tableLayer">
            <table class="table">
                <caption>심사대상입찰자</caption>
                <colgroup>
                    <col width="170px">
                    <col width="320px">
                    <col width="170px">
                    <col width="320px">

                </colgroup>
                <tbody>
                <tr class="line">
                    <th>업체명</th>
                    <td>은우소프트&nbsp;</td>
                    <th>대표자</th>
                    <td>정한규&nbsp;</td>
                </tr>
                <tr>
                	<th>주사업내용</th>
                	<td colspan="3"> &nbsp;</td>
                </tr>
                </tbody>
            </table>
        </div>

		<h4 class="bulSubTitle">회사를 대표하는 연락책임자 인적사항</h4>
		<div class="tableLayer">
            <table class="table">
                <caption>회사를 대표하는 연락책임자 인적사항</caption>
                <colgroup>
                    <col width="170px">
                    <col width="320px">
                    <col width="170px">
                    <col width="320px">
                </colgroup>
                <tbody>
                <tr class="line">
                  	<th>성명</th>
                  	<td>이만영&nbsp;</td>
                  	<th>직위</th>
                  	<td>이사&nbsp;</td>
                </tr>
                <tr>
					<th>담당부서</th>
                  	<td>전략사업팀&nbsp;</td>
                  	<th>휴대전화</th>
                  	<td>010-1111-2222&nbsp;</td>                
               	</tr>
                <tr>
                  	<th>전화번호</th>
                  	<td>02-841-8120&nbsp;</td>
                  	<th>팩스번호</th>
                  	<td>02-841-0721&nbsp;</td>
                </tr>
                <tr></tr>
                </tbody>
            </table>
        </div> 
        
        <h4 class="bulSubTitle">적격심사 관련 첨부파일</h4>
		
        <div>
				<div class="T_btnLayer cn n_m fr" style="float:left;">
					<button type="button" class="grayBtn S" id="setAllCheckOn">전체선택</button>
					<button type="button" class="grayBtn S" id="setAllCheckOff">전체해제</button>				
				</div>
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
        
        
        
        
        
        
        
        
        
        
        
        <div class="tableLayer">
        <h4 class="bulSubTitle">종합평가</h4>
		<form id="registFrm" method="POST" action="${contextPath}/elbi/properJdgmnEvlRegist.do">
		<input type="hidden" name="P_PBLANC_NO" value="${ P_PBLANC_NO }" />
		<input type="hidden" name="P_PBLANC_ODR"  value="${ P_PBLANC_ODR }" />
		<input type="hidden" name="P_ENTRPS_REGIST_NO" value="${sessionScope.loginResult.USER_ID }"/>
		<input type="hidden" name="resourceName" value="${param.resourceName}">
		<input type="hidden" name="P_PRCURE_SE_CD" value="1"/>
		<input type="hidden" name="P_PRCURE_DETAIL_SE_CD" value="${myProperJdgmnDetail.PRCURE_DETAIL_SE_CD }"/>
		<table class="tableList">
            <caption>종합평가</caption>
            <colgroup>
                <col width="40px"/>
                <col width="160px"/>
                <col width="140px"/>
                <col width="140px"/>
                <col width="140px"/>
            </colgroup>			
			<thead>
                <tr>
                    <th>번호</th>
                    <th>구분</th>
                    <th>배점한도</th>
                    <th>자기평점(점)</th>
                    <th>심사평점(점)</th>
                </tr>
            </thead>
			<tbody id = "entrpsJdgmnEvlListJson">
					<tr class="row">
						<td>1	<input type="hidden" name="entrpsJdgmnEvlListLength" value="0"/>
								</td>
						<td>경영상태<input type="hidden" name="P_EVL_SN" value="경영상태"/></td>
						<td class="LMT">30<input type="hidden" id="allotLmtScore[0]" value="30"/></td>
						<td>	
						  		<label for="entrpsEvlScore_0" class="blind">경영상태의 자기평점 입력</label>
						  		<input type="text" style="text-align: center;" id="entrpsEvlScore_0" name="P_ENTRPS_EVL_SCORE" onchange="entrpsEvlSm(this,'30');" onkeyup="entrpsEvlSm(this,'30');" value="30" jdgmn>
						</td>
						<td>	
								<input type="hidden" style="text-align: center;" id="koicaEvlScore[0]" name="P_KOICA_EVL_SCORE" onchange="entrpsEvlSm(this,'30');" onkeyup="entrpsEvlSm(this,'30');" value="">
						</td>
					</tr>
					<tr class="row">
						<td>2 <input type="hidden" name="entrpsJdgmnEvlListLength" value="1"/></td>
						<td>신인도<input type="hidden" name="P_EVL_SN" value="신인도"/></td>
						<td class="LMT">+3~-2<input type="hidden" id="allotLmtScore[1]" value="+3~-2"/></td>
						<td>	
						  		<label for="entrpsEvlScore_1" class="blind">신인도의 자기평점 입력</label>
						  		<input type="text" style="text-align: center;" id="entrpsEvlScore_1" name="P_ENTRPS_EVL_SCORE" onchange="entrpsEvlSm(this,'+3~-2');" onkeyup="entrpsEvlSm(this,'+3~-2');" value="-2" jdgmn>
						</td>
						<td>	
								<input type="hidden" style="text-align: center;" id="koicaEvlScore[1]" name="P_KOICA_EVL_SCORE" onchange="entrpsEvlSm(this,'+3~-2');" onkeyup="entrpsEvlSm(this,'+3~-2');" value="">
						</td>
					</tr>
					<tr class="row">
						<td>3<input type="hidden" name="entrpsJdgmnEvlListLength" value="2"/></td>
						<td>결격사유<input type="hidden" name="P_EVL_SN" value="결격사유"/></td>
						<td class="LMT">-30<input type="hidden" id="allotLmtScore[2]" value="-30"/></td>
						<td>	
						  		<label for="entrpsEvlScore_2" class="blind">결격사유의 자기평점 입력</label>
						  		<input type="text" style="text-align: center;" id="entrpsEvlScore_2" name="P_ENTRPS_EVL_SCORE" onchange="entrpsEvlSm(this,'-30');" onkeyup="entrpsEvlSm(this,'-30');" value="-10" jdgmn>
						</td>
						<td>	
								<input type="hidden" style="text-align: center;" id="koicaEvlScore[2]" name="P_KOICA_EVL_SCORE" onchange="entrpsEvlSm(this,'-30');" onkeyup="entrpsEvlSm(this,'-30');" value="">
						</td>
					</tr>
					<tr class="row">
						<td>4	<input type="hidden" name="entrpsJdgmnEvlListLength" value="3"/></td>
						<td>입찰가격<input type="hidden" name="P_EVL_SN" value="입찰가격"/></td> 
						<td class="LMT">70<input type="hidden" id="allotLmtScore[3]" value="70"/></td>
						<td>	
						  		<label for="entrpsEvlScore_3" class="blind">입찰가격의 자기평점 입력</label>
						  		<input type="text" readonly="readonly" class="disabled" style="text-align: center;" id="entrpsEvlScore3" name="P_ENTRPS_EVL_SCORE_BID_PC" onchange="entrpsEvlSm();" value="69.8"/>
						</td>
						<td>	
								<input type="hidden" style="text-align: center;" id="koicaEvlScore[3]" name="P_KOICA_EVL_SCORE" onchange="entrpsEvlSm(this,'70');" onkeyup="entrpsEvlSm(this,'70');" value="">
						</td> 
					</tr>
					<tr class="row">
						<td colspan="2">계</td>
						<td>
								<label for="allotlmtTot" class="blind">배점한도 총점</label>
								<input type="text" style="border:0px; text-align: center;" readonly="readonly" class="disabled" id="allotlmtTot" name="allotlmtTot" value="100"/></td>
						<td>
								<label for="entrpsEvlTot" class="blind">자기평점 총점</label>
								<input type="text" style="border:0px; text-align: center;" readonly="readonly" class="disabled" id="entrpsEvlTot" name="entrpsEvlTot" /></td>
						<td>
								<label for="koicaEvlTot" class="blind">심사평점 총점</label>
								<input type="text" style="border:0px; text-align: center;" readonly="readonly" class="disabled" id="koicaEvlTot" name="koicaEvlTot" value="0.0" /></td>
								
					</tr>
			</tbody> 
		</table>
		</form> 
		</div>
        
        
        <div class="T_btnLayer fr">
        	<button type="button" class="blueBtn L" id="registBtn">저장</button>
            <button type="button" class="blueBtn L" id="scoreDspthBtn">자가점수통보</button>
            <button type="button" class="blueBtn L" id="listBtn">목록</button>
        </div>
    </div>
 </div>
    
   	<form id="listFrm" method="POST" >
   		<input type="hidden" id="resourceName" name="resourceName" value="${param.resourceName}">
	</form>
	
	<form id="detailFrm" method="POST" >
		<input type="hidden" id="resourceName" name="resourceName" value="${param.resourceName}">
	</form>
	
