<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 예정가격 > 예가등록 목록
 *
 * <pre>
 * ebid 
 *    |_ prdprcManageList.jsp
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

<script type="text/javascript" src="${jsPath}/ipro/views/ebid/prdprcManageList.js"></script> 

<div class="contents_wrap">
	<ul class="step_wrap">
		<li><a href="#">${myMenuList.bigMenuNm}</a></li>
		<li><a href="#">${myMenuSubList.smallMenuNm}</a></li>
	</ul>
	<div class="tit_wrap">
		<h3 class="tit">예가등록</h3>
	</div>

	<form id="searchFrm" method="post">
		<input type="hidden" name="resourceName" value="${param.resourceName}">
		<input type="hidden" id="P_PAGE_NO" name="P_PAGE_NO" value="${comFn:nvl(param.P_PAGE_NO, 1)}">
		<input type="hidden" id="P_PAGE_SIZE" name="P_PAGE_SIZE" value="${comFn:nvl(param.P_PAGE_SIZE, 10)}">
		<input type="hidden" id="P_FIRST_S" value="${param.P_FIRST_S}">
		
		<fieldset>
			<div class="view_wrap typeA">
				<div class="view_area">
					<table>
						<colgroup>
							<col width="15%">
							<col width="35%">
							<col width="15%">
							<col width="35%">
						</colgroup>
						<tr>
							<th>공고명</th>
							<td>
								<input type="text" id="P_BID_NM_S" name="P_BID_NM_S" value="${param.P_BID_NM_S }">
							</td>
							<th>공고번호</th>
							<td>
								<input type="text" id="P_ANNC_NO_S" name="P_ANNC_NO_S" value="${param.P_ANNC_NO_S }" class="w180">
							</td>
						</tr>
						<tr>
							<th>계약구분</th>
							<td>
								<comTag:comCmcdCdValueComboBox name="P_CONT_SECD_S" cdId="CONT_SECD" cond1="BID" selectKey="${param.P_CONT_SECD_S }" headerValue="전체" width="180"/>
							</td>
							<th>낙찰방법</th>
							<td>
								<comTag:comCmcdCdValueComboBox name="P_SBID_MTCD_S" cdId="SBID_MTCD" cond1="BID" selectKey="${param.P_SBID_MTCD_S }" headerValue="전체" width="180"/>
							</td>
						</tr>
						<tr>
							<th>예가확정자</th>
							<td>
								<input type="text" class="w180" id="usrNm" name="P_ESTC_CHRGR_NM_S" value="${param.P_ESTC_CHRGR_NM_S }" readonly>
								<input type="hidden" id="usrId" name="P_ESTC_CHRGR_ID_S" value="${param.P_ESTC_CHRGR_ID_S }">
								<button type="button" class="btn btn_s btn_sch" id="chargerBtn">검색</button>
								<button type="button" class="btn btn_s btn_sch" id="chargerDelBtn">삭제</button>
							</td>
							<th>예가등록상태</th>
							<td>
								<comTag:comCmcdCdValueComboBox name="P_ESTC_PSCD_S" cdId="ESTC_PSCD" cond1="2" selectKey="${param.P_ESTC_PSCD_S }" headerValue="전체" width="180"/>
							</td>	
						</tr>
						<tr>
							<th>계약담당자</th>
							<td>
		                    	<label for="userNmclient" class="blind">담당자명</label>
		                    	<input type="text" class="w180" id="usrChrgrNm" name="P_CHRGR_NM_S" value="${param.P_CHRGR_NM_S}" class="disabled w180" readonly="readonly">
		                    	<input type="hidden" id="usrChrgrId" name="P_CHRGR_ID_S" value="${param.P_CHRGR_ID_S}">
		                    	<button type="button" class="btn btn_s btn_sch" id="searchUserBtn">검색</button>
		                    	<button type="button" class="btn btn_s btn_sch" id="searchDelBtn">삭제</button>
							</td>
							<th></th>
							<td>
							</td>	
						</tr>
					</table>				
				</div>
			</div>
			
			<div class="btn_wrap mt10">
				<button type="button" class="btn btn_m btn_c2" id="searchBtn">조회</button>
			</div> <!--// btn_wrap E -->
			
			<div class="list_wrap mt30" id="contentWrap">
				<div class="list_top">
					<p class="total">총 <span>${comFn:nvl(prdprcManageListTotcnt,0)}</span>건</p>		
				</div> <!--// list_top E -->
				<div class="list_conts" style="overflow: auto;">
					<table>
		           		<colgroup>
							<col width="4%">
							<col width="6%">
							<col width="6%">
							<col width="6%">
							<col width="10%">
							<col width="*">
							<col width="8%">
							<col width="6%">
							<col width="6%">
							<col width="8%">
							<col width="8%">
							<col width="6%">
						</colgroup>
		            	<thead>
					    	<tr>
					            <th>No</th>
					            <th>진행상태</th>
					            <th>입찰구분</th>
					            <th>계약구분</th>
					            <th>공고번호</th>
					            <th>공고명</th>
					            <th>낙찰방법</th>
					            <th>예가확정자</th>
					            <th>계약담당자</th>
					            <th>제출시작일자</th>
					            <th>제출종료일자</th>
					            <th>예가등록상태</th>
					        </tr>
		            	</thead>
		            	<tbody>
		            	<c:if test="${comFn:nvl(prdprcManageListTotcnt,0) > 0}">
			            	<c:forEach var="data" items="${prdprcManageList}" varStatus="status">
				            	<tr class="row" onclick="prdprcManageDetailInqire('${data.ANNC_NO}', '${data.ANNC_NGR}','${data.ROUND_NO}');" style="cursor: pointer;">
				            		<td>${data.RNUM}</td>
				            		<td>${data.BID_PSCD_NM}</td>
				            		<td>${data.BID_MTCD_NM}</td>
				            		<td>${data.CONT_SECD_NM}</td>
				            		<td>${data.ANNC_NO}-${data.ANNC_NGR}</td>
				            		<td class="pl5 list_tit pointer" title="${data.BID_NM}">
										<c:if test="${data.EMRG_YN eq 'Y'}"><font color="red">[긴급] </font></c:if>
										<c:if test="${data.ANNC_NGR > '1'}"><font color="red">[정정] </font></c:if>
										${comFn:toShorten(data.BID_NM,42)}
									</td>
				            		<td>${data.SBID_MTCD_NM}</td>
				            		<td>${data.ESTC_CHRGR_NM}</td>
				            		<td>${data.CHRGR_NM}</td>
									<td>${comFn:formatDate(data.BIDC_SBMT_STDT,'yyyyMMddHHmmss','yyyy-MM-dd')}</td>
									<td>${comFn:formatDate(data.BIDC_SBMT_ENDT,'yyyyMMddHHmmss','yyyy-MM-dd')}</td>
				            		<td>${data.ESTC_PSCD_NM}</td>
				            	</tr>
			            	</c:forEach>
		            	</c:if>
		            	<c:if test="${comFn:nvl(prdprcManageListTotcnt,0) == 0}">
		            		<tr class="row">
		            			<td colspan="10">등록된 데이터가 없습니다.</td>
		            		</tr>
		            	</c:if>
		            	</tbody>
				    </table>
				</div>
				<div class="list_bottom">
					<comTag:pagingIpro totalCount="${comFn:nvl(prdprcManageListTotcnt,0)}" pageNo="${comFn:nvl(param.P_PAGE_NO, 1)}" pageSize="${comFn:nvl(param.P_PAGE_SIZE, 10)}" clickPage="pageObj.clickPage"/>
				</div> <!--// list_bottom E -->
		    </div>				
			
		</fieldset>
	</form>
</div>

<%-- DETAIL FORM --%>
<form id="viewFrm" method="POST" action="">
	<input type="hidden" name="resourceName" value="${param.resourceName}">
	<input type="hidden" name="P_ANNC_NO" >
	<input type="hidden" name="P_ANNC_NGR" >
	<input type="hidden" name="P_ROUND_NO">
</form>

<form id="popupFrm" method="POST" action="">
	<input type="hidden" name="resourceName" value="${param.resourceName}">
	<input type="hidden" name="resourceDesc" value="${param.resourceDesc}">
	<input type="hidden" name="P_USER_NM_S" value="">
	<input type="hidden" name="setMulti" value="">
	<input type="hidden" name="estcChangerYn" value="">
</form>