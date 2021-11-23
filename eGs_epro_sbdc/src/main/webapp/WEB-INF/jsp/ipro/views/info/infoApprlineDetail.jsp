<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 결재선관리 상세
 *
 * <pre>
 * info 
 *    |_ infoApprlineDetail.jsp
 * 
 * </pre>
 * @date : 2017. 06. 16
 * @version : 1.0
 * @author : 은우소프트 홍찬일
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %> 
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %> 

<script type="text/javascript" src="${jsPath}/ipro/views/info/infoApprlineDetail.js"></script> 
 
<div class="content">
	<form id="dataFrm" method="POST">
		<input type="hidden" id="resourceName" name="resourceName" value="${param.resourceName}">
		<input type="hidden" id="resourceDesc" name="resourceDesc" value="${param.resourceDesc}">
		<input type="hidden" id="P_PAGE_NO" name="P_PAGE_NO" value="${comFn:nvl(param.P_PAGE_NO, 1)}">
		<input type="hidden" id="P_PAGE_SIZE" name="P_PAGE_SIZE" value="${comFn:nvl(param.P_PAGE_SIZE, 10)}">
		<input type="hidden" id="P_APPL_NO" name="P_APPL_NO" value="${P_APPL_NM_OBJ[0].APPL_NO}">
		
		<div class="conts_wrap">
			<div class="inner">
				<div class="tit_wrap">
					<h3 class="tit">결재선관리 상세</h3>
					<ul class="step_wrap">
						<li><a href="#">${comFn:fmIso2Euc(myMenuList.bigMenuNm)}</a></li>
						<li><a href="#">${comFn:fmIso2Euc(myMenuSubList.smallMenuNm)}</a></li>
					</ul> <!--// step_wrap E -->
				</div> <!--// tit_wrap E -->
				<div class="view_wrap typeA">
		    		<div class="view_area">
						<table>
							<colgroup>												
								<col width="15%" align="left">
								<col width="*" align="left">
							</colgroup>
							<tr height="24">
								<th class=" txtl">
									<img src="${imagePath}/ipro/main/dot_blue.gif" width="11" height="11">
									결재선
								</th>
								<td>
									${P_APPL_NM_OBJ[0].APPL_NM}
									테스트 결재선1<input type="hidden" value="${P_APPL_NM_OBJ[0].APPL_NM}">
								</td>
							</tr>
						</table>
					</div>
				<br>
				<div class="tit_area">
				    <div class="btn_right">
				    	<button type="button" class="btn btn_s2 btn_c2" id="popupBtn">추가</button>
				    	<button type="button" class="btn btn_s2 btn_c2" id="inPopupBtn">내부결재자 조회</button>
			        </div>			  		
			    </div>
		   			 <div class="view_area">
							<table>
				          		<colgroup>
								<col width="12%" >
								<col width="22%">  
								<col width="*">
								<col width="15%" >
								<col width="15%" >
							</colgroup>
							<thead>
				                <tr>
				                   	<th class="txtc">순번</th>
									<th class="txtc">성명</th>
				                   	<th class="txtc">부서</th>
									<th class="txtc">구분</th>
									<th class="txtc">삭제</th>
				                </tr>
				            </thead>
							<tbody id="userFrame">
							<c:if test="${comFn:nvl(tMmAprpListTotCnt, 0) == 0}">
								<tr>
									<td class="txtc">1</td>
									<td class="txtc">
										사용자1
									</td>					
									<td class="txtc">
										관리부
										</td>
									
									<td class="txtc">
										<select name="P_APPR_AUCD">
											<option value="C" selected >승인</option>
											<option value="A" <c:if test="${data.APPR_AUCD eq 'A'}">selected</c:if> >합의</option>
										</select>
									</td>
									<td class="txtc">
										<button type="button" class="btn btn_02 btn_sch" onclick="chargerTrRemoveWait(this);">삭제</button>
										<input type="hidden" name="P_DEL_YN" value="" >
									</td>
								</tr>
								<tr>
									<td class="txtc">2</td>
									<td class="txtc">
										사용자2
									</td>					
									<td class="txtc">
										법무팀
										</td>
									
									<td class="txtc">
										<select name="P_APPR_AUCD">
											<option value="C" selected >승인</option>
											<option value="A" <c:if test="${data.APPR_AUCD eq 'A'}">selected</c:if> >합의</option>
										</select>
									</td>
									<td class="txtc">
										<button type="button" class="btn btn_02 btn_sch" onclick="chargerTrRemoveWait(this);">삭제</button>
										<input type="hidden" name="P_DEL_YN" value="" >
									</td>
								</tr>
								<!-- <tr>
									<td class="txt-center" colspan="12">검색 조건에 해당하는 데이터가 존재하지 않습니다.</td>
								</tr> -->
							</c:if>
							<c:if test="${tMmAprpListTotCnt > 0}">
								<c:forEach var="data" items="${tMmAprpList}" varStatus="status" >
								<tr>
									<td class="txtc">${status.count}</td>
									<td class="txtc">
										${data.EMPL_NM}
										<input type="hidden" name="P_EMPL_NM" value="${data.EMPL_NM}" >
										<input type="hidden" name="P_EMPL_NO" value="${data.EMPL_NO}" >
										<input type="hidden" name="P_APRP_SN" value="${data.APRP_SN}" >
									</td>					
									<td class="txtc">
										${data.DEPT_NM}
										<input type="hidden" name="P_ORG_NAME" value="${data.DEPT_NM}" >
										</td>
									<td class="txtc">
										${data.OFCPS_NM}
										<input type="hidden" name="P_POSI_NAME" value="${data.OFCPS_NM}" >
										</td>
									<td class="txtc">
										<select name="P_APPR_AUCD">
											<option value="C" <c:if test="${data.APPR_AUCD eq 'C'}">selected</c:if> >승인</option>
											<option value="A" <c:if test="${data.APPR_AUCD eq 'A'}">selected</c:if> >합의</option>
										</select>
									</td>
									<td class="txtc">
										<button type="button" class="btn btn_02 btn_sch" onclick="chargerTrRemoveWait(this);">삭제</button>
										<input type="hidden" name="P_DEL_YN" value="" >
									</td>
								</tr>
								</c:forEach>
							</c:if>
							</tbody>
						</table>
					</div>
					<div class="btn_wrap">
						<button type="button" class="btn btn_02 btn_revise" id="saveBtn">저장</button>
			           	<button type="button" class="btn btn_02 btn_revise" id="delBtn">삭제</button>
			           	<button type="button" class="btn btn_02 btn_sch" id="listBtn">목록</button>
					</div>
				</div>
			</div>
		</div>
	</form>	
</div>

<%-- DETAIL FORM --%>
<form id="listFrm" method="POST" action="">
 	<input type="hidden" name="resourceName" value="${param.resourceName}">
	<input type="hidden" name="resourceDesc" value="${param.resourceDesc}">
</form>
<form id="detailFrm" method="POST" > 
	<input type="hidden" name="resourceName" value="${ param.resourceName }" >
</form>
<form id="popupFrm" method="POST" > 
	<input type="hidden" name="resourceName" value="${ param.resourceName }" >
</form>
<form id="searchFrm" class="search_form" method="POST" action="${contextPath}/info/infoApprlineList.do">
	<input type="hidden" id="resourceName" name="resourceName" value="${param.resourceName}">
	<input type="hidden" id="resourceDesc" name="resourceDesc" value="${param.resourceDesc}">
	<input type="hidden" id="P_PAGE_NO" name="P_PAGE_NO" value="${comFn:nvl(param.P_PAGE_NO, 1)}">
	<input type="hidden" id="P_PAGE_SIZE" name="P_PAGE_SIZE" value="${comFn:nvl(param.P_PAGE_SIZE, 10)}">
</form>	