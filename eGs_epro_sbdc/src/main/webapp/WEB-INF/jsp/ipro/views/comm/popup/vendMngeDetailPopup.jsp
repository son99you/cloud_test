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
 
<div class="pop_wrap">
	<div class="pop_header">
		<h1 class="tit">협력사 상세</h1>
	</div> <!--// pop_header E -->
	<div class="pop_container">
		<form id="detailFrm" name="detailFrm" method="POST" enctype="multipart/form-data">
			<input type="hidden" id="resourceName" name="resourceName" value="${param.resourceName}">
			<input type="hidden" id="resourceDesc" name="resourceDesc" value="${param.resourceDesc}">
			<input type="hidden" name="P_VEND_REG_NO" value="${vendMngeDetail.VEND_REG_NO }"/>
			
	       	<div class="tab_wrap01" style="margin-bottom: 30px;">
				<ul class="tab_tit">
					<li><a class="tapBtn on" id="1" href='javascript:tabEvent(1);' >기본정보</a></li>
<!-- 					<li><a class="tapBtn" id="2" href='javascript:tabEvent(2);'>상세정보</a></li> -->
					<li><a class="tapBtn" id="3" href='javascript:tabEvent(3);'>계약실적</a></li>
					<li><a class="tapBtn" id="4" href='javascript:tabEvent(4);'>입찰정보</a></li>
				</ul>
			</div>
				
			<div class="view_wrap typeC">
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
								<th class=" txtl">업체명</th>
								<td>${vendMngeDetail.VEND_NM} <input type="hidden" name="P_VEND_NM" value="${vendMngeDetail.VEND_NM}"></td>
								<th class=" txtl">대표자명</th>
								<td>${vendMngeDetail.RPRS_NM}</td>
							</tr>
							<tr height="24">
								<th class=" txtl">사업자등록번호</th>
								<td>
									<c:if test="${fn:length(vendMngeDetail.BIZRNO) eq 10 }">${comFn:formatBizNumber(vendMngeDetail.BIZRNO) }</c:if>
									<c:if test="${fn:length(vendMngeDetail.BIZRNO) ne 10 }">${fn:substring(vendMngeDetail.BIZRNO,0,6) } - *******</c:if> 
									<input type="hidden" name="P_BIZRNO" value="${vendMngeDetail.BIZRNO}">
								</td>
								<th class=" txtl">법인등록번호</th>
								<td>${vendMngeDetail.CORP_REG_NO}</td>
							</tr>
							<tr height="24">
								<th class=" txtl">업태</th>
								<td>${vendMngeDetail.BCNM}</td>
								<th class=" txtl">업종</th>
								<td>${vendMngeDetail.BTNM}</td>
							</tr>
							<tr height="24">
								<th class=" txtl">주소</th>
								<td colspan="3">${vendMngeDetail.ADDR_NM}&nbsp;${vendMngeDetail.ADDR_DENM} </td>
							</tr>
							<tr height="24">
								<th class=" txtl">대표자전화번호</th>
								<td>${vendMngeDetail.TEL_NO }</td>
								<th class=" txtl">업체그룹설정</th>
								<td>
									${data.GRP_ID }
<!-- 									<select name="P_GRP_ID" id="P_GRP_ID"> -->
<!-- 										<option>선택</option> -->
<%-- 										<c:forEach var="data" items="${VendMngeGroupList}" varStatus="status" > --%>
<%-- 											<option value="${data.GRP_ID }" <c:if test="${data.SEL_GRP_ID eq data.GRP_ID}"> selected="selected"</c:if> >${data.GRP_ID }</option> --%>
<%-- 										</c:forEach> --%>
<!-- 									</select> -->
								</td>
							</tr>
						</table>
					</div>
					
					<div class="tit_area">
						<h4 class="tit" style="clear: both;">담당자정보</h4>
				   	</div>
					<div class="view_area" id="userDiv">
						<table>
							<colgroup>
			                   	<col style="width: 15%;">
			                   	<col style="width: 15%;">
			                   	<col style="width: 15%;">
			                   	<col style="width: 15%;">
			                   	<col style="width: *;">
							</colgroup>
							<thead>
								<tr>
									<th class="txtc">담당자명</th>
									<th class="txtc">직급</th> 
									<th class="txtc">전화번호</th> 
									<th class="txtc">휴대전화번호</th> 
									<th class="txtc">이메일</th> 
								</tr>
							</thead>
							<tbody id="userAddShowTbdy">
								<c:if test="${ not empty vendMngeUserList }">
									<c:forEach var="data" items="${vendMngeUserList}" varStatus="status" >
										<tr height="24">
											<td class="contd txtc">${data.USR_NM }</td>
											<td class="contd txtc">${data.OPNM }</td>
											<td class="contd txtc">${data.TEL_NO }</td>
											<td class="contd txtc">${data.CP_NO }</td>
											<td class="contd txtl">${data.EMAL_ADDR }</td>
										</tr>
									</c:forEach>
								</c:if>
							</tbody>
						</table>
					</div>
					
					<!-- 업체 제재 목록 -->
					<div class="tit_area">
						<h4 class="tit" style="clear: both;">업체제재등록</h4>
				   	</div>
					<div class="view_area">
						<table>
							<colgroup>
			                   	<col width="20%;">
			                   	<col width="20%;">
			                   	<col width="*">
			                   	<col width="10%;">
			                   	<col width="10%;">
							</colgroup>
							<thead>
								<tr>
									<th class="txtc">제재일시</th>
									<th class="txtc">제재구분</th> 
									<th class="txtc">제재사유</th> 
									<th class="txtc">해제여부</th> 
									<th class="txtc">제재처리자</th> 
								</tr>
							</thead>
							<tbody id="snctBody">
								<c:if test="${ not empty VendMngeSnctList }">
									<c:forEach var="data" items="${VendMngeSnctList}" varStatus="status" >
										<tr height="24" style="width: 100%;">
											<td class="contd txtc">
						                		${comFn:formatDate(data.SNCT_STDE,'yyyyMMdd','yyyy-MM-dd')}
						                		 ~
						                		${comFn:formatDate(data.SNCT_ENDE,'yyyyMMdd','yyyy-MM-dd')}
												
												<input type="hidden" name="P_SNCT_STDE" value="${comFn:formatDate(data.SNCT_STDE,'yyyyMMdd','yyyy-MM-dd')}" >  
												<input type="hidden" name="P_SNCT_ENDE" value="${comFn:formatDate(data.SNCT_ENDE,'yyyyMMdd','yyyy-MM-dd')}" >
											</td>
											<td class="contd txtc">
												${data.SNCT_STCD_NM }<input type="hidden" id="P_SNCT_STCD" name="P_SNCT_STCD" value="${data.SNCT_STCD }">
											</td>
											<td class="contd txtl">
												${data.SNCT_RSN_CNTN }<input type="hidden" id="P_SNCT_RSN_CNTN" name="P_SNCT_RSN_CNTN" value="${data.SNCT_RSN_CNTN }">
											</td>
											<td class="contd txtc">
												<c:if test="${data.SNCT_YN eq 'Y'}">예</c:if>
												<c:if test="${data.SNCT_YN ne 'Y'}">아니오</c:if>
											</td>
											<td class="contd txtc">
												${data.PROCR_NM }
											</td>
										</tr>
									</c:forEach>
								</c:if>
							</tbody>
						</table>
					</div>
					
					<div class="tit_area">
						<h4 class="tit" style="clear: both;">첨부파일</h4>
				   	</div>
					<div class="view_area">
						<table>
							<colgroup>
			                   	<col style="width: 20%;">
			                   	<col style="width: *;">
							</colgroup>
							<thead>
								<tr>
									<th class="txtc">문서구분</th>
									<th class="txtc">파일</th> 
								</tr>
							</thead>
							<tbody id="userAddShowTbdy">
								<c:if test="${ not empty vendMngeRedFileList }">
									<c:forEach var="data" items="${vendMngeRedFileList}" varStatus="status" >
										<tr height="24">
											<td class="contd txtc">${data.FILE_DOC_NM }</td>
											<td class="contd txtl">
												<a href="javascript:pageObj.download('${data.FILE_SN}','${data.FILE_GRP_NO }');" class="fileView fl">${data.SYS_FILE_NM }</a>
											</td>
										</tr>
									</c:forEach>
								</c:if>
								<c:if test="${ not empty vendMngeFileList }">
									<c:forEach var="data" items="${vendMngeFileList}" varStatus="status" >
										<tr height="24">
											<td class="contd txtc">${data.FILE_DOC_NM }</td>
											<td class="contd txtl">
												<a href="javascript:pageObj.download('${data.FILE_SN}','${data.FILE_GRP_NO }');" class="fileView fl">${data.SYS_FILE_NM }</a>
											</td>
										</tr>
									</c:forEach>
								</c:if>
							</tbody>
						</table>
					</div>
					
					
					<div class="btn_wrap view_btn">
			    		<button type="button" class="btn btn_02 btn_sch" onclick="javascript:window.close();" >닫기</button>
			    	</div>
				</div>
				<!-- 기본정보 TAB END -->
			
				<!-- 상세정보 TAB START -->
				<div id="detail" style="display:none;">
					<div class="btn_wrap view_btn">
			    		<button type="button" class="btn btn_02 btn_sch" onclick="javascript:window.close();">닫기</button>
			    	</div>
				</div>
				
				<!-- 상세정보 TAB END -->
				
				<!-- 계약실적 TAB START -->
				<div id="contract" style="display:none;">
					<div class="view_area">
						<table>
							<colgroup>
								<col style="width: 10%;">
								<col style="width: 5%;">
								<col style="width: auto;">
								<col style="width: 10%;">
								<col style="width: 15%;">
								<col style="width: 20%;">
							</colgroup>
							<thead>
								<tr height="24">
									<th class="txtc">계약번호</th>
									<th class="txtc">차수</th>
									<th class="txtc">계약명</th>
									<th class="txtc">계약일자</th>
									<th class="txtc">계약금액</th>
									<th class="txtc">계약기간</th>
								</tr>
							</thead>
							<tbody>
								<c:if test="${ empty vendMngeCntrctList }">
									<tr height="24">
										<td class=" txtc" colspan="6">검색 조건에 해당하는 데이터가 존재하지 않습니다.</td>
									</tr>
								</c:if>
								
								<c:if test="${ not empty vendMngeCntrctList }">
									<c:forEach var="data" items="${vendMngeCntrctList}" varStatus="status" >
										<tr height="24">
											<td class="txtc">${data.CONT_NO }</td>
											<td class="txtc">${data.CHNG_NGR }</td>
											<td class="txtl">${data.CONT_NM }</td>
											<td class="txtc">${comFn:formatDate(data.CONT_DE, 'yyyyMMdd', 'yyyy-MM-dd')}</td>
											<td class="txtr">${comFn:formatMoney(data.CONT_AMT)}</td>
											<td class="txtc">
												${comFn:formatDate(data.CONT_STDE, 'yyyyMMdd', 'yyyy-MM-dd')}
												~
												${comFn:formatDate(data.CONT_ENDE, 'yyyyMMdd', 'yyyy-MM-dd')}
											</td>
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
								<col width="10%">
								<col width="*">
								<col width="15%">
								<col width="10%">
								<col width="10%">
								<col width="10%">
								<col width="10%">
								<col width="10%">
							</colgroup>
							<thead>
								<tr height="24">
									<th class="txtc">입찰번호</th>
									<th class="txtc">입찰명</th>
									<th class="txtc">공고일자</th>
									<th class="txtc">투찰금액</th>
									<th class="txtc">낙찰여부</th>
									<th class="txtc">담당자명</th>
									<th class="txtc">전화번호</th>
									<th class="txtc">이메일</th>
								</tr>
							</thead>
							<tbody>
								<c:if test="${ empty vendMngeEbidList }">
									<tr height="24">
										<td class=" txtc" colspan="8">검색 조건에 해당하는 데이터가 존재하지 않습니다.</td>
									</tr>
								</c:if>
								
								<c:if test="${ not empty vendMngeEbidList }">
									<c:forEach var="data" items="${vendMngeEbidList}" varStatus="status" >
										<tr>
											<td class="txtc">${data.ANNC_NO }</td>
											<td class="txtl">${data.BID_NM }</td>
											<td class="txtc">${comFn:formatDate(data.ANNC_DT, 'yyyyMMddmmss', 'yyyy-MM-dd')}</td>
											<td class="txtc">${comFn:formatMoney(data.TNDR_AMT)}</td>
											<td class="txtc">${data.SBID_YN }</td>
											<td class="txtc">${data.CHRGR_NM }</td>
											<td class="txtc">${data.TEL_NO }</td>
											<td class="txtc">${data.EMAL_ADDR }</td>
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
	<input type="hidden" name="setMulti" value="Y">
</form>
<form id="snctRegistPopupFrm" method="POST">
	<input type="hidden" name="resourceName" value="${param.resourceName}">
	<input type="hidden" name="resourceDesc" value="${param.resourceDesc}">
</form>
<form id="downFrm" method="POST" >  
	<input type="hidden" name="P_FILE_GRP_NO">
	<input type="hidden" name="P_FILE_SN">
</form>
