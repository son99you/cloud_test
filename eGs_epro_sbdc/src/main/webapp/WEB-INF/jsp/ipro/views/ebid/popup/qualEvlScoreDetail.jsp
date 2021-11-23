<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 입찰현황 > 관심입찰업체목록
 *
 * <pre>
 * ebid 
 *    |_ popup
              |_ intrstBidEntrpsList.jsp
 * 
 * </pre>
 * @date : 2017. 06. 13
 * @version : 1.0
 * @author : 은우소프트 전상훈
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %> 
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %> 

<link rel="stylesheet" type="text/css" href="${cssPath}/ipro/template/pagingPlace.css">
<link rel="stylesheet" type="text/css" href="${cssPath}/ipro/windowPop.css">

<script type="text/javascript" src="${jsPath}/ipro/views/ebid/popup/qualEvlScoreDetail.js"></script> 
 
<div id="windowPopup" class="w_980">
	<div class="tableLayer">
		<h3 class="windowTitle" style="">기술평가결과 상세 ( 평가위원 : 고길동 님 )</h3>
  	</div>
	<div style="width: 100%;">
		<table style="width: 100%">
			<tr>
				<td background='${imagePath}/ipro/tab/tab_bg.gif '>
					<table>
						<tr style="width: 100%;">
							<td style="width: 10px;"><img src="${imagePath}/ipro/tab/tab_on_1.gif " alt=""></td>
							<td style="width: 60px; text-align: center; background-repeat: repeat-x;" class="tab_on" id="1"><a href='javascript:tabEvent(1);' target='_self'>평가점수</a></td>
							<td style="width: 10px;"><img src="${imagePath}/ipro/tab/tab_on_2.gif " alt=""></td>
							<td style="width: 10px;"><img src="${imagePath}/ipro/tab/tab_off_1.gif " alt=""></td>
							<td style="width: 70px; text-align: center; background-repeat: repeat-x;" class="tab_off" id="2"><a href='javascript:tabEvent(2);' target='_self'>평가의견서</a></td>
							<td style="width: 10px;"><img src="${imagePath}/ipro/tab/tab_off_2.gif " alt=""></td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</div>
  	<br>	
	<div class="tableLayer">
		<div class="block1 tabBlock ">
			<div class="subscrip_info">
				<table class="table">
			    	<colgroup>
			        	<col width="15%" />
			            <col width="*" />
			            <col width="10%" />
			            <col width="20%" />
			            <col width="20%" />
			        </colgroup>
			        <tr>
			        	<th class="txtc">평가구분</th>
			        	<th class="txtc">평가항목구분</th>
			        	<th class="txtc">배점</th>
			        	<th class="txtc">주식회사 은우소프트</th>
			        	<th class="txtc">LIZ시스템</th>
					</tr>
					<tr>
						<td>제안사 소개</td>
						<td >일반현황 및 연혁 등</td>
						<td class="txtc">5</td>
						<td class="txtc">5</td>
						<td class="txtc">4</td>
					</tr>
					<tr>
						<td rowspan="4">전략 및 방법론</td>
						<td >사업이해도</td>
						<td class="txtc">4</td>
						<td class="txtc">3</td>
						<td class="txtc">2</td>
					</tr>
					<tr>
						<td >추진전략</td>
						<td class="txtc">4</td>
						<td class="txtc">4</td>
						<td class="txtc">3</td>
					</tr>
					<tr>
						<td >성과관리</td>
						<td class="txtc">4</td>
						<td class="txtc">4</td>
						<td class="txtc">4</td>
					</tr>
					<tr>
						<td >컨설팅 방법론</td>
						<td class="txtc">3</td>
						<td class="txtc">3</td>
						<td class="txtc">2</td>
					</tr>
					<tr>
						<td rowspan="2">기술 및 기능</td>
						<td >컨설팅 요구사항</td>
						<td class="txtc">10</td>
						<td class="txtc">9</td>
						<td class="txtc">9</td>
					</tr>
					<tr>
						<td >제약사항</td>
						<td class="txtc">10</td>
						<td class="txtc">10</td>
						<td class="txtc">8</td>
					</tr>
					<tr>
						<td rowspan="6">프로젝트 관리</td>
						<td >관리방법론</td>
						<td class="txtc">5</td>
						<td class="txtc">5</td>
						<td class="txtc">5</td>
					</tr>
					<tr>
						<td >개발장비</td>
						<td class="txtc">5</td>
						<td class="txtc">4</td>
						<td class="txtc">4</td>
					</tr>
					<tr>
						<td >일정관리</td>
						<td class="txtc">4</td>
						<td class="txtc">4</td>
						<td class="txtc">4</td>
					</tr>
					<tr>
						<td >위험관리</td>
						<td class="txtc">4</td>
						<td class="txtc">4</td>
						<td class="txtc">3</td>
					</tr>
					<tr>
						<td >사후관리</td>
						<td class="txtc">4</td>
						<td class="txtc">4</td>
						<td class="txtc">4</td>
					</tr>
					<tr>
						<td >모니터링 및 보고</td>
						<td class="txtc">3</td>
						<td class="txtc">3</td>
						<td class="txtc">3</td>
					</tr>
					<tr>
						<td rowspan="2">특수제안</td>
						<td >제안요청내용 개선사항</td>
						<td class="txtc">3</td>
						<td class="txtc">3</td>
						<td class="txtc">3</td>
					</tr>
					<tr>
						<td >추가 투입계획</td>
						<td class="txtc">2</td>
						<td class="txtc">2</td>
						<td class="txtc">2</td>
					</tr>
					<tr>
						<td>테스트점수</td>
						<td >테스트점수</td>
						<td class="txtc">10</td>
						<td class="txtc">9</td>
						<td class="txtc">8</td>
					</tr>
					<tr>
						<th colspan="2" class="txtc">총점</th>
						<th class="txtc">80</th>
						<td class="txtc">76</td>
						<td class="txtc">65</td>
					</tr>
				</table>
			</div>
		</div>
		
		<div style="display: none;" class="block2 tabBlock ">
			<div class="subscrip_info">
				<table class="table">
			    	<colgroup>
			    		<col width="15%" />
			            <col width="20%" />
			            <col width="*" />
			        </colgroup>
			        <tr>
			        	<th class="txtc">평가항목</th>
			        	<th class="txtc">업체명</th>
			        	<th class="txtc">평가의견</th>
					</tr>
					<tr>
			        	<tdrowspan="2">제안사 소개</td>
			        	<td>주식회사 은우소프트</td>
			        	<td>소개자료와 발표가 깔끔함</td>
					</tr>
					<tr>
			        	<td>LIZ시스템</td>
			        	<td>자료가 부실하고 설명이 제대로 되지 않음</td>
					</tr>
					<tr>
			        	<tdrowspan="2">전략 및 방법론</td>
			        	<td>주식회사 은우소프트</td>
			        	<td>전략과 방법이 뛰어남</td>
					</tr>
					<tr>
			        	<td>LIZ시스템</td>
			        	<td>전략과 방법이 평범함</td>
					</tr>
					<tr>
			        	<tdrowspan="2">기술 및 기능</td>
			        	<td>주식회사 은우소프트</td>
			        	<td>최신 기술을 이용한 기능구현</td>
					</tr>
					<tr>
			        	<td>LIZ시스템</td>
			        	<td>기능구현</td>
					</tr>
					<tr>
			        	<tdrowspan="2">프로젝트 관리</td>
			        	<td>주식회사 은우소프트</td>
			        	<td>철저한 프로젝트 관리</td>
					</tr>
					<tr>
			        	<td>LIZ시스템</td>
			        	<td>프리한 프로젝트 관리</td>
					</tr>
					<tr>
			        	<tdrowspan="2">특수제안</td>
			        	<td>주식회사 은우소프트</td>
			        	<td>제안이 흥미로움</td>
					</tr>
					<tr>
			        	<td>LIZ시스템</td>
			        	<td>제안 없음</td>
					</tr>
					<tr>
			        	<tdrowspan="2">테스트점수</td>
			        	<td>주식회사 은우소프트</td>
			        	<td>테스트 훌륭함</td>
					</tr>
					<tr>
			        	<td>LIZ시스템</td>
			        	<td>테스트</td>
					</tr>
				</table>
			</div>
		</div>
		<div class="T_btnLayer fr top10">
	    	<button type="button" class="blueBtn L pointer" id="closeBtn" >닫기</button>
	    </div>
	</div>
</div> <!--// content E-->
