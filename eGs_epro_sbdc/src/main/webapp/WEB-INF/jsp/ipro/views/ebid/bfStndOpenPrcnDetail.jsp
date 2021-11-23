<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 계약설계 > 사전규격공개진행 상세
 *
 * <pre>
 * prpo 
 *    |_ bfStndOpenPrcnDetail.jsp
 * 
 * </pre>
 * @date : 2020. 09
 * @version : 1.0
 * @author : 은우소프트 
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %> 
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %> 

<script type="text/javascript" src="${jsPath}/ipro/views/ebid/bfStndOpenPrcnDetail.js"></script>
<script type="text/javascript" src="/raonkupload/js/raonkupload.js"></script>

<div class="sp_sec">
	<div class="nav_sec">
		<ul id="breadcrumbs">
			<li class="home">홈</li>
			<li>${myMenuList.bigMenuNm}</li>
			<li>${myMenuSubList.smallMenuNm}</li> 
		</ul>
	</div>
	<h3 class="sp_tit">사전규격공개진행 상세</h3>

	<form id="registFrm" method="POST" enctype="multipart/form-data">
		<input type="hidden" name="resourceName" value="${comFn:nvl(param.resourceName, resourceName)}">
		<input type="hidden" name="P_BFAN_NO"  value="${bfStndOpenPrcnDetail.BFAN_NO }">
		<input type="hidden" id="contSecd" name="contSecd"  value="${bfStndOpenPrcnDetail.CONT_SECD }">
		<input type="hidden" id="contMtcd" name="contMtcd"  value="${bfStndOpenPrcnDetail.CONT_MTCD }">
		
		<!-- raonk첨부파일그룹번호  -->
		<input type="hidden" id="P_FILE_GRP_NO_ETC" name="P_FILE_GRP_NO_ETC"  value="${bfStndOpenPrcnDetail.FILE_GRP_NO_ETC}"> 
		<!-- 필수 첨부파일 그룹번호-->
		<input type="hidden" id="P_FILE_GRP_NO" name="P_FILE_GRP_NO"  value="${bfStndOpenPrcnDetail.FILE_GRP_NO}">
		<div class="sp_cont">
			<c:if test="${not empty bfStndPrcnRtn }">
				<p class="spc_stit">반려사유</p>
				<table class="form_tb" summary="반려사유 입니다.">
	                <caption>반려사유</caption>
	                <colgroup>
	                    <col width="15%">
	                    <col width="*">
	                </colgroup>
	               	<tbody>
	               		<tr>
	               			<th>반려사유</th>
	              				<td>[${comFn:formatDate(bfStndPrcnRtn.PROC_DT,'yyyyMMddHHmmss','yyyy-MM-dd HH:mm:ss')}]&nbsp;${bfStndPrcnRtn.RMK}</td>
	               		</tr>
	               	</tbody>
				</table>
			</c:if>
			<p class="spc_stit">기본정보</p>
		
			<table class="form_tb" summary="기본정보 입니다.">
                <caption>기본정보</caption>
                <colgroup>
                    <col width="15%">
                    <col width="35%">
                    <col width="15%">
                    <col width="35%">
                </colgroup>
                <tbody>
	                <tr>
	                    <th>요구번호</th>
	                    <td>${bfStndOpenPrcnDetail.PCRQ_NO}</td>
	                    <th>사전규격요청번호</th>
	                    <td>${bfStndOpenPrcnDetail.BFAN_NO}</td>
	               </tr>
	               <tr>
	                    <th>진행상태</th>
	                    <td>${comFn:nvl(bfStndOpenPrcnDetail.BFAN_PSCD_NM,"작성대기")}</td>
	                    <th>결재상태</th>
	                    <td>${bfStndOpenPrcnDetail.APPR_STCD_NM}</td>
	                </tr>
	                <tr>
	                    <th>본지사</th>
	                    <td>
	                    	${bfStndOpenPrcnDetail.ARA_DEPT_CD_NM}
	                    </td>
	                    <th>작성부서</th>
	                    <td>${bfStndOpenPrcnDetail.RQR_DEPT_NM }</td>
	                </tr>
	                <tr>
	                    <th>계약구분</th>
	                    <td>
	                    	${bfStndOpenPrcnDetail.CONT_SECD_NM}
	                    </td>
	                    <th>작성자</th>
	                    <td>${bfStndOpenPrcnDetail.RQR_CHRGR_NM }</td>
	                </tr>
	                <tr>
	                	<th>감독원(담당자)</th>
	                	<td>
	                		${bfStndOpenPrcnDetail.MNGR_CHRGR_NM}
	                	</td>
	                	<th>검사원(부서장)</th>
	                	<td>
	                		${bfStndOpenPrcnDetail.CHCK_CHRGR_NM}
	                	</td>
	                </tr>
	                <tr>
	                	<th>계약명(사업명)</th>
	                	<td colspan="3">
	                		${bfStndOpenPrcnDetail.BFAN_NM }
	                	</td>
	                </tr>
	                <tr>
	                	<th>계약기간</th>
	                	<td>
	                		계약일로부터&nbsp;&nbsp;&nbsp;${bfStndOpenPrcnDetail.CONT_TE }&nbsp;일
	                	</td>
	                	<th>추정가격</th>
	                	<td>
	                		${comFn:formatMoney(bfStndOpenPrcnDetail.ESTT_PRCE) }
	                	</td>
	                </tr>
	                <tr>
	                	<th>계약방법</th>
	                	<td>${bfStndOpenPrcnDetail.CONT_MTCD_NM }</td>
	                	<th>낙찰방법</th>
	                	<td>${bfStndOpenPrcnDetail.SBID_MTCD_NM }</td>
	                </tr>
	                <tr>
	                	<th>계약담당구분</th>
	                	<td>
	                		<c:if test="${bfStndOpenPrcnDetail.PRCH_CHRG_SECD eq 'A'}">부서계약</c:if><c:if test="${bfStndOpenPrcnDetail.PRCH_CHRG_SECD eq 'B'}">의뢰계약</c:if>
	                	</td>
	                	<th>계약부서</th>
	                	<td>${bfStndOpenPrcnDetail.PRCH_DEPT_NM }</td>
	                </tr>
	                <tr>
	                	<th id="dlgdPlcNm">공사현장</th>
	                	<td colspan="3">${bfStndOpenPrcnDetail.DLGD_PLC_NM }</td>
	                </tr>
	                <tr>
	                	<th>공동계약여부</th>
	                	<td>
	                		${bfStndOpenPrcnDetail.ASSO_SPDM_CD_NM }
	                	</td>
	                	<th>요청일자</th>
	                	<td>
	                		${comFn:formatDate(bfStndOpenPrcnDetail.RQR_DE,'yyyyMMdd','yyyy-MM-dd')}
	                	</td>
	                </tr>
	                <tr id="contMtcdGbn">
	                	<th>긴급입찰</th>
	                	<td>
	                		<c:if test="${bfStndOpenPrcnDetail.EMRG_YN eq 'Y'}">해당</c:if><c:if test="${bfStndOpenPrcnDetail.EMRG_YN eq 'N'}">미해당</c:if>
	                	</td>
	                	<th>사전규격공개여부</th>
	                	<td>
	                		<c:if test="${bfStndOpenPrcnDetail.BF_STND_OPEN_YN eq 'Y'}">공개</c:if><c:if test="${bfStndOpenPrcnDetail.BF_STND_OPEN_YN eq 'N'}">비공개</c:if>
	                		&nbsp;&nbsp;&nbsp;${bfStndOpenPrcnDetail.BF_STND_OPEN_TE }&nbsp;일 이상
	                	</td>
	                </tr>
	                <tr>
	                	<th>SW사업대상</th>
	                	<td>
	                		<c:if test="${bfStndOpenPrcnDetail.SW_BSNS_OBJ_YN eq 'Y'}">예</c:if><c:if test="${bfStndOpenPrcnDetail.SW_BSNS_OBJ_YN eq 'N'}">아니오</c:if>
	                	</td>
	                	<th>하도급</th>
	                	<td>
	                		<c:if test="${bfStndOpenPrcnDetail.SBCT_YN eq 'Y'}">예</c:if><c:if test="${bfStndOpenPrcnDetail.SBCT_YN eq 'N'}">아니오</c:if>
	                	</td>
	                </tr>
	                <tr>
	                	<th>중소기업자간경쟁제품</th>
	                	<td>
	                		<c:if test="${bfStndOpenPrcnDetail.SMVE_CMPTI_YN eq 'Y'}">예</c:if><c:if test="${bfStndOpenPrcnDetail.SMVE_CMPTI_YN eq 'N'}">아니오</c:if>
	                	</td>
	                	<th>우선구매대상 제3자단가</th>
	                	<td>
	                		<c:if test="${bfStndOpenPrcnDetail.PRIO_PRCH_YN eq 'Y'}">예</c:if><c:if test="${bfStndOpenPrcnDetail.PRIO_PRCH_YN eq 'N'}">아니오</c:if>
	                	</td>
	                </tr>
	                <tr>
	                	<th>학술용역</th>
	                	<td>
	                		<c:if test="${bfStndOpenPrcnDetail.SCLSH_SVC_YN eq 'Y'}">예</c:if><c:if test="${bfStndOpenPrcnDetail.SCLSH_SVC_YN eq 'N'}">아니오</c:if>
	                	</td>
	                	<th>여성기업 또는 사회적기업</th>
	                	<td>
	                		<c:if test="${bfStndOpenPrcnDetail.WMAN_SCTY_CORP_YN eq 'Y'}">예</c:if><c:if test="${bfStndOpenPrcnDetail.WMAN_SCTY_CORP_YN eq 'N'}">아니오</c:if>
	                	</td>
	                </tr>
	                <tr>
	                	<th>실적제한</th>
	                	<td>
	                		<c:if test="${bfStndOpenPrcnDetail.ACPS_LMT_YN eq 'Y'}">예</c:if><c:if test="${bfStndOpenPrcnDetail.ACPS_LMT_YN eq 'N'}">아니오</c:if>
	                	</td>
	                	<th>정보화사업</th>
	                	<td>
	                		<c:if test="${bfStndOpenPrcnDetail.INF_BSNS_YN eq 'Y'}">예</c:if><c:if test="${bfStndOpenPrcnDetail.INF_BSNS_YN eq 'N'}">아니오</c:if>
	                	</td>
	                </tr>
	                <tr>
	                	<th>비고</th>
	                	<td colspan="3">
	                		${bfStndOpenPrcnDetail.RMK }
	                	</td>
	                </tr>
               </tbody>
            </table>
			<p class="spc_stit">사전규격공개정보</p>
			<table class="form_tb"  summary="사전규격공개정보 입니다.">
                <caption>사전규격공개정보</caption>
                <colgroup>
                    <col width="15%">
                    <col width="35%">
                    <col width="15%">
                    <col width="35%">
                </colgroup>
                <tbody>
                	<tr>
	                	<th>사업예산</th>
	                	<td>${comFn:formatMoney(bfStndOpenPrcnDetail.BSNS_BDG_AMT)}</td>
	                	<th>공고기간</th>
	                	<td>
	                		${comFn:formatDate(bfStndOpenPrcnDetail.BFAN_STDE,'yyyyMMdd','yyyy-MM-dd')}&nbsp;&nbsp;~&nbsp;&nbsp;${comFn:formatDate(bfStndOpenPrcnDetail.BFAN_ENDE,'yyyyMMdd','yyyy-MM-dd')}
						</td>
	                </tr>
                	<tr>
	                	<th>접수자</th>
	                	<td>${comFn:nvl(bfStndOpenPrcnDetail.CHRGR_NM, sessionScope.loginResult.USR_NM)}</td>
	                	<th>접수일자</th>
	                	<td>
	                		${comFn:formatDate(comFn:nvl(bfStndOpenPrcnDetail.ACPT_DE, currentDate),'yyyyMMdd','yyyy-MM-dd')}
						</td>
	                </tr>
                	<tr>
	                	<th>기타사항</th>
	                	<td colspan="3">
	                		${bfStndOpenPrcnDetail.ETC_ITEM }
	                	</td>
	                </tr>
                </tbody>
			</table>
			<p class="spc_stit">품목정보</p>
			<div style=" overflow-x: scroll; overflow-y:hidden" >
				<table class="tb">
					<colgroup>
						<col width="50px;">
						<col width="150px;">
						<col width="180px;">
						<col width="150px;">
						<col width="200px;">
						<col width="100px;">
						<col width="100px;">
						<col width="150px;">
						<col width="150px;">
						<col width="250px;">
						<col width="150px;">
						<col width="150px;">
						<col width="200px;">
						<col width="150px;">
					</colgroup>
					<thead>
						<tr class="line">
							<th scope="col"  class="noBg" >번호</th>
							<th scope="col"  class="txtc" >재산유형</th>
							<th scope="col"  class="txtc" >품목코드</th>
							<th scope="col"  class="txtc" >품목(재산)명</th>
							<th scope="col" class="txtc" >건명(용도)</th>
							<th scope="col" class="txtc" >단위</th>
							<th scope="col" class="txtc">수량</th>
							<th scope="col" class="txtc">추정단가</th>
							<th scope="col" class="txtc">추정가격</th>
							<th scope="col" class="txtc">부가세구분</th>
							<th scope="col" class="txtc">부가세</th>
							<th scope="col" class="txtc">추정금액</th>
							<th scope="col" class="txtc">예산명(프로젝트명)</th>
							<th scope="col" class="txtc">예산년월</th>
						</tr>
					</thead>
					<tbody >
						<c:if test="${empty bfStndOpenitemList}">
							<tr class="row">
								<td colspan="14" align="center">검색 조건에 해당하는 데이터가 존재하지 않습니다.</td>
							</tr>
						</c:if>
						<c:if test="${not empty bfStndOpenitemList}">
							<c:forEach var="data" items="${bfStndOpenitemList}" varStatus="status" >
				            	<tr>
									<td class="txtc">${data.RNUM }</td>
									<td class="txtc">${data.ASTS_TYCD_NM }</td>
									<td class="txtc">${data.ITEM_NO }</td>
									<td class="txtl pl5">${data.ITEM_NM }</td>
									<td class="txtl pl5">${data.ITEM_DTL }</td>
									<td class="txtc">${data.ITEM_UNCD_NM }</td>
									<td class="txtr pr5">${comFn:formatMoney(data.ITEM_QTY) }</td>
									<td class="txtr pr5">${comFn:formatMoney(data.ESTT_UPRC) }</td>
									<td class="txtr pr5">${comFn:formatMoney(data.ESTT_PRCE) }</td>
									<td class="txtc">${data.STAX_SECD_NM }</td>
									<td class="txtr pr5">${comFn:formatMoney(data.ITEM_STAX) }</td>
									<td class="txtr pr5">${comFn:formatMoney(data.ESTT_AMT) }</td>
									<td class="txtl pl5">${data.ACNT_NM }</td>
									<td class="txtc">${data.BDG_YR } / ${data.BDG_MM}</td>
				            	</tr>
				            </c:forEach>
				    	</c:if>
		            </tbody>
				</table>
			</div>
			<!-- 품목정보 END -->
			<c:if test="${bfStndRqstPrcnDetail.CONT_MTCD ne '10004' }">
				<div class="tit_area" style="margin-top: 10px;">
					<p class="spc_stit">필수첨부파일&nbsp;&nbsp;</p>
					<table class="tb">
						<colgroup>
							<col width="20%">
							<col width="*">
						</colgroup>
						<thead>
							<tr>
								<th class="txtc">파일명</th> 
								<th class="txtc">파일첨부</th>
							</tr>
						</thead> 
						<tbody>
							<c:forEach var="data" items="${bfanFile}" varStatus="idx">
								<tr>
									<td>${data.BFAN_FSCD_NM}</td>
									<td class="txtl"> 
										<a href="javascript:download('${data.FILE_GRP_NO}', '${data.FILE_SN}');">${data.SYS_FILE_NM}&nbsp;</a>
				                    </td>	
								</tr>
							</c:forEach> 
						</tbody>
					</table>
				</div>
			</c:if>
       		 <p class="spc_stit">기타첨부파일</p>
			<div class="fileViewer">
				<!-- 업로드 삽입. -->
				<script type="text/javascript">
					var raonkParam = {
				            Id: "uploadView1",
				            Mode: "view",
				            Width: "100%",
				            Height: "200px",
				            ButtonBarView: "open,download",
				            BorderStyle: "solid"
				        };
						var raonkUpload = new RAONKUpload(raonkParam);
				</script>
			</div>	
			<div id="upload_fileInfo"></div>
	        <div class="btm_btns">
	        	<!-- 계약담당자, 계약담당자(지사) , 관리자만 -->
	        	<c:if test="${ loginResult.AUTH_ID eq '1' || loginResult.AUTH_ID eq '4' || loginResult.AUTH_ID eq '5'}">
		        	<c:if test="${bfStndOpenPrcnDetail.BFAN_PSCD  eq 'B003' || bfStndOpenPrcnDetail.BFAN_PSCD  eq 'B007'}">
					</c:if>
					<%-- <c:if test="${bfStndOpenPrcnDetail.BFAN_PSCD  eq 'B008'}">
						<button type="button" class="btn ty02" id="annoComplBtn">공고완료</button>
					</c:if> --%>
					<c:if test="${bfStndOpenPrcnDetail.BFAN_PSCD  eq 'B006'}">
						<button type="button" class="btn ty02" id="annoBtn">공고</button>
					</c:if>
					<c:if test="${bfStndOpenPrcnDetail.BFAN_PSCD  eq 'B003' || bfStndOpenPrcnDetail.BFAN_PSCD eq 'B007'}">
						<button type="button" class="btn ty02" id="updtFormBtn">수정</button>
						<button type="button" class="btn ty02" id="rqstRtnBtn">요청반려</button>
					</c:if>
					<c:if test="${bfStndOpenPrcnDetail.BFAN_PSCD  eq 'B003'}">
						<button type="button" class="btn ty02" id="rqstRecBtn">요청접수</button>
						<button type="button" class="btn ty02" id="rtnBtn">단순반려</button>
					</c:if>
					<!-- 팀장만 가능 -->
					<c:if test="${bfStndOpenPrcnDetail.BFAN_PSCD  eq 'B004' && loginResult.APPR_AUTH_YN eq 'Y'}" >
						<button type="button" class="btn ty02" id="recApplyBtn">접수승인</button>
						<button type="button" class="btn ty02" id="recRtnBtn">접수반려</button>
						<button type="button" class="btn ty02" id="rtnBtn">단순반려</button>
					</c:if>
				</c:if>
				<button type="button" class="btn ty02" id="progHistBtn">진행이력</button>
				<button type="button" class="btn ty04" id="listBtn">목록</button>
			</div>
   		</div>
	</form>
</div>

<!-- LIST FORM -->
<form id="listFrm" method="POST">
	<input type="hidden" id="resourceName" name="resourceName" value="${comFn:nvl(param.resourceName, resourceName)}">
</form>

<!-- DETAIL FORM -->
<form id="updtFrm" method="POST" > 
	<input type="hidden" name="resourceName" value="${comFn:nvl(param.resourceName, resourceName)}" >
	<input type="hidden" name="P_BFAN_NO" value="${bfStndOpenPrcnDetail.BFAN_NO }">
	<input type="hidden" name="P_BFAN_PSCD" value="">
</form>
<!-- POPUP FORM -->
<form id="popupFrm" method="POST">
	<input type="hidden" name="resourceName" value="${comFn:nvl(param.resourceName, resourceName)}">
	<input type="hidden" name="resourceDesc" value="${param.resourceDesc}">
	<input type="hidden" name="P_BFAN_NO"  value="${bfStndOpenPrcnDetail.BFAN_NO }">
	<input type="hidden" name="P_BFAN_PSCD" value="">
</form>
<form id="downloadFrm" method="POST">
	<input type="hidden" name="P_FILE_GRP_NO"  id="P_FILE_GRP_NO">
	<input type="hidden" name="P_FILE_SN"  id="P_FILE_SN">
</form>
