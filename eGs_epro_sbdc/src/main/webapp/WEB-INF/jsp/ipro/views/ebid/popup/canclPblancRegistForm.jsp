<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 입찰현황 > 취소공고 팝업
 *
 * <pre>
 * ebid 
 *    |_ popup
 			  |_ canclPblancRegistForm.jsp
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

<script type="text/javascript" src="${jsPath}/ipro/views/ebid/popup/canclPblancRegistForm.js"></script> 
 
<div class="pop_wrap">
	<div class="pop_header">
		<h1 class="tit">취소공고</h1>
	</div> <!--// pop_header E -->
	<form id="registFrm" method="POST" action="">
 		<input type="hidden" name="P_ANNC_NO" value="${bidPlanDetail.ANNC_NO}">
		<input type="hidden" name="P_ANNC_NGR" value="${bidPlanDetail.ANNC_NGR}">
		<input type="hidden" name="P_ROUND_NO" value="${bidPlanDetail.ROUND_NO}">
 		<input type="hidden" name="P_BID_PSCD" value="PF41">
		<div class="pop_container">
			<div class="view_wrap typeC">
				<div class="view_area">
					<table class="table">
				    	<colgroup>
				        	<col width="15%" />
				            <col width="35%" />
				            <col width="15%" />
				            <col width="35%" />
				        </colgroup>
				    	<tr>
				        	<th>입찰번호</th>
				            <td>${bidPlanDetail.ANNC_NO}-${bidPlanDetail.ANNC_NGR}</td>
				            <th>공고일자</th>
				            <td>${comFn:formatDate(bidPlanDetail.ANNC_DT,'yyyyMMddHHmmss','yyyy-MM-dd')}</td>
				        </tr>
				        <tr>
				        	<th>입찰명</th>
				            <td colspan="3">${bidPlanDetail.BID_NM}</td>
				        </tr>
				        <tr>
				        	<th>입찰방법</th>
				            <td>${bidPlanDetail.CONT_MTCD_NM}&nbsp;/&nbsp;${bidPlanDetail.SBID_MTCD_NM}</td>
				            <th>취소처리일자</th>
				            <td>${comFn:formatDate(canclProcessDt,'yyyyMMddHHmmss','yyyy-MM-dd HH:mm')}</td>
				        </tr>
				        <tr>
				            <th class="bullet_orange">취소사유</th>
				            <td colspan="3">
				            	<label for="P_PROC_RSN" class="blind">취소사유</label>
		                    	<textarea id="P_PROC_RSN" name="P_PROC_RSN" style="width: 99%; height: 50px;" maxlength="4000"></textarea>
				            </td>
				        </tr>
				    </table>
				</div>
				<div class="btn_wrap view_btn">
					<button type="button" class="btn btn_m btn_orange" id="registBtn" >저장</button>
			    	<button type="button" class="btn btn_m btn_del" id="closeBtn" >닫기</button>
			    </div>
			</div>
		</div>
	</form>
</div> <!--// content E-->
