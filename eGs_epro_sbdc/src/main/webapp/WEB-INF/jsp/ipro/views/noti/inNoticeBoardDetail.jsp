<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 *  내부공지사항 상세 페이지
 *
 * <pre>
 * noti 
 *    |_ inNoticeBoardDetail.jsp
 * 
 * </pre>
 * @date : 2017. 06. 14
 * @version : 1.0
 * @author : 은우소프트 이주연
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %> 
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %>

<%-- <link rel="stylesheet" type="text/css" href="${cssPath}/ipro/template/buttonStyle.css">
<link rel="stylesheet" type="text/css" href="${cssPath}/ipro/template/pagingPlace.css">
<link rel="stylesheet" type="text/css" href="${cssPath}/ipro/bodyBasic.css"> --%>

<script type="text/javascript" src="/dext5editor/js/dext5editor.js"></script>
<script type="text/javascript" src="${jsPath}/ipro/views/noti/inNoticeBoardDetail.js"></script> 
 
<div class="content">
	<!-- <h3>내부공지사항 상세</h3> -->

	<div class="conts_wrap">
		<div class="inner">
			<div class="tit_wrap">
				<h3 class="tit">내부공지사항 상세</h3>
				<ul class="step_wrap">
					<li><a href="#">${myMenuList.bigMenuNm}</a></li>
					<li><a href="#">${myMenuSubList.smallMenuNm}</a></li>
				</ul> <!--// step_wrap E -->
			</div> <!--// tit_wrap E -->
			<div class="view_wrap typeA">
				<h4 class="tit">내부 공지사항정보</h4>
				<div class="view_area">
					<table>
				    	<colgroup>
				        	<col width="150px">
							<col width="300px">
							<col width="150px">
							<col width="300px">
				        </colgroup>
				    		<tr class="line">
			               <th>제목</th>
			               <td colspan="3">
			                   ${inNoticeBoardDetail.SJ_NM}
			               </td>
			            </tr>
			        	<tr>
				            <th>등록자</th> 
				            <td>     
				           	 	${inNoticeBoardDetail.REGISTER_NM}
			               	</td>
				            <th>이메일</th>
				            <td>
				           	 	${inNoticeBoardDetail.EMAIL_ADRES}
				            </td>  
				        </tr>
			            <tr>
			                <th scope="row">내용</th>
			               	<td colspan="3" style="min-height: 100px; vertical-align: top; padding-top: 5px;">
			                   	<div style="width:100%;height:450px;">  
			                       <script type="text/javascript">
								    	DEXT5.config.Width = "100%";
				                        DEXT5.config.Height = "100%";
				                        DEXT5.config.SkinName = "blue";
				                        DEXT5.config.Mode = "view";
				                        DEXT5.config.Runtimes = "html5";
								        new Dext5editor('editor1');
								   </script>
								</div> 
								<textarea id="P_CN" name="P_CN" style="width: 100%; height: 100%; display: none;"><c:out value=" ${inNoticeBoardDetail.CN}"></c:out></textarea>
							</td>
			            </tr> 
				    </table>
			    </div>
		    <!-- 첨부파일 상세 -->
			</div>
			<div class="btn_wrap view_btn fr">
				<button type="button" class="btn btn_02 btn_revise" id="ubiBtn">출력</button>
		    	<button type="button" class="btn btn_02 btn_revise" id="modifyBtn">수정</button>
		        <button type="button" class="btn btn_02 btn_revise" id="deleteBtn">삭제</button>
		        <button type="button" class="btn btn_02 btn_sch" id="listBtn">목록</button>
		    </div>			
		</div>
	</div>
</div> <!--// content E-->

<form id="listFrm" method="POST" > 
	<input type="hidden" name="resourceName" value="${ param.resourceName }" >
	<input type="hidden" name="resourceDesc" value="${ param.resourceDesc }" >
</form>
<form id="updtFrm" method="POST" > 
	<input type="hidden" name="resourceName" value="${ param.resourceName }" >
	<input type="hidden" name="resourceDesc" value="${ param.resourceDesc }" >
	<input type="hidden" name="P_NTT_SN" value="${inNoticeBoardDetail.NTT_SN}" >  
	<input type="hidden" name="P_USER_ID" value="${sessionScope.loginResult.USER_ID}" >  
</form> 
<form id="downloadFrm" method="POST" > 
	<input type="hidden" name="P_ATCHMNFL_SN">  
	<input type="hidden" name="resourceDesc" value="${ param.resourceDesc }" >
	<input type="hidden" name="resourceName" value="${ param.resourceName }" >
</form>
<form id="ubiPopupFrm" method="POST" > 
	<input type="hidden" name="P_JRF" value="test.jrf">
	<input type="hidden" name="P_ARG" value="P_NTT_SN#${inNoticeBoardDetail.NTT_SN }#"> 
	<input type="hidden" name="P_NTT_SN" value="${inNoticeBoardDetail.NTT_SN}" >  
</form>