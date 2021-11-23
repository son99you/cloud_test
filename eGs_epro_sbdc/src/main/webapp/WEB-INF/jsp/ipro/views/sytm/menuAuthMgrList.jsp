<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 권한별 메뉴 관리 페이지
 *
 * <pre>
 * sytm
 *    |_ menuAuthMgrList.jsp
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

<script type="text/javascript" src="${jsPath}/ipro/views/sytm/menuAuthMgrList.js"></script> 


<div class="content">
	<h3>권한별메뉴관리</h3>
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
													
													권한명
												</td>
												<td>
													<select >
														<option value="0">전체</option>
									              		<option value="1" >전체사용자</option>
									              		<option value="2">협력업체사용자</option>
									              		<option value="3">시스템관리자</option>
									              		<option value="4" selected>구매담당자</option>
									              		<option value="5">계약담당자</option>
									              		<option value="6">구매요구자</option>
									              		<option value="7">예가담당자</option>
									              		<option value="8">검수담당자</option>
									              		<option value="9">실적증명발급담당자</option>
									          		</select>
												</td>
												<td> 
													
													대분류명
												</td>
												<td>
													<select >
														<option value="0">전체</option>
									              		<option value="1" selected>구매요구</option>
									              		<option value="2">입찰관리</option>
									              		<option value="3">계약관리</option>
									              		<option value="4">발주관리</option>
									              		<option value="5">정산관리</option>
									              		<option value="6">실적증명</option>
									              		<option value="7">통계관리</option>
									              		<option value="8">알림마당</option>
									              		<option value="9">협력사관리</option>
									              		<option value="10">기준정보</option>
									              		<option value="11">시스템관리</option>
									          		</select>
												</td>
											</tr>
											<tr height="24px">
												<td class="" >
													
													메뉴명
												</td>	
												<td  colspan="3">
													<input type="text" onKeyDown="enter(event);" id="menuName"  class="w_95p"/> 
												</td>
											</tr>
											<tr>
												<td class=" searchBtnTd" colspan="4">
									            	<button type="button" class="blueBtn L" id="searchBtn">조회</button>
									            	<button type="button" class="blueBtn L" id="registBtn">저장</button>
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
					<caption>메뉴관리 목록</caption>
		             	<colgroup>
		                  <col width="10%"/>
		                  <col width="10%"/>
		                  <col width="15%"/>
		                  <col width="10%"/>
		                  <col width="10%"/>
		                  <col width="10%"/>
		                  <col width="10%"/>
		                  <col width="10%"/>
		           	</colgroup>
			    	<tr>
				    	<th>권한명</th> 
				    	<th>대분류명</th> 
				    	<th>메뉴명</th>
				    	<th>생성</th>
				    	<th>수정</th>
				    	<th>삭제</th>
				    	<th>조회</th>
				    	<th>출력</th>
			        </tr>
			         <tr>
			          	<td class="txtc" >구매담당자</td> 
			          	<td class="txtc" >구매요구</td> 
			            <td  >연간발주계획</td> 
			            <td class="txtc" >
			         		<input type="checkbox" name="pgmqryCheck" value="Y" class="checkbox" checked>
			            </td>
			            <td class="txtc" >
			         		<input type="checkbox" name="pgmqryCheck" value="Y" class="checkbox" checked>
			            </td>
			            <td class="txtc" >
			         		<input type="checkbox" name="pgmqryCheck" value="Y" class="checkbox" checked>
			            </td>
			            <td class="txtc" >
			         		<input type="checkbox" name="pgmqryCheck" value="Y" class="checkbox" checked>
			            </td>
						<td class="txtc" > 
							<input type="checkbox" name="pgmqryCheck" value="Y" class="checkbox" >
						</td>
			        </tr>
			        <tr>
			        	<td class=" txtc" >구매담당자</td> 
			          	<td class=" txtc" >구매요구</td> 
			            <td >물품구매요구서</td> 
			            <td class=" txtc" >
			         		<input type="checkbox" name="pgmqryCheck" value="Y" class="checkbox" >
			            </td>
			             <td class=" txtc" >
			         		<input type="checkbox" name="pgmqryCheck" value="Y" class="checkbox" >
			            </td>
			             <td class=" txtc" >
			         		<input type="checkbox" name="pgmqryCheck" value="Y" class="checkbox" >
			            </td>
			             <td class=" txtc" >
			         		<input type="checkbox" name="pgmqryCheck" value="Y" class="checkbox" >
			            </td>
						<td class=" txtc" > 
							<input type="checkbox" name="pgmqryCheck" value="Y" class="checkbox" >
						</td>
			        </tr>
			        <tr>
			        	<td class="txtc" >구매담당자</td> 
			          	<td class="txtc" >구매요구</td> 
			            <td >용역계약요구서</td>
			            <td class="txtc" >
			         		<input type="checkbox" name="pgmqryCheck" value="Y" class="checkbox" checked>
			            </td>
			             <td class="txtc" >
			         		<input type="checkbox" name="pgmqryCheck" value="Y" class="checkbox" checked>
			            </td>
			             <td class="txtc" >
			         		<input type="checkbox" name="pgmqryCheck" value="Y" class="checkbox" checked>
			            </td>
			             <td class="txtc" >
			         		<input type="checkbox" name="pgmqryCheck" value="Y" class="checkbox" checked>
			            </td>
						<td class="txtc" > 
							<input type="checkbox" name="pgmqryCheck" value="Y" class="checkbox" >
						</td>
			         </tr>
			         <tr>
			        	<td class=" txtc"  >구매담당자</td> 
			          	<td class=" txtc" >구매요구</td> 
			            <td >공사계약요구서</td> 
			            <td class=" txtc" >
			         		<input type="checkbox" name="pgmqryCheck" value="Y" class="checkbox" >
			            </td>
			            <td class=" txtc" >
			         		<input type="checkbox" name="pgmqryCheck" value="Y" class="checkbox" >
			            </td>
			              <td class=" txtc" >
			         		<input type="checkbox" name="pgmqryCheck" value="Y" class="checkbox" >
			            </td>
			              <td class=" txtc" >
			         		<input type="checkbox" name="pgmqryCheck" value="Y" class="checkbox" >
			            </td>
						<td class=" txtc" > 
							<input type="checkbox" name="pgmqryCheck" value="Y" class="checkbox" checked>
						</td>
			         </tr>
			         <tr>
			        	<td class="txtc" >구매담당자</td> 
			          	<td class="txtc" >구매요구</td> 
			            <td >구매요구현황</td> 
			            <td class="txtc" >
			         		<input type="checkbox" name="pgmqryCheck" value="Y" class="checkbox" >
			            </td>
			             <td class="txtc" >
			         		<input type="checkbox" name="pgmqryCheck" value="Y" class="checkbox" >
			            </td>
			             <td class="txtc" >
			         		<input type="checkbox" name="pgmqryCheck" value="Y" class="checkbox" >
			            </td>
			             <td class="txtc" >
			         		<input type="checkbox" name="pgmqryCheck" value="Y" class="checkbox" >
			            </td>
						<td class="txtc" > 
							<input type="checkbox" name="pgmqryCheck" value="Y" class="checkbox" checked>
						</td>
			         </tr>
			         <tr>
			         	<td class=" txtc" >구매담당자</td> 
			          	<td class=" txtc" >구매요구</td> 
			            <td >구매계약진행현황</td> 
			            <td class=" txtc" >
			         		<input type="checkbox" name="pgmqryCheck" value="Y" class="checkbox" >
			            </td>
			            <td class=" txtc" >
			         		<input type="checkbox" name="pgmqryCheck" value="Y" class="checkbox" >
			            </td>
			            <td class=" txtc" >
			         		<input type="checkbox" name="pgmqryCheck" value="Y" class="checkbox" >
			            </td>
			            <td class=" txtc" >
			         		<input type="checkbox" name="pgmqryCheck" value="Y" class="checkbox" >
			            </td>
						<td class=" txtc" > 
							<input type="checkbox" name="pgmqryCheck" value="Y" class="checkbox" >
						</td>
			         </tr> 
			         <tr> 
			         	<td class="txtc" >구매담당자</td> 
			          	<td class="txtc" >구매요구</td>
			            <td >구매요구접수</td> 
			            <td class="txtc" >
			         		<input type="checkbox" name="pgmqryCheck" value="Y" class="checkbox" >
			            </td>
			             <td class="txtc" >
			         		<input type="checkbox" name="pgmqryCheck" value="Y" class="checkbox" >
			            </td>
			             <td class="txtc" >
			         		<input type="checkbox" name="pgmqryCheck" value="Y" class="checkbox" >
			            </td>
			             <td class="txtc" >
			         		<input type="checkbox" name="pgmqryCheck" value="Y" class="checkbox" >
			            </td>
						<td class="txtc" > 
							<input type="checkbox" name="pgmqryCheck" value="Y" class="checkbox" checked>
						</td>  
			         </tr>
			         <tr>
			         	<td class=" txtc" >구매담당자</td> 
			          	<td class=" txtc" >구매요구</td>
			            <td >구매요구접수현황</td> 
			            <td class=" txtc" >
			         		<input type="checkbox" name="pgmqryCheck" value="Y" class="checkbox" checked>
			            </td>
			            <td class=" txtc" >
			         		<input type="checkbox" name="pgmqryCheck" value="Y" class="checkbox" checked>
			            </td>
			            <td class=" txtc" >
			         		<input type="checkbox" name="pgmqryCheck" value="Y" class="checkbox" checked>
			            </td>
			            <td class=" txtc" >
			         		<input type="checkbox" name="pgmqryCheck" value="Y" class="checkbox" checked>
			            </td>
						<td class=" txtc" > 
							<input type="checkbox" name="pgmqryCheck" value="Y" class="checkbox" checked>
						</td> 
			         </tr>
			         <tr>
			         	<td class=" txtc" >구매담당자</td> 
			          	<td class=" txtc" >구매요구</td>
			            <td >사전공고작성</td>
			            <td class="txtc" >
			         		<input type="checkbox" name="pgmqryCheck" value="Y" class="checkbox" checked>
			             </td>
			             <td class="txtc" >
			         		<input type="checkbox" name="pgmqryCheck" value="Y" class="checkbox" checked>
			             </td>
			             <td class="txtc" >
			         		<input type="checkbox" name="pgmqryCheck" value="Y" class="checkbox" checked>
			             </td>
			             <td class="txtc" >
			         		<input type="checkbox" name="pgmqryCheck" value="Y" class="checkbox" checked>
			             </td>
						 <td class="txtc" > 
							<input type="checkbox" name="pgmqryCheck" value="Y" class="checkbox" >
						 </td>
			         </tr>
			         <tr>
			        	<td class=" txtc" >구매담당자</td> 
			          	<td class=" txtc" >구매요구</td>
			            <td >사전공고진행현황</td> 
			            <td class=" txtc" >
			         		<input type="checkbox" name="pgmqryCheck" value="Y" class="checkbox" >
			            </td>
			            <td class=" txtc" >
			         		<input type="checkbox" name="pgmqryCheck" value="Y" class="checkbox" >
			            </td>
			            <td class=" txtc" >
			         		<input type="checkbox" name="pgmqryCheck" value="Y" class="checkbox" >
			            </td>
			            <td class=" txtc" >
			         		<input type="checkbox" name="pgmqryCheck" value="Y" class="checkbox" >
			            </td>
						<td class=" txtc" > 
							<input type="checkbox" name="pgmqryCheck" value="Y" class="checkbox" >
						</td>
			         </tr>
			    </table>
			</div>
	    </div> 
	    <br>
		<div class="T_btnLayer fr"> 
		       <button type="button" class="blueBtn L" id="listBtn" onclick="movePage('/sytm/authMgrList.do')">목록</button>
		</div>
	</div>
</div> <!--// content E-->

	<%-- DETAIL FORM --%> 
<form id="detailFrm" method="POST" > 
	<input type="hidden" name="resourceName" value="${ param.resourceName }" >
</form>
