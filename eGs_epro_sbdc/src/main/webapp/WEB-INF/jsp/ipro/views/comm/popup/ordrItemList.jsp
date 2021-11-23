<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">--%>
<%--
 * 공통 > 요구부서 조회(팝업)
 *
 * <pre>
 * comm 
 *  |_popup
 *   |_rqstDeptList.jsp
 * 
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %>

<link rel="stylesheet" type="text/css" href="${cssPath}/ipro/windowPop.css">
<link rel="stylesheet" type="text/css" href="${cssPath}/ipro/template/pagingPlace.css">

<script type="text/javascript" src="${jsPath}/ipro/views/comm/popup/ordrPopup.js"></script>

<div id="windowPopup" class="w_800">
	<h3 class="windowTitle">품목 목록</h3>
	<div class="formLayer">
		<form id="searchFrm" class="search_form" method="POST" >
		<br/><br/> &nbsp;
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
										<tr height="29px">
											<td>
												
												품목명
											</td>
											
												<input class="w_95p" type="text" size="20" maxlength="100">
											</td>
											<td>
												
												품목코드
											</td>
											
												<input class="w_95p" type="text" size="20" maxlength="100">
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
           
		<!-- Data Total Count -->
		<br />
	    <div class="tableComment">
	        <p class="list_count">총 <span>8</span>건</p>
	    </div>
	       
	    <!-- Data List -->
		<div class="list">
			<table>
	            <caption>품목 목록</caption>
	            <colgroup>
	                <col width="10%">
	                <col width="*">
	                <col width="15%">
	                <col width="8%">
	                <col width="12%">
	            </colgroup>			
				<thead>
	                <tr>
	                    <th>No</th>
	                    <th>품목명</th>
	                    <th>품목코드</th>
	                    <th>단위</th>
	                    <th>요구수량</th>
	                </tr>
	            </thead>
				<tbody>
					<tr class="select_tr" style="cursor:pointer;">
						<td class="txtc">1</td>
						<td class="txtc">업무용 노트북(intel i-7)</td>						
						<td class="txtc">83628</td>
						<td class="txtc">대</td>
						<td class="txtc">10</td>
					</tr>	
					<tr class="select_tr" style="cursor:pointer;">
						<td class=" txtc">2</td>
						<td class=" txtc">아날로그 멀티채널 오디오 입력 모듈</td>						
						<td class=" txtc">13723</td>
						<td class=" txtc">ea</td>
						<td class=" txtc">1</td>
					</tr>	
					<tr class="select_tr" style="cursor:pointer;">
						<td class="txtc">3</td>
						<td class="txtc">시험용 안드로이드 단말기(미가입 단말기)</td>						
						<td class="txtc">80890</td>
						<td class="txtc">대</td>
						<td class="txtc">5</td>
					</tr>	
					<tr class="select_tr" style="cursor:pointer;">
						<td class=" txtc">4</td>
						<td class=" txtc">카메라용 광각렌즈</td>						
						<td class=" txtc">25677</td>
						<td class=" txtc">ea</td>
						<td class=" txtc">30</td>
					</tr>	
					<tr class="select_tr" style="cursor:pointer;">
						<td class="txtc">5</td>
						<td class="txtc">기능 시연용 태블릿 PC</td>						
						<td class="txtc">89099</td>
						<td class="txtc">대</td>
						<td class="txtc">20</td>
					</tr>	
					<tr class="select_tr" style="cursor:pointer;">
						<td class=" txtc">6</td>
						<td class=" txtc">연구개발 소프트웨어 패키지</td>						
						<td class=" txtc">37480</td>
						<td class=" txtc">pack</td>
						<td class=" txtc">100</td>
					</tr>	
					<tr class="select_tr" style="cursor:pointer;">
						<td class="txtc">7</td>
						<td class="txtc">디지털 검연기(Fiber Twist chamber)</td>						
						<td class="txtc">56352</td>
						<td class="txtc">대</td>
						<td class="txtc">3</td>
					</tr>	
					<tr class="select_tr" style="cursor:pointer;">
						<td class=" txtc">8</td>
						<td class=" txtc">파이프 유량 조절 부품</td>						
						<td class=" txtc">30398</td>
						<td class=" txtc">box</td>
						<td class=" txtc">5</td>
					</tr>	
				</tbody> 
			</table>
			
			<!-- 페이징 -->
			<div class="paging_place">
				<div class="paging_wrap"><a href="#" class="pprev" title="맨앞으로" style="display: none;"><span style="display: none;">맨앞으로</span></a><a href="#" class="prev" title="앞으로"><span style="display: none;">앞으로</span></a>
					<span><a href="#" class="active" title="1">1</a></span>
					<a href="#" class="next" title="뒤로"><span style="display: none;">뒤로</span></a><a href="#" class="nnext" title="맨뒤로" style="display: none;"><span style="display: none;">맨뒤로</span></a>
				</div>
			</div>
			<!-- 페이징 끝 -->
		</div> 
		<br>
	    <div class="T_btnLayer fr">
			<button type="button" class="blueBtn L" id="closeBtn" onclick="window.close();">닫기</button>
	    </div>
	</div> 
</div>
