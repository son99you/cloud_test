<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 전자입찰 > 낙찰자선정 결격사유  조회(팝업)
 *
 * <pre>
 * elbi 
 *    |_iepElbiPopupBidResultBrdoResnInqire.jsp
 * 
 * </pre>
 * @date : 2015. 04. 06. 오후 05:06:42
 * @version : 1.0
 * @author : 은우소프트 손연우
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %>

<script type="text/javascript" src="${jsPath}/opro/views/ebid/popup/bidResultBrdoResnInqire.js"></script>

<div id="windowPopup">
	<h3 class="windowTitle">결격사유조회</h3>
	<div class="tableLayer">
		<table class="table">
			<caption>심사대상물품</caption>
            <colgroup>
				<col width="15%" />
	            <col width="35%" />
	            <col width="15%" />
	            <col width="35%" />
			</colgroup>
			<tbody>
				<tr class="line">
					<th>공고번호</th>
                   	<td>${bidInfoDetail.ANNC_NO}-${bidInfoDetail.ANNC_NGR}</td>
                   	<th>공고일자</th>
                   	<td>${comFn:formatDate(bidInfoDetail.ANNC_DT,'yyyyMMddHHmmss','yyyy-MM-dd')}</td>
               	</tr>
               	<tr>
                   	<th>공고명</th>
                   	<td colspan="3">${bidInfoDetail.BID_NM}</td>
               	</tr>
               	<tr>
                   	<th>계약방법</th>
                  	<td>${bidInfoDetail.CONT_MTCD_NM}</td>
                  	<th>결격처리일시</th>
                  	<td>${comFn:formatDate(entrpsInfoDetail.REG_DT,'yyyyMMddHHmmss','yyyy-MM-dd')}</td>	                
				</tr>
               	<tr>
               		<th>결격사유</th>
               		<td><textarea taView style="display: none;" >${entrpsInfoDetail.NT_ELGB_RSN}</textarea></td>
               	</tr>
			</tbody>
		</table>
	</div>
        
	<div class="T_btnLayer fr">
		<button type="button" class="blueBtn L" id="closeBtn">닫기</button>
	</div>
</div>