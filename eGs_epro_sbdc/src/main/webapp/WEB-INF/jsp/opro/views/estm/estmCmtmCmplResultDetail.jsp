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

<script type="text/javascript" src="${jsPath}/opro/views/estm/estmCmtmCmplResultDetail.js"></script>

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
					<li style="font-size: 30px; font-weight: 500;">평가진행완료 상세</li>
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
				<li>
					<a href="javascript:tabEvent(2);">서류평가</a>
				</li>
				<li>
					<a href="javascript:tabEvent(3);">품평회</a>
				</li>
				<li class="is-selected">
					<a href="javascript:tabEvent(4);">평가결과</a>
				</li>
				<li>
					<a href="javascript:tabEvent(5);">화상회의</a>
				</li>
			</ul>
		</div>
		<!-- //tab -->
		
		<!-- 평가결과인경우 -->
		<div class="table-detail">
			<!-- Top -->
			<div class="top-detail">
				<div class="type-fleft">
					<div class="contents-label">평가결과목록</div>
				</div>
			</div>
			<!-- //Top -->
			<table class="component-detail-table ">
				<colgroup>
					<col width="80px;">
					<col width="200px;">
					<col width="auto;">
					<col width="80px;">
					<col width="100px;">
					<col width="100px;">
					<col width="80px;">
					<col width="80px;">
				</colgroup>
				<thead>
					<tr>
						<th rowspan="2" style="vertical-align: middle;">순위</th>
						<th rowspan="2" style="vertical-align: middle;">평가대상명</th>
						<th colspan="4">평가위원</th>
						<th rowspan="2" style="vertical-align: middle;">총점</th>
						<th rowspan="2" style="vertical-align: middle;">일자리평가점수</th>
						<th rowspan="2" style="vertical-align: middle;">가점</th>
						<th rowspan="2" style="vertical-align: middle;">최종점수</th>
						<th rowspan="2" style="vertical-align: middle;">합격여부</th>
					</tr>
					<tr>
						<th style="width: 25%;">평가위원1</th>
						<th style="width: 25%;">평가위원2</th>
						<th style="width: 25%;">평가위원3</th>
						<th style="width: 25%;">평가위원4</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td class="txt-center">1</td>
						<td onclick="detailInqirePopup();" style="cursor: pointer;">주식회사 홈세라</td>
						<td class="txt-center">62</td>
						<td class="txt-center">66</td>
						<td class="txt-center">56</td>
						<td class="txt-center">77</td>
						<td class="txt-center">66.6</td>
						<td class="txt-center">17</td>
						<td class="txt-center">5</td>
						<td class="txt-center">86.6</td>
						<td class="txt-center">합격</td>
					</tr>
					<tr>
						<td class="txt-center">2</td>
						<td onclick="detailInqirePopup();" style="cursor: pointer;">베이비로하</td>
						<td class="txt-center">60</td>
						<td class="txt-center">77</td>
						<td class="txt-center">56</td>
						<td class="txt-center">75</td>
						<td class="txt-center">68.8</td>
						<td class="txt-center">16</td>
						<td class="txt-center">2</td>
						<td class="txt-center">86.8</td>
						<td class="txt-center">합격</td>
					</tr>
					<tr>
						<td class="txt-center">3</td>
						<td onclick="detailInqirePopup();" style="cursor: pointer;">두배</td>
						<td class="txt-center">63</td>
						<td class="txt-center">63</td>
						<td class="txt-center">55</td>
						<td class="txt-center">63</td>
						<td class="txt-center">64.6</td>
						<td class="txt-center">18</td>
						<td class="txt-center">4</td>
						<td class="txt-center">83.4</td>
						<td class="txt-center">합격</td>
					</tr>
					<tr>
						<td class="txt-center">4</td>
						<td onclick="detailInqirePopup();" style="cursor: pointer;">(주)정원</td>
						<td class="txt-center">56</td>
						<td class="txt-center">79</td>
						<td class="txt-center">72</td>
						<td class="txt-center">58</td>
						<td class="txt-center">68.4</td>
						<td class="txt-center">14</td>
						<td class="txt-center">2</td>
						<td class="txt-center">84.4</td>
						<td class="txt-center">합격</td>
					</tr>
					<tr>
						<td class="txt-center">5</td>
						<td onclick="detailInqirePopup();" style="cursor: pointer;">주식회사 더라이크스킨</td>
						<td class="txt-center">59</td>
						<td class="txt-center">68</td>
						<td class="txt-center">75</td>
						<td class="txt-center">62</td>
						<td class="txt-center">66.4</td>
						<td class="txt-center">15</td>
						<td class="txt-center">2</td>
						<td class="txt-center">83.4</td>
						<td class="txt-center">불합격</td>
					</tr>
					<tr>
						<td class="txt-center">6</td>
						<td onclick="detailInqirePopup();" style="cursor: pointer;">코스티</td>
						<td class="txt-center">55</td>
						<td class="txt-center">65</td>
						<td class="txt-center">73</td>
						<td class="txt-center">56</td>
						<td class="txt-center">63.2</td>
						<td class="txt-center">14</td>
						<td class="txt-center">2</td>
						<td class="txt-center">79.2</td>
						<td class="txt-center">불합격</td>
					</tr>
					<tr>
						<td class="txt-center">7</td>
						<td onclick="detailInqirePopup();" style="cursor: pointer;">㈜ 이던</td>
						<td class="txt-center">69</td>
						<td class="txt-center">72</td>
						<td class="txt-center">66</td>
						<td class="txt-center">60</td>
						<td class="txt-center">64.6</td>
						<td class="txt-center">12</td>
						<td class="txt-center">2</td>
						<td class="txt-center">78.6</td>
						<td class="txt-center">불합격</td>
					</tr>
					<tr>
						<td class="txt-center">8</td>
						<td onclick="detailInqirePopup();" style="cursor: pointer;">뿌뿌를루</td>
						<td class="txt-center">58</td>
						<td class="txt-center">76</td>
						<td class="txt-center">67</td>
						<td class="txt-center">56</td>
						<td class="txt-center">62.4</td>
						<td class="txt-center">15</td>
						<td class="txt-center">1</td>
						<td class="txt-center">78.4</td>
						<td class="txt-center">불합격</td>
					</tr>
				</tbody>
			</table>
		</div>
		<!-- //평가결과인경우 -->
		
		<!-- bottom button -->
		<div class="bottom-buttons">
			<a href="javascript:" class="btn-bottom type-a" id="listBtn">목록</a>
		</div>
		<!-- //bottom button -->
		
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