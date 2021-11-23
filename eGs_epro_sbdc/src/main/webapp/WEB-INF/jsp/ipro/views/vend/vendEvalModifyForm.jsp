<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 평가종합관리 평가분야상세수정(신분당선 평가종합관리)
 *
 * <pre>
 * vend
 *    |_ vendEvalmodifyForm.jsp
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

<link rel="stylesheet" type="text/css" href="${cssPath}/ipro/bodyBasic.css">
<link rel="stylesheet" type="text/css" href="${cssPath}/ipro/template/buttonStyle.css">
<link rel="stylesheet" type="text/css" href="${cssPath}/ipro/template/pagingPlace.css">
<link rel="stylesheet" type="text/css" href="${cssPath}/ipro/template/calendar.css">
<link rel="stylesheet" type="text/css" href="${cssPath}/ipro/eval.css">

<script type="text/javascript" src="${jsPath}/ipro/views/vend/vendEvalModifyForm.js"></script> 
 
<div class="content">
		<h3>평가분야상세</h3>
		<div class="conts_wrap">
		<div class="btn_wrap view_btn fr">
	  		<button type="button" class="btn btn_02 btn_revise" id="saveBtn">저장</button>
	  		<button type="button" class="btn btn_02 btn_sch" id="listBtn">취소</button>
   		</div>	
   		<br>
		<form id="modifyFrm">
			<input type="hidden" name="dyyyy_hid" value="<c:out value='${ vendEvalMasterDetail.DYYYY }'/>">	
			<input type="hidden" name="ev_seq" value="<c:out value='${ vendEvalMasterDetail.EV_SEQ}'/>">
			<table>
				<colgroup>												
					<col width="15%" class="txtl">
					<col width="35%" class="txtl">
					<col width="15%" class="txtl">
					<col width="35%" class="txtl">
				</colgroup>
				<tr height="24">
					<th class=" txtl">
						<img src="${imagePath}/ipro/main/dot_blue.gif" width="11" height="11">
						<input type="hidden" id="dyyyy_sch_hid" value="">
						년도
					</th>
					<td>
						<select name="dyyyy" onchange="yyyySet(this);" ></select>
					</td>
					<th class=" txtl">
						<img src="${imagePath}/ipro/main/dot_blue.gif" width="11" height="11">
						평가명
					</th>
					<td>
						<input type="text" name="ev_name" value="${vendEvalMasterDetail.EV_NAME }" style="width: 98%;">
					</td>
				</tr>
				<tr height="24">
					<th class=" txtl">
						<img src="${imagePath}/ipro/main/dot_blue.gif" width="11" height="11">
						평가유형
					</th>
					<td>
						<comTag:comCmcdCdValueComboBox name="ev_type" cdId="N00005" headerValue="선택" selectKey="${vendEvalMasterDetail.EV_TYPE}"/>
					</td>
					<th class=" txtl">
						<img src="${imagePath}/ipro/main/dot_blue.gif" width="11" height="11">
						평가수행기간
					</th>
					<td>
						<div class="calendar_wrap">
						  	 <label for="exec_date_f" class="blind"></label>
						  	 <input type="text" style="width: 70%;" id="exec_date_f" name="exec_date_f" value="${vendEvalMasterDetail.EXEC_DATE_F }" date >
						</div>
						<span class="wave"> ~ </span>
						<div class="calendar_wrap">
						    <label for="exec_date_t" class="blind"></label>
						    <input type="text" style="width: 70%;" id="exec_date_t" name="exec_date_t" value="${vendEvalMasterDetail.EXEC_DATE_T }" date >
						</div>
					</td>
				</tr>
				<tr height="24">
					<th class=" txtl">
						<img src="${imagePath}/ipro/main/dot_blue.gif" width="11" height="11">
						평가실적기간
					</th>
					<td>
						<div class="calendar_wrap">
						  	 <label for="work_date_f" class="blind"></label>
						  	 <input type="text" style="width: 70%;" id="work_date_f" name="work_date_f" value="${vendEvalMasterDetail.WORK_DATE_F }" date >
						</div>
						<span class="wave"> ~ </span>
						<div class="calendar_wrap">
						    <label for="work_date_t" class="blind"></label>
						    <input type="text" style="width: 70%;" id="work_date_t" name="work_date_t" value="${vendEvalMasterDetail.WORK_DATE_T }" date >
						</div>
					</td>
					<th class=" txtc">
					</th>
					<td>
					</td>
				</tr>
				<tr height="24">
					<th class=" txtl">
						<img src="${imagePath}/ipro/main/dot_blue.gif" width="11" height="11">
						비고
					</th>
					<td  colspan="3">
						<textarea style="overflow: hidden; width: 100%; height: 70px;" name="remark">${vendEvalMasterDetail.REMARK }</textarea>
					</td>
				</tr>
			</table>
			<div class="contentTitle" style="">평가분야</div>
			<div id="tableDocs" style="margin-top: 30px; padding-left : 0px;">
				<c:forEach items="${ vendEvalCode1List }" var="sgCodeList" varStatus="status">
					<ul class="tableTr" style="list-style: none; width: 100%; clear: left; margin: 0px;">
						<li class="tableHTd" style="width: 15%; cursor: hand;" onclick="toggleTb(this);">
							<span>
								<c:if test="${status.count ne 1 }">
									▼
								</c:if>
								<c:if test="${status.count eq 1 }">
									▲
								</c:if>
							</span>
							<a>&nbsp;&nbsp;SG명</a>
						</li>
						<li class="tableHTd_none_l" style="width: 54%;">${sgCodeList.SG_NAME }</li>
						<li class="tableHTd_none_l" style="width: 15%;">총가중치</li>
						<li class="tableHTd_none_l" style="width: 15%;">
							<input type="text" name="total_score" value="0" size="4" readonly 
								   style="border: 0px; background-color: #f1f8fc; text-align: center; margin:4px;" />
						</li>
					</ul>
					<div style="width: 100%; <c:if test="${status.count ne 1 }">display: none;</c:if> ">
						<ul class="tableTr" style="list-style: none; width: 100%; clear: left; margin: 0px;">
							<li class="tableHTd_none_t" style="width: 15%;" onclick="toggleTb(this);">평가분야코드</li>
							<li class="tableHTd_none_l_t" style="width: 54%;">평가분야명</li>
							<li class="tableHTd_none_l_t" style="width: 15%;">평가구분</li>
							<li class="tableHTd_none_l_t" style="width: 15%;">가중치</li>
						</ul>
						<c:if test="${ not empty vendEvalSgCode1List }">
					  		<div id="evalField_${status.index }">
						  		<c:forEach items="${ vendEvalSgCode1List }" var="code1List" varStatus="loop">
						  			<c:if test="${ sgCodeList.SG_CODE eq code1List.SG_CODE }" >
										<ul class="tableTr" style="list-style: none; width: 100%; clear: left; margin: 0px;">
											<li class="tableBTd_none_t" style="width: 15%; text-align: center;">${code1List.EV_CODE1 }</li>
											<li class="tableBTd_none_l_t" style="width: 54%;">
												<div style="text-overflow:ellipsis; overflow:hidden; white-space:nowrap; width:523px">
													&nbsp;&nbsp;${code1List.EV_NAME1 }
												</div>
											</li>
											<li class="tableBTd_none_l_t" style="width: 15%;">
												<div style="text-overflow:ellipsis; overflow:hidden; white-space:nowrap; width:95px" title="현업평가">
													&nbsp;&nbsp;${code1List.EV_GUBUN1_NM }
												</div>
											</li>
											<li class="tableBTd_none_l_t" style="width: 15%; text-align: center;">
												<input type="hidden" name="ev_code1" value="<c:out value="${ code1List.EV_CODE1 }"/>" />
												<input type="hidden" name="sg_name" value="<c:out value="${ sgCodeList.SG_NAME }"/>" />
												<input type="hidden" name="sg_code" value="<c:out value="${ sgCodeList.SG_CODE }"/>" />
												<input type="text" name="dist_score" value="${ code1List.DIST_SCORE }" size="4" numeric 
													   style="IME-MODE: disabled; margin: 3px;" 
													   onkeyup="autoEvalSum(this, ${status.index });" 
													   onfocus="hideZero(this);" onblur="showZero(this);" />&nbsp;
											</li>
										</ul>
									</c:if>
								</c:forEach>
							</div>
						</c:if>
					</div>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				</c:forEach>
			</div>
		</form>
	</div>
</div>

<%-- DETAIL FORM --%>
<form id="modifyFrm" method="POST" >
	<input type="hidden" name="dyyyy" value="<c:out value='${ vendEvalMasterDetail.DYYYY }'/>">	
	<input type="hidden" name="ev_seq" value="<c:out value='${ vendEvalMasterDetail.EV_SEQ}'/>">
</form> 
<form id="detailFrm" method="POST" > 
	<input type="hidden" name="resourceName" value="${ param.resourceName }" >
	<input type="hidden" name="dyyyy" value="<c:out value='${ vendEvalMasterDetail.DYYYY }'/>">	
	<input type="hidden" name="ev_seq" value="<c:out value='${ vendEvalMasterDetail.EV_SEQ}'/>">
</form>