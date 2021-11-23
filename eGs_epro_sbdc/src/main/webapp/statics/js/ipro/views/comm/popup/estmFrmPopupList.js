/**
 * 공통 > 평가서식 목록 조회(팝업)
 *
 * <pre>
 * comm 
 *    |_ popup
 *       |_ estmFrmPopupList.js
 * 
 * </pre>
 * @date : 2016. 10. 27.
 * @version : 1.0
 * @author : 은우소프트 손연우
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

	
	pageObj.clickPage = function(pageNo) {
		
		$("#" + defaultFrm + " #P_PAGE_NO").val(pageNo);
		pageObj.frmPopupList();
	};
	
	pageObj.frmPopupList = function() {
		FwkCmmnUtil.submitForm(defaultFrm, "/comm/popup/estmFrmPopupList.do");
	};
	
	/**  
	 * <pre>
	 * 1. 개요 : 부모창에 선택된 사용자 값 전달
	 * 2. 처리내용 : 
	 * 		부모창에 선택된 사용자 값 전달한다
	 *  	
	 * </pre>
	 * @Function Name : setEstmFrmInfo
	 */
	setEstmFrmInfo = function(ESTM_FRM_NO, ESTM_FRM_NM) {
		window.opener.estmFrmPopupAdd(ESTM_FRM_NO, ESTM_FRM_NM);
	   self.close();
	};
	
	/**
	 * 페이지내에서 필요한 항목의 이벤트를 바인딩한다.
	 * 1. 닫기버튼(#closeBtn) 의 click 이벤트를 binding 한다.
	 *   - 닫기버튼에 click 이벤트 발생시 팝업을 닫는다.
	 * 2. Form 안에서 필요한 필드에 대해 엔터키 이벤트를 바인딩한다.
	 *   - 엔터키 이벤트 발생시 pageObj.clickPage 함수를 호출한다.
	 */	
	pageObj.setEventHandler = function() {

		
		// 닫기버튼
		$("#colseBtn").on("click", function() {
			self.close();
			return false;
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