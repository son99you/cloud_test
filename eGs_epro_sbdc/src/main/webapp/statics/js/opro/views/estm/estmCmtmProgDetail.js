/**
 * 평가관리 > 평가진행현황 상세 - 기본정보
 *
 * <pre>
 * estm
 *    |_ estmCmtmProgDetail.js
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

	var defaultFrm = "detailFrm";

	// 목록
	pageObj.estmCmtmProgList = function() {
		FwkCmmnUtil.submitForm("listFrm", "/opro/estm/estmCmtmProgList.do"); 
	};
	
	// 다운로드
	pageObj.download = function(fileGrpNo, fileSn){
		$("#downFrm input[name='P_FILE_GRP_NO']").val(fileGrpNo);
		$("#downFrm input[name='P_FILE_SN']").val(fileSn);
		
		FwkCmmnUtil.submitForm("downFrm", "/comm/download.do");
	};
	
	// 공동인증서 전자서명
	pageObj.cmtmSign = function(){
		
		var jsonData = $("#signFrm").serializeObject(); 
        var actionUrl = "/opro/estm/estmCmtmSign";
        
        FwkCmmnUtil.submitAjax(actionUrl, jsonData, function(data) {
        	//alert("data.resultCode ==> " + data.resultCode);
        	if(data.resultCode != "success") {
        		alert("["+data.resultCode+"]"+data.msg);
    		} else {
    			FwkCmmnUtil.submitForm("detailFrm", "/opro/estm/estmCmtmProgDetail.do");
    		}
        });
        
	};
	
	//파일 수정버튼을 
	fileModBtn = function(obj){
		var td = $(obj).parent().parent();	//td
		td.hide();			//보여주는 td
		td.next().show();	// 다음td, 수정가능한 td
		td.next().find("input[type=file]").attr("name","P_FILE");
		td.next().find("input[name=P_FILE_DOC_SECD]").prop("disabled","");
	};
	
	//파일 취소버튼을 
	fileCancleBtn = function(obj){
		var td = $(obj).parent().parent();	//td
		td.hide();			//수정가능한 td 숨기기
		td.prev().show();	// 이전 td, 보여주는 td
		td.find("input[type=file]").prop("name","");
		td.find("input[name=P_FILE_DOC_SECD]").prop("disabled","disabled");
	};
	
	/**
	 * 페이지내에서 필요한 항목의 이벤트를 바인딩한다.
	 * 1. 목록버튼(#listBtn) 의 click 이벤트를 binding 한다.
	 */	
	pageObj.setEventHandler = function() {
		
		// 목록버튼
		$("#listBtn").on("click", function() {
			pageObj.estmCmtmProgList();
		});

		// 저장버튼
		$("#registBtn").on("click", function() {
			var flag = true;
			$("input[name='P_FILE']").each(function(inx){
				if(flag && $(this).val() == ""){
					$(this).focus();
					//return false;
					flag = false;
				}
			});
			
			if(!flag){
				alert("등록하지 않은 심사위원 첨부파일이 있습니다.");
				return false;
			}
			
			FwkCmmnUtil.submitForm("detailFrm", "/opro/estm/estmCmtmfileRegist.do"); 
		});

		// 수정버튼
		$("#updtBtn").on("click", function() {
			var flag = true;
			$("input[name='P_FILE']").each(function(inx){
				if(flag && $(this).val() == ""){
					$(this).focus();
					//return false;
					flag = false;
				}
			});
			
			if(!flag){
				alert("등록하지 않은 심사위원 첨부파일이 있습니다.");
				return false;
			}
			if ($("input[name='P_FILE']").length>0) {
				FwkCmmnUtil.submitForm("detailFrm", "/opro/estm/estmCmtmfileUpdt.do"); 
			}else{
				alert("수정된 심사위원 첨부파일이 없습니다.");
				return false;
			}
			
		});
		
		// 공동인증서 전자서명
		$("#assoSignBtn").on("click", function() {
			
			// 심사위원 첨부파일 등록여부 확인
			if($("#P_CMTM_FILE_GRP_NO").val() == ""){
				alert("심사위원 첨부파일 저장 후 전자서명이 가능합니다.");
				return false;
			} 
			
			// 전자서명 체크여부 확인
			if($("input:checkbox[name=confirmCheck]").is(":checked") == false) {
				alert("첨부파일확인 동의 후 전자서명이 가능합니다.");
				return false;
			}
			
			if(!confirm("전자서명하시겠습니까?")){
				return false;
			}
			
			cmtmSignAssoData_open();
		});
		
		// 브라우저인증서 전자서명
		$("#browserSignBtn").on("click", function() {
			
			// 심사위원 첨부파일 등록여부 확인
			if($("#P_CMTM_FILE_GRP_NO").val() == ""){
				alert("심사위원 첨부파일 저장 후 전자서명이 가능합니다.");
				return false;
			} 
			
			// 전자서명 체크여부 확인
			if($("input:checkbox[name=confirmCheck]").is(":checked") == false) {
				alert("첨부파일확인 동의 후 전자서명이 가능합니다.");
				return false;
			}
			
			//pageObj.cmtmBrowserSign();
			cmtmSignBrowserData_open();
		});
		
		
		$('.list-tab li').click(function(){
            var $tabLineIdx = $(this).index();
            $(this).addClass('is-selected');
            $(this).siblings().removeClass('is-selected');
            $('.tab-contents-in').eq($tabLineIdx).show();
            $('.tab-contents-in').eq($tabLineIdx).siblings().hide();
        });

		
	};

	 
	/**
	 * window load
	 *
	 */
	$(function() {		
		pageObj.setEventHandler();
		
		fileView($("#P_FILE_GRP_NO").val());
		
		nxTSPKI.onInit(function(){ 
			//nxTSPKI.init 함수 완료 후 실행해야 하는 함수나 기능 작성 위치
			console.log("Init 완료");
		});

		nxTSPKI.init(true);
		
		
		if($("#P_SIGN_YN").val() == "Y"){
			$("input:checkbox[name=confirmCheck]").attr("checked", true);
			$("input:checkbox[name=confirmCheck]").attr("disabled", true);
		}
		
		
		if($("#resultCode").val() == "Success"){
			var estmNo = $("#P_ESTM_NO_TRANS").val();
			$("#detailFrm input[name='P_ESTM_NO']").val(estmNo);
			$("#resultCode").val("");
			FwkCmmnUtil.submitForm("detailFrm", "/opro/estm/estmCmtmProgDetail.do");
		}
		
	});
})();