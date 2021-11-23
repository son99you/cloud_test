<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 정보관리 > 로그관리 목록
 *
 * <pre>
 * sytm
 *    |_ logMngeList.jsp
 * 
 * </pre>
 * @date : 2016. 09. 28
 * @version : 1.0
 * @author :  yhs
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %>

<script type="text/javascript" src="${jsPath}/ipro/views/sytm/logMngeList.js"></script>

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
					<li style="font-size: 30px; font-weight: 500;">로그관리 목록</li>
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

 <form id="searchFrm" name="searchFrm" class="searchFrm" method="POST">
	<input type="hidden" id="resourceName" name="resourceName" value="${param.resourceName}">
	<input type="hidden" id="resourceDesc" name="resourceDesc" value="${param.resourceDesc}">
	<input type="hidden" id="P_PAGE_NO" name="P_PAGE_NO" value="${comFn:nvl(param.P_PAGE_NO, 1)}">
	<input type="hidden" id="P_PAGE_SIZE" name="P_PAGE_SIZE" value="${comFn:nvl(param.P_PAGE_SIZE, 10)}">
	
	<div class="page-list">
		<div class="form-setting-box">
			<div class="ui-setting">
				<!-- Form Setting -->
				<div class="form-setting">
					<span class="txt-label">시스템접속구분</span>
					<comTag:comCmcdCdValueComboBox id="P_SYS_CONN_SECD_S" name="P_SYS_CONN_SECD_S" selectKey="${param.P_SYS_CONN_SECD_S}" cdId="SYS_CONN_SECD"  headerKey="" headerValue="전체" className="component-select"/>
				 </div>
				 <!-- //Form Setting -->
		
				<div class="ui-setting">			
					<!-- Form Setting -->
					<div class="form-setting">
						<span class="txt-label">접속일자</span>
						<!-- Component Calen -->
						<div class="component-calen">
							<div class="data-calen">
								<input type="text"  id="P_CONN_BEGIN_DT_S" name="P_CONN_BEGIN_DT_S"   class="component-input datepicker" value="${param.P_CONN_BEGIN_DT_S}" date>
								<!-- <i class="icon-calen" date></i> -->
							</div>
		
							<em class="txt-bar">~</em>
							<div class="data-calen">
								<input type="text"  id="P_CONN_END_DT_S" name="P_CONN_END_DT_S"   class="component-input datepicker"  value="${param.P_CONN_END_DT_S}" date>
								<!-- <i class="icon-calen"></i> -->
							</div>
						</div>
						<!-- //Component Calen -->
					</div>
					<!-- //Form Setting -->
				</div>
				<!-- //Form Setting -->
		
				<!-- Form Setting -->
				<div class="ui-setting">
					<div class="form-setting">
						<span  class="txt-label" >사업자번호<br>(사용자ID)</span>
						<input type="text" name="P_CONN_ID_S"  id="P_CONN_ID_S" value="${param.P_CONN_ID_S}" class="component-input"   />
					</div>
					<div class="form-setting">
						<span  class="txt-label">업체명(사용자명)</span>
						<input type="text" name="P_REGR_NM_S"  id="P_REGR_NM_S" value="${param.P_REGR_NM_S}" class="component-input"  />
					</div>
				</div>
				<!--// Form Setting -->
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
						총 <strong>${comFn:nvl(logMngeListTotCnt, 0)}</strong>건
					</div>
					<a href="javascript:excelDwd('searchFrm', '/sytm/logMngeListExcelDwld.do','${comFn:nvl(logMngeListTotCnt, 0)}');" class="btn-download-s type-fleft">
						<i class="icon-download"></i>엑셀 다운로드
					</a>
				</div>
				<!-- //Right -->
			</div>
			<!-- //Option -->
			
			<div style="overflow-x: scroll; overflow-y:hidden" >
				<table class="component-table">
					<colgroup>
						<col width="120px">
						<col width="120px">
						<col width="120px">
						<col width="120px">
						<col width="280px">
						<col width="180px">
						<col width="150px">
						<%-- <col width="150px"> --%>
					</colgroup>
					<thead id="excelTh">
				    	<tr>
				        	<th class="txt-center">시스템접속구분</th>
				        	<th class="txt-center">접속IP</th>
				        	<th class="txt-center">사업자번호<br>(사용자ID)</th>
				        	<th class="txt-center">업체명<br>(사용자명)</th>
				        	<th>내용</th>
				        	<th class="txt-center">접속화면</th>
				            <th class="txt-center">접속일자</th>
				            <!-- <th class="txt-center">상세</th> -->
				        </tr>
		          	</thead>
		          	<tbody>
		          		<!-- 엑셀다운로드데이터 시작 -->
						<input type="hidden" name="P_EXCEL_TD" value="SYS_CONN_SECD_NM"/>
						<input type="hidden" name="P_EXCEL_TD" value="CONN_IP"/>
						<input type="hidden" name="P_EXCEL_TD" value="CONN_ID"/>
						<input type="hidden" name="P_EXCEL_TD" value="REGR_NM"/>
						<input type="hidden" name="P_EXCEL_TD" value="CONN_URL"/>
						<input type="hidden" name="P_EXCEL_TD" value="MENU_NM"/>
						<input type="hidden" name="P_EXCEL_TD" value="REG_DT_F"/>
						<!-- //엑셀다운로드데이터 종료 -->
						<c:if test="${comFn:nvl(logMngeListTotCnt, 0) == 0}">
							<tr>
								<td colspan="8" class="txt-center">검색 조건에 해당하는 데이터가 존재하지 않습니다.</td>
							</tr>
						</c:if>
						<c:if test="${logMngeListTotCnt > 0}">
							<c:forEach var="data" items="${logMngeList}" varStatus="status">
								<tr>
									<td class="txt-center">${data.SYS_CONN_SECD_NM}</td>
									<td class="txt-center">${data.CONN_IP}</td>
									<td class="txt-center">${comFn:formatBizNumber(data.CONN_ID) }</td>
									<td class="txt-center">${data.REGR_NM}</td>
									<td>${data.CONN_CNTN}</td>
									<td class="txt-center">${data.MENU_NM}</td>
									<td class="txt-center">${comFn:formatDate(data.REG_DT,'yyyyMMddHHmmss','yyyy-MM-dd HH:mm')}</td>
									<%-- <td class="txt-center">
										<c:if test="${data.SYS_CONN_SECD eq 'E' }">
											<button type="button" class="btn ty04" onclick="detailInqire('${data.LOG_SN}')">상세</button>
										</c:if>
									</td> --%>
								</tr>
							</c:forEach>
						</c:if>
				  </tbody>
		       </table>
			</div>
	    </div>

	    <!-- pageing -->
		<div class="component-pageing">
			<comTag:pagingIpro totalCount="${comFn:nvl(logMngeListTotCnt,0)}" pageNo="${comFn:nvl(param.P_PAGE_NO, 1)}" pageSize="${comFn:nvl(param.P_PAGE_SIZE, 10)}" clickPage="pageObj.clickPage"/>
		</div> 		
		<!-- //pageing -->
		
	</div>
</form>
	
<%-- POPUP FORM --%>
<form id="popupFrm" method="POST"> 
	<input type="hidden" name="resourceName" value="${ param.resourceName }">
	<input type="hidden" name="P_LOG_SN">
</form>