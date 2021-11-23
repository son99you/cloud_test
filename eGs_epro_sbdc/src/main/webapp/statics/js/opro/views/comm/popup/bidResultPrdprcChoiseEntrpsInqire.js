/**
 * 전자입찰 > 예가선택업체 조회
 *
 * <pre>
 * elbi
 *    |_ oepElbiPopupBidResultPrdprcChoiseEntrpsInqire.js
 * 
 * </pre>
 * @date : 2015. 03. 23. 오후 1:58:09
 * @version : 1.0
 * @author : 은우소프트 손연우
 */

(function() {

	/**
	 * Default Constructor
	 */
	pageObj = {};

	/**
	 * Default Form Name
	 */

	
	/**
	 * Default Form Name
	 */
	var defaultFrm = "searchFrm";

	
	
	/**
	 * 페이지내에서 필요한 항목의 이벤트를 바인딩한다.
	 * 1. 닫기버튼(#closeBtn) 의 click 이벤트를 binding 한다.
	 *   - 닫기버튼에 click 이벤트 발생시 POPUP창을 닫는다.
	 */	
	pageObj.setEventHandler = function() {

		$("#closeBtn").on("click", function() {
			window.close();
			 
		});


	};

	/**
	 * window load
	 *
	 */
	$(function(){
		pageObj.setEventHandler();
	});
})();