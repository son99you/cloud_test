(function() {

	/**
	 * Default Constructor
	 */
	pageObj = {};
	
	var defaultFrm = "loginFrm";
	
/*	pageObj.emplyrLogin = function() {
		FwkCmmnUtil.submitForm("loginFrm", "/main/mainPage.do");  
	};*/
	
	pageObj.emplyrLogin = function() {		
		$("#loginFrm input[name='resourceName']").val("OEP15001");
		FwkCmmnUtil.submitForm("loginFrm", "/opro/estm/estmCmtmProgList.do");
	};
	
	downloadManual = function(){
		FwkCmmnUtil.submitForm("manualFrm", "/opro/comm/userManual.do");
	};
	
	
	pageObj.login = function() {
		$("#P_LOGIN_GBN").val("CMTM");
		$("#P_LOGIN_MTHD").val("RSDNNO");
		
		if(!$("#P_USR_ID").val()){
			alert("아이디를 입력해주세요.");
			$("#P_USR_ID").focus();
			return false;
		}
		if(!$("#P_PWD").val()){
			alert("비밀번호를 입력해주세요.");
			$("#P_PWD").focus();
			return false;
		}
		
		if( $("#P_RSDN_NO_1").val() == "" ){
			alert("주민등록번호를 입력해 주세요.");
			$("#P_RSDN_NO_1").focus();
			return;
		}
		if( $("#P_RSDN_NO_2").val() == "" ){
			alert("주민등록번호를 입력해 주세요.");
			$("#P_RSDN_NO_2").focus();
			return;
		}
		
		var rsdn_no_1 = $("#P_RSDN_NO_1").val();
		var rsdn_no_2 = $("#P_RSDN_NO_2").val();
		
		$("#P_LOGIN_ID").val(rsdn_no_1 + "" + rsdn_no_2);
		
		
		// 쿠키 저장할지 체크
		pageObj.cookiCheck();
		
		var actionUrl = "/opro/main/emgncCmtmEmplyrLogin.do";
		var jsonParam = $("#loginFrm").serializeObject();
		
		FwkCmmnUtil.submitAjax (actionUrl, jsonParam
				, function(res) {
					/**
					 *  resultCode type :
					 *  
					 *  default:  -999999999;
					 *  정상: 0
					 *  아이디가 없을 경우: -9999 		==
					 *  임시이용자: -1
					 *  계정잠금: -2						
					 *  계정만료: -3						==
					 *  비번 정책 실패: -4X
					 *  	임시비밀번호: -41
					 *  	비밀번호 실패 검증:-42
					 *  	비밀번호 유효일검증: -43
					 *  	비밀번호 실패 검증 횟수 초과: -44
					 *  	비밀번호 실패 검증 횟수 초과(계정잠금): -45
					 *  
					 *  resultMessage :
					 *  공통에서 제공하는 메세지
					 *  /oda.smp/src/main/webapp/WEB-INF/config/springmvc/oda-smp-servlet.xml 의 messageSource bean에 <value>classpath:/config/message/oda-com-message</value> 추가한다.
					 *  그 외 각 환경에 맞는 메세지를 사용하여도 무관함. 
					 *  	
					 *  
					 *  retUrl ->? 추후 IMS 서버 주소로 설정이 필요함.
					 *  goUrl  ->? 추후 IMS 서버 주소로 설정이 필요함.
					 *  									
					 */
			
	
					var resultCode = res.code;
					if(resultCode == "success"){ //로그인성공
						//alert("USR_ID :: " + res.loginResult.USR_ID);
						$("#P_USR_ID").val("");
						
						pageObj.emplyrLogin();
					}else{
						alert(res.msg);
					}
				}
			);
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
	
	//목록화면
	notiList = function(bbsSecd){
		$("#popupFrm input[name='P_BBS_SECD']").val(bbsSecd);
		
		$("#popupFrm").one("submit", function() {
			window.open("", "noticeFrm", "width=1024px,height=760px,toolbar=no,status=no,scrollbars=yes,resizeable=no,menubar=no,left=500,top=100");
			this.action = FwkCmmnUtil['contextPath']+'/opro/main/popup/noticeListPopup.do';
	        this.method = 'POST';
	        this.target = 'noticeFrm';
	    }).trigger("submit");
	}
	
	//공지 상세화면
	detailInqire = function(bbsSecd, bbsSn){
		$("#popupFrm input[name='P_BBS_SECD']").val(bbsSecd);
		$("#popupFrm input[name='P_BBS_SN']").val(bbsSn);
		
		$("#popupFrm").one("submit", function() {
			window.open("", "noticeDetailFrm", "width=1024px,height=760px,toolbar=no,status=no,scrollbars=yes,resizeable=no,menubar=no,left=500,top=100");
			this.action = FwkCmmnUtil['contextPath']+'/opro/main/popup/noticePopup.do';
	        this.method = 'POST';
	        this.target = 'noticeDetailFrm';
	    }).trigger("submit");
	}
	
	//manual 다운로드
	downloadManual = function(){
		FwkCmmnUtil.submitForm("manualFrm", "/opro/comm/userManual.do");
	}
	
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
	
	helpPopup = function() {
		$("#popupFrm").one("submit", function() {
			window.open("", "helpPopup", "width=600px,height=350px,toolbar=no,status=no,scrollbars=yes,resizeable=no,menubar=no,left=500,top=100");
			this.action = FwkCmmnUtil['contextPath']+'/opro/main/popup/helpPopup.do';
	        this.method = 'POST';
	        this.target = 'helpPopup';
	    }).trigger("submit");
	};
	
	/**
	 * window load
	 *
	 */
	$(function() {		
		pageObj.setEventHandler();
		helpPopup();
	});
	
})();