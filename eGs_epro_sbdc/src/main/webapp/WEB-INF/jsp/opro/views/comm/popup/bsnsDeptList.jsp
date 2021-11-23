<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%--
 * 사업부서 조회(팝업)
 *
 * <pre>
 * elbi 
 *  |_popup
 *   |_bsnsDeptList.jsp
 * 
 * </pre>
 * @date : 2017. 06. 19.
 * @version : 1.0
 * @author : 은우소프트 하성윤
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %>

<script type="text/javascript" src="${jsPath}/views/comm/popup/bsnsDeptList.js"></script>

<div id="windowPopup" class="w_92p">
	<h3 class="windowTitle">사업부서 조회</h3>
	<div class="formLayer">

		<form id="searchFrm" class="search_form" method="POST" >

            <fieldset>
                <legend></legend>
            </fieldset>		
            
            <div class="searchFormLayerLine">
		        <span class="search_bullet" style="width: 68px;">부서명</span>
		        <span class="contents_search_bar"></span>
		        	<input type="text" class="lineTxt" id="P_ORG_NM_S" name="P_ORG_NM_S" style="width: 160px;" value="" >
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
        <p class="list_count">총 <span>10</span>건</p>
    </div>
       
       
       
    <!-- Data List -->
	<div class="tableLayer">
		<table class="tableList">
            <caption>사업부서 목록</caption>
            <colgroup>
                <col width="20px"/>
                <col width="40px"/>
                <col width="100px"/>
            </colgroup>			
			<thead>
                <tr>
                    <th class="noBg">번호</th>
                    <th>부서ID</th>
                    <th>부서명</th>
                </tr>
            </thead>
			<tbody>
				<tr class="row" onclick="" style="cursor: pointer;">
					<td>10</td>
					<td >A01</td>
					<td class="left_T">구매1팀</td>
				</tr>
				<tr class="row" onclick="" style="cursor: pointer;">
					<td>9</td>
					<td >A02</td>
					<td class="left_T">구매2팀</td>
				</tr>
				<tr class="row" onclick="" style="cursor: pointer;">
					<td>8</td>
					<td >A03</td>
					<td class="left_T">구매3팀</td>
				</tr>
				<tr class="row" onclick="" style="cursor: pointer;">
					<td>7</td>
					<td >A04</td>
					<td class="left_T">영업1팀</td>
				</tr>
				<tr class="row" onclick="" style="cursor: pointer;">
					<td>6</td>
					<td >A05</td>
					<td class="left_T">영업2팀</td>
				</tr>
				<tr class="row" onclick="" style="cursor: pointer;">
					<td>5</td>
					<td >A06</td>
					<td class="left_T">개발1팀</td>
				</tr>
				<tr class="row" onclick="" style="cursor: pointer;">
					<td>4</td>
					<td >A07</td>
					<td class="left_T">개발2팀</td>
				</tr>
				<tr class="row" onclick="" style="cursor: pointer;">
					<td>3</td>
					<td >A08</td>
					<td class="left_T">개발3팀</td>
				</tr>
				<tr class="row" onclick="" style="cursor: pointer;">
					<td>2</td>
					<td >A09</td>
					<td class="left_T">운영지원팀</td>
				</tr>
				<tr class="row" onclick="" style="cursor: pointer;">
					<td>1</td>
					<td >A10</td>
					<td class="left_T">비서실</td>
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