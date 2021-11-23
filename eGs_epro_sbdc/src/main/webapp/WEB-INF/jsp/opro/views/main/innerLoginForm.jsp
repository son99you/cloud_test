<%@ page language="java" contentType="text/html; charset=UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %>

<script type="text/javascript" src="${jsPath}/opro/views/main/innerLoginForm.js"></script>

<section class="left_cnt"  >
	<h2>
		<span>공지사항</span>
		<span class="more" onclick="notiList('NOTI')">+</span>
	</h2>
	<ul>
		<c:if test="${notiListTotCnt > 0}">
			<c:forEach var="data" items="${notiList}" varStatus="status" >
				<li>
					<a onclick="detailInqire('${data.BBS_SECD }', '${data.BBS_SN}');">
						<span>${data.TTL_NM }</span>
						<span>${comFn:formatDate(data.REG_DT,"yyyyMMddHHmmss","yyyy-MM-dd") }</span>
					</a>
				</li>
			</c:forEach>
		</c:if>
	</ul>
</section>

<section class="center_cnt" >
	<h2>
		<span>FAQ</span>
		<span class="more" onclick="notiList('FAQ')">+</span>
	</h2>
	<ul>
		<c:if test="${faqListTotCnt > 0}">
			<c:forEach var="data" items="${faqList}" varStatus="status" >
				<li>
					<a onclick="detailInqire('${data.BBS_SECD }', '${data.BBS_SN}');">
						<span>${data.TTL_NM }</span>
					</a>
				</li>
			</c:forEach>
		</c:if>
	</ul>
</section>

<section class="right_cnt" >
	<a href="javascript:downloadManual">메뉴얼 다운로드</a>
	<p>업무를 불편함없이 이용하실 수</p>
	<p>있도록 안내해드립니다.</p>
</section>

<form id="manualFrm"  method="POST">
</form>

<form id="notiDetailFrm" method="POST">
	<input type="hidden" name="resourceDesc" value="${ param.resourceDesc }" > 
	<input type="hidden" name="resourceName" value="${comFn:nvl(param.resourceName, resourceName)}" >
	<input type="hidden" name="P_BBS_SECD">
	<input type="hidden" name="P_BBS_SN">
</form>