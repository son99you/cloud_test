<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%--
 * 입찰관리 > 품목별 세부입찰사항 팝업
 *
 * <pre>
 * ebid
 *    |_popup
 *        |		prdlstAcctoDetailBidDtlsPopupInqire.jsp
 * 
 * </pre>
 * @date : 2017. 06. 23
 * @version : 1.0
 * @author : 은우소프트 이주연
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %>

<script type="text/javascript" src="${jsPath}/views/comm/popup/prdlstAcctoDetailBidDtlsPopupInqire.js"></script>

<div id="windowPopup">
	<h3 class="windowTitle">품목별 세부입찰사항</h3>
        <div class="tableLayer">
            <p class="popSubTitle marginSet">투찰업체</p>
            <table class="table" summary="투찰 업체 테이블 입니다.">
                <caption>투찰업체</caption>
                <colgroup>
                   <col width="20%">
                   <col width="30%">
                   <col width="20%">
                   <col width="30%">
                </colgroup>
                <tbody>
	                <tr class="line">
	                    <th>업체명</th>
	                    <td>주식회사 은우소프트&nbsp;</td>
	                    <th>대표자</th>
	                    <td>정한규&nbsp;</td>
	                </tr>
	                <tr>
	                    <th>사업자번호</th>
	                    <td>119-86-02801&nbsp;</td>
	                    <th>전화번호</th>
	                    <td>02-847-0832&nbsp;</td>
	                </tr>
                </tbody>
            </table>
        </div>
        
        <div class="tableLayer">
		<p class="popSubTitle marginSet">입찰물품 목록</p>
        <table class="tableList" summary="입찰물품 목록 테이블 입니다.">
            <caption>입찰물품 목록</caption>
            <colgroup>
                <col width="5%">
                <col width="25%">
                <col width="15%">
                <col width="15%">
                <col width="17%">
                <col width="33%">
            </colgroup>			
			<thead>
                <tr>
                    <th>번호</th>
                    <th>품명</th>
                    <th>단위</th>
                    <th>수량</th>
                    <th>단가</th>
                    <th>금액</th>
                </tr>
            </thead>
			<tbody>
				<tr class="row">
					<td>1</td>
					<td class="left_T">노트북</td>
					<td> 대 </td>
					<td>10</td>
					<td>1,200,000</td>
					<td>12,000,000</td>
				</tr>
			</tbody> 
		</table>
        </div>
        
      <%--Data View Area1 --%>
		<div class="tableLayer">
		<p class="popSubTitle marginSet">첨부파일</p>
			<table class="table">
		    	<caption>첨부파일</caption>
				<colgroup>
					<col width="170px">
	                <col width="320px">
	                <col width="170px">
	                <col width="320px">  
				</colgroup>
		        <tbody>
					<tr>
	                	<th scope="row" class="vtop">첨부파일</th>
						<td colspan="3" class="vtop" style="margin-top: 3px; line-height: 30px;">
							<a href="">투찰내역서.docx</a>
						</td>
		             </tr>
		        </tbody>
		    </table> 
		</div>
        <div class="T_btnLayer fr">
            <button type="button" class="blueBtn L" id="closeBtn"  onclick="window.close();">닫기</button>
        </div>
</div>