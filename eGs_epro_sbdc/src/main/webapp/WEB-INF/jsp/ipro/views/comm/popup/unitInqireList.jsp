<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">--%>
<%--
 * 공통 > 요구부서 조회(팝업)
 *
 * <pre>
 * comm 
 *  |_popup
 *   |_bsnsList.jsp
 * 
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %>

<script type="text/javascript" src="${jsPath}/ipro/views/comm/popup/unitInqireList.js"></script> 
 
<div id="windowPopup" class="pop_wrap">
	<div class="pop_header">
		<h1 class="tit">단위 목록</h1>
	</div> <!--// pop_header E --> 
	<!-- <h3 class="windowTitle">단위 목록</h3> -->
	<div class="pop_container">
		<form id="searchFrm" class="search_form" method="POST" >
		
		&nbsp;
		<input type="hidden" id="P_PAGE_NO" name="P_PAGE_NO" value="${comFn:nvl(param.P_PAGE_NO, 1)}">
		<input type="hidden" id="P_PAGE_SIZE" name="P_PAGE_SIZE" value="${comFn:nvl(param.P_PAGE_SIZE, 10)}">
		<input type="hidden" id="btnId" name="searchGbnId" value="${param.searchGbnId}">
		<input type="hidden" id="gbnDept" name="searchGbnDept" value="${param.searchGbnDept }">
		<input type="hidden" id="setMulti" name="setMulti" value="${param.setMulti}">
		<div class="view_wrap typeC">
				<div class="view_area m0 typeB">
					<table>
						<colgroup>
							<col width="15%" align="left">
							<col width="35%" align="left">
							<col width="15%" align="left">
							<col width="35%" align="left">
						</colgroup>
						<tr height="29px">
							<td>
								단위명
							</td>
							<td colspan="3">
								<input type="text" class="lineTxt" id="P_USER_NM_S" name="P_USER_NM_S" style="width: 160px;" value="${param.P_USER_NM_S}" >
							</td>
							<%-- <td>
								
								부서명
							</td>
							<td>
								<c:if test="${ empty param.searchGbnDept  }">
									<input type="text" class="lineTxt" id="P_ORG_NM_S" name="P_ORG_NM_S" style="width: 160px;" value="${param.P_ORG_NM_S}" >											
								</c:if>
								<c:if test="${ not empty param.searchGbnDept  }">
									<input type="text" class="lineTxt disabled" readonly="readonly" id="P_ORG_NM_S" name="P_ORG_NM_S" style="width: 160px;" value="${param.searchGbnDept}" >											
								</c:if>
							</td> --%>
						</tr>											
					</table>
				</div> <!--// view_area E -->
			</div> <!--// view_wrap E -->
			 
			<%-- <table class="contable2">
				<tr> 
					<td>
						<table class="contable">
							<tr>
								<td>
									<table>
										<colgroup>
											<col width="15%" align="left">
											<col width="35%" align="left">
											<col width="15%" align="left">
											<col width="35%" align="left">
										</colgroup>
										<tr height="29px">
											<td>
												
												단위명
											</td>
											
												<input type="text" name="P_CD_VALUE_NM_S" id="P_CD_VALUE_NM_S" style="width: 90%;" value="${param.P_CD_VALUE_NM_S}" maxlength="600">
											</td>
										</tr>
										<tr>
											<td class=" searchBtnTd" colspan="4">
												<button type="button" class="grayBtn ico" id="searchBtn">
								                    <img src="${imagePath}/ipro/icon/ico_search.png" alt="조회 버튼"> 조회
								                </button>
											</td>
										</tr>
									</table>
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table> --%>
		</form>
        <div class="btn_wrap mt10">
			<button type="button" class="btn_p btn_p1 btn_lookup" id="searchBtn">
				<img src="${imagePath}/ipro/icon/btn_icon02.png" alt="">조회
			</button>
		</div> <!--// btn_wrap E -->   
		<!-- Data Total Count -->
		<br />
		
	<div class="pop_list_wrap">
	   <div class="pop_list_top" >
	       <p class="pop_total">총 <span>${comFn:nvl(unitInqireListTotcnt, 0)}</span>건</p>
	   </div>
	       
	    <!-- Data List -->
		<div class="pop_list_conts">
			<table>
	            <caption>단위 목록</caption>
	            <colgroup>
	                 <col width="10px">
	            	 <col width="40px">
	                 <col width="40px">
	            </colgroup>			
				<thead  class="line">
	                <tr>
	                	<c:choose>
							<c:when test="${param.setMulti eq 'Y'}">
								 <th class="noBg">
								 	<label for="chargerAllCbx" class="blind">체크박스</label>
			                    	<input type="checkbox" id="chargerAllCbx" name="chargerCbx" onclick="FwkCmmnUtil.setAllCheck('chargerAllCbx','chargerCbx');">
			                    </th>
							</c:when>
							<c:otherwise>
								 <th class="noBg">번호</th>
							</c:otherwise>
						</c:choose>
	                    <th>단위코드</th>
	                    <th>단위명</th>
	                </tr>
	            </thead>
				<tbody>
<!-- 					<tr>
						<td class="txtc">일반</td>
						<td>EBS 프로그램 연장구매</td>
						<td class="txtr">225,000,000</td>
						<td class="txtr">225,000,000</td>
						<td class="txtr">225,000,000</td>
						<td class="txtc">계약관리실</td>
					</tr>
 -->					<c:if test="${comFn:nvl(unitInqireListTotcnt, 0) == 0}">
						<tr>
							<td colspan="3">검색 조건에 해당하는 데이터가 존재하지 않습니다.</td>
						</tr>
					</c:if>
					<c:if test="${unitInqireListTotcnt > 0}">
						<c:forEach var="data" items="${unitInqireList}" varStatus="status" >
							<tr class="row" <c:if test="${param.setMulti ne 'Y'}">onclick="setUnitInfo('${data.CD_VALUE}', '${data.CD_VALUE_NM}');"</c:if> style="cursor: pointer;">
								<c:if test="${param.setMulti eq 'Y'}">
									<td>
										<label for="chargerCbx${status.count }" class="blind">체크박스</label>
										<input type="checkbox" id="chargerCbx${status.count }" name="chargerCbx">
									</td>
								</c:if>
								<c:if test="${param.setMulti ne 'Y'}">
									<td style="text-align : center">${data.RNUM}&nbsp;</td>
								</c:if> 
								<td style="text-align : center">${data.CD_VALUE}&nbsp;</td>
								<td style="text-align : center">${data.CD_VALUE_NM}&nbsp;</td>
							</tr>
						</c:forEach>
					</c:if>
				</tbody> 
			</table>
		</div>
		
			
			<!-- Data Paging -->
			<div class="pop_list_bottom">
				<div class="pop_list_pager">
					<comTag:pagingIpro totalCount="${unitInqireListTotcnt}" pageNo="${comFn:nvl(param.P_PAGE_NO, 1)}" pageSize="${comFn:nvl(param.P_PAGE_SIZE, 10)}" clickPage="pageObj.clickPage"/>
				</div>
				<div class="btn_wrap view_btn" > 
		       		<c:if test="${param.setMulti eq 'Y'}">
		        		<button type="button" class="btn btn_02 btn_close" id="choiceBtn">선택</button>
		        	</c:if>
		            <button type="button" class="btn btn_02 btn_close" id="closeBtn" onclick="window.close();">닫기</button>
		        </div> 
			</div>
		</div>	
		<%-- 
	    <div class="T_btnLayer fr">
	   		<c:if test="${param.setMulti eq 'Y'}">
	        	<button type="button" class="btn btn_2b8f5d" id="choiceBtn">선택</button>
	        </c:if>
			<button type="button" class="blueBtn L" id="closeBtn" onclick="window.close();">닫기</button>
	    </div> --%>
	</div> 
</div>
