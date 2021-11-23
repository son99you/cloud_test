<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 입찰현황 > 입찰계획품의 상세
 *
 * <pre>
 * ebid 
 *    |_ bidPlanAprvlDetail.jsp
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

<script type="text/javascript" src="${jsPath}/ipro/views/ebid/bidPlanAprvlDetail.js"></script> 
 
<div class="conts_wrap">
	<div class="inner">
		<div class="tit_wrap">
		   <h3 class="tit">입찰계획품의 상세</h3>
		   <ul class="step_wrap">
		      <li><a href="#">${myMenuList.bigMenuNm}</a></li>
		      <li><a href="#">${myMenuSubList.smallMenuNm}</a></li>
		   </ul> <!--// step_wrap E -->
		</div> <!--// tit_wrap E -->
		
		<div class="view_wrap typeB">
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
	                        ${bidPlanAprvlDetail.PBLANC_NO}-${bidPlanAprvlDetail.PBLANC_ODR}
	                    </td>
	                    <th scope="row">공고일자</th>
	                    <td>
	                        ${comFn:formatDate(bidPlanAprvlDetail.PBLANC_DT,'yyyyMMddHHmmss','yyyy-MM-dd')}
	                    </td>
					</tr>
	                <tr>
	                    <th scope="row">입찰명</th>
	                    <td colspan="3">
	                    	<c:if test="${bidPlanAprvlDetail.EMRGNCY_BID_AT eq 'Y'}"><font color="red">[긴급]</font></c:if>
	                    	<c:if test="${bidPlanAprvlDetail.FNGPRT_BID_AT eq 'Y'}"><font color="red">[지문]</font></c:if>
	                    	<c:if test="${bidPlanAprvlDetail.INTRLBID_AT eq 'Y'}"><font color="red">[국제]</font></c:if>
	                    	${bidPlanAprvlDetail.BID_NM}
	                    </td>
	                </tr>
	                <%-- 국제입찰 여부가 Y이면 활성화 --%>
	                <c:if test="${bidPlanAprvlDetail.INTRLBID_AT eq 'Y' }">
	                <tr>
	                    <th scope="row">입찰명(영문)</th>
	                    <td colspan="3">
	                    	${bidPlanAprvlDetail.BID_ENG_NM}
	                    </td>
	                </tr>
	                </c:if>
	                <%-- 국제입찰 여부가 Y이면 활성화 END --%>
	                <tr>
	                    <th scope="row">공고구분</th>
	                    <td>
	                        ${bidPlanAprvlDetail.PBLANC_SE_CD_NM}
	                    </td>
	                    <th scope="row">입찰구분</th>
	                    <td>
	                        ${bidPlanAprvlDetail.PRCURE_SE_CD_NM}&nbsp;/&nbsp; ${bidPlanAprvlDetail.PRCURE_DETAIL_SE_CD_NM}
	                    </td>
	                </tr>
	                <tr>
	                    <th scope="row">긴급입찰여부</th>
	                    <td>
	                        ${bidPlanAprvlDetail.EMRGNCY_BID_AT_NM}
	                    </td>
	                    <th scope="row">투찰내역서 제출필수여부</th>
	                	<td>
	                		<c:choose>
	                        	<c:when test="${bidPlanAprvlDetail.BDDPR_DTSTMN_PRESENTN_AT eq 'Y' }">예</c:when>
	                        	<c:otherwise>아니오</c:otherwise>
	                        </c:choose>
	                    </td>
	                </tr>
	                <tr>
	                    <th scope="row">나라장터 연계여부</th>
	                    <td <c:if test="${bidPlanAprvlDetail.PRCURE_SE_CD eq '0'}">colspan="3"</c:if>>
	                        <c:choose>
	                        	<c:when test="${bidPlanAprvlDetail.CARE_ESSNTL_AT eq 'Y' }">예</c:when>
	                        	<c:otherwise>아니오</c:otherwise>
	                        </c:choose>
	                    </td>
	                   	<%-- 입찰구분이 공사, 용역일때 --%>
	                	<c:if test="${bidPlanAprvlDetail.PRCURE_SE_CD ne '0'}">
	                    <th scope="row">기자재 포함 여부</th>
	                    <td>
	                        ${bidPlanAprvlDetail.MHRML_INCLS_AT_NM}
	                    </td>
	                	</c:if>
	                </tr>
	                <%-- 입찰구분이 [공사], [용역] 일 경우 활성화 --%>
	                <c:if test="${bidPlanAprvlDetail.PRCURE_SE_CD ne '0'}">
	                <tr>
	                    <th scope="row">입찰범위</th>
	                    <td colspan="3">
	                    	<label for="P_BID_SCOPE_CN" class="blind">입찰범위</label>
	                    	<textarea id="P_BID_SCOPE_CN" readonly taView style="display: none;"><c:out value="${bidPlanAprvlDetail.BID_SCOPE_CN}"></c:out></textarea>
	                    </td>
	                </tr>
	                <%-- 국제입찰 여부가 Y이면 활성화 --%>
	                <c:if test="${bidPlanAprvlDetail.INTRLBID_AT eq 'Y' }">
	                <tr>
	                    <th scope="row">입찰범위(영문)</th>
	                    <td colspan="3">${bidPlanAprvlDetail.BID_SCOPE_ENG_CN}</td>
	                </tr>
	                </c:if>
	                <tr>
	                    <th scope="row">계약기간</th>
	                    <td colspan="3">${bidPlanAprvlDetail.CNTRCTPD_CN}</td>
	                </tr>
	                <%-- 국제입찰 여부가 Y이면 활성화 --%>
	                <c:if test="${bidPlanAprvlDetail.INTRLBID_AT eq 'Y' }">
	                <tr>
	                    <th scope="row">계약기간(영문)</th>
	                    <td colspan="3">${bidPlanAprvlDetail.CNTRCTPD_ENG_CN}</td>
	                </tr>
	                </c:if>
	                </c:if>
	                <%-- 입찰구분이 [공사], [용역] 일 경우 활성화 END --%>
	                <tr>
	                    <th scope="row">입찰한도액</th>
	                    <td colspan="3">
	                        ${comFn:formatMoney(bidPlanAprvlDetail.BID_LMT_AMOUNT)} 원
	                    </td>
	                </tr>
			    </table>
			</div>
		
		
			<h4 class="tit">입찰방법</h4>
			<div class="view_area">
				<table>
			    	<colgroup>
			        	<col style="width: 15%;">						        
			        	<col style="width: 35%;">						        
			        	<col style="width: 15%;">						        
			        	<col style="width: 35%;">
			        </colgroup>
		            <tr>
		                <th scope="row">계약방법</th>
		                <td>
		                    ${bidPlanAprvlDetail.CNTRCT_MTH_CD_NM}
		                </td>
		                <th scope="row">낙찰자선정방법</th>
		                <td>
		                     ${bidPlanAprvlDetail.SCSBID_MTH_CD_NM}
		                </td>
		            </tr>
		            <tr>
		                <th scope="row">복수예가여부</th>
		                <td <c:if test="${bidPlanAprvlDetail.SCSBID_MTH_CD ne '31'}">colspan="3"</c:if>>
		                    ${bidPlanAprvlDetail.COMPNO_PRDPRC_SE_NM}
		                </td>
		                <%-- 낙찰자 선정 방법이 [최저가입찰가순 적격심사] 일 경우에 활성화  --%>
		                <c:if test="${bidPlanAprvlDetail.SCSBID_MTH_CD eq '31'}">
		                <th scope="row">낙찰하한율</th>
		                <td>
		                    ${bidPlanAprvlDetail.SCSBID_LWLT_RT}
		                </td>
		                </c:if>
		                <%-- 낙찰자 선정 방법이 [최저가입찰가순 적격심사] 일 경우에 활성화  END --%>
		            </tr>
		            <tr>
		                <th scope="row">입찰설명회여부</th>
		                <td <c:if test="${bidPlanAprvlDetail.BID_DC_PEO_CD eq 'BPNN'}">colspan="3"</c:if>>
		                    ${bidPlanAprvlDetail.BID_DC_PEO_CD_NM}
		                </td>
		                <c:if test="${bidPlanAprvlDetail.BID_DC_PEO_CD ne 'BPNN'}">
		                <th scope="row">입찰설명회장소</th>
		                <td>${bidPlanAprvlDetail.BID_DC_PEO_PLACE_NM}</td>
		                </c:if>
		            </tr>
		            <tr>
		                <th scope="row">입찰보증금 납부형태</th>
		                <td colspan="3">
		                	<label for="P_BID_GTN_PAY_STLE_CN" class="blind">입찰보증금 납부형태</label>
		                	<textarea id="P_BID_GTN_PAY_STLE_CN" readonly taView style="display: none;"><c:out value="${bidPlanAprvlDetail.BID_GTN_PAY_STLE_CN}"></c:out></textarea>
		                </td>
		            </tr>
		            <c:if test="${bidPlanAprvlDetail.CNTRCT_MTH_CD eq '10000' or bidPlanAprvlDetail.CNTRCT_MTH_CD eq '10002'}">
		            <tr>
		                <th scope="row">입찰참가자격</th>
		                <td colspan="3">
		                	<label for="P_BID_PARTCPT_QUALF_CN" class="blind">입찰참가자격</label>
		                	<textarea id="P_BID_PARTCPT_QUALF_CN" readonly taView style="display: none;"><c:out value="${bidPlanAprvlDetail.BID_PARTCPT_QUALF_CN}"></c:out></textarea>
		                </td>
		            </tr>
		            </c:if>
		            <tr>
		                <th scope="row">공동수급 여부</th>
		                <td colspan="3">
		                    ${bidPlanAprvlDetail.COPERTN_SPLDMD_DUTY_SE_CD_NM}
		                </td>
		            </tr>
		            <%-- 낙찰자선정방법이 [협상에 의한 계약(기술평가)], [협상에 의한 계약(기술가격종합평가)], [기술.가격분리(기술가격입찰동시-최저가)] 일 경우 활성화 --%>
		            <c:if test="${bidPlanAprvlDetail.SCSBID_MTH_CD eq '40' }">
		             <tr>
		                 <th scope="row">제안서제출방법</th>
		                 <td>
		                     ${bidPlanAprvlDetail.PRPR_MTH_CD_NM}
		                 </td>
		                 <th scope="row">온라인제출필수여부</th>
		                 <td>
		                     ${bidPlanAprvlDetail.ONPR_ESSNTL_AT_NM}
		                 </td>
		             </tr>
		             <tr>
		                 <th scope="row">기술평가 담당자</th>
		                 <td colspan="3">${bidPlanAprvlDetail.TCHQVLN_CHARGER_NM}</td>
		             </tr>
		            </c:if>
		            <%-- 낙찰자선정방법이 [협상에 의한 계약(기술평가)], [협상에 의한 계약(기술가격종합평가)], [기술.가격분리(기술가격입찰동시-최저가)] 일 경우 활성화 END --%>
			    </table>
			</div>
		
			<c:if test="${bidPlanAprvlDetail.CNTRCT_MTH_CD eq '10001' and bidPlanDetail.CONT_MTCD ne '10005'}">
				<h4 class="tit">지명업체 정보</h4>
				<div class="view_area">
					<table class="tableList" summary="지명업체 정보입니다.">
						<caption>지명업체정보</caption>
						<colgroup>
							<col width="5%"/>
		                   	<col width="20%"/>
		                   	<col width="*"/>
		                   	<col width="20%"/>
						</colgroup>
						<thead>
							<tr>
								<th class="txtc">번호</th>
			                 	<th class="txtc">사업자번호</th>
			                 	<th class="txtc">업체명</th>
			                 	<th class="txtc">대표자명</th>
			             	</tr>
						</thead>
						<tbody>
							<c:if test="${empty bidNmfpcEntrpsList}">
								<tr>
									<td colspan="4" class="txtc">지명업체 정보가 없습니다.</td>
								</tr>
							</c:if>
							<c:if test="${not empty bidNmfpcEntrpsList}">
								<c:forEach var="data" items="${bidNmfpcEntrpsList}" varStatus="status" >
									<tr>
										<td class="txtc">${status.count}</td>
										<td class="txtc">${data.BIZRNO}</td>
										<td>${data.ENTRPS_NM}</td>
										<td>${data.RPRSNTV_NM}</td>
									</tr>
								</c:forEach>
							</c:if>
						</tbody>
					</table>
				</div>
			</c:if>
		
			<h4 class="tit">계약조건</h4>
			<div class="view_area">
				<table>
			    	<colgroup>
			        	<col style="width: 15%;">						        
			        	<col style="width: 35%;">						        
			        	<col style="width: 15%;">						        
			        	<col style="width: 35%;">
			        </colgroup>
			        <tr class="line">
						<th scope="row">지체상금률(%)</th>
						<td>
							${bidPlanAprvlDetail.DFRCMPST_RT} /1,000
						</td>
						<th scope="row">하자이행보증금률</th>
						<td>
							${bidPlanAprvlDetail.FLAW_FLFL_GTN_RT}&nbsp;%
						</td>
					</tr>
					<tr>
						<th scope="row">계약보증금률</th>
						<td <c:if test="${bidPlanAprvlDetail.MHRML_INCLS_AT ne 'Y'}">colspan="3"</c:if>>
							${bidPlanAprvlDetail.CRYMY_RT}&nbsp;%
						</td>
						<c:if test="${bidPlanAprvlDetail.MHRML_INCLS_AT eq 'Y'}">
							<th scope="row">물품인도조건</th>
							<td>
								${bidPlanAprvlDetail.THNG_DELY_CND_CD_NM}
							</td>
						</c:if>
					</tr>
					<c:if test="${bidPlanAprvlDetail.MHRML_INCLS_AT eq 'Y'}">
						<tr>
							<th scope="row">납품장소</th>
							<td colspan="3">
								<label for="P_DVYFG_PLACE_NM" class="blind">납품장소</label>
								<textarea id="P_DVYFG_PLACE_NM" readonly taView style="display: none;"><c:out value="${bidPlanAprvlDetail.DVYFG_PLACE_NM}"></c:out></textarea>
							</td>
						</tr>
						<tr>
							<th scope="row">납품기한</th>
							<td colspan="3">
								<label for="P_DVYFG_PD_CN" class="blind">납품기한</label>
								<textarea id="P_DVYFG_PD_CN" readonly taView style="display: none;"><c:out value="${bidPlanAprvlDetail.DVYFG_PD_CN}"></c:out></textarea>
							</td>
						</tr>
						<tr>
							<th scope="row">설치기한</th>
							<td colspan="3">
								<label for="P_INSTL_TMLMT_CN" class="blind">설치기한</label>
								<textarea id="P_INSTL_TMLMT_CN" readonly taView style="display: none;"><c:out value="${bidPlanAprvlDetail.INSTL_TMLMT_CN}"></c:out></textarea>
							</td>
						</tr>
					</c:if>
			    </table>
			</div>
	
			<h4 class="tit">입찰진행순서</h4>
			<div class="view_area">
				<table>
			    	<colgroup>
			        	<col style="width: 15%;">						        
			        	<col style="width: 85%;">
			        </colgroup>
			        <tr class="line">
						<th scope="row">입찰공고 일시</th>
						<td>${comFn:formatDate(bidPlanAprvlDetail.PBLANC_DT,'yyyyMMddHHmmss','yyyy-MM-dd HH:mm')}</td>
					</tr>
					<c:if test="${bidPlanAprvlDetail.BID_DC_PEO_CD ne 'BPNN'}">
						<tr>
		                    <th scope="row">입찰설명회 일시</th>
		                    <td>${comFn:formatDate(bidPlanAprvlDetail.BID_DC_PEO_DT,'yyyyMMddHHmmss','yyyy-MM-dd HH:mm')}</td>
		                </tr>
					</c:if>
		            <tr>
		                <th scope="row">참가신청서 제출기간</th>
		                <td>${comFn:formatDate(bidPlanAprvlDetail.PARE_BEGIN_DT,'yyyyMMddHHmmss','yyyy-MM-dd HH:mm')} ~ ${comFn:formatDate(bidPlanAprvlDetail.PARE_END_DT,'yyyyMMddHHmmss','yyyy-MM-dd HH:mm')}</td>
		            </tr>
		            <tr>
		                <th scope="row">입찰서 제출기간</th>
		                <td>${comFn:formatDate(bidPlanAprvlDetail.BIPA_PRESENTN_BEGIN_DT,'yyyyMMddHHmmss','yyyy-MM-dd HH:mm')} ~ ${comFn:formatDate(bidPlanAprvlDetail.BIPA_PRESENTN_END_DT,'yyyyMMddHHmmss','yyyy-MM-dd HH:mm')}</td>
		            </tr>
		            <tr>
		                <th scope="row">개찰일시</th>
		                <td>${comFn:formatDate(bidPlanAprvlDetail.OPENG_DT,'yyyyMMddHHmmss','yyyy-MM-dd HH:mm')}</td>
		            </tr>
			    </table>
			</div>
	
			<%--입찰구분이 [구매]일 경우에만 보여짐 --%>
			<c:if test="${bidPlanAprvlDetail.PRCURE_SE_CD eq '0'}">
			 	<h4 class="tit">입찰품목정보</h4>
			 	<div class="view_area">
					<table class="tableList" summary="입찰품목정보 입니다">
			            <caption>입찰품목정보</caption>
			            <colgroup>
			                <col width="5%"/>
		                   	<col width="20%"/>
		                   	<col width="25%"/>
		                   	<col width="*"/>
		                   	<col width="10%"/>
		                   	<col width="10%"/>
			            </colgroup>
						<thead>
			                <tr>
			                    <th class="txtc">번호</th>
			                    <th class="txtc">품명번호</th>
			                    <th class="txtc">품명</th>
			                    <th class="txtc">참조사항</th>
			                    <th class="txtc">단위</th>
			                    <th class="txtc">수량</th>
			                </tr>
			            </thead>
						<tbody>
							<c:if test="${empty bidPrdlsList}">
								<tr>
									<td colspan="6" class="txtc">입찰품목 정보가 없습니다.</td>
								</tr>
							</c:if>
							<c:if test="${not empty bidPrdlsList}">
								<c:forEach var="data" items="${bidPrdlsList}" varStatus="status" >
								<tr>
									<td class="txtc">${status.count}</td>
									<td class="txtc">${data.THNG_NO}</td>
									<td>${data.QLY_NM}</td>
									<td>${data.PRDLST_REFRN_CN}</td>
									<td class="txtc">${data.G2B_UNIT_NM}</td>
									<td class="txtr">${comFn:formatMoney(data.PRDLST_QY)}</td>
								</tr>
								</c:forEach>
							</c:if>
						</tbody>
					</table>
				</div>
			</c:if>
		
			<h4 class="tit">첨부파일</h4>
			<div class="view_area">
				<table>
			    	<colgroup>
			        	<col style="width: 15%;">						        
			        	<col style="width: 85%;">						        
			        </colgroup>
			        <tr>
			        	<th scope="row" class="vtop">첨부파일</th>
						<td class="vtop">
							<div id="fileViewDiv" style="width: 680px; line-height: 30px;"> 
								<c:if test="${not empty bidAtchDocList }">
		                  			<c:forEach items="${bidAtchDocList }" var="data" varStatus="status">
			                   			<div style="height: 30px;"> 
			                   				<a href="javascript:pageObj.download('${data.ATCHMNFL_SN}');" class="attfile">${data.ATCHMNFL_NM }</a>
			                    		</div>
			                    	</c:forEach> 
			                    </c:if>
			                    <c:if test="${empty bidAtchDocList}">
						        	<div style="height: 30px;"> 입찰첨부파일 정보가 없습니다.</div>
						        </c:if>
		                   	</div>
						</td>	
		             </tr>
			    </table>
			</div>
		
			<h4 class="tit">담당자 정보</h4>
			<div class="view_area">
				<table>
			    	<colgroup>
			        	<col style="width: 15%;">						        
			        	<col style="width: 35%;">						        
			        	<col style="width: 15%;">						        
			        	<col style="width: 35%;">
			        </colgroup>
		            <tr class="line">
		                <th scope="row">입찰담당자</th>
		                <td>
		                    ${bidPlanAprvlDetail.BID_CHARGER_NM}
		                </td>
		                <th scope="row">사업담당자(의뢰자)</th>
		                <td>
		                    ${bidPlanAprvlDetail.BID_CLIENT_NM}
		                </td>
		            </tr>
		            <tr>
		                <th scope="row">입찰담당자 이메일</th>
		                <td>
		                    ${bidPlanAprvlDetail.BID_CHARGER_EMAIL_ADRES}
		                </td>
		                <th scope="row">사업담당자 이메일</th>
		                <td>
		                    ${bidPlanAprvlDetail.BID_CLIENT_EMAIL_ADRES}
		                </td>
		            </tr>
			    </table>
			</div>
			<div class="btn_wrap view_btn">
				<input type="hidden" id="P_BID_DC_PEO_CD" value="${bidPlanAprvlDetail.BID_DC_PEO_CD}">
				<c:if test="${bidPlanAprvlDetail.BID_DETAIL_PRST_CD eq 'PE90' || bidPlanAprvlDetail.BID_DETAIL_PRST_CD eq 'PE91'}">
					<button type="button" class="btn btn_02 btn_revise" onclick="pblancSttu2('PF10','PE92');">결제반려(임시)</button>
					<button type="button" class="btn btn_02 btn_revise" onclick="pblancSttu2('PF10','PE94');">결제완료(임시)</button>
				</c:if>
				<c:if test="${bidPlanAprvlDetail.BID_DETAIL_PRST_CD eq 'PE92'}">
					<button type="button" class="btn btn_02 btn_revise" onclick="pblancSttu2('PF10','PE90');">결제기안</button>
			        <button type="button" class="btn btn_02 btn_revise" id="updateBtn" >수정</button>	
	        	</c:if>
	        	<c:if test="${bidPlanAprvlDetail.BID_DETAIL_PRST_CD eq 'PE94'}">
	         		<button type="button" class="btn btn_02 btn_revise" id="pblancBtn" onclick="pblancSttu();">입찰공고(임시)</button>
	         	</c:if>
		    	<button type="button" class="btn btn_02 btn_sch" id="listBtn" >목록</button>
		    </div>
		</div>
	</div>
</div>
<form id="listFrm" method="POST">
	<input type="hidden" name="resourceName" value="${param.resourceName}">
</form>

<form id="updtFrm" method="POST">
	<input type="hidden" name="resourceName" value="${param.resourceName}">
	<input type="hidden" name="P_PBLANC_NO" value="${bidPlanAprvlDetail.PBLANC_NO}">
	<input type="hidden" name="P_PBLANC_ODR" value="${bidPlanAprvlDetail.PBLANC_ODR}">
	<input type="hidden" name="P_USER_ID" value="${sessionScope.loginResult.USER_ID}" >
</form>

<form id="sttusFrm" method="POST" >
	<input type="hidden" name="resourceName" value="${param.resourceName}">
	<input type="hidden" name="P_PBLANC_NO" value="${bidPlanAprvlDetail.PBLANC_NO}">
	<input type="hidden" name="P_PBLANC_ODR" value="${bidPlanAprvlDetail.PBLANC_ODR}">
	<input type="hidden" name="P_USER_ID" value="${sessionScope.loginResult.USER_ID}" >
	<input type="hidden" name="P_BID_PROGRS_STTUS_CD">
	<input type="hidden" name="P_BID_DETAIL_PRST_CD">
</form>

<form id="downFrm" method="POST" >
	<input type="hidden" name="P_ATCHMNFL_SN">
</form>
