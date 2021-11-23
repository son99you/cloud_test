<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 기술평가현황 > 상임위원 조회
 *
 * <pre>
 * ebid 
 *    |_ popup
              |_ mfcmmCndcySelectRegistForm.jsp
 * 
 * </pre>
 * @date : 2017. 06. 13
 * @version : 1.0
 * @author : 은우소프트 전상훈
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %> 
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %> 

<link rel="stylesheet" type="text/css" href="${cssPath}/ipro/template/pagingPlace.css">
<link rel="stylesheet" type="text/css" href="${cssPath}/ipro/windowPop.css">

<script type="text/javascript" src="${jsPath}/ipro/views/ebid/popup/mfcmmCndcySelectRegistForm.js"></script> 
 
<div id="windowPopup" class="w_1250">
	<div class="formLayer">
		<h3 class="windowTitle">상임위원 조회</h3>
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
													
													성명
												</td>
												<td>
													<input type="text"  size="20" name="" >
												</td>
												<td>
													
													부서명
												</td>
												<td>
													<input type="text"  size="20" name="" >
												</td>
											</tr>
											<tr height="24px">
												<td>
													
													평가횟수
												</td>
												<td>
													<input type="text"  size="20" name="" >
												</td>
												<td>
													
													근무부서키워드
												</td>
												<td>
													<input type="text"  size="20" name="" >
												</td>
											</tr>
											<tr>
												<td class=" searchBtnTd" colspan="4">
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
		<div class="tableComment">
	        <p class="list_count">총 <span>10</span>건</p>
	    </div>
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
												<col width="690px" align="left">
											</colgroup>
											<tr height="24px">
												<td>
													
													평가위원분야
												</td>
												<td>
													<input type="text" size="20" id="P_MFCMM_REALM_NM_S" >
													<font>(후보군으로 이동 시 반드시 평가위원 분야를 입력해야합니다.)</font>
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
		
		<div class="memberList">
			<div class="tableLayer">
				<div class="popSubTitle" style="">상임위원</div>
				<table class="table">
					<caption>평가위원 총괄표</caption>
	               		<colgroup>
		                   	<col width="5%"/>
		                   	<col width="10%"/>
		                   	<col width="12%"/>
		                   	<col width="8%"/>
		                   	<col width="12%"/>
		                   	<col width="10%"/>
		                   	<col width="*"/>
		                   	<col width="8%"/>
		                   	<col width="10%"/>
		            	</colgroup>
			    	<tr>
			    		<th><input type="checkbox" id="AllCbx" onclick="FwkCmmnUtil.setAllCheck('AllCbx','Cbx');"></th>
			    		<th>성명</th>
			    		<th>부서</th>
			    		<th>직책</th>
			    		<th>직전근무부서</th>
			    		<th>전화번호</th>
			    		<th>이메일</th>
			    		<th>평가횟수</th>
			    		<th>근무이력</th>
			    	</tr>
			    	<tr>
			    		<td class="txtc" style="border-left: 1px solid #d5ddfd"><input type="checkbox" name="Cbx"></td>
			    		<td class="txtc">홍길동</td>
			    		<td>개발구매팀</td>
			    		<td class="txtc">부장</td>
			    		<td>사업1팀</td>
			    		<td>010-1234-5678</td>
			    		<td>kdhong@mail.com</td>
			    		<td class="txtc">13</td>
			    		<td class="txtc">
			    			<button type="button" class="grayBtn ico pointer" name="popupBtn">
		                    	<img src="${imagePath}/ipro/icon/ico_search.png" alt="조회 버튼">
							</button>
						</td>
			    	</tr>
			    	<tr>
			    		<td class="txtc" style="border-left: 1px solid #d5ddfd"><input type="checkbox" name="Cbx"></td>
			    		<td class="txtc">고길동</td>
			    		<td>개발구매팀</td>
			    		<td class="txtc">대리</td>
			    		<td></td>
			    		<td>010-2345-6789</td>
			    		<td>kdgoo@mail.com</td>
			    		<td class="txtc">3</td>
			    		<td class="txtc">
			    			<button type="button" class="grayBtn ico pointer" name="popupBtn">
		                    	<img src="${imagePath}/ipro/icon/ico_search.png" alt="조회 버튼">
							</button>
						</td>
			    	</tr>
			    	<tr>
			    		<td class="txtc" style="border-left: 1px solid #d5ddfd"><input type="checkbox" name="Cbx"></td>
			    		<td class="txtc">김철수</td>
			    		<td>사업1팀</td>
			    		<td class="txtc">차장</td>
			    		<td>개발부매팀</td>
			    		<td>010-3456-7890</td>
			    		<td>cskim@mail.com</td>
			    		<td class="txtc">7</td>
			    		<td class="txtc">
			    			<button type="button" class="grayBtn ico pointer" name="popupBtn">
		                    	<img src="${imagePath}/ipro/icon/ico_search.png" alt="조회 버튼">
							</button>
						</td>
			    	</tr>
			    	<tr>
			    		<td class="txtc" style="border-left: 1px solid #d5ddfd"><input type="checkbox" name="Cbx"></td>
			    		<td class="txtc">이영희</td>
			    		<td>사업1팀</td>
			    		<td class="txtc">사원</td>
			    		<td></td>
			    		<td>010-4567-8901</td>
			    		<td>yhlee@mail.com</td>
			    		<td class="txtc">0</td>
			    		<td class="txtc">
			    			<button type="button" class="grayBtn ico pointer" name="popupBtn">
		                    	<img src="${imagePath}/ipro/icon/ico_search.png" alt="조회 버튼">
							</button>
						</td>
			    	</tr>
			    	<tr>
			    		<td class="txtc" style="border-left: 1px solid #d5ddfd"><input type="checkbox" name="Cbx"></td>
			    		<td class="txtc">성유리</td>
			    		<td>영업지원팀</td>
			    		<td class="txtc">부장</td>
			    		<td>사업2팀</td>
			    		<td>010-5678-9012</td>
			    		<td>ylsung@mail.com</td>
			    		<td class="txtc">10</td>
			    		<td class="txtc">
			    			<button type="button" class="grayBtn ico pointer" name="popupBtn">
		                    	<img src="${imagePath}/ipro/icon/ico_search.png" alt="조회 버튼">
							</button>
						</td>
			    	</tr>
			    	<tr>
			    		<td class="txtc" style="border-left: 1px solid #d5ddfd"><input type="checkbox" name="Cbx"></td>
			    		<td class="txtc">이지은</td>
			    		<td>영업지원팀</td>
			    		<td class="txtc">대리</td>
			    		<td></td>
			    		<td>010-6789-0123</td>
			    		<td>kdhong@mail.com</td>
			    		<td class="txtc">5</td>
			    		<td class="txtc">
			    			<button type="button" class="grayBtn ico pointer" name="popupBtn">
		                    	<img src="${imagePath}/ipro/icon/ico_search.png" alt="조회 버튼">
							</button>
						</td>
			    	</tr>
			    	<tr>
			    		<td class="txtc" style="border-left: 1px solid #d5ddfd"><input type="checkbox" name="Cbx"></td>
			    		<td class="txtc">마이콜</td>
			    		<td>사업2팀</td>
			    		<td class="txtc">과장</td>
			    		<td>영업지원팀</td>
			    		<td>010-7890-1234</td>
			    		<td>kdhong@mail.com</td>
			    		<td class="txtc">6</td>
			    		<td class="txtc">
			    			<button type="button" class="grayBtn ico pointer" name="popupBtn">
		                    	<img src="${imagePath}/ipro/icon/ico_search.png" alt="조회 버튼">
							</button>
						</td>
			    	</tr>
			    	<tr>
			    		<td class="txtc" style="border-left: 1px solid #d5ddfd"><input type="checkbox" name="Cbx"></td>
			    		<td class="txtc">사용자</td>
			    		<td>사업2팀</td>
			    		<td class="txtc">대리</td>
			    		<td></td>
			    		<td>010-8901-2345</td>
			    		<td>kdhong@mail.com</td>
			    		<td class="txtc">3</td>
			    		<td class="txtc">
			    			<button type="button" class="grayBtn ico pointer" name="popupBtn">
		                    	<img src="${imagePath}/ipro/icon/ico_search.png" alt="조회 버튼">
							</button>
						</td>
			    	</tr>
			    	<tr>
			    		<td class="txtc" style="border-left: 1px solid #d5ddfd"><input type="checkbox" name="Cbx"></td>
			    		<td class="txtc">안희근</td>
			    		<td>감리부</td>
			    		<td class="txtc">과장</td>
			    		<td></td>
			    		<td>010-9012-3456</td>
			    		<td>kdhong@mail.com</td>
			    		<td class="txtc">4</td>
			    		<td class="txtc">
			    			<button type="button" class="grayBtn ico pointer" name="popupBtn">
		                    	<img src="${imagePath}/ipro/icon/ico_search.png" alt="조회 버튼">
							</button>
						</td>
			    	</tr>
			    	<tr>
			    		<td class="txtc" style="border-left: 1px solid #d5ddfd"><input type="checkbox" name="Cbx"></td>
			    		<td class="txtc">이철희</td>
			    		<td>감리부</td>
			    		<td class="txtc">대리</td>
			    		<td></td>
			    		<td>010-1023-4567</td>
			    		<td>kdhong@mail.com</td>
			    		<td class="txtc">2</td>
			    		<td class="txtc">
			    			<button type="button" class="grayBtn ico pointer" name="popupBtn">
		                    	<img src="${imagePath}/ipro/icon/ico_search.png" alt="조회 버튼">
							</button>
						</td>
			    	</tr>
			    </table>
			    <div class="paging_place">
					<div class="paging_wrap"><a href="#" class="pprev" title="맨앞으로" style="display: none;"><span style="display: none;">맨앞으로</span></a><a href="#" class="prev" title="앞으로"><span style="display: none;">앞으로</span></a>
						<span><a href="#" class="active" title="1">1</a></span>
						<a href="#" class="next" title="뒤로"><span style="display: none;">뒤로</span></a><a href="#" class="nnext" title="맨뒤로" style="display: none;"><span style="display: none;">맨뒤로</span></a>
					</div>
				</div>
			</div>
		</div>
		
		<div class="btnAddSub">
			<button type="button" class="grayBtn ico pointer" id="addBtn">&gt;</button><br>
			<button type="button" class="grayBtn ico pointer" id="delBtn">&lt;</button>
		</div>
		
		<div class="candidate">
			<div class="tableLayer">
				<div class="popSubTitle" style="">후보군</div>
				<table class="table">
					<caption>평가위원 총괄표</caption>
	               		<colgroup>
		                   	<col width="15%"/>
		                   	<col width="40%"/>
		                   	<col width="*%"/>
		            	</colgroup>
			    	<tr>
			    		<th><input type="checkbox" id="AllCbx2" onclick="FwkCmmnUtil.setAllCheck('AllCbx2','Cbx2');"></th>
			    		<th>성명</th>
			    		<th>분야</th>
			    	</tr>
			    	<tr id="hideTr" style="display: none;">
			    		<td class="txtc" style="border-left: 1px solid #d5ddfd"><input type="checkbox" name="Cbx2Temp"></td>
			    		<td class="txtc"><input type="text" disabled="disabled" name="nm" style="width: 85%;"></td>
			    		<td class="txtc"><input type="text" disabled="disabled" name="bun" style="width: 85%;"></td>
			    	</tr>
			    	<tbody id="showTr">
			    	</tbody>
			    </table>
			</div>
		</div>
	</div>
	<div class="T_btnLayer fr top10">
		<button type="button" class="blueBtn L pointer" id="choiceBtn" >후보군선별</button>
    	<button type="button" class="blueBtn L pointer" id="closeBtn" >닫기</button>
    </div>
</div> <!--// content E-->
<form action="" id="popupFrm" method="POST"></form>
