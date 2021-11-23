(function() {

	/**
	 * Default Constructor
	 */
	pageObj = {};
	var defaultFrm = "searchFrm";
	movePage = function(url) {
		FwkCmmnUtil.submitForm("detailFrm", url);  
	};
	pageObj.vendDtlEvalStndList = function(pageNo) {
		FwkCmmnUtil.submitForm("searchFrm", "/vend/vendDtlEvalStndList.do");  
	};
	pageObj.clickPage = function(pageNo) {
		$("#" + defaultFrm + " #P_PAGE_NO").val(pageNo);
		pageObj.vendDtlEvalStndList();
	};
	vendDtlEvalStndDetail = function(evCode1,evCode2) {
		$("#detailFrm input[name='ev_code1']").val(evCode1);
		$("#detailFrm input[name='ev_code2']").val(evCode2);
		FwkCmmnUtil.submitForm("detailFrm", "/vend/vendDtlEvalStndDetail.do");  
	};
	pageObj.setEventHandler = function() {
		/**
		 * 1. 추가버튼 클릭 이벤트
		 */
		$("#regBtn").on("click", function() {
			movePage("/vend/vendEvalStndRegForm.do");
			return false;
		});
		$("#searchBtn").on("click", function() {
			$("#P_PAGE_NO").val("1");
			$("#P_PAGE_SIZE").val("10");
			removeComma();
			pageObj.vendDtlEvalStndList();
		});
		FwkCmmnUtil.setEnterKeyBinding(defaultFrm, ["ev_code1","ev_name1","ev_code2","ev_name2"], function() {
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