<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 기술평가 > 정성평가의견서 등록 폼
 *
 * <pre>
 * ebid 
 *    |_ qualEvlWrtopnRegistForm.jsp
 * 
 * </pre>
 * @date : 2015. 09. 15. 오후 3:29:08
 * @version : 1.0
 * @author : 은우소프트 김봉수
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %>

<script type="text/javascript" src="${jsPath}/opro/views/ebid/qualEvlWrtopnRegistForm.js"></script>  

<div id="panelSubContent">

	<h3 class="subTitle">정성평가 등록</h3>
                
	<div class="sectionTapArea">
		<div class="sectionTapBtns w_980 perBtn mt_20">
			<button type="button" class="tapBtn" id="qualEvlScoreTabBtn">평가점수</button>
			<button type="button" class="tapBtn on">평가의견서</button>
		</div>
	</div>

	<div class="tapLayers">
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
				        	<td class="txtc" ><textarea rows="4" cols="" style="width: 95%"></textarea> </td>
						</tr>
						<tr>
				        	<td class="left_T" >LIZ시스템</td>
				        	<td class="txtc" ><textarea rows="4" cols="" style="width: 95%"></textarea> </td>
						</tr>
						<tr>
				        	<td class="" rowspan="2">전략 및 방법론</td>
				        	<td class="left_T" >주식회사 은우소프트</td>
				        	<td class="txtc" ><textarea rows="4" cols="" style="width: 95%"></textarea> </td>
						</tr>
						<tr>
				        	<td class="left_T" >LIZ시스템</td>
				        	<td class="txtc" ><textarea rows="4" cols="" style="width: 95%"></textarea> </td>
						</tr>
						<tr>
				        	<td class="" rowspan="2">기술 및 기능</td>
				        	<td class="left_T" >주식회사 은우소프트</td>
				        	<td class="txtc" ><textarea rows="4" cols="" style="width: 95%"></textarea> </td>
						</tr>
						<tr>
				        	<td class="left_T" >LIZ시스템</td>
				        	<td class="txtc" ><textarea rows="4" cols="" style="width: 95%"></textarea> </td>
						</tr>
						<tr>
				        	<td class="" rowspan="2">프로젝트 관리</td>
				        	<td class="left_T" >주식회사 은우소프트</td>
				        	<td class="txtc" ><textarea rows="4" cols="" style="width: 95%"></textarea> </td>
						</tr>
						<tr>
				        	<td class="left_T" >LIZ시스템</td>
				        	<td class="txtc" ><textarea rows="4" cols="" style="width: 95%"></textarea> </td>
						</tr>
						<tr>
				        	<td class="" rowspan="2">특수제안</td>
				        	<td class="left_T" >주식회사 은우소프트</td>
				        	<td class="txtc" ><textarea rows="4" cols="" style="width: 95%"></textarea> </td>
						</tr>
						<tr>
				        	<td class="left_T" >LIZ시스템</td>
				        	<td class="txtc" ><textarea rows="4" cols="" style="width: 95%"></textarea> </td>
						</tr>
						<tr>
				        	<td class="" rowspan="2">테스트점수</td>
				        	<td class="left_T" >주식회사 은우소프트</td>
				        	<td class="txtc" ><textarea rows="4" cols="" style="width: 95%"></textarea> </td>
						</tr>
						<tr>
				        	<td class="left_T" >LIZ시스템</td>
				        	<td class="txtc" ><textarea rows="4" cols="" style="width: 95%"></textarea> </td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>

        <div class="T_btnLayer fr">
        	<button type="button" class="blueBtn L" id="qualEvlWrtopnRegistBtn">정성평가의견서저장</button>
            <button type="button" class="blueBtn L" id="listBtn">취소</button>
        </div>
    </div>
</div>

	<%-- LIST FORM --%>
	<form id="listFrm" class="search_form" method="POST">
		<input type="hidden" name="resourceName" value="${ param.resourceName }" >
	</form>

