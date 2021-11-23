(function() {

	/**
	 * Default Constructor
	 */
	pageObj = {};

	var defaultFrm = "searchFrm";

	
	detailInqire = function(P_ESTM_FRM_NO){ 
		$("#detailFrm input[name='P_ESTM_FRM_NO']").val(P_ESTM_FRM_NO); 
		FwkCmmnUtil.submitForm("detailFrm", "/sytm/estmFrmDetail.do");
	};
	
	
	pageObj.dataBaseRegist = function(P_ESTM_FRM_NO){ 
		$("#detailFrm input[name='P_DATABASE_NM']").val("TEST"); 
		FwkCmmnUtil.submitForm("detailFrm", "/sytm/dataBaseRegist.do");
	};
	
	
	pageObj.estmFrmResultRegForm = function() {
		FwkCmmnUtil.submitForm("registFrm", "/sytm/estmFrmResultRegForm.do");
	};
	
	pageObj.estmFrmRegForm = function() {
		FwkCmmnUtil.submitForm("registFrm", "/sytm/estmFrmRegForm.do");
	};
	
	pageObj.estmFrmList = function() { 
		FwkCmmnUtil.submitForm(defaultFrm , "/sytm/estmFrmList.do"); 
	};
	 
	pageObj.clickPage = function(pageNo) { 
		$("#" + defaultFrm + " #P_PAGE_NO").val(pageNo);
		pageObj.estmFrmList(); 
	};
	
	
	pageObj.setEventHandler = function() {
		
		$("#searchBtn").on("click", function() {
			$("#P_PAGE_NO").val("1");
			$("#P_PAGE_SIZE").val("10");
			pageObj.estmFrmList();
		});
		
		
		$("#registResultBtn").on("click", function() {
			pageObj.estmFrmResultRegForm(); 
		});
		
		$("#registBtn").on("click", function() {
			pageObj.estmFrmRegForm(); 
		});
		
		$("#registDataBaseBtn").on("click", function() {
			pageObj.dataBaseRegist();
		});
		
		// 1. Form 안에서 필요한 Fields 에 엔터키 이벤트 핸들러를 등록한다.
		FwkCmmnUtil.setEnterKeyBinding(defaultFrm, ["P_ESTM_PROCD_SECD_S", "P_ESTM_FRM_NM_S"], function() {
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