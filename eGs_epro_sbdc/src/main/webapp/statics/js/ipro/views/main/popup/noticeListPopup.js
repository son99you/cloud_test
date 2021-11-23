/**
 * 통합게시판 > 일반 공지사항 상세
 *
 * <pre>
 * noti
 *    |_ noticePopup.js
 * 
 * </pre>
 * @date : 2021.04.23
 * @version : 1.0
 * @author : 은우소프트 이재인
 */

(function() {

	/**
	 * Default Constructor
	 */
	defaultFrm = "searchFrm";
	pageObj = {};

	pageObj.notiListPopup = function() {
		FwkCmmnUtil.submitForm(defaultFrm, "/main/popup/noticeListPopup.do"); 
	};
	
	pageObj.clickPage = function(pageNo) { 
		$("#" + defaultFrm + " #P_PAGE_NO").val(pageNo);
		pageObj.notiListPopup(); 
	};
	
	detailInqire = function(bbsSecd, bbsSn){
		$("#detailFrm input[name='P_BBS_SECD']").val(bbsSecd);
		$("#detailFrm input[name='P_BBS_SN']").val(bbsSn);
		FwkCmmnUtil.submitForm("detailFrm", "/main/popup/noticePopup.do"); 
	};
	/**
	 * 페이지내에서 필요한 항목의 이벤트를 바인딩한다.
	 * 1. 목록버튼(#listBtn) 의 click 이벤트를 binding 한다.
	 */	
	pageObj.setEventHandler = function() {
		$("#searchBtn").on("click", function() {
			$("#P_PAGE_NO").val("1");
			$("#P_PAGE_SIZE").val("10");
			pageObj.notiListPopup();
		});

		// 1. Form 안에서 필요한 Fields 에 엔터키 이벤트 핸들러를 등록한다.
		FwkCmmnUtil.setEnterKeyBinding(defaultFrm, ["P_TTL_NM_S","P_REGR_NM_S"], function() {
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