<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 수의시담 수정
 *
 * <pre>
 * ebid
 *    |_ vltrnPrvstlModify.jsp
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

<script type="text/javascript" src="${jsPath}/ipro/views/ebid/vltrnPrvstlModifyForm.js"></script> 
 
<div class="conts_wrap">
	<div class="inner">
		<div class="tit_wrap">
			<h3 class="tit">수의시담 수정</h3>
			<ul class="step_wrap">
				<li><a href="#">${myMenuList.bigMenuNm}</a></li>
				<li><a href="#">${myMenuSubList.smallMenuNm}</a></li>
			</ul> <!--// step_wrap E -->
		</div> <!--// tit_wrap E -->
		
		<form id="updtFrm" method="POST" action="" enctype="multipart/form-data" >
			<input type="hidden" name="resourceName" value="${param.resourceName}">
	  		<input type="hidden" name="resourceDesc" value="${param.resourceDesc}">
			<input type="hidden" id="P_DELETE_FILE_SN" name="P_DELETE_FILE_SN">
			<input type="hidden" name="P_PRVSTL_PRST_CD" value="PO20">
			<input type="hidden" name="P_USER_ID" value="${sessionScope.loginResult.USER_ID}">
			<input type="hidden" id="P_ATCHMNFL_GROUP_NO" name="P_ATCHMNFL_GROUP_NO" value="${vltrnPrvstlDetail.ATCHMNFL_GROUP_NO }">
			<input type="hidden" id="P_PRVSTL_NO" name="P_PRVSTL_NO" value="${vltrnPrvstlDetail.PRVSTL_NO }">
			
			<div class="view_wrap typeA">
				<h4 class="tit">기본정보</h4>
	        	<div class="view_area">
		            <table class="table" summary="기본정보 입니다.">
		                <caption>기본정보</caption>
		                <colgroup>
		                    <col width="15%">
		                    <col width="35%">
		                    <col width="15%">
		                    <col width="35%">
		                </colgroup>
		                <tbody>
			                <tr class="line">
			                    <th scope="row" >공고번호</th>
			                    <td>P2018-00013-1</td>
			                    <th scope="row" >작성일자</th>
			                    <td>2018-08-30</td>
			                </tr>
			                <tr>
			                    <th scope="row" >공고부서</th>
			                    <td id="DeptTd">솔루션개발팀</td>
			                    <th scope="row" >계약담당자</th>
			                    <td id="emplyrTd">user1</td>
			                </tr>
			                <tr>
			                    <th scope="row" >요구부서</th>
			                    <td id="DeptTd">솔루션개발팀</td>
			                    <th scope="row" >요구자</th>
			                    <td id="emplyrTd">user1</td>
			                </tr>
		                </tbody>
		            </table>
	       	 	</div>
				<h4 class="tit">기본정보</h4>
				 <div class="view_area">
		            <table class="table">
		                <caption>기본정보</caption>
		                <colgroup>
		                   <col width="15%">
		                   <col width="35%">
		                   <col width="15%">
		                   <col width="35%">
		                </colgroup>
		                <tbody>
		                <tr>
		                    <th class="bullet_orange" scope="row">입찰공고명</th>
		                    <td colspan="3">
		                    		<label for="P_PRVSTL_NM" class="blind">의뢰명</label>
		                    		<input type="text" id="P_PRVSTL_NM" name="P_PRVSTL_NM" value="노트북 구매"></td>
		                </tr>
		                <tr>
	                	<th>구매구분</th>
	                	<td>내자구매</td>
	                    <th>계약방법</th>
	                    <td>수의계약						
	                    	<input type="hidden" name="P_CNTRCT_MTH_CD" value="31">&nbsp;
                    	</td>
	                </tr>
	                <tr>
	                	<th>낙찰방법</th>
	                	<td>예가이하 최저가</td>
	                		<th scope="row">예가방식</th>
	                	<td>
	              			  비예가
	                    </td>
	                </tr>
	                <tr>
	                	<th>추정금액</th>
	                	<td>200,000,000</td>
	                </tr>
		                </tbody>
		            </table>
		        </div>
		        <h4 class="tit">시담진행순서</h4>
        		<div class="view_area">
		            <table>
		                <caption>시담진행순서</caption>
		                <colgroup>
			               	<col style="width: 15%;">
							<col style="width: *;">
			            </colgroup>
						<tr class="line">
							<th class="bullet_orange" scope="row">시담요청일시</th>
							<td>
								<label for="P_PRVSTL_DT" class="blind">시담날짜</label>
                    			<input type="text"  id="P_PRVSTL_DT" name="P_PRVSTL_DT" date class="w170" value="2018-09-01">
                				<label for="P_PRVSTL_DT_HH" class="blind">시담시간(시)</label>
                				<input type="text" id="P_PRVSTL_DT_HH" name="P_PRVSTL_DT_HH" style="width: 50px;" value="10" numeric> &nbsp; : &nbsp;
                				<label for="P_PRVSTL_DT_mm" class="blind">시담시간(분)</label>
                				<input type="text" id="P_PRVSTL_DT_mm" name="P_PRVSTL_DT_mm" style="width: 50px;" value="00" numeric>
	                		</td>
						</tr>
						<tr class="line">
							<th class="bullet_orange" scope="row">시담제출기간</th>
							<td>
								<label for="P_PRVSTL_DT" class="blind">시담날짜</label>
                    			<input type="text"  id="P_PRVSTL_DT" name="P_PRVSTL_DT" date class="w170" value="2018-09-01">
                				<label for="P_PRVSTL_DT_HH" class="blind">시담시간(시)</label>
                				<input type="text" id="P_PRVSTL_DT_HH" name="P_PRVSTL_DT_HH" style="width: 50px;" value="10" numeric> &nbsp; : &nbsp;
                				<label for="P_PRVSTL_DT_mm" class="blind">시담시간(분)</label>
                				<input type="text" id="P_PRVSTL_DT_mm" name="P_PRVSTL_DT_mm" style="width: 50px;" value="00" numeric>
                				&nbsp;~&nbsp;<label for="P_PRVSTL_DT" class="blind">시담날짜</label>
                    			<input type="text"  id="P_PRVSTL_DT" name="P_PRVSTL_DT" date class="w170" value="2018-09-10">
                				<label for="P_PRVSTL_DT_HH" class="blind">시담시간(시)</label>
                				<input type="text" id="P_PRVSTL_DT_HH" name="P_PRVSTL_DT_HH" style="width: 50px;" value="10" numeric> &nbsp; : &nbsp;
                				<label for="P_PRVSTL_DT_mm" class="blind">시담시간(분)</label>
                				<input type="text" id="P_PRVSTL_DT_mm" name="P_PRVSTL_DT_mm" style="width: 50px;" value="00" numeric>
	                		</td>
						</tr>
						<tr class="line">
							<th class="bullet_orange" scope="row">개찰일시</th>
							<td>
								<label for="P_PRVSTL_DT" class="blind">시담날짜</label>
                    			<input type="text"  id="P_PRVSTL_DT" name="P_PRVSTL_DT" date class="w170" value="2018-09-11">
                				<label for="P_PRVSTL_DT_HH" class="blind">시담시간(시)</label>
                				<input type="text" id="P_PRVSTL_DT_HH" name="P_PRVSTL_DT_HH" style="width: 50px;" value="10" numeric> &nbsp; : &nbsp;
                				<label for="P_PRVSTL_DT_mm" class="blind">시담시간(분)</label>
                				<input type="text" id="P_PRVSTL_DT_mm" name="P_PRVSTL_DT_mm" style="width: 50px;" value="00" numeric>
	                		</td>
						</tr>
					</table>
		         </div>
		          <div class="tit_area">
	     				<h4 class="tit">시담업체</h4>
		            	 <div class="btn_right">
				            <button type="button" style="width: 100px;" class="btn btn_s2 btn_c2" id="enAddBtn" name="enAddBtn" value="0">시담업체업체추가</button>
				            <button type="button" style="width: 100px;" class="btn btn_s2 btn_c2" id="enDelBtn" name="enDelBtn" value="0">시담업체업체삭제</button>
				        </div>
			        </div>
			        <div class="view_area">
		            <table class="tableList" summary="시담업체 입니다." id="">
		                <caption>시담업체내역</caption>
		                <colgroup>
							<col width="5%">
							<col width="15%">
							<col width="20%">
							<col width="20%">
							<col width="20%">
							<col width="20%">
		                </colgroup>
		                <thead>
			                <tr><!--  class="line" -->
			                    <th style="text-align: center;" rowspan="2" class="noBg" scope="col">선택</th>
			                    <th style="text-align: center;" scope="col">사업자번호</th>
			                    <th style="text-align: center;" scope="col">업체명</th>
			                    <th style="text-align: center;" scope="col">담당자명</th>
			                    <th style="text-align: center;" scope="col">이메일</th>
			                    <th style="text-align: center;" scope="col">전화번호</th>
			                </tr>
		                </thead>
		                <tbody id="">
								<tr class="row">
									<td style='text-align: center;'><input type='checkbox' name='itemChoiseCbx'></td>
									<td style="text-align: center;">119-86-02801</td>
									<td style="text-align: left;">(주)은우소프트</td>
									<td style="text-align: left;"><input type="text" value="홍찬일"></td>
									<td style="text-align: left;"><input type="text" value="hci1135@eunwoosoft.com"></td>
									<td style="text-align: left;"><input type="text" value="02-547-8548"></td>
								</tr>
		                </tbody>
		            </table>
	            	</div>
		          <h4 class="tit">시담첨부문서</h4>
					<div class="view_area">
						<table id="fileTable">
					    	<colgroup>
					    		<col style="width: 15%;">
								<col style="width: auto;">
					        </colgroup>
				            <tr>
			                	<th scope="row" class="vtop">첨부파일</th>
								<td class="vtop">
								<span class="stD">
			                    	<button type="button"  class="btn btn_02 btn_sch" id="fileBtn" style="float: right;">추가</button> 
			                    </span>
			                    	<div id="fileRow" style="display: none; height: 30px;">
			                    		<input type="file" name="" style="width: 80%;height: 24px;" >
					                   	<span class="stD"> 
			                    			<button type="button" class="btn btn_02 btn_sch" style="display: inline-block; margin-top: 3px;" onclick="rowDel(this)">삭제</button>
			                    		</span>
			                    	</div>
			                    	<div id="fileViewDiv" style="width: 680px; line-height: 30px;">
			                    		<c:if test="${not empty atchFileList }">
						            		<c:forEach var="data" items="${atchFileList }" varStatus="status">
						            			<div style="height: 30px;"> 
					                   				<a href="javascript:pageObj.download('${data.ATCHMNFL_SN}');" class="attfile">${data.ATCHMNFL_NM }</a>
					                   				<span class="stD">
						                   				<button type="button" class="btn btn_02 btn_sch" style="display: inline-block; margin-top: 3px;" onclick="fileDel(this,'${data.ATCHMNFL_SN }')">삭제</button>
						                   			</span> 
					                    		</div>
						            		</c:forEach>
						            	</c:if>
			                    	</div>
			                    	<div id="fileDiv" style="width: 680px; line-height: 30px;">
			                    	</div>
			                    </td>	
				             </tr>
					    </table>
					</div>
					<div class="tit_area">
				        	<h4 class="tit" style="clear: both;">시담물품내역</h4>
					        <div class="btn_right">
<!-- 					            <button type="button" class="btn btn_02_auto btn_c2" id="biprInfoSearchBtn">구매물품 찾기</button> -->
<!-- 					            <button type="button" class="btn btn_02_auto btn_c2" id="biprInfoDeleteBtn">구매물품 삭제</button> -->
					        </div>
						</div>
						<div class="view_area" style="margin-bottom: 30px;">
				            <table class="tableList" summary="입찰품목정보 수정 입니다.">
				                <caption>입찰품목정보</caption>
				                <colgroup>
				                    <col width="5%">
				                    <col width="10%">
				                    <col width="10%">
				                    <col width="*">
				                    <col width="10%">
				                    <col width="10%">
				                    <col width="10%">
				                    <col width="10%">
				                    <col width="12%">
				                    <col width="12%">
				                </colgroup>
				                <thead>
				                <tr>
				                    <th scope="col" style="text-align: center;">No</th>
				                    <th scope="col" style="text-align: center;">품명번호</th>
				                    <th scope="col" style="text-align: center;">품목분류</th>
				                    <th scope="col" style="text-align: center;">품명</th>
				                    <th scope="col" style="text-align: center;">규격</th>
				                    <th scope="col" style="text-align: center;">수량</th>
				                    <th scope="col" style="text-align: center;">단위</th>
				                    <th scope="col" style="text-align: center;">추정단가</th>
				                    <th scope="col" style="text-align: center;">추정금액</th>
				                </tr>
				                </thead>
				                <tbody>
				                	<tr>
				                		<td style="text-align: center;">1</td>
				                		<td style="text-align: center;">A20180001</td>
				                		<td style="text-align: center;">노트북</td>
				                		<td>삼성노트북</td>
				                		<td style="text-align: center;">Sens 520</td>
				                		<td style="text-align: center;">200</td>
				                		<td style="text-align: center;">EA</td>
		                        		<td style="text-align: right;">1,000,000</td>
				                		<td style="text-align: right;">200,000,000</td>
				                	</tr>
				               	</tbody>
				            </table>
						</div>
			        </div>
				<div class="btn_wrap view_btn">
					<button type="button" class="btn btn_02 btn_revise" id="saveBtn" >저장</button>
			    	<button type="button" class="btn btn_02 btn_sch" id="cancelBtn" >취소</button>
			    </div>
			</div>
		</form>
	</div>
</div><!-- content E -->

<%-- DETAIL FORM --%>
<form id="detailFrm" method="POST" > 
	<input type="hidden" name="resourceName" value="${ param.resourceName }" >
	<input type="hidden" id="P_PRVSTL_NO" name="P_PRVSTL_NO" value="${vltrnPrvstlDetail.PRVSTL_NO }">
</form>
 <form id="popupFrm" method="POST">
    	<input type="hidden" name="searchGbnId" id="searchGbnId" >
    	<input type="hidden" name="P_ORG_NM_S" id="P_ORG_NM_S">
    	<input type="hidden" id="resourceName" name="resourceName" value="${param.resourceName}">
    	<input type="hidden" id="P_ENTRPS_CNT" name="P_ENTRPS_CNT" value="1">
    	<input type="hidden" id="P_USER_NM_S" name="P_USER_NM_S">
    	<input type="hidden" id="P_ENTRPS_NM_S" name="P_ENTRPS_NM_S">
 </form>