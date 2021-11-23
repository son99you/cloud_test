<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">--%>
<%--
 * 공통 > 설계추천파일 (팝업)
 *
 * <pre>
 * comm 
 *  |_popup
 *   |_fileSamplePoup.jsp
 * 
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %>

<script type="text/javascript" src="${jsPath}/ipro/views/comm/popup/fileSamplePopup.js"></script>
<div class="pop_sp_sec"> 
	<h1 class="sp_tit">설계추천파일 목록</h1>
	<div class="sp_cont">
		<form id="searchFrm" class="search_form" method="POST" >
			<input type="hidden" id="P_PAGE_NO" name="P_PAGE_NO" value="${comFn:nvl(param.P_PAGE_NO, 1)}">
			<input type="hidden" id="P_PAGE_SIZE" name="P_PAGE_SIZE" value="${comFn:nvl(param.P_PAGE_SIZE, 10)}">
		</form>
		
		    <!-- Data List -->
		<table class="recm_tb">
            <caption>설계추천파일 목록</caption>
            <colgroup>
                <col width="10%">
                <col width="*">
                <col width="*">
			<thead>
                <tr>
                    <th>설계파일ID</th>
                    <th>설계파일명</th>
                    <th>첨부파일</th>
                </tr>
            </thead>
			<tbody>
				<c:if test="${empty fileSampleList}">
					<tr>
						<td colspan="3" style="text-align: center;">검색 조건에 해당하는 데이터가 존재하지 않습니다.</td>
					</tr>
				</c:if>
				<c:if test="${not empty fileSampleList}">
					<c:set var="cdId" />
					<c:forEach var="data" items="${fileSampleList}" varStatus="status" >
						<tr> <!-- style="cursor: pointer;" -->
							<!-- PCRQ_RECM_FSCD --><!--#fcf5f9  -->
							<c:if test="${cdId  ne data.CD_DTL_ID}"><c:set var="rowCnt"  value = "1"/></c:if>
							<c:if test="${data.CD_DTL_ID eq fileRecmId.PCRQ_RECM_FSCD }">
								<c:if test="${cdId  ne data.CD_DTL_ID}"><td class="no recm_img recm recm_brd" rowspan="${data.CNT }" >${data.CD_DTL_ID}</td></c:if>
								<c:if test="${cdId  ne data.CD_DTL_ID}"><td class="txtl pl5 recm recm_brd" rowspan="${data.CNT }"  style="line-height: 150%;">${data.CD_DTL_NM} </td></c:if>
								<c:if test="${rowCnt eq data.CNT }">
									<td class="txtl pl5 recm recm_brd" >
										<span style="float:left;"><a href="javascript:download('${data.FILE_GRP_NO}', '${data.FILE_SN}');">${data.SYS_FILE_NM}&nbsp;</a></span>
									</td>
								</c:if>
								<c:if test="${rowCnt ne data.CNT }">
									<td class="txtl pl5 recm" >
										<span style="float:left;"><a href="javascript:download('${data.FILE_GRP_NO}', '${data.FILE_SN}');">${data.SYS_FILE_NM}&nbsp;</a></span>
									</td>
								</c:if>
							</c:if>
							<c:if test="${data.CD_DTL_ID ne fileRecmId.PCRQ_RECM_FSCD }">
								<c:if test="${cdId  ne data.CD_DTL_ID}"><td class="no recm_non recm_brd"  rowspan="${data.CNT }" >${data.CD_DTL_ID}</td></c:if>
								<c:if test="${cdId  ne data.CD_DTL_ID}"><td class="txtl pl5 recm_non recm_brd"  rowspan="${data.CNT }"  style="line-height: 150%;" >${data.CD_DTL_NM} </td></c:if>
								<c:if test="${rowCnt eq data.CNT }">
									<td class="txtl pl5 recm_non recm_brd" >
										<span style="float:left;"><a href="javascript:download('${data.FILE_GRP_NO}', '${data.FILE_SN}');">${data.SYS_FILE_NM}&nbsp;</a></span>
									</td>
								</c:if>
								<c:if test="${rowCnt ne data.CNT }">
									<td class="txtl pl5 recm_non" >
										<span style="float:left;"><a href="javascript:download('${data.FILE_GRP_NO}', '${data.FILE_SN}');">${data.SYS_FILE_NM}&nbsp;</a></span>
									</td>
								</c:if>
							</c:if>
							<c:set var="cdId"  value="${data.CD_DTL_ID }" />
							<c:set var="rowCnt"  value="${rowCnt +1}" />
						</tr>
					</c:forEach>
				</c:if>
			</tbody> 
		</table>
			
		<!-- Data Paging -->
		<%-- <div class="paging">
			<comTag:pagingIpro totalCount="${fileSampleListTotcnt}" pageNo="${comFn:nvl(param.P_PAGE_NO, 1)}" pageSize="${comFn:nvl(param.P_PAGE_SIZE, 10)}" clickPage="pageObj.clickPage"/>
		</div> --%>
			
		<div class="btm_btns">
			<button type="button" class="btn ty04" id="closeBtn" onclick="window.close();">닫기</button>
		</div>
	</div> <!--// pop_container E -->
</div> <!--// pop_wrap E -->

<form id="downloadFrm" method="POST">
	<input type="hidden" name="P_FILE_GRP_NO"  id="P_FILE_GRP_NO">
	<input type="hidden" name="P_FILE_SN"  id="P_FILE_SN">
</form>