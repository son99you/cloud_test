/**
 * 정보관리 > 평가분야관리 등록
 *
 * <pre>
 * sytm
 *    |_ estmSpheMngRegistForm.js
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
	var defaultFrm = "registFrm";

	
	// 목록
	pageObj.estmSpheMngList = function() {
		FwkCmmnUtil.submitForm(defaultFrm, "/sytm/estmSpheMngList.do");
	};

	// 상세
	pageObj.estmSpheMngDetail = function() {
		FwkCmmnUtil.submitForm("detailFrm", "/sytm/estmSpheMngDetail.do");
	};
	
	
	// 저장
	pageObj.estmSpheMngRegist = function(){
		
		var jsonData = $("#registFrm").serializeObject();
		var actionUrl = "/sytm/estmSpheMngRegist";

		FwkCmmnUtil.submitAjax (actionUrl, jsonData
				, function(res) {
			pageObj.estmSpheMngList();
		});
		
	};
	
	pageObj.setEventHandler = function() {
		
		// 목록
		$("#listBtn").on("click", function() {
			pageObj.estmSpheMngList();
		});
		
		// 저장
		$("#saveBtn").on("click", function() {
			
			// 필수체크
			if($("#P_ESTM_SPHE_SENM").val() == ""){
				alert("[평가분야구분명] 항목은 필수입력 입니다.");
				$("#P_ESTM_SPHE_SENM").focus();
				return false;
			}
			
			if(!confirm("저장하시겠습니까?")){
				return false;
			}
			
			pageObj.estmSpheMngRegist();
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