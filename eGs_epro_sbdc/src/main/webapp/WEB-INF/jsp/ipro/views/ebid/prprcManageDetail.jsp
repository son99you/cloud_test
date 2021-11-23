<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 입찰관리 > 제안/규격서검토 상세
 *
 * <pre>
 * ebid 
 *    |_ prprcManageDetail.jsp
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

<script type="text/javascript" src="${jsPath}/ipro/views/ebid/prprcManageDetail.js"></script> 

<div class="contents_wrap">
	<ul class="step_wrap">
		<li><a href="#">${myMenuList.bigMenuNm}</a></li>
		<li><a href="#">${myMenuSubList.smallMenuNm}</a></li>
	</ul>
	<div class="tit_wrap">
		<h3 class="tit">제안/규격서검토 상세</h3>
	</div>

	<div class="view_wrap typeB">
		<div class="tit_area">
			<h4 class="tit">입찰개요</h4>
			<div class="btn_right">
				<button type="button" class="btn btn_s2 btn_c2" id="detailBtn" style="width: 100px;">입찰공고문 상세</button>
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
						<c:if test="${empty bidPrprcDetail.BFAN_NO_LIST}">사전공고번호가 없습니다.</c:if>
						<c:if test="${not empty bidPrprcDetail.BFAN_NO_LIST}">${bidPrprcDetail.BFAN_NO_LIST }</c:if>
					</td>
	            </tr>
		    	<tr>
					<th>공고번호</th>
					<td>${bidPrprcDetail.ANNC_NO}-${bidPrprcDetail.ANNC_NGR}</td>
	                <th>공고일자</th>
	                <td>
	                    ${comFn:formatDate(bidPrprcDetail.ANNC_DT,'yyyyMMddHHmmss','yyyy-MM-dd')}
	                </td>
	            </tr>
	            <tr>
	                <th>입찰명</th>
	                <td colspan="3">
	                	<c:if test="${bidPrprcDetail.EMRG_YN eq 'Y'}"><font color="red">[긴급] </font></c:if>
						<c:if test="${bidPrprcDetail.ANNC_NGR > 1}"><font color="red">[정정] </font></c:if>
	                	${bidPrprcDetail.BID_NM}
	                </td>
	            </tr>
	            <tr>
	                <th>계약구분</th>
	                <td>
	                    ${bidPrprcDetail.CONT_SECD_NM}
	                    <c:if test="${bidPrprcDetail.CONT_DECD eq '200'}">&nbsp;/&nbsp;${bidPrprcDetail.CONT_DECD_NM}</c:if>
	                </td>
	                <th>계약방법</th>
	                <td>
	                    ${bidPrprcDetail.CONT_MTCD_NM}
	                </td>
	            </tr>
	            <tr>
	                <th>낙찰방법</th>
	                <td>
	                    ${bidPrprcDetail.SBID_MTCD_NM}
	                </td>        
                    <th scope="row"><label>추정금액 (원)</label></th>
                    <td>
                        ${comFn:formatMoney(bidPrprcDetail.ESTT_AMT)}
                    </td>
		        </tr>
		        <tr>
		        	<th scope="row">예가방식</th>
		            <td>
	            		${bidPrprcDetail.ESTPC_SECD_NM}
					</td>
		        	<th scope="row"><label <c:if test="${bidPrprcDetail.ESTPC_SECD eq '180002'}"> style="display:none;"</c:if>>기초금액 (원)</label></th>
		        	<td>
		        		<c:if test="${bidPrprcDetail.ESTPC_SECD ne '180002'}">
		        			${comFn:formatMoney(bidPrprcDetail.BASE_AMT)}
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
					<td>${comFn:formatDate(bidPrprcDetail.ANNC_DT,'yyyyMMddHHmmss','yyyy-MM-dd HH:mm')}&nbsp;</td>
				</tr>
				<c:if test="${bidPrprcDetail.ROUND_NO < 2}">
				<c:if test="${bidPrprcDetail.BID_BRFS_YN eq 'Y' }">
					<tr>
						<th>입찰설명회일시</th>
						<td>${comFn:formatDate(bidPrprcDetail.BRFS_DT,'yyyyMMddHHmmss','yyyy-MM-dd HH:mm')}&nbsp;&nbsp;&nbsp; 
						</td>
					</tr>
				</c:if>
				<c:if test="${bidPrprcDetail.PRPDC_ESS_YN eq 'Y'}">
				<tr>
					<th>제안서제출기간</th>
					<td>${comFn:formatDate(bidPrprcDetail.PRPDC_SBMT_STDT,'yyyyMMddHHmmss','yyyy-MM-dd HH:mm')}&nbsp;
							&nbsp; ~ &nbsp;
						   ${comFn:formatDate(bidPrprcDetail.PRPDC_SBMT_ENDT,'yyyyMMddHHmmss','yyyy-MM-dd HH:mm')}&nbsp;
					</td>
				</tr>
				</c:if>
				</c:if>
				<c:if test="${bidPrprcDetail.SBID_MTCD ne '34' or ( bidPrprcDetail.SBID_MTCD eq '34' and not empty bidPrprcDetail.BIDC_SBMT_STDT )}">
				<tr>
					<th>입찰서제출기간</th>
					<td>${comFn:formatDate(bidPrprcDetail.BIDC_SBMT_STDT,'yyyyMMddHHmmss','yyyy-MM-dd HH:mm')}&nbsp;
							&nbsp; ~ &nbsp;
						   ${comFn:formatDate(bidPrprcDetail.BIDC_SBMT_ENDT,'yyyyMMddHHmmss','yyyy-MM-dd HH:mm')}&nbsp;
						   <input type="hidden" id="P_BIDC_SBMT_ENDT" value="${bidPrprcDetail.BIDC_SBMT_ENDT }">
					</td>
				</tr>
				</c:if>
				<c:if test="${bidPrprcDetail.SBID_MTCD ne '34' or ( bidPrprcDetail.SBID_MTCD eq '34' and not empty bidPrprcDetail.BIDC_SBMT_STDT )}">
				<tr>
					<th>개찰일시</th>
					<td>${comFn:formatDate(bidPrprcDetail.OPNG_DT,'yyyyMMddHHmmss','yyyy-MM-dd HH:mm')}&nbsp;</td>
				</tr>
				</c:if>
		    </table>
		</div>
	</div>	
	
	<br/>
		
	<div class="view_wrap typeA">		
		<c:if test="${(bidPrprcDetail.SBID_MTCD ne '34' and toDayTime > bidPrprcDetail.BIDC_SBMT_ENDT) or (bidPrprcDetail.SBID_MTCD and '34' or bidPrprcDetail.PRPDC_SBMT_ENDT < toDayTime )}">
			<div class="tit_area">
				<h4 class="tit">제안서/규격서 검토</h4>
			</div>
			<div class="view_area">
				<table>
					<caption>제안서/규격서 검토 목록</caption>
					<colgroup>
	                   	<col style="width: 11%;">
	                   	<col style="width: 11%;">
	                   	<col style="width: auto;">
	                   	<col style="width: 9%;">
	                   	<col style="width: 10%;">
	                   	<col style="width: 12%;">
	                   	<col style="width: 12%;">
	                   	<col style="width: 10%;">
		            </colgroup>
			    	<tr>
			            <th class="txtc">제안/규격서검토</th>
			            <th class="txtc">사업자번호</th>
			            <th class="txtc">업체명</th>
			            <th class="txtc">담당자</th>
			            <th class="txtc">전화번호</th>
			            <th class="txtc">이메일</th>
			            <th class="txtc">제출일자</th>
			            <th class="txtc">제안서/규격서</th>
			        </tr>
			        <tbody id="entrpsListFormTbody">
						<c:if test="${empty bidPrprcEntrpsList}">
							<tr>
								<td colspan="8" class="txtc">참가업체 정보가 없습니다.</td>
							</tr>
						</c:if>
						<c:if test="${not empty bidPrprcEntrpsList}">
							<c:forEach var="data" items="${bidPrprcEntrpsList}" varStatus="status">
								<tr>
									<td class="txtc">
										<c:if test="${empty data.ESTM_ELCD}">
											<button type="button" class="btn btn_s btn_sch" onclick="dfDcdRegist('${data.VEND_REG_NO}');">검토전</button>									
										</c:if>
										<c:if test="${not empty data.ESTM_ELCD}">
											<button type="button" class="btn btn_s btn_sch" onclick="dfDcdDetail('${data.VEND_REG_NO}');">${data.ESTM_ELCD_NM}</button>
										</c:if>
									</td>
									<td class="txtc">${comFn:formatBizNumber(data.BIZRNO)}&nbsp;</td>
									<td>${data.VEND_NM}&nbsp;</td>
									<td class="txtc">${data.CHRGR_NM}</td>
									<td class="txtc">${data.TEL_NO}</td>
									<td>${data.EMAL_ADDR}</td>								
									<td class="txtc">${comFn:formatDate(data.SIGN_DT,'yyyyMMddHHmmss','yyyy-MM-dd HH:mm') }&nbsp;</td>
									<td class="hid txtc">
										<button type="button" class="btn btn_s btn_sch" onclick="bidPartcptPrprcDocInqire('${data.VEND_REG_NO}');">보기</button>
									</td>
								</tr>
							</c:forEach>
				 		</c:if>
		            </tbody>
			    </table>
		    </div>
	    </c:if>
	    
	    <br/>
	    
	    <div class="btn_wrap">
	    	<c:if test="${bidPrprcDetail.SBID_MTCD eq '34'}">
		    	<c:if test="${NOT_ELCD_EXT_YN ne 'Y'}">
			    	<c:if test="${empty bidPrprcDetail.BIDC_SBMT_ENDT}">
						<button type="button" class="btn btn_m btn_orange" id="tndrRegistBtn">입찰시간등록</button>
					</c:if>
				</c:if>
			</c:if>
			<button type="button" class="btn btn_m btn_orange" id="fibBtn">유찰</button>
			
			<!-- 2019-08-01 은잔디 수정 : 제안/규격서검토 재입찰 안함 -->
			<%-- <c:if test="${NOT_ELCD_EXT_YN ne 'Y'}">
				<button type="button" class="btn btn_m btn_orange" id="reBidBtn">재입찰</button>
			</c:if> --%>	    
	    	<button type="button" class="btn btn_m btn_del" id="listBtn">목록</button>
	    </div>
	</div>
</div>

<form id="detailReloadFrm" method="POST">
	<input type="hidden" name="P_ANNC_NO" value="${bidPrprcDetail.ANNC_NO}">
	<input type="hidden" name="P_ANNC_NGR" value="${bidPrprcDetail.ANNC_NGR}">
	<input type="hidden" name="P_ROUND_NO" value="${bidPrprcDetail.ROUND_NO}">
	<input type="hidden" name="resourceName" value="${param.resourceName}">
</form>
  
<form id="listFrm" method="POST">
	<input type="hidden" name="resourceName" value="${param.resourceName}">
</form>

<form id="popupFrm" method="POST">
	<input type="hidden" name="P_ANNC_NO" value="${bidPrprcDetail.ANNC_NO}">
	<input type="hidden" name="P_ANNC_NGR" value="${bidPrprcDetail.ANNC_NGR}">
	<input type="hidden" name="P_ROUND_NO" value="${bidPrprcDetail.ROUND_NO}">
	<input type="hidden" name="P_VEND_REG_NO">
	<input type="hidden" name="P_BID_SBMT_FSCD" value="DO02">
	<input type="hidden" name="P_BID_TPI_SECD" value="OP05">
	<input type="hidden" name="resourceName" value="${param.resourceName}">
</form>
<form id="bidWrtancDetailPopupFrm" method="POST">
	<input type="hidden" name="P_ANNC_NO" value="${bidPrprcDetail.ANNC_NO}">
	<input type="hidden" name="P_ANNC_NGR" value="${bidPrprcDetail.ANNC_NGR}">
	<input type="hidden" name="P_ROUND_NO" value="${bidPrprcDetail.ROUND_NO}">
</form>
<form id="fibFrm" method="POST">
  	<input type="hidden" name="P_ANNC_NO" value="${bidPrprcDetail.ANNC_NO }">
  	<input type="hidden" name="P_ANNC_NGR" value="${bidPrprcDetail.ANNC_NGR }">
  	<input type="hidden" name="P_ROUND_NO" value="${bidPrprcDetail.ROUND_NO}">
</form>
<form id="intrstBidEntrpsPopupFrm" method="POST">
	<input type="hidden" name="P_ANNC_NO" value="${bidPrprcDetail.ANNC_NO }">
  	<input type="hidden" name="P_ANNC_NGR" value="${bidPrprcDetail.ANNC_NGR }">
  	<input type="hidden" name="P_ROUND_NO" value="${bidPrprcDetail.ROUND_NO}">
</form>
