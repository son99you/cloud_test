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