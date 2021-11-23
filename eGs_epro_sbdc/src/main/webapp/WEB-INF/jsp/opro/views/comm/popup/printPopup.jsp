<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 통합게시판 > 출력 팝업
 *
 * <pre>
 * cmmn 
 *    |_ cmmnPopupEmplyrInqireList.jsp
 * 
 * </pre>
 * @date : 2016. 10. 16. 
 * @version : 1.0
 * @author : 은우소프트 손연우
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %>

<%-- <script type="text/javascript" src="${jsPath}/comUtil.js"></script>  
<script type="text/javascript" src="${jsPath}/views/cmmn/popup/cmmnPopupDeptInqireList.js"></script> --%>
<style type="text/css" media="print">    
    @page{  size:auto; margin : 0mm;  } 
    td {FONT: 10pt Gulim, 굴림,Helvetica,Aria; line-height: 25px; }
p.top {margin-top:100}
p.middle{margin-top:400}
.firsttitle {font-family: '굴림', '굴림체', 'Arial'; font-size: 20pt; font-weight: bold; color: #000000;line-height: 50px; text-align:center; margin-top:20px}
.firstGraph{font-family: '굴림', '굴림체', 'Arial'; font-size: 10pt; font-weight: bold; color: #000000; line-height: 20px; background-color:FFFFFF;	}
.firstGraph02{font-family: '굴림', '굴림체', 'Arial'; font-size: 10pt; color: #000000; line-height: 20px}
.td_line{ 	           
           border-top: 2px solid #000000;
           border-right: 2px solid #000000;
           border-bottom: 2px solid #000000;
           border-left: 2px solid #000000;
           }    
.td_line02{            
           padding-left:2px;
           border-top: 0px solid #000000;
           border-right: 1px solid #000000;
           border-bottom: 1px solid #000000;
           border-left: 1px solid #000000;}   
.td_line03{ 
           font-family:'굴림';
		   font-size:19;
		   font-weight: bold;
		   color: #000000;
           padding-left:2px;
           border-right: 2px solid #000000;

           }  
.td_line04{ 
           padding-left:2px;
           border-right: 1px solid #000000;
           border-bottom: 1px solid #000000;
           }  
.td_line05{ 
           padding-left:2px;
           border-right: 1px solid #000000;
           }      
.td_line06{ 
           padding-left:2px;
           border-bottom: 1px solid #000000;
           }   
.td_line07{ 
           padding-left:2px;
           border-bottom: 0px solid #000000;
           }   
.td_line10{ 
           padding-left:2px;
           border-top: 1px solid #000000;
           }          
.td_line11{ 
           padding-left:2px;
           border-top: 2px solid #000000;
           }                                                                                                         
.td_line11_top{ 
           padding-left: 2px;
		   border-right: 2px solid #000000;
           border-left:  2px solid #000000;
           border-bottom: 1px solid #000000;
           }  
.td_line11_mid{ 
           padding-left: 2px;
           border-top:   1px solid #000000;
		   border-right: 2px solid #000000;
           border-left:  2px solid #000000;
           border-bottom: 1px solid #000000;
           }  
.td_line11_bot{ 
           padding-left: 2px;
		   border-right: 2px solid #000000;
           border-left:  2px solid #000000;
           border-bottom: 2px solid #000000;
           }
.tr_height{ 
           height: 30px;
           }
    
</style>

<!-- <script type="text/javascript">

  function print_cont(){
	
	var initBody = document.body.innerHTML;
	 var initBody = $("#contHtml").val();
	 
	 alert("initBody 1 ::::: " + initBody);
	 document.getElementById('print').innerHTML =  document.getElementById('contHtml').value;
	 
	 $("#print").html($("#contHtml").val());
	 
	 alert("contHtml.val() ::::: " + $("#contHtml").val());
	  
	 alert("print.html() ::::: " + $("#print").html()); 
	 
	 
	 window.print(); 
	 var initBody = document.body.innerHTML;
	 
	 document.getElementById('print').innerHTML =  document.getElementById('contHtml').value;
	  
	 window.onbeforeprint = function(){
		initBody =  document.getElementById('print').innerHTML;
	 };
	 window.onafterprint = function(){
		document.body.innerHTML = initBody;
	 }; 
	 
	 window.print();
}  -->
 
<!-- /* function print_cont(){
	
	 var initBody = document.body.innerHTML;
	 
	 document.getElementById('print').innerHTML =  document.getElementById('contHtml').value;
	  
	window.onbeforeprint = function(){
		initBody =  document.getElementById('print').innerHTML;
	 };
	 window.onafterprint = function(){
		document.body.innerHTML = initBody;
	 }; 
	 
	 window.print();
} */

 
</script>  --> 

<form>
<%-- <input type="hidden" name="contHtml" id="contHtml" value="${ printPopup }"/> --%>
<div id="print" style="width: 100%;">${ printPopup }</div>
</form>
<%-- <div id="pop-wrap">
<!-- <div class="content"> -->
	<div class="pop-head">
		<div class="p-inner">
			
			<h1 class="pop-title" >부서 목록</h1>
			
		</div> <!--// p-inner E-->
	</div> <!--// pop-head E-->

<!-- <div class="content" style="padding: 0 20px 0 20px;">
	<h3 class="windowTitle">내부 담당자 조회</h3>
	<h2 class="tit">부서 목록</h2>
	<div class="formLayer"> -->

		<form id="searchFrm" class="search_form" method="POST" action="${contextPath}/buyer/prcm/popup/emplyrInqireList.do">
		<br><br>
			<input type="hidden" id="P_PAGE_NO" name="P_PAGE_NO" value="${comFn:nvl(param.P_PAGE_NO, 1)}">
			<input type="hidden" id="P_PAGE_SIZE" name="P_PAGE_SIZE" value="${comFn:nvl(param.P_PAGE_SIZE, 10)}">
			<input type="hidden" id="P_CTRTC" name="P_CTRTC" value="${param.P_CTRTC }">
			<input type="hidden" id="btnId" name="searchGbnId" value="${param.searchGbnId}">
			<input type="hidden" id="gbnDept" name="searchGbnDept" value="${param.searchGbnDept }">
			<input type="hidden" id="setMulti" name="setMulti" value="${param.setMulti}">

            <fieldset>
				<div class="search-tb"  style="padding-left: 5%; padding-right: 5%;">
					<table>
						<caption>부서 조회</caption>
						<colgroup>
							<col style="width: 120px;">
							<col style="width: 36.2%;">
							<col style="width: 120px;">
							<col style="width: auto;">
						</colgroup>
						<tbody>
						<tr>
							<th scope="row"><label for="P_SJ_NM_S">성명</label></th>
							<td><input type="text" name="P_SJ_NM_S" id="P_SJ_NM_S" value="${param.P_SJ_NM_S}" maxlength="600"></td>
							<th scope="row"><label for="P_REGISTER_NM_S">부서명</label></th>
							<td><input type="text" name="P_REGISTER_NM_S" id="P_REGISTER_NM_S" value="${param.P_REGISTER_NM_S}" maxlength="600"></td>
						</tr>
					</table>
					<div class="button-area">
						<button type="button" class="btn btn_search" id="searchBtn" title="조회">조회</button>
					</div> <!--// button-area E-->
				</div> <!--// search-tb E-->
            </fieldset>		
          </form>	
            
            <div class="searchFormLayerLine">
	            <span class="search_bullet" style="width: 68px;">성명</span>
	            <span class="contents_search_bar"></span>
	            	<label for="P_USER_NM_S" class="blind">성명</label>
	             	<input type="text" class="lineTxt" id="P_USER_NM_S" name="P_USER_NM_S" style="width: 160px;" value="${param.P_USER_NM_S}" >
	            <c:if test="${ empty param.searchGbnDept  }">
			        <span class="search_bullet" style="width: 68px;">부서명</span>
			        <span class="contents_search_bar"></span>
			        	<label for="P_ORG_NM_S" class="blind">부서명</label>
			        	<input type="text" class="lineTxt" id="P_ORG_NM_S" name="P_ORG_NM_S" style="width: 160px;" value="${param.P_ORG_NM_S}" >
		        </c:if>
		        <c:if test="${ not empty param.searchGbnDept  }">
		        	<span class="search_bullet" style="width: 68px;">부서명</span>
			        <span class="contents_search_bar"></span>
			        	<label for="P_ORG_NM_S" class="blind">부서명</label>
			        	<input type="text" class="lineTxt disabled" readonly="readonly" id="P_ORG_NM_S" name="P_ORG_NM_S" style="width: 160px;" value="${param.searchGbnDept}" >
		        </c:if>
	            <div class="T_btnLayer fr cn">
	                <button type="button" class="grayBtn ico" id="searchBtn">
	                    <img src="${imagePath}/ico_search.png" alt="조회 버튼"> 조회
	                </button>
	            </div>
            </div>
        </form>
	</div>
            
	<!-- Data Total Count -->
    <div class="tableComment" style="padding-left: 5%; padding-right: 5%;">
        <p class="list_count">총 <span>${comFn:nvl(deptInqireListTotcnt, 0)}</span>건</p>
    </div>
       
       
       
    <!-- Data List -->
	<div class="list-tb" style="padding-left: 5%; padding-right: 5%;">
		<table summary="내부 담당자 목록 입니다.">
            <caption>내부 담당자 목록</caption>
            <colgroup>
                <col width="40px">
                <col width="100px">
                <col width="70px">
                <col width="70px">
                <col width="70px">
                <col width="100px">
            </colgroup>			
			<thead  class="line">
                <tr>
                	<c:choose>
						<c:when test="${param.setMulti eq 'Y'}">
							 <th class="noBg">
							 	<label for="chargerAllCbx" class="blind">체크박스</label>
		                    	<input type="checkbox" id="chargerAllCbx" name="chargerCbx" onclick="FwkCmmnUtil.setAllCheck('chargerAllCbx','chargerCbx');">
		                    </th>
						</c:when>
						<c:otherwise>
							 <th class="noBg">번호</th>
						</c:otherwise>
					</c:choose>
                    <th>부서</th>
                    <th>사업장</th>
                </tr>
            </thead>
			<tbody>
				<c:if test="${comFn:nvl(deptInqireListTotcnt, 0) == 0}">
					<tr class="row">
						<td colspan="3">검색 조건에 해당하는 데이터가 존재하지 않습니다.</td>
					</tr>
				</c:if>
				<c:if test="${deptInqireListTotcnt > 0}">
					<c:forEach var="data" items="${deptInqireList}" varStatus="status" >
						
							
						<tr class="row" <c:if test="${param.setMulti ne 'Y'}">onclick="setdeptInfo('${data.DEPT_NO}', '${data.DEPT_NM}', '${data.BRFFC_CD}', '${data.BRFFC_NM}');"</c:if> style="cursor: pointer;">
							<c:if test="${param.setMulti eq 'Y'}">
								<td>
									<label for="chargerCbx${status.count }" class="blind">체크박스</label>
									<input type="checkbox" id="chargerCbx${status.count }" name="chargerCbx">
								</td>
							</c:if>
							<c:if test="${param.setMulti ne 'Y'}">
								<td>${data.RNUM}&nbsp;</td>
							</c:if>
							<td>
								<input type="hidden" name="P_DEPT_NO" value="${data.DEPT_NO }">
								<input type="hidden" name="P_DEPT_NM" value="${data.DEPT_NM }">
								${data.DEPT_NM}&nbsp;
							</td>
							<td>
								<input type="hidden" name="P_BRFFC_NM" value="${data.BRFFC_NM }">
								<input type="hidden" name="P_BRFFC_CD" value="${data.BRFFC_CD }">
								${data.BRFFC_NM}&nbsp;
							</td>
							<td>
								<input type="hidden" name="P_EMPL_NM" value="${data.EMPL_NM }">
								<input type="hidden" name="P_EMPNO" value="${data.EMPNO }">
								${data.EMPL_NM}&nbsp;
							</td>
							<td>
								<input type="hidden" name="P_TELNO" value="${data.TELNO }">
								${data.TELNO}&nbsp;
							</td>
							<td class="left_T">
								<input type="hidden" name="P_EMAIL" value="${data.EMAIL }">
								${data.EMAIL}&nbsp;
							</td>
						</tr>
					</c:forEach>
				</c:if>
			</tbody> 
		</table>
		
		<!-- Data Paging -->
		<div class="paging_place">
			<comTag:paging totalCount="${deptInqireListTotcnt}" pageNo="${comFn:nvl(param.P_PAGE_NO, 1)}" pageSize="${comFn:nvl(param.P_PAGE_SIZE, 10)}" clickPage="pageObj.clickPage"/>
		</div>
        <div class="search-tb"  style="padding-left: 5%; padding-right: 5%;">
        <div class="button-area">
        	<c:if test="${param.setMulti eq 'Y'}">
        		<button type="button" class="btn btn_2b8f5d" id="choiceBtn">선택</button>
        	</c:if>
            <button type="button" class="btn btn_2b8f5d" id="closeBtn">닫기</button>
        </div>
		</div>

	page move form
	<form id="detailFrm" method="POST" >
		<input type="hidden" name="contextPath" value="${contextPath}" >
		<input type="hidden" name="P_NTT_SN" >
		<input type="hidden" name="P_LOGIN_ID" value="${loginResult.LOGIN_ID}" >
		<input type="hidden" id="resourceName" name="resourceName" value="${param.resourceName}">
		<input type="hidden" id="resourceDesc" name="resourceDesc" value="${param.resourceDesc}">
	</form>         
</div> --%>