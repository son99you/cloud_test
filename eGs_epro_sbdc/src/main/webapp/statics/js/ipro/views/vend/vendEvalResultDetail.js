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
			movePage("/vend/vendEvalResultList.do");
			return false;
		});
		/**
		 * 1. Excel버튼 클릭 이벤트
		 */
		$("#excelBtn").on("click", function() {
			if(confirm("엑셀 다운로드하시겠습니까?")){
				alert("다운로드를 완료하였습니다.");
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