<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%--
 *  입찰공고 의견등록 
 *
 * <pre>
 * elbi 
 *  |_popup
 *   | 	bidPblancOpinionRegistForm.jsp
 * 
 * </pre>
 * @date : 2017. 06. 20.
 * @version : 1.0
 * @author : 은우소프트 이주연 
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %>
 
<script type="text/javascript" src="${jsPath}/opro/views/comm/popup/popupBidRegistForm.js"></script>

<div id="windowPopup">
	<h3 class="windowTitle">입찰설명회 참가등록</h3>
        <div class="tableLayer">
            <p class="popSubTitle marginSet">입찰설명회 참가등록</p>
            <table class="table" summary="입찰설명회 참가등록 입니다.">
                <caption>입찰설명회 참가등록</caption>
                <colgroup>
                    <col width="15%">
                    <col width="35%">
                    <col width="15%">
                    <col width="35%">
                </colgroup>
                <tbody>
	                <tr class="line">
	                    <th scope="row" class="bullet_orange">업체명</th>
		                <td>(주)은우소프트
		                	<!-- <label class="blind">업체명</label>
		                        <input type="text"  value="(주)은우소프트"> -->
	                    </td>
	                    <th scope="row" class="bullet_orange">대표자명</th>
		                <td>정한규
		                	<!-- <label class="blind">대표자명</label>
		                        <input type="text" value="정한규" > -->
	                    </td>
	                </tr>
	                <tr>
	                    <th scope="row" class="bullet_orange">담당자명</th>
	                    <td>
		                	<label class="blind">담당자명</label>
		                        <input type="text" style="width: 60%;"/>
	                   	</td>
	                    <th scope="row" class="bullet_orange">전화번호</th>
	                    <td>
		                	<label class="blind">전화번호</label>
		                        <input type="text"  style="width: 60%;" />
	                    </td>
	                </tr>
	                <tr>
	                    <th scope="row" class="bullet_orange">이메일</th>
	                    <td colspan="3">
	                    	<label class="blind">이메일</label>
							 <input type="text" style="width: 85%;" />
	                    </td>
	                </tr>
                </tbody>
            </table>
        </div>
        
        <div class="T_btnLayer fr">
           	<button type="button" class="blueBtn L" id="participBtn">참가신청</button>
          	<button type="button" class="blueBtn L" id="closeBtn" onclick="window.close();">닫기</button>
       </div>
</div>         