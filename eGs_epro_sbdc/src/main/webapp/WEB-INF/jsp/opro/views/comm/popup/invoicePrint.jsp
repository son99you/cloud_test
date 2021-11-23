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

<STYLE type=text/css>

div {
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

</STYLE>


<div id="windowPopup" class="w_800">
	<div class="formLayer">
		  <div id="content">
        <table width="100%" height="600" summary="세금계산서">
            <tbody><tr valign="top">
              <td id="DtiMessge">
<div class="taxBillDivision">
<p class="taxBillFormDescription">
            &nbsp;


<b style="color:Red"></b></p>
<div class="tax_invoice"><div class="tax_table">
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
<br><p class="taxBillFormAttention" id="taxBillFormAttention01">
            주의 : 본 세금계산서는 국세청 고시 기준에 따라 발급된 전자(세금)계산서 입니다.
          </p>
</div>
</td>              
            </tr>       
        </tbody></table>
    </div>
	</div>
</div>
