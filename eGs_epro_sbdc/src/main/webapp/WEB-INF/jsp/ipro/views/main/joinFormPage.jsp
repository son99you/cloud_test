<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 메인화면 > 회원가입 폼
 *
 * <pre>
 * main 
 *    |_ joinFormPage.jsp
 * 
 * </pre>
 * @date : 2015. 06. 08. 오전 10:10:32
 * @version : 1.0
 * @author : 은우소프트 전상훈
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %> 
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %> 

<script type="text/javascript" src="${jsPath}/opro/views/main/joinFormPage.js"></script>  

<script src="${contextPath}/vestsign/vestsign.js"></script>
<script src="${contextPath}/vestsign/library/json3.min.js"></script>
<script src="${contextPath}/vestsign/vestsign_ew.js"></script>

<div class="contents_wrap">
	<ul class="step_wrap"></ul>
	<div class="tit_wrap">
		<h3 class="tit">회원가입</h3>
	</div>
	
	<form id="registFrm" name="registFrm" method="POST" enctype="multipart/form-data">
		<input type="hidden" id="resourceName" name="resourceName" value="${param.resourceName}">
		<input type="hidden" id="resourceDesc" name="resourceDesc" value="${param.resourceDesc}">
		
		<input type="hidden" id="P_BIZRNO_CHECK" name="P_BIZRNO_CHECK" value="${P_BIZRNO_CHECK}">
		
		<input type="hidden" name="P_LOGIN_ID" id="P_LOGIN_ID" >
		<input type="hidden" name="loginData" id="loginData" value='' />
		<input type="hidden" name="strCert" id="strCert" value='' />
		
		<div class="view_wrap typeB">
			<div class="tit_area">
				<h4 class="tit">업체정보</h4>
			</div>
			<div class="view_area">
				<table>
					<caption>업체정보</caption>
					<colgroup>
						<col width="15%">
						<col width="35%">
						<col width="15%">
						<col width="35%">
					</colgroup>
					<tbody>
						<tr> 
							<th class="bullet_orange">사업자등록번호</th>
							<td>
								<input type="text" id="P_BIZRNO1" name="P_BIZRNO1" class="w80" maxlength="3" numeric>  
								- <input type="text" id="P_BIZRNO2" name="P_BIZRNO2" class="w50" maxlength="2" numeric> 
								- <input type="text" id="P_BIZRNO3" name="P_BIZRNO3" class="w120" maxlength="5" numeric>
								
								<button type="button" class="btn btn_m btn_orange" id="birnoCheckBtn">중복확인</button>
							</td>
							<th>법인등록번호</th>
							<td><input type="text" id="P_CORP_REG_NO" name="P_CORP_REG_NO"></td>
						</tr>
						<tr> 
							<th class="bullet_orange">업체명</th>
							<td><input type="text" id="P_VEND_NM" name="P_VEND_NM"></td>
							<th class="bullet_orange">업체영문명</th>
							<td><input type="text" id="P_VEND_ENM" name="P_VEND_ENM"></td>
						</tr>
						<tr> 
							<th class="bullet_orange">대표자명</th>
							<td><input type="text" id="P_RPRS_NM" name="P_RPRS_NM"></td>
							<th>대표자영문명</th>
							<td><input type="text" id="P_RPRS_ENM" name="P_RPRS_ENM"></td>
						</tr>
						<tr> 
							<th class="bullet_orange">이메일주소</th>
							<td colspan="3"><input type="text" id="P_EMAL_ADDR" name="P_EMAL_ADDR"></td>
						</tr>
						<tr> 
							<th>공급업체구분</th>
							<td>
								<comTag:comCmcdCdValueComboBox name="P_SPLY_VEND_SECD" cdId="SPLY_VEND_SECD" headerValue="전체" width="180"/>
							</td>
							<th class="bullet_orange">기업형태</th>
							<td>
								<comTag:comCmcdCdValueComboBox name="P_CORP_TPCD" cdId="CORP_TPCD" headerValue="전체" width="180"/>
							</td>
						</tr>
						<tr> 
							<th class="bullet_orange">업태</th>
							<td><input type="text" id="P_BCNM" name="P_BCNM"></td>
							<th class="bullet_orange">종목</th>
							<td><input type="text" id="P_BTNM" name="P_BTNM"></td>
						</tr>
						<tr> 
							<th class="bullet_orange">전화번호</th>
							<td><input type="text" id="P_TEL_NO" name="P_TEL_NO"></td>
							<th class="bullet_orange">팩스번호</th>
							<td><input type="text" id="P_FX_NO" name="P_FX_NO"></td>
						</tr>
						<tr> 
							<th>홈페이지주소</th>
							<td colspan="3"><input type="text" id="P_HMPG_ADDR" name="P_HMPG_ADDR"></td>
						</tr>
						<tr> 
							<th class="bullet_orange">주소</th>
							<td colspan="3">
								<input type="text" id="P_ZIP" name="P_ZIP" class="w120" max="6" readonly="readonly">
								<button type="button" class="btn btn_s2 btn_sch" id="zipBtn">우편번호</button>
								<input type="text" id="P_ADDR_NM" name="P_ADDR_NM">
								<input type="text" id="P_ADDR_DENM" name="P_ADDR_DENM">
							</td>
						</tr>
						<tr> 
							<th>영문주소</th>
							<td colspan="3"><input type="text" id="P_ADDR_ENM" name="P_ADDR_ENM"></td>
						</tr>
						<tr> 
							<th>자본금액</th>
							<td><input type="text" id="P_CPTL_AMT" name="P_CPTL_AMT" class="tr w170" money></td>
							<th>종업원수</th>
							<td><input type="text" id="P_MMBR_CNT" name="P_MMBR_CNT" numeric></td>
						</tr>
						<tr> 
							<th class="bullet_orange">설립일자</th>
							<td>	
								<div class="calendar_wrap w_120">
									<label for="P_ESTB_DE">
										<input type="text" id="P_ESTB_DE" name="P_ESTB_DE" class="w120" date>
									</label>
								</div>
							</td>
							<th></th>
							<td></td>
						</tr>
						<tr> 
							<th class="bullet_orange">비밀번호</th>
							<td><input type="password" id="P_PWD" name="P_PWD"></td>
							<th class="bullet_orange">비밀번호 확인</th>
							<td><input type="password" id="P_PWD_CNFM" name="P_PWD_CNFM"></td>
						</tr>
						<tr> 
							<th>특기사항</th>
							<td colspan="3"><textarea id="P_SPCL_ITEM" name="P_SPCL_ITEM" rows="3" cols="49" maxlength="4000" style="width: 99%"></textarea></td>
						</tr>
					</tbody>
				</table>
			</div>

			
			<div class="tit_area">
				<h4 class="tit">담당자정보</h4>
			</div>
			<div class="view_area">
				<table>
					<caption>담당자정보</caption>
					<colgroup>
						<col width="15%">
						<col width="35%">
						<col width="15%">
						<col width="35%">
					</colgroup>
					<tbody>
						<tr> 
							<th class="bullet_orange">담당자명</th>
							<td>
								<input type="text" id="P_USR_NM" name="P_USR_NM">
							</td>
							<th>직급</th>
							<td><input type="text" id="P_ODNM" name="P_ODNM"></td>
						</tr>
						<tr> 
							<th>부서</th>
							<td><input type="text" id="P_DEPT_NM" name="P_DEPT_NM"></td>
							<th>이메일</th>
							<td><input type="text" id="P_EMAL_ADDR_USER" name="P_EMAL_ADDR_USER"></td>
						</tr>
						<tr> 
							<th class="bullet_orange">전화번호</th>
							<td><input type="text" id="P_TEL_NO" name="P_TEL_NO_USER"></td>
							<th class="bullet_orange">휴대전화번호</th>
							<td><input type="text" id="P_CP_NO" name="P_CP_NO"></td>
						</tr>
					</tbody>
				</table>
			</div>
			
			
			<div class="tit_area">
				<h4 class="tit">계좌정보</h4>
				<span style="color: red; float: right; margin-right: 20px;">※ 거래은행계좌입금의뢰서 양식은 공지사항에서 다운받으시길 바랍니다.</span>
			</div>
			
			<div class="view_area">
				<table id="accTb">
					<caption>계좌정보</caption>
					<colgroup>
						<col width="15%">
						<col width="25%">
						<col width="12%">
						<col width="15%">
						<col width="*">
					</colgroup>
					<thead>
						<tr>
							<th class="txtc">은행</th>
							<th class="txtc">계좌번호</th>
							<th class="txtc">예금주</th>
							<th class="txtc" colspan="2">첨부파일(필수)</th>
						</tr>
					</thead>
						<tbody id="accHiddTbdy">
							<tr>
								<td class="txtc" rowspan="2"><comTag:comCmcdCdValueComboBox name="P_BKCD" cdId="BKCD" headerValue="전체" width="150"/></td>
								<td rowspan="2"><input type="text" id="P_ACNO" name="P_ACNO"></td>
								<td rowspan="2"><input type="text" id="P_DPSO" name="P_DPSO"></td>
								<th>
									<input type="text" name="P_ACCT_FILE_DOC_NM" value="통장사본" readonly="readonly">
									<input type="hidden" name="P_ACCT_FILE_DOC_SECD" value="ACCT01">
								</th>
								<td><input type="file" name="P_ACCT_FILE"></td>
							</tr>
							<tr>
								<th>
									<input type="text" name="P_ACCT_FILE_DOC_NM" value="거래은행계좌입금의뢰서" readonly="readonly">
									<input type="hidden" name="P_ACCT_FILE_DOC_SECD" value="ACCT02">
								</th>
								<td><input type="file" name="P_ACCT_FILE"></td>
							</tr>
						</tbody>
					</tbody>
				</table>
			</div>
			
			<%-- <div class="tit_area">
				<h4 class="tit">계좌정보</h4>
				<div class="btn_right">
		            <button type="button" class="btn btn_s2 btn_c2" id="accAddBtn">추가</button>
		            <button type="button" class="btn btn_s2 btn_c2" id="accDelBtn">삭제</button>
		        </div>
			</div>
			<div class="view_area">
				<table id="accTb">
					<caption>계좌정보</caption>
					<colgroup>
						<col width="5%">
						<col width="15%">
						<col width="20%">
						<col width="15%">
						<col width="*">
					</colgroup>
					<thead>
						<tr>
							<th class="txtc"></th>
							<th class="txtc">은행</th>
							<th class="txtc">계좌번호</th>
							<th class="txtc">예금주</th>
							<th class="txtc">통장사본(필수)</th>
						</tr>
					</thead>
					<tbody id="accHiddTbdy" style="display: none;">
						<tr>
							<td class="txtc">
								<input type="checkbox" name="accCbk">
							</td>
							<td class="txtc"><comTag:comCmcdCdValueComboBox name="P_BKCD" cdId="BKCD" headerValue="전체" width="120"/></td>
							<td><input type="text" name="P_ACNO" class="acno"><input type="hidden" name="P_ACCT_FILE_DOC_SECD" value="ACCT" class="fileDocSecd1"></td>
							<td><input type="text" name="P_DPSO" class="dpso"><input type="hidden" name="P_ACNO_NEW" class="acctTskVkey1"></td>
							<td><input type="file" name=""></td>
						</tr>
					</tbody>
					
					<tbody id="accShowTbdy">
					</tbody>
				</table>
			</div> --%>
			
		    <div class="tit_area">
	           	<h4 class="tit">기업정보</h4>
				<div class="btn_right">
		            <button type="button" class="btn btn_s2 btn_c2" id="acqsAddBtn">추가</button>
		            <button type="button" class="btn btn_s2 btn_c2" id="acqsDelBtn">삭제</button>
		        </div>
			</div>
			<div class="view_area">
				<table id="acqsTb">
					<caption>기업정보</caption>
					<colgroup>
						<col width="5%">
						<col width="15%">
						<col width="40%">
						<col width="*">
					</colgroup>
					<thead>
						<tr>
							<th class="txtc"></th>
							<th class="txtc">명칭</th>
							<th class="txtc">유효기간</th>
							<th class="txtc">첨부파일</th>
						</tr>
					</thead>
					<tbody id="acqsShowTbdy">
					</tbody>
				</table>
			</div>
			
			
			<div class="tit_area">
				<h4 class="tit">주요취급품목</h4>
				<div class="btn_right">
		            <button type="button" class="btn btn_s2 btn_c2" id="itemAddBtn">추가</button>
		            <button type="button" class="btn btn_s2 btn_c2" id="itemDelBtn">삭제</button>
		        </div>
		        <span style="color: red; float: right; margin-right: 20px;">※ 선택된 주요취급품목에 관련된 입찰공고시 안내메일이 발송됩니다.</span>
			</div>
			<div class="view_area">
				<table id="itemTb">
					<caption>주요취급품목</caption>
					<colgroup>
						<col width="5%">
						<col width="30%">
						<col width="*">
					</colgroup>
					<thead>
						<tr>
							<th class="txtc"></th>
							<th class="txtc">품목번호</th>
							<th class="txtc">품목명</th>
						</tr>
					</thead>
					<tbody id="itemShowTbdy">
					</tbody>
				</table>
			</div>


	        <div class="tit_area">
            	<h4 class="tit">필수첨부파일</h4>
			</div>
            <div class="view_area">
				<table class="table">
					<caption>필수첨부파일</caption>
					<colgroup>
						<col style="width: 30%;">
		                <col style="width: auto;">
		                <col style="width: 11%;">
					</colgroup>
					<thead>
						<tr>
							<th scope="col" class="txtc">문서구분명</th>
							<th scope="col" class="txtc">파일</th>
							<th scope="col"></th>
						</tr>
					</thead>
					<tbody id="redFileShowTbdy">
						<c:set var="DC01_YN" value="N" />
						<c:forEach items="${vendAtchDocListRed }" var="data" varStatus="state">
							<c:if test="${data.FILE_DOC_SECD eq 'DC01'}">
								<c:set var="DC01_YN" value="Y" />
							</c:if>
	                   		<tr class="${data.FILE_DOC_SECD}">
								<td>
									${data.FILE_DOC_NM }
									<input type="hidden" name="P_RED_FILE_DOC_NM" value="${data.FILE_DOC_NM }">
									<input type="hidden" name="P_RED_FILE_DOC_SECD" value="${data.FILE_DOC_SECD }" disabled="disabled">
									<input type="hidden" id="${data.FILE_DOC_SECD}" value="${data.FILE_SN}">
								</td>
								<td>
									<a href="javascript:pageObj.download('${data.FILE_SN}','${data.FILE_GRP_NO }');" class="redFileView">${data.SYS_FILE_NM }</a>
									<input type="file" name="P_RED_VEND_FILE" class="redFile" style="display: none;" disabled="disabled">
								</td>
								<td align="center">
									<button type="button" class="btn btn_s btn_sch redFileViewBtn" onclick="rowRedFileChng(this,'${data.FILE_SN}')">변경</button>
									<button type="button" class="btn btn_s btn_sch redDelBtn" onclick="rowRedFileCncl(this,'${data.FILE_SN}')" style="display: none;">취소</button>
									<button type="button" class="btn btn_s btn_sch redFileBtn" onclick="rowRedFileChngCncl(this,'${data.FILE_SN}')" style="display: none;">취소</button>
								</td>
			            	</tr>
                    	</c:forEach>
                    	<c:if test="${DC01_YN eq 'N' }">
                    		<tr>
								<td>
									사업자등록증
									<input type="hidden" name="P_RED_FILE_DOC_SECD" value="DC01" class='fileView'>
									<input type="hidden" name="P_RED_FILE_DOC_NM" value="사업자등록증">
								</td>
								<td>
									<input type="file" name="P_RED_VEND_FILE" class='fileView'>
								</td>
								<td align="center">
									<button type="button" class="btn btn_s btn_sch fileView" onclick="rowRegDel(this)">삭제</button>
									<button type="button" class="btn btn_s btn_sch fileDel" onclick="rowCnclDel(this)" style="display: none;">취소</button>
								</td>
			            	</tr>
                    	</c:if>
					</tbody>
				</table>
			</div>
			
			
			
			<div class="tit_area">
            	<h4 class="tit">기타첨부파일</h4>
            	<div class="btn_right">
		            <button type="button" class="btn btn_s2 btn_c2" id="vendFileBtn">추가</button>
		        </div>
			</div>
            <div class="view_area">
				<table class="table">
					<caption>기타첨부파일</caption>
					<colgroup>
						<col style="width: 30%;">
		                <col style="width: auto;">
		                <col style="width: 11%;">
					</colgroup>
					<thead>
						<tr>
							<th scope="col" class="txtc">문서구분명</th>
							<th scope="col" class="txtc">파일</th>
							<th scope="col"></th>
						</tr>
					</thead>
					<tbody id="fileHideTbdy" style="display: none;">
						<tr>
							<td><input type="text" name=""><input type="hidden" name="P_ETC_FILE_DOC_SECD" value="DC99"></td>
							<td><input type="file" name=""></td>
							<td align="center"><button type="button" class="btn btn_s btn_sch" onclick="rowDel(this)">삭제</button></td>
		            	</tr>
					</tbody>
					<tbody id="fileShowTbdy">
						<c:if test="${not empty vendAtchDocList }">
	                   		<c:forEach items="${vendAtchDocList }" var="data" varStatus="state">
		                   		<tr>
									<td>
										${data.FILE_DOC_NM }
										<input type="hidden" name="P_ORG_FILE_DOC_NM" value="${data.FILE_DOC_NM }" class="fileView">
										<input type="hidden" name="P_FILE_SN" value="${data.FILE_SN }" class="fileView">
									</td>
									<td><a href="javascript:pageObj.download('${data.FILE_SN}','${data.FILE_GRP_NO }');" class="attfile">${data.SYS_FILE_NM }</a></td>
									<td align="center">
										<button type="button" class="btn btn_s btn_sch fileViewBtn" onclick="rowFileDel(this,'${data.FILE_SN}')">삭제</button>
										<button type="button" class="btn btn_s btn_sch fileDelBtn" onclick="rowFileCncl(this,'${data.FILE_SN}')" style="display: none;">취소</button>
									</td>
				            	</tr>
	                    	</c:forEach>  
	                    </c:if>
					</tbody>
				</table>
			</div>
			
			<div class="btn_wrap">
	           	<button type="button" class="btn btn_m btn_orange" id="saveBtn">회원가입</button>
			</div>
			
		</div>
	</form>

</div> <!--// content E-->

<%-- DETAIL FORM --%>
<form id="detailFrm" method="POST" > 
	<input type="hidden" name="resourceName" value="${ param.resourceName }" >
	<input type="hidden" name="resourceDesc" value="${param.resourceDesc}">
</form> 

<form id="popupFrm" method="POST" > 
	<input type="hidden" name="resourceName" value="${ param.resourceName }" >
	<input type="hidden" name="resourceDesc" value="${param.resourceDesc}">
</form>

<!-- ITEM POPUP FORM -->
<form id="itemPopupFrm" method="POST">
	<input type="hidden" name="resourceName" value="${param.resourceName}">
	<input type="hidden" name="resourceDesc" value="${param.resourceDesc}">
	<input type="hidden" id="searchGbnId" name="searchGbnId">
	<input type="hidden" id="setMulti" name="setMulti">
</form>

<%-- ZIP POPUP FORM --%>
<form id="zipPopupFrm" method="POST">
	<input type="hidden" name="resourceName" value="${param.resourceName}">
	<input type="hidden" name="resourceDesc" value="${param.resourceDesc}">
</form>

<form id="selectRefFrm" method="POST" style="display: none;">
	<comTag:comCmcdCdValueComboBox name="P_DATA_CD" cdId="DATA_CD" headerValue="전체" width="120"/>
</form>