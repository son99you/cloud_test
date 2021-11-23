/**
 * 시스템 관리 > 사용자별 권한관리 상세
 *  
 *   <pre>
 * sytm
 *    |_ userAuthMgrDetail.js
 * 
 * </pre>
 * @date : 2017.06.15.
 * @version : 1.0
 * @author : 은우소프트 이주연
 */


(function() {

	/**
	 * Default Constructor
	 */
	pageObj = {};

	/**  
     * <pre>
     * 1. 개요 : 목록조회 
     * 2. 처리내용 : 
     * 		일반 공지사항 목록 조회
     *  	
     * </pre>
     * @Function Name : listInqire
     * @date : 2017.06.13
     * @author : 은우소프트 이주연
     * @history : 
     *  =======================================================
     *  변경일             		작성자                     		변경내용  
     *  =======================================================
     *  2017.06.13      은우소프트 이주연              최초 작성 
     *  =======================================================   
     */
	pageObj.listInqire = function() {
		FwkCmmnUtil.submitForm("menuMoveFrm", "/sytm/userAuthMgrList.do");
	};
	
	/**  
     * <pre>
     * 1. 개요 : 수정 폼 조회 
     * 2. 처리내용 : 
     * 		일반 공지사항 수정 폼 조회 
     *  	
     * </pre>
     * @Function Name : updtFrmInqire
     * @date : 2017.06.13
     * @author : 은우소프트 이주연
     * @history : 
     *  =======================================================
     *  변경일             		작성자                     		변경내용  
     *  =======================================================
     *  2017.06.13       은우소프트 이주연           최초 작성 
     *  =======================================================   
     */
	pageObj.saveFrmInqire = function() {
		FwkCmmnUtil.submitForm("saveFrm", "/sytm/userAuthMgrList.do");
	};
	/**
	 * 페이지내에서 필요한 항목의 이벤트를 바인딩한다.
	 * 1. 목록버튼(#listBtn) 의 click 이벤트를 binding 한다.
	 */	
	pageObj.setEventHandler = function() {

		// 목록버튼
		$("#listBtn").on("click", function() {
			pageObj.listInqire();
		});
		
		// 등록버튼 
		$("#saveBtn").on("click", function() {
			pageObj.saveFrmInqire();
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