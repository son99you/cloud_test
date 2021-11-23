<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
입찰관리 > 나의 협상통보 상세
 *
 * <pre>
 * ebid 
 *   	 |_ myNtatDspthDetail.jsp
 * </pre>
 * @date : 2017. 06. 22.
 * @version : 1.0
 * @author : 은우소프트 이주연
--%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %>

<script type="text/javascript" src="${jsPath}/comm/comUtil.js"></script>  
<script type="text/javascript" src="${jsPath}/opro/views/ebid/myNtatDspthDetail.js"></script>  
 
<ul class="step_wrap">
	<li><a href="#">경쟁입찰</a></li>
	<li><a href="#">나의 협상통보</a></li> 
</ul> <!--// step_wrap E -->
<div class="tit_wrap">
	<h3 class="tit">나의 협상통보 상세</h3>
</div> <!--// tit_wrap E -->  

<div class="view_wrap typeB">
	<div class="tit_area">
		<h4 class="tit">입찰개요</h4>
	</div> <!--// tit_area E -->
	<div class="view_area">
		 <table class="table">
                <caption>입찰개요</caption>
                <colgroup>
                    <col width="170px">
                    <col width="320px">
                    <col width="170px">
                    <col width="320px">
                </colgroup>
                <tbody>
                <tr class="line">
                    <th>입찰공고번호</th>
                    <td>${myNtatDspthDetail.PBLANC_NO}-${myNtatDspthDetail.PBLANC_ODR}</td>
                    <th>공고일자</th>
                    <td>${comFn:formatDate(myNtatDspthDetail.PBLANC_DT,'yyyyMMddHHmmss','yyyy-MM-dd')}&nbsp;</td>
                </tr>
                <tr>
                    <th>입찰명</th>
                    <td colspan="3">${myNtatDspthDetail.BID_NM}&nbsp;</td>
                </tr>
                <tr>
                    <th>계약방법</th>
                    <td>${myNtatDspthDetail.CNTRCT_MTH_NM}&nbsp;</td>
                    <th>낙찰방법</th>
                    <td>${myNtatDspthDetail.SCSBID_MTH_NM}&nbsp;</td>
                </tr>
                </tbody>
            </table>
	</div> <!--// view_area E -->
	<div class="tit_area">
		<h4 class="tit">협상내용</h4>
	</div> <!--// tit_area E -->
	<div class="view_area">
		<table class="table">
                <caption>협상내용</caption>
                <colgroup>
                    <col width="170px">
                    <col width="320px">
                    <col width="170px">
                    <col width="320px">

                </colgroup>
                <tbody>
                <tr>
                    <th>통보일자</th>
                    <td colspan="3">${comFn:formatDate(myNtatDspthDetail.DSPTH_DT,'yyyyMMddHHmmss','yyyy-MM-dd HH:mm:ss')}&nbsp;</td>
                </tr>
                <tr>
                    <th>협상가격</th>
                    <td colspan="3"> &nbsp; ￦ &nbsp; ${comFn:formatMoney(myNtatDspthDetail.BDDPR_AMOUNT)}&nbsp;</td>
                </tr>
                <tr>
                	<th>통보내용</th>
                	<td colspan="3"> ${myNtatDspthDetail.DSPTH_CN }&nbsp;</td>
                </tr>
                </tbody>
            </table>
	</div> <!--// view_area E -->
        	
	<div class="btn_wrap view_btn">
            <button type="button" class="btn btn_m btn_del" id="listBtn">목록</button>
	</div> <!--// btn_wrap E -->
</div>    
    
   	<form id="listFrm" method="POST" >
		<input type="hidden" id="resourceName" name="resourceName" value="${param.resourceName}">
	</form>
   	<form id="popupFrm" method="POST" >
		<input type="hidden" name="P_PBLANC_NO" value="${bidInfoDetail.PBLANC_NO}"/>
		<input type="hidden" name="P_PBLANC_ODR" value="${bidInfoDetail.PBLANC_ODR}"/>
		<input type="hidden" name="P_COMPNO_PRDPRC_NO" />
		<input type="hidden" name="P_ENTRPS_REGIST_NO"/>
		<input type="hidden" id="resourceName" name="resourceName" value="${param.resourceName}">
		
	</form>
	<form id="sttusFrm" method="POST">
	
		<input type="hidden" name="P_PBLANC_NO" value="${bidInfoDetail.PBLANC_NO}"/>
		<input type="hidden" name="P_PBLANC_ODR" value="${bidInfoDetail.PBLANC_ODR}"/>
		<input type="hidden" name="P_SCSBID_MTH_CD" value="${bidInfoDetail.SCSBID_MTH_CD }"/>
		<input type="hidden" name="P_BID_LMT_AMOUNT" value="${bidInfoDetail.BID_LMT_AMOUNT }"/>
		<input type="hidden" name="P_PRCURE_SE_CD" value="${bidInfoDetail.PRCURE_SE_CD }"/>
		<input type="hidden" name="P_EVL_CN_TY"/>
		<input type="hidden" name="P_ENTRPS_REGIST_NO"/>
		<input type="hidden" name="P_ENTRPS_PRST_CD" />
		<input type="hidden" id="resourceName" name="resourceName" value="${param.resourceName}">
	
	</form>


