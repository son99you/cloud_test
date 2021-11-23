/**
 * 알림마당 > 일반공지사항 목록
 *
 * <pre>
 * noti
 *    |_ notiList.js
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

	detailInqire = function(recrNo){ 
		$("#detailFrm input[name='P_ESTM_NO']").val(recrNo);
		FwkCmmnUtil.submitForm("detailFrm", "/opro/recr/recrAnncDetail.do");
	};	
	  

	pageObj.recrAnncList = function() {
		FwkCmmnUtil.submitForm(defaultFrm, "/opro/recr/recrAnncList.do"); 
	};
	
	pageObj.clickPage = function(pageNo) { 
		$("#" + defaultFrm + " #P_PAGE_NO").val(pageNo);
		pageObj.recrAnncList(); 
	};
	 
	
	
	/**
	 * 페이지내에서 필요한 항목의 이벤트를 바인딩한다.
	 * 1. 목록버튼(#listBtn) 의 click 이벤트를 binding 한다.
	 */	
	pageObj.setEventHandler = function() {

		// 조회버튼
		$("#searchBtn").on("click", function() {
			$("#P_PAGE_NO").val("1");
			$("#P_PAGE_SIZE").val("10");
			pageObj.recrAnncList();
		});
		
		// 1. Form 안에서 필요한 Fields 에 엔터키 이벤트 핸들러를 등록한다.
		FwkCmmnUtil.setEnterKeyBinding(defaultFrm,
							["P_RSDN_NO_1"
							 ,"P_RSDN_NO_2"
							 ,"P_CRTR_NM"
							 ,"P_TEL_NO"
							 ,"P_EMAL"
							 ,"P_CHNL_NM"
							 ,"P_SSCRT_CNT"
							 ,"P_CHNL_ADDR"
							 ], function() {
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


