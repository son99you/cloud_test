(function() {

	/**
	 * Default Constructor
	 */
	pageObj = {};
	
	movePage = function(url) {
		FwkCmmnUtil.submitForm("listFrm", url);  
	};
	deleteFn = function(obj){
		$(obj).parent().parent().remove();
	};
	
	pageObj.setEventHandler = function() {
		/**
		 * 1. 목록버튼 클릭 이벤트
		 */
		$("#listBtn").on("click", function() {
			movePage("/vend/vendEvalStndList.do");
			return false;
		});
		
		$("#saveBtn").on("click", function() {
			
			$("#copyTrget").remove();
			
			var jsonData = $("#registFrm").serializeObject();
			var actionUrl = "/vend/vendEvalStndReg";
		
			FwkCmmnUtil.submitAjax (actionUrl, jsonData
			, function(res) {	
				alert("저장되었습니다.");
				$("#detailFrm input[name='ev_code1']").val(res.ev_code1);
				FwkCmmnUtil.submitForm("detailFrm", "/vend/vendEvalStndDetail.do");
			});
			return false;
		});
		
		$("#regBtn").on("click", function() {
			// 거꾸로 추가 완성
			var trBefore = $("#copyArea").children().clone();
			var tr = $("#copyTrget").clone();
			tr.css("display","");
			//tr = tr + trBefore;
			$("#copyArea").children().remove();
			$("#copyArea").append(tr);
			$("#copyArea").append(trBefore);
			//$("#copyArea").append(trBefore);
			
			
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