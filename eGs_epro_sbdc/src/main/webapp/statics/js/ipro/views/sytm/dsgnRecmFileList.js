(function() {

	/**
	 * Default Constructor
	 */
	pageObj = {};

	var defaultFrm = "searchFrm";

	
	detailInqire = function(cdId){ 
		$("#detailFrm input[name='P_CD_DTL_ID']").val(cdId); 
		FwkCmmnUtil.submitForm("detailFrm", "/sytm/dsgnRecmFileDetail.do");
	};
	
	pageObj.dsgnRecmFileRegForm = function() {
		FwkCmmnUtil.submitForm("registFrm", "/sytm/dsgnRecmFileRegForm.do");
	};
	
	pageObj.dsgnRecmFileList = function() { 
		FwkCmmnUtil.submitForm(defaultFrm , "/sytm/dsgnRecmFileList.do"); 
	};
	 
	pageObj.clickPage = function(pageNo) { 
		$("#" + defaultFrm + " #P_PAGE_NO").val(pageNo);
		pageObj.dsgnRecmFileList(); 
	};
	
	
	pageObj.setEventHandler = function() {
		
		$("#searchBtn").on("click", function() {
			pageObj.dsgnRecmFileList();
		});
		
		$("#registBtn").on("click", function() {
			pageObj.dsgnRecmFileRegForm(); 
		});
		
		
		// 1. Form 안에서 필요한 Fields 에 엔터키 이벤트 핸들러를 등록한다.
		FwkCmmnUtil.setEnterKeyBinding(defaultFrm, ["P_CD_DTL_ID_S", "P_CD_DTL_NM_S"], function() {
			// EnteKey 이벤트 발생시 현재페이지 다시 조회를 한다.
			pageObj.clickPage(1);
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