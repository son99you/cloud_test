<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 공통 > 실적증명신청현황 > 신청서출력(팝업)
 *
 * <pre>
 * comm 
 *  |_popup
 *   |_acprRqstDocView.jsp
 * 
 * </pre>
 * @date : 2017. 06. 27
 * @version : 1.0
 * @author : 은우소프트 은잔디
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %>

<link rel="stylesheet" type="text/css" href="${cssPath}/ipro/windowPop.css">
<link rel="stylesheet" type="text/css" href="${cssPath}/ipro/template/pagingPlace.css">

<div id="windowPopup" class="w_800">
	<h3 class="windowTitle">실적증명</h3>
	<div class="formLayer">
		<br/><br/> &nbsp;
			<table class="contable2">
				<tr>
					<td>
						<table class="contable">
							<tr>
								<td>
									<table>
										<colgroup>
											<col width="10%">
											<col width="12%">
											<col width="15%">
											<col width="15%">
											<col width="15%">
											<col width="13%">
											<col width="12%">
										</colgroup>
										<tr height="29px">
											<td class="" rowspan="4">
												업체현황
											</td>
											<td>업체명</td>
											<td  colspan="2">은우소프트</td>
											<td>대표자</td>
											<td  colspan="2">정한규</td>
										</tr>
										<tr height="29px">
											<td>소재지</td>
											<td  colspan="2">서울 구로구 디지털로33길 28, 1402호(구로동, 우림 EBIZ 1차)</td>
											<td>전화번호</td>
											<td  colspan="2">02-841-0721</td>
										</tr>
										<tr height="29px">
											<td>사업자번호</td>
											<td  colspan="2">119-86-02801</td>
											<td>등록번호</td>
											<td  colspan="2">2017061300</td>
										</tr>
										<tr height="29px">
											<td>증명서용도</td>
											<td  colspan="5">실적증명용</td>
										</tr>
										
										<tr height="29px">
											<td class="" rowspan="4">
												계약내용
											</td>
											<td>계약명</td>
											<td  colspan="3">(강원지사)미래코 폐광지역 꿈나무 응원</td>
											<td>계약유형</td>
											구매</td>
										</tr>
										
										<tr height="29px">
											<td>계약금액</td>
											<td  colspan="3">2,400,000 (금 이백사십만원)</td>
											<td>지분율</td>
											100 %</td>
										</tr>
										
										<tr height="29px">
											<td>계약일자</td>
											<td class="" colspan="5">계약기간</td>
										</tr>
										
										<tr height="29px">
											<td  style="text-align: center;">2017-06-13</td>
											<td  colspan="5">2017-06-14 ~ 2017-12-30</td>
										</tr>
										
										<tr height="29px">
											<td class="" rowspan="2">
												실적내용
											</td>
											<td>전년도까지</td>
											<td>실적분야</td>
											<td>실적연도</td>
											<td class="" colspan="2">실적금액(부가세포함)</td>
											<td>관급금액</td>
										</tr>
										
										<tr height="29px">
											금 1,250,000</td>
											기타</td>
											2017</td>
											<td  colspan="2">금 50,000</td>
											금 0</td>
										</tr>
									
										<tr height="29px">
											<td class="" rowspan="4">증명서<br>발급<br>신청자</td>
											<td  colspan="6">위 사실을 증명하여 주시기 바랍니다. <br>2017년 06월 13일</td>
										</tr>
										
										<tr height="29px">
											<td  colspan="6">회사명 : 은우소프트<br>
																						주소 : 서울 구로구 디지털로33길 28, 1402호(구로동, 우림 EBIZ 1차)<br>
																						대표자 : 정한규 (인)</td>
										</tr>
										
										<tr height="29px">
											<td  colspan="6">2017년 06월 13일</td>
										</tr>
										
									</table>
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
			
			<br><br>
           
	    <div class="T_btnLayer fr" style="margin-top: 20px;">
			<button type="button" class="blueBtn L" id="closeBtn">출력</button>
			<button type="button" class="blueBtn L" id="closeBtn" onclick="window.close();">닫기</button>
	    </div>
	</div> 
</div>
