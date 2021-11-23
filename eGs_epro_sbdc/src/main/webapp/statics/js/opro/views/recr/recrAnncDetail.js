/**
 * 알림마당 > 일반공지사항 상세
 *
 * <pre>
 * noti
 *    |_ notiDetail.js
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
	
	var defaultFrm = "searchFrm";
	
	
	
	pageObj.recrAnncList = function() {
		FwkCmmnUtil.submitForm("listFrm", "/opro/recr/recrAnncList.do");
	};
	
	//크리에이터 신청 (2)
	pageObj.recrAnncRegForm =function(recrNo) {
		$("#detailFrm input[name='P_ESTM_NO']").val(recrNo);
		FwkCmmnUtil.submitForm("regFrm", "/opro/recr/recrAnncRegForm.do");
	};
	
	//업체 신청(2)
	pageObj.recrAnncEstmRegForm = function(recrEtNo){
		$("#detailFrm input[name='P_ESTM_NO']").val(recrEtNo);
		FwkCmmnUtil.submitForm("regFrm", "/opro/recr/recrAnncEstmRegForm.do");
	};
	
	
	// 파일 업로드
	fileUploadStart = function() {
		RAONKUPLOAD.Transfer("uploadView1");
	};	
	
	/**
	 * 페이지내에서 필요한 항목의 이벤트를 바인딩한다.
	 * 1. 목록버튼(#listBtn) 의 click 이벤트를 binding 한다.
	 */	
	pageObj.setEventHandler = function() {
		
		// 목록버튼
		$("#listBtn").on("click", function() {
			pageObj.recrAnncList();
		});
		
		// 크리에이터  참여 신청 (1)
		$("#regBtn").on("click", function(){
			pageObj.recrAnncRegForm();
		});
		
		//업체 참여신청 버튼(2)
		$("#regEstmBtn").on("click", function() {
			pageObj.recrAnncEstmRegForm();
		});
		
	};

	/**
	 * window load
	 *
	 */ 
	$(function(){
		pageObj.setEventHandler();
		
		fileView($("#P_FILE_GRP_NO").val());
		
		if($("#resultCode").val() == 'Success'){
			var recrNo = $("#P_ESTM_NO_TRANS").val();
			$("#detailFrm input[name = 'P_ESTM_NO_TRANS']").val(recrNo);
			FwkCmmnUtil.submitForm("detailFrm", "/estm/recrAnncDetail.do");
		}
	});
})();