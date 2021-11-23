<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 기술평가현황 > 평가계획수립 등록 폼
 *
 * <pre>
 * ebid 
 *    |_ tchqvlnRegistForm.jsp
 * 
 * </pre>
 * @date : 2017. 06. 16
 * @version : 1.0
 * @author : 은우소프트 전상훈
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %> 
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %> 

<link rel="stylesheet" type="text/css" href="${cssPath}/ipro/template/buttonStyle.css">
<link rel="stylesheet" type="text/css" href="${cssPath}/ipro/template/pagingPlace.css">
<link rel="stylesheet" type="text/css" href="${cssPath}/ipro/bodyBasic.css">
<link rel="stylesheet" type="text/css" href="${cssPath}/ipro/template/calendar.css">

<script type="text/javascript" src="${jsPath}/ipro/views/ebid/tchqvlnResultGnrlzResultDetail.js"></script>
 
<div class="content">
	<h3>평가결과현황 상세</h3>
	
	<div class="conts_wrap">
		<div class="btn_wrap view_btn fr">
			<span class="block2 tabBlock" style="display: none;">
				<button type="button" class="btn btn_02 btn_revise pointer" id="" >위원평가완료</button>
			</span>
	    	<button type="button" class="btn btn_02 btn_sch pointer" id="listBtn" >목록</button>
	    </div>
		<div class="contentTitle" style="">기술평가 기본정보</div>
		<table>
	    	<colgroup>
	        	<col width="15%" />
	            <col width="35%" />
	            <col width="15%" />
	            <col width="35%" />
	        </colgroup>
	    	<tr>
	        	<th>기술평가일자</th>
	            <td>2017-06-28</td>
	            <th>총기술평가위원수</th>
	            <td>2</td>
	        </tr>
	        <tr>
	            <th>기술평가장소</th>
	            <td>기술평가실</td>
	            <th>소집서면구분</th>
	            <td>소집</td>
	        </tr>
	    </table>
	</div>
	
<!-- 	<table class="top20"> -->
<!-- 		<tr> -->
<!-- 		    <td> -->
<!-- 		    	<div id='1' class="txtc tab_on"  style="width: 100px; float: left;"><a href='javascript:tabEvent(1);' target='_self'>종합평가</a></div> -->
<!-- 		       	<div id='2' class="txtc tab_off" style="width: 100px; float: left;"><a href='javascript:tabEvent(2);' target='_self'>위원평가</a></div> -->
<!-- 		       	<div id='3' class="txtc tab_off" style="width: 100px; float: left;"><a href='javascript:tabEvent(3);' target='_self'>정량평가결과</a></div> -->
<!-- 		       	<div id='4' class="txtc tab_off" style="width: 100px; float: left;"><a href='javascript:tabEvent(4);' target='_self'>가점내역</a></div> -->
<!-- 		       	<div id='5' class="txtc tab_off" style="width: 100px; float: left;"><a href='javascript:tabEvent(5);' target='_self'>감점내역</a></div> -->
<!-- 		       	<div id='6' class="txtc tab_off" style="width: 100px; float: left;"><a href='javascript:tabEvent(6);' target='_self'>평가업체1</a></div> -->
<!-- 				<div id='7' class="txtc tab_off" style="width: 100px; float: left;"><a href='javascript:tabEvent(7);' target='_self'>평가업채2</a></div>		       	 -->
<!-- 		    </td> -->
<!-- 		</tr> -->
<!-- 	</table> -->
	
	<div class="view_area top20" style="width: 98%; padding-left: 10px;">
        <table style="width: 100%">
        	<tr>
				<td background='${imagePath}/ipro/tab/tab_bg.gif '>
					<table>
						<tr style="width: 100%;">
							<td style="width: 10px;"><img src="${imagePath}/ipro/tab/tab_on_1.gif " alt=""></td>
							<td style="width: 60px; text-align: center;" class="tab_on" id="1"><a href='javascript:tabEvent(1);' target='_self'>종합평가</a></td>
							<td style="width: 10px;"><img src="${imagePath}/ipro/tab/tab_on_2.gif " alt=""></td>
							<td style="width: 10px;"><img src="${imagePath}/ipro/tab/tab_off_1.gif " alt=""></td>
							<td style="width: 60px; text-align: center;" class="tab_off" id="2"><a href='javascript:tabEvent(2);' target='_self'>위원평가</a></td>
							<td style="width: 10px;"><img src="${imagePath}/ipro/tab/tab_off_2.gif " alt=""></td>
							<td style="width: 10px;"><img src="${imagePath}/ipro/tab/tab_off_1.gif " alt=""></td>
							<td style="width: 80px; text-align: center;" class="tab_off" id="3"><a href='javascript:tabEvent(3);' target='_self'>정량평가결과</a></td>
							<td style="width: 10px;"><img src="${imagePath}/ipro/tab/tab_off_2.gif " alt=""></td>
							<td style="width: 10px;"><img src="${imagePath}/ipro/tab/tab_off_1.gif " alt=""></td>
							<td style="width: 60px; text-align: center;" class="tab_off" id="4"><a href='javascript:tabEvent(4);' target='_self'>가점내역</a></td>
							<td style="width: 10px;"><img src="${imagePath}/ipro/tab/tab_off_2.gif " alt=""></td>
							<td style="width: 10px;"><img src="${imagePath}/ipro/tab/tab_off_1.gif " alt=""></td>
							<td style="width: 60px; text-align: center;" class="tab_off" id="5"><a href='javascript:tabEvent(5);' target='_self'>감점내역</a></td>
							<td style="width: 10px;"><img src="${imagePath}/ipro/tab/tab_off_2.gif " alt=""></td>
							<td style="width: 10px;"><img src="${imagePath}/ipro/tab/tab_off_1.gif " alt=""></td>
							<td style="width: 60px; text-align: center;" class="tab_off" id="6"><a href='javascript:tabEvent(6);' target='_self'>평가업체1</a></td>
							<td style="width: 10px;"><img src="${imagePath}/ipro/tab/tab_off_2.gif " alt=""></td>
							<td style="width: 10px;"><img src="${imagePath}/ipro/tab/tab_off_1.gif " alt=""></td>
							<td style="width: 60px; text-align: center;" class="tab_off" id="7"><a href='javascript:tabEvent(7);' target='_self'>평가업체2</a></td>
							<td style="width: 10px;"><img src="${imagePath}/ipro/tab/tab_off_2.gif " alt=""></td>
						</tr>
					</table>
				</td>
        	</tr>
        </table>
  	</div>
	
	<div class="block1 tabBlock top20">
		<div class="conts_wrap">
			<table>
		    	<colgroup>
		        	<col width="15%" />
		            <col width="35%" />
		            <col width="15%" />
		            <col width="35%" />
		        </colgroup>
		    	<tr>
		        	<th>상임위원수</th>
		            <td>1명</td>
		            <th>비상임위원수</th>
		            <td>1명</td>
		        </tr>
			</table>
			* 기술평가 결과 , 적격업체인 경우 <font color="blue">파란색</font> / 부적격업체인 경우 <font color="red">빨간색</font>으로 점수 표기		
			<table>
		    	<colgroup>
		        	<col width="*" />
		            <col width="15%" />
		            <col width="15%" />
		            <col width="15%" />
		            <col width="5%" />
		            <col width="15%" />
		        </colgroup>
		        <tr>
		        	<th class="txtc">평가대상업체</th>
		        	<th class="txtc">정량평가점수(A)</th>
		        	<th class="txtc">정성평가 점수 평균(B)</th>
		        	<th class="txtc">평가점수</th>
		        	<th class="txtc">순위</th>
		        	<th class="txtc">의견서보기</th>
				</tr>
				<tr>
					<td class="pointer" onclick="tchqvlnResultTotEvlScoreInqire();"><font color="blue">주식회사 은우소프트</font></td>
					<td class="txtc">17.25</td>
					<td class="txtc">72</td>
					<td class="txtc">89.25</td>
					<td class="txtc">1</td>
					<td class="txtc"><button type="button" class="btn btn_02 btn_sch pointer" onclick="qualEvlWrtopnInqire();" >의견서보기</button></td>
				</tr>
				<tr>
					<td class="pointer" onclick="tchqvlnResultTotEvlScoreInqire();"><font color="blue">LIZ시스템</font></td>
					<td class="txtc">10.25</td>
					<td class="txtc">68</td>
					<td class="txtc">78.25</td>
					<td class="txtc">2</td>
					<td class="txtc"><button type="button" class="btn btn_02 btn_sch pointer" onclick="qualEvlWrtopnInqire();" >의견서보기</button></td>
				</tr>
			</table>
		</div>
	</div>
	
	<div style="display: none;" class="block2 tabBlock top20">
		<div class="conts_wrap">
			* 상임위원
			<table>
		    	<colgroup>
		            <col width="7%" />
		            <col width="7%" />
		            <col width="10%" />
		            <col width="10%" />
		            <col width="10%" />
		            <col width="10%" />
		            <col width="7%" />
		            <col width="7%" />
		        	<col width="*" />
		        </colgroup>
		        <tr>
		        	<th class="txtc">성명</th>
		        	<th class="txtc">평가상태</th>
		        	<th class="txtc">평가이해 및<br>준비도</th>
		        	<th class="txtc">질의내용의<br>우수성</th>
		        	<th class="txtc">의견서작성<br>충성도</th>
		        	<th class="txtc">평가태도 및<br>협조도</th>
		        	<th class="txtc">질의 횟수</th>
					<th class="txtc">종합등급</th>
		        	<th class="txtc">기타의견</th>
				</tr>
				<tr>
					<td class="txtc pointer" onclick="qualEvlScoreDetail();">고길동</td>
					<td class="txtc">평가완료</td>
					<td class="txtc">
						<select name="num1" style="width: 70px;">
							<option value="0">선택</option>
							<option value="5">매우우수</option>
							<option value="4">우수</option>
							<option value="3">보통</option>
							<option value="2">미흡</option>
							<option value="1">매우미흡</option>
						</select>
					</td>
					<td class="txtc">
						<select name="num1" style="width: 70px;">
							<option value="0">선택</option>
							<option value="5">매우우수</option>
							<option value="4">우수</option>
							<option value="3">보통</option>
							<option value="2">미흡</option>
							<option value="1">매우미흡</option>
						</select>
					</td>
					<td class="txtc">
						<select name="num1" style="width: 70px;">
							<option value="0">선택</option>
							<option value="5">매우우수</option>
							<option value="4">우수</option>
							<option value="3">보통</option>
							<option value="2">미흡</option>
							<option value="1">매우미흡</option>
						</select>
					</td>
					<td class="txtc">
						<select name="num1" style="width: 70px;">
							<option value="0">선택</option>
							<option value="5">매우우수</option>
							<option value="4">우수</option>
							<option value="3">보통</option>
							<option value="2">미흡</option>
							<option value="1">매우미흡</option>
						</select>
					</td>
					<td class="txtc"><input type="text" class="undisabled" style="width: 70px;"></td>
					<td class="txtc num1Sum"></td>
					<td class="txtc"><input type="text" class="undisabled" style="width: 95%"></td>
				</tr>
			</table>
			* 비상임위원
			<table>
		    	<colgroup>
		        	<col width="7%" />
		            <col width="7%" />
		            <col width="10%" />
		            <col width="10%" />
		            <col width="10%" />
		            <col width="10%" />
		            <col width="7%" />
		            <col width="7%" />
		        	<col width="*" />
		        </colgroup>
		        <tr>
		        	<th class="txtc">성명</th>
		        	<th class="txtc">평가상태</th>
		        	<th class="txtc">평가이해 및<br>준비도</th>
		        	<th class="txtc">질의내용의<br>우수성</th>
		        	<th class="txtc">의견서작성<br>충성도</th>
		        	<th class="txtc">평가태도 및<br>협조도</th>
		        	<th class="txtc">질의 횟수</th>
					<th class="txtc">종합등급</th>
		        	<th class="txtc">기타의견</th>
				</tr>
				<tr>
					<td class="txtc pointer" onclick="qualEvlScoreDetail();">이상수</td>
					<td class="txtc">평가완료</td>
					<td class="txtc">
						<select name="num2" style="width: 70px;">
							<option value="0">선택</option>
							<option value="5">매우우수</option>
							<option value="4">우수</option>
							<option value="3">보통</option>
							<option value="2">미흡</option>
							<option value="1">매우미흡</option>
						</select>
					</td>
					<td class="txtc">
						<select name="num2" style="width: 70px;">
							<option value="0">선택</option>
							<option value="5">매우우수</option>
							<option value="4">우수</option>
							<option value="3">보통</option>
							<option value="2">미흡</option>
							<option value="1">매우미흡</option>
						</select>
					</td>
					<td class="txtc">
						<select name="num2" style="width: 70px;">
							<option value="0">선택</option>
							<option value="5">매우우수</option>
							<option value="4">우수</option>
							<option value="3">보통</option>
							<option value="2">미흡</option>
							<option value="1">매우미흡</option>
						</select>
					</td>
					<td class="txtc">
						<select name="num2" style="width: 70px;">
							<option value="0">선택</option>
							<option value="5">매우우수</option>
							<option value="4">우수</option>
							<option value="3">보통</option>
							<option value="2">미흡</option>
							<option value="1">매우미흡</option>
						</select>
					</td>
					<td class="txtc"><input type="text" class="undisabled" style="width: 70px;"></td>
					<td class="txtc num2Sum"></td>
					<td class="txtc"><input type="text" class="undisabled" style="width: 95%;"></td>
				</tr>
			</table>
		</div>
	</div>
	
	<div class="block3 tabBlock top20" style="display: none;">
		<div class="conts_wrap">
			<table>
		    	<colgroup>
		        	<col width="10%" />
		            <col width="15%" />
		            <col width="5%" />
		            <col width="30%" />
		            <col width="5%" />
		            <col width="30%" />
		            <col width="5%" />
		        </colgroup>
		        <tr>
		        	<th class="txtc" rowspan="2">평가구분</th>
		        	<th class="txtc" rowspan="2">평가항목구분</th>
		        	<th class="txtc" rowspan="2">배점</th>
		        	<th class="txtc pointer" colspan="2">주식회사 은우소프트</th>
		        	<th class="txtc pointer" colspan="2">LIZ시스템</th>
				</tr>
				<tr>
		        	<th class="txtc" >내용</th>
		        	<th class="txtc" >배점</th>
		        	<th class="txtc" >내용</th>
		        	<th class="txtc" >배점</th>
				</tr>
				<tr>
					<td>경영상태</td>
					<td>신용평가등급</td>
					<td class="txtc">8</td>
					<td class="txtc"><textarea rows="4" cols="" style="width: 85%" disabled="disabled">경영상태 좋음</textarea></td>
					<td class="txtc"><input type="text" style="width: 85%" value="6" disabled="disabled"></td>
					<td class="txtc"><textarea rows="4" cols="" style="width: 85%" disabled="disabled">경영상태 나쁨</textarea></td>
					<td class="txtc"><input type="text" style="width: 85%" value="2" disabled="disabled"></td>
				</tr>
				<tr>
					<td>회사 유사사업 수행실적</td>
					<td>국내외 유사사업 수행실적 비율</td>
					<td class="txtc">6</td>
					<td class="txtc"><textarea rows="4" cols="" style="width: 85%" disabled="disabled">유사사업 수행실적 다수</textarea></td>
					<td class="txtc"><input type="text" style="width: 85%" value="5" disabled="disabled"></td>
					<td class="txtc"><textarea rows="4" cols="" style="width: 85%" disabled="disabled">유사사업 수행실적 부족</textarea></td>
					<td class="txtc"><input type="text" style="width: 85%" value="2" disabled="disabled"></td>
				</tr>
				<tr>
					<td>핵심투입인력 유사사업 수행실적</td>
					<td>국내외 유사사업 수행실적 건수</td>
					<td class="txtc">6</td>
					<td class="txtc"><textarea rows="4" cols="" style="width: 85%" disabled="disabled">수행실적건수 적절</textarea></td>
					<td class="txtc"><input type="text" style="width: 85%" disabled="disabled" value="3"></td>
					<td class="txtc"><textarea rows="4" cols="" style="width: 85%" disabled="disabled">수행실적건수 적절</textarea></td>
					<td class="txtc"><input type="text" style="width: 85%" disabled="disabled" value="3"></td>
				</tr>
				<tr>
					<td class="txtc" rowspan="2">가감점인정내역</td>
					<td class="txtc">가점인정내역</td>
					<td class="txtc"></td>
					<td class="txtc">"가점내역"탭 참조</td>
					<td class="txtr">3.25</td>
					<td class="txtc">"가점내역"탭 참조</td>
					<td class="txtr">3.25</td>
				</tr>
				<tr>
					<td class="txtc">감점인정내역</td>
					<td class="txtc"></td>
					<td class="txtc">"감점내역"탭 참조</td>
					<td class="txtr">0</td>
					<td class="txtc">"감점내역"탭 참조</td>
					<td class="txtr">0</td>
				</tr>
				<tr>
					<th class="txtc" colspan="2">총점</th>
					<th class="txtc">20</th>
					<td class="txtc" colspan="2">17.25</td>
					<td class="txtc" colspan="2">10.25</td>
				</tr>
			</table>
		</div>
	</div>
	
	<div style="display: none;" class="block4 tabBlock top20">
		<div class="conts_wrap">
			<table>
		    	<colgroup>
		    		<col width="10%" />
		            <col width="12%" />
		            <col width="8%" />
		            <col width="30%" />
		            <col width="5%" />
		            <col width="30%" />
		            <col width="5%" />
		        </colgroup>
		        <tr>
		        	<th class="txtc" colspan="2" rowspan="2">평가항목</th>
		        	<th class="txtc" rowspan="2">배점</th>
		        	<th class="txtc" colspan="2">주식회사 은우소프트</th>
		        	<th class="txtc" colspan="2">LIZ시스템</th>
				</tr>
				<tr>
		        	<th class="txtc" >내용</th>
		        	<th class="txtc" >배점</th>
		        	<th class="txtc" >내용</th>
		        	<th class="txtc" >배점</th>
				</tr>
				<tr>
					<td colspan="2">A. 계약실적이 없는 업체(단, 공동수급체의 경우 지분율/분담률이 20%이상인 경우 적용)</td>
					<td class="txtc">1.00</td>
					<td class="txtc"><textarea rows="4" cols="" style="width: 85%">본사와 계약실적이 없음</textarea></td>
					<td class="txtc"><input type="text" style="width: 85%" value="1"></td>
					<td class="txtc"><textarea rows="4" cols="" style="width: 85%">본사와 계약실적이 없음</textarea></td>
					<td class="txtc"><input type="text" style="width: 85%" value="1"></td>
				</tr>
				<tr>
					<td colspan="2">B. 단독 또는 공동수급으로 참여한 입찰참여업체가 중소기업인 경우(단, 공동수급체의 경우 지분율/분담률이 20%이상인 경우 적용) </td>
					<td class="txtc">1.25</td>
					<td class="txtc"><textarea rows="4" cols="" style="width: 85%">중소기업임</textarea></td>
					<td class="txtc"><input type="text" style="width: 85%" value="1.25"></td>
					<td class="txtc"><textarea rows="4" cols="" style="width: 85%">중소기업임</textarea></td>
					<td class="txtc"><input type="text" style="width: 85%" value="1.25"></td>
				</tr>
				<tr>
					<td colspan="7">
						C. 여성기업지원 및 여성고용촉진<br>
						<font color="red">
							※(주의 사항)<br>
							1) '여성기업지원 및 여성고용촉진' 평가항목 내에 각 항목은 중복하여 평가할 수 없고, 그 중 높은 평점 한 가지만 인정<br>
							2) (여성고용 우수기업)과 (장애인고용 우수기업)의 평가에 중복되는 경우, 그 중 높은 평점 한 가지만 평가)
						</font> 
					</td>
				</tr>
				<tr>
					<td rowspan="2">(1) 여성기업</td>
					<td>3년이상</td>
					<td class="txtc">0.50</td>
					<td class="txtc"><textarea rows="4" cols="" style="width: 85%"></textarea></td>
					<td class="txtc"><input type="text" style="width: 85%"></td>
					<td class="txtc"><textarea rows="4" cols="" style="width: 85%"></textarea></td>
					<td class="txtc"><input type="text" style="width: 85%"></td>
				</tr>
				<tr>
					<td>3년미만</td>
					<td class="txtc">0.25</td>
					<td class="txtc"><textarea rows="4" cols="" style="width: 85%"></textarea></td>
					<td class="txtc"><input type="text" style="width: 85%"></td>
					<td class="txtc"><textarea rows="4" cols="" style="width: 85%"></textarea></td>
					<td class="txtc"><input type="text" style="width: 85%"></td>
				</tr>
				<tr>
					<td rowspan="2">(2) 여성고용 우수기업</td>
					<td>최근 3개월 평균 여성고용률이 10%이상이면서 최근 3개월 평균 여성종업원이 10인 이상인 기업</td>
					<td class="txtc">0.50</td>
					<td class="txtc"><textarea rows="4" cols="" style="width: 85%"></textarea></td>
					<td class="txtc"><input type="text" style="width: 85%"></td>
					<td class="txtc"><textarea rows="4" cols="" style="width: 85%"></textarea></td>
					<td class="txtc"><input type="text" style="width: 85%"></td>
				</tr>
				<tr>
					<td>최근 3개월 평균 여성고용률이 5%이상 이면서 최근 3개월 평균 여성종업원이 5인 이상인 기업</td>
					<td class="txtc">0.25</td>
					<td class="txtc"><textarea rows="4" cols="" style="width: 85%"></textarea></td>
					<td class="txtc"><input type="text" style="width: 85%"></td>
					<td class="txtc"><textarea rows="4" cols="" style="width: 85%"></textarea></td>
					<td class="txtc"><input type="text" style="width: 85%"></td>
				</tr>
				<tr>
					<td colspan="2">(3)고용노동부장관에 의해 남녀고용 평등우수기업으로 지정받은 자</td>
					<td class="txtc">0.50</td>
					<td class="txtc"><textarea rows="4" cols="" style="width: 85%"></textarea></td>
					<td class="txtc"><input type="text" style="width: 85%"></td>
					<td class="txtc"><textarea rows="4" cols="" style="width: 85%"></textarea></td>
					<td class="txtc"><input type="text" style="width: 85%"></td>
				</tr>
				<tr>
					<td colspan="7">
						D. 장애인기업지원 및 장애인 고용촉진<br>
						<font color="red">
							※(주의 사항)<br>
							1) '장애인기업지원 및 장애인 고용촉진' 평가항목 내에 각 항목은 중복하여 평가할 수 없고, 그 중 높은 평점 한 가지만 인정<br>
							2) (장애인고용 우수기업)과 (여성고용 우수기업)의 평가에 중복되는 경우, 그 중 높은 평점 한 가지만 평가)
						</font> 
					</td>
				</tr>
				<tr>
					<td colspan="2">(1) 장애인기업:「장애인기업활동 촉진법」에서 정한 기업으로서, 중소기업청장(위임받은 자를 포함)에 의해 확인된 경우 인정</td>
					<td class="txtc">0.50</td>
					<td class="txtc"><textarea rows="4" cols="" style="width: 85%"></textarea></td>
					<td class="txtc"><input type="text" style="width: 85%"></td>
					<td class="txtc"><textarea rows="4" cols="" style="width: 85%"></textarea></td>
					<td class="txtc"><input type="text" style="width: 85%"></td>
				</tr>
				<tr>
					<td rowspan="2">(2) 장애인고용 우수기업</td>
					<td>「장애인고용촉진 및 직업재활법」제24조에 따른 장애인고용우수사업주</td>
					<td class="txtc">0.50</td>
					<td class="txtc"><textarea rows="4" cols="" style="width: 85%"></textarea></td>
					<td class="txtc"><input type="text" style="width: 85%"></td>
					<td class="txtc"><textarea rows="4" cols="" style="width: 85%"></textarea></td>
					<td class="txtc"><input type="text" style="width: 85%"></td>
				</tr>
				<tr>
					<td>최근 3개월 평균 장애인 고용률이 「장애인 고용촉진 및 직업재활법 시행령」 제25조에 의한 의무고용률(2.5%) 이상인 기업</td>
					<td class="txtc">0.25</td>
					<td class="txtc"><textarea rows="4" cols="" style="width: 85%"></textarea></td>
					<td class="txtc"><input type="text" style="width: 85%"></td>
					<td class="txtc"><textarea rows="4" cols="" style="width: 85%"></textarea></td>
					<td class="txtc"><input type="text" style="width: 85%"></td>
				</tr>
				<tr>
					<td colspan="2">E. 「사회적기업육성법」제7조에 따른 고용노동부장관의 인증을 받은 기업</td>
					<td class="txtc">1.00</td>
					<td class="txtc"><textarea rows="4" cols="" style="width: 85%">인증받음</textarea></td>
					<td class="txtc"><input type="text" style="width: 85%" value="1"></td>
					<td class="txtc"><textarea rows="4" cols="" style="width: 85%">인증받음</textarea></td>
					<td class="txtc"><input type="text" style="width: 85%" value="1"></td>
				</tr>
				<tr>
					<td colspan="2">F. 핵심투입인력이 입찰공고일로부터 5년이내ODA 전문가 자격증(1,2,3급)취득자인 경우</td>
					<td class="txtc">인당 1점<br>(최대 2점)</td>
					<td class="txtc"><textarea rows="4" cols="" style="width: 85%"></textarea></td>
					<td class="txtc"><input type="text" style="width: 85%"></td>
					<td class="txtc"><textarea rows="4" cols="" style="width: 85%"></textarea></td>
					<td class="txtc"><input type="text" style="width: 85%"></td>
				</tr>
				<tr>
					<th class="txtc" colspan="3">총점</th>
					<td class="txtc" colspan="2">3.25</td>
					<td class="txtc" colspan="2">3.25</td>
				</tr>
			</table>
		</div>
	</div>
	
	<div style="display: none;" class="block5 tabBlock top20">
		<div class="conts_wrap">
			<table>
		    	<colgroup>
		            <col width="22%" />
		            <col width="8%" />
		            <col width="30%" />
		            <col width="5%" />
		            <col width="30%" />
		            <col width="5%" />
		        </colgroup>
		        <tr>
		        	<th class="txtc" rowspan="2">평가항목</th>
		        	<th class="txtc" rowspan="2">배점</th>
		        	<th class="txtc" colspan="2">주식회사 은우소프트</th>
		        	<th class="txtc" colspan="2">LIZ시스템</th>
				</tr>
				<tr>
		        	<th class="txtc" >내용</th>
		        	<th class="txtc" >배점</th>
		        	<th class="txtc" >내용</th>
		        	<th class="txtc" >배점</th>
				</tr>
				<tr>
					<td>G. 입찰공고일 기준 최근 5년 이내 추진 또는 완료한 사업에서 불성실한 사업수행으로 서면경고 등 제재, 수원국의 문제제기를 받은 경우 또는 사업수행평가 결과가 부적격(60점이하)인 업체</td>
					<td class="txtc">건별/1점</td>
					<td class="txtc"><textarea rows="4" cols="" style="width: 85%"></textarea></td>
					<td class="txtc"><input type="text" style="width: 85%"></td>
					<td class="txtc"><textarea rows="4" cols="" style="width: 85%"></textarea></td>
					<td class="txtc"><input type="text" style="width: 85%"></td>
				</tr>
				<tr>
					<td>H. 제안사의 사업핵심인력이 타용역 중복참여 시 업무량은 현재 수행중인 용역 잔여과업기간이 개찰일 기준 3개월 이상인 것에 한함</td>
					<td class="txtc">건별/0.5점</td>
					<td class="txtc"><textarea rows="4" cols="" style="width: 85%"></textarea></td>
					<td class="txtc"><input type="text" style="width: 85%"></td>
					<td class="txtc"><textarea rows="4" cols="" style="width: 85%"></textarea></td>
					<td class="txtc"><input type="text" style="width: 85%"></td>
				</tr>
				<tr>
					<td>I. 제안서의 규격, 매수, 형식, 색상 등에 부합하지 않는경우</td>
					<td class="txtc">건별/0.25점<br>(최대 2점)</td>
					<td class="txtc"><textarea rows="4" cols="" style="width: 85%"></textarea></td>
					<td class="txtc"><input type="text" style="width: 85%"></td>
					<td class="txtc"><textarea rows="4" cols="" style="width: 85%"></textarea></td>
					<td class="txtc"><input type="text" style="width: 85%"></td>
				</tr>
				<tr>
					<td>J. 입찰공고일 기준 최근 2년 이내에 공정거래위원장으로부터 하도급 상습법위반자 또는 불공정거래 행위 위반자로 통보 받은 자</td>
					<td class="txtc">2.00</td>
					<td class="txtc"><textarea rows="4" cols="" style="width: 85%"></textarea></td>
					<td class="txtc"><input type="text" style="width: 85%"></td>
					<td class="txtc"><textarea rows="4" cols="" style="width: 85%"></textarea></td>
					<td class="txtc"><input type="text" style="width: 85%"></td>
				</tr>
				<tr>
					<th class="txtc" colspan="2">총점</th>
					<td class="txtc" colspan="2">0.00</td>
					<td class="txtc" colspan="2">0.00</td>
				</tr>
			</table>
		</div>
	</div>
	
	<div style="display: none;" class="block6 tabBlock top20">
		<div class="conts_wrap">
			<table>
		    	<colgroup>
		        	<col width="15%" />
		            <col width="35%" />
		            <col width="15%" />
		            <col width="35%" />
		        </colgroup>
		    	<tr>
		        	<th>평가업체명</th>
		            <td>주식회사 은우소프트</td>
		            <th>최종평점<br>(최고, 최저점 제외)</th>
		            <td class="txtr">72</td>
		        </tr>
			</table>
			<div class="contentTitle" style="">상임위원</div>
			<table>
		    	<colgroup>
		        	<col width="20%" />
		            <col width="10%" />
		            <col width="10%" />
		            <col width="*" />
		        </colgroup>
		    	<tr>
		        	<th class="txtc">평가항목</th>
		            <th class="txtc">배점</th>
		            <th class="txtc">고길동</th>
		            <td class="txtc"></td>
		        </tr>
		        <tr>
		        	<th class="txtl">일반현황 및 연혁 등</th>
		            <th class="txtc">5</th>
		            <td class="txtc">5</td>
		            <td class="txtc"></td>
		        </tr>
		        <tr>
		        	<th class="txtl">사업이해도</th>
		            <th class="txtc">4</th>
		            <td class="txtc">4</td>
		            <td class="txtc"></td>
		        </tr>
		        <tr>
		        	<th class="txtl">추진전략</th>
		            <th class="txtc">4</th>
		            <td class="txtc">4</td>
		            <td class="txtc"></td>
		        </tr>
		        <tr>
		        	<th class="txtl">성과관리</th>
		            <th class="txtc">4</th>
		            <td class="txtc">4</td>
		            <td class="txtc"></td>
		        </tr>
		        <tr>
		        	<th class="txtl">컨설팅 방법론</th>
		            <th class="txtc">3</th>
		            <td class="txtc">3</td>
		            <td class="txtc"></td>
		        </tr>
		        <tr>
		        	<th class="txtl">컨설팅 요구사항</th>
		            <th class="txtc">10</th>
		            <td class="txtc">9</td>
		            <td class="txtc"></td>
		        </tr>
		        <tr>
		        	<th class="txtl">제약사항</th>
		            <th class="txtc">10</th>
		            <td class="txtc">9</td>
		            <td class="txtc"></td>
		        </tr>
		        <tr>
		        	<th class="txtl">관리방법론</th>
		            <th class="txtc">5</th>
		            <td class="txtc">4</td>
		            <td class="txtc"></td>
		        </tr>
		        <tr>
		        	<th class="txtl">개발장비</th>
		            <th class="txtc">5</th>
		            <td class="txtc">4</td>
		            <td class="txtc"></td>
		        </tr>
		        <tr>
		        	<th class="txtl">일정관리</th>
		            <th class="txtc">4</th>
		            <td class="txtc">3</td>
		            <td class="txtc"></td>
		        </tr>
		        <tr>
		        	<th class="txtl">위험관리</th>
		            <th class="txtc">4</th>
		            <td class="txtc">3</td>
		            <td class="txtc"></td>
		        </tr>
		        <tr>
		        	<th class="txtl">사후관리</th>
		            <th class="txtc">4</th>
		            <td class="txtc">3</td>
		            <td class="txtc"></td>
		        </tr>
		        <tr>
		        	<th class="txtl">모니터링 및 보고</th>
		            <th class="txtc">3</th>
		            <td class="txtc">3</td>
		            <td class="txtc"></td>
		        </tr>
		        <tr>
		        	<th class="txtl">제안요청내용 개선사항</th>
		            <th class="txtc">3</th>
		            <td class="txtc">3</td>
		            <td class="txtc"></td>
		        </tr>
		        <tr>
		        	<th class="txtl">추가 투입계획</th>
		            <th class="txtc">2</th>
		            <td class="txtc">2</td>
		            <td class="txtc"></td>
		        </tr>
		        <tr>
		        	<th class="txtl">테스트 점수</th>
		            <th class="txtc">10</th>
		            <td class="txtc">9</td>
		            <td class="txtc"></td>
		        </tr>
		        <tr>
		        	<th class="txtc">총점</th>
		            <th class="txtc">80</th>
		            <th class="txtc">72</th>
		            <td class="txtc"></td>
		        </tr>
			</table>
			
			<div class="contentTitle" style="">비상임위원</div>
			<table>
		    	<colgroup>
		        	<col width="20%" />
		            <col width="10%" />
		            <col width="10%" />
		            <col width="*" />
		        </colgroup>
		    	<tr>
		        	<th class="txtc">평가항목</th>
		            <th class="txtc">배점</th>
		            <th class="txtc">이상수</th>
		            <td class="txtc"></td>
		        </tr>
		        <tr>
		        	<th class="txtl">일반현황 및 연혁 등</th>
		            <th class="txtc">5</th>
		            <td class="txtc">5</td>
		            <td class="txtc"></td>
		        </tr>
		        <tr>
		        	<th class="txtl">사업이해도</th>
		            <th class="txtc">4</th>
		            <td class="txtc">3</td>
		            <td class="txtc"></td>
		        </tr>
		        <tr>
		        	<th class="txtl">추진전략</th>
		            <th class="txtc">4</th>
		            <td class="txtc">3</td>
		            <td class="txtc"></td>
		        </tr>
		        <tr>
		        	<th class="txtl">성과관리</th>
		            <th class="txtc">4</th>
		            <td class="txtc">4</td>
		            <td class="txtc"></td>
		        </tr>
		        <tr>
		        	<th class="txtl">컨설팅 방법론</th>
		            <th class="txtc">3</th>
		            <td class="txtc">3</td>
		            <td class="txtc"></td>
		        </tr>
		        <tr>
		        	<th class="txtl">컨설팅 요구사항</th>
		            <th class="txtc">10</th>
		            <td class="txtc">9</td>
		            <td class="txtc"></td>
		        </tr>
		        <tr>
		        	<th class="txtl">제약사항</th>
		            <th class="txtc">10</th>
		            <td class="txtc">9</td>
		            <td class="txtc"></td>
		        </tr>
		        <tr>
		        	<th class="txtl">관리방법론</th>
		            <th class="txtc">5</th>
		            <td class="txtc">4</td>
		            <td class="txtc"></td>
		        </tr>
		        <tr>
		        	<th class="txtl">개발장비</th>
		            <th class="txtc">5</th>
		            <td class="txtc">5</td>
		            <td class="txtc"></td>
		        </tr>
		        <tr>
		        	<th class="txtl">일정관리</th>
		            <th class="txtc">4</th>
		            <td class="txtc">4</td>
		            <td class="txtc"></td>
		        </tr>
		        <tr>
		        	<th class="txtl">위험관리</th>
		            <th class="txtc">4</th>
		            <td class="txtc">3</td>
		            <td class="txtc"></td>
		        </tr>
		        <tr>
		        	<th class="txtl">사후관리</th>
		            <th class="txtc">4</th>
		            <td class="txtc">3</td>
		            <td class="txtc"></td>
		        </tr>
		        <tr>
		        	<th class="txtl">모니터링 및 보고</th>
		            <th class="txtc">3</th>
		            <td class="txtc">3</td>
		            <td class="txtc"></td>
		        </tr>
		        <tr>
		        	<th class="txtl">제안요청내용 개선사항</th>
		            <th class="txtc">3</th>
		            <td class="txtc">3</td>
		            <td class="txtc"></td>
		        </tr>
		        <tr>
		        	<th class="txtl">추가 투입계획</th>
		            <th class="txtc">2</th>
		            <td class="txtc">2</td>
		            <td class="txtc"></td>
		        </tr>
		        <tr>
		        	<th class="txtl">테스트 점수</th>
		            <th class="txtc">10</th>
		            <td class="txtc">9</td>
		            <td class="txtc"></td>
		        </tr>
		        <tr>
		        	<th class="txtc">총점</th>
		            <th class="txtc">80</th>
		            <th class="txtc">72</th>
		            <td class="txtc"></td>
		        </tr>
			</table>
		</div>
	</div>
	
	<div style="display: none;" class="block7 tabBlock top20">
		<div class="conts_wrap">
			<table>
		    	<colgroup>
		        	<col width="15%" />
		            <col width="35%" />
		            <col width="15%" />
		            <col width="35%" />
		        </colgroup>
		    	<tr>
		        	<th>평가업체명</th>
		            <td>LIZ시스템</td>
		            <th>최종평점<br>(최고, 최저점 제외)</th>
		            <td class="txtr">68</td>
		        </tr>
			</table>
			<div class="contentTitle" style="">상임위원</div>
			<table>
		    	<colgroup>
		        	<col width="20%" />
		            <col width="10%" />
		            <col width="10%" />
		            <col width="*" />
		        </colgroup>
		    	<tr>
		        	<th class="txtc">평가항목</th>
		            <th class="txtc">배점</th>
		            <th class="txtc">고길동</th>
		            <td class="txtc"></td>
		        </tr>
		        <tr>
		        	<th class="txtl">일반현황 및 연혁 등</th>
		            <th class="txtc">5</th>
		            <td class="txtc">5</td>
		            <td class="txtc"></td>
		        </tr>
		        <tr>
		        	<th class="txtl">사업이해도</th>
		            <th class="txtc">4</th>
		            <td class="txtc">3</td>
		            <td class="txtc"></td>
		        </tr>
		        <tr>
		        	<th class="txtl">추진전략</th>
		            <th class="txtc">4</th>
		            <td class="txtc">3</td>
		            <td class="txtc"></td>
		        </tr>
		        <tr>
		        	<th class="txtl">성과관리</th>
		            <th class="txtc">4</th>
		            <td class="txtc">3</td>
		            <td class="txtc"></td>
		        </tr>
		        <tr>
		        	<th class="txtl">컨설팅 방법론</th>
		            <th class="txtc">3</th>
		            <td class="txtc">2</td>
		            <td class="txtc"></td>
		        </tr>
		        <tr>
		        	<th class="txtl">컨설팅 요구사항</th>
		            <th class="txtc">10</th>
		            <td class="txtc">9</td>
		            <td class="txtc"></td>
		        </tr>
		        <tr>
		        	<th class="txtl">제약사항</th>
		            <th class="txtc">10</th>
		            <td class="txtc">9</td>
		            <td class="txtc"></td>
		        </tr>
		        <tr>
		        	<th class="txtl">관리방법론</th>
		            <th class="txtc">5</th>
		            <td class="txtc">4</td>
		            <td class="txtc"></td>
		        </tr>
		        <tr>
		        	<th class="txtl">개발장비</th>
		            <th class="txtc">5</th>
		            <td class="txtc">4</td>
		            <td class="txtc"></td>
		        </tr>
		        <tr>
		        	<th class="txtl">일정관리</th>
		            <th class="txtc">4</th>
		            <td class="txtc">3</td>
		            <td class="txtc"></td>
		        </tr>
		        <tr>
		        	<th class="txtl">위험관리</th>
		            <th class="txtc">4</th>
		            <td class="txtc">4</td>
		            <td class="txtc"></td>
		        </tr>
		        <tr>
		        	<th class="txtl">사후관리</th>
		            <th class="txtc">4</th>
		            <td class="txtc">3</td>
		            <td class="txtc"></td>
		        </tr>
		        <tr>
		        	<th class="txtl">모니터링 및 보고</th>
		            <th class="txtc">3</th>
		            <td class="txtc">3</td>
		            <td class="txtc"></td>
		        </tr>
		        <tr>
		        	<th class="txtl">제안요청내용 개선사항</th>
		            <th class="txtc">3</th>
		            <td class="txtc">2</td>
		            <td class="txtc"></td>
		        </tr>
		        <tr>
		        	<th class="txtl">추가 투입계획</th>
		            <th class="txtc">2</th>
		            <td class="txtc">2</td>
		            <td class="txtc"></td>
		        </tr>
		        <tr>
		        	<th class="txtl">테스트 점수</th>
		            <th class="txtc">10</th>
		            <td class="txtc">9</td>
		            <td class="txtc"></td>
		        </tr>
		        <tr>
		        	<th class="txtc">총점</th>
		            <th class="txtc">80</th>
		            <th class="txtc">68</th>
		            <td class="txtc"></td>
		        </tr>
			</table>
			
			<div class="contentTitle" style="">비상임위원</div>
			<table>
		    	<colgroup>
		        	<col width="20%" />
		            <col width="10%" />
		            <col width="10%" />
		            <col width="*" />
		        </colgroup>
		    	<tr>
		        	<th class="txtc">평가항목</th>
		            <th class="txtc">배점</th>
		            <th class="txtc">이상수</th>
		            <td class="txtc"></td>
		        </tr>
		        <tr>
		        	<th class="txtl">일반현황 및 연혁 등</th>
		            <th class="txtc">5</th>
		            <td class="txtc">5</td>
		            <td class="txtc"></td>
		        </tr>
		        <tr>
		        	<th class="txtl">사업이해도</th>
		            <th class="txtc">4</th>
		            <td class="txtc">3</td>
		            <td class="txtc"></td>
		        </tr>
		        <tr>
		        	<th class="txtl">추진전략</th>
		            <th class="txtc">4</th>
		            <td class="txtc">4</td>
		            <td class="txtc"></td>
		        </tr>
		        <tr>
		        	<th class="txtl">성과관리</th>
		            <th class="txtc">4</th>
		            <td class="txtc">3</td>
		            <td class="txtc"></td>
		        </tr>
		        <tr>
		        	<th class="txtl">컨설팅 방법론</th>
		            <th class="txtc">3</th>
		            <td class="txtc">2</td>
		            <td class="txtc"></td>
		        </tr>
		        <tr>
		        	<th class="txtl">컨설팅 요구사항</th>
		            <th class="txtc">10</th>
		            <td class="txtc">9</td>
		            <td class="txtc"></td>
		        </tr>
		        <tr>
		        	<th class="txtl">제약사항</th>
		            <th class="txtc">10</th>
		            <td class="txtc">9</td>
		            <td class="txtc"></td>
		        </tr>
		        <tr>
		        	<th class="txtl">관리방법론</th>
		            <th class="txtc">5</th>
		            <td class="txtc">5</td>
		            <td class="txtc"></td>
		        </tr>
		        <tr>
		        	<th class="txtl">개발장비</th>
		            <th class="txtc">5</th>
		            <td class="txtc">4</td>
		            <td class="txtc"></td>
		        </tr>
		        <tr>
		        	<th class="txtl">일정관리</th>
		            <th class="txtc">4</th>
		            <td class="txtc">3</td>
		            <td class="txtc"></td>
		        </tr>
		        <tr>
		        	<th class="txtl">위험관리</th>
		            <th class="txtc">4</th>
		            <td class="txtc">3</td>
		            <td class="txtc"></td>
		        </tr>
		        <tr>
		        	<th class="txtl">사후관리</th>
		            <th class="txtc">4</th>
		            <td class="txtc">3</td>
		            <td class="txtc"></td>
		        </tr>
		        <tr>
		        	<th class="txtl">모니터링 및 보고</th>
		            <th class="txtc">3</th>
		            <td class="txtc">2</td>
		            <td class="txtc"></td>
		        </tr>
		        <tr>
		        	<th class="txtl">제안요청내용 개선사항</th>
		            <th class="txtc">3</th>
		            <td class="txtc">3</td>
		            <td class="txtc"></td>
		        </tr>
		        <tr>
		        	<th class="txtl">추가 투입계획</th>
		            <th class="txtc">2</th>
		            <td class="txtc">1</td>
		            <td class="txtc"></td>
		        </tr>
		        <tr>
		        	<th class="txtl">테스트 점수</th>
		            <th class="txtc">10</th>
		            <td class="txtc">9</td>
		            <td class="txtc"></td>
		        </tr>
		        <tr>
		        	<th class="txtc">총점</th>
		            <th class="txtc">80</th>
		            <th class="txtc">68</th>
		            <td class="txtc"></td>
		        </tr>
			</table>
		</div>
	</div>
</div> <!--// content E-->
<form action="" id="listFrm" method="POST">
	<input type="hidden" name="resourceName" value="${ param.resourceName }" >
</form>
<form action="" id="popupFrm" method="POST">
	<input type="hidden" name="P_EVL_GUBN">
</form>
