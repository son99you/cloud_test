<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 평가종합관리 신규등록(신분당선 평가종합관리)
 *
 * <pre>
 * vend
 *    |_ vendEvalReg.jsp
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

<script type="text/javascript" src="${jsPath}/ipro/views/vend/vendEvalRegForm.js"></script> 
 
<div class="conts_wrap">
	<div class="inner">
		<div class="tit_wrap">
			<h3 class="tit">평가신규등록</h3>
			<ul class="step_wrap">
				<li><a href="#">${bigMenuNm}</a></li>
				<li><a href="#">${smallMenuNm}</a></li>
			</ul> <!--// step_wrap E -->
		</div> <!--// tit_wrap E -->
   		<form id="registFrm">
			<div class="view_wrap typeA">
				<div class="view_area">
					<table>
						<colgroup>												
							<col style="width: 170px;">
							<col style="width: 349px;">
							<col style="width: 170px;">
							<col style="width: auto;">
						</colgroup>
						<tr height="24">
							<th class=" txtl">
								
								<input type="hidden" id="dyyyy_sch_hid" value="">
								년도
							</th>
							<td>
								<select name="dyyyy" onchange="yyyySet(this);" ></select>
							</td>
							<th class=" txtl">
								
								평가명
							</th>
							<td>
								<input type="text" name="ev_name" >
							</td>
						</tr>
						<tr height="24">
							<th class=" txtl">
								
								평가유형
							</th>
							<td>
								<comTag:comCmcdCdValueComboBox name="ev_type" cdId="N00005" headerValue="선택"/>
							</td>
							<th class=" txtl">
								
								평가수행기간
							</th>
							<td>
							  	 <label for="exec_date_f" class="blind"></label>
							  	 <input type="text" id="exec_date_f" name="exec_date_f" date class="w120">
								<span class="wave">&nbsp;~&nbsp;</span>
							    <label for="exec_date_t" class="blind"></label>
							    <input type="text" id="exec_date_t" name="exec_date_t"  date class="w120">
							</td>
						</tr>
						<tr height="24">
							<th class=" txtl">
								
								평가실적기간
							</th>
							<td>
							  	 <label for="work_date_f" class="blind"></label>
							  	 <input type="text" name="work_date_f" date class="w120">
								<span class="wave">&nbsp;~&nbsp;</span>
							    <label for="work_date_t" class="blind"></label>
							    <input type="text" name="work_date_t"  date class="w120">
							</td>
							<th class=" txtl">
							</th>
							<td>
							</td>
						</tr>
						<tr height="24">
							<th class=" txtl">
								
								비고
							</th>
							<td  colspan="3">
								<textarea style="overflow: hidden; width: 100%; height: 70px;" name="remark"></textarea>
							</td>
						</tr>
					</table>
				</div>
				
				<h4 class="tit">평가분야</h4>
				<div class="view_area">					
					<c:forEach items="${ vendEvalSgCodeList }" var="sgCodeList" varStatus="status">
						<table>
							<caption></caption>
							<colgroup>
								<col style="width: 15%;">
								<col style="width: auto;">
								<col style="width: 15%;">
								<col style="width: 15%;">
							</colgroup>
							<thead>
								<tr>
									<th class="txtc pointer" onclick="toggleTb(this,'tbody');">
										<c:if test="${status.count ne 1 }">▼</c:if>
										<c:if test="${status.count eq 1 }">▲</c:if>
										SG명
									</th>
									<th class="txtc">${sgCodeList.CD_VALUE_NM }</th>
									<th class="txtc">총가중치</th>
									<th class="txtc">
										<input type="text" name="total_score" value="0"  readonly="readonly" style="border: 0px; text-align: center;" />
									</th>
								</tr>
							</thead>
							<tbody <c:if test="${status.count ne 1 }">style="display: none;"</c:if>>
								<tr>
									<td class="txtc pointer" onclick="toggleTb(this,'tr');">평가분야코드</td>
									<td class="txtc">평가분야명</td>
									<td class="txtc">평가구분</td>
									<td class="txtc">가중치</td>
								</tr>
								<c:forEach items="${ vendEvalCode1List }" var="code1List" varStatus="loop">
									<tr class="js_tr">
										<td class="txtc">${code1List.EV_CODE1 }</td>
										<td class="txtl pr5">${code1List.EV_NAME1 }</td>
										<td class="txtl pr5">${code1List.EV_GUBUN1_NM }</td>
										<td class="txtc">
											<input type="hidden" name="ev_code1" value="<c:out value="${ code1List.EV_CODE1 }"/>" />
											<input type="hidden" name="sg_name" value="<c:out value="${ sgCodeList.CD_VALUE_NM }"/>" />
											<input type="hidden" name="sg_code" value="<c:out value="${ sgCodeList.CD_VALUE }"/>" />
											<input type="text" name="dist_score" value="0" size="4" numeric 
												   style="IME-MODE: disabled; margin: 3px;" 
												   onkeyup="autoEvalSum(this, ${status.index });" 
												   onfocus="hideZero(this);" onblur="showZero(this);" />
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</c:forEach>
				</div>
				<!-- /SlideToggle -->
				<div class="btn_wrap view_btn">
			  		<button type="button" class="btn btn_02 btn_revise" id="saveBtn1">저장</button>
			  		<button type="button" class="btn btn_02 btn_sch" id="listBtn1">취소</button>
		   		</div>	
			</div>
		</form>
	</div>
</div>

<%-- DETAIL FORM --%>
<form id="detailFrm" method="POST" > 
	<input type="hidden" name="resourceName" value="${ param.resourceName }" >
	<input type="hidden" name="dyyyy" value="">	
	<input type="hidden" name="ev_seq" value="">
</form>
<form id="registFrm" method="POST" >
	<input type="hidden" name="dyyyy" value="">	
	<input type="hidden" name="ev_seq" value="">
</form> 