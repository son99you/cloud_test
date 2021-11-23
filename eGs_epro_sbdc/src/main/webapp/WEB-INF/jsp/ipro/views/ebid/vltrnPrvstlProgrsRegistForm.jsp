<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 수의시담 > 수의시담 상세 - 시담진행
 *
 * <pre>
 *vlpr 
 *    |_ oepVlprVltrnPrvstlProgrsDetail.jsp
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

<script type="text/javascript" src="${jsPath}/opro/views/ebid/vltrnPrvstlProgrsDetail.js"></script> 
<script src="/nxts/js/nxts/nxts.min.js"></script>
<script src="/nxts/js/nxts/nxtspki_config.js"></script>
<script src="/nxts/js/nxts/nxtspki.js"></script>

<ul class="step_wrap">
	<li><a href="#">전자시담</a></li>
	<li><a href="#">시담요청현황</a></li>
</ul> <!--// step_wrap E -->
<div class="tit_wrap">
	<h3 class="tit">시담가 등록</h3>
</div> <!--// tit_wrap E -->

<div class="view_wrap typeB">
	<div class="tit_area">
		<h4 class="tit">기본정보</h4>
	</div>
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
  	<div class="tit_area">
  		<h4 class="tit">시담기본정보</h4>
  	</div>
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
	                    <td colspan="3">노트북 구매&nbsp;</td>
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
       		<div class="tit_area"><h4 class="tit">시담요청정보</h4></div>
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
								2018-09-01 10:00
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
	             <div class="tit_area">
	     				<h4 class="tit">시담업체</h4>
		        </div>
			        <div class="view_area">
		            <table class="tableList" summary="시담업체 입니다." id="">
		                <caption>시담업체내역</caption>
		                <colgroup>
							<col width="20%">
							<col width="20%">
							<col width="20%">
							<col width="20%">
							<col width="20%">
		                </colgroup>
		                <thead>
			                <tr><!--  class="line" -->
			                    <th style="text-align: center;" scope="col">사업자번호</th>
			                    <th style="text-align: center;" scope="col">업체명</th>
			                    <th style="text-align: center;" scope="col">담당자명</th>
			                    <th style="text-align: center;" scope="col">이메일</th>
			                    <th style="text-align: center;" scope="col">전화번호</th>
			                </tr>
		                </thead>
		                <tbody id="">
								<tr class="row">
									<td style="text-align: center;">119-86-02801</td>
									<td style="text-align: left;">(주)은우소프트</td>
									<td style="text-align: center;">홍찬일</td>
									<td style="text-align: left;">hci1135@eunwoosoft.com</td>
									<td style="text-align: center;">02-547-8548</td>
								</tr>
		                </tbody>
		            </table>
	            	</div>
	            <div class="tit_area"><h4 class="tit">시담첨부문서</h4></div>
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
			                    			<button type="button" class="btn btn_02 btn_sch" style="display: inline-block; margin-top: 3px;" onclick="rowDel(this)">삭제</button>
			                    		</span>
			                    	</div>
			                    	<div id="fileViewDiv" style="width: 680px; line-height: 30px;">
				            			<div style="height: 30px;"> 
			                   				<a href="#" class="attfile">시담문서.hwp</a>
			                    		</div>
			                    	</div>
			                    	<div id="fileDiv" style="width: 680px; line-height: 30px;">
			                    	</div>
			                    </td>	
				             </tr>
					    </table>
					</div>
					
       				<div class="tit_area">
			        	<h4 class="tit" style="clear: both;">시담물품내역</h4>
			       	</div>
					<div class="view_area" style="margin-bottom: 30px;">
			            <table summary="입찰품목정보 수정 입니다.">
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
					
					<div class="tit_area">
			        	<h4 class="tit" style="clear: both;">시담등록정보</h4>
			       	</div>
					<div class="view_area">
			            <table summary="입찰품목정보 수정 입니다.">
			                <caption>입찰품목정보</caption>
			                <colgroup>
			                    <col width="15%">
			                    <col width="35%">
			                    <col width="15%">
			                    <col width="35%">
			                </colgroup>
			                <tr>
			                	<th>입찰금액</th>
			                	<td><input type="text"></td>
			                	<th>통화단위</th>
			                	<td>
			                		<select>
			                			<option>선택</option>
			                			<option>won</option>
			                		</select>
			                	</td>
			                </tr>
			                <tr>
			                	<th>담당자</th>
			                	<td><input type="text"></td>
			                	<th>담당자전화번호</th>
			                	<td><input type="text"></td>
			                </tr>
			                <tr>
			                	<th>담당자핸드폰</th>
			                	<td><input type="text"></td>
			                	<th>담당자이메일</th>
			                	<td><input type="text"></td>
			                </tr>
			                <tr>
			                	<th scope="row" class="vtop">첨부파일</th>
								<td class="vtop" colspan="3">
								<span class="stD">
			                    	<button type="button"  class="btn btn_s btn_sch" id="fileBtn" style="float: right;">추가</button> 
			                    </span>
			                    	<div id="fileRow" style="display: none; height: 30px;">
			                    		<input type="file" name="" style="width: 80%;height: 24px;" >
					                   	<span class="stD"> 
			                    			<button type="button" class="btn btn_s btn_sch" style="display: inline-block; margin-top: 3px;" onclick="rowDel(this)">삭제</button>
			                    		</span>
			                    	</div>
			                    	<div id="fileDiv" style="width: 680px; line-height: 30px;">
			                    	</div>
			                    </td>	
				             </tr>
			            </table>
					</div>
					<div>
						□ 본인은 위의 번호로 공고한 귀원의 입찰에 참가하고자 입찰유의서 및 입찰공고사항과 계약조건 등 입찰 및 계약에 필요한 
    						모든 사항을 숙지하고 이를 승낙하며 입찰서를 제출합니다.
					</div>
	<div class="btn_wrap view_btn">
		<button type="button" class="btn btn_m btn_orange" id="listBtn">제출</button>
		<button type="button" class="btn btn_m btn_del" id="listBtn">목록</button>
	</div> <!--// btn_wrap E -->
					
</div>
     
   	<form id="listFrm" method="POST">
   		<input type="hidden" id="resourceName" name="resourceName" value="${param.resourceName}">
   		<input type="hidden" id="P_PRCURE_BSNS_SE_CD" name="P_PRCURE_BSNS_SE_CD" value="${vltrnPrvstlDetail.PRCURE_BSNS_SE_CD }">
	</form>
	
	<form id="refreshFrm" method="POST">
		<input type="hidden" name="P_PRVSTL_NO" value="${vltrnPrvstlDetail.PRVSTL_NO }">
		<input type="hidden" name="P_TYPE_SE" value="refresh">
		<input type="hidden" id="resourceName" name="resourceName" value="${param.resourceName}">
	</form>
	<form id="ntatRefreshFrm" method="POST">
		<input type="hidden" name="P_PRVSTL_NO" value="${vltrnPrvstlDetail.PRVSTL_NO }">
		<input type="hidden" name="P_TYPE_SE" value="refresh">
		<input type="hidden" id="resourceName" name="resourceName" value="${param.resourceName}">
	</form>
	
	
	<form id="prvstlRequstFrm" method="POST">
		<input type="hidden" name="P_PRVSTL_NO" value="${vltrnPrvstlDetail.PRVSTL_NO }">
		<input type="hidden" name="P_PRCURE_REQEST_NO" value="${vltrnPrvstlDetail.PRCURE_REQEST_NO }">
		<input type="hidden" name="P_PRVSTL_PRST_CD" value="PO50">
		<input type="hidden" name="P_SCRIN_SE" value="detail">
		<input type="hidden" id="resourceName" name="resourceName" value="${param.resourceName}">
	</form>
	<form id="fileViewFrm" method="POST" action = "">
		<input type="hidden" id="P_ATCHMNFL_GROUP_NO" name="P_ATCHMNFL_GROUP_NO" value="${vltrnPrvstlDetail.ATCHMNFL_GROUP_NO}">
	</form>
<form id="downloadFrm" method="POST" action="">
   		<input type="hidden" id="P_ATCHMNFL_SN" name="P_ATCHMNFL_SN" value="">
   	</form>
