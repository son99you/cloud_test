<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 정보정보 > 설계추천파일 목록
 *
 * <pre>
 * sytm 
 *    |_ dsgnRecmFileList.jsp
 * 
 * </pre>
 * @date : 20201028
 * @version : 1.0
 * @author : 
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %> 
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %> 

<script type="text/javascript" src="${jsPath}/ipro/views/sytm/dsgnRecmFileList.js"></script>

<div class="sp_sec">
	<div class="nav_sec">
		<ul id="breadcrumbs">
			<li class="home">홈</li>
			<li>${myMenuList.bigMenuNm}</li>
			<li>${myMenuSubList.smallMenuNm}</li> 
		</ul>
	</div>
	<h3 class="sp_tit">설계추천파일관리</h3>

	<form id="searchFrm" name="searchFrm" method="post">
		<input type="hidden" id="resourceName" name="resourceName" value="${param.resourceName}">
		<input type="hidden" id="P_PAGE_NO" name="P_PAGE_NO" value="${comFn:nvl(param.P_PAGE_NO, 1)}">
		<input type="hidden" id="P_PAGE_SIZE" name="P_PAGE_SIZE" value="${comFn:nvl(param.P_PAGE_SIZE, 10)}">
		<div class="sp_cont">
			<div class="sch_box filter_sch">
				<dl class="first">
					<dt>설계파일ID</dt>
					<dd>
						<input type="text" name="P_CD_DTL_ID_S"  id="P_CD_DTL_ID_S" value="${param.P_CD_DTL_ID_S}"  class="input w100p"  placeholder="설계파일ID" />
					</dd>
					<dt>설계파일명</dt>
					<dd>
						<input type="text" name="P_CD_DTL_NM_S"  id="P_CD_DTL_NM_S" value="${param.P_CD_DTL_NM_S}"  class="input w100p"  placeholder="설계파일명" />
					</dd>
				</dl>
			</div>
			<button type="button" class="btn ty03 btn_sch" id="searchBtn">조회</button>
			
			<div class="list_top">
				<p class="total">총 <span>${comFn:nvl(dsgnRecmListTotCnt, 0)}</span>건</p>	
				<p class="total"  style="float: right;"> 
					<button type="button" class="btn ty02" id="registBtn">신규등록</button>
				</p>
			</div><!--// list_top -->
			<table class="list_tb">
				<caption>설계추천파일관리 목록</caption>
             	<colgroup>
             		<col width="8%" />
             		<col width="10%" />
					<col width="*" />
					<col width="10%" />
					<col width="10%" />
           		</colgroup>
           		<thead>
			    	<tr>
	                   <th>No</th>
	                   <!-- <th>계약구분</th> -->
	                   <th>설계파일ID</th>
	                   <th>설계파일명</th>
	                   <th>등록자</th>
	                   <th>등록일자</th>
			        </tr>
           		</thead>
           		<tbody>
           			<c:if test="${comFn:nvl(dsgnRecmListTotCnt, 0) == 0}">
						<tr>
							<td class="txt-center" colspan="6">검색 조건에 해당하는 데이터가 존재하지 않습니다.</td>
						</tr>
					</c:if>
					<c:if test="${dsgnRecmListTotCnt > 0}">
						<c:forEach var="data" items="${dsgnRecmList}" varStatus="status" >
							<tr class="row" onclick="detailInqire('${data.CD_DTL_ID}');" style="cursor: pointer;">
								<td class="no">${data.RNUM}</td>
								<%-- <td>${data.CONT_SECD_NM}</td> --%>
								<td>${data.CD_DTL_ID}</td>
								<td class="tit" title="${data.CD_DTL_NM}"><a href="#">${data.CD_DTL_NM}</a></td>
								<td>${data.REGR_NM}</td>
								<td class="date">${comFn:formatDate(data.REG_DT, 'yyyyMMddHHmmss', 'yyyy-MM-dd')}</td>
							</tr>
						</c:forEach>
					</c:if>
           		</tbody>
		    </table>
				
			<div class="paging">
				<comTag:pagingIpro totalCount="${comFn:nvl(dsgnRecmListTotCnt,0)}" pageNo="${comFn:nvl(param.P_PAGE_NO, 1)}" pageSize="${comFn:nvl(param.P_PAGE_SIZE, 10)}" clickPage="pageObj.clickPage"/>
			</div>
		</div>
	</form>
</div>

<%-- DETAIL FORM --%>
<form id="detailFrm" class="search_form" method="POST">
	<input type="hidden" name="resourceName" value="${comFn:nvl(param.resourceName, resourceName)}" >
	<input type="hidden" name="P_CD_DTL_ID" id="P_CD_DTL_ID">
	<input type="hidden" name="contextPath" value="${contextPath}">
	<input type="hidden" name="P_USR_ID" value="${loginResult.USR_ID}">
</form>

<%-- DETAIL FORM --%>
<form id="registFrm" class="search_form" method="POST">
	<input type="hidden" name="resourceName" value="${comFn:nvl(param.resourceName, resourceName)}" >
</form>