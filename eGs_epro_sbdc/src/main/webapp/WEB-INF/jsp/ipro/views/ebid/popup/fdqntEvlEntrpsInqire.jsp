<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 입찰현황 > 입찰공고문 상세 팝업
 *
 * <pre>
 * ebid 
 *    |_ popup
               |_ bidWrtancDetail.jsp
 * 
 * </pre>
 * @date : 2017. 06. 19
 * @version : 1.0
 * @author : 은우소프트 전상훈
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %> 
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %> 

<link rel="stylesheet" type="text/css" href="${cssPath}/ipro/template/pagingPlace.css">
<link rel="stylesheet" type="text/css" href="${cssPath}/ipro/windowPop.css">

<script type="text/javascript" src="${jsPath}/ipro/views/ebid/popup/fdqntEvlEntrpsInqire.js"></script> 
 
<div id="windowPopup" class="w_980">
	<div class="tableLayer">
		<h3 class="windowTitle">계약실적 및 제재사항</h3>
		<table class="table">
	    	<colgroup>
	        	<col width="15%" />
	            <col width="85%" />
	        </colgroup>
	    	<tr>
	        	<th>업체명</th>
	            <td>주식회사 은우소프트</td>
	        </tr>
	    </table>
	</div>
	
	<div class="tableLayer">
		<div class="popSubTitle" style="">계약실적</div>
		<table class="table">
			<caption>계약실적</caption>
          		<colgroup>
               	<col width="5%"/>
               	<col width="10%"/>
               	<col width="*"/>
               	<col width="20%"/>
               	<col width="25%"/>
               	<col width="10%"/>
           	</colgroup>
	    	<tr>
	        	<th class="txtc">번호</th>
	        	<th class="txtc">계약번호</th>
	        	<th class="txtc">계약명</th>
	        	<th class="txtc">계약금액</th>
	        	<th class="txtc">계약기간</th>
	            <th class="last txtc">수급방식</th>
	        </tr>
	        <tbody>
		        <tr>
		        	<td class="txtc" style="border-left: 1px solid #d5ddfd">1</td>
		        	<td class="txtc">G2017-00050-1</td>
		        	<td >통합시스템 구축</td>
		        	<td class="txtr">500,000,000</td>
		        	<td class="txtc">2014-06-10 ~ 2016-03-01</td>
		            <td class="txtc">단독이행</td>
		        </tr>
	        </tbody>
	    </table>
	</div>
	
	<div class="tableLayer">
		<div class="popSubTitle" style="">제재내역</div>
		<table class="table">
			<caption>계약실적</caption>
          		<colgroup>
               	<col width="5%"/>
               	<col width="20%"/>
               	<col width="20%"/>
               	<col width="*"/>
           	</colgroup>
	    	<tr>
	        	<th class="txtc">번호</th>
	        	<th class="txtc">제재구분</th>
	        	<th class="txtc">제재기간</th>
	            <th class="last txtc">제재사유</th>
	        </tr>
	        <tbody>
		        <tr>
		        	<td class="txtc" colspan="4" style="border-left: 1px solid #d5ddfd">제재실적 데이터가 존재하지 않습니다.</td>
		        </tr>
	        </tbody>
	    </table>
	</div>
	
	<div class="tableLayer">
		<div class="popSubTitle" style="">지체상금부과내역</div>
		<table class="table">
			<caption>계약실적</caption>
          		<colgroup>
               	<col width="5%"/>
               	<col width="10%"/>
               	<col width="*"/>
               	<col width="20%"/>
               	<col width="10%"/>
           	</colgroup>
	    	<tr>
	        	<th class="txtc">번호</th>
	        	<th class="txtc">계약번호</th>
	        	<th class="txtc">계약명</th>
	        	<th class="txtc">지체상금대상금액</th>
	            <th class="last txtc">처리일시</th>
	        </tr>
	        <tbody>
		        <tr>
		        	<td class="txtc" colspan="5" style="border-left: 1px solid #d5ddfd">지체상금부과내역 데이터가 존재하지 않습니다.</td>
		        </tr>
	        </tbody>
	    </table>
	</div>

	<div class="T_btnLayer fr top10">
    	<button type="button" class="blueBtn L pointer" name="closeBtn" >닫기</button>
    </div>
</div> <!--// content E-->
