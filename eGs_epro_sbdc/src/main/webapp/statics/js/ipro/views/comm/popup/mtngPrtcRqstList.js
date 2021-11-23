/**
 * 공통 > 회의참가요청(팝업)
 *
 * <pre>
 * comm 
 *  |_popup
 *   |_ mtngPrtcRqstList.js
 * 
 * </pre>
 * @date : 2016. 10. 27.
 * @version : 1.0
 * @author : 은우소프트 손연우
 */
(function() {

	/**
	 * Default Constructor
	 */
	pageObj = {};

	/**
	 * Default Form Name
	 */
	var defaultFrm = "searchFrm";
	
	// 참가요청
	pageObj.vidoRqst = function(obj) {
		
		$("#meetMailFrm input[name='P_MAIL_USER_ID']").val($(obj).parent().parent().find("input[name='P_MAIL_USER_ID']").val());
		$("#meetMailFrm input[name='P_ROLE']").val($(obj).parent().parent().find("input[name='P_ROLE']").val());
		$("#meetMailFrm input[name='P_EMAL']").val($(obj).parent().parent().find("input[name='P_EMAL']").val());
		
		
		var actionUrl = "/comm/popup/meetSendMail";
		var jsonData = $("#meetMailFrm").serializeObject();
		
		FwkCmmnUtil.submitAjaxNoLoading(actionUrl, jsonData, function(res) {
			
			if(res.successAt == "Y") {
				alert("참가요청메일 전송에  성공하였습니다.");
			}else if(res.successAt =="N") {
				alert("참가요청메일 전송에  실패하였습니다.");
			}
			
		});
	};
	
	/**
	 * 페이지내에서 필요한 항목의 이벤트를 바인딩한다.
	 * 1. 닫기버튼(#closeBtn) 의 click 이벤트를 binding 한다.
	 *   - 닫기버튼에 click 이벤트 발생시 팝업을 닫는다.
	 */	
	pageObj.setEventHandler = function() {

		// 닫기버튼
		$("#colseBtn").on("click", function() {
			self.close();
			return false;
		});
		
		$("button[name='vidoRqst']").on("click", function() {
			//alert("메일로 화상회의정보를 보냈습니다.");
			pageObj.vidoRqst(this);
			return false;
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