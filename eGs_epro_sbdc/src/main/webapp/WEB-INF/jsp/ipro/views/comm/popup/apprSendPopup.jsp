<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 통합게시판 > 출력 팝업
 *
 * <pre>
 * 
 * </pre>
 * @date : 2018. 10. 08. 
 * @version : 1.0
 * @author : 은우소프트 맹경열
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %>

<script type="text/javascript">
window.onmessage = function(e) {
	//console.log(e);
	//alert(e);
	//alert(e.data.status);
	window.opener.refresh();
	window.close();
};
</script>

<!-- 결재상신 선 : NEW -->
<c:if test="${param.P_STATUS eq 'NEW'}">
<iframe id="doc_frame" name="doc_frame" style="width: 100%; height: 800px;" src="https://core.ebs.co.kr/xclick_ebs/legacyAppDispatcher.jsp?interface_id=${param.P_INTERFACE_ID}&isDispath=true"></iframe>
</c:if>
<!-- 결재상신 후 : APP, STOP, END -->
<c:if test="${param.P_STATUS ne 'NEW'}">
<iframe id="doc_frame" name="doc_frame" style="width: 100%; height: 800px;" src="https://core.ebs.co.kr/xclick_ebs/legacyAppView.jsp?interface_id=${param.P_INTERFACE_ID}&sabun=${param.P_DRAFT_SABUN}&isDispath=true"></iframe>
</c:if>
