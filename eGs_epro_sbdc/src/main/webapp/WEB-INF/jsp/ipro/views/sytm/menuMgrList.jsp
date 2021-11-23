<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * Admin > 메뉴관리 목록
 *
 * <pre>
 * sytm
 *    |_ menuMgrList.jsp
 * 
 * </pre>
 * @date : 2017. 06. 15
 * @version : 1.0
 * @author : 은우소프트 이주연
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %> 
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %> 

<script type="text/javascript" src="${jsPath}/ipro/views/sytm/menuMgrList.js"></script>

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
					<li style="font-size: 30px; font-weight: 500;">메뉴관리 목록</li>
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
<form id="searchFrm" method="POST">
	<input type="hidden" id="resourceName" name="resourceName" value="${comFn:nvl(param.resourceName, resourceName)}">
	<input type="hidden" id="resourceDesc" name="resourceDesc" value="${param.resourceDesc}">
	<input type="hidden" id="P_PAGE_NO" name="P_PAGE_NO" value="${comFn:nvl(param.P_PAGE_NO, 1)}">
	<input type="hidden" id="P_PAGE_SIZE" name="P_PAGE_SIZE" value="${comFn:nvl(param.P_PAGE_SIZE, 10)}">
	<div class="form-setting-box">
		  <div class="ui-setting">
				<!-- From Setting -->
				<div class="form-setting">
					<span class="txt-label">메뉴ID</span>
					<input type="text" id="P_MENU_ID_S" name="P_MENU_ID_S" value="${param.P_MENU_ID_S}" class="component-input">
				</div>
				<!--// From Setting -->
					
				<!-- From Setting -->
				<div class="form-setting">
					<span class="txt-label">대메뉴</span>
					<dd>
						<select class="component-select" id="P_LRG_MENU_ID_S" name="P_LRG_MENU_ID_S">
							<option value="">전체</option>
							<c:forEach var="data" items="${menuMgrLrgList}" varStatus="status" >
								<option value="${data.MENU_ID}" <c:if test="${data.MENU_ID eq  param.P_LRG_MENU_ID_S}">selected="selected"</c:if>>${data.MENU_NM }</option>
							</c:forEach>
						</select>
					</dd>
				</div>
				<!-- // From Setting -->
		 </div>
	 <div class="ui-setting">
			<!-- From Setting -->
			<div class="form-setting" >
				<span class="txt-label">메뉴명</span>
				<input type="text" name="P_MENU_NM_S"  id="P_MENU_NM_S" value="${param.P_MENU_NM_S}" class="component-input" />
			</div>
			<!-- // From Setting -->
			
			<!-- From Setting -->
			<div class="form-setting" style="margin-right: -30px;">
				<span class="txt-label">메뉴URL</span>
				<input type="text" name="P_LNK_URL_S" id="P_LNK_URL_S" value="${param.P_LNK_URL_S}" class="component-input w50">
			</div>
			<!-- // From Setting -->
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
					총 <strong>${comFn:nvl(menuMgrListTotcnt, 0)}</strong>건
				</div>
				<a href="javascript:excelDwd('searchFrm', '/sytm/menuMgrListExcelDwld.do','${comFn:nvl(menuMgrListTotcnt, 0)}');" class="btn-download-s type-fleft">
					<i class="icon-download"></i>엑셀 다운로드
				</a>
			</div>
			<!-- //Right -->
		</div>
		<!-- //Option -->
			
		<table class="component-table">
             <colgroup>
                  <col width="10%"/>
                  <col width="15%"/>
                  <col width="15%"/>
                  <col width="20%"/>
                  <col width="*"/> 
           	</colgroup>
           	<thead id="excelTh">
		    	<tr>
			    	<th class="txt-center">순번</th>
			    	<th class="txt-center">대메뉴</th>
			    	<th>메뉴ID</th>
			    	<th>메뉴명</th>
			    	<th>메뉴URL</th>
		        </tr>
           	</thead>
           	<tbody>
           		<!-- 엑셀다운로드데이터 시작 -->
				<input type="hidden" name="P_EXCEL_TD" value="RNUM"/>
				<input type="hidden" name="P_EXCEL_TD" value="LRG_MENU_NM"/>
				<input type="hidden" name="P_EXCEL_TD" value="MENU_ID"/>
				<input type="hidden" name="P_EXCEL_TD" value="MENU_NM"/>
				<input type="hidden" name="P_EXCEL_TD" value="LNK_URL"/>
				<!-- //엑셀다운로드데이터 종료 -->
           		<c:if test="${comFn:nvl(menuMgrListTotcnt, 0) == 0}">
					<tr>
						<td colspan="5" class="txt-center">검색 조건에 해당하는 데이터가 존재하지 않습니다.</td>
					</tr>
				</c:if>
				<c:if test="${menuMgrListTotcnt > 0}">
					<c:forEach var="data" items="${menuMgrList}" varStatus="status" >
						<tr>
							<td class="txt-center">${data.RNUM}</td>
							<td class="txt-center">${data.LRG_MENU_NM}</td>
							<td>${data.MENU_ID}</td>
							<td>${data.MENU_NM}</td>
							<td>${data.LNK_URL}</td>
						</tr>
					</c:forEach>
				</c:if>
           	</tbody>
	    </table>
	</div>
		    
	    <!-- pageing -->
	<div class="component-pageing">
		<comTag:pagingIpro totalCount="${comFn:nvl(menuMgrListTotcnt,0)}" pageNo="${comFn:nvl(param.P_PAGE_NO, 1)}" pageSize="${comFn:nvl(param.P_PAGE_SIZE, 10)}" clickPage="pageObj.clickPage"/>
	</div>	
		<!-- // pageing -->
	</form>		
</div>
