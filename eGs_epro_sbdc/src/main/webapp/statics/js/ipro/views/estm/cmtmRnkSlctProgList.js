/**
 * 평가관리 > 평가위원순위선정진행현황 목록
 *
 * <pre>
 * estm
 *    |_ cmtmRnkSlctProgList.js
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

	var defaultFrm = "searchFrm";
	
	// 상세
	detailInqire = function(estmNo, maxEstmCmtmSlctNgr){
		$("#detailFrm input[name='P_ESTM_NO']").val(estmNo);
		$("#detailFrm input[name='P_ESTM_CMTM_SLCT_NGR']").val(maxEstmCmtmSlctNgr);
		FwkCmmnUtil.submitForm("detailFrm", "/estm/cmtmRnkSlctProgDetail.do");
	};
	
	// 목록
	pageObj.cmtmRnkSlctProgList = function() {
		FwkCmmnUtil.submitForm(defaultFrm, "/estm/cmtmRnkSlctProgList.do"); 
	};
	
	
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
	
	pageObj.clickPage = function(pageNo) { 
		$("#" + defaultFrm + " #P_PAGE_NO").val(pageNo);
		pageObj.cmtmRnkSlctProgList(); 
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
			pageObj.cmtmRnkSlctProgList();
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
		FwkCmmnUtil.setEnterKeyBinding(defaultFrm, ["P_ESTM_NO_S", "P_ESTM_NM_S"], function() {
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