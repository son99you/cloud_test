(function() {

	/**
	 * Default Constructor
	 */
	pageObj = {};
	
	/**
	 * Default Form Name
	 */
	var defaultFrm = "registFrm";
	
	RAONKEDITOR_CreationComplete = function(editorId){
		var html = $("#P_BBS_CNTN").val();
		RAONKEDITOR.SetHtmlContents(html, 'editor1');
	};
	
	pageObj.prdtMngList = function(){
		FwkCmmnUtil.submitForm("listFrm", "/sytm/prdtMngList.do");
	};
	
	pageObj.setEventHandler = function() {
		
		//목록
		$("#listBtn").on("click", function() {
//			if(!confirm("목록으로 이동하시겠습니까?")){
//				return false;
//			}
			pageObj.prdtMngList();
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