<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 세부평가기준 상세(신분당선 세부평가기준)
 *
 * <pre>
 * vend
 *    |_ vendDtlEvalStndDetail.jsp
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

<script type="text/javascript" src="${jsPath}/ipro/views/vend/vendDtlEvalStndDetail.js"></script> 
 
<div class="conts_wrap">
	<div class="inner">
		<div class="tit_wrap">
			<h3 class="tit">세부평가기준 상세보기</h3>
			<ul class="step_wrap">
				<li><a href="#">${bigMenuNm}</a></li>
				<li><a href="#">${smallMenuNm}</a></li>
			</ul> <!--// step_wrap E -->
		</div> <!--// tit_wrap E -->
		<div class="view_wrap typeB">
			<div class="view_area">
				<table>
					<colgroup>												
						<col style="width: 170px;">
						<col style="width: 349px;">
						<col style="width: 170px;">
						<col style="width: auto;">
					</colgroup>
					<tr height="24">
						<th>
							평가분야코드
						</th>
						<td>
							${vendDtlEvalStndDetail.EV_CODE1 }
						</td>
						<th>
							평가분야명
						</th>
						<td>
							협조도
						</td>
					</tr>
					<tr height="24">
						<th>
							평가항복코드
						</th>
						<td>
							${vendDtlEvalStndDetail.EV_CODE2 }
						</td>
						<th>
							평가항목명
						</th>
						<td>
							${vendDtlEvalStndDetail.EV_NAME1 }
						</td>
					</tr>
					<tr height="24">
						<th>
							배점
						</th>
						<td>
							${vendDtlEvalStndDetail.DIST_SCORE }
						</td>
						<th>
						</th>
						<td>
						</td>
					</tr>
					<tr height="24">
						<th>
							설명
						</th>
						<td  colspan="3">
							<textarea rows="5" cols="" style="width: 100%; overflow: hidden;" readonly="readonly">${vendDtlEvalStndDetail.REMARK }</textarea>
						</td>
					</tr>
				</table>
			</div>
			<h4 class="tit">세부평가기준</h4>
			<div class="view_area">
				<table>
	           		<colgroup>
						<col style="width: 10%;">
						<col style="width: 15%;">
						<col style="width: 10%;">
						<col style="width: auto;">
					</colgroup>
					<thead>
		                <tr>
	                    	<th class="txtc">순번</th>
							<th class="txtc">세부평가기준</th>
							<th class="txtc">배점</th>
							<th class="txtc">비고</th>
		                </tr>
		            </thead>
					<tbody>
						<c:forEach items="${ vendDtlEvalStndDetailList }" var="list" varStatus="loop">
							<tr>
								<td class="txtc">${list.EV_CODE3 }</td>
								<td class="txtl pl5">${list.EV_NAME3 }</td>
								<td class="txtc">${list.DIST_SCORE }</td>
								<td class="txtc">
									<textarea style="width: 100%; height: 50px; overflow: hidden;" readonly="readonly">${list.REMARK }</textarea>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<div class="btn_wrap view_btn">
		  		<button type="button" class="btn btn_02 btn_revise" id="modifyBtn">수정</button>
		  		<button type="button" class="btn btn_02 btn_revise" id="deleteBtn">삭제</button>
		  		<button type="button" class="btn btn_02 btn_sch" id="listBtn">목록</button>
	   		</div>	
   		</div>	
	</div>
</div>

<%-- DETAIL FORM --%>
<form id="modifyFrm" method="POST" >
	<input type="hidden" name="resourceName" value="${ param.resourceName }" >
	<input type="hidden" name="ev_code1" value="${vendDtlEvalStndDetail.EV_CODE1 }" >
	<input type="hidden" name="ev_code2" value="${vendDtlEvalStndDetail.EV_CODE2 }" >
</form> 
<form id="deleteFrm" method="POST" > 
	<input type="hidden" name="resourceName" value="${ param.resourceName }" >
	<input type="hidden" name="ev_code1" value="${vendDtlEvalStndDetail.EV_CODE1}">
	<input type="hidden" name="ev_code2" value="${vendDtlEvalStndDetail.EV_CODE2 }" >
</form>
<form id="listFrm" method="POST" >
	<input type="hidden" name="resourceName" value="${ param.resourceName }" >
</form> 
<form id="detailFrm" method="POST" > 
</form>