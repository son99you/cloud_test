/**
 * 메인 > 사전규격공개 상세 (팝업)
 *
 * <pre>
 * main
 *    |_ bereNotiPopup.js
 * 
 * </pre>
 * @date : 2017.06.14
 * @version : 1.0
 * @author : 은우소프트 이주연
 */

(function() {

	/**
	 * Default Constructor
	 */
	pageObj = {};
	
	/**
	 * 페이지내에서 필요한 항목의 이벤트를 바인딩한다.
	 */	
	pageObj.setEventHandler = function() {
		
		// 닫기 버튼
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
		
		fileView($("#P_FILE_GRP_NO").val());
	});
})();