<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 계약관리 > 기준정보 > 결재현황상세
 *
 * <pre>
 * cont 
 *    |_ infoAppResultDetail.jsp
 * 
 * </pre>
 * @date : 2018. 11. 06
 * @version : 1.0
 * @author : 은우소프트 
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %> 
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %> 

<script type="text/javascript" src="${jsPath}/ipro/views/info/infoAppResultDetail.js"></script> 

<div class="content">
		<div class="conts_wrap">
			<div class="inner">
				<div class="tit_wrap">
					<h3 class="tit">결재완료 상세</h3>
					<ul class="step_wrap">
						<li><a href="#">${comFn:fmIso2Euc(myMenuList.bigMenuNm)}</a></li>
						<li><a href="#">${comFn:fmIso2Euc(myMenuSubList.smallMenuNm)}</a></li>
					</ul> <!--// step_wrap E -->
				</div> <!--// tit_wrap E -->
				<div class="view_wrap typeB">
					<div class="btn_wrap view_btn" style="text-align: right;">
				    </div>
		    		<h4 class="tit">결재 정보</h4>
					<div class="view_area">
						<table>
					    	<colgroup>
					    		<col width="15%">
					    		<col width="35%">
					    		<col width="15%">
					    		<col width="35%">
					        </colgroup>
					    	 <tr>
					        	<th class="txtl"><img src="${imagePath}/ipro/main/dot_blue.gif" width="11" height="11">결재명</th>
					            <td colspan="3">
					            	입찰공고 결재요청 합니다.
					            </td>
					         </tr>
					         <tr>   
					         	<th class="txtl"><img src="${imagePath}/ipro/main/dot_blue.gif" width="11" height="11">요청자</th>
					            <td >
					            	사용자1
					            </td>
					        	<th class="txtl"><img src="${imagePath}/ipro/main/dot_blue.gif" width="11" height="11">요청일시</th>
					            <td>
					            	2018-11-06
					            </td>
					        </tr> 
						</table>
					</div>		    
					<br>
					<h4 class="tit">결재자정보</h4>
					<div id="apprList">
						<div class="view_area">
							<table>
						    	<colgroup>
						    		<col width="5%">
						    		<col width="10%">
						    		<col width="20%">
						    		<col width="10%">
						    		<col width="*">
						    		<col width="15%">
						        </colgroup>
						        <thead>
							        <tr>
							        	<th style="text-align: center;">순번</th>
							        	<th style="text-align: center;">성명</th>
							        	<th style="text-align: center;">부서</th>
							        	<th style="text-align: center;">일자</th>
							        	<th style="text-align: center;">의견</th>
							        	<th style="text-align: center;">결재여부</th>

							        </tr>
						        </thead>
						        <tbody id="apprShowTbdy">
						        	<tr>
						        		<td style="text-align: center;">1</td>
						        		<td style="text-align: center;">결재자1</td>
						        		<td>관리팀</td>
						        		<td style="text-align: center;">2018-11-06</td>
						        		<td>의견없음</td>
						        		<td style="text-align: center;">승인</td>
						        	</tr>
						        	<tr>
						        		<td style="text-align: center;">2</td>
						        		<td style="text-align: center;">결재자2</td>
						        		<td>법무팀</td>
						        		<td style="text-align: center;">2018-11-06</td>
						        		<td></td>
						        		<td style="text-align: center;">숭안</td>
						        	</tr>
						        	<tr>
						        		<td style="text-align: center;">3</td>
						        		<td style="text-align: center;">결재자3</td>
						        		<td>법무팀</td>
						        		<td style="text-align: center;">2018-11-07</td>
						        		<td></td>
						        		<td style="text-align: center;">승인</td>
						        	</tr>
								<%-- <c:if test="${comFn:nvl(tApprTglListTotCnt, 0) == 0}">
									<tr>
										<td colspan="8" class="txt-center">검색 조건에 해당하는 데이터가 존재하지 않습니다.</td>
									</tr>
								</c:if> --%>
								<%-- <c:if test="${tApprTglListTotCnt > 0}">
								<c:forEach var="data" items="${tApprTglList}" varStatus="status" >			        
							        <tr>
							        	<td style="text-align: center;">${status.count}</td>
							        	<td style="text-align: center;">${data.USER_NM}</td>
							        	<td style="text-align: center;">${data.ORG_NM}</td>
							        	<td style="text-align: center;"></td>
							        	<td style="text-align: center;">
							        		<c:if test="${data.APPR_STAT eq 'A'}">작성</c:if>
											<c:if test="${data.APPR_STAT eq 'B'}">승인</c:if>
											<c:if test="${data.APPR_STAT eq 'C'}">반려</c:if>
							        	</td>
							        	<td style="text-align: center;">${data.MOD_DT}</td>
							        	<td style="text-align: center;">
							        		<button type="button" class="btn btn_02 btn_sch" onclick="opinionInsertPopup('${data.APRP_SN}');">의견작성</button>
							        	</td>
							        	<td style="text-align: center;">
							        		<button type="button" class="btn btn_02 btn_sch" onclick="apprTglStatUpdate('${data.APRP_SN}', 'B');">승인</button>
							        		<button type="button" class="btn btn_02 btn_sch" onclick="apprTglStatUpdate('${data.APRP_SN}', 'C');">반려</button>
							        	</td>
							        </tr>
								</c:forEach>
								</c:if> --%>
						        </tbody>
						    </table>
						</div>
					</div>
					<br><br>
					<h4 class="tit">결재문서정보</h4>
					<div class="view_area">
						<iframe id="doc_frame" name="doc_frame" style="width: 100%; height: 500px;" src="" onload="iframeElementSet();"></iframe>
					</div>
					<!-- <br>			
					<h4 class="tit">계약서 품의</h4>
					<div class="view_area">
					</div>
		    		<br>
		    		<h4 class="tit">품의내용</h4>
					<div class="view_area">
					</div> -->
					<div class="btn_wrap">
						<%-- <c:if test="${param.gbn eq 'A' }"> 
		             		<button type="button" class="btn btn_02 btn_revise" id="aprBtn">승인</button> 
		             		<button type="button" class="btn btn_02 btn_revise" id="returnBtn">반려</button>
		             	 </c:if>  --%>
		            	<button type="button" class="btn btn_02 btn_sch" id="listBtn">목록</button>
		            </div>
				</div>
			</div>
		</div>
	
	<form id="listFrm" method="POST">
		<input type="hidden" name="resourceName" value="${ param.resourceName }" >
	</form>
	<form id="detailFrm" target="doc_frame" method="POST">
		<input type="hidden" name="P_BEFFAT_PBLANC_NO" value="${tApprMstList[0].APRDC_INTL_NO }" >
		<input type="hidden" name="resourceName" value="${ param.resourceName }" >
	</form>
	
	<form id="popupFrm" method="POST" action="${contextPath}/info/popup/infoApprRsnRegist.do">
		<input type="hidden" id="P_USER_NM_S" name="P_USER_NM_S">
		<input type="hidden" id="P_APRDC_NO_S" name="P_APRDC_NO_S" value="${tApprMstList[0].APRDC_NO}">
		<input type="hidden" id="P_APRP_SN_S" name="P_APRP_SN_S">
	</form>
	
	<form id="statFrm" method="POST" action="${contextPath}/info/infoApprTglUpdate.do">
		<input type="hidden" id="P_USER_NM_S" name="P_USER_NM_S">
		<input type="hidden" id="P_APRDC_NO" name="P_APRDC_NO" value="${tApprMstList[0].APRDC_NO}">
		<input type="hidden" id="P_APRDC_SE" name="P_APRDC_SE" value="${tApprMstList[0].APRDC_SE}">
		<input type="hidden" id="P_APRP_SN" name="P_APRP_SN">
		<input type="hidden" id="P_APPR_STAT" name="P_APPR_STAT">
	</form>
	<form id="docuFrm" method="POST" action="${contextPath}/info/infoAppConsultDetail.do"> 
		<input type="hidden" id="P_APRDC_NO_S" name="P_APRDC_NO_S" value="${tApprMstList[0].APRDC_NO}" >
		<input type="hidden" id="P_APRDC_SE_S" name="P_APRDC_SE_S" value="${tApprMstList[0].APRDC_SE}" >
		<input type="hidden" name="resourceName" value="${ param.resourceName }" >
	</form>	
	
</div> <!--// content E-->