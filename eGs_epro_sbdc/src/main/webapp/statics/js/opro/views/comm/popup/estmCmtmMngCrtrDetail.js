/**
 * 평가관리 > 평가위원관리 > 평가위원POOL현황 상세
 *
 * <pre>
 * estm
 *    |_ cmtmMngPoolDetail.js
 * 
 * </pre>
 * @date : 2021. 03. 23.
 * @version : 1.0
 * @author : 은우소프트
 */

(function() {
	
	/**
	 * Default Constructor
	 */
	pageObj = {};

	var defaultFrm = "detailFrm";

	/**
	 * 페이지내에서 필요한 항목의 이벤트를 바인딩한다.
	 * 1. 목록버튼(#listBtn) 의 click 이벤트를 binding 한다.
	 */	
	pageObj.setEventHandler = function() {
		
		// 닫기버튼
		$("#closeBtn").on("click", function() {
			self.close();
			return false;
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