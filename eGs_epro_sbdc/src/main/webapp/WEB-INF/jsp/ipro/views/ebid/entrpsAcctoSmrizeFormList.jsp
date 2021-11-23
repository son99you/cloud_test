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
	<h3>업체별 총괄표 목록</h3>
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
												<input type="text" >
											</td>
											<td>
												
												기술평가년도
											</td>
											<td>
												<select>
													<option>2017</option>
													<option>2016</option>
													<option>2015</option>
													<option>2014</option>
													<option>2013</option>
													<option>2012</option>
												</select>
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
		
		<div class="list_wrap mt30" id="contentWrap">
			<div class="list_top">
				<p class="total">총 <span></span>건</p>
				<div class="btn_right">
					<button type="button" class="btn btn_02 btn_down">엑셀 다운로드</button>
				</div>
			</div> <!--// list_top E -->
			<div class="list_conts">
					
				<table>
					<caption>평가위원 총괄표</caption>
	           		<colgroup>
	                   	<col width="7%"/>
	                   	<col width="*"/>
	                   	<col width="8%"/>
	                   	<col width="12%"/>
	                   	<col width="5%"/>
	                   	<col width="5%"/>
	                   	<col width="6%"/>
	                   	<col width="6%"/>
	                   	<col width="6%"/>
	                   	<col width="6%"/>
	                   	<col width="6%"/>
	                   	<col width="6%"/>
	                   	<col width="6%"/>
	            	</colgroup>
			    	<tr>
			            <th class="txtc" rowspan="2">기술<br>평가일자</th>
			            <th class="txtc" rowspan="2">입찰명</th>
			            <th class="txtc" rowspan="2">평가<br>방법</th>
			            <th class="txtc" rowspan="2">평가업체명</th>
			            <th class="txtc" colspan="3">기술평가점수</th>
			            <th class="txtc" colspan="3">기술가격비율환산</th>
			            <th class="txtc" rowspan="2">우선<br>협상</th>
			            <th class="last txtc" rowspan="2">낙찰<br>여부</th>
			        </tr>
			        <tr>
			            <th class="txtc">정량</th>
			            <th class="txtc">정성</th>
			            <th class="txtc">합산</th>
			            <th class="txtc">기술</th>
			            <th class="txtc">가격</th>
			            <th class="last txtc">종합</th>
			        </tr>
			        <tr>
			            <td class="txtc" rowspan="2">2017-06-28</td>
			            <td  rowspan="2">2017년 하반기 NCS기반 신입직원 채용대행 용역</td>
			            <td class="txtc" rowspan="2">소집</td>
			            <td class="txtc">주식회사은우소프트</td>
			            <td class="txtc">16</td>
			            <td class="txtc">75</td>
			            <td class="txtc">91</td>
			            <td class="txtc">76</td>
			            <td class="txtc">18</td>
			            <td class="txtc">94</td>
			            <td class="txtc">우선</td>
			            <td class="txtc">낙찰</td>
			        </tr>
			        <tr>
			            <td class="txtc">LIZ시스템</td>
			            <td class="txtc">10</td>
			            <td class="txtc">60</td>
			            <td class="txtc">70</td>
			            <td class="txtc">70</td>
			            <td class="txtc">10</td>
			            <td class="txtc">80</td>
			            <td class="txtc"></td>
			            <td class="txtc"></td>
			        </tr>
			        <tr>
			            <td class=" txtc" rowspan="2">2017-06-25</td>
			            <td  rowspan="2">서울센터 에어컨 배관 이설공사</td>
			            <td class=" txtc" rowspan="2">소집</td>
			            <td class=" txtc">우람건축</td>
			            <td class=" txtc">15</td>
			            <td class=" txtc">70</td>
			            <td class=" txtc">85</td>
			            <td class=" txtc">70</td>
			            <td class=" txtc">15</td>
			            <td class=" txtc">85</td>
			            <td class=" txtc"></td>
			            <td class=" txtc"></td>
			        </tr>
			        <tr>
			            <td class=" txtc">튼튼건축</td>
			            <td class=" txtc">11</td>
			            <td class=" txtc">77</td>
			            <td class=" txtc">88</td>
			            <td class=" txtc">75</td>
			            <td class=" txtc">16</td>
			            <td class=" txtc">91</td>
			            <td class=" txtc">우선</td>
			            <td class=" txtc">낙찰</td>
			        </tr>
			        <tr>
			            <td class="txtc" rowspan="2">2017-06-20</td>
			            <td  rowspan="2">2017년 통합시스템 구축</td>
			            <td class="txtc" rowspan="2">소집</td>
			            <td class="txtc">주식회사 은우소프트</td>
			            <td class="txtc">20</td>
			            <td class="txtc">76</td>
			            <td class="txtc">96</td>
			            <td class="txtc">79</td>
			            <td class="txtc">16</td>
			            <td class="txtc">95</td>
			            <td class="txtc">우선</td>
			            <td class="txtc"></td>
			        </tr>
			        <tr>
			            <td class="txtc">LIZ시스템</td>
			            <td class="txtc">17</td>
			            <td class="txtc">77</td>
			            <td class="txtc">94</td>
			            <td class="txtc">69</td>
			            <td class="txtc">11</td>
			            <td class="txtc">80</td>
			            <td class="txtc"></td>
			            <td class="txtc"></td>
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