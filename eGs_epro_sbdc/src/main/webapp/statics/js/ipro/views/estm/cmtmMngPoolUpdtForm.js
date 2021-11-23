/**
 * 평가관리 > 평가위원관리 > 평가위원POOL현황 수정
 *
 * <pre>
 * estm
 *    |_ cmtmMngPoolUpdtForm.js
 * 
 * </pre>
 * @date : 2021. 04. 19.
 * @version : 1.0
 * @author : 은우소프트 
 */
(function() {

	/**
	 * Default Constructor
	 */
	pageObj = {};

	var defaultFrm = "updtFrm";


	pageObj.cmtmMngPoolList = function() {
		FwkCmmnUtil.submitForm("listFrm", "/estm/cmtmMngPoolList.do");
	};

	pageObj.download = function(fileGrpNo, fileSn){
		$("#downloadFrm input[name='P_FILE_GRP_NO']").val(fileGrpNo);
		$("#downloadFrm input[name='P_FILE_SN']").val(fileSn);
		FwkCmmnUtil.submitForm("downloadFrm", "/comm/download.do");
	};
	
	pageObj.poolDownload = function(fileStreCours, wonFileNm){
		$("#downloadFrm input[name='P_FILE_STRE_COURS']").val(fileStreCours);
		$("#downloadFrm input[name='P_WON_FILE_NM']").val(wonFileNm);
		FwkCmmnUtil.submitForm("downloadFrm", "/comm/poolDownload.do");
	};
	
	//파일을 첨부했을경우
	fileSet = function(obj){
		var node = $(obj).parent().parent();
		node.find("input[type=file]").prop("name","P_EDUC_FILE");
		node.parent().find("input[name='P_MOD_YN']").attr("value","Y");
	};
	
	//파일 수정버튼을 
	fileModBtn = function(obj){
		var td = $(obj).parent().parent();	//td
		td.hide();			//보여주는 td
		td.next().show();	// 다음td, 수정가능한 td
		td.next().find("input[type=file]").attr("name","P_EDUC_FILE");
		td.parent().find("input[name='P_MOD_YN']").attr("value","Y");
	};
	
	//파일 취소버튼을 
	fileCancleBtn = function(obj){
		var td = $(obj).parent().parent();	//td
		td.hide();			//수정가능한 td 숨기기
		td.prev().show();	// 이전 td, 보여주는 td
		td.find("input[type=file]").prop("name","");
		td.parent().find("input[name='P_MOD_YN']").attr("value","N");
	};

	//파일을 첨부했을경우
	mstFileSet = function(obj){
		$(obj).next().prop("disabled", false);
	};
	//파일 수정버튼을 
	mstFileModBtn = function(obj){
		var td = $(obj).parent().parent();	//td
		td.hide();			//보여주는 td
		td.next().show();	// 다음td, 수정가능한 td
		td.next().find("input[type=file]").attr("name","P_FILE");
		td.next().find("input[name='P_FILE_DOC_SECD']").prop("disabled", false);
	};
	
	//파일 취소버튼을 
	mstFileCancleBtn = function(obj){
		var td = $(obj).parent().parent();	//td
		td.hide();			//수정가능한 td 숨기기
		td.prev().show();	// 이전 td, 보여주는 td
		td.find("input[type=file]").prop("name","");
		td.find("input[name='P_FILE_DOC_SECD']").prop("disabled", true);
	};
	
	pageObj.cmtmMngPoolUpdt = function() {	
		FwkCmmnUtil.submitForm("updtFrm", "/estm/cmtmMngPoolUpdt.do");
	};
	
	
	/*// 파일 업로드
	fileUploadStart = function() {
		// 첨부파일이 존재하는 경우만 업로드
		if (RAONKUPLOAD.GetTotalFileCount("upload") == 0) {
			FwkCmmnUtil.submitForm("updtFrm", "/estm/cmtmMngPoolRegist.do");
        } else {
            //업로드에서 추가된 파일이 정상으로 넘어오면 파일전송 이벤트를 발생시킵니다.
        	RAONKUPLOAD.Transfer("upload");
        }
	};*/
	
	// 담당자 조회 팝업
	pageObj.chargerInqirePopup = function(obj) {
		$("input[name='setChargerGbn']").val(obj);
		$("#popupFrm").one("submit", function() {
			window.open("", "chargerPopup", "width=1400px,height=760px,toolbar=no,status=no,scrollbars=yes,resizeable=no,menubar=no,left=200,top=100");
			this.action = FwkCmmnUtil['contextPath']+'/comm/popup/chargerList.do';
	        this.method = 'POST';
	        this.target = 'chargerPopup';
	    }).trigger("submit");
	};

	chargerListAdd = function(usrId, emplNo,usrNm, deptCd, deptNm, ofpsCd, ofpsNm, telNo, emalAddr, chargerGbn){
		
		if(chargerGbn == "estmCmtmRegr"){
			// 평가위원등록자
			$("#P_ESTM_CMTM_REGR_NM").val(usrNm);
			$("#P_ESTM_CMTM_REGR_ID").val(usrId);
			
			$("#P_ESTM_CMTM_REG_DEPT_NM").val(deptNm);
			$("#P_ESTM_CMTM_REG_DEPT_NO").val(deptCd);
			
		}
	};
	
	
	// 자격증정보 추가
	pageObj.crqfAdd = function( showTbdy, hidTbdy){
		$("#" + showTbdy)
			.append($('<tr>')
				.append($('<td>').addClass("txt-center")
						.append($('<label>').addClass("component-checkbox")
								.append($('<input>').attr({'type':'checkbox','name':'crqfCbx'}))
								.append($('<i>'))))
				.append($('<td>').addClass("txt-center")
						.append($('<input>').attr({'type':'text','name':'P_CRQF_NM'}).addClass('component-input w100')))
				.append($('<td>').addClass("txt-center")
						.append($('<input>').attr({'type':'text','name':'P_PBLS_AGNC'}).addClass('component-input w100')))
				.append($('<td>').addClass("txt-center")
						.append($('<div>').addClass('area-calen')
								.append($('<div>').addClass('component-calen'))
										.append($('<div>').addClass('data-calen'))
												.append($('<input>').attr({'type':'text','name':'P_ACQS_DE', 'date':''}).css({'width':'100px'}).addClass('component-input'))))
			);

			setDatePicker();
	};
	
	// 자격증정보 삭제
	pageObj.crqfDel = function(){
		$("#crqfShowTbdy input[name='crqfCbx']").each(function(inx){
			if(this.checked) {
				$(this).parent().parent().parent().remove();
			}
		});
	};
	
	// 직장경력정보 추가
	pageObj.ofcCareAdd = function(showTbdy, hidTbdy){		
		$("#" + showTbdy)
		.append($('<tr>')
			.append($('<td>').addClass("txt-center")
					.append($('<label>').addClass("component-checkbox")
							.append($('<input>').attr({'type':'checkbox','name':'ofcCareCbx'}))
							.append($('<i>'))))
			.append($('<td>').addClass("txt-center")
					.append($('<input>').attr({'type':'text','name':'P_OFC_NM'}).addClass('component-input w100')))
			.append($('<td>').addClass("txt-center")
					.append($('<input>').attr({'type':'text','name':'P_WRK_DEPT_NM'}).addClass('component-input w100')))
			.append($('<td>').addClass("txt-center")
					.append($('<input>').attr({'type':'text','name':'P_OPNM'}).addClass('component-input w100')))
			.append($('<td>').addClass("txt-center")
					.append($('<input>').attr({'type':'text','name':'P_WRK_YEAR_CNT','maxlength':'2','onkeyup':'fnNumberChk(this);'}).addClass('component-input w50'))
							.append('&nbsp;년&nbsp;'))
			.append($('<td>').addClass("txt-center")
					.append($('<input>').attr({'type':'text','name':'P_CHRG_TSK_ACPS'}).addClass('component-input w100')))
		);	
		/*var row = $("#ofcCareHidTbdy").children().clone();
		$("#ofcCareShowTbdy").append(row);*/
	};

	// 직장경력정보 삭제
	pageObj.ofcCareDel = function(){
		$("#ofcCareShowTbdy input[name='ofcCareCbx']").each(function(inx){
			if(this.checked) {
				$(this).parent().parent().parent().remove();
			}
		});
	};

	// 평가위원학력 추가
	pageObj.educAdd = function(showTbdy, hidTbdy){		
		$("#" + showTbdy)
		.append($('<tr>')
			.append($('<td>').addClass("txt-center")
					.append($('<label>').addClass("component-checkbox")
							.append($('<input>').attr({'type':'checkbox','name':'educCbx'}))
							.append($('<i>'))))
			.append($('<td>').addClass("txt-center")
					.append($('<input>').attr({'type':'text','name':'P_MSDG'}).addClass('component-input w100'))
					.append($('<input>').attr({'type':'hidden','name':'P_EDUC_SEQ'}).addClass('component-input w100')))
			.append($('<td>').addClass("txt-center")
					.append($('<input>').attr({'type':'text','name':'P_SHL'}).addClass('component-input w100')))
			.append($('<td>').addClass("txt-center")
					.append($('<input>').attr({'type':'text','name':'P_SCCT'}).addClass('component-input w100')))
			.append($('<td>')
					.append($('<input>').attr({'type':'text','name':'P_TE_FROM_Y','maxlength':'4','onkeyup':'fnNumberChk(this);'}).addClass('component-input w20'))
					.append('&nbsp;년&nbsp;')
					.append($('<input>').attr({'type':'text','name':'P_TE_FROM_M','maxlength':'2','onkeyup':'fnNumberChk(this);'}).addClass('component-input w10'))
					.append('&nbsp;월&nbsp;~&nbsp;')
					.append($('<input>').attr({'type':'text','name':'P_TE_TO_Y','maxlength':'4','onkeyup':'fnNumberChk(this);'}).addClass('component-input w20'))
					.append('&nbsp;년&nbsp;')
					.append($('<input>').attr({'type':'text','name':'P_TE_TO_M','maxlength':'2','onkeyup':'fnNumberChk(this);'}).addClass('component-input w10'))
					.append('&nbsp;월&nbsp;'))
			.append($('<td>').addClass("txt-center")
					.append($('<input>').attr({'type':'file','name':'P_EDUC_FILE'}).css({'width':'100%'}))
					.append($('<input>').attr({'type':'hidden','name':'P_FILE_SZ'}))
					.append($('<input>').attr({'type':'hidden','name':'P_SV_FILE_NM'}))
					.append($('<input>').attr({'type':'hidden','name':'P_SYS_FILE_NM'}))
					.append($('<input>').attr({'type':'hidden','name':'P_FILE_LCTN'})))
		);
	/*	var row = $("#educHidTbdy").children().clone();
		$("#educShowTbdy").append(row);*/
	};
	
	// 평가위원학력 삭제
	pageObj.educDel = function(){
		$("#educShowTbdy input[name='educCbx']").each(function(inx){
			if(this.checked) {
				$(this).parent().parent().parent().remove();
			}
		});
	};
	
	
	// 평가위원활동경력 추가
	pageObj.careAdd = function(showTbdy, hidTbdy){		
		$("#" + showTbdy)
		.append($('<tr>')
			.append($('<td>').addClass("txt-center")
					.append($('<label>').addClass("component-checkbox")
							.append($('<input>').attr({'type':'checkbox','name':'careCbx'}))
							.append($('<i>'))))
			.append($('<td>').addClass("txt-center")
					.append($('<input>').attr({'type':'text','name':'P_BSNS_NM'}).addClass('component-input w100')))
			.append($('<td>').addClass("txt-center")
					.append($('<input>').attr({'type':'text','name':'P_ESTM_SPHE'}).addClass('component-input w100')))
			.append($('<td>').addClass("txt-center")
					.append($('<input>').attr({'type':'text','name':'P_RUN_YR','maxlength':'4','onkeyup':'fnNumberChk(this);'}).addClass('component-input w70'))
					.append('&nbsp;년&nbsp;'))
			.append($('<td>').addClass("txt-center")
					.append($('<input>').attr({'type':'text','name':'P_AGNC_NM'}).addClass('component-input w100')))
		);
		/*var row = $("#careHidTbdy").children().clone();
		$("#careShowTbdy").append(row);*/
	};
	
	// 평가위원활동경력 삭제
	pageObj.careDel = function(){
		$("#careShowTbdy input[name='careCbx']").each(function(inx){
			if(this.checked) {
				$(this).parent().parent().parent().remove();
			}
		});
	};
	
	fnNumberChk = function(obj){
      if($(obj).val() != "" ){
         if(FwkCmmnUtil.isNumeric($(obj).val()) != true){
            alert("숫자만 입력 가능합니다.");
            $(obj).val( $(obj).val().replace(/[^0-9]/gi,""));
         }
      }
   };
	
	/**
	 * 페이지내에서 필요한 항목의 이벤트를 바인딩한다.
	 * 1. 목록버튼(#listBtn) 의 click 이벤트를 binding 한다.
	 */
	pageObj.setEventHandler = function() {

		// 등록버튼
		$("#saveBtn").on("click", function() {
			
			if($("#P_ESTM_CMTM_NM").val() == ''){
				alert("평가위원을 입력하여 주시기 바랍니다.");
				$("#P_ESTM_CMTM_NM").focus();
				return false;
			}
			if($("#P_INO_CMTM_SECD").val() == ''){
				alert("내/외부 구분을 선택하여 주시기 바랍니다.");
				$("#P_INO_CMTM_SECD").focus();
				return false;
			}
			if($("#P_BRDT").val() != ''){
				if($("#P_BRDT").val().length != 8){
					alert("생년월일을 올바르게 입력해주세요.");
					$("#P_BRDT").focus();
					return false;
				}
			}
			if($("#P_ESTM_CMTM_REG_DEPT_NM").val() == ''){
				alert("평가위원등록부서을 선택하여 주시기 바랍니다.");
				$("#P_ESTM_CMTM_REG_DEPT_NM").focus();
				return false;
			}
			if($("#P_ESTM_CMTM_REG_DEPT_NM").val() == ''){
				alert("평가위원등록부서을 선택하여 주시기 바랍니다.");
				$("#P_ESTM_CMTM_REG_DEPT_NM").focus();
				return false;
			}
			
			if($("#P_HNDW_REG_YN").val() == ''){
				alert("수기등록여부를 선택하여 주시기 바랍니다.");
				$("#P_HNDW_REG_YN").focus();
				return false;
			}
			if($("#P_CP_NO").val() == ''){
				alert("휴대폰전화번호를 입력하여 주시기 바랍니다.");
				$("#P_CP_NO").focus();
				return false;
			}
			if($("#P_TEL_NO").val() == ''){
				alert("전화번호를 입력하여 주시기 바랍니다.");
				$("#P_TEL_NO").focus();
				return false;
			}
			if($("#P_EMAL").val() == ''){
				alert("이메일 입력하여 주시기 바랍니다.");
				$("#P_EMAL").focus();
				return false;
			}
			
			if($("#P_ESTM_CMTM_BLNG_AGNC").val() == ''){
				alert("소속회사명을 선택하여 주시기 바랍니다.");
				$("#P_ESTM_CMTM_BLNG_AGNC").focus();
				return false;
			}
			if($("#P_ESTM_CMTM_BLNG_DEPT").val() == ''){
				alert("부서를 선택하여 주시기 바랍니다.");
				$("#P_ESTM_CMTM_BLNG_DEPT").focus();
				return false;
			}
			if($("#P_ESTM_CMTM_OFPS").val() == ''){
				alert("직위를 선택하여 주시기 바랍니다.");
				$("#P_ESTM_CMTM_OFPS").focus();
				return false;
			}
			
			if($("#P_BLNG_AGNC_HMPG_ADDR").val() == ''){
				alert("홈페이지주소를 입력하여 주시기 바랍니다.");
				$("#P_BLNG_AGNC_HMPG_ADDR").focus();
				return false;
			}
			
			var flag = true;
			
			if(!$("#P_HFFC_FILE").next().prop("disabled") ){
				$("#P_HFFC_FILE").each(function(inx){
					if(flag && $(this).val() == ""){
						$(this).focus();
						//return false;
						flag = false;
					}
				});
			}
			
			if(!flag){
				alert("재직증명서 사본을 등록해주세요.");
				return false;
			}
			
			$("#careShowTbdy input[type='text']").each(function(inx){
				if(flag && $(this).val() == ""){
					$(this).focus();
					//return false;
					flag = false;
				}
			});
			
			if(!flag){
				alert("평가위원활동경력에 입력되지 않은 정보가 있습니다.");
				return false;
			}
			
			var gbn;
			$("#educShowTbdy input[type='text']").each(function(inx){
				if(flag && $(this).val() == ""){
					$(this).focus();
					//return false;
					flag = false;
				}
				
				if($(this).attr("name") == "P_TE_FROM_Y"){
					if($(this).val() != ''){
						if($(this).val().length != 4){
							$(this).focus();
							flag = false;
							gbn = "Y";
						}
					}
				}
				
				if($(this).attr("name") == "P_TE_FROM_M"){
					if($(this).val() != ''){
						if(Number($(this).val()) > 12 || Number($(this).val()) < 1){
							$(this).focus();
							flag = false;
							gbn = "P";
						}else{
							if($(this).val().length == 1 && $(this).val() != 0){
								$(this).val(0 + $(this).val());
							}else if($(this).val().length != 2){
								$(this).focus();
								flag = false;
								gbn = "M";
							}
						}
					}
				}
				
				if($(this).attr("name") == "P_TE_TO_Y"){
					if($(this).val() != ''){
						if($(this).val().length != 4){
							$(this).focus();
							flag = false;
							gbn = "Y";
						}
					}
				}
				
				if($(this).attr("name") == "P_TE_TO_M"){
					if($(this).val() != ''){
						if(Number($(this).val()) > 12 || Number($(this).val()) < 1){
							$(this).focus();
							flag = false;
							gbn = "P";
						}else{
							if($(this).val().length == 1 && $(this).val() != 0){
								$(this).val(0 + $(this).val());
							}else if($(this).val().length != 2){
								$(this).focus();
								flag = false;
								gbn = "M";
							}
						}
					}
				}
			});
			$("#educShowTbdy input[name='P_EDUC_FILE']").each(function(inx){
				if(flag && $(this).val() == ""){
					$(this).focus();
					//return false;
					flag = false;
				}
			});

			$("input[name='P_FILE']").each(function(inx){
				$("input[name='P_FILE_MOD_YN']").val("Y");
			});
			
			if(!flag){
				if(gbn == "Y"){
					alert("학력 기간의 [년]을 올바르게 입력해주세요.");
				}else if(gbn == "M"){
					alert("학력 기간의 [월]을 올바르게 입력해주세요.");
				}else if(gbn == "P"){
					alert("학력 기간의 [월]을 1~12까지만 입력해주세요.");
				}else{
					alert("평가위원학력에 입력되지 않은 정보가 있습니다.");
				}
				return false;
			}
			
			$("#ofcCareShowTbdy input[type='text']").each(function(inx){
				if(flag && $(this).val() == ""){
					$(this).focus();
					//return false;
					flag = false;
				}
			});
			
			if(!flag){
				alert("직장경력정보에 입력되지 않은 정보가 있습니다.");
				return false;
			}
			
			$("#crqfShowTbdy input[type='text']").each(function(inx){
				if(flag && $(this).val() == ""){
					$(this).focus();
					//return false;
					flag = false;
				}
			});
			
			if(!flag){
				alert("자격증정보에 입력되지 않은 정보가 있습니다.");
				return false;
			}
			
			
			if(!confirm("저장하시겠습니까?")){
				return false;
			}
			
			$("#crqfHidTbdy").remove();
			$("#ofcCareHidTbdy").remove();
			$("#educHidTbdy").remove();
			$("#careHidTbdy").remove();
			
			$("#educShowTbdy input[name='P_MOD_YN']").each(function(inx){
				if($(this).val() == "Y"){
					$(this).prev().val("");
				}
			});
			//fileUploadStart();
			pageObj.cmtmMngPoolUpdt();
			
		});

		// 목록버튼
		$("#listBtn").on("click", function() {
			if(!confirm("목록으로 이동하시겠습니까?")){ 
				return false;
			}
			pageObj.cmtmMngPoolList();
		});

		// 평가위원등록자 검색버튼
		$("#estmCmtmRegrSrchBtn").on("click", function() {
			pageObj.chargerInqirePopup('estmCmtmRegr');
		});
		
		// 자격증정보 추가 버튼
		$("#crqfAddBtn").on("click", function() {
			pageObj.crqfAdd("crqfShowTbdy", "crqfHidTbdy");
		});
		
		// 자격증정보 삭제 버튼
		$("#crqfDelBtn").on("click", function() {
			pageObj.crqfDel();
		});
		
		// 직장경력정보 추가 버튼
		$("#ofcCareAddBtn").on("click", function() {
			pageObj.ofcCareAdd("ofcCareShowTbdy", "ofcCareHidTbdy");
			//pageObj.ofcCareAdd();
		});
		
		// 직장경력정보 삭제 버튼
		$("#ofcCareDelBtn").on("click", function() {
			pageObj.ofcCareDel();
		});
		
		// 평가위원학력 추가 버튼
		$("#educAddBtn").on("click", function() {
			pageObj.educAdd("educShowTbdy", "educHidTbdy");
			//pageObj.educAdd();
		});
		
		// 평가위원학력 삭제 버튼
		$("#educDelBtn").on("click", function() {
			pageObj.educDel();
		});
		
		// 평가위원활동경력 추가 버튼
		$("#careAddBtn").on("click", function() {
			pageObj.careAdd("careShowTbdy", "careHidTbdy");
			//pageObj.careAdd();
		});
		
		// 평가위원활동경력 삭제 버튼
		$("#careDelBtn").on("click", function() {
			pageObj.careDel();
		});
		
	};
	

	/**
	 * window load
	 *
	 */
	$(function() {
		pageObj.setEventHandler();
		fileUpload("updtFrm", "P_FILE_GRP_NO", "/estm/cmtmMngPoolRegist.do", "listFrm", "/estm/cmtmMngPoolList.do", "");
	});
})();