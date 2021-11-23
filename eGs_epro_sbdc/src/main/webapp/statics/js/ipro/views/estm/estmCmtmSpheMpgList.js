(function() {

	/**
	 * Default Constructor
	 */
	pageObj = {};

	var defaultFrm = "searchFrm";

	
	detailInqire = function(ESTM_SPHE_SECD, REG_AT){
		
		$("#detailFrm #P_ESTM_SPHE_SECD").val(ESTM_SPHE_SECD);
		
		if(REG_AT == "Y") {
			FwkCmmnUtil.submitForm("detailFrm", "/estm/estmCmtmSpheMpgDetail.do");
		}else if(REG_AT == "N") {
			FwkCmmnUtil.submitForm("detailFrm", "/estm/estmCmtmSpheMpgRegForm.do");
		}
	};
	
	
	pageObj.estmCmtmSpheMpgList = function() { 
		FwkCmmnUtil.submitForm(defaultFrm , "/estm/estmCmtmSpheMpgList.do"); 
	};
	 
	pageObj.clickPage = function(pageNo) { 
		$("#" + defaultFrm + " #P_PAGE_NO").val(pageNo);
		pageObj.estmCmtmSpheMpgList(); 
	};
	
	// 평가분야구분 조회
	pageObj.estmSpheSecd = function(){
		$("#P_ESTM_SPHE_SECD").empty();   // 소분류
		
		var actionUrl = "/estm/getEstmSpheSecd";
		var jsonData = $("#registFrm").serializeObject();
		
		var optionInit = "<option value=\"\" >선택</option>";
		$("#P_ESTM_SPHE_SECD").append(optionInit);
		
		FwkCmmnUtil.submitAjaxNoLoading(actionUrl, jsonData, function(res) {
			var option = "";
			var list = res.listEstmSpheSecd;
			
			$.each(list, function(inx, item) {
				option += "<option value='" + item.ESTM_SPHE_SECD + "' >" + item.ESTM_SPHE_SENM + "</option>";
			});
			
			$("#P_ESTM_SPHE_SECD").append(option);
		});
	};
	
	pageObj.setEventHandler = function() {
		
		$("#searchBtn").on("click", function() {
			$("#P_PAGE_NO").val("1");
			$("#P_PAGE_SIZE").val("10");
			pageObj.estmCmtmSpheMpgList();
		});
		
		
		$("#registResultBtn").on("click", function() {
			pageObj.estmFrmResultRegForm(); 
		});
		
		$("#registBtn").on("click", function() {
			pageObj.estmFrmRegForm(); 
		});
		
		
		// 1. Form 안에서 필요한 Fields 에 엔터키 이벤트 핸들러를 등록한다.
		FwkCmmnUtil.setEnterKeyBinding(defaultFrm, ["P_REGR_NM_S", "select2"], function() {
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
		pageObj.estmSpheSecd();
	});
	
})();