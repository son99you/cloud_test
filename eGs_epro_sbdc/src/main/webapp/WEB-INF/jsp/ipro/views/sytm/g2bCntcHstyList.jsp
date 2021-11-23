<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * Admin > 시스템현황 > 나라장터연계이력 목록
 *
 * <pre>
 * sytm
 *    |_ g2bCntcHstyList.jsp
 * 
 * </pre>
 * @date : 2019. 04. 22
 * @version : 1.0
 * @author :  은우소프트 은잔디
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %>

<script type="text/javascript" src="${jsPath}/ipro/views/sytm/g2bCntcHstyList.js"></script>

<div class="contents_wrap">
	<ul class="step_wrap">
		<li><a href="#">${myMenuList.bigMenuNm}</a></li>
		<li><a href="#">${myMenuSubList.smallMenuNm}</a></li>
	</ul>
	<div class="tit_wrap">
		<h3 class="tit">나라장터연계이력</h3>
	</div>
		
	<form id="listFrm" class="listFrm" method="POST" action="${contextPath}/ordr/ordrList.do">
		<input type="hidden" id="resourceName" name="resourceName" value="${param.resourceName}">
		<input type="hidden" id="resourceDesc" name="resourceDesc" value="${param.resourceDesc}">
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
						<tr>
							<th>제목</th>
							<td><input type="text" id="P_TTL_S" name="P_TTL_S" value="${param.P_TTL_S }"></td>
							<th>연계구분</th>
							<td>
								<comTag:comCmcdCdValueComboBox name="P_CNTC_SECD_S" cdId="CNTC_SECD" selectKey="${param.P_CNTC_SECD_S }" headerValue="전체" width="180"/>
							</td>
						</tr>
						<tr>
							<th>연계일시</th>
							<td>
								<div class="calendar_box">
									<label for="" class="blind">연계일시 시작일</label>
								 	<input type="text" class="datepicker1" id="P_CNTC_BEGIN_DT_S" name="P_CNTC_BEGIN_DT_S" value="${param.P_CNTC_BEGIN_DT_S}" date >
									<span class="wave"> ~ </span>
								    <label for="" class="blind">연계일시 마감일</label>
								    <input type="text" class="datepicker2" id="P_CNTC_END_DT_S" name="P_CNTC_END_DT_S" value="${param.P_CNTC_END_DT_S}" date >
								</div>
							</td>
							<th>송수신구분</th>
							<td>
								<comTag:comCmcdCdValueComboBox name="P_SND_RCV_SECD_S" cdId="SND_RCV_SECD" selectKey="${param.P_SND_RCV_SECD_S }" headerValue="전체" width="180"/>
							</td>
						</tr>
					</table>				
				</div>
			</div>
			
			<div class="btn_wrap mt10">
				<button type="button" class="btn btn_m btn_c2" id="searchBtn">조회</button>
			</div>
			
			<div class="list_wrap mt30" id="contentWrap">
				<div class="list_top">
					<p class="total">총 <span>${comFn:nvl(g2bCntcHstyListTotCnt, 0)}</span>건</p>		
				</div> <!--// list_top E -->
				<div class="list_conts">		
					<table id="list_tbl">
						<caption>나라장터연계이력 목록</caption>
		              	<colgroup>
		                   	<col width="10%"/>
		                   	<col width="15%"/>
		                   	<col width="*"/>
		                   	<col width="15%"/>
		                   	<col width="8%"/>
		            	</colgroup>
		            	<thead>
					    	<tr>
					        	<th>송수신구분</th>
					        	<th>연계구분</th>
					        	<th>제목</th>
					        	<th>연계일시</th>
					        	<th>내용</th>
					        </tr>
		            	</thead>
		            	<tbody>
							<c:if test="${comFn:nvl(g2bCntcHstyListTotCnt, 0) == 0}">
								<tr>
									<td colspan="5" class="txt-center">검색 조건에 해당하는 데이터가 존재하지 않습니다.</td>
								</tr>
							</c:if>
							<c:if test="${g2bCntcHstyListTotCnt > 0}">
								<c:forEach var="data" items="${g2bCntcHstyList}" varStatus="status" >
									<tr>
										<td>${data.SND_RCV_SECD_NM}</td>
										<td>${data.CNTC_SECD_NM}</td>
										<td class="pl5 txtl">${data.TTL}</td>
										<td>${comFn:formatDate(data.CNTC_DT,'yyyyMMddHHmmss','yyyy-MM-dd HH:mm')}</td>
										<td>
											<button type="button" class="btn btn_s btn_sch" onclick="detailInqire('${data.CNTC_SN}')">상세</button>
										</td>
									</tr>
								</c:forEach>
							</c:if>
						</tbody>
				    </table>
				</div>
				<div class="list_bottom">
					<comTag:pagingIpro totalCount="${g2bCntcHstyListTotCnt}" pageNo="${comFn:nvl(param.P_PAGE_NO, 1)}" pageSize="${comFn:nvl(param.P_PAGE_SIZE, 10)}" clickPage="pageObj.clickPage"/>
				</div> <!--// list_bottom E -->			
		    </div>				
			
		</fieldset>
	</form>
</div>

<%-- POPUP FORM --%>
<form id="popupFrm" method="POST" > 
	<input type="hidden" name="resourceName" value="${ param.resourceName }">
	<input type="hidden" name="P_CNTC_SN">
</form>