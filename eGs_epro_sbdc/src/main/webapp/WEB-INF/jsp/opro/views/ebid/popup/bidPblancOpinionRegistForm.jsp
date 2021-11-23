<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 전자입찰 > 입찰공고 의견 등록
 *
 * <pre>
 * elbi
 *    |_popup
 *        |_oepElbiPopupBidPblancOpinionRegistForm.jsp
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

<script type="text/javascript" src="${jsPath}/opro/views/ebid/popup/bidPblancOpinionRegistForm.js"></script>

<div id="windowPopup">
	<h3 class="windowTitle">입찰공고 의견 등록</h3>
    <form id="registFrm" method="POST" action="">
    <input type="hidden" name="resourceName" value="${param.resourceName}">
    <input type="hidden" name="P_PBLANC_NO" value="${param.P_PBLANC_NO}">
	<input type="hidden" name="P_PBLANC_ODR" value="${param.P_PBLANC_ODR}">
	<input type="hidden" id="P_BID_OPINION_NO" name="P_BID_OPINION_NO" value="${bidPblancOpinionInfoDetail.BID_OPINION_NO}">
	<input type="hidden" id="P_DELETE_AT" name="P_DELETE_AT" value="">
	<%-- <input type="text" id="clickType" name="clickType" value="${param.clickType }"> --%>
	<fieldset>
        <legend>입찰공고 의견 등록</legend>
        
        <%-- 답변이 등록되어 있지 않을 경우 활성화 --%>
        <c:if test="${empty bidPblancOpinionInfoDetail.ANSWER_SJ_NM}">
        <div class="tableLayer">
            <p class="popSubTitle marginSet">의견정보</p>
            <table class="table" summary="의견정보 입니다.">
                <caption>의견정보</caption>
                <colgroup>
                    <col width="120px">
                    <col width="230px">
                    <col width="120px">
                    <col width="230px">
                </colgroup>
                <tbody>
	                <tr class="line">
	                    <th scope="row" class="bullet_orange">제목</th>
		                <td colspan="3">
		                	<label for="P_OPINION_SJ_NM" class="blind">제목</label>
		                	<c:if test="${(bidPblancOpinionInfoDetail.ENTRPS_REGIST_NO eq sessionScope.loginResult.LOGIN_ID) || (param.clickType eq 'new') }">
		                        <input type="text" id="P_OPINION_SJ_NM" name="P_OPINION_SJ_NM" value="${bidPblancOpinionInfoDetail.OPINION_SJ_NM}" style="width: 560px" maxlength="300">
		                	</c:if>
		                	<c:if test="${bidPblancOpinionInfoDetail.ENTRPS_REGIST_NO ne sessionScope.loginResult.LOGIN_ID}">
		                		${bidPblancOpinionInfoDetail.OPINION_SJ_NM}
		                	</c:if>
	                    </td>
	                </tr>
	                <tr>
	                    <th scope="row" class="bullet_orange">등록자</th>
	                    <td>
		                	<label for="P_OPINION_REGISTER_NM" class="blind">등록자</label>
		                	<c:if test="${(bidPblancOpinionInfoDetail.ENTRPS_REGIST_NO eq sessionScope.loginResult.LOGIN_ID) || (param.clickType eq 'new') }">
		                        <input type="text" id="P_OPINION_REGISTER_NM" name="P_OPINION_REGISTER_NM" value="${bidPblancOpinionInfoDetail.OPINION_REGISTER_NM}" style="width: 210px" maxlength="30">
		                	</c:if>
		                	<c:if test="${bidPblancOpinionInfoDetail.ENTRPS_REGIST_NO ne sessionScope.loginResult.LOGIN_ID}">
		                		${bidPblancOpinionInfoDetail.OPINION_REGISTER_NM}
		                	</c:if>
	                   	</td>
	                    <th scope="row" class="bullet_orange">이메일</th>
	                    <td>
		                	<label for="P_OPINION_REGISTER_EMAIL_ADRES" class="blind">이메일</label>
		                	<c:if test="${(bidPblancOpinionInfoDetail.ENTRPS_REGIST_NO eq sessionScope.loginResult.LOGIN_ID) || (param.clickType eq 'new') }">
		                        <input type="text" id="P_OPINION_REGISTER_EMAIL_ADRES" name="P_OPINION_REGISTER_EMAIL_ADRES" value="${bidPblancOpinionInfoDetail.OPINION_REGISTER_EMAIL_ADRES}" style="width: 210px" maxlength="50" eMail>
		                	</c:if>
		                	<c:if test="${bidPblancOpinionInfoDetail.ENTRPS_REGIST_NO ne sessionScope.loginResult.LOGIN_ID}">
		                		${bidPblancOpinionInfoDetail.OPINION_REGISTER_EMAIL_ADRES}
		                	</c:if>
	                    </td>
	                </tr>
	                <tr>
	                    <th scope="row" class="bullet_orange">내용</th>
	                    <td colspan="3">
	                    	<label for="P_OPINION_CN" class="blind">내용</label>
	                    	<c:if test="${(bidPblancOpinionInfoDetail.ENTRPS_REGIST_NO eq sessionScope.loginResult.LOGIN_ID) || (param.clickType eq 'new') }">
	                    		<textarea id="P_OPINION_CN" name="P_OPINION_CN" rows="5" cols="49" maxlength="4000">${bidPblancOpinionInfoDetail.OPINION_CN}</textarea>
	                    	</c:if>
	                    	<c:if test="${bidPblancOpinionInfoDetail.ENTRPS_REGIST_NO ne sessionScope.loginResult.LOGIN_ID}">
	                    		<textarea id="P_CN" name="P_CN" rows="5" cols="49" style="display: none;" taView readonly="readonly"><c:out value="${bidPblancOpinionInfoDetail.OPINION_CN}"></c:out></textarea>
	                    	</c:if>
	                    </td>
	                </tr>
                </tbody>
            </table>
        </div>
        </c:if>
        <%-- 답변이 등록되어 있지 않을 경우 활성화 END --%>
        
        <%-- 답변이 등록되어 있을 경우 활성화 --%>
        <c:if test="${not empty bidPblancOpinionInfoDetail.ANSWER_SJ_NM}">
        <div class="tableLayer">
            <p class="popSubTitle marginSet">의견정보</p>
            <table class="table" summary="의견정보 입니다.">
                <caption>의견정보</caption>
                <colgroup>
                    <col width="120px">
                    <col width="230px">
                    <col width="120px">
                    <col width="230px">
                </colgroup>
                <tbody>
	                <tr class="line">
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
	                    	<label for="P_OPINION_CN" class="blind">내용</label>
	                    	<textarea id="P_OPINION_CN" rows="5" cols="49" style="display: none;" taView readonly="readonly"><c:out value="${bidPblancOpinionInfoDetail.OPINION_CN}"></c:out></textarea>
	                    </td>
	                </tr>
                </tbody>
            </table>
        </div>
        <div class="tableLayer">
            <p class="popSubTitle marginSet">답변정보</p>
            <table class="table" summary="답변정보 입니다.">
                <caption>답변정보</caption>
                <colgroup>
                    <col width="120px">
                    <col width="230px">
                    <col width="120px">
                    <col width="230px">
                </colgroup>
                <tbody>
	                <tr class="line">
	                    <th scope="row" class="bullet_orange">제목</th>
	                    <td colspan="3">
		                    <label for="P_ANSWER_SJ_NM" class="blind">제목</label>
	                        ${bidPblancOpinionInfoDetail.ANSWER_SJ_NM}
	                    </td>
	                </tr>
	                <tr>
	                    <th scope="row" class="bullet_orange">등록자</th>
	                    <td>
			                    <label for="P_ANSWER_REGISTER_NM" class="blind">등록자</label>
		                        ${bidPblancOpinionInfoDetail.ANSWER_REGISTER_NM}
			                    
	                    </td>
	                    <th scope="row" class="bullet_orange">이메일</th>
	                    <td>
		                    <label for="P_ANSWER_REGISTER_EMAIL_ADRES" class="blind">이메일</label>
	                        ${bidPblancOpinionInfoDetail.ANSWER_REGISTER_EMAIL_ADRES}
	                    </td>
	                </tr>
	                <tr>
	                    <th scope="row" class="bullet_orange">내용</th>
	                    <td colspan="3">
	                    	<label for="P_ANSWER_CN" class="blind">내용</label>
	                    	<textarea id="P_ANSWER_CN" name="P_ANSWER_CN" rows="5" cols="49" style="display: none;" taView readonly="readonly"><c:out value="${bidPblancOpinionInfoDetail.ANSWER_CN}"></c:out></textarea>
	                    </td>
	                </tr>
                </tbody>
            </table>
        </div>
        </c:if>
        <%-- 답변이 등록되어 있지 않을 경우 활성화 END --%>
        
        <div class="T_btnLayer fr">
        	<%-- 의견이 등록이 되어 있지 않으면 활성화 --%>
	        <c:if test="${empty bidPblancOpinionInfoDetail.OPINION_SJ_NM}">
            	<button type="button" class="blueBtn L" id="opinionRegistBtn">의견저장</button>
            </c:if>
            <%-- 의견이 등록이 되어 있지 않으면 활성화 END --%>
            <%-- 의견이 등록이 되어 있으면 활성화 --%>
	        <c:if test="${not empty bidPblancOpinionInfoDetail.OPINION_SJ_NM}">
            	<%-- 자신이 등록한 의견만 수정/삭제 가능 --%>
		        <c:if test="${bidPblancOpinionInfoDetail.ENTRPS_REGIST_NO eq sessionScope.loginResult.LOGIN_ID}">
	        		<%-- 답변이 등록되어 있지 않을 경우 활성화 --%>
			        <c:if test="${empty bidPblancOpinionInfoDetail.ANSWER_SJ_NM}">
			            <button type="button" class="blueBtn L" id="opinionRegistBtn">의견저장</button>
			            <button type="button" class="blueBtn L" id="opinionDeleteBtn">의견삭제</button>
			        </c:if>
			        <%-- 답변이 등록되어 있지 않을 경우 활성화 END --%>
	        	</c:if>
	        	<%-- 자신이 등록한 의견만 수정/삭제 가능 END --%>
            </c:if>
            <%-- 의견이 등록이 되어 있으면 활성화 END --%>
	        
            <button type="button" class="blueBtn L" id="closeBtn">닫기</button>
        </div>
    </fieldset>
    </form>
</div>         