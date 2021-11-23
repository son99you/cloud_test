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
	var defaultFrm = "updtFrm";

	/**
	 * 1.개요 : 탭클릭이벤트 적용
	 * 2.처리내용 : 각 탭 클릭시 이동.
	 * 3. 처리일자 : 2018-11-08
	 * @param no
	 * @param obj
	 */
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
			FwkCmmnUtil.submitForm("updtFrm", "/ebid/bidPblancResultDetail.do");
		}else if(no == "2"){	//기술평가TCHN_ESTM
//			FwkCmmnUtil.submitForm("detailFrm", "/ebid/bidPartcptSttusDetail.do"); 
			FwkCmmnUtil.submitForm("updtFrm", "/ebid/bidTchnEstmResultDetail.do"); 
		}else if(no == "3"){	//개찰OPNG
		//	FwkCmmnUtil.submitForm("detailFrm", "/ebid/opengManageDetail.do");
			FwkCmmnUtil.submitForm("updtFrm", "/ebid/bidOpenResultDetail.do");
		}else if(no == "4"){	//기술협상TCHN_NEGO
			FwkCmmnUtil.submitForm("updtFrm", "/ebid/bidTchnNegoResultDetail.do");
		}else if(no == "5"){	//낙찰SBID
			FwkCmmnUtil.submitForm("updtFrm", "/ebid/bidSbidResultDetail.do");
		}
		
	};
	
	
	
	// 목록
	pageObj.bidPblancResultList = function() {
		FwkCmmnUtil.submitForm("listFrm", "/ebid/bidPblancResultList.do");
	};
	
	
	// 수정양식
/*	pageObj.bidPblancPrcnUpdtForm = function() {
		FwkCmmnUtil.submitForm("updtFrm", "/ebid/bidPblancPrcnUpdtForm.do");
	};*/
	
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
	
	
	download = function(grpNo, sn) {
		$("#downloadFrm input[name='P_FILE_GRP_NO']").val(grpNo);
		$("#downloadFrm input[name='P_FILE_SN']").val(sn);
		FwkCmmnUtil.submitForm("downloadFrm", "/comm/download.do" );
	};
	
	// 저장
	pageObj.bidReqPrcnUpdt = function() {
		fileUploadStart();
	};
	
	
	// 파일 업로드
	fileUploadStart = function() {
		RAONKUPLOAD.Transfer("uploadView1");
	};
	
	
	// 담당자 조회 팝업 
	pageObj.chargerInqirePopup = function(obj) {
		$("input[name='setChargerGbn']").val(obj);
		$("#popupFrm").one("submit", function() {
			window.open("", "chargerPopup", "width=750px,height=760px,toolbar=no,status=no,scrollbars=yes,resizeable=no,menubar=no,left=500,top=100");
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
	
	pageObj.itemDel = function(){
		$("#itemShowTbdy input[name='itemChoiseCbx']").each(function(inx){
			if(this.checked) {
				$(this).parent().parent().remove();
			}
		});
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
	

	
	pageObj.bfStndOpenYn = function(obj){
		if($(obj).val() == "Y"){ 
			$(".bfStndOpenTeSpan").show();
		}else{
			$(".bfStndOpenTeSpan").hide(); 
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
	};
	
	//파일 취소버튼을 
	fileCancleBtn = function(obj){
		var td = $(obj).parent().parent();	//td
		td.hide();	//수정가능한 td 숨기기
		td.prev().show();	// 이전 td, 보여주는 td
	};
	
	
	
	/**
	 * 낙찰자 선정 방식 선택에 따른 이벤트 발생시
	 * 1. 제한적최저가, 적격심사일 경우 합산비율을 낙찰하햔율로 변경
	 * 2. 협상에 의한 계약일 경우 낙찰하햔율을 합산비율로 변경
	 * 
	 * 10	낙찰방법코드	최저가
		20	낙찰방법코드	제한적최저가
		31	낙찰방법코드	적격심사
		33	낙찰방법코드	규격가격분리동시경쟁
		34	낙찰방법코드	2단계경쟁
		40	낙찰방법코드	협상에 의한 계약
		52	낙찰방법코드	제3자단가
		55	낙찰방법코드	수의계약
		

	 */
	//33	낙찰방법코드	규격가격분리동시경쟁
	//34	낙찰방법코드	2단계경쟁
	//40	낙찰방법코드	협상에 의한 계약
	//일 경우에만 기술평가, 기술협상 가능
	pageObj.sbidMtcdEvent = function (obj) {
		if($(obj).val() == "20" || $(obj).val() == "31"){// 제한적최저가 또는 적격심사일 경우
			//낙찰하한율
			$(".sbidlwst").css("display","");
			//합산비율
			$(".tchnScrRt").css("display","none");
			//합산비율 초기화
			$(".tchnScrRtInput").val("");

			//제안/규격서 제출기간 
			$(".prpdcTr").css("display","none");
			$(".prpdcInput").val(""); 
			
			$("#2").attr("href","javascript:;");
			$("#2").attr("class","disa");
			$("#4").attr("href","javascript:;");
			$("#4").attr("class","disa");
		}else if($(obj).val() == '40'){	//협상에 의한 계약
			//낙찰하한율
			$(".sbidlwst").css("display","none");
			//합산비율
			$(".tchnScrRt").css("display","");
			//낙찰하한율 초기화
			$(".sbidlwstInput").val("");
			
			//제안/규격서 제출기간 
			$(".prpdcTr").css("display","");
			

			$("#2").attr("href","javascript:tabEvent(2);");
			$("#2").attr("class",""); 
			$("#4").attr("href","javascript:tabEvent(4);");
			$("#4").attr("class",""); 
		}else if($(obj).val() == '34'|| $(obj).val() == '33'){	//2단계 경쟁 or 규격가격분리동시
			//낙찰하한율
			$(".sbidlwst").css("display","none");
			//합산비율
			$(".tchnScrRt").css("display","none");
			//낙찰하한율 초기화
			$(".sbidlwstInput").val("");
			//합산비율 초기화
			$(".tchnScrRtInput").val("");
			
			
			//제안/규격서 제출기간 
			$(".prpdcTr").css("display","");
			

			$("#2").attr("href","javascript:tabEvent(2);");
			$("#2").attr("class",""); 
			$("#4").attr("href","javascript:tabEvent(4);");
			$("#4").attr("class",""); 
			
		}else{// 그 외
			//낙찰하한율
			$(".sbidlwst").css("display","none");
			//합산비율
			$(".tchnScrRt").css("display","none"); 
			//낙찰하한율 초기화
			$(".sbidlwstInput").val("");
			//합산비율 초기화
			$(".tchnScrRtInput").val("");
			
			//제안/규격서 제출기간 
			$(".prpdcTr").css("display","none");
			$(".prpdcInput").val("");
			
			$("#2").attr("href","javascript:;");
			$("#2").attr("class","disa");
			$("#4").attr("href","javascript:;");
			$("#4").attr("class","disa");
		}
	};
	
	
	pageObj.brfsYnEvent = function (obj) {
		if($(obj).val() == "Y"){	//입찰설명여부가 예일경우
			$(".brfsSpan").css("display","");
		}else{
			$(".brfsSpan").css("display","none");
			$(".brfsInput").val(""); 
		} 
	};
	
	
	// 부서 조회 팝업
	pageObj.deptInqirePopup = function() {
		$("#popupFrm").one("submit", function() {
			window.open("", "deptPopup", "width=750px,height=760px,toolbar=no,status=no,scrollbars=yes,resizeable=no,menubar=no,left=500,top=100");
			this.action = FwkCmmnUtil['contextPath']+'/comm/popup/deptList.do';
	        this.method = 'POST';
	        this.target = 'deptPopup';
	    }).trigger("submit");
	};
	
	// 품목 조회 팝업
	pageObj.itemInqirePopup = function(obj) {
		$("#itemSn").val($(obj).val());
		$("#popupFrm").one("submit", function() {
			window.open("", "itemPopup", "width=750px,height=760px,toolbar=no,status=no,scrollbars=yes,resizeable=no,menubar=no,left=500,top=100");
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
		$("#P_ITEM_NM"+ sn).val(itemNm);
		$("#P_STND_NM"+ sn).prev().find("option:eq(" + itemUncd +")").prop("selected", true);
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
	}
	
	//계약구분에 따른 필수첨부파일 
	contSecdSet = function(){ 
		$(".essfileList").html(essFileSet());
	}
	
	//필수첨부파일 세팅 
	essFileSet = function(){
		$(".essfileList").empty();  

		//변함없을경우
		if( $("#contSecd").val() == $("#P_CONT_SECD").val() 
			&& $("#contMtcd").val() == $("#P_CONT_MTCD").val()
			&& $("#esttPrceM").val() == $("#P_ESTT_PRCE_M").val()){	
				$(".essfileShowList").show();  
				
		//변화있을경우
		}else{	
			$(".essfileShowList").hide();  
			
			var jsonData = {'P_CONT_SECD' : ''+ $("#P_CONT_SECD").val() +'','P_CONT_MTCD' : ''+ $("#P_CONT_MTCD").val() +'','P_ESTT_PRCE_M' : ''+ $("#P_ESTT_PRCE_M").val() +''};
			var actionUrl = "/prpo/selectEssFileCode"; 
			
			FwkCmmnUtil.submitAjax(actionUrl, jsonData, function(res) {
				if(res.fileCodeList.length > 0){  
					$(".essfileList").html(essfileList(res.fileCodeList));
				}
			}); 
		}
	} 

	
	// 수정 폼
	pageObj.bidReqPrcnUpdtForm = function() { 
		FwkCmmnUtil.submitForm("updtFrm", "/ebid/bidPblancPrcnUpdtForm.do");
	};
	
	/**
	 * 페이지내에서 필요한 항목의 이벤트를 바인딩한다.
	 * 1. 목록이동버튼(#listBtn) 의 click 이벤트를 binding 한다.
	 *   - 목록이동버튼에 click 이벤트 발생시 사전공고현황 목록조회 함수를 호출한다.
	 * 2. 저장버튼(#saveBtn) 의 click 이벤트를 binding 한다.
	 *   - 저장버튼에 click 이벤트 발생시 사전공고현황 등록 함수를 호출한다.
	 */	
	pageObj.setEventHandler = function() {
		
		//계약구분
		$("#P_CONT_SECD").on("change", function() {
			contSecdSet();
		});
		
		//계약방법
		$("#P_CONT_MTCD").on("change", function() {
			contSecdSet();
		});
		
		//추정가격 
		$("#P_ESTT_PRCE_M").on("blur", function(){ 
			contSecdSet();
		});
		
		
		// 취소버튼
		$("#listBtn").on("click", function() {
			if(!confirm("목록화면으로 이동하시겠습니까?")){
				return false;
			}
			pageObj.bidPblancResultList();
		});

		
		// 진행이력버튼
		$("#progHistBtn").on("click", function() {
			pageObj.bidReqProgHistPopup("Y");
		});
		
		//	재공고버튼 
		$("#reBidBtn").on("click", function() {
			$("#P_PRVT_CONT_YN").val("N");
			$("#P_ARAM_GBN").val($("#P_BID_PSCD").val()+"N");
			$("#P_BID_PSCD_TEXT").val("재공고 진행");
			FwkCmmnUtil.submitForm("updtFrm", "/prpo/bidReqProgStat.do");
		});
		
		//재공고취소버튼
		$("#reBidCnlBtn").on("click", function() {
			$("#P_PRVT_CONT_YN").val("");
			$("#P_ARAM_GBN").val($("#P_BID_PSCD").val()+"NC");
			$("#P_BID_PSCD_TEXT").val("재공고 진행 취소");
			FwkCmmnUtil.submitForm("updtFrm", "/prpo/bidReqProgStat.do");
		});
		
		// 수의계약버튼 
		$("#contProgBtn").on("click", function() {
			$("#P_PRVT_CONT_YN").val("Y");
			$("#P_ARAM_GBN").val($("#P_BID_PSCD").val()+"Y"); 
			$("#P_BID_PSCD_TEXT").val("수의계약 진행");
			FwkCmmnUtil.submitForm("updtFrm", "/prpo/bidReqProgStat.do");
		});
		
		// 수의계약취소버튼 
		$("#contProgCnlBtn").on("click", function() {
			$("#P_PRVT_CONT_YN").val("");
			$("#P_ARAM_GBN").val($("#P_BID_PSCD").val()+"YC"); 
			$("#P_BID_PSCD_TEXT").val("수의계약 진행 취소");
			FwkCmmnUtil.submitForm("updtFrm", "/prpo/bidReqProgStat.do");
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
		});
		$("select[name='P_CONT_MTCD']").on("change", function() {
			pageObj.chgContMtcd(this);
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
		
		//본지사 체인치 이벤트
		$("select[name='P_ARA_DEPT_CD']").on("change", function() {
			pageObj.contDeptFn(); 
		}); 
		//계약담당구분 체인치 이벤트
		$("input:radio[name='P_PRCH_CHRG_SECD']").on("click", function() {
			pageObj.contDeptFn(); 
		}); 
		
		// 낙찰자 선정 방식 선택에 따른 클릭/체인지 이벤트
		$("select[name='P_SBID_MTCD']").on("click", function() {
			pageObj.sbidMtcdEvent(this);
		}).on("change", function(){
			pageObj.sbidMtcdEvent(this);
		});
		//입찰설명회여부에 따른 이벤트 
		$("input:radio[name='P_BID_BRFS_YN']").on("click", function() {
			pageObj.brfsYnEvent(this);
		});
		//사전규격공개여부
		$("input:radio[name='P_BF_STND_OPEN_YN']").on("click", function() {
			pageObj.bfStndOpenYn(this);
		});
		
	};
	
	/**
	 * window load
	 * 
	 */
	$(function(){
		pageObj.setEventHandler();
		
		//계약구분
		pageObj.chgContSecd($("#contSecd"));
		//계약방법
		pageObj.chgContMtcd($("#contMtcd"));
		//낙찰방법
		pageObj.sbidMtcdEvent($("#sbidMtcd"));
		
		pageObj.bfStndOpenYn($("#bfStndOpenYn"));
		
		if($("#contMtcd").val() == "10003" ){	//계약방법이 수의일경우 
			pageObj.pvctRsnFn($("#P_PVCT_RSN_NO")); 
		};  
		
		/*if($("#contMtcd").val() == "10000" || $("#contMtcd").val() == "10003"){	//일반이나 수의 일경우에만 보임
*/			$(".essfileShowList").show(); 
		/*}*/
		
		fileEtcModify("updtFrm", "P_FILE_GRP_NO_ETC", "P_FILE_GRP_NO_NEW", "/prpo/bidReqPrcnUpdt.do");
	});
})();