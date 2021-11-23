<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 수의시담 > 수의시담 상세 - 시담진행
 *
 * <pre>
 *vlpr 
 *    |_ iepVlprVltrnPrvstlProgrsDetail.jsp
 * 
 * </pre>
 * @date : 2015. 03. 09. 오후 12:01:44
 * @version : 1.0
 * @author : 은우소프트 손연우
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %> 
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %> 

<script type="text/javascript" src="${jsPath}/ipro/views/ebid/vltrnPrvstlProgrsDetail.js"></script>

<div class="conts_wrap">
	<div class="inner">
		<div class="tit_wrap">
		<c:if test="${gbn eq 'B'}"><h3 class="tit">수의시담요청 상세</h3></c:if>
		<c:if test="${gbn eq 'C'}"><h3 class="tit">개찰관리 상세</h3></c:if>
		<c:if test="${gbn eq 'D'}"><h3 class="tit">낙찰관리 상세</h3></c:if>
		<c:if test="${gbn eq 'E'}"><h3 class="tit">개찰관리 상세</h3></c:if>
			<ul class="step_wrap">
				<li><a href="#">${myMenuList.bigMenuNm}</a></li>
				<li><a href="#">${myMenuSubList.smallMenuNm}</a></li>
			</ul> <!--// step_wrap E -->
		</div> <!--// tit_wrap E -->
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
        	<h4 class="tit">시담기본정보</h4>
       		<div class="view_area">
	            <table class="table">
	                <caption>기본정보</caption>
	                <colgroup>
	                   <col width="15%">
		                    <col width="35%">
		                    <col width="15%">
		                    <col width="35%">
	                </colgroup>
	                <tr> 
	                    <th>입찰공고명</th>
	                    <td colspan="3"> 노트북 구매&nbsp;</td>
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
	                	<td>15,000,000</td>
	                </tr>
	            </table>
        	</div>
		        
		    <h4 class="tit">시담진행순서</h4>
        	<div class="view_area">
	            <table class="table">
	                <caption>시담요청정보</caption>
	                <colgroup>
		                <col style="width: 15%;">
						<col style="width: *;">
		            </colgroup>
					<tbody>
						<tr class="line">
							<th class="bullet_orange" scope="row">시담요청일시</th>
							<td>
								2018-09-01 10:00&nbsp;
							</td>
						</tr>
						<tr class="line">
							<th class="bullet_orange" scope="row">시담제출기간</th>
							<td>
								2018-09-01 10:00
								~
								2018-09-10 10:00
							</td>
						</tr>
						<tr class="line">
							<th class="bullet_orange" scope="row">개찰일시</th>
							<td>
								2018-09-11 10:00
							</td>
						</tr>
					</tbody>
	            </table>
	            </div><!-- subscript_info E  -->
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
			                    	<div id="fileRow" style="display: none; height: 30px;">
			                    		<input type="file" name="" style="width: 80%;height: 24px;" >
					                   	<span class="stD"> 
<!-- 			                    			<button type="button" class="btn btn_02 btn_sch" style="display: inline-block; margin-top: 3px;" onclick="rowDel(this)">삭제</button> -->
			                    		</span>
			                    	</div>
			                    	<div id="fileViewDiv" style="width: 680px; line-height: 30px;">
			                    		<c:if test="${not empty atchFileList }">
						            		<c:forEach var="data" items="${atchFileList }" varStatus="status">
						            			<div style="height: 30px;"> 
					                   				<a href="javascript:pageObj.download('${data.ATCHMNFL_SN}');" class="attfile">${data.ATCHMNFL_NM }</a>
					                   				<span class="stD">
<%-- 						                   				<button type="button" class="btn btn_02 btn_sch" style="display: inline-block; margin-top: 3px;" onclick="fileDel(this,'${data.ATCHMNFL_SN }')">삭제</button> --%>
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
			        
			          <c:if test="${gbn eq 'C' or gbn eq 'D' or gbn eq 'E'}">
			       		 <div class="tit_area">
				        	<h4 class="tit" style="clear: both;">예가정보</h4>
						</div>
						<div class="view_area" style="margin-bottom: 30px;">
				            <table class="tableList" summary="입찰품목정보 수정 입니다.">
				                <caption>입찰품목정보</caption>
				                <colgroup>
				                    <col width="15%">
				                    <col width="35%">
				                    <col width="15%">
				                    <col width="35%">
				                </colgroup>
				                <tr>
				                    <c:if test="${gbn eq 'E'}">
					                    <th scope="col">기초금액</th>
				                    	<td>13,500,000</td>
				                    	<th scope="col">에정금액</th>
				                   		<td>12,760,000</td>
				                    </c:if>
				                    <c:if test="${gbn ne 'E'}">
				                    	<th scope="col">기초금액</th>
				                    	<td><input type="text" value="13,500,000"></td>
				                    	<th scope="col">에정금액</th>
				                    <td><input type="text" value="12,760,000"></td>
				                    </c:if>
				                    
				                </tr>
				            </table>
						</div>
						
			       		 <div class="tit_area">
				        	<h4 class="tit" style="clear: both;">시담업체</h4>
				        	<div class="btn_right">
				        	 <c:if test="${gbn eq 'D'}">
					            <button type="button" class="btn btn_02_auto btn_c2" id="biprInfoDeleteBtn">낙찰등록</button>
					         </c:if>
					        </div>
						</div>
						<div class="view_area" style="margin-bottom: 30px;">
				            <table class="tableList" summary="입찰품목정보 수정 입니다.">
				                <caption>입찰품목정보</caption>
				                <colgroup>
				                    <col width="5%">
				                    <col width="*">
				                    <col width="10%">
				                    <col width="10%">
				                    <col width="10%">
				                    <col width="10%">
				                    <col width="10%">
				                    <col width="10%">
				                    <col width="10%">
				                </colgroup>
				                <tr>
				                	<c:if test="${gbn eq 'C' or gbn eq 'E'}">
				                    	<th scope="col" style="text-align: center;">No</th>
				                	</c:if>
				                	<c:if test="${gbn eq 'D'}">
				                		<th scope="col" style="text-align: center;"><input type="checkbox"></th>
				                	</c:if>
				                    <th scope="col" style="text-align: center;">업체명</th>
				                    <th scope="col" style="text-align: center;">사업자번호</th>
				                    <th scope="col" style="text-align: center;">시담일시</th>
				                    <th scope="col" style="text-align: center;">시담IP</th>
				                    <th scope="col" style="text-align: center;">담당자명</th>
				                    <th scope="col" style="text-align: center;">제안가격</th>
				                    <th scope="col" style="text-align: center;">투찰율</th>
				                    <th scope="col" style="text-align: center;">비고</th>
				                </tr>
				                <tr>
				                	<c:if test="${gbn eq 'C' or gbn eq 'E'}">
				                		<td style="text-align: center;">1</td>
				                	</c:if>
				                	<c:if test="${gbn eq 'D'}">
				                		<td scope="col" style="text-align: center;"><input type="checkbox"></td>
				                	</c:if>
				                	<td>은우소프트</td>
				                	<td style="text-align: center;">119-86-02801</td>
				                	<td style="text-align: center;">2018-08-30</td>
				                	<td style="text-align: center;">192.168.0.1</td>
				                	<td style="text-align: center;">홍찬일</td>
				                	<td style="text-align: right;">198,000,000</td>
				                	<td style="text-align: center;">100%</td>
				                	<td></td>
				                </tr>
				            </table>
						</div>
						<c:if test="${gbn eq 'E'}">
						 <div class="tit_area">
				        	<h4 class="tit" style="clear: both;">유찰정보</h4>
						</div>
						<div class="view_area" style="margin-bottom: 30px;">
				            <table class="tableList" summary="입찰품목정보 수정 입니다.">
				                <caption>입찰품목정보</caption>
				                <colgroup>
				                    <col width="15%">
				                    <col width="35%">
				                    <col width="15%">
				                    <col width="35%">
				                </colgroup>
				                <tr>
				                    <th scope="col">유찰단계</th>
				                    <td>개찰</td>
				                    <th>유찰일시</th>
				                    <td>2018-08-30 14:00</td>
			                    </tr>
			                    <tr>
				                    <th scope="col">유찰사유</th>
				                    <td>예가초과</td>
				                    <th>유찰처리자</th>
				                    <td>홍길동</td>
				                </tr>
				            </table>
						</div>
						</c:if>
			        </c:if>
		  
	       
	        
	        <div class="btn_wrap view_btn">
	        	 <c:if test="${gbn eq 'E'}">
			        <button type="button" class="btn_04 btn_revise" id="listBtn">재공고진행</button>
	        	 </c:if>
	        	 <c:if test="${gbn eq 'C'}">
			        <button type="button" class="btn btn_02 btn_revise" id="listBtn">개찰</button>
			        <button type="button" class="btn btn_02 btn_revise" id="listBtn">유찰</button>
	        	 </c:if>
	        	 <c:if test="${gbn eq 'B'}">
		        <button type="button" class="btn btn_02 btn_revise" id="listBtn">정정요청</button>
		        <button type="button" class="btn btn_02 btn_revise" id="listBtn">요청취소</button>
		        <button type="button" class="btn btn_02 btn_revise" id="listBtn">유찰</button>
	        	 </c:if>
		        <button type="button" class="btn btn_02 btn_sch" id="listBtn">목록</button>
	        </div>
<!-- 	        <div class="btn_wrap view_btn fr"> -->
<!-- 		        <button type="button" class="btn btn_02 btn_revise" id="updtBtn">수정</button> -->
<!-- 		        <button type="button" class="btn btn_02 btn_revise" id="deleteBtn">삭제</button> -->
<!-- 		        <button type="button" class="btn btn_02 btn_revise" id="listBtn">목록</button> -->
<!-- 	        </div> -->
      </div>
		</div>
     </div>
<form id="listFrm" method="POST">
	<input type="hidden" id="resourceName" name="resourceName" value="${param.resourceName}">
</form>

<form id="refreshFrm" method="POST">
	<input type="hidden" name="P_PRVSTL_NO" value="${vltrnPrvstlDetail.PRVSTL_NO }"/>
	<input type="hidden" name="P_TYPE_SE" value="refresh"/>
	<input type="hidden" id="resourceName" name="resourceName" value="${param.resourceName}">
</form>

<form id="prvstlRequstFrm" method="POST">
	<input type="hidden" name="P_PRVSTL_NO" value="${vltrnPrvstlDetail.PRVSTL_NO }"/>
	<input type="hidden" name="P_PRCURE_REQEST_NO" value="${vltrnPrvstlDetail.PRCURE_REQEST_NO }"/>
	<input type="hidden" name="P_PRVSTL_PRST_CD" value="PO50"/>
	<input type="hidden" name="P_SCRIN_SE" value="detail"/>
	<input type="hidden" id="resourceName" name="resourceName" value="${param.resourceName}">
</form>

<form id="updtFrm" method="POST">
	<input type="hidden" name="P_PRVSTL_NO" value="${vltrnPrvstlDetail.PRVSTL_NO }">
	<input type="hidden" id="resourceName" name="resourceName" value="${param.resourceName}">
</form>

<%-- 전자결재 연동 폼
		결재업무코드 (SANCTN_JOB_CD)
			CPR010 : 입찰 계획 작성
			CPR020 : 입찰 설명회 참석 업체
			CPR030 : 입찰 참가 신청 업체
			CPR040 : 개찰
			CPR050 : 입찰 결과
			CPR060 : 기술평가 계획
			CPR070 : 기술평가 결과
			CPR080 : 수의시담 작성
			CPR090 : 수의시담 결과
			CPR100 : 견적의뢰
			CPR110 : 계약 작성
			CPR120 : 변경 계약 작성
			CPR130 : 실적증명 신청
			CPR140 : 사전공고 작성
			CPR150 : 예가조서 작성
			CPR160 : 사전협의결과
			CPR170 : 계약심의결과
			CPR180 : 기술평가결과통보
		해당 코드를 아래 SANCTN_JOB_CD 에 넣어준다.
 --%>
<form id="elctrntFrm" method="POST" action="">
	<input type="hidden" name="P_PBLANC_NO" value="${vltrnPrvstlDetail.PRVSTL_NO}" />
	<input type="hidden" name="P_SANCTN_JOB_CD" value="CPR080"/>
	<input type="hidden" id="sanctnSttusCd" value="${elsaInfo.SANCTN_PRST_CD}"/>
</form>
<%-- 전자결재 기안 폼 --%>
<form id="scanctnFrm" method="POST" action="${apvrUrlList['ApvUrl']}"  target="_blank">
	<%-- 자동입력 --%>
	<input type="hidden" size="50" name="formid"><%-- 통합서식결재연동 --%>
	<input type="hidden" name="deptcode" value="${sessionScope.loginResult.ORG_CD}"> <%-- 부서코드 --%>
	<input type="hidden" name="empcode" value="${sessionScope.loginResult.EMP_NO}"> <%-- 사원번호  --%>
	<input type="hidden" size="50" name="miskey" value="${elsaInfo.SANCTN_CNTC_NO}"><%-- 업무시스템고유키 --%>
	<%-- 자동입력  END--%>
	
	<input type="hidden" name="title" value="${vltrnPrvstlDetail.PRVSTL_NM}"><%-- 제목 --%>
	<%-- 파일로 기안기에 넘겨줄때 사용 
	<input type=text name="bodyurl" value="">
	--%>
	<%-- 데이터로 기안기에 넘겨줄때 사용 --%>
	<input type="hidden" name="firstvalue" value="">
	<input type="hidden" name="returl"  value="${apvrUrlList['ApvRtnUrl']}">
	
	<%-- 
			첨부파일을 기안기로 넘겨줄 때 사용 
			첨부파일이 다중일 경우 (,)로 구분하여 넣어주며 첨부파일명과 참부파일URL의 순서를 맞춰주어야 한다.
	--%>
	<input type="hidden" name="P_ATCHMNFL_GROUP_NO" value="${bidAtchDoc.ATCHMNFL_GROUP_NO}"><%-- 첨부파일파일 그룹번호 --%>
	<input type="hidden" id="ATTNAMES" name="ATTNAMES" value=""><%-- 첨부파일명 --%>
	<input type="hidden" id="ATTURLS" name="ATTURLS" value=""><%--  첨부파일 URL --%>
	
</form>
<%-- 전자결재 확인 폼 --%>
<form id="scanctnCnfirmFrm" method="POST" action="${apvrUrlList['ApvViewUrl']}"  target="_blank">
	<input type="hidden" name="deptcode" value="${sessionScope.loginResult.ORG_CD}"><%-- 부서코드 --%>
	<input type="hidden" name="empcode" value="${sessionScope.loginResult.EMP_NO}"><%-- 사원번호  --%>
	<input type="hidden" name="docid" value="${elsaInfo.SANCTN_DRFT_NO}" >
</form>

<form id="fileViewFrm" method="POST" action = "">
	<input type="hidden" id="P_ATCHMNFL_GROUP_NO" name="P_ATCHMNFL_GROUP_NO" value="${vltrnPrvstlDetail.ATCHMNFL_GROUP_NO}">
</form>
<form id="downloadFrm" method="POST" action="">
	<input type="hidden" id="P_ATCHMNFL_SN" name="P_ATCHMNFL_SN" value="">
</form>

