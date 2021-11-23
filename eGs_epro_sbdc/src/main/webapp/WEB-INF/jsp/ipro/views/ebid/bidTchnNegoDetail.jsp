<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 입찰관리 > 상세_기술평가
 *
 * <pre>
 * ebid 
 *    |_ bidTchnEstmDetail.jsp 
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
<script type="text/javascript" src="${jsPath}/ipro/views/ebid/bidTchnNegoDetail.js"></script>

<div class="sp_sec">
	<div class="nav_sec">
		<ul id="breadcrumbs">
			<li class="home">홈</li>
			<li>${myMenuList.bigMenuNm}</li>
			<li>${myMenuSubList.smallMenuNm}</li> 
		</ul>
	</div><!--// nav_sec --> 
	
	<h3 class="sp_tit">입찰공고진행 상세</h3>
	<form id="detailFrm" method="POST" enctype="multipart/form-data">
		<input type="hidden" name="resourceName" value="${comFn:nvl(param.resourceName, resourceName)}">
		<input type="hidden" id="contSecd" value="${bidReqDetail.CONT_SECD}" />
		<input type="hidden" id="contMtcd" value="${bidReqDetail.CONT_MTCD}" />
		<input type="hidden" id="sbidMtcd" value="${bidReqDetail.SBID_MTCD}" />
		<input type="hidden" name="P_ANNC_NO" value="${bidReqDetail.ANNC_NO}" />
		<input type="hidden" name="P_ANNC_NGR" value="${bidReqDetail.ANNC_NGR}" />
		<input type="hidden" name="P_ROUND_NO" value="${bidReqDetail.ROUND_NO}" />
		<input type="hidden" id="vendCnt" name="vendCnt" value="${vendListTotCnt}" />
		<input type="hidden" id="vendSn" name="vendSn" value="" />
		<input type="hidden" id="P_BID_PSCD" name="P_BID_PSCD" value="${bidReqDetail.BID_PSCD}">
		<input type="hidden" id="tchnNegoSaveYn" name="tchnNegoSaveYn" value="">
		
		<div class="sp_cont">
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
						<th scope="row">진행상태</th>
						<td>${comFn:nvl(bidReqDetail.BID_PSCD_NM,"작성대기")}</td>
						<th></th>
						<td></td>
						<%-- <th>결재상태</th>
		                <td><c:if test="${bidReqDetail.APPR_STCD eq 'N'}" >미결재</c:if><c:if test="${bidReqDetail.APPR_STCD eq 'Y'}" >결재</c:if></td> --%>
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
		                	<th>우선구매대상 제3자단가</th>
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
	 		<div class="det_tabs" style="margin-top: 30px;">
				<a id="1" name="tab" href='javascript:tabEvent(1);' >공고상세</a>
				<a id="2" name="tab" href='javascript:tabEvent(2);'>기술평가</a>
				<a  id="3" name="tab" href='javascript:tabEvent(3);'>개찰</a>
				<a class="on" id="4" name="tab" href='javascript:tabEvent(4);'>기술협상</a>
				<a id="5" name="tab" href='javascript:tabEvent(5);'>낙찰</a>
			</div>
				
			<%-- <c:if test="${loginResult.DEPT_NO eq bidReqDetail.RQR_DEPT_NO || loginResult.AUTH_ID eq '1'}"><!-- 사업부서에서 본인들이 평가  또는 관리자 --> --%>
			<c:if test="${bidReqDetail.BID_PSCD eq 'A019'}"><!-- 개찰 승인경우 -->
				<c:if test="${loginResult.AUTH_ID eq '1' ||  loginResult.AUTH_ID eq '4' || loginResult.AUTH_ID eq '5'}">
					<table class="form_tb">
						<colgroup>
							<col width="15%">
					        <col width="35%"> 
					        <col width="15%">
					        <col width="35%">
						</colgroup>
						<tbody>
							<tr> 
								<th scope="row">첨부파일(기술협상결과보고서)</th>
								<c:if test="${empty bidReqDetail.FILE_GRP_NO_TECH}">	
									<input type="hidden" id="techFileReg"  value="reg"/>
									<td class="txtl vtop" colspan="3">
										<input type="hidden" name="P_FILE_DOC_SECD" disabled="disabled" value="TECH" />
										<input type="file" onchange="fileSet(this);" style="width: 800px;" />
									</td>
								</c:if>
								<c:if test="${not empty bidReqDetail.FILE_GRP_NO_TECH}">
									<input type="hidden" id="techFileReg"  value="mod"/>	
									<c:forEach var="data" items="${bidTechFile}" varStatus="idx">
										<td class="txtl vtop" colspan="3">
											<span style="float:left;"><a href="javascript:download('${data.FILE_GRP_NO}', '${data.FILE_SN}');">${data.SYS_FILE_NM}&nbsp;</a></span>
											<span style="float:right;"><button type="button" class="btn" onclick="fileModBtn(this);">수정</button></span>
					                    </td>
					                    <td class="txtl vtop" style="display: none;" colspan="3"> 
											<input type="hidden" name="P_FILE_DOC_SECD" value="${data.BID_FSCD}" disabled="disabled" />
											<span style="float:left;"><input type="file" style="width: 800px;" onchange="fileSet(this);" /></span>
											<span style="float:right;"><button type="button" class="btn" onclick="fileCancleBtn(this);">취소</button></span>
					                    </td>	  
				                    </c:forEach>
								</c:if>
							</tr>
						</tbody>
					</table>	
					<p class="spc_stit">업체정보<!-- <button type="button" class="btn ty02" id="vendSaveBtn">협상결과저장</button> --></p>
					<div style="overflow-x: scroll; overflow-y:hidden" >
						<table class="tb"> 
				         	<colgroup>
								<col width="50px;">
								<col width="80px;">
								<col width="150px;">
								<col width="200px">
								<col width="100px;">
								<col width="100px;">
								<col width="100px;">
								<col width="150px;">
								<col width="150px;">
								<col width="150px;">
								<!-- 제안/규격서 , 평가결과, 기술점수, 가격점수는 협상, 2단계, 규격가격분리일경우 활성화 -->
											<!-- 	--33	낙찰방법코드	규격가격분리동시경쟁
													--34	낙찰방법코드	2단계경쟁
													--40	낙찰방법코드	협상에 의한 계약 -->
								<c:if test="${bidReqDetail.SBID_MTCD ne '33' && bidReqDetail.SBID_MTCD ne '34' }"><!-- 협상의의한계약일 경우만 -->
									<col width="150px;">
								</c:if>
								<col width="150px;">
								<col width="150px;"> 
								<col width="150px;">
							</colgroup>
							<thead> 
				                <tr>
									<th scope="col">순번</th>
									<th scope="col">순위</th> 
									<th scope="col">사업자번호</th> 
									<th scope="col">업체명</th>
									<th scope="col">제안서/규격서</th>
									<th scope="col">평가결과</th>
									<th scope="col">산출내역서</th>
									<th scope="col">투찰금액</th>
									<th scope="col">기술점수</th>
									<th scope="col">가격점수</th>
									<c:if test="${bidReqDetail.SBID_MTCD ne '33' && bidReqDetail.SBID_MTCD ne '34' }"><!-- 협상의의한계약일 경우만 -->
										<th scope="col">협상결과</th>
									</c:if>
									<th scope="col">주소</th>
									<th scope="col">전화번호</th>
									<th scope="col">이메일</th>
				                </tr>
				           		</thead>
				           		<tbody id="vendShowTbdy">
									<c:if test="${vendListTotCnt > 0}">
										<c:forEach var="data" items="${vendList}" varStatus="status" >
											<tr>
												<td> ${status.count}
													<input type="hidden" id="P_VEND_REG_NO" name="P_VEND_REG_NO" placeholder="거래처번호" value="${data.VEND_REG_NO}" /><!-- 사업자번호랑 동일한 애가 들어감  -->
												</td>
												<td>
													<input type="text" id="P_OPNG_RNK" name="P_OPNG_RNK"  class="input w100p" placeholder="순위" value="${data.OPNG_RNK}"/>
												</td>
												<td> 
													<input type="text" id="P_BIZRNO" name="P_BIZRNO" class="input w100p" placeholder="사업자번호" value="${comFn:formatBizNumber(data.BIZRNO)}" style="background: #f0f0f0;"  readonly="readonly"/>
												</td>
												<td>
													<input type="text" id="P_VEND_NM" name="P_VEND_NM" class="input w60p" placeholder="업체명" value="${data.VEND_NM}" style="background: #f0f0f0;"  readonly="readonly"/>
													<!-- <button type="button" class="btn ty03" id="vendSch" name="vendSch" />검색</button> -->
												</td>
												<td>
													<c:if test="${empty data.FILE_GRP_NO_DO02}">
													<input type="hidden" name="SAVEYN" value="N" />
														<button type="button"  name="DO02" class="btn ty04" onClick="javascript:filePopup('${data.VEND_REG_NO}','DO02');">미등록</button>
													</c:if>
													<c:if test="${not empty data.FILE_GRP_NO_DO02}"> 
													<input type="hidden" name="SAVEYN" value="Y" />
														<button type="button" class="btn ty04" onClick="javascript:filePopup('${data.VEND_REG_NO}','DO02','view');">보기</button>
													</c:if>
												</td>
												<td>
													<c:if test="${empty data.ESTM_ELCD}">
													<input type="hidden" name="SAVEYN" value="N" />
														<button type="button"  name="DO01" class="btn ty04" onClick="javascript:tchnEstmPopup('${data.VEND_REG_NO}','DO01');">미등록</button>
													</c:if>
													<c:if test="${not empty data.ESTM_ELCD}">
													<input type="hidden" name="SAVEYN" value="Y" />
														<button type="button" class="btn ty04" onClick="javascript:tchnEstmPopup('${data.VEND_REG_NO}','DO01','view');">${comFn:nvl(data.ESTM_ELCD_NM,'미등록')}</button>
													</c:if>
												</td>
												<td>
													<c:if test="${empty data.FILE_GRP_NO_DO03}">
														<input type="hidden" name="clcSAVEYN" value="N" />
														<button type="button" name="DO03" class="btn ty04" onClick="javascript:clcCntnFilePopup('${data.VEND_REG_NO}','DO03');">미등록</button>
													</c:if>
													<c:if test="${not empty data.FILE_GRP_NO_DO03}">
														<input type="hidden" name="clcSAVEYN" value="Y" />
														<button type="button" class="btn ty04" onClick="javascript:clcCntnFilePopup('${data.VEND_REG_NO}','DO03','view');">보기</button>
													</c:if>
												</td>
												<td><input type="text" id="P_TNDR_AMT" name="P_TNDR_AMT" class="input rt w100p" placeholder="투찰금액" value="${comFn:formatMoneyDp(data.TNDR_AMT)}" money/></td>
												<td><input type="text" id="P_TCHN_ESTM_SCR" name="P_TCHN_ESTM_SCR" class="input rt w100p" placeholder="기술점수" value="${comFn:nvl(data.TCHN_ESTM_SCR,data.ESTM_SCR)}"/></td>  
												<td><input type="text" id="P_PRCE_SCR" name="P_PRCE_SCR" class="input rt w100p" placeholder="가격점수" value="${data.PRCE_SCR}"/></td>
												<c:if test="${bidReqDetail.SBID_MTCD ne '33' && bidReqDetail.SBID_MTCD ne '34' }"><!-- 협상의의한계약일 경우만 -->
													<td>
														<c:if test="${empty data.FILE_GRP_NO_DO04}">
														<input type="hidden" name="negoSAVEYN" value="N" />
															<button type="button" name="DO04" class="btn ty04" onClick="javascript:negoFilePopup('${data.VEND_REG_NO}','DO04');">미등록</button>
														</c:if>
														<c:if test="${not empty data.FILE_GRP_NO_DO04}">
														<input type="hidden" name="negoSAVEYN" value="Y" /> 
															<button type="button" class="btn ty04" onClick="javascript:negoFilePopup('${data.VEND_REG_NO}','DO04','view');">보기</button>
														</c:if>
													</td>
												</c:if>  
												<td><input type="text" id="P_CHRGR_NM" name="P_CHRGR_NM" class="input w100p" placeholder="주소" value="${data.CHRGR_NM}" /></td>
												<td><input type="text" id="P_TEL_NO" name="P_TEL_NO" class="input w100p" placeholder="전화번호" value="${data.TEL_NO}" /></td>
												<td><input type="text" id="P_EMAL_ADDR" name="P_EMAL_ADDR" class="input w100p" placeholder="이메일" value="${data.EMAL_ADDR}" /></td>
											</tr>
										</c:forEach>
									</c:if> 
								</tbody>
								<c:if test="${comFn:nvl(vendListTotCnt, 0) == 0}"> 
				             		<tr>
										<c:if test="${bidReqDetail.SBID_MTCD ne '33' && bidReqDetail.SBID_MTCD ne '34' }"><!-- 협상의의한계약일 경우만 -->
											<td colspan="14" style="text-align: center;">업체가 존재하지 않습니다.</td>
				             			</c:if>
				             			<c:if test="${bidReqDetail.SBID_MTCD eq '33' || bidReqDetail.SBID_MTCD eq '34' }"><!-- 협상의의한계약아닐 경우만 -->
											<td colspan="13" style="text-align: center;">업체가 존재하지 않습니다.</td>
										</c:if>
									</tr>
				             </c:if>
				             
						</table>
					</div> 
				</c:if>
				<c:if test="${loginResult.AUTH_ID eq '2' ||  loginResult.AUTH_ID eq '3'}">
					<c:if test="${loginResult.USR_ID eq bidReqDetail.RQR_CHRGR_ID}">
						<table class="form_tb">
							<colgroup>
								<col width="15%">
						        <col width="35%"> 
						        <col width="15%">
						        <col width="35%">
							</colgroup>
							<tbody>
								<tr> 
									<th scope="row">첨부파일(기술협상결과보고서)</th>
									<c:if test="${empty bidReqDetail.FILE_GRP_NO_TECH}">	
										<input type="hidden" id="techFileReg"  value="reg"/>
										<td class="txtl vtop" colspan="3">
											<input type="hidden" name="P_FILE_DOC_SECD" disabled="disabled" value="TECH" />
											<input type="file" onchange="fileSet(this);" style="width: 800px;" />
										</td>
									</c:if>
									<c:if test="${not empty bidReqDetail.FILE_GRP_NO_TECH}">
										<input type="hidden" id="techFileReg"  value="mod"/>	
										<c:forEach var="data" items="${bidTechFile}" varStatus="idx">
											<td class="txtl vtop" colspan="3">
												<span style="float:left;"><a href="javascript:download('${data.FILE_GRP_NO}', '${data.FILE_SN}');">${data.SYS_FILE_NM}&nbsp;</a></span>
												<span style="float:right;"><button type="button" class="btn" onclick="fileModBtn(this);">수정</button></span>
						                    </td>
						                    <td class="txtl vtop" style="display: none;" colspan="3"> 
												<input type="hidden" name="P_FILE_DOC_SECD" value="${data.BID_FSCD}" disabled="disabled" />
												<span style="float:left;"><input type="file" style="width: 800px;" onchange="fileSet(this);" /></span>
												<span style="float:right;"><button type="button" class="btn" onclick="fileCancleBtn(this);">취소</button></span>
						                    </td>	  
					                    </c:forEach>
									</c:if>
								</tr>
							</tbody>
						</table>	
						<p class="spc_stit">업체정보<!-- <button type="button" class="btn ty02" id="vendSaveBtn">협상결과저장</button> -->
						</p>
						<div style="overflow-x: scroll; overflow-y:hidden" >
							<table class="tb"> 
					         	<colgroup>
									<col width="50px;">
									<col width="80px;">
									<col width="150px;">
									<col width="200px">
									<col width="100px;">
									<col width="100px;">
									<col width="100px;">
									<col width="150px;">
									<col width="150px;">
									<col width="150px;">
									<!-- 제안/규격서 , 평가결과, 기술점수, 가격점수는 협상, 2단계, 규격가격분리일경우 활성화 -->
												<!-- 	--33	낙찰방법코드	규격가격분리동시경쟁
														--34	낙찰방법코드	2단계경쟁
														--40	낙찰방법코드	협상에 의한 계약 -->
									<c:if test="${bidReqDetail.SBID_MTCD ne '33' && bidReqDetail.SBID_MTCD ne '34' }"><!-- 협상의의한계약일 경우만 -->
										<col width="150px;">
									</c:if>
									<col width="150px;">
									<col width="150px;"> 
									<col width="150px;">
								</colgroup>
								<thead> 
					                <tr>
										<th scope="col">순번</th>
										<th scope="col">순위</th> 
										<th scope="col">사업자번호</th> 
										<th scope="col">업체명</th>
										<th scope="col">제안서/규격서</th>
										<th scope="col">평가결과</th>
										<th scope="col">산출내역서</th>
										<th scope="col">투찰금액</th>
										<th scope="col">기술점수</th>
										<th scope="col">가격점수</th>
										<c:if test="${bidReqDetail.SBID_MTCD ne '33' && bidReqDetail.SBID_MTCD ne '34' }"><!-- 협상의의한계약일 경우만 -->
											<th scope="col">협상결과</th>
										</c:if>
										<th scope="col">주소</th>
										<th scope="col">전화번호</th>
										<th scope="col">이메일</th>
					                </tr>
					           		</thead>
					           		<tbody id="vendShowTbdy">
										<c:if test="${vendListTotCnt > 0}">
											<c:forEach var="data" items="${vendList}" varStatus="status" >
												<tr>
													<td> ${status.count}
														<input type="hidden" id="P_VEND_REG_NO" name="P_VEND_REG_NO" placeholder="거래처번호" value="${data.VEND_REG_NO}" /><!-- 사업자번호랑 동일한 애가 들어감  -->
													</td>
													<td>
														<input type="text" id="P_OPNG_RNK" name="P_OPNG_RNK"  class="input w100p" placeholder="순위" value="${data.OPNG_RNK}"/>
													</td>
													<td> 
														<input type="text" id="P_BIZRNO" name="P_BIZRNO" class="input w100p" placeholder="사업자번호" value="${comFn:formatBizNumber(data.BIZRNO)}" style="background: #f0f0f0;"  readonly="readonly"/>
													</td>
													<td>
														<input type="text" id="P_VEND_NM" name="P_VEND_NM" class="input w60p" placeholder="업체명" value="${data.VEND_NM}" style="background: #f0f0f0;"  readonly="readonly"/>
														<!-- <button type="button" class="btn ty03" id="vendSch" name="vendSch" />검색</button> -->
													</td>
													<td>
														<c:if test="${empty data.FILE_GRP_NO_DO02}">
														<input type="hidden" name="SAVEYN" value="N" />
															<button type="button"  name="DO02" class="btn ty04" onClick="javascript:filePopup('${data.VEND_REG_NO}','DO02');">미등록</button>
														</c:if>
														<c:if test="${not empty data.FILE_GRP_NO_DO02}"> 
														<input type="hidden" name="SAVEYN" value="Y" />
															<button type="button" class="btn ty04" onClick="javascript:filePopup('${data.VEND_REG_NO}','DO02','view');">보기</button>
														</c:if>
													</td>
													<td>
														<c:if test="${empty data.ESTM_ELCD}">
														<input type="hidden" name="SAVEYN" value="N" />
															<button type="button"  name="DO01" class="btn ty04" onClick="javascript:tchnEstmPopup('${data.VEND_REG_NO}','DO01');">미등록</button>
														</c:if>
														<c:if test="${not empty data.ESTM_ELCD}">
														<input type="hidden" name="SAVEYN" value="Y" />
															<button type="button" class="btn ty04" onClick="javascript:tchnEstmPopup('${data.VEND_REG_NO}','DO01','view');">${comFn:nvl(data.ESTM_ELCD_NM,'미등록')}</button>
														</c:if>
													</td>
													<td>
														<c:if test="${empty data.FILE_GRP_NO_DO03}">
															<input type="hidden" name="clcSAVEYN" value="N" />
															<button type="button" name="DO03" class="btn ty04" onClick="javascript:clcCntnFilePopup('${data.VEND_REG_NO}','DO03');">미등록</button>
														</c:if>
														<c:if test="${not empty data.FILE_GRP_NO_DO03}">
															<input type="hidden" name="clcSAVEYN" value="Y" />
															<button type="button" class="btn ty04" onClick="javascript:clcCntnFilePopup('${data.VEND_REG_NO}','DO03','view');">보기</button>
														</c:if>
													</td>
													<td><input type="text" id="P_TNDR_AMT" name="P_TNDR_AMT" class="input rt w100p" placeholder="투찰금액" value="${comFn:formatMoneyDp(data.TNDR_AMT)}" money/></td>
													<td><input type="text" id="P_TCHN_ESTM_SCR" name="P_TCHN_ESTM_SCR" class="input rt w100p" placeholder="기술점수" value="${comFn:nvl(data.TCHN_ESTM_SCR,data.ESTM_SCR)}"/></td>  
													<td><input type="text" id="P_PRCE_SCR" name="P_PRCE_SCR" class="input rt w100p" placeholder="가격점수" value="${data.PRCE_SCR}"/></td>
													<c:if test="${bidReqDetail.SBID_MTCD ne '33' && bidReqDetail.SBID_MTCD ne '34' }"><!-- 협상의의한계약일 경우만 -->
														<td>
															<c:if test="${empty data.FILE_GRP_NO_DO04}">
															<input type="hidden" name="negoSAVEYN" value="N" />
																<button type="button" name="DO04" class="btn ty04" onClick="javascript:negoFilePopup('${data.VEND_REG_NO}','DO04');">미등록</button>
															</c:if>
															<c:if test="${not empty data.FILE_GRP_NO_DO04}">
															<input type="hidden" name="negoSAVEYN" value="Y" /> 
																<button type="button" class="btn ty04" onClick="javascript:negoFilePopup('${data.VEND_REG_NO}','DO04','view');">보기</button>
															</c:if>
														</td>
													</c:if>  
													<td><input type="text" id="P_CHRGR_NM" name="P_CHRGR_NM" class="input w100p" placeholder="주소" value="${data.CHRGR_NM}" /></td>
													<td><input type="text" id="P_TEL_NO" name="P_TEL_NO" class="input w100p" placeholder="전화번호" value="${data.TEL_NO}" /></td>
													<td><input type="text" id="P_EMAL_ADDR" name="P_EMAL_ADDR" class="input w100p" placeholder="이메일" value="${data.EMAL_ADDR}" /></td>
												</tr>
											</c:forEach>
										</c:if> 
									</tbody>
									<c:if test="${comFn:nvl(vendListTotCnt, 0) == 0}"> 
					             		<tr>
											<c:if test="${bidReqDetail.SBID_MTCD ne '33' && bidReqDetail.SBID_MTCD ne '34' }"><!-- 협상의의한계약일 경우만 -->
												<td colspan="14" style="text-align: center;">업체가 존재하지 않습니다.</td>
					             			</c:if>
					             			<c:if test="${bidReqDetail.SBID_MTCD eq '33' || bidReqDetail.SBID_MTCD eq '34' }"><!-- 협상의의한계약아닐 경우만 -->
												<td colspan="13" style="text-align: center;">업체가 존재하지 않습니다.</td>
											</c:if>
										</tr>
					             </c:if>
							</table>
						</div> 
					</c:if>
					<c:if test="${loginResult.USR_ID ne bidReqDetail.RQR_CHRGR_ID}">
						<table class="form_tb">
							<colgroup>
								<col width="15%">
						        <col width="35%">
						        <col width="15%">
						        <col width="35%">
							</colgroup>
							<tbody>
								<%-- <tr>
									<th scope="row">예정가격</th>
									<td>${comFn:formatMoneyDp(bidReqDetail.ESTPC_AMT)}</td>
									<th></th>
									<td></td>
								</tr>
								<tr>  --%>
									<th scope="row">첨부파일(기술협상결과보고서)</th>
									<c:if test="${empty bidReqDetail.FILE_GRP_NO_TECH}">
										<td class="txtl vtop" colspan="3" style="text-align: center;">개찰결과보고서가 존재하지 않습니다.</td>
									</c:if>
									<c:if test="${not empty bidReqDetail.FILE_GRP_NO_TECH}">
										<c:forEach var="data" items="${bidTechFile}" varStatus="idx">
											<td class="txtl vtop" colspan="3">
												<span style="float:left;"><a href="javascript:download('${data.FILE_GRP_NO}', '${data.FILE_SN}');">${data.SYS_FILE_NM}&nbsp;</a></span>
						                    </td>
					                    </c:forEach>
									</c:if>
								</tr>
							</tbody>
						</table>
						<p class="spc_stit">업체정보<!-- <button type="button" class="btn ty02" id="vendSaveBtn">협상결과저장</button> -->
						</p>
						<div style="overflow-x: scroll; overflow-y:hidden" >
							<table class="tb"> 
					         	<colgroup>
									<col width="50px;">
									<col width="80px;">
									<col width="150px;">
									<col width="200px">
									<col width="100px;">
									<col width="100px;">
									<col width="100px;">
									<col width="150px;">
									<col width="150px;">
									<col width="150px;">
									<c:if test="${bidReqDetail.SBID_MTCD ne '33' && bidReqDetail.SBID_MTCD ne '34' }"><!-- 협상의의한계약일 경우만 -->
										<col width="150px;">
									</c:if>
									<col width="150px;">
									<col width="150px;"> 
									<col width="150px;">
								</colgroup>
								<thead> 
					                <tr>
										<th scope="col">순번</th>
										<th scope="col">순위</th> 
										<th scope="col">사업자번호</th> 
										<th scope="col">업체명</th>
										<th scope="col">제안서/규격서</th>
										<th scope="col">평가결과</th>
										<th scope="col">산출내역서</th>
										<th scope="col">투찰금액</th>
										<th scope="col">기술점수</th>
										<th scope="col">가격점수</th>
										<c:if test="${bidReqDetail.SBID_MTCD ne '33' && bidReqDetail.SBID_MTCD ne '34' }"><!-- 협상의의한계약일 경우만 -->
											<th scope="col">협상결과</th>
										</c:if>
										<th scope="col">주소</th>
										<th scope="col">전화번호</th>
										<th scope="col">이메일</th>
					                </tr>
					           		</thead>
									<c:if test="${vendListTotCnt > 0}">
										<c:forEach var="data" items="${vendList}" varStatus="status" >
											<tr>
												<td> ${status.count}
													<input type="hidden" id="P_VEND_REG_NO" name="P_VEND_REG_NO" placeholder="거래처번호" value="${data.VEND_REG_NO}" /><!-- 사업자번호랑 동일한 애가 들어감  -->
												</td>
												<td>
													<input type="text" id="P_OPNG_RNK" name="P_OPNG_RNK"  class="input w100p" placeholder="순위" value="${data.OPNG_RNK}"/>
												</td>
												<td> 
													<input type="text" id="P_BIZRNO" name="P_BIZRNO" class="input w100p" placeholder="사업자번호" value="${comFn:formatBizNumber(data.BIZRNO)}" style="background: #f0f0f0;"  readonly="readonly"/>
												</td>
												<td>
													<input type="text" id="P_VEND_NM" name="P_VEND_NM" class="input w60p" placeholder="업체명" value="${data.VEND_NM}" style="background: #f0f0f0;"  readonly="readonly"/>
													<!-- <button type="button" class="btn ty03" id="vendSch" name="vendSch" />검색</button> -->
												</td>
												<td>
													<button type="button" class="btn ty04" onClick="javascript:filePopup('${data.VEND_REG_NO}','DO02','view');">보기</button>
												</td>
												<td>
													<button type="button" class="btn ty04" onClick="javascript:tchnEstmPopup('${data.VEND_REG_NO}','DO01','view');">${comFn:nvl(data.ESTM_ELCD_NM,'미등록')}</button>
												</td>
												<td>
													<button type="button" class="btn ty04" onClick="javascript:clcCntnFilePopup('${data.VEND_REG_NO}','DO03','view');">보기</button>
												</td>
												<td><input type="text" id="P_TNDR_AMT" name="P_TNDR_AMT" class="input rt w100p" placeholder="투찰금액" value="${comFn:formatMoneyDp(data.TNDR_AMT)}" money/></td>
												<td><input type="text" id="P_TCHN_ESTM_SCR" name="P_TCHN_ESTM_SCR" class="input rt w100p" placeholder="기술점수" value="${comFn:nvl(data.TCHN_ESTM_SCR,data.ESTM_SCR)}"/></td>  
												<td><input type="text" id="P_PRCE_SCR" name="P_PRCE_SCR" class="input rt w100p" placeholder="가격점수" value="${data.PRCE_SCR}"/></td>
												
												<c:if test="${bidReqDetail.SBID_MTCD ne '33' && bidReqDetail.SBID_MTCD ne '34' }"><!-- 협상의의한계약일 경우만 -->
													<td>
														<button type="button" class="btn ty04" onClick="javascript:negoFilePopup('${data.VEND_REG_NO}','DO04','view');">보기</button>
													</td>
												</c:if>  
												<td><input type="text" id="P_CHRGR_NM" name="P_CHRGR_NM" class="input w100p" placeholder="주소" value="${data.CHRGR_NM}" /></td>
												<td><input type="text" id="P_TEL_NO" name="P_TEL_NO" class="input w100p" placeholder="전화번호"/></td>
												<td><input type="text" id="P_EMAL_ADDR" name="P_EMAL_ADDR" class="input w100p" placeholder="이메일" value="${data.EMAL_ADDR}"/></td>
											</tr>
										</c:forEach>
									</c:if> 
									<c:if test="${comFn:nvl(vendListTotCnt, 0) == 0}"> 
					             		<tr>
											<c:if test="${bidReqDetail.SBID_MTCD ne '33' && bidReqDetail.SBID_MTCD ne '34' }"><!-- 협상의의한계약일 경우만 -->
												<td colspan="14" style="text-align: center;">업체가 존재하지 않습니다.</td>
					             			</c:if>
					             			<c:if test="${bidReqDetail.SBID_MTCD eq '33' || bidReqDetail.SBID_MTCD eq '34' }"><!-- 협상의의한계약아닐 경우만 -->
												<td colspan="13" style="text-align: center;">업체가 존재하지 않습니다.</td>
											</c:if>
										</tr>
					             </c:if>
					              
							</table> 
						</div>
					</c:if>
				</c:if>
			</c:if>
			<c:if test="${bidReqDetail.BID_PSCD ne 'A019'}"><!-- 개찰 승인이 아닌경우 정보확인만 -->
				<table class="form_tb">
					<colgroup>
						<col width="15%">
				        <col width="35%">
				        <col width="15%">
				        <col width="35%">
					</colgroup>
					<tbody>
							<th scope="row">첨부파일(기술협상결과보고서)</th>
							<c:if test="${empty bidReqDetail.FILE_GRP_NO_TECH}">
								<td class="txtl vtop" colspan="3" style="text-align: center;">기술협상결과보고서가 존재하지 않습니다.</td>
							</c:if>
							<c:if test="${not empty bidReqDetail.FILE_GRP_NO_TECH}">
								<c:forEach var="data" items="${bidTechFile}" varStatus="idx">
									<td class="txtl vtop" colspan="3">
										<span style="float:left;"><a href="javascript:download('${data.FILE_GRP_NO}', '${data.FILE_SN}');">${data.SYS_FILE_NM}&nbsp;</a></span>
				                    </td>
			                    </c:forEach>
							</c:if>
						</tr>
					</tbody>
				</table>			
				<p class="spc_stit">업체정보<!-- <button type="button" class="btn ty02" id="vendSaveBtn">협상결과저장</button> -->
				</p>
				<div style="overflow-x: scroll; overflow-y:hidden" >
					<table class="tb"> 
			         	<colgroup>
							<col width="50px;">
							<col width="80px;">
							<col width="150px;">
							<col width="200px">
							<col width="100px;">
							<col width="100px;">
							<col width="100px;">
							<col width="150px;">
							<col width="150px;">
							<col width="150px;">
							<c:if test="${bidReqDetail.SBID_MTCD ne '33' && bidReqDetail.SBID_MTCD ne '34' }"><!-- 협상의의한계약일 경우만 -->
								<col width="150px;">
							</c:if>
							<col width="150px;">
							<col width="150px;"> 
							<col width="150px;">
						</colgroup>
						<thead> 
			                <tr>
								<th scope="col">순번</th>
								<th scope="col">순위</th> 
								<th scope="col">사업자번호</th> 
								<th scope="col">업체명</th>
								<th scope="col">제안서/규격서</th>
								<th scope="col">평가결과</th>
								<th scope="col">산출내역서</th>
								<th scope="col">투찰금액</th>
								<th scope="col">기술점수</th>
								<th scope="col">가격점수</th>
								<c:if test="${bidReqDetail.SBID_MTCD ne '33' && bidReqDetail.SBID_MTCD ne '34' }"><!-- 협상의의한계약일 경우만 -->
									<th scope="col">협상결과</th>
								</c:if>
								<th scope="col">주소</th>
								<th scope="col">전화번호</th>
								<th scope="col">이메일</th>
			                </tr>
			           		</thead>
							<c:if test="${vendListTotCnt > 0}">
								<c:forEach var="data" items="${vendList}" varStatus="status" >
									<tr>
										<td> ${status.count}
											<input type="hidden" id="P_VEND_REG_NO" name="P_VEND_REG_NO" placeholder="거래처번호" value="${data.VEND_REG_NO}" /><!-- 사업자번호랑 동일한 애가 들어감  -->
										</td>
										<td>
											<input type="text" id="P_OPNG_RNK" name="P_OPNG_RNK"  class="input w100p" placeholder="순위" value="${data.OPNG_RNK}"/>
										</td>
										<td> 
											<input type="text" id="P_BIZRNO" name="P_BIZRNO" class="input w100p" placeholder="사업자번호" value="${comFn:formatBizNumber(data.BIZRNO)}" style="background: #f0f0f0;"  readonly="readonly"/>
										</td>
										<td>
											<input type="text" id="P_VEND_NM" name="P_VEND_NM" class="input w60p" placeholder="업체명" value="${data.VEND_NM}" style="background: #f0f0f0;"  readonly="readonly"/>
											<!-- <button type="button" class="btn ty03" id="vendSch" name="vendSch" />검색</button> -->
										</td>
										<td>
											<button type="button" class="btn ty04" onClick="javascript:filePopup('${data.VEND_REG_NO}','DO02','view');">보기</button>
										</td>
										<td>
											<button type="button" class="btn ty04" onClick="javascript:tchnEstmPopup('${data.VEND_REG_NO}','DO01','view');">${comFn:nvl(data.ESTM_ELCD_NM,'미등록')}</button>
										</td>
										<td>
											<button type="button" class="btn ty04" onClick="javascript:clcCntnFilePopup('${data.VEND_REG_NO}','DO03','view');">보기</button>
										</td>
										<td><input type="text" id="P_TNDR_AMT" name="P_TNDR_AMT" class="input rt w100p" placeholder="투찰금액" value="${comFn:formatMoneyDp(data.TNDR_AMT)}" money/></td>
										<td><input type="text" id="P_TCHN_ESTM_SCR" name="P_TCHN_ESTM_SCR" class="input rt w100p" placeholder="기술점수" value="${comFn:nvl(data.TCHN_ESTM_SCR,data.ESTM_SCR)}"/></td>  
										<td><input type="text" id="P_PRCE_SCR" name="P_PRCE_SCR" class="input rt w100p" placeholder="가격점수" value="${data.PRCE_SCR}"/></td>
										
										<c:if test="${bidReqDetail.SBID_MTCD ne '33' && bidReqDetail.SBID_MTCD ne '34' }"><!-- 협상의의한계약일 경우만 -->
											<td>
												<button type="button" class="btn ty04" onClick="javascript:negoFilePopup('${data.VEND_REG_NO}','DO04','view');">보기</button>
											</td>
										</c:if>  
										<td><input type="text" id="P_CHRGR_NM" name="P_CHRGR_NM" class="input w100p" placeholder="주소" value="${data.CHRGR_NM}" /></td>
										<td><input type="text" id="P_TEL_NO" name="P_TEL_NO" class="input w100p" placeholder="전화번호"/></td>
										<td><input type="text" id="P_EMAL_ADDR" name="P_EMAL_ADDR" class="input w100p" placeholder="이메일" value="${data.EMAL_ADDR}"/></td>
									</tr>
								</c:forEach>
							</c:if> 
							<c:if test="${comFn:nvl(vendListTotCnt, 0) == 0}"> 
			             		<tr>
									<c:if test="${bidReqDetail.SBID_MTCD ne '33' && bidReqDetail.SBID_MTCD ne '34' }"><!-- 협상의의한계약일 경우만 -->
										<td colspan="14" style="text-align: center;">업체가 존재하지 않습니다.</td>
			             			</c:if>
			             			<c:if test="${bidReqDetail.SBID_MTCD eq '33' || bidReqDetail.SBID_MTCD eq '34' }"><!-- 협상의의한계약아닐 경우만 -->
										<td colspan="13" style="text-align: center;">업체가 존재하지 않습니다.</td>
									</c:if>
								</tr>
			             </c:if>
			              
					</table> 
				</div>
			</c:if>
			<%-- <c:if test="${loginResult.DEPT_NO eq bidReqDetail.PRCH_DEPT_NO && loginResult.AUTH_ID ne '1'}"><!-- 경영지원팀에서는  정보 확인만 --> --%>
			<%-- <c:if test="${loginResult.AUTH_ID eq '4' || loginResult.AUTH_ID eq '5' }">
				<p class="spc_stit">업체정보<!-- <button type="button" class="btn ty02" id="vendSaveBtn">협상결과저장</button> -->
				</p>
				<div style="overflow-x: scroll; overflow-y:hidden" >
					<table class="tb"> 
			         	<colgroup>
							<col width="50px;">
							<col width="80px;"> 
							<col width="150px;">
							<col width="200px">
							<col width="100px;">
							<col width="100px;">
							<col width="100px;">
							<col width="150px;">
							<col width="150px;">
							<col width="150px;">
							<c:if test="${bidReqDetail.SBID_MTCD ne '33' && bidReqDetail.SBID_MTCD ne '34' }"><!-- 협상의의한계약일 경우만 -->
								<col width="150px;">
							</c:if>
							<col width="150px;">
							<col width="150px;"> 
							<col width="150px;">
						</colgroup>
						<thead> 
			                <tr>
								<th scope="col">순번</th>
								<th scope="col">순위</th> 
								<th scope="col">사업자번호</th> 
								<th scope="col">업체명</th>
								<th scope="col">제안서/규격서</th>
								<th scope="col">평가결과</th>
								<th scope="col">산출내역서</th>
								<th scope="col">투찰금액</th>
								<th scope="col">기술점수</th>
								<th scope="col">가격점수</th>
								<c:if test="${bidReqDetail.SBID_MTCD ne '33' && bidReqDetail.SBID_MTCD ne '34' }"><!-- 협상의의한계약일 경우만 -->
									<th scope="col">협상결과</th>
								</c:if>
								<th scope="col">주소</th>
								<th scope="col">전화번호</th>
								<th scope="col">이메일</th>
			                </tr>
			           		</thead>
							<c:if test="${vendListTotCnt > 0}">
								<c:forEach var="data" items="${vendList}" varStatus="status" >
									<tr>
										<td> ${status.count}
											<input type="hidden" id="P_VEND_REG_NO" name="P_VEND_REG_NO" placeholder="거래처번호" value="${data.VEND_REG_NO}" /><!-- 사업자번호랑 동일한 애가 들어감  -->
										</td>
										<td>
											<input type="text" id="P_OPNG_RNK" name="P_OPNG_RNK"  class="input w100p" placeholder="순위" value="${data.OPNG_RNK}"/>
										</td>
										<td> 
											<input type="text" id="P_BIZRNO" name="P_BIZRNO" class="input w100p" placeholder="사업자번호" value="${comFn:formatBizNumber(formatMoneyDpdata.BIZRNO)}" style="background: #f0f0f0;"  readonly="readonly"/>
										</td>
										<td>
											<input type="text" id="P_VEND_NM" name="P_VEND_NM" class="input w60p" placeholder="업체명" value="${data.VEND_NM}" style="background: #f0f0f0;"  readonly="readonly"/>
											<!-- <button type="button" class="btn ty03" id="vendSch" name="vendSch" />검색</button> -->
										</td>
										<td>
											<button type="button" class="btn ty04" onClick="javascript:filePopup('${data.VEND_REG_NO}','DO02','view');">보기</button>
										</td>
										<td>
											<button type="button" class="btn ty04" onClick="javascript:tchnEstmPopup('${data.VEND_REG_NO}','DO01','view');">${comFn:nvl(data.ESTM_ELCD_NM,'미등록')}</button>
										</td>
										<td>
											<button type="button" class="btn ty04" onClick="javascript:clcCntnFilePopup('${data.VEND_REG_NO}','DO03','view');">보기</button>
										</td>
										<td><input type="text" id="P_TNDR_AMT" name="P_TNDR_AMT" class="input rt w100p" placeholder="투찰금액" value="${comFn:formatMoneyDp(data.TNDR_AMT)}" money/></td>
										<td><input type="text" id="P_TCHN_ESTM_SCR" name="P_TCHN_ESTM_SCR" class="input rt w100p" placeholder="기술점수" value="${comFn:nvl(data.TCHN_ESTM_SCR,data.ESTM_SCR)}"/></td>  
										<td><input type="text" id="P_PRCE_SCR" name="P_PRCE_SCR" class="input rt w100p" placeholder="가격점수" value="${data.PRCE_SCR}"/></td>
										<c:if test="${bidReqDetail.SBID_MTCD ne '33' && bidReqDetail.SBID_MTCD ne '34' }"><!-- 협상의의한계약일 경우만 -->
											<td>
												<button type="button" class="btn ty04" onClick="javascript:negoFilePopup('${data.VEND_REG_NO}','DO04','view');">보기</button>
											</td>
										</c:if>  
										<td><input type="text" id="P_CHRGR_NM" name="P_CHRGR_NM" class="input w100p" placeholder="주소" value="${data.CHRGR_NM}" /></td>
										<td><input type="text" id="P_TEL_NO" name="P_TEL_NO" class="input w100p" placeholder="전화번호" value="${data.TEL_NO}" /></td>
										<td><input type="text" id="P_EMAL_ADDR" name="P_EMAL_ADDR" class="input w100p" placeholder="이메일" value="${data.EMAL_ADDR}" /></td>
									</tr>
								</c:forEach>
							</c:if> 
							<c:if test="${comFn:nvl(vendListTotCnt, 0) == 0}"> 
			             		<tr>
									<c:if test="${bidReqDetail.SBID_MTCD ne '33' && bidReqDetail.SBID_MTCD ne '34' }"><!-- 협상의의한계약일 경우만 -->
										<td colspan="14" style="text-align: center;">업체가 존재하지 않습니다.</td>
			             			</c:if>
			             			<c:if test="${bidReqDetail.SBID_MTCD eq '33' || bidReqDetail.SBID_MTCD eq '34' }"><!-- 협상의의한계약아닐 경우만 -->
										<td colspan="13" style="text-align: center;">업체가 존재하지 않습니다.</td>
									</c:if>
								</tr>
			             </c:if>
			              
					</table>
				</div>
			</c:if> --%>
			<div class="btm_btns"> 
				<%-- <c:if test="${loginResult.DEPT_NO eq bidReqDetail.RQR_DEPT_NO || loginResult.AUTH_ID eq '1'}"><!-- 사업부서 또는 관리자 --> --%>
				<c:if test="${loginResult.AUTH_ID eq '1' || loginResult.AUTH_ID eq '4' || loginResult.AUTH_ID eq '5'}"> 
					<c:if test="${bidReqDetail.BID_PSCD eq 'A019'}"><!-- 개찰승인일때 -->
						<button type="button" class="btn ty02" id="vendSaveBtn">저장</button><!-- 협상결과저장 -->
					</c:if>
				</c:if>
				<c:if test="${loginResult.AUTH_ID eq '2' ||  loginResult.AUTH_ID eq '3'}"><!-- 사업부서 -->
					<c:if test="${loginResult.USR_ID eq bidReqDetail.RQR_CHRGR_ID}">
						<c:if test="${bidReqDetail.BID_PSCD eq 'A019'}"><!-- 개찰승인일때 -->
							<button type="button" class="btn ty02" id="vendSaveBtn">저장</button><!-- 협상결과저장 -->
						</c:if>
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
<form id="updtFrm" method="POST" > 
	<input type="hidden" name="resourceName" value="${comFn:nvl(param.resourceName, resourceName)}" >
	<input type="hidden" name="P_ANNC_NO" value="${bidReqDetail.ANNC_NO}">
	<input type="hidden" name="P_ANNC_NGR" value="${bidReqDetail.ANNC_NGR}">
	<input type="hidden" name="P_ROUND_NO"  value="${bidReqDetail.ROUND_NO}">
	<input type="hidden" name="P_BID_PSCD" value="">
	<input type="hidden" name="P_HIST_RMK" value=""> 
</form>

<!-- POPUP FORM -->
<form id="popupFrm" method="POST">
	<input type="hidden" name="resourceName" value="${comFn:nvl(param.resourceName, resourceName)}">
	<input type="hidden" name="P_ANNC_NO"  value="${bidReqDetail.ANNC_NO}"> 
	<input type="hidden" name="P_ANNC_NGR"  value="${bidReqDetail.ANNC_NGR}">
	<input type="hidden" name="P_ROUND_NO"  value="${bidReqDetail.ROUND_NO}">
</form>
<form id="downloadFrm" method="POST">
	<input type="hidden" name="P_FILE_GRP_NO"  id="P_FILE_GRP_NO">
	<input type="hidden" name="P_FILE_SN"  id="P_FILE_SN">
</form>

<form id="vendPopupFrm" method="POST">
	<input type="hidden" name="resourceName" value="${comFn:nvl(param.resourceName, resourceName)}">
	<input type="hidden" name="setMulti" value="N">
</form>

<!-- 업체정보 등록 -->
<%-- <form id="registFrm" method="POST">
	<input type="hidden" name="resourceName" value="${comFn:nvl(param.resourceName, resourceName)}">
	<input type="hidden" name="P_ANNC_NO"  value="${bidReqDetail.ANNC_NO}"> 
	<input type="hidden" name="P_ANNC_NGR"  value="${bidReqDetail.ANNC_NGR}">
	<input type="hidden" name="P_ROUND_NO"  value="${bidReqDetail.ROUND_NO}">
	<input type="hidden" name="P_VEND_REG_NO" >
	<input type="hidden" name="P_BIZRNO" >
	<input type="hidden" name="P_VEND_NM" >
	<input type="hidden" name="P_RPRS_NM" >
	<input type="hidden" name="P_CHRGR_NM" >
	<input type="hidden" name="P_TEL_NO" >
	<input type="hidden" name="P_EMAL" >
</form>
<!-- 업체정보 삭제 -->
<form id="deleteFrm" method="POST" > 
	<input type="hidden" name="resourceName" value="${comFn:nvl(param.resourceName, resourceName)}"> 
	<input type="hidden" name="P_ANNC_NO"  value="${bidReqDetail.ANNC_NO}"> 
	<input type="hidden" name="P_ANNC_NGR"  value="${bidReqDetail.ANNC_NGR}">
	<input type="hidden" name="P_ROUND_NO"  value="${bidReqDetail.ROUND_NO}">
	<input type="hidden" name="P_VEND_REG_NO">
</form> --%>

<!-- 평가결과 등록 POPUP FORM -->
<form id="tchnEstmFrm" method="POST">
	<input type="hidden" name="resourceName" value="${comFn:nvl(param.resourceName, resourceName)}">
	<input type="hidden" name="P_ANNC_NO"  value="${bidReqDetail.ANNC_NO}"> 
	<input type="hidden" name="P_ANNC_NGR"  value="${bidReqDetail.ANNC_NGR}">
	<input type="hidden" name="P_ROUND_NO"  value="${bidReqDetail.ROUND_NO}">
	<input type="hidden" name="P_BID_SBMT_FSCD"  value="">
	<input type="hidden" name="P_VEND_REG_NO">
	<input type="hidden" name="P_GBN">
</form>