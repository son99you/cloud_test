/**
 * 정보관리 > 평가분야관리 수정
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
	var defaultFrm = "updtFrm";

	
	// 목록
	pageObj.estmSpheMngList = function() {
		FwkCmmnUtil.submitForm("listFrm", "/sytm/estmSpheMngList.do");
	};
	
	// 상세
	pageObj.estmSpheMngDetail = function() {
		FwkCmmnUtil.submitForm(defaultFrm, "/sytm/estmSpheMngDetail.do");
	};
	
	// 수정
	pageObj.estmSpheMngUpdt = function(){
		
		var jsonData = $("#updtFrm").serializeObject();
		var actionUrl = "/sytm/estmSpheMngUpdt";

		FwkCmmnUtil.submitAjax (actionUrl, jsonData
				, function(res) {
			alert("저장되었습니다.");
			pageObj.estmSpheMngDetail();
		});
		
	};
	
	
	
	pageObj.setEventHandler = function() {
		
		// 목록
		$("#listBtn").on("click", function() {
			pageObj.estmSpheMngList();
		});
		
		// 취소버튼
		$("#cnclBtn").on("click", function() {
			if(!confirm("취소되었습니다.")){ 
				return false;
			}
			pageObj.estmSpheMngDetail();
		});
		
		// 저장
		$("#saveBtn").on("click", function() {
			
			// 필수체크
			if($("#P_ESTM_SPHE_SENM").val() == ""){
				alert("[평가분야구분명] 항목은 필수입력 입니다.");
				$("#P_ESTM_SPHE_SENM").focus();
				return false;
			}
			
			pageObj.estmSpheMngUpdt();
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