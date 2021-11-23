<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">--%>
<%--
 * 공통 > 구매요구접수 목록(팝업)
 *
 * <pre>
 * comm 
 *  |_popup
 *   |_purdmdList.jsp
 * 
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %>

<link rel="stylesheet" type="text/css" href="${cssPath}/ipro/windowPop.css">
<link rel="stylesheet" type="text/css" href="${cssPath}/ipro/template/pagingPlace.css">
<script type="text/javascript" src="${jsPath}/ipro/views/comm/popup/purdmdList.js"></script>

<div id="windowPopup" style="width: 850px;">
	<h3 class="windowTitle">구매요구접수 목록</h3>
	<div class="formLayer">
		<form id="searchFrm" class="search_form" method="POST" >
			
		<br/><br/> &nbsp;
			<table class="contable2">
				<tr>
					<td>
						<table class="contable">
							<tr>
								<td>
									<table>
										<colgroup>
											<col width="15%" align="left">
											<col width="35%" align="left">
											<col width="15%" align="left">
											<col width="35%" align="left">
										</colgroup>
										<tr height="29px">
											<td>
												
												계약명
											</td>
											
												<input type="text" style="width: 80%;" maxlength="100">
											</td>
										</tr>
										<tr>
											<td class=" searchBtnTd" colspan="4">
												<button type="button" class="grayBtn ico" id="searchBtn">
								                    <img src="${imagePath}/ipro/icon/ico_search.png" alt="조회 버튼"> 조회
								                </button>
											</td>
										</tr>
									</table>
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</form>
           
		<!-- Data Total Count -->
		<br />
	    <div class="tableComment">
	        <p class="list_count">총 <span>12</span>건</p>
	    </div>
	       
	    <!-- Data List -->
		<div class="list">
			<table>
	            <caption>사업 목록</caption>
	            <colgroup>
	                <%-- <col width="5%"/> --%>
                   	<%-- <col width="10%"/> --%>
                   	<col width="8%"/>
                   	<col width="*"/>
                   	<col width="12%"/>
                   	<col width="8%"/>
                   	<col width="10%"/>
                   	<col width="8%"/>
                   	<col width="10%"/>
	            </colgroup>			
				<thead>
	                <tr>
	                 	<!-- <th>NO</th> -->
			            <!-- <th>진행상태</th> -->
			            <th>계약구분</th>
			            <th>계약명</th>
			            <th>추정금액</th>
			            <th>계약방법</th>
			            <th>요구부서</th>
			            <th>요구자</th>
			            <th class="last">요구일자</th>
	                </tr>
	            </thead>
				<tbody>
				<tr style="cursor: pointer;" onclick="setPurdmdListInfo('EBS 프로그램 연장구매','EBS 프로그램 연장구매','31','0','222,005,000','손연우','syw@eunwoosoft.com','eunwoo@eunwoosoft.com');">
		        	<!-- <td class="txtc">12</td> -->
		        	<!-- <td class="txtc">구매접수대기</td> -->
		            <td class="txtc">물품</td>
		            <td >EBS 프로그램 연장구매</td>
		            <td class="txtr">225,005,000</td>
		            <td class="txtc">수의(총액)</td>
		            <td class="txtc">계약관리실</td>
		            <td class="txtc">손연우</td>
		            <td class="txtc">2017-06-13</td>
		        </tr>
		        <tr style="cursor: pointer;" onclick="setPurdmdListInfo('RFID물품관리 유지보수','RFID물품관리 유지보수','31','1','7,300,000','홍찬일','hci@eunwoosoft.com','eunwoo2@eunwoosoft.com');">
		        	<!-- <td class=" txtc">11</td> -->
		        	<!-- <td class=" txtc">구매접수대기</td> -->
		            <td class=" txtc">용역</td>
		            <td >RFID물품관리 유지보수</td>
		            <td class=" txtr">7,300,000</td>
		            <td class=" txtc">수의(총액)</td>
		            <td class=" txtc">계약관리실</td>
		            <td class=" txtc">홍찬일</td>
		            <td class=" txtc">2017-06-11</td>
		        </tr>
		        <tr style="cursor: pointer;" onclick="setPurdmdListInfo('사옥 파티션 공사','사옥 파티션 공사','31','2','1,000,000','김나영','kny@eunwoosoft.com','eunwoo3@eunwoosoft.com');">
		        	<!-- <td class="txtc">10</td> -->
		        	<!-- <td class="txtc">구매접수대기</td> -->
		            <td class="txtc">공사</td>
		            <td >사옥 파티션 공사</td>
		            <td class="txtr">1,000,000</td>
		            <td class="txtc">수의(총액)</td>
		            <td class="txtc">비서실</td>
		            <td class="txtc">김나영</td>
		            <td class="txtc">2017-05-22</td>
		        </tr>
		     <tr style="cursor: pointer;" onclick="setPurdmdListInfo('2017아젠다 개발 TFT 사업계획 컨설팅','2017아젠다 개발 TFT 사업계획 컨설팅','11','1','7,600,000','김나영','kny@eunwoosoft.com','eunwoo4@eunwoosoft.com');">
		        	<!-- <td class=" txtc">9</td> -->
		    		<!-- <td class=" txtc">구매접수대기</td> -->
		            <td class=" txtc">용역</td>
		            <td >2017아젠다 개발 TFT 사업계획 컨설팅</td>
		            <td class=" txtr">7,600,000</td>
		            <td class=" txtc">일반(총액)</td>
		            <td class=" txtc">IT지원팀</td>
		            <td class=" txtc">강호동</td>
		            <td class=" txtc">2017-05-15</td>
		        </tr>
		       <tr style="cursor: pointer;" onclick="setPurdmdListInfo('도시락 등 신학기 용품','도시락 등 신학기 용품','11','0','2,680,000','송진호','sjh@eunwoosoft.com','eunwoo5@eunwoosoft.com');">
		        	<!-- <td class="txtc">8</td> -->
		        	<!-- <td class="txtc">구매접수대기</td> -->
		            <td class="txtc">물품</td>
		            <td >도시락 등 신학기 용품</td>
		            <td class="txtr">2,680,000</td>
		            <td class="txtc">일반(총액)</td>
		            <td class="txtc">홍보실</td>
		        	<td class="txtc">송진호</td>
		            <td class="txtc">2017-04-27</td>
		        </tr>
		      	 <tr style="cursor: pointer;" onclick="setPurdmdListInfo('기념품 구매','기념품 구매','31','0','21,560,000','송진호','sjh@eunwoosoft.com','eunwoo5@eunwoosoft.com');">
		        	<!-- <td class=" txtc">7</td> -->
		        	<!-- <td class=" txtc">구매접수대기</td> -->
		        	<td class=" txtc">물품</td>
		            <td >기념품 구매</td>
		            <td class=" txtr">21,560,000</td>
		            <td class=" txtc">수의(총액)</td>
		            <td class=" txtc">홍보실</td>
		            <td class=" txtc">송진호</td>
		            <td class=" txtc">2017-04-27</td>
		        </tr>
		        <tr style="cursor: pointer;" onclick="setPurdmdListInfo('기타인쇄물','기타인쇄물','23','0','6,952,000','정한솔','jhs@eunwoosoft.com','eunwoo6@eunwoosoft.com');">
		        	<!-- <td class="txtc">6</td> -->
		        	<!-- <td class="txtc">구매접수대기</td> -->
		        	<td class="txtc">물품</td>
		            <td >기타인쇄물</td>
		            <td class="txtr">6,952,000</td>
		            <td class="txtc">지명(단가)</td>
		            <td class="txtc">총무실</td>
		            <td class="txtc">정한솔</td>
		            <td class="txtc">2017-04-24</td>
		        </tr>
		      	  <tr style="cursor: pointer;" onclick="setPurdmdListInfo('계약심사사례집','계약심사사례집','31','0','5,200,000','손연우','syw@eunwoosoft.com','eunwoo7@eunwoosoft.com');">
		        	<!-- <td class=" txtc">5</td> -->
		        	<!-- <td class=" txtc">구매접수대기</td> -->
		        	<td class=" txtc">물품</td>
		            <td >계약심사사례집</td>
		            <td class=" txtr">5,200,000</td>
		            <td class=" txtc">수의(총액)</td>
		            <td class=" txtc">계약관리실</td>
		            <td class=" txtc">손연우</td>
		            <td class=" txtc">2017-04-21</td>
		        </tr>
		        <tr style="cursor: pointer;" onclick="setPurdmdListInfo('업무용 모니터','업무용 모니터','31','0','13,030,000','강한울','khl@eunwoosoft.com','eunwoo7@eunwoosoft.com');">
		        	<!-- <td class="txtc">4</td> -->
		        	<!-- <td class="txtc">구매접수대기</td> -->
		        	<td class="txtc">물품</td>
		            <td >업무용 모니터</td>
		            <td class="txtr">13,030,000</td>
		            <td class="txtc">수의(총액)</td>
		            <td class="txtc">전략기획실</td>
		            <td class="txtc">강한울</td>
		            <td class="txtc">2017-03-31</td>
		        </tr>
		        <tr style="cursor: pointer;" onclick="setPurdmdListInfo('업무용 데스크탑','업무용 데스크탑','31','0','63,790,000','강한울','khl@eunwoosoft.com','eunwoo8@eunwoosoft.com');">
		        	<!-- <td class=" txtc">3</td> -->
		        	<!-- <td class=" txtc">구매접수대기</td> -->
		        	<td class=" txtc">물품</td>
		            <td >업무용 데스크탑</td>
		            <td class=" txtr">63,790,000</td>
		            <td class=" txtc">수의(총액)</td> 
		            <td class=" txtc">전략기획실</td>
		            <td class=" txtc">강한울</td>
		            <td class=" txtc">2017-03-26</td>
		        </tr>
				</tbody> 
			</table>
			
			<!-- 페이징 -->
			<div class="paging_place" style="width: 850px;">
				<div class="paging_wrap"><a href="#" class="pprev" title="맨앞으로" style="display: none;"><span style="display: none;">맨앞으로</span></a><a href="#" class="prev" title="앞으로"><span style="display: none;">앞으로</span></a>
					<span><a href="#" class="active" title="1">1</a><a href="#" class="active" title="2">2</a></span>
					<a href="#" class="next" title="뒤로"><span style="display: none;">뒤로</span></a><a href="#" class="nnext" title="맨뒤로" style="display: none;"><span style="display: none;">맨뒤로</span></a>
				</div>
			</div>
			<!-- 페이징 끝 -->
		</div> 
		<br>
	    <div class="T_btnLayer fr">
			<button type="button" class="blueBtn L" id="closeBtn" onclick="window.close();">닫기</button>
	    </div>
	</div> 
</div>
