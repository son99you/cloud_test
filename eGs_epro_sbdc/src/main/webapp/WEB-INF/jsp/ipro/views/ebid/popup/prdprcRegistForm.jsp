<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 입찰현황 > 유찰등록 팝업
 *
 * <pre>
 * ebid 
 *    |_ popup
 			  |_ fibRegistForm.jsp
 * 
 * </pre>
 * @date : 2017. 06. 14
 * @version : 1.0
 * @author : 은우소프트 전상훈
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %> 
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %> 

<script type="text/javascript" src="${jsPath}/ipro/views/ebid/popup/prdprcRegistForm.js"></script> 
 
<div class="pop_wrap">
	<div class="pop_header">
		<h1 class="tit">예가생성</h1>
	</div> <!--// pop_header E -->
	<div class="pop_container">
		<div class="view_wrap typeA">
			<form id="registFrm"  method="POST" >
	    		<input type="hidden" id="P_ANNC_NO" name="P_ANNC_NO" value="${bidPlanDetail.ANNC_NO }">
	    		<input type="hidden" id="P_ANNC_NGR" name="P_ANNC_NGR" value="${bidPlanDetail.ANNC_NGR }">
	    		<input type="hidden" id="P_ROUND_NO" name="P_ROUND_NO" value="${bidPlanDetail.ROUND_NO }">
	    		<input type="hidden" id="P_APPR_STCD" value="${biApprCntc.APPR_STCD }">
	    		<input type="hidden" id="P_OPNG_YN" value="${bidPlanDetail.OPNG_YN }">
				<div class="view_area">
					<table class="table">
				    	<colgroup>
				        	<col width="15%" />
				            <col width="35%" />
				            <col width="15%" />
				            <col width="35%" />
				        </colgroup>
				    	<tr>
			                <th scope="row">사업예산 (원)</th>
			                <td>${comFn:formatMoney(bidPlanDetail.BSNS_BDG_AMT) }</td>
			                <th scope="row">기초금액 (원)</th>
			                <td>${comFn:formatMoney(prdprcInfoInqire.BASE_ESTPC_AMT) }</td>
						</tr>
						<tr>
			                <th scope="row">추정금액 (원)</th>
			                <td>${comFn:formatMoney(bidPlanDetail.ESTT_AMT) }</td>
			                <th scope="row">추정가격 (원)</th>
			                <td>${comFn:formatMoney(bidPlanDetail.ESTT_PRCE) }</td>
						</tr>
				       <tr>
			                <th scope="row">산정기준&산정방법</th>
							<td colspan="3">
-복수예가<br>
기초금액을 근거로 하여 15개의 예비가격을 작성(비공개)한 후 이중 입찰 참가자로 하여금 예비가격을 선택하게 하고<br> 
최다빈도수의 예비가격 산술평균으로 예정가격을 결정하는 방식<br><br>
-단일예가<br>
복수예가방식을 사용하지 않고 기초금액을 근거로 하여 담당자가 미리 예정가격을 결정하는 방식
							</td>
			            </tr>
			            <tr>
			                <th scope="row">첨부파일</th>
			                <td colspan="3">
			                	<c:if test="${not empty bidAtchDocList }">
			                   		<c:forEach items="${bidAtchDocList }" var="data" varStatus="state">
										<a href="javascript:pageObj.download('${data.FILE_SN}','${data.FILE_GRP_NO }');" class="attfile">${data.SYS_FILE_NM }</a><br>
			                    	</c:forEach>  
			                    </c:if>
			                    <c:if test="${empty bidAtchDocList }">
									첨부파일이 없습니다.
			                    </c:if>
			                </td>
			            </tr>
				    </table>
				</div>
				
				<div class="tit_area">
					<h2 class="tit01" style="color: red;">예정가격</h2>
				</div>
				<div class="view_area">
					<table class="table">
				    	<colgroup>
				        	<col width="15%" />
				        	<col width="85%" />
				        </colgroup>
				    	<tr>
				    		<th scope="row" class="bullet_orange"><label for="P_SCH_PRCE_AMT">예정가격 (원)<br><span style="color:red;">(부가세포함)</span></label></th>
				    		<td><input type="text" class="w250" id="P_SCH_PRCE_AMT" name="P_SCH_PRCE_AMT" money></td>
				    	</tr>
				    </table>
				</div>
				
			</form>
		</div>
	</div>
	<div class="btn_wrap view_btn">
		<c:if test="${biApprCntc.APPR_STCD ne 'NEW'}">
			<c:if test="${bidPlanDetail.OPNG_YN ne 'Y'}">
				<button type="button" class="btn btn_02 btn_revise" id="registBtn" >예가생성</button>
			</c:if>
		</c:if>
    	<button type="button" class="btn btn_02 btn_close" id="closeBtn" >닫기</button>
    </div>
</div> <!--// content E-->
<form id="downFrm" method="POST" >
	<input type="hidden" name="P_FILE_SN">
	<input type="hidden" name="P_FILE_GRP_NO">
</form>

