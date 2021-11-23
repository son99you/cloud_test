<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 	입찰관리 > 나의입찰진행 목록
 *
 * <pre>
 * ebid 
 *   	 |_ myBidPblancList.jsp
 * 
 * </pre>
 * @date : 2017. 06. 19.
 * @version : 1.0
 * @author : 은우소프트 이주연
--%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %>

<script type="text/javascript" src="${jsPath}/opro/views/ebid/myBidPblancList.js"></script>  

<div class="contents_wrap">
	<ul class="step_wrap">
		<li><a href="#">${myMenuList.bigMenuNm}</a></li>
		<li><a href="#">${myMenuSubList.smallMenuNm}</a></li>
	</ul>
	<div class="tit_wrap">
		<h3 class="tit">나의입찰진행</h3>
	</div>

	<form id="searchFrm" class="search_form" method="POST" action="${contextPath}/elbi/myBidPblancList.do">
		<input type="hidden" name="resourceName" value="${param.resourceName}">
		<input type="hidden" id="P_PAGE_NO" name="P_PAGE_NO" value="${comFn:nvl(param.P_PAGE_NO, 1)}">
		<input type="hidden" id="P_PAGE_SIZE" name="P_PAGE_SIZE" value="${comFn:nvl(param.P_PAGE_SIZE, 10)}">
			
		<fieldset>
	        <div class="view_wrap typeA">	
	        	<div class="view_area">
					<table>
						<colgroup>
							<col style="width: 15%;">
							<col style="width: 35%;">
							<col style="width: 15%;">
							<col style="width: 35%;">
						</colgroup>
						<tbody>
							<tr>
								<th scope="row">공고명</th>
								<td>
	               					<input type="text" id="P_BID_NM_S" name="P_BID_NM_S" value="${param.P_BID_NM_S}" >
								</td>
								<th>공고번호</th>
								<td>
									<input type="text" id="P_ANNC_NO_S" name="P_ANNC_NO_S" value="${param.P_ANNC_NO_S}" class="w180">
								</td>
							</tr>
							<tr>
								<th scope="row">계약구분</th>
								<td>
					            	<comTag:comCmcdCdValueComboBox name="P_CONT_SECD_S" cdId="CONT_SECD" selectKey="${param.P_CONT_SECD_S }" headerValue="전체" width="180" />
								</td>
								<th>진행상태</th>
								<td>
									<comTag:comCmcdCdValueComboBox name="P_BID_PSCD_S" cdId="BID_PSCD" cond3="MY" selectKey="${param.P_BID_PSCD_S }" headerValue="전체" width="180" />
								</td>
							</tr>
							<tr>
								<th scope="row"><label for="division">공고일자</label></th>
								<td>
									<div class="calendar_box">
					                    <label for="P_ANNC_STDT_S" class="blind">공고일자 시작일</label>
					                    <input type="text" class="datepicker1 w120" id="P_ANNC_STDT_S" name="P_ANNC_STDT_S" value="${param.P_ANNC_STDT_S}" date >
					                	<span class="wave"> ~ </span>
					                    <label for="P_ANNC_ENDT_S" class="blind">공고일자 마감일</label>
					                    <input type="text" class="datepicker2 w120" id="P_ANNC_ENDT_S" name="P_ANNC_ENDT_S" value="${param.P_ANNC_ENDT_S}"  date >
					                </div>
								</td>
								<th scope="row"></th>
								<td>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
			<div class="btn_wrap mt10">
				<button type="button" class="btn btn_m btn_c2" id="searchBtn">조회</button>
			</div> <!--// btn_wrap E -->
					
		</fieldset>
	</form>		
		
	<div class="list_wrap mt30" id="contentWrap">
		<div class="list_top">
			<p class="total">총 <span>${comFn:nvl(myBidPblancListTotcnt, 0)}</span>건</p>		
		</div> <!--// list_top E -->
		<div class="list_conts">		
			<table>
				<caption>입찰작성현황 목록</caption>
           		<colgroup>
	                <col width="7%"/>
	                <col width="6%"/>
	                <col width="6%"/>
	                <col width="11%"/>
	                <col width="*"/> 
	                <col width="10%"/>
	                <col width="9%"/>
	                <col width="11%"/>
            	</colgroup>
            	<thead>
			    	<tr>
	                    <th scope="col">진행상태</th>
	                    <th scope="col">투찰여부</th>
	                    <th scope="col">계약구분</th>
	                    <th scope="col">공고번호</th>
	                    <th scope="col">공고명</th>
	                    <th scope="col">공고일자</th>
	                    <th scope="col">계약방법</th>
                   		<th scope="col">투찰마감일자</th>
			        </tr>
            	</thead>
		        <tbody>
				 	<c:if test="${comFn:nvl(myBidPblancListTotcnt, 0) == 0}">
						<tr class="row">
							<td colspan="8">검색 조건에 해당하는 데이터가 존재하지 않습니다.</td>
						</tr>
					</c:if>
					<c:if test="${myBidPblancListTotcnt > 0}">
						<c:forEach var="data" items="${myBidPblancList}" varStatus="status" >
						<tr class="row">
							<td>${data.BID_PSCD_NM }</td>
							<td>
								<c:if test="${data.BIDC_SBMT_CNT > 0 }">예</c:if>
								<c:if test="${data.BIDC_SBMT_CNT == 0 }">아니오</c:if>
							</td>
							<td>${data.CONT_SECD_NM}</td>
							<td>${data.ANNC_NO}-${data.ANNC_NGR}</td>
							<td class="pl20 list_tit" onclick="myBidPblancDetailInqire('${data.ANNC_NO}', '${data.ANNC_NGR}','${data.ROUND_NO }');" title="${data.BID_NM}" style="cursor: pointer;">
								<c:if test="${data.EMRG_YN eq 'Y'}"><font color="red">[긴급] </font></c:if>
								<c:if test="${data.ANNC_NGR ne '1'}"><font color="red">[정정] </font></c:if>
								${data.BID_NM}
							</td>
							<td>${comFn:formatDate(data.ANNC_DT,'yyyyMMddHHmmss','yyyy-MM-dd')}</td>
							<td>${data.CONT_MTCD_NM}</td>
							<td>${comFn:formatDate(data.BIDC_SBMT_ENDT,'yyyyMMddHHmmss','yyyy-MM-dd')}</td>
						</tr>						
						</c:forEach>
					</c:if>
		        </tbody>
		    </table>
		</div>
		<div class="list_bottom">
			<comTag:pagingIpro totalCount="${myBidPblancListTotcnt}" pageNo="${comFn:nvl(param.P_PAGE_NO, 1)}" pageSize="${comFn:nvl(param.P_PAGE_SIZE, 10)}" clickPage="pageObj.clickPage"/>
		</div> <!--// list_bottom E -->			
    </div>	
</div>		
            
<%--page move form --%>  
<form id="viewFrm" method="POST">
	<input type="hidden" name="resourceName" value="${param.resourceName}">
	<input type="hidden" name="P_ANNC_NO" >
	<input type="hidden" name="P_ANNC_NGR" >
	<input type="hidden" name="P_ROUND_NO" >
</form>