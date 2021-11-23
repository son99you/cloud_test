(function() {

	/**
	 * Default Constructor
	 */
	pageObj = {};
	
	var defaultFrm = "loginFrm";
	
	pageObj.login = function() {
		var cookieStr = getCookie("access_token");
		if(cookieStr == undefined || cookieStr == null || cookieStr == "") {
			cookieStr = getCookie("refresh_token");
		}
		$('#loginFrm input[name=P_ACCESS_TOKEN]').val(cookieStr);
		
		if(cookieStr == undefined || cookieStr == null || cookieStr == "") {
			FwkCmmnUtil.loadingImage();
			
//			
/*			
			var sysCd = "C0010018";
  			var response_type = "code";
			var client_id = "2e613055bb604bc4a192cd7b6712cd87";
			var rtnUrl = "http%3A%2F%2Fdevbid.keri.re.kr%2Fmain%2FssoLoginForm.do";
			var scope = "http://sso.keri.re.kr";
			var state = "";*/
			
			// MESSAGE CODE 값 MssageUtil.js에 정의
			var sysCd = FwkMssageUtil.getMessage("SSO.SYS.CD");
			var response_type = FwkMssageUtil.getMessage("SSO.RESPONSE.TYPE");
			var client_id = FwkMssageUtil.getMessage("SSO.CLIENT.ID");
			var rtnUrl = FwkMssageUtil.getMessage("SSO.SYS.URL");
			var scope = FwkMssageUtil.getMessage("SSO.SCOPE");
			var url = FwkMssageUtil.getMessage("SSO.URL");
			
			var state = "";
			
			FwkCmmnUtil.submitForm("loginFrm", url+"?sysCd="+sysCd+"&rtnUrl="+rtnUrl+"");
			//FwkCmmnUtil.submitForm("loginFrm", "http://devkepos.keri.re.kr/kp/index.jsp?sysCd="+sysCd+"&rtnUrl="+rtnUrl+"");
		}else {
			var actionUrl = "/main/ssoEmplyrLogin.do";
			var jsonParam = $("#loginFrm").serializeObject();
			
			FwkCmmnUtil.submitAjax (actionUrl, jsonParam
					, function(res) {
				
						pageObj.emplyrLogin();	
					}
				);			
		}
	};
	
	pageObj.emplyrLogin = function() {
		FwkCmmnUtil.submitForm("loginFrm", "/main/mainPage.do");  
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
	
	pageObj.cookiCheck = function() {
		if($("#P_LOGIN_ID_COO").is(":checked")){
			if(getCookie("userInputId") != $("input[name='P_USR_ID']").val()){
				setCookie("userInputId", $("input[name='P_USR_ID']").val(), 7);
			}
		}else{
			setCookie("userInputId", $("input[name='P_USR_ID']").val(), -1);
		}
	};
	
	
	pageObj.setEventHandler = function() {
		
		$('#chk_div').css({'line-height':'17px'});
		$('#P_LOGIN_ID_COO').css({'float':'left'});
		$('#chk_span').css({'float':'left', 'vertical-align':'bottom'});
		
		$("#loginBtn").on("click", function() {
			pageObj.login();
		});
		
		
		FwkCmmnUtil.setEnterKeyBinding("loginFrm", ["P_USR_ID","P_PWD"], function(res) {
			pageObj.login();
		});
		
		// 쿠키에 저장된 값 불러오기
//	    if($("input[name='P_USR_ID']").val() == ""){
//	    	$("input[name='P_USR_ID']").val(getCookie("userInputId"));
//	    }
		// 쿠키에 저장되 있을시 체크박스 체크
//	    if($("input[name='P_USR_ID']").val() != ""){ 
//	        $("#P_LOGIN_ID_COO").attr("checked", true);
//	    }
		
	};

	/**
	 * window load
	 *
	 */
	$(function() {		
		pageObj.setEventHandler();
		pageObj.login();
	});
	
})();