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
<script type="text/javascript" src="${jsPath}/ipro/views/ebid/popup/elgbEstmPopup.js"></script> 
 
<div class="pop_wrap">
	<form id="registFrm" method="POST" enctype="multipart/form-data">
		<div class="pop_header">
			<h1 class="tit"> 적격심사결과
				<c:if test="${empty bidVendDetail.ELGB_CD}">등록</c:if>
				<c:if test="${not empty bidVendDetail.ELGB_CD}">상세</c:if>
			</h1>
		</div> <!--// pop_header E -->
		<div class="pop_container">
			<div class="view_wrap typeA"> 
				<div class="view_area">
						<input type="hidden" name="P_ANNC_NO" value="${bidReqDetail.ANNC_NO}" >
						<input type="hidden" name="P_ANNC_NGR"  value="${bidReqDetail.ANNC_NGR}" >
						<input type="hidden" name="P_ROUND_NO"  value="${bidReqDetail.ROUND_NO}" >
						<input type="hidden" name="P_REGIST"  value="${P_REGIST}" >
						<c:if test="${empty bidVendDetail.ELGB_CD}">
							<input type="hidden" name="P_VEND_REG_NO" value="${param.P_VEND_REG_NO}">
							<input type="hidden" name="P_BID_SBMT_FSCD" value="${param.P_BID_SBMT_FSCD}">
						</c:if>
						<c:if test="${not empty bidVendDetail.ELGB_CD}">
							<input type="hidden" name="P_VEND_REG_NO" value="${bidVendDetail.VEND_REG_NO}">
							<input type="hidden" name="P_BID_SBMT_FSCD" value="${bidVendDetail.BID_SBMT_FSCD}">
						</c:if>
						<input type="hidden" id="P_FILE_GRP_NO" name="P_FILE_GRP_NO" value="${bidVendDetail.FILE_GRP_NO}">  
						<input type="hidden" id="P_FILE_GRP_NO_NEW" name="P_FILE_GRP_NO_NEW">
						 
						<table class="form_tb">
					    	<colgroup>
					        	<col width="15%" />
					            <col width="*" />
					        </colgroup>
							<tr>
								<th>평가결과</th>
								<td>
									<div class="rad_g">
										<comTag:cmmnCdValueRadio  name="P_ELGB_CD"  selectKey="${comFn:nvl(bidVendDetail.ELGB_CD,'ELGB')}"  list="{'ELGB':'적격', 'NT_ELGB':'부적격'}"/>
									</div><!--// rad_g -->
								</td>
							</tr>
					    </table>
				</div>
			
				<p class="spc_stit">첨부파일</p>
				<div class=fileViewer"> 
					<!-- 업로드 삽입. --> 
					<script type="text/javascript">
						var raonkParam = {
				            Id: "uploadView1",
				            Width: "100%",
				            Height: "200px",
				            ButtonBarEdit: "add,remove,remove_all", 
				            BorderStyle: "solid"
				        };
						var raonkUpload = new RAONKUpload(raonkParam);
					</script> 
				</div>
				<div id="upload_fileInfo"></div> 
				
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
