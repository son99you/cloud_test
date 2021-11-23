<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 기술평가 > 기술평가대상 목록
 *
 * <pre>
 * ebid 
 *    |_ tchqvlnTrgetList.jsp
 * 
 * </pre>
 * @date : 2015. 08. 12. 오전 11:20:08
 * @version : 1.0
 * @author : 은우소프트 김봉수
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %>

<script type="text/javascript" src="${jsPath}/opro/views/ebid/tchqvlnTrgetList.js"></script>  

<div id="panelSubContent">
	<h3 class="subTitle">정성평가 목록</h3>
	<div class="formLayer">
		<form id="searchFrm" class="search_form" method="POST" action="${contextPath}/ebid/tchqvlnTrgetList.do">
			<input type="hidden" id="P_PAGE_NO" name="P_PAGE_NO" value="${comFn:nvl(param.P_PAGE_NO, 1)}">
			<input type="hidden" id="P_PAGE_SIZE" name="P_PAGE_SIZE" value="${comFn:nvl(param.P_PAGE_SIZE, 10)}">
			<input type="hidden" name="P_USER_ID" value="${loginResult.USER_ID }" />
			<input type="hidden" name="P_MFCMM_ID" value="${loginResult.USER_ID }" />

            <fieldset>
                <legend>기술평가대상목록 검색</legend>
            </fieldset>		

            <%-- Search Line 1 --%>
			<div class="searchFormLayerLine">
                <span class="search_bullet" style="width: 70px;">입찰명</span>
                <span class="contents_search_bar"></span>
                <label for="P_BID_NM" class="blind">입찰명</label>
                <input type="text" class="lineTxt" id="P_BID_NM" name="P_BID_NM" style="width: 85%;" value="${param.P_BID_NM}">
            </div>
            		
            <%-- Search Line 3 --%>
            <div class="searchFormLayerLine">
                <span class="search_bullet" style="width: 70px;">기술평가일자</span>
                <span class="contents_search_bar"></span>
                
                <div class="calendar_wrap">
                    <label for="P_TCHQVLN_DE_STT" class="blind">기술평가일자 시작일</label>
                    <input type="text" id="P_TCHQVLN_DE_STT" name="P_TCHQVLN_DE_STT" style="width: 110px;" value="${param.P_TCHQVLN_DE_STT}"  date maxlength="10">
                </div>
                <span class="wave"> ~ </span>
                <div class="calendar_wrap">
                    <label for="P_TCHQVLN_DE_END" class="blind">기술평가일자 종료일</label>
                    <input type="text" id="P_TCHQVLN_DE_END" name="P_TCHQVLN_DE_END" style="width: 110px;" value="${param.P_TCHQVLN_DE_END}"  date maxlength="10">
                </div>
                <div class="T_btnLayer fr cn">
	                <button id="searchBtn" type="button" class="grayBtn ico">
	                    <img src="${imagePath}/opro/icon/ico_search.png" alt="조회 버튼"> 조회
	                </button>
	            </div>
            </div>
            
        </form>	
	</div>
	
	<%-- Data Total Count --%>
    <div class="tableComment">
        <p class="list_count">총 <span>10</span>건</p>
    </div>	

	<%-- Data List --%>
	<div class="tableLayer">
		<table class="tableList">
               <caption>기술평가 목록</caption>
               <colgroup>
                   <col width="40px"/>
                   <col width="100px"/>
                   <col width="80px"/>
                   <col width="315px"/>
                   <col width="85px"/>
                   <col width="85px"/>
                   <col width="100px"/>
                   <col width="100px"/>
               </colgroup>			
			<thead class="line">
                <tr>
                    <th class="noBg">번호</th>
                    <th>공고번호</th>
                    <th>입찰구분</th>
                    <th>입찰명</th>
                    <th>공고일자</th>
                    <th>기술평가일자</th>
                    <th>소집서면구분</th>
                    <th>평가상태</th>
                </tr>
            </thead>
			<tbody> 
				<tr class="row" onclick="tchqvlnResultRegistForm('');" style="cursor: pointer;">
					<td>1</td>
		            <td>P2017-00100-1</td>
		            <td>공사</td>
		            <td class="left_T">서울센터 에어컨 배관 이설공사</td>
		            <td>2017-06-20</td>
		            <td>2017-06-30</td>
		            <td>소집</td>
		            <td><div class="btn_before">평가중</div></td>
		        </tr>
		        <tr class="row" onclick="tchqvlnResultRegistForm('');" style="cursor: pointer;">
		        	<td>2</td>
		            <td>P2017-00099-1</td>
		            <td>용역</td>
		            <td class="left_T">2017년 하반기 NCS기반 신입직원 채용대행 용역</td>
		            <td>2017-06-18</td>
		            <td>2017-06-28</td>
		            <td>소집</td>
		            <td><div class="btn_before">평가중</div></td>
		        </tr>
		        <tr class="row" onclick="tchqvlnResultRegistForm('');" style="cursor: pointer;">
		        	<td>3</td>
		            <td>P2017-00098-1</td>
		            <td>용역</td>
		            <td class="left_T">팔레스타인 창취업 지원프로그램 국내 PC용역</td>
		           	<td>2017-06-16</td>
		            <td>2017-06-26</td>
		            <td>소집</td>
		            <td><div class="btn_before">평가중</div></td>
		        </tr>
		        <tr class="row" onclick="tchqvlnResultRegistForm('');" style="cursor: pointer;">
		        	<td>4</td>
		            <td>P2017-00097-1</td>
		            <td>물품</td>
		            <td class="left_T">소모성 물품(MRO) 물품대행 사업자 선정</td>
		            <td>2017-06-16</td>
		            <td>2017-06-26</td>
		            <td>소집</td>
		            <td><div class="btn_before">평가중</div></td>
		        </tr>
		        <tr class="row" onclick="tchqvlnResultRegistForm('');" style="cursor: pointer;">
		        	<td>5</td>
		            <td>P2017-00096-1</td>
		            <td>용역</td>
		            <td class="left_T">인력선발 및 교육운영 지원업무 위탁 용역</td>
		            <td>2017-06-14</td>
		            <td>2017-06-24</td>
		            <td>소집</td>
		            <td><div class="btn_before">평가중</div></td>
		        </tr>
		        <tr class="row" onclick="tchqvlnResultRegistForm('');" style="cursor: pointer;">
		        	<td>6</td>
		            <td>P2017-00095-1</td>
		            <td>용역</td>
		            <td class="left_T">소셜미디어 홍보대행 [2017-2019] 용역</td>
		            <td>2017-06-12</td>
		            <td>2017-06-22</td>
		            <td>소집</td>
		            <td><div class="btn_before">평가중</div></td>
		        </tr>
		        <tr class="row" onclick="tchqvlnResultRegistForm('Y');" style="cursor: pointer;">
		        	<td>7</td>
		            <td>P2017-00094-1</td>
		            <td>공사</td>
		            <td class="left_T">서울센터 외벽도색공사</td>
		            <td>2017-06-10</td>
		            <td>2017-06-20</td>
		            <td>소집</td>
		            <td><div class="btn_before">평가완료</div></td>
		        </tr>
		        <tr class="row" onclick="tchqvlnResultRegistForm('Y');" style="cursor: pointer;">
		        	<td>8</td>
		            <td>P2017-00093-1</td>
		            <td>용역</td>
		            <td class="left_T">원조조달기업지원센터 위탁운영 용역</td>
		            <td>2017-06-08</td>
		            <td>2017-06-18</td>
		            <td>소집</td>
		            <td><div class="btn_before">평가완료</div></td>
		        </tr>
		        <tr class="row" onclick="tchqvlnResultRegistForm('Y');" style="cursor: pointer;">
		        	<td>9</td>
		            <td>P2017-00092-1</td>
		            <td>용역</td>
		            <td class="left_T">전자정부 및 ICT분야 ODA사업 통합감리용역</td>
		            <td>2017-06-06</td>
		            <td>2017-06-16</td>
		            <td>소집</td>
		            <td><div class="btn_before">평가완료</div></td>
		        </tr>
		        <tr class="row" onclick="tchqvlnResultRegistForm('Y');" style="cursor: pointer;">
		        	<td>10</td>
		            <td>P2017-00091-1</td>
		            <td>물품</td>
		            <td class="left_T" title="국제 동등성 확보 지원 및 통합시스템 구축사업 시험기자재 공급업체 선정">국제 동등성 확보 지원 및 통합시스템 구축사업 시험기자 ...</td>
		            <td>2017-06-04</td>
		            <td>2017-06-14</td>
		            <td>소집</td>
		            <td><div class="btn_before">평가완료</div></td>
		        </tr>                        
			</tbody>
		</table>
		
		<%--Data Paging --%>
		<div class="paging_place">
			<div class="paging_wrap">
				<a href="#" class="prev" title="앞으로"><span style="display: none;">앞으로</span></a>
				<span><a href="#" class="active" title="1">1</a>
				</span><a href="#" class="next" title="뒤로"><span style="display: none;">뒤로</span></a>
			</div>
		</div>
	</div>
	
	<%--page move form --%>
	<form id="registFrm" class="search_form" method="POST" >
		<input type="hidden" name="resourceName" value="${ param.resourceName }" >
	</form>
</div>	