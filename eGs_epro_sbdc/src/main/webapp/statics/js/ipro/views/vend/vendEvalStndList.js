(function() {

	/**
	 * Default Constructor
	 */
	pageObj = {};
	var defaultFrm = "searchFrm";
	movePage = function(url) {
		FwkCmmnUtil.submitForm("detailFrm", url);  
	};
	pageObj.vendEvalStndList = function(pageNo) {
		FwkCmmnUtil.submitForm("searchFrm", "/vend/vendEvalStndList.do");  
	};
	pageObj.clickPage = function(pageNo) {
		$("#" + defaultFrm + " #P_PAGE_NO").val(pageNo);
		pageObj.vendEvalStndList();
	};
	vendEvalStndDetail = function(evCode) {
		$("#detailFrm input[name='ev_code1']").val(evCode);
		FwkCmmnUtil.submitForm("detailFrm", "/vend/vendEvalStndDetail.do");  
	};
	pageObj.setEventHandler = function() {
		
		$("#regBtn4").on("click", function() {
			movePage("/vend/vendEvalStndRegForm4.do");
			return false;
		});
		
		
		$("#regBtn3").on("click", function() {
			movePage("/vend/vendEvalStndRegForm3.do");
			return false;
		});
		
		
		$("#regBtn2").on("click", function() {
			movePage("/vend/vendEvalStndRegForm2.do");
			return false;
		});
		
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
			pageObj.vendEvalStndList();
		});
		FwkCmmnUtil.setEnterKeyBinding(defaultFrm, ["ev_code1","ev_name1"], function() {
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