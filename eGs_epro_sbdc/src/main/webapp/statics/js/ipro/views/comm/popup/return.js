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

	/**
	 * Default Form Name
	 */
	var registFrm = "registFrm";
	/**  
     * <pre>
     * 1. 개요 : 수정요청등록
     * 2. 처리내용 :
     *  	
     * </pre>
     * @Function Name : returnRegist
     * @date : 2016. 10. 
     * @author : 은우소프트 
     * @history : 
     *  =======================================================
     *  변경일             		작성자                     		변경내용  
     *  =======================================================
     *  2016. 10.        	은우소프트	              최초 작성 
     *  =======================================================   
     */
	pageObj.returnRegist = function() {
		
		window.opener.returnListSubmit();
		self.close();
		
	};
	
	
	pageObj.setEventHandler = function() {
		
		// 닫기버튼
		$("#closeBtn").on("click", function() {
			window.close();
		});
		
		// 저장버튼
		$("#saveBtn").on("click", function() {
			if(!confirm(FwkMssageUtil.getMessage("COM.CON.001", "반려"))){
				return false;
			}
			pageObj.returnRegist();
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