/**
 * 공통 > 반려
 *
 * <pre>
 * comm
 *    |_popup
 *        |_return.js
 * 
 * </pre>
 * @date : 2016. 10.
 * @version : 1.0
 * @author : 은우소프트 
 */

(function() {

	/**
	 * Default Constructor
	 */
	pageObj = {};
	
	pageObj.save = function() {
		alert("입찰포기 신청되었습니다.");
		window.close();
	};
	
	

	pageObj.setEventHandler = function() {
		// 저장버튼
		$("#saveBtn").on("click", function() {
			if(!confirm("저장하시겠습니까?")){
				return false;
			}
			pageObj.save();
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