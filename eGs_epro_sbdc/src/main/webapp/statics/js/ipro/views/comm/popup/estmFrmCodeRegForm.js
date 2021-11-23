(function() {

	/**
	 * Default Constructor
	 */
	pageObj = {};
	
	
	pageObj.setEventHandler = function() {
		
		/**
		 * 1. 목록버튼 클릭 이벤트
		 */
		
		// 닫기버튼
		$("#closeBtn").on("click", function() {
			window.opener.returnEstmSeMngListOnload();
			self.close();
			return false;
		});
		
		$("#saveBtn").on("click", function() {
			
			var actionUrl = "/comm/popup/estmFrmCodeRegist";
			var jsonData = $("#regFrm").serializeObject();
			
			FwkCmmnUtil.submitAjaxNoLoading(actionUrl, jsonData, function(res) {
				if(res.returnMsg == "S") {
					alert("평가구분코드를 저장하였습니다.");
					window.opener.returnEstmSeMngListOnload();
					self.close();
					return false;
				}else if(res.returnMsg =="F") {
					alert("평가구분코드 저장에  실패하였습니다.");
					return false;
				}
				
			});
			
			
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