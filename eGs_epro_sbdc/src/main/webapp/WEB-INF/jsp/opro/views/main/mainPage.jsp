<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 *	main
 *    |_ mainPage.jsp
 * 
 * </pre>
 * @date : 2017. 06. 16.
 * @version : 1.0
 * @author : 은우소프트 홍찬일
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %>

<script type="text/javascript" src="${jsPath}/opro/views/main/mainPage.js"></script>

<!-- 컨텐츠 영역 -->
<section id="summary">
	<h2>
		<img src="${imagePath}/kto_sub/work.png" alt="업무요약 타이틀 이미지">
	</h2>

	<form name="searchFrm" id="searchFrm" method="post">
		<div class="summary_dat">
			<div class="dat_depth01">
				<span >조회기간 : </span>
				<span class="dat">&nbsp;${comFn:formatDate(P_BEGIN_DT_S,'yyyyMMdd','yyyy-MM-dd')} ~ ${comFn:formatDate(P_END_DT_S,'yyyyMMdd','yyyy-MM-dd')}</span> 
				<span class="refresh">
					<img src="${imagePath}/kto_sub/refresh_icon.png" id="searchBtn" alt="새로고침 아이콘">
				</span> 
			</div> 
		</div> 
	</form>

	<div class="summary_info_wrap">
		<h3 class="info_title">계약설계 및 대금지급 진행 현황</h3>
		<div class="info_cnt">
			<table>
				<tr> 
					<th>검토요청</th>
					<th>검토요청(계약)</th>
					<th>서명요청</th>
					<th>대금지급요청</th>
				</tr>

				<tr>
					<td><a href="javascript:movePage('/opro/cont/contProgrList.do', 'OEP04001','C006')" style="cursor: pointer;">${myContReqMainCnt}건</a></td>
					<td><a href="javascript:movePage('/opro/cont/contProgrList.do', 'OEP04001','C018')" style="cursor: pointer;">${myContPrcnMainCnt}건</a></td>
					<td><a href="javascript:movePage('/opro/cont/contProgrList.do', 'OEP04001','C021')" style="cursor: pointer;">${myContSignMainCnt}건</a></td>
					<td><a href="javascript:movePage('/opro/prpy/contPrpyReqstList.do', 'OEP08001','P003')"  style="cursor: pointer;">${myPayReqMainCnt}건</a></td>
				</tr>
			</table>
		</div>
	</div>

	<div class="bottom_wrap">
		<section class="notice bottom_cnt">
			<h3>
				<span>공지사항</span>
				<span class="more" onclick="notiList('NOTI')">더보기</span>
			</h3>
			<ul>
				<c:if test="${notiListTotCnt > 0}">
					<c:forEach var="data" items="${notiList}" varStatus="status" >
						<li>
							<a onclick="detailInqire('${data.BBS_SECD }', '${data.BBS_SN}');">
								<span>${data.TTL_NM }</span>
								<span style="padding-right:10px;">${comFn:formatDate(data.REG_DT,"yyyyMMddHHmmss","yyyy-MM-dd") }</span>
							</a>
						</li>
					</c:forEach>
				</c:if>
			</ul>
		</section>

		<section class="faq bottom_cnt">
			<h3>
				<span>FAQ</span>
				<span class="more" onclick="notiList('FAQ')">더보기</span>
			</h3>
			<ul>
				<c:if test="${faqListTotCnt > 0}">
					<c:forEach var="data" items="${faqList}" varStatus="status" >
						<li>
							<a onclick="detailInqire('${data.BBS_SECD }', '${data.BBS_SN}');">
								<span>${data.TTL_NM }</span>
								<span style="padding-right:10px;">${comFn:formatDate(data.REG_DT,"yyyyMMddHHmmss","yyyy-MM-dd") }</span>
							</a>
						</li>
					</c:forEach>
				</c:if>
			</ul>
		</section>
		
	</div>
</section> 

<%-- DETAIL FORM --%>
<form id="detailFrm" method="POST">
	<input type="hidden" name="resourceName">
	<input type="hidden" name="P_BBS_SN" id="P_BBS_SN">
	<input type="hidden" name="P_BBS_SECD" id="P_BBS_SECD">

	<input type="hidden" name="P_CONT_PSCD_S" id="P_CONT_PSCD_S"><!-- 계약요청 상태 -->
	<input type="hidden" name="P_PAY_STCD_S" id="P_PAY_STCD_S"><!-- 대금지급요청 상태 -->
</form>

<form id="downloadFrm" method="POST">
 	<input type="hidden" name="P_FILE_SN">
 	<input type="hidden" name="P_FILE_GRP_NO">
</form>

<form id=manualFrm method="POST">
</form>
