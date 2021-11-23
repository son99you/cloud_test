<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">--%>
<%--
 * 통계관리 > 계약서첨부파일
 *
 * <pre>
 * comm 
 *  |__ popup
 *   |__	cntrctFileList.jsp.jsp
 * 
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %>

<link rel="stylesheet" type="text/css" href="${cssPath}/ipro/windowPop.css">
<link rel="stylesheet" type="text/css" href="${cssPath}/ipro/template/pagingPlace.css">

<div id="windowPopup" style="width: 460px;">
	<h3 class="windowTitle">&nbsp;계약첨부파일</h3>
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
											<col width="20%" align="left">
											<col width="80%" align="left">
										</colgroup>
										<tr height="29px">
											<td>첨부파일</td>
											<td colspan="3"   
												style="margin-top: 3px; line-height: 30px;">
												<a href="#">&nbsp;01.계약서_v0.5.pptx</a>
											</td> 
										</tr>
									</table>
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>

		<br>
		<div class="T_btnLayer fr">
			<button type="button" class="blueBtn L" id="closeBtn" onclick="window.close();">닫기</button>
		</div>
	</div> 
</div>
