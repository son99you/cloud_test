<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 입찰관리 > 나의 참가신청 상세 > 공동수급 협정서 제출 폼
 *
 * <pre>
 * ebid
 *    |_ myPartcptCopertnSpldmdTrtyOfeWritngForm.jsp
 * 
 
 * </pre>
 * @date : 2017.06.21
 * @version : 1.0
 * @author : 은우소프트 이주연  
--%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 

<link rel="stylesheet" type="text/css" href="${cssPath}/opro/supplierCommon.css">
<script type="text/javascript" src="${jsPath}/comm/comUtil.js"></script>  
<script type="text/javascript" src="${jsPath}/opro/views/ebid/myPartcptCopertnSpldmdTrtyOfeWritngForm.js"></script> 
 

<div class="contents">
	<div id="panelSubContent">
        <h4 class="bulSubTitle">조달입찰 공동수급협정서</h4>
        <div class="tableLayer"> 
            <table class="table" summary="공동수급협정서 입니다.">
                <caption>입찰개요</caption>
                <colgroup>
                    <col width="170px">
                    <col width="810px">
                </colgroup>
                <tbody>
                <tr class="line">
                    <th scope="row">입찰공고번호</th>
                    <td>
                       P2017-00004-1
                    </td>
                </tr>
                <tr>
                    <th scope="row">입찰명</th>
                    <td>송변전분야 유지보수용 예비품-램프 등 17종 </td>
                </tr>
                <tr>
                    <th scope="row">입찰구분</th>
                    <td>
                    	물품
                    </td>
                </tr>
                <tr>
                    <th scope="row">대표업체명</th>
                    <td>
                       은우소프트
                    </td>
                </tr>
                <tr>
                    <th scope="row">공동수급 방식</th>
                    <td>
						분담이행  
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
        
		<h4 class="bulSubTitle">선택한 공동수급 방식 : 분담이행  </h4>
        <div class="T_btnLayer fr cn n_m">
            <button type="button" class="grayBtn S" id="addBtn">업체추가</button>
            <button type="button" class="grayBtn S" id="nmenDeleteBtn">업체삭제</button>
        </div>
        <div class="tableLayer">
            <table class="tableList" summary="공동수급방식 입니다.">
                <caption>공동수급방식</caption>
                <colgroup>
	                    <col width="40px">
	                    <col width="80px">
	                    <col width="290px">
	                    <col width="200px">
	                    <col width="200px">
	                    <col width="170px">
                </colgroup>
                <thead>
                <tr>
                    <th scope="col" class="noBg">
                    선택
          <!--           	<label for="nmenChoiseAllCbx" class="blind">체크박스</label>
                    	<input type="checkbox" id="nmenChoiseAllCbx" name="nmenChoiseCbx" onclick="FwkCmmnUtil.setAllCheck('nmenChoiseAllCbx','nmenChoiseCbx');">
           -->          </th>
                    <th scope="col">구분</th>
                    <th scope="col">업체명</th>
                    <th scope="col">사업자등록번호</th>
                    <th scope="col">대표자</th>
                    <th scope="col">출자비율</th>
                </tr>
                </thead>
                <tbody id="trHiddTbdy">
                	<tr class="row" style="display: none;">
                		<td>
                			<label for="nmenChoiseCbx" class="blind">체크박스</label> 
							<input type="checkbox" id="nmenChoiseCbx" name="nmenChoiseCbx">
						</td>
						<td><input type="hidden" name="P_SDEN_REGIST_NO" disabled></td>
						<td>
							<label for="P_BID_NM" class="blind">업체명</label>
                        	<input type="text" name="P_SDEN_NM" style="width: 250px;" maxlength="100" disabled>
						</td>
						<td>
							<label for="P_BID_NM" class="blind">사업자등록번호</label>
                        	<input type="text" name="P_SDEN_BIZR_NO" style="width: 180px;" maxlength="10" disabled>
						</td>
						<td>
							<label for="P_BID_NM" class="blind">대표자</label>
                        	<input type="text" name="P_SDEN_RPRSNTV_NM" style="width: 180px;" maxlength="30" disabled>
						</td>
						<td>
							<label for="P_BID_NM" class="blind">출자비율</label>
                        	<input type="text" name="P_INVSTMNT_RATE" style="width: 100px;" maxlength="22" disabled numeric> %
						</td>
					</tr>
                </tbody>
                <tbody>
                		<tr class="row">
						<td>
							<label for="entrpsChoiseCbx${status.count }" class="blind">체크박스</label>
							<input type="checkbox" id="entrpsChoiseCbx${status.count }" name="entrpsChoiseCbx" value="">
						</td>
						<td>대표자</td>
						<td class="left_T">은우소프트</td>
						<td>119-86-02801</td>
						<td>정한규</td>
						<td><input type="text" value="90">% </td> 
					</tr>
					<tr class="row">
						<td>
							<label for="entrpsChoiseCbx${status.count }" class="blind">체크박스</label>
							<input type="checkbox" id="entrpsChoiseCbx${status.count }" name="entrpsChoiseCbx" value="">
						</td>
						<td>대표자</td>
						<td class="left_T">케이웨어</td>
						<td>429-56-11241</td>
						<td>이주연</td>
						<td><input type="text" value="10">%</td> 
					</tr>
                </tbody>
                <tbody id="trAddTbdy">
                </tbody>
            </table>
        </div>
        
        <div class="tableLayer">
            <table class="" summary="참가신청 인내 입니다.">
                <caption>참가신청안내</caption>
                <colgroup>
                    <col width="980px">
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
        
        <div class="contract_wrap">
	        <div class="contract_sign">
	        	<p>2017-06-21</p>
			    <p>신청자 : 홍길동</p>
			    <p>은우소프트 이사장 귀하 </p>
			</div>
        </div> 

        <div class="T_btnLayer fr" >
            <button type="button" class="blueBtn L" id="presentnBtn" >제출</button>
            <button type="button" class="blueBtn L" id="returnBtn">뒤로가기</button>
        </div>
    </div>
</div>
<%--page move form --%> 
<form id="detailFrm" class="search_form" method="POST" > 
	<input type="hidden" id="state" name="state" value="${parameterMap.state}" > 
	<input type="hidden" id="resourceName" name="resourceName" value="${param.resourceName}">
</form>
	

<form id="ccpyPopupFrm" method="POST" action=""></form>