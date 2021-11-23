(function() {

	/**
	 * Default Constructor
	 */
	pageObj = {};
	
	movePage = function(url) {
		FwkCmmnUtil.submitForm("detailFrm", url);  
	};
	pageObj.setEventHandler = function() {
		/**
		 * 1. 수정버튼 클릭 이벤트
		 */
		$("#modifyBtn").on("click", function() {
			FwkCmmnUtil.submitForm("modifyFrm", "/vend/vendDtlEvalStndModifyForm.do");  
			return false;
		});
		$("#listBtn").on("click", function() {
			movePage("/vend/vendDtlEvalStndList.do");
			return false;
		});
		$("#deleteBtn").on("click", function() {
			var jsonData = $("#deleteFrm").serializeObject();
			var actionUrl = "/vend/vendDtlEvalStndDelete";
		
			FwkCmmnUtil.submitAjax (actionUrl, jsonData
			, function(res) {	
				alert("삭제되었습니다.");
				FwkCmmnUtil.submitForm("listFrm", "/vend/vendDtlEvalStndList.do");
			});
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