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
	
	var defaultFrm = "regFrm";
	
	pageObj.recrAnncDetail = function() {
		FwkCmmnUtil.submitForm("detailFrm", "/opro/recr/recrAnncDetail.do");
	};
	
	
	pageObj.recrAnncReg =function() {
//		$("#regFrm input[name='CRTR_NO']")./opro/val(recrNo);
		FwkCmmnUtil.submitForm("regFrm ", "recr/recrAnncEstmReg.do");
//		();
	};
	
	pageObj.clickPage = function(pageNo){
		$("#" + defaultFrm + "#P_PAGE_NO").val(pageNo);
		pageObj.recrAnncReg
		alert("이미 신청된 정보가 있습니다.");
		return false;
	}
	
	//파일 업로드
	 fileUploadStart = function(){
		 //첨부파일이 존재하는 경우만 업로드 
		 if(RAONKUPLOAD.GetTotalFileCount("upload") == 0){
			 FwkCmmnUtil.submitForm("regFrm", "/opro/recr/recrAnncEstmReg.do");
				alert("참여신청이 완료되었습니다");
		 }else{
			 //업로드에서 추가된 파일이 정상으로 넘어오면 파일전송 이벤트를 발생시킵니다. 
			 RAONKUPLOAD.Transfer("upload");
			 alert("참여신청이 완료되었습니다");
		 }
	 };
	/**
	 * 페이지내에서 필요한 항목의 이벤트를 바인딩한다.
	 * 1. 목록버튼(#listBtn) 의 click 이벤트를 binding 한다.
	 */
	 
	pageObj.setEventHandler = function() {
		
		// 이전버튼
		$("#beforPageBtn").on("click", function() {
			pageObj.recrAnncDetail();
		});
		
		// 참여신청버튼
		$("#saveBtn").on("click", function() {
			
			/*
			 	필수 체크
			 */
			
			if($("#P_BIZRNO").val() == ""  ){
				alert("[사업자등록번호] 항목은 필수입력 입니다.");
				$("#P_BIZRNO").focus();
				return false;
			}
			
			if($("#P_VEND_NM").val() == ""  ){
				alert("[업체명] 항목은 필수입력 입니다.");
				$("#P_VEND_NM").focus();
				return false;
			}
			
			if($("#P_ITEM_NM").val() == ""  ){
				alert("[상품명] 항목은 필수입력 입니다.");
				$("#P_ITEM_NM").focus();
				return false;
			}
			
			if($("#P_EMAL").val() == ""){
				alert("[이메일] 항목은 필수입력 입니다.");
				$("#P_EMAL").focus();
				return false;
			}
	
			
			if($("#P_ESTM_OBJ_PE_NM").val() == ""){
				alert("[성명] 항목은 필수입력 입니다.");
				$("#P_ESTM_OBJ_PE_NM").focus();
				return false;
			}
			
			if($("#P_TEL_NO").val() == ""){
				alert("[전화번호 ] 항목은 필수입력 입니다.");
				$("#P_TEL_NO").focus();
				return false;
			}
			
			/*
				중복체크
				생년월일, 성명, 전화번호로 인당 1회만 신청가능하도록 설정함 
			
			if($("#P_RSDN_NO_1").val() == '850307' && 
				$("#P_RSDN_NO_2").val() == '1' &&
				$("#P_TEL_NO").val() == '01012122323' && 
				$("#P_CRTR_NM").val() == '강건강' ){
				alert("이미 신청된 정보가 있습니다.");
				return false;
			}
			*/
			if(!confirm("신청하시겠습니까?")){
				return false;
			}
			fileUploadStart();
//			pageObj.recrAnncReg();
		});
		
		
		// 1. Form 안에서 필요한 Fields 에 엔터키 이벤트 핸들러를 등록한다.
		FwkCmmnUtil.setEnterKeyBinding(defaultFrm, ["P_TTL_NM_S","P_REGR_NM_S"], function() {
			// EnteKey 이벤트 발생시 현재페이지 다시 조회를 한다.
			pageObj.clickPage(1);
		});
		
	};

	/**
	 * window load
	 *
	 */ 
	$(function(){
		pageObj.setEventHandler();
		
//		if($("#resultCode").val() == "Success"){
//			var estmSecd = $("#P_ESTM_SECD_TRANS").val();
//			
//			$("#P_ESTM_SECD").val(estmSecd);
//			$("#resultCode").val("");
//			FwkCmmnUtil.submitForm("detailFrm", "/opro/recr/recrAnncDetail.do");
//		}
		
		fileUpload("regFrm", "P_FILE_GRP_NO", "/opro/recr/recrAnncEstmReg.do", "detailFrm", "/opro/recr/recrAnncDetail.do", "");
		//		fileUpload("regFrm", "P_FILE_GRP_NO", "/opro/recr/recrAnncReg.do");
//		fileView($("#P_FILE_GRP_NO").val());
	});
})();