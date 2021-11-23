/**
 * <pre>
 * 공통 > 평가대상 탭 - 평가대상정보 아임스타즈 연계 상세 팝업 : 상품정보
 *
 * <pre>
 * comm 
 *   |_ popup
 *     |_ estmObjImstarsPrdsView.js
 * 
 * </pre>
 * @date : 2021. 05. 31.
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
	// SPORT_REQST_FILEDETAILINFO
	imstarsDownload = function(fileId, fileSn) {
		$("#downloadFrm input[name='P_FILE_ID']").val(fileId);
		$("#downloadFrm input[name='P_FILE_SN']").val(fileSn);
		FwkCmmnUtil.submitForm("downloadFrm", "/comm/imstarsDownload.do" );
	};
	
	// 상품-서브 이미지 다운로드
	// CMMN_FILEDETAILINFO
	imstarsDetailDownload = function(fileId, fileSn) {
		$("#downloadFrm input[name='P_FILE_ID']").val(fileId);
		$("#downloadFrm input[name='P_FILE_SN']").val(fileSn);
		FwkCmmnUtil.submitForm("downloadFrm", "/comm/imstarsDetailDownload.do" );
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
	});
})();