<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 입찰현황 > 관심입찰업체목록
 *
 * <pre>
 * ebid 
 *    |_ popup
              |_ intrstBidEntrpsList.jsp
 * 
 * </pre>
 * @date : 2017. 06. 13
 * @version : 1.0
 * @author : 은우소프트 전상훈
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %> 
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %> 

<link rel="stylesheet" type="text/css" href="${cssPath}/ipro/template/pagingPlace.css">
<link rel="stylesheet" type="text/css" href="${cssPath}/ipro/windowPop.css">

<script type="text/javascript" src="${jsPath}/ipro/views/ebid/popup/tchqvlnResultTotEvlScoreInqire.js"></script> 
 
<div id="windowPopup">
	<div class="tableLayer">
		<h3 class="windowTitle" style="">총 평가점수 조회</h3>
		<div class="popSubTitle" style="">평가표 기본정보</div>
		<table class="table">
	    	<colgroup>
				<col width="15%" />
				<col width="35%" />
				<col width="15%" />
				<col width="35%" />
	        </colgroup>
	    	<tr>
	        	<th>업체명</th>
	            <td>주식회사 은우소프트</td>
	            <th>총점</th>
	            <td class="txtr">72</td>
	        </tr>
		</table>
	</div>
	<br>	
	<div style="width: 100%;">
        <table style="width: 100%">
        	<tr>
				<td background='${imagePath}/ipro/tab/tab_bg.gif '>
					<table>
						<tr style="width: 100%;">
							<td style="width: 10px;"><img src="${imagePath}/ipro/tab/tab_on_1.gif " alt=""></td>
							<td style="width: 60px; text-align: center; background-repeat: repeat-x;" class="tab_on" id="1"><a href='javascript:tabEvent(1);' target='_self'>상임위원</a></td>
							<td style="width: 10px;"><img src="${imagePath}/ipro/tab/tab_on_2.gif " alt=""></td>
							<td style="width: 10px;"><img src="${imagePath}/ipro/tab/tab_off_1.gif " alt=""></td>
							<td style="width: 70px; text-align: center; background-repeat: repeat-x;" class="tab_off" id="2"><a href='javascript:tabEvent(2);' target='_self'>비상임위원</a></td>
							<td style="width: 10px;"><img src="${imagePath}/ipro/tab/tab_off_2.gif " alt=""></td>
						</tr>
					</table>
				</td>
        	</tr>
        </table>
	</div>
	<br>
	<div class="tableLayer ">
		<div class="block1 tabBlock ">
			<div class="subscrip_info">
				<table class="table">
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
			</div>
		</div>
			
		<div class="block2 tabBlock " style="display: none;">
			<div class="subscrip_info">
				<table class="table">
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
    </div>
	<div class="T_btnLayer fr top10">
    	<button type="button" class="blueBtn L pointer" id="closeBtn" >닫기</button>
    </div>
</div> <!--// content E-->
