/**
 * 전자입찰 > 유찰 등록
 *
 * <pre>
 *    |_ estiMngeRegstrRegistForm.js
 * 
 * </pre>
 * @date : 2018. 03. 13.
 * @version : 1.0
 * @author : 은우소프트 홍찬일
 */

(function() {

	/**
	 * Default Constructor
	 */
	pageObj = {};

	/**
	 * Default Form Name
	 */

	
	/**
	 * Default Form Name
	 */
	var defaultFrm = "registFrm";

	
	pageObj.entrpsSubmit = function() {
		var jsonData = $("#registFrm").serializeObject();
		var actionUrl = "/prpo/estiMngeRegstrRegist"; 
		FwkCmmnUtil.submitAjax (actionUrl, jsonData
				, function() {
			alert("업체선정을 완료하였습니다.");
			window.opener.fnRetrun();
			window.open('', '_self').close();
		});
	};
	

	/**
	 * 페이지내에서 필요한 항목의 이벤트를 바인딩한다.
	 * 1. 조회버튼(#searchBtn) 의 click 이벤트를 binding 한다.
	 *   - 조회버튼에 click 이벤트 발생시 입찰참가업체목록조회 함수를 호출한다.
	 */	
	pageObj.setEventHandler = function() {

		$("#registBtn").on("click", function() {
			
			if(!confirm("업체선정 하시겠습니까? ")){
				return false;
			}
			
			pageObj.entrpsSubmit();
		});

		$("#closeBtn").on("click", function() {
			window.close();
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