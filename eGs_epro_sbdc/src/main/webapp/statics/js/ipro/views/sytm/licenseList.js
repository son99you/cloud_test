(function() {

	/**
	 * Default Constructor
	 */
	pageObj = {};

	var defaultFrm = "searchFrm";

	
	detailInqire = function(P_LICENSE_NO){ 
		$("#detailFrm input[name='P_LICENSE_NO']").val(P_LICENSE_NO); 
		FwkCmmnUtil.submitForm("detailFrm", "/sytm/licenseDetail.do");
	};
	
	pageObj.licenseRegForm = function() {
		FwkCmmnUtil.submitForm("registFrm", "/sytm/licenseRegForm.do");
	};
	
	pageObj.licenseList = function() { 
		FwkCmmnUtil.submitForm(defaultFrm , "/sytm/licenseList.do"); 
	};
	 
	pageObj.clickPage = function(pageNo) { 
		$("#" + defaultFrm + " #P_PAGE_NO").val(pageNo);
		pageObj.licenseList(); 
	};
	
	
	pageObj.setEventHandler = function() {
		
		$("#searchBtn").on("click", function() {
			$("#P_PAGE_NO").val("1");
			$("#P_PAGE_SIZE").val("10");
			pageObj.licenseList();
		});
		
		
		$("#registBtn").on("click", function() {
			pageObj.licenseRegForm(); 
		});
		
	};

	/**
	 * window load
	 *
	 */
	$(function() {		
		pageObj.setEventHandler();
		// 엑셀다운로드 공통
		excelDownSetting("searchFrm");
	});
	
})();