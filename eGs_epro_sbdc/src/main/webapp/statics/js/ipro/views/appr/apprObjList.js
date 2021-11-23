(function() {

	/**
	 * Default Constructor
	 */
	pageObj = {};
	
	var defaultFrm = "listFrm";
	
	
	// 목록
	pageObj.apprObjList = function() {
		FwkCmmnUtil.submitForm("listFrm", "/appr/apprObjList.do");
	};
	
	// 상세
	apprObjDetail = function(apprNo) {
		$("#detailFrm input[name='P_APPR_NO']").val(apprNo);
		FwkCmmnUtil.submitForm("detailFrm", "/appr/apprObjDetail.do");
	};
	

	
	pageObj.clickPage = function(pageNo) {
		$("#" + defaultFrm + " #P_PAGE_NO").val(pageNo);
		pageObj.apprObjList();
	};
	
	
	pageObj.setEventHandler = function() {
		
		//조회버튼
		$('#searchBtn').on('click', function() {
			pageObj.apprObjList();		
		});
		
		$('input').on("keyup", function(evnt){
			if(evnt.keyCode == 13) {
				pageObj.apprObjList();		
			}
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