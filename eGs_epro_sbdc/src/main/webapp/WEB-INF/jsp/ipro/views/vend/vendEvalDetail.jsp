<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 평가종합관리 상세(신분당선 평가종합관리)
 *
 * <pre>
 * vend
 *    |_ vendEvalDetail.jsp
 * 
 * </pre>
 * @date : 2017. 06. 16
 * @version : 1.0
 * @author : 은우소프트 홍찬일
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %> 
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %> 

<script type="text/javascript" src="${jsPath}/ipro/views/vend/vendEvalDetail.js"></script> 
 
<div class="sp_sec">
	<div class="nav_sec">
		<ul id="breadcrumbs">
			<li class="home">홈</li>
			<li>${myMenuList.bigMenuNm}</li>
			<li>${myMenuSubList.smallMenuNm}</li> 
		</ul>
	</div><!--// nav_sec -->
	<h3 class="sp_tit">평가종합관리 상세</h3>
		
	<div class="sp_cont">
		<p class="spc_stit">기본정보</p>
		<table class="form_tb">
			<colgroup>
				<col width="15%">
				<col width="35%">
				<col width="15%">
				<col width="35%">
			</colgroup>
			<tr>
				<th>년도</th>
				<td>${vendEvalMasterDetail.DYYYY }년 ${vendEvalMasterDetail.EV_SEQ } 차</td>
				<th>평가명</th>
				<td>${vendEvalMasterDetail.EV_NAME }</td>
			</tr>
			<tr>
				<th>평가유형</th>
				<td>${vendEvalMasterDetail.EV_TYPE_NM }</td>
				<th>평가수행기간</th>
				<td>
					${comFn:formatDate(vendEvalMasterDetail.EXEC_DATE_F ,'yyyyMMdd','yyyy-MM-dd')} 
							~ 
					${comFn:formatDate(vendEvalMasterDetail.EXEC_DATE_T ,'yyyyMMdd','yyyy-MM-dd')} 
				</td>
			</tr>
			<tr>
				<th>평가실적기간</th>
				<td>
					${comFn:formatDate(vendEvalMasterDetail.WORK_DATE_F ,'yyyyMMdd','yyyy-MM-dd')} 
							~
					${comFn:formatDate(vendEvalMasterDetail.WORK_DATE_T ,'yyyyMMdd','yyyy-MM-dd')} 
				</td>
				<th>진행상태</th>
				<td>${vendEvalMasterDetail.EV_STATE_NM }</td>
			</tr>
		</table>

		<p class="spc_stit">평가항목</p>
		<table class="tb">
          	<colgroup>
				<col width="10%">
				<col width="30%">
				<col width="15%">  
				<col width="15%">
				<col width="15%">
				<col width="15%">
			</colgroup>
			<thead>
                <tr>
                   	<th>순번</th>
					<th>협력업체</th>
					<th>대표자</th>
					<th>소싱그룹</th>
					<th>총점</th>
					<th>평가대상</th>
                </tr>
            </thead>
			<tbody>
				<c:forEach items="${ vendEvalVendorList }" var="list" varStatus="loop">
					<tr>
						<td align="center" <c:if test="${ list.USE_YN eq 'Y'}"> onclick="fnDetailEvalView( '<c:out value='${ list.DYYYY }'/>', '<c:out value='${ list.EV_SEQ }'/>', '<c:out value='${ list.VENDOR_CODE }'/>', '<c:out value='${ list.SG_CODE}'/>', '<c:out value='${ list.ATCHMNFL_GROUP_NO}'/>'  )"</c:if> ><c:out value="${ loop.index +1 }"/></td>
						<td align="left" <c:if test="${ list.USE_YN eq 'Y'}"> onclick="fnDetailEvalView( '<c:out value='${ list.DYYYY }'/>', '<c:out value='${ list.EV_SEQ }'/>', '<c:out value='${ list.VENDOR_CODE }'/>', '<c:out value='${ list.SG_CODE}'/>', '<c:out value='${ list.ATCHMNFL_GROUP_NO}'/>' )" </c:if> ><a href="#"><c:out value="${ list.VENDOR_NAME}"/></a></td>
						<td><c:out value="${ list.PRES_NAME }"/></td>
						<td align="center"><c:out value="${ list.SG_NAME }"/></td>
						<td align="center"><c:out value="${ list.TOT_SCORE }"/></td>
						<td align="center">
							<c:if test="${ list.USE_YN eq 'Y' }"><input type="checkbox" <c:if test="${ list.USE_YN eq 'Y'}">checked</c:if> <c:if test="${ vendEvalMasterDetail.EV_STATE ne 'A' }"> onclick="return false;" </c:if> /></c:if>
							<c:if test="${ list.USE_YN ne 'Y' }">
								<input type="checkbox" name="ev_chk" id="ev_chk<c:out value="${ loop.index }"/>" 
									<c:if test="${ list.USE_YN eq 'Y'}">
										checked
									</c:if>   
									<c:if test="${ vendEvalMasterDetail.EV_STATE eq 'B' or vendEvalMasterDetail.EV_STATE eq 'C' or vendEvalMasterDetail.EV_STATE eq 'D2' }"> 
										onclick="return false;" 
									</c:if> 
								/>
								<input type="hidden" value="<c:out value="${ list.VENDOR_CODE }" />" />
								<input type="hidden" value="<c:out value="${ list.SG_CODE }" />" />
								<input type="hidden" value="<c:out value="${ list.SG_NAME }" />" />
							</c:if>	
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
			
		<div class="btm_btns">
			<c:if test="${ vendEvalMasterDetail.EV_STATE eq 'A' }">
				<button type="button" class="btn btn_02_auto btn_revise" onClick="fnProceed()">평가진행</button>
		  	</c:if>
		   	<c:if test="${ vendEvalMasterDetail.EV_STATE eq 'D1' }">
			  	<button type="button" class="btn btn_02_auto btn_revise" onClick="search()">하반기평가업체추가</button>
			  </c:if>
			  <c:if test="${ vendEvalMasterDetail.EV_STATE eq 'D1' }">
			  	<button type="button" class="btn btn_02_auto btn_revise" onClick="addSave()">하반기평가업체저장</button>
			  </c:if>
			  <c:if test="${ vendEvalMasterDetail.EV_STATE eq 'B'}">
				<button type="button" class="btn btn_02_auto btn_revise" onClick="fnComplate('D1','상반기현업평가완료')">상반기현업평가완료</button>
			  </c:if>
			  <c:if test="${ vendEvalMasterDetail.EV_STATE eq 'D1' }">
			  	<button type="button" class="btn btn_02_auto btn_revise" onClick="fnComplate('D2','하반기현업평가완료')">하반기현업평가완료</button>
			  </c:if>
			  <c:if test="${ vendEvalMasterDetail.EV_STATE eq 'D1' }">
				<button type="button" class="btn btn_02_auto btn_revise" onClick="fnComplate('B','상반기현업평가완료취소')">상반기현업평가완료취소</button>
			  </c:if>
			  <c:if test="${ vendEvalMasterDetail.EV_STATE eq 'D2' }">
				<button type="button" class="btn btn_02_auto btn_revise" onClick="fnComplate('D1','하반기현업평가완료취소')">하반기현업평가완료취소</button>
			  </c:if>
			  <c:if test="${ evalDeptYn eq 'N' }">
				<button type="button" class="btn btn_02_auto btn_revise" onClick="evalDelete()">삭제</button>
			  </c:if>
			  <c:if test="${ vendEvalMasterDetail.EV_STATE eq 'D2' }">
			 	 <button type="button" class="btn btn_02_auto btn_revise" onClick="fnComplate('C','평가완료')">평가완료</button>
			  </c:if>
			<button type="button" class="btn btn_02 btn_sch" id="listBtn">목록</button>
  		</div>
	</div>
</div>

<%-- DETAIL FORM --%>
<form id="detailFrm" method="POST"> 
	<input type="hidden" name="resourceName" value="${ param.resourceName }">
	<input type="hidden" name="dyyyy" value="${vendEvalMasterDetail.DYYYY }">
	<input type="hidden" name="ev_seq" value="${vendEvalMasterDetail.EV_SEQ }">
	<input type="hidden" name="istVenCode">
	<input type="hidden" name="istSgCode">
	<input type="hidden" name="istSgName">
	<input type="hidden" name="sg_code">
	<input type="hidden" name="P_ATCHMNFL_GROUP_NO">
	<input type="hidden" name="vendor_code">
	<input type="hidden" name="ev_state">
	<input type="hidden" name="gbn">
	<input type="hidden" name="work_date_f" value="${vendEvalMasterDetail.WORK_DATE_F }">
	<input type="hidden" name="work_date_t" value="${vendEvalMasterDetail.WORK_DATE_T }">
</form>