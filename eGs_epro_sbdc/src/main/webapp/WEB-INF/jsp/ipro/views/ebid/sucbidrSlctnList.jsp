<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 입찰관리 > 낙찰찰자선정 목록
 *
 * <pre>
 * ebid 
 *    |_ sucbidrSlctnList.jsp
 * 
 * </pre>
 * @date : 2017. 06. 20
 * @version : 1.0
 * @author : 은우소프트 전상훈
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %> 
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %> 

<script type="text/javascript" src="${jsPath}/ipro/views/ebid/sucbidrSlctnList.js"></script> 

<div class="contents_wrap">
	<ul class="step_wrap">
		<li><a href="#">${myMenuList.bigMenuNm}</a></li>
		<li><a href="#">${myMenuSubList.smallMenuNm}</a></li>
	</ul>
	<div class="tit_wrap">
		<h3 class="tit">낙찰자선정</h3>
	</div>
		
	<form id="searchFrm" class="search_form" method="POST" action="${contextPath}/elbi/sucbidrSlctnList.do">
		<input type="hidden" id="P_PAGE_NO" name="P_PAGE_NO" value="${comFn:nvl(param.P_PAGE_NO, 1)}">
		<input type="hidden" id="P_PAGE_SIZE" name="P_PAGE_SIZE" value="${comFn:nvl(param.P_PAGE_SIZE, 10)}">
		<input type="hidden" name="resourceName" value="${param.resourceName}">
		
		<fieldset>
			<div class="view_wrap typeA">
				<div class="view_area">
					<table>
						<colgroup>
							<col width="15%" align="left">
							<col width="35%" align="left">
							<col width="15%" align="left">
							<col width="35%" align="left">
						</colgroup>
						<tr>
							<th>공고명</th>
							<td><input type="text" id="P_BID_NM_S" name="P_BID_NM_S" value="${param.P_BID_NM_S }"></td>
							<th>공고번호</th>
							<td><input type="text" id="P_ANNC_NO_S" name="P_ANNC_NO_S" value="${param.P_ANNC_NO_S }" class="w180"></td>
						</tr>
						<tr>
							<th>계약구분</th>
							<td>
								<comTag:comCmcdCdValueComboBox name="P_CONT_SECD_S" cdId="CONT_SECD" selectKey="${param.P_CONT_SECD_S }" headerValue="전체" width="180"/>
							</td>
							<th>낙찰방법</th>
							<td>
								<comTag:comCmcdCdValueComboBox name="P_SBID_MTCD_S" cdId="SBID_MTCD" cond1="BID" selectKey="${param.P_SBID_MTCD_S }" headerValue="전체" width="180"/>
							</td>						
						</tr>
						<tr>
							<th>개찰일자</th>
							<td>
								<div class="calendar_box">
				                    <label for="P_OPNG_STDT_S" class="blind">개찰일자 시작일</label>
				                    <input type="text" class="datepicker1 w120" id="P_OPNG_STDT_S" name="P_OPNG_STDT_S" value="${param.P_OPNG_STDT_S}" date>
				                	<span class="wave"> ~ </span>
				                    <label for="P_OPNG_ENDT_S" class="blind">개찰일자 마감일</label>
				                    <input type="text" class="datepicker2 w120" id="P_OPNG_ENDT_S" name="P_OPNG_ENDT_S" value="${param.P_OPNG_ENDT_S}"  date>
				                </div>
							</td>
							<th>진행상태</th>
							<td>
								<comTag:comCmcdCdValueComboBox name="P_BID_PSCD_S" cdId="BID_PSCD" cond1="SBID" selectKey="${param.P_BID_PSCD_S }" headerValue="전체" width="180"/>
							</td>		
						</tr>
						<tr>
							<th>입찰담당자</th>
							<td>
								<input type="text" class="w180" id="usrNm" name="P_CHRGR_NM_S" value="${param.P_CHRGR_NM_S }" readonly>
								<input type="hidden" id="usrId" name="P_CHRGR_ID_S" value="${param.P_CHRGR_ID_S }">
								<button type="button" class="btn btn_s btn_sch" id="chargerBtn">검색</button>
								<button type="button" class="btn btn_s btn_sch" id="chargerDelBtn">삭제</button>
							</td>						
							<th></th>
							<td></td>
						</tr>
					</table>				
				</div>
			</div>
			
			<div class="btn_wrap mt10">
				<button type="button" class="btn btn_m btn_c2" id="searchBtn">조회</button>
			</div> <!--// btn_wrap E -->
			
		</fieldset>
	</form>
	
	<div class="list_wrap mt30" id="contentWrap">
		<div class="list_top">
			<p class="total">총 <span>${comFn:nvl(sucbidrSlctnListTotcnt, 0)}</span>건</p>		
		</div> <!--// list_top E -->
		<div class="list_conts">		
			<table>
				<caption>낙찰자선정 목록</caption>
              	<colgroup>
             		<col width="8%"/>
                   	<col width="8%"/>
                   	<col width="10%"/>
                   	<col width="*"/>
                   	<col width="10%"/>
                   	<col width="8%"/>
                   	<col width="10%"/>
            		<col width="6%"/>
            	</colgroup>
            	<thead>
			    	<tr>
			    		<th scope="col">진행상태</th>
			            <th scope="col">계약구분</th>
			            <th scope="col">공고번호</th>
			            <th scope="col">공고명</th>
			            <th scope="col">낙찰방법</th>
			            <th scope="col">담당자</th>
			            <th scope="col">개찰일시</th>
			            <th scope="col">열람</th>
			        </tr>
            	</thead>
            	<tbody>
			        <c:if test="${comFn:nvl(sucbidrSlctnListTotcnt, 0) == 0}">
						<tr>
							<td colspan="8" class="txtc">검색 조건에 해당하는 데이터가 존재하지 않습니다.</td>
						</tr>
					</c:if>
					<c:if test="${sucbidrSlctnListTotcnt > 0}">
						<c:forEach var="data" items="${sucbidrSlctnList}" varStatus="status">
							<tr class="row">
								<td>${data.BID_PSCD_NM}</td>
								<td>${data.CONT_SECD_NM}</td>
								<td>${data.ANNC_NO}-${data.ANNC_NGR}</td>
								<td class="pl5 list_tit" title="${data.BID_NM}" onclick="sucbidrSlctnDetail('${data.ANNC_NO}','${data.ANNC_NGR }', '${data.ROUND_NO }');" style="cursor: pointer;">
									<c:if test="${data.EMRG_YN eq 'Y'}"><font color="red">[긴급] </font></c:if>
									<c:if test="${data.ANNC_NGR > 1}"><font color="red">[정정] </font></c:if>
									${comFn:toShorten(data.BID_NM,40)}
								</td>
								<td>${data.SBID_MTCD_NM}</td>
								<td>${data.CHRGR_NM}</td>
								<td>${comFn:formatDate(data.OPNG_DT,'yyyyMMddHHmmss','yyyy-MM-dd HH:mm')}</td>
								<td><button type="button" class="btn btn_s btn_sch" onclick="vendOpenList('${data.ANNC_NO}', '${data.ANNC_NGR }', '${data.ROUND_NO}')">${data.OPEN_CNT }</button></td>
							</tr>
						</c:forEach>
					</c:if>
            	</tbody>
		    </table>
		</div>
		<div class="list_bottom">
			<comTag:pagingIpro totalCount="${sucbidrSlctnListTotcnt}" pageNo="${comFn:nvl(param.P_PAGE_NO, 1)}" pageSize="${comFn:nvl(param.P_PAGE_SIZE, 10)}" clickPage="pageObj.clickPage"/>
		</div> <!--// list_bottom E -->
    </div>		
	
</div>

<%-- DETAIL FORM --%>
<form id="viewFrm" method="POST">
	<input type="hidden" name="P_ANNC_NO">
	<input type="hidden" name="P_ANNC_NGR">
	<input type="hidden" name="P_ROUND_NO">
	<input type="hidden" name="resourceName" value="${param.resourceName}">
</form>

<!-- POPUP FORM -->
<form id="popupFrm" method="POST">
	<input type="hidden" name="resourceName" value="${param.resourceName}">
	<input type="hidden" name="resourceDesc" value="${param.resourceDesc}">
	<input type="hidden" name="P_ANNC_NO">
	<input type="hidden" name="P_ANNC_NGR">
	<input type="hidden" name="P_ROUND_NO">
</form>