<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 마이페이지 > 부정당업체등록정보 목록
 *
 * <pre>
 * user 
 *    |_ snctVendRegInfoList.jsp
 * 
 * </pre>
 * @date : 2019. 03. 08
 * @version : 1.0
 * @author : 은우소프트 은잔디
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %> 
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %> 

<script type="text/javascript" src="${jsPath}/opro/views/user/snctVendRegInfoList.js"></script>

<div class="contents_wrap">
	<ul class="step_wrap">
		<li><a href="#">${myMenuList.bigMenuNm}</a></li>
		<li><a href="#">${myMenuSubList.smallMenuNm}</a></li>
	</ul>
	<div class="tit_wrap">
		<h3 class="tit">부정당업체등록정보</h3>
	</div>

	<form id="searchFrm" name="searchFrm" class="search_form" method="POST">
		<input type="hidden" id="resourceName" name="resourceName" value="${param.resourceName}">
		<input type="hidden" id="resourceDesc" name="resourceDesc" value="${param.resourceDesc}">
		
		<input type="hidden" id="P_PAGE_NO" name="P_PAGE_NO" value="${comFn:nvl(param.P_PAGE_NO, 1)}">
		<input type="hidden" id="P_PAGE_SIZE" name="P_PAGE_SIZE" value="${comFn:nvl(param.P_PAGE_SIZE, 10)}">
		
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
							<th>발생일자</th>
							<td>
								<div class="calendar_box">
						            <label for="P_GNRT_BEGIN_DE_S" class="blind">발생 시작일자</label>
						            <input type="text" class="w120 datepicker1" id="P_GNRT_BEGIN_DE_S" name="P_GNRT_BEGIN_DE_S" maxlength="10" value="${param.P_GNRT_BEGIN_DE_S }" date>
				                	<span class="wave"> &nbsp;~ &nbsp;</span>
					            	<label for="P_GNRT_END_DE_S" class="blind">발생 종료일자</label>
					            	<input type="text" class="w120 datepicker2" id="P_GNRT_END_DE_S"  name="P_GNRT_END_DE_S" maxlength="10" value="${param.P_GNRT_END_DE_S }" date>
					            </div>
							</td>
							<th>제재구분</th>
							<td>
								<comTag:comCmcdCdValueComboBox name="P_SNCT_STCD_S" cdId="SNCT_STCD" selectKey="${param.P_SNCT_STCD_S }" headerValue="전체" width="336"/>			
							</td>
						</tr>
					</table>
				</div>
			</div>
			
			<div class="btn_wrap mt10">
				<button type="button" class="btn btn_m btn_c2" id="searchBtn">조회</button>
			</div> <!--// btn_wrap E -->
			
			<div class="list_wrap mt30 contentWrap">
				<div class="list_top">
					<p class="total">총 <span>${comFn:nvl(snctVendRegInfoListTotcnt, 0)}</span>건</p>
				</div> <!--// list_top E -->
				<div class="list_conts">			
					<table>
		           		<colgroup>
							<col width="4%">
							<col width="10%">
							<col width="10%">
							<col width="10%">
							<col width="10%">
							<col width="*" >
							<col width="8%">
							<col width="8%">
						</colgroup>
						<thead>
			                <tr>
								<th scope="col">No</th>
								<th scope="col">발생일자</th>
								<th scope="col">제재구분</th>
								<th scope="col">시작일자</th>
								<th scope="col">종료일자</th>
								<th scope="col">제재사유</th>
								<th scope="col">처리자</th>
								<th scope="col">상세</th>
			                </tr>
			            </thead>
						<tbody>
							<c:if test="${comFn:nvl(snctVendRegInfoListTotcnt, 0) == 0}">
								<tr>
									<td class="txtc" colspan="8">검색 조건에 해당하는 데이터가 존재하지 않습니다.</td>
								</tr>
							</c:if>
					        <c:if test="${comFn:nvl(snctVendRegInfoListTotcnt, 0) > 0}">
						        <c:forEach var="data" items="${snctVendRegInfoList}" varStatus="status">
						        	<tr class="row">
						        		<td>${data.RNUM}</td>
						        		<td class="txtc">${comFn:formatDate(data.GNRT_DE,'yyyyMMdd','yyyy-MM-dd')}</td>
						        		<td>${data.SNCT_STCD_NM}</td>
						        		<td class="txtc">${comFn:formatDate(data.SNCT_STDE,'yyyyMMdd','yyyy-MM-dd')}</td>
						        		<td class="txtc">${comFn:formatDate(data.SNCT_ENDE,'yyyyMMdd','yyyy-MM-dd')}</td>
						        		<td class="pl5 list_tit">${data.SNCT_RSN_CNTN}</td>
						        		<td>${data.PROCR_NM}</td>
						        		<td>
						        			<button type="button" class="btn btn_s btn_sch" onclick="detailInqire('${data.VEND_REG_NO}', '${data.SNCT_SN }')">상세</button>
						        		</td>
						        	</tr>
						        </c:forEach>
					        </c:if>
						</tbody>
					</table>
				</div>
				<div class="list_bottom">
					<comTag:pagingIpro totalCount="${snctVendRegInfoListTotcnt}" pageNo="${comFn:nvl(param.P_PAGE_NO, 1)}" pageSize="${comFn:nvl(param.P_PAGE_SIZE, 10)}" clickPage="pageObj.clickPage"/>
				</div> <!--// list_bottom E -->				
			</div>				
			
		</fieldset>
	</form>
</div>

<%-- POPUP FORM --%>
<form id="popupFrm" method="POST" > 
	<input type="hidden" name="resourceName" value="${ param.resourceName }">
	<input type="hidden" name="P_VEND_REG_NO">
	<input type="hidden" name="P_SNCT_SN">
</form>

<form id="listFrm" method="POST" > 
	<input type="hidden" name="resourceName" value="${ param.resourceName }">
	<input type="hidden" id="resourceDesc" name="resourceDesc" value="${param.resourceDesc}">
</form>