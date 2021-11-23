<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 입찰관리 > 입찰작성대기현황 목록
 *
 * <pre>
 * ebid 
 *    |_ bidPlanList.jsp
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

<script type="text/javascript" src="${jsPath}/ipro/views/ebid/bidPlanList.js"></script> 
 
<div class="contents_wrap">
	<ul class="step_wrap">
		<li><a href="#">${myMenuList.bigMenuNm}</a></li>
		<li><a href="#">${myMenuSubList.smallMenuNm}</a></li>
	</ul>
	<div class="tit_wrap">
		<h3 class="tit">입찰작성대기현황</h3>
	</div>
	
	<form id="searchFrm" method="post" >
		<input type="hidden" id="resourceName" name="resourceName" value="${param.resourceName}">
		<input type="hidden" id="P_PAGE_NO" name="P_PAGE_NO" value="${comFn:nvl(param.P_PAGE_NO, 1)}">
		<input type="hidden" id="P_PAGE_SIZE" name="P_PAGE_SIZE" value="${comFn:nvl(param.P_PAGE_SIZE, 10)}">
		<input type="hidden" name="P_USR_ID" value="${sessionScope.loginResult.USR_ID}" >
		<input type="hidden" id="search_gbn" name="search_gbn" value="${param.search_gbn}">
		
		<fieldset>
			<div class="view_wrap typeA">
				<div class="view_area">
					<table>
						<colgroup>
							<col width="15%">
							<col width="35%">
							<col width="15%">
							<col width="35%">
						</colgroup>
						<tr>
							<th>구매요구명</th>
							<td colspan="3">
								<input type="text" id="P_PCRQ_NM_S" name="P_PCRQ_NM_S" value="${param.P_PCRQ_NM_S }">
							</td>
						</tr>
						<tr>
							<th>계약구분</th>
							<td>
								<comTag:comCmcdCdValueComboBox name="P_CONT_SECD_S" cdId="CONT_SECD" selectKey="${param.P_CONT_SECD_S }" headerValue="전체" width="180" />
							</td>
							<th>요구부서</th>
							<td>
								<input type="text" class="w180" id="P_DEPT_NM" name="P_RQR_DEPT_NM_S" value="${param.P_RQR_DEPT_NM_S }">
								<input type="hidden" id="P_DEPT_NO" name="P_RQR_DEPT_NO_S" value="${param.P_RQR_DEPT_NO_S }">
								<button type="button" class="btn btn_s btn_sch" id="searchDeptBtn">검색</button>
							</td>							 						
						</tr>
						<tr>
							<th>입찰담당자</th>
							<td>
								<input type="text" class="w180" id="usrNm" name="P_PRCH_CHRGR_NM_S" value="${param.P_PRCH_CHRGR_NM_S }" readonly>
								<input type="hidden" id="usrId" name="P_PRCH_CHRGR_ID_S" value="${param.P_PRCH_CHRGR_ID_S }">
								<button type="button" class="btn btn_s btn_sch" id="chargerBtn">검색</button>
								<button type="button" class="btn btn_s btn_sch" id="chargerDelBtn">삭제</button>
							</td>
							<th></th>
							<td></td>
						</tr>
					</table>
				</div>					
			</div>
			
			<div class="btn_wrap mt10">
				<button type="button" class="btn btn_m btn_c2" id="searchBtn">조회</button>
			</div> <!--// btn_wrap E -->
					
		</fieldset>
	</form>	
	
	<div class="list_wrap mt30" id="contentWrap">
		<div class="list_top">
			<p class="total">총 <span>${comFn:nvl(bidPlanListTotcnt, 0)}</span>건</p>
		</div> <!--// list_top E -->
		<div class="list_tb" style="overflow: auto;">		
			<table style="width: 100%;">
				<caption>입찰작성대기현황 목록</caption>
              		<colgroup>
                   	<col width="8%"/>
                   	<col width="8%"/>
                   	<col width="*"/>
                   	<col width="10%"/>
                   	<col width="8%"/>
                   	<col width="15%"/>
                   	<col width="8%"/>
                   	<col width="8%"/>
                   	<col width="8%"/>
            	</colgroup>
            	<thead>
			    	<tr>
			        	<th scope="col">진행상태</th>
			        	<th scope="col">계약구분</th>
			            <th scope="col">구매요구명</th>
			            <th scope="col">추정금액</th>
			            <th scope="col">입찰방법</th>
			            <th scope="col">요구부서</th>
			            <th scope="col">요구자</th>
			            <th scope="col">입찰담당자</th>
			            <th scope="col">사전공고<br/>진행상태</th>
			        </tr>
            	</thead>
		        <tbody>
		        	<c:if test="${comFn:nvl(bidPlanListTotcnt, 0) == 0}">
						<tr>
							<td class="txtc" colspan="8">검색 조건에 해당하는 데이터가 존재하지 않습니다.</td>
						</tr>
					</c:if>
					<c:if test="${bidPlanListTotcnt > 0}">
						<c:forEach var="data" items="${bidPlanList}" varStatus="status" >
							<tr class="row" onclick="bidPlanDetailInqire('${data.BID_WAIT_NO}', '${data.ANNC_NO}', '${data.ANNC_NGR}', '${data.ROUND_NO}');" style="cursor: pointer;">
								<td><div <c:if test="${data.BID_PSCD eq 'PF00' }">style="color: red;"</c:if>>${data.BID_PSCD_NM}</div></td>
								<td>${data.CONT_SECD_NM }</td>
								<td class="pl5 list_tit pointer" title="${data.PCRQ_NM}">
									<c:if test="${data.EMRG_YN eq 'Y'}"><font color="red">[긴급] </font></c:if>
									<c:if test="${data.ANNC_NGR > '1'}"><font color="red">[정정] </font></c:if>
									${comFn:toShorten(data.PCRQ_NM,42)}
								</td>
								<td class="pr5 txtr">${comFn:formatMoney(data.ESTT_AMT)}</td>
								<td>${data.BID_MTCD_NM}</td>
								<td class="txtl pl5">${data.RQR_DEPT_NM}</td>
								<td>${data.RQR_CHRGR_NM}</td>
								<td>${data.PRCH_CHRGR_NM}</td>
								<td>${data.BID_PSCD_NM}</td>
							</tr>
						</c:forEach>
					</c:if>
		        </tbody>
		    </table>
		</div>
		
		<div class="list_bottom">
			<comTag:pagingIpro totalCount="${bidPlanListTotcnt}" pageNo="${comFn:nvl(param.P_PAGE_NO, 1)}" pageSize="${comFn:nvl(param.P_PAGE_SIZE, 10)}" clickPage="pageObj.clickPage"/>
		</div> <!--// list_bottom E -->			
    </div>	
</div>
		
<%-- DETAIL FORM --%>
<form id="viewFrm" method="POST" action="">
	<input type="hidden" name="resourceName" value="${param.resourceName}">
	<input type="hidden" name="P_PCAC_NO" value="">
	<input type="hidden" name="P_PCAC_ODR" value="">
	<input type="hidden" name="P_BID_WAIT_NO" value="">
	<input type="hidden" name="P_ANNC_NO" value="">
	<input type="hidden" name="P_ANNC_NGR" value="">
	<input type="hidden" name="P_ROUND_NO" value="">
</form>
<form id="popupFrm" method="POST" action="">
	<input type="hidden" name="resourceName" value="${param.resourceName}">
	<input type="hidden" name="resourceDesc" value="${param.resourceDesc}">
</form>