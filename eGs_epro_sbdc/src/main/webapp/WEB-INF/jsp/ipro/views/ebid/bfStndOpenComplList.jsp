<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 계약설계 > 사전규격공개완료현황 목록
 *
 * <pre>
 * ebid
 *    |_bfStndOpenComplList.jsp
 * 
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %> 
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %> 

<script type="text/javascript" src="${jsPath}/ipro/views/ebid/bfStndOpenComplList.js"></script> 

<div class="sp_sec">
	<div class="nav_sec">
		<ul id="breadcrumbs">
			<li class="home">홈</li>
			<li>${myMenuList.bigMenuNm}</li>
			<li>${myMenuSubList.smallMenuNm}</li> 
		</ul>
	</div><!--// nav_sec -->
	<h3 class="sp_tit">사전규격요청완료현황</h3>

	<form name="searchFrm" id="searchFrm" method="post">
		<input type="hidden" name="resourceName" value="${comFn:nvl(param.resourceName, resourceName)}">
		<input type="hidden" name="resourceDesc" value="${param.resourceDesc}">
		<input type="hidden" id="P_PAGE_NO" name="P_PAGE_NO" value="${comFn:nvl(param.P_PAGE_NO, 1)}">
		<input type="hidden" id="P_PAGE_SIZE" name="P_PAGE_SIZE" value="${comFn:nvl(param.P_PAGE_SIZE, 10)}">
		
		<input type="hidden" id="P_AUTH_ID" name="P_AUTH_ID" value="${sessionScope.loginResult.AUTH_ID}" >
		<input type="hidden" id="P_SEARCH" name="P_SEARCH" value="">
			<legend>사전규격공개완료현황</legend>
			<div class="sp_cont">
				<div class="sch_box filter_sch">
					<dl class="first">
						<dt>요구번호</dt>
						<dd><input type="text" id="P_PCRQ_NO_S" name="P_PCRQ_NO_S" value="${param.P_PCRQ_NO_S}"  class="input w100p"  placeholder="요구번호" /></dd>
						<dt>사전규격요청번호</dt>
						<dd><input type="text" id="P_BFAN_NO_S" name="P_BFAN_NO_S" value="${param.P_BFAN_NO_S }" class="input w100p"  placeholder="사전규격요청번호" /></dd>
						<dt>본지사</dt>
						<dd>
							   <comTag:comCmcdCdValueComboBox id="P_ARA_DEPT_CD_S" name="P_ARA_DEPT_CD_S" selectKey="${comFn:nvl(param.P_ARA_DEPT_CD_S, P_ARA_DEPT_CD_S)}" cdId="ARA_DEPT_CD"  headerKey="" headerValue="전체"  className="select w100p"/>
						</dd>
					</dl>
					<dl>	
						<dt>계약구분</dt>
						<dd>
				            <comTag:comCmcdCdValueComboBox id="P_CONT_SECD_S" name="P_CONT_SECD_S" selectKey="${param.P_CONT_SECD_S}" cdId="CONT_SECD"  headerKey="" headerValue="전체" className="select w100p"/>
						</dd>
						<dt>계약명(사업명)</dt>
						<dd><input type="text" id="P_BFAN_NM_S" name="P_BFAN_NM_S" value="${param.P_BFAN_NM_S}" class="input w100p " placeholder="계약명(사업명)" /></dd>
						<dt>작성부서</dt>
						<dd>
	                    	<label for="rqrDeptNm" class="blind">작성부서</label>
	                    	<input type="text" id="detpNm" name="P_RQR_DEPT_NM_S" value="${comFn:nvl(param.P_RQR_DEPT_NM_S, P_RQR_DEPT_NM_S)}" class="input w100p"  placeholder="작성부서" style="width: 50%; background:#e0e0e0;"readonly="readonly"  />
	                    	<input type="hidden" id="deptId" name="P_RQR_DEPT_NO_S" value="${comFn:nvl(param.P_RQR_DEPT_NO_S, P_RQR_DEPT_NO_S)}"> 
	                    	<c:if test="${sessionScope.loginResult.AUTH_ID eq '1' or sessionScope.loginResult.AUTH_ID eq '4' or sessionScope.loginResult.AUTH_ID eq '5'}">
		                    	<button type="button" class="btn ty03" id="searchDeptBtn">검색</button>
		                    	<button type="button" class="btn ty04" id="deptDelBtn">삭제</button>
		                    </c:if>
						</dd>
					</dl>
					<dl>		
						<dt>진행상태</dt>
						<dd>
							<comTag:comCmcdCdValueComboBox id="P_BFAN_PSCD_S" name="P_BFAN_PSCD_S" selectKey="${param.P_BFAN_PSCD_S}" cdId="BFAN_PSCD" cond2="Y"  headerValue="전체" className="select w100p"/>
						</dd>				
						<dt>요청일자</dt>
						<dd class="date">
							<span class="date_box"><input type="text" id="P_RQR_BEGIN_DT_S" name="P_RQR_BEGIN_DT_S" class="input datepicker start-date " placeholder="시작일" date value="${comFn:formatDate(P_RQR_BEGIN_DT_S,'yyyyMMdd','yyyy-MM-dd')}"></span> <span class="date_center">~</span> <span class="date_box"><input type="text" id="P_RQR_END_DT_S" name="P_RQR_END_DT_S" class="input w100p datepicker end-date" placeholder="종료일" value="${comFn:formatDate(P_RQR_END_DT_S,'yyyyMMdd','yyyy-MM-dd')}" date></span>
						</dd>
					</dl>
				</div> <!--// view_area E -->
				<button type="submit" class="btn ty03 btn_sch" id="searchBtn">조회</button>
			
				<div class="list_top">
					<p class="total">총 <span>${comFn:nvl(bfStndOpenComplListTotCnt, 0)}</span>건</p>
					<p class="total" style="float: right;"> 
						<button type="button" class="btn btn_down" id="excelBtn">엑셀 다운로드</button>
					</p>
				</div><!--// list_top -->
				<div style="overflow-x: scroll; overflow-y:hidden" >
					<table class="list_tb">
						<caption>사전규격공개완료현황 목록</caption>
	               		<colgroup>
	           				<col width="50px;"/>
		           			<col width="120px;"/>
		           			<col width="100px;"/>
		           			<col width="130px;"/>
		           			<col width="130px;"/>
		                   	<col width="400px;"/>
		                   	<col width="120px;"/>
		                   	<col width="150px;"/>
		                   	<col width="100px;"/>
		                   	<col width="100px;"/>
		                   	<col width="100px;"/>
		                   	<col width="120px;"/>
		                   	<col width="120px;"/>
		            	</colgroup>
		            	<thead>
					    	<tr>
					    		<th scope="col">순번</th>
					    		<th scope="col">진행상태</th>
					    		<th scope="col">계약구분</th>
					    		<th scope="col">요구번호</th>
					    		<th scope="col">사전규격요청번호</th>
					    		<th scope="col">계약명(사업명)</th>
					    		<th scope="col">사업예산</th>
					    		<th scope="col">낙찰방법</th>
					    		<th scope="col">공고시작일자</th>
					    		<th scope="col">공고종료일자</th>
					    		<th scope="col">요청일자</th>
					    		<th scope="col">작성부서</th>
					    		<th scope="col">작성자</th>
					        </tr>
				        </thead>
				        <tbody>
							<c:if test="${comFn:nvl(bfStndOpenComplListTotCnt, 0) == 0}">
								<tr class="row">
									<td colspan="14" align="center">검색 조건에 해당하는 데이터가 존재하지 않습니다.</td>
								</tr>
							</c:if>
							<c:if test="${bfStndOpenComplListTotCnt > 0}">
								<c:forEach var="data" items="${bfStndOpenComplList}" varStatus="status" >
									<tr class="row" onclick="detailInqire('${data.BFAN_NO}');" style="cursor: pointer;">
										<td class="no">${data.RNUM}</td>
										<td>${comFn:nvl(data.BFAN_PSCD_NM,"작성대기")}</td>
										<td>${data.CONT_SECD_NM}</td>
										<td class="no">${data.PCRQ_NO}</td>
										<td class="no">${data.BFAN_NO}</td>
										<td class="tit" title="${data.BFAN_NM}"><a href="#">${comFn:toShorten(data.BFAN_NM, 50)}</a></td>
										<td class="txtr">${comFn:formatMoney(data.BSNS_BDG_AMT)}</td>
										<td >${data.SBID_MTCD_NM}</td>
										<td class="date">${comFn:formatDate(data.BFAN_STDE,'yyyyMMdd','yyyy-MM-dd')}</td>
										<td class="date">${comFn:formatDate(data.BFAN_ENDE,'yyyyMMdd','yyyy-MM-dd')}</td>
										<td class="date">${comFn:formatDate(data.RQR_DE,'yyyyMMdd','yyyy-MM-dd')}</td>
										<td>${data.RQR_DEPT_NM}</td>
										<td>${data.RQR_CHRGR_NM}</td>
									</tr>
								</c:forEach>
							</c:if>
						</tbody>
			    	</table>
			    </div>
				<div class="paging">
					<comTag:pagingIpro totalCount="${comFn:nvl(bfStndOpenComplListTotCnt,0)}" pageNo="${comFn:nvl(param.P_PAGE_NO, 1)}" pageSize="${comFn:nvl(param.P_PAGE_SIZE, 10)}" clickPage="pageObj.clickPage"/>
				</div> <!--// list_bottom E -->
			</div> <!--// sp_cont -->
	</form>
</div><!--// sp_sec -->		


<!-- REGIST FORM -->
<form id="detailFrm" method="POST">
	<input type="hidden" name="resourceName" value="${comFn:nvl(param.resourceName, resourceName)}">
	<input type="hidden" name="resourceDesc" value="${param.resourceDesc}">
	<input type="hidden" name="P_BFAN_NO" >
</form>

<!-- POPUP FORM -->
<form id="popupFrm" method="POST">
	<input type="hidden" name="resourceName" value="${comFn:nvl(param.resourceName, resourceName)}">
	<input type="hidden" name="resourceDesc" value="${param.resourceDesc}">
	<input type="hidden" id="P_AUTH_ID" name="P_AUTH_ID" value="${sessionScope.loginResult.AUTH_ID}" >
	<input type="hidden" id="P_ARA_DEPT_CD" name="P_ARA_DEPT_CD" >
	<!-- <input type="hidden" name="setMulti" value="Y"> -->
</form>