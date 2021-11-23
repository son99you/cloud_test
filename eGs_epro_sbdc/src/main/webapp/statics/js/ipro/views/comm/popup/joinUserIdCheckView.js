(function() {

	/**
	 * Default Constructor
	 */
	pageObj = {};
	
	/**
	 * Default Form Name
	 */
	var defaultFrm = "searchFrm";
	
	// 비밀번호 변경
	pageObj.userPwdChange = function() {

		var jsonData = $("#searchFrm").serializeObject();
		var actionUrl = "/sytm/userPwdChange";
		FwkCmmnUtil.submitAjax (actionUrl, jsonData
				, function(res) {
			
			var resultCode = res.pwdChangeResult.resultCode;
			if(resultCode == "0"){ //비밀번호 변경 성공
				window.open('', '_self').close();
				alert("비밀번호가 변경되었습니다.");
			}else{
				alert(res.pwdChangeResult.resultMessage);
				$("#P_NOW_PWD").focus();
				$("#P_NOW_PWD").val("");
			}
		});
	};
	
	pageObj.userIdFind = function(){
		FwkCmmnUtil.submitForm(defaultFrm , "/comm/popup/joinUserIdCheckView.do");
	};
	
	
	// 아이디 형식 체크  
	 unformatId = function( Char ) {
		var	reg	= /[^0-9A-Za-z-_]/g;
		Char.value = Char.value.replace( reg, "" );
	};
	
	fnSetData = function(userId){
		$("#P_LOGIN_ID",opener.document).val(userId);
		window.close(); 
	};
	 
	pageObj.setEventHandler = function() {
		
		$("#searchBtn").on("click",function(){
			pageObj.userIdFind();
		});
		
		// 저장 버튼
		/*$("#saveBtn").on("click", function() {
			
			if($("#P_NOW_PWD").val() == null || $("#P_NOW_PWD").val() == ""){
				alert("비밀번호를 입력하세요.");
				$("#P_NOW_PWD").focus();
				return false;
			}
			
			if($("#P_CHG_PWD").val() != $("#P_CHG_PWD_CFRM").val()){
				alert("새 비밀번호와 비밀번호 확인이 일치하지 않습니다.");
				$("#P_CHG_PWD_CFRM").focus();
				return false;
			}
			
			if(!confirm("비밀번호를 변경하시겠습니까?")){
				return false; 
			}
			
			pageObj.userPwdChange();
		});*/
		
		
		
	};

	/**
	 * window load
	 *
	 */
	$(function() {		
		pageObj.setEventHandler();
	});
	
})();