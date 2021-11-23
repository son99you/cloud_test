<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 기술평가 > 평가등급 부여기준 조회
 *
 * <pre>
 * ebid 
 *    |_evlGradAlwncStdrInqire.jsp
 * 
 * </pre>
 * @date : 2015. 09. 15. 오전 9:21:00
 * @version : 1.0
 * @author : 은우소프트 김봉수
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %>
<script type="text/javascript" src="${jsPath}/opro/views/ebid/popup/evlGradAlwncStdrInqire.js"></script>

<div id="windowPopup">
	<h3 class="windowTitle">평가등급 부여기준 조회</h3>

	<div id="popLayerTaps">
		<p class="popSubTitle marginSet">평가등급 부여기준 정보</p>
        <div class="tableLayer">
            <table class="table no_bul">
                <caption>평가표 정보</caption>
                <colgroup>
                    <col width="150px">
                    <col width="550px">
                </colgroup>
                <thead>
	                <tr class="line">
	                    <th class="noBg">구분</th>
	                    <th>평가항목</th>
	                </tr>
	            </thead>
                <tbody>
					<tr>
						<td>매우우수</td>
						<td class="left_T"> o 제안내용이 우수하여 보완이 전혀 필요 없거나, 표현상 또는 편집상의 문제 등 보완필요사항이 극히 미미하여 제안내용에 별다른 영향을 미치지 않는 경우
<br />o 실적, 경험, 인력구성, 기술력 등에 있어서 해당분야의 국내 최고 수준임이 확인된 경우</td>
					</tr>
					<tr>
						<td>우수</td>
						<td class="left_T"> o 사업시행조건(TOR) 또는 제안요청서(RFP)상의 요구사항을 충족하고, 제안내용이 양호하여 동 과업의 원활한 수행은 가능하나, 사업효과 제고를 위해 일부 수정/보완이 필요한 경우
<br />o 실적, 경험, 인력구성, 기술력 등에 있어서 해당분야별 요구수준 이상 또는 원활한 사업수행을 위해 필요한 수준이상인 경우</td>
					</tr>
					<tr>
						<td>적합(보통)</td>
						<td class="left_T"> o 사업시행조건(TOR) 또는 제안요청서(RFP)상의 요구사항은 대체로 충족시켰으나 제안내용상 부문별 사업추진전략 및 계획의 타당성 및 충실도가 다소 불충분한 경우
<br />o 실적, 경험, 인력구성, 기술력 등에 있어서 최소 요구수준을 충족하였으나, 일부 불충분한 경우</td>
					</tr>
					<tr>
						<td>미흡</td>
						<td class="left_T"> o 사업시행조건(TOR) 또는 제안요청서(RFP)상의 요구사항을 일부 충족하지 못하고, 제안내용의 부문별 사업추진전략 및 계획의 타당성 및 충실도가 부족하고, 현실성이 없는 경우
<br />o 실적, 경험, 인력구성, 기술력 등에 있어서 일부 최소 요구수준을 충족하지 못하였거나, 상당부분 불충분한 경우</td>
					</tr>
					<tr>
						<td>매우미흡</td>
						<td class="left_T"> o 사업시행조건(TOR) 또는 제안요청서(RFP)상의 요구사항을 상당부분 충족하지 못한 경우
<br />o 제안내용의 실현 가능성이 없어 시행이 불가능하다고 확실시 되는 경우
<br />o 실적, 경험, 인력구성, 기술력 등이 최소 요구수준에 미달하여, 교체 등의 조치가 꼭 필요한 경우</td>
					</tr>
                </tbody>
            </table>
        </div>
	
		<div class="T_btnLayer fr">
            <button type="button" class="blueBtn L" id="closeBtn">닫기</button>
        </div>
	
	</div>
</div>         