/**
 * 평가관리 > 평가위원순위선정완료현황 상세
 *
 * <pre>
 * estm
 *    |_ cmtmRnkSlctCmplDetail.js
 * 
 * </pre>
 * @date : 2021. 03. 19.
 * @version : 1.0
 * @author : 은우소프트
 */

(function() {
	
	/**
	 * Default Constructor
	 */
	pageObj = {};

	var defaultFrm = "detailFrm";

	pageObj.cmtmRnkSlctCmplList = function() {
		FwkCmmnUtil.submitForm("listFrm", "/estm/cmtmRnkSlctCmplList.do"); 
	};
	
	// 선정차수 상세
	estmCmtmSlctNgrDetail = function(estmCmtmSlctNgr){
		$("#P_ESTM_CMTM_SLCT_NGR").val(estmCmtmSlctNgr);
		FwkCmmnUtil.submitForm("detailFrm", "/estm/cmtmRnkSlctCmplDetail.do");
	};
	
	
	pageObj.clickPage = function(pageNo) { 
		$("#" + defaultFrm + " #P_PAGE_NO").val(pageNo);
		pageObj.cmtmRnkSlctCmplList(); 
	};
	
	
	/**
	 * 페이지내에서 필요한 항목의 이벤트를 바인딩한다.
	 * 1. 목록버튼(#listBtn) 의 click 이벤트를 binding 한다.
	 */	
	pageObj.setEventHandler = function() {
		
		// 목록버튼
		$("#listBtn").on("click", function() {
			pageObj.cmtmRnkSlctCmplList();
		});
		
	};

	 
	/**
	 * window load
	 *
	 */
	$(function() {		
		pageObj.setEventHandler();
		
		fileView($("#P_FILE_GRP_NO").val());
	});
})();