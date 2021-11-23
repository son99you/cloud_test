<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 전자입찰 > 입찰 공지
 *
 * <pre>
 * elbi
 *    |_popup
 *        |_bidNotiDetail.jsp
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

<script type="text/javascript" src="${jsPath}/opro/views/ebid/popup/bidNotiDetail.js"></script>

<div id="windowPopup">
	<h3 class="windowTitle">입찰공지</h3>
    <form id="registFrm" method="POST" action="">
    <input type="hidden" name="P_ANNC_NO" value="${param.P_ANNC_NO}">
	<input type="hidden" name="P_ANNC_NGR" value="${param.P_ANNC_NGR}">
	<fieldset>
        <div class="tableLayer">
            <table class="table">
                <caption>입찰공지</caption>
                <colgroup>
                    <col width="15%">
                    <col width="85%">
                </colgroup>
                <tbody>
	                <tr class="line">
	                    <th scope="row">등록일시</th>
		                <td>${comFn:formatDate(bidPblancOpinionInfoDetail.RPLY_SV_DT,'yyyyMMddHHmmss','yyyy-MM-dd HH:mm')}</td>
	                </tr>
	                <tr>
	                    <th scope="row">내용</th>
	                    <td><textarea style="display: none;" taView>${bidPblancOpinionInfoDetail.RPLY_CNTN }</textarea> </td>
	                </tr>
                </tbody>
            </table>
        </div>
        
        <div class="T_btnLayer fr">
            <button type="button" class="blueBtn L" id="closeBtn">닫기</button>
        </div>
    </fieldset>
    </form>
</div>         