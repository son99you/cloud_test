<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%--
 * 계약관리 > 하도급관리 > 하도급신청 등록 > 계약선택(팝업)
 *
 * <pre>
 * comm 
 *  |_ popup
 *    |_ contSelList.jsp
 * 
 * </pre>
 * @date : 2017. 06. 21
 * @version : 1.0
 * @author : 은우소프트 은잔디
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %>

<script type="text/javascript" src="${jsPath}/opro/views/comm/popup/contSelList.js"></script>

<div id="windowPopup" class="w_92p">
	<h3 class="windowTitle">계약목록 조회</h3>
	<div class="formLayer">

		<form id="searchFrm" class="search_form" method="POST" >
			<input type="hidden" id="P_PAGE_GBN" name="P_PAGE_GBN" value="">
			<input type="hidden" id="btnId" name="searchGbnId" value="${param.searchGbnId}">
			
            <fieldset>
                <legend></legend>
            </fieldset>		
            
            <div class="searchFormLayerLine">
		        <span class="search_bullet" style="width: 60px;">계약번호</span>
                <span class="contents_search_bar"></span>
                <label for="P_VEND_NM_S" class="blind">계약번호</label>
                <input type="text" class="lineTxt" id="P_CONT_NO_S" name="P_CONT_NO_S" style="width: 180px;" value="" >
	            <span class="search_bullet" style="width: 60px;">계약명</span>
		        <span class="contents_search_bar"></span>
		        <label for="P_CONT_NM_S" class="blind">계약명</label>
	            <input type="text" class="lineTxt" id="P_CONT_NM_S" name="P_CONT_NM_S" style="width: 180px;" value="" >
	            
	            <div class="T_btnLayer fr cn">
	                <button type="button" class="grayBtn ico" id="searchBtn">
	                    <img src="${imagePath}/opro/icon/ico_search.png" alt="조회 버튼"> 조회
	                </button>
	            </div>
            </div>
        </form>
	</div>
            
	<!-- Data Total Count -->
    <div class="tableComment">
        <p class="list_count">총 <span>1</span>건</p>
    </div>
       
    <!-- Data List -->
	<div class="tableLayer">
		<table class="tableList">
            <caption>계약 목록</caption>
            <colgroup>
                <col width="80px">
                <col width="120px">
                <col width="340px">
            </colgroup>			
			<thead>
                <tr>
                    <th class="noBg">번호</th>
                    <th>계약번호</th>
                    <th>계약명</th>
                </tr>
            </thead>
			<tbody>
				<tr class="row" onclick="setContSelListInfo('PC20170100', '(강원지사)미래코 폐광지역 꿈나무 응원', '2,400,000', '2017-06-13', '2017-01-01', '2017-12-30', '은우소프트', '정한규', '서울특별시 구로구 디지털로33길', '구매');" style="cursor: pointer;">
					<td>1</td>
					<td>PC20170100</td>
					<td class="left_T">(강원지사)미래코 폐광지역 꿈나무 응원</td>
				</tr>
			</tbody> 
		</table>
		
		<!-- Data Paging -->
		<div class="paging_place">
			<comTag:paging totalCount="${bsnsDeptListTotcnt}" pageNo="${comFn:nvl(param.P_PAGE_NO, 1)}" pageSize="${comFn:nvl(param.P_PAGE_SIZE, 10)}" clickPage="pageObj.clickPage"/>
		</div>
		
        <div class="T_btnLayer fr">
            <button type="button" class="blueBtn L" id="closeBtn">닫기</button>
        </div>
	</div>         
</div>

<form id="registFrm">
	<input type="hidden" id="P_CONT_NO" name="P_CONT_NO" >
	<input type="hidden" id="P_CHNG_NGR" name="P_CHNG_NGR" >
</form>