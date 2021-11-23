/**
 * 비밀번호변경(팝업)
 *
 * <pre>
 * user
 *    |_ vendPwdChngRegistForm.js
 * 
 * </pre>
 * @date : 2019. 03. 22.
 * @version : 1.0
 * @author : 은우소프트 은잔디
 */
(function() {

	/**
	 * Default Constructor
	 */
	pageObj = {};

	/**
	 * Default Form Name
	 */
	var defaultFrm = "registFrm";
	
	
	// 의견정보삭제
	pageObj.vendPwdChngRegist = function() {
		
		var jsonData = $("#" + defaultFrm).serializeObject();
		var actionUrl = "/opro/user/vendPwdChngRegist";
		
		FwkCmmnUtil.submitAjax (actionUrl, jsonData
				, function() {
			alert("비밀번호가 변경되었습니다.");
			window.opener.vendInfoDetail();
			window.open('', '_self').close();
		});
	};
	

	
	/**
	 * 페이지내에서 필요한 항목의 이벤트를 바인딩한다.
	 * 1. 조회버튼(#searchBtn) 의 click 이벤트를 binding 한다.
	 *   - 조회버튼에 click 이벤트 발생시 해당 함수를 호출한다.
	 * 2. 닫기버튼(#closeBtn) 의 click 이벤트를 binding 한다.
	 *   - 닫기버튼에 click 이벤트 발생시 팝업을 닫는다.
	 * 3. Form 안에서 필요한 필드에 대해 엔터키 이벤트를 바인딩한다.
	 *   - 엔터키 이벤트 발생시 pageObj.clickPage 함수를 호출한다.
	 */	
	pageObj.setEventHandler = function() {

		// 닫기 버튼
		$("#closeBtn").on("click", function() {
			window.close();
		});
		
		
		// 변경 버튼
		$("#chngBtn").on("click", function() {
			
			if($("#P_CHG_PWD").val() == "" || $("#P_CHG_PWD").val() == null){
				alert("[변경 비밀번호] 항목은 필수입력입니다.");
				$("#P_CHG_PWD").focus();
				return false;
			}
			
			if($("#P_CHG_PWD_CFRM").val() == "" || $("#P_CHG_PWD_CFRM").val() == null){
				alert("[변경 비밀번호 확인] 항목은 필수입력입니다.");
				$("#P_CHG_PWD_CFRM").focus();
				return false;
			}
			
			if($("#P_CHG_PWD").val() != $("#P_CHG_PWD_CFRM").val()){
				alert("[변경 비밀번호]와 [변경 비밀번호 확인] 이 일치하지 않습니다.");
				$("#P_CHG_PWD_CFRM").focus();
				return false;
			}
			
			if(!confirm(FwkMssageUtil.getMessage("COM.CON.002", "비밀번호", "변경"))){
				return false;
			}
			
			pageObj.vendPwdChngRegist();
		});
		
	};

	/**
	 * window load
	 *
	 */
	$(function(){
		pageObj.setEventHandler();
	});
})();