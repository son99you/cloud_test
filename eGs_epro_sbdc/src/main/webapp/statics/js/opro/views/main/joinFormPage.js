(function() {

	/**
	 * Default Constructor
	 */
	pageObj = {};
	
	/**
	 * Default Form Name
	 */
	var defaultFrm = "registFrm";
	
	
	pageObj.loginData = function(first) {
	    var verifyVID = true;
	    var options = {};
		
	    var regNum = $("#P_BIZRNO1").val()+$("#P_BIZRNO2").val()+$("#P_BIZRNO3").val();
	    regNum= regNum.replace( /-/g, "");
	    $("#P_LOGIN_ID").val(regNum);
	    
	    var sessionId = "<%= session.getId() %>";
	    var ssn = regNum;
	    var userInfo = "1:" + regNum;
	    if(verifyVID == true) {
			if(ssn.length == 10) {
				options.ssn = ssn;
			}
			else {
				alert("사업자번호를 정확하게 입력하세요.");
				return;
			}
	    }
	    
	    var option = {
	    		encoding : 'base64'
	    		, charset : 'utf-8'
	    		, signtype : '2'
	    };
	    var config = {
	    		certificateClass : '16'
	    };
		config.OID = FwkMssageUtil.getMessage("CONFIG.OID");
		config.firstTrigger = first;
		yettie.init(config);
		
	    yettie.signWithVerifyVID(options.ssn ,options.ssn, option, pageObj.login_data_complete_callback, pageObj.error_callback);
	};	
	
	pageObj.login_data_complete_callback = function(res) {
		if (res.signature != "") {
			$("#loginData").val(res.signature);
			
			pageObj.joinRegist();
			
		}else {
			alert("본인인증에 실패했습니다.");
			return ;
	    }
	};
	
	pageObj.success_callback = function(res) {
		alert("에러");
	};
	
	pageObj.error_callback = function(error) {
		if(error.code != -9999){ // 취소 버튼 이벤트 error.code: -9999
			alert(error.msg);
		}  
	};			
	
	
	// 사업자번호 중복체크
	pageObj.bizrnoCheck = function() {
		
		var jsonData = $("#"+defaultFrm).serializeObject();
		var actionUrl = "/opro/main/joinEnpaCheck";
		
		FwkCmmnUtil.submitAjax (actionUrl, jsonData, function(res) {
			
			//alert("res ==> " + JSON.stringify(res));
			//alert("res.joinCheck ==> " + res.joinCheck);
			
			if(res.joinCheck=='join'){
				//alert("이미 가입 되어있는 사업자번호 입니다.");
				alert("기존에 회원가입 완료 또는 승인대기중 입니다.");
			}else if(res.joinCheck!='join'){
				
				alert("회원가입 대상입니다.");
				
				$("#P_BIZRNO1").val(res.P_BIZRNO1);
				$("#P_BIZRNO2").val(res.P_BIZRNO2);
				$("#P_BIZRNO3").val(res.P_BIZRNO3);
				
				if(res.P_BIZRNO_CHECK == "Y"){
					$("#P_BIZRNO_CHECK").val(res.P_BIZRNO_CHECK );
				}else{
					$("#P_BIZRNO_CHECK").val("");
				}
				
			}
		});
		
	};
	
		
	pageObj.joinRegist = function() {
		
		FwkCmmnUtil.submitForm(defaultFrm, "/opro/main/joinRegist.do");
		
		/*var jsonData = $("#registFrm").serializeObject();
		 
		var actionUrl = "/opro/main/joinRegist";
		
		FwkCmmnUtil.submitAjax (actionUrl, jsonData, function(res) {
			
			alert("res ==> " + JSON.stringify(res));
			
			if(res.mberRegistCompt=='JOIN'){
				alert("이미 가입 되어있는 사업자 번호입니다.");
			}else if(res.mberRegistCompt=='compt'){ 
				FwkCmmnUtil.submitForm(defaultFrm, "/opro/main/joinSuccessView.do");
			}
		});
		return;*/
	};
	
	
	
	// 아이디 중복체크 확인 
	pageObj.checkUidPopup = function() {
		$("#registFrm").one("submit", function() {
			window.open("", "zipListPopup", "width=600px, height=300px, toolbar=no, status=no, resizeable=no, scrollbars=no, menubar=no, left="+x(740)+",top="+y(600));
			this.action = FwkCmmnUtil['contextPath']+'/opro/comm/popup/joinUserIdCheckView.do'; 
			this.method = 'POST';
			this.target = 'zipListPopup';
		}).trigger("submit");
	};
	
	
	jusoCallBack = function(roadFullAddr,roadAddrPart1,addrDetail,roadAddrPart2,engAddr, jibunAddr, zipNo, admCd, rnMgtSn, bdMgtSn,detBdNmList,bdNm,bdKdcd,siNm,sggNm,emdNm,liNm,rn,udrtYn,buldMnnm,buldSlno,mtYn,lnbrMnnm,lnbrSlno,emdNo){
		$("#P_ZIP").val(zipNo);
		$("#P_RN_ADRES").val(roadAddrPart1);
		$("#P_DETAIL_ADRES").val(addrDetail);
	};
	
	
	// 파일다운로드
	pageObj.download = function(fileSn, fileGrpNo) {
		$("#downloadFrm input[name='P_FILE_SN']").val(fileSn);
		$("#downloadFrm input[name='P_FILE_GRP_NO']").val(fileGrpNo);
		FwkCmmnUtil.submitForm("downloadFrm", "/comm/download.do" );
	};
	
	
	// 계좌정보 추가
	rowAdd2 = function( fileDiv, fileRow, fileName ){
		var row = $("#"+fileRow).children().clone();
		row.find(".acctTskVkey1").attr("name", "P_ACNO_NEW");
		row.find(".acno").attr("name", "P_ACNO");
		row.find(".dpso").attr("name", "P_DPSO");
		row.find(".fileDocSecd1").attr("name", "P_ACCT_FILE_DOC_SECD");
		row.find("input[type='file']").attr("name", fileName);
		$("#"+fileDiv).append(row);
	};
	
	
	// 계좌정보 삭제
	pageObj.accDeleteEvent = function(){
		$("#accShowTbdy input[name='accCbk']").each(function(inx){
			
			var fileSn = $(this).parent().parent().find("input[name='P_ACC_FILE_SN']").val();
			
			if(this.checked) {
				if($("#P_ACC_DEL_SN").val()){
					$("#P_ACC_DEL_SN").val($("#P_ACC_DEL_SN").val()+","+fileSn);
				}else{
					$("#P_ACC_DEL_SN").val(fileSn);
				}
				$(this).parent().parent().remove();
			}
		});
	};
	
	
	// 기업정보 추가
	rowAdd3 = function( fileDiv, fileRow, fileName ){
		var $select = $('#selectRefFrm').find('select[name=P_DATA_CD]').clone();
			
		$("#"+fileDiv)
			.append($('<tr>')
						.append($('<td>').addClass('txtc')
									.append($('<input>').attr({'type':'checkbox','name':'acqsCbk'}))
									)
						.append($('<td>').addClass('txtc')
									.append($select.removeAttr('id'))
									)
						.append($('<td>').addClass('txtc')
									.append($('<div>').addClass('calendar_box')
												.append($('<input>').attr({'type':'text','name':'P_VLD_STDE','maxlength':'10','date':''}).addClass('w120 date vldStde'))
												.append($('<span>').addClass('wave').text("~"))
												.append($('<input>').attr({'type':'text','name':'P_VLD_ENDE','maxlength':'10','date':''}).addClass('w120 date vldStde'))
												)
									.append($('<input>').attr({'type':'hidden','name':'P_ACQS_FILE_DOC_SECD'}).addClass('fileDocSecd2').val("ACQS"))			
									.append($('<input>').attr({'type':'hidden','name':'P_DATA_CD_NEW'}).addClass('acqsTskVkey1'))			
									)
						.append($('<td>')
									.append($('<input>').attr({'type':'file','name':fileName}))
									)									
						);
		
		setDatePicker();
	};
	
	// 기업정보 삭제
	pageObj.acqsDeleteEvent = function(){
		$("#acqsShowTbdy input[name='acqsCbk']").each(function(inx){
			
			var fileSn = $(this).parent().parent().find("input[name='P_ACQS_FILE_SN']").val();
			
			if(this.checked) {
				if($("#P_ACQS_DEL_SN").val()){
					$("#P_ACQS_DEL_SN").val($("#P_ACQS_DEL_SN").val()+","+fileSn);
				}else{
					$("#P_ACQS_DEL_SN").val(fileSn);
				}
				$(this).parent().parent().remove();
			}
		});
	};
	
	
	// 품목찾기 팝업
	pageObj.mjrHndlItemListPopup = function(obj) {
		
		$("#itemPopupFrm").one("submit", function() {
			window.open("", "itemListPopup", "width=750px, height=770px, toolbar=no, status=no, scrollbars=yes, menubar=no,left=500,top=100");
			this.action = FwkCmmnUtil['contextPath']+'/opro/comm/popup/mjrHndlItemList.do';
			this.method = 'POST';
			this.target = 'itemListPopup'; 
		}).trigger("submit");

	};
	
	
	// 주요취급품목 추가
	mjrHndlItemListAdd = function(ccpyCnt, arr_item_no, arr_item_nm, arr_llf_cd, arr_mlf_cd, arr_slf_cd, arr_dlf_cd){
		var flag=true;
		$(arr_item_no).each(function(inx, item){
			// 중복체크
			$("input[name='P_ITEM_NO']").each(function() {		
				if($(this).val() == arr_item_no[inx]){
					flag = false;
				}
			});
		});
		
		if(flag == true){
			for (var idx = 0; idx < ccpyCnt; idx++) {
				// 팝업에서 선택된 정보 화면에 보여주기
				$("#itemShowTbdy")
				.append($('<tr>').addClass('row')
						.append($('<td>').addClass('txtc')
								.append($('<input>').attr({'type':'checkbox', 'name':'itemCbk'}))
								
									)
						.append($('<td>').addClass('txtc').text(arr_item_no[idx])
								.append($('<input>').attr({'type':'hidden', 'name':'P_ITEM_NO'}).val(arr_item_no[idx]))
								.append($('<input>').attr({'type':'hidden', 'name':'P_ITEM_NM'}).val(arr_item_nm[idx]))
								.append($('<input>').attr({'type':'hidden', 'name':'P_LLF_CD'}).val(arr_llf_cd[idx]))
								.append($('<input>').attr({'type':'hidden', 'name':'P_MLF_CD'}).val(arr_mlf_cd[idx]))
								.append($('<input>').attr({'type':'hidden', 'name':'P_SLF_CD'}).val(arr_slf_cd[idx]))
								.append($('<input>').attr({'type':'hidden', 'name':'P_DLF_CD'}).val(arr_dlf_cd[idx]))
									)
						.append($('<td>').text(arr_item_nm[idx])
									)
						/*.append($('<td>')
								.append($('<input>').attr({'type':'text','name':'P_ITEM_UPRC'}).addClass('money').css({'width':'95%', 'maxlength':'12', 'text-align':'right'}).val(0))
									)*/
					);
			}
		}
		
	};
	
	
	// 주요취급품목 삭제
	pageObj.itemDeleteEvent = function(){
		$("#itemShowTbdy input[name='itemCbk']").each(function(inx){
			if(this.checked) {
				$(this).parent().parent().remove();
			}
		});
	};
	
	
	// 기존 필수파일 변경
	rowRedFileChng = function(obj, sn){
		if($("#P_RED_DEL_SN").val() == ""){
			$("#P_RED_DEL_SN").val(sn);
		}else{
			$("#P_RED_DEL_SN").val($("#P_RED_DEL_SN").val()+","+sn);
		}
		$(obj).closest('tr').find(".redFileView").css("display","none");
		$(obj).closest('tr').find(".redFile").css("display","");
		$(obj).closest('tr').find(".redFileViewBtn").css("display","none");
		$(obj).closest('tr').find(".redFileBtn").css("display","");
		$(obj).closest('tr').find("input[name='P_RED_FILE_DOC_SECD']").attr("disabled",false);
		$(obj).closest('tr').find("input[type='file']").attr("disabled",false);
	};
	
	
	// 기존 필수파일 취소
	rowRedFileCncl = function(obj, sn){
		var P_RED_DEL_SN = $("#P_RED_DEL_SN").val();
		$("#P_RED_DEL_SN").val(P_RED_DEL_SN.replace(sn,""));
		$(obj).closest('tr').css("text-decoration","");
		$(obj).closest('tr').find(".redDelBtn").css("display","none");
		$(obj).closest('tr').find(".redFileViewBtn").css("display","");
	};
	
	// 기존 필수파일 변경 취소
	rowRedFileChngCncl = function(obj, sn){
		var P_RED_DEL_SN = $("#P_RED_DEL_SN").val();
		$("#P_RED_DEL_SN").val(P_RED_DEL_SN.replace(sn,""));
		$(obj).closest('tr').find(".redFile").css("display","none");
		$(obj).closest('tr').find(".redFileView").css("display","");
		$(obj).closest('tr').find(".redFileBtn").css("display","none");
		$(obj).closest('tr').find(".redFileViewBtn").css("display","");
		$(obj).closest('tr').find("input[name='P_RED_FILE_DOC_SECD']").attr("disabled",true);
		$(obj).closest('tr').find("input[type='file']").attr("disabled",true);
	};
	
	
	// 기타첨부파일 추가
	rowAdd = function( fileDiv, fileRow, fileName, fileDocName ){
		var row = $("#"+fileRow).children().clone();
		row.find("input[type='text']").attr("name",fileDocName);
		row.find("input[type='file']").attr("name",fileName); 
		$("#"+fileDiv).append(row);
	};
	
	
	// 처음 기타첨부파일 삭제
	rowDel = function(obj){
		$(obj).closest('tr').remove();
	};
	
	
	// 기존 기타첨부파일 삭제
	rowFileDel = function(obj, sn){
		if($("#P_ETC_DEL_SN").val() == ""){
			$("#P_ETC_DEL_SN").val(sn);
		}else{
			$("#P_ETC_DEL_SN").val($("#P_ETC_DEL_SN").val()+","+sn);
		}
		$(obj).closest('tr').css("text-decoration","line-through");
		$(obj).closest('tr').find(".fileView").attr("disabled", true);
		$(obj).closest('tr').find(".fileViewBtn").css("display","none");
		$(obj).closest('tr').find(".fileDelBtn").css("display","");
	};
	
	
	// 기존 기타첨부파일 취소
	rowFileCncl = function(obj, sn){
		var P_ETC_DEL_SN = $("#P_ETC_DEL_SN").val();
		$("#P_ETC_DEL_SN").val(P_ETC_DEL_SN.replace(sn,""));
		$(obj).closest('tr').css("text-decoration","");
		$(obj).closest('tr').find(".fileView").attr("disabled", true);
		$(obj).closest('tr').find(".fileViewBtn").css("display","");
		$(obj).closest('tr').find(".fileDelBtn").css("display","none");
	};
	
	
	// 우편번호 팝업
	pageObj.zipListPopup = function() {
		$("#zipPopupFrm").one("submit", function() {
			window.open("", "zipListPopup", "width=750px, height=650px, toolbar=no, status=no, resizeable=no, scrollbars=no, menubar=no, left="+x(740)+",top="+y(600));
			this.action = FwkCmmnUtil['contextPath']+'/opro/comm/popup/zipList.do';
			this.method = 'POST';
			this.target = 'zipListPopup';
		}).trigger("submit");
	};
	
	
	// 우편번호 CALLBACK
	jusoCallBack = function(roadFullAddr,roadAddrPart1,addrDetail,roadAddrPart2,engAddr, jibunAddr, zipNo, admCd, rnMgtSn, bdMgtSn,detBdNmList,bdNm,bdKdcd,siNm,sggNm,emdNm,liNm,rn,udrtYn,buldMnnm,buldSlno,mtYn,lnbrMnnm,lnbrSlno,emdNo){
		$("#P_ZIP").val(zipNo);
		$("#P_ADDR_NM").val(roadAddrPart1);
		$("#P_ADDR_DENM").val(addrDetail);
	};
	
	
	/**
	 * 페이지내에서 필요한 항목의 이벤트를 바인딩한다.
	 */
	pageObj.setEventHandler = function() {
		
		// 저장
		$("#saveBtn").on("click", function() { 
			
			if(!required_ew()){
				return false;
			}
			
			var flag = true;
			
			if($("#P_BIZRNO_CHECK").val() == null || $("#P_BIZRNO_CHECK").val() != "Y"){
				alert("[사업자등록번호 중복확인] 은 필수입니다.");
				return false;
			}
				
			if($("#P_PWD").val() == "" || $("#P_PWD").val() == null){
				alert("[비밀번호] 항목은 필수입력입니다.");
				$("#P_PWD").focus();
				return false;
			}
			
			if($("#P_PWD").val() != $("#P_PWD_CNFM").val()){
				alert("[비밀번호]와 [비밀번호 확인] 이 일치하지 않습니다.");
				$("#P_PWD_CNFM").focus();
				return false;
			}
			
			/*var accTr = $("#accShowTbdy tr").length;
			if(accTr <= 0){
				alert("계좌정보가 최소 1개는 필수입니다.");
				return false;
			}
			
			$("#accShowTbdy select[name='P_BKCD']").each(function(){
				if($(this).val() == "" || $(this).val() == null){
					alert("[은행코드] 항목은 필수입력입니다.");
					$(this).focus();
					flag = false;
					return false;
				}
			});*/
			
			
			$("#accHiddTbdy select[name='P_BKCD']").each(function(){
				if($(this).val() == "" || $(this).val() == null){
					alert("[은행코드] 항목은 필수입력입니다.");
					$(this).focus();   
					flag = false;
					return false;
				}
			});
			
			if(!flag){
				return false; 
			}
			
			$("#accHiddTbdy input[name='P_ACNO']").each(function(){
				if($(this).val() == "" || $(this).val() == null){
					alert("[계좌번호] 항목은 필수입력입니다.");
					$(this).focus();   
					flag = false;
					return false;
				}
			});
			
			if(!flag){
				return false; 
			}
			
			$("#accHiddTbdy select[name='P_DPSO']").each(function(){
				if($(this).val() == "" || $(this).val() == null){
					alert("[예금주] 항목은 필수입력입니다.");
					$(this).focus();   
					flag = false;
					return false;
				}
			});
			
			if(!flag){
				return false; 
			}						
			
			// 첨부파일 필수체크
			$("#accHiddTbdy input[name='P_ACCT_FILE']").each(function(){
				if($(this).val() == "" || $(this).val() == null){
					alert("[계좌정보 첨부파일] 항목은 필수입력입니다.");
					flag = false;
					return false;
				}
			});
			
			
			if(!flag){
				return false; 
			}
			
			$("#acqsShowTbdy select[name='P_DATA_CD']").each(function(){
				if($(this).val() == "" || $(this).val() == null){
					alert("[명칭] 항목은 필수입력입니다.");
					$(this).focus();   
					flag = false;
					return false;
				}
			});
			
			$("#acqsShowTbdy select[name='P_VLD_STDE']").each(function(){
				if($(this).val() == "" || $(this).val() == null){
					alert("[기업정보 유효기간] 항목은 필수입력입니다.");
					$(this).focus();   
					flag = false;
					return false;
				}
			});
			$("#acqsShowTbdy select[name='P_VLD_ENDE']").each(function(){
				if($(this).val() == "" || $(this).val() == null){
					alert("[기업정보 유효기간] 항목은 필수입력입니다.");
					$(this).focus();   
					flag = false;
					return false;
				}
			});
			
			if(!flag){
				return false; 
			}
			
			// 첨부파일 필수체크
			$("#acqsShowTbdy input[name='P_ACQS_FILE']").each(function(){
				if($(this).val() == "" || $(this).val() == null){
					alert("[기업정보 첨부파일] 항목은 필수입력입니다.");
					flag = false;
					return false;
				}
			});
			
			if(!flag){
				return false; 
			}
			
			var dataCdArray = [];   // 기업정보 명칭 중복배열
			var dataCdRemoveArray = [];   // 기업정보 명칭 중복제거 배열
			
			// 기업정보 중복체크
			$("#acqsShowTbdy select[name='P_DATA_CD']").each(function(){
				var dataCd =$(this).val();
				dataCdArray.push(dataCd);
			});
			
			for (var i = 0; i < dataCdArray.length; i++) {
				
				var chkFlag = false;
				
				if(dataCdRemoveArray.length == 0){
					dataCdRemoveArray.push(dataCdArray[i]);
					continue;
				}
				
				for (var j = 0; j < dataCdRemoveArray.length; j++) {
					if(dataCdArray[i] == dataCdRemoveArray[j]){
						alert("중복된 기업정보 명칭이 있습니다.");
						chkFlag = true;
						flag = false;
					}					
				}
				
				if(!chkFlag){
					dataCdRemoveArray.push(dataCdArray[i]);
				}
				
				
			}
			
			
			if(!flag){
				return false; 
			}
			
			var itemTr = $("#itemShowTbdy tr").length;
			if(itemTr <= 0){
				alert("주요취급품목이 최소 1개는 필수입니다.");
				//flag = false;
				//return false;
			}
			
			if(!flag){
				return false; 
			}
			
			
			if(!confirm("회원가입 하시겠습니까?")){
				return false; 
			}
			
			removeComma();
			//$("#accHiddTbdy").children().remove();
			$("#acqsHiddTbdy").children().remove();
			$("#fileHideTbdy").children().remove();
			
			$("#accShowTbdy input[name='P_ACNO_NEW']").each(function(){
				var acno = $(this).parent().parent().find("input[name='P_ACNO']").val();
				$(this).val(acno);
			});
			
			$("#acqsShowTbdy input[name='P_DATA_CD_NEW']").each(function(){
				var dataCd = $(this).parent().parent().find("select[name='P_DATA_CD']").val();
				$(this).val(dataCd);
			});
			
			//회원가입 위한 인증서 툴킷 로딩
			pageObj.loginData("hibiscus");
			
		});
		

		// 아이디 중복체크 클릭 이벤트
		$("#checkUid").on("click", function() {
			pageObj.checkUidPopup();
		});
		
		
		// 사업자등록번호 중복체크
		$("#birnoCheckBtn").on("click", function() {
			
			if($("#P_BIZRNO1").val() == "" || $("#P_BIZRNO1").val() == null){
				alert("[사업자등록번호] 항목은 필수입력 입니다.");
				$("#P_BIZRNO1").focus();
				return false;
			}
			
			if($("#P_BIZRNO2").val() == "" || $("#P_BIZRNO2").val() == null){
				alert("[사업자등록번호] 항목은 필수입력 입니다.");
				$("#P_BIZRNO2").focus();
				return false;
			}
			
			if($("#P_BIZRNO3").val() == "" || $("#P_BIZRNO3").val() == null){
				alert("[사업자등록번호] 항목은 필수입력 입니다.");
				$("#P_BIZRNO3").focus();
				return false;
			}
			
			pageObj.bizrnoCheck();
			
		});
		
		
		// 계좌정보 추가
		$("#accAddBtn").on("click", function() {
			rowAdd2("accShowTbdy", "accHiddTbdy", "P_ACCT_FILE");
		});
		
		
		// 계좌정보 삭제
		$("#accDelBtn").on("click", function() {
			pageObj.accDeleteEvent();
		});
		
		
		// 기업정보 추가
		$("#acqsAddBtn").on("click", function() {
			rowAdd3("acqsShowTbdy", "acqsHiddTbdy", "P_ACQS_FILE");
		});
		
		
		// 기업정보 삭제
		$("#acqsDelBtn").on("click", function() {
			pageObj.acqsDeleteEvent();
		});
		
		
		// 주요취급품목 추가
		$("#itemAddBtn").on("click", function() {
			$("#setMulti").val("Y");
			pageObj.mjrHndlItemListPopup();
		});
		
		
		// 주요취급품목 삭제
		$("#itemDelBtn").on("click", function() {
			pageObj.itemDeleteEvent();
		});
		
		
		// 기타첨부파일 추가
		$("#vendFileBtn").on("click", function(){
			rowAdd("fileShowTbdy", "fileHideTbdy", "P_VEND_FILE", "P_ETC_FILE_DOC_NM");
		});
		
		
		// 우편번호 버튼 클릭 이벤트
		$("#zipBtn").on("click", function() {
			pageObj.zipListPopup();
		});
		
	};

	/**
	 * window load
	 */
	$(function() {		
		pageObj.setEventHandler();
		
		//금액 처리 추가
		$(document).on('keyup', '.money', function() {
			if($(this).val() != "" ){
				$(this).val( $(this).val().replace(/[^0-9]|\[,]|\[.]/gi,""));
				if($(this).val().indexOf(",") > -1){
					$(this).val( $(this).val().split(",").join("") );
				}			
				$(this).val(Number($(this).val().replace(/,/gi,"")));
				$(this).val(FwkCmmnUtil.addComma($(this).val()));				
			}
		}).on('change', '.money', function() {
			if($(this).val() != "" ){
				if(FwkCmmnUtil.isNumeric($(this).val()) != true){
					$(this).val( $(this).val().replace(/[^0-9]|\[,]/gi,"")); 
				}
			}
			$(this).val(FwkCmmnUtil.addComma($(this).val()));			
		}).on('blur', '.money', function() {
			if($(this).val() != "" ){
				if(Number($(this).val().replace(/,/gi,"")) == 0 ){
					alert("0원이 들어갈수 없습니다.");
					$(this).val("");
					$(this).focus();
				}				
			}
		});
		
	});
	
})();