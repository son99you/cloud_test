<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 예가현황 > 예가현황 등록 폼
 *
 * <pre>
 * ebid 
 *    |_ prdprcManageUpdtForm.jsp
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

<script type="text/javascript" src="${jsPath}/ipro/views/ebid/prdprcManageUpdtForm.js"></script>
 
<div class="conts_wrap">
	<div class="inner">
		<div class="tit_wrap">
			<h3 class="tit">예가현황 수정</h3>
			<ul class="step_wrap">
				<li><a href="#">${myMenuList.bigMenuNm}</a></li>
				<li><a href="#">${myMenuSubList.smallMenuNm}</a></li>
			</ul> <!--// step_wrap E -->
		</div> <!--// tit_wrap E -->
		<input type="hidden" id="compnoPrdprcAtCd" value="${bidPlanDetail.COMPNO_PRDPRC_SE_CD}">
		<input type="hidden" id="prdprcRegistAt" <c:if test="${empty prdprcRegistInqire}"> value="empty"</c:if>>
		<input type="hidden" id="bidLmtAmount" value="${bidPlanDetail.BID_LMT_AMOUNT}">
		
		<div class="view_wrap typeA">
			<h4 class="tit">입찰개요</h4>
			<div class="view_area">
				<table>
			    	<colgroup>
			        	<col style="width: 15%;">						        
			        	<col style="width: 35%;">						        
			        	<col style="width: 15%;">						        
			        	<col style="width: 35%;">
			        </colgroup>
			    	<tr>
			        	<th scope="row">입찰공고번호</th>
		                <td>
		                    ${bidPlanDetail.PBLANC_NO}-${bidPlanDetail.PBLANC_ODR}
		                </td>
		                <th scope="row">공고일자</th>
		                <td>
		                <c:if test="${bidPlanDetail.PRVSTL_AT eq 'Y' }">
		                	${comFn:formatDate(bidPlanDetail.PBLANC_DT,'yyyyMMddHHmm','yyyy-MM-dd')}
		                </c:if>
		                <c:if test="${bidPlanDetail.PRVSTL_AT ne 'Y' }">
		                    ${comFn:formatDate(bidPlanDetail.PBLANC_DT,'yyyyMMddHHmmss','yyyy-MM-dd')}
		                </c:if>
		                </td>
			        </tr>
			        <tr>
			            <th>입찰명</th>
			            <td colspan="3">${bidPlanDetail.BID_NM}</td>
			        </tr>
			        <tr>
			            <th scope="row">공고구분</th>
		                <td>
		                <c:if test="${bidPlanDetail.PRVSTL_AT ne 'Y' }">
		                    ${bidPlanDetail.PBLANC_SE_CD_NM}
		                </c:if>
		                <c:if test="${bidPlanDetail.PRVSTL_AT eq 'Y' }">
		                	실공고
		                </c:if>
		                </td>
		                <th scope="row">입찰구분</th>
		                <td>
		                	<c:if test="${bidPlanDetail.PRVSTL_AT ne 'Y' }">
		                	<%-- 입찰구분이 [구매]가 아닐경우 입찰상세구분 활성화 --%>
		                    ${bidPlanDetail.PRCURE_SE_CD_NM}<c:if test="${bidPlanDetail.PRCURE_SE_CD ne '1'}"> &nbsp;>&nbsp; ${bidPlanDetail.PRCURE_DETAIL_SE_CD_NM}</c:if>
		                    </c:if>
		                    <c:if test="${bidPlanDetail.PRVSTL_AT eq 'Y' }">
		                    ${bidPlanDetail.PRCURE_SE_CD_NM}                        
		                    </c:if>
		                </td>
			        </tr>
			        <tr>    
			            <th scope="row">입찰방법</th>
		                <td colspan="3">
		                	<c:if test="${bidPlanDetail.PRVSTL_AT ne 'Y' }">
		                	<%-- 낙찰자 선정 방법이 [협상에의한 계약(기술종합평가)] 일 경우에 합산비율 활성화, [최저가입찰가순 적격심사] 일 경우에 낙찰하한율 활성화    --%>
		                    ${bidPlanDetail.CNTRCT_MTH_CD_NM} &nbsp;>&nbsp; ${bidPlanDetail.SCSBID_MTH_CD_NM} <c:if test="${bidPlanDetail.SCSBID_MTH_CD eq '31'}">( ${bidPlanDetail.SCSBID_LWLT_RT} )</c:if>
		                    </c:if>
		                    <c:if test="${bidPlanDetail.PRVSTL_AT eq 'Y' }">
		                    ${bidPlanDetail.CNTRCT_MTH_CD_NM} &nbsp;
		                    </c:if>
		                </td>
					</tr>
					<tr>
			            <th>예가구분</th>
			            <td colspan="3">
			            	${bidPlanDetail.COMPNO_PRDPRC_SE_NM}
			            </td>
			        </tr>
			    </table>
			</div>
			
			<form id="updtFrm" method="POST" enctype="multipart/form-data">
		        <input type="hidden" name="resourceName" value="${param.resourceName}">
		        <input type="hidden" name="P_PBLANC_NO" value="${bidPlanDetail.PBLANC_NO}">
				<input type="hidden" name="P_PBLANC_ODR" value="${bidPlanDetail.PBLANC_ODR}">
				<input type="hidden" name="P_USER_ID" value="${sessionScope.loginResult.USER_ID}" >
				<input type="hidden" name="P_PRVSTL_AT" value="${param.P_PRVSTL_AT}" >
				<input type="hidden" name="P_BSIS_PRDPRC_STTUS_CD" value="F">
				<input type="hidden" id="P_ATCHMNFL_GROUP_NO" name="P_ATCHMNFL_GROUP_NO" value="${prdprcInfoInqire.ATCHMNFL_GROUP_NO}">
				<input type="hidden" id="P_DEL_SN" name="P_DEL_SN">
			
				<h4 class="tit">기초예가 산정현황</h4>
				<div class="view_area">
					<table>
						<caption>기초예가 산정현황</caption>
			       		<colgroup>
			              	<col style="width: 33%;">
							<col style="width: 33%;">
							<col style="width: auto;">
			       		</colgroup>
				    	<tr>
				            <th class="txtc">집행예산한도액(A)</th>
				            <th class="txtc">기초예정가격(B)</th>
				            <th class="last txtc">잔액(C = A - B)</th>
				        </tr>
				        <tr>
				            <td class="txtc">${comFn:formatMoney(bidPlanDetail.BID_LMT_AMOUNT)}</td>
							<td class="txtc">
								<label for="P_BSIS_PRDPRC_TOT_AMOUNT" class="blind">기초예정가격</label>
		                       	<input type="text" class="tr disabled" id="P_BSIS_PRDPRC_TOT_AMOUNT" name="P_BSIS_PRDPRC_TOT_AMOUNT" value="${comFn:formatMoney(prdprcInfoInqire.BSIS_PRDPRC_TOT_AMOUNT)}" maxlength="17" money readonly> 
							</td>
							<td class="txtc" id="blce">${comFn:formatMoney(bidPlanDetail.BID_LMT_AMOUNT - prdprcInfoInqire.BSIS_PRDPRC_TOT_AMOUNT)}</td>
				        </tr>
				    </table>
				</div>
				<div class="tit_area">
					<h4 class="tit" style="clear: both;">집행예산한도액 상세내역</h4>
					<c:if test="${empty prdprcIemList}">
						<div class="btn_right">
							<button type="button" class="btn btn_02_auto btn_c2" id="iemAditBtn">항목 추가</button>
						</div>
					</c:if>
				</div>
				<div class="view_area">
					<c:if test="${empty prdprcIemList}">
			        	<c:set var="iemListAt" value="N" />
			        </c:if>
			        <c:if test="${not empty prdprcIemList}">
			        	<c:set var="iemListAt" value="Y" />
			        </c:if>
			        <input type="hidden" id="iemListAt" value="${iemListAt}" />
			        <c:if test="${empty prdprcIemList}">
						<table>
							<caption>집행예산한도액 상세내역</caption>
				       		<colgroup>
				               	<col style="width: 10%;">
								<col style="width: 20%;">
								<col style="width: 20%;">
								<col style="width: auto;">
								<col style="width: 10%;">
				           	</colgroup>
				           	<tr>
					            <th class="txtc">번호</th>
					            <th class="txtc">항목</th>
					            <th class="txtc">집행예산한도액</th>
					            <th class="txtc">내용</th>
					            <th class="last txtc">삭제</th>
					        </tr>
					        <c:forEach var="data" items="${prdprcDetailList}" varStatus="status" >
		                	<c:if test="${status.first eq true}">
								<tr class="row" style="display:none;">
									<td class="txtc">${data.SN}</td>
									<td class="txtc">
										<label for="P_IEM_NM" class="blind">항목명</label>
		                        		<input type="text" id="P_IEM_NM" name="P_IEM_NM" value="${data.IEM_NM}" style="width: 220px;" maxlength="80" onblur="iemNmInput(this);"> 
									</td>
									<td class="txtc">
										<label for="P_EXCUT_LMT_AMOUNT" class="blind">집행예산한도액</label>
			                        	<input type="text" id="P_EXCUT_LMT_AMOUNT" name="P_EXCUT_LMT_AMOUNT" value="${comFn:formatMoney(data.EXCUT_LMT_AMOUNT)}" money style="width: 220px;" maxlength="80"  onblur="excutLmtAmountInput(this);"> 
									</td>
									<td class="txtc">
										<label for="P_EXCUT_LMT_CN" class="blind">집행한도내용</label>
			                        	<input type="text" id="P_EXCUT_LMT_CN" name="P_EXCUT_LMT_CN" value="${data.EXCUT_LMT_CN}" style="width: 90%;" maxlength="4000" > 
									</td>
									<td class="txtc">
										<button type="button" class="btn btn_02 btn_sch" onclick="iemDeleteBtn(this);">삭제</button>
									</td>
								</tr>
		                	</c:if>
							</c:forEach>
							<tbody id="cloneTbodyOne">
								<tr class="row" style="display:none;">
									<td class="txtc" id="sn"></td>
									<td class="txtc">
										<label for="P_IEM_NM" class="blind">항목명</label>
			                        	<input type="text" id="P_IEM_NM" name="P_IEM_NM" style="width: 220px;" maxlength="80" disabled onblur="iemNmInput(this);"> 
									</td>
									<td class="txtc">
										<label for="P_EXCUT_LMT_AMOUNT" class="blind">집행예산한도액</label>
			                        	<input type="text" id="P_EXCUT_LMT_AMOUNT" name="P_EXCUT_LMT_AMOUNT" money style="width: 220px;" maxlength="80" disabled onblur="excutLmtAmountInput(this);"> 
									</td>
									<td class="txtc">
										<label for="P_EXCUT_LMT_CN" class="blind">집행한도내용</label>
			                        	<input type="text" id="P_EXCUT_LMT_CN" name="P_EXCUT_LMT_CN"  style="width: 90%;" maxlength="4000" disabled> 
									</td>
									<td class="txtc">
										<button type="button" class="btn btn_02 btn_sch" onclick="iemDeleteBtn(this);">삭제</button>
									</td>
								</tr>
			                </tbody>
			                <tbody id="appendTbodyOne">
			                	<c:forEach var="data" items="${prdprcDetailList}" varStatus="status" >
			                	<c:if test="${status.first eq false}">
									<tr>
										<td class="txtc">${data.SN}</td>
										<td class="txtc">
											<label for="P_IEM_NM" class="blind">항목명</label>
			                        	<input type="text" id="P_IEM_NM" name="P_IEM_NM" value="${data.IEM_NM}" style="width: 220px;" maxlength="80" onblur="iemNmInput(this);"> 
										</td>
										<td class="txtc">
											<label for="P_EXCUT_LMT_AMOUNT" class="blind">집행예산한도액</label>
			                        	<input type="text" id="P_EXCUT_LMT_AMOUNT" name="P_EXCUT_LMT_AMOUNT" value="${comFn:formatMoney(data.EXCUT_LMT_AMOUNT)}" money style="width: 220px;" maxlength="80"  onblur="excutLmtAmountInput(this);"> 	
										</td>
										<td class="txtc">
											<label for="P_EXCUT_LMT_CN" class="blind">집행한도내용</label>
				                        	<input type="text" id="P_EXCUT_LMT_CN" name="P_EXCUT_LMT_CN" value="${data.EXCUT_LMT_CN}" style="width: 90%;" maxlength="4000" > 
										</td>
										<td class="txtc">
											<button type="button" class="btn btn_02 btn_sch" onclick="iemDeleteBtn(this);">삭제</button>
										</td>
									</tr>
			                	</c:if>
								</c:forEach>
			                </tbody>
					    </table>
				    </c:if>
				    <%-- 조달의뢰정보에서 받아온 항목이 하나도 없을때 항목 추가 버튼 활성화 ENd--%>
			        <%-- 조달의뢰정보에서 받아온 항목이 있으면 그대로 보여주기 --%>
			        <c:if test="${not empty prdprcIemList}">
				        <div class="view_area">
				            <table class="tableList" summary="집행예산한도액 상세내역 입니다.">
				                <caption>집행예산한도액 상세내역</caption>
				                <colgroup>
				                    <col style="width: 10%;">
									<col style="width: 20%;">
									<col style="width: 20%;">
									<col style="width: auto;">
				                </colgroup>
				                <thead>
				                <tr>
				                    <th scope="col" class="txtc">번호</th>
				                    <th scope="col" class="txtc">항목</th>
				                    <th scope="col" class="txtc">집행예산한도액</th>
				                    <th scope="col" class="txtc">내용</th>
				                </tr>
				                </thead>
				                <tbody>
				                	<c:forEach var="data" items="${prdprcDetailList}" varStatus="status" >
										<tr class="row" <c:if test="${status.first eq true}">style="display:none;"</c:if>>
											<td class="txtc">${status.index}</td>
											<td class="txtc">${data.IEM_NM}</td>
											<td class="txtc">${comFn:formatMoney(data.EXCUT_LMT_AMOUNT)}</td>
											<td class="txtc">
												<label for="P_EXCUT_LMT_CN" class="blind">집행한도내용</label>
					                        	<input type="text" id="P_EXCUT_LMT_CN" name="P_EXCUT_LMT_CN" value="${data.EXCUT_LMT_CN}"  style="width: 90%;" maxlength="4000" > 
											</td>
										</tr>
									</c:forEach>
				                </tbody>
				            </table>
				        </div>
			        </c:if>
			        <%-- 조달의뢰정보에서 받아온 항목이 있으면 그대로 보여주기 END --%>
			    </div>
			    
		        
				
				<h4 class="tit">소요예산 조정내역</h4>
				<div class="view_area">
					<table>
						<caption>소요예산 조정내역</caption>
			      		<colgroup>
			               	<col style="width: 5%;">
			               	<col style="width: 18%;">
			               	<col style="width: 18%;">
			               	<col style="width: 16%;">
			               	<col style="width: 16%;">
			               	<col style="width: auto;">
			        	</colgroup>
				    	<tr>
				            <th class="txtc">번호</th>
				            <th class="txtc">항목</th>
				            <th class="txtc">집행예산한도액</th>
				            <th class="txtc">기초예정가격</th>
				            <th class="txtc">차액</th>
				            <th class="last txtc">비고</th>
				        </tr>
				        <%-- 조달의뢰정보에서 받아온 항목이 하나도 없을때 --%>
		        		<c:if test="${empty prdprcIemList}">
		        			<c:forEach var="data" items="${prdprcDetailList}" varStatus="status" >
		                		<c:if test="${status.first eq true}">
									<tr class="row"  id="smTr">
										<td class="txtc"></td>
										<td class="txtc">${data.IEM_NM}</td>
										<td class="txtc" <c:if test="${status.first eq true}"> id="totExcutLmtAmount2"</c:if>>${comFn:formatMoney(data.EXCUT_LMT_AMOUNT)}</td>
										<td class="txtc">
											<label for="P_BSIS_PRDPRC_AMOUNT${data.SN}" class="blind">기초예정가격</label>
				                        	<input type="text" id="P_BSIS_PRDPRC_AMOUNT${data.SN}" name="P_BSIS_PRDPRC_AMOUNT" value="${comFn:formatMoney(data.BSIS_PRDPRC_AMOUNT)}" style="width: 140px;" maxlength="17" money onkeyup="budgetIemCalc(this);" <c:if test="${status.first eq true}">readonly class='disabled tr'</c:if><c:if test="${status.first eq false}">class='tr'</c:if>>
											<input type="hidden" name="P_SN" value="${data.SN}">
										</td>
										<td class="txtc">${comFn:formatMoney(data.EXCUT_LMT_AMOUNT - data.BSIS_PRDPRC_AMOUNT)}</td>
										<td class="txtc">
				                        	<label for="P_BSIS_PRDPRC_CN" class="blind">기초예가내용</label>
				                        	<input type="text" id="P_BSIS_PRDPRC_CN" name="P_BSIS_PRDPRC_CN" value="${data.BSIS_PRDPRC_CN}" style="width: 240px;" maxlength="4000">
										</td>
									</tr>
								</c:if>
							</c:forEach>
			                <tbody id="appendTbodyTwo">
			                	<c:forEach var="data" items="${prdprcDetailList}" varStatus="status" >
			                		<c:if test="${status.first eq false}">
										<tr>
											<td class="txtc"><c:if test="${status.first eq false}">${data.SN}</c:if></td>
											<td class="txtc">${data.IEM_NM}</td>
											<td class="txtc" <c:if test="${status.first eq true}"> id="totExcutLmtAmount2"</c:if>>${comFn:formatMoney(data.EXCUT_LMT_AMOUNT)}</td>
											<td class="txtc">
												<label for="P_BSIS_PRDPRC_AMOUNT${data.SN}" class="blind">기초예정가격</label>
					                        	<input type="text" id="P_BSIS_PRDPRC_AMOUNT${data.SN}" name="P_BSIS_PRDPRC_AMOUNT" value="${comFn:formatMoney(data.BSIS_PRDPRC_AMOUNT)}" style="width: 140px;" maxlength="17" money onkeyup="budgetIemCalc(this);" <c:if test="${status.first eq true}">readonly class='disabled tr'</c:if><c:if test="${status.first eq false}">class='tr'</c:if>>
												<input type="hidden" name="P_SN" value="${data.SN}">
											</td>
											<td class="txtc">${comFn:formatMoney(data.EXCUT_LMT_AMOUNT - data.BSIS_PRDPRC_AMOUNT)}</td>
											<td class="txtc">
					                        	<label for="P_BSIS_PRDPRC_CN" class="blind">기초예가내용</label>
					                        	<input type="text" id="P_BSIS_PRDPRC_CN" name="P_BSIS_PRDPRC_CN" value="${data.BSIS_PRDPRC_CN}" style="width: 240px;" maxlength="4000">
											</td>
										</tr>
									</c:if>
								</c:forEach>
			                </tbody>
			                <tbody id="cloneTbodyTwo">
								<tr class="row" style="display:none;">
									<td class="txtc" id="sn"></td>
									<td class="txtc"></td>
									<td class="txtc"></td>
									<td class="txtc">
										<label for="P_BSIS_PRDPRC_AMOUNT" class="blind">기초예정가격</label>
			                        	<input type="text" id="P_BSIS_PRDPRC_AMOUNT" name="P_BSIS_PRDPRC_AMOUNT"  style="width: 140px;" maxlength="17" money onkeyup="budgetIemCalc(this);" class="tr" disabled>
										<input type="hidden" name="P_SN" disabled>
									</td>
									<td class="txtc"></td>
									<td class="txtc">
			                        	<label for="P_BSIS_PRDPRC_CN" class="blind">기초예가내용</label>
			                        	<input type="text" id="P_BSIS_PRDPRC_CN" name="P_BSIS_PRDPRC_CN" style="width: 240px;" maxlength="4000" disabled>
									</td>
								</tr>
			                </tbody>
		                </c:if>
		                <%-- 조달의뢰정보에서 받아온 항목이 하나도 없을때  END --%>
		                <%-- 조달의뢰정보에서 받아온 항목이 있으면 그대로 보여주기 --%>
		        		<c:if test="${not empty prdprcIemList}">
			                <tbody>
			                	<c:forEach var="data" items="${prdprcDetailList}" varStatus="status" >
								<tr>
									<td class="txtc"><c:if test="${status.first eq false}">${status.index}</c:if></td>
									<td class="txtc">${data.IEM_NM}<input type="hidden" name="P_IEM_NM" value="${data.IEM_NM}" ><input type="hidden" name="P_SN" value="${data.SN}" ></td>
									<td class="txtc">${comFn:formatMoney(data.EXCUT_LMT_AMOUNT)}<input type="hidden" name="P_EXCUT_LMT_AMOUNT" value="${data.EXCUT_LMT_AMOUNT}" ></td>
									<td class="txtc">
										<label for="P_BSIS_PRDPRC_AMOUNT" class="blind">기초예정가격</label>
			                        	<input type="text" id="P_BSIS_PRDPRC_AMOUNT" name="P_BSIS_PRDPRC_AMOUNT" value="${comFn:formatMoney(data.BSIS_PRDPRC_AMOUNT)}" style="width: 140px;" maxlength="17" money onkeyup="budgetIemCalc(this);" <c:if test="${status.first eq true}">readonly class='disabled tr'</c:if><c:if test="${status.first eq false}">class='tr'</c:if>>
									</td>
									<td class="txtc">${comFn:formatMoney(data.EXCUT_LMT_AMOUNT - data.BSIS_PRDPRC_AMOUNT)}</td>
									<td class="txtc">
			                        	<label for="P_BSIS_PRDPRC_CN" class="blind">기초예가내용</label>
			                        	<input type="text" id="P_BSIS_PRDPRC_CN" name="P_BSIS_PRDPRC_CN" value="${data.BSIS_PRDPRC_CN}" style="width: 240px;" maxlength="4000">
									</td>
								</tr>
								</c:forEach>
			                </tbody>
		                </c:if>
		                <%-- 조달의뢰정보에서 받아온 항목이 있으면 그대로 보여주기 END --%>
				    </table>
			    </div>
				
				<h4 class="tit">기초예정가격 산정 근거</h4>
				<div class="view_area">
					<table>
				    	<colgroup>
				        	<col style="width: 15%;">						        
				        	<col style="width: 85%;">						        
				        </colgroup>
				        <tbody>
			                <tr>
			                    <th scope="row">산정기준</th>
			                    <td>
			                        <label for="P_CALC_STDR_CN" class="blind">산정기준</label>
			                        <textarea id="P_CALC_STDR_CN" name="P_CALC_STDR_CN" rows="5"  cols="70" style="width: 100%" maxlength="4000">${prdprcInfoInqire.CALC_STDR_CN}</textarea>
			                    </td>
			                </tr>
			                <tr>
			                    <th scope="row">산정방법</th>
			                    <td>
			                        <label for="P_CALC_MTH_CN" class="blind">산정방법</label>
			                        <textarea id="P_CALC_MTH_CN" name="P_CALC_MTH_CN" rows="5"  cols="70" style="width: 100%" maxlength="4000">${prdprcInfoInqire.CALC_MTH_CN}</textarea>
			                    </td>
			                </tr>
		                </tbody>
				    </table>
				</div>
				
				<h4 class="tit">첨부파일 (예가 산정 관련된 파일을 첨부)</h4>
				<div class="view_area">
					<table id="fileTable">
				    	<colgroup>
				        	<col style="width: 15%;">						        
				        	<col style="width: 85%;">						        
				        </colgroup>
			            <tr>
		                	<th scope="row" class="vtop">첨부파일</th>
							<td class="vtop">
							<span class="stD">
		                    	<button type="button"  class="btn btn_02 btn_sch" id="fileBtn" style="float: right;">추가</button> 
		                    </span>
		                    	<div id="fileRow" style="display: none; height: 30px;">
		                    		<input type="file" name="" style="width: 80%;height: 24px;" >
				                   	<span class="stD"> 
		                    			<button type="button" class="btn btn_02 btn_sch" style="display: inline-block; margin-top: 3px;" onclick="rowDel(this)">삭제</button>
		                    		</span>
		                    	</div>
		                    	<div id="fileViewDiv" style="width: 680px; line-height: 30px;">
		                    		<c:if test="${not empty bidAtchDocList }">
					            		<c:forEach var="data" items="${bidAtchDocList }" varStatus="status">
					            			<div style="height: 30px;"> 
				                   				<a href="javascript:pageObj.download('${data.ATCHMNFL_SN}');" class="attfile">${data.ATCHMNFL_NM }</a>
				                   				<span class="stD">
					                   				<button type="button" class="btn btn_02 btn_sch" style="display: inline-block; margin-top: 3px;" onclick="fileDel(this,'${data.ATCHMNFL_SN }')">삭제</button>
					                   			</span> 
				                    		</div>
					            		</c:forEach>
					            	</c:if>
		                    	</div>
		                    	<div id="fileDiv" style="width: 680px; line-height: 30px;">
		                    	</div>
		                    </td>	
			             </tr>
				    </table>
				</div>
				<div class="btn_wrap">
					<button type="button" class="btn btn_02_auto btn_revise pointer" id="detailBtn">입찰공고문 상세</button>
					<button type="button" class="btn btn_02 btn_revise pointer" name="updtBtn" >저장</button>
			    	<button type="button" class="btn btn_02 btn_sch pointer" name="listBtn" >취소</button>
			    </div>
			</form>
		</div>
	</div>
</div> <!--// content E-->
<form id="listFrm" method="POST">
	<input type="hidden" name="resourceName" value="${ param.resourceName }" >
</form>
<form id="popupFrm" method="POST">
	<input type="hidden" name="P_PBLANC_NO" value="${bidPlanDetail.PBLANC_NO}" >
	<input type="hidden" name="P_PBLANC_ODR" value="${bidPlanDetail.PBLANC_ODR}" >
</form>
<form id="downFrm" method="POST" >
	<input type="hidden" name="P_ATCHMNFL_SN">
</form>