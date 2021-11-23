/**
 * <pre>
 * 공통 > 평가대상정보 첨부파일 수정 팝업
 *
 * <pre>
 * comm 
 *  |_popup
 *   |_ estmVidoFileUpdtForm.js
 * 
 * </pre>
 * @date : 2021. 04. 02.
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
	
	// 수정
	pageObj.estmObjFileUpdt = function(){
		fileUploadStart();
	};
	
	
	// 파일 업로드
	fileUploadStart = function() {
		// 첨부파일이 존재하는 경우만 업로드
		if (RAONKUPLOAD.GetTotalFileCount("upload") == 0) {
			FwkCmmnUtil.submitForm("updtFrm", "/estm/estmVidoFileUpdt.do");
        } else {
            //업로드에서 추가된 파일이 정상으로 넘어오면 파일전송 이벤트를 발생시킵니다.
        	RAONKUPLOAD.Transfer("upload");
        }
	};
	
	
	pageObj.setEventHandler = function() {

		// 닫기버튼
		$("#closeBtn").on("click", function() {
			self.close();
		});
		
		// 수정 버튼
		$("#saveBtn").on("click", function() {
			//fileUploadStart();
			
			pageObj.estmObjFileUpdt();
		});
	};

	/** 
	 * window load
	 *
	 */
	$(function(){
		pageObj.setEventHandler();
		
		fileModify("updtFrm", "P_FILE_GRP_NO", "P_FILE_GRP_NO_NEW", "/estm/estmVidoFileUpdt.do", "updtFrm", "/comm/popup/estmVidoFileView.do", "");
		
	});
})();