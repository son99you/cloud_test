(function() {

	/**
	 * Default Constructor
	 */
	pageObj = {};
	
	
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
	
	// 목록
	pageObj.estmSeMngList = function() {
		FwkCmmnUtil.submitForm("listFrm", "/sytm/estmSeMngList.do"); 
	};
	
	// 수정
	pageObj.estmSeMngUpdtForm = function(){
		FwkCmmnUtil.submitForm("detailFrm" , "/sytm/estmSeMngUpdtForm.do");
	};
	
	// 삭제
	pageObj.estmSeMngDelete = function(){
		if(!confirm("삭제 하시겠습니까?")){
			return false;
		}else {
			FwkCmmnUtil.submitForm("detailFrm" , "/sytm/estmSeMngDelete.do");	
		}
		
	};	
	
	
	// 합산식 저장
	pageObj.estmSeMngClcRulSave = function(){
		
		var CLC_RUL = "";
		$(".sansool").each(function (i) {
			var str = "";
			if($(this).attr("name") == "ESTM_PROCD_SEQ" ) {
				str = "ESTM_PROCD_SEQ" + $(this).val();
			}else {
				str = $(this).val();
			}
			
			if(i== 0) {
				CLC_RUL += str; 
			}else {
				CLC_RUL += "," +str;
			}
			
		});
		
		console.log("CLC_RUL  :: " + CLC_RUL);
		
		$("input[name='P_CLC_RUL']").val(CLC_RUL);
		
		FwkCmmnUtil.submitForm("detailFrm" , "/sytm/estmSeMngClcRulSave.do");
	};
	
	//산술추가로직
	pageObj.addSanSoolStrLogic = function(){
		var sansoolArray = new Array(1);
		var procdSeqArray = new Array(1);
		var sansoolLength = 0;
		$("input[name='SANSOOL_STR']").each(function(i) {
			sansoolArray[i] = $(this).val();
			sansoolLength++;
		});
		
		var procdSeqLength = 0;
		$("select[name='ESTM_PROCD_SEQ']").each(function(i) {
			procdSeqArray[i] = $(this).val();
			procdSeqLength++;
		});
		
		
		
		var strHtml = $("#sansool").html();
		
		if( (Number(sansoolLength)+Number(procdSeqLength)+Number(1)) % 6 == 0) {
			strHtml +="<br><br>";
		}
		
		strHtml  += "<input type=\"text\" name=\"SANSOOL_STR\" class=\"component-input sansool\" style=\"width:100px; float:left;\" >";
		strHtml  += "<a href=\"javascript:\" type=\"button\" class=\"btn-search-close\" name=\"delBtn\"></a>";
		$("#sansool").html(strHtml);
		
		for(var i = 0; i < sansoolLength; i++) {
			$("#sansool input[name='SANSOOL_STR']").eq(i).val(sansoolArray[i]);
		}
		
		for(var i = 0; i < procdSeqLength; i++) {
			$("#sansool select[name='ESTM_PROCD_SEQ']").eq(i).val(procdSeqArray[i]);
		}
	};
	
	//estmSeMngFrmPopup('${data.ESTM_FRM_NO}','${data.ESTM_PROCD_SEQ }')
	estmSeMngFrmPopup = function(ESTM_FRM_NO,ESTM_PROCD_SEQ) {// ,ESTM_PROCD_SECD
		$("#popupFrm input[name='P_ESTM_FRM_NO']").val(ESTM_FRM_NO);
		$("#popupFrm input[name='P_ESTM_PROCD_SEQ']").val(ESTM_PROCD_SEQ);
		
		$("#popupFrm").one("submit", function() {
			window.open("", "estmFrmViewPopup", "width=1024px,height=760px,toolbar=no,status=no,scrollbars=yes,resizeable=no,menubar=no,left=500,top=100");
			this.action = FwkCmmnUtil['contextPath']+'/comm/popup/estmSeMngFrmDetail.do';
	        this.method = 'POST';
	        this.target = 'estmFrmViewPopup';
	    }).trigger("submit");
	};
	
	//절차추가로직
	pageObj.addProcdLogic = function(){

		var sansoolArray = new Array(1);
		var procdSeqArray = new Array(1);
		var sansoolLength = 0;
		$("input[name='SANSOOL_STR']").each(function(i) {
			sansoolArray[i] = $(this).val();
			sansoolLength++;
		});
		
		var procdSeqLength = 0;
		$("select[name='ESTM_PROCD_SEQ']").each(function(i) {
			procdSeqArray[i] = $(this).val();
			procdSeqLength++;
		});
		
		var strHtml = $("#sansool").html();
		
		
		if( (Number(sansoolLength)+Number(procdSeqLength)+Number(1)) % 6 == 0) {
			strHtml +="<br><br>";
		}
		strHtml  += "<select class=\"component-select  sansool\" name=\"ESTM_PROCD_SEQ\" style=\"float:left; width:100px;\">";
			$("input[name='D_ESTM_PROCD_SEQ']").each(function(i) {
				strHtml  += "<option value=\""+ $("input[name='D_ESTM_PROCD_SEQ']").eq(i).val() +"\">" + $("input[name='D_ESTM_PROCD_NM']").eq(i).val() + "</option>";
				
			});
		strHtml  += "</select>";
		strHtml  += "<a href=\"javascript:\" type=\"button\" class=\"btn-search-close\" name=\"delBtn\"></a>";
		$("#sansool").html(strHtml);
		
		for(var i = 0; i < sansoolLength; i++) {
			$("#sansool input[name='SANSOOL_STR']").eq(i).val(sansoolArray[i]);
		}
		
		for(var i = 0; i < procdSeqLength; i++) {
			$("#sansool select[name='ESTM_PROCD_SEQ']").eq(i).val(procdSeqArray[i]);
		}
	};
	
	
	
	pageObj.setEventHandler = function() {
		
		$("#delSanSoolBtn").on("click", function() {
			$("#sansool").html("");
		});
		
		$("#sansoolSaveBtn").on("click", function() {
			pageObj.estmSeMngClcRulSave();
		});

		$("#addSanSoolStrBtn").on("click", function() {
			
			pageObj.addSanSoolStrLogic();
			
		});
		
		$("#addProcdBtn").on("click", function() {
			pageObj.addProcdLogic();
		});
		
		$(document).on("click","a[name='delBtn']", function() {
			$(this).prev().remove();
			$(this).remove();
		});
		
		//평가서식표 팝업 C
//		$(document).on("click","button[name='estmSeMngFrmPopup']", function() {
//			//$("#popupFrm input[name='P_FRM_GBN']").val("C");
//			
//			$("#popupFrm").one("submit", function() {
//				window.open("", "estmFrmPopupList", "width=750px,height=760px,toolbar=no,status=no,scrollbars=yes,resizeable=no,menubar=no,left=500,top=100");
//				this.action = FwkCmmnUtil['contextPath']+'/comm/popup/estmSeMngFrmDetail.do';
//		        this.method = 'POST';
//		        this.target = 'estmSeMngFrmDetail';
//		    }).trigger("submit");
//		});
		
		// 목록버튼
		$("#listBtn").on("click", function() {
			pageObj.estmSeMngList();
		});
		
		
		// 수정버튼
		$("#updtBtn").on("click", function() {
			pageObj.estmSeMngUpdtForm();
		});
		
		// 삭제버튼
		$("#delBtn").on("click", function() {
			pageObj.estmSeMngDelete();
		});
		
		
	};

	/**
	 * window load
	 *
	 */
	$(function() {
		pageObj.setEventHandler();
		
		if($("#resultCode").val() == "Success"){
			var estmSecd = $("#P_ESTM_SECD_TRANS").val();
			
			$("#P_ESTM_SECD").val(estmSecd);
			$("#resultCode").val("");
			FwkCmmnUtil.submitForm("detailFrm", "/sytm/estmSeMngDetail.do");
		}
		
		// 평가결과 산술식
		var pClcRul = $("#P_CLC_RUL").val();
		
		var pClcRulSplit = pClcRul.split(",");
		var strHtml = $("#sansool").html();
		
		for( var i in pClcRulSplit) {
			if(pClcRulSplit[i].match("ESTM_PROCD_SEQ") == "ESTM_PROCD_SEQ") {
				var replaceVal = pClcRulSplit[i];
				replaceVal = replaceVal.replace("ESTM_PROCD_SEQ","");
				
				if( (i != 0) && (i % 6 == 0) ) {
					strHtml +="<br><br>";
				}
				strHtml  += "<select class=\"component-select  sansool\" name=\"ESTM_PROCD_SEQ\" style=\"float:left; width:100px;\">";
					$("input[name='D_ESTM_PROCD_SEQ']").each(function(i) {
						
						if (replaceVal == $("input[name='D_ESTM_PROCD_SEQ']").eq(i).val()) {
							strHtml  += "<option value=\""+ $("input[name='D_ESTM_PROCD_SEQ']").eq(i).val() +"\" selected >" + $("input[name='D_ESTM_PROCD_NM']").eq(i).val() + "</option>";
						}else {
							strHtml  += "<option value=\""+ $("input[name='D_ESTM_PROCD_SEQ']").eq(i).val() +"\">" + $("input[name='D_ESTM_PROCD_NM']").eq(i).val() + "</option>";
						}
						
					});
				strHtml  += "</select>";
				strHtml  += "<a href=\"javascript:\" type=\"button\" class=\"btn-search-close\" name=\"delBtn\"></a>";
				$("#sansool").html(strHtml);
				
				//$("#sansool select[name='ESTM_PROCD_SEQ']").eq(i).val(replaceVal);
				
			}else {
				var replaceVal = pClcRulSplit[i];
				var strHtml = $("#sansool").html();
				
				if( (i != 0) && (i % 6 == 0)) {
					strHtml +="<br><br>";
				}
				
				strHtml  += "<input type=\"text\" name=\"SANSOOL_STR\" class=\"component-input sansool\" style=\"width:100px; float:left;\" value=\"" + replaceVal + "\" >";
				strHtml  += "<a href=\"javascript:\" type=\"button\" class=\"btn-search-close\" name=\"delBtn\"></a>";
				$("#sansool").html(strHtml);
				
				//$("#sansool input[name='SANSOOL_STR']").eq(i).val(replaceVal);
			}
		}
		
		
	});
	
})();