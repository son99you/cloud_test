/**
 * 시스템관리 > 권한관리
 *
 * <pre>
 * sytm
 *    |_authMgrList.js
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
	var defaultFrm = "listFrm";

	
	// 목록
	pageObj.authMgrList = function() {
		FwkCmmnUtil.submitForm(defaultFrm, "/sytm/authMgrList.do");
	};
	
	
	
	// 상세
	detailInqire = function (authId){
		
		$("#viewFrm input[name='P_AUTH_ID']").val(authId);
		
		FwkCmmnUtil.submitForm("viewFrm", "/sytm/authMgrDetail.do");
		
		/*$("#popupFrm input[name='P_AUTH_ID']").val(authId);

		$("#popupFrm").one("submit", function() {
			window.open("", "menuAuthMgrList", "width=750px, height=780px, toolbar=no, status=no, resizeable=no, scrollbars=yes, menubar=no, left="+x(740)+",top=100");
			this.action = FwkCmmnUtil['contextPath']+'/comm/popup/menuAuthMgrList.do';
	        this.method = 'POST';
	        this.target = 'menuAuthMgrList';
	    }).trigger("submit");*/
	};
	
	
	pageObj.setEventHandler = function() {
	
		// 조회버튼
		$("#searchBtn").on("click", function() {
			$("#P_PAGE_NO").val("1");
			$("#P_PAGE_SIZE").val("10");
			pageObj.authMgrList();			
		});
		
		// 1. Form 안에서 필요한 Fields 에 엔터키 이벤트 핸들러를 등록한다.
		FwkCmmnUtil.setEnterKeyBinding(defaultFrm, ["P_AUTH_NM_S"], function() {
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