/**
 * 마이페이지 > 부정당업체등록정보 목록
 *
 * <pre>
 * user
 *    |_ snctVendRegInfoList.js
 * 
 * </pre>
 * @date : 2019. 03. 08. 
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
	
	
	// 목록
	pageObj.snctVendRegInfoList = function() {
		loading();
		FwkCmmnUtil.submitForm(defaultFrm, "/opro/user/snctVendRegInfoList.do"); 
	};
	
	
	// 상세
	detailInqire = function(vendRegNo, snctSn) {
		$("#popupFrm input[name='P_VEND_REG_NO']").val(vendRegNo);
		$("#popupFrm input[name='P_SNCT_SN']").val(snctSn);
		
		$("#popupFrm").one("submit", function() {
			window.open("", "snctVendRegInfoDetail", "width=700px,height=700px,toolbar=no,status=no,scrollbars=yes,resizeable=no,menubar=no,left=500,top=100");
			this.action = FwkCmmnUtil['contextPath']+'/opro/user/popup/snctVendRegInfoDetail.do';
	        this.method = 'POST';
	        this.target = 'snctVendRegInfoDetail';
	    }).trigger("submit");
		
	};

	
	/**  
     * <pre>
     * 1. 개요 : 페이지번호 클릭
     * 2. 처리내용 : 
     * 		- 선택된 페이지번호를 set 한다.
     *  	- 입찰계획목록조회를 호출한다.
     * </pre>
     */
	pageObj.clickPage = function(pageNo) {
		$("#" + defaultFrm + " #P_PAGE_NO").val(pageNo);
		pageObj.snctVendRegInfoList();
	};
	

	/**
	 * 페이지내에서 필요한 항목의 이벤트를 바인딩한다.
	 * 1. 조회버튼(#searchBtn) 의 click 이벤트를 binding 한다.
	 *   - 조회버튼에 click 이벤트 발생시 연간발주계획 목록 조회 함수를 호출한다.
	 * 2. Form 안에서 필요한 필드에 대해 엔터키 이벤트를 바인딩한다.
	 *   - 엔터키 이벤트 발생시 pageObj.clickPage 함수를 호출한다.
	 */	
	pageObj.setEventHandler = function() {
		
		// 조회버튼
		$("#searchBtn").on("click", function() {
			$("#P_PAGE_NO").val("1");
			$("#P_PAGE_SIZE").val("10");
			removeComma();
			pageObj.snctVendRegInfoList();
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