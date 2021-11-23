/**
 * <pre>
 * 공통 > 평가대상정보 첨부파일 상세 팝업
 *
 * <pre>
 * comm 
 *  |_popup
 *   |_ estmObjFileView.js
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
	var defaultFrm = "detailFrm";
	
	// 첨부파일 수정 팝업
	pageObj.estmObjFileUpdtForm = function(){
		FwkCmmnUtil.submitForm("detailFrm", "/comm/popup/estmObjFileUpdtForm.do");
	};
	
	
	pageObj.setEventHandler = function() {

		// 닫기버튼
		$("#closeBtn").on("click", function() {
			self.close();		
		});
		
		// 수정 버튼
		$("#updtBtn").on("click", function() {
			pageObj.estmObjFileUpdtForm();		
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