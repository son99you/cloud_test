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
			if(confirm("수정하시겠습니까?")){
				FwkCmmnUtil.submitForm("modifyFrm", "/vend/vendEvalStndModifyForm.do");  
				return false;
			}
		});
		$("#listBtn").on("click", function() {
			FwkCmmnUtil.submitForm("listFrm", "/vend/vendEvalStndList.do");  
			return false;
		});
		$("#deleteBtn").on("click", function() {
			if(confirm("삭제하시겠습니까?")){
				var jsonData = $("#deleteFrm").serializeObject();
				var actionUrl = "/vend/vendEvalStndDelete";
			
				FwkCmmnUtil.submitAjax (actionUrl, jsonData
				, function(res) {	
					alert("삭제되었습니다.");
					FwkCmmnUtil.submitForm("listFrm", "/vend/vendEvalStndList.do");
				});
				return false;
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