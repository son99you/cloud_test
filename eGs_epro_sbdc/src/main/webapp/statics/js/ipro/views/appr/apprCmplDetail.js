(function() {

	/**
	 * Default Constructor
	 */
	pageObj = {};
	
	var defaultFrm = "detailFrm";

	pageObj.apprCmplList = function() {
		FwkCmmnUtil.submitForm("listFrm", "/appr/apprCmplList.do");
	};

	
	pageObj.setEventHandler = function() {
		
		
		// 목록
		$("#listBtn").on("click", function() {
			pageObj.apprCmplList();
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