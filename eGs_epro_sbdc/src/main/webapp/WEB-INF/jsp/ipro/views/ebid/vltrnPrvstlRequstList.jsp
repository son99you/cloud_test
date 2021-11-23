<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 수의시담 목록
 *
 * <pre>
 * ebid
 *    |_ vltrnPrvstlList.jsp
 * 
 * </pre>
 * @date : 2017. 06. 20
 * @version : 1.0
 * @author : 은우소프트 홍찬일
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %> 
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %> 

<script type="text/javascript" src="${jsPath}/ipro/views/ebid/vltrnPrvstlList.js"></script> 

<div class="conts_wrap">
	<div class="inner">
		<div class="tit_wrap">
			<h3 class="tit">수의시담요청 현황</h3>
			<ul class="step_wrap">
				<li><a href="#">${myMenuList.bigMenuNm}</a></li>
				<li><a href="#">${myMenuSubList.smallMenuNm}</a></li>
			</ul>			
		</div>
		<form id="searchFrm" class="search_form" method="POST" action="${contextPath}/ebid/iepVlprVltrnPrvstlList.do">
			<input type="hidden" name="resourceName" value="${ param.resourceName }" >
			<input type="hidden" id="P_PAGE_NO" name="P_PAGE_NO" value="${comFn:nvl(param.P_PAGE_NO, 1)}">
			<input type="hidden" id="P_PAGE_SIZE" name="P_PAGE_SIZE" value="${comFn:nvl(param.P_PAGE_SIZE, 10)}">
			
			<fieldset>
				<div class="view_wrap typeA">
					<div class="view_area">
						<table>
							<colgroup>												
								<col width="15%">
								<col width="35%">
								<col width="15%" >
								<col width="35%">
							</colgroup>
							<tr height="24">
								<th>
									계약명
								</th>
								<td>
									<input type="text"  id="P_PRVSTL_NM_S" name="P_PRVSTL_NM_S" value="${P_PRVSTL_NM_S }">
								</td>
								<th scope="row">
										요구자
									</th>
									<td>
				                        <input type="text" class="lineTxt w170" id="deptNmDeptTd" name="P_DEPT_NM_S" value="${param.P_DEPT_NM_S}" readonly  maxlength="300">
						                <input type="hidden" id="deptNoDeptTd" name="P_DEMAND_DEPT_NO_S" value="${param.P_DEMAND_DEPT_NO_S}" >
					                	<button type="button" class="btn btn_02 btn_sch vert" id="deptBtn">검색</button>
									</td>			
							</tr>
							<tr>
								 <th scope="row">업무구분</th>
			                    <td>
			                    	<div class="selectLayer2 w170">
		                   		<select>
		                   			<option>선택</option>
		                   			<option>공사</option>
		                   			<option>구매</option>
		                   			<option>용역</option>
		                   		</select>
		               		</div>
		                    	</td>
		                    	<th>
									계약담당자
								</th>
								<td>
			                    	<label for="userNmclient" class="blind">의뢰자명</label>
			                    	<input type="text" class="disabled w170" id="userNmclient" name="P_CLIENT_NM" readonly="readonly"  >
			                    	<input type="hidden" id="userIdclient" name="P_CLIENT_ID">
			                    	<button type="button" class="btn btn_02 btn_sch vert" id="searchUserBtn1">검색</button>
								</td>
							</tr>
							<tr height="24">
								<th>
									접수일자
								</th>
								<td>
									<div class="calendar_box">
										<label for=" " class="blind">의뢰일자 시작일</label>
					                   	<input type="text" class="w120 datepicker1" date id="P_REQEST_BEGIN_DE_S" name="P_REQEST_BEGIN_DE_S" value="${P_REQEST_BEGIN_DE_S }">
										<span class="wave"> ~ </span>
					                    <label for=" " class="blind">의뢰일자 마감일</label>
										<input type="text" class="w120 datepicker2" date id="P_REQEST_END_DE_S" name="P_REQEST_END_DE_S" value="${P_REQEST_END_DE_S }">
					                </div>
								</td>
								<th>
									진행상태
								</th>
								<td>
									<select class="w170">
										<option>전체</option>
										<option>공고중</option>
										<option>시담진행</option>
									</select>
								</td>
							</tr>
						</table>						
					</div>
				</div>
				
				<div class="btn_wrap mt10">
					<button type="button" class="btn btn_03 btn_inquire" id="searchBtn">조회</button>
				</div> <!--// btn_wrap E -->
				
				<div class="list_wrap mt30 contentWrap">
					<div class="list_top">
						<p class="total">총 <span>${ vltrnPrvstlListTotcnt}</span>건</p>		
						<!--  <div class="btn_right"> -->
							<!-- <button type="button" class="btn btn_02 btn_down">엑셀 다운로드</button> -->
						<!-- </div> -->
					</div> <!--// list_top E -->
					<div class="list_conts" style="width: auto; height: auto; overflow-x: scroll; overflow-y: hidden;">
						<table style="width: 1800px;">
			           		<colgroup>
								<col width="2%">
								<col width="5%">
								<col width="4%">
								<col width="8%">
								<col width="*">
								<col width="8%">
								<col width="5%">
								<col width="5%">
								<col width="7%">
								<col width="7%">
								<col width="7%">
								<col width="7%">
								<col width="7%">
								<col width="7%">
								<col width="5%">
							</colgroup>
							<thead>
				                <tr>
			                    	<th>번호</th>
									<th>진행상태</th>
									<th>업무구분</th>
									<th>공고번호</th>
									<th>계약명</th>
									<th>추정금액</th>
									<th>계약방법</th>
									<th>요구부서</th>
									<th>요구자</th>
									<th>작성일자</th>
									<th>요철일자</th>
									<th>개찰일자</th>
									<th>개찰일자</th>
									<th>낙찰/유찰일자</th>
									<th>처리자</th>
				                </tr>
				            </thead>
							<tbody>
								<tr class="row" onclick="fnProgrsDetailView('');" style="cursor: pointer;">
										<td>1&nbsp;</td>
										<td>공고중&nbsp;</td>
										<td>공사&nbsp;</td>
										<td>H2018-0000&nbsp;</td>
										<td class="pl5 list_tit">노트북 구매</td>
										<td style="text-align: right;">200,000,000&nbsp;</td>
										<td>일반경쟁&nbsp;</td>
										<td>계약관리팀</td>
										<td><div class="btn_before">사용자3</div></td>
										<td>2018-04-18&nbsp;</td>
										<td>2018-04-18&nbsp;</td>
										<td>2018-04-18&nbsp;</td>
										<td>2018-04-18&nbsp;</td>
										<td>2018-04-18&nbsp;</td>
										<td><div class="btn_before">사용자3</div></td>
									</tr>
								
									<tr class="row" onclick="fnProgrsDetailView('');" style="cursor: pointer;">
										<td>2&nbsp;</td>
										<td>공고중&nbsp;</td>
										<td>공사&nbsp;</td>
										<td>E2018-00001&nbsp;</td>
										<td class="pl5 list_tit">스마트폰 구매</td>
										<td style="text-align: right;">200,000,000&nbsp;</td>
										<td>일반경쟁&nbsp;</td>
										<td>계약관리팀</td>
										<td><div class="btn_before">사용자1</div></td>
										<td>2018-03-14&nbsp;</td>
										<td>2018-03-14&nbsp;</td>
										<td>2018-03-14&nbsp;</td>
										<td>2018-03-14&nbsp;</td>
										<td>2018-03-14&nbsp;</td>
										<td><div class="btn_before">사용자1</div></td>
									</tr>
							</tbody>
						</table>
					</div>	
					<div class="list_bottom">
						<comTag:pagingIpro totalCount="${vltrnPrvstlListTotcnt}" pageNo="${comFn:nvl(param.P_PAGE_NO, 1)}" pageSize="${comFn:nvl(param.P_PAGE_SIZE, 10)}" clickPage="pageObj.clickPage"/>
						<div class="list_btn">
<!-- 							<button type="button" class="btn btn_02 btn_blue" id="registBtn">등록</button> -->
						</div> <!--// btn_wrap E -->
					</div> <!--// list_bottom E -->			
				</div>				
				
			</fieldset>
		</form>
	</div>
</div>
<%-- DETAIL FORM --%>
<form id="detailFrm" method="POST" > 
	<input type="hidden" name="resourceName" value="${ param.resourceName }" >
	<input type="hidden" id="P_PRVSTL_NO" name="P_PRVSTL_NO">
	<input type="hidden" id="P_PRCURE_REQEST_NO" name="P_PRCURE_REQEST_NO">
	<input type="hidden" id="gbn" name="gbn" value="B">
</form>