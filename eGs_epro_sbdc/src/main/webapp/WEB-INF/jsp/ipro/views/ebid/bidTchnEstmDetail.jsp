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
<script type="text/javascript" src="${jsPath}/ipro/views/ebid/bidTchnEstmDetail.js"></script>

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

		<input type="hidden" name="P_VEND_SN" value="">
		
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
				<a class="on" id="2" name="tab" href='javascript:tabEvent(2);'>기술평가</a>
				<a id="3" name="tab" href='javascript:tabEvent(3);'>개찰</a>
				<a id="4" name="tab" href='javascript:tabEvent(4);'>기술협상</a>
				<a id="5" name="tab" href='javascript:tabEvent(5);'>낙찰</a>
			</div>
<%-- 			<c:if test="${loginResult.DEPT_NO eq bidReqDetail.RQR_DEPT_NO || loginResult.AUTH_ID eq '1'}"><!-- 사업부서에서 본인들이 평가  또는 관리자 -->
				<c:if test="${bidReqDetail.BID_PSCD ne 'A009' 
						&&  bidReqDetail.BID_PSCD ne 'A010' 
						&& bidReqDetail.BID_PSCD ne 'A011' 
						&& bidReqDetail.BID_PSCD ne 'A013'
						&& bidReqDetail.BID_PSCD ne 'A014'
						&& bidReqDetail.BID_PSCD ne 'A015'
						
						}">
					<p class="spc_stit"><font color="red">기술평가 가능한 상태가 아닙니다.</font> </p>
				</c:if>
			</c:if> --%>
			
			<%-- <c:if test="${loginResult.DEPT_NO eq bidReqDetail.RQR_DEPT_NO || loginResult.AUTH_ID eq '1'}"><!-- 사업부서에서 본인들이 평가  또는 관리자 --> --%>
				<c:if test="${bidReqDetail.BID_PSCD eq 'A008'|| bidReqDetail.BID_PSCD eq 'A012' || bidReqDetail.BID_PSCD eq 'A016'}">
					<table class="form_tb">
						<colgroup>
							<col width="15%">
					        <col width="35%">
					        <col width="15%">
					        <col width="35%">
						</colgroup>
						<tbody>
							<tr>
								<th scope="row">결재상태</th>
								<td>${bidReqDetail.APPR_STCD_NM }</td>
							</tr>
						</tbody>  
					</table>
					<!-- A008	입찰진행상태코드	입찰공고
					A012	입찰진행상태코드	기술평가내부반려
					A016	입찰진행상태코드	기술평가경영지원팀반려 -->
					<p class="spc_stit">업체정보
						<c:if test="${loginResult.AUTH_ID eq '2' || loginResult.AUTH_ID eq '3'}">
						 	<c:if test="${sessionScope.loginResult.USR_ID eq bidReqDetail.RQR_CHRGR_ID }"><!-- 작성자만  -->
								<span style="float: right;"><button type="button" class="btn ty02" id="vendSaveBtn" >업체정보저장</button></span>
								<button type="button" class="btn" id="vendDelBtn">삭제</button>
								<button type="button" class="btn" id="vendAddBtn">추가</button>
								<button type="button" class="btn" id="offVendAddBtn">수기업체추가</button>
								<button type="button" class="btn ty02" id="fileSampleBtn">심사평가서류샘플</button>
							</c:if>
						</c:if>
						<c:if test="${loginResult.AUTH_ID eq '1' || loginResult.AUTH_ID eq '4' || loginResult.AUTH_ID eq '5'}"> 
							<span style="float: right;"><button type="button" class="btn ty02" id="vendSaveBtn" >업체정보저장</button></span>
							<button type="button" class="btn" id="vendDelBtn">삭제</button>
							<button type="button" class="btn" id="vendAddBtn">추가</button>
							<button type="button" class="btn" id="offVendAddBtn">수기업체추가</button>
							<button type="button" class="btn ty02" id="fileSampleBtn">심사평가서류샘플</button>
						</c:if>
					</p>
					<table class="tb">
			         	<colgroup>
							<col width="50px;">
							<col width="210px;">
							<col width="200px">
							<col width="100px;">
							<col width="100px;">
							<col width="100px;">
							<col width="100px;">
							<col width="100px;">
						</colgroup>
						<thead>
			                <tr>
								<th scope="col">
									<input type="checkbox" id="vendCbx_ALL" onclick="FwkCmmnUtil.setAllCheck('vendCbx_ALL','vendCbx');" />
								</th>
								<th scope="col">사업자번호</th> 
								<th scope="col">업체명</th>
								<th scope="col">제안서/규격서</th>
								<th scope="col">평가결과</th>
								<th scope="col">주소</th>
								<th scope="col">전화번호</th>
								<th scope="col">이메일</th>
			                </tr>
			            </thead>
			            <tbody id="vendHideTbdy" style="display: none;">
			            	<tr> 
			            		<td> 
									<input type="checkbox" id="vendCbx" name="vendCbx"/> 
									<input type="hidden" id="P_VEND_REG_NO" placeholder="거래처번호" /><!-- 사업자번호랑 동일한 애가 들어감  -->
								</td>
								<td>
									<input type="text" id="P_BIZRNO"  class="input w100p" placeholder="사업자번호" style="background: #f0f0f0;"  readonly="readonly" />
								</td>
								<td>
									<input type="text" id="P_VEND_NM" class="input w60p" placeholder="업체명" style="background: #f0f0f0;"  readonly="readonly"/> 
									<button type="button" class="btn ty03" id="vendSch" name="vendSch" />검색</button>
								</td>
								<td><input type="hidden" value="N" name="btnYn"/></td> 
								<td><input type="hidden" value="N" name="btnYn"/></td>
								<td><input type="text" id="P_CHRGR_NM" class="input w100p" placeholder="주소" /></td>
								<td><input type="text" id="P_TEL_NO" class="input w100p" placeholder="전화번호" /></td>
								<td><input type="text" id="P_EMAL_ADDR" class="input w100p" placeholder="이메일" /></td>
			            	</tr>
			            </tbody>
			            <tbody id="offVendHideTbdy" style="display: none;">
			            	<tr> 
			            		<td> 
									<input type="checkbox" id="vendCbx" name="vendCbx"/> 
									<input type="hidden" id="P_VEND_REG_NO" placeholder="거래처번호" /><!-- 사업자번호랑 동일한 애가 들어감  -->
								</td>
								<td>
									<input type="text" id="P_BIZRNO"  class="input w60p" maxlength="10" placeholder="사업자번호" onblur="vendRegNoAuto(this);" numeric/>&nbsp;('-' 빼고 입력)
								</td>
								<td>
									<input type="text" id="P_VEND_NM" class="input w60p" placeholder="업체명" /> 
								</td>
								<td><input type="hidden" value="N" name="btnYn"/></td> 
								<td><input type="hidden" value="N" name="btnYn"/></td>
								<td><input type="text" id="P_CHRGR_NM" class="input w100p" placeholder="주소" /></td>
								<td><input type="text" id="P_TEL_NO" class="input w100p" placeholder="전화번호" /></td>
								<td><input type="text" id="P_EMAL_ADDR" class="input w100p" placeholder="이메일" /></td>
			            	</tr>
			            </tbody>
		             	<tbody id="vendShowTbdy">
							<c:if test="${vendListTotCnt > 0}">
								<c:forEach var="data" items="${vendList}" varStatus="status" >
									<tr> 
										<td> 
											<input type="checkbox" id="vendCbx" name="vendCbx"/> 
											<input type="hidden" id="P_VEND_REG_NO" name="P_VEND_REG_NO" placeholder="거래처번호" value="${data.VEND_REG_NO}" style="background: #f0f0f0;"  readonly="readonly"/><!-- 사업자번호랑 동일한 애가 들어감  -->
										</td>
										<td>
											<input type="text" id="P_BIZRNO${status.count}" name="P_BIZRNO"class="input w100p" placeholder="사업자번호" value="${comFn:formatBizNumber(data.BIZRNO)}"  style="background: #f0f0f0;"  readonly="readonly"/>
										</td>
										<td>
											<input type="text" id="P_VEND_NM${status.count}" name="P_VEND_NM" class="input w60p" placeholder="업체명" value="${data.VEND_NM}" style="background: #f0f0f0;"  readonly="readonly"/>
											<%-- <button type="button" class="btn ty03" id="vendSch${status.count}" name="vendSch" value="${status.count}" />검색</button> --%>
										</td>
										<td>
											<input type="hidden" value="Y" name="btnYn"/>
											<c:if test="${empty data.FILE_GRP_NO_DO02}">
												<input type="hidden" name="SAVEYN" value="N" />
												<button type="button"  name="DO02" class="btn ty04" onClick="javascript:filePopup( '${data.VEND_REG_NO}','DO02','',this);" >미등록</button>
											</c:if>
											<c:if test="${not empty data.FILE_GRP_NO_DO02}"> 
												<input type="hidden" name="SAVEYN" value="Y" />
												<c:if test="${bidReqDetail.BID_PSCD eq 'A008'|| bidReqDetail.BID_PSCD eq 'A012' || bidReqDetail.BID_PSCD eq 'A016'}"><!-- 입찰공고상태일때  -->
													<button type="button" class="btn ty04" onClick="javascript:filePopup('${data.VEND_REG_NO}','DO02');">보기</button>
												</c:if>
												<c:if test="${bidReqDetail.BID_PSCD ne 'A008' && bidReqDetail.BID_PSCD ne 'A012' && bidReqDetail.BID_PSCD ne 'A016'}"><!-- 기술평가등록상태일때  -->
													<button type="button" class="btn ty04" onClick="javascript:filePopup('${data.VEND_REG_NO}','DO02','view');">보기</button>
												</c:if>
											</c:if>
										</td> 
										<td>
											<input type="hidden" value="Y" name="btnYn"/>
											<c:if test="${empty data.ESTM_ELCD}">
												<input type="hidden" name="tchnSAVEYN" value="N" />
												<button type="button" name="DO01" class="btn ty04" onClick="javascript:tchnEstmPopup('${data.VEND_REG_NO}','DO01','',this);">미등록</button>
											</c:if>
											<c:if test="${not empty data.ESTM_ELCD}">
												<input type="hidden" name="tchnSAVEYN" value="Y" />
												<c:if test="${bidReqDetail.BID_PSCD eq 'A008'|| bidReqDetail.BID_PSCD eq 'A012' || bidReqDetail.BID_PSCD eq 'A016'}"><!-- 입찰공고상태일때  -->
													<button type="button" class="btn ty04" onClick="javascript:tchnEstmPopup('${data.VEND_REG_NO}','DO01');">${comFn:nvl(data.ESTM_ELCD_NM,'미등록')}</button>
												</c:if> 
												<c:if test="${bidReqDetail.BID_PSCD ne 'A008' && bidReqDetail.BID_PSCD ne 'A012' && bidReqDetail.BID_PSCD ne 'A016'}"><!-- 기술평가등록상태일때  -->
													<button type="button" class="btn ty04" onClick="javascript:tchnEstmPopup('${data.VEND_REG_NO}','DO01','view');">${comFn:nvl(data.ESTM_ELCD_NM,'미등록')}</button>
												</c:if>
											</c:if>
										</td>
										<td><input type="text" id="P_CHRGR_NM${status.count}" name="P_CHRGR_NM" class="input w100p" placeholder="주소" value="${data.CHRGR_NM}" /></td>
										<td><input type="text" id="P_TEL_NO${status.count}" name="P_TEL_NO" class="input w100p" placeholder="전화번호" value="${data.TEL_NO}" /></td>
										<td><input type="text" id="P_EMAL_ADDR${status.count}" name="P_EMAL_ADDR" class="input w100p" placeholder="이메일" value="${data.EMAL_ADDR}" /></td>
									</tr>
								</c:forEach>
							</c:if>
			             </tbody>
		             	<tbody id="vendEmptyTbdy">
	            			 <c:if test="${comFn:nvl(vendListTotCnt, 0) == 0}"> 
			             		<tr><td colspan="8" style="text-align: center;">업체가 존재하지 않습니다.</td></tr>
				             </c:if>
		             	</tbody> 
					</table>
				</c:if>
				<c:if test="${bidReqDetail.BID_PSCD ne 'A008'&& bidReqDetail.BID_PSCD ne 'A012' && bidReqDetail.BID_PSCD ne 'A016'}"><!-- 기술평가 등록 완료일 경우 정보 확인만 -->
					<table class="form_tb">
						<colgroup>
							<col width="15%">
					        <col width="35%">
					        <col width="15%">
					        <col width="35%">
						</colgroup>
						<tbody>
							<tr>
								<th scope="row">결재상태</th>
								<td></td>
							</tr>
						</tbody>
					</table>
					<p class="spc_stit">업체정보</p>
						<table class="tb">
			         	<colgroup>
							<col width="50px;">
							<col width="150px;">
							<col width="200px">
							<col width="100px;">
							<col width="100px;">
							<col width="100px;">
							<col width="100px;">
							<col width="100px;">
						</colgroup>
						<thead>
			                <tr>
								<th scope="col">순번</th>
								<th scope="col">사업자번호</th> 
								<th scope="col">업체명</th>
								<th scope="col">제안서/규격서</th>
								<th scope="col">평가결과</th>
								<th scope="col">주소</th>
								<th scope="col">전화번호</th>
								<th scope="col">이메일</th>
			                </tr>
			            </thead>
						<c:if test="${vendListTotCnt > 0}">
							<c:forEach var="data" items="${vendList}" varStatus="status" >
								<tr>
									<td> 
										${status.count} 
										<input type="hidden" id="P_VEND_REG_NO" name="P_VEND_REG_NO" placeholder="거래처번호" value="${data.VEND_REG_NO}" style="background: #f0f0f0;"  readonly="readonly"/><!-- 사업자번호랑 동일한 애가 들어감  -->
									</td>
									<td>
										<input type="text" id="P_BIZRNO" name="P_BIZRNO"class="input w100p" placeholder="사업자번호" value="${comFn:formatBizNumber(data.BIZRNO)}"  style="background: #f0f0f0;"  readonly="readonly"/>
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
									<td><input type="text" id="P_CHRGR_NM" name="P_CHRGR_NM" class="input w100p" placeholder="주소" value="${data.CHRGR_NM}" /></td>
									<td><input type="text" id="P_TEL_NO" name="P_TEL_NO" class="input w100p" placeholder="전화번호" value="${data.TEL_NO}" /></td>
									<td><input type="text" id="P_EMAL_ADDR" name="P_EMAL_ADDR" class="input w100p" placeholder="이메일" value="${data.EMAL_ADDR}" /></td>
								</tr>
							</c:forEach>
						</c:if>
            			 <c:if test="${comFn:nvl(vendListTotCnt, 0) == 0}"> 
		             		<tr><td colspan="8" style="text-align: center;">업체가 존재하지 않습니다.</td></tr>
			             </c:if>
					</table>
				</c:if>
			<%-- </c:if> --%>
			
			 <!-- 의뢰계약 --> 
			<%-- <c:if test="${bidReqDetail.PRCH_CHRG_SECD eq 'B' && loginResult.DEPT_NO eq bidReqDetail.PRCH_DEPT_NO && loginResult.AUTH_ID ne '1'}"><!-- 경영지원팀에서는  정보 확인만 -->
				<table class="form_tb">
						<colgroup>
							<col width="15%">
					        <col width="35%"> 
					        <col width="15%">
					        <col width="35%">
						</colgroup>
						<tbody>
							<tr>
								<th scope="row">결재상태</th>
								<td></td>
							</tr>
						</tbody>
					</table>
					<p class="spc_stit">업체정보</p>
						<table class="tb">
			         	<colgroup>
							<col width="50px;">
							<col width="150px;">
							<col width="200px">
							<col width="100px;">
							<col width="100px;">
							<col width="100px;">
							<col width="100px;">
							<col width="100px;">
						</colgroup>
						<thead>
			                <tr>
								<th scope="col">순번</th>
								<th scope="col">사업자번호</th> 
								<th scope="col">업체명</th>
								<th scope="col">제안서/규격서</th>
								<th scope="col">평가결과</th> 
								<th scope="col">담당자</th>
								<th scope="col">전화번호</th>
								<th scope="col">이메일</th>
			                </tr>
			            </thead>
						<c:if test="${vendListTotCnt > 0}">	 
							<c:forEach var="data" items="${vendList}" varStatus="status" >
								<tr>
									<td> 
										${status.count}
										<input type="hidden" id="P_VEND_REG_NO" name="P_VEND_REG_NO" placeholder="거래처번호" value="${data.VEND_REG_NO}" style="background: #f0f0f0;"  readonly="readonly"/><!-- 사업자번호랑 동일한 애가 들어감  -->
									</td>
									<td>
										<input type="text" id="P_BIZRNO" name="P_BIZRNO"class="input w100p" placeholder="사업자번호" value="${comFn:formatBizNumber(data.BIZRNO)}"  style="background: #f0f0f0;"  readonly="readonly"/>
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
									<td><input type="text" id="P_CHRGR_NM" name="P_CHRGR_NM" class="input w100p" placeholder="담당자" value="${data.CHRGR_NM}" style="background: #f0f0f0;"  readonly="readonly"/></td>
									<td><input type="text" id="P_TEL_NO" name="P_TEL_NO" class="input w100p" placeholder="전화번호" value="${data.TEL_NO}" style="background: #f0f0f0;"  readonly="readonly"/></td>
									<td><input type="text" id="P_EMAL_ADDR" name="P_EMAL_ADDR" class="input w100p" placeholder="이메일" value="${data.EMAL_ADDR}" style="background: #f0f0f0;"  readonly="readonly"/></td>
								</tr>
							</c:forEach>
						</c:if>
           			 <c:if test="${comFn:nvl(vendListTotCnt, 0) == 0}"> 
	             		<tr><td colspan="8" style="text-align: center;">업체가 존재하지 않습니다.</td></tr>
		             </c:if>
				</table>
            </c:if> --%>
			
			<div class="btm_btns"> 
			<!-- 1	사용자권한코드	관리자 
				 2	사용자권한코드	사업담당자 
				 4	사용자권한코드	계약담당자
				 5	사용자권한코드	계약담당자(지사)
			 -->
					<!--   사업담당자 -->
					<c:if test="${loginResult.AUTH_ID eq '2' || loginResult.AUTH_ID eq '3'}"> 
						<!-- 입찰공고 -->
						<c:if test="${bidReqDetail.BID_PSCD eq 'A010' && loginResult.APPR_AUTH_YN eq 'Y'}"><!-- 기술평가승인요청-사업부서팀장 -->
							<button type="button" class="btn ty02" id="applyBtn">승인</button>
							<button type="button" class="btn ty02" id="rtnBtn">반려</button>
						</c:if>
						<c:if test="${sessionScope.loginResult.USR_ID eq bidReqDetail.RQR_CHRGR_ID }"><!-- 작성자만  -->
									<c:if test="${bidReqDetail.BID_PSCD eq 'A008'}">	 
										<button type="button" class="btn ty02" id="sancReqBtn_i">전자결재(심사평가시행품의)</button> 
									</c:if>
									<c:if test="${bidReqDetail.BID_PSCD eq 'A009'}"><!-- 기술평가등록  -->
										<button type="button" class="btn ty02" id="applyReqBtn">승인요청</button>
									</c:if>  
									<!--입찰공고, 기술평가반려, 기술평가경영지원팀반려-->
									<c:if test="${bidReqDetail.BID_PSCD eq 'A008'|| bidReqDetail.BID_PSCD eq 'A012' || bidReqDetail.BID_PSCD eq 'A016'}">
											<!-- 업체저장 후에 저장 보이도록 -->
										<c:if test="${vendListTotCnt > 0 }">
											<button type="button" class="btn ty02" id="statChnBtn">저장완료</button><!--  기술평가완료 상태로 변경 -->
										</c:if>
									</c:if> 
						</c:if>
					</c:if>
					<%-- </c:if> --%>
					<c:if test="${loginResult.AUTH_ID eq '1' || loginResult.AUTH_ID eq '4' || loginResult.AUTH_ID eq '5'}">
						<c:if test="${bidReqDetail.BID_PSCD eq 'A011'}"><!-- 기술평가승인_경영지원팀원 --> 
							<button type="button" class="btn ty02" id="teamRecApplyBtn">경영지원팀접수</button>
						</c:if>
						<c:if test="${bidReqDetail.BID_PSCD eq 'A011' || bidReqDetail.BID_PSCD eq 'A014'}"><!-- 기술평가승인 또는 기술평가경영지원팀 접수반려_경영지원팀원 -->
							<button type="button" class="btn ty02" id="teamRecRtnBtn">경영지원팀반려</button>
						</c:if>
						<c:if test="${bidReqDetail.BID_PSCD eq 'A013' && loginResult.APPR_AUTH_YN eq 'Y'}"><!-- 기술평가경영지원팀접수_경영지원팀장 -->
							<button type="button" class="btn ty02" id="RecApplyBtn">접수승인</button> 
							<button type="button" class="btn ty02" id="RecRtnBtn">접수반려</button>
						</c:if>
						<c:if test="${bidReqDetail.BID_PSCD eq 'A008'}">	 
							<button type="button" class="btn ty02" id="sancReqBtn_i">전자결재(심사평가시행품의)</button> 
						</c:if>
						<c:if test="${bidReqDetail.BID_PSCD eq 'A010' && loginResult.APPR_AUTH_YN eq 'Y'}"><!-- 기술평가승인요청-사업부서팀장 -->
							<button type="button" class="btn ty02" id="applyBtn">승인</button>
							<button type="button" class="btn ty02" id="rtnBtn">반려</button>
						</c:if>
						<c:if test="${bidReqDetail.BID_PSCD eq 'A009'}"><!-- 기술평가등록  -->
							<button type="button" class="btn ty02" id="applyReqBtn">승인요청</button>
						</c:if>  
						<!--입찰공고, 기술평가반려, 기술평가경영지원팀반려-->
						<c:if test="${bidReqDetail.BID_PSCD eq 'A008'|| bidReqDetail.BID_PSCD eq 'A012' || bidReqDetail.BID_PSCD eq 'A016'}">
								<!-- 업체저장 후에 저장 보이도록 -->
							<c:if test="${vendListTotCnt > 0 }">
								<button type="button" class="btn ty02" id="statChnBtn">저장완료</button><!--  기술평가완료 상태로 변경 -->
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
	<input type="hidden" name="P_BID_PSCD" value="">
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

<!-- 결재창  -->
<form id="apprDetailFrm" method="POST" action="">
	<input type="hidden" name="resourceName" value="${comFn:nvl(param.resourceName, resourceName)}">
	<input type="hidden" name="P_ANNC_NO"  value="${bidReqDetail.ANNC_NO}">
	<input type="hidden" name="P_ANNC_NGR"  value="${bidReqDetail.ANNC_NGR}">
	<input type="hidden" name="P_APPR_URL" value="${APPR_URL}" >
	<input type="hidden" name="P_APPR_CNTC_KEY" value="B" ><!-- 심사평가 실행품의-->
	<input type="hidden" name="P_MODE" ><!-- drft인지 view인지 -->
	<input type="hidden" name="P_GBN" value="BI" > 
</form> 


