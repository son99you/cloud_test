<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 결재현황
 *
 * <pre>
 * info 
 *    |_ infoAppResultList.jsp
 * 
 * </pre>
 * @date : 2017. 06. 16
 * @version : 1.0
 * @author : 은우소프트 홍찬일
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %> 
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %> 

<script type="text/javascript" src="${jsPath}/ipro/views/info/infoAppResultList.js"></script>

 <div class="conts_wrap">
	<div class="inner">
		<div class="tit_wrap">
			<h3 class="tit">결재완료</h3>
			<ul class="step_wrap">
				<li><a href="#">${comFn:fmIso2Euc(myMenuList.bigMenuNm)}</a></li>
				<li><a href="#">${comFn:fmIso2Euc(myMenuSubList.smallMenuNm)}</a></li>
			</ul>			
		</div>
		<form id="searchFrm" class="search_form" method="POST" action="${contextPath}/info/infoAppResultList.do">
			<input type="hidden" name="resourceName" value="${ param.resourceName }" >
			
			<fieldset>
				<div class="view_wrap typeA">
					<div class="view_area">
						<table>
							<colgroup>												
								<col width="15%" align="left">
								<col width="35%" align="left">
								<col width="15%" align="left">
								<col width="35%"   align="left">
							</colgroup>
							<tr height="24">
								
								<th>
									결재명
								</th>
								<td>
									<input type="text" id="P_APRDC_INTL_NM" name="P_APRDC_INTL_NM" value="${param.P_APRDC_INTL_NM}" maxlength="50">
								</td>
								<th>
									결재상태
								</th>
								<td  >
									<select><option>전체</option><option>결재완료</option><option>반려</option></select>
								</td>					
							</tr>
							<tr height="24">
								<th>
									결재요청일
								</th>
								<td  >
									<div class="calendar_box">
					                    <label for="P_ANNC_STDT_S" class="blind">결재요청 시작일</label>
					                    <input type="text" class="w100 datepicker1" id="P_ANNC_STDT_S" name="P_ANNC_STDT_S" value="${param.P_ANNC_STDT_S}" date >
					                	<span class="wave"> ~ </span>
					                    <label for="P_ANNC_ENDT_S" class="blind">결재요청 마감일</label>
					                    <input type="text" class="w100 datepicker2" id="P_ANNC_ENDT_S" name="P_ANNC_ENDT_S" value="${param.P_ANNC_ENDT_S}"  date >
					                </div>
								</td>
								<th>
									구분
								</th>
								<td>
									<select><option>전체</option><option>요청</option><option>결재</option></select>
								</td>
							</tr>
						</table>				
					</div>
				</div>
				
				<div class="btn_wrap mt10">
					<button type="button" class="btn btn_m btn_sch" id="searchBtn">조회</button>
				</div> <!--// btn_wrap E -->
				
				<div class="list_wrap mt30 contentWrap">
					<div class="list_top">
						<p class="total">총 <span>${comFn:nvl(tApprMstListTotCnt, 0)}</span>건</p>		
						<!--  <div class="btn_right"> -->
							<!-- <button type="button" class="btn btn_02 btn_down">엑셀 다운로드</button> -->
						<!-- </div> -->
					</div> <!--// list_top E -->
					<div class="list_conts">		
						<table>
			           		<colgroup>
								<col width="15%">
								
								<col width="*">
								<col width="15%">
								<col width="15%">
								<col width="10%" >
								<col width="15%" >
							</colgroup>
							<thead>
				                <tr>
			                    	<th>결재번호</th>
			                    	<th>결재명</th>
<!-- 			                    	<th>결재문서구분</th> -->
									<th>결재요청일</th>
									<th>요청자</th>
									<th>구분</th>
									<th>결재상태</th>
				                </tr>
				            </thead>
							<tbody>
								<tr class="pointer" onclick="infoApprlineDetail('${data.APRDC_NO}', '${data.APRDC_SE}');">
									<td>E2018-0001</td>
									<td>입찰공고 결재요청 합니다.</td>
									<td>2018-11-06</td>
									<td>사용자1</td>
									<td>요청</td>
									<td>결재완료</td>
								</tr>
								<tr>
									<td>C2018-0001</td>
									<td>계약서서식 결재요청 합니다.</td>
									<td>2018-11-06</td>
									<td>사용자2</td>
									<td>결재</td>
									<td>반려</td>
								</tr>
								<%-- <c:if test="${comFn:nvl(tApprMstListTotCnt, 0) == 0}">
									<tr>
										<td class="txt-center"colspan="8">검색 조건에 해당하는 데이터가 존재하지 않습니다.</td>
									</tr>
								</c:if>
								<c:if test="${tApprMstListTotCnt > 0}">
								<c:forEach var="data" items="${tApprMstList}" varStatus="status" >					
								 <tr class="pointer" onclick="infoApprlineDetail('${data.APRDC_NO}', '${data.APRDC_SE}');">
									<td class="txtc">${data.APRDC_NO}</td>
									<td class="txtc">${data.APRDC_INTL_NO}</td>
									<td class="txtc">${data.APRDC_SE}</td>
									<td class="pl5 list_tit">${data.APRDC_INTL_NM}</td>
									<td class="pl5 txtl">${data.APPL_NM}</td>
									<td class="txtc">${data.REG_DT}</td>
									<td class="txtc">${data.REGR_NM}</td>
									<td class="txtc">
										<c:if test="${data.LT_APPR_STAT eq 'A'}">작성</c:if>
										<c:if test="${data.LT_APPR_STAT eq 'B'}">승인</c:if>
										<c:if test="${data.LT_APPR_STAT eq 'C'}">반려</c:if>
									</td>
								</tr>
								</c:forEach>
								</c:if> --%>
							</tbody>
						</table>
					</div>
					<div class="list_bottom">
						<comTag:pagingIpro totalCount="${tApprMstListTotCnt}" pageNo="${comFn:nvl(param.P_PAGE_NO, 1)}" pageSize="${comFn:nvl(param.P_PAGE_SIZE, 10)}" clickPage="pageObj.clickPage"/>
					</div> <!--// list_bottom E -->				
				</div>
				
			</fieldset>
		</form>
	</div>
</div>
 
<%-- DETAIL FORM --%>
<form id="detailFrm" method="POST" > 
	<input type="hidden" id="P_APRDC_NO_S" name="P_APRDC_NO_S" value="" >
	<input type="hidden" id="P_APRDC_SE_S" name="P_APRDC_SE_S" value="" >
	<input type="hidden" name="resourceName" value="${ param.resourceName }" >
</form>
<form id="popupFrm" method="POST" > 
	<input type="hidden" name="resourceName" value="${ param.resourceName }" >
</form>