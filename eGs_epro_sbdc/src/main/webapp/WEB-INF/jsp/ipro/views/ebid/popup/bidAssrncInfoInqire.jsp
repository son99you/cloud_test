<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 입찰현황 > 입찰보증정보 팝업
 *
 * <pre>
 * ebid 
 *    |_ popup
 		      |_ bidAssrncInfoInqire.jsp
 * 
 * </pre>
 * @date : 2017. 06. 13
 * @version : 1.0
 * @author : 은우소프트 전상훈
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %> 
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %> 

<script type="text/javascript" src="${jsPath}/ipro/views/ebid/popup/bidAssrncInfoInqire.js"></script> 
 
<div class="pop_wrap">
	<div class="pop_header">
		<h1 class="tit">보증금조회</h1>
	</div>
	<div class="pop_container">
		<div class="view_wrap typeC">
			<div class="view_area">
				<table>
			    	<colgroup>
			        	<col style="width: 15%;">
						<col style="width: 35%;">
						<col style="width: 15%;">
						<col style="width: 35%;">
			        </colgroup>
			    	<tr class="line">
		               <th>공고번호</th>
		               <td>${bidAssrncInfoInqire.ANNC_NO}-${bidAssrncInfoInqire.ANNC_NGR}</td>
		               <th>공고일자</th>
		              	<td>
		                  	${comFn:formatDate(bidAssrncInfoInqire.ANNC_DT,'yyyyMMddHHmmss','yyyy-MM-dd')}
		              	</td>	     
		           </tr>
		           <tr>
		               <th>공고명</th>
		               <td colspan="3">${bidAssrncInfoInqire.BID_NM}</td>
		           </tr>
			    </table>
			</div>
			
			<div class="view_area typeB">
	    		<table id="tableList">
	    			<colgroup>
			        	<col style="width: 25%;">
						<col style="width: 25%;">
						<col style="width: 25%;">
						<col style="width: 25%;">
			        </colgroup>
			        <thead>
			        	<tr class="line" >
			        		<th class="txtc">보증납부구분</th>
			        		<th class="txtc">보증금액</th>
			        		<th class="txtc">보증번호</th>
			        		<th class="txtc">발급기관</th>
			        	</tr>
			        </thead>
					<tbody>
						<c:forEach var="data" items="${entrpsGrntyInfoList}" varStatus="status" >
							<tr>
								<td class="txtc">
									${data.BIDGR_SECD_NM }
								</td>
								<td class="txtr pr5">
									${comFn:formatMoney(data.BIDGR_AMT) }
								</td>
								<td>
									${data.BIDGR_NO }
								</td>
								<td>
									${data.BIDGR_AGNM }
								</td>
							</tr>
			        	</c:forEach>
	      			</tbody>   
				</table>
			</div>
			
    		<div class="tit_area">
				<h2 class="tit01">보증서</h2>
			</div>
           <div class="view_area fileViewer">
				<!-- 업로드 삽입. -->
				<script type="text/javascript">
					DEXT5UPLOAD.config.Mode = 'upload';
					DEXT5UPLOAD.config.Width = '100%';
					DEXT5UPLOAD.config.FolderNameRule = '/bid';
					var dext5Upload = new Dext5Upload("upload");
				</script>	            	
			</div>			
			
			<div class="btn_wrap view_btn">
		    	<button type="button" class="btn btn_m btn_del" id="closeBtn" >닫기</button>
		    </div>
		</div>    
	</div>
</div> <!--// content E-->
<%-- 파일 VIEW 폼 --%>
<form id="fileViewFrm" method="POST">
	<input type="hidden" id="P_FILE_GRP_NO" name="P_FILE_GRP_NO" value="${bidAssrncInfoInqire.FILE_GRP_NO}">
</form>