/**
 * 메인 > 평가공고 신청
 *
 * <pre>
 * recr 
 *    |_ recrAnncRegForm.js
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
	
	var defaultFrm = "regFrm";
	
	//목록
	pageObj.recrAnncList = function(){
		FwkCmmuUtil.submitForm("detailFrm", "/opro/recr/recrAnncList.do");
	};
	
	
	pageObj.recrAnncDetail = function() {
		FwkCmmnUtil.submitForm("detailFrm", "/opro/recr/recrAnncDetail.do");
	};
	
	
	/*pageObj.recrAnncReg =function(recrNo) {
		$("#regFrm input[name='CRTR_NO']").val(recrNo);
		fileUploadStartReg();
	};*/
	
	pageObj.recrAnncReg =function() {
		fileUploadStartReg();
	};
	
	pageObj.clickPage = function(pageNo){
		$("#" + defaultFrm + "#P_PAGE_NO").val(pageNo);
		pageObj.recrAnncReg();
	}
	
	
	//파일 수정 폼 
	pageObj.recrUpdt = function() {
		fileUploadStart();
		//FwkCmmnUtil.submitForm("regFrm", "/opro/recr/recrUpdt.do");
	};
	
	// 파일 업로드(수정)
	fileUploadStart = function() {

		 //업로드에서 추가된 파일이 정상으로 넘어오면 파일전송 이벤트를 발생시킵니다. 
		 RAONKUPLOAD.Transfer("upload");
	};	
	
	//파일 업로드(작성)
	 fileUploadStartReg = function(){
		 //첨부파일이 존재하는 경우만 업로드 
		 if(RAONKUPLOAD.GetTotalFileCount("upload") == 0){
			 FwkCmmnUtil.submitForm("regFrm", "/opro/recr/recrAnncReg.do");
			 //alert("참여신청이 완료되었습니다.");
		 }else{
			 //업로드에서 추가된 파일이 정상으로 넘어오면 파일전송 이벤트를 발생시킵니다. 
			 RAONKUPLOAD.Transfer("upload");
			 //FwkCmmnUtil.submitForm("regFrm", "/opro/recr/recrUpdt.do");
			 //alert("참여신청이 완료되었습니다.");
		 }
	 };
	/**
	 * 페이지내에서 필요한 항목의 이벤트를 바인딩한다.
	 * 1. 목록버튼(#listBtn) 의 click 이벤트를 binding 한다.
	 */
	 
	 
	pageObj.setEventHandler = function() {
		
	/*	 중복체크
			생년월일, 전화번호로 인당 1회만 신청가능하도록 설정함 */
	$("#checkBtn").on("click", function(){
		/*
		 * 필수입력  
		 */
		if($("#P_RSDN_NO_1").val() == ""  ){
			alert("[생년월일] 항목은 필수입력 입니다.");
			$("#P_RSDN_NO_1").focus();
			return false;
		}
		
		if($("#P_RSDN_NO_2").val() == ""  ){
			alert("[생년월일] 항목은 필수입력 입니다.");
			$("#P_RSDN_NO_2").focus();
			return false;
		}	
		
		if($("#P_TEL_NO").val() == ""){
			alert("[전화번호] 항목은 필수입력 입니다.");
			$("#P_TEL_NO").focus();
			return false;
		}
		
		var jsonData = $("#regFrm").serializeObject();
		var actionUrl = "/opro/recr/recrAnncCheckReg.do"
			
			FwkCmmnUtil.submitAjax (actionUrl, jsonData, function(res){
			if(res.res == 1){
				alert("이미 신청된 정보가 있습니다. 수정화면으로 넘어갑니다.");
				
				//alert("res ==> " + res.estmObjPeNo);
				//alert("ESTM_OBJ_SEQ ==> " + res.ESTM_OBJ_SEQ);
				$("#P_ESTM_OBJ_SEQ").val(res.ESTM_OBJ_SEQ); //ESTM_OBJ_SEQ ( 7 ) 값을 폼안에 넣어주는 역할
				$("#P_CRTR_NO").val(res.estmObjPeNo);
				$("#P_ESTM_PSCD").val(res.recrGnrlDetail);
			
				FwkCmmnUtil.submitForm("regFrm", "/opro/recr/recrAnncRegForm.do");
			}else if (res.res == 0){
				alert("신청 가능한 크리에이터 입니다.");
				$("#regFrm input[name='P_RECR_CHECK']").val("Y");
				return false;
			}
		});
	 });
		
		// 이전버튼
		$("#beforPageBtn").on("click", function() {
			pageObj.recrAnncDetail();
		});
		
		// 참여신청버튼
		$("#saveBtn").on("click", function() {
			
			 var regexp = /^[0-9]*$/
			
			 var cout = $("#P_SSCRT_CNT").val();
			
			 var tel = $("#P_TEL_NO").val();
			 
			 if(! regexp.test(tel)){
				 alert("[전화번호] 항목은 숫자만 입력 가능합니다. ");
				 $("#P_TEL_NO").focus();
				 return false;
			 }
			
			 if(! regexp.test(cout)){
				 alert("[구독자수] 항목은 숫자만 입력 가능합니다. ");
				 $("#P_SSCRT_CNT").focus();
				 return false;
			 }
			 
			/*
			 	필수 체크
			 */
			if($("#P_RSDN_NO_1").val() == ""  ){
				alert("[생년월일] 항목은 필수입력 입니다.");
				$("#P_RSDN_NO_1").focus();
				return false;
			}
			
			if($("#P_RSDN_NO_2").val() == ""  ){
				alert("[생년월일] 항목은 필수입력 입니다.");
				$("#P_RSDN_NO_2").focus();
				return false;
			}
			
			if($("#P_TEL_NO").val() == ""){
				alert("[전화번호] 항목은 필수입력 입니다.");
				$("#P_TEL_NO").focus();
				return false;
			}
			
			if($("#P_CRTR_NM").val() == ""  ){
				alert("[이름] 항목은 필수입력 입니다.");
				$("#P_CRTR_NM").focus();
				return false;
			}
	
			if($("#P_EMAL").val() == ""){
				alert("[이메일] 항목은 필수입력 입니다.");
				$("#P_EMAL").focus();
				return false;
			}
			
			if($("#P_CHNL_NM").val() == ""){
				alert("[채널명] 항목은 필수입력 입니다.");
				$("#P_CHNL_NM").focus();
				return false;
			}
			
			
			if($("#P_SSCRT_CNT").val() == ""  ){
				alert("[구독자수] 항목은 필수입력 입니다.");
				$("#P_SSCRT_CNT").focus();
				return false;
			}
			
			
			if($("#P_CHNL_ADDR").val() == ""){
				alert("[채널주소] 항목은 필수입력 입니다.");
				$("#P_CHNL_ADDR").focus();
				return false;
			}
			
			if($("#P_RECR_CHECK").val()  != "Y"){
				alert("[중복체크] 항목은 필수입력 입니다.");
				return false;
			}
			
			if(!confirm("신청하시겠습니까?")){
				return false;
			}
			
			pageObj.recrAnncReg();
		});
		
		// 참여수정 버튼 
		$("#upateBtn").on("click", function(){
			
		 var regexp = /^[0-9]*$/
				
		var cout = $("#P_SSCRT_CNT").val();
		
		 var tel = $("#P_TEL_NO").val();
		 
		 if(! regexp.test(cout)){
			 alert("[구독자수] 항목은 숫자만 입력 가능합니다. ");
			 $("#P_SSCRT_CNT").focus();
			 return false;
		 }
		 
		 if(! regexp.test(tel)){
			 alert("[전화번호] 항목은 숫자만 입력 가능합니다. ");
			 $("#P_TEL_NO").focus();
			 return false;
		 }
		 
		/* 
		  	필수 체크 
		 */
		 if($("#P_CRTR_NM").val() == ""  ){
			alert("[이름] 항목은 필수입력 입니다.");
			$("#P_CRTR_NM").focus();
			return false;
		}

		if($("#P_EMAL").val() == ""){
			alert("[이메일] 항목은 필수입력 입니다.");
			$("#P_EMAL").focus();
			return false;
		}
		
		if($("#P_CHNL_NM").val() == ""){
			alert("[채널명] 항목은 필수입력 입니다.");
			$("#P_CHNL_NM").focus();
			return false;
		}
		
		if($("#P_SSCRT_CNT").val() == ""  ){
			alert("[구독자수] 항목은 필수입력 입니다.");
			$("#P_SSCRT_CNT").focus();
			return false;
		}
		
		if($("#P_CHNL_ADDR").val() == ""){
			alert("[채널주소] 항목은 필수입력 입니다.");
			$("#P_CHNL_ADDR").focus();
			return false;
		}
		
		if(!confirm("수정하시겠습니까?")){
			return false;
		}
		
		pageObj.recrUpdt();
		
		});
		// 1. Form 안에서 필요한 Fields 에 엔터키 이벤트 핸들러를 등록한다.
		FwkCmmnUtil.setEnterKeyBinding(defaultFrm, ["P_TTL_NM_S","P_REGR_NM_S"], function() {
			// EnteKey 이벤트 발생시 현재페이지 다시 조회를 한다.
			pageObj.clickPage(1);
		});
		
		//중복체크 이후 생년월일, 전화번호 수정했을경우 다시 중복체크 하도록.
		$("#P_RSDN_NO_1, #P_RSDN_NO_2, #P_TEL_NO").on("change", function(){
			$("#P_RECR_CHECK").val("N");
		});
	};

	/**
	 * window load
	 *
	 */ 
	$(function(){
		pageObj.setEventHandler();

		if($("#P_ESTM_OBJ_SEQ").val() == ""){
			fileUpload("regFrm", "P_FILE_GRP_NO", "/opro/recr/recrAnncReg.do");
		}
		
		if($("#P_ESTM_OBJ_SEQ").val() != ""){
			fileModify("regFrm", "P_FILE_GRP_NO", "P_FILE_GRP_NO_NEW", "/opro/recr/recrUpdt.do");
			/*fileModify("regFrm", "P_FILE_GRP_NO", "/opro/recr/recrUpdt.do");*/
		}
		
	});
})();