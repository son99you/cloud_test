<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 사용자별권한 상세 페이지
 *
 * <pre>
 *  sytm 
 *    |_ userAuthMgrDetail.jsp
 * 
 * </pre>
 * @date : 2017. 06. 15
 * @version : 1.0
 * @author : 은우소프트 이주연
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %> 
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %> 

<link rel="stylesheet" type="text/css" href="${cssPath}/ipro/bodyBasic.css">
<link rel="stylesheet" type="text/css" href="${cssPath}/ipro/template/buttonStyle.css">
<link rel="stylesheet" type="text/css" href="${cssPath}/ipro/template/pagingPlace.css">
<link rel="stylesheet" type="text/css" href="${cssPath}/ipro/template/calendar.css">

<script type="text/javascript" src="${jsPath}/ipro/views/sytm/userAuthMgrDetail.js"></script>  

<div class="content">
	<h3>사용자별 권한관리 상세</h3>
	<div class="conts_wrap">
		<div class="btn_wrap view_btn fr">
	    	<button type="button" class="btn btn_02 btn_revise" id="saveBtn" >저장</button>
	    	<button type="button" class="btn btn_02 btn_sch" id="listBtn" >목록</button>
	    </div>
		<div class="contentTitle" style="">사용자별 권한관리</div>
		<table>
	    	<colgroup>
	        	<col width="15%" />
	            <col width="35%" />
	            <col width="15%" />
	            <col width="35%" />
	        </colgroup>
    		<tr >
	        	<th>사번</th>
	            <td>20160301</td>
	            <th>사용자명</th>
	            <td>홍길동</td>
            </tr>
            <tr> 
            	<th>부서명</th> 
			 	<td colspan="3">전략실</td>
			</tr>
			<tr>
            	<th>직급명</th>
            	<td>부장</td>
            	<th>지사명</th>
            	<td>본사</td>
			</tr> 
			<tr>
            	<th>사용자권한</th>
            	<td colspan="3"> 
	            	<select name="">
		           		<option value="x">전체</option>
		           		<option value="0" selected>전체사용자</option>
	              		<option value="1">시스템관리자</option>
	              		<option value="2">구매담당자</option>
	              		<option value="3">계약담당자</option>
	              		<option value="4">구매요구자</option>
	              		<option value="5">예가담당자</option>
	              		<option value="6">검수담당자</option>
	              		<option value="7">실적증명발급담당자</option>
		       		</select>
				</td> 
    		</tr>
    		
	    </table>
	</div>
</div> <!--// content E-->

<form id="listFrm" method="POST" >  
	<input type="hidden" name="resourceName" value="${ param.resourceName }" >
</form>
<form id="saveFrm" method="POST" > 
	<input type="hidden" name="resourceName" value="${ param.resourceName }" >
</form>
