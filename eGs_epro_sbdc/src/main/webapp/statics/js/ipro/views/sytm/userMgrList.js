/**
 * 정보관리 > 사용자관리 목록
 *
 * <pre>
 * sytm
 *    |_ userMrgList.js
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
	
	
	// 목록
	pageObj.userMgrList = function() {
		FwkCmmnUtil.submitForm(defaultFrm, "/sytm/userMgrList.do");
	};
	
	
	// 상세
	detailInqire = function (usrId, deptNo){
		$("#detailFrm input[name='P_USR_ID_S']").val(usrId);
		$("#detailFrm input[name='P_LEGACYDEPTCODE']").val(deptNo);
		FwkCmmnUtil.submitForm("detailFrm", "/sytm/userMgrDetail.do");
	};
	
	
	pageObj.clickPage = function(pageNo) {
		$("#" + defaultFrm + " #P_PAGE_NO").val(pageNo);
		pageObj.userMgrList();
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
			pageObj.userMgrList();			
		});
		
		// 1. Form 안에서 필요한 Fields 에 엔터키 이벤트 핸들러를 등록한다.
		FwkCmmnUtil.setEnterKeyBinding(defaultFrm, ["P_USR_ID_S","P_USR_NM_S","P_AUTH_ID_S"], function() {
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
		excelDownSetting("searchFrm");
	});
})();