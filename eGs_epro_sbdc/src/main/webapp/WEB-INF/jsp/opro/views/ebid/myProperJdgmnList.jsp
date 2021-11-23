<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
입찰관리 >
 * 	 나의 적격심사 목록
 *
 * <pre>
 * ebid 
 *   	 |_ myProperJdgmnList.jsp
 * 
 * </pre>
 * @date : 2017. 06. 19.
 * @version : 1.0
 * @author : 은우소프트 이주연
--%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %>
<%@ taglib prefix="fc" uri="/WEB-INF/tlds/fwkComboBoxTag.tld" %>

<script type="text/javascript" src="${jsPath}/opro/views/ebid/myProperJdgmnList.js"></script>  

	<ul class="step_wrap">
	<li><a href="#">
		<c:if test="${param.resourceName eq 'opm1015' or param.resourceName eq 'opm2015' }">
			경쟁입찰
		</c:if>
		<c:if test="${param.resourceName eq 'opm302'  }">
			매각
		</c:if>

	</a></li>
	<li><a href="#">
			<c:if test="${param.resourceName eq 'opm1015' or param.resourceName eq 'opm2015' }">
			 나의 적격심사
			</c:if>
			<c:if test="${param.resourceName eq 'opm302'  }">
				나의매각참여
			</c:if>
		</a>
	</li>
</ul>
<div class="tit_wrap">
	<h3 class="tit">
		<c:if test="${param.resourceName eq 'opm1025' or param.resourceName eq 'opm2015'}">
			나의 적격심사
		</c:if>
		<c:if test="${param.resourceName eq 'opm302' }">
			나의매각참여
		</c:if>
	</h3>
</div>

<form id="searchFrm" class="search_form" method="POST" >
	<input type="hidden" id="resourceName" name="resourceName" value="${param.resourceName}">
	<input type="hidden" id="P_PAGE_NO" name="P_PAGE_NO" value="${comFn:nvl(param.P_PAGE_NO, 1)}">
	<input type="hidden" id="P_PAGE_SIZE" name="P_PAGE_SIZE" value="${comFn:nvl(param.P_PAGE_SIZE, 10)}">
    <fieldset>
		<legend>구매견적</legend>
        <div class="view_wrap typeA">	
        	<div class="view_area">
				<table>
					<caption>구매견적</caption>
					<colgroup>
						<col style="width: 15%;">
						<col style="width: auto;">
					</colgroup>
					<tbody>
						<tr>
							<th scope="row"><label for="plan">입찰명</label></th>
							<td>
								<input type="text" id="P_BID_NM_S" name="P_BID_NM_S" value="${param.P_BID_NM_S}" maxlength="600">
							</td>
						</tr>
						<tr>
							<th scope="row"><label for="division">진행상태</label></th>
							<td>
								<fc:fwkComboBox name="P_BID_PROGRS_STTUS_CD_S" id="P_BID_PROGRS_STTUS_CD_S" selectKey="${param.P_BID_PROGRS_STTUS_CD_S}" list="{'':'전체', 'PF50':'개찰','PF60':'낙찰','PF99':'유찰'}"></fc:fwkComboBox>
							</td>
						</tr>
						<tr>
							<th scope="row"><label for="division">공고일자</label></th>
							<td> 
								<div class="calendar_box">
									<input type="text" id="schedule1" title="발주예정시기" class="w170 datepicker1" id="P_PBLANC_BEGIN_DT_S" name="P_PBLANC_BEGIN_DT_S" value="${param.P_PBLANC_BEGIN_DT_S}">
									<span class="wave">~</span>
									<input type="text" id="schedule2" title="발주예정시기" class="w170 datepicker2" id="P_PBLANC_END_DT_S" name="P_PBLANC_END_DT_S" value="${param.P_PBLANC_END_DT_S}">
								</div> <!--// calendar_box E -->
								
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
		<div class="btn_wrap mt10">
			<button type="button" class="btn btn_m btn_c2" id="searchBtn">조회</button>
		</div> <!--// btn_wrap E -->
		<div class="list_wrap mt30">
			<div class="list_top">
				<p class="total">총 <span>${comFn:nvl(myProperJdgmnListTotcnt, 0)}</span>건</p>
				<div class="btn_right">
<!-- 						<button type="button" class="btn btn_02 btn_down">엑셀 다운로드</button> -->
				</div>
			</div> <!--// list_top E -->
			<div class="list_conts">
				<table class="tableList">
            <caption>낙찰자선정 목록</caption>
            <colgroup>
                <col width="60px"/>
                <col width="120px"/>
                <col width="40px"/>
                <col width="300px"/>
                <col width="100px"/>
                <col width="100px"/>
                <col width="120px"/>
                <%-- <col width="60px"/> --%>
            </colgroup>			
			<thead>
                <tr>
                    <th class="noBg">번호</th>
                    <th>공고번호</th>
                    <th>차수</th>
                    <th>입찰명</th>
                    <th>공고일자</th>
                    <th>신청마감일자</th>
                    <th>진행상태</th>
                </tr>
            </thead>
			<tbody>
				<c:if test="${comFn:nvl(myProperJdgmnListTotcnt, 0) == 0}">
					<tr class="row">
						<td colspan="7">검색 조건에 해당하는 데이터가 존재하지 않습니다.</td>
					</tr>
				</c:if>
				<c:if test="${myProperJdgmnListTotcnt > 0}">
					<c:forEach var="data" items="${myProperJdgmnList}" varStatus="status" >
						<tr class="row"
								onclick="myProperJdgmnDetail('${data.PBLANC_NO}','${data.PBLANC_ODR }');"
							  style="cursor: pointer;">
							<td>${data.RNUM}&nbsp;</td>
							<td>${data.PBLANC_NO}&nbsp;</td>
							<td>${data.PBLANC_ODR}&nbsp;</td>
							<td class="txtl pl20" title="${data.BID_NM}">${data.BID_NM}&nbsp;</td>
							<td>${comFn:formatDate(data.PBLANC_DT,'yyyyMMddHHmmss','yyyy-MM-dd')}&nbsp;</td>
							<td><%-- ${comFn:formatDate(data.OPENG_DT,'yyyyMMddHHmmss','yyyy-MM-dd')} --%>&nbsp;</td>
							<td><div class="btn_before">${data.BID_PROGRS_STTUS_NM }</div></td>
						</tr>
					</c:forEach>
				</c:if>
			</tbody>
		</table>
		
			</div> <!--// list_conts E -->
			<div class="list_bottom">
				<div class="list_pager">
					<comTag:paging totalCount="${myProperJdgmnListTotcnt}" pageNo="${comFn:nvl(param.P_PAGE_NO, 1)}" pageSize="${comFn:nvl(param.P_PAGE_SIZE, 10)}" clickPage="pageObj.clickPage"/>
				</div> <!--// list_pager E -->
				<div class="list_btn">
<!-- 					<button type="button" class="btn btn_m btn_orange" id="intrstBidRegistBtn">관심입찰등록</button> -->
				</div> <!--// btn_wrap E -->
			</div> <!--// list_bottom E -->
		</div> <!--// list_wrap E -->
  		</fieldset>		
</form> 
 
<form id="viewFrm" method="POST">
	<input type="hidden" name="P_PBLANC_NO" />
	<input type="hidden" name="P_PBLANC_ODR"/>
	<input type="hidden" name="resourceName" value="${param.resourceName}">
</form>

<form id="registFrm" method="POST">
	<input type="hidden" name="resourceName" value="${param.resourceName}">
</form>
