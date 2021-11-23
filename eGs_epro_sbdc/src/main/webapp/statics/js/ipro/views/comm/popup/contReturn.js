/**
 * 계약관리 > 반려 등록
 *
 * <pre>
 * prpo
 *    |_ contReturn.js
 * 
 * </pre>
 */

(function() {

	/**
	 * Default Constructor
	 */
	pageObj = {};

	/**
	 * Default Form Name
	 */
	var defaultFrm = "";
	
	pageObj.returnRegist = function() {
		
		var jsonData = $("#registFrm").serializeObject();
		var actionUrl = "/cont/contPscdUpdt";
		
		FwkCmmnUtil.submitAjax (actionUrl, jsonData
				, function(res) {
			
			if(res.stateUpdt == "succ"){
				window.opener.detail(res.P_CONT_PSCD);
			}else{
				
			}
			window.close();
		});
		
	};

	/**
	 * 페이지내에서 필요한 항목의 이벤트를 바인딩한다.
	 * 1. 조회버튼(#searchBtn) 의 click 이벤트를 binding 한다.
	 *   - 조회버튼에 click 이벤트 발생시 입찰참가업체목록조회 함수를 호출한다.
	 */	
	pageObj.setEventHandler = function() {

		$("#registBtn").on("click", function() {
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