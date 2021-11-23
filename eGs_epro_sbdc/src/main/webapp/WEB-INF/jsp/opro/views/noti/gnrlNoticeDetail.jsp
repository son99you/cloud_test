<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 통합게시판 > 공지사항 상세
 *
 * <pre>
 * noti 
 *    |_ noticeDetail.jsp
 * 
 * </pre>
 * @date : 2017. 06. 16.
 * @version : 1.0
 * @author : 은우소프트 
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld"%>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<script type="text/javascript" src="${jsPath}/comm/comUtil.js"></script>
<script type="text/javascript" src="${jsPath}/opro/views/noti/gnrlNoticeDetail.js"></script>

<ul class="step_wrap">
	<li><a href="#">커뮤니터</a></li>
	<c:if test="${param.resourceName eq 'opm701'}">
		<li><a href="#">공지사항</a></li>
	</c:if>
	<c:if test="${param.resourceName eq 'opm702'}">
		<li><a href="#">입찰공지사항</a></li>
	</c:if>
</ul> <!--// step_wrap E -->
<div class="tit_wrap">
	<c:if test="${param.resourceName eq 'opm701'}">
		<h3 class="tit">공지사항 상세</h3>
	</c:if>
	<c:if test="${param.resourceName eq 'opm702'}">
		<h3 class="tit">입찰공지사항 상세</h3>
	</c:if>
</div> 
<div class="view_wrap typeB">
	<div class="tit_area">
		<h4 class="tit">공지사항 정보</h4>
	</div> <!--// tit_area E -->
	<div class="view_area">
		<table class="table">
			<caption>공지사항정보</caption>
			<colgroup>
				<col width="15%">
				<col width="35%">
				<col width="15%">
				<col width="35%">
			</colgroup>
			<tbody>
				<tr class="line">
					<th>제목</th>
					<td colspan="3"> ${noticeBoardDetail.SJ_NM}</td>
				</tr>
				<tr>
					<th>등록자</th>
					<td>${noticeBoardDetail.REGISTER_NM}</td>
					<th>이메일</th>
					<td>${noticeBoardDetail.EMAIL_ADRES}</td>
				</tr>
				<tr>
					<th scope="row">내용</th>
					<td colspan="3" style="min-height: 100px; vertical-align: top">
						 ${noticeBoardDetail.CN}
					</td>
				</tr>
			</tbody>
		</table>
	</div> <!--// view_area E -->
	<div class="tit_area">
		<h4 class="tit">첨부파일</h4>
	</div> <!--// tit_area E -->
	<div class="view_area">
		<table class="table" summary="첨부파일 입니다."> 
				<caption>첨부파일</caption>
				<colgroup>
					<col width="15%">
					<col width="35%">
					<col width="15%">
					<col width="35%">
				</colgroup>
				<tbody>
					 <tr>
                	<th scope="row" class="vtop">첨부파일</th>
					<td colspan="3" class="vtop"> 
						<c:if test="${not empty noticeFileList}">
							<c:forEach items="${noticeFileList}" var="data" varStatus="state"> 
								<div style="height: 30px;"> 
									<a href="javascript:download('${data.ATCHMNFL_SN}');" class="attfile">${data.ATCHMNFL_NM}</a>
								</div> 
							</c:forEach>   
						</c:if>  
                    </td>	 
	             </tr>
				</tbody>
			</table>
	</div> <!--// view_area E -->

        	
	<div class="btn_wrap view_btn">
            <button type="button" class="btn btn_m btn_del" id="listBtn">목록</button>
	</div> <!--// btn_wrap E -->
</div>     


<%--page move form --%>
<form id="listFrm" class="search_form" method="POST">
	<input type="hidden" id="resourceName" name="resourceName"
		value="${param.resourceName}">
</form>

<form id="downloadFrm" method="POST" > 
	<input type="hidden" name="P_ATCHMNFL_SN">  
	<input type="hidden" name="resourceDesc" value="${ param.resourceDesc }" >
	<input type="hidden" name="resourceName" value="${ param.resourceName }" >
</form>


