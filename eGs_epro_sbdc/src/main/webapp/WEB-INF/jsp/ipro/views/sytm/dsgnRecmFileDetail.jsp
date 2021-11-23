<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 정보관리 > 설계추천파일관리 상세
 *
 * <pre>
 * sytm 
 *    |_ dsgnRecmFileDetail.jsp
 * 
 * </pre>
 * @date : 20201028
 * @version : 1.0
 * @author : 
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld"%>
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld"%>

<script type="text/javascript" src="${jsPath}/ipro/views/sytm/dsgnRecmFileDetail.js"></script>
<script type="text/javascript" src="/raonkeditor/js/raonkeditor.js"></script>

<div class="sp_sec">
	<div class="nav_sec">
		<ul id="breadcrumbs">
			<li class="home">홈</li>
			<li>${myMenuList.bigMenuNm}</li>
			<li>${myMenuSubList.smallMenuNm}</li> 
		</ul>
	</div>
	<h3 class="sp_tit">설계추천파일관리 상세</h3>
	
	<form id="detailFrm" method="POST">
		<input type="hidden" name="resourceName" value="${comFn:nvl(param.resourceName, resourceName)}" >
		<div class="sp_cont">
		<p class="spc_stit">설계추천파일관리</p>
		<table class="form_tb" summary="설계추천파일관리 입니다.">
			<colgroup>
				<col width="15%">
				<col width="35%">
				<col width="15%">
				<col width="35%">
			</colgroup>
				<tr>
					<th>등록자</th>
					<td>${dsgnRecmDetail.REGR_NM}</td>
					<th>등록일자</th>
					<td>${comFn:formatDate(dsgnRecmDetail.REG_DT,'yyyyMMddHHmmss', 'yyyy-MM-dd')}</td>
				</tr>
				<tr>
					<th>설계파일ID</th>
					<td>${dsgnRecmDetail.CD_DTL_ID}</td>
					<th></th>
					<td></td>
				</tr>
				<tr>
					<th>설계파일명</th>
					<td colspan="3">${dsgnRecmDetail.CD_DTL_NM}</td>
				</tr>
				<tr>
					<th>추천코드</th>
					<td colspan="3">${dsgnRecmDetail.RECM_ID}</td>
				</tr>
			</table>
			
							
			<p class="spc_stit">첨부파일</p>
			<table class="tb"  summary="첨부파일 입니다.">
				<colgroup>
					<col width="20%">
					<col width="*">
				</colgroup>
				<thead>
					<tr>
						<th class="txt-center">파일명</th>
						<th class="txt-center">파일첨부</th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${empty fileList}">
						<tr>
							<td class="txt-center" colspan="2">검색 조건에 해당하는 데이터가 존재하지 않습니다.</td>
						</tr>
					</c:if>
					<c:if test="${not empty fileList}">
						<c:forEach var="data" items="${fileList}" varStatus="status" >
							<tr>
								<td>${data.FILE_DOC_NM}</td>
								<td class="txtl">
									<a href="javascript:download('${data.FILE_GRP_NO}', '${data.FILE_SN}');" class="attfile" >${data.SYS_FILE_NM}&nbsp;</a>
								</td>
							</tr>
						</c:forEach>
					</c:if>
				</tbody>
			</table>
			<div class="btm_btns">
				<button type="button" class="btn ty02" id="updtBtn">수정</button>
				<button type="button" class="btn ty04" id="listBtn">목록</button>
			</div>
		</div>
	</form>
</div>

<form id="listFrm" method="POST">
	<input type="hidden" name="resourceName" value="${comFn:nvl(param.resourceName, resourceName)}" >
</form> 

<form id="downloadFrm" method="POST">
	<input type="hidden" name="P_FILE_GRP_NO" value="" >
	<input type="hidden" name="P_FILE_SN" value="" >
</form> 

<form id="updtFrm" method="POST">
	<input type="hidden" name="resourceName" value="${comFn:nvl(param.resourceName, resourceName)}" >
	<input type="hidden" name="P_CD_DTL_ID" id="P_CD_DTL_ID" value="${dsgnRecmDetail.CD_DTL_ID}">
</form>

<form id="ubiPopupFrm" method="POST" > 
	<input type="hidden" name="P_JRF" value=""/>
	<input type="hidden" name="P_ARG" value=""/>
	<input type="hidden" name="P_CD_DTL_ID" value="${dsgnRecmDetail.CD_DTL_ID}">
</form>

<form id="popupFrm" method="POST"> 
	<input type="hidden" name="resourceName" value="${comFn:nvl(param.resourceName, resourceName)}" >
	<input type="hidden" name="P_FILE_GRP_NO" >
</form>
