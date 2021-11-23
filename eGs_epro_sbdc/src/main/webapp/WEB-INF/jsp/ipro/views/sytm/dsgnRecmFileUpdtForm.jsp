<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 정보관리 > 설계추천파일관리 수정
 *
 * <pre>
 * sytm
 *    |_ dsgnRecmFileUpdtForm.jsp
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

<script type="text/javascript" src="/raonkeditor/js/raonkeditor.js"></script>
<script type="text/javascript" src="${jsPath}/ipro/views/sytm/dsgnRecmFileUpdtForm.js"></script>

<div class="sp_sec">
	<div class="nav_sec">
		<ul id="breadcrumbs">
			<li class="home">홈</li>
			<li>${myMenuList.bigMenuNm}</li>
			<li>${myMenuSubList.smallMenuNm}</li> 
		</ul>
	</div><!--// nav_sec -->
	<h3 class="sp_tit">설계추천파일관리 수정</h3>
	
	<form id="updtFrm" method="POST" enctype="multipart/form-data">
		<input type="hidden" name="P_CD_DTL_ID" id="P_CD_DTL_ID" value="${dsgnRecmDetail.CD_DTL_ID}">
		<input type="hidden" name="resourceName" value="${comFn:nvl(param.resourceName, resourceName)}" >
		<input type="hidden" name="P_FILE_GRP_NO" value="${dsgnRecmDetail.FILE_GRP_NO}">
		
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
					<td>${dsgnRecmDetail.REGR_ID}</td>
					<th>등록일자</th>
					<td>${comFn:formatDate(dsgnRecmDetail.REG_DT,'yyyyMMddHHmmss', 'yyyy-MM-dd')}</td>
				</tr>
					<tr>
						<th>설계파일ID</th>
						<td>${dsgnRecmDetail.CD_DTL_ID}</td>
						<th></th>
						<td></td>
					</tr>
				<%-- <tr>
					<th>계약구분</th>
					<td><comTag:comCmcdCdValueComboBox id="P_CONT_SECD" name="P_CONT_SECD" cdId="CONT_SECD"  selectKey="${dsgnRecmDetail.ETC_VAL1}" cond2="" headerValue="전체" className="select w50p"/></td>
					<th>계약방법</th>
					<td><comTag:comCmcdCdValueComboBox id="P_CONT_MTCD" name="P_CONT_MTCD" cdId="CONT_MTCD"  selectKey="${dsgnRecmDetail.ETC_VAL2}" cond2="" headerValue="전체" className="select w50p"/></td>
				</tr>
				<tr>
					<th>추정가격</th>
					<td>
						<input type="text"  id="P_ESTT_PRCE_ST"  name="P_ESTT_PRCE_ST"  value="${comFn:foramtMoney(dsgnRecmDetail.ETC_VAL3) }" money class="input rt w30p" placeholder="추정가격"> ~ <input type="text"  id="P_ESTT_PRCE_END"  name="P_ESTT_PRCE_END"   value="${comFn:foramtMoney(dsgnRecmDetail.ETC_VAL4) }" money class="input rt w30p" placeholder="추정가격">
						(부가세 미포함)
					</td>
					<th>긴급입찰</th>
					<td>
						<div class="rad_g">
                			<comTag:cmmnCdValueRadio name="P_EMRG_YN" selectKey="${dsgnRecmDetail.ETC_VAL5}" list="{'N':'미해당', 'Y':'해당'}"/>
                		</div>
					</td>
				</tr> --%>
				<tr>
					<th>설계파일명</th>
					<td colspan="3"><input type="text" value="${dsgnRecmDetail.CD_DTL_NM}"  id="P_CD_DTL_NM" name="P_CD_DTL_NM" placeholder="설계파일명"></td>
				</tr>
				<tr>
					<th>추천코드</th>
					<td><input type="text" value="${dsgnRecmDetail.RECM_ID}"  id="P_RECM_ID" name="P_RECM_ID" placeholder="추천코드"></td>
					<th></th>
					<td></td>
				</tr>
			</table>
		</div>
			
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
						<th>
							<input type="checkbox" id="allChk">
						</th>
						<th class="txtc">파일명</th>
						<th class="txtc">파일첨부</th>
					</tr>
				</thead>
				<tbody id="copyArea" style="display: none;">
					<tr>
						<td><input type="checkbox" class="fileDel"></td>
						<td>
							<input type="text" id="P_FILE_DOC_NM" disabled="disabled"   class="input w80p"  placeholder="파일명" />
						</td>
						<td class="txtl"> 
							<input type="file" class="w80p" onchange="fileSet(this);" >
						</td>
					</tr>
				</tbody> 
				<tbody id="pasteArea">
					<c:forEach items="${fileList}" var="data" varStatus="status">
						<tr>
							<td>
								<input type="checkbox" class="fileDel">
								<input type="hidden" name="P_FILE_SN" value="${data.FILE_SN}" disabled="disabled" />
							</td>
							<td> ${data.FILE_DOC_NM}</td>
							<td class="vtop"> 
								<div style="float:left"> 
                   					<a href="javascript:download('${data.FILE_SN}', '${data.FILE_GRP_NO}');" class="attfile">${data.SYS_FILE_NM}</a>
                   				</div>
                   				<div style="float:right"> 
                   					<button type="button" class="btn btn_s btn_blue" onclick="fileMod(this);">수정</button>
                    			</div>
							</td>
		             	</tr>
		             	<tr style="display: none;">
		             		<td>
								<input type="checkbox" class="fileDel">
								<input type="hidden" name="P_FILE_SN" value="${data.FILE_SN}" disabled="disabled" />
							</td>
							<td>
								<input type="text" id="P_FILE_DOC_NM"  name="P_FILE_DOC_NM" value="${data.FILE_DOC_NM}" disabled="disabled"   class="input w80p"  placeholder="파일명" />
							</td>
							<td class="txtl"> 
								<input type="file" class="w80p" onchange="fileSet(this);" >
								<div style="float:right"> 
		                   			<button type="button" class="btn btn_s btn_blue" onclick="fileCancelMod(this);">취소</button>
		                    	</div>
		                    </td>	
			             </tr>
					</c:forEach> 
				</tbody>
			</table>
			<div class="btm_btns">
				<button type="button" class="btn ty02" id="updtBtn">저장</button>
				<button type="button" class="btn ty04" id="listBtn">목록</button>
			</div>
		</div>
	</form>
</div>

<form id="listFrm" method="POST">
	<input type="hidden" name="resourceName" value="${comFn:nvl(param.resourceName, resourceName)}" >
</form>

<form id="detailFrm" method="POST"> 
	<input type="hidden" name="resourceName" value="${comFn:nvl(param.resourceName, resourceName)}" >
	<input type="hidden" name="P_CD_DTL_ID" id="P_CD_DTL_ID" value="${dsgnRecmDetail.CD_DTL_ID}">
</form>
