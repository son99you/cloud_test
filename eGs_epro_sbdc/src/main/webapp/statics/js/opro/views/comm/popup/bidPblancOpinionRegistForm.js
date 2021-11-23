/**
 * 전자입찰 > 입찰공고 의견등록
 *
 * <pre>
 * elbi
 *    |_popup
 *        |_oepElbiPopupBidPblancOpinionRegistForm.js
 * 
 * </pre>
 * @date : 2015. 02. 12. 오후 05:29:15
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
     * 1. 개요 : 의견저장
     * 2. 처리내용 :
     * 		의견저장 submit 한다. 
     *  	
     * </pre>
     * @Function Name : bidPblancOpinionUpdt
     * @date : 2017. 06. 23
     * @author : 은우소프트 이주연
     * @history : 
     *  =======================================================
     *  변경일             		작성자                     		변경내용  
     *  =======================================================
     *  2017. 06. 23       은우소프트 이주연              최초 작성 
     *  =======================================================   
     */
	pageObj.bidPblancOpinionUpdt = function() {
		alert("저장되었습니다."); 
		window.close();
	};
	
	/**
	 * 페이지내에서 필요한 항목의 이벤트를 바인딩한다.
	 * 1. 조회버튼(#searchBtn) 의 click 이벤트를 binding 한다.
	 *   - 조회버튼에 click 이벤트 발생시 입찰공고목록조회 함수를 호출한다.
	 * 2. 입찰종류(#select2) 콤보박스의 change 이벤트를 biding한다.
	 *   2.1 change 이벤트 발생시 changeSelect2 함수를 호출한다.
	 * 3. Form 안에서 필요한 필드에 대해 엔터키 이벤트를 바인딩한다.
	 *   - 엔터키 이벤트 발생시 pageObj.clickPage 함수를 호출한다.
	 */	
	pageObj.setEventHandler = function() {
		
		// 닫기버튼
		$("#closeBtn").on("click", function() {
			window.close();
		});
		
		// 의견저장 버튼
		$("#opinionRegistBtn").on("click", function() {
			pageObj.bidPblancOpinionUpdt();
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