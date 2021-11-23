/**
 * 공통 > 용어사전(팝업)
 *
 * <pre>
 * comm
 *    |_ popup
 *           |_ trmPopup.js
 * 
 * </pre>
 * @date : 2018. 12. 31
 * @version : 1.0
 * @author : 은우소프트 전상훈
 */

(function() {

	/**
	 * Default Constructor
	 */
	pageObj = {};

	/**
	 * Default Form Name
	 */
	var defaultFrm = "";

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