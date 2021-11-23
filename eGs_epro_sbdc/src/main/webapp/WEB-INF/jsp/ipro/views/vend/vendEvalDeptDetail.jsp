<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 유관부서평가 상세(신분당선 유관부서평가)
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

<script type="text/javascript" src="${jsPath}/ipro/views/vend/vendEvalDeptDetail.js"></script> 
 
<div class="conts_wrap">
	<div class="inner">
		<div class="tit_wrap">
			<h3 class="tit">유관부서평가 상세</h3>
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
							${vendEvalMasterDetail.DYYYY }년도 ${vendEvalMasterDetail.EV_SEQ }차
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
				</table>
			</div>
			<h4 class="tit">평가업체</h4>
			<div class="view_area">
				<table>
	           		<colgroup>
						<col width="5%" >
						<col width="*" >
						<col width="15%">  
						<col width="15%">
						<col width="15%">
						<col width="15%">
						<col style="width: 5%;">
						<col style="width: auto;">
						<col style="width: 15%;">
						<col style="width: 15%;">
						<col style="width: 15%;">
						<col style="width: 15%;">
					</colgroup>
					<thead>
		                <tr>
	                    	<th class="txtc">순번</th>
							<th class="txtc">협력업체</th>
							<th class="txtc">대표자</th>
							<th class="txtc">소싱그룹</th>
							<th class="txtc">부서명</th>
							<th class="txtc">총점</th>
		                </tr>
		            </thead>
					<tbody>
						<c:forEach items="${ vendorDeptList }" var="list" varStatus="loop">
							<tr class="pointer" onclick="fnDetailView( '<c:out value='${ list.DYYYY }'/>', '<c:out value='${ list.EV_SEQ }'/>', '81','<c:out value='${ list.VENDOR_CODE }'/>', '<c:out value='${ list.SG_CODE }'/>', '<c:out value='${ list.DEPT_CODE }'/>', '<c:out value='${ list.ATCHMNFL_GROUP_NO }'/>' )">
								<td style="padding-left: 2px; font-family: '굴림'; font-size: 12px;" align="center"><c:out value="${ loop.index +1 }"/></td>
								<td style="padding-left: 2px; font-family: '굴림'; font-size: 12px;" align="left" ><c:out value="${ list.VENDOR_NAME}"/></td>
								<td style="padding-left: 2px; font-family: '굴림'; font-size: 12px;" align="left"><c:out value="${ list.PRES_NAME }"/></td>
								<td style="padding-left: 2px; font-family: '굴림'; font-size: 12px;" align="center"><c:out value="${ list.SG_NAME }"/></td>
								<td style="padding-right: 2px; font-family: '굴림'; font-size: 12px;" align="center"><c:out value="${ list.DEPT_NAME }"/></td>
								<td style="padding-left: 2px; font-family: '굴림'; font-size: 12px;" align="center"><c:out value="${ list.TOT_SCORE }"/></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
	   		</div>
	   		<div class="btn_wrap view_btn fr">
		  		<button type="button" class="btn btn_02 btn_sch" id="listBtn">목록</button>
	   		</div>	
		</div>
	</div>
</div>

<%-- DETAIL FORM --%>
<form id="detailFrm" method="POST" > 
	<input type="hidden" name="resourceName" value="${ param.resourceName }" >
	<input type="hidden" name="dyyyy" value=''>
	<input type="hidden" name="ev_seq" value=''>
	<input type="hidden" name= "gbn" value = "1">
	<input type="hidden" name="vendor_code" value="">
	<input type="hidden" name="sg_code" value="">
	<input type="hidden" name="dept_code" value="">
	<input type="hidden" name="orgId" value=''>
	<input type="hidden" name="P_ATCHMNFL_GROUP_NO" value="">
</form>