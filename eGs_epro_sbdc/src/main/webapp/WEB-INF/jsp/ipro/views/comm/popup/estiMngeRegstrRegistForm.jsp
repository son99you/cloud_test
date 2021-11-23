

<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 견적의뢰 > 견적의뢰상세 - 선정사유 등록 폼[팝업]
 *
 * <pre>
 *esre 
 *   |_ popup
 *   	     |_ estiMngeRegstrRegistForm.jsp
 * 
 * </pre>
 * @date : 2015. 01. 23. 오전 9:43:32
 * @version : 1.0
 * @author : 은우소프트 손연우
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %>

<link rel="stylesheet" type="text/css" href="${cssPath}/ipro/windowPop.css">
<link rel="stylesheet" type="text/css" href="${cssPath}/ipro/template/pagingPlace.css">
<script type="text/javascript" src="${jsPath}/ipro/views/comm/popup/estiMngeRegstrRegistForm.js"></script>
  
  
<div id="windowPopup">  


<form id="registFrm" method="POST" action="${contextPath}/esre/estmtSctnResnRegist">

<input type="hidden" name="P_ESTMT_NO" value = "${ P_ESTMT_NO }">
<input type="hidden" name="P_ENTRPS_REGIST_NO" value = "${P_ENTRPS_REGIST_NO }">	
<input type="hidden" name="P_USER_ID"  value = "${sessionScope.loginResult.USER_ID }">
<input type="hidden" id="resourceName" name="resourceName" value="${param.resourceName}">
		
		
        <h3 class="windowTitle">견적업체선정</h3>
        <div id="popLayerTaps">
            <div class="tapArea">
            	<p class="popSubTitle marginSet"></p>
            	<div class="tableLayer">
			            <table class="table">
			                <caption>견적업체선정</caption>
			                <colgroup>
			                    <col width="150px">
			                    <col width="200px">
			                    <col width="150px">
			                    <col width="200px">
			                </colgroup>
			                <tbody>
			                <tr>
			                    <th>견적요청번호</th>
			                    <td colspan="3">
			                        ${selectTepnEnesreDetail.PRCURE_REQEST_NO}&nbsp;
			                    </td>
			                </tr>
			                <tr>
			                    <th>의뢰명</th>
			                    <td colspan="3">
			                    	${selectTepnEnesreDetail.ESTMT_REQEST_NM}&nbsp;
			                    </td>
			                </tr>
			                <tr>
			                    <th>견적총금액</th>
			                    <td>
			                        ${comFn:formatMoney(selectTepnEnesreDetail.ESTMT_TOT_AMOUNT)}&nbsp;
			                    </td>
			                    <th>담당자 이메일</th>
			                    <td>
			                    	${selectTepnEnesreDetail.CHARGER_EMAIL_ADRES}&nbsp;
			                    </td>
			                </tr>
			                <tr>
			                    <th>담당자명</th>
			                    <td>
			                        ${selectTepnEnesreDetail.CHARGER_NM}&nbsp;
			                    </td>
			                    <th>담당자 휴대전화</th>
			                    <td>${selectTepnEnesreDetail.CHARGER_MVMN_TELNO}&nbsp;</td>
			                </tr>
			                <tr>
			                	<th>선정사유</th>
			                	<td colspan="3">
			                	<textarea style="width: 100%; height: 70px;" name="P_SLCTN_RESN_CN"></textarea>
			                	</td>
			                </tr>
			              	
		                </tbody>
		            </table>
            		<div class="T_btnLayer fr">
	            		<button type="button" class="blueBtn L" id="registBtn">업체선정</button>
       					<button type="button" class="blueBtn L" id="closeBtn">닫기</button>
       				</div>
		          </div>
	         </div>
        </div>
	</form>
</div>

		