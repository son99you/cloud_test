(function() {

	/**
	 * Default Constructor
	 */
	pageObj = {};
	
	var defaultFrm = "listFrm";
	
	movePage = function(url) {
		FwkCmmnUtil.submitForm("detailFrm", url);  
	};
	
	apprCmplDetail = function(appr_no) {
		$('#P_APPR_NO').val(appr_no);
		FwkCmmnUtil.submitForm("detailFrm", "/appr/apprCmplDetail.do");
	};
	
	pageObj.apprCmplList = function() {
		FwkCmmnUtil.submitForm(defaultFrm, "/appr/apprCmplList.do");
	};
	
	pageObj.clickPage = function(pageNo) {
		$("#" + defaultFrm + " #P_PAGE_NO").val(pageNo);
		pageObj.apprCmplList();
	};
	
	
	pageObj.setEventHandler = function() {
		
		// 조회버튼
		$("#searchBtn").on("click", function() {
			$("#P_PAGE_NO").val("1");
			$("#P_PAGE_SIZE").val("10");
			pageObj.apprCmplList();
		});
		
		// 1. Form 안에서 필요한 Fields 에 엔터키 이벤트 핸들러를 등록한다.
		FwkCmmnUtil.setEnterKeyBinding("searchFrm", ["P_APRDC_INTL_NM","select2"], function() {
			alert("enter");
			// EnteKey 이벤트 발생시 현재페이지 다시 조회를 한다.
			pageObj.clickPage(1);
		});
		
		
		$('input').on("keyup", function(evnt){
			if(evnt.keyCode == 13) {
				pageObj.apprCmplList();		
			}
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