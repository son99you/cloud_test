(function() {

	/**
	 * Default Constructor
	 */
	pageObj = {};
	/**
	 * Default Form Name
	 */
	var defaultFrm = "searchFrm";
	
	// 페이지 이동
	movePage = function(url, resName, pscd) {
		$("#detailFrm input[name='resourceName']").val(resName);
		
		// 계약체결요청 /opro/cont/contProgrList.do
		if(resName == "OEP04001"){
			$("#detailFrm input[name='P_CONT_PSCD_S']").val(pscd);
			// 대금지급요청 /opro/prpy/contPrpyReqstList.do
		}else if(resName == "OEP08001"){
			$("#detailFrm input[name='P_PAY_STCD_S']").val(pscd);
		}
		 
		FwkCmmnUtil.submitForm("detailFrm", url); 
	};
	
	
	// 다운로드
	download = function(fileSn, fileGrpNo) {
		$("#downloadFrm input[name='P_FILE_SN']").val(fileSn);
		$("#downloadFrm input[name='P_FILE_GRP_NO']").val(fileGrpNo);
		FwkCmmnUtil.submitForm("downloadFrm", "/comm/download.do" );
	};
	
	downloadManual = function(){
		FwkCmmnUtil.submitForm("manualFrm", "/opro/comm/userManual.do");
	}
	
	notiList = function(bbsSecd){
		$("#detailFrm input[name='P_BBS_SECD']").val(bbsSecd);
		if(bbsSecd == "NOTI"){
			$("#detailFrm input[name='resourceName']").val("OEP12001");
			FwkCmmnUtil.submitForm("detailFrm", "/opro/noti/notiList.do");
		}else if(bbsSecd == "FAQ"){
			$("#detailFrm input[name='resourceName']").val("OEP12002");
			FwkCmmnUtil.submitForm("detailFrm", "/opro/noti/faqList.do");
		}
	}
	
	detailInqire = function(bbsSecd, bbsSn){
		$("#detailFrm input[name='P_BBS_SECD']").val(bbsSecd);
		$("#detailFrm input[name='P_BBS_SN']").val(bbsSn);
		if(bbsSecd == "NOTI"){
			$("#detailFrm input[name='resourceName']").val("OEP12001");
			FwkCmmnUtil.submitForm("detailFrm", "/opro/noti/notiDetail.do");
		}else if(bbsSecd == "FAQ"){
			$("#detailFrm input[name='resourceName']").val("OEP12002");
			FwkCmmnUtil.submitForm("detailFrm", "/opro/noti/faqDetail.do");
		}
	}
	
	pageObj.setEventHandler = function() {
		
		$("#loginBtn").on("click", function() {
			pageObj.popupBasic();
			return false;
		});
		
		// 조회버튼
		$("#searchBtn").on("click", function() {
			FwkCmmnUtil.submitForm(defaultFrm, "/opro/main/mainPage.do");
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