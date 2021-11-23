<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 기준정보 > 서식관리 수정
 *
 * <pre>
 * sytm
 *    |_ contFormModiForm.jsp
 * 
 * </pre>
 * @date : 2018. 08. 31
 * @version : 1.0
 * @author : 은우소프트 은잔디
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld"%>
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld"%>

<script type="text/javascript" src="/raonkeditor/js/raonkeditor.js"></script>
<script type="text/javascript" src="${jsPath}/ipro/views/sytm/contFormRenewalForm.js"></script>

<div class="sp_sec">
	<div class="nav_sec">
		<ul id="breadcrumbs">
			<li class="home">홈</li>
			<li>${myMenuList.bigMenuNm}</li>
			<li>${myMenuSubList.smallMenuNm}</li> 
		</ul>
	</div><!--// nav_sec -->
	<h3 class="sp_tit">서식관리 갱신</h3>
	
	<form id="modiFrm" method="POST" enctype="multipart/form-data">
		<input type="hidden" name="P_FRM_NO" id="P_FRM_NO" value="${contFormDetail.FRM_NO}">
		<input type="hidden" name="P_VRSN" id="P_VRSN" value="${contFormDetail.VRSN}">
		<input type="hidden" name="resourceName" value="${comFn:nvl(param.resourceName, resourceName)}" >
		<input type="hidden" name="P_CTDC_FILE_GRP_NO" id="P_CTDC_FILE_GRP_NO" value="${contFormDetail.CTDC_FILE_GRP_NO}">		<!-- 계약서첨부 -->
		<input type="hidden" name="P_COPY_FILE_GRP_NO" id="P_COPY_FILE_GRP_NO" value="${contFormDetail.FILE_GRP_NO}">		<!-- 필수첨부 -->
		<input type="hidden" name="P_CHNG_FRM" value="" >
		
		<div class="sp_cont">
			<p class="spc_stit">서식정보</p>
			<table class="form_tb">
				<colgroup>
					<col width="15%">
					<col width="35%">
					<col width="15%">
					<col width="35%">
				</colgroup>
				<tr>
					<th>서식명</th>
					<td colspan="3"><input type="text"  id="P_FRM_NM" name="P_FRM_NM"   value="${contFormDetail.FRM_NM}" class="input w100p" placeholder="서식명" /></td>
				</tr>
				<tr>
					<th>계약구분</th>
					<td>
						<comTag:comCmcdCdValueComboBox name="P_CONT_SECD"  id="P_CONT_SECD" cdId="CONT_SECD" selectKey="${contFormDetail.CONT_SECD}" headerValue="전체"  className="select w50p"/>
					</td>
					<th>계약분류</th>
					<td>
						<comTag:comCmcdCdValueComboBox name="P_CONT_KDCD"  id="P_CONT_KDCD" cdId="CONT_KDCD" selectKey="${contFormDetail.CONT_KDCD}" headerValue="전체"  className="select w50p"/>
					</td>
				</tr>
				<tr>
					<th>변경구분</th>
					<td>
						<div class="rad_g">
							<comTag:cmmnCdValueRadio  name="P_CHNG_SECD" selectKey="${comFn:nvl(contFormDetail.CHNG_SECD, '0')}" list="{'0':'일반', '1':'변경'}" />
						</div><!--// rad_g -->
					</td>
					<th>사용여부</th>
					<td>
						<div class="rad_g">
							<comTag:cmmnCdValueRadio  name="P_USE_YN" selectKey="${comFn:nvl(contFormDetail.USE_YN, 'Y')}"  list="{'Y':'예', 'N':'아니오'}" />
						</div><!--// rad_g -->
					</td>
				</tr>
				<tr>
					<th>계약버전</th>
					<td>ver.${contFormDetail.VRSN}</td>
					<th>적용일자</th>
					<td>
						<span class="date_box">
                			<input type="text" id="P_APPC_DT" name="P_APPC_DT" class="input datepicker start-date " placeholder="적용일자" date value="${comFn:formatDate(contFormDetail.APPC_DT,'yyyyMMdd','yyyy-MM-dd') }">
                		</span>
					</td>
				</tr>
				<tr>
					<th>갱신사유</th>
					<td colspan="3" style="min-height: 100px; vertical-align: top">
						<textarea id="P_RENW_RSN" name="P_RENW_RSN" style="width: 100%; height: 100%; display:;" rows="3" ><c:out value=" ${contFormDetail.RENW_RSN}"></c:out></textarea>
					</td>
				</tr>
				<%-- <tr>
					<th>내용</th>
					<td colspan="3" style="min-height: 100px; vertical-align: top">
						<div style="width: 100%; height: 450px;">
							<!-- 에디터 생성 -->
							<script type="text/javascript">
							   var kEditorParam = {
				                       Id: "editor1",
				                       SkinName: "bluegray",
				                       Lang: "ko-kr", // ko-kr, en-us, ja-jp, zh-cn, zh-tw
				                       Mode: "edit",
				                       Width: "100%",
				                       Height: "100%"
				                   };
				         	var editor1 = new RAONKEditor(kEditorParam);
							</script>
						</div>
						<textarea id="P_FRM_CNTN" name="P_FRM_CNTN" style="width: 100%; height: 100%; display: none;"><c:out value=" ${contFormDetail.FRM_CNTN}"></c:out></textarea>
					</td>
				</tr> --%>
			</table>
			
			<p class="spc_stit">계약서</p>
			<table class="form_tb">
				<colgroup>
					<col width="20%">
					<col width="*">
				</colgroup>
				<tbody>
					<tr>
						<th>계약서파일</th>
						<td class="vtop"> 
							<div style="float:left"> 
								<input type="hidden" name="P_CONT_FILE_SN" value="${contFormDetail.CTDC_FILE_SN}" disabled="disabled" />
                  					<a href="javascript:download('${contFormDetail.CTDC_FILE_GRP_NO}', '${contFormDetail.CTDC_FILE_SN}');" class="attfile">${contFormDetail.CTDC_FILE_NM}</a>
                  				</div>
                  				<div style="float:right"> 
                  					<button type="button" class="btn btn_s btn_blue"  id="contFileBtn"onclick="contFileMod(this);">수정</button>
                   			</div>
						</td>
	             	</tr>
	             	<tr style="display: none;">
						<th>계약서파일</th>
						<td class="vtop"> 
							<input type="file" class="w80p"  id="P_CONT_FILE">
							<div style="float:right"> 
	                   			<button type="button" class="btn btn_s btn_blue" onclick="contCancelMod(this);">취소</button>
	                    	</div>
	                    </td>	
		             </tr>
				</tbody>
			</table>
			
			<p class="spc_stit">계약자동첨부문서&nbsp;&nbsp;(계약생성시 자동 첨부되는 서류입니다)
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
							<input type="file" class="w80p" onchange="fileSet(this);">
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
                   					<button type="button" class="btn btn_s btn_blue" onclick="contFileMod(this);">수정</button>
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
								<input type="file" style="width: 80%" onchange="fileSet(this);" >
								<div style="float:right"> 
		                   			<button type="button" class="btn btn_s btn_blue" onclick="contCancelMod(this);">취소</button>
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
	<input type="hidden" name="P_FRM_NO" id="P_FRM_NO" value="${contFormDetail.FRM_NO}">
	<input type="hidden" name="P_VRSN" id="P_VRSN" value="${contFormDetail.VRSN}">
</form>
