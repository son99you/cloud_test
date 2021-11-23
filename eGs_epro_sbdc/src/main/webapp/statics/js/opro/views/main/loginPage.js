(function() {

	/**
	 * Default Constructor
	 */
	pageObj = {};
	
	pageObj.emplyrLogin = function() {	
		FwkCmmnUtil.submitForm("loginFrm", "/opro/main/mainPage.do");  
	};
	
	
	// 회원가입
	pageObj.emplyrJoin = function() {	
		FwkCmmnUtil.submitForm("agreeFrm", "/opro/main/mberSbscrbForm.do");
		//FwkCmmnUtil.submitForm("loginFrm", "/opro/main/joinFormPage.do");  
	};
	
	pageObj.login = function(first){
		if( $("#P_LOGIN_ID_VIEW").val() == "" ){
			alert("사업자번호를 입력해 주세요.");
			$("#P_LOGIN_ID_VIEW").focus();
			return;
		}
		pageObj.loginData(first);
	};
	
	setSubmitVisible = function() {
		var p_sServerTime = $("#serverTime").text();
		//var p_sServerTime = document.getElementById( "serverTime" ).value;
		
		/*var p_sYear 	= new Number(p_sServerTime.substring(0, 4));
		var p_sMonth 	= new Number(p_sServerTime.substring(4, 6));
		var p_sDay 		= new Number(p_sServerTime.substring(6, 8));
		var p_sHour 	= new Number(p_sServerTime.substring(8, 10));
		var p_sMinute 	= new Number(p_sServerTime.substring(10, 12));
		var p_sSecond 	= new Number(p_sServerTime.substring(12, 14));*/
		var p_sYear 	= new Number(p_sServerTime.substring(0, 4));
		var p_sMonth 	= new Number(p_sServerTime.substring(6, 8)) - 1;
		var p_sDay 		= new Number(p_sServerTime.substring(10, 12));
		var p_sHour 	= new Number(p_sServerTime.substring(14, 16));
		var p_sMinute 	= new Number(p_sServerTime.substring(17, 19));
		var p_sSecond 	= new Number(p_sServerTime.substring(20, 22));
		var	now	= new Date(p_sYear, p_sMonth, p_sDay, p_sHour, p_sMinute);
	};
	

	pageObj.loginData = function(first) {
	    var verifyVID = true;
	    var options = {};
		
	    var regNum = $("#P_LOGIN_ID_VIEW").val();
	    regNum= regNum.replace( /-/g, "");
	    $("#P_LOGIN_ID").val(regNum);
	    
	    var sessionId = "<%= session.getId() %>";
	    var ssn = regNum;
	    var userInfo = "1:" + regNum;
	    if(verifyVID == true) {
			if(ssn.length == 10) {
				options.ssn = ssn;
			}
			else {
				alert("사업자번호를 정확하게 입력하세요.");
				return;
			}
	    }
	    
	    var option = {
	    		encoding : 'base64'
	    		, charset : 'utf-8'
	    		, signtype : '2'
	    };
	    var config = {
	    		certificateClass : '16'
	    };
		config.OID = FwkMssageUtil.getMessage("CONFIG.OID");
		config.firstTrigger = first;
		yettie.init(config);
		
	    yettie.signWithVerifyVID(options.ssn ,options.ssn, option, pageObj.login_data_complete_callback, pageObj.error_callback);
	};


	pageObj.login_data_complete_callback = function(res) {
		if (res.signature != "") {
			$("#loginData").val(res.signature);
//			$("#strCert").val(res.data.certInfo["cert"]);
			
	        var actionUrl = "/opro/main/login.do";
	        var jsonParam = $("#loginFrm").serializeObject(); 
	        FwkCmmnUtil.submitAjax(actionUrl, jsonParam, function(data) {
	        	
	        	if(data.code != "success") {
	        		alert("["+data.code+"]"+data.msg);
	    		} else {
	    			// 쿠키 저장할지 체크
	    			pageObj.cookiCheck();	    			
	    			
	    			pageObj.emplyrLogin();
	    		}
	        });			
		}else {
//	        alert("error code = " + res.code + ", message = " + res.errorMessage);
			alert("서명에 실패했습니다.");
			return ;
	    }
	};
	
	pageObj.success_callback = function(res) {
		alert("에러");
	};
	
	pageObj.error_callback = function(error) {
		if(error.code != -9999){ // 취소 버튼 이벤트 error.code: -9999
			alert(error.msg);
		}  
	};		
	
	pageObj.loginByIdPw = function() {
		
		if(!$("#P_ID_PW_LOGIN_ID").val()){
			alert("아이디를 입력해주세요.");
			$("#P_ID_PW_LOGIN_ID").focus();
			return false;
		}
		if(!$("#P_ID_PW_LOGIN_PASSWORD").val()){
			alert("비밀번호를 입력해주세요.");
			$("#P_ID_PW_LOGIN_PASSWORD").focus();
			return false;
		}
		
		$("#P__HDN_LOGIN_ID").val($("#P_ID_PW_LOGIN_ID").val());
		$("#P__HDN_LOGIN_PASSWORD").val($("#P_ID_PW_LOGIN_PASSWORD").val());
		
		var actionUrl = "/opro/main/loginIdPw.do";
		var jsonParam = $("#idPwLoginFrm").serializeObject();
		
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
					var resultCode = res.loginResult.resultCode;
					if(resultCode == "0"){ //로그인성공
						pageObj.emplyrLogin();
					}else{
						alert(res.loginResult.resultMessage);
					}
				}
			);
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
	
	movePage = function(url, resName) {
		$("#moveFrm input[name='resourceName']").val(resName);
		FwkCmmnUtil.submitForm("moveFrm", url);  
	};
	
	// 다운로드
	download = function(fileSn, fileGrpNo) {
		$("#downloadFrm input[name='P_FILE_SN']").val(fileSn);
		$("#downloadFrm input[name='P_FILE_GRP_NO']").val(fileGrpNo);
		FwkCmmnUtil.submitForm("downloadFrm", "/comm/download.do" );
	};
	
	
	// 부서안내
	deptGuidList = function(){

		$("#popupFrm").one("submit", function() {
			window.open("", "deptGuidListPopup", "width=750px,height=760px,toolbar=no,status=no,scrollbars=yes,resizeable=no,menubar=no,left=500,top=100");
			this.action = FwkCmmnUtil['contextPath']+'/opro/main/popup/deptGuidList.do';
	        this.method = 'POST';
	        this.target = 'deptGuidListPopup';
	    }).trigger("submit");
		
	};
	
	
	
	// 사용자 PC 환경설정
	usrPCSet = function(){
		$("#popupFrm").one("submit", function() {
			window.open("", "usrPCSetPopup", "width=750px,height=760px,toolbar=no,status=no,scrollbars=yes,resizeable=no,menubar=no,left=500,top=100");
			this.action = FwkCmmnUtil['contextPath']+'/opro/main/popup/usrPCSet.do';
	        this.method = 'POST';
	        this.target = 'usrPCSetPopup';
	    }).trigger("submit");
	};
	
	// 용어정리
	trmList = function(){
		$("#popupFrm").one("submit", function() {
			window.open("", "trmListPopup", "width=750px,height=760px,toolbar=no,status=no,scrollbars=yes,resizeable=no,menubar=no,left=500,top=100");
			this.action = FwkCmmnUtil['contextPath']+'/opro/main/popup/trmList.do';
	        this.method = 'POST';
	        this.target = 'trmListPopup';
	    }).trigger("submit");
	};
	
	pageObj.setEventHandler = function() { 
		$(".loginBtn").on("click", function() {
			pageObj.login("hibiscus");
			return false;
		});
		
		$(".loginBtn2").on("click", function() {
			pageObj.login("H");
			return false;
		});
		
		// 회원가입 버튼
		$("#joinBtn").on("click", function() {
			pageObj.emplyrJoin();
			return false;
		});
		
		// ID/PW 로그인 버튼
		$("#idPwLoginBtn").on("click", function() {
			pageObj.loginByIdPw();
		});
		
		
		FwkCmmnUtil.setEnterKeyBinding("idPwLoginFrm", ["P_ID_PW_LOGIN_ID","P_ID_PW_LOGIN_PASSWORD"], function(res) {
			pageObj.loginByIdPw();
		});
		
		FwkCmmnUtil.setEnterKeyBinding("loginFrm", ["P_LOGIN_ID_VIEW"], function(res) {
			pageObj.login("hibiscus");
		});
		
	};

	/**
	 * window load
	 *
	 */
	$(function() {
		pageObj.setEventHandler();
		window.setInterval( setSubmitVisible, 1000 );
		serverTime($("#serverTime").text(), $("#sevrTime").val());
		
		if(getCookie("userInputId") != null || getCookie("userInputId") != "undefined") {
			$("#P_LOGIN_ID").val(getCookie("userInputId"));
			$("#P_LOGIN_ID_VIEW").val(getCookie("userInputId"));
			$("#P_LOGIN_ID_COO").attr("checked", true);
		}
	});
	
})();