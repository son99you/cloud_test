/**
 * 마이페이지 > 자사정보
 *
 * <pre>
 * user
 *    |_ vendorInfoMgr.js
 * 
 * </pre>
 * @date : 2017. 06. 15. 오전 11:50:24
 * @version : 1.0
 * @author : 은우소프트 손연우
 */
(function() {

	/**
	 * Default Constructor
	 */
	pageObj = {};

	/**
	 * Default Form Name
	 */
	var defaultFrm = "registFrm";
	

	// 수정
	pageObj.apprWaitVendUpdt = function() {
		FwkCmmnUtil.submitForm(defaultFrm, "/opro/user/vendInfoUpdt.do");
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
		row.find("input[type='file']").attr("name",fileName); 
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
		var copyRow = $("#"+fileRow).children().clone(true);
		copyRow.css({"display" : ""});
		
		copyRow.find("input[type='file']").attr("name",fileName);
		
		copyRow.find(".date").datepicker({
		    showOn: "both", //엘리먼트와 이미지 동시 사용(both,button)
		    //한글 지정
		    regional : "ko-KR",
	        buttonImageOnly: true, //이미지표시
			buttonText: '달력선택', //버튼 텍스트 표시
			buttonImage: '../../statics/images/ipro/icon/calendar_icon01.png' //이미지주소
		});
		
		$("#"+fileDiv).append(copyRow);
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
		
		// btn value 
		$("#itemPopupFrm #searchGbnId").val("Item" + $(obj).val());
		
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
						.append($('<td>')
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
						.append($('<td>')
								.append($('<input>').attr({'type':'text','name':'P_ITEM_UPRC'}).addClass('money').css({'width':'95%', 'maxlength':'12', 'text-align':'right'}).val(0))
									)
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
	
	
	/**
	 * 페이지내에서 필요한 항목의 이벤트를 바인딩한다.
	 * 1. 목록버튼(#listBtn) 의 click 이벤트를 binding 한다.
	 *   - 목록버튼에 click 이벤트 발생시 입찰계획목록조회 함수를 호출한다.
	 */	
	pageObj.setEventHandler = function() {
		
		// 목록버튼
		$("#listBtn").on("click", function() {
			pageObj.apprWaitVendList();
		});
		
		
		// 수정
		$("#saveBtn").on("click", function() {
			
			var flag = true;
			
			var accTr = $("#accShowTbdy tr").length;
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
			});
			
			if(!flag){
				return false; 
			}
			
			$("#accShowTbdy input[name='P_ACNO']").each(function(){
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
			
			// 첨부파일 필수체크
			$("#accShowTbdy input[name='P_ACCT_FILE']").each(function(){
				if($(this).val() == "" || $(this).val() == null){
					alert("[통장사본] 항목은 필수입력입니다.");
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
			
			
			if(!confirm(FwkMssageUtil.getMessage("COM.CON.001", "수정"))){
				return false;
			}
			
			removeComma();
			$("#accHiddTbdy").children().remove();
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
			
			pageObj.apprWaitVendUpdt();
			
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
		
	};

	/**
	 * window load
	 *
	 */
	$(function(){
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