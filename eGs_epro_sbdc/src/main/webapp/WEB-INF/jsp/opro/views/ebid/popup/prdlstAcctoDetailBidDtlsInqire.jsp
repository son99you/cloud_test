<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 전자입찰 > 품목별 세부입찰사항
 *
 * <pre>
 * elbi
 *    |_popup
 *        |_iepElbiPopupPrdlstAcctoDetailBidDtlsInqire.jsp
 * 
 * </pre>
 * @date : 2015. 08. 18. 오전 9:25:31
 * @version : 1.0
 * @author : 은우소프트 손연우
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %>

<script type="text/javascript" src="${jsPath}/opro/views/ebid/popup/prdlstAcctoDetailBidDtlsInqire.js"></script>

<div id="windowPopup">
	<h3 class="windowTitle">품목별 세부입찰사항</h3>
    <form id="registFrm" method="POST">
	<fieldset>
        <legend>투찰업체</legend>
        <div class="tableLayer">
            <p class="popSubTitle marginSet">투찰업체</p>
            <table class="table" summary="투찰 업체 테이블 입니다.">
                <caption>투찰업체</caption>
                <colgroup>
                   <col width="20%">
                   <col width="30%">
                   <col width="20%">
                   <col width="30%">
                </colgroup>
                <tbody>
	                <tr class="line">
	                    <th>업체명</th>
	                    <td>${prdlstAcctoDetailBidDtlsInqire.VEND_NM}&nbsp;</td>
	                    <th>대표자</th>
	                    <td>${prdlstAcctoDetailBidDtlsInqire.RPRS_NM}&nbsp;</td>
	                </tr>
	                <tr>
	                    <th>사업자번호</th>
	                    <td>${comFn:formatBizNumber(prdlstAcctoDetailBidDtlsInqire.BIZRNO)}&nbsp;</td>
	                    <th>전화번호</th>
	                    <td>${prdlstAcctoDetailBidDtlsInqire.TEL_NO}&nbsp;</td>
	                </tr>
                </tbody>
            </table>
        </div>
        
        <c:if test="${not empty bidThngList}">
        <div class="tableLayer">
		<p class="popSubTitle marginSet">입찰물품 목록</p>
        <table class="tableList" summary="입찰물품 목록 테이블 입니다.">
            <caption>입찰물품 목록</caption>
            <colgroup>
                <col width="5%">
                <col width="25%">
                <col width="15%">
                <col width="15%">
                <col width="17%">
                <col width="33%">
            </colgroup>			
			<thead>
                <tr>
                    <th>번호</th>
                    <th>품명</th>
                    <th>단위</th>
                    <th>수량</th>
                    <th>단가</th>
                    <th>금액</th>
                </tr>
            </thead>
			<tbody>
					<c:forEach var="data" items="${bidThngList}" varStatus="status" >
					<tr class="row">
						<td>${status.count}</td>
						<td class="left_T">${data.ITEM_NM}</td>
						<td class="txtc">${data.ITEM_UNNM}</td>
						<td>${comFn:formatMoney(data.ITEM_QTY)}</td>
						<td>${comFn:formatMoney(data.ITEM_UNIT_AMT)}</td>
						<td>${comFn:formatMoney((data.ITEM_UNIT_AMT * data.ITEM_QTY))}</td>
					</tr>
					</c:forEach>
			</tbody> 
		</table>
        </div>
        </c:if>
        
        <p class="popSubTitle marginSet">제안 요청서</p>
		<div class="tableLayer hid">
			<table class="table">
		    	<colgroup>
		        	<col width="15%" />
		            <col width="85%" />
		        </colgroup>
		    	<tr>
		        	<th scope="row" class="vtop">첨부파일</th>
					<td class="vtop">
						<div id="fileViewDiv" style="line-height: 30px;"> 
							<c:if test="${not empty bidAtchDocListDO02 }">
	                  			<c:forEach items="${bidAtchDocListDO02 }" var="data" varStatus="status">
		                   			<div style="height: 30px;"> 
		                   				<a href="javascript:pageObj.download('${data.ATCHMNFL_SN}');" class="attfile">${data.ATCHMNFL_NM }</a>
		                    		</div>
		                    	</c:forEach> 
		                    </c:if>
		                    <c:if test="${empty bidAtchDocListDO02}">
					        	<div style="height: 30px;"> 첨부파일 정보가 없습니다.</div>
					        </c:if>
	                   	</div>
					</td>	
	             </tr>
			</table>
		</div>
        
        <p class="popSubTitle marginSet">투찰내역서</p>
		<div class="tableLayer hid">
			<table class="table">
		    	<colgroup>
		        	<col width="15%" />
		            <col width="85%" />
		        </colgroup>
		    	<tr>
		        	<th scope="row" class="vtop">첨부파일</th>
					<td class="vtop">
						<div id="fileViewDiv" style="line-height: 30px;"> 
							<c:if test="${not empty bidAtchDocListDO04 }">
	                  			<c:forEach items="${bidAtchDocListDO04 }" var="data" varStatus="status">
		                   			<div style="height: 30px;"> 
		                   				<a href="javascript:pageObj.download('${data.ATCHMNFL_SN}');" class="attfile">${data.ATCHMNFL_NM }</a>
		                    		</div>
		                    	</c:forEach> 
		                    </c:if>
		                    <c:if test="${empty bidAtchDocListDO04}">
					        	<div style="height: 30px;"> 첨부파일 정보가 없습니다.</div>
					        </c:if>
	                   	</div>
					</td>	
	             </tr>
			</table>
		</div>
        
        <div class="T_btnLayer fr">
            <button type="button" class="blueBtn L" id="closeBtn">닫기</button>
        </div>
    </fieldset>
    </form>
    	<%-- 파일 VIEW 폼 --%>
	<form id="fileViewFrm" method="POST">
		<input type="hidden" id="P_FILE_GRP_NO" name="P_FILE_GRP_NO" value="${enatpaInqireDO04.FILE_GRP_NO}">
	</form>
	<form id="fileViewFrm2" method="POST">
		<input type="hidden" id="P_FILE_GRP_NO2" name="P_FILE_GRP_NO" value="${enatpaInqireDO02.FILE_GRP_NO}">
	</form>
	<form id="downFrm" method="POST" action="">
		<input type="hidden" name="P_ATCHMNFL_SN" >
	</form>
</div>