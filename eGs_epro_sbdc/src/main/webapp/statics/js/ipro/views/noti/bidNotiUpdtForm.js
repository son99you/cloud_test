/**
 * 알림마당 > 입찰공지사항 수정
 *
 * <pre>
 * noti
 *    |_bidNotiUpdtForm.js
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
	

	pageObj.bidNotiList = function() {
		FwkCmmnUtil.submitForm("menuMoveFrm", "/noti/bidNotiList.do");  
	}; 
	
	pageObj.bidNotiUpdt = function() {
		
		var jsonData = $("#" + defaultFrm).serializeObject();
		var actionUrl = "/noti/bidNotiUpdt";

		FwkCmmnUtil.submitAjax(actionUrl, jsonData, function(res) {
			//alert("res ==> " + JSON.stringify(res));
			pageObj.fileUploadStart();
			//FwkCmmnUtil.submitForm("detailFrm", "/noti/bidNotiDetail.do");
		});
		
	}; 
	
	
	// 파일 업로드
	pageObj.fileUploadStart = function() {
		// 첨부파일이 존재하는 경우만 업로드
		DEXT5UPLOAD.Transfer("upload");
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
			pageObj.bidNotiUpdt();
		});
		
		// 목록버튼 
		$("#listBtn").on("click", function() {
			pageObj.bidNotiList(); 
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
		
		fileView($("#P_FILE_GRP_NO").val());
		noViewFileModify("updtFrm", "P_FILE_GRP_NO", "P_ATCHMNFL_GROUP_NEW", "/noti/bidNotiUpdt.do","detailFrm", "/noti/bidNotiDetail.do");
	});
})();