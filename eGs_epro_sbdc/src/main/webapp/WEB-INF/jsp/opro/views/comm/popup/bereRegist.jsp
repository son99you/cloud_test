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
	<h3 class="windowTitle">의견등록</h3>
    <form id="registFrm" method="POST">
	<fieldset>
        <legend>의견등록</legend>
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
	                    <td colspan="3"><input type="text" name="" value="" style="width: 300px;"></td>
	                </tr>
	                <tr>
	                    <th>등록자</th>
	                    <td><input type="text" name="" value="" style="width: 200px;"> </td>
	                    <th>이메일</th>
	                    <td><input type="text" name="" value="" style="width: 200px;"></td>
	                </tr>
	                <tr>
	                    <th>내용</th>
	                    <td colspan="3"><textarea id="lawordCdDetail" name="P_LAWORD_CD_DETAIL" style="padding-left: 10px;width: 98%;" rows="5" cols="" ></textarea></td>
	                </tr>
                </tbody>
            </table>
        </div>
        
        <div class="T_btnLayer fr">
            <button type="button" class="blueBtn L" onclick="if(!confirm('저장하시겠습니까?')){return false;} window.close();" id="saveBtn">저장</button>
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
												<input type="text" name="" value="" style="width: 300px;">
											</td>
										</tr>
										
										<tr height="29px">
											<td>
												<img src="${imagePath}/ipro/main/dot_blue.gif">
												등록자
											</td>
											<td>
												<input type="text" name="" value="">
											</td>
											<td>
												<img src="${imagePath}/ipro/main/dot_blue.gif">
												이메일
											</td>
											<td>
												<input type="text" name="" value="">
											</td>
										</tr>
										
										<tr height="29px">
											<td>
												<img src="${imagePath}/ipro/main/dot_blue.gif">
												내용
											</td>
											<td colspan="3" class="contd">
												<textarea id="lawordCdDetail" name="P_LAWORD_CD_DETAIL" style="padding-left: 10px;width: 95%;" rows="5" cols="" ></textarea>
												<!-- <textarea rows="10" style="width: 98%; height: 300px;"></textarea> -->
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
	    	<button type="button" class="blueBtn L" id="closeBtn" onclick="window.close();">저장</button>
			<button type="button" class="blueBtn L" id="closeBtn" onclick="window.close();">닫기</button>
	    </div>
	</div> 
</div> --%>
