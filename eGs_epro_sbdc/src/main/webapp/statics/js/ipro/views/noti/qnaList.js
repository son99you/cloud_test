/**
 * 알림마당 > qna 목록
 *
 * <pre>
 * noti 
 *    |_ qnaList.js
 * 
 * </pre>
 * @date : 2017. 06. 14
 * @version : 1.0
 * @author : 은우소프트 이주연
 */
(function() {
	
	/**
	 * Default Constructor
	 */
	pageObj = {};

	var defaultFrm = "searchFrm";
	
	//신규등록
	pageObj.registForm = function(){
//		alert("2");
		FwkCmmnUtil.submitForm("detailFrm", "/noti/qnaReplyRegistForm.do");
		return false;
	}
	
	detailInqire = function( bbsSn, bbsSecd){ 
		$("#detailFrm input[name='P_BBS_SECD']").val(bbsSecd);
		$("#detailFrm input[name='P_BBS_SN']").val(bbsSn);
		FwkCmmnUtil.submitForm("detailFrm", "/noti/qnaDetail.do");
	};	
	
	
	pageObj.qnaList = function() {
		FwkCmmnUtil.submitForm("searchFrm", "/noti/qnaList.do");  
	}; 
	  
	pageObj.clickPage = function(pageNo) { 
		$("#" + defaultFrm + " #P_PAGE_NO").val(pageNo);
		pageObj.qnaList(); 
	};
	
	
	/**
	 * 페이지내에서 필요한 항목의 이벤트를 바인딩한다.
	 * 1. 목록버튼(#listBtn) 의 click 이벤트를 binding 한다.
	 */	
	pageObj.setEventHandler = function() {
		
		$("#registBtn").on("click", function(){
			FwkCmmnUtil.submitForm("detailFrm", "/noti/qnaReplyRegistForm.do");
			return false;
		});
		
		
		// 1. Form 안에서 필요한 Fields 에 엔터키 이벤트 핸들러를 등록한다.
		FwkCmmnUtil.setEnterKeyBinding(defaultFrm, ["P_TTL_NM_S","P_REGR_NM_S"], function() {
			// EnteKey 이벤트 발생시 현재페이지 다시 조회를 한다.
			pageObj.clickPage(1);
		});
		// 조회버튼 
		$("#searchBtn").on("click", function() {
			$("#P_PAGE_NO").val("1");
			$("#P_PAGE_SIZE").val("10");
			pageObj.qnaList(); 
		});
	};

	
	/**
	 * window load
	 * 
	 */
	$(function() {		 
		pageObj.setEventHandler();
		// 엑셀다운로드 공통
		excelDownSetting("searchFrm");
	});
})();