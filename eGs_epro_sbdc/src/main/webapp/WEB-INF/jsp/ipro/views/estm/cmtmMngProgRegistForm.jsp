<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
  * 평가관리 > 평가위원관리 > 평가위원평가진행현황 작성
 *
 * <pre>
 * estm
 *    |_ cmtmMngProgRegistForm.jsp
 * 
 * </pre>
 * @date : 2021. 03. 23.
 * @version : 1.0
 * @author : 은우소프트
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %> 
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %> 

<script type="text/javascript" src="${jsPath}/ipro/views/estm/cmtmMngProgRegistForm.js"></script>
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
					<li style="font-size: 30px; font-weight: 500;">평가위원평가진행현황 등록</li>
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
	<form id="registFrm" name="registFrm" method="POST" enctype="multipart/form-data">
		<input type="hidden" name="resourceDesc" value="${ param.resourceDesc}">
		<input type="hidden" name="resourceName" value="${comFn:nvl(param.resourceName, resourceName)}">
		<input type="hidden" name="P_REGR_NM" value="${sessionScope.loginResult.USR_NM}">
		<input type="hidden" name="P_USR_ID" value="${sessionScope.loginResult.USR_ID}">
		<input type="hidden" id="P_FILE_GRP_NO" name="P_FILE_GRP_NO">
		
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
						<td>자동생성</td>
						<th>평가진행상태</th>
						<td>평가작성</td>
					</tr>
					<tr>
						<th><i class="icon-necessary"></i>평가명</th>
						<td colspan="3">
							<input type="text" id="P_ESTM_NM" name="P_ESTM_NM" class="component-input w100" maxlength="600">
						</td>
					</tr>
					<tr>
						<th><i class="icon-necessary"></i>평가구분</th>
						<td>
							<comTag:comCmcdCdValueComboBox id="P_ESTM_SECD" name="P_ESTM_SECD" cdId="ESTM_SECD" cond1="CMTM_PROG" headerValue="전체" className="component-select"/>
						</td>
						<th><i class="icon-necessary"></i>평가정보연계번호</th>
						<td><input type="text" id="P_ESTM_INFO_CNTC_NO" name="P_ESTM_INFO_CNTC_NO" class="component-input" readonly="readonly"></td>
					</tr>
					<tr>
						<th><i class="icon-necessary"></i>평가분야구분</th>
						<td>
							<select id="P_ESTM_SPHE_SECD" name="P_ESTM_SPHE_SECD" class="component-select"></select>
						</td>
						<th><i class="icon-necessary"></i>평가대상구분</th>
						<td>
							<%-- <comTag:comCmcdCdValueComboBox id="P_ESTM_OBJ_SECD" name="P_ESTM_OBJ_SECD" cdId="ESTM_OBJ_SECD" headerValue="전체" className="component-select"/> --%>
							평가위원
							<input type="hidden" name="P_ESTM_OBJ_SECD" value="C"/>
						</td>
					</tr>
					
					<tr>
						<th><i class="icon-necessary"></i>평가담당자</th>
						<td>
							<div class="component-input-search type-division">
								<input type="text" id="P_ESTM_CHRGR_NM" name="P_ESTM_CHRGR_NM" value="${P_ESTM_CHRGR_NM }" class="component-input" readonly="readonly">
								<input type="hidden" id="P_ESTM_CHRGR_ID" name="P_ESTM_CHRGR_ID" value="${P_ESTM_CHRGR_ID }">
								<a href="javascript:" class="btn-search" id="estmChrgrSrchBtn"></a>
							</div>
						</td>
						<th><i class="icon-necessary"></i>평가담당부서</th>
						<td>
							<input type="text" id="P_ESTM_CHRG_DEPT_NM" name="P_ESTM_CHRG_DEPT_NM" value="${P_ESTM_CHRG_DEPT_NM }" class="component-input" readonly="readonly">
							<input type="hidden" id="P_ESTM_CHRG_DEPT_NO" name="P_ESTM_CHRG_DEPT_NO" value="${P_ESTM_CHRG_DEPT_NO }">
						</td>
					</tr>
					<tr>
						<th><i class="icon-necessary"></i>평가시작일시</th>
						<td>
							<div class="area-calen">
								<!-- Component Calen -->
								<div class="component-calen">
									<div class="data-calen">
										<input type="text" id="P_TOTL_ESTM_ST_DE" name="P_TOTL_ESTM_ST_DE" class="component-input" date>
										
									</div>
								</div>
								<!-- //Component Calen -->
		
								<!-- Component Time -->
								<div class="component-time">
									<input type="text" id="P_TOTL_ESTM_ST_HH" name="P_TOTL_ESTM_ST_HH" class="component-input" numeric maxlength="2" onkeyup="fnTimeHHChk(this);">
									<em class="time-bar">:</em>
									<input type="text" id="P_TOTL_ESTM_ST_MM" name="P_TOTL_ESTM_ST_MM" class="component-input" numeric maxlength="2" onkeyup="fnTimeMMChk(this);">
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
										<input type="text" id="P_TOTL_ESTM_END_DE" name="P_TOTL_ESTM_END_DE" class="component-input" date>
										
									</div>
								</div>
								<!-- //Component Calen -->
		
								<!-- Component Time -->
								<div class="component-time">
									<input type="text" id="P_TOTL_ESTM_END_HH" name="P_TOTL_ESTM_END_HH" class="component-input" numeric maxlength="2" onkeyup="fnTimeHHChk(this);">
									<em class="time-bar">:</em>
									<input type="text" id="P_TOTL_ESTM_END_MM" name="P_TOTL_ESTM_END_MM" class="component-input" numeric maxlength="2" onkeyup="fnTimeMMChk(this);">
								</div>
								<!-- //Component Time -->
							</div>
						</td>
					</tr>
					<tr>
						<th>실평가여부</th>
						<td>
							<comTag:cmmnCdValueRadio name="P_REAL_ESTM_YN" selectKey="${P_REAL_ESTM_YN }" list="{'Y' :'예', 'N':'아니오'}"/>
						</td>
						<th>최고/최저점제외여부</th>
						<td>
							<comTag:cmmnCdValueRadio name="P_MXMN_SCR_EXCP_YN" selectKey="" list="{'Y' :'예', 'N':'아니오'}"/>
						</td>
					</tr>
					<tr>
						<th><i class="icon-necessary"></i>내부평가위원선정방법</th>
						<td>
							<comTag:comCmcdCdValueComboBox id="P_INN_CMTM_SLCT_MTHD_SECD" name="P_INN_CMTM_SLCT_MTHD_SECD" cdId="ESTM_CMTM_SLCT_MTHD" cond1="INN" headerValue="전체" className="component-select"/>
						</td>
						<th>&nbsp;</th>
						<td>&nbsp;</td>
					</tr>
					<tr>
						<th>내부평가위원수</th>
						<td><input type="text" id="P_INN_ESTM_CMTM_CNT" name="P_INN_ESTM_CMTM_CNT" class="component-input w20" numeric maxlength="2"> 명</td>
						<th>내부평가위원배수</th>
						<td><input type="text" id="P_INN_ESTM_CMTM_TMES" name="P_INN_ESTM_CMTM_TMES" class="component-input w20" numeric maxlength="2"> 배수</td>
					</tr>
					<tr>
						<th>우선순위선정자</th>
						<td>
							<div class="component-input-search type-division">
								<input type="text" id="P_PRIO_RNK_SLCT_PE_NM" name="P_PRIO_RNK_SLCT_PE_NM" class="component-input" readonly="readonly">
								<input type="hidden" id="P_PRIO_RNK_SLCT_PE_ID" name="P_PRIO_RNK_SLCT_PE_ID">
								<a href="javascript:" class="btn-search-close" id="prioRnkSlctPeDelBtn"></a>
								<a href="javascript:" class="btn-search" id="prioRnkSlctPeSrchBtn"></a>
							</div>
						</td>
						<th>우선순위선정부서</th>
						<td>
							<input type="text" id="P_PRIO_RNK_SLCT_DEPT_NM" name="P_PRIO_RNK_SLCT_DEPT_NM" class="component-input" readonly="readonly">
							<input type="hidden" id="P_PRIO_RNK_SLCT_DEPT_NO" name="P_PRIO_RNK_SLCT_DEPT_NO">
						</td>
					</tr>
					<tr>
						<th>
							비고
						</th>
						<td colspan="3">
							<textarea name="P_RMK" class="component-textarea" maxlength="4000"></textarea>
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
	
				<!-- <div class="type-fright">
					<a href="javascript:" class="component-button-s type-add" id="estmAddBtn">평가추가</a>
					<a href="javascript:" class="component-button-s type-del" id="estmDelBtn">평가삭제</a>
				</div> -->
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
						<th>번호</th>
						<th>평가절차구분</th>
						<th>평가자구분</th>
						<th>평가절차명</th>
						<th>평가절차 시작일시</th>
						<th>평가절차 종료일시</th>
					</tr>
				</thead>
				<%-- <tbody id="estmHidTbdy" style="display: none;">
					<tr>
						<td class="txt-center">
							<label class="component-checkbox">
								<input type="checkbox" id="estmCbx" name="estmCbx">
								<i></i>
							</label>
						</td>
						<td>
							<comTag:comCmcdCdValueComboBox id="P_ESTM_PROCD_SECD" name="P_ESTM_PROCD_SECD" cdId="ESTM_PROCD_SECD" headerValue="전체" className="component-select w85"/>
						</td>
						<td>
							<comTag:comCmcdCdValueComboBox id="P_ESTR_SECD" name="P_ESTR_SECD" cdId="ESTR_SECD"  headerKey="" headerValue="전체" className="component-select w85"/>
						</td>
						<td><input type="text" name="P_ESTM_PROCD_NM" class="component-input w100"></td>
						<td>
							<div class="area-calen">
								<!-- Component Calen -->
								<div class="component-calen">
									<div class="data-calen">
										<input type="text" name="P_ESTM_PROCD_ST_DE" class="component-input" date>
									</div>
								</div>
								<!-- //Component Calen -->
		
								<!-- Component Time -->
								<div class="component-time">
									<input type="text" name="P_ESTM_PROCD_ST_HH" class="component-input" numeric maxlength="2">
									<em class="time-bar">:</em>
									<input type="text" name="P_ESTM_PROCD_ST_MM" class="component-input" numeric maxlength="2">
								</div>
								<!-- //Component Time -->
							</div>
						</td>
						<td>
							<div class="area-calen">
								<!-- Component Calen -->
								<div class="component-calen">
									<div class="data-calen">
										<input type="text" name="P_ESTM_PROCD_END_DE" class="component-input" date>
									</div>
								</div>
								<!-- //Component Calen -->
		
								<!-- Component Time -->
								<div class="component-time">
									<input type="text" name="P_ESTM_PROCD_END_HH" class="component-input" numeric maxlength="2">
									<em class="time-bar">:</em>
									<input type="text" name="P_ESTM_PROCD_END_MM" class="component-input" numeric maxlength="2">
								</div>
								<!-- //Component Time -->
							</div>
						</td>
					</tr>
				</tbody> --%>
				<tbody id="estmShowTbdy">
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
					<col width="120">
					<col width="*">
				</colgroup>
				<tbody>
				<tr>
					<th>첨부파일</th>
					<td colspan="3">
						<div class="fileViewer">
							<!-- 업로드 삽입. -->
							<script type="text/javascript">
								var raonkParam = {
						            Id: "upload",
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
		
		<!-- bottom button -->
		<div class="bottom-buttons">
			<a href="javascript:" class="btn-bottom type-b" id="saveBtn">저장</a>
			<a href="javascript:" class="btn-bottom type-a" id="listBtn">목록</a>
		</div>
		<!-- //bottom button -->
	
	</form>
</div>
<!-- //Detail -->


<!-- LIST FORM -->
<form id="listFrm" method="POST">
	<input type="hidden" name="resourceName" value="${comFn:nvl(param.resourceName, resourceName)}">
	<input type="hidden" name="resourceDesc" value="${param.resourceDesc}">
</form>

<!-- POPUP FORM -->
<form id="popupFrm" method="POST">
	<input type="hidden" name="resourceName" value="${comFn:nvl(param.resourceName, resourceName)}">
	<input type="hidden" name="resourceDesc" value="${param.resourceDesc}">
	<input type="hidden" name="setChargerGbn">
	<input type="hidden" name="P_E006_YN">
</form>

<form id="selectEstmProcdSecdFrm" method="POST" style="display: none;">
	<comTag:comCmcdCdValueComboBox id="P_ESTM_PROCD_SECD" name="P_ESTM_PROCD_SECD" cdId="ESTM_PROCD_SECD" headerValue="전체" className="component-select w85"/>
</form>

<form id="selectEstrSecdFrm" method="POST" style="display: none;">
	<comTag:comCmcdCdValueComboBox id="P_ESTR_SECD" name="P_ESTR_SECD" cdId="ESTR_SECD" headerValue="전체" className="component-select w85"/>
</form>

<!-- AJAX FORM -->
<form id="ajaxForm" method="POST">
	<input type="hidden" name="resourceName" value="${comFn:nvl(param.resourceName, resourceName)}">
	<input type="hidden" name="resourceDesc" value="${param.resourceDesc}">
	<input type="hidden" name="P_ESTM_SECD">
</form>