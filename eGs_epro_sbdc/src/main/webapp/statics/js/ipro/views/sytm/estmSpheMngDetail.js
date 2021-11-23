/**
 * 정보관리 > 평가분야관리 상세
 *
 * <pre>
 * sytm
 *    |_ estmSpheMngDetail.js
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
	var defaultFrm = "detailFrm";

	
	// 목록
	pageObj.estmSpheMngList = function() {
		FwkCmmnUtil.submitForm("listFrm", "/sytm/estmSpheMngList.do");
	};
	
	// 수정
	pageObj.estmSpheMngUpdtForm = function(){
		FwkCmmnUtil.submitForm(defaultFrm, "/sytm/estmSpheMngUpdtForm.do");
	};
	
	// 삭제
	pageObj.estmSpheMngDelete = function(){
		
		var jsonData = $("#detailFrm").serializeObject();
		var actionUrl = "/sytm/estmSpheMngDelete";

		FwkCmmnUtil.submitAjax (actionUrl, jsonData
				, function(res) {
			alert("삭제되었습니다.");
			pageObj.estmSpheMngList();
		});
		
	};
	
	pageObj.setEventHandler = function() {
		
		// 목록
		$("#listBtn").on("click", function() {
			pageObj.estmSpheMngList();
		});

		// 수정
		$("#updtBtn").on("click", function() {
			pageObj.estmSpheMngUpdtForm();
		});
	
		// 삭제
		$("#deleteBtn").on("click", function() {
			if(!confirm(FwkMssageUtil.getMessage("COM.CON.001", "삭제"))){
				return false;
			}
			pageObj.estmSpheMngDelete();
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