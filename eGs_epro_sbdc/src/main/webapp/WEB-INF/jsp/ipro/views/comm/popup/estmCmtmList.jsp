<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">--%>
<%--
 * 공통 > 평가위원 목록 조회(팝업)
 *
 * <pre>
 * comm 
 *    |_ popup
 *       |_ estmCmtmList.jsp
 * 
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %>

<script type="text/javascript" src="${jsPath}/ipro/views/comm/popup/estmCmtmList.js"></script>

<div class="layout-pop">
	<form id="searchFrm" class="search_form" method="POST">
		<input type="hidden" id="setMulti" name="setMulti" value="${param.setMulti}">
		<input type="hidden" id="tbdy_id" name="tbdy_id" value="${param.tbdy_id}">
		<input type="hidden" id="P_INO_CMTM_SECD" name="P_INO_CMTM_SECD" value="${param.P_INO_CMTM_SECD}">
		<input type="hidden" id="P_ESTM_CMTM_CNT" name="P_ESTM_CMTM_CNT" value="${param.P_ESTM_CMTM_CNT}">
		<input type="hidden" id="P_ESTM_SPHE_SECD" name="P_ESTM_SPHE_SECD" value="${param.P_ESTM_SPHE_SECD}">
		
		<c:forEach var="data" items="${estmCmtmSlctList}" varStatus="status">
			<input type="hidden" id="P_ESTM_CMTM_NO_S" name="P_ESTM_CMTM_NO_S" value="${data.ESTM_CMTM_NO }">
		</c:forEach>
						
		
		<div class="pop_header">
			<div class="title">평가위원 목록</div>
		</div> <!--// pop_header E -->
		
		<div class="form-setting-box">
	        <div class="ui-setting">
	        
	        	<!-- Form Setting -->
	            <div class="form-setting">
	                <span class="txt-label">평가위원명</span>
					<input type="text" id="P_ESTM_CMTM_NM_S" name="P_ESTM_CMTM_NM_S" value="${param.P_ESTM_CMTM_NM_S }" class="component-input">
	            </div>
	            <!-- //Form Setting -->
	        </div>
	  </div>
	  
	  <!-- buttons -->
	  <div class="setting-button">
	      <button type="button" class="component-button" id="searchBtn">조회</button>
	  </div>
	  
	  <!-- //buttons -->
	  <div class="area-list">
	      <!-- Option -->
	      <div class="table-option">
	          <!-- Right -->
	          <div class="option-right">
	              <div class="table-num type-fleft">
	                  총 <strong>${comFn:nvl(estmCmtmListTotCnt, 0)}</strong>건
	              </div>
	          </div>
	          <!-- //Right -->
	      </div>
	      <!-- //Option -->

			<table class="component-table">
				<colgroup>
					<col width="8%" />
					<col width="12%" />
					<col width="20%" />
					<col width="18%" />
					<col width="*" />
					<col width="15%" />
				</colgroup>
				<thead>
					<tr>
						<c:choose>
							<c:when test="${param.setMulti eq 'Y'}">
								 <th class="txt-center">선택</th>
							</c:when>
							<c:otherwise>
								 <th class="txt-center">번호</th>
							</c:otherwise>
						</c:choose>
						
						<th class="txt-center">내/외부 구분</th>
						<th class="txt-center">평가위원명</th>
						<th>전화번호</th>
						<th>이메일</th>
						<th class="txt-center">수기등록여부</th>
					</tr>
				</thead>
				<tbody id="cmtmTbdy">
					<c:if test="${comFn:nvl(estmCmtmListTotCnt, 0) == 0}">
						<tr>
							<td colspan="6" class="txt-center">검색 조건에 해당하는 데이터가 존재하지 않습니다.</td>
						</tr>
					</c:if>
					<c:if test="${estmCmtmListTotCnt > 0}">
						<c:forEach var="data" items="${estmCmtmList}" varStatus="status">
							<tr>
								<c:choose>
									<c:when test="${param.setMulti eq 'Y'}">
										 <td class="txt-center">
											<label class="component-checkbox">
												<input type="checkbox" name="estmCbk">
												<i></i>
											</label>
										</td>
									</c:when>
									<c:otherwise>
										 <td class="txt-center">${status.count }</td>
									</c:otherwise>
								</c:choose>
								<td class="txt-center">
									<c:if test="${data.INO_CMTM_SECD eq 'INN' }"><span class="tag-status type-ing">내부</span></c:if>
									<c:if test="${data.INO_CMTM_SECD ne 'INN' }"><span class="tag-status type-change">외부</span></c:if>
								</td>
								<td class="txt-center">
									${data.ESTM_CMTM_NM }
									
									<input type="hidden" name="P_ESTM_CMTM_NO" value="${data.ESTM_CMTM_NO}">
									<%-- <input type="hidden" name="P_ESTM_CMTM_NM" value="${data.ESTM_CMTM_NM}">
									<input type="hidden" name="P_INO_CMTM_SECD" value="${data.INO_CMTM_SECD}">
									<input type="hidden" name="P_INO_CMTM_SECD_NM" value="${data.INO_CMTM_SECD_NM}">
									<input type="hidden" name="P_CP_NO" value="${data.CP_NO}">
									<input type="hidden" name="P_TEL_NO" value="${data.TEL_NO}">
									<input type="hidden" name="P_EMAL" value="${data.EMAL}">
									<input type="hidden" name="P_LLF_SECD" value="${data.LLF_SECD}">
									<input type="hidden" name="P_LLF_SECD_NM" value="${data.LLF_SECD_NM}">
									<input type="hidden" name="P_CNTN_SECD" value="${data.CNTN_SECD}">
									<input type="hidden" name="P_CNTN_SECD_NM" value="${data.CNTN_SECD_NM}">
									<input type="hidden" name="P_MLF_SECD" value="${data.MLF_SECD}">
									<input type="hidden" name="P_MLF_SECD_NM" value="${data.MLF_SECD_NM}">
									<input type="hidden" name="P_SLF_SECD" value="${data.SLF_SECD}">
									<input type="hidden" name="P_SLF_SECD_NM" value="${data.SLF_SECD_NM}"> --%>
								</td>
								<td>${data.TEL_NO }</td>
								<td>${data.EMAL }</td>
								<td class="txt-center">
									<c:if test="${data.HNDW_REG_YN eq 'Y'}">예</c:if>
									<c:if test="${data.HNDW_REG_YN ne 'Y'}">아니오</c:if>
								</td>
							</tr>
						</c:forEach>
					</c:if>
				</tbody>
			</table>
		</div>
		
		<div class="bottom-buttons">
			<c:if test="${param.setMulti eq 'Y'}">
				<a href="javascript:" class="btn-bottom type-b" id="choiceBtn">선택</a>
			</c:if>
			<a href="javascript:" class="btn-bottom type-a" onclick="window.close();">닫기</a>
		</div>
	</form>
</div> 
 
<%--page move form --%>
<form id="detailFrm" method="POST">
	<input type="hidden" id="resourceName" name="resourceName" value="${param.resourceName}">
	<input type="hidden" id="resourceDesc" name="resourceDesc" value="${param.resourceDesc}">
</form>      