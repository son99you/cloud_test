<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 입찰현황 > 유찰공고 목록
 *
 * <pre>
 * ebid 
 *    |_ popup
              |_ fibList.jsp
 * 
 * </pre>
 * @date : 2017. 06. 13
 * @version : 1.0
 * @author : 은우소프트 전상훈
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %> 
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %> 

<script type="text/javascript" src="${jsPath}/ipro/views/ebid/popup/fibList.js"></script> 
 
<div class="pop_wrap">
	<div class="pop_header">
		<h1 class="tit">유찰공고 목록</h1>
	</div> <!--// pop_header E -->
	<div class="pop_container">
		<div class="view_wrap typeC">
			<div class="view_area m0 typeB">
				<form id="searchFrm" method="post">
					<table>
						<colgroup>
							<col style="width: 15%;">
							<col style="width: 30%;">
							<col style="width: 15%;">
							<col style="width: 30%;">
						</colgroup>
						<tr>
							<th>공고번호</th>
							<td>
								<input type="text"  size="20" id="P_ANNC_NO_ODR_S" name="P_ANNC_NO_ODR_S" value="${param.P_ANNC_NO_ODR_S}">
							</td>
							<th>입찰명</th>
							<td>
								<input type="text"  size="20" id="P_BID_NM_S" name="P_BID_NM_S" value="${param.P_BID_NM_S}" maxlength="600">
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
		
		<div class="btn_wrap mt10">
			<button type="button" class="btn_p btn_p1 btn_lookup" id="searchBtn"><img src="${imagePath}/ipro/icon/btn_icon02.png" alt="">조회</button>
		</div> <!--// btn_wrap E -->
		
		<div class="pop_list_wrap">
			<div class="pop_list_top">
		        <p class="pop_total">총 <span>${comFn:nvl(fibListTotcnt, 0)}</span>건</p>
		    </div>
		    <div class="pop_list_conts">
				<table>
					<caption>유찰 공고 목록</caption>
               		<colgroup>
						<col style="width: 20%;">
						<col style="width: auto;">
						<col style="width: 20%;">
	            	</colgroup>
	            	<thead>
				    	<tr>
				            <th>공고번호</th>
				            <th>입찰명</th>
				            <th>유찰일시</th>
				        </tr>
			        </thead>
			        <tbody>
			        	<c:if test="${comFn:nvl(fibListTotcnt, 0) == 0}">
							<tr>
								<td colspan="3" class="txtc">검색 조건에 해당하는 데이터가 존재하지 않습니다.</td>
							</tr>
						</c:if>
						<c:if test="${fibListTotcnt > 0}">
							<c:forEach var="data" items="${fibList}" varStatus="status" >
							<tr class="row" style="cursor: pointer;"  onclick="fibListSelect('${data.ANNC_NO}', '${data.ANNC_NGR}','${data.ROUND_NO }');">
								<td class="txtc">${data.ANNC_NO}-${data.ANNC_NGR}</td>
								<td class="txtl pl5" title="${data.BID_NM}">${comFn:toShorten(data.BID_NM, 50)}</td>
								<td class="txtc">${comFn:formatDate(data.PROC_DT,'yyyyMMddHHmmss','yyyy-MM-dd')}</td>
							</tr>
							</c:forEach>
						</c:if>
			        </tbody>
			    </table>
			</div>
		    <div class="pop_list_bottom">
				<div class="pop_list_pager">
					<comTag:pagingIpro totalCount="${fibListTotcnt}" pageNo="${comFn:nvl(param.P_PAGE_NO, 1)}" pageSize="${comFn:nvl(param.P_PAGE_SIZE, 10)}" clickPage="pageObj.clickPage"/>
				</div>
				<div class="btn_wrap view_btn">
			    	<button type="button" class="btn btn_02 btn_close" id="closeBtn" >닫기</button>
			    </div>
	    	</div>
		</div>
	</div>
</div> <!--// content E-->
