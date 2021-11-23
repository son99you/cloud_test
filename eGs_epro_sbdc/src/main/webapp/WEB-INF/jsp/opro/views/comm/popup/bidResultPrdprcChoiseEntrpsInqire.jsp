<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%--
 *  예가선택업체조회 팝업
 *
 * <pre>
 * ebid
 *  |_popup
 *   | 	 bidResultPrdprcChoiseEntrpsInqire.jsp
 * 
 * </pre>
 * @date : 2017. 06. 23.
 * @version : 1.0
 * @author : 은우소프트 이주연 
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %>

<script type="text/javascript" src="${jsPath}/views/comm/popup/bidPartcptReqstdocPopupInqire.js"></script>

<div id="windowPopup">
	<h3 class="windowTitle">예가선택업체 조회</h3>
	<fieldset>
        <legend>예가선택업체 조회</legend>
        <div class="tableLayer">
            <p class="popSubTitle marginSet"></p>
            <table class="tableList">
            <caption>심사평가</caption>
            <colgroup>
                <col width="40px"/>
                <col width="300px"/>
                <col width="140px"/>
                <col width="140px"/>
            </colgroup>			
			<thead>
                <tr>
                    <th>번호</th>
                    <th>업체명</th>
                    <th>대표자</th>
                    <th>사업자번호</th>
                </tr>
            </thead>
			<tbody>
				<c:if test="${param.P_CHOISE_NUM eq 0 }">
		      <tr class="row" >
		        	<td colspan="4">선택한 업체가 없습니다.</td>
		        </tr>
	        </c:if>
	        <c:if test="${param.P_CHOISE_NUM > 0 }">
		        <tr class="row" >
		        	<td >1</td>
		            <td class="left_T">주식회사 은우소프트</td>
		            <td >정한규</td>
		            <td >119-86-02801</td>
		        </tr>
	        </c:if>
	        <c:if test="${param.P_CHOISE_NUM > 1 }">
		       <tr class="row" >
		        	<td>2</td>
		            <td class="left_T">케이웨어</td>
		            <td >이주연</td>
		            <td >429-56-11241</td>
		        </tr>
	        </c:if>
	        <c:if test="${param.P_CHOISE_NUM > 2 }">
		       <tr class="row" >
		        	<td>3</td>
		            <td class="left_T">테스트업체119</td>
		            <td>구덕한</td>
		            <td>111-11-11119</td>
		        </tr>
	        </c:if>
	        <c:if test="${param.P_CHOISE_NUM > 3 }">
		       <tr class="row" >
		        	<td >4</td>
		            <td class="left_T">테스트업체229</td>
		            <td>이종한</td>
		            <td>222-22-22229</td>
		        </tr> 
	        </c:if>
			</tbody>  
		</table>
		</div>
        
        <div class="T_btnLayer fr">
            <button type="button" class="blueBtn L" id="closeBtn" onclick="window.close();">닫기</button>
        </div>
    </fieldset>
</div>