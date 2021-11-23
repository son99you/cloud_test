<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 입찰현황 > 입찰계획품의 목록
 *
 * <pre>
 * ebid 
 *    |_ bidPlanAprvlList.jsp
 * 
 * </pre>
 * @date : 2017. 06. 19
 * @version : 1.0
 * @author : 은우소프트 전상훈
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %> 
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %> 

<script type="text/javascript" src="${jsPath}/ipro/views/ebid/bidPlanAprvlList.js"></script>

<div class="conts_wrap">
	<div class="inner">
		<div class="tit_wrap">
			<h3 class="tit">입찰계획품의 목록</h3>
			<ul class="step_wrap">
				<li><a href="#">${myMenuList.bigMenuNm}</a></li>
				<li><a href="#">${myMenuSubList.smallMenuNm}</a></li>
			</ul>			
		</div>
		<form id="searchFrm" method="post" >
			<input type="hidden" id="resourceName" name="resourceName" value="${param.resourceName}">
			<input type="hidden" id="P_PAGE_NO" name="P_PAGE_NO" value="${comFn:nvl(param.P_PAGE_NO, 1)}">
			<input type="hidden" id="P_PAGE_SIZE" name="P_PAGE_SIZE" value="${comFn:nvl(param.P_PAGE_SIZE, 10)}">
			<input type="hidden" name="P_USER_ID" value="${sessionScope.loginResult.USER_ID}" >
			<input type="hidden" id="search_gbn" name="search_gbn" value="${param.search_gbn}">
			
			<fieldset>
				<div class="view_wrap typeA">
					<div class="view_area">
						<table>
							<colgroup>
								<col width="130px" align="left">
								<col width="280px" align="left">
								<col width="130px" align="left">
								<col width="280px" align="left">
							</colgroup>
							<tr height="24px">
								<th>
									입찰명
								</th>
								<td>
									<input type="text"  id="P_BID_NM_S" name="P_BID_NM_S" value="${param.P_BID_NM_S}" maxlength="600">
								</td>
								<th>
									입찰구분
								</th>
								<td>
									<comTag:comCmcdCdValueComboBox id="P_PRCURE_SE_CD_S" name="P_PRCURE_SE_CD_S"   selectKey="${param.P_PRCURE_SE_CD_S}" cdId="C00001" headerKey="" headerValue="전체" />
								</td>
							</tr>
							<tr height="24px">
								<th>
									진행상태
								</th>
								<td>
									<comTag:comCmcdCdValueComboBox id="P_BID_DETAIL_PRST_CD_S" name="P_BID_DETAIL_PRST_CD_S"   selectKey="${P_BID_DETAIL_PRST_CD_S}" cdId="22646" cond1="PE" cond2="APRVL" headerKey="" headerValue="전체" />
								</td>
								<th>
									공고일자
								</th>
								<td>
									<div class="calendar_box">
					                    <label for=" " class="blind">접수일자 시작일</label>
					                    <input type="text" class="w120 datepicker1" id="P_PBLANC_BEGIN_DT_S" name="P_PBLANC_BEGIN_DT_S" value="${P_PBLANC_BEGIN_DT_S}" date>
					                	<span class="wave"> ~ </span>
					                    <label for=" " class="blind">접수일자 마감일</label>
					                    <input type="text" class="w120 datepicker2" id="P_PBLANC_END_DT_S" name="P_PBLANC_END_DT_S" value="${P_PBLANC_END_DT_S}" date >
					                </div>
								</td>
							</tr>
							<tr>
								<th>
									입찰한도액(백만원)
								</th>
								<td  colspan="3">
				                    <input type="text" class="w150" id="P_BID_LMT_BEGIN_AMOUNT_S" name="P_BID_LMT_BEGIN_AMOUNT_S" value="${comFn:formatMoney(param.P_BID_LMT_BEGIN_AMOUNT_S)}" money2  maxlength="6">
					                <span> ~ </span>
				                    <input type="text" class="w150" id="P_BID_LMT_END_AMOUNT_S" name="P_BID_LMT_END_AMOUNT_S" value="${comFn:formatMoney(param.P_BID_LMT_END_AMOUNT_S)}" money2  maxlength="6">
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
						<p class="total">총 <span>${comFn:nvl(bidPlanAprvlListTotcnt, 0)}</span>건</p>		
						<!--  <div class="btn_right"> -->
							<!-- <button type="button" class="btn btn_02 btn_down">엑셀 다운로드</button> -->
						<!-- </div> -->
					</div> <!--// list_top E -->
					<div class="list_conts">
						<table>
							<caption>입찰설명회 목록</caption>
		               		<colgroup>
			                   	<col width="8%"/>
			                   	<col width="10%"/>
			                   	<col width="7%"/>
			                   	<col width="*"/>
			                   	<col width="8%"/>
			                   	<col width="8%"/>
			                   	<col width="7%"/>
			                   	<col width="10%"/>
			            	</colgroup>
			            	<thead>
						    	<tr>
						        	<th>진행상태</th>
						            <th>공고번호</th>
						            <th>입찰구분</th>
						            <th>입찰명</th>
						            <th>공고일자</th>
						            <th>결재상신일자</th>
						            <th>계약방법</th>
						            <th class="last">입찰한도액(원)</th>
						        </tr>
			            	</thead>
					        <tbody>
					        	<c:if test="${comFn:nvl(bidPlanAprvlListTotcnt, 0) == 0}">
									<tr>
										<td class="txtc" colspan="9">검색 조건에 해당하는 데이터가 존재하지 않습니다.</td>
									</tr>
								</c:if>
								<c:if test="${bidPlanAprvlListTotcnt > 0}">
									<c:forEach var="data" items="${bidPlanAprvlList}" varStatus="status" >
										<c:if test="${status.count % 2  ne 0}">
											<c:set var="row" value="td01"></c:set>
										</c:if>
										<c:if test="${status.count % 2  eq 0}">
											<c:set var="row" value=""></c:set>
										</c:if>
										<tr class="pointer" onclick="bidPlanAprvlDetailInqire('${data.PBLANC_NO}', '${data.PBLANC_ODR}');">
											<td><div class="btn_before">${data.BID_DETAIL_PRST_CD_NM}</div></td>
											<td><c:if test="${not empty data.PBLANC_NO}">${data.PBLANC_NO}-${data.PBLANC_ODR}</c:if></td>
											<td>${data.PRCURE_SE_CD_NM}</td>
											<td class="pl5 list_tit" title="${data.BID_NM}">
												<c:if test="${data.EMRGNCY_BID_AT eq 'Y'}"><font color="red">[긴급] </font></c:if>
												<c:if test="${data.FNGPRT_BID_AT eq 'Y'}"><font color="red">[지문] </font></c:if>
					                    		<c:if test="${data.INTRLBID_AT eq 'Y'}"><font color="red">[국제] </font></c:if>
												${comFn:toShorten(data.BID_NM,38)}
											</td>
											<td>${comFn:formatDate(data.PBLANC_DT,'yyyyMMddHHmmss','yyyy-MM-dd')}</td>
											<td></td>
											<td>${data.CNTRCT_MTH_CD_NM}</td>
											<td class="pr5 txtr">${comFn:formatMoney(data.BID_LMT_AMOUNT)}</td>
										</tr>
									</c:forEach>
								</c:if>
					        </tbody>
					    </table>
					</div>
					<div class="list_bottom">
						<comTag:pagingIpro totalCount="${bidPlanAprvlListTotcnt}" pageNo="${comFn:nvl(param.P_PAGE_NO, 1)}" pageSize="${comFn:nvl(param.P_PAGE_SIZE, 10)}" clickPage="pageObj.clickPage"/>
					</div> <!--// list_bottom E -->			
			    </div>				
				
			</fieldset>
		</form>
	</div>
</div> 

<%-- DETAIL FORM --%>
<form id="viewFrm" method="POST">
	<input type="hidden" name="resourceName" value="${param.resourceName}">
	<input type="hidden" name="P_PBLANC_NO" >
	<input type="hidden" name="P_PBLANC_ODR" >
	<input type="hidden" name="P_USER_ID" value="${sessionScope.loginResult.USER_ID}" >
</form>
