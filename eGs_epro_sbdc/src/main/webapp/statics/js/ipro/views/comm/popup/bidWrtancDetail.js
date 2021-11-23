/**
 * 전자입찰 > 입찰공고문 상세 팝업
 *
 * <pre>
 * ebid
 *    |_ popup
 *           |_ bidWrtancDetail.js
 * 
 * </pre>
 * @date : 2017. 06. 19
 * @version : 1.0
 * @author : 은우소프트 전상훈
 */

(function() {

	/**
	 * Default Constructor
	 */
	pageObj = {};

	/**
	 * Default Form Name
	 */
	var defaultFrm = "updtFrm";

	pageObj.download = function(atchmnflSn){
		$("#downFrm input[name='P_ATCHMNFL_SN']").val(atchmnflSn);
		FwkCmmnUtil.submitForm("downFrm", "/comm/download.do");
	};
	
	/**
	 * 페이지내에서 필요한 항목의 이벤트를 바인딩한다.
	 * 1. 조회버튼(#searchBtn) 의 click 이벤트를 binding 한다.
	 *   - 조회버튼에 click 이벤트 발생시 입찰공고목록조회 함수를 호출한다.
	 * 2. 입찰종류(#select2) 콤보박스의 change 이벤트를 biding한다.
	 *   2.1 change 이벤트 발생시 changeSelect2 함수를 호출한다.
	 * 3. Form 안에서 필요한 필드에 대해 엔터키 이벤트를 바인딩한다.
	 *   - 엔터키 이벤트 발생시 pageObj.clickPage 함수를 호출한다.
	 */	
	pageObj.setEventHandler = function() {
		
		// 닫기버튼
		$("#closeBtn").on("click", function() {
			window.close();
		});
		
		
	};

	/**
	 * window load
	 *
	 */
	$(function(){
		pageObj.setEventHandler();
		fileView($("#P_FILE_GRP_NO").val());
	});
})();