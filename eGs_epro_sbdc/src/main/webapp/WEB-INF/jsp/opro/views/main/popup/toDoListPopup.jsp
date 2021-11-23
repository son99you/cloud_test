<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">--%>
<%--
 * 메인 > 오늘의 조달일정 (팝업)
 *
 * <pre>
 * main
 *    |_ toDoListPopup.jsp
 *    
 * </pre>
 * @date : 2019.03.14
 * @version : 1.0
 * @author : 은우소프트 은잔디
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %>

<div class="pop_wrap">
	<div class="pop_header">
		<h1 class="tit">오늘의 조달일정</h1>
	</div> <!--// pop_header E -->
	<div class="pop_container">
	           
		<!-- Data Total Count -->
	    <div class="pop_list_wrap">
			<div class="pop_list_top">
	        	<p class="pop_total">총 <span>${comFn:nvl(toDoListTotcnt, 0)}</span>건</p>
	        </div>
	       
	   		 <!-- Data List -->
			<div class="pop_list_conts">
				<table>
		            <caption>오늘의 조달일정 목록</caption>
		            <colgroup>
		                <col width="20%">
		                <col width="20%">
		                <col width="*">
		            </colgroup>			
					<thead>
		                <tr>
		                	<th>구분</th>
							<th>번호</th>
							<th>제목</th>
		                </tr> 
		            </thead> 
					<tbody>
						<c:if test="${comFn:nvl(toDoListTotcnt, 0) == 0}"> 
							<tr>
								<td colspan="3" style="text-align: center;">검색 조건에 해당하는 데이터가 존재하지 않습니다.</td>
							</tr>
						</c:if>
						<c:if test="${toDoListTotcnt > 0}">
							<c:forEach var="data" items="${toDoList}" varStatus="status">
								<tr>
									<td>
										<c:if test="${data.GUBUN_SN eq '1'}">사전공고</c:if>
										<c:if test="${data.GUBUN_SN eq '2'}">입찰공고</c:if>
										<c:if test="${data.GUBUN_SN eq '6'}">입찰서제출</c:if>
									</td>
									<td>${data.TODO_NO}</td>
									<td style="text-align: left; padding-left: 5px;">${data.TODO_NM}</td>
								</tr>
							</c:forEach>
						</c:if>
					</tbody> 
				</table>  
			</div>
			
			<div class="pop_list_bottom">
				<div class="btn_wrap view_btn">
					<button type="button" class="btn btn_m btn_del" onclick="window.close();">닫기</button>
				</div> <!--// btn_wrap E -->
	    		
			</div>
		</div>
	</div> 
</div>