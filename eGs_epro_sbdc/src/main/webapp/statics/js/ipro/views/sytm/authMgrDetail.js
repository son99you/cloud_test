(function() {

	/**
	 * Default Constructor
	 */
	pageObj = {};
	
	
	pageObj.authMgrList = function() {
		FwkCmmnUtil.submitForm("listFrm", "/sytm/authMgrList.do");
	};
	
	pageObj.estmCmtmSpheMpgUpdtForm = function() {
		FwkCmmnUtil.submitForm("updtFrm", "/sytm/authMgrUpdtForm.do");
	};
	
	pageObj.setEventHandler = function() {
		
		//  목록
		$("#listBtn").on("click", function() {
			pageObj.authMgrList();
		});

		
		// 수정
		$("#updtBtn").on("click", function() {
			pageObj.estmCmtmSpheMpgUpdtForm();
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