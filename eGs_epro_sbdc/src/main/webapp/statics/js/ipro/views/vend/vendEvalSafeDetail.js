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
		 * 1. 목록버튼 클릭 이벤트
		 */
		$("#listBtn").on("click", function() {
			movePage("/vend/vendEvalSafeList.do");
			return false;
		});
		/**
		 * 1. 삭제버튼 클릭 이벤트
		 */
		$("#deleteBtn").on("click", function() {
			if(confirm("삭제하시겠습니까?")){
				movePage("/vend/vendEvalSafeList.do");
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