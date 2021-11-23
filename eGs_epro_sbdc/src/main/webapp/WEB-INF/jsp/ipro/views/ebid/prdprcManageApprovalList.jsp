<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 예가현황 > 예가관리 목록
 *
 * <pre>
 * ebid 
 *    |_ prdprcManageList.jsp
 * 
 * </pre>
 * @date : 2017. 06. 20
 * @version : 1.0
 * @author : 은우소프트 전상훈
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %> 
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %> 

<script type="text/javascript" src="${jsPath}/ipro/views/ebid/prdprcManageList.js"></script> 

<div class="conts_wrap">
	<div class="inner">
		<div class="tit_wrap">
			<h3 class="tit">대리결재자지정 목록</h3>
			<ul class="step_wrap">
				<li><a href="#">${myMenuList.bigMenuNm}</a></li>
				<li><a href="#">${myMenuSubList.smallMenuNm}</a></li>
			</ul>			
		</div>
		<form id="searchFrm" method="post">
			<input type="hidden" name="resourceName" value="${param.resourceName}">
			<input type="hidden" id="P_PAGE_NO" name="P_PAGE_NO" value="${comFn:nvl(param.P_PAGE_NO, 1)}">
			<input type="hidden" id="P_PAGE_SIZE" name="P_PAGE_SIZE" value="${comFn:nvl(param.P_PAGE_SIZE, 10)}">
			
			<fieldset>
				<div class="view_wrap typeA">
					<div class="view_area">
						<table>
							<colgroup>
								<col width="15%" align="left">
								<col width="35%" align="left">
								<col width="15%" align="left">
								<col width="35%" align="left">
							</colgroup>
							<tr>
								<th>
									대리일자
								</th>
								<td>
						            <div class="calendar_box">
						            <label for="P_BEFFAT_PBLANC_BEGIN_DE_S" class="blind">사전공고시작일자</label>
					                    <input type="text" class="w120 datepicker1" id="P_BEFFAT_PBLANC_BEGIN_DE_S" name="P_BEFFAT_PBLANC_BEGIN_DE_S" maxlength="10" date value="${comFn:formatDate(P_BEFFAT_PBLANC_BEGIN_DE_S,'yyyyMMdd','yyyy-MM-dd')}">
					                <span class="wave"> &nbsp;~ &nbsp;</span>
						            <label for="P_BEFFAT_PBLANC_END_DE_S" class="blind">사전공고종료일자</label>
						            	<input type="text" class="w120 datepicker2" id="P_BEFFAT_PBLANC_END_DE_S" name="P_BEFFAT_PBLANC_END_DE_S" maxlength="10" date value="${comFn:formatDate(P_BEFFAT_PBLANC_END_DE_S,'yyyyMMdd','yyyy-MM-dd')}">
						            </div>												
								</td>
								<th>
									대리자
								</th>
								<td>
			                    	<label for="userNmclient" class="blind">의뢰자명</label>
			                    	<input type="text" class="disabled w170" id="userNmclient" name="P_CLIENT_NM" readonly="readonly"  >
			                    	<input type="hidden" id="userIdclient" name="P_CLIENT_ID">
			                    	<button type="button" class="btn btn_02 btn_sch vert" id="searchUserBtn1">검색</button>
								</td>
							</tr>
						</table> 				
					</div>
				</div>
				
				<div class="btn_wrap mt10">
					<button type="button" class="btn btn_03 btn_inquire" id="searchBtn">조회</button>
				</div> <!--// btn_wrap E -->
				
				<div class="list_wrap mt30" id="contentWrap">
					<div class="list_top">
						<p class="total">총1</span>건</p>		
						<!--  <div class="btn_right"> -->
							<!-- <button type="button" class="btn btn_02 btn_down">엑셀 다운로드</button> -->
						<!-- </div> -->
					</div> <!--// list_top E -->
					<div class="list_conts">
						<table>
			           		<colgroup>
								<col width="5%">
								<col width="10%">
								<col width="10%">
								<col width="18%">
								<col width="*">
								<col width="10%">
								<col width="10%">
							</colgroup>
			            	<thead>
						    	<tr>
						            <th>No</th>
						            <th>원결재자</th>
						            <th>대리결재자</th>
						            <th>대리기간</th>
						            <th>사유</th>
						            <th>등록자</th>
						            <th>등록일자</th>
						        </tr>
			            	</thead>
			            	<tbody>
			            		<tr>
			            			<td>1</td>
			            			<td>홍찬일</td>
			            			<td>이주연</td>
			            			<td>2018-09-01 ~ 2018-10-01</td>
			            			<td>해외출장</td>
			            			<td>이주연</td>
			            			<td>2018-09-01 10:00</td>
			            		</tr>
			            	</tbody>
					    </table>
					</div>
					<div class="list_bottom">
						<comTag:pagingIpro totalCount="${0}" pageNo="${comFn:nvl(param.P_PAGE_NO, 1)}" pageSize="${comFn:nvl(param.P_PAGE_SIZE, 10)}" clickPage="pageObj.clickPage"/>
						<div class="list_btn">
							<button type="button" class="btn btn_02 btn_blue" id="registBtn">신규등록</button>
						</div>	
					</div> <!--// list_bottom E -->		
			    </div>				
				
			</fieldset>
		</form>
	</div>
</div>

<%-- DETAIL FORM --%>
<form id="viewFrm" method="POST" action="">
	<input type="hidden" name="resourceName" value="${param.resourceName}">
	<input type="hidden" name="P_PBLANC_NO" >
	<input type="hidden" name="P_PBLANC_ODR" >
	<input type="hidden" name="P_PRVSTL_AT">
	<input type="hidden" name="P_BSIS_PRDPRC_STTUS_CD">
</form>
<form id="popupFrm" method="POST">
</form>