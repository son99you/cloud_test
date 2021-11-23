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
	
	
	detailInqire = function(bbsSecd, bbsSn){ 
		$("#detailFrm input[name='P_BBS_SECD']").val(bbsSecd);
		$("#detailFrm input[name='P_BBS_SN']").val(bbsSn);
		FwkCmmnUtil.submitForm("detailFrm", "/opro/noti/faqDetail.do");
	};	
	
	pageObj.faqList = function() {
		FwkCmmnUtil.submitForm(defaultFrm, "/opro/noti/faqList.do");  
	}; 
	
	pageObj.clickPage = function(pageNo) { 
		$("#" + defaultFrm + " #P_PAGE_NO").val(pageNo);
		pageObj.faqList(); 
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
		 /** 
		 * 라온시큐어
		 * 화면캡쳐, 키보드보안 적용
		 */
		//TouchEnNxConfig.installPage.tos = TouchEnNxConfig.installPage.nxkey;
		//화면캡쳐 보안 실행시         nxwebhtml5_load(9);   //0으로 설정 할 경우 화면캡쳐 작동 안함
		//화면캡쳐 보안 실행 안할경우  개발시에는 0으로 하고 진행
		//nxwebhtml5_load(0);   //0으로 설정 할 경우 화면캡쳐 작동 안함

	});
})();