<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 정보관리 > 설계추천파일관리 등록
 *
 * <pre>
 * sytm 
 *    |_ dsgnRecmFileRegForm.jsp
 * 
 * </pre>
 * @date : 2020.10.28
 * @version : 1.0
 * @author : jane
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld"%>
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld"%>

<script type="text/javascript" src="${jsPath}/ipro/views/sytm/dsgnRecmFileRegForm.js"></script>

<div class="sp_sec">
	<div class="nav_sec">
		<ul id="breadcrumbs">
			<li class="home">홈</li>
			<li>${myMenuList.bigMenuNm}</li>
			<li>${myMenuSubList.smallMenuNm}</li> 
		</ul>
	</div><!--// nav_sec -->
	<h3 class="sp_tit">설계추천파일관리 등록</h3>

	<form id="registFrm" method="POST" enctype="multipart/form-data">
		<input type="hidden" name="P_REGR_NM" value="${sessionScope.loginResult.USR_NM}">
		<input type="hidden" name="P_USR_ID" value="${sessionScope.loginResult.USR_ID}" >
		<input type="hidden" name="resourceName" value="${comFn:nvl(param.resourceName, resourceName)}" >
		<div class="sp_cont">
			<p class="spc_stit">설계추천파일관리</p>
			<table class="form_tb">
				<colgroup>
					<col width="15%">
					<col width="35%">
					<col width="15%">
					<col width="35%">
				</colgroup>
				<tr>
					<th>등록자</th>
					<td>${sessionScope.loginResult.USR_NM}</td>
					<th>등록일자</th>
					<td>${comFn:formatDate(P_REG_DT, 'yyyyMMddHHmmss', 'yyyy-MM-dd')}</td>
				</tr>
				<tr>
					<th>설계파일ID</th>
					<td>자동생성</td>
					<th></th>
					<td></td>
				</tr>
				<%-- <tr>
					<th>계약구분</th>
					<td><comTag:comCmcdCdValueComboBox id="P_CONT_SECD" name="P_CONT_SECD" cdId="CONT_SECD"  selectKey="" cond2="" headerValue="전체" className="select w50p"/></td>
					<th>계약방법</th>
					<td><comTag:comCmcdCdValueComboBox id="P_CONT_MTCD" name="P_CONT_MTCD" cdId="CONT_MTCD"  selectKey="" cond2="" headerValue="전체" className="select w50p"/></td>
				</tr>
				<tr>
					<th>추정가격</th>
					<td>
						<input type="text"  id="P_ESTT_PRCE_ST"  name="P_ESTT_PRCE_ST"  money class="input rt w30p" placeholder="추정가격"> ~ <input type="text"  id="P_ESTT_PRCE_END"  name="P_ESTT_PRCE_END"  money class="input rt w30p" placeholder="추정가격">
						(부가세 미포함)
					</td>
					<th>긴급입찰</th>
					<td>
						<div class="rad_g">
                			<comTag:cmmnCdValueRadio name="P_EMRG_YN" selectKey="N" list="{'N':'미해당', 'Y':'해당'}"/>
                		</div>
					</td>
				</tr> --%>
				<tr>
					<th>설계파일명</th>
					<td colspan="3"><input type="text"  id="P_CD_DTL_NM" name="P_CD_DTL_NM" placeholder="설계파일명"></td>
				</tr>
				
				<tr>
					<th>추천코드</th>
					<td><input type="text"  id="P_RECM_ID" name="P_RECM_ID" placeholder="추천코드"></td>
					<th></th>
					<td></td>
				</tr>
			</table>
			
			<p class="spc_stit">첨부파일
				<button type="button" class="btn btn" id="delBtn" name="delBtn">삭제</button>
				<button type="button" class="btn btn_add" id="addBtn" name="addBtn">추가</button>
			</p>
			<table class="tb">
				<colgroup>
					<col width="5%">
					<col width="20%">
					<col width="*">
				</colgroup>
				<thead>
					<tr>
						<th scope="col" style="text-align: center; padding:0;">
							<input type="checkbox" id="allChk" style="cursor: pointer;"> 
						</th>
						<th class="txtc">파일명</th>
						<th class="txtc">파일첨부</th>
					</tr>
				</thead> 
				<tbody id="copyArea" style="display: none;">
					<tr>
						<td style="text-align: center;"><input type="checkbox" class="fileDel"></td>
						<td>
							<input type="text" id="P_FILE_DOC_NM"  class="input w80p"  placeholder="파일명" />
						</td>
						<td class="vtop"> 
							<input type="file" style="width: 100%" onchange="fileSet(this);">
						</td> 
					</tr>
				</tbody>
				<tbody id="pasteArea">
					<tr>
						<td style="text-align: center;">
							<input type="checkbox" class="fileDel" style="cursor: pointer;"> 
						</td>
						<td><input type="text" id="P_FILE_DOC_NM"  name="P_FILE_DOC_NM" class="input w80p"  placeholder="파일명" /></td>
						<td class="vtop"> 
							<input type="file" name="P_FILE" style="width: 100%" onchange="fileSet(this);">
	                    </td>	
	             	</tr>
				</tbody>
			</table>
				
			<div class="btm_btns">
				<button type="button" class="btn ty02" id="saveBtn">저장</button>
				<button type="button" class="btn ty04" id="listBtn">목록</button>
			</div>
		</div>
	</form>
	
</div>


<form id="listFrm" method="POST">
	<input type="hidden" name="resourceName" value="${comFn:nvl(param.resourceName, resourceName)}">
</form>

<!-- DETAIL FORM -->
<form id="detailFrm" method="POST" > 
	<input type="hidden" name="resourceName" value="${comFn:nvl(param.resourceName, resourceName)}"  >
	<input type="hidden" name="P_CD_DTL_ID">
</form>
