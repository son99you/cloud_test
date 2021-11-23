<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 낙찰자선정 > 적격심사 평가등록
 *
 * <pre>
 * ebid 
 *    |_ popup
               |_ properJdgmnEvlRegistForm.jsp
 * 
 * </pre>
 * @date : 2017. 06. 19
 * @version : 1.0
 * @author : 은우소프트 전상훈
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %> 
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %> 

<script type="text/javascript" src="/raonkupload/js/raonkupload.js"></script>
<script type="text/javascript" src="${jsPath}/ipro/views/ebid/popup/tchnEstmPopup.js"></script> 
 
<div class="pop_wrap">
	<form id="registFrm" method="POST" enctype="multipart/form-data">
		<div class="pop_header">
			<h1 class="tit"> 평가결과
				<c:if test="${empty bidVendDetail.ESTM_ELCD}">등록</c:if>
				<c:if test="${not empty bidVendDetail.ESTM_ELCD}">상세</c:if>
			</h1>
		</div> <!--// pop_header E -->
		<div class="pop_container">
			<div class="view_wrap typeA"> 
				<div class="view_area">
						<input type="hidden" name="P_ANNC_NO" value="${bidReqDetail.ANNC_NO}" >
						<input type="hidden" name="P_ANNC_NGR"  value="${bidReqDetail.ANNC_NGR}" >
						<input type="hidden" name="P_ROUND_NO"  value="${bidReqDetail.ROUND_NO}" >
						<input type="hidden" name="P_REGIST" value="${P_REGIST}">
						<c:if test="${empty bidVendDetail.ESTM_ELCD}">
							<input type="hidden" name="P_VEND_REG_NO" value="${param.P_VEND_REG_NO}">
							<input type="hidden" name="P_BID_SBMT_FSCD" value="${param.P_BID_SBMT_FSCD}">
						</c:if>
						<c:if test="${not empty bidVendDetail.ESTM_ELCD}">
							<input type="hidden" name="P_VEND_REG_NO" value="${bidVendDetail.VEND_REG_NO}">
							<input type="hidden" name="P_BID_SBMT_FSCD" value="${bidVendDetail.BID_SBMT_FSCD}">
						</c:if>
						<input type="hidden" id="P_FILE_GRP_NO" name="P_FILE_GRP_NO" value="${bidVendDetail.FILE_GRP_NO}">  
						<input type="hidden" id="P_FILE_GRP_NO_NEW" name="P_FILE_GRP_NO_NEW">
						<input type="hidden" id="estmElcd" name="estmElcd" value="${bidVendDetail.ESTM_ELCD}">
						<input type="hidden" id=P_SBID_MTCD name="P_SBID_MTCD" value="${bidReqDetail.SBID_MTCD }">
						<input type="hidden" id=P_ESTM_ELCD_GBN name="P_ESTM_ELCD_GBN" value="${bidVendDetail.ESTM_ELCD }">
							 
						<table class="form_tb">
					    	<colgroup>
					        	<col width="20%" />
					            <col width="*" />
					        </colgroup>
					    	<tr>
				                <th scope="row">낙찰방법</th>
				                <td>&nbsp;&nbsp;${bidReqDetail.SBID_MTCD_NM}</td>
				            </tr> 
							<tr>
								<th>평가결과</th>
								<td>
									<div class="rad_g">
									<comTag:cmmnCdValueRadio  name="P_ESTM_ELCD"  selectKey="${comFn:nvl(bidVendDetail.ESTM_ELCD,'ELGB')}"  list="{'ELGB':'적격', 'NT_ELGB':'부적격'}"/>
								</div><!--// rad_g -->
								</td>
							</tr>
							<!-- 협상의 의한 계약일 경우에만  -->
							<c:if test="${bidReqDetail.SBID_MTCD eq '40'}">
								<tr>
									<th>적합제한점수</th>
									<td>&nbsp;&nbsp;${bidReqDetail.ELGB_LMT_SCR}</td>
								</tr>
								<tr>
									<th>평가점수</th>
									<td><input type="text" name="P_ESTM_SCR" value="${bidVendDetail.ESTM_SCR}" class="w30p" placeholder="평가점수" /></td>
								</tr>
							</c:if> 
							<tr class="estmTr" style="display: none;">
								<th>부적격사유</th>
								<td>
									<textarea class="tarea" id="P_ESTM_NT_ELGB_RSN" name="P_ESTM_NT_ELGB_RSN" placeholder="부적격사유를 입력해주세요."><c:out value="${bidVendDetail.ESTM_NT_ELGB_RSN}"></c:out></textarea>
								</td>
							</tr>
					    </table>
				</div>
			
				<p class="spc_stit">평가결과첨부</p>
				<c:if test="${param.P_GBN ne 'view'}">
					<div class=fileViewer"> 
						<!-- 업로드 삽입. --> 
						<script type="text/javascript">
							var raonkParam = {
					            Id: "uploadView1",
					            Width: "100%",
					            Height: "200px",
					            ButtonBarEdit: "add,remove,remove_all", 
					            BorderStyle: "solid",
					            FolderNameRule: "yyyy/mm/dd/ebid/bid" 
					        };
							var raonkUpload = new RAONKUpload(raonkParam);
						</script> 
					</div>
					<div id="upload_fileInfo"></div> 
				</c:if>	
				<c:if test="${param.P_GBN eq 'view'}">
					<div class=fileViewer"> 
						<!-- 업로드 삽입. --> 
						<script type="text/javascript">
							var raonkParam = {
					            Id: "uploadView1",
					            Width: "100%",
					            Height: "200px",
					            ButtonBarEdit: "open,download", 
					            BorderStyle: "solid",
					            FolderNameRule: "yyyy/mm/dd/ebid/bid" 
					        };
							var raonkUpload = new RAONKUpload(raonkParam);
						</script> 
					</div>
					<div id="upload_fileInfo"></div> 
				</c:if>	
				<div class="btn_wrap view_btn">
					<c:if test="${param.P_GBN ne 'view'}">
						<button type="button" class="btn btn_02 btn_revise" id="registBtn" >저장</button>
					</c:if>
	    			<button type="button" class="btn btn_02 btn_close" id="closeBtn" >닫기</button>
				</div> <!--// btn_wrap E -->
			</div> <!--// view_wrap E -->
		</div> <!--// pop_container E -->
		
	</form>
</div> <!--// pop_wrap E --> 
<form id="downFrm" method="POST">
	<input type="hidden" name="P_FILE_SN">
	<input type="hidden" name="P_FILE_GRP_NO">
</form> 
