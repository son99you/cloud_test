<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 마이페이지 > 자사정보
 *
 * <pre>
 * user 
 *    |_ vendInfoViewPopup.jsp
 *
 * </pre>
 * @date : 2017. 06. 22.
 * @version : 1.0
 * @author : 은우소프트 홍찬일
 *
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld"%>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld"%>

<script type="text/javascript" src="${jsPath}/opro/views/comm/popup/vendInfoViewPopup.js"></script>

<div class="pop_wrap">
	<div class="pop_header">
		<h1 class="tit">자사정보 상세</h1>
	</div> <!--// pop_header E -->
	
	<div class="pop_container">
		<div class="view_wrap typeC">
			<div class="tit_area">	
				<h4 class="tit">업체정보</h4>
			</div>	
			<div class="view_area">
				<table>
					<caption>업체정보</caption>
					<colgroup>
						<col width="15%">
						<col width="35%">
						<col width="15%">
						<col width="35%">
					</colgroup>
					<tbody>
						<tr> 
							<th>사업자등록번호</th>
							<td>${comFn:formatBizNumber(vendInfoDetail.BIZRNO)}</td>
							<th>법인등록번호</th>
							<td>${vendInfoDetail.CORP_REG_NO}</td>
						</tr>
						<tr> 
							<th>업체명</th>
							<td>${vendInfoDetail.VEND_NM}</td>
							<th>업체영문명</th>
							<td>${vendInfoDetail.VEND_ENM}</td>
						</tr>
						<tr> 
							<th>대표자명</th>
							<td>${vendInfoDetail.RPRS_NM}</td>
							<th>대표자영문명</th>
							<td>${vendInfoDetail.RPRS_ENM}</td>
						</tr>
						<tr> 
							<th>이메일주소</th>
							<td colspan="3">${vendInfoDetail.EMAL_ADDR}</td>
						</tr>
						<tr> 
							<th>공급업체구분</th>
							<td>${vendInfoDetail.SPLY_VEND_SECD_NM}</td>
							<th>기업형태</th>
							<td>${vendInfoDetail.CORP_TPCD_NM}</td>
						</tr>
						<tr> 
							<th>업태</th>
							<td>${vendInfoDetail.BCNM}</td>
							<th>종목</th>
							<td>${vendInfoDetail.BTNM}</td>
						</tr>
						<tr> 
							<th>전화번호</th>
							<td>${vendInfoDetail.TEL_NO}</td>
							<th>팩스번호</th>
							<td>${vendInfoDetail.FX_NO}</td>
						</tr>
						<tr> 
							<th>홈페이지주소</th>
							<td>${vendInfoDetail.HMPG_ADDR}</td>
							<th>회사설립일</th>
							<td>${vendInfoDetail.ESTB_YR}</td>
						</tr>
						<tr> 
							<th>주소</th>
							<td colspan="3">
								${vendInfoDetail.ZIP} ${vendInfoDetail.ADDR_NM} ${vendInfoDetail.ADDR_DENM}
							</td>
						</tr>
						<tr> 
							<th>영문주소</th>
							<td colspan="3">${vendInfoDetail.ADDR_ENM}</td>
						</tr>
						<tr> 
							<th>자본금액</th>
							<td>${comFn:formatMoney(vendInfoDetail.CPTL_AMT)}</td>
							<th>종업원수</th>
							<td>${vendInfoDetail.MMBR_CNT}</td>
						</tr>
						<tr> 
							<th>설립일자</th>
							<td>	
								${comFn:formatDate(vendInfoDetail.ESTB_DE,'yyyyMMdd','yyyy-MM-dd')}
							</td>
							<th></th>
							<td></td>
						</tr>
						<tr> 
							<th>특기사항</th>
							<td colspan="3">${vendInfoDetail.SPCL_ITEM}</td>
						</tr>
					</tbody>
				</table>
			</div>

			
			<div class="tit_area">
				<h4 class="tit">담당자정보</h4>
			</div>
			<div class="view_area">
				<table>
					<caption>담당자정보</caption>
					<colgroup>
						<col width="15%">
						<col width="35%">
						<col width="15%">
						<col width="35%">
					</colgroup>
					<tbody>
						<tr> 
							<th>담당자명</th>
							<td>
								${vendChrgrInfoDetail.USR_NM}
							</td>
							<th>직급</th>
							<td>${vendChrgrInfoDetail.ODNM}</td>
						</tr>
						<tr> 
							<th>부서</th>
							<td>${vendChrgrInfoDetail.DEPT_NM}</td>
							<th>이메일</th>
							<td>${vendChrgrInfoDetail.EMAL_ADDR}</td>
						</tr>
						<tr> 
							<th>전화번호</th>
							<td>${vendChrgrInfoDetail.TEL_NO}</td>
							<th>휴대전화번호</th>
							<td>${vendChrgrInfoDetail.CP_NO}</td>
						</tr>
					</tbody>
				</table>
			</div>
			
			
			<div class="tit_area">
				<h4 class="tit">계좌정보</h4>
			</div>
			<div class="view_area">
				<table id="accTb">
					<caption>계좌정보</caption>
					<colgroup>
						<col width="15%">
						<col width="20%">
						<col width="15%">
						<col width="*">
					</colgroup>
					<thead>
						<tr>
							<th class="txtc">은행</th>
							<th class="txtc">계좌번호</th>
							<th class="txtc">예금주</th>
							<th class="txtc">통장사본(필수)</th>
						</tr>
					</thead>
					<tbody id="accShowTbdy">
					<c:if test="${not empty vendAcctInfoList }">
						<c:forEach var="data" items="${vendAcctInfoList}" varStatus="status">
							<tr>
								<td class="txtc">${data.BKNM}</td>
								<td>${data.ACNO }</td>
								<td>${data.DPSO }</td>
								<td>
									<a href="javascript:download('${data.FILE_SN}', '${data.FILE_GRP_NO}');" class="attfile">${data.SYS_FILE_NM }</a>
									<input type="hidden" name="P_ACC_FILE_SN" value="${data.FILE_SN }">
								</td>
							</tr>
						</c:forEach>
					</c:if>
					</tbody>
				</table>
			</div>
			
		    <div class="tit_area">
	           	<h4 class="tit">기업정보</h4>
			</div>
			<div class="view_area">
				<table id="acqsTb">
					<caption>기업정보</caption>
					<colgroup>
						<col width="15%">
						<col width="30%">
						<col width="*">
					</colgroup>
					<thead>
						<tr>
							<th class="txtc">명칭</th>
							<th class="txtc">유효기간</th>
							<th class="txtc">첨부파일</th>
						</tr>
					</thead>
					<tbody id="acqsShowTbdy">
					<c:if test="${not empty vendAcqsInfoList }">
						<c:forEach var="data" items="${vendAcqsInfoList}" varStatus="status">
							<tr>
								<td class="txtc">${data.CTT_NM}</td>
								<td class="txtc">${comFn:formatDate(data.VLD_STDE,'yyyyMMdd','yyyy-MM-dd')} ~ ${comFn:formatDate(data.VLD_ENDE,'yyyyMMdd','yyyy-MM-dd')}</td>
								<td>
									<a href="javascript:download('${data.FILE_SN}', '${data.FILE_GRP_NO}');" class="attfile">${data.SYS_FILE_NM }</a>
									<input type="hidden" name="P_ACQS_FILE_SN" value="${data.FILE_SN }">
								</td>
							</tr>
						</c:forEach>
					</c:if>
					</tbody>
				</table>
			</div>
			
			
			<div class="tit_area">
				<h4 class="tit">주요취급품목</h4>
			</div>
			<div class="view_area">
				<table id="itemTb">
					<caption>주요취급품목</caption>
					<colgroup>
						<col width="30%">
						<col width="*">
						<col width="15%">
					</colgroup>
					<thead>
						<tr>
							<th class="txtc">품목번호</th>
							<th class="txtc">품목명</th>
							<th class="txtc">단가</th>
						</tr>
					</thead>
					<tbody id="itemShowTbdy">
					<c:if test="${not empty vendItemList}">
						<c:forEach var="data" items="${vendItemList}" varStatus="status">
							<tr>
								<td class="txtc">${data.ITEM_NO}</td>
								<td>${data.ITEM_NM}</td>
								<td class="pr5 txtr">${comFn:formatMoney(data.ITEM_UPRC)}</td>
							</tr>
						</c:forEach>
					</c:if>				
					</tbody>
				</table>
			</div>


	        <div class="tit_area">
            	<h4 class="tit">필수첨부파일</h4>
			</div>
            <div class="view_area">
				<table class="table">
					<caption>필수첨부파일</caption>
					<colgroup>
						<col style="width: 30%;">
		                <col style="width: auto;">
					</colgroup>
					<thead>
						<tr>
							<th scope="col" class="txtc">문서구분명</th>
							<th scope="col" class="txtc">파일</th>
						</tr>
					</thead>
					<tbody id="redFileShowTbdy">
					<c:set var="DC01_YN" value="N" />
					<c:forEach items="${vendAtchDocListRed }" var="data" varStatus="state">
						<c:if test="${data.FILE_DOC_SECD eq 'DC01'}">
							<c:set var="DC01_YN" value="Y" />
						</c:if>
                   		<tr class="${data.FILE_DOC_SECD}">
							<td>
								${data.FILE_DOC_NM }
							</td>
							<td>
								<a href="javascript:pageObj.download('${data.FILE_SN}','${data.FILE_GRP_NO }');" class="redFileView">${data.SYS_FILE_NM }</a>
								<input type="file" name="P_RED_VEND_FILE" class="redFile" style="display: none;" disabled="disabled">
							</td>
		            	</tr>
                   	</c:forEach>
					</tbody>
				</table>
			</div>
			
			
			
			<div class="tit_area">
            	<h4 class="tit">기타첨부파일</h4>
			</div>
            <div class="view_area">
				<table class="table">
					<caption>기타첨부파일</caption>
					<colgroup>
						<col style="width: 30%;">
		                <col style="width: auto;">
					</colgroup>
					<thead>
						<tr>
							<th scope="col" class="txtc">문서구분명</th>
							<th scope="col" class="txtc">파일</th>
						</tr>
					</thead>
					<tbody id="fileShowTbdy">
					<c:if test="${not empty vendAtchDocList }">
                   		<c:forEach items="${vendAtchDocList }" var="data" varStatus="state">
	                   		<tr>
								<td>${data.FILE_DOC_NM }</td>
								<td><a href="javascript:pageObj.download('${data.FILE_SN}','${data.FILE_GRP_NO }');" class="attfile">${data.SYS_FILE_NM }</a></td>
			            	</tr>
                    	</c:forEach>  
                    </c:if>
					</tbody>
				</table>
			</div>
		</div>
	</div>	
	
	<div class="btn_wrap view_btn">
	    <button type="button" class="btn btn_m btn_del" onclick="window.close();">닫기</button>
	</div>
	
</div> <!--// content E-->

<!-- LIST FORM -->  	
<form id="listFrm" method="POST">
 	<input type="hidden" name="resourceName" value="${param.resourceName}">
 	<input type="hidden" name="resourceDesc" value="${param.resourceDesc}">
</form>

<!-- RETURN FORM -->
 <form id="returnFrm" method="POST">
	<input type="hidden" name="resourceName" value="${param.resourceName}">
 	<input type="hidden" name="resourceDesc" value="${param.resourceDesc}">
	<input type="hidden" id="P_VEND_REG_NO" name="P_VEND_REG_NO" value="${vendInfoDetail.VEND_REG_NO}">
</form>

<!-- DETAIL FORM -->  	
<form id="detailFrm" method="POST">
 	<input type="hidden" name="resourceName" value="${param.resourceName}">
 	<input type="hidden" name="resourceDesc" value="${param.resourceDesc}">
 	<input type="hidden" id="P_VEND_REG_NO" name="P_VEND_REG_NO">
</form>

<!-- ITEM POPUP FORM -->
<form id="itemPopupFrm" method="POST">
	<input type="hidden" name="resourceName" value="${param.resourceName}">
	<input type="hidden" name="resourceDesc" value="${param.resourceDesc}">
	<input type="hidden" id="searchGbnId" name="searchGbnId">
	<input type="hidden" id="setMulti" name="setMulti">
</form>

<form id="downloadFrm" method="POST">
 	<input type="hidden" name="P_FILE_SN">
 	<input type="hidden" name="P_FILE_GRP_NO">
</form>