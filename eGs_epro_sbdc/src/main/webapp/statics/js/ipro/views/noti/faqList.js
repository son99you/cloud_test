/**
 * 알림마당 > 자주묻는질문 목록
 *
 * <pre>
 * noti
 *    |_faqList.js
 * 
 * </pre>
 * @date : 2017.06.14
 * @version : 1.0
 * @author : 은우소프트 이주연
 */
(function() {
	
	/**
	 * Default Constructor
	 */
	pageObj = {};

	var defaultFrm = "searchFrm";
	
	/**  
     * <pre>
     * 1. 개요 : 내부 등록 폼 조회 
     * 2. 처리내용 : 
     * 		내부 공지사항 등록 폼 조회한다.
     *  	
     * </pre>
     * @Function Name : registForm
     * @date : 2017.06.13
     * @author : 은우소프트 이주연
     * @history : 
     *  =======================================================
     *  변경일             		작성자                     		변경내용  
     *  =======================================================
     *  2017. 06.13      은우소프트 이주연            최초 작성 
     *  =======================================================   
     */
	pageObj.registForm = function() {
//		alert("2");
		FwkCmmnUtil.submitForm("detailFrm", "/noti/faqRegistForm.do");
		return false;
	};
	
	
	detailInqire = function(bbsSecd, bbsSn){ 
		$("#detailFrm input[name='P_BBS_SECD']").val(bbsSecd);
		$("#detailFrm input[name='P_BBS_SN']").val(bbsSn);
		FwkCmmnUtil.submitForm("detailFrm", "/noti/faqDetail.do");
	};	
	
	
	pageObj.clickPage = function(pageNo) { 
		$("#" + defaultFrm + " #P_PAGE_NO").val(pageNo);
		pageObj.faqList(); 
	};
	
	pageObj.faqList = function() {
		FwkCmmnUtil.submitForm(defaultFrm, "/noti/faqList.do");  
	}; 
	
	
	/**
	 * 페이지내에서 필요한 항목의 이벤트를 바인딩한다.
	 * 1. 목록버튼(#listBtn) 의 click 이벤트를 binding 한다.
	 */	
	pageObj.setEventHandler = function() {

		// 등록버튼
		$("#registBtn").on("click", function() {
//			alert("1");
			FwkCmmnUtil.submitForm("detailFrm", "/noti/faqRegistForm.do");
//			pageObj.registForm();
			return false;
		});
		
		// 조회버튼 
		$("#searchBtn").on("click", function() {
			$("#P_PAGE_NO").val("1");
			$("#P_PAGE_SIZE").val("10");
			pageObj.faqList(); 
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
	$(function() {		 
		pageObj.setEventHandler();
		// 엑셀다운로드 공통
		excelDownSetting("searchFrm");
	});
})();