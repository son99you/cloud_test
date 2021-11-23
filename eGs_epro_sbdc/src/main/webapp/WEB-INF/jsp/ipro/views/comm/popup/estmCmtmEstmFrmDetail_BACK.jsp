<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 평가관리 > 평가위원평가내용 팝업 상세
 *
 * <pre>
 * estm/popup 
 *    |_ estmFrmDetail.jsp
 * 
 * </pre>
 * @date : 2018. 08. 22
 * @version : 1.0
 * @author : 은우소프트 은잔디
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld"%>
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld"%>

<script type="text/javascript" src="${jsPath}/ipro/views/comm/popup/estmCmtmEstmFrmDetail.js"></script>
<script type="text/javascript" src="/raonkeditor/js/raonkeditor.js"></script>


<div class="layout-pop">
	<form id="searchFrm" class="search_form" method="POST">
		<input type="hidden" id="P_PAGE_NO" name="P_PAGE_NO" value="${comFn:nvl(param.P_PAGE_NO, 1)}">
		<input type="hidden" id="P_PAGE_SIZE" name="P_PAGE_SIZE" value="${comFn:nvl(param.P_PAGE_SIZE, 10)}">
		<input type="hidden" id="setMulti" name="setMulti" value="${param.setMulti}">
		<input type="hidden" id="P_GBN" name="P_GBN" value="${comFn:nvl(param.P_GBN, GBN)}">
		<input type="hidden" id="P_FRM_GBN" name="P_FRM_GBN" value="${param.P_FRM_GBN}">
		
		<div class="pop_header">
			<div class="title">평가위원평가표</div>
		</div> <!--// pop_header E -->
		
		<table class="component-detail-table type-line-none">
			<colgroup>
				<col width="20%">
				<col width="30%">
				<col width="20%">
				<col width="30%">
			</colgroup>
			<tbody>
			<tr>
				<th>평가절차구분</th>
				<td>비계량</td>
				<th>&nbsp;</th>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<th>
					<i class="icon-necessary"></i>서식명
				</th>
				<td colspan="3">
					2021년 2분기 비계량평가서식
				</td>
			</tr>
			</tbody>
		</table>
		
		<div style="height: 10px;"></div>
		
		<div id="tableDiv">
			<table class="component-detail-table type-line-none">
				<colgroup>
					<col style="width: auto;">
				</colgroup>
				<tbody id="copyArea">
				<tr>
					<th>평가항목명</th>
				</tr>
				<tr>
				</tr>
				</tbody>
			</table>
		</div>
		
		<div style="height: 10px;"></div>
		
		<table class="component-detail-table type-line-none" style="float: right; width:150px;">
			<colgroup>
				<col width="50%">
				<col width="50%">
			</colgroup>
			<tbody>
			<tr>
				<th>합계</th>
				<td><input type="text" id="P_TOT_SCR" name="P_TOT_SCR" class="component-input type-full"  placeholder=""></td>
			</tr>
<!-- 			<tr>
				<td>
					<label class="component-radio">
						<input type="radio"  name="" checked disabled="disabled" id="radioId" value="" >
						<i></i>
					</label>
				</td>
			</tr> -->
			</tbody>
		</table>
		
		<div style="height: 10px;"></div>
		
		
		<div class="bottom-buttons">
			<button id="colseBtn" class="btn-bottom type-a">닫기</button>
		</div>
	  <!-- pageing -->
	 <%--  <div class="component-pageing">
	      <comTag:pagingIpro totalCount="${comFn:nvl(deptInqireListTotcnt,0)}" pageNo="${comFn:nvl(param.P_PAGE_NO, 1)}" pageSize="${comFn:nvl(param.P_PAGE_SIZE, 10)}" clickPage="pageObj.clickPage"/>
	  </div> --%>
	  <!--//pageing -->
	</form>
</div> 

<%-- <!-- Detail -->
<div class="area-detail">
	<div class="table-detail">
		<!-- Top -->
		<div class="top-detail">
			<div class="type-fleft">
				<div class="contents-label">기본정보</div>
			</div>
		</div>
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
				<td>비계량</td>
				<th>&nbsp;</th>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<th>
					<i class="icon-necessary"></i>서식명
				</th>
				<td colspan="3">
					2021년 2분기 비계량평가서식
				</td>
			</tr>
			</tbody>
		</table>
	</div>

	<div class="table-detail">
		<!-- Top -->
		<div class="top-detail">
			<div class="type-fleft">
				<div class="contents-label">평가항목</div>
			</div>
		</div>
		<!-- //Top -->
		<div id="tableDiv">
			<table class="component-detail-table type-line-none">
				<colgroup>
					<col style="width: auto;">
				</colgroup>
				<tbody id="copyArea">
				<tr>
					<th>평가항목명</th>
				</tr>
				<tr>
				</tr>
				</tbody>
			</table>
		</div>
	</div>


	<!-- bottom button -->
	<div class="bottom-buttons">
		<a href="javascript:" id="listBtn" class="btn-bottom type-a">목록</a>
		<a href="javascript:" id="updtBtn" class="btn-bottom type-b">수정</a>
	</div>
	<!-- //bottom button -->
</div>
<!-- //Detail -->
</div> --%>

<form id="listFrm" method="POST">
	<input type="hidden" name="resourceName" value="${comFn:nvl(param.resourceName, resourceName)}" >
</form> 

<form id="downloadFrm" method="POST">
	<input type="hidden" name="P_FILE_GRP_NO" value="" >
	<input type="hidden" name="P_FILE_SN" value="" >
</form> 

<form id="updtFrm" method="POST">
	<input type="hidden" name="resourceName" value="${comFn:nvl(param.resourceName, resourceName)}" >
	<input type="hidden" name="P_FRM_NO" id="P_FRM_NO" value="${contFormDetail.FRM_NO}">
	<input type="hidden" name="P_VRSN" id="P_VRSN" value="${contFormDetail.VRSN}">
</form>

<form id="ubiPopupFrm" method="POST" > 
	<input type="hidden" name="P_JRF" value=""/>
	<input type="hidden" name="P_ARG" value=""/>
	<input type="hidden" name="P_FRM_NO" value="${contFormDetail.FRM_NO}">
</form>

<form id="popupFrm" method="POST"> 
	<input type="hidden" name="resourceName" value="${comFn:nvl(param.resourceName, resourceName)}" >
	<input type="hidden" name="P_FILE_GRP_NO" >
</form>

