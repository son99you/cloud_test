(function() {

	/**
	 * Default Constructor
	 */
	pageObj = {};
	
	
	movePage = function(url) {
		FwkCmmnUtil.submitForm("listFrm", url);  
	};
	
	/**  
	 * <pre>
	 * 1. 개요 : 파일 다운로드
	 * 2. 처리내용 : 
	 * 		공고게시
	 *  	
	 * </pre>
	 */
	download = function(fileGrpNo, fileSn) {
		$("#downloadFrm input[name='P_FILE_SN']").val(fileSn);
		$("#downloadFrm input[name='P_FILE_GRP_NO']").val(fileGrpNo);
		FwkCmmnUtil.submitForm("downloadFrm", "/comm/download.do" );
	};
	
	pageObj.setEventHandler = function() {
		
		
		/**
		 * 라이센스발급 클릭 이벤트
		 */
		$("#licenseBtn").on("click", function() {
			
			if(!confirm("라이센스를 발급 하시겠습니까?")){
				return false;
			}else {
				FwkCmmnUtil.submitForm("licenseFrm" , "/sytm/licenseIssue.do");
			}
			
			return false;
		});
		
		
		/**
		 * 삭제버튼 클릭 이벤트
		 */
		$("#delBtn").on("click", function() {
			
			if(!confirm("삭제하시겠습니까?")){
				return false;
			}else {
				FwkCmmnUtil.submitForm("updtFrm" , "/sytm/licenseDelete.do");
			}
			
			return false;
		});
		
		/**
		 * 수정버튼 클릭 이벤트
		 */
		$("#updateBtn").on("click", function() {
			
			if(!confirm("수정화면으로 이동하시겠습니까?")){
				return false;
			}else {
				FwkCmmnUtil.submitForm("updtFrm" , "/sytm/licenseUpdtForm.do");
			}
			
			
			return false;
		});
		
		
		/**
		 * 목록버튼 클릭 이벤트
		 */
		$("#listBtn").on("click", function() {
			FwkCmmnUtil.submitForm("listFrm" , "/sytm/licenseList.do");
			return false;
		});
		
	};

	/**
	 * window load
	 *
	 */
	$(function() {
		
		if($("#resultCode").val() == 'Success'){
			FwkCmmnUtil.submitForm("detailFrm", "/sytm/licenseDetail.do");
		}
		pageObj.setEventHandler();
		
	});
	
})();