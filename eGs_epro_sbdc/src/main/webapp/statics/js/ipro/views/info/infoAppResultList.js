(function() {

	/**
	 * Default Constructor
	 */
	pageObj = {};
	
	movePage = function(url) {
		FwkCmmnUtil.submitForm("detailFrm", url);  
	};
	
	infoApprlineDetail = function(aprdc_no, aprdc_se) {
		$('#P_APRDC_NO_S').val(aprdc_no);
		$('#P_APRDC_SE_S').val(aprdc_se);
		loading();
		FwkCmmnUtil.submitForm("detailFrm", "/info/infoAppResultDetail.do");
	};
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