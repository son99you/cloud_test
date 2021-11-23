/**
 * 조달공통 > 내부 담당자 조회
 *
 * <pre>
 * elbi
 *    |_ infoApprRsnRegist.js
 * 
 * </pre>
 * @date : 2018. 03. 26.
 * @version : 1.0
 * @author : 은우소프트 맹경열
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
     * </pre>
     * @Function Name : chargerListInqire
     * @date : 2018. 03. 26.
     * @author : 은우소프트 맹경열
     * @history : 
     *  =======================================================
     *  변경일             		작성자                     		변경내용  
     *  =======================================================
     *  2018. 03. 26.       은우소프트 맹경열              최초 작성 
     *  =======================================================   
     */
	pageObj.chargerListInqire = function() {
		FwkCmmnUtil.submitForm(defaultFrm, "/comm/popup/chargerList.do");
	};

	/**  
	 * <pre>
	 * 1. 개요 : 부모창에 체크 선택된 사용자 값 전달
	 * 2. 처리내용 : 
	 * 		부모창에 체크 선택된 사용자 값 전달한다
	 * </pre>
	 * @Function Name : chargerListRegist
     * @date : 2018. 03. 26.
     * @author : 은우소프트 맹경열
     * @history : 
     *  =======================================================
     *  변경일             		작성자                     		변경내용  
     *  =======================================================
     *  2018. 03. 26.       은우소프트 맹경열              최초 작성 
     *  =======================================================
	 */
	pageObj.apprRsnRegist = function() {
		var jsonData = $("#rsnFrm").serializeObject();
		var actionUrl = "/info/infoApprTglUpdate";
		
		FwkCmmnUtil.submitAjax (actionUrl, jsonData
				, function(res) {
					alert("의견이 등록되었습니다.");
					self.close();
				}
		);
	};
	
	/**  
     * <pre>
     * 1. 개요 : 페이지번호 클릭
     * 2. 처리내용 : 
     * 		- 선택된 페이지번호를 set 한다.
     *  	- 입찰설명회 목록조회를 호출한다.
     * </pre>
     * @Function Name : clickPage
     * @date : 2018. 03. 26.
     * @author : 은우소프트 맹경열
     * @history : 
     *  =======================================================
     *  변경일             		작성자                     		변경내용  
     *  =======================================================
     *  2018. 03. 26.       은우소프트 맹경열              최초 작성 
     *  =======================================================  
     */
	pageObj.clickPage = function(pageNo) {
		
		$("#" + defaultFrm + " #P_PAGE_NO").val(pageNo);
		pageObj.chargerListInqire();
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
			pageObj.chargerListInqire();			
		});
		
		//선택버튼
		$("#saveBtn").on("click", function() {
			pageObj.apprRsnRegist();
		});
		
		// 닫기버튼
		$("#closeBtn").on("click", function() {
			self.close();		
		});
		
		// 1. Form 안에서 필요한 Fields 에 엔터키 이벤트 핸들러를 등록한다.
		FwkCmmnUtil.setEnterKeyBinding(defaultFrm, ["P_USER_NM_S","select2"], function() {
			// EnteKey 이벤트 발생시 현재페이지 다시 조회를 한다.
			pageObj.clickPage(1);
		});
		// 1. Form 안에서 필요한 Fields 에 엔터키 이벤트 핸들러를 등록한다.
		FwkCmmnUtil.setEnterKeyBinding(defaultFrm, ["P_ORG_NM_S","select2"], function() {
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