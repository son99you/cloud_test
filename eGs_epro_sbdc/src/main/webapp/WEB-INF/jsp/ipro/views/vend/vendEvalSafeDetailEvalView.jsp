<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 안전관리평가 상세  -> 평가수행(신분당선 안전관리평가)
 *
 * <pre>
 * vend
 *    |_ vendEvalSafeDetailEvalView.jsp
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

<link rel="stylesheet" type="text/css" href="${cssPath}/ipro/bodyBasic.css">
<link rel="stylesheet" type="text/css" href="${cssPath}/ipro/template/buttonStyle.css">
<link rel="stylesheet" type="text/css" href="${cssPath}/ipro/template/pagingPlace.css">
<link rel="stylesheet" type="text/css" href="${cssPath}/ipro/template/calendar.css">
<link rel="stylesheet" type="text/css" href="${cssPath}/ipro/eval.css">

<script type="text/javascript" src="${jsPath}/ipro/views/vend/vendEvalSafeDetailEvalView.js"></script> 
 
<div class="content">
		<h3>안전환경평가 평가수행</h3>
		<div class="subscrip_info">
		<div class="T_btnLayer fr">
	  		<button type="button" class="blueBtn L" id="saveBtn">저장</button>
	  		<button type="button" class="blueBtn L" id="listBtn">취소</button>
   		</div>	
   		<br>
				<table>
					<colgroup>												
						<col width="15%">
						<col width="35%">
						<col width="15%">
						<col width="35%">
					</colgroup>
					<tr height="24">
						<th class=" txtl">
							
							년도
						</th>
						<td>
							2017년도 30차
						</td>
						<th class=" txtl">
							
							평가명
						</th>
						<td>
							2017년05월30일 평가
						</td>
					</tr>
					<tr height="24">
						<th class=" txtl">
							
							평가유형
						</th>
						<td>
							등급평가
						</td> 
						<th class=" txtl">
							
							평가수행기간
						</th>
						<td>
							2017-06-01 ~ 2017-06-14 
						</td>
					</tr>
					<tr height="24">
						<th class=" txtl">
							
							평가실적기간
						</th>
						<td>
							2017-06-01 ~ 2017-06-14 
						</td>
						<th class=" txtl">
							
							진행상태
						</th>
						<td>
							작성 
						</td>
					</tr>
					<tr height="24">
						<th class=" txtl">
							
							협력업체
						</th>
						<td>
							(주)한국철도기술공사 
						</td>
						<th class=" txtl">
							
							대표자
						</th>
						<td>
							김대영 
						</td>
					</tr>
					<tr height="24">
						<th class=" txtl">
							
							소싱그룹
						</th>
						<td>
							공통
						</td>
						<th class=" txtl">
							
							부서명
						</th>
						<td>
							영업팀
						</td>
					</tr>
				</table>
	<br>
	</div>
	<div class="subscrip_info">
		<table>
			<colgroup>
				<col width="15%">
				<col width="20%">
				<col width="5%" >
				<col width="40%">
				<col width="10%">
				<col width="10%">
			</colgroup>
			<thead>
				<tr>
					<th class="txtc tab_border1">분야(배점)</th>
					<th class="txtc tab_border1">평가항목</th>
					<th class="txtc tab_border1">배점</th>
					<th class="txtc tab_border1">설명</th>
					<th class="txtc tab_border1">실측값</th>
					<th class="txtc tab_border1">점수</th>
				</tr>
			</thead>
			<tfoot>
			    <tr class="tab_border1">
			        <th class=" txtc tab_border1"colspan="5"><font><b>총&nbsp;&nbsp;&nbsp;&nbsp;점</b></font></th>
			        <td class=" txtr tab_border1" ><input type="text" size="10" name="tot_score" style="text-align: right;" value="" readonly  ></td>
			    </tr>
		    </tfoot>
			<tbody id="gSignFrame" class="grdtable">
				<tr>
					<th class="txtl tab_border1" rowspan="3"><font><b>안전환경평가</b></font></th>
					<td class="txtl tab_border">안전평가</td>
					<td class="txtr tab_border">80</td>
					<td class="txtl tab_border" style="padding: 5px 5px 5px 5px; ">사망(여객,공중,직원)사고 / 열차사고 발생 5점/건 감점<br>산업재해/여객사상사고/운행지연 10분 이상 발생 3점/건 감점<br>안전점검(감사,사고조사) 결과 부적합 사항 발생 0.5점/건 감점<br>부적합사항 개선조치 불이행 1점/건 감점<br>안전교육 미실시 (정기,신규자,특별교육 등) 1점/건 감점<br>안전협의체 미참석 / 의결사항 불이행 1점/건 감점<br>우수사례 발생시 1점/건 가점 </td>
					<td class="txtl tab_border"><input type="text" name="value2" value="80"></td>
					<td class="txtr tab_border"><input type="text" size="10" name="score" style="text-align: right;" value="0" onkeyup ="evalScoreSum('score','tot_score');" onblur="evalScoreChk('80',this); "> </td>
				</tr>
				<tr>
					<td class="txtl tab_border">환경평가</td>
					<td class="txtr tab_border">20</td>
					<td class="txtl tab_border" style="padding: 5px 5px 5px 5px; ">환경점검/감사시 지적건수 0.5/건 감점<br>환경민원 발생건수 0.5점/건 감점 </td>
					<td class="txtl tab_border"><input type="text" name="value2" value="20"></td>
					<td class="txtr tab_border"><input type="text" size="10" name="score" style="text-align: right;" value="0" onkeyup ="evalScoreSum('score','tot_score');" onblur="evalScoreChk('20',this); "> </td>
				</tr>                    
			</tbody>
		</table>
	</div><br>
	<div class="subscrip_info">
	<div>
				<div class="contentTitle">첨부파일</div>
				<div class="T_btnLayer cn n_m fl">
					<button type="button" class="grayBtn S" id="setAllCheckOn">전체선택</button>
					<button type="button" class="grayBtn S" id="setAllCheckOff">전체해제</button>				
				</div>
				<div class="T_btnLayer cn n_m fr" style="float: right;">
		            <button type="button" class="grayBtn S" id="entrpsPlusBtn">문서추가</button>
		            <button type="button" class="grayBtn S" id="entrpsDeleteBtn">문서삭제</button>
		       	</div>		       	
		       	
		       	<div id="ctrtcEntrpsChoise">
					<table id="entrpsTb">
				    	<colgroup>
				    		<col width="5%"/>
			                <col width="5%"/>
			                <col width="*"/>
			                <col width="8%"/>
			                <col width="15%"/>
				        </colgroup>
				        <thead>
			            	<tr class="line">
				            	<th class="noBg" style="text-align: center;">선택</th>
				            	<th style="text-align: center;">순번</th>
				            	<th style="text-align: center;">문서명</th>
				            	<th style="text-align: center;">파일타입</th>
				            	<th class="copsntrA" style="text-align: center;">파일사이즈</th>
			            	</tr>
			            </thead>
			            
			            <tbody>
			            	<tr style="display: none;">
								<td>
									<label for="entrpsChoiceCbx" class="blind">체크박스</label>
									<input type="checkbox" id="entrpsChoiceCbx" name="entrpsChoiceCbx">
								</td>
								<td></td>
								<td>
									<input type="file" style="width: 95%;"/>
				                </td>
								<td></td>
								<td></td>
			            	</tr>
			            </tbody>
				    </table>
		       	</div>
			</div>
	</div><!-- 첨부파일 div End -->
	<br>
</div>

<%-- DETAIL FORM --%>
<form id="detailFrm" method="POST" > 
	<input type="hidden" name="resourceName" value="${ param.resourceName }" >
</form>