(function() {
	
	pageObj = {};

/**
 * 메인 > bodyHeader
 *
 * <pre>
 * main
 *    |_ innerLoginFormr.js
 * 
 * </pre>
 * @date : 2015. 03. 12. 오전 9:57:00
 * @version : 1.0
 * @author : 은우소프트 김봉수 
 */

    /**  
     * <pre>
     * 1. 개요 : TOP메뉴 클릭시 해당 LEFT메뉴 셋팅 및 첫 메뉴 이동 처리
     * 2. 처리내용 : 
     * 		TOP메뉴 클릭시 해당 LEFT메뉴 셋팅 및 첫 메뉴 이동 처리한다.
     *  	@@@@@ 계정관리에서 정보를 받아서 추후 자동 처리해야함.
     *  
     * </pre>
     * @Function Name : clickLeftMenuMove
     * @date : 2015. 03. 12.
     * @author : 은우소프트 김봉수
     * @history : 
     *  =======================================================
     *  변경일             		작성자                     		변경내용  
     *  =======================================================
     *  2015. 03. 12.       은우소프트 김봉수              최초 작성 
     *  =======================================================   
     */
	
	detailInqire = function(bbsSecd, bbsSn){
		$("#notiDetailFrm input[name='P_BBS_SECD']").val(bbsSecd);
		$("#notiDetailFrm input[name='P_BBS_SN']").val(bbsSn);
		$("#notiDetailFrm").one("submit", function() {
			window.open("", "noticePopup", "width=750px,height=760px,toolbar=no,status=no,scrollbars=yes,resizeable=no,menubar=no,left=500,top=100");
			this.action = FwkCmmnUtil['contextPath']+'/opro/main//popup/noticePopup.do';
	        this.method = 'POST';
	        this.target = 'noticePopup';
	    }).trigger("submit");
		//FwkCmmnUtil.submitForm("notiDetailFrm", "/opro/main//popup/noticePopup.do");
	}
	
	notiList = function(bbsSecd){
		$("#notiDetailFrm input[name='P_BBS_SECD']").val(bbsSecd);
		$("#notiDetailFrm").one("submit", function() {
			window.open("", "noticeListPopup", "width=750px,height=760px,toolbar=no,status=no,scrollbars=yes,resizeable=no,menubar=no,left=500,top=100");
			this.action = FwkCmmnUtil['contextPath']+'/opro/main/popup/noticeListPopup.do';
			this.method = 'POST';
			this.target = 'noticeListPopup';
		}).trigger("submit");
	}
	/**  
     * <pre>
     * 1. 개요 : 로그아웃 클릭시 페이지 이동
     * 2. 처리내용 : 
     * 		로그아웃 클릭시 해당 세션의 정보를 지우고
     * 		로그인 페이지 화면으로 이동한다.
     *  
     * </pre>
     * @Function Name : logout
     * @date : 2015. 05. 04.
     * @author : 은우소프트 손연우
     * @history : 
     *  =======================================================
     *  변경일             		작성자                     		변경내용  
     *  =======================================================
     *  2015. 05. 04.       은우소프트 손연우              최초 작성 
     *  =======================================================   
     */
	
	logout = function(){
		
//		window.close();
		if(!confirm("로그아웃 하시겠습니까?")){
			return false;
		}
		
		var actionUrl = "/opro/main/logout.do";
		var jsonParam = $("#logOutFrm").serializeObject();
		
		FwkCmmnUtil.submitAjax (actionUrl, jsonParam
			, function(res) {
				FwkCmmnUtil.submitForm("logOutFrm","/opro/main/innerLoginForm.do");
			}
		);
		//FwkCmmnUtil.submitForm("logOutFrm","/main/emgncEmplyrLogout.do");
	};
	
	emplyrLogin = function() {	
		$("#notiDetailFrm input[name='resourceName']").val("IOEP04001");
		FwkCmmnUtil.submitForm("notiDetailFrm", "/opro/cont/contInnerProgrList.do");  
	};
	
	pageObj.cookiCheck = function() {
		if($("#P_LOGIN_ID_COO").is(":checked")){
			if(getCookie("userInputId") != $("#P_LOGIN_ID").val()){
				setCookie("userInputId", $("#P_LOGIN_ID").val(), 7);
			}
		}else{
			setCookie("userInputId", $("#P_LOGIN_ID").val(), -1);
		}
	};
	
	setCookie = function(cookieName, value, exdays){
	    var exdate = new Date();
	    exdate.setDate(exdate.getDate() + exdays);
	    var cookieValue = escape(value) + ((exdays==null) ? "" : "; expires=" + exdate.toGMTString());
	    document.cookie = cookieName + "=" + cookieValue;
	};
	
	getCookie = function(cookieName) {
	    cookieName = cookieName + '=';
	    var cookieData = document.cookie;
	    var start = cookieData.indexOf(cookieName);
	    var cookieValue = '';
	    if(start != -1){
	        start += cookieName.length;
	        var end = cookieData.indexOf(';', start);
	        if(end == -1)end = cookieData.length;
	        cookieValue = cookieData.substring(start, end);
	    }
	    return unescape(cookieValue);
	};	
	
	pageObj.userLogin = function() {	
		//로그인...
		if( $("#P_LOGIN_ID").val() == "" ){
			alert("아이디를 입력해 주세요.");
			$("#P_LOGIN_ID").focus();
			return;
		}
		if( $("#P_PWD").val() == "" ){
			alert("비밀번호를 입력해 주세요.");
			$("#P_PWD").focus();
			return;
		}
		if( $("#P_OTP_NO").val() == "" ){
			alert("OTP번호를 입력해 주세요.");
			$("#P_OTP_NO").focus();
			return;
		}
		
		var jsonData = $("#loginFrm").serializeObject();
		var actionUrl = "/opro/main/loginIdPw"; 
		
		FwkCmmnUtil.submitAjax(actionUrl, jsonData, function(res) {
			var resultCode = res.loginResult.resultCode;
			if(resultCode == "0"){ //로그인성공
				emplyrLogin();
			}else{
				alert(res.loginResult.resultMessage);
			}
		}); 
	};
	
	pageObj.userLogin_dev = function() {	
		//로그인...
		if( $("#P_LOGIN_ID").val() == "" ){
			alert("아이디를 입력해 주세요.");
			$("#P_LOGIN_ID").focus();
			return;
		}
		if( $("#P_PWD").val() == "" ){
			alert("비밀번호를 입력해 주세요.");
			$("#P_PWD").focus();
			return;
		}
		if( $("#P_OTP_NO").val() == "" ){
			alert("OTP번호를 입력해 주세요.");
			$("#P_OTP_NO").focus();
			return;
		}
		var jsonData = $("#loginFrm").serializeObject();
		var actionUrl = "/opro/main/loginIdPw_dev"; 
		
		FwkCmmnUtil.submitAjax(actionUrl, jsonData, function(res) {
			var resultCode = res.loginResult.resultCode;
			if(resultCode == "0"){ //로그인성공
				emplyrLogin();
			}else{
				alert(res.loginResult.resultMessage);
			}
		}); 
	};

	downloadManual = function(){
		FwkCmmnUtil.submitForm("manualFrm", "/comm/userManual.do");
	}
	
	pageObj.setEventHandler = function() { 
		
		$("#userLoginBtn").on("click", function() {
			//운영 시
			pageObj.userLogin();
			//개발 시 pageObj.userLogin_dev(); 
		});
		
		FwkCmmnUtil.setEnterKeyBinding("loginFrm", ["P_LOGIN_ID","P_PWD","P_OTP_NO"], function(res) {
			//운영 시
			pageObj.userLogin(); 
			//개발 시 pageObj.userLogin_dev();
		});
		
	};

	/**
	 * window load
	 *
	 */
	$(function() {
		pageObj.setEventHandler();
	});
	
})();