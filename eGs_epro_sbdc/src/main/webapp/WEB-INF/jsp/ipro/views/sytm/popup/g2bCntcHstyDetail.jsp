<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 나라장터연계이력 상세(팝업)
 *
 * <pre>
 * sytm
 *    |_ g2bCntcHstyDetail.jsp
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

<div class="pop_wrap">
	<div class="pop_header">
		<h1 class="tit">나라장터연계이력</h1>
	</div> <!--// pop_header E -->

	<div class="pop_container">
		<input type="hidden" id="P_MSG_SN" value="${g2bCntcHstyDetail.MSG_SN }">
		
		<div class="view_wrap typeC">
			
			<div class="view_area m0 typeB">
				<table>
					<colgroup>
						<col width="15%">
						<col width="*">
					</colgroup>
					<tr>
						<th>송수신구분</th>
						<td>${g2bCntcHstyDetail.SND_RCV_SECD_NM }</td>
					</tr>
					<tr>
						<th>연계구분</th>
						<td>${g2bCntcHstyDetail.CNTC_SECD_NM }</td>
					</tr>
					<tr>
						<th>제목</th>
						<td>${g2bCntcHstyDetail.TTL }</td>
					</tr>
					<tr>
						<th>접속내용</th>
						<td><textarea style="height: 280px; width: 100%;" readonly="readonly">${g2bCntcHstyDetail.CNTN }</textarea></td>
					</tr>
				</table>
			</div>
			
			<div class="pop_list_bottom">
				<div class="btn_wrap view_btn">
					<button type="button" class="btn btn_s btn_del" id="closeBtn" onclick="javascript:window.close();">닫기</button>
				</div> <!--// btn_wrap E -->
			</div>			
		</div>
	</div>
</div>