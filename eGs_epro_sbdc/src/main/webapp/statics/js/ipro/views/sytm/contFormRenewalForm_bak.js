(function() {

	/**
	 * Default Constructor
	 */
	pageObj = {};

	var defaultFrm = "modiFrm";
	var myEditor;
	
	
	pageObj.contFormList = function() {
		FwkCmmnUtil.submitForm("menuMoveFrm", "/sytm/contFormList.do");
	};
	
	
	dext_editor_loaded_event = function() {
        var html = $("#P_FRM_CNTN").val();
        // id가 editor1인 에디터 디자인 영역에 body 태그 내부 소스를 입력합니다.
        DEXT5.setBodyValue(html, 'editor1');
    };
	
	
	pageObj.renewalVerup = function() {
		
		//editor 내용 가져오기
		//document.getElementById("P_FRM_CNTN").value = myEditor.getContent();
		$("#P_FRM_CNTN").val(DEXT5.getBodyValue('editor1'));
		
		var jsonData = $("#modiFrm").serializeObject();  
		var actionUrl = "/sytm/contFormRenewal";  
 
		 FwkCmmnUtil.submitAjax (actionUrl, jsonData
		 	, function(res) {	 
			 FwkCmmnUtil.submitForm("detailFrm", "/sytm/contFormList.do");    
		 });
	
	}; 
	 
	pageObj.setEventHandler = function() {
		
		// 취소
		$("#listBtn").on("click", function() {
			pageObj.contFormList();
		});
		
		
		// 저장
		$("#updtBtn").on("click", function() {
			if(!confirm("계약서식을 갱신하시겠습니까?")){
				return false; 
			}
			pageObj.renewalVerup();
		});
		
	};

	/**
	 * window load
	 *
	 */
	$(function() {		
		pageObj.setEventHandler();
		//dext_editor_loaded_event();
	});
	
})();