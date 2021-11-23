<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">--%>
<%--
 * 공통 >예산팝업 조회(팝업)
 *
 * <pre>
 * comm 
 *  |_popup
 *   |_budgetList.jsp
 * 
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %>

<script type="text/javascript" src="${jsPath}/opro/views/comm/popup/budgetList.js"></script>
<div class="pop_sp_sec"> 
	<h3 class="sp_tit">예산목록</h3>
	<div class="sp_cont">
		<form id="searchFrm" class="search_form" method="POST" >
			<input type="hidden" id="P_PAGE_NO" name="P_PAGE_NO" value="${comFn:nvl(param.P_PAGE_NO, 1)}">
			<input type="hidden" id="P_PAGE_SIZE" name="P_PAGE_SIZE" value="${comFn:nvl(param.P_PAGE_SIZE, 10)}">
			<input type="hidden" id="setMulti" name="setMulti" value="${param.setMulti}">
		
		
			<div class="sch_box filter_sch">
				<dl class="first">
					<dt>예산년도</dt>
					<dd><input type="text" id="P_BDG_YR_S" name="P_BDG_YR_S" value="${comFn:nvl(param.P_BDG_YR_S,P_BDG_YR_S)}"  class="input w100p"  placeholder="예산년도" /></dd>
					<dt>사업명</dt>
					<dd><input type="text" id="P_BSNS_NM" name="P_BSNS_NM" value="${param.P_BSNS_NM }" class="input w100p"  placeholder="사업명" /></dd>
					<dt>계정명</dt>
					<dd><input type="text" id="P_ACNT_NM" name="P_ACNT_NM" value="${param.P_ACNT_NM }" class="input w100p"  placeholder="계정명" /></dd>
				</dl>
				<dl>
					<dt>부서명</dt>
					<dd><input type="text" id="P_DEPT_NM_S" name="P_DEPT_NM_S" value="${comFn:nvl(param.P_DEPT_NM_S,P_DEPT_NM_S)}"  class="input w100p"  placeholder="부서명" /></dd>
				</dl>
			</div>
			<button type="button" class="btn ty03 btn_sch" id="searchBtn">조회</button>
		</form>
		<!-- Data Total Count -->
		<div class="list_top">
			<p class="total">총 <span>${comFn:nvl(budgetInqireListTotcnt, 0)}</span>건</p>
        </div>
		    <!-- Data List -->
		<table class="list_tb">
            <caption>예산목록</caption>
            <colgroup>
                <col width="5%">
                <col width="10%">
                <col width="*">
                <col width="*">
                <col width="10%">
                <col width="10%">
                <col width="10%">
                <col width="10%">
                <col width="10%">
            </colgroup>			
			<thead>
                <tr>
                	<c:choose>
	                	<c:when test="${param.setMulti eq 'Y'}">
							 <th class="noBg">
							 	<label for="budgetAllCbx" class="blind">체크박스</label>
		                    	<input type="checkbox" id="budgetAllCbx" name="budgetCbx" onclick="FwkCmmnUtil.setAllCheck('budgetAllCbx','budgetCbx');">
		                    </th>
						</c:when>
						<c:otherwise>
							 <th class="noBg">번호</th>
						</c:otherwise>
					</c:choose>
                    <th>부서명</th>
                    <th>사업명</th>
                    <th>계정명</th>
                    <th>결의서잔액</th>
                    <th>당초예산</th>
                    <th>지출결의</th>
                    <th>사용예산</th>
                    <th>연월</th>
                </tr>
            </thead>
			<tbody>
				<c:if test="${comFn:nvl(budgetInqireListTotcnt, 0) == 0}">
					<tr>
						<td colspan="9" style="text-align: center;">검색 조건에 해당하는 데이터가 존재하지 않습니다.</td>
					</tr>
				</c:if>
				<c:if test="${budgetInqireListTotcnt > 0}">
					<c:forEach var="data" items="${budgetInqireList}" varStatus="status" >
						<tr class="row" <c:if test="${param.setMulti ne 'Y'}">onclick="setbudgetInfo('${data.DEPT_NM}','${data.DEPT_NO }','${data.BSNS_CD}','${data.BSNS_NM }','${data.ACNT_CD}','${data.ACNT_NM }','${data.ACNT_ITEM_CD }','${data.RQST_BLNC_AMT}', '${data.BDG_AMT}', '${data.EXPS_RQST_AMT}', '${data.USE_BDG_AMT}', '${data.BDG_YR}', '${data.BDG_MM}');"</c:if> style="cursor: pointer;">
							<c:if test="${param.setMulti eq 'Y'}">
								<td class="txtc">
									<label for="budgetCbx${status.count }" class="blind">체크박스</label>
									<input type="checkbox" id="budgetCbx${status.count }" name="budgetCbx">
								</td> 
							</c:if>
							<c:if test="${param.setMulti ne 'Y'}"> 
								<td class="no">${data.RNUM}&nbsp;</td>
							</c:if>
							<td>
								${data.DEPT_NM}&nbsp;
								<input type="hidden" name="P_DEPT_NM" value="${data.DEPT_NM}">
								<input type="hidden" name="P_DEPT_NO" value="${data.DEPT_NO}">
							</td>
							<td>
								${data.BSNS_NM}&nbsp;
								<input type="hidden" name="P_BSNS_CD" value="${data.BSNS_CD}"> 
								<input type="hidden" name="P_BSNS_NM" value="${data.BSNS_NM}"> 
							</td>
							<td class="txtl pl5">
								${data.ACNT_NM}&nbsp; 
								<input type="hidden" name="P_ACNT_CD" value="${data.ACNT_CD}"> 
								<input type="hidden" name="P_ACNT_NM" value="${data.ACNT_NM}"> 
								<input type="hidden" name="P_ACNT_ITEM_CD" value="${data.ACNT_ITEM_CD}"> 
							</td>
							<td  class="txtr pr5">
								${comFn:formatMoney(data.RQST_BLNC_AMT)}&nbsp;
								<input type="hidden" name="P_RQST_BLNC_AMT" value="${data.RQST_BLNC_AMT}">
							</td>
							<td  class="txtr pr5">
								${comFn:formatMoney(data.BDG_AMT)}&nbsp;
								<input type="hidden" name="P_BDG_AMT" value="${data.BDG_AMT}">
							</td>
							<td  class="txtr pr5">
								${comFn:formatMoney(data.EXPS_RQST_AMT)}&nbsp;
								<input type="hidden" name="P_EXPS_RQST_AMT" value="${data.EXPS_RQST_AMT}">
							</td>
							<td  class="txtr pr5">
								${comFn:formatMoney(data.USE_BDG_AMT)}&nbsp;
								<input type="hidden" name="P_USE_BDG_AMT" value="${data.USE_BDG_AMT}">
							</td>
							<td >
								${data.BDG_YR} / ${data.BDG_MM }&nbsp;
								<input type="hidden" name="P_BDG_YR" value="${data.BDG_YR}">
								<input type="hidden" name="P_BDG_MM" value="${data.BDG_MM}">
							</td>
						</tr>
					</c:forEach>
				</c:if>
			</tbody> 
		</table>
			
		<!-- Data Paging -->
		<div class="paging">
			<comTag:pagingIpro totalCount="${comFn:nvl(budgetInqireListTotcnt,0)}" pageNo="${comFn:nvl(param.P_PAGE_NO, 1)}" pageSize="${comFn:nvl(param.P_PAGE_SIZE, 10)}" clickPage="pageObj.clickPage"/>
		</div>
		
		<div class="btm_btns">
			<c:if test="${param.setMulti eq 'Y'}"> 
       			<button type="button" class="btn ty02"   id="choiceBtn">선택</button>
       		</c:if>
			<button type="button" class="btn ty04" id="closeBtn" onclick="window.close();">닫기</button>
		</div>
	</div> <!--// pop_container E -->
</div> <!--// pop_wrap E -->