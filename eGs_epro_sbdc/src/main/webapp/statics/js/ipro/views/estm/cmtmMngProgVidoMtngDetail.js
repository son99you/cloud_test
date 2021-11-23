(function() {

	/**
	 * Default Constructor
	 */
	pageObj = {};
	
	// 파일 업로드
	fileUploadStart = function() {
		RAONKUPLOAD.Transfer("uploadView1");
	};
	
	// 평가대상정보 첨부파일 상세 팝업
	pageObj.estmVidoFileView = function(VIDO_MTNG_SEQ, FILE_GRP_NO){
		//var P_ESTM_PSCD = $("#registFrm input[name='P_ESTM_PSCD']").val();
		
		$("#popupFrm input[name='P_VIDO_MTNG_SEQ']").val(VIDO_MTNG_SEQ);
		$("#popupFrm input[name='P_FILE_GRP_NO']").val(FILE_GRP_NO);
		$("#popupFrm").one("submit", function() {
			window.open("", "estmVidoFileViewPopup", "width=750px,height=500px,toolbar=no,status=no,scrollbars=yes,resizeable=no,menubar=no,left=600,top=300");
			this.action = FwkCmmnUtil['contextPath']+'/comm/popup/estmVidoFileView.do';
	        this.method = 'POST';
	        this.target = 'estmVidoFileViewPopup';
	    }).trigger("submit");
	};
	
	// addBtn
	pageObj.addRow = function() {
		var showTbdy = "showTbody";
		$("#" + showTbdy)
		.append($('<tr>')
			.append($('<td>').addClass("txt-center")
				.append($('<label>').addClass("component-checkbox")
					.append($('<input>').attr({'type':'checkbox','name':'chck'}))
					.append($('<i>'))))
			.append($('<td>')
				.append($('<input>').attr({'type':'text','name':'P_VIDO_MTNG_NM','maxlength':'300'}).addClass('component-input type-full')))
			.append($('<td>')
				.append($('<div>').addClass('area-calen')
					.append($('<div>').addClass('component-calen')
						.append($('<div>').addClass('data-calen')
							.append($('<input>').attr({'type':'text','name':'P_VIDO_ST_DE', 'date':''}).css({'width':'80px'}).addClass('component-input'))))
					.append($('<div>').addClass('component-time')
						.append($('<input>').attr({'type':'text','name':'P_VIDO_ST_HH', 'maxlength':'2', 'numeric':'', 'onkeyup':'fnTimeHHChk(this);'}).css({'width':'30px'}).addClass('component-input'))
						.append($('<em>').addClass('time-bar').text(" : "))
						.append($('<input>').attr({'type':'text','name':'P_VIDO_ST_MM', 'maxlength':'2', 'numeric':'', 'onkeyup':'fnTimeMMChk(this);'}).css({'width':'30px'}).addClass('component-input'))
					)))
			.append($('<td>')
					.append($('<div>').addClass('area-calen')
						.append($('<div>').addClass('component-calen')
							.append($('<div>').addClass('data-calen')
								.append($('<input>').attr({'type':'text','name':'P_VIDO_END_DE', 'date':''}).css({'width':'80px'}).addClass('component-input'))))
							.append($('<div>').addClass('component-time')
								.append($('<input>').attr({'type':'text','name':'P_VIDO_END_HH', 'maxlength':'2', 'numeric':'', 'onkeyup':'fnTimeHHChk(this);'}).css({'width':'30px'}).addClass('component-input'))
								.append($('<em>').addClass('time-bar').text(" : "))
								.append($('<input>').attr({'type':'text','name':'P_VIDO_END_MM', 'maxlength':'2', 'numeric':'', 'onkeyup':'fnTimeMMChk(this);'}).css({'width':'30px'}).addClass('component-input'))
							)))
			.append($('<td>')
				.append($('<input>').attr({'type':'hidden','name':'P_VIDO_MTNG_PRST_SECD','maxlength':'100'}).addClass('component-input type-full')))
			.append($('<td>'))
			.append($('<td>'))
		
		);
		setDatePicker();	
		
	};
	
	// delRow
	pageObj.delRow = function() {
		$("#showTbody input[name='chck']").each(function(inx){
			if(this.checked) {
				$(this).parent().parent().parent().remove();
			}
		});
	};
	
	pageObj.createMeetingRoom = function(obj) {
		
		//alert($(obj).parent().parent().find("#P_VIDO_MTNG_SEQ").val());
		
		var vidoMtngSeq = $(obj).parent().parent().find("#P_VIDO_MTNG_SEQ").val();
		
		$("#meetFrm input[name='P_VIDO_MTNG_SEQ']").val(vidoMtngSeq);
		FwkCmmnUtil.submitForm("meetFrm" , "/estm/cmtmMngCreateMeetingRoom.do");
	};
	
	
	moveRoom = function(VIDO_MTNG_URL_INFO) {
		var encodeStr = encodeURI(VIDO_MTNG_URL_INFO);
		//window.open(VIDO_MTNG_URL_INFO, "moveRoom", "width=750px,height=760px,toolbar=no,status=no,scrollbars=yes,resizeable=no,menubar=no,left=500,top=100");
		window.open(encodeStr, "moveRoom", "width=750px,height=760px,toolbar=no,status=no,scrollbars=yes,resizeable=no,menubar=no,left=500,top=100");
		return false;
	}
	
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
	
	pageObj.setEventHandler = function() {
		
		//회의참가요청목록 팝업 
		$(document).on("click","button[name='mtngPrtcRqs']", function() {
			$("#popupFrm input[name='P_ESTM_NO']").val($("#detailFrm input[name='P_ESTM_NO']").val());
			$("#popupFrm input[name='P_ESTM_PROCD_SEQ']").val($("#detailFrm input[name='P_ESTM_PROCD_SEQ']").val());
			$("#popupFrm input[name='P_VIDO_MTNG_SEQ']").val($(this).parent().parent().find("#P_VIDO_MTNG_SEQ").val());
			$("#popupFrm input[name='P_ESTM_OBJ_SECD']").val($("#detailFrm input[name='P_ESTM_OBJ_SECD']").val());
			
			$("#popupFrm").one("submit", function() {
				window.open("", "mtngPrtcRqstList", "width=750px,height=760px,toolbar=no,status=no,scrollbars=yes,resizeable=no,menubar=no,left=500,top=100");
				this.action = FwkCmmnUtil['contextPath']+'/comm/popup/mtngPrtcRqstList.do';
		        this.method = 'POST';
		        this.target = 'mtngPrtcRqstList';
		    }).trigger("submit");
			return false;
		
		});
		
		// 회의실생성 
		$(document).on("click","button[name='mtngMvmt']", function() {
			//FwkCmmnUtil.submitForm("detailFrm" , "/estm/estmProgVidoMtngDetail.do");
			pageObj.createMeetingRoom(this);
			//return false;
		});
		
		
		/**
		 * 저장버튼 클릭 이벤트
		 */
		$("#saveBtn").on("click", function() {
			var flag = true;
			$("#showTbody input[name='P_VIDO_MTNG_NM']").each(function(){
				if($(this).val() == ""){
					alert("[화상회의명] 항목은 필수입력 입니다.");
					$(this).focus();
					flag = false;
					return false;
				}
			});
			
			if(!flag){
				return false;
			}
			
			$("#showTbody input[name='P_VIDO_ST_DE']").each(function(){
				if($(this).val() == ""){
					alert("[회의시작일시(날짜)] 항목은 필수입력 입니다.");
					$(this).focus();
					flag = false;
					return false;
				}
			});
			
			if(!flag){
				return false;
			}
			
			$("#showTbody input[name='P_VIDO_ST_HH']").each(function(){
				if($(this).val() == ""){
					alert("[회의시작일시(시)] 항목은 필수입력 입니다.");
					$(this).focus();
					flag = false;
					return false;
				}
			});
			
			if(!flag){
				return false;
			}
			
			$("#showTbody input[name='P_VIDO_ST_MM']").each(function(){
				if($(this).val() == ""){
					alert("[회의시작일시(분)] 항목은 필수입력 입니다.");
					$(this).focus();
					flag = false;
					return false;
				}
			});
			
			if(!flag){
				return false;
			}
			
			$("#showTbody input[name='P_VIDO_END_DE']").each(function(){
				if($(this).val() == ""){
					alert("[회의종료일시(날짜)] 항목은 필수입력 입니다.");
					$(this).focus();
					flag = false;
					return false;
				}
			});
			
			if(!flag){
				return false;
			}
			
			$("#showTbody input[name='P_VIDO_END_HH']").each(function(){
				if($(this).val() == ""){
					alert("[회의종료일시(시)] 항목은 필수입력 입니다.");
					$(this).focus();
					flag = false;
					return false;
				}
			});
			
			if(!flag){
				return false;
			}
			
			$("#showTbody input[name='P_VIDO_END_MM']").each(function(){
				if($(this).val() == ""){
					alert("[회의종료일시(분)] 항목은 필수입력 입니다.");
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
			
			FwkCmmnUtil.submitForm("detailFrm" , "/estm/cmtmMngProgVidoMtngSave.do");
		});
		
		
		/**
		 * 목록버튼 클릭 이벤트
		 */
		$("#listBtn").on("click", function() {
			FwkCmmnUtil.submitForm("listFrm" , "/estm/cmtmMngProgList.do");
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
		
		
		
		
	};

	/**
	 * window load
	 *
	 */
	$(function() {		
		pageObj.setEventHandler();
		
	});
	
})();