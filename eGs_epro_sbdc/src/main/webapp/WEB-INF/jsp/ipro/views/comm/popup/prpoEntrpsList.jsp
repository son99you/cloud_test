<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">--%>
<%--
 * 공통 > 업체팝업 조회(팝업)
 *
 * <pre>
 * comm 
 *  |_popup
 *   |_ prpoEntrpsList.jsp
 * 
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %>

<%-- <link rel="stylesheet" type="text/css" href="${cssPath}/ipro/windowPop.css">
<link rel="stylesheet" type="text/css" href="${cssPath}/ipro/template/pagingPlace.css"> --%>

<script type="text/javascript" src="${jsPath}/ipro/views/comm/popup/prpoEntrpsList.js"></script>

<div id="windowPopup" class="pop_wrap">
	<div class="pop_header">
		<h1 class="tit">업체목록</h1>
	</div> <!--// pop_header E -->
	<!-- <h3 class="windowTitle">견적의뢰 업체목록</h3> -->
	<div class="pop_container" style="">
		<form id="searchFrm" class="search_form" method="POST" >
		<input type="hidden" id="ENTRPS_REGIST_NO_CHK_LIST" value="${ENTRPS_REGIST_NO_CHK_LIST }">
		<!-- <br/><br/> --> &nbsp;
		<div class="view_wrap typeC">
			<div class="view_area m0 typeB">
			<table>
				<colgroup>
					<col width="15%" align="left">
					<col width="35%" align="left">
					<col width="15%" align="left">
					<col width="35%" align="left">
				</colgroup>
				<tr height="29px">
					<td>
						업체명
					</td>
					<td  colspan="3">
						<input type="text" style="width: 200px" name="P_ENTRPS_NM_S" id="P_ENTRPS_NM_S" value="${param.P_ENTRPS_NM_S }">
					</td>
				</tr>
				<tr height="29px">
					<td>
						
						소싱그룹
					</td>
					<td colspan="3">
						<comTag:comCmcdCdValueComboBox name="P_SG_CODE_S" cdId="N00001" headerValue="전체" selectKey="${param.P_SG_CODE_S }"/>
					</td>
				</tr>
			</table>
			</div> <!--// view_area E -->
		</div> <!--// view_wrap E -->
			
			<%-- <table class="contable2">
				<tr>
					<td>
						<table class="contable">
							<tr>
								<td>
									<table>
										<colgroup>
											<col width="15%" align="left">
											<col width="*" align="left">
										</colgroup>
										<tr height="29px">
											<td>
												
												업체명
											</td>
											<td  colspan="5">
												<input type="text" style="width: 98%" name="P_ENTRPS_NM_S" id="P_ENTRPS_NM_S" value="${param.P_ENTRPS_NM_S }">
											</td>
										</tr>
										<tr height="29px">
											<td>
												
												소싱그룹
											</td>
											<td  colspan="5">
												<comTag:comCmcdCdValueComboBox name="P_SG_CODE_S" cdId="N00001" headerValue="전체" selectKey="${param.P_SG_CODE_S }"/>
											</td>
										</tr>
										<tr>
											<td class=" searchBtnTd" colspan="6">
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
			</div>
		</div> --%>
		</form>
       	
       	<div class="btn_wrap mt10">
			<button type="button" class="btn_p btn_p1 btn_lookup" id="searchBtn"><img src="${imagePath}/ipro/icon/btn_icon02.png" alt="">조회</button>
		</div> <!--// btn_wrap E -->
		
		<!-- Data Total Count -->
		<br />
		<div class="pop_list_wrap">
	       	<div class="" style="width: 450px;float:left;"><!-- memberList -->
				<div class="pop_list_top">
		    		<p class="popSubTitle" style="">견적업체<spanstyle="float: right; font-size: 12px;"> 총<span style=" color:#f7901e;">${entrpsInqireListTotcnt }</span>건</span></p>
		    	</div>
				<div class="pop_list_conts" style="width: 380px; height: 350px; overflow-y: scroll; overflow-x:hidden" >
					<table class="" id="table1" style="width: 380px;"  >
						<caption>평가위원 총괄표</caption>
		               		<colgroup>
			                   	<col width="10%"/>
			                   	<col width="45%"/>
			                   	<col width="45%"/>
			            	</colgroup>
			            <thead>
				    	<tr>
				    		<th><input type="checkbox" id="AllCbx" onclick="FwkCmmnUtil.setAllCheck('AllCbx','Cbx');"></th>
				    		<th>사업자번호</th>
				    		<th>업체명</th>
				    	</tr>
				    	</thead>
				    	<tbody>
				    	<c:forEach items="${ entrpsInqireList }" var="list" varStatus="loop">
				    		<tr class="pointer">
					    		<td class="txtc"><input type="checkbox" name="Cbx"></td><!--  style="border-left: 1px solid #d5ddfd" -->
					    		<td style="display: none;">${list.VEND_REG_NO }</td>
					    		<td class="txtc">${list.BIZRNO }</td>
					    		<td>${list.VEND_NM }</td>
					    		<td style="display: none;">${list.RPRS_NM }</td>
					    		<td style="display: none;">${list.EMAL_ADDR }</td>
					    		<td style="display: none;">${list.TEL_NO  }</td>
				    		</tr>
				    	</c:forEach>
				    	</tbody>
				    </table>
				</div>
			</div>
		
		<div class="btnAddSub" style="float:left;width: 50px; margin-top: 150px;">
			<button type="button" class="grayBtn ico pointer" id="addBtn"><img src="${imagePath}/ipro/icon/list_arrow_next.jpg" alt=""></button><br>
			<button type="button" class="grayBtn ico pointer" id="delBtn"><img src="${imagePath}/ipro/icon/list_arrow_prev.jpg" alt=""></button>
			<%-- <button type="button" class="btn btn_02 btn_sch" id="chargerBtn">
											<img src="${imagePath}/btn_form_search.png" alt="조회 버튼">
										</button>  --%>
			<!-- <a href="#" class="arrow" title="앞으로"><img src="/statics/images/ipro/icon/list_arrow_prev.jpg" alt="앞으로"></a>
			<a href="#" class="arrow" title="뒤로"><img src="/statics/images/ipro/icon/list_arrow_next.jpg" alt="뒤로"></a>
			-->
		</div>
		<div class="candidate" style="width: 400px;float:right;">
		<div class="popSubTitle" style="">지정업체</div>
			<div class="pop_list_conts" style="width: 380px; height: 350px; overflow-y: scroll; overflow-x:hidden">
				<table class="" id="table2" style="width: 380px;">
					<caption>지정업체</caption>
	               		<colgroup>
		                   	<col width="10%"/>
		                   	<col width="45%"/>
		                   	<col width="45%"/>
		            	</colgroup>
		            <thead>
			    	<tr>
			    		<th><input type="checkbox" id="AllCbx2" onclick="FwkCmmnUtil.setAllCheck('AllCbx2','Cbx2');"></th>
			    		<th>사업자번호</th>
			    		<th>업체명</th>
			    	</tr>
			    	</thead>
			    	<tbody>
			    	<tr id="hideTr" class="pointer"style="display: none;" onclick="check(this);">
			    		<td class="txtc" ><input type="checkbox" name="Cbx2Temp"onclick='event.cancelBubble=true;'></td><!-- style="border-left: 1px solid #d5ddfd" -->
			    		<td class="txtc"><input type="text" disabled="disabled" name="deptNo" style="width: 85%;"></td>
			    		<td class="txtc"><input type="text" disabled="disabled" name="deptNm" style="width: 85%;"></td>
			    		<td class="txtc" style="display: none;"><input type="text" disabled="disabled" name="deptCode" style="width: 85%;"></td>
			    		<td class="txtc" style="display: none;"><input type="text" disabled="disabled" name="userNm" style="width: 85%;"></td>
			    		<td class="txtc" style="display: none;"><input type="text" disabled="disabled" name="userEmail" style="width: 85%;"></td>
			    		<td class="txtc" style="display: none;"><input type="text" disabled="disabled" name="userTel" style="width: 85%;"></td>
			    	</tr>
			    	</tbody>
			    	<tbody id="showTr">
			    	</tbody>
			    </table>
			</div>
		</div>
		</div>
		</div> 
		<br><br>
		
		
	    <div class="btn_wrap view_btn" style="margin-top: 340px;">
	    <br><br>
			<button type="button" class="btn btn_02 btn_close" id="setBtn">추가</button>
			<button type="button" class="btn btn_02 btn_close" id="closeBtn" onclick="window.close();">닫기</button>
	    </div>
	</div> 
