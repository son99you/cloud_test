<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 낙찰자선정 > 적격심사 평가등록
 *
 * <pre>
 * ebid 
 *    |_ popup
               |_ properJdgmnEvlRegistForm.jsp
 * 
 * </pre>
 * @date : 2017. 06. 19
 * @version : 1.0
 * @author : 은우소프트 전상훈
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %> 
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %> 

<script type="text/javascript" src="${jsPath}/ipro/views/ebid/popup/properJdgmnEvlRegistForm.js"></script> 
 
<div class="pop_wrap">
	<div class="pop_header">
		<h1 class="tit">적격심사평가
		<c:if test="${ empty properJdgmnEntrpsDetail.ELGB_ESTM_SCR}">등록</c:if>
		<c:if test="${ not empty properJdgmnEntrpsDetail.ELGB_ESTM_SCR}">상세</c:if>
		</h1>
	</div> <!--// pop_header E -->
	<div class="pop_container">
		<div class="view_wrap typeA">
			<div class="view_area">
				<form id="registFrm" method="POST" enctype="multipart/form-data">
					<input type="hidden" name="P_ANNC_NO" value="${ properJdgmnEntrpsDetail.ANNC_NO }" >
					<input type="hidden" name="P_ANNC_NGR"  value="${ properJdgmnEntrpsDetail.ANNC_NGR }" >
					<input type="hidden" name="P_ROUND_NO"  value="${ properJdgmnEntrpsDetail.ROUND_NO }" >
					<input type="hidden" name="P_VEND_REG_NO" value="${ properJdgmnEntrpsDetail.VEND_REG_NO }">
					<input type="hidden" name="P_FILE_GRP_NO" value="${ properJdgmnEntrpsDetail.FILE_GRP_NO }">
					<input type="hidden" name="P_DEL_SN" value="">
					<input type="hidden" id="SAVE_YN" value="${SAVE_YN }">
					<table class="table">
				    	<colgroup>
				        	<col width="15%" />
				            <col width="35%" />
				            <col width="15%" />
				            <col width="35%" />
				        </colgroup>
				    	<tr>
			                <th scope="row">업체명</th>
			                <td>${properJdgmnEntrpsDetail.VEND_NM}</td>
			                <th scope="row" class="bullet_orange">적격심사총점</th>
			                <td>
								<input type="text" name="P_ELGB_ESTM_SCR" numeric value="${properJdgmnEntrpsDetail.ELGB_ESTM_SCR}">
							</td>
			            </tr>
			            <tr>
			               	<th scope="row">업체첨부파일</th>
							<td colspan="3">
		                  		<c:if test="${not empty bidAtchDocList }">
			                   		<c:forEach items="${bidAtchDocList }" var="data" varStatus="status">
			                   			<div style="height: 30px;"> 
			                   				<a href="javascript:pageObj.download('${data.FILE_SN}','${data.FILE_GRP_NO }');" class="attfile">${data.SYS_FILE_NM }</a>
			                    		</div>
			                    	</c:forEach> 
			                    </c:if>
			                    <c:if test="${empty bidAtchDocList}">
						        	<div style="height: 30px;"> 업체첨부파일 정보가 없습니다.</div>
						        </c:if>
			               </td>	
						</tr>
						<tr>
							<c:if test="${ empty properJdgmnEntrpsDetail.ELGB_ESTM_SCR}">
								<th>심사접수결과</th>
								<td colspan="3"><input type="file" name="P_FILE"></td>
							</c:if>
							<c:if test="${ not empty properJdgmnEntrpsDetail.ELGB_ESTM_SCR}">
								<th>심사접수결과</th>
								<td colspan="3">
									<c:if test="${not empty bidAtchDocList2}">
				                   		<c:forEach items="${bidAtchDocList2 }" var="data" varStatus="status">
				                   			<div style="height: 30px;"> 
				                   				<input type="file" name="P_FILE" class="fileReg" style="display: none;">
				                   				<a href="javascript:pageObj.download('${data.FILE_SN}','${data.FILE_GRP_NO }');" class="fileView">${data.SYS_FILE_NM }</a>
				                   				<button type="button" class="btn btn_02 btn_sch fileView" onclick="fileDel('${data.FILE_SN}')">삭제</button>
				                    		</div>
				                    	</c:forEach> 
				                    </c:if>
				                    <c:if test="${empty bidAtchDocList2}">
							        	<input type="file" name="P_FILE">
							        </c:if>
						        </td>
							</c:if>
						</tr>
				    </table>
			    </form>
			</div>
			
			<div class="btn_wrap view_btn">
				<button type="button" class="btn btn_02 btn_revise" id="registBtn" >저장</button>
    			<button type="button" class="btn btn_02 btn_close" id="closeBtn" >닫기</button>
			</div> <!--// btn_wrap E -->
		</div> <!--// view_wrap E -->
	</div> <!--// pop_container E -->
</div> <!--// pop_wrap E -->
<form id="downFrm" method="POST">
	<input type="hidden" name="P_FILE_SN">
	<input type="hidden" name="P_FILE_GRP_NO">
</form> 
