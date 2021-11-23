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

<script type="text/javascript" src="${jsPath}/ipro/views/ebid/qualEvlScoreRegistForm.js"></script>
 
<div class="content">
	<h3>정성평가 등록</h3>
	
	<div class="conts_wrap">
		<div class="btn_wrap view_btn fr">
			<button type="button" class="btn btn_02 btn_revise pointer" id="comptBtn" >정성평가완료</button>
			<button type="button" class="btn btn_02 btn_revise pointer" id="registBtn" >정성평가점수저장</button>
	    	<button type="button" class="btn btn_02 btn_sch pointer" id="listBtn" >취소</button>
	    </div>
		<div class="blueBox top20" style="border: 1px solid;">
			<p class="blueBoxTitle">※ 평가위원 안내사항</p>
			<br>
			<ul class="decimal">
				<li>1.하기 배점기준에 따라 평가하시되 배점한도 내에서 자유롭게 점수 부여(소수점 포함) 가능합니다.(예 : 9.5점)</li>
				<li>
					<br>2.항목별로 적격업체로 판단되는 경우에는 ‘우수’ 이상의 점수를 부여합니다.<br>
					<span class="colorRed"><font color="red">* 적격기준 : 총점 기준 68점(만점의 약 85%)</font></span>
				</li>
				<li>
					<br>3.특정업체에게 타당한 이유 없이 현저하게 과다/과소 점수 차이*를 부여하지 않도록 유의 바랍니다.<br>
					<span class="colorRed"><font color="red">* 총점(80점) 기준 30%(24점) 이상 차이</font></span>
				</li>
			</ul>
		</div>
	</div>
	
	<div class="view_area top20" style="width: 98%; padding-left: 10px;">
		<div class="btn_wrap view_btn fr">
			<button type="button" class="btn btn_02 btn_sch pointer" id="evlGradAlwncStdrInqireBtn" >평가등급 부여기준 보기</button>
		</div>
        <table style="width: 100%">
        	<tr>
				<td background='${imagePath}/ipro/tab/tab_bg.gif '>
					<table>
						<tr style="width: 100%;">
							<td style="width: 10px;"><img src="${imagePath}/ipro/tab/tab_on_1.gif " alt=""></td>
							<td style="width: 60px; text-align: center;" class="tab_on" id="1"><a href='javascript:tabEvent(1);' target='_self'>평가점수</a></td>
							<td style="width: 10px;"><img src="${imagePath}/ipro/tab/tab_on_2.gif " alt=""></td>
							<td style="width: 10px;"><img src="${imagePath}/ipro/tab/tab_off_1.gif " alt=""></td>
							<td style="width: 70px; text-align: center;" class="tab_off" id="2"><a href='javascript:tabEvent(2);' target='_self'>평가의견서</a></td>
							<td style="width: 10px;"><img src="${imagePath}/ipro/tab/tab_off_2.gif " alt=""></td>
						</tr>
					</table>
				</td>
        	</tr>
        </table>
  	</div>
	
<!-- 	<div style="width: 98%; padding:0 10px;" class="top20"> -->
<!-- 		<table style="width: 100%;"> -->
<!-- 			<tr> -->
<!-- 			    <td> -->
<!-- 			    	<div id='1' class="txtc tab_on"  style="width: 100px; float: left;"><a href='javascript:tabEvent(1);' target='_self'>평가점수</a></div> -->
<!-- 			       	<div id='2' class="txtc tab_off" style="width: 100px; float: left;"><a href='javascript:tabEvent(2);' target='_self'>평가의견서</a></div> -->
<!-- 			       	<button type="button" class="btn btn_02 btn_sch pointer float-right" id="evlGradAlwncStdrInqireBtn" >평가등급 부여기준 보기</button> -->
<!-- 			    </td> -->
<!-- 			</tr> -->
<!-- 		</table> -->
<!-- 	</div> -->
	
	<div id="block1" class="top20">
		<div class="conts_wrap">
			<table>
		    	<colgroup>
		        	<col width="15%" />
		            <col width="*" />
		            <col width="10%" />
		            <col width="15%" />
		            <col width="20%" />
		            <col width="20%" />
		        </colgroup>
		        <tr>
		        	<th class="txtc">평가구분</th>
		        	<th class="txtc">평가항목구분</th>
		        	<th class="txtc">배점</th>
		        	<th class="txtc">배점구분</th>
		        	<th class="txtc">주식회사 은우소프트</th>
		        	<th class="txtc">LIZ시스템</th>
				</tr>
				<tr>
					<td>제안사 소개</td>
					<td class="pointer" onclick="evlDtlsIemCnInqirePopup();">일반현황 및 연혁 등</td>
					<td class="txtc">5</td>
					<td>
						매우우수(5)<br>
						우수(4.5)<br>
						보통(4.0)<br> 
						미흡(3.0)<br>
						매우미흡(2.0)
					</td>
					<td class="txtc"><input type="text" style="width: 30%"></td>
					<td class="txtc"><input type="text" style="width: 30%"></td>
				</tr>
				<tr>
					<td rowspan="4">전략 및 방법론</td>
					<td class="pointer" onclick="evlDtlsIemCnInqirePopup();">사업이해도</td>
					<td class="txtc">4</td>
					<td>
						매우우수(4)<br>
						우수(3.6)<br>
						보통(3.2)<br> 
						미흡(2.4)<br>
						매우미흡(1.6)
					</td>
					<td class="txtc"><input type="text" style="width: 30%"></td>
					<td class="txtc"><input type="text" style="width: 30%"></td>
				</tr>
				<tr>
					<td class="pointer" onclick="evlDtlsIemCnInqirePopup();">추진전략</td>
					<td class="txtc">4</td>
					<td>
						매우우수(4)<br>
						우수(3.6)<br>
						보통(3.2)<br> 
						미흡(2.4)<br>
						매우미흡(1.6)
					</td>
					<td class="txtc"><input type="text" style="width: 30%"></td>
					<td class="txtc"><input type="text" style="width: 30%"></td>
				</tr>
				<tr>
					<td class="pointer" onclick="evlDtlsIemCnInqirePopup();">성과관리</td>
					<td class="txtc">4</td>
					<td>
						매우우수(4)<br>
						우수(3.6)<br>
						보통(3.2)<br> 
						미흡(2.4)<br>
						매우미흡(1.6)
					</td>
					<td class="txtc"><input type="text" style="width: 30%"></td>
					<td class="txtc"><input type="text" style="width: 30%"></td>
				</tr>
				<tr>
					<td class="pointer" onclick="evlDtlsIemCnInqirePopup();">컨설팅 방법론</td>
					<td class="txtc">3</td>
					<td>
						매우우수(3)<br>
						우수(2.7)<br>
						보통(2.4)<br> 
						미흡(1.8)<br>
						매우미흡(1.2)
					</td>
					<td class="txtc"><input type="text" style="width: 30%"></td>
					<td class="txtc"><input type="text" style="width: 30%"></td>
				</tr>
				<tr>
					<td rowspan="2">기술 및 기능</td>
					<td class="pointer" onclick="evlDtlsIemCnInqirePopup();">컨설팅 요구사항</td>
					<td class="txtc">10</td>
					<td>
						매우우수(10)<br>
						우수(9.0)<br>
						보통(8.0)<br> 
						미흡(6.0)<br>
						매우미흡(4.0)
					</td>
					<td class="txtc"><input type="text" style="width: 30%"></td>
					<td class="txtc"><input type="text" style="width: 30%"></td>
				</tr>
				<tr>
					<td class="pointer" onclick="evlDtlsIemCnInqirePopup();">제약사항</td>
					<td class="txtc">10</td>
					<td>
						매우우수(10)<br>
						우수(9.0)<br>
						보통(8.0)<br> 
						미흡(6.0)<br>
						매우미흡(4.0)
					</td>
					<td class="txtc"><input type="text" style="width: 30%"></td>
					<td class="txtc"><input type="text" style="width: 30%"></td>
				</tr>
				<tr>
					<td rowspan="6">프로젝트 관리</td>
					<td class="pointer" onclick="evlDtlsIemCnInqirePopup();">관리방법론</td>
					<td class="txtc">5</td>
					<td>
						매우우수(5)<br>
						우수(4.5)<br>
						보통(4.0)<br> 
						미흡(3.0)<br>
						매우미흡(2.0)
					</td>
					<td class="txtc"><input type="text" style="width: 30%"></td>
					<td class="txtc"><input type="text" style="width: 30%"></td>
				</tr>
				<tr>
					<td class="pointer" onclick="evlDtlsIemCnInqirePopup();">개발장비</td>
					<td class="txtc">5</td>
					<td>
						매우우수(5)<br>
						우수(4.5)<br>
						보통(4.0)<br> 
						미흡(3.0)<br>
						매우미흡(2.0)
					</td>
					<td class="txtc"><input type="text" style="width: 30%"></td>
					<td class="txtc"><input type="text" style="width: 30%"></td>
				</tr>
				<tr>
					<td class="pointer" onclick="evlDtlsIemCnInqirePopup();">일정관리</td>
					<td class="txtc">4</td>
					<td>
						매우우수(4)<br>
						우수(3.6)<br>
						보통(3.2)<br> 
						미흡(2.4)<br>
						매우미흡(1.6)
					</td>
					<td class="txtc"><input type="text" style="width: 30%"></td>
					<td class="txtc"><input type="text" style="width: 30%"></td>
				</tr>
				<tr>
					<td class="pointer" onclick="evlDtlsIemCnInqirePopup();">위험관리</td>
					<td class="txtc">4</td>
					<td>
						매우우수(4)<br>
						우수(3.6)<br>
						보통(3.2)<br> 
						미흡(2.4)<br>
						매우미흡(1.6)
					</td>
					<td class="txtc"><input type="text" style="width: 30%"></td>
					<td class="txtc"><input type="text" style="width: 30%"></td>
				</tr>
				<tr>
					<td class="pointer" onclick="evlDtlsIemCnInqirePopup();">사후관리</td>
					<td class="txtc">4</td>
					<td>
						매우우수(4)<br>
						우수(3.6)<br>
						보통(3.2)<br> 
						미흡(2.4)<br>
						매우미흡(1.6)
					</td>
					<td class="txtc"><input type="text" style="width: 30%"></td>
					<td class="txtc"><input type="text" style="width: 30%"></td>
				</tr>
				<tr>
					<td class="pointer" onclick="evlDtlsIemCnInqirePopup();">모니터링 및 보고</td>
					<td class="txtc">3</td>
					<td>
						매우우수(3)<br>
						우수(2.7)<br>
						보통(2.4)<br> 
						미흡(1.8)<br>
						매우미흡(1.2)
					</td>
					<td class="txtc"><input type="text" style="width: 30%"></td>
					<td class="txtc"><input type="text" style="width: 30%"></td>
				</tr>
				<tr>
					<td rowspan="2">특수제안</td>
					<td class="pointer" onclick="evlDtlsIemCnInqirePopup();">제안요청내용 개선사항</td>
					<td class="txtc">3</td>
					<td>
						매우우수(3)<br>
						우수(2.7)<br>
						보통(2.4)<br> 
						미흡(1.8)<br>
						매우미흡(1.2)
					</td>
					<td class="txtc"><input type="text" style="width: 30%"></td>
					<td class="txtc"><input type="text" style="width: 30%"></td>
				</tr>
				<tr>
					<td class="pointer" onclick="evlDtlsIemCnInqirePopup();">추가 투입계획</td>
					<td class="txtc">2</td>
					<td>
						매우우수(2)<br>
						우수(1.8)<br>
						보통(1.6)<br> 
						미흡(1.2)<br>
						매우미흡(0.8)
					</td>
					<td class="txtc"><input type="text" style="width: 30%"></td>
					<td class="txtc"><input type="text" style="width: 30%"></td>
				</tr>
				<tr>
					<td>테스트점수</td>
					<td class="pointer" onclick="evlDtlsIemCnInqirePopup();">테스트점수</td>
					<td class="txtc">10</td>
					<td>
						매우우수(10)<br>
						우수(9.0)<br>
						보통(8.0)<br> 
						미흡(6.0)<br>
						매우미흡(4.0)
					</td>
					<td class="txtc"><input type="text" style="width: 30%"></td>
					<td class="txtc"><input type="text" style="width: 30%"></td>
				</tr>
				<tr>
					<th colspan="2" class="txtc">총점</th>
					<th colspan="2" class="txtc">80</th>
					<td class="txtc">0</td>
					<td class="txtc">0</td>
				</tr>
			</table>
		</div>
	</div>
	
	<div id="block2" style="display: none;" class="top20">
		<div class="conts_wrap">
			<table>
		    	<colgroup>
		    		<col width="15%" />
		            <col width="20%" />
		            <col width="*" />
		        </colgroup>
		        <tr>
		        	<th class="txtc">평가항목</th>
		        	<th class="txtc">업체명</th>
		        	<th class="txtc">평가의견</th>
				</tr>
				<tr>
		        	<td rowspan="2">제안사 소개</td>
		        	<td>주식회사 은우소프트</td>
		        	<td class="txtc" ><textarea rows="4" cols="" style="width: 95%"></textarea> </td>
				</tr>
				<tr>
		        	<td>LIZ시스템</td>
		        	<td class="txtc" ><textarea rows="4" cols="" style="width: 95%"></textarea> </td>
				</tr>
				<tr>
		        	<td rowspan="2">전략 및 방법론</td>
		        	<td>주식회사 은우소프트</td>
		        	<td class="txtc" ><textarea rows="4" cols="" style="width: 95%"></textarea> </td>
				</tr>
				<tr>
		        	<td>LIZ시스템</td>
		        	<td class="txtc" ><textarea rows="4" cols="" style="width: 95%"></textarea> </td>
				</tr>
				<tr>
		        	<td rowspan="2">기술 및 기능</td>
		        	<td>주식회사 은우소프트</td>
		        	<td class="txtc" ><textarea rows="4" cols="" style="width: 95%"></textarea> </td>
				</tr>
				<tr>
		        	<td>LIZ시스템</td>
		        	<td class="txtc" ><textarea rows="4" cols="" style="width: 95%"></textarea> </td>
				</tr>
				<tr>
		        	<td rowspan="2">프로젝트 관리</td>
		        	<td>주식회사 은우소프트</td>
		        	<td class="txtc" ><textarea rows="4" cols="" style="width: 95%"></textarea> </td>
				</tr>
				<tr>
		        	<td>LIZ시스템</td>
		        	<td class="txtc" ><textarea rows="4" cols="" style="width: 95%"></textarea> </td>
				</tr>
				<tr>
		        	<td rowspan="2">특수제안</td>
		        	<td>주식회사 은우소프트</td>
		        	<td class="txtc" ><textarea rows="4" cols="" style="width: 95%"></textarea> </td>
				</tr>
				<tr>
		        	<td>LIZ시스템</td>
		        	<td class="txtc" ><textarea rows="4" cols="" style="width: 95%"></textarea> </td>
				</tr>
				<tr>
		        	<td rowspan="2">테스트점수</td>
		        	<td>주식회사 은우소프트</td>
		        	<td class="txtc" ><textarea rows="4" cols="" style="width: 95%"></textarea> </td>
				</tr>
				<tr>
		        	<td>LIZ시스템</td>
		        	<td class="txtc" ><textarea rows="4" cols="" style="width: 95%"></textarea> </td>
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
