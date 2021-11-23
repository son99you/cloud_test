<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 낙찰자선정 > 예가선택업체 조회
 *
 * <pre>
 * ebid 
 *    |_ popup
              |_ bidResultPrdprcChoiseEntrpsInqire.jsp
 * 
 * </pre>
 * @date : 2017. 06. 21
 * @version : 1.0
 * @author : 은우소프트 전상훈
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %> 
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %> 

<script type="text/javascript" src="${jsPath}/ipro/views/ebid/popup/opengSetVal.js"></script> 
 
<div class="pop_wrap">
	<div class="pop_header">
		<h1 class="tit">부적격 업체 개찰포함여부</h1>
	</div> <!--// pop_header E -->
	<div class="pop_container">
		<div class="view_wrap typeC" style="text-align: center;">
			<div style="font-size: 16px; text-align: left;">
				부적격 업체가 있습니다.<br><br> 
				개찰시 부적격업체가 선택한 예비가격을 포함하시겠습니까?<br><br>
   		 	</div>
			<div class="radio_box ">
				<input name="P_IN_YN" id="P_IN_YN_1" type="radio" checked="checked" value="Y">
				<label class="vam mr5" for="P_IN_YN_1">포함</label>
			</div>
			<div class="radio_box ">
				<input name="P_IN_YN" id="P_IN_YN_2" type="radio" value="N">
				<label class="vam mr5" for="P_IN_YN_2">미포함</label>
			</div>
			<div class="btn_wrap view_btn">
				<button type="button" class="btn btn_02 btn_revise" id="openBtn" >개찰</button>
		    	<button type="button" class="btn btn_02 btn_close" id="closeBtn" >닫기</button>
		    </div>
		</div>
	</div> <!--// content E-->
</div>
