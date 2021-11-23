<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 로그인화면
 *
 * <pre>
 * main 
 *    |_ loginPage.jsp
 * 
 * </pre>
 * @date : 2017. 06. 15
 * @version : 1.0
 * @author : 은우소프트 하성윤
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %>

<script src="${jsPath}/opro/views/main/loginPage.js"></script>

<%-- <script src="${contextPath}/vestsign/vestsign.js"></script> --%>
<script src="${contextPath}/vestsign/library/json3.min.js"></script>
<script src="${contextPath}/vestsign/vestsign_ew.js"></script>

<section id="container" class="member_wrap">
	<div class="inner">
		<div class="contents">
			<div class="conts_left">
				<div class="tit_area">
					<p class="tit">사업자등록번호를 입력하십시오.</p>
					<form id="loginFrm" name="loginFrm" class="loginForm">
						<input type="hidden" name="loginData" id="loginData" value='' />
						<input type="hidden" name="strCert" id="strCert" value='' />
						
						<fieldset>
							<legend>사업자등록번호 입력</legend>
							<input type="text" title="사업자등록번호 입력" id="P_LOGIN_ID_VIEW" value="${P_LOGIN_ID_VIEW }">
							<input type="hidden" id="P_LOGIN_ID" name="P_LOGIN_ID">
							<div class="btn_wrap">
								<button type="button" class="btn btn_s2 btn_c1 loginBtn" id="bizrnoLoginBtn" onsubmit="return false;">로그인</button>
								<button type="button" class="btn btn_s2 btn_c2" id="joinBtn" onsubmit="return false;">회원가입</button>
							</div>
							<div id="chk_div">
								<label id="chk_lab"><input type="checkbox" id="P_LOGIN_ID_COO" name="P_LOGIN_ID_COO">
								<span id="chk_span">&nbsp;아이디저장</span></label>
							</div>
						</fieldset>
					</form>
				</div> <!--// tit_area E -->
				
				<div class="bidding_area">
					<span class="tit">입찰기준시각</span>
					<div class="time_area">
						<span class="date" id="serverDate"></span>
						<span class="time" id="serverTime"></span>
					</div>
				</div> <!--// bidding_area E -->
				
				<ul class="link_area">
					<li><a href="http://www.g2b.go.kr/" rel="noopener noreferrer" target="_blank"><img src="${imagePath}/opro/main/link_img01.jpg" alt="나라장터 국가종합전자조달, 조달청 업체정보 연계 (1588-0800)"></a></li>
					<li><a href="https://www.mss.go.kr/" rel="noopener noreferrer" target="_blank"><img src="${imagePath}/opro/main/link_img02.jpg" alt="중소기업청 공공구매, 종합정보망(1577-7531)"></a></li>
					<li><a href="https://www.sgic.co.kr/" rel="noopener noreferrer" target="_blank"><img src="${imagePath}/opro/main/link_img03.jpg" alt="SGI서울보증 사이버지점, 연계증권 발행(02-3671-7000)"></a></li>
					<li><a href="https://www.ksfc.or.kr/" rel="noopener noreferrer" target="_blank"><img src="${imagePath}/opro/main/link_img04.jpg" alt="소프트웨어공제조합, 연계증권 발행(02-2141-7874)"></a></li>
				</ul> <!--// link_area E -->
			</div> <!--// conts_left E -->
			<div class="conts_right">
				<div class="tit_area">
					<div class="location_area">
						<span>로그인</span>
					</div>
					<h2 class="tit">로그인</h2>
				</div> <!--// tit_area E -->
				<div class="conts_wrap">
					<ul class="login_wrap">
						<li>
							<h3 class="tit">브라우저인증서 로그인</h3>
							<button type="button" class="btn btn_s3 btn_c1 loginBtn">브라우저 로그인</button>
							<p class="conts">구매업무를 진행하기 위한 기본 로그인방식으로 아래<br>안내를 참고하여 로그인하시기 바랍니다.</p>
							<a href="#" class="btn_link">브라우저 인증서 이용안내</a>
						</li>
						<li>
							<h3 class="tit">아이디/비밀번호 로그인</h3>
								<fieldset>
									<legend>아이디/비밀번호 로그인</legend>											
											<div class="login_area">
												<dl>
													<dt><label for="P_ID_PW_LOGIN_ID">아이디</label></dt>
													<dd><input type="text" id="P_ID_PW_LOGIN_ID" name="P_LOGIN_ID" value="" maxlength="10"></dd>
													<dt><label for="P_ID_PW_LOGIN_PASSWORD">비밀번호</label></dt>
													<dd><input type="password" id="P_ID_PW_LOGIN_PASSWORD" name="P_LOGIN_PASSWORD" value=""></dd>
												</dl>
											</div> <!--// login_area E -->
										<!-- <button type="submit" class="btn btn_s4 btn_c1">로그인</button> -->
										<button type="button" class="btn btn_s4 btn_c1" id="idPwLoginBtn" onsubmit="return false;">로그인</button>
								</fieldset>								 
							<p class="conts">견적제출, 입찰, 계약 등에 참여하지 않고 단순한 조회<br>업무만 진행할 경우 권장합니다.</p>
						</li>
					</ul> <!--// login_wrap E -->
					<div class="file_login_wrap">
						<div class="txt_area">
							<h3 class="tit">브라우저인증서 로그인이 불가능한 경우</h3>
							<p class="conts">사용자의 PC에 인증서실행프로그램을 설치하여 인증서 로그인 처리합니다.<br>해당 방법은 사용자PC에 파일이 설치되어 실행되므로 <em>브라우저인증서 로그인이 불가능한<br>경우에만</em> 권장합니다.</p>
						</div> <!--// txt_area E -->
						<a href="#" class="btn_file loginBtn2">실행파일 로그인</a>
					</div> <!--// file_login_wrap E -->
					<div class="link_wrap01">
						<ul>
							<li>
								<a href="javascript:download('1', 'VEND_MANUAL')" title="전자입찰 이용안내">
									<img src="${imagePath}/opro/main/link_icon01.png" alt="전자입찰 이용안내">
									<span class="conts">전자입찰<br>이용안내</span>
								</a>
							</li>
							<li>
								<a href="javascript:deptGuidList()" title="부서안내">
									<img src="${imagePath}/opro/main/link_icon02.png" alt="부서안내">
									<span class="conts">부서안내</span>
								</a>
							</li>
							<li>
								<a href="javascript:movePage('/opro/noti/faqList.do','opm704')">
									<img src="${imagePath}/opro/main/link_icon03.png" alt="자주묻는 질문">
									<span class="conts">자주묻는<br>질문</span>
								</a>
							</li>
							<li>
								<a href="javascript:movePage('/opro/noti/notiList.do','opm701')" title="공지사항">
									<img src="${imagePath}/opro/main/link_icon04.png" alt="공지사항">
									<!-- <span class="conts">업무안내<br>정보제공</span> -->
									<span class="conts">일반<br>공지사항</span>
								</a>
							</li>
							<li>
								<a href="javascript:usrPCSet()" title="사용자PC 환경설정">
									<img src="${imagePath}/opro/main/link_icon05.png" alt="사용자PC 환경설정">
									<span class="conts">사용자PC<br>환경설정</span>
								</a>
							</li>
							<li>
								<a href="http://www.tradesign.net/ra/keri" target="_blank" title="공인인증서 발급안내">
									<img src="${imagePath}/opro/main/link_icon06.png" alt="공인인증서 발급안내">
									<span class="conts">공인인증서<br>발급안내</span>
								</a>
							</li>
							<li>
								<a href="javascript:trmList()" title="용어정리">
									<img src="${imagePath}/opro/main/link_icon09.png" alt="용어정리">
									<span class="conts">용어정리</span>
								</a>
							</li>
						</ul>
					</div> <!--// link_wrap01 E -->
				</div> <!--// conts_wrap E -->
			</div> <!--// conts_right E -->
		</div> <!--// contents E -->
	</div><!--//inner E-->
</section><!--// container E-->

<form id="idPwLoginFrm" name="idPwLoginFrm">
	<input type="hidden" id="P__HDN_LOGIN_ID" name="P_LOGIN_ID">
	<input type="hidden" id="P__HDN_LOGIN_PASSWORD" name="P_LOGIN_PASSWORD">
</form>

<form id="agreeFrm" class="search_form" method="POST">
	<input type="hidden" name="resourceDesc" value="${ param.resourceDesc }"> 
	<input type="hidden" name="resourceName">
</form>

<form id="popupFrm" method="POST">
	<input type="hidden" name="resourceDesc" value="${ param.resourceDesc }"> 
	<input type="hidden" name="resourceName" value="${ param.resourceName }"> 
</form>

<%-- MOVE FORM --%>
<form id="moveFrm" class="search_form" method="POST">
	<input type="hidden" name="resourceDesc" value="${ param.resourceDesc }"> 
	<input type="hidden" name="resourceName">
	<input type="hidden" name="contextPath" value="${contextPath}">
</form>

<form id="downloadFrm" method="POST">
 	<input type="hidden" name="P_FILE_SN">
 	<input type="hidden" name="P_FILE_GRP_NO">
</form>