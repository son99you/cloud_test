/**
 * 통합게시판 > 서식자료실 목록
 *
 * <pre>
 * noti
 *    |_ qnaList.js
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

	pageObj.registForm = function() {
		FwkCmmnUtil.submitForm("detailFrm", "/opro/noti/qnaRegistForm.do");
		return false;
	};
	
	detailInqire = function(bbsSecd, sn){ 
		$("#detailFrm input[name='P_BBS_SECD']").val(bbsSecd);
		$("#detailFrm input[name='P_BBS_SN']").val(sn);
		FwkCmmnUtil.submitForm("detailFrm", "/opro/noti/qnaDetail.do");
	};	
	  

	pageObj.qnaList = function() {
		FwkCmmnUtil.submitForm("searchFrm", "/opro/noti/qnaList.do"); 
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
		// 등록버튼
		$("#registBtn").on("click", function() {
			FwkCmmnUtil.submitForm("detailFrm", "/opro/noti/qnaRegistForm.do");
//			pageObj.registForm();
			return false;
		});
		
		FwkCmmnUtil.setEnterKeyBinding(defaultFrm, ["P_TTL_NM_S","P_REGR_NM_S"], function() {
			// EnteKey 이벤트 발생시 현재페이지 다시 조회를 한다.
			pageObj.clickPage(1);
		});
		// 조회버튼
		$("#searchBtn").on("click", function() {
			$("#P_PAGE_NO").val("1");
			$("#P_PAGE_SIZE").val("10");
			//removeComma();
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
		/** 
		 * 라온시큐어
		 * 화면캡쳐, 키보드보안 적용
		 */
//		TouchEnNxConfig.installPage.tos = TouchEnNxConfig.installPage.nxkey;
		//화면캡쳐 보안 실행시
		
//		nxwebhtml5_load(0);	//0으로 설정 할 경우 화면캡쳐 작동 안함
		//화면캡쳐 보안 실행 안할경우  개발시에는 0으로 하고 진행
		//nxwebhtml5_load(0);	//0으로 설정 할 경우 화면캡쳐 작동 안함

	});
})();