<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 기술평가현황 > 평가계획수립 등록 폼
 *
 * <pre>
 * ebid 
 *    |_ tchqvlnRegistForm.jsp
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

<script type="text/javascript" src="${jsPath}/ipro/views/ebid/tchqvlnRegistForm.js"></script>
 
<div class="content">
	<div class="conts_wrap">
		<div class="inner">
			<div class="tit_wrap">
				<h3 class="tit">기술평가현황 등록</h3>
				<ul class="step_wrap">
					<li><a href="#">${myMenuList.bigMenuNm}</a></li>
					<li><a href="#">${myMenuSubList.smallMenuNm}</a></li>
				</ul> <!--// step_wrap E -->
			</div> <!--// tit_wrap E -->
			<div class="view_wrap typeB">	
				<h4 class="tit" style="">입찰개요</h4>
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
				<h4 class="tit" style="">기술평가 기본정보</h4>
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
				            <td colspan="3">
				            	<label><input type="radio" class="vam mr5" name="P_CNVC_DOCMNT_SE_CD" checked="checked">소집평가</label>
				            	<label><input type="radio" class="vam mr5" name="P_CNVC_DOCMNT_SE_CD">서면평가</label>
				            </td>
						</tr>
						<tr>
				            <th>기술평가일자</th>
				            <td colspan="3">
		                		<input type="text" id="P_TCHQVLN_DE" name="P_TCHQVLN_DE" date class="w120" readonly="readonly">
			                   	<span class="wave">&nbsp;</span>
			                    <input type="text" id="P_TCHQVLN_BEGIN_HH" name="P_TCHQVLN_BEGIN_HH" class="w80" maxlength="2" numeric onkeyup="hhKeyup(this);" onchange="hhKeyup(this);" ><span class="wave">:</span>
			                    <input type="text" id="P_TCHQVLN_BEGIN_MM" name="P_TCHQVLN_BEGIN_MM"  class="w80" maxlength="2" numeric onkeyup="mmKeyup(this);" onchange="mmKeyup(this);" >
			                    <span class="wave">~</span>
			                    <input type="text" id="P_TCHQVLN_END_HH" name="P_TCHQVLN_END_HH" class="w80" maxlength="2" numeric onkeyup="hhKeyup(this);" onchange="hhKeyup(this);" ><span class="wave">:</span>
			                    <input type="text" id="P_TCHQVLN_END_MM" name="P_TCHQVLN_END_MM" class="w80" maxlength="2" numeric onkeyup="mmKeyup(this);" onchange="mmKeyup(this);"  >
			            	</td>
				        </tr>
				        <tr>
				            <th>기술평가장소</th>
			                <td colspan="3">
			                    <input type="text" value="기술평가실">
			                </td>
			            </tr>
				        <tr>
				            <th>상임위원 인원수</th>
			                <td>
			                    <select>
			                    	<option value="">선택</option>
			                    	<option value="0">0명</option>
			                    	<option value="1">1명</option>
			                    	<option value="2">2명</option>
			                    	<option value="3">3명</option>
			                    	<option value="4">4명</option>
			                    	<option value="5">5명</option>
			                    	<option value="6">6명</option>
								</select>
			                </td>
			                <th>비상임위원 인원수</th>
			                <td>
			                    <select>
			                    	<option value="">선택</option>
			                    	<option value="0">0명</option>
			                    	<option value="1">1명</option>
			                    	<option value="2">2명</option>
			                    	<option value="3">3명</option>
			                    	<option value="4">4명</option>
			                    	<option value="5">5명</option>
			                    	<option value="6">6명</option>
								</select>
			                </td>
				        </tr>
				        <tr>    
				            <th>정량평가수행여부</th>
				            <td>
				            	<label><input type="radio" checked="checked" class="vam mr5" name="P_FDQNT_EVL_EXC_AT" value="Y">예</label>
				            	<label><input type="radio" class="vam mr5" name="P_FDQNT_EVL_EXC_AT" value="N">아니오</label>
				            </td>
				        	<th>정성평가총배점</th>
				            <td>
				            	<input type="text" class="w80"> 점
				            </td>
						</tr>
						<tr id="trFdqntEvlChargerNm">    
				            <th>정량평가 담당자</th>
				            <td>
				            	<input type="text" class="w120">
				            	<button type="button" class="btn btn_02 btn_sch vert" id="">검색</button>
				            </td>
				        	<th>정량평가총배점</th>
				            <td>
				            	<input type="text" class="w80"> 점
				            </td>
						</tr>
				        <tr>
				            <th>평가표</th>
				            <td colspan="3">
				            	<select name="P_EVL_TY_NO" id="P_FDQNT_EVL_TY_NO" class="w338">
				            		<option value="G0020000001">SW사업(ICT컨설팅) 평가기준표</option>
				            		<option value="G0030000001">SW사업(시스템개발용역) 평가기준표</option>
				            		<option value="G0040000001">SW사업(정보시스템감리용역) 평가기준표</option>
				            		<option value="G0010000001">건축/설계용역 평가기준표</option>
				            		<option value="G0050000001">기자재지원용역 평가기준표</option>
				            		<option value="G0000000001">일반/기술용역 평가기준표</option>
				            	</select>
				            		<button type="button" class="btn btn_02_auto btn_sch pointer" id="fdqntEvlFormBtn">정량평가표보기</button>
				            		<button type="button" class="btn btn_02_auto btn_sch pointer" id="pmtQualEvlFormBtn">상임위원 평가표보기</button>
				            		<button type="button" class="btn btn_02_auto btn_sch pointer" id="npmtQualEvlFormBtn">비상임위원 평가표보기</button>
				            </td>
						</tr>
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
</form>
