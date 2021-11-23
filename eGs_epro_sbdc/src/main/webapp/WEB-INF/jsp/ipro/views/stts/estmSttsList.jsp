<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 통계관리 > 평가통계 목록
 *
 * <pre>
 * estm
 *    |_ estmSttsList.jsp
 * 
 * </pre>
 * @date : 2021. 03. 22.
 * @version : 1.0
 * @author : 은우소프트 
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %> 
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<script type="text/javascript" src="${jsPath}/ipro/views/stts/estmSttsList.js"></script> 

<!-- 네비게이션 -->
<div class="area-detail">
	<div class="table-detail">
		<!-- Top -->
		<div class="nav_sec">

			<div class="btn-help" style="display:none;">
				<a href="javascript:helpPopup();">도움말</a>
			</div>
			
			<div class="option-left">
				<ul class="location">
					<li style="font-size: 30px; font-weight: 500;">통계관리 목록</li>
				</ul>
			</div>
			
			<div class="option-right">
				<ul class="location">
					<li class="home"><a href="#">홈</a></li>
					<li><a href="#">${myMenuList.bigMenuNm}</a></li>
					<li><a href="#">${myMenuSubList.smallMenuNm}</a></li>
				</ul>
			</div>
		</div>
	</div>
</div>
<!--//네비게이션 -->

<div class="page-list">
<form id="searchFrm" name="searchFrm">
		<input type="hidden" id="resourceName" name="resourceName" value="${param.resourceName}">
		<input type="hidden" id="resourceDesc" name="resourceDesc" value="${param.resourceDesc}">
		<input type="hidden" id="P_PAGE_NO" name="P_PAGE_NO" value="${comFn:nvl(param.P_PAGE_NO, 1)}">
		<input type="hidden" id="P_PAGE_SIZE" name="P_PAGE_SIZE" value="${comFn:nvl(param.P_PAGE_SIZE, 10)}">
	<div class="form-setting-box">
		<div class="ui-setting">
			<!-- Form Setting -->
			<div class="form-setting">
				<span class="txt-label">통계대상</span>
				<comTag:comCmcdCdValueComboBox id="P_STTS_OBJ_SECD_S" name="P_STTS_OBJ_SECD_S" selectKey="${param.P_STTS_OBJ_SECD_S}" cdId="STTS_OBJ_SECD"  headerKey="" headerValue="선택" className="component-select"/>
			</div>
			<!-- Form Setting -->
			
		</div>

	</div>
	
	
	<!-- buttons -->
	<div class="setting-button">
		<button type="button" id="searchBtn" class="component-button">조회</button>
	</div>
	<!-- //buttons -->
	<div class="area-list">
		<!-- Option -->
		<div class="table-option">
		</div>
		<!-- //Option -->

		<div style="overflow-x: auto; overflow-y:hidden" >
			<table class="component-detail-table type-line-none">
				<colgroup>
					<col span="1" style="width:10%;">
					<col span="1" style="width:20%;">
					<col span="1" style="width:10%;">
					<col span="1" style="width:60%;">
				</colgroup>
				<tbody>
					<tr>
							<th scope="row">조회컬럼추가</th>
							<td style="padding-bottom: 0px; padding-left: 0px; padding-right: 0px; padding-top: 0px;">
								<div style="height: 360px; overflow-x: hidden;">
									<table class="component-detail-table type-line-none">
										<colgroup>
										    <col style="width:30%;">
											<col style="width:*;">
										</colgroup>
										<thead>
											<tr>
												<th colspan="2" style="text-align: left;">&nbsp;
													<input type="hidden" name="selectColAllCheckAt" value="${param.selectColAllCheckAt }">
													<label class="component-checkbox">
														<input type="checkbox" id="selectColAll">
														<i></i>
														<span class="txt-checkbox">컬럼명</span>
													</label>
												</th>
											</tr>
										</thead>
										<tbody>
											<c:if test="${ empty colList }">
												<tr><td colspan="2" class="txt-center">검색 조건에 해당하는 데이터가 존재하지 않습니다.</td></tr>
											</c:if>
											<c:if test="${ not empty colList }">
												<input type="hidden" name="selectCol" value="empty">
												<!-- 통계대상에 세팅되어 있는 컬럼 리스트 -->
												<input type="hidden" name="selectColListCnt" value="${selectColListCnt }">
												<c:forEach items="${ colList }" var="sttsListData" varStatus="status">
													<tr>
													    <td colspan="2">
													    	<label class="component-checkbox">
													    		<!-- 체크된 컬럼 리스트 -->
																<input type="checkbox" name="selectCol" id="selectCol_${ status.count }"  value="${ sttsListData.COLUMN_NAME }"
																 <c:forEach items="${selectColList}" var="selecColData" varStatus="cnt">
																 	<c:if test="${ selecColData.COLUMN_NAME eq sttsListData.COLUMN_NAME }">
																 		checked
																 	</c:if>
																 </c:forEach>
																/>
																<i></i>
																<span class="txt-checkbox"><c:out value='${ sttsListData.COMMENTS }'/></span>
															</label>
													    </td>
													</tr>
												</c:forEach>
											</c:if>
										</tbody>
									</table>
								</div>
							</td>
							
							<th scope="row">조회조건추가</th>
							<td style="padding-bottom: 0px; padding-left: 0px; padding-right: 0px; padding-top: 0px;">
							<div style="height: 360px; overflow-x: hidden;">
								<table class="component-detail-table type-line-none">
									<colgroup>
									    <col align="center" style="width:30%;">
										<col align="left" style="width:30%;">
										<col align="left" style="width:40%;">
									</colgroup>
									<thead>
									<tr>
									    <th scope="col">조건컬럼</th>
									    <th scope="col">조건구분</th>
										<th scope="col">조건값</th>
									</tr>
									</thead>
									<tbody>
										<c:if test="${ empty colList }">
											<tr><td colspan="3" class="txt-center">검색 조건에 해당하는 데이터가 존재하지 않습니다.</td></tr>
										</c:if>
										<c:if test="${ not empty colList }">
											<input type="hidden" name="inqCol" value="empty" style="background-color: yellow;">
											
											<c:forEach items="${ colList }" var="data" varStatus="status">
												<tr>
													<td class="tit"><input type="hidden" name="inqColNm" value="${ data.COLUMN_NAME }" style="background-color: yellow;"/>${ data.COMMENTS }</td>
												    <td><!-- 조건구분 -->
												    	<c:if test="${ data.COLUMN_TYPE ne 'DATE' }">
												    		<comTag:comCmcdCdValueComboBox id="inqCond" name="inqCond" selectKey="${ sch_inqCondList[status.index] }" cdId="STTS_SCH" headerKey="" headerValue="선택" className="component-select"/>
												    	</c:if>
												    	<c:if test="${ data.COLUMN_TYPE eq 'DATE' }">
												    		<comTag:comCmcdCdValueComboBox id="inqCond" name="inqCond" selectKey="${ sch_inqCondList[status.index] }" cdId="STTS_SCH" headerKey="" cond1="1" headerValue="선택" className="component-select"/>
												    	</c:if>
												    	<input type="hidden" name="COMMENTS" value="${data.COMMENTS }" style="background-color: yellow;"/>
												    </td>
												    <td class="tit">
												    	<c:if test="${ data.COLUMN_TYPE ne 'DATE' }">
												    		<input type="text" class="component-input type-full" name="inqText" value="${sch_inqTextList[status.index]}"/>
												    		<input type="hidden" name="inqCol" value="" style="background-color: yellow;"><!-- inqColNm, inqCond, inqText 저장공간 -->
												    	</c:if>
												    	<c:if test="${ data.COLUMN_TYPE eq 'DATE' }">
												    		<input type="hidden" name="dateValue" value="${sch_inqTextList[status.index]}" style="background-color: yellow;"/>
												    		<input type="text" class="component-input datepicker2" name="fromDate" maxlength="10" id="fromDate${sch_inqTextList[status.index]}" value=""  placeholder="시작일" date>
															<span class="wave"> &nbsp;~ &nbsp;</span>
															<input type="text" class="component-input datepicker2" name="toDate" maxlength="10" id="toDate${sch_inqTextList[status.index]}"   value="" placeholder="종료일" date>
															<input type="hidden" class="input w100" name="inqText" value="${sch_inqTextList[status.index]}" style="background-color: yellow;"/>
															<input type="hidden" name="inqCol" value="" style="background-color: yellow;"><!-- inqColNm, inqCond, inqText 저장공간 -->
												    	</c:if>
												    </td>
												</tr>
											</c:forEach>
										</c:if>
									</tbody>
								</table>
							</div>
							</td>
						</tr>
				</tbody>
			</table>
		</div>
	</div>
	<c:if test="${param.P_ACTION ne 'CHANGE' }">
		<div class="area-list" style="width: auto; height: auto;">
			<!-- Option -->
			<div class="table-option">
	
				<!-- Right -->
				<div class="option-right">
					<%-- <div class="table-num type-fleft">
						총 <strong>${comFn:nvl(estmCmplListTotCnt, 0)}</strong>건
					</div> --%>
					<a href="javascript:sttsExcelDwd('searchFrm', '/stts/estmSttsListExcelDwld.do','${comFn:nvl(sttsKeyListCnt, 0)}');" class="btn-download-s type-fleft">
						<i class="icon-download"></i>엑셀 다운로드
					</a>
				</div>
				<!-- //Right -->
			</div>
			<!-- //Option -->
		
		
			<div style="width: auto; height: auto; max-height:400px; overflow-x: auto; overflow-y:auto; white-space:nowrap;" >
				<input type="hidden" id="colListCnt" value="${selectColListCnt }"/>
				<table class="component-detail-table type-line-none" style="width:auto; margin-top: 10px;">
					<caption></caption>
					<colgroup>
						<c:forEach items="${selectColList}" var="selecColData" varStatus="cnt">
							<col span="1">
						</c:forEach>
					</colgroup>
					<thead id="excelTh">
						<tr>
							<c:set var="addCnt" value="0"/>
							<c:forEach items="${selectColList}" var="selecColData" varStatus="cnt">
								<th scope="col" style="padding-left: 10px; padding-right: 10px;">${ selecColData.COMMENTS }</th>
								<c:set var="addCnt" value="${addCnt+1}" />
							</c:forEach>
						</tr>
					</thead>
					<tbody>
						<c:if test="${ empty selectColList }">
							<tr><td class="txt-center" colspan="${addCnt}" style="width:990px; border-top:1px solid #D8DAE8;">검색 조건에 해당하는 데이터가 존재하지 않습니다.</td></tr>
						</c:if>
						<c:if test="${ not empty selectColList }">
							<c:forEach items="${ sttsKeyList }" var="sttsKey" varStatus="rowStatus">
								<tr>
									<c:forEach items="${ sttsListData }" var="stts" varStatus="status">
										<td class="" title="${ stts[rowStatus.index]}" style="padding: 13px 10px; <c:if test="${ not empty sttsListAlign[status.count-1] }">text-align: ${ sttsListAlign[status.count-1]}</c:if>;">												
											
											<c:set var="ROW_TYPE" value="${sttsListType[status.count-1] }" />
											
											<c:if test="${ROW_TYPE eq 'AMT' }">
												${comFn:formatMoney(stts[rowStatus.index])}
											</c:if>
											<c:if test="${ROW_TYPE eq 'DATE' }">
												<%-- ${comFn:formatDate(stts[rowStatus.index],'yyyyMMdd','yyyy-MM-dd')} --%>
												<%-- <fmt:parseDate value="" patten="yyyy-MM-dd"/> --%>
												<fmt:formatDate value="${stts[rowStatus.index]}" pattern="yyyy-MM-dd"/> 
											</c:if>
											<c:if test="${ROW_TYPE eq 'TEXT' }">
												${stts[rowStatus.index]}
											</c:if>
										</td>
									</c:forEach>
								</tr>
							</c:forEach>
						</c:if>
						
					</tbody>
				</table>
			</div>
		</div>
	</c:if>
</form>
</div>

<form id="ajaxFrm" method="POST">
	<input type="hidden" name="resourceName" value="${param.resourceName}">
	<input type="hidden" name="resourceDesc" value="${param.resourceDesc}">
	<input type="hidden" id="P_STTS_OBJ_SECD_S" name="P_STTS_OBJ_SECD_S">
	<input type="hidden" id="P_ACTION" name="P_ACTION">
</form>

<form id="registFrm" method="POST">
	<input type="hidden" name="resourceName" value="${comFn:nvl(param.resourceName, resourceName)}">
	<input type="hidden" name="resourceDesc" value="${param.resourceDesc}">
</form>

<form id="detailFrm" method="POST">
	<input type="hidden" name="resourceName" value="${comFn:nvl(param.resourceName, resourceName)}">
	<input type="hidden" name="resourceDesc" value="${param.resourceDesc}">
</form>

<!-- POPUP FORM -->
<form id="popupFrm" method="POST">
	<input type="hidden" name="resourceName" value="${comFn:nvl(param.resourceName, resourceName)}">
	<input type="hidden" name="resourceDesc" value="${param.resourceDesc}">
	<input type="hidden" id="P_AUTH_ID" name="P_AUTH_ID" value="${sessionScope.loginResult.AUTH_ID}">
	<input type="hidden" id="P_ARA_DEPT_CD" name="P_ARA_DEPT_CD">
</form>