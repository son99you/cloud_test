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
		$("#listBtn").on("click", function() {
			movePage("/vend/vendEvalDeptStndList.do");
			return false;
		});
		$("#saveBtn").on("click", function() {
			$("#copyTrget").remove();
			
			var jsonData = $("#modifyFrm").serializeObject();
			var actionUrl = "/vend/vendEvalDeptStndModify";
		
			FwkCmmnUtil.submitAjax (actionUrl, jsonData
			, function(res) {	
				alert("저장되었습니다.");
				FwkCmmnUtil.submitForm("detailFrm", "/vend/vendEvalDeptStndDetail.do");
			});
			return false;
		});
		
		$("#addBtn").on("click", function() {
			var tr = $("#copyTrget").clone();
			tr.css("display","");
			$("#evalFrame").append(tr);
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