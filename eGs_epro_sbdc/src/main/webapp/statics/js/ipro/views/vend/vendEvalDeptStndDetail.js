(function() {

	/**
	 * Default Constructor
	 */
	pageObj = {};
	
	movePage = function(url) {
		FwkCmmnUtil.submitForm("listFrm", url);  
	};
	pageObj.setEventHandler = function() {
		/**
		 * 1. 수정버튼 클릭 이벤트
		 */
		$("#modifyBtn").on("click", function() {
			if(confirm("수정하시겠습니까?")){
				FwkCmmnUtil.submitForm("modifyFrm", "/vend/vendEvalDeptStndModifyForm.do");  
				return false;
			}
		});
		$("#listBtn").on("click", function() {
			movePage("/vend/vendEvalDeptStndList.do");
			return false;
		});
		$("#deleteBtn").on("click", function() {
			if(confirm("삭제하시겠습니까?")){
				var jsonData = $("#deleteFrm").serializeObject();
				var actionUrl = "/vend/vendEvalDeptStndDelete";
			
				FwkCmmnUtil.submitAjax (actionUrl, jsonData
				, function(res) {	
					alert("삭제되었습니다.");
					FwkCmmnUtil.submitForm("listFrm", "/vend/vendEvalDeptStndList.do");
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