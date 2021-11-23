<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 협력사현황(신분당선 업체관리)
 *
 * <pre>
 * vend 
 *    |_ vendMngeList.jsp
 * 
 * </pre>
 * @date : 2017. 06. 13
 * @version : 1.0
 * @author : 은우소프트 홍찬일
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %> 
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %> 

<script type="text/javascript" src="${jsPath}/ipro/views/vend/vendMngeList.js"></script>

<div class="conts_wrap">
	<div class="inner">
		<div class="tit_wrap">
			<h3 class="tit">협력사현황</h3>
			<ul class="step_wrap">
				<li><a href="#">${bigMenuNm}</a></li>
				<li><a href="#">${smallMenuNm}</a></li>
			</ul>			
		</div>
		
		<form id="listFrm" name="listFrm" class="search_form" method="POST">
			<input type="hidden" id="resourceName" name="resourceName" value="${param.resourceName}">
			<input type="hidden" id="resourceDesc" name="resourceDesc" value="${param.resourceDesc}">
			
			<input type="hidden" id="P_PAGE_NO" name="P_PAGE_NO" value="${comFn:nvl(param.P_PAGE_NO, 1)}">
			<input type="hidden" id="P_PAGE_SIZE" name="P_PAGE_SIZE" value="${comFn:nvl(param.P_PAGE_SIZE, 10)}">
			
			<input type="hidden" id="CCN_SPHE_1" value="${param.P_CCN_SPHE_1_S}">
			<input type="hidden" id="CCN_SPHE_2" value="${param.P_CCN_SPHE_2_S}">
			<input type="hidden" id="CCN_SPHE_3" value="${param.P_CCN_SPHE_3_S}">
			<input type="hidden" id="CCN_SPHE_4" value="${param.P_CCN_SPHE_4_S}">
			<input type="hidden" id="CCN_SPHE_5" value="${param.P_CCN_SPHE_5_S}">
			
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
							
							<tr height="24">
								<th>업체명</th>
								<td>
									<input type="text" id="P_VEND_NM_S" name="P_VEND_NM_S" value="${param.P_VEND_NM_S}">
								</td>
								<th>사업자등록번호</th>
								<td>
									<input type="text" class="w280" id="P_BIZRNO_S" name="P_BIZRNO_S" value="${param.P_BIZRNO_S}" maxlength="10" numeric> ('-'제외)
								</td>
							</tr>
							<tr height="24">
								<th>가입일자</th>
								<td>
									<div class="calendar_box">
										<label for="" class="blind">가입일자 시작일</label>
										<input type="text" class="w120 datepicker1" id="P_REG_BEGIN_DT_S" name="P_REG_BEGIN_DT_S" value="${param.P_REG_BEGIN_DT_S}" date >
										<span class="wave"> ~ </span>
									    <label for="" class="blind">가입일자 마감일</label>
									    <input type="text" class="w120 datepicker2" id="P_REG_END_DT_S" name="P_REG_END_DT_S" value="${param.P_REG_END_DT_S}" date >
									</div>
								</td>
								<th>관심분야</th>
								<td>
									<input type="hidden" name="P_CCN_SPHE_1_S" value="${param.P_CCN_SPHE_1_S}">
									<input type="checkbox" id="P_CCN_SPHE_1_S" class="checkbox" onclick="ccnSpheEvent(this, 'P_CCN_SPHE_1_S');"> 공사&nbsp;&nbsp;
									
									<input type="hidden" name="P_CCN_SPHE_2_S" value="${param.P_CCN_SPHE_2_S}">
									<input type="checkbox" id="P_CCN_SPHE_2_S" class="checkbox" onclick="ccnSpheEvent(this, 'P_CCN_SPHE_2_S');"> 용역&nbsp;&nbsp;
									
									<input type="hidden" name="P_CCN_SPHE_3_S" value="${param.P_CCN_SPHE_3_S}">
									<input type="checkbox" id="P_CCN_SPHE_3_S" class="checkbox" onclick="ccnSpheEvent(this, 'P_CCN_SPHE_3_S');"> 물품&nbsp;&nbsp;
									
									<input type="hidden" name="P_CCN_SPHE_4_S" value="${param.P_CCN_SPHE_4_S}">
									<input type="checkbox" id="P_CCN_SPHE_4_S" class="checkbox" onclick="ccnSpheEvent(this, 'P_CCN_SPHE_4_S');"> 임대차&nbsp;&nbsp;
									
									<input type="hidden" name="P_CCN_SPHE_5_S" value="${param.P_CCN_SPHE_5_S}">
									<input type="checkbox" id="P_CCN_SPHE_5_S" class="checkbox" onclick="ccnSpheEvent(this, 'P_CCN_SPHE_5_S');"> 매각
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
						<p class="total">총 <span>${comFn:nvl(vendMngeListTotcnt, 0)}</span>건</p>
						<!--  <div class="btn_right"> -->
							<!-- <button type="button" class="btn btn_02 btn_down">엑셀 다운로드</button> -->
						<!-- </div> -->
					</div> <!--// list_top E -->
					<div class="list_conts">			
						<table>
			           		<colgroup>
								<col width="*">  
								<col width="10%">
								<col width="10%" >
								<col width="8%" >
								<col width="8%" >
								<col width="8%" >
							</colgroup>
							<thead>
				                <tr>
									<th scope="col">업체명</th>
									<th scope="col">사업자등록번호</th>
									<th scope="col">대표이사</th>
									<th scope="col">가입일자</th>
									<th scope="col">인증서</th>
									<th scope="col">인증서만료일</th>
				                </tr>
				            </thead>
							<tbody>
								<c:if test="${comFn:nvl(vendMngeListTotcnt, 0) == 0}">
									<tr>
										<td colspan="6" class="txt-center">검색 조건에 해당하는 데이터가 존재하지 않습니다.</td>
									</tr>
								</c:if>
								<c:if test="${vendMngeListTotcnt > 0}">
									
									<c:set var="tdStyle" value="alterstyle" scope="request"></c:set>
									<c:set var="totCnt" value="0" scope="request"></c:set>
									
									<c:forEach var="data" items="${vendMngeList}" varStatus="status" >
										<c:if test="${ status.count == 1 }">
											<c:set var="totCnt" value="${ vendMngeListTotcnt }" scope="request"></c:set>
										</c:if>
										<c:if test="${ status.index % 2 == 0 }">
											<c:set var="tdStyle" value="td01" scope="request"/>
										</c:if>
										<c:if test="${ status.index % 2 != 0 }">
											<c:set var="tdStyle" value="" scope="request"/>
										</c:if>
										
										<tr class="pointer" onclick="vendMngeDetail('${data.VEND_REG_NO}')">
											<td class="pl5 list_tit">${data.VEND_NM}</td>
											<td>${comFn:formatBizNumber(data.BIZRNO) }</td>
											<td class="txtl pl5">${data.RPRS_NM}</td>
											<td>${comFn:formatDate(data.REG_DT, 'yyyyMMddhhmmss', 'yyyy-MM-dd')}</td>
											<td>
												<c:if test="${data.CRTFC_AT eq 'Y'}">제출</c:if>
												<c:if test="${data.CRTFC_AT eq 'N'}">미제출</c:if>
											</td>
											<td>${comFn:formatDate(data.VALID_END_DE, 'yyyyMMdd', 'yyyy-MM-dd')}</td>
										</tr>
									</c:forEach>
								</c:if>
							</tbody>
						</table>
					</div>
					<div class="list_bottom">
						<comTag:pagingIpro totalCount="${vendMngeListTotcnt}" pageNo="${comFn:nvl(param.P_PAGE_NO, 1)}" pageSize="${comFn:nvl(param.P_PAGE_SIZE, 10)}" clickPage="pageObj.clickPage"/>
					</div> <!--// list_bottom E -->				
				</div>				
				
			</fieldset>
		</form>
	</div>
</div> 

<%-- DETAIL FORM --%>
<form id="detailFrm" method="POST" > 
	<input type="hidden" name="resourceName" value="${ param.resourceName }">
	<input type="hidden" name="P_VEND_REG_NO">
</form>