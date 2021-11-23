<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 평가기준(신분당선 평가기준)
 *
 * <pre>
 * vend
 *    |_ vendEvalStndList.jsp
 * 
 * </pre>
 * @date : 2017. 06. 15
 * @version : 1.0
 * @author : 은우소프트 홍찬일
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %> 
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %> 

<script type="text/javascript" src="${jsPath}/ipro/views/vend/vendEvalStndList.js"></script>

<div class="sp_sec">
	<div class="nav_sec">
		<ul id="breadcrumbs">
			<li class="home">홈</li>
			<li>${myMenuList.bigMenuNm}</li>
			<li>${myMenuSubList.smallMenuNm}</li> 
		</ul>
	</div>
	<h3 class="sp_tit">평가기준</h3>
	<form id="searchFrm" method="POST"> 
		<input type="hidden" name="resourceName" value="${ param.resourceName }">
		<input type="hidden" id="P_PAGE_NO" name="P_PAGE_NO" value="${comFn:nvl(param.P_PAGE_NO, 1)}">
		<input type="hidden" id="P_PAGE_SIZE" name="P_PAGE_SIZE" value="${comFn:nvl(param.P_PAGE_SIZE, 10)}">
		
		<div class="sp_cont">
			<div class="sch_box filter_sch">
				<dl class="first">
					<dt>평가분야코드</dt>
					<dd>
						<input type="text" id="ev_code1" name="ev_code1" value="${param.ev_code1 }">
					</dd>
					<dt>평가분야명</dt>
					<dd>
						<input type="text" id="ev_name1" name="ev_name1" value="${param.ev_name1 }">
					</dd>
					<dt>사용여부</dt>
					<dd>
						<select name='use_yn' class="select w85p"> 
							<option value='' >전체 </option>    
							<option value='Y' <c:if test="${param.use_yn eq 'Y' }">selected="selected"</c:if>>사용</option>    
							<option value='N' <c:if test="${param.use_yn eq 'N' }">selected="selected"</c:if>>미사용</option>
						</select>
					</dd>
				</dl>
			</div>
			
			<button type="button" class="btn ty03 btn_sch" id="searchBtn">조회</button>
			
			<div class="list_top">
				<p class="total">총 <span>${comFn:nvl(vendEvalStndListTotCnt, 0)}</span>건</p>
				<p class="total" style="float: right;">
					<button type="button" class="btn ty02" id="regBtn">등록</button>
					<button type="button" class="btn ty02" id="regBtn2">등록2</button>
					<button type="button" class="btn ty02" id="regBtn3">등록3</button>
					<button type="button" class="btn ty02" id="regBtn4">평가서식등록</button>
				</p>
			</div><!--// list_top -->
	
			<table class="list_tb">
          		<colgroup>
					<col width="12%">
					<col width="*">  
					<col width="10%">
					<col width="10%">
				</colgroup>
				<thead>
	                <tr>
	                   	<th>평가분야코드</th>
						<th>평가분야명</th>
						<th>평가구분</th>
						<th>사용유무</th>
	                </tr>
	            </thead>
				<tbody>
					<c:if test="${ not empty vendEvalStndList }">
						<c:forEach items="${ vendEvalStndList }" var="codeList" varStatus="loop">
							<tr class="pointer data" onclick="vendEvalStndDetail('${codeList.EV_CODE1}');" style="cursor: pointer;">
								<td>
									<c:out value="${codeList.EV_CODE1}"/>
								</td>
								<td class="tit"><a href="#">${codeList.EV_NAME1 }</a></td>
								<td>${codeList.EV_GUBUN1_NM }</td>
								<td>
									<c:if test="${ codeList.USE_YN eq 'Y' }">
										사용
									</c:if>
									<c:if test="${ codeList.USE_YN eq 'N' }">
										미사용
									</c:if>
								</td>
							</tr>
						</c:forEach>
					</c:if>
				</tbody>
			</table>

			<div class="paging">
				<comTag:pagingIpro totalCount="${comFn:nvl(vendEvalStndListTotCnt,0)}" pageNo="${comFn:nvl(param.P_PAGE_NO, 1)}" pageSize="${comFn:nvl(param.P_PAGE_SIZE, 10)}" clickPage="pageObj.clickPage"/>
			</div> <!--// list_bottom E -->		
		</div>					
	</form>
</div> 
 
<%-- DETAIL FORM --%>
<form id="detailFrm" method="POST"> 
	<input type="hidden" name="resourceName" value="${ param.resourceName }">
	<input type="hidden" name="ev_code1" value="">
</form>