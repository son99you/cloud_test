<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">--%>
<%--
 * 공통 > 평가대상 탭 - 평가대상정보 아임스타즈 연계 상세 팝업 : 상세내용
 *
 * <pre>
 * comm 
 *   |_ popup
 *     |_ estmObjImstarsDetailView.jsp
 * 
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %>

<script type="text/javascript" src="${jsPath}/ipro/views/comm/popup/estmObjImstarsDetailView.js"></script>

<div class="layout-pop">
	<form id="detailFrm" class="detailFrm" method="POST">
		<input type="hidden" name="resourceDesc" value="${ param.resourceDesc}">
		<input type="hidden" name="resourceName" value="${comFn:nvl(param.resourceName, resourceName)}">
		<input type="hidden" id="P_BSNM_REGIST_NO" name="P_BSNM_REGIST_NO" value="${comFn:nvl(goodsDetail.BSNM_REGIST_NO, param.P_BSNM_REGIST_NO)}">
		<input type="hidden" id="P_GOODSNO" name="P_GOODSNO" value="${comFn:nvl(goodsDetail.GOODSNO, param.P_GOODSNO)}">
		<input type="hidden" id="P_REQST_NO" name="P_REQST_NO" value="${param.P_REQST_NO }">
		
		<div class="pop_header">
			<div class="title">지원사업관리</div>
		</div> <!--// pop_header E -->
		
		<!-- tab -->
		<div class="component-tab-line">
			<ul class="list-tab">
				<li>
					<a href="javascript:" onclick="pageObj.objImstarsTabEvent(1);">기업정보</a>
				</li>
				<li class="is-selected">
					<a href="javascript:" onclick="pageObj.objImstarsTabEvent(2);">상품정보</a>
				</li>
				<li>
					<a href="javascript:" onclick="pageObj.objImstarsTabEvent(3);">첨부파일</a>
				</li>
			</ul>
		</div>
		<!-- //tab -->
		
		<br><br><br>
		
		<div>
			<table class="component-detail-table">
				<colgroup>
					<col width="15%">
					<col width="20%">
					<col width="15%">
					<col width="50%">
				</colgroup>
				<tbody>
					<tr>
						<th class="txt-center">구분</th>
						<th colspan="3" class="txt-center">주요내용</th>
					</tr>
					<tr>
						<th>상품분류</th>
						<td colspan="3">${goodsDetail.PATHNAME }</td>
					</tr>
					<tr>
						<th rowspan="2">상품현황</th>
						<th>상품명</th>
						<td colspan="2">${goodsDetail.GOODSNM }</td>
					</tr>
					<tr>
						<th>상품설명</th>
						<td colspan="2">
							<textarea taView style="display: none;">${goodsDetail.GOODSDC }</textarea>
						</td>
					</tr>
					<tr>
						<th>상품특징</th>
						<td colspan="3">
							<textarea taView style="display: none;">${goodsDetail.GOODSSFE }</textarea>
						</td>
					</tr>
					<tr>
						<th>소비자가격</th>
						<td>
							<c:if test="${not empty goodsDetail.CNSMRPC}">
								${comFn:formatMoney(goodsDetail.CNSMRPC)} 원
							</c:if>
						</td>
						<th>공장도가격</th>
						<td>
							<c:if test="${not empty goodsDetail.FCTRYDOPC}">
								${comFn:formatMoney(goodsDetail.FCTRYDOPC)} 원
							</c:if>
						</td>
					</tr>
					<tr>
						<th>브랜드명</th>
						<td>${goodsDetail.BRANDNM }</td>
						<th>모델명</th>
						<td>${goodsDetail.MODLNM }</td>
					</tr>
					<tr>
						<th>상품규격</th>
						<td>${goodsDetail.STNDRD }</td>
						<th>출시일자</th>
						<td>${comFn:formatDate(goodsDetail.MNFCTURDE,'yyyyMM','yyyy-MM')}</td>
					</tr>
					<tr>
						<th>유통기한/품질보증기간</th>
						<td>${goodsDetail.DISTBGRNTYMONTHCO}</td>
						<th>원산지(국내, 국외)</th>
						<td>${goodsDetail.ORGPLCECODE_NM }</td>
					</tr>
					
					<tr>
						<th>상품-대표 이미지</th>
						<td colspan="3">
							<c:if test="${not empty goodsDetail.REPRSNTIMAGEFILEID}">
								<a href="javascript:imstarsDetailDownload('${goodsDetail.REPRSNTIMAGEFILEID}');">
									<img border="0" src ="/comm/imstarsDetailDownload.do?P_FILE_ID=${goodsDetail.REPRSNTIMAGEFILEID}" alt="${goodsDetail.REPRSNTIMAGEFILEID_FILE_NM }">
								</a>
							</c:if>
						</td>
					</tr>
					<tr>
						<th>상품-서브 이미지</th>
						<td colspan="3">
							<c:if test="${not empty fileDetailList}">
								<c:forEach items="${fileDetailList}" var="data" varStatus="status">
									<a href="javascript:imstarsDetailDownload('${data.FILE_ID}', '${data.FILE_SN }');">
										<img border="0" src ="/comm/imstarsDetailDownload.do?P_FILE_ID=${data.FILE_ID}&P_FILE_SN=${data.FILE_SN}" alt="${data.WON_FILE_NM }">
									</a>
			                   	</c:forEach>
							</c:if>
						</td>
					</tr>
					<tr>
						<th>상품-상세 파일</th>
						<td colspan="3">
							<a href="javascript:imstarsDetailDownload('${goodsDetail.DETAILFILEID}');">${goodsDetail.DETAILFILEID_FILE_NM }</a>
						</td>
					</tr>
					
					<tr>
						<th>인증현황</th>
						<td colspan="3">
							<br>
							<c:if test="${not empty goodsCrtfcList }">
								<table class="component-detail-table"> 
									<colgroup>
										<col width="6%">
										<col width="11%">
										<col width="12%">
										<col width="15%">
										<col width="15%">
										<col width="*%">
										<col width="11%">
									</colgroup>
									<thead>
										<tr>
											<th>번호</th>
											<th>취득일</th>
											<th>발급기관</th>
											<th>인증명칭</th>
											<th>인증서 번호</th>
											<th>인증내용</th>
											<th>인증서</th>
										</tr>
									</thead>
									<tbody>
										<c:if test="${empty goodsCrtfcList}">
											<tr>
												<td colspan="7" class="txt-center">검색 조건에 해당하는 데이터가 존재하지 않습니다.</td>
											</tr>
										</c:if>
					                	<c:if test="${not empty goodsCrtfcList }">
					                  		<c:forEach items="${goodsCrtfcList}" var="data" varStatus="status">
					                  			<tr>
					                  				<td class="txt-center">${data.CRTFC_NO }</td>
					                  				<td class="txt-center">${comFn:formatDate(data.ACQS_DE, 'yyyyMMdd', 'yyyy-MM-dd')}</td>
					                  				<td>${data.ISSU_ENGN }</td>
					                  				<td>${data.CRTFC_NM }</td>
					                  				<td>${data.AHRZT_NO }</td>
					                  				<td>${data.CRTFC_CN }</td>
					                  				<td class="txt-center">
					                  					<c:if test="${not empty data.AHRZT_FILE_ID}">
															<a href="javascript:imstarsDetailDownload('${data.AHRZT_FILE_ID}', '1');">
																<img src="${imagePath}/comm/sub/icon_file.png" alt="인증서">
															</a>
														</c:if>
					                  				</td>
					                  			</tr>
						                   	</c:forEach>
					                   	</c:if>
									</tbody>
								</table> 
							</c:if>
							<br>
						</td>
					</tr>
					<tr>
						<th>상품매출액</th>
						<td colspan="3">
							<br>
							<c:if test="${not empty goodsSelngList }">
								<table class="component-detail-table">
									<tr>
										<c:forEach items="${goodsSelngList}" var="data" varStatus="status">
											<th class="txt-center">${data.SELNG_YY }</th>
										</c:forEach>
									</tr>
									<tr>
										<c:forEach items="${goodsSelngList}" var="data" varStatus="status">
											<td class="txt-center">${comFn:formatMoney(data.SELNG_AM)}</td>
										</c:forEach>
									</tr>
								</table>
							</c:if>
							<br>
						</td>
					</tr>
					
					<tr style="display: none;">
						<th>현 유통현황</th>
						<td colspan="3">???</td>
					</tr>
				</tbody>
			</table>
		</div>
		
		<div class="bottom-buttons">
			<a href="javascript:" class="btn-bottom type-a" onclick="window.close();">닫기</a>
		</div>
	</form>
</div> 


<!-- DOWNLOAD FORM -->
<form id="downloadFrm" method="POST">
	<input type="hidden" name="P_FILE_ID">
	<input type="hidden" name="P_FILE_SN">
</form>