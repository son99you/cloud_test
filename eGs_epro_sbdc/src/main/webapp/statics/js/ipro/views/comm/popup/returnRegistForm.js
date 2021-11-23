/**
 * 전자입찰 > 반려 등록
 *
 * <pre>
 * elbi
 *    |_ prcmPopupReturnRegistForm.js
 * 
 * </pre>
 * @date : 2015. 04. 07. 오전 11:39:21
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

	
	/**
	 * Default Form Name
	 */
	var defaultFrm = "registFrm";

	
	pageObj.returnRegist = function() {
		var jsonData = $("#registFrm").serializeObject();
		var actionUrl = "/comm/popup/returnRegist"; 
		FwkCmmnUtil.submitAjax (actionUrl, jsonData
				, function() {
			window.opener.returnList();
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
			
			if(!confirm("반려하시겠습니까?")){
				return false; 
			}
			pageObj.returnRegist();
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