(function() {

	/**
	 * Default Constructor
	 */
	pageObj = {};
	
	fnDetailView = function(dyyyy, evSeq, orgId, vendorCode, sgCode, deptCode ,groupNo){
		$("#detailFrm input[name='dyyyy']").val(dyyyy);
		$("#detailFrm input[name='ev_seq']").val(evSeq);
		$("#detailFrm input[name='orgId']").val(orgId);
		$("#detailFrm input[name='vendor_code']").val(vendorCode);
		$("#detailFrm input[name='sg_code']").val(sgCode);
		$("#detailFrm input[name='dept_code']").val(deptCode);
		$("#detailFrm input[name='P_ATCHMNFL_GROUP_NO']").val(groupNo);
		FwkCmmnUtil.submitForm("detailFrm", "/vend/vendEvalDeptEvalView.do");  
	};
	
	pageObj.setEventHandler = function() {
		/**
		 * 1. 목록버튼 클릭 이벤트
		 */
		$("#listBtn").on("click", function() {
			FwkCmmnUtil.submitForm("detailFrm", "/vend/vendEvalDeptList.do");  
		});
		/**
		 * 1. 삭제버튼 클릭 이벤트
		 */
		$("#deleteBtn").on("click", function() {
			if(confirm("삭제하시겠습니까?")){
				movePage("/vend/vendEvalDeptList.do");
				return false;
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