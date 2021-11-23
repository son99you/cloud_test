(function() {

	/**
	 * Default Constructor
	 */
	pageObj = {};
	
	var defaultFrm = "registFrm";
	movePage = function(url) {
		FwkCmmnUtil.submitForm("detailFrm", url);  
	};
	
	
		

	pageObj.infoApprSetRegist = function() {
		
		if(!confirm("저장 하시겠습니까?")){
			return false;
		}
		
		var jsonData = $("#registFrm").serializeObject();
		
		var actionUrl = "/info/infoApprSetRegist.do";
		FwkCmmnUtil.submitAjax (actionUrl, jsonData
				, function() {	
				FwkCmmnUtil.submitForm("viewFrm", "/info/infoApprSet.do"); 
		});
	};
	
	pageObj.setEventHandler = function() {
		
		$("#regBtn").on("click", function() {
			pageObj.infoApprSetRegist();
			return false;
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