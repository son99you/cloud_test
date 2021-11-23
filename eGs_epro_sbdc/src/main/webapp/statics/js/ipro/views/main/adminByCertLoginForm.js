(function() {

	/**
	 * Default Constructor
	 */
	pageObj = {};
	
	var defaultFrm = "loginFrm";
	
	pageObj.emplyrLogin = function() {
		if($("#P_LOGIN_ID_COO").is(":checked")){//업체
			FwkCmmnUtil.submitForm("loginFrm", "/opro/main/mainPage.do");  
		}else{
			FwkCmmnUtil.submitForm("loginFrm", "/main/mainPage.do");  
		}
	};
	
	
	pageObj.login = function(first){
		if($("#P_LOGIN_ID_COO").is(":checked")){//업체
			if( $("#P_BIZRNO").val() == "" ){
				alert("사업자번호를 입력해 주세요.");
				$("#P_BIZRNO").focus();
				return;
			}
			
		}else{
			if($("#P_USR_ID").val() == ""){
				alert("아이디를 입력해주세요.");
				$("#P_USR_ID").focus();
				return false;
			}
			if($("#P_PWD").val() == ""){
				alert("비밀번호를 입력해주세요.");
				$("#P_PWD").focus();
				return false;
			}
		}
		pageObj.loginData(first);
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
			
			if($("#P_BIZRNO").val() != ""){
				var actionUrl = "/main/certLogin.do";
				var jsonParam = $("#loginFrm").serializeObject(); 
				FwkCmmnUtil.submitAjax(actionUrl, jsonParam, function(data) {
					
					if(data.code != "success") {
						alert("["+data.code+"]"+data.msg);
					} else {
						// 쿠키 저장할지 체크
						//pageObj.cookiCheck();	  
						pageObj.emplyrLogin(); 
					}
				});			
				
			}else{	//내부로그인
				pageObj.innerLogin();
			}
	        
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
	
	
	pageObj.innerLogin = function() {
		var actionUrl = "/main/emgncEmplyrLogin.do";
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
	
					var resultCode = res.loginResult.resultCode;
					if(resultCode == "0"){ //로그인성공
						pageObj.emplyrLogin();
					}else{
						alert(res.loginResult.resultMessage);
					}
				}
			);
	};
	
	
	pageObj.setEventHandler = function() {
		
		$('#chk_div').css({'line-height':'17px'});
		$('#P_LOGIN_ID_COO').css({'float':'left'});
		$('#chk_span').css({'float':'left', 'vertical-align':'bottom'});
		
		$("#bizrnoLoginBtn").on("click", function() {
			pageObj.login("H"); 
		})
		
		$('#P_LOGIN_ID_COO').css({'float':'left'});
		$('#chk_span').css({'float':'left', 'vertical-align':'bottom'});
		
		$("#P_LOGIN_ID_COO").on("click", function(){
			if($("#P_LOGIN_ID_COO").is(":checked")){	//업체
				$("#P_USR_ID").css("display","none");
				$("#P_BIZRNO").css("display","");
				$("#P_PWD").css("display","none");
			}else{
				$("#P_USR_ID").css("display","");
				$("#P_BIZRNO").css("display","none");
				$("#P_PWD").css("display","");
			}
		});
		
		FwkCmmnUtil.setEnterKeyBinding("loginFrm", ["P_USR_ID","P_PWD","P_BIZRNO"], function(res) {
			pageObj.login("H"); 
		});
	};

	/**
	 * window load
	 *
	 */
	$(function() {		
		pageObj.setEventHandler();

		if($("#P_LOGIN_ID_COO").is(":checked")){//업체
			$("#P_USR_ID").css("display","none");
			$("#P_BIZRNO").css("display","");
			$("#P_PWD").css("display","none");
		}else{
			$("#P_USR_ID").css("display","");
			$("#P_BIZRNO").css("display","none");
			$("#P_PWD").css("display","");
		}
	});
	
})();