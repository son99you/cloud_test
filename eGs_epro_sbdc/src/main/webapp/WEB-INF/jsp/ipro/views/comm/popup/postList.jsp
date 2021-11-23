<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">--%>
<%--
 * 공통 >우편번호팝업 조회(팝업)
 *
 * <pre>
 * comm 
 *  |_popup
 *   |_postList.jsp
 * 
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %>

<script type="text/javascript" src="${jsPath}/ipro/views/comm/popup/postList.js"></script>
<div class="pop_sp_sec"> 
	<h1 class="sp_tit">우편번호목록</h1>
	<div class="sp_cont">
		<form id="searchFrm" class="search_form" method="POST" >
			<input type="hidden" id="P_PAGE_NO" name="P_PAGE_NO" value="${comFn:nvl(param.P_PAGE_NO, 1)}">
			<input type="hidden" id="P_PAGE_SIZE" name="P_PAGE_SIZE" value="${comFn:nvl(param.P_PAGE_SIZE, 10)}">
			<input type="hidden" id="setMulti" name="setMulti" value="${param.setMulti}">
			<div class="sch_box filter_sch">
				<dl class="first">
					<dt style="width: 18%;">도로명(읍면동)</dt>
					<dd><input type="text" id="P_BEOBJEONGDONG_S" name="P_BEOBJEONGDONG_S" value="${param.P_BEOBJEONGDONG_S}"  class="input w100p"  placeholder="도로명(읍면동)" /></dd>
					<dt style="width: 18%;">건물본번(번지)</dt>
					<dd><input type="text" id="P_JIBEON_NO_BON_S" name="P_JIBEON_NO_BON_S" value="${param.P_JIBEON_NO_BON_S }" class="input w100p"  placeholder="건물본번(번지)" /></dd>
				</dl>	
				<dl>
					<dt style="width: 18%;">공동주택명</dt>
					<dd><input type="text" id="P_BUILD_NAME_S" name="P_BUILD_NAME_S" value="${param.P_BUILD_NAME_S }" class="input w100p"  placeholder="공동주택명" /></dd>
				</dl>
			</div> <!--// view_area E -->
			<button type="button" class="btn ty03 btn_sch" id="searchBtn">조회</button>
		</form>
		
		<!-- Data Total Count -->
		<div class="list_top">
        	<p class="total">총 <span>${comFn:nvl(postListTotcnt, 0)}</span>건</p>
    	</div> <!--// pop list_top E -->
	    	
		<table class="list_tb">
            <caption>우편번호목록</caption>
            <colgroup>
                <col width="5%">
                <col width="15%">
                <col width="*">
                <col width="25%">
            </colgroup>			
			<thead>
                <tr>
                	<c:choose>
	                	<c:when test="${param.setMulti eq 'Y'}">
							 <th class="noBg">
							 	<label for="postAllCbx" class="blind">체크박스</label>
		                    	<input type="checkbox" id="postAllCbx" name="postCbx" onclick="FwkCmmnUtil.setAllCheck('postAllCbx','postCbx');">
		                    </th>
						</c:when>
						<c:otherwise>
							 <th class="noBg">번호</th>
						</c:otherwise>
					</c:choose>
                    <th>우편번호</th>
                    <th>주소</th>
                    <th>법정동, 공동주택명</th>
                </tr>
            </thead>
			<tbody>
				<c:if test="${comFn:nvl(postListTotcnt, 0) == 0}">
					<tr>
						<td colspan="4" style="text-align: center;">검색 조건에 해당하는 데이터가 존재하지 않습니다.</td>
					</tr>
				</c:if>
				<c:if test="${postListTotcnt > 0}">
					<c:forEach var="data" items="${postList}" varStatus="status" >
						<tr class="row" <c:if test="${param.setMulti ne 'Y'}">onclick="setPostInfo('${data.BUILD_MAN_N}','${data.ZIPCODE }','${data.BEOBJEONGDONG}','${data.JIBEON_NO_BON }','${data.DOROMYEONG}','${data.GEONMUL_NO_BON }','${data.BUILD_NAME }','${data.ADDRESS}','${data.ADDRESS1}');"</c:if> style="cursor: pointer;">
							<c:if test="${param.setMulti eq 'Y'}">
								<td class="txtc">
									<label for="postCbx${status.count }" class="blind">체크박스</label>
									<input type="checkbox" id="postCbx${status.count }" name="postCbx">
								</td> 
							</c:if>
							<c:if test="${param.setMulti ne 'Y'}"> 
								<td class="no">${data.RNUM}&nbsp;</td>
							</c:if>
							<td class="txtc">
								${comFn:formatPostNumber(data.ZIPCODE)}&nbsp;
								<input type="hidden" name="P_ZIPCODE" value="${data.ZIPCODE}">
								<input type="hidden" name="P_BUILD_MAN_N" value="${data.BUILD_MAN_N}">
								<input type="hidden" name="P_BEOBJEONGDONG value="${data.BEOBJEONGDONG}">
								<input type="hidden" name="P_JIBEON_NO_BON value="${data.JIBEON_NO_BON}">
							</td>
							<td class="txtl pl5">
								${data.ADDRESS}&nbsp;${data. ADDRESS1}
								<input type="hidden" name="P_DOROMYEONG" value="${data.DOROMYEONG}"> 
								<input type="hidden" name="P_GEONMUL_NO_BON" value="${data.GEONMUL_NO_BON}"> 
								<input type="hidden" name="P_ADDRESS" value="${data.ADDRESS}"> 
								<input type="hidden" name="P_ADDRESS1" value="${data.ADDRESS1}"> 
							</td>
							<td class="txtl pl5">
								${data.BUILD_NAME}&nbsp; 
								<input type="hidden" name="P_BUILD_NAME" value="${data.BUILD_NAME}"> 
							</td>
						</tr>
					</c:forEach>
				</c:if>
			</tbody> 
		</table>
			
		<!-- Data Paging -->
		<div class="paging">
			<comTag:pagingIpro totalCount="${comFn:nvl(postListTotcnt,0)}" pageNo="${comFn:nvl(param.P_PAGE_NO, 1)}" pageSize="${comFn:nvl(param.P_PAGE_SIZE, 10)}" clickPage="pageObj.clickPage"/>
		</div>
			
		<div class="btm_btns">
			<c:if test="${param.setMulti eq 'Y'}"> 
       			<button type="button" class="btn ty02"   id="choiceBtn">선택</button>
       		</c:if>
			<button type="button" class="btn ty04" id="closeBtn" onclick="window.close();">닫기</button>
		</div>
	</div> <!--// pop_container E -->
</div> <!--// pop_wrap E -->