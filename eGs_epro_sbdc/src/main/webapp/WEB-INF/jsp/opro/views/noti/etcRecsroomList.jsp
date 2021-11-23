<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 예제
 *
 * <pre>
 * noti
 *    |_ gnrlNoticeListjsp
 * 
 * </pre>
 * @date : 2017. 06. 16.
 * @version : 1.0
 * @author : 은우소프트 이주연
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %>

<script type="text/javascript" src="${jsPath}/opro/views/noti/etcRecsroomList.js"></script>

<div class="contents_wrap">
	<ul class="step_wrap">
		<li><a href="#">${myMenuList.bigMenuNm}</a></li>
		<li><a href="#">${myMenuSubList.smallMenuNm}</a></li>
	</ul>
	<div class="tit_wrap">
		<h3 class="tit">자료실</h3>
	</div>
	  
	<form id="searchFrm" class="searchFrm" method="POST" action="${contextPath}/elbi/myBidPblancList.do">
		<input type="hidden" name="resourceName" value="${param.resourceName}">
		<input type="hidden" id="P_PAGE_NO" name="P_PAGE_NO" value="${comFn:nvl(param.P_PAGE_NO, 1)}">
		<input type="hidden" id="P_PAGE_SIZE" name="P_PAGE_SIZE" value="${comFn:nvl(param.P_PAGE_SIZE, 10)}">
		
		<fieldset>
			<div class="view_wrap typeA">	
	        	<div class="view_area">
					<table>
						<caption>입찰공고조회</caption>    
						<colgroup>
							<col style="width: 15%;">
							<col style="width: auto;">
						</colgroup>
						<tbody>
							<tr>
								<th scope="row">제목명</th>
								<td>
	               					<input type="text" class="lineTxt" id="P_SJ_NM_S" name="P_SJ_NM_S"  value="${param.P_SJ_NM_S}" maxlength="600">
								</td>
							</tr>
							<tr>
								<th scope="row">등록자</th>
								<td>
	               					<input type="text" class="lineTxt" id="P_REGISTER_NM_S" name="P_REGISTER_NM_S" value="${param.P_REGISTER_NM_S}" maxlength="600">
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
			
		 	<div class="btn_wrap mt10">
				<button type="button" class="btn btn_m btn_c2">조회</button>
			</div> <!--// btn_wrap E -->
			
			<div class="list_wrap mt30">
				<div class="list_top">
					<p class="total">총 <span>${ noticeBoardListTotCnt}</span>건</p>
				</div> <!--// list_top E -->
				<div class="list_conts">
					<table class="tableList" summary="자료실 목록입니다">
						<caption>자료실 목록</caption>
						<colgroup>
							<col width="40px"/>
							<col width="530px"/>
							<col width="100px"/>
							<col width="110px"/>
							<col width="80px"/>
						</colgroup>			
						<thead class="line">
			                <tr>
			                    <th class="noBg" scope="col">번호</th>
			                    <th scope="col">제목</th>
			                    <th scope="col">등록자</th>
			                    <th scope="col">등록일자</th>
			                    <th scope="col">첨부파일</th>
			                </tr>
			            </thead>
						<tbody>                         
							<c:if test="${comFn:nvl(noticeBoardListTotCnt, 0) == 0}">
								<tr class="row">
									<td colspan="5">검색 조건에 해당하는 데이터가 존재하지 않습니다.</td>
								</tr>
							</c:if>
							<c:if test="${noticeBoardListTotCnt > 0}">
								<c:forEach var="data" items="${noticeBoardList}" varStatus="status" >
									<tr class="row" onclick="detailInqire('${data.NTT_SN}');" style="cursor: pointer;">
										<td>${data.RNUM}</td>
										<td style="text-align: left;"><c:if test="${data.MURE_CD eq 'M' }"><font color="red">[필독]</font></c:if>${data.SJ_NM}</td>
										<td>${data.REGISTER_NM}</td>
										<td>${comFn:formatDate(data.REGIST_DT, 'yyyyMMddHHmmss', 'yyyy-MM-dd')}</td>
										<td>
											<c:if test="${not empty data.ATCHMNFL_GROUP_NO and data.FILE_CNT > 0}">
												<img src="${imagePath}/ipro/icon/ico_power.png" alt="첨부파일">					
											</c:if>
										</td>
									</tr> 
								</c:forEach>
							</c:if>
						</tbody> 
					</table>
				</div> <!--// list_conts E -->
				<div class="list_bottom">
					<div class="list_pager">
						<comTag:paging totalCount="${noticeBoardListTotCnt}" pageNo="${comFn:nvl(param.P_PAGE_NO, 1)}" pageSize="${comFn:nvl(param.P_PAGE_SIZE, 10)}" clickPage="pageObj.clickPage"/>
					</div> <!--// list_pager E -->
				</div> <!--// list_bottom E -->
			</div> <!--// list_wrap E -->
		</fieldset>	
	</form>
</div>	

<%--page move form --%>
<form id="detailFrm" class="search_form" method="POST" >
	<input type="hidden" name="contextPath" value="${contextPath}" >
	<input type="hidden" name="P_NTT_SN" >
	<input type="hidden" name="P_USER_ID" value="${loginResult.USER_ID}" >
	<input type="hidden" id="resourceName" name="resourceName" value="${param.resourceName}">
	<input type="hidden" id="resourceDesc" name="resourceDesc" value="${param.resourceDesc}">
</form>