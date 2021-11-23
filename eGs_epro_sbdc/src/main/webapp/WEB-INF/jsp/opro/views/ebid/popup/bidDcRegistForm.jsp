<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 전자입찰 > 입찰설명회 참가
 *
 * <pre>
 * elbi
 *    |_popup
 *        |_bidDcRegistForm.jsp
 * 
 * </pre>
 * @date : 2015. 02. 12. 오후 4:49:25
 * @version : 1.0
 * @author : 은우소프트 하성윤
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %>

<script type="text/javascript" src="${jsPath}/opro/views/ebid/popup/bidDcRegistForm.js"></script>

<div class="pop_wrap">
	<div class="pop_header">
		<h1 class="tit">설명회 참가신청</h1>
	</div> <!--// pop_header E -->
	<div class="pop_container">
		<form id="registFrm" method="POST" action="">
		    <input type="hidden" name="P_ANNC_NO" value="${param.P_ANNC_NO}">
			<input type="hidden" name="P_ANNC_NGR" value="${param.P_ANNC_NGR}">
			
			<div class="view_wrap typeC">
				<div class="tit_area">
					<h2 class="tit01">참가자정보</h2>
				</div> <!--// tit_area E -->
							
				<div class="view_area typeB">
		            <table>
		                <caption>참가자정보</caption>
		                <colgroup>
		                    <col width="15%">
		                    <col width="35%">
		                    <col width="15%">
		                    <col width="35%">
		                </colgroup>
		                <tbody>
			                <tr>
			                    <th scope="row">업체명</th>
				                <td>${VEND_NM}</td>
				                <th scope="row" class="bullet_orange">참가자</th>
			                    <td><input type="text" name="P_ATNPE_NM" value="${bidDcDetail.ATNPE_NM }"></td>
			                </tr>
			                <tr>
			                    <th scope="row" class="bullet_orange">전화번호</th>
			                    <td><input type="text" name="P_ATNPE_TEL_NO" value="${bidDcDetail.ATNPE_TEL_NO }" tel></td>
			                    <th scope="row" class="bullet_orange">이메일</th>
			                    <td><input type="text" name="P_ATNPE_EMAL" value="${bidDcDetail.ATNPE_EMAL }" eMail></td>
			                </tr>
		                </tbody>
		            </table>
				</div>
	       
		        <div class="btn_wrap view_btn">
		           	<button type="button" class="btn btn_m btn_orange" id="brfsBtn">신청</button>
		           	<c:if test="${not empty bidDcDetail }">
			           	<button type="button" class="btn btn_m btn_orange" id="delBtn">삭제</button>
		           	</c:if>
		            <button type="button" class="btn btn_m btn_del" id="closeBtn">닫기</button>
		        </div>							
			</div>
		</form>	
	</div>
</div>