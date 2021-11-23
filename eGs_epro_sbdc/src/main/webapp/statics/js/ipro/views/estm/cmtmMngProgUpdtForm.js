/**
 * 평가관리 > 평가위원관리 > 평가위원평가진행현황 수정
 *
 * <pre>
 * estm
 *    |_ cmtmMngProgUpdtForm.js
 * 
 * </pre>
 * @date : 2021. 04. 29.
 * @version : 1.0
 * @author : 은우소프트
 */
(function() {

	/**
	 * Default Constructor
	 */
	pageObj = {};

	var defaultFrm = "updtFrm";


	pageObj.cmtmMngProgDetail = function() {
		FwkCmmnUtil.submitForm("detailFrm", "/estm/cmtmMngProgDetail.do");
	};

	pageObj.estmProgUpdt = function() {
		fileUploadStart();
	};
	
	
	// 파일 업로드
	fileUploadStart = function() {
		// 첨부파일이 존재하는 경우만 업로드
//		if (RAONKUPLOAD.GetTotalFileCount("upload") == 0) {
//			FwkCmmnUtil.submitForm("updtFrm", "/estm/estmProgUpdt.do");
//        } else {
//            //업로드에서 추가된 파일이 정상으로 넘어오면 파일전송 이벤트를 발생시킵니다.
//        	RAONKUPLOAD.Transfer("upload");
//        }
		RAONKUPLOAD.Transfer("uploadView1");
	};
	
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
	
	// 담당자 삭제
	pageObj.chargerInqireDelete = function(chargerGbn) {
		if(chargerGbn == "estmChrgr"){
			// 평가담당자
			$("#P_ESTM_CHRGR_NM").val("");
			$("#P_ESTM_CHRGR_ID").val("");
			
			$("#P_ESTM_CHRG_DEPT_NM").val("");
			$("#P_ESTM_CHRG_DEPT_NO").val("");
			
		}else if(chargerGbn == "prioRnkSlctPe"){
			// 우선순위선정자
			$("#P_PRIO_RNK_SLCT_PE_NM").val("");
			$("#P_PRIO_RNK_SLCT_PE_ID").val("");
			
			$("#P_PRIO_RNK_SLCT_DEPT_NM").val("");
			$("#P_PRIO_RNK_SLCT_DEPT_NO").val("");
		}
	};
	
	chargerListAdd = function(usrId, emplNo,usrNm, deptCd, deptNm, ofpsCd, ofpsNm, telNo, emalAddr, chargerGbn){
		if(chargerGbn == "estmChrgr"){
			// 평가담당자
			$("#P_ESTM_CHRGR_NM").val(usrNm);
			$("#P_ESTM_CHRGR_ID").val(usrId);
			
			$("#P_ESTM_CHRG_DEPT_NM").val(deptNm);
			$("#P_ESTM_CHRG_DEPT_NO").val(deptCd);
			
		}else if(chargerGbn == "prioRnkSlctPe"){
			// 우선순위선정자
			$("#P_PRIO_RNK_SLCT_PE_NM").val(usrNm);
			$("#P_PRIO_RNK_SLCT_PE_ID").val(usrId);
			
			$("#P_PRIO_RNK_SLCT_DEPT_NM").val(deptNm);
			$("#P_PRIO_RNK_SLCT_DEPT_NO").val(deptCd);
		}
	};
	
	// 평가정보불러오기 팝업
	pageObj.estmInfoListPopup = function() {
		
		$("#popupFrm input[name='P_E006_YN']").val("Y");
		
		$("#popupFrm").one("submit", function() {
			window.open("", "estmInfoListPopup", "width=1000px,height=760px,toolbar=no,status=no,scrollbars=yes,resizeable=no,menubar=no,left=250,top=100");
			this.action = FwkCmmnUtil['contextPath']+'/comm/popup/estmInfoList.do';
			this.method = 'POST';
			this.target = 'estmInfoListPopup';
		}).trigger("submit");
	};
	
	setEstmInfoAdd = function(estmAnncNo, estmAnncNm){
		$("#P_ESTM_INFO_CNTC_NO").val(estmAnncNo);
		//$("#P_ESTM_NM").val(estmAnncNm);
	};
	
	// 평가절차 평가추가
	pageObj.estmAdd = function(showTbdy, hidTbdy){		
		
		var $selectEstmProcdSecd = $('#selectEstmProcdSecdFrm').find('select[name=P_ESTM_PROCD_SECD]').clone();   // 평가절차구분코드
		var $selectEstrSecd = $('#selectEstrSecdFrm').find('select[name=P_ESTR_SECD]').clone();   // 평가자구분
		
		$("#" + showTbdy)
			.append($('<tr>')
				.append($('<td>').addClass("txt-center")
					.append($('<label>').addClass("component-checkbox")
						.append($('<input>').attr({'type':'checkbox','name':'estmCbx'}))
						.append($('<i>'))))
				.append($('<td>')
					.append($selectEstmProcdSecd.removeAttr('id')))
				.append($('<td>')
					.append($selectEstrSecd.removeAttr('id')))
				.append($('<td>')
					.append($('<input>').attr({'type':'text','name':'P_ESTM_PROCD_NM','maxlength':'600'}).addClass('component-input w100')))
				.append($('<td>')
					.append($('<div>').addClass('area-calen')
						.append($('<div>').addClass('component-calen')
							.append($('<div>').addClass('data-calen')
								.append($('<input>').attr({'type':'text','name':'P_ESTM_PROCD_ST_DE', 'date':''}).css({'width':'100px'}).addClass('component-input'))))
						.append($('<div>').addClass('component-time')
							.append($('<input>').attr({'type':'text','name':'P_ESTM_PROCD_ST_HH', 'maxlength':'2', 'numeric':''}).addClass('component-input'))
							.append($('<em>').addClass('time-bar').text(" : "))
							.append($('<input>').attr({'type':'text','name':'P_ESTM_PROCD_ST_MM', 'maxlength':'2', 'numeric':''}).addClass('component-input'))
						)))
				.append($('<td>')
						.append($('<div>').addClass('area-calen')
							.append($('<div>').addClass('component-calen')
								.append($('<div>').addClass('data-calen')
									.append($('<input>').attr({'type':'text','name':'P_ESTM_PROCD_END_DE', 'date':''}).css({'width':'100px'}).addClass('component-input'))))
								.append($('<div>').addClass('component-time')
									.append($('<input>').attr({'type':'text','name':'P_ESTM_PROCD_END_HH', 'maxlength':'2', 'numeric':''}).addClass('component-input'))
									.append($('<em>').addClass('time-bar').text(" : "))
									.append($('<input>').attr({'type':'text','name':'P_ESTM_PROCD_END_MM', 'maxlength':'2', 'numeric':''}).addClass('component-input'))
								)))
					
						
		);
		
		setDatePicker();		
	};
	
	// 평가절차 평가삭제
	pageObj.estmDel = function(){
		$("#estmShowTbdy input[name='estmCbx']").each(function(inx){
			if(this.checked) {
				$(this).parent().parent().parent().remove();
			}
		});
	};
	
	// 평가구분에 따른 평가절차 조회
	pageObj.estmSecdEvent = function(obj){
		
		var estm_secd = $(obj).val();
		var showTbdy = "estmShowTbdy";
		var $selectEstrSecd = $('#selectEstrSecdFrm').find('select[name=P_ESTR_SECD]').clone();   // 평가자구분
		
		$("#" + showTbdy).empty();
		
		var estmObjSecd = "";   // 평가대상구분
		var mxmnScrExcpYn = "";   // 최고/최저점제외여부	
		
		$("#ajaxForm input[name='P_ESTM_SECD']").val(estm_secd);
		var actionUrl = "/estm/estmSecdProcdSetJson.do";
		var jsonData = $("#ajaxForm").serializeObject();
		
		FwkCmmnUtil.submitAjax (actionUrl, jsonData
				, function(res) {
			
//			alert("res ==> " + JSON.stringify(res.estmSecdProcdSetList));
			var resultCode = res.resultCode;
			
			if(resultCode == 1){   // 성공
				
				for (var i = 0; i < res.estmSecdProcdSetList.length; i++) {
					
					var procdInfo = res.estmSecdProcdSetList[i];
					
					estmObjSecd = procdInfo.ESTM_OBJ_SECD[0];
					mxmnScrExcpYn = procdInfo.MXMN_SCR_EXCP_YN[0];
					
					$("#" + showTbdy)
					.append($('<tr>')
						.append($('<td>').addClass("txt-center").text(Number(i+1)))
						.append($('<td>').addClass("txt-center").text(procdInfo.ESTM_PROCD_SECD_NM)
							.append($('<input>').attr({'type':'hidden','name':'P_ESTM_PROCD_SECD'}).val(procdInfo.ESTM_PROCD_SECD))
							.append($('<input>').attr({'type':'hidden','name':'P_ESTM_FRM_NO'}).val(procdInfo.ESTM_FRM_NO)))
						.append($('<td>')
							.append($('<select>').attr({'name':'P_ESTR_SECD'}).addClass('component-select w85').append($selectEstrSecd.html())))
						.append($('<td>')
							.append($('<input>').attr({'type':'text','name':'P_ESTM_PROCD_NM','maxlength':'600'}).addClass('component-input w100').val(procdInfo.ESTM_PROCD_NM)))
						.append($('<td>')
							.append($('<div>').addClass('area-calen')
								.append($('<div>').addClass('component-calen')
									.append($('<div>').addClass('data-calen')
										.append($('<input>').attr({'type':'text','name':'P_ESTM_PROCD_ST_DE', 'date':''}).css({'width':'100px'}).addClass('component-input'))))	
								.append($('<div>').addClass('component-time')
									.append($('<input>').attr({'type':'text','name':'P_ESTM_PROCD_ST_HH', 'maxlength':'2', 'numeric':'', 'onkeyup':'fnTimeHHChk(this);'}).addClass('component-input'))
									.append($('<em>').addClass('time-bar').text(" : "))
									.append($('<input>').attr({'type':'text','name':'P_ESTM_PROCD_ST_MM', 'maxlength':'2', 'numeric':'', 'onkeyup':'fnTimeMMChk(this);'}).addClass('component-input'))
								)))
						.append($('<td>')
								.append($('<div>').addClass('area-calen')
									.append($('<div>').addClass('component-calen')
										.append($('<div>').addClass('data-calen')
											.append($('<input>').attr({'type':'text','name':'P_ESTM_PROCD_END_DE', 'date':''}).css({'width':'100px'}).addClass('component-input'))))
										.append($('<div>').addClass('component-time')
											.append($('<input>').attr({'type':'text','name':'P_ESTM_PROCD_END_HH', 'maxlength':'2', 'numeric':'', 'onkeyup':'fnTimeHHChk(this);'}).addClass('component-input'))
											.append($('<em>').addClass('time-bar').text(" : "))
											.append($('<input>').attr({'type':'text','name':'P_ESTM_PROCD_END_MM', 'maxlength':'2', 'numeric':'', 'onkeyup':'fnTimeMMChk(this);'}).addClass('component-input'))
										)))
					);
				}
			}
		});
		
		$("select[name='P_ESTM_OBJ_SECD']").val(estmObjSecd);
		$("input[name='P_MXMN_SCR_EXCP_YN']:radio[value='" + mxmnScrExcpYn + "']").prop("checked", true);
		
		setDatePicker();
	};
	
	// 심사위원 서명파일 추가 버튼
	fileRowAdd = function() {
		$("#signFileTbody")
			.append($('<tr>')
					.append($('<th>').css({'width':'20%'})
							.append($('<input>').attr({'type':'text', 'name':'P_FILE_DOC_NM'}).addClass('component-input w100'))
							.append($('<input>').attr({'type':'hidden', 'name':'P_FILE_DOC_SECD'}).val("SIGN"))
							.append($('<input>').attr({'type':'hidden', 'name':'P_TSK_VKEY1'}).val(""))
					)
					.append($('<td>')
								.append($('<input>').attr({'type':'file', 'name':'P_SIGN_FILE', 'onchange':'fileChange(this);'}).css({'width':'80%'}))
								.append($('<input>').attr({'type':'hidden', 'name':'ESTM_FSCD', 'disabled':'disabled'}).val("SIGN"))
								.append($('<button>').attr({'type':'button', 'onclick':'fileRowDel(this);'}).addClass('component-button-s type-del').css({'float':'right'}).text("삭제"))
							)
						);
	};
	
	fileRowDel = function(obj){
		$(obj).closest('tr').remove();	
	};
	
	fileChange = function(obj){
		var fullFile = $(obj).val().substring($(obj).val().lastIndexOf("\\")+1);
		var fileNameSplit = fullFile.split('.');
		var fileName = fileNameSplit[0];
		$(obj).parent().parent().find("input[name='P_FILE_DOC_NM']").val(fileName);
	};
	
	fileDel = function(obj, fileSn){
		if($("#P_DELETE_FILE_SN").val()){
			$("#P_DELETE_FILE_SN").val($("#P_DELETE_FILE_SN").val()+","+fileSn);
		}else{
			$("#P_DELETE_FILE_SN").val(fileSn);
		}
		$(obj).parent().parent().remove();
	};
	
	// 평가분야구분 조회
	pageObj.estmSpheSecd = function(){
		
		var P_ESTM_SPHE_SECD_DATA = $("#P_ESTM_SPHE_SECD_DATA").val();
		
		var actionUrl = "/estm/getEstmSpheSecd";
		var jsonData = $("#registFrm").serializeObject();
		
		var optionInit = "<option value=\"\" >선택</option>";
		$("#P_ESTM_SPHE_SECD").append(optionInit);
		
		FwkCmmnUtil.submitAjaxNoLoading(actionUrl, jsonData, function(res) {
			var option = "";
			var list = res.listEstmSpheSecd;
			
			$.each(list, function(inx, item) {
				
				if(P_ESTM_SPHE_SECD_DATA == item.ESTM_SPHE_SECD){
					option += "<option value='" + item.ESTM_SPHE_SECD + "' + selected='selected'>" + item.ESTM_SPHE_SENM + "</option>";
				}else{
					option += "<option value='" + item.ESTM_SPHE_SECD + "'>" + item.ESTM_SPHE_SENM + "</option>";					
				}
			});
			
			$("#P_ESTM_SPHE_SECD").append(option);
		});
	};
	
	
	/**  
	 * <pre>
	 * 1. 개요 : 파일 다운로드
	 * 2. 처리내용 : 
	 * 		공고게시
	 *  	
	 * </pre>
	 */
	download = function(fileGrpNo, fileSn) {
		$("#downloadFrm input[name='P_FILE_SN']").val(fileSn);
		$("#downloadFrm input[name='P_FILE_GRP_NO']").val(fileGrpNo);
		FwkCmmnUtil.submitForm("downloadFrm", "/comm/download.do" );
	};
	
	// ESTM_CMTM_SLCT_MTHD (평가위원선정방법)
	pageObj.innCmtmSlctMthdEvent = function(obj){
		
		var innCmtmSlctMthdEvent = "";
		if($(obj).val() != null){
			innCmtmSlctMthdEvent = $(obj).val();
		}else{
			innCmtmSlctMthdEvent = $("select[name='P_INN_CMTM_SLCT_MTHD_SECD']").val();
		}
		
		if(innCmtmSlctMthdEvent == "A"){
			
			// A - 자체선정 : 배수X, 지정평가위원수X, 우선순위선정자X
			$("#P_INN_ESTM_CMTM_CNT").attr("disabled", false);   // 내부평가위원수
			$("#P_INN_ESTM_CMTM_CNT").css("background", "#ffffff");
			
			$("#P_INN_ESTM_CMTM_TMES").attr("disabled", true);   // 내부평가위원배수
			$("#P_INN_ESTM_CMTM_TMES").css("background", "#E8E8E8");
			$("#P_INN_ESTM_CMTM_TMES").val("");
			
//			$("#prioRnkSlctPeSrchBtn").css("display", "none");   // 우선순위선정자 검색 버튼
//			$("#P_PRIO_RNK_SLCT_PE_NM").val("");   // 우선순위선정자
//			$("#P_PRIO_RNK_SLCT_PE_ID").val("");   // 우선순위선정자ID
//			$("#P_PRIO_RNK_SLCT_DEPT_NM").val("");   // 우선순위선정부서명
//			$("#P_PRIO_RNK_SLCT_DEPT_NO").val("");   // 우선순위선정부서ID
			
		}else if(innCmtmSlctMthdEvent == "B"){
			
			// B - 우선순위 : 배수O, 지정평가위원수X, 우선순위선정자O
			$("#P_INN_ESTM_CMTM_CNT").attr("disabled", false);   // 내부평가위원수
			$("#P_INN_ESTM_CMTM_CNT").css("background", "#ffffff");
			
			$("#P_INN_ESTM_CMTM_TMES").attr("disabled", false);   // 내부평가위원배수
			$("#P_INN_ESTM_CMTM_TMES").css("background", "#ffffff");
			
//			$("#prioRnkSlctPeSrchBtn").css("display", "");   // 우선순위선정자 검색 버튼
			
		}else{
			
			// 없음, 전체
			$("#P_INN_ESTM_CMTM_CNT").val("");   // 내부평가위원수
			$("#P_INN_ESTM_CMTM_TMES").val("");   // 내부평가위원배수
//			$("#P_PRIO_RNK_SLCT_PE_NM").val("");   // 우선순위선정자
//			$("#P_PRIO_RNK_SLCT_PE_ID").val("");   // 우선순위선정자ID
//			$("#P_PRIO_RNK_SLCT_DEPT_NM").val("");   // 우선순위선정부서명
//			$("#P_PRIO_RNK_SLCT_DEPT_NO").val("");   // 우선순위선정부서ID

			$("#P_INN_ESTM_CMTM_CNT").attr("disabled", true);
			$("#P_INN_ESTM_CMTM_CNT").css("background", "#E8E8E8");
			
			$("#P_INN_ESTM_CMTM_TMES").attr("disabled", true);
			$("#P_INN_ESTM_CMTM_TMES").css("background", "#E8E8E8");
			
//			$("#prioRnkSlctPeSrchBtn").css("display", "none");   // 우선순위선정자 검색 버튼
			
		}
	};

	pageObj.setProcdStde = function(obj){
		var stDe = $(obj).val();
		$("input[name='P_ESTM_PROCD_ST_DE']").each(function(){
			$(this).val(stDe);
		});
	};

	pageObj.setProcdStHh = function(obj){
		var stHh = $(obj).val();
		$("input[name='P_ESTM_PROCD_ST_HH']").each(function(){
			$(this).val(stHh);
		});
	};

	pageObj.setProcdStMm = function(obj){
		var stMm = $(obj).val();
		$("input[name='P_ESTM_PROCD_ST_MM']").each(function(){
			$(this).val(stMm);
		});
	};

	pageObj.setProcdEndDe = function(obj){
		var endDe = $(obj).val();
		$("input[name='P_ESTM_PROCD_END_DE']").each(function(){
			$(this).val(endDe);
		});
	};

	pageObj.setProcdEndHh = function(obj){
		var endHh = $(obj).val();
		$("input[name='P_ESTM_PROCD_END_HH']").each(function(){
			$(this).val(endHh);
		});
	};

	pageObj.setProcdEndMm = function(obj){
		var endMm = $(obj).val();
		$("input[name='P_ESTM_PROCD_END_MM']").each(function(){
			$(this).val(endMm);
		});
	};
	
	// 시간체크 0~24
	fnTimeHHChk = function(obj) {
		if ($(obj).val() != "") {
			if (FwkCmmnUtil.isNumeric($(obj).val()) != true) {
				alert("숫자만 입력 가능합니다.");
				$(obj).val($(obj).val().replace(/[^0-9]/gi, ""));
			}

			var timeInput = Number($(obj).val());

			if (timeInput < 25 != true) {
				alert("24까지만 입력가능합니다.");
				$(obj).val("");
				$(obj).focus();
				return false;
			}
		}
	};

	
	// 분체크 0~59
	fnTimeMMChk = function(obj) {
		if ($(obj).val() != "") {
			if (FwkCmmnUtil.isNumeric($(obj).val()) != true) {
				alert("숫자만 입력 가능합니다.");
				$(obj).val($(obj).val().replace(/[^0-9]/gi, ""));
			}

			var timeInput = Number($(obj).val());

			if (timeInput < 60 != true) {
				alert("59까지만 입력가능합니다.");
				$(obj).val("");
				$(obj).focus();
				return false;
			}
		}
	}
	
	/**
	 * 페이지내에서 필요한 항목의 이벤트를 바인딩한다.
	 * 1. 목록버튼(#listBtn) 의 click 이벤트를 binding 한다.
	 */
	pageObj.setEventHandler = function() {

		// 저장버튼
		$("#saveBtn").on("click", function() {
			
			// 필수체크
			if($("#P_ESTM_NM").val() == ""){
				alert("[평가명] 항목은 필수입력 입니다.");
				$("#P_ESTM_NM").focus();
				return false;
			}
			
			if($("select[name='P_ESTM_SECD']").val() == ""){
				alert("[평가구분] 항목은 필수입력 입니다.");
				$("select[name='P_ESTM_SECD']").focus();
				return false;
			}
			
			if($("select[name='P_ESTM_SPHE_SECD']").val() == ""){
				alert("[평가분야구분] 항목은 필수입력 입니다.");
				$("select[name='P_ESTM_SPHE_SECD']").focus();
				return false;
			}
			
			if($("select[name='P_ESTM_OBJ_SECD']").val() == ""){
				alert("[평가대상구분] 항목은 필수입력 입니다.");
				$("select[name='P_ESTM_OBJ_SECD']").focus();
				return false;
			}
			
			if($("#P_ESTM_CHRGR_NM").val() == ""){
				alert("[평가담당자] 항목은 필수입력 입니다.");
				$("#P_ESTM_CHRGR_NM").focus();
				return false;
			}
			
			if($("#P_TOTL_ESTM_ST_DE").val() == ""){
				alert("[평가시작일시]는 필수입력입니다.");
				$("#P_TOTL_ESTM_ST_DE").focus();
				return false;
			}
			
			if($("#P_TOTL_ESTM_ST_HH").val() == ""){
				alert("[평가시작일시]는 필수입력입니다.");
				$("#P_TOTL_ESTM_ST_HH").focus();
				return false;
			}
			
			if($("#P_TOTL_ESTM_ST_MM").val() == ""){
				alert("[평가시작일시]는 필수입력입니다.");
				$("#P_TOTL_ESTM_ST_MM").focus();
				return false;
			}
			
			if($("#P_TOTL_ESTM_END_DE").val() == ""){
				alert("[평가종료일시]는 필수입력입니다.");
				$("#P_TOTL_ESTM_END_DE").focus();
				return false;
			}
			
			if($("#P_TOTL_ESTM_END_HH").val() == ""){
				alert("[평가종료일시]는 필수입력입니다.");
				$("#P_TOTL_ESTM_END_HH").focus();
				return false;
			}
			
			if($("#P_TOTL_ESTM_END_MM").val() == ""){
				alert("[평가종료일시]는 필수입력입니다.");
				$("#P_TOTL_ESTM_END_MM").focus();
				return false;
			}
			
			if($("select[name='P_INN_CMTM_SLCT_MTHD_SECD']").val() == ""){
				alert("[내부평가위원선정방법] 항목은 필수입력 입니다.");
				$("select[name='P_INN_CMTM_SLCT_MTHD_SECD']").focus();
				return false;
			}
			
			var inn_cmtm_slct_mthd_secd = $("select[name='P_INN_CMTM_SLCT_MTHD_SECD']").val();   // 내부평가위원선정방법
			
			if(inn_cmtm_slct_mthd_secd == "A"){
				// 자체선정
				if($("#P_INN_ESTM_CMTM_CNT").val() == "" || $("#P_INN_ESTM_CMTM_CNT").val() == "0"){
					alert("[내부평가위원수] 항목은 필수입력 입니다.");
					$("#P_INN_ESTM_CMTM_CNT").focus();
					return false;
				}
				$("#P_INN_ESTM_CMTM_TMES").val("");
				$("#P_PRIO_RNK_SLCT_PE_NM").val("");
				$("#P_PRIO_RNK_SLCT_PE_ID").val("");
				
				$("#P_PRIO_RNK_SLCT_DEPT_NM").val("");
				$("#P_PRIO_RNK_SLCT_DEPT_NO").val("");
				
			}else if(inn_cmtm_slct_mthd_secd == "B"){   
				// 우선순위 
				if($("#P_INN_ESTM_CMTM_CNT").val() == "" || $("#P_INN_ESTM_CMTM_CNT").val() == "0"){
					alert("[내부평가위원수] 항목은 필수입력 입니다.");
					$("#P_INN_ESTM_CMTM_CNT").focus();
					return false;
				}
				
				if($("#P_INN_ESTM_CMTM_TMES").val() == "" || $("#P_INN_ESTM_CMTM_TMES").val() == "0"){
					alert("[내부평가위원배수] 항목은 필수입력 입니다.");
					$("#P_INN_ESTM_CMTM_TMES").focus();
					return false;
				}
				
				if($("#P_PRIO_RNK_SLCT_PE_NM").val() == ""){
					alert("[우선순위선정자] 항목은 필수입력 입니다.");
					$("#P_PRIO_RNK_SLCT_PE_NM").focus();
					return false;
				}
				
			}else{
				// 없음
				$("#P_INN_ESTM_CMTM_CNT").val("");
				$("#P_INN_ESTM_CMTM_TMES").val("");
				
				$("#P_PRIO_RNK_SLCT_PE_NM").val("");
				$("#P_PRIO_RNK_SLCT_PE_ID").val("");
				
				$("#P_PRIO_RNK_SLCT_DEPT_NM").val("");
				$("#P_PRIO_RNK_SLCT_DEPT_NO").val("");
			}
			
			var flag = true;

			$("#estmShowTbdy select[name='P_ESTM_PROCD_SECD']").each(function(){
				if($(this).val() == ""){
					alert("[평가절차구분] 항목은 필수입력 입니다.");
					$(this).focus();
					flag = false;
					return false;
				}
			});
			
			if(!flag){
				return false;
			}
			
			$("#estmShowTbdy select[name='P_ESTR_SECD']").each(function(){
				if($(this).val() == ""){
					alert("[평가자구분] 항목은 필수입력 입니다.");
					$(this).focus();
					flag = false;
					return false;
				}
			});
			
			if(!flag){
				return false;
			}
			
			$("#estmShowTbdy input[name='P_ESTM_PROCD_ST_DE']").each(function(){
				if($(this).val() == ""){
					alert("[평가절차 시작일시] 항목은 필수입력 입니다.");
					$(this).focus();
					flag = false;
					return false;
				}
			});
			
			if(!flag){
				return false;
			}
			
			$("#estmShowTbdy input[name='P_ESTM_PROCD_ST_HH']").each(function(){
				if($(this).val() == ""){
					alert("[평가절차 시작일시] 항목은 필수입력 입니다.");
					$(this).focus();
					flag = false;
					return false;
				}
			});
			
			if(!flag){
				return false;
			}
			
			$("#estmShowTbdy input[name='P_ESTM_PROCD_ST_MM']").each(function(){
				if($(this).val() == ""){
					alert("[평가절차 시작일시] 항목은 필수입력 입니다.");
					$(this).focus();
					flag = false;
					return false;
				}
			});

			if(!flag){
				return false;
			}
			
			$("#estmShowTbdy input[name='P_ESTM_PROCD_END_DE']").each(function(){
				if($(this).val() == ""){
					alert("[평가절차 종료일시] 항목은 필수입력 입니다.");
					$(this).focus();
					flag = false;
					return false;
				}
			});

			if(!flag){
				return false;
			}
			
			$("#estmShowTbdy input[name='P_ESTM_PROCD_END_HH']").each(function(){
				if($(this).val() == ""){
					alert("[평가절차 종료일시] 항목은 필수입력 입니다.");
					$(this).focus();
					flag = false;
					return false;
				}
			});

			if(!flag){
				return false;
			}
			
			$("#estmShowTbdy input[name='P_ESTM_PROCD_END_MM']").each(function(){
				if($(this).val() == ""){
					alert("[평가절차 종료일시] 항목은 필수입력 입니다.");
					$(this).focus();
					flag = false;
					return false;
				}
			});

			if(!flag){
				return false;
			}
			
			if(!confirm("저장하시겠습니까?")){
				return false;
			}

			$("#estmHidTbdy").remove();
			
			fileUploadStart();
			//pageObj.estmProgUpdt();
			
		});

		// 취소버튼
		$("#cnclBtn").on("click", function() {
			if(!confirm("취소하시겠습니까?")){ 
				return false;
			}
			pageObj.cmtmMngProgDetail();
		});

		// 평가담당자 검색버튼
		$("#estmChrgrSrchBtn").on("click", function() {
			pageObj.chargerInqirePopup('estmChrgr');
		});
		
		// 우선순위선정자 삭제버튼
		$("#prioRnkSlctPeDelBtn").on("click", function() {
			pageObj.chargerInqireDelete("prioRnkSlctPe");
		});
		
		// 우선순위선정자 검색버튼
		$("#prioRnkSlctPeSrchBtn").on("click", function() {
			pageObj.chargerInqirePopup('prioRnkSlctPe');
		});
		
		// 평가정보불러오기 버튼
		$("#estmInfoListPopup").on("click", function() {
			pageObj.estmInfoListPopup();
		});
		
		// 평가절차 평가추가 버튼
		$("#estmAddBtn").on("click", function() {
			//pageObj.estmAdd("estmShowTbdy", "estmHidTbdy");
		});
		
		// 평가절차 평가삭제 버튼
		$("#estmDelBtn").on("click", function() {
			pageObj.estmDel();
		});
		
		// 심사위원 서명파일 추가 버튼
		$(".fileAddBtn").on("click", function() {
			fileRowAdd();
		});
		
		// 평가구분
		$("select[name='P_ESTM_SECD']").on("change", function() {
			pageObj.estmSecdEvent(this);
			
			// 평가구분이 변경될 경우 > 평가절차의 시작,종료일시 입력
			pageObj.setProcdStde($("#P_TOTL_ESTM_ST_DE"));
			pageObj.setProcdStHh($("#P_TOTL_ESTM_ST_HH"));
			pageObj.setProcdStMm($("#P_TOTL_ESTM_ST_MM"));
			pageObj.setProcdEndDe($("#P_TOTL_ESTM_END_DE"));
			pageObj.setProcdEndHh($("#P_TOTL_ESTM_END_HH"));
			pageObj.setProcdEndMm($("#P_TOTL_ESTM_END_MM"));
		});
		
		// 내부평가위원선정방법
		$("select[name='P_INN_CMTM_SLCT_MTHD_SECD']").on("change", function() {
			pageObj.innCmtmSlctMthdEvent(this);
		});

		//평가시작일시 입력시 평가절차 시작일시 자동세팅
		$("input[name='P_TOTL_ESTM_ST_DE']").on({"change": function() {
			pageObj.setProcdStde(this);
		}, "click": function(){
			pageObj.setProcdStde(this);
		}});

		$("input[name='P_TOTL_ESTM_ST_HH']").on({"change": function() {
			pageObj.setProcdStHh(this);
		}, "click": function(){
			pageObj.setProcdStHh(this);
		}});

		$("input[name='P_TOTL_ESTM_ST_MM']").on({"change": function() {
			pageObj.setProcdStMm(this);
		}, "click": function(){
			pageObj.setProcdStMm(this);
		}});
		
		//평가종료일시 입력시 평가절차 종료일시 자동세팅
		$("input[name='P_TOTL_ESTM_END_DE']").on({"change": function() {
			pageObj.setProcdEndDe(this);
		}, "click": function(){
			pageObj.setProcdEndDe(this);
		}});

		$("input[name='P_TOTL_ESTM_END_HH']").on({"change": function() {
			pageObj.setProcdEndHh(this);
		}, "click": function(){
			pageObj.setProcdEndHh(this);
		}});

		$("input[name='P_TOTL_ESTM_END_MM']").on({"change": function() {
			pageObj.setProcdEndMm(this);
		}, "click": function(){
			pageObj.setProcdEndMm(this);
		}});
	};

	/**
	 * window load
	 *
	 */
	$(function() {
		pageObj.setEventHandler();
		pageObj.estmSpheSecd();
		pageObj.innCmtmSlctMthdEvent();
		fileModify("updtFrm", "P_FILE_GRP_NO", "P_FILE_GRP_NO_NEW", "/estm/estmProgUpdt.do");//, "detailFrm", "/estm/cmtmMngProgDetail.do", "");
	});
})();