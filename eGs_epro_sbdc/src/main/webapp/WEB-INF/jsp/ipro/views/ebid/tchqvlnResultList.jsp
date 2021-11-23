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

<script type="text/javascript" src="${jsPath}/ipro/views/ebid/tchqvlnResultList.js"></script> 
 
<div class="content">
	<h3>평가결과현황 목록</h3>
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
												
												입찰구분
											</td>
											<td>
												<select>
													<option>전체</option>
													<option>물품</option>
													<option>공사</option>
													<option>용역</option>
												</select>
											</td>
										</tr>
										<tr height="24px">
											<td>
												
												기술평가일자
											</td>
											<td  colspan="3">
												<div class="calendar_box">
								                    <label for=" " class="blind">접수일자 시작일</label>
								                    <input type="text"  date >
								                </div>
								                <span class="wave"> ~ </span>
								                <div class="calendar_box" >
								                    <label for=" " class="blind">접수일자 마감일</label>
								                    <input type="text"  date >
								                </div>
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
				<!--  <div class="btn_right"> -->
					<!-- <button type="button" class="btn btn_02 btn_down">엑셀 다운로드</button> -->
				<!-- </div> -->
			</div> <!--// list_top E -->
			<div class="list_conts">		
				<table>
					<caption>기술평가결과 목록</caption>
	               		<colgroup>
		                   	<col width="10%"/>
		                   	<col width="10%"/>
		                   	<col width="*"/>
		                   	<col width="10%"/>
		                   	<col width="10%"/>
		                   	<col width="10%"/>
		                   	<col width="10%"/>
		            	</colgroup>
			    	<tr>
			            <th>공고번호</th>
			            <th>입찰구분</th>
			            <th>입찰명</th>
			            <th>공고일자</th>
			            <th>기술평가일자</th>
			            <th>소집서면구분</th>
			            <th class="last">평가상태</th>
			        </tr>
			        <tr class="pointer" onclick="tchqvlnResultRegistForm();">
			            <td class="txtc">P2017-00100-1</td>
			            <td class="txtc">공사</td>
			            <td >서울센터 에어컨 배관 이설공사</td>
			            <td class="txtc">2017-06-20</td>
			            <td class="txtc">2017-06-30</td>
			            <td class="txtc">소집</td>
			            <td class="txtc">평가중</td>
			        </tr>
			        <tr class="pointer" onclick="tchqvlnResultRegistForm();">
			            <td class=" txtc">P2017-00099-1</td>
			            <td class=" txtc">용역</td>
			            <td >2017년 하반기 NCS기반 신입직원 채용대행 용역</td>
			            <td class=" txtc">2017-06-18</td>
			            <td class=" txtc">2017-06-28</td>
			            <td class=" txtc">소집</td>
			            <td class=" txtc">평가중</td>
			        </tr>
			        <tr class="pointer" onclick="tchqvlnResultRegistForm();">
			            <td class="txtc">P2017-00098-1</td>
			            <td class="txtc">용역</td>
			            <td >팔레스타인 창취업 지원프로그램 국내 PC용역</td>
			           	<td class="txtc">2017-06-16</td>
			            <td class="txtc">2017-06-26</td>
			            <td class="txtc">소집</td>
			            <td class="txtc">평가중</td>
			        </tr>
			        <tr class="pointer" onclick="tchqvlnResultRegistForm();">
			            <td class=" txtc">P2017-00097-1</td>
			            <td class=" txtc">물품</td>
			            <td >소모성 물품(MRO) 물품대행 사업자 선정</td>
			            <td class=" txtc">2017-06-16</td>
			            <td class=" txtc">2017-06-26</td>
			            <td class=" txtc">소집</td>
			            <td class=" txtc">평가완료</td>
			        </tr>
			        <tr class="pointer" onclick="tchqvlnResultRegistForm();">
			            <td class="txtc">P2017-00096-1</td>
			            <td class="txtc">용역</td>
			            <td >인력선발 및 교육운영 지원업무 위탁 용역</td>
			            <td class="txtc">2017-06-14</td>
			            <td class="txtc">2017-06-24</td>
			            <td class="txtc">소집</td>
			            <td class="txtc">평가완료</td>
			        </tr>
			        <tr class="pointer" onclick="tchqvlnResultRegistForm();">
			            <td class=" txtc">P2017-00095-1</td>
			            <td class=" txtc">용역</td>
			            <td >소셜미디어 홍보대행 [2017-2019] 용역</td>
			            <td class=" txtc">2017-06-12</td>
			            <td class=" txtc">2017-06-22</td>
			            <td class=" txtc">소집</td>
			            <td class=" txtc">평가완료</td>
			        </tr>
			        <tr class="pointer" onclick="tchqvlnResultRegistForm();">
			            <td class="txtc">P2017-00094-1</td>
			            <td class="txtc">공사</td>
			            <td >서울센터 외벽도색공사</td>
			            <td class="txtc">2017-06-10</td>
			            <td class="txtc">2017-06-20</td>
			            <td class="txtc">소집</td>
			            <td class="txtc">평가완료</td>
			        </tr>
			        <tr class="pointer" onclick="tchqvlnResultRegistForm();">
			            <td class=" txtc">P2017-00093-1</td>
			            <td class=" txtc">용역</td>
			            <td >원조조달기업지원센터 위탁운영 용역</td>
			            <td class=" txtc">2017-06-08</td>
			            <td class=" txtc">2017-06-18</td>
			            <td class=" txtc">소집</td>
			            <td class=" txtc">평가완료</td>
			        </tr>
			        <tr class="pointer" onclick="tchqvlnResultRegistForm();">
			            <td class="txtc">P2017-00092-1</td>
			            <td class="txtc">용역</td>
			            <td >전자정부 및 ICT분야 ODA사업 통합감리용역</td>
			            <td class="txtc">2017-06-06</td>
			            <td class="txtc">2017-06-16</td>
			            <td class="txtc">소집</td>
			            <td class="txtc">평가완료</td>
			        </tr>
			        <tr class="pointer" onclick="tchqvlnResultRegistForm();">
			            <td class=" txtc">P2017-00091-1</td>
			            <td class=" txtc">물품</td>
			            <td >국제 동등성 확보 지원 및 통합시스템 구축사업 시험기자재 공급업체 선정</td>
			            <td class=" txtc">2017-06-04</td>
			            <td class=" txtc">2017-06-14</td>
			            <td class=" txtc">소집</td>
			            <td class=" txtc">평가완료</td>
			        </tr>
			    </table>
			</div>
	    </div>
	    
	    <div class="paging_place">
			<div class="paging_wrap"><a href="#" class="pprev" title="맨앞으로" style="display: none;"><span style="display: none;">맨앞으로</span></a><a href="#" class="prev" title="앞으로"><span style="display: none;">앞으로</span></a>
			<span><a href="#" class="active" title="1">1</a></span>
			<span><a href="#" title="2">2</a></span>
			<span><a href="#" title="3">3</a></span>
			<span><a href="#" title="4">4</a></span>
			<span><a href="#" title="5">5</a></span>
			<span><a href="#" title="6">6</a></span>
			<span><a href="#" title="7">7</a></span>
			<span><a href="#" title="8">8</a></span>
			<span><a href="#" title="9">9</a></span>
			<span><a href="#" title="10">10</a></span>
			<a href="#" class="next" title="뒤로"><span style="display: none;">뒤로</span></a><a href="#" class="nnext" title="맨뒤로" style="display: none;"><span style="display: none;">맨뒤로</span></a></div>
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