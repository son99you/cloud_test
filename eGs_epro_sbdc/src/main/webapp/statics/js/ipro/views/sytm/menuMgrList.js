/**
 * Admin > 메뉴관리 목록
 *
 * <pre>
 * sytm
 *    |_menuMgrList.js
 * 
 * </pre>
 * @date : 2017.06.15
 * @version : 1.0
 * @author : 은우소프트 이주연
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
	
	
	pageObj.menuMgrList = function() {
		FwkCmmnUtil.submitForm("searchFrm", "/sytm/menuMgrList.do");
	};

	pageObj.clickPage = function(pageNo) {
		$("#searchFrm #P_PAGE_NO").val(pageNo);
		pageObj.menuMgrList();
	};
	
	pageObj.setEventHandler = function() {
		
		// 조회버튼
		$("#searchBtn").on("click", function() {
			$("#P_PAGE_NO").val("1");
			$("#P_PAGE_SIZE").val("10");
			pageObj.menuMgrList();			
		});
		
		// 1. Form 안에서 필요한 Fields 에 엔터키 이벤트 핸들러를 등록한다.
		FwkCmmnUtil.setEnterKeyBinding(defaultFrm, ["P_MENU_ID_S","P_MENU_NM_S", "P_LNK_URL_S","P_LRG_MENU_ID_S"], function() {
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
		// 엑셀다운로드 공통
		excelDownSetting("searchFrm");
	});
})();