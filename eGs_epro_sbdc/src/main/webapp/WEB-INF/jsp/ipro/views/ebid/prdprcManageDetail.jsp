<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 예정가격 > 예가등록 상세
 *
 * <pre>
 * ebid 
 *    |_ prdprcManageDetail.jsp
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

<script type="text/javascript" src="${jsPath}/ipro/views/ebid/prdprcManageDetail.js"></script>

<div class="contents_wrap">
	<ul class="step_wrap">
		<li><a href="#">${myMenuList.bigMenuNm}</a></li>
		<li><a href="#">${myMenuSubList.smallMenuNm}</a></li>
	</ul>
	<div class="tit_wrap">
		<h3 class="tit">예가등록 상세</h3>
	</div>
	
	<form id="bidPrdprcRegistForm" method="POST" enctype="multipart/form-data">
		<input type="hidden" name="resourceName" value="${param.resourceName}">
		
		<input type="hidden" id="P_FILE_GROUP_FLAG" name="P_FILE_GROUP_FLAG" value="estc">
		
		<input type="hidden" id="P_ANNC_NO" name="P_ANNC_NO" value="${bidPlanDetail.ANNC_NO}">
		<input type="hidden" id="P_ANNC_NGR" name="P_ANNC_NGR" value="${bidPlanDetail.ANNC_NGR}">
		<input type="hidden" id="P_ROUND_NO" name="P_ROUND_NO" value="${bidPlanDetail.ROUND_NO}">
		<input type="hidden" id="P_BID_FSCD" name="P_BID_FSCD" value="DI01">
		<input type="hidden" id="P_FILE_GRP_NO" name="P_FILE_GRP_NO" value="${bidPlanDetail.FILE_GRP_NO}">
		
		<input type="hidden" name="P_USR_ID" value="${sessionScope.loginResult.USR_ID}">
		<input type="hidden" id="P_PAGE_NO" name="P_PAGE_NO" value="1">
		<input type="hidden" id="P_ESTT_AMT" value="${bidPlanDetail.ESTT_AMT}">
		
		<input type="hidden" id="P_ESTC_PSCD" name="P_ESTC_PSCD" value="${bidPlanDetail.ESTC_PSCD}">
		
		<input type="hidden" id="P_ESTPC_SECD" name="P_ESTPC_SECD" value="${bidPlanDetail.ESTPC_SECD}">
		<input type="hidden" id="P_PLR_ESTPC_RNG_CD" value="${bidPlanDetail.PLR_ESTPC_RNG_CD}">
		<input type="hidden" id="P_ESTPC_UP_CNT" value="${bidPlanDetail.ESTPC_UP_CNT}">
		<input type="hidden" id="P_ESTPC_DCSN_SECD" name="P_ESTPC_DCSN_SECD" value="${bidPlanDetail.PLR_ESTPC_RNG_CD}${bidPlanDetail.ESTPC_UP_CNT}">
		
		<fieldset>

			<div class="view_wrap typeB">
				<div class="tit_area">
					<h4 class="tit">공고개요</h4>
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
				        	<th scope="row">공고번호</th>
			                <td>${bidPlanDetail.ANNC_NO}</td>
			                <th scope="row">공고일자</th>
			                <td>${comFn:formatDate(bidPlanDetail.ANNC_DT,'yyyyMMddHHmmss','yyyy-MM-dd')}</td>
				        </tr>
				        <tr>
				            <th scope="row">공고명</th>
							<td colspan="3">${bidPlanDetail.BID_NM}</td>
				        </tr>
				        <tr>
				            <th scope="row">입찰방법</th>
			                <td>${bidPlanDetail.BID_MTCD_NM}</td>
			                <th scope="row">계약방법</th>
			                <td>${bidPlanDetail.CONT_MTCD_NM}</td>
				        </tr>
				        <tr>    
				            <th scope="row">낙찰방법</th>
			                <td>${bidPlanDetail.SBID_MTCD_NM}</td>
				            <th scope="row">계약담당자</th>
			                <td>${bidPlanDetail.CHRGR_NM}</td>
				        </tr>
				    </table>
				</div>
				
				<div class="tit_area">
					<h4 class="tit">진행순서</h4>
				</div>
				<div class="view_area">
		            <table>
		                <caption>진행순서</caption>
		                <colgroup>
		                    <col style="width: 15%;">
							<col style="width: *">
		                </colgroup>
		                <tbody>
		                <tr class="line">
		                    <th scope="row"><label>공고게시일시</label></th>
		                    <td>
								${comFn:formatDate(bidPlanDetail.ANNC_DT,'yyyyMMddHHmmss','yyyy-MM-dd HH:mm')}
		                    </td>
		                </tr>
		                <%-- 설명회 여부가 [예] 일 경우에 활성화 --%>
		                <c:if test="${bidPlanDetail.ROUND_NO < 2}">
		                <tr class="brfsDiv" <c:if test="${bidPlanDetail.BID_BRFS_YN ne 'Y'}"> style="display:none;"</c:if>>
		                    <th scope="row"><label>설명회일시</label></th>
		                    <td>
								${comFn:formatDate(bidPlanDetail.BRFS_DT,'yyyyMMddHHmmss','yyyy-MM-dd HH:mm')}
		                    </td>
		                </tr>
		                <%-- 설명회 여부가 [예] 일 경우에 활성화 End --%>
		                <c:if test="${bidPlanDetail.PRPDC_ESS_YN eq 'Y'}">
		                <tr>
		                    <th scope="row"><label>제안서제출기간</label></th>
		                    <td>
			                    ${comFn:formatDate(bidPlanDetail.PRPDC_SBMT_STDT,'yyyyMMddHHmmss','yyyy-MM-dd HH:mm')}
		                        <span class="wave">~</span>
		                        ${comFn:formatDate(bidPlanDetail.PRPDC_SBMT_ENDT,'yyyyMMddHHmmss','yyyy-MM-dd HH:mm')}
		                    </td>
		                </tr>
		                </c:if>
		                </c:if>
		                <tr>
		                    <th scope="row"><label>가격서제출기간</label></th>
		                    <td>
		                    	${comFn:formatDate(bidPlanDetail.BIDC_SBMT_STDT,'yyyyMMddHHmmss','yyyy-MM-dd HH:mm')}
		                        <span class="wave">~</span>
		                        ${comFn:formatDate(bidPlanDetail.BIDC_SBMT_ENDT,'yyyyMMddHHmmss','yyyy-MM-dd HH:mm')}
		                    </td>
		                </tr>
		                <tr>
		                    <th scope="row"><label>개찰일시</label></th>
		                    <td>
			                    ${comFn:formatDate(bidPlanDetail.OPNG_DT,'yyyyMMddHHmmss','yyyy-MM-dd HH:mm')}
		                    </td>
		                </tr>
		                <c:if test="${bidPlanDetail.ANNC_SECD eq 'HNDW_BID' }">
			                <tr>
			                    <th scope="row">개찰장소</th>
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
				        	<th scope="row">예가확정</th>
			                <td>${bidPlanDetail.ESTC_CHRGR_NM}</td>
			                <th scope="row">예가방식</th>
			                <td>${bidPlanDetail.ESTPC_SECD_NM}</td>
				        </tr>
				        <tr>
				            <th scope="row">추정금액</th>
			                <td>${comFn:formatMoney(bidPlanDetail.ESTT_AMT)}</td>
			                <th scope="row" class="bullet_orange">기초금액</th>
			                <td>
				        		<c:if test="${bidPlanDetail.ESTPC_SECD ne '180002'}">
				                	<input type="text" id="P_BASE_ESTPC_AMT" name="P_BASE_ESTPC_AMT" value="${comFn:formatMoney(bidPlanDetail.BASE_ESTPC_AMT)}" money>
				        		</c:if>
			                </td>
				        </tr>
				        <tr>
				        	<th>조사가격금액</th>
				        	<td>
				        		<%-- ${comFn:formatMoney(bidPlanDetail.SVY_PRCE_AMT)} --%>
				        		<input type="text" id="P_SVY_PRCE_AMT" name="P_SVY_PRCE_AMT" value="${comFn:formatMoney(bidPlanDetail.SVY_PRCE_AMT)}" class="w180" money onblur="baseAmtRT2(this);">
				            	(기초금액대비 : <span id="rt2">0</span> %)				        		
				        	</td>
				        	<th></th>
				        	<td></td>
				        </tr> 
				        <c:if test="${bidPlanDetail.ESTPC_SECD eq '180000'}">
					        <tr>
					        	<th scope="row">복수예비가격범위</th>
					            <td colspan="3">${bidPlanDetail.PLR_ESTPC_RNG_CD_NM}</td>
					        </tr>
				        </c:if>
				        <tr>
				        	<th scope="row">예정가격</th>
				            <td colspan="3">
					        	<c:if test="${bidPlanDetail.ESTPC_SECD eq '180000' and bidPlanDetail.ESTC_PSCD eq 'ES03'}">
					        		복수예가 생성완료
					        	</c:if>
					        	<c:if test="${bidPlanDetail.ESTPC_SECD eq '180001'}">
					            	<input type="text" class="w180" id="P_SCH_PRCE_AMT" name="P_SCH_PRCE_AMT" value="${bidPlanDetail.SCH_PRCE_AMT}" money onblur="baseAmtRT(this);">
					            	(기초금액대비 : <span id="rt">0</span> %)
					        	</c:if>
							</td>
				        </tr>
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
						DEXT5UPLOAD.config.Mode = 'view';
						DEXT5UPLOAD.config.Width = '100%';
						DEXT5UPLOAD.config.FolderNameRule = '/bid';
						var dext5Upload = new Dext5Upload("upload");
					</script>	            	
				</div>	
				<div id="upload_fileInfo"></div>		
						
				<div class="btn_wrap">
					<%-- 복수예가 --%>
					<c:if test="${bidPlanDetail.ESTPC_SECD eq '180000' and bidPlanDetail.ESTC_PSCD eq 'ES02'}">
						<c:if test="${toDayTime < bidPlanDetail.BIDC_SBMT_ENDT  }">
							<button type="button" class="btn btn_m btn_orange" id="rjtBtn">반려</button>
							<button type="button" class="btn btn_m btn_orange" id="saveBtn">등록완료</button>
						</c:if>
					</c:if>
					<%-- 단일예가 --%>
					<c:if test="${bidPlanDetail.ESTPC_SECD eq '180001'}">
						<c:if test="${bidPlanDetail.BID_PSCD eq 'PF20' or bidPlanDetail.BID_PSCD eq 'PF30'}">
							<button type="button" class="btn btn_m btn_orange" id="rjtBtn">반려</button>
							<button type="button" class="btn btn_m btn_orange" id="saveBtn">등록완료</button>
						</c:if>
					</c:if>
			    	<button type="button" class="btn btn_m btn_del" id="listBtn" name="listBtn">목록</button>
			    </div>
			</div>
		</fieldset>
	</form>
</div> <!--// content E-->

<form id="listFrm" method="POST">
	<input type="hidden" name="resourceName" value="${ param.resourceName }">
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