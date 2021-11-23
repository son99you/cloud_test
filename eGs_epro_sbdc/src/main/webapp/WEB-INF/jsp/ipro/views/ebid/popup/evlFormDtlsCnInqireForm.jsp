<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 기술평가현황 > 평가상세항목 내용 조회 팝업
 *
 * <pre>
 * ebid 
 *    |_ popup
 			  |_ evlFormDtlsCnInqireForm.jsp
 * 
 * </pre>
 * @date : 2017. 06. 14
 * @version : 1.0
 * @author : 은우소프트 전상훈
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %> 
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %> 

<script type="text/javascript" src="${jsPath}/ipro/views/ebid/popup/evlFormDtlsCnInqireForm.js"></script>

<div class="pop_wrap">
	<div class="pop_header">
		<h1 class="tit">평가상세항목 내용 조회</h1>
	</div> <!--// pop_header E -->

	<div class="pop_container">
		<div class="view_wrap typeC">
			<!--  
			<div class="tit_area">
				<h2 class="tit01">평가표 기본정보</h2>
			</div>
			-->
			<div class="view_area">
				<table class="table">
			    	<colgroup>
			        	<col width="20%" />
			            <col width="80%" />
			        </colgroup>
			    	<tr>
			        	<th>평가항목명</th>
			            <td>경영상태</td>
			        </tr>
			        <tr>
			        	<th>평가상세항목명</th>
			            <td>신용평가등급</td>
			        </tr>
			        <tr>
			        	<th>평가상세항목 내용</th>
			            <td>업체의 신용등급을 체크해서 평가한다.</td>
			        </tr>
			    </table>
			</div> <!--// view_area E -->
			
			<div class="btn_wrap view_btn">
				<button type="button" class="btn btn_02 btn_close" id="closeBtn">닫기</button>
			</div> <!--// btn_wrap E -->						
		</div> <!--// view_wrap E -->
	</div>
</div>	