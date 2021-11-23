(function() {

	/**
	 * Default Constructor
	 */
	pageObj = {};
	var defaultFrm = "searchFrm";

	pageObj.vendEvalList = function() {
		FwkCmmnUtil.submitForm(defaultFrm, "/vend/vendEvalList.do");

	};
	pageObj.clickPage = function(pageNo) {
		$("#" + defaultFrm + " #P_PAGE_NO").val(pageNo);
		pageObj.vendEvalList();
	};

	fnView = function(dyyyy, evSeq) {
		$("#detailFrm input[name='ev_seq']").val(evSeq);
		$("#detailFrm input[name='dyyyy']").val(dyyyy);
		FwkCmmnUtil.submitForm("detailFrm", "/vend/vendEvalDetail.do");
	};
	fnDetailView = function(dyyyy, evSeq) {
		$("#detailFrm input[name='ev_seq']").val(evSeq);
		$("#detailFrm input[name='dyyyy']").val(dyyyy);
		FwkCmmnUtil.submitForm("detailFrm", "/vend/vendEvalView.do");
	};

	movePage = function(url) {

		FwkCmmnUtil.submitForm("detailFrm", url);
	};
	pageObj.setEventHandler = function() {
		/**
		 * 1. 신규등록버튼 클릭 이벤트.
		 */
		$("#regBtn").on("click", function() {
			FwkCmmnUtil.submitForm("registFrm", "/vend/vendEvalRegForm.do");
			return false;
		});
		// 조회버튼
		$("#searchBtn").on("click", function() {
			$("#P_PAGE_NO").val("1");
			$("#P_PAGE_SIZE").val("10");
			removeComma();
			pageObj.vendEvalList();
		});
		FwkCmmnUtil.setEnterKeyBinding(defaultFrm,
				[ "dyyyy_sch", "ev_name_sch" ], function() {
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