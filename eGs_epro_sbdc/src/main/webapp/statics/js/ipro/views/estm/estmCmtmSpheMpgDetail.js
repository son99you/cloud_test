(function() {

	/**
	 * Default Constructor
	 */
	pageObj = {};
	
	
	pageObj.estmCmtmSpheMpgList = function() {
		FwkCmmnUtil.submitForm("listFrm", "/estm/estmCmtmSpheMpgList.do");
	};
	
	pageObj.estmCmtmSpheMpgUpdtForm = function() {
		FwkCmmnUtil.submitForm("updtFrm", "/estm/estmCmtmSpheMpgUpdtForm.do");
	};
	
	
    
	 RAONKEDITOR_CreationComplete = function(editorId) {
	 	var html = $("#P_FRM_CNTN").val();
	 	RAONKEDITOR.SetHtmlContents(html, editorId); 
	 };
	 
	 download = function(grpNo, sn){
		$("#downloadFrm input[name='P_FILE_GRP_NO']").val(grpNo);
		$("#downloadFrm input[name='P_FILE_SN']").val(sn);
		FwkCmmnUtil.submitForm("downloadFrm", "/comm/download.do" );
	 }

	ubiPopup = function() { 
		var jrf = $("#jrfNm").val(); 
		$("#ubiPopupFrm input[name='P_JRF']").val(jrf);
		
      $("#ubiPopupFrm").one("submit", function() {
			window.open("", "ubiPopup", "width=1020px,height=850px,toolbar=no,status=no,scrollbars=yes,resizeable=no,menubar=no,left=250,top=0");
			this.action = FwkCmmnUtil['contextPath']+'/ubi4/ubihtml.jsp';
			this.method = 'POST';
			this.target = 'ubiPopup'; 
	    }).trigger("submit");	 
	};
 

	contFormViewPopup = function(frmNo, vrsn, jrf){
		 $("#ubiPopupFrm input[name='P_JRF']").val(jrf); 
		
	      $("#ubiPopupFrm").one("submit", function() {
				window.open("", "ubiPopup", "width=1020px,height=850px,toolbar=no,status=no,scrollbars=yes,resizeable=no,menubar=no,left=250,top=0");
				this.action = FwkCmmnUtil['contextPath']+'/ubi4/ubihtml.jsp';
				this.method = 'POST';
				this.target = 'ubiPopup'; 
		    }).trigger("submit");
		
	};
	 
	contFormFile = function(fileGrpNo){
		$("#popupFrm input[name='P_FILE_GRP_NO']").val(fileGrpNo);
		
		 $("#popupFrm").one("submit", function() {
				window.open("", "filePopup", "width=750px,height=760px,toolbar=no,status=no,scrollbars=yes,resizeable=no,menubar=no,left=500,top=100");
				this.action = FwkCmmnUtil['contextPath']+'/comm/popup/contFormFileList.do';
				this.method = 'POST';
				this.target = 'filePopup';
			}).trigger("submit");
		
	};
	
	pageObj.setEventHandler = function() {
		
		//  목록
		$("#listBtn").on("click", function() {
			pageObj.estmCmtmSpheMpgList();
		});

		
		// 수정
		$("#updtBtn").on("click", function() {
			pageObj.estmCmtmSpheMpgUpdtForm();
		});
		

		// 갱신
		$("#renewalBtn").on("click", function() {
			pageObj.contFormRenewalForm();
		});
		
		//유비레포트
	   $("#ubiBtn").on("click", function() {
	      ubiPopup();
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