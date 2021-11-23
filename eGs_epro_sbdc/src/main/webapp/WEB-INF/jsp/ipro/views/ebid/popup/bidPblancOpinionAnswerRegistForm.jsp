<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 입찰현황 > 입찰의견 답변등록 폼
 *
 * <pre>
 * ebid 
 *    |_ popup
 			  |_ bidPblancOpinionAnswerRegistForm.jsp
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

<script type="text/javascript" src="${jsPath}/ipro/views/ebid/popup/bidPblancOpinionAnswerRegistForm.js"></script> 
 
<div class="pop_wrap">
	<div class="pop_header">
		<h1 class="tit">입찰공고 의견 조회</h1>
	</div> <!--// pop_header E -->
	<form id="registFrm" method="POST" action="">
	    <input type="hidden" id="resourceName" name="resourceName" value="${param.resourceName}">
	    <input type="hidden" name="P_PBLANC_NO" value="${bidPblancOpinionInfoDetail.PBLANC_NO}">
		<input type="hidden" name="P_PBLANC_ODR" value="${bidPblancOpinionInfoDetail.PBLANC_ODR}">
		<input type="hidden" id="P_BID_OPINION_NO" name="P_BID_OPINION_NO" value="${bidPblancOpinionInfoDetail.BID_OPINION_NO}">
		<div class="pop_container">
			<div class="view_wrap typeC">
				<div class="tit_area">
					<h2 class="tit01">의견정보</h2>
				</div> 
				<div class="view_area">
					<table class="table">
				    	<colgroup>
				        	<col width="15%" />
				            <col width="35%" />
				            <col width="15%" />
				            <col width="35%" />
				        </colgroup>
				    	<tr>
				        	<th scope="row">제목</th>
							<td colspan="3">${bidPblancOpinionInfoDetail.OPINION_SJ_NM}</td>
				        </tr>
				        <tr>
				        	<th scope="row">등록자</th>
		                    <td>${bidPblancOpinionInfoDetail.OPINION_REGISTER_NM}</td>
		                    <th scope="row">이메일</th>
		                    <td>${bidPblancOpinionInfoDetail.OPINION_REGISTER_EMAIL_ADRES}</td>
				        </tr>
				        <tr>
				        	<th scope="row">내용</th>
		                    <td colspan="3">
		                    	<label for="P_NTCN_RESN_CN" class="blind">내용</label>
		                    	<textarea id="P_NTCN_RESN_CN" name="P_NTCN_RESN_CN" rows="5" cols="49" readonly taView style="display: none;"><c:out value="${bidPblancOpinionInfoDetail.OPINION_CN}"></c:out></textarea>
		                    </td>
				        </tr>
				    </table>
				</div>
		
				<div class="tit_area">
					<h2 class="tit01">답변정보</h2>
				</div> 
				<div class="view_area">
					<table class="table">
				    	<colgroup>
				        	<col width="15%" />
				            <col width="35%" />
				            <col width="15%" />
				            <col width="35%" />
				        </colgroup>
				        <tr>
				        	<th scope="row" class="bullet_orange">제목</th>
		                    <td colspan="3">
		                    	<c:if test="${empty bidPblancOpinionInfoDetail.ANSWER_SJ_NM }">
			                    	<label for="P_ANSWER_SJ_NM" class="blind">제목</label>
		                        	<input type="text" id="P_ANSWER_SJ_NM" name="P_ANSWER_SJ_NM" value="${bidPblancOpinionInfoDetail.ANSWER_SJ_NM}" style="width: 97%" maxlength="300">
		                        </c:if>
		                        <c:if test="${not empty bidPblancOpinionInfoDetail.ANSWER_SJ_NM}">
		                        	${bidPblancOpinionInfoDetail.ANSWER_SJ_NM}
		                        </c:if>
		                    </td>
				        </tr>
				        <tr>
				            <th scope="row" class="bullet_orange">등록자</th>
		                    <td>
		                    	<c:if test="${empty bidPblancOpinionInfoDetail.ANSWER_SJ_NM }">
				                    <label for="P_ANSWER_REGISTER_NM" class="blind">등록자</label>
			                        <input type="text" class="disabled" id="P_ANSWER_REGISTER_NM" value="${sessionScope.loginResult.USER_NM}" style="width: 93%" readonly >
			                    	<label for="P_ANSWER_REGISTER_ID" class="blind">등록자ID</label>
		                        	<input type="hidden"id="P_ANSWER_REGISTER_ID" name="P_ANSWER_REGISTER_ID" value="${sessionScope.loginResult.USER_ID}">
		                        </c:if>
		                        <c:if test="${not empty bidPblancOpinionInfoDetail.ANSWER_SJ_NM}">
				                    <label for="P_ANSWER_REGISTER_NM" class="blind">등록자</label>
			                        <input type="text" class="disabled" id="P_ANSWER_REGISTER_NM" value="${bidPblancOpinionInfoDetail.ANSWER_REGISTER_NM}" style="width: 93%" readonly >
			                    	<label for="P_ANSWER_REGISTER_ID" class="blind">등록자ID</label>
		                        	<input type="hidden"id="P_ANSWER_REGISTER_ID" name="P_ANSWER_REGISTER_ID" value="${bidPblancOpinionInfoDetail.ANSWER_REGISTER_ID}">
		                        </c:if>
		                    </td>
		                    <th scope="row" class="bullet_orange">이메일</th>
		                    <td>
		                    	<c:if test="${empty bidPblancOpinionInfoDetail.ANSWER_SJ_NM}">
			                    	<label for="P_ANSWER_REGISTER_EMAIL_ADRES" class="blind">이메일</label>
		                        	<input type="text" id="P_ANSWER_REGISTER_EMAIL_ADRES" name="P_ANSWER_REGISTER_EMAIL_ADRES" value="${bidPblancOpinionInfoDetail.ANSWER_REGISTER_EMAIL_ADRES}" style="width: 93%" maxlength="50" eMail>
		                        </c:if>
		                        <c:if test="${not empty bidPblancOpinionInfoDetail.ANSWER_SJ_NM}">
		                        	${bidPblancOpinionInfoDetail.ANSWER_REGISTER_EMAIL_ADRES}
		                        </c:if>
		                    </td>
				        </tr>
				        <tr>
				            <th scope="row" class="bullet_orange">내용</th>
		                    <td colspan="3">
		                    	<c:if test="${empty bidPblancOpinionInfoDetail.ANSWER_SJ_NM}">
			                    	<label for="P_ANSWER_CN" class="blind">내용</label>
			                    	<textarea id="P_ANSWER_CN" name="P_ANSWER_CN" rows="5" cols="49" maxlength="4000" style="width: 99%"></textarea>
			                     </c:if>
			                     <c:if test="${not empty bidPblancOpinionInfoDetail.ANSWER_SJ_NM}">
			                    	<label for="P_ANSWER_CN" class="blind">내용</label>
			                    	<textarea id="P_ANSWER_CN" name="P_ANSWER_CN" rows="5" cols="49" maxlength="4000" readonly="readonly" taView style="display: none; width: 99%;"><c:out value="${bidPblancOpinionInfoDetail.ANSWER_CN}"></c:out></textarea>
			                     </c:if>
		                    </td>
				        </tr>
				    </table>
				</div>
				<div class="btn_wrap view_btn">
					<c:if test="${empty bidPblancOpinionInfoDetail.ANSWER_SJ_NM }">
						<button type="button" class="btn btn_02 btn_revise" id="answerRegistBtn" >답변저장</button>
					</c:if>
					<c:if test="${sessionScope.loginResult.USER_ID eq bidPblancOpinionInfoDetail.ANSWER_REGISTER_ID && not empty bidPblancOpinionInfoDetail.ANSWER_SJ_NM }">
		            	<button type="button" class="btn btn_02 btn_close" id="answerDeleteBtn">답변삭제</button>
		            </c:if>
			    	<button type="button" class="btn btn_02 btn_close" id="closeBtn" >닫기</button>
			    </div>
		    </div>
	    </div>
	</form>
</div> <!--// content E-->
