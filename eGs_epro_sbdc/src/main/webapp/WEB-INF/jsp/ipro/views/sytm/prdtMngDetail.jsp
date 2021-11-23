<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 정보관리 > 상품관리 상세
 *
 * <pre>
 *  sytm 
 *    |_ prdtMngDetail.jsp
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

<script type="text/javascript" src="${jsPath}/ipro/views/sytm/prdtMngDetail.js"></script>
<script type="text/javascript" src="/raonkupload/js/raonkupload.js"></script>

<!-- 네비게이션 -->
<div class="area-detail">
	<div class="table-detail">
		<!-- Top -->
		<div class="nav_sec">

			<div class="btn-help" style="display:none;">
				<a href="javascript:helpPopup();">도움말</a>
			</div>
			
			<div class="option-left">
				<ul class="location">
					<li style="font-size: 30px; font-weight: 500;">상품관리 상세</li>
				</ul>
			</div>
			
			<div class="option-right">
				<ul class="location">
					<li class="home"><a href="#">홈</a></li>
					<li><a href="#">${myMenuList.bigMenuNm}</a></li>
					<li><a href="#">${myMenuSubList.smallMenuNm}</a></li>
				</ul>
			</div>
		</div>
	</div>
</div>
<!--//네비게이션 -->

<!-- Detail -->
<div class="area-detail">
	<form id="detailFrm" name="detailFrm" method="POST">
		<input type="hidden" name="resourceName" value="${comFn:nvl(param.resourceName, resourceName)}" >
		<input type="hidden" name="P_VEND_REG_NO" value="${prdtMngDetail.ITEM_NO }">
	
		<div class="table-detail">
			<!-- Top -->
			<div class="top-detail">
				<div class="type-fleft">
					<div class="contents-label">기본정보</div>
				</div>
			</div>
			<!-- //Top -->
			
			<table class="component-detail-table type-line-none">
				<colgroup>
					<col width="15%">
					<col width="35%">
					<col width="15%">
					<col width="35%">
				</colgroup>
				<tr>
					<th>품목코드</th>
					<td>${prdtMngDetail.ITEM_NO}</td>
					<th>품목명</th>
					<td>${comFn:formatBizNumber(prdtMngDetail.ITEM_NM)}</td>
				</tr>
				<tr>
					<th>규격</th>	
					<td colspan="3" >${prdtMngDetail.STND_NM}</td>	
				</tr>
				<tr>
					<th >품목설명</th>
					<td colspan="3"  rowspan="30">${prdtMngDetail.ITEM_DESC}</td>			   		                    
		        </tr>
			</table>
		</div>
		
		<div class="table-detail">
		<!--  Top -->
		<div class="top-detail">
			<div class="contents-label">첨부파일</div>
		</div>
		<!-- // Top -->
		<table class="component-detail-table type-file">
			<colgroup>
				<col width="15%">
				<col width="*">
			</colgroup>
			<tbody>
				<tr>
					<th>첨부파일</th>
					<td>
						<div class="fileViewer">
						<!-- 업로드 삽입. -->
							<script type="text/javascript">
								var raonkParam = {
						            Id: "uploadView1",
						            Mode: "view",
						            Width: "100%",
						            Height: "200px",
						            ButtonBarView: "open,download",
						            BorderStyle: "solid"
						        };
								var raonkUpload = new RAONKUpload(raonkParam);
							</script>
						</div>
						<div id="upload_filwInfo"></div>
					</td>
				</tr>
			</tbody>
		  </table>
		</div>
		
		<!-- bottom button -->
		<div class="bottom-buttons">
			<a href="javascript:"class="btn-bottom type-a" id="listBtn">목록</a>
		</div>
		<!-- // bottom button -->
	</form>
</div>
<!-- //Detail -->

<!-- LIST FORM -->
<form id="listFrm" method="POST"> 
	<input type="hidden" name="resourceName" value="${comFn:nvl(param.resourceName, resourceName)}" >
</form>
