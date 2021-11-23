<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 입찰관리 > 품목별 업체 투찰금액 상세 (팝업)
 *
 * <pre>
 * ebid 
 *    |_ popup
 * 		      |_ tndrItemAmtList.jsp
 * 
 * </pre>
 * @date : 2019. 08. 13
 * @version : 1.0
 * @author : 은우소프트 은잔디
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %> 
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %> 

<div class="pop_wrap">
	<div class="pop_header">
		<h1 class="tit">품목별 업체 투찰금액</h1>
	</div>
	<div class="pop_container">
		<div class="view_wrap typeC">
			<div class="view_area">
				<table>
			    	<colgroup>
			        	<col style="width: 15%;">
						<col style="width: 35%;">
						<col style="width: 15%;">
						<col style="width: 35%;">
			        </colgroup>
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
			    </table>
			</div>
			
        	<div class="tit_area">
		       	<h4 class="tit" style="clear: both;">입찰품목</h4>
	        </div>
			<div class="view_area" style="margin-bottom: 30px; overflow: auto;">
	            <table style="width:100%;">
	                <caption>입찰품목</caption>
	                <colgroup>
						<col style="width: 15%;">
	                   	<col style="width: 12%;">
	                   	<col style="width: auto;"> 
	                   	<col style="width: 30%;">
	                   	<col style="width: 8%;">
	                   	<col style="width: 15%;">
	                </colgroup>
	                <thead>
		                <tr>
		                    <th scope="col" class="txtc">구매요구번호</th>
		                    <th scope="col" class="txtc">품목번호</th>
		                    <th scope="col" class="txtc">품명</th>
		                    <th scope="col" class="txtc">규격</th>
		                    <th scope="col" class="txtc">수량</th>
		                    <th scope="col" class="txtc">품목단가금액</th>
		                </tr>
	                </thead>
	                
	                <c:if test="${ empty tndrItemAmtList}">
	                	<tr class="row">
		               		<td colspan="6" class="txtc">검색 조건에 해당하는 데이터가 존재하지 않습니다.</td>
		               	</tr>
	                </c:if>
	                
	                <c:if test="${ not empty  tndrItemAmtList}">
		                <tbody id="biprInfoShowTbdy">
		                	<c:forEach var="data" items="${tndrItemAmtList}" varStatus="status">
								<tr class="row">
									<td class="txtc">${data.PCRQ_NO}</td>
									<td class="txtc">${data.ITEM_NO}</td>
									<td>${data.ITEM_NM}</td>
									<td>${data.STND_NM}</td>
									<td class="txtr pr5">${data.ITEM_QTY}</td>
									<td class="txtr pr5">${comFn:formatMoney(data.ITEM_UNIT_AMT)}</td>
								</tr>
							</c:forEach>
		                </tbody>
	                </c:if>
	                
	            </table>
	        </div>
			
			<div class="btn_wrap view_btn">
		    	<button type="button" class="btn btn_m btn_del" id="closeBtn" onclick="window.close();">닫기</button>
		    </div>
		</div>    
	</div>
</div> <!--// content E-->