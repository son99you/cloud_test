<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%--
 *  입찰공고 의견등록 
 *
 * <pre>
 * elbi 
 *  |_popup
 *   | 	bidPblancOpinionRegistForm.jsp
 * 
 * </pre>
 * @date : 2017. 06. 20.
 * @version : 1.0
 * @author : 은우소프트 이주연 
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %>
 
<script type="text/javascript" src="${jsPath}/opro/views/comm/popup/bidPblancOpinionRegistForm.js"></script>

<div id="windowPopup">
	<c:if test="${param.P_GBN eq 'new' }"><h3 class="windowTitle">입찰공고 의견 등록</h3></c:if>
	<c:if test="${param.P_GBN ne 'new' }"><h3 class="windowTitle">입찰공고 의견 정보</h3></c:if>
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
		                       <c:if test="${param.P_GBN eq 'new' }"> <input type="text" id="P_OPINION_SJ_NM" name="P_OPINION_SJ_NM" value="" style="width: 560px" maxlength="300"></c:if>
	                    		<c:if test="${param.P_GBN ne 'new' }">입찰공고 의견 1 </c:if>
	                    </td>
	                </tr>
	                <tr>
	                    <th scope="row" class="bullet_orange">등록자</th>
	                    <td>
		                	<label for="P_OPINION_REGISTER_NM" class="blind">등록자</label>
		                        <c:if test="${param.P_GBN eq 'new' }"><input type="text" id="P_OPINION_REGISTER_NM" name="P_OPINION_REGISTER_NM" value="" style="width: 210px" maxlength="30"></c:if>
	                			<c:if test="${param.P_GBN ne 'new' }">이주연</c:if> 
					 	</td>
	                    <th scope="row" class="bullet_orange">이메일</th>
	                    <td>
		                	<label for="P_OPINION_REGISTER_EMAIL_ADRES" class="blind">이메일</label>
		                        <c:if test="${param.P_GBN eq 'new' }"><input type="text" id="P_OPINION_REGISTER_EMAIL_ADRES" name="P_OPINION_REGISTER_EMAIL_ADRES" value="" style="width: 210px" maxlength="50" eMail></c:if>
	                   			<c:if test="${param.P_GBN ne 'new' }">eunwoo@eunwoosoft.com</c:if> 
	                    </td>
	                </tr>
	                <tr>
	                    <th scope="row" class="bullet_orange">내용</th>
	                    <td colspan="3">
	                    	<label for="P_OPINION_CN" class="blind">내용</label>
	                    		<c:if test="${param.P_GBN eq 'new' }"><textarea id="P_OPINION_CN" name="P_OPINION_CN" rows="5" cols="49" maxlength="4000"></textarea></c:if>
	                    		<c:if test="${param.P_GBN ne 'new' }">의견정보 내용</c:if> 
	                    </td>
	                </tr>
                </tbody>
            </table>
        </div>
        
        <div class="T_btnLayer fr">
         <c:if test="${param.P_GBN eq 'new' }">
           	<button type="button" class="blueBtn L" id="opinionRegistBtn">의견저장</button></c:if>
          	<button type="button" class="blueBtn L" id="closeBtn" onclick="window.close();">닫기</button>
       </div>
</div>         