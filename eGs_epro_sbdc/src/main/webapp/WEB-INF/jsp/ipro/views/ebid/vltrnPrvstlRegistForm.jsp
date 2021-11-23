<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 수의시담 상세
 *
 * <pre>
 * ebid
 *    |_ vltrnPrvstlDetail.jsp
 * 
 * </pre>
 * @date : 2017. 06. 20
 * @version : 1.0
 * @author : 은우소프트 홍찬일
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %> 
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %> 

<script type="text/javascript" src="${jsPath}/ipro/views/ebid/vltrnPrvstlRegistForm.js"></script> 
 
<div class="conts_wrap">
	<div class="inner">
		<div class="tit_wrap">
			<h3 class="tit">수의시담 등록</h3>
			<ul class="step_wrap">
				<li><a href="#">${myMenuList.bigMenuNm}</a></li>
				<li><a href="#">${myMenuSubList.smallMenuNm}</a></li>
			</ul> <!--// step_wrap E -->
		</div> <!--// tit_wrap E -->
		<form id="registFrm" method="POST" enctype="multipart/form-data" >
			<input type="hidden" name="P_PRCURE_REQEST_NO" value="">
			<input type="hidden" name="P_PRVSTL_PRST_CD" value="PO20">
			<input type="hidden" name="P_SCRIN_SE" value="newRegist">
			<input type="hidden" name="P_USER_ID" value="${sessionScope.loginResult.USER_ID}">
			<input type="hidden" id="resourceName" name="resourceName" value="${param.resourceName}">
			<input type="hidden" id="today" name="today" value="${today}">
			<input type="hidden" id="P_ATCHMNFL_GROUP_NO" name="P_ATCHMNFL_GROUP_NO">
			
			<div class="view_wrap typeA">
				<h4 class="tit">기본정보</h4>
		        <div class="view_area">
		            <table>
		                <caption>기본정보</caption>
		                <colgroup>
				        	<col style="width: 15%;">						        
				        	<col style="width: 35%;">						        
				        	<col style="width: 15%;">						        
				        	<col style="width: 35%;">
		                </colgroup>
		                <tbody>
			                <tr class="line">
			                    <th>의뢰번호</th>
			                    <td><font color="red">※자동생성</font>&nbsp;</td>
			                    <th class="bullet_orange" scope="row">의뢰일자</th>
			                    <td>
			                    	<div class="calendar_wrap">
			                    		<label for="P_REQEST_DE" class="blind">의뢰일자</label>
			                    		<input type="text"  id="P_REQEST_DE" name="P_REQEST_DE" date class="w280">
			                    	</div>
			                    </td>
			                </tr>
			                <tr>
			                    <th>계약방법</th>
			                    <td>수의계약						
			                    	<input type="hidden" name="P_CNTRCT_MTH_CD" value="31">&nbsp;</td>
			                    <th class="bullet_orange" scope="row">집행한도액</th>
			                    <td>
			                    	<label for="P_EXCUT_LMT_AMOUNT" class="blind">집행한도액</label>
			                    	<input type="text" class="lineTxt tr" id="P_EXCUT_LMT_AMOUNT" name="P_EXCUT_LMT_AMOUNT" value="" money></td>
			                </tr>
			                <tr>
			                	<th class="bullet_orange" scope="row">계약구분</th>
			                	<td>
				                	<div class="selectLayer2 w_50">
					            		<comTag:comCmcdCdValueComboBox id="P_PRCURE_SE_CD" name="P_PRCURE_SE_CD" selectKey="${param.PRCURE_SE_CD}" cdId="C00001" headerKey="" headerValue="선택" />
					         		</div>
			                	</td>
			                	<th class="bullet_orange">입찰종류</th>
			                	<td>
				                	<div class="selectLayer2 w_50">
					            		<comTag:comCmcdCdValueComboBox id="P_PRCURE_BSNS_SE_CD" name="P_PRCURE_BSNS_SE_CD" selectKey="${param.PRCURE_BSNS_SE_CD}" cdId="22358" headerKey="" headerValue="선택" />
					         		</div>
			                	</td> 
			                </tr>
			                <tr>
			                    <th class="bullet_orange" scope="row">의뢰명</th>
			                    <td colspan="3">
			                    		<label for="P_PRVSTL_NM" class="blind">의뢰명</label>
			                    		<input type="text" id="P_PRVSTL_NM" name="P_PRVSTL_NM" value=""></td>
			              	</tr>
			                <tr>
			                    <th class="bullet_orange" scope="row">시담선정업체</th>
			                    <td>
			                    	  <label for="arr_entrps_nm" class="blind">시담선정업체</label>
			                    	  <input type="text" class="disabled w280" id="entrpsNm" name="P_ENTRPS_NM" readonly="readonly">
			                    	  <input type="hidden" id="entrpsRegistNo" name="P_ENTRPS_REGIST_NO">
			                    	  <button type="button" class="btn btn_02 btn_sch vert" id="searchEntrpsBtn">검색</button>
			                   	</td>
			                    <th class="bullet_orange" scope="row">업체전화번호</th>
			                    <td>
			                    	<label for="arr_telno" class="blind">업체전화번호</label>
			                    	<input type="text"  name="P_TELNO" id="telno" size="40" tel>
			                    &nbsp;</td>
			                </tr>
			                <tr>
			                	<th class="bullet_orange" scope="row">업체 사업자 번호</th>
			                	<td colspan="3">
			                		<label for="arr_bizr_no" class="blind">업체 사업자 번호</label>
			                		<input type="text" class="disabled" readonly="readonly" name="P_BIZRNO" id="bizrno">
			                	</td>
			                </tr>
			                
			                <tr id="client">
			                    <th class="bullet_orange" scope="row">의뢰자</th>
			                    <td>
			                    	<label for="userNmclient" class="blind">의뢰자명</label>
			                    	<input type="text" class="disabled w280" id="userNmclient" name="P_CLIENT_NM" readonly="readonly"  >
			                    	<input type="hidden" id="userIdclient" name="P_CLIENT_ID">
			                    	<button type="button" class="btn btn_02 btn_sch vert" id="searchUserBtn1">검색</button>
								</td>
			                    <th class="bullet_orange" scope="row">의뢰부서</th>
			                    <td>
			                    	<label for="orgNmclient" class="blind">의뢰부서명</label>
									<input type="text" readonly="readonly" class="disabled" id="orgNmclient" name="P_REQEST_DEPT_NM" size="30" >
									<input type="hidden" id="orgCdclient"  name="P_REQEST_DEPT_NO" >
			                    &nbsp;</td>
			                </tr>
			                
			                <tr>
			                    <th>납품기한</th>
			                    <td colspan="3">
			                    		<label for="P_DVYFG_PD_CN" class="blind">납품기한</label>
			                    		<textarea rows="" cols="5" id="P_DVYFG_PD_CN" name="P_DVYFG_PD_CN" style="width: 100%; height: 70px;"></textarea>
			                    </td>
			                </tr>
		                </tbody>
		            </table>
				</div>
		        
		        <h4 class="tit" style="">시담요청정보</h4>
		        <div class="view_area">
		            <table>
		                <caption>시담요청정보</caption>
		                <colgroup>
				        	<col style="width: 15%;">						        
				        	<col style="width: 35%;">						        
				        	<col style="width: 15%;">						        
				        	<col style="width: 35%;">
			            </colgroup>
						<tr class="line">
							<th class="bullet_orange" scope="row">시담일시</th>
							<td colspan="3">
								<label for="P_PRVSTL_DT" class="blind">시담날짜</label>
                    			<input type="text"  id="P_PRVSTL_DT" name="P_PRVSTL_DT" date class="w280">
                				<label for="P_PRVSTL_DT_HH" class="blind">시담시간(시)</label>
                				&nbsp;&nbsp;
                				<input type="text" id="P_PRVSTL_DT_HH" name="P_PRVSTL_DT_HH" style="width: 50px;" numeric> &nbsp; : &nbsp;
                				<label for="P_PRVSTL_DT_mm" class="blind">시담시간(분)</label>
                				<input type="text" id="P_PRVSTL_DT_mm" name="P_PRVSTL_DT_mm" style="width: 50px;" numeric>
	                		</td>
						</tr>
						<tr id="charger">
							<th class="bullet_orange" scope="row">시담담당자</th>
							<td>	
								<label for="userNmcharger" class="blind">시담 담당자명</label>
								<input type="text" id="userNmcharger" name="P_PRVSTL_INNER_CHARGER_NM" class="disabled w280"  readonly="readonly"  value="${sessionScope.loginResult.USER_NM}">
								<input type="hidden" id="userIdcharger" name="P_PRVSTL_INNER_CHARGER_ID" class="fl" value="${sessionScope.loginResult.USER_ID}">
								<button type="button" class="btn btn_02 btn_sch vert" id="searchUserBtn2">검색</button> 
							</td>
							<th>시담담당부서</th>
							<td>
								<label for="orgNmcharger" class="blind">시담 담당부서</label>
								<input type="text" id="orgNmcharger" name="P_PRVSTL_CHRG_DEPT_NM" class="disabled" readonly="readonly" size="30" value="${sessionScope.loginResult.ORG_NM }">
								<input type="hidden" id="orgCdcharger" name="P_PRVSTL_CHRG_DEPT_NO" value="${sessionScope.loginResult.ORG_CD }">
							</td>
						</tr>
						<tr>
							<th>시담공지내용</th>
							<td colspan="3">
								<label for="P_PRVSTL_NOTICE_CN" class="blind">시담 공지내용</label>
								<textarea id="P_PRVSTL_NOTICE_CN" name="P_PRVSTL_NOTICE_CN" style="width: 100%; height: 70px;" ></textarea>
							</td>
						</tr>
						<tbody>
							<tr>
			               		<th scope="row" >첨부파일</th>
								<td colspan="3"> 
									<span class="stD" style="height: 30px;">
			                    		<button type="button"  class="btn btn_02 btn_sch" id="fileBtn" style="float: right; margin-top: 3px;">추가</button> 
			                    	</span> 
			                    	<div id="fileRow" style="display: none; height: 30px; margin-top: 3px;">
			                    		<input type="file" name="" onchange="fileChange(this)" style="height: 24px; margin-top: 3px;" >
					                   	<span class="stD"> 
			                    			<button type="button" class="btn btn_02 btn_sch" style="display: inline-block;" onclick="rowDel(this)">삭제</button>
			                    		</span> 
			                    	</div>
			                    	<div id="fileDiv" style="width: 96%; line-height: 30px; margin-top: 3px;">
			                    	</div>
			                   	</td>	
			            	</tr>
						</tbody>
		            </table>
		         </div>
         		<div class="btn_wrap view_btn">
				    <button type="button" class="btn btn_02 btn_revise" id="registBtn">저장</button>
		           	<button type="button" class="btn btn_02 btn_sch" id="listBtn">취소</button>
		        </div>
         	</div>
		</form>
	</div>	
</div>
<form id="popupFrm" method="POST">
	<input type="hidden" name="searchGbnId" id="searchGbnId" >
	<input type="hidden" name="P_ORG_NM_S" id="P_ORG_NM_S">
	<input type="hidden" id="resourceName" name="resourceName" value="${param.resourceName}">
	<input type="hidden" id="P_ENTRPS_CNT" name="P_ENTRPS_CNT" value="1">
	<input type="hidden" id="P_USER_NM_S" name="P_USER_NM_S">
	<input type="hidden" id="P_ENTRPS_NM_S" name="P_ENTRPS_NM_S">
</form>
<form id="detailFrm" method="POST">
	<input type="hidden" name="P_PRVSTL_NO" value="${P_PRVSTL_NO }">
	<input type="hidden" id="resourceName" name="resourceName" value="${param.resourceName}">
	<input type="hidden" id="REFRESH" value="${REFRESH}">
</form>
<form id="listFrm" method="POST">
	<input type="hidden" id="resourceName" name="resourceName" value="${param.resourceName}">
</form>