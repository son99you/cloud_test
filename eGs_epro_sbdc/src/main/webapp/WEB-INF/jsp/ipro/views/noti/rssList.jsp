<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 알림마당 > 계약자료실 목록
 *
 * <pre>
 * noti 
 *    |_ rssList.jsp
 * 
 * </pre>
 * @date : 2017. 06. 13
 * @version : 1.0
 * @author : 은우소프트 이주연
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %> 
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %> 

<script type="text/javascript" src="${jsPath}/ipro/views/noti/rssList.js"></script>

<div class="sp_sec">
	<div class="nav_sec">
		<ul id="breadcrumbs">
			<li class="home">홈</li>
			<li>${myMenuList.bigMenuNm}</li>
			<li>${myMenuSubList.smallMenuNm}</li> 
		</ul>
	</div>
	<h3 class="sp_tit">계약자료실 목록</h3>

	<form id="searchFrm" name="searchFrm" method="post">
		<input type="hidden" name="resourceName" value="${comFn:nvl(param.resourceName, resourceName)}" >
		<input type="hidden" id="resourceDesc" name="resourceDesc" value="${param.resourceDesc}">
		<input type="hidden" id="P_PAGE_NO" name="P_PAGE_NO" value="${comFn:nvl(param.P_PAGE_NO, 1)}">
		<input type="hidden" id="P_PAGE_SIZE" name="P_PAGE_SIZE" value="${comFn:nvl(param.P_PAGE_SIZE, 10)}">
		
		<div class="sp_cont">
			<div class="sch_box filter_sch">
				<dl class="first">
					<dt>제목</dt>
					<dd><input type="text" name="P_TTL_NM_S"  id="P_TTL_NM_S" value="${param.P_TTL_NM_S}" class="input w100p"  placeholder="제목" /></dd>
					<dt>등록자</dt>
					<dd><input type="text" name="P_REGR_NM_S"  id="P_REGR_NM_S" value="${param.P_REGR_NM_S}" class="input w100p"  placeholder="등록자" /></dd>
				</dl> 
			</div>
			<button type="button" class="btn ty03 btn_sch" id="searchBtn">조회</button>
			
		<div class="list_top">
				<div class="list_top">
					<p class="total">총 <span>${comFn:nvl(rssListTotCnt, 0)}</span>건</p>		
					<p class="total" style="float: right;"> 
					<button type="button" class="btn ty02" " id="registBtn">신규등록</button>
				</p>
			</div> <!--// list_top E -->
			<table class="list_tb">
				<caption>계약자료실 목록</caption>
             	<colgroup>
             		<col width="5%">
             		<col width="*">
             		<col width="10%">
             		<col width="10%">
             		<col width="5%">
             		<col width="5%">
           		</colgroup>
           		<thead>
			    	<tr>
	                   <th>No</th>
	                   <th>제목</th>
	                   <th>등록자</th>
	                   <th>등록일자</th>
	                   <th>조회수</th>
	                   <th>파일</th>
			        </tr>
           		</thead>
           		<tbody>
           			<c:if test="${comFn:nvl(rssListTotCnt, 0) == 0}">
						<tr>
							<td class="txt-center" colspan="6">검색 조건에 해당하는 데이터가 존재하지 않습니다.</td>
						</tr>
					</c:if>
					<c:if test="${rssListTotCnt > 0}">
						<c:forEach var="data" items="${rssList}" varStatus="status" >
							<tr class="row" onclick="detailInqire('${data.BBS_SECD }', '${data.BBS_SN}');" style="cursor: pointer;">
								<td class="no">${data.RNUM}</td>
								<td class="tit" title="${data.TTL_NM}"><a href="#">${data.TTL_NM}</a></td>
								<td>${data.REGR_NM}</td>
								<td class="date">${comFn:formatDate(data.REG_DT, 'yyyyMMddHHmmss', 'yyyy-MM-dd')}</td>
								<td>${comFn:nvl(data.INQ_CNT, 0)}</td>
								<td> 
									<c:if test="${not empty data.FILE_GRP_NO and data.FILE_CNT > 0}">
										<img src="${imagePath}/comm/sub/icon_file.png" alt="첨부파일">					
									</c:if>
								</td>
							</tr>
						</c:forEach>
					</c:if>
           		</tbody>
		    </table>
				
			<div class="paging">
				<comTag:pagingIpro totalCount="${comFn:nvl(rssListTotCnt,0)}" pageNo="${comFn:nvl(param.P_PAGE_NO, 1)}" pageSize="${comFn:nvl(param.P_PAGE_SIZE, 10)}" clickPage="pageObj.clickPage"/>
			</div> <!--// list_bottom E -->			
	    </div> 				
	</form>
</div>

<%-- DETAIL FORM --%>  
<form id="detailFrm" class="search_form" method="POST" >
	<input type="hidden" name="resourceDesc" value="${ param.resourceDesc }" > 
	<input type="hidden" name="resourceName" value="${comFn:nvl(param.resourceName, resourceName)}" >
	<input type="hidden" name="contextPath" value="${contextPath}" >
	<input type="hidden" name="P_BBS_SECD">
	<input type="hidden" name="P_BBS_SN">
</form>