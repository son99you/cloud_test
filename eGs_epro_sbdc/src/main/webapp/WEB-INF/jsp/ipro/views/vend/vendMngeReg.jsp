<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 협력사등록
 *
 * <pre>
 * vend 
 *    |_ vendMngeReg.jsp
 * 
 * </pre>
 * @date : 2017. 06. 13
 * @version : 1.0
 * @author : 은우소프트 홍찬일
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %> 
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %> 

<script type="text/javascript" src="${jsPath}/ipro/views/vend/vendMngeReg.js"></script> 
 
<div class="conts_wrap">
	<div class="inner">
		<div class="tit_wrap">
			<h3 class="tit">협력사등록</h3>
			<ul class="step_wrap">
				<li><a href="#">${bigMenuNm}</a></li>
				<li><a href="#">${smallMenuNm}</a></li>
			</ul> <!--// step_wrap E -->
		</div> <!--// tit_wrap E -->
		
		<form id="registFrm" name="registFrm" method="POST" enctype="multipart/form-data">
			<input type="hidden" id="resourceName" name="resourceName" value="${param.resourceName}">
			<input type="hidden" id="resourceDesc" name="resourceDesc" value="${param.resourceDesc}">
			<input type="hidden" name="P_MAIN_SG" value=""/>
			
	       	<div class="tab_wrap01" style="margin-bottom: 30px;">
				<ul class="tab_tit">
					<li><a class="tapBtn on" id="1" href='javascript:tabEvent(1);' >기본정보</a></li>
					<li><a class="tapBtn" id="2" href='javascript:tabEvent(2);'>상세정보</a></li>
				</ul>
			</div>
       	
			<!-- 기본정보 탭 START -->
	       	<div class="view_wrap typeA" id="basic">
				<h4 class="tit">기본정보</h4>
				<div class="view_area">
					<table>
						<colgroup>
							<col style="width: 170px;">
							<col style="width: 349px;">
							<col style="width: 170px;">
							<col style="width: auto;">
						</colgroup>
						
						<tr height="24">
							<th class=" txtl bullet_orange"> 회사명</th>
							<td  colspan="3">
								<input type="text" id="P_VEND_NM" name="P_VEND_NM" style="width: 25%;">
							</td>
							<%-- <th class=" txtl"> 담당자성명(직위)</th>
							
								<input type="text" style="width: 25%;">
							</td> --%>
						</tr>
						
						<tr height="24">
						    <th class=" txtl"> 사업자등록번호</th>
							<td  >
								<input type="text" id="P_BIZRNO_1" name="P_BIZRNO_1" maxlength="3" style="width: 25%;" numeric>
								-
								<input type="text" id="P_BIZRNO_2" name="P_BIZRNO_2" maxlength="2" style="width: 25%;" numeric>
								-
								<input type="text" id="P_BIZRNO_3" name="P_BIZRNO_3" maxlength="5" style="width: 25%;" numeric>									
							</td>
							<th class=" txtl"> 법인번호</th>
							<td>
								<input type="text" id="P_CORP_REG_NO" name="P_CORP_REG_NO" style="width: 40%;">
							</td>
						</tr>
						<tr height="24">
						    <th class=" txtl"> 대표이사</th>
							<td  colspan="3">
								<input type="text" id="P_RPRS_NM" name="P_RPRS_NM" style="width: 25%;">
							</td>
						</tr>
						<tr height="24">
						    <th class=" txtl"> 업태</th>
							<td>
								<input type="text" id="P_BCNM" name="P_BCNM" style="width: 25%;">
							</td>
							<th class=" txtl"> 종목</th>
							<td>
								<input type="text" id="P_BTNM" name="P_BTNM" style="width: 25%;">
							</td>
						</tr>
					</table>
					
					<table>
						<colgroup>
							<col style="width: 170px;">
							<col style="width: 349px;">
							<col style="width: 170px;">
							<col style="width: auto;"> 
						</colgroup>
						<tr height="24">
							<th class=" txtl"> 회사설립일</th>
							<td>
								<div class="calendar_box">
									<input type="text" id="P_ESTB_YR" name="P_ESTB_YR" style="width: 50%;" date>
								</div>
							</td>
							<th class=" txtl bullet_orange"> 파트너유형</th>
							<td>
								<div class="selectLayer2 w_120">
		                			<comTag:comCmcdCdValueComboBox id="P_PRTN_TYCD" name="P_PRTN_TYCD" selectKey="" cdId="20005"  headerKey="" headerValue="선택" />
		                		</div>
							</td>
						</tr>
						<tr height="24">
						    <th class=" txtl"> 홈페이지주소</th>
							<td>
								<input type="text" id="P_HMPG_ADDR" name="P_HMPG_ADDR" style="width: 85%;">
							</td>
							<th class=" txtl"> 이메일주소</th>
							<td  >
								<input type="text" id="P_EMAL_ADDR1" name="P_EMAL_ADDR1" style="width: 85%;" >
							</td>
						</tr>
						
						<tr height="24">
						    <th class=" txtl"> 주소</th>
							<td  colspan="3">
								<input type="text" id="P_ZIP" name="P_ZIP" style="width: 10%;" class="disabled" readonly="readonly">
	                            <button type="button" class="btn btn_02 btn_sch" id="zipBtn" name="zipBtn">우편번호</button>
								<br>			                            
								<input type="text" id="P_ADDR_NM" name="P_ADDR_NM" style="width: 30%;">
								<input type="text" id="P_ADDR_DENM " name="P_ADDR_DENM " style="width: 30%;">
							</td>
						</tr>
						
						<tr height="24">
						    <th class=" txtl"> 전화번호</th>
							<td  >
								<input type="text" id="P_TEL_NO1" name="P_TEL_NO1" style="width: 85%;">
							</td>
							<th class=" txtl"> FAX번호</th>
							<td  >
								<input type="text" id="P_FX_NO" name="P_FX_NO" style="width: 85%;" >
							</td>
						</tr>
						
						<tr height="24">
							<th class=" txtl"> 주거래은행</th>
							<td>
								<input type="text" id="P_BKNM" name="P_BKNM" style="width: 25%;">
							</td>
						    <th class=" txtl"> 은행지점</th>
							<td>
								<input type="text" id="P_BNK_BRNC_NM" name="P_BNK_BRNC_NM" style="width: 25%;">
							</td>
						</tr>
						
						<tr height="24">
						    <th class=" txtl"> 신용등급</th>
							<td>
								<input type="text" id="P_CRDT_ESTM_RKCD" name="P_CRDT_ESTM_RKCD" style="width: 25%;" >
							</td>
							<th class=" txtl"> 기업신용평가회사</th>
							<td>
								<input type="text" id="P_CRDT_ESTM_AGNM" name="P_CRDT_ESTM_AGNM" style="width: 25%;">
							</td>
						</tr>
						
						<tr height="24">
							<th class=" txtl"> 영업장소재지</th>
							<td  colspan="3">
								<input type="text" id="P_CMPP_LCTN" name="P_CMPP_LCTN" style="width: 25%;">
							</td>
						</tr>
					</table>
				</div>

				<div class="tit_area">				
					<h4 class="tit" style="clear: both;">SG분류</h4>
					<div class="btn_right">
						<button type="button" class="btn btn_02 btn_c2" id="sgPopupBtn">SG설명</button>
						<button type="button" class="btn btn_02 btn_c2" id="sgAddBtn">추가</button>
					</div>
				</div>
				<div class="view_area" id="sgDiv">
					<table>
						<colgroup>
							<col style="width: 33%;">
							<col style="width: 33%;">
							<col style="width: auto;">
						</colgroup>
						<tr height="24">
							<th class="txtc">SG명</th>
							<th class="txtc">주거래SG</th>
							<th class="txtc">삭제</th>
						</tr>
						<tbody id="sgAddHiddTbdy">
							<tr height="24" style="display:none; width: 100%;" id="copySg">
								<td class="contd txtc">
									<div class="selectLayer2 w_120">
			                			<comTag:comCmcdCdValueComboBox id="P_SG_CD" name="P_SG_CD" selectKey="" cdId="N00001"  headerKey="" headerValue="선택" />
			                		</div>
								</td>
								<td class="contd txtc">
									<input type="checkbox" id="P_MAIN_SG" name="P_MAIN_SG_CHK" onclick="mainSgEvent(this);">
									<input type="hidden" name="P_MAIN_SG_YN"> 
								</td>
								<td class="contd txtc" >
									<button class='btn btn_02 btn_sch' id='deleteBtn' onclick='deleteFn(this);' >삭제</button>
								</td>
							</tr>
						</tbody>
						<tbody id="sgAddShowTbdy">
							<tr height="24" style="width: 100%;">
								<td class="contd txtc">
									<div class="selectLayer2 w_120">
			                			<comTag:comCmcdCdValueComboBox id="P_SG_CD" name="P_SG_CD" selectKey="" cdId="N00001"  headerKey="" headerValue="선택" />
			                		</div>
								</td>
								<td class="contd txtc">
									<input type="checkbox" id="P_MAIN_SG" name="P_MAIN_SG_CHK" onclick="mainSgEvent(this);" checked="checked">
									<input type="hidden" name="P_MAIN_SG_YN"> 
								</td>
								<td class="contd txtc">
									<button class='btn btn_02 btn_sch' id='deleteBtn' onclick='deleteFn(this);' >삭제</button>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
				
			
				<div class="view_area">
					<table>
						<colgroup>
							<col style="width: 170px;">
							<col style="width: auto;">
						</colgroup>
						<tr height="24">
							<th class=" txtl" > 관심분야</th>
							<td  >
								<input type="checkbox" name="P_CCN_SPHE_CHK" class="checkbox"> 공사&nbsp;&nbsp;
								<input type="checkbox" name="P_CCN_SPHE_CHK" class="checkbox"> 용역&nbsp;&nbsp;
								<input type="checkbox" name="P_CCN_SPHE_CHK" class="checkbox"> 물품&nbsp;&nbsp;
								<input type="checkbox" name="P_CCN_SPHE_CHK" class="checkbox"> 임대차 &nbsp;&nbsp;
								<input type="checkbox" name="P_CCN_SPHE_CHK" class="checkbox"> 매각
								
								<input type="hidden" name="P_CCN_SPHE">
							</td>
						</tr>
						<tr height="">
							<th class=" txtl" > 기술분야</th>
							<td  >
								<input type="checkbox" name="P_TCHN_SPHE_CHK" class="checkbox"> AFC&nbsp;&nbsp;
								<input type="checkbox" name="P_TCHN_SPHE_CHK" class="checkbox"> 건축&nbsp;&nbsp;
								<input type="checkbox" name="P_TCHN_SPHE_CHK" class="checkbox"> 토목&nbsp;&nbsp;
								<input type="checkbox" name="P_TCHN_SPHE_CHK" class="checkbox"> 통신&nbsp;&nbsp;
								<input type="checkbox" name="P_TCHN_SPHE_CHK" class="checkbox"> 전차선&nbsp;&nbsp;
								<input type="checkbox" name="P_TCHN_SPHE_CHK" class="checkbox"> 송변선&nbsp;&nbsp;
								<input type="checkbox" name="P_TCHN_SPHE_CHK" class="checkbox"> 전력&nbsp;&nbsp;
								<input type="checkbox" name="P_TCHN_SPHE_CHK" class="checkbox"> 기계&nbsp;&nbsp;
								<input type="checkbox" name="P_TCHN_SPHE_CHK" class="checkbox"> 선로&nbsp;&nbsp;
								<br>
								<input type="checkbox" name="P_TCHN_SPHE_CHK" class="checkbox"> 전동차&nbsp;&nbsp;
								<input type="checkbox" name="P_TCHN_SPHE_CHK" class="checkbox"> 신호&nbsp;&nbsp;
								<input type="checkbox" name="P_TCHN_SPHE_CHK" class="checkbox"> 광고홍보&nbsp;&nbsp;
								<input type="checkbox" name="P_TCHN_SPHE_CHK" class="checkbox"> 역무&nbsp;&nbsp;
								<input type="checkbox" name="P_TCHN_SPHE_CHK" class="checkbox"> 경영&nbsp;&nbsp;
								<input type="checkbox" name="P_TCHN_SPHE_CHK" class="checkbox"> 안전&nbsp;&nbsp;
								<input type="checkbox" name="P_TCHN_SPHE_CHK" class="checkbox"> 단순구매&nbsp;&nbsp;
								<input type="checkbox" name="P_TCHN_SPHE_CHK" class="checkbox"> 보수정비&nbsp;&nbsp;
								<input type="checkbox" name="P_TCHN_SPHE_CHK" class="checkbox"> 기타&nbsp;&nbsp;
								
								<input type="hidden" name="P_TCHN_SPHE">
							</td>
						</tr>
					</table>
				</div>

				<div class="tit_area">				
					<h4 class="tit" style="clear: both;">담당자등록</h4>
					<div class="btn_right">
				   		<button type="button" class="btn btn_02 btn_c2" id="userAddBtn" >추가</button>
				   	</div>
			   	</div>
				<div class="view_area" id="userDiv">
					<table>
						<colgroup>
							<col style="width: 13%;">
		                   	<col style="width: 15%;">
		                   	<col style="width: 15%;">
		                   	<col style="width: 15%;">
		                   	<col style="width: 20%;">
		                   	<col style="width: 15%;">
		                   	<col style="width: 7%;">
						</colgroup>
						<thead>
			               <tr>
				                <th class="txtc">담당자명</th>
								<th class="txtc">담당자직급</th> 
								<th class="txtc">연락처</th> 
								<th class="txtc">연락처(휴대폰)</th> 
								<th class="txtc">이메일</th> 
								<th class="txtc">부서명</th> 
								<th class="txtc">삭제</th> 
			               </tr>
						</thead>
						
						<tbody id="userAddHiddTbdy">
							<tr height="24" style="display:none; width: 100%;" id="copySg">
								<td class="contd txtc">
									<input type="text" id="P_USR_NM" name="P_USR_NM">
								</td>
								<td class="contd txtc">
									<input type="text" id="P_OPNM" name="P_OPNM">
								</td>
								<td class="contd txtc">
									<input type="text" id="P_TEL_NO2" name="P_TEL_NO2">
								</td>
								<td class="contd txtc">
									<input type="text" id="P_CP_NO" name="P_CP_NO">
								</td>
								<td class="contd txtc">
									<input type="text" id="P_EMAL_ADDR2" name="P_EMAL_ADDR2" style="width: 85%;">
								</td>
								<td class="contd txtc">
									<input type="text" id="P_DEPT_NM" name="P_DEPT_NM">
								</td>
								<td class="contd txtc">
									<button class='btn btn_02 btn_sch' id='deleteBtn' onclick='deleteFn(this);' >삭제</button>
								</td>
							</tr>
						</tbody>
						
						<tbody id="userAddShowTbdy">
							<tr height="24">
								<td class="contd txtc">
									<input type="text" id="P_USR_NM" name="P_USR_NM">
								</td>
								<td class="contd txtc">
									<input type="text" id="P_OPNM" name="P_OPNM">
								</td>
								<td class="contd txtc">
									<input type="text" id="P_TEL_NO2" name="P_TEL_NO2">
								</td>
								<td class="contd txtc">
									<input type="text" id="P_CP_NO" name="P_CP_NO">
								</td>
								<td class="contd txtc">
									<input type="text" id="P_EMAL_ADDR2" name="P_EMAL_ADDR2" style="width: 85%;">
								</td>
								<td class="contd txtc">
									<input type="text" id="P_DEPT_NM" name="P_DEPT_NM">
								</td>
								<td class="contd txtc">
									<button class='btn btn_02 btn_sch' id='deleteBtn' onclick='deleteFn(this);' >삭제</button>
								</td>
							</tr>
						</tbody>
						
					</table>
				</div> 

				<div class="tit_area">				
					<h4 class="tit" style="clear: both;">품목등록</h4>
					<div class="btn_right">
				   		<button type="button" class="btn btn_02 btn_c2" id="itemAddBtn" >추가</button>
				   	</div>
			   	</div>
				<div class="view_area">
					<table>
						<colgroup>
							<col style="width: 10%;">
		                   	<col style="width: 20%;">
		                   	<col style="width: 15%;">
		                   	<col style="width: 15%;">
		                   	<col style="width: 15%;">
		                   	<col style="width: 15%;">
		                   	<col style="width: 10%">
						</colgroup>
						<thead>
							<tr>
				                <th class="txtc">품목번호</th>
				                <th class="txtc">대분류명</th>
								<th class="txtc">중분류명</th> 
								<th class="txtc">소분류명</th> 
								<th class="txtc">품목명</th>
								<th class="txtc">단가</th>  
								<th class="txtc">삭제</th> 
			               </tr>
						</thead>
						<tbody id="itemFrame">
						</tbody>
					</table>
				</div> 
			</div>
			<!-- 기본정보 탭 END -->
	
			<!-- 상세정보 탭 START -->
			<div class="view_wrap typeA" id="detail" style="display:none;">
				<h4 class="tit">인증, 자격취득 현황(품질,ISO등)</h4>
				<div class="view_area">
					<table>
						<colgroup>
							<col style="width: 315px;">
							<col style="width: 155px;">
							<col style="width: auto;">
						</colgroup>
						<tr height="24">
							<th class="txtc">인증명칭</th>
							<th class="txtc">취득일</th>
							<th class="txtc">취득기관</th>
						</tr>
						<tr height="24">
							<td>
								<input type="text" name="P_CTT_NM" value="" style="width: 90%;">
							</td>
							<td>
								<input type="text" name="P_ACQS_DE"  value="" style="width: 90%;">
							</td>
							<td>
								<input type="text" name="P_ACQS_AGNM"  value="" style="width: 90%;">
							</td>
						</tr>
						<tr height="24">
							<td>
								<input type="text" name="P_CTT_NM" value="" style="width: 90%;" >
							</td>
							<td>
								<input type="text"  name="P_ACQS_DE"  value="" style="width: 90%;" >
							</td>
							<td>
								<input type="text"  name="P_ACQS_AGNM"  value="" style="width: 90%;" >
							</td>
						</tr>
						<tr height="24">
							<td>
								<input type="text" name="P_CTT_NM" value="" style="width: 90%;" >
							</td>
							<td>
								<input type="text"  name="P_ACQS_DE"  value="" style="width: 90%;" >
							</td>
							<td>
								<input type="text"  name="P_ACQS_AGNM"  value="" style="width: 90%;" >
							</td>
						</tr>
					</table>
				</div>
				
			
				<div class="view_area">
				    <div class="view_area">
						<table>
							<colgroup>
								<col style="width: 5%;">
								<col style="width: 20%;">
								<col style="width: 75%;">
							</colgroup>
							<thead>
							<tr height="24">
								<th class="txtc">순번</th>
								<th class="txtc">문서명</th>
								<th class="txtc">파일명</th>
							</tr>
							</thead>
							<tr height="24">
								<th class=" txtc">1</th>
								<td>사업자등록증</td>
								<td  id="file1">
	
									<button type="button" class="btn btn_02 btn_sch" title="검색" style="float: right; margin-top: 3px;" id="fileBtn1">파일찾기</button>
	
			                    	<div id="fileRow1" style="display: none; height: 30px;">
			                    		<input type="file" name="" onchange="fileChange(this)" style="display: none;">
			                    		<input type="hidden" name="fileGbn" value="A" disabled="disabled"/>    
					                   	<span class="stD" style=""> 
			                    			<button type="button" class="btn btn_02 btn_sch" style="float: right; display: inline-block; margin-top: 3px;" onclick="rowDel(this)">삭제</button>
			                    		</span>
			                    	</div>
			                    	
			                    	<div id="fileDiv1" style="width: 680px; line-height: 30px;">
			                    	</div>
			                    	
				                    	
								</td>
							</tr>
							<tr height="24">
								<th class=" txtc">2</th>
								<td>신용평가등급자료</td>
								<td>
									<button type="button" class="btn btn_02 btn_sch" title="검색" style="float: right; margin-top: 3px;" id="fileBtn2">파일찾기</button>
				                    
			                    	<div id="fileRow2" style="display: none; height: 30px;">
			                    		<input type="file" name="" onchange="fileChange(this)" style="display: none;">
			                    		<input type="hidden" name="fileGbn" value="B" disabled="disabled"/>    
					                   	<span class="stD" style=""> 
			                    			<button type="button" class="btn btn_02 btn_sch" style="float: right; display: inline-block; margin-top: 3px;" onclick="rowDel(this)">삭제</button>
			                    		</span>
			                    	</div>
			                    	
			                    	<div id="fileDiv2" style="width: 680px; line-height: 30px;">
			                    	</div>
								</td>
							</tr>
							<tr height="24">
								<th class=" txtc">3</th>
								<td>최근년도 결산 재무재표</td>
								<td>
									<button type="button" class="btn btn_02 btn_sch" title="검색" style="float: right; margin-top: 3px;" id="fileBtn3">파일찾기</button>
				                    
			                    	<div id="fileRow3" style="display: none; height: 30px;">
			                    		<input type="file" name="" onchange="fileChange(this)" style="display: none;">
			                    		<input type="hidden" name="fileGbn" value="C" disabled="disabled"/>    
					                   	<span class="stD" style=""> 
			                    			<button type="button" class="btn btn_02 btn_sch" style="float: right; display: inline-block; margin-top: 3px;" onclick="rowDel(this)">삭제</button>
			                    		</span>
			                    	</div>
			                    	
			                    	<div id="fileDiv3" style="width: 680px; line-height: 30px;">
			                    	</div>
								</td>
							</tr>
							<tr height="24">
								<th class=" txtc">4</th>
								<td>회사소개자료 카타로그</td>
								<td>
									<button type="button" class="btn btn_02 btn_sch" title="검색" style="float: right; margin-top: 3px;" id="fileBtn4">파일찾기</button>
				                    
			                    	<div id="fileRow4" style="display: none; height: 30px;">
			                    		<input type="file" name="" onchange="fileChange(this)" style="display: none;">
			                    		<input type="hidden" name="fileGbn" value="D" disabled="disabled"/>    
					                   	<span class="stD" style=""> 
			                    			<button type="button" class="btn btn_02 btn_sch" style="float: right; display: inline-block; margin-top: 3px;" onclick="rowDel(this)">삭제</button>
			                    		</span>
			                    	</div>
			                    	
			                    	<div id="fileDiv4" style="width: 680px; line-height: 30px;">
			                    	</div>
								</td>
							</tr>
							<tr height="24">
								<th class=" txtc">5</th>
								<td>인증서 등 자료</td>
								<td>
									<button type="button" class="btn btn_02 btn_sch" title="검색" style="float: right; margin-top: 3px;" id="fileBtn5">파일찾기</button>
				                    
			                    	<div id="fileRow5" style="display: none; height: 30px;">
			                    		<input type="file" name="" onchange="fileChange(this)" style="display: none;">
			                    		<input type="hidden" name="fileGbn" value="E" disabled="disabled"/>    
					                   	<span class="stD" style=""> 
			                    			<button type="button" class="btn btn_02 btn_sch" style="float: right; display: inline-block; margin-top: 3px;" onclick="rowDel(this)">삭제</button>
			                    		</span>
			                    	</div>
			                    	
			                    	<div id="fileDiv5" style="width: 680px; line-height: 30px;">
			                    	</div>
								</td>
							</tr>
							<tr height="24">
								<th class=" txtc">6</th>
								<td>면허수첩, 면허증사본</td>
								<td>
									<button type="button" class="btn btn_02 btn_sch" title="검색" style="float: right; margin-top: 3px;" id="fileBtn6">파일찾기</button>
				                    
			                    	<div id="fileRow6" style="display: none; height: 30px;">
			                    		<input type="file" name="" onchange="fileChange(this)" style="display: none;">
			                    		<input type="hidden" name="fileGbn" value="F" disabled="disabled"/>    
					                   	<span class="stD" style=""> 
			                    			<button type="button" class="btn btn_02 btn_sch" style="float: right; display: inline-block; margin-top: 3px;" onclick="rowDel(this)">삭제</button>
			                    		</span>
			                    	</div>
			                    	
			                    	<div id="fileDiv6" style="width: 680px; line-height: 30px;">
			                    	</div>
								</td>
							</tr>
							<tr height="24">
								<th class=" txtc">7</th>
								<td>기타자료</td>
								<td>
									<button type="button" class="btn btn_02 btn_sch" title="검색" style="float: right; margin-top: 3px;" id="fileBtn7">파일찾기</button>
				                    
			                    	<div id="fileRow7" style="display: none; height: 30px;">
			                    		<input type="file" name="" onchange="fileChange(this)" style="display: none;">
			                    		<input type="hidden" name="fileGbn" value="G" disabled="disabled"/>    
					                   	<span class="stD" style=""> 
			                    			<button type="button" class="btn btn_02 btn_sch" style="float: right; display: inline-block; margin-top: 3px;" onclick="rowDel(this)">삭제</button>
			                    		</span>
			                    	</div>
			                    	
			                    	<div id="fileDiv7" style="width: 680px; line-height: 30px;">
			                    	</div>
			                    </td>
							</tr>
						</table>
					</div>
					
					
				</div>
			</div>
			<!-- 상세정보 탭 END -->
<!-- 			<div class="view_area">		 -->
		    	<div class="btn_wrap view_btn">
		    		<!-- <button type="button" class="blueBtn L" id="searchBtn1" onclick="chargerList();">담당자조회</button> -->
		    		<button type="button" class="btn btn_02 btn_revise" id="saveBtn1" >등록</button>
		    	</div>
<!-- 	    	</div> -->
		</form>
	</div>
</div>
	<%-- DETAIL FORM --%>
	<form id="detailFrm" method="POST" > 
		<input type="hidden" name="resourceName" value="${ param.resourceName }" >
	</form>
	<form id="popupFrm" method="POST" > 
		<input type="hidden" name="resourceName" value="${ param.resourceName }" >
		<input type="hidden" name="resourceDesc" value="${param.resourceDesc}">
	</form>
	
	<form id="itemListPopupFrm" method="POST">
   		<input type="hidden" name="resourceName" value="${param.resourceName}">
   		<input type="hidden" name="resourceDesc" value="${param.resourceDesc}">
   	</form>
	
	<form id="zipPopupFrm" method="POST">
   		<input type="hidden" name="resourceName" value="${param.resourceName}">
   		<input type="hidden" name="resourceDesc" value="${param.resourceDesc}">
   	</form>
	
