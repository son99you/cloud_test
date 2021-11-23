(function() {
	
	/**
	 * Default Constructor
	 */
	pageObj = {};

	movePage = function(url) {
		FwkCmmnUtil.submitForm("detailFrm", url);  
	};
	
	
	/**
	 * 페이지내에서 필요한 항목의 이벤트를 바인딩한다.
	 * 1. 목록버튼(#listBtn) 의 click 이벤트를 binding 한다.
	 */	
	pageObj.setEventHandler = function() {
	};

	/**
	 * window load
	 *
	 */
	$(function() {		
		pageObj.setEventHandler();
	});
})();


