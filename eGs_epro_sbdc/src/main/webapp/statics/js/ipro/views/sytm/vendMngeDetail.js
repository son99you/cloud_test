(function() {

	/**
	 * Default Constructor
	 */
	pageObj = {};
	
	/**
	 * Default Form Name
	 */
	var defaultFrm = "registFrm";

	
	pageObj.vendList = function(){
		FwkCmmnUtil.submitForm("listFrm", "/sytm/vendMngeList.do");
	};
	
	pageObj.setEventHandler = function() {

		// 목록
		$("#listBtn").on("click", function() {
			pageObj.vendList();
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