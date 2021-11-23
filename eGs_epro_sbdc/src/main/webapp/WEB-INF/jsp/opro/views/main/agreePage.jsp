<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 회원가입 > 이용약관
 *
 * <pre>
 * main 
 *    |_ agreePage.jsp
 * 
 * </pre>
 * @date : 2019. 02. 25
 * @version : 1.0
 * @author : 은우소프트 은잔디
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %>

<script type="text/javascript" src="${jsPath}/comm/comUtil.js"></script>  
<script type="text/javascript" src="${jsPath}/opro/views/main/agreePage.js"></script>  

<div class="contents_wrap">
	<ul class="step_wrap">
		<li><a href="#">${myMenuList.bigMenuNm}</a></li>
		<li><a href="#">${myMenuSubList.smallMenuNm}</a></li>
	</ul>
	<div class="tit_wrap">
		<h3 class="tit">회원가입</h3>
	</div>
	
	<form id="agreeFrm" name="agreeFrm" method="POST">
		
		<div class="view_wrap typeB">
			<fieldset>
				<legend>알림</legend>
					<div class="board-write">
						<span style="font-weight: bold; color: red;">아래 약관을 확인하시고 동의하여 주시기 바랍니다</span>	
					</div>				
			</fieldset>
			
			<div class="tit_area">
				<h4 class="tit">제 1조 (목적)</h4>
			</div>
			<div class="view_area">
				<table>
					<caption>제 1조 (목적)</caption>
					<colgroup>
						<col style="width:auto;">
					</colgroup>
					<tbody>
						<tr> 
							<td><textarea taView style="display: none;">이 약관은 한국전기연구원(이하 “연구원”이라 한다)의 전자조달시스템 운영자인 연구원과 연구원의 전자조달시스템을 통하여 집행되는 전자입찰 및 전자계약에 참가하고자 하는 자(이하 “이용자”라 한다)의 이용조건, 절차, 권리 및 의무사항 등을 정함을 목적으로 한다.</textarea></td>
						</tr>
					</tbody>
				</table>
			</div>
			
			<div class="tit_area">
				<h4 class="tit">제 2조 (용어의 정의)</h4>
			</div>
			<div class="view_area">
				<table>
					<caption>제 2조 (용어의 정의)</caption>
					<colgroup>
						<col style="width:auto;">
					</colgroup>
					<tbody>
						<tr> 
							<td>
								<textarea rows="3" style="width: 100%;" readonly="readonly">
①	이 약관 또는 전자조달시스템에서 사용하는 특정한 용어의 정의는 다음 각 호와 같다.
	1. "전자조달시스템"(이하 "시스템"이라 한다.)이라 함은 연구원의 경영정보시스템 중 전자조달업무를 수행하기 위한 정보통신설비체계를 말한다.
	2. "전자입찰"이라 함은 시스템이 제공하는 전자거래 수단을 통해 집행되는 입찰을 말한다.
	3. "전자조달"이라 함은 연구원과 이용자간에 시스템을 이용하여 사이버 공간에서 이루어지는 양자간의 전자적 교류를 말한다.
	4. "전자적 교류"라 함은 컴퓨터 등 정보처리능력을 가진 장치에 의하여 시스템 사용시에 전자적 형태로 저장된 송·수신 정보를 말한다.
	5. "전자적 교류 수단"이라 함은 시스템을 이용하기 위하여 연구원이 정한 사항을 이행한 후 이용자가 입력한 ID와 접속비밀번호, 전자서명인증을 말한다.
	6. "전자서명 인증"이라 함은 전자조달을 위하여 연구원에 당해 전자적 교류를 송신한 자가 이용자임을 나타내는 보안정보를 말하며, 인장 날인 행위와 동일한 효력을 갖는다.
	7. "시스템 서버"라 함은 연구원의 시스템과 이용자간 전자문서의 송·수신을 목적으로 연구원에 설치된 전산장비를 의미한다.
	8. "이용자"라 함은 연구원의 시스템을 이용하기 위하여 연구원이 정한 사항을 이행한 “법인” 혹은 “개인”을 말한다.
	9. 서비스 제공하는 사이버 공간의 주소는 ebid.keri.re.kr을 말한다.
	10."역경매"라 함은 공급자가 일정한 가격을 제시하더라도 다른 경쟁자가 제시한 가격을 보고 다시 그보다 더 유리한 가격을 제시할 수 있는 기회를 갖도록 하는 최저가 매입방식을 말한다.
	11. 매각이라 함은 불용품 매각등 연구원의 재산 처분을 위한 최고가 매각방식을 말한다.
②	이 약관에서 정의하지 아니한 용어는 일반 상관례와 관계 법규에 의한다.</textarea>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			
			
			
			<div class="tit_area">
				<h4 class="tit">제 3조 (약관의 효력 및 변경)</h4>
			</div>
			<div class="view_area">
				<table>
					<caption>제 3조 (약관의 효력 및 변경)</caption>
					<colgroup>
						<col style="width:auto;">
					</colgroup>
					<tbody>
						<tr> 
							<td>
								<textarea style="width: 100%; height: 100px; border: 1px solid #DCDBDB; padding: 15px; color: #595959; text-align: left;" readonly="readonly">
①	이 약관은 이용자가 이에 동의함으로써 그 효력이 발생한다.
②	이 약관은 연구원과 이용자간에 이루어지는 전자조달에 적용하며, 전자적 형태로 되어 있다는 이유로 효력이 부인되지 않는다.
③	이용자의 의사표현은 전자적 교류수단을 사용함으로써 발생한다.
④	연구원은 시스템의 원활한 운영을 위하여 이 약관을 개정할 수 있으며, 이 약관이 개정된 경우에는 개정내용 및 시행 시기 등을 시행 7일전에 시스템에 공고한다. 다만, 긴급한 때에는 변경 즉시 이를 공고하고 시행할 수 있다.
⑤	제7항의 규정에 의하여 개정된 약관은 개정 내용이 관계 법령에 위배되지 않고 별도의 경과규정이 없는 한 개정 이전에 등록한 이용자에게도 적용된다.
⑥	이 약관에서 정하지 아니한 사항과 이 약관의 해석에 관하여는 연구원의 계약관련규정, 전자거래기본법, 전자서명법 등 기타 대한민국 관계 법령에 의한다.</textarea>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			
			
			<div class="tit_area">
				<h4 class="tit">제 4조 (이용자 등록)</h4>
			</div>
			<div class="view_area">
				<table>
					<caption>제 4조 (이용자 등록)</caption>
					<colgroup>
						<col style="width:auto;">
					</colgroup>
					<tbody>
						<tr> 
							<td><textarea taView style="display: none;">전자조달에 참여하고자 하는 자는 시스템에서 정한 방법에 따라 이용자로 등록하여야 한다.</textarea></td>
						</tr>
					</tbody>
				</table>
			</div>
			
			
			<div class="tit_area">
				<h4 class="tit">제5조(이용자 등록절차)</h4>
			</div>
			<div class="view_area">
				<table>
					<caption>제5조(이용자 등록절차)</caption>
					<colgroup>
						<col style="width:auto;">
					</colgroup>
					<tbody>
						<tr> 
							<td>
								<textarea style="width: 100%; height: 150px; border: 1px solid #DCDBDB; padding: 15px; color: #595959; text-align: left;" readonly="readonly">
①	이용자 등록절차는 다음 각 호의 순서와 같다.
1. 서비스를 이용할 사이버 주소에서 이용약관에 동의
2. 이용자 정보 입력
3. 제출서류를 연구원이 정한 장소로 우편 송부(해당 서류가 미비되거나 오기가 있을 경우 이용자 등록거절)하거나 혹은 인편방문
②	제출서류는 아래 각호와 같다.
1. 사업자등록증 사본 1부(원본 대조필)
2. 거래은행 계좌입금의뢰서(거래은행계좌입금의뢰서.hwp)
③	전항 제2호 내지 제3호의 서류는 연구원 도착 당일 국가기관에서 정한 구비서류의 유효기간내 발행분을 유효서류로 정하거나(유효기간을 준수하지 아니하는 경우 등록거절), 유효기간을 정하기 곤란한 경우 등록일 기준 30일(1달)이내의 발행분으로 한다.
④	연구원에 제출한 서류는 반환하지 않는다.</textarea>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			
			
			<div class="tit_area">
				<h4 class="tit">제 6조 (이용자 등록 거절 및 취소)</h4>
			</div>
			<div class="view_area">
				<table>
					<caption>제 6조 (이용자 등록 거절 및 취소)</caption>
					<colgroup>
						<col style="width:auto;">
					</colgroup>
					<tbody>
						<tr> 
							<td>
								<textarea style="width: 100%; height: 150px; border: 1px solid #DCDBDB; padding: 15px; color: #595959; text-align: left;" readonly="readonly">
이용자 등록 신청을 한 자가 다음 각 호에 해당되는 때에는 연구원은 이용자 등록을 거절하거나 취소할 수 있다.
1. 약관에 동의하지 않은 경우
2. 이용자가 본 약관을 준수치 아니한 경우
3. 입력 내용에 허위 기재, 누락, 오기가 있는 경우
4. 구비서류에 날인을 위조 또는 변조, 누락하여 제출한 경우
5. 구비서류가 유효기간을 경과하였을 경우
6. 이용자의 등록을 완료한 후 1년 동안 연구원 전자조달시스템을 사용하지 아니한 경우
7. 이용자가 신규등록 후 2개월이 경과하고 구비서류를 제출하지 아니한 경우
8. 이용자가 등록번호 변경 신청 후 구비서류를 10일 이내에 제출하지 아니한 경우
9. 기타 연구원이 판단하여 거절 또는 취소할 만한 정당한 사유가 있는 경우</textarea>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			
			
			<div class="tit_area">
				<h4 class="tit">제 7조 (분실, 망실 등)</h4>
			</div>
			<div class="view_area">
				<table>
					<caption>제 7조 (분실, 망실 등)</caption>
					<colgroup>
						<col style="width:auto;">
					</colgroup>
					<tbody>
						<tr> 
							<td><textarea taView style="display: none;">
①	이용자가 전자적 교류수단을 분실, 망실하였을 경우 연구원이 정한 절차에 의하여 회복한다.
②	전항의 경우 연구원은 이용자의 분실, 망실 신고 후 사실확인을 거쳐 이용자가 신규등록시 기입한 대표자 전자우편주소로 해당 정보를 제공한다.
③	전항에 따라 제공된 정보가 이용자를 제외한 그외의 자에게 유출되거나 유실되었을 경우 그 책임은 이용자가 진다.
</textarea></td>
						</tr>
					</tbody>
				</table>
			</div>
			
			<div class="tit_area">
				<h4 class="tit">제 8조 (등록인증 및 관리)</h4>
			</div>
			<div class="view_area">
				<table>
					<caption>제 8조 (등록인증 및 관리)</caption>
					<colgroup>
						<col style="width:auto;">
					</colgroup>
					<tbody>
						<tr> 
							<td><textarea taView style="display: none;">연구원은 전자조달의 안정성과 신뢰성 및 투찰금액의 비밀보장을 확보하기 위하여 계약부서와는 별도로 운영 및 관리부서를 지정.운영 한다.</textarea></td>
						</tr>
					</tbody>
				</table>
			</div>
			
			
			<div class="btn_wrap view_btn">
	           	<button type="button" class="btn btn_m btn_orange" id="apprBtn">동의합니다.</button>
		        <button type="button" class="btn btn_m btn_del" id="listBtn">동의하지 않습니다.</button>
			</div>
			
		</div>
	</form>

	
</div>