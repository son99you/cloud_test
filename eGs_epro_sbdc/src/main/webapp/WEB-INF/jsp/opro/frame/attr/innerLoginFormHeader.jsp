<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%-- <script type="text/javascript" src="${jsPath}/opro/frame/bodyHeader.js"></script> --%> 

<header id="header" class="minw-1000">
		<h1> 
			<img src="${imagePath}/kto_sub/logo.png">
		</h1> 
		<ul class="hidden">
			<li>
				<a href="${contextPath}/opro/main/mainPage.do"><img src="${imagePath}/kto_sub/home_icon.png" alt="중소기업유통센터" ></a>
			</li>
			<li>
				${loginResult.USR_NM}님 로그인 하셨습니다.
			</li>
			<li>
				<a href="#" class="logout_btn" onClick="javascript:logout();">로그아웃</a>
			</li>
		</ul>
	</header>

	<!--class에 hidden을 추가하면 나타나지 않습니다.-->
	<!-- 남색바-->
	<nav id="top_nav_bar" class="">
		<div class="nav_line"></div>
	</nav>

	<!-- 메뉴 -->
	<nav id="top_nav_menu" class="hidden">
		<div class="menu_wrap">
			<ul class="menu">
				<li>
					<a href="">계약설계<span class="menu_line"></span></a>
				</li>
				<li>
					<a href="">입찰관리<span class="menu_line"></span></a>
				</li>
				<li>
					<a href="">계약관리<span class="menu_line"></span></a>
				</li>
				<li>
					<a href="">대금지급<span class="menu_line"></span></a>
				</li>
				<li>
					<a href="">정보관리<span class="menu_line"></span></a>
				</li>
				<li>
					<a href="">알림마당</a>
				</li>
			</ul>
		</div>
	</nav>

	<div id="main_login">
		<div class="login_wrap">
			<div class="login_depth_01">
				<div class="main_txt">
					<img src="${imagePath}/kto_sub/main_inner_txt.png">
				</div>
				<section class="login"> 
					<form id="loginFrm" name="loginFrm" class="loginForm">
						<input type="hidden" name="loginData" id="loginData" value='' />
						<div class="login_depth_01" style="width:1000px; margin:17px 40px 17px 40px;">
							<input type="text" id="P_LOGIN_ID" name="P_LOGIN_ID" value="${param.P_LOGIN_ID}" title="아이디를 입력하십시오." placeholder="아이디를 입력하십시오."  style="margin-bottom:5px; height: 30px; margin-left:10px;">
							<input type="password" id="P_PWD" name="P_PWD" value="${param.P_PWD}"title="비밀번호를 입력하십시오." placeholder="비밀번호를 입력하십시오."  style="margin-bottom:5px; height: 30px; margin-left:10px;">
							<input type="text" id="P_OTP_NO" name="P_OTP_NO" value="${param.P_OTP_NO}" title="OTP번호를 입력하십시오." placeholder="OTP번호를 입력하십시오." numeric  style="margin-bottom:5px; height: 30px; margin-left:10px;">
							<a href="#" class="login_btn" id="userLoginBtn" onsubmit="return false;">로그인</a>
						</div>
					</form>
				</section>
			</div>
		</div>
	</div>

