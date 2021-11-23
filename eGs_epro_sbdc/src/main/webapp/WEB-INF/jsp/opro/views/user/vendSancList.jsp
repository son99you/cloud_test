<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
마이페이지 > 본사정보관리 페이지
 *
 * <pre>
 * user 
 *   	 |_ vendorInfoMgr.jsp
 * </pre>
 * @date : 2017. 06. 22.
 * @version : 1.0
 * @author : 은우소프트 홍찬일
--%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %>


<script type="text/javascript" src="${jsPath}/comm/comUtil.js"></script>  
<script type="text/javascript" src="${jsPath}/opro/views/user/vendorInfoMgr.js"></script>  

<ul class="step_wrap">
	<li><a href="#">마이페이지</a></li>
	<li><a href="#">부정당업체등록정보</a></li>
</ul> <!--// step_wrap E -->
<div class="tit_wrap">
	<h3 class="tit">부정당업체등록정보</h3>
</div> 
				<div class="view_wrap typeA">
					<div class="view_area">
						<table>
							<colgroup>												
								<col width="15%">
								<col width="35%">
								<col width="15%">
								<col width="35%">
							</colgroup>
							
							<tr height="24">
								<th>업체명</th>
								<td>
									<input type="text" value="">
								</td>
								<th>사업자번호</th>
								<td>
									<input type="text" value="">
								</td>
							</tr>
							<tr height="24">
								<th>제재일자</th>
								<td>
									<input type="text" style="width: 40%;" value="" class="datepicker1">
									~
									<input type="text" style="width: 40%;" value="" class="datepicker2">
								</td>
								<th>제재구분</th>
								<td>
									<div class="w170">
										<select>
											<option>전체</option>
											<option>경고</option>
											<option>입찰참가정지</option>
											<option>로그인금지</option>
										</select>
									</div>									
								</td>
							</tr>
						</table>
					</div>
				</div>
				
				<div class="btn_wrap mt10">
					<button type="button" class="btn btn_s2 btn_sch" id="searchBtn">조회</button>
				</div> <!--// btn_wrap E -->
				
				<div class="list_wrap mt30 contentWrap">
					<div class="list_top">
						<p class="total">총 <span>2</span>건</p>
						<div class="btn_right">
							<button type="button" class="btn btn_s2 btn_down" id="excelBtn">엑셀 다운로드</button>
						</div> 
					</div> <!--// list_top E -->
					<div class="list_conts">			
						<table>
			           		<colgroup>
								<col width="5%" >
								<col width="10%">
								<col width="12%">  
								<col width="10%" >
								<col width="10%" >
								<col width="10%" >
								<col width="*" >
								<col width="8%" >
							</colgroup>
							<thead>
				                <tr>
									<th scope="col">No</th>
									<th scope="col">제재일자</th>
									<th scope="col">업체명</th>
									<th scope="col">제재상태</th>
									<th scope="col">시작일자</th>
									<th scope="col">졸요일자</th>
									<th scope="col">제재사유</th>
									<th scope="col">처리자</th>
				                </tr>
				            </thead>
							<tbody>
								<tr>
									<td>1</td>
									<td>2018-09-07</td>
									<td>은우소프트</td>
									<td>경고</td>
									<td></td>
									<td></td>
									<td class="txtl pl5">계약 불이행</td>
									<td>홍찬일</td>
								</tr>
								<tr>
									<td>2</td>
									<td>2018-09-06</td>
									<td>테스트업체</td>
									<td>입찰참가정지</td>
									<td>2018-09-01</td>
									<td>2018-09-10</td>
									<td class="txtl pl5">자동포기로 인한 입찰참가정지 사유발생</td>
									<td>홍찬일</td>
								</tr>
							</tbody>
						</table>
					</div>
					<div class="list_bottom">
						<comTag:pagingIpro totalCount="${vendMngeListTotcnt}" pageNo="${comFn:nvl(param.P_PAGE_NO, 1)}" pageSize="${comFn:nvl(param.P_PAGE_SIZE, 10)}" clickPage="pageObj.clickPage"/>
					</div> <!--// list_bottom E -->				
				</div>				
				
			</fieldset>
		</form>
	</div>
</div>
 
  
<%-- DETAIL FORM --%>
<form id="popupFrm" method="POST" > 
	<input type="hidden" name="resourceName" value="${ param.resourceName }" >
</form>
<form id="listFrm" method="POST" >  
	<input type="hidden" name="resourceName" value="${ param.resourceName }" >
</form>
<form id="itemListPopupFrm" method="POST">
	<input type="hidden" name="resourceName" value="${param.resourceName}">
	<input type="hidden" name="resourceDesc" value="${param.resourceDesc}">
</form> 
<form id="downloadFrm" method="POST" > 
	<input type="hidden" name="resourceName" value="${ param.resourceName }" >
	<input type="hidden" name="P_ATCHMNFL_SN">
</form>
<form id="zipPopupFrm" method="POST">
	<input type="hidden" name="resourceName" value="${param.resourceName}">
	<input type="hidden" name="resourceDesc" value="${param.resourceDesc}">
</form>