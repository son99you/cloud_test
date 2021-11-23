/**
 * 평가관리 > 평가위원순위선정진행현황 상세
 *
 * <pre>
 * estm
 *    |_ cmtmRnkSlctProgDetail.js
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

	// 목록
	pageObj.cmtmRnkSlctProgList = function() {
		FwkCmmnUtil.submitForm("listFrm", "/estm/cmtmRnkSlctProgList.do"); 
	};
	
	
	// 상세
	pageObj.cmtmRnkSlctProgDetail = function(){
		FwkCmmnUtil.submitForm("detailFrm", "/estm/cmtmRnkSlctProgDetail.do");
	};
	
	
	// 우선순위자동선별
	pageObj.prioRnkAutoSlct = function() {
		
		$("#detailFrm input[name='P_ESTM_PSCD']").val("A004");
		
		var jsonData = $("#detailFrm").serializeObject();
		var actionUrl = "/estm/cmtmPrioRnkAutoSlct";

		FwkCmmnUtil.submitAjax (actionUrl, jsonData
				, function(res) {
			alert("처리되었습니다.");
			pageObj.cmtmRnkSlctProgList();
		});
	};
	
	// 평가위원선정차수에 따른 평가위원 조회
	estmCmtmSlctNgrDetail = function(estmCmtmSlctNgr){
		$("#searhFrm input[name='P_ESTM_CMTM_SLCT_NGR']").val(estmCmtmSlctNgr);
		
		var jsonData = $("#searhFrm").serializeObject();
		var actionUrl = "/estm/estmCmtmSlctNgrDetail";

		FwkCmmnUtil.submitAjax (actionUrl, jsonData
				, function(res) {
			pageObj.cmtmRnkSlctProgDetail();
		});
		
	};
	
	/**
	 * 페이지내에서 필요한 항목의 이벤트를 바인딩한다.
	 * 1. 목록버튼(#listBtn) 의 click 이벤트를 binding 한다.
	 */	
	pageObj.setEventHandler = function() {
		
		// 목록버튼
		$("#listBtn").on("click", function() {
			pageObj.cmtmRnkSlctProgList();
		});
		
		// 우선순위자동선별
		$("#prioRnkAutoSlctBtn").on("click", function() {
			
			if(!confirm("우선순위자동선별을 하시겠습니까?")){
				return false; 
			}
			
			pageObj.prioRnkAutoSlct();
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