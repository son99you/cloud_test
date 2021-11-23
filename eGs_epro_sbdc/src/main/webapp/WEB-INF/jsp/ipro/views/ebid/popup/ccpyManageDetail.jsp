<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 입찰현황 > 입찰공고문 상세 팝업
 *
 * <pre>
 * ebid 
 *    |_ popup
               |_ bidWrtancDetail.jsp
 * 
 * </pre>
 * @date : 2017. 06. 19
 * @version : 1.0
 * @author : 은우소프트 전상훈
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %> 
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %> 

<script type="text/javascript" src="${jsPath}/ipro/views/ebid/popup/ccpyManageDetail.js"></script> 
 
<div class="pop_wrap">
	<div class="pop_header">
		<h1 class="tit">협력업체관리 상세</h1>
	</div> <!--// pop_header E -->
	<div class="pop_container">
		<div class="view_wrap typeC">
			<div class="tit_area">
				<h2 class="tit01">기본정보</h2>
			</div> <!--// tit_area E -->
			<div class="view_area">
				<table class="table">
					<colgroup>
						<col width="15%" >
						<col width="35%" >
						<col width="15%" >
						<col width="35%" >
					</colgroup>
					<tr height="24">
						<th>회사명</th>
						<td>은우소프트</td>
						<th>담당자성명(직위)</th>
						<td>김기우 과장</td>
					</tr>
					<tr height="24">
					    <th>사업자등록번호</th>
						<td>119-86-02801</td>
						<th>취급종목</th>
						<td>SW</td>
					</tr>
					<tr height="24">
					    <th>본사사업자번호</th>
						<td></td>
					    <th>법인번호</th>
						<td></td>
					</tr>
					<tr height="24">
					    <th>대표이사</th>
						<td>정한규</td>
						<th>등록구분</th>
						<td>정등록</td>
					</tr>
					<tr height="24">
					    <th>업태</th>
						<td></td>
						<th>종목</th>
						<td></td>
					</tr>
				</table>
			</div>
			
			<div class="tit_area">
				<h2 class="tit01">계약실적</h2>
			</div> <!--// tit_area E -->	
			<div class="view_area">
				<table class="table">
					<colgroup>
						<col class="txtc" width="8%">
						<col class="txtc" width="5%">
						<col class="txtc" width="8%">
						<col class="txtc" width="*">
						<col class="txtc" width="10%">
						<col class="txtc" width="10%">
						<col class="txtc" width="10%">
						<col class="txtc" width="10%">
						<col class="txtc" width="10%">
						<col class="txtc" width="10%">
					</colgroup>
					<thead>
					<tr height="24">
						<th class="txtc">계약번호</th>
						<th class="txtc">차수</th>
						<th class="txtc">계약유형</th>
						<th class="txtc">계약명</th>
						<th class="txtc">계약금액</th>
						<th class="txtc">공급가</th>
						<th class="txtc">부가세</th>
						<th class="txtc">계약일자</th>
						<th class="txtc">계약시작일</th>
						<th class="txtc">계약종료일</th>
					</tr>
					</thead>
					<tr height="24">
						<td class="txtc">30000099</td>
						<td class="txtc">0</td>
						<td class="txtc">용역</td>
						<td class="txtl" title="2017년 차량팀 계측기류 검교정 용역요청"><div class="txtover">2017년 차량팀 계측기류 검교정 용역요청</div></td>
						<td class="txtr">4,356,000</td>
						<td class="txtr">4,791,600</td>
						<td class="txtr">435,600</td>
						<td class="txtc">2017-06-17</td>
						<td class="txtc">2017-06-17</td>
						<td class="txtc">2017-06-20</td>
					</tr>
					<tr height="24">
						<td class="txtc">30000098</td>
						<td class="txtc">0</td>
						<td class="txtc">물품</td>
						<td class="txtl" title="전차선분야 유지보수용 공기구류 22종"><div class="txtover">전차선분야 유지보수용 공기구류 22종</div></td>
						<td class="txtr">15,950,000</td>
						<td class="txtr">17,545,000</td>
						<td class="txtr">1,595,000</td>
						<td class="txtc">2017-04-05</td>
						<td class="txtc">2017-04-05</td>
						<td class="txtc">2017-04-10</td>
					</tr>
					<tr height="24">
						<td class="txtc">30000097</td>
						<td class="txtc">0</td>
						<td class="txtc">공사</td>
						<td class="txtl" title="청계산입구역 2번출구 캐노피 유리 교체 공사"><div class="txtover">청계산입구역 2번출구 캐노피 유리 교체 공사</div></td>
						<td class="txtr">2,310,000</td>
						<td class="txtr">2,541,000</td>
						<td class="txtr">231,000</td>
						<td class="txtc">2017-05-10</td>
						<td class="txtc">2017-05-15</td>
						<td class="txtc">2017-05-19</td>
					</tr>
				</table>
			</div>
			
			<div class="tit_area">
				<h2 class="tit01">입찰정보</h2>
			</div> <!--// tit_area E -->
			<div class="view_area">
				<table class="table">
					<colgroup>
						<col width="15%" class="txtc">
						<col width="55%" class="txtc">
						<col width="15%" class="txtc">
						<col width="15%" class="txtc">
					</colgroup>
					<thead>
						<tr height="24">
							<th class="txtc">공고번호</th>
							<th class="txtc">공고명</th>
							<th class="txtc">입찰일자</th>
							<th class="txtc">낙찰여부</th>
						</tr>
					</thead>
					<tr height="24">
						<td class=" txtc">201701-001</td>
						<td class=" txtl">통신분야 풍수해대비 자재 구매</td>
						<td class=" txtc">2017-01-25 17:52</td>
						<td class=" txtc"></td>
					</tr>
					<tr height="24">
						<td class=" txtc">201702-001</td>
						<td class=" txtl">통신분야 계측기류 검교정 용역</td>
						<td class=" txtc">2017-02-08 15:20</td>
						<td class=" txtc">Y</td>
					</tr>
					<tr height="24">
						<td class=" txtc">201702-002</td>
						<td class=" txtl">신호분야 응급복구함 제작</td>
						<td class=" txtc">2017-02-11 18:50</td>
						<td class=" txtc"></td>
					</tr>
					<tr height="24">
						<td class=" txtc">201705-001</td>
						<td class=" txtl">청계산입구역 에어브러쉬 구조변경</td>
						<td class=" txtc">2017-05-11 14:50</td>
						<td class=" txtc">Y</td>
					</tr>
				</table>
			</div>
	
			<div class="btn_wrap view_btn">
		    	<button type="button" class="btn btn_02 btn_close" name="closeBtn" >닫기</button>
		    </div>
		</div>
	</div>
</div> <!--// content E-->
