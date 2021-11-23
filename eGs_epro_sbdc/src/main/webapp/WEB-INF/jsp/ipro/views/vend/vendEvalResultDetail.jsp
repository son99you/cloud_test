<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 평가결과 상세(신분당선 평가관리)
 *
 * <pre>
 * vend
 *    |_ vendEvalResultDetail.jsp
 * 
 * </pre>
 * @date : 2017. 06. 20
 * @version : 1.0
 * @author : 은우소프트 홍찬일
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %> 
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %> 

<script type="text/javascript" src="${jsPath}/ipro/views/vend/vendEvalResultDetail.js"></script> 
 
<div class="conts_wrap">
	<div class="inner">
		<div class="tit_wrap">
			<h3 class="tit">평가관리 상세</h3>
			<ul class="step_wrap">
				<li><a href="#">${bigMenuNm}</a></li>
				<li><a href="#">${smallMenuNm}</a></li>
			</ul> <!--// step_wrap E -->
		</div> <!--// tit_wrap E -->
		<div class="view_wrap typeB">
			<div class="view_area">
				<table>
					<colgroup>												
						<col style="width: 170px;">
						<col style="width: 349px;">
						<col style="width: 170px;">
						<col style="width: auto;">
					</colgroup>
				<tr height="24">
					<th>
						년도
					</th>
					<td>
						${vendEvalMasterDetail.DYYYY }년 ${vendEvalMasterDetail.EV_SEQ }차
					</td>
					<th>
						평가명
					</th>
					<td>
						${vendEvalMasterDetail.EV_NAME }
					</td>
				</tr>
				<tr height="24">
					<th>
						평가유형
					</th>
					<td>
						${vendEvalMasterDetail.EV_TYPE_NM }
					</td>
					<th>
						평가수행기간
					</th>
					<td>
						${comFn:formatDate(vendEvalMasterDetail.EXEC_DATE_F ,'yyyyMMdd','yyyy-MM-dd')} 
								~ 
						${comFn:formatDate(vendEvalMasterDetail.EXEC_DATE_T ,'yyyyMMdd','yyyy-MM-dd')} 
				</td>
				</tr>
				<tr height="24">
					<th>
						평가실적기간
					</th>
					<td>
						${comFn:formatDate(vendEvalMasterDetail.WORK_DATE_F ,'yyyyMMdd','yyyy-MM-dd')} 
								~
						${comFn:formatDate(vendEvalMasterDetail.WORK_DATE_T ,'yyyyMMdd','yyyy-MM-dd')} 
					</td>
					<th>
						진행상태
					</th>
					<td>
						${vendEvalMasterDetail.EV_STATE_NM }
					</td>
				</tr>
				<tr height="24">
						<th>
							비고
						</th>
						<td  colspan="3">
							<textarea rows="5" cols="" style="width: 100%; overflow: hidden;" readonly="readonly">${vendEvalMasterDetail.REMARK }</textarea> 
						</td>
					</tr>
				</table>
			</div>
	
			<div class="view_area">	
				<table>
					<colgroup>
						<col style="width: 40px;">
	                   	<col style="width: 150px;">
	                   	<col style="width: 80px;">
	                   	<col style="width: 70px;">
	                   	<col style="width: 70px;">
						<c:if test="${ not empty dataList_sub }">
						    <c:forEach items="${ dataList_sub }"  var="dataList_sub"   varStatus="loops">
						        <col style="width: 70px;">
	                   			<col style="width: 70px;">
	                   			<col style="width: 70px;">
						    </c:forEach>
						</c:if>
					</colgroup>
					<thead>
					<tr class="tab_border1">
						<th class="titable_blue tab_border1 txtc" rowspan="2"><font><b>순번</b></font></th>
						<th class="titable_blue tab_border1 txtc" rowspan="2"><font><b>협력업체</b></font></th>
						<th class="titable_blue tab_border1 txtc" rowspan="2"><font><b>소싱그룹</b></font></th>
						<th class="titable_blue tab_border1 txtc" rowspan="2"><font><b>등급</b></font></th>
						<th class="titable_blue tab_border1 txtc" rowspan="2"><font><b>총점</b></font></th>
			        	<c:if test="${ not empty dataList_sub }">
						    <c:forEach items="${ dataList_sub }"  var="dataList_sub"   varStatus="loops">
				        		<th class="titable_blue tab_border1 txtc" colspan="3"><font><b><c:out value="${ dataList_sub.EV_NAME1 }"/></b></font></th>
						    </c:forEach>
						</c:if>
					</tr>
					<tr class="tab_border1">	
						<c:forEach var="i" begin="0" end="${ dataList_sub_cnt }" step="1">
						    <th class="titable_blue tab_border1 txtc"><font><b>평<br/>가<br/>점<br/>수</b></font></th>
						    <th class="titable_blue tab_border1 txtc"><font><b>가<br/>중<br/>치</b></font></th>
						    <th class="titable_blue tab_border1 txtc"><font><b>결<br/>과</b></font></th>	  
						</c:forEach>
					</tr>
					</thead>
					<tbody id="gSignFrame" class="grdtable">
		                          
					<c:if test="${ not empty vendEvalVendorRsltList }">
						<c:forEach items="${ vendEvalVendorRsltList }"  var="dataList"   varStatus="loop">
						
						<c:if test="${ loop.index % 2 == 0 }"><c:set var="rowStyle" value="alterstyle" /></c:if>
						<c:if test="${ loop.index % 2 == 1 }"><c:set var="rowStyle" value="itemstyle" /></c:if>
						<tr id="tr_<c:out value="${ loop.count }"/>" class="<c:out value="${ rowStyle }"/> tab_border1" >
							<td  class="tab_border1" align="center"><c:out value="${ loop.index +1 }"/></td>
							<td  class="tab_border1" align="left"><c:out value="${ dataList.VENDOR_NAME }"/></td>
							<td  class="tab_border1" align="center"><c:out value="${ dataList.SG_NAME }"/></td>
							<td  class="tab_border1" align="center"><c:out value="${ dataList.GRADE }"/></td>
							<td class="tab_border1" align="right"><c:out value="${ dataList.TOT_RSLT_SCORE }"/></td>
							<c:forEach var="i" begin="0" end="${ dataList_sub_cnt }" step="1">
							    <c:set var="tempName">EVAL_SCORE${i}</c:set>  
							    <td class="tab_border1" style="padding-right: 2px;" align="right"><c:out value="${dataList[tempName]}"/></td>
							    <c:set var="tempName">DIST_SCORE${i}</c:set>  
							    <td class="tab_border1" style="padding-right: 2px; " align="right"><c:out value="${dataList[tempName]}"/></td>
							    <c:set var="tempName">RSLT_SCORE${i}</c:set>                                            
							    <td class="tab_border1"style="padding-right: 2px; " align="right"><c:out value="${dataList[tempName]}"/></td>
							</c:forEach>
						</tr>
						</c:forEach>
					</c:if>
					</tbody>
				</table>
			</div>
			<div class="btn_wrap view_btn fr">
<!-- 	  		<button type="button" class="btn btn_02 btn_revise" id="excelBtn">엑셀다운로드</button> -->
	  			<button type="button" class="btn btn_02 btn_sch" id="listBtn">목록</button>
   			</div>
		</div>
	</div>
</div>

<%-- DETAIL FORM --%>
<form id="detailFrm" method="POST" > 
	<input type="hidden" name="resourceName" value="${ param.resourceName }" >
</form>