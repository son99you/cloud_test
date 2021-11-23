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

<script type="text/javascript" src="${jsPath}/opro/views/ebid/popup/bidPartcptAbandnReqstdocRegistForm.js"></script>

<div id="windowPopup">
	<h3 class="windowTitle">입찰포기제출</h3>
    <form id="registFrm" method="POST">
    <input type="hidden" name="resourceName" value="${param.resourceName}">
    <input type="hidden" name="P_ANNC_NO" value="${myPartcptReqstSttusDetail.ANNC_NO}"/>
	<input type="hidden" name="P_ANNC_NGR" value="${myPartcptReqstSttusDetail.ANNC_NGR}"/>
	<input type="hidden" name="P_ROUND_NO" value="${myPartcptReqstSttusDetail.ROUND_NO}"/>
	<fieldset>
        <legend>입찰포기</legend>
        <div class="tableLayer">
            <p class="popSubTitle marginSet"></p>
            <table class="table">
                <caption>신청개요</caption>
                <colgroup>
                   <col width="15%" />
		            <col width="35%" />
		            <col width="15%" />
		            <col width="35%" />
                </colgroup>
                <tbody>
	                <tr class="line">
	                    <th>입찰공고번호</th>
		                <td>${myPartcptReqstSttusDetail.ANNC_NO}-${myPartcptReqstSttusDetail.ANNC_NGR}</td>
		                <th>공고일자</th>
		                <td>${comFn:formatDate(myPartcptReqstSttusDetail.ANNC_DT,'yyyyMMddHHmmss','yyyy-MM-dd')}</td>
	                </tr>
	                <tr>
		                <th>공고명</th>
		                <td colspan="3">${myPartcptReqstSttusDetail.BID_NM}</td>
		            </tr>
		            <tr>
		                <th>계약방법</th>
		                <td>${myPartcptReqstSttusDetail.CONT_MTCD_NM}</td>
		                <th>포기일자</th>
		                <td>${comFn:formatDate(today,'yyyyMMdd','yyyy-MM-dd')}</td>
		            </tr>
		            <tr>
	                    <th>포기사유</th>
	                    <td colspan="3"><textarea maxlength="4000" name="P_BID_ABND_RSN"></textarea></td>
	                </tr>
                </tbody>
            </table>
        </div>
        
        <div class="T_btnLayer fr">
            <button type="button" class="blueBtn L" id="saveBtn">저장</button>
            <button type="button" class="blueBtn L" id="closeBtn">닫기</button>
        </div>
    </fieldset>
    </form>
</div>