<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 평가계획현황 > 평가계획  
 *
 * <pre>
 * ebid 
 *    |_ popup
               |_ fdqntEvlTable.jsp
 * 
 * </pre>
 * @date : 2017. 06. 26
 * @version : 1.0
 * @author : 은우소프트 전상훈
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %> 
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %> 

<script type="text/javascript" src="${jsPath}/ipro/views/ebid/popup/evlFormInqire.js"></script>

<div class="pop_wrap">
	<div class="pop_header">
		<h1 class="tit">평가표 조회</h1>
	</div> <!--// pop_header E -->

	<div class="pop_container">
		<div class="view_wrap typeC">
			<div class="tit_area">
				<h2 class="tit01">평가표 기본정보</h2>
			</div>
			<div class="view_area">
				<table class="table">
					<caption>평가표 기본정보</caption>
		          		<colgroup>
		               	<col width="15%"/>
		               	<col width="85%"/>
		           	</colgroup>
			    	<tr>
			        	<th>평가표명</th>
			        	<td>SW사업(ICT컨설팅) 평가기준표</td>
			       	</tr>
			       	<tr>
			        	<th>평가유형</th>
			        	<td>소집</td>
			        </tr>
			        <c:if test="${param.P_EVL_GUBN ne 'F'}">
			        <tr>
			        	<th>평가자유형</th>
			        	<td>
			        		<c:if test="${param.P_EVL_GUBN eq 'NPMT'}">비</c:if>상임위원
			        	</td>
			        </tr>
			        </c:if>
			    </table>
			</div> <!--// view_area E -->
			
			<div class="tit_area">
				<h2 class="tit01">평가표 정보<span style="color: red;"></span></h2>
			</div>
			
			<div class="view_area">
				<table class="table">
					<caption>평가표 정보</caption>
		          		<colgroup>
		               	<col width="30%"/>
		               	<col width="10%"/>
		               	<col width="*"/>
		               	<col width="15%"/>
		           	</colgroup>
			    	<tr>
			        	<th>평가항목</th>
			        	<th>항목배점</th>
			        	<th>평가상세항목</th>
			            <th>상세항목배점</th>
			        </tr>
			        <c:if test="${param.P_EVL_GUBN eq 'F'}">
				        <tbody>
					        <tr>
					        	<td style="border-left: 1px solid #d5ddfd">경영상태</td>
					        	<td>8점</td>
					            <td class="pointer" onclick="evlFormDtlsCnInqireForm();">신용평가등급</td>
					            <td>8점</td>
					        </tr>
					        <tr>
					        	<td style="border-left: 1px solid #d5ddfd">회사 유사사업 수행실적</td>
					        	<td>6점</td>
					            <td class="pointer" onclick="evlFormDtlsCnInqireForm();">국내외 유사사업 수행실적 비율</td>
					            <td>6점</td>
					        </tr>
					        <tr>
					        	<td style="border-left: 1px solid #d5ddfd">핵심투입인력 유사사업 수행실적</td>
					        	<td>6점</td>
					            <td class="pointer" onclick="evlFormDtlsCnInqireForm();">국내외 유사사업 수행실적 건수</td>
					            <td>6점</td>
					        </tr>
					        <tr>
					        	<th colspan="2">총 합</th>
					            <th colspan="2">20점</th>
					        </tr>
				        </tbody>
			        </c:if>
			        <c:if test="${param.P_EVL_GUBN eq 'PMT'}">
				        <tbody>
					        <tr>
					        	<td style="border-left: 1px solid #d5ddfd">제안사 소개</td>
					        	<td>5점</td>
					            <td class="pointer" onclick="evlFormDtlsCnInqireForm();">일반현황 및 연혁 등</td>
					            <td>5점</td>
					        </tr>
					        <tr>
					        	<td rowspan="4" style="border-left: 1px solid #d5ddfd">전략 및 방법론</td>
					        	<td rowspan="4">15점</td>
					            <td class="pointer" onclick="evlFormDtlsCnInqireForm();">사업이해도</td>
					            <td>4점</td>
					        </tr>
					        <tr>
					        	<td class="pointer" onclick="evlFormDtlsCnInqireForm();">추진전략</td>
					            <td>4점</td>
					        </tr>
					        <tr>
					        	<td class="pointer" onclick="evlFormDtlsCnInqireForm();">성과관리</td>
					            <td>4점</td>
					        </tr>
					        <tr>
					        	<td class="pointer" onclick="evlFormDtlsCnInqireForm();">컨설팅 방법론</td>
					            <td>3점</td>
					        </tr>
					        <tr>
					        	<td rowspan="2" style="border-left: 1px solid #d5ddfd">기술 및 기능</td>
					        	<td rowspan="2">20점</td>
					            <td class="pointer" onclick="evlFormDtlsCnInqireForm();">컨설팅 요구사항</td>
					            <td>10점</td>
					        </tr>
					        <tr>
					        	<td class="pointer" onclick="evlFormDtlsCnInqireForm();">제약사항</td>
					            <td>10점</td>
					        </tr>
					        <tr>
					        	<td rowspan="6" style="border-left: 1px solid #d5ddfd">프로젝트 관리</td>
					        	<td rowspan="6">25점</td>
					            <td class="pointer" onclick="evlFormDtlsCnInqireForm();">관리방법론</td>
					            <td>5점</td>
					        </tr>
					        <tr>
					        	<td class="pointer" onclick="evlFormDtlsCnInqireForm();">개발장비</td>
					            <td>5점</td>
					        </tr>
					        <tr>
					        	<td class="pointer" onclick="evlFormDtlsCnInqireForm();">일정관리</td>
					            <td>4점</td>
					        </tr>
					        <tr>
					        	<td class="pointer" onclick="evlFormDtlsCnInqireForm();">위험관리</td>
					            <td>4점</td>
					        </tr>
					        <tr>
					        	<td class="pointer" onclick="evlFormDtlsCnInqireForm();">사후관리</td>
					            <td>4점</td>
					        </tr>
					        <tr>
					        	<td class="pointer" onclick="evlFormDtlsCnInqireForm();">모니터링 및 보고</td>
					            <td>3점</td>
					        </tr>
					        <tr>
					        	<td rowspan="4" style="border-left: 1px solid #d5ddfd">프로젝트 지원</td>
					        	<td rowspan="4">10점</td>
					            <td class="pointer" onclick="evlFormDtlsCnInqireForm();">품질보증</td>
					            <td>3점</td>
					        </tr>
					        <tr>
					        	<td class="pointer" onclick="evlFormDtlsCnInqireForm();">교육훈련</td>
					            <td>2점</td>
					        </tr>
					        <tr>
					        	<td class="pointer" onclick="evlFormDtlsCnInqireForm();">기밀보안</td>
					            <td>3점</td>
					        </tr>
					        <tr>
					        	<td class="pointer" onclick="evlFormDtlsCnInqireForm();">비상대책</td>
					            <td>2점</td>
					        </tr>
					        <tr>
					        	<td rowspan="2" style="border-left: 1px solid #d5ddfd">특수제안</td>
					        	<td rowspan="2">5점</td>
					            <td class="pointer" onclick="evlFormDtlsCnInqireForm();">제안요청내용 개선사항</td>
					            <td>3점</td>
					        </tr>
					        <tr>
					        	<td class="pointer" onclick="evlFormDtlsCnInqireForm();">추가 투입계획</td>
					            <td>2점</td>
					        </tr>
					        <tr>
					        	<th colspan="2">총 합</th>
					            <th colspan="2">80점</th>
					        </tr>
				        </tbody>
			        </c:if>
			        <c:if test="${param.P_EVL_GUBN eq 'NPMT'}">
				        <tbody>
					        <tr>
					        	<td style="border-left: 1px solid #d5ddfd">제안사 소개</td>
					        	<td>5점</td>
					            <td class="pointer" onclick="evlFormDtlsCnInqireForm();">일반현황 및 연혁 등</td>
					            <td>5점</td>
					        </tr>
					        <tr>
					        	<td rowspan="4" style="border-left: 1px solid #d5ddfd">전략 및 방법론</td>
					        	<td rowspan="4">15점</td>
					            <td class="pointer" onclick="evlFormDtlsCnInqireForm();">사업이해도</td>
					            <td>4점</td>
					        </tr>
					        <tr>
					        	<td class="pointer" onclick="evlFormDtlsCnInqireForm();">추진전략</td>
					            <td>4점</td>
					        </tr>
					        <tr>
					        	<td class="pointer" onclick="evlFormDtlsCnInqireForm();">성과관리</td>
					            <td>4점</td>
					        </tr>
					        <tr>
					        	<td class="pointer" onclick="evlFormDtlsCnInqireForm();">컨설팅 방법론</td>
					            <td>3점</td>
					        </tr>
					        <tr>
					        	<td rowspan="2" style="border-left: 1px solid #d5ddfd">기술 및 기능</td>
					        	<td rowspan="2">20점</td>
					            <td class="pointer" onclick="evlFormDtlsCnInqireForm();">컨설팅 요구사항</td>
					            <td>10점</td>
					        </tr>
					        <tr>
					        	<td class="pointer" onclick="evlFormDtlsCnInqireForm();">제약사항</td>
					            <td>10점</td>
					        </tr>
					        <tr>
					        	<td rowspan="6" style="border-left: 1px solid #d5ddfd">프로젝트 관리</td>
					        	<td rowspan="6">25점</td>
					            <td class="pointer" onclick="evlFormDtlsCnInqireForm();">관리방법론</td>
					            <td>5점</td>
					        </tr>
					        <tr>
					        	<td class="pointer" onclick="evlFormDtlsCnInqireForm();">개발장비</td>
					            <td>5점</td>
					        </tr>
					        <tr>
					        	<td class="pointer" onclick="evlFormDtlsCnInqireForm();">일정관리</td>
					            <td>4점</td>
					        </tr>
					        <tr>
					        	<td class="pointer" onclick="evlFormDtlsCnInqireForm();">위험관리</td>
					            <td>4점</td>
					        </tr>
					        <tr>
					        	<td class="pointer" onclick="evlFormDtlsCnInqireForm();">사후관리</td>
					            <td>4점</td>
					        </tr>
					        <tr>
					        	<td class="pointer" onclick="evlFormDtlsCnInqireForm();">모니터링 및 보고</td>
					            <td>3점</td>
					        </tr>
					        <tr>
					        	<td rowspan="4" style="border-left: 1px solid #d5ddfd">프로젝트 지원</td>
					        	<td rowspan="4">10점</td>
					            <td class="pointer" onclick="evlFormDtlsCnInqireForm();">품질보증</td>
					            <td>3점</td>
					        </tr>
					        <tr>
					        	<td class="pointer" onclick="evlFormDtlsCnInqireForm();">교육훈련</td>
					            <td>2점</td>
					        </tr>
					        <tr>
					        	<td class="pointer" onclick="evlFormDtlsCnInqireForm();">기밀보안</td>
					            <td>3점</td>
					        </tr>
					        <tr>
					        	<td class="pointer" onclick="evlFormDtlsCnInqireForm();">비상대책</td>
					            <td>2점</td>
					        </tr>
					        <tr>
					        	<td rowspan="2" style="border-left: 1px solid #d5ddfd">특수제안</td>
					        	<td rowspan="2">5점</td>
					            <td class="pointer" onclick="evlFormDtlsCnInqireForm();">제안요청내용 개선사항</td>
					            <td>3점</td>
					        </tr>
					        <tr>
					        	<td class="pointer" onclick="evlFormDtlsCnInqireForm();">추가 투입계획</td>
					            <td>2점</td>
					        </tr>
					        <tr>
					        	<th colspan="2">총 합</th>
					            <th colspan="2">80점</th>
					        </tr>
				        </tbody>
			        </c:if>
			    </table>
			</div> <!--// view_area E -->
			<div class="btn_wrap view_btn">
				<button type="button" class="btn btn_02 btn_close" id="closeBtn">닫기</button>
			</div> <!--// btn_wrap E -->						
		</div> <!--// view_wrap E -->
	</div>
</div>		

<form action="" id="popupFrm" method="POST"></form>