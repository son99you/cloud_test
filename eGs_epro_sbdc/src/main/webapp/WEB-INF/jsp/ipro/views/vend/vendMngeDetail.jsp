<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 협력사현황 상세
 *
 * <pre>
 * vend 
 *    |_ vendMngeDetail.jsp
 * 
 * </pre>
 * @date : 2017. 06. 13
 * @version : 1.0
 * @author : 은우소프트 홍찬일
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %> 
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %> 

<script type="text/javascript" src="${jsPath}/ipro/views/vend/vendMngeDetail.js"></script> 
 
<div class="conts_wrap">
	<div class="inner">
		<div class="tit_wrap">
			<h3 class="tit">협력사상세</h3>
			<ul class="step_wrap">
				<li><a href="#">${bigMenuNm}</a></li>
				<li><a href="#">${smallMenuNm}</a></li>
			</ul> <!--// step_wrap E -->
		</div> <!--// tit_wrap E -->
		<form id="detailFrm" name="detailFrm" method="POST" enctype="multipart/form-data">
			<input type="hidden" id="resourceName" name="resourceName" value="${param.resourceName}">
			<input type="hidden" id="resourceDesc" name="resourceDesc" value="${param.resourceDesc}">
			<input type="hidden" name="P_MAIN_SG" value=""/>
			<input type="hidden" name="P_VEND_REG_NO" value="${vendMngeDetail.VEND_REG_NO }"/>
			
			<c:if test="${ not empty vendMngeCttAcqsList }">
				<c:forEach var="data" items="${vendMngeCttAcqsList}" varStatus="status" >
					<input type="hidden" id="P_ACQS_SN_${data.ACQS_SN }" name="ACQS_SN" value="${data.ACQS_SN }" style="width: 90%;">
					<input type="hidden" id="P_CTT_NM_${data.ACQS_SN }" name="CTT_NM" value="${data.CTT_NM }" style="width: 90%;">
					<input type="hidden" id="P_ACQS_DE_${data.ACQS_SN }"  name="ACQS_DE" value="${data.ACQS_DE }" style="width: 90%;">
					<input type="hidden" id="P_ACQS_AGNM_${data.ACQS_SN }"  name="ACQS_AGNM" value="${data.ACQS_AGNM }" style="width: 90%;">
				</c:forEach>
			</c:if>
			
			<input type="hidden" id="P_DELETE_FILE_SN" name="P_DELETE_FILE_SN" value="">
			
			<input type="hidden" id="P_FILE_GRP_NO_A" name="P_FILE_GRP_NO_A" value="${P_FILE_GRP_NO_A }">
			<input type="hidden" id="P_FILE_GRP_NO_B" name="P_FILE_GRP_NO_B" value="${P_FILE_GRP_NO_B }">
			<input type="hidden" id="P_FILE_GRP_NO_C" name="P_FILE_GRP_NO_C" value="${P_FILE_GRP_NO_C }">
			<input type="hidden" id="P_FILE_GRP_NO_D" name="P_FILE_GRP_NO_D" value="${P_FILE_GRP_NO_D }">
			<input type="hidden" id="P_FILE_GRP_NO_E" name="P_FILE_GRP_NO_E" value="${P_FILE_GRP_NO_E }">
			<input type="hidden" id="P_FILE_GRP_NO_F" name="P_FILE_GRP_NO_F" value="${P_FILE_GRP_NO_F }">
			<input type="hidden" id="P_FILE_GRP_NO_G" name="P_FILE_GRP_NO_G" value="${P_FILE_GRP_NO_G }">
			
	       	<div class="tab_wrap01" style="margin-bottom: 30px;">
				<ul class="tab_tit">
					<li><a class="tapBtn on" id="1" href='javascript:tabEvent(1);' >기본정보</a></li>
					<li><a class="tapBtn" id="2" href='javascript:tabEvent(2);'>상세정보</a></li>
					<li><a class="tapBtn" id="3" href='javascript:tabEvent(3);'>계약실적</a></li>
					<li><a class="tapBtn" id="4" href='javascript:tabEvent(4);'>입찰정보</a></li>
				</ul>
			</div>
				
			<div class="view_wrap typeA">
				<!-- 기본정보 TAB START -->
				<div id="basic">
					<div class="view_area">
						<table>
							<colgroup>
								<col style="width: 170px;">
								<col style="width: 349px;">
								<col style="width: 170px;">
								<col style="width: auto;">
							</colgroup>
							<tr height="24">
								<th class=" txtl"> 회사명</th>
								<td  colspan="3">${vendMngeDetail.VEND_NM}</td>
							</tr>
							<tr height="24">
							    <th class=" txtl"> 사업자등록번호</th>
								<td  >${comFn:formatBizNumber(vendMngeDetail.BIZRNO) }</td>
								<th class=" txtl"> 법인번호</th>
								<td>
									<input type="text" id="P_CORP_REG_NO" name="P_CORP_REG_NO" value="${vendMngeDetail.CORP_REG_NO}" style="width: 40%;">
								</td>
							</tr>
							
							<tr height="24">
							    <th class=" txtl"> 대표이사</th>
								<td  colspan="3">
									<input type="text" id="P_RPRS_NM" name="P_RPRS_NM" value="${vendMngeDetail.RPRS_NM}" style="width: 40%;">
								</td>
							</tr>
							
							<tr height="24">
							    <th class=" txtl"> 업태</th>
								<td>
									<input type="text" id="P_BCNM" name="P_BCNM" value="${vendMngeDetail.BCNM}" style="width: 85%;">
								</td>
								<th class=" txtl"> 종목</th>
								<td>
									<input type="text" id="P_BTNM" name="P_BTNM" value="${vendMngeDetail.BTNM}" style="width: 85%;">
								</td>
							</tr>
						</table>
						
						<table>
							<colgroup>
								<col style="width: 170px;">
								<col style="width: 349px;">
								<col style="width: 170px;">
								<col style="width: auto;">
							</colgroup>
							<tr height="24">
								<th class=" txtl"> 회사설립일</th>
								<td>
									<div class="calendar_wrap">
										<input type="text" id="P_ESTB_YR" name="P_ESTB_YR" value="${vendMngeDetail.ESTB_YR}" style="width: 50%;" date>
									</div>
								</td>
								
								<th class=" txtl bullet_orange"> 파트너유형</th>
								<td>
									<div class="selectLayer2 w_120">
			                			<comTag:comCmcdCdValueComboBox id="P_PRTN_TYCD" name="P_PRTN_TYCD" selectKey="${vendMngeDetail.PRTN_TYCD}" cdId="20005"  headerKey="" headerValue="선택" />
			                		</div>
								</td>
		
							</tr>
							<tr height="24">
							    <th class=" txtl"> 홈페이지주소</th>
								<td  >
									<input type="text" id="P_HMPG_ADDR" name="P_HMPG_ADDR" value="${vendMngeDetail.HMPG_ADDR }" style="width: 85%;">
								</td>
								<th class=" txtl"> 이메일주소</th>
								<td  >
									<input type="text" id="P_EMAL_ADDR1" value="${vendMngeDetail.EMAL_ADDR }" name="P_EMAL_ADDR1" style="width: 85%;" >
								</td>
							</tr>
							
							<tr height="24">
							    <th class=" txtl"> 주소</th>
								<td  colspan="3">
									<input type="text" id="P_ZIP" name="P_ZIP" value="${vendMngeDetail.ZIP }" style="width: 10%;" readonly="readonly">
		                            <button type="button" class="btn btn_02 btn_sch" id="zipBtn" name="zipBtn"> 우편번호</button>
				                            
									<br>
									<input type="text" id="P_ADDR_NM" name="P_ADDR_NM" value="${vendMngeDetail.ADDR_NM }" style="width: 30%;">
									<input type="text" id="P_ADDR_DENM" name="P_ADDR_DENM" value="${vendMngeDetail.ADDR_DENM }" style="width: 30%;">
								</td>
							</tr>
							
							<tr height="24">
							    <th class=" txtl"> 전화번호</th>
								<td  >
									<input type="text" id="P_TEL_NO1" name="P_TEL_NO1" value="${vendMngeDetail.TEL_NO }" style="width: 85%;">
								</td>
								<th class=" txtl"> FAX번호</th>
								<td  >
									<input type="text" id="P_FX_NO" name="P_FX_NO" value="${vendMngeDetail.FX_NO }" style="width: 85%;" >
								</td>
							</tr>
							
							<tr height="24">
								<th class=" txtl"> 주거래은행</th>
								<td>
									<input type="text" id="P_BKNM" name="P_BKNM" value="${vendMngeDetail.BKNM}" style="width: 25%;">
								</td>
							    <th class=" txtl"> 은행지점</th>
								<td>
									<input type="text" id="P_BNK_BRNC_NM" name="P_BNK_BRNC_NM" value="${vendMngeDetail.BNK_BRNC_NM}" style="width: 25%;">
								</td>
							</tr>
							
							<tr height="24">
							    <th class=" txtl"> 신용등급</th>
								<td>
									<input type="text" id="P_CRDT_ESTM_RKCD" name="P_CRDT_ESTM_RKCD" value="${vendMngeDetail.CRDT_ESTM_RKCD }" style="width: 25%;" >
								</td>
								<th class=" txtl"> 기업신용평가회사</th>
								<td>
									<input type="text" id="P_CRDT_ESTM_CMPY" name="P_CRDT_ESTM_CMPY" value="${vendMngeDetail.CRDT_ESTM_CMPY }" style="width: 25%;">
								</td>
							</tr>
							
							<tr height="24">
								<th class=" txtl"> 영업장소재지</th>
								<td  colspan="3">
									<input type="text" id="P_CMPP_LCTN" name="P_CMPP_LCTN" value="${vendMngeDetail.CMPP_LCTN }" style="width: 25%;">
								</td>
							</tr>
						</table>
					</div>

					<div class="tit_area">						
						<h4 class="tit" style="clear: both;">SG분류</h4>
						<div class="btn_right">
							<button type="button" class="btn btn_02 btn_c2" id="sgPopupBtn">SG설명</button>
							<button type="button" class="btn btn_02 btn_c2" id="sgAddBtn">추가</button>
				  		</div>
			  		</div>
					<div class="view_area" id="sgDiv">
						<table>
							<colgroup>
								<col style="width: 33%;">
								<col style="width: 33%;">
								<col style="width: auto;">
							</colgroup>
							<tr height="24">
								<th class="txtc">SG명</th>
								<th class="txtc">주거래SG</th>
								<th class="txtc">삭제</th>
							</tr>
							<tbody id="sgAddHiddTbdy">
								<tr height="24" style="display:none; width: 100%;" id="copySg">
									<td class="contd txtc" style="width: 33%">
										<div class="selectLayer2 w_120">
				                			<comTag:comCmcdCdValueComboBox id="P_SG_CD" name="P_SG_CD" selectKey="" cdId="N00001"  headerKey="" headerValue="선택" />
				                		</div>
									</td>
									<td class="contd txtc" style="width: 33%">
										<input type="checkbox" id="P_MAIN_SG" name="P_MAIN_SG_CHK" onclick="mainSgEvent(this);">
										<input type="hidden" name="P_MAIN_SG_YN"> 
									</td>
									<td class="contd txtc" style="width: 33%">
										<button class='btn btn_02 btn_sch' id='deleteBtn' onclick='deleteFn(this);' >삭제</button>
									</td>
								</tr>
							</tbody>
								
							<tbody id="sgAddShowTbdy">
								<c:if test="${ empty vendMngeSgCodeList}">
									<tr height="24" style="width: 100%;">
										<td class="contd txtc" style="width: 33%">
											<div class="selectLayer2 w_120">
					                			<comTag:comCmcdCdValueComboBox id="P_SG_CD" name="P_SG_CD" selectKey="" cdId="N00001"  headerKey="" headerValue="선택" />
					                		</div>
										</td>
										<td class="contd txtc" style="width: 33%">
											<input type="checkbox" id="P_MAIN_SG" name="P_MAIN_SG_CHK" onclick="mainSgEvent(this);" checked="checked">
											<input type="hidden" name="P_MAIN_SG_YN"> 
										</td>
										<td class="contd txtc" style="width: 33%">
											<button class='btn btn_02 btn_sch' id='deleteBtn' onclick='deleteFn(this);' >삭제</button>
										</td>
									</tr>
								</c:if>
								
								<c:if test="${ not empty vendMngeSgCodeList}">
									<c:forEach var="data" items="${vendMngeSgCodeList}" varStatus="status" >
										<tr height="24" style="width: 100%;">
											<td class="contd txtc" style="width: 33%">
												<div class="selectLayer2 w_120">
						                			<comTag:comCmcdCdValueComboBox id="P_SG_CD" name="P_SG_CD" selectKey="${data.SG_CD}" cdId="N00001"  headerKey="" headerValue="선택" />
						                		</div>
											</td>
											<td class="contd txtc" style="width: 33%">
												<input type="checkbox" id="P_MAIN_SG" name="P_MAIN_SG_CHK" <c:if test="${data.MAIN_SG_YN eq 'Y'}">checked</c:if> onclick="mainSgEvent(this);">
												<input type="hidden" name="P_MAIN_SG_YN"> 
											</td>
											<td class="contd txtc" style="width: 33%">
												<button class='btn btn_02 btn_sch' id='deleteBtn' onclick='deleteFn(this);' >삭제</button>
											</td>
										</tr>
									</c:forEach>
								</c:if>
							</tbody>
						</table>
					</div>
					
					<div class="view_area">
						
						<table>
							<colgroup>
								<col style="width: 170px;">
								<col style="width: auto;">
							</colgroup>
		
							<tr height="24">
								<th class=" txtl" > 관심분야</th>
								<td  >
									<input type="checkbox" name="P_CCN_SPHE_CHK" class="checkbox" <c:if test="${vendMngeDetail.CCN_SPHE_1 eq 'Y'}">checked</c:if>> 공사&nbsp;&nbsp;
									<input type="checkbox" name="P_CCN_SPHE_CHK" class="checkbox" <c:if test="${vendMngeDetail.CCN_SPHE_2 eq 'Y'}">checked</c:if>> 용역&nbsp;&nbsp;
									<input type="checkbox" name="P_CCN_SPHE_CHK" class="checkbox" <c:if test="${vendMngeDetail.CCN_SPHE_3 eq 'Y'}">checked</c:if>> 물품&nbsp;&nbsp;
									<input type="checkbox" name="P_CCN_SPHE_CHK" class="checkbox" <c:if test="${vendMngeDetail.CCN_SPHE_4 eq 'Y'}">checked</c:if>> 임대차 &nbsp;&nbsp;
									<input type="checkbox" name="P_CCN_SPHE_CHK" class="checkbox" <c:if test="${vendMngeDetail.CCN_SPHE_5 eq 'Y'}">checked</c:if>> 매각
									
									<input type="hidden" name="P_CCN_SPHE">
								</td>
							</tr>
							
							<tr height="">
								<th class=" txtl" > 기술분야</th>
								<td  >
									<input type="checkbox" name="P_TCHN_SPHE_CHK" class="checkbox" <c:if test="${vendMngeDetail.TCHN_SPHE_1 eq 'Y'}">checked</c:if>> AFC&nbsp;&nbsp;
									<input type="checkbox" name="P_TCHN_SPHE_CHK" class="checkbox" <c:if test="${vendMngeDetail.TCHN_SPHE_2 eq 'Y'}">checked</c:if>> 건축&nbsp;&nbsp;
									<input type="checkbox" name="P_TCHN_SPHE_CHK" class="checkbox" <c:if test="${vendMngeDetail.TCHN_SPHE_3 eq 'Y'}">checked</c:if>> 토목&nbsp;&nbsp;
									<input type="checkbox" name="P_TCHN_SPHE_CHK" class="checkbox" <c:if test="${vendMngeDetail.TCHN_SPHE_4 eq 'Y'}">checked</c:if>> 통신&nbsp;&nbsp;
									<input type="checkbox" name="P_TCHN_SPHE_CHK" class="checkbox" <c:if test="${vendMngeDetail.TCHN_SPHE_5 eq 'Y'}">checked</c:if>> 전차선&nbsp;&nbsp;
									<input type="checkbox" name="P_TCHN_SPHE_CHK" class="checkbox" <c:if test="${vendMngeDetail.TCHN_SPHE_6 eq 'Y'}">checked</c:if>> 송변선&nbsp;&nbsp;
									<input type="checkbox" name="P_TCHN_SPHE_CHK" class="checkbox" <c:if test="${vendMngeDetail.TCHN_SPHE_7 eq 'Y'}">checked</c:if>> 전력&nbsp;&nbsp;
									<input type="checkbox" name="P_TCHN_SPHE_CHK" class="checkbox" <c:if test="${vendMngeDetail.TCHN_SPHE_8 eq 'Y'}">checked</c:if>> 기계&nbsp;&nbsp;
									<input type="checkbox" name="P_TCHN_SPHE_CHK" class="checkbox" <c:if test="${vendMngeDetail.TCHN_SPHE_9 eq 'Y'}">checked</c:if>> 선로&nbsp;&nbsp;
									<br>
									<input type="checkbox" name="P_TCHN_SPHE_CHK" class="checkbox" <c:if test="${vendMngeDetail.TCHN_SPHE_10 eq 'Y'}">checked</c:if>> 전동차&nbsp;&nbsp;
									<input type="checkbox" name="P_TCHN_SPHE_CHK" class="checkbox" <c:if test="${vendMngeDetail.TCHN_SPHE_11 eq 'Y'}">checked</c:if>> 신호&nbsp;&nbsp;
									<input type="checkbox" name="P_TCHN_SPHE_CHK" class="checkbox" <c:if test="${vendMngeDetail.TCHN_SPHE_12 eq 'Y'}">checked</c:if>> 광고홍보&nbsp;&nbsp;
									<input type="checkbox" name="P_TCHN_SPHE_CHK" class="checkbox" <c:if test="${vendMngeDetail.TCHN_SPHE_13 eq 'Y'}">checked</c:if>> 역무&nbsp;&nbsp;
									<input type="checkbox" name="P_TCHN_SPHE_CHK" class="checkbox" <c:if test="${vendMngeDetail.TCHN_SPHE_14 eq 'Y'}">checked</c:if>> 경영&nbsp;&nbsp;
									<input type="checkbox" name="P_TCHN_SPHE_CHK" class="checkbox" <c:if test="${vendMngeDetail.TCHN_SPHE_15 eq 'Y'}">checked</c:if>> 안전&nbsp;&nbsp;
									<input type="checkbox" name="P_TCHN_SPHE_CHK" class="checkbox" <c:if test="${vendMngeDetail.TCHN_SPHE_16 eq 'Y'}">checked</c:if>> 단순구매&nbsp;&nbsp;
									<input type="checkbox" name="P_TCHN_SPHE_CHK" class="checkbox" <c:if test="${vendMngeDetail.TCHN_SPHE_17 eq 'Y'}">checked</c:if>> 보수정비&nbsp;&nbsp;
									<input type="checkbox" name="P_TCHN_SPHE_CHK" class="checkbox" <c:if test="${vendMngeDetail.TCHN_SPHE_18 eq 'Y'}">checked</c:if>> 기타&nbsp;&nbsp;
									
									<input type="hidden" name="P_TCHN_SPHE">
								</td>
			
							</tr>
						</table>
					</div>
					
					<div class="tit_area">
						<h4 class="tit" style="clear: both;">담당자등록</h4>
						<div class="btn_right">
					   		<button type="button" class="btn btn_02 btn_c2" id="userAddBtn" >추가</button>
					   	</div>
				   	</div>
					<div class="view_area" id="userDiv">
						<table>
							<colgroup>
								<col style="width: 13%;">
			                   	<col style="width: 15%;">
			                   	<col style="width: 15%;">
			                   	<col style="width: 15%;">
			                   	<col style="width: 20%;">
			                   	<col style="width: 15%;">
			                   	<col style="width: 7%;">
							</colgroup>
							<thead>
								<tr>
									<th class="txtc">담당자명</th>
									<th class="txtc">담당자직급</th> 
									<th class="txtc">연락처</th> 
									<th class="txtc">연락처(휴대폰)</th> 
									<th class="txtc">이메일</th> 
									<th class="txtc">부서명</th> 
									<th class="txtc">삭제</th> 
								</tr>
							</thead>
							
							<tbody id="userAddHiddTbdy">
								<tr height="24" style="display:none; width: 100%;" id="copySg">
									<td class="contd txtc">
										<input type="text" id="P_USR_NM" name="P_USR_NM">
									</td>
									<td class="contd txtc">
										<input type="text" id="P_OPNM" name="P_OPNM">
									</td>
									<td class="contd txtc">
										<input type="text" id="P_TEL_NO2" name="P_TEL_NO2">
									</td>
									<td class="contd txtc">
										<input type="text" id="P_CP_NO" name="P_CP_NO">
									</td>
									<td class="contd txtc">
										<input type="text" id="P_EMAL_ADDR2" name="P_EMAL_ADDR2" style="width: 85%;">
									</td>
									<td class="contd txtc">
										<input type="text" id="P_DEPT_NM" name="P_DEPT_NM">
									</td>
									<td class="contd txtc">
										<button class='btn btn_02 btn_sch' id='deleteBtn' onclick='deleteFn(this);' >삭제</button>
									</td>
								</tr>
							</tbody>
							
							<tbody id="userAddShowTbdy">
								<c:if test="${ empty vendMngeUserList }">
									<tr height="24">
										<td class="contd txtc">
											<input type="text" id="P_USR_NM" name="P_USR_NM">
										</td>
										<td class="contd txtc">
											<input type="text" id="P_OPNM" name="P_OPNM">
										</td>
										<td class="contd txtc">
											<input type="text" id="P_TEL_NO2" name="P_TEL_NO2">
										</td>
										<td class="contd txtc">
											<input type="text" id="P_CP_NO" name="P_CP_NO">
										</td>
										<td class="contd txtc">
											<input type="text" id="P_EMAL_ADDR2" name="P_EMAL_ADDR2" style="width: 85%;">
										</td>
										<td class="contd txtc">
											<input type="text" id="P_DEPT_NM" name="P_DEPT_NM">
										</td>
										<td class="contd txtc">
											<button class='btn btn_02 btn_sch' id='deleteBtn' onclick='deleteFn(this);' >삭제</button>
										</td>
									</tr>
								</c:if>
								
								<c:if test="${ not empty vendMngeUserList }">
									<c:forEach var="data" items="${vendMngeUserList}" varStatus="status" >
										<tr height="24">
											<td class="contd txtc">
												<input type="text" id="P_USR_NM" name="P_USR_NM" value="${data.USR_NM }">
											</td>
											<td class="contd txtc">
												<input type="text" id="P_OPNM" name="P_OPNM" value="${data.OPNM }">
											</td>
											<td class="contd txtc">
												<input type="text" id="P_TEL_NO2" name="P_TEL_NO2" value="${data.TEL_NO }">
											</td>
											<td class="contd txtc">
												<input type="text" id="P_CP_NO" name="P_CP_NO" value="${data.CP_NO }">
											</td>
											<td class="contd txtc">
												<input type="text" id="P_EMAL_ADDR2" name="P_EMAL_ADDR2" value="${data.EMAL_ADDR }" style="width: 85%;" >
											</td>
											<td class="contd txtc">
												<input type="text" id="P_DEPT_NM" name="P_DEPT_NM" value="${data.DEPT_NM }">
											</td>
											<td class="contd txtc">
												<button class='btn btn_02 btn_sch' id='deleteBtn' onclick='deleteFn(this);' >삭제</button>
											</td>
										</tr>
									</c:forEach>
								</c:if>
							</tbody>
						</table>
					</div>
					
					<div class="tit_area">
						<h4 class="tit" style="clear: both;">품목등록</h4>
						<div class="btn_right">
					   		<button type="button" class="btn btn_02 btn_c2" id="itemAddBtn" >추가</button>
					   	</div>
				   	</div>
					<div class="view_area">
						<table>
							<colgroup>
								<col style="width: 10%;">
			                   	<col style="width: 20%;">
			                   	<col style="width: 15%;">
			                   	<col style="width: 15%;">
			                   	<col style="width: 15%;">
			                   	<col style="width: 15%;">
			                   	<col style="width: 10%">
							</colgroup>
							<thead>
								<tr>
									<th class="txtc">품목번호</th>
					                <th class="txtc">대분류명</th>
									<th class="txtc">중분류명</th> 
									<th class="txtc">소분류명</th> 
									<th class="txtc">품목명</th> 
									<th class="txtc">단가</th> 
									<th class="txtc">삭제</th> 
								</tr>
							</thead>
							
							<tbody id="itemShowTbdy">
								<c:if test="${ not empty vendMngeItemList }">
									<c:forEach var="data" items="${vendMngeItemList}" varStatus="status" >
										<tr height="24">
											<td class='txtc'>
												<input type="text" id="P_ITEM_NO" name="P_ITEM_NO" value="${data.ITEM_NO }" style="width: 95%; text-align: center;">
											</td>
											<td class='txtc'>
												<input type="text" id="P_LLF_NM" name="P_LLF_NM" value="${data.LLF_NM }" style="width: 95%;">
											</td>
											<td class='txtc'>
												<input type="text" id="P_MLF_NM" name="P_MLF_NM" value="${data.MLF_NM }" style="width: 95%;">
											</td>
											<td class='txtc'>
												<input type="text" id="P_SLF_NM" name="P_SLF_NM" value="${data.SLF_NM }" style="width: 95%;">
											</td>
											<td class='txtc'>
												<input type="text" id="P_ITEM_NM" name="P_ITEM_NM" value="${data.ITEM_NM }" style="width: 95%;">
											</td>
											<td>
												<input type="text" id="P_ITEM_UPRC" name="P_ITEM_UPRC" value="${comFn:formatMoneyDp(data.ITEM_UPRC)}" onkeyup="$(this).val(Comma(unComma(this.value)));" style="width: 95%; text-align: right;">
											</td>
											<td class='txtc'>
												<button class='btn btn_02 btn_sch' id='deleteBtn' onclick='deleteFn(this);' >삭제</button>
											</td>
										</tr>
									</c:forEach>
								</c:if>
							</tbody>
							
							<tbody id="itemFrame">
							</tbody>
						</table>
					</div>
					
					<!-- 업체 제재 목록 -->
					<div class="tit_area">
						<h4 class="tit" style="clear: both;">업체제재등록</h4>
						<div class="btn_right">
					   		<button type="button" class="btn btn_02 btn_c2" onclick="punishAddRow();" >추가</button>
					   	</div>
				   	</div>
					<div class="view_area">
						<table>
							<colgroup>
								<col style="width: 10%;">
			                   	<col style="width: 25%;">
			                   	<col style="width: 20%;">
			                   	<col style="width: 20%;">
			                   	<col style="width: 20%;">
			                   	<col style="width: 5%;">
							</colgroup>
							<thead>
								<tr>
									<th class="txtc">제재상태</th>
									<th class="txtc">제재기간</th> 
									<th class="txtc">제재사유</th> 
									<th class="txtc">제재근거</th> 
									<th class="txtc">비고</th> 
									<th class="txtc">삭제</th> 
								</tr>
							</thead>
							<tbody id="punishAddHiddTbdy">
								<tr height="24" style="display:none; width: 100%;">
									<td class="contd txtc">
										<select id="P_SNCT_STCD" name="P_SNCT_STCD">
											<option selected="selected">선택</option>
											<option value="A">경고</option>
											<option value="B">입찰참가금지</option>
											<option value="C">정지</option>
											<option value="D">영구퇴출</option>
										</select>
									</td>
									<td class="contd txtc">
										<input type="text" class="date" style="width: 90px;" name="P_SNCT_STDE"> ~ <input type="text" class="date" style="width: 90px;" name="P_SNCT_ENDE">
									</td>
									<td class="contd txtc">
										<input type="text" id="P_SNCT_RSN_CNTN" name="P_SNCT_RSN_CNTN">
									</td>
									<td class="contd txtc">
										<input type="text" id="P_SNCT_BSS_CNTN" name="P_SNCT_BSS_CNTN">
									</td>
									<td class="contd txtc">
										<input type="text" id="P_RMK" name="P_RMK">
									</td>
									<td class="contd txtc">
										<button class='btn btn_02 btn_sch' id='deleteBtn' onclick='deleteFn(this);' >삭제</button>
									</td>
								</tr>
							</tbody>
							<tbody id="punishBody">
								<c:if test="${ not empty vendSnctList }">
									<c:forEach var="data" items="${vendSnctList}" varStatus="status" >
										<tr height="24" style="width: 100%;">
											<td class="contd txtc">
												<select id="P_SNCT_STCD" name="P_SNCT_STCD">
													<option selected="selected">선택</option>
													<option value="A">경고</option>
													<option value="B">입찰참가금지</option>
													<option value="C">정지</option>
													<option value="D">영구퇴출</option>
												</select>
											</td>
											<td class="contd txtc">
												<input type="text" class="date" style="width: 90px;" name="P_SNCT_STDE" value="${data.SNCT_STDE }" date> ~ <input type="text" class="date" style="width: 90px;" name="P_SNCT_ENDE" value="${data.SNCT_ENDE }" date>
											</td>
											<td class="contd txtc">
												<input type="text" id="P_SNCT_RSN_CNTN" name="P_SNCT_RSN_CNTN" value="${data.SNCT_RSN_CNTN }">
											</td>
											<td class="contd txtc">
												<input type="text" id="P_SNCT_BSS_CNTN" name="P_SNCT_BSS_CNTN" value="${data.SNCT_BSS_CNTN }">
											</td>
											<td class="contd txtc">
												<input type="text" id="P_RMK" name="P_RMK" value="${data.RMK }">
											</td>
											<td class="contd txtc">
												<button class='btn btn_02 btn_sch' id='deleteBtn' onclick='deleteFn(this);' >삭제</button>
											</td>
										</tr>
									</c:forEach>
								</c:if>
							</tbody>
						</table>
					</div>
					
					
					<div class="btn_wrap view_btn">
			    		<button type="button" class="btn btn_02 btn_revise" id="saveBtn2" >저장</button>
			    		<button type="button" class="btn btn_02 btn_sch" id="listBtn2" >목록</button>
			    	</div>
				</div>
				<!-- 기본정보 TAB END -->
			
				<!-- 상세정보 TAB START -->
				<div id="detail" style="display:none;">
					<h4 class="tit" id="cttDiv">인증, 자격취득 현황(품질,ISO등)</h4>
					<div class="view_area">
						<table>
							<colgroup>
								<col style="width: 315px;">
								<col style="width: 155px;">
								<col style="width: auto;">
							</colgroup>
							<thead>
								<tr>
									<th class="txtc">인증명칭</th>
									<th class="txtc">취득일</th>
									<th class="txtc">취득기관</th>
								</tr>
							</thead>
							<tr height="24">
								<td>
									<input type="text" id="P_CTT_NM_INFO_1" name="P_CTT_NM" value="" style="width: 90%;" >
								</td>
								<td>
									<input type="text" id="P_ACQS_DE_INFO_1" name="P_ACQS_DE"  value="" style="width: 90%;" >
								</td>
								<td>
									<input type="text" id="P_ACQS_AGNM_INFO_1" name="P_ACQS_AGNM"  value="" style="width: 90%;" >
								</td>
							</tr>
							<tr height="24">
								<td>
									<input type="text" id="P_CTT_NM_INFO_2" name="P_CTT_NM" value="" style="width: 90%;" >
								</td>
								<td>
									<input type="text" id="P_ACQS_DE_INFO_2" name="P_ACQS_DE"  value="" style="width: 90%;" >
								</td>
								<td>
									<input type="text" id="P_ACQS_AGNM_INFO_2" name="P_ACQS_AGNM"  value="" style="width: 90%;" >
								</td>
							</tr>
							<tr height="24">
								<td>
									<input type="text" id="P_CTT_NM_INFO_3" name="P_CTT_NM" value="" style="width: 90%;" >
								</td>
								<td>
									<input type="text" id="P_ACQS_DE_INFO_3" name="P_ACQS_DE"  value="" style="width: 90%;" >
								</td>
								<td>
									<input type="text" id="P_ACQS_AGNM_INFO_3" name="P_ACQS_AGNM"  value="" style="width: 90%;" >
								</td>
							</tr>
						</table>
					</div>
				
					<div class="view_area">
						<table>
							<colgroup>
								<col style="width: 5%;">
								<col style="width: 20%;">
								<col style="width: 75%;">
							</colgroup>
							<thead>
								<tr height="24">
									<th class="txtc">순번</th>
									<th class="txtc">문서명</th>
									<th class="txtc">파일명</th>
								</tr>
							</thead>
							
							<tr height="24">
								<th class="txt-center">1</th>
								<td>사업자등록증</td>
								<td>
									<button type="button" class="btn btn_02 btn_sch" title="검색" style="float: right; margin-top: 3px;" id="fileBtn1">파일찾기</button>
		
			                    	<div id="fileRow1" style="display: none; height: 30px;">
			                    		<input type="file" name="" onchange="fileChange(this)" style="display: none;">
			                    		<input type="hidden" name="fileGbn" value="A" disabled="disabled"/>    
					                   	<span class="stD" style=""> 
			                    			<button type="button" class="btn btn_02 btn_sch" style="float: right; display: inline-block; margin-top: 3px;" onclick="rowDel(this)">삭제</button>
			                    		</span>
			                    	</div>
			                    	
			                    	<div id="fileDiv1" style="width: 680px; line-height: 30px;">
			                    	</div>
									
									<div id="viewFileDiv1" style="width: 680px; line-height: 30px;">
			                   			<c:if test="${not empty vendMngeFileListA }">
					                   		<c:forEach items="${vendMngeFileListA }" var="data" varStatus="state">
					                   			<div style="height: 30px;">
					                   				<a href="javascript:download('${data.FILE_SN}');" class="attfile">${data.SYS_FILE_NM }</a>
					                   				<span class="stD" style="">
						                   				<button type="button" class="btn btn_02 btn_sch" style="float: right; display: inline-block; margin-top: 3px;" onclick="fileDel(this,'${data.FILE_SN }')">삭제</button>
						                   			</span>
					                    		</div>
					                    		
					                    		<input type="hidden" id="ATCHMNFL_SN_A" value="${data.FILE_SN}">
					                    	</c:forEach>
					                    </c:if>
			                    	</div>
								</td>
							</tr>
							
							<tr height="24">
								<th class="txt-center">2</th>
								<td>신용평가등급자료</td>
								<td>
									<button type="button" class="btn btn_02 btn_sch" title="검색" style="float: right; margin-top: 3px;" id="fileBtn2">파일찾기</button>
					                    
			                    	<div id="fileRow2" style="display: none; height: 30px;">
			                    		<input type="file" name="" onchange="fileChange(this)" style="display: none;">
			                    		<input type="hidden" name="fileGbn" value="B" disabled="disabled"/>    
					                   	<span class="stD" style=""> 
			                    			<button type="button" class="btn btn_02 btn_sch" style="float: right; display: inline-block; margin-top: 3px;" onclick="rowDel(this)">삭제</button>
			                    		</span>
			                    	</div>
			                    	
			                    	<div id="fileDiv2" style="width: 680px; line-height: 30px;">
			                    	</div>
			                    	
			                    	<div id="viewFileDiv2" style="width: 680px; line-height: 30px;">
			                   			<c:if test="${not empty vendMngeFileListB }">
					                   		<c:forEach items="${vendMngeFileListB }" var="data" varStatus="state">
					                   			<div style="height: 30px;">
					                   				<a href="javascript:download('${data.FILE_SN}');" class="attfile">${data.SYS_FILE_NM }</a>
					                   				<span class="stD" style="">
						                   				<button type="button" class="btn btn_02 btn_sch" style="float: right; display: inline-block; margin-top: 3px;" onclick="fileDel(this,'${data.FILE_SN }')">삭제</button>
						                   			</span>
					                    		</div>
					                    		
					                    		<input type="hidden" id="ATCHMNFL_SN_B" value="${data.FILE_SN}">
					                    	</c:forEach>
					                    </c:if>
			                    	</div>
								</td>
							</tr>
							
							<tr height="24">
								<th class="txt-center">3</th>
								<td>최근년도 결산 재무재표</td>
								<td>
									<button type="button" class="btn btn_02 btn_sch" title="검색" style="float: right; margin-top: 3px;" id="fileBtn3">파일찾기</button>
				                    
			                    	<div id="fileRow3" style="display: none; height: 30px;">
			                    		<input type="file" name="" onchange="fileChange(this)" style="display: none;">
			                    		<input type="hidden" name="fileGbn" value="C" disabled="disabled"/>    
					                   	<span class="stD" style=""> 
			                    			<button type="button" class="btn btn_02 btn_sch" style="float: right; display: inline-block; margin-top: 3px;" onclick="rowDel(this)">삭제</button>
			                    		</span>
			                    	</div>
			                    	
			                    	<div id="fileDiv3" style="width: 680px; line-height: 30px;">
			                    	</div>
			                    	
			                    	<div id="viewFileDiv3" style="width: 680px; line-height: 30px;">
			                   			<c:if test="${not empty vendMngeFileListC }">
					                   		<c:forEach items="${vendMngeFileListC }" var="data" varStatus="state">
					                   			<div style="height: 30px;">
					                   				<a href="javascript:download('${data.FILE_SN}');" class="attfile">${data.SYS_FILE_NM }</a>
					                   				<span class="stD" style="">
						                   				<button type="button" class="btn btn_02 btn_sch" style="float: right; display: inline-block; margin-top: 3px;" onclick="fileDel(this,'${data.FILE_SN }')">삭제</button>
						                   			</span>
					                    		</div>
					                    		
					                    		<input type="hidden" id="ATCHMNFL_SN_C" value="${data.FILE_SN}">
					                    	</c:forEach>
					                    </c:if>
			                    	</div>
								</td>
							</tr>
							<tr height="24">
								<th class="txt-center">4</th>
								<td>회사소개자료 카타로그</td>
								<td>
									<button type="button" class="btn btn_02 btn_sch" title="검색" style="float: right; margin-top: 3px;" id="fileBtn4">파일찾기</button>
				                    
			                    	<div id="fileRow4" style="display: none; height: 30px;">
			                    		<input type="file" name="" onchange="fileChange(this)" style="display: none;">
			                    		<input type="hidden" name="fileGbn" value="D" disabled="disabled"/>    
					                   	<span class="stD" style=""> 
			                    			<button type="button" class="btn btn_02 btn_sch" style="float: right; display: inline-block; margin-top: 3px;" onclick="rowDel(this)">삭제</button>
			                    		</span>
			                    	</div>
			                    	
			                    	<div id="fileDiv4" style="width: 680px; line-height: 30px;">
			                    	</div>
			                    	
			                    	<div id="viewFileDiv4" style="width: 680px; line-height: 30px;">
			                   			<c:if test="${not empty vendMngeFileListD }">
					                   		<c:forEach items="${vendMngeFileListD }" var="data" varStatus="state">
					                   			<div style="height: 30px;">
					                   				<a href="javascript:download('${data.FILE_SN}');" class="attfile">${data.SYS_FILE_NM }</a>
					                   				<span class="stD" style="">
						                   				<button type="button" class="btn btn_02 btn_sch" style="float: right; display: inline-block; margin-top: 3px;" onclick="fileDel(this,'${data.FILE_SN }')">삭제</button>
						                   			</span>
					                    		</div>
					                    		
					                    		<input type="hidden" id="ATCHMNFL_SN_D" value="${data.FILE_SN}">
					                    	</c:forEach>
					                    </c:if>
			                    	</div>
								</td>
							</tr>
							<tr height="24">
								<th class="txt-center">5</th>
								<td>인증서 등 자료</td>
								<td>
									<button type="button" class="btn btn_02 btn_sch" title="검색" style="float: right; margin-top: 3px;" id="fileBtn5">파일찾기</button>
				                    
			                    	<div id="fileRow5" style="display: none; height: 30px;">
			                    		<input type="file" name="" onchange="fileChange(this)" style="display: none;">
			                    		<input type="hidden" name="fileGbn" value="E" disabled="disabled"/>    
					                   	<span class="stD" style=""> 
			                    			<button type="button" class="btn btn_02 btn_sch" style="float: right; display: inline-block; margin-top: 3px;" onclick="rowDel(this)">삭제</button>
			                    		</span>
			                    	</div>
			                    	
			                    	<div id="fileDiv5" style="width: 680px; line-height: 30px;">
			                    	</div>
			                    	
			                    	<div id="viewFileDiv5" style="width: 680px; line-height: 30px;">
			                   			<c:if test="${not empty vendMngeFileListE }">
					                   		<c:forEach items="${vendMngeFileListE }" var="data" varStatus="state">
					                   			<div style="height: 30px;">
					                   				<a href="javascript:download('${data.FILE_SN}');" class="attfile">${data.SYS_FILE_NM }</a>
					                   				<span class="stD" style="">
						                   				<button type="button" class="btn btn_02 btn_sch" style="float: right; display: inline-block; margin-top: 3px;" onclick="fileDel(this,'${data.FILE_SN }')">삭제</button>
						                   			</span>
					                    		</div>
					                    		
					                    		<input type="hidden" id="ATCHMNFL_SN_E" value="${data.FILE_SN}">
					                    	</c:forEach>
					                    </c:if>
			                    	</div>
								</td>
							</tr>
							<tr height="24">
								<th class="txt-center">6</th>
								<td>면허수첩, 면허증사본</td>
								<td>
									<button type="button" class="btn btn_02 btn_sch" title="검색" style="float: right; margin-top: 3px;" id="fileBtn6">파일찾기</button>
				                    
			                    	<div id="fileRow6" style="display: none; height: 30px;">
			                    		<input type="file" name="" onchange="fileChange(this)" style="display: none;">
			                    		<input type="hidden" name="fileGbn" value="F" disabled="disabled"/>    
					                   	<span class="stD" style=""> 
			                    			<button type="button" class="btn btn_02 btn_sch" style="float: right; display: inline-block; margin-top: 3px;" onclick="rowDel(this)">삭제</button>
			                    		</span>
			                    	</div>
			                    	
			                    	<div id="fileDiv6" style="width: 680px; line-height: 30px;">
			                    	</div>
			                    	
			                    	<div id="viewFileDiv6" style="width: 680px; line-height: 30px;">
			                   			<c:if test="${not empty vendMngeFileListF }">
					                   		<c:forEach items="${vendMngeFileListF }" var="data" varStatus="state">
					                   			<div style="height: 30px;">
					                   				<a href="javascript:download('${data.FILE_SN}');" class="attfile">${data.SYS_FILE_NM }</a>
					                   				<span class="stD" style="">
						                   				<button type="button" class="btn btn_02 btn_sch" style="float: right; display: inline-block; margin-top: 3px;" onclick="fileDel(this,'${data.FILE_SN }')">삭제</button>
						                   			</span>
					                    		</div>
					                    		
					                    		<input type="hidden" id="ATCHMNFL_SN_F" value="${data.FILE_SN}">
					                    	</c:forEach>
					                    </c:if>
			                    	</div>
			                    	
								</td>
							</tr>
							<tr height="24">
								<th class="txt-center">7</th>
								<td>기타자료</td>
								<td>
									<button type="button" class="btn btn_02 btn_sch" title="검색" style="float: right; margin-top: 3px;" id="fileBtn7">파일찾기</button>
				                    
			                    	<div id="fileRow7" style="display: none; height: 30px;">
			                    		<input type="file" name="" onchange="fileChange(this)" style="display: none;">
			                    		<input type="hidden" name="fileGbn" value="G" disabled="disabled"/>    
					                   	<span class="stD" style=""> 
			                    			<button type="button" class="btn btn_02 btn_sch" style="float: right; display: inline-block; margin-top: 3px;" onclick="rowDel(this)">삭제</button>
			                    		</span>
			                    	</div>
			                    	
			                    	<div id="fileDiv7" style="width: 680px; line-height: 30px;">
			                    	</div>
			                    	
			                    	<div id="viewFileDiv7" style="width: 680px; line-height: 30px;">
			                   			<c:if test="${not empty vendMngeFileListG }">
					                   		<c:forEach items="${vendMngeFileListG }" var="data" varStatus="state">
					                   			<div style="height: 30px;">
					                   				<a href="javascript:download('${data.FILE_SN}');" class="attfile">${data.SYS_FILE_NM }</a>
					                   				<span class="stD" style="">
						                   				<button type="button" class="btn btn_02 btn_sch" style="float: right; display: inline-block; margin-top: 3px;" onclick="fileDel(this,'${data.FILE_SN }')">삭제</button>
						                   			</span>
					                    		</div>
					                    		
					                    		<input type="hidden" id="ATCHMNFL_SN_G" value="${data.FILE_SN}">
					                    	</c:forEach>
					                    </c:if>
			                    	</div>
			                    	
			                    </td>
							</tr>
						</table>
					</div>
					
					<div class="btn_wrap view_btn">
			    		<button type="button" class="btn btn_02 btn_revise" id="saveBtn3" >저장</button>
			    		<button type="button" class="btn btn_02 btn_sch" id="listBtn3" >목록</button>
			    	</div>
				</div>
				<!-- 상세정보 TAB END -->
				
				<!-- 계약실적 TAB START -->
				<div id="contract" style="display:none;">
					<div class="view_area">
						<table>
							<colgroup>
								<col style="width: 8%;">
								<col style="width: 5%;">
								<col style="width: 8%;">
								<col style="width: auto;">
								<col style="width: 10%;">
								<col style="width: 10%;">
								<col style="width: 10%;">
								<col style="width: 10%;">
								<col style="width: 10%;">
								<col style="width: 10%;">
							</colgroup>
							<thead>
								<tr height="24">
									<th class="txtc">계약번호</th>
									<th class="txtc">차수</th>
									<th class="txtc">계약유형</th>
									<th class="txtc">계약명</th>
									<th class="txtc">계약금액</th>
									<th class="txtc">공급가</th>
									<th class="txtc">부가세</th>
									<th class="txtc">계약일자</th>
									<th class="txtc">계약시작일</th>
									<th class="txtc">계약종료일</th>
								</tr>
							</thead>
							<tbody>
								<c:if test="${ empty vendMngeCntrctList }">
									<tr height="24">
										<td class="txt-center" colspan="10">검색 조건에 해당하는 데이터가 존재하지 않습니다.</td>
									</tr>
								</c:if>
								
								<c:if test="${ not empty vendMngeCntrctList }">
									<c:forEach var="data" items="${vendMngeCntrctList}" varStatus="status" >
										<tr height="24">
											<td class="txtc">${data.CNTRCT_NO }</td>
											<td class="txtc">${data.CHANGE_ODR }</td>
											<td class="txtc">-</td>
											<td class="txtl">${data.CNTRCT_NM }</td>
											<td class="txtr">${comFn:formatMoney(data.CNTRCT_AMOUNT)}</td>
											<td class="txtc">-</td>
											<td class="txtc">-</td>
											<td class="txtc">${comFn:formatDate(data.CNTRCT_DE, 'yyyyMMdd', 'yyyy-MM-dd')}</td>
											<td class="txtc">${comFn:formatDate(data.CNTRCTPD_BEGIN_DE, 'yyyyMMdd', 'yyyy-MM-dd')}</td>
											<td class="txtc">${comFn:formatDate(data.CNTRCTPD_END_DE, 'yyyyMMdd', 'yyyy-MM-dd')}</td>
										</tr>
									</c:forEach>
								</c:if>
							</tbody>
						</table>
					</div>
				</div>
				<!-- 계약실적 TAB END -->
				
				<!-- 입찰정보 TAB START -->
				<div id="bidding" style="display:none;">
					<div class="view_area">
						<table>
							<colgroup>
								<col width="15%" class="txtc">
								<col width="55%" class="txtc">
								<col width="15%" class="txtc">
								<col width="15%" class="txtc">
							</colgroup>
							<thead>
								<tr height="24">
									<th class="txtc">공고번호</th>
									<th class="txtc">공고명</th>
									<th class="txtc">입찰일자</th>
									<th class="txtc">낙찰여부</th>
								</tr>
							</thead>
							<tbody>
								<c:if test="${ empty vendMngeEbidList }">
									<tr height="24">
										<td class="txt-center" colspan="4">검색 조건에 해당하는 데이터가 존재하지 않습니다.</td>
									</tr>
								</c:if>
								
								<c:if test="${ not empty vendMngeEbidList }">
									<c:forEach var="data" items="${vendMngeEbidList}" varStatus="status" >
										<tr>
											<td class="txtc">${data.PBLANC_NO } - ${PBLANC_ODR }</td>
											<td class="txtl">${data.BID_NM }</td>
											<td class="txtc">${data.PBLANC_DT }</td>
											<td class="txtc">${data.SCSBID_AT }</td>
										</tr>
									</c:forEach>
								</c:if>
							</tbody>
						</table>
					</div>	
				</div>	
				<!-- 입찰정보 TAB END -->
			</div>
		</form>
	</div>
</div>
<%-- DETAIL FORM --%>
<form id="popupFrm" method="POST" > 
	<input type="hidden" name="resourceName" value="${ param.resourceName }" >
</form>
<form id="listFrm" method="POST" > 
	<input type="hidden" name="resourceName" value="${ param.resourceName }" >
</form>
<form id="itemListPopupFrm" method="POST">
	<input type="hidden" name="resourceName" value="${param.resourceName}">
	<input type="hidden" name="resourceDesc" value="${param.resourceDesc}">
</form>
<form id="downloadFrm" method="POST" > 
	<input type="hidden" name="resourceName" value="${ param.resourceName }" >
	<input type="hidden" name="P_FILE_SN">
</form>
<form id="zipPopupFrm" method="POST">
	<input type="hidden" name="resourceName" value="${param.resourceName}">
	<input type="hidden" name="resourceDesc" value="${param.resourceDesc}">
</form>
