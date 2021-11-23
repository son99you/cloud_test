(function() {

	/**
	 * Default Constructor
	 */
	pageObj = {};
	
	
	pageObj.dsgnRecmFile = function() {
		FwkCmmnUtil.submitForm("listFrm", "/sytm/dsgnRecmFileList.do");
	};
	
	pageObj.dsgnRecmFileUpdtForm = function() {
		FwkCmmnUtil.submitForm("updtFrm", "/sytm/dsgnRecmFileUpdtForm.do");
	};
	
    
	 download = function(grpNo, sn){
		$("#downloadFrm input[name='P_FILE_GRP_NO']").val(grpNo);
		$("#downloadFrm input[name='P_FILE_SN']").val(sn);
		FwkCmmnUtil.submitForm("downloadFrm", "/comm/download.do" );
	 }

	
	pageObj.setEventHandler = function() {
		
		//  목록
		$("#listBtn").on("click", function() {
			pageObj.dsgnRecmFile();
		});

		
		// 수정
		$("#updtBtn").on("click", function() {
			pageObj.dsgnRecmFileUpdtForm();
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