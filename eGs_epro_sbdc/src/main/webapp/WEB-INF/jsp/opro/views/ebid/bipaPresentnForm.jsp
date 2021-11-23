<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 입찰관리 > 입찰진행현황상세 > 입찰서제출
 *
 * <pre>
 * ebid
 *    |_ bipaPresentnForm.jsp
 * 
 * </pre>
 * @date : 2017.06.19
 * @version : 1.0 
 * @author : 은우소프트 이주연 
--%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 

<script type="text/javascript" src="${jsPath}/opro/views/ebid/bipaPresentnForm.js"></script>
 
<script src="${contextPath}/vestsign/vestsign.js"></script>
<script src="${contextPath}/vestsign/library/json3.min.js"></script>
<script src="${contextPath}/vestsign/vestsign_ew.js"></script>

<input type="hidden" id="serverKmCert" value="${comFn:getServerCert()}"> 
<input type="hidden" id="compnoPrdprcAtCd" value="${myPartcptReqstSttusDetail.ESTPC_SECD}">
<input type="hidden" id="prcureSeCd" value="${myPartcptReqstSttusDetail.CONT_SECD}">
<input type="hidden" id="uprcYn" value="${myPartcptReqstSttusDetail.UPRC_YN}">
<input type="hidden" id="ifcId" value="${myPartcptReqstSttusDetail.IFC_ID}">
<input type="hidden" id="bidgrSecd" value="${myPartcptReqstSttusDetail.BIDGR_SECD}">
<input type="hidden" id="errMsg" value="${msg}">

<div class="contents_wrap">
	<ul class="step_wrap">
		<li><a href="#">${myMenuList.bigMenuNm}</a></li>
		<li><a href="#">${myMenuSubList.smallMenuNm}</a></li>
	</ul>
	<div class="tit_wrap">
		<h3 class="tit">입찰서제출</h3>
	</div>
	
	<form id="bipaPresentnFrm_bid" method="POST" enctype="multipart/form-data">
		<input type="hidden" name="resourceName" value="${param.resourceName}">
        <input type="hidden" name="P_ANNC_NO" value="${myPartcptReqstSttusDetail.ANNC_NO}"/>
		<input type="hidden" name="P_ANNC_NGR" value="${myPartcptReqstSttusDetail.ANNC_NGR}"/>
		<input type="hidden" name="P_ROUND_NO" value="${myPartcptReqstSttusDetail.ROUND_NO}"/>
		<input type="hidden" id="bizrNo" value="${sessionScope.loginResult.BIZR_NO}"/>
		<input type="hidden" id="sessionKey" name="P_SSN_VKEY"/><%-- 암호화된 세션키 --%>
		<textarea id="userSignCert" name="P_USER_SIGN_CERT" style="display: none"></textarea><!--사용자 인증서 정보 -->
		<input type="hidden" id="userCertDn" name="P_CERT_DN"/>
		<input type="hidden" id="encptBddprValue" name="P_TNDR_AMT_ENC"/><%-- 암호화된 금액 --%>
		<input type="hidden" id="encRandom" name="P_ENC_RANDOM"/><%-- 사용자 인증서에서 추출한 랜덤값 --%>
		<input type="hidden" id="encSsn" name="P_ENC_SSN"/><%-- 암호화된 사용자 ID --%>
		<input type="hidden" id="P_VEND_REG_NO" name="P_VEND_REG_NO" value="${sessionScope.loginResult.USR_ID }">	
		<input type="hidden" id="P_ESTT_PRCE" value="${myPartcptReqstSttusDetail.ESTT_PRCE }">
		<input type="hidden" name="P_RETURN_URL" value="${param.P_RETURN_URL }">
		<input type="hidden" name="P_PRCDC_SBMT_YN" value="${myPartcptReqstSttusDetail.PRCDC_SBMT_YN }">
		<input type="hidden" name="P_PRPDC_ESS_YN" value="${myPartcptReqstSttusDetail.PRPDC_ESS_YN }">
		<input type="hidden" id="P_ORG_VAL_CLEAN" name="P_ORG_VAL_CLEAN">
		<input type="hidden" id="P_ORG_VAL_BID" name="P_ORG_VAL_BID">
		<input type="hidden" id="P_ORG_VAL_GRNT" name="P_ORG_VAL_GRNT">
		<input type="hidden" id="P_ORG_HASH_VAL_CLEAN" name="P_ORG_HASH_VAL_CLEAN">
		<input type="hidden" id="P_ORG_HASH_VAL_BID" name="P_ORG_HASH_VAL_BID">
		<input type="hidden" id="P_ORG_HASH_VAL_GRNT" name="P_ORG_HASH_VAL_GRNT">
		<input type="hidden" id="P_SIGN_VAL_CLEAN" name="P_SIGN_VAL_CLEAN">
		<input type="hidden" id="P_SIGN_VAL_BID" name="P_SIGN_VAL_BID">
		<input type="hidden" id="P_SIGN_VAL_GRNT" name="P_SIGN_VAL_GRNT">
		
		<input type="hidden" id="P_FILE_GROUP_FLAG" name="P_FILE_GROUP_FLAG" value="tndr">
		<input type="hidden" id="P_BID_TPI_SECD" name="P_BID_TPI_SECD" value="OP03">
		<input type="hidden" id="P_BID_SBMT_FSCD" name="P_BID_SBMT_FSCD" value="DO04">
		
		<input type="hidden" id="fileDivTotCnt" value="1">
		
		<c:if test="${not empty bidPartcptTndrInfo}">
			<input type="hidden" id="fileDivTot" value="2">
		</c:if>
		<c:if test="${empty bidPartcptTndrInfo}">
			<input type="hidden" id="fileDivTot" value="3">
		</c:if>
		<input type="hidden" id="P_FILE_GRP_NO" name="P_FILE_GRP_NO">
		
		<input type="hidden" id="P_BID_MTCD" value="${myPartcptReqstSttusDetail.BID_MTCD}">

		<fieldset>
		
			<div class="view_wrap typeB">
				<div class="tit_area">
					<h4 class="tit">입찰개요</h4>
				</div>
				<div class="view_area">
					<table>
						<caption>입찰개요</caption>
						<colgroup>
							<col width="15%">
							<col width="35%">
							<col width="15%">
							<col width="35%">
						</colgroup>
						<tbody>
							<tr class="line">
								<th>입찰구분</th>
								<td>
									${myPartcptReqstSttusDetail.BID_MTCD_NM}
								</td>
								<th>계약구분</th>
								<td>
									${myPartcptReqstSttusDetail.CONT_SECD_NM}
									<c:if test="${myPartcptReqstSttusDetail.CONT_DECD eq '200'}">&nbsp;/&nbsp;${myPartcptReqstSttusDetail.CONT_DECD_NM}</c:if>
								</td>
							</tr>
							<tr>
								<th>입찰공고명</th>
								<td colspan="3">
									<c:if test="${myPartcptReqstSttusDetail.EMRG_YN eq 'Y'}"><font color="red">[긴급] </font></c:if>
									<c:if test="${myPartcptReqstSttusDetail.ANNC_NGR > 1}"><font color="red">[정정] </font></c:if>
									${myPartcptReqstSttusDetail.BID_NM}
								</td>
							</tr>
							<tr>
			                    <th scope="row">기초금액 (원)</th>
					        	<td>
					        		<c:if test="${myPartcptReqstSttusDetail.ESTPC_SECD ne '180002'}">
					        			${comFn:formatMoney(myPartcptReqstSttusDetail.BASE_AMT)}
					        		</c:if>
					        	</td>
					        	<th scope="row"><c:if test="${myPartcptReqstSttusDetail.ESTPC_SECD eq '180000'}">복수예비가격범위</c:if></th>
					            <td>
				            		${myPartcptReqstSttusDetail.PLR_ESTPC_RNG_CD_NM}
								</td>					        	
					        </tr>
						</tbody>
					</table>
				</div>
				
				<%-- 예가여부가 [복수예가] 일 경우 --%>
        		<c:if test="${myPartcptReqstSttusDetail.ESTPC_SECD eq '180000'}">
				<div class="tit_area">
					<h4 class="tit">예가선택 [ 임의로 2개를 선택하세요.]</h4>
				</div>        		
				<%-- <div class="view_area">        		
		            <table>
		                <caption>복수예가선택</caption>
		                <colgroup>
		                    <col width="980px">
		                </colgroup>
		                <tbody>
			                <tr class="line">
								<td>
									<c:forEach begin="1" end="15" varStatus="loop">
										<label for="compnoPrdprcNo${ loop.count}" class="checkFormLayer">
											<input type="checkbox" class="right_15" id="compnoPrdprcNo${ loop.count }" name="P_PLR_ESTPC_NO" value="${loop.count}" style="border:0px;" onclick="checkPrdprcNo(this)"
											<c:if test="${not empty bidPartcptTndrInfo}">
											disabled
											<c:forEach var="data" items="${bidPartcptEsseList}" varStatus="status">
											<c:if test="${data.PLR_ESTPC_NO eq loop.count}">
											checked
											</c:if>
											</c:forEach>
											</c:if>
											>
										</label>&nbsp;&nbsp;&nbsp;
									</c:forEach>
								</td>
			                </tr>
		                </tbody>
		            </table>
	        	</div> --%>
				</c:if>
				<%-- 예가여부가 [복수예가] 일 경우 END--%>
				
				<div class="tit_area">
					<h4 class="tit">입찰금액</h4><span style="color: red; float: left; margin-left: 20px;">입찰유의서 등의 입찰문서에 의해서 세부 가격 산출내역이 필요한 입찰건의 경우 반드시 첨부하셔야 합니다.</span>
				</div>        		
				<div class="view_area"> 				
					<table>
		                <caption>입찰금액</caption>
		                <colgroup>
		                    <col width="15%">
		                    <col width="35%">
		                </colgroup>
		                <tbody>
		                	<c:if test="${myPartcptReqstSttusDetail.CONT_SECD eq '4'}">
		               	 		<tr>
			                		<th>통화</th>
			                		<td>${myPartcptReqstSttusDetail.CURR_SECD_NM}</td>
			                		<th>환율</th>
			                		<td>${myPartcptReqstSttusDetail.EXRT}</td>
			                	</tr>
		                	</c:if>
			                <tr class="line">
			                    <th class="bullet_orange">입찰금액 <span style="color: red">(<c:if test="${myPartcptReqstSttusDetail.CONT_SECD ne '4'}">원화</c:if><c:if test="${myPartcptReqstSttusDetail.CONT_SECD eq '4'}">달러</c:if> 기준)</span></th>
			                    <td id="abandnRegistDt" colspan="3">
			                		<c:if test="${empty bidPartcptTndrInfo}">
				                        <label for="P_BID_NM_S" class="blind">입찰금액</label>
				               			<input type="text" <c:if test="${not empty bidPrdlsList}">class="lineTxt w180 disabled" readonly</c:if> id="P_TNDR_AMT" name="P_TNDR_AMT" money maxlength="22" onkeyup="conversionNumToKor(this);">  &nbsp;(일금 <font color="red"></font>
				               			<c:if test="${myPartcptReqstSttusDetail.CONT_SECD ne '4'}">원)</c:if><c:if test="${myPartcptReqstSttusDetail.CONT_SECD eq '4'}">${myPartcptReqstSttusDetail.CURR_SECD_NM})</c:if>
				               			&nbsp;<c:if test="${myPartcptReqstSttusDetail.TNDR_AMT_STAX_YN eq 'Y'}"><span style="color:red;">부가세포함</span></c:if>
				               			<c:if test="${myPartcptReqstSttusDetail.TNDR_AMT_STAX_YN ne 'Y'}">부가세미포함</c:if>
			                    	</c:if>
									<c:if test="${not empty bidPartcptTndrInfo}">
									투찰이 완료되었습니다.
									</c:if>			                    	
			                    </td>
			                </tr>
		                </tbody>
		            </table>
        		</div>
        
        		<c:if test="${not empty bidPrdlsList}">
					<div class="tit_area">
						<h4 class="tit">입찰품목정보</h4>
					</div>
					<div class="view_area">
						<table>
				            <caption>입찰품목정보</caption>
				            <colgroup>
				                <col width="12%"/>
				                <col width="20%"/>
				                <col width="*"/>
				                <col width="6%"/>
				                <col width="10%"/>
				                <col width="8%"/>
				                <col width="10%"/>
				                <col width="10%"/>
				            </colgroup>
							<thead>
				                <tr>
				                    <th class="noBg txtc">품목분류</th>
				                    <th class="txtc">규격명</th>
				                    <th class="txtc">품목명</th>
				                    <th class="txtc">단위</th>
				                    <th class="txtc">추정단가<br/>(VAT포함)</th>
				                    <th class="txtc">수량</th>
				                    <th class="txtc">단가<br><span style="color: red">(부가세 포함)</span></th>
				                    <th class="txtc">금액</th>
				                </tr>
				            </thead>
							<tbody>
								<c:if test="${empty bidPrdlsList}">
									<tr class="row">
										<td colspan="8">입찰품목 정보가 없습니다.</td>
									</tr>
								</c:if>
								<c:if test="${not empty bidPrdlsList}">
									<c:forEach var="data" items="${bidPrdlsList}" varStatus="status">
										<tr class="row">
											<td class="txtc">
												${data.ITEM_NO}
												<c:if test="${empty bidPartcptTndrInfo}">
												<input type="hidden" name="P_ITEM_SN" value="${data.ITEM_SN}">
												<input type="hidden" name="P_ITEM_NO" value="${data.ITEM_NO}">
												</c:if>
											</td>
											<td style="text-align: left;">${data.STND_NM }</td> 
											<td style="text-align: left;">${data.ITEM_NM }</td>
											<td class="txtc">${data.ITEM_UNNM }</td>
											<td class="txtr pr5">${comFn:formatMoney(data.RQST_UNIT)}</td>
											<td style="text-align: right;">${data.ITEM_QTY }</td>
											<td class="txtc">
												<c:if test="${empty bidPartcptTndrInfo}">
													<input type="text" id="P_ITEM_UNIT_AMT" bidUntpcAmount maxlength="22" money onkeyup="prdlstAcctoAmount(this, '${data.ITEM_QTY}');" style="text-align: right;">
													<input type="hidden" id="bidUntpcEncptValue${status.index}" name="P_ITEM_UNIT_ENC">
													<input type="hidden" name="P_RQST_UNIT_ORG" value="${data.RQST_UNIT}">
												</c:if>
											</td>
											<td class="sumAmount txtr pr5"></td>
										</tr>
									</c:forEach>
								</c:if>
							</tbody>
						</table>
					</div>
				</c:if>
				
				<div id="upload_fileInfo"></div>
				<c:if test="${empty bidPartcptTndrInfo}">
					<div class="tit_area">
		            	<h4 class="tit" style="margin-top:0px;">세부가격 산출내역서(견적서)</h4>
						<div class="btn_left" style="float:left;">
							&nbsp;(&nbsp;제출여부&nbsp;:&nbsp; 
							<label for="on" class="mr_10"><input type="radio" name="P_FILE_TNDR_ONOF_YN" class="mr_5" value="Y"> 제출함</label>
				           	<label for="off" class="mr_10"><input type="radio" name="P_FILE_TNDR_ONOF_YN" class="mr_5" checked="checked" value="N"> 제출안함</label>
				           	&nbsp;)
						</div>		            	
					</div>
		            <div class="view_area fileViewer onOffTndrDiv">
						<!-- 업로드 삽입. -->
						<script type="text/javascript">
							DEXT5UPLOAD.config.Mode = 'upload';
							DEXT5UPLOAD.config.Width = '100%';
							DEXT5UPLOAD.config.FolderNameRule = '/bid';
							var dext5Upload = new Dext5Upload("upload");
						</script>
					</div>
        		</c:if>				
				
				<div class="tit_area">
					<h4 class="tit" style="margin-top:0px;">입찰참가서류문서</h4>
					<div class="btn_left" style="float:left;">
						&nbsp;(&nbsp;제출방법&nbsp;:&nbsp; 
						<label for="on" class="mr_10"><input type="radio" name="P_FILE_PRTC_ONOF_YN" class="mr_5" value="Y"> 온라인</label>
			           	<label for="off" class="mr_10"><input type="radio" name="P_FILE_PRTC_ONOF_YN" class="mr_5" checked="checked" value="N"> 오프라인</label>
			           	&nbsp;)
					</div>
				</div>        		
	            <div class="view_area fileViewer onOffPrtcDiv">
					<!-- 업로드 삽입. -->
					<script type="text/javascript">
						DEXT5UPLOAD.config.Mode = 'upload';
						DEXT5UPLOAD.config.Width = '100%';
						DEXT5UPLOAD.config.FolderNameRule = '/bid';
						var dext5Upload = new Dext5Upload("upload2");
					</script>	            	
				</div>
		
				<div class="tit_area">
					<h4 class="tit">입찰보증정보</h4>
				</div>
				<div class="view_area">
					<table>
						<caption>입찰보증정보</caption>
						<colgroup>
							<col width="15%">
							<col width="85%">
						</colgroup>
						<tbody>
							<tr class="line">
								<th>입찰업체구분</th>
								<td>
		                			<label class="mr_10" for="P_BID_VEND_SE">일반기업</label>
		                			<input type="radio" name="P_BID_VEND_SECD" class="mr_5" value="A" <c:if test="${empty bidPartcptGrntInfo.BID_VEND_SECD or bidPartcptGrntInfo.BID_VEND_SECD eq 'A'}">checked</c:if>>
		                			<label class="mr_10" for=P_BID_VEND_SE>공공기관</label>
		                			<input type="radio" name="P_BID_VEND_SECD" class="mr_5" value="B" <c:if test="${bidPartcptGrntInfo.BID_VEND_SECD eq 'B'}">checked</c:if>>
		                			<input type="hidden" name="P_BIDGR_SECD" class="mr_5" value="<c:if test="${empty bidPartcptGrntInfo.BID_VEND_SECD or bidPartcptGrntInfo.BID_VEND_SECD eq 'A'}">GRNT_STCK</c:if>">
								</td>
							</tr>
							<tr class="grntTr" <c:if test="${bidPartcptGrntInfo.BIDGR_SECD eq 'PAY_MMRD'}">style="display:none;"</c:if>>
								<th>보증금</th>
								<td>
									<input type="text" class="w250" name="P_BIDGR_AMT" money value="${comFn:formatMoney(bidPartcptGrntInfo.BIDGR_AMT)}">
								</td>
							</tr>
							<tr class="grntTr" <c:if test="${bidPartcptGrntInfo.BIDGR_SECD eq 'PAY_MMRD'}">style="display:none;"</c:if>>
								<th>보증기간</th>
								<td>
			                    	<div class="cddDiv">
				                        <input type="text" id="P_BIDGR_STDE" name="P_BIDGR_STDE" class="w120" date value="${comFn:formatDate(bidPartcptGrntInfo.BIDGR_STDE,'yyyyMMdd','yyyy-MM-dd')}">
				                        &nbsp;~&nbsp;
				                        <input type="text" id="P_BIDGR_ENDE" name="P_BIDGR_ENDE" class="w120" date value="${comFn:formatDate(bidPartcptGrntInfo.BIDGR_ENDE,'yyyyMMdd','yyyy-MM-dd')}">
			                    	</div>									
								</td>
							</tr>
							<tr class="grntTr" <c:if test="${bidPartcptGrntInfo.BIDGR_SECD eq 'PAY_MMRD'}">style="display:none;"</c:if>>
								<th>보증기관</th>
								<td>
									<input type="text" class="w250" name="P_BIDGR_AGNM" value="${bidPartcptGrntInfo.BIDGR_AGNM}">
								</td>
							</tr>
							<tr class="grntTr" <c:if test="${bidPartcptGrntInfo.BIDGR_SECD eq 'PAY_MMRD'}">style="display:none;"</c:if>>
								<th>보증번호</th>
								<td>
									<input type="text" class="w250" name="P_BIDGR_NO" value="${bidPartcptGrntInfo.BIDGR_NO}">
								</td>
							</tr>
						</tbody>
					</table>
				</div>
				
				<div class="tit_area">
	            	<h4 class="tit" style="margin-top:0px;">보증서 파일</h4>
					<div class="btn_left" style="float:left;">
						&nbsp;(&nbsp;제출방법&nbsp;:&nbsp; 
						<label for="on" class="mr_10"><input type="radio" name="P_FILE_GRNT_ONOF_YN" class="mr_5" value="Y"> 파일제출</label>
			           	<label for="off" class="mr_10"><input type="radio" name="P_FILE_GRNT_ONOF_YN" class="mr_5" checked="checked" value="N"> 파일제출안함</label>
			           	&nbsp;)
					</div>	            	
				</div>						
	            <div class="view_area fileViewer onOffGrntDiv">
					<!-- 업로드 삽입. -->
					<script type="text/javascript">
						DEXT5UPLOAD.config.Mode = 'upload';
						DEXT5UPLOAD.config.Width = '100%';
						DEXT5UPLOAD.config.FolderNameRule = '/bid';
						var dext5Upload = new Dext5Upload("upload3");
					</script>	            	
				</div>
				
				<div class="tit_area">
					<h4 class="tit">신청인정보</h4>
				</div>
				<div class="view_area">				
		            <table>
		                <caption>신청인정보</caption>
		                <colgroup>
		                    <col width="15%">
		                    <col width="35%">
		                    <col width="15%">
		                    <col width="35%">
		                </colgroup>
		                <tbody>
			                <tr class="line">
			                	<th>업체명</th>
			                    <td>${bidPartcptnSttus.VEND_NM }</td>
			                    <th>사업자번호</th>
			                    <td>${comFn:formatBizNumber(bidPartcptnSttus.BIZRNO)}</td>
			                </tr>
			                <tr>
			                	<th>대표자명</th>
			                    <td>${bidPartcptnSttus.RPRS_NM }</td>
			                    <th class="bullet_orange">담당자명</th>
			                    <td>
			                    	<c:if test="${empty bidPartcptTndrInfo}">
			                        	<input type="text" id="P_CHRGR_NM" name="P_CHRGR_NM" maxlength="30" value="${!empty bidPartcptTndrInfo.CHRGR_NM ? bidPartcptTndrInfo.CHRGR_NM : bidVendChrgr.USR_NM}">
			                    	</c:if>
			                    	<c:if test="${not empty bidPartcptTndrInfo}">
			                   	 		${bidPartcptTndrInfo.CHRGR_NM}
			                    	</c:if>
			                    </td>
			                </tr>
			                <tr>
			                	<th class="bullet_orange">담당자전화번호</th>
			                    <td>
			                    	<c:if test="${empty bidPartcptTndrInfo}">
			                        	<input type="text" id="P_TEL_NO" name="P_TEL_NO" maxlength="30" value="${!empty bidPartcptTndrInfo.TEL_NO ? bidPartcptTndrInfo.TEL_NO : bidVendChrgr.CP_NO}">
			                    	</c:if>
			                    	<c:if test="${not empty bidPartcptTndrInfo}">
			                    		${bidPartcptTndrInfo.TEL_NO}
			                    	</c:if>
			                    </td>
			                    <th class="bullet_orange">담당자이메일</th>
			                    <td>
			                    	<c:if test="${empty bidPartcptTndrInfo}">
			                     	   <input type="text" id="P_EMAL_ADDR" name="P_EMAL_ADDR" maxlength="30" value="${!empty bidPartcptTndrInfo.EMAL_ADDR ? bidPartcptTndrInfo.EMAL_ADDR : bidVendChrgr.EMAL_ADDR}">
			                    	</c:if>
			                    	<c:if test="${not empty bidPartcptTndrInfo}">
			                    		${bidPartcptTndrInfo.EMAL_ADDR}
			                    	</c:if>			                    
			                    </td>
			                </tr>
			                <tr>
			                	<th>업체주소</th>
			                    <td colspan="3">
			                        ${bidPartcptnSttus.ADDR_NM }&nbsp;${bidPartcptnSttus.ADDR_DENM }
			                    </td>
			                </tr>
		                </tbody>
		            </table>
        		</div>				
		
				<div class="tit_area">
	            	<h4 class="tit">입찰유의서 및 청렴이행각서</h4>
	            	<div class="btn_right">
			            <button type="button"  class="btn btn_s2 btn_c2" id="cleanBtn" style="width: 200px;">입찰유의서 및 청렴이행각서보기</button> 
			        </div>
				</div>
				
				<br/><br/><br/>
				
	            <div class="info_wrap">
			        <div class="blueBox">
						<p>동 입찰에 참여함에 있어 「조달 및 계약규정」등 관련규정이 정하는 바에 따르며, 본 입찰과 관련된 모든 세부사항을 숙지하였음을 확인합니다.</p>
						<p>동 입찰이 귀 한국전기연구원에 의하여 수락되면 계약상의 모든 조건에 따라 위의 금액으로 계약기간내에 업무를 완료할 것을 확약하며 입찰서를 제출합니다.</p>
						<c:if test="${empty bidPartcptTndrInfo}">
				            <label for="agreIemY" class="mr_10">
					           <input type="radio" id="agreIemY" name="agreIem" class="mr_5"> 동의함
				           </label>
				           <label for="agreIemN" class="mr_10">
					           <input type="radio" id="agreIemN" name="agreIem" class="mr_5"> 동의안함
							</label>
						</c:if>
			        </div>
				</div>
				
				<br/>
				
				<div class="tit_area">
	            	<h4 class="tit">자사정보</h4>
	            	<div class="btn_right">
			            <button type="button"  class="btn btn_s2 btn_c2" id="vendInfoConfirmBtn" style="width: 120px;">자사정보조회</button> 
			        </div>	            	
				</div>
				
	            <div class="info_wrap">
			        <div class="blueBox">
						<p>대표자, 상호 등 자사정보가 틀린 경우 입찰이 무효화 될 수 있음을 확인합니다.</p>
						<c:if test="${empty bidPartcptTndrInfo}">
			            <label for="agreIem2Y" class="mr_10">
				           <input type="radio" id="agreIem2Y" name="agreIem2" class="mr_5"> 동의함
			           </label>
			           <label for="agreIem2N" class="mr_10">
				           <input type="radio" id="agreIem2N" name="agreIem2" class="mr_5"> 동의안함
						</label>
						</c:if>
			        </div>
				</div>								
				
			    <div class="btn_wrap view_btn">
			    	<c:if test="${empty bidPartcptTndrInfo}">
						<button type="button" class="btn btn_m btn_orange" id="bipaPresentnBtn">입찰서제출</button>
					</c:if>
					<c:if test="${not empty bidPartcptTndrInfo}">
						<button type="button" class="btn btn_m btn_orange" id="do01FileRePresent">입찰참가서류수정</button>
					</c:if> 
			    	<button type="button" class="btn btn_m btn_del" id="listBtn">목록</button>
			    </div>
        	</div>
        </fieldset>
	</form>
</div>

<%--page move form --%> 
<form id="listFrm" class="search_form" method="POST"> 
	<input type="hidden" name="resourceName" value="${param.resourceName}">
	<input type="hidden" name="P_ANNC_NO" value="${myPartcptReqstSttusDetail.ANNC_NO}"/>
	<input type="hidden" name="P_ANNC_NGR" value="${myPartcptReqstSttusDetail.ANNC_NGR}"/>
	<input type="hidden" name="P_ROUND_NO" value="${myPartcptReqstSttusDetail.ROUND_NO}"/>
	<input type="hidden" name="P_RETURN_URL" value="${param.P_RETURN_URL}"/>
</form>

<form id="listFrm2" class="search_form" method="POST"> 
	<input type="hidden" name="resourceName" value="${param.resourceName}">
</form>

<form id="bidPartcptAbandnFrm" method="POST">
	<input type="hidden" name="resourceName" value="${param.resourceName}">
	<input type="hidden" name="P_ANNC_NO" value="${myPartcptReqstSttusDetail.ANNC_NO}"/>
	<input type="hidden" name="P_ANNC_NGR" value="${myPartcptReqstSttusDetail.ANNC_NGR}"/>
	<input type="hidden" name="P_ROUND_NO" value="${myPartcptReqstSttusDetail.ROUND_NO}"/>
	<input type="hidden" name="P_BID_NM" value="${myPartcptReqstSttusDetail.BID_NM}"/>
</form>
	
<%-- 팝업 폼 --%>
<form id="popupFrm" method="POST">
	<input type="hidden" name="P_ANNC_NO" value="${myPartcptReqstSttusDetail.ANNC_NO}">
	<input type="hidden" name="P_ANNC_NGR" value="${myPartcptReqstSttusDetail.ANNC_NGR}">
	<input type="hidden" name="P_ROUND_NO" value="${myPartcptReqstSttusDetail.ROUND_NO}"/>
	<input type="hidden" name="P_VEND_REG_NO" value="${sessionScope.loginResult.VEND_REG_NO}"/>
	<input type="hidden" id="useAgreeYN" value="N">
</form>
	
<%-- 입찰가능검증 폼 --%>
<form id="bidPosblAtVrifyFrm" method="POST">
	<input type="hidden" name="P_ANNC_NO" value="${myPartcptReqstSttusDetail.ANNC_NO}">
	<input type="hidden" name="P_ANNC_NGR" value="${myPartcptReqstSttusDetail.ANNC_NGR}">
	<input type="hidden" name="P_ROUND_NO" value="${myPartcptReqstSttusDetail.ROUND_NO}"/>
	<input type="hidden" name="P_SECD" value="BIDC_SBMT">
</form>

<form id="grntPopupFrm" method="POST">
	<input type="hidden" name="P_ANNC_NO" value="${myPartcptReqstSttusDetail.ANNC_NO}">
	<input type="hidden" name="P_ANNC_NGR" value="${myPartcptReqstSttusDetail.ANNC_NGR}">
	<input type="hidden" name="P_ROUND_NO" value="${myPartcptReqstSttusDetail.ROUND_NO}"/>
	<input type="hidden" name="P_BIDGR_SECD" />
</form>

<form id="cleanPopupFrm" method="POST">
	<input type="hidden" name="P_ANNC_NO" value="${myPartcptReqstSttusDetail.ANNC_NO}">
	<input type="hidden" name="P_ANNC_NGR" value="${myPartcptReqstSttusDetail.ANNC_NGR}">
	<input type="hidden" name="P_ROUND_NO" value="${myPartcptReqstSttusDetail.ROUND_NO}"/>
	<input type="hidden" name="P_CONT_SECD" value="${myPartcptReqstSttusDetail.CONT_SECD}"/>
	<input type="hidden" name="P_CONT_SECD_NM" value="${myPartcptReqstSttusDetail.CONT_SECD_NM}"/>
	<input type="hidden" name="P_VEND_NM" value="${bidPartcptnSttus.VEND_NM }">
	<input type="hidden" name="P_RPRS_NM" value="${bidPartcptnSttus.RPRS_NM }">
</form>
<%-- 파일 VIEW 폼 --%>
<form id="fileViewFrm" method="POST">
	<input type="hidden" id="P_VIEW_ATCHMNFL_GROUP_NO" name="P_VIEW_ATCHMNFL_GROUP_NO">
	<input type="hidden" id="P_VIEW_ATCHMNFL_GROUP_NO_TNDR_DOC" name="P_VIEW_ATCHMNFL_GROUP_NO_TNDR" value="${bidDo04FileInfo.FILE_GRP_NO}">
	<input type="hidden" id="P_VIEW_ATCHMNFL_GROUP_NO_PARTCPT_DOC" name="P_VIEW_ATCHMNFL_GROUP_NO_PART" value="${bidDo01FileInfo.FILE_GRP_NO}">
	<input type="hidden" id="P_VIEW_ATCHMNFL_GROUP_NO_GRNT" name="P_VIEW_ATCHMNFL_GROUP_NO_GRNT" value="${bidPartcptGrntInfo.FILE_GRP_NO}">
</form>