(function() {

	/**
	 * Default Constructor
	 */
	pageObj = {};
	
	var defaultFrm = "loginFrm";
	
	pageObj.iproEmplyrLogin = function() {
		FwkCmmnUtil.submitForm("loginFrm", "/main/mainPage.do");  
	};
	
	downloadManual = function(){
		FwkCmmnUtil.submitForm("manualFrm", "/comm/userManual.do");
	};
	
	
	pageObj.login = function() {
		
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

		var actionUrl = "/main/iproSBDC_sugarmanWorkConn.do";
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
					 *  그 외 각 환경에 맞는 메세지를 사용하여도 무관함. 
					 */
					var resultCode = res.loginResult.resultCode;
					if(resultCode == "0"){ //로그인성공
						pageObj.iproEmplyrLogin();
					}else{
						alert(res.loginResult.resultMessage);
					}
				}
			);
	};
	
	
	// 주민등록번호 로그인
	pageObj.rsdn_no_login = function(){
		
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
		
		var actionUrl = "/opro/main/oproSBDC_sugarmanWorkConn.do";
        var jsonParam = $("#loginFrm").serializeObject(); 
        FwkCmmnUtil.submitAjax(actionUrl, jsonParam, function(data) {
        	if(data.code != "success") {
        		alert("["+data.code+"]"+data.msg);
    		} else {
    			
    			// 쿠키 저장할지 체크			
    			pageObj.oproEmplyrLogin(); 
    		}
        });
        
	};
	
	
	// 로그인 성공 후 화면 호출
	pageObj.oproEmplyrLogin = function() {		
		$("#loginFrm input[name='resourceName']").val("OEP15001");
		FwkCmmnUtil.submitForm("loginFrm", "/opro/estm/estmCmtmProgList.do");
	};
	
	
	pageObj.setEventHandler = function() {
		
		$('#chk_div').css({'line-height':'17px'});
		$('#P_LOGIN_ID_COO').css({'float':'left'});
		$('#chk_span').css({'float':'left', 'vertical-align':'bottom'});
		
		$("#loginBtn").on("click", function() {
			pageObj.login();
		});
		
		
		// 주민등록번호 로그인
		$("#rsdnNoLoginBtn").on("click", function() {
			$("#P_LOGIN_GBN").val("CMTM");
			$("#P_LOGIN_MTHD").val("RSDNNO");
			pageObj.rsdn_no_login();
			return false;
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