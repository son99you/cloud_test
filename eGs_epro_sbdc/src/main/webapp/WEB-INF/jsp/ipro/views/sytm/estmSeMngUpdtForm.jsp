<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 정보관리 > 평가구분관리 수정
 *
 * <pre>
 * sytm 
 *    |_ estmSeMngUpdtForm.jsp
 * 
 * </pre>
 * @date : 2018. 08. 22
 * @version : 1.0
 * @author : 은우소프트 은잔디
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld"%>
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld"%>

<script type="text/javascript" src="${jsPath}/ipro/views/sytm/estmSeMngUpdtForm.js"></script>

<!-- 네비게이션 -->
<div class="area-detail">
	<div class="table-detail">
		<!-- Top -->
		<div class="nav_sec">

			<div class="btn-help" style="display:none;">
				<a href="javascript:helpPopup();">도움말</a>
			</div>
			
			<div class="option-left">
				<ul class="location">
					<li style="font-size: 30px; font-weight: 500;">평가구분관리 수정</li>
				</ul>
			</div>
			
			<div class="option-right">
				<ul class="location">
					<li class="home"><a href="#">홈</a></li>
					<li><a href="#">${myMenuList.bigMenuNm}</a></li>
					<li><a href="#">${myMenuSubList.smallMenuNm}</a></li>
				</ul>
			</div>
		</div>
	</div>
</div>
<!--//네비게이션 -->

<!-- Detail -->
<form id="updtFrm" method="POST" enctype="multipart/form-data">
	<input type="hidden" name="resourceName" value="${comFn:nvl(param.resourceName, resourceName)}">
	<input type="hidden" id="seProcdListCnt" value="${estmSeProcdListCnt }">
	<input type="hidden" name="P_ESTM_SECD" value="${estmSeMngDetail.ESTM_SECD}" >
	<input type="hidden" id="P_SIGN_FILE_GRP_NO" name="P_SIGN_FILE_GRP_NO" value="${estmSeMngDetail.FILE_GRP_NO}">
	
	<input type="hidden" id="P_DELETE_FILE_SN" name="P_DELETE_FILE_SN">
		
			<div class="area-detail">
				<div class="table-detail">
					<!-- Top -->
					<div class="top-detail">
						<div class="type-fleft">
							<div class="contents-label">기본정보</div>
						</div>

					</div>
					<!-- //Top -->


					<table class="component-detail-table type-line-none">
						<colgroup>
							<col width="150">
							<col width="*">
							<col width="150">
							<col width="*">
						</colgroup>
						<tbody>
						<tr>
							<th>평가구분</th>
							<td>${estmSeMngDetail.ESTM_SENM }</td>
							<th>최고/최저점제외여부</th>
							<td>
								<comTag:cmmnCdValueRadio name="P_MXMN_SCR_EXCP_YN" selectKey="${estmSeMngDetail.MXMN_SCR_EXCP_YN }" list="{'Y' :'예', 'N':'아니오'}"/>
							</td>
						</tr>
						<tr>
							<th>
								평가대상구분
							</th>
							<td>
								<comTag:comCmcdCdValueComboBox id="P_ESTM_OBJ_SECD" name="P_ESTM_OBJ_SECD" selectKey="${estmSeMngDetail.ESTM_OBJ_SECD }" cdId="ESTM_OBJ_SECD"  headerKey="" headerValue="선택" className="component-select"/>
							</td>
							<th>
								평가부서
							</th>
							<td>
								<div class="component-input-search type-division">
									<input type="text" id="P_ESTM_CHRG_DEPT_NM" name="P_ESTM_DEPT_NM" value="${estmSeMngDetail.ESTM_DEPT_NM }" class="component-input" readonly="readonly">
									<input type="hidden" id="P_ESTM_CHRG_DEPT_NO" name="P_ESTM_DEPT_NO" value="${estmSeMngDetail.ESTM_DEPT_NO }">
									<!-- <a href="javascript:" class="btn-search-close" id="estmChrgDeptDelBtn"></a> -->
									<a href="javascript:" class="btn-search" id="estmChrgDepSrchBtn"></a>
								</div>
							</td>
						</tr>
						</tbody>
					</table>
				</div>
				
				

		
		<div class="table-detail">
			<!-- Top -->
			<div class="top-detail">
				<div class="type-fleft">
					<div class="contents-label">심사위원 서명파일</div>
				</div>
				<!-- //Top -->
				
				<div class="type-fright">
					<a href="javascript:" class="component-button-s type-add fileAddBtn">추가</a>
				</div>
			</div>
			
			<table class="component-detail-table type-file"> 
				<colgroup>
					<col width="30%">
					<col width="70%">
				</colgroup>
				<thead>
					<tr>
						<th>문서명</th>
						<th>파일첨부</th>
					</tr>
				</thead>
				<tbody id="signFileTbody">
                	<c:if test="${not empty estmSignFileList }">
                  		<c:forEach items="${estmSignFileList}" var="data" varStatus="status">
                  			<tr>
                  				<th style="padding:7px 15px 7px 15px;">${data.FILE_DOC_NM}</th>
                  				<td style="padding:10px 10px 2px 13px;">
	                   				<a href="javascript:download('${data.FILE_GRP_NO}', '${data.FILE_SN}');">${data.SYS_FILE_NM }</a>
	                   				<button type="button" class="component-button-s type-del" onclick="fileDel(this,'${data.FILE_SN }')" style="float: right;">삭제</button>
                  				</td>
                  			</tr>
	                   	</c:forEach>
                   	</c:if>
				</tbody>
			</table> 
		</div>
		
		
		<div class="table-detail">
			<!-- Top -->
			<div class="top-detail">
				<div class="type-fleft">
					<div class="contents-label">평가절차</div>
				</div>

				<div class="type-fright">

					<button type="button" class="component-button-s type-add" id="addBtn" name="addBtn">평가추가</button>
					<button type="button" class="component-button-s type-del" id="delBtn" name="delBtn">평가삭제</button>
				</div>
			</div>
			<!-- //Top -->

			<table class="component-detail-table ">
				<colgroup>
					<col width="88">
					<col width="200px;">
					<col width="auto;">
					<col width="100px;">
					<col width="300px;">
				</colgroup>
				<thead>
				<tr>
					<th>순번</th>
					<th>평가절차구분</th>
					<th>평가절차명</th>
					<th>평가서식</th>
					<th>평가서식명</th>
				</tr>

				</thead>
				<tbody id="hiddenTbody">
					<tr style="display: none;">
					<td class="txt-center">
						<label class="component-checkbox">
							<input type="checkbox" name="chck">
							<i></i>
						</label>
					</td>
						<td class="txt-center"><comTag:comCmcdCdValueComboBox id="P_ESTM_PROCD_SECD" name="P_ESTM_PROCD_SECD_H" selectKey="" cdId="ESTM_PROCD_SECD"  headerKey="" headerValue="선택" className="component-select"/></td>
						<td><input type="text" class="component-input type-full" placeholder="평가절차명" name="P_ESTM_PROCD_NM_H" value=""></td>
						<td class="txt-center">
							<button type="button" name="estmFrmPopup" id="" class="component-button-s type-a">선택</button>
							<input type="hidden" class="component-input" id="P_ESTM_FRM_NO" name="P_ESTM_FRM_NO_H" value="">
							<input type="hidden" class="component-input" id="P_ESTM_FRM_NM" name="P_ESTM_FRM_NM_H" value="">
						</td>
						<td id="P_ESTM_FRM_NM_TEXT"></td>
					</tr>
				</tbody>
				<tbody id="showTbody">
				<c:forEach var="data" items="${estmSeProcdList}" varStatus="status">
					<tr>
						<td class="txt-center">
							<label class="component-checkbox">
								<input type="checkbox" name="chck">
								<i></i>
							</label>
						</td>
						<td class="txt-center">
							<comTag:comCmcdCdValueComboBox id="P_ESTM_PROCD_SECD" name="P_ESTM_PROCD_SECD" selectKey="${data.ESTM_PROCD_SECD}" cdId="ESTM_PROCD_SECD"  headerKey="" headerValue="선택" className="component-select"/></td>
						<td><input type="text" class="component-input type-full" placeholder="평가절차명" name="P_ESTM_PROCD_NM" value="${data.ESTM_PROCD_NM}"></td>
						<td class="txt-center">
							<button type="button" name="estmFrmPopup" id="${status.count }" class="component-button-s type-a">선택</button>
							<input type="hidden" class="component-input" id="P_ESTM_FRM_NO${status.count }" name="P_ESTM_FRM_NO" value="${data.ESTM_FRM_NO}">
							<input type="hidden" class="component-input" id="P_ESTM_FRM_NM${status.count }" name="P_ESTM_FRM_NM" value="${data.ESTM_FRM_NM}">
						</td>
						<td id="P_ESTM_FRM_NM_TEXT${status.count }">${data.ESTM_FRM_NM}</td>
					</tr>
					<%-- <tr>
						<td class="txt-center">${data.RNUM}</td>
						<td class="txt-center"><comTag:comCmcdCdValueComboBox id="P_ESTM_PROCD_SECD" name="P_ESTM_PROCD_SECD" selectKey="C" cdId="ESTM_PROCD_SECD"  headerKey="" headerValue="선택" className="component-select"/></td>
						<td><input type="text" class="component-input type-full" placeholder="평가절차명" name="P_ESTM_DTL_ITEM_NM" value="적격심사"></td>
						<td class="txt-center">
							<button href="javascript:" name="estmFrmPopup" class="component-button-s type-a">보기</button>
							<input type="hidden" class="component-input" id="P_ESTM_FRM_NO" name="P_ESTM_FRM_NO_H" value="">
							<input type="hidden" class="component-input" id="P_ESTM_FRM_NM" name="P_ESTM_FRM_NM_H" value="">
						</td>
						<td id="P_ESTM_FRM_NM_TEXT">${data.ESTM_FRM_NM}</td>
						<!-- <td class="txt-center">
							<button href="javascript:" name="estmFrmPopupC" class="component-button-s type-a">보기</button>
						</td> -->
					</tr> --%>
				</c:forEach>
				</tbody>
			</table>
		</div>
	<!-- bottom button -->
	<div class="bottom-buttons">
		<a href="javascript:" class="btn-bottom type-b" id="saveBtn">저장</a>
		<a href="javascript:" class="btn-bottom type-a" id="cnclBtn">취소</a>
	</div>
	<!-- //bottom button -->
</div>
<!-- //Detail -->
</form>
</div>

<form id="listFrm" method="POST">
	<input type="hidden" name="resourceName" value="${comFn:nvl(param.resourceName, resourceName)}" >
</form> 

<form id="downloadFrm" method="POST">
	<input type="hidden" name="P_FILE_GRP_NO">
	<input type="hidden" name="P_FILE_SN">
</form> 

<form id="popupFrm" method="POST"> 
	<input type="hidden" name="resourceName" value="${comFn:nvl(param.resourceName, resourceName)}" >
	<input type="hidden" name="P_ESTM_PROCD_SECD" >
	<input type="hidden" name="P_BUTTON_ID" >
</form>

