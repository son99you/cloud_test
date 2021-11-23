(function() {

	/**
	 * Default Constructor
	 */
	pageObj = {};

	var defaultFrm = "registFrm";
	
	pageObj.bakFileSave = function() { 
		FwkCmmnUtil.submitForm("registFrm", "/opro/user/bakFileSave.do");
	}; 
	
	fileSet = function(obj){
		var fileInfo = fileInfoSet(obj);
		var node = $(obj).parent().parent();
		if($(obj).val() != ""){
			node.find("input[type=text]").empty().val(fileInfo[0]);
			node.find("input[type=file]").prop("name","P_FILE");
		};
	};
	
	pageObj.setEventHandler = function() {
		$("#saveBtn").on("click", function() {
			
			if(!confirm(FwkMssageUtil.getMessage("COM.CON.001", "저장"))){
				return false;
			}
			pageObj.bakFileSave(); 
		});
		
	};

	/**
	 * window load
	 *
	 */
	$(function() {		
		pageObj.setEventHandler();
	});
})();