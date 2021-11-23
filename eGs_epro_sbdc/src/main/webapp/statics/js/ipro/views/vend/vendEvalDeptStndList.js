(function() {

	/**
	 * Default Constructor
	 */
	pageObj = {};
	/**
	 * Default Form Name
	 */
	var defaultFrm = "searchFrm";
	
	movePage = function(url) {
		FwkCmmnUtil.submitForm("detailFrm", url);  
	};
	pageObj.vendEvalDeptStndList = function() {
		FwkCmmnUtil.submitForm(defaultFrm, "/vend/vendEvalDeptStndList.do");
		
	};
	pageObj.clickPage = function(pageNo) {
		$("#" + defaultFrm + " #P_PAGE_NO").val(pageNo);
		pageObj.vendEvalDeptStndList();
	};
	
	vendEvalDeptStndDetail = function(evCode) {
		$("#detailFrm input[name='ev_code1']").val(evCode);
		FwkCmmnUtil.submitForm("detailFrm", "/vend/vendEvalDeptStndDetail.do");  
	};
	pageObj.setEventHandler = function() {
		/**
		 * 1. 추가버튼 클릭 이벤트
		 */
		$("#regBtn").on("click", function() {
			movePage("/vend/vendEvalDeptStndRegForm.do");
			return false;
		});
		$("#searchBtn").on("click", function() {
			$("#P_PAGE_NO").val("1");
			$("#P_PAGE_SIZE").val("10");
			removeComma();
			pageObj.vendEvalDeptStndList();
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