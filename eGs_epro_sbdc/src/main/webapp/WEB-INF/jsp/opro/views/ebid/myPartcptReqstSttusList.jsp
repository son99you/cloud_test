<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
입찰관리 > 나의 참가신청 목록
 *
 * <pre>
 * ebid 
 *    |_ myPartcptReqstSttusList.jsp
 * 
 * </pre>
 * @date : 2017. 06. 20.
 * @version : 1.0
 * @author : 은우소프트 이주연
--%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %>
<%@ taglib prefix="fc" uri="/WEB-INF/tlds/fwkComboBoxTag.tld" %>

<script type="text/javascript" src="${jsPath}/comm/comUtil.js"></script>  
<script type="text/javascript" src="${jsPath}/opro/views/ebid/myPartcptReqstSttusList.js"></script>   


	<ul class="step_wrap">
		<li><a href="#">경쟁입찰</a></li>
		<li><a href="#">나의참가신청</a></li>
	</ul> <!--// step_wrap E -->
<div class="tit_wrap">
	<h3 class="tit">나의참가신청</h3>
</div>

<form id="searchFrm" class="search_form" method="POST" >
	<input type="hidden" id="resourceName" name="resourceName" value="${param.resourceName}">
	<input type="hidden" id="P_PAGE_NO" name="P_PAGE_NO" value="${comFn:nvl(param.P_PAGE_NO, 1)}">
	<input type="hidden" id="P_PAGE_SIZE" name="P_PAGE_SIZE" value="${comFn:nvl(param.P_PAGE_SIZE, 10)}">
    <fieldset>
		<legend>구매견적</legend>
        <div class="view_wrap typeA">	
        	<div class="view_area">
				<table>
					<caption>구매견적</caption>
					<colgroup>
						<col style="width: 15%;">
						<col style="width: auto;">
					</colgroup>
					<tbody>
						<tr>
							<th scope="row"><label for="plan">입찰명</label></th>
							<td>
								<input type="text" id="P_BID_NM_S" name="P_BID_NM_S" value="${param.P_BID_NM_S}" maxlength="600">
							</td>
						</tr>
						<tr>
							<th scope="row"><label for="division">진행상태</label></th>
							<td>
								<fc:fwkComboBox name="P_BID_PROGRS_STTUS_CD_S" id="P_BID_PROGRS_STTUS_CD_S" selectKey="${param.P_BID_PROGRS_STTUS_CD_S}" list="{'':'전체', 'PF20':'공고중','PF30':'정정공고','PF40':'취소공고','PF50':'개찰'}"></fc:fwkComboBox>
							</td>
						</tr>
						<tr>
							<th scope="row"><label for="division">입찰구분</label></th>
							<td>
								<comTag:comCmcdCdValueComboBox id="P_PRCURE_SE_CD_S" name="P_PRCURE_SE_CD_S"   selectKey="${param.P_PRCURE_SE_CD_S}" cdId="C00001" headerKey="" headerValue="전체" />
							</td>
						</tr>
						<tr>
							<th scope="row"><label for="division">공고일자</label></th>
							<td> 
								<div class="calendar_box">
									<input type="text" id="schedule1" title="발주예정시기" class="w170 datepicker1" id="P_PBLANC_BEGIN_DT_S" name="P_PBLANC_BEGIN_DT_S" value="${param.P_PBLANC_BEGIN_DT_S}">
									<span class="wave">~</span>
									<input type="text" id="schedule2" title="발주예정시기" class="w170 datepicker2" id="P_PBLANC_END_DT_S" name="P_PBLANC_END_DT_S" value="${param.P_PBLANC_END_DT_S}">
								</div> <!--// calendar_box E -->
								
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
				<p class="total">총 <span>${comFn:nvl(myPartcptReqstSttusListTotcnt, 0)}</span>건</p>
				<div class="btn_right">
<!-- 						<button type="button" class="btn btn_02 btn_down">엑셀 다운로드</button> -->
				</div>
			</div> <!--// list_top E -->
			<div class="list_conts">
				<table class="tableList" summary="진행중인 입찰공고 목록입니다.">
               <caption>진행중인 입찰공고 목록</caption>
               <colgroup>
                  	  <col width="5%"/>
	                  <col width="12%"/>
	                  <col width="5%"/>
	                  <col width="*"/> 
	                  <col width="10%"/>
	                  <col width="10%"/>
	                  <col width="10%"/>
	                  <col width="12%"/>
	                  <col width="10%"/>
               </colgroup>			
			<thead class="line">
                <tr>
                    <th scope="col">번호</th> 
			    	<th class="noBg" scope="col">공고번호</th>
			    	<th scope="col">입찰구분</th>
			    	<th scope="col">입찰명</th>
			    	<th scope="col">공고일자</th>
			    	<th scope="col">신청마감일자</th>
			    	<th scope="col">계약방법</th> 
			    	<th scope="col">입찰한도액(원)</th>
			    	<th scope="col">진행상태</th>
                </tr> 
            </thead> 
			<tbody>    
				<c:if test="${comFn:nvl(myPartcptReqstSttusListTotcnt, 0) == 0}">
					<tr class="row">
						<td colspan="9">검색 조건에 해당하는 데이터가 존재하지 않습니다.</td>
					</tr>
				</c:if>
				<c:if test="${myPartcptReqstSttusListTotcnt > 0}">
					<c:forEach var="data" items="${myPartcptReqstSttusList}" varStatus="status" >
					<tr class="row" onclick="myPartcptReqstSttusDetailInqire('${data.PBLANC_NO}', '${data.PBLANC_ODR}','${data.ENTRPS_REGIST_NO }');" style="cursor: pointer;">
						<td >${data.RNUM}</td>
						<td >${data.PBLANC_NO}-${data.PBLANC_ODR}</td>
						<td >${data.PRCURE_SE_CD_NM}</td>
						<td class="txtl pl20" title="${data.BID_NM}"><c:if test="${data.EMRGNCY_BID_AT eq 'Y'}"><font color="red">[긴급]</font></c:if>${comFn:toShorten(data.BID_NM,30)}</td>
						<td >${comFn:formatDate(data.PBLANC_DT,'yyyyMMddHHmmss','yyyy-MM-dd')}</td>
						<td >${comFn:formatDate(data.PARE_END_DT,'yyyyMMddHHmmss','yyyy-MM-dd')}</td>
						<td >${data.CNTRCT_MTH_CD_NM}</td>
						<td class="txtr pr20">${comFn:formatMoney(data.BID_LMT_AMOUNT)}</td>
						<td ><div class="btn_before">${data.BID_PROGRS_STTUS_CD_NM }</div></td>
					</tr>
					</c:forEach>
				</c:if>          
			</tbody>
		</table>
			</div> <!--// list_conts E -->
			<div class="list_bottom">
				<div class="list_pager">
					<comTag:paging totalCount="${myPartcptReqstSttusListTotcnt}" pageNo="${comFn:nvl(param.P_PAGE_NO, 1)}" pageSize="${comFn:nvl(param.P_PAGE_SIZE, 10)}" clickPage="pageObj.clickPage"/>
				</div> <!--// list_pager E -->
				<div class="list_btn">
<!-- 					<button type="button" class="btn btn_m btn_orange" id="intrstBidRegistBtn">관심입찰등록</button> -->
				</div> <!--// btn_wrap E -->
			</div> <!--// list_bottom E -->
		</div> <!--// list_wrap E -->
  		</fieldset>		
</form>
 
<%--page move form --%>   
<form id="viewFrm" method="POST" action="${contextPath}/elbi/myPartcptReqstSttusDetail.do">
	<input type="hidden" name="resourceName" value="${param.resourceName}">
	<input type="hidden" name="P_PBLANC_NO" >
	<input type="hidden" name="P_PBLANC_ODR" >
	<input type="hidden" name="P_ENTRPS_REGIST_NO" >
</form>