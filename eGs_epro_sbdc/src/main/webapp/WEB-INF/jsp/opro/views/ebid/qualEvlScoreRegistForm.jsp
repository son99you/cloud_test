<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 기술평가 > 정성평가점수 등록 폼
 *
 * <pre>
 * ebid 
 *    |_ qualEvlScoreRegistForm.jsp
 * 
 * </pre>
 * @date : 2015. 09. 15. 오후 9:14:08
 * @version : 1.0
 * @author : 은우소프트 김봉수
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %>

<script type="text/javascript" src="${jsPath}/opro/views/ebid/qualEvlScoreRegistForm.js"></script>  

            <div id="panelSubContent">

                <h3 class="subTitle">정성평가 등록</h3>
                
				<div class="blueBox">
                    <p class="blueBoxTitle">※ 평가위원 안내사항</p>
                    <!--
                        ul list style 두가지입니다.
                        class name : decimal
                        class name : disc
                    -->
                    <ul class="decimal">
                        <li>하기 배점기준에 따라 평가하시되 배점한도 내에서 자유롭게 점수 부여(소수점 포함) 가능합니다.(예 : 9.5점)</li>
                        <li>
                            항목별로 적격업체로 판단되는 경우에는 ‘우수’ 이상의 점수를 부여합니다.<br>
                            <span class="colorRed">* 적격기준 : 총점 기준 68점(만점의 약 85%)</span>
                        </li>
                        <li>
                            특정업체에게 타당한 이유 없이 현저하게 과다/과소 점수 차이*를 부여하지 않도록 유의 바랍니다.<br>
                            <span class="colorRed">* 총점(80점) 기준 30%(24점) 이상 차이</span>
                        </li>
                    </ul>
                </div>
                
                <div class="sectionTapArea">
                    <div class="sectionTapBtns w_980 perBtn mt_20">
                        <button type="button" class="tapBtn on">평가점수</button>
                        <button type="button" class="tapBtn" id="qualEvlWrtopnTabBtn">평가의견서</button>
                        <div class="T_btnLayer fr cn ">
                            <button type="button" class="whiteGray S" id="evlGradAlwncStdrInqireBtn">평가등급 부여기준 보기</button>
                        </div>
                    </div>

                </div>

                <div class="tapLayers">
                    <!--텝 하나-->
                    <div class="tapLayer">
                        <div class="tableLayer">
                            <table class="table no_bul">
                                <caption>정성평가</caption>
                                <colgroup>
                                	<col width="120px">
                                    <col width="300px">
                                    <col width="50px">
                                    <col width="150px">
                                    <col width="150px">
                                    <col width="150px">
                                </colgroup>
                                <thead>
                                    <tr class="line">
                                        <th>평가구분</th>
                                        <th>평가항목구분</th>
                                        <th>배점</th>
                                        <th>배점구분</th>
                                        <th class="">주식회사 은우소프트</th>
		        						<th class="">LIZ시스템</th>
                                    </tr>
                                </thead>
                                <tbody>
                                	<tr>
										<td>제안사 소개</td>
										<td class="left_T" onclick="evlDtlsIemCnInqirePopup();" style="cursor: pointer;">일반현황 및 연혁 등</td>
										<td class="">5</td>
										<td class="left_T">
											매우우수(5)<br>
											우수(4.5)<br>
											보통(4.0)<br> 
											미흡(3.0)<br>
											매우미흡(2.0)
										</td>
										<td class=""><input type="text" class="tr" numeric2 style="width: 35px;"/></td>
										<td class=""><input type="text" class="tr" numeric2 style="width: 35px;"/></td>
									</tr>
									<tr>
										<td rowspan="4">전략 및 방법론</td>
										<td class="left_T" style="cursor: pointer;" onclick="evlDtlsIemCnInqirePopup();">사업이해도</td>
										<td class="">4</td>
										<td class="left_T">
											매우우수(4)<br>
											우수(3.6)<br>
											보통(3.2)<br> 
											미흡(2.4)<br>
											매우미흡(1.6)
										</td>
										<td class=""><input type="text" class="tr" numeric2 style="width: 35px;"/></td>
										<td class=""><input type="text" class="tr" numeric2 style="width: 35px;"/></td>
									</tr>
									<tr>
										<td class="left_T" style="cursor: pointer;" onclick="evlDtlsIemCnInqirePopup();">추진전략</td>
										<td class="">4</td>
										<td class="left_T">
											매우우수(4)<br>
											우수(3.6)<br>
											보통(3.2)<br> 
											미흡(2.4)<br>
											매우미흡(1.6)
										</td>
										<td class=""><input type="text" class="tr" numeric2 style="width: 35px;"/></td>
										<td class=""><input type="text" class="tr" numeric2 style="width: 35px;"/></td>
									</tr>
									<tr>
										<td class="left_T" style="cursor: pointer;" onclick="evlDtlsIemCnInqirePopup();">성과관리</td>
										<td class="">4</td>
										<td class="left_T">
											매우우수(4)<br>
											우수(3.6)<br>
											보통(3.2)<br> 
											미흡(2.4)<br>
											매우미흡(1.6)
										</td>
										<td class=""><input type="text" class="tr" numeric2 style="width: 35px;"/></td>
										<td class=""><input type="text" class="tr" numeric2 style="width: 35px;"/></td>
									</tr>
									<tr>
										<td class="left_T" style="cursor: pointer;" onclick="evlDtlsIemCnInqirePopup();">컨설팅 방법론</td>
										<td class="">3</td>
										<td class="left_T">
											매우우수(3)<br>
											우수(2.7)<br>
											보통(2.4)<br> 
											미흡(1.8)<br>
											매우미흡(1.2)
										</td>
										<td class=""><input type="text" class="tr" numeric2 style="width: 35px;"/></td>
										<td class=""><input type="text" class="tr" numeric2 style="width: 35px;"/></td>
									</tr>
									<tr>
										<td rowspan="2">기술 및 기능</td>
										<td class="left_T" style="cursor: pointer;" onclick="evlDtlsIemCnInqirePopup();">컨설팅 요구사항</td>
										<td class="">10</td>
										<td class="left_T">
											매우우수(10)<br>
											우수(9.0)<br>
											보통(8.0)<br> 
											미흡(6.0)<br>
											매우미흡(4.0)
										</td>
										<td class=""><input type="text" class="tr" numeric2 style="width: 35px;"/></td>
										<td class=""><input type="text" class="tr" numeric2 style="width: 35px;"/></td>
									</tr>
									<tr>
										<td class="left_T" style="cursor: pointer;" onclick="evlDtlsIemCnInqirePopup();">제약사항</td>
										<td class="">10</td>
										<td class="left_T">
											매우우수(10)<br>
											우수(9.0)<br>
											보통(8.0)<br> 
											미흡(6.0)<br>
											매우미흡(4.0)
										</td>
										<td class=""><input type="text" class="tr" numeric2 style="width: 35px;"/></td>
										<td class=""><input type="text" class="tr" numeric2 style="width: 35px;"/></td>
									</tr>
									<tr>
										<td rowspan="6">프로젝트 관리</td>
										<td class="left_T" style="cursor: pointer;" onclick="evlDtlsIemCnInqirePopup();">관리방법론</td>
										<td class="">5</td>
										<td class="left_T">
											매우우수(5)<br>
											우수(4.5)<br>
											보통(4.0)<br> 
											미흡(3.0)<br>
											매우미흡(2.0)
										</td>
										<td class=""><input type="text" class="tr" numeric2 style="width: 35px;"/></td>
										<td class=""><input type="text" class="tr" numeric2 style="width: 35px;"/></td>
									</tr>
									<tr>
										<td class="left_T" style="cursor: pointer;" onclick="evlDtlsIemCnInqirePopup();">개발장비</td>
										<td class="">5</td>
										<td class="left_T">
											매우우수(5)<br>
											우수(4.5)<br>
											보통(4.0)<br> 
											미흡(3.0)<br>
											매우미흡(2.0)
										</td>
										<td class=""><input type="text" class="tr" numeric2 style="width: 35px;"/></td>
										<td class=""><input type="text" class="tr" numeric2 style="width: 35px;"/></td>
									</tr>
									<tr>
										<td class="left_T" style="cursor: pointer;" onclick="evlDtlsIemCnInqirePopup();">일정관리</td>
										<td class="">4</td>
										<td class="left_T">
											매우우수(4)<br>
											우수(3.6)<br>
											보통(3.2)<br> 
											미흡(2.4)<br>
											매우미흡(1.6)
										</td>
										<td class=""><input type="text" class="tr" numeric2 style="width: 35px;"/></td>
										<td class=""><input type="text" class="tr" numeric2 style="width: 35px;"/></td>
									</tr>
									<tr>
										<td class="left_T" style="cursor: pointer;" onclick="evlDtlsIemCnInqirePopup();">위험관리</td>
										<td class="">4</td>
										<td class="left_T">
											매우우수(4)<br>
											우수(3.6)<br>
											보통(3.2)<br> 
											미흡(2.4)<br>
											매우미흡(1.6)
										</td>
										<td class=""><input type="text" class="tr" numeric2 style="width: 35px;"/></td>
										<td class=""><input type="text" class="tr" numeric2 style="width: 35px;"/></td>
									</tr>
									<tr>
										<td class="left_T" style="cursor: pointer;" onclick="evlDtlsIemCnInqirePopup();">사후관리</td>
										<td class="">4</td>
										<td class="left_T">
											매우우수(4)<br>
											우수(3.6)<br>
											보통(3.2)<br> 
											미흡(2.4)<br>
											매우미흡(1.6)
										</td>
										<td class=""><input type="text" class="tr" numeric2 style="width: 35px;"/></td>
										<td class=""><input type="text" class="tr" numeric2 style="width: 35px;"/></td>
									</tr>
									<tr>
										<td class="left_T" style="cursor: pointer;" onclick="evlDtlsIemCnInqirePopup();">모니터링 및 보고</td>
										<td class="">3</td>
										<td class="left_T">
											매우우수(3)<br>
											우수(2.7)<br>
											보통(2.4)<br> 
											미흡(1.8)<br>
											매우미흡(1.2)
										</td>
										<td class=""><input type="text" class="tr" numeric2 style="width: 35px;"/></td>
										<td class=""><input type="text" class="tr" numeric2 style="width: 35px;"/></td>
									</tr>
									<tr>
										<td rowspan="2">특수제안</td>
										<td class="left_T" style="cursor: pointer;" onclick="evlDtlsIemCnInqirePopup();">제안요청내용 개선사항</td>
										<td class="">3</td>
										<td class="left_T">
											매우우수(3)<br>
											우수(2.7)<br>
											보통(2.4)<br> 
											미흡(1.8)<br>
											매우미흡(1.2)
										</td>
										<td class=""><input type="text" class="tr" numeric2 style="width: 35px;"/></td>
										<td class=""><input type="text" class="tr" numeric2 style="width: 35px;"/></td>
									</tr>
									<tr>
										<td class="left_T" style="cursor: pointer;" onclick="evlDtlsIemCnInqirePopup();">추가 투입계획</td>
										<td class="">2</td>
										<td class="left_T">
											매우우수(2)<br>
											우수(1.8)<br>
											보통(1.6)<br> 
											미흡(1.2)<br>
											매우미흡(0.8)
										</td>
										<td class=""><input type="text" class="tr" numeric2 style="width: 35px;"/></td>
										<td class=""><input type="text" class="tr" numeric2 style="width: 35px;"/></td>
									</tr>
									<tr>
										<td>테스트점수</td>
										<td class="left_T" style="cursor: pointer;" onclick="evlDtlsIemCnInqirePopup();">테스트점수</td>
										<td class="">10</td>
										<td class="left_T">
											매우우수(10)<br>
											우수(9.0)<br>
											보통(8.0)<br> 
											미흡(6.0)<br>
											매우미흡(4.0)
										</td>
										<td class=""><input type="text" class="tr" numeric2 style="width: 35px;"/></td>
										<td class=""><input type="text" class="tr" numeric2 style="width: 35px;"/></td>
									</tr>
									<%--총점영역 --%>
	                                <tr>
	                                    <th colspan="2">총점</th>
	                                    <th colspan="2">80</th>
                                   		<td id="totScore_${status.count }">0.00</td>
                                   		<td id="totScore_${status.count }">0.00</td>
	                                </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>

                    <div class="T_btnLayer fr">
                        <button type="button" class="blueBtn L" id="qualEvlComptBtn">정성평가완료</button>
                    	<button type="button" class="blueBtn L" id="qualEvlScoreRegistBtn">정성평가점수저장</button>
                        <button type="button" class="blueBtn L" id="listBtn">취소</button>
                    </div>
                </div>


            </div>

	<%-- LIST FORM --%>
	<form id="listFrm" class="search_form" method="POST">
		<input type="hidden" name="resourceName" value="${ param.resourceName }" >
	</form>

	<%-- POPUP FORM --%>
	<form id="popupFrm" method="POST">
	</form>
	
