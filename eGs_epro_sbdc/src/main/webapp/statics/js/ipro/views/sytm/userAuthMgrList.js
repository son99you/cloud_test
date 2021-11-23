/**
 * 시스템 관리 > 사용자별 권한관리 
 *  
 *   <pre>
 * sytm
 *    |_ userAuthMgrDetail.js
 * 
 * </pre>
 * @date : 2017.06.15.
 * @version : 1.0
 * @author : 은우소프트 이주연
 */

(function() {
	
	/**
	 * Default Constructor
	 */
	pageObj = {};

	movePage = function(url) {
		FwkCmmnUtil.submitForm("detailFrm", url);  
	};
	
	
	/**
	 * 페이지내에서 필요한 항목의 이벤트를 바인딩한다.
	 * 1. 목록버튼(#listBtn) 의 click 이벤트를 binding 한다.
	 */	
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


