/**
 * 정보관리 > 로그관리 목록
 *
 * <pre>
 * sytm
 *    |_ logMngeList.js
 * 
 * </pre>
 * @date : 2016. 09. 28
 * @version : 1.0
 * @author :  yhs
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
	pageObj.logMngeList = function() {
		FwkCmmnUtil.submitForm(defaultFrm, "/sytm/logMngeList.do");
	};
	
	
	// 상세
	detailInqire = function(logSn) {
		$("#popupFrm input[name='P_LOG_SN']").val(logSn);
		
		$("#popupFrm").one("submit", function() {
			window.open("", "snctVendDetail", "width=700px,height=500px,toolbar=no,status=no,scrollbars=yes,resizeable=no,menubar=no,left=500,top=100");
			this.action = FwkCmmnUtil['contextPath']+'/sytm/popup/logMngeDetail.do';
	        this.method = 'POST';
	        this.target = 'snctVendDetail';
	    }).trigger("submit");
		
	};
	
	
	pageObj.clickPage = function(pageNo) {
		$("#" + defaultFrm + " #P_PAGE_NO").val(pageNo);
		pageObj.logMngeList();
	};
	
	pageObj.setEventHandler = function() {
		
		// 조회버튼
		$("#searchBtn").on("click", function() {
			$("#P_PAGE_NO").val("1");
			$("#P_PAGE_SIZE").val("10");
			pageObj.logMngeList();			
		});
		
		// 1. Form 안에서 필요한 Fields 에 엔터키 이벤트 핸들러를 등록한다.
		FwkCmmnUtil.setEnterKeyBinding(defaultFrm, ["P_CONN_ID_S","P_REGR_NM_S"], function() {
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