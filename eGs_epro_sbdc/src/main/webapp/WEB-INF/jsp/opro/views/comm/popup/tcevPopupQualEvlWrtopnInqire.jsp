<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%--
 * 입찰관리 > 기술평가 의견서 팝업
 *
 * <pre>
 * ebid
 *    |_popup
 *        |		tcevPopupQualEvlWrtopnInqire.jsp
 * 
 * </pre>
 * @date : 2017. 06. 23
 * @version : 1.0
 * @author : 은우소프트 이주연
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %>

<script type="text/javascript" src="${jsPath}/views/comm/popup/tcevPopupQualEvlWrtopnInqire.js"></script>

<div id="windowPopup" >
	<h3 class="windowTitle">기술평가 의견상세</h3>

	<div id="popLayerTaps">
	    <div class="tapLayers">
	    	<p class="popSubTitle marginSet">상임위원 평가의견서</p>
	         <div class="tapLayer">
	             <div class="tableLayer">
	                 <table class="table no_bul">
	                     <caption>상임위원 평가의견서</caption>
	                     <colgroup>
	                     	<col width="200px">
	                         <col width="90px">
	                         <col width="">
	                     </colgroup>
	                     <thead>
	                         <tr class="line">
	                             <th>평가항목</th>
	                             <th>평가위원</th>
	                             <th>평가의견</th>
	                         </tr>
	                     </thead>
	                     <tbody>
							<tr class="row">
								<td class="left_T">제안사 소개</td>
								<td class="left_T">상임위원1</td>
								<td class="left_T">
								<textarea name="P_EVL_OPINION_CN" readonly="readonly" taView style="display: none;"><c:out value="지문테스트"></c:out></textarea></td>
							</tr>	
							<tr class="row">
								<td class="left_T">전략 및 방법론</td>
								<td class="left_T">상임위원1</td>
								<td class="left_T">
								<textarea name="P_EVL_OPINION_CN" readonly="readonly" taView style="display: none;"><c:out value="지문테스트"></c:out></textarea></td>
							</tr>
							<tr class="row">
								<td class="left_T">기술 및 기능</td>
								<td class="left_T">상임위원1</td>
								<td class="left_T">
								<textarea name="P_EVL_OPINION_CN" readonly="readonly" taView style="display: none;"><c:out value="지문테스트"></c:out></textarea></td>
							</tr>
							<tr class="row">
								<td class="left_T">프로젝트 관리</td>
								<td class="left_T">상임위원1</td>
								<td class="left_T">
								<textarea name="P_EVL_OPINION_CN" readonly="readonly" taView style="display: none;"><c:out value="지문테스트"></c:out></textarea></td>
							</tr>
							<tr class="row">
								<td class="left_T">프로젝트 지원</td>
								<td class="left_T">상임위원1</td>
								<td class="left_T">
								<textarea name="P_EVL_OPINION_CN" readonly="readonly" taView style="display: none;"><c:out value="지문테스트"></c:out></textarea></td>
							</tr>
							<tr class="row">
								<td class="left_T">특수제안</td>
								<td class="left_T">상임위원1</td>
								<td class="left_T">
								<textarea name="P_EVL_OPINION_CN" readonly="readonly" taView style="display: none;"><c:out value="지문테스트"></c:out></textarea></td>
							</tr>
	                     </tbody>
	                 </table>
	             </div>
	         </div>
	
			<p class="popSubTitle marginSet">비상임위원 평가의견서</p>
	         <div class="tapLayer">
	             <div class="tableLayer">
	                 <table class="table no_bul">
	                     <caption>상임위원 평가의견서</caption>
	                     <colgroup>
	                     	<col width="200px">
	                         <col width="90px">
	                         <col width="">
	                     </colgroup>
	                     <thead>
	                         <tr class="line">
	                             <th>평가항목</th>
	                             <th>평가위원</th>
	                             <th>평가의견</th>
	                         </tr>
	                     </thead>
	                     <tbody>
							<tr class="row">
                  				<td colspan="3">평가의견서가 존재하지 않습니다.</td>
							</tr>	
	                     </tbody>
	                 </table>
	             </div>
	         </div>
	         <div class="T_btnLayer fr">
	            <button type="button" class="blueBtn L" id="closeBtn" onclick="window.close();">닫기</button>
	        </div>
	    </div>
	</div>
</div>

