<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%--
 * 공동수급협정서 조회
 *
 * <pre>
 * elbi 
 *  |_popup
 *   | 	copertnSpldmdTrtyOfePopupInqire.jsp
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

<script type="text/javascript" src="${jsPath}/views/comm/popup/copertnSpldmdTrtyOfePopupInqire.js"></script>

<div id="windowPopup">
	<h3 class="windowTitle">공동수급협정서 조회</h3>
    <form id="registFrm" method="POST">
    <input type="hidden" id="resourceName" name="resourceName" value="${param.resourceName}">
	<fieldset>
        <legend>공동수급협정서 조회</legend>
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
	                <tr>
	                    <th>대표업체명</th>
	                    <td colspan="3">은우소프트</td>
	                </tr>
                </tbody>
            </table>
        </div>
        
        <div class="tableLayer">
	        <p class="popSubTitle marginSet">공동수급 방식 : 단독이행 </p>
            <table class="tableList" summary="공동수급방식 입니다.">
                <caption>공동수급방식</caption>
                <colgroup>
                    	<col width="80px">
	                    <col width="220px">
	                    <col width="150px">
	                    <col width="150px">
	                    <col width="100px">
                </colgroup>
                <thead>
                <tr>
               		<th scope="col">구분</th>
                    <th scope="col">업체명</th>
                    <th scope="col">사업자등록번호</th>
                    <th scope="col">대표자</th>
                    <th scope="col">출자비율</th>
                </tr>
                </thead>
                <tbody>
                	<tr class="row">
                		<td>대표자</td>
						<td class="left_T">은우소프트</td>
						<td>119-86-02801</td>
						<td>정한규</td>
						<td>	90 % </td> 
					</tr>
					<tr>
						<td>대표자</td>
						<td class="left_T">케이웨어</td>
						<td>429-56-11241</td>
						<td>이주연</td> 
						<td>10% </td> 
					</tr>
                </tbody>
            </table>
        </div>
        
        <div class="tableLayer">
            <table class="" summary="참가신청 인내 입니다.">
                <caption>참가신청안내</caption>
                <colgroup>
                    <col width="700px">
                </colgroup>
                <tbody>
                <tr> 
                    <td>
                       본인은 귀 은우소프트에서 집행하는 위의 입찰에 참가함에 있어 입찰공고 및 입찰설명서에 정한 바에 따라 본 협정서를 제출하며 낙찰자로 선정될 경우에는 은우소프트 공동도급계약 운용기준에서 정한 공동수급협정서 제출을 확약합니다.
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
        
      <br><br>
      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      2017-01-01
      <br>
      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
     &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      신청자 : 테스트
      <br>
      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      은우소프트 이사장 귀하
      <br><br>
        
        <div class="T_btnLayer fr">
            <button type="button" class="blueBtn L" id="closeBtn" onclick="window.close();">닫기</button>
        </div>
    </fieldset>
    </form>
</div>