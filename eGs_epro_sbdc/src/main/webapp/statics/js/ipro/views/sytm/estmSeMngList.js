(function() {

	/**
	 * Default Constructor
	 */
	pageObj = {};

	var defaultFrm = "searchFrm";

	
	detailInqire = function(ESTM_SECD, ESTM_DEPT_NO){ 
		$("#detailFrm input[name='P_ESTM_SECD']").val(ESTM_SECD);
		
		if(ESTM_DEPT_NO != null && ESTM_DEPT_NO != "" ){
			FwkCmmnUtil.submitForm("detailFrm", "/sytm/estmSeMngDetail.do");
		}else {
			FwkCmmnUtil.submitForm("detailFrm", "/sytm/estmSeMngRegForm.do");
		}
	};
	
	//estmFrmCodeRegForm
	
	
	pageObj.estmFrmResultRegForm = function() {
		FwkCmmnUtil.submitForm("registFrm", "/sytm/estmFrmResultRegForm.do");
	};
	
	
	returnEstmSeMngListOnload = function() {
		FwkCmmnUtil.submitForm(defaultFrm , "/sytm/estmSeMngList.do"); 
	};
	
	pageObj.estmFrmCodeRegForm = function() {
		/*$("#popupFrm input[name='setMulti']").val("N");
		$("#popupFrm").one("submit", function() {
			window.open("", "estmFrmCodeRegForm", "width=500px,height=300px,toolbar=no,status=no,scrollbars=yes,resizeable=no,menubar=no,left=500,top=100");
			this.action = FwkCmmnUtil['contextPath']+'/comm/popup/estmFrmCodeRegForm.do';
	        this.method = 'POST';
	        this.target = 'estmFrmCodeRegForm';
	    }).trigger("submit");*/
		FwkCmmnUtil.submitForm("detailFrm", "/sytm/estmSeMngRegForm.do");
	};
	
	pageObj.estmSeMngList = function() {
		FwkCmmnUtil.submitForm(defaultFrm , "/sytm/estmSeMngList.do"); 
	};
	 
	pageObj.clickPage = function(pageNo) { 
		$("#" + defaultFrm + " #P_PAGE_NO").val(pageNo);
		pageObj.estmSeMngList(); 
	};
	
	// 부서팝업 스크립트 시작 //
	// 부서 조회 팝업
	pageObj.deptInqirePopup = function() {
		$("#popupFrm input[name='setMulti']").val("N");
		$("#popupFrm").one("submit", function() {
			window.open("", "deptPopup", "width=750px,height=760px,toolbar=no,status=no,scrollbars=yes,resizeable=no,menubar=no,left=500,top=100");
			this.action = FwkCmmnUtil['contextPath']+'/comm/popup/deptList.do';
	        this.method = 'POST';
	        this.target = 'deptPopup';
	    }).trigger("submit");
	};
	
	//평가담당부서 삭제
	pageObj.deptInqireDelete = function() {
		$("#P_ESTM_CHRG_DEPT_NO_S").val("");
		$("#P_ESTM_CHRG_DEPT_NM_S").val("");
	};
	
	deptListAdd = function(deptNo, deptNm){
		
		$("#P_ESTM_CHRG_DEPT_NO_S").val(deptNo);
		$("#P_ESTM_CHRG_DEPT_NM_S").val(deptNm);
		
	}
	// 부서팝업 스크립트 종료 //
	
	pageObj.setEventHandler = function() {
		
		
		
		$("#searchBtn").on("click", function() {
			$("#P_PAGE_NO").val("1");
			$("#P_PAGE_SIZE").val("10");
			pageObj.estmSeMngList();
		});
		
		
		$("#codeRegistBtn").on("click", function() {
			pageObj.estmFrmCodeRegForm(); 
		});
		
		$("#registResultBtn").on("click", function() {
			pageObj.estmFrmResultRegForm(); 
		});
		
		$("#registBtn").on("click", function() {
			pageObj.estmFrmRegForm(); 
		});
		
		// 평가담당부서 검색버튼
		$("#estmChrgDepSrchBtn").on("click", function() {
			pageObj.deptInqirePopup();
		});
		
		// 평가담당부서 삭제버튼
		$("#estmChrgDeptDelBtn").on("click", function() {
			pageObj.deptInqireDelete();
		});
		
		// 1. Form 안에서 필요한 Fields 에 엔터키 이벤트 핸들러를 등록한다.
		FwkCmmnUtil.setEnterKeyBinding(defaultFrm, ["P_ESTM_SECD_S", "P_ESTM_CHRG_DEPT_NM_S"], function() {
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