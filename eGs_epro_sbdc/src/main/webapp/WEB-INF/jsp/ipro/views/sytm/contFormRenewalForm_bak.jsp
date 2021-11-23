<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 기준정보 > 서식관리 갱신
 *
 * <pre>
 * sytm 
 *    |_ contFormRenewalForm.jsp
 * 
 * </pre>
 * @date : 2018. 09. 11
 * @version : 1.0
 * @author : 은우소프트 은잔디
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %> 
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %> 

<script type="text/javascript" src="/dext5editor/js/dext5editor.js"></script>
<script type="text/javascript" src="${jsPath}/ipro/views/sytm/contFormRenewalForm.js"></script>

<div class="contents_wrap">
	<ul class="step_wrap">
		<li><a href="#">${myMenuList.bigMenuNm}</a></li>
		<li><a href="#">${myMenuSubList.smallMenuNm}</a></li>
	</ul>
	<div class="tit_wrap">
		<h3 class="tit">서식관리 갱신</h3>
	</div>
	
	<form id="modiFrm" method="POST">
		<input type="hidden" name="P_FRM_NO" id="P_FRM_NO" value="${contFormDetail.FRM_NO}">
		<input type="hidden" name="P_VRSN" id="P_VRSN" value="${contFormDetail.VRSN}">
		
		<div class="view_wrap typeA">
			<div class="view_area">
				<table>
					<colgroup>
						<col width="15%">
						<col width="35%">
						<col width="15%">
						<col width="35%">
					</colgroup>
					<tr>
						<th>서식명</th>
						<td colspan="3">
							<input type="text" class="w_95p" name="P_FRM_NM" id="P_FRM_NM" value="${contFormDetail.FRM_NM}">
						</td>
					</tr>
					<tr>
						<th>계약구분</th>
						<td>
							<div class="selectLayer2 w_120">
								<comTag:comCmcdCdValueComboBox id="P_CONT_SECD" name="P_CONT_SECD" cdId="CONT_SECD" selectKey="${contFormDetail.CONT_SECD}" headerKey="" headerValue="선택" />
							</div>
						</td>
						<th>계약서식구분코드</th>
						<td>
							<div class="selectLayer2 w_120">
								<comTag:comCmcdCdValueComboBox id="P_CONT_FORM_SECD" name="P_CONT_FORM_SECD" cdId="CONT_FORM_SECD" selectKey="${contFormDetail.CONT_FORM_SECD}" headerKey="" headerValue="선택" />
							</div>
						</td>
					</tr>
					<tr>
						<th>사용여부</th>
						<td colspan="3">
							<comTag:cmmnCdValueRadio name="P_USE_YN" selectKey="${comFn:nvl(contFormDetail.USE_YN, 'Y')}" list="{'Y':'예', 'N':'아니오'}" />
						</td>
					</tr>
					<tr>
						<th>계약버전</th>
						<td>ver.${contFormDetail.VRSN}</td>
						<th>적용일자</th>
						<td>
							<div class="calndar_wrap">
								<label for="" class="blind">적용일자</label>
								<input type="text" name="P_APPC_DT" id="P_APPC_DT" value="${comFn:formatDate(contFormDetail.APPC_DT, 'yyyyMMdd', 'yyyy-MM-dd')}" class="w150" date>
							</div>
						</td>
					</tr>
					<tr>
						<th>갱신사유</th>
						<td colspan="3" style="min-height: 100px; vertical-align: top">
							<textarea id="P_RENW_RSN" name="P_RENW_RSN" style="width: 100%; height: 100%; display:;" rows="3">
								<c:out value=" ${contFormDetail.RENW_RSN}"></c:out>
							</textarea>
						</td>
					</tr>
					<tr>
						<th>내용</th>
						
						<td colspan="3" style="min-height: 100px; vertical-align: top">
							<div style="width: 100%; height: 450px;">
								<!-- 에디터 생성 -->
								<script type="text/javascript">
									DEXT5.config.Width = "100%";
									DEXT5.config.Height = "100%";
									DEXT5.config.SkinName = "blue";
									DEXT5.config.Runtimes = "html5";
									new Dext5editor('editor1');
								</script>
							</div>
							<textarea id="P_FRM_CNTN" name="P_FRM_CNTN" style="width: 100%; height: 100%; display: none;"><c:out value=" ${contFormDetail.FRM_CNTN}"></c:out></textarea>
						</td>
					</tr>
				</table>
			</div>
			
			<div class="btn_wrap">
				<button type="button" class="btn btn_m btn_orange" id="updtBtn">저장</button>
				<button type="button" class="btn btn_m btn_del" id="listBtn">목록</button>
			</div>
	
		</div>
	</form>
</div>

<form id="listFrm" method="POST">
	<input type="hidden" name="resourceName" value="${ param.resourceName }" >
	<input type="hidden" name="resourceDesc" value="${ param.resourceDesc }" > 
</form> 
<form id="detailFrm" method="POST">
	<input type="hidden" name="resourceName" value="${ param.resourceName }" >
	<input type="hidden" name="resourceDesc" value="${ param.resourceDesc }" >  
	<input type="hidden" name="P_FRM_NO"  id="P_FRM_NO" value="${contFormDetail.FRM_NO}">
</form> 