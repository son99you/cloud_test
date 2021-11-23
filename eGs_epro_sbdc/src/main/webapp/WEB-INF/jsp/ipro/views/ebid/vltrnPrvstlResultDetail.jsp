<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 수의시담 > 수의시담 상세 - 시담진행
 *
 * <pre>
 *vlpr 
 *    |_ oepVlprVltrnPrvstlProgrsDetail.jsp
 * 
 * </pre>
 * @date : 2015. 03. 09. 오후 12:01:44
 * @version : 1.0
 * @author : 은우소프트 손연우
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %> 
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %> 

<script type="text/javascript" src="${jsPath}/opro/views/ebid/vltrnPrvstlProgrsDetail.js"></script> 
<script src="/nxts/js/nxts/nxts.min.js"></script>
<script src="/nxts/js/nxts/nxtspki_config.js"></script>
<script src="/nxts/js/nxts/nxtspki.js"></script>

<ul class="step_wrap">
	<li><a href="#">전자시담</a></li>
	<li><a href="#">시담요청현황</a></li>
</ul> <!--// step_wrap E -->
<div class="tit_wrap">
	<h3 class="tit">시담요청현황 상세</h3>
</div> <!--// tit_wrap E -->

<div class="view_wrap typeB">
	<div class="tit_area">
		<h4 class="tit">기본정보</h4>
	</div>
   	<div class="view_area">
        <table class="table" summary="기본정보 입니다.">
            <caption>기본정보</caption>
            <colgroup>
                <col width="15%">
                <col width="35%">
                <col width="15%">
                <col width="35%">
            </colgroup>
            <tbody>
             <tr class="line">
                 <th scope="row" >공고번호</th>
                 <td>P2018-00013-1</td>
                 <th scope="row" >작성일자</th>
                 <td>2018-08-30</td>
             </tr>
             <tr>
                 <th scope="row" >공고부서</th>
                 <td id="DeptTd">솔루션개발팀</td>
                 <th scope="row" >계약담당자</th>
                 <td id="emplyrTd">user1</td>
             </tr>
             <tr>
                 <th scope="row" >요구부서</th>
                 <td id="DeptTd">솔루션개발팀</td>
                 <th scope="row" >요구자</th>
                 <td id="emplyrTd">user1</td>
             </tr>
            </tbody>
        </table>
 	 </div>
  	<div class="tit_area">
  		<h4 class="tit">시담기본정보</h4>
  	</div>
       		<div class="view_area">
	            <table class="table">
	                <caption>기본정보</caption>
	                <colgroup>
	                   <col width="15%">
		                    <col width="35%">
		                    <col width="15%">
		                    <col width="35%">
	                </colgroup>
	                <tr> 
	                    <th>입찰공고명</th>
	                    <td colspan="3">노트북 구매&nbsp;</td>
	                </tr>
	                 <tr>
	                	<th>구매구분</th>
	                	<td>내자구매</td>
	                    <th>계약방법</th>
	                    <td>수의계약						
	                    	<input type="hidden" name="P_CNTRCT_MTH_CD" value="31">&nbsp;
                    	</td>
	                </tr>
	                <tr>
	                	<th>낙찰방법</th>
	                	<td>예가이하 최저가</td>
	                		<th scope="row">예가방식</th>
	                	<td>
	              			  비예가
	                    </td>
	                </tr>
	                <tr>
	                	<th>추정금액</th>
	                	<td>15,000,000</td>
	                </tr>
	            </table>
        	</div>
       		<div class="tit_area"><h4 class="tit">시담요청정보</h4></div>
        	<div class="view_area">
	            <table class="table">
	                <caption>시담요청정보</caption>
	                <colgroup>
		                <col style="width: 15%;">
						<col style="width: *;">
		            </colgroup>
					<tbody>
						<tr class="line">
							<th class="bullet_orange" scope="row">시담요청일시</th>
							<td>
								2018-09-01 10:00
							</td>
						</tr>
						<tr class="line">
							<th class="bullet_orange" scope="row">시담제출기간</th>
							<td>
								2018-09-01 10:00
								~
								2018-09-10 10:00
							</td>
						</tr>
						<tr class="line">
							<th class="bullet_orange" scope="row">개찰일시</th>
							<td>
								2018-09-11 10:00
							</td>
						</tr>
					</tbody>
	            </table>
	            </div><!-- subscript_info E  -->
					
       				<div class="tit_area">
			        	<h4 class="tit" style="clear: both;">시담물품내역</h4>
			       	</div>
					<div class="view_area" style="margin-bottom: 30px;">
			            <table summary="입찰품목정보 수정 입니다.">
			                <caption>입찰품목정보</caption>
			                <colgroup>
			                    <col width="5%">
			                    <col width="10%">
			                    <col width="10%">
			                    <col width="*">
			                    <col width="10%">
			                    <col width="10%">
			                    <col width="10%">
			                    <col width="10%">
			                    <col width="12%">
			                    <col width="12%">
			                </colgroup>
			                <thead>
			                <tr>
			                    <th scope="col" style="text-align: center;">No</th>
			                    <th scope="col" style="text-align: center;">품명번호</th>
			                    <th scope="col" style="text-align: center;">품목분류</th>
			                    <th scope="col" style="text-align: center;">품명</th>
			                    <th scope="col" style="text-align: center;">규격</th>
			                    <th scope="col" style="text-align: center;">수량</th>
			                    <th scope="col" style="text-align: center;">단위</th>
			                    <th scope="col" style="text-align: center;">추정단가</th>
			                    <th scope="col" style="text-align: center;">추정금액</th>
			                </tr>
			                </thead>
			                <tbody>
			                	<tr>
			                		<td style="text-align: center;">1</td>
			                		<td style="text-align: center;">A20180001</td>
			                		<td style="text-align: center;">노트북</td>
			                		<td>삼성노트북</td>
			                		<td style="text-align: center;">Sens 520</td>
			                		<td style="text-align: center;">200</td>
			                		<td style="text-align: center;">EA</td>
			                		<td style="text-align: right;">1,000,000</td>
			                		<td style="text-align: right;">200,000,000</td>
			                	</tr>
			               	</tbody>
			            </table>
					</div>
					 <div class="tit_area">
				        	<h4 class="tit" style="clear: both;">예가정보</h4>
						</div>
						<div class="view_area" style="margin-bottom: 30px;">
				            <table class="tableList" summary="입찰품목정보 수정 입니다.">
				                <caption>입찰품목정보</caption>
				                <colgroup>
				                    <col width="15%">
				                    <col width="35%">
				                    <col width="15%">
				                    <col width="35%">
				                </colgroup>
				                <tr>
					                    <th scope="col">기초금액</th>
				                    	<td>198,500,000</td>
				                    	<th scope="col">에정금액</th>
				                   		<td>198,760,000</td>
				                </tr>
				            </table>
						</div>
					<div class="tit_area">
				        	<h4 class="tit" style="clear: both;">시담업체</h4>
						</div>
						<div class="view_area" style="margin-bottom: 30px;">
				            <table class="tableList" summary="입찰품목정보 수정 입니다.">
				                <caption>입찰품목정보</caption>
				                <colgroup>
				                    <col width="5%">
				                    <col width="*">
				                    <col width="10%">
				                    <col width="10%">
				                    <col width="10%">
				                    <col width="10%">
				                    <col width="10%">
				                    <col width="10%">
				                    <col width="10%">
				                </colgroup>
				                <tr>
				                    	<th scope="col" style="text-align: center;">No</th>
				                    <th scope="col" style="text-align: center;">업체명</th>
				                    <th scope="col" style="text-align: center;">사업자번호</th>
				                    <th scope="col" style="text-align: center;">시담일시</th>
				                    <th scope="col" style="text-align: center;">시담IP</th>
				                    <th scope="col" style="text-align: center;">담당자명</th>
				                    <th scope="col" style="text-align: center;">제안가격</th>
				                    <th scope="col" style="text-align: center;">투찰율</th>
				                    <th scope="col" style="text-align: center;">비고</th>
				                </tr>
				                <tr>
				                		<td style="text-align: center;">1</td>
				                	<td>은우소프트</td>
				                	<td style="text-align: center;">119-86-02801</td>
				                	<td style="text-align: center;">2018-08-30</td>
				                	<td style="text-align: center;">192.168.0.1</td>
				                	<td style="text-align: center;">홍찬일</td>
				                	<td style="text-align: right;">198,000,000</td>
				                	<td style="text-align: center;">100%</td>
				                	<td></td>
				                </tr>
				            </table>
						</div>
						
						<c:if test="${P_GBN eq 'B' }">
							<div class="tit_area">
						<h4 class="tit">유찰정보</h4>
						</div>
   		<div class="view_area">
   		<table>
	        <colgroup>
	            <col width="15%">
	            <col width="35%">
	            <col width="15%">
	            <col width="35%">
	        </colgroup>
		   	<tbody>
		   		<tr>
					<th scope="row" class="bullet_orange">유찰단계</th>
				    <td>
						개찰
				   	</td>
				   	<th>유찰일시</th>
				   	<td>2018-09-01 10:00</td>
				</tr>
				<tr>
					<th>유찰사유</th>
					<td>
						
					</td>
					<th>유찰처리자</th>
					<td>홍찬일</td>
				</tr>
		    </tbody>
   		</table>
   		</div>
   		</c:if>
	<div class="btn_wrap view_btn">
		<button type="button" class="btn btn_m btn_orange" id="registBtn">시담가 등록</button>
		<button type="button" class="btn btn_m btn_del" id="listBtn">목록</button>
	</div> <!--// btn_wrap E -->
					
</div>
     
   	<form id="listFrm" method="POST">
   		<input type="hidden" id="resourceName" name="resourceName" value="${param.resourceName}">
	</form>
	
     
   	<form id="registFrm" method="POST">
   		<input type="hidden" id="resourceName" name="resourceName" value="${param.resourceName}">
	</form>
	
