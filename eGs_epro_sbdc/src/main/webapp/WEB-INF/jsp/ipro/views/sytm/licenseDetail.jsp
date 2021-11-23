<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 정보관리 > 서식관리 상세
 *
 * <pre>
 * sytm 
 *    |_ licenseDetail.jsp
 * 
 * </pre>
 * @date : 2021. 09. 24
 * @version : 1.0
 * @author : 은우소프트 손연우
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld"%>
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld"%>

<script type="text/javascript" src="${jsPath}/ipro/views/sytm/licenseDetail.js"></script>
<script type="text/javascript" src="/raonkeditor/js/raonkeditor.js"></script>

<!-- 네비게이션 -->
<div class="area-detail">
	<div class="table-detail">
		<!-- Top -->
		<div class="nav_sec">

			<div class="btn-help" style="display:none;">
				<a href="javascript:helpPopup();">도움말</a>
			</div>
			
			<div class="option-left">
				<ul class="location">
					<li style="font-size: 30px; font-weight: 500;">라이센스 등록</li>
				</ul>
			</div>
			
			<div class="option-right">
				<ul class="location">
					<li class="home"><a href="#">홈</a></li>
					<li><a href="#">${myMenuList.bigMenuNm}</a></li>
					<li><a href="#">${myMenuSubList.smallMenuNm}</a></li>
				</ul>
			</div>
		</div>
	</div>
</div>
<!--//네비게이션 -->

<!-- Detail -->
<form id="regFrm" method="POST">
	<input type="hidden" name="resourceName" value="${comFn:nvl(param.resourceName, resourceName)}" >
	<input type="hidden" name="P_LICENSE_NO" value="${licenseDetail.LICENSE_NO}" >
	
			<div class="area-detail">
				<div class="table-detail">
					<!-- Top -->
					<div class="top-detail">
						<div class="type-fleft">
							<div class="contents-label">라이센스정보</div>
						</div>

					</div>
					<!-- //Top -->

					<table class="component-detail-table type-line-none">
						<colgroup>
							<col width="180">
							<col width="*">
							<col width="150">
							<col width="*">
						</colgroup>
						<tbody>
						<tr>
							<th><i class="icon-necessary"></i>사업자등록번호</th>
							<td>${licenseDetail.BIZRNO}</td>
							<th>진행상태</th>
							<td>${licenseDetail.LICENSE_PSCD_NM}</td>
						</tr>
						<tr>
							<th>
								업체명
							</th>
							<td colspan="3">
								${licenseDetail.VEND_NM}
							</td>
						</tr>
						<tr>
							<th>버전</th>
							<td>${licenseDetail.VERSION_NM}</td>
							<th>발급구분</th>
							<td>${licenseDetail.LICENSE_PROCD_NM}</td>
						</tr>
						<tr>
							<th>
								발급기간
							</th>
							<td colspan="3">
								${comFn:formatDate(licenseDetail.LICENSE_ST_DATE, 'yyyyMMdd', 'yyyy-MM-dd')} ~ ${comFn:formatDate(licenseDetail.LICENSE_END_DATE, 'yyyyMMdd', 'yyyy-MM-dd')}
							</td>
						</tr>
						<tr style="height: 50px;">
							<th>
								IP주소 <br> (여러개 인경우 콤마[,]로 표시)
							</th>
							<td colspan="3">
								${licenseDetail.VEND_IP_ADDR}
							</td>
						</tr>
						</tbody>
					</table>
				</div>
				
				<div class="table-detail">
			<!-- Top -->
			<div class="top-detail">
				<div class="type-fleft">
					<div class="contents-label">라이센스파일</div>
				</div>
				<!-- //Top -->
			</div>
			
			<table class="component-detail-table"> 
				<colgroup>
					<col width="30%">
					<col width="70%">
				</colgroup>
				<thead>
					<tr>
						<th>문서명</th>
						<th>파일첨부</th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${empty licenseFileList}">
						<tr>
							<td colspan="2" class="txt-center">검색 조건에 해당하는 데이터가 존재하지 않습니다.</td>
						</tr>
					</c:if>
                	<c:if test="${not empty licenseFileList }">
                  		<c:forEach items="${licenseFileList}" var="data" varStatus="status">
                  			<tr>
                  				<th>${data.FILE_DOC_NM}</th>
                  				<td><a href="javascript:download('${data.FILE_GRP_NO}', '${data.FILE_SN}');">${data.SYS_FILE_NM }</a></td>
                  			</tr>
	                   	</c:forEach>
                   	</c:if>
				</tbody>
			</table> 
		</div>

				<!-- bottom button -->
				<div class="bottom-buttons">
					<a href="javascript:" id="licenseBtn" class="btn-bottom type-b">라이센스발급</a>
					<a href="javascript:" id="updateBtn" class="btn-bottom type-b">수정</a>
					<a href="javascript:" id="delBtn" class="btn-bottom type-b">삭제</a>
					<a href="javascript:" id="listBtn" class="btn-bottom type-a">목록</a>
				</div>
				<!-- //bottom button -->
			</div>
			<!-- //Detail -->
		</div>
</form>

<form id="listFrm" method="POST">
	<input type="hidden" name="resourceName" value="${comFn:nvl(param.resourceName, resourceName)}" >
</form> 

<!-- DOWNLOAD FORM -->
<form id="downloadFrm" method="POST">
	<input type="hidden" name="P_FILE_SN">
	<input type="hidden" name="P_FILE_GRP_NO">
</form>


<form id="licenseFrm" method="POST">
	<input type="hidden" name="resourceName" value="${comFn:nvl(param.resourceName, resourceName)}" >
	<input type="hidden" name="P_LICENSE_NO" id="P_LICENSE_NO" value="${licenseDetail.LICENSE_NO}">
</form>

<form id="updtFrm" method="POST">
	<input type="hidden" name="resourceName" value="${comFn:nvl(param.resourceName, resourceName)}" >
	<input type="hidden" name="P_LICENSE_NO" id="P_LICENSE_NO" value="${licenseDetail.LICENSE_NO}">
</form>

<form id="ubiPopupFrm" method="POST" > 
	<input type="hidden" name="P_JRF" value=""/>
	<input type="hidden" name="P_ARG" value=""/>
	<input type="hidden" name="P_LICENSE_NO" id="P_LICENSE_NO" value="${licenseDetail.LICENSE_NO}">
</form>

<form id="popupFrm" method="POST"> 
	<input type="hidden" name="resourceName" value="${comFn:nvl(param.resourceName, resourceName)}" >
	<input type="hidden" name="P_FILE_GRP_NO" >
</form>