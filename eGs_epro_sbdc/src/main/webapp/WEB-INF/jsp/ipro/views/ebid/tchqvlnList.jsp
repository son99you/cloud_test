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

<script type="text/javascript" src="${jsPath}/ipro/views/ebid/tchqvlnList.js"></script>

<div class="conts_wrap">
	<div class="inner">
		<div class="tit_wrap">
			<h3 class="tit">기술평가진행 목록</h3>
			<ul class="step_wrap">
				<li><a href="#">${myMenuList.bigMenuNm}</a></li>
				<li><a href="#">${myMenuSubList.smallMenuNm}</a></li>
			</ul>			
		</div>
		<form name="biSearchForm" method="post">
			<fieldset>
				<div class="view_wrap typeA">
					<div class="view_area">
						<table>
							<colgroup>
								<col width="15%" align="left">
								<col width="35%" align="left">
								<col width="15%" align="left">
								<col width="35%" align="left">
							</colgroup>
							<tr>
								<th>
									입찰명
								</th>
								<td>
									<input type="text"  >
								</td>
								<th>
									입찰구분
								</th>
								<td>
									<select>
										<option>전체</option>
										<option>물품</option>
										<option>공사</option>
										<option>용역</option>
									</select>
								</td>
							</tr>
							<tr>
								<th>
									평가상태
								</th>
								<td>
									<select>
										<option value="">전체</option>
										<option value="PJ10">계획수립대상</option>
										<option value="PJ20">평가계획수립</option>
										<option value="PJ30">위원추첨</option>
										<option value="PJ40">위원교섭중</option>
										<option value="PJ50">위원확정</option>
										<option value="PJ90">평가계획결재상신</option>
										<option value="PJ91">평가계획결재중</option>
										<option value="PJ92">평가계획결재반려</option>
										<option value="PJ93">평가계획결재회수</option>
										<option value="PJ94">평가계획결재완료</option>
									</select>
								</td>
								<th>
									기술평가일자
								</th>
								<td>
									<div class="calendar_box">
					                    <label for=" " class="blind">접수일자 시작일</label>
					                    <input type="text" class="w120 datepicker1"  date >
					                	<span class="wave"> ~ </span>
					                    <label for=" " class="blind">접수일자 마감일</label>
					                    <input type="text" class="w120 datepicker2" date >
					                </div>
								</td>
							</tr>
						</table>					
					</div>
				</div>
				
				<div class="btn_wrap mt10">
					<button type="button" class="btn btn_03 btn_inquire" id="searchBtn">조회</button>
				</div>
				
				<div class="list_wrap mt30">
					<div class="list_top">
						<p class="total">총 <span>100</span>건</p>
<!-- 						<div class="btn_right"> -->
<!-- 							<button type="button" class="btn btn_02 btn_down">엑셀 다운로드</button> -->
<!-- 						</div> -->
					</div> <!--// list_top E -->
					<div class="list_conts">
						<table>
							<caption>평가대기현황 목록</caption>
		               		<colgroup>
			                   	<col width="12%"/>
			                   	<col width="7%"/>
			                   	<col width="*"/>
			                   	<col width="8%"/>
			                   	<col width="15%"/>
			                   	<col width="8%"/>
			            	</colgroup>
				            <thead>
						    	<tr>
						            <th>공고번호</th>
						            <th>입찰구분</th>
						            <th>입찰명</th>
						            <th>공고일자</th>
						            <th>기술평가일자</th>
						            <th class="last">평가상태</th>
						        </tr>
				            </thead>	
					        <tr class="pointer" onclick="tchqvlnRegistForm('');">
					            <td>P2017-00100-1</td>
					            <td>공사</td>
					            <td class="pl5 list_tit">서울센터 에어컨 배관 이설공사</td>
					            <td>2017-06-20</td>
					            <td>2017-06-30 15:00~17:00</td>
					            <td>계획수립대상</td>
					        </tr>
					        <tr class="pointer" onclick="tchqvlnRegistForm('');">
					            <td>P2017-00099-1</td>
					            <td>용역</td>
					            <td class="pl5 list_tit">2017년 하반기 NCS기반 신입직원 채용대행 용역</td>
					            <td>2017-06-18</td>
					            <td>2017-06-28 15:00~17:00</td>
					            <td>계획수립대상</td>
					        </tr>
					        <tr class="pointer" onclick="tchqvlnRegistForm('');">
					            <td>P2017-00098-1</td>
					            <td>용역</td>
					            <td class="pl5 list_tit">팔레스타인 창취업 지원프로그램 국내 PC용역</td>
					           	<td>2017-06-16</td>
					            <td>2017-06-26 15:00~17:00</td>
					            <td>계획수립대상</td>
					        </tr>
					        <tr class="pointer" onclick="tchqvlnRegistForm('');">
					            <td>P2017-00097-1</td>
					            <td>물품</td>
					            <td class="pl5 list_tit">소모성 물품(MRO) 물품대행 사업자 선정</td>
					            <td>2017-06-16</td>
					            <td>2017-06-26 15:00~17:00</td>
					            <td>계획수립대상</td>
					        </tr>
					        <tr class="pointer" onclick="tchqvlnRegistForm('PJ30');">
					            <td>P2017-00096-1</td>
					            <td>용역</td>
					            <td class="pl5 list_tit">인력선발 및 교육운영 지원업무 위탁 용역</td>
					            <td>2017-06-14</td>
					            <td>2017-06-24 15:00~17:00</td>
					            <td>위원추첨</td>
					        </tr>
					        <tr class="pointer" onclick="tchqvlnRegistForm('PJ30');">
					            <td>P2017-00095-1</td>
					            <td>용역</td>
					            <td class="pl5 list_tit">소셜미디어 홍보대행 [2017-2019] 용역</td>
					            <td>2017-06-12</td>
					            <td>2017-06-22 15:00~17:00</td>
					            <td>위원추첨</td>
					        </tr>
					        <tr class="pointer" onclick="tchqvlnRegistForm('PJ30');">
					            <td>P2017-00094-1</td>
					            <td>공사</td>
					            <td class="pl5 list_tit">서울센터 외벽도색공사</td>
					            <td>2017-06-10</td>
					            <td>2017-06-20 15:00~17:00</td>
					            <td>위원추첨</td>
					        </tr>
					        <tr class="pointer" onclick="tchqvlnRegistForm('PJ50');">
					            <td>P2017-00093-1</td>
					            <td>용역</td>
					            <td class="pl5 list_tit">원조조달기업지원센터 위탁운영 용역</td>
					            <td>2017-06-08</td>
					            <td>2017-06-18 15:00~17:00</td>
					            <td>위원확정</td>
					        </tr>
					        <tr class="pointer" onclick="tchqvlnRegistForm('PJ50');">
					            <td>P2017-00092-1</td>
					            <td>용역</td>
					            <td class="pl5 list_tit">전자정부 및 ICT분야 ODA사업 통합감리용역</td>
					            <td>2017-06-06</td>
					            <td>2017-06-16 15:00~17:00</td>
					            <td>위원확정</td>
					        </tr>
					        <tr class="pointer" onclick="tchqvlnRegistForm('PJ50');">
					            <td>P2017-00091-1</td>
					            <td>물품</td>
					            <td class="pl5 list_tit">국제 동등성 확보 지원 및 통합시스템 구축사업 시험기자재 공급업체 선정</td>
					            <td>2017-06-04</td>
					            <td>2017-06-14 15:00~17:00</td>
					            <td>위원확정</td>
					        </tr>
					    </table>					
					</div>
					<div class="list_bottom">
						<div class="paging_wrap">
							<comTag:pagingIpro totalCount="100" pageNo="${comFn:nvl(param.P_PAGE_NO, 1)}" pageSize="${comFn:nvl(param.P_PAGE_SIZE, 10)}" clickPage="pageObj.clickPage"/>
						</div>
					</div> <!--// list_bottom E -->					
				</div>
			</fieldset>
		</form>
	</div>
</div> 

<%-- DETAIL FORM --%>
<form id="registFrm" method="POST" > 
	<input type="hidden" name="P_PBLANC_NO" value="" >
	<input type="hidden" name="P_PBLANC_ODR" value="" >
	<input type="hidden" name="P_TCHQVLN_PLAN_PRST_CD" value="" >
	<input type="hidden" name="resourceName" value="${ param.resourceName }" >
</form>