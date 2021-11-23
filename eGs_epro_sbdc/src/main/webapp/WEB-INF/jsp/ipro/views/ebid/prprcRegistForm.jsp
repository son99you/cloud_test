<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 입찰관리 > 상세> 입찰서 제출 폼
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

<script type="text/javascript" src="${jsPath}/opro/views/ebid/prprcRegistForm.js"></script>
 
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
	
	<form id="bipaPresentnFrm" method="POST" enctype="multipart/form-data">
		<input type="hidden" name="resourceName" value="${param.resourceName}">
        <input type="hidden" name="P_ANNC_NO" value="${myPartcptReqstSttusDetail.ANNC_NO}"/>
		<input type="hidden" name="P_ANNC_NGR" value="${myPartcptReqstSttusDetail.ANNC_NGR}"/>
		<input type="hidden" name="P_ROUND_NO" value="${myPartcptReqstSttusDetail.ROUND_NO}"/>
		<input type="hidden" id="bizrNo" value="${sessionScope.loginResult.BIZR_NO}"/>
		<input type="hidden" id="sessionKey" name="P_SSN_VKEY" value=""/><%-- 암호화된 세션키 --%>
		<textarea id="userSignCert" name="P_USER_SIGN_CERT" style="display: none"></textarea><!--사용자 인증서 정보 -->
		<input type="hidden" id="userCertDn" name="P_CERT_DN" value=""/>
		<input type="hidden" id="encptBddprValue" name="P_TNDR_AMT_ENC" value=""/><%-- 암호화된 금액 --%>
		<input type="hidden" id="encRandom" name="P_ENC_RANDOM" value=""/><%-- 사용자 인증서에서 추출한 랜덤값 --%>
		<input type="hidden" id="encSsn" name="P_ENC_SSN" value=""/><%-- 암호화된 사용자 ID --%>
		<input type="hidden" id="P_VEND_REG_NO" name="P_VEND_REG_NO" value="${sessionScope.loginResult.USR_ID }">	
		<input type="hidden" id="P_ESTT_PRCE" value="${myPartcptReqstSttusDetail.ESTT_PRCE }">
		<input type="hidden" name="P_RETURN_URL" value="${param.P_RETURN_URL }">
		<input type="hidden" name="P_PRCDC_SBMT_YN" value="${myPartcptReqstSttusDetail.PRCDC_SBMT_YN }">
		<input type="hidden" name="P_PRPDC_ESS_YN" value="${myPartcptReqstSttusDetail.PRPDC_ESS_YN }">
		<input type="hidden" id="P_ORG_VAL_CLEAN" name="P_ORG_VAL_CLEAN" value="">
		<input type="hidden" id="P_ORG_VAL_BID" name="P_ORG_VAL_BID" value="">
		<input type="hidden" id="P_ORG_VAL_GRNT" name="P_ORG_VAL_GRNT" value="">
		<input type="hidden" id="P_ORG_HASH_VAL_CLEAN" name="P_ORG_HASH_VAL_CLEAN" value="">
		<input type="hidden" id="P_ORG_HASH_VAL_BID" name="P_ORG_HASH_VAL_BID" value="">
		<input type="hidden" id="P_ORG_HASH_VAL_GRNT" name="P_ORG_HASH_VAL_GRNT" value="">
		<input type="hidden" id="P_SIGN_VAL_CLEAN" name="P_SIGN_VAL_CLEAN" value="">
		<input type="hidden" id="P_SIGN_VAL_BID" name="P_SIGN_VAL_BID" value="">
		<input type="hidden" id="P_SIGN_VAL_GRNT" name="P_SIGN_VAL_GRNT" value="">
		
		<input type="hidden" id="P_FILE_GROUP_FLAG" name="P_FILE_GROUP_FLAG" value="tndr">
		<input type="hidden" id="P_BID_TPI_SECD" name="P_BID_TPI_SECD" value="OP05">
		<input type="hidden" id="P_BID_SBMT_FSCD" name="P_BID_SBMT_FSCD" value="DO02">
		
		<input type="hidden" id="P_FILE_GRP_NO" name="P_FILE_GRP_NO" value="${bidDo01FileInfo.FILE_GRP_NO}">

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
					        	<th scope="row">복수예비가격범위</th>
					            <td>
				            		${myPartcptReqstSttusDetail.PLR_ESTPC_RNG_CD_NM}
								</td>					        	
					        </tr>
						</tbody>
					</table>
				</div>
				
				<div class="tit_area">
					<h4 class="tit">제안서/규격서 제출</h4>
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
			                        <input type="text" id="P_CHRGR_NM" name="P_CHRGR_NM" maxlength="30" value="${bidPartcptTndrInfo.CHRGR_NM}" style="width: 95%">
			                    </td>
			                </tr>
			                <tr>
			                	<th class="bullet_orange">담당자전화번호</th>
			                    <td>
			                        <input type="text" id="P_TEL_NO" name="P_TEL_NO" maxlength="30" value="${bidPartcptTndrInfo.TEL_NO}" style="width: 95%" tel>
			                    </td>
			                    <th class="bullet_orange">담당자이메일</th>
			                    <td>
			                        <input type="text" id="P_EMAL_ADDR" name="P_EMAL_ADDR" maxlength="50" value="${bidPartcptTndrInfo.EMAL_ADDR}" eMail style="width: 95%">
			                    </td>
			                </tr>
		                </tbody>
		            </table>
        		</div>
				<div class="tit_area">
	            	<h4 class="tit">제안서 파일</h4>
				</div>						
	            <div class="view_area fileViewer">
					<!-- 업로드 삽입. -->
					<script type="text/javascript">
						DEXT5UPLOAD.config.Mode = 'upload';
						DEXT5UPLOAD.config.Width = '100%';
						DEXT5UPLOAD.config.FolderNameRule = '/bid';
						var dext5Upload = new Dext5Upload("upload");
					</script>	            	
				</div>
				<div id="upload_fileInfo"></div>
				
			    <div class="btn_wrap view_btn">
					<button type="button" class="btn btn_m btn_orange" id="prprcPresentnBtn">제안서제출</button>
			    	<button type="button" class="btn btn_m btn_del" id="listBtn" >목록</button>
			    </div>				
        	</div>
        </fieldset>
	</form>
</div>

<%--page move form --%> 
<form id="listFrm" class="search_form" method="POST" > 
	<input type="hidden" name="resourceName" value="${param.resourceName}">
	<input type="hidden" name="P_ANNC_NO" value="${myPartcptReqstSttusDetail.ANNC_NO}"/>
	<input type="hidden" name="P_ANNC_NGR" value="${myPartcptReqstSttusDetail.ANNC_NGR}"/>
	<input type="hidden" name="P_ROUND_NO" value="${myPartcptReqstSttusDetail.ROUND_NO}"/>
	<input type="hidden" name="P_RETURN_URL" value="${param.P_RETURN_URL}"/>
	
</form>

<form id="bidPartcptAbandnFrm" method="POST" >
	<input type="hidden" name="resourceName" value="${param.resourceName}">
	<input type="hidden" name="P_ANNC_NO" value="${myPartcptReqstSttusDetail.ANNC_NO}"/>
	<input type="hidden" name="P_ANNC_NGR" value="${myPartcptReqstSttusDetail.ANNC_NGR}"/>
	<input type="hidden" name="P_ROUND_NO" value="${myPartcptReqstSttusDetail.ROUND_NO}"/>
	<input type="hidden" name="P_BID_NM" value="${myPartcptReqstSttusDetail.BID_NM}"/>
</form>
	
<%-- 팝업 폼 --%>
<form id="popupFrm" method="POST" action="">
	<input type="hidden" name="P_ANNC_NO" value="${myPartcptReqstSttusDetail.ANNC_NO}">
	<input type="hidden" name="P_ANNC_NGR" value="${myPartcptReqstSttusDetail.ANNC_NGR}">
	<input type="hidden" name="P_ROUND_NO" value="${myPartcptReqstSttusDetail.ROUND_NO}"/>
	<input type="hidden" id="useAgreeYN" value="N">
</form>
	
<%-- 입찰가능검증 폼 --%>
<form id="bidPosblAtVrifyFrm" method="POST" action="">
	<input type="hidden" name="P_ANNC_NO" value="${myPartcptReqstSttusDetail.ANNC_NO}">
	<input type="hidden" name="P_ANNC_NGR" value="${myPartcptReqstSttusDetail.ANNC_NGR}">
	<input type="hidden" name="P_ROUND_NO" value="${myPartcptReqstSttusDetail.ROUND_NO}"/>
	<input type="hidden" name="P_SECD" value="BIDC_SBMT">
</form>
<%-- 파일 VIEW 폼 --%>
<form id="fileViewFrm" method="POST">
	<input type="hidden" id="P_VIEW_ATCHMNFL_GROUP_NO" name="P_VIEW_ATCHMNFL_GROUP_NO" value="${bidDo01FileInfo.FILE_GRP_NO}">
</form>