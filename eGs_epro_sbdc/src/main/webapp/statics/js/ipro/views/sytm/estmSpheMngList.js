/**
 * 정보관리 > 평가분야관리 목록
 *
 * <pre>
 * sytm
 *    |_ estmSpheMngList.js
 * 
 * </pre>
 * @date : 2021. 03. 23
 * @version : 1.0
 * @author : 은우소프트
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
	pageObj.estmSpheMngList = function() {
		FwkCmmnUtil.submitForm(defaultFrm, "/sytm/estmSpheMngList.do");
	};
	
	pageObj.registForm = function() {
		FwkCmmnUtil.submitForm(defaultFrm, "/sytm/estmSpheMngRegistForm.do");
	};
	
	// 상세
	detailInqire = function(estmSpheSecd){ 
		$("#detailFrm input[name='P_ESTM_SPHE_SECD']").val(estmSpheSecd);
		FwkCmmnUtil.submitForm("detailFrm", "/sytm/estmSpheMngDetail.do");
	};

	pageObj.clickPage = function(pageNo) { 
		$("#" + defaultFrm + " #P_PAGE_NO").val(pageNo);
		pageObj.estmSpheMngList(); 
	};
	
	pageObj.setEventHandler = function() {
		
		// 신규등록 버튼
		$("#registBtn").on("click", function() {
			pageObj.registForm();
		});
	
		// 조회버튼
		$("#searchBtn").on("click", function() {
			$("#P_PAGE_NO").val("1");
			$("#P_PAGE_SIZE").val("10");
			pageObj.estmSpheMngList();			
		});
		
		// 1. Form 안에서 필요한 Fields 에 엔터키 이벤트 핸들러를 등록한다.
		FwkCmmnUtil.setEnterKeyBinding(defaultFrm, ["P_ESTM_SPHE_SENM_S"], function() {
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
	});
})();