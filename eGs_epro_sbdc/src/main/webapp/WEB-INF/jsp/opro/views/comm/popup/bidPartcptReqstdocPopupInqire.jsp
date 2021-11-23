<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%--
 * 입찰참가신청서보기 
 *
 * <pre>
 * elbi 
 *  |_popup
 *   | 	bidPartcptReqstdocPopupInqire.jsp
 * 
 * </pre>
 * @date : 2017. 06. 20.
 * @version : 1.0
 * @author : 은우소프트 이주연 
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %>

<script type="text/javascript" src="${jsPath}/views/comm/popup/bidPartcptReqstdocPopupInqire.js"></script>

<div id="windowPopup">
<h3 class="windowTitle">입찰참가신청서 조회</h3>
		<fieldset>
               <legend>입찰참가신청서 조회</legend>
         </fieldset>		
        <div class="tableLayer">
            <p class="popSubTitle marginSet">입찰개요</p>
            <table class="table">
                <caption>입찰개요</caption>
                <colgroup>
                 	<col width="120px">
                    <col width="230px">
                    <col width="120px">
                    <col width="230px">
                </colgroup>
                <tbody>
	                <tr class="line">
	                    <th>입찰공고번호</th>
	                    <td colspan="3">2017-00004-1</td>
	                </tr>
	                <tr>
	                    <th>입찰명</th>
	                    <td colspan="3">송변전분야 유지보수용 예비품-램프 등 17종 &nbsp;</td>
	                </tr>
	                <tr>
	                    <th>입찰구분</th>
	                    <td colspan="3">물품&nbsp;</td>
	                </tr>
	                <tr>
	                    <th>입찰방법</th>
	                    <td colspan="3">일반경쟁 &nbsp; > &nbsp; 협상에 의한 계약&nbsp;</td>
	                </tr>
                </tbody>
            </table>
        </div>
        <div class="tableLayer">
            <p class="popSubTitle marginSet">신청인</p>
            <table class="table">
                <caption></caption>
                <colgroup>
                    <col width="120px">
                    <col width="230px">
                    <col width="120px">
                    <col width="230px">
                </colgroup>
                <tbody>
	                <tr  class="line">
	                    <th>업체명</th>
	                    <td colspan="3">은우소프트&nbsp;</td>
	                </tr>
	                <tr>
	                    <th>대표자</th>
	                    <td>정한규&nbsp;</td>
	                    <th>사업자등록번호</th>
	                    <td>119-86-02801&nbsp;</td>
	                </tr>
	                <tr>
	                	<th>주소</th>
	                	<td colspan="3">구로구 디지털로 33길 28, 1402호 (구로동 우림이비지센터1차)&nbsp;</td>
	                </tr>
	                <tr>
	                	<th>담당자</th>
	                	<td>테스트&nbsp;</td>
	                	<th>전화번호</th>
	                	<td>02-841-0711&nbsp;</td>
	                </tr>
	                <tr>
	                	<th>담당자 이메일</th>
	                	<td colspan="3">eunwoo@eunwoosoft.com&nbsp;</td>
	                </tr>
                </tbody>
            </table>
        </div>
        
        <br><br>
        
        <div class="tableLayer">
	      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	        본인은 위의 번호를 공고한 입찰 건에 대해 은우소프트에서 정한 입찰유의서 및 입찰 공고<br>
	      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
		    사항을 모두 승낙하고 입찰 참가 신청을 합니다.
      <br><br>
      
      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      	2017-01-03
      <br>
      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      신청자 : 테스트
      <br>
      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      은우소프트 이사장 귀하
      <br><br>
      
        <div class="T_btnLayer fr">
            <button type="button" class="blueBtn L" id="closeBtn" onclick="window.close();">닫기</button>
        </div>
      </div>
</div>         
