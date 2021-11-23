/**
 * Admin > 시스템현황 > 인터페이스 목록
 *
 * <pre>
 * sytm
 *    |_ intfMnge.js
 * 
 * </pre>
 * @date : 2016. 09. 28
 * @version : 1.0
 * @author :  yhs
 */
(function() {

	/**
	 * Default Constructor
	 */
	pageObj = {};
	
	/**
	 * Default Form Name
	 */
	var defaultFrm = "menuMoveFrm";
	
	
	// 목록
	pageObj.intfMnge = function() {
		FwkCmmnUtil.submitForm(defaultFrm, "/sytm/intfMnge.do");
	};


	pageObj.clickPage = function(pageNo) {
		$("#" + defaultFrm + " #P_PAGE_NO").val(pageNo);
		pageObj.intfMnge();
	};
	
	pageObj.setEventHandler = function() {
		
		// 조회버튼
		$("#searchBtn").on("click", function() {
			$("#P_PAGE_NO").val("1");
			$("#P_PAGE_SIZE").val("10");
			pageObj.intfMnge();			
		});
		
		// 1. Form 안에서 필요한 Fields 에 엔터키 이벤트 핸들러를 등록한다.
		FwkCmmnUtil.setEnterKeyBinding(defaultFrm, ["P_IF_NM_S"], function() {
			// EnteKey 이벤트 발생시 현재페이지 다시 조회를 한다.
			pageObj.clickPage(1);
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