<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 평가관리 > 평가진행현황 상세 - 평가대상
 *
 * <pre>
 * estm
 *    |_ estmProgObjDetail.jsp
 * 
 * </pre>
 * @date : 2021. 03. 22.
 * @version : 1.0
 * @author : 은우소프트
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %> 
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %> 

<script type="text/javascript" src="${jsPath}/opro/views/estm/estmProgProcdADetail.js"></script>

<!-- 네비게이션 -->
<div class="area-detail">
	<div class="table-detail">
		<!-- Top -->
		<div class="nav_sec">

			<div class="btn-help" style="display:none;">
				<a href="javascript:helpPopup();">도움말</a>
			</div>
			
			<div class="option-left">
				<ul class="location">
					<li style="font-size: 30px; font-weight: 500;">평가진행진행 상세</li>
				</ul>
			</div>
			
			<div class="option-right">
				<ul class="location">
					<li class="home"><a href="#">홈</a></li>
					<li><a href="#">${myMenuList.bigMenuNm}</a></li>
					<li><a href="#">${myMenuSubList.smallMenuNm}</a></li>
				</ul>
			</div>
		</div>
	</div>
</div>
<!--//네비게이션 --> 

<!-- Detail -->
<div class="area-detail">
	<form id="detailFrm" name="detailFrm" method="POST">
		<input type="hidden" name="P_REGR_NM" value="${sessionScope.loginResult.USR_NM}">
		<input type="hidden" name="P_USR_ID" value="${sessionScope.loginResult.USR_ID}">
		<input type="hidden" name="resourceDesc" value="${ param.resourceDesc}">
		<input type="hidden" name="resourceName" value="${comFn:nvl(param.resourceName, resourceName)}">
		<input type="hidden" name="P_ESTM_PROCD_SEQ" value="${param.P_ESTM_PROCD_SEQ}">
		
		<!-- 필수정보 고정 -->
		<div class="table-detail">
			<table class="component-detail-table type-line-none">
				<colgroup>
					<col width="15%">
					<col width="35%">
					<col width="15%">
					<col width="35%">
				</colgroup>
				<tbody>
					<tr>
						<th>평가번호</th>
						<td></td>
						<th>평가진행상태</th>
						<td></td>
					</tr>
					<tr>
						<th>평가명</th>
						<td colspan="3"></td>
					</tr>
					<tr>
						<th>평가구분</th>
						<td></td>
						<th>평가정보연계번호</th>
						<td></td>
					</tr>
					<tr>
						<th>평가분야구분</th>
						<td></td>
						<th>평가대상구분</th>
						<td></td>
					</tr>
				</tbody>
			</table>
		</div>
		<!-- //필수정보 고정 -->
		
		
		<!-- tab -->
		<div class="component-tab-line">
			<ul class="list-tab">
				<li>
					<a href="javascript:tabEvent(1);">기본정보</a>
				</li>
				<c:if test="${param.P_ESTM_PROCD_SEQ eq '1' }">
				<li class="is-selected">
					<a href="javascript:tabEvent(2);">서류평가</a>
				</li>
				<li>
					<a href="javascript:tabEvent(3);">품평회</a>
				</li>
				</c:if>
				<c:if test="${param.P_ESTM_PROCD_SEQ eq '2' }">
				<li>
					<a href="javascript:tabEvent(2);">서류평가</a>
				</li>
				<li class="is-selected">
					<a href="javascript:tabEvent(3);">품평회</a>
				</li>
				</c:if>
				<li class="">
					<a href="javascript:tabEvent(4);">화상회의</a>
				</li>
			</ul>
		</div>
		<!-- //tab -->
		
		<c:if test="${param.P_ESTM_PROCD_SEQ eq '1' }">
		<!-- 서류평가인경우 -->
		<div class="table-detail">
			<!-- Top -->
			<div class="top-detail">
				<div class="type-fleft">
					<div class="contents-label">서류평가목록</div>
				</div>

				<!-- <div class="type-fright">
					<a href="javascript:" class="component-button-s type-add">대상추가</a>
					<a href="javascript:" class="component-button-s type-del">대상삭제</a>
				</div> -->
			</div>
			<!-- //Top -->

			<table class="component-detail-table ">
				<colgroup>
					<col width="80px;">
					<col width="80px;">
					<col width="250px;">
					<col width="auto;">
					<col width="120px;">
				</colgroup>
				<thead>
					<tr>
						<th rowspan="2" style="vertical-align: middle;">순위</th>
						<th rowspan="2" style="vertical-align: middle;">구분</th>
						<th rowspan="2" style="vertical-align: middle;">기업명</th>
						<th rowspan="2" style="vertical-align: middle;">총점</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td class="txt-center"></td>
						<td class="txt-center">1</td>
						<td>㈜ 이던</td>
						<td class="txt-center">85.25</td>
					</tr>
					<tr>
						<td class="txt-center"></td>
						<td class="txt-center">2</td>
						<td>베이비로하</td>
						<td class="txt-center">78.25</td>
					</tr>
					<tr>
						<td class="txt-center"></td>
						<td class="txt-center">3</td>
						<td>뿌뿌를루</td>
						<td class="txt-center">75.25</td>
					</tr>
					<tr>
						<td class="txt-center"></td>
						<td class="txt-center">4</td>
						<td>주식회사 홈세라</td>
						<td class="txt-center">70</td>
					</tr>
					<tr>
						<td class="txt-center"></td>
						<td class="txt-center">5</td>
						<td>두배</td>
						<td class="txt-center">81.5</td>
					</tr>
					<tr>
						<td class="txt-center"></td>
						<td class="txt-center">6</td>
						<td>주식회사 더라이크스킨</td>
						<td class="txt-center">81.25</td>
					</tr>
					<tr>
						<td class="txt-center"></td>
						<td class="txt-center">7</td>
						<td>코스티</td>
						<td class="txt-center">82.5</td>
					</tr>
					<tr>
						<td class="txt-center"></td>
						<td class="txt-center">8</td>
						<td>(주)정원</td>
						<td class="txt-center">83</td>
					</tr>
				</tbody>
			</table>
		</div>
		<!-- //서류평가인경우 -->
		</c:if>
		
		
		<c:if test="${param.P_ESTM_PROCD_SEQ eq '2' }">
		<!-- 서류평가인경우 -->
		<div class="table-detail">
			<!-- Top -->
			<div class="top-detail">
				<div class="type-fleft">
					<div class="contents-label">품평회목록</div>
				</div>

				<!-- <div class="type-fright">
					<a href="javascript:" class="component-button-s type-add">대상추가</a>
					<a href="javascript:" class="component-button-s type-del">대상삭제</a>
				</div> -->
			</div>
			<!-- //Top -->

			<table class="component-detail-table ">
				<colgroup>
					<col width="80px;">
					<col width="80px;">
					<col width="250px;">
					<col width="auto;">
					<col width="120px;">
				</colgroup>
				<thead>
					<thead>
					<tr>
						<th rowspan="2" style="vertical-align: middle;">순위</th>
						<th rowspan="2" style="vertical-align: middle;">구분</th>
						<th rowspan="2" style="vertical-align: middle;">기업명</th>
						<th rowspan="2" style="vertical-align: middle;">총점</th>
					</tr>
				</thead>
				</thead>
				<tbody>
					<tr>
						<td class="txt-center"></td>
						<td class="txt-center">1</td>
						<td>㈜ 이던</td>
						<td class="txt-center" onclick="detailInqirePopup();" style="cursor: pointer;">82</td>
						<td class="txt-center" onclick="detailInqirePopup();" style="cursor: pointer;">79</td>
						<td class="txt-center" onclick="detailInqirePopup();" style="cursor: pointer;">92</td>
						<td class="txt-center" onclick="detailInqirePopup();" style="cursor: pointer;">88</td>
						<td class="txt-center">85.25</td>
					</tr>
					<tr>
						<td class="txt-center"></td>
						<td class="txt-center">2</td>
						<td>베이비로하</td>
						<td class="txt-center">78.25</td>
					</tr>
					<tr>
						<td class="txt-center"></td>
						<td class="txt-center">3</td>
						<td>뿌뿌를루</td>
						<td class="txt-center">75.25</td>
					</tr>
					<tr>
						<td class="txt-center"></td>
						<td class="txt-center">4</td>
						<td>주식회사 홈세라</td>
						<td class="txt-center">70</td>
					</tr>
					<tr>
						<td class="txt-center"></td>
						<td class="txt-center">5</td>
						<td>두배</td>
						<td class="txt-center" onclick="detailInqirePopup();" style="cursor: pointer;">90</td>
						<td class="txt-center" onclick="detailInqirePopup();" style="cursor: pointer;">67</td>
						<td class="txt-center" onclick="detailInqirePopup();" style="cursor: pointer;">83</td>
						<td class="txt-center" onclick="detailInqirePopup();" style="cursor: pointer;">86</td>
						<td class="txt-center">81.5</td>
					</tr>
					<tr>
						<td class="txt-center"></td>
						<td class="txt-center">6</td>
						<td>주식회사 더라이크스킨</td>
						<td class="txt-center">81.25</td>
					</tr>
					<tr>
						<td class="txt-center"></td>
						<td class="txt-center">7</td>
						<td>코스티</td>
						<td class="txt-center">82.5</td>
					</tr>
					<tr>
						<td class="txt-center"></td>
						<td class="txt-center">8</td>
						<td>(주)정원</td>
						<td class="txt-center">83</td>
					</tr>
				</tbody>
			</table>
		</div>
		<!-- //서류평가인경우 -->
		</c:if>
		
		
		
	</form>
	
</div>
<!-- //Detail -->


<!-- LIST FORM -->
<form id="listFrm" method="POST">
	<input type="hidden" name="resourceName" value="${comFn:nvl(param.resourceName, resourceName)}">
</form>

<form id="popupFrm" method="POST"> 
	<input type="hidden" name="resourceName" value="${comFn:nvl(param.resourceName, resourceName)}" >
	<input type="hidden" name="P_FILE_GRP_NO" >
</form>