/**
 * 공통 > 팝업
 *
 * <pre>
 * comm
 *    |_popup
 *        |_popup.js
 * 
 * </pre>
 * @date : 2017. 06.21
 * @version : 1.0
 * @author : 은우소프트 하성윤
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
     * <pre>
     * 1. 개요 : 부모창 이벤트
     * 2. 처리내용 :
     *  	
     * </pre>
     * @Function Name : returnRegist
     * @date : 2017. 06.21 
     * @author : 은우소프트 
     * @history : 
     *  ===================================================================
     *  변경일             		작성자                     		변경내용  
     *  ===================================================================
     *  2016. 06.21        	    하성윤	                        최초 작성 
     *  ===================================================================
     */
	returnFuntion = function() { 
		if($("#gbn").val() == "invoice2"){
			window.opener.returnFunction3();
			self.close();
		}else{
			window.opener.returnFunction();
			self.close();
		}
		
		
	};
	
	/**
	 * window load
	 */
	$(function(){
		pageObj.setEventHandler();
	});
})();