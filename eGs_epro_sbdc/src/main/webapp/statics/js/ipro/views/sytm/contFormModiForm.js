(function() {

	/**
	 * Default Constructor
	 */
	pageObj = {};

	var defaultFrm = "modiFrm";
	var myEditor;
	
	
	pageObj.contFormList = function() {
		FwkCmmnUtil.submitForm("listFrm", "/sytm/contFormList.do");
	};
	
	
	rowAdd = function(){
		var row = $("#copyArea").children().clone();
		row.show();
		row.find("input[type=file]").eq(0).attr("name","P_FILE");
		row.find("input[type=text]").eq(0).attr("name","P_FILE_DOC_NM");
		row.find("input[name=P_FILE_DOC_NM]").prop("disabled","");
		$("#pasteArea").append(row);
	};
	
	rowDel = function(){
		$("input:checkbox[class='fileDel']").each(function(idx){ 
			var row = $(this).parent().parent();
			if($(this).prop("checked")){
				row.find("input[name='P_FILE_SN']").prop("disabled","");
				row.find("input[type=file]").attr("name","");
				row.find("input[type=text]").prop("name","");
				row.find("input[name=P_FILE_DOC_NM]").prop("disabled","disabled");
				row.hide();
			}
		});
	}; 
	
	allChk = function(){
		// 전체 체크 시
		if($("#allChk").prop("checked")){
			$("input:checkbox[class='fileDel']").each(function(inx){
				if(inx != 0){ // 추가시 복사
					$(this).prop("checked", true);
				}
			});
			
		// 전체 체크 해제 시
		}else{
			$("input:checkbox[class='fileDel']").each(function(inx){
				$(this).prop("checked", false);
			});
		}
	};
	
	fileSet = function(obj){
		var fileInfo = fileInfoSet(obj);
		var node = $(obj).parent().parent();
		if($(obj).val() != ""){
			node.find("input[type=text]").empty().val(fileInfo[0]);
			node.find("input[type=file]").prop("name","P_FILE");
			node.find("input[name='P_FILE_SN']").prop("disabled","");
			node.find("input[name='P_FILE_DOC_NM']").prop("disabled","");
		};
	}; 

    RAONKEDITOR_CreationComplete = function(editorId) {
	   	var html = $("#P_FRM_CNTN").val();
	

	 	RAONKEDITOR.SetHtmlContents(html, editorId);
	 };
    
	 pageObj.contFormUpdate = function() {
			//fn_getBodyValue('editor1');
		 FwkCmmnUtil.submitForm("modiFrm" , "/sytm/contFormModi.do");
	};

	contFormChk = function() { 
		var jsonData = $("#modiFrm").serializeObject();
		var actionUrl = "/sytm/contFormChk";
		var chkCnt = "";
		FwkCmmnUtil.submitAjax (actionUrl, jsonData 
				, function(res) {
				chkCnt =  res.contFormChkCnt;
		});
		return chkCnt;
	}; 
	
	fn_getBodyValue = function(editorID) {
			var rtnValue;
		    var fn_callback = function (paramObj) {
		    	
		    	rtnValue = paramObj.strData;	    	
				$("#P_FRM_CNTN").val(rtnValue);
				
				FwkCmmnUtil.submitForm("modiFrm" , "/sytm/contFormModi.do");
		    };

		    RAONKEDITOR.GetHtmlContents({
		        type: 'body',
		        callback: fn_callback
		    }, editorID);
		    
		    
		};
		
		contFileMod = function(obj){
			$(obj).parent().parent().parent().hide(); 
			$(obj).parent().parent().parent().next("tr").show();
			$(obj).parent().parent().parent().next("tr").find($("#P_CONT_FILE")).prop("name","P_CONT_FILE");
		}
		
		contCancelMod = function(obj){
			$(obj).parent().parent().parent().find($("#P_CONT_FILE")).prop("name","");
			$(obj).parent().parent().parent().hide(); 
			$(obj).parent().parent().parent().prev("tr").show(); 
		}
	pageObj.setEventHandler = function() {
		
		// 목록
		$("#listBtn").on("click", function() {
			pageObj.contFormList();
		});
		
		
		// 저장
		$("#updtBtn").on("click", function() {  
			
			if($("#P_FRM_NM").val() == null || $("#P_FRM_NM").val() == ""){
				alert("서식명을 입력해 주십시요.");
				$('#P_FRM_NM').focus();
				return false;
			}
			
			if($("#P_CONT_SECD").val() ==""){
				alert("계약구분을 선택해 주십시요.");
				$("#P_CONT_SECD").focus();
				return false;
			}
			
			if($("#P_CONT_KDCD").val() ==""){
				alert("계약분류를 선택해 주십시요.");
				$("#P_CONT_KDCD").focus();
				return false;
			}
			
			if($("select[name='P_CONT_KDCD']").val() == "A" ||  $("select[name='P_CONT_KDCD']").val() == "D"){
				if($("input[name='P_CONT_FILE']").val() == ""){
					alert("계약서 파일을 등록해주세요.");
					return false;
				}
			}
			
			for(var i = 0; i<$("#pasteArea input[name='P_FILE']").length;i++){
				 if($("#pasteArea input[name='P_FILE']").eq(i).val() == ""){
					 alert("계약자동첨부문서를 등록해주세요.");
					 return false;
				 }
			}
			
			var chkCnt = contFormChk();
			
			if(chkCnt >= 1 ){
				if(confirm("이미 등록된 서식이 있습니다.\n그래도 저장하시겠습니까?")){
					$("#modiFrm input[name='P_CHNG_FRM']").val("Y");
				}else{
					return false;
				}
			}else{
				if(!confirm("계약서식을 수정하시겠습니까?")){
					return false;
				}
			}
			
			/*if(!confirm("계약서식을 수정하시겠습니까?")){
				return false; 
			}*/
			
			$("[date]").each(function(){
				$(this).val($(this).val().replace( /-/g, ""));
			});
			pageObj.contFormUpdate();
		});
		
		$("#addBtn").on("click", function(){
			rowAdd();
		});
		
		$("#delBtn").on("click", function(){
			rowDel();
		});
		
		$("#allChk").on("click", function(){
			allChk();
		});
		
		$(".fileDel").on("click", function(){
			$("#allChk").prop("checked", false);
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