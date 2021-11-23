<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 입찰현황 > 입찰대기현황 목록
 *
 * <pre>
 * ebid 
 *    |_ bidPlanRegstList.jsp
 * 
 * </pre>
 * @date : 2017. 06. 30
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

<script type="text/javascript" src="${jsPath}/ipro/views/ebid/bidPlanRegstList.js"></script> 
 
<div class="content">
	<h3>입찰대기현황 목록</h3> 
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
											<col width="130px" align="left">
											<col width="280px" align="left">
											<col width="130px" align="left">
											<col width="280px" align="left">
										</colgroup>
										<tr height="24px">
											<td>
												
												계약구분
											</td> 
											<td>
												<select>
													<option>전체</option>
													<option>물품</option>
													<option>공사</option>
													<option>용역</option>
												</select>
											</td>
											<td>
												
												요구번호
											</td>
											<td>
												<input type="text" />
											</td>
										</tr>
										<tr height="24px">
											<td>
												
												계약명
											</td> 
											<td>
												<input type="text" />
											</td>
											<td>
												
												요구부서
											</td>
											<td>
												<input type="text" />
											</td>
										</tr>
										<tr>
											<td class=" searchBtnTd" colspan="4">
												<button type="button" class="grayBtn ico pointer" style="float: left;" id="setAllCheckOn">전체선택</button>
												<button type="button" class="grayBtn ico pointer"  style="float: left;" id="setAllCheckOff">전체해제</button>
												
												<label><input type="radio" name="radiogroup" checked="checked" class="vam mr5"  id="A" value="A">총액제</label>
								            	<label><input type="radio" name="radiogroup" class="vam mr5"  id="B"  value="B">품목별</label>
								            	<label><input type="radio"  name="radiogroup" class="vam mr5" id="C"  value="C">그룹별</label>
								            	
								            	<button type="button" class="grayBtn ico pointer" id="groupNewBtn">그룹생성</button>
								            	<button type="button" class="grayBtn ico pointer" id="groupCancleBtn">그룹취소</button>
								            	<button type="button" class="grayBtn ico pointer" id="registBtn">작성</button>
												<button type="button" class="grayBtn ico pointer" id="searchBtn">
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
					<caption>입찰대기현황 목록</caption>
	               		<colgroup>
		                   	<col width="5%"/>
		                   	<col width="7%"/>
		                   	<col width="10%"/>
		                   	<col width="*%"/>
		                   	<col width="5%"/>
		                   	<col width="20%"/>
		                   	<col width="10%"/>
		                   	<col width="7%"/>
		                   	<col width="7%"/>
		            	</colgroup>
			    	<tr>
			        	<th class="txtc">
			        		<input type="checkbox" id="bidPlanAllCbx" >
			        	</th>
			        	<th>계약구분</th>
			            <th>요구번호</th>
			            <th>계약명</th>
			            <th>그룹</th>
			            <th>건명</th>
			            <th>추정금액</th>
			            <th>요구부서</th>
			            <th>요구자</th>
			        </tr>
			        <tr>
			        	<td class="txtc"><input type="checkbox" value="PO201706-006" name="bidPlanCbxTemp"  onclick="bidPlanCbx(this,0)"></td>
			        	<td class="txtc">물품</td>
			            <td class="txtc" >PO201706-006</td>
			            <td>EBS 프로그램 연장구매</td>
			            <td class="txtc" ><input type="hidden" name="PO201706-006" /><span></span></td>
			            <td>프로그램라이센스</td>
			            <td class="txtr">225,000,000</td>
			            <td class="txtc">운영지원팀</td>
			            <td class="txtc">홍길동</td>
			        </tr>
			        <tr >
			        	<td class=" txtc">
			        		<input type="checkbox"  value="PO201706-006"  name="bidPlanCbxTemp"  onclick="bidPlanCbx(this,0)">
			        	</td>
			            <td class=" txtc">물품</td>
			            <td class=" txtc">PO201706-006</td>
			            <td class=" ">EBS 프로그램 연장구매</td>
			            <td class=" txtc"><input type="hidden" name="PO201706-006" /><span></span></td>
			            <td class=" ">등기 비용</td>
			            <td class=" txtr">5,000</td>
			            <td class=" txtc">운영지원팀</td>
			            <td class=" txtc">홍길동</td>
			        </tr>
			        <tr>
			        	<td class="txtc"><input type="checkbox"  value="PO201706-005"  name="bidPlanCbxTemp" onclick="bidPlanCbx(this,1)"></td>
			        	<td class="txtc">용역</td>
			            <td class="txtc">PO201706-005</td>
			            <td>RFID 물품관리 유지보수</td>
			            <td class="txtc"><input type="hidden" name="PO201706-005" /><span></span></td>
			            <td>RFID 물품관리 유지보수</td>
			            <td class="txtr">7,300,000</td>
			            <td class="txtc">운영지원팀</td>
			            <td class="txtc">김영희</td>
			        </tr>
			        <tr >
			        	<td class=" txtc"><input type="checkbox"  value="PO201706-004" name="bidPlanCbxTemp" onclick="bidPlanCbx(this,0)"></td>
			            <td class=" txtc">물품</td>
			            <td class=" txtc">PO201706-004</td>
			            <td class=" ">업무용품구매</td>
			            <td class=" txtc"><input type="hidden" name="PO201706-004" /><span></span></td>
			            <td class=" ">볼펜</td>
			            <td class=" txtr">100,000</td>
			            <td class=" txtc">사업기획팀</td>
			            <td class=" txtc">이주연</td>
			        </tr>
			         <tr>
			        	<td class="txtc"><input type="checkbox"  value="PO201706-004" name="bidPlanCbxTemp" onclick="bidPlanCbx(this,0)"></td>
			        	<td class="txtc">물품</td>
			            <td class="txtc">PO201706-004</td>
			            <td>업무용품구매</td>
			            <td class="txtc"><input type="hidden" name="PO201706-004" /><span></span></td>
			            <td>연필꽂이</td>
			            <td class="txtr">150,000</td>
			            <td class="txtc">사업기획팀</td>
			            <td class="txtc">이주연</td>
			        </tr>
			        <tr >
			        	<td class=" txtc"><input type="checkbox" value="PO201706-004" name="bidPlanCbxTemp" onclick="bidPlanCbx(this,0)"></td>
			            <td class=" txtc">물품</td>
			            <td class=" txtc">PO201706-004</td>
			            <td class=" ">업무용품구매</td>
			            <td class=" txtc"><input type="hidden" name="PO201706-004" /><span></span></td>
			            <td class=" ">모니터받침대</td>
			            <td class=" txtr">450,000</td>
			            <td class=" txtc">사업기획팀</td>
			            <td class=" txtc">이주연</td>
			        </tr>
			         <tr>
			        	<td class="txtc"><input type="checkbox" value="PO201706-004" name="bidPlanCbxTemp" onclick="bidPlanCbx(this,0)"></td>
			        	<td class="txtc">물품</td>
			            <td class="txtc">PO201706-004</td>
			            <td>업무용품구매</td>
			            <td class="txtc"><input type="hidden" name="PO201706-004" /><span></span></td>
			            <td>마우스패드</td>
			            <td class="txtr">120,000</td>
			            <td class="txtc">사업기획팀</td>
			            <td class="txtc">이주연</td>
			        </tr> 
			       <tr >
			        	<td class=" txtc"><input type="checkbox" value="PO201706-004" name="bidPlanCbxTemp" onclick="bidPlanCbx(this,0)"></td>
			            <td class=" txtc">물품</td>
			            <td class=" txtc">PO201706-004</td>
			            <td class=" ">업무용품구매</td>
			            <td class=" txtc"><input type="hidden" name="PO201706-004" /><span></span></td>
			            <td class=" ">키스킨</td>
			            <td class=" txtr">240,000</td>
			            <td class=" txtc">사업기획팀</td>
			            <td class=" txtc">이주연</td>
			        </tr>
			        <tr>
			        	<td class="txtc"><input type="checkbox"  value="PO201706-003" name="bidPlanCbxTemp" onclick="bidPlanCbx(this,2)"></td>
			        	<td class="txtc">공사</td>
			            <td class="txtc">PO201706-003</td> 
			            <td>파티션공사</td>
			            <td class="txtc"><input type="hidden" name="PO201706-003" /><span></span></td>
			            <td>파티션공사</td>
			            <td class="txtr">1,000,000</td>
			            <td class="txtc">경영지원실</td> 
			            <td class="txtc">김명수</td>  
			        </tr>
			    </table> 
			</div>
	    </div>
	    <div class="paging_place">
			<div class="paging_wrap"><a href="#" class="pprev" title="맨앞으로" style="display: none;"><span style="display: none;">맨앞으로</span></a><a href="#" class="prev" title="앞으로"><span style="display: none;">앞으로</span></a>
			<span><a href="#" class="active" title="1">1</a></span>
			<span><a href="#" title="2">2</a></span>
			<span><a href="#" title="3">3</a></span>
			<span><a href="#" title="4">4</a></span>
			<span><a href="#" title="5">5</a></span>
			<span><a href="#" title="6">6</a></span>
			<span><a href="#" title="7">7</a></span>
			<span><a href="#" title="8">8</a></span>
			<span><a href="#" title="9">9</a></span>
			<span><a href="#" title="10">10</a></span>
			<a href="#" class="next" title="뒤로"><span style="display: none;">뒤로</span></a><a href="#" class="nnext" title="맨뒤로" style="display: none;"><span style="display: none;">맨뒤로</span></a></div>
		</div>
	</div>

</div> <!--// content E-->
