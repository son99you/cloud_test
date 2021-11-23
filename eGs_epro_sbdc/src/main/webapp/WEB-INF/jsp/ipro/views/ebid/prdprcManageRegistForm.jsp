<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 예정가격 > 예가등록요청 상세
 *
 * <pre>
 * ebid 
 *    |_ prdprcManageRegistForm.jsp
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

<script type="text/javascript" src="${jsPath}/ipro/views/ebid/prdprcManageRegistForm.js"></script>

<div class="contents_wrap">
	<ul class="step_wrap">
		<li><a href="#">${myMenuList.bigMenuNm}</a></li>
		<li><a href="#">${myMenuSubList.smallMenuNm}</a></li>
	</ul>
	<div class="tit_wrap">
		<h3 class="tit">예가등록요청 상세</h3>
	</div>
	
	<form id="bidPrdprcRegistForm" method="POST" enctype="multipart/form-data">
		<input type="hidden" name="resourceName" value="${param.resourceName}">
		
		<input type="hidden" id="P_FILE_GROUP_FLAG" name="P_FILE_GROUP_FLAG" value="estc">
		
		<input type="hidden" id="P_ANNC_NO" name="P_ANNC_NO" value="${bidPlanDetail.ANNC_NO}">
		<input type="hidden" id="P_ANNC_NGR" name="P_ANNC_NGR" value="${bidPlanDetail.ANNC_NGR}">
		<input type="hidden" id="P_ROUND_NO" name="P_ROUND_NO" value="${bidPlanDetail.ROUND_NO}">
		<input type="hidden" id="P_BID_FSCD" name="P_BID_FSCD" value="DI01">
		<input type="hidden" id="P_FILE_GRP_NO" name="P_FILE_GRP_NO" value="${bidPlanDetail.FILE_GRP_NO}">
		
		<input type="hidden" name="P_USR_ID" value="${sessionScope.loginResult.USR_ID}" >
		<input type="hidden" id="P_PAGE_NO" name="P_PAGE_NO" value="1">
		<input type="hidden" id="P_ESTT_AMT" value="${bidPlanDetail.ESTT_AMT}">
		
		<input type="hidden" id="P_ESTC_PSCD" name="P_ESTC_PSCD" value="${bidPlanDetail.ESTC_PSCD}">
		
		<fieldset>

			<div class="view_wrap typeB">
				<div class="tit_area">
					<h4 class="tit">입찰개요</h4>
				</div>
				<div class="view_area">
					<table>
				    	<colgroup>
				        	<col style="width: 15%;">
							<col style="width: 35%">
							<col style="width: 15%">
							<col style="width: 35%">
				        </colgroup>
				    	<tr>
				        	<th>공고번호</th>
			                <td>${bidPlanDetail.ANNC_NO}</td>
			                <th>공고일자</th>
			                <td>${comFn:formatDate(bidPlanDetail.ANNC_DT,'yyyyMMddHHmmss','yyyy-MM-dd')}</td>
				        </tr>
				        <tr>
				            <th>입찰공고명</th>
							<td colspan="3">${bidPlanDetail.BID_NM}</td>
				        </tr>
				        <tr>
				            <th>입찰구분</th>
			                <td>${bidPlanDetail.BID_MTCD_NM}</td>
			                <th>계약방법</th>
			                <td>${bidPlanDetail.CONT_MTCD_NM}</td>
				        </tr>
				        <tr>    
				            <th>낙찰방법</th>
			                <td>${bidPlanDetail.SBID_MTCD_NM}</td>
				            <th></th>
			                <td></td>
				        </tr>
				    </table>
				</div>
				
				<div class="tit_area">
					<h4 class="tit">입찰진행순서</h4>
				</div>
				<div class="view_area">
		            <table>
		                <caption>입찰진행순서</caption>
		                <colgroup>
		                    <col style="width: 170px;">
							<col style="width: auto;">
		                </colgroup>
		                <tbody>
		                <tr class="line">
		                    <th><label>입찰공고게시일시</label></th>
		                    <td>
								${comFn:formatDate(bidPlanDetail.ANNC_DT,'yyyyMMddHHmmss','yyyy-MM-dd HH:mm')}
		                    </td>
		                </tr>
		                <%-- 입찰설명회 여부가 [예] 일 경우에 활성화 --%>
		                <c:if test="${bidPlanDetail.ROUND_NO < 2}">
		                <tr class="brfsDiv" <c:if test="${bidPlanDetail.BID_BRFS_YN ne 'Y'}"> style="display:none;"</c:if>>
		                    <th><label>입찰설명회일시</label></th>
		                    <td>
								${comFn:formatDate(bidPlanDetail.BRFS_DT,'yyyyMMddHHmmss','yyyy-MM-dd HH:mm')}
		                    </td>
		                </tr>
		                <%-- 입찰설명회 여부가 [예] 일 경우에 활성화 End --%>
		                <c:if test="${bidPlanDetail.PRPDC_ESS_YN eq 'Y'}">
		                <tr>
		                    <th><label>제안서제출기간</label></th>
		                    <td>
			                    ${comFn:formatDate(bidPlanDetail.PRPDC_SBMT_STDT,'yyyyMMddHHmmss','yyyy-MM-dd HH:mm')}
		                        <span class="wave">~</span>
		                        ${comFn:formatDate(bidPlanDetail.PRPDC_SBMT_ENDT,'yyyyMMddHHmmss','yyyy-MM-dd HH:mm')}
		                    </td>
		                </tr>
		                </c:if>
		                </c:if>		                
		                <tr>
		                    <th><label>입찰서제출기간</label></th>
		                    <td>
		                    	${comFn:formatDate(bidPlanDetail.BIDC_SBMT_STDT,'yyyyMMddHHmmss','yyyy-MM-dd HH:mm')}
		                        <span class="wave">~</span>
		                        ${comFn:formatDate(bidPlanDetail.BIDC_SBMT_ENDT,'yyyyMMddHHmmss','yyyy-MM-dd HH:mm')}
		                    </td>
		                </tr>
		                <tr>
		                    <th><label>개찰일시</label></th>
		                    <td>
			                    ${comFn:formatDate(bidPlanDetail.OPNG_DT,'yyyyMMddHHmmss','yyyy-MM-dd HH:mm')}
		                    </td>
		                </tr>
		                <c:if test="${bidPlanDetail.ANNC_SECD eq 'HNDW_BID' }">
			                <tr>
			                    <th>개찰장소</th>
			                    <td>
			                    	${bidPlanDetail.OPNG_PLC_NM }
			                    </td>
							</tr>
						</c:if>
		                </tbody>
		            </table>
		        </div>				
				
				<div class="tit_area">
					<h4 class="tit">예가정보</h4>
				</div>
				<div class="view_area">
					<table>
				    	<colgroup>
				        	<col style="width: 15%;">
							<col style="width: 35%">
							<col style="width: 15%">
							<col style="width: 35%">
				        </colgroup>
				    	<tr>
				        	<th>예가확정</th>
			                <td>
								<input type="text" class="w180" id="usrNm" name="P_ESTC_CHRGR_NM" value="${bidPlanDetail.ESTC_CHRGR_NM}" readonly="readonly">
								<input type="hidden" id="usrId" name="P_ESTC_CHRGR_ID" value="${bidPlanDetail.ESTC_CHRGR_ID}">
								<button type="button" class="btn btn_s btn_sch" id="chargerBtn">검색</button>
			                </td>
			                <th>예가방식</th>
			                <td>${bidPlanDetail.ESTPC_SECD_NM}</td>
				        </tr>
				        <tr>
				            <th>추정금액</th>
			                <td>${comFn:formatMoney(bidPlanDetail.ESTT_AMT)}</td>
			                <th>기초금액</th>
			                <td>
			                	<input type="text" name="P_BASE_ESTPC_AMT" value="${comFn:formatMoney(bidPlanDetail.BASE_AMT)}" class="w180" money>
			                </td>
				        </tr>
				        <tr>
				        	<th>조사가격금액</th>
				        	<td>
				        		<input type="text" name="P_SVY_PRCE_AMT" value="${comFn:formatMoney(bidPlanDetail.SVY_PRCE_AMT)}" class="w180" money onchange="baseAmtRT(this);">
				        		(기초금액대비 : <span id="rt">0</span> %)
				        	</td>
				        	<th></th>
				        	<td></td>
				        </tr>
				        <c:if test="${bidPlanDetail.ESTPC_SECD eq '180000'}">
					        <tr>
					        	<th>복수예비가격범위</th>
					            <td colspan="3">
				            		${bidPlanDetail.PLR_ESTPC_RNG_CD_NM}
								</td>
					        </tr>
				        </c:if>				        
				        <tr>
		                    <th scope="row">비고</th>
		                    <td colspan="3">
		                    	<textarea id="P_RMK" name="P_RMK" style="width: 100%; height: 70px;" maxlength="4000">${bidPlanDetail.RMK}</textarea>
		                    </td>
		                </tr>
				    </table>
				</div>		
				
				<div class="tit_area">
		           	<h4 class="tit">첨부파일</h4>
				</div>
		           <div class="view_area fileViewer">
					<!-- 업로드 삽입. -->
					<script type="text/javascript">
						DEXT5UPLOAD.config.Mode = 'upload';
						DEXT5UPLOAD.config.Width = '100%';
						DEXT5UPLOAD.config.FolderNameRule = '/bid';
						var dext5Upload = new Dext5Upload("upload");
					</script>	            	
				</div>	
				<div id="upload_fileInfo"></div>		
						
				<div class="btn_wrap">
					<button type="button" class="btn btn_m btn_orange" id="saveBtn">저장</button>
					<button type="button" class="btn btn_m btn_orange" id="reqBtn">등록요청</button>
			    	<button type="button" class="btn btn_m btn_del" id="listBtn">목록</button>
			    </div>
			</div>
		</fieldset>
	</form>
</div> <!--// content E-->

<form id="listFrm" method="POST">
	<input type="hidden" name="resourceName" value="${ param.resourceName }" >
</form>
<form id="popupFrm" method="POST">
	<input type="hidden" name="resourceName" value="${param.resourceName}">
  	<input type="hidden" name="setMulti" value="Y">
  	<input type="hidden" name="tchnChangerYn" value="Y">
</form>
<%-- 파일 VIEW 폼 --%>
<form id="fileViewFrm" method="POST">
	<input type="hidden" id="P_VIEW_ATCHMNFL_GROUP_NO" name="P_VIEW_ATCHMNFL_GROUP_NO" value="${bidPlanDetail.FILE_GRP_NO}">
</form>