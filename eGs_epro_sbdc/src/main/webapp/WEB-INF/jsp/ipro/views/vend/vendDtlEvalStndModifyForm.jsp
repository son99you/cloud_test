<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 세부평가기준 수정(신분당선 세부평가기준)
 *
 * <pre>
 * vend
 *    |_ vendDtlEvalStndModify.jsp
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

<script type="text/javascript" src="${jsPath}/ipro/views/vend/vendDtlEvalStndModifyForm.js"></script> 
 
<div class="conts_wrap">
	<div class="inner">
		<div class="tit_wrap">
			<h3 class="tit">세부평가기준 수정</h3>
			<ul class="step_wrap">
				<li><a href="#">${bigMenuNm}</a></li>
				<li><a href="#">${smallMenuNm}</a></li>
			</ul> <!--// step_wrap E -->
		</div> <!--// tit_wrap E -->
   		<form id="modifyFrm" method="POST" > 
			<div class="view_wrap typeA">
				<div class="view_area">
					<table>
						<colgroup>												
							<col style="width: 170px;">
							<col style="width: 349px;">
							<col style="width: 170px;">
							<col style="width: auto;">
						</colgroup>
						<tr height="24">
							<th class=" txtl">
								
								평가분야코드
							</th>
							<td>
								${vendDtlEvalStndDetail.EV_CODE1 }
								<input type="hidden" name="ev_code1" value="${vendDtlEvalStndDetail.EV_CODE1 }">
							</td>
							<th class=" txtl">
								
								평가분야명
							</th>
							<td>
								협조도
							</td>
						</tr>
						<tr height="24">
							<th class=" txtl">
								
								평가항복코드
							</th>
							<td>
								${vendDtlEvalStndDetail.EV_CODE2 }
								<input type="hidden" name="ev_code2" value="${vendDtlEvalStndDetail.EV_CODE2 }">
							</td>
							<th class=" txtl">
								
								평가항목명
							</th>
							<td>
								${vendDtlEvalStndDetail.EV_NAME1 }
							</td>
						</tr>
						<tr height="24">
							<th class=" txtl">
								
								배점
							</th>
							<td>
								${vendDtlEvalStndDetail.DIST_SCORE }
							</td>
							<th class=" txtl">
								
							</th>
							<td>
							</td>
						</tr>
						<tr height="24">
							<th class=" txtl">
								
								설명
							</th>
							<td  colspan="3">
								<textarea style="width: 100%; height:50px; overflow: hidden;" readonly="readonly">${vendDtlEvalStndDetail.REMARK }</textarea>
							</td>
						</tr>
					</table>
				</div>
				<div class="tit_area">
					<h4 class="tit" style="clear: both;">세부평가기준</h4>
					<div class="btn_right">
				  		<button type="button" class="btn btn_02 btn_c2" id="addBtn">추가</button>
					</div>
				</div>
				<div class="view_area">
					<table>
			     		<colgroup>
							<col style="width: 10%;">
							<col style="width: 15%;">
							<col style="width: 10%;">
							<col style="width: auto;">
							<col style="width: 10%;">
						</colgroup>
						<thead>
			                <tr>
			                   	<th class="txtc">순번</th>
								<th class="txtc">세부평가기준</th>
								<th class="txtc">배점</th>
								<th class="txtc">비고</th>
								<th class="txtc">삭제</th>
			                </tr>
			            </thead>
						<tbody>
							<tr id="copyTrget" style="display: none;">
								<td class="txtc"><font color="red"><b>자동생성</b></font> <input type="hidden" name="ev_code3" value=""></td>
								<td class="txtc"><input type="text" name="ev_name3" value=""></td>
								<td class="txtc"><input type="text" name="dist_score3" numeric value=""></td>
								<td class="txtc">
									<textarea style="width: 100%; height:50px; overflow: hidden;" name="remark"></textarea>
								</td>
								<td  class="txtc">
									<button type="button" class='btn btn_02 btn_sch' id='deleteBtn' onclick='deleteFn(this);' >삭제</button>
								</td>
							</tr>
						</tbody>
						<tbody id="copyArea">
							<c:forEach items="${ vendDtlEvalStndDetailList }" var="list" varStatus="loop">
								<tr>
									<td class="txtc">${list.EV_CODE3 }<input type="hidden" name="ev_code3" value="${list.EV_CODE3 }"></td>
									<td class="txtc"><input type="text" name="ev_name3" value="${list.EV_NAME3 }"></td>
									<td class="txtc"><input type="text" name="dist_score3" numeric value="${list.DIST_SCORE }"></td>
									<td class="txtc">
										<textarea style="width: 100%; height: 50px; overflow: hidden;" name="remark">${list.REMARK }</textarea>
									</td>
									<td  class="txtc">
										<button type="button" class='btn btn_02 btn_sch' id='deleteBtn' onclick='deleteFn(this);' >삭제</button>
									</td>
								</tr>	
							</c:forEach>
						</tbody>
					</table>
				</div>
				<div class="btn_wrap view_btn">
			  		<button type="button" class="btn btn_02 btn_revise" id="saveBtn">저장</button>
			  		<button type="button" class="btn btn_02 btn_sch" id="listBtn">취소</button>
		   		</div>
			</div>
		</form>
	</div>
</div>

<%-- DETAIL FORM --%>
<form id="detailFrm" method="POST" > 
	<input type="hidden" name="resourceName" value="${ param.resourceName }" >
	<input type="hidden" name="ev_code1" value="${vendDtlEvalStndDetail.EV_CODE1 }" >
	<input type="hidden" name="ev_code2" value="${vendDtlEvalStndDetail.EV_CODE2 }" >
</form>