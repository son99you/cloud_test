/**
 * 알림마당 > 내부공지사항 목록
 *  
 *   <pre>
 * noti
 *    |_ inNoticeBoardList.js
 * 
 * </pre>
 * @date : 2017.06.14.
 * @version : 1.0
 * @author : 은우소프트 이주연
 */


(function() {
	
	/**
	 * Default Constructor
	 */
	pageObj = {};
	var defaultFrm = "biSearchForm";
	
	/*
		movePage = function(url) {
			FwkCmmnUtil.submitForm("detailFrm", url);  
		};
	*/
	
	detailInqire = function(nttSn){ 
		$("#detailFrm input[name='P_NTT_SN']").val(nttSn);
		FwkCmmnUtil.submitForm("detailFrm", "/noti/inNoticeBoardDetail.do");
	};	
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
		FwkCmmnUtil.submitForm("detailFrm", "/noti/inNoticeBoardRegistForm.do");
	};
	
	pageObj.inNoticeBoardList = function() {
		FwkCmmnUtil.submitForm(defaultFrm, "/noti/inNoticeBoardList.do"); 
	};
	 
	pageObj.clickPage = function(pageNo) { 
		$("#" + defaultFrm + " #P_PAGE_NO").val(pageNo);
		pageObj.inNoticeBoardList(); 
	};
	
	/**
	 * 페이지내에서 필요한 항목의 이벤트를 바인딩한다.
	 * 1. 목록버튼(#listBtn) 의 click 이벤트를 binding 한다.
	 */	
	pageObj.setEventHandler = function() {

		// 등록버튼
		$("#registBtn").on("click", function() {
			pageObj.registForm();
		});
		
		// 조회버튼 
		$("#searchBtn").on("click", function() {
			$("#P_PAGE_NO").val("1");
			$("#P_PAGE_SIZE").val("10");
			//removeComma();
			pageObj.inNoticeBoardList(); 
		}); 
	};

	
	/**
	 * window load
	 *
	 */
	$(function() {		
		pageObj.setEventHandler();
	});
})();


