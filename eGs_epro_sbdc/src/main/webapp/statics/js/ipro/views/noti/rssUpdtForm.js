/**
 * 알림마당 > 계약자료실 수정
 *
 * <pre>
 * noti
 *    |_rssUpdtForm.js
 * 
 * </pre>
 * @date : 2017.06.14
 * @version : 1.0
 * @author : 은우소프트 이주연
 */
(function() {
	
	/**
	 * Default Constructor
	 */
	pageObj = {};

	var defaultFrm = "updtFrm";
	

	pageObj.rssList = function() {
		FwkCmmnUtil.submitForm("listFrm", "/noti/rssList.do");  
	}; 
	
	pageObj.rssUpdt = function() {
		
/*		var jsonData = $("#" + defaultFrm).serializeObject();
		var actionUrl = "/noti/rssUpdt";

		FwkCmmnUtil.submitAjax(actionUrl, jsonData, function(res) {
			//alert("res ==> " + JSON.stringify(res));
			pageObj.fileUploadStart();
			//FwkCmmnUtil.submitForm("detailFrm", "/noti/rssDetail.do");
		});*/
		fileUploadStart();
		
	}; 
	
	
	// 파일 업로드
	fileUploadStart = function() {
		/*// 첨부파일이 존재하는 경우만 업로드
		if (DEXT5UPLOAD.GetTotalFileCount("upload") == 0) {
			FwkCmmnUtil.submitForm("detailFrm", "/noti/rssDetail.do");
        } else {
            //업로드에서 추가된 파일이 정상으로 넘어오면 파일전송 이벤트를 발생시킵니다. 
            DEXT5UPLOAD.Transfer("upload");
        }*/
		RAONKUPLOAD.Transfer("uploadView1");
	};	
	
	
	ppupYnEvent = function(obj) {
		
		if($(obj).prop("checked")){
			$(obj).val("Y");
		}else{
			$(obj).val("N");
		}
		
	};
	
	
	/**
	 * 페이지내에서 필요한 항목의 이벤트를 바인딩한다.
	 * 1. 목록버튼(#listBtn) 의 click 이벤트를 binding 한다.
	 */	
	pageObj.setEventHandler = function() {

		// 저장버튼
		$("#updtBtn").on("click", function() {
			pageObj.rssUpdt();
		});
		
		// 목록버튼 
		$("#listBtn").on("click", function() {
			pageObj.rssList(); 
		});
		
		// 팝업여부
		$("#P_PPUP_YN").on("click", function() {
			pageObj.ppupYnEvent(); 
		});
		
	};

	
	/**
	 * window load
	 * 
	 */
	$(function() {		 
		pageObj.setEventHandler();
		
		fileModify("updtFrm", "P_FILE_GRP_NO", "P_FILE_GRP_NO_NEW", "/noti/rssUpdt.do");
		//fileView($("#P_FILE_GRP_NO").val());
		//noViewFileModify("updtFrm", "P_FILE_GRP_NO", "P_ATCHMNFL_GROUP_NEW", "/noti/rssUpdt.do","detailFrm", "/noti/rssDetail.do");
	});
})();