<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 소액견적, 입찰관리 > 상세 > 공동수급협정서제출 화면
 *
 * <pre>
 * ebid
 *    |_ copertnSpldmdTrtyOfeWritngForm.jsp
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

<script type="text/javascript" src="${jsPath}/opro/views/ebid/copertnSpldmdTrtyOfeWritngForm.js"></script>

<div class="contents_wrap">
	<ul class="step_wrap">
		<li><a href="#">${myMenuList.bigMenuNm}</a></li>
		<li><a href="#">${myMenuSubList.smallMenuNm}</a></li>
	</ul>
	<div class="tit_wrap">
		<h3 class="tit">공동수급협정서 등록</h3>
	</div>
	
	<form id="registFrm" method="POST">
		<fieldset>
			<input type="hidden" name="resourceName" value="${param.resourceName}">
			<input type="hidden" name="P_ANNC_NO" value="${inProgrsBidPblancDetail.ANNC_NO}">
			<input type="hidden" name="P_ANNC_NGR" value="${inProgrsBidPblancDetail.ANNC_NGR}">
			<input type="hidden" name="P_ROUND_NO" value="${inProgrsBidPblancDetail.ROUND_NO}">
			<input type="hidden" name="P_VEND_REG_NO" value="${vendInfo.VEND_REG_NO}">
			<input type="hidden" id="P_BID_GBN" name="P_BID_GBN" value="${param.P_BID_GBN}">
			
			<div class="view_wrap typeB">
				<div class="tit_area">
					<h4 class="tit">조달입찰 공동수급협정서(${inProgrsBidPblancDetail.ASSO_SPDM_CD_NM})</h4>
				</div>
				<div class="view_area">
					<table>			
		                <caption>입찰개요</caption>
		                <colgroup>
		                    <col width="15%">
		                    <col width="85%">
		                </colgroup>
		                <tbody>
			                <tr class="line">
			                    <th scope="row">입찰공고번호</th>
			                    <td>
			                        ${inProgrsBidPblancDetail.ANNC_NO}-${inProgrsBidPblancDetail.ANNC_NGR}
			                    </td>
			                </tr>
			                <tr>
			                    <th scope="row">입찰명</th>
			                    <td>${inProgrsBidPblancDetail.BID_NM}</td>
			                </tr>
			                <tr>
			                    <th scope="row">계약구분</th>
			                    <td>
			                        ${inProgrsBidPblancDetail.CONT_SECD_NM}
			                        <c:if test="${inProgrsBidPblancDetail.CONT_DECD eq '200'}">&nbsp;/&nbsp;${inProgrsBidPblancDetail.CONT_DECD_NM}</c:if>
			                    </td>
			                </tr>
			                <tr>
			                    <th scope="row">대표업체명</th>
			                    <td>
			                        ${loginResult.USR_NM}
			                    </td>
			                </tr>
			                <tr>
			                    <th scope="row">공동수급 방식</th>
			                    <td>
			                        ${inProgrsBidPblancDetail.ASSO_SPDM_CD_NM}
			                    </td>
			                </tr>
		                </tbody>
		            </table>
		        </div>
	        
				<div class="tit_area">
					<h4 class="tit">공동수급업체</h4>
					<div class="btn_right">
						<button type="button" class="btn btn_s2 btn_c2" id="addBtn">업체추가</button>
		            	<button type="button" class="btn btn_s2 btn_c2" id="nmenDeleteBtn">업체삭제</button>
					</div>
				</div>
				<div class="view_area" style="margin-bottom: 30px; overflow: auto;">
	            	<table>
		                <caption>공동수급방식</caption>
		                <colgroup>
		                    <col width="4%">
		                    <col width="8%">
		                    <col width="12%">
		                    <col width="12%">
		                    <col width="10%">
		                    <col width="8%">
		                    <col width="auto">
		                </colgroup>
		                <thead>
			                <tr>
			                    <th scope="col" class="noBg txtc">
			                    	<label for="nmenChoiseAllCbx" class="blind">체크박스</label>
			                    	<input type="checkbox" id="nmenChoiseAllCbx" name="nmenChoiseCbx" onclick="FwkCmmnUtil.setAllCheck('nmenChoiseAllCbx','nmenChoiseCbx');">
			                    </th>
			                    <th scope="col" class="txtc">구분</th>
			                    <th scope="col" class="txtc">업체명</th>
			                    <th scope="col" class="txtc">사업자등록번호</th>
			                    <th scope="col" class="txtc">대표자</th>
			                    <th scope="col" class="txtc">출자비율(%)</th>
			                    <th scope="col" class="txtc">비고</th>
			                </tr>
		                </thead>
		                <tbody>
		                	<tr class="row">
								<td>
									<input type="hidden" name="P_ASSO_VEND_BIZRNO" value="${vendInfo.BIZRNO}">
									<input type="hidden" name="P_ASSO_VEND_NM" value="${vendInfo.VEND_NM}">
									<input type="hidden" name="P_RPRS_NM" value="${vendInfo.RPRS_NM}">
								</td>
								<td class="txtc">대표업체</td>
								<td class="txtc">${vendInfo.VEND_NM}</td>
								<td class="txtc">${comFn:formatBizNumber(vendInfo.BIZRNO)}</td>
								<td class="txtc">${vendInfo.RPRS_NM}</td>
								<td>
									<label for="P_BID_NM" class="blind">출자비율</label>
		                        	<input type="text" name="P_SHR_RT" maxlength="5" numeric>
								</td>
								<td>
									<label for="P_RMK" class="blind">비고</label>
									<input type="text" name="P_RMK" value="">
								</td>
							</tr>
		                </tbody>
		                <tbody id="hiddenTbdy" style="display: none;">
		                	<tr class="row">
		                		<td>
		                			<label for="nmenChoiseCbx" class="blind">체크박스</label>
		                			<input type="checkbox" id="nmenChoiseCbx" name="nmenChoiseCbx">
		                		</td>
		                		<td>분담업체</td>
		                		<td>
		                			<label for="P_ASSO_VEND_NM" class="blind">업체명</label>
		                			<input type="text" name="P_ASSO_VEND_NM">
		                		</td>
		                		<td>
		                			<label for="P_ASSO_VEND_BIZRNO" class="blind">사업자등록번호</label>
		                			<input type="text" name="P_ASSO_VEND_BIZRNO" maxlength="10" numeric>
		                		</td>
		                		<td>
		                			<label for="P_RPRS_NM" class="blind">대표자</label>
		                			<input type="text" name="P_RPRS_NM">
		                		</td>
		                		<td>
		                			<label for="P_SHR_RT" class="blind">출자비율</label>
		                			<input type="text" name="P_SHR_RT" maxlength="4" class="numeric" numeric>
		                		</td>
		                		<td>
		                			<label for="P_RMK" class="blind">비고</label>
		                			<input type="text" name="P_RMK">
		                		</td>
		                	</tr>
		                </tbody>
		                <tbody id="trAddTbdy">
		                </tbody>
		            </table>
		        </div>
		        
				<div class="tit_area">
					<h4 class="tit">참가신청안내</h4>
				</div>
				<div class="view_area">
					<table>				        
		                <caption>참가신청안내</caption>
		                <colgroup>
		                    <col width="100%">
		                </colgroup>
		                <tbody>
							<tr>
								<td>
								귀하는 한국전기연구원에서 집행하는 위의 입찰에 참가함에 있어 입찰공고 및 입찰설명서에 정한 바에 따라 본 협정서를 제출하며 낙찰자로 선정될 경우에는 한국전기연구원 공동도급계약 운용기준에서 정한 공동수급협정서 제출을 확약합니다.
			                    </td>
							</tr>
		                </tbody>
		            </table>
		        </div>
		        
	            <div class="info_wrap">
			        <div class="blueBox">
			        	<p>${today}</p>
			    		<p>신청자 : ${loginResult.USR_NM}</p>
					    <p>한국전기연구원 귀하 </p>
			        </div>
				</div>		        
		        
		        <div class="btn_wrap view_btn">
		            <c:if test="${empty bidPartcptnSttus}">
		            	<button type="button" class="btn btn_m btn_orange" id="presentnBtn">제출</button>
		            </c:if>
		            <button type="button" class="btn btn_m btn_del" id="returnBtn">뒤로가기</button>
		        </div>
			</div>		
		</fieldset>
	</form>
</div>
		
<%--page move form --%> 
<form id="ccpyPopupFrm" method="POST">
	<input type="hidden" name="setMulti" value="Y">
</form>

<form id="listFrm" method="POST">
	<input type="hidden" name="resourceName" value="${param.resourceName}">
</form>