<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 메인화면 > 회원가입 폼
 *
 * <pre>
 * main
 *    |_ mberSbscrbForm.jsp
 * 
 * </pre>
 * @date : 2015. 06. 08. 오전 10:10:32
 * @version : 1.0
 * @author : 은우소프트 전상훈
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %>

<link rel="stylesheet" type="text/css" href="${kicaPath}/WebUI/css/base.css" />

<script type="text/javascript" src="${jsPath}/opro/views/main/mberSbscrbForm.js"></script>  

<div class="contents_wrap">
	<ul class="step_wrap"></ul>
	
	<div class="tit_wrap">
		<h3 class="tit">회원가입</h3>
	</div>
	
	<form id="joinFrm" name="joinFrm" method="POST">
		<input type="hidden" id="resourceName" name="resourceName" value="${param.resourceName}">
		
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
								<textarea rows="3" style="width: 100%;  line-height: inherit;" readonly="readonly">
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
								<textarea style="width: 100%; height: 100px; border: 1px solid #DCDBDB; padding: 15px; color: #595959; text-align: left;  line-height: inherit;" readonly="readonly">
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
							<td><textarea taView style="display: none;">
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
							<td><textarea taView style="display: none;">
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
			
			
			<div class="tit_area">
				<h4 class="tit">제 9조 (등록승인, 유효기간, 변경 및 탈퇴)</h4>
			</div>
			<div class="view_area">
				<table>
					<caption>제 9조 (등록승인, 유효기간, 변경 및 탈퇴)</caption>
					<colgroup>
						<col style="width:auto;">
					</colgroup>
					<tbody>
						<tr> 
							<td><textarea taView style="display: none;">
①	연구원은 이용자 등록이 승인되었을 경우 이용자가 전자조달시스템 이용자 정보에 기입한 대표자 전자우편주소로 그 사실을 통보한다
②	이용자등록을 완료한 시기는 이용자가 전자적 교류 수단 사용이 가능한 시점을 말한다
③	이용자 등록 유효기간은 등록 승인일로부터 1년까지로 하되, 기등록자는 재등록을 신청할 수 있다.
④	이용자는 등록사항이 추가/변경되었을 경우 지체없이 ‘이용자등록정보 변경신청서’를 작성한 후 연구원이 정한 서류를 제출(인편/우편)하며, 만일 이를 준수치 아니한 경우 이용자정보는 변경될 수 없으며, 이로 인하여 발생되는 모든 책임은 이용자가 진다.
</textarea></td>
						</tr>
					</tbody>
				</table>
			</div>
			
			<div class="tit_area">
				<h4 class="tit">제 10조 (시스템의 운영중단 및 시스템 장애)</h4>
			</div>
			<div class="view_area">
				<table>
					<caption>제 10조 (시스템의 운영중단 및 시스템 장애)</caption>
					<colgroup>
						<col style="width:auto;">
					</colgroup>
					<tbody>
						<tr> 
							<td><textarea taView style="display: none;">①	연구원은 컴퓨터 등 전산장비의 보수점검 및 교체 등의 사유로 시스템의 서비스 운영을 일시적으로 중단할 수 있으며, 이 경우에는 시스템에 미리 공지한다.
②	연구원이 통제할 수 없는 시스템의 장애로 시스템 운영이 불가능하다고 판단 될 때에는 연구원은 입찰취소, 입찰연기, 재공고, 개찰의 일시중단 등 필요한 조치를 취할 수 있다.
③	제2항의 시스템 장애는 시스템의 가동 중단, 시스템에 연결된 모든 네트워크의 장애 등으로 시스템에 접속이 불가능하거나 전자문서의 송·수신이 불가능한 경우 등을 말한다.
④	연구원의 시스템 장애가 아닌 입찰자의 네트워크 또는 네트워크 서비스 업체의 장애, 기타 입찰자의 컴퓨터 등 전산장비 사정으로 인한 정보시스템 장애 등의 사유로 입찰서 등이 시스템에 입력되지 않을 경우(시스템 서버에 수신되지 않은 경우를 포함한다)에는 입찰자가 송신하지 아니한 것으로 본다.</textarea></td>
						</tr>
					</tbody>
				</table>
			</div>
			
			<div class="tit_area">
				<h4 class="tit">제 11조 (전자문서 송·수신 시기의 특약)</h4>
			</div>
			<div class="view_area">
				<table>
					<caption>제 11조 (전자문서 송·수신 시기의 특약)</caption>
					<colgroup>
						<col style="width:auto;">
					</colgroup>
					<tbody>
						<tr> 
							<td><textarea taView style="display: none;">
이 시스템에서 사용되는 전자문서 등 전자적 교류의 송·수신 확인 및 시기는 연구원의 전자조달 업무의 특성상 다음 각호에서 정한 바에 의한다.
1. 이 시스템을 제외한 전산장비에 입력된 전자적 교류행위는 그 출력 여부를 불문하고 송신되지 아니한 것으로 본다.
2. 이용자의 진의와 관계없이 일단 시스템 서버에 도달한 전자문서는 송·수신된 것으로 보며 수신된 문서의 무효 처리, 재송신 허용 등은 입찰공고 및 기타 관련 규정에 따른다.
3. 이 시스템, 입찰공고, 기타 입찰 관련 규정 등에서 규정한 경우를 제외하고는 이용자는 임의의 전자문서에 대하여 수신 확인 통지를 요청할 수 없으며 임의의 효력발생 조건을 붙일 수 없다.
</textarea></td>
						</tr>
					</tbody>
				</table>
			</div>
			
			<div class="tit_area">
				<h4 class="tit">제 12조 (연구원의 책임과 의무)</h4>
			</div>
			<div class="view_area">
				<table>
					<caption>제 12조 (연구원의 책임과 의무)</caption>
					<colgroup>
						<col style="width:auto;">
					</colgroup>
					<tbody>
						<tr> 
							<td><textarea taView style="display: none;">
①	연구원은 이 약관이 정하는 바에 따라 신뢰할 수 있는 서비스를 제공하기 위하여 노력한다.
②	연구원은 이용자의 신용정보를 포함한 정보보안에 대하여 최상의 안전조치를 강구할 의무를 진다.
</textarea></td>
						</tr>
					</tbody>
				</table>
			</div>
			
			
			<div class="tit_area">
				<h4 class="tit">제 13조 (이용자의 역할과 책임)</h4>
			</div>
			<div class="view_area">
				<table>
					<caption>제 13조 (이용자의 역할과 책임)</caption>
					<colgroup>
						<col style="width:auto;">
					</colgroup>
					<tbody>
						<tr> 
							<td><textarea taView style="display: none;">
①	이용자는 ID와 접속 비밀번호 및 전자서명 인증서를 관리하고 비밀을 유지할 책임이 있으며, ID와 접속 비밀번호 및 전자서명 인증서의 유출에 따른 책임은 이용자가 진다.
②	이용자는 자신의 전자서명 인증 관련 정보가 도난당하거나 제3자가 사용하고 있음을 인지한 경우에는 인증기관의 정책에 따라 가입인증기관에 통보하고 당해 전자서명 인증서의 폐지와 재발급 등 필요한 조치를 취하여야 한다.
③	컴퓨터 백신 프로그램 설치 등 시스템의 이용을 위하여 사용하는 개인용 컴퓨터에 대한 정보보안 책임은 이용자에게 있다.
</textarea></td>
						</tr>
					</tbody>
				</table>
			</div>
			
			
			<div class="tit_area">
				<h4 class="tit">제 14조 (관련 규정 및 집행 절차 준수)</h4>
			</div>
			<div class="view_area">
				<table>
					<caption>제 14조 (관련 규정 및 집행 절차 준수)</caption>
					<colgroup>
						<col style="width:auto;">
					</colgroup>
					<tbody>
						<tr> 
							<td><textarea taView style="display: none;">
이용자는 연구원의 입찰유의서, 전자입찰 특별유의서, 역경매방식 물품구매 전자입찰 특별유의서와 입찰공고, 이 시스템상의 이용 안내 및 사용자 매뉴얼 등 관련 규정과 절차를 준수하여야 하며, 이를 준수하지 않음으로써 발생하는 불이익에 대한 책임은 이용자에게 있다.</textarea></td>
						</tr>
					</tbody>
				</table>
			</div>
			
			
			<div class="tit_area">
				<h4 class="tit">제 15조 (분쟁 해결 및 재판 관할)</h4>
			</div>
			<div class="view_area">
				<table>
					<caption>제 15조 (분쟁 해결 및 재판 관할)</caption>
					<colgroup>
						<col style="width:auto;">
					</colgroup>
					<tbody>
						<tr> 
							<td><textarea taView style="display: none;">이용자는 연구원의 입찰유의서, 전자입찰 특별유의서, 역경매방식 물품구매 전자입찰 특별유의서와 입찰공고, 이 시스템상의 이용 안내 및 사용자 매뉴얼 등 관련 규정과 절차를 준수하여야 하며, 이를 준수하지 않음으로써 발생하는 불이익에 대한 책임은 이용자에게 있다.</textarea></td>
						</tr>
					</tbody>
				</table>
			</div>
			
			
			<div class="tit_area">
				<h4 class="tit">개인정보 수집 및 이용에 관한 동의</h4>
				<span style="float: right;"><label for="chk1">개인정보 수집 및 이용에 동의합니다.<input type="checkbox" id="chk1"></label></span>
			</div>
			<div class="view_area">
				<table>
					<caption>개인정보 수집 및 이용에 관한 동의</caption>
					<colgroup>
						<col style="width:auto;">
					</colgroup>
					<tbody>
						<tr> 
							<td>
								<textarea style="width: 100%; height: 150px; border: 1px solid #DCDBDB; color: #595959; text-align: left; line-height: inherit;" readonly="readonly">한국전기연구원 전자조달시스템 회원가입을 위하여 아래의 개인정보 수집·이용에 대한 내용을 자세히 읽어보신 후 동의 여부를 결정하여 주시기 바랍니다.

1. 수집하는 개인정보 항목
대표자명(대표자영문명), 담당자명, 직급, 부서, 연락처(이메일, 전화번호, 휴대전화번호)

2. 수집 및 이용 목적
중소기업유통센터 평가위원관리시스템의 개인정보는 전자조달을 위한 입찰 참여, 구매 계약 및 전자조달시스템 관리 서비스를 위한 목적으로만 사용됩니다. 

3. 개인정보의 보유 및 이용기간
수집된 개인정보는 정보제공자가 수집, 이용에 동의한 날로부터 보유하며, 전자조달시스템 탈퇴시 개인정보는 폐기됩니다. 

4. 동의 거부권과 불이익 내용
개인정보 수집 동의를 거부하실 수 있습니다. 다만, 동의하지 않을 경우 전자조달시스템 이용에 제한이 있을 수 있습니다.</textarea>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			
			
			<div class="tit_area">
				<h4 class="tit">개인정보 처리 방침</h4>
				<span style="float: right;"><label for="chk2">개인청보 처리방침을 동의합니다.<input type="checkbox" id="chk2"></label></span>
			</div>
			<div class="view_area">
				<table>
					<caption>개인정보 처리 방침</caption>
					<colgroup>
						<col style="width:auto;">
					</colgroup>
					<tbody>
						<tr> 
							<td>
								<textarea style="width: 100%; height: 150px; border: 1px solid #DCDBDB; color: #595959; text-align: left; line-height: inherit;" readonly="readonly">한국전기연구원은 개인정보 보호법 제30조에 따라 정보주체의 개인정보를 보호하고 이와 관련한 고충을 신속하고 원활하게 처리할 수 있도록 하기 위하여 다음과 같이 개인정보 처리지침을 수립․공개합니다.

1. 개인정보의 처리목적
전자조달을 위한 입찰 참여, 구매 계약 및 전자조달시스템 관리 서비스 제공.

2. 개인정보의 처리 및 보유기간
한국전기연구원은 법령에 따른 개인정보 보유·이용기간 또는 정보주체로부터 개인정보를 수집 시에 동의받은 개인정보 보유·이용기간 내에서 개인정보를 처리·보유합니다. 수집된 개인정보는 정보제공자가 수집, 이용에 동의한 날로부터 보유하며, 전자조달시스템 탈퇴시 개인정보는 폐기됩니다. 

3. 개인정보의 제3자 제공에 관한 사항
중소기업유통센터 평가위원관리시스템 정보주체의 개인정보를 ‘개인정보의 처리목적’에서 고지한 범위 내에서 명시한 범위 내에서만 처리하며 제 3자 제공을 하지 않습니다.


4. 개인정보 처리의 위탁에 관한 사항
한국전기연구원은 전자조달시스템의 원활한 운영 및 유지보수를 위해 정보주체의 개인정보를 외부에 위탁하여 처리 할 수 있으며, 처리를 위탁하는 경우 기술적·관리적·물리적 보호조치 의무를 이행하도록 관리감독을 시행하고 있습니다.

5. 정보주체와 법정대리인의 권리·의무 및 행사방법
정보주체는 한국전기연구원에 대해 언제든지 개인정보 열람․정정․삭제․처리정지 요구 등의 권리를 행사할 수 있습니다.
제1항에 따른 권리 행사는 한국전기연구원에 대해 개인정보 보호법 시행령 제41조제1항에 따라 서면, 전자우편, 모사전송(FAX) 등을 통하여 하실 수 있으며, 한국전기연구원은 이에 대해 지체없이 조치하겠습니다.
권리 행사는 정보주체의 법정대리인이나 위임을 받은 자 등 대리인을 통하여 하실 수 있습니다. 이 경우 개인정보 보호법 시행규칙 별지 제11호 서식에 따른 위임장을 제출하셔야 합니다.
개인정보 열람 및 처리정지 요구는 개인정보보호법 제35조 제5항, 제37조 제2항에 의하여 정보주체의 권리가 제한 될 수 있습니다.
개인정보의 정정 및 삭제 요구는 다른 법령에서 그 개인정보가 수집 대상으로 명시되어 있는 경우에는 그 삭제를 요구할 수 없습니다.
한국전기연구원은 정보주체 권리에 따른 열람의 요구, 정정·삭제의 요구, 처리정지의 요구 시 열람 등 요구를 한 자가 본인이거나 정당한 대리인인지를 확인합니다.

6. 처리하는 개인정보의 항목
1) 회원가입 시 : 대표자명(대표자영문명), 담당자명, 직급, 부서, 연락처(이메일, 전화번호, 휴대전화번호)

 2) 인터넷 서비스 이용과정에서 아래 개인정보 항목이 자동으로 생성되어 수집될 수 있습니다.

- IP주소, 쿠키, MAC주소, 서비스 이용기록, 방문기록, 불량 이용기록 등

7. 개인정보의 파기
한국전기연구원은 개인정보 보유기간의 경과, 처리목적 달성 등 개인정보가 불필요하게 되었을 때에는 지체없이 해당 개인정보를 파기합니다. 

정보주체로부터 동의받은 개인정보 보유기간이 경과하거나 처리목적이 달성되었음에도 불구하고 다른 법령에 따라 개인정보를 계속 보존하여야 하는 경우에는, 해당 개인정보(또는 개인정보파일)을 별도의 데이터베이스(DB)로 옮기거나 보관장소를 달리하여 보존합니다. 
개인정보 파기의 절차 및 방법은 다음과 같습니다. 

파기절차 
한국전기연구원은 파기하여야 하는 개인정보(또는 개인정보파일)에 대해 개인정보 파기계획을 수립하여 파기합니다. 한국전기연구원는 파기 사유가 발생한 개인정보(또는 개인정보파일)을 선정하고, 개인정보 보호책임자가 승인한 절차에 따라 개인정보(또는 개인정보파일)을 파기합니다. 

파기방법 
한국전기연구원은 전자적 파일 형태로 기록․저장된 개인정보는 기록을 재생할 수 없도록 7회 이상 덮어쓰기, 디가우징 등의 방법을 이용하여 파기하며, 종이 문서에 기록․저장된 개인정보는 분쇄기로 분쇄하거나 소각하여 파기합니다. 

8. 개인정보의 안전성 확보 조치
중소기업유통센터 평가위원관리시스템은 정보주체의 개인정보를 처리함에 있어 개인정보가 분실, 도난, 누출, 변조 또는 훼손되지 않도록 안전성 확보를 위하여 다음과 같은 조치를 취하고 있습니다.

관리적 조치 : 내부관리계획 수립․시행, 정기적 직원 교육 등
기술적 조치 : 비밀번호의 암호화, 해킹 또는 바이러스 백신프로그램 이용, SSL 설치 등
물리적 조치 : 전산실, 자료보관실 등의 접근통제

9. 개인정보 자동 수집 장치의 설치·운영 및 거부에 관한 사항
한국전기연구원은 이용자에게 개별적인 맞춤서비스를 제공하기 위해 이용정보를 저장하고 수시로 불러오는 ‘쿠기(cookie)’를 사용합니다. 쿠키는 웹사이트를 운영하는데 이용되는 서버(http)가 이용자의 컴퓨터 브라우저에게 보내는 소량의 정보이며 이용자의 PC 컴퓨터내의 하드디스크에 저장되기도 합니다.

쿠키의 사용목적: 이용자가 방문한 각 서비스와 웹 사이트들에 대한 방문 및 이용형태, 인기 검색어, 보안접속 여부, 등을 파악하여 이용자에게 최적화된 정보 제공을 위해 사용됩니다.
쿠키의 설치∙운영 및 거부 : 웹브라우저 상단의 도구인터넷 옵션개인정보 메뉴의 옵션 설정을 통해 쿠키 저장을 거부 할 수 있습니다.
쿠키 저장을 거부할 경우 맞춤형 서비스 이용에 어려움이 발생할 수 있습니다.

10. 개인정보 보호책임자
한국전기연구원은 개인정보 처리에 관한 업무를 총괄해서 책임지고, 개인정보 처리와 관련한 정보주체의 불만처리 및 피해구제 등을 위하여 아래와 같이 개인정보 보호책임자를 지정하고 있습니다. 정보주체께서는 한국전기연구원의 전자조달시스템을 이용하시면서 발생한 모든 개인정보 보호 관련 문의, 불만처리, 피해구제 등에 관한 사항을 개인정보 보호책임자 및 담당부서로 문의하실 수 있습니다. 한국전기연구원은 정보주체의 문의에 대해 지체없이 답변 및 처리해드릴 것입니다.

개인정보 보호책임자
성명 : 노판석
부서명 : 경영지원부
메일 : psnoh@keri.re.kr
전화번호 : 055-280-1200
팩스번호 : 055-280-1216
개인정보 보호담당자
성명 : 지현미
부서명 : 인력개발실
메일 : hmji@keri.re.kr
메일 : hmji@keri.re.kr
팩스번호 : 055-280-1266

11. 권익침해 구제방법
정보주체는 아래의 기관에 대해 개인정보 침해에 대한 피해구제, 상담 등을 문의하실 수 있습니다. 아래의 기관은 한국전기연구원과는 별개의 기관으로서, 한국전기연구원의 자체적인 개인정보 불만처리, 피해구제 결과에 만족하지 못하시거나 보다 자세한 도움이 필요하시면 문의하여 주시기 바랍니다.

개인정보 침해신고센터 (한국인터넷진흥원 운영)
소관업무 : 개인정보 침해사실 신고, 상담 신청
홈페이지 : privacy.kisa.or.kr
전화 : (국번없이) 118
주소 : (58324) 전남 나주시 진흥길 9(빛가람동 301-2) 3층 개인정보침해신고센터
개인정보 분쟁조정위원회
소관업무 : 개인정보 분쟁조정신청, 집단분쟁조정 (민사적 해결)
홈페이지 : www.kopico.go.kr
전화 : (국번없이) 1833-6972
주소 : (03171)서울특별시 종로구 세종대로 209 정부서울청사 4층
대검찰청 사이버범죄수사단 : 02-3480-3573 (www.spo.go.kr)
경찰청 사이버안전국 : 182 (http://cyberbureau.police.go.kr)</textarea>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			
			
			<div class="btn_wrap view_btn">
	           	<button type="button" class="btn btn_m btn_orange" id="agreeBtn">동의합니다.</button>
		        <button type="button" class="btn btn_m btn_del" id="disagreeBtn">동의하지 않습니다.</button>
			</div>
			
		</div>
	</form>
</div>

<!-- MAIN FORM -->
<form id="mainFrm" method="POST">
	<input type="hidden" name="resourceName" value="${param.resourceName}">
	<input type="hidden" name="resourceDesc" value="${param.resourceDesc}">
</form>
