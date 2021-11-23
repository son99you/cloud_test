<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 사용자별 권한관리 목록
 *
 * <pre>
 * sytm
 *    |_ userAuthMgrList.jsp
 * 
 * </pre>
 * @date : 2017. 06. 15
 * @version : 1.0
 * @author : 은우소프트 이주연
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %> 
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %> 

<link rel="stylesheet" type="text/css" href="${cssPath}/ipro/bodyBasic.css">
<link rel="stylesheet" type="text/css" href="${cssPath}/ipro/template/buttonStyle.css">
<link rel="stylesheet" type="text/css" href="${cssPath}/ipro/template/pagingPlace.css">
<link rel="stylesheet" type="text/css" href="${cssPath}/ipro/template/calendar.css">

<script type="text/javascript" src="${jsPath}/ipro/views/sytm/userAuthMgrList.js"></script>  
<div class="content">
	<h3>사용자별권한관리</h3>
	<div class="list">
		<div id="searchWrap">
			<form name="biSearchForm" method="post">
				<table class="contable2">
					<tr>
						<td>
							<table class="contable">
								<tr>
									<td>
										<table>
											<colgroup>
												<col width="15%" align="left">
												<col width="35%" align="left">
												<col width="15%" align="left">
												<col width="35%" align="left">
											</colgroup>
											<tr height="24px">
												<td>
													
													사용자명
												</td>
												<td  >
													<input type="text" onKeyDown="enter(event);" size="20">
												</td>
												<td>
													
													사용자권한
												</td>
												<td>
													<select name="">
														<option value="x">전체</option>
									          			<option value="0">전체사용자</option>
									              		<option value="1">시스템관리자</option>
									              		<option value="2">구매담당자</option>
									              		<option value="3">계약담당자</option>
									              		<option value="4">구매요구자</option>
									              		<option value="5">예가담당자</option>
									              		<option value="6">검수담당자</option>
									              		<option value="7">실적증명발급담당자</option>
									          		</select>
												</td>
											</tr>
											<tr>
												<td class=" searchBtnTd" colspan="4">
													<button type="button" class="grayBtn ico" id="searchBtn">
									                    <img src="${imagePath}/ipro/icon/ico_search.png" alt="조회 버튼"> 조회
									                </button>
												</td>
											</tr>
										</table>
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
			</form>
		</div>
		<br>

		<div class="list_wrap mt30" id="contentWrap">
			<div class="list_top">
				<p class="total">총 <span></span>건</p>		
				<!--  <div class="btn_right"> -->
					<!-- <button type="button" class="btn btn_02 btn_down">엑셀 다운로드</button> -->
				<!-- </div> -->
			</div> <!--// list_top E -->
			<div class="list_conts">		
				<table>
					<caption>사용자별권한관리 목록</caption>
		             	<colgroup>
		                  <col width="10%"/>
		                  <col width="10%"/>
		                  <col width="20%"/>
		                  <col width="10%"/>
		                  <col width="30%"/>
		           	</colgroup>
			    	<tr>
				    	<th>사번</th>
				    	<th>사용자명</th>
				    	<th>부서명</th>
				    	<th>지사명</th>
				    	<th>사용자권한</th>
			        </tr>
			        <tr class="pointer" onclick="movePage('/sytm/userAuthMgrDetail.do', '');">
			            <td class="txtc">20160301</td>
			            <td class="txtc">홍길동</td> 
			            <td >전략실</td>
			            <td class="txtc">본사</td>  
						<td  >전체사용자</td>
			        </tr>
			        <tr>
			            <td class=" txtc">20070305</td>
			            <td class=" txtc">정준하</td> 
			            <td >전략사업팀</td> 
						<td class=" txtc" >본사</td>
						<td  >계약담당자</td>
			        </tr>
			        <tr>
			            <td class="txtc">20101201</td>
			            <td class="txtc">양세형</td> 
			            <td >사업팀</td> 
			            <td class="txtc">본사</td> 
						<td  >검수담당자</td>
			         </tr> 
			         <tr> 
			            <td class=" txtc">20051001</td>
			            <td class=" txtc">유재석</td>  
			            <td >솔루션사업팀</td> 
						<td class=" txtc" >본사</td>  
						<td  >시스템관리자</td>  
			         </tr>
			    </table>
			</div>
	    </div>
	</div>
</div> <!--// content E-->

<%-- DETAIL FORM --%> 
<form id="detailFrm" method="POST" > 
	<input type="hidden" name="resourceName" value="${ param.resourceName }" >
</form>
