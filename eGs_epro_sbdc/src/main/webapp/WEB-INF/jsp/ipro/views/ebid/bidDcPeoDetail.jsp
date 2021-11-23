<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 입찰관리 > 현장설명회 상세
 *
 * <pre>
 * ebid 
 *    |_ bidDcPeoDetail.jsp
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

<script type="text/javascript" src="${jsPath}/ipro/views/ebid/bidDcPeoDetail.js"></script> 
 
<div class="contents_wrap">
	<ul class="step_wrap">
		<li><a href="#">${myMenuList.bigMenuNm}</a></li>
		<li><a href="#">${myMenuSubList.smallMenuNm}</a></li>
	</ul>
	<div class="tit_wrap">
		<h3 class="tit">현장설명회 상세</h3>
	</div>
 
	<div class="view_wrap typeA">
		<div class="tit_area">
			<h4 class="tit">입찰개요</h4>
			<div class="btn_right">
				<button type="button" class="btn btn_s2 btn_c2" id="detailBtn" style="width: 120px;">입찰공고문 상세</button>
			</div>
		</div>
		<div class="view_area">
			<table>
		    	<colgroup>
		        	<col style="width: 15%;">
		        	<col style="width: 35%;">
		        	<col style="width: 15%;">
		        	<col style="width: 35%;">
		        </colgroup>
		        <tr>
		        	<th scope="row">사전공고번호</th>
		            <td colspan="3">
		            	<c:if test="${empty bidDcPeoDetail.BFAN_NO_LIST}">사전공고번호가 없습니다.</c:if>
		            	<c:if test="${not empty bidDcPeoDetail.BFAN_NO_LIST}">${bidDcPeoDetail.BFAN_NO_LIST }</c:if>
		            </td>
		        </tr>
		    	<tr>
		        	<th scope="row">공고번호</th>
		            <td>${bidDcPeoDetail.ANNC_NO}-${bidDcPeoDetail.ANNC_NGR}</td>
	             	<th scope="row">공고일자</th>
                    <td>${comFn:formatDate(bidDcPeoDetail.ANNC_DT,'yyyyMMddHHmmss','yyyy-MM-dd')}</td>
		        </tr>
		        <tr>
		            <th scope="row">입찰공고명</th>
		            <td colspan="3">
                        <c:if test="${bidDcPeoDetail.EMRG_YN eq 'Y'}"><font color="red">[긴급] </font></c:if>
						<c:if test="${bidDcPeoDetail.ANNC_NGR > 1}"><font color="red">[정정] </font></c:if>
						${bidDcPeoDetail.BID_NM}
		            </td>
		        </tr>
		        <tr>
		        	<th scope="row">계약구분</th>
		            <td>
		            	${bidDcPeoDetail.CONT_SECD_NM}
		            	<c:if test="${bidDcPeoDetail.CONT_DECD eq '200'}">&nbsp;/&nbsp;${bidDcPeoDetail.CONT_DECD_NM}</c:if>
		            </td>
		        	<th scope="row">계약방법</th>
		            <td>${bidDcPeoDetail.BID_MTCD_NM}</td>
		        </tr>
		        <tr>
		        	<th scope="row">낙찰방법</th>
		            <td>${bidDcPeoDetail.SBID_MTCD_NM}</td>
                    <th scope="row">추정금액 (원)</th>
                    <td>${comFn:formatMoney(bidDcPeoDetail.ESTT_AMT)}</td>
		        </tr>
		        <tr>
		        	<th scope="row">예가방식</th>
		            <td>${bidDcPeoDetail.ESTPC_SECD_NM}</td>
		        	<th scope="row"><label <c:if test="${bidDcPeoDetail.ESTPC_SECD eq '180002'}"> style="display:none;"</c:if>>기초금액 (원)</label></th>
		        	<td>
		        		<c:if test="${bidDcPeoDetail.ESTPC_SECD ne '180002'}">
		        			${comFn:formatMoney(bidDcPeoDetail.BASE_AMT)}
		        		</c:if>
		        	</td>
                </tr>
		    </table>
		</div>
		
	    
	    <div class="tit_area">
		    <h4 class="tit">입찰진행순서</h4>
		</div>
		
		<div class="view_area">
            <table>
                <caption>입찰진행순서</caption>
                <colgroup>
		        	<col style="width: 15%;">
		        	<col style="width: 85%;">
                </colgroup>
                <tbody>
	                <tr>
	                    <th scope="row">입찰공고게시일시</th>
	                    <td>${comFn:formatDate(bidDcPeoDetail.ANNC_DT,'yyyyMMddHHmmss','yyyy-MM-dd HH:mm')}</td>
	                </tr>
	                <%-- 입찰설명회 여부가 [예] 일 경우에 활성화 --%>
	                <c:if test="${bidDcPeoDetail.ROUND_NO < 2}">
		                <tr>
		                    <th scope="row">입찰설명회일시</th>
		                    <td>${comFn:formatDate(bidDcPeoDetail.BRFS_DT,'yyyyMMddHHmmss','yyyy-MM-dd HH:mm')}</td>
		                </tr>
		                <%-- 입찰설명회 여부가 [예] 일 경우에 활성화 End --%>
		                <c:if test="${bidDcPeoDetail.PRPDC_ESS_YN eq 'Y'}">
			                <tr>
			                    <th scope="row">제안서제출기간</th>
			                    <td>
				                    ${comFn:formatDate(bidDcPeoDetail.PRPDC_SBMT_STDT,'yyyyMMddHHmmss','yyyy-MM-dd HH:mm')}
			                        <span class="wave">~</span>
			                        ${comFn:formatDate(bidDcPeoDetail.PRPDC_SBMT_ENDT,'yyyyMMddHHmmss','yyyy-MM-dd HH:mm')}
			                    </td>
			                </tr>
		                </c:if>
	                </c:if>                
	                <tr>
	                    <th scope="row">입찰서제출기간</th>
	                    <td>
	                    	${comFn:formatDate(bidDcPeoDetail.BIDC_SBMT_STDT,'yyyyMMddHHmmss','yyyy-MM-dd HH:mm')}
	                        <span class="wave">~</span>
	                        ${comFn:formatDate(bidDcPeoDetail.BIDC_SBMT_ENDT,'yyyyMMddHHmmss','yyyy-MM-dd HH:mm')}
	                    </td>
	                </tr>
	                <tr>
	                    <th scope="row">개찰일시</th>
	                    <td>${comFn:formatDate(bidDcPeoDetail.OPNG_DT,'yyyyMMddHHmmss','yyyy-MM-dd HH:mm')}</td>
	                </tr>
                </tbody>
            </table>
        </div>
		
		<div class="tit_area">
       		<h4 class="tit" style="clear: both;">참석업체</h4>
       		<div class="btn_right">
	            <button type="button" class="btn btn_s2 btn_c2" id="nmenSearchBtn">업체추가</button>
	            <button type="button" class="btn btn_s2 btn_c2" id="nmenDeleteBtn">업체삭제</button>
	        </div>
        </div>
		<div class="view_area" style="margin-bottom: 30px;">
			<form id="registFrm" method="POST">  
				<input type="hidden" name="resourceName" value="${param.resourceName}">
			    <input type="hidden" name="P_ANNC_NO" value="${bidDcPeoDetail.ANNC_NO}">
				<input type="hidden" name="P_ANNC_NGR" value="${bidDcPeoDetail.ANNC_NGR}">
				<input type="hidden" name="P_ROUND_NO" value="${bidDcPeoDetail.ROUND_NO}">
	            <table>
	                <caption>참석업체</caption>
	                <colgroup>
	                   	<col style="width: 5%;">
	                   	<col style="width: 10%;">
	                   	<col style="width: auto;">
	                   	<col style="width: 10%;">
	                   	<col style="width: 15%;">
	                   	<col style="width: 15%;">
	                   	<col style="width: 15%;">
	                   	<col style="width: 10%;">
	            	</colgroup>
	                <thead>
	                	<tr>
			                <th class="txtc">선택</th>
				            <th class="txtc">사업자번호</th>
				            <th class="txtc">업체명</th>
				            <th class="txtc">대표자명</th>
				            <th class="txtc">참석자명</th>
				            <th class="txtc">전화번호</th>
				            <th class="txtc">이메일</th>
				            <th class="last txtc">등록일자</th>
			            </tr>
	                </thead>
	                <tbody id="nmenChoiseHiddTbdy">
	                	<tr style="display: none;">
							<td class="txtc">
								<input type="checkbox" id="nmenChoiseCbx" name="nmenChoiseCbx">
								<input type="hidden" name="P_VEND_REG_NO" disabled>
							</td>
							<td bizrNo class="txtc"></td>
							<td entrpsNm class="txtl pl5"></td>
							<td rprsntvNm class="txtl pl5"></td>
							<td><input type="text" name="P_ATNPE_NM" chrgrNm></td>
							<td><input type="text" name="P_ATNPE_TEL_NO" tel></td>
							<td><input type="text" name="P_ATNPE_EMAL" eMail></td>
							<td regDe class="txtc"></td>
						</tr>
	                </tbody>
	                <tbody id="nmenChoiseShowTbdy">
	                	<c:forEach var="data" items="${bidDcPeoPartcptEntrpsList}" varStatus="status">
							<tr class="row">
								<td class="txtc">
									<label for="entrpsChb${status.count }" class="blind">선택</label>
									<input type="checkbox" name="nmenChoiseCbx" id="nmenChoiseCbx${status.count }" value="${data.VEND_REG_NO }">
									<input type="hidden" name="P_VEND_REG_NO" value="${data.VEND_REG_NO }">
								</td>
								<td class="txtc">${comFn:formatBizNumber(data.BIZRNO)}</td>
								<td class="txtl pl5">${data.VEND_NM}</td>
								<td class="txtl pl5">${data.RPRS_NM}</td>
								<td><input type="text" name="P_ATNPE_NM" value="${data.ATNPE_NM}"></td>
								<td><input type="text" name="P_ATNPE_TEL_NO" value="${data.ATNPE_TEL_NO}" tel></td>
								<td><input type="text" name="P_ATNPE_EMAL" value="${data.ATNPE_EMAL}" eMail></td>
								<td class="txtc">${comFn:formatDate(data.REG_DT,'yyyyMMddHHmmss','yyyy-MM-dd') }</td>
							</tr>
						</c:forEach>
	                </tbody>
	                <tbody>
		               	<tr class="row" id="nmenChoiseEmpty" <c:if test="${not empty bidDcPeoPartcptEntrpsList}"> style="display:none;"</c:if>>
		               		<td colspan="8"  class="txtc">선택된 업체가 없습니다.</td>
		               	</tr>
	               	</tbody>
	            </table>
			</form>
        </div>
		
		<div class="btn_wrap view_btn">
			<button type="button" class="btn btn_m btn_orange" id="saveBtn">저장</button>
	    	<button type="button" class="btn btn_m btn_del" id="listBtn">목록</button>
	    </div>
	</div>
</div>	
	
<form id="listFrm" method="POST">
	<input type="hidden" name="resourceName" value="${param.resourceName}">
</form>
<form id="popupFrm" method="POST">
	<input type="hidden" name="resourceName" value="${param.resourceName}">
	<input type="hidden" name="setMulti" value="Y">
</form>
<form id="bidWrtancDetailPopupFrm" method="POST" action="">
	<input type="hidden" name="P_ANNC_NO" value="${bidDcPeoDetail.ANNC_NO}">
	<input type="hidden" name="P_ANNC_NGR" value="${bidDcPeoDetail.ANNC_NGR}">
	<input type="hidden" name="P_ROUND_NO" value="${bidDcPeoDetail.ROUND_NO}">
</form>