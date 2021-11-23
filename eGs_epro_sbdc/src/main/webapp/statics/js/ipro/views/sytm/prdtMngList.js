/**
 * 정보관리 > 상품관리 목록
 *
 * <pre>
 * sytm
 *    |_ prdtMngList.js
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
	
	movePage = function(url){
		FwkCmmnUtil.submitForm("detailFrm", url);
	};
	
	pageObj.prdtMngList = function() {
		FwkCmmnUtil.submitForm("searchFrm", "/sytm/prdtMngList.do");
	};

	pageObj.clickPage = function(pageNo) {
		$("#" + defaultFrm + " #P_PAGE_NO").val(pageNo);
		pageObj.prdtMngList();
	};
	
	pageObj.setEventHandler = function() {
		
		// 조회버튼
		$("#searchBtn").on("click", function() {
			$("#P_PAGE_NO").val("1");
			$("#P_PAGE_SIZE").val("10");
			pageObj.prdtMngList();			
		});
		
		// 1. Form 안에서 필요한 Fields 에 엔터키 이벤트 핸들러를 등록한다.
		FwkCmmnUtil.setEnterKeyBinding("searchFrm", ["P_ITEM_NO_S", "P_ITEM_NM_S"], function() {
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