/**
 * 평가관리 > 평가위원관리 > 평가위원POOL현황 목록
 *
 * <pre>
 * estm
 *    |_ cmtmMngPoolList.js
 * 
 * </pre>
 * @date : 2021. 03. 23.
 * @version : 1.0
 * @author : 은우소프트 
 */

(function() {
	
	/**
	 * Default Constructor
	 */
	pageObj = {};

	var defaultFrm = "searchFrm";
	
	pageObj.registForm = function() {
		FwkCmmnUtil.submitForm(defaultFrm, "/estm/cmtmMngPoolRegistForm.do");
	};

	detailInqire = function(estmCmtmNo){ 
		$("#detailFrm input[name='P_ESTM_CMTM_NO']").val(estmCmtmNo);
		FwkCmmnUtil.submitForm("detailFrm", "/estm/cmtmMngPoolDetail.do");
	};	
	  

	pageObj.cmtmMngPoolList = function() {
		FwkCmmnUtil.submitForm(defaultFrm, "/estm/cmtmMngPoolList.do"); 
	};
	
	
	// 엑셀 양식 다운로드
	pageObj.excelStyleDwld = function(){
		if(confirm("엑셀양식을 다운받으겠습니까?") == true){
			FwkCmmnUtil.submitForm(defaultFrm, "/estm/cmtmMngPoolExcelFrmDwld.do");
		}else{
			return false;
		}
	};
	

	// 엑셀업로드 등록
	excelUploadRegist = function() {
		
		if($("#excelFileUpload").val()==""){
			alert("파일을 선택해주세요."); 
			return false;
		}
		
		if($("#excelFileUpload").val() !=""){
			
			var extName = $("#excelFileUpload").val().substring($("#excelFileUpload").val().lastIndexOf(".")+1).toUpperCase();   // 업로드한 파일의 확장자
			
			if(extName != "xlsx" && extName != "XLSX" && extName != "xls" && extName != "XLS"){
				alert("엑셀파일 형식이 아닙니다.");
				return false;
			}
			
			if(confirm("엑셀업로드를 하시겠니까?") == true){
				FwkCmmnUtil.submitForm("excelUploadFrm", "/estm/cmtmMngPoolExcelUpld.do");
			}else{ 
				return false;   
			} 
		}
	};
	
	pageObj.clickPage = function(pageNo) { 
		$("#" + defaultFrm + " #P_PAGE_NO").val(pageNo);
		pageObj.cmtmMngPoolList(); 
	};
	
	
	/**
	 * 페이지내에서 필요한 항목의 이벤트를 바인딩한다.
	 * 1. 목록버튼(#listBtn) 의 click 이벤트를 binding 한다.
	 */	
	pageObj.setEventHandler = function() {
		
		// 수기등록 버튼
		$("#registBtn").on("click", function() {
			pageObj.registForm();
		});
		
		// 조회버튼
		$("#searchBtn").on("click", function() {
			$("#P_PAGE_NO").val("1");
			$("#P_PAGE_SIZE").val("10");
			pageObj.cmtmMngPoolList();
		});
		
		// 엑셀양식다운로드
		$("#excelStyleDwldBtn").click(function () {
			pageObj.excelStyleDwld();
        });
		
		// 엑셀 업로드
		$("#excelStyleUpldBtn").on("click", function(){
			$("#excelFileUpload").val("");  
			$("input[name='excelFileUpload']").click();	 
		});
		
		
		// 1. Form 안에서 필요한 Fields 에 엔터키 이벤트 핸들러를 등록한다.
		FwkCmmnUtil.setEnterKeyBinding(defaultFrm, ["P_ESTM_CMTM_NM_S","P_INO_CMTM_SECD_S","P_HNDW_REG_YN_S","P_LLF_SECD_S","P_CNTN_SECD_S","P_MLF_SECD_S","P_SLF_SECD_S"], function() {
			// EnteKey 이벤트 발생시 현재페이지 다시 조회를 한다.
			pageObj.clickPage(1);
		});
		
	};

	 
	/**
	 * window load
	 *
	 */
	$(function() {		
		pageObj.setEventHandler();
		
		// 엑셀다운로드 공통
		excelDownSetting("searchFrm");
		
		var excelResultCode = $("#excelResultCode").val();
		
		if(excelResultCode == "fail"){
			alert("데이터 형식에 맞지 않는 데이터가 있습니다.");
			FwkCmmnUtil.submitForm("searchFrm", "/estm/cmtmMngPoolList.do");
		}else if(excelResultCode == "Success"){
			FwkCmmnUtil.submitForm("searchFrm", "/estm/cmtmMngPoolList.do");
		}

	});
})();