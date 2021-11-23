(function() {

	/**
	 * Default Constructor
	 */
	pageObj = {};
	
	clickLeftMenuMove = function(url, title ,resourceName) {
		$("input[name='resourceName']").val(resourceName);
		FwkCmmnUtil.submitForm("menuLeftMoveFrm", url);
	};
	
	logout = function(){
//		window.close();
		if(!confirm("로그아웃 하시겠습니까?")){
			return false;
		}
		
		var actionUrl = "/opro/main/logout.do";
		var jsonParam = $("#logOutFrm").serializeObject();
		
		FwkCmmnUtil.submitAjax (actionUrl, jsonParam
			, function(res) {
				FwkCmmnUtil.submitForm("logOutFrm","/opro/main/emgncLoginForm.do");
			}
		);
//		FwkCmmnUtil.submitForm("logOutFrm","/main/emgncEmplyrLogout.do");
	};
	
})();