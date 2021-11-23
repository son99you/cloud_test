<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 입찰현황 > 정정공고 수정 폼
 *
 * <pre>
 * ebid 
 *    |_ bidPblancReRegistForm.jsp
 * 
 * </pre>
 * @date : 2017. 06. 19
 * @version : 1.0
 * @author : 은우소프트 전상훈
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %> 
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %> 

<script type="text/javascript" src="${jsPath}/ipro/views/ebid/bidPblancReRegistForm.js"></script>

<div class="contents_wrap">
	<ul class="step_wrap">
		<li><a href="#">${myMenuList.bigMenuNm}</a></li>
		<li><a href="#">${myMenuSubList.smallMenuNm}</a></li>
	</ul>
	<div class="tit_wrap">
		<h3 class="tit">재공고 작성</h3>
	</div>

	<form id="bidPblancUpdtFrm" method="POST" enctype="multipart/form-data">
		<input type="hidden" id="resourceName" name="resourceName" value="${param.resourceName}">
		<input type="hidden" name="P_USR_ID" value="${sessionScope.loginResult.USR_ID}">
		
		<input type="hidden" name="P_ANNC_NO" value="">
   		<input type="hidden" name="P_ANNC_NGR" value="">
		<input type="hidden" id="P_ROUND_NO" name="P_ROUND_NO" value="${bidPlanDetail.ROUND_NO}">
		
		<input type="hidden" id="P_PAGE_NO" name="P_PAGE_NO" value="1">
		<input type="hidden" id="P_PAGE_SIZE" name="P_PAGE_SIZE" value="10">
	    <input type="hidden" id="P_FILE_GRP_NO" name="P_FILE_GRP_NO" value="${bidPlanDetail.FILE_GRP_NO}">
		<input type="hidden" id="P_DEL_SN" name="P_DEL_SN">
		
		<input type="hidden" id="P_IFC_ID" name="P_IFC_ID" value="${bidPlanDetail.IFC_ID}">
		<input type="hidden" id="P_PRCH_RQR_NO" name="P_PRCH_RQR_NO" value="${bidPlanDetail.PRCH_RQR_NO}">
		<input type="hidden" id="P_PRCH_NO" name="P_PRCH_NO" value="${bidPlanDetail.PRCH_NO}">
		
		<input type="hidden" id="P_BID_FSCD" name="P_BID_FSCD" value="DI01">
		<input type="hidden" id="P_FILE_GROUP_FLAG" name="P_FILE_GROUP_FLAG" value="bid">
			
		<input type="hidden" id="P_BF_ANNC_NO" name="P_BF_ANNC_NO" value="${bidPlanDetail.ANNC_NO}">
		<input type="hidden" id="P_BF_ANNC_NGR" name="P_BF_ANNC_NGR" value="${bidPlanDetail.ANNC_NGR}">
		
		<input type="hidden" id="P_CONT_SECD" name="P_CONT_SECD" value="${bidPlanDetail.CONT_SECD }">
		<input type="hidden" id="P_BID_MTCD" name="P_BID_MTCD" value="${bidPlanDetail.BID_MTCD}">
		
		<input type="hidden" id="P_RQSTR_ID" name="P_RQSTR_ID" value="${bidPlanDetail.RQSTR_ID}">
		<input type="hidden" id="P_RQSTR_NM" name="P_RQSTR_NM" value="${bidPlanDetail.RQSTR_NM}">
		<input type="hidden" id="P_RQSTR_TEL_NO" name="P_RQSTR_TEL_NO" value="${bidPlanDetail.RQSTR_TEL_NO}">
		<input type="hidden" id="P_RQST_DEPT_NO" name="P_RQST_DEPT_NO" value="${bidPlanDetail.RQST_DEPT_NO}">
		<input type="hidden" id="P_RQST_DEPT_NM" name="P_RQST_DEPT_NM" value="${bidPlanDetail.RQST_DEPT_NM}">
		<input type="hidden" id="P_RQSTR_EMAL" name="P_RQSTR_EMAL" value="${bidPlanDetail.RQSTR_EMAL}">
		
		<input type="hidden" name="P_DLGD_PLCD" value="${bidPlanDetail.DLGD_PLCD }">
		<input type="hidden" name="P_CONT_DECD" value="${bidPlanDetail.CONT_DECD }">					
		
		<fieldset>
			
			<div class="view_wrap typeA">
				<div class="tit_area">
					<h4 class="tit">기본정보</h4>
				</div>
				<div class="view_area">
					<table>
						<caption>기본정보</caption>
				    	<colgroup>
				        	<col style="width: 15%;">						        
				        	<col style="width: 35%;">						        
				        	<col style="width: 15%;">						        
				        	<col style="width: 35%;">						        
						</colgroup>
						<tbody>
					    	<tr>
					    		<th scope="row">구매번호</th>
					    		<td colspan="3">
					    			${bidPlanDetail.PRCH_NO}
					    		</td>
					    	</tr>
					    	<tr>	
					            <th scope="row" class="bullet_orange">입찰담당자</th>
					            <td>
									<input type="text" class="w180" id="usrNm" name="P_CHRGR_NM" value="${bidPlanDetail.CHRGR_NM}">
									<input type="hidden" id="usrId" name="P_CHRGR_ID" value="${bidPlanDetail.CHRGR_ID}">
									<input type="hidden" id="mail" name="P_CHRGR_EMAL" value="${bidPlanDetail.CHRGR_EMAL }">
									<input type="hidden" id="deptCd" name="P_CHRGR_DEPT_NO" value="${bidPlanDetail.CHRGR_DEPT_NO }">
									<input type="hidden" id="deptNm" name="P_CHRGR_DEPT_NM" value="${bidPlanDetail.CHRGR_DEPT_NM }">
									<button type="button" class="btn btn_s btn_sch" id="chargerBtn">검색</button>					            
					            </td>
					            <th scope="row" class="bullet_orange">입찰문의 전화번호</th>
					            <td>
					            	<input type="text" id="usrTel" name="P_CHRGR_TEL_NO" value="${bidPlanDetail.CHRGR_TEL_NO }">
					            </td>
				        	</tr>
					    	<tr>	
					            <th scope="row" class="bullet_orange">기술문의담당자</th>
					            <td>
									<input type="text" class="w180" id="tchnUsrNm" name="P_TCHN_CHRGR_NM" value="${bidPlanDetail.TCHN_CHRGR_NM }">
									<input type="hidden" id="tchnUsrId" name="P_TCHN_CHRGR_ID" value="${bidPlanDetail.TCHN_CHRGR_ID }">
									<input type="hidden" id="tchnMail" name="P_TCHN_CHRGR_EMAL" value="${bidPlanDetail.TCHN_CHRGR_EMAL }" class="w180">
									<button type="button" class="btn btn_s btn_sch" id="tchnChargerBtn">검색</button>					            
					            </td>
					            <th scope="row" class="bullet_orange">기술문의 전화번호</th>
					            <td>
					            	<input type="text" id="tchnUsrTel" name="P_TCHN_CHRGR_TEL_NO" value="${bidPlanDetail.TCHN_CHRGR_TEL_NO }">
					            </td>
				        	</tr>
						</tbody>						
					</table>
				</div>
				
				<div class="tit_area">
					<h4 class="tit">입찰개요</h4>
				</div>
				<div class="view_area">
					<table>
						<caption>입찰개요</caption>
				    	<colgroup>
				        	<col style="width: 15%;">						        
				        	<col style="width: 35%;">						        
				        	<col style="width: 15%;">						        
				        	<col style="width: 35%;">					        
						</colgroup>
				    	<tr>
				    		<th scope="row">입찰방법</th>
				    		<td>
				    			${bidPlanDetail.BID_MTCD_NM}
				    		</td>
				            <th scope="row">계약구분</th>
				            <td>
			            		${bidPlanDetail.CONT_SECD_NM }<c:if test="${bidPlanDetail.CONT_DECD eq '200'}">&nbsp;/&nbsp;${bidPlanDetail.CONT_DECD_NM}</c:if>
				            </td>
				        </tr>
				        <tr>
				            <th scope="row" class="bullet_orange"><label for="P_BID_NM">입찰공고명</label></th>
				            <td colspan="3">
		                        <input type="text" id="P_BID_NM" name="P_BID_NM"  value="${bidPlanDetail.BID_NM}" maxlength="1000">
				            </td>
				        </tr>
				        <tr class="grlDiv">    
				            <th scope="row">제안서(규격서)필수여부</th>
				            <td>
				            	<comTag:cmmnCdValueRadio id="P_PRPDC_ESS_YN" name="P_PRPDC_ESS_YN_DISP" selectKey="${comFn:nvl(bidPlanDetail.PRPDC_ESS_YN, 'N')}" list="{'Y':'예', 'N':'아니오'}"/>
				            	<input type="hidden" name="P_PRPDC_ESS_YN" value="N">
				            </td>
				            <th scope="row">G2B 동시게시여부</th>
				            <td>
				            	<comTag:cmmnCdValueRadio name="P_G2B_NTFY_YN"  selectKey="${comFn:nvl(bidPlanDetail.G2B_NTFY_YN, 'Y')}" list="{'Y':'예', 'N':'아니오'}"/>
				            	<input type="hidden" id="P_OPNG_SYS_SECD" name="P_OPNG_SYS_SECD" value="${bidPlanDetail.OPNG_SYS_SECD }">
				            </td>				            
						</tr>				        
						<tr class="emrgDiv" <c:if test="${bidPlanDetail.EMRG_YN ne 'Y'}"> style="display: none;"</c:if>>
				            <th scope="row" class="bullet_orange"><label for="P_EMRG_BID_RSN">긴급입찰사유</label></th>
				            <td colspan="3">
		                        <input type="text" id="P_EMRG_BID_RSN" name="P_EMRG_BID_RSN"  value="${bidPlanDetail.EMRG_BID_RSN}" maxlength="4000">
				            </td>
				        </tr>
				        <tr>
		                    <th scope="row"><label for="P_CONT_TE" class="cdtDiv">계약기간(TEXT)</label></th>
		                    <td>
		                        <input type="text" class="cdtDiv" id="P_CONT_TE" name="P_CONT_TE" value="${bidPlanDetail.CONT_TE}" maxlength="1000" onblur="contTeEvent(this)">
		                    </td>
				        	<th scope="row"><label for="P_CONT_STDE"  class="cddDiv">계약기간</label></th>
		                    <td>
		                    	<div class="cddDiv">
			                        <input type="text" id="P_CONT_STDE" name="P_CONT_STDE" class="w120" date value="${comFn:formatDate(bidPlanDetail.CONT_STDE,'yyyyMMdd','yyyy-MM-dd')}">
			                        &nbsp;~&nbsp;
			                        <input type="text" id="P_CONT_ENDE" name="P_CONT_ENDE" class="w120" date value="${comFn:formatDate(bidPlanDetail.CONT_ENDE,'yyyyMMdd','yyyy-MM-dd')}">
		                    	</div>
		                    </td>
		                </tr>
				        <tr class="grlDiv">
				        	<th scope="row" class="bullet_orange"><label for="P_ESTT_AMT">추정금액 (원)</label></th>
		                    <td>
		                        <input type="text" class="tr" id="P_ESTT_AMT" name="P_ESTT_AMT" money value="${comFn:formatMoney(bidPlanDetail.ESTT_AMT)}" maxlength="22">
		                    </td>
				        	<th scope="row" class="bullet_orange"><label for="P_ESTT_PRCE">추정가격 (원)</label></th>
		                    <td>
		                        <input type="text" class="tr" id="P_ESTT_PRCE" name="P_ESTT_PRCE" money value="${comFn:formatMoney(bidPlanDetail.ESTT_PRCE)}" maxlength="22">
		                    </td>
		                </tr>
		                <tr>
		                    <th scope="row">SW사업대상</th>
		                    <td colspan="3">
		                    	<comTag:cmmnCdValueRadio name="P_SW_BSNS_OBJ_YN"  selectKey="${bidPlanDetail.SW_BSNS_OBJ_YN }" list="{'Y':'예', 'N':'아니오'}"/>
		                    	<input type="hidden" name="P_TNDR_AMT_STAX_YN" value="Y">
		                    </td>
		                </tr>		                
		                <c:if test="${bidPlanDetail.CONT_SECD eq '4'}">
		                <tr>
							<th scope="row">통화</th>
							<td>
								<comTag:comCmcdCdValueComboBox id="P_CURR_SECD" name="P_CURR_SECD" selectKey="${bidPlanDetail.CURR_SECD}" cdId="CURR_SECD" headerKey="" headerValue="선택" width="160"/>
							</td>
							<th scope="row">환율</th>
							<td>
								<input type="text" class="tr" id="P_EXRT" name="P_EXRT" value="${comFn:formatMoney(bidPlanDetail.EXRT)}" maxlength="22" money>
							</td>		                    
		                </tr>
		                </c:if>
				    </table>
				</div>
				
				<div class="tit_area">
					<h4 class="tit">입찰방법</h4>
				</div>
				<div class="view_area">
					<table>
						<caption>입찰방법</caption>
				    	<colgroup>
				        	<col style="width: 15%;">						        
				        	<col style="width: 35%;">						        
				        	<col style="width: 15%;">						        
				        	<col style="width: 35%;">
				        </colgroup>
				        <tr>
				            <th scope="row" class="bullet_orange"><label for="P_CONT_MTCD">계약방법</label></th>
				            <td colspan="3">
			            		<comTag:comCmcdCdValueComboBox id="P_CONT_MTCD" name="P_CONT_MTCD"   selectKey="${bidPlanDetail.CONT_MTCD}" cdId="CONT_MTCD" headerKey="" headerValue="선택" width="160"/>
				            </td>
				        </tr>
				        <tr>
				        	<th scope="row" class="bullet_orange"><label for="P_SBID_MTCD">낙찰자선정방법</label></th>
				            <td>
			            		<comTag:comCmcdCdValueComboBox id="P_SBID_MTCD" name="P_SBID_MTCD"   selectKey="${bidPlanDetail.SBID_MTCD}" cdId="SBID_MTCD"  headerKey="" headerValue="선택" width="160"/>
				            </td>
							<th scope="row" id="negoTH">
								<%-- 낙찰자선정방식이 [적격심사]가 아닐 경우 활성화 --%>
								<label for="P_SBID_LWST_RT" class="elgbDiv grlDiv lwstDiv" <c:if test="${bidPlanDetail.SBID_MTCD ne '31' and bidPlanDetail.SBID_MTCD ne '20'}">style="display: none;"</c:if>>낙찰하한율 (%)</label>
								<%-- 낙찰자선정방식이 [적격심사]가 아닐 경우 활성화 End--%>
								<%-- 낙찰자선정방식이 [협상에의한계약]이 아닐 경우 활성화 --%>
								<label for="P_TCHN_SCR_RT" class="negoDiv grlDiv" <c:if test="${bidPlanDetail.SBID_MTCD ne '40'}">style="display: none;"</c:if>>합산비율</label>
								<%-- 낙찰자선정방식이 [협상에의한계약]이 아닐 경우 활성화 End--%>
							</th>
							<td>
			                    <div class="elgbDiv grlDiv lwstDiv" <c:if test="${bidPlanDetail.SBID_MTCD ne '31' and bidPlanDetail.SBID_MTCD ne '20'}"> style="display:none;"</c:if>>
				                    <%-- 낙찰자선정방식이 [적격심사]가 아닐 경우 활성화 --%>
			                        <input type="text" class="tr PT4 elgbDiv lwstDiv" id="P_SBID_LWST_RT" name="P_SBID_LWST_RT" value="${bidPlanDetail.SBID_LWST_RT}" maxlength="10">
			                    	<%-- 낙찰자선정방식이 [적격심사]가 아닐 경우 활성화 End--%>
			                    </div>
			                    <div class="negoDiv grlDiv" <c:if test="${bidPlanDetail.SBID_MTCD ne '40'}"> style="display:none;"</c:if>>
			                    	<%-- 낙찰자선정방식이 [협상에의한계약]이 아닐 경우 활성화 --%>
<%-- 			                        	<comTag:comCmcdCdValueComboBox id="P_SUM_RTCD" name="P_SUM_RTCD"   selectKey="${bidPlanDetail.SUM_RTCD}" cdId="SUM_RTCD" headerKey="" headerValue="선택" className="negoDiv" width="160"/> --%>
									기술점수비율&nbsp;<input type="text" class="negoDiv" id="P_TCHN_SCR_RT" name="P_TCHN_SCR_RT" value="${bidPlanDetail.TCHN_SCR_RT }" maxlength="1" numeric  style="width: 50px;">
									&nbsp;:&nbsp;
									가격점수비율&nbsp;<input type="text" class="negoDiv" id="P_PRCE_SCR_RT" name="P_PRCE_SCR_RT" value="${bidPlanDetail.PRCE_SCR_RT }" maxlength="1" numeric  style="width: 50px;">
									<%-- 낙찰자선정방식이 [협상에의한계약]이 아닐 경우 활성화 End--%>
			                    </div>
		                    </td>
				        </tr>
				        <tr class="elgbDiv grlDiv negoDiv" <c:if test="${bidPlanDetail.SBID_MTCD ne '31'}"> style="display:none;"</c:if>>
				        	<th scope="row"><label for="P_ELGB_ESTM_KDCD">적격심사종류</label></th>
				        	<td class="negoNotDiv">
				        		<comTag:comCmcdCdValueComboBox id="P_ELGB_ESTM_KDCD_CTRC" name="P_ELGB_ESTM_KDCD_VIEW" selectKey="${bidPlanDetail.ELGB_ESTM_KDCD}" cdId="ELGB_ESTM_KDCD" cond1="CTRC" className="ctrcDiv" headerKey="" headerValue="선택"/>
				        		<comTag:comCmcdCdValueComboBox id="P_ELGB_ESTM_KDCD_SVC_TCHN" name="P_ELGB_ESTM_KDCD_VIEW" selectKey="${bidPlanDetail.ELGB_ESTM_KDCD}" cdId="ELGB_ESTM_KDCD" cond1="SVC" cond2="TCHN" className="svcDiv svcKdDiv svcTchnDiv" headerKey="" headerValue="선택"/>
				        		<comTag:comCmcdCdValueComboBox id="P_ELGB_ESTM_KDCD_SVC_ACDM" name="P_ELGB_ESTM_KDCD_VIEW" selectKey="${bidPlanDetail.ELGB_ESTM_KDCD}" cdId="ELGB_ESTM_KDCD" cond1="SVC" cond2="ACDM" className="svcDiv svcKdDiv svcAcdmDiv" headerKey="" headerValue="선택"/>
				        		<comTag:comCmcdCdValueComboBox id="P_ELGB_ESTM_KDCD_SVC_FCLT" name="P_ELGB_ESTM_KDCD_VIEW" selectKey="${bidPlanDetail.ELGB_ESTM_KDCD}" cdId="ELGB_ESTM_KDCD" cond1="SVC" cond2="FCLT" className="svcDiv svcKdDiv svcFcltDiv" headerKey="" headerValue="선택"/>
				        		<comTag:comCmcdCdValueComboBox id="P_ELGB_ESTM_KDCD_SVC_INFO" name="P_ELGB_ESTM_KDCD_VIEW" selectKey="${bidPlanDetail.ELGB_ESTM_KDCD}" cdId="ELGB_ESTM_KDCD" cond1="SVC" cond2="INFO" className="svcDiv svcKdDiv svcInfoDiv" headerKey="" headerValue="선택"/>
				        		<comTag:comCmcdCdValueComboBox id="P_ELGB_ESTM_KDCD_SVC_WST" name="P_ELGB_ESTM_KDCD_VIEW" selectKey="${bidPlanDetail.ELGB_ESTM_KDCD}" cdId="ELGB_ESTM_KDCD" cond1="SVC" cond2="WST" className="svcDiv svcKdDiv svcWstDiv" headerKey="" headerValue="선택"/>
				        		<comTag:comCmcdCdValueComboBox id="P_ELGB_ESTM_KDCD_SVC_LAND" name="P_ELGB_ESTM_KDCD_VIEW" selectKey="${bidPlanDetail.ELGB_ESTM_KDCD}" cdId="ELGB_ESTM_KDCD" cond1="SVC" cond2="LAND" className="svcDiv svcKdDiv svcLandDiv" headerKey="" headerValue="선택"/>
				        		<comTag:comCmcdCdValueComboBox id="P_ELGB_ESTM_KDCD_PRCH" name="P_ELGB_ESTM_KDCD_VIEW" selectKey="${bidPlanDetail.ELGB_ESTM_KDCD}" cdId="ELGB_ESTM_KDCD" cond1="PRCH" className="buyDiv" headerKey="" headerValue="선택"/>
				        		<input type="hidden" id="P_ELGB_ESTM_KDCD" name="P_ELGB_ESTM_KDCD" value="${bidPlanDetail.ELGB_ESTM_KDCD}">
				        	</td>
				        	<th scope="row"><label for="P_ELGB_LMT_SCR">적격심사통과점수</label></th>
				        	<td><input type="text" class="PT0" id="P_ELGB_LMT_SCR" name="P_ELGB_LMT_SCR" value="${bidPlanDetail.ELGB_LMT_SCR }" numeric maxlength="3"></td>
				        </tr>
				        <tr class="grlDiv">
				        	<th scope="row" class="bullet_orange"><label for="P_ESTPC_SECD">예가방식</label></th>
				            <td>
			            		<comTag:comCmcdCdValueComboBox id="P_ESTPC_SECD" name="P_ESTPC_SECD"   selectKey="${bidPlanDetail.ESTPC_SECD}" cdId="ESTPC_SECD" headerKey="" headerValue="선택" width="160"/>
							</td>
				        	<th scope="row"><label for="P_BASE_AMT" class="estpcDiv" <c:if test="${bidPlanDetail.ESTPC_SECD eq '180002'}"> style="display:none;"</c:if>>기초금액 (원)</label></th>
				        	<td>
				        		<input type="text" class="tr estpcDiv" id="P_BASE_AMT" name="P_BASE_AMT" money value="${comFn:formatMoney(bidPlanDetail.BASE_AMT)}" maxlength="22" <c:if test="${bidPlanDetail.ESTPC_SECD eq '180002'}"> style="display:none;"</c:if>>
				        	</td>
				        </tr>
				        <tr class="prepDiv grlDiv" <c:if test="${bidPlanDetail.ESTPC_SECD ne '180000'}"> style="display:none;"</c:if>>
				        	<th scope="row"><label for="P_PLR_ESTPC_RNG_CD">복수예비가격범위</label></th>
				            <td colspan="3">
			            		<comTag:comCmcdCdValueComboBox id="P_PLR_ESTPC_RNG_CD" name="P_PLR_ESTPC_RNG_CD"   selectKey="${comFn:nvl(bidPlanDetail.PLR_ESTPC_RNG_CD,'NTN')}" cdId="PLR_ESTPC_RNG_CD" headerKey="" headerValue="선택" width="160"/>
			            		<input type="hidden" name="P_ESTPC_UP_CNT" value="7">
							</td>
				        </tr>
				        <tr class="grlDiv">    
				            <th scope="row">입찰설명회여부</th>
				            <td>
				            	<comTag:cmmnCdValueRadio name="P_BID_BRFS_YN"  selectKey="${comFn:nvl(bidPlanDetail.BID_BRFS_YN, 'Y')}" list="{'Y':'예', 'N':'아니오'}"/>
				            </td>
				            <%-- 입찰설명회 [예] 일 경우 활성화 --%>
				            <th scope="row"><label class="brfsDiv" <c:if test="${bidPlanDetail.BID_BRFS_YN ne 'Y'}">style="display:none;"</c:if>>입찰설명회필수참석여부</label></th>
				            <td>
				            	<div class="brfsDiv" <c:if test="${bidPlanDetail.BID_BRFS_YN ne 'Y'}">style="display:none;"</c:if>>
				            		<comTag:cmmnCdValueRadio name="P_BID_BRFS_ATND_YN"  selectKey="${comFn:nvl(bidPlanDetail.BID_BRFS_ATND_YN, 'Y')}" list="{'Y':'예', 'N':'아니오'}" className="brfsDiv" />
				            	</div>
				            </td>
						</tr>
						<tr class="brfsDiv grlDiv" <c:if test="${bidPlanDetail.BID_BRFS_YN ne 'Y'}">style="display:none;"</c:if>>    
				            <th scope="row"><label for="P_BRFS_PLC_NM">입찰설명회장소</label></th> 
		                    <td colspan="3">
		                        <input type="text" id="P_BRFS_PLC_NM" name="P_BRFS_PLC_NM" value="${bidPlanDetail.BRFS_PLC_NM}" maxlength="1000">
		                    </td>
						</tr>
						<%-- 입찰설명회 [예] 일 경우 활성화 END--%>
						<%-- 현장설명회 [예] 일 경우 활성화 END--%>
						<tr class="exmtNDiv grlDiv">
		                    <th scope="row"><label for="P_BID_GTAMT_RT" class="bidgrRtDiv">입찰보증률 (%)</label></th>
		                    <td>
		                    	<input type="text" id="P_BID_GTAMT_RT" name="P_BID_GTAMT_RT" value="${bidPlanDetail.BID_GTAMT_RT }" class="PT2 bidgrRtDiv">
		                    </td>
		                    <th scope="row"><label for="P_BIDGR_AMT" class="bidgrAmtDiv">입찰보증금 (원)</label></th>
		                    <td>
								<input type="text" id="P_BIDGR_AMT" name="P_BIDGR_AMT" value="${comFn:formatMoney(bidPlanDetail.BIDGR_AMT) }" money class="bidgrAmtDiv">
		                    </td>
		                </tr>
		                <tr class="grlDiv">
		                    <th scope="row"><label for="P_PRTC_QLF_CNTN">입찰참가자격</label></th>
		                    <td colspan="3">
		                        <textarea id="P_PRTC_QLF_CNTN" name="P_PRTC_QLF_CNTN" style="width: 100%; height: 120px;" maxlength="4000">${bidPlanDetail.PRTC_QLF_CNTN}</textarea>
		                    </td>
		                </tr>
		                <tr class="grlDiv">
		                	<th scope="row" class="bullet_orange"><label for="P_ASSO_SPDM_CD">공동수급</label></th>
		                    <td>
								<comTag:comCmcdCdValueComboBox id="P_ASSO_SPDM_CD" name="P_ASSO_SPDM_CD"   selectKey="${bidPlanDetail.ASSO_SPDM_CD}" cdId="ASSO_SPDM_CD" headerKey="" headerValue="선택" width="160"/>
		                    </td>
		                    <th scope="row" class="bullet_orange"><label for="P_SMPR_BID_SECD">동가입찰옵션</label></th>
		                    <td>
			            		<comTag:comCmcdCdValueComboBox id="P_SMPR_BID_SECD" name="P_SMPR_BID_SECD"   selectKey="${bidPlanDetail.SMPR_BID_SECD}" cdId="SMPR_BID_SECD" headerKey="" headerValue="선택" width="160"/>
		                    </td>
		                </tr>
				    </table>
				</div>
				
				<%-- 계약방법이 [지명경쟁] 일 경우 활성화 --%>
		        <div class="slctDiv" <c:if test="${bidPlanDetail.CONT_MTCD ne '10001' and bidPlanDetail.CONT_MTCD ne '10005'}"> style="display: none;"</c:if>>
		        	<div class="tit_area">
		        		<h4 class="tit" style="clear: both;">지명업체</h4>
				        <div class="btn_right">
				            <button type="button" class="btn btn_s2 btn_c2" id="nmenSearchBtn">업체추가</button>
				            <button type="button" class="btn btn_s2 btn_c2" id="nmenDeleteBtn">업체삭제</button>
				        </div>
			        </div>
					<div class="view_area" style="margin-bottom: 30px;">
			            <table>
			                <caption>지명업체선택</caption>
			                <colgroup>
			                    <col style="width: 5%;">
			                   	<col style="width: 20%;">
			                   	<col style="width: auto;">
			                   	<col style="width: 20%;">
			                </colgroup>
			                <thead>
				                <tr>
				                    <th scope="col" style="text-align: center;">
<!-- 					                    	<input type="checkbox" id="nmenChoiseAllCbx" name="nmenChoiseCbx" onclick="FwkCmmnUtil.setAllCheck('nmenChoiseAllCbx','nmenChoiseCbx');"> -->
											선택
				                    </th>
				                    <th scope="col" style="text-align: center;">사업자번호</th>
				                    <th scope="col" style="text-align: center;">업체명</th>
				                    <th scope="col" style="text-align: center;">대표자명</th>
				                </tr>
			                </thead>
			                <tbody id="nmenChoiseHiddTbdy">
			                	<tr style="display: none;">
									<td style="text-align: center;">
										<input type="checkbox" id="nmenChoiseCbx" name="nmenChoiseCbx">
										<input type="hidden" name="P_VEND_REG_NO" disabled>
									</td>
									<td bizrNo style="text-align: center;"></td>
									<td entrpsNm></td>
									<td rprsntvNm></td>
								</tr>
			                </tbody>
			                <tbody id="nmenChoiseShowTbdy">
			                	<c:forEach var="data" items="${bidNmfpcEntrpsList}" varStatus="status">
								<tr class="row">
									<td  style="text-align: center;">
										<input type="checkbox" id="nmenChoiseCbx${status.count}" name="nmenChoiseCbx">
										<input type="hidden" name="P_VEND_REG_NO" value="${data.VEND_REG_NO}">
										<c:set var="cnt" value="${status.count}" scope="request" />
									</td>
									<td style="text-align: center;">${data.BIZRNO}</td>
									<td>${data.VEND_NM}</td>
									<td>${data.RPRS_NM}</td>
								</tr>
								</c:forEach>
			                </tbody>
			                <tbody>
				               	<tr class="row" id="nmenChoiseEmpty" <c:if test="${not empty bidNmfpcEntrpsList}"> style="display:none;"</c:if>>
				               		<td colspan="4"  style="text-align: center;">선택된 업체가 없습니다.<input type="hidden" id="cnt" value="${cnt}"></td>
				               	</tr>
			               	</tbody>
			            </table>
			        </div>
		        </div>
		        <%-- 계약방법이 [지명경쟁] 일 경우 활성화 End --%>
		        
		        <%-- 계약방법이 [제한경쟁] 일 경우 활성화 --%>
		        <div class="lmtDiv" <c:if test="${bidPlanDetail.CONT_MTCD ne '10002'}"> style="display: none;"</c:if>>
		        	<h4 class="tit">투찰제한</h4>
		        	<div class="view_area" style="margin-bottom: 30px;">
						<table>
							<caption>투찰제한</caption>
					    	<colgroup>
					        	<col style="width: 170px;">
								<col style="width: 349px;">
								<col style="width: 170px;">
								<col style="width: auto;">
					        </colgroup>
					        <tr>
					            <th scope="row"><label for="P_ARA_LMT_CD">지역제한</label></th>
					            <td>
					            	<comTag:comCmcdCdValueComboBox id="P_ARA_LMT_CD" name="P_ARA_LMT_CD"   selectKey="${bidPlanDetail.ARA_LMT_CD}" cdId="ARA_LMT_CD" headerKey="" headerValue="선택" width="160"/>
					            </td>
					            <th scope="row"><label for="P_BTP_LMT_CD">업종제한</label></th>
					            <td>
					            	<input type="text" class="tr w180" id="P_BTP_LMT_CD" name="P_BTP_LMT_CD" value="${bidPlanDetail.BTP_LMT_CD}" />
					            </td>
					        </tr>
						</table>
					</div>
				</div>
		        <%-- 계약방법이 [제한경쟁] 일 경우 활성화 End --%>
				
				<div class="tit_area">
					<h4 class="tit">계약조건</h4>
				</div>
				<div class="view_area grlDiv">
		            <table>
		                <caption>계약조건</caption>
		                <colgroup>
				        	<col style="width: 15%;">						        
				        	<col style="width: 35%;">						        
				        	<col style="width: 15%;">						        
				        	<col style="width: 35%;">
		                </colgroup>
		                <tbody>
		                <tr>
		                    <th scope="row" class="bullet_orange"><label for="P_CPDF_RT">지체상금률 (%)</label></th>
		                    <td>
		                        <input type="text" class="tr PT2" id="P_CPDF_RT" name="P_CPDF_RT" value="<c:if test="${empty bidPlanDetail.ANNC_NO}">0.25</c:if><c:if test="${not empty bidPlanDetail.ANNC_NO}">${bidPlanDetail.CPDF_RT}</c:if>" numeric maxlength="4"> /1,000
		                    </td>
		                    <th scope="row" class="bullet_orange"><label for="P_CTFL_GTAMT_RT">계약보증금률 (%)</label></th>
		                    <td>
		                        <input type="text" class="tr PT2" id="P_CTFL_GTAMT_RT" name="P_CTFL_GTAMT_RT" value="<c:if test="${empty bidPlanDetail.ANNC_NO}">10</c:if><c:if test="${not empty bidPlanDetail.ANNC_NO}">${bidPlanDetail.CTFL_GTAMT_RT}</c:if>" numeric maxlength="4">
		                    </td>
		                </tr>
		                <tr>
		                    <th scope="row" class="bullet_orange"><label for="P_DFFL_GTAMT_RT">하자이행보증금률 (%)</label></th>
		                    <td>
		                        <input type="text" class="tr PT2" id="P_DFFL_GTAMT_RT" name="P_DFFL_GTAMT_RT" value="<c:if test="${empty bidPlanDetail.ANNC_NO}">0</c:if><c:if test="${not empty bidPlanDetail.ANNC_NO}">${bidPlanDetail.DFFL_GTAMT_RT}</c:if>" numeric maxlength="4">
		                    </td>
		                    <%-- 계약구분이 [구매] 일 경우 활성화 --%>
		                    <th scope="row"><label for="P_ITEM_DRCD" class="buyDiv" <c:if test="${bidPlanDetail.CONT_SECD ne '0'}"> style="display: none;"</c:if>>물품인도조건</label></th>
		                    <td>
		                    	<div class="buyDiv" <c:if test="${bidPlanDetail.CONT_SECD ne '0'}">style="display: none;"</c:if>>
			            			<comTag:comCmcdCdValueComboBox id="P_ITEM_DRCD" name="P_ITEM_DRCD"   selectKey="${bidPlanDetail.ITEM_DRCD}" cdId="ITEM_DRCD" headerKey="" headerValue="선택" className="buyDiv" width="160"/>
			            		</div>
		                    </td>
		                </tr>
		                <tr class="buyDiv" <c:if test="${bidPlanDetail.CONT_SECD ne '0'}"> style="display: none;"</c:if>>
		                    <th scope="row"><label for="P_DLGD_PLC_NM">납품장소</label></th>
		                    <td colspan="3">
		                        <textarea id="P_DLGD_PLC_NM" name="P_DLGD_PLC_NM" style="width: 100%; height: 50px;" maxlength="4000">${bidPlanDetail.DLGD_PLC_NM}</textarea>
		                    </td>
		                </tr>
		                <tr class="buyDiv" <c:if test="${bidPlanDetail.CONT_SECD ne '0'}"> style="display: none;"</c:if>>
		                    <th scope="row"><label for="P_DLGD_TE_CNTN">납품기한</label></th>
		                    <td colspan="3">
		                    	<textarea id="P_DLGD_TE_CNTN" name="P_DLGD_TE_CNTN" style="width: 100%; height: 50px;" maxlength="4000">${bidPlanDetail.DLGD_TE_CNTN}</textarea>
		                    </td>
		                </tr>
		                <%-- 
		                <tr class="buyDiv" <c:if test="${bidPlanDetail.CONT_SECD ne '0'}"> style="display: none;"</c:if>>
		                    <th scope="row"><label for="P_ISTL_LMT">설치기한</label></th>
		                    <td colspan="3">
		                    	<textarea id="P_ISTL_LMT" name="P_ISTL_LMT" style="width: 100%; height: 50px;" maxlength="4000">${bidPlanDetail.ISTL_LMT}</textarea>
		                    </td>
		                </tr>
		                --%>
		                <tr>
		                    <th scope="row"><label for="P_RMK">비고</label></th>
		                    <td colspan="3">
		                    	<textarea id="P_RMK" name="P_RMK" style="width: 100%; height: 50px;" maxlength="4000">${bidPlanDetail.RMK}</textarea>
		                    </td>
		                </tr>		                
		                <%-- 계약구분이 [구매] 일 경우 활성화 END --%>
		                </tbody>
		            </table>
		        </div>
		        
		        
				<div class="tit_area">
					<h4 class="tit">입찰진행순서</h4>
				</div>
				<div class="view_area grlDiv">
		            <table>
		                <caption>입찰진행순서</caption>
		                <colgroup>
				        	<col style="width: 15%;">						        
				        	<col style="width: 85%;">						        
		                </colgroup>
		                <tbody>
			                <tr>
			                    <th scope="row" class="bullet_orange"><label for="P_ANNC_DT">입찰공고게시일시</label></th>
			                    <td>
									<input type="text"  id="P_ANNC_DT" name="P_ANNC_DT" class="w120" date value="${comFn:formatDate(bidPlanDetail.ANNC_DT,'yyyyMMddHHmmss','yyyy-MM-dd')}">
			                        <span class="wave">&nbsp;</span>
			                        <input type="text" class="float-left" id="P_ANNC_DT_HH" name="P_ANNC_DT_HH" style="width: 50px" maxlength="2" numeric value="${comFn:formatDate(bidPlanDetail.ANNC_DT,'yyyyMMddHHmmss','HH')}"><span class="wave">:</span>
			                        <input type="text" class="float-left mr5" id="P_ANNC_DT_MM" name="P_ANNC_DT_MM" style="width: 50px" maxlength="2"  numeric value="${comFn:formatDate(bidPlanDetail.ANNC_DT,'yyyyMMddHHmmss','mm')}">
			                    </td>
			                </tr>
			                <%-- 입찰설명회 여부가 [예] 일 경우에 활성화 --%>
			                <c:if test="${bidPlanDetail.ROUND_NO < 2}">
			                <tr class="brfsDiv" <c:if test="${bidPlanDetail.BID_BRFS_YN ne 'Y'}"> style="display:none;"</c:if>>
			                    <th scope="row"><label for="P_BRFS_DT">입찰설명회일시</label></th>
			                    <td>
									<input type="text"  id="P_BRFS_DT" name="P_BRFS_DT" class="w120" date  value="${comFn:formatDate(bidPlanDetail.BRFS_DT,'yyyyMMddHHmmss','yyyy-MM-dd')}">
			                        <span class="wave">&nbsp;</span>
			                        <input type="text" class="float-left" id="P_BRFS_DT_HH" name="P_BRFS_DT_HH" style="width: 50px" maxlength="2" numeric  value="${comFn:formatDate(bidPlanDetail.BRFS_DT,'yyyyMMddHHmmss','HH')}"><span class="wave">:</span>
			                        <input type="text" class="float-left" id="P_BRFS_DT_MM" name="P_BRFS_DT_MM" style="width: 50px" maxlength="2" numeric  value="${comFn:formatDate(bidPlanDetail.BRFS_DT,'yyyyMMddHHmmss','mm')}">
			                    </td>
			                </tr>
			                <%-- 입찰설명회 여부가 [예] 일 경우에 활성화 End --%>
			                <tr class="prpdcDiv"<c:if test="${bidPlanDetail.PRPDC_ESS_YN ne 'Y'}"> style="display:none;"</c:if>>
			                    <th scope="row"><label for="P_PRPDC_SBMT_STDT">제안서제출기간</label></th>
			                    <td>
			                    	<div style="display: inline-block;">
					                    <input type="text"  id="P_PRPDC_SBMT_STDT" name="P_PRPDC_SBMT_STDT" class="w120" date  value="${comFn:formatDate(bidPlanDetail.PRPDC_SBMT_STDT,'yyyyMMddHHmmss','yyyy-MM-dd')}">
				                        <span class="wave">&nbsp;</span>
				                        <input type="text" class="float-left" id="P_PRPDC_SBMT_STDT_HH" name="P_PRPDC_SBMT_STDT_HH" style="width: 50px" maxlength="2" numeric  value="${comFn:formatDate(bidPlanDetail.PRPDC_SBMT_STDT,'yyyyMMddHHmmss','HH')}"><span class="wave">:</span>
				                        <input type="text" class="float-left" id="P_PRPDC_SBMT_STDT_MM" name="P_PRPDC_SBMT_STDT_MM" style="width: 50px" maxlength="2" numeric  value="${comFn:formatDate(bidPlanDetail.PRPDC_SBMT_STDT,'yyyyMMddHHmmss','mm')}">
			                        </div>
			                        <span class="wave">~</span>
			                        <div style="display: inline-block;">
					                    <input type="text"  id="P_PRPDC_SBMT_ENDT" name="P_PRPDC_SBMT_ENDT" class="w120" date  value="${comFn:formatDate(bidPlanDetail.PRPDC_SBMT_ENDT,'yyyyMMddHHmmss','yyyy-MM-dd')}">
	                       				<span class="wave">&nbsp;</span>
				                        <input type="text" class="float-left" id="P_PRPDC_SBMT_ENDT_HH" name="P_PRPDC_SBMT_ENDT_HH" style="width: 50px" maxlength="2" numeric  value="${comFn:formatDate(bidPlanDetail.PRPDC_SBMT_ENDT,'yyyyMMddHHmmss','HH')}"><span class="wave">:</span>
				                        <input type="text" class="float-left" id="P_PRPDC_SBMT_ENDT_MM" name="P_PRPDC_SBMT_ENDT_MM" style="width: 50px" maxlength="2" numeric  value="${comFn:formatDate(bidPlanDetail.PRPDC_SBMT_ENDT,'yyyyMMddHHmmss','mm')}">
									</div>
			                    </td>
			                </tr>
							</c:if>
			                <tr class="sbmtDiv"<c:if test="${bidPlanDetail.SBID_MTCD eq '34'}"> style="display:none;"</c:if>>
			                    <th scope="row" class="bullet_orange"><label for="P_BIDC_SBMT_STDT">입찰서제출기간</label></th>
			                    <td>
			                    	<div style="display: inline-block;">
					                    <input type="text"  id="P_BIDC_SBMT_STDT" name="P_BIDC_SBMT_STDT" class="w120" date  value="${comFn:formatDate(bidPlanDetail.BIDC_SBMT_STDT,'yyyyMMddHHmmss','yyyy-MM-dd')}">
				                        <span class="wave">&nbsp;</span>
				                        <input type="text" class="float-left" id="P_BIDC_SBMT_STDT_HH" name="P_BIDC_SBMT_STDT_HH" style="width: 50px" maxlength="2" numeric  value="${comFn:formatDate(bidPlanDetail.BIDC_SBMT_STDT,'yyyyMMddHHmmss','HH')}"><span class="wave">:</span>
				                        <input type="text" class="float-left" id="P_BIDC_SBMT_STDT_MM" name="P_BIDC_SBMT_STDT_MM" style="width: 50px" maxlength="2" numeric  value="${comFn:formatDate(bidPlanDetail.BIDC_SBMT_STDT,'yyyyMMddHHmmss','mm')}">
			                        </div>
			                        <span class="wave">~</span>
			                        <div style="display: inline-block;">
					                    <input type="text"  id="P_BIDC_SBMT_ENDT" name="P_BIDC_SBMT_ENDT" class="w120 edOpDt" date  value="${comFn:formatDate(bidPlanDetail.BIDC_SBMT_ENDT,'yyyyMMddHHmmss','yyyy-MM-dd')}">
				                        <span class="wave">&nbsp;</span>
				                        <input type="text" class="float-left edOpDt" id="P_BIDC_SBMT_ENDT_HH" name="P_BIDC_SBMT_ENDT_HH" style="width: 50px" maxlength="2" numeric  value="${comFn:formatDate(bidPlanDetail.BIDC_SBMT_ENDT,'yyyyMMddHHmmss','HH')}"><span class="wave">:</span>
				                        <input type="text" class="float-left edOpDt" id="P_BIDC_SBMT_ENDT_MM" name="P_BIDC_SBMT_ENDT_MM" style="width: 50px" maxlength="2" numeric  value="${comFn:formatDate(bidPlanDetail.BIDC_SBMT_ENDT,'yyyyMMddHHmmss','mm')}">
			                        </div>
			                    </td>
			                </tr>
			                <tr class="sbmtDiv"<c:if test="${bidPlanDetail.SBID_MTCD eq '34'}"> style="display:none;"</c:if>>
			                    <th scope="row" class="bullet_orange"><label for="P_OPNG_DT">개찰일시</label></th>
			                    <td>
				                    <input type="text"   id="P_OPNG_DT" name="P_OPNG_DT" class="w120 edOpDt" date  value="${comFn:formatDate(bidPlanDetail.OPNG_DT,'yyyyMMddHHmmss','yyyy-MM-dd')}">
			                        <span class="wave">&nbsp;</span>
			                        <input type="text" class="float-left edOpDt" id="P_OPNG_DT_HH" name="P_OPNG_DT_HH" style="width: 50px" maxlength="2" numeric  value="${comFn:formatDate(bidPlanDetail.OPNG_DT,'yyyyMMddHHmmss','HH')}"><span class="wave">:</span>
			                        <input type="text" class="float-left edOpDt" id="P_OPNG_DT_MM" name="P_OPNG_DT_MM" style="width: 50px" maxlength="2" numeric  value="${comFn:formatDate(bidPlanDetail.OPNG_DT,'yyyyMMddHHmmss','mm')}">
			                    </td>
			                </tr>
		                </tbody>
		            </table>
		        </div>
		        
		        <div class="buyDiv">
		        	<div class="tit_area">
				       	<h4 class="tit" style="clear: both;">입찰품목</h4>
				       	<c:if test="${ bidPlanDetail.UNI_ANNC_PSBL_YN eq 'Y' }">
				        <div class="btn_right">
				            <button type="button" class="btn btn_s2 btn_c2" id="biprInfoSearchBtn">품목추가</button>
				            <button type="button" class="btn btn_s2 btn_c2" id="biprInfoDeleteBtn">품목삭제</button>
				        </div>
				        </c:if>
			        </div>
					<div class="view_area" style="margin-bottom: 30px; overflow: auto;">
			            <table style="width:100%;">
			                <caption>입찰품목정보</caption>
			                <colgroup>
			                    <col style="width: 4%;">
			                   	<col style="width: 10%;">
			                   	<col style="width: 10%;">
			                   	<col style="width: auto;">
			                   	<col style="width: 15%;">
			                   	<col style="width: 8%;">
			                   	<col style="width: 6%;">
			                   	<col style="width: 8%;">
			                   	<col style="width: 8%;">
			                </colgroup>
			                <thead>
				                <tr>
				                    <th scope="col" style="text-align: center;">선택</th>
				                    <th scope="col" style="text-align: center;">구매요구번호</th>
				                    <th scope="col" style="text-align: center;">품목번호</th>
				                    <th scope="col" style="text-align: center;">품명</th>
				                    <th scope="col" style="text-align: center;">규격</th>
				                    <th scope="col" style="text-align: center;">단위</th>
				                    <th scope="col" style="text-align: center;">수량</th>
				                    <th scope="col" style="text-align: center;">추정단가<br/>(VAT포함)</th>
				                    <th scope="col" style="text-align: center;">추정금액<br/>(수량*단가)</th>
				                </tr>
			                </thead>
			                <tbody id="biprInfoShowTbdy">
			                	<c:forEach var="data" items="${bidPrdlsList}" varStatus="status">
									<tr class="row">
										<td style="text-align: center;">
											<input type="checkbox" id="biprInfoCbx${status.count }" name="biprInfoCbx">
											<input type="hidden" name="P_BID_WAIT_NO" value="${data.BID_WAIT_NO}">
											<input type="hidden" name="P_ITEM_SN" value="${data.ITEM_SN}">
											<input type="hidden" name="P_ITEM_NO" value="${data.ITEM_NO}">
											<input type="hidden" name="P_PCRQ_NO" value="${data.PCRQ_NO}">
											<input type="hidden" name="P_PCRQ_ITEM_SN" value="${data.PCRQ_ITEM_SN}">
										</td>
										<td style="text-align: center;">${data.PCRQ_NO}</td>
										<td style="text-align: center;">${data.ITEM_NO}</td>
										<td><input type="hidden" name="P_ITEM_NM" value="${data.ITEM_NM}">${data.ITEM_NM}</td>
										<td><input type="text" name="P_STND_NM"  value="${data.STND_NM}"></td>
										<td><input type="hidden" name="P_ITEM_UNNM" value="${data.ITEM_UNNM}">${data.ITEM_UNNM}</td>
										<td><input type="hidden" name="P_ITEM_QTY" value="${data.ITEM_QTY}">${data.ITEM_QTY}</td>
										<td>
											${comFn:formatMoney(data.RQST_UNIT)}
											<input type="hidden" name="P_RQST_UNIT" value="${data.RQST_UNIT}">
										</td>
										<td>
											${comFn:formatMoney(data.SCH_UNIT)}
											<input type="hidden" name="P_SCH_UNIT" value="${data.SCH_UNIT}">
										</td>
									</tr>
								</c:forEach>
			                </tbody>
			                <tbody>
								<tr class="row" id="biprInfoEmpty" <c:if test="${ not empty bidPrdlsList}"> style="display:none;"</c:if>>
				               		<td colspan="8" style="text-align: center;">선택된 입찰품목이 없습니다.</td>
				               	</tr>
			               	</tbody>
			            </table>
			        </div>
		        </div>
		        
				<div class="tit_area">
	            	<h4 class="tit">첨부파일</h4>
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
					<button type="button" class="btn btn_m btn_orange" id="UpdateBtn">저장</button>
			    	<button type="button" class="btn btn_m btn_del" id="cancelBtn">취소</button>
			    </div>
			</div>
		</fieldset>
	</form>
</div> <!--// content E-->

<form id="listFrm" method="POST">
	<input type="hidden" name="resourceName" value="${param.resourceName}">
	<input type="hidden" name="P_ANNC_NO">
	<input type="hidden" name="P_ANNC_NGR">
	<input type="hidden" name="P_ROUND_NO">	
</form>

<form id="popupFrm" method="POST">
	<input type="hidden" name="P_ANNC_NO" value="${bidPlanDetail.ANNC_NO}">
	<input type="hidden" name="P_ANNC_NGR" value="${bidPlanDetail.ANNC_NGR}">
	<input type="hidden" name="P_ROUND_NO" value="${bidPlanDetail.ROUND_NO }">
	<input type="hidden" name="P_PLR_ESTPC_NO">
	<input type="hidden" name="P_VEND_REG_NO">
	<input type="hidden" id="setMulti" name="setMulti">
	<input type="hidden" id="P_SBID_MTCD" value="${bidPlanDetail.SBID_MTCD}">
	<input type="hidden" name="P_BID_SBMT_FSCD">
	<input type="hidden" name="P_BID_TPI_SECD">
	<input type="hidden" name="P_BID_WAIT_NO" value="${bidPlanDetail.IFC_ID}">	
	<input type="hidden" name="gbn" value="BID">
</form>

<form id="reBidFrm" method="POST">
	<input type="hidden" name="resourceName" value="${param.resourceName}">
	<input type="hidden" name="P_ANNC_NO" value="${bidPlanDetail.ANNC_NO}">
	<input type="hidden" name="P_ANNC_NGR" value="${bidPlanDetail.ANNC_NGR}">
	<input type="hidden" name="P_ROUND_NO" value="${bidPlanDetail.ROUND_NO }">
	<input type="hidden" name="P_BID_PSCD">
	<input type="hidden" name="P_APPR_STCD">
</form>

<form id="fibFrm" method="POST">
   	<input type="hidden" name="P_ANNC_NO" value="${bidPlanDetail.ANNC_NO}">
   	<input type="hidden" name="P_ANNC_NGR" value="${bidPlanDetail.ANNC_NGR}">
   	<input type="hidden" name="P_ROUND_NO" value="${bidPlanDetail.ROUND_NO}">
</form>

<form id="vendPopupFrm" method="POST">
	<input type="hidden" name="P_ANNC_NO" value="${bidPlanDetail.ANNC_NO}">
	<input type="hidden" name="P_ANNC_NGR" value="${bidPlanDetail.ANNC_NGR}">
	<input type="hidden" name="P_ROUND_NO" value="${bidPlanDetail.ROUND_NO}">
	<input type="hidden" name="P_BID_PSCD" value="${bidPlanDetail.BID_PSCD}">
</form>
<form id="ajaxItemForm" method="POST">
	<input type="hidden" name="P_BID_WAIT_NO">
	<input type="hidden" name="gbn" value="BID">
</form>
<form id="detailFrm" method="POST">
	<input type="hidden" name="resourceName" value="${param.resourceName}">
	<input type="hidden" name="P_ANNC_NO" value="${bidPlanDetail.ANNC_NO}">
	<input type="hidden" name="P_ANNC_NGR" value="${bidPlanDetail.ANNC_NGR}">
	<input type="hidden" name="P_ROUND_NO" value="${bidPlanDetail.ROUND_NO }">
</form>