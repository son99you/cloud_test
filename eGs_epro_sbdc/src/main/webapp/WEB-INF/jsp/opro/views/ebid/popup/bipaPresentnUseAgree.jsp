<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 전자입찰 >가격(입찰)서 제출 안내 팝업
 *
 * <pre>
 * elbi
 	  |_	popup 
 *    			|_ oepElbiPopupBipaPresentnUseAgree.jsp
 * </pre>
 * @date : 2017. 03. 09
 * @version : 1.0
 * @author : 은우소프트 홍찬일
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %>

<script type="text/javascript" src="${jsPath}/opro/views/ebid/popup/bipaPresentnUseAgree.js"></script>

<div class="pop_wrap">
	<div class="pop_header">
		<h1 class="tit">가격(입찰)서 제출 안내</h1>
	</div> <!--// pop_header E -->
	<div class="pop_container">
		<div class="view_wrap typeC">
			<div class="info_wrap">
				<p class="conts">가격(입찰)서는 제출 이후 수정하실 수 없습니다. 따라서 입찰금액 등 내용이 맞는지 다시 한번 확인하시기 바랍니다.</p>
				<p>입찰금액 작성은 원화 입찰만 허용</p>
				<p>내역입찰 시 내역서와 투찰금액이 일치하지 않는 경우 무효처리</p>
				<p>단위오타 및 서류의 누락·보완 또는 허용하지 않는 임의수정을 요구하는 경우로서 입찰진행을 방해하는 경우 무효처리</p>
			</div> <!--// info_wrap E -->

			<div style="text-align: center;">
				<label for="chckOk"><input type="checkbox" id="chckOk">&nbsp;해당 안내를 확인 했습니다.</label>
			</div>
		</div>	
	    <div class="btn_wrap view_btn">
	       	<button type="button" class="btn btn_m btn_orange" id="chckBtn">확인</button>
	        <button type="button" class="btn btn_m btn_del" id="closeBtn">닫기</button>
	    </div>
	</div>
</div>
