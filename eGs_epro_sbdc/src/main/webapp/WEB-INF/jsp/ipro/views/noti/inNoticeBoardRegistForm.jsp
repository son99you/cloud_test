<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 *  내부 공지사항 등록 페이지
 *
 * <pre>
 * noti 
 *    |_ inNoticeBoardRegistForm.jsp
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
<script type="text/javascript" src="${jsPath}/ipro/views/noti/inNoticeBoardRegistForm.js"></script> 
 
<div class="content">
	<form id="registFrm" method="POST" enctype="multipart/form-data" >
		<input type="hidden" name="resourceDesc" value="${ param.resourceDesc}" >
		<input type="hidden" name="resourceName" value="${ param.resourceName }" >
		<input type="hidden" id="P_PAGE_NO" name="P_PAGE_NO" value="1">
		<input type="hidden" id="P_PAGE_SIZE" name="P_PAGE_SIZE" value="10">
		<!-- <h3>내부공지사항 등록</h3> -->

		<div class="conts_wrap">
			<div class="inner">
				<div class="tit_wrap">
					<h3 class="tit">내부공지사항 등록</h3>
					<ul class="step_wrap">
						<li><a href="#">${myMenuList.bigMenuNm}</a></li>
						<li><a href="#">${myMenuSubList.smallMenuNm}</a></li>
					</ul> <!--// step_wrap E -->
				</div> <!--// tit_wrap E -->
				<div class="view_wrap typeA">
					<h4 class="tit">내부 공지사항정보 등록</h4>
					<br>
					<div class="view_area">
						<table>
					    	<colgroup>
					        	<col width="15%">
								<col width="35%">
								<col width="15%">
								<col width="35%">
					        </colgroup>
				    		<tr class="line">
			                    <th>제목</th>
			                    <td colspan="3">
			                     	<input type="text" id="P_SJ_NM" name="P_SJ_NM"  maxlength="200"  class="w_95p">
				                </td>
			                </tr>
				        	<tr>
					            <th>등록자</th> 
					            <td>     
					            	<input type="text"  id="P_USER_NM" name="P_USER_NM"  value="${loginResult.USER_NM}" maxlength="10" readonly  class="w_95p disabled" >
					            	<input type="hidden"  id="P_USER_ID" name="P_USER_ID"  value="${loginResult.USER_ID}" maxlength="10"  readonly  class="w_95p disabled" >
			                	</td>
					            <th>이메일</th>
					            <td>
			        				<input type="text"  id="P_EMAIL"  name="P_EMAIL"  value="${loginResult.EMAIL_ADRES}" style="width: 88%;" readonly class="w_95p disabled" >
					            </td>  
					        </tr> 
			                <tr>
			                    <th>내용</th> 
			                    <td colspan="3" style="min-height: 100px; vertical-align: top">
			                    	<div style="width:100%;height:450px;">  
										    <!-- 에디터 생성 -->  
										    <script type="text/javascript">
										    	DEXT5.config.Width = "100%";
			                            		DEXT5.config.Height = "100%";
			                           			DEXT5.config.SkinName = "blue";
			                           			DEXT5.config.Runtimes = "html5";
										        new Dext5editor('editor1');
										    </script>       
									</div>
									<textarea id="P_CN" name="P_CN" style="width: 100%; height: 100%; display: none;" ></textarea>
								</td>
			                </tr>
					    </table>
					</div>
				<br>
				</div>
				<div class="btn_wrap view_btn fr">
			       <button type="button" class="btn btn_02 btn_revise" id="saveBtn">저장</button>
					<button type="button" class="btn btn_02 btn_sch" id="listBtn">취소</button>
				</div>				
			</div>
		</div>
	</form>
</div> <!--// content E-->


<form id="listFrm" method="POST" > 
	<input type="hidden" name="resourceDesc" value="${ param.resourceDesc}" >
	<input type="hidden" name="resourceName" value="${ param.resourceName }" >
</form>
<form id="saveFrm" method="POST" > 
	<input type="hidden" name="resourceDesc" value="${ param.resourceDesc }" >
	<input type="hidden" name="resourceName" value="${ param.resourceName }" >
</form>


