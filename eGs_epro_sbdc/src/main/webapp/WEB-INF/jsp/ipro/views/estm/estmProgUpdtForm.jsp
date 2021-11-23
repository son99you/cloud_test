<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 평가관리 > 평가진행현황 수정
 *
 * <pre>
 * estm
 *    |_ estmProgUpdtForm.jsp
 * 
 * </pre>
 * @date : 2021. 03. 31.
 * @version : 1.0
 * @author : 은우소프트
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %> 
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %> 

<script type="text/javascript" src="${jsPath}/ipro/views/estm/estmProgUpdtForm.js"></script>
<script type="text/javascript" src="/raonkupload/js/raonkupload.js"></script>

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
					<li style="font-size: 30px; font-weight: 500;">평가진행현황 수정</li>
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
<div class="area-detail">
	<form id="updtFrm" name="updtFrm" method="POST" enctype="multipart/form-data">
		<input type="hidden" name="resourceDesc" value="${ param.resourceDesc}">
		<input type="hidden" name="resourceName" value="${comFn:nvl(param.resourceName, resourceName)}">
		<input type="hidden" name="P_REGR_NM" value="${sessionScope.loginResult.USR_NM}">
		<input type="hidden" name="P_USR_ID" value="${sessionScope.loginResult.USR_ID}">
		<input type="hidden" name="P_ESTM_NO" value="${estmMngMstDetail.ESTM_NO }">
		<input type="hidden" name="P_ESTM_PSCD" value="${estmMngMstDetail.ESTM_PSCD }">
		<input type="hidden" id="P_ESTM_SPHE_SECD_DATA" value="${estmMngMstDetail.ESTM_SPHE_SECD }">
		<input type="hidden" id="P_FILE_GRP_NO" name="P_FILE_GRP_NO" value="${estmMstFile.FILE_GRP_NO}">
		<input type="hidden" id="P_FILE_GRP_NO_NEW" name="P_FILE_GRP_NO_NEW">		
		<input type="hidden" id="P_SIGN_FILE_GRP_NO" name="P_SIGN_FILE_GRP_NO" value="${estmSignFile.FILE_GRP_NO}">
		<input type="hidden" id="resultCode" value="${param.resultCode}">
		<input type="hidden" id="P_DELETE_FILE_SN" name="P_DELETE_FILE_SN">
		<input type="hidden" id="D_ESTM_SECD" value="${estmMngMstDetail.ESTM_SECD }">
		
		
		<input type="hidden" id="P_RETURN_URL" name="P_RETURN_URL" value="/estm/estmProgDetail.do">
		
		<div class="table-detail">
			<!-- Top -->
			<div class="top-detail">
				<div class="type-fleft">
					<div class="contents-label">기본정보</div>
				</div>
				
				<div class="type-fright">
					<a href="javascript:" class="component-button-s type-s" id="estmInfoListPopup">평가정보불러오기</a>
				</div>
			</div>
			<!-- //Top -->
	
			<table class="component-detail-table type-line-none">
				<colgroup>
					<col width="15%">
					<col width="35%">
					<col width="15%">
					<col width="35%">
				</colgroup>
				<tbody>
					<tr>
						<th>평가번호</th>
						<td>${estmMngMstDetail.ESTM_NO }</td>
						<th>평가진행상태</th>
						<td>${estmMngMstDetail.ESTM_PSCD_NM }</td>
					</tr>
		
					<tr>
						<th><i class="icon-necessary"></i>평가명</th>
						<td colspan="3">
							<input type="text" id="P_ESTM_NM" name="P_ESTM_NM" value="${estmMngMstDetail.ESTM_NM }" class="component-input w100" maxlength="600">
						</td>
					</tr>
					<tr>
						<th><i class="icon-necessary"></i>평가구분</th>
						<td>
							<%-- <comTag:comCmcdCdValueComboBox id="P_ESTM_SECD" name="P_ESTM_SECD" cdId="ESTM_SECD" selectKey="${estmMngMstDetail.ESTM_SECD }" headerKey="" cond1="ESTM_PROG" headerValue="전체" className="component-select"/> --%>
							<select id="P_ESTM_SECD" name="P_ESTM_SECD" class="component-select w100"></select>
						</td>
						<th>평가정보연계번호</th>
						<td><input type="text" id="P_ESTM_INFO_CNTC_NO" name="P_ESTM_INFO_CNTC_NO" value="${estmMngMstDetail.ESTM_INFO_CNTC_NO }" class="component-input" readonly="readonly"></td>
					</tr>
					<tr>
						<th><i class="icon-necessary"></i>평가분야구분</th>
						<td>
							<select id="P_ESTM_SPHE_SECD" name="P_ESTM_SPHE_SECD" class="component-select w100"></select>
						</td>
						<th><i class="icon-necessary"></i>평가대상구분</th>
						<td>
							<comTag:comCmcdCdValueComboBox id="P_ESTM_OBJ_SECD" name="P_ESTM_OBJ_SECD" cdId="ESTM_OBJ_SECD" selectKey="${estmMngMstDetail.ESTM_OBJ_SECD }" headerKey="" headerValue="전체" className="component-select"/>
						</td>
					</tr>
					
					<tr>
						<th><i class="icon-necessary"></i>평가담당자</th>
						<td>
							<div class="component-input-search type-division">
								<input type="text" id="P_ESTM_CHRGR_NM" name="P_ESTM_CHRGR_NM" value="${estmMngMstDetail.ESTM_CHRGR_NM }" class="component-input" readonly="readonly">
								<input type="hidden" id="P_ESTM_CHRGR_ID" name="P_ESTM_CHRGR_ID" value="${estmMngMstDetail.ESTM_CHRGR_ID }">
								<a href="javascript:" class="btn-search" id="estmChrgrSrchBtn"></a>
							</div>
						</td>
						<th><i class="icon-necessary"></i>평가담당부서</th>
						<td>
							<input type="text" id="P_ESTM_CHRG_DEPT_NM" name="P_ESTM_CHRG_DEPT_NM" value="${estmMngMstDetail.ESTM_CHRG_DEPT_NM }" class="component-input" readonly="readonly">
							<input type="hidden" id="P_ESTM_CHRG_DEPT_NO" name="P_ESTM_CHRG_DEPT_NO" value="${estmMngMstDetail.ESTM_CHRG_DEPT_NO }">
						</td>
					</tr>
					<tr>
						<th><i class="icon-necessary"></i>평가시작일시</th>
						<td>
							<div class="area-calen">
								<!-- Component Calen -->
								<div class="component-calen">
									<div class="data-calen">
										<input type="text" id="P_TOTL_ESTM_ST_DE" name="P_TOTL_ESTM_ST_DE" value="${comFn:formatDate(estmMngMstDetail.TOTL_ESTM_ST_DT,'yyyyMMddHHmmss','yyyy-MM-dd')}" class="component-input" date>
									</div>
								</div>
								<!-- //Component Calen -->
		
								<!-- Component Time -->
								<div class="component-time">
									<input type="text" id="P_TOTL_ESTM_ST_HH" name="P_TOTL_ESTM_ST_HH" value="${comFn:formatDate(estmMngMstDetail.TOTL_ESTM_ST_DT,'yyyyMMddHHmmss','HH')}" class="component-input" numeric maxlength="2" onkeyup="fnTimeHHChk(this);">
									<em class="time-bar">:</em>
									<input type="text" id="P_TOTL_ESTM_ST_MM" name="P_TOTL_ESTM_ST_MM" value="${comFn:formatDate(estmMngMstDetail.TOTL_ESTM_ST_DT,'yyyyMMddHHmmss','mm')}" class="component-input" numeric maxlength="2" onkeyup="fnTimeMMChk(this);" >
								</div>
								<!-- //Component Time -->
							</div>
						</td>
						<th><i class="icon-necessary"></i>평가종료일시</th>
						<td>
							<div class="area-calen">
								<!-- Component Calen -->
								<div class="component-calen">
									<div class="data-calen">
										<input type="text" id="P_TOTL_ESTM_END_DE" name="P_TOTL_ESTM_END_DE" value="${comFn:formatDate(estmMngMstDetail.TOTL_ESTM_END_DT,'yyyyMMddHHmmss','yyyy-MM-dd')}" class="component-input" date>
										
									</div>
								</div>
								<!-- //Component Calen -->
		
								<!-- Component Time -->
								<div class="component-time">
									<input type="text" id="P_TOTL_ESTM_END_HH" name="P_TOTL_ESTM_END_HH" value="${comFn:formatDate(estmMngMstDetail.TOTL_ESTM_END_DT,'yyyyMMddHHmmss','HH')}" class="component-input" numeric maxlength="2" onkeyup="fnTimeHHChk(this);">
									<em class="time-bar">:</em>
									<input type="text" id="P_TOTL_ESTM_END_MM" name="P_TOTL_ESTM_END_MM" value="${comFn:formatDate(estmMngMstDetail.TOTL_ESTM_END_DT,'yyyyMMddHHmmss','mm')}" class="component-input" numeric maxlength="2" onkeyup="fnTimeMMChk(this);" >
								</div>
								<!-- //Component Time -->
							</div>
						</td>
					</tr>
					<tr>
						<th>실평가여부</th>
						<td>
							<comTag:cmmnCdValueRadio name="P_REAL_ESTM_YN" selectKey="${estmMngMstDetail.REAL_ESTM_YN }" list="{'Y' :'예', 'N':'아니오'}"/>
						</td>
						<th>최고/최저점제외여부</th>
						<td>
							<comTag:cmmnCdValueRadio name="P_MXMN_SCR_EXCP_YN" selectKey="${estmMngMstDetail.MXMN_SCR_EXCP_YN }" list="{'Y' :'예', 'N':'아니오'}"/>
						</td>
					</tr>
					<tr>
						<th><i class="icon-necessary"></i>외부평가위원선정방법</th>
						<td>
							<comTag:comCmcdCdValueComboBox id="P_OUT_CMTM_SLCT_MTHD_SECD" name="P_OUT_CMTM_SLCT_MTHD_SECD" cdId="ESTM_CMTM_SLCT_MTHD" selectKey="${estmMngMstDetail.OUT_CMTM_SLCT_MTHD_SECD }"  headerKey="" headerValue="전체" className="component-select"/>
						</td>
						<th><i class="icon-necessary"></i>내부평가위원선정방법</th>
						<td>
							<comTag:comCmcdCdValueComboBox id="P_INN_CMTM_SLCT_MTHD_SECD" name="P_INN_CMTM_SLCT_MTHD_SECD" cdId="ESTM_CMTM_SLCT_MTHD"  selectKey="${estmMngMstDetail.INN_CMTM_SLCT_MTHD_SECD }" cond1="INN" headerKey="" headerValue="전체" className="component-select"/>
						</td>
					</tr>
					<tr>
						<th>외부평가위원수</th>
						<td><input type="text" id="P_OUT_ESTM_CMTM_CNT" name="P_OUT_ESTM_CMTM_CNT" value="${estmMngMstDetail.OUT_ESTM_CMTM_CNT }" class="component-input w20" numeric maxlength="2"> 명</td>
						<th>내부평가위원수</th>
						<td><input type="text" id="P_INN_ESTM_CMTM_CNT" name="P_INN_ESTM_CMTM_CNT" value="${estmMngMstDetail.INN_ESTM_CMTM_CNT }" class="component-input w20" numeric maxlength="2"> 명</td>
					</tr>
					<tr>
						<th>외부평가위원배수</th>
						<td><input type="text" id="P_OUT_ESTM_CMTM_TMES" name="P_OUT_ESTM_CMTM_TMES" value="${estmMngMstDetail.OUT_ESTM_CMTM_TMES }" class="component-input w20" numeric maxlength="2"> 배수</td>
						<th>내부평가위원배수</th>
						<td><input type="text" id="P_INN_ESTM_CMTM_TMES" name="P_INN_ESTM_CMTM_TMES" value="${estmMngMstDetail.INN_ESTM_CMTM_TMES }" class="component-input w20" numeric maxlength="2"> 배수</td>
					</tr>
					<tr>
						<th>지정평가위원수</th>
						<td><input type="text" id="P_FIX_ESTM_CMTM_CNT" name="P_FIX_ESTM_CMTM_CNT" value="${estmMngMstDetail.FIX_ESTM_CMTM_CNT }" class="component-input w20" numeric maxlength="2"> 명</td>
						<th></th>
						<td></td>
					</tr>
					<tr>
						<th>우선순위선정자</th>
						<td>
							<div class="component-input-search type-division">
								<input type="text" id="P_PRIO_RNK_SLCT_PE_NM" name="P_PRIO_RNK_SLCT_PE_NM" value="${estmMngMstDetail.PRIO_RNK_SLCT_PE_NM }" class="component-input" readonly="readonly">
								<input type="hidden" id="P_PRIO_RNK_SLCT_PE_ID" name="P_PRIO_RNK_SLCT_PE_ID" value="${estmMngMstDetail.PRIO_RNK_SLCT_PE_ID }">
								<a href="javascript:" class="btn-search-close" id="prioRnkSlctPeDelBtn"></a>
								<a href="javascript:" class="btn-search" id="prioRnkSlctPeSrchBtn"></a>
							</div>
						</td>
						<th>우선순위선정부서</th>
						<td>
							<input type="text" id="P_PRIO_RNK_SLCT_DEPT_NM" name="P_PRIO_RNK_SLCT_DEPT_NM" value="${estmMngMstDetail.PRIO_RNK_SLCT_DEPT_NM }" class="component-input" readonly="readonly">
							<input type="hidden" id="P_PRIO_RNK_SLCT_DEPT_NO" name="P_PRIO_RNK_SLCT_DEPT_NO" value="${estmMngMstDetail.PRIO_RNK_SLCT_DEPT_NO }">
						</td>
					</tr>
					<tr>
						<th>
							비고
						</th>
						<td colspan="3">
							<textarea name="P_RMK" class="component-textarea" maxlength="4000">${estmMngMstDetail.RMK}</textarea>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
		<div class="table-detail">
			<!-- Top -->
			<div class="top-detail">
				<div class="type-fleft">
					<div class="contents-label">평가절차</div>
				</div>
			</div>
			<!-- //Top -->
	
			<table class="component-detail-table ">
				<colgroup>
					<col width="60">
					<col width="150">
					<col width="150">
					<col width="160">
					<col width="*">
					<col width="*">
				</colgroup>
				<thead>
					<tr>
						<th>선택</th>
						<th>평가절차구분</th>
						<th>평가자구분</th>
						<th>평가절차명</th>
						<th>평가절차 시작일시</th>
						<th>평가절차 종료일시</th>
					</tr>
				</thead>
				<tbody id="estmShowTbdy">
					<c:forEach var="data" items="${estmProcdList}" varStatus="status">
						<tr>
							<td class="txt-center">
								<label class="component-checkbox">
									<input type="checkbox" id="estmCbx" name="estmCbx">
									<i></i>
								</label>
							</td>
							
						<td>
							<comTag:comCmcdCdValueComboBox id="P_ESTM_PROCD_SECD" name="P_ESTM_PROCD_SECD" cdId="ESTM_PROCD_SECD" selectKey="${data.ESTM_PROCD_SECD }" headerKey="" headerValue="전체" className="component-select w85"/>
						</td>
						<td>
							<comTag:comCmcdCdValueComboBox id="P_ESTR_SECD" name="P_ESTR_SECD" cdId="ESTR_SECD"  selectKey="${data.ESTR_SECD }" headerKey="" headerValue="전체" className="component-select w85"/>
						</td>
						<td>
							<input type="text" name="P_ESTM_PROCD_NM" value="${data.ESTM_PROCD_NM }" class="component-input w100">
							<input type="hidden" name="P_ESTM_FRM_NO" value="${data.ESTM_FRM_NO }">
						</td>
						<td>
							<div class="area-calen">
								<!-- Component Calen -->
								<div class="component-calen">
									<div class="data-calen">
										<input type="text" name="P_ESTM_PROCD_ST_DE" value="${comFn:formatDate(data.ESTM_PROCD_ST_DT,'yyyyMMddHHmmss', 'yyyy-MM-dd')}" class="component-input" date>
									</div>
								</div>
								<!-- //Component Calen -->
		
								<!-- Component Time -->
								<div class="component-time">
									<input type="text" name="P_ESTM_PROCD_ST_HH" value="${comFn:formatDate(data.ESTM_PROCD_ST_DT,'yyyyMMddHHmmss', 'HH')}" class="component-input" maxlength="2" onblur="fnTimeHHChk(this)">
									<em class="time-bar">:</em>
									<input type="text" name="P_ESTM_PROCD_ST_MM" value="${comFn:formatDate(data.ESTM_PROCD_ST_DT,'yyyyMMddHHmmss', 'mm')}" class="component-input" maxlength="2" onblur="fnTimeMMChk(this)">
								</div>
								<!-- //Component Time -->
							</div>
						</td>
						<td>
							<div class="area-calen">
								<!-- Component Calen -->
								<div class="component-calen">
									<div class="data-calen">
										<input type="text" name="P_ESTM_PROCD_END_DE" value="${comFn:formatDate(data.ESTM_PROCD_END_DT,'yyyyMMddHHmmss', 'yyyy-MM-dd')}" class="component-input" date>
									</div>
								</div>
								<!-- //Component Calen -->
		
								<!-- Component Time -->
								<div class="component-time">
									<input type="text" name="P_ESTM_PROCD_END_HH" value="${comFn:formatDate(data.ESTM_PROCD_END_DT,'yyyyMMddHHmmss', 'HH')}" class="component-input" maxlength="2" onblur="fnTimeHHChk(this)">
									<em class="time-bar">:</em>
									<input type="text" name="P_ESTM_PROCD_END_MM" value="${comFn:formatDate(data.ESTM_PROCD_END_DT,'yyyyMMddHHmmss', 'mm')}" class="component-input" maxlength="2" onblur="fnTimeMMChk(this)">
								</div>
								<!-- //Component Time -->
							</div>
						</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
	
		</div>
	
		<div class="table-detail">
			<!-- Top -->
			<div class="top-detail">
				<div class="contents-label">첨부파일</div>
	
			</div>
			<!-- //Top -->
	
			<table class="component-detail-table type-file">
				<colgroup>
					<col width="15%">
					<col width="85%">
				</colgroup>
				<tbody>
					<tr>
						<th>첨부파일</th>
						<td colspan="3">
							<div class="fileViewer">
								<!-- 업로드 삽입. -->
								<script type="text/javascript">
									var raonkParam = {
							            Id: "uploadView1",
							            Width: "100%",
							            Height: "200px",
							            ButtonBarEdit: "add,remove,remove_all",
							            BorderStyle: "solid",
							            FolderNameRule: "yyyy/mm/dd/estm" 
							        };
									var raonkUpload = new RAONKUpload(raonkParam);
								</script>
							</div>
							<div id="upload_fileInfo"></div>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
		
		<div class="table-detail">
			<!-- Top -->
			<div class="top-detail">
				<div class="type-fleft">
					<div class="contents-label">심사위원 서명파일</div>
				</div>
				<!-- //Top -->
				
				<div class="type-fright">
					<a href="javascript:" class="component-button-s type-add fileAddBtn">추가</a>
				</div>
			</div>
			
			<table class="component-detail-table type-file"> 
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
				<tbody id="signFileTbody">
                	<c:if test="${not empty estmSignFileList }">
                  		<c:forEach items="${estmSignFileList}" var="data" varStatus="status">
                  			<tr>
                  				<th style="padding:7px 15px 7px 15px;">${data.FILE_DOC_NM}</th>
                  				<td style="padding:10px 10px 2px 13px;">
	                   				<a href="javascript:download('${data.FILE_GRP_NO}', '${data.FILE_SN}');">${data.SYS_FILE_NM }</a>
	                   				<button type="button" class="component-button-s type-del" onclick="fileDel(this,'${data.FILE_SN }')" style="float: right;">삭제</button>
                  				</td>
                  			</tr>
	                   	</c:forEach>
                   	</c:if>
				</tbody>
			</table> 
		</div>
	
		<!-- bottom button -->
		<div class="bottom-buttons">
			<a href="javascript:" class="btn-bottom type-b" id="saveBtn">저장</a>
			<a href="javascript:" class="btn-bottom type-a" id="cnclBtn">취소</a>
		</div>
		<!-- //bottom button -->
	
	</form>
</div>
<!-- //Detail -->


<!-- LIST FORM -->
<form id="detailFrm" method="POST">
	<input type="hidden" name="resourceDesc" value="${ param.resourceDesc}">
	<input type="hidden" name="resourceName" value="${comFn:nvl(param.resourceName, resourceName)}">
	<input type="hidden" name="P_ESTM_NO" value="${estmMngMstDetail.ESTM_NO }">
</form>

<!-- POPUP FORM -->
<form id="popupFrm" method="POST">
	<input type="hidden" name="resourceName" value="${comFn:nvl(param.resourceName, resourceName)}">
	<input type="hidden" name="resourceDesc" value="${param.resourceDesc}">
	<input type="hidden" name="setChargerGbn">
</form>

<!-- DOWNLOAD FORM -->
<form id="downloadFrm" method="POST">
	<input type="hidden" name="P_FILE_SN">
	<input type="hidden" name="P_FILE_GRP_NO">
</form>

<form id="selectEstmProcdSecdFrm" method="POST" style="display: none;">
	<comTag:comCmcdCdValueComboBox id="P_ESTM_PROCD_SECD" name="P_ESTM_PROCD_SECD" cdId="ESTM_PROCD_SECD"  headerKey="" headerValue="전체" className="component-select w85"/>
</form>

<form id="selectEstrSecdFrm" method="POST" style="display: none;">
	<comTag:comCmcdCdValueComboBox id="P_ESTR_SECD" name="P_ESTR_SECD" cdId="ESTR_SECD"  headerKey="" headerValue="전체" className="component-select w85"/>
</form>

<!-- AJAX FORM -->
<form id="ajaxForm" method="POST">
	<input type="hidden" name="resourceName" value="${comFn:nvl(param.resourceName, resourceName)}">
	<input type="hidden" name="resourceDesc" value="${param.resourceDesc}">
	<input type="hidden" name="P_ESTM_SECD">
</form>

<!-- AJAX FORM -->
<form id="ajaxEstmSecdForm" method="POST">
	<input type="hidden" name="resourceName" value="${comFn:nvl(param.resourceName, resourceName)}">
	<input type="hidden" name="resourceDesc" value="${param.resourceDesc}">
	<input type="hidden" name="P_CD_ID" value="ESTM_SECD">
	<input type="hidden" name="P_ETC_VAL1" value="ESTM_PROG">
	<input type="hidden" name="P_ETC_VAL2" value="${sessionScope.loginResult.DEPT_NO}">
</form>