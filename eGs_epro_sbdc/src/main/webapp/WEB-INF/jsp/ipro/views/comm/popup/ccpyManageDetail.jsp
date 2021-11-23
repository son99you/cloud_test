<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 입찰현황 > 입찰공고문 상세 팝업
 *
 * <pre>
 * ebid 
 *    |_ popup
               |_ bidWrtancDetail.jsp
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

<script type="text/javascript" src="${jsPath}/ipro/views/comm/popup/ccpyManageDetail.js"></script> 
 
<div class="pop_wrap">
	<div class="pop_header">
		<h1 class="tit">협력사 상세</h1>
	</div> <!--// pop_header E -->
	<div class="pop_container">
		<div class="view_wrap typeC">
			<div class="tit_area">
				<h2 class="tit01">기본정보</h2>
			</div> <!--// tit_area E -->
			<div class="view_area">
				<table class="table">
					<colgroup>
						<col width="15%" >
						<col width="35%" >
						<col width="15%" >
						<col width="35%" >
					</colgroup>
					<tr height="24">
						<th class=" txtl"> 회사명</th>
						<td  colspan="3">${vendMngeDetail.PRTNR_KOREAN_NM}</td>
					</tr>
					<tr height="24">
					    <th class=" txtl"> 사업자등록번호</th>
						<td  >${comFn:formatBizNumber(vendMngeDetail.BIZRNO) }</td>
						<th class=" txtl"> 법인번호</th>
						<td>
							${vendMngeDetail.JURIRNO}
						</td>
					</tr>
					<tr height="24">
					    <th class=" txtl"> 대표이사</th>
						<td  colspan="3">
							${vendMngeDetail.RPRSNTV_NM}
						</td>
					</tr>
					
					<tr height="24">
					    <th class=" txtl"> 업태</th>
						<td>
							${vendMngeDetail.BIZCND_NM}
						</td>
						<th class=" txtl"> 종목</th>
						<td>
							${vendMngeDetail.INDUTY_NM}
						</td>
					</tr>
				</table>
			</div>
			
			<div class="view_area">
				<table class="table">
					<colgroup>
						<col width="15%" align="left">
						<col width="35%" align="left">
						<col width="15%" align="left">
						<col width="35%"   align="left">
					</colgroup>
					<tr height="24">
						<th class=" txtl"> 회사설립일</th>
						<td>
							<div class="calendar_box">
								${comFn:formatDate(vendMngeDetail.CPR_FOND_DE,'yyyyMMdd','yyyy-MM-dd')}
							</div>
						</td>
						
						<th class=" txtl bullet_orange"> 파트너유형</th>
						<td>
							<div class="selectLayer2 w_120">
	                			${vendMngeDetail.PRTNR_TY_SE_NM}
	                		</div>
						</td>
	
					</tr>
					<tr height="24">
					    <th class=" txtl"> 홈페이지주소</th>
						<td  >
							${vendMngeDetail.HMPG_URL }
						</td>
						<th class=" txtl"> 이메일주소</th>
						<td  >
							${vendMngeDetail.EMAIL_ADRES }
						</td>
					</tr>
					
					<tr height="24">
					    <th class=" txtl"> 주소</th>
						<td  colspan="3">
							${vendMngeDetail.ZIP }
							<br>
							${vendMngeDetail.RN_ADRES }&nbsp;&nbsp;
							${vendMngeDetail.DETAIL_ADRES }
						</td>
					</tr>
					
					<tr height="24">
					    <th class=" txtl"> 전화번호</th>
						<td  >
							${vendMngeDetail.TELNO }
						</td>
						<th class=" txtl"> FAX번호</th>
						<td  >
							${vendMngeDetail.FAX_TELNO }
						</td>
					</tr>
					
					<tr height="24">
						<th class=" txtl"> 주거래은행</th>
						<td>
							${vendMngeDetail.BNK_NM}
						</td>
					    <th class=" txtl"> 은행지점</th>
						<td>
							${vendMngeDetail.BRNC_NM}
						</td>
					</tr>
					
					<tr height="24">
					    <th class=" txtl"> 신용등급</th>
						<td>
							${vendMngeDetail.CRDT_RNK }
						</td>
						<th class=" txtl"> 기업신용평가회사</th>
						<td>
							${vendMngeDetail.CRDT_ESTM_CMPY }
						</td>
					</tr>
					
					<tr height="24">
						<th class=" txtl"> 상시고용종업원수</th>
						<td>
							${vendMngeDetail.MMBR_CNT}
						</td>
						<th class=" txtl"> 영업소재지</th>
						<td  colspan="3">
							${vendMngeDetail.CMP_LCTN }
						</td>
					</tr>
				</table>
			</div>
				
			<div class="tit_area">
				<h2 class="tit01">SG분류</h2>
			</div> <!--// tit_area E -->
			<div class="view_area">
				<table class="table">
					<tr height="24">
						<th class="txtc">SG명</th>
						<th class="txtc">주거래SG</th>
					</tr>
					<tbody id="sgAddShowTbdy">
						<c:if test="${ empty vendMngeSgCodeList}">
							<tr height="24" style="width: 100%;">
								<td class="contd txtc" colspan="2">
									등록된 SG 분류가 없습니다.
								</td>
							</tr>
						</c:if>
						<c:if test="${ not empty vendMngeSgCodeList}">
							<c:forEach var="data" items="${vendMngeSgCodeList}" varStatus="status" >
								<tr height="24" style="width: 100%;">
									<td class="contd txtc" style="width: 33%">
										${data.SG_NAME}
									</td>
									<td class="contd txtc" style="width: 33%">
										<input type="checkbox" id="P_MAIN_SG" name="P_MAIN_SG_CHK" disabled="disabled" <c:if test="${data.MAIN_SG eq 'Y'}">checked</c:if> onclick="mainSgEvent(this);">
										<input type="hidden" name="P_MAIN_SG_AT_CHK" value="${data.MAIN_SG}"  />
									</td>
								</tr>
							</c:forEach>
						</c:if>
					</tbody>
				</table>
			</div>
			
			<div class="view_area">
				<table class="table">
					<colgroup>
						<col width="15%" align="left">
						<col width="*" align="left">
					</colgroup>
	
					<tr height="24">
						<th class=" txtl" > 관심분야</th>
						<td  >
							<input type="checkbox" name="P_CCN_SPHE_CHK" class="checkbox" disabled="disabled" <c:if test="${vendMngeDetail.CCN_SPHE_1 eq 'Y'}">checked</c:if>> 공사&nbsp;&nbsp;
							<input type="checkbox" name="P_CCN_SPHE_CHK" class="checkbox" disabled="disabled" <c:if test="${vendMngeDetail.CCN_SPHE_2 eq 'Y'}">checked</c:if>> 용역&nbsp;&nbsp;
							<input type="checkbox" name="P_CCN_SPHE_CHK" class="checkbox" disabled="disabled" <c:if test="${vendMngeDetail.CCN_SPHE_3 eq 'Y'}">checked</c:if>> 물품&nbsp;&nbsp;
							<input type="checkbox" name="P_CCN_SPHE_CHK" class="checkbox" disabled="disabled" <c:if test="${vendMngeDetail.CCN_SPHE_4 eq 'Y'}">checked</c:if>> 임대차 &nbsp;&nbsp;
							<input type="checkbox" name="P_CCN_SPHE_CHK" class="checkbox" disabled="disabled" <c:if test="${vendMngeDetail.CCN_SPHE_5 eq 'Y'}">checked</c:if>> 매각
							<input type="hidden" name="P_CCN_SPHE">
						</td>
					</tr>
					
					<tr height="">
						<th class=" txtl" > 기술분야</th>
						<td  >
							<input type="checkbox" name="P_TCHN_SPHE_CHK" class="checkbox" disabled="disabled" <c:if test="${vendMngeDetail.TCHN_SPHE_1 eq 'Y'}">checked</c:if>> AFC&nbsp;&nbsp;
							<input type="checkbox" name="P_TCHN_SPHE_CHK" class="checkbox" disabled="disabled" <c:if test="${vendMngeDetail.TCHN_SPHE_2 eq 'Y'}">checked</c:if>> 건축&nbsp;&nbsp;
							<input type="checkbox" name="P_TCHN_SPHE_CHK" class="checkbox" disabled="disabled" <c:if test="${vendMngeDetail.TCHN_SPHE_3 eq 'Y'}">checked</c:if>> 토목&nbsp;&nbsp;
							<input type="checkbox" name="P_TCHN_SPHE_CHK" class="checkbox" disabled="disabled" <c:if test="${vendMngeDetail.TCHN_SPHE_4 eq 'Y'}">checked</c:if>> 통신&nbsp;&nbsp;
							<input type="checkbox" name="P_TCHN_SPHE_CHK" class="checkbox" disabled="disabled" <c:if test="${vendMngeDetail.TCHN_SPHE_5 eq 'Y'}">checked</c:if>> 전차선&nbsp;&nbsp;
							<input type="checkbox" name="P_TCHN_SPHE_CHK" class="checkbox" disabled="disabled" <c:if test="${vendMngeDetail.TCHN_SPHE_6 eq 'Y'}">checked</c:if>> 송변선&nbsp;&nbsp;
							<input type="checkbox" name="P_TCHN_SPHE_CHK" class="checkbox" disabled="disabled" <c:if test="${vendMngeDetail.TCHN_SPHE_7 eq 'Y'}">checked</c:if>> 전력&nbsp;&nbsp;
							<input type="checkbox" name="P_TCHN_SPHE_CHK" class="checkbox" disabled="disabled" <c:if test="${vendMngeDetail.TCHN_SPHE_8 eq 'Y'}">checked</c:if>> 기계&nbsp;&nbsp;
							<input type="checkbox" name="P_TCHN_SPHE_CHK" class="checkbox" disabled="disabled" <c:if test="${vendMngeDetail.TCHN_SPHE_9 eq 'Y'}">checked</c:if>> 선로&nbsp;&nbsp;
							<br>                                                           
							<input type="checkbox" name="P_TCHN_SPHE_CHK" class="checkbox" disabled="disabled" <c:if test="${vendMngeDetail.TCHN_SPHE_10 eq 'Y'}">checked</c:if>> 전동차&nbsp;&nbsp;
							<input type="checkbox" name="P_TCHN_SPHE_CHK" class="checkbox" disabled="disabled" <c:if test="${vendMngeDetail.TCHN_SPHE_11 eq 'Y'}">checked</c:if>> 신호&nbsp;&nbsp;
							<input type="checkbox" name="P_TCHN_SPHE_CHK" class="checkbox" disabled="disabled" <c:if test="${vendMngeDetail.TCHN_SPHE_12 eq 'Y'}">checked</c:if>> 광고홍보&nbsp;&nbsp;
							<input type="checkbox" name="P_TCHN_SPHE_CHK" class="checkbox" disabled="disabled" <c:if test="${vendMngeDetail.TCHN_SPHE_13 eq 'Y'}">checked</c:if>> 역무&nbsp;&nbsp;
							<input type="checkbox" name="P_TCHN_SPHE_CHK" class="checkbox" disabled="disabled" <c:if test="${vendMngeDetail.TCHN_SPHE_14 eq 'Y'}">checked</c:if>> 경영&nbsp;&nbsp;
							<input type="checkbox" name="P_TCHN_SPHE_CHK" class="checkbox" disabled="disabled" <c:if test="${vendMngeDetail.TCHN_SPHE_15 eq 'Y'}">checked</c:if>> 안전&nbsp;&nbsp;
							<input type="checkbox" name="P_TCHN_SPHE_CHK" class="checkbox" disabled="disabled" <c:if test="${vendMngeDetail.TCHN_SPHE_16 eq 'Y'}">checked</c:if>> 단순구매&nbsp;&nbsp;
							<input type="checkbox" name="P_TCHN_SPHE_CHK" class="checkbox" disabled="disabled" <c:if test="${vendMngeDetail.TCHN_SPHE_17 eq 'Y'}">checked</c:if>> 보수정비&nbsp;&nbsp;
							<input type="checkbox" name="P_TCHN_SPHE_CHK" class="checkbox" disabled="disabled" <c:if test="${vendMngeDetail.TCHN_SPHE_18 eq 'Y'}">checked</c:if>> 기타&nbsp;&nbsp;
							<input type="hidden" name="P_TCHN_SPHE">
						</td>
					</tr>
				</table>
			</div>
			
			
			<div class="tit_area">
				<h2 class="tit01">담당자등록</h2>
			</div> <!--// tit_area E -->
			<div class="view_area">
				<table class="table">
					<colgroup>
						<col width="13%" />
						<col width="15%" />
						<col width="15%" />
						<col width="15%" />
						<col width="20%" />
						<col width="15%" />
					</colgroup>
					<thead>
						<tr>
							<th class="txtc">담당자명</th>
							<th class="txtc">담당자직급</th> 
							<th class="txtc">연락처</th> 
							<th class="txtc">연락처(휴대폰)</th> 
							<th class="txtc">이메일</th> 
							<th class="txtc">부서명</th> 
						</tr>
					</thead>
					<tbody id="userAddShowTbdy">
						<c:if test="${ empty vendMngeUserList }">
							<tr height="24">
								<td class="contd txtc" colspan="6">
									등록된 담당자가 없습니다.
								</td>
							</tr>
						</c:if>
						<c:if test="${ not empty vendMngeUserList }">
							<c:forEach var="data" items="${vendMngeUserList}" varStatus="status" >
								<tr height="24">
									<td class="contd txtc">
										${data.USER_NAME }
									</td>
									<td class="contd txtc">
										${data.USER_POSITION }
									</td>
									<td class="contd txtc">
										${data.USER_TEL }
									</td>
									<td class="contd txtc">
										${data.USER_HP }
									</td>
									<td class="contd txtc">
										${data.USER_EMAIL }
									</td>
									<td class="contd txtc">
										${data.DEPT_NAME }
									</td>
								</tr>
							</c:forEach>
						</c:if>
					</tbody>
				</table>
			</div>
			
			<div class="tit_area">
				<h2 class="tit01">품목등록</h2>
			</div> <!--// tit_area E -->
			<div class="tableLayer">
				<table class="table">
					<thead>
						<tr>
							<th class="txtc">품목번호</th>
			                <th class="txtc">대분류명</th>
							<th class="txtc">중분류명</th> 
							<th class="txtc">소분류명</th> 
							<th class="txtc">품목명</th> 
							<th class="txtc">단가</th> 
						</tr>
					</thead>
					
					<tbody id="itemShowTbdy">
						<c:if test="${ not empty vendMngeItemList }">
							<c:forEach var="data" items="${vendMngeItemList}" varStatus="status" >
								<tr height="24">
									<td class='txtc'>
										${data.ITEM_NO }
									</td>
									<td class='txtl pl5'>
										${data.LLF_NM }
									</td>
									<td class='txtl pl5'>
										${data.MLF_NM }
									</td>
									<td class='txtl pl5'>
										${data.SLF_NM }
									</td>
									<td class='txtl pl5'>
										${data.ITEM_NM }
									</td>
									<td class="txtr pr5">
										${comFn:formatMoneyDp(data.PRICE)}
									</td>
								</tr>
							</c:forEach>
						</c:if>
					</tbody>
					
					<tbody id="itemFrame">
					</tbody>
				</table>
			</div>
			<div class="btn_wrap view_btn">
		    	<button type="button" class="btn btn_02 btn_close" name="closeBtn" >닫기</button>
		    </div>
		</div>
	</div>
</div> <!--// content E-->

<form id="downFrm" method="POST" >
	<input type="hidden" name="P_ATCHMNFL_SN">
</form>
