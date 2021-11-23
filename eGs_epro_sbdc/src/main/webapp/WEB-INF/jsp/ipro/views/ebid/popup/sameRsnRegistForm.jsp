<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 낙찰자선정 > 낙찰자선전 결격사유 등록
 *
 * <pre>
 * ebid 
 *    |_ popup
 			  |_ sucbidrSlctnBrdoResnRegistForm.jsp
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

<script type="text/javascript" src="${jsPath}/ipro/views/ebid/popup/sameRsnRegistForm.js"></script> 
 
<div class="pop_wrap">
	<div class="pop_header">
		<h1 class="tit">낙찰 사유</h1>
	</div> <!--// pop_header E -->
	<div class="pop_container">
		<div class="view_wrap typeB">
			<div class="view_area">
			    <form id="registFrm" method="POST">
				    <input type="hidden" name="P_VEND_REG_NO" value="${ param.P_VEND_REG_NO }">
				    <input type="hidden" name="P_ANNC_NO" value="${ param.P_ANNC_NO }">
				    <input type="hidden" name="P_ANNC_NGR" value="${ param.P_ANNC_NGR }">
				    <input type="hidden" name="P_ROUND_NO" value="${ param.P_ROUND_NO }">
					<table class="table">
				    	<colgroup>
				        	<col width="15%" />
				            <col width="85%" />
				        </colgroup>
				    	<tr class="line">
			                <th>공고번호</th>
			                <td>${param.P_ANNC_NO}-${param.P_ANNC_NGR}</td>
			            </tr>
			            <tr>
			            	<th>낙찰사유</th>
			            	<td><textarea name="P_PROC_RSN"></textarea></td>
			            </tr>
				    </table>
				</form>
			</div> <!--// view_area E -->
			<div class="btn_wrap view_btn">
		        <button type="button" class="btn btn_02 btn_revise" id="registBtn">낙찰</button>
    			<button type="button" class="btn btn_02 btn_close" id="closeBtn" >닫기</button>
    		</div>
   		</div> <!--// view_wrap E -->
	</div> <!--// pop_container E -->
</div> <!--// pop_wrap E -->
