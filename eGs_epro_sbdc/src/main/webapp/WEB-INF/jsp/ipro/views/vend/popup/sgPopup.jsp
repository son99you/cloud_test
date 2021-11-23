<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * sg설명 팝업창
 *
 * <pre>
 * vend 
 *    |_ sgPopup.jsp
 * 
 * </pre>
 * @date : 2017. 06. 13
 * @version : 1.0
 * @author : 은우소프트 홍찬일
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %> 
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %> 

<div class="pop_wrap">
	<div class="pop_header">
		<h1 class="tit">소싱그룹 설명</h1>
	</div> <!--// pop_header E -->

	<div class="pop_container">
		<div class="pop_list_wrap">
			<div class="pop_list_conts">
				<table>
		            <caption>목록</caption>
		            <colgroup>
		                <col width="15%">
		                <col width="15%">
		                <col width="*">
		            </colgroup>			
					<thead>
		                <tr>
	                    	<th>SG코드</th>
							<th>SG코드명</th>
							<th>코드설명</th>
		                </tr>
		            </thead>
					<tbody>
						 <tr  >
							<td class=" txtc">AF</td>
							<td class=" txtc">AFC</td>
							<td class=" txtl">AFC 설명란입니다.</td>
						</tr>
						<tr  >
							<td class="txtc">GA</td>
							<td class="txtc">전차선</td>
							<td class="txtl">전차선 설명란입니다.</td>
						</tr>
						<tr  >
							<td class=" txtc">GB</td>
							<td class=" txtc">송변전</td>
							<td class=" txtl">송변전 설명란입니다.</td>
						</tr>
						<tr  >
							<td class="txtc">GC</td>
							<td class="txtc">전력</td>
							<td class="txtl">전력 설명란입니다.</td>
						</tr>
						<tr  >
							<td class=" txtc">ME</td>
							<td class=" txtc">기계</td>
							<td class=" txtl">기계 설명란입니다.</td>
						</tr>
						<tr  >
							<td class="txtc">RA</td>
							<td class="txtc">선로</td>
							<td class="txtl">선로 설명란입니다.</td>
						</tr>
						<tr  >
							<td class=" txtc">RS</td>
							<td class=" txtc">전동차</td>
							<td class=" txtl">전동차 설명란입니다.</td>
						</tr>
						<tr  >
							<td class="txtc">SI</td>
							<td class="txtc">신호</td>
							<td class="txtl">신호 설명란입니다.</td>
						</tr>
						<tr  >
							<td class=" txtc">ZA</td>
							<td class=" txtc">광고홍보</td>
							<td class=" txtl">광고홍보 설명란입니다.</td>
						</tr>
						<tr  >
							<td class="txtc">ZB</td>
							<td class="txtc">역무</td>
							<td class="txtl">역무 설명란입니다.</td>
						</tr>
					</tbody> 
				</table>		
			</div>
			<div class="pop_list_bottom">
				<div class="pop_list_pager">
					<comTag:pagingIpro totalCount="100" pageNo="${comFn:nvl(param.P_PAGE_NO, 1)}" pageSize="${comFn:nvl(param.P_PAGE_SIZE, 100)}" clickPage="pageObj.clickPage"/>
				</div> <!--// list_pager E -->

				<div class="btn_wrap view_btn">
					<button type="button" class="btn btn_02 btn_close" id="closeBtn" onclick="javascript:window.close();">닫기</button>
				</div> <!--// btn_wrap E -->
			</div>			
		</div>
	</div>
</div>