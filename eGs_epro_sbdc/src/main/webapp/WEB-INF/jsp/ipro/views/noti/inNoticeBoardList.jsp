<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 내부 공지사항 목록 페이지
 *
 * <pre>
 * noti 
 *    |_ inNoticeBoardList.jsp
 * 
 * </pre>
 * @date : 2017. 06. 14
 * @version : 1.0
 * @author : 은우소프트 이주연
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %> 
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %> 

<script type="text/javascript" src="${jsPath}/ipro/views/noti/inNoticeBoardList.js"></script> 

<div class="contents_wrap">
	<ul class="step_wrap">
		<li><a href="#">${myMenuList.bigMenuNm}</a></li>
		<li><a href="#">${myMenuSubList.smallMenuNm}</a></li>
	</ul>
	<div class="tit_wrap">
		<h3 class="tit">내부공지사항 목록</h3>
	</div>

	<form id="biSearchForm" name="biSearchForm" method="post">
		<input type="hidden" id="resourceName" name="resourceName" value="${param.resourceName}">
		<input type="hidden" id="resourceDesc" name="resourceDesc" value="${param.resourceDesc}">
		<input type="hidden" id="P_PAGE_NO" name="P_PAGE_NO" value="${comFn:nvl(param.P_PAGE_NO, 1)}">
		<input type="hidden" id="P_PAGE_SIZE" name="P_PAGE_SIZE" value="${comFn:nvl(param.P_PAGE_SIZE, 10)}">
		<input type="hidden" name="P_USER_ID" value="${sessionScope.loginResult.USER_ID}" >
		
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
						<tr height="24px">
							<th>
								제목명
							</th>
							<td colspan="3">
								<input type="text" name="P_SJ_NM_S"  id="P_SJ_NM_S" value="${param.P_SJ_NM_S}" onKeyDown="enter(event);" class="w_95p" />
							</td>
						</tr>
					</table>				
				</div>
			</div>
			
			<div class="btn_wrap mt10">
				<button type="button" class="btn btn_m btn_c2" id="searchBtn">조회</button>
			</div> <!--// btn_wrap E -->
			
			<div class="list_wrap mt30" id="contentWrap">
				<div class="list_top">
					<p class="total">총 <span>${comFn:nvl(inNoticeBoardListTotCnt, 0)}</span>건</p>		
					<!--  <div class="btn_right"> -->
						<!-- <button type="button" class="btn btn_02 btn_down">엑셀 다운로드</button> -->
					<!-- </div> -->
				</div> <!--// list_top E -->
				<div class="list_conts">		
					<table>
						<caption>내부공지사항 목록</caption>
			            <colgroup>
			                  <col width="40px"/>
			                  <col width="450px"/>
			                  <col width="100px"/>
			                  <col width="110px"/>
			                  <col width="80px"/>	
			           	</colgroup>
			           	<thead>
					    	<tr>
					    		<th>No</th>
			                    <th>제목</th>
			                    <th>등록자</th>
			                    <th>등록일자</th>
			                    <th>첨부파일</th>
					        </tr>
			           	</thead>
			           	<tbody>
							<c:if test="${comFn:nvl(inNoticeBoardListTotCnt, 0) == 0}">
								<tr>
									<td colspan="5" class="txt-center">검색 조건에 해당하는 데이터가 존재하지 않습니다.</td>
								</tr> 
							</c:if>  
							<c:if test="${inNoticeBoardListTotCnt > 0}">
								<c:forEach var="data" items="${inNoticeBoardList}" varStatus="status" > 
									<tr class="row" onclick="detailInqire('${data.NTT_SN}');" style="cursor: pointer;">
										<td>${data.RNUM}</td>
										<td class="pl5 list_tit"><c:if test="${data.MURE_CD eq 'M'}"><font color="red">[필독]</font></c:if>${data.SJ_NM}</td>
										<td>${data.REGISTER_NM}</td>
										<td>${comFn:formatDate(data.REGIST_DT, 'yyyyMMddHHmmss', 'yyyy-MM-dd')}</td>
										<td  style="text-align: center;"> 
											<c:if test="${not empty data.ATCHMNFL_GROUP_NO and data.FILE_CNT > 0}">
												<img src="${imagePath}/comm/sub/icon_file.png" alt="첨부파일">					
											</c:if>
										</td> 
									</tr> 
								</c:forEach>
							</c:if>
			           	</tbody>
				    </table>
				</div>
				<div class="list_bottom">
					<comTag:pagingIpro totalCount="${noticeBoardListTotCnt}" pageNo="${comFn:nvl(param.P_PAGE_NO, 1)}" pageSize="${comFn:nvl(param.P_PAGE_SIZE, 10)}" clickPage="pageObj.clickPage"/>
					<div class="list_btn">
						<button type="button" class="btn btn_m btn_orange" id="registBtn">등록</button>
					</div> <!--// btn_wrap E -->
				</div> <!--// list_bottom E -->			
		    </div>				
			
		</fieldset>
	</form>
</div>

<%-- DETAIL FORM --%> 
<form id="detailFrm" class="search_form" method="POST" >
	<input type="hidden" name="resourceDesc" value="${ param.resourceDesc }" > 
	<input type="hidden" name="resourceName" value="${ param.resourceName }" >
	<input type="hidden" name="P_NTT_SN"  id="P_NTT_SN" value="" >
	<input type="hidden" name="contextPath" value="${contextPath}" >
	<input type="hidden" name="P_USER_ID" value="${loginResult.USER_ID}" >
</form>