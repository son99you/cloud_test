(function() {

	/**
	 * Default Constructor
	 */
	pageObj = {};
	
	/**
	 * Default Form Name
	 */
	var defaultFrm = "registFrm";
	
	var file1 = null;   // 사업자등록증
	var file2 = null;   // 신용평가등급자료
	var file3 = null;   // 최근년도 결산 재무재표
	var file4 = null;   // 회사소개자료 카타로그
	var file5 = null;   // 인증서 등 자료
	var file6 = null;   // 면허수첩, 면허증사본
	var file7 = null;   // 기타자료
	
	
	// 등록
	pageObj.vendMngeRegist = function () {
		FwkCmmnUtil.submitForm("registFrm", "/vend/vendMngeRegist.do");
	};
	
	// SG추가 버튼
	pageObj.sgAddEvent = function () {
		var copyRow = $("#sgDiv #sgAddHiddTbdy").children().clone(true);
		copyRow.css({"display" : ""});
		$("#sgDiv #sgAddShowTbdy").append(copyRow);
		
	};
	
	// 삭제 이벤트
	deleteFn = function(obj){
		$(obj).parent().parent().remove();
	};

	// 주거래SG 클릭 이벤트
	mainSgEvent = function(obj){
		if($(obj).prop("checked")){
			$(obj).parent().parent().find("input[name='P_MAIN_SG_AT_CHK']").val("Y");
		}else{
			$(obj).parent().parent().find("input[name='P_MAIN_SG_AT_CHK']").val("N");
		}
	};
	
	// 담당자추가 버튼
	pageObj.userAddEvent = function () {
		var copyRow = $("#userDiv #userAddHiddTbdy").children().clone(true);
		copyRow.css({"display" : ""});
		$("#userDiv #userAddShowTbdy").append(copyRow);
	};
	
	// 품목등록 추가 팝업
	pageObj.itemListPopup = function() {
		$("#itemListPopupFrm").one("submit", function() {
			window.open("", "itemListPopup", "width=750px, height=650px, toolbar=no, status=no, resizeable=no, scrollbars=no, menubar=no, left="+x(740)+",top="+y(600));
			this.action = FwkCmmnUtil['contextPath']+'/comm/popup/itemList.do';
	        this.method = 'POST';
	        this.target = 'itemListPopup';
	    }).trigger("submit");
	};

	// 우편번호 팝업
	pageObj.zipListPopup = function() {
		$("#zipPopupFrm").one("submit", function() {
			window.open("", "zipListPopup", "width=750px, height=650px, toolbar=no, status=no, resizeable=no, scrollbars=no, menubar=no, left="+x(740)+",top="+y(600));
			this.action = FwkCmmnUtil['contextPath']+'/comm/popup/zipList.do';
			this.method = 'POST';
			this.target = 'zipListPopup';
		}).trigger("submit");
	};
	
	jusoCallBack = function(roadFullAddr,roadAddrPart1,addrDetail,roadAddrPart2,engAddr, jibunAddr, zipNo, admCd, rnMgtSn, bdMgtSn,detBdNmList,bdNm,bdKdcd,siNm,sggNm,emdNm,liNm,rn,udrtYn,buldMnnm,buldSlno,mtYn,lnbrMnnm,lnbrSlno,emdNo){
		$("#P_ZIP").val(zipNo);
		$("#P_RN_ADRES").val(roadAddrPart1);
		$("#P_DETAIL_ADRES").val(addrDetail);
	};
	
	
	// 첨부파일 추가
	rowAdd = function( fileDiv, fileRow, inputFile, fileName ){
		if($("#"+ fileDiv +" input").length == 0 || $(inputFile).val()){
			var row = $("#"+fileRow).clone();
			row.css("display","");
			row.attr("id","");
			row.find("input").eq(0).attr("name",fileName); 
			row.find("input").eq(1).removeAttr("disabled");  
			inputFile = row.find("input"); 
			inputFile.click();
			
			if($(inputFile).val() == "" || $(inputFile).val() == null) {
				
			}else {
				$("#"+fileDiv).append(row);
			}
		}else{
			inputFile.click();
		}
		
		if(fileDiv == "fileDiv1"){
			$("#fileBtn1").css("display", "none");
		}else if(fileDiv == "fileDiv2"){
			$("#fileBtn2").css("display", "none");
		}else if(fileDiv == "fileDiv3"){
			$("#fileBtn3").css("display", "none");
		}else if(fileDiv == "fileDiv4"){
			$("#fileBtn4").css("display", "none");
		}else if(fileDiv == "fileDiv5"){
			$("#fileBtn5").css("display", "none");
		}else if(fileDiv == "fileDiv6"){
			$("#fileBtn6").css("display", "none");
		}else if(fileDiv == "fileDiv7"){
			$("#fileBtn7").css("display", "none");
		}
		
		return inputFile;
	};
	
	fileChange = function(obj){
		$(obj).parent().append($(obj).val().substring($(obj).val().lastIndexOf("\\")+1));
	};
	
	// 첨부파일 삭제
	rowDel = function(obj){
		$(obj).parent().parent().parent().parent().children().eq(0).css("display", "");   // 해당 div의 검색 버튼 보이게 하기
		$(obj).parent().parent().remove();
	};
	
	pageObj.sgPopup = function() {
		$("#popupFrm").one("submit", function() {
			window.open("", "sgPopup", "width=740px,height=600px,toolbar=no,status=no,scrollbars=yes,resizeable=no,menubar=no,left="+x(740)+",top="+y(600));
			this.action = FwkCmmnUtil['contextPath']+'/vend/popup/sgPopup.do';
	        this.method = 'POST';
	        this.target = 'sgPopup';
	    }).trigger("submit");
	};
	/**
	 * 1. 담당자조회
	 */
	chargerList = function() {
		$("#popupFrm").one("submit", function() {
			window.open("", "chargerList", "width=740px,height=600px,toolbar=no,status=no,scrollbars=yes,resizeable=no,menubar=no,left="+x(740)+",top="+y(600));
			this.action = FwkCmmnUtil['contextPath']+'/comm/popup/chargerList.do';
	        this.method = 'POST';
	        this.target = 'chargerList';
	    }).trigger("submit");
	};

	
	
	/**
	 * 1.개요 : 탭클릭이벤트
	 * 2.처리내용 : 각 탭 클릭시 이동.
	 * @param no
	 * @param obj
	 */
	tabEvent = function(no,obj){
		
//		$(".tab_on").prev().children().eq(0).attr('src','/statics/images/ipro/tab/tab_off_1.gif');
//		$(".tab_on").next().children().eq(0).attr('src','/statics/images/ipro/tab/tab_off_2.gif');
		$(".on").attr('class','tapBtn');
		$('#'+no).attr('class','tapBtn on');
//		$('#'+no).prev().children().eq(0).attr('src','/statics/images/ipro/tab/tab_on_1.gif');    
//		$('#'+no).next().children().eq(0).attr('src','/statics/images/ipro/tab/tab_on_2.gif');    
		if(no == "1"){
			$("#tab1").css("display", "");
			$("#tab2").css("display", "none");
			
			$("#basic").css("display", "");
			$("#detail").css("display", "none");
			$("#contract").css("display", "none");
			$("#bidding").css("display", "none");
			
		}else if(no == "2"){
			$("#tab1").css("display", "none");
			$("#tab2").css("display", "");
			
			$("#basic").css("display", "none");
			$("#detail").css("display", "");
			$("#contract").css("display", "none");
			$("#bidding").css("display", "none");
		}
		
		/*$(".tab_on").attr('class','tab_off txtc');
		$('#'+no).attr('class','tab_on txtc');
		if(no == "1"){
			$("#basic").css("display", "");
			$("#detail").css("display", "none");
			$("#contract").css("display", "none");
			$("#bidding").css("display", "none");
		}else if(no == "2"){
			$("#basic").css("display", "none");
			$("#detail").css("display", "");
			$("#contract").css("display", "none");
			$("#bidding").css("display", "none");
		}else if(no == "3"){
			$("#basic").css("display", "none");
			$("#detail").css("display", "none");
			$("#contract").css("display", "");
			$("#bidding").css("display", "none");
		}else if(no == "4"){
			$("#basic").css("display", "none");
			$("#detail").css("display", "none");
			$("#contract").css("display", "none");
			$("#bidding").css("display", "");
		}*/
	};
	pageObj.setEventHandler = function() {
		$("#saveBtn1, #saveBtn2").on("click", function() {
			
			/*if($("#P_BIZRNO_1").val() == "" || $("#P_BIZRNO_1").val() == null){
				alert("[ 사업자등록번호 ] 항목은 필수입력 입니다.");
				$("#P_BIZRNO_1").focus();
				return false;
			}
			
			if($("#P_BIZRNO_2").val() == "" || $("#P_BIZRNO_2").val() == null){
				alert("[ 사업자등록번호 ] 항목은 필수입력 입니다.");
				$("#P_BIZRNO_2").focus();
				return false;
			}
			
			if($("#P_BIZRNO_3").val() == "" || $("#P_BIZRNO_3").val() == null){
				alert("[ 사업자등록번호 ] 항목은 필수입력 입니다.");
				$("#P_BIZRNO_3").focus();
				return false;
			}
			
			// 필수체크
			if(!required_ew()){
				return false;
			}
			*/
			
			// 주거래SG
			var P_MAIN_SG = 0;
			
			var count = 0; /// 주거래SG 체크를 위함
			
			$("input[name=P_MAIN_SG]").val("");
			
			$("input[name=P_MAIN_SG_CHK]").each(function(){
				var chk = "N";
				if(this.checked){
					chk="Y";
				}
				
				if(chk == "Y"){
					count++;
				}
				
				if(P_MAIN_SG == 0) {
					$("input[name=P_MAIN_SG]").val($("input[name=P_MAIN_SG]").val()+ chk);
				}else {
					$("input[name=P_MAIN_SG]").val($("input[name=P_MAIN_SG]").val()+ "," + chk);
				}
				P_MAIN_SG++;
			});
			
			if(count > 1){
				alert("주거래SG는 1개만 선택되어야합니다.");
				return false;
			}
			
			// 관심분야
			var P_CCN_SPHE = 0;
			
			$("input[name=P_CCN_SPHE]").val("");
			
			$("input[name=P_CCN_SPHE_CHK]").each(function(){
				var chk = "N";
				if(this.checked){
					chk="Y";
				}
				
				if(P_CCN_SPHE == 0) {
					$("input[name=P_CCN_SPHE]").val($("input[name=P_CCN_SPHE]").val()+ chk);
				}else {
					$("input[name=P_CCN_SPHE]").val($("input[name=P_CCN_SPHE]").val()+ chk);
				}
				P_CCN_SPHE++;
			});
			
			// 기술분야
			var P_TCHN_SPHE = 0;
			
			$("input[name=P_TCHN_SPHE]").val("");
			
			$("input[name=P_TCHN_SPHE_CHK]").each(function(){
				var chk = "N";
				if(this.checked){
					chk="Y";
				}
				
				if(P_CCN_SPHE == 0) {
					$("input[name=P_TCHN_SPHE]").val($("input[name=P_TCHN_SPHE]").val()+ chk);
				}else {
					$("input[name=P_TCHN_SPHE]").val($("input[name=P_TCHN_SPHE]").val()+ chk);
				}
				P_TCHN_SPHE++;
			});
			
			
			$("input[name=P_CTT_NM]").each(function(){
				if($(this).val() == null || $(this).val() == ""){
					$(this).parent().parent().remove();
				}
			});
			
			var text = confirm("협력사를 저장하시겠습니까?");
			if(text == true){
				$("#sgAddHiddTbdy").children().remove();
				$("#userAddHiddTbdy").children().remove();
			}else{
				return false;
			}
			
			pageObj.vendMngeRegist();
		});
		
		/**
		 * 1. SG추가 이벤트 
		 */
		$("#addBtn").on("click", function() {
				var tr = $("#copySg").clone();
				tr.css("display","");
				$("#sGFrame").append(tr);
		});
		/**
		 * 1. SG설명 팝업창 
		 */
		$("#sgPopupBtn").on("click", function() {
			pageObj.sgPopup();
		});
		/**
		 * 1. 담당자등록 이벤트 
		 */
		
		$("#addBtn1").on("click", function() {
			var tr = $("#copySbcnt").clone();
			tr.css("display","");
			$("#sbcntFrame").append(tr);
		});
		
		/**
		 * 1. 품목등록 이벤트 
		 */
		$("#addBtn2").on("click", function() {
			$("#popupFrm").one("submit", function() {
				window.open("", "vendMngeItemList", "width=740px,height=600px,toolbar=no,status=no,scrollbars=yes,resizeable=no,menubar=no,left="+x(740)+",top="+y(600));
				this.action = FwkCmmnUtil['contextPath']+'/vend/popup/vendMngeItemList.do';
		        this.method = 'POST';
		        this.target = 'vendMngeItemList';
		    }).trigger("submit");
		});
		/**
		 * 1. 담당자조회 버튼 이벤트. 
		 */
		$("#searchBtn1").on("click", function(){
			chargerList();
		});
		
		
		// SG추가 버튼 클릭 이벤트
		$("#sgAddBtn").on("click", function() {
			pageObj.sgAddEvent();
		});

		// 담당자추가 버튼 클릭 이벤트
		$("#userAddBtn").on("click", function() {
			pageObj.userAddEvent();
		});
		
		// 품목등록 추가 버튼 클릭 이벤트
		$("#itemAddBtn").on("click", function() {
			pageObj.itemListPopup();
		});

		// 우편번호 버튼 클릭 이벤트
		$("#zipBtn").on("click", function() {
			pageObj.zipListPopup();
		});
		
		// 첨부파일 : 사업자등록증
		$("#fileBtn1").on("click", function(){
			file1 = rowAdd("fileDiv1", "fileRow1", file1, "P_FILE");
		});
		
		// 첨부파일 : 신용평가등급자료
		$("#fileBtn2").on("click", function(){
			file2 = rowAdd("fileDiv2", "fileRow2", file2, "P_FILE");
		});
		
		// 첨부파일 : 최근년도 결산 재무재표
		$("#fileBtn3").on("click", function(){
			file3 = rowAdd("fileDiv3", "fileRow3", file3, "P_FILE");
		});
		
		// 첨부파일 : 회사소개자료 카타로그
		$("#fileBtn4").on("click", function(){
			file4 = rowAdd("fileDiv4", "fileRow4", file4, "P_FILE");
		});
		
		// 첨부파일 : 인증서 등 자료 
		$("#fileBtn5").on("click", function(){
			file5 = rowAdd("fileDiv5", "fileRow5", file5, "P_FILE");
		});
		
		// 첨부파일 : 면허수첩, 면허증사본
		$("#fileBtn6").on("click", function(){
			file6 = rowAdd("fileDiv6", "fileRow6", file6, "P_FILE");
		});
		
		// 첨부파일 : 기타자료
		$("#fileBtn7").on("click", function(){
			file7 = rowAdd("fileDiv7", "fileRow7", file7, "P_FILE");
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