<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">--%>
<%--
 * 공통 > 세금계산서 인쇄
 *
 * <pre>
 * comm 
 *  |_popup
 *   |invoicePrint.jsp
 * 
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %>
<link rel="stylesheet" type="text/css" href="${cssPath}/ipro/windowPop.css">
<link rel="stylesheet" type="text/css" href="${cssPath}/ipro/template/pagingPlace.css">

<STYLE type=text/css>

/* ---------lim--------- */

* {
	margin: 0;
	padding: 1px;
}


.tax_red {font-size:9pt;font-family:굴림;color:#fe6d69;line-height:130%; line-height:140%;}
.tax_blue {font-size:8pt;font-family:굴림;color:#666699;line-height:130%; line-height:140%;}

.black {font-size:9pt;font-family:굴림;color:#000000;line-height:140%; }
.black_B {font-size:8pt;font-family:굴림;color:#000000;line-height:140%; font-weight: bold;}
.white {font-size:9pt;font-family:굴림;color:#FFFFFF;line-height:130%;}

.red {font-size:9pt;font-family:굴림;color:#fe6d69;ine-height:140%;}
.red1 {font-size:9pt;font-family:굴림;color:#ff0000;ine-height:140%;}

.tax_blue1 {font-size:18pt;font-family:굴림;color:#666699;line-height:120%; font-weight : bold; }
.tax_blue2 {font-size:8pt;font-family:굴림;color:#666699;line-height:130%; font-weight : bold;}
.tax_blue3 {font-size:7pt;font-family:굴림;color:#666699;line-height:130%;}

.tax_red1 {font-size:18pt;font-family:굴림;color:#fe6d69;line-height:120%; font-weight : bold; }
.tax_red2 {font-size:8pt;font-family:굴림;color:#fe6d69;line-height:130%; font-weight : bold; vertical-align: middle; padding-top: 3px;}
.tax_red3 {font-size:7pt;font-family:굴림;color:#fe6d69;line-height:130%;}

/* .tax_red {font-size:8pt;font-family:굴림;color:#000000;line-height:130%;}
.tax_red1 {font-size:9pt;font-family:굴림;color:#000000;line-height:130%;}
.tax_red2 {font-size:6pt;font-family:굴림;color:#000000;line-height:130%;}
 */

/* 테이블에 대한 기본 스타일 정의 */
body {MARGIN: 0px}
td {font-family: "돋움"; font-size: 9pt; line-height:18px ; color: #505050}

/* 일반 텍스트보다 크고 굵은 텍스트를 사용할때 */
.b {font-family:"돋움"; font-size:9pt; color:#626262;font-weight:bold;}

.l {text-align: left;}

/* ------------ LINK ----------- */

/* 일반적인 텍스트 롤오버및 링크 */
a:link     {font-family: "돋움";  	color: #505050;	text-decoration: none; line-height:18px ;}
a:visited  {font-family: "돋움";  	color: #505050;	text-decoration: none; line-height:18px ;}
a:hover    {font-family: "돋움";  	color: #008CDE;	text-decoration: underline; line-height:18px ;}

.white_top_tr{
	border-top: 0px solid #fff;
}
.white_bottom_tr{
	border-top: 0px solid #fff;
}

.white_top_tr td{
	border-top: 0px solid #fff;
}
.white_bottom_tr td{
	border-top: 0px solid #fff;
}

/* div {
    display: block;
}

caption {
    display: none;
}

h1, h2, h3, h4, h5, h6, p, dl, dt, dd, ul, ol, li, form, fieldset, blockquote {
    margin: 0px;
    padding: 0px;
}

p {
    display: block;
    -webkit-margin-before: 1em;
    -webkit-margin-after: 1em;
    -webkit-margin-start: 0px;
    -webkit-margin-end: 0px;
}
 
#content {
    clear: both;
}

table[Attributes Style] {
    width: 100%;
    height: 600px;
}

user agent stylesheettable {
    display: table;
    border-collapse: separate;
    border-spacing: 2px;
    border-color: grey;
}

td {
    font-family: "돋움", "Dotum", "굴림", "Gulim", "Arial", "sans-serif", "Verdana", "Helvetica", "geneva";
    font-size: 12px;
    line-height: 150%;
    color: #000;
}

.taxBillDivision {
    margin-bottom: 20px;
}

.taxBillFormDescription {
    margin-bottom: 5px;
}

.tax_table {
    position: relative;
    border: #e66464 solid 1px;
    padding: 1px;
    z-index: 99999;
    width: 745px;
}

.tax_invoice {
    width: 749px;
    margin-top: 20px;
    color: #000;
}

.tax_invoice01 {
    border-top: #e66464 solid 1px;
    border-left: #e66464 solid 1px;
    border-right: #e66464 solid 1px;
    border-collapse: collapse;
    width: 100%;
    table-layout: fixed;
    z-index: 999;
    position: relative;
}

.tax_invoice01 th {
    padding: 4px 0 1px 0;
    line-height: 150%;
    color: #fe6d69;
    font-size: 12px;
    font-weight: normal;
    border-bottom: #e66464 solid 1px;
}

.tax_invoice01 td {
    border-bottom: #e66464 solid 1px;
    color: #000;
    padding: 2px 0 0 2px;
    background: none;
}

.tax_invoice01 td.td3 {
    color: #000;
    border-left: #e66464 solid 1px;
    border-bottom: #e66464 solid 1px;
}

.td2 {
    text-align: center;
    letter-spacing: 0.3em;
}

.tax_invoice02 {
    margin-top: 1px;
    border-top: #e66464 solid 1px;
    border-right: #e66464 solid 1px;
    border-collapse: collapse;
    width: 100%;
    table-layout: fixed;
    word-break: break-all;
    z-index: 999;
    position: relative;
}

.tax_invoice02 .fontB {
    font-weight: bold;
}

.tax_invoice02 th {
    border-bottom: #e66464 solid 1px;
    border-left: #e66464 solid 1px;
    font-size: 12px;
    font-weight: normal;
    color: #fe6d69;
    text-align: center;
    padding: 4px 0 1px 0;
    line-height: 150%;
    vertical-align: middle;
}

.tax_invoice02 td {
    border-bottom: #e66464 solid 1px;
    border-left: #e66464 solid 1px;
    padding: 2px 2px 0 2px;
    color: #000;
    vertical-align: middle;
    line-height: 170%;
}

.tax_bold01 {
    font-weight: bold;
    text-align: center;
    font-size: 13px;
}

.tax_invoice02 .td_chargeR {
    text-align: right;
    padding: 0;
    border-left: #e66464 solid 1px;
    border-bottom: #e66464 solid 1px;
}

.tax_invoice02 .td_chargeC {
    text-align: center;
    padding: 0;
    border-left: 0;
    border-top: 0;
}

.tax_invoice02 .td_chargeL {
    text-align: left;
    padding: 0;
    border-bottom: #e66464 solid 1px;
    border-left: 0;
}

.tax_invoice02 .li0202 {
    color: #000;
    font-weight: bold;
}

td_chargeL {
    text-align: left;
    padding: 0;
    border-bottom: #e66464 solid 1px;
    border-left: 0;
}

.cell_right01 {
    text-align: right;
}

.cell_center01 {
    text-align: center;
}

.td4 {
    border-right: #e66464 solid 1px;
}
 */
</STYLE>

<script type="text/javascript" src="${jsPath}/ipro/views/comm/popup/invoicePrint.js"></script>
<div id="windowPopup" class="w_800">
	<div class="formLayer">
		  <div id="content">
		  
		  <!-- ----// 본문 조회 시작 //---- -->
			<table width="630" border=0 align="center">						
				<tr> 
					<td valign="top">
						<table width="100%" border="0" cellspacing="0" cellpadding="2" >
							<tr> 
								<td>
					[별지 제 11호서식 : 전자세금계산서]
								</td>
							</tr>
							<table width="100%" border="1" align="center" cellpadding="2" cellspacing="0" bordercolor="e66464" bordercolorlight="e66464">
								<tr class="tax_red" > 
									<td align="center">
									<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" bordercolor="e66464" bordercolorlight="e66464">
										<tr class="tax_red" > 
											<td align="center">
												<table width="100%" border="1" cellspacing="0" cellpadding="2" bordercolorlight="e66464" bordercolor="e66464">
													<tr class="tax_red" > 
														<td width="524" rowspan="3" align="center" class="tax_red"> 
															<table width="100%" border="0" cellpadding="0" cellspacing="0">
																<tr class="tax_red"> 
																	<td width="311" rowspan="3" align="center" class="tax_red1"><span id="spnDTIType">세금계산서</span></td>
																	<td width="9" rowspan="3" align="center" class="tax_red"></td>
																	<td width="95" rowspan="3" align="center" class="tax_red">
																		공&nbsp;급&nbsp;자<br>(&nbsp;보&nbsp; 관&nbsp; 용&nbsp;)</td>
																	<td rowspan="3" align="center" width="6" class="tax_red"></td>
																	<td width="100" align="right" class="tax_red">승인번호</td>
																</tr>
																<tr class="tax_red" style="display:none"> 
																	<td align="right" class="tax_red">당초 승인번호</td>
																</tr>
																<tr class="tax_red"> 
																	<td align="right" class="tax_red">관리번호</td>
																</tr>
															</table>
														</td>
														<td width="212" colspan="5" align="center" class="tax_red"> 
															<font class="black"><span id="spnIssueId"><c:out value="${ issue_id }" /></span>&nbsp;</font></td>
													</tr>
													<!-- 당초 수정 승인번호 번호 tr 추가 start -->
						                                                        <tr class="tax_red" style="display:none"> 
														<td colspan="5" align="center" class="tax_red"> 
															<font class="tax_red"><span id="spnSeqId"></span>&nbsp;</font></td>
													</tr>
							                                                <!--// 당초 수정 승인번호 번호 tr 추가 end -->
													<tr class="tax_red"> 
														<td colspan="5" align="center" class="tax_red"> 
															<font class="tax_red"><span id="spnSeqId">&nbsp;</span>&nbsp;</font></td>
													</tr>
												</table>
												<table width="602" border="0" cellspacing="0" cellpadding="0">
													<tr> 
														<td height="2"></td>
													</tr>
												</table>
												<table width="100%" border="1" cellspacing="0" cellpadding="2" bordercolorlight="e66464" bordercolor="e66464" style="word-wrap:break-word">
													<tr class="tax_red white_top_tr" bordercolor="#ffffff" > 
														<td height="0" width="2%" ></td>
														<td height="0" width="10%" ></td>
														<td height="0" width="3%" ></td>
														<td height="0" width="3%" ></td>
														<td height="0" width="3%" ></td>
														<td height="0" width="2%" ></td>
														<td height="0" width="3%" ></td>
														<td height="0" width="3%" ></td>
														<td height="0" width="2%" ></td>
														<td height="0" width="3%" ></td>
														<td height="0" width="3%" ></td>
														<td height="0" width="3%" ></td>
														<td height="0" width="3%" ></td>
														<td height="0" width="3%" ></td>

														<td height="0" width="2%" ></td>
														<td height="0" width="10%" ></td>
														<td height="0" width="3%" ></td>
														<td height="0" width="3%" ></td>
														<td height="0" width="3%" ></td>
														<td height="0" width="2%" ></td>
														<td height="0" width="3%" ></td>
														<td height="0" width="3%" ></td>
														<td height="0" width="2%" ></td>
														<td height="0" width="3%" ></td>
														<td height="0" width="3%" ></td>
														<td height="0" width="3%" ></td>
														<td height="0" width="3%" ></td>
														<td height="0" width="3%" ></td>
													</tr>
													<tr class="tax_red white_bottom_tr" style="border-top: #ffffff">
														<td rowspan="4" align="center" width="2%" class="tax_red2">공<br><br>급<br> <br>자</td>
														<td width="10%" align="center" class="tax_red">등록번호</td>
														<td width="34%" colspan="12" align="center" class="tax_red black"><b>
															<c:out value="${ dem_regnum }" /></b>
														</td>
														<td rowspan="4" align="center" width="2%" class="tax_red2"> 
															공<br>
															급<br> 
															받<br>
															는<br>
															자</td>
														
														<td width="10%" align="center" class="tax_red">등록번호</td>
														<td width="36%" colspan="12" align="center"  class="tax_red black"><b>
															<c:out value="${ sup_regnum }" /></b>
														</td>
														
													</tr>
													<tr class="tax_red"> 
														<td align="center" class="tax_red">상 &nbsp;&nbsp;&nbsp;&nbsp;호<br>(법인명)</td>
														<td colspan="6" class="tax_red black l"><span id="spnSupComName"><c:out value="${ dem_vendor_name }" /></span></td>
														<td colspan="3" align="center" class="tax_red">성명</td>
														<td colspan="3" class="tax_red black l" ><span id="spnSupPresident"><c:out value="${ dem_owner_name }" /></span></td>
														<td align="center" class="tax_red">상 &nbsp;&nbsp;&nbsp;&nbsp;호<br>(법인명)</td>
														<td colspan="6" class="tax_red black l"><span id="spnByrComName"><c:out value="${ sup_vendor_name }" /></span></td>
														<td colspan="3" align="center" class="tax_red">성명</td>
														<td colspan="3" class="tax_red black l" ><span id="spnByrPresident"><c:out value="${ sup_owner_name }" /></span></td>
													</tr>
													<tr class="tax_red"> 
														<td align="center" class="tax_red">사업장<br>주&nbsp;&nbsp;&nbsp;소</td>
														<td colspan="6" class="tax_red black l"><span id="spnSupAddress"><c:out value="${ dem_addr }" /></span></td>
														<td colspan="3" align="left" class="tax_red"><div align="center">종사업<br>장번호</div></td>
														<td colspan="3" class="tax_red black l" ><span id="spnSupBizPlaceCode">&nbsp;</span></td>
														<td align="center" class="tax_red">사업장<br>주&nbsp;&nbsp;&nbsp;소</td>
														<td colspan="6" class="tax_red black l"><span id="spnByrAddress"><c:out value="${ sup_addr }" /></span></td>
														<td colspan="3" align="center" class="tax_red">종사업<br>장번호</td>
														<td colspan="3" class="tax_red black l" ><span id="spnByrBizPlaceCode">&nbsp;</span></td>
													</tr>
													<tr class="tax_red"> 
														<td align="center" class="tax_red">업태</td>
														<td colspan="5" class="tax_red black l">&nbsp;<span id="spnSupBizStatus"><c:out value="${ dem_biz_type }" /></span></td>
														<td align="center" class="tax_red">종<br>목</td>
														<td colspan="6" class="tax_red black l">&nbsp;<span id="spnSupBizClass"><c:out value="${ dem_biz_kind }" /></span></td>
														<td align="center"  class="tax_red">업태</td>
														<td colspan="5" class="tax_red black l">&nbsp;<span id="spnByrBizStatus"><c:out value="${ sup_biz_type }" /></span> 
														</td>
														<td align="center" class="tax_red">종<br>목</td>
														<td colspan="6" class="tax_red black l">&nbsp;<span id="spnByrBizClass"><c:out value="${ sup_biz_kind }" /></span></td>
													</tr>
												</table>
												<table border="0" cellspacing="0" cellpadding="0">
													<tr> 
														<td height="2"></td>
													</tr>
												</table>
																	
						<table width="100%" border="1" cellspacing="0" cellpadding="2" bordercolorlight="e66464" bordercolor="e66464" style="word-wrap:break-word">
                          <tr align="center" class="tax_red" > 
                            <td colspan="3" class="tax_red2" width="14%">작성일자</td>
                            <td colspan="12" class="tax_red2" width="39%">공&nbsp;&nbsp;&nbsp; 
                              &nbsp;&nbsp;&nbsp;급&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;가&nbsp;&nbsp;&nbsp; 
                              &nbsp;&nbsp;&nbsp;액</td>
                            <td colspan="11" class="tax_red2" width="30%">세 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;액</td>
                            <td width="18%" class="tax_red2">수정사유</td>
                          </tr>
						  
                          <tr align="center" class="tax_red" > 
                            <td width="4%" class="tax_red">년</td>
                            <td width="3%" class="tax_red">월</td>
                            <td width="3%" class="tax_red">일</td>
                            <td width="2%" class="tax_red">천</td>
                            <td width="2%" class="tax_red">백</td>
                            <td width="2%" class="tax_red">십</td>
                            <td width="2%" class="tax_red">억</td>
                            <td width="2%" class="tax_red">천</td>
                            <td width="2%" class="tax_red">백</td>
                            <td width="2%" class="tax_red">십</td>
                            <td width="2%" class="tax_red">만</td>
                            <td width="2%" class="tax_red">천</td>
                            <td width="2%" class="tax_red">백</td>
                            <td width="2%" class="tax_red">십</td>
                            <td width="2%" class="tax_red">일</td>
							<td width="2%" class="tax_red">백</td>
                            <td width="2%" class="tax_red">십</td>
                            <td width="2%" class="tax_red">억</td>
                            <td width="2%" class="tax_red">천</td>
                            <td width="2%" class="tax_red">백</td>
                            <td width="2%" class="tax_red">십</td>
                            <td width="2%" class="tax_red">만</td>
                            <td width="2%" class="tax_red">천</td>
                            <td width="2%" class="tax_red">백</td>
                            <td width="2%" class="tax_red">십</td>
                            <td width="2%" class="tax_red">일</td>
                            <td rowspan="2" class="tax_red" align="left"><span id="spnAmendCode">&nbsp;</span></td>
                          </tr>
                          <tr class="tax_red" align="center"  > 
                            <td class="tax_red black"><span id="spnWriteDate"><c:out value="${ pubDate }" /></span></td>
                            <td class="tax_red black"><span id="spnWriteDate"><c:out value="${ pubDateMM }" /></span></td>
                            <td class="tax_red black"><span id="spnWriteDate"><c:out value="${ pubDateDD }" /></span></td>
                            
								<td class="tax_red black"><span id="spnSupAmount"><c:out value="${ amtSum12 }" /></span></td>
							
								<td class="tax_red black"><span id="spnSupAmount"><c:out value="${ amtSum11 }" /></span></td>
							
								<td class="tax_red black"><span id="spnSupAmount"><c:out value="${ amtSum10 }" /></span></td>
							
								<td class="tax_red black"><span id="spnSupAmount"><c:out value="${ amtSum9 }" /></span></td>
							
								<td class="tax_red black"><span id="spnSupAmount"><c:out value="${ amtSum8 }" /></span></td>
							
								<td class="tax_red black"><span id="spnSupAmount"><c:out value="${ amtSum7 }" /></span></td>
							
								<td class="tax_red black"><span id="spnSupAmount"><c:out value="${ amtSum6 }" /></span></td>
							
								<td class="tax_red black"><span id="spnSupAmount"><c:out value="${ amtSum5 }" /></span></td>
							
								<td class="tax_red black"><span id="spnSupAmount"><c:out value="${ amtSum4 }" /></span></td>
							
								<td class="tax_red black"><span id="spnSupAmount"><c:out value="${ amtSum3 }" /></span></td>
							
								<td class="tax_red black"><span id="spnSupAmount"><c:out value="${ amtSum2 }" /></span></td>
							
								<td class="tax_red black"><span id="spnSupAmount"><c:out value="${ amtSum1 }" /></span></td>
							
								<td class="tax_red black"><span id="spnTaxAmount"><c:out value="${ surtax11 }" /></span></td>
							
								<td class="tax_red black"><span id="spnTaxAmount"><c:out value="${ surtax10 }" /></span></td>
							
								<td class="tax_red black"><span id="spnTaxAmount"><c:out value="${ surtax9 }" /></span></td>
							
								<td class="tax_red black"><span id="spnTaxAmount"><c:out value="${ surtax8 }" /></span></td>
							
								<td class="tax_red black"><span id="spnTaxAmount"><c:out value="${ surtax7 }" /></span></td>
							
								<td class="tax_red black"><span id="spnTaxAmount"><c:out value="${ surtax6 }" /></span></td>
							
								<td class="tax_red black"><span id="spnTaxAmount"><c:out value="${ surtax5 }" /></span></td>
							
								<td class="tax_red black"><span id="spnTaxAmount"><c:out value="${ surtax4 }" /></span></td>
							
								<td class="tax_red black"><span id="spnTaxAmount"><c:out value="${ surtax3 }" /></span></td>
							
								<td class="tax_red black"><span id="spnTaxAmount"><c:out value="${ surtax2 }" /></span></td>
							
								<td class="tax_red black"><span id="spnTaxAmount"><c:out value="${ surtax1 }" /></span></td>
							
                          </tr>
						  <tr align="center" class="tax_red" > 
                            <td colspan="3" class="tax_red2" width="14%">비 &nbsp;고&nbsp;&nbsp;</td>
                            <td colspan="24" class="tax_red black" align="left"><span id="spnRemark"><c:out value="${ remark }" /></span></td>
                          </tr>
			  <!-- V3 추가 비고2, 비고3 by 이승현 - V3 2차 --> 
			  
			  <!-- V3 추가 비고2, 비고3 by 이승현 - V3 2차  -->
                        </table>
                        <table width="602" border="0" cellspacing="0" cellpadding="0">
                          <tr> 
                            <td height="2"></td>
                          </tr>
                        </table>
                        <table width="100%" border="1" cellspacing="0" cellpadding="2" bordercolorlight="e66464" bordercolor="e66464" style="word-wrap:break-word">
                          <tr class="tax_red" align="center"> 
                            <td width="3%" class="tax_red2">월</td>
                            <td width="3%" class="tax_red2">일</td>
                            <td width="23%" class="tax_red2">품 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;목</td>
                            <td width="14%" class="tax_red2">규 격 </td>
                            <td width="8%" class="tax_red2">수 량</td>
                            <td width="12%" class="tax_red2">단가</td>
                            <td width="15%" class="tax_red2">공 급 가 액</td>
                            <td width="15%" class="tax_red2">세 &nbsp;액</td>
                            <td width="7%" class="tax_red2" style="word-wrap:break-word">비 고</td>
                          </tr>
                          
													<tr style="word-wrap:break-word"> 
														<td align="center" class="tax_red black" ><span id="spnTM"><c:out value="${ itemDateMM }" /></span></td>
														<td align="center" class="tax_red black"><span id="spnTD"><c:out value="${ itemDateDD }" /></span></td>
														<td align="left" class="tax_red black" style="word-wrap:break-word"><span id="spnTItem"><c:out value="${ itemName }" /></span></td>
														<td align="left" class="tax_red black" ><span id="spnTSize"><c:out value="${ itemStd }" /></span></td>
														<td align="right" class="tax_red black" ><span id="spnTQnt"><c:out value="${ itemQty }" /></span></td>
														<td align="right" class="tax_red black" ><span id="spnTUnitPrice"><c:out value="${ itemPrice }" /></span></td>
														<td align="right" class="tax_red black" ><span id="spnTSupAmount"><c:out value="${ itemAmt }" /></span>&nbsp;</td>
														<td align="right" class="tax_red black" ><span id="spnTTaxAmount"><c:out value="${ itemStax }" /></span>&nbsp;</td>
														<td class="tax_red black" align="left"><span id="spnTRemark"><c:out value="${ itemRemark }" /></span></td>
													</tr>
													
                        </table>
					
												<table border="0" cellspacing="0" cellpadding="0">
													<tr> 
														<td height="2"></td>
													</tr>
												</table>
												<table width="100%" border="1" cellspacing="0" cellpadding="2" bordercolorlight="e66464" bordercolor="e66464">
													<tr class="tax_red" > 
														<td align="center" width="115" class="tax_red2">합계금액</td>
														<td align="center" width="85" class="tax_red2">현금</td>
														<td align="center" width="85" class="tax_red2">수표</td>
														<td align="center" width="85" class="tax_red2">어음</td>
														<td align="center" width="85" class="tax_red2">외상미수금</td>
														<td rowspan="2" width="194" align="center"> 
															<table border="0" cellspacing="0" cellpadding="1">
																<tr class="tax_red"> 
																	<td ></td>
																	<td rowspan="3" align="center">영수<br><b><span id="spnTaxDemand"><u>[ 청구 ]</u></span></b>

																	<td>&nbsp;</td>
																</tr>
																<tr class="tax_red"> 
																	<td class="tax_red">이 금액을&nbsp;</td>
																	<td align="center" class="tax_red">함</td>
																</tr>
																<tr> 
																	<td>&nbsp;</td>
																	<td>&nbsp;</td>
																</tr>
															</table>
														</td>
													</tr>
													<tr > 
														<td align="right" class="tax_red black" ><span id="spnTotAmount"><c:out value="${ amtTotal }" /></span></td>
														<td align="right" class="tax_red black" ><span id="spnCash"><c:out value="${ amtCash }" /></span></td>
														<td align="right" class="tax_red black" ><span id="spnCheck"><c:out value="${ amtCheck }" /></span></td>
														<td align="right" class="tax_red black" ><span id="spnBill"><c:out value="${ amtBill }" /></span></td>
														<td align="right" class="tax_red black" ><span id="spnCredit"><c:out value="${ amtCredit }" /></span></td>
													</tr>
												</table>
											</td>
										</tr>
									</table>
								</td>
							</tr>
						</table>
					</td>
				</tr>
		</table>
		</td>
		</tr>
		</table>

		<table width="630" border=0 align="center">
	
			<tr align = "center">
				<td align="left">22226-61921일 '96.3.14승인'</td>
				<td align="right">인쇄용지(특급)34g/㎡ 182㎜×128㎜</td>
			</tr>
			<tr align = "center">
				<td align="left" colspan=2>주의 : 본 세금계산서는 국세청 고시 기준에 따라 발급된 전자(세금)계산서 입니다.</td>
			</tr>
	
			<tr>
				<td>&nbsp;</td>
			</tr>
			<tr align="center"> 
          			<td colspan="2" align="center" valign="top" background="http://www.smartbill.co.kr/images/Center/bg_dot3.gif"></td>
        		</tr>	
		</table>
		  
		  
<%-- 		  
        <table width="100%" height="600" summary="세금계산서">
            <tbody><tr valign="top">
              <td id="DtiMessge">
<div class="taxBillDivision">
<div class="tax_invoice">
<p class="taxBillFormAttention" id="taxBillFormAttention01">[별지 제 11호서식 : 전자세금계산서]</p>
<div class="tax_table">
<table class="tax_invoice01" border="0" cellspacing="0" cellpadding="0" width="100%" summary="전자세금계산서 승인번호, 관리번호">
<caption>
            전자세금계산서 승인번호, 관리번호
          </caption>
<colgroup>
<col width="41%">
<col width="15%">
<col width="5%">
<col width="12%">
<col width="%">
</colgroup>
<tbody><tr>
<th rowspan="2"><h1 class="title">
                   세금계산서
              </h1></th>
<th rowspan="2" class="td2">
              공급자<br>
              (보관용)
            </th>
<td rowspan="2" class="td4"></td>
<th>승인번호</th>
<td class="td3"><c:out value="${ issue_id }" /></td>
</tr>
<tr>
<th>관리번호</th>
<td class="td3"></td>
</tr>
</tbody></table>
<table class="tax_invoice02" border="0" cellspacing="0" cellpadding="0" width="100%" summary="공급자, 공급받는자 정보">
<caption>
            공급자, 공급받는자 정보
          </caption>
<colgroup>
<col width="3%">
<col width="8%">
<col width="17%">
<col width="1%">
<col width="7%">
<col width="">
<col width="3%">
<col width="8%">
<col width="17%">
<col width="1%">
<col width="7%">
<col width="">
</colgroup>
<tbody><tr>
<th class="title02-1 fontB" rowspan="4">공급자</th>
<th>등록번호</th>
<td class="tax_bold01" colspan="4"><c:out value="${ dem_regnum }" /></td>
<th class="title02-1 fontB" rowspan="4">공급받는자</th>
<th>등록번호</th>
<td class="tax_bold01" colspan="4"><c:out value="${ sup_regnum }" /></td>
</tr>
<tr>
<th>
              상호<br>
              (법인명)
            </th>
<td colspan="2"><c:out value="${ dem_vendor_name }" /></td>
<th>성명</th>
<td><c:out value="${ dem_owner_name }" /></td>
<th>
              상호<br>
              (법인명)
            </th>
<td colspan="2"><c:out value="${ sup_vendor_name }" /></td>
<th>성명</th>
<td><c:out value="${ sup_owner_name }" /></td>
</tr>
<tr>
<th>
              사업장<br>
              주소
            </th>
<td colspan="2"><c:out value="${ dem_addr }" /></td>
<th>
              종사업<br>
              장번호
            </th>
<td></td>
<th>
              사업장<br>
              주소
            </th>
<td colspan="2"><c:out value="${ sup_addr }" /></td>
<th>
              종사업<br>
              장번호
            </th>
<td></td>
</tr>
<tr>
<th>업태</th>
<td colspan="2"><c:out value="${ dem_biz_type }" /></td>
<th>종목</th>
<td><c:out value="${ dem_biz_kind }" /></td>
<th>업태</th>
<td colspan="2">통신</td>
<th><c:out value="${ sup_biz_type }" /></th>
<td><c:out value="${ sup_biz_kind }" /></td>
</tr>
</tbody></table>
<table class="tax_invoice02" border="0" cellspacing="0" cellpadding="0" summary="작성일자, 공급가액, 세액, 수정사유, 비고">
<caption>
            작성일자, 공급가액, 세액, 수정사유, 비고
          </caption>
<colgroup>
<col width="6%">
<col width="3%">
<col width="3%">
<col width="3%">
<col width="3%">
<col width="3%">
<col width="3%">
<col width="3%">
<col width="3%">
<col width="3%">
<col width="3%">
<col width="3%">
<col width="3%">
<col width="3%">
<col width="3%">
<col width="3%">
<col width="3%">
<col width="3%">
<col width="3%">
<col width="3%">
<col width="3%">
<col width="3%">
<col width="3%">
<col width="3%">
<col width="3%">
<col width="3%">
<col width="19%">
<col width="">
</colgroup>
<tbody>
<tr>
<th colspan="3" class="fontB ">작성일자</th>
<th colspan="12" class="fontB ">공급가액</th>
<th colspan="11" class="fontB ">세 액</th>
<th class="fontB ">수정사유</th>
</tr>
<tr>
<td class="cell_center01 ">년</td>
<td class="cell_center01 ">월</td>
<td class="cell_center01 ">일</td>
<td class="cell_center01 ">천</td>
<td class="cell_center01 ">백</td>
<td class="cell_center01 ">십</td>
<td class="cell_center01 ">억</td>
<td class="cell_center01 ">천</td>
<td class="cell_center01 ">백</td>
<td class="cell_center01 ">십</td>
<td class="cell_center01 ">만</td>
<td class="cell_center01 ">천</td>
<td class="cell_center01 ">백</td>
<td class="cell_center01 ">십</td>
<td class="cell_center01 ">일</td>
<td class="cell_center01 ">백</td>
<td class="cell_center01 ">십</td>
<td class="cell_center01 ">억</td>
<td class="cell_center01 ">천</td>
<td class="cell_center01 ">백</td>
<td class="cell_center01 ">십</td>
<td class="cell_center01 ">만</td>
<td class="cell_center01 ">천</td>
<td class="cell_center01 ">백</td>
<td class="cell_center01 ">십</td>
<td class="cell_center01 ">일</td>
<td rowspan="2" class="center "></td>
</tr>
<tr>
<td class="cell_center01 "><c:out value="${ pubDate }" /></td>
<td class="cell_center01 "><c:out value="${ pubDateMM }" /></td>
<td class="cell_center01 "><c:out value="${ pubDateDD }" /></td>
<td class="cell_center01 "><c:out value="${ amtSum12 }" /></td>
<td class="cell_center01 "><c:out value="${ amtSum11 }" /></td>
<td class="cell_center01 "><c:out value="${ amtSum10 }" /></td>
<td class="cell_center01 "><c:out value="${ amtSum9 }" /></td>
<td class="cell_center01 "><c:out value="${ amtSum8 }" /></td>
<td class="cell_center01 "><c:out value="${ amtSum7 }" /></td>
<td class="cell_center01 "><c:out value="${ amtSum6 }" /></td>
<td class="cell_center01 "><c:out value="${ amtSum5 }" /></td>
<td class="cell_center01 "><c:out value="${ amtSum4 }" /></td>
<td class="cell_center01 "><c:out value="${ amtSum3 }" /></td>
<td class="cell_center01 "><c:out value="${ amtSum2 }" /></td>
<td class="cell_center01 "><c:out value="${ amtSum1 }" /></td>
<td class="cell_center01 "><c:out value="${ surtax11 }" /></td>
<td class="cell_center01 "><c:out value="${ surtax10 }" /></td>
<td class="cell_center01 "><c:out value="${ surtax9 }" /></td>
<td class="cell_center01 "><c:out value="${ surtax8 }" /></td>
<td class="cell_center01 "><c:out value="${ surtax7 }" /></td>
<td class="cell_center01 "><c:out value="${ surtax6 }" /></td>
<td class="cell_center01 "><c:out value="${ surtax5 }" /></td>
<td class="cell_center01 "><c:out value="${ surtax4 }" /></td>
<td class="cell_center01 "><c:out value="${ surtax3 }" /></td>
<td class="cell_center01 "><c:out value="${ surtax2 }" /></td>
<td class="cell_center01 "><c:out value="${ surtax1 }" /></td>
</tr>
<tr>
<th colspan="3" class="center fontB"><strong>
                    비고</strong></th>
<td colspan="24"><span><c:out value="${ remark }" /></span></td>
</tr>
</tbody>
</table>
<table class="tax_invoice02" border="0" cellspacing="0" cellpadding="0" width="100%" summary="월, 일, 품목, 규격, 수량, 단가, 공급가액, 세액, 비고">
<caption>
            월, 일, 품목, 규격, 수량, 단가, 공급가액, 세액, 비고
          </caption>
<colgroup>
<col width="3%">
<col width="3%">
<col width="23%">
<col width="9%">
<col width="9%">
<col width="15%">
<col width="15%">
<col width="13%">
<col width="10%">
</colgroup>
<thead><tr>
<th class="fontB">월</th>
<th class="fontB">일</th>
<th class="fontB">품 목</th>
<th class="fontB">규 격</th>
<th class="fontB">수 량</th>
<th class="fontB">단 가</th>
<th class="fontB">공 급 가 액</th>
<th class="fontB">세 액</th>
<th class="fontB">비 고</th>
</tr></thead>
<tbody><tr>
<td class="center"><c:out value="${ itemDateMM }" /></td>
<td class="center"><c:out value="${ itemDateDD }" /></td>
<td><c:out value="${ itemName }" /></td>
<td class="center"><c:out value="${ itemStd }" /></td>
<td class="center"><c:out value="${ itemQty }" /></td>
<td class="cell_right01"><c:out value="${ itemPrice }" /></td>
<td class="cell_right01"><c:out value="${ itemAmt }" /></td>
<td class="cell_right01"><c:out value="${ itemStax }" /></td>
<td><c:out value="${ itemRemark }" /></td>
</tr></tbody>
</table>
<table class="tax_invoice02" border="0" cellspacing="0" cellpadding="0" width="100%" summary="합계금액, 현금, 수표, 어음, 외상미수금">
<caption>
            합계금액, 현금, 수표, 어음, 외상미수금
          </caption>
<colgroup>
<col width="15%">
<col width="15%">
<col width="15%">
<col width="15%">
<col width="15%">
<col width="10%">
<col width="">
<col width="6%">
</colgroup>
<tbody><tr>
<th class="fontB">합계금액</th>
<th class="fontB">현 금</th>
<th class="fontB">수 표</th>
<th class="fontB">어 음</th>
<th class="fontB">외상미수금</th>
<td class="td_chargeR" rowspan="2">이 금액을</td>
<td class="td_chargeC" rowspan="2">
<span class="li0201">영수</span><br><span class="li0202">[ 청구 ]</span>
</td>
<td class="td_chargeL" rowspan="2">함</td>
</tr>
<tr>
<td class="cell_right01"><c:out value="${ amtTotal }" /></td>
<td class="cell_right01"><c:out value="${ amtCash }" /></td>
<td class="cell_right01"><c:out value="${ amtCheck }" /></td>
<td class="cell_right01"><c:out value="${ amtBill }" /></td>
<td class="cell_right01"><c:out value="${ amtCredit }" /></td>
</tr>
</tbody></table>
</div></div>
<div style="width:745px;">
	<p class="taxBillFormAttention" id="taxBillFormAttention01" style="width: 100%;"><span>22226-61921일 '96.3.14승인'</span>
	<span style="right: 0; position: absolute;">인쇄용지(특급)34g/㎡  182mm × 128mm</span></p>
	<p class="taxBillFormAttention" id="taxBillFormAttention01">주의 : 본 세금계산서는 국세청 고시 기준에 따라 발급된 전자(세금)계산서 입니다.</p>
</div>
</div>
</td>              
            </tr>       
        </tbody></table>
        
        
        
         --%>
        
    </div>
	</div>
</div>
