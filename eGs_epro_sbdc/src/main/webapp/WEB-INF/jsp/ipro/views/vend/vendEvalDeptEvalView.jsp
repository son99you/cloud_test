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

<script type="text/javascript" src="${jsPath}/ipro/views/vend/vendEvalDeptEvalView.js"></script> 

<div class="conts_wrap">
	<div class="inner">
		<div class="tit_wrap">
			<h3 class="tit">유관부서평가수행</h3>
			<ul class="step_wrap">
				<li><a href="#">${bigMenuNm}</a></li>
				<li><a href="#">${smallMenuNm}</a></li>
			</ul> <!--// step_wrap E -->
		</div> <!--// tit_wrap E -->
		
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
						<td>
							<c:out value="${ vendEvalMasterDetail.DYYYY }"/>년도&nbsp;&nbsp;<c:out value="${ vendEvalMasterDetail.EV_SEQ }"/>차
						</td>
						<th>
							평가명
						</th>
						<td>
							<c:out value="${ vendEvalMasterDetail.EV_NAME }"/>
						</td>
					</tr>
					<tr height="24">
						<th>
							평가유형
						</th>
						<td>
							<c:out value="${ vendEvalMasterDetail.EV_TYPE_NM }"/>
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
							<c:out value="${ vendEvalMasterDetail.EV_STATE_NM }"/>
						</td>
					</tr>
					<tr height="24">
						<th>
							협력업체
						</th>
						<td>
						    <c:out value="${ vendEvalVendorDeptDetail.VENDOR_NAME }"/>
						</td>
						<th>
							대표자
						</th>
						<td>
							<c:out value="${ vendEvalVendorDeptDetail.PRES_NAME }"/>
						</td>
					</tr>
					<tr height="24">
						<th>
							소싱그룹
						</th>
						<td>													
							<c:out value="${ vendEvalVendorDeptDetail.SG_NAME }"/> 
						</td>
						<th>
							부서명
						</th>
						<td>
							<c:out value="${ vendEvalVendorDeptDetail.DEPT_NAME }"/> 
						</td>
					</tr>
				</table>
			</div>
		
			<form id="registFrm" name="registFrm" method="post" action="/buyer/eval/evalDeptEvalView.do"  enctype="multipart/form-data" >
				<input type="hidden" name="resourceName" value="${ param.resourceName }" >
				<input type="hidden" name="pageNo" value="<c:out value='${ searchMap["pageNo"] }'/>">	
				<input type="hidden" id="P_DELETE_FILE_SN" name="P_DELETE_FILE_SN">	
				
				<input type="hidden" name="dyyyy" value="<c:out value='${ dyyyy }'/>">
				<input type="hidden" name="ev_seq" value="<c:out value='${ ev_seq }'/>">
				<input type="hidden" name="vendor_code" value="<c:out value='${ vendor_code }'/>">
				<input type="hidden" name="sg_code" value="<c:out value='${ sg_code }'/>">
				<input type="hidden" name="dept_code" value="<c:out value='${ dept_code }'/>">
				<input type="hidden" name="seq" value="<c:out value='${ seq }'/>"/>
				<input type="hidden" name="ev_state" value="<c:out value='${ vendEvalMasterDetail.EV_STATE }' />">
				<input type="hidden" name="P_ATCHMNFL_GROUP_NO" value="<c:out value='${ vendEvalVendorDeptDetail.ATCHMNFL_GROUP_NO }'/>">
				
				<h4 class="tit">상반기</h4>
				<div class="view_area">	
					<table id="codeTable">
						<colgroup>
							<col style="width: 15%;">
		                   	<col style="width: 15%;">
		                   	<col style="width: 5%;">
		                   	<col style="width: auto;">
		                   	<col style="width: 10%;">
						</colgroup>
						<thead>
						<tr>
								<th class="txtc">분야(배점)</th>
								<th class="txtc">평가항목</th>
								<th class="txtc">배점</th>
								<th class="txtc">설명</th>
								<th class="txtc">실측값</th>
								<th class="txtc">점수</th>
						</tr>
						</thead>
						<tfoot>
					    <tr>
					        <td class="titable_blue" style="background-color: #EFECF5; color: #333333; font-family: '굴림'; font-size: 12px;text-align: right; padding-right: 10px" colspan="5">총&nbsp;&nbsp;&nbsp;&nbsp;점</td>
					        <td class="titable_blue" style="background-color: #EFECF5; color: #333333; font-family: '굴림'; font-size: 12px;" ><input type="text" size="10" name="tot_score" style="text-align: right;" value="" readonly  money ></td>
					    </tr>
					    </tfoot>
						<tbody>
				                          <c:if test="${ empty vendEvalVendorDeptCodeList1 }">
							<tr class="itemstyle"><td colspan="6" align="center">평가항목이 없습니다.</td></tr>
						</c:if>
						                                                                                                                                                                                                                                                                                                                                                                                         
						<c:if test="${ not empty vendEvalVendorDeptCodeList1 }">
							<c:forEach items="${ vendEvalVendorDeptCodeList1 }"  var="dataList"   varStatus="loop">
								<c:if test="${ loop.index % 2 == 0 }"><c:set var="rowStyle" value="alterstyle" /></c:if>
								<c:if test="${ loop.index % 2 == 1 }"><c:set var="rowStyle" value="itemstyle" /></c:if>
								<tr>
									<td class="alterstyle tab_border1"  style="padding-left: 2px; font-family: '굴림'; font-size: 12px;" align="left">
									    <c:out value="${ dataList.EV_NAME1 }"/> (<c:out value="${ dataList.DIST_SCORE1 }"/>)
									</td>
									<td  class="<c:out value="${ rowStyle }"/> tab_border1"   style="padding-left: 2px; font-family: '굴림'; font-size: 12px;" align="left">
									    <c:out value="${ dataList.EV_NAME2 }"/>
									<c:if test="${ vendEvalMasterDetail.EV_STATE eq 'B' }">
									    <input type="hidden" name="ev_name1" value="<c:out value='${ dataList.EV_NAME1 }'/>">
									    <input type="hidden" name="ev_code1" value="<c:out value='${ dataList.EV_CODE1 }'/>">
									    <input type="hidden" name="ev_name2" value="<c:out value='${ dataList.EV_NAME2 }'/>">
									    <input type="hidden" name="ev_code2" value="<c:out value='${ dataList.EV_CODE2 }'/>">
									</c:if>
									</td>
									<td class="<c:out value="${ rowStyle }"/> tab_border1"  style="padding-right: 2px; font-family: '굴림'; font-size: 12px;" align="right"><c:out value="${ dataList.DIST_SCORE2 }"/></td>
									<td class="<c:out value="${ rowStyle }"/> tab_border1"  style="padding-left: 2px; font-family: '굴림'; font-size: 12px;" align="left"><c:out escapeXml ="false"  value="${ dataList.REMARK2 }"/></td>
									<td class="<c:out value="${ rowStyle }"/> tab_border1"  style="font-family: '굴림'; font-size: 12px;" align="left">
										<input type="text" name="value2"  style="width: 90%;"  value="<c:out value='${ dataList.VALUE2  }'/>" <c:if test="${ vendEvalMasterDetail.EV_STATE eq 'D' or vendEvalMasterDetail.EV_STATE eq 'C' }">readonly="readonly"</c:if> 
										<c:if test="${ vendEvalMasterDetail.EV_STATE eq 'D1' }">readonly="readonly"</c:if> > 
									</td>
									<td class="<c:out value="${ rowStyle }"/> tab_border1"  style="font-family: '굴림'; font-size: 12px;" align="right">
										<input type="text" name="score2" style="width: 90%;" value="<c:out value='${ dataList.SCORE2 }'/>" onkeyup ="evalScoreSum('score2','tot_score');" onblur="evalScoreChk('<c:out value="${ dataList.DIST_SCORE2 }"/>',this); "  money 
										<c:if test="${ vendEvalMasterDetail.EV_STATE eq 'D' or vendEvalMasterDetail.EV_STATE eq 'C' }">readonly="readonly"</c:if> <c:if test="${ vendEvalMasterDetail.EV_STATE eq 'D1' }">readonly="readonly"</c:if> > 
									</td>
								</tr>
							</c:forEach>
						</c:if>
						</tbody>
					</table>
				</div>
		
				<h4 class="tit">하반기</h4>
				<div class="view_area">
					<table id="codeTable2">
						<colgroup>
							<col style="width: 15%;">
		                   	<col style="width: 15%;">
		                   	<col style="width: 5%;">
		                   	<col style="width: auto;">
		                   	<col style="width: 10%;">
		                   	<col style="width: 10%;">
						</colgroup>
						<thead>
						<tr>
								<th class="txtc">분야(배점)</th>
								<th class="txtc">평가항목</th>
								<th class="txtc">배점</th>
								<th class="txtc">설명</th>
								<th class="txtc">실측값</th>
								<th class="txtc">점수</th>
						</tr>
						</thead>
						<tfoot>
					    <tr>
					        <td class="titable_blue" style="background-color: #EFECF5; color: #333333; font-family: '굴림'; font-size: 12px;text-align: right; padding-right: 10px" colspan="5">총&nbsp;&nbsp;&nbsp;&nbsp;점</td>
					        <td class="titable_blue" style="background-color: #EFECF5; color: #333333; font-family: '굴림'; font-size: 12px;" ><input type="text" size="10" name="tot_score2" style="text-align: right;" value="" readonly  money ></td>
					    </tr>
					    </tfoot>
						<tbody>
				                          <c:if test="${ empty vendEvalVendorDeptCodeList2 }">
							<tr class="itemstyle"><td colspan="6" align="center">평가항목이 없습니다.</td></tr>
						</c:if>
						                                                                                                                                                                                                                                                                                                                                                                                         
						<c:if test="${ not empty vendEvalVendorDeptCodeList2 }">
							<c:forEach items="${ vendEvalVendorDeptCodeList2 }"  var="dataList"   varStatus="loop">
								<c:if test="${ loop.index % 2 == 0 }"><c:set var="rowStyle" value="alterstyle" /></c:if>
								<c:if test="${ loop.index % 2 == 1 }"><c:set var="rowStyle" value="itemstyle" /></c:if>
								<tr>
									<td class="alterstyle tab_border1"  style="padding-left: 2px; font-family: '굴림'; font-size: 12px;" align="left">
									    <c:out value="${ dataList.EV_NAME1 }"/> (<c:out value="${ dataList.DIST_SCORE1 }"/>)
									</td>
									<td  class="<c:out value="${ rowStyle }"/> tab_border1"  style="padding-left: 2px; font-family: '굴림'; font-size: 12px;" align="left">
									    <c:out value="${ dataList.EV_NAME2 }"/>
									<c:if test="${ vendEvalMasterDetail.EV_STATE eq 'D1' }">
									    <input type="hidden" name="ev_name1" value="<c:out value='${ dataList.EV_NAME1 }'/>">
									    <input type="hidden" name="ev_code1" value="<c:out value='${ dataList.EV_CODE1 }'/>">
									    <input type="hidden" name="ev_name2" value="<c:out value='${ dataList.EV_NAME2 }'/>">
									    <input type="hidden" name="ev_code2" value="<c:out value='${ dataList.EV_CODE2 }'/>">
									</c:if>
									</td>
									<td class="<c:out value="${ rowStyle }"/> tab_border1"  style="padding-right: 2px; font-family: '굴림'; font-size: 12px;" align="right"><c:out value="${ dataList.DIST_SCORE2 }"/></td>
									<td class="<c:out value="${ rowStyle }"/> tab_border1"  style="padding-left: 2px; font-family: '굴림'; font-size: 12px;" align="left"><c:out escapeXml ="false"  value="${ dataList.REMARK2 }"/></td>
									<td class="<c:out value="${ rowStyle }"/> tab_border1"  style="font-family: '굴림'; font-size: 12px;" align="left">
										<input type="text" name="value2_2"  style="width: 90%;"  value="<c:out value='${ dataList.VALUE2  }'/>" <c:if test="${ vendEvalMasterDetail.EV_STATE eq 'D' or vendEvalMasterDetail.EV_STATE eq 'C' }">readonly="readonly"</c:if> 
										<c:if test="${ vendEvalMasterDetail.EV_STATE eq 'B' }">readonly="readonly"</c:if> > 
									</td>
									<td class="<c:out value="${ rowStyle }"/> tab_border1"  style="font-family: '굴림'; font-size: 12px;" align="right">
										<input type="text" name="score2_2" style="width: 90%;" value="<c:out value='${ dataList.SCORE2 }'/>" onkeyup ="evalScoreSum('score2_2','tot_score2');" onblur="evalScoreChk('<c:out value="${ dataList.DIST_SCORE2 }"/>',this); "  money 
										<c:if test="${ vendEvalMasterDetail.EV_STATE eq 'D' or vendEvalMasterDetail.EV_STATE eq 'C' }">readonly="readonly"</c:if> <c:if test="${ vendEvalMasterDetail.EV_STATE eq 'B' }">readonly="readonly"</c:if> > 
									</td>
								</tr>
							</c:forEach>
						</c:if>
						</tbody>
					</table>
				</div>
				
				<h4 class="tit">첨부파일</h4>
				<div class="view_area">
					<table>
						<caption>첨부파일(물품규격서)</caption>
						<colgroup>
							<col style="width: 170px;">
							<col style="width: auto;">
						</colgroup>
						<tbody>
							<tr>
			               		<th scope="row" >첨부파일</th>
								<td colspan="3"> 
									<span class="stD" style="height: 30px;">
										<c:if test="${ vendEvalMasterDetail.EV_STATE eq 'B' or vendEvalMasterDetail.EV_STATE eq 'D1' }">
			                    			<button type="button"  class="btn btn_02 btn_sch" id="fileBtn" style="float: right; margin-top: 3px;">추가</button>
			                    		</c:if> 
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
						                   					<c:if test="${ vendEvalMasterDetail.EV_STATE eq 'B' or vendEvalMasterDetail.EV_STATE eq 'D1' }">
							                   					<button type="button" class="btn btn_02 btn_sch" style="display: inline-block; margin-top: 3px;" onclick="fileDel(this,'${data.ATCHMNFL_SN }')">삭제</button>
							                   				</c:if>
							                   			</span>
						                    		</div>
						                    	</c:forEach>  
						                    </c:if>
				                    	</div> 
			                    	<div id="fileDiv" style="line-height: 30px; margin-top: 3px;">
			                    	</div>
			                   	</td>	
			            	</tr>
						</tbody>
					</table>
				</div>
			</form>
			<div class="btn_wrap view_btn">
			 	<c:if test="${ vendEvalMasterDetail.EV_STATE eq 'B' or vendEvalMasterDetail.EV_STATE eq 'D1' }">	
				  <button type="button" class="btn btn_02 btn_revise" id="saveBtn">저장</button>
				 </c:if>
		  		<button type="button" class="btn btn_02 btn_sch" id="listBtn">취소</button>
	   		</div>	
	   	</div>
	</div>
</div>
<c:if test="${ not empty errorMessage }">
<script type="text/javascript">
<!--
	sm_MsgPrint( '', 'ERROR', 'E',"<c:out value="${ errorMessage }"/>", '' );
//-->
</script>
</c:if>
<c:if test="${ empty errorMessage }">
<script type="text/javascript">
<!--
	sm_MsgPrint( '', 'OK', 'I',"<fmt:message key="search.status.success" />", '' );
//-->
</script>
</c:if>
<script type="text/javascript" src="/js/jstiger.js"></script>
</html>
<form id="listFrm" name="listFrm" method="post" action="/buyer/eval/evalDeptEvalView.do" >
			<input type="hidden" name="resourceName" value="${ param.resourceName }" >
			<input type="hidden" name="pageNo" value="<c:out value='${ searchMap["pageNo"] }'/>">	
			<input type="hidden" id="P_DELETE_FILE_SN" name="P_DELETE_FILE_SN">	
			
			<input type="hidden" name="dyyyy" value="<c:out value='${ dyyyy }'/>">
			<input type="hidden" name="ev_seq" value="<c:out value='${ ev_seq }'/>">
</form>