<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 기술평가현황 > 평가계획수립 상세
 *
 * <pre>
 * ebid 
 *    |_ tchqvlnDetail.jsp
 * 
 * </pre>
 * @date : 2017. 06. 16
 * @version : 1.0
 * @author : 은우소프트 전상훈
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %> 
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %> 

<script type="text/javascript" src="${jsPath}/ipro/views/ebid/tchqvlnDetail.js"></script>
 
<div class="content">
	<div class="conts_wrap">
		<div class="inner">
			<div class="tit_wrap">
				<h3 class="tit">평가계획현황 상세</h3>
				<ul class="step_wrap">
					<li><a href="#">${myMenuList.bigMenuNm}</a></li>
					<li><a href="#">${myMenuSubList.smallMenuNm}</a></li>
				</ul> <!--// step_wrap E -->
			</div> <!--// tit_wrap E -->
			<div class="view_wrap typeB">	
				<h4 class="tit">입찰개요</h4>
					<div class="view_area">
						<table>
					    	<colgroup>
					        	<col width="15%" />
					            <col width="35%" />
					            <col width="15%" />
					            <col width="35%" />
					        </colgroup>
					    	<tr>
					        	<th>입찰공고번호</th>
					            <td>P2017-00099-1</td>
					            <th>공고일자</th>
					            <td>2017-06-01</td>
					        </tr>
					        <tr>
					            <th>입찰명</th>
					            <td colspan="3">2017년 하반기 NCS기반 신입직원 채용대행 용역</td>
					        </tr>
					        <tr>
					        	<th>계약방법</th>
					            <td>일반(총액)</td>
					            <th>낙찰자선정방법</th>
					            <td>협상에 의한 계약</td>
					        </tr>
					        <tr>
					        	<th>공동도급</th>
					            <td>단독이행</td>
					            <th>복수예가여부</th>
					            <td>단일예가</td>
					        </tr>
					        <tr>
					            <th>입찰한도액</th>
					            <td colspan="3">90,000,000</td>
					        </tr>
					    </table>
					  </div>
						<br>
						<h4 class="tit">기술평가 기본정보</h4>
						<div class="view_area">
							<table>
						    	<colgroup>
						        	<col width="15%" />
						            <col width="35%" />
						            <col width="15%" />
						            <col width="35%" />
						        </colgroup>
						    	<tr>
						        	<th>기술평가구분</th>
						            <td>소집평가</td>
						            <th>기술평가일자</th>
						            <td>2017-06-22   15 : 00 ~ 17 : 00</td>
						        </tr>
						        <tr>
						            <th>기술평가장소</th>
					                <td colspan="3">기술평가실</td>
					            </tr>
						        <tr>
						            <th>상임위원 인원수</th>
					                <td colspan="3">1명</td>
								</tr>
						        <tr>
					                <th>비상임위원 인원수</th>
					                <td colspan="3">1명</td>
						        </tr>
						        <tr>    
						            <th>정량평가수행여부</th>
						            <td>예</td>
						        	<th>정성평가총배점</th>
						            <td>80 점</td>
								</tr>
								<tr>    
						            <th>정량평가 담당자</th>
						            <td>사용자1</td>
						        	<th>정량평가총배점</th>
						            <td>20 점</td>
								</tr>
						        <tr>
						            <th rowspan="2">평가표</th>
						            <td rowspan="2">SW사업(ICT컨설팅) 평가기준표</td>
						            <td colspan="2">
						            	<div class="btn_wrap">
						            		<button type="button" class="btn btn_02_auto btn_sch pointer" id="fdqntEvlFormBtn">정량평가표보기</button>
						            		<button type="button" class="btn btn_02_auto btn_sch pointer" id="pmtQualEvlFormBtn">상임위원 평가표보기</button>
						            		<button type="button" class="btn btn_02_auto btn_sch pointer" id="npmtQualEvlFormBtn">비상임위원 평가표보기</button>
						            	</div>
						            </td>
								</tr>
								<tr>
									<td colspan="2">
						            	<div class="btn_wrap ">
						            		<button type="button" class="btn btn_02_auto btn_sch pointer" id="pmtQualEvlWrtopnBtn">상임위원 평가의견서 설정</button>
						            		<button type="button" class="btn btn_02_auto btn_sch pointer" id="npmtQualEvlWrtopnBtn">비상임위원 평가의견서 설정</button>
						            	</div>
						            </td>
								</tr>
							</table>
						</div>
						<br>
						<div class="btn_right">
				       		<button type="button" class="btn btn_02_auto btn_sch pointer" id="mfcmmAcctoSmrizeFormBtn">평가위원별 총괄표</button>
				       		<c:if test="${param.P_TCHQVLN_PLAN_PRST_CD ne 'PJ50' }">
				       			<button type="button" class="btn btn_02_auto btn_sch pointer" id="nonPrmpstMfcmmCndcySelectBtn">비상임위원 후보군 선별</button>
				       			<button type="button" class="btn btn_02_auto btn_sch pointer" id="prmpstMfcmmCndcySelectBtn">상임위원 후보군 선별</button>
				       		</c:if>
				       	</div>
				       	<c:if test="${param.P_TCHQVLN_PLAN_PRST_CD ne 'PJ50' }">
							<h4 class="tit">기술평가위원 교섭 [ <span id="caAll">0차</span> ]
								<input type="hidden" value="0" id="caNum">
							</h4>
						</c:if>
						<c:if test="${param.P_TCHQVLN_PLAN_PRST_CD eq 'PJ50' }">
							<h4 class="tit">기술평가위원 교섭 [ <span id="caAll">최종확정</span> ]
							</h4>
						</c:if>
<!-- 						<div style="display: inline-block; float: left; height: 24px; padding-top: 3px;">교섭차수 : </div> -->
			       		<br>
						<div id="caButton" style="float: left;">
							<c:if test="${param.P_TCHQVLN_PLAN_PRST_CD eq 'PJ50' }">
								<button class="btn btn_02_auto btn_sch pointer" onclick="tchqvlnMfcmmNgtList('1');" type="button">1차 선별</button>
								<button class="btn btn_02_auto btn_sch pointer" onclick="tchqvlnMfcmmNgtList('2');" type="button">2차 선별</button>
								<button type="button" class="btn btn_02 btn_sch pointer" onclick="tchqvlnMfcmmNgtComptList();">최종확정</button>
							</c:if>
						</div>
							<input type="hidden" id="caAt" value="N">
				<c:if test="${param.P_TCHQVLN_PLAN_PRST_CD ne 'PJ50' }">
					<div class="view_area">
						<table id="showTable">
					    	<colgroup>
					        	<col width="10%" />
					            <col width="10%" />
					            <col width="8%" />
					            <col width="8%" />
					            <col width="*%" />
					            <col width="10%" />
					            <col width="12%" />
					            <col width="10%" />
					            <col width="16%" />
					        </colgroup>
					        <tbody id="showTbody">
						    	<tr>
						    		<th class="txtc">위원구분</th>
						            <th class="txtc">위원분야</th>
						            <th class="txtc">번호</th>
						            <th class="txtc">이름</th>
						            <th class="txtc">소속</th>
						            <th class="txtc">전화번호</th>
						            <th class="txtc">이메일</th>
						            <th class="txtc">교섭상태</th>
						            <th class="last txtc">교섭여부</th>
						        </tr>
						        <tr id="zeroTr">
						        	<td class="txtc" colspan="9">데이터가 존재하지 않습니다.</td>
						        </tr>
					        </tbody>
					        <tbody id="hideTbPmt" style="display: none;">
					        	<tr>
						        	<td class="txtc">상임위원</td>
						        	<td class="txtc">교육</td>
						        	<td class="txtc number1">1</td>
						        	<td class="txtc">고길동</td>
						        	<td>개발구매팀</td>
						        	<td class="txtc">010-2345-6789</td>
						        	<td>kdgoo@mail.com</td>
						        	<td class="txtc stateText">교섭전</td>
						        	<td class="txtc">
						        		<input type="hidden" name="P_ROWNUM" value="1">
						        		<div class="oBtn">
						        			<button type="button" class="btn btn_02 btn_sch pointer" onclick="ngtCompt(this);">교섭완료</button>
						        			<button type="button" class="btn btn_02 btn_sch pointer" onclick="ngtBrdoResnRegistPopup(this,'N');">교섭결렬</button>
						        		</div>
						        		<div class="nBtn" style="display: none;">
						        			<button type="button" class="btn btn_02 btn_sch pointer" onclick="ngtBrdoResnDetailPopup('Y');">결렬사유보기</button>
						        		</div>
						        		<div class="cBtn" style="display: none;">
						        			<button type="button" class="btn btn_02 btn_sch pointer" onclick="ngtBrdoResnRegistPopup(this,'N');">교섭취소</button>
						        		</div>
						        	</td>
						        </tr>
						        <tr>
						        	<td class="txtc">상임위원</td>
						        	<td class="txtc">교육</td>
						        	<td class="txtc number2">2</td>
						        	<td class="txtc">김하나</td>
						        	<td>사업2팀</td>
						        	<td class="txtc">010-7546-8764</td>
						        	<td>hnkim@mail.com</td>
						        	<td class="txtc stateText">교섭전</td>
						        	<td class="txtc">
						        		<input type="hidden" name="P_ROWNUM" value="2">
						        		<div class="oBtn">
						        			<button type="button" class="btn btn_02 btn_sch pointer" onclick="ngtCompt(this);">교섭완료</button>
						        			<button type="button" class="btn btn_02 btn_sch pointer" onclick="ngtBrdoResnRegistPopup(this,'N');">교섭결렬</button>
						        		</div>
						        		<div class="nBtn" style="display: none;">
						        			<button type="button" class="btn btn_02 btn_sch pointer" onclick="ngtBrdoResnDetailPopup('Y');">결렬사유보기</button>
						        		</div>
						        		<div class="cBtn" style="display: none;">
						        			<button type="button" class="btn btn_02 btn_sch pointer" onclick="ngtBrdoResnRegistPopup(this,'N');">교섭취소</button>
						        		</div>
						        	</td>
						        </tr>
					        </tbody>
					        <tbody id="hideTbNpmt" style="display: none;">
					        	<tr>
						        	<td class="txtc">비상임위원</td>
						        	<td class="txtc">심리학과</td>
						        	<td class="txtc number1">1</td>
						        	<td class="txtc">이상수</td>
						        	<td>울산대학교</td>
						        	<td class="txtc">010-5346-6669</td>
						        	<td>sslee@mail.com</td>
						        	<td class="txtc stateText">교섭전</td>
						        	<td class="txtc">
						        		<input type="hidden" name="P_ROWNUM" value="3">
						        		<div class="oBtn">
						        			<button type="button" class="btn btn_02 btn_sch pointer" onclick="ngtCompt(this);">교섭완료</button>
						        			<button type="button" class="btn btn_02 btn_sch pointer" onclick="ngtBrdoResnRegistPopup(this,'N');">교섭결렬</button>
						        		</div>
						        		<div class="nBtn" style="display: none;">
						        			<button type="button" class="btn btn_02 btn_sch pointer" onclick="ngtBrdoResnDetailPopup('Y');">결렬사유보기</button>
						        		</div>
						        		<div class="cBtn" style="display: none;">
						        			<button type="button" class="btn btn_02 btn_sch pointer" onclick="ngtBrdoResnRegistPopup(this,'N');">교섭취소</button>
						        		</div>
						        	</td>
						        </tr>
						        <tr>
						        	<td class="txtc">비상임위원</td>
						        	<td class="txtc">심리학과</td>
						        	<td class="txtc number2">2</td>
						        	<td class="txtc">김고은</td>
						        	<td>한국외국어대학교</td>
						        	<td class="txtc">010-2425-6755</td>
						        	<td>kukim@mail.com</td>
						        	<td class="txtc stateText">교섭전</td>
						        	<td class="txtc">
						        		<input type="hidden" name="P_ROWNUM" value="4">
						        		<div class="oBtn">
						        			<button type="button" class="btn btn_02 btn_sch pointer" onclick="ngtCompt(this);">교섭완료</button>
						        			<button type="button" class="btn btn_02 btn_sch pointer" onclick="ngtBrdoResnRegistPopup(this,'N');">교섭결렬</button>
						        		</div>
						        		<div class="nBtn" style="display: none;">
						        			<button type="button" class="btn btn_02 btn_sch pointer" onclick="ngtBrdoResnDetailPopup('Y');">결렬사유보기</button>
						        		</div>
						        		<div class="cBtn" style="display: none;">
						        			<button type="button" class="btn btn_02 btn_sch pointer" onclick="ngtBrdoResnRegistPopup(this,'N');">교섭취소</button>
						        		</div>
						        	</td>
						        </tr>
					        </tbody>
						</table>
					</div>
				</c:if>
				<c:if test="${param.P_TCHQVLN_PLAN_PRST_CD eq 'PJ50' }">
					<div class="view_area">
						<table id="showTable" style="display: none;">
					    	<colgroup>
					        	<col width="10%" />
					            <col width="10%" />
					            <col width="8%" />
					            <col width="8%" />
					            <col width="*%" />
					            <col width="10%" />
					            <col width="12%" />
					            <col width="10%" />
					            <col width="16%" />
					        </colgroup>
					        <tbody id="showTbody">
						    	<tr>
						    		<th class="txtc">위원구분</th>
						            <th class="txtc">위원분야</th>
						            <th class="txtc">번호</th>
						            <th class="txtc">이름</th>
						            <th class="txtc">소속</th>
						            <th class="txtc">전화번호</th>
						            <th class="txtc">이메일</th>
						            <th class="txtc">교섭상태</th>
						            <th class="last txtc">교섭여부</th>
						        </tr>
					        </tbody>
					        <tbody id="showTb1">
					        	<tr>
						        	<td class="txtc">상임위원</td>
						        	<td class="txtc">교육</td>
						        	<td class="txtc">1</td>
						        	<td class="txtc">가나다</td>
						        	<td>개발구매팀</td>
						        	<td class="txtc">010-7864-7894</td>
						        	<td>def@mail.com</td>
						        	<td class="txtc stateText">교섭결렬</td>
						        	<td class="txtc">
						        		<div class="nBtn">
						        			<button type="button" class="btn btn_02 btn_sch pointer" onclick="ngtBrdoResnDetailPopup('Y');">결렬사유보기</button>
						        		</div>
						        	</td>
						        </tr>
						        <tr>
						        	<td class="txtc">비상임위원</td>
						        	<td class="txtc">심리학과</td>
						        	<td class="txtc">2</td>
						        	<td class="txtc">유시우</td>
						        	<td>청주대학교</td>
						        	<td class="txtc">010-4564-4786</td>
						        	<td>abc@mail.com</td>
						        	<td class="txtc stateText">교섭결렬</td>
						        	<td class="txtc">
						        		<div class="nBtn">
						        			<button type="button" class="btn btn_02 btn_sch pointer" onclick="ngtBrdoResnDetailPopup('Y');">결렬사유보기</button>
						        		</div>
						        	</td>
						        </tr>
					        </tbody>
					        <tbody id="showTb2">
					        	<tr>
						        	<td class="txtc">상임위원</td>
						        	<td class="txtc">교육</td>
						        	<td class="txtc">1</td>
						        	<td class="txtc">고길동</td>
						        	<td>개발구매팀</td>
						        	<td class="txtc">010-2345-6789</td>
						        	<td>kdgoo@mail.com</td>
						        	<td class="txtc stateText">교섭완료</td>
						        	<td class="txtc">
						        	</td>
						        </tr>
						        <tr>
						        	<td class="txtc">상임위원</td>
						        	<td class="txtc">교육</td>
						        	<td class="txtc">2</td>
						        	<td class="txtc">김하나</td>
						        	<td>사업2팀</td>
						        	<td class="txtc">010-7546-8764</td>
						        	<td>hnkim@mail.com</td>
						        	<td class="txtc stateText">교섭전</td>
						        	<td class="txtc">
						        	</td>
						        </tr>
						        <tr>
						        	<td class="txtc">비상임위원</td>
						        	<td class="txtc">심리학과</td>
						        	<td class="txtc">3</td>
						        	<td class="txtc">이상수</td>
						        	<td>울산대학교</td>
						        	<td class="txtc">010-5346-6669</td>
						        	<td>sslee@mail.com</td>
						        	<td class="txtc stateText">교섭완료</td>
						        	<td class="txtc">
						        	</td>
						        </tr>
						        <tr>
						        	<td class="txtc">비상임위원</td>
						        	<td class="txtc">심리학과</td>
						        	<td class="txtc">4</td>
						        	<td class="txtc">김고은</td>
						        	<td>한국외국어대학교</td>
						        	<td class="txtc">010-2425-6755</td>
						        	<td>kukim@mail.com</td>
						        	<td class="txtc stateText">교섭전</td>
						        	<td class="txtc">
						        	</td>
						        </tr>
					        </tbody>
						</table>
					</div>
				</c:if>
				<div class="view_area">
				<table id="showTable2" <c:if test="${param.P_TCHQVLN_PLAN_PRST_CD ne 'PJ50' }">style="display: none;"</c:if>>
			    	<colgroup>
			        	<col width="10%" />
			            <col width="10%" />
			            <col width="8%" />
			            <col width="8%" />
			            <col width="*%" />
			            <col width="10%" />
			            <col width="12%" />
			        </colgroup>
			        <tbody id="showTbody">
				    	<tr>
				    		<th class="txtc">위원구분</th>
				            <th class="txtc">위원분야</th>
				            <th class="txtc">번호</th>
				            <th class="txtc">이름</th>
				            <th class="txtc">소속</th>
				            <th class="txtc">전화번호</th>
				            <th class="txtc">이메일</th>
				        </tr>
				        <tr>
				    		<td class="txtc">상임위원</td>
				            <td class="txtc">교육</td>
				            <td class="txtc">1</td>
				            <td class="txtc">고길동</td>
				            <td class="txtc">구매개발팀</td>
				            <td class="txtc">010-2345-6789</td>
				            <td class="txtc">kdgoo@mail.com</td>
				        </tr>
				        <tr>
				    		<td class="txtc">비상임위원</td>
				            <td class="txtc">심리학과</td>
				            <td class="txtc">2</td>
				            <td class="txtc">이상수</td>
				            <td class="txtc">울산대학교</td>
				            <td class="txtc">010-5346-6669</td>
				            <td class="txtc">sslee@mail.com</td>
				        </tr>
					</tbody>
				</table>
				</div>
			</div>
		</div>
	</div>
</div> <!--// content E-->
<form action="" id="listFrm" method="POST">
	<input type="hidden" name="resourceName" value="${ param.resourceName }" >
</form>
<form action="" id="popupFrm" method="POST">
	<input type="hidden" name="P_EVL_GUBN">
	<input type="hidden" name="P_REGIST_AT">
</form>
