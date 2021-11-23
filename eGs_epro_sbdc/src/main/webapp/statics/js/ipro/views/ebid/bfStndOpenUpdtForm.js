/**
 * 입찰관리 > 사전규격공개진행 수정
 *
 * <pre>
 * ebid 
 *    |_ bfStndOpenUpdtForm.js
 * 
 * </pre>
 * @date : 2018. 02. 19.
 * @version : 1.0
 * @author : 은우소프트 맹경열
 */

(function() {

	/**
	 * Default Constructor
	 */
	pageObj = {};

	/**
	 * Default Form Name
	 */
	var defaultFrm = "updtFrm";

	
	// 목록
	pageObj.bfStndOpenPrcnList = function() {
		FwkCmmnUtil.submitForm("listFrm", "/ebid/bfStndOpenPrcnList.do");
	};
	
	//취소
	pageObj.bfStndOpenPrcnDetail = function() {
		FwkCmmnUtil.submitForm("updtFrm", "/ebid/bfStndOpenPrcnDetail.do");
	};
	
	download = function(grpNo, sn) {
		$("#downloadFrm input[name='P_FILE_GRP_NO']").val(grpNo);
		$("#downloadFrm input[name='P_FILE_SN']").val(sn);
		FwkCmmnUtil.submitForm("downloadFrm", "/comm/download.do" );
	};
	
	// 저장
	pageObj.bfStndOpenUpdt = function() {
		fileUploadStart();
	};
	
	
	// 파일 업로드
	fileUploadStart = function() {
		RAONKUPLOAD.Transfer("uploadView1");
	};
	
	pageObj.fileSamplePopup = function () {
		// 팝업처리
		$("#popupFrm input[name='P_RECM_ID']").val($("#P_RECM_ID").val());
		$("#popupFrm").one("submit", function() {
			window.open("", "fileSamplePopup", "width=1000px, height=800px, toolbar=no, status=no, scrollbars=yes, menubar=no,left=500,top=100");
			this.action = FwkCmmnUtil['contextPath']+'/comm/popup/fileSamplePopup.do';
			this.method = 'POST';
			this.target = 'fileSamplePopup';
		}).trigger("submit");
	};	
	
	
	// 담당자 조회 팝업
	pageObj.chargerInqirePopup = function(obj) {
		$("input[name='setChargerGbn']").val(obj);
		$("#popupFrm").one("submit", function() {
			window.open("", "chargerPopup", "width=1400px,height=760px,toolbar=no,status=no,scrollbars=yes,resizeable=no,menubar=no,left=500,top=100");
			this.action = FwkCmmnUtil['contextPath']+'/comm/popup/chargerList.do';
	        this.method = 'POST';
	        this.target = 'chargerPopup';
	    }).trigger("submit");
	};
	
	//담당자 삭제
	pageObj.chargerDelete = function(gbn) {
		if(gbn == "M"){
			$('#P_MNGR_CHRGR_NM').val("");
			$('#P_MNGR_CHRGR_ID').val("");
		}else if(gbn == "C"){
			$('#P_CHCK_CHRGR_NM').val("");
			$('#P_CHCK_CHRGR_ID').val("");
		}
	};
	
	recmId = function(){
		var P_SCLSH_SVC_YN = $("input[name='P_SCLSH_SVC_YN']").val();//학술
		var P_ACPS_LMT_YN = $("input[name='P_ACPS_LMT_YN']").val();	//실적제한
		var P_EMRG_YN = $("input[name='P_EMRG_YN']").val();	//긴급입찰
		var P_SW_BSNS_OBJ_YN = $("input[name='P_SW_BSNS_OBJ_YN']").val();	//소프트웨어
		var P_INF_BSNS_YN = $("input[name='P_INF_BSNS_YN']").val();
		var P_WMAN_SCTY_CORP_YN = $("input[name='P_WMAN_SCTY_CORP_YN']").val();
		var P_CONT_SECD = $("#P_CONT_SECD").val();
		var P_CONT_MTCD = $("#P_CONT_MTCD").val();
		var P_SPLY_PRCE_M = Number(FwkCmmnUtil.deleteComma($("#P_ESTT_PRCE_M").val()));
		var P_SBID_MTCD = $("input[name='P_SBID_MTCD']").val();			//낙찰방법
		var P_ASSO_SPDM_CD = $("input[name='P_ASSO_SPDM_CD']").val();			//공동계약여부
		if(P_CONT_SECD == 0){
			if(P_CONT_MTCD == "10003"){
				if(P_SPLY_PRCE_M < 1000000){
					$("#P_RECM_ID").val("010003A");
				}else if(P_SPLY_PRCE_M > 1000000 && P_SPLY_PRCE_M <= 10000000){
					$("#P_RECM_ID").val("010003B");
				}else if(P_SPLY_PRCE_M > 10000000 && P_SPLY_PRCE_M <= 20000000){
					$("#P_RECM_ID").val("010003C");
				/*}else if(P_PVCT_RSN_NO == '5' || P_PVCT_RSN_NO == '6' && P_SPLY_PRCE_M > 20000000){
					$("#P_RECM_ID").val("010003F");*/
				}else if(P_SPLY_PRCE_M > 20000000 && P_SPLY_PRCE_M <= 30000000 && P_WMAN_SCTY_CORP_YN == 'Y'){
					$("#P_RECM_ID").val("010003D");
				}else if(P_SPLY_PRCE_M > 30000000 && P_SPLY_PRCE_M <= 50000000 && P_WMAN_SCTY_CORP_YN == 'Y'){
					$("#P_RECM_ID").val("010003E");
				}else if(P_SPLY_PRCE_M > 20000000 && P_WMAN_SCTY_CORP_YN == 'N'){
					$("#P_RECM_ID").val("010003G");
				}
			}else if(P_CONT_MTCD == "10000" || P_CONT_MTCD == "10001" || P_CONT_MTCD == "10002"){
				$("#P_RECM_ID").val("010000A");
			}
		}else if(P_CONT_SECD == 1){
			if(P_CONT_MTCD == "10003"){
				if(P_SPLY_PRCE_M < 20000000){
					$("#P_RECM_ID").val("110003B");
				}else if(P_SPLY_PRCE_M > 20000000 && P_SPLY_PRCE_M < 50000000 && P_WMAN_SCTY_CORP_YN == 'Y'){
					$("#P_RECM_ID").val("110003E");
				}else if(P_SPLY_PRCE_M > 20000000 && P_SPLY_PRCE_M < 50000000 && P_SCLSH_SVC_YN == 'Y'){
					$("#P_RECM_ID").val("110003E");
				}else if(P_SPLY_PRCE_M > 20000000 && P_WMAN_SCTY_CORP_YN == 'N'){
					$("#P_RECM_ID").val("110003C");
				}
			}else if(P_CONT_MTCD == "10000" || P_CONT_MTCD == "10001" || P_CONT_MTCD == "10002"){
				if(P_SBID_MTCD == "40"){
					if(P_SW_BSNS_OBJ_YN == "Y"){
						if(P_SPLY_PRCE_M < 3600000000){
							$("#P_RECM_ID").val("110000A");
						}else{
							$("#P_RECM_ID").val("");
						}
					}else{
						if(P_SPLY_PRCE_M < 100000000){
							if(P_SCLSH_SVC_YN == "N" && P_ACPS_LMT_YN == "N"){
								if(P_ASSO_SPDM_CD == "240000"){		//단독일때
									if(	P_EMRG_YN == "N"){	
										$("#P_RECM_ID").val("110000B");
									}else{   //긴급일때
										$("#P_RECM_ID").val("110000C");
									}
								}else if(P_ASSO_SPDM_CD == "240005"){		//공동일때
									if(	P_EMRG_YN == "N"){	
										$("#P_RECM_ID").val("110000D");
									}else{   //긴급일때
										$("#P_RECM_ID").val("110000E");
									}
								}
							}else if(P_SCLSH_SVC_YN == "Y" && P_ACPS_LMT_YN == "N"){
								if(P_ASSO_SPDM_CD == "240000"){		//단독일때
									if(	P_EMRG_YN == "N"){	
										$("#P_RECM_ID").val("110000F");
									}else{   //긴급일때
										$("#P_RECM_ID").val("110000G");
									}
								}else if(P_ASSO_SPDM_CD == "240005"){		//공동일때
									if(	P_EMRG_YN == "N"){	
										$("#P_RECM_ID").val("110000H");
									}else{   //긴급일때
										$("#P_RECM_ID").val("110000I");
									}
								}
							}
						}else if(P_SPLY_PRCE_M >= 100000000 && P_SPLY_PRCE_M < 200000000){
							if(P_SCLSH_SVC_YN == "N" && P_ACPS_LMT_YN == "N"){
								if(P_ASSO_SPDM_CD == "240000"){		//단독일때
									if(	P_EMRG_YN == "N"){	
										$("#P_RECM_ID").val("110000J");
									}else{   //긴급일때
										$("#P_RECM_ID").val("110000K");
									}
								}else if(P_ASSO_SPDM_CD == "240005"){		//공동일때
									if(	P_EMRG_YN == "N"){	
										$("#P_RECM_ID").val("110000L");
									}else{   //긴급일때
										$("#P_RECM_ID").val("110000M");
									}
								}
							}else if(P_SCLSH_SVC_YN == "Y" && P_ACPS_LMT_YN == "N"){
								if(P_ASSO_SPDM_CD == "240000"){		//단독일때
									if(	P_EMRG_YN == "N"){	
										$("#P_RECM_ID").val("110000N");
									}else{   //긴급일때
										$("#P_RECM_ID").val("110000O");
									}
								}else if(P_ASSO_SPDM_CD == "240005"){		//공동일때
									if(	P_EMRG_YN == "N"){	
										$("#P_RECM_ID").val("110000P");
									}else{   //긴급일때
										$("#P_RECM_ID").val("110000Q");
									}
								}
							}
						}else if(P_SPLY_PRCE_M >= 200000000){
							if(P_SCLSH_SVC_YN == "N" && P_ACPS_LMT_YN == "N"){
								if(P_ASSO_SPDM_CD == "240000"){		//단독일때
									if(	P_EMRG_YN == "N"){	
										$("#P_RECM_ID").val("110000R");
									}else{   //긴급일때
										$("#P_RECM_ID").val("110000S");
									}
								}else if(P_ASSO_SPDM_CD == "240005"){		//공동일때
									if(	P_EMRG_YN == "N"){	
										$("#P_RECM_ID").val("110000T");
									}else{   //긴급일때
										$("#P_RECM_ID").val("110000U");
									}
								}
							}else if(P_SCLSH_SVC_YN == "N" && P_ACPS_LMT_YN == "Y"){
								if(P_ASSO_SPDM_CD == "240000"){		//단독일때
									if(	P_EMRG_YN == "N"){	
										$("#P_RECM_ID").val("110000V");
									}else{   //긴급일때
										$("#P_RECM_ID").val("110000W");
									}
								}else if(P_ASSO_SPDM_CD == "240005"){		//공동일때
									if(	P_EMRG_YN == "N"){	
										$("#P_RECM_ID").val("110000X");
									}else{   //긴급일때
										$("#P_RECM_ID").val("110000Y");
									}
								}
							}else if(P_SCLSH_SVC_YN == "Y" && P_ACPS_LMT_YN == "N"){
								if(P_ASSO_SPDM_CD == "240000"){		//단독일때
									if(	P_EMRG_YN == "N"){	
										$("#P_RECM_ID").val("110000Z");
									}else{   //긴급일때
										$("#P_RECM_ID").val("110000AA");
									}
								}else if(P_ASSO_SPDM_CD == "240005"){		//공동일때
									if(	P_EMRG_YN == "N"){	
										$("#P_RECM_ID").val("110000AB");
									}else{   //긴급일때
										$("#P_RECM_ID").val("110000AC");
									}
								}
							}else if(P_SCLSH_SVC_YN == "Y" && P_ACPS_LMT_YN == "Y"){
								if(P_ASSO_SPDM_CD == "240000"){		//단독일때
									if(	P_EMRG_YN == "N"){	
										$("#P_RECM_ID").val("110000AD");
									}else{   //긴급일때
										$("#P_RECM_ID").val("110000AE");
									}
								}else if(P_ASSO_SPDM_CD == "240005"){		//공동일때
									if(	P_EMRG_YN == "N"){	
										$("#P_RECM_ID").val("110000AF");
									}else{   //긴급일때
										$("#P_RECM_ID").val("110000AG");
									}
								}
							}	
						}
					}
				}
			}
		}else if(P_CONT_SECD == 2){
			if(P_CONT_MTCD == "10003"){
				if(P_SPLY_PRCE_M < 1000000){
					$("#P_RECM_ID").val("210003A");
				}else if(P_SPLY_PRCE_M > 1000000 && P_SPLY_PRCE_M <= 10000000){
					$("#P_RECM_ID").val("210003B");
				}else if(P_SPLY_PRCE_M > 10000000 && P_SPLY_PRCE_M <= 20000000){
					$("#P_RECM_ID").val("210003G");
				}else if(P_SPLY_PRCE_M > 20000000 && P_SPLY_PRCE_M <= 30000000 && P_WMAN_SCTY_CORP_YN == 'Y'){
					$("#P_RECM_ID").val("210003C");
				}else if(P_SPLY_PRCE_M > 30000000 && P_SPLY_PRCE_M <= 50000000 && P_WMAN_SCTY_CORP_YN == 'Y'){
					$("#P_RECM_ID").val("210003D");
				}else if(P_SPLY_PRCE_M > 20000000 && P_WMAN_SCTY_CORP_YN == 'N'){
					$("#P_RECM_ID").val("210003E");
				/*}else if(P_PVCT_RSN_NO == '5' || P_PVCT_RSN_NO == '6' && P_SPLY_PRCE_M > 20000000){
					$("#P_RECM_ID").val("210003F");*/
				}
			}else if(P_CONT_MTCD == "10000" || P_CONT_MTCD == "10001" || P_CONT_MTCD == "10002"){
				$("#P_RECM_ID").val("210000A");
			}
		}
	};
	
	chargerListAdd = function(usrId, emplNo,usrNm, deptCd, deptNm, ofpsCd, ofpsNm, telNo, emalAddr,chargerGbn){
		if(chargerGbn == "M"){
			$('#P_MNGR_CHRGR_ID').val(usrId);
			$('#P_MNGR_CHRGR_NM').val(usrNm);
		}else if(chargerGbn == "C"){
			$('#P_CHCK_CHRGR_NM').val(usrId);
			$('#P_CHCK_CHRGR_ID').val(usrNm);
		}
	};
	
	pageObj.itemAdd = function(){
		var copyRow = $("#itemHideTbdy").children().clone(true);
		
		copyRow.find("label[for='itemChoiseCbx']").attr("for","itemChoiseCbx"+$("#itemCount").val());
		copyRow.find("input[name='itemChoiseCbx']").attr("id","itemChoiseCbx"+$("#itemCount").val());
		copyRow.find("#P_ASTS_TYCD").attr({"name" : "P_ASTS_TYCD","id" : "P_ASTS_TYCD"+$("#itemCount").val()});
		copyRow.find("#P_ITEM_NO").attr({"name" : "P_ITEM_NO","id" : "P_ITEM_NO"+$("#itemCount").val()});
		copyRow.find("#itemSrchBtn").attr({"name" : "itemSrchBtn","id" : "itemSrchBtn"+$("#itemCount").val(), "value": $("#itemCount").val()});
		copyRow.find("#P_ITEM_NM").attr({"name" : "P_ITEM_NM","id" : "P_ITEM_NM"+$("#itemCount").val()});
		copyRow.find("#P_ITEM_DTL").attr({"name" : "P_ITEM_DTL","id" : "P_ITEM_DTL"+$("#itemCount").val()});
		copyRow.find("#P_ITEM_UNCD").attr({"name" : "P_ITEM_UNCD","id" : "P_ITEM_UNCD"+$("#itemCount").val()});
		copyRow.find("#P_STND_NM").attr({"name" : "P_STND_NM","id" : "P_STND_NM"+$("#itemCount").val()});
		copyRow.find("#P_ITEM_QTY").attr({"name" : "P_ITEM_QTY","id" : "P_ITEM_QTY"+$("#itemCount").val()});
		copyRow.find("#P_ESTT_UPRC").attr({"name" : "P_ESTT_UPRC","id" : "P_ESTT_UPRC"+$("#itemCount").val()});
		copyRow.find("#P_ESTT_PRCE").attr({"name" : "P_ESTT_PRCE","id" : "P_ESTT_PRCE"+$("#itemCount").val()});
		copyRow.find("#P_STAX_SECD").attr({"name" : "P_STAX_SECD","id" : "P_STAX_SECD"+$("#itemCount").val()});
		copyRow.find("#P_ITEM_STAX").attr({"name" : "P_ITEM_STAX","id" : "P_ITEM_STAX"+$("#itemCount").val()});
		copyRow.find("#P_ESTT_AMT").attr({"name" : "P_ESTT_AMT","id" : "P_ESTT_AMT"+$("#itemCount").val()});
		copyRow.find("#P_BDG_YR").attr({"name" : "P_BDG_YR","id" : "P_BDG_YR"+$("#itemCount").val()});
		copyRow.find("#P_BDG_MM").attr({"name" : "P_BDG_MM","id" : "P_BDG_MM"+$("#itemCount").val()});
		copyRow.find("#P_BSNS_CD").attr({"name" : "P_BSNS_CD","id" : "P_BSNS_CD"+$("#itemCount").val()});
		copyRow.find("#P_ACNT_CD").attr({"name" : "P_ACNT_CD","id" : "P_ACNT_CD"+$("#itemCount").val()});
		copyRow.find("#P_ACNT_ITEM_CD").attr({"name" : "P_ACNT_ITEM_CD","id" : "P_ACNT_ITEM_CD"+$("#itemCount").val()});
		copyRow.find("#P_DEPT_NO").attr({"name" : "P_DEPT_NO","id" : "P_DEPT_NO"+$("#itemCount").val()});
		copyRow.find("#P_ACNT_NM").attr({"name" : "P_ACNT_NM","id" : "P_ACNT_NM"+$("#itemCount").val()});
		copyRow.find("#budgetSrchBtn").attr({"name" : "budgetSrchBtn","id" : "budgetSrchBtn"+$("#itemCount").val(), "value": $("#itemCount").val()});
		
		copyRow.css({"display" : ""});
		$(" #itemHideTbdy").css({"display" : "none"});
		$(" #itemShowTbdy").append(copyRow);
		
		var itemCount = Number($("#itemCount").val()) + Number(1);
		$("#itemCount").val(itemCount);
	}
	
	staxSecdFn = function(obj){
		var node =$(obj).parent().parent().parent();
		var staxSecd = $(obj).val();
		var esttPrce = Number(FwkCmmnUtil.deleteComma(node.find("input[name='P_ESTT_PRCE']").val()));
		if(staxSecd == "4A" || staxSecd == "5A" || staxSecd == "6A" || staxSecd == "B0" || staxSecd == "C0" || staxSecd == "C1"){
			node.find("input[name='P_ITEM_STAX']").val("0");
		}else{
			node.find("input[name='P_ITEM_STAX']").val(FwkCmmnUtil.addComma(Math.round(esttPrce*0.1)));
		}
		esttAmtCalc(node.find("input[name='P_ESTT_PRCE']"));
	};
	pageObj.itemDel = function(){
		$("#itemShowTbdy input[name='itemChoiseCbx']").each(function(inx){
			if(this.checked) {
				$(this).parent().parent().remove();
			}
		});
	};
	
	contSecdSet = function(){
		$(".essfileList").html(essFileSet());
	}
	
	//필수첨부파일 세팅
	essFileSet = function(){
		$(".essfileList").empty();  

		//변함없을경우
		if( $("#contSecd").val() == $("#P_CONT_SECD").val() 
				&& $("#esttPrceM").val() == FwkCmmnUtil.deleteComma($("#P_ESTT_PRCE_M").val())
				&& $("#emrgYn").val() == $("#P_EMRG_YN").val()	
				&& $("#infBsnsYn").val() == $("#P_INF_BSNS_YN").val()){	
				$(".essfileShowList").show();  		
		//변화있을경우
		}else{
			$(".essfileShowList").hide();  
			var jsonData = {'P_CONT_SECD' : ''+ $("#P_CONT_SECD").val() +'','P_EMRG_YN' : ''+ $("#P_EMRG_YN").val() +'','P_FILE_PAGE_GBN' : ''+ $("#filePageGbn").val() +'','P_FILE_PAGE_GBN2' : ''+ $("#filePageGbn2").val() +'','P_BFAN_WAIT_NO' : ''+ $("#P_BFAN_WAIT_NO").val() +'' ,'P_ESTT_PRCE_M' : ''+ $("#P_ESTT_PRCE_M").val() +'','P_INF_BSNS_YN' : ''+ $("#P_INF_BSNS_YN").val() +''};
			var actionUrl = "/prpo/selectNoEssFileCode"; 
			
			FwkCmmnUtil.submitAjax(actionUrl, jsonData, function(res) {
				if(res.fileCodeList.length > 0){  
					$(".essfileList").html(essfileList(res.fileCodeList));
				}
			}); 
		}
	} 
	
	//파일을 첨부했을경우
	fileSet = function(obj){
		var node = $(obj).parent().parent();
		node.find("input[type=file]").prop("name","P_FILE");
		node.find("input[name=P_FILE_DOC_SECD]").prop("disabled", "");
	};
	
	//파일 수정버튼을 
	fileModBtn = function(obj){
		var td = $(obj).parent().parent();	//td
		td.hide();			//보여주는 td
		td.next().show();	// 다음td, 수정가능한 td
		td.next().find("input[type=file]").attr("name","P_FILE");
	};
	
	//파일 취소버튼을 
	fileCancleBtn = function(obj){
		var td = $(obj).parent().parent();	//td
		td.hide();			//수정가능한 td 숨기기
		td.prev().show();	// 이전 td, 보여주는 td
		td.find("input[type=file]").prop("name","");
		td.find("input[name=P_FILE_DOC_SECD]").prop("disabled", "disabled");
	};
	
	pageObj.chgContSecd = function(obj){
		var contSecdCd = $(obj).val();
		
		if(contSecdCd==0){
			$("#dlgdPlcNm").text("납품장소");
			$("#P_DLGD_PLC_NM").prop("placeholder","납품장소");
		}else if(contSecdCd == 1){
			$("#dlgdPlcNm").text("용역현장");
			$("#P_DLGD_PLC_NM").prop("placeholder","용역현장");
		}else if(contSecdCd == 2){
			$("#dlgdPlcNm").text("공사현장");
			$("#P_DLGD_PLC_NM").prop("placeholder","공사현장");
		}
	};
	
	pageObj.chgContMtcd = function(obj){
		var contMtcd = $(obj).val();
		
		if(contMtcd==10000 || contMtcd==10001 || contMtcd==10002){
			$("#contMtcdGbn").css("display","");
		}else {
			$("#contMtcdGbn").css("display","none");
		}
	};
	
	// 부서 조회 팝업
	pageObj.deptInqirePopup = function() {
		$("#popupFrm").one("submit", function() {
			window.open("", "deptPopup", "width=1400px,height=760px,toolbar=no,status=no,scrollbars=yes,resizeable=no,menubar=no,left=500,top=100");
			this.action = FwkCmmnUtil['contextPath']+'/comm/popup/deptList.do';
	        this.method = 'POST';
	        this.target = 'deptPopup';
	    }).trigger("submit");
	};
	
	// 품목 조회 팝업
	pageObj.itemInqirePopup = function(obj) {
		$("#itemSn").val($(obj).val());
		$("#popupFrm").one("submit", function() {
			window.open("", "itemPopup", "width=1400px,height=760px,toolbar=no,status=no,scrollbars=yes,resizeable=no,menubar=no,left=500,top=100");
			this.action = FwkCmmnUtil['contextPath']+'/comm/popup/itemList.do';
			this.method = 'POST';
			this.target = 'itemPopup';
		}).trigger("submit");
	};
	
	// 예산 조회 팝업
	pageObj.budgetInqirePopup = function(obj) {
		$("#itemSn").val($(obj).val());
		$("#popupFrm").one("submit", function() {
			window.open("", "budgetPopup", "width=750px,height=760px,toolbar=no,status=no,scrollbars=yes,resizeable=no,menubar=no,left=500,top=100");
			this.action = FwkCmmnUtil['contextPath']+'/comm/popup/budgetList.do';
			this.method = 'POST';
			this.target = 'budgetPopup';
		}).trigger("submit");
	};
	
	itemListAdd = function(llfNm, llfCd, slfNm, slfCd, itemNm, itemUncd,stndNm,itemNo){
		var sn  = $("#itemSn").val();
		var node = $("#P_ITEM_NM"+ sn).parent().next().next();
		$("#P_ITEM_NM"+ sn).val(itemNm);
		$("#P_ITEM_NM"+ sn).val(itemNm);
		node.find($("option")).each(function(){
		    if($(this).val()==itemUncd){
		      $(this).attr("selected","selected"); // attr적용안될경우 prop으로 
		    }
		  });
		$("#P_STND_NM" + sn).val(stndNm);
		$("#P_ITEM_NO" + sn).val(itemNo);
	};
	
	budgetListAdd = function(deptNm, deptNo, bsnsCd, bsnsNm, acntCd, acntNm, acntItemCd,rqstBlncAmt, bdgAmt, expsRqstAmt, useBdgAmt,bdgYr, bdgMm,ddtlbzId){
		var sn  = $("#itemSn").val();
		$("#P_DEPT_NO"+ sn).val(deptNo);
		$("#P_BSNS_CD"+ sn).val(bsnsCd);
		$("#P_ACNT_CD" + sn).val(acntCd);
		$("#P_ACNT_ITEM_CD" + sn).val(acntItemCd);
		$("#P_ACNT_NM" + sn).val(acntNm);
		$("#P_BDG_YR" + sn).val(bdgYr);
		$("#P_BDG_MM" + sn).val(bdgMm);
		
	}	
	//요구부서 삭제
	pageObj.deptInqireDelete = function() {
		$('#P_PRCH_DEPT_NM').val("");
		$('#P_PRCH_DEPT_NO').val("");
	};
	
	deptListAdd = function(deptNo, deptNm){
		$('#P_PRCH_DEPT_NM').val(deptNm);
		$('#P_PRCH_DEPT_NO').val(deptNo);
	}
	
	pageObj.contDeptFn = function(){
		var araDeptCd = $("select[name='P_ARA_DEPT_CD']").val();
		var prchchgrSecd = $(':radio[name="P_PRCH_CHRG_SECD"]:checked').val();

		$('#detailFrm input[name=P_ARA_DEPT_CD]').val(araDeptCd);
		$('#detailFrm input[name=P_PRCH_CHRG_SECD]').val(prchchgrSecd);

		var jsonData = $("#detailFrm").serializeObject();
		var actionUrl = "/prpo/selectCodeVal"; 
		// contDeptCodeval
		FwkCmmnUtil.submitAjax (actionUrl, jsonData 
				, function(res) {
			var PRCH_DEPT_NO = res.contDeptCodeval.PRCH_DEPT_NO;
			var PRCH_DEPT_NM = res.contDeptCodeval.PRCH_DEPT_NM;
			
			$("input[name='P_PRCH_DEPT_NO']").val(PRCH_DEPT_NO);
			$("input[name='P_PRCH_DEPT_NM']").val(PRCH_DEPT_NM);
		});
	};
	
	/**  
	 * <pre>
	 * 1. 개요 : 수량 단가 계산
	 * 2. 처리내용 : 
	 * 		- 계약품목정보 수량 단가 계산
	 * </pre>
	 */
	qyUntpcCalc = function(obj) {
		var qy = $(obj).closest('tr').find("input[name='P_ITEM_QTY']").val(); // 수량
		var amt = $(obj).closest('tr').find("input[name='P_ESTT_UPRC']").val(); // 단가
		if(qy != "" && amt != ""){
			var tot = Number(FwkCmmnUtil.deleteComma(qy)) * Number(FwkCmmnUtil.deleteComma(amt));
			$(obj).closest('tr').find("input[name='P_ESTT_PRCE']").val(tot);
			$(obj).closest('tr').find("input[name='P_ESTT_PRCE']").val(FwkCmmnUtil.addComma($(obj).closest('tr').find("input[name='P_ESTT_PRCE']").val()));
		}else{
			$(obj).closest('tr').find("input[name='P_ESTT_PRCE']").val("");
		}
	};	
	
	
	/**
	 * 추정가격 + 부가세 : 추정금액
	 * 
	 * @param obj
	 */
	esttAmtCalc = function(obj) {
		var prce = $(obj).closest('tr').find("input[name='P_ESTT_PRCE']").val(); // 추정가격
		var stax = $(obj).closest('tr').find("input[name='P_ITEM_STAX']").val(); // 부가세
		if(prce != "" && stax != ""){
			var tot = Number(FwkCmmnUtil.deleteComma(prce)) + Number(FwkCmmnUtil.deleteComma(stax));
			//추정금액
			$(obj).closest('tr').find("input[name='P_ESTT_AMT']").val(tot);
			$(obj).closest('tr').find("input[name='P_ESTT_AMT']").val(FwkCmmnUtil.addComma($(obj).closest('tr').find("input[name='P_ESTT_AMT']").val()));
			
		}else{
			$(obj).closest('tr').find("input[name='P_ESTT_AMT']").val("");
		}
	};	
	
	/**
	 * 페이지내에서 필요한 항목의 이벤트를 바인딩한다.
	 * 1. 목록이동버튼(#listBtn) 의 click 이벤트를 binding 한다.
	 *   - 목록이동버튼에 click 이벤트 발생시 사전공고현황 목록조회 함수를 호출한다.
	 * 2. 저장버튼(#saveBtn) 의 click 이벤트를 binding 한다.
	 *   - 저장버튼에 click 이벤트 발생시 사전공고현황 등록 함수를 호출한다.
	 */	
	pageObj.setEventHandler = function() {
		
		//목록버튼
		$("#listBtn").on("click", function() {
			if(!confirm("목록화면으로 이동하시겠습니까?")){
				return false;
			}
			pageObj.bfStndOpenPrcnList();
		});
		
		//취소버튼
		$("#cancelBtn").on("click", function() {
			if(!confirm("사전규격공개 수정을 취소하시겠습니까?")){
				return false;
			}
			pageObj.bfStndOpenPrcnDetail();
		});
		
		// 저장버튼
		$("#updtBtn").on("click", function() {

			//필수체크
			var checkData = eval(FwkMssageUtil.getMessage("bfanRegistForm.001"));   
			if(FwkCmmnUtil.validate("registFrm", checkData) == false) {
				return false;
			}
			
			//품목정보
			var checkData = eval(FwkMssageUtil.getMessage("itemRegistForm.001"));   
			if(FwkCmmnUtil.validate("itemShowTbdy", checkData) == false) {
				return false;
			}
			
			for(var i=0; i<$(".essfileShowList input[name='P_FILE']").length; i++){
				 if($(".essfileShowList input[name='P_FILE']").eq(i).val() == ""){
		        	alert("필수첨부파일을 등록해주세요.");
		        	return false;
		        }
			}
			
			/*var itemPrce_tot = 0;
			$.each($('input[name=P_ESTT_PRCE]'), function(key, value) {	//추정가격의 합
				itemPrce_tot += Number(FwkCmmnUtil.deleteComma($(value).val()));
			});
			
			if(itemPrce_tot != FwkCmmnUtil.deleteComma($("#P_ESTT_PRCE_M").val())){
				alert("품목 추정가격의 합계와 추정가격이 다릅니다.");
				return false;
			}*/
			
			var itemAmt_tot = 0;
			$.each($('input[name=P_ESTT_AMT]'), function(key, value) {	//추정금액의 합
				itemAmt_tot += Number(FwkCmmnUtil.deleteComma($(value).val()));
			});

			if(itemAmt_tot != FwkCmmnUtil.deleteComma($("#P_BSNS_BDG_AMT").val())){
				alert("품목 추정금액의 합계와 사업예산금액이 다릅니다.");
				return false;
			} 
			
			if(!confirm(FwkMssageUtil.getMessage("COM.CON.002", "사전규격공개", "저장"))){
				return false;
			}
			
			removeComma();
			
			$("[date]").each(function(){
				$(this).val($(this).val().replace( /-/g, ""));
			});
			pageObj.bfStndOpenUpdt();
		});
		
		// 감독원 검사원 검색버튼
		$("#mngrChrgrSrchBtn").on("click", function() {
			pageObj.chargerInqirePopup("M");
		});
		
		// 감독원 검사원 삭제버튼
		$("#mngrChrgrDelBtn").on("click", function() {
			pageObj.chargerDelete("M");
		});
		
		// 감독원 검사원 검색버튼
		$("#chkChrgrSrchBtn").on("click", function() {
			pageObj.chargerInqirePopup("C");
		});
		
		$("select[name='P_STAX_SECD']").on("change", function() {
			staxSecdFn(this);
		});
		
		// 감독원 검사원 삭제버튼
		$("#chkChrgrDelBtn").on("click", function() {
			pageObj.chargerDelete("C");
		});
		
		// 품목 추가버튼
		$("#itemAddBtn").on("click", function() {
			pageObj.itemAdd();
		});
		// 품목 삭제버튼
		$("#itemdelBtn").on("click", function() {
			pageObj.itemDel();
		});
		
		$("select[name='P_CONT_SECD']").on("change", function() {
			pageObj.chgContSecd(this);
			contSecdSet();
		});
		$("select[name='P_CONT_MTCD']").on("change", function() {
			pageObj.chgContMtcd(this);
			contSecdSet();
		});
		
		//추정가격
		$("#P_ESTT_PRCE_M").on("blur", function(){ 
			contSecdSet();
		});
		
		//긴급여부
		$("input:radio[name='P_EMRG_YN']").on("change", function(){
			contSecdSet();
		});
		
		// 계약부서 검색버튼
		$("#searchDeptBtn").on("click", function() {
			pageObj.deptInqirePopup();
		});
		
		// 계약부서 삭제버튼
		$("#deptDelBtn").on("click", function() {
			pageObj.deptInqireDelete();
		});

		// 품목 검색버튼
		$("button[name='itemSrchBtn']").on("click", function() {
			pageObj.itemInqirePopup(this);
		});
		// 예산 검색버튼
		$("button[name='budgetSrchBtn']").on("click", function() {
			pageObj.budgetInqirePopup(this);
		});
		
		$("select[name='P_ARA_DEPT_CD']").on("change", function() {
			pageObj.contDeptFn(); 
		}); 
		
		$("input:radio[name='P_PRCH_CHRG_SECD']").on("click", function() {
			pageObj.contDeptFn(); 
		}); 
		
		//사전규격공개여부
		$("input:radio[name='P_BF_STND_OPEN_YN']").on("click", function() {
			if($(this).val() == "Y"){ 
				$(".bfStndOpenTeSpan").show();
			}else{
				$(".bfStndOpenTeSpan").hide();
			}
		});
		
		// 첨부파일샘플
		$(document).on('click', 'button[name="filesample"]', function() {
			pageObj.fileSamplePopup();
		});
		
		
		//부가세 계산
		//추정금액 계산
		
		// 추정금액
		$("input[name='P_ESTT_AMT']").each(function(i){
			if(Number(FwkCmmnUtil.deleteComma($(this).val())) != Number(FwkCmmnUtil.deleteComma($("#P_ESTT_PRCE"+i).val())) + Number(FwkCmmnUtil.deleteComma($("#P_ITEM_STAX"+i).val()))){
				alert(i+1 + "번째 품목의 추정금액과 추정가격+부가세의 합이 다릅니다.");
				$(this).focus(); 
				return false;
			}	
		});
		
		// 계약품목 
		//추정금액 계산
		$(document).on('keyup', 'input[name=P_ESTT_PRCE]', function() {
			if($(this).val() != "") {
				$(this).val(FwkCmmnUtil.addComma($(this).val()));	
			}
			esttAmtCalc(this);
			staxSecdFn($(this).parent().parent().find("select[name='P_STAX_SECD']"));
		});
		
		$(document).on('keyup', 'input[name=P_ITEM_STAX]', function() {
			if($(this).val() != "") {
				$(this).val(FwkCmmnUtil.addComma($(this).val()));	
			}
			esttAmtCalc(this);
		});
		
		// 계약품목 금액 계산
		$(document).on('keyup', 'input[name=P_ITEM_QTY]', function() {
			qyUntpcCalc(this);
			esttAmtCalc(this);
		});
		
		$(document).on('keyup', 'input[name=P_ESTT_UPRC]', function() {
			if($(this).val() != "") {
				$(this).val(FwkCmmnUtil.addComma($(this).val()));	
			}
			qyUntpcCalc(this);
			esttAmtCalc(this);
			staxSecdFn($(this).parent().parent().find("select[name='P_STAX_SECD']"));
		});
		
	};
	
	/**
	 * window load
	 *
	 */
	$(function(){
		pageObj.setEventHandler();
		
		pageObj.chgContSecd($("#contSecd"));
		pageObj.chgContMtcd($("#contMtcd"));
		
		//제3자 단가 아닐때 보임
		if($("#P_CONT_MTCD").val() != "10004"){
			$(".essfileShowList").show();
		}
		
		recmId();
		fileEtcModify("updtFrm", "P_FILE_GRP_NO_ETC", "P_FILE_GRP_NO_NEW", "/ebid/bfStndOpenUpdt.do");
		/*fileModify("updtFrm", "P_FILE_GRP_NO", "P_FILE_GRP_NO_NEW", "/ebid/bfStndOpenUpdt.do");*/
	});
})();