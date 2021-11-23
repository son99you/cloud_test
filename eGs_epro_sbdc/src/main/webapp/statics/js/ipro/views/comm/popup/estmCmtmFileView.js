/**
 * <pre>
 * 공통 > 평가위원첨부 상세 팝업
 *
 * <pre>
 * comm 
 *  |_popup
 *   |_ estmCmtmFileView.js
 * 
 * </pre>
 * @date : 2021. 05. 06.
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

		// 닫기버튼
		$("#closeBtn").on("click", function() {
			self.close();		
		});
	};

	/** 
	 * window load
	 *
	 */
	$(function(){
		pageObj.setEventHandler();
		
		fileView($("#P_FILE_GRP_NO").val());
	});
})();