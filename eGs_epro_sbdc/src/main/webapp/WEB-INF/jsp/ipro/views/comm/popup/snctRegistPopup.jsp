<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 공통 > 품목등록 (팝업)
 *
 * <pre>
 * comm 
 *  |_popup
 *   |_itemList.jsp
 * 
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %>

<script type="text/javascript" src="/statics/fwk/CmmnUtil.js"></script>
<script type="text/javascript" src="${jsPath}/ipro/views/comm/popup/snctRegistPopup.js"></script>

<div class="pop_wrap">
	<div class="pop_header">
		<h1 class="tit">업체제재등록</h1>
	</div> <!--// pop_header E -->
    <form id="registFrm" method="POST">
    	<input type="hidden" id="PROCR_NM" value="${PROCR_NM }">
      	<div class="pop_container">
			<div class="view_wrap typeA">
        		<div class="view_area">      
		            <table>
						<colgroup>
							<col style="width: 15%;">
							<col style="width: 25%;">
							<col style="width: 15%;">
							<col style="width: *">
						</colgroup>
						<tr>
							<th scope="row" class="bullet_orange">제재구분</th>
							<td>
								<comTag:comCmcdCdValueComboBox id="P_SNCT_STCD" name="P_SNCT_STCD" cdId="SNCT_STCD" headerValue="선택"/>
							</td>
							<th scope="row" class="bullet_orange">제재기간</th>
							<td>
								<div class="calendar_box">
				                    <input type="text" class="w100" id="P_SNCT_STDE" name="P_SNCT_STDE" maxlength="10" date>
				                	<span class="wave"> &nbsp;~ &nbsp;</span>
					            	<input type="text" class="w100" id="P_SNCT_ENDE" name="P_SNCT_ENDE" maxlength="10" date>
					            </div>
							</td>
						</tr>
						<tr>
							<th scope="row" class="bullet_orange">제재사유</th>
							<td colspan="3">
								<textarea rows="8" cols="180" maxlength="4000" id="P_SNCT_RSN_CNTN" name="P_SNCT_RSN_CNTN"></textarea>
							</td>
						</tr>
						<tr>
							<th scope="row" class="bullet_orange">제재근거</th>
							<td colspan="3">
								<textarea rows="8" cols="180" maxlength="4000" id="P_SNCT_BSS_CNTN" name="P_SNCT_BSS_CNTN"></textarea>
							</td>
						</tr>
					</table>
        		</div>
			</div>
		</div>
	</form>
    <div class="btn_wrap view_btn">
		<button type="button" class="btn btn_02 btn_revise" id="saveBtn" >저장</button>
    	<button type="button" class="btn btn_02 btn_close" id="closeBtn" onclick="javascript:self.close()">닫기</button>
    </div>
</div>		   
