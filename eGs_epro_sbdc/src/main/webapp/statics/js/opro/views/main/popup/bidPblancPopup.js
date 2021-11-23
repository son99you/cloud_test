/**
 * 메인 > 입찰공고 상세 팝업 
 *
 * <pre>
 * main
 *    |_ bidPblancPopup.js
 * 
 * </pre>
 * @date : 2019.04.12
 * @version : 1.0
 * @author : 은우소프트 은잔디
 */

(function() {

	/**
	 * Default Constructor
	 */
	pageObj = {};

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
	$(function(){
		pageObj.setEventHandler();
		fileView($("#P_FILE_GRP_NO").val());
	});
})();