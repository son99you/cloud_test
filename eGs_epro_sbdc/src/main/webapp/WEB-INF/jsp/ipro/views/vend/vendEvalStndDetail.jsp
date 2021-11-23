<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 평가기준 상세(신분당선 평가기준)
 *
 * <pre>
 * vend
 *    |_ vendEvalStndDetail.jsp
 * 
 * </pre>
 * @date : 2017. 06. 16
 * @version : 1.0
 * @author : 은우소프트 홍찬일
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %> 
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %> 

<script type="text/javascript" src="${jsPath}/ipro/views/vend/vendEvalStndDetail.js"></script> 
 
<div class="sp_sec">
	<div class="nav_sec">
		<ul id="breadcrumbs">
			<li class="home">홈</li>
			<li>${myMenuList.bigMenuNm}</li>
			<li>${myMenuSubList.smallMenuNm}</li> 
		</ul>
	</div><!--// nav_sec -->
	<h3 class="sp_tit">평가기준 상세</h3>
	
	<div class="sp_cont">
		<p class="spc_stit">기본정보</p>
		<table class="form_tb">
			<caption>기본정보</caption>
               <colgroup>
                   <col width="15%">
                   <col width="35%">
                   <col width="15%">
                   <col width="35%">
               </colgroup>
			<tr>
				<th>평가분야코드</th>
				<td>
					${vendEvalStndDetail.EV_CODE1}
				</td>
				<th>평가분야명</th>
				<td>
					${vendEvalStndDetail.EV_NAME1}
				</td>
			</tr>
			<tr>
				<th>사용여부</th>
				<td>
					${ vendEvalStndDetail.USE_YN_NM}
				</td>
				<th>평가구분</th>
				<td>
					${vendEvalStndDetail.EV_GUBUN1_NM}
				</td>
			</tr>
			<tr>
				<th>설명</th>
				<td  colspan="3">
					${vendEvalStndDetail.REMARK}
				</td>
			</tr>
		</table>
		
		<p class="spc_stit">평가항목</p>
		<table class="tb">
       		<colgroup>
				<col style="width: 10%;">
               	<col style="width: 15%;">
               	<col style="width: 10%;">
               	<col style="width: auto;">
			</colgroup>
			<thead>
                <tr>
                   	<th class="txtc">순번</th>
					<th class="txtc">평가항목명</th>
					<th class="txtc">배점</th>
					<th class="txtc">설명</th>
                </tr>
            </thead>
			<tbody>
				<c:if test="${ not empty vendEvalStndDetailList }">
					<c:forEach items="${ vendEvalStndDetailList }" var="list" varStatus="loop">
						<tr>
							<td class="txtc">${list.EV_CODE2}</td>
							<td class="txtl pl5">${list.EV_NAME2}</td>
							<td class="txtc">${list.DIST_SCORE }</td>
							<td class="txtc">
								<textarea rows="5" cols="6" style="width: 100%; height: 50px; overflow: hidden;" readonly="readonly">${list.REMARK }</textarea>
							</td>
						</tr>
					</c:forEach>
				</c:if>
			</tbody>
		</table>

		<div class="btm_btns">
	  		<button type="button" class="btn ty02" id="modifyBtn">수정</button>
	  		<button type="button" class="btn ty02" id="deleteBtn">삭제</button>
	  		<button type="button" class="btn ty04" id="listBtn">목록</button>
   		</div>
   	</div>
</div>

<form id="listFrm" method="POST">
	<input type="hidden" name="resourceName" value="${ param.resourceName }">
</form> 

<form id="modifyFrm" method="POST"> 
	<input type="hidden" name="resourceName" value="${ param.resourceName }">
	<input type="hidden" name="ev_code1"  value="${vendEvalStndDetail.EV_CODE1}">
</form>

<form id="deleteFrm" method="POST"> 
	<input type="hidden" name="resourceName" value="${ param.resourceName }">
	<input type="hidden" name="ev_code1"  value="${vendEvalStndDetail.EV_CODE1}">
</form>