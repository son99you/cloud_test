<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 나의 수의시담 목록
 *
 * <pre>
 * ebid
 *    |_ myVltrnPrvstlList.jsp
 * 
 * </pre>
 * @date : 2017. 06. 23
 * @version : 1.0
 * @author : 은우소프트 홍찬일
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %> 
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %> 

<script type="text/javascript" src="${jsPath}/opro/views/ebid/myVltrnPrvstlList.js"></script> 

<div class="contents_wrap">
	<ul class="step_wrap">
		<li><a href="#">${myMenuList.bigMenuNm}</a></li>
		<li><a href="#">${myMenuSubList.smallMenuNm}</a></li>
	</ul>
	<div class="tit_wrap">
		<h3 class="tit">시담요청현황</h3>
	</div>

	<form id="searchFrm" class="searchFrm" method="POST" action="${contextPath}/elbi/myBidPblancList.do">
		<input type="hidden" name="resourceName" value="${param.resourceName}">
		<input type="hidden" id="P_PAGE_NO" name="P_PAGE_NO" value="${comFn:nvl(param.P_PAGE_NO, 1)}">
		<input type="hidden" id="P_PAGE_SIZE" name="P_PAGE_SIZE" value="${comFn:nvl(param.P_PAGE_SIZE, 10)}">
		
		<fieldset>
			<div class="view_wrap typeA">	
	        	<div class="view_area">
					<table>
						<colgroup>
							<col style="width: 15%;">
							<col style="width: 35%;">
							<col style="width: 15%;">
							<col style="width: 35%;">
						</colgroup>
						<tbody>
							<tr>
								<th scope="row">계약명</th>
								<td colspan="3">
	               					<input type="text" class="lineTxt" id="P_ESTMT_REQEST_NM_S" name="P_ESTMT_REQEST_NM_S" value="" >
								</td>
							</tr>
							<tr>
								<th scope="row">업무구분</th>
								<td>
					            	<select>
					            		<option>전체</option>
					            		<option>물품</option>
					            		<option>공사</option>
					            		<option>용역</option>
					            	</select>
								</td>
								<th>요구부서</th>
								<td>
									<input type="text" style="width: 90%;">
									<button type="button" class="btn btn_s btn_sch" id="zipBtn" name="zipBtn">검색</button>
								</td>
							</tr>
							<tr>
								<th scope="row"><label for="division">요청일자</label></th>
								<td>
									<div class="calendar_box">
		              					<input type="text" class="datepicker1" id="P_REQEST_DE_BEGIN_S" name="P_REQEST_DE_BEGIN_S"  value="${P_REQEST_DE_BEGIN_S}"  >
										<span class="wave">~</span>
		              					<input type="text" class="datepicker2" id="P_REQEST_DE_END_S" name="P_REQEST_DE_END_S" value="${P_REQEST_DE_END_S}"   >
									</div> <!--// calendar_box E -->
								</td>
								<th scope="row">진행상태</th>
								<td>
					            	<select>
					            		<option>전체</option>
					            		<option>요청중</option>
					            		<option>마감</option>
					            	</select>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
			
		 	<div class="btn_wrap mt10">
				<button type="button" class="btn btn_m btn_c2" id="searchBtn">조회</button>
			</div> <!--// btn_wrap E -->
			
			<div class="list_wrap mt30">
				<div class="list_top">
					<p class="total">총 <span>${ 2}</span>건</p>
				</div> <!--// list_top E -->
				<div class="list_conts">
				<table class="tableList" summary="나의 입찰공고 목록입니다.">
		               <caption>나의 입찰공고 목록</caption>
		               <colgroup>
		                    <col width="5%"/>
		                   	<col width="8%"/>
		                   	<col width="10%"/>
		                   	<col width="10%"/>
		                   	<col width="*"/>
		                   	<col width="10%"/>
		                   	<col width="10%"/>
		                   	<col width="10%"/>
		                   	<col width="10%"/>
			               </colgroup>			
						<thead class="line">
			                <tr>
			                	<th class="noBg" scope="col">NO</th>
					            <th scope="col">진행상태</th>
					            <th scope="col">업무구분</th>
					            <th scope="col">공고번호</th>
					            <th scope="col">공고명</th>
					            <th scope="col">추정금액</th>
					            <th scope="col">계약방법</th>
					            <th scope="col">공고일자</th>
					            <th scope="col">담당자</th>
			                </tr>
			            </thead>
						<tbody>  
							<tr class="pointer" onclick="fnProgrsDetailView();" style="cursor: pointer;">
								<td class="txtc">
									1
								</td>
								<td class="txtc">
									요청
								</td>
								<td class="txtc">
									구매
								</td>
								<td class="txtc">
									20180000001
								</td>
								<td class="list_tit">
									노트북 구매
								</td>
								<td class="txtr right_T">
									200,000,000원
								</td>
								<td class="txtc">
									수의계약
								</td>
								<td class="txtc">
									2018-09-10
								</td>
								<td class="txtc">
									홍찬일
								</td>
							</tr>
							<tr class="pointer" onclick="fnProgrsDetailView();" style="cursor: pointer;">
								<td class="txtc">
									2
								</td>
								<td class="txtc">
									마감
								</td>
								<td class="txtc">
									구매
								</td>
								<td class="txtc">
									20180000002
								</td>
								<td class="list_tit">
									스마트폰 구매
								</td>
								<td class="txtr right_T">
									200,000,000원
								</td>
								<td class="txtc">
									수의계약
								</td>
								<td class="txtc">
									2018-09-11
								</td>
								<td class="txtc">
									홍찬일
								</td>
							</tr>
						</tbody>
					</table>
				</div> <!--// list_conts E -->
				<div class="list_bottom">
					<div class="list_pager">
						<comTag:paging totalCount="${0}" pageNo="${comFn:nvl(param.P_PAGE_NO, 1)}" pageSize="${comFn:nvl(param.P_PAGE_SIZE, 10)}" clickPage="pageObj.clickPage"/>
					</div> <!--// list_pager E -->
				</div> <!--// list_bottom E -->
			</div> <!--// list_wrap E -->
		</fieldset>	
	</form>
</div>	
	
<form id="detailFrm" method="POST" action="${contextPath}/vlpr/vltrnPrvstlDetail.do">
	<input type="hidden" id="P_PRVSTL_NO" name="P_PRVSTL_NO" />
	<input type="hidden" id="resourceName" name="resourceName" value="${param.resourceName}">
	
</form>

<form id="registFrm" method="POST" action="${contextPath}/vlpr/vltrnPrvstlRequstRegistForm.do">
	<input type="hidden" name="P_PRCURE_REQEST_NO" />
	<input type="hidden" id="resourceName" name="resourceName" value="${param.resourceName}">
</form>

<form id="newRegistFrm" method="POST" action="${contextPath}/vlpr/vltrnPrvstlNewRegistForm.do">
	<input type="hidden" id="resourceName" name="resourceName" value="${param.resourceName}">
</form>