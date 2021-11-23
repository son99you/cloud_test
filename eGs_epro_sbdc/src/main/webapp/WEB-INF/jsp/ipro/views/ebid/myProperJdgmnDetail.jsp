<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 입찰계획 > 나의 적격심사 상세
 *
 * <pre>
 *elbi 
 *    |_ oepElbiMyProperJdgmnDetail.jsp
 * 
 * </pre>
 * @date : 2015. 03. 23. 오후 05:23:55
 * @version : 1.0
 * @author : 은우소프트 손연우
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %>

<script type="text/javascript" src="${jsPath}/opro/views/ebid/myProperJdgmnDetail.js"></script>  

<ul class="step_wrap">
	<li><a href="#">경쟁입찰</a></li>
	<li><a href="#">나의 적격심사</a></li> 
</ul> <!--// step_wrap E -->
<div class="tit_wrap">
	<h3 class="tit">나의 적격심사 상세</h3>
</div> <!--// tit_wrap E -->
  
<div class="view_wrap typeA">
	<div class="tit_area">
		<h4 class="tit">심사대상 물품</h4>
	</div> <!--// tit_area E -->
	<div class="view_area">
		<table class="table">
                <caption>심사대상물품</caption>
                <colgroup>
                    <col width="170px">
                    <col width="320px">
                    <col width="170px">
                    <col width="320px">
                </colgroup>
                <tbody>
                <tr class="line">
                    <th>입찰공고번호</th>
                    <td>${myProperJdgmnDetail.PBLANC_NO}-${myProperJdgmnDetail.PBLANC_ODR}</td>
                    <th>공고일자</th>
                    <td>${comFn:formatDate(myProperJdgmnDetail.PBLANC_DT,'yyyyMMddHHmmss','yyyy-MM-dd HH:mm:ss')}&nbsp;</td>
                </tr>
                <tr>
                    <th>입찰명</th>
                    <td colspan="3">${myProperJdgmnDetail.BID_NM}&nbsp;</td>
                </tr>
                <tr>
                    <th>입찰금액(원)</th>
                    <td colspan="3">￦&nbsp;${comFn:formatMoney(myProperJdgmnDetail.BDDPR_AMOUNT)}&nbsp;</td>
                    
                </tr>
                
                </tbody>
            </table>
	</div> <!--// view_area E -->
	<div class="tit_area">
		<h4 class="tit">심사대상입찰자</h4>
	</div> <!--// tit_area E -->
	<div class="view_area">
		<table class="table">
                <caption>심사대상입찰자</caption>
                <colgroup>
                    <col width="170px">
                    <col width="320px">
                    <col width="170px">
                    <col width="320px">

                </colgroup>
                <tbody>
                <tr>
                    <th>업체명</th>
                    <td>${myProperJdgmnDetail.ENTRPS_NM}&nbsp;</td>
                    <th>대표자</th>
                    <td>${myProperJdgmnDetail.RPRSNTV_NM}&nbsp;</td>
                </tr>
                <tr>
                	<th>주사업내용</th>
                	<td colspan="3"> ${MAIN_BSNS }&nbsp;</td>
                </tr>
                </tbody>
            </table>
	</div> <!--// view_area E -->
	<div class="tit_area">
		<h4 class="tit">회사를 대표하는 연락책임자 인적사항</h4>
	</div> <!--// tit_area E -->
	<div class="view_area">
		<table class="table">
                <caption>회사를 대표하는 연락책임자 인적사항</caption>
                <colgroup>
                    <col width="170px">
                    <col width="320px">
                    <col width="170px">
                    <col width="320px">
                </colgroup>
                <tbody>
                <tr>
                  	<th>성명</th>
                  	<td>${myProperJdgmnDetail.ENTRPS_CHARGER_NM}&nbsp;</td>
                  	<th>직위</th>
                  	<td>${myProperJdgmnDetail.RSPOFC_NM}&nbsp;</td>
                </tr>
                <tr>
					<th>담당부서</th>
                  	<td>${myProperJdgmnDetail.DEPT_NM}&nbsp;</td>
                  	<th>휴대전화</th>
                  	<td>${myProperJdgmnDetail.MVMN_TELNO}&nbsp;</td>                
               	</tr>
                <tr>
                  	<th>전화번호</th>
                  	<td>${myProperJdgmnDetail.TELNO}&nbsp;</td>
                  	<th>팩스번호</th>
                  	<td>${myProperJdgmnDetail.FAX_TELNO}&nbsp;</td>
                </tr>
                <tr></tr>
                </tbody>
            </table>
	</div> <!--// view_area E -->
	<div class="tit_area">
		<h4 class="tit">종합평가</h4>
	</div> <!--// tit_area E -->
	<div class="view_area">
		<form id="registFrm" method="POST" action="${contextPath}/elbi/properJdgmnEvlRegist.do">
		<input type="hidden" name="P_PBLANC_NO" value="${ P_PBLANC_NO }" />
		<input type="hidden" name="P_PBLANC_ODR"  value="${ P_PBLANC_ODR }" />
		<input type="hidden" name="P_ENTRPS_REGIST_NO" value="${sessionScope.loginResult.USER_ID }"/>
		<input type="hidden" name="resourceName" value="${param.resourceName}">
		<input type="hidden" name="P_PRCURE_SE_CD" value="${myProperJdgmnDetail.PRCURE_SE_CD }"/>
		<input type="hidden" name="P_PRCURE_DETAIL_SE_CD" value="${myProperJdgmnDetail.PRCURE_DETAIL_SE_CD }"/>
		<table class="tableList">
            <caption>종합평가</caption>
            <colgroup>
                <col width="40px"/>
                <col width="160px"/>
                <col width="140px"/>
                <col width="140px"/>
                <col width="140px"/>
            </colgroup>			
			<thead>
                <tr>
                    <th class="txtc">번호</th>
                    <th class="txtc">구분</th>
                    <th class="txtc">배점한도</th>
                    <th class="txtc">자기평점(점)</th>
                    <th class="txtc">심사평점(점)</th>
                </tr>
            </thead>
			<tbody id = "entrpsJdgmnEvlListJson">
					<c:forEach var="data" items="${entrpsJdgmnEvlList}" varStatus="status" >
					<tr class="row">
						<td>${data.RNUM}
								<c:if test="${ status.last }">
									<input type="hidden" name="entrpsJdgmnEvlListLength" value="${status.index }"/>
								</c:if></td>
						<td>${data.EVL_CN}
								<input type="hidden" name="P_EVL_SN" value="${ data.EVL_SN }"/></td>
						<td class="LMT">${data.ALLOT_LMT_CN}
								<input type="hidden" id="allotLmtScore[${status.index }]" value="${data.ALLOT_LMT_CN}"/></td>
						<td>	
							  	<c:if test="${ data.EVL_CN eq '입찰가격'}">
									<input type="text" readonly="readonly" class="disabled" style="text-align: center;" id="entrpsEvlScore${status.index }" name="P_ENTRPS_EVL_SCORE_BID_PC" onchange="entrpsEvlSm();" value="${data.ENTRPS_EVL_SCORE}"/>
								</c:if>																																
							  	<c:if test="${ data.EVL_CN ne '입찰가격' }">
							  		<label for="entrpsEvlScore_${status.index }" class="blind">${data.EVL_CN}의 자기평점 입력</label>
							  		<input type="text" style="text-align: center;" id="entrpsEvlScore_${status.index }" name="P_ENTRPS_EVL_SCORE" onchange="entrpsEvlSm(this,'${data.ALLOT_LMT_CN}');" onkeyup="entrpsEvlSm(this,'${data.ALLOT_LMT_CN}');" value="${data.ENTRPS_EVL_SCORE}" jdgmn>
							  	</c:if>
						</td>
						<td>	<c:if test= "${ myProperJdgmnDetail.ENTRPS_PRST_CD eq 'OP07' }">
								${data.KOICA_EVL_SCORE }
								<input type="hidden" style="text-align: center;" id="koicaEvlScore[${status.index }]" name="P_KOICA_EVL_SCORE" onchange="entrpsEvlSm(this,'${data.ALLOT_LMT_CN}');" onkeyup="entrpsEvlSm(this,'${data.ALLOT_LMT_CN}');" value="${data.KOICA_EVL_SCORE }">
								</c:if>
								<c:if test= "${ myProperJdgmnDetail.ENTRPS_PRST_CD ne 'OP07' }">
								&nbsp;
								</c:if>
						</td>
					</tr>
					</c:forEach>
					<tr class="row">
						<td colspan="2">계</td>
						<td>
								<label for="allotlmtTot" class="blind">배점한도 총점</label>
								<input type="text" style="border:0px; text-align: center;" readonly="readonly" class="disabled" id="allotlmtTot" name="allotlmtTot" value="100"/></td>
						<td>
								<label for="entrpsEvlTot" class="blind">자기평점 총점</label>
								<input type="text" style="border:0px; text-align: center;" readonly="readonly" class="disabled" id="entrpsEvlTot" name="entrpsEvlTot"/></td>
						<td>
								<label for="koicaEvlTot" class="blind">심사평점 총점</label>
								<input type="text" style="border:0px; text-align: center;" readonly="readonly" class="disabled" id="koicaEvlTot" name="koicaEvlTot"
								<c:if test="${ myProperJdgmnDetail.ENTRPS_PRST_CD eq 'OP07' }"> value="${ koicaEvlTot }" </c:if> /></td>
								
					</tr>
			</tbody> 
		</table>
		</form> 
	</div> <!--// view_area E -->

        	
	<div class="btn_wrap view_btn">
		<c:if test= "${ myProperJdgmnDetail.ENTRPS_PRST_CD ne 'OP07'}">
        	<button type="button" class="btn btn_m btn_orange" id="registBtn">저장</button>
            <button type="button" class="btn btn_m btn_del" id="scoreDspthBtn">자가점수통보</button>
       </c:if>
            <button type="button" class="btn btn_m btn_del" id="listBtn">목록</button>
	</div> <!--// btn_wrap E -->
</div>     
   	<form id="listFrm" method="POST" >
   		<input type="hidden" id="resourceName" name="resourceName" value="${param.resourceName}">

	</form>
	
	
	<form id="detailFrm" method="POST" >
		<input type="hidden" name="P_PBLANC_NO" value="${bidInfoDetail.PBLANC_NO}">
		<input type="hidden" name="P_PBLANC_ODR" value="${bidInfoDetail.PBLANC_ODR}">
		<input type="hidden" id="resourceName" name="resourceName" value="${param.resourceName}">
	</form>
	
   	<form id="popupFrm" method="POST" >
		<input type="hidden" name="P_PBLANC_NO" value="${bidInfoDetail.PBLANC_NO}"/>
		<input type="hidden" name="P_PBLANC_ODR" value="${bidInfoDetail.PBLANC_ODR}"/>
		<input type="hidden" name="P_COMPNO_PRDPRC_NO" />
		<input type="hidden" name="P_ENTRPS_REGIST_NO"/>
		<input type="hidden" id="resourceName" name="resourceName" value="${param.resourceName}">
		
		
	</form>
	<form id="sttusFrm" method="POST">
	
		<input type="hidden" name="P_PBLANC_NO" value="${bidInfoDetail.PBLANC_NO}"/>
		<input type="hidden" name="P_PBLANC_ODR" value="${bidInfoDetail.PBLANC_ODR}"/>
		<input type="hidden" name="P_SCSBID_MTH_CD" value="${bidInfoDetail.SCSBID_MTH_CD }"/>
		<input type="hidden" name="P_BID_LMT_AMOUNT" value="${bidInfoDetail.BID_LMT_AMOUNT }"/>
		<input type="hidden" name="P_PRCURE_SE_CD" value="${bidInfoDetail.PRCURE_SE_CD }"/>
		<input type="hidden" name="P_EVL_CN_TY"/>
		<input type="hidden" name="P_ENTRPS_REGIST_NO"/>
		<input type="hidden" name="P_ENTRPS_PRST_CD" />
		<input type="hidden" id="resourceName" name="resourceName" value="${param.resourceName}">
	
	</form>


