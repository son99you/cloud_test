<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 입찰현황 > 관심입찰업체목록
 *
 * <pre>
 * ebid 
 *    |_ popup
              |_ intrstBidEntrpsList.jsp
 * 
 * </pre>
 * @date : 2017. 06. 13
 * @version : 1.0
 * @author : 은우소프트 전상훈
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %> 
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %> 

<script type="text/javascript" src="${jsPath}/ipro/views/ebid/popup/intrstBidEntrpsList.js"></script> 
 
<div class="pop_wrap">
	<div class="pop_header">
		<h1 class="tit">관심입찰업체조회</h1>
	</div> <!--// pop_header E -->
	<div class="pop_container">
		<div class="view_wrap typeC">
			<div class="view_area typeA">
				<table>
					<caption>입찰설명회 목록</caption>
					<colgroup>
	                   	<col style="width: 10%;">
						<col style="width: 20%;">
						<col style="width: auto;">
					</colgroup>
					<thead>
				    	<tr>
				            <th class="txtc">번호</th>
				            <th class="txtc">사업자번호</th>
				            <th class="last txtc">업체명</th>
				        </tr>
			        </thead>
			        <tbody>
						<c:if test="${empty intrstBidEntrpsList}">
							<tr>
								<td colspan="3" class="txtc">관심입찰 등록한 업체가 존재하지 않습니다.</td>
							</tr>
						</c:if>
						<c:if test="${not empty intrstBidEntrpsList}">
							<c:forEach var="data" items="${intrstBidEntrpsList}" varStatus="status" >
								<tr>
									<td class="txtc">${status.count}</td>
									<td class="txtc">${comFn:formatBizNumber(data.BIZRNO)}</td>
									<td class="txtl pl5">${data.VEND_NM}</td>
								</tr>
							</c:forEach>
						</c:if>
					</tbody>
			    </table>
			</div>
			<div class="btn_wrap view_btn">
		    	<button type="button" class="btn btn_02 btn_close" id="closeBtn" >닫기</button>
		    </div>
		</div>
    </div>
</div> <!--// content E-->
