/**
 * Admin > 시스템현황 > 나라장터연계이력 목록
 *
 * <pre>
 * sytm
 *    |_ g2bCntcHstyList.js
 * 
 * </pre>
 * @date : 2019. 04. 22
 * @version : 1.0
 * @author :  은우소프트 은잔디
 */
(function() {

	/**
	 * Default Constructor
	 */
	pageObj = {};
	
	/**
	 * Default Form Name
	 */
	var defaultFrm = "menuMoveFrm";
	
	
	// 목록
	pageObj.g2bCntcHstyList = function() {
		FwkCmmnUtil.submitForm(defaultFrm, "/sytm/g2bCntcHstyList.do");
	};
	
	
	// 상세
	detailInqire = function(cntcSn) {
		$("#popupFrm input[name='P_CNTC_SN']").val(cntcSn);
		
		$("#popupFrm").one("submit", function() {
			window.open("", "g2bCntcHstyDetail", "width=700px,height=570px,toolbar=no,status=no,scrollbars=yes,resizeable=no,menubar=no,left=500,top=100");
			this.action = FwkCmmnUtil['contextPath']+'/sytm/popup/g2bCntcHstyDetail.do';
	        this.method = 'POST';
	        this.target = 'g2bCntcHstyDetail';
	    }).trigger("submit");
		
	};
	
	
	pageObj.clickPage = function(pageNo) {
		$("#" + defaultFrm + " #P_PAGE_NO").val(pageNo);
		pageObj.g2bCntcHstyList();
	};
	
	pageObj.setEventHandler = function() {
		
		// 조회버튼
		$("#searchBtn").on("click", function() {
			$("#P_PAGE_NO").val("1");
			$("#P_PAGE_SIZE").val("10");
			pageObj.g2bCntcHstyList();			
		});
		
		// 1. Form 안에서 필요한 Fields 에 엔터키 이벤트 핸들러를 등록한다.
		FwkCmmnUtil.setEnterKeyBinding(defaultFrm, ["P_TTL_S"], function() {
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
	});
	
})();