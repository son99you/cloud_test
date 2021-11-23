/**
 * 공통 > 평가위원위원평가(팝업)
 *
 * <pre>
 * comm 
 *    |_ popup
 *       |_ estmCmtmScrUpdtFrm.js
 * 
 * </pre>
 * @date : 2021.04.26
 * @version : 1.0
 * @author : 은우소프트 이재인
 */
(function() {

	/**
	 * Default Constructor
	 */
	pageObj = {};

	/**
	 * Default Form Name
	 */
	var defaultFrm = "updtFrm";

	pageObj.cmtmScrUpdt = function(){
		var jsonData = $("#updtFrm").serializeObject();
		var actionUrl = "/comm/popup/estmCmtmScrUpdt";

		FwkCmmnUtil.submitAjax (actionUrl, jsonData
				, function(res) {
			FwkCmmnUtil.submitForm(defaultFrm, "/comm/popup/estmCmtmScrUpdtFrm.do"); 
		});
		//FwkCmmnUtil.submitForm(defaultFrm, "/comm/popup/estmCmtmScrUpdt.do"); 
	}
	/**
	 * 페이지내에서 필요한 항목의 이벤트를 바인딩한다.
	 * 1. 닫기버튼(#closeBtn) 의 click 이벤트를 binding 한다.
	 *   - 닫기버튼에 click 이벤트 발생시 팝업을 닫는다.
	 * 2. Form 안에서 필요한 필드에 대해 엔터키 이벤트를 바인딩한다.
	 *   - 엔터키 이벤트 발생시 pageObj.clickPage 함수를 호출한다.
	 */	
	pageObj.setEventHandler = function() {

		
		// 닫기버튼
		$("#colseBtn").on("click", function() {
			self.close();
			return false;
		});
		//저장버튼
		$("#saveBtn").on("click", function() {
			pageObj.cmtmScrUpdt();
		});
		
	};

	/** 
	 * window load
	 *
	 */
	$(function(){
		pageObj.setEventHandler();
	});
})();