<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 결재선관리 목록
 *
 * <pre>
 * info 
 *    |_ infoApprlineList.jsp
 * 
 * </pre>
 * @date : 2018. 03. 08
 * @version : 1.0
 * @author : 은우소프트 맹경열
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %> 
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %> 

<script type="text/javascript" src="${jsPath}/ipro/views/info/infoApprSet.js"></script>
<script type="text/javascript" src="${jsPath}/ipro/views/info/jquery.tablednd.js"></script>


<div class="contents_area">
	<div class="tit_wrap">
		<h2 class="tit">결재설정</h2>
		<ul class="step_area">
			<li><a href="#">${comFn:fmIso2Euc(myMenuList.bigMenuNm)}</a></li>
			<li><a href="#">${comFn:fmIso2Euc(myMenuSubList.smallMenuNm)}</a></li>
		</ul>			
	</div> <!--// tit_wrap E -->
			
	<form id="registFrm" class="search_form" method="POST" action="${contextPath}/info/infoApprSet.do">
		<br>
		<br>
		<div class="sub_tit_wrap">
			<h3 class="sub_tit">결재 사용 여부</h3>
		</div>
		<div class="board_write">
			<table>
				<caption>결재 사용 여부</caption>
				<colgroup>
					<col style="width: 10%;">
					<col style="width: 20%;">
					<col style="width: 10%;">
					<col style="width: 20%;">
					<col style="width: 10%;">
					<col style="width: 20%;">
				</colgroup>
				<tbody>
					<tr>
						<th scope="row">
							전자계약(발신)
						</th>
						<td class="contd">
                   			<comTag:cmmnCdValueRadio name="P_CONT_APPR_YN"  selectKey="${comFn:nvl(apprYnDetail.CONT_APPR_YN,'N')}" list="{'Y':'예', 'N':'아니오'}"/>
						</td>
						<th scope="row">
							전자계약(수신)
						</th>
						<td class="contd">
                   			<comTag:cmmnCdValueRadio name="P_RCV_CONT_APPR_YN"  selectKey="${comFn:nvl(apprYnDetail.RCV_CONT_APPR_YN,'N')}" list="{'Y':'예', 'N':'아니오'}"/>
						</td>
					</tr>
					<tr>
						<th scope="row">
							서면계약
						</th>
						<td class="contd">
                   			<comTag:cmmnCdValueRadio name="P_WRTN_CONT_APPR_YN"  selectKey="${comFn:nvl(apprYnDetail.WRTN_CONT_APPR_YN,'N')}" list="{'Y':'예', 'N':'아니오'}"/>
						</td>
						<%-- <th scope="row">
							서면수신계약
						</th>
						<td class="contd">
                   			<comTag:cmmnCdValueRadio name="P_WRTN_RCV_CONT_APPR_YN"  selectKey="${comFn:nvl(apprYnDetail.WRTN_RCV_CONT_APPR_YN,'N')}" list="{'Y':'예', 'N':'아니오'}"/>
						</td> --%>
						<th scope="row">
							계약서서식
						</th>
						<td class="contd">
                   			<comTag:cmmnCdValueRadio name="P_FRM_APPR_YN"  selectKey="${comFn:nvl(apprYnDetail.FRM_APPR_YN,'N')}" list="{'Y':'예', 'N':'아니오'}"/>
						</td>
					</tr>
					<tr>
						
						
						<th scope="row">
							자동연장
						</th>
						<td class="contd">
                        	<comTag:cmmnCdValueRadio name="P_AUTO_EXTN_APPR_YN"  selectKey="${comFn:nvl(apprYnDetail.AUTO_EXTN_APPR_YN,'N')}" list="{'Y':'예', 'N':'아니오'}"/>
						</td>
						<th scope="row">
							입찰공고
						</th>
						<td class="contd">
                        	<comTag:cmmnCdValueRadio name="P_EBID_APPR_YN"  selectKey="${comFn:nvl(apprYnDetail.EBID_APPR_YN,'N')}" list="{'Y':'예', 'N':'아니오'}"/>
						</td>
					</tr>
					<tr>
						
						<th scope="row">
							낙찰
						</th>
						<td class="contd">
                        	<comTag:cmmnCdValueRadio name="P_SBID_APPR_YN"  selectKey="${comFn:nvl(apprYnDetail.SBID_APPR_YN,'N')}" list="{'Y':'예', 'N':'아니오'}"/>
						</td>
						<th scope="row">
							유찰
						</th>
						<td class="contd">
                        	<comTag:cmmnCdValueRadio name="P_FBID_APPR_YN"  selectKey="${comFn:nvl(apprYnDetail.FBID_APPR_YN,'N')}" list="{'Y':'예', 'N':'아니오'}"/>
						</td>
					</tr>
					<tr>
						
						<th scope="row">
							공문발신
						</th>
						<td class="contd">
                        	<comTag:cmmnCdValueRadio name="P_OFCL_DOCU_APPR_YN"  selectKey="${comFn:nvl(apprYnDetail.OFCL_DOCU_APPR_YN,'N')}" list="{'Y':'예', 'N':'아니오'}"/>
						</td>
						<th scope="row">
							공문수신
						</th>
						<td class="contd">
                        	<comTag:cmmnCdValueRadio name="P_RCV_OFCL_DOCU_APPR_YN"  selectKey="${comFn:nvl(apprYnDetail.RCV_OFCL_DOCU_APPR_YN,'N')}" list="{'Y':'예', 'N':'아니오'}"/>
						</td>
					</tr>
					<!-- <tr>
						
						<th scope="row">
						</th>
						<td class="contd">
						</td>
					</tr> -->
				</tbody>
			</table>
		</div>
		<br>
		<div class="sub_tit_wrap">
			<h3 class="sub_tit">결재순서</h3>
		</div>
		<div class="board_write">
			<table>
				<caption>결재 사용 여부</caption>
				<colgroup>
					<col style="width: 10%;">
					<col style="width: 20%;">
					<col style="width: 10%;">
					<col style="width: 20%;">
					<col style="width: 10%;">
					<col style="width: 20%;">
				</colgroup>
				<tbody>
					<tr>
						<th scope="row">
							결재순서
						</th>
						<td class="contd" colspan="3">
                   			<comTag:cmmnCdValueRadio name="P_SIGN_ORD"  selectKey="${comFn:nvl(apprYnDetail.SIGN_ORD,'A')}" list="{'A':'협력사 서명전', 'B':'협력사 서명후'}"/>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
		<div class="list_bottom">
			<div class="list_btn">
				<button type="button" class="btn btn_02 btn_blue" id="regBtn">저장</button>
			</div> <!--// btn_wrap E -->
		</div> <!--// list_bottom E -->		
	</form>

</div> 

<%-- DETAIL FORM --%>
<form id="deleteFrm" method="POST" > 
	<input type="hidden" name="resourceName" value="${ param.resourceName }" >
	<input type="hidden" id="resourceDesc" name="resourceDesc" value="${param.resourceDesc}">
	<input type="hidden" name="P_APPL_NO" id="P_APPL_NO" >
</form>

<form id="popupFrm" method="POST" > 
	<input type="hidden" name="resourceName" value="${ param.resourceName }" >
	<input type="hidden" name="setMulti" value="Y">
	<input type="hidden" id="P_APPL_SE" name="P_APPL_SE">
</form>
<form id="viewFrm" method="POST" >
	<input type="hidden" id="resourceName" name="resourceName" value="${param.resourceName}">
	<input type="hidden" id="resourceDesc" name="resourceDesc" value="${param.resourceDesc}">
</form>
