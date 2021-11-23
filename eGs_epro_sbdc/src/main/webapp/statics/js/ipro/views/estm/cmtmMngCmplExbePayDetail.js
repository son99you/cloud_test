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
	
	// 2021-05-06 추가 : 평가위원첨부 상세 팝업
	pageObj.estmCmtmFileView = function(estmCmtmNo, fileGrpNo){
		
		$("#popupFrm input[name='P_ESTM_CMTM_NO']").val(estmCmtmNo);
		$("#popupFrm input[name='P_FILE_GRP_NO']").val(fileGrpNo);
		
		$("#popupFrm").one("submit", function() {
			window.open("", "deptPopup", "width=750px,height=400px,toolbar=no,status=no,scrollbars=yes,resizeable=no,menubar=no,left=600,top=300");
			this.action = FwkCmmnUtil['contextPath']+'/comm/popup/estmCmtmFileView.do';
	        this.method = 'POST';
	        this.target = 'deptPopup';
	    }).trigger("submit");
	};
	
	
	
	// 부서 조회 팝업
	pageObj.deptInqirePopup = function(selectNumber) {
		
		var str = selectNumber;
		str = str.replace("estmChrgDepSrchBtn" , "");
		
		$("#popupFrm input[name='P_SELECT_NUMBER']").val(str);
		$("#popupFrm input[name='setMulti']").val("N");
		$("#popupFrm").one("submit", function() {
			window.open("", "deptPopup", "width=750px,height=760px,toolbar=no,status=no,scrollbars=yes,resizeable=no,menubar=no,left=500,top=100");
			this.action = FwkCmmnUtil['contextPath']+'/comm/popup/deptYMList.do';
	        this.method = 'POST';
	        this.target = 'deptPopup';
	    }).trigger("submit");
	};
	
	//평가담당부서 삭제
	pageObj.deptInqireDelete = function(selectNumber) {
		
		var str = selectNumber;
		str = str.replace("estmChrgDeptDelBtn" , "");
		
		
		$("#P_PAY_ORGAN_NM" + str).val(""); // 집행부서
		$("#P_PAY_STORE" + str).val(""); // 집행부서코드(store)
		$("#P_PAY_ORGAN" + str).val(""); // 집행부서코드(organ)
		
	};
	
	pageObj.paySubmit = function(obj) {
		
		if($(obj).parent().parent().find($("input[name='P_RSDN_NO']")).val() == "") {
			alert("지급요청시 주민번호는 필수 입니다.");
			return false;
		}
		
		if($(obj).parent().parent().find($("input[name='P_DEPOSITOR_NM']")).val() == "") {
			alert("지급요청시 예금주는 필수 입니다.");
			return false;
		}
		
		if($(obj).parent().parent().find($("input[name='P_BANK_HQ']")).val() == "") {
			alert("지급요청시 지급은행은 필수 입니다.");
			return false;
		}
		
		if($(obj).parent().parent().find($("input[name='P_BANK_ACCT']")).val() == "") {
			alert("지급요청시 지급계좌번호는 필수 입니다.");
			return false;
		}
		
		if($(obj).parent().parent().find($("input[name='P_PAY_STORE']")).val() == "") {
			alert("지급요청시 집행사업부는 필수 입니다.");
			return false;
		}
		
		if($(obj).parent().parent().find($("input[name='P_PAY_ORGAN']")).val() == "") {
			alert("지급요청시 집행부서코드는 필수 입니다.");
			return false;
		}
		
		
		
		$("#PayFrm input[name='P_ESTM_CMTM_NO']").val($(obj).parent().parent().find($("input[name='P_ESTM_CMTM_NO']")).val()); // 평가위원번호
		$("#PayFrm input[name='P_RSDN_NO']").val($(obj).parent().parent().find($("input[name='P_RSDN_NO']")).val()); // 주민등록번호
		$("#PayFrm input[name='P_DEPOSITOR_NM']").val($(obj).parent().parent().find($("input[name='P_DEPOSITOR_NM']")).val()); // 예금주
		$("#PayFrm input[name='P_BANK_HQ']").val($(obj).parent().parent().find($("select[name='P_BANK_HQ']")).val()); // 지급은행
		$("#PayFrm input[name='P_BANK_ACCT']").val($(obj).parent().parent().find($("input[name='P_BANK_ACCT']")).val()); // 지급계좌번호
		$("#PayFrm input[name='P_YM']").val($(obj).parent().parent().find($("input[name='P_YM']")).val()); // 년월
		$("#PayFrm input[name='P_PAY_STORE']").val($(obj).parent().parent().find($("input[name='P_PAY_STORE']")).val()); // 집행부서사업부
		$("#PayFrm input[name='P_PAY_ORGAN']").val($(obj).parent().parent().find($("input[name='P_PAY_ORGAN']")).val()); // 집행부서코드
		
		$("#PayFrm input[name='P_ASSESS_DT']").val($(obj).parent().parent().find($("input[name='P_ASSESS_DT']")).val()); // 지급요청일자
		$("#PayFrm input[name='P_ASSESS_SEQ']").val($(obj).parent().parent().find($("input[name='P_ASSESS_SEQ']")).val()); // 지급요청번호
		
		
		
		
		FwkCmmnUtil.submitForm("PayFrm", "/estm/estmCmplExbePaySave.do");
		
	};
	
	
	
	deptListAdd = function(YM,STORE, ORGAN, DEPT_NM){
		
		var selectNumber = $("#popupFrm input[name='P_SELECT_NUMBER']").val();
		
		
		$("#P_YM" + selectNumber).val(YM); // 집행부서
		$("#P_PAY_ORGAN_NM" + selectNumber).val(DEPT_NM); // 집행부서
		$("#P_PAY_STORE" + selectNumber).val(STORE); // 집행부서코드(store)
		$("#P_PAY_ORGAN" + selectNumber).val(ORGAN); // 집행부서코드(organ)
		
		// 마지막에 초기화
		$("#popupFrm input[name='P_SELECT_NUMBER']").val("");
	}
	
	
	pageObj.setEventHandler = function() {
		
		//회의참가요청목록 팝업 
		$(document).on("click","button[name='mtngPrtcRqs']", function() {
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
		
		
		// 평가담당부서 검색버튼
		$("a[name='estmChrgDepSrchBtn']").on("click", function() {
			pageObj.deptInqirePopup($(this).attr("id"));
		});
		
		// 평가담당부서 삭제버튼
		$("a[name='estmChrgDeptDelBtn']").on("click", function() {
			pageObj.deptInqireDelete($(this).attr("id"));
		});
		
		$("a[name='paySubmit']").on("click", function() {
			pageObj.paySubmit($(this));
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