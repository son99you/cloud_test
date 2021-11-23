<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 기술평가 > 정성평가의견서 상세
 *
 * <pre>
 * ebid 
 *    |_ qualEvlWrtopnDetail.jsp
 * 
 * </pre>
 * @date : 2015. 02. 25. 오전 11:27:08
 * @version : 1.0
 * @author : 은우소프트 김봉수
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %>

<script type="text/javascript" src="${jsPath}/opro/views/ebid/qualEvlWrtopnDetail.js"></script>  

            <div id="panelSubContent">

                <h3 class="subTitle">정성평가 상세</h3>
                
                <div class="sectionTapArea">
                    <div class="sectionTapBtns w_980 perBtn mt_20">
                        <button type="button" class="tapBtn" id="qualEvlScoreTabBtn">평가점수</button>
                        <button type="button" class="tapBtn on">평가의견서</button>
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
                                    <col width="150px">
                                    <col width="">
                                </colgroup>
                                <thead>
                                    <tr class="line">
                                        <th>평가항목</th>
                                        <th>업체명</th>
                                        <th>평가의견</th>
                                    </tr>
                                </thead>
                                <tbody>
									<tr>
							        	<td class="" rowspan="2">제안사 소개</td>
							        	<td class="left_T" >주식회사 은우소프트</td>
							        	<td class="txtc" ><textarea rows="4" cols="" style="width: 95%" disabled="disabled">소개자료와 발표가 깔끔함</textarea> </td>
									</tr>
									<tr>
							        	<td class="left_T" >LIZ시스템</td>
							        	<td class="txtc" ><textarea rows="4" cols="" style="width: 95%" disabled="disabled">자료가 부실하고 설명이 제대로 되지 않음</textarea> </td>
									</tr>
									<tr>
							        	<td class="" rowspan="2">전략 및 방법론</td>
							        	<td class="left_T" >주식회사 은우소프트</td>
							        	<td class="txtc" ><textarea rows="4" cols="" style="width: 95%" disabled="disabled">전략과 방법이 뛰어남</textarea> </td>
									</tr>
									<tr>
							        	<td class="left_T" >LIZ시스템</td>
							        	<td class="txtc" ><textarea rows="4" cols="" style="width: 95%" disabled="disabled">전략과 방법이 평범함</textarea> </td>
									</tr>
									<tr>
							        	<td class="" rowspan="2">기술 및 기능</td>
							        	<td class="left_T" >주식회사 은우소프트</td>
							        	<td class="txtc" ><textarea rows="4" cols="" style="width: 95%" disabled="disabled">최신 기술을 이용한 기능구현</textarea> </td>
									</tr>
									<tr>
							        	<td class="left_T" >LIZ시스템</td>
							        	<td class="txtc" ><textarea rows="4" cols="" style="width: 95%" disabled="disabled">기능구현</textarea> </td>
									</tr>
									<tr>
							        	<td class="" rowspan="2">프로젝트 관리</td>
							        	<td class="left_T" >주식회사 은우소프트</td>
							        	<td class="txtc" ><textarea rows="4" cols="" style="width: 95%" disabled="disabled">철저한 프로젝트 관리</textarea> </td>
									</tr>
									<tr>
							        	<td class="left_T" >LIZ시스템</td>
							        	<td class="txtc" ><textarea rows="4" cols="" style="width: 95%" disabled="disabled">프리한 프로젝트 관리</textarea> </td>
									</tr>
									<tr>
							        	<td class="" rowspan="2">특수제안</td>
							        	<td class="left_T" >주식회사 은우소프트</td>
							        	<td class="txtc" ><textarea rows="4" cols="" style="width: 95%" disabled="disabled">제안이 흥미로움</textarea> </td>
									</tr>
									<tr>
							        	<td class="left_T" >LIZ시스템</td>
							        	<td class="txtc" ><textarea rows="4" cols="" style="width: 95%" disabled="disabled">제안 없음</textarea> </td>
									</tr>
									<tr>
							        	<td class="" rowspan="2">테스트점수</td>
							        	<td class="left_T" >주식회사 은우소프트</td>
							        	<td class="txtc" ><textarea rows="4" cols="" style="width: 95%" disabled="disabled">테스트 훌륭함</textarea> </td>
									</tr>
									<tr>
							        	<td class="left_T" >LIZ시스템</td>
							        	<td class="txtc" ><textarea rows="4" cols="" style="width: 95%" disabled="disabled">테스트</textarea> </td>
									</tr>
                                </tbody>
                            </table>
                        </div>
                    </div>

                    <div class="T_btnLayer fr">
                        <button type="button" class="blueBtn L" id="listBtn">목록</button>
                    </div>
                </div>


            </div>

	<%-- LIST FORM --%>
	<form id="listFrm" class="search_form" method="POST">
		<input type="hidden" name="resourceName" value="${ param.resourceName }" >
	</form>

