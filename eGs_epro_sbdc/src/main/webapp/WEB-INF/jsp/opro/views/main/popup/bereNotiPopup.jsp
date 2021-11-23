<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">--%>

<%--
 * 메인 > 사전규격공개 상세 (팝업)
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %>

<script type="text/javascript" src="${jsPath}/opro/views/main/popup/bereNotiPopup.js"></script>

<div class="pop_wrap">
	<div class="pop_header">
		<h1 class="tit">사전규격공개</h1>
	</div> <!--// pop_header E -->
	
	<div class="pop_container">
		<input type="hidden" id="P_BFAN_NO" value="${bereNotiMngeDetail.BFAN_NO }">
		
		<div class="view_wrap typeC">
			<div class="view_area m0 typeB">
				<table class="table" summary="기본정보 입니다.">
	                <caption>기본정보</caption>
	                <colgroup>
	                    <col width="15%">
	                    <col width="35%">
	                    <col width="15%">
	                    <col width="35%">
	                </colgroup>
	                <tbody>
		                <tr>
		                    <th>사전공고번호</th>
		                    <td>${bereNotiMngeDetail.BFAN_NO }</td>
		                    <th>담당자</th>
		                    <td>${bereNotiMngeDetail.CHRGR_NM }</td>
		               </tr>
		               <tr>
		                    <th>사전규격공개명</th>
		                    <td colspan="3">${bereNotiMngeDetail.BFAN_NM }</td>
		                </tr>
		                <tr>
		                    <th>계약구분</th>
		                    <td>${bereNotiMngeDetail.CONT_SECD_NM }</td>
		                    <th>추정금액</th>
		                    <td>${comFn:formatMoney(bereNotiMngeDetail.BSNS_BDG_AMT)}</td>
		                </tr>
		                
		                <tr>
		                    <th>계약방법</th>
		                    <td>${bereNotiMngeDetail.CONT_MTCD_NM }</td>
		                    <th>낙찰방법</th>
		                    <td>${bereNotiMngeDetail.SBID_MTCD_NM }</td>
		                </tr>
		                <tr>
		                	<th>사전규격공개기간</th>
		                	<td>
		                		<c:if test="${not empty bereNotiMngeDetail.BFAN_STDE}">
			                		${comFn:formatDate(bereNotiMngeDetail.BFAN_STDE,'yyyyMMdd','yyyy-MM-dd')}
			                		 ~
			                		${comFn:formatDate(bereNotiMngeDetail.BFAN_ENDE,'yyyyMMdd','yyyy-MM-dd')}
		                		</c:if>
		                	</td>
		                	<th>진행상태</th>
		                	<td>${bereNotiMngeDetail.BFAN_PSCD_NM }</td>
		                </tr>
	               </tbody>
	            </table>
			</div>
			
			<div class="tit_area">
				<h4 class="tit">품목정보</h4>
			</div>
			<div class="view_area">
				<table class="table" summary="품목정보 입니다.">
					<caption>품목정보</caption>
					<colgroup>
						<col width="15%">
						<col width="25%">
						<col width="*">
						<col width="8%">
						<col width="9%">
					</colgroup>
					<thead>
						<tr>
							<th class="txtc">품목번호</th>
							<th class="txtc">품목명</th>
							<th class="txtc">규격</th>
							<th class="txtc">단위</th>
							<th class="txtc">수량</th>
						</tr>
					</thead>
					<tbody>
						<c:if test="${ empty bereNotiMngeItemList }">
							<tr>
								<td colspan="5" class="txtc">검색 조건에 해당하는 데이터가 존재하지 않습니다.</td>
							</tr>
						</c:if>
						<c:if test="${ not empty bereNotiMngeItemList }">
							<c:forEach var="data" items="${bereNotiMngeItemList}" varStatus="status">
								<tr>
									<td class="txtc">${data.ITEM_NO}</td>
									<td>${data.ITEM_NM}</td>
									<td class="txtl">${data.STND_NM}</td>
									<td class="txtc">${data.ITEM_UNCD}</td>
									<td class="txtr">${data.ITEM_QTY}&nbsp;</td>
								</tr>
							</c:forEach>
						</c:if>
					</tbody>
				</table>
			</div>
			
			<div class="tit_area">
				<h4 class="tit">사전규격파일</h4>
			</div>
			<div class="view_area fileViewer">
				<!-- 업로드 삽입. -->
				<script type="text/javascript">
					DEXT5UPLOAD.config.Mode = 'view';
					DEXT5UPLOAD.config.Width = '100%';
					DEXT5UPLOAD.config.FolderNameRule = '/bere';
					var dext5Upload = new Dext5Upload("upload");
				</script>
			</div>	
			<div id="upload_fileInfo"></div>
			
			<%-- <div class="tit_area">
				<h4 class="tit">사전규격의견정보</h4>
			</div>
			<div class="view_area">
				<table class="table" summary="사전규격의견정보 입니다.">
					<caption>사전규격의견정보</caption>
					<colgroup>
						<col width="5%">
						<col width="10%">
						<col width="*">
						<col width="10%">
						<col width="15%">
						<col width="10%">
						<col width="8%">
						<col width="9%">
					</colgroup>
					<thead>
						<tr>
							<th class="txtc">No</th>
							<th class="txtc">제목</th>
							<th class="txtc">내용</th>
							<th class="txtc">등록업체</th>
							<th class="txtc">이메일</th>
							<th class="txtc">전화번호</th>
							<th class="txtc">첨부파일</th>
							<th class="txtc">등록일자</th>
					</thead>
					<tbody>
						<c:if test="${ empty bereNotiMngeOpnnList }">
							<tr>
								<td colspan="8" class="txtc">검색 조건에 해당하는 데이터가 존재하지 않습니다.</td>
							</tr>
						</c:if>
						<c:if test="${ not empty bereNotiMngeOpnnList }">
							<c:forEach var="data" items="${bereNotiMngeOpnnList}" varStatus="status">
								<tr class="pointer" onclick="detailInqire('${data.OPNN_SN}');" style="cursor: pointer;"> 
									<td class="txtc">${data.OPNN_SN}</td>
									<td>${data.OPNN_TTL_NM}</td>
									<td>${data.OPNN_CNTN}</td>
									<td></td>
									<td>${data.OPNNR_EMAL}</td>
									<td>${data.OPNNR_CP_NO}</td>
									<td>${data.OPNN_FILE_GRP_NO}</td>
									<td class="txtc">${comFn:formatDate(data.REG_DT,'yyyyMMddHHmmss','yyyy-MM-dd')}</td>
								</tr>
							</c:forEach>
						</c:if>
					</tbody>
				</table>
			</div> --%>
			
			
			<div class="btn_wrap view_btn">
				<button type="button" class="btn btn_m btn_del" id="closeBtn">닫기</button>
			</div> <!--// btn_wrap E -->
			
		</div>
	</div> 
</div>

<%-- 파일 VIEW 폼 --%>
<form id="fileViewFrm" method="POST">
	<input type="hidden" id="P_FILE_GRP_NO" name="P_FILE_GRP_NO" value="${bereNotiMngeDetail.FILE_GRP_NO}">
</form>