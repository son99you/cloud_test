<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 부정당업체 상세(팝업)
 *
 * <pre>
 * user 
 *    |_ snctVendRegInfoDetail.jsp
 * 
 * </pre>
 * @date : 2017. 06. 13
 * @version : 1.0
 * @author : 은우소프트 홍찬일
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %> 
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %> 

<div class="pop_wrap">
	<div class="pop_header">
		<h1 class="tit">부정당업체</h1>
	</div> <!--// pop_header E -->

	<div class="pop_container">
		<input type="hidden" id="P_VEND_REG_NO" value="${snctVendRegInfoDetail.VEND_REG_NO }">
		<input type="hidden" id="P_SNCT_SN" value="${snctVendRegInfoDetail.SNCT_SN }">
		
		<div class="view_wrap typeC">
			
			<div class="view_area m0 typeB">
				<table>
					<colgroup>
						<col width="15%">
						<col width="35%">
						<col width="15%">
						<col width="35%">
					</colgroup>
					<tr>
						<th>업체명</th>
						<td>${snctVendRegInfoDetail.VEND_NM }</td>
						<th>사업자번호</th>
						<td>${comFn:formatBizNumber(snctVendRegInfoDetail.BIZRNO)}</td>
					</tr>
				</table>
			</div>
			
			<br>
			
			<div class="view_area m0 typeB">
				<table>
					<colgroup>
						<col width="15%">
						<col width="*">
					</colgroup>
					<tr>
						<th>제재상태</th>
						<td>${snctVendRegInfoDetail.SNCT_STCD_NM }</td>
					</tr>
					<tr>
						<th>제재기간</th>
						<td>${comFn:formatDate(snctVendRegInfoDetail.SNCT_STDE,'yyyyMMdd','yyyy-MM-dd')} ~ ${comFn:formatDate(snctVendRegInfoDetail.SNCT_ENDE,'yyyyMMdd','yyyy-MM-dd')}</td>
					</tr>
					<tr>
						<th>제재사유</th>
						<td><textarea style="height: 100px; width: 100%;" readonly="readonly">${snctVendRegInfoDetail.SNCT_RSN_CNTN }</textarea></td>
					</tr>
					<tr>
						<th>제재근거</th>
						<td><textarea style="height: 100px; width: 100%;" readonly="readonly">${snctVendRegInfoDetail.SNCT_BSS_CNTN }</textarea></td>
					</tr>
					<tr>
						<th>비고</th>
						<td><textarea style="height: 100px; width: 100%;" readonly="readonly">${snctVendRegInfoDetail.RMK }</textarea></td>
					</tr>
				</table>
			</div>
			
			<div class="pop_list_bottom">
				<div class="btn_wrap view_btn">
					<button type="button" class="btn btn_s btn_del" id="closeBtn" onclick="javascript:window.close();">닫기</button>
				</div> <!--// btn_wrap E -->
			</div>			
		</div>
	</div>
</div>