<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">--%>
<%--
 * 공통 > 요구부서 조회(팝업)
 *
 * <pre>
 * comm 
 *  |_popup
 *   |_rqstDeptList.jsp
 * 
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %>

<%-- <link rel="stylesheet" type="text/css" href="${cssPath}/ipro/windowPop.css">
<link rel="stylesheet" type="text/css" href="${cssPath}/ipro/template/pagingPlace.css"> --%>

<div id="windowPopup">
	<h3 class="windowTitle">의견등록상세</h3>
    <form id="registFrm" method="POST">
	<fieldset>
        <legend>의견등록상세</legend>
        <div class="tableLayer">
            <p class="popSubTitle marginSet"></p>
            <table class="table">
                <caption>신청개요</caption>
                <colgroup>
                   	<col width="15%" align="left">
					<col width="35%" align="left">
					<col width="15%" align="left">
					<col width="35%" align="left">
                </colgroup>
                <tbody>
	                <tr class="line">
	                    <th>제목</th>
	                    <td colspan="3">계약금관련 문의 사항</td>
	                </tr>
	                <tr>
	                    <th>등록자</th>
	                    <td>사용자4 </td>
	                    <th>이메일</th>
	                    <td>user4@eunwoosoft.com</td>
	                </tr>
	                <tr>
	                    <th>내용</th>
	                    <td colspan="3">계약금관련하여 질문드립니다.<br>알마면 될까요? </td>
	                </tr>
                </tbody>
            </table>
        </div>
        
        <div class="T_btnLayer fr">
            <button type="button" class="blueBtn L"  onclick="window.close();">닫기</button>
        </div>
    </fieldset>
    </form>
</div>



<%-- <div id="windowPopup" class="w_500">
	<h3 class="windowTitle">사전규격공개 의견등록</h3>
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
											<col width="15%" align="left">
											<col width="35%" align="left">
											<col width="15%" align="left">
											<col width="35%" align="left">
										</colgroup>
										<tr height="29px">
											<td>
												<img src="${imagePath}/ipro/main/dot_blue.gif">
												제목
											</td>
											<td colspan="3" class="contd">
												계약금관련 문의 사항
											</td>
										</tr>
										
										<tr height="29px">
											<td>
												<img src="${imagePath}/ipro/main/dot_blue.gif">
												등록자
											</td>
											<td>
												사용자4
											</td>
											<td>
												<img src="${imagePath}/ipro/main/dot_blue.gif">
												이메일
											</td>
											<td>
												user4@eunwoosoft.com
											</td>
										</tr>
										
										<tr height="29px">
											<td>
												<img src="${imagePath}/ipro/main/dot_blue.gif">
												내용
											</td>
											<td colspan="3" class="contd">
												계약금관련하여 질문드립니다.
												알마면 될까요?
											</td>
										</tr>
										
									</table>
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
			
           
	    <div class="T_btnLayer fr" style="margin-top: 20px;">
			<button type="button" class="blueBtn L" id="closeBtn" onclick="window.close();">닫기</button>
	    </div>
	</div> 
</div> --%>
