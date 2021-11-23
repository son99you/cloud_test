<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 알림마당 > 입찰공지사항 목록
 *
 * <pre>
 * noti 
 *    |_ bidNotiList.jsp
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

<script type="text/javascript" src="${jsPath}/ipro/views/noti/bidNotiList.js"></script>

<div class="contents_wrap">
	<ul class="step_wrap">
		<li><a href="#">${myMenuList.bigMenuNm}</a></li>
		<li><a href="#">${myMenuSubList.smallMenuNm}</a></li>
	</ul>
	<div class="tit_wrap">
		<h3 class="tit">입찰공지사항 목록</h3>
	</div>

	<form id="searchFrm" name="searchFrm" method="post">
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
						<tr height="24px">
							<th>제목</th>
							<td><input type="text" name="P_TTL_NM_S"  id="P_TTL_NM_S" value="${param.P_TTL_NM_S}" class="w_95p" /></td>
							<th>등록자</th>
							<td><input type="text" name="P_REGR_NM_S"  id="P_REGR_NM_S" value="${param.P_REGR_NM_S}" class="w_95p" /></td>
						</tr> 
					</table>				
				</div>
			</div>
			
			<div class="btn_wrap mt10">
				<button type="button" class="btn btn_m btn_c2" id="searchBtn">조회</button>
			</div> <!--// btn_wrap E -->
			
			<div class="list_wrap mt30" id="contentWrap">
				<div class="list_top">
					<p class="total">총 <span>${comFn:nvl(bidNotiListTotCnt, 0)}</span>건</p>		
				</div> <!--// list_top E -->
				<div class="list_conts">		
					<table>
						<caption>입찰공지사항 목록</caption>
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
		           			<c:if test="${comFn:nvl(bidNotiListTotCnt, 0) == 0}">
								<tr>
									<td class="txt-center" colspan="6">검색 조건에 해당하는 데이터가 존재하지 않습니다.</td>
								</tr>
							</c:if>
							<c:if test="${bidNotiListTotCnt > 0}">
								<c:forEach var="data" items="${bidNotiList}" varStatus="status" >
									<tr class="row" onclick="detailInqire('${data.BBS_SECD }', '${data.BBS_SN}');" style="cursor: pointer;">
										<td>${data.RNUM}</td>
										<td class="pl5 list_tit">${data.TTL_NM}</td>
										<td>${data.REGR_NM}</td>
										<td>${comFn:formatDate(data.REG_DT, 'yyyyMMddHHmmss', 'yyyy-MM-dd')}</td>
										<td>${comFn:nvl(data.INQ_CNT, 0)}</td>
										<td> 
											<c:if test="${not empty data.FILE_GRP_NO}">
												<img src="${imagePath}/comm/sub/icon_file.png" alt="첨부파일">					
											</c:if>
										</td>
									</tr>
								</c:forEach>
							</c:if>
		           		</tbody>
				    </table>
				</div>
				
				<div class="list_bottom">
					<comTag:pagingIpro totalCount="${bidNotiListTotCnt}" pageNo="${comFn:nvl(param.P_PAGE_NO, 1)}" pageSize="${comFn:nvl(param.P_PAGE_SIZE, 10)}" clickPage="pageObj.clickPage"/>
					<div class="list_btn">
						<button type="button" class="btn btn_m btn_orange" id="registBtn">신규등록</button>
					</div> <!--// btn_wrap E -->
				</div> <!--// list_bottom E -->			
		    </div> 				
			
		</fieldset>
	</form>
</div>

<%-- DETAIL FORM --%>  
<form id="detailFrm" class="search_form" method="POST" >
	<input type="hidden" name="resourceDesc" value="${ param.resourceDesc }" > 
	<input type="hidden" name="resourceName" value="${ param.resourceName }" >
	<input type="hidden" name="contextPath" value="${contextPath}" >
	<input type="hidden" name="P_BBS_SECD">
	<input type="hidden" name="P_BBS_SN">
</form>