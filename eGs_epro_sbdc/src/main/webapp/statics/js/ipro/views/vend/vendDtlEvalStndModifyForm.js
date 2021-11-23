(function() {

	/**
	 * Default Constructor
	 */
	pageObj = {};
	
	movePage = function(url) {
		FwkCmmnUtil.submitForm("detailFrm", url);  
	};
	deleteFn = function(obj){
		$(obj).parent().parent().remove();
	};
	
	pageObj.setEventHandler = function() {
		/**
		 * 1. 수정버튼 클릭 이벤트
		 */
		$("#modifyBtn").on("click", function() {
			movePage("/vend/vendDtlEvalStndModify.do");
			return false;
		});
		$("#listBtn").on("click", function() {
			movePage("/vend/vendDtlEvalStndList.do");
			return false;
		});
		$("#saveBtn").on("click", function() {
			$("#copyTrget").remove();
			
			var jsonData = $("#modifyFrm").serializeObject();
			var actionUrl = "/vend/vendDtlEvalStndModify";
		
			FwkCmmnUtil.submitAjax (actionUrl, jsonData
			, function(res) {	
				alert("저장되었습니다.");
				FwkCmmnUtil.submitForm("detailFrm", "/vend/vendDtlEvalStndDetail.do");
			});
			return false;
		});
		$("#addBtn").on("click", function() {
			var tr = $("#copyTrget").clone();
			tr.css("display","");
			$("#copyArea").append(tr);
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