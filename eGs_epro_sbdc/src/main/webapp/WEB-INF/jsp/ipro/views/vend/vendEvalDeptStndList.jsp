<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 유관부서 평가기준(신분당선 유관부서 평가기준)
 *
 * <pre>
 * vend
 *    |_ vendEvalDeptStndList.jsp
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

<script type="text/javascript" src="${jsPath}/ipro/views/vend/vendEvalDeptStndList.js"></script> 

<div class="sp_sec">
	<div class="nav_sec">
		<ul id="breadcrumbs">
			<li class="home">홈</li>
			<li>${myMenuList.bigMenuNm}</li>
			<li>${myMenuSubList.smallMenuNm}</li> 
		</ul>
	</div>
	
	<h3 class="sp_tit">유관부서평가기준</h3>
	
	<form id="searchFrm" method="POST" > 
		<input type="hidden" name="resourceName" value="${ param.resourceName }" >
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
					<dt>SG명</dt>
					<dd>
						<comTag:comCmcdCdValueComboBox name="dept_sg_code" cdId="N00001" headerValue="전체" selectKey="${param.dept_sg_code }"/>
					</dd>
				</dl>
				<dl>
					<dt>사용여부</dt>
					<dd>
						<select name='use_yn'>
							<option value='' >전체 </option>    
							<option value='Y' <c:if test="${param.use_yn eq 'Y' }">selected="selected"</c:if>>사용</option>    
							<option value='N' <c:if test="${param.use_yn eq 'N' }">selected="selected"</c:if>>미사용</option>
						</select>
					</dd>
				</dl>
			</div>
			
			<button type="button" class="btn ty03 btn_sch" id="searchBtn">조회</button>
			
			<div class="list_top">
				<p class="total">총 <span>${vendEvalDeptStndListTotCnt }</span>건</p>
				<p class="total" style="float: right;">
					<button type="button" class="btn ty02" id="regBtn">등록</button>
				</p>		
			</div> <!--// list_top E -->

			<table class="list_tb">
           		<colgroup>
					<col width="10%" >
					<col width="10%" >
					<col width="*">  
					<col width="10%">
					<col width="10%" >
				</colgroup>
				<thead>
	                <tr>
                    	<th>SG명</th>
                    	<th>평가분야코드</th>
						<th>평가분야명</th>
						<th>평가구분</th>
						<th>사용유무</th>
	                </tr>
	            </thead>
				<tbody>
					<c:if test="${vendEvalDeptStndListTotCnt > 0}">
						<c:set var="tot_cnt" value="0" scope="request"></c:set> 
						<c:forEach items="${ vendEvalDeptStndList }" var="codeList" varStatus="loop">
							<c:if test="${ loop.count == 1 }">
								<c:set var="tot_cnt" value="${ vendEvalDeptStndListTotCnt}" scope="page"></c:set>
							</c:if>
							
							<tr class="pointer" onclick="vendEvalDeptStndDetail('${codeList.EV_CODE1}');">
								<td class="txtc">${codeList.DEPT_SG_NAME}</td>
								<td class="txtc">${codeList.EV_CODE1}</td>
								<td class="tit">
									<a href="#">${codeList.EV_NAME1 }</a>
								</td>
								<td class="txtc">${codeList.EV_GUBUN1_NM }</td>
								<td class="txtc">
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
				<comTag:pagingIpro totalCount="${vendEvalDeptStndListTotCnt}" pageNo="${comFn:nvl(param.P_PAGE_NO, 1)}" pageSize="${comFn:nvl(param.P_PAGE_SIZE, 10)}" clickPage="pageObj.clickPage"/>
			</div> <!--// list_bottom E -->
		</div>		
	</form>
</div>

<%-- DETAIL FORM --%>
<form id="detailFrm" method="POST" > 
	<input type="hidden" name="resourceName" value="${ param.resourceName }" >
	<input type="hidden" name="ev_code1" value="" >
</form>