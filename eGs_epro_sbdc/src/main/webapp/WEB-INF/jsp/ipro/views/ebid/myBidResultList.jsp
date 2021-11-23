<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 입찰관리 > 나의 입찰결과 목록
 *
 * <pre>
 *ebid
 *    |_ myBidResultList.jsp
 * 
 * </pre>
 * @date : 2017.06.22
 * @version : 1.0
 * @author : 은우소프트 이주연 
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %>

<script type="text/javascript" src="${jsPath}/opro/views/ebid/myBidResultList.js"></script>  

<div class="contents_wrap">
	<ul class="step_wrap">
		<li><a href="#">${myMenuList.bigMenuNm}</a></li>
		<li><a href="#">${myMenuSubList.smallMenuNm}</a></li>
	</ul>
	<div class="tit_wrap">
		<h3 class="tit">나의입찰결과</h3>
	</div>

	<form id="searchFrm" class="searchFrm" method="POST" action="${contextPath}/elbi/myBidPblancList.do">
		<input type="hidden" name="resourceName" value="${param.resourceName}">
		<input type="hidden" id="P_PAGE_NO" name="P_PAGE_NO" value="${comFn:nvl(param.P_PAGE_NO, 1)}">
		<input type="hidden" id="P_PAGE_SIZE" name="P_PAGE_SIZE" value="${comFn:nvl(param.P_PAGE_SIZE, 10)}">
		
		<fieldset>
			<div class="view_wrap typeA">	
	        	<div class="view_area">
					<table>
						<caption>입찰공고조회</caption>    
						<colgroup>
							<col style="width: 15%;">
							<col style="width: auto;">
						</colgroup>
						<tbody>
							<tr>
								<th scope="row">입찰명</th>
								<td>
	               					<input type="text" class="" id="P_BID_NM_S" name="P_BID_NM_S" value="${param.P_BID_NM_S}" maxlength="600">
								</td>
							</tr>
							<tr>
								<th scope="row"><label for="division">개찰일자</label></th>
								<td>
									<div class="calendar_box">
		              					<input type="text" class="datepicker1" id="P_OPENG_BEGIN_DE_S" name="P_OPENG_BEGIN_DE_S" value="${param.P_OPENG_BEGIN_DE_S}">
										<span class="wave">~</span>
		              					<input type="text" class="datepicker2" id="P_OPENG_END_DE_S" name="P_OPENG_END_DE_S"  value="${param.P_OPENG_END_DE_S}">
									</div> <!--// calendar_box E -->
								</td>
							</tr>
							<tr>
								<th scope="row">입찰구분</th>
								<td>
					            	<comTag:comCmcdCdValueComboBox id="P_PRCURE_SE_CD_S" name="P_PRCURE_SE_CD_S"   selectKey="${param.P_PRCURE_SE_CD_S}" cdId="C00001" headerKey="" headerValue="전체" />
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
					<p class="total">총 <span>${ myBidResultListTotcnt}</span>건</p>
				</div> <!--// list_top E -->
				<div class="list_conts">
					<table class="tableList" summary="나의 입찰공고 목록입니다.">
		               <caption>나의 입찰공고 목록</caption>
		               <colgroup>
			               <col width="40px"/>
			               <col width="100px"/>
			               <col width="250px"/>
			               <col width="70px"/>
			               <col width="100px"/>
			               <col width="90px"/>
			               <col width="90px"/>
			               <col width="70px"/>
				        </colgroup>			
						<thead>
			               <tr>
			                   <th class="noBg">번호</th>
			                   <th>공고번호</th>
			                   <th>입찰명</th>
			                   <th>낙찰여부</th>
			                   <th>낙찰방법</th>
			                   <th>공고일자</th>
			                   <th>개찰일자</th>
			                   <th>진행상태</th>
			               </tr>
		           		</thead> 
						<tbody>
							<c:if test="${comFn:nvl(myBidResultListTotcnt, 0) == 0}">
								<tr class="row">
									<td colspan="8">검색 조건에 해당하는 데이터가 존재하지 않습니다.</td>
								</tr>
							</c:if>
							<c:if test="${myBidResultListTotcnt > 0}">
								<c:forEach var="data" items="${myBidResultList}" varStatus="status" >
								<tr class="row"
										onclick="myBidResultDetail('${data.PBLANC_NO}','${data.PBLANC_ODR }','${data.ENTRPS_REGIST_NO }');"
									  style="cursor: pointer;">
									<td>${data.RNUM}&nbsp;</td>
									<td>${data.PBLANC_NO}-${data.PBLANC_ODR}</td>
									<td class="txtl pl20" title="${data.BID_NM}">${data.BID_NM}&nbsp;</td>
									<td>${data.BID_STTUS }</td>
									<td>${data.SCSBID_MTH_NM}</td>
									<td>${comFn:formatDate(data.PBLANC_DT,'yyyyMMddHHmmss','yyyy-MM-dd')}&nbsp;</td>
									<td>${comFn:formatDate(data.OPENG_DT,'yyyyMMddHHmmss','yyyy-MM-dd')}&nbsp;</td>
									<td><div class="btn_before">${data.BID_PROGRS_STTUS_NM }</div></td>
								</tr>
								</c:forEach>
							</c:if>
						</tbody>
					</table>
				</div> <!--// list_conts E -->
				
				<div class="list_bottom">
					<div class="list_pager">
						<comTag:paging totalCount="${estiMngeRqstListTotcnt}" pageNo="${comFn:nvl(param.P_PAGE_NO, 1)}" pageSize="${comFn:nvl(param.P_PAGE_SIZE, 10)}" clickPage="pageObj.clickPage"/>
					</div> <!--// list_pager E -->
				</div> <!--// list_bottom E -->
			</div> <!--// list_wrap E -->
		</fieldset>	
	</form>
</div>
	
<form id="viewFrm" method="POST" >
	<input type="hidden" name="P_PBLANC_NO" />
	<input type="hidden" name="P_PBLANC_ODR"/>
	<input type="hidden" name="P_REPRSNT_ENTRPS_REGIST_NO"/>
	<input type="hidden" name="resourceName" value="${param.resourceName}">
</form>
<form id="popupFrm" method="POST" action="">
</form>


