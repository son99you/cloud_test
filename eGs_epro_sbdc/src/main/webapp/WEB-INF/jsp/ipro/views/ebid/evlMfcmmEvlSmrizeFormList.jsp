<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 기술평가현황 > 평가계획수립 목록 
 *
 * <pre>
 * ebid 
 *    |_ tchqvlnList.jsp
 * 
 * </pre>
 * @date : 2017. 06. 22
 * @version : 1.0
 * @author : 은우소프트 전상훈
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %> 
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %> 

<link rel="stylesheet" type="text/css" href="${cssPath}/ipro/bodyBasic.css">
<link rel="stylesheet" type="text/css" href="${cssPath}/ipro/template/buttonStyle.css">
<link rel="stylesheet" type="text/css" href="${cssPath}/ipro/template/pagingPlace.css">
<link rel="stylesheet" type="text/css" href="${cssPath}/ipro/template/calendar.css">

<script type="text/javascript" src="${jsPath}/ipro/views/ebid/entrpsAcctoSmrizeFormList.js"></script> 
 
<div class="content">
	<h3>평가위원 총괄표 목록</h3>
	<div class="list">
		<div id="searchWrap">
			<form name="biSearchForm" method="post">
			<table class="contable2">
				<tr>
					<td>
						<table class="contable">
							<tr>
								<td>
									<table>
										<colgroup>
											<col width="130px" align="left">
											<col width="280px" align="left">
											<col width="130px" align="left">
											<col width="280px" align="left">
										</colgroup>
										<tr height="24px">
											<td>
												
												입찰명
											</td>
											<td>
												<input type="text"  >
											</td>
											<td>
												
												위원구분
											</td>
											<td>
												<select>
													<option>전체</option>
													<option>비상임위원</option>
													<option>상임위원</option>
												</select>
											</td>
										</tr>
										<tr>
											<td>
												
												기술평가년도
											</td>
											<td>
												<select>
													<option>전체</option>
													<option>2017</option>
													<option>2016</option>
													<option>2015</option>
													<option>2014</option>
													<option>2013</option>
													<option>2012</option>
													<option>2011</option>
													<option>2010</option>
													<option>2009</option>
													<option>2008</option>
												</select>
											</td>
											<td>
												
												종합등급
											</td>
											<td>
												<select>
													<option>전체</option>
													<option>매우우수</option>
													<option>우수</option>
													<option>보통</option>
													<option>미흡</option>
													<option>매우미흡</option>
												</select>
											</td>
										</tr>
										<tr>
											<td>
												
												성명
											</td>
											<td>
												<input type="text"  size="20" name="biNo" >
											</td>
											<td>
												
												전문분야
											</td>
											<td>
												<input type="text"  size="20" name="biNo" >
											</td>
										</tr>
										<tr>
											<td>
												
												소속
											</td>
											<td  colspan="3">
												<input type="text"  size="20" name="biNo" >
											</td>
										</tr>
										<tr>
											<td class=" searchBtnTd" colspan="4">
												<button type="button" class="grayBtn ico pointer" id="searchBtn">
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
		</div>
		<br>
		
		<div class="list_wrap mt30" id="contentWrap" style="width: auto; height: auto; overflow-x: scroll; white-space:nowrap;">
			<div class="list_top">
				<p class="total">총 <span></span>건</p>		
				<div class="btn_right">
					<button type="button" class="btn btn_02 btn_down">엑셀 다운로드</button>
					<!-- <button type="button" class="btn btn_02 btn_down">엑셀 다운로드</button> -->
				</div>
			</div> <!--// list_top E -->
			<div class="list_conts">		
		
				<table style="table-layout:fixed;">
					<caption>평가위원 총괄표</caption>
	         		<colgroup>
	                   <col width="95px">
	                   <col width="80px">
	                   <col width="300px">
	                   <col width="75px">
	                   <col width="80px">
	                   <col width="90px">
	                   <col width="70px">
	                   <col width="80px">
	                   <col width="110px">
	                   <col width="130px">
	                   <col width="130px">
	                   <col width="130px">
	                   <col width="130px">
	                   <col width="90px">
	                   <col width="85px">
	                   <col width="220px">
	               </colgroup>
			    	<tr>
			            <th class="txtc" rowspan="2">기술평가일자</th>
			            <th class="txtc" rowspan="2"> 담당자 </th>
			            <th class="txtc" rowspan="2">입찰명</th>
			            <th class="txtc" rowspan="2">위원구분</th>
			            <th class="txtc" rowspan="2">평가위원</th>
			            <th class="txtc" rowspan="2">누적참여횟수</th>
			            <th class="txtc" rowspan="2">신규여부</th>
			            <th class="txtc" rowspan="2">전문분야</th>
			            <th class="txtc" rowspan="2">소속 및 지위</th>
			            <th class="txtc" colspan="5">평가위원평가</th>
			            <th class="txtc" rowspan="2">종합등급</th>
			            <th class="last txtc" rowspan="2">기타의견</th>
			        </tr>
			        <tr>
			            <th class="txtc">평가이해 및 준비도</th>
			            <th class="txtc">질의 내용의 우수성</th>
			            <th class="txtc">의견서 작성 충실도</th>
			            <th class="txtc">평가태도 및 협조도</th>
			            <th class="txtc">질의 횟수</th>
			        </tr>
			        <tr>
			        	<td class="txtc" >2017-06-28</td>
			        	<td class="txtc" >사용자1</td>
			        	<td  >2017년 하반기 NCS기반 신입직원 채용대행 용역</td>
			        	<td class="txtc" >상임위원</td>
			        	<td class="txtc" >고길동</td>
			        	<td class="txtc" >4</td>
			        	<td class="txtc" ></td>
			        	<td class="txtc" >교육</td>
			        	<td class="txtc" >구매개발팀</td>
			        	<td class="txtc" >우수</td>
			        	<td class="txtc" >적합</td>
			        	<td class="txtc" >매우우수</td>
			        	<td class="txtc" >우수</td>
			        	<td class="txtc" >4회</td>
			        	<td class="txtc" >우수</td>
			        	<td  >기타의견 입니다.</td>
			        </tr>
			        <tr>
			        	<td class="txtc " >2017-06-28</td>
			        	<td class="txtc " >사용자1</td>
			        	<td  >2017년 하반기 NCS기반 신입직원 채용대행 용역</td>
			        	<td class="txtc " >비상임위원</td>
			        	<td class="txtc " >이상수</td>
			        	<td class="txtc " >1</td>
			        	<td class="txtc " ></td>
			        	<td class="txtc " >심리학</td>
			        	<td class="txtc " >울산대학교</td>
			        	<td class="txtc " >적합</td>
			        	<td class="txtc " >적합</td>
			        	<td class="txtc " >매우우수</td>
			        	<td class="txtc " >적합</td>
			        	<td class="txtc " >4회</td>
			        	<td class="txtc " >적합</td>
			        	<td  >의견 입니다.</td>
			        </tr>
			        <tr>
			        	<td class="txtc" >2017-06-24</td>
			        	<td class="txtc" >사용자2</td>
			        	<td  >팔레스타인 창취업 지원프로그램 국내 PC용역</td>
			        	<td class="txtc" >상임위원</td>
			        	<td class="txtc" >고길동</td>
			        	<td class="txtc" >3</td>
			        	<td class="txtc" ></td>
			        	<td class="txtc" >교육</td>
			        	<td class="txtc" >구매개발팀</td>
			        	<td class="txtc" >우수</td>
			        	<td class="txtc" >우수</td>
			        	<td class="txtc" >우수</td>
			        	<td class="txtc" >우수</td>
			        	<td class="txtc" >5회</td>
			        	<td class="txtc" >우수</td>
			        	<td  >기타의견 입니다.</td>
			        </tr>
			        <tr>
			        	<td class="txtc " >2017-06-24</td>
			        	<td class="txtc " >사용자2</td>
			        	<td  >팔레스타인 창취업 지원프로그램 국내 PC용역</td>
			        	<td class="txtc " >비상임위원</td>
			        	<td class="txtc " >이상수</td>
			        	<td class="txtc " >0</td>
			        	<td class="txtc " >신규</td>
			        	<td class="txtc " >심리학</td>
			        	<td class="txtc " >울산대학교</td>
			        	<td class="txtc " >미흡</td>
			        	<td class="txtc " >적합</td>
			        	<td class="txtc " >매우우수</td>
			        	<td class="txtc " >우수</td>
			        	<td class="txtc " >2회</td>
			        	<td class="txtc " >적합</td>
			        	<td  >의견 입니다.</td>
			        </tr>
			    </table>
			</div>
	    </div>
	</div>
</div> <!--// content E-->

<%-- DETAIL FORM --%>
<form id="registFrm" method="POST" > 
	<input type="hidden" name="P_PBLANC_NO" value="" >
	<input type="hidden" name="P_PBLANC_ODR" value="" >
	<input type="hidden" name="P_TCHQVLN_PLAN_PRST_CD" value="" >
	<input type="hidden" name="resourceName" value="${ param.resourceName }" >
</form>