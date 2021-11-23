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
<script type="text/javascript" src="${jsPath}/ipro/views/ebid/bidPblancPrcnDetail.js"></script>

<div class="sp_sec">
	<div class="nav_sec">
		<ul id="breadcrumbs">
			<li class="home">홈</li>
			<li>${myMenuList.bigMenuNm}</li>
			<li>${myMenuSubList.smallMenuNm}</li> 
		</ul>
	</div><!--// nav_sec --> 
	
	<h3 class="sp_tit">입찰공고진행 상세</h3>
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
		<input type="hidden" name="P_BID_PSCD" value=""> 
		<input type="hidden" id="P_BID_PSCD_TEXT" name="P_BID_PSCD_TEXT">
		
		<input type="hidden" id="P_PAGE_GBN" name="P_PAGE_GBN" value="EBID" />
		  
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
		                    <th></th>
							<td></td>
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
		                	<th>우선구매대상_제3자단가</th>
		                	<td>
		                		<c:if test="${bidReqDetail.PRIO_PRCH_YN eq 'Y'}">예</c:if><c:if test="${bidReqDetail.PRIO_PRCH_YN eq 'N'}">아니요</c:if> 
		                	</td>
		                </tr>
		                <tr>
		                	<th>학술용역</th>
		                	<td>
		                		<c:if test="${bidReqDetail.SCLSH_SVC_YN eq 'Y'}">예</c:if><c:if test="${bidReqDetail.SCLSH_SVC_YN eq 'N'}">아니요</c:if>
		                	</td>
		                	<th>여성기업 또는 사회적기업</th>
		                	<td>
		                		<c:if test="${bidReqDetail.WMAN_SCTY_CORP_YN eq 'Y'}">예</c:if><c:if test="${bidReqDetail.WMAN_SCTY_CORP_YN eq 'N'}">아니요</c:if> 
		                	</td>
		                </tr>
		                <tr>
		                	<th>실적제한</th>
		                	<td>
		                		<c:if test="${bidReqDetail.ACPS_LMT_YN eq 'Y'}">예</c:if><c:if test="${bidReqDetail.ACPS_LMT_YN eq 'N'}">아니요</c:if>
		                	</td>
		                	<th>정보화사업</th>
		                	<td>
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
				<!-- <div class="det_tabs">
					<a href="#" class="on">공고상세</a>
					<a href="#">기술평가</a>
					<a href="#">개찰</a>
					<a href="#" class="disa">기술협상</a>
					<a href="#">낙찰</a>
					
				</div> --><!--// det_tabs -->
		 		<div class="det_tabs" style="margin-top: 30px;">
					<a class="on" id="1" name="tab" href='javascript:tabEvent(1);' >공고상세</a>
					<a id="2" name="tab" href='javascript:tabEvent(2);'>기술평가</a>
					<a id="3" name="tab" href='javascript:tabEvent(3);'>개찰</a>
					<a id="4" name="tab" href='javascript:tabEvent(4);'>기술협상</a>
					<a id="5" name="tab" href='javascript:tabEvent(5);'>낙찰</a>
				</div>
			
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
						<th scope="row">입찰공고번호</th> 
						<%-- <td><c:if test ="${bidReqDetail.BID_PSCD eq 'A003' || bidReqDetail.BID_PSCD eq 'A005'||bidReqDetail.BID_PSCD eq 'A007'}"><input type="text" name="P_REAL_ANNC_NO" class="input w100p" value="${bidReqDetail.REAL_ANNC_NO}" placeholder="입찰공고번호" /></c:if>
						<c:if test ="${bidReqDetail.BID_PSCD ne 'A003' && bidReqDetail.BID_PSCD ne 'A005'&& bidReqDetail.BID_PSCD ne 'A007'}">${bidReqDetail.REAL_ANNC_NO}</c:if></td> --%>
						<td>
							<input type="text" name="P_REAL_ANNC_NO" class="input w100p" value="${bidReqDetail.REAL_ANNC_NO}" placeholder="입찰공고번호" />
						</td> 
						<th scope="row">사업예산</th>  
						<td class="sort01">${comFn:formatMoney(bidReqDetail.ESTT_AMT)}원</td>
					</tr>
					<tr>
	                	<th>접수자</th> 
	                	<td>${comFn:nvl(bidReqDetail.CHRGR_NM, sessionScope.loginResult.USR_NM)}</td>  
	                	<th>접수일자</th>
	                	<td>
	                		${comFn:formatDate(comFn:nvl(bidReqDetail.ACPT_DE, currentDate),'yyyyMMdd','yyyy-MM-dd')}
						</td>
	                </tr>
					<!--입찰방법에 표현 -->
	         		<tr>
						<th scope="row">계약방법</th>
						<td>${bidReqDetail.CONT_MTCD_NM }</td>
						<th scope="row">예가방식</th>
						<td>${bidReqDetail.ESTPC_SECD_NM }</td>
					</tr>
					<tr>
			        	<th scope="row">낙찰방법</th>
			            <td>${bidReqDetail.SBID_MTCD_NM }</td> 
						<th scope="row">
							<%-- 낙찰자선정방식이 [적격심사, 제한적최저가]일 경우 활성화 --%>
							<label <c:if test="${bidReqDetail.SBID_MTCD ne '31' and bidReqDetail.SBID_MTCD ne '20'}">style="display: none;"</c:if>>낙찰하한율 (%)</label>
							<%-- 낙찰자선정방식이 [적격심사, 제한적최저가]일 활성화 End--%>
							
							<%-- 낙찰자선정방식이 [협상에의한계약]일 경우 활성화 --%>
							<label <c:if test="${bidReqDetail.SBID_MTCD ne '40'}">style="display: none;"</c:if>>합산비율</label>
							<%-- 낙찰자선정방식이 [협상에의한계약]일 활성화 End--%> 
						</th>
						<td>
		                    <div <c:if test="${bidReqDetail.SBID_MTCD ne '31' and bidReqDetail.SBID_MTCD ne '20'}"> style="display:none;"</c:if>>
			                    <%-- 낙찰자선정방식이 [적격심사, 제한적최저가]일 경우 활성화 낙찰하한율 --%>
		                        ${bidReqDetail.SBID_LWST_RT}&nbsp;%
		                    	<%-- 낙찰자선정방식이 [적격심사, 제한적최저가]일 경우 활성화 End--%>
		                    </div>
		                     
	                    	<%-- 낙찰자선정방식이 [협상에의한계약]일 경우 활성화 --%>
		                    <div <c:if test="${bidReqDetail.SBID_MTCD ne '40'}"> style="display:none;"</c:if>>
								기술점수비율&nbsp;${bidReqDetail.TCHN_SCR_RT}
								&nbsp;:&nbsp;
								가격점수비율&nbsp;${bidReqDetail.PRCE_SCR_RT}
								<br>
								적격제한점수&nbsp;:&nbsp;${bidReqDetail.ELGB_LMT_SCR}
		                    </div>
							<%-- 낙찰자선정방식이 [협상에의한계약]일  활성화 End--%>
	                    </td> 
			        </tr>
					<tr>
						<th scope="row">입찰설명회여부</th>
						<td><c:if test="${bidReqDetail.BID_BRFS_YN eq 'Y'}">예</c:if><c:if test="${bidReqDetail.BID_BRFS_YN eq 'N'}">아니요</c:if></td>
						<th><label <c:if test="${bidReqDetail.BID_BRFS_YN eq 'N'}">style="display: none;"</c:if>>입찰설명회장소</label></th>
						<td><span <c:if test="${bidReqDetail.BID_BRFS_YN eq 'N'}">style="display: none;"</c:if>>${bidReqDetail.BRFS_PLC_NM}</span></td>
					</tr>
					<%-- <tr>
						<th>입찰참가자격</th>
						<td colspan="3">${bidReqDetail.PRTC_QLF_CNTN}</td> 
					</tr> --%>
					<tr>
						<th>기타</th>
						<td colspan="3">${bidReqDetail.ETC_ITEM}</td>
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
					<tr class="line">
                    <th scope="row">입찰공고일시</th>
                    <td>${comFn:formatDate(bidReqDetail.ANNC_DT,'yyyyMMdd','yyyy-MM-dd')}</td>
                 </tr>
                <%-- 입찰설명회 여부가 [예] 일 경우에 활성화 --%>
                <tr <c:if test="${bidReqDetail.BID_BRFS_YN ne 'Y'}"> style="display:none;"</c:if>>
                    <th scope="row">입찰설명회 일시</th>
                    <td>${comFn:formatDate(bidReqDetail.BRFS_DT,'yyyyMMdd','yyyy-MM-dd')}</td>
                </tr>
                <%-- 입찰설명회 여부가 [예] 일 경우에 활성화 End --%>
                <!-- 협상,2단계입찰,규격가격분리동시일 경우에 활성화 Start -->
                <c:if test="${bidReqDetail.SBID_MTCD eq '40' or bidReqDetail.SBID_MTCD eq '33' or bidReqDetail.SBID_MTCD eq '34'}">
	                <tr>
	                    <th scope="row">제안/규격서 제출기간</th>
	                    <td>
		                    ${comFn:formatDate(bidReqDetail.PRPDC_SBMT_STDT,'yyyyMMdd','yyyy-MM-dd')}
	                        <span class="wave">~</span>
	                        ${comFn:formatDate(bidReqDetail.PRPDC_SBMT_ENDT,'yyyyMMddHHmmss','yyyy-MM-dd HH:mm')}
	                    </td>
	                </tr>
                </c:if>
                <!-- 협상,2단계입찰,규격가격분리동시일 경우에 활성화 End -->
                <tr>
                    <th scope="row">입찰서 제출기간</th>
                    <td>
                    	${comFn:formatDate(bidReqDetail.BIDC_SBMT_STDT,'yyyyMMdd','yyyy-MM-dd')}
                        <span class="wave">~</span>
                        ${comFn:formatDate(bidReqDetail.BIDC_SBMT_ENDT,'yyyyMMddHHmmss','yyyy-MM-dd HH:mm')}
                    </td>
                </tr>
               <%--  <tr>
                    <th scope="row">개찰일시</th>
                    <td>${comFn:formatDate(bidReqDetail.OPNG_DT,'yyyyMMddHHmmss','yyyy-MM-dd HH:mm')}</td>
                </tr> --%>
				</tbody>
			</table><!--// form_tb -->
			
		<!-- 입찰진행순서 end-->
			
			
			
				<p class="spc_stit">품목정보</p>
				<div style="overflow-x: scroll; overflow-y:hidden" >
					<table class="tb">
						<caption>품목정보</caption>
						<colgroup>
							<col width="50px;">
							<col width="150px;">
							<col width="100px;">
							<col width="150px;">
							<col width="300px;">
							<col width="100px;">
							<col width="100px;">
							<col width="150px;">
							<col width="150px;">
							<col width="200px;">
							<col width="150px;"><!-- 부가세 -->
							<col width="150px;"><!-- 추정금액 --> 
							<col width="250px;">
							<col width="150px;">
						</colgroup>
						<thead>
							<tr >
								<th scope="col"  class="noBg" >순번</th>
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
						<tbody>
			            	<c:if test="${empty bidReqItemList}">
								<tr class="row">
									<td colspan="14" align="center">검색 조건에 해당하는 데이터가 존재하지 않습니다.</td>
								</tr>
							</c:if>
							<c:if test="${not empty bidReqItemList}">
								<c:forEach var="data" items="${bidReqItemList}" varStatus="status" >
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
					</table><!--// form_tb -->
				</div>
				<div class="essfileShowList tit_area" style="margin-top: 10px; display: none;">
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
							<c:forEach var="data" items="${bidFile}" varStatus="idx">
								<tr>
									<td>${data.BID_FSCD_NM}</td>
									<td class="txtl"> 
										<a href="javascript:download('${data.FILE_GRP_NO}', '${data.FILE_SN}');">${data.SYS_FILE_NM}&nbsp;</a>
				                    </td>	
								</tr>
							</c:forEach> 
						</tbody>
				</table>
			</div>
			<br>
			<p class="spc_stit">기타첨부파일&nbsp;&nbsp;</p>
			<div class="fileViewer"> 
				<!-- 업로드 삽입. -->
				<script type="text/javascript">
					var raonkParam = {
			            Id: "uploadView1",
			            Width: "100%",
			            Height: "200px",
			            ButtonBarEdit: "open,download",
			            BorderStyle: "solid",
			        };
					var raonkUpload = new RAONKUpload(raonkParam);
				</script>
	        </div>
	        <div id="upload_fileInfo"></div>
		
		
			<div class="btm_btns">
				<!--   계약부서_[경영지원팀] 팀원 -->
				<%-- <c:if test="${loginResult.DEPT_NO eq bidReqDetail.PRCH_DEPT_NO ||  loginResult.AUTH_ID eq '1'}"> --%> 
				<c:if test="${loginResult.AUTH_ID eq '1' || loginResult.AUTH_ID eq '4' || loginResult.AUTH_ID eq '5' }"> 
				
					<c:if test="${bidReqDetail.BID_PSCD  ne 'A001'
									&& bidReqDetail.BID_PSCD  ne 'A002'
									&& bidReqDetail.BID_PSCD  ne 'A003'
									&& bidReqDetail.BID_PSCD  ne 'A004' 
									&& bidReqDetail.BID_PSCD  ne 'A005'
									&& bidReqDetail.BID_PSCD  ne 'A006'
									&& bidReqDetail.BID_PSCD  ne 'A007'
									&& bidReqDetail.BID_PSCD  ne 'A028'
								}"><!-- 입찰공고 이후상태에서만 가능 -->
						<button type="button" class="btn ty02" id="cancleBtn">입찰취소</button>
					</c:if> 
					<c:if test="${bidReqDetail.BID_PSCD  eq 'A006'}"><!-- 입찰접수승인 -->
						<button type="button" class="btn ty02" id="annoBtn">공고</button>
					</c:if>
					<c:if test="${bidReqDetail.BID_PSCD  eq 'A003' || bidReqDetail.BID_PSCD  eq 'A007'}"><!-- 입찰요청, 입찰접수반려 _ 경영지원팀원-->
						<button type="button" class="btn ty02" id="saveBtn">수정</button>
						<button type="button" class="btn ty02" id="rqstRtnBtn">요청반려</button>
					</c:if>
					<c:if test="${bidReqDetail.BID_PSCD  eq 'A003'}"><!-- 입찰요청일 경우 수정 가능   _ 경영지원팀원-->
						<button type="button" class="btn ty02" id="rqstRecBtn">요청접수</button> 
						<button type="button" class="btn ty02" id="rtnBtn">단순반려</button>
					</c:if> 
					<!--요청접수상태일 경우 _ [경영지원팀] 계약부서_팀장 --> 
					<c:if test="${bidReqDetail.BID_PSCD  eq 'A004' && loginResult.APPR_AUTH_YN eq 'Y'}">
						<button type="button" class="btn ty02" id="RecApplyBtn">접수승인</button>
						<button type="button" class="btn ty02" id="RecRtnBtn">접수반려</button>
						<button type="button" class="btn ty02" id="rtnBtn">단순반려</button>
					</c:if> 
					
				</c:if>	
				<button type="button" class="btn ty02" id="progHistBtn">진행이력</button>
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
</form>

<form id="downloadFrm" method="POST">
	<input type="hidden" name="P_FILE_GRP_NO"  id="P_FILE_GRP_NO">
	<input type="hidden" name="P_FILE_SN"  id="P_FILE_SN">
</form>
