/**
 * 기술평가 > 정성평가의견서 조회(팝업)
 *
 * <pre>
 * tcev
 *    |_ oepTcevPopupQualEvlWrtopnInqire.js
 * 
 * </pre>
 * @date : 2015. 11. 24. 오전 10:27:08
 * @version : 1.0
 * @author : 은우소프트 김봉수
 */

(function() {

	/**
	 * Default Constructor
	 */
	pageObj = {};

	/**
	 * 페이지내에서 필요한 항목의 이벤트를 바인딩한다.
	 * 1. 닫기버튼(#closeBtn) 의 click 이벤트를 binding 한다.
	 * 
	 */	
	pageObj.setEventHandler = function() {
		// 닫기버튼
		$("#closeBtn").on("click", function() {
			window.open('', '_self').close();
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