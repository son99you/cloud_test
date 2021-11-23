<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * sg설명 팝업창
 *
 * <pre>
 * vend 
 *    |_ sgPopup.jsp
 * 
 * </pre>
 * @date : 2017. 06. 13
 * @version : 1.0
 * @author : 은우소프트 홍찬일
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %> 
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %> 

<script type="text/javascript" src="${jsPath}/ipro/views/comm/popup/estmReview.js"></script>


<!-- Detail -->
<div class="layout-pop">
	<div class="pop_header">
			<div class="title">평가위원평가표</div>
		</div> <!--// pop_header E -->

				<!-- <div class="table-detail">
					Top
					<div class="top-detail">
						<div class="type-fleft">
							<div class="contents-label">기본정보</div>
						</div>
					</div> -->
					<!-- //Top -->

					<table class="component-detail-table type-line-none">
						<colgroup>
							<col width="150">
							<col width="*">
							<col width="150">
							<col width="*">
						</colgroup>
						<tbody>
						<tr>
							<th>평가절차구분</th>
							<td>&nbsp;</td>
							<th>&nbsp;</th>
							<td>&nbsp;</td>
						</tr>
						<tr>
							<th>
								<i class="icon-necessary"></i>서식명
							</th>
							<td colspan="3">
								&nbsp;
							</td>
						</tr>
						</tbody>
					</table>
					
					
				<!-- </div> -->

<!-- 				<div class="table-detail">
					Top
					<div class="top-detail">
						<div class="type-fleft">
							<div class="contents-label">평가항목</div>
						</div>
					</div>
					//Top -->
					<div id="tableDiv" style="margin-top: 20px;">
						<table class="component-detail-table type-line-none">
							<colgroup>
								<col style="width: auto;">
							</colgroup>
							<tbody id="copyArea">
							<tr>
								<th>상세내용</th>
							</tr>
							<tr>
							</tr>
							</tbody>
						</table>
 					</div>


				<!-- bottom button -->
				<div class="bottom-buttons">
					<a href="javascript:" id="closeBtn" class="btn-bottom type-a">닫기</a>
					
				</div>
				<!-- //bottom button -->
			</div>
			<!-- //Detail -->
		<!-- </div> -->

<form id="listFrm" method="POST">
	<input type="hidden" name="resourceName" value="${comFn:nvl(param.resourceName, resourceName)}" >
</form>