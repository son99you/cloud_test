<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%--
 * 협력업체 목록조회
 *
 * <pre>
 * comm
 *  |_popup
 *   | 	ccpyPopupList.jsp
 * 
 * </pre>
 * @date : 2017. 06. 21
 * @version : 1.0
 * @author : 은우소프트 이주연 
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %>

<script type="text/javascript" src="${jsPath}/opro/views/comm/popup/ccpyPopupList.js"></script>


<div id="windowPopup">
	<h3 class="windowTitle">협력업체 조회</h3>
	<div class="formLayer">
		<form id="searchFrm" class="search_form" method="POST" action="${contextPath}/prcm/popup/ccpyList.do">
			<div class="searchFormLayerLine">
                <span class="search_bullet" style="width: 70px;">업체명</span>
                <span class="contents_search_bar"></span>
                <label for="P_ENTRPS_NM_S" class="blind">업체명</label>
                <input type="text" class="lineTxt" id="P_ENTRPS_NM_S" name="P_ENTRPS_NM_S" style="width: 400px;" value="${param.P_ENTRPS_NM_S}" >
            </div>		
            <div class="searchFormLayerLine">
	            <span class="search_bullet" style="width: 68px;">사업자번호</span>
		        <span class="contents_search_bar"></span>
		        <label for="P_BIZRNO_S" class="blind">사업자번호</label>
	            <input type="text" class="lineTxt" id="P_BIZRNO_S" name="P_BIZRNO_S" style="width: 185px;" value="${param.P_BIZRNO_S}" maxlength="10" >
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
        <p class="list_count">총 <span>5</span>건</p>
    </div>
       
       
       
    <!-- Data List -->
	<div class="tableLayer">
		<table class="tableList" summary="협력업체 목록 입니다.">
            <caption>협력업체 목록</caption>
            <colgroup>
                <col width="40px">
                <col width="100px">
                <col width="200px">
                <col width="80px">
                <col width="120px">
            </colgroup>			
			<thead>
                <tr>
                	<th scope="col" class="noBg">
                		<label for="ccpyChoiseAllCbx" class="blind">체크박스</label>
                		<input type="checkbox" id="ccpyChoiseAllCbx" name="ccpyChoiseCbx" onclick="FwkCmmnUtil.setAllCheck('ccpyChoiseAllCbx','entrpsChoiseCbx');">
                	</th>
                    <th scope="col">업체명</th>
                    <th scope="col">대표자명</th>
                    <th scope="col">사업자번호</th>
                </tr>
            </thead>
			<tbody>
				<tr class="row"  style="cursor: pointer;">
					<td>
						<label for="entrpsChoiseCbx${status.count }" class="blind">체크박스</label>
						<input type="checkbox" id="entrpsChoiseCbx${status.count }" value="은우소프트,정한규,119-86-02801" name="entrpsChoiseCbx" value="">
					</td>
					<td class="left_T">은우소프트&nbsp;</td>
					<td>정한규&nbsp;</td>
					<td>119-86-02801&nbsp;</td>
				</tr>
				<tr class="row"  style="cursor: pointer;">
					<td>
						<label for="entrpsChoiseCbx${status.count }" class="blind">체크박스</label>
						<input type="checkbox" id="entrpsChoiseCbx${status.count }" value="에이치소프트,홍찬일,121-54-02141"   name="entrpsChoiseCbx" value="">
					</td>
					<td class="left_T">에이치소프트1&nbsp;</td>
					<td>홍찬일&nbsp;</td>
					<td>121-54-02141&nbsp;</td>
				</tr>
				<tr class="row"  style="cursor: pointer;">
					<td>
						<label for="entrpsChoiseCbx${status.count }" class="blind">체크박스</label>
						<input type="checkbox" id="entrpsChoiseCbx${status.count }" value="엘소프트,이주연,154-20-44124"  name="entrpsChoiseCbx" value="">
					</td>
					<td class="left_T">엘소프트&nbsp;</td>
					<td>이주연&nbsp;</td>
					<td>154-20-44124&nbsp;</td>
				</tr>
			</tbody> 
		</table>
		
		<!-- Data Paging -->
		<div class="paging_place">
			<comTag:paging totalCount="${bsnsDeptListTotcnt}" pageNo="${comFn:nvl(param.P_PAGE_NO, 1)}" pageSize="${comFn:nvl(param.P_PAGE_SIZE, 10)}" clickPage="pageObj.clickPage"/>
		</div>
		 
        <div class="T_btnLayer fr">
            <button type="button" class="blueBtn L" id="choiceBtn" onclick="addDept();">선택</button>
            <button type="button" class="blueBtn L" id="closeBtn">닫기</button>
        </div>

    
	</div>         
</div>