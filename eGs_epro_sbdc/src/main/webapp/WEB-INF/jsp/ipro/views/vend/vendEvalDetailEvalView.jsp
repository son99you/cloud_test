<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 평가종합관리 상세 -> 평가수행(신분당선 평가종합관리)
 *
 * <pre>
 * vend
 *    |_ vendEvalDetailEvalView.jsp
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

<script type="text/javascript" src="${jsPath}/ipro/views/vend/vendEvalDetailEvalView.js"></script> 
 
<div class="conts_wrap">
	<div class="inner">
		<div class="tit_wrap">
			<h3 class="tit">평가종합관리 평가수행</h3>
			<ul class="step_wrap">
				<li><a href="#">${bigMenuNm}</a></li>
				<li><a href="#">${smallMenuNm}</a></li>
			</ul> <!--// step_wrap E -->
		</div> <!--// tit_wrap E -->
		<form id="registFrm" method="post" action="/buyer/eval/evalDetailEvalView.do" enctype="multipart/form-data" >
			<input type="hidden" name="resourceName" value="${ param.resourceName }" >
			<input type="hidden" id="P_DELETE_FILE_SN" name="P_DELETE_FILE_SN">	
			<input type="hidden" name="dyyyy" value="<c:out value='${ dyyyy }'/>">
			<input type="hidden" name="ev_seq" value="<c:out value='${ ev_seq }'/>">
			<input type="hidden" name="P_ATCHMNFL_GROUP_NO" value="<c:out value='${ vendEvalVendorDetail.ATCHMNFL_GROUP_NO }'/>">
			<input type="hidden" name="vendor_code" value="<c:out value='${ vendor_code }'/>">
			<input type="hidden" name="sg_code" value="<c:out value='${ sg_code }'/>">
			<input type="hidden" name="dataListEvCodeLength" value="<c:out value='${dataListEvCodeLength }'/>">
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
							<th>
								년도
							</th>
							<td class="txtl">
								${vendEvalMasterDetail.DYYYY }년도 ${vendEvalMasterDetail.EV_SEQ }차
							</td>
							<th>
								평가명
							</th>
							<td class="txtl">
								${vendEvalMasterDetail.EV_NAME }
							</td>
						</tr>
						<tr height="24">
							<th>
								평가유형
							</th>
							<td class="txtl">
								${vendEvalMasterDetail.EV_TYPE_NM }
							</td>
							<th>
								평가수행기간
							</th>
							<td class="txtl">
								${comFn:formatDate(vendEvalMasterDetail.EXEC_DATE_F ,'yyyyMMdd','yyyy-MM-dd')} 
								~ 
								${comFn:formatDate(vendEvalMasterDetail.EXEC_DATE_T ,'yyyyMMdd','yyyy-MM-dd')} 
							</td>
						</tr>
						<tr height="24">
							<th>
								평가실적기간
							</th>
							<td class="txtl">
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
								협력업체
							</th>
							<td class="txtl">
								${vendEvalVendorDetail.VENDOR_NAME}
							</td>
							<th>
								대표자
							</th>
							<td class="txtl">
								${vendEvalVendorDetail.PRES_NAME}
							</td>
						</tr>
						<tr height="24">
							<th>
								소싱그룹
							</th>
							<td class="txtl">
								${vendEvalVendorDetail.SG_NAME}
							</td>
							<th class="txtl">
							</th>
							<td class="txtl">
							</td>
						</tr>
					</table>
				</div>
				<div class="view_area">
					<table class="tab_border"  id="codeTable" >
		           		<colgroup>
							<col width="15%">
							<col width="5%">  
							<col width="13%">
							<col width="5%">
							<col width="*">
							<col width="10%">
							<col width="10%">
							<col width="5%">
						</colgroup>
						<thead>
			                <tr class="tab_border">
		                    	<th class="txtc tab_border1">분야(가중치)</th>
								<th class="txtc tab_border1">평가점수</th>
								<th class="txtc tab_border1">평가항목</th>
								<th class="txtc tab_border1">배점</th>
								<th class="txtc tab_border1">설명</th>
								<th class="txtc tab_border1">세부평가기준</th>
								<th class="txtc tab_border1">실측값</th>
								<th class="txtc tab_border1">점수</th>
			                </tr>
			            </thead>
			            <tbody id="gSignFrame" class="grdtable">
		                    <c:if test="${ empty vendEvalVendorCodeList }">
							<tr class="itemstyle"><td colspan="9" align="center">평가항목이 없습니다.</td></tr>
							</c:if>
							<c:if test="${ not empty vendEvalVendorCodeList }">
								<c:forEach items="${ vendEvalVendorCodeList }"  var="dataList"   varStatus="loop">
									<c:if test="${ loop.index % 2 == 0 }"><c:set var="rowStyle" value="alterstyle" /></c:if>
									<c:if test="${ loop.index % 2 == 1 }"><c:set var="rowStyle" value="itemstyle" /></c:if>
									<tr >
										<td class="alterstyle tab_border1" style="padding-left: 2px; font-family: '굴림'; font-size: 12px;" align="left"><c:out value="${ dataList.EV_NAME1 }"/>(<c:out value="${ dataList.DIST_SCORE1 }"/>)</td>
										<td class="<c:out value="${ rowStyle }"/> tab_border1" style="padding-left: 2px; font-family: '굴림'; font-size: 12px;" align="left">&nbsp;</td>
										<td  class="<c:out value="${ rowStyle }"/> tab_border1"  style="padding-left: 2px; font-family: '굴림'; font-size: 12px;" align="left">
										    <c:out value="${ dataList.EV_NAME2 }"/>
										    <input type="hidden" name="ev_name1" value="<c:out value='${ dataList.EV_NAME1 }'/>">
										    <input type="hidden" name="ev_code1" value="<c:out value='${ dataList.EV_CODE1 }'/>">
										    <input type="hidden" name="ev_name2" value="<c:out value='${ dataList.EV_NAME2 }'/>">
										    <input type="hidden" name="ev_code2" value="<c:out value='${ dataList.EV_CODE2 }'/>">
										</td>
										<td class="<c:out value="${ rowStyle }"/> tab_border1" style="padding-right: 2px; font-family: '굴림'; font-size: 12px;" align="right"><c:out value="${ dataList.DIST_SCORE2 }"/></td>
										<td class="<c:out value="${ rowStyle }"/> tab_border1" style="padding-left: 2px; font-family: '굴림'; font-size: 12px;" align="left"><c:out escapeXml ="false" value="${ dataList.REMARK2 }"/></td>
										<td class="<c:out value="${ rowStyle }"/> tab_border1" style="padding-left: 2px; font-family: '굴림'; font-size: 12px;" align="left">
										  <c:forEach items="${ vendEvalEvYyyyCode3List }" var="list" varStatus="status">
										    <c:if test="${ dataList.SG_CODE eq list.SG_CODE and dataList.EV_CODE1 eq list.EV_CODE1 and dataList.EV_CODE2 eq list.EV_CODE2 }">
												<input type="radio" name="ev_code3_<c:out value="${ loop.index }" />"  onclick="setScore(this);" <c:if test="${ dataList.SCORE2 eq list.DIST_SCORE }">checked</c:if>  /><input type="hidden" value="${ list.DIST_SCORE }" />&nbsp;<c:out value="${ list.EV_NAME3 }"/><br>
										    </c:if>
										  </c:forEach>
										</td>
										<td class="<c:out value="${ rowStyle }"/> tab_border1" style="font-family: '굴림'; font-size: 12px;">
											<input type="text" name="value2" style="width: 90%;" value="<c:out value='${ dataList.VALUE2  }'/>" <c:if test="${ dataList.EV_GUBUN1 eq 'B' }">readonly</c:if>	 /> 
										</td>
										<td class="<c:out value="${ rowStyle }"/> tab_border1"  style="font-family: '굴림'; font-size: 12px;">
											<input type="text" name="score2" style="text-align: right; width: 60px;" value="<c:out value='${ dataList.SCORE2 }'/>"
													onkeyup ="evalScoreSum2('score2','tot_score'); setScoreAvg('<c:out value="${ dataList.DIST_SCORE2 }"/>', this);" 
													<c:if test="${ dataList.EV_GUBUN1 eq 'A' or dataList.EV_GUBUN1 eq 'B' or dataList.EV_GUBUN1 eq 'C' }">readonly</c:if>
													money />
											<input type="hidden" value="<c:out value="${ dataList.DIST_SCORE1 }"/>" />
											<input type="hidden" value="<c:out value="${ dataList.DIST_SCORE2 }"/>"/>
										</td>
									</tr>
								</c:forEach>
								</c:if>
							</tbody>
						<tfoot>
						    <tr>
						        <td class="titable_blue" style="background-color: #EFECF5; color: #333333; font-family: '굴림'; font-size: 12px; padding-right: 10px" colspan="2">
						        	합&nbsp;&nbsp;&nbsp;&nbsp;계 <input type="text" size="5" name="tot_score" style="text-align: right;" value="" readonly  money  class="w100">
						        </td>
						        <td  style="background-color: #EFECF5; font-family: '굴림'; font-size: 12px; padding-left: 5px" colspan="6"></td>
						    </tr>
					    </tfoot>
					</table>
				</div>

				<h4 class="tit">협력업체 첨부파일</h4>
				<div class="view_area">
					<table>
						<caption>첨부파일(물품규격서)</caption>
						<colgroup>
							<col style="width: 170px;">
							<col style="width: auto;">
						</colgroup>
						<tbody>
							<tr>
			               		<th scope="row" >협력업체 첨부파일</th>
								<td colspan="3"> 
									<span class="stD" style="height: 30px;">
			                    		<button type="button"  class="btn btn_02 btn_sch" id="fileBtn" style="float: right; margin-top: 3px;">추가</button> 
			                    	</span> 
			                    	<div id="fileRow" style="display: none; height: 30px; margin-top: 3px;">
			                    		<input type="file" name="" onchange="fileChange(this)" style="height: 24px; margin-top: 3px;" >
					                   	<span class="stD"> 
			                    			<button type="button" class="btn btn_02 btn_sch" style="display: inline-block;" onclick="rowDel(this)">삭제</button>
			                    		</span> 
			                    	</div>
			                    	<div id="viewFileDiv" style="line-height: 30px;"> 
				                   			<c:if test="${not empty atchFileList }">
						                   		<c:forEach items="${atchFileList }" var="data" varStatus="state">
						                   			<div style="height: 30px;"> 
						                   				<a href="javascript:download('${data.ATCHMNFL_SN}');" class="attfile">${data.ATCHMNFL_NM }</a>
						                   				<span class="stD"> 
							                   				<button type="button" class="btn btn_02 btn_sch" style="display: inline-block; margin-top: 3px;" onclick="fileDel(this,'${data.ATCHMNFL_SN }')">삭제</button>
							                   			</span>
						                    		</div>
						                    	</c:forEach>  
						                    </c:if>
				                    	</div> 
			                    	<div id="fileDiv" style="width: 96%; line-height: 30px; margin-top: 3px;">
			                    	</div>
			                   	</td>	
			            	</tr>
						</tbody>
					</table>
				</div>
		
				<h4 class="tit">유관부서 첨부파일</h4>
				<div class="view_area">
					<table>
		          		<colgroup>
							<col style="width: 5%;">
							<col style="width: 20%;">
							<col style="width: auto;">
						</colgroup>
						<thead>
			                <tr>
			                   	<th class="txtc">순번</th>
								<th class="txtc">부서명</th>
								<th class="txtc">파일명</th>
			                </tr>
			            </thead>
			            <tbody>
			            	<c:forEach items="${ vendorDeptList }" var="list" varStatus="loop">
			            		<c:set var="fileName" value="atchFileList${loop.count }" />
			            		<c:forEach items="${requestScope[fileName]}" var="data" varStatus="state">
			            			<c:set var="cnt" value="${cnt+1 }" />
										<tr>
											<td class="txtc">${cnt}</td>
											<td>${list.DEPT_NAME }</td>
											<td><a href="javascript:download('${data.ATCHMNFL_SN}');" class="attfile">${data.ATCHMNFL_NM }</a></td>
										</tr>
								</c:forEach>
							</c:forEach>
			            </tbody>
					</table>
				</div>
				<div class="btn_wrap view_btn">
					<c:if test="${ vendEvalMasterDetail.EV_STATE eq 'B' or vendEvalMasterDetail.EV_STATE eq 'D1' }">
			  			<button type="button" class="btn btn_02 btn_revise" id="saveBtn1">저장</button>
			  		</c:if>
			  		<button type="button" class="btn btn_02 btn_sch" id="listBtn1">취소</button>
		   		</div>
	   		</div>	
		</form>
	</div>
</div>

<%-- DETAIL FORM --%>
<form id="detailFrm" method="POST" > 
	<input type="hidden" name="resourceName" value="${ param.resourceName }" >
	<input type="hidden" name="dyyyy" value="<c:out value='${ dyyyy }'/>">
	<input type="hidden" name="ev_seq" value="<c:out value='${ ev_seq }'/>">
</form>
<form id="downloadFrm" method="POST" action="">
	<input type="hidden" id="P_ATCHMNFL_SN" name="P_ATCHMNFL_SN" value="">
</form>