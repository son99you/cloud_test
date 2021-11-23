<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 평가관리 > 평가위원관리 > 평가위원POOL현황 목록
 *
 * <pre>
 * estm
 *    |_ cmtmMngPoolList.jsp
 * 
 * </pre>
 * @date : 2021. 03. 23.
 * @version : 1.0
 * @author : 은우소프트 
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %> 
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %> 

<script type="text/javascript" src="${jsPath}/ipro/views/estm/cmtmMngPoolList.js"></script> 

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
					<li style="font-size: 30px; font-weight: 500;">평가위원POOL현황 목록</li>
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
		<input type="hidden" id="excelResultCode" value="${excelResultCode }">
		
		<div class="form-setting-box">
			<div class="ui-setting">
				<!-- Form Setting -->
				<div class="form-setting">
					<span class="txt-label">평가위원명</span>
					<input type="text" id="P_ESTM_CMTM_NM_S" name="P_ESTM_CMTM_NM_S" value="${param.P_ESTM_CMTM_NM_S }" class="component-input">
				</div>
				<!-- Form Setting -->
				
				<!-- Form Setting -->
				<div class="form-setting">
					<span class="txt-label">내/외부 구분</span>
					<!-- Select -->
					<comTag:comCmcdCdValueComboBox id="P_INO_CMTM_SECD_S" name="P_INO_CMTM_SECD_S" selectKey="${param.P_INO_CMTM_SECD_S}" cdId="INO_CMTM_SECD"  headerKey="" headerValue="전체" className="component-select"/>
					<!-- //Select -->
				</div>
				<!-- Form Setting -->
				
			</div>
			<div class="ui-setting">
				<!-- Form Setting -->
				<div class="form-setting">
					<span class="txt-label">대분류</span>
					<!-- Select -->
					<comTag:comCmcdCdValueComboBox id="P_LLF_SECD_S" name="P_LLF_SECD_S" selectKey="${param.P_LLF_SECD_S}" cdId="LLF_SECD"  headerKey="" headerValue="전체" className="component-select"/>
					<!-- //Select -->
				</div>
				<!-- Form Setting -->
				
				<!-- Form Setting -->
				<div class="form-setting">
					<span class="txt-label">내역</span>
					<!-- Select -->
					<comTag:comCmcdCdValueComboBox id="P_CNTN_SECD_S" name="P_CNTN_SECD_S" selectKey="${param.P_CNTN_SECD_S}" cdId="CNTN_SECD"  headerKey="" headerValue="전체" className="component-select"/>
					<!-- //Select -->
				</div>
				<!-- Form Setting -->
				
			</div>
			<div class="ui-setting">
				<!-- Form Setting -->
				<div class="form-setting">
					<span class="txt-label">중분류</span>
					<!-- Select -->
					<comTag:comCmcdCdValueComboBox id="P_MLF_SECD_S" name="P_MLF_SECD_S" selectKey="${param.P_MLF_SECD_S}" cdId="MLF_SECD"  headerKey="" headerValue="전체" className="component-select"/>
					<!-- //Select -->
				</div>
				<!-- Form Setting -->
				
				<!-- Form Setting -->
				<div class="form-setting">
					<span class="txt-label">소분류</span>
					<!-- Select -->
					<comTag:comCmcdCdValueComboBox id="P_SLF_SECD_S" name="P_SLF_SECD_S" selectKey="${param.P_SLF_SECD_S}" cdId="SLF_SECD"  headerKey="" headerValue="전체" className="component-select"/>
					<!-- //Select -->
				</div>
				<!-- Form Setting -->
				
			</div>
	
			<div class="ui-setting">
				<!-- Form Setting -->
				<div class="form-setting">
					<span class="txt-label">수기등록여부</span>
					<!-- Select -->
					<comTag:comCmcdCdValueComboBox id="P_HNDW_REG_YN_S" name="P_HNDW_REG_YN_S" selectKey="${param.P_HNDW_REG_YN_S}" cdId="HNDW_REG_YN"  headerKey="" headerValue="전체" className="component-select"/>
					<!-- //Select -->
				</div>
				<!-- Form Setting -->
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
				<!-- Left -->
				<div class="option-left">
					<button type="button" class="component-button-s" id="registBtn">수기등록</button>
				</div>
				<!-- //Left -->
	
				<!-- Right -->
				<div class="option-right">
	
					<div class="table-num type-fleft">
						총 <strong>${comFn:nvl(cmtmMngPoolListTotCnt, 0)}</strong>건
					</div>
					<a href="javascript:excelDwd('searchFrm'
							, '/estm/cmtmMngPoolListExcelDwld.do'
							,'${comFn:nvl(cmtmMngPoolListTotCnt, 0)}');" class="btn-download-s">
						<i class="icon-download"></i>엑셀 다운로드
					</a>
					
					
					<a type="button" class="btn-download-s" id="excelStyleDwldBtn">
						<i class="icon-download"></i>업로드 양식 다운로드
					</a>
					
					<a type="button" class="btn-upload-s" id="excelStyleUpldBtn" style="padding:5px 8px; margin: 0;">
						<i class="icon-upload"></i>엑셀 업로드
					</a>
					
				</div>
				<!-- //Right -->
			</div>
			<!-- //Option -->
	
			<div style="overflow-x:scroll;">
				<table class="component-table">
					<colgroup>
						<%-- <col width="8%" />
						<col width="8%" />
						<col width="12%" />
						<col width="20%" />
						<col width="18%" />
						<col width="*" />
						<col width="12%" /> --%>
						<col width="50px;" />
						<col width="100px;" />
						<col width="100px;" />
						<col width="200px;" />
						<col width="200px;" />
						<col width="100px;" />
						<col width="200px;" />
						<col width="200px;" />
						<col width="200px;" />
						<col width="100px;" />
					</colgroup>
					<thead id="excelTh">
						<tr>
							<th class="txt-center">순번</th>
							<!-- <th class="txt-center">실평가여부</th> -->
							<th class="txt-center">내/외부 구분</th>
							<th class="txt-center">평가위원명</th>
							<th>전화번호</th>
							<th>이메일</th>
							<th class="txt-center">대분류</th>
							<th class="txt-center">내역</th>
							<th class="txt-center">중분류</th>
							<th class="txt-center">소분류</th>
							<th class="txt-center">수기등록여부</th>
						</tr>
					</thead>
					<tbody>
						<!-- 엑셀다운로드데이터 시작 -->
						<input type="hidden" name="P_EXCEL_TD" value="RNUM"/>
						<input type="hidden" name="P_EXCEL_TD" value="INO_CMTM_SENM"/>
						<input type="hidden" name="P_EXCEL_TD" value="ESTM_CMTM_NM"/>
						<input type="hidden" name="P_EXCEL_TD" value="TEL_NO"/>
						<input type="hidden" name="P_EXCEL_TD" value="EMAL"/>
						<input type="hidden" name="P_EXCEL_TD" value="LLF_NM"/>
						<input type="hidden" name="P_EXCEL_TD" value="CNTN_NM"/>
						<input type="hidden" name="P_EXCEL_TD" value="MLF_NM"/>
						<input type="hidden" name="P_EXCEL_TD" value="SLF_NM"/>
						<input type="hidden" name="P_EXCEL_TD" value="HNDW_REG_NM"/>
						<!-- //엑셀다운로드데이터 종료 -->
						<c:if test="${comFn:nvl(cmtmMngPoolListTotCnt, 0) == 0}">
							<tr>
								<td colspan="10" class="txt-center">검색 조건에 해당하는 데이터가 존재하지 않습니다.</td>
							</tr>
						</c:if>
						<c:if test="${cmtmMngPoolListTotCnt > 0}">
							<c:forEach var="data" items="${cmtmMngPoolList}" varStatus="status">
								<%-- <c:forEach var="real" items="${realEstmYnList}" varStatus="status"> --%>
								<tr onclick="detailInqire('${data.ESTM_CMTM_NO }');">
									<td class="txt-center">${data.RNUM}</td>
									<%-- <td class="txt-center">
										<c:if test="${real.REAL_ESTM_YN eq 'Y' }" ><b style="color: red;">실제</b></c:if>
										<c:if test="${real.REAL_ESTM_YN eq 'N' }"><b style="color: green">모의</b></c:if>
										<c:if test="${real.REAL_ESTM_YN eq  null}">ㅡ</c:if>
									</td> --%>
									<td class="txt-center">
										<c:if test="${data.INO_CMTM_SECD eq 'INN' }"><span class="tag-status type-ing">내부</span></c:if>
										<c:if test="${data.INO_CMTM_SECD ne 'INN' }"><span class="tag-status type-change">외부</span></c:if>
									</td>
									<td class="txt-center">${data.ESTM_CMTM_NM }</td>
									<td>${data.TEL_NO }</td>
									<td>${data.EMAL }</td>
									<td class="txt-center">${data.LLF_NM }</td>
									<td class="txt-center">${data.CNTN_NM }</td>
									<td class="txt-center">${data.MLF_NM }</td>
									<td class="txt-center">${data.SLF_NM }</td>
									<td class="txt-center">
										<c:if test="${data.HNDW_REG_YN eq 'Y'}">예</c:if>
										<c:if test="${data.HNDW_REG_YN ne 'Y'}">아니오</c:if>
									</td>
								</tr>
								<%-- </c:forEach> --%>
							</c:forEach>
						</c:if>
					</tbody>
				</table>
			</div>
		</div>
		
		<!-- pageing -->
		<div class="component-pageing">
			<comTag:pagingIpro totalCount="${comFn:nvl(cmtmMngPoolListTotCnt,0)}" pageNo="${comFn:nvl(param.P_PAGE_NO, 1)}" pageSize="${comFn:nvl(param.P_PAGE_SIZE, 10)}" clickPage="pageObj.clickPage"/>
		</div>
		<!--//pageing -->
	</form>	
</div>

<!-- DETAIL FORM -->
<form id="detailFrm" method="POST">
	<input type="hidden" name="resourceName" value="${comFn:nvl(param.resourceName, resourceName)}">
	<input type="hidden" name="resourceDesc" value="${param.resourceDesc}">
	<input type="hidden" name="P_ESTM_CMTM_NO">
</form>

<!-- EXCEL UPLOAD FORM -->
<form id="excelUploadFrm" method="POST" enctype="multipart/form-data" >
	<input type="hidden" id="resourceName" name="resourceName" value="${param.resourceName}">
	<input type="hidden" id="resourceDesc" name="resourceDesc" value="${param.resourceDesc}"> 
	<input type="hidden" name="fileFlag" value="Y"/>
	<input type="file" id="excelFileUpload" name="excelFileUpload" onchange="excelUploadRegist()" style="display: none;"/>
</form>