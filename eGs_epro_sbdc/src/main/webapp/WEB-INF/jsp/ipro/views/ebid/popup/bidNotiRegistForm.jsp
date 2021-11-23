<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 입찰현황 > 입찰공지
 *
 * <pre>
 * ebid 
 *    |_ popup
 			  |_ bidNotiRegistForm.jsp
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

<script type="text/javascript" src="${jsPath}/ipro/views/ebid/popup/bidNotiRegistForm.js"></script> 
 
<div class="pop_wrap">
	<div class="pop_header">
		<h1 class="tit">입찰공지</h1>
	</div> <!--// pop_header E -->
	<form id="registFrm" method="POST" action="">
	    <input type="hidden" name="P_ANNC_NO" value="${param.P_ANNC_NO}">
		<input type="hidden" name="P_ANNC_NGR" value="${param.P_ANNC_NGR}">
		<input type="hidden" name="P_OPNN_SN" value="${param.P_OPNN_SN}">
		<div class="pop_container">
			<div class="view_wrap typeC">
		
<!-- 				<div class="tit_area"> -->
<!-- 					<h2 class="tit01">답변정보</h2> -->
<!-- 				</div>  -->
				<div class="view_area">
					<table class="table">
				    	<colgroup>
				        	<col width="15%" />
				            <col width="85%" />
				        </colgroup>
				        <tr>
				            <th scope="row">등록일시</th>
		                    <td>
		                    	${comFn:formatDate(today,'yyyyMMddHHmmss','yyyy-MM-dd HH:mm')}
		                    </td>
				        </tr>
				        <tr>
				            <th scope="row" class="bullet_orange">내용</th>
		                    <td colspan="3">
		                    	<label for="P_RPLY_CNTN" class="blind">내용</label>
		                    	<textarea id="P_RPLY_CNTN" name="P_RPLY_CNTN" rows="5" cols="49" maxlength="4000" style="width: 99%">${bidNotiDetail.RPLY_CNTN }</textarea>
		                    </td>
				        </tr>
				    </table>
				</div>
				<div class="btn_wrap view_btn">
					<button type="button" class="btn btn_m btn_orange" id="answerRegistBtn" >저장</button>
					<c:if test="${not empty bidNotiDetail.RPLY_CNTN }">
		            	<button type="button" class="btn btn_m btn_orange" id="answerDeleteBtn">삭제</button>
					</c:if>
			    	<button type="button" class="btn btn_m btn_del" id="closeBtn" >닫기</button>
			    </div>
		    </div>
	    </div>
	</form>
</div> <!--// content E-->
