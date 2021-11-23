/**
 * 공통 > 진행상태 조회 팝업
 *
 * <pre>
 * elbi
 *    |_ contProgPopup.js
 * 
 * </pre>
 * @date : 2018. 12. 24. 
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
	var defaultFrm = "searchFrm";

	pageObj.progListInqire = function() {
		FwkCmmnUtil.submitForm(defaultFrm, "/comm/popup/bidReqProgPopup.do");
	};

	
	/**  
     * <pre>
     * 1. 개요 : 페이지번호 클릭
     * 2. 처리내용 : 
     * 		- 선택된 페이지번호를 set 한다.
     *  	- 입찰설명회 목록조회를 호출한다.
     * </pre>
     * @Function Name : clickPage
     * @date : 2018. 03. 15
     * @author : 은우소프트 맹경열
     * @history : 
     *  =======================================================
     *  변경일             		작성자                     		변경내용  
     *  =======================================================
     *  2018. 03. 15.       은우소프트 맹경열              최초 작성 
     *  =======================================================   
     */
	pageObj.clickPage = function(pageNo) {
		$("#" + defaultFrm + " #P_PAGE_NO").val(pageNo);
		pageObj.progListInqire();
	};
	
	/**
	 * window load
	 *
	 */
	$(function(){
	});
})();