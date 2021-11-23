(function() {

	/**
	 * Default Constructor
	 */
	pageObj = {};
	
	movePage = function(url) {
		FwkCmmnUtil.submitForm("detailFrm", url);  
	};
	
	/**
	 * 1. 결재선관리 화면으로 해당 유저정보 전달.
	 * @param dept
	 * @param pos
	 * @param name
	 */
	regFn = function(dept,pos,name) {
		opener.pageObj.setUserFn(dept,pos,name);
		window.open("about:blank","_self").close();
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