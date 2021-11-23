<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 기술평가 > 평가상세항목 내용 조회
 *
 * <pre>
 * ebid 
 *    |_evlFormDtlsCnInqireForm.jsp
 * 
 * </pre>
 * @date : 2015. 11. 19. 오후 11:26:00
 * @version : 1.0
 * @author : 은우소프트 김봉수
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %>

<script type="text/javascript" src="${jsPath}/opro/views/ebid/popup/evlFormDtlsCnInqireForm.js"></script>

<div id="windowPopup">
	<h3 class="windowTitle">평가상세항목 내용 조회</h3>
	<div id="popLayerTaps">
		<%--Data View Area --%>
        <div class="tableLayer">
            <table class="table">
                <caption>평가상세항목 내용 등록</caption>
                <colgroup>
                    <col width="150px">
                    <col width="350px">
                </colgroup>
                <tbody>
	                <tr class="line">
	                    <th>평가항목명</th>
	                    <td>제안사 소개</td>
	                </tr>
	                <tr>
	                    <th>평가상세항목명</th>
	                    <td>일반현황 및 연혁 등</td>
	                </tr>
	                <tr>
	                    <th>평가상세항목 내용</th>
	                    <td>제안사의 일반적인 현황과 최근의 연혁등의 제안사를 소개 할수 있는 내용</td>
	                </tr>
                </tbody>
            </table>
        </div>
		
		<%--Button Area --%>
		<div class="T_btnLayer fr">
            <button type="button" class="blueBtn L" id="closeBtn">닫기</button>
        </div>
	</div>
</div>         