/**
 * 평가관리 > 평가위원관리 > 평가위원평가진행현황 상세 - 평가위원
 *
 * <pre>
 * estm
 *    |_ cmtmMngProgCmtmDetail.js
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
	pageObj.cmtmMngProgList = function() {
		FwkCmmnUtil.submitForm("listFrm", "/estm/cmtmMngProgList.do"); 
	};
	
	
	// 선정차수 상세
	estmCmtmSlctNgrDetail = function(estmCmtmSlctNgr){
		$("#P_ESTM_CMTM_SLCT_NGR_CLICK").val(estmCmtmSlctNgr);
		$("#P_CMPL_YN").val("");
		FwkCmmnUtil.submitForm("detailFrm", "/estm/cmtmMngProgCmtmDetail.do");
	};
	
	// 평가위원선정최종 상세
	estmCmtmSlctCmplDetail = function(){
		$("#P_CMPL_YN").val("Y");
		FwkCmmnUtil.submitForm("detailFrm", "/estm/cmtmMngProgCmtmDetail.do");
	};

	
	
	// 평가위원 자동선별
	estmCmtmAutoSlct = function(INO_CMTM_SECD){
		
		$("#detailFrm input[name='P_INO_CMTM_SECD']").val(INO_CMTM_SECD);   // 내외부위원구분코드
		$("#detailFrm input[name='P_SLCT_SECD']").val("AUTO");   // 선별구분코드
		
		var jsonData = $("#detailFrm").serializeObject();
		var actionUrl = "/estm/estmCmtmAutoSlct";

		FwkCmmnUtil.submitAjax (actionUrl, jsonData
				, function(res) {
				alert("저장되었습니다.");
			FwkCmmnUtil.submitForm("detailFrm", "/estm/cmtmMngProgCmtmDetail.do");
		});
	};
	
	
	// 평가위원 재 자동선별
	estmCmtmReAutoSlct = function(INO_CMTM_SECD, gbn){
		
		console.log(":::::::::: 평가위원 우선순위재선정 [자동선별] ==> " + gbn);
		var estmCmtmSlctNgr = "";
		
		if($("#P_ESTM_CMTM_RE_SLCT_NGR").val() != ""){
			estmCmtmSlctNgr = $("#detailFrm input[name='P_ESTM_CMTM_RE_SLCT_NGR']").val();   // 재선정될 차수
			$("#detailFrm input[name='P_ESTM_CMTM_SLCT_NGR']").val(estmCmtmSlctNgr);   // 평가위원순위재선정
		}else{
			estmCmtmSlctNgr = $("#detailFrm input[name='P_ESTM_CMTM_SLCT_NGR']").val();
			$("#detailFrm input[name='P_ESTM_CMTM_RE_SLCT_NGR']").val(estmCmtmSlctNgr);   // 평가위원순위재선정
		}
		
		console.log("estmCmtmSlctNgr ==> " + estmCmtmSlctNgr);
		
		$("#detailFrm input[name='P_ESTM_PSCD']").val("A0041");   // 평가위원순위재선정
		$("#detailFrm input[name='P_INO_CMTM_SECD']").val(INO_CMTM_SECD);   // 내외부위원구분코드
		$("#detailFrm input[name='P_SLCT_SECD']").val("AUTO");   // 선별구분코드
		
		
		var jsonData = $("#detailFrm").serializeObject();
		var actionUrl = "/estm/estmCmtmReAutoSlct";

		FwkCmmnUtil.submitAjax (actionUrl, jsonData
				, function(res) {
				alert("저장되었습니다");
				
				var P_ESTM_CMTM_RE_SLCT_NGR = $("#detailFrm input[name='P_ESTM_CMTM_RE_SLCT_NGR']").val();   // 재선정될 차수
				
				var P_ESTM_CMTM_SLCT_NGR = $("#detailFrm input[name='P_ESTM_CMTM_SLCT_NGR']").val();   // 현재차수
				//alert("재선정될 차수 ==> " + P_ESTM_CMTM_RE_SLCT_NGR + "\n 현재차수 ==> " + P_ESTM_CMTM_SLCT_NGR);
				
				$("#P_PRIO_RNK_RE_SLCT_YN").val("");
				$("#P_ESTM_CMTM_RE_SLCT_NGR").val("");
				
				$("#detailFrm input[name='P_ESTM_CMTM_SLCT_NGR']").val(P_ESTM_CMTM_RE_SLCT_NGR);   // 평가위원선정차수
				FwkCmmnUtil.submitForm("detailFrm", "/estm/cmtmMngProgCmtmDetail.do");
		});
	};
	
	// 외부평가위원 추가
	pageObj.outEstmAdd = function(){
		
		$("#popupFrm input[name='P_ESTM_CMTM_CNT']").val($("#P_OUT_ESTM_CMTM_CNT").val());   // 평가위원수
		$("#popupFrm input[name='P_INO_CMTM_SECD']").val("OUT");   // 내외부위원구분코드
		$("#popupFrm input[name='setMulti']").val("Y");
		
		$("#popupFrm input[name='tbdy_id']").val("outShowTbdy");
		
		$("#popupFrm").one("submit", function() {
			window.open("", "estmCmtmListPopup", "width=950px,height=850px,toolbar=no,status=no,scrollbars=yes,resizeable=no,menubar=no,left=450,top=50");
			this.action = FwkCmmnUtil['contextPath']+'/comm/popup/estmCmtmList.do';
	        this.method = 'POST';
	        this.target = 'estmCmtmListPopup';
	    }).trigger("submit");
	};	
	
	// 외부평가위원 삭제
	pageObj.outEstmDel = function(){
		var estmCmtmNo = "";
		$("#outShowTbdy input[name='estmCbk']").each(function(inx){
			
			if(this.checked) {
				estmCmtmNo = $(this).parent().parent().parent().find($("input[name='P_ESTM_CMTM_NO']")).val();
				
				if($("#P_DELETE_ESTM_CMTM_NO").val()){
					$("#P_DELETE_ESTM_CMTM_NO").val($("#P_DELETE_ESTM_CMTM_NO").val()+","+estmCmtmNo);
				}else{
					$("#P_DELETE_ESTM_CMTM_NO").val(estmCmtmNo);
				}
				
				$(this).parent().parent().parent().remove();
			}
		});
	};
	
	// 내부평가위원 추가
	pageObj.innEstmAdd = function(){
		
		$("#popupFrm input[name='P_ESTM_CMTM_CNT']").val($("#P_INN_ESTM_CMTM_CNT").val());   // 평가위원수
		$("#popupFrm input[name='P_INO_CMTM_SECD']").val("INN");   // 내외부위원구분코드
		$("#popupFrm input[name='setMulti']").val("Y");
		
		$("#popupFrm input[name='tbdy_id']").val("innShowTbdy");
		
		$("#popupFrm").one("submit", function() {
			window.open("", "estmCmtmListPopup", "width=950px,height=850px,toolbar=no,status=no,scrollbars=yes,resizeable=no,menubar=no,left=450,top=50");
			this.action = FwkCmmnUtil['contextPath']+'/comm/popup/estmCmtmList.do';
	        this.method = 'POST';
	        this.target = 'estmCmtmListPopup';
	    }).trigger("submit");
		
	};
	
	
	// 내부평가위원 삭제
	pageObj.innEstmDel = function(){
		var estmCmtmNo = "";
		var count = 0;
		$("#innShowTbdy input[name='estmCbk']").each(function(inx){
			
			if(this.checked) {
				estmCmtmNo = $(this).parent().parent().parent().find($("input[name='P_ESTM_CMTM_NO']")).val();
				
				if($("#P_DELETE_ESTM_CMTM_NO").val()){
					$("#P_DELETE_ESTM_CMTM_NO").val($("#P_DELETE_ESTM_CMTM_NO").val()+","+estmCmtmNo);
				}else{
					$("#P_DELETE_ESTM_CMTM_NO").val(estmCmtmNo);
				}
				$(this).parent().parent().parent().remove();
			}
		});
		
		//선정된 평가위원 중에 삭제된 평가위원이 존재하는지 체크하여 존재하면 연산에 필요한 값에서 빼주는 코드
		count = Number($("#INN_CMTM_SLCT_CNT").val())- Number(count);
		$("#INN_CMTM_SLCT_CNT").val(count);
		
		
	};
	
	cmtmListAdd = function(slctCmtmCnt, arr_estm_cmtm_no, tbdy_id){
		var flag = true;

		$(arr_estm_cmtm_no).each(function(inx, item){
		
			if(flag == true){
				
				$("#ajaxCmtmForm input[name='P_ESTM_CMTM_NO']").val(arr_estm_cmtm_no[inx]);
				var actionUrl = "/estm/estmCmtmSetJson.do";
				var jsonData = $("#ajaxCmtmForm").serializeObject();
				
				FwkCmmnUtil.submitAjax (actionUrl, jsonData
						, function(res) {
					
					//alert("res ==> " + JSON.stringify(res.estmCmtmSetDetail));
					var resultCode = res.resultCode;
					if(resultCode == 1){   // 성공
						
						// 중복품목 체크
						var cmtmFlag =false;
						$("#" + tbdy_id + "input[name='P_ESTM_CMTM_NO']").each(function(){
							if($(this).val() == res.estmCmtmSetDetail.ESTM_CMTM_NO){
								cmtmFlag = true;
								//break;
							}
						});
						if(!cmtmFlag){
							$("#" + tbdy_id)
							.append($('<tr>').attr({'id':'new'})
								.append($('<td>').text(""))
								.append($('<td>').addClass("txt-center")
										.append($('<label>').addClass("component-checkbox")
												.append($('<input>').attr({'type':'checkbox','name':'estmCbk'}))
												.append($('<i>'))))
								.append($('<td>').addClass("txt-center").text(res.estmCmtmSetDetail.ESTM_CMTM_NM)
										.append($('<input>').attr({'type':'hidden','name':'P_ESTM_CMTM_NO'}).val(res.estmCmtmSetDetail.ESTM_CMTM_NO))
										.append($('<input>').attr({'type':'hidden','name':'P_ESTM_CMTM_NM'}).val(res.estmCmtmSetDetail.ESTM_CMTM_NM))
										.append($('<input>').attr({'type':'hidden','name':'P_INO_CMTM_SENM'}).val(res.estmCmtmSetDetail.INO_CMTM_SECD_NM))
										.append($('<input>').attr({'type':'hidden','name':'P_LLF_SECD'}).val(res.estmCmtmSetDetail.LLF_SECD))
										.append($('<input>').attr({'type':'hidden','name':'P_LLF_NM'}).val(res.estmCmtmSetDetail.LLF_NM))
										.append($('<input>').attr({'type':'hidden','name':'P_CNTN_SECD'}).val(res.estmCmtmSetDetail.CNTN_SECD))
										.append($('<input>').attr({'type':'hidden','name':'P_CNTN_NM'}).val(res.estmCmtmSetDetail.CNTN_SECD_NM))
										.append($('<input>').attr({'type':'hidden','name':'P_MLF_SECD'}).val(res.estmCmtmSetDetail.MLF_SECD))
										.append($('<input>').attr({'type':'hidden','name':'P_MLF_NM'}).val(res.estmCmtmSetDetail.MLF_SECD_NM))
										.append($('<input>').attr({'type':'hidden','name':'P_SLF_SECD'}).val(res.estmCmtmSetDetail.SLF_SECD))
										.append($('<input>').attr({'type':'hidden','name':'P_SLF_NM'}).val(res.estmCmtmSetDetail.SLF_SECD_NM))
										.append($('<input>').attr({'type':'hidden','name':'P_CP_NO'}).val(res.estmCmtmSetDetail.CP_NO))
										.append($('<input>').attr({'type':'hidden','name':'P_EMAL'}).val(res.estmCmtmSetDetail.EMAL))
										)
								.append($('<td>').addClass("txt-center").text(res.estmCmtmSetDetail.INO_CMTM_SECD_NM))
								.append($('<td>').text(res.estmCmtmSetDetail.CP_NO))
								.append($('<td>').text(res.estmCmtmSetDetail.EMAL))
								.append($('<td>').addClass("txt-center").text(""))
								.append($('<td>').text(res.estmCmtmSetDetail.LLF_SECD_NM))
								.append($('<td>').text(res.estmCmtmSetDetail.MLF_SECD_NM))
								.append($('<td>').text(res.estmCmtmSetDetail.SLF_SECD_NM))
								.append($('<td>').addClass("txt-center").text(""))
								.append($('<td>').addClass("txt-center").text(""))
								.append($('<td>').addClass("txt-center").text(""))
								.append($('<td>').addClass("txt-center").text(""))
							);
						}
					}
				});
				
			}
		});
		
	};
	
	
	// 평가위원 자동선별
	estmCmtmRegist = function(INO_CMTM_SECD,TBODY_ID){
		
		var cnt = 0;
		var showTbdyId = TBODY_ID;
		var ESTM_CMTM_CNT = "";
		var CMTM_SLCT_CNT = "";
		
		ESTM_CMTM_CNT = $("#" + INO_CMTM_SECD +  "_ESTM_CMTM_CNT").val(); // 평가위원수
		CMTM_SLCT_CNT = $("#" + INO_CMTM_SECD +  "_CMTM_SLCT_CNT").val(); // 현재 선정된 평가위원 수
		
		if(showTbdyId == "fixShowTbdy") {
			
			ESTM_CMTM_CNT = $("#FIX_ESTM_CMTM_CNT").val(); // 지정평가위원수
			CMTM_SLCT_CNT = $("#" + INO_CMTM_SECD +  "_CMTM_HNDW_REG_CNT").val(); // 현재 선정된 지정평가위원 수
		}
		
		$("#" + showTbdyId + " input[name='estmCbk']").each(function(inx){
		/*$("#" + showTbdyId + " #new").each(function(inx){*/
			cnt++;
		});

		//console.log("ESTM_CMTM_CNT : " + ESTM_CMTM_CNT + " CMTM_SLCT_CNT : " + CMTM_SLCT_CNT);
		
		//CMTM_SLCT_CNT = Number(CMTM_SLCT_CNT)+ Number(cnt);
		
		//alert("CMTM_SLCT_CNT :: " + CMTM_SLCT_CNT);
		if(Number(ESTM_CMTM_CNT) < Number(cnt) ) {
			alert("평가위원을 초과 선정할 수 없습니다.");
		}else {
		
			$("#detailFrm input[name='P_INO_CMTM_SECD']").val(INO_CMTM_SECD);   // 내외부위원구분코드
			$("#detailFrm input[name='P_SLCT_SECD']").val("HNDW_REG");   // 선별구분코드
			
			var jsonData = $("#detailFrm").serializeObject();
			var actionUrl = "/estm/estmCmtmRegist";
	
			FwkCmmnUtil.submitAjax (actionUrl, jsonData
					, function(res) {
					alert("저장되었습니다.");
				FwkCmmnUtil.submitForm("detailFrm", "/estm/cmtmMngProgCmtmDetail.do");
			});
		}
	};
	
	// 지정평가위원 추가
	pageObj.fixEstmAdd = function(){

		$("#popupFrm input[name='P_ESTM_CMTM_CNT']").val($("#P_OUT_ESTM_CMTM_CNT").val());   // 평가위원수
		$("#popupFrm input[name='P_INO_CMTM_SECD']").val("OUT");   // 내외부위원구분코드
		$("#popupFrm input[name='setMulti']").val("Y");
		
		$("#popupFrm input[name='tbdy_id']").val("fixShowTbdy");
		
		$("#popupFrm").one("submit", function() {
			window.open("", "estmCmtmListPopup", "width=950px,height=850px,toolbar=no,status=no,scrollbars=yes,resizeable=no,menubar=no,left=450,top=50");
			this.action = FwkCmmnUtil['contextPath']+'/comm/popup/estmCmtmList.do';
	        this.method = 'POST';
	        this.target = 'estmCmtmListPopup';
	    }).trigger("submit");
	};
	
	// 지정평가위원 삭제
	pageObj.fixEstmDel = function(){
		var estmCmtmNo = "";
		var count = 0;
		$("#fixShowTbdy input[name='estmCbk']").each(function(inx){
			if(this.checked) {
				if($(this).parent().parent().parent().attr('id') == "new") {
				}else{
					count++;
				}
				estmCmtmNo = $(this).parent().parent().parent().find($("input[name='P_ESTM_CMTM_NO']")).val();
				
				if($("#P_DELETE_ESTM_CMTM_NO").val()){
					$("#P_DELETE_ESTM_CMTM_NO").val($("#P_DELETE_ESTM_CMTM_NO").val()+","+estmCmtmNo);
				}else{
					$("#P_DELETE_ESTM_CMTM_NO").val(estmCmtmNo);
				}
				$(this).parent().parent().parent().remove();
			}
		});
		
		//선정된 평가위원 중에 삭제된 평가위원이 존재하는지 체크하여 존재하면 연산에 필요한 값에서 빼주는 코드
		count = Number($("#OUT_CMTM_HNDW_REG_CNT").val())- Number(count);
		$("#OUT_CMTM_HNDW_REG_CNT").val(count);
	};
	
	// 우선순위재선정
	pageObj.estmCmtmPrioRnkReSlct = function(){
		var jsonData = $("#detailFrm").serializeObject();
		var actionUrl = "/estm/estmCmtmPrioRnkReSlct";
		
		FwkCmmnUtil.submitAjax (actionUrl, jsonData
				, function(res) {
			FwkCmmnUtil.submitForm("detailFrm", "/estm/cmtmMngProgCmtmDetail.do");
		});
	};
	
	
	// 평가위원 선정여부 - 선정/비선정
	pageObj.cmtmSlctYnUpdt = function(obj, estmCmtmSlctNgr, estmCmtmNo, inoCmtmSecd, yn){
		var RMK = $(obj).parent().parent().find($("input[name='P_RMK']")).val();   // 비고 - 비선정사유
		
		if(yn == "N"){
			if(RMK == "" || RMK == null){
				alert("비선정 사유를 비고란에 입력하세요.");				
				$(obj).parent().parent().find($("input[name='P_RMK']")).focus();
				return; 
			}
		}else if(yn == "C"){
			// 선정취소
			RMK = "";
			yn = "";
			
		}else{
			
		}
		
		/**
		 * 2021-05-17 손연우
		 * 선정인 경우 평가위원수 초과인지 여부 체크 로직
		 */
		
		var rnkFlag = "T";
		var tbdyId = "";
		var cnt;
		var slctCnt;
		if(inoCmtmSecd == "OUT" && yn == "Y") {
			tbdyId = "outShowTbdy";
			cnt  = Number($("#OUT_ESTM_CMTM_CNT").val()) - Number($("#FIX_ESTM_CMTM_CNT").val());
			slctCnt = Number($("#OUT_CMTM_SLCT_CNT").val()) - Number($("#OUT_CMTM_HNDW_REG_CNT").val());	//선택된외부 - 지정평가
		}else if(inoCmtmSecd == "INN" && yn == "Y") {
			tbdyId = "innShowTbdy";
			cnt  = Number($("#INN_ESTM_CMTM_CNT").val());
			slctCnt = Number($("#INN_CMTM_SLCT_CNT").val());
		}
		
		if( yn == "Y" ) {
			$("#"+ tbdyId + " [id*='yBtn']").each(function(inx){
				console.log("inx :" + inx + " estmCmtmNo :" + estmCmtmNo + " P_ESTM_CMTM_NO :: " + $(this).parent().parent().find($("input[name='P_ESTM_CMTM_NO']")).val());
				if(inx == 0 && ($(this).parent().parent().find($("input[name='P_ESTM_CMTM_NO']")).val() == estmCmtmNo)) {
					if(slctCnt >= cnt){
						alert("평가위원 선정이 초과되었습니다.");
						rnkFlag = "F";
						return false;
					}else{
						return false;
					}
				}else {
					alert("우선순위가 빠른 평가위원의 선정여부를 먼저 선정해주세요.");
					rnkFlag = "F";
					return false;
				}
			});
		}
			
		if( rnkFlag == "F") {
			return false;
		}
		
		
		$("#estmCmtmFrm input[name='P_ESTM_CMTM_SLCT_NGR']").val(estmCmtmSlctNgr);   // 내외부위원구분코드
		$("#estmCmtmFrm input[name='P_ESTM_CMTM_NO']").val(estmCmtmNo);   // 평가위원번호 
		$("#estmCmtmFrm input[name='P_SLCT_YN']").val(yn);   // 선정구분코드
		$("#estmCmtmFrm input[name='P_INO_CMTM_SECD']").val(inoCmtmSecd);   // 내외부위원구분코드
		$("#estmCmtmFrm input[name='P_RMK']").val(RMK);   // 비고
		
		var jsonData = $("#estmCmtmFrm").serializeObject();
		var actionUrl = "/estm/estmCmtmSlctYnUpdt";

		FwkCmmnUtil.submitAjax (actionUrl, jsonData
				, function(res) {
				alert("저장하였습니다.");
			FwkCmmnUtil.submitForm("detailFrm", "/estm/cmtmMngProgCmtmDetail.do");
		});
		
	};
	
	
	// 평가진행상태코드 update
	pageObj.estmPscdUpdt = function(){

		var jsonData = $("#detailFrm").serializeObject();
		var actionUrl = "/estm/estmPscdUpdt";

		FwkCmmnUtil.submitAjax (actionUrl, jsonData
				, function(res) {
			FwkCmmnUtil.submitForm("detailFrm", "/estm/cmtmMngProgCmtmDetail.do");
		});
		
	};
	
	// 평가위원선정완료여부 update
	pageObj.estmCmtmSlctCmplYnUpdt = function(){

		var jsonData = $("#rsdnNoForm").serializeObject();
		var actionUrl = "/estm/estmCmtmSlctCmplYnUpdt";

		FwkCmmnUtil.submitAjax (actionUrl, jsonData
				, function(res) {
			$("#P_CMPL_YN").val("Y");   // 평가위원선정완료 후 최종탭으로 이동
			FwkCmmnUtil.submitForm("detailFrm", "/estm/cmtmMngProgCmtmDetail.do");
		});
		
	};
	
	
	// 버튼 활성화 처리
	pageObj.btnEnableProc = function(){
		var ESTM_PSCD = $("#P_ESTM_PSCD").val();
		var ESTM_CMTM_SLCT_NGR = $("#P_ESTM_CMTM_SLCT_NGR").val();
		
		if(ESTM_PSCD == "A001"){
			$("#estmCmtmPrioRnkSlctRqstBtn").css("display", "");
		}
		else if(ESTM_PSCD == "A004" && ESTM_CMTM_SLCT_NGR > 1 ){
			//$("#estmCmtmPrioRnkSlctRqstBtn").css("display", "");
			//$("#prioRnkReSlct").css("display", "none");
		}
		else if($("#P_PRIO_RNK_RE_SLCT_YN").val() == "Y"){
			$("#estmCmtmPrioRnkSlctRqstBtn").css("display", "");
			$("#prioRnkReSlct").css("display", "none");
		}else if(ESTM_PSCD == "A0041"){
			$("#estmCmtmPrioRnkSlctRqstBtn").css("display", "");
		}		
	};
	
	
	
	/**
	 * 페이지내에서 필요한 항목의 이벤트를 바인딩한다.
	 * 1. 목록버튼(#listBtn) 의 click 이벤트를 binding 한다.
	 */	
	pageObj.setEventHandler = function() {
		
		// 목록버튼
		$("#listBtn").on("click", function() {
			pageObj.cmtmMngProgList();
		});
		
		// 내부평가위원 추가
		$("#innEstmAddBtn").on("click", function() {
			pageObj.innEstmAdd();
		});
		
		// 내부평가위원 삭제
		$("#innEstmDelBtn").on("click", function() {
			pageObj.innEstmDel();
		});
		
		// 평가위원우선순위선정요청
		$("#estmCmtmPrioRnkSlctRqstBtn").on("click", function() {

			$("#detailFrm input[name='P_ESTM_PSCD']").val("A003");   // 평가위원순위선정요청
			
			if(!confirm("평가위원우선순위선정요청 하시겠습니까?")){
				return false; 
			}
			
			pageObj.estmPscdUpdt();
			
		});
		
		// 평가위원선정완료
		$("#estmCmtmSlctCmpl").on("click", function() {
			
			if(!confirm("평가위원선정완료 하시겠습니까?")){
				return false;
			}
			
			pageObj.estmCmtmSlctCmplYnUpdt();
			
		});
		
		// 우선순위재선정
		$("#prioRnkReSlct").on("click", function() {

			$("#detailFrm input[name='P_PRIO_RNK_RE_SLCT_YN']").val("Y");
			
			if(!confirm("우선순위재선정을 하시겠습니까?")){
				return false;
			}
			
			pageObj.estmCmtmPrioRnkReSlct();
		});
		
		
	};

	 
	/**
	 * window load
	 *
	 */
	$(function() {		
		pageObj.setEventHandler();
		
		pageObj.btnEnableProc();   // 버튼 활성화 처리
		
	});
})();