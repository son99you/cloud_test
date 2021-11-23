/**
 * 통합게시판 > 기타자료실 목록
 *
 * <pre>
 * unbb
 *    |_ oepUnbbEtcRecsroomList.js
 * 
 * </pre>
 * @date : 2015. 06. 01. 오후 02:07:08
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
	var defaultFrm = "searchFrm";
	
	/**
	 * 페이지 이동
	 * @param url
	 */
	movePage = function(url) {
		FwkCmmnUtil.submitForm("detailFrm", url);  
	};
 
	/**  
     * <pre>
     * 1. 개요 : 기타자료실 목록조회 Submit
     * 2. 처리내용 : 
     * 		기타자료실 목록 조회 Form 을 Sumit 한다.
     *  	
     * </pre>
     * @Function Name : listInqire
     * @date : 2015. 06. 01.
     * @author : 은우소프트 전상훈
     * @history : 
     *  =======================================================
     *  변경일             		작성자                     		변경내용  
     *  =======================================================
     *  2015. 06. 01.       은우소프트 전상훈              최초 작성 
     *  =======================================================   
     */
	pageObj.listInqire = function() {
		FwkCmmnUtil.submitForm(defaultFrm, "/opro/noti/etcRecsroomList.do");

	};
	
	/**   
	 * <pre>
	 * 1. 개요 : 기타자료실 상세조회 페이지 이동
	 * 2. 처리내용 : 
	 * 		기타자료실 상세 조회 페이지로 이동한다.
	 *  	
	 * </pre>
	 * @Function Name : detailInqire
	 * @date : 2015. 06. 01.
	 * @author : 은우소프트 전상훈
	 * @history : 
	 *  =======================================================
	 *  변경일             		작성자                     		변경내용  
	 *  =======================================================
	 *  2015. 006. 01.       은우소프트 전상훈              최초 작성 
	 *  =======================================================   
	 */
	detailInqire = function(nttSn) {
		$("#detailFrm input[name='P_NTT_SN']").val(nttSn);
		FwkCmmnUtil.submitForm("detailFrm", "/opro/noti/etcRecsroomDetail.do");
	};
	
	/**  
     * <pre>
     * 1. 개요 : 페이지번호 클릭
     * 2. 처리내용 : 
     * 		- 선택된 페이지번호를 set 한다.
     *  	- 일반 공지시항 목록조회를 호출한다.
     * </pre>
     * @Function Name : clickPage
     * @date : 2015. 06. 01.
     * @author : 은우소프트 전상훈
     * @history : 
     *  =======================================================
     *  변경일             		작성자                     		변경내용  
     *  =======================================================
     *  2015. 06. 01.       은우소프트 전상훈              최초 작성 
     *  =======================================================   
     */
	pageObj.clickPage = function(pageNo) {
		$("#" + defaultFrm + " #P_PAGE_NO").val(pageNo);
		pageObj.listInqire();
	};

	/**
	 * 페이지내에서 필요한 항목의 이벤트를 바인딩한다.
	 * 1. 조회버튼(#searchBtn) 의 click 이벤트를 binding 한다.
	 *   - 조회버튼에 click 이벤트 발생시 일반계획목록조회 함수를 호출한다.
	 * 2. 일반종류(#select2) 콤보박스의 change 이벤트를 biding한다.
	 *   2.1 change 이벤트 발생시 changeSelect2 함수를 호출한다.
	 * 3. Form 안에서 필요한 필드에 대해 엔터키 이벤트를 바인딩한다.
	 *   - 엔터키 이벤트 발생시 pageObj.clickPage 함수를 호출한다.
	 */	
	pageObj.setEventHandler = function() {

		// 조회버튼
		$("#searchBtn").on("click", function() {
			$("#P_PAGE_NO").val("1");
			pageObj.listInqire();
		});		
		// 1. Form 안에서 필요한 Fields 에 엔터키 이벤트 핸들러를 등록한다.
		FwkCmmnUtil.setEnterKeyBinding(defaultFrm, ["P_SJ_NM_S","P_REGISTER_NM_S"], function() {
			// EnteKey 이벤트 발생시 현재페이지 다시 조회를 한다.
			pageObj.clickPage(1);
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


