<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 기술평가현황 > 평가계획수립 등록 폼
 *
 * <pre>
 * ebid 
 *    |_ evlFormUpdtForm.jsp
 * 
 * </pre>
 * @date : 2017. 06. 16
 * @version : 1.0
 * @author : 은우소프트 전상훈
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld"%>
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld"%>

<link rel="stylesheet" type="text/css" href="${cssPath}/ipro/template/buttonStyle.css">
<link rel="stylesheet" type="text/css" href="${cssPath}/ipro/template/pagingPlace.css">
<link rel="stylesheet" type="text/css" href="${cssPath}/ipro/bodyBasic.css">
<link rel="stylesheet" type="text/css" href="${cssPath}/ipro/template/calendar.css">

<script type="text/javascript" src="${jsPath}/ipro/views/ebid/evlFormUpdtForm.js"></script>

<div class="content">
	<h3>평가표 수정</h3>

	<div class="conts_wrap">
		<div class="btn_wrap view_btn fr">
			<button type="button" class="btn btn_02 btn_sch pointer" id="listBtn">수정화면이동</button>
		</div>
		<div class="contentTitle" style="">평가표 기본정보</div>
		<table>
			<colgroup>
				<col width="15%" />
				<col width="35%" />
				<col width="15%" />
				<col width="35%" />
			</colgroup>
			<tr>
				<th>사업평가명</th>
				<td>SW사업(ICT컨설팅) 평가기준표</td>
				<th>평가유형</th>
				<td>소집</td>
			</tr>
			<tr>
				<th>총배점</th>
				<td>
					<c:if test="${param.P_EVL_GUBN eq 'F' }">20</c:if>
					<c:if test="${param.P_EVL_GUBN eq 'PMT' }">80</c:if>
					<c:if test="${param.P_EVL_GUBN eq 'NPMT' }">80</c:if>점 / <span id="totalScore"></span>점
				</td>
				<th>정량평가여부</th>
				<td>예</td>
			</tr>
		</table>
	</div>

	<div class="conts_wrap">
		<div class="contentTitle" style="">평가항목</div>
		<div class="btn_wrap view_btn cn n_m fr">
			<button type="button" class="btn btn_02 btn_sch pointer" id="addBtn">평가항목추가</button>
			<button type="button" class="btn btn_02 btn_sch pointer" id="delBtn">평가항목삭제</button>
		</div>
		<table id="Table">
			<colgroup>
				<col width="5%" />
				<col width="*" />
				<col width="10%" />
				<col width="10%" />
				<col width="10%" />
			</colgroup>
			<tr>
				<th class="txtc">선택</th>
				<th class="txtc">항목명</th>
				<th class="txtc">배점</th>
				<th class="txtc">등록일자</th>
				<th class="txtc">평가항목수정</th>
			</tr>
			<c:if test="${param.P_EVL_GUBN eq 'F' }">
				<tr>
					<td class="txtc"><input type="checkbox"></td>
					<td class="text">경영상태</td>
					<td class="txtc score">8</td>
					<td class="txtc">2016-04-28</td>
					<td class="txtc"><button type="button" class="btn btn_02 btn_sch pointer" onclick="evlFormUpdtPopup(this);">항목수정</button></td>
				</tr>
				<tr>
					<td class="txtc"><input type="checkbox"></td>
					<td class="text">회사 유사사업 수행실적</td>
					<td class="txtc score">6</td>
					<td class="txtc">2016-04-28</td>
					<td class="txtc"><button type="button" class="btn btn_02 btn_sch pointer" onclick="evlFormUpdtPopup(this);">항목수정</button></td>
				</tr>
				<tr>
					<td class="txtc"><input type="checkbox"></td>
					<td class="text">핵심투입인력 유사사업 수행실적</td>
					<td class="txtc score">6</td>
					<td class="txtc">2016-04-28</td>
					<td class="txtc"><button type="button" class="btn btn_02 btn_sch pointer" onclick="evlFormUpdtPopup(this);">항목수정</button></td>
				</tr>
			</c:if>
			<c:if test="${param.P_EVL_GUBN eq 'PMT' }">
				<tr>
					<td class="txtc"><input type="checkbox"></td>
					<td class="text">제안사 소개</td>
					<td class="txtc score">5</td>
					<td class="txtc">2016-04-28</td>
					<td class="txtc"><button type="button" class="btn btn_02 btn_sch pointer" onclick="evlFormUpdtPopup(this);">항목수정</button></td>
				</tr>
				<tr>
					<td class="txtc"><input type="checkbox"></td>
					<td class="text">전략 및 방법론</td>
					<td class="txtc score">15</td>
					<td class="txtc">2016-04-28</td>
					<td class="txtc"><button type="button" class="btn btn_02 btn_sch pointer" onclick="evlFormUpdtPopup(this);">항목수정</button></td>
				</tr>
				<tr>
					<td class="txtc"><input type="checkbox"></td>
					<td class="text">기술 및 기능</td>
					<td class="txtc score">20</td>
					<td class="txtc">2016-04-28</td>
					<td class="txtc"><button type="button" class="btn btn_02 btn_sch pointer" onclick="evlFormUpdtPopup(this);">항목수정</button></td>
				</tr>
				<tr>
					<td class="txtc"><input type="checkbox"></td>
					<td class="text">프로젝트 관리</td>
					<td class="txtc score">25</td>
					<td class="txtc">2016-04-28</td>
					<td class="txtc"><button type="button" class="btn btn_02 btn_sch pointer" onclick="evlFormUpdtPopup(this);">항목수정</button></td>
				</tr>
				<tr>
					<td class="txtc"><input type="checkbox"></td>
					<td class="text">프로젝트 지원</td>
					<td class="txtc score">10</td>
					<td class="txtc">2016-04-28</td>
					<td class="txtc"><button type="button" class="btn btn_02 btn_sch pointer" onclick="evlFormUpdtPopup(this);">항목수정</button></td>
				</tr>
				<tr>
					<td class="txtc"><input type="checkbox"></td>
					<td class="text">특수제안</td>
					<td class="txtc score">5</td>
					<td class="txtc">2016-04-28</td>
					<td class="txtc"><button type="button" class="btn btn_02 btn_sch pointer" onclick="evlFormUpdtPopup(this);">항목수정</button></td>
				</tr>
			</c:if>
			<c:if test="${param.P_EVL_GUBN eq 'NPMT' }">
				<tr>
					<td class="txtc"><input type="checkbox"></td>
					<td class="text">제안사 소개</td>
					<td class="txtc score">5</td>
					<td class="txtc">2016-04-28</td>
					<td class="txtc"><button type="button" class="btn btn_02 btn_sch pointer" onclick="evlFormUpdtPopup(this);">항목수정</button></td>
				</tr>
				<tr>
					<td class="txtc"><input type="checkbox"></td>
					<td class="text">전략 및 방법론</td>
					<td class="txtc score">15</td>
					<td class="txtc">2016-04-28</td>
					<td class="txtc"><button type="button" class="btn btn_02 btn_sch pointer" onclick="evlFormUpdtPopup(this);">항목수정</button></td>
				</tr>
				<tr>
					<td class="txtc"><input type="checkbox"></td>
					<td class="text">기술 및 기능</td>
					<td class="txtc score">20</td>
					<td class="txtc">2016-04-28</td>
					<td class="txtc"><button type="button" class="btn btn_02 btn_sch pointer" onclick="evlFormUpdtPopup(this);">항목수정</button></td>
				</tr>
				<tr>
					<td class="txtc"><input type="checkbox"></td>
					<td class="text">프로젝트 관리</td>
					<td class="txtc score">25</td>
					<td class="txtc">2016-04-28</td>
					<td class="txtc"><button type="button" class="btn btn_02 btn_sch pointer" onclick="evlFormUpdtPopup(this);">항목수정</button></td>
				</tr>
				<tr>
					<td class="txtc"><input type="checkbox"></td>
					<td class="text">프로젝트 지원</td>
					<td class="txtc score">10</td>
					<td class="txtc">2016-04-28</td>
					<td class="txtc"><button type="button" class="btn btn_02 btn_sch pointer" onclick="evlFormUpdtPopup(this);">항목수정</button></td>
				</tr>
				<tr>
					<td class="txtc"><input type="checkbox"></td>
					<td class="text">특수제안</td>
					<td class="txtc score">5</td>
					<td class="txtc">2016-04-28</td>
					<td class="txtc"><button type="button" class="btn btn_02 btn_sch pointer" onclick="evlFormUpdtPopup(this);">항목수정</button></td>
				</tr>
			</c:if>
			<tr id="hideTr" style="display: none;">
				<td class="txtc"><input type="checkbox"></td>
				<td class="text"></td>
				<td class="txtc score">0</td>
				<td class="txtc nowDate"></td>
				<td class="txtc"><button type="button" class="btn btn_02 btn_sch pointer" onclick="evlFormUpdtPopup(this);">항목수정</button></td>
			</tr>
		</table>
	</div>

	<div class="conts_wrap">
		<div class="contentTitle" style="">평가상세항목</div>
		<div class="btn_wrap view_btn cn n_m fr">
			<button type="button" class="btn btn_02 btn_sch pointer" id="addDetailBtn">평가상세항목추가</button>
			<button type="button" class="btn btn_02 btn_sch pointer" id="delDetailBtn">평가상세항목삭제</button>
			<button type="button" class="btn btn_02 btn_sch pointer" id="saveBtn">평가상세항목저장</button>
		</div>
		<table id="detailTable">
			<colgroup>
				<col width="5%" />
				<col width="*" />
				<col width="10%" />
				<col width="10%" />
				<col width="10%" />
			</colgroup>
			<tr>
				<th class="txtc">선택</th>
				<th class="txtc">상세항목명</th>
				<th class="txtc">상세항목배점</th>
				<th class="txtc">등록일자</th>
				<th class="txtc">상세항목내용</th>
			</tr>
			<c:if test="${param.P_EVL_GUBN eq 'F' }">
				<tr>
					<td class="txtc"><input type="checkbox"></td>
					<td class="txtc text"><input type="text" value="신용평가등급" style="width: 95%" ></td>
					<td class="txtc"><input type="text" value="8" style="width: 95%"></td>
					<td class="txtc">2016-04-28</td>
					<td class="txtc"><button type="button" class="btn btn_02 btn_sch pointer" onclick="evlFormDtlsCnUpdtPopup(this,'Y')">상세보기</button></td>
				</tr>
			</c:if>
			<c:if test="${param.P_EVL_GUBN eq 'PMT' }">
				<tr>
					<td class="txtc"><input type="checkbox"></td>
					<td class="txtc text"><input type="text" value="일반현황 및 연혁 등" style="width: 95%" ></td>
					<td class="txtc"><input type="text" value="5" style="width: 95%"></td>
					<td class="txtc">2016-04-28</td>
					<td class="txtc"><button type="button" class="btn btn_02 btn_sch pointer" onclick="evlFormDtlsCnUpdtPopup(this,'Y')">상세보기</button></td>
				</tr>
			</c:if>
			<c:if test="${param.P_EVL_GUBN eq 'NPMT' }">
				<tr>
					<td class="txtc"><input type="checkbox"></td>
					<td class="txtc text"><input type="text" value="일반현황 및 연혁 등" style="width: 95%" ></td>
					<td class="txtc"><input type="text" value="5" style="width: 95%"></td>
					<td class="txtc">2016-04-28</td>
					<td class="txtc"><button type="button" class="btn btn_02 btn_sch pointer" onclick="evlFormDtlsCnUpdtPopup(this,'Y')">상세보기</button></td>
				</tr>
			</c:if>
			<tr id="detailHideTr" style="display: none;">
				<td class="txtc"><input type="checkbox"></td>
				<td class="txtc text"><input type="text" value="" style="width: 95%"></td>
				<td class="txtc"><input type="text" value="" style="width: 95%"></td>
				<td class="txtc dateTemp"></td>
				<td class="txtc btnTemp">
					<button type="button" class="btn btn_02 btn_sch pointer" onclick="evlFormDtlsCnUpdtPopup(this,'N')" style="display: none;">상세보기</button>
				</td>
			</tr>
		</table>
	</div>
</div>
<!--// content E-->
<form action="" id="listFrm" method="POST">
	<input type="hidden" name="resourceName" value="${ param.resourceName }">
</form>
<form action="" id="popupFrm" method="POST">
	<input type="hidden" name="P_REGIST_AT">
	<input type="hidden" name="P_TEXT"> 
	<input type="hidden" name="P_SCORE">
</form>
<form action="" id="evlFormUpdtFrm" method="POST">
	<input type="hidden" name=P_EVL_GUBN>
</form>
