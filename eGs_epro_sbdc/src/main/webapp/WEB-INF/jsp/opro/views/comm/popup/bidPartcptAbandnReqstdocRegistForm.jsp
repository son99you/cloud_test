<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%--
 * 입찰포기신청서 
 *
 * <pre>
 * elbi 
 *  |_popup
 *   | 	bidPartcptAbandnReqstdocRegistForm.jsp
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

<script type="text/javascript" src="${jsPath}/opro/views/comm/popup/bidPartcptAbandnReqstdocRegistForm.js"></script>

<div id="windowPopup">
	<h3 class="windowTitle">입찰포기신청서</h3>
    <form id="registFrm" method="POST">
	<fieldset>
        <legend>입찰포기신청서</legend>
        <div class="tableLayer">
            <p class="popSubTitle marginSet"></p>
            <table class="table">
                <caption>신청개요</caption>
                <colgroup>
                   <col width="120px">
                   <col width="400px">
                </colgroup>
                <tbody>
	                <tr class="line">
	                    <th>입찰번호</th>
	                    <td>P2017-00004-1</td>
	                </tr>
	                <tr>
	                    <th>입찰명</th>
	                    <td>송변전분야 유지보수용 예비품-램프 등 17종 </td>
	                </tr>
	                <tr>
	                    <th>업체명</th>
	                    <td>은우소프트</td>
	                </tr>
                </tbody>
            </table>
        </div>
        <div class="tableLayer">
            <table class="table">
                <caption></caption>
                <colgroup>
                    <col width="120px">
                    <col width="400px">
                </colgroup>
                <tbody>
	                <tr>
	                    <th>포기일자</th>
	                    <td id="abandnRegistDt">2017-01-30</td>
	                </tr>
	                <tr>
	                    <th>포기사유</th>
	                    <td><textarea rows="5" maxlength="4000" name="P_BIAB_RESN_CN"><c:out value="다시 입찰하려고..."></c:out></textarea></td>
	                </tr>
                </tbody>
            </table>
        </div>
        
        <div class="T_btnLayer fr">
            <button type="button" class="blueBtn L" id="saveBtn">저장</button>
            <button type="button" class="blueBtn L"  onclick="window.close();">닫기</button>
        </div>
    </fieldset>
    </form>
</div>