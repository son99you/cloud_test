<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 계약설계 > 입찰공고요청 등록
 *
 * <pre>
 * prpo 
 *    |_ bidReqDetail.jsp 
 * 
 * </pre>
 * @date : 2020. 08. 25
 * @version : 1.0
 * @author : 은우소프트 joo
--%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %> 
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %> 

<script type="text/javascript" src="/raonkupload/js/raonkupload.js"></script>
<script type="text/javascript" src="${jsPath}/ipro/views/ebid/bidPblancPrcnUpdtForm.js"></script>

<div class="sp_sec">
	<div class="nav_sec">
		<ul id="breadcrumbs"> 
			<li class="home">홈</li>
			<li>${myMenuList.bigMenuNm}</li>
			<li>${myMenuSubList.smallMenuNm}</li> 
		</ul>
	</div><!--// nav_sec --> 
	
	<h3 class="sp_tit">입찰공고진행 수정</h3>
	<form id="updtFrm" method="POST" enctype="multipart/form-data">
		<input type="hidden" name="resourceName" value="${comFn:nvl(param.resourceName, resourceName)}">
		<!-- 라온케이첨부파일 그룹번호 -->
		<input type="hidden" id="P_FILE_GRP_NO_ETC" name="P_FILE_GRP_NO_ETC"  value="${bidReqDetail.FILE_GRP_NO_ETC}"> 
		<input type="hidden" id="P_FILE_GRP_NO_NEW" name="P_FILE_GRP_NO_NEW">

		<!-- 필수 첨부파일 그룹번호-->
		<input type="hidden" id="P_FILE_GRP_NO" name="P_FILE_GRP_NO"  value="${bidReqDetail.FILE_GRP_NO}">  
		
		<input type="hidden" id="itemCount" name="itemCount"  value="${fn:length(bidReqItemList)}" />
		<input type="hidden" id="itemSn" name="itemSn"  value="" />
		<input type="hidden" id="contSecd" name="contSecd"  value="${bidReqDetail.CONT_SECD}" />
		<input type="hidden" id="contMtcd" name="contMtcd"  value="${bidReqDetail.CONT_MTCD}" />
		<input type="hidden" id="esttPrceM" name="esttPrceM"  value="${comFn:formatMoney(bidReqDetail.ESTT_PRCE)}" money/>
		<input type="hidden" id="sbidMtcd" name="sbidMtcd" value="${bidReqDetail.SBID_MTCD}" />
		<input type="hidden" id="bfStndOpenYn" name="bfStndOpenYn" value="${bidReqDetail.BF_STND_OPEN_YN}" />
		<input type="hidden" name="P_ANNC_NO" value="${bidReqDetail.ANNC_NO}">
		<input type="hidden" name="P_ANNC_NGR" value="${bidReqDetail.ANNC_NGR}">
		<input type="hidden" name="P_ROUND_NO" value="${bidReqDetail.ROUND_NO}">
		<input type="hidden" name="P_BID_PSCD" value="${bidReqDetail.BID_PSCD}"> 
		
		<input type="hidden" name="P_PAGE_GBN" value="EBID"> 
		
		<!-- 필수첨부파일 떄문 -->
		<input type="hidden" id="P_CONT_SECD" name="P_CONT_SECD"  value="${bidReqDetail.CONT_SECD}" />
		<input type="hidden"  id="P_ESTT_PRCE_M"  name="P_ESTT_PRCE_M"  class="input rt w50p" placeholder="추정가격" value="${comFn:formatMoney(bidReqDetail.ESTT_PRCE)}" money>
		<input type="hidden" id="P_EMRG_YN" name="P_EMRG_YN"  value="${bidReqDetail.EMRG_YN}" />
		<input type="hidden" id="emrgYn" name="emrgYn"  value="${bidReqDetail.EMRG_YN}" />
		<input type="hidden" id="infBsnsYn" name="infBsnsYn"  value="${bidReqDetail.INF_BSNS_YN}" />
		
		<div class="sp_cont">
			<c:if test="${not empty bidReqPrcnRtn}">
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
               				<td>[${comFn:formatDate(bidReqPrcnRtn.PROC_DT,'yyyyMMddHHmmss','yyyy-MM-dd HH:mm:ss')}]&nbsp;${bidReqPrcnRtn.RMK}</td>
	               		</tr>
	               	</tbody>
				</table>
			</c:if>	
			<%-- <p class="spc_stit">기본정보</p>
			<table class="form_tb">
				<caption>기본정보</caption>
				<colgroup>
					 <col width="15%">
			        <col width="35%">
			        <col width="15%">
			        <col width="35%">
				</colgroup>
				<tbody>
		               <tr>
							<th scope="row">요구번호</th>
							<td>${bidReqDetail.PRCH_RQR_NO}</td>
							<th scope="row">입찰요청번호</th>
							<td>${bidReqDetail.ANNC_NO}</td>
						</tr>
		               <tr>
		                    <th>진행상태</th>
		                    <td>${bidReqDetail.BID_PSCD_NM}</td>
		                    <th>결재상태</th>
		                    <td><c:if test="${bidReqDetail.APPR_STCD eq 'N'}" >미결재</c:if><c:if test="${bidReqDetail.APPR_STCD eq 'Y'}" >결재</c:if></td>
		                </tr>
		                <tr>
		                    <th>본지사</th>
		                    <td>
		                    	<comTag:comCmcdCdValueComboBox name="P_ARA_DEPT_CD" cdId="ARA_DEPT_CD" selectKey="${bidReqDetail.ARA_DEPT_CD}" cond2="" headerValue="전체" className="select w50p"/>
		                    </td>
		                    <th>작성부서</th>
		                    <td>${bidReqDetail.RQR_DEPT_NM }
		                    	<input type="hidden" name="P_RQR_DEPT_NM" value="${bidReqDetail.RQR_DEPT_NM }">
		                    	<input type="hidden" name="P_RQR_DEPT_NO" value="${bidReqDetail.RQR_DEPT_NO }">
		                    </td>
		                </tr>
		                <tr>
		                    <th>계약구분</th>
		                    <td>
		                    	<comTag:comCmcdCdValueComboBox id="P_CONT_SECD" name="P_CONT_SECD" cdId="CONT_SECD"  selectKey="${bidReqDetail.CONT_SECD}" cond2="" headerValue="전체"  className="select w50p"/>
		                    </td>
		                    <th>작성자</th>
		                    <td>${bidReqDetail.RQR_CHRGR_NM }
		                    	<input type="hidden" name="P_RQR_CHRGR_NM" value="${bidReqDetail.RQR_CHRGR_NM }">
		                    	<input type="hidden" name="P_RQR_CHRGR_ID" value="${bidReqDetail.RQR_CHRGR_ID }">
		                    </td>
		                </tr>
		                <tr>
		                	<th>감독원</th>
		                	<td>
		                		<input type="text"  id="P_MNGR_CHRGR_NM"  name="P_MNGR_CHRGR_NM"  value="${bidReqDetail.MNGR_CHRGR_NM }" class="input w50p" placeholder="감독원" readonly="readonly" style="background:#e0e0e0;"/>
		                		<input type="hidden"  id="P_MNGR_CHRGR_ID" name="P_MNGR_CHRGR_ID"  value="${bidReqDetail.MNGR_CHRGR_ID }" >
		                		<button type="button" class="btn ty03" id="mngrChrgrSrchBtn">검색</button>
		                    	<button type="button" class="btn ty04" id="mngrChrgrDelBtn">삭제</button>
		                	</td>
		                	<th>검사원</th>
		                	<td>
			                	<input type="text"  id="P_CHCK_CHRGR_NM" name="P_CHCK_CHRGR_NM"  value="${bidReqDetail.CHCK_CHRGR_NM }" class="input w50p" placeholder="검사원" readonly="readonly" style="background:#e0e0e0;"/>
			                	<input type="hidden"  id="P_CHCK_CHRGR_ID" name="P_CHCK_CHRGR_ID"  value="${bidReqDetail.CHCK_CHRGR_ID }">
			                	<button type="button" class="btn ty03" id="chkChrgrSrchBtn">검색</button>
		                    	<button type="button" class="btn ty04" id="chkChrgrDelBtn">삭제</button>
		                	</td>
		                </tr>
		                <tr>
		                	<th>계약명(사업명)</th>
		                	<td colspan="3">
		                		 <input type="text"  id="P_BID_NM" name="P_BID_NM"  value="${bidReqDetail.BID_NM }"  class="input w100p" placeholder="계약명(사업명)" />
		                	</td>
		                </tr>
		                <tr>
		                	<th>계약기간</th>
		                	<td>
		                		계약일로부터&nbsp;&nbsp;&nbsp;<input type="text"  id="P_CONT_TE" name="P_CONT_TE"  value="${bidReqDetail.CONT_TE}"  class="input w50p" placeholder="계약기간" />&nbsp;일
		                	</td>
		                	<th>추정가격</th>
		                	<td>
		                		<input type="text"  id="P_ESTT_PRCE_M"  name="P_ESTT_PRCE_M"  class="input rt w50p" placeholder="추정가격" value="${comFn:formatMoney(bidReqDetail.ESTT_PRCE)}" money>
		                	</td>
		                </tr>
		                <tr>
		                	<th>계약담당구분</th>
		                	<td>
		                		<div class="rad_g">
		                			<comTag:cmmnCdValueRadio  name="P_PRCH_CHRG_SECD" selectKey="${bidReqDetail.PRCH_CHRG_SECD }" list="{'A':'부서계약', 'B':'의뢰계약'}"/>
	                			</div>
		                	</td>
		                	<th>계약부서</th>
		                	<td>
			                	<input type="text" name="P_PRCH_DEPT_NM" id="P_PRCH_DEPT_NM"  value="${bidReqDetail.PRCH_DEPT_NM }"   class="input w50p" placeholder="계약부서" readonly="readonly" style="background:#e0e0e0;"/>
			                	<input type="hidden" name="P_PRCH_DEPT_NO" id="P_PRCH_DEPT_NO"  value="${bidReqDetail.PRCH_DEPT_NO }" class="w180">
			                	<button type="button" class="btn ty03" id="searchDeptBtn">검색</button>
		                    	<button type="button" class="btn ty04" id="deptDelBtn">삭제</button>
		                	</td>
		                </tr>
		                <tr>
		                	<th id="dlgdPlcNm">공사현장</th>
		                	<td colspan="3"><input type="text" name="P_DLGD_PLC_NM" id="P_DLGD_PLC_NM"  value="${bidReqDetail.DLGD_PLC_NM }"  class="input w100p" placeholder="공사현장" />
		                </tr>
		                <tr>
		                	<th>공동계약여부</th>
		                	<td>
		                		<comTag:comCmcdCdValueComboBox name="P_ASSO_SPDM_CD" cdId="ASSO_SPDM_CD" selectKey="${bidReqDetail.ASSO_SPDM_CD }"  cond2="" headerValue="전체"  className="select w50p"/>
		                	</td>
		                	<th>요청일자</th>
		                	<td>
		                		<span class="date_box">
		                			<input type="text" id="P_RQR_DE" name="P_RQR_DE" class="input datepicker start-date " placeholder="요청일자" date value="${comFn:formatDate(bidReqDetail.RQR_DE,'yyyyMMdd','yyyy-MM-dd') }">
		                		</span>
		                	</td>
		                </tr>
		                <tr id="contMtcdGbn">
		                	<th>긴급입찰</th>
		                	<td>
		                		<div class="rad_g">
		                			<comTag:cmmnCdValueRadio  name="P_EMRG_YN" selectKey="${comFn:nvl(bidReqDetail.EMRG_YN,'N')}" list="{'N':'미해당', 'Y':'해당'}"/>
		                		</div>
		                	</td>
		                	<th>사전규격공개여부</th>
		                	<td>
		                		<div class="rad_g">
		                			<comTag:cmmnCdValueRadio  name="P_BF_STND_OPEN_YN" selectKey="${comFn:nvl(bidReqDetail.BF_STND_OPEN_YN,'N')}" list="{'N':'미공개', 'Y':'공개'}"/>
		                		</div>
		                		<span class="bfStndOpenTeSpan" style="display: none;">&nbsp;<input type="text" id="P_BF_STND_OPEN_TE" name="P_BF_STND_OPEN_TE"  value="${bidReqDetail.BF_STND_OPEN_TE}"  class="input w40p" placeholder="사전규격공개일" />&nbsp;일 이상</span>
		                	</td>
		                </tr>
		                <tr>
		                	<th>비고</th>
		                	<td colspan="3">
		                		<input type="text" id="P_RMK" name="P_RMK" value="${bidReqDetail.RMK}" class="input"  placeholder="비고">
		                	</td>
		                </tr>
	               </tbody>
			</table><!--// form_tb --> --%>
			
			<p class="spc_stit">기본정보</p>
			<table class="form_tb">
				<caption>기본정보</caption>
				<colgroup>
					 <col width="15%">
			        <col width="35%">
			        <col width="15%">
			        <col width="35%">
				</colgroup>
				<tbody>
		               <tr>
							<th scope="row">요구번호</th>
							<td>${bidReqDetail.PRCH_RQR_NO}</td>
							<th scope="row">입찰요청번호</th>
							<td>${bidReqDetail.ANNC_NO}</td>
						</tr>
		               <tr>
		                    <th>진행상태</th>
		                    <td>${bidReqDetail.BID_PSCD_NM}</td>
		                    <th>결재상태</th>
		                    <td><c:if test="${bidReqDetail.APPR_STCD eq 'N'}" >미결재</c:if><c:if test="${bidReqDetail.APPR_STCD eq 'Y'}" >결재</c:if></td>
		                </tr>
		                <tr>
						<th scope="row">본지사</th>
						<td>${bidReqDetail.ARA_DEPT_CD_NM}</td>
						<!-- 요구부서 -->
						<th scope="row">작성부서</th>
						<td>${bidReqDetail.RQR_DEPT_NM}</td>
					</tr>
					<tr>
						<th scope="row">계약구분</th>
						<td>${bidReqDetail.CONT_SECD_NM}</td>
						<!-- 요구자 -->
						<th scope="row">작성자</th>
						<td>${bidReqDetail.RQR_CHRGR_NM}</td>
					</tr> 
					<tr>
						<th scope="row">감독원(담당자)</th>
	        			<td>${bidReqDetail.MNGR_CHRGR_NM}</td>
	        			<th scope="row">검사원(부서장)</th>
	        			<td>${bidReqDetail.CHCK_CHRGR_NM}</td>
		        	</tr>
	        		<tr>
	        			<!-- 입찰명 -->
			          	<th scope="row">계약명(사업명)</th>
			          	<td colspan="3">
			          		${bidReqDetail.BID_NM}
			          	</td>
			        </tr>
	          		<tr>
			          	<th scope="row">계약기간</th>
			          	<td>
			          		계약일로부터&nbsp;&nbsp;&nbsp;${bidReqDetail.CONT_TE}&nbsp;일
			          	</td>
			          	<th scope="row">추정가격</th>
					  	<td class="sort01">${comFn:formatMoney(bidReqDetail.ESTT_PRCE)}원</td>
			          </tr>
		                <%-- <tr>
		                	<th>계약방법</th>
		                	<td>
		                		<comTag:comCmcdCdValueComboBox name="P_CONT_MTCD" cdId="CONT_MTCD"  selectKey="${bidReqDetail.CONT_MTCD}" cond2="" headerValue="전체"  className="select w50p"/>
		                	</td>
		                	<th>낙찰방법</th>
		                	<td>
		                		<comTag:comCmcdCdValueComboBox name="P_SBID_MTCD" cdId="SBID_MTCD"  selectKey="${bidReqDetail.SBID_MTCD}" cond2="" headerValue="전체"  className="select w50p"/>
		                	</td>
		                </tr> --%>
		                <tr>
							<th scope="row">계약담당구분</th>
							<td> 
								<c:if test="${bidReqDetail.PRCH_CHRG_SECD eq 'A'}">부서계약</c:if><c:if test="${bidReqDetail.PRCH_CHRG_SECD eq 'B'}">의뢰계약</c:if>
							</td> 
							<!-- 구매부서명, 구매부서번호 -->
							<th scope="row">계약부서</th>
							<td>${bidReqDetail.PRCH_DEPT_NM }</td>
						</tr> 
						<tr>
				          	<th id="dlgdPlcNm" scope="row">공사현장</th>
				          	<td colspan="3">${bidReqDetail.DLGD_PLC_NM }</td>
				          </tr>
	          			<tr>
							<th scope="row">공동계약여부</th>
							<td>
								<input type="hidden" id="P_ASSO_SPDM_CD" name="P_ASSO_SPDM_CD"   value="${bidReqDetail.ASSO_SPDM_CD }"/>
								${bidReqDetail.ASSO_SPDM_CD_NM }
							</td>
							<!-- 요청일자 -->
							<th scope="row">요청일자</th>
							<td>
		                		${comFn:formatDate(bidReqDetail.RQR_DE,'yyyyMMdd','yyyy-MM-dd')}
		                	</td>
						</tr>
						<tr id="contMtcdGbn" style="display: none;"> 
							<th scope="row">긴급입찰</th>
							<td> 
								<c:if test="${bidReqDetail.EMRG_YN eq 'Y'}">해당</c:if><c:if test="${bidReqDetail.EMRG_YN eq 'N'}">미해당</c:if>
							</td>
							<th scope="row">사전규격공개여부</th>
							<td>
								<c:if test="${bidReqDetail.BF_STND_OPEN_YN eq 'Y'}">공개&nbsp;/&nbsp;${bidReqDetail.BF_STND_OPEN_TE }&nbsp;일 이상</c:if><c:if test="${bidReqDetail.BF_STND_OPEN_YN eq 'N'}">비공개</c:if>
							</td>
						</tr>
						<tr>
		                	<th>SW사업대상</th>
		                	<td>
		                		<input type="hidden" value= "${bidReqDetail.SW_BSNS_OBJ_YN}" id="P_SW_BSNS_OBJ_YN" name="P_SW_BSNS_OBJ_YN">
		                		<c:if test="${bidReqDetail.SW_BSNS_OBJ_YN eq 'Y'}">예</c:if><c:if test="${bidReqDetail.SW_BSNS_OBJ_YN eq 'N'}">아니요</c:if>
		                	</td>
		                	<th>하도급</th>
		                	<td>
		                		<c:if test="${bidReqDetail.SBCT_YN eq 'Y'}">예</c:if><c:if test="${bidReqDetail.SBCT_YN eq 'N'}">아니요</c:if> 
		                	</td>
		                </tr>
		                <tr>
		                	<th>중소기업자간경쟁제품</th>
		                	<td>
		                		<c:if test="${bidReqDetail.SMVE_CMPTI_YN eq 'Y'}">예</c:if><c:if test="${bidReqDetail.SMVE_CMPTI_YN eq 'N'}">아니요</c:if>
		                	</td>
		                	<th>우선구매대상 제3자단가</th>
		                	<td>
		                		<c:if test="${bidReqDetail.PRIO_PRCH_YN eq 'Y'}">예</c:if><c:if test="${bidReqDetail.PRIO_PRCH_YN eq 'N'}">아니요</c:if> 
		                	</td>
		                </tr>
		                <tr>
		                	<th>학술용역</th>
		                	<td>
		                		<input type="hidden" value= "${bidReqDetail.SCLSH_SVC_YN}" id="P_SCLSH_SVC_YN" name="P_SCLSH_SVC_YN">
		                		<c:if test="${bidReqDetail.SCLSH_SVC_YN eq 'Y'}">예</c:if><c:if test="${bidReqDetail.SCLSH_SVC_YN eq 'N'}">아니요</c:if>
		                	</td>
		                	<th>여성기업 또는 사회적기업</th>
		                	<td>
		                		<input type="hidden" id="P_WMAN_SCTY_CORP_YN" name="P_WMAN_SCTY_CORP_YN"  value=${bidReqDetail.WMAN_SCTY_CORP_YN } >
		                		<c:if test="${bidReqDetail.WMAN_SCTY_CORP_YN eq 'Y'}">예</c:if><c:if test="${bidReqDetail.WMAN_SCTY_CORP_YN eq 'N'}">아니요</c:if> 
		                	</td>
		                </tr>
		                <tr>
		                	<th>실적제한</th>
		                	<td>
		                		<input type="hidden" value= "${bidReqDetail.ACPS_LMT_YN}" id="P_ACPS_LMT_YN" name="P_ACPS_LMT_YN">
		                		<c:if test="${bidReqDetail.ACPS_LMT_YN eq 'Y'}">예</c:if><c:if test="${bidReqDetail.ACPS_LMT_YN eq 'N'}">아니요</c:if>
		                	</td>
		                	<th>정보화사업</th>
		                	<td>
		                		<input type="hidden" id="P_INF_BSNS_YN" name="P_INF_BSNS_YN" value=${bidReqDetail.INF_BSNS_YN } >
		                		<c:if test="${bidReqDetail.INF_BSNS_YN eq 'Y'}">예</c:if><c:if test="${bidReqDetail.INF_BSNS_YN eq 'N'}">아니요</c:if> 
		                	</td>
		                </tr>
						<tr>
				          	<th scope="row">비고</th>
				          	<td colspan="3">
								${bidReqDetail.RMK}
				          	</td>
			            </tr>
	               </tbody>
			</table><!--// form_tb -->
			
				<!-- 입찰정보 start-->
		 		<!-- <div class="det_tabs" style="margin-top: 30px;">
					<a class="on" id="1" name="tab" href='javascript:tabEvent(1);' >공고상세</a>
					<a id="2" name="tab" href='javascript:tabEvent(2);'>기술평가</a>
					<a id="3" name="tab" href='javascript:tabEvent(3);'>개찰</a>
					<a id="4" name="tab" href='javascript:tabEvent(4);'>기술협상</a>
					<a id="5" name="tab" href='javascript:tabEvent(5);'>낙찰</a>
				</div> -->
			
				<p class="spc_stit">입찰정보</p>
				<table class="form_tb">
				<caption>입찰정보</caption>
				<colgroup>
					<col width="15%">
			        <col width="35%">
			        <col width="15%">
			        <col width="35%">
				</colgroup>
				<tbody>
					<tr>
						<!-- 추정금액 -->
						<th scope="row">사업예산</th>
						<td class="sort01"><input type="text" id="P_ESTT_AMT_M" name="P_ESTT_AMT_M" class="input rt w50p" placeholder="추정금액" money value="${comFn:formatMoney(bidReqDetail.ESTT_AMT)}"/>원</td>
						<th scope="row"></th>
						<td></td>
					</tr>
					<!--입찰방법에 표현 --> 
	         		<tr>
						<th scope="row">계약방법</th>
						<td>${bidReqDetail.CONT_MTCD_NM}
							<input type="hidden" name="P_CONT_MTCD" id="P_CONT_MTCD" value="${bidReqDetail.CONT_MTCD}" />
							<%-- <comTag:comCmcdCdValueComboBox id="P_CONT_MTCD" name="P_CONT_MTCD" cdId="CONT_MTCD" selectKey="${bidReqDetail.CONT_MTCD}" headerValue="전체" className="select w50p"/> --%>
						</td>  
						<th scope="row">예가방식</th>
						<td>
							<comTag:comCmcdCdValueComboBox name="P_ESTPC_SECD" cdId="ESTPC_SECD" selectKey="${bidReqDetail.ESTPC_SECD}" headerValue="전체" className="select w50p"/>
						</td>
					</tr>
					<tr>
			        	<th scope="row">낙찰방법</th>
			             <td>${bidReqDetail.SBID_MTCD_NM}
			            	<input type="hidden" name="P_SBID_MTCD" id="P_SBID_MTCD" value="${bidReqDetail.SBID_MTCD}" />
							<%-- <comTag:comCmcdCdValueComboBox name="P_SBID_MTCD" cdId="SBID_MTCD" selectKey="${bidReqDetail.SBID_MTCD}" headerValue="전체" className="select w50p"/> --%>
						</td> 
						<th scope="row">
							<%-- 낙찰자선정방식이 [적격심사, 제한적최저가]일 경우 활성화 --%>
							<label for="P_SBID_LWST_RT" class="sbidlwst" <c:if test="${bidReqDetail.SBID_MTCD ne '31' and bidReqDetail.SBID_MTCD ne '20'}">style="display: none;"</c:if>>낙찰하한율 (%)</label>
							<%-- 낙찰자선정방식이 [적격심사, 제한적최저가]일 활성화 End--%>
							
							<%-- 낙찰자선정방식이 [협상에의한계약]일 경우 활성화 --%>
							<label for="P_TCHN_SCR_RT" class="tchnScrRt"<c:if test="${bidReqDetail.SBID_MTCD ne '40'}">style="display: none;"</c:if>>합산비율</label>
							<%-- 낙찰자선정방식이 [협상에의한계약]일 활성화 End--%> 
						</th>
						<td>
		                    <div class="sbidlwst" <c:if test="${bidReqDetail.SBID_MTCD ne '31' and bidReqDetail.SBID_MTCD ne '20'}"> style="display:none;"</c:if>>
			                    <%-- 낙찰자선정방식이 [적격심사, 제한적최저가]일 경우 활성화 낙찰하한율 --%>
		                        <input type="text" class="sbidlwstInput w20p rt" id="P_SBID_LWST_RT" name="P_SBID_LWST_RT" value="${bidReqDetail.SBID_LWST_RT}" /> %
		                    	<%-- 낙찰자선정방식이 [적격심사, 제한적최저가]일 경우 활성화 End--%> 
		                    </div>
		                      
	                    	<%-- 낙찰자선정방식이 [협상에의한계약]일 경우 활성화 --%>
		                    <div class="tchnScrRt" <c:if test="${bidReqDetail.SBID_MTCD ne '40'}"> style="display:none;"</c:if>>
								기술점수비율&nbsp;<input type="text" class="tchnScrRtInput w10p" id="P_TCHN_SCR_RT" name="P_TCHN_SCR_RT" value="${bidReqDetail.TCHN_SCR_RT}" numeric />
								&nbsp;:&nbsp;
								가격점수비율&nbsp;<input type="text" class="tchnScrRtInput w10p" id="P_PRCE_SCR_RT" name="P_PRCE_SCR_RT" value="${bidReqDetail.PRCE_SCR_RT}" numeric />
								<br>
								적격제한점수&nbsp;:&nbsp;<input type="text" class="tchnScrRtInput w20p" id="P_ELGB_LMT_SCR" name="P_ELGB_LMT_SCR" value="${bidReqDetail.ELGB_LMT_SCR}" numeric />
		                    </div>
							<%-- 낙찰자선정방식이 [협상에의한계약]일  활성화 End--%>
	                    </td> 
			        </tr>
					<tr>
						<th scope="row">입찰설명회여부</th>
						<td>
							<div class="rad_g">
								<comTag:cmmnCdValueRadio name="P_BID_BRFS_YN"  list="{'Y':'예', 'N':'아니요'}" selectKey="${bidReqDetail.BID_BRFS_YN}"/>
							</div><!--// rad_g -->
						</td>
						<th><label class="brfsSpan" style="display: none;">입찰설명회장소</label></th>
						<td><span class="brfsSpan" style="display: none;"><input type="text" class="brfsInput" id="P_BRFS_PLC_NM" name="P_BRFS_PLC_NM"   class="input w100p" value="${bidReqDetail.BRFS_PLC_NM}" placeholder="입찰설명회장소" /></span></td>
					</tr>
				<%-- 	<tr>
						<th>입찰참가자격</th>
						<td colspan="3">
							<textarea class="tarea" id="P_PRTC_QLF_CNTN" name="P_PRTC_QLF_CNTN" placeholder="입찰참가자격을 입력해주세요."><c:out value="${bidReqDetail.PRTC_QLF_CNTN}"></c:out></textarea>
						</td>
					</tr> --%>
					<tr>
						<th>기타</th>
						<td colspan="3">
							 <input type="text" id="P_ETC_ITEM" name="P_ETC_ITEM" class="input w100p" value="${bidReqDetail.ETC_ITEM}" placeholder="기타"  />
						</td>
					</tr>
				</tbody> 
			</table><!--// form_tb -->
		<!-- 입찰정보 end-->
			
		<!-- 입찰진행순서 start-->
		<p class="spc_stit">입찰진행순서</p>
			<table class="form_tb">
				<caption>입찰진행순서</caption>
				<colgroup>
					<col width="15%">
			        <col width="85%">
				</colgroup>
				<tbody>
					<tr>
						<th scope="row">입찰공고일시</th>
						<td colspan="3">
							<input type="text"  id="P_ANNC_DT" name="P_ANNC_DT" class="w180" date placeholder="입찰공고일시" value="${comFn:formatDate(bidReqDetail.ANNC_DT,'yyyyMMdd','yyyy-MM-dd')}"/>
	                        <span class="wave">&nbsp;</span>
	                        <%-- <input type="text" class="float-left" id="P_ANNC_DT_HH" name="P_ANNC_DT_HH" style="width: 50px" maxlength="2" numeric  value="${comFn:formatDate(bidReqDetail.ANNC_DT,'yyyyMMddHHmmss','HH')}" placeholder="시"><span class="wave">:</span>
	                        <input type="text" class="float-left mr5" id="P_ANNC_DT_MM" name="P_ANNC_DT_MM" style="width: 50px" maxlength="2"  numeric value="${comFn:formatDate(bidReqDetail.ANNC_DT,'yyyyMMddHHmmss','mm')}" placeholder="분"> --%>
						</td>
					</tr>
					<%-- 입찰설명회 여부가 [예] 일 경우에 활성화 --%>
					<tr class="brfsSpan" style="display: none;">
						<th scope="row" id="brfsTh">입찰설명회 일시</th>
						<td>
							<input type="text" id="P_BRFS_DT" name="P_BRFS_DT" class="w180" date placeholder="입찰설명회일시" value="${comFn:formatDate(bidReqDetail.BRFS_DT,'yyyyMMdd','yyyy-MM-dd')}"/>
	                        <span class="wave">&nbsp;</span>
	                        <%-- <input type="text" class="float-left" id="P_BRFS_DT_HH" name="P_BRFS_DT_HH" style="width: 50px" maxlength="2" numeric placeholder="시" value="${comFn:formatDate(bidReqDetail.BRFS_DT,'yyyyMMddHHmmss','HH')}"/><span class="wave">:</span>
	                        <input type="text" class="float-left" id="P_BRFS_DT_MM" name="P_BRFS_DT_MM" style="width: 50px" maxlength="2" numeric placeholder="분" value="${comFn:formatDate(bidReqDetail.BRFS_DT,'yyyyMMddHHmmss','mm')}"/> --%>
						</td>
					</tr>
					<%-- 입찰설명회 여부가 [예] 일 경우에 활성화 End --%>
					<%-- 협상,2단계입찰,규격가격분리동시일 경우에 활성화 Start --%>
					<tr class="prpdcTr" style="display: none;">
						<th scope="row">제안/규격서 제출기간</th>
						<td>
							<div style="display: inline-block;">
			                    <input type="text"  id="P_PRPDC_SBMT_STDT" name="P_PRPDC_SBMT_STDT" class="w180 prpdcInput" date placeholder="제안/규격서 제출시작일" value="${comFn:formatDate(bidReqDetail.PRPDC_SBMT_STDT,'yyyyMMdd','yyyy-MM-dd')}" />
		                        <span class="wave">&nbsp;</span>
		                        <%-- <input type="text" class="float-left prpdcInput" id="P_PRPDC_SBMT_STDT_HH" name="P_PRPDC_SBMT_STDT_HH" style="width: 50px" maxlength="2" numeric placeholder="시" value="${comFn:formatDate(bidReqDetail.PRPDC_SBMT_STDT,'yyyyMMddHHmmss','HH')}"><span class="wave">:</span>
		                        <input type="text" class="float-left prpdcInput" id="P_PRPDC_SBMT_STDT_MM" name="P_PRPDC_SBMT_STDT_MM" style="width: 50px" maxlength="2" numeric placeholder="분" value="${comFn:formatDate(bidReqDetail.PRPDC_SBMT_STDT,'yyyyMMddHHmmss','mm')}"> --%>
	                        </div>
	                        <span class="wave">~</span>
	                        <div style="display: inline-block;">
			                    <input type="text"  id="P_PRPDC_SBMT_ENDT" name="P_PRPDC_SBMT_ENDT" class="w180 prpdcInput" date placeholder="제안/규격서 제출종료일" value="${comFn:formatDate(bidReqDetail.PRPDC_SBMT_ENDT,'yyyyMMddHHmmss','yyyy-MM-dd')}" />
                      				<span class="wave">&nbsp;</span>
		                        <input type="text" class="float-left prpdcInput" id="P_PRPDC_SBMT_ENDT_HH" name="P_PRPDC_SBMT_ENDT_HH" style="width: 50px" maxlength="2" numeric placeholder="시" value="${comFn:formatDate(bidReqDetail.PRPDC_SBMT_ENDT,'yyyyMMddHHmmss','HH')}"><span class="wave">:</span>
		                        <input type="text" class="float-left prpdcInput" id="P_PRPDC_SBMT_ENDT_MM" name="P_PRPDC_SBMT_ENDT_MM" style="width: 50px" maxlength="2" numeric placeholder="분" value="${comFn:formatDate(bidReqDetail.PRPDC_SBMT_ENDT,'yyyyMMddHHmmss','mm')}">
	                        </div>
						</td>
					</tr>
					<%-- 협상,2단계입찰,규격가격분리동시일 경우에 활성화 End --%>
					 <tr class="sbmtDiv">
					 	<th scope="row">입찰서 제출기간</th>
					 	<td>
					 		<div style="display: inline-block;">
			                    <input type="text"  id="P_BIDC_SBMT_STDT" name="P_BIDC_SBMT_STDT" class="w180" date placeholder="입찰서 제출시작일" value="${comFn:formatDate(bidReqDetail.BIDC_SBMT_STDT,'yyyyMMdd','yyyy-MM-dd')}" />
		                        <span class="wave">&nbsp;</span>
		                        <%-- <input type="text" class="float-left" id="P_BIDC_SBMT_STDT_HH" name="P_BIDC_SBMT_STDT_HH" style="width: 50px" maxlength="2" numeric placeholder="시" value="${comFn:formatDate(bidReqDetail.BIDC_SBMT_STDT,'yyyyMMddHHmmss','HH')}"><span class="wave">:</span>
		                        <input type="text" class="float-left" id="P_BIDC_SBMT_STDT_MM" name="P_BIDC_SBMT_STDT_MM" style="width: 50px" maxlength="2" numeric placeholder="분" value="${comFn:formatDate(bidReqDetail.BIDC_SBMT_STDT,'yyyyMMddHHmmss','mm')}"> --%>
	                        </div>
	                        <span class="wave">~</span>
	                        <div style="display: inline-block;">
			                    <input type="text"  id="P_BIDC_SBMT_ENDT" name="P_BIDC_SBMT_ENDT" class="w180" date placeholder="입찰서 제출종료일" value="${comFn:formatDate(bidReqDetail.BIDC_SBMT_ENDT,'yyyyMMddHHmmss','yyyy-MM-dd')}"/>
		                        <span class="wave">&nbsp;</span>
		                        <input type="text" class="float-left edOpDt" id="P_BIDC_SBMT_ENDT_HH" name="P_BIDC_SBMT_ENDT_HH" style="width: 50px" maxlength="2" numeric placeholder="시" value="${comFn:formatDate(bidReqDetail.BIDC_SBMT_ENDT,'yyyyMMddHHmmss','HH')}"><span class="wave">:</span>
		                        <input type="text" class="float-left edOpDt" id="P_BIDC_SBMT_ENDT_MM" name="P_BIDC_SBMT_ENDT_MM" style="width: 50px" maxlength="2" numeric placeholder="분" value="${comFn:formatDate(bidReqDetail.BIDC_SBMT_ENDT,'yyyyMMddHHmmss','mm')}">
	                       	</div>
                       	</td>
					 </tr>
					 <%-- <tr class="sbmtDiv">
					 	<th scope="row">개찰일시</th> 
					 	<td>
					 		<input type="text" id="P_OPNG_DT" name="P_OPNG_DT" class="w180" date placeholder="개찰일시" value="${comFn:formatDate(bidReqDetail.OPNG_DT,'yyyyMMddHHmmss','yyyy-MM-dd')}"/>
	                        <span class="wave">&nbsp;</span>
	                        <input type="text" class="float-left edOpDt" id="P_OPNG_DT_HH" name="P_OPNG_DT_HH" style="width: 50px" maxlength="2" numeric placeholder="시" value="${comFn:formatDate(bidReqDetail.OPNG_DT,'yyyyMMddHHmmss','HH')}"><span class="wave">:</span>
	                        <input type="text" class="float-left edOpDt" id="P_OPNG_DT_MM" name="P_OPNG_DT_MM" style="width: 50px" maxlength="2" numeric placeholder="분" value="${comFn:formatDate(bidReqDetail.OPNG_DT,'yyyyMMddHHmmss','mm')}">
					 	</td>
					 </tr> --%>
				</tbody>
			</table><!--// form_tb -->
			
		<!-- 입찰진행순서 end-->
			
			
			
				<p class="spc_stit">품목정보
					<button type="button" class="btn" id="itemdelBtn">삭제</button>
					<button type="button" class="btn" id="itemAddBtn">추가</button>
				</p>
				<div style="overflow-x: scroll; overflow-y:hidden" >
					<table class="tb">
						<caption>품목정보</caption>
						<colgroup>
							<col width="50px;">
							<col width="150px;">
							<col width="200px;">
							<col width="150px;">
							<col width="200px;">
							<col width="100px;">
							<col width="100px;">
							<col width="150px;">
							<col width="150px;">
							<col width="250px;">
							<col width="180px;"><!-- 부가세 -->
							<col width="180px;"><!-- 추정금액 --> 
							<col width="300px;">
							<col width="200px;">
						</colgroup>
						<thead>
							<tr >
								<th scope="col">
			                    	<label for="itemChoiseAllCbx" class="blind"></label>
			                    	<input type="checkbox" id="itemChoiseAllCbx" name="itemChoiseCbx" onclick="FwkCmmnUtil.setAllCheck('itemChoiseAllCbx','itemChoiseCbx');">
			                    </th>
								<th scope="col" >재산유형</th>
								<th scope="col" >품목코드</th>
								<th scope="col" >품목(재산)명</th>
								<th scope="col" >건명(용도)</th>
								<th scope="col" >단위</th>
								<th scope="col" >수량</th>
								<th scope="col" >추정단가</th>
								<th scope="col" >추정가격</th>
								<th scope="col" >부가세구분</th>
								<th scope="col" >부가세</th>
								<th scope="col" >추정금액</th>
								<th scope="col" >예산명(프로젝트명)</th>
								<th scope="col" >예산년월</th>
							</tr>
						</thead>
						<tbody id="itemHideTbdy"  style="display: none;">
			            	<tr>
								<td class="txtc">
		                			<label for="itemChoiseCbx" class="blind"></label>
									<input type="checkbox" id="itemChoiseCbx" name="itemChoiseCbx" style="text-align: center;">
								</td>
								<td class="txtc">
									<span>
										<select  id="P_ASTS_TYCD" class="select w100p" >
											<option value="">선택</option>
											<c:forEach var="astsData" items="${astsTycdList}" varStatus="astsStatus" >
												<option value="${astsData.CD_DTL_ID}" <c:if test="${astsData.ORD_SN eq '1' }">selected</c:if><c:if test="${astsData.ORD_SN ne '1' }">style="background:#e0e0e0;"</c:if>>${astsData.CD_DTL_NM}</option> 
											</c:forEach>
										</select>
									</span>
								</td>
								<td class="txtc">
									<input type="text" id="P_ITEM_NO"  class="input w60p" style="background: #f0f0f0;"  readonly="readonly" placeholder="품목코드" />
									<button type="button" class="btn ty03" id="itemSrchBtn"  name="itemSrchBtn" value="${status.index }" >검색</button>
								</td>
								<td class="txtc">
									<input type="text" id="P_ITEM_NM"  class="input w100p" readonly="readonly" style="background: #f0f0f0;" placeholder="품목(재산)명" />
								</td>
								<td class="txtc">
									<input type="text" id="P_ITEM_DTL" class="input w100p" placeholder="건명(용도)" />
								</td>
								<td class="txtc">
									<comTag:comCmcdCdValueComboBox name=""  id="P_ITEM_UNCD" cdId="ITEM_UNCD" selectKey="${bidReqDetail.ITEM_UNCD}" cond2="" headerValue="전체"  className="select w100p"/>
									<input type="hidden"  id="P_STND_NM" />
								</td>
								<td class="txtc">
									<input type="text" id="P_ITEM_QTY"   class="input rt w100p" placeholder="수량" money />
								</td>
								<td class="txtc">
									<input type="text" id="P_ESTT_UPRC"  class="input rt w100p" placeholder="추정단가" money />
								</td>
								<td class="txtc">
									<input type="text" id="P_ESTT_PRCE"   class="input rt w100p" placeholder="추정가격" money />
								</td>
								<td class="txtc">
									<span>
										<select id="P_STAX_SECD" class="select w100p" >
											<option value="">선택</option>
											<c:forEach var="data" items="${staxSecdList}" varStatus="staxStatus" >
												<option value="${data.CD_DTL_ID}" <c:if test="${data.ETC_VAL1 ne 'Y' }">style="background:#e0e0e0;"</c:if>>${data.CD_DTL_NM}</option> 
											</c:forEach>
										</select>
									</span>
								</td>
								<td class="txtc">
									<input type="text" id="P_ITEM_STAX"   class="input rt w100p" placeholder="부가세" money0 />
								</td>
								<td class="txtc">
									<input type="text" id="P_ESTT_AMT"    class="input  rt w100p" placeholder="추정금액" money />
								</td>
								<td class="txtc">
									<input type="text" id="P_ACNT_NM" name="" class="input w70p" placeholder="예산명(프로젝트명)" style="background: #f0f0f0;" readonly="readonly"/> 
									<input type="hidden"  id="P_ACNT_CD" >
									<input type="hidden"  id="P_ACNT_ITEM_CD">
									<button type="button" class="btn ty03" id="budgetSrchBtn"  name="budgetSrchBtn" value="${status.index }">검색</button>
								</td>
								<td class="txtc">
									<input type="text" id="P_BDG_YR" name="" class="input w40p" placeholder="예산년" style="background: #f0f0f0;" readonly="readonly"/>/<input type="text" id="P_BDG_MM" name="" class="input w40p" placeholder="예산월" style="background: #f0f0f0;" readonly="readonly"/>
									<input type="hidden"  id="P_BSNS_CD" >
									<input type="hidden"  id="P_DEPT_NO" >
								</td>
			            	</tr>
			            </tbody>
			            <tbody id="itemShowTbdy">
			            	<c:if test="${not empty bidReqItemList}">
								<c:forEach var="data" items="${bidReqItemList}" varStatus="status" >
					            	<tr>
										<td class="txtc">
				                			<label for="itemChoiseCbx" class="blind"></label>
											<input type="checkbox" id="itemChoiseCbx" name="itemChoiseCbx" style="text-align: center;">
										</td>
										<td class="txtc">
											<span>
												<select  name="P_ASTS_TYCD" class="select w100p" >
													<option value="">선택</option>
													<c:forEach var="astsData" items="${astsTycdList}" varStatus="astsStatus" >
														<option value="${astsData.CD_DTL_ID}" <c:if test="${astsData.CD_DTL_ID eq data.ASTS_TYCD }">selected="selected"</c:if><c:if test="${astsData.ORD_SN ne '1' }">style="background:#e0e0e0;"</c:if>>${astsData.CD_DTL_NM}</option> 
													</c:forEach>
												</select>
											</span>
										</td>
										<td class="txtc">
											<input type="text"  id="P_ITEM_NO${status.index }" name="P_ITEM_NO"  value="${data.ITEM_NO }" class="input w60p" style="background: #f0f0f0;"  readonly="readonly" placeholder="품목코드" />
											<button type="button" class="btn ty03" id="itemSrchBtn${status.index }"  name="itemSrchBtn" value="${status.index }" >검색</button>
										</td>
										<td class="txtc"><input type="text"  id="P_ITEM_NM${status.index }" name="P_ITEM_NM"  value="${data.ITEM_NM }" class="input w100p" readonly="readonly" style="background: #f0f0f0;" placeholder="품목(재산)명" /></td>
										<td class="txtc"><input type="text"  id="P_ITEM_DTL${status.index }" name="P_ITEM_DTL"  value="${data.ITEM_DTL }"  class="input w100p" placeholder="건명(용도)" /></td>
										<td class="txtc">
											<comTag:comCmcdCdValueComboBox name="P_ITEM_UNCD" cdId="ITEM_UNCD" selectKey="${data.ITEM_UNCD}" cond2="" headerValue="전체"  className="select w100p"/></td>
											<input type="hidden"  id="P_STND_NM${status.index }" name="P_STND_NM"  value="${data.STND_NM }">
										</td>
										<td class="txtc"><input type="text"  id="P_ITEM_QTY${status.index }" name="P_ITEM_QTY"  value="${comFn:formatMoney(data.ITEM_QTY) }"class="input rt w100p" placeholder="수량" money /></td>
										<td class="txtc"><input type="text"  id="P_ESTT_UPRC${status.index }" name="P_ESTT_UPRC"  value="${comFn:formatMoney(data.ESTT_UPRC) }"  class="input rt w100p" placeholder="추정단가" money /></td>
										<td class="txtc"><input type="text"  id="P_ESTT_PRCE${status.index }" name="P_ESTT_PRCE"  value="${comFn:formatMoney(data.ESTT_PRCE) }" class="input rt w100p" placeholder="추정가격" money /></td>
										<td class="txtc">
											<span>
												<select name="P_STAX_SECD" id="P_STAX_SECD${status.index }" class="select w100p" >
													<option value="">선택</option>
													<c:forEach var="dataStax" items="${staxSecdList}" varStatus="statusStax" >
														<option value="${dataStax.CD_DTL_ID}" <c:if test="${dataStax.ETC_VAL1 ne 'Y' }">style="background:#e0e0e0;"</c:if> <c:if test="${data.STAX_SECD eq dataStax.CD_DTL_ID }">selected=""</c:if>>${dataStax.CD_DTL_NM}</option> 
													</c:forEach>
												</select>
											</span>
										</td>
										<td class="txtc"><input type="text"  id="P_ITEM_STAX${status.index }" name="P_ITEM_STAX"  value="${comFn:formatMoney(data.ITEM_STAX) }" class="input rt w100p" placeholder="부가세" money0 /></td>
										<td class="txtc"><input type="text"  id="P_ESTT_AMT${status.index }" name="P_ESTT_AMT"  value="${comFn:formatMoney(data.ESTT_AMT) }" class="input rt w100p" placeholder="추정금액" money /></td>
										<td class="txtc">
											<input type="text"  id="P_ACNT_NM${status.index }" name="P_ACNT_NM"  value="${data.ACNT_NM }"  class="input w70p" placeholder="예산명(프로젝트명)" style="background: #f0f0f0;" readonly="readonly"/>
											<input type="hidden"  id="P_ACNT_CD${status.index }" name="P_ACNT_CD"  value="${data.ACNT_CD }" />
											<input type="hidden"  id="P_ACNT_ITEM_CD${status.index }" name="P_ACNT_ITEM_CD"  value="${data.ACNT_ITEM_CD }" />
											<button type="button" class="btn ty03" id="budgetSrchBtn${status.index }"  name="budgetSrchBtn" value="${status.index }">검색</button>
										</td>
										<td class="txtc">
											<input type="text" id="P_BDG_YR${status.index }" name="P_BDG_YR" class="input w40p" placeholder="예산년" style="background: #f0f0f0;" readonly="readonly" value="${data.BDG_YR }"/>/<input type="text" id="P_BDG_MM${status.index}" name="P_BDG_MM" class="input w40p" placeholder="예산월" style="background: #f0f0f0;" readonly="readonly"  value="${data.BDG_MM }" />
											<input type="hidden"  id="P_BSNS_CD${status.index }" name="P_BSNS_CD"  value="${data.BSNS_CD }" />
											<input type="hidden"  id="P_DEPT_NO${status.index }" name="P_DEPT_NO"  value="${data.DEPT_NO }" />
										</td>
					            	</tr> 
					            </c:forEach>
					    	</c:if>
			            </tbody>
					</table><!--// form_tb -->
				</div>
				<br>
				<div style="margin-top: 10px;">
					<p class='spc_stit'><button type='button' class='btn ty02' name='filesample'>첨부파일샘플</button><input type='hidden' value=""  id='P_RECM_ID'/></p>
				</div>
				<div class="essfileShowList tit_area" style="margin-top: 10px; display: none;">
					<p class='spc_stit'>필수첨부파일</p>
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
						<c:forEach var="data" items="${bidFile}" varStatus="idx">
							<tr>
								<td>${data.BID_FSCD_NM}</td> 
								<td class="txtl vtop"> 
									<span style="float:left;"><a href="javascript:download('${data.FILE_GRP_NO}', '${data.FILE_SN}');">${data.SYS_FILE_NM}&nbsp;</a></span>
									<span style="float:right;"><button type="button" class="btn" onclick="fileModBtn(this);">수정</button></span>
			                    </td>
			                    <td class="txtl vtop" style="display: none;"> 
									<input type="hidden" name="P_FILE_DOC_SECD" value="${data.BID_FSCD}" disabled="disabled" />
									<span style="float:left;"><input type="file" style="width: 800px;" onchange="fileSet(this);" /></span>
									<span style="float:right;"><button type="button" class="btn" onclick="fileCancleBtn(this);">취소</button></span>
			                    </td>	 
							</tr> 
						</c:forEach> 
					</tbody>
				</table>
			</div>
			<div class="essfileList"></div>
			<br>
			<p class="spc_stit">기타첨부파일</p>
			<div class="fileViewer"> 
				<!-- 업로드 삽입. -->
				<script type="text/javascript">
					var raonkParam = {
			            Id: "uploadView1",
			            Width: "100%",
			            Height: "200px",
			            ButtonBarEdit: "download,add,remove,remove_all",
			            BorderStyle: "solid",
			            FolderNameRule: "yyyy/mm/dd/ebid/bid"
			        };
					var raonkUpload = new RAONKUpload(raonkParam);
				</script>
	        </div>
	        <div id="upload_fileInfo"></div>
		
		
			<div class="btm_btns">
					<button type="button" class="btn ty02" id="saveBtn">수정</button>
				<button type="button" class="btn ty04" id="listBtn">목록</button>
			</div><!--// btm_btns --> 
		</div><!--// sp_cont --> 
	</form>
</div><!-- sp_sec -->

<!-- LIST FORM -->
<form id="listFrm" method="POST">
	<input type="hidden" id="resourceName" name="resourceName" value="${comFn:nvl(param.resourceName, resourceName)}">
</form>

<!-- DETAIL FORM -->
<form id="detailFrm" method="POST" > 
	<input type="hidden" name="resourceName" value="${comFn:nvl(param.resourceName, resourceName)}" >
	<input type="hidden" name="P_ARA_DEPT_CD" >
	<input type="hidden" name="P_PRCH_CHRG_SECD" >
	<input type="hidden" name="P_PVCT_RSN_NO" >
	<input type="hidden" name="P_ANNC_NO">
	<input type="hidden" name="P_ANNC_NGR"> 
	<input type="hidden" name="P_ROUND_NO">
</form>


<!-- POPUP FORM -->
<form id="popupFrm" method="POST">
	<input type="hidden" name="resourceName" value="${comFn:nvl(param.resourceName, resourceName)}">
	<input type="hidden" name="P_ANNC_NO"  value="${bidReqDetail.ANNC_NO}"> 
	<input type="hidden" name="P_ANNC_NGR"  value="${bidReqDetail.ANNC_NGR}">
	<input type="hidden" name="P_ROUND_NO"  value="${bidReqDetail.ROUND_NO}">
	<input type="hidden" name="P_BID_PSCD" value="">
	<input type="hidden" name="setChargerGbn" id="setChargerGbn">
	<input type="hidden" name="P_DEPT_NM_S"  value="${bidReqDetail.RQR_DEPT_NM}" />
	<input type="hidden" name="P_CONT_SECD_S"  value="${bidReqDetail.CONT_SECD}" />
	<input type="hidden" name="P_SEARCH_S"  value="N" />
	<input type="hidden" name="P_RECM_ID"  value="" />
</form>

<form id="downloadFrm" method="POST">
	<input type="hidden" name="P_FILE_GRP_NO"  id="P_FILE_GRP_NO">
	<input type="hidden" name="P_FILE_SN"  id="P_FILE_SN">
</form>
