<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 입찰현황 > 공동수급협정서 팝업
 *
 * <pre>
 * ebid 
 *    |_ popup
 		      |_ copertnSpldmdTrtyOfeInqire.jsp
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

<script type="text/javascript" src="${jsPath}/opro/views/ebid/popup/copertnSpldmdTrtyOfeInqire.js"></script> 

<div class="pop_wrap">
	<div class="pop_header">
		<h1 class="tit">공동수급협정서조회</h1>
	</div> <!--// pop_header E -->
	
	<div class="pop_container">
		<div class="view_wrap typeC">
			<div class="tit_area">
				<h4 class="tit">입찰개요</h4>
			</div>		
			<div class="view_area">
				<table>
		    	<colgroup>
		        	<col width="15%" />
		            <col width="35%" />
		            <col width="15%" />
		            <col width="35%" />
		        </colgroup>
			        <tbody>
				    	<tr class="line">
			                <th>입찰공고번호</th>
			                <td>${inProgrsBidPblancDetail.ANNC_NO}-${inProgrsBidPblancDetail.ANNC_NGR}</td>
			                <th>공고일자</th>
			                <td>${comFn:formatDate(inProgrsBidPblancDetail.ANNC_DT,'yyyyMMddHHmmss','yyyy-MM-dd')}</td>
			            </tr>
			            <tr>
			                <th>공고명</th>
			                <td colspan="3">${inProgrsBidPblancDetail.BID_NM}</td>
			            </tr>
			            <tr>
			                <th>계약구분</th>
			                <td>
			                	${inProgrsBidPblancDetail.CONT_SECD_NM}
			                	<c:if test="${inProgrsBidPblancDetail.CONT_DECD eq '200'}">&nbsp;/&nbsp;${inProgrsBidPblancDetail.CONT_DECD_NM}</c:if>
			                </td>
			                <th>계약방법</th>
			                <td>${inProgrsBidPblancDetail.CONT_MTCD_NM}</td>
			            </tr>
			            <tr>
			                <th>대표업체명</th>
			                <td colspan="3">${entrpsinfoinqire.VEND_NM}</td>
			            </tr>
		            </tbody>
	    		</table>
			</div>
		
			<div class="tit_area">
				<%-- <h4 class="tit">공동수급 방식 : ${inProgrsBidPblancDetail.ASSO_SPDM_CD_NM}</h4> --%>
				<h4 class="tit">공동수급업체</h4>
			</div>		
			<div class="view_area">
				<table>		
					<caption>공동수급업체 목록</caption>
					<colgroup>
						<col width="10%">
	                 	<col width="15%">
	                	<col width="20%">
	                 	<col width="15%">
	                 	<col width="15%">
	                 	<col width="auto;">
		            </colgroup>
		            <thead>
				    	<tr>
				            <th class="txtc">구분</th>
			               	<th class="txtc">사업자번호</th>
			               	<th class="txtc">업체명</th>
			               	<th class="txtc">대표자</th>
			               	<th class="txtc">출자비율(%)</th>
			               	<th class="txtc">비고</th>
				        </tr>
			        </thead>
			        <tbody>
			        	<c:if test="${empty copertnSdenList}">
							<tr class="row">
								<td colspan="6" align="center">검색 조건에 해당하는 데이터가 존재하지 않습니다.</td>
							</tr>
						</c:if>
						<c:if test="${not empty copertnSdenList}">
					        <c:forEach var="data" items="${copertnSdenList}" varStatus="status">
								<tr class="row">
									<td class="txtc">
										<c:if test="${data.VEND_REG_NO eq data.ASSO_VEND_REG_NO}">대표자</c:if>
									</td>
									<td class="txtc">${comFn:formatBizNumber(data.ASSO_VEND_BIZRNO)}</td>
									<td>${data.ASSO_VEND_NM}</td>
									<td class="txtc">${data.RPRS_NM}</td>
									<td class="txtr pr5">${data.SHR_RT} %</td>
									<td>${data.RMK}</td>
								</tr>
							</c:forEach>
						</c:if>
					</tbody>
			    </table>
	    	</div>
    
		    <div class="btn_wrap view_btn">
		    	<button type="button" class="btn btn_m btn_del" id="closeBtn" >닫기</button>
		    </div>
	    
		</div> <!--// content E-->
	</div>
</div>
<form id="downFrm" method="post">
	<input type="hidden" name="P_FILE_SN">
	<input type="hidden" name="P_FILE_GRP_NO">
</form>
