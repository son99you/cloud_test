/**
 * 계약설계 > 입찰공고요청 등록
 *
 * <pre>
 * prpo 
 *    |_ bidReqDetail.js
 * 
 * </pre>
 * @date : 2020. 08. 25
 * @version : 1.0
 * @author : 은우소프트 joo
 */

(function() {

	/**
	 * Default Constructor
	 */
	pageObj = {};

	/**
	 * Default Form Name
	 */
	var defaultFrm = "detailFrm";

	/**
	 * 1.개요 : 탭클릭이벤트 적용
	 * 2.처리내용 : 각 탭 클릭시 이동.
	 * 3. 처리일자 : 2018-11-08
	 * @param no
	 * @param obj
	 */
	tabEvent = function(no){
		
//		$(".tab_wrap01 > .on").attr('class','tapBtn');
//		$(".det_tabs > .on").attr('class','tapBtn');
		$('#'+no).attr("class","on");
		if(no == "1"){  	//공고상세
			FwkCmmnUtil.submitForm("detailFrm", "/ebid/bidPblancPrcnDetail.do");
		}else if(no == "2"){	//기술평가TCHN_ESTM
//			FwkCmmnUtil.submitForm("detailFrm", "/ebid/bidPartcptSttusDetail.do"); 
			FwkCmmnUtil.submitForm("detailFrm", "/ebid/bidTchnEstmDetail.do"); 
		}else if(no == "3"){	//개찰OPNG
//			FwkCmmnUtil.submitForm("detailFrm", "/ebid/opengManageDetail.do");
			FwkCmmnUtil.submitForm("detailFrm", "/ebid/bidOpenDetail.do");
		}else if(no == "4"){	//기술협상TCHN_NEGO
			FwkCmmnUtil.submitForm("detailFrm", "/ebid/bidTchnNegoDetail.do");
		}else if(no == "5"){	//낙찰SBID
			FwkCmmnUtil.submitForm("detailFrm", "/ebid/bidSbidDetail.do");
		}
		
	};
	

	//파일을 첨부했을경우
	fileSet = function(obj){
		var node = $(obj).parent().parent();
		node.find("input[type=file]").prop("name","P_FILE");
		node.find("input[name=P_FILE_DOC_SECD]").prop("disabled", "");
	};
	
	
	//파일 수정버튼을 
	fileModBtn = function(obj){
		var td = $(obj).parent().parent();	//td
		td.hide();	//보여주는 td
		td.next().show();	// 다음td, 수정가능한 td
		td.next().find("input[type=file]").prop("name","P_FILE");
	};
	
	//파일 취소버튼을 
	fileCancleBtn = function(obj){
		var td = $(obj).parent().parent();	//td
		td.hide();	//수정가능한 td 숨기기
		td.prev().show();	// 이전 td, 보여주는 td
	};
	
	

	// 목록
	pageObj.bidPblancPrcnList = function() {
		FwkCmmnUtil.submitForm("listFrm", "/ebid/bidPblancPrcnList.do");
	};
	
	
	// 수정양식
	pageObj.bidPblancPrcnUpdtForm = function() {
		FwkCmmnUtil.submitForm("updtFrm", "/ebid/bidPblancPrcnUpdtForm.do");
	};
	
	/*// 삭제
	pageObj.bidReqDelete = function() {
		var jsonData = $("#updtFrm").serializeObject();
		var actionUrl = "/prpo/bidReqDelete";

		FwkCmmnUtil.submitAjax (actionUrl, jsonData
			, function(res) {
				alert(FwkMssageUtil.getMessage("COM.INF.002", "삭제"));
				pageObj.bidReqPrcnList();
			}
		);
	};*/
	
	// 
	pageObj.bidReqProgStat = function(obj,stat) { 
		$("#updtFrm input[name='P_BID_PSCD']").val(obj);	//상태
		$("#updtFrm input[name='P_HIST_RMK']").val(stat);	//진행상태_한글
		FwkCmmnUtil.submitForm("updtFrm", "/prpo/bidReqProgStat.do");
	};
	
	//개찰등록 
	//pageObj.bidReqProgStat("A017","개찰등록");
	pageObj.openResultRegist = function(){
		
		$("#detailFrm input[name='P_BID_PSCD']").val("A017");	//상태
		$("#detailFrm input[name='P_HIST_RMK']").val("");	//진행상태_한글
		removeComma();
		FwkCmmnUtil.submitForm("detailFrm", "/ebid/openResultRegist.do");
	};
	  
	
	// 진행이력 팝업
	pageObj.bidReqProgHistPopup = function() {
		$("#popupFrm").one("submit", function() {
			window.open("", "bidReqProgPopup", "width=750px,height=760px,toolbar=no,status=no,scrollbars=yes,resizeable=no,menubar=no,left=500,top=100");
			this.action = FwkCmmnUtil['contextPath']+'/comm/popup/bidReqProgPopup.do';
	        this.method = 'POST';
	        this.target = 'bidReqProgPopup';
	    }).trigger("submit"); 
	};
	
	pageObj.chgContSecd = function(obj){
		var contSecdCd = $(obj).val();
		
		if(contSecdCd==0){
			$("#dlgdPlcNm").text("납품장소");
		}else if(contSecdCd == 1){
			$("#dlgdPlcNm").text("용역현장");
		}else if(contSecdCd == 2){
			$("#dlgdPlcNm").text("공사현장");
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
	
	download = function(grpNo, sn) {
		$("#downloadFrm input[name='P_FILE_GRP_NO']").val(grpNo);
		$("#downloadFrm input[name='P_FILE_SN']").val(sn);
		FwkCmmnUtil.submitForm("downloadFrm", "/comm/download.do" );
	};
	
	/*pageObj.vendSearchEvent = function () {
		// 팝업처리
		$("#vendPopupFrm").one("submit", function() {
			window.open("", "vendPopupFrmPopup", "width=740px, height=800px, toolbar=no, status=no, scrollbars=yes, menubar=no,left=500,top=100");
			this.action = FwkCmmnUtil['contextPath']+'/comm/popup/entrpsList.do';
	        this.method = 'POST';
	        this.target = 'vendPopupFrmPopup';
	    }).trigger("submit");
	};
	
	
	
	*//**
	 * 지명업체 삭제 이벤트 발생시
	 * 1. 체크된 지명업체 Row를 삭제한다.
	 *//*
	pageObj.vendDeleteEvent = function () {
		var isChoice = false;
		var temp = "";
		
		$("input[name='chargerCbx']").each(function(index) {
			if($(this).prop("checked")){
				temp += $(this).next().next().val()+"/";
				isChoice = true;

			}
		});
		
		if(!isChoice){
			alert("업체를 선택해 주시기 바랍니다.") ;
			return ;
		}
		
		$("#deleteFrm input[name='P_VEND_REG_NO']").val(temp);
		var jsonData = $("#deleteFrm").serializeObject();
		var actionUrl = "/ebid/vendDelete";
		
		FwkCmmnUtil.submitAjax (actionUrl, jsonData
				, function(res) {
					FwkCmmnUtil.submitForm("detailFrm", "/ebid/bidTchnEstmDetail.do"); 
				}
		);
	};
		
	
	//entrpsListAdd(arr_vend_reg_no, arr_bizrno, arr_vend_nm, arr_rprs_nm,arr_chrgr_nm,arr_tel_no,arr_emal_addr);
	entrpsListAdd = function(arr_vend_reg_no, arr_bizrno, arr_vend_nm, arr_rprs_nm, arr_chrgr_nm,arr_tel_no,arr_emal_addr){

		var regNo = "";
		var bizrNo = "";
		var vendNm = "";
		var rprsNm = "";
		var chrgrNm = "";
		var telNo = "";
		var emalAddr = "";
		
		$(arr_vend_reg_no).each(function(inx, item){
			regNo  += arr_vend_reg_no[inx]+"/";
			bizrNo += arr_bizrno[inx]+"/";
			vendNm += arr_vend_nm[inx]+"/";
			rprsNm += arr_rprs_nm[inx]+"/";
			chrgrNm += arr_chrgr_nm[inx]+"/";
			telNo += arr_tel_no[inx]+"/";
			emalAddr += arr_emal_addr[inx]+"/";
		});
		
		$("#registFrm input[name='P_VEND_REG_NO']").val(regNo);
		$("#registFrm input[name='P_BIZRNO']").val(bizrNo);
		$("#registFrm input[name='P_VEND_NM']").val(vendNm);
		$("#registFrm input[name='P_RPRS_NM']").val(rprsNm);
		$("#registFrm input[name='P_CHRGR_NM']").val(chrgrNm);
		$("#registFrm input[name='P_TEL_NO']").val(telNo);
		$("#registFrm input[name='P_EMAL']").val(emalAddr);
		
		var jsonData = $("#registFrm").serializeObject();
		
		//업체저장
		var actionUrl = "/ebid/vendRegist";
		
		FwkCmmnUtil.submitAjax (actionUrl, jsonData
				, function(res) {
			
				var failVendNm = "";
				var succVendNm = "";
				$(res.failVendNmList).each(function(inx, item){
					if(inx == 0){
						failVendNm += res.failVendNmList[inx];
					}else{
						failVendNm += ","+res.failVendNmList[inx];
					}
				});
				$(res.succVendNmList).each(function(inx, item){
					if(inx == 0){
						succVendNm += res.succVendNmList[inx];
					}else{
						succVendNm += ","+res.succVendNmList[inx];
					}
				});
				if(res.failVendNmList.length > 0){
					alert("["+failVendNm+"]는 이미 등록된 업체입니다.");
				}
			
				if(res.succVendNmList.length > 0){ 
					alert("["+succVendNm+"]업체가 추가되었습니다.");
				}
				
				FwkCmmnUtil.submitForm("detailFrm", "/ebid/bidTchnEstmDetail.do");
				
			});
	};*/
	
	//기술평가팝업
	tchnEstmPopup = function (vendRegNo, bidSbmtFscd, gbn) {
		$("#tchnEstmFrm input[name='P_VEND_REG_NO']").val(vendRegNo);
		$("#tchnEstmFrm input[name='P_BID_SBMT_FSCD']").val(bidSbmtFscd);
		$("#tchnEstmFrm input[name='P_GBN']").val(gbn);
		// 팝업처리
		$("#tchnEstmFrm").one("submit", function() {
			window.open("", "tchnEstmFrmPopup", "width=740px, height=830px, toolbar=no, status=no, scrollbars=yes, menubar=no,left=500,top=50");
			this.action = FwkCmmnUtil['contextPath']+'/ebid/popup/tchnEstmPopup.do';
	        this.method = 'POST';
	        this.target = 'tchnEstmFrmPopup'; 
	    }).trigger("submit");
	};
	
	
	filePopup = function (vendRegNo, bidSbmtFscd, gbn) {
		$("#tchnEstmFrm input[name='P_VEND_REG_NO']").val(vendRegNo);
		$("#tchnEstmFrm input[name='P_BID_SBMT_FSCD']").val(bidSbmtFscd);
		$("#tchnEstmFrm input[name='P_GBN']").val(gbn);
		// 팝업처리 
		$("#tchnEstmFrm").one("submit", function() {
			window.open("", "tchnEstmFrmPopup", "width=740px, height=400px, toolbar=no, status=no, scrollbars=yes, menubar=no,left=500,top=100");
			this.action = FwkCmmnUtil['contextPath']+'/ebid/popup/tchnFilePopup.do';
	        this.method = 'POST';
	        this.target = 'tchnEstmFrmPopup'; 
	    }).trigger("submit");
	};
	
	/**
	 * 산출내역서 등록
	 * @param vendRegNo
	 * @param bidSbmtFscd
	 */
	clcCntnFilePopup = function (vendRegNo, bidSbmtFscd, gbn) {
		$("#tchnEstmFrm input[name='P_VEND_REG_NO']").val(vendRegNo);
		$("#tchnEstmFrm input[name='P_BID_SBMT_FSCD']").val(bidSbmtFscd);
		$("#tchnEstmFrm input[name='P_GBN']").val(gbn);
		// 팝업처리 
		$("#tchnEstmFrm").one("submit", function() {
			window.open("", "tchnEstmFrmPopup", "width=740px, height=400px, toolbar=no, status=no, scrollbars=yes, menubar=no,left=500,top=100");
			this.action = FwkCmmnUtil['contextPath']+'/ebid/popup/clcCntnFilePopup.do';
	        this.method = 'POST';
	        this.target = 'tchnEstmFrmPopup'; 
	    }).trigger("submit");
	};
	

	vendRegNoAuto = function(obj){ 
		var node = $(obj).parent().parent();
		node.find("input[name='P_VEND_REG_NO']").val($(obj).val());
	}
	
	pageObj.vendAddEvent = function (){
		var copyRow = $("#vendHideTbdy").children().clone(true);
		
		copyRow.find("#vendCbx").attr({"name" : "vendCbx","id" : "vendCbx"+$("#vendCnt").val()});	//체크박스
		copyRow.find("#P_VEND_REG_NO").attr({"name" : "P_VEND_REG_NO","id" : "P_VEND_REG_NO"+$("#vendCnt").val()});// 거래처번호
		copyRow.find("#P_BIZRNO").attr({"name" : "P_BIZRNO","id" : "P_BIZRNO"+$("#vendCnt").val()});// 사업자번호
		copyRow.find("#P_VEND_NM").attr({"name" : "P_VEND_NM","id" : "P_VEND_NM"+$("#vendCnt").val()});// 업체명
		copyRow.find("#vendSch").attr({"name" : "vendSch","id" : "vendSch"+$("#vendCnt").val(), "value": $("#vendCnt").val()});	//업체검색버튼
		copyRow.find("#P_CHRGR_NM").attr({"name" : "P_CHRGR_NM","id" : "P_CHRGR_NM"+$("#vendCnt").val()});// 담당자
		copyRow.find("#P_TEL_NO").attr({"name" : "P_TEL_NO","id" : "P_TEL_NO"+$("#vendCnt").val()});// 전화번호
		copyRow.find("#P_EMAL_ADDR").attr({"name" : "P_EMAL_ADDR","id" : "P_EMAL_ADDR"+$("#vendCnt").val()});// 이메일
		//추가
		copyRow.find("#P_OPNG_RNK").attr({"name" : "P_OPNG_RNK","id" : "P_OPNG_RNK"+$("#vendCnt").val()});// 순위
		copyRow.find("#P_TNDR_AMT").attr({"name" : "P_TNDR_AMT","id" : "P_TNDR_AMT"+$("#vendCnt").val()});// 투찰금액
		copyRow.find("#P_TCHN_ESTM_SCR").attr({"name" : "P_TCHN_ESTM_SCR","id" : "P_TCHN_ESTM_SCR"+$("#vendCnt").val()});// 기술평가점수
		copyRow.find("#P_PRCE_SCR").attr({"name" : "P_PRCE_SCR","id" : "P_PRCE_SCR"+$("#vendCnt").val()});// 가격점수
		 
		copyRow.css({"display" : ""});
		$("#vendHideTbdy").css({"display" : "none"});
		$("#vendShowTbdy").append(copyRow);
		
		//업체가 없을 경우 문구
		//업체가 추가되면 없애기
		$("#vendEmptyTbdy").css({"display" : "none"});
		
		var vendCnt = Number($("#vendCnt").val()) + Number(1);
		$("#vendCnt").val(vendCnt);
	};
	
	
	//수기추가 
	pageObj.offVendAddEvent = function (){
		var copyRow = $("#offVendHideTbdy").children().clone(true);
		
		copyRow.find("#vendCbx").attr({"name" : "vendCbx","id" : "vendCbx"+$("#vendCnt").val()});	//체크박스
		copyRow.find("#P_VEND_REG_NO").attr({"name" : "P_VEND_REG_NO","id" : "P_VEND_REG_NO"+$("#vendCnt").val()});// 거래처번호
		copyRow.find("#P_BIZRNO").attr({"name" : "P_BIZRNO","id" : "P_BIZRNO"+$("#vendCnt").val()});// 사업자번호
		copyRow.find("#P_VEND_NM").attr({"name" : "P_VEND_NM","id" : "P_VEND_NM"+$("#vendCnt").val()});// 업체명
		copyRow.find("#P_CHRGR_NM").attr({"name" : "P_CHRGR_NM","id" : "P_CHRGR_NM"+$("#vendCnt").val()});// 담당자
		copyRow.find("#P_TEL_NO").attr({"name" : "P_TEL_NO","id" : "P_TEL_NO"+$("#vendCnt").val()});// 전화번호
		copyRow.find("#P_EMAL_ADDR").attr({"name" : "P_EMAL_ADDR","id" : "P_EMAL_ADDR"+$("#vendCnt").val()});// 이메일
		//추가
		copyRow.find("#P_OPNG_RNK").attr({"name" : "P_OPNG_RNK","id" : "P_OPNG_RNK"+$("#vendCnt").val()});// 순위
		copyRow.find("#P_TNDR_AMT").attr({"name" : "P_TNDR_AMT","id" : "P_TNDR_AMT"+$("#vendCnt").val()});// 투찰금액
		copyRow.find("#P_TCHN_ESTM_SCR").attr({"name" : "P_TCHN_ESTM_SCR","id" : "P_TCHN_ESTM_SCR"+$("#vendCnt").val()});// 기술평가점수
		copyRow.find("#P_PRCE_SCR").attr({"name" : "P_PRCE_SCR","id" : "P_PRCE_SCR"+$("#vendCnt").val()});// 가격점수
		 
		copyRow.css({"display" : ""});
		$("#offVendHideTbdy").css({"display" : "none"});
		$("#vendShowTbdy").append(copyRow);
		
		//업체가 없을 경우 문구
		//업체가 추가되면 없애기
		$("#vendEmptyTbdy").css({"display" : "none"});
		
		var vendCnt = Number($("#vendCnt").val()) + Number(1);
		$("#vendCnt").val(vendCnt);
	};
	
	//업체찾기 팝업
	pageObj.vendSerchPopup = function (obj) {
		$("#vendSn").val($(obj).val());	//업체순번
		
		$("#vendPopupFrm").one("submit", function() {
			window.open("", "vendPopupFrmPopup", "width=740px, height=800px, toolbar=no, status=no, scrollbars=yes, menubar=no,left=500,top=100");
			this.action = FwkCmmnUtil['contextPath']+'/comm/popup/entrpsList.do';
	        this.method = 'POST';
	        this.target = 'vendPopupFrmPopup'; 
	    }).trigger("submit");
		
	};
	
	/**
	 * 업체찾기 팝업 에서 업체 선택 시 Event
	 * 업체찾기 팝업 화면에서 받아 온 업체 정보를 부모창에 적용한다.
	 */
	entrpsListAdd = function(vendRegNo,bizrno, vendNm, rprsNm, chrgrNm, telNo, emalAddr){ 
		
		// 중복체크
		var flag=true;
		$("input[name='P_VEND_REG_NO']").each(function(){
			if($(this).val() == vendRegNo){
				flag = false;
				alert("이미 선택한 업체가 존재합니다.");
				return false;
			}
		}); 
		
		if(flag == true){
			var vendSn  = $("#vendSn").val();
			$("#P_VEND_REG_NO"+ vendSn).val(vendRegNo);
			$("#P_BIZRNO"+ vendSn).val(bizrno);
			$("#P_VEND_NM"+ vendSn).val(vendNm);
			$("#P_CHRGR_NM"+ vendSn).val(chrgrNm);
			$("#P_TEL_NO"+ vendSn).val(telNo);
			$("#P_EMAL_ADDR"+ vendSn).val(emalAddr);
		}
		
	};
	
	reChgBtn = function(vendRegNo,fscd,gbn){
		$("#vendShowTbdy input[name=P_VEND_REG_NO]").each(function(){
		var node = $(this).parent().parent().find("button[name=" + fscd + "]");
			if($(this).val() == vendRegNo){
				if(gbn==""){
					node.text("보기");
					node.prev($("input[type='hidden']")).val("Y");
				}else if(gbn=="ELGB"){
					node.text("적격");
					node.prev($("input[name='tchnSAVEYN']")).val("Y");
				}else if(gbn=="NT_ELGB"){
					node.text("부적격");
					node.prev($("input[name='tchnSAVEYN']")).val("Y");
				}
			}
		});
	};
	
	pageObj.vendDelEvent = function(){

		$("#vendShowTbdy input[name='vendCbx']").each(function(inx){
			if(this.checked) {
				var vendSn = $(this).next().val();
				if($("input[name='P_VEND_SN']").val()){
					$("input[name='P_VEND_SN']").val($("input[name='P_VEND_SN']").val()+","+vendSn);
				}else{
					$("input[name='P_VEND_SN']").val(vendSn);
				}
				$(this).parent().parent().remove(); 
			}
		}); 
	};
	
	pageObj.vendSave = function(){
		
		var flag = true;
		$("#vendShowTbdy input[name=P_BIZRNO]").each(function(index) {	
			if($(this).val() != "" && flag == true ){	
				flag = true;
			}else{
				flag = false;
				return false
			}
		}); 
		
		$("#vendShowTbdy input[name=P_VEND_NM]").each(function(index) {	
			if($(this).val() != "" && flag == true ){	
				flag = true;
			}else{
				flag = false;
				return false
			}
		}); 
	/*	
		$("#vendShowTbdy input[name=P_CHRGR_NM]").each(function(index) {	
			if($(this).val() != "" && flag == true ){	
				flag = true;
			}else{
				flag = false;
				return false
			}
		}); 
		
		$("#vendShowTbdy input[name=P_TEL_NO]").each(function(index) {	
			if($(this).val() != "" && flag == true ){	
				flag = true;
			}else{
				flag = false;
				return false
			}
		}); 
		
		$("#vendShowTbdy input[name=P_EMAL_ADDR]").each(function(index) {	
			if($(this).val() != "" && flag == true ){	
				flag = true;
			}else{
				flag = false;
				return false
			}
		}); 
		*/
		if(!flag){
			alert("업체정보를 등록해주세요.");
			return false;
		}
		
		if(!confirm("업체정보를 저장 하시겠습니까?")){
			return false;
		}
		 
		removeComma();

		$("input[name=P_BIZRNO]").each(function(){ 
			$(this).val($(this).val().replace( /-/g, ""));
		});
		
		//업체저장
		var jsonData = $("#detailFrm").serializeObject();
		var actionUrl = "/ebid/vendRegist"; 
		
		FwkCmmnUtil.submitAjax (actionUrl, jsonData
				, function(res) { 
			FwkCmmnUtil.submitForm("detailFrm", "/ebid/bidOpenDetail.do");
		});
	};
	 

	// 반려 팝업
	pageObj.bidReqRtnPopup = function() {
		$("#popupFrm").one("submit", function() {
			window.open("", "bidReqRtnPopup", "width=750px,height=500px,toolbar=no,status=no,scrollbars=yes,resizeable=no,menubar=no,left=500,top=100");
			this.action = FwkCmmnUtil['contextPath']+'/ebid/popup/bidRtnRsnPopup.do';
	        this.method = 'POST';
	        this.target = 'bidReqRtnPopup';
	    }).trigger("submit"); 
	};

	//반려 처리 후에 다시 detail 불러오기 
	reDetail = function() {
		FwkCmmnUtil.submitForm("updtFrm", "/ebid/bidOpenDetail.do"); 
	}
	
	/**
	 * 페이지내에서 필요한 항목의 이벤트를 바인딩한다.
	 * 1. 목록이동버튼(#listBtn) 의 click 이벤트를 binding 한다.
	 *   - 목록이동버튼에 click 이벤트 발생시 사전공고현황 목록조회 함수를 호출한다.
	 * 2. 저장버튼(#saveBtn) 의 click 이벤트를 binding 한다.
	 *   - 저장버튼에 click 이벤트 발생시 사전공고현황 등록 함수를 호출한다.
	 */	
	pageObj.setEventHandler = function() {
		
		// 업체추가 버튼
		/*$("#vendAddBtn").on("click", function() {
			pageObj.vendSearchEvent();
		});
		
		// 업체삭제 버튼
		$("#vendDelBtn").on("click", function() {
			if(!confirm("업체를 삭제 하시겠습니까?")){
				return false;
			}
			pageObj.vendDeleteEvent();
		});*/
		
		//업체추가
		$("#vendAddBtn").on("click", function() {
			pageObj.vendAddEvent();
		});
		
		//수기추가
		$("#offVendAddBtn").on("click", function() {
			pageObj.offVendAddEvent();
		});
		//업체삭제
		$("#vendDelBtn").on("click", function() {
			pageObj.vendDelEvent();
		});
		//업체저장
		$("#vendSaveBtn").on("click", function() {
			pageObj.vendSave();
		});
		//업체 검색버튼
		$("button[name=vendSch]").on("click", function() {
			pageObj.vendSerchPopup(this);
		});
		
		// 취소버튼
		$("#listBtn").on("click", function() {
			if(!confirm("목록화면으로 이동하시겠습니까?")){
				return false;
			}
			pageObj.bidPblancPrcnList(); 
		});
		
		// 수정버튼
		$("#updtFormBtn").on("click", function() {
			if(!confirm("수정화면으로 이동하시겠습니까?")){
				return false;
			}
			pageObj.bidPblancPrcnUpdtForm();
		});
		
		// 저장완료
		$("#statChnBtn").on("click", function() { 

			var flag = false;
			
			$("input[name=btnYn]").each(function(index) {	
				//버튼이 안보이면 아직 업체정보저장을 안했다는 거
				if($(this).val() == "Y"){	
					flag = true;
				}else{
					flag = false;
				}
			}); 
			

			if(!flag){
				alert("업체정보를 먼저 저장해 주세요.");
				return false;
			}
			
			
			if($("#openFileReg").val() == "reg"){
				if($("input[type=file]").val() == ""){
					alert("개찰결과보고서를 등록해주세요.");
					return false;
				}  
				
				if($("input[name=P_FILE]").val() == "undefined"){
					alert("개찰결과보고서를 등록해주세요.");
					return false;
				}       
			}
			
			
			$("#vendShowTbdy #P_OPNG_RNK").each(function(index) {
				if($(this).val() != "" && flag == true ){	
					flag = true;
				}else{
					flag = false;
					return false;
				}
			}); 
			
			//산출내역서는 한개만 등록해도 넘어가야함
			if(!flag == false){
				$("#vendShowTbdy input[name=clcSAVEYN]").each(function(index) {
					if($(this).val() == "Y"){
						flag = true;
						return false;
					}else{
						flag = false;
					}
				}); 
			}
			
			$("#vendShowTbdy input[name=P_TNDR_AMT]").each(function(index) {
				if($(this).val() != "" && flag == true ){	
					flag = true;
				}else{
					flag = false;
					return false;
				}
			});
			
			
			if($("#sbidMtcd").val() == '33' || $("#sbidMtcd").val() == '34' || $("#sbidMtcd").val() == '40'){
				//제안/규격서
				$("#vendShowTbdy input[name=SAVEYN]").each(function(index) {
					if($(this).val() == "Y" && flag == true ){	
						flag = true;
					}else{
						flag = false;
						return false;
					}
				}); 
				
				//평가결과
				$("#vendShowTbdy input[name=tchnSAVEYN]").each(function(index) {
					if($(this).val() == "Y" && flag == true ){	
						flag = true;
					}else{
						flag = false;
						return false;
					}
				}); 
				
				
				$("#vendShowTbdy input[name=P_TCHN_ESTM_SCR]").each(function(index) {
					if($(this).val() != "" && flag == true ){	
						flag = true;
					}else{
						flag = false;
						return false;
					}
				}); 
				
				$("#vendShowTbdy input[name=P_PRCE_SCR]").each(function(index) {
					if($(this).val() != "" && flag == true ){	
						flag = true;
					}else{
						flag = false; 
						return false;
					}
				});
			}
			 
			
			if(!flag){
				alert("등록하지 않은 개찰정보가 존재합니다.") ;
				return false;
			}
			
			if(!confirm("개찰등록 하시겠습니까?")){
				return false;
			}
			
			$("input[name=P_BIZRNO]").each(function(){ 
				$(this).val($(this).val().replace( /-/g, ""));
			});
			
			pageObj.openResultRegist();
			
		});
		
		
		
		// 진행이력버튼
		$("#progHistBtn").on("click", function() {
			pageObj.bidReqProgHistPopup();
		});
		 
		// 승인요청
		$("#applyReqBtn").on("click", function() { 
			if(!confirm("개찰승인요청 하시겠습니까?")){
				return false;
			}
			pageObj.bidReqProgStat("A018","");
		});
		
		// 승인 _경영지원팀장
		$("#applyBtn").on("click", function() { 
			if(!confirm("개찰 승인하시겠습니까?")){
				return false;
			}
			pageObj.bidReqProgStat("A019","");
		});
		// 반려_경영지원팀장
		$("#rtnBtn").on("click", function() { 
			if(!confirm("개찰  반려하시겠습니까?")){
				return false;
			}
			$("#popupFrm input[name='P_BID_PSCD']").val("A020");
			pageObj.bidReqRtnPopup();
			//pageObj.bidReqProgStat("A012","기술평가 내부반려");
		});
		
	};
	
	/**
	 * window load
	 *
	 */ 
	$(function(){
		
		//33	낙찰방법코드	규격가격분리동시경쟁 
		//34	낙찰방법코드	2단계경쟁
		//40	낙찰방법코드	협상에 의한 계약
		//일 경우에만 기술평가, 기술협상 가능
		if($("#sbidMtcd").val() != "33" &&  $("#sbidMtcd").val() !="34" && $("#sbidMtcd").val() !="40" ){
			$("#2").attr("href","javascript:;");
			$("#2").attr("class","disa");
			$("#4").attr("href","javascript:;"); 
			$("#4").attr("class","disa"); 
		};
		
		pageObj.setEventHandler();
	});
})();