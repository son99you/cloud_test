/**
 * 공통 > 평가정보목록 조회(팝업)
 *
 * <pre>
 * comm 
 *    |_ popup
 *       |_ estmInfoList.js
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

	/**  
     * <pre>
     * 1. 개요 : 담당자조회 Submit
     * 2. 처리내용 : 
     * 		담당자 조회 Form 을 Sumit 한다.
     *  	
     * </pre>
     */
	pageObj.estmInfoListInqire = function() {
		FwkCmmnUtil.submitForm(defaultFrm, "/comm/popup/estmInfoList.do");
	};

	
	/**  
	 * <pre>
	 * 1. 개요 : 부모창에 선택된 사용자 값 전달
	 * 2. 처리내용 : 
	 * 		부모창에 선택된 사용자 값 전달한다
	 *  	
	 * </pre>
	 */
	setEstmInfo = function(estmAnncNo, estmAnncNm) {
		try {
			window.opener.setEstmInfoAdd(estmAnncNo, estmAnncNm);	
		}catch(exception) {
			//console.log("부모객체에 function 없으면 통과(별다른 문제 없음)");
		}
		self.close();
	}
	
	/**  
     * <pre>
     * 1. 개요 : 페이지번호 클릭
     * 2. 처리내용 : 
     * 		- 선택된 페이지번호를 set 한다.
     *  	- 입찰설명회 목록조회를 호출한다.
     * </pre>
     */
	pageObj.clickPage = function(pageNo) {
		$("#" + defaultFrm + " #P_PAGE_NO").val(pageNo);
		pageObj.estmInfoListInqire();
	};
	
	
	/**
	 * 페이지내에서 필요한 항목의 이벤트를 바인딩한다.
	 * 1. 조회버튼(#searchBtn) 의 click 이벤트를 binding 한다.
	 *   - 조회버튼에 click 이벤트 발생시 해당 함수를 호출한다.
	 * 2. 닫기버튼(#closeBtn) 의 click 이벤트를 binding 한다.
	 *   - 닫기버튼에 click 이벤트 발생시 팝업을 닫는다.
	 * 3. Form 안에서 필요한 필드에 대해 엔터키 이벤트를 바인딩한다.
	 *   - 엔터키 이벤트 발생시 pageObj.clickPage 함수를 호출한다.
	 */	
	pageObj.setEventHandler = function() {

		// 조회버튼
		$("#searchBtn").on("click", function() {
			$("#P_PAGE_NO").val("1");
			$("#P_PAGE_SIZE").val("10");
			pageObj.estmInfoListInqire();			
		});
		
		// 1. Form 안에서 필요한 Fields 에 엔터키 이벤트 핸들러를 등록한다.
		FwkCmmnUtil.setEnterKeyBinding(defaultFrm, [ "P_USR_NM_S", "P_EMPL_NO_S" ], function() {
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