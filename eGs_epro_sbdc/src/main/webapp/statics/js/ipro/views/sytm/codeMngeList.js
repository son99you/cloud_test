/**
 * Admin > 코드관리 목록
 *
 * <pre>
 * sytm
 *    |_codeMngeList.js
 * 
 * </pre>
 * @date : 2019.01.30
 * @version : 1.0
 * @author : 은우소프트 은잔디
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
	

	pageObj.codeMngeRegForm = function() {
		FwkCmmnUtil.submitForm("regFrm", "/sytm/codeMngeRegForm.do");
	};
	
	pageObj.codeMngeList = function() {
		FwkCmmnUtil.submitForm(defaultFrm, "/sytm/codeMngeList.do");
	};
	
	pageObj.clickPage = function(pageNo) {
		$("#" + defaultFrm + " #P_PAGE_NO").val(pageNo);
		pageObj.codeMngeList();
	};
	
	pageObj.setEventHandler = function() {
		
		$("#registBtn").on("click", function() {
			pageObj.codeMngeRegForm();
		});
		
		// 조회버튼
		$("#searchBtn").on("click", function() {
			$("#P_PAGE_NO").val("1");
			$("#P_PAGE_SIZE").val("10");
			pageObj.codeMngeList();			
		});
		
		// 1. Form 안에서 필요한 Fields 에 엔터키 이벤트 핸들러를 등록한다.
		FwkCmmnUtil.setEnterKeyBinding("searchFrm", ["P_CD_ID_S", "P_CD_NM_S", "P_CD_DTL_NM_S"], function() {
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


