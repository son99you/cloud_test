(function() {

	/**
	 * Default Constructor
	 */
	pageObj = {};
	
	movePage = function(url) {
		FwkCmmnUtil.submitForm("listFrm", url);  
	};
	deleteFn = function(obj,gbn){
		$("#deleteList").val($("#deleteList").val()+gbn+"/");
		$(obj).parent().parent().remove();
	};
	
	pageObj.setEventHandler = function() {
		/**
		 * 1. 수정버튼 클릭 이벤트
		 */
		$("#listBtn").on("click", function() {
			movePage("/vend/vendEvalStndList.do");
			return false;
		});
		$("#saveBtn").on("click", function() {
			$("#copyTrget").remove();
			
			var jsonData = $("#modifyFrm").serializeObject();
			var actionUrl = "/vend/vendEvalStndModify";
		
			FwkCmmnUtil.submitAjax (actionUrl, jsonData
			, function(res) {	
				alert("저장되었습니다.");
				FwkCmmnUtil.submitForm("detailFrm", "/vend/vendEvalStndDetail.do");
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