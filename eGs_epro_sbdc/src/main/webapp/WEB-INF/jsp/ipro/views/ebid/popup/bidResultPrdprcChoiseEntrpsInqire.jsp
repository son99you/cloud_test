<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 낙찰자선정 > 예가선택업체 조회
 *
 * <pre>
 * ebid 
 *    |_ popup
              |_ bidResultPrdprcChoiseEntrpsInqire.jsp
 * 
 * </pre>
 * @date : 2017. 06. 21
 * @version : 1.0
 * @author : 은우소프트 전상훈
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %> 
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %> 

<script type="text/javascript" src="${jsPath}/ipro/views/ebid/popup/bidResultPrdprcChoiseEntrpsInqire.js"></script> 
 
<div class="pop_wrap">
	<div class="pop_header">
		<h1 class="tit">예비가격선택업체조회</h1>
	</div> <!--// pop_header E -->
	<div class="pop_container">
		<div class="view_wrap typeC">
			<div class="view_area">
				<table class="table">
					<caption>예가선택업체 조회</caption>
					<colgroup>
		               	<col width="*"/>
		               	<col width="20%"/>
		               	<col width="20%"/>
		           	</colgroup>
			    	<tr>
			            <th class="txtc">업체명</th>
			            <th class="txtc">사업자번호</th>
			            <th class="txtc">대표자</th>
			        </tr>
			        <c:if test="${empty prdprcChoiseEntrpsInqire}">
						<tr>
							<td colspan="3" class="txtc">데이터가 존재하지 않습니다.</td>
						</tr>
					</c:if>
					<c:if test="${not empty prdprcChoiseEntrpsInqire}">
						<c:forEach var="data" items="${prdprcChoiseEntrpsInqire}" varStatus="status" >
							<tr>
								<td>${data.VEND_NM}</td>
								<td class="txtc">${comFn:formatBizNumber(data.BIZRNO)}</td>
								<td class="txtc">${data.RPRS_NM}</td>
							</tr>
						</c:forEach>
					</c:if>
			    </table>
   		 	</div>
		    
		    <div class="btn_wrap view_btn">
		    	<button type="button" class="btn btn_m btn_del" id="closeBtn" >닫기</button>
		    </div>
		    
		</div>
	</div> <!--// content E-->
</div>
