<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 예가현황 > 예가관리 목록
 *
 * <pre>
 * ebid 
 *    |_ prdprcManageList.jsp
 * 
 * </pre>
 * @date : 2017. 06. 20
 * @version : 1.0
 * @author : 은우소프트 전상훈
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %> 
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %> 

<script type="text/javascript" src="${jsPath}/ipro/views/ebid/prdprcManageList.js"></script> 

<div class="conts_wrap">
	<div class="inner">
		<div class="tit_wrap">
			<h3 class="tit">예가등록현황</h3>
			<ul class="step_wrap">
				<li><a href="#">${myMenuList.bigMenuNm}</a></li>
				<li><a href="#">${myMenuSubList.smallMenuNm}</a></li>
			</ul>			
		</div>
		<form id="searchFrm" method="post">
			<input type="hidden" name="resourceName" value="${param.resourceName}">
			<input type="hidden" id="P_PAGE_NO" name="P_PAGE_NO" value="${comFn:nvl(param.P_PAGE_NO, 1)}">
			<input type="hidden" id="P_PAGE_SIZE" name="P_PAGE_SIZE" value="${comFn:nvl(param.P_PAGE_SIZE, 10)}">
			
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
							<tr height="24px">
								<th>
									공고명
								</th>
								<td>
									<input type="text" onKeyDown="enter(event);" class="w_95p" name="P_CNTRCT_NM_S" value="${P_CNTRCT_NM_S }">
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
								<th>
									공고구분
								</th>
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
							<tr>
								<th>
									접수일자
								</th>
								<td>
						            <div class="calendar_box">
						            <label for="P_BEFFAT_PBLANC_BEGIN_DE_S" class="blind">사전공고시작일자</label>
					                    <input type="text" class="w120 datepicker1" id="P_BEFFAT_PBLANC_BEGIN_DE_S" name="P_BEFFAT_PBLANC_BEGIN_DE_S" maxlength="10" date value="${comFn:formatDate(P_BEFFAT_PBLANC_BEGIN_DE_S,'yyyyMMdd','yyyy-MM-dd')}">
					                <span class="wave"> &nbsp;~ &nbsp;</span>
						            <label for="P_BEFFAT_PBLANC_END_DE_S" class="blind">사전공고종료일자</label>
						            	<input type="text" class="w120 datepicker2" id="P_BEFFAT_PBLANC_END_DE_S" name="P_BEFFAT_PBLANC_END_DE_S" maxlength="10" date value="${comFn:formatDate(P_BEFFAT_PBLANC_END_DE_S,'yyyyMMdd','yyyy-MM-dd')}">
						            </div>												
								</td>
								<th>
									진행상태
								</th>
								<td>
									<select class="w170">
										<option>선택</option>
										<option>공고중</option>
									</select>
								</td>
							</tr>
						</table> 					
					</div>
				</div>
				
				<div class="btn_wrap mt10">
					<button type="button" class="btn btn_03 btn_inquire" id="searchBtn">조회</button>
				</div> <!--// btn_wrap E -->
				
				<div class="list_wrap mt30" id="contentWrap">
					<div class="list_top">
						<p class="total">총1</span>건</p>		
						<!--  <div class="btn_right"> -->
							<!-- <button type="button" class="btn btn_02 btn_down">엑셀 다운로드</button> -->
						<!-- </div> -->
					</div> <!--// list_top E -->
					<div class="list_conts" style="width: auto; height: auto; overflow-x: scroll; overflow-y: hidden;">
						<table style="width: 1500px;">
			           		<colgroup>
								<col width="2%">
								<col width="5%">
								<col width="4%">
								<col width="10%">
								<col width="*">
								<col width="10%">
								<col width="5%">
								<col width="5%">
								<col width="6%">
								<col width="8%">
								<col width="8%">
								<col width="8%">
								<col width="6%">
							</colgroup>
			            	<thead>
						    	<tr>
						            <th>No</th>
						            <th>진행상태</th>
						            <th>업무구분</th>
						            <th>공고번호</th>
						            <th>계약명</th>
						            <th>추정금액</th>
						            <th>계약방법</th>
						            <th>요구부서</th>
						            <th>요구자</th>
						            <th>요구일자</th>
						            <th>접수일자</th>
						            <th>작성일자</th>
						            <th>작성자</th>
						        </tr>
			            	</thead>
			            	<tbody>
						       	<tr class="row" onclick="prvstlPrdprcManageDetailInqire('','','','');" style="cursor: pointer;">
											<td>1</td>
											 <td>공고중</td>
											<td>용역</td>
											<td>P2018-00001-1</td>
											<td class="pl5 list_tit">
												노트북 구매
											</td>
											<td style="text-align: right;">200,000,000</td>
											<td>단일예가</td>
											<td>계약관리팀</td>
											<td>홍찬일</td>
											<td>2018-04-11</td>
											<td>2018-04-11</td>
											<td>2018-04-11</td>
											<td>홍찬일</td>
										</tr>
									
										<tr class="row" onclick="prvstlPrdprcManageDetailInqire('','','','');" style="cursor: pointer;">
											<td>2</td>
											 <td>공고중</td>
											<td>용역</td>
											<td>P2018-00002-1</td>
											<td class="pl5 list_tit">
												스마트폰 구매
											</td>
											<td style="text-align: right;">200,000,000</td>
											<td>복수예가</td>
											<td>계약관리팀</td>
											<td>홍찬일</td>
											<td>2018-04-11</td>
											<td>2018-04-11</td>
											<td>2018-04-11</td>
											<td>홍찬일</td>
										</tr>
			            	</tbody>
					    </table>
					</div>
					<div class="list_bottom">
						<comTag:pagingIpro totalCount="${0}" pageNo="${comFn:nvl(param.P_PAGE_NO, 1)}" pageSize="${comFn:nvl(param.P_PAGE_SIZE, 10)}" clickPage="pageObj.clickPage"/>
						<div class="list_btn">
							<button type="button" class="btn btn_02 btn_blue" id="registBtn">신규등록</button>
						</div>	
					</div> <!--// list_bottom E -->		
			    </div>				
				
			</fieldset>
		</form>
	</div>
</div>

<%-- DETAIL FORM --%>
<form id="viewFrm" method="POST" action="">
	<input type="hidden" name="resourceName" value="${param.resourceName}">
	<input type="hidden" name="P_PBLANC_NO" >
	<input type="hidden" name="P_PBLANC_ODR" >
	<input type="hidden" name="P_PRVSTL_AT">
	<input type="hidden" name="P_BSIS_PRDPRC_STTUS_CD">
</form>
<form id="popupFrm" method="POST">
</form>