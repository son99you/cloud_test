/**
 * 기준정보 > 사용자관리 상세 
 *  
 *   <pre>
 * sytm
 *    |_ userMgrDetail.js
 * 
 * </pre>
 * @date : 2017.06.15
 * @version : 1.0
 * @author : 은우소프트 이주연
 */

(function() {

	/**
	 * Default Constructor
	 */
	pageObj = {};
	
	/**
	 * Default Form Name
	 */
	var defaultFrm = "detailFrm";

	// 목록
	pageObj.userMgrList = function() {
		FwkCmmnUtil.submitForm("listFrm", "/sytm/userMgrList.do");
	};
	
	
	// 수정
	pageObj.userMgrUpdt = function() {
		
		//alert($("#P_AUTH_ID option:selected").text());
		
		$("#P_AFTER_AUTH_NM").val($("#P_AUTH_ID option:selected").text());
		
		var jsonData = $("#" + defaultFrm).serializeObject();
		var actionUrl = "/sytm/userMgrUpdt";

		 FwkCmmnUtil.submitAjax (actionUrl, jsonData
		 	, function(res) {
			 if(res.trans.P_SUCC =="SUCC"){
				 alert("저장이 완료되었습니다.");
				 $("#viewFrm input[name='P_USR_ID_S']").val(res.trans.P_USR_ID);
				 FwkCmmnUtil.submitForm("viewFrm", "/sytm/userMgrDetail.do");
			 }else{
			 }
		 });
	};
	
	
	/**
	 * 페이지내에서 필요한 항목의 이벤트를 바인딩한다.
	 * 1. 목록버튼(#listBtn) 의 click 이벤트를 binding 한다.
	 */	
	pageObj.setEventHandler = function() {

		// 목록 버튼
		$("#listBtn").on("click", function() {
			pageObj.userMgrList();
		});
		
		
		// 저장 버튼 
		$("#saveBtn").on("click", function() {
			
			if(!confirm(FwkMssageUtil.getMessage("COM.CON.001", "저장"))){
				return false;
			}
			
			pageObj.userMgrUpdt();
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