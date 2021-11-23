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

<script type="text/javascript" src="${jsPath}/ipro/views/ebid/popup/bidResultBrdoResnInqire.js"></script>

<div class="pop_wrap">
	<div class="pop_header">
		<h1 class="tit">결격사유 조회</h1>
	</div> <!--// pop_header E -->
    <form id="registFrm" method="POST">
	    <input type="hidden" name="P_VEND_REG_NO" value="${ entrpsInfoDetail.VEND_REG_NO }">
	    <input type="hidden" name="P_ANNC_NO" value="${ bidInfoDetail.ANNC_NO }">
	    <input type="hidden" name="P_ANNC_NGR" value="${bidInfoDetail.ANNC_NGR }">
	    <input type="hidden" name="P_BID_ELCD" value="F">
        <div class="pop_container">
			<div class="view_wrap typeC">
				<div class="view_area">
		            <table class="table">
		                <caption>심사대상물품</caption>
		                <colgroup>
		                   <col width="120px">
		                   <col width="400px">
		                </colgroup>
		                <tbody>
			                <tr class="line">
			                    <th>공고번호</th>
			                    <td>${bidInfoDetail.ANNC_NO}-${bidInfoDetail.ANNC_NGR}</td>
			                </tr>
			                <tr>
			                    <th>입찰명</th>
			                    <td>${bidInfoDetail.BID_NM}&nbsp;</td>
			                </tr>
			                <tr>
			                    <th>입찰방법</th>
		                    	<td>
		                        	${bidInfoDetail.CNTRCT_MTH_NM}&nbsp;>&nbsp;  ${bidInfoDetail.SCSBID_MTH_NM}&nbsp;
		                    	</td>	                
		                    </tr>
			                <tr>
			                	<th>결격업체</th>
			                	<td>${entrpsInfoDetail.VEND_NM }</td>
			                </tr>
			                <tr>
			                	<th>결격사유</th>
			                	<td><textarea cols="" rows="" taView style="display: none;" name="P_NT_ELGB_RSN" ><c:out value="${entrpsInfoDetail.NT_ELGB_RSN}" /></textarea></td>
			                	
			                </tr>
		                </tbody>
		            </table>
        		</div>
		        <div class="btn_wrap view_btn">
		            <button type="button" class="btn btn_02 btn_close" id="closeBtn">닫기</button>
		        </div>
			</div>
		</div>	       
	</form>
</div>