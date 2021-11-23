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
		 * 1. 목록버튼 클릭 이벤트
		 */
		$("#listBtn").on("click", function() {
			movePage("/vend/vendEvalDeptStndList.do");
			return false;
		});
		$("#saveBtn").on("click", function() {
			$("#copyTrget").remove();
			
			var jsonData = $("#registFrm").serializeObject();
			var actionUrl = "/vend/vendEvalDeptStndRegist";
		
			FwkCmmnUtil.submitAjax (actionUrl, jsonData
			, function(res) {	
				alert("저장되었습니다.");
				$("#detailFrm input[name='ev_code1']").val(res.ev_code1);
				FwkCmmnUtil.submitForm("detailFrm", "/vend/vendEvalDeptStndDetail.do");
			});
			return false;
		});
		$("#addbtn").on("click", function() {
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