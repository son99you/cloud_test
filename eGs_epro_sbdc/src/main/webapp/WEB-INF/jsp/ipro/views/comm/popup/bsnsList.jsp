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
<%@ taglib prefix="fc" uri="/WEB-INF/tlds/fwkComboBoxTag.tld" %>

<link rel="stylesheet" type="text/css" href="${cssPath}/ipro/windowPop.css">
<link rel="stylesheet" type="text/css" href="${cssPath}/ipro/template/pagingPlace.css">

<script type="text/javascript" src="${jsPath}/ipro/views/comm/popup/bsnsList.js"></script>

<div id="windowPopup" class="w_800">
	<h3 class="windowTitle">사업목록 조회</h3>
	<div class="formLayer">

		<form id="searchFrm" class="search_form" method="POST" action="">
			<input type="hidden" id="P_PAGE_NO" name="P_PAGE_NO" value="${comFn:nvl(param.P_PAGE_NO, 1)}">
			<input type="hidden" id="P_PAGE_SIZE" name="P_PAGE_SIZE" value="${comFn:nvl(param.P_PAGE_SIZE, 10)}">
			<input type="hidden" id="P_BSNS_CNT" name="P_BSNS_CNT" value="${param.P_BSNS_CNT}">
			
			<table class="contable2">
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
												
												사업번호
											</td>
											
												<input type="text" class="lineTxt" id="P_BSNS_NO_S" name="P_BSNS_NO_S" style="width: 80px;" value="${param.P_BSNS_NO_S}" maxlength="14">
											</td>
											<td>
												
												사업명
											</td>
											
												<input type="text" class="lineTxt" id="P_BSNS_NM_S" name="P_BSNS_NM_S" style="width: 300px;" value="${param.P_BSNS_NM_S}" maxlength="600" >											
											</td>
										</tr>											
										<tr height="29px">
											<td>
												
												사업년도
											</td>
											
								            	<div class="selectLayer2 w_80">
								                	<label for="P_BSNS_YEAR_S" class="blind">사업년도</label>
										            <fc:fwkComboBox name="P_BSNS_YEAR_S" id="P_BSNS_YEAR_S" width="80" height="30" selectKey="${P_BSNS_YEAR_S}" list="${yearList}" ></fc:fwkComboBox>
								                </div>
											</td>
											<td>
												
												계약년도
											</td>
											
								                <div class="selectLayer2 w_80">
								                	<label for="P_BSNS_PLAN_YEAR_S" class="blind">계약년도</label>
								                	<fc:fwkComboBox name="P_BSNS_PLAN_YEAR_S" id="P_BSNS_PLAN_YEAR_S" width="80" height="30" selectKey="${param.P_BSNS_PLAN_YEAR_S}" list="${yearList2}" ></fc:fwkComboBox>
								                </div>
											</td>
										</tr>								
										<tr height="29px">
											<td>
												
												사업유형
											</td>
											
								            	<div class="selectLayer2 w_80">
								                	<label for="P_CPRBIZ_SE_CD_S" class="blind">사업유형</label>
								                	<input type="text" class="lineTxt" style="width: 80px;" value="국별협력" disabled="disabled">
								                	<input type="hidden" id="P_CPRBIZ_SE_CD_S" name="P_CPRBIZ_SE_CD_S" value="1">
								                </div>											
											</td>
											<td>
												
												사업형태
											</td>
											
								                <div class="selectLayer2 w_80">
								                	<label for="P_BSNS_STLE_CD_S" class="blind">사업형태</label>
										            <comTag:comCmcdCdValueComboBox id="P_BSNS_STLE_CD_S" name="P_BSNS_STLE_CD_S"   selectKey="${param.P_BSNS_STLE_CD_S}" cdId="20010" headerKey="" headerValue="전체" />
								                </div>				
											</td>
										</tr>								
										<tr height="29px">
											<td>
												
												지원분야
											</td>
											<td  colspan="3">
								                <div class="selectLayer2 w_120">
								                	<label for="P_SPORT_REALM_CD_S" class="blind">지원분야</label>
										            <comTag:comCmcdCdValueComboBox id="P_SPORT_REALM_CD_S" name="P_SPORT_REALM_CD_S"   selectKey="${param.P_SPORT_REALM_CD_S}" cdId="22080" headerKey="" headerValue="전체" />
								                </div>											
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
			</table>			
        </form>
	</div>
            
	<!-- Data Total Count -->
    <div class="tableComment">
        <p class="list_count">총 <span>${comFn:nvl(bsnsListTotcnt, 0)}</span>건</p>
    </div>
       
    <!-- Data List -->
	<div class="tableLayer list">
		<%-- 업체 1개만 선택 --%>
		<c:if test="${param.P_BSNS_CNT eq 1}">
		<table class="tableList" summary="사업 목록 입니다.">
            <caption>사업 목록</caption>
            <colgroup>
            	<col width="40px">
            	<col width="60px">
                <col width="220px">
                <col width="60px">
                <col width="60px">
                <col width="100px">
                <col width="80px">
            </colgroup>			
			<thead>
                <tr>
                    <th scope="col">번호</th>
                    <th scope="col">사업유형</th>
                    <th scope="col">사업명</th>
                    <th scope="col">지원분야</th>
                    <th scope="col">사업형태</th>
                    <th scope="col">사업기간</th>
                    <th scope="col">계획년도</th>
                </tr>
            </thead>
			<tbody>
				<c:if test="${comFn:nvl(bsnsListTotcnt, 0) == 0}">
					<tr>
						<td colspan="7">검색 조건에 해당하는 데이터가 존재하지 않습니다.</td>
					</tr>
				</c:if>
				<c:if test="${bsnsListTotcnt > 0}">
					<c:forEach var="data" items="${bsnsList}" varStatus="status" >
						<tr class="row"  style="cursor: pointer;" onclick="bsnsRegist(this);">
							<td>${status.count}</td>
							<td><input type="hidden" name="P_BSNS_NO" value="${data.BSNS_NO }">${data.CPRBIZ_SE_NM }</td>
							<td class="left_T"><input type="hidden" name="P_BSNS_NM" value="${data.KOREAN_BSNS_NM }">${data.KOREAN_BSNS_NM}</td>
							<td>${data.SPORT_REALM_NM}</td>
							<td>${data.BSNS_STLE_NM}</td>
							<td>${data.BSNS_BEGIN_YEAR}년 ~ ${data.BSNS_END_YEAR}년</td>
							<td>${data.BSNS_PLAN_YEAR}년</td>
						</tr>
					</c:forEach>
				</c:if>
			</tbody> 
		</table>
		</c:if>
		<%-- 업체 1개만 선택 END --%>
		
		<%-- 업체 여러개 선택 --%>
		<c:if test="${param.P_BSNS_CNT ne 1}">
		<table class="tableList" summary="협력업체 목록 입니다.">
            <caption>협력업체 목록</caption>
            <colgroup>
            	<col width="40px">
                <col width="40px">
            	<col width="60px">
                <col width="180px">
                <col width="60px">
                <col width="60px">
                <col width="100px">
                <col width="80px">
            </colgroup>			
			<thead>
                <tr>
                	<th scope="col" class="noBg">
                		<label for="bsnsChoiseAllCbx" class="blind">체크박스</label>
                		<input type="checkbox" id="bsnsChoiseAllCbx" name="bsnsChoiseCbx" onclick="FwkCmmnUtil.setAllCheck('bsnsChoiseAllCbx','bsnsChoiseCbx');" value="0">
                	</th>
                    <th scope="col">번호</th>
                    <th scope="col">사업유형</th>
                    <th scope="col">사업명</th>
                    <th scope="col">지원분야</th>
                    <th scope="col">사업형태</th>
                    <th scope="col">사업기간</th>
                    <th scope="col">계획년도</th>
                </tr>
            </thead>
			<tbody>
				<c:if test="${comFn:nvl(bsnsListTotcnt, 0) == 0}">
					<tr>
						<td colspan="8">검색 조건에 해당하는 데이터가 존재하지 않습니다.</td>
					</tr>
				</c:if>
				<c:if test="${bsnsListTotcnt > 0}">
					<c:forEach var="data" items="${bsnsList}" varStatus="status" >
					<tr class="row"  style="cursor: pointer;">
						<td>
							<label for="bsnsChoiseCbx${status.count }" class="blind">체크박스</label>
							<input type="checkbox" id="bsnsChoiseCbx${status.count }" name="bsnsChoiseCbx" value="${status.count }">
						</td>
						<td>${status.count}</td>
						<td><input type="hidden" name="P_BSNS_NO" value="${data.BSNS_NO }">${data.CPRBIZ_SE_NM }</td>
						<td class="left_T"><input type="hidden" name="P_BSNS_NM" value="${data.KOREAN_BSNS_NM }">${data.KOREAN_BSNS_NM}</td>
						<td>${data.SPORT_REALM_NM}</td>
						<td>${data.BSNS_STLE_NM}</td>
						<td>${data.BSNS_BEGIN_YEAR}년 ~ ${data.BSNS_END_YEAR}년</td>
						<td>${data.BSNS_PLAN_YEAR}년</td>
					</tr>
					</c:forEach>
				</c:if>
			</tbody> 
		</table>
		</c:if>
		<%-- 업체 여러개 선택 END --%>
		
		<!-- Data Paging -->
		<div class="paging_place">
			<comTag:pagingIpro totalCount="${bsnsListTotcnt}" pageNo="${comFn:nvl(param.P_PAGE_NO, 1)}" pageSize="${comFn:nvl(param.P_PAGE_SIZE, 10)}" clickPage="pageObj.clickPage"/>
		</div>
        <div class="T_btnLayer fr">
        	<c:if test="${param.P_BSNS_CNT ne 1}">
            	<button type="button" class="blueBtn L" id="choiceBtn">선택</button>
            </c:if>
            <button type="button" class="blueBtn L" id="closeBtn" onclick="window.close();">닫기</button>
        </div>

	</div>         
</div>

<!--  
			<div class="paging_place">
				<div class="paging_wrap"><a href="#" class="pprev" title="맨앞으로" style="display: none;"><span style="display: none;">맨앞으로</span></a><a href="#" class="prev" title="앞으로"><span style="display: none;">앞으로</span></a>
					<span><a href="#" class="active" title="1">1</a></span>
					<a href="#" class="next" title="뒤로"><span style="display: none;">뒤로</span></a><a href="#" class="nnext" title="맨뒤로" style="display: none;"><span style="display: none;">맨뒤로</span></a>
				</div>
			</div>
		-->