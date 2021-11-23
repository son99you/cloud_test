<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 공통 > 계약서식 > 계약서보기(팝업)
 *
 * <pre>
 * comm 
 *  |_popup
 *   |_contFormView.jsp
 * 
 * </pre>
 * @date : 2017. 07. 04
 * @version : 1.0
 * @author : 은우소프트 은잔디
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %>

<link rel="stylesheet" type="text/css" href="${cssPath}/ipro/windowPop.css">
<link rel="stylesheet" type="text/css" href="${cssPath}/ipro/template/pagingPlace.css">

<!-- <script src="/dhtmlxSuite_v51_pro/codebase/dhtmlx.js" type="text/javascript"></script> 
<link rel="stylesheet" type = "text/css" href="/dhtmlxSuite_v51_pro/codebase/dhtmlx.css"> 
<link rel="stylesheet" type = "text/css" href="/dhtmlxSuite_v51_pro/skins/terrace/dhtmlx.css"> -->
<script type="text/javascript" src="/dext5editor/js/dext5editor.js"></script>
<script type="text/javascript" src="${jsPath}/ipro/views/comm/popup/contFormView.js"></script>

<div id="windowPopup" class="w_800">
	<h3 class="windowTitle">계약서보기</h3>
	<div class="formLayer">
		<br/><br/> &nbsp;
			<table class="contable2">
				<tr>
					<td>
						<table class="contable">
							<tr>
								<td>
									<table>
								    	<colgroup>
											<col width="*">
								        </colgroup>
								        <%-- <tr>
							                <th scope="row">내용</th>
							               	<td colspan="3" style="min-height: 100px; vertical-align: top;">
							                      ${noticeBoardDetail.CN}
							                      <div id="editorObj" style="width :100%; height:300px; border:#909090 1px solid;"></div>
											</td>
							            </tr> 
							             <tr style="display: none;">   
							                <th>내용</th>                                                                                                              
							                <td colspan="3" >
								                <textarea id="P_CN" name="P_CN" style="width: 100%; height: 100%;"><c:out value=" ${noticeBoardDetail.CN}"></c:out></textarea>
											</td> 
							            </tr>    --%> 
							            <tr>
						                    <!-- <td colspan="3" style="min-height: 100px; vertical-align: top">
						                         <div id="editorObj" style="width:700px; height: 500px; border:#909090 1px solid;"> </div> 
											</td> -->
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
												<textarea id="P_FRMKTN" name="P_FRMKTN" style="width: 100%; height: 100%; display: none;"><c:out value=" ${contHistDetail.FRMKTN}"></c:out></textarea>
											</td>
						                </tr> 
							            <%-- <tr style="display: none;">  
							                <td colspan="3" >
							                	<textarea id="P_FRMKTN" name="P_FRMKTN" style="width: 100%; height: 100%;"><c:out value=" ${contHistDetail.FRMKTN}"></c:out></textarea>
											</td>  
							            </tr>    --%>
								    </table> 
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
	    <div class="T_btnLayer fr" style="margin-top:10px;">
			<button type="button" class="blueBtn L" id="closeBtn" onclick="window.close();">닫기</button>
	    </div>
	</div> 
</div> 

	
<form id="ubiPopupFrm" method="POST" > 
	<input type="hidden" name="P_JRF" value="test2.jrf">
	<input type="hidden" name="P_ARG" value="P_FRM_NO#${contHistDetail.FRM_NO}#P_VRSN#${contHistDetail.VRSN}#">  
	<input type="hidden" name="P_FRM_NO" value="${contHistDetail.FRM_NO}" >  
	<input type="hidden" name="P_VRSN" value="${contHistDetail.VRSN}" >  
</form>  
