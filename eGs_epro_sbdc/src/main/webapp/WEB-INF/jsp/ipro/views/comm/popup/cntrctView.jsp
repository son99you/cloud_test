<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 공통 > 계약진행현황 > 계약서보기(팝업)
 *
 * <pre>
 * comm 
 *  |_popup
 *   |_cntrctView.jsp
 * 
 * </pre>
 * @date : 2017. 06. 27
 * @version : 1.0
 * @author : 은우소프트 은잔디
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %>

<link rel="stylesheet" type="text/css" href="${cssPath}/ipro/windowPop.css">
<link rel="stylesheet" type="text/css" href="${cssPath}/ipro/template/pagingPlace.css">

<div id="windowPopup" class="w_800">
	<h3 class="windowTitle">계약서보기</h3>
	<div class="formLayer">
		<br/><br/> &nbsp;
			<table class="contable2">
				<tr>
					<td>
						<table class="contable">
							<tr>
								<td>
									<table id="">
								    	<colgroup>
							                <col width="15%"/>
							                <col width="35%"/>
							                <col width="15%"/>
							                <col width="35%"/>
								        </colgroup>
								        <thead>
							            	<tr class="line">
								            	<th class="" colspan="2" style="text-align: center;">발주처</th>
								            	<th class="" colspan="2" style="text-align: center;">계약상대자</th>
							            	</tr>
							            </thead>
							            
							            <tbody>
							            	<tr>
												<td  colspan="2" style="text-align: center;">은우소프트</td>
												<th>상호</th>
												<td  style="text-align: center;">테스트업체</td>
							            	</tr>
							            	<tr>
												<th>대표자</th>
												<td  style="text-align: left;">정한규</td>
												<th>주소</th>
												<td  style="text-align: center;">서울시 구로구 구로3동 에이스타워 501호 </td>
							            	</tr>
							            	<tr>
												<th>담당자</th>
												<td  style="text-align: left;">은잔디 ( TEL : 02-841-0721 )</td>
												<th>대표자</th>
												<td  style="text-align: center;">홍길동</td>
							            	</tr>
							            	<tr>
												<th></th>
												<td  style="text-align: left;"></td>
												<th>사업자등록번호</th>
												<td  style="text-align: center;">111-11-11119  </td>
							            	</tr>
							            	<tr>
												<th></th>
												<td  style="text-align: left;"></td>
												<th class="" >전화번호</th>
												<td  style="text-align: center;">02-000-0000</td>
							            	</tr>
							            	<tr>
												<th></th>
												<td  style="text-align: left;"></td>
												<th>팩스번호</th>
												<td  style="text-align: center;">02-111-1111</td>
							            	</tr>
							            </tbody>
								    </table>
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
			
			<br><br>
			
			<table class="contable2">
				<tr>
					<td>
						<table class="contable">
							<tr>
								<td>
									<table>
										<colgroup>
											<col width="20%" align="left">
											<col width="30%" align="left">
											<col width="20%" align="left">
											<col width="30%" align="left">
										</colgroup>
										<tr height="29px">
											<td>
												
												계약번호
											</td>
											<td  colspan="3">
												PC20170100
											</td>
										</tr>
										
										<tr height="29px">
											<td>
												
												계약명
											</td>
											<td  colspan="3">
												(강원지사)미래코 폐광지역 꿈나무 응원
											</td>
										</tr>
										
										<tr height="29px">
											<td>
												
												계약일자
											</td>
											<td  colspan="3">
												2017-06-13
											</td>
										</tr>
										
										<tr height="29px">
											<td>
												
												금차계약금액
											</td>
											<td  colspan="3">
												2,400,000
											</td>
										</tr>
										
										<tr height="29px">
											<td>
												
												계약보증금액 
											</td>
											<td  colspan="3">
												360,000 
											</td>
										</tr>
										
										<tr height="29px">
											<td>
												
												계약보증방법
											</td>
											<td  colspan="3"> 
												해당없음
											</td>
										</tr>
										
										<tr height="29px">
											<td>
												
												하자보수보증금율 
											</td>
											0 %</td>
											<td>
												
												하자담보책임기간 
											</td>
											계약일로부터 1년</td>
										</tr>
										<tr height="29px">
											<td>
												
												착수일자  
											</td>
											2017-06-14 </td>
											<td>
												
												금차완수일자  
											</td>
											2017-12-30 </td>
										</tr>
										
										<tr>
											<td colspan="4">
												위 물품계약을 체결함에 있어 계약자와 연대보증인은 각각 다음의 사항을 확약하며 계약의 증거로 이 계약서를 작성한다.
												
												<br><br>
													1. 계약자는 입찰시 공시한 물품입찰유의서, 물품입찰특별유의서, 청렴계약입찰특별유의서, 설계서및 현장설명 사항과 물품계약일반조건, 물품계약특수조건, 청렴계약특수조건, 공동수급협정서, 산출내역서(하도급 사항 포함) 및 붙임 전자계약 확약사항이 이 계약의 일부분임을 확인하며 신의에 따라 성실하게 계약상의 의무를 이행한다.
											</td>
										</tr>
									</table>
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
			
			<br><br>
			
			<table class="contable2">
				<tr>
					<td>
						<table class="contable">
							<tr>
								<td>
									<table>
										<colgroup>
											<col width="5%" align="center">
											<col width="15%" align="center">
											<col width="15%" align="center">
											<col width="14%" align="center">
											<col width="14%" align="center">
											<col width="14%" align="center">
											<col width="" align="center">
										</colgroup>
										<thead>
											<tr height="29px">
												<th class="" rowspan="2">No.</th>
												<th>물품분품번호</th>
												<th>물품식별번호</th>
												<th class="" colspan="2">품명</th>
												<th>규격</th>
												<th>단위</th>
											</tr>
											<tr height="29px">
												<th class="" colspan="2">인도조건</th>
												<th>수량</th>
												<th>단가</th>
												<th>계약금액</th>
												<th>납품기한</th>
											</tr>
										</thead>
										<tbody>
											<tr height="29px">
												<td  rowspan="2" style="text-align: center;">1</td>
												<td  style="text-align: center;">81111599</td>
												<td  style="text-align: center;">20604007</td>
												<td  colspan="2" style="text-align: center;">적외선탐지기</td>
												<td  style="text-align: center;">적외선탐지기</td>
												<td  style="text-align: center;">CM</td>
											</tr>
											<tr height="29px">
												<td  colspan="2" style="text-align: center;"> 공판장,특약점,대리점도  </td>
												<td  style="text-align: right;">10</td>
												<td  style="text-align: right;">200,000 </td>
												<td  style="text-align: right;">2,000,000 </td>
												<td  style="text-align: center;">납품일수 365 </td>
											</tr>
											<tr height="29px">
												<td  rowspan="2" style="text-align: center;">2</td>
												<td  style="text-align: center;">81111598</td>
												<td  style="text-align: center;">20604006</td>
												<td  colspan="2" style="text-align: center;">골재공급기</td>
												<td  style="text-align: center;">골재공급기</td>
												<td  style="text-align: center;">M</td>
											</tr>
											<tr height="29px">
												<td  colspan="2" style="text-align: center;"> 납품장소 창고입고도  </td>
												<td  style="text-align: right;">20</td>
												<td  style="text-align: right;">20,000 </td>
												<td  style="text-align: right;">400,000 </td>
												<td  style="text-align: center;">납품일수 30 </td>
											</tr>
										</tbody>		
									</table>
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
			
           
	    <div class="T_btnLayer fr" style="margin-top: 20px;">
			<button type="button" class="blueBtn L" id="">출력</button>
			<button type="button" class="blueBtn L" id="closeBtn" onclick="window.close();">닫기</button>
	    </div>
	</div> 
</div>
