(function() {

	/**
	 * Default Constructor
	 */
	pageObj = {};
	
	var defaultFrm = "detailFrm";

	pageObj.infoAppList = function() {
		FwkCmmnUtil.submitForm("listFrm", "/info/infoAppList.do");
	};

	
	pageObj.setEventHandler = function() {
		
		$("#listBtn").on("click", function() {
			pageObj.infoAppList();
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