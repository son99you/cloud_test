<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 입찰관리 > 입찰참가검토 상세
 *
 * <pre>
 * ebid 
 *    |_ bidPartcptSttusDetail.jsp
 * 
 * </pre>
 * @date : 2017. 06. 14
 * @version : 1.0
 * @author : 은우소프트 전상훈
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %> 
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %> 

<script type="text/javascript" src="${jsPath}/ipro/views/ebid/bidPartcptSttusDetail.js"></script> 

<div class="contents_wrap">
	<ul class="step_wrap">
		<li><a href="#">${myMenuList.bigMenuNm}</a></li>
		<li><a href="#">${myMenuSubList.smallMenuNm}</a></li>
	</ul>
	<div class="tit_wrap">
		<h3 class="tit">입찰참가검토 상세</h3>
	</div>

	<div class="view_wrap typeB">
		
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
		    	<tr class="line">
					<th>사전공고번호</th>
					<td colspan="3">
						<c:if test="${empty bidPartcptSttusDetail.BFAN_NO_LIST}">사전공고번호가 없습니다.</c:if>
						<c:if test="${not empty bidPartcptSttusDetail.BFAN_NO_LIST}">${bidPartcptSttusDetail.BFAN_NO_LIST }</c:if>
					</td>
	            </tr>
		    	<tr>
					<th>공고번호</th>
					<td>${bidPartcptSttusDetail.ANNC_NO}-${bidPartcptSttusDetail.ANNC_NGR}</td>
	                <th>공고일자</th>
	                <td>${comFn:formatDate(bidPartcptSttusDetail.ANNC_DT,'yyyyMMddHHmmss','yyyy-MM-dd')}</td>
	            </tr>
	            <tr>
	                <th>입찰명</th>
	                <td colspan="3">
	                	<c:if test="${bidPartcptSttusDetail.EMRG_YN eq 'Y'}"><font color="red">[긴급] </font></c:if>
						<c:if test="${bidPartcptSttusDetail.ANNC_NGR > 1}"><font color="red">[정정] </font></c:if>
	                	${bidPartcptSttusDetail.BID_NM}
	                </td>
	            </tr>
	            <tr>
	                <th scope="row">계약구분</th>
	                <td>
	                    ${bidPartcptSttusDetail.CONT_SECD_NM}
	                    <c:if test="${bidPartcptSttusDetail.CONT_DECD eq '200'}">&nbsp;/&nbsp;${bidPartcptSttusDetail.CONT_DECD_NM}</c:if>
	                </td>
	                <th scope="row">계약방법</th>
	                <td>${bidPartcptSttusDetail.CONT_MTCD_NM}</td>
	            </tr>
	            <tr>
	                <th scope="row">낙찰방법</th>
	                <td>${bidPartcptSttusDetail.SBID_MTCD_NM}</td>        
                    <th scope="row"><label>추정금액 (원)</label></th>
                    <td>${comFn:formatMoney(bidPartcptSttusDetail.ESTT_AMT)}</td>
		        </tr>
		        <tr>
		        	<th scope="row">예가방식</th>
		            <td>${bidPartcptSttusDetail.ESTPC_SECD_NM}</td>
		        	<th scope="row"><label <c:if test="${bidPartcptSttusDetail.ESTPC_SECD eq '180002'}"> style="display:none;"</c:if>>기초금액 (원)</label></th>
		        	<td>
		        		<c:if test="${bidPartcptSttusDetail.ESTPC_SECD ne '180002'}">
		        			${comFn:formatMoney(bidPartcptSttusDetail.BASE_AMT)}
		        		</c:if>
		        	</td>
                </tr>
		    </table>
		</div>
	
		<div class="tit_area">
			<h4 class="tit">입찰진행</h4>
		</div>
		<div class="view_area">
			<table>
		    	<colgroup>
		        	<col style="width: 15%;">
		        	<col style="width: 85%;">
		        </colgroup>
		        <tr class="line">
					<th>입찰공고게시일시</th>
					<td>${comFn:formatDate(bidPartcptSttusDetail.ANNC_DT,'yyyyMMddHHmmss','yyyy-MM-dd HH:mm')}&nbsp;</td>
				</tr>
				<c:if test="${bidPartcptSttusDetail.ROUND_NO < 2}">
				<c:if test="${bidPartcptSttusDetail.BID_BRFS_YN eq 'Y' }">
					<tr>
						<th>입찰설명회일시</th>
						<td>${comFn:formatDate(bidPartcptSttusDetail.BRFS_DT,'yyyyMMddHHmmss','yyyy-MM-dd HH:mm')}&nbsp;&nbsp;&nbsp; 
						</td>
					</tr>
				</c:if>
				<c:if test="${bidPartcptSttusDetail.PRPDC_ESS_YN eq 'Y'}">
				<tr>
					<th>제안서제출기간</th>
					<td>${comFn:formatDate(bidPartcptSttusDetail.PRPDC_SBMT_STDT,'yyyyMMddHHmmss','yyyy-MM-dd HH:mm')}&nbsp;
							&nbsp; ~ &nbsp;
						   ${comFn:formatDate(bidPartcptSttusDetail.PRPDC_SBMT_ENDT,'yyyyMMddHHmmss','yyyy-MM-dd HH:mm')}&nbsp;
					</td>
				</tr>
				</c:if>
				</c:if>
				<tr>
					<th>입찰서제출기간</th>
					<td>${comFn:formatDate(bidPartcptSttusDetail.BIDC_SBMT_STDT,'yyyyMMddHHmmss','yyyy-MM-dd HH:mm')}&nbsp;
							&nbsp; ~ &nbsp;
						   ${comFn:formatDate(bidPartcptSttusDetail.BIDC_SBMT_ENDT,'yyyyMMddHHmmss','yyyy-MM-dd HH:mm')}&nbsp;
						   <input type="hidden" id="P_BIDC_SBMT_ENDT" value="${bidPartcptSttusDetail.BIDC_SBMT_ENDT }">
					</td>
				</tr>
				<tr>
					<th>개찰일시</th>
					<td>${comFn:formatDate(bidPartcptSttusDetail.OPNG_DT,'yyyyMMddHHmmss','yyyy-MM-dd HH:mm')}&nbsp;</td>
				</tr>
		    </table>
		</div>
	
		<c:if test="${toDayTime > bidPartcptSttusDetail.BIDC_SBMT_ENDT  }">
			<div class="tit_area">
				<h4 class="tit">투찰업체</h4>
			</div>
			<div class="view_area" style="overflow: auto;">
				<table style="width: 120%;">
					<caption>투찰업체 목록</caption>
					<colgroup>
	                   	<col style="width: 6%;">
	                   	<col style="width: 8%;">
	                   	<col style="width: 10%;">
	                   	<col style="width: 8%;">
	                   	<col style="width: 8%;">
	                   	<col style="width: 8%;">
	                   	<col style="width: 12%;">
	                   	<col style="width: 10%;">
	                   	<col style="width: 6%;">
	                   	<col style="width: 6%;">
	                   	<c:if test="${bidPartcptSttusDetail.ASSO_SPDM_CD ne '240000'}">
	                   		<col style="width: 8%;">
	                   	</c:if>
		            </colgroup>
		            <thead>
				    	<tr>
				            <th scope="col" class="txtc">참가검토</th>
				            <th scope="col" class="txtc">사업자번호</th>
				            <th scope="col" class="txtc">업체명</th>
				            <th scope="col" class="txtc">대표자</th>
				            <th scope="col" class="txtc">담당자</th>
				            <th scope="col" class="txtc">전화번호</th>
				            <th scope="col" class="txtc">이메일</th>
				            <th scope="col" class="txtc">투찰일시</th>
				            <th scope="col" class="txtc">참가서류</th>
				            <th scope="col" class="txtc">보증정보</th>
				            <c:if test="${bidPartcptSttusDetail.ASSO_SPDM_CD ne '240000'}">
				            	<th scope="col" class="txtc">공동업체정보</th>
				            </c:if>
				        </tr>
		            </thead>
			        <tbody id="entrpsListFormTbody">
						<c:if test="${empty bidPartcptEntrpsList}">
							<tr>
								<td colspan="10" class="txtc">참가업체 정보가 없습니다.</td>
							</tr>
						</c:if>
						<c:if test="${not empty bidPartcptEntrpsList}">
							<c:forEach var="data" items="${bidPartcptEntrpsList}" varStatus="status">
								<tr>
									<td class="txtc">
										<c:if test="${empty data.BID_ELCD}">
											<button type="button" class="btn btn_s btn_sch" onclick="dfDcdRegist('${data.VEND_REG_NO}');">검토전</button>									
										</c:if>
										<c:if test="${not empty data.BID_ELCD}">
											<button type="button" class="btn btn_s btn_sch" onclick="dfDcdDetail('${data.VEND_REG_NO}');">${data.BID_ELCD_NM}</button>
										</c:if>
									</td>
									<td class="txtc">${comFn:formatBizNumber(data.BIZRNO)}&nbsp;</td>
									<td class="txtl pl5">${data.VEND_NM}&nbsp;</td>
									<td class="txtc">${data.RPRS_NM}</td>
									<td class="txtc">${data.CHRGR_NM}</td>
									<td class="txtc">${data.TEL_NO}</td>
									<td class="txtl pl5">${data.EMAL_ADDR}</td>
									<td class="txtc">${comFn:formatDate(data.SIGN_DT,'yyyyMMddHHmmss','yyyy-MM-dd HH:mm') }&nbsp;</td>
									<td class="txtc"><!-- 참가서류 -->
										<button type="button" class="btn btn_s btn_sch" onclick="bidPartcptReqstdocInqire('${data.VEND_REG_NO}');">보기</button>
									</td>
									<td class="hid txtc"><!-- 보증정보 -->
										<%-- <c:if test="${not empty data.BIDGR_AMT}">
											<button type="button" class="btn btn_s btn_sch" onclick="bidAssrncInfoDetail('${data.VEND_REG_NO}');">보기</button>
										</c:if> --%>
										<button type="button" class="btn btn_s btn_sch" onclick="bidAssrncInfoDetail('${data.VEND_REG_NO}');">보기</button>
									</td>
									<c:if test="${bidPartcptSttusDetail.ASSO_SPDM_CD ne '240000'}">
										<td class="txtc"><!-- 공동업체정보 -->
											<button type="button" class="btn btn_s btn_sch" onclick="bidAssoInfoInqire('${data.VEND_REG_NO}');">보기</button>
										</td>
									</c:if>
								</tr>
							</c:forEach>
				 		</c:if>
		            </tbody>
			    </table>
		    </div>
	    </c:if>
	</div>
	    
	    
    <div class="btn_wrap view_btn mt20">
		<button type="button" class="btn btn_m btn_orange" id="fibBtn">유찰</button>
    	<button type="button" class="btn btn_m btn_del" id="listBtn">목록</button>
    </div>
</div>

<form id="detailReloadFrm" method="POST">
	<input type="hidden" name="P_ANNC_NO" value="${bidPartcptSttusDetail.ANNC_NO}">
	<input type="hidden" name="P_ANNC_NGR" value="${bidPartcptSttusDetail.ANNC_NGR}">
	<input type="hidden" name="P_ROUND_NO" value="${bidPartcptSttusDetail.ROUND_NO}">
	<input type="hidden" name="resourceName" value="${param.resourceName}">
</form>
  
<form id="listFrm" method="POST">
	<input type="hidden" name="resourceName" value="${param.resourceName}">
</form>

<form id="popupFrm" method="POST">
	<input type="hidden" name="P_ANNC_NO" value="${bidPartcptSttusDetail.ANNC_NO}">
	<input type="hidden" name="P_ANNC_NGR" value="${bidPartcptSttusDetail.ANNC_NGR}">
	<input type="hidden" name="P_ROUND_NO" value="${bidPartcptSttusDetail.ROUND_NO}">
	<input type="hidden" name="P_VEND_REG_NO">
	<input type="hidden" name="P_BID_SBMT_FSCD" value="DO01">
	<input type="hidden" name="P_BID_TPI_SECD" value="OP01">
	<input type="hidden" name="resourceName" value="${param.resourceName}">
</form>
<form id="bidWrtancDetailPopupFrm" method="POST">
	<input type="hidden" name="P_ANNC_NO" value="${bidPartcptSttusDetail.ANNC_NO}">
	<input type="hidden" name="P_ANNC_NGR" value="${bidPartcptSttusDetail.ANNC_NGR}">
	<input type="hidden" name="P_ROUND_NO" value="${bidPartcptSttusDetail.ROUND_NO}">
</form>
<form id="fibFrm" method="POST">
  	<input type="hidden" name="P_ANNC_NO" value="${bidPartcptSttusDetail.ANNC_NO }">
  	<input type="hidden" name="P_ANNC_NGR" value="${bidPartcptSttusDetail.ANNC_NGR }">
  	<input type="hidden" name="P_ROUND_NO" value="${bidPartcptSttusDetail.ROUND_NO}">
</form>
<form id="intrstBidEntrpsPopupFrm" method="POST">
	<input type="hidden" name="P_ANNC_NO" value="${bidPartcptSttusDetail.ANNC_NO }">
  	<input type="hidden" name="P_ANNC_NGR" value="${bidPartcptSttusDetail.ANNC_NGR }">
  	<input type="hidden" name="P_ROUND_NO" value="${bidPartcptSttusDetail.ROUND_NO}">
</form>
