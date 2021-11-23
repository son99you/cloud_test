<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 평가관리 > 평가위원순위선정완료현황 목록
 *
 * <pre>
 * estm
 *    |_ cmtmRnkSlctCmplList.jsp
 * 
 * </pre>
 * @date : 2021. 03. 11.
 * @version : 1.0
 * @author : 은우소프트 
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %> 
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %> 

<script type="text/javascript" src="${jsPath}/ipro/views/estm/cmtmRnkSlctCmplList.js"></script> 

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
					<li style="font-size: 30px; font-weight: 500;">평가위원순위선정완료현황 목록</li>
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
	<form id="searchFrm" name="searchFrm" method="post">
		<input type="hidden" id="resourceName" name="resourceName" value="${comFn:nvl(param.resourceName, resourceName)}">
		<input type="hidden" id="resourceDesc" name="resourceDesc" value="${param.resourceDesc}">
		<input type="hidden" id="P_PAGE_NO" name="P_PAGE_NO" value="${comFn:nvl(param.P_PAGE_NO, 1)}">
		<input type="hidden" id="P_PAGE_SIZE" name="P_PAGE_SIZE" value="${comFn:nvl(param.P_PAGE_SIZE, 10)}">
		
		<div class="form-setting-box">
			<div class="ui-setting">
				<!-- Form Setting -->
				<div class="form-setting">
					<span class="txt-label">평가번호</span>
					<input type="text" id="P_ESTM_NO_S" name="P_ESTM_NO_S" value="${param.P_ESTM_NO_S }" class="component-input">
				</div>
				<!-- Form Setting -->
				
				<!-- Form Setting -->
				<div class="form-setting">
					<span class="txt-label">평가명</span>
					<input type="text" id="P_ESTM_NM_S" name="P_ESTM_NM_S" value="${param.P_ESTM_NM_S }" class="component-input w50">
				</div>
				<!-- //Form Setting -->
				
				
			</div>
	
			<div class="ui-setting">
				<!-- Form Setting -->
				<div class="form-setting">
					<span class="txt-label">평가구분</span>
					<!-- Select -->
					<comTag:comCmcdCdValueComboBox id="P_ESTM_SECD_S" name="P_ESTM_SECD_S" selectKey="${param.P_ESTM_SECD_S}" cdId="ESTM_SECD" headerKey="" headerValue="전체" className="component-select"/>
					<!-- //Select -->
				</div>
				<!-- Form Setting -->
				
				<!-- Form Setting -->
				<%-- <div class="form-setting">
					<span class="txt-label">진행상태</span>
					<!-- Select -->
					<comTag:comCmcdCdValueComboBox id="P_ESTM_PSCD_S" name="P_ESTM_PSCD_S" selectKey="${param.P_ESTM_PSCD_S}" cdId="ESTM_PSCD"  headerKey="" cond2="RNK_SLCT_CMPL" headerValue="전체" className="component-select"/>
					<!-- //Select -->
				</div> --%>
				<!-- Form Setting -->
				
			</div>
			
			<div class="ui-setting">			
				<!-- Form Setting -->
				<div class="form-setting">
						<span class="txt-label">작성일자</span>
						<!-- Component Calen -->
						<div class="component-calen">
							<div class="data-calen">
								<input type="text" id="P_REG_BEGIN_DT_S" name="P_REG_BEGIN_DT_S" value="${param.P_REG_BEGIN_DT_S }" class="component-input" date>
								<!-- <i class="icon-calen" date></i> -->
							</div>
							<em class="txt-bar">~</em>
							<div class="data-calen">
								<input type="text" id="P_REG_END_DT_S" name="P_REG_END_DT_S" value="${param.P_REG_END_DT_S }" class="component-input" date>
								<!-- <i class="icon-calen"></i> -->
							</div>
						</div>
						<!-- //Component Calen -->
					</div>
				<!-- //Form Setting -->
				
				<!-- Form Setting -->
				<div class="form-setting">
						<span class="txt-label">평가담당부서</span>
						<div class="component-input-search">
							<input type="text" id="P_ESTM_CHRG_DEPT_NM_S" name="P_ESTM_CHRG_DEPT_NM_S" value="${param.P_ESTM_CHRG_DEPT_NM_S }" class="component-input" readonly="readonly">
							<input type="hidden" id="P_ESTM_CHRG_DEPT_NO_S" name="P_ESTM_CHRG_DEPT_NO_S" value="${param.P_ESTM_CHRG_DEPT_NO_S }">
							<a href="javascript:" class="btn-search-close" id="estmChrgDeptDelBtn"></a>
							<a href="javascript:" class="btn-search" id="estmChrgDepSrchBtn"></a>
						</div>
					</div>
				<!-- //Form Setting -->
				
			</div>
		</div>
	
		
		<!-- buttons -->
		<div class="setting-button">
			<button type="button" class="component-button" id="searchBtn">조회</button>
		</div>
		<!-- //buttons -->
		<div class="area-list">
			<!-- Option -->
			<div class="table-option">
	
				<!-- Right -->
				<div class="option-right">
	
					<div class="table-num type-fleft">
						총 <strong>${comFn:nvl(cmtmRnkSlctCmplListTotCnt, 0)}</strong>건
					</div>
					<a href="javascript:excelDwd('searchFrm', '/estm/cmtmRnkSlctCmplListExcelDwld.do','${comFn:nvl(cmtmRnkSlctCmplListTotCnt, 0)}');" class="btn-download-s type-fleft">
						<i class="icon-download"></i>엑셀 다운로드
					</a>
				</div>
				<!-- //Right -->
			</div>
			<!-- //Option -->
			
			
			<div style="overflow-x: scroll; overflow-y:hidden" >
				<table class="component-table">
					<colgroup>
						<col width="50px">
						<col width="80px">
						<col width="180px">
						<col width="150px">
						<col width="100px">
						<col width="300px">
						<col width="350px">
						<col width="150px">
						<col width="120px">
					</colgroup>
					<thead id="excelTh">
						<tr>
							<th class="txt-center">순번</th>
							<th class="txt-center">실평가여부</th>
							<th class="txt-center">진행상태</th>
							<th>평가구분</th>
							<th class="txt-center">평가번호</th>
							<th>평가명</th>
							<th>평가기간</th>
							<th>평가담당부서</th>
							<th class="txt-center">작성자</th>
						</tr>
					</thead>
					<tbody>
						<!-- 엑셀다운로드데이터 시작 -->
						<input type="hidden" name="P_EXCEL_TD" value="RNUM"/>
						<input type="hidden" name="P_EXCEL_TD" value="REAL_ESTM_NM"/>
						<input type="hidden" name="P_EXCEL_TD" value="ESTM_PSCD_NM"/>
						<input type="hidden" name="P_EXCEL_TD" value="ESTM_SECD_NM"/>
						<input type="hidden" name="P_EXCEL_TD" value="ESTM_NO"/>
						<input type="hidden" name="P_EXCEL_TD" value="ESTM_NM"/>
						<input type="hidden" name="P_EXCEL_TD" value="TOTL_ESTM_DT_ALL"/>
						<input type="hidden" name="P_EXCEL_TD" value="ESTM_CHRG_DEPT_NM"/>
						<input type="hidden" name="P_EXCEL_TD" value="REGR_NM"/>
						<!-- //엑셀다운로드데이터 종료 -->
						<c:if test="${comFn:nvl(cmtmRnkSlctCmplListTotCnt, 0) == 0}">
							<tr>
								<td colspan="8" class="txt-center">검색 조건에 해당하는 데이터가 존재하지 않습니다.</td>
							</tr>
						</c:if>
						<c:if test="${cmtmRnkSlctCmplListTotCnt > 0}">
							<c:forEach var="data" items="${cmtmRnkSlctCmplList}" varStatus="status">
								<tr onclick="detailInqire('${data.ESTM_NO }', '${data.MAX_ESTM_CMTM_SLCT_NGR }');">
									<td class="txt-center">${data.RNUM}</td>
									<td class="txt-center">
										<c:if test="${data.REAL_ESTM_YN eq 'Y' }" ><b style="color: red;">실제</b></c:if>
										<c:if test="${data.REAL_ESTM_YN eq 'N' }"><b style="color: green">모의</b></c:if>
										<c:if test="${data.REAL_ESTM_YN eq  null}">ㅡ</c:if>
									</td>
									<td class="txt-center">
										<span class="tag-status type-complete">${data.ESTM_PSCD_NM}</span>
									</td>
									<td>${data.ESTM_SECD_NM }</td>
									<td class="txt-center">${data.ESTM_NO }</td>
									<td title="${data.ESTM_NM }">${comFn:toShorten(data.ESTM_NM, 25)}</td>
									<td>${comFn:formatDate(data.TOTL_ESTM_ST_DT,'yyyyMMddHHmmss','yyyy-MM-dd HH:mm:ss')} ~ ${comFn:formatDate(data.TOTL_ESTM_END_DT,'yyyyMMddHHmmss','yyyy-MM-dd HH:mm:ss')}</td>
									<td>${data.ESTM_CHRG_DEPT_NM }</td>
									<td class="txt-center">${data.REGR_NM }</td>
								</tr>
							</c:forEach>
						</c:if>
					</tbody>
				</table>
			</div>
		</div>
	
		<!-- pageing -->
		<div class="component-pageing">
			<comTag:pagingIpro totalCount="${comFn:nvl(cmtmRnkSlctCmplListTotCnt,0)}" pageNo="${comFn:nvl(param.P_PAGE_NO, 1)}" pageSize="${comFn:nvl(param.P_PAGE_SIZE, 10)}" clickPage="pageObj.clickPage"/>
		</div>
		<!--//pageing -->
	</form>
		
</div>

<!-- DETAIL FORM -->
<form id="detailFrm" method="POST">
	<input type="hidden" name="resourceName" value="${comFn:nvl(param.resourceName, resourceName)}">
	<input type="hidden" name="resourceDesc" value="${param.resourceDesc}">
	<input type="hidden" id="P_ESTM_NO" name="P_ESTM_NO">
	<input type="hidden" id="P_ESTM_CMTM_SLCT_NGR" name="P_ESTM_CMTM_SLCT_NGR"> 
</form>

<!-- POPUP FORM -->
<form id="popupFrm" method="POST">
	<input type="hidden" name="resourceName" value="${comFn:nvl(param.resourceName, resourceName)}">
	<input type="hidden" name="resourceDesc" value="${param.resourceDesc}">
	<input type="hidden" name="setMulti">	
</form>