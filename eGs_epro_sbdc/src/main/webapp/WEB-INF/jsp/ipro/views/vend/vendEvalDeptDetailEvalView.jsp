<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 유관부서평가 상세 -> 평가수행(신분당선 유관부서평가)
 *
 * <pre>
 * vend
 *    |_ vendEvalDeptDetailEvalView.jsp
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

<script type="text/javascript" src="${jsPath}/ipro/views/vend/vendEvalDeptDetailEvalView.js"></script> 
 
<div class="content">
		<h3>유관부서평가 평가수행</h3>
		<div class="subscrip_info">
		<div class="T_btnLayer fr">
	  		<button type="button" class="blueBtn L" id="saveBtn">저장</button>
	  		<button type="button" class="blueBtn L" id="listBtn">취소</button>
   		</div>	
   		<br>
		<div id="searchWrap">
			<table>
				<colgroup>												
					<col width="15%">
					<col width="35%">
					<col width="15%">
					<col width="35%">
				</colgroup>
				<tr height="24">
					<th class="txtl">
						
						년도
					</th>
					<td>
						2017년도 30차
					</td>
					<th class="txtl">
						
						평가명
					</th>
					<td>
						2017년05월30일 평가
					</td>
				</tr>
				<tr height="24">
					<th class="txtl">
						
						평가유형
					</th>
					<td>
						등급평가
					</td>
					<th class="txtl">
						
						평가수행기간
					</th>
					<td>
						2017-06-01 ~ 2017-06-14 
					</td>
				</tr>
				<tr height="24">
					<th class="txtl">
						
						평가실적기간
					</th>
					<td>
						2017-06-01 ~ 2017-06-14 
					</td>
					<th class="txtl">
						
						진행상태
					</th>
					<td>
						작성 
					</td>
				</tr>
				<tr height="24">
					<th class="txtl">
						
						협력업체
					</th>
					<td>
						(주)한국철도기술공사 
					</td>
					<th class="txtl">
						
						대표자
					</th>
					<td>
						김대영 
					</td>
				</tr>
				<tr height="24">
					<th class="txtl">
						
						소싱그룹
					</th>
					<td>
						공통
					</td>
					<th class="txtl">
						
						부서명
					</th>
					<td>
						영업팀
					</td>
				</tr>
			</table>
	</div><br>
	</div>
	<div class="subscrip_info">
	<div class="contentTitle" style="">상반기</div>
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
			    <tr>
			        <th class=" txtc tab_border1"colspan="5"><font><b>총&nbsp;&nbsp;&nbsp;&nbsp;점</b></font></th>
			        <th class=" txtr tab_border1" ><input type="text" size="10" name="tot_score" style="text-align: right;" value="" readonly  ></th>
			    </tr>
		    </tfoot>
			<tbody id="gSignFrame" class="grdtable">
				<tr class="tab_border1">
					<th class="txtl tab_border1" rowspan="3"><font><b>기타 (10)</b></font></th>
					<td class="txtl tab_border">각종점검(감사) 결과 지적</td>
					<td class="txtr tab_border">3</td>
					<td class="txtl tab_border" style="padding: 5px 5px 5px 5px; ">지적 건당 0.1점 감점<BR/></td>
					<td class="txtl tab_border"><input type="text" name="value2" value="3"></td>
					<td class="txtr tab_border"><input type="text" size="10" name="score2" style="text-align: right;" value="3" onkeyup ="evalScoreSum('score2','tot_score');" onblur="evalScoreChk('3',this); "> </td>
				</tr>
				<tr class="tab_border1">
					<td class="txtl tab_border">민원(신고) 발생 등의 지적</td>
					<td class="txtr tab_border">3</td>
					<td class="txtl tab_border" style="padding: 5px 5px 5px 5px; ">지적 건당 0.1점 감점<BR/></td>
					<td class="txtl tab_border"><input type="text" name="value2" value="4"></td>
					<td class="txtr tab_border"><input type="text" size="10" name="score2" style="text-align: right;" value="3" onkeyup ="evalScoreSum('score2','tot_score');" onblur="evalScoreChk('3',this); "> </td>
				</tr>                    
				<tr class="tab_border1">
					<td class=" txtl tab_border">지시사항이행실태(지시사항 위반율)</td>
					<td class=" txtr tab_border">4</td>
					<td class=" txtl tab_border" style="padding: 5px 5px 5px 5px; ">지적 건당 0.1점 감점<BR/></td>
					<td class=" txtl tab_border"><input type="text" name="value2" value="4"></td>
					<td class=" txtr tab_border"><input type="text" size="10" name="score2" style="text-align: right;" value="4" onkeyup ="evalScoreSum('score2','tot_score');" onblur="evalScoreChk('4',this); " > </td>
				</tr>
				<tr class="tab_border1">
					<th class=" txtl tab_border1" rowspan="2"><font><b>유지관리 (25)</b></font></th>
					<td class=" txtl tab_border ">검수실적 이행율(계획대실적율)</td>
					<td class=" txtr tab_border">15</td>
					<td class=" txtl tab_border" style="padding: 5px 5px 5px 5px; ">100% 이상 15점<BR/>90% - 99% 10점<BR/>80% - 89% 8점<BR/>70% - 79% 6점<BR/>69% 이하 4점<BR/><BR/>EX)<BR/><BR/>[A] 검수계획 (2960) 건<BR/>[B] 실적 (2960) 건<BR/><BR/>[B] / [A] x 100% = (100) %</td>
					<td class=" txtl tab_border"><input type="text" size="10" name="value2" value="15" maxbyte="80"	 > </td>
					<td class=" txtr tab_border"><input type="text" size="10" name="score2" style="text-align: right;" value="10" onkeyup ="evalScoreSum('score2','tot_score');" onblur="evalScoreChk('15',this); " > </td>
				</tr>
				<tr class="tab_border1">
					<td class=" txtl tab_border">장비관리</td>
					<td class=" txtr tab_border">10</td>
					<td class=" txtl tab_border" style="padding: 5px 5px 5px 5px; ">100% 이상 10점<BR/>90% - 99% 8점<BR/>80% - 89% 6점<BR/>70% - 79% 4점<BR/>69% 이하 2점<BR/><BR/>EX)<BR/><BR/>[A] 검수계획 (11) 건<BR/>[B] 실적 (11) 건<BR/><BR/>[B] / [A] x 100% = (100) %</td>
					<td class=" txtl tab_border"><input type="text" size="10" name="value2" value="10" maxbyte="80"	 > </td>
					<td class=" txtr tab_border"><input type="text" size="10" name="score2" style="text-align: right;" value="8" onkeyup ="evalScoreSum('score2','tot_score');" onblur="evalScoreChk('10',this); " > </td>
				</tr>
				<tr class="tab_border1">
					<th class=" txtl tab_border1" rowspan="2"><font><b>업무관리 (25)</b></font></th>
					<td class=" txtl tab_border"> 하자율(재보수시행율)</td>
					<td class=" txtr tab_border">10</td>
					<td class=" txtl tab_border" style="padding: 5px 5px 5px 5px; ">1% 미만 10점<BR/>1% - 3%미만 8점<BR/>3% - 5%미만 6점<BR/>5% - 10%미만 4점<BR/><BR/>EX)<BR/><BR/>[A] 총 작업건수 (146) 건<BR/>[B] 재작업시행건수 ( 8) 건<BR/><BR/>[B] / [A] x 100% = (5.5) %</td>
					<td class=" txtl tab_border"><input type="text" size="10" name="value2" value="10" maxbyte="80"	 > </td>
					<td class=" txtr tab_border"><input type="text" size="10" name="score2" style="text-align: right;" value="4" onkeyup ="evalScoreSum('score2','tot_score');" onblur="evalScoreChk('10',this); "  > </td>
				</tr>
				<tr class="tab_border1">
					<td class=" txtl tab_border">장애발생처리(처리시간/계획시간) X 100%</td>
					<td class=" txtr tab_border">15</td>
					<td class=" txtl tab_border" style="padding: 5px 5px 5px 5px; ">95% 이하 15점<BR/>96% - 100% 13점<BR/>101% - 110% 10점<BR/>111% - 120% 5점<BR/>120% 이상 2점<BR/><BR/>EX)<BR/><BR/>[A] 계획된 시간 (86.88) 시간<BR/>[B] 실제소요시간 (64.48) 시간<BR/><BR/>[B] / [A] x 100% = (78.82) %</td>
					<td class=" txtl tab_border"><input type="text" size="10" name="value2" value="15" maxbyte="80"	 > </td>
					<td class=" txtr tab_border"><input type="text" size="10" name="score2" style="text-align: right;" value="13" onkeyup ="evalScoreSum('score2','tot_score');" onblur="evalScoreChk('15',this); " > </td>
				</tr>
				<tr class="tab_border1">
					<th class=" txtl tab_border1" rowspan="4"><font><b>인력관리 (25)</b></font></th>
					<td class=" txtl tab_border">용역인력 노무관리(근무율)</td>
					<td class=" txtr tab_border">10</td>
					<td class=" txtl tab_border" style="padding: 5px 5px 5px 5px; ">100% 10점<BR/>98%이상 8점<BR/>95%이상 6점<BR/>93%이상 4점<BR/><BR/>EX)<BR/><BR/>[A] 계약 총인원수 (1210) 인<BR/>[B] 근무 총인원수 (1210) 인<BR/><BR/>[B] / [A] x 100% = (100) %</td>
					<td class=" txtl tab_border"><input type="text" size="10" name="value2" value="10" maxbyte="80"	 > </td>
					<td class=" txtr tab_border"><input type="text" size="10" name="score2" style="text-align: right;" value="4" onkeyup ="evalScoreSum('score2','tot_score');" onblur="evalScoreChk('10',this); " > </td>
				</tr>
				<tr class="tab_border1">
					<td class=" txtl tab_border">용역인력 노무관리(이직률)</td>
					<td class=" txtr tab_border">5</td>
					<td class=" txtl tab_border" style="padding: 5px 5px 5px 5px; ">9%이하 5점<BR/>10% - 19% 3점<BR/>20% - 29% 1점<BR/>30%이상 0점<BR/><BR/>EX)<BR/><BR/>[A] 당해년도 총투입인수 (1210) 인<BR/>[B] 당해년도 이직인원수 (0) 인<BR/><BR/>[B] / [A] x 100% = (100) %</td>
					<td class=" txtl tab_border"><input type="text" size="10" name="value2" value="5" maxbyte="80"	 > </td>
					<td class=" txtr tab_border"><input type="text" size="10" name="score2" style="text-align: right;" value="3" onkeyup ="evalScoreSum('score2','tot_score');" onblur="evalScoreChk('5',this); " > </td>
				</tr>
				<tr class="tab_border1">
					<td class=" txtl tab_border">기술자직무관련자격증보유(기능사이상)</td>
					<td class=" txtr tab_border">5</td>
					<td class=" txtl tab_border" style="padding: 5px 5px 5px 5px; ">90%이상 5점<BR/>80% - 89% 4점<BR/>70% - 79% 3점<BR/>69%이하 2점<BR/><BR/>EX)<BR/><BR/>[A] 계약인력 (10) 인<BR/>[B] 기술자격 보유인력 (10) 인<BR/><BR/>[B] / [A] x 100% = (100) %</td>
					<td class=" txtl tab_border"><input type="text" size="10" name="value2" value="5" maxbyte="80"	 > </td>
					<td class=" txtr tab_border"><input type="text" size="10" name="score2" style="text-align: right;" value="2" onkeyup ="evalScoreSum('score2','tot_score');" onblur="evalScoreChk('5',this); " > </td>
				</tr>
				<tr class="tab_border1">
					<td class=" txtl tab_border">용역인력인건비 지급 수준</td>
					<td class=" txtr tab_border">5</td>
					<td class=" txtl tab_border" style="padding: 5px 5px 5px 5px; ">90%이상 5점<BR/>80% - 89% 3점<BR/>70% - 79% 1점<BR/>69%이하 0점<BR/><BR/>EX)<BR/><BR/>[A] 계약 총인건비 (157,055)천원<BR/>[B] 실 지급 총인건비 (10,077)천원<BR/><BR/>[B] / [A] x 100% = (64.35)%</td>
					<td class=" txtl tab_border"><input type="text" size="10" name="value2" value="5" maxbyte="80"	 > </td>
					<td class=" txtr tab_border"><input type="text" size="10" name="score2" style="text-align: right;" value="0" onkeyup ="evalScoreSum('score2','tot_score');" onblur="evalScoreChk('5',this); " > </td>
				</tr>
				<tr class="tab_border1">
					<th class=" txtl tab_border1" rowspan="3"><font><b>행정관리 (15)</b></font></th>
					<td class=" txtl tab_border">제출문선의 기간준수</td>
					<td class=" txtr tab_border">5</td>
					<td class=" txtl tab_border" style="padding: 5px 5px 5px 5px; ">용역수행 관련 문서 제출 지연 -0.1/일<BR/>각종 시설물 점검 결과제출 지연 -0.1/일<BR/>문서처리의 부적정성(반송건수) -0.1/건<BR/></td>
					<td class=" txtl tab_border"><input type="text" size="10" name="value2" value="5" maxbyte="80"	 > </td>
					<td class=" txtr tab_border"><input type="text" size="10" name="score2" style="text-align: right;" value="3.1" onkeyup ="evalScoreSum('score2','tot_score');" onblur="evalScoreChk('5',this); " > </td>
				</tr>
				<tr class="tab_border1">
					<td class=" txtl tab_border">문서의 기록, 유지 및 보존 상태</td>
					<td class=" txtr tab_border">5</td>
					<td class=" txtl tab_border" style="padding: 5px 5px 5px 5px; ">문서의 기록관리상태 부실 -0.1/건<BR/>용역사 비치문서 관리 상태 부실 -0.1/건<BR/>당사에서 제공된 도서관리 상태 부실 -0.1/건<BR/></td>
					<td class=" txtl tab_border"><input type="text" size="10" name="value2" value="5" maxbyte="80"	 > </td>
					<td class=" txtr tab_border"><input type="text" size="10" name="score2" style="text-align: right;" value="2.1" onkeyup ="evalScoreSum('score2','tot_score');" onblur="evalScoreChk('5',this); " > </td>
				</tr>
				<tr class="tab_border1">
					<td class=" txtl tab_border">교육관리 실태(교육이행 실적)</td>
					<td class=" txtr tab_border">5</td>
					<td class=" txtl tab_border" style="padding: 5px 5px 5px 5px; ">90% 이상 5점 [A]교육계획건수<BR/>80% - 89% 4점[B]교육이행건수<BR/>80% - 79% 3점[B]/[A] x 100% = (100)%<BR/>69% 이하 2점 [B]/[A] x 100% = (100)%<BR/></td>
					<td class=" txtl tab_border"><input type="text" size="10" name="value2" value="5" maxbyte="80"	 > </td>
					<td class=" txtr tab_border"><input type="text" size="10" name="score2" style="text-align: right;" value="4" onkeyup ="evalScoreSum('score2','tot_score');" onblur="evalScoreChk('5',this); " > </td>
				</tr>
				<tr class="tab_border1">
					<th class=" txtl tab_border1" rowspan="2"><font><b>연구개발 (20)</b></font></th>
					<td class=" txtl tab_border">표창및 아이디어 제안										</td>
					<td class=" txtr tab_border">10</td>
					<td class=" txtl tab_border" style="padding: 5px 5px 5px 5px; ">우수사례 표창 +5점<BR/>제안 채택 건수 +5점<BR/>연구개발 등의 활동적 사례 +5점<BR/></td>
					<td class=" txtl tab_border"><input type="text" size="10" name="value2" value="10" maxbyte="80"	 > </td>
					<td class=" txtr tab_border"><input type="text" size="10" name="score2" style="text-align: right;" value="5" onkeyup ="evalScoreSum('score2','tot_score');" onblur="evalScoreChk('10',this); " > </td>
				</tr>
				<tr class="tab_border1">
					<td class=" txtl tab_border">표창및 아이디어 제안</td>
					<td class=" txtr tab_border">10</td>
					<td class=" txtl tab_border" style="padding: 5px 5px 5px 5px; ">매우 잘함 +10점<BR/>잘함 +5점<BR/></td>
					<td class=" txtl tab_border"><input type="text" size="10" name="value2" value="10" maxbyte="80"	 > </td>
					<td class=" txtr tab_border"><input type="text" size="10" name="score2" style="text-align: right;" value="5" onkeyup ="evalScoreSum('score2','tot_score');" onblur="evalScoreChk('10',this); "  > </td>
				</tr>
				<tr class="tab_border1">
					<th class=" txtl tab_border1" rowspan="2"><font><b>안전관리 (-20)</b></font></th>
					<td class=" txtl tab_border">인적사고</td>
					<td class=" txtr tab_border">-10</td>
					<td class=" txtl tab_border" style="padding: 5px 5px 5px 5px; ">사망자발생 (-10/건)<BR/>2주이상 부상 (-2/건)<BR/></td>
					<td class=" txtl tab_border"><input type="text" size="10" name="value2" value="-10" maxbyte="80"	 > </td>
					<td class=" txtr tab_border"><input type="text" size="10" name="score2" style="text-align: right;" value="-8" onkeyup ="evalScoreSum('score2','tot_score');" onblur="evalScoreChk('-10',this); "  > </td>
				</tr>
				<tr class="tab_border1">
					<td class=" txtl tab_border">산업재해</td>
					<td class=" txtr tab_border">-10</td>
					<td class=" txtl tab_border" style="padding: 5px 5px 5px 5px; ">산업재해발생(-5/건)<BR/></td>
					<td class=" txtl tab_border"><input type="text" size="10" name="value2" value="-10" maxbyte="80"	 > </td>
					<td class=" txtr tab_border"><input type="text" size="10" name="score2" style="text-align: right;" value="-5" onkeyup ="evalScoreSum('score2','tot_score');" onblur="evalScoreChk('-10',this); " > </td>
				</tr>
				<tr class="tab_border1">
					<th class=" txtl tab_border1" rowspan="2"><font><b>고객서비스 (-15)</b></font></th>
					<td class=" txtl tab_border">운행지연(평가기간동안의 누적시간)</td>
					<td class=" txtr tab_border">-10</td>
					<td class=" txtl tab_border" style="padding: 5px 5px 5px 5px; ">10분 이상 ~ 20분 미만 -2점<BR/>20분 이상 ~ 30분 미만 -3점<BR/>30분 이상 -5점<BR/></td>
					<td class=" txtl tab_border"><input type="text" size="10" name="value2" value="-10" maxbyte="80"	 > </td>
					<td class=" txtr tab_border"><input type="text" size="10" name="score2" style="text-align: right;" value="-3" onkeyup ="evalScoreSum('score2','tot_score');" onblur="evalScoreChk('-10',this); " > </td>
				</tr>
				<tr class="tab_border1">
					<td class=" txtl tab_border">대내외 이미지 손상</td>
					<td class=" txtr tab_border">-5</td>
					<td class=" txtl tab_border" style="padding: 5px 5px 5px 5px; ">신분당선의 이미지를 중대하게 훼손 -3점<BR/>신분당선의 이미지를 경미하게 훼손 -2점<BR/></td>
					<td class=" txtl tab_border"><input type="text" size="10" name="value2" value="-5" maxbyte="80"	 > </td>
					<td class=" txtr tab_border"><input type="text" size="10" name="score2" style="text-align: right;" value="-2" onkeyup ="evalScoreSum('score2','tot_score');" onblur="evalScoreChk('-5',this); " > </td>
				</tr>
			</tbody>
		</table>
	</div><br>
	<div class="subscrip_info">
	<div class="contentTitle" style="">하반기</div>
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
			    <tr>
			        <th class=" txtc tab_border1"colspan="5"><font><b>총&nbsp;&nbsp;&nbsp;&nbsp;점</b></font></th>
			        <th class=" txtr tab_border" ><input type="text" size="10" name="tot_score2" style="text-align: right;" value="" readonly  ></th>
			    </tr>
		    </tfoot>
			<tbody id="gSignFrame" class="grdtable">
				<tr class="tab_border1">
					<th class=" txtl tab_border1" rowspan="3"><font><b>기타 (10)</b></font></th>
					<td class=" txtl tab_border">각종점검(감사) 결과 지적</td>
					<td class=" txtr tab_border">3</td>
					<td class=" txtl tab_border" style="padding: 5px 5px 5px 5px; ">지적 건당 0.1점 감점<BR/></td>
					<td class=" txtl tab_border"><input type="text" name="value2" value="3"></td>
					<td class=" txtr tab_border"><input type="text" size="10" name="score2_2" style="text-align: right;" value="3" onkeyup ="evalScoreSum('score2_2','tot_score2');" onblur="evalScoreChk('3',this); "> </td>
				</tr>
				<tr class="tab_border1">
					<td class=" txtl tab_border">민원(신고) 발생 등의 지적</td>
					<td class=" txtr tab_border">3</td>
					<td class=" txtl tab_border" style="padding: 5px 5px 5px 5px; ">지적 건당 0.1점 감점<BR/></td>
					<td class=" txtl tab_border"><input type="text" name="value2" value="4"></td>
					<td class=" txtr tab_border"><input type="text" size="10" name="score2_2" style="text-align: right;" value="3" onkeyup ="evalScoreSum('score2_2','tot_score2');" onblur="evalScoreChk('3',this); "> </td>
				</tr>                    
				<tr class="tab_border1">
					<td class=" txtl tab_border">지시사항이행실태(지시사항 위반율)</td>
					<td class=" txtr tab_border">4</td>
					<td class=" txtl tab_border" style="padding: 5px 5px 5px 5px; ">지적 건당 0.1점 감점<BR/></td>
					<td class=" txtl tab_border"><input type="text" name="value2" value="4"></td>
					<td class=" txtr tab_border"><input type="text" size="10" name="score2_2" style="text-align: right;" value="4" onkeyup ="evalScoreSum('score2_2','tot_score2');" onblur="evalScoreChk('4',this); " > </td>
				</tr>
				<tr class="tab_border1">
					<th class=" txtl tab_border1" rowspan="2"><font><b>유지관리 (25)</b></font></th>
					<td class=" txtl tab_border ">검수실적 이행율(계획대실적율)</td>
					<td class=" txtr tab_border">15</td>
					<td class=" txtl tab_border" style="padding: 5px 5px 5px 5px; ">100% 이상 15점<BR/>90% - 99% 10점<BR/>80% - 89% 8점<BR/>70% - 79% 6점<BR/>69% 이하 4점<BR/><BR/>EX)<BR/><BR/>[A] 검수계획 (2960) 건<BR/>[B] 실적 (2960) 건<BR/><BR/>[B] / [A] x 100% = (100) %</td>
					<td class=" txtl tab_border"><input type="text" size="10" name="value2" value="15" maxbyte="80"	 > </td>
					<td class=" txtr tab_border"><input type="text" size="10" name="score2_2" style="text-align: right;" value="10" onkeyup ="evalScoreSum('score2_2','tot_score2');" onblur="evalScoreChk('15',this); " > </td>
				</tr>
				<tr class="tab_border1">
					<td class=" txtl tab_border">장비관리</td>
					<td class=" txtr tab_border">10</td>
					<td class=" txtl tab_border" style="padding: 5px 5px 5px 5px; ">100% 이상 10점<BR/>90% - 99% 8점<BR/>80% - 89% 6점<BR/>70% - 79% 4점<BR/>69% 이하 2점<BR/><BR/>EX)<BR/><BR/>[A] 검수계획 (11) 건<BR/>[B] 실적 (11) 건<BR/><BR/>[B] / [A] x 100% = (100) %</td>
					<td class=" txtl tab_border"><input type="text" size="10" name="value2" value="10" maxbyte="80"	 > </td>
					<td class=" txtr tab_border"><input type="text" size="10" name="score2_2" style="text-align: right;" value="8" onkeyup ="evalScoreSum('score2_2','tot_score2');" onblur="evalScoreChk('10',this); " > </td>
				</tr>
				<tr class="tab_border1">
					<th class=" txtl tab_border1" rowspan="2"><font><b>업무관리 (25)</b></font></th>
					<td class=" txtl tab_border"> 하자율(재보수시행율)</td>
					<td class=" txtr tab_border">10</td>
					<td class=" txtl tab_border" style="padding: 5px 5px 5px 5px; ">1% 미만 10점<BR/>1% - 3%미만 8점<BR/>3% - 5%미만 6점<BR/>5% - 10%미만 4점<BR/><BR/>EX)<BR/><BR/>[A] 총 작업건수 (146) 건<BR/>[B] 재작업시행건수 ( 8) 건<BR/><BR/>[B] / [A] x 100% = (5.5) %</td>
					<td class=" txtl tab_border"><input type="text" size="10" name="value2" value="10" maxbyte="80"	 > </td>
					<td class=" txtr tab_border"><input type="text" size="10" name="score2_2" style="text-align: right;" value="4" onkeyup ="evalScoreSum('score2_2','tot_score2');" onblur="evalScoreChk('10',this); "  > </td>
				</tr>
				<tr class="tab_border1">
					<td class=" txtl tab_border">장애발생처리(처리시간/계획시간) X 100%</td>
					<td class=" txtr tab_border">15</td>
					<td class=" txtl tab_border" style="padding: 5px 5px 5px 5px; ">95% 이하 15점<BR/>96% - 100% 13점<BR/>101% - 110% 10점<BR/>111% - 120% 5점<BR/>120% 이상 2점<BR/><BR/>EX)<BR/><BR/>[A] 계획된 시간 (86.88) 시간<BR/>[B] 실제소요시간 (64.48) 시간<BR/><BR/>[B] / [A] x 100% = (78.82) %</td>
					<td class=" txtl tab_border"><input type="text" size="10" name="value2" value="15" maxbyte="80"	 > </td>
					<td class=" txtr tab_border"><input type="text" size="10" name="score2_2" style="text-align: right;" value="13" onkeyup ="evalScoreSum('score2_2','tot_score2');" onblur="evalScoreChk('15',this); " > </td>
				</tr>
				<tr class="tab_border1">
					<th class=" txtl tab_border1" rowspan="4"><font><b>인력관리 (25)</b></font></th>
					<td class=" txtl tab_border">용역인력 노무관리(근무율)</td>
					<td class=" txtr tab_border">10</td>
					<td class=" txtl tab_border" style="padding: 5px 5px 5px 5px; ">100% 10점<BR/>98%이상 8점<BR/>95%이상 6점<BR/>93%이상 4점<BR/><BR/>EX)<BR/><BR/>[A] 계약 총인원수 (1210) 인<BR/>[B] 근무 총인원수 (1210) 인<BR/><BR/>[B] / [A] x 100% = (100) %</td>
					<td class=" txtl tab_border"><input type="text" size="10" name="value2" value="10" maxbyte="80"	 > </td>
					<td class=" txtr tab_border"><input type="text" size="10" name="score2_2" style="text-align: right;" value="4" onkeyup ="evalScoreSum('score2_2','tot_score2');" onblur="evalScoreChk('10',this); " > </td>
				</tr>
				<tr class="tab_border1">
					<td class=" txtl tab_border">용역인력 노무관리(이직률)</td>
					<td class=" txtr tab_border">5</td>
					<td class=" txtl tab_border" style="padding: 5px 5px 5px 5px; ">9%이하 5점<BR/>10% - 19% 3점<BR/>20% - 29% 1점<BR/>30%이상 0점<BR/><BR/>EX)<BR/><BR/>[A] 당해년도 총투입인수 (1210) 인<BR/>[B] 당해년도 이직인원수 (0) 인<BR/><BR/>[B] / [A] x 100% = (100) %</td>
					<td class=" txtl tab_border"><input type="text" size="10" name="value2" value="5" maxbyte="80"	 > </td>
					<td class=" txtr tab_border"><input type="text" size="10" name="score2_2" style="text-align: right;" value="3" onkeyup ="evalScoreSum('score2_2','tot_score2');" onblur="evalScoreChk('5',this); " > </td>
				</tr>
				<tr class="tab_border1">
					<td class=" txtl tab_border">기술자직무관련자격증보유(기능사이상)</td>
					<td class=" txtr tab_border">5</td>
					<td class=" txtl tab_border" style="padding: 5px 5px 5px 5px; ">90%이상 5점<BR/>80% - 89% 4점<BR/>70% - 79% 3점<BR/>69%이하 2점<BR/><BR/>EX)<BR/><BR/>[A] 계약인력 (10) 인<BR/>[B] 기술자격 보유인력 (10) 인<BR/><BR/>[B] / [A] x 100% = (100) %</td>
					<td class=" txtl tab_border"><input type="text" size="10" name="value2" value="5" maxbyte="80"	 > </td>
					<td class=" txtr tab_border"><input type="text" size="10" name="score2_2" style="text-align: right;" value="2" onkeyup ="evalScoreSum('score2_2','tot_score2');" onblur="evalScoreChk('5',this); " > </td>
				</tr>
				<tr class="tab_border1">
					<td class=" txtl tab_border">용역인력인건비 지급 수준</td>
					<td class=" txtr tab_border">5</td>
					<td class=" txtl tab_border" style="padding: 5px 5px 5px 5px; ">90%이상 5점<BR/>80% - 89% 3점<BR/>70% - 79% 1점<BR/>69%이하 0점<BR/><BR/>EX)<BR/><BR/>[A] 계약 총인건비 (157,055)천원<BR/>[B] 실 지급 총인건비 (10,077)천원<BR/><BR/>[B] / [A] x 100% = (64.35)%</td>
					<td class=" txtl tab_border"><input type="text" size="10" name="value2" value="5" maxbyte="80"	 > </td>
					<td class=" txtr tab_border"><input type="text" size="10" name="score2_2" style="text-align: right;" value="0" onkeyup ="evalScoreSum('score2_2','tot_score2');" onblur="evalScoreChk('5',this); " > </td>
				</tr>
				<tr class="tab_border1">
					<th class=" txtl tab_border1" rowspan="3"><font><b>행정관리 (15)</b></font></th>
					<td class=" txtl tab_border">제출문선의 기간준수</td>
					<td class=" txtr tab_border">5</td>
					<td class=" txtl tab_border" style="padding: 5px 5px 5px 5px; ">용역수행 관련 문서 제출 지연 -0.1/일<BR/>각종 시설물 점검 결과제출 지연 -0.1/일<BR/>문서처리의 부적정성(반송건수) -0.1/건<BR/></td>
					<td class=" txtl tab_border"><input type="text" size="10" name="value2" value="5" maxbyte="80"	 > </td>
					<td class=" txtr tab_border"><input type="text" size="10" name="score2_2" style="text-align: right;" value="3.1" onkeyup ="evalScoreSum('score2_2','tot_score2');" onblur="evalScoreChk('5',this); " > </td>
				</tr>
				<tr class="tab_border1">
					<td class=" txtl tab_border">문서의 기록, 유지 및 보존 상태</td>
					<td class=" txtr tab_border">5</td>
					<td class=" txtl tab_border" style="padding: 5px 5px 5px 5px; ">문서의 기록관리상태 부실 -0.1/건<BR/>용역사 비치문서 관리 상태 부실 -0.1/건<BR/>당사에서 제공된 도서관리 상태 부실 -0.1/건<BR/></td>
					<td class=" txtl tab_border"><input type="text" size="10" name="value2" value="5" maxbyte="80"	 > </td>
					<td class=" txtr tab_border"><input type="text" size="10" name="score2_2" style="text-align: right;" value="2.1" onkeyup ="evalScoreSum('score2_2','tot_score2');" onblur="evalScoreChk('5',this); " > </td>
				</tr>
				<tr class="tab_border1">
					<td class=" txtl tab_border">교육관리 실태(교육이행 실적)</td>
					<td class=" txtr tab_border">5</td>
					<td class=" txtl tab_border" style="padding: 5px 5px 5px 5px; ">90% 이상 5점 [A]교육계획건수<BR/>80% - 89% 4점[B]교육이행건수<BR/>80% - 79% 3점[B]/[A] x 100% = (100)%<BR/>69% 이하 2점 [B]/[A] x 100% = (100)%<BR/></td>
					<td class=" txtl tab_border"><input type="text" size="10" name="value2" value="5" maxbyte="80"	 > </td>
					<td class=" txtr tab_border"><input type="text" size="10" name="score2_2" style="text-align: right;" value="4" onkeyup ="evalScoreSum('score2_2','tot_score2');" onblur="evalScoreChk('5',this); " > </td>
				</tr>
				<tr class="tab_border1">
					<th class=" txtl tab_border1" rowspan="2"><font><b>연구개발 (20)</b></font></th>
					<td class=" txtl tab_border">표창및 아이디어 제안										</td>
					<td class=" txtr tab_border">10</td>
					<td class=" txtl tab_border" style="padding: 5px 5px 5px 5px; ">우수사례 표창 +5점<BR/>제안 채택 건수 +5점<BR/>연구개발 등의 활동적 사례 +5점<BR/></td>
					<td class=" txtl tab_border"><input type="text" size="10" name="value2" value="10" maxbyte="80"	 > </td>
					<td class=" txtr tab_border"><input type="text" size="10" name="score2_2" style="text-align: right;" value="5" onkeyup ="evalScoreSum('score2_2','tot_score2');" onblur="evalScoreChk('10',this); " > </td>
				</tr>
				<tr class="tab_border1">
					<td class=" txtl tab_border">표창및 아이디어 제안</td>
					<td class=" txtr tab_border">10</td>
					<td class=" txtl tab_border" style="padding: 5px 5px 5px 5px; ">매우 잘함 +10점<BR/>잘함 +5점<BR/></td>
					<td class=" txtl tab_border"><input type="text" size="10" name="value2" value="10" maxbyte="80"	 > </td>
					<td class=" txtr tab_border"><input type="text" size="10" name="score2_2" style="text-align: right;" value="5" onkeyup ="evalScoreSum('score2_2','tot_score2');" onblur="evalScoreChk('10',this); "  > </td>
				</tr>
				<tr class="tab_border1">
					<th class=" txtl tab_border1" rowspan="2"><font><b>안전관리 (-20)</b></font></th>
					<td class=" txtl tab_border">인적사고</td>
					<td class=" txtr tab_border">-10</td>
					<td class=" txtl tab_border" style="padding: 5px 5px 5px 5px; ">사망자발생 (-10/건)<BR/>2주이상 부상 (-2/건)<BR/></td>
					<td class=" txtl tab_border"><input type="text" size="10" name="value2" value="-10" maxbyte="80"	 > </td>
					<td class=" txtr tab_border"><input type="text" size="10" name="score2_2" style="text-align: right;" value="-8" onkeyup ="evalScoreSum('score2_2','tot_score2');" onblur="evalScoreChk('-10',this); "  > </td>
				</tr>
				<tr class="tab_border1">
					<td class=" txtl tab_border">산업재해</td>
					<td class=" txtr tab_border">-10</td>
					<td class=" txtl tab_border" style="padding: 5px 5px 5px 5px; ">산업재해발생(-5/건)<BR/></td>
					<td class=" txtl tab_border"><input type="text" size="10" name="value2" value="-10" maxbyte="80"	 > </td>
					<td class=" txtr tab_border"><input type="text" size="10" name="score2_2" style="text-align: right;" value="-5" onkeyup ="evalScoreSum('score2_2','tot_score2');" onblur="evalScoreChk('-10',this); " > </td>
				</tr>
				<tr class="tab_border1">
					<th class=" txtl tab_border1" rowspan="2"><font><b>고객서비스 (-15)</b></font></th>
					<td class=" txtl tab_border">운행지연(평가기간동안의 누적시간)</td>
					<td class=" txtr tab_border">-10</td>
					<td class=" txtl tab_border" style="padding: 5px 5px 5px 5px; ">10분 이상 ~ 20분 미만 -2점<BR/>20분 이상 ~ 30분 미만 -3점<BR/>30분 이상 -5점<BR/></td>
					<td class=" txtl tab_border"><input type="text" size="10" name="value2" value="-10" maxbyte="80"	 > </td>
					<td class=" txtr tab_border"><input type="text" size="10" name="score2_2" style="text-align: right;" value="-3" onkeyup ="evalScoreSum('score2_2','tot_score2');" onblur="evalScoreChk('-10',this); " > </td>
				</tr>
				<tr class="tab_border1">
					<td class=" txtl tab_border">대내외 이미지 손상</td>
					<td class=" txtr tab_border">-5</td>
					<td class=" txtl tab_border" style="padding: 5px 5px 5px 5px; ">신분당선의 이미지를 중대하게 훼손 -3점<BR/>신분당선의 이미지를 경미하게 훼손 -2점<BR/></td>
					<td class=" txtl tab_border"><input type="text" size="10" name="value2" value="-5" maxbyte="80"	 > </td>
					<td class=" txtr tab_border"><input type="text" size="10" name="score2_2" style="text-align: right;" value="-2" onkeyup ="evalScoreSum('score2_2','tot_score2');" onblur="evalScoreChk('-5',this); " > </td>
				</tr>
			</tbody>
		</table>
	</div><!--하반기 div End  --><br>
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
	<div class="T_btnLayer fr">
	  		<button type="button" class="blueBtn L" id="saveBtn1">저장</button>
	  		<button type="button" class="blueBtn L" id="listBtn1">취소</button>
   		</div>	
</div>

<%-- DETAIL FORM --%>
<form id="detailFrm" method="POST" > 
	<input type="hidden" name="resourceName" value="${ param.resourceName }" >
</form>