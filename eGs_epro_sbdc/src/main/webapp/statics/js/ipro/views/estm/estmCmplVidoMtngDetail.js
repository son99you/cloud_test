(function() {

	/**
	 * Default Constructor
	 */
	pageObj = {};
	
	// 파일 업로드
	fileUploadStart = function() {
		RAONKUPLOAD.Transfer("uploadView1");
	};
	
	// addBtn
	pageObj.addRow = function() {
		var copyRow = $("#hiddenTbody").children().clone(true);
		
		copyRow.css({"display" : ""});
		
		$(" #hiddenTbody").css({"display" : "none"});
		
		$(" #showTbody").append(copyRow);
	};
	
	// delRow
	pageObj.delRow = function() {
		$("#showTbody input[name='chck']").each(function(inx){
			if(this.checked) {
				$(this).parent().parent().parent().remove();
			}
		});
	};
	
	tabEvent = function(tab_no){
		
		if(tab_no == "1"){   // 기본정보 
			FwkCmmnUtil.submitForm("detailFrm", "/estm/estmCmplDetail.do");
		}else if(tab_no == "2"){   // 평가대상
			FwkCmmnUtil.submitForm("detailFrm", "/estm/estmCmplObjDetail.do");
		}else if(tab_no == "3"){   // 평가위원
			FwkCmmnUtil.submitForm("detailFrm", "/estm/estmCmplCmtmDetail.do"); 
		}else if(tab_no == "4"){   // 서류평가
			FwkCmmnUtil.submitForm("detailFrm", "/estm/estmCmplProcdADetail.do?P_ESTM_PROCD_SEQ=1");
		}else if(tab_no == "5"){   // 품평회
			FwkCmmnUtil.submitForm("detailFrm", "/estm/estmCmplProcdADetail.do?P_ESTM_PROCD_SEQ=2");
		}else if(tab_no == "6"){   // 정량평가
			FwkCmmnUtil.submitForm("detailFrm", "/estm/estmCmplProcdBDetail.do");
		}else if(tab_no == "7"){   // 평가결과
			FwkCmmnUtil.submitForm("detailFrm", "/estm/estmCmplResultDetail.do");
		}else if(tab_no == "8"){   // 화상회의
			FwkCmmnUtil.submitForm("detailFrm", "/estm/estmCmplVidoMtngDetail.do");
		}else if(tab_no == "9"){   // 수당지급
			FwkCmmnUtil.submitForm("detailFrm", "/estm/estmCmplExbePayDetail.do");
		}
		
	};
	
	// 평가대상정보 첨부파일 상세 팝업
	pageObj.estmVidoFileView = function(VIDO_MTNG_SEQ, FILE_GRP_NO){
		//var P_ESTM_PSCD = $("#registFrm input[name='P_ESTM_PSCD']").val();
		
		$("#popupFrm input[name='P_VIDO_MTNG_SEQ']").val(VIDO_MTNG_SEQ);
		$("#popupFrm input[name='P_FILE_GRP_NO']").val(FILE_GRP_NO);
		$("#popupFrm").one("submit", function() {
			window.open("", "estmVidoFileViewPopup", "width=750px,height=500px,toolbar=no,status=no,scrollbars=yes,resizeable=no,menubar=no,left=600,top=300");
			this.action = FwkCmmnUtil['contextPath']+'/comm/popup/estmVidoFileView.do';
	        this.method = 'POST';
	        this.target = 'estmVidoFileViewPopup';
	    }).trigger("submit");
	};
	
	
	pageObj.setEventHandler = function() {
		
		//회의참가요청목록 팝업 
		$(document).on("click","button[name='mtngPrtcRqs']", function() {
			$("#popupFrm input[name='P_ESTM_NO']").val($("#detailFrm input[name='P_ESTM_NO']").val());
			/*$("#popupFrm input[name='P_ESTM_PROCD_SEQ']").val($("#detailFrm input[name='P_ESTM_PROCD_SEQ']").val());*/
			$("#popupFrm input[name='P_VIDO_MTNG_SEQ']").val($("#detailFrm input[name='P_VIDO_MTNG_SEQ']").val());
			$("#popupFrm input[name='P_ESTM_OBJ_SECD']").val($("#detailFrm input[name='P_ESTM_OBJ_SECD']").val());
			
			$("#popupFrm").one("submit", function() {
				window.open("", "mtngPrtcRqstList", "width=750px,height=760px,toolbar=no,status=no,scrollbars=yes,resizeable=no,menubar=no,left=500,top=100");
				this.action = FwkCmmnUtil['contextPath']+'/comm/popup/mtngPrtcRqstList.do';
		        this.method = 'POST';
		        this.target = 'mtngPrtcRqstList';
		    }).trigger("submit");
			return false;
		
		});
		
		// 회의실이동 
		$(document).on("click","button[name='mtngMvmt']", function() {
			FwkCmmnUtil.submitForm("detailFrm" , "/estm/estmProgVidoMtngDetail.do");
			return false;
		});
		
		
		/**
		 * 수정버튼 클릭 이벤트
		 */
		$("#saveBtn").on("click", function() {
			FwkCmmnUtil.submitForm("detailFrm" , "/estm/estmProgVidoMtngDetail.do");
			return false;
		});
		
		
		/**
		 * 목록버튼 클릭 이벤트
		 */
		$("#listBtn").on("click", function() {
			FwkCmmnUtil.submitForm("listFrm" , "/estm/estmCmplList.do");
			return false;
		});
		
		/**
		 * 평가추가버튼 클릭 이벤트
		 */
		$("#addBtn").on("click", function() {
			// 목록
			pageObj.addRow();
			return false;
		});
		
		/**
		 * 평가추가버튼 클릭 이벤트
		 */
		$("#delBtn").on("click", function() {
			// 목록
			pageObj.delRow();
			return false;
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