(function() {

	/**
	 * Default Constructor
	 */
	pageObj = {};
	
	zipSet = function(obj){
		var gbn = $("#gbn").val();
		$("#"+gbn+"1",opener.document).val($(obj).children().eq(0).html());
		$("#"+gbn+"2",opener.document).val($(obj).children().eq(1).html());
		window.close();
	};
	movePage = function(url) {
		FwkCmmnUtil.submitForm("detailFrm", url);  
	};
	pageObj.setEventHandler = function() {
		
	};

	/**
	 * window load
	 *
	 */
	$(function() {		
		pageObj.setEventHandler();
	});
	
})();